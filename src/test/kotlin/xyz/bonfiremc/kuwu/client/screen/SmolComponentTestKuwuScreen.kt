package xyz.bonfiremc.kuwu.client.screen

import io.wispforest.owo.ui.base.BaseUIModelScreen
import io.wispforest.owo.ui.component.BoxComponent
import io.wispforest.owo.ui.component.SlimSliderComponent
import io.wispforest.owo.ui.container.FlowLayout
import net.minecraft.util.Identifier
import xyz.bonfiremc.kowo.dsl.childById
import xyz.bonfiremc.kowo.dsl.fixed
import xyz.bonfiremc.kowo.dsl.horizontalSizing
import xyz.bonfiremc.kowo.dsl.literal
import xyz.bonfiremc.kowo.dsl.padding
import xyz.bonfiremc.kowo.dsl.root
import xyz.bonfiremc.kowo.dsl.value

class SmolComponentTestKuwuScreen() : BaseUIModelScreen<FlowLayout>(FlowLayout::class.java, Identifier.of("kuwu", "smol_components")) {
    override fun build(rootComponent: FlowLayout) {
        root {
            childById<SlimSliderComponent>("precise-slider").apply {
                tooltipSupplier(SlimSliderComponent.valueTooltipSupplier(2))
            }

            childById<SlimSliderComponent>("tiny-steppy-man").apply {
                tooltipSupplier(SlimSliderComponent.VALUE_TOOLTIP_SUPPLIER)

                onChanged().subscribe {
                    client?.player?.sendMessage("tiny steppy man: $value".literal, false)
                }
            }

            childById<SlimSliderComponent>("big-steppy-man").apply {
                tooltipSupplier { "big steppy man: $it".literal }

                onChanged().subscribe {
                    client?.player?.sendMessage("big steppy man: $value".literal, false)
                }
            }

            childById<SlimSliderComponent>("inset-slider").apply {
                tooltipSupplier { "Insets: ${it.toInt()}".literal }

                onChanged().subscribe {
                    rootComponent.childById<FlowLayout>("inset-container").apply {
                        padding {
                            all(it.toInt())
                        }
                    }
                }
            }

            childById<SlimSliderComponent>("expando-slider").apply {
                tooltipSupplier(SlimSliderComponent.VALUE_TOOLTIP_SUPPLIER)

                onChanged().subscribe {
                    rootComponent.childById<BoxComponent>("expando-box").apply {
                        horizontalSizing = it.toInt().fixed
                    }
                }
            }
        }
    }
}
