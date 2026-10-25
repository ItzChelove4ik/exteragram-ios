package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public class GoogleSignInOptionsExtensionParcelable extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GoogleSignInOptionsExtensionParcelable> CREATOR = null;
    final int zaa;
    private int zab;
    private Bundle zac;

    static {
        CREATOR = new zaa();
    }

    public GoogleSignInOptionsExtensionParcelable(int r1, int r2, Bundle r3) {
        this.zaa = r1;
        this.zab = r2;
        this.zac = r3;
    }

    public int getType() {
        return this.zab;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 1, this.zaa);
        SafeParcelWriter.writeInt(r3, 2, getType());
        SafeParcelWriter.writeBundle(r3, 3, this.zac, false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
