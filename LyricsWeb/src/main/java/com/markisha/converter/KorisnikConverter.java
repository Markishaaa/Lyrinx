package com.markisha.converter;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import com.markisha.repository.KorisnikRepository;

import model.Korisnik;

public class KorisnikConverter implements Converter<String, Korisnik> {

	KorisnikRepository kr;

	public KorisnikConverter(KorisnikRepository kr) {
		this.kr = kr;
	}

	@Override
	public Korisnik convert(String source) {
		int idKorisnika = -1;
		try {

			idKorisnika = Integer.parseInt(source);

		} catch (NumberFormatException e) {
			throw new ConversionFailedException(TypeDescriptor.valueOf(String.class),
					TypeDescriptor.valueOf(Korisnik.class), idKorisnika, null);
		}

		Korisnik k = kr.findById(idKorisnika).get();

		return k;
	}

}
