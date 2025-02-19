package cvoperator.cvoperator.service;

import cvoperator.cvoperator.entity.User;
import cvoperator.cvoperator.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Boolean registrtion(User user) {
        //User user = new User();
        //user.setEmail(email);
        //user.setPassword(password);
        return userRepository.save(user) != null;
    }

    public boolean login(String email, String password) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        if(userRepository.findById(id).isPresent()) {
            return false;
        }else return true;
    }
}
