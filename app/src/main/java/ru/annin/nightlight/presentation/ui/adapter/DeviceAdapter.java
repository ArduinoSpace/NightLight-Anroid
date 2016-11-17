package ru.annin.nightlight.presentation.ui.adapter;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.annin.nightlight.R;
import ru.annin.nightlight.presentation.ui.viewholder.ItemDeviceConnectViewHolder;

/**
 * <p>Адаптер "Устройств".</p>
 *
 * @author P. Annin.
 */
public class DeviceAdapter extends RecyclerView.Adapter<ItemDeviceConnectViewHolder> {

    public interface OnClickListener {
        void onItemClick(@NonNull BluetoothDevice device);
    }

    // Data's
    private final List<BluetoothDevice> data;

    // Listener's
    private OnClickListener listener;

    public DeviceAdapter() {
        data = new ArrayList<>();
    }

    @Override
    public ItemDeviceConnectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View vRoot = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_connect_device, parent, false);
        return new ItemDeviceConnectViewHolder(vRoot);
    }

    @Override
    public void onBindViewHolder(ItemDeviceConnectViewHolder holder, int position) {
        final BluetoothDevice device = data.get(position);
        holder.showItem(device)
                .setOnClickListener(view -> {
                    if (listener != null) {
                        listener.onItemClick(device);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(@NonNull BluetoothDevice device) {
        data.add(device);
        notifyItemInserted(data.size() - 1);
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
}
