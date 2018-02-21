package core

import core.exception.CoreException
import kotlinx.serialization.Serializable

@Serializable
class Glossary(val contextualTermsList: List<ContextualTerms> = listOf(), private val _designatedContext: Context? = null) {
    init {
        if(_designatedContext != null) {
            if(!isRegisteredContext(_designatedContext)) throw CoreException("")
        }
    }

    val contextList: List<Context>
        get() = contextualTermsList.map { it.context }
    fun designatedContextualTerms(): ContextualTerms
        = contextualTermsList.find { it.context == designatedContext() } ?: throw CoreException("")

    fun designatedContext(): Context
        = _designatedContext ?: throw CoreException("Context is not selected")

    fun checkoutContext(context: Context): Glossary
        = Glossary(contextualTermsList, context)

    fun registerTerminology(terminology: Terminology): Glossary {
        if(designatedContextualTerms().terms.isExistTerminology(terminology)) throw CoreException("")
        return Glossary(
            replaceContextualTermsList(designatedContextualTerms().registerTerminology(terminology)),
            _designatedContext
        )
    }

    fun updateTerminology(terminology: Terminology): Glossary {
        if(!designatedContextualTerms().terms.isExistTerminology(terminology)) throw CoreException("")
        return Glossary(
            replaceContextualTermsList(designatedContextualTerms().updateTerminology(terminology)),
            _designatedContext
        )
    }

    fun registerContext(addedContext: Context): Glossary {
        if(isRegisteredContext(addedContext)) throw CoreException("")
        return Glossary(contextualTermsList + ContextualTerms(addedContext), _designatedContext)
    }

    fun updateContext(from: Context, to: Context): Glossary {
        if(isRegisteredContext(to)) throw CoreException("")
        val fromContextualTerms = contextualTermsList.find { it.context == from } ?: throw CoreException("")
        val toContextualTerms = ContextualTerms(to, fromContextualTerms.terms)

        return Glossary(removeContextualTermsList(fromContextualTerms) + toContextualTerms, _designatedContext)
    }

    fun terminologyList(): List<Terminology>
        = designatedContextualTerms().terms.terminologies

    fun findByKeyword(keyword: Keyword): Terminology?
        = designatedContextualTerms().terms.findByKeyword(keyword)

    private fun replaceContextualTermsList(contextualTerms: ContextualTerms): List<ContextualTerms>
            = removeContextualTermsList(contextualTerms) + contextualTerms

    private fun removeContextualTermsList(contextualTerms: ContextualTerms): List<ContextualTerms>
        = contextualTermsList.filterNot { it == contextualTerms }

    private fun isRegisteredContext(context: Context): Boolean
        = contextList.contains(context)
}