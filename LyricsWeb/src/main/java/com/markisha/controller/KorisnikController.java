package com.markisha.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.markisha.annotations.AdminAuth;
import com.markisha.annotations.KorisnikAuth;
import com.markisha.repository.KorisnikRepository;

import model.Korisnik;

@Controller
@RequestMapping(value = "/korisnici")
public class KorisnikController {

	@Autowired
	KorisnikRepository kr;

	@RequestMapping(value = "/getKorisnici", method = RequestMethod.GET)
	public void getKorisnici(Model m) {
		List<Korisnik> korisnici = kr.findAll();

		m.addAttribute("korisnici", korisnici);
	}

	// trazi ulogovanog korisnika
	// svima dozvoljeno
	@RequestMapping(value = "/nadjiKorisnika", method = RequestMethod.GET)
	public String nadjiKorisnika(Model m, HttpServletRequest request) {
		Korisnik k = kr.findByUsername((String) request.getSession().getAttribute("username"));

		request.getSession().setAttribute("trenutniKorisnik", k);
		
		return "jsp/pregled/KorisnikInfo";
	}

	// trazi korisnika na koji je kliknut
	// svima dozvoljeno
	@RequestMapping(value = "/getKorisnik", method = RequestMethod.GET)
	public String getKorisnik(HttpServletRequest request) {
		int kId = Integer.parseInt(request.getParameter("kor"));
		Korisnik k = kr.findById(kId).get();

		request.getSession().setAttribute("korisnik", k);

		return "jsp/pregled/KorisnikInfo";
	}

	@AdminAuth
	@RequestMapping(value = "/promovisiKorisnika", method = RequestMethod.POST)
	public String promovisiKorisnika(HttpServletRequest request) {
		try {

			Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");

			if (k.getUloga().equals("KORISNIK")) {
				k.setUloga("MODERATOR");
			}

			kr.save(k);

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getSession().setAttribute("korisnik", null);
		return "jsp/pregled/KorisnikInfo";
	}

	// svima dozvoljeno
	@RequestMapping(value = "/getUsername", method = RequestMethod.GET)
	public String getUsername(HttpServletRequest request) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		Korisnik k = kr.findByUsername(username);
		request.getSession().setAttribute("trenutniKorisnik", k);
		request.getSession().setAttribute("username", username);

		return "index";
	}
	
	@KorisnikAuth
	@RequestMapping(value = "/promeniUsername", method = RequestMethod.POST)
	public String promeniUsername(String noviUsername, Model m, HttpServletRequest request) {
		Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");
		
		try {

			if (kr.findByUsername(noviUsername) != null) {
				m.addAttribute("greskaUsername", "Username already exists");
				return "jsp/pregled/KorisnikInfo";
			}
			
			k.setUsername(noviUsername);
			kr.save(k);
			
			m.addAttribute("uspesnoUsername", "Username successfully changed.");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "jsp/pregled/KorisnikInfo";
	}
	
	@AdminAuth
	@RequestMapping(value = "/banujKorisnika", method = RequestMethod.GET)
	public String banujKorisnika(HttpServletRequest request, Model m) {
		try {
			
			int idKor = Integer.parseInt(request.getParameter("korisnik"));
			Korisnik k = kr.findById(idKor).get();
			
			k.setUloga("BANOVAN");
			
			kr.save(k);
			
		} catch (Exception e) {
			m.addAttribute("greskaBan", "Something went wrong.");
			e.printStackTrace();
		}
		
		return "jsp/pregled/KorisnikInfo";
	}

}
