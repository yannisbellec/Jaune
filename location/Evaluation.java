package location;

/**
 * Représente une évaluation avec une note (sur 5) et un commentaire sur un
 * film.
 * 
 * <p>Cette classe permet d'associer une note et un commentaire à un film, tout en
 * mettant à jour les statistiques du film (par exemple, la moyenne des notes).
 * </p>
 *
 * @author De Brito Cerqueira Alexandre
 */
public class Evaluation {
  
  /** Note de l'évaluation, comprise entre 0 et 5. */
  private int note;
  
  /** Commentaire associé à l'évaluation. */
  private String commentaire;
  
  /*
   * Utilisateur ayant effectué l'évaluation.
   */
  private Utilisateur utilisateur;
  
  /** Film auquel l'évaluation est associée. */
  private Film film;
  
  /**
   * Constructeur d'une évaluation avec note, commentaire, et film.
   *
   * @param note la note donnée, comprise entre 0 et 5
   * @param commentaire le commentaire associé à l'évaluation
   * @param film le film évalué
   */
  public Evaluation(int note, String commentaire, Utilisateur utilisateur,
      Film film) {
    setNote(note);
    this.commentaire = commentaire;
    this.utilisateur = utilisateur;
    this.film = film;
  }
  
  /**
   * Définit la note de l'évaluation. Si la note dépasse 5, elle est fixée à 5.
   * Si elle est inférieure à 0, elle est fixée à 0.
   *
   * @param note la note donnée
   */
  public void setNote(int note) {
    if (note > 5) {
      this.note = 5;
    } else if (note < 0) {
      this.note = 0;
    } else {
      this.note = note;
    }
  }
  
  /**
   * Retourne la note de l'évaluation.
   *
   * @return la note de l'évaluation
   */
  public int getNote() {
    return this.note;
  }
  
  /**
   * Retourne le commentaire associé à l'évaluation.
   *
   * @return le commentaire de l'évaluation
   */
  public String getCommentaire() {
    return this.commentaire;
  }
  
  /**
   * Modifie le commentaire associé à l'évaluation.
   *
   * @param commentaire le nouveau commentaire
   */
  public void setCommentaire(String commentaire) {
    this.commentaire = commentaire;
  }
  
  /**
   * Associe un film à l'évaluation.
   *
   * @param film le film à associer
   */
  public void setFilm(Film film) {
    this.film = film;
  }
  
  /**
   * Retourne le film associé à l'évaluation.
   *
   * @return le film évalué
   */
  public Film getFilm() {
    return this.film;
  }
  
  /**
   * Retourne l'utilisateur qui a émis cette évaluation.
   *
   * @return l'utilisateur qui a émis cette évaluation.
   */
  public Utilisateur getUtilisateur() {
    return this.utilisateur;
  }
  
  @Override
  public String toString() {
    return this.utilisateur.toString() + " : " + this.note;
  }
}
