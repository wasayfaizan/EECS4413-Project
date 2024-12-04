@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a new user
    public User register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        user.setCart(new ArrayList<>());   // Initialize empty cart
        user.setWishlist(new ArrayList<>()); // Initialize empty wishlist
        return userRepository.save(user); // Save user to the database
    }

    // Authenticate user
    public User authenticate(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username or password."));
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid username or password.");
        }
        return user; // Return user if authentication is successful
    }

    // Get user by ID
    public User getUser(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }

    // Update user profile
    public User updateUser(String id, User updatedUser) {
        User user = getUser(id); // Retrieve existing user
        user.setName(updatedUser.getName());
        user.setShippingAddress(updatedUser.getShippingAddress());
        user.setBillingAddress(updatedUser.getBillingAddress());
        user.setCreditCard(updatedUser.getCreditCard());
        return userRepository.save(user); // Save updates
    }

    // Persistent cart and wishlist management
    public List<CartItem> getCart(String userId) {
        return getUser(userId).getCart();
    }

    public List<String> getWishlist(String userId) {
        return getUser(userId).getWishlist();
    }

    public void saveCart(String userId, List<CartItem> cart) {
        User user = getUser(userId);
        user.setCart(cart);
        userRepository.save(user);
    }

    public void saveWishlist(String userId, List<String> wishlist) {
        User user = getUser(userId);
        user.setWishlist(wishlist);
        userRepository.save(user);
    }
}
