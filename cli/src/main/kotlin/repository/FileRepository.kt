package repository

import core.Glossary
import kotlinx.serialization.json.JSON
import utile.require

val fs = require("fs")

object FileRepository {
    fun save(glossary: Glossary, filePath: String = Config.storeFilePath): Boolean {
        val json = JSON.indented.stringify(glossary)
        fs.writeFileSync(filePath, json)

        return true
    }

    fun load(filePath: String = Config.storeFilePath): Glossary {
        val json = fs.readFileSync(filePath, "utf8")
        return JSON.indented.parse(json)
    }
}