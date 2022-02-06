package com.markisha.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.markisha.repository.AlbumRepository;
import com.markisha.repository.IzvodjacRepository;
import com.markisha.repository.KorisnikRepository;
import com.markisha.repository.ZahtevRepository;
import com.markisha.annotations.KorisnikAuth;
import com.markisha.annotations.ModeratorAuth;
import com.markisha.enums.Zahtevi;

import model.Album;
import model.Izvodjac;
import model.Korisnik;
import model.Pesma;
import model.Zahtev;

@Controller
@RequestMapping(value = "/zahtevi")
public class ZahtevController {

	@Autowired
	ZahtevRepository zr;
	@Autowired
	IzvodjacRepository ir;
	@Autowired
	AlbumRepository ar;
	@Autowired
	KorisnikRepository kr;

	private void zahtev(Zahtevi tip, Zahtev z, Model m, HttpServletRequest request) {
		tip = (Zahtevi) request.getSession().getAttribute("tipZahteva");
		z.setTipZahteva(tip.toString());

		Korisnik k = kr.findByUsername((String) request.getSession().getAttribute("username"));
		request.getSession().setAttribute("korisnik", k);
		z.setKorisnik(k);
		z.setOdobrenje((byte) -1);

		Zahtev zahtev = zr.save(z);

		m.addAttribute("zahtev", zahtev);
	}

	@KorisnikAuth
	@RequestMapping(value = "/zahtevPesma", method = RequestMethod.POST)
	public String zahtevPesma(String ime, int strofa, Integer idIzvodjaca, Integer idAlbuma, String tekst, Model m,
			HttpServletRequest request) {
		try {

			Zahtev z = new Zahtev();
			z.setImePesme(ime);

			Izvodjac izv = ir.findById(idIzvodjaca).get();
			z.setIzvodjac(izv);
			z.setStrofa(strofa);

			if (idAlbuma != -1) {
				Album a = ar.findById(idAlbuma).get();
				z.setAlbum(a);
			}

			tekst = "[Verse 1]\r\n" + tekst;
			for (int i = 2; i <= strofa; i++) {
				tekst = tekst.replaceFirst("\r\n\r\n", "\n\r\n[Verse " + i + "]\r\n");
			}
			z.setTekst(tekst);

			zahtev(Zahtevi.PESMA, z, m, request);

		} catch (Exception e) {
			m.addAttribute("greskaZahtev", "An error has occured.");
			e.printStackTrace();
		}

		m.addAttribute("uspesnoZahtev", "Request sent successfully.");
		
		return "jsp/unos/unosZahteva/UnosZahteva";
	}

	@KorisnikAuth
	@RequestMapping(value = "/zahtevAlbum", method = RequestMethod.POST)
	public String zahtevAlbum(String ime, int brPesama, Integer idIzvodjaca, Model m, HttpServletRequest request) {
		try {

			Album a = ar.findByNazivAlbuma(ime);
			if (a != null) {
				m.addAttribute("greskaZahtev", "Album already exists.");

				return "jsp/unos/unosZahteva/UnosZahteva";
			}

			Izvodjac i = ir.findById(idIzvodjaca).get();

			Zahtev z = new Zahtev();
			z.setNazivAlbuma(ime);
			z.setBrojPesama(brPesama);
			z.setIzvodjac(i);

			zahtev(Zahtevi.ALBUM, z, m, request);

		} catch (Exception e) {
			m.addAttribute("greskaZahtev", "An error has occured.");
		}

		m.addAttribute("uspesnoZahtev", "Request sent successfully.");
		
		return "jsp/unos/unosZahteva/UnosZahteva";
	}

	@KorisnikAuth
	@RequestMapping(value = "/zahtevIzvodjac", method = RequestMethod.POST)
	public String zahtevIzvodjac(String ime, Model m, HttpServletRequest request) {
		try {

			Izvodjac i = ir.findByImeIzvodjaca(ime);
			if (i != null) {
				m.addAttribute("greskaZahtev", "Artist already exists.");

				return "jsp/unos/unosZahteva/UnosZahteva";
			}

			Zahtev z = new Zahtev();
			z.setImeIzvodjaca(ime);

			zahtev(Zahtevi.IZVODJAC, z, m, request);

		} catch (Exception e) {
			m.addAttribute("greskaZahtev", "An error has occured.");
			e.printStackTrace();
		}

		m.addAttribute("uspesnoZahtev", "Request sent successfully.");
		
		return "jsp/unos/unosZahteva/UnosZahteva";
	}

	@KorisnikAuth
	@RequestMapping(value = "/getZahtevi", method = RequestMethod.GET)
	public String getZahtevi(Model m) {
		m.addAttribute("zahtevi", Zahtevi.values());

		return "jsp/unos/unosZahteva/UnosTipaZahteva";
	}
	
