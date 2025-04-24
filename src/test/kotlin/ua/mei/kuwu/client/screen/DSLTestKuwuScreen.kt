package ua.mei.kuwu.client.screen

import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import ua.mei.kowo.dsl.*

class DSLTestKuwuScreen : BaseOwoScreen<FlowLayout>() {
    override fun createAdapter(): OwoUIAdapter<FlowLayout> {
        return OwoUIAdapter.create(this, ::verticalFlow)
    }

    override fun build(rootComponent: FlowLayout) {
        root {
            verticalAlignment = VerticalAlignment.CENTER
            horizontalAlignment = HorizontalAlignment.CENTER
            gap = 4
            surface = Surface.VANILLA_TRANSLUCENT

            children {
                val layout: FlowLayout = verticalFlow(Sizing.content(), Sizing.content()).apply {
                    padding {
                        all(6)
                    }
                    gap = 3
                }

                +verticalScroll(50.fill, 50.fill, layout).apply {
                    padding {
                        all(7)
                    }
                    surface = Surface.panelWithInset(6)
                }

                +verticalFlow(50.fill, Sizing.content()).apply {
                    horizontalAlignment = HorizontalAlignment.CENTER
                    gap = 2

                    children {
                        var value = ""
                        val textBox = +textBox(100.fill).apply {
                            onChanged().subscribe {
                                value = it
                            }
                        }
                        +button("Add".literal).apply {
                            horizontalSizing = 40.fixed

                            onPress {
                                layout.child(label(value.literal))
                                textBox.text = ""
                            }
                        }
                    }
                }
            }
        }
    }
}
