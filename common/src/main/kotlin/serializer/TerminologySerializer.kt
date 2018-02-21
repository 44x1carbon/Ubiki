package serializer

import core.Terminology
import kotlinx.serialization.*
import kotlinx.serialization.internal.SerialClassDescImpl

@Serializer(forClass = Terminology::class)
object TerminologySerializer: KSerializer<Terminology> {
    override val serialClassDesc: KSerialClassDesc
        get() = SerialClassDescImpl("core.Terminology")

    override fun load(input: KInput): Terminology {
        return input.read<Terminology>()
    }

    override fun save(output: KOutput, obj: Terminology) {
        output.write(obj)
    }
}