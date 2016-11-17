package ru.annin.nightlight.presentation.ui.viewholder;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import ru.annin.nightlight.R;

/**
 * <p>ViewHolder элемента списка усройств.</p>
 *
 * @author P. Annin.
 */
public class ItemDeviceConnectViewHolder extends RecyclerView.ViewHolder {

    // View's
    private final TextView txtDeviceName;
    private final TextView txtDeviceMacAddress;

    public ItemDeviceConnectViewHolder(View vRoot) {
        super(vRoot);
        txtDeviceName = (TextView) vRoot.findViewById(R.id.txt_device_name);
        txtDeviceMacAddress = (TextView) vRoot.findViewById(R.id.txt_device_mac_address);
    }

    public ItemDeviceConnectViewHolder showItem(@NonNull BluetoothDevice device) {
        final String deviceName = device.getName();
        final String deviceAddress = device.getAddress();

        // Device Name
        if (!TextUtils.isEmpty(deviceName)) {
            txtDeviceName.setText(deviceName);
            txtDeviceName.setVisibility(View.VISIBLE);
        } else {
            txtDeviceName.setVisibility(View.GONE);
        }

        // Device Mac Address
        if (!TextUtils.isEmpty(deviceAddress)) {
            txtDeviceMacAddress.setText(deviceAddress);
            txtDeviceMacAddress.setVisibility(View.VISIBLE);
        } else {
            txtDeviceMacAddress.setVisibility(View.GONE);
        }

        return this;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
    }
}
