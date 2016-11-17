package ru.annin.nightlight.presentation.navigation;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import ru.annin.nightlight.presentation.ui.activity.ConnectActivity;
import ru.annin.nightlight.presentation.ui.activity.LedActivity;

/**
 * <p>Класс, обеспечивающий навигацию по приложению.</p>
 *
 * @author P. Annin.
 */
public class Navigator {

    public void navigate2Connect(@NonNull final Context ctx) {
        final Intent intent = new Intent(ctx, ConnectActivity.class);
        ctx.startActivity(intent);
    }

    public void navigate2Led(@NonNull final Context ctx, @NonNull BluetoothDevice device) {
        final Intent intent = new Intent(ctx, LedActivity.class);
        intent.putExtra(LedActivity.EXTRA_DEVICE, device);
        ctx.startActivity(intent);
    }

}
