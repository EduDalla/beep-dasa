package br.com.beep.model.UsuarioModel;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String dataNascimento;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UsuarioRegra> usuarioRegras = new HashSet<>();

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Set<UsuarioRegra> getUsuarioRegras() {
		return usuarioRegras;
	}

	public void setUsuarioRegras(Set<UsuarioRegra> usuarioRegras) {
		this.usuarioRegras = usuarioRegras;
	}
}