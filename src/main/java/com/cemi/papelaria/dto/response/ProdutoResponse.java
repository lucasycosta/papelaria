package com.cemi.papelaria.dto.response;

import java.time.LocalDate;

import com.cemi.papelaria.domain.enums.CategoriaProduto;

import lombok.Data;

@Data
public class ProdutoResponse {

	private Long idProduto;
	private String nomeProduto;
	private String descricao;
	private CategoriaProduto categoria;
	private Double preco;
	private Integer estoque;
	private LocalDate dataValidade;
}
