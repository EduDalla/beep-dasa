package br.com.beep.model.UsuarioModel;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Regra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomeRegra")
    private String nomeRegra;

    @Column(name = "dateCreated")
    private LocalDateTime dateCreated;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeRegra() {
        return nomeRegra;
    }

    public void setNomeRegra(String nomeRegra) {
        this.nomeRegra = nomeRegra;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}