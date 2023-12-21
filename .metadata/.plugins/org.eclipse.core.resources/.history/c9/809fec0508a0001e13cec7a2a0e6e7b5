package com.tutorMe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.auth.entity.User;
import com.tutorMe.enums.TipoLezione;
import com.tutorMe.model.Annuncio;

@Repository
public interface AnnuncioRepository extends JpaRepository<Annuncio, Long>, PagingAndSortingRepository<Annuncio, Long> {
	@Query ("SELECT a FROM Annuncio a WHERE a.listaMaterie = :nomeMateria")
	List<Annuncio> findByMateria (String nomeMateria);
	
	@Query("SELECT a FROM Annuncio a WHERE a.tipoLezione = :tipoLezione")
	List<Annuncio> findByTipoAnnuncio (TipoLezione tipoLezione);
	
	@Query("SELECT a FROM Annuncio a WHERE a.tariffaOraria = :tariffaOraria")
	List<Annuncio> findByTariffaOraria (Integer tariffaOraria);
	
	@Query("SELECT a FROM Annuncio a WHERE a.user = :user")
	List<Annuncio> findByUser (User user);
}

