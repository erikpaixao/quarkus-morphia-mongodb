package br.com.bb.entitys.user;

import br.com.bb.entitys.GenericEntity;
import dev.morphia.annotations.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends GenericEntity {

    @NotEmpty(message = "Campo username nao pode ser nulo")
    @Email
    private String username;

    @NotEmpty(message = "Campo password nao pode ser nulo")
    @Size(min = 2, max = 14)
    private String password;

    public User(String id, String username, String password) {
        super(new ObjectId(id));
        this.username = username;
        this.password = password;
    }
}
