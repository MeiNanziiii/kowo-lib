package ua.mei.kowo.dsl

import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.component.LabelComponent
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.container.StackLayout
import io.wispforest.owo.ui.core.*
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import ua.mei.kowo.mixin.BaseOwoScreenAccessor

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

fun <R : ParentComponent> BaseOwoScreen<R>.root(builder: R.() -> Unit) {
    (this as BaseOwoScreenAccessor).getUiAdapter<R>().rootComponent.builder()
}

var ParentComponent.surface: Surface
    get() = this.surface()
    set(value) {
        this.surface(value)
    }

var ParentComponent.verticalAlignment: VerticalAlignment
    get() = this.verticalAlignment()
    set(value) {
        this.verticalAlignment(value)
    }

var ParentComponent.horizontalAlignment: HorizontalAlignment
    get() = this.horizontalAlignment()
    set(value) {
        this.horizontalAlignment(value)
    }

var FlowLayout.gap: Int
    get() = this.gap()
    set(value) {
        this.gap(value)
    }

var LabelComponent.shadow: Boolean
    get() = this.shadow()
    set(value) {
        this.shadow(value)
    }

class InsetsBuilder {
    var top: Int = 0
    var bottom: Int = 0
    var left: Int = 0
    var right: Int = 0

    fun horizontal(value: Int) {
        left = value
        right = value
    }

    fun vertical(value: Int) {
        top = value
        bottom = value
    }

    fun all(value: Int) {
        top = value
        bottom = value
        left = value
        right = value
    }

    fun build(): Insets {
        return Insets.of(top, bottom, left, right)
    }

    companion object {
        operator fun invoke(builder: InsetsBuilder.() -> Unit): Insets {
            return InsetsBuilder().apply(builder).build()
        }
    }
}

fun Component.margin(builder: InsetsBuilder.() -> Unit) {
    this.margins(InsetsBuilder().apply(builder).build())
}

fun ParentComponent.padding(builder: InsetsBuilder.() -> Unit) {
    this.padding(InsetsBuilder().apply(builder).build())
}

// ---------
// Minecraft
// ---------

val String.literal: MutableText
    get() = Text.literal(this)

val String.translatable: MutableText
    get() = Text.translatable(this)
