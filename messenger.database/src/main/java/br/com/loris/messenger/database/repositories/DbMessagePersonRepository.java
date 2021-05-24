package br.com.loris.messenger.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.loris.messenger.database.entities.DbMessagePerson;

@Repository
public interface DbMessagePersonRepository extends JpaRepository<DbMessagePerson, Long> {
}
