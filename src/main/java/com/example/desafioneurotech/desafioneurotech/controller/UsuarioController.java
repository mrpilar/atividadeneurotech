package com.example.desafioneurotech.desafioneurotech.controller;

import java.net.URI;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	public ResponseEntity<Usuario> salvar(@Valid @RequestBody Usuario usuario, UriComponentsBuilder uriBuilder) {
		usuarioRepository.save(usuario);

		URI uri = uriBuilder.path("/pessoa/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(usuario);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if (usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		}
		return ResponseEntity.notFound().build();
	}

}
