package com.example.tugas1_1506689622.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeluargaModel {
	@NotNull
	private int id;
	@NotNull
	private String nomorKk;
	@NotNull
	private String alamat;
	@NotNull
	private String rt;
	@NotNull
	private String rw;
	@NotNull
	private int idKelurahan;
	@NotNull
	private int isTidakBerlaku;
	List<PendudukModel> anggotaKeluarga;
	
	//Berikut Extra Atribute untuk memudahkan display
	private String namaKelurahan;
	private String namaKecamatan;
	private String namaKota;
	
	public void newKeluargaModel(int id, String nomorKk, String alamat, String rt, String rw, int idKelurahan, int isTidakBerlaku) {
		this.id = id;
		this.nomorKk = nomorKk;
		this.alamat = alamat;
		this. rt = rt;
		this.rw = rw;
		this.idKelurahan = idKelurahan;
		this.isTidakBerlaku = isTidakBerlaku;
	}
	
	public String generateNKKTemp(String kodeKelurahan) {
		String subKodeKelurahan = kodeKelurahan.substring(0,6);
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + 1; // karena bulan dimulai dari 0
        int year = calendar.get(Calendar.YEAR);
        String dayStr = "";
        String monthStr = "";
        String yearStr = "";
        if(day < 10) {
        	dayStr = "0" + Integer.toString(day);
        }else {
        	dayStr = Integer.toString(day);
        }
        if(month < 10) {
        	monthStr = "0" + Integer.toString(month);
        }else {
        	monthStr = Integer.toString(month);
        }
        yearStr = Integer.toString(year).substring(2,4);
        String nkkTemp = subKodeKelurahan + dayStr + monthStr + yearStr;
		return nkkTemp;
	}
	
	public String generateFullNKK (String nkkTemp, int jumlahYangAda) {
		String nkk = "";
		String jmlStr = "";
		jumlahYangAda += 1;
		if(jumlahYangAda < 10 ) {
			jmlStr = "000" + Integer.toString(jumlahYangAda);
		}else {
			if(jumlahYangAda < 100) {
				jmlStr = "00" + Integer.toString(jumlahYangAda);
			}else if(jumlahYangAda < 1000) {
				jmlStr = "0" + Integer.toString(jumlahYangAda);
			}else {
				jmlStr = Integer.toString(jumlahYangAda);
			}
		}
		nkk = nkkTemp + jmlStr;
		return nkk;
	}
}
