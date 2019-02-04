package fr.unice.mbds.stateless;

import fr.unice.mbds.entites.BoiteAuxLettres;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.*;

@Stateless
public class BalManager {

	@PersistenceContext(unitName="persistenceUnit")
	private EntityManager em;

	public void save(BoiteAuxLettres bal) {
		em.persist(bal);
	}

	public BoiteAuxLettres findBalById(long id) {
		return em.find(BoiteAuxLettres.class, id);
	}

	public Long findAllBalCount() {
		return em.createQuery("Select count(bal.id) from BoiteAuxLettres bal",Long.class).getSingleResult();
	}

	public List<BoiteAuxLettres> findBalByCommune(String commune) {
		return em.createQuery("Select bal from BoiteAuxLettres bal where bal.commune like :commune", BoiteAuxLettres.class)
			.setParameter("commune",commune)
			.getResultList();
	}

	public List<BoiteAuxLettres> findBalByCommune(String commune, int startPosition, int maxResult) {
		return em.createQuery("Select bal from BoiteAuxLettres bal where bal.commune = :commune", BoiteAuxLettres.class)
			.setParameter("commune",commune)
			.setFirstResult(startPosition)
			.setMaxResults(maxResult)
			.getResultList();
	}

	public Long findBalCountByCommune(String commune) {
		return em.createQuery("Select count(bal.id) from BoiteAuxLettres bal where commune like :commune", Long.class)
			.setParameter("commune", commune)
			.getSingleResult();
	}

	public List<String> findAllCommune() {
		return em.createQuery("Select bal.commune from BoiteAuxLettres bal group by bal.commune", String.class)
			.getResultList();
	}

	public Long findAllCommuneCount() {
		return em.createQuery("Select count(distinct bal.commune) from BoiteAuxLettres bal", Long.class)
			.getSingleResult();
	}

	public Long findAllDepartementCount() {
		return em.createQuery("Select count(distinct substring(bal.codePostale, 0, 2)) from BoiteAuxLettres bal ", Long.class)
			.getSingleResult();
	}

	public List<BoiteAuxLettres> findAllBal() {
		return em.createQuery("Select bal from BoiteAuxLettres bal", BoiteAuxLettres.class)
			.getResultList();
	}

	public List<String> findAllDepartement() {
		return em.createQuery("Select distinct substring(bal.codePostale,0,2) from BoiteAuxLettres bal group by bal.codePostale", String.class)
			.getResultList();
	}

	public List<String> findCommuneByFirstLetter(char firstLetter) {
		String s1 = ("" + firstLetter).toUpperCase();
		String s2 = ("" + firstLetter).toLowerCase();
		return em.createQuery("Select distinct bal.commune from BoiteAuxLettres bal where substring(bal.commune,1,1)=:scap or substring(bal.commune,1,1) = :ssmall", String.class)
			.setParameter("scap", s1)
			.setParameter("ssmall", s2)
			.getResultList();
	}

	public List<String> findCommuneByFirstLetter(char firstLetter, int startPosition, int maxResult) {
		return em.createQuery("Select distinct bal.commune from BoiteAuxLettres bal where upper(substring(bal.commune,1,1)) = :firstCapLetter", String.class)
			.setParameter("firstCapLetter", "" + Character.toUpperCase(firstLetter))
			.setFirstResult(startPosition)
			.setMaxResults(maxResult)
			.getResultList();
	}


}