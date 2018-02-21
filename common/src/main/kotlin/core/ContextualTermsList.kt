package core

class ContextualTermsList(val contextualTermsList: List<ContextualTerms> = listOf()) {
    val contextList: List<Context>
        get() = contextualTermsList.map { it.context }

    fun findByContext(context: Context): ContextualTerms?
        = contextualTermsList.find { it.context == context }

    fun replaceContextualTerms(contextualTerms: ContextualTerms): ContextualTermsList
            =  ContextualTermsList(exclusionContextualTerms(contextualTerms) + contextualTerms)

    private fun exclusionContextualTerms(contextualTerms: ContextualTerms): List<ContextualTerms>
        = contextualTermsList.filterNot { it.context == contextualTerms.context }

    operator fun plus(contextualTerms: ContextualTerms): ContextualTermsList
        = ContextualTermsList(contextualTermsList + contextualTerms)
}

