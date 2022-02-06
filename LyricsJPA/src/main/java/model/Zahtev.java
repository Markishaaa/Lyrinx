package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the zahtev database table.
 * 
 */
@Entity
@NamedQuery(name="Zahtev.findAll", query="SELECT z FROM Zahtev z")
public class Zahtev implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ZAHTEVA")
	private int idZahteva;

	@Column(name="BROJ_PESAMA")
	private int brojPesama;

	@Column(name="IME_IZVODJACA")
	private String imeIzvodjaca;

	@Column(name="IME_PESME")
	private String imePesme;

	@Column(name="NAZIV_ALBUMA")
	private String nazivAlbuma;

	private byte odobrenje;

	private int strofa;

	@Lob
	private String tekst;

	@Column(name="TIP_ZAHTEVA")
	private String tipZahteva;

	//bi-directional many-to-one association to Album
	@ManyToOne
	@JoinColumn(name="ID_ALBUMA")
	private Album album;

	//bi-directional many-to-one association to Izvodjac
	@ManyToOne
	@JoinColumn(name="ID_IZVODJACA")
	private Izvodjac izvodjac;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="ID_KORISNIKA")
	private Korisnik korisnik;

	//bi-directional many-to-one association to Pesma
	@ManyToOne
	@JoinColumn(name="ID_PESME")
	private Pesma pesma;

	public Zahtev() {
	}

	public int getIdZahteva() {
		return this.idZahteva;
	}

	public void setIdZahteva(int idZahteva) {
		this.idZahteva = idZahteva;
	}

	public int getBrojPesama() {
		return this.brojPesama;
	}

	public void setBrojPesama(int brojPesama) {
		this.brojPesama = brojPesama;
	}

	public String getImeIzvodjaca() {
		return this.imeIzvodjaca;
	}

	public void setImeIzvodjaca(String imeIzvodjaca) {
		this.imeIzvodjaca = imeIzvodjaca;
	}

	public String getImePesme() {
		return this.imePesme;
	}

	public void setImePesme(String imePesme) {
		this.imePesme = imePesme;
	}

	public String getNazivAlbuma() {
		return this.nazivAlbuma;
	}

	public void setNazivAlbuma(String nazivAlbuma) {
		this.nazivAlbuma = nazivAlbuma;
	}

	public byte getOdobrenje() {
		return this.odobrenje;
	}

	public void setOdobrenje(byte odobrenje) {
		this.odobrenje = odobrenje;
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

	public String getTipZahteva() {
		return this.tipZahteva;
	}

	public void setTipZahteva(String tipZahteva) {
		this.tipZahteva = tipZahteva;
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

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Pesma getPesma() {
		return this.pesma;
	}

	public void setPesma(Pesma pesma) {
		this.pesma = pesma;
	}

}