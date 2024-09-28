package com.shahid.iqbal.screeny.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shahid.iqbal.screeny.R
import com.shahid.iqbal.screeny.models.RecentSearch
import com.shahid.iqbal.screeny.ui.theme.GrayColor
import com.shahid.iqbal.screeny.ui.theme.screenyFontFamily
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchedWallpaperScreen() {

    var text by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(true) }
    val searchViewModel = koinViewModel<SearchViewModel>()
    val recentSearches by searchViewModel.recentSearches.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth(0.97f)
                .wrapContentHeight(),
            inputField = {
                SearchBarDefaults.InputField(
                    query = text,
                    onQueryChange = { text = it },
                    onSearch = {
                        expanded = false
                        searchViewModel.saveRecentSearch(text)
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text(stringResource(id = R.string.search_wallpaper)) },
                    leadingIcon = {
                        if (!expanded) Icon(Icons.Default.Search, contentDescription = null) else
                            Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null,
                                modifier = Modifier.clickable {
                                    text = ""
                                    expanded = false
                                })
                    },
                    trailingIcon = {
                        if (text.isNotEmpty())
                            Icon(Icons.Default.Clear, contentDescription = null,
                                modifier =
                                Modifier.clickable {
                                    text = ""
                                    expanded = false
                                })
                    },

                    )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
            colors = if (expanded) SearchBarDefaults.colors(containerColor = MaterialTheme.colorScheme.background) else
                SearchBarDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        )
        {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(vertical = 20.dp, horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(recentSearches) {
                    SingleRecentItem(recentSearch = it)
                }
            }
        }
    }
}

@Composable
fun SingleRecentItem(recentSearch: RecentSearch) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(
            painter = painterResource(id = R.drawable.history), contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = recentSearch.query, fontFamily = screenyFontFamily,
            style = MaterialTheme.typography.titleMedium
                .copy(color = MaterialTheme.colorScheme.onSurfaceVariant), modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp)
        )
    }
}