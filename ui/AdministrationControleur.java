package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import location.Artiste;
import location.Film;
import location.FonctionsAdministrateur;
import location.Genre;



/**
 * Controleur JavaFX de la fenêtre d'administration.
 *
 * @author Eric Cariou
 *
 */
public class AdministrationControleur {
  
  private FonctionsAdministrateur foncAdmin;
  
  @FXML
  private CheckBox checkBoxLocationFilm;
  
  @FXML
  private TextField entreeAffiche;
  
  @FXML
  private TextField entreeAnneeFilm;
  
  @FXML
  private TextField entreeNationaliteArtiste;
  
  @FXML
  private TextField entreeNomArtiste;
  
  @FXML
  private TextField entreeNomPrenomRealisateur;
  
  @FXML
  private TextField entreePrenomArtiste;
  
  @FXML
  private TextField entreeTitreFilm;
  
  @FXML
  private Label labelListeArtistes;
  
  @FXML
  private Label labelListeFilms;
  
  @FXML
  private ListView<String> listeArtistes;
  
  @FXML
  private ChoiceBox<String> listeChoixAgeLimite;
  
  @FXML
  private ListView<String> listeFilms;
  
  @FXML
  private ListView<String> listeGenresFilm;
  
  @FXML
  private ListView<String> listeTousGenres;
  
  @FXML
  void actionBoutonAfficherArtistesActeurs(ActionEvent event) {
    this.listeArtistes.getItems().clear();
    this.labelListeArtistes.setText("Les acteurs");
    for (Artiste real : this.foncAdmin.foncArtiste.getSetActeur()) {
      this.listeArtistes.getItems().add(real.toString());
    }
  }
  
  @FXML
  void actionBoutonAfficherArtistesRealisateurs(ActionEvent event) {
    this.listeArtistes.getItems().clear();
    this.labelListeArtistes.setText("Les realisateurs");
    for (Artiste real : this.foncAdmin.foncArtiste.getSetRealisateur()) {
      this.listeArtistes.getItems().add(real.toString());
    }
  }
  
  @FXML
  void actionBoutonAfficherFilmsActeurSelectionne(ActionEvent event) {
    
    this.listeFilms.getItems().clear();
    this.labelListeFilms.setText("Films de l'acteur selectionné");
    
    String artisteSelectionne =
        this.listeArtistes.getSelectionModel().getSelectedItem();
    
    if (artisteSelectionne == null || artisteSelectionne.isEmpty()
        || !artisteSelectionne.contains("-")) {
      System.out.println("Erreur de sélection ou format incorrect.");
      return;
    }
    
    // Séparer la nationalité, le nom et le prénom
    int premierTiret = artisteSelectionne.indexOf("-");
    int dernierTiret = artisteSelectionne.lastIndexOf("-");
    
    if (premierTiret == dernierTiret || premierTiret == -1
        || dernierTiret == -1) {
      System.out
          .println("Erreur : Format inattendu pour 'artisteSelectionne'.");
      return;
    }
    
    String prenom = artisteSelectionne.substring(0, premierTiret).trim();
    String nom =
        artisteSelectionne.substring(premierTiret + 1, dernierTiret).trim();
    
    Artiste acteur = this.foncAdmin.foncArtiste.getActeur(nom, prenom);
    if (acteur == null) {
      return;
    }
    this.listeFilms.getItems().clear();
    
    for (Film film : acteur.getFilms()) {
      this.listeFilms.getItems().add(film.toString());
    }
    
  }
  
