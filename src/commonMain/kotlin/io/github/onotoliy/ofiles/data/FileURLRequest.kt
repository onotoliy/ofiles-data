package io.github.onotoliy.ofiles.data

import io.github.onotoliy.core.data.HasAuthor
import io.github.onotoliy.core.data.HasCreationDate
import io.github.onotoliy.core.data.HasName
import io.github.onotoliy.core.data.HasUID
import io.github.onotoliy.core.data.Option
import kotlinx.datetime.Instant
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
expect sealed class FileURLRequest: HasUID, HasName, HasAuthor, HasCreationDate {
    abstract override val uid: String
    abstract override val name: String
    abstract override val author: Option
    abstract override val creationDate: Instant

    /**
     * URL.
     */
    abstract val url: String

    /**
     * Описание.
     */
    abstract val description: String

    /**
     * Аккаунт.
     */
    abstract val account: String

    /**
     * Дополнительные параметры.
     */
    abstract val parameters: List<Option>
}

/**
 * Запрос на загрузку файла с другого сайта по URL, без авторизации.
 */
@Serializable
@SerialName("None")
expect class NoneFileURLRequest: FileURLRequest {
    override val uid: String
    override val name: String
    override val author: Option
    override val creationDate: Instant
    override val url: String
    override val description: String
    override val account: String
    override val parameters: List<Option>
}

/**
 * Запрос на загрузку файла с другого сайта по URL, с Basic авторизацией.
 */
@Serializable
@SerialName("Headers")
expect class HeadersFileURLRequest: FileURLRequest {
    override val uid: String
    override val name: String
    override val author: Option
    override val creationDate: Instant
    override val url: String
    override val description: String
    override val account: String
    override val parameters: List<Option>

    /**
     * Заголовки.
     */
    val headers: List<Option>
}
