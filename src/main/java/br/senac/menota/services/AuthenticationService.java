package br.senac.menota.services;

import br.senac.menota.exceptions.NotFoundException;
import br.senac.menota.model.Empresario;
import br.senac.menota.model.Investidor;
import br.senac.menota.repositories.EmpresarioRepository;
import br.senac.menota.repositories.InvestidorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final InvestidorRepository investidorRepository;
    private final EmpresarioRepository empresarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Empresario empresario = empresarioRepository.findByEmail(username);

        if (empresario != null){
            return empresario;
        }

        Investidor investidor = investidorRepository.findByEmail(username);

        if (investidor != null){
            return investidor;
        }

        throw new NotFoundException("Usuário não encontrado");
    }

}
