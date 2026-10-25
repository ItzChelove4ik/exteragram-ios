package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.view.Surface;
import com.google.android.gms.common.api.ApiMetadata;

/* loaded from: classes4.dex */
public abstract class zzex extends zzb implements zzey {
    public zzex() {
        super("com.google.android.gms.cast.remote_display.ICastRemoteDisplayCallbacks");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    public final boolean zza(int r4, Parcel r5, Parcel r6, int r7) {
        if (r4 != 1) goto L5;
        int r42 = r5.readInt();
        int r0 = r5.readInt();
        Surface r1 = (Surface) zzc.zzb(r5, Surface.CREATOR);
        ApiMetadata r2 = (ApiMetadata) zzc.zzb(r5, ApiMetadata.CREATOR);
        zzc.zzf(r5);
        zzb(r42, r0, r1, r2);
    L19:
        r6.writeNoException();
        return true;
    L5:
        if (r4 != 2) goto L7;
        int r43 = r5.readInt();
        ApiMetadata r02 = (ApiMetadata) zzc.zzb(r5, ApiMetadata.CREATOR);
        zzc.zzf(r5);
        zzd(r43, r02);
        goto L19
    L7:
        if (r4 != 3) goto L9;
        ApiMetadata r44 = (ApiMetadata) zzc.zzb(r5, ApiMetadata.CREATOR);
        zzc.zzf(r5);
        zzf(r44);
        goto L19
    L9:
        if (r4 != 4) goto L11;
        ApiMetadata r45 = (ApiMetadata) zzc.zzb(r5, ApiMetadata.CREATOR);
        zzc.zzf(r5);
        zzc(r45);
        goto L19
    L11:
        if (r4 == 5) goto L14;
        return false;
    L14:
        boolean r46 = zzc.zza(r5);
        ApiMetadata r03 = (ApiMetadata) zzc.zzb(r5, ApiMetadata.CREATOR);
        zzc.zzf(r5);
        zze(r46, r03);
        goto L19
    }
}
