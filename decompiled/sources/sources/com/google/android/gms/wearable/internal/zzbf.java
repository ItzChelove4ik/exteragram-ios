package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.wearable.ChannelApi;

/* loaded from: classes5.dex */
public final class zzbf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbf> CREATOR = null;
    final zzbq zza;
    final int zzb;
    final int zzc;
    final int zzd;

    static {
        CREATOR = new zzbg();
    }

    public zzbf(zzbq r1, int r2, int r3, int r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    public final String toString() {
        String r0 = String.valueOf(this.zza);
        int r1 = this.zzb;
        if (r1 == 1) goto L12;
        if (r1 == 2) goto L11;
        if (r1 != 3) goto L7;
        String r12 = "INPUT_CLOSED";
    L13:
        int r5 = this.zzc;
        if (r5 == 0) goto L22;
        if (r5 == 1) goto L21;
        if (r5 == 2) goto L20;
        if (r5 == 3) goto L19;
        String r2 = Integer.toString(r5);
    L24:
        return "ChannelEventParcelable[, channel=" + r0 + ", type=" + r12 + ", closeReason=" + r2 + ", appErrorCode=" + this.zzd + "]";
    L19:
        r2 = "CLOSE_REASON_LOCAL_CLOSE";
        goto L24
    L20:
        r2 = "CLOSE_REASON_REMOTE_CLOSE";
        goto L24
    L21:
        r2 = "CLOSE_REASON_DISCONNECTED";
        goto L24
    L22:
        r2 = "CLOSE_REASON_NORMAL";
        goto L24
    L7:
        if (r1 == 4) goto L9;
        r12 = Integer.toString(r1);
        goto L13
    L9:
        r12 = "OUTPUT_CLOSED";
        goto L13
    L11:
        r12 = "CHANNEL_CLOSED";
        goto L13
    L12:
        r12 = "CHANNEL_OPENED";
        goto L13
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 2, this.zza, r6, false);
        SafeParcelWriter.writeInt(r5, 3, this.zzb);
        SafeParcelWriter.writeInt(r5, 4, this.zzc);
        SafeParcelWriter.writeInt(r5, 5, this.zzd);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    public final void zza(ChannelApi.ChannelListener r3) {
        int r0 = this.zzb;
        if (r0 != 1) goto L5;
        r3.onChannelOpened(this.zza);
        return;
    L5:
        if (r0 != 2) goto L7;
        r3.onChannelClosed(this.zza, this.zzc, this.zzd);
        return;
    L7:
        if (r0 != 3) goto L9;
        r3.onInputClosed(this.zza, this.zzc, this.zzd);
        return;
    L9:
        if (r0 == 4) goto L12;
        Log.w("ChannelEventParcelable", "Unknown type: " + r0);
        return;
    L12:
        r3.onOutputClosed(this.zza, this.zzc, this.zzd);
    }
}
