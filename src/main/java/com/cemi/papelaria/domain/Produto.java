package com.cemi.papelaria.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="produto")
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements Serializable{
	
	@Id
	@Column(name="id_produto")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aux_produto")
	@SequenceGenerator(allocationSize = 1, name = "seq_aux_produto", sequenceName = "seq_aux_produto")
	private Long idProduto;
	
	@Column(name="nome_produto")
	private String nomeProduto;
	
	@Column(name="descricao_produto")
	private String descricao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_categoria_produto", nullable = false)
	private CategoriaProduto categoria;
	
	@Column(name="preco_produto")
	private Double preco;
	
	@Column(name="estoque_produto")
	private Integer estoque;

	@Column(name="data_validade")
	private LocalDate dataValidade;
	
	@OneToMany(mappedBy = "produto")
	@JsonIgnore
    private List<ItemVenda> itens;
}
