package com.tutorMe.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.auth.entity.User;
import com.auth.repository.UserRepository;
import com.tutorMe.model.Annuncio;
import com.tutorMe.model.Prenotazione;
import com.tutorMe.repository.AnnuncioRepository;
import com.tutorMe.repository.PrenotazioneRepository;


import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PrenotazioneService {
	
	@Autowired PrenotazioneRepository prenotazioneRepository;;
	@Autowired UserRepository userRepository;
	@Autowired AnnuncioRepository annuncioRepsitory;
	
	//@Autowired @Qualifier ("prenotazioneCustom") ObjectProvider<Prenotazione> prenotazione;
	
	public String addPrenotazione(Prenotazione prenotazione) {
		if(prenotazioneRepository.existsById(prenotazione.getId())) {
			throw new EntityExistsException("Prenotazione già esistente con questo ID");
		}
		prenotazioneRepository.save(prenotazione);
		return "Prenotazione aggiunta correttamente!";
	}
	
	public List<Prenotazione> findAllPrenotazioni(){
		return prenotazioneRepository.findAll();
	}
	
	public Prenotazione findById(Long id) {
		if(!prenotazioneRepository.existsById(id)) {
			throw new EntityNotFoundException("Nessuna prenotazione esistente con questo ID");
		}
		return prenotazioneRepository.findById(id).get();
	}
	
	public Page<Prenotazione> getAllPrenotazioniPageable(Pageable pageable){
		if(prenotazioneRepository.findAll().isEmpty()) {
			throw new EntityNotFoundException("Nessuna prenotazione esistente nel Database");
		}
		return prenotazioneRepository.findAll(pageable);
	}
	
	//METODO FINDBYUTENTE:
	public List<Prenotazione> findByUtente(Long idUser){
		if(!userRepository.existsById(idUser)) {
			throw new EntityNotFoundException("Nessuna prenotazione associata a questo Studente ");	
		} return prenotazioneRepository.findByPrenotazioneAndStudente(idUser);
	}
	
	
	
	// METODO FINDBYDATA:
	public List<Prenotazione> findByData(Date data){
		return prenotazioneRepository.findByDataPrenotazione(data);
	}
	
	//METODO ELIMINA PRENOTAZIONE:
	public String deletePrenotazione(Long id) {
		if(!prenotazioneRepository.existsById(id)) {
			throw new EntityNotFoundException("Nessuna Prenotazione esistente con questo ID");
		}
		prenotazioneRepository.deleteById(id);
		return "Prenotazione eliminata con successo";
	}
	
	public Prenotazione editPrenotazione(Prenotazione prenotazione) {
		if(!prenotazioneRepository.existsById(prenotazione.getId())) {
			throw new EntityNotFoundException("Impossibile aggiornare la prenotazione poichè non esiste una prenotazione con questo ID");
		}
		return prenotazioneRepository.save(prenotazione);
	}
	
	public List<Prenotazione> findByUserAndDate(User u, Date data){
		return prenotazioneRepository.listByUserAndDate(u, data);
	}
	
//	METODO FUNZIONANTE MA NON SETTA LO USER PRENOTANTE
	public Prenotazione creaPrenotazioneDaAnnuncio(Prenotazione p, Long idAnnuncio, String username) {
		
		User user = userRepository.findByUsername(username).get();
		Annuncio annuncio = annuncioRepsitory.findById(idAnnuncio).get();
		if(annuncio.getUser() == user) {
			throw new EntityNotFoundException("Non puoi prenotare il tuo stesso annuncio");
		}
		annuncio.getListaPrenotazioniAnnuncio().forEach(a ->  {if(a.getUserPrenotante() == user) {
			throw new EntityNotFoundException("L'utente ha gia prenotato questo annuncio");
		} });
		if(annuncioRepsitory.existsById(idAnnuncio) && userRepository.existsByUsername(username)) {
			Annuncio a = annuncioRepsitory.findById(idAnnuncio).get();
			User u = userRepository.findByUsername(username).get();
			a.getListaPrenotazioniAnnuncio().add(p);
			u.getListaPrenotazioni().add(p);
			p.setUserPrenotante(u);
			p.setInsegnante(annuncio.getUser());
			p.setAnnuncio(a);
		return prenotazioneRepository.save(p);
		}else {
			throw new EntityNotFoundException("Annuncio o Utente non esistenti");
		}

	}
	
}
