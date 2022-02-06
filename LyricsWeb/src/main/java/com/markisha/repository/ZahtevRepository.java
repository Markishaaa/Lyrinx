package com.markisha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Zahtev;

public interface ZahtevRepository extends JpaRepository<Zahtev, Integer> {

	@Query("select z from Zahtev z where z.odobrenje = '-1'")
	public List<Zahtev> nadjiNeocenjene();
	
}
