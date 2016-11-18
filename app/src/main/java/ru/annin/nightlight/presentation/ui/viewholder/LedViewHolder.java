package ru.annin.nightlight.presentation.ui.viewholder;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import ru.annin.nightlight.R;
import ru.annin.nightlight.presentation.common.BaseViewHolder;

/**
 * <p>ViewHolder экрана "Настройка LED".</p>
 *
 * @author P. Annin.
 */
public class LedViewHolder extends BaseViewHolder {

    public interface OnInteractionListener {
        void onChangeEffectRainbow(boolean flag);
        void onChangeColor();
    }

    // View's
    private final Toolbar vToolbar;
    private final AppCompatCheckBox cbEffectRainbow;
    private final Button btnChangeColor;
    private final ProgressBar pbLoading;

    // Listener's
    private OnInteractionListener listener;

    public LedViewHolder(@NonNull View view) {
        super(view);
        vToolbar = (Toolbar) vRoot.findViewById(R.id.toolbar);
        cbEffectRainbow = (AppCompatCheckBox) vRoot.findViewById(R.id.cb_effect_rainbow);
        btnChangeColor = (Button) vRoot.findViewById(R.id.btn_change_color);
        pbLoading = (ProgressBar) vRoot.findViewById(R.id.pb_loading);

        // Setup View's
        cbEffectRainbow.setOnCheckedChangeListener((compoundButton, b) -> {
            if (listener != null) {
                listener.onChangeEffectRainbow(b);
            }
        });
        btnChangeColor.setOnClickListener(view1 -> {
            if (listener != null) {
                listener.onChangeColor();
            }
        });
    }

    public LedViewHolder toggleLoading(boolean flag) {
        pbLoading.setVisibility(flag ? View.VISIBLE : View.GONE);
        cbEffectRainbow.setEnabled(!flag);
        btnChangeColor.setEnabled(!flag);
        return this;
    }

    public LedViewHolder setEffectRainbow(boolean effectRainbow) {
        cbEffectRainbow.setChecked(effectRainbow);
        return this;
    }

    public LedViewHolder error(@NonNull Throwable t) {
        Snackbar.make(vRoot, t.getMessage(), Snackbar.LENGTH_LONG).show();
        return this;
    }

    public void setOnInteractionListener(OnInteractionListener listener) {
        this.listener = listener;
    }

}
