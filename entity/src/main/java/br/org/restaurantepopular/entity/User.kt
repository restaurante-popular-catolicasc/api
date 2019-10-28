package br.org.restaurantepopular.entity

data class User(
        var id: String? = null,
        val name: String,
        var role: String,
        val code: String,
        val cpf: String)