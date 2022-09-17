package io.github.onotoliy.ofiles.data

import io.github.onotoliy.core.data.HasAuthor
import io.github.onotoliy.core.data.HasCreationDate
import io.github.onotoliy.core.data.HasName
import io.github.onotoliy.core.data.HasUID
import io.github.onotoliy.core.data.Option
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

/**
 * Meta-данные файла.
 *
 * @param uid Уникальный идентификатор.
 * @param account Аккаунт.
 * @param author Автор.
 * @param contentType ContentType.
 * @param description Описание.
 * @param extension Расширение.
 * @param name Название.
 * @param originalName Оригинальное название.
 * @param size Размер.
 * @param creationDate Дата создания.
 * @param parameters Дополнительные параметры.
 * @author Anatoliy Pokhresniy
 */
data class FileMetadata(
    override val uid: String = "",
    override val author: Option = Option(),
    override val creationDate: Instant = Clock.System.now(),
    override val name: String = "",
    val originalName: String = "",
    val description: String = "",
    val account: String = "",
    val extension: String = "",
    val contentType: String = "",
    val size: Long = 0,
    val parameters: List<Option> = listOf()
): HasUID, HasName, HasAuthor, HasCreationDate
