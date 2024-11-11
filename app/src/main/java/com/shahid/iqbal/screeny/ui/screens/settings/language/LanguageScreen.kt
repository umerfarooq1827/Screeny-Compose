package com.shahid.iqbal.screeny.ui.screens.settings.language

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.shahid.iqbal.screeny.R
import com.shahid.iqbal.screeny.ui.screens.settings.language.utils.LANGUAGES_LIST
import com.shahid.iqbal.screeny.ui.screens.settings.language.utils.LanguageEntity
import com.shahid.iqbal.screeny.ui.theme.screenyFontFamily
import com.shahid.iqbal.screeny.ui.utils.ComponentHelpers.noRippleClickable
import com.shahid.iqbal.screeny.ui.utils.NoRippleInteractionSource


@Composable
fun LanguageScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) {

        var isSelected by remember {
            mutableStateOf<LanguageEntity?>(null)
        }


        Toolbar { navController.navigateUp() }

        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally, contentPadding = PaddingValues(10.dp)
        ) {
            items(LANGUAGES_LIST, key = { it.languageName }) { language ->
                SingleLanguageItem(language = language, isSelected = (language == isSelected),
                    onClick = { selectedLanguage -> isSelected = selectedLanguage })
            }
        }

    }
}


@Composable
private fun LazyItemScope.SingleLanguageItem(
    language: LanguageEntity, isSelected: Boolean, onClick: (LanguageEntity) -> Unit
) {

    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent, label = "Select Language Background Color"
    )

    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .animateItem()
        .clip(shape = RoundedCornerShape(10.dp))
        .noRippleClickable { onClick(language) }
        .background(
            color = backgroundColor
        )
        .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {

        Text(
            text = language.flag, style = MaterialTheme.typography.titleLarge, modifier = Modifier
                .wrapContentSize()
                .padding(end = 10.dp)
        )

        Text(
            text = language.languageName, style = MaterialTheme.typography.titleMedium, modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )

        RadioButton(
            selected = isSelected, onClick = null, enabled = false, colors = RadioButtonDefaults.colors(
                disabledUnselectedColor = RadioButtonDefaults.colors().unselectedColor, disabledSelectedColor = RadioButtonDefaults.colors().selectedColor
            )
        )
    }
}


@Composable
fun Toolbar(
    modifier: Modifier = Modifier, onBackPress: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp), verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = onBackPress, interactionSource = NoRippleInteractionSource()
        ) {
            Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
        }


        Text(
            text = stringResource(id = R.string.language), style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), fontFamily = screenyFontFamily
        )

    }
}

