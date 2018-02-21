package view

class NewLineText(val text: String): CliView {
    override fun render() {
        println(text)
    }
}