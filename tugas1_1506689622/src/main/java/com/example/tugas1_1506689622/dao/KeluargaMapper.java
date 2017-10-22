package com.example.tugas1_1506689622.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.tugas1_1506689622.model.KeluargaModel;
import com.example.tugas1_1506689622.model.PendudukModel;

@Mapper
public interface KeluargaMapper {
	@Select("select * from keluarga where id = #{id}")
	@Results(value = {
			@Result(property="nomorKk", column="nomor_kk"),
			@Result(property="alamat", column="alamat"),
			@Result(property="rt", column="rt"),
			@Result(property="rw", column="rw"),
			@Result(property="idKelurahan", column="id_kelurahan"),
			@Result(property="isTidakBerlaku", column="is_tidak_berlaku"),
			@Result(property="anggotaKeluarga", column="id",
				javaType = List.class,
				many=@Many(select="selectAnggotaKeluarga"))
	})
	KeluargaModel selectKeluarga(@Param("id") int id);
	
	@Select("select id from keluarga where nomor_kk = #{nkk}")
	int selectIdKeluarga(@Param("nkk") String nkk);
	
	@Select("select * from penduduk where id_keluarga = #{id}")
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
	List<PendudukModel> selectAnggotaKeluarga(@Param("id") int id);
	
	@Insert("INSERT INTO keluarga (id,nomor_kk,alamat,RT,RW,id_kelurahan,is_tidak_berlaku) "
			+ "VALUES (#{id},#{nomorKk},#{alamat},#{rt},#{rw},#{idKelurahan},#{isTidakBerlaku})")
	void addKeluarga(KeluargaModel keluarga);
	
	/*Mendapatkan id keluarga terbesar*/
	@Select("select max(id) from keluarga")
	int idMaxKeluarga();
	
	/*Mencari jumlah keluarga dengan kriteria domisili dan tanggal*/
	@Select("select count(*) from keluarga where nomor_kk like #{nkk}")
	int jumlahKeluargaBerdasarkanDomisilidanTanggal(@Param("nkk") String nkk);
	
	@Update("update keluarga set nomor_kk=#{nomorKk}, alamat=#{alamat}, rt=#{rt}, rw=#{rw}, "
			+ "id_kelurahan=#{idKelurahan}, is_tidak_berlaku=#{isTidakBerlaku} where id=#{id}")
	void updateKeluarga (KeluargaModel keluarga);
}
