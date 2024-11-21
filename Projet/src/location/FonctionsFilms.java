package location;

import java.util.*;

public class FonctionsFilms 
{
    private Set<Film> setFilm;
    private FonctionsArtistes foncArtiste;
    
    public FonctionsFilms() 
    {
    	this.setFilm = new HashSet<>();
    	this.foncArtiste = null;
    }
      
    public void setFoncArtiste(FonctionsArtistes foncArtiste) {
    	this.foncArtiste = foncArtiste;
    }
    
    Set<Film> getSetFilm()
    {
    	return this.setFilm;
    }
    
	public void setSetFilm(Set<Film> setFilm) {
	    if (setFilm != null) {
	        this.setFilm = setFilm;
	    }
	}

	public void setFilm(Film film) {
	    if (film != null) {
	        this.setFilm.add(film);
	    }
	}
}
