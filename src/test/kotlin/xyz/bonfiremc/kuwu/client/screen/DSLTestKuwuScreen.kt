package xyz.bonfiremc.kuwu.client.screen

import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import xyz.bonfiremc.kowo.dsl.button
import xyz.bonfiremc.kowo.dsl.fill
import xyz.bonfiremc.kowo.dsl.fixed
import xyz.bonfiremc.kowo.dsl.gap
import xyz.bonfiremc.kowo.dsl.horizontalAlignment
import xyz.bonfiremc.kowo.dsl.horizontalSizing
import xyz.bonfiremc.kowo.dsl.label
import xyz.bonfiremc.kowo.dsl.literal
import xyz.bonfiremc.kowo.dsl.padding
import xyz.bonfiremc.kowo.dsl.root
import xyz.bonfiremc.kowo.dsl.surface
import xyz.bonfiremc.kowo.dsl.textBox
import xyz.bonfiremc.kowo.dsl.unaryPlus
import xyz.bonfiremc.kowo.dsl.verticalAlignment
import xyz.bonfiremc.kowo.dsl.verticalFlow
import xyz.bonfiremc.kowo.dsl.verticalScroll

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
