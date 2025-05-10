package xyz.bonfiremc.kowo.dsl

import io.wispforest.owo.ui.container.*
import io.wispforest.owo.ui.core.Component
import io.wispforest.owo.ui.core.Sizing
import net.minecraft.text.Text

// ------
// Layout
// ------

fun grid(horizontalSizing: Sizing, verticalSizing: Sizing, rows: Int, columns: Int): GridLayout {
    return Containers.grid(horizontalSizing, verticalSizing, rows, columns)
}

fun verticalFlow(horizontalSizing: Sizing, verticalSizing: Sizing): FlowLayout {
    return Containers.verticalFlow(horizontalSizing, verticalSizing)
}

fun horizontalFlow(horizontalSizing: Sizing, verticalSizing: Sizing): FlowLayout {
    return Containers.horizontalFlow(horizontalSizing, verticalSizing)
}

fun ltrTextFlow(horizontalSizing: Sizing, verticalSizing: Sizing): FlowLayout {
    return Containers.ltrTextFlow(horizontalSizing, verticalSizing)
}

fun stack(horizontalSizing: Sizing, verticalSizing: Sizing): StackLayout {
    return Containers.stack(horizontalSizing, verticalSizing)
}

// ------
// Scroll
// ------

fun <C : Component> verticalScroll(horizontalSizing: Sizing, verticalSizing: Sizing, child: C): ScrollContainer<C> {
    return Containers.verticalScroll(horizontalSizing, verticalSizing, child)
}

fun <C : Component> horizontalScroll(horizontalSizing: Sizing, verticalSizing: Sizing, child: C): ScrollContainer<C> {
    return Containers.horizontalScroll(horizontalSizing, verticalSizing, child)
}

// ----------------
// Utility wrappers
// ----------------

fun <C : Component> draggable(horizontalSizing: Sizing, verticalSizing: Sizing, child: C): DraggableContainer<C> {
    return Containers.draggable(horizontalSizing, verticalSizing, child)
}

fun collapsible(horizontalSizing: Sizing, verticalSizing: Sizing, title: Text, expanded: Boolean): CollapsibleContainer {
    return Containers.collapsible(horizontalSizing, verticalSizing, title, expanded)
}

fun collapsible(horizontalSizing: Sizing, verticalSizing: Sizing, title: Text): CollapsibleContainer {
    return Containers.collapsible(horizontalSizing, verticalSizing, title, false)
}

fun <C : Component> overlay(child: C): OverlayContainer<C> {
    return Containers.overlay(child)
}

fun <C : Component> renderEffect(child: C): RenderEffectWrapper<C> {
    return Containers.renderEffect(child)
}
