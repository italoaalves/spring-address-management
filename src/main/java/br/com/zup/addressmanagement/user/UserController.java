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
@RequestMapping(path = "/api/v1")
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

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "/{id}/address")
    public ResponseEntity<Void> addNewAddress(@PathVariable("id") Long id, @RequestBody AddressRequest addressResquest) {
        try {
            userService.addAddress(id, addressResquest.toAddress());

            URI uri = ServletUriComponentsBuilder.fromHttpUrl("/users/{id}").buildAndExpand(id).toUri();

            return ResponseEntity.created(uri).build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/{id}")
    public User findById(@PathVariable("id") Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseGet(User::new);
    }
}
