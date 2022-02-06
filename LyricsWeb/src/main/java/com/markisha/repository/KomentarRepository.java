package com.markisha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Komentar;
import model.Pesma;

public interface KomentarRepository extends JpaRepository<Komentar, Integer> {

	public List<Komentar> findAllByPesma(Pesma p);
	
}
