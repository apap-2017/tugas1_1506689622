package com.example.tugas1_1506689622.service;

import java.util.List;

import com.example.tugas1_1506689622.model.KecamatanModel;
import com.example.tugas1_1506689622.model.KelurahanModel;
import com.example.tugas1_1506689622.model.KotaModel;

public interface LokasiService {
	KelurahanModel selectKelurahan(int id);
	KecamatanModel selectKecamatan(int id);
	KotaModel selectKota(int id);
	KelurahanModel selectKelurahanByName(String namaKelurahan);
	List<KelurahanModel> selectKelurahanAll(int idKecamatan);
	List<KecamatanModel> selectKecamatanAll(int idKota);
	List<KotaModel> selectKotaAll();
}
