package com.tutorMe.configuration;

import java.time.LocalDate;
import java.util.Date;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.auth.entity.User;
import com.tutorMe.model.Prenotazione;

@Configuration
public class PrenotazioneConfiguration {
	
	@Bean
	@Scope("prototype")
	public Prenotazione creaPrenotazione() {
		return new Prenotazione();
	}
	
	@Bean("prenotazioneCustom")
	@Scope("prototype")
	public Prenotazione prenotazioneCustom(User userPrenotante,LocalDate dataPrenotazione) {
		Prenotazione p = new Prenotazione();
		p.setUserPrenotante(userPrenotante);
		p.setDataPrenotazione(dataPrenotazione);

		
		return p;
	}
	
}