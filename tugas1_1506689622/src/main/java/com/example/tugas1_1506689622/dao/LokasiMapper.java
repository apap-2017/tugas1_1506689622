package com.example.tugas1_1506689622.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.tugas1_1506689622.model.KecamatanModel;
import com.example.tugas1_1506689622.model.KelurahanModel;
import com.example.tugas1_1506689622.model.KotaModel;

@Mapper
public interface LokasiMapper {
	@Select("select * from kelurahan where id = #{id}")
	@Results(value = {
			@Result(property="idKecamatan", column="id_kecamatan"),
			@Result(property="kodeKelurahan", column="kode_kelurahan"),
			@Result(property="namaKelurahan", column="nama_kelurahan"),
			@Result(property="kodePos", column="kode_pos")
	})
	KelurahanModel selectKelurahan(@Param("id") int id);
	
	@Select("select * from kecamatan where id = #{id}")
	@Results(value = {
			@Result(property="idKota", column="id_kota"),
			@Result(property="kodeKecamatan", column="kode_kecamatan"),
			@Result(property="namaKecamatan", column="nama_kecamatan"),
	})
	KecamatanModel selectKecamatan(@Param("id") int id);
	
	@Select("select * from kota where id = #{id}")
	@Results(value = {
			@Result(property="kodeKota", column="kode_kota"),
			@Result(property="namaKota", column="nama_kota"),
	})
	KotaModel selectKota(@Param("id") int id);

	@Select("select * from kelurahan where nama_kelurahan = #{namaKelurahan}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="idKecamatan", column="id_kecamatan"),
			@Result(property="kodeKelurahan", column="kode_kelurahan"),
			@Result(property="namaKelurahan", column="nama_kelurahan"),
			@Result(property="kodePos", column="kode_pos")
	})
	KelurahanModel selectKelurahanByName(@Param("namaKelurahan") String namaKelurahan);
	
	@Select("select * from kelurahan where id_kecamatan = #{idKecamatan}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="idKecamatan", column="id_kecamatan"),
			@Result(property="kodeKelurahan", column="kode_kelurahan"),
			@Result(property="namaKelurahan", column="nama_kelurahan"),
			@Result(property="kodePos", column="kode_pos")
	})
	List<KelurahanModel> selectKelurahanAll(@Param("idKecamatan") int idKecamatan);
	
	@Select("select * from kecamatan where id_kota = #{idKota}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="idKota", column="id_kota"),
			@Result(property="kodeKecamatan", column="kode_kecamatan"),
			@Result(property="namaKecamatan", column="nama_kecamatan")
	})
	List<KecamatanModel> selectKecamatanAll(@Param("idKota") int idKota);
	
	@Select("select * from kota")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="kodeKota", column="kode_kota"),
			@Result(property="namaKota", column="nama_kota")
	})
	List<KotaModel> selectKotaAll();
}
