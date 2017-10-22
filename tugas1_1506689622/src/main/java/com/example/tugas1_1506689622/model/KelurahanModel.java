package com.example.tugas1_1506689622.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelurahanModel {
	@NotNull
	private int id;
	@NotNull
	private int idKecamatan;
	@NotNull
	private String kodeKelurahan;
	@NotNull
	private String namaKelurahan;
	@NotNull
	private String kodePos;
	
}
