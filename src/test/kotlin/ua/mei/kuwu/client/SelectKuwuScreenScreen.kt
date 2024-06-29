package ua.mei.kuwu.client

import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import ua.mei.kowo.ops.button
import ua.mei.kowo.ops.label
import ua.mei.kowo.ops.literal
import ua.mei.kowo.ops.verticalFlow
import ua.mei.kowo.ui.base.BaseKowoScreen


class SelectKuwuScreenScreen : BaseKowoScreen<FlowLayout>() {
    override fun createRoot(horizontalSizing: Sizing, verticalSizing: Sizing): FlowLayout {
        return Containers.verticalFlow(horizontalSizing, verticalSizing)
    }

    override fun build() {
        root {
            surface(Surface.flat(0x77000000))
            verticalAlignment(VerticalAlignment.CENTER)
            horizontalAlignment(HorizontalAlignment.CENTER)

            label {
                text("Available screens".literal())
                shadow(true)
                margins(Insets.bottom(5))
            }

            verticalFlow {
                gap(6)
                padding(Insets.of(5))
                surface(Surface.PANEL)
                horizontalAlignment(HorizontalAlignment.CENTER)

                button {
                    message = "button test".literal()

                    onPress {
                        client?.setScreen(ButtonTestScreen())
                    }
                }
                button {
                    message = "optimization test".literal()

                    onPress {
                        client?.setScreen(TooManyComponentsScreen())
                    }
                }
                button {
                    message = "sizenite".literal()

                    onPress {
                        client?.setScreen(SizingTestScreen())
                    }
                }
            }
        }
    }
}