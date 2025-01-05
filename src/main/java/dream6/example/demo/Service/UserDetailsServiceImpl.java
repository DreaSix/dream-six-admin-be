package dream6.example.demo.Service;

import dream6.example.demo.Entity.UserDetailsConf;
import dream6.example.demo.Entity.Users;
import dream6.example.demo.Repository.UserDetailsConfigRepository;
import dream6.example.demo.Repository.UsersRepository;
import dream6.example.demo.dto.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsConfigRepository userDetailsConfigRepository;

    @Autowired
    private UsersRepository usersRepository;

    public UserDetailsConf createUser(Users request) {

        String userName;


        String password = generateDummyPassword();

        UserDetailsConf userDetailsConfig = new UserDetailsConf();
        userDetailsConfig.setContactNo(request.getContactNo());
        userDetailsConfig.setUsername(request.getContactNo());
        userDetailsConfig.setPassword(password);
        userDetailsConfig.setRole(request.getRole());

        userDetailsConfigRepository.save(userDetailsConfig);

        usersRepository.delete(request);

        return userDetailsConfig;
    }

    public static String generateDummyPassword() {
        String[] prefixes = new String[]{"Textbook@", "Learning@", "Notebook@", "Graduate@", "Seminar@", "MySchool@", "Future@", "Success@"};
        String var10000 = prefixes[(new Random()).nextInt(prefixes.length)];
        String password = var10000 + getRandomNumber();
        return password;
    }

    private static String getRandomNumber() {
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000);
        return String.valueOf(randomNumber);
    }

    private String generateUniqueUserName(String name, String contactNo) {
        String userName = null;
        if(name != null && !name.trim().isEmpty() && !contactNo.trim().isEmpty()) {
            if(name.length() > 4) {
                userName = name.substring(0, 4);
            } else {
                userName = name;
            }
        } else if(contactNo != null && !contactNo.trim().isEmpty()) {
            userName = userName + contactNo.substring(0, 4);
        }

        int count = 1;
        String tempUserName = userName;
        while (userDetailsConfigRepository.existsByUsername(tempUserName)) {
            tempUserName = userName + count;
            count++;
        }
        return tempUserName.toLowerCase(Locale.ENGLISH);
    }

    private static final Random random = new Random();

    public static String generateGuid() {
        return "DR" + getRandomAlphabet() + String.format("%05d", random.nextInt(100));
    }

    public static char getRandomAlphabet() {
        String alphabets = "ABCDEFGHIJKLMNPQRSTUVWXYZ"; // Excluding 'O'
        int randomIndex = random.nextInt(alphabets.length());
        return alphabets.charAt(randomIndex);
    }

    public void addUser(UserRequest request) throws Exception {

        Users existUser = usersRepository.findByContactNo(request.getContactNumber());

        if (existUser != null){
            throw new Exception("User exist with this contact number");
        }

        Users users = new Users();
        users.setName(request.getUserName());
        users.setContactNo(request.getContactNumber());
        users.setRole(request.getRole());

        usersRepository.save(users);
    }


    public List<Users> getUsers() {

        return usersRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserDetailsConf> userDetails = userDetailsConfigRepository.findByUsername(username);

        if (userDetails.isPresent()) {
            UserDetailsConf userInfo = userDetails.get();

            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("User"));
            return new User(userInfo.getUsername(), userInfo.getPassword(), authorities);

        } else {
            throw new UsernameNotFoundException("User doesn't exist");
        }

    }

    public List<UserDetailsConf> getUserDetailsConfig() {
        return  userDetailsConfigRepository.findAll();
    }
}
