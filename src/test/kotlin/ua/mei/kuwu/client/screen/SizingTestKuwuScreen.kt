package ua.mei.kuwu.client.screen

import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import net.minecraft.text.ClickEvent
import ua.mei.kowo.dsl.*

class SizingTestKuwuScreen : BaseOwoScreen<FlowLayout>() {
    override fun createAdapter(): OwoUIAdapter<FlowLayout> {
        return OwoUIAdapter.create(this, ::verticalFlow)
    }

    override fun build(rootComponent: FlowLayout) {
        root {
            verticalAlignment = VerticalAlignment.CENTER
            horizontalAlignment = HorizontalAlignment.CENTER

            children {
                +stack(Sizing.content(), Sizing.content()).apply {
                    padding {
                        all(15)
                    }
                    horizontalAlignment = HorizontalAlignment.CENTER
                    surface = Surface.panelWithInset(6)

                    val animation: Animation<Sizing> = horizontalSizing().animate(500, Easing.CUBIC, 75.fill)

                    children {
                        +button("initialize sizenite".literal).apply {
                            horizontalSizing = 50.fill

                            onPress {
                                animation.reverse()
                            }
                        }
                    }
                }
                +label("bruh".literal.styled { it.withClickEvent(ClickEvent(ClickEvent.Action.OPEN_URL, "https://wispforest.io")) })
            }
        }
    }
}
