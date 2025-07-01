package com.example.matchmate.presentation.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.matchmate.data.network.models.UserDetails
import com.example.matchmate.data.repository.MatchMateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MatchMateViewModel @Inject constructor(val matchMateRepository: MatchMateRepository) : ViewModel(){

    // Paging flow
    val userPagingFlow: Flow<PagingData<UserDetails>> =
        matchMateRepository.getUserPagingFlow().map { data ->
            data.map {
                // Login user details
                it.preferenceMatchScoreCheck(50, "France")
            }
        }.cachedIn(viewModelScope)



    fun updateUserAction(accept:Boolean, userDetails: UserDetails){

        viewModelScope.launch {
            if(accept){
                matchMateRepository.updateUserAction("Y", userDetails.id)
            }
            else{
                matchMateRepository.updateUserAction("N", userDetails.id)
            }

        }

    }

}


// Here I have added logic of match maker by age and country(changing color or card green,orange and white ), we can modify it according to more additional factor
fun UserDetails.preferenceMatchScoreCheck(currentUserAge: Int, currentUserCountry: String): UserDetails {
    val ageMatch = kotlin.math.abs(currentUserAge - (this.dob?.age ?: 0 )) <= 6
    val countryMatch = this.location?.country.equals(currentUserCountry, ignoreCase = true)

    val matchPercentage = when {
        ageMatch && countryMatch -> 100 // green
        ageMatch || countryMatch -> 50 // Orange
        else -> 10 // white
    }
    return this.copy(matchPercentage = matchPercentage)
}
