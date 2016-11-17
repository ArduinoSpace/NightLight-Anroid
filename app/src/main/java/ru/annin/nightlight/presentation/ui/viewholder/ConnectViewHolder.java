package ru.annin.nightlight.presentation.ui.viewholder;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import ru.annin.nightlight.R;
import ru.annin.nightlight.presentation.common.BaseViewHolder;
import ru.annin.nightlight.presentation.ui.adapter.DeviceAdapter;

/**
 * <p>ViewHolder экрана "Подключение к устройству".</p>
 *
 * @author P. Annin.
 */
public class ConnectViewHolder extends BaseViewHolder {

    public interface OnInteractionListener {
        void onRefresh();
        void onDeviceClick(@NonNull BluetoothDevice device);
    }

    // View's
    private final Toolbar vToolbar;
    private final SwipeRefreshLayout vSwipe;
    private final RecyclerView rvDevices;
    private final ProgressBar pbLoading;
    private final View vEmpty;

    // Adapter's
    private final DeviceAdapter deviceAdapter;

    // Listener's
    private OnInteractionListener listener;

    public ConnectViewHolder(@NonNull View view) {
        super(view);
        vToolbar = (Toolbar) vRoot.findViewById(R.id.toolbar);
        vSwipe = (SwipeRefreshLayout) vRoot.findViewById(R.id.swipe_refresh);
        rvDevices = (RecyclerView) vRoot.findViewById(R.id.rv_devices);
        pbLoading = (ProgressBar) vRoot.findViewById(R.id.pb_loading);
        vEmpty = vRoot.findViewById(R.id.txt_empty);

        // Setup View's
        deviceAdapter = new DeviceAdapter();
        rvDevices.setItemAnimator(new DefaultItemAnimator());
        rvDevices.addItemDecoration(new DividerItemDecoration(vRoot.getContext(), DividerItemDecoration.VERTICAL));
        rvDevices.setHasFixedSize(false);
        rvDevices.setAdapter(deviceAdapter);

        deviceAdapter.setOnClickListener(device -> {
            if (listener != null) {
                listener.onDeviceClick(device);
            }
        });
        vSwipe.setOnRefreshListener(() -> {
            if (listener != null) {
                listener.onRefresh();
            }
        });
    }

    public ConnectViewHolder addData(@NonNull BluetoothDevice device) {
        deviceAdapter.addItem(device);
        vEmpty.setVisibility(View.GONE);
        return this;
    }

    public ConnectViewHolder clearData() {
        deviceAdapter.clear();
        vEmpty.setVisibility(View.VISIBLE);
        return this;
    }

    public ConnectViewHolder showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
        vSwipe.setRefreshing(false);
        vEmpty.setVisibility(View.GONE);
        return this;
    }

    public ConnectViewHolder hideLoading() {
        pbLoading.setVisibility(View.GONE);
        vSwipe.setRefreshing(false);
        return this;
    }

    public ConnectViewHolder isEmpty() {
        vEmpty.setVisibility(deviceAdapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);
        return this;
    }

    public ConnectViewHolder error(@StringRes int res) {
        Snackbar.make(vRoot, res, Snackbar.LENGTH_LONG).show();
        return this;
    }

    public ConnectViewHolder error(@NonNull Throwable t) {
        Snackbar.make(vRoot, t.getMessage(), Snackbar.LENGTH_LONG).show();
        return this;
    }

    public void setOnInteractionListener(OnInteractionListener listener) {
        this.listener = listener;
    }
}
