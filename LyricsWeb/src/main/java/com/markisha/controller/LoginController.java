package com.markisha.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.markisha.repository.KorisnikRepository;
import com.markisha.enums.Uloga;

import model.Korisnik;

@Controller
@ControllerAdvice
@RequestMapping(value = "auth")
public class LoginController {

	@Autowired
	KorisnikRepository kr;
	
	@ModelAttribute
	public void getRoles(Model m) {
		List<String> uloge = new ArrayList<>();
		for (Uloga u : Uloga.values()) {
			uloge.add(u.toString());
		}
		
		m.addAttribute("roles", uloge);
	}

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/access_denied", method = RequestMethod.GET)
	public String deniedPage() {
		return "access_denied";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.GET)
	public String newUser(HttpServletRequest request, Model m) {
		Korisnik k = new Korisnik();
		m.addAttribute("user", k);
		
		return "register";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String saveUser(@Valid @ModelAttribute("user") Korisnik k, Errors e, Model m, BindingResult bindingResult, HttpServletRequest request) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		k.setPassword(passwordEncoder.encode(k.getPassword()));

//		k.setUloga(Uloga.KORISNIK.toString());
		
		if (e.hasErrors()) {
			m.addAttribute("greska", e.getFieldError().getDefaultMessage());
			return "register";
		} else {
			kr.save(k);
			request.getSession().setAttribute("user", k);
		}

		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		request.getSession().setAttribute("username", null);
		return "redirect:/auth/loginPage";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getPocetna() {

		return "index";
	}

	@ExceptionHandler({ SQLException.class, DataAccessException.class })
	public String databaseError() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "jsp/greska/Greska";
	}

}
