package br.com.bb.repositorys;

import br.com.bb.entitys.GenericEntity;
import dev.morphia.Datastore;
import lombok.Data;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Data
@ApplicationScoped
public abstract class GenericRepository<E> {

    @Inject
    Datastore datastore;

    public Datastore getMongoDatastore() {
        return datastore;
    }

    public <E extends GenericEntity> String persist(E entity) {
        getMongoDatastore().save(entity);
        return entity.getId().toString();
    }

    public <E extends GenericEntity> long count(Class<E> clazz) {
        if (clazz == null) {
            return 0;
        }

        return getMongoDatastore().find(clazz).count();
    }

    public <E extends GenericEntity> E get(Class<E> clazz, final ObjectId id) {
        if ((clazz == null) || (id == null)) {
            return null;
        }

        return getMongoDatastore().find(clazz).field("id").equal(id).first();
    }

}
