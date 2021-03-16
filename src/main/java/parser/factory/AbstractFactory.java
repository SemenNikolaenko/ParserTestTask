package parser.factory;

import parser.CustomParser;

/**
 * фабрика которая возвращает определенную реализацию вашего парсера
 */
public abstract class AbstractFactory {
    public abstract CustomParser createParser();
}
