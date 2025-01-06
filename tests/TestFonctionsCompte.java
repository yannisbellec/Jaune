package tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import location.FonctionsCompte;
import location.InformationPersonnelle;
import location.Utilisateur;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de tests JUnit pour {@link location.FonctionsCompte}.
 *
 * <p>Cette classe vérifie le bon fonctionnement des méthodes de la classe
 * FonctionsCompte, notamment l'inscription, la connexion, la déconnexion,
 * et la récupération des utilisateurs.</p>
 *
 * @author Yannis Bellec
 */
public class TestFonctionsCompte {
  /**
   * Definition d'une classe FonctionsCompte basique.
   */
  private FonctionsCompte fctCompte;
  
  /**
   * Instanciation de la FonctionsCompte pour les tests.
   *
   * @throws Exception ne peut pas être levée ici.
   */
  
  @BeforeEach
  void setUp() throws Exception {
    fctCompte = new FonctionsCompte();
  }
  
  
  /**
   * Ne fait rien après les tests : à modifier au besoin.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  /**
   * Test si a l’initialisation de FonctionsCompte, le resultat est null.
   */
  @Test
  void testInitialisationVide() {
    assertTrue(fctCompte.getListUtilisateur().isEmpty());
    assertEquals(fctCompte.getUtilisateurConnecte(), null);
  }
  
  /**
   * Verifie si le resultat de estConnecte est False si aucun utilisateur est
   * connecté.
   */
  @Test
  void testNonConnecte() {
    assertEquals(fctCompte.estConnecte(), false);
  }
  
  /**
   * Verifie si le retour de inscription est 1 a l’inscription d’un nouvel
   * utilisateur.
   */
  @Test
  void testInscription() {
    InformationPersonnelle infoBasique =
        new InformationPersonnelle("Skywalker", "Luke");
    assertEquals(fctCompte.inscription("pseudo1", "mdp", infoBasique), 1);
  }
  
  /**
   * Verifie si le retour de inscription est 0 a l’inscription d’un utilisateur
   * deja present (pseudo).
   */
  @Test
  void testInscriptionDouble() {
    InformationPersonnelle infoBasique =
        new InformationPersonnelle("Skywalker", "Luke");
    assertEquals(fctCompte.inscription("pseudo1", "mdp", infoBasique), 0);
  }
  
  /**
   * Verifie si l’utilisateur est bien present dans la liste aprés inscription.
   */
  @Test
  void testInscriptionList() {
    InformationPersonnelle infoBasique =
        new InformationPersonnelle("Skywalker", "Luke");
    Utilisateur ut1 = new Utilisateur("pseudo1", "mdp", infoBasique);
    assertTrue(fctCompte.getListUtilisateur().contains(ut1));
  }
  
  /**
   * Verifie si le nouvel utilisateur inscrit est bien l’utilisateur connecté.
   */
  @Test
  void testInscriptionUtilisateurConnecte() {
    InformationPersonnelle infoBasique =
        new InformationPersonnelle("Skywalker", "Luke");
    fctCompte.inscription("pseudo2", "mdp", infoBasique);
    assertEquals(fctCompte.getUtilisateurConnecte().getPseudo(), "pseudo2");
  }
  
  /**
   * Verifie si l’utilisateur qui se connect est bien définit comme
   * l’utilisateur connecté.
   */
  @Test
  void tesConnextion() {
    assertTrue(fctCompte.connexion("pseudo1", "mdp"));
    assertEquals(fctCompte.getUtilisateurConnecte().getPseudo(), "pseudo1");
    assertEquals(fctCompte.connexion("pseudo2", "mdpInconnue"), false);
  }
  
  /**
   * Verifie qu’un utilisateur non inscrit ne peut pas se connecter.
   */
  @Test
  void testConnextionNonInscrit() {
    assertEquals(fctCompte.connexion("pseudoInconnue", "mdp"), false);
    assertEquals(fctCompte.getUtilisateurConnecte().getPseudo(), "pseudo1");
  }
  
  /**
   * Verifie si l’utilisateur qui se connect a le bon mdp.
   */
  @Test
  void testConnexionNonMdp() {
    assertEquals(fctCompte.connexion("pseudo2", "mdpInconnue"), false);
    assertEquals(fctCompte.getUtilisateurConnecte().getPseudo(), "pseudo1");
  }
  
  /**
   * Verifie que l’utilisateur connecté = null aprés deconnexion.
   */
  @Test
  void testDeconnexion() {
    fctCompte.deconnexion();
    assertEquals(fctCompte.getUtilisateurConnecte().getPseudo(), null);
  }
  
  
  
}


