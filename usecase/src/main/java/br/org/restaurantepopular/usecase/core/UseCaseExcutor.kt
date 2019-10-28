package br.org.restaurantepopular.usecase.core

import java.util.concurrent.CompletableFuture

class UseCaseExecutor : IUseCaseExecutor {
    override fun <RequestDto, ResponseDto, Request, Response> invoke(
        useCase: IUseCase<Request, Response>,
        requestDto: RequestDto,
        requestConverter: (RequestDto) -> Request,
        responseConverter: (Response) -> ResponseDto
    ): CompletableFuture<ResponseDto> =
        CompletableFuture
            .supplyAsync { requestConverter(requestDto) }
            .thenApplyAsync { useCase.execute(it) }
            .thenApplyAsync { responseConverter(it) }
}