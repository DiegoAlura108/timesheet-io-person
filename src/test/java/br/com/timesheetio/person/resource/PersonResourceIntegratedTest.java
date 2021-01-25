package br.com.timesheetio.person.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.timesheetio.person.domain.PersonEntity;
import br.com.timesheetio.person.dto.AddressDTO;
import br.com.timesheetio.person.dto.PersonDTO;
import br.com.timesheetio.person.dto.ResponseDTO;
import br.com.timesheetio.person.enums.PersonType;
import br.com.timesheetio.person.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(JUnitPlatform.class)
public class PersonResourceIntegratedTest {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	private int port;
	
	@BeforeEach
	public void beforeEach() {
		
		List<PersonEntity> persons = new ArrayList<>();
		
		PersonEntity personEntity = new PersonEntity();
		personEntity.setId(null);
		personEntity.setSocialRason("");
		personEntity.setFirstName("Diego");
		personEntity.setLastName("Cordeiro");
		personEntity.setNickName("Feijao Riollando e.E");
		personEntity.setAge(25);
		personEntity.setDocument("39.726.836-1");
		personEntity.setPersonType(PersonType.FISIC);
		personEntity.setPersonUserKey(new BCryptPasswordEncoder().encode("123456789"));
		
		persons.add(personEntity);
		
		personRepository.saveAll(persons);
	}
	
	@AfterEach
	public void afterEach() {
		
		personRepository.deleteAll();
	}
	
	@Test
	public void testSave() {
		
		PersonDTO personDTO = PersonDTO.builder()
		 		   .firstName("Diego")
		 		   .lastName("Cordeiro")
		 		   .nickName("Cabeca de Feijao")
		 		   .document("39.726.836-1")
		 		   .personType(PersonType.FISIC)
		 		   .socialRason("")
		 		   .age(25)
		 		   .createdDate(LocalDate.now())
		 		   .address(AddressDTO.builder().neighborhood("Serraria")
		 				   						.street("Rua Milton Patricio de Oliveira")
		 				   						.cep("09980490")
		 				   						.city("Diadema")
		 				   						.reference("State School Santa Maria")
		 				   						.complement("")
		 				   						.build())
		 		   .build();
		
		HttpEntity<PersonDTO> request = new HttpEntity<PersonDTO>(personDTO);
		
		ResponseEntity<ResponseDTO<PersonDTO>> response = testRestTemplate.exchange("http://localhost:" + port + "/person", HttpMethod.POST, request, new ParameterizedTypeReference<ResponseDTO<PersonDTO>>() {});
	
		assertNotNull(response.getBody().getData());
		
	    assertEquals(201, response.getBody().getStatus());
	}
	
	@Test
	public void testUpdate() {
		
		PersonEntity personEntity = personRepository.findAll().get(0);
		
		PersonDTO personDTO = PersonDTO.builder()
				   .id(personEntity.getId())
		 		   .firstName("Diego")
		 		   .lastName("Cordeiro")
		 		   .nickName("Cabeca de Feijao")
		 		   .document("39.726.836-1")
		 		   .personType(PersonType.FISIC)
		 		   .socialRason("")
		 		   .age(25)
		 		   .createdDate(LocalDate.now())
		 		   .address(AddressDTO.builder().id(1l)
		 				   						.neighborhood("Serraria")
		 				   						.street("Rua Milton Patricio de Oliveira")
		 				   						.cep("09980490")
		 				   						.city("Diadema")
		 				   						.reference("State School Santa Maria")
		 				   						.complement("")
		 				   						.build())
		 		   .build();
		
		HttpEntity<PersonDTO> request = new HttpEntity<PersonDTO>(personDTO);
		
		ResponseEntity<ResponseDTO<PersonDTO>> response = testRestTemplate.exchange("http://localhost:" + port + "/person/", HttpMethod.PUT, request, new ParameterizedTypeReference<ResponseDTO<PersonDTO>>() {});
	
		assertNotNull(response.getBody().getData());
		
	    assertEquals(200, response.getBody().getStatus());
	}
	
	@Test
	public void testDeleteById() {
		
		PersonEntity personEntity = personRepository.findAll().get(0);
		
		ResponseEntity<Void> response = testRestTemplate.exchange("http://localhost:" + port + "/person/" + personEntity.getId(), HttpMethod.DELETE, null, new ParameterizedTypeReference<Void>() {});
	
		assertNull("Deletado com sucesso.", response.getBody());
	}
	
	@Test
	public void testFindById() {
		
		PersonEntity personEntity = personRepository.findAll().get(0);
		
		ResponseEntity<ResponseDTO<PersonDTO>> response = testRestTemplate.exchange("http://localhost:" + port + "/person/" + personEntity.getId(), HttpMethod.GET, null, new ParameterizedTypeReference<ResponseDTO<PersonDTO>>() {});
	
		assertNotNull(response.getBody().getData());
		
	    assertEquals(200, response.getBody().getStatus());
	}
	
	@Test
	public void testFindAll() {
		
		ResponseEntity<?> response = testRestTemplate.getForEntity("http://localhost:" + port + "/person/", ResponseDTO.class);
		
		ResponseDTO<?> responseOk = (ResponseDTO<?>) response.getBody();
		
		assertNotNull(responseOk.getData());
		
		assertEquals(200, responseOk.getStatus());
	}
}