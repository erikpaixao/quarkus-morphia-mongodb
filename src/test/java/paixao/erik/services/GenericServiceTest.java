package paixao.erik.services;

import paixao.erik.entitys.GenericEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

abstract class GenericServiceTest<E extends GenericEntity, S extends GenericService> {

    private S service;
    private String uuid;
    private E object;
    private Class<S> clazz;

    GenericServiceTest(Class<S> clazz, E object) {
        this.clazz = clazz;
        this.object = object;
        this.uuid = object.getId().toString();
    }

    @BeforeEach
    void setUp() {
        service = Mockito.mock(clazz);
    }

    @Test
    void getAll() {
        service.getAll();
        Mockito.verify(service).getAll();
    }

    @Test
    void get() {
        service.getOne(uuid);
        Mockito.verify(service).getOne(uuid);
    }

    @Test
    void save() {
        service.save(object);
        Mockito.verify(service).save(object);
    }

    @Test
    void delete() {
        service.delete(uuid);
        Mockito.verify(service).delete(uuid);
    }
}