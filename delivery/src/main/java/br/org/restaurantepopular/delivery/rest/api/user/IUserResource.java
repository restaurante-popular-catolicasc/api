package br.org.restaurantepopular.delivery.rest.api.user;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/users")
    public interface IUserResource {

    @GetMapping(
            value = "{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    CompletionStage<UserDTO> getUserById(@PathVariable("id") String id);

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    CompletionStage createUser(@RequestBody UserDTO userDTO);

    @PutMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    CompletionStage<ResponseEntity> updateUser(@RequestBody UserDTO userDTO);

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
                consumes = {MediaType.APPLICATION_JSON_VALUE})
    CompletionStage<List<UserDTO>> listUsers();


    @DeleteMapping(value = "{id}")
    CompletionStage<ResponseEntity> deleteUser(@PathVariable("id") String id);


    }
