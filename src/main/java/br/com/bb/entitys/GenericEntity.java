package br.com.bb.entitys;

import dev.morphia.annotations.Id;
import dev.morphia.annotations.PrePersist;
import dev.morphia.annotations.Version;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
@RegisterForReflection
public abstract class GenericEntity {

    @Id
    protected ObjectId id;

    protected Date creationDate;
    protected Date lastChange;

    protected Boolean ativo;

    @Version
    private long version;

    @PrePersist
    public void prePersist() {
        this.ativo = (ativo == null) ? true : ativo;
        this.creationDate = (creationDate == null) ? new Date() : creationDate;
        this.lastChange = new Date();
    }
}