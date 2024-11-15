package location;

import java.util.ArrayList;
import java.util.List;

public class FonctionsFilms {
    private List<Film> listFilms = new ArrayList<Film>();
    private FonctionsArtistes foncArtiste;
    
    
    public FonctionsFilms() {
    	this.listFilms = new ArrayList<Film>();
    	this.foncArtiste = null;
    }
    
    public void setFoncArtiste(FonctionsArtistes foncArtiste) {
    	this.foncArtiste = foncArtiste;
    }

}
