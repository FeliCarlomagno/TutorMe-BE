package com.tutorMe.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.entity.User;
import com.tutorMe.model.Annuncio;
import com.tutorMe.model.Prenotazione;
import com.tutorMe.service.PrenotazioneService;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
	
	@Autowired PrenotazioneService prenotazioneService;
	
	@GetMapping("/listaPrenotazioni")
	@PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
	public ResponseEntity<?> recuperaPrenotazioni(){
		return new ResponseEntity<>(prenotazioneService.findAllPrenotazioni(), HttpStatus.OK);
	}
	
	
	@GetMapping("/listaPrenotazioniPageable")
	@PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
	public ResponseEntity<?> recuperaPrenotazioniPageable(Pageable pageable){
		try {
			return new ResponseEntity<> (prenotazioneService.getAllPrenotazioniPageable(pageable), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> (e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> registraPrenotazione(@RequestBody Prenotazione prenotazione){
		try {
			return new ResponseEntity<>(prenotazioneService.addPrenotazione(prenotazione), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/trovaPrenotazione/{id}")
	@PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
	public ResponseEntity<?> trovaPrenotazione(@PathVariable Long id){
		try {
			return new ResponseEntity<>(prenotazioneService.findById(id),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/trovaPrenotazioneByStudente/{idStudente}")
	@PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
	public ResponseEntity<?> trovaPrenotazioneDaUtente(@PathVariable Long idUtente){
		try {
			return new ResponseEntity<>(prenotazioneService.findByUtente(idUtente), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	

	
	@GetMapping("/trovaPrenotazioneByData/{data}")
	@PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
	public ResponseEntity<?> trovaPrenotazioneDaData(@PathVariable Date data){
		try {
			return new ResponseEntity<>(prenotazioneService.findByData(data), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> modificaPrenotazione(@RequestBody Prenotazione p, Long id){
		p.setId(id);
		try {
			return new ResponseEntity<>(prenotazioneService.editPrenotazione(p), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//METODO FUNZIONANTE MA NON SETTO LO USER PRENOTANTE:
	@PostMapping("/effettuaPrenotazione/{idAnnuncio}/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> effettuaPrenotazione(@RequestBody Prenotazione p, @PathVariable Long idAnnuncio, @PathVariable String username){
		try {
			return new ResponseEntity<>(prenotazioneService.creaPrenotazioneDaAnnuncio(p, idAnnuncio, username), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/eliminaPrenotazione/{idPrenotazione}")
	@PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
	public ResponseEntity<?> eliminaPrenotazione(@PathVariable Long idPrenotazione){
		try {
			return new ResponseEntity<>(prenotazioneService.deletePrenotazione(idPrenotazione),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

		
}