  @FXML
  void actionBoutonAfficherFilmsDuRealisateur(ActionEvent event) {
    
    this.listeFilms.getItems().clear();
    
    this.labelListeFilms.setText("Film du réalisateur sélectionné");
    if (this.entreeNomPrenomRealisateur.getText() == null
        || this.entreeNomPrenomRealisateur.getText().isEmpty()) {
      System.out.println("Erreur de sélection ou format incorrect.");
      return;
    }
    int premierTiret = this.entreeNomPrenomRealisateur.getText().indexOf("-");
    
    String prenom = this.entreeNomPrenomRealisateur.getText()
        .substring(0, premierTiret).trim();
    String nom = this.entreeNomPrenomRealisateur.getText()
        .substring(premierTiret + 1).trim();
    
    
    
    for (Film film : this.foncAdmin.foncFilm.getSetFilm()) {
      if (film.getRealisateur()
          .equals(this.foncAdmin.foncArtiste.getRealisateur(nom, prenom))) {
        this.listeFilms.getItems().add(film.toString());
      }
    }
    
  }
  
  @FXML
  void actionBoutonAfficherFilmsRealisateurSelectionne(ActionEvent event) {
    
    this.listeFilms.getItems().clear();
    this.labelListeFilms.setText("Films du réalisateur selectionné");
    
    String artisteSelectionne =
        this.listeArtistes.getSelectionModel().getSelectedItem();
    
    if (artisteSelectionne == null || artisteSelectionne.isEmpty()
        || !artisteSelectionne.contains("-")) {
      System.out.println("Erreur de sélection ou format incorrect.");
      return;
    }
    
    // Séparer la nationalité, le nom et le prénom
    int premierTiret = artisteSelectionne.indexOf("-");
    int dernierTiret = artisteSelectionne.lastIndexOf("-");
    
    if (premierTiret == dernierTiret || premierTiret == -1
        || dernierTiret == -1) {
      System.out
          .println("Erreur : Format inattendu pour 'artisteSelectionne'.");
      return;
    }
    
    String prenom = artisteSelectionne.substring(0, premierTiret).trim();
    String nom =
        artisteSelectionne.substring(premierTiret + 1, dernierTiret).trim();
    
    
    Artiste realisateur =
        this.foncAdmin.foncArtiste.getRealisateur(nom, prenom);
    if (realisateur == null) {
      return;
    }
    
    this.listeFilms.getItems().clear();
    
    for (Film film : realisateur.getFilms()) {
      this.listeFilms.getItems().add(film.toString());
    }
    
  }
  
  @FXML
  void actionBoutonAfficherTousActeursFilm(ActionEvent event) {
    
    this.labelListeArtistes.setText("Liste d'acteur pour le film selectionné");
    
    if (this.listeFilms.getSelectionModel().getSelectedItem() == null) {
      System.out.println("Erreur de selection de film");
      return;
    }
    
    String titre = this.listeFilms.getSelectionModel().getSelectedItem();
    
    Film film = this.foncAdmin.foncFilm.getFilm(titre);
    
    this.listeArtistes.getItems().clear();
    for (Artiste acteur : film.getSetActeur()) {
      this.listeArtistes.getItems().add(acteur.toString());
    }
    
  }
  
  @FXML
  void actionBoutonAfficherTousArtistes(ActionEvent event) {
    this.listeArtistes.getItems().clear();
    this.labelListeArtistes.setText("Les artistes");
    for (Artiste artiste : this.foncAdmin.foncArtiste.getSetArtiste()) {
      this.listeArtistes.getItems().add(artiste.toString());
    }
  }
  
  
  @FXML
  void actionBoutonAjouterActeurFilm(ActionEvent event) {
    
    String nom = this.entreeNomArtiste.getText();
    String prenom = this.entreePrenomArtiste.getText();
    
    Artiste artiste = this.foncAdmin.foncArtiste.getArtiste(nom, prenom);
    
    String titre = this.entreeTitreFilm.getText();
    
    Film film = this.foncAdmin.foncFilm.getFilm(titre);
    
    if (artiste == null || film == null) {
      System.out.println("Ajout d'acteur échouée");
      return;
    }
    
    film.setActeur(artiste);
    
    if (this.foncAdmin.foncArtiste.addActeur(artiste)) {
      System.out.println("Ajout d'acteur réussie");
      return;
    }
    
    System.out.println("Ajout d'acteur : acteur existant dans le film");
    
  }
  
