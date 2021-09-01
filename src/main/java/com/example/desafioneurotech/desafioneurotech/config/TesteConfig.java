package com.example.desafioneurotech.desafioneurotech.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.desafioneurotech.desafioneurotech.model.Endereco;
import com.example.desafioneurotech.desafioneurotech.model.Usuario;
import com.example.desafioneurotech.desafioneurotech.repository.EnderecoRepository;
import com.example.desafioneurotech.desafioneurotech.repository.UsuarioRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		Date y1 = sdf1.parse("03/02/1988");


		Usuario u1 = new Usuario();
		u1.setCpf("99999");
		u1.setDataNascimento(y1);
		u1.setEmail("maria@email.com");
		u1.setNome("MARIA BROWN");
		
		usuarioRepository.save(u1);
		
		Endereco e1 = new Endereco();
		e1.setBairro("Acacias");
		e1.setCep("12345");
		e1.setCidade("Brigadeiro");
		e1.setComplemento("APT 101");
		e1.setEstado("Pernambuco");
		e1.setLogradouro("Rua da Persia");
		e1.setNumero(171);
		e1.setUsuario(u1);
		
		enderecoRepository.save(e1);
				
	}

}
