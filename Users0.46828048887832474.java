
Spring Boot Application with Controller, Service, and Repository Classes:

Controller:

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	@PostMapping("/users")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	
	@PutMapping("/users/{id}")
	public User editUser(@PathVariable Long id, @RequestBody User user) {
		return userService.editUser(id, user);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
	
	@GetMapping("/users/download")
	public void downloadUserData() {
		userService.downloadUserData();
	}
	
	@GetMapping("/users/archive")
	public void archiveUser() {
		userService.archiveUser();
	}
	
	@GetMapping("/users/activate")
	public void activateUser() {
		userService.activateUser();
	}
	
	@PostMapping("/users/submit")
	public void submitUser() {
		userService.submitUser();
	}
	
	@PostMapping("/users/reset")
	public void resetUser() {
		userService.resetUser();
	}
	
}

Service:

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	public User addUser(User user) {
		if (user.getUsername() == null || user.getUsername().equals("")) {
			throw new IllegalArgumentException("Username should not be empty");
		}
		if (user.getPassword() == null || user.getPassword().equals("")) {
			throw new IllegalArgumentException("Password should not be empty");
		}
		if (user.getLevel() == null || user.getLevel().equals("")) {
			throw new IllegalArgumentException("Level should not be empty");
		}
		if (user.getParentType() == null || user.getParentType().equals("")) {
			throw new IllegalArgumentException("Parent Type should not be empty");
		}
		if (user.getParentType().equals("W") && user.getWholesaler() == null) {
			throw new IllegalArgumentException("Wholesaler should not be empty");
		}
		if (user.getParentType().equals("B") && user.getBranch() == null) {
			throw new IllegalArgumentException("Branch should not be empty");
		}
		if (user.getParentType().equals("U")) {
			if (user.getFirstName() == null || user.getFirstName().equals("")) {
				throw new IllegalArgumentException("First Name should not be empty");
			}
			if (user.getLastName() == null || user.get