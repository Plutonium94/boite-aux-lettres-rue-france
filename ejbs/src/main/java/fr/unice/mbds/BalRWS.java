package fr.unice.mbds;

import fr.unice.mbds.entites.BoiteAuxLettres;
import fr.unice.mbds.stateless.*;

import javax.ejb.EJB;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;

import javax.servlet.ServletContext;

import java.util.*;

import java.nio.file.Paths;
import java.io.*;

@Path("bal")
public class BalRWS {

	@EJB
	private BalManager bm;

	@Context
	private ServletContext sc; 

	public BalRWS() {

	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/")
	public List<BoiteAuxLettres> getAllBals() {
		// return BoiteAuxLettres.getSampleBALs();
		return bm.findAllBal();
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON}) 
	@Path("/count")
	public Long getAllBalCount() {
		return bm.findAllBalCount();
	}

	@GET
	@Path("/create/{vaNoVoie}/{lbVoie}/{commune}") 
	public boolean createBal(@PathParam("vaNoVoie") int vaNoVoie, @PathParam("lbVoie") String lbVoie, @PathParam("commune") String commune) {
		BoiteAuxLettres bal = new BoiteAuxLettres(vaNoVoie, lbVoie, commune);
		try {
			bm.save(bal);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@GET
	@Path("/current")
	public String getCurrentDirectory() {
		InputStream is = sc.getResourceAsStream("/WEB-INF/boites-aux-lettres-de-rue.csv");
		if(is == null) {
			return "Is null. Check path";
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try {

			String res = br.readLine();
			br.close();
			return res;
		} catch(IOException ioe) {
			System.err.println(ioe);
			return "hmm";
		}

	}

	@GET
	@Path("/commune")
	public List<String> getAllCommune() {
		return bm.findAllCommune();
	}

	@GET
	@Path("/commune/count")
	public Long getAllCommuneCount() {
		return bm.findAllCommuneCount();
	}

	@GET
	@Path("/departement/count") 
	public Long getAllDepartementCount() {
		return bm.findAllDepartementCount();
	}

	@GET
	@Path("/commune/{firstLetter}")
	public List<String> getCommuneByFirstLetter(@PathParam("firstLetter") char firstLetter) {
		return bm.findCommuneByFirstLetter(firstLetter);
	}

	@GET
	@Path("/departement")
	public List<String> getAllDepartement() {
		return bm.findAllDepartement();
	}

	/*@GET
	@Path("/")
	public String wise() {
		return "begin boite aux lettres";
	}*/

	@GET
	@Path("test")
	public String test() {
		return "test successful";
	}
}