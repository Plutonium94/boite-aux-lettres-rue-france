package fr.unice.mbds;

import fr.unice.mbds.entites.BoiteAuxLettres;

import org.junit.*;

public class BalTest {

	private BoiteAuxLettres bal1;

	private static final String LB_VOIE_1 = "Rue de France";

	@Before
	public void setUp() {
		bal1 = new BoiteAuxLettres();
	}

	@Test
	public void testLbVoie() {
		bal1.setLbVoie(LB_VOIE_1);
		Assert.assertEquals(LB_VOIE_1, bal1.getLbVoie());
	}


}