package org.kodluyoruz.webuygulamasi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Request Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
	
	private Integer id;
	private String name;
	private float price;
}
