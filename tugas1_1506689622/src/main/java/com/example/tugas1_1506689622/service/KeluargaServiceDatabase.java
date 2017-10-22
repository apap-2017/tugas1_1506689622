package com.example.tugas1_1506689622.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1_1506689622.dao.KeluargaMapper;
import com.example.tugas1_1506689622.model.KeluargaModel;
import com.example.tugas1_1506689622.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService {
	@Autowired
	private KeluargaMapper keluargaMapper;
	
	@Override
	public KeluargaModel selectKeluarga(int id) {
		log.info ("select keluarga with id {}", id);
        return keluargaMapper.selectKeluarga(id);
	}
	
	@Override
	public int selectIdKeluarga(String nkk) {
		log.info ("select id keluarga with nkk {}", nkk);
		return keluargaMapper.selectIdKeluarga(nkk);
	}
	
	@Override
	public void addKeluarga(KeluargaModel keluarga) {
		log.info("Keluarga ditambahkan");
		keluargaMapper.addKeluarga(keluarga);
	}
	
	@Override
	public int idNextKeluarga() {
		int idMax = keluargaMapper.idMaxKeluarga();
		log.info ("id max {}", idMax);
		int returnedID = idMax + 1;
		return returnedID;
	}
	
	@Override
	public int jumlahKeluargaBerdasarkanDomisilidanTanggal(String nkk) {
		int jumlah = keluargaMapper.jumlahKeluargaBerdasarkanDomisilidanTanggal(nkk);
		log.info("jumlah keluarga yang ada berdasarkan domisili dan tanggal {}", jumlah);
		return jumlah;
	}
	
	@Override
	public void updateKeluarga(KeluargaModel keluarga) {
		keluargaMapper.updateKeluarga(keluarga);
		log.info("keluarga dengan nkk " + keluarga.getNomorKk() + " dan dengan id " + keluarga.getId() + " updated");
	}
	
	@Override
	public List<PendudukModel> selectAnggotaKeluarga(int id){
		return keluargaMapper.selectAnggotaKeluarga(id);
	}
}
