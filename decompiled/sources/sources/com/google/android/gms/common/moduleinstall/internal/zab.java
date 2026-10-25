package com.google.android.gms.common.moduleinstall.internal;

import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import java.util.Comparator;

/* loaded from: classes4.dex */
final /* synthetic */ class zab implements Comparator {
    static final /* synthetic */ zab zaa = null;

    static {
        zaa = new zab();
    }

    private /* synthetic */ zab() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object r3, Object r4) {
        Feature r42 = (Feature) r4;
        Feature r32 = (Feature) r3;
        Parcelable.Creator<ApiFeatureRequest> r2 = ApiFeatureRequest.CREATOR;
        if (r32.getName().equals(r42.getName()) == true) goto L7;
        return r32.getName().compareTo(r42.getName());
    L7:
        return Long.compare(r32.getVersion(), r42.getVersion());
    }
}
