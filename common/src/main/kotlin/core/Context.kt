package core

import kotlinx.serialization.Serializable
import utility.generateHashCode

@Serializable
class Context(val name: String) {
    override fun equals(other: Any?): Boolean
        = other.takeIf { other is Context }?.let { _other -> hashCode() == _other.hashCode() } ?: false
    override fun hashCode(): Int = generateHashCode(name)
    override fun toString(): String {
        return name
    }
}