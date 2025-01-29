package com.shahid.iqbal.screeny.ui.screens.settings.language

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.shahid.iqbal.screeny.R
import com.shahid.iqbal.screeny.ui.screens.settings.utils.LANGUAGES_LIST
import com.shahid.iqbal.screeny.ui.screens.settings.utils.LanguageEntity
import com.shahid.iqbal.screeny.ui.screens.settings.utils.setUserSelectedLanguageForApp
import com.shahid.iqbal.screeny.ui.theme.screenyFontFamily
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LanguageScreen(
    modifier: Modifier = Modifier, navController: NavController, languageViewModel: LanguageViewModel = koinViewModel()
) {

    val currentSelected by languageViewModel.currentLanguage.collectAsStateWithLifecycle()

    var localSelected by remember {
        mutableStateOf<LanguageEntity?>(null)
    }
    val state = rememberLazyListState()
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding(),
    ) {


        Toolbar(
            onBackPress = { navController.navigateUp() },
            isApplyEnable = localSelected != null,
            onApply = {

                if (localSelected == null)
                    return@Toolbar

                setUserSelectedLanguageForApp(context,localSelected!!.languageCode)
                languageViewModel.updateCurrentLanguage(localSelected!!)
                navController.navigateUp()
            }
        )


        Spacer(modifier = Modifier.height(10.dp))

        Text(
            stringResource(id = R.string.system_default),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
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
        CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
            LazyColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(), state = state
            ) {
                items(LANGUAGES_LIST.filter { it != currentSelected }, key = { it.languageName }) { language ->
                    SingleLanguageItem(language = language, isSelected = (language == localSelected),
                        onClick = { selectedLanguage -> localSelected = selectedLanguage })
                }
            }
        }
    }
}






