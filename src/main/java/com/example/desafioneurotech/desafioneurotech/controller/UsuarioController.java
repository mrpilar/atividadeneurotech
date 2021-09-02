package com.example.desafioneurotech.desafioneurotech.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.desafioneurotech.desafioneurotech.model.Usuario;
import com.example.desafioneurotech.desafioneurotech.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<?> salvar(@Valid @RequestBody Usuario usuario, UriComponentsBuilder uriBuilder) {

		Optional<Usuario> optional = usuarioRepository.findByCpf(usuario.getCpf());
		Optional<Usuario> optional2 = usuarioRepository.findByEmail(usuario.getEmail());
		if (optional.isPresent() || optional2.isPresent()) {
			return ResponseEntity.badRequest().body("Usuário cadastrado!");
		}
		usuarioRepository.save(usuario);
		URI uri = uriBuilder.path("/pessoa/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(usuario);

	}

	@GetMapping("/{id}")
	public Usuario findById(@PathVariable Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não cadastrado!"));
	}

}
