package com.trakdesk.app.models.contacts

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class ContactResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("meta") val meta: Meta,
    @SerializedName("data") var data: List<ContactsData>?
)


data class Meta(
    @SerializedName("status_code") val status_code: Int,
    @SerializedName("headers") val headers: Headers
)

@Parcelize
data class Headers(
    @SerializedName("access-control-allow-methods") val accessControlAllowMethods: String,
    @SerializedName("access-control-allow-origin") val accessControlAllowOrigin: String,
    @SerializedName("access-control-expose-headers") val accessControlExposeHeaders: String,
    @SerializedName("content-type") val contentType: String,
    @SerializedName("date") val date: String,
    @SerializedName("pagination-count") val paginationCount: Int,
    @SerializedName("pagination-limit") val paginationLimit: Int,
    @SerializedName("pagination-page") val paginationPage: Int,
    @SerializedName("self") val self: String,
    @SerializedName("server") val server: String,
    @SerializedName("x-ratelimit-remaining") val xRatelimitRemaining: Int,
    @SerializedName("x-ratelimit-reset") val xRatelimitReset: String,
    @SerializedName("x-ratelimit-total") val xRatelimitTotal: Int,
    @SerializedName("x-ratelimit-used") val xRatelimitUsed: Int,
    @SerializedName("x-trakdesk-api-version") val xTrakdeskApiVersion: String
) : Parcelable

data class ContactsData(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("job_title") val jobTitle: String,
    @SerializedName("email") val email: String,
    @SerializedName("company_id") val company_id: Any,
    @SerializedName("pagination-page") val paginationPage: Int,
    @SerializedName("secondary_emails") val secondaryEmails: List<String>,
    @SerializedName("address") val address: Any,
    @SerializedName("phone") val phone: Any,
    @SerializedName("mobile") val mobile: Any,
    @SerializedName("fax") val fax: Any,
    @SerializedName("department_id") val departmentId: Any,
    @SerializedName("language") val language: String,
    @SerializedName("notes") val notes: Any,
    @SerializedName("verified") val verified: Boolean,
    @SerializedName("blocked") val blocked: Boolean,
    @SerializedName("deleted") val deleted: Boolean,
    @SerializedName("view_all_tickets") val viewAllTickets: Any,
    @SerializedName("avatar_url") val avatarUrl: Any,
    @SerializedName("custom_fields") val customFields: CustomFields,
    @SerializedName("last_login_at") val lastLoginAt: Any
)

data class CustomFields(
    @SerializedName("c37") val c37: Any,
    @SerializedName("c40") val c40: Any,
    @SerializedName("c44") val c44: Any,
    @SerializedName("c45") val c45: Any,
    @SerializedName("c49") val c49: Any,
    @SerializedName("c51") val c51: Any,
    @SerializedName("c56") val c56: Any,
    @SerializedName("c57") val c57: Any,
    @SerializedName("c65") val c65: Any
)



