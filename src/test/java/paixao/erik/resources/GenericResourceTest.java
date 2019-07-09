package paixao.erik.resources;

import paixao.erik.entitys.GenericEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

abstract class GenericResourceTest<E extends GenericEntity, R extends GenericResources> {

    private R resource;
    private String uuid;
    private E object;
    private Class<R> clazz;

    GenericResourceTest(Class<R> clazz, E object) {
        this.clazz = clazz;
        this.object = object;
        this.uuid = object.getId().toString();
    }

    @BeforeEach
    void setUp() {
        resource = Mockito.mock(clazz);
    }

    @Test
    void get() {
        resource.get(uuid);
        Mockito.verify(resource).get(uuid);
    }

    @Test
    void save() {
        resource.save(object);
        Mockito.verify(resource).save(object);
    }

    @Test
    void delete() {
        resource.delete(uuid);
        Mockito.verify(resource).delete(uuid);
    }

    @Test
    void getAll() {
        resource.getAll();
        Mockito.verify(resource).getAll();
    }
}