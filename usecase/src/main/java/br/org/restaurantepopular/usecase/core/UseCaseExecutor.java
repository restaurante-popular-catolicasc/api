package br.org.restaurantepopular.usecase.core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public class UseCaseExecutor implements IUseCaseExecutor {

    @Override
    public <RequestDTO, ResponseDTO, Request, Response> CompletionStage<ResponseDTO> invoke
            (IUseCase<Request, Response> useCase,
             RequestDTO requestDTO,
             Function<RequestDTO, Request> requestConverter,
             Function<Response, ResponseDTO> responseConverter) {
        return CompletableFuture
                .supplyAsync(() -> requestConverter.apply(requestDTO))
                .thenApplyAsync(useCase::execute)
                .thenApplyAsync(responseConverter);
    }
}
