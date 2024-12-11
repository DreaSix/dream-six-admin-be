package dream6.example.demo.Service;

import dream6.example.demo.Entity.UserDetailsConfig;
import dream6.example.demo.Entity.Users;
import dream6.example.demo.Repository.UserDetailsConfigRepository;
import dream6.example.demo.Repository.UsersRepository;
import dream6.example.demo.dto.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

public class UserDetailsServiceImpl {

    @Autowired
    private UserDetailsConfigRepository userDetailsConfigRepository;

    @Autowired
    private UsersRepository usersRepository;

    public UserDetailsConfig createUser(Users request) {

        String generatedGuid = getAvailableGUID();
        String userName;

        userName = generateUniqueUserName(request.getName(), request.getContactNo());

        String password = generateDummyPassword();

        UserDetailsConfig userDetailsConfig = new UserDetailsConfig();
        userDetailsConfig.setUserName(userName);
        userDetailsConfig.setPassword(password);
        userDetailsConfig.setGuid(generatedGuid);
        userDetailsConfig.setEntityId(request.getUserId());
        userDetailsConfig.setRole(request.getRole());

        userDetailsConfigRepository.save(userDetailsConfig);

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
        while (userDetailsConfigRepository.existsByUserName(tempUserName)) {
            tempUserName = userName + count;
            count++;
        }
        return tempUserName.toLowerCase(Locale.ENGLISH);
    }

    private static final Random random = new Random();

    public String getAvailableGUID() {
        String guid;
        do {
            guid = generateGuid();
        } while (userDetailsConfigRepository.existsByGuid(guid));

        return guid;
    }

    public static String generateGuid() {
        return "EP" + getRandomAlphabet() + String.format("%05d", random.nextInt(100000));
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

    public UserDetailsConfig findUserByUserName(String userNameEntityIdStr) {
        String userNameBranchTokens[] = userNameBranchCodeStr.split("~");
        Optional<UserDetailsConfig> userData = userDetailsConfigRepository.findByUserNameAndEntityId(userNameBranchTokens[0], userNameBranchTokens[1]);
        if (userData.isPresent()) {
            return userData.get();
        } else {
            throw new UsernameNotFoundException("User doesn't exist");
        }
    }

    public List<Users> getUsers() {

        return usersRepository.findAll();
    }
}
