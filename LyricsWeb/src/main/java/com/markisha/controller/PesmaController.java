package com.markisha.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.markisha.repository.AlbumRepository;
import com.markisha.repository.IzvodjacRepository;
import com.markisha.repository.KorisnikRepository;
import com.markisha.repository.PesmaRepository;

import model.Album;
import model.Izvodjac;
import model.Pesma;
import model.Zahtev;

@Controller
@RequestMapping(value = "/pesme")
public class PesmaController {

	@Autowired
	PesmaRepository pr;
	@Autowired
	IzvodjacRepository ir;
	@Autowired
	AlbumRepository ar;
	@Autowired
	KorisnikRepository kr;
	
	//@AdminAuth
	@RequestMapping(value = "/dodajPesmu", method = RequestMethod.POST)
	public String dodajPesmu(String ime, int strofa, Integer idIzvodjaca, Integer idAlbuma, 
			String tekst, Model m, HttpServletRequest request) {
		try {

			Pesma p = new Pesma();
			p.setImePesme(ime);

			Izvodjac izv = ir.findById(idIzvodjaca).get();
			p.setIzvodjac(izv);
			p.setStrofa(strofa);

			if (idAlbuma != -1) {
				Album a = ar.findById(idAlbuma).get();
				p.setAlbum(a);
			}

			tekst = "[Verse 1]\r\n" + tekst;
			for (int i = 2; i <= strofa; i++) {
				tekst = tekst.replaceFirst("\r\n\r\n", "\n\r\n[Verse " + i + "]\r\n");
			}
			p.setTekst(tekst);
			
			Pesma pesma = pr.save(p);
			m.addAttribute("novaPesma", pesma);
			
		} catch (Exception e) {
			m.addAttribute("greska", "An error has occured.");
		}

		return "jsp/unos/UnosPesme";
	}

	// svima dozvoljeno
	@RequestMapping(value = "/nadjiPesmu", method = RequestMethod.GET)
	public String nadjiPesmu(HttpServletRequest request) {
		String p = request.getParameter("pesma");
		Pesma pesma = pr.findByImePesme(p);

		request.getSession().setAttribute("pesma", pesma);

		return "jsp/pregled/PregledPesme";
	}

	// svima dozvoljeno
	@RequestMapping(value = "/getPesmeBezAlbuma", method = RequestMethod.GET)
	public String getPesmeBezAlbuma(Model m) {
		List<Pesma> pesme = pr.findAll();
		List<Pesma> bezAlbuma = new ArrayList<>();

		for (Pesma p : pesme) {
			if (p.getAlbum() == null) {
				bezAlbuma.add(p);
			}
		}

		m.addAttribute("pesme", bezAlbuma);

		return "jsp/unos/UnosPesmeUAlbum";
	}

	// svima dozvoljeno
	@RequestMapping(value = "/nadjiPesme", method = RequestMethod.GET)
	public String nadjiPesme(String pretraga, HttpServletRequest request) {
		if (pretraga == null || pretraga == "")
			return "index";

		pretraga = pretraga.trim();
		List<Pesma> pesme = pr.findAll();
		List<Pesma> nadjenePesme = new ArrayList<>();

		String imeSpojeno = pretraga.replaceAll(" ", "").toLowerCase();

		for (Pesma p : pesme) {
			String pSpojeno = p.getImePesme().replaceAll(" ", "").toLowerCase();
			if (pSpojeno.trim().contains(imeSpojeno.trim())) {
				nadjenePesme.add(p);
			}
		}

		request.getSession().setAttribute("nadjenePesme", nadjenePesme);
		request.getSession().setAttribute("pretragaCon", pretraga);

		return "redirect:/izvodjaci/nadjiIzvodjace";
	}
	
	//@ModeratorAuth
	@RequestMapping(value = "/dodajZahtevKaoPesmu", method = RequestMethod.GET)
	public String dodajZahtevKaoPesmu(HttpServletRequest request, Model m) {
		try {
			
			Zahtev z = (Zahtev) request.getSession().getAttribute("zahtevOdobren");
			
			Pesma p = new Pesma();
			p.setImePesme(z.getImePesme());
			p.setIzvodjac(z.getIzvodjac());
			p.setAlbum(z.getAlbum());
			p.setStrofa(z.getStrofa());
			p.setTekst(z.getTekst());
			
			pr.save(p);
			m.addAttribute("poruka", "Request approved. Song added successfully.");
			
		} catch (Exception e) {
			m.addAttribute("greskaPesmaZah", "There has been an error.");
			e.printStackTrace();
		}
		
		return "jsp/pregled/pregledZahteva/PregledZahteva";
	}
	
	//@ModeratorAuth
	@RequestMapping(value = "/dodajZahtevKaoEdit", method = RequestMethod.GET)
	public String dodajZahtevKaoEdit(HttpServletRequest request, Model m) {
		try {
			
			Zahtev z = (Zahtev) request.getSession().getAttribute("zahtevOdobren");
			Pesma p = z.getPesma();
			
			p.setTekst(z.getTekst());
			
			pr.save(p);
			m.addAttribute("poruka", "Request approved. Song edited successfully.");
			
		} catch (Exception e) {
			m.addAttribute("greskaPesmaZah", "There has been an error.");
			e.printStackTrace();
		}
		
		return "jsp/pregled/pregledZahteva/PregledZahteva";
	}
	
	@RequestMapping(value = "/nadjiNovePesme", method = RequestMethod.GET)
	public String nadjiNovePesme(HttpServletRequest request) {
		try {
			
			List<Pesma> pesme = pr.findAll();
			List<Pesma> najnovije = new ArrayList<Pesma>();
			
			if (pesme != null && pesme.size() > 0) {
				Collections.reverse(pesme);
				int br = 0;
				for (int i = 0; i < pesme.size(); i++) {
					najnovije.add(pesme.get(i));
					
					br++;
					if (br == 10)
						break;
				}
				
				request.getSession().setAttribute("najnovije", najnovije);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/obrisiPesmu", method = RequestMethod.POST)
	public String obrisiPesmu(String ime, String izvodjac, Model m) {
		try {

			Izvodjac i = ir.findByImeIzvodjacaIgnoreCase(izvodjac);
			Pesma p = pr.findByImePesmeIgnoreCaseAndIzvodjac(ime, i);
			
			pr.deleteById(p.getIdPesme());
			m.addAttribute("obrisiPesmu", "Song " + ime + " successfully deleted.");
		
		} catch (Exception e) {
			m.addAttribute("obrisiPesmu", "Something went wrong. Check for spelling errors.");
			e.printStackTrace();
		}
		
		return "jsp/brisanje/BrisanjePesme";
	}

}
