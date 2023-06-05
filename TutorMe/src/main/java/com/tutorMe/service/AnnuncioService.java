package com.tutorMe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.auth.entity.User;
import com.auth.repository.UserRepository;
import com.tutorMe.enums.TipoLezione;
import com.tutorMe.model.Annuncio;
import com.tutorMe.model.Prenotazione;
import com.tutorMe.repository.AnnuncioRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AnnuncioService {
	
	@Autowired AnnuncioRepository annuncioRepository;
	@Autowired UserRepository userRepository;
	
	public String addAnnuncio(Annuncio a) {
		if(annuncioRepository.existsById(a.getId())) {
			throw new EntityExistsException("Annuncio già esistente con questo ID");
		}
		annuncioRepository.save(a);
		return "Annuncio aggiunto correttamente";
	}
	
	public List<Annuncio> findAllAnnunci(){
		return annuncioRepository.findAll();
	}
	
	public Annuncio editAnnuncio(Annuncio annuncio) {
		if(!annuncioRepository.existsById(annuncio.getId())) {
			throw new EntityNotFoundException("Impossibile aggiornare l'annuncio poichè non esiste un annuncio con questo ID");
		}
		return annuncioRepository.save(annuncio);
	}
	
	public Annuncio findById(Long id) {
		if(!annuncioRepository.existsById(id)) {
			throw new EntityExistsException(" Nessun Annuncio esistente con questo ID");
		}
		return annuncioRepository.findById(id).get();
	}
	
	public Page<Annuncio> getAllAnnunciPageable(Pageable pageable){
		if(annuncioRepository.findAll().isEmpty()) {
			throw new EntityExistsException(" Nessun Annuncio esistente");
		}
		return annuncioRepository.findAll(pageable);
	}
	
	public List<Annuncio> findByMateria ( String materia){
		if(annuncioRepository.findAll().isEmpty()) {
			throw new EntityExistsException(" Nessun Annuncio esistente per questa Materia");
		}
		return annuncioRepository.findByMateria(materia);
	}
	
	public List<Annuncio> findByTipoLezione(TipoLezione tipo){
		if(annuncioRepository.findAll().isEmpty()) {
			throw new EntityExistsException(" Nessun Annuncio esistente");
		}
		return annuncioRepository.findByTipoAnnuncio(tipo);
	}
	
	public List<Annuncio> findByTariffa (Integer tariffaOraria){
		if(annuncioRepository.findAll().isEmpty()) {
			throw new EntityExistsException(" Nessun Annuncio esistente");
		}
		return annuncioRepository.findByTariffaOraria(tariffaOraria);
	}
	
	
	public Annuncio associaAnnuncioAuser(String username, Annuncio a) {
		if(userRepository.existsByUsername(username)) {
			User u = userRepository.findByUsername(username).get();
			u.getListaAnnunci().add(a);
			a.setUser(u);
			return annuncioRepository.save(a);
		}else {
			throw new EntityNotFoundException("Nessun utente presente con questo Username");
		}
	}
	
	public String eliminaAnnuncio (Long id) {
		if(!annuncioRepository.existsById(id)) {
			throw new EntityNotFoundException("Nessun Annuncio esistente con questo ID");
		}
		annuncioRepository.deleteById(id);
		return "Annuncio eliminato correttamente";
	}
	
}