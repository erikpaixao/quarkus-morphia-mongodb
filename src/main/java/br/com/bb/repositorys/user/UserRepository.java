package br.com.bb.repositorys.user;

import br.com.bb.entitys.user.User;
import br.com.bb.repositorys.GenericRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository extends GenericRepository<User> {
}
