package br.com.timesheetio.person.resource;

//@ExtendWith(MockitoExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles({ "test" })
//@RunWith(JUnitPlatform.class)
public class PersonResourceIntegratedTest {

//	@Autowired
//	private PersonRepository personRepository;
//	
//	@Autowired
//	private TestRestTemplate testRestTemplate;
//
//	@LocalServerPort
//	private int port;
//	
//	@Mock
//	private static ResponseDTO<PersonAuthDTO> personAuthResponseDTO;
//
//	@Mock
//	private static PersonAuthDTO personAuthDTO;
//
//	@BeforeEach
//	public void beforeEach() {
//		
//		personAuthResponseDTO = new ResponseDTO<PersonAuthDTO>();
//
//		personAuthDTO = PersonAuthDTO.builder()
//									 .email("diego.test@gmail.com")
//									 .password("123456789")
//									 .personAuthUserKey("db6634ca8589495fb3e15043e298bc91")
//									 .build();
//
//		personAuthResponseDTO.setStatus(201);
//		personAuthResponseDTO.setData(personAuthDTO);
//
//		List<PersonEntity> persons = new ArrayList<>();
//
//		PersonEntity personEntity = new PersonEntity();
//		personEntity.setId(null);
//		personEntity.setSocialRason("");
//		personEntity.setFirstName("Diego");
//		personEntity.setLastName("Cordeiro");
//		personEntity.setNickName("Feijao Riollando e.E");
//		personEntity.setAge(25);
//		personEntity.setDocument("39.726.836-1");
//		personEntity.setPersonType(PersonType.FISIC);
//		personEntity.setPersonAuthUserKey(new BCryptPasswordEncoder().encode("123456789"));
//
//		persons.add(personEntity);
//
//		personRepository.saveAll(persons);
//	}
//
//	@AfterEach
//	public void afterEach() {
//
//		personRepository.deleteAll();
//	}
//
//	@Test
//	public void testDeleteById() {
//
//		PersonEntity personEntity = personRepository.findAll().get(0);
//
//		ResponseEntity<Void> response = testRestTemplate.exchange(
//				"http://localhost:" + port + "/person/" + personEntity.getId(), HttpMethod.DELETE, null,
//				new ParameterizedTypeReference<Void>() {
//				});
//
//		assertNull("Deletado com sucesso.", response.getBody());
//	}
//
//	@Test
//	public void testFindById() {
//
//		PersonEntity personEntity = personRepository.findAll().get(0);
//
//		ResponseEntity<ResponseDTO<PersonDTO>> response = testRestTemplate.exchange(
//				"http://localhost:" + port + "/person/" + personEntity.getId(), HttpMethod.GET, null,
//				new ParameterizedTypeReference<ResponseDTO<PersonDTO>>() {
//				});
//
//		assertNotNull(response.getBody().getData());
//
//		assertEquals(200, response.getBody().getStatus());
//	}
//
//	@Test
//	public void testFindAll() {
//
//		ResponseEntity<?> response = testRestTemplate.exchange("http://localhost:" + port + "/person/", HttpMethod.GET,
//				null, new ParameterizedTypeReference<ResponseDTO<?>>() {
//				});
//
//		ResponseDTO<?> responseOk = (ResponseDTO<?>) response.getBody();
//
//		assertNotNull(responseOk.getData());
//
//		assertEquals(200, responseOk.getStatus());
//	}
}
