package br.senac.menota.services;

import br.senac.menota.model.Entregavel;
import br.senac.menota.repositories.EntregavelRepository;
import br.senac.menota.strategy.NewEntregavelValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntregavelService {

    private final EntregavelRepository entregavelRepository;
    private final List<NewEntregavelValidationStrategy> newEntregavelValidationStrategies;

    public Entregavel create(Entregavel entregavel){

        newEntregavelValidationStrategies.forEach(validation -> validation.validate(entregavel));

        return entregavelRepository.save(entregavel);
    }

    public List<Entregavel> getByProjetctId(Long id){
        try {
            List<Entregavel> entregaveis = entregavelRepository.findAllByProjetoIdOrderByDataCriacaoAsc(id);
            
            if (entregaveis == null || entregaveis.isEmpty()) {
                throw new RuntimeException("Entregável não encontrado");
            }

            return entregaveis;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar entregáveis: " + e.getMessage());
        }
    }

    public Entregavel update(Entregavel entregavel) {
        try {
            if (entregavel.getId() == null) {
                throw new RuntimeException("ID do entregável não pode ser nulo");
            }
            return entregavelRepository.save(entregavel);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar entregável: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            entregavelRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar entregável: " + e.getMessage());
        }
    }
}
