package com.tiagoperroni.produtoapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tiagoperroni.produtoapi.model.Categoria;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdutoApiApplicationTests {


	@Test
	void contextLoads() {
		var categoria = new Categoria();
		categoria.setNome("Vários");

		assertEquals("Vários", categoria.getNome());
	}

}
