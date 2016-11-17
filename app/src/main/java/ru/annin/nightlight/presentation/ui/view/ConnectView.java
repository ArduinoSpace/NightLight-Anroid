package ru.annin.nightlight.presentation.ui.view;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;

/**
 * <p>Представление экрана "Подключение к устройству".</p>
 *
 * @author P. Annin.
 */
public interface ConnectView {
    void navigateLed(@NonNull BluetoothDevice device);
}
