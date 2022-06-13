package jxx.odin.config;


import jxx.odin.domain.character.CharacterManager;
import jxx.odin.domain.character.CharacterManagerV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CharacterManager characterManager() {
        return new CharacterManagerV2();
    }
}
