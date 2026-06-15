package com.cemi.papelaria.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="item_venda")
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemVenda implements Serializable{
	
	@Id
	@Column(name="id_item_venda")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aux_item")
	@SequenceGenerator(allocationSize = 1, name = "seq_aux_item", sequenceName = "seq_aux_item")
	private Long idItemVenda;
	
	@Column(name = "quantidade")
	private Integer quantidade;
	
	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_produto", nullable = false)
	private Produto produto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_venda", nullable = false)
	private Venda venda;

	@Column(name = "id_venda", insertable = false, updatable = false)
	private Long idVenda;

}
