@file:Suppress("UNUSED")

package xyz.bonfiremc.kowo.dsl

import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.component.*
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.container.GridLayout
import io.wispforest.owo.ui.container.StackLayout
import io.wispforest.owo.ui.core.*
import net.minecraft.client.gui.tooltip.TooltipComponent
import net.minecraft.entity.Entity
import net.minecraft.item.ItemStack
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import xyz.bonfiremc.kowo.mixin.BaseOwoScreenAccessor
import xyz.bonfiremc.kowo.mixin.GridLayoutAccessor

// -------
// oωo-lib
// -------

@Deprecated("Directly use unaryPlus in apply block")
class ChildrenBuilder() {
    val children: MutableList<Component> = mutableListOf()

    operator fun <C : Component> C.unaryPlus(): C {
        children.add(this)
        return this
    }
}

@Deprecated("Directly use unaryPlus in apply block")
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

@Deprecated("Directly use unaryPlus in apply block")
fun FlowLayout.children(builder: ChildrenBuilder.() -> Unit) {
    children(ChildrenBuilder().apply(builder).children)
}

@Deprecated("Directly use unaryPlus in apply block")
fun StackLayout.children(builder: ChildrenBuilder.() -> Unit) {
    children(ChildrenBuilder().apply(builder).children)
}

private fun ParentComponent.tryChild(component: Component) {
    when (this) {
        is FlowLayout -> this.child(component)
        is StackLayout -> this.child(component)
        is GridLayout -> {
            this as GridLayoutAccessor

            val size: Int = this.children.size
            val row: Int = size / this.rows - 1
            val col: Int = size % this.columns - 1

            this.child(component, row, col)
        }
    }
}

context(parent: ParentComponent)
operator fun <C : Component> C.unaryPlus(): C {
    parent.tryChild(this)
    return this
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

inline fun <reified T : Component> ParentComponent.childById(id: String): T {
    return this.childById(T::class.java, id)
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

var Component.positioning: Positioning
    get() = this.positioning().get()
    set(value) {
        this.positioning(value)
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

val Component.parent: ParentComponent?
    get() = this.parent()

val Component.root: ParentComponent?
    get() = this.root()

var FlowLayout.gap: Int
    get() = this.gap()
    set(value) {
        this.gap(value)
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

var ButtonComponent.textShadow: Boolean
    get() = this.textShadow()
    set(value) {
        this.textShadow(value)
    }

var TextAreaComponent.maxLines: Int
    get() = this.maxLines()
    set(value) {
        this.maxLines(value)
    }

var TextAreaComponent.displayCharCount: Boolean
    get() = this.displayCharCount()
    set(value) {
        this.displayCharCount(value)
    }

var <E : Entity> EntityComponent<E>.allowMouseRotation: Boolean
    get() = this.allowMouseRotation()
    set(value) {
        this.allowMouseRotation(value)
    }

var <E : Entity> EntityComponent<E>.lookAtCursor: Boolean
    get() = this.lookAtCursor()
    set(value) {
        this.lookAtCursor(value)
    }

var <E : Entity> EntityComponent<E>.scale: Float
    get() = this.scale()
    set(value) {
        this.scale(value)
    }

var <E : Entity> EntityComponent<E>.scaleToFit: Boolean
    get() = this.scaleToFit()
    set(value) {
        this.scaleToFit(value)
    }

var <E : Entity> EntityComponent<E>.showNametag: Boolean
    get() = this.showNametag()
    set(value) {
        this.showNametag(value)
    }

var ItemComponent.setTooltipFromStack: Boolean
    get() = this.setTooltipFromStack()
    set(value) {
        this.setTooltipFromStack(value)
    }

var ItemComponent.stack: ItemStack
    get() = this.stack()
    set(value) {
        this.stack(value)
    }

var ItemComponent.showOverlay: Boolean
    get() = this.showOverlay()
    set(value) {
        this.showOverlay(value)
    }

var LabelComponent.text: Text
    get() = this.text()
    set(value) {
        this.text(value)
    }

var LabelComponent.maxWidth: Int
    get() = this.maxWidth()
    set(value) {
        this.maxWidth(value)
    }

var LabelComponent.shadow: Boolean
    get() = this.shadow()
    set(value) {
        this.shadow(value)
    }

var LabelComponent.color: Color
    get() = this.color().get()
    set(value) {
        this.color(value)
    }

var LabelComponent.verticalTextAlignment: VerticalAlignment
    get() = this.verticalTextAlignment()
    set(value) {
        this.verticalTextAlignment(value)
    }

var LabelComponent.horizontalTextAlignment: HorizontalAlignment
    get() = this.horizontalTextAlignment()
    set(value) {
        this.horizontalTextAlignment(value)
    }

var LabelComponent.lineHeight: Int
    get() = this.lineHeight()
    set(value) {
        this.lineHeight(value)
    }

var LabelComponent.lineSpacing: Int
    get() = this.lineSpacing()
    set(value) {
        this.lineSpacing(value)
    }

var CheckboxComponent.checked: Boolean
    get() = this.isChecked
    set(value) {
        this.checked(value)
    }

var SliderComponent.value: Double
    get() = this.value()
    set(value) {
        this.value(value)
    }

var SliderComponent.scrollStep: Double
    get() = this.scrollStep()
    set(value) {
        this.scrollStep(value)
    }

var DiscreteSliderComponent.snap: Boolean
    get() = this.snap()
    set(value) {
        this.snap(value)
    }

var SpriteComponent.blend: Boolean
    get() = this.blend()
    set(value) {
        this.blend(value)
    }

var TextureComponent.visibleArea: PositionedRectangle
    get() = this.visibleArea().get()
    set(value) {
        this.visibleArea(value)
    }

var TextureComponent.blend: Boolean
    get() = this.blend()
    set(value) {
        this.blend(value)
    }

var BoxComponent.fill: Boolean
    get() = this.fill()
    set(value) {
        this.fill(value)
    }

var BoxComponent.direction: BoxComponent.GradientDirection
    get() = this.direction()
    set(value) {
        this.direction(value)
    }

var BoxComponent.color: Color
    get() = throw UnsupportedOperationException()
    set(value) {
        this.color(value)
    }

var BoxComponent.startColor: Color
    get() = this.startColor().get()
    set(value) {
        this.startColor(value)
    }

var BoxComponent.endColor: Color
    get() = this.endColor().get()
    set(value) {
        this.endColor(value)
    }

var SlimSliderComponent.value: Double
    get() = this.value()
    set(value) {
        this.value(value)
    }

var SlimSliderComponent.min: Double
    get() = this.min()
    set(value) {
        this.min(value)
    }

var SlimSliderComponent.max: Double
    get() = this.max()
    set(value) {
        this.max(value)
    }

var SlimSliderComponent.stepSize: Double
    get() = this.stepSize()
    set(value) {
        this.stepSize(value)
    }

var SmallCheckboxComponent.checked: Boolean
    get() = this.checked()
    set(value) {
        this.checked(value)
    }

var SmallCheckboxComponent.label: Text
    get() = this.label()
    set(value) {
        this.label(value)
    }

var SmallCheckboxComponent.labelShadow: Boolean
    get() = this.labelShadow()
    set(value) {
        this.labelShadow(value)
    }

// ---------
// Minecraft
// ---------

val String.literal: MutableText
    get() = Text.literal(this)

val String.translatable: MutableText
    get() = Text.translatable(this)
