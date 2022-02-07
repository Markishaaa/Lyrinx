package com.markisha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Izvodjac;
import model.Pesma;

public interface PesmaRepository extends JpaRepository<Pesma, Integer> {

	public Pesma findByImePesme(String ime);
	
	public Pesma findByImePesmeIgnoreCaseAndIzvodjac(String ime, Izvodjac izvodjac);
	
}
