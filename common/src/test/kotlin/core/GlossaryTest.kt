package core

import kotlinx.serialization.json.JSON
import kotlinx.serialization.list
import serializer.TerminologySerializer

import kotlin.test.*

class GlossaryTest {
    @Test
    fun init() {
        val glossary = Glossary()
        assertTrue(true)
    }

    @Test
    fun registerContext() {
        val glossary = Glossary()
        glossary.registerContext(Context("context1"))
        assertTrue(true)
        assertFails {
            glossary.registerContext(Context("context1")).registerContext(Context("context1"))
        }
    }

    @Test
    fun checkoutContext() {
        val glossary = Glossary()

        assertFails {
            glossary.checkoutContext(Context("context1"))
        }

        glossary.registerContext(Context("context1")).checkoutContext(Context("context1"))
        assertTrue(true)
    }

    @Test
    fun registerTerminology() {
        val glossary = Glossary()
        glossary.registerContext(Context("context1"))
                .checkoutContext(Context("context1"))
                .registerTerminology(Terminology(Keyword("word"), Meaning("meaning")))
        assertTrue(true)
    }

    @Test
    fun updateTerminology() {
        val glossary = Glossary()
        val registeredGlossary = glossary.registerContext(Context("context1"))
                .checkoutContext(Context("context1"))
                .registerTerminology(Terminology(Keyword("word"), Meaning("meaning")))
        val before = registeredGlossary.findByKeyword(Keyword("word"))
        val after = registeredGlossary.updateTerminology(Terminology(Keyword("word"), Meaning("meaning2")))
                .findByKeyword(Keyword("word"))

        assertNotEquals(Meaning("meaning2"), before?.meaning)
        assertEquals(Meaning("meaning2"), after?.meaning)
    }

    @Test
    fun updateContext() {
        val glossary = Glossary()
                .registerContext(Context("context1"))
                .checkoutContext(Context("context1"))
                .registerTerminology(Terminology(Keyword("word"), Meaning("meaning")))

        val before = glossary.terminologyList()
        assertEquals(true, true)
    }
}