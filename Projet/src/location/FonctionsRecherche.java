package location;

import java.util.HashSet;
import java.util.Set;

/**
 * La classe {@code FonctionsRecherche} permet d'effectuer des recherches 
 * sur les films et les artistes en fonction de divers critères.
 * Elle s'appuie sur les instances des classes {@link FonctionsFilms} 
 * et {@link FonctionsArtistes} pour accéder aux données nécessaires.
 */
public class FonctionsRecherche {

  /** Instance de {@code FonctionsFilms} pour accéder aux données des films. */
  private FonctionsFilms foncFilms;

  /** Instance de {@code FonctionsArtistes} pour accéder aux données des artistes. */
  private FonctionsArtistes foncArtistes;
  /**
     * Constructeur de la classe {@code FonctionsRecherche}.
     * Initialise les instances des classes {@code FonctionsFilms} et {@code FonctionsArtistes}.
     *
     * @param foncFilms l'instance de {@code FonctionsFilms} pour accéder aux films
     * @param foncArtistes l'instance de {@code FonctionsArtistes} pour accéder aux artistes
     */
  
  public FonctionsRecherche(FonctionsFilms foncFilms, FonctionsArtistes foncArtistes) { 
    this.foncArtistes = foncArtistes;
    this.foncFilms = foncFilms;
  }

  /**
     * Cherche un film à partir de son titre.
     *
     * @param titre le titre du film
     * @return le film s'il a été trouvé ou <code>null</code> sinon
     */
  Film getFilm(String titre) { 
    if (titre == null || this.foncFilms.getSetFilm() == null) {
      return null; 
    }
        
    for (Film film : this.foncFilms.getSetFilm()) {
      if (film.getTitre() != null && film.getTitre().equals(titre)) {
        return film;
      }
    }
    return null;
  }

  /**
   * Renvoie l'ensemble des films.
   *
   * @return l'ensemble des films ou <code>null</code> si aucun film n'existe
   */
  Set<Film> ensembleFilms() {
    if (this.foncFilms.getSetFilm() == null || this.foncFilms.getSetFilm().isEmpty()) {
      return null;
    }
    return this.foncFilms.getSetFilm();
  }

  /**
   * Renvoie l'ensemble des évaluations d'un film.
   *
   * @param film le film dont on veut les évaluations
   * @return toutes les évaluations d'un film ou <code>null</code> si aucune
   *         évaluation n'existe ou si le film était invalide
   *         (valeur <code>null</code> par exemple)
   */
  Set<Evaluation> ensembleEvaluationsFilm(Film film) {
    if (film == null || film.getSetEvaluations().isEmpty()) {
      return null;
    } else {
      return film.getSetEvaluations();
    }
  }

  /**
   * Renvoie l'ensemble des évaluations d'un film.
   *
   * @param titre le titre du film dont on veut les évaluations
   * @return toutes les évaluations d'un film ou <code>null</code> si aucune
   *         évaluation n'existe ou si le titre du film était invalide
   */
  Set<Evaluation> ensembleEvaluationsFilm(String titre) {
    if (titre == null) {
      return null;
    }

    Film bonFilm = null;
    for (Film film : foncFilms.getSetFilm()) {
      if (film.getTitre().equals(titre)) {
        bonFilm = film;
        break;
      }
    }

    return ensembleEvaluationsFilm(bonFilm);
  }

  /**
   * Renvoie l'ensemble des films d'un certain acteur.
   *
   * @param acteur l'acteur
   * @return l'ensemble des films de l'acteur ou <code>null</code> si aucun film
   *         n'a été trouvé ou que le paramètre était invalide
   */
  Set<Film> ensembleFilmsActeur(Artiste acteur) {
    if (acteur == null) {
      return null;
    }

    Set<Film> filmsActeur = new HashSet<>();

    for (Film film : foncFilms.getSetFilm()) {
      if (film.getSetArtistes() != null && film.getSetArtistes().contains(acteur)) {
        filmsActeur.add(film);
      }
    }

    return filmsActeur.isEmpty() ? null : filmsActeur;
  }

  /**
   * Renvoie l'ensemble des films d'un certain genre.
   *
   * @param genre le genre du film
   * @return l'ensemble des films du genre ou <code>null</code> si aucun film
   *         n'a été trouvé
   */
  Set<Film> ensembleFilmsGenre(Genre genre) {
    if (genre == null) {
      return null;
    }

    Set<Film> setFilmsGenre = new HashSet<>();

    for (Film film : foncFilms.getSetFilm()) {
      if (film.getSetGenre() != null && film.getSetGenre().contains(genre)) {
        setFilmsGenre.add(film);
      }
    }

    return setFilmsGenre.isEmpty() ? null : setFilmsGenre;
  }

  /**
   * Renvoie l'évaluation moyenne d'un film.
   *
   * @param film le film dont on récupère l'évaluation moyenne
   * @return l'évaluation moyenne du film ou -1 si aucune évaluation n'existe,
   *         ou -2 en cas de film invalide (valeur <code>null</code>)
   */
  double evaluationMoyenne(Film film) {
    if (film == null) {
      return -2;
    } else if (film.getSetEvaluations().isEmpty()) {
      return -1;
    } else {
      return film.getNoteMoyenne();
    }
  }
}