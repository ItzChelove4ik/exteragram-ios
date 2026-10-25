package com.google.android.gms.internal.measurement;

import com.google.android.gms.cast.MediaStatus;
import java.io.InputStream;
import kotlin.io.CloseableKt;

/* loaded from: classes4.dex */
public final class zzna implements zzrt {
    private final boolean zza;

    public zzna(boolean r1) {
        this.zza = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzrt
    public final /* bridge */ /* synthetic */ Object zza(zzrs r6) {
        InputStream r62 = zzst.zzc(r6);
        int r0 = 4096;
        if (this.zza == true) goto L6;
        zznd r5 = zznd.zzb(zzacv.zzM(r62, 4096), false);     // Catch: Throwable -> L13
    L17:
        CloseableKt.closeFinally(r62, null);
        return r5;
    L6:
        if ((r62 instanceof zzsf) == false) goto L15;
        long r1 = ((zzsf) r62).zza().length();     // Catch: Throwable -> L13
        if (r1 != 0) goto L11;
        r0 = 512;
        goto L15
    L11:
        if (r1 >= MediaStatus.COMMAND_EDIT_TRACKS) goto L15;
        r0 = (int) r1;     // Catch: Throwable -> L13
    L15:
        r5 = zznd.zzb(zzacv.zzM(r62, r0), true);     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        throw th;     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        CloseableKt.closeFinally(r62, th);
        throw th;
    }
}
