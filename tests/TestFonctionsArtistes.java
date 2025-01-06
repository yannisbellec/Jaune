package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import location.Artiste;
import location.Film;
import location.FonctionsArtistes;
import location.FonctionsFilms;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de tests JUnit pour la classe {@link location.FonctionsArtistes}.
 *
 * <p>
 * Cette classe teste les principales fonctionnalités de la classe
 * FonctionsArtistes, notamment la gestion des artistes, acteurs et
 * réalisateurs.
 * </p>
 *
 * @author
 * @see location.FonctionsArtistes
 */
class TestFonctionsArtistes {
  
  /** Objet FonctionsArtistes utilisé pour les tests. */
  private FonctionsArtistes fonctionsArtistes;
  
  /** Ensemble de films simulé pour les tests. */
  private FonctionsFilms fonctionsFilms;
  
  /** Un acteur et un réalisateur de test. */
  private Artiste acteur;
  private Artiste realisateur;
  
  /** Un film associé pour les tests. */
  private Film film;
  
  /**
   * Prépare les objets nécessaires aux tests avant chaque exécution.
   *
   * @throws Exception si une erreur se produit (non applicable ici).
   */
  @BeforeEach
  void setUp() throws Exception {
    fonctionsFilms = new FonctionsFilms();
    fonctionsArtistes = new FonctionsArtistes(fonctionsFilms);
    
    acteur = new Artiste("Doe", "John", "Américaine");
    realisateur = new Artiste("Smith", "Jane", "Britannique");
    
    Set<Artiste> acteurs = new HashSet<>();
    acteurs.add(acteur);
    
    film = new Film("The mandalorian", null, 1789, 12);
    fonctionsFilms.setFilm(film);
    
    fonctionsArtistes.addActeur(acteur);
    fonctionsArtistes.addRealisateur(realisateur);
  }
  
  /**
   * Vérifie que la méthode getActeur retourne un acteur existant.
   */
  @Test
  void testGetActeur() {
    Artiste result = fonctionsArtistes.getActeur("Doe", "John");
    assertEquals(acteur, result,
        "La méthode devrait retourner l'acteur correspondant.");
  }
  
  /**
   * Vérifie que la méthode getActeur retourne null pour un acteur inexistant.
   */
  @Test
  void testGetActeurInexistant() {
    Artiste result = fonctionsArtistes.getActeur("Inconnu", "Acteur");
    assertNull(result,
        "La méthode devrait retourner null pour un acteur inexistant.");
  }
  
  /**
   * Vérifie que la méthode getRealisateur retourne un réalisateur existant.
   */
  @Test
  void testGetRealisateur() {
    Artiste result = fonctionsArtistes.getRealisateur("Smith", "Jane");
    assertEquals(realisateur, result,
        "La méthode devrait retourner le réalisateur correspondant.");
  }
  
  /**
   * Vérifie que la méthode getRealisateur retourne null pour un réalisateur
   * inexistant.
   */
  @Test
  void testGetRealisateurInexistant() {
    Artiste result = fonctionsArtistes.getRealisateur("Inconnu", "Realisateur");
    assertNull(result,
        "La méthode devrait retourner null pour un réalisateur inexistant.");
  }
  
  /**
   * Vérifie que la méthode ensembleFilmsActeur retourne les films d'un acteur
   * donné.
   */
  @Test
  void testEnsembleFilmsActeur() {
    assertNull(fonctionsArtistes.ensembleFilmsActeur(null),
        "La méthode devrait retourner un ensemble vide pour un acteur sans film.");
  }

  
  /**
   * Vérifie que la méthode ensembleFilmsRealisateur retourne null si aucun film
   * n'est trouvé.
   */
  @Test
  void testEnsembleFilmsRealisateurAucunFilm() {
    Set<Film> result = fonctionsArtistes.ensembleFilmsRealisateur(realisateur);
    assertNull(result,
        "La méthode devrait retourner null si aucun film n'est trouvé.");
  }
  
  /**
   * Vérifie que la méthode addActeur ajoute un acteur correctement.
   */
  @Test
  void testAddActeur() {
    Artiste nouvelActeur = new Artiste("Wayne", "Bruce", "Américaine");
    boolean result = fonctionsArtistes.addActeur(nouvelActeur);
    assertTrue(result,
        "La méthode devrait retourner true pour un ajout réussi.");
    assertTrue(fonctionsArtistes.ensembleActeurs().contains(nouvelActeur),
        "L'acteur devrait être ajouté dans l'ensemble.");
  }
  
  /**
   * Vérifie que la méthode removeActeur supprime un acteur correctement.
   */
  @Test
  void testRemoveActeur() {
    boolean result = fonctionsArtistes.removeActeur(acteur);
    assertTrue(result,
        "La méthode devrait retourner true pour une suppression réussie.");
    assertFalse(fonctionsArtistes.ensembleActeurs().contains(acteur),
        "L'acteur ne devrait plus être présent dans l'ensemble.");
  }
  
  /**
   * Vérifie que la méthode addRealisateur ajoute un réalisateur correctement.
   */
  @Test
  void testAddRealisateur() {
    Artiste nouveauRealisateur =
        new Artiste("Nolan", "Christopher", "Britannique");
    boolean result = fonctionsArtistes.addRealisateur(nouveauRealisateur);
    assertTrue(result,
        "La méthode devrait retourner true pour un ajout réussi.");
    assertTrue(
        fonctionsArtistes.ensembleRealisateurs().contains(nouveauRealisateur),
        "Le réalisateur devrait être ajouté dans l'ensemble.");
  }
  
  /**
   * Vérifie que la méthode removeRealisateur supprime un réalisateur
   * correctement.
   */
  @Test
  void testRemoveRealisateur() {
    boolean result = fonctionsArtistes.removeRealisateur(realisateur);
    assertTrue(result,
        "La méthode devrait retourner true pour une suppression réussie.");
    assertFalse(fonctionsArtistes.ensembleRealisateurs().contains(realisateur),
        "Le réalisateur ne devrait plus être présent dans l'ensemble.");
  }
}
