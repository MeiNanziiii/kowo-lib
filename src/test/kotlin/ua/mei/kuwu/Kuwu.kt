package ua.mei.kuwu

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import org.lwjgl.glfw.GLFW
import ua.mei.kuwu.client.SelectKuwuScreenScreen


object Kuwu : ModInitializer {
    override fun onInitialize() {
        val testScreenKeyBinding = KeyBinding("key.kuwu.test_screen", GLFW.GLFW_KEY_K, "misc")
        KeyBindingHelper.registerKeyBinding(testScreenKeyBinding)

        ClientTickEvents.START_CLIENT_TICK.register {
            while (testScreenKeyBinding.wasPressed()) {
                it.setScreen(SelectKuwuScreenScreen())
            }
        }
    }
}