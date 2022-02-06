package com.markisha.converter;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import com.markisha.repository.PesmaRepository;

import model.Pesma;

public class PesmaConverter implements Converter<String, Pesma> {

	PesmaRepository pr;

	public PesmaConverter(PesmaRepository pr) {
		this.pr = pr;
	}

	@Override
	public Pesma convert(String source) {
		int idPesme = -1;
		try {

			idPesme = Integer.parseInt(source);

		} catch (NumberFormatException e) {
			throw new ConversionFailedException(TypeDescriptor.valueOf(String.class),
					TypeDescriptor.valueOf(Pesma.class), idPesme, null);
		}

		Pesma a = pr.findById(idPesme).get();

		return a;
	}
	
}
