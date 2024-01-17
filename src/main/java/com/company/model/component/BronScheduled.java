package com.company.model.component;

import com.company.repository.BronRepository;
import com.company.repository.TableRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BronScheduled {

    private final TableRepository tableRepository;
    private final BronRepository bronRepository;

    @Transactional
    @Scheduled(cron = "0 0 * * * *")
    public void bronScheduled() {



    }
}
