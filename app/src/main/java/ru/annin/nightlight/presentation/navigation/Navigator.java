package ru.annin.nightlight.presentation.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import ru.annin.nightlight.presentation.ui.activity.ConnectActivity;

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

}
