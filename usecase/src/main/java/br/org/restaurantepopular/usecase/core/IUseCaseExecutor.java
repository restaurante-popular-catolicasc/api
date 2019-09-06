package br.org.restaurantepopular.usecase.core;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public interface IUseCaseExecutor {
    <RequestDTO, ResponseDTO, Request, Response> CompletionStage<ResponseDTO> invoke(
            IUseCase<Request, Response> useCase,
            RequestDTO requestDTO,
            Function<RequestDTO, Request> requestConverter,
            Function<Response, ResponseDTO> responseConverter
    );

    default <RequestDTO, Request> CompletionStage invoke(
            IUseCase<Request, Object> useCase,
            RequestDTO requestDTO,
            Function<RequestDTO, Request> requestConverter) {
        return invoke(useCase, requestDTO, requestConverter, null);
    }

    default <ResponseDTO, Response> CompletionStage<ResponseDTO> invoke(
            IUseCase<Optional, Response> useCase,
            Function<Response, ResponseDTO> responseConverter) {
        return invoke(useCase, null, ((Optional requestDTO) -> Optional.empty()), responseConverter);
    }

    default CompletionStage invoke(IUseCase<Optional, Optional> useCase) {
        return invoke(useCase, null);
    }
}
