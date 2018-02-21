package action

import core.Context
import repository.FileRepository
import view.CliView
import view.NewLineText

object AddContextAction: Action(){
    override val requiredInit: Boolean = true

    override fun action(args: dynamic): List<CliView> {
        val name = args.name as String
        val glossary = FileRepository.load()
        glossary.registerContext(Context(name)).let {
            FileRepository.save(it)
        }
        return listOf(
            NewLineText("add $name context")
        )
    }
}