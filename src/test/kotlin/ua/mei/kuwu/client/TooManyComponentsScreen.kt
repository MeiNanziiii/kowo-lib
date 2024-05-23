package ua.mei.kuwu.client

import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import net.minecraft.item.Items
import ua.mei.kowo.ops.*
import ua.mei.kowo.ui.base.BaseKowoScreen
import java.util.concurrent.ThreadLocalRandom

class TooManyComponentsScreen : BaseKowoScreen<FlowLayout>() {
    override fun createRoot(horizontalSizing: Sizing, verticalSizing: Sizing): FlowLayout {
        return Containers.verticalFlow(horizontalSizing, verticalSizing)
    }

    override fun build() {
        root {
            verticalScroll {
                sizing(Sizing.fill(45))

                verticalFlow {
                    for (i in 0..50000) {
                        collapsible {
                            title(ThreadLocalRandom.current().nextInt(100000).toString().literal())

                            item {
                                sizing(Sizing.fixed(100))
                                stack(Items.ECHO_SHARD.defaultStack)
                            }
                        }
                    }
                }

                surface(Surface.DARK_PANEL)
                padding(Insets.of(5))
            }

            verticalAlignment(VerticalAlignment.CENTER)
            horizontalAlignment(HorizontalAlignment.CENTER)
        }
    }
}