/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemi1;

/**
 * The simulation parameters
 */
public interface Conf {

    int MAX_DAY = 100;
    int MAX_CONTACTS_PER_DAY = 3;
    int POPULATION = 10000;
    int INITALLY_SICK = 34;

    double SICK_PROBABILITY = 0.5;
    double DEATH_PROBILITY = 0.4;
    int MIN_SICK_DAYS = 3;
    int MAX_SICK_DAYS = 10;
}
