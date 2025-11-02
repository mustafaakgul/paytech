package org.fintech.paytech.domain.core.logger.service.impl;

import org.fintech.paytech.domain.core.logger.model.Logger;
import org.fintech.paytech.domain.core.logger.repository.LoggerRepository;
import org.fintech.paytech.domain.core.logger.service.LoggerService;
import org.springframework.stereotype.Service;

@Service
public class LoggerServiceImpl implements LoggerService {

    private final LoggerRepository loggerRepository;

    public LoggerServiceImpl(LoggerRepository loggerRepository) {
        this.loggerRepository = loggerRepository;
    }

    public Logger createLogger(Logger logger) {
        return loggerRepository.save(logger);
    }
}
