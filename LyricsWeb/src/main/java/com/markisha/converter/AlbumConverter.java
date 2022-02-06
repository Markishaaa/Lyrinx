package com.markisha.converter;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import com.markisha.repository.AlbumRepository;

import model.Album;

public class AlbumConverter implements Converter<String, Album> {

	AlbumRepository ar;

	public AlbumConverter(AlbumRepository ar) {
		this.ar = ar;
	}

	@Override
	public Album convert(String source) {
		int idAlbuma = -1;
		try {

			idAlbuma = Integer.parseInt(source);

		} catch (NumberFormatException e) {
			throw new ConversionFailedException(TypeDescriptor.valueOf(String.class),
					TypeDescriptor.valueOf(Album.class), idAlbuma, null);
		}

		Album a = ar.findById(idAlbuma).get();

		return a;
	}
	
}
