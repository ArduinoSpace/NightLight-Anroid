package ru.annin.nightlight.presentation.common;

/**
 * <p>Интерфейс Presenter (MVP).</p>
 *
 * @author P. Annin.
 */
public interface Presenter {

    /** @see android.app.Activity#onResume() */
    void onResume();
    /** @see android.app.Activity#onPause() */
    void onPause();
    /** @see android.app.Activity#onDestroy() */
    void onDestroy();

}