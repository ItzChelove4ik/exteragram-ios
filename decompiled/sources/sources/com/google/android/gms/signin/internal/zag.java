package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* loaded from: classes5.dex */
public final class zag extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<zag> CREATOR = null;
    private final List zaa;
    private final String zab;

    static {
        CREATOR = new zah();
    }

    public zag(List r1, String r2) {
        this.zaa = r1;
        this.zab = r2;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        if (this.zab == null) goto L7;
        return Status.RESULT_SUCCESS;
    L7:
        return Status.RESULT_CANCELED;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        List r52 = this.zaa;
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeStringList(r4, 1, r52, false);
        SafeParcelWriter.writeString(r4, 2, this.zab, false);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }
}
