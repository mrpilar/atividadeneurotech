package com.example.desafioneurotech.desafioneurotech.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.desafioneurotech.desafioneurotech.model.Usuario;
import com.example.desafioneurotech.desafioneurotech.repository.UsuarioRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;


	public Usuario findById(Long id)  {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
	}
	
}
