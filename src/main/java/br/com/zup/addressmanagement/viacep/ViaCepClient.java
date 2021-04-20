package br.com.zup.addressmanagement.viacep;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@FeignClient(value = "viacep", url = "https://viacep.com.br/ws/")
public interface ViaCepClient {
    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json", produces = "application/json")
    ViaCepAddress getViaCepAddress(@PathVariable("cep") int cep);
}
