package xyz.bonfiremc.kuwu.client.screen

import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import net.minecraft.text.ClickEvent
import xyz.bonfiremc.kowo.dsl.button
import xyz.bonfiremc.kowo.dsl.fill
import xyz.bonfiremc.kowo.dsl.horizontalAlignment
import xyz.bonfiremc.kowo.dsl.horizontalSizing
import xyz.bonfiremc.kowo.dsl.label
import xyz.bonfiremc.kowo.dsl.literal
import xyz.bonfiremc.kowo.dsl.padding
import xyz.bonfiremc.kowo.dsl.root
import xyz.bonfiremc.kowo.dsl.stack
import xyz.bonfiremc.kowo.dsl.surface
import xyz.bonfiremc.kowo.dsl.unaryPlus
import xyz.bonfiremc.kowo.dsl.verticalAlignment
import xyz.bonfiremc.kowo.dsl.verticalFlow
import java.net.URI

class SizingTestKuwuScreen : BaseOwoScreen<FlowLayout>() {
    override fun createAdapter(): OwoUIAdapter<FlowLayout> {
        return OwoUIAdapter.create(this, ::verticalFlow)
    }

    override fun build(rootComponent: FlowLayout) {
        root {
            verticalAlignment = VerticalAlignment.CENTER
            horizontalAlignment = HorizontalAlignment.CENTER

            +stack(Sizing.content(), Sizing.content()).apply {
                padding {
                    all(15)
                }
                horizontalAlignment = HorizontalAlignment.CENTER
                surface = Surface.panelWithInset(6)

                val animation: Animation<Sizing> = horizontalSizing().animate(500, Easing.CUBIC, 75.fill)

                +button("initialize sizenite".literal).apply {
                    horizontalSizing = 50.fill

                    onPress {
                        animation.reverse()
                    }
                }
            }

            +label("bruh".literal.styled { it.withClickEvent(ClickEvent.OpenUrl(URI.create("https://wispforest.io"))) })
        }
    }
}
