package fr.unice.mbds;

import fr.unice.mbds.entites.BoiteAuxLettres;
import fr.unice.mbds.stateless.*;

import javax.ejb.*;
import javax.annotation.*;
import javax.servlet.annotation.WebListener;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Context;
import javax.servlet.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

//@Startup
// @Singleton
@WebListener(value="listens to the startup of the ServletContext")
public class InitBal implements ServletContextListener {

	@EJB
	private BalManager bm;

	private static final String FILE_LOCATION = "/WEB-INF/boites-aux-lettres-de-rue-de-la-france.csv";

	
	private List<BoiteAuxLettres> balList = new ArrayList<BoiteAuxLettres>();

	@Override
	public void contextInitialized(ServletContextEvent ce) {
		InputStream is = ce.getServletContext().getResourceAsStream(FILE_LOCATION);
		if(is == null) {
			return;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try {
			br.readLine(); // Ignore header line
			for(String line = br.readLine(); line != null; line = br.readLine()) {
				String[] lineSplit = line.split(";");
				BoiteAuxLettres bal = new BoiteAuxLettres();
				int vaNoVoie = 0;
				try {
					vaNoVoie = Integer.parseInt(lineSplit[1]);
				} catch(NumberFormatException nfe) {

				}
				bal.setVaNoVoie(vaNoVoie);
				bal.setLbVoie(lineSplit[3]);
				bal.setCommune(lineSplit[4]);
				String[] coordonneesGeographiques = lineSplit[11].split(",");
				double latitude = 0, longitude = 0;
				try {
					latitude = Double.parseDouble(coordonneesGeographiques[0]);
					longitude = Double.parseDouble(coordonneesGeographiques[1]);
				} catch(NumberFormatException nfe) {

				}
				bal.setLatitude(latitude);
				bal.setLongitude(longitude);

				balList.add(bal);
			}
			br.close();
		} catch(IOException ioe) {
			System.err.println(ioe);
		}
		for(BoiteAuxLettres bal : balList) {
			bm.save(bal);
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent ce) {

	}

	public List<BoiteAuxLettres> getBalList() {
		return balList;
	}


}