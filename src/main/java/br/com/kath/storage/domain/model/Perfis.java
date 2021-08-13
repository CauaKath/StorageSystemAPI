package br.com.kath.storage.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Perfis implements GrantedAuthority {

    @Id
    private String nome;

    @ManyToMany
    private List<Pessoas> pessoas;

    @Override
    public String getAuthority() {
        return this.nome;
    }

}
