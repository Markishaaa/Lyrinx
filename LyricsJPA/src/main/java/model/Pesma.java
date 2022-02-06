package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the pesma database table.
 * 
 */
@Entity
@NamedQuery(name="Pesma.findAll", query="SELECT p FROM Pesma p")
public class Pesma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PESME")
	private int idPesme;

	@Column(name="IME_PESME")
	private String imePesme;

	private int strofa;

	@Lob
	private String tekst;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="pesma", fetch=FetchType.EAGER)
	private Set<Komentar> komentars;

	//bi-directional many-to-one association to Album
	@ManyToOne
	@JoinColumn(name="ID_ALBUMA")
	private Album album;

	//bi-directional many-to-one association to Izvodjac
	@ManyToOne
	@JoinColumn(name="ID_IZVODJACA")
	private Izvodjac izvodjac;

	//bi-directional many-to-many association to Korisnik
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="editor_pesme"
		, joinColumns={
			@JoinColumn(name="ID_PESME")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_KORISNIKA")
			}
		)
	private Set<Korisnik> korisniks;

	//bi-directional many-to-one association to Zahtev
	@OneToMany(mappedBy="pesma", fetch=FetchType.EAGER)
	private Set<Zahtev> zahtevs;

	public Pesma() {
	}

	public int getIdPesme() {
		return this.idPesme;
	}

	public void setIdPesme(int idPesme) {
		this.idPesme = idPesme;
	}

	public String getImePesme() {
		return this.imePesme;
	}

	public void setImePesme(String imePesme) {
		this.imePesme = imePesme;
	}

	public int getStrofa() {
		return this.strofa;
	}

	public void setStrofa(int strofa) {
		this.strofa = strofa;
	}

	public String getTekst() {
		return this.tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Set<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(Set<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setPesma(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setPesma(null);

		return komentar;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Izvodjac getIzvodjac() {
		return this.izvodjac;
	}

	public void setIzvodjac(Izvodjac izvodjac) {
		this.izvodjac = izvodjac;
	}

	public Set<Korisnik> getKorisniks() {
		return this.korisniks;
	}

	public void setKorisniks(Set<Korisnik> korisniks) {
		this.korisniks = korisniks;
	}

	public Set<Zahtev> getZahtevs() {
		return this.zahtevs;
	}

	public void setZahtevs(Set<Zahtev> zahtevs) {
		this.zahtevs = zahtevs;
	}

	public Zahtev addZahtev(Zahtev zahtev) {
		getZahtevs().add(zahtev);
		zahtev.setPesma(this);

		return zahtev;
	}

	public Zahtev removeZahtev(Zahtev zahtev) {
		getZahtevs().remove(zahtev);
		zahtev.setPesma(null);

		return zahtev;
	}

}