package com.github.galcyurio.freetodo.commons;

import android.util.Log;
import timber.log.Timber;

/**
 * @author galcyurio
 */
public class ReleaseTree extends Timber.Tree {

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if(priority >= Log.WARN) {
            // do crash report!
        }
    }

}
