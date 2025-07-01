package br.senac.menota.services;

import br.senac.menota.dtos.UserProfileResponseDTO;
import br.senac.menota.dtos.UserProfileUpdateRequestDTO;
import br.senac.menota.exceptions.NotFoundException;
import br.senac.menota.model.BaseUser;
import br.senac.menota.model.Empresario;
import br.senac.menota.model.Investidor;
import br.senac.menota.repositories.EmpresarioRepository;
import br.senac.menota.repositories.InvestidorRepository;
import br.senac.menota.strategy.profile.CompositeProfileValidationStrategy;
import br.senac.menota.utils.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final EmpresarioRepository empresarioRepository;
    private final InvestidorRepository investidorRepository;
    private final CompositeProfileValidationStrategy profileValidationStrategy;

    public UserProfileResponseDTO getCurrentUserProfile() {
        BaseUser user = AuthenticationUtil.retriveAuthenticatedUser();
        return UserProfileResponseDTO.fromEntity(user);
    }

    @Transactional
    public UserProfileResponseDTO updateProfile(UserProfileUpdateRequestDTO request) {
        BaseUser currentUser = AuthenticationUtil.retriveAuthenticatedUser();
        
        if (currentUser instanceof Empresario empresario) {
            updateEmpresarioProfile(empresario, request);
            profileValidationStrategy.validate(empresario);
            return UserProfileResponseDTO.fromEntity(empresarioRepository.save(empresario));
        } else if (currentUser instanceof Investidor investidor) {
            updateInvestidorProfile(investidor, request);
            profileValidationStrategy.validate(investidor);
            return UserProfileResponseDTO.fromEntity(investidorRepository.save(investidor));
        }
        
        throw new NotFoundException("Tipo de usuário não suportado");
    }

    private void updateEmpresarioProfile(Empresario empresario, UserProfileUpdateRequestDTO request) {
        if (request.getNome() != null) {
            empresario.setNome(request.getNome());
        }
        if (request.getSobrenome() != null) {
            empresario.setSobrenome(request.getSobrenome());
        }
        if (request.getEmail() != null) {
            empresario.setEmail(request.getEmail());
        }
        if (request.getNumeroCelular() != null) {
            empresario.setNumeroCelular(request.getNumeroCelular());
        }
        if (request.getDataNasc() != null) {
            empresario.setDataNasc(request.getDataNasc());
        }
    }

    private void updateInvestidorProfile(Investidor investidor, UserProfileUpdateRequestDTO request) {
        if (request.getNome() != null) {
            investidor.setNome(request.getNome());
        }
        if (request.getSobrenome() != null) {
            investidor.setSobrenome(request.getSobrenome());
        }
        if (request.getEmail() != null) {
            investidor.setEmail(request.getEmail());
        }
        if (request.getNumeroCelular() != null) {
            investidor.setNumeroCelular(request.getNumeroCelular());
        }
        if (request.getDataNasc() != null) {
            investidor.setDataNasc(request.getDataNasc());
        }
    }
} 