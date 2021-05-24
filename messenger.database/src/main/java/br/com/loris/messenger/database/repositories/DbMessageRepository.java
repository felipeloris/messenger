package br.com.loris.messenger.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.loris.messenger.database.entities.DbMessage;

@Repository
public interface DbMessageRepository extends JpaRepository<DbMessage, Long> {
	@Query(value = "SELECT * FROM message WHERE loaded=0 ORDER BY inclusion ASC LIMIT 1", nativeQuery = true)
	DbMessage findFirstUnload();
}
