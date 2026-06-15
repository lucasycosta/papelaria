package com.cemi.papelaria.dto.request;

import java.time.LocalDate;

import com.cemi.papelaria.domain.enums.CategoriaProduto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdutoRequest {

	@NotBlank(message="Nome é obrigatório")
	private String nomeProduto;

	private String descricao;

	@NotNull(message="Categoria é obrigatória")
	private CategoriaProduto categoria;

	@NotNull(message="Preço é obrigatório")
	private Double preco;

	@NotNull(message="Estoque é obrigatório")
	private Integer estoque;

	private LocalDate dataValidade;
}
