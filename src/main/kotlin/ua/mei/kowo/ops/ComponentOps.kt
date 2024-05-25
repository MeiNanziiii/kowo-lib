package ua.mei.kowo.ops

import io.wispforest.owo.ui.component.BoxComponent
import io.wispforest.owo.ui.component.ButtonComponent
import io.wispforest.owo.ui.component.CheckboxComponent
import io.wispforest.owo.ui.component.Components
import io.wispforest.owo.ui.component.DropdownComponent
import io.wispforest.owo.ui.component.ItemComponent
import io.wispforest.owo.ui.component.LabelComponent
import io.wispforest.owo.ui.component.SliderComponent
import io.wispforest.owo.ui.component.SmallCheckboxComponent
import io.wispforest.owo.ui.component.SpacerComponent
import io.wispforest.owo.ui.component.TextAreaComponent
import io.wispforest.owo.ui.component.TextBoxComponent
import io.wispforest.owo.ui.core.Component
import io.wispforest.owo.ui.core.ParentComponent
import io.wispforest.owo.ui.core.Sizing
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.text.Text
import ua.mei.kowo.mixin.KowoEntityComponent


// -----------------------
// Wrapped Vanilla Widgets
// -----------------------

fun ParentComponent.button(init: ButtonComponent.() -> Unit): ButtonComponent {
    val button: ButtonComponent = Components.button(Text.empty()) {}
    button.init()
    this.tryChild(button)
    return button
}
fun ParentComponent.textBox(init: TextBoxComponent.() -> Unit): TextBoxComponent {
    val textBox: TextBoxComponent = Components.textBox(Sizing.content())
    textBox.init()
    this.tryChild(textBox)
    return textBox
}
fun ParentComponent.textArea(init: TextAreaComponent.() -> Unit): TextAreaComponent {
    val textArea: TextAreaComponent = Components.textArea(Sizing.content(), Sizing.content())
    textArea.init()
    this.tryChild(textArea)
    return textArea
}

// ------------------
// Default Components
// ------------------

fun ParentComponent.entity(init: KowoEntityComponent<Entity>.() -> Unit): KowoEntityComponent<Entity> {
    val entity: KowoEntityComponent<Entity> = Components.entity(Sizing.content(), EntityType.CREEPER, NbtCompound()) as KowoEntityComponent<Entity> // Ignore warning
    entity.init()
    this.tryChild(entity)
    return entity
}
fun ParentComponent.item(init: ItemComponent.() -> Unit): ItemComponent {
    val item: ItemComponent = Components.item(ItemStack.EMPTY)
    item.init()
    this.tryChild(item)
    return item
}

// TODO: BlockComponent

fun ParentComponent.label(init: LabelComponent.() -> Unit): LabelComponent {
    val label: LabelComponent = Components.label(Text.empty())
    label.init()
    this.tryChild(label)
    return label
}
fun ParentComponent.checkbox(init: CheckboxComponent.() -> Unit): CheckboxComponent {
    val checkbox: CheckboxComponent = Components.checkbox(Text.empty())
    checkbox.init()
    this.tryChild(checkbox)
    return checkbox
}
fun ParentComponent.slider(init: SliderComponent.() -> Unit): SliderComponent {
    val slider: SliderComponent = Components.slider(Sizing.content())
    slider.init()
    this.tryChild(slider)
    return slider
}

// TODO: DiscreteSliderComponent

// TODO: SpriteComponent

// TODO: TextureComponent

fun ParentComponent.box(init: BoxComponent.() -> Unit): BoxComponent {
    val box: BoxComponent = Components.box(Sizing.content(), Sizing.content())
    box.init()
    this.tryChild(box)
    return box
}
fun ParentComponent.dropdown(init: DropdownComponent.() -> Unit): DropdownComponent {
    val dropdown: DropdownComponent = Components.dropdown(Sizing.content())
    dropdown.init()
    this.tryChild(dropdown)
    return dropdown
}
fun ParentComponent.smallCheckbox(init: SmallCheckboxComponent.() -> Unit): SmallCheckboxComponent {
    val smallCheckbox: SmallCheckboxComponent = Components.smallCheckbox(Text.empty())
    smallCheckbox.init()
    this.tryChild(smallCheckbox)
    return smallCheckbox
}
fun ParentComponent.spacer(init: SpacerComponent.() -> Unit): SpacerComponent {
    val spacer: SpacerComponent = Components.spacer()
    spacer.init()
    this.tryChild(spacer)
    return spacer
}

// ----------
// Extensions
// ----------

/**
 * Does not support built-in components or components with protected constructors
 */
inline fun <reified T : Component> ParentComponent.component(vararg args: Any, noinline init: T.() -> Unit): T {
    val constructor = T::class.constructors.firstOrNull { it.parameters.size == args.size }
        ?: throw IllegalArgumentException("No constructor found with ${args.size} parameters")
    val component = constructor.call(*args)
    component.init()
    this.tryChild(component)
    return component
}

/**
 * Compatible with all components
 */
fun <T : Component> ParentComponent.component(component: T, init: T.() -> Unit): T {
    component.init()
    this.tryChild(component)
    return component
}