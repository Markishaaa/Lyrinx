package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the izvodjac database table.
 * 
 */
@Entity
@NamedQuery(name="Izvodjac.findAll", query="SELECT i FROM Izvodjac i")
public class Izvodjac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_IZVODJACA")
	private int idIzvodjaca;

	@Column(name="IME_IZVODJACA")
	private String imeIzvodjaca;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="izvodjac", fetch=FetchType.EAGER)
	private Set<Album> albums;

	//bi-directional many-to-one association to Pesma
	@OneToMany(mappedBy="izvodjac", fetch=FetchType.EAGER)
	private Set<Pesma> pesmas;

	//bi-directional many-to-one association to Zahtev
	@OneToMany(mappedBy="izvodjac", fetch=FetchType.EAGER)
	private Set<Zahtev> zahtevs;

	public Izvodjac() {
	}

	public int getIdIzvodjaca() {
		return this.idIzvodjaca;
	}

	public void setIdIzvodjaca(int idIzvodjaca) {
		this.idIzvodjaca = idIzvodjaca;
	}

	public String getImeIzvodjaca() {
		return this.imeIzvodjaca;
	}

	public void setImeIzvodjaca(String imeIzvodjaca) {
		this.imeIzvodjaca = imeIzvodjaca;
	}

	public Set<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public Album addAlbum(Album album) {
		getAlbums().add(album);
		album.setIzvodjac(this);

		return album;
	}

	public Album removeAlbum(Album album) {
		getAlbums().remove(album);
		album.setIzvodjac(null);

		return album;
	}

	public Set<Pesma> getPesmas() {
		return this.pesmas;
	}

	public void setPesmas(Set<Pesma> pesmas) {
		this.pesmas = pesmas;
	}

	public Pesma addPesma(Pesma pesma) {
		getPesmas().add(pesma);
		pesma.setIzvodjac(this);

		return pesma;
	}

	public Pesma removePesma(Pesma pesma) {
		getPesmas().remove(pesma);
		pesma.setIzvodjac(null);

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
		zahtev.setIzvodjac(this);

		return zahtev;
	}

	public Zahtev removeZahtev(Zahtev zahtev) {
		getZahtevs().remove(zahtev);
		zahtev.setIzvodjac(null);

		return zahtev;
	}

}