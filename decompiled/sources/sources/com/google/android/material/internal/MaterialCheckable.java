package com.google.android.material.internal;

import android.widget.Checkable;
import com.google.android.material.internal.MaterialCheckable;

/* loaded from: classes5.dex */
public interface MaterialCheckable<T extends MaterialCheckable<T>> extends Checkable {

    public interface OnCheckedChangeListener<C> {
        void onCheckedChanged(C r1, boolean r2);
    }

    int getId();

    void setInternalOnCheckedChangeListener(OnCheckedChangeListener<T> r1);
}
