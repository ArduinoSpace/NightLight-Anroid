package ru.annin.nightlight.presentation.presenter;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.annotation.NonNull;

import com.github.ivbaranov.rxbluetooth.RxBluetooth;

import java.util.concurrent.TimeUnit;

import ru.annin.nightlight.R;
import ru.annin.nightlight.presentation.common.BasePresenter;
import ru.annin.nightlight.presentation.ui.view.ConnectView;
import ru.annin.nightlight.presentation.ui.viewholder.ConnectViewHolder;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * <p>Presenter экрана подключения к устройству.</p>
 *
 * @author P. Annin.
 */
public class ConnectPresenter extends BasePresenter<ConnectViewHolder, ConnectView> {

    private static final int REQUEST_ENABLE_BLE = 23;

    // Component's
    private final CompositeSubscription rxSubscription;

    // Repositories
    private final RxBluetooth rxBluetooth;


    public ConnectPresenter(@NonNull Context context) {
        rxBluetooth = new RxBluetooth(context);
        rxSubscription = new CompositeSubscription();
    }

    public void onInitialization(@NonNull Activity activity) {
        if (!rxBluetooth.isBluetoothEnabled()) {
            rxBluetooth.enableBluetooth(activity, REQUEST_ENABLE_BLE);
        } else {
            mViewHolder.showLoading();
            scanDevices();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!rxBluetooth.isBluetoothEnabled()) {
            mViewHolder.hideLoading()
                    .error(R.string.msg_ble_enable);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        rxBluetooth.cancelDiscovery();
        rxSubscription.unsubscribe();
    }

    @NonNull
    @Override
    public BasePresenter setViewHolder(ConnectViewHolder vh) {
        super.setViewHolder(vh);
        mViewHolder.setOnInteractionListener(onViewHolderListener);
        return this;
    }

    private void scanDevices() {
        mViewHolder.clearData();
        if (!rxBluetooth.isBluetoothEnabled()) {
            mViewHolder.hideLoading()
                    .error(R.string.msg_ble_enable);
        } else {
            final Subscription subscription = rxBluetooth.observeDevices()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.computation())
                    .timeout(8_000, TimeUnit.MILLISECONDS, Observable.empty(), AndroidSchedulers.mainThread())
                    .subscribe(mViewHolder::addData,
                            mViewHolder::error,
                            () -> {
                                rxBluetooth.cancelDiscovery();
                                mViewHolder.hideLoading()
                                        .isEmpty();
                            });
            rxSubscription.add(subscription);
            rxBluetooth.startDiscovery();
        }
    }

    private final ConnectViewHolder.OnInteractionListener onViewHolderListener = new ConnectViewHolder.OnInteractionListener() {
        @Override
        public void onRefresh() {
            scanDevices();
        }

        @Override
        public void onDeviceClick(@NonNull BluetoothDevice device) {
            mView.navigateLed(device);
        }
    };
}
