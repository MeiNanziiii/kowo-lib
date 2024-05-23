package ua.mei.kowo.ops

import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Formatting


fun String.literal(): MutableText { return Text.literal(this) }
fun String.translatable(): MutableText { return Text.translatable(this) }

infix fun String.colored(color: Int): MutableText { return this.literal().styled { it.withColor(color) } }
infix fun String.formatting(formatting: Formatting): MutableText { return this.literal().formatted(formatting) }

infix fun MutableText.colored(color: Int): MutableText { return this.styled { it.withColor(color) } }
infix fun MutableText.formatting(formatting: Formatting): MutableText { return this.formatted(formatting) }

operator fun Text.plus(other: MutableText): MutableText { return Text.empty().append(this).append(other) }