package io.github.onotoliy.ofiles.data

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

/**
 * Запрос на загрузку файла с другого сайта по URL.
 */
@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("discriminator")
expect sealed class FileURLRequest {

    /**
     * URL.
     */
    abstract val url: String
}

/**
 * Запрос на загрузку файла с другого сайта по URL, без авторизации.
 */
@Serializable
@SerialName("None")
expect class NoneFileURLRequest: FileURLRequest {
    override val url: String
}

/**
 * Запрос на загрузку файла с другого сайта по URL, с Basic авторизацией.
 */
@Serializable
@SerialName("Basic")
expect class BasicFileURLRequest: FileURLRequest {
    override val url: String

    /**
     * Токен.
     */
    val token: String
}

/**
 * Запрос на загрузку файла с другого сайта по URL, с Bearer авторизацией.
 */
@Serializable
@SerialName("Bearer")
expect class BearerFileURLRequest: FileURLRequest {
    override val url: String

    /**
     * Токен.
     */
    val token: String
}
