package br.org.restaurantepopular.data_db.repository.user;

import br.org.restaurantepopular.data_db.mapper.UserEntityMapper;
import br.org.restaurantepopular.entity.User;
import br.org.restaurantepopular.usecase.core.exception.NotFoundException;
import br.org.restaurantepopular.usecase.user.IUserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepository implements IUserRepository {

    private final UserDAO userDAO;

    public UserRepository(UserDAO userDAO) { this.userDAO = userDAO;}

    @Override
    public Optional<User> save (User user){
        return Optional.ofNullable(UserEntityMapper.toUser(
                userDAO.save(UserEntityMapper.toUserEntity(user))
        ));
    }

    @Override
    public void update(User user) throws NotFoundException {
        if(!userDAO.existsById(user.getId()))
            throw new NotFoundException(String.format("Not found a user with this %s ID",user.getId()));

        userDAO.save(UserEntityMapper.toUserEntity(user));
    }

    @Override
    public List<User> listUser() {
        return userDAO
                .findAll()
                .stream()
                .map(UserEntityMapper::toUser)
                .collect(Collectors.toList());
    }

    @Override
    public  Optional<User> findById(String id) throws NotFoundException {
        var userOptional = userDAO.findById(id).map(UserEntityMapper::toUser);
        if (userOptional.isEmpty())
            throw new NotFoundException(String.format("Not found a user with this %s ID", id));
        return userOptional;
    }

    @Override
    public void delete(String id){
        if (!userDAO.existsById(id))
            throw new NotFoundException(String.format("Not found a user with this %s ID", id));
        userDAO.deleteById(id);
    }






}
