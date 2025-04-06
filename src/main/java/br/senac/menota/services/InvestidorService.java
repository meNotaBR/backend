package br.senac.menota.services;

import br.senac.menota.dtos.InvestidorCreateResponseDTO;
import br.senac.menota.model.Investidor;
import br.senac.menota.repositories.InvestidorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InvestidorService {

    private final InvestidorRepository investidorRepository;

    public InvestidorCreateResponseDTO create(Investidor request){

        request.setSenha(new BCryptPasswordEncoder().encode(request.getSenha()));

        return InvestidorCreateResponseDTO.fromEntity(investidorRepository.save(request));
    }

    public Investidor byId(Long id){
        return investidorRepository.findById(id).orElseThrow(()-> new RuntimeException("Investidor não encontrado"));
    }

    public List<Investidor> getAllInvestors(){
        return investidorRepository.findAll();
    }
}
