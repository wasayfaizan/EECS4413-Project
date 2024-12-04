@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username); // Find user by username (email)
}
