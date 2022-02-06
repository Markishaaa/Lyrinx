package com.markisha.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.markisha.annotations.AdminAuth;
import com.markisha.annotations.ModeratorAuth;
import com.markisha.repository.AlbumRepository;
import com.markisha.repository.IzvodjacRepository;
import com.markisha.repository.KorisnikRepository;
import com.markisha.repository.PesmaRepository;

import model.Album;
import model.Izvodjac;
import model.Pesma;
import model.Zahtev;

@Controller
@RequestMapping(value = "/albumi")
public class AlbumController {

	@Autowired
	AlbumRepository ar;
	@Autowired
	IzvodjacRepository ir;
	@Autowired
	PesmaRepository pr;
	@Autowired
	KorisnikRepository kr;
	
	@AdminAuth
	@RequestMapping(value = "/dodajAlbum", method = RequestMethod.POST)
	public String dodajAlbum(String ime, int brPesama, Integer idIzvodjaca, Model m, HttpServletRequest request) {
		try {

			Izvodjac i = ir.findById(idIzvodjaca).get();
			Album a = new Album();
			a.setNazivAlbuma(ime);
			a.setBrojPesama(brPesama);
			a.setIzvodjac(i);

			Album album = ar.save(a);
			m.addAttribute("album", album);

		} catch (Exception e) {
			m.addAttribute("greska", "Album already exists.");
		}

		return "jsp/unos/UnosAlbuma";
	}

	@ModeratorAuth
	@RequestMapping(value = "/dodajPesmuUAlbum", method = RequestMethod.POST)
	public String dodajPesmuUAlbum(Integer idPesme, Integer idAlbuma, Model m) {
		try {
		
			Pesma p = pr.findById(idPesme).get();
			Album a = ar.findById(idAlbuma).get();
	
			if (p.getAlbum() == null) {
				p.setAlbum(a);
			}
			pr.save(p);
			
		} catch(Exception e) {
			m.addAttribute("greska", "There has been an error.");
			e.printStackTrace();
		}
		
		return "jsp/unos/UnosPesmeUAlbum";
	}

	// svima dozvoljeno
	@RequestMapping(value = "/getAlbumi", method = RequestMethod.GET)
	public String getAlbumi(HttpServletRequest request) {
		List<Album> albumi = ar.findAll();
		request.getSession().setAttribute("albumi", albumi);

		return "jsp/unos/UnosPesme";
	}

	// svima dozvoljeno
	@RequestMapping(value = "/getNePuneAlbume", method = RequestMethod.GET)
	public String getNePuneAlbume(HttpServletRequest request) {
		List<Album> albumi = ar.findAll();
		List<Album> nePuni = new ArrayList<>();
		for (Album a : albumi) {
			if (a.getBrojPesama() > a.getPesmas().size())
				nePuni.add(a);
		}

		request.getSession().setAttribute("albumi", nePuni);

		return "redirect:/pesme/getPesmeBezAlbuma";
	}

	// svima dozvoljeno
	@RequestMapping(value = "/nadjiAlbume", method = RequestMethod.GET)
	public String nadjiAlbume(HttpServletRequest request) {
		String pretraga = (String) request.getSession().getAttribute("pretragaCon");

		List<Album> albumi = ar.findAll();
		List<Album> nadjeniAlbumi = new ArrayList<>();

		for (Album a : albumi) {
			String iSpojeno = a.getNazivAlbuma().replaceAll(" ", "").toLowerCase();
			if (iSpojeno.trim().contains(pretraga.trim())) {
				nadjeniAlbumi.add(a);
			}
		}

		request.getSession().setAttribute("nadjeniAlbumi", nadjeniAlbumi);

		return "jsp/pregled/Pretraga";
	}
	
	// svima dozvoljeno
	@RequestMapping(value = "/nadjiAlbum", method = RequestMethod.GET)
	public String nadjiAlbum(HttpServletRequest request) {
		String albumStr = request.getParameter("album");
		Album a = ar.findByNazivAlbuma(albumStr);
		
		request.getSession().setAttribute("album", a);
		
		try {
			request.getSession().setAttribute("pesma", a.getPesmas().iterator().next());
		} catch (Exception e) {}
		
		return "jsp/pregled/PregledJednogAlbuma";
	}
	
	@ModeratorAuth
	@RequestMapping(value = "/dodajZahtevKaoAlbum", method = RequestMethod.GET)
	public String dodajZahtevKaoAlbum(HttpServletRequest request, Model m) {
		try {
			
			Zahtev z = (Zahtev) request.getSession().getAttribute("zahtevOdobren");
			
			Album a = new Album();
			a.setBrojPesama(z.getBrojPesama());
			a.setIzvodjac(z.getIzvodjac());
			a.setNazivAlbuma(z.getNazivAlbuma());
			
			ar.save(a);
			m.addAttribute("poruka", "Request approved. Album added successfully.");
			
		} catch (Exception e) {
			m.addAttribute("greskaPesmaZah", "There has been an error.");
			e.printStackTrace();
		}
		
		return "jsp/pregled/pregledZahteva/PregledZahteva";
	}
	
	@ModeratorAuth
	@RequestMapping(value = "/dodajSliku", method = RequestMethod.POST)
	public String dodajSliku(String url, HttpServletRequest request) {
		Album a = (Album) request.getSession().getAttribute("album");
		
		a.setSlika(url);
		
		ar.save(a);
		
		return "jsp/pregled/PregledJednogAlbuma";
	}

}
