package com.cemi.papelaria.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
@Table(name="cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class Cliente implements Serializable{
	
	@Id
	@Column(name="id_cliente")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aux_cliente")
	@SequenceGenerator(allocationSize = 1, name = "seq_aux_cliente", sequenceName = "seq_aux_cliente")
	private Long idCliente;
	
	@Column(name="nome_cliente")
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
	private String nome;
	
	@Column(name="cpf_cliente")
	@NotBlank(message="CPF é obrigatório")
	@Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos")
	private String cpf;
	
	@Column(name="telefone_cliente")
	@NotBlank(message="Telefone é obrigatório")
	@Pattern(
		    regexp = "\\d{10,11}",
		    message = "Telefone deve conter apenas dígitos (10 ou 11 com DDD)"
		    )
	private String telefone;
	
	@Column(name="email_cliente")
	@NotBlank(message="E-mail é obrigatório")
	@Email
	private String email;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Venda> vendas;
	
	
	
	
}
