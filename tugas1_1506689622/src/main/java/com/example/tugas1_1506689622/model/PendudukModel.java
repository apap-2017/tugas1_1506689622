package com.example.tugas1_1506689622.model;

import javax.validation.constraints.NotNull;

import com.example.tugas1_1506689622.service.PendudukServiceDatabase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendudukModel {
	@NotNull
	private int id;
	@NotNull
	private String nik;
	@NotNull
	private String nama;
	@NotNull
	private String tempatLahir;
	@NotNull
	private String tanggalLahir;
	@NotNull
	private int jenisKelamin;
	private String jenisKelaminString;
	@NotNull
	private int isWni;
	@NotNull
	private int idKeluarga;
	@NotNull
	private String agama;
	@NotNull
	private String pekerjaan;
	@NotNull
	private String statusPerkawinan;
	@NotNull
	private String statusDalamKeluarga;
	@NotNull
	private String golonganDarah;
	@NotNull
	private int isWafat;
	
	/*Memperoleh String dari isWafat dan kewarganegaraan*/
	private String kewarganegaraan;
	private String statusKematian;
	
	public void newPendudukModel (int id, String nik, String nama, String tempatLahir, String tanggalLahir, 
			String jenisKelaminString, String kewarganegaraan, int idKeluarga, String agama, 
			String pekerjaan, String statusPerkawinan, String statusDalamKeluarga, String golonganDarah,
			String statusKematian) {
			this.id = id;
			this.nik = nik;
			this.nama = nama;
			this.tempatLahir = tempatLahir;
			this.tanggalLahir = tanggalLahir;
			jenisKelaminStringToInteger(jenisKelaminString);
			kewarganegaraanToInteger(kewarganegaraan);
			this.idKeluarga = idKeluarga;
			this.agama = agama;
			this.pekerjaan = pekerjaan;
			this.statusPerkawinan = statusPerkawinan;
			this.statusDalamKeluarga = statusDalamKeluarga;
			this.golonganDarah = golonganDarah;
			statusKematianStringToInteger(statusKematian);		
	}
	
	/*Untuk membantu menampilkan data dari database dalam format String di view*/
	
	public String getKewarganegaraan() {
		if(this.isWni == 0) {
			return this.kewarganegaraan = "WNA";
		}else {
			return this.kewarganegaraan = "WNI";
		}
	}
	public String getStatusKematian() {
		if(this.isWafat == 0) {
			return this.statusKematian = "Hidup";
		}else {
			return this.statusKematian = "Wafat";
		}
	}
	public String getJenisKelaminString() {
		if(this.jenisKelamin == 0) {
			return this.jenisKelaminString = "Pria";
		}else {
			return this.jenisKelaminString = "Wanita";
		}
	}
	
	
	/*Untuk menambahkan PendudukModel sesuai format di database*/
	public String generateNIKTemp(String kodeKecamatan, String dateLahir, String kelamin) {
		String subKodeKec = kodeKecamatan.substring(0,6);
		String getHari = dateLahir.substring(8,10);
		String getBulan = dateLahir.substring(5,7);
		String getTahun = dateLahir.substring(2,4);
		int hari = Integer.parseInt(getHari);
		if(kelamin.equals("Pria")) {
			hari = hari + 0;
		}else {
			hari = hari + 40;
		}
		if(hari < 10) {
			String tempHari = Integer.toString(hari);
			getHari = "0" + tempHari;
		}else {
			getHari = Integer.toString(hari);
		}
		String nikStr = subKodeKec + getHari + getBulan + getTahun;
		return nikStr;
	}
	
	
	public String generateNIK(String nikTemp, int jumlahYangAda) {
		jumlahYangAda += 1;
		String jml = "";
		if(jumlahYangAda < 10 ) {
			jml = "000" + Integer.toString(jumlahYangAda);
		}else {
			if(jumlahYangAda < 100) {
				jml = "00" + Integer.toString(jumlahYangAda);
			}else if(jumlahYangAda < 1000) {
				jml = "0" + Integer.toString(jumlahYangAda);
			}else {
				jml = Integer.toString(jumlahYangAda);
			}
		}
		
		String nikStr = nikTemp + jml;
		return this.nik = nikStr;
	}
	
	public void jenisKelaminStringToInteger(String jenisKelaminString) {
		if(jenisKelaminString.equals("Pria")) {
			this.jenisKelamin = 0;
		}else {
			this.jenisKelamin = 1;
		}
	}
	
	public void statusKematianStringToInteger(String statusKematianString) {
		if(statusKematianString.equals("Hidup")) {
			this.isWafat = 0;
		}else {
			this.isWafat = 1;
		}
	}
	
	public void kewarganegaraanToInteger(String kewarganegaraanString) {
		if(kewarganegaraanString.equals("WNA")) {
			this.isWni = 0;
		}else {
			this.isWni = 1;
		}
	}
	
}
