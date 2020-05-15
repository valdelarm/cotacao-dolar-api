package br.com.martins.valdelar.repository;

import br.com.martins.valdelar.model.Cotacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CotacaoRepository extends MongoRepository<Cotacao, String> {

}
