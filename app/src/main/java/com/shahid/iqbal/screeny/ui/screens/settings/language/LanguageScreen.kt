package com.shahid.iqbal.screeny.ui.screens.settings.language

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.shahid.iqbal.screeny.R
import com.shahid.iqbal.screeny.ui.screens.settings.utils.LANGUAGES_LIST
import com.shahid.iqbal.screeny.ui.screens.settings.utils.LanguageEntity
import com.shahid.iqbal.screeny.ui.theme.screenyFontFamily
import com.shahid.iqbal.screeny.ui.utils.ComponentHelpers.noRippleClickable
import com.shahid.iqbal.screeny.ui.utils.NoRippleInteractionSource
import org.koin.androidx.compose.koinViewModel


@Composable
fun LanguageScreen(
    modifier: Modifier = Modifier, navController: NavController, languageViewModel: LanguageViewModel = koinViewModel()
) {

    val currentSelected by languageViewModel.currentLanguage.collectAsStateWithLifecycle()

    var localSelected by remember {
        mutableStateOf<LanguageEntity?>(null)
    }
    val state = rememberLazyListState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) {


        Toolbar(
            onBackPress = { navController.navigateUp() },
            isApplyEnable = localSelected != null,
            onApply = {

                if (localSelected == null)
                    return@Toolbar

                languageViewModel.updateCurrentLanguage(localSelected!!)
            }
        )


        Spacer(modifier = Modifier.height(20.dp))

        Text(
            stringResource(id = R.string.current_language),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            fontFamily = screenyFontFamily,
            modifier = Modifier.padding(10.dp)
        )
        SingleLanguageItem(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            currentSelected,
            isSelected = true,
            canApplyBg = false,
            onClick = {})


        Spacer(modifier = Modifier.height(20.dp))
        Text(
            stringResource(id = R.string.all_languages),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            fontFamily = screenyFontFamily,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(
                horizontal = 10.dp,
                vertical = 5.dp,
            ), state = state
        ) {
            items(LANGUAGES_LIST.filter { it != currentSelected }, key = { it.languageName }) { language ->
                SingleLanguageItem(language = language, isSelected = (language == localSelected),
                    onClick = { selectedLanguage -> localSelected = selectedLanguage })
            }
        }

    }
}


@Composable
private fun SingleLanguageItem(
    modifier: Modifier = Modifier,
    language: LanguageEntity,
    isSelected: Boolean,
    canApplyBg: Boolean = true,
    onClick: (LanguageEntity) -> Unit
) {

    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected && canApplyBg)
            MaterialTheme.colorScheme.primaryContainer else Color.Transparent, label = "Select Language Background Color"
    )

    Row(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clip(shape = RoundedCornerShape(10.dp))
        .noRippleClickable { onClick(language) }
        .background(
            color = backgroundColor
        )
        .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {

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
    modifier: Modifier = Modifier,
    isApplyEnable:Boolean,
    onBackPress: () -> Unit,
    onApply: () -> Unit
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
            text = stringResource(id = R.string.language),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            fontFamily = screenyFontFamily,
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = onApply,
            shape = RoundedCornerShape(10.dp),
            interactionSource = NoRippleInteractionSource(),
            modifier = Modifier
                .height(35.dp)
                .padding(horizontal = 10.dp),
            enabled = isApplyEnable
        ) {
            Text(
                text = stringResource(id = R.string.apply),
                style = MaterialTheme.typography.titleSmall,
                fontFamily = screenyFontFamily,
            )
        }
    }
}