  @FXML
  void actionBoutonAjouterGenreFilm(ActionEvent event) {
    String genreSelectionne =
        this.listeTousGenres.getSelectionModel().getSelectedItem();
    String filmSelectionne =
        this.listeFilms.getSelectionModel().getSelectedItem();
    
    if (genreSelectionne == null || filmSelectionne == null) {
      System.out.println("Genre ou film pas selectionné");
      return;
    }
    
    if (this.foncAdmin.ajouterGenres(
        this.foncAdmin.foncFilm.getFilm(filmSelectionne),
        Genre.valueOf(genreSelectionne))) {
      System.out.println("Ajout de genre réussie");
      this.listeGenresFilm.getItems().clear();
      for (Genre genre : this.foncAdmin.foncFilm.getFilm(filmSelectionne)
          .getSetGenre()) {
        this.listeGenresFilm.getItems().add(genre.toString());
      }
      return;
    }
    System.out.println("Ajout de genre échoué");
  }
  
  @FXML
  void actionBoutonChercherArtiste(ActionEvent event) {
    this.listeArtistes.getItems().clear();
    this.labelListeArtistes.setText("Recherche d'un artiste");
    // Boucle if pour éviter le nullPointerException à cause du toString si
    // jamais getArtiste renvoie null
    if (this.foncAdmin.foncArtiste.getArtiste(this.entreeNomArtiste.getText(),
        this.entreePrenomArtiste.getText()) != null) {
      this.listeArtistes.getItems()
          .add((this.foncAdmin.foncArtiste.getArtiste(
              this.entreeNomArtiste.getText(),
              this.entreePrenomArtiste.getText())).toString());
      this.entreeNationaliteArtiste.setText(
          this.foncAdmin.foncArtiste.getArtiste(this.entreeNomArtiste.getText(),
              this.entreePrenomArtiste.getText()).getNationalite());
    }
  }
  
  @FXML
  void actionBoutonChercherFilm(ActionEvent event) {
    this.listeFilms.getItems().clear();
    this.listeGenresFilm.getItems().clear();
    this.checkBoxLocationFilm.setSelected(false);
    this.listeChoixAgeLimite.setValue(null);
    this.entreeAnneeFilm.setText(null);
    this.entreeNomPrenomRealisateur.setText(null);
    
    this.labelListeFilms.setText("Recherche de film");
    if (this.foncAdmin.foncFilm
        .getFilm(this.entreeTitreFilm.getText()) != null) {
      this.listeFilms.getItems().add((this.foncAdmin.foncFilm
          .getFilm(this.entreeTitreFilm.getText()).toString()));
      this.entreeNomPrenomRealisateur.setText(
          this.foncAdmin.foncFilm.getFilm(this.entreeTitreFilm.getText())
              .getRealisateur().getNom() + "-"
              + this.foncAdmin.foncFilm.getFilm(this.entreeTitreFilm.getText())
                  .getRealisateur().getPrenom());
      this.entreeAnneeFilm.setText(String.valueOf(this.foncAdmin.foncFilm
          .getFilm(this.entreeTitreFilm.getText()).getAnneeReal()));
      this.checkBoxLocationFilm.setSelected(this.foncAdmin.foncFilm
          .getFilm(this.entreeTitreFilm.getText()).getLocation());
      this.listeChoixAgeLimite.setValue(String.valueOf(this.foncAdmin.foncFilm
          .getFilm(this.entreeTitreFilm.getText()).getAgeMin()));
      this.listeGenresFilm.getItems().clear();
      for (Genre genre : this.foncAdmin.foncFilm
          .getFilm(this.entreeTitreFilm.getText()).getSetGenre()) {
        this.listeGenresFilm.getItems().add(genre.toString());
      }
    } else {
      System.out.println("Recherche de film : échouée");
    }
  }
  
