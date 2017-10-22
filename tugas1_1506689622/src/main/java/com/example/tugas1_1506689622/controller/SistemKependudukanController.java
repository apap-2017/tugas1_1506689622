package com.example.tugas1_1506689622.controller;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tugas1_1506689622.model.KecamatanModel;
import com.example.tugas1_1506689622.model.KeluargaModel;
import com.example.tugas1_1506689622.model.KelurahanModel;
import com.example.tugas1_1506689622.model.KotaModel;
import com.example.tugas1_1506689622.model.PendudukModel;
import com.example.tugas1_1506689622.service.KeluargaService;
import com.example.tugas1_1506689622.service.LokasiService;
import com.example.tugas1_1506689622.service.PendudukService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class SistemKependudukanController {
	@Autowired
	PendudukService pendudukDAO;
	@Autowired
	KeluargaService keluargaDAO;
	@Autowired
	LokasiService lokasiDAO;
	
	@RequestMapping("/")
    public String index ()
    {
        return "index";
    }
	
	//Fitur Nomor 1
	@RequestMapping("/penduduk")
	public String view(Model model, @RequestParam(value = "nik", required=false) String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		int idKeluarga = penduduk.getIdKeluarga();
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(idKeluarga);
		int idKelurahan = keluarga.getIdKelurahan();
		KelurahanModel kelurahan = lokasiDAO.selectKelurahan(idKelurahan);
		int idKecamatan = kelurahan.getIdKecamatan();
		KecamatanModel kecamatan = lokasiDAO.selectKecamatan(idKecamatan);
		int idKota = kecamatan.getIdKota();
		KotaModel kota = lokasiDAO.selectKota(idKota);
		if(penduduk == null) {
			//return "message-page";
		}
		model.addAttribute("penduduk", penduduk);
		model.addAttribute("keluarga", keluarga);
		model.addAttribute("kelurahan", kelurahan);
		model.addAttribute("kecamatan", kecamatan);
		model.addAttribute("kota", kota);
		return "view-penduduk";
		
	}
	
	//Fitur Nomor 2
	@RequestMapping("/keluarga")
	public String viewKeluarga(Model model, @RequestParam(value = "nkk", required=false) String nkk) {
		int idKeluarga = keluargaDAO.selectIdKeluarga(nkk);
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(idKeluarga);
		int idKelurahan = keluarga.getIdKelurahan();
		KelurahanModel kelurahan = lokasiDAO.selectKelurahan(idKelurahan);
		int idKecamatan = kelurahan.getIdKecamatan();
		KecamatanModel kecamatan = lokasiDAO.selectKecamatan(idKecamatan);
		int idKota = kecamatan.getIdKota();
		KotaModel kota = lokasiDAO.selectKota(idKota);
		if(keluarga == null) {
			//return "message-page";
		}
		model.addAttribute("keluarga", keluarga);
		model.addAttribute("kelurahan", kelurahan);
		model.addAttribute("kecamatan", kecamatan);
		model.addAttribute("kota", kota);
		return "view-keluarga";
	}
	
	//Fitur Nomor 3
	@RequestMapping(value = "/penduduk/tambah")
	public String addPendudukModel(Model model,
			@RequestParam(value = "nama", required = false) String nama,
            @RequestParam(value = "tempatLahir", required = false) String tempatLahir,
            @RequestParam(value = "tanggalLahir", required = false) String tanggalLahir,
            @RequestParam(value = "jenisKelamin", required = false) String jenisKelamin,
            @RequestParam(value = "golonganDarah", required = false) String golonganDarah,
            @RequestParam(value = "agama", required = false) String agama,
            @RequestParam(value = "statusPerkawinan", required = false) String statusPerkawinan,
            @RequestParam(value = "pekerjaan", required = false) String pekerjaan,
            @RequestParam(value = "kewarganegaraan", required = false) String kewarganegaraan,
            @RequestParam(value = "statusKematian", required = false) String statusKematian,
            @RequestParam(value = "idKeluarga", required = false) String idKeluarga,
            @RequestParam(value = "statusDalamKeluarga", required = false) String statusDalamKeluarga
            ) {
		if(nama == null && tempatLahir == null && tanggalLahir == null && jenisKelamin == null && golonganDarah == null && agama == null
				&& statusPerkawinan == null && pekerjaan == null && kewarganegaraan == null && statusKematian == null 
				&& statusDalamKeluarga == null && idKeluarga == null) {
			return "form-add-penduduk";
		}
		PendudukModel penduduk = new PendudukModel ();
		int idKeluargaInt = Integer.parseInt(idKeluarga);
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(idKeluargaInt);
		int idKelurahan = keluarga.getIdKelurahan();
		KelurahanModel kelurahan = lokasiDAO.selectKelurahan(idKelurahan);
		int idKecamatan = kelurahan.getIdKecamatan();
		KecamatanModel kecamatan = lokasiDAO.selectKecamatan(idKecamatan);
		String kodeKecamatan = kecamatan.getKodeKecamatan(); // mendapatkan kode kecamatan
		String nikTemp = penduduk.generateNIKTemp(kodeKecamatan, tanggalLahir, jenisKelamin);
		int jumlahPendudukYangSudahAda = 
				pendudukDAO.jumlahPendudukBerdasarkanTanggalLahirdanKelamin(tanggalLahir, jenisKelamin, nikTemp+"%"); // mendapatkan jumlah penduduk berdasarkan jenis kelamin dan kesamaan tanggal lahir serta domisili
		String nik = penduduk.generateNIK(nikTemp, jumlahPendudukYangSudahAda);
		int id = pendudukDAO.idNext();
		penduduk.newPendudukModel(id, nik, nama, tempatLahir, tanggalLahir, jenisKelamin, kewarganegaraan, idKeluargaInt, agama, pekerjaan, statusPerkawinan, statusDalamKeluarga, golonganDarah, statusKematian);
		pendudukDAO.addPenduduk(penduduk);
		String messageContent = "Penduduk dengan NIK " + nik + " berhasil ditambahkan";
		model.addAttribute("msgContent", messageContent);
		model.addAttribute("msg", "Sukses !");
		model.addAttribute("msgTitle", "Sukses Menambahkan Penduduk");
		return "message-page";

	}
	
	//Fitur nomor 4
	@RequestMapping(value = "/keluarga/tambah")
	public String addKeluargaModel(Model model, 
			@RequestParam(value = "alamat", required = false) String alamat,
            @RequestParam(value = "rt", required = false) String rt,
            @RequestParam(value = "rw", required = false) String rw,
            @RequestParam(value = "kelurahan", required = false) String kelurahan,
            @RequestParam(value = "kecamatan", required = false) String kecamatan,
            @RequestParam(value = "kota", required = false) String kota) {
		if(alamat == null && rt == null && rw == null && kelurahan == null && kecamatan == null && kota == null) {
			return "form-add-keluarga";
		}
		KeluargaModel keluarga = new KeluargaModel();
		KelurahanModel kelurahanModel = lokasiDAO.selectKelurahanByName(kelurahan);
		String kodeKelurahan = kelurahanModel.getKodeKelurahan();
		int idKelurahan = kelurahanModel.getId();
		String nkkTemp = keluarga.generateNKKTemp(kodeKelurahan);
		int jumlahKeluargaYangAda = keluargaDAO.jumlahKeluargaBerdasarkanDomisilidanTanggal(nkkTemp+"%");
		int id = keluargaDAO.idNextKeluarga();
		String nkk = keluarga.generateFullNKK(nkkTemp, jumlahKeluargaYangAda);
		keluarga.newKeluargaModel(id, nkk, alamat, rt, rw, idKelurahan, 0);
		keluargaDAO.addKeluarga(keluarga);
		String messageContent = "Keluarga dengan NKK " + nkk + " berhasil ditambahkan";
		model.addAttribute("msgContent", messageContent);
		model.addAttribute("msg", "Sukses !");
		model.addAttribute("msgTitle", "Sukses Menambahkan Keluarga");
		return "message-page";
	}
	
	
	// Fitur Nomor 5
	@RequestMapping("/penduduk/ubah/{nik}")
	public String updatePenduduk(Model model, @PathVariable(value = "nik") String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		if(penduduk != null) {
			//log.info("namanya " + penduduk.getNama());
			model.addAttribute("penduduk", penduduk);
			return "form-update-penduduk";
		}else {
			model.addAttribute("msgContent", "Penduduk dengan NIK " + nik + " tidak ditemukan");
			model.addAttribute("msg", "Penduduk Tidak Ditemukan");
			model.addAttribute("msgTitle", "Tidak Ditemukan");
			return "message-page";
		}
	} 
	
	@RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.POST)
	public String updateSubmitPenduduk(Model model, @PathVariable(value = "nik") String oldNik, 
									@RequestParam(value = "jenisKelaminString", required = false) String jenisKelaminString, 
									@RequestParam(value = "kewarganegaraan", required = false) String kewarganegaraan, 
									@RequestParam(value = "statusKematian", required = false) String statusKematian, 
									@ModelAttribute PendudukModel penduduk) {
		PendudukModel oldPenduduk = pendudukDAO.selectPenduduk(oldNik);
		String nikOld = oldPenduduk.getNik();
		
		String tanggalLahirOld = oldPenduduk.getTanggalLahir();
		log.info("tgl lahir old" + tanggalLahirOld);
		String jenisKelaminOld = oldPenduduk.getJenisKelaminString();
		log.info("jenis kelamin Old " + jenisKelaminOld);
		int idKeluargaOld = oldPenduduk.getIdKeluarga();
		KeluargaModel keluargaOld = keluargaDAO.selectKeluarga(idKeluargaOld);
		int idKelurahanOld = keluargaOld.getIdKelurahan();
		KelurahanModel kelurahanOld = lokasiDAO.selectKelurahan(idKelurahanOld);
		int idKecamatanOld = kelurahanOld.getIdKecamatan();
		KecamatanModel kecamatanOld = lokasiDAO.selectKecamatan(idKecamatanOld);
		String kodeKecamatanOld = kecamatanOld.getKodeKecamatan(); // mendapatkan kode kecamatan
		String nikTempOld = oldPenduduk.generateNIKTemp(kodeKecamatanOld, tanggalLahirOld, jenisKelaminOld);
		log.info("nikTempOld " + nikTempOld);
		
		String tanggalLahir = penduduk.getTanggalLahir();
		log.info("tgl lahir " + tanggalLahir);
		String jenisKelamin = penduduk.getJenisKelaminString();
		log.info("jenis kelamin " + jenisKelamin);
		int idKeluarga = penduduk.getIdKeluarga();
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(idKeluarga);
		int idKelurahan = keluarga.getIdKelurahan();
		KelurahanModel kelurahan = lokasiDAO.selectKelurahan(idKelurahan);
		int idKecamatan = kelurahan.getIdKecamatan();
		KecamatanModel kecamatan = lokasiDAO.selectKecamatan(idKecamatan);
		String kodeKecamatan = kecamatan.getKodeKecamatan(); // mendapatkan kode kecamatan
		String nikTemp = penduduk.generateNIKTemp(kodeKecamatan, tanggalLahir, jenisKelaminString);
		log.info("nikTemp " + nikTemp);
		log.info("Jenis Kelamin Inputan " + jenisKelaminString);
		if(!(nikTempOld.equals(nikTemp))) {
			log.info("NIK ga sama");
			int jumlahPendudukYangSudahAda = 
					pendudukDAO.jumlahPendudukBerdasarkanTanggalLahirdanKelamin(tanggalLahir, jenisKelaminString, nikTemp+"%"); // mendapatkan jumlah penduduk berdasarkan jenis kelamin dan kesamaan tanggal lahir serta domisili
			String nik = penduduk.generateNIK(nikTemp, jumlahPendudukYangSudahAda);
			penduduk.setNik(nik); // nik baru
			
			//penduduk.setJenisKelaminString(jenisKelamin);
			//penduduk.setKewarganegaraan(penduduk.getKewarganegaraan());
			//penduduk.setStatusKematian(penduduk.getStatusKematian());
			//penduduk.jenisKelaminStringToInteger(jenisKelamin); // mengubah input string menjadi int untuk menjadi attribute model yang bisa dimasukkan ke dalam db
			
			//penduduk.statusKematianStringToInteger(penduduk.getStatusKematian()); // mengubah input string menjadi int untuk menjadi attribute model yang bisa dimasukkan ke dalam db
			//log.info("status kematian " + penduduk.getStatusKematian() + " angka " + penduduk.getIsWafat());
			//penduduk.kewarganegaraanToInteger(penduduk.getKewarganegaraan()); // mengubah input string menjadi int untuk menjadi attribute model yang bisa dimasukkan ke dalam db
			//log.info("status kematian " + penduduk.getKewarganegaraan() + " angka " + penduduk.getIsWni());
		}else {
			//penduduk.statusKematianStringToInteger(penduduk.getStatusKematian()); // mengubah input string menjadi int untuk menjadi attribute model yang bisa dimasukkan ke dalam db
			//log.info("status kematian " + penduduk.getStatusKematian() + " angka " + penduduk.getIsWafat());
			//penduduk.kewarganegaraanToInteger(penduduk.getKewarganegaraan()); // mengubah input string menjadi int untuk menjadi attribute model yang bisa dimasukkan ke dalam db
			//log.info("status kematian " + penduduk.getKewarganegaraan() + " angka " + penduduk.getIsWni());
		}
		penduduk.setId(oldPenduduk.getId());
		penduduk.newPendudukModel(penduduk.getId(), penduduk.getNik(), penduduk.getNama(), penduduk.getTempatLahir(), tanggalLahir, jenisKelaminString, 
				kewarganegaraan, penduduk.getIdKeluarga(), penduduk.getAgama(), penduduk.getPekerjaan(), penduduk.getStatusPerkawinan(), 
				penduduk.getStatusDalamKeluarga(), penduduk.getGolonganDarah(), statusKematian);
		pendudukDAO.updatePenduduk(penduduk);
		model.addAttribute("msgContent", "Penduduk dengan NIK " + nikOld + " berhasil diubah");
		model.addAttribute("msg", "Sukses !");
		model.addAttribute("msgTitle", "Sukses");
		return "message-success-update";
	}
	    
	//Fitur Nomor 6
	@RequestMapping("/keluarga/ubah/{nkk}")
	public String updateKeluarga(Model model, @PathVariable(value = "nkk") String nkk) {
		int idKeluarga = keluargaDAO.selectIdKeluarga(nkk);
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(idKeluarga);
		if(keluarga != null) {
			//log.info("namanya " + penduduk.getNama());
			int idKelurahan = keluarga.getIdKelurahan();
			KelurahanModel kelurahan = lokasiDAO.selectKelurahan(idKelurahan);
			KecamatanModel kecamatan = lokasiDAO.selectKecamatan(kelurahan.getIdKecamatan());
			KotaModel kota = lokasiDAO.selectKota(kecamatan.getIdKota());
			keluarga.setNamaKelurahan(kelurahan.getNamaKelurahan());
			keluarga.setNamaKecamatan(kecamatan.getNamaKecamatan());
			keluarga.setNamaKota(kota.getNamaKota());
			model.addAttribute("keluarga", keluarga);
			return "form-update-keluarga";
		}else {
			model.addAttribute("msgContent", "Keluarga dengan NKK " + nkk + " tidak ditemukan");
			model.addAttribute("msg", "Keluarga Tidak Ditemukan");
			model.addAttribute("msgTitle", "Tidak Ditemukan");
			return "message-page";
		}
	} 
	
	@RequestMapping(value = "/keluarga/ubah/{nkk}", method = RequestMethod.POST)
	public String updateSubmiKeluarga(Model model, @PathVariable(value = "nkk") String oldNkk, @ModelAttribute KeluargaModel keluarga) {
		log.info("old Nkk" + oldNkk);
		int idKeluargaOld = keluargaDAO.selectIdKeluarga(oldNkk);
		KeluargaModel keluargaOld = keluargaDAO.selectKeluarga(idKeluargaOld);
		String nkkOld = keluargaOld.getNomorKk();
		String nkkSubOld = nkkOld.substring(0,12);
		log.info("nkk lama " + nkkSubOld);
		
		KelurahanModel kelurahanModel = lokasiDAO.selectKelurahanByName(keluarga.getNamaKelurahan());
		String kodeKelurahan = kelurahanModel.getKodeKelurahan();
		int idKelurahan = kelurahanModel.getId();
		String nkkTemp = keluarga.generateNKKTemp(kodeKelurahan);
		log.info("nkk now " + nkkTemp);
		if(!(nkkTemp.equals(nkkSubOld))) {
			int jumlahKeluargaYangAda = keluargaDAO.jumlahKeluargaBerdasarkanDomisilidanTanggal(nkkTemp+"%");
			String nkk = keluarga.generateFullNKK(nkkTemp, jumlahKeluargaYangAda);
			keluarga.setNomorKk(nkk);
			keluarga.setIdKelurahan(idKelurahan);
			log.info("nkk baru " + nkk);
			
			
			//Untuk edit NIK penduduk yang terpengaruh
			List<PendudukModel> anggotaKeluarga = keluargaDAO.selectAnggotaKeluarga(keluarga.getId());
			keluarga.setAnggotaKeluarga(anggotaKeluarga);
			String nikFromNkk = nkk.substring(0,6);
			log.info("nikFromNkk " + nikFromNkk);
			log.info("jumlah anggota var " + anggotaKeluarga.size());
			log.info("jumlah anggot " + keluarga.getAnggotaKeluarga().size());
			for(int i = 0; i < keluarga.getAnggotaKeluarga().size(); i++) {
				String tanggalLahir = keluarga.getAnggotaKeluarga().get(i).getTanggalLahir();
				String jenisKelamin = keluarga.getAnggotaKeluarga().get(i).getJenisKelaminString();
				int jumlahPendudukYangSudahAda = 
						pendudukDAO.jumlahPendudukBerdasarkanTanggalLahirdanKelamin(tanggalLahir, jenisKelamin, nikFromNkk+"%"); // mendapatkan jumlah penduduk berdasarkan jenis kelamin dan kesamaan tanggal lahir serta domisili
				String nik = keluarga.getAnggotaKeluarga().get(i).generateNIK(nikFromNkk, jumlahPendudukYangSudahAda);
				keluarga.getAnggotaKeluarga().get(i).setNik(nik); // nik baru
				keluarga.getAnggotaKeluarga().get(i).setId(anggotaKeluarga.get(i).getId());
				pendudukDAO.updatePenduduk(keluarga.getAnggotaKeluarga().get(i));
				log.info("penduduk 1 " + keluarga.getAnggotaKeluarga().get(i).getNik());
			}
		}else {
			keluarga.setNomorKk(nkkOld);
			keluarga.setIdKelurahan(idKelurahan);
		}
		keluarga.setId(idKeluargaOld);
		keluargaDAO.updateKeluarga(keluarga);
		model.addAttribute("msgContent", "Keluarga dengan NKK " + nkkOld + " berhasil diubah");
		model.addAttribute("msg", "Sukses !");
		model.addAttribute("msgTitle", "Sukses");
		return "message-success-update";
	}
	
	//Fitur Nomor 7
	@RequestMapping(value = "/penduduk/mati", method = RequestMethod.POST)
	public String pendudukWafat(Model model, @ModelAttribute PendudukModel penduduk) {
		penduduk = pendudukDAO.selectPenduduk(penduduk.getNik());
		penduduk.setId(penduduk.getId());
		//log.info("Idnya " + penduduk.getId());
		penduduk.setIsWafat(1);
		pendudukDAO.updatePenduduk(penduduk);
		//log.info("penduduk ", penduduk.getNik() + " wafat");
		model.addAttribute("msgContent", "Penduduk dengan NIK " + penduduk.getNik() + " sudah tidak aktif");
		model.addAttribute("msg", "Sukses !");
		model.addAttribute("msgTitle", "Sukses");
		return "message-success-update";
	}
	
	// Fitur Nomor 8
	@RequestMapping("/penduduk/cari")
	public String pendudukCari( Model model, 
								@RequestParam(value = "kt", required=false, defaultValue = "0") String kt,
								@RequestParam(value = "kc", required=false, defaultValue = "0") String kc,
								@RequestParam(value = "kl", required=false, defaultValue = "0") String kl) 
	{
		List<KotaModel> kotas = lokasiDAO.selectKotaAll();
		model.addAttribute("kotas", kotas);
		
		if(kt != "0") {
			log.info("kt terisi");
			int ktInt = Integer.parseInt(kt);
			List<KecamatanModel> kecamatans = lokasiDAO.selectKecamatanAll(ktInt);
			model.addAttribute("kecamatans", kecamatans);
		}
		if(kc != "0") {
			log.info("kc terisi");
			int kcInt = Integer.parseInt(kc);
			List<KelurahanModel> kelurahans = lokasiDAO.selectKelurahanAll(kcInt);
			model.addAttribute("kelurahans", kelurahans);
			
		}
		
		if(kt != "0" && kc != "0" && kl != "0") {
			
		}
		return "cari-penduduk";
	}
	
}
