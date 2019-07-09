package br.com.bb.services.user;

import br.com.bb.entitys.user.User;
import br.com.bb.repositorys.user.UserRepository;
import br.com.bb.services.GenericService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserService extends GenericService<User, UserRepository> {

    @Inject
    public UserService(UserRepository repository) {
        super(repository, User.class);
    }
}
