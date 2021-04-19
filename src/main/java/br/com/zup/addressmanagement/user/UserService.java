package br.com.zup.addressmanagement.user;

import br.com.zup.addressmanagement.address.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void addNewAddress(Long id, Address address) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "User with id " + id + " does not exists."));

        // validação com viaapi

        user.addAddress(address);
        userRepository.save(user);
    }
}
