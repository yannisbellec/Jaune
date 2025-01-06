package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import location.Artiste;
import location.Evaluation;
import location.Film;
import location.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de tests JUnit pour la classe {@link location.Evaluation}.
 * 
 * <p>Cette classe teste les méthodes principales de la classe Evaluation,
 * notamment la gestion des notes, des commentaires et des films associés.
 * </p>
 *
 * @author 
 * @see location.Evaluation
 */
class TestEvaluation {

  /** Une évaluation de test avec une note et un commentaire. */
  private Evaluation evaluation;

  /** Un film utilisé pour associer l'évaluation. */
  private Film film;

  /**
   * Prépare les objets nécessaires aux tests avant chaque exécution.
   *
   * @throws Exception si une erreur se produit (non applicable ici).
   */
  @BeforeEach
  void setUp() throws Exception {
    Set<Genre> genres = new HashSet<>();
    genres.add(Genre.Action);

    Set<Artiste> artistes = new HashSet<>();
    artistes.add(new Artiste("Skywalker", "Luke"));

    film = new Film(artistes, 2024, 12, "Film de Test");
    evaluation = new Evaluation(4, "Excellent film.", film);
  }

  /**
   * Vérifie qu'on getNote renvoie la note.
  */
  @Test
  void testGetNote() {
    assertEquals(4, evaluation.getNote(), "La note de l'évaluation devrait être 4.");
  }

  /**
   * Vérifie que setNote met à jour la note.
   */
  @Test
  void testSetNoteValide() {
    evaluation.setNote(5);
    assertEquals(5, evaluation.getNote(), "La note de l'évaluation devrait être mise à jour à 5.");
  }

  /**
   * Vérifie que setNote vérifie si la limite est atteinte alors elle est réduite à 5.
   */
  @Test
  void testSetNoteSupLimite() {
    evaluation.setNote(6);
    assertEquals(5, evaluation.getNote(), "La note de l'évaluation ne devrait pas dépasser 5.");
  }

  /**
   * Vérifie que setNote vérifie si la limite est atteinte alors elle est augmentée à 0.
   */
  @Test
  void testSetNoteInfLimite() {
    evaluation.setNote(-1);
    assertEquals(0, evaluation.getNote(), 
        "La note de l'évaluation ne devrait pas être inférieure à 0.");
  }

  /**
   * Vérifie que la getCommentaire renvoie le commentaire.
   */
  @Test
  void testGetCommentaire() {
    assertEquals("Excellent film.", evaluation.getCommentaire(), 
         "Le commentaire devrait être 'Excellent film.'.");
  }

  /**
   * Vérifie que la setCommentaire met à jour le commentaire.
   */
  @Test
  void testSetCommentaire() {
    evaluation.setCommentaire("Film correct.");
    assertEquals("Film correct.", evaluation.getCommentaire(), 
        "Le commentaire devrait être 'Film correct.'.");
  }

  /**
   * Vérifie que la méthode getFilm renvoie le film.
   */
  @Test
  void testGetFilm() {
    assertEquals(film, evaluation.getFilm(), 
        "Le film associé à l'évaluation devrait être correct.");
  }

  /**
   * Vérifie que la méthode setFilm met à jour le film.
   */
  @Test
  void testSetFilm() {
    Film nouveauFilm = new Film(new HashSet<>(), new HashSet<>(), 2023, 18, "Nouveau Film");
    evaluation.setFilm(nouveauFilm);
    assertEquals(nouveauFilm, evaluation.getFilm(), 
        "Le film associé à l'évaluation devrait être mis à jour.");
  }
}
