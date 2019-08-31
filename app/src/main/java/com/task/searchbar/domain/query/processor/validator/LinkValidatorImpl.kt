package com.task.searchbar.domain.query.processor.validator

import com.task.searchbar.domain.query.processor.SCHEMA.*
import java.net.URL
import java.util.*
import java.util.regex.Pattern
import javax.inject.Inject

class LinkValidatorImpl @Inject constructor() : LinkValidator {

    private val linkRegex = ("^((ftp|http|https|intent)?://)"                      // support scheme
            + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
            + "(([0-9]{1,3}\\.){3}[0-9]{1,3}"                            // IP形式的URL -> 199.194.52.184
            + "|"                                                        // 允许IP和DOMAIN（域名）
            + "(.)*"                                                     // 域名 -> www.
            // + "([0-9a-z_!~*'()-]+\\.)*"                               // 域名 -> www.
            + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\."                    // 二级域名
            + "[a-z]{2,6})"                                              // first level domain -> .com or .museum
            + "(:[0-9]{1,4})?"                                           // 端口 -> :80
            + "((/?)|"                                                   // a slash isn't required if there is no file name
            + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$")


    /**
     * Check the provided query whether is a link or not.
     * @return true if is a link
     */
    override fun isLink(query: String): Boolean {

        val temp = query.toLowerCase(Locale.getDefault())

        if (temp.startsWith(ABOUT_BLANK.value))
            return true

        val pattern = Pattern.compile(linkRegex)
        return pattern.matcher(query).matches()
    }

    override fun isFacebookLink(query: String): Boolean {
        return if (isLink(query)) {
            val url = getURL(query)
            val host = url.host.replace("m.", "")
            host == "fb.com" || host == "facebook.com"
        } else false
    }

    override fun isMailLink(query: String): Boolean {
        return isLink(query) && query.startsWith(MAIL_TO.value)
    }

    private fun getURL(query: String): URL {
        return if (!query.contains("://"))
            URL(HTTPS.value + query)
        else
            URL(query)
    }
}