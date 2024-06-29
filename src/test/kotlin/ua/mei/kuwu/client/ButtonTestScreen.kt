package ua.mei.kuwu.client

import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.Sizing
import ua.mei.kowo.ops.button
import ua.mei.kowo.ops.label
import ua.mei.kowo.ops.literal
import ua.mei.kowo.ui.base.BaseKowoScreen


class ButtonTestScreen : BaseKowoScreen<FlowLayout>() {
    override fun createRoot(horizontalSizing: Sizing, verticalSizing: Sizing): FlowLayout {
        return Containers.verticalFlow(horizontalSizing, verticalSizing)
    }

    override fun build() {
        // like rootComponent, but better
        root {
            // components can be stored in variables...
            val exampleLabel = label {
                text("Example screen: 0".literal())
            }

            // or directly added to the root
            button {
                message = "Example button".literal()

                // variables can be stored within components
                var value = 0
                onPress {
                    // components stored in variables can be modified
                    exampleLabel.text("Example screen: ${++value}".literal())
                }
            }
        }
    }
}