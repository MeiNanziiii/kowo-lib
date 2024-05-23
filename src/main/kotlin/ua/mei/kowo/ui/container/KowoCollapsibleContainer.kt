package ua.mei.kowo.ui.container

import io.wispforest.owo.ui.component.LabelComponent
import io.wispforest.owo.ui.container.CollapsibleContainer
import io.wispforest.owo.ui.core.Sizing
import net.minecraft.text.Text
import ua.mei.kowo.mixin.FlowLayoutAccessor

class KowoCollapsibleContainer : CollapsibleContainer(Sizing.content(), Sizing.content(), Text.empty(), false) {
    fun title(): Text {
        return ((titleLayout as FlowLayoutAccessor).children[0] as LabelComponent).text()
    }
    fun title(title: Text) {
        ((titleLayout as FlowLayoutAccessor).children[0] as LabelComponent).text(title)
    }

    fun expanded(expanded: Boolean) {
        this.expanded = expanded
    }
}