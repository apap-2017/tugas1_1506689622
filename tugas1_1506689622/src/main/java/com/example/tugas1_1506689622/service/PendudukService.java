package com.example.tugas1_1506689622.service;

import com.example.tugas1_1506689622.model.PendudukModel;

public interface PendudukService {
	PendudukModel selectPenduduk(String nik);
	
	void addPenduduk(PendudukModel penduduk);
	
	int idNext();
	
	int jumlahPendudukBerdasarkanTanggalLahirdanKelamin(String tanggalLahir, String jenisKelamin, String nik);

	void updatePenduduk(PendudukModel penduduk);
}
