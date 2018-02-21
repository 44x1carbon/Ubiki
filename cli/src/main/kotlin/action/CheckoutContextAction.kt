package action

import core.Context
import repository.FileRepository
import view.CliView

object CheckoutContextAction: Action() {
    override val requiredInit: Boolean  = true

    override fun action(args: dynamic): List<CliView> {
        val glossary = FileRepository.load()
        glossary.checkoutContext(Context(args.name as String)).let {
            FileRepository.save(it)
        }

        return listOf()
    }
}