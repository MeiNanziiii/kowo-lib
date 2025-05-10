package xyz.bonfiremc.kuwu.client.screen

import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import net.minecraft.item.Items
import xyz.bonfiremc.kowo.dsl.collapsible
import xyz.bonfiremc.kowo.dsl.fill
import xyz.bonfiremc.kowo.dsl.fixed
import xyz.bonfiremc.kowo.dsl.horizontalAlignment
import xyz.bonfiremc.kowo.dsl.item
import xyz.bonfiremc.kowo.dsl.literal
import xyz.bonfiremc.kowo.dsl.padding
import xyz.bonfiremc.kowo.dsl.root
import xyz.bonfiremc.kowo.dsl.surface
import xyz.bonfiremc.kowo.dsl.unaryPlus
import xyz.bonfiremc.kowo.dsl.verticalAlignment
import xyz.bonfiremc.kowo.dsl.verticalFlow
import xyz.bonfiremc.kowo.dsl.verticalScroll
import java.util.concurrent.ThreadLocalRandom

class TooManyComponentsKuwuScreen : BaseOwoScreen<FlowLayout>() {
    override fun createAdapter(): OwoUIAdapter<FlowLayout> {
        return OwoUIAdapter.create(this, ::verticalFlow)
    }

    override fun build(rootComponent: FlowLayout) {
        root {
            verticalAlignment = VerticalAlignment.CENTER
            horizontalAlignment = HorizontalAlignment.CENTER

            +verticalScroll(45.fill, 45.fill, verticalFlow(Sizing.content(), Sizing.content()).apply {
                (0..50000).forEach { _ ->
                    +collapsible(Sizing.content(), Sizing.content(), ThreadLocalRandom.current().nextInt(100000).toString().literal).apply {
                        +item(Items.ECHO_SHARD.defaultStack).sizing(100.fixed)
                    }
                }
            }).apply {
                padding {
                    all(5)
                }
                surface = Surface.DARK_PANEL
            }
        }
    }
}
