package com.example.desafioneurotech.desafioneurotech.config.validacao;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErroDeFormulario {
	
	private String campo;
	private String erro;

	
}
