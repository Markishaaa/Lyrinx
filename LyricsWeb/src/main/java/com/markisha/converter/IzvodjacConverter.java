package com.markisha.converter;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import com.markisha.repository.IzvodjacRepository;

import model.Izvodjac;

public class IzvodjacConverter implements Converter<String, Izvodjac> {

	IzvodjacRepository ir;

	public IzvodjacConverter(IzvodjacRepository ir) {
		this.ir = ir;
	}

	@Override
	public Izvodjac convert(String source) {
		int idIzvodjaca = -1;
		try {

			idIzvodjaca = Integer.parseInt(source);

		} catch (NumberFormatException e) {
			throw new ConversionFailedException(TypeDescriptor.valueOf(String.class),
					TypeDescriptor.valueOf(Izvodjac.class), idIzvodjaca, null);
		}

		Izvodjac i = ir.findById(idIzvodjaca).get();

		return i;
	}

}
