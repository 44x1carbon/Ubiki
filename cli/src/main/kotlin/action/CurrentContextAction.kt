package action

import repository.FileRepository
import view.CliView
import view.NewLineText

object CurrentContextAction: Action() {
    override val requiredInit: Boolean = true

    override fun action(args: dynamic): List<CliView> {
        val glossary = FileRepository.load()

        return listOf(
            NewLineText(glossary.designatedContext().name)
        )
    }
}