package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import parser.factory.AllParserFactory;
import parser.factory.CsvParserFactory;
import parser.factory.JsonParserFactory;
import parser.implementation.CsvParser;
import parser.implementation.JsonParser;

@Configuration
@ComponentScan({"java", "converter", "output", "parser"})
public class SpringConfiguration {
    @Bean
    public JsonParser jsonParser(){
        return new JsonParser();
    }
    @Bean
    public JsonParserFactory jsonParserFactory() {
        return new JsonParserFactory(jsonParser());
    }
@Bean
    public CsvParser csvParser(){
        return new CsvParser();
    }
    @Bean
    public CsvParserFactory csvParserFactory() {
        return new CsvParserFactory(csvParser());
    }

    @Bean
    public AllParserFactory allParserFactory() {
        return new AllParserFactory(csvParserFactory(),jsonParserFactory());
    }


}
