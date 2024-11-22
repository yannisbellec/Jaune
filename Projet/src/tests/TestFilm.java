package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import location.Artiste;
import location.Evaluation;
import location.Film;
import location.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe { @link location.Film }.
 *
 * @author Alexandre De Brito
 * @see location.Film
 */
class TestFilm {

  /**
  * Un film avec des informations de base : titre et année de réalisation.
  */
  private Film filmBasique;

  /**
   * Un film complet avec titre, genre, artistes, évaluations et autres informations.
   */
  private Film filmComplet;

  /**
   * Instancie un film basique et un film complet pour les tests.
   *
   * @throws Exception ne peut pas être levée ici.
   */
  @BeforeEach
  void setUp() throws Exception {
    Set<Genre> genres = new HashSet<>();
    genres.add(Genre.Action); 

    Set<Artiste> artistes = new HashSet<>();
    artistes.add(new Artiste("Skywalker", "Luke"));

    filmBasique = new Film(genres, artistes, 2024, 18, "Film Basique");
    filmComplet = new Film(genres, artistes, 2024, 18, "Film Complet");

    Set<Evaluation> evaluations = new HashSet<>();
    evaluations.add(new Evaluation(5, "Super film !", filmBasique));
    evaluations.add(new Evaluation(3, "Complet mais pas fou !", filmComplet));
  }

  /**
   * Vérifie que l'on peut positionner un âge minimal de 25 ans sur un film.
   */
  @Test
  void testAgeMin25() {
    filmBasique.setAgeMin(25);
    assertEquals(25, filmBasique.getAgeMin());
  }

  /**
   * Vérifie que la moyenne des évaluations est correcte.
   */
  @Test
  void testCalculMoyenne() {
    filmBasique.setEvaluation(new Evaluation(4, "Bon film", filmBasique));
    filmBasique.calculeMoyenne();
    assertEquals(4.5, filmBasique.getNoteMoyenne(), 0.01);
  }

  /**
   * Vérifie qu'on ne peut pas définir une évaluation null sur un film.
   */
  @Test
  void testSetterEvaluationNull() {
    filmComplet.setEvaluation(null);
    assertTrue(filmComplet.getSetEvaluations().size() > 0);
  }

  /**
   * Vérifie que l'on peut ajouter un genre à un film.
   */
  @Test
  void testAjouterGenre() {
    filmBasique.setGenre(Genre.Drame);
    assertTrue(filmBasique.getSetGenre().contains(Genre.Drame));
  }

  /**
   * Vérifie que l'on peut ajouter un acteur à un film.
   */
  @Test
  void testAjouterActeur() {
    Artiste acteur = new Artiste("Skywalker", "Anakin");
    filmBasique.setArtiste(acteur);
    assertTrue(filmBasique.getSetArtistes().contains(acteur));
  }

  /**
   * Vérifie qu'une location est bien activée.
   */
  @Test
  void testLocationActive() {
    filmBasique.setLocation(true);
    assertTrue(filmBasique.getLocation());
  }

  /**
   * Vérifie qu'on ne peut pas activer la location d'un film si le nombre de locations dépasse 3.
   */
  @Test
  void testLocationLimiteAtteinte() {
    filmBasique.setNbLoc(4);
    filmBasique.setLocation(true);
    assertFalse(filmBasique.getLocation());
  }
  
}

