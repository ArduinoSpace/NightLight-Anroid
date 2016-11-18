package ru.annin.nightlight.presentation.ui.activity;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import ru.annin.nightlight.R;
import ru.annin.nightlight.presentation.common.BaseActivity;
import ru.annin.nightlight.presentation.presenter.ConnectPresenter;
import ru.annin.nightlight.presentation.ui.view.ConnectView;
import ru.annin.nightlight.presentation.ui.viewholder.ConnectViewHolder;

/**
 * <p>Экран подключения к BLE устройству.</p>
 *
 * @author P. Annin.
 */
public class ConnectActivity extends BaseActivity<ConnectPresenter> implements ConnectView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        final View vRoot = findViewById(R.id.root);
        final ConnectViewHolder viewHolder = new ConnectViewHolder(vRoot);

        mPresenter = new ConnectPresenter(this);
        mPresenter.setViewHolder(viewHolder);
        mPresenter.setView(this);
        mPresenter.onInitialization(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mPresenter == null || !mPresenter.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void navigateLed(@NonNull BluetoothDevice device) {
        mNavigator.navigate2Led(this, device);
        finish();
    }
}
