package com.example.tugas1_1506689622.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.tugas1_1506689622.model.PendudukModel;

@Mapper
public interface PendudukMapper {
	@Select("select * from penduduk where nik = #{nik}")
	@Results(value = {
			@Result(property="nik", column="nik"),
			@Result(property="nama", column="nama"),
			@Result(property="tempatLahir", column="tempat_lahir"),
			@Result(property="tanggalLahir", column="tanggal_lahir"),
			@Result(property="jenisKelamin", column="jenis_kelamin"),
			@Result(property="isWni", column="is_wni"),
			@Result(property="idKeluarga", column="id_keluarga"),
			@Result(property="agama", column="agama"),
			@Result(property="pekerjaan", column="pekerjaan"),
			@Result(property="statusPerkawinan", column="status_perkawinan"),
			@Result(property="statusDalamKeluarga", column="status_dalam_keluarga"),
			@Result(property="golonganDarah", column="golongan_darah"),
			@Result(property="isWafat", column="is_wafat")
	})
	PendudukModel selectPenduduk(@Param("nik") String nik);
	
	@Insert("INSERT INTO penduduk (id,nik,nama,tempat_lahir,tanggal_lahir,jenis_kelamin,is_wni,id_keluarga,"
			+ "agama,pekerjaan,status_perkawinan,status_dalam_keluarga,golongan_darah,is_wafat) "
			+ "VALUES (#{id},#{nik},#{nama},#{tempatLahir},#{tanggalLahir},#{jenisKelamin},#{isWni},#{idKeluarga},"
			+ "#{agama},#{pekerjaan},#{statusPerkawinan},#{statusDalamKeluarga},#{golonganDarah},#{isWafat})")
	void addPenduduk(PendudukModel penduduk);
	
	/*Mendapatkan id penduduk terbesar*/
	@Select("select max(id) from penduduk")
	int idMax();
	
	/*Mencari jumlah penduduk berdasarkan tanggal lahir dan jenis kelamin serta domisili*/
	@Select("select count(*) from penduduk where tanggal_lahir = #{tanggalLahir} "
			+ "and jenis_kelamin = #{jenisKelamin} and nik like #{nik}")
	int jumlahPendudukBerdasarkanTanggalLahirdanKelamin(@Param("tanggalLahir") String tanggalLahir, 
			@Param("jenisKelamin") int jenisKelamin, @Param("nik") String nik);
	
	@Update("update penduduk set nik=#{nik}, nama=#{nama}, tempat_lahir=#{tempatLahir}, tanggal_lahir=#{tanggalLahir}, "
			+ "jenis_kelamin=#{jenisKelamin}, is_wni = #{isWni}, id_keluarga=#{idKeluarga}, "
			+ "agama=#{agama}, pekerjaan=#{pekerjaan}, status_perkawinan=#{statusPerkawinan},"
			+ "status_dalam_keluarga=#{statusDalamKeluarga}, golongan_darah=#{golonganDarah}, "
			+ "is_wafat=#{isWafat} where id=#{id}")
	void updatePenduduk (PendudukModel penduduk);
	
}
