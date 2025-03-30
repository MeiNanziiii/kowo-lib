package ua.mei.kuwu.client.screen

import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import net.minecraft.item.Items
import ua.mei.kowo.dsl.*
import java.util.concurrent.ThreadLocalRandom

class TooManyComponentsKuwuScreen : BaseOwoScreen<FlowLayout>() {
    override fun createAdapter(): OwoUIAdapter<FlowLayout> {
        return OwoUIAdapter.create(this, ::verticalFlow)
    }

    override fun build(rootComponent: FlowLayout) {
        root {
            verticalAlignment = VerticalAlignment.CENTER
            horizontalAlignment = HorizontalAlignment.CENTER

            children {
                +verticalScroll(45.fill, 45.fill, verticalFlow(Sizing.content(), Sizing.content()).apply {
                    children {
                        (0..50000).forEach {
                            +collapsible(Sizing.content(), Sizing.content(), ThreadLocalRandom.current().nextInt(100000).toString().literal).apply {
                                children {
                                    +item(Items.ECHO_SHARD.defaultStack).sizing(100.fixed)
                                }
                            }
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
}
