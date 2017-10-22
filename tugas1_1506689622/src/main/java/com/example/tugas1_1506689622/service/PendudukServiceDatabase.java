package com.example.tugas1_1506689622.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1_1506689622.dao.PendudukMapper;
import com.example.tugas1_1506689622.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService {
	@Autowired
	private PendudukMapper pendudukMapper;
	
	@Override
	public PendudukModel selectPenduduk(String nik) {
		log.info ("select penduduk with nik {}", nik);
        return pendudukMapper.selectPenduduk(nik);
	}
	
	@Override
	public void addPenduduk(PendudukModel penduduk) {
		log.info ("penduduk ditambahkan");
		pendudukMapper.addPenduduk(penduduk);
	}
	
	@Override
	public int idNext() {
		int idMax = pendudukMapper.idMax();
		log.info ("id max {}", idMax);
		int returnedID = idMax + 1;
		return returnedID;
	}
	
	@Override
	public int jumlahPendudukBerdasarkanTanggalLahirdanKelamin(String tanggalLahir, String jenisKelamin, String nik) {
		int jenisKelaminInt = 0;
		if(jenisKelamin.equals("Pria")) {
			jenisKelaminInt = 0;
		}else {
			jenisKelaminInt = 1;
		}
		nik += "%";
		int jml = pendudukMapper.jumlahPendudukBerdasarkanTanggalLahirdanKelamin(tanggalLahir, jenisKelaminInt, nik);
		log.info ("jumlah ditemukan {}", jml);
		return jml;
		
	}
	
	@Override
	public void updatePenduduk(PendudukModel penduduk) {
		pendudukMapper.updatePenduduk(penduduk);
		log.info("penduduk dengan nik " + penduduk.getNik() + " dan dengan id " + penduduk.getId() + " updated");
	}
	

}
