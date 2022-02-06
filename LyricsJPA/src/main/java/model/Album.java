package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the album database table.
 * 
 */
@Entity
@NamedQuery(name="Album.findAll", query="SELECT a FROM Album a")
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ALBUMA")
	private int idAlbuma;

	@Column(name="BROJ_PESAMA")
	private int brojPesama;

	@Column(name="NAZIV_ALBUMA")
	private String nazivAlbuma;

	private String slika;

	//bi-directional many-to-one association to Izvodjac
	@ManyToOne
	@JoinColumn(name="ID_IZVODJACA")
	private Izvodjac izvodjac;

	//bi-directional many-to-many association to Korisnik
	@ManyToMany(mappedBy="albums", fetch=FetchType.EAGER)
	private Set<Korisnik> korisniks;

	//bi-directional many-to-one association to Pesma
	@OneToMany(mappedBy="album", fetch=FetchType.EAGER)
	private Set<Pesma> pesmas;

	//bi-directional many-to-one association to Zahtev
	@OneToMany(mappedBy="album", fetch=FetchType.EAGER)
	private Set<Zahtev> zahtevs;

	public Album() {
	}

	public int getIdAlbuma() {
		return this.idAlbuma;
	}

	public void setIdAlbuma(int idAlbuma) {
		this.idAlbuma = idAlbuma;
	}

	public int getBrojPesama() {
		return this.brojPesama;
	}

	public void setBrojPesama(int brojPesama) {
		this.brojPesama = brojPesama;
	}

	public String getNazivAlbuma() {
		return this.nazivAlbuma;
	}

	public void setNazivAlbuma(String nazivAlbuma) {
		this.nazivAlbuma = nazivAlbuma;
	}

	public String getSlika() {
		return this.slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
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

	public Set<Pesma> getPesmas() {
		return this.pesmas;
	}

	public void setPesmas(Set<Pesma> pesmas) {
		this.pesmas = pesmas;
	}

	public Pesma addPesma(Pesma pesma) {
		getPesmas().add(pesma);
		pesma.setAlbum(this);

		return pesma;
	}

	public Pesma removePesma(Pesma pesma) {
		getPesmas().remove(pesma);
		pesma.setAlbum(null);

		return pesma;
	}

	public Set<Zahtev> getZahtevs() {
		return this.zahtevs;
	}

	public void setZahtevs(Set<Zahtev> zahtevs) {
		this.zahtevs = zahtevs;
	}

	public Zahtev addZahtev(Zahtev zahtev) {
		getZahtevs().add(zahtev);
		zahtev.setAlbum(this);

		return zahtev;
	}

	public Zahtev removeZahtev(Zahtev zahtev) {
		getZahtevs().remove(zahtev);
		zahtev.setAlbum(null);

		return zahtev;
	}

}