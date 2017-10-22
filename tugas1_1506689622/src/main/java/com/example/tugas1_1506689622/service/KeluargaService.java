package com.example.tugas1_1506689622.service;

import java.util.List;

import com.example.tugas1_1506689622.model.KeluargaModel;
import com.example.tugas1_1506689622.model.PendudukModel;

public interface KeluargaService {
	KeluargaModel selectKeluarga(int id);
	
	int selectIdKeluarga(String nkk);

	void addKeluarga(KeluargaModel keluarga);
	
	int idNextKeluarga();
	
	int jumlahKeluargaBerdasarkanDomisilidanTanggal(String nkk);
	
	void updateKeluarga(KeluargaModel keluarga);
	
	List<PendudukModel> selectAnggotaKeluarga(int id);
}
