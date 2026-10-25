package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zay extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zay> CREATOR = null;
    final int zaa;
    final IBinder zab;
    private final ConnectionResult zac;
    private final boolean zad;
    private final boolean zae;

    static {
        CREATOR = new zaz();
    }

    public zay(int r1, IBinder r2, ConnectionResult r3, boolean r4, boolean r5) {
        this.zaa = r1;
        this.zab = r2;
        this.zac = r3;
        this.zad = r4;
        this.zae = r5;
    }

    public final boolean equals(Object r5) {
        if (r5 != null) goto L6;
        return false;
    L6:
        if (this != r5) goto L9;
        return true;
    L9:
        if ((r5 instanceof zay) == true) goto L11;
        return false;
    L11:
        zay r52 = (zay) r5;
        if (this.zac.equals(r52.zac) == true) goto L14;
    L16:
        return false;
    L14:
        if (Objects.equal(zaa(), r52.zaa()) == false) goto L16;
        return true;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, this.zaa);
        SafeParcelWriter.writeIBinder(r5, 2, this.zab, false);
        SafeParcelWriter.writeParcelable(r5, 3, this.zac, r6, false);
        SafeParcelWriter.writeBoolean(r5, 4, this.zad);
        SafeParcelWriter.writeBoolean(r5, 5, this.zae);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    public final IAccountAccessor zaa() {
        IBinder r0 = this.zab;
        if (r0 != null) goto L7;
        return null;
    L7:
        return IAccountAccessor.Stub.asInterface(r0);
    }

    public final ConnectionResult zab() {
        return this.zac;
    }

    public final boolean zac() {
        return this.zad;
    }

    public final boolean zad() {
        return this.zae;
    }
}
