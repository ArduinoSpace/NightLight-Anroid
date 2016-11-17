package ru.annin.nightlight.presentation.ui.activity;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.view.View;

import com.pavelsikun.vintagechroma.ChromaDialog;
import com.pavelsikun.vintagechroma.IndicatorMode;
import com.pavelsikun.vintagechroma.OnColorSelectedListener;
import com.pavelsikun.vintagechroma.colormode.ColorMode;

import ru.annin.nightlight.R;
import ru.annin.nightlight.presentation.common.BaseActivity;
import ru.annin.nightlight.presentation.presenter.LedPresenter;
import ru.annin.nightlight.presentation.ui.view.LedView;
import ru.annin.nightlight.presentation.ui.viewholder.LedViewHolder;

/**
 * <p>Экран "Настройка LED".</p>
 *
 * @author P. Annin.
 */
public class LedActivity extends BaseActivity<LedPresenter> implements LedView {

    public static final String EXTRA_DEVICE = "ru.annin.nightlight.extra.device";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led);

        final View vRoot = findViewById(R.id.root);
        final LedViewHolder viewHolder = new LedViewHolder(vRoot);

        BluetoothDevice device = null;
        if (getIntent() != null && getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey(EXTRA_DEVICE)) {
                device = getIntent().getExtras().getParcelable(EXTRA_DEVICE);
            }
        }

        mPresenter = new LedPresenter(this);
        mPresenter.setViewHolder(viewHolder);
        mPresenter.setView(this);
        mPresenter.onInitialization(device);
    }

    @Override
    public void selectColor(@ColorInt int color, @Nullable OnColorSelectedListener listener) {
        new ChromaDialog.Builder()
                .initialColor(color)
                .colorMode(ColorMode.RGB)
                .indicatorMode(IndicatorMode.DECIMAL)
                .onColorSelected(listener)
                .create()
                .show(getSupportFragmentManager(), "dialog");
    }
}
