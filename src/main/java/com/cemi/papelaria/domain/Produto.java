package com.cemi.papelaria.domain;

import java.io.Serializable;
import java.util.List;

import com.cemi.papelaria.domain.enums.CategoriaProduto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@NotBlank(message="Nome é obrigatório")
	private String nomeProduto;
	
	@Column(name="descricao_produto")
	private String descricao;
	
	@Column(name="categoria_produto")
	@NotNull(message="Categoria é obrigatória")
	@Enumerated(EnumType.STRING)
	private CategoriaProduto categoria;
	
	@Column(name="preco_produto")
	@NotNull(message="Preço é obrigatório")
	private Double preco;
	
	@Column(name="estoque_produto")
	@NotNull(message="Estoque é obrigatório")
	private Integer estoque;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_fornecedor", nullable = true)
	private Fornecedor fornecedor;
	
	@OneToMany(mappedBy = "produto")
	@JsonIgnore
    private List<ItemVenda> itens;
}
