package core

import kotlinx.serialization.Serializable
import utility.generateHashCode

@Serializable
class ContextualTerms(val context: Context, val terms: Terms = Terms()) {
    fun findByKeyword(keyword: Keyword): Terminology?
        = terms.findByKeyword(keyword)
    fun includeTerms(terminology: Terminology): List<Terminology>
        = terms.terminologies.filter { terminology.meaning.inTerminology(terminology) }

    fun registerTerminology(terminology: Terminology): ContextualTerms
        = ContextualTerms(context, terms.registerTerminology(terminology))

    fun updateTerminology(terminology: Terminology): ContextualTerms
        = ContextualTerms(context, terms.updateTerminology(terminology))

    override fun equals(other: Any?): Boolean
            = other.takeIf { other is ContextualTerms }?.let { _other -> hashCode() == _other.hashCode() } ?: false
    override fun hashCode(): Int = generateHashCode(context)
}