package com.google.android.gms.internal.play_billing;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.cast.MediaError;

/* loaded from: classes5.dex */
public final class zzak extends zzap implements zzam {
    public zzak(IBinder r2) {
        super(r2, "com.android.vending.billing.IInAppBillingService");
    }

    @Override // com.google.android.gms.internal.play_billing.zzam
    public final int zza(int r2, String r3, String r4) {
        Parcel r22 = zzs();
        r22.writeInt(3);
        r22.writeString(r3);
        r22.writeString(r4);
        Parcel r1 = zzt(5, r22);
        int r23 = r1.readInt();
        r1.recycle();
        return r23;
    }

    @Override // com.google.android.gms.internal.play_billing.zzam
    public final int zzc(int r2, String r3, String r4, Bundle r5) {
        Parcel r0 = zzs();
        r0.writeInt(r2);
        r0.writeString(r3);
        r0.writeString(r4);
        zzar.zzc(r0, r5);
        Parcel r1 = zzt(10, r0);
        int r22 = r1.readInt();
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.internal.play_billing.zzam
    public final Bundle zze(int r2, String r3, String r4, Bundle r5) {
        Parcel r22 = zzs();
        r22.writeInt(9);
        r22.writeString(r3);
        r22.writeString(r4);
        zzar.zzc(r22, r5);
        Parcel r1 = zzt(12, r22);
        Bundle r23 = (Bundle) zzar.zza(r1, Bundle.CREATOR);
        r1.recycle();
        return r23;
    }

    @Override // com.google.android.gms.internal.play_billing.zzam
    public final Bundle zzf(int r1, String r2, String r3, String r4, String r5) {
        Parcel r12 = zzs();
        r12.writeInt(3);
        r12.writeString(r2);
        r12.writeString(r3);
        r12.writeString(r4);
        r12.writeString(null);
        Parcel r0 = zzt(3, r12);
        Bundle r13 = (Bundle) zzar.zza(r0, Bundle.CREATOR);
        r0.recycle();
        return r13;
    }

    @Override // com.google.android.gms.internal.play_billing.zzam
    public final Bundle zzg(int r1, String r2, String r3, String r4, String r5, Bundle r6) {
        Parcel r52 = zzs();
        r52.writeInt(r1);
        r52.writeString(r2);
        r52.writeString(r3);
        r52.writeString(r4);
        r52.writeString(null);
        zzar.zzc(r52, r6);
        Parcel r0 = zzt(8, r52);
        Bundle r12 = (Bundle) zzar.zza(r0, Bundle.CREATOR);
        r0.recycle();
        return r12;
    }

    @Override // com.google.android.gms.internal.play_billing.zzam
    public final Bundle zzh(int r2, String r3, String r4, String r5) {
        Parcel r22 = zzs();
        r22.writeInt(3);
        r22.writeString(r3);
        r22.writeString(r4);
        r22.writeString(r5);
        Parcel r1 = zzt(4, r22);
        Bundle r23 = (Bundle) zzar.zza(r1, Bundle.CREATOR);
        r1.recycle();
        return r23;
    }

    @Override // com.google.android.gms.internal.play_billing.zzam
    public final Bundle zzi(int r2, String r3, String r4, String r5, Bundle r6) {
        Parcel r0 = zzs();
        r0.writeInt(r2);
        r0.writeString(r3);
        r0.writeString(r4);
        r0.writeString(r5);
        zzar.zzc(r0, r6);
        Parcel r1 = zzt(11, r0);
        Bundle r22 = (Bundle) zzar.zza(r1, Bundle.CREATOR);
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.internal.play_billing.zzam
    public final Bundle zzj(int r2, String r3, String r4, Bundle r5, Bundle r6) {
        Parcel r0 = zzs();
        r0.writeInt(r2);
        r0.writeString(r3);
        r0.writeString(r4);
        zzar.zzc(r0, r5);
        zzar.zzc(r0, r6);
        Parcel r1 = zzt(MediaError.DetailedErrorCode.BREAK_CLIP_LOADING_ERROR, r0);
        Bundle r22 = (Bundle) zzar.zza(r1, Bundle.CREATOR);
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.internal.play_billing.zzam
    public final int zzw(int r2, String r3, String r4) {
        Parcel r0 = zzs();
        r0.writeInt(r2);
        r0.writeString(r3);
        r0.writeString(r4);
        Parcel r1 = zzt(1, r0);
        int r22 = r1.readInt();
        r1.recycle();
        return r22;
    }
}
