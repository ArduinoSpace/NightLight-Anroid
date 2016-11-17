package ru.annin.nightlight.presentation.ui.view;

import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;

import com.pavelsikun.vintagechroma.OnColorSelectedListener;

/**
 * <p>Представление экрана "Настройка LED".</p>
 *
 * @author P. Annin.
 */
public interface LedView {
    void selectColor(@ColorInt int color, @Nullable OnColorSelectedListener listener);
}
