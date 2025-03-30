package ua.mei.kuwu.client.screen

import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import ua.mei.kowo.dsl.*

class SelectKuwuScreenScreen : BaseOwoScreen<FlowLayout>() {
    override fun createAdapter(): OwoUIAdapter<FlowLayout> {
        return OwoUIAdapter.create(this, ::verticalFlow)
    }

    override fun build(rootComponent: FlowLayout) {
        root {
            surface = Surface.flat(0x77000000)
            verticalAlignment = VerticalAlignment.CENTER
            horizontalAlignment = HorizontalAlignment.CENTER

            children {
                +label("Available screens".literal).apply {
                    margin {
                        bottom = 5
                    }
                    shadow = true
                }
                +verticalFlow(Sizing.content(), Sizing.content()).apply {
                    padding {
                        all(5)
                    }
                    gap = 6
                    surface = Surface.PANEL
                    horizontalAlignment = HorizontalAlignment.CENTER

                    children {
                        +button("optimization test".literal) {
                            client!!.setScreen(TooManyComponentsKuwuScreen())
                        }
                        +button("sizenite".literal) {
                            client!!.setScreen(SizingTestKuwuScreen())
                        }
                        +button("dsl test".literal) {
                            client!!.setScreen(DSLTestKuwuScreen())
                        }
                    }
                }
            }
        }
    }
}
