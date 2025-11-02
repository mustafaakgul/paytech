package org.fintech.paytech.domain.core.logger.repository;

import org.fintech.paytech.domain.core.logger.model.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerRepository extends JpaRepository<Logger, Long> {
}
