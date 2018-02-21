package core

import kotlinx.serialization.Serializable
import utility.generateHashCode

@Serializable
class Keyword(val word: String) {
    override fun equals(other: Any?): Boolean
            = other.takeIf { other is Keyword }?.let { _other -> hashCode() == _other.hashCode() } ?: false
    override fun hashCode(): Int = generateHashCode(word)
}