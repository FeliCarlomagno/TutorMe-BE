package com.tutorMe.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.auth.service.AuthServiceImpl;
import com.tutorMe.enums.TipoLezione;
import com.tutorMe.model.Annuncio;
import com.tutorMe.repository.AnnuncioRepository;
import com.tutorMe.service.AnnuncioService;

@RestController
@RequestMapping("/annuncio")
public class AnnuncioController {
	
	@Autowired AuthServiceImpl userServiceImpl;
	@Autowired AnnuncioService annuncioService;
	@Autowired AnnuncioRepository annuncioRepository;
	
	
    @PostMapping("/creaAnnuncio/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> creaAnnuncio(@RequestBody Annuncio a, @PathVariable String username){
    	try {
			return new ResponseEntity<>(annuncioService.associaAnnuncioAuser(username,a),HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }    
    
    
    @GetMapping("/listaAnnunci")
//    @PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
    public ResponseEntity<?> tuttiAnnunci(){
    		return new ResponseEntity<>(annuncioService.findAllAnnunci(), HttpStatus.OK);
    }
    
    @GetMapping("/annuncioById/{idAnnuncio}")
    public ResponseEntity<?> cercaAnnuncio(@PathVariable Long idAnnuncio){
    	try {
			return new ResponseEntity<>(annuncioService.findById(idAnnuncio),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    
    //1- FILTRA ANNUNCI PER MATERIA
    @GetMapping("/listaAnnunciPerMateria/{materia}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> filtraAnnunciPerMateria(@PathVariable String materia){
    	try {
			return new ResponseEntity<>(annuncioService.findByMateria(materia),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    //2 - FILTRA ANNUNCI PER TARIFFA
    @GetMapping("listaAnnunciPerTariffa/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?>filtraAnnuncioPerTariffa(@PathVariable Integer tariffa){
    	try {
			return new ResponseEntity<>(annuncioService.findByTariffa(tariffa),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    //3- FILTRA ANNUNCIO PER TIPO DI LEZIONE:
    @GetMapping("/listaAnnunciPerTipo/{tipoLezione}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> filtraAnnuncioPerTipo(@PathVariable TipoLezione tipoAnnuncio){
    	try {
			return new ResponseEntity<> (annuncioService.findByTipoLezione(tipoAnnuncio),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
//    @PutMapping("modificaAnnuncio/{idAnnuncio}/{username}")
//    //@PreAuthorize("hasRole('USER')")
//    public ResponseEntity<?> modificaAnnuncio(@RequestBody Annuncio a, @PathVariable Long idAnnuncio, @PathVariable String username){
//    	a.setId(idAnnuncio);
//    	a.setUser(userServiceImpl.findByUsername(username));
//    	try {
//			return new ResponseEntity<>(annuncioService.editAnnuncio(a), HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//    }
    
    @DeleteMapping("/eliminaAnnuncio/{idAnnuncio}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> eliminaAnnuncio (@PathVariable Long idAnnuncio){
    	try {
			return new ResponseEntity<>(annuncioService.eliminaAnnuncio(idAnnuncio), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    @PutMapping("modificaAnnuncio/{idAnnuncio}/{idUser}")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> modificaAnnuncio(@RequestBody Annuncio a, @PathVariable Long idAnnuncio,@PathVariable Long idUser){
    	try {
			return new ResponseEntity<>(annuncioService.editAnnuncio(a, idAnnuncio, idUser), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
}
