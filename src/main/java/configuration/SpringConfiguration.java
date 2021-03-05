package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import parser.implementation.CsvParser;
import parser.implementation.JsonParser;

@Configuration
@ComponentScan({"java","converter","output","parser","work"})
public class SpringConfiguration {



}
