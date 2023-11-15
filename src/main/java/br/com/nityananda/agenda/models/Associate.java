package br.com.nityananda.agenda.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_ASSOCIATE")
public class Associate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "registration", unique = true, nullable = false)
    private UUID registration;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    public UUID getRegistration() {
        return registration;
    }

    public void setRegistration(UUID registration) {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
