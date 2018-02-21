package view

import kotlin.js.Json
import kotlin.js.json

@JsModule("cli-table2")
external class Table(options: Json)

class TableView(val header: List<String>, val colWidths: List<Int>, val data: List<List<String>>): CliView {
    override fun render() {
        val table: dynamic = Table(json(
            "head" to ArrayList(header).toTypedArray(),
            "colWidths" to ArrayList(colWidths).toTypedArray(),
            "wordWrap" to true
        ))

        data.forEach {
            val array = arrayOf<String>()
            it.forEachIndexed { index, value -> array[index] = value.chunked(colWidths[index]).joinToString(" ")}
            table.push(array)
        }

        console.log(table.toString())
    }
}