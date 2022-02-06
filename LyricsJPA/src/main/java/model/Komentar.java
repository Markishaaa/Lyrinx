package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the komentar database table.
 * 
 */
@Entity
@NamedQuery(name="Komentar.findAll", query="SELECT k FROM Komentar k")
public class Komentar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_KOMENTARA")
	private int idKomentara;

	@Temporal(TemporalType.DATE)
	@Column(name="DATUM_OBJAVLJIVANJA")
	private Date datumObjavljivanja;

	private int downvote;

	private byte ocenjen;

	@Column(name="SADRZAJ_KOMENTARA")
	private String sadrzajKomentara;

	private int upvote;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="ID_KORISNIKA")
	private Korisnik korisnik;

	//bi-directional many-to-one association to Pesma
	@ManyToOne
	@JoinColumn(name="ID_PESME")
	private Pesma pesma;

	public Komentar() {
	}

	public int getIdKomentara() {
		return this.idKomentara;
	}

	public void setIdKomentara(int idKomentara) {
		this.idKomentara = idKomentara;
	}

	public Date getDatumObjavljivanja() {
		return this.datumObjavljivanja;
	}

	public void setDatumObjavljivanja(Date datumObjavljivanja) {
		this.datumObjavljivanja = datumObjavljivanja;
	}

	public int getDownvote() {
		return this.downvote;
	}

	public void setDownvote(int downvote) {
		this.downvote = downvote;
	}

	public byte getOcenjen() {
		return this.ocenjen;
	}

	public void setOcenjen(byte ocenjen) {
		this.ocenjen = ocenjen;
	}

	public String getSadrzajKomentara() {
		return this.sadrzajKomentara;
	}

	public void setSadrzajKomentara(String sadrzajKomentara) {
		this.sadrzajKomentara = sadrzajKomentara;
	}

	public int getUpvote() {
		return this.upvote;
	}

	public void setUpvote(int upvote) {
		this.upvote = upvote;
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