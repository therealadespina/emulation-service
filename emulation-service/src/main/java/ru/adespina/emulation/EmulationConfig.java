package ru.adespina.emulation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "emulation")
@ConstructorBinding
@Getter
@Setter
public class EmulationConfig {
    private String folder;
}
