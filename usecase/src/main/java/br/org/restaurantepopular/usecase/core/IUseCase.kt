package br.org.restaurantepopular.usecase.core

interface IUseCase<in Request, out Response> {
    fun execute(request: Request): Response
}