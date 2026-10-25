package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.wearable.MessageEvent;

/* loaded from: classes5.dex */
public final class zzfx extends AbstractSafeParcelable implements MessageEvent {
    public static final Parcelable.Creator<zzfx> CREATOR = null;
    private final int zza;
    private final String zzb;
    private final byte[] zzc;
    private final String zzd;

    static {
        CREATOR = new zzfy();
    }

    public zzfx(int r1, String r2, byte[] r3, String r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // com.google.android.gms.wearable.MessageEvent
    public final byte[] getData() {
        return this.zzc;
    }

    @Override // com.google.android.gms.wearable.MessageEvent
    public final String getPath() {
        return this.zzb;
    }

    @Override // com.google.android.gms.wearable.MessageEvent
    public final String getSourceNodeId() {
        return this.zzd;
    }

    public final String toString() {
        int r0 = this.zza;
        String r1 = this.zzb;
        byte[] r4 = this.zzc;
        if (r4 != null) goto L5;
        Object r42 = "null";
    L7:
        return "MessageEventParcelable[" + r0 + "," + r1 + ", size=" + r42.toString() + "]";
    L5:
        r42 = Integer.valueOf(r4.length);
        goto L7
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeInt(r4, 2, this.zza);
        SafeParcelWriter.writeString(r4, 3, this.zzb, false);
        SafeParcelWriter.writeByteArray(r4, 4, this.zzc, false);
        SafeParcelWriter.writeString(r4, 5, this.zzd, false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
