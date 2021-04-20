package br.com.zup.addressmanagement.user;

import br.com.zup.addressmanagement.address.AddressRequest;

import br.com.zup.addressmanagement.viacep.ViaCepAddress;
import br.com.zup.addressmanagement.viacep.ViaCepClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/")
public class UserController {
    private final UserRepository userRepository;
    private final ViaCepClient viaCepClient;

    public UserController(UserRepository userRepository, ViaCepClient viaCepClient) {
        this.userRepository = userRepository;
        this.viaCepClient = viaCepClient;
    }

    @PostMapping("/users")
    public ResponseEntity<?> registerNewUser(@RequestBody @Valid UserRequest userRequest) {
        Optional<User> userOptional = userRepository.findByEmail(userRequest.getEmail());

        if(userOptional.isPresent()) {
            return new ResponseEntity<>("Email already in use.", HttpStatus.CONFLICT);
        }

        User user = userRequest.toUser();
        userRepository.save(user);

        return new ResponseEntity<>(new UserResponse(user), HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping(value = "users/{id}/addresses")
    public ResponseEntity<?> addNewAddress(@PathVariable("id") Long id, @RequestBody @Valid AddressRequest addressRequest) {
        Optional<User> userOptional = userRepository.findById(id);

        int cep = Integer.parseInt(addressRequest.getCep().replace("-", ""));
        ViaCepAddress viaCepAddress = viaCepClient.getViaCepAddress(cep);

        if(userOptional.isEmpty()){
            return new ResponseEntity<>("User with id:" + id + " not found.", HttpStatus.BAD_REQUEST);
        }

        if(viaCepAddress.getCep() == null) {
            return new ResponseEntity<>("CEP:" + cep + " does not exist.", HttpStatus.BAD_REQUEST);
        }

        addressRequest.autoComplete(viaCepAddress);
        User user = userOptional.get();

        user.addAddress(addressRequest.toAddress());
        userRepository.save(user);

        return new ResponseEntity<>(new UserResponse(user), HttpStatus.CREATED);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            return new ResponseEntity<>("User with id:" + id + " not found.", HttpStatus.BAD_REQUEST);
        }
        User user = userOptional.get();
        return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
    }
}
