package com.tutorMe.model;


import java.util.ArrayList;
import java.util.List;

import com.auth.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tutorMe.enums.TipoLezione;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="annunci")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Annuncio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonIgnoreProperties({"id","listaAnnunci","citta","password", "listaMaterie","roles","listaPrenotazioni"})
	private User user;
	
	@Column(nullable = false)
	private List<String> listaMaterie;
	
	@Column(nullable = false)
	private String titoloAnnuncio;
	
	@Column(nullable = false)
	private String descrizioneAnnuncio;
	
	@Column(nullable = false)
	private Integer tariffaOraria;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private List<TipoLezione> tipoLezione ;
	
	@OneToMany(mappedBy = "annuncio", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"userPrenotato"})
	private List<Prenotazione> listaPrenotazioniAnnuncio;
	

		public Annuncio(User u,List<String> listaMaterie, String titoloAnnuncio, String descrizioneAnnuncio, Integer tariffaOraria
				,List<TipoLezione> tipoLezione, List<Prenotazione> listaPrenotazioniAnnuncio
				) {
			super();
			this.user = u;
			this.listaMaterie = listaMaterie;
			this.titoloAnnuncio = titoloAnnuncio;
			this.descrizioneAnnuncio = descrizioneAnnuncio;
			this.tariffaOraria = tariffaOraria;
			this.tipoLezione = tipoLezione;
			this.listaPrenotazioniAnnuncio = listaPrenotazioniAnnuncio;
		}
	
	

}
