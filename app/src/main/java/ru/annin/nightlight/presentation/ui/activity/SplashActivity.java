package ru.annin.nightlight.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.annin.nightlight.presentation.common.BaseActivity;

/**
 * <p>Экрана Splash, отображается в момент загрузки приложения.</p>
 *
 * @author P. Annin.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNavigator.navigate2Connect(this);
    }

}
