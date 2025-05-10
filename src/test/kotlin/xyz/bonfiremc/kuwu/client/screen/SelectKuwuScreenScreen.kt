package xyz.bonfiremc.kuwu.client.screen

import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import xyz.bonfiremc.kowo.dsl.button
import xyz.bonfiremc.kowo.dsl.gap
import xyz.bonfiremc.kowo.dsl.horizontalAlignment
import xyz.bonfiremc.kowo.dsl.label
import xyz.bonfiremc.kowo.dsl.literal
import xyz.bonfiremc.kowo.dsl.margin
import xyz.bonfiremc.kowo.dsl.padding
import xyz.bonfiremc.kowo.dsl.root
import xyz.bonfiremc.kowo.dsl.shadow
import xyz.bonfiremc.kowo.dsl.surface
import xyz.bonfiremc.kowo.dsl.unaryPlus
import xyz.bonfiremc.kowo.dsl.verticalAlignment
import xyz.bonfiremc.kowo.dsl.verticalFlow

class SelectKuwuScreenScreen : BaseOwoScreen<FlowLayout>() {
    override fun createAdapter(): OwoUIAdapter<FlowLayout> {
        return OwoUIAdapter.create(this, ::verticalFlow)
    }

    override fun build(rootComponent: FlowLayout) {
        root {
            surface = Surface.flat(0x77000000)
            verticalAlignment = VerticalAlignment.CENTER
            horizontalAlignment = HorizontalAlignment.CENTER

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

                +button("optimization test".literal) {
                    client?.setScreen(TooManyComponentsKuwuScreen())
                }

                +button("smoltine".literal) {
                    client?.setScreen(SmolComponentTestKuwuScreen())
                }

                +button("sizenite".literal) {
                    client?.setScreen(SizingTestKuwuScreen())
                }

                +button("dsl test".literal) {
                    client?.setScreen(DSLTestKuwuScreen())
                }
            }
        }
    }
}
