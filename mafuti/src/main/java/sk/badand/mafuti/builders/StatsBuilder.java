
/*
 * Copyright 2014 Andrej Badinka
 */

package sk.badand.mafuti.builders;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abadinka
 */
public class StatsBuilder implements Observer{
    private static final Logger LOG = Logger.getLogger(StatsBuilder.class.getName());
    
    //TODO recode *builders not to be builders

    @Override
    public void update(Observable o, Object o1) {
        LOG.log(Level.FINE, "update: {0} {1}", new Object[]{o, o1});
    }
    
}
