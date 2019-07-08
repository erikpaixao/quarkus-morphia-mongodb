package br.com.bb.services;

import br.com.bb.entitys.GenericEntity;
import br.com.bb.repositorys.GenericRepository;

import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import dev.morphia.query.UpdateResults;
import lombok.extern.java.Log;
import org.bson.types.ObjectId;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;

@Log
public class GenericService<E, Repository extends GenericRepository> {

    private final Repository repository;

    public GenericService(Repository repository) {
        this.repository = repository;
    }

    public <E extends GenericEntity> ArrayList<E> getAll(Class<E> clazz) {
        log.log(Level.INFO, "Buscando todos os registros");

        return repository.getMongoDatastore()
                .find(clazz)
                .into(new ArrayList<>());
    }

    public <E extends GenericEntity> E get(Class<E> clazz, String id) {
        log.log(Level.INFO, "Buscando registro " + id);

        if (!ObjectId.isValid(id)) {
            throw new NotFoundException();
        }

        return repository.getMongoDatastore()
                .find(clazz)
                .field("id").equal(new ObjectId(id))
                .first();
    }

    public <E extends GenericEntity> E save(E object) {
        log.log(Level.INFO, "Salvando entidade");

        repository.getMongoDatastore().save(object);
        return (E) get(object.getClass(), object.getId().toString());
    }

    public boolean delete(String id, Class<E> clazz) {
        final Query<E> query = repository.getMongoDatastore().createQuery(clazz);

        query.field("id").equal(new ObjectId(id));

        final UpdateOperations<E> updateOperations = repository.getMongoDatastore().createUpdateOperations(clazz);

        updateOperations.set("ativo", false);

        UpdateResults updateResults = repository.getMongoDatastore().update(query, updateOperations);

        return updateResults.getUpdatedExisting();
    }

}
