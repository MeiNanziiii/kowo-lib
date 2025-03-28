package ua.mei.kowo.dsl

import io.wispforest.owo.ui.component.*
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.Component
import io.wispforest.owo.ui.core.Sizing
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.client.gui.widget.ClickableWidget
import net.minecraft.client.texture.Sprite
import net.minecraft.client.util.SpriteIdentifier
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Supplier

// -----------------------
// Wrapped Vanilla Widgets
// -----------------------

fun button(message: Text, onPress: (ButtonComponent) -> Unit): ButtonComponent {
    return Components.button(message, onPress)
}

fun button(message: Text): ButtonComponent {
    return button(message) {}
}

fun textBox(horizontalSizing: Sizing): TextBoxComponent {
    return Components.textBox(horizontalSizing)
}

fun textBox(horizontalSizing: Sizing, text: String): TextBoxComponent {
    return textBox(horizontalSizing).apply {
        setText(text)
    }
}

fun textArea(horizontalSizing: Sizing, verticalSizing: Sizing): TextAreaComponent {
    return Components.textArea(horizontalSizing, verticalSizing)
}

fun textArea(horizontalSizing: Sizing, verticalSizing: Sizing, text: String): TextAreaComponent {
    return textArea(horizontalSizing, verticalSizing).apply {
        setText(text)
    }
}

// ------------------
// Default Components
// ------------------

fun <E : Entity> entity(sizing: Sizing, type: EntityType<E>, nbt: NbtCompound?): EntityComponent<E> {
    return Components.entity(sizing, type, nbt)
}

fun <E : Entity> entity(sizing: Sizing, entity: E): EntityComponent<E> {
    return Components.entity(sizing, entity)
}

fun item(item: ItemStack): ItemComponent {
    return Components.item(item)
}

fun block(state: BlockState): BlockComponent {
    return Components.block(state)
}

fun block(state: BlockState, entity: BlockEntity): BlockComponent {
    return Components.block(state, entity)
}

fun block(state: BlockState, nbt: NbtCompound?): BlockComponent {
    return Components.block(state, nbt)
}

fun block(block: Block): BlockComponent {
    return Components.block(block.defaultState)
}

fun label(text: Text): LabelComponent {
    return Components.label(text)
}

fun checkbox(message: Text): CheckboxComponent {
    return Components.checkbox(message)
}

fun slider(horizontalSizing: Sizing): SliderComponent {
    return Components.slider(horizontalSizing)
}

fun discreteSlider(horizontalSizing: Sizing, min: Double, max: Double): DiscreteSliderComponent {
    return Components.discreteSlider(horizontalSizing, min, max)
}

fun sprite(spriteId: SpriteIdentifier): SpriteComponent {
    return Components.sprite(spriteId)
}

fun sprite(sprite: Sprite): SpriteComponent {
    return Components.sprite(sprite)
}

fun texture(texture: Identifier, u: Int, v: Int, regionWidth: Int, regionHeight: Int, textureWidth: Int, textureHeight: Int): TextureComponent {
    return Components.texture(texture, u, v, regionWidth, regionHeight, textureWidth, textureHeight)
}

fun texture(texture: Identifier, u: Int, v: Int, regionWidth: Int, regionHeight: Int): TextureComponent {
    return texture(texture, u, v, regionWidth, regionHeight, 256, 256)
}

fun box(horizontalSizing: Sizing, verticalSizing: Sizing): BoxComponent {
    return Components.box(horizontalSizing, verticalSizing)
}

fun dropdown(horizontalSizing: Sizing): DropdownComponent {
    return Components.dropdown(horizontalSizing)
}

fun slimSlider(axis: SlimSliderComponent.Axis): SlimSliderComponent {
    return Components.slimSlider(axis)
}

fun smallCheckbox(label: Text): SmallCheckboxComponent {
    return Components.smallCheckbox(label)
}

fun spacer(percent: Int): SpacerComponent {
    return Components.spacer(percent)
}

fun spacer(): SpacerComponent {
    return spacer(100)
}

// -------
// Utility
// -------

fun <T, C : Component> list(data: List<T>, layoutConfigurator: Consumer<FlowLayout>, componentMaker: Function<T, C>, vertical: Boolean): FlowLayout {
    return Components.list(data, layoutConfigurator, componentMaker, vertical)
}

fun wrapVanillaWidget(widget: ClickableWidget): VanillaWidgetComponent {
    return Components.wrapVanillaWidget(widget)
}

fun <T : Component> createWithSizing(componentMaker: Supplier<T>, horizontalSizing: Sizing, verticalSizing: Sizing): T {
    return Components.createWithSizing(componentMaker, horizontalSizing, verticalSizing)
}
