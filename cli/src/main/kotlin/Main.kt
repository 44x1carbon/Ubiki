import action.*
import action.Action.Companion.NoBuilder
import utile.require

val yargs = require("yargs")
// hoge
fun main(args: Array<String>) {
    yargs.command("init", "", ::NoBuilder, InitAction::invoke)

    yargs.command("term", "") { yargs ->
        yargs.command("add <word> <meaning>", "add term", ::NoBuilder, AddTermAction::invoke)
        yargs.command("edit <target> <newname>", "list term")
                    .option("m", js("{ alias: 'meaning', type: 'string', describe: 'Term Meaning', }"))
                    .option("w", js("{ alias: 'word', type: 'string', describe: 'Term keyword', }"))
        yargs.command("list", "list term", ::NoBuilder, ListTermAction::invoke)
        yargs.command("search", "list term")
        yargs
    }

    yargs.command("context", "" ,{ yargs ->
        yargs.command("checkout <name>", "add term", ::NoBuilder, CheckoutContextAction::invoke)
        yargs.command("add <name>", "add term", ::NoBuilder, AddContextAction::invoke)
        yargs.command("rename", "list term")
        yargs.command("remove", "list term")
        yargs
    }, CurrentContextAction::invoke)

    yargs.argv
}