package es.codeurjc.ais.nitflex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.codeurjc.ais.nitflex.film.Film;
import es.codeurjc.ais.nitflex.film.FilmRepository;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseInitializer {


	private FilmRepository filmRepository;
	Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

	@Autowired
	public DatabaseInitializer(FilmRepository filmRepository){
		this.filmRepository = filmRepository;
	}
	@PostConstruct
	public void init() {


		// Sample films

		if (!isTestingEnviroment()){
			filmRepository.save(new Film("Dune", "En un lejano futuro, la galaxia conocida es gobernada mediante un sistema feudal de casas nobles bajo el mandato del Emperador.", 2021, "https://www.themoviedb.org/t/p/w220_and_h330_face/m6XWQpT0biTpe5wBGWd60RXmtEX.jpg"));
			filmRepository.save(new Film("Interstellar", "Narra las aventuras de un grupo de exploradores que hacen uso de un agujero de gusano recientemente descubierto para superar las limitaciones de los viajes espaciales tripulados y vencer las inmensas distancias que tiene un viaje interestelar.", 2014, "https://www.themoviedb.org/t/p/w220_and_h330_face/nrSaXF39nDfAAeLKksRCyvSzI2a.jpg"));
			filmRepository.save(new Film("Django", "Dos años antes de estallar la Guerra Civil (1861-1865), Schultz, un cazarrecompensas alemán que le sigue la pista a unos asesinos, le promete al esclavo Django dejarlo en libertad si le ayuda a atraparlos.", 2013, "https://www.themoviedb.org/t/p/w220_and_h330_face/naaYX64yMGzEFsATOFQDaxxQWbJ.jpg"));
		}

	}

	private boolean isTestingEnviroment(){
		try {
            Class.forName("org.junit.jupiter.api.Test");
			logger.info("TEST ENV");
			return true;
        } catch (ClassNotFoundException e) {
			logger.info("PRODUCTION ENV");
            return false;
        }
	}

}
