package com.example.desafioneurotech.desafioneurotech.controller;

import java.net.URI;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.desafioneurotech.desafioneurotech.model.Endereco;
import com.example.desafioneurotech.desafioneurotech.model.Usuario;
import com.example.desafioneurotech.desafioneurotech.repository.EnderecoRepository;
import com.example.desafioneurotech.desafioneurotech.service.UsuarioService;

import javassist.tools.web.BadHttpRequest;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	UsuarioService usuarioService;

	@PostMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Endereco> salvar(@PathVariable Long id, @Valid @RequestBody Endereco obj,
			UriComponentsBuilder uriBuilder) throws BadHttpRequest {

		Usuario usuario = usuarioService.findById(id);
		obj.setUsuario(usuario);
		enderecoRepository.save(obj);

		URI uri = uriBuilder.path("/endereco/{id}").buildAndExpand(obj.getIdEndereco()).toUri();
		return ResponseEntity.created(uri).body(obj);

	}

}
