package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import location.Artiste;
import location.Evaluation;
import location.Film;
import location.FonctionsFilms;
import location.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de tests unitaires pour la classe {@link FonctionsFilms}. Vérifie les
 * comportements de gestion des films et des évaluations.
 *
 * @author Alexandre De Brito
 */
class TestFonctionsFilms {
  
  private FonctionsFilms fonctionsFilms;
  private Film film1;
  private Film film2;
  
  /**
   * Préparation des données de test avant chaque test.
   *
   * @throws Exception ne peut pas être levée ici.
   */
  @BeforeEach
  void setUp() throws Exception {
    fonctionsFilms = new FonctionsFilms();
    
    Set<Genre> genres1 = new HashSet<>();
    genres1.add(Genre.Action);
    
    Set<Genre> genres2 = new HashSet<>();
    genres2.add(Genre.Comedie);
    
    Set<Artiste> artistes = new HashSet<>();
    artistes.add(new Artiste("Van Damme", "Jean-Claude"));
    
    film1 = new Film(genres1, artistes, 2024, 18, "Action Movie");
    film2 = new Film(genres2, artistes, 2024, 18, "Comedy Movie");
    
    Set<Evaluation> evaluations1 = new HashSet<>();
    evaluations1.add(new Evaluation(5, "Super film !", film1));
    film1.setSetEvaluations(evaluations1);
  }
  
  /**
   * Teste l'ajout de films dans l'ensemble de films de {@link FonctionsFilms}.
   * Vérifie que les films ajoutés sont bien présents.
   */
  @Test
  void testAjouterFilm() {
    fonctionsFilms.setFilm(film1);
    fonctionsFilms.setFilm(film2);
    
    Set<Film> films = fonctionsFilms.getSetFilm();
    assertEquals(2, films.size(), "L'ensemble doit contenir deux films.");
    assertTrue(films.contains(film1), "L'ensemble doit contenir le film1.");
    assertTrue(films.contains(film2), "L'ensemble doit contenir le film2.");
  }
  
  /**
   * Vérifie qu'un film avec un titre donné peut être récupéré à partir de
   * l'ensemble de films de {@link FonctionsFilms}.
   */
  @Test
  void testGetFilmTrouve() {
    fonctionsFilms.setFilm(film1);
    
    Film result = fonctionsFilms.getFilm("Action Movie");
    assertNotNull(result, "Le film doit être trouvé.");
    assertEquals(film1, result,
        "Le film retourné doit être identique à film1.");
  }
  
  /**
   * Teste la recherche d'un film inexistant dans {@link FonctionsFilms}.
   * Vérifie que la méthode retourne {@code null} pour un titre non trouvé.
   */
  @Test
  void testGetFilmNonTrouve() {
    fonctionsFilms.setFilm(film1);
    
    Film result = fonctionsFilms.getFilm("Unknown Movie");
    assertNull(result,
        "La méthode doit retourner null pour un film inexistant.");
  }
  
  /**
   * Vérifie que la méthode {@link FonctionsFilms#ensembleFilms()} retourne tous
   * les films ajoutés ou {@code null} si aucun film n'a été ajouté.
   */
  @Test
  void testEnsembleFilms() {
    assertNull(fonctionsFilms.ensembleFilms(),
        "Sans films, la méthode doit retourner null.");
    
    fonctionsFilms.setFilm(film1);
    Set<Film> films = fonctionsFilms.ensembleFilms();
    assertNotNull(films,
        "Après ajout, l'ensemble des films ne doit pas être null.");
    assertEquals(1, films.size(), "L'ensemble doit contenir un seul film.");
  }
  
  /**
   * Vérifie que les évaluations d'un film valide sont retournées correctement
   * par {@link FonctionsFilms#ensembleEvaluationsFilm(Film)}.
   */
  @Test
  void testEnsembleEvaluationsFilm() {
    fonctionsFilms.setFilm(film1);
    
    Set<Evaluation> evaluations = fonctionsFilms.ensembleEvaluationsFilm(film1);
    assertNotNull(evaluations,
        "Les évaluations du film doivent être renvoyées.");
    assertEquals(1, evaluations.size(), "Le film doit avoir une évaluation.");
  }
  
  /**
   * Vérifie que la méthode {@link FonctionsFilms#ensembleEvaluationsFilm(Film)}
   * retourne {@code null} pour un film invalide ou null.
   */
  @Test
  void testEnsembleEvaluationsFilmInvalide() {
    Set<Evaluation> evaluations =
        fonctionsFilms.ensembleEvaluationsFilm((Film) null);
    assertNull(evaluations,
        "Pour un film null, la méthode doit retourner null.");
  }
  
  /**
   * Vérifie que la méthode {@link FonctionsFilms#ensembleFilmsGenre(Genre)}
   * retourne les films d'un genre spécifique correctement.
   */
  @Test
  void testEnsembleFilmsGenre() {
    fonctionsFilms.setFilm(film1);
    fonctionsFilms.setFilm(film2);
    
    Set<Film> actionFilms = fonctionsFilms.ensembleFilmsGenre(Genre.Action);
    assertNotNull(actionFilms,
        "L'ensemble des films d'Action ne doit pas être null.");
    assertTrue(actionFilms.contains(film1),
        "Le film d'Action doit être inclus.");
    assertFalse(actionFilms.contains(film2),
        "Le film de Comédie ne doit pas être inclus.");
  }
  
  /**
   * Vérifie que la méthode {@link FonctionsFilms#evaluationMoyenne(Film)}
   * calcule correctement la moyenne des évaluations pour un film valide.
   */
  @Test
  void testEvaluationMoyenneValide() {
    fonctionsFilms.setFilm(film1);
    
    double moyenne = fonctionsFilms.evaluationMoyenne(film1);
    assertEquals(5.0, moyenne, 0.01,
        "La moyenne des évaluations doit être correcte.");
  }
  
  /**
   * Vérifie que la méthode {@link FonctionsFilms#evaluationMoyenne(Film)}
   * retourne {@code -2} lorsqu'un film invalide (null) est passé en paramètre.
   */
  @Test
  void testEvaluationMoyenneFilmInvalide() {
    double moyenne = fonctionsFilms.evaluationMoyenne(null);
    assertEquals(-2, moyenne,
        "Pour un film invalide, la méthode doit retourner -2.");
  }
  
  /**
   * Vérifie que la méthode {@link FonctionsFilms#evaluationMoyenne(Film)}
   * retourne {@code -1} lorsqu'un film n'a aucune évaluation.
   */
  @Test
  void testEvaluationMoyenneSansEvaluations() {
    fonctionsFilms.setFilm(film2); // film2 n'a pas d'évaluations
    
    double moyenne = fonctionsFilms.evaluationMoyenne(film2);
    assertEquals(-1, moyenne,
        "Pour un film sans évaluations, la méthode doit retourner -1.");
  }
}
