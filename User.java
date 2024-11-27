@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username; // Unique identifier (email)

    private String password; // Encrypted password
    private String name;     // Full name of the user
    private String shippingAddress; // Default shipping address
    private String billingAddress;  // Default billing address
    private String creditCard;      // Credit card info (encrypted for security)

    private List<CartItem> cart = new ArrayList<>();   // Persistent shopping cart
    private List<String> wishlist = new ArrayList<>(); // Wishlist with product IDs

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getCreditCard() {
        return creditCard;
    }
    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public List<CartItem> getCart() {
        return cart;
    }
    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public List<String> getWishlist() {
        return wishlist;
    }
    public void setWishlist(List<String> wishlist) {
        this.wishlist = wishlist;
    }
}
