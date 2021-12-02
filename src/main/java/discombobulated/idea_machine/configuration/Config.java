package discombobulated.idea_machine.configuration;

import discombobulated.idea_machine.dtos.converter.DTOConverter;
import discombobulated.idea_machine.services.ActivityServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Autowired
    ActivityServiceImpl activityService;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Bean
    public DTOConverter dtosConverter(ModelMapper modelMapper) {
        return new DTOConverter(modelMapper, activityService);
    }

}
