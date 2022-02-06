package com.markisha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

	public Album findByNazivAlbuma(String naziv);
	
}
