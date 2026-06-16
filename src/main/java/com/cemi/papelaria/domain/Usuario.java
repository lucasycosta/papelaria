package com.cemi.papelaria.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class Usuario implements Serializable{

	@Id
	@Column(name="id_usuario", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aux_usuario")
	@SequenceGenerator(allocationSize = 1, name = "seq_aux_usuario", sequenceName = "seq_aux_usuario")
	private Long idUsuario;
	
	@Column(name="nome_usuario", nullable = false, unique = true, length = 100)
	private String nome;
	
	@Column(name="email_usuario", nullable = false, unique = true, length = 100)
	private String email;
	
	@Column(name="senha_usuario", nullable = false, unique = true, length = 8)
	private String senha;

}
