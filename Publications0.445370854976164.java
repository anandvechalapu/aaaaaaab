·       Ability to access the sub screens like edit, view and download.
·       User should be able to add/edit publication.
·       User should able to view the results in Excel format.
·       Ability to filter the data using the drop down menu.

@SpringBootApplication
public class PublicationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicationsApplication.class, args);
	}

}

@RestController
public class PublicationsController {
	
	@Autowired
	private PublicationsService publicationsService;
	
	@GetMapping("/publications")
	public ResponseEntity<List<Publications>> getPublications(){
		List<Publications> publicationsList = publicationsService.getPublications();
		return ResponseEntity.ok().body(publicationsList);
	}
	
	@PostMapping("/publications")
	public ResponseEntity<Publications> addPublications(@RequestBody Publications publication){
		Publications publicationAdded = publicationsService.addPublications(publication);
		return ResponseEntity.ok().body(publicationAdded);
	}
	
	@PutMapping("/publications/{id}")
	public ResponseEntity<Publications> updatePublications(@PathVariable("id") Long id, @RequestBody Publications publication){
		Publications publicationUpdated = publicationsService.updatePublications(id, publication);
		return ResponseEntity.ok().body(publicationUpdated);
	}
	
	@DeleteMapping("/publications/{id}")
	public ResponseEntity<Void> deletePublications(@PathVariable("id") Long id){
		publicationsService.deletePublications(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/publications/{id}/download")
	public ResponseEntity<Void> downloadPublications(@PathVariable("id") Long id){
		publicationsService.downloadPublications(id);
		return ResponseEntity.ok().build();
	}
	
}

@Service
public class PublicationsService {

	@Autowired
	private PublicationsRepository publicationsRepository;
	
	public List<Publications> getPublications(){
		return publicationsRepository.findAll();
	}
	
	public Publications addPublications(Publications publication){
		return publicationsRepository.save(publication);
	}
	
	public Publications updatePublications(Long id, Publications publication) {
		Publications publicationToUpdate = publicationsRepository.findById(id).get();
		publicationToUpdate.setName(publication.getName());
		publicationToUpdate.setDays(publication.getDays());
		publicationToUpdate.setPublisher(publication.getPublisher());
		publicationToUpdate.setActive(publication.isActive());
		return publicationsRepository.save(publicationToUpdate);
	}
	
	public void deletePublications(Long id){
		publicationsRepository.deleteById(id);
	}
	
	public void downloadPublications(Long id){
		Publications publicationToDownload = publicationsRepository.findById(id).get();
		// Code to download the publication in .csv format
	}
}

@Repository
public interface PublicationsRepository extends JpaRepository<Publications, Long>{

}

@Entity
public class Publications {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;