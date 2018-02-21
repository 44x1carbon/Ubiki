package action

import view.CliView

val fs = utile.require("fs")

abstract class Action {
    abstract val requiredInit: Boolean
    private fun before() {
        if(requiredInit && !isInit) throw RuntimeException("not init")
    }

    private fun after(renderList: List<CliView>) {
        renderList.forEach{ it.render() }
    }

    protected abstract fun action(args: dynamic): List<CliView>

    fun invoke(args: dynamic) {
        before()
        val renderList = action(args)
        after(renderList)
    }

    companion object {
        val isInit: Boolean
            get() = fs.existsSync(Config.storeFilePath) as Boolean

        fun NoBuilder(yargs: dynamic) = yargs
    }
}