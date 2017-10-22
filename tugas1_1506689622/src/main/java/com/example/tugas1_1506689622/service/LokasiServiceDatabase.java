package com.example.tugas1_1506689622.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1_1506689622.dao.LokasiMapper;
import com.example.tugas1_1506689622.model.KecamatanModel;
import com.example.tugas1_1506689622.model.KelurahanModel;
import com.example.tugas1_1506689622.model.KotaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LokasiServiceDatabase implements LokasiService {
	@Autowired
	private LokasiMapper lokasiMapper;
	
	@Override
	public KelurahanModel selectKelurahan(int id) {
		log.info ("select kelurahan with id {}", id);
        return lokasiMapper.selectKelurahan(id);
	}
	
	@Override
	public KecamatanModel selectKecamatan(int id) {
		log.info ("select kecamatan with id {}", id);
        return lokasiMapper.selectKecamatan(id);
	}
	
	@Override
	public KotaModel selectKota(int id) {
		log.info ("select kota with id {}", id);
        return lokasiMapper.selectKota(id);
	}
	
	@Override
	public KelurahanModel selectKelurahanByName(String namaKelurahan) {
		log.info ("select kelurahan with nama {}", namaKelurahan);
		return lokasiMapper.selectKelurahanByName(namaKelurahan);
	}
	
	@Override
	public List<KelurahanModel> selectKelurahanAll(int idKecamatan){
		log.info("kelurahan semua" );
		return lokasiMapper.selectKelurahanAll(idKecamatan);
	}
	
	@Override
	public List<KecamatanModel> selectKecamatanAll(int idKota){
		log.info("kecamatan semua" );
		return lokasiMapper.selectKecamatanAll(idKota);
	}
	
	@Override
	public List<KotaModel> selectKotaAll(){
		log.info("kota semua" );
		return lokasiMapper.selectKotaAll();
	}

}
