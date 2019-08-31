package com.task.searchbar.domain.query.processor

enum class SCHEMA constructor(val value: String) {
    ABOUT("about:"),
    HTTPS("https://"),
    ABOUT_BLANK("about:blank"),
    MAIL_TO("mailto:"),
    FACEBOOK("fb://")
}
