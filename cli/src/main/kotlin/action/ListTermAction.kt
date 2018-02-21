package action

import repository.FileRepository
import view.CliView
import view.NewLineText
import view.TableView

object ListTermAction: Action() {
    override val requiredInit: Boolean = true

    override fun action(args: dynamic): List<CliView> {
        val glossary = FileRepository.load()
        val terms = glossary.terminologyList()
        val data = terms.map { listOf(it.keyword.word, it.meaning.text) }

        return listOf(
            NewLineText("Context: " + glossary.designatedContext().name),
            TableView(listOf("Keyword", "Meaning"), listOf(20, 50),  data)
        )
    }
}