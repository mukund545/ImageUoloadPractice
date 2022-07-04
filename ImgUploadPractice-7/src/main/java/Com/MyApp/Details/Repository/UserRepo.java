package Com.MyApp.Details.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Com.MyApp.Details.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	User findUserByFileName(String fileName);

}
