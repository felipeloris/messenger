package br.com.loris.messenger.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.loris.messenger.domain.MessengerLog;

@Repository
public interface MessengerLogRepository extends MongoRepository<MessengerLog, Long> {
}
