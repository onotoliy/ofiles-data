package io.github.onotoliy.ofiles.data

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import io.github.onotoliy.core.data.HasAuthor
import io.github.onotoliy.core.data.HasCreationDate
import io.github.onotoliy.core.data.HasName
import io.github.onotoliy.core.data.HasUID
import io.github.onotoliy.core.data.Option
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
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
    Type(HeadersFileURLRequest::class, name = "Headers")
)
@JsonInclude(Include.NON_EMPTY)
@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("discriminator")
actual sealed class FileURLRequest: HasUID, HasName, HasAuthor, HasCreationDate {
    actual abstract override val uid: String
    actual abstract override val name: String
    actual abstract override val author: Option
    actual abstract override val creationDate: Instant

    /**
     * URL.
     */
    actual abstract val url: String

    /**
     * Описание.
     */
    actual abstract val description: String

    /**
     * Аккаунт.
     */
    actual abstract val account: String

    /**
     * Дополнительные параметры.
     */
    actual abstract val parameters: List<Option>
}

/**
 * Запрос на загрузку файла с другого сайта по URL, без авторизации.
 */
@Serializable
@SerialName("None")
actual data class NoneFileURLRequest(
    actual override val uid: String = "",
    actual override val name: String = "",
    actual override val author: Option = Option(),
    actual override val creationDate: Instant = Clock.System.now(),
    actual override val url: String = "",
    actual override val description: String = "",
    actual override val account: String = "",
    actual override val parameters: List<Option> = listOf()
): FileURLRequest()

/**
 * Запрос на загрузку файла с другого сайта по URL, с Basic авторизацией.
 */
@Serializable
@SerialName("Headers")
actual class HeadersFileURLRequest(
    actual override val uid: String = "",
    actual override val name: String = "",
    actual override val author: Option = Option(),
    actual override val creationDate: Instant = Clock.System.now(),
    actual override val url: String = "",
    actual override val description: String = "",
    actual override val account: String = "",
    actual override val parameters: List<Option> = listOf(),

    /**
     * Заголовки.
     */
    actual val headers: List<Option> = listOf()
) : FileURLRequest()