  @FXML
  void actionBoutonChoisirArtisteSelectionneRealisateur(ActionEvent event) {
    String nom = this.entreeNomArtiste.getText();
    String prenom = this.entreePrenomArtiste.getText();
    // Remplir les champs de texte
    this.entreeNomPrenomRealisateur.setText(prenom + "-" + nom);
  }
  
  @FXML
  void actionBoutonEnregistrerArtiste(ActionEvent event) {
    Artiste a =
        this.foncAdmin.creerArtiste(this.entreeNomArtiste.getText().trim(),
            this.entreePrenomArtiste.getText().trim(),
            this.entreeNationaliteArtiste.getText().trim());
    
    
    this.actionBoutonAfficherTousArtistes(event);
    if (a == null) {
      System.out.println("Erreur artiste pas créé");
    } else {
      System.out.println("Artiste créé");
    }
  }
  
  @FXML
  void actionBoutonEnregistrerFilm(ActionEvent event) {
    int anneeFilm;
    int ageLimite;
    try {
      ageLimite = Integer.parseInt(this.listeChoixAgeLimite.getValue());
      anneeFilm = Integer.parseInt(this.entreeAnneeFilm.getText().trim());
    } catch (NumberFormatException e) {
      System.out
          .println("Format annee incorrect ou age limite pas selectionne");
      return;
    }
    
    String nom = this.entreeNomArtiste.getText();
    String prenom = this.entreePrenomArtiste.getText();
    boolean location = this.checkBoxLocationFilm.isSelected();
    
    Film f = this.foncAdmin.creerFilm(this.entreeTitreFilm.getText().trim(),
        this.foncAdmin.foncArtiste.getArtiste(nom, prenom), anneeFilm,
        ageLimite, location);
    
    if (f == null) {
      System.out.println("Création de film échouée");
      return;
    }
    
    System.out.println("Création de film réussie");
  }
  
  @FXML
  void actionBoutonNouveauArtiste(ActionEvent event) {
    this.entreePrenomArtiste.clear();
    this.entreeNomArtiste.clear();
    this.entreeNationaliteArtiste.clear();
  }
  
  @FXML
  void actionBoutonNouveauFilm(ActionEvent event) {
    this.entreeNomPrenomRealisateur.clear();
    this.entreeTitreFilm.clear();
    this.listeChoixAgeLimite.setValue(null);
    this.entreeAnneeFilm.clear();
    this.checkBoxLocationFilm.setSelected(false);
    this.listeGenresFilm.getItems().clear();
  }
  
  
  
