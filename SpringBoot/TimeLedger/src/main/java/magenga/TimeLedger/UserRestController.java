package magenga.TimeLedger;

import magenga.TimeLedger.common.UserQueryService;
import magenga.TimeLedger.common.dto.RequestUser;
import magenga.TimeLedger.common.exceprion.NotFoundException;
import magenga.TimeLedger.common.util.AuthenticationResponse;
import magenga.TimeLedger.common.util.JwtUtil;
import magenga.TimeLedger.systemMethod.SystemLogging;
import magenga.TimeLedger.common.UserCommandService;
import magenga.TimeLedger.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

    @CrossOrigin(origins = "*") // 允許所有跨域請求
    @RequestMapping("/api") //url前要加/api
    @RestController
    public class UserRestController {

        @Autowired
        private SystemLogging controllerSystemLogging;
        @Autowired
        private UserCommandService userCommandService;
        @Autowired
        private UserQueryService userQueryService;
        @Autowired
        private JwtUtil jwtUtil;


        @GetMapping("/")
        public String hellWorld () {
            return "hello sb";
        }


        //users api//

        //查詢全部用戶
        @GetMapping("/user")
        public List<User> getAllUser () {
            return userQueryService.findAll();
        }

        //查詢單一用戶
        @GetMapping("/user/{user_id}")
        public User getOneUser (@PathVariable int user_id) {
            User user = userQueryService.findOne(user_id);

            if (user == null) {
                throw new NotFoundException("user id not found " + user_id);
            }

            return user;
        }

        //新增用戶
        @PostMapping("/user")//註冊
        public ResponseEntity<Map<String, Object>> savingUserData (@RequestBody RequestUser signUpRequestUser) {

            boolean creatable = userCommandService.signUpCheck(signUpRequestUser.getAccount());
            Map<String, Object> response = new HashMap<>();
            response.put("userCreated", creatable);

            if (creatable){
                User tempUser = new User(signUpRequestUser.getAccount(),signUpRequestUser.getPassword());
                userCommandService.save(tempUser);
                controllerSystemLogging.userSignUp(tempUser);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }else{
                return ResponseEntity.ok(response);
            }
        }


        @PostMapping("/login")//登入
        public ResponseEntity<AuthenticationResponse> login (@RequestBody RequestUser loginRequestUser) {

            boolean signInSuccess = userCommandService.signInCheck(loginRequestUser.getAccount(), loginRequestUser.getPassword());
            User loginUser = null;
            if (signInSuccess) {
                loginUser = userQueryService.findSeqByAccount(loginRequestUser.getAccount());
                String token = jwtUtil.generateToken(loginUser.getAccount());
                controllerSystemLogging.userLogIn(loginUser);
                return ResponseEntity.ok(new AuthenticationResponse(token));
            } else {
                controllerSystemLogging.userLogInFailed(loginUser);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthenticationResponse(null));
            }
        }

        @PutMapping("/user")
        public User updateUserPassword (@RequestBody RequestUser requestUser) {

            User user = null;
            return user;
        }

}