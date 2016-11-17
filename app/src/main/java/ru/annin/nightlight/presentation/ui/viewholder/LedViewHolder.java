package ru.annin.nightlight.presentation.ui.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

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

    // Listener's
    private OnInteractionListener listener;

    public LedViewHolder(@NonNull View view) {
        super(view);
        vToolbar = (Toolbar) vRoot.findViewById(R.id.toolbar);
        cbEffectRainbow = (AppCompatCheckBox) vRoot.findViewById(R.id.cb_effect_rainbow);
        btnChangeColor = (Button) vRoot.findViewById(R.id.btn_change_color);

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

    public void setEffectRainbow(boolean effectRainbow) {
        cbEffectRainbow.setChecked(effectRainbow);
    }

    public void setOnInteractionListener(OnInteractionListener listener) {
        this.listener = listener;
    }

}
