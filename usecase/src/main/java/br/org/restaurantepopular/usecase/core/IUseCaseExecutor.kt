package br.org.restaurantepopular.usecase.core

import java.util.concurrent.CompletionStage

interface IUseCaseExecutor {
    operator fun <RequestDto, ResponseDto, Request, Response> invoke(
        useCase: IUseCase<Request, Response>,
        requestDto: RequestDto,
        requestConverter: (RequestDto) -> Request,
        responseConverter: (Response) -> ResponseDto
    ): CompletionStage<ResponseDto>

    operator fun <RequestDto, Request> invoke(
        useCase: IUseCase<Request, Unit>,
        requestDto: RequestDto,
        requestConverter: (RequestDto) -> Request
    ) =
        invoke(useCase, requestDto, requestConverter, {})

    operator fun invoke(useCase: IUseCase<Unit, Unit>) =
        invoke(useCase, Unit) { }

    operator fun <ResponseDto, Response> invoke(
        useCase: IUseCase<Unit, Response>,
        responseConverter: (Response) -> ResponseDto
    ) =
        invoke(useCase, Unit, { }, responseConverter)
}