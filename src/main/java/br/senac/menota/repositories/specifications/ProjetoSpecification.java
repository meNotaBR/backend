package br.senac.menota.repositories.specifications;

import br.senac.menota.model.Projeto;
import org.springframework.data.jpa.domain.Specification;

public class ProjetoSpecification {

    public static Specification<Projeto> getUltimosProjetos() {
        return ((root, query, criteriaBuilder) -> {
            if (query == null){
                return criteriaBuilder.conjunction();
            }

            query.orderBy(criteriaBuilder.desc(root.get("dataCadastro")));
            return criteriaBuilder.conjunction();
        });
    }
}
