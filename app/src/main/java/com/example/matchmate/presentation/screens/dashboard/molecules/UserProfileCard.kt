package com.example.matchmate.presentation.screens.dashboard.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.matchmate.R
import com.example.matchmate.data.network.models.Dob
import com.example.matchmate.data.network.models.Id
import com.example.matchmate.data.network.models.Location
import com.example.matchmate.data.network.models.Login
import com.example.matchmate.data.network.models.Name
import com.example.matchmate.data.network.models.Picture
import com.example.matchmate.data.network.models.Registered
import com.example.matchmate.data.network.models.Street
import com.example.matchmate.data.network.models.UserDetails


@Composable
fun UserProfileCard(userDetails: UserDetails, onAccept:(UserDetails) -> Unit = {}, onDecline:(UserDetails)-> Unit = {}) {

    Card (modifier = Modifier.fillMaxWidth(), elevation = 8.dp, shape = RoundedCornerShape(24.dp) , backgroundColor = if(userDetails.matchPercentage == 100 ) Color(
        0xFFD3ECCD
    ) else if(userDetails.matchPercentage == 50)
        Color(0xFFFFA500)
    else Color.White) {


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            AsyncImage(
                model = userDetails.picture?.medium,
                contentDescription = "",
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .padding(10.dp),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentScale = ContentScale.Fit
            )


            Text(
                text = userDetails.name?.first.orEmpty() + " " + userDetails.name?.last.orEmpty(),
                modifier = Modifier.padding(end = 8.dp),
                style = typography.h5,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = getLocationCombinedData(userDetails.location),
                modifier = Modifier.padding(end = 8.dp),
                style = typography.subtitle2,
                color = Color.DarkGray
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {


                if(userDetails.userLastAction!=null){
                    if(userDetails.userLastAction == "N") {
                        CircularIconButton(
                            painter = painterResource(R.drawable.ic_close),
                            backgroundColor = Color.Red
                        )
                    }
                    else {
                        CircularIconButton(
                            painter = painterResource(R.drawable.ic_check),
                            backgroundColor = Color(0xFF06923E)
                        )
                    }
                }
                else {
                    CircularIconButton(
                        painter = painterResource(R.drawable.ic_close),
                        onClick = {
                            onDecline.invoke(userDetails)
                        },
                        backgroundColor = Color.Red
                    )

                    CircularIconButton(
                        painter = painterResource(R.drawable.ic_check),
                        onClick = {
                            onAccept.invoke(userDetails)
                        },
                        backgroundColor = Color(0xFF06923E)
                    )
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun previewUserProfileCard(){

    val sampleUser = UserDetails(
        gender = "male",
        name = Name(
            title = "Mr",
            first = "Nolan",
            last = "Laurent"
        ),
        location = Location(
            street = Street(
                number = "7038",
                name = "Place Paul-Duquaire"
            ),
            city = "Strasbourg",
            state = "Finistère",
            country = "France",
            postcode = "69208",
//            coordinates = Coordinates(
//                latitude = "-13.3294",
//                longitude = "-32.8392"
//            ),
//            timezone = Timezone(
//                offset = "-7:00",
//                description = "Mountain Time (US & Canada)"
//            )
        ),
        email = "nolan.laurent@example.com",
        login = Login(
            uuid = "c73b4d4a-2d7c-4a9a-b730-02cb5b276492",
            username = "brownbear111",
            password = "illusion",
            salt = "iYCWNLNS",
            md5 = "bc682d4b99ca7a7dbe560dc353b40921",
            sha1 = "ea4bc178bf98459ace1af6e4884aa26d6880b64f",
            sha256 = "39e0396f14545f9875524907b0a5489c5b33dd97cfcdad9787cd6739a432a87b"
        ),
        dob = Dob(
            date = "1954-02-22T11:06:28.325Z",
            age = 71
        ),
        registered = Registered(
            date = "2017-12-02T10:48:57.372Z",
            age = 7
        ),
        phone = "02-71-44-25-16",
        cell = "06-93-02-42-71",
        id = Id(
            name = "INSEE",
            value = "1540171480350 53"
        ),
        picture = Picture(
            large = "https://randomuser.me/api/portraits/men/73.jpg",
            medium = "https://randomuser.me/api/portraits/med/men/73.jpg",
            thumbnail = "https://randomuser.me/api/portraits/thumb/men/73.jpg"
        ),
        nat = "FR"
    )

    UserProfileCard(sampleUser)

}


fun getLocationCombinedData(location: Location?):String{
    return if(location!=null)
        return location.street?.number.toString() + ", " + location.street?.name + ", " + location.city
    else{
        ""
    }
}