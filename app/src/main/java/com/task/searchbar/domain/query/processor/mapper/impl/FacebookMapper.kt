package com.task.searchbar.domain.query.processor.mapper.impl

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import com.task.searchbar.domain.query.processor.SCHEMA
import com.task.searchbar.domain.query.processor.mapper.QueryMapper
import javax.inject.Inject


class FacebookMapper @Inject constructor(private val context: Context) : QueryMapper {

    override fun map(query: String): Uri {
        return if (isFacebookAppReady())
            Uri.parse(appendFacebookSchema(query))
        else
            Uri.parse(addHttpsSchema(query))
    }


    private fun appendFacebookSchema(query: String): String {
        val queryWithSchema = addHttpsSchema(query)
        val uriQuery = Uri.parse(query).query
        return if (uriQuery == null || uriQuery.isBlank())
            SCHEMA.FACEBOOK.value + "profile"
        else SCHEMA.FACEBOOK.value + "facewebmodal/f?href=" + queryWithSchema
    }

    private fun isFacebookAppReady(): Boolean {
        return try {
            val applicationInfo =
                context.packageManager.getApplicationInfo("com.facebook.katana", 0)
            applicationInfo.enabled
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    private fun addHttpsSchema(query: String): String {
        return if (query.startsWith(SCHEMA.HTTPS.value))
            query
        else
            SCHEMA.HTTPS.value + query
    }
}