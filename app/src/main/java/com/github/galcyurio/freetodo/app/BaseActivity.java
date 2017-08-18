package com.github.galcyurio.freetodo.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * @author galcyurio
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.v("%s created", getClass().getSimpleName());
    }
}
