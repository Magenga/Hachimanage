package magenga.TimeLedger;

import magenga.TimeLedger.common.util.AuthenticationResponse;
import magenga.TimeLedger.common.util.JwtUtil;
import magenga.TimeLedger.systemMethod.SystemLogging;
import magenga.TimeLedger.common.dao.UserDao;
import magenga.TimeLedger.common.UserService;
import magenga.TimeLedger.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

    @CrossOrigin(origins = "*") // 允許所有跨域請求
    @RequestMapping("/database")
    @org.springframework.web.bind.annotation.RestController
    public class RestController {

        @Autowired
        private SystemLogging controllerSystemLogging;
        @Autowired
        private final UserService userService;
        @Autowired
        private JwtUtil jwtUtil;
        @Autowired
        public RestController(UserService userService) {
            this.userService = userService;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();



        @GetMapping("/")
        public String hellWorld () {
            String rawPassword = "myPassword";
            String hashedPassword = encoder.encode(rawPassword);
            System.out.println("Hashed password: " + hashedPassword);

            return "hello sb";
        }

        @PostMapping("/userSignUp")
        public ResponseEntity<Map<String, Object>> savingUserData (@RequestBody User tempUser) {
            boolean creatable = userService.signUpCheck(tempUser.getAccount());
            Map<String, Object> response = new HashMap<>();
            tempUser.setPassword(encoder.encode(tempUser.getPassword()));
            if (creatable){
                response.put("userCreated", true);
                userService.save(tempUser);
                controllerSystemLogging.userSignUp(tempUser);
                return ResponseEntity.ok(response);
            }else{
                response.put("userCreated", false);

                return ResponseEntity.ok(response);
            }
        }

        @GetMapping("/allUserInfo")
        public List<User> showAll () {
            return UserDao.findAll();
        }

        @PostMapping("/login")
        @ResponseBody
        public ResponseEntity<AuthenticationResponse> login (@RequestBody User loginUser) {

            boolean signInSuccess = userService.signInCheck(loginUser.getAccount(), loginUser.getPassword());
            if(signInSuccess) {
                loginUser = UserDao.findSeqByAccount(loginUser.getAccount());
                String token = jwtUtil.generateToken(loginUser.getAccount());
                controllerSystemLogging.userLogIn(loginUser);
                return ResponseEntity.ok(new AuthenticationResponse(token));
            }else{
                controllerSystemLogging.userLogInFailed(loginUser);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthenticationResponse(null));
            }
        }

}