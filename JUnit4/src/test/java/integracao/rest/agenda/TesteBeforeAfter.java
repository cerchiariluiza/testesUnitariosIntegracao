package integracao.rest.agenda;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import integracao.rest.contatos.Contato;
import integracao.rest.contatos.ContatoRepository;

@RunWith(SpringRunner.class)   //testes web
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TesteBeforeAfter {

	//testes com construção de cenários
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private ContatoRepository contatoRepository;
	private Contato contato;
	private String nome = "Aluno";
	private String ddd = "11";
	private String telefone = "99999999";
	@Before
	public void start() {
		contato = new Contato(nome, ddd, telefone);
		contatoRepository.save(contato); }	 
	@Test
	public void deveMostrarTodosContatos() {
		ResponseEntity<String> resposta = testRestTemplate.exchange("/contatos/",HttpMethod.GET,null, String.class);
		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
//	@After
//	public void end() {
//		contatoRepository.deleteAll();
//	}

}