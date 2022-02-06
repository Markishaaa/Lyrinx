package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_KORISNIKA")
	private int idKorisnika;

	private String password;

	private String uloga;

	private String username;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="korisnik", fetch=FetchType.EAGER)
	private Set<Komentar> komentars;

	//bi-directional many-to-many association to Album
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="editor_albuma"
		, joinColumns={
			@JoinColumn(name="ID_KORISNIKA")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_ALBUMA")
			}
		)
	private Set<Album> albums;

	//bi-directional many-to-many association to Pesma
	@ManyToMany(mappedBy="korisniks", fetch=FetchType.EAGER)
	private Set<Pesma> pesmas;

	//bi-directional many-to-one association to Zahtev
	@OneToMany(mappedBy="korisnik", fetch=FetchType.EAGER)
	private Set<Zahtev> zahtevs;

	public Korisnik() {
	}

	public int getIdKorisnika() {
		return this.idKorisnika;
	}

	public void setIdKorisnika(int idKorisnika) {
		this.idKorisnika = idKorisnika;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUloga() {
		return this.uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(Set<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setKorisnik(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setKorisnik(null);

		return komentar;
	}

	public Set<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public Set<Pesma> getPesmas() {
		return this.pesmas;
	}

	public void setPesmas(Set<Pesma> pesmas) {
		this.pesmas = pesmas;
	}

	public Set<Zahtev> getZahtevs() {
		return this.zahtevs;
	}

	public void setZahtevs(Set<Zahtev> zahtevs) {
		this.zahtevs = zahtevs;
	}

	public Zahtev addZahtev(Zahtev zahtev) {
		getZahtevs().add(zahtev);
		zahtev.setKorisnik(this);

		return zahtev;
	}

	public Zahtev removeZahtev(Zahtev zahtev) {
		getZahtevs().remove(zahtev);
		zahtev.setKorisnik(null);

		return zahtev;
	}

}