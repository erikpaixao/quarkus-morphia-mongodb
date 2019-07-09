package paixao.erik.services.user;

import paixao.erik.entitys.user.User;
import paixao.erik.repositorys.user.UserRepository;
import paixao.erik.services.GenericService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserService extends GenericService<User, UserRepository> {

    @Inject
    public UserService(UserRepository repository) {
        super(repository, User.class);
    }
}