	@KorisnikAuth
	@RequestMapping(value = "/zahtevEditPesme", method = RequestMethod.POST)
	public String zahtevEditPesme(String tekst, HttpServletRequest request, Model m) {
		try {
		
		Zahtev z = new Zahtev();
		z.setTekst(tekst);
		z.setTipZahteva("EDIT");
		
		Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");
		if (k == null) {
			k = kr.findByUsername((String) request.getSession().getAttribute("username"));
			request.getSession().setAttribute("korisnik", k);
		}
		
		Pesma p = (Pesma) request.getSession().getAttribute("pesma");

		z.setKorisnik(k);
		z.setOdobrenje((byte) -1);
		z.setPesma(p);
		
		zr.save(z);
		
		request.getSession().setAttribute("editUspesno", "Request sent.");
		request.getSession().setAttribute("greskaEditZahtev", null);
		
		} catch (Exception e) {
			request.getSession().setAttribute("greskaEditZahtev", "An error has occured.");
			request.getSession().setAttribute("editUspesno", null);
			e.printStackTrace();
		}
		
		Pesma p = (Pesma) request.getSession().getAttribute("pesma");
		
		return "redirect:/pesme/nadjiPesmu?pesma=" + p.getImePesme();
	}

	@KorisnikAuth
	@RequestMapping(value = "/getTipZahteva", method = RequestMethod.GET)
	public String getTipZahteva(Zahtevi tipZahteva, HttpServletRequest request) {
		request.getSession().setAttribute("tipZahteva", tipZahteva);

		if (tipZahteva.equals(Zahtevi.ALBUM) || tipZahteva.equals(Zahtevi.IZVODJAC)) {
			List<Izvodjac> izvodjaci = ir.findAll();
			request.getSession().setAttribute("izvodjaci", izvodjaci);
		} else if (tipZahteva.equals(Zahtevi.PESMA)) {
			List<Izvodjac> izvodjaci = ir.findAll();
			request.getSession().setAttribute("izvodjaci", izvodjaci);
			List<Album> albumi = ar.findAll();
			request.getSession().setAttribute("albumi", albumi);
		}
		
		return "jsp/unos/unosZahteva/UnosZahteva";
	}

	@ModeratorAuth
	@RequestMapping(value = "/getRandomZahtev", method = RequestMethod.GET)
	public String getRandomZahtev(HttpServletRequest request) {
		List<Zahtev> zahtevi = zr.nadjiNeocenjene();

		Random rand = new Random();
		Zahtev randomZahtev = null;
		
		if (zahtevi.size() > 0)
			randomZahtev = zahtevi.get(rand.nextInt(zahtevi.size()));

		request.getSession().setAttribute("randomZahtev", randomZahtev);

		return "jsp/pregled/pregledZahteva/PregledZahteva";
	}

	@ModeratorAuth
	@RequestMapping(value = "/odobrenjeZahteva", method = RequestMethod.POST)
	public String odobrenjeZahteva(boolean odobrenje, HttpServletRequest request, Model m) {
		try {

			Zahtev z = (Zahtev) request.getSession().getAttribute("randomZahtev");

			if (!odobrenje) {
				z.setOdobrenje((byte) 0);
				m.addAttribute("poruka", "Request refused.");
			} else {
				z.setOdobrenje((byte) 1);
			}
			
			zr.save(z);
			
			if (odobrenje) return "redirect:/zahtevi/dodajZahtevUBazu?z=" + z.getIdZahteva();

		} catch (Exception e) {
			m.addAttribute("greska", "There has been an error.");
			e.printStackTrace();
		}

		return "jsp/pregled/pregledZahteva/PregledZahteva";
	}
	
	@ModeratorAuth
	@RequestMapping(value = "/dodajZahtevUBazu", method = RequestMethod.GET)
	public String dodajZahtevUBazu(Model m, HttpServletRequest request) {
		try {
			
			int zId = Integer.parseInt(request.getParameter("z"));
			Zahtev z = zr.findById(zId).get();
			request.getSession().setAttribute("zahtevOdobren", z);
			
			if (z.getTipZahteva().equals(Zahtevi.PESMA.toString())) {
				return "redirect:/pesme/dodajZahtevKaoPesmu";
			} else if (z.getTipZahteva().equals(Zahtevi.ALBUM.toString())) {
				return "redirect:/albumi/dodajZahtevKaoAlbum";
			} else if (z.getTipZahteva().equals(Zahtevi.IZVODJAC.toString())) {
				return "redirect:/izvodjaci/dodajZahtevKaoIzvodjaca";
			} else {
				return "redirect:/pesme/dodajZahtevKaoEdit";
			}
				
		} catch (Exception e) {
			m.addAttribute("greskaZahBaza", "There has been an error.");
			e.printStackTrace();
		}
		
		return "jsp/pregled/pregledZahteva/PregledZahteva";
	}

}
