package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zaa extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<zaa> CREATOR = null;
    final int zaa;
    private int zab;
    private Intent zac;

    static {
        CREATOR = new zab();
    }

    public zaa(int r1, int r2, Intent r3) {
        this.zaa = r1;
        this.zab = r2;
        this.zac = r3;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        if (this.zab != 0) goto L7;
        return Status.RESULT_SUCCESS;
    L7:
        return Status.RESULT_CANCELED;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r0 = this.zaa;
        int r1 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeInt(r4, 1, r0);
        SafeParcelWriter.writeInt(r4, 2, this.zab);
        SafeParcelWriter.writeParcelable(r4, 3, this.zac, r5, false);
        SafeParcelWriter.finishObjectHeader(r4, r1);
    }
}
