package fr.unice.mbds;

public class BoiteAuxLettres {

	/*private String co_mup;*/
	private int vaNoVoie;
	/*private double latitude;
	private double longitude;
	private int com_insee;*/
	private String lbVoie;
	private String commune;

	public BoiteAuxLettres() {

	}

	public BoiteAuxLettres(int vaNoVoie, String lbVoie, String commune) {
		this.vaNoVoie = vaNoVoie;
		this.lbVoie = lbVoie;
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

	public void setVaNoVoie() {
		this.vaNoVoie = vaNoVoie;
	}

	public void setLbVoie() {
		this.lbVoie = lbVoie;
	}

	public void setCommune() {
		this.commune = commune;
	}

	public static BoiteAuxLettres[] getSampleBALs() {
		return new BoiteAuxLettres[] {
			new BoiteAuxLettres(3, "Lucioles","Valbonne"),
			new BoiteAuxLettres(5, "Dolines", "Rennes"),
			new BoiteAuxLettres(9, "Promenade des Arts", "Grenoble")
		};
	}

	public String toString() {
		return "BoiteAuxLettres [vaNoVoie : " + vaNoVoie + ", lbVoie : " + lbVoie + ", commune : " + commune + "]";
	}
}