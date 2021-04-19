package br.com.zup.addressmanagement.user;

import br.com.zup.addressmanagement.address.AddressRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<Void> registerNewUser(@RequestBody @Valid UserRequest userRequest) {
        Optional<User> userOptional = userRepository.findUserByEmail(userRequest.getEmail());

        if(userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        User user = userRequest.toUser();
        userRepository.save(user);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "users/{id}/addresses")
    public ResponseEntity<Void> addNewAddress(@PathVariable("id") Long id, @RequestBody @Valid AddressRequest addressRequest) {
        try {
            userService.addNewAddress(id, addressRequest.toAddress());
            URI uri = ServletUriComponentsBuilder.fromUriString("/api/users/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(uri).build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/users/{id}")
    public UserResponse findById(@PathVariable("id") Long id) {
        return userRepository.findById(id).map(UserResponse::new).orElseThrow();
    }
}
