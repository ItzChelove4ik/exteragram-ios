package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public class ClientIdentity extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ClientIdentity> CREATOR = null;
    public final String packageName;
    public final int uid;

    static {
        CREATOR = new zaa();
    }

    public ClientIdentity(int r1, String r2) {
        this.uid = r1;
        this.packageName = r2;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof ClientIdentity) == true) goto L8;
        return false;
    L8:
        ClientIdentity r52 = (ClientIdentity) r5;
        if (r52.uid == this.uid) goto L11;
    L13:
        return false;
    L11:
        if (Objects.equal(r52.packageName, this.packageName) == false) goto L13;
        return true;
    }

    public final int hashCode() {
        return this.uid;
    }

    public final String toString() {
        int r0 = this.uid;
        int r1 = String.valueOf(r0).length();
        String r4 = this.packageName;
        int r12 = r1 + 1;
        StringBuilder r3 = new StringBuilder(r12 + String.valueOf(r4).length());
        r3.append(r0);
        r3.append(":");
        r3.append(r4);
        return r3.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = this.uid;
        int r0 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 1, r42);
        SafeParcelWriter.writeString(r3, 2, this.packageName, false);
        SafeParcelWriter.finishObjectHeader(r3, r0);
    }
}
