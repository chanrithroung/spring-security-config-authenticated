package com.example.spring_Security.service.userServices;


import com.example.spring_Security.model.UserModel.UserModel;
import com.example.spring_Security.model.UserModel.UserPrincipal;
import com.example.spring_Security.repository.UserRepository;
import com.example.spring_Security.service.TokenService.JWTServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServices implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public MyUserDetailsServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsername(username);
//        UserModel testUser = userRepository.findById(1).orElse(null);
//        assert testUser != null;
//        String username1 = testUser.getUsername();
//        System.out.println(username1);
//        System.out.println( Objects.requireNonNull(userRepository.findById(1).orElse(null)).getUsername() );
//        UserModel testuser = userRepository.findById(1)
//                .orElseThrow( () -> new RuntimeException("Uer not found"));

//        UserModel testuser = userRepository.findById(1)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        if (user == null) {
        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }
}
