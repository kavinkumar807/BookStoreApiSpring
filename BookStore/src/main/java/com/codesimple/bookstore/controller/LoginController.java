package com.codesimple.bookstore.controller;


import com.codesimple.bookstore.common.APIResponse;
import com.codesimple.bookstore.dto.LoginRequestDTO;
import com.codesimple.bookstore.dto.SignUpRequestDTO;
import com.codesimple.bookstore.service.LoginService;
import com.codesimple.bookstore.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO){
        APIResponse apiResponse = loginService.signUp(signUpRequestDTO);

        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO){
        APIResponse apiResponse = loginService.login(loginRequestDTO);

        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

    @GetMapping("/privateApi")
    public ResponseEntity<APIResponse> privateApi(@RequestHeader(value = "authorization",defaultValue = "") String auth) throws Exception {
        APIResponse apiResponse = new APIResponse();

//        System.out.println(auth);
//        String authorization = "";
        jwtUtils.verify(auth);

        apiResponse.setData("This is private api");
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

}
