·       Ability to access the sub screens.
·       User should able to select the wholesalers from the wholesalers list
·       User should able to input the branch name based on the pattern "/^\d+$/".
·       Number should be unique for each wholesaler.
·       User should able to select the NFRN Region from the NFRN Regions List
·       User should able to select the TV Region from the TV Regions List.
·       User should able to select No. AAT/SAT value from dropdown.
·       User should have Submit and Reset buttons on this screen.
·       User should able to download the branches as format of .csv file

Here is the Spring Boot Application with Controller, Service, and Repository Classes for the given user story:

Controller:
@RestController
public class BranchesController {
    private final BranchesService branchesService;

    @Autowired
    public BranchesController(BranchesService branchesService) {
        this.branchesService = branchesService;
    }

    @GetMapping("/branches")
    public ResponseEntity<List<Branches>> getBranches(){
        List<Branches> branches = branchesService.getBranches();
        return ResponseEntity.ok(branches);
    }

    @PostMapping("/branches")
    public ResponseEntity<Branches> createBranches(@RequestBody Branches branches){
        Branches createdBranches = branchesService.createBranches(branches);
        return ResponseEntity.ok(createdBranches);
    }

    @PutMapping("/branches/{id}")
    public ResponseEntity<Branches> updateBranches(@PathVariable Long id, @RequestBody Branches branches){
        Branches updatedBranches = branchesService.updateBranches(id, branches);
        return ResponseEntity.ok(updatedBranches);
    }

    @DeleteMapping("/branches/{id}")
    public ResponseEntity<Void> deleteBranches(@PathVariable Long id){
        branchesService.deleteBranches(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/branches/download")
    public void downloadBranchesAsCsv(HttpServletResponse response) throws IOException {
        // Set the content type and attachment header.
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"branches.csv\"");

        // Write the data to the output stream.
        branchesService.downloadBranchesAsCsv(response.getWriter());
    }
}

Service:
@Service
public class BranchesService {
    private final BranchesRepository branchesRepository;

    @Autowired
    public BranchesService(BranchesRepository branchesRepository) {
        this.branchesRepository = branchesRepository;
    }

    public List<Branches> getBranches() {
        List<Branches> branches = branchesRepository.findAll();
        return branches;
    }

    public Branches createBranches(Branches branches) {
        if (branches.getWholesaler() == null || branches.getName() == null || branches.getNumber() == null ||
                branches.getAddress() == null || branches.getNfrnRegion() == null || branches.getTvRegion() == null) {
            throw new IllegalArgumentException("Missing required fields");
        }
        if (!branchesRepository.existsByNameAndWholesaler(branches.getName(), branches.getWholesaler