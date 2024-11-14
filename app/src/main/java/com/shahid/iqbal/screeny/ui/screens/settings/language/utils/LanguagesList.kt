package com.shahid.iqbal.screeny.ui.screens.settings.language.utils

import com.shahid.iqbal.screeny.ui.screens.settings.language.utils.CountryFlags.getCountryFlag



val LANGUAGES_LIST by lazy {
    listOf(
        LanguageEntity("sa", "Arabic (عربي)", "ar", getCountryFlag("sa")),
        LanguageEntity("gb", "English", "en", getCountryFlag("gb")),
        LanguageEntity("ru", "Russian  (Русский)", "ru", getCountryFlag("ru")),
        LanguageEntity("id", "Indonesian (Bahasa Indonesia)", "in", getCountryFlag("id")),
        LanguageEntity("bd", "Bengali (বাংলা)", "bn", getCountryFlag("bd")),
        LanguageEntity("in", "Hindi  (हिंदी)", "hi", getCountryFlag("in")),
        LanguageEntity("kp", "Korean (한국인)", "ko", getCountryFlag("kp")),
        LanguageEntity("jp", "Japanese (日本)", "ja", getCountryFlag("jp")),
        LanguageEntity("cn", "Chinese (中国人)", "zh", getCountryFlag("cn")),
        LanguageEntity("pl", "Polish (Polski)", "pl", getCountryFlag("pl")),
        LanguageEntity("fr", "French (Français)", "fr", getCountryFlag("fr")),
        LanguageEntity("it", "Italian (Italiano)", "it", getCountryFlag("it")),
        LanguageEntity("in", "Tamil (தமிழ்)", "ta", getCountryFlag("in")),
        LanguageEntity("pk", "Urdu (اردو)", "ur", getCountryFlag("pk")),
        LanguageEntity("de", "German (Deutsch)", "de", getCountryFlag("de")),
        LanguageEntity("es", "Spanish (Español)", "es", getCountryFlag("es")),
        LanguageEntity("nl", "Dutch (Nederlands)", "nl", getCountryFlag("nl")),
        LanguageEntity("pt", "Portuguese (Português)", "pt", getCountryFlag("pt")),
        LanguageEntity("th", "Thai (แบบไทย)", "th", getCountryFlag("th")),
        LanguageEntity("tr", "Turkish (Türkçe)", "tr", getCountryFlag("tr")),
        LanguageEntity("ro", "Romanian(Română)", "ro", getCountryFlag("ro")),
        LanguageEntity("ms", "Malay(Melayu)", "ro", getCountryFlag("ms")),
        LanguageEntity("ir", "Persian (فارسی)", "fa", getCountryFlag("ir"))
    ).sortedBy { it.languageName.substringBefore(" (") }
}
