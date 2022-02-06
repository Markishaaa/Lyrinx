package com.markisha.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.markisha.annotations.KorisnikAuth;
import com.markisha.repository.KomentarRepository;
import com.markisha.repository.KorisnikRepository;
import com.markisha.repository.PesmaRepository;

import model.Komentar;
import model.Korisnik;
import model.Pesma;

@Controller
@RequestMapping(value = "/komentari")
public class KomentarController {

	@Autowired
	KomentarRepository komr;
	@Autowired
	KorisnikRepository kr;
	@Autowired
	PesmaRepository pr;
	
	@KorisnikAuth
	@RequestMapping(value = "/dodajKomentar", method = RequestMethod.POST)
	public String dodajKomentar(String sadrzaj, HttpServletRequest request, Model m) {
		Pesma p = (Pesma) request.getSession().getAttribute("pesma");
		try {
			
			Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");
			if (k == null) {
				k = kr.findByUsername((String) request.getSession().getAttribute("username"));
				request.getSession().setAttribute("korisnik", k);
			}
			
			Komentar kom = new Komentar();
			kom.setKorisnik(k);
			kom.setPesma(p);
			kom.setSadrzajKomentara(sadrzaj);
			kom.setUpvote(0);
			kom.setDownvote(0);
			kom.setOcenjen((byte) 0);
			
			Date datum = Calendar.getInstance().getTime();
			kom.setDatumObjavljivanja(datum);
			
			komr.save(kom);
			
		} catch (Exception e) {
			m.addAttribute("greskaKomentar", "Something went wrong.");
			e.printStackTrace();
		}
		
		return "redirect:/komentari/nadjiKomentare?pesma=" + p.getImePesme();
	}
	
	// svima dozvoljeno
	@RequestMapping(value = "/nadjiKomentare", method = RequestMethod.GET)
	public String nadjiKomentare(HttpServletRequest request) {
		String pStr = (String) request.getParameter("pesma");
		Pesma p = pr.findByImePesme(pStr);
		
		if (request.getParameter("komentar") != null) {
			int kId = Integer.parseInt(request.getParameter("komentar"));
			Komentar k = komr.findById(kId).get();
			request.getSession().setAttribute("kom", k);
		}
		
		List<Komentar> komentari = komr.findAllByPesma(p);
		request.getSession().setAttribute("komentari", komentari);
		
		return "redirect:/pesme/nadjiPesmu?pesma=" + p.getImePesme();
	}
	
	@KorisnikAuth
	@RequestMapping(value = "/dodajUpvote", method = RequestMethod.GET)
	public String dodajUpvote(HttpServletRequest request) {
		Pesma p = (Pesma) request.getSession().getAttribute("pesma");
		try {
			
			int kId = Integer.parseInt(request.getParameter("kom"));
			Komentar k = komr.findById(kId).get();
			
			if (k.getOcenjen() == 0) {
				k.setUpvote(k.getUpvote() + 1);
				k.setOcenjen((byte) 1);
			} else if (k.getOcenjen() == 1) {
				k.setUpvote(k.getUpvote() - 1);
				k.setOcenjen((byte) 0);
			}
			
			komr.save(k);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/komentari/nadjiKomentare?pesma=" + p.getImePesme();
	}
	
	@KorisnikAuth
	@RequestMapping(value = "/dodajDownvote", method = RequestMethod.GET)
	public String dodajDownvote(HttpServletRequest request) {
		Pesma p = (Pesma) request.getSession().getAttribute("pesma");
		try {
			
			int kId = Integer.parseInt(request.getParameter("kom"));
			Komentar k = komr.findById(kId).get();
			
			if (k.getOcenjen() == 0) {
				k.setUpvote(k.getUpvote() + 1);
				k.setOcenjen((byte) 1);
			} else if (k.getOcenjen() == 1) {
				k.setUpvote(k.getUpvote() - 1);
				k.setOcenjen((byte) 0);
			}
			
			komr.save(k);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/komentari/nadjiKomentare?pesma=" + p.getImePesme();
	}
	
}
