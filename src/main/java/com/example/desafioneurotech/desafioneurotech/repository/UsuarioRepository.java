package com.example.desafioneurotech.desafioneurotech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.desafioneurotech.desafioneurotech.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByCpf(String cpf);
	Optional<Usuario> findByEmail (String email);

}
