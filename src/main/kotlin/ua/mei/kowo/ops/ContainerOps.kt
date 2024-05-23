package ua.mei.kowo.ops

import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.DraggableContainer
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.container.GridLayout
import io.wispforest.owo.ui.container.OverlayContainer
import io.wispforest.owo.ui.container.ScrollContainer
import io.wispforest.owo.ui.container.StackLayout
import io.wispforest.owo.ui.container.WrappingParentComponent
import io.wispforest.owo.ui.core.Component
import io.wispforest.owo.ui.core.ParentComponent
import io.wispforest.owo.ui.core.Sizing
import ua.mei.kowo.mixin.GridLayoutAccessor
import ua.mei.kowo.ui.container.KowoCollapsibleContainer


// ------
// Layout
// ------

fun ParentComponent.verticalFlow(init: FlowLayout.() -> Unit): FlowLayout {
    val layout: FlowLayout = Containers.verticalFlow(Sizing.content(), Sizing.content())
    layout.init()
    this.tryChild(layout)
    return layout
}
fun ParentComponent.horizontalFlow(init: FlowLayout.() -> Unit): FlowLayout {
    val layout: FlowLayout = Containers.horizontalFlow(Sizing.content(), Sizing.content())
    layout.init()
    this.tryChild(layout)
    return layout
}
fun ParentComponent.ltrTextFlow(init: FlowLayout.() -> Unit): FlowLayout {
    val layout: FlowLayout = Containers.ltrTextFlow(Sizing.content(), Sizing.content())
    layout.init()
    this.tryChild(layout)
    return layout
}
fun ParentComponent.stackLayout(init: StackLayout.() -> Unit): StackLayout {
    val layout: StackLayout = Containers.stack(Sizing.content(), Sizing.content())
    layout.init()
    this.tryChild(layout)
    return layout
}

// ------
// Scroll
// ------

fun ParentComponent.verticalScroll(init: ScrollContainer<Component>.() -> Unit): ScrollContainer<Component> {
    val scroll: ScrollContainer<Component> = Containers.verticalScroll(Sizing.content(), Sizing.content(), null)
    scroll.init()
    this.tryChild(scroll)
    return scroll
}
fun ParentComponent.horizontalScroll(init: ScrollContainer<Component>.() -> Unit): ScrollContainer<Component> {
    val scroll: ScrollContainer<Component> = Containers.horizontalScroll(Sizing.content(), Sizing.content(), null)
    scroll.init()
    this.tryChild(scroll)
    return scroll
}

// ----------------
// Utility wrappers
// ----------------

fun ParentComponent.draggable(init: DraggableContainer<Component>.() -> Unit): DraggableContainer<Component> {
    val draggable: DraggableContainer<Component> = Containers.draggable(Sizing.content(), Sizing.content(), null)
    draggable.init()
    this.tryChild(draggable)
    return draggable
}

fun ParentComponent.collapsible(init: KowoCollapsibleContainer.() -> Unit): KowoCollapsibleContainer {
    val collapsible: KowoCollapsibleContainer = KowoCollapsibleContainer()
    collapsible.init()
    this.tryChild(collapsible)
    return collapsible
}

fun ParentComponent.overlay(init: OverlayContainer<Component>.() -> Unit): OverlayContainer<Component> {
    val overlay: OverlayContainer<Component> = Containers.overlay(null)
    overlay.init()
    this.tryChild(overlay)
    return overlay
}

// TODO: RenderEffectWrapper

// ----------
// Extensions
// ----------

fun GridLayout.child(child: Component): GridLayout {
    val grid: GridLayoutAccessor = this as GridLayoutAccessor
    grid.children += child
    return grid as GridLayout
}
fun ParentComponent.tryChild(child: Component) {
    (this as? FlowLayout)?.child(child)
        ?: (this as? GridLayout)?.child(child)
        ?: (this as? StackLayout)?.child(child)
        ?: (this as? WrappingParentComponent<Component>)?.child(child)
        ?: throw UnsupportedOperationException("This type of layout does not support adding a child: ${this::class.simpleName}")
}