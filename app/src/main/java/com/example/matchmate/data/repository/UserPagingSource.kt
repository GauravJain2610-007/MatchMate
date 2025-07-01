import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import com.example.matchmate.data.network.NetworkInterface
import com.example.matchmate.data.network.models.UserDetails
import androidx.paging.LoadType
import androidx.paging.PagingState
import com.example.matchmate.data.local.UserDetailsDao


@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val apiService: NetworkInterface,
    private val userDetailsDao: UserDetailsDao
) : RemoteMediator<Int, UserDetails>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserDetails>
    ): MediatorResult {

        Log.d("UserPaging loadType", ""+loadType)

        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                Log.d("UserPaging lastItem", ""+lastItem)
                if (lastItem == null)
                    1
                else (state.pages.size + 1)
            }
        }

        try {
            val response = apiService.getUsersList(page = page)
            if (response.isSuccessful) {
                val users = response.body()?.results
                     ?: emptyList()

                Log.d("UserPaging users", ""+users.size + " page "+page)

                if (loadType == LoadType.REFRESH) userDetailsDao.clearAll()
                userDetailsDao.insertUsers(users)

                return MediatorResult.Success(endOfPaginationReached =  users.isEmpty())
            } else {
                Log.d("UserPaging api error", ""+response.message())
                return MediatorResult.Error(Exception("API error ${response.code()}"))
            }
        } catch (e: Exception) {
            Log.d("UserPaging exception", ""+e.message)
            return MediatorResult.Error(e)
        }
    }
}
