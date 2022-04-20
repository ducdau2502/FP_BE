package gethigh.fp_be.controller.auth;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;

import gethigh.fp_be.dto.request.LoginRequest;
import gethigh.fp_be.dto.request.SignupRequest;
import gethigh.fp_be.dto.response.JwtResponse;
import gethigh.fp_be.dto.response.MessageResponse;
import gethigh.fp_be.model.Account;
import gethigh.fp_be.model.AccountDetail;
import gethigh.fp_be.model.AccountRole;
import gethigh.fp_be.model.num.EAccountRole;
import gethigh.fp_be.repository.AccountRepo;
import gethigh.fp_be.repository.AccountRoleRepo;
import gethigh.fp_be.security.JwtUtils;
import gethigh.fp_be.service.IAccountDetailService;
import gethigh.fp_be.service.impl.AccountDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/auth/account")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    AccountRoleRepo accountRoleRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    IAccountDetailService accountDetailService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AccountDetailImpl accountDetails = (AccountDetailImpl) authentication.getPrincipal();
        List<String> roles = accountDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                accountDetails.getId(),
                accountDetails.getUsername(),
                accountDetails.getEmail(),
                roles));
    }
    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (accountRepo.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (accountRepo.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        Account account = new Account(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail()

        );
        Set<String> strRoles = signUpRequest.getRole();
        Set<AccountRole> roles = new HashSet<>();
        if (strRoles == null) {
            AccountRole accountRole = accountRoleRepo.findByName(EAccountRole.ROLE_CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(accountRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        AccountRole adminRole = accountRoleRepo.findByName(EAccountRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "seller":
                        AccountRole sellerRole = accountRoleRepo.findByName(EAccountRole.ROLE_SELLER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(sellerRole);
                        break;
                    case "user":
                        AccountRole customerRole = accountRoleRepo.findByName(EAccountRole.ROLE_CUSTOMER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(customerRole);
                }
            });
        }
        account.setRoles(roles);
        Account acc = accountRepo.save(account);
        AccountDetail accountDetail = new AccountDetail(
            signUpRequest.getFullName(),
                signUpRequest.getAge(),
                signUpRequest.getGender(),
                signUpRequest.getAddress(),
                signUpRequest.getIdentityCard(),
                signUpRequest.getAvatar(),
                LocalDate.now(),
                signUpRequest.getBankAccount(),
                signUpRequest.getStatusName(),
                acc
        );
        accountDetailService.save(accountDetail);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
