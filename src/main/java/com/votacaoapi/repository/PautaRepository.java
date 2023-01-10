package com.votacaoapi.repository;

import com.votacaoapi.entity.Pauta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PautaRepository extends MongoRepository<Pauta, String> {

}
