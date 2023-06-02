package com.tutorMe.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.auth.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="prenotazioni")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Prenotazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JsonIgnoreProperties({"listaAnnunci","listaPrenotazioni","password","citta","roles"})
	private User insegnante;
	
	@ManyToOne
	@JsonIgnoreProperties({"listaAnnunci","listaPrenotazioni","password","citta","roles"})
	private User userPrenotante;
	
	@Column(nullable = false)
	private LocalDate dataPrenotazione;
	
	private String descrizionePrenotazione;
		
	@ManyToOne
	@JsonIgnore
	private Annuncio annuncio;

	public Prenotazione(User userPrenotante,LocalDate dataPrenotazione, String descrizionePrenotazione) {
		super();
		this.dataPrenotazione = dataPrenotazione;
		this.descrizionePrenotazione = descrizionePrenotazione;
		
	}

	

}