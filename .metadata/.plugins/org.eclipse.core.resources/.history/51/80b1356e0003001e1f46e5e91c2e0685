package com.tutorMe.runner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.auth.entity.User;
import com.auth.repository.UserRepository;
import com.tutorMe.enums.TipoLezione;
import com.tutorMe.model.Annuncio;
import com.tutorMe.model.Prenotazione;
import com.tutorMe.repository.AnnuncioRepository;
import com.tutorMe.repository.PrenotazioneRepository;
import com.tutorMe.service.AnnuncioService;
import com.tutorMe.service.PrenotazioneService;

@Component
public class Runner implements ApplicationRunner {
	
	@Autowired UserRepository userRepo;
	@Autowired PrenotazioneRepository prenotazioneRepo;
	@Autowired PrenotazioneService prenotazioneService;
	@Autowired AnnuncioRepository annuncioRepo;
	@Autowired AnnuncioService annuncioService;
	
	@Autowired @Qualifier ("userCustom") ObjectProvider<User> user;
	@Autowired @Qualifier ("prenotazioneCustom") ObjectProvider<Prenotazione> prenotazione;
	@Autowired @Qualifier ("annuncioCustom") ObjectProvider<Annuncio> annuncio;

	@Override
	public void run(ApplicationArguments args) throws Exception {
	
		User u = user.getObject("Andre", "123","a.123@example.com","qwerty");
		System.out.println(u);
//		userRepo.save(u);
		
		User u2 = user.getObject("Federico", "Ottaviano","F.Ottaviano","qwerty");
		System.out.println(u);
		//userRepo.save(u2);
		
		List<String> listaMaterie = new ArrayList<>();
		listaMaterie.add("Italiano");
		
		List<TipoLezione> tipoLezione = new ArrayList<>();
		tipoLezione.add(TipoLezione.ONLINE);
		
	     Annuncio a = annuncio.getObject(userRepo.findById(1l).get(),listaMaterie, "nuovoAnnuncio","descrizione prova", 15,tipoLezione);
		 //annuncioRepo.save(a);
		
		
		
	}

}
