package com.airwings.app.model.DTO.vuelo;

import java.util.List;

import com.airwings.app.model.entity.avion.Asiento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDto {

	private Long vueloId;
	private Long asientoId;
	private List<Asiento> clases;
}
