package com.cemi.papelaria.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProdutoResponse {

	private Long idProduto;
	private String nomeProduto;
	private String descricao;
	private Long idCategoriaProduto;
	private String nomeCategoria;
	private Double preco;
	private Integer estoque;
	private LocalDate dataValidade;
}
