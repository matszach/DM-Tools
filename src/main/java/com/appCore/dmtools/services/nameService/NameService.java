package com.appCore.dmtools.services.nameService;


/**
 * Defines responsibilities of NameService_impl class
 */
public interface NameService {
    void generateNames(int number);
    String getResults();
    void reset();
}
