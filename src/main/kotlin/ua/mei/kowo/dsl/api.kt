package ua.mei.kowo.dsl

import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.component.LabelComponent
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.container.GridLayout
import io.wispforest.owo.ui.container.StackLayout
import io.wispforest.owo.ui.core.*
import net.minecraft.client.gui.tooltip.TooltipComponent
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import ua.mei.kowo.mixin.BaseOwoScreenAccessor
import ua.mei.kowo.mixin.GridLayoutAccessor

// -------
// oωo-lib
// -------

class ChildrenBuilder() {
    val children: MutableList<Component> = mutableListOf()

    operator fun <C : Component> C.unaryPlus(): C {
        children.add(this)
        return this
    }
}

fun GridLayout.children(builder: ChildrenBuilder.() -> Unit) {
    this as GridLayoutAccessor

    var index: Int = 0

    for (child in ChildrenBuilder().apply(builder).children) {
        while (index < this.children.size && this.children[index] != null) {
            index++
        }
        if (index >= this.children.size) break

        child(child, index / this.columns, index % this.columns)
        index++
    }
}

fun FlowLayout.children(builder: ChildrenBuilder.() -> Unit) {
    children(ChildrenBuilder().apply(builder).children)
}

fun StackLayout.children(builder: ChildrenBuilder.() -> Unit) {
    children(ChildrenBuilder().apply(builder).children)
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

var ParentComponent.allowOverflow: Boolean
    get() = this.allowOverflow()
    set(value) {
        this.allowOverflow(value)
    }

var ParentComponent.positioning: Positioning
    get() = this.positioning().get()
    set(value) {
        this.positioning(value)
    }

var Component.verticalSizing: Sizing
    get() = this.verticalSizing().get()
    set(value) {
        this.verticalSizing(value)
    }

var Component.horizontalSizing: Sizing
    get() = this.horizontalSizing().get()
    set(value) {
        this.horizontalSizing(value)
    }

var Component.sizing: Sizing
    get() = throw UnsupportedOperationException()
    set(value) {
        this.sizing(value)
    }

var Component.cursorStyle: CursorStyle
    get() = this.cursorStyle()
    set(value) {
        this.cursorStyle(value)
    }

var Component.tooltip: MutableList<TooltipComponent>
    get() = this.tooltip()!!
    set(value) {
        this.tooltip(value)
    }

var Component.id: String?
    get() = this.id()
    set(value) {
        this.id(value)
    }

var Component.zIndex: Int
    get() = this.zIndex()
    set(value) {
        this.zIndex(value)
    }

var Component.x: Int
    get() = this.x()
    set(value) {
        this.updateX(value)
    }

var Component.y: Int
    get() = this.y()
    set(value) {
        this.updateY(value)
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
}

fun insetsBuilder(builder: InsetsBuilder.() -> Unit): Insets {
    return InsetsBuilder().apply(builder).build()
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
