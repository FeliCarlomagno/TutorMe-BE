package com.auth.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.auth.entity.User;
import com.auth.payload.JWTAuthResponse;
import com.auth.payload.LoginDto;
import com.auth.payload.RegisterDto;
import com.auth.service.AuthService;
import com.auth.service.AuthServiceImpl;
import com.tutorMe.model.Annuncio;
import com.tutorMe.repository.AnnuncioRepository;
import com.tutorMe.service.AnnuncioService;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired AuthServiceImpl userServiceImpl;
	@Autowired AnnuncioService annuncioService;
	@Autowired AnnuncioRepository annuncioRepository;
	
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Build Login REST API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
           	
    	String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setUsername(loginDto.getUsername());
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Build Register REST API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    //-----------------------------------------------------METODI ----------------------------------------------------------------------------------------//  
    @GetMapping("/listaUtenti")
//    @PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
    public ResponseEntity<?> recuperaUtenti(){
    	return new ResponseEntity<>(userServiceImpl.findtAllUser(),HttpStatus.OK);
    }
    
    @GetMapping("/listaUtenti/paginati")
//    @PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
    public ResponseEntity<?> recuperaUtentiPageable(Pageable pageable){
    	try {
			return new ResponseEntity<>(userServiceImpl.getAllUserPageable(pageable),HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    @GetMapping("/listaUtenti/paginatiPerNome")
//    @PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
    public ResponseEntity<?> recuperaUtentiPageableByNome(Pageable pageable){
    	try {
    		return new ResponseEntity<>(userServiceImpl.getAllUserPageableByName(),HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    @GetMapping("/trovaUtente/{id}")
//    @PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
    public ResponseEntity<?> trovaUtente (@PathVariable Long id){
    	try {
			return new ResponseEntity<>(userServiceImpl.findById(id),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    @GetMapping("/trovaUtenteByUsername/{username}")
//  @PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
    public ResponseEntity<?> trovaUtenteByUsername (@PathVariable String username){
    	try {
			return new ResponseEntity<>(userServiceImpl.findByUsername(username),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    @PutMapping("/modificaUtente/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> modificaUtente(@RequestBody User u, @PathVariable Long id){
    	u.setId(id);
    	try {
			return new ResponseEntity<User>(userServiceImpl.editUser(u),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    @DeleteMapping("/eliminaUtente/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> eliminaUtente(@PathVariable Long id){
    	try {
			return new ResponseEntity<>(userServiceImpl.deleteUserById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
//    //METODO PER INVIARE UN IMMAGINE AL SERVER
//    @PutMapping("/inviaImmagine/")
//    public ResponseEntity<?> inviaImmagine (@RequestParam ("image") MultipartFile image){
//    	try {
//			return new ResponseEntity<>(userServiceImpl.)
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//    }
//    
    
}
