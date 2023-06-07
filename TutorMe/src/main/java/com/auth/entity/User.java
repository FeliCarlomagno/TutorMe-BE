package com.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tutorMe.model.Annuncio;
import com.tutorMe.model.Prenotazione;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false, unique = true)
    private String email;
    
//    private Integer numeroTelefono;
    
    @Column(nullable = false)
    private String password;
    
 //   private String citta;
    
    @Column(length = 2048)
    private String descrizione;
    
    @Lob
    private byte[] image;
         
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();
   
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.MERGE)
   private List<Annuncio> listaAnnunci;
   
   @OneToMany (fetch = FetchType.EAGER, mappedBy = "userPrenotante", cascade = CascadeType.MERGE)
   @JsonIgnoreProperties({"userPrenotante"})
   private List<Prenotazione> listaPrenotazioni;
   
   
   @OneToMany(fetch = FetchType.EAGER, mappedBy = "insegnante", cascade = CascadeType.MERGE)
   @JsonIgnoreProperties({"insegnante"})
   private List<Prenotazione> listaPrenotazioniInsegnante;
}
