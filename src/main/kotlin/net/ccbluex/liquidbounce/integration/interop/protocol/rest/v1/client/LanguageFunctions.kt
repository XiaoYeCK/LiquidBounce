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

package net.ccbluex.liquidbounce.integration.interop.protocol.rest.v1.client

import com.google.gson.JsonObject
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import net.minecraft.locale.Language

// GET /api/v1/client/translate?key=menu.singleplayer&key=menu.multiplayer
private fun Route.getTranslations() = get("/translate") {
    val keys = call.request.queryParameters.getAll("key").orEmpty()

    call.respond(JsonObject().apply {
        keys.distinct().forEach { key ->
            addProperty(key, Language.getInstance().getOrDefault(key, key))
        }
    })
}

internal fun Route.languageRoutes() {
    getTranslations()
}
