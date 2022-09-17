package io.github.onotoliy.ofiles.data

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

/**
 * Запрос на загрузку файла с другого сайта по URL.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "discriminator")
@JsonSubTypes(
    Type(NoneFileURLRequest::class, name = "None"),
    Type(BasicFileURLRequest::class, name = "Basic"),
    Type(BearerFileURLRequest::class, name = "Bearer"),
)
@JsonInclude(Include.NON_EMPTY)
@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("discriminator")
actual sealed class FileURLRequest {

    /**
     * URL.
     */
    actual abstract val url: String
}

/**
 * Запрос на загрузку файла с другого сайта по URL, без авторизации.
 */
@Serializable
@SerialName("None")
actual data class NoneFileURLRequest(
    actual override val url: String = ""
): FileURLRequest()

/**
 * Запрос на загрузку файла с другого сайта по URL, с Basic авторизацией.
 */
@Serializable
@SerialName("Basic")
actual data class BasicFileURLRequest(
    actual override val url: String = "",

    /**
     * Токен.
     */
    actual val token: String = ""
): FileURLRequest()

/**
 * Запрос на загрузку файла с другого сайта по URL, с Bearer авторизацией.
 */
@Serializable
@SerialName("Bearer")
actual data class BearerFileURLRequest(
    actual override val url: String = "",

    /**
     * Токен.
     */
    actual val token: String = ""
): FileURLRequest()
