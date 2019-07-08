package br.com.bb.services.user;

import br.com.bb.entitys.user.User;
import br.com.bb.repositorys.user.UserRepository;
import br.com.bb.services.GenericService;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

@Log
@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;


    public List<User> getUsers() {
        return getGenericService().getAll(User.class);
    }

    public User getOne(String id) {
        return getGenericService().get(User.class, id);
    }

    public User save(User user) {
        if (Objects.isNull(user.getId()) || StringUtils.isEmpty(user.getId().toString())) {
            return getGenericService().save(user);
        } else {
            return update(user);
        }
    }

    public boolean delete(String id) {
        return getGenericService().delete(id, User.class);
    }

    private User update(User user) {
        return getGenericService().save(user);
    }

    private GenericService<User, UserRepository> getGenericService() {
        return new GenericService<>(userRepository);
    }

}
