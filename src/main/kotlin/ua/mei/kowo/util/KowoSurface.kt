package ua.mei.kowo.util

import io.wispforest.owo.ui.core.Surface
import net.minecraft.client.MinecraftClient

object KowoSurface {
    // TODO: fix blur render size
    val VANILLA_BLUR: Surface = Surface { context, component ->
        MinecraftClient.getInstance().gameRenderer.renderBlur()
    }
}
