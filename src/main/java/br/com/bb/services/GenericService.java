package br.com.bb.services;

import br.com.bb.entitys.GenericEntity;
import br.com.bb.repositorys.GenericRepository;

import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import dev.morphia.query.UpdateResults;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

@Log
@Getter
@Setter
@NoArgsConstructor
public abstract class GenericService<E extends GenericEntity, R extends GenericRepository> {

    private R repository;

    private Class<E> clazz;

    public GenericService(R repository, Class<E> clazz) {
        this.clazz = clazz;
        this.repository = repository;
    }

    public ArrayList<E> getAll() {
        log.log(Level.INFO, "Buscando todos os registros");

        return repository.getMongoDatastore()
                .find(clazz)
                .into(new ArrayList<>());
    }

    public E getOne(String id) {
        log.log(Level.INFO, "Buscando registro " + id);

        if (!ObjectId.isValid(id)) {
            throw new NotFoundException();
        }

        return repository.getMongoDatastore()
                .find(clazz)
                .field("id").equal(new ObjectId(id))
                .first();
    }

    public E save(E object) {
        log.log(Level.INFO, "Salvando entidade");

        object.setLastChange(new Date());

        repository.getMongoDatastore().save(object);
        return getOne(object.getId().toString());
    }

    public boolean delete(String id) {
        final Query<E> query = repository.getMongoDatastore().createQuery(clazz);

        query.field("id").equal(new ObjectId(id));

        final UpdateOperations<E> updateOperations = repository.getMongoDatastore().createUpdateOperations(clazz);

        updateOperations.set("ativo", false);

        UpdateResults updateResults = repository.getMongoDatastore().update(query, updateOperations);

        return updateResults.getUpdatedExisting();
    }

}
