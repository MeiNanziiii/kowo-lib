package ua.mei.kowo.dsl

import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.container.StackLayout
import io.wispforest.owo.ui.core.Component
import io.wispforest.owo.ui.core.ParentComponent
import io.wispforest.owo.ui.core.Sizing
import net.minecraft.text.MutableText
import net.minecraft.text.Text

// -------
// oωo-lib
// -------

class ChildrenHandler() {
    val children: MutableList<Component> = mutableListOf()

    operator fun <C : Component> C.unaryPlus(): C {
        children.add(this)
        return this
    }
}

fun ParentComponent.children(builder: ChildrenHandler.() -> Unit) {
    ChildrenHandler().apply(builder).children.forEach(this::tryChild)
}

fun ParentComponent.tryChild(child: Component) {
    if (this is FlowLayout) {
        this.child(child)
    } else if (this is StackLayout) {
        this.child(child)
    } else {
        throw UnsupportedOperationException("This type of layout does not support adding a child: ${this::class.simpleName}")
    }
}

val Int.fixed: Sizing
    get() = Sizing.fixed(this)

val Int.content: Sizing
    get() = Sizing.content(this)

val Int.fill: Sizing
    get() = Sizing.fill(this)

val Int.expand: Sizing
    get() = Sizing.expand(this)

// ---------
// Minecraft
// ---------

val String.literal: MutableText
    get() = Text.literal(this)

val String.translatable: MutableText
    get() = Text.translatable(this)
