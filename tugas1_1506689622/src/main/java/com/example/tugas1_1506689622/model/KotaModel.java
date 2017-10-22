package com.example.tugas1_1506689622.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KotaModel {
	@NotNull
	private int id;
	@NotNull
	private String kodeKota;
	@NotNull
	private String namaKota;
}
