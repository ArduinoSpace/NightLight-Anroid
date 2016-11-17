package ru.annin.nightlight.presentation.presenter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.ivbaranov.rxbluetooth.BluetoothConnection;
import com.github.ivbaranov.rxbluetooth.RxBluetooth;

import java.util.UUID;

import ru.annin.nightlight.presentation.common.BasePresenter;
import ru.annin.nightlight.presentation.ui.view.LedView;
import ru.annin.nightlight.presentation.ui.viewholder.LedViewHolder;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * <p>Presenter экрана "Настрой Led".</p>
 *
 * @author P. Annin.
 */
public class LedPresenter extends BasePresenter<LedViewHolder, LedView> {

    private static final UUID CONNECT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    //  Component's
    private final CompositeSubscription rxSubscription;
    private BluetoothConnection bluetoothConnection;

    // Repositories
    private final RxBluetooth rxBluetooth;

    // Data's
    private boolean enableEffectRainbow;
    @ColorInt
    private int selectColor;

    public LedPresenter(@NonNull Context context) {
        rxBluetooth = new RxBluetooth(context);
        rxSubscription = new CompositeSubscription();
        enableEffectRainbow = true;
        selectColor = Color.RED;
    }

    public void onInitialization(@Nullable BluetoothDevice device) {
        if (bluetoothConnection != null) {
            bluetoothConnection.closeConnection();
        }
        mViewHolder.setEffectRainbow(enableEffectRainbow);
        rxBluetooth.observeConnectDevice(device, CONNECT_UUID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(socket -> {
                    try {
                        bluetoothConnection = new BluetoothConnection(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bluetoothConnection != null) {
            bluetoothConnection.closeConnection();
        }
        rxBluetooth.cancelDiscovery();
        rxSubscription.unsubscribe();
    }

    @NonNull
    @Override
    public BasePresenter setViewHolder(LedViewHolder mViewHolder) {
        super.setViewHolder(mViewHolder);
        mViewHolder.setOnInteractionListener(onViewHolderListener);
        return this;
    }

    private final LedViewHolder.OnInteractionListener onViewHolderListener = new LedViewHolder.OnInteractionListener() {
        @Override
        public void onChangeEffectRainbow(boolean flag) {
            enableEffectRainbow = flag;
            if (bluetoothConnection != null) {
                if (enableEffectRainbow) {
                    byte[] packet = {(byte) 0xBB, (byte) 0x00, (byte) 0x00, (byte) 0x00};
                    bluetoothConnection.send(packet);
                } else {
                    int red = Color.red(selectColor);
                    int green = Color.green(selectColor);
                    int blue = Color.blue(selectColor);
                    byte[] packet = {(byte) 0xAA, (byte) red, (byte) green, (byte) blue};
                    bluetoothConnection.send(packet);
                }
            }
        }

        @Override
        public void onChangeColor() {
            mView.selectColor(selectColor, color -> {
                selectColor = color;
                enableEffectRainbow = false;
                mViewHolder.setEffectRainbow(false);
                int red = Color.red(selectColor);
                int green = Color.green(selectColor);
                int blue = Color.blue(selectColor);
                byte[] packet = {(byte) 0xAA, (byte) red, (byte) green, (byte) blue};
                bluetoothConnection.send(packet);
            });
        }
    };
}
