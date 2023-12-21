package com.tutorMe.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.auth.entity.User;
import com.tutorMe.model.Annuncio;
import com.tutorMe.model.Prenotazione;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long>, PagingAndSortingRepository<Prenotazione, Long> {
	@Query("SELECT p FROM Prenotazione p WHERE p.userPrenotante = :idUser")
	List<Prenotazione> findByPrenotazioneAndStudente(Long idUser);
	
	public List<Prenotazione> findByDataPrenotazione(Date dataPrenotazione);
	
	@Query("SELECT p FROM Prenotazione p INNER JOIN p.userPrenotante userPrenotante WHERE userPrenotante = :userPrenotante AND p.dataPrenotazione = :dataPrenotazione")
	public List<Prenotazione> listByUserAndDate(User userPrenotante, Date dataPrenotazione);
	
	@Query ("SELECT p FROM Prenotazione p WHERE p.userPrenotante.id = :userPrenotanteId")
	List<Prenotazione> findPrenotazioneByUserId ( Long userPrenotanteId);
}


