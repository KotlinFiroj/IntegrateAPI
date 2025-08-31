package com.example.testone.prasentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.testone.app.ui.theme.loginBG

@Composable
fun LoadLogin() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        ConstraintLayout(modifier = Modifier.padding(8.dp)) {

            val (card, userName, password, loginButton) = createRefs()
            Box(modifier = Modifier.background(color = loginBG)
                .constrainAs(card) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)

                }) {


            }

            Text(
                modifier = Modifier.padding(8.dp)
                    .constrainAs(userName){
                        top.linkTo(card.top, 8.dp)
                        start.linkTo(card.start, 8.dp)
                    },
                text = "Username")

            Text(modifier = Modifier.padding(8.dp)
                .constrainAs(password){
                    start.linkTo(userName.start)
                    top.linkTo(userName.bottom, 8.dp)
                },

                text = "Password")

            Button(modifier = Modifier.padding(8.dp)
                .constrainAs(loginButton) {
                    start.linkTo(userName.start)
                    top.linkTo(password.bottom, 8.dp)
                },
                onClick = {})
            { Text(text = "Login") }




        }


    }

}
