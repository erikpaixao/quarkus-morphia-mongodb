package br.com.bb.entitys.user;

import br.com.bb.entitys.GenericEntity;
import dev.morphia.annotations.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends GenericEntity {

    @NotEmpty(message = "Campo username nao pode ser nulo")
    private String username;

    @NotEmpty(message = "Campo password nao pode ser nulo")
    private String password;

}
