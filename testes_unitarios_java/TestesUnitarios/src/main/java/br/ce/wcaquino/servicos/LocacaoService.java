package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;
import static org.junit.Assert.*;

import java.util.Date;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.Test;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, Filme filme) {
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		//Salvando a locacao...	
		//TODO adicionar método para salvar

		return locacao;
	}

	@Test
	public void LocacaoServiceTest(){

		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Matheus");
		Filme filme = new Filme("The goodfelas", 2, 5.0);

		//acao do metodo a ser testado
		Locacao locacao = service.alugarFilme(usuario, filme);

		//verificacao se o resultado da acao esta de acordo com o esperado
        assertEquals(5.0, locacao.getValor(), 0.0);
		assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
	}
}