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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="fornecedor")
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fornecedor implements Serializable{
	
	@Id
	@Column(name="id_fornecedor")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aux_fornecedor")
	@SequenceGenerator(allocationSize = 1, name = "seq_aux_fornecedor", sequenceName = "seq_aux_fornecedor")
	private Long idFornecedor;
	
	@Column(name="nome_fornecedor")
	@NotBlank(message="Nome é obrigatório")
	@Pattern(
		    regexp = "[A-ZÀÁÂÃÉÊÍÓÔÕÚÇ].*",
		    message = "Nome deve começar com letra maiúscula"
		    )
	@Pattern(
		    regexp = "[A-Za-zÀ-ú\\s]+",
		    message = "Nome deve conter apenas letras"
		    )
	@Size(min=3)
	private String nomeFornecedor;
	
	@Column(name="cnpj_fornecedor")
	@NotBlank(message="CNPJ é obrigatório")
	@Pattern(regexp = "\\d{14}", message = "CNPJ deve conter exatamente 14 dígitos numéricos")
	private String cnpjFornecedor;
	
	@Column(name="telefone_fornecedor")
	@NotBlank(message="Telefone é obrigatório")
	@Pattern(
		    regexp = "\\d{10,11}",
		    message = "Telefone deve conter apenas dígitos (10 ou 11 com DDD)"
		    )
	private String telefoneFornecedor;
	
	@Column(name="email_fornecedor")
	@NotBlank(message="E-mail é obrigatório")
	@Email
	private String emailFornecedor;
	
	@OneToMany(mappedBy = "fornecedor")
	@JsonIgnore
	private List<Produto> produtos;


}
