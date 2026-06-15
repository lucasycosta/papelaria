package com.cemi.papelaria.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.cemi.papelaria.domain.enums.FormaPagamento;

import jakarta.persistence.CascadeType;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="venda")
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venda implements Serializable{
	
	@Id
	@Column(name="id_venda")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aux_venda")
	@SequenceGenerator(allocationSize = 1, name = "seq_aux_venda", sequenceName = "seq_aux_venda")
	private Long idVenda;
	
	@CreationTimestamp
	@Column(name="data_venda", updatable = false)
	private LocalDate dataVenda;
	
	@Column(name="valor_total")
	private Double valorTotal;
	
	@Column(name="forma_pagamento")
	@Enumerated(EnumType.STRING)
	private FormaPagamento formaPagamento;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;

	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVenda> itens = new ArrayList<>();
}
