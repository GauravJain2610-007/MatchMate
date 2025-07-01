package com.example.matchmate.data.repository

import UserRemoteMediator
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.matchmate.data.local.UserDetailsDao
import com.example.matchmate.data.network.NetworkInterface
import com.example.matchmate.data.network.models.Id
import com.example.matchmate.data.network.models.UserDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MatchMateRepository @Inject constructor(
    var networkInterface: NetworkInterface,
    var userDetailsDao: UserDetailsDao
){


    @OptIn(ExperimentalPagingApi::class)
    fun getUserPagingFlow(): Flow<PagingData<UserDetails>> {
        return Pager(
            config = PagingConfig(pageSize = 40, prefetchDistance = 4),
            remoteMediator = UserRemoteMediator(networkInterface, userDetailsDao),
            pagingSourceFactory = { userDetailsDao.getPagedUsers(20) }
        ).flow
    }

    suspend fun updateUserAction(userAction: String, userId:Id){
        userDetailsDao.updateUserAction(userAction, userId)
    }
}