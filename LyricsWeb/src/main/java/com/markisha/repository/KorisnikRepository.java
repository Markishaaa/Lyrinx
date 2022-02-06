package com.markisha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {

	public Korisnik findByUsername(String username);
	
}
