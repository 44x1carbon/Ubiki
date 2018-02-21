package core

import kotlinx.serialization.Serializable
import utility.generateHashCode

@Serializable
class Meaning(val text: String) {
    fun inTerminology(terminology: Terminology): Boolean
        = text.contains(terminology.keyword.word)

    override fun equals(other: Any?): Boolean
            = other.takeIf { other is Meaning }?.let { _other -> hashCode() == _other.hashCode() } ?: false
    override fun hashCode(): Int = generateHashCode(text)
}