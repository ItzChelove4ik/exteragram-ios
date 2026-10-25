package com.google.android.gms.cloudmessaging;

import android.os.IBinder;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public final class zzd implements Parcelable {
    public static final Parcelable.Creator<zzd> CREATOR = null;
    final Messenger zza;

    static {
        CREATOR = new zzb();
    }

    public zzd(IBinder r2) {
        this.zza = new Messenger(r2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object r2) {
        if (r2 != null) goto L8;
    L6:
        return false;
    L8:
        return this.zza.getBinder().equals(((zzd) r2).zza.getBinder());
    }

    public final int hashCode() {
        return this.zza.getBinder().hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r1, int r2) {
        r1.writeStrongBinder(this.zza.getBinder());
    }
}