  @FXML
  void actionBoutonParcourirAffiche(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonSupprimerArtiste(ActionEvent event) {
    
    String nom = this.entreeNomArtiste.getText();
    String prenom = this.entreePrenomArtiste.getText();
    
    boolean suppression = this.foncAdmin
        .supprimerArtiste(this.foncAdmin.foncArtiste.getArtiste(prenom, nom));
    this.actionBoutonAfficherTousArtistes(event);
    
    if (suppression) {
      System.out.println("Suppresion artiste reussie");
    } else {
      System.out.println("Suppresion artiste echouée");
    }
    
  }
  
  
  @FXML
  void actionBoutonSupprimerFilm(ActionEvent event) {
    this.foncAdmin.supprimerFilm(
        this.foncAdmin.foncFilm.getFilm(this.entreeTitreFilm.getText()));
    this.listeFilms.getItems().clear();
  }
  
  @FXML
  void actionMenuApropos(ActionEvent event) {
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
    alert.setTitle("À propos");
    alert.setHeaderText("À propos de l'application");
    alert.setContentText(
        "Cette application permet de gérer des artistes et des films.\n"
            + "Version : 1.0\n" + "Développée par : Jaune");
    
    alert.showAndWait();
  }
  
  
  @FXML
  void actionMenuCharger(ActionEvent event) {
    
  }
  
  @FXML
  void actionMenuQuitter(ActionEvent event) {
    System.exit(0);
  }
  
  @FXML
  void actionMenuSauvegarder(ActionEvent event) {
    
  }
  
  @FXML
  void actionListeSelectionArtiste(MouseEvent event) {
    String artisteSelectionne =
        this.listeArtistes.getSelectionModel().getSelectedItem();
    
    if (artisteSelectionne == null || artisteSelectionne.isEmpty()
        || !artisteSelectionne.contains("-")) {
      System.out.println("Erreur de sélection ou format incorrect.");
      return;
    }
    
    // Séparer la nationalité, le nom et le prénom
    int premierTiret = artisteSelectionne.indexOf("-");
    int dernierTiret = artisteSelectionne.lastIndexOf("-");
    
    if (premierTiret == dernierTiret || premierTiret == -1
        || dernierTiret == -1) {
      System.out
          .println("Erreur : Format inattendu pour 'artisteSelectionne'.");
      return;
    }
    
    String prenom = artisteSelectionne.substring(0, premierTiret).trim();
    String nom =
        artisteSelectionne.substring(premierTiret + 1, dernierTiret).trim();
    String nationalite = artisteSelectionne.substring(dernierTiret + 1).trim();
    
    // Remplir les champs de texte
    this.entreeNationaliteArtiste.setText(nationalite);
    this.entreeNomArtiste.setText(nom);
    this.entreePrenomArtiste.setText(prenom);
  }
  
  @FXML
  void actionListeSelectionFilm(MouseEvent event) {
    String filmSelectionne =
        this.listeFilms.getSelectionModel().getSelectedItem();
    
    if (filmSelectionne == null || filmSelectionne.isEmpty()) {
      System.out.println("Erreur de sélection ou format incorrect.");
      return;
    }
    
    Film film = this.foncAdmin.foncFilm.getFilm(filmSelectionne);
    String nom = film.getRealisateur().getNom().trim();
    String prenom = film.getRealisateur().getPrenom().trim();
    String annee = String.valueOf(film.getAnneeReal());
    
    this.entreeNomPrenomRealisateur.setText(prenom + "-" + nom);
    this.entreeAnneeFilm.setText(annee);
    this.entreeTitreFilm.setText(film.getTitre().trim());
    this.checkBoxLocationFilm.setSelected(film.getLocation());
    this.listeChoixAgeLimite.setValue(String.valueOf(film.getAgeMin()));
    this.listeGenresFilm.getItems().clear();
    for (Genre genre : film.getSetGenre()) {
      this.listeGenresFilm.getItems().add(genre.toString());
    }
  }
  
  @FXML
  void actionCheckBoxChangerLocationFilm() {
    String filmSelectionne =
        this.listeFilms.getSelectionModel().getSelectedItem();
    
    if (filmSelectionne == null || filmSelectionne.isEmpty()) {
      System.out.println("Erreur de sélection.");
      return;
    }
    
    Film film = this.foncAdmin.foncFilm.getFilm(filmSelectionne);
    
    film.setLocation(this.checkBoxLocationFilm.isSelected());
    
    if (this.checkBoxLocationFilm.isSelected()) {
      System.out.println("Film est désormais louable");
    } else {
      System.out.println("Film n'est désormais plus louable");
    }
  }
  
  @FXML
  void initialize() {
    
    this.listeChoixAgeLimite.getItems().add("0");
    this.listeChoixAgeLimite.getItems().add("12");
    this.listeChoixAgeLimite.getItems().add("16");
    this.listeChoixAgeLimite.getItems().add("18");
    
    for (Genre genre : Genre.values()) {
      this.listeTousGenres.getItems().add(genre.toString());
    }
  }
  
  /**
   * Définit l'instance de fonctions Administrateur.
   */
  public void setFonctionsAdministrateur(FonctionsAdministrateur foncAdmin) {
    this.foncAdmin = foncAdmin;
  }
}
