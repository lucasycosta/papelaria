package com.cemi.papelaria.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="categoria_produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class CategoriaProduto implements Serializable{
	
	@Id
	@Column(name="id_categoria_produto", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aux_categoria")
	@SequenceGenerator(allocationSize = 1, name = "seq_aux_categoria", sequenceName = "seq_aux_categoria")
	private Long idCategoriaProduto;
	
	@Column(name="nome_categoria")
	private String nomeCategoria;

	@OneToMany(mappedBy = "categoria")
	@JsonIgnore
	private List<Produto> produtos;

}
