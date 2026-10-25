package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class TelemetryData extends AbstractSafeParcelable {
    public static final Parcelable.Creator<TelemetryData> CREATOR = null;
    private final int zaa;
    private List zab;

    static {
        CREATOR = new zaae();
    }

    public TelemetryData(int r1, List r2) {
        this.zaa = r1;
        this.zab = r2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 1, this.zaa);
        SafeParcelWriter.writeTypedList(r3, 2, this.zab, false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }

    public final int zaa() {
        return this.zaa;
    }

    public final List zab() {
        return this.zab;
    }

    public final void zac(MethodInvocation r2) {
        if (this.zab != null) goto L5;
        this.zab = new ArrayList();
    L5:
        this.zab.add(r2);
    }
}
