package com.markisha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Izvodjac;

public interface IzvodjacRepository extends JpaRepository<Izvodjac, Integer> {

	public Izvodjac findByImeIzvodjaca(String ime);
	
}
