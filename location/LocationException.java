package location;

/**
 * Exception levée quand il y a un problème lors de la location d'un film par un
 * utilisateur.
 *
 * @author Eric Cariou
 */
public class LocationException extends Exception {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = -3365565475174636290L;
  
  /**
   * Constructeur sans message spécifique.
   */
  LocationException() {
    super("Location exception");
  }
  
  /**
   * Constructeur avec message.
   *
   * @param message le message de l'exception
   */
  LocationException(String message) {
    super(message);
  }
}
