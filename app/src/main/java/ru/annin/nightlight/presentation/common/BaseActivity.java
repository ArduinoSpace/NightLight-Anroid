package ru.annin.nightlight.presentation.common;

/**
 * @author П. Аннин, компания "СофтИнвент" (http://softinvent.ru).
 */

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import ru.annin.nightlight.presentation.navigation.Navigator;

/**
 * <p>Базовая Activity.</p>
 *
 * @author P. Annin.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    static { AppCompatDelegate.setCompatVectorFromResourcesEnabled(true); }

    @NonNull
    protected final Navigator mNavigator;
    @Nullable
    protected P mPresenter;

    public BaseActivity() {
        mNavigator = new Navigator();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

}
