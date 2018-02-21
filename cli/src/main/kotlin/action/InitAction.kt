package action

import core.Glossary
import repository.FileRepository
import view.CliView
import view.NewLineText

object InitAction : Action() {
    override val requiredInit: Boolean = false

    override fun action( args: dynamic): List<CliView> {
        FileRepository.save(Glossary())

        return listOf(
            NewLineText("success init")
        )
    }
}