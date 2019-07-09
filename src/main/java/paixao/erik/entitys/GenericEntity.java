package paixao.erik.entitys;

import dev.morphia.annotations.Id;
import dev.morphia.annotations.PrePersist;
import dev.morphia.annotations.Version;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
@NoArgsConstructor
@RegisterForReflection
public abstract class GenericEntity {

    @Id
    protected ObjectId id;

    protected Date creationDate;
    protected Date lastChange;

    protected Boolean ativo;

    @Version
    private long version;

    public GenericEntity(ObjectId id) {
        this.id = id;
    }

    @PrePersist
    public void prePersist() {
        if (ativo == null) {
            ativo = true;
        }
        this.creationDate = (creationDate == null) ? new Date() : creationDate;
        this.lastChange = new Date();
    }
}
