package com.shahid.iqbal.screeny.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shahid.iqbal.screeny.R
import com.shahid.iqbal.screeny.ui.theme.ActionIconBgColor

@Composable
fun ActionButtons(
    isFavourite: Boolean = false, onDownload: () -> Unit = {}, onApply: () -> Unit = {}, onFavourite: () -> Unit = {}
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(0.70f)
            .wrapContentHeight()
            .padding(vertical = 50.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom
    ) {


        Image(painterResource(id = R.drawable.download_icon), stringResource(id = R.string.download), Modifier
            .size(40.dp)
            .clickable { onDownload() }
            .clip(CircleShape)
            .background(color = ActionIconBgColor)
            .padding(8.dp),
            colorFilter = ColorFilter.tint(color = Color.White))

        Image(painterResource(id = R.drawable.apply_icon), null, Modifier
            .size(40.dp)
            .clip(CircleShape)
            .clickable { onApply() }
            .background(color = Color.White))

        Image(
            imageVector = if (isFavourite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = stringResource(id = R.string.favourite),
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable { onFavourite() }
                .background(color = ActionIconBgColor)
                .padding(8.dp)
        )


    }
}