package com.google.android.gms.internal.clearcut;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzdu<T> implements zzef<T> {
    private final zzdo zzmn;
    private final boolean zzmo;
    private final zzex<?, ?> zzmx;
    private final zzbu<?> zzmy;

    private zzdu(zzex<?, ?> r1, zzbu<?> r2, zzdo r3) {
        this.zzmx = r1;
        this.zzmo = r2.zze(r3);
        this.zzmy = r2;
        this.zzmn = r3;
    }

    public static <T> zzdu<T> zza(zzex<?, ?> r1, zzbu<?> r2, zzdo r3) {
        return new zzdu(r1, r2, r3);
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final boolean equals(T r3, T r4) {
        if (this.zzmx.zzq(r3).equals(this.zzmx.zzq(r4)) == true) goto L7;
        return false;
    L7:
        if (this.zzmo == true) goto L9;
        return true;
    L9:
        return this.zzmy.zza(r3).equals(this.zzmy.zza(r4));
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final int hashCode(T r3) {
        int r0 = this.zzmx.zzq(r3).hashCode();
        if (this.zzmo == true) goto L5;
        return r0;
    L5:
        return (r0 * 53) + this.zzmy.zza(r3).hashCode();
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final T newInstance() {
        return (T) this.zzmn.zzbd().zzbi();
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final void zza(T r3, zzfr r4) {
        Iterator<Map.Entry<?, Object>> r0 = this.zzmy.zza(r3).iterator();
        if (r0.hasNext() == true) goto L6;
        zzex<?, ?> r2 = this.zzmx;
        r2.zzc(r2.zzq(r3), r4);
        return;
    L6:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.next().getKey());
        throw null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.clearcut.zzef
    public final void zza(T r7, byte[] r8, int r9, int r10, zzay r11) {
        zzcg r72 = (zzcg) r7;
        zzey r6 = r72.zzjp;
        if (r6 != zzey.zzea()) goto L5;
        r6 = zzey.zzeb();
        r72.zzjp = r6;
    L5:
        zzey r4 = r6;
    L6:
        if (r9 >= r10) goto L32;
        int r2 = zzax.zza(r8, r9, r11);
        int r0 = r11.zzfd;
        if (r0 != 11) goto L9;
        byte[] r1 = r8;
        int r3 = r10;
        zzay r5 = r11;
        int r62 = 0;
        zzbb r82 = null;
    L14:
        if (r2 >= r3) goto L27;
        r9 = zzax.zza(r1, r2, r5);
        int r102 = r5.zzfd;
        int r112 = r102 >>> 3;
        int r02 = r102 & 7;
        if (r112 != 2) goto L18;
        if (r02 != 0) goto L25;
        r2 = zzax.zza(r1, r9, r5);
        r62 = r5.zzfd;
    L25:
        if (r102 == 12) goto L28;
        r2 = zzax.zza(r102, r1, r9, r3, r5);
    L28:
        if (r82 == null) goto L30;
        r4.zzb((r62 << 3) | 2, r82);
    L30:
        r8 = r1;
        r10 = r3;
        r11 = r5;
        goto L6
    L18:
        if (r112 != 3) goto L25;
        if (r02 != 2) goto L25;
        r2 = zzax.zze(r1, r9, r5);
        r82 = (zzbb) r5.zzff;
        goto L14
    L27:
        r9 = r2;
        goto L28
    L9:
        byte[] r12 = r8;
        int r32 = r10;
        zzay r52 = r11;
        if ((r0 & 7) == 2) goto L11;
        r9 = zzax.zza(r0, r12, r2, r32, r52);
        goto L6
    L11:
        r9 = zzax.zza(r0, r12, r2, r32, r4, r52);
        goto L6
    L32:
        if (r9 != r10) goto L35;
        return;
    L35:
        throw zzco.zzbo();
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final void zzc(T r2) {
        this.zzmx.zzc(r2);
        this.zzmy.zzc(r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final void zzc(T r2, T r3) {
        zzeh.zza(this.zzmx, r2, r3);
        if (this.zzmo == false) goto L6;
        zzeh.zza(this.zzmy, r2, r3);
        return;
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final int zzm(T r3) {
        zzex<?, ?> r0 = this.zzmx;
        int r02 = r0.zzr(r0.zzq(r3));
        if (this.zzmo == true) goto L5;
        return r02;
    L5:
        return r02 + this.zzmy.zza(r3).zzat();
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final boolean zzo(T r1) {
        return this.zzmy.zza(r1).isInitialized();
    }
}
