package es.codeurjc.ais.nitflex.rest;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.ais.nitflex.film.Film;
import es.codeurjc.ais.nitflex.film.FilmService;

@RestController
@RequestMapping("/api/films")
public class FilmRestController {

	@Autowired
	private FilmService filmfilmService;

	@GetMapping("/")
	public Collection<Film> getFilms() {
		return filmfilmService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Film> getFilm(@PathVariable long id) {
		
		Optional<Film> op = filmfilmService.findOne(id);
		if(op.isPresent()) {
			Film film = op.get();
			return new ResponseEntity<>(film, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Film createFilm(@RequestBody Film film) {

		return filmfilmService.save(film);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Film> updateFilm(@PathVariable long id, @RequestBody Film updatedFilm) {

		if (filmfilmService.exist(id)) {
			
			updatedFilm.setId(id);
			filmfilmService.save(updatedFilm);

			return new ResponseEntity<>(updatedFilm, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Film> deleteFilm(@PathVariable long id) {

		try {
			filmfilmService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
