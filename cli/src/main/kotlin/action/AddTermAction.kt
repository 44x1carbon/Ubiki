package action

import core.Keyword
import core.Meaning
import core.Terminology
import repository.FileRepository
import view.CliView

object AddTermAction : Action() {
    override val requiredInit: Boolean = true

    override fun action(args: dynamic): List<CliView> {
        val glossary = FileRepository.load()
        val keyword = Keyword(args.word)
        val meaning = Meaning(args.meaning)
        glossary.registerTerminology(Terminology(keyword, meaning)).let {
            FileRepository.save(it)
        }
        return listOf()
    }
}