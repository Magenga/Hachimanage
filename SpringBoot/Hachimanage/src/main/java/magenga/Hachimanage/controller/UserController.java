package magenga.Hachimanage.controller;

import magenga.Hachimanage.common.service.UserQueryService;
import magenga.Hachimanage.common.dto.RequestUser;
import magenga.Hachimanage.common.exceprion.NotFoundException;
import magenga.Hachimanage.common.util.AuthenticationResponse;
import magenga.Hachimanage.common.util.JwtUtil;
import magenga.Hachimanage.systemMethod.SystemLogging;
import magenga.Hachimanage.common.service.UserCommandService;
import magenga.Hachimanage.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
    @RequestMapping("/api")
    @RestController
    public class UserController {

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
            User user = userQueryService.findById(user_id);

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
                User tempUser = new User(signUpRequestUser.getAccount(),signUpRequestUser.getPassword(), signUpRequestUser.getEmail());
                response.put("user", tempUser);
                userCommandService.save(tempUser);
                controllerSystemLogging.userSignUp(tempUser);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }else{
                return ResponseEntity.ok(response);
            }
        }


        @PostMapping("/login")//登入
        public ResponseEntity<AuthenticationResponse> login (@RequestBody RequestUser loginRequestUser) {
            System.out.println("Login request received: " + loginRequestUser.getAccount());


            boolean signInSuccess = userCommandService.signInCheck(loginRequestUser.getAccount(), loginRequestUser.getPassword());
            User loginUser = null;
            if (signInSuccess) {
                loginUser = userQueryService.findSeqByAccount(loginRequestUser.getAccount());
                String token = jwtUtil.generateToken(loginUser.getAccount());
                int userId = loginUser.getId();
                String account = loginUser.getAccount();
                controllerSystemLogging.userLogIn(loginUser);
                return ResponseEntity.ok(new AuthenticationResponse(token, userId, account));
            } else {
                controllerSystemLogging.userLogInFailed(loginUser);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthenticationResponse());
            }
        }

        @PutMapping("/user")
        public User updateUserPassword (@RequestBody RequestUser requestUser) {

            User user = userQueryService.findSeqByAccount(requestUser.getAccount());
            if(user == null){
                throw new NotFoundException("incorrect account!");
            }
            user.setPassword(requestUser.getPassword());
            userCommandService.updatePassword(user);

            return user;
        }

        @DeleteMapping("/user/{user_id}")
        public User deleteUser (@PathVariable int user_id) {

            User user = userQueryService.findById(user_id);
            if(user == null){
                throw new NotFoundException("incorrect id!");
            }
            userCommandService.delete(user);
            return user;
        }
}