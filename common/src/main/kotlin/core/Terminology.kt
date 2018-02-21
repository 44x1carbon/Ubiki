package core

import kotlinx.serialization.*
import kotlinx.serialization.internal.SerialClassDescImpl
import utility.generateHashCode

@Serializable
class Terminology(val keyword: Keyword, val meaning: Meaning) {
    override fun equals(other: Any?): Boolean
            = other.takeIf { other is Terminology }?.let { _other -> hashCode() == _other.hashCode() } ?: false
    override fun hashCode(): Int = generateHashCode(keyword)
}