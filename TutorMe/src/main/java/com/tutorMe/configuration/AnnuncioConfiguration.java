package com.tutorMe.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.auth.entity.User;
import com.tutorMe.enums.TipoLezione;
import com.tutorMe.model.Annuncio;

@Configuration
public class AnnuncioConfiguration {
	
	@Bean
	@Scope("prototype")
	public Annuncio creaAnnuncio() {
		return new Annuncio();
	}
	
	@Bean("annuncioCustom")
	@Scope("prototype")
	public Annuncio annuncioCustom(User u, List<String>listaMaterie,String titoloAnnuncio, String descrizioneAnnuncio,Integer tariffaOraria
			,List<TipoLezione> tipoLezione
			) {
		Annuncio a = new Annuncio();
		a.setUser(u);
		a.setListaMaterie(listaMaterie);
		a.setTitoloAnnuncio(titoloAnnuncio);
		a.setDescrizioneAnnuncio(descrizioneAnnuncio);
		a.setTariffaOraria(tariffaOraria);
		a.setTipoLezione(tipoLezione);
		
		return a;
	}
}
