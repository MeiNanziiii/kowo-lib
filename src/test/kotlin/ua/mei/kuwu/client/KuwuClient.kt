package ua.mei.kuwu.client

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import org.lwjgl.glfw.GLFW
import ua.mei.kuwu.client.screen.SelectKuwuScreenScreen

object KuwuClient : ClientModInitializer {
    val testScreenKeyBinding: KeyBinding = KeyBinding("key.kuwu.test_screen", GLFW.GLFW_KEY_K, "misc")

    override fun onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(testScreenKeyBinding)

        ClientTickEvents.END_CLIENT_TICK.register { client ->
            while (testScreenKeyBinding.wasPressed()) {
                client.setScreen(SelectKuwuScreenScreen())
            }
        }
    }
}
