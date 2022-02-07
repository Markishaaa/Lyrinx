package com.markisha.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.markisha.repository.IzvodjacRepository;

import model.Izvodjac;
import model.Zahtev;

@Controller
@RequestMapping(value = "/izvodjaci")
public class IzvodjacController {

	@Autowired
	IzvodjacRepository ir;
	
	//@AdminAuth
	@RequestMapping(value = "/dodajIzvodjaca", method = RequestMethod.POST)
	public String dodajIzvodjaca(String ime, Model m) {
		try {
			Izvodjac i = new Izvodjac();

			i.setImeIzvodjaca(ime);
			
			Izvodjac izvodjac = ir.save(i);
			m.addAttribute("izvodjac", izvodjac);
		} catch (Exception e) {
			m.addAttribute("greska", "An error has occured.");
		}

		return "jsp/unos/UnosIzvodjaca";
	}

	// svima dozvoljeno
	@RequestMapping(value = "/getIzvodjaci", method = RequestMethod.GET)
	public String getIzvodjaci(HttpServletRequest request) {
		List<Izvodjac> izvodjaci = ir.findAll();
		request.getSession().setAttribute("izvodjaci", izvodjaci);

		String ret = request.getParameter("l");

		return ret;
	}

	// svima dozvoljeno
	@RequestMapping(value = "/nadjiIzvodjaca", method = RequestMethod.GET)
	public String nadjiIzvodjaca(HttpServletRequest request) {
		String p = request.getParameter("izvodjac");
		Izvodjac izvodjac = ir.findByImeIzvodjaca(p);
		
		request.getSession().setAttribute("izvodjac", izvodjac);
		request.getSession().setAttribute("albumiIzv", izvodjac.getAlbums());
		
		return "jsp/pregled/PregledIzvodjaca";
	}

	@RequestMapping(value = "/nadjiIzvodjace", method = RequestMethod.GET)
	public String nadjiIzvodjace(HttpServletRequest request) {
		String pretraga = (String) request.getSession().getAttribute("pretragaCon");

		List<Izvodjac> izvodjaci = ir.findAll();
		List<Izvodjac> nadjeniIzvodjaci = new ArrayList<>();

		for (Izvodjac i : izvodjaci) {
			String iSpojeno = i.getImeIzvodjaca().replaceAll(" ", "").toLowerCase();
			if (iSpojeno.trim().contains(pretraga.trim())) {
				nadjeniIzvodjaci.add(i);
			}
		}

		request.getSession().setAttribute("nadjeniIzvodjaci", nadjeniIzvodjaci);

		return "redirect:/albumi/nadjiAlbume";
	}
	
	//@ModeratorAuth
	@RequestMapping(value = "/dodajZahtevKaoIzvodjaca", method = RequestMethod.GET)
	public String dodajZahtevKaoIzvodjaca(HttpServletRequest request, Model m) {
		try {
			
			Zahtev z = (Zahtev) request.getSession().getAttribute("zahtevOdobren");
			
			Izvodjac i = new Izvodjac();
			i.setImeIzvodjaca(z.getImeIzvodjaca());

			ir.save(i);
			
			m.addAttribute("poruka", "Request approved. Artist added successfully.");
			
		} catch (Exception e) {
			m.addAttribute("greskaPesmaZah", "There has been an error.");
			e.printStackTrace();
		}
		
		return "jsp/pregled/pregledZahteva/PregledZahteva";
	}

}
