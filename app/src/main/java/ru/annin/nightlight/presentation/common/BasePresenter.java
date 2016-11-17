package ru.annin.nightlight.presentation.common;

import android.support.annotation.NonNull;

/**
 * <p>Базовый Presenter (MVP).</p>
 *
 * @author P. Annin.
 */
public abstract class BasePresenter<VH extends BaseViewHolder, V> implements Presenter {

    protected VH mViewHolder;
    protected V mView;

    @Override
    public void onResume() {
        // Empty.
    }

    @Override
    public void onPause() {
        // Empty.
    }

    @Override
    public void onDestroy() {
        // Empty.
    }

    @NonNull
    public BasePresenter setViewHolder(VH mViewHolder) {
        this.mViewHolder = mViewHolder;
        return this;
    }

    @NonNull
    public BasePresenter setView(V view) {
        this.mView = view;
        return this;
    }
}