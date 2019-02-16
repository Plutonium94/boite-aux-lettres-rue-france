package fr.unice.mbds.entites;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;

@Entity
public class BoiteAuxLettres implements Serializable {

	private static final long serialVersionUID = 2L;

	private static final double EPSILON = 1E-13;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private int vaNoVoie;
	private String lbVoie;
	private String commune;
	private double latitude;
	private double longitude;

	public BoiteAuxLettres() {

	}

	public BoiteAuxLettres(int vaNoVoie, String lbVoie, String commune) {
		this.vaNoVoie = vaNoVoie;
		this.lbVoie = lbVoie;
		this.commune = commune;
	}

	public void setVaNoVoie(int vaNoVoie) {
		this.vaNoVoie = vaNoVoie;
	}

	public void setLbVoie(String lbVoie) {
		this.lbVoie = lbVoie;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}

	public int getVaNoVoie() {
		return vaNoVoie;
	}

	public String getLbVoie() {
		return lbVoie;
	}

	public String getCommune() {
		return commune;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public long getId() {
		return id;
	}

	public String toString() {
		return "BoiteAuxLettres[commune: " + commune + ", vaNoVoie: " + vaNoVoie + ", lbVoie: " + lbVoie + "]";
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof BoiteAuxLettres) {
			BoiteAuxLettres b = (BoiteAuxLettres)o;
			return vaNoVoie == b.vaNoVoie && lbVoie.equals(b.lbVoie) && commune.equals(b.getCommune())
				&& doubleEquals(latitude, b.latitude) && doubleEquals(longitude, b.longitude);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return vaNoVoie + lbVoie.hashCode() + commune.hashCode() + (int)latitude + (int)longitude;
	}

	private static boolean doubleEquals(double d1, double d2) {
		return Math.abs(d1 - d2) <= EPSILON;
	}

}