package configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"parser","converter","output"})
public class SpringConfiguration {

//    public ParserFile parserFile(){
//        return new ParserFile();
//    }

}
