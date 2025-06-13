package com.negocio.comidajipijapa.Data

import android.content.Context
import android.content.SharedPreferences

class FavoritosPrefs(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("favoritos_prefs", Context.MODE_PRIVATE)

    fun guardarFavorito(id: String) {
        val favoritos = obtenerFavoritos().toMutableSet()
        favoritos.add(id)
        prefs.edit().putStringSet("favoritos", favoritos).apply()
    }

    fun eliminarFavorito(id: String) {
        val favoritos = obtenerFavoritos().toMutableSet()
        favoritos.remove(id)
        prefs.edit().putStringSet("favoritos", favoritos).apply()
    }

    fun esFavorito(id: String): Boolean {
        return obtenerFavoritos().contains(id)
    }

    fun obtenerFavoritos(): Set<String> {
        return prefs.getStringSet("favoritos", emptySet()) ?: emptySet()
    }
}
