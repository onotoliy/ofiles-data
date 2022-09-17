package io.github.onotoliy.ofiles.data

import io.github.onotoliy.core.data.HasAuthor
import io.github.onotoliy.core.data.HasCreationDate
import io.github.onotoliy.core.data.HasName
import io.github.onotoliy.core.data.HasUID
import io.github.onotoliy.core.data.Option
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

/**
 * Запрос на загрузку файла в формате Base64.
 *
 * @param uid Уникальный идентификатор.
 * @param account Аккаунт.
 * @param author Автор.
 * @param description Описание.
 * @param name Название.
 * @param creationDate Дата создания.
 * @param parameters Дополнительные параметры.
 * @param content Содержимое файла в формате Base64.
 * @author Anatoliy Pokhresniy
 */
data class FileBase64Request(
    override val uid: String = "",
    override val author: Option = Option(),
    override val creationDate: Instant = Clock.System.now(),
    override val name: String = "",
    val description: String = "",
    val account: String = "",
    val parameters: List<Option> = listOf(),
    val content: String = ""
): HasUID, HasName, HasAuthor, HasCreationDate
