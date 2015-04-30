package br.com.condinte.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "perfis")
public class PerfilUsuario {
	@Id
	private String nome;

	private String descricao;

	@OneToMany
	@JoinTable(name = "perfis_permissoes", joinColumns = { @JoinColumn(name = "perfil_nome") }, inverseJoinColumns = { @JoinColumn(name = "permissao_nome") })
	private List<PermissaoUsuario> permissoes;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<PermissaoUsuario> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<PermissaoUsuario> permissoes) {
		this.permissoes = permissoes;
	}
}
