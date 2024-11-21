package location;

import java.util.*;

/**
 * Représente un film avec ses attributs : genres, artistes, évaluations, titre, âge minimum, année de réalisation,
 * nombre de locations et la possibilité de location.
 * Cette classe permet de gérer les informations liées à un film.
 * @author Alexandre De Brito
 */
public class Film {
  
	/**
     * Ensemble des genres associés au film.
     */
    private Set<Genre> setGenre;

    /**
     * Ensemble des artistes (acteurs, réalisateurs) associés au film.
     */
    private Set<Artiste> setArtistes;

    /**
     * Ensemble des évaluations du film.
     */
    private Set<Evaluation> setEvaluations;

    /**
     * Le titre du film.
     */
    private String titre;

    /**
     * Indique si le film est disponible en location (true si le film est en location, false sinon).
     */
    private boolean location;

    /**
     * La note moyenne des évaluations du film.
     */
    private double noteMoyenne;

    /**
     * L'âge minimum recommandé pour regarder le film.
     */
    private int ageMin;

    /**
     * L'année de réalisation du film.
     */
    private int anneeReal;

    /**
     * Le nombre de fois que le film a été loué.
     */
    private int nbLoc;
  
    /**
     * Constructeur avec tous les paramètres.
     * Initialise un film avec les genres, artistes, évaluations, location, année de réalisation, âge minimum et titre.
     * 
     * @param genre Set des genres du film
     * @param setArtistes Set des artistes (acteurs, réalisateurs) du film
     * @param setEvaluations Set des évaluations du film
     * @param location Indique si le film est en location
     * @param anneeReal L'année de réalisation du film
     * @param ageMin L'âge minimum recommandé pour regarder le film
     * @param titre Le titre du film
     */
    public Film(Set<Genre> genre, Set<Artiste> setArtistes,boolean location, int anneeReal, int ageMin, String titre) {
        this.setGenre = genre != null ? genre : new HashSet<>();  // Utilise le paramètre ou crée un HashSet vide
        this.setArtistes = setArtistes != null ? setArtistes : new HashSet<>();  // Idem pour les autres ensembles
        this.setEvaluations = new HashSet<>();
        this.location = location;
        this.calculeMoyenne();  // Calcule la moyenne des évaluations
        this.anneeReal = anneeReal;
        this.ageMin = ageMin;
        this.titre = titre;
        this.nbLoc = 0;
    }

    /**
     * Constructeur sans le paramètre 'location'.
     * Appelle le constructeur principal en définissant 'location' par défaut à false.
     *
     * @param setGenre Set des genres du film
     * @param setArtistes Set des artistes du film
     * @param setEvaluations Set des évaluations du film
     * @param anneeReal L'année de réalisation du film
     * @param ageMin L'âge minimum recommandé pour regarder le film
     * @param titre Le titre du film
     */
    public Film(Set<Genre> setGenre, Set<Artiste> setArtistes,int anneeReal, int ageMin, String titre) {
        this(setGenre, setArtistes, false, anneeReal, ageMin, titre);  // Appelle le premier constructeur avec 'location' par défaut à false
    }

    // Section des Getters

    /**
     * Retourne le titre du film.
     *
     * @return le titre du film
     */
    public String getTitre() {
        return this.titre;
    }

    /**
     * Retourne l'âge minimum pour regarder le film.
     *
     * @return l'âge minimum du film
     */
    public int getAgeMin() {
        return this.ageMin;
    }

    /**
     * Retourne l'année de réalisation du film.
     *
     * @return l'année de réalisation
     */
    public int getAnneeReal() {
        return this.anneeReal;
    }

    /**
     * Retourne l'état de la location du film (vrai si le film est disponible en location).
     *
     * @return true si le film est disponible à la location, false sinon
     */
    public boolean getLocation() {
        return this.location;
    }

    /**
     * Retourne la note moyenne des évaluations du film.
     *
     * @return la note moyenne des évaluations
     */
    public double getNoteMoyenne() {
        return this.noteMoyenne;
    }

