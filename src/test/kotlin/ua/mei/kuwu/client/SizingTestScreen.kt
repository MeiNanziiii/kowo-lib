package ua.mei.kuwu.client

import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import net.minecraft.text.ClickEvent
import net.minecraft.text.Style
import ua.mei.kowo.ops.*
import ua.mei.kowo.ui.base.BaseKowoScreen

class SizingTestScreen : BaseKowoScreen<FlowLayout>() {
    override fun createRoot(horizontalSizing: Sizing, verticalSizing: Sizing): FlowLayout {
        return Containers.verticalFlow(horizontalSizing, verticalSizing)
    }

    override fun build() {
        root {
            stackLayout {
                horizontalAlignment(HorizontalAlignment.CENTER)
                surface(Surface.panelWithInset(6))
                padding(Insets.of(15))

                val animation = horizontalSizing().animate(500, Easing.CUBIC, Sizing.fill(75))

                button {
                    message = "initialize sizenite".literal()

                    horizontalSizing(Sizing.fill(50))
                    onPress {
                        animation.reverse()
                    }
                }
            }
            label {
                text("bruh".literal().setStyle(Style.EMPTY.withClickEvent(ClickEvent(ClickEvent.Action.OPEN_URL, "https://wispforest.io"))))
            }
            horizontalAlignment(HorizontalAlignment.CENTER)
            verticalAlignment(VerticalAlignment.CENTER)
        }
    }
}