package shop.qtmspc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
public class ProfileLocalInit {

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        log.info("★★★★★★★★★★★★ local Profile ★★★★★★★★★★★★");
    }

}
