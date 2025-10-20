package com.app.repository;

import com.app.model.CotacaoEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface CotacaoRepository
        extends CrudRepository<CotacaoEntity, BigInteger> {

    @EntityGraph(attributePaths = "cliente")
    Optional<CotacaoEntity> findByIdAndClienteUsername(BigInteger cotacaoId, String username);
}