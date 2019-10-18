package br.org.restaurantepopular.usecase.core;



public interface IUseCase<Request, Response> {
    Response execute(Request request);

}

