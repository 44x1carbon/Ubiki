package core

import core.exception.CoreException
import kotlinx.serialization.Serializable

@Serializable
class Terms(val terminologies: List<Terminology> = listOf()) {
    fun findByKeyword(keyword: Keyword): Terminology?
        = terminologies.find { it.keyword == keyword }
    fun registerTerminology(terminology: Terminology): Terms
        = takeUnless { isExistTerminology(terminology) }?.let { Terms(terminologies + terminology) } ?: throw CoreException("")
    fun updateTerminology(terminology: Terminology): Terms
        = takeIf { isExistTerminology(terminology) }?.let { Terms(exclusionTerminology(terminology) + terminology) } ?: throw CoreException("")

    fun isExistTerminology(terminology: Terminology): Boolean = terminologies.contains(terminology)
    private fun exclusionTerminology(terminology: Terminology): List<Terminology> = terminologies.filterNot { it.keyword == terminology.keyword }
}