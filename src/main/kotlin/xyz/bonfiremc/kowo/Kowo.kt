package xyz.bonfiremc.kowo

import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object Kowo : ModInitializer {
    const val MOD_ID: String = "kowo"
    const val MOD_NAME: String = "κoωo"

    val logger: Logger = LogManager.getLogger(MOD_NAME)

    override fun onInitialize() {
    }
}
