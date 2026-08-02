/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LiquidBounce is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LiquidBounce. If not, see <https://www.gnu.org/licenses/>.
 */
package net.ccbluex.liquidbounce.integration.theme

import com.mojang.blaze3d.platform.NativeImage
import net.ccbluex.liquidbounce.render.drawTexQuad
import net.ccbluex.liquidbounce.utils.render.asTexture
import net.ccbluex.liquidbounce.utils.render.textureSetup
import net.minecraft.client.gui.GuiGraphicsExtractor
import java.io.Closeable

sealed interface ThemeBackground : Closeable {

    /**
     * Returns false to let Minecraft render its default wallpaper.
     */
    object Minecraft : ThemeBackground {
        override fun draw(
            context: GuiGraphicsExtractor,
            width: Int,
            height: Int,
            mouseX: Int,
            mouseY: Int,
            delta: Float
        ): Boolean = false // Show default Minecraft wallpaper

        @Suppress("EmptyFunctionBlock")
        override fun close() { }
    }

    /**
     * Background implementation that renders a static image texture.
     * @param texture The image texture
     */
    class Image(
        private val metadata: ThemeMetadata,
        image: NativeImage,
    ) : ThemeBackground {

        private val texture = image.asTexture { "ThemeBackground/Image - ${metadata.name}" }
        private val textureSetup = texture.textureSetup

        override fun draw(
            context: GuiGraphicsExtractor,
            width: Int,
            height: Int,
            mouseX: Int,
            mouseY: Int,
            delta: Float
        ): Boolean {
            context.drawTexQuad(
                textureSetup,
                x0 = 0f, y0 = 0f,
                x1 = width.toFloat(), y1 = height.toFloat(),
            )

            return true
        }

        override fun close() {
            texture.close()
        }
    }


    /**
     * Draws the background on the screen.
     * @param context The drawing context
     * @param width Screen width
     * @param height Screen height
     * @param mouseX Mouse X coordinate
     * @param mouseY Mouse Y coordinate
     * @param delta Time delta for animations
     * @return true if background was drawn, false to use default Minecraft background
     */
    @Suppress("LongParameterList")
    fun draw(
        context: GuiGraphicsExtractor,
        width: Int,
        height: Int,
        mouseX: Int,
        mouseY: Int,
        delta: Float
    ): Boolean

    /**
     * Called when resources are reloaded.
     */
    fun onResourceReload() {}
}
