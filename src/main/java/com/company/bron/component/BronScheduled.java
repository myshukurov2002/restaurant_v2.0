package com.company.bron.component;

import com.company.bron.BronRepository;
import com.company.table.TableRepository;
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
