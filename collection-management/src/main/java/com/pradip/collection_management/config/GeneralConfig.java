package com.pradip.collection_management.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
//@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class GeneralConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