    /**
     * Retourne le nombre de locations effectuées pour ce film.
     *
     * @return le nombre de locations
     */
    public int getNbLoc() {
        return this.nbLoc;
    }

    /**
     * Retourne l'ensemble des genres associés au film.
     *
     * @return l'ensemble des genres du film
     */
    public Set<Genre> getSetGenre() {
        return this.setGenre;
    }

    /**
     * Retourne l'ensemble des artistes associés au film (acteurs, réalisateurs).
     *
     * @return l'ensemble des artistes du film
     */
    public Set<Artiste> getSetArtistes() {
        return this.setArtistes;
    }

    /**
     * Retourne l'ensemble des évaluations du film.
     *
     * @return l'ensemble des évaluations du film
     */
    public Set<Evaluation> getSetEvaluations() {
        return this.setEvaluations;
    }

    // Section des Setters

    /**
     * Définit l'âge minimum du film.
     *
     * @param ageMin l'âge minimum pour regarder le film
     */
    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    /**
     * Définit l'année de réalisation du film.
     *
     * @param anneeReal l'année de réalisation
     */
    public void setAnneeReal(int anneeReal) {
        this.anneeReal = anneeReal;
    }

    /**
     * Définit l'état de la location du film.
     *
     * @param location l'état de la location du film
     */
    public void setLocation(boolean location) {
        this.location = location;
    }

    /**
     * Définit le nombre de locations du film.
     *
     * @param nbLoc le nombre de locations
     */
    public void setNbLoc(int nbLoc) {
        this.nbLoc = nbLoc;
    }

    /**
     * Définit l'ensemble des genres associés au film.
     *
     * @param setGenre l'ensemble des genres du film
     */
    public void setSetGenre(Set<Genre> setGenre) {
        if (setGenre != null) {
            this.setGenre = setGenre;
        }
    }

    /**
     * Ajoute un genre à l'ensemble des genres du film.
     *
     * @param genre le genre à ajouter
     */
    public void setGenre(Genre genre) {
        if (genre != null) {
            this.setGenre.add(genre);
        }
    }

    /**
     * Définit l'ensemble des artistes associés au film.
     *
     * @param setArtistes l'ensemble des artistes du film
     */
    public void setSetArtistes(Set<Artiste> setArtistes) {
        if (setArtistes != null) {
            this.setArtistes = setArtistes;
        }
    }

    /**
     * Ajoute un artiste à l'ensemble des artistes du film.
     *
     * @param artiste l'artiste à ajouter
     */
    public void setArtiste(Artiste artiste) {
        if (artiste != null) {
            this.setArtistes.add(artiste);
        }
    }

    /**
     * Définit l'ensemble des évaluations du film.
     *
     * @param setEvaluations l'ensemble des évaluations
     */
    public void setSetEvaluations(Set<Evaluation> setEvaluations) {
        if (setEvaluations != null) {
            this.setEvaluations = setEvaluations;
            this.calculeMoyenne(); // Recalcule la moyenne des notes
        }
    }

    /**
     * Ajoute une évaluation à l'ensemble des évaluations du film.
     *
     * @param evaluation l'évaluation à ajouter
     */
    public void setEvaluation(Evaluation evaluation) {
        if (evaluation != null) {
            this.setEvaluations.add(evaluation);
            this.calculeMoyenne(); // Recalcule la moyenne des notes
        }
    }

    /**
     * Calcule la moyenne des notes à partir des évaluations.
     * Si aucune évaluation n'existe, la note moyenne est définie à 0.0.
     */
    public void calculeMoyenne() {
        if (setEvaluations.isEmpty()) {
            this.noteMoyenne = 0.0;
            return;
        }

        // Calcule la somme des notes
        double somme = 0.0;

        for (Evaluation eval : setEvaluations) {
            somme += eval.getNote(); // Ajoute la note de chaque évaluation à la somme
        }

        // Calcule la moyenne
        this.noteMoyenne = somme / setEvaluations.size();
    }
}

