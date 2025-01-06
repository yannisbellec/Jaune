package ui;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import location.FonctionsAdministrateur;
import location.FonctionsUtilisateur;

/**
 * Classe qui lance l'interface graphique de l'application.
 *
 * @author Eric Cariou
 */
public final class MainInterface extends Application {
  
  /**
   * Affiche la fenêtre de l'utilisateur pour louer des films.
   */
  @Override
  public void start(Stage primaryStage) {
    // Créer les objets nécessaires pour l'initialisation
    FonctionsAdministrateur foncAdmin = new FonctionsAdministrateur();
    FonctionsUtilisateur foncUtilisateur =
        new FonctionsUtilisateur(foncAdmin.foncFilm, foncAdmin.foncArtiste);
    
    // Lancer la fenêtre d'administration
    this.startFenetreFormation(primaryStage,foncAdmin);
    
    // Lancer la fenêtre utilisateur en passant l'instance de
    // FonctionsUtilisateur
    this.startFenetreEtudiants(foncUtilisateur);
  }
  
  private void startFenetreEtudiants(FonctionsUtilisateur foncUtilisateur) {
    try {
      URL url = getClass().getResource("utilisateur.fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(url);
      VBox root = (VBox) fxmlLoader.load();
      
      // Passer l'instance de FonctionsUtilisateur au contrôleur de la vue
      // utilisateur
      UtilisateurControleur utilisateurControleur = fxmlLoader.getController();
      utilisateurControleur.setFonctionsUtilisateur(foncUtilisateur);
      
      Scene scene = new Scene(root, 1200, 650);
      Stage stage = new Stage();
      stage.setResizable(true);
      stage.setTitle("Location de films");
      
      stage.setScene(scene);
      stage.show();
      
    } catch (IOException e) {
      System.err
          .println("Erreur au chargement de la fenêtre utilisateur : " + e);
    }
  }
  
  private void startFenetreFormation(Stage primaryStage, FonctionsAdministrateur foncAdmin) {
    try {
      URL url = getClass().getResource("administration.fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(url);
      VBox root = (VBox) fxmlLoader.load();
      
      Scene scene = new Scene(root, 615, 725);
      
      primaryStage.setScene(scene);
      primaryStage.setResizable(true);
      primaryStage.setTitle("Administration des films");
      primaryStage.show();
      
   // Passer l'instance de FonctionsUtilisateur au contrôleur de la vue
      // utilisateur
      AdministrationControleur administrationControleur = fxmlLoader.getController();
      administrationControleur.setFonctionsAdministrateur(foncAdmin);      
      
      
    } catch (IOException e) {
      System.err
          .println("Erreur au chargement de la fenêtre administration : " + e);
    }
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}
