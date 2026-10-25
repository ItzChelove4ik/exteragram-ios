package com.google.android.gms.internal.measurement;

import com.google.common.base.Preconditions;
import java.util.Objects;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
public final class zzmv implements Comparable {
    final long zza;
    final String zzb;
    final int zzc;
    final long zzd;
    final Object zze;
    private final RuntimeException zzf;

    public zzmv(long r5, String r7, int r8, long r9, Object r11) {
        boolean r1 = true;
        if (r5 == 0) goto L5;
        boolean r0 = false;
    L6:
        if (r7 != null) goto L8;
        boolean r3 = false;
    L9:
        if (r0 == r3) goto L12;
        r1 = false;
    L12:
        Preconditions.checkArgument(r1);
        this.zza = r5;
        this.zzb = r7;
        this.zzc = r8;
        this.zzd = r9;
        this.zze = r11;
        if (r8 != 5) goto L25;
        if (r11 != null) goto L18;
        this.zzf = new NullPointerException("Null stringOrBytes");
        return;
    L18:
        if ((r11 instanceof byte[]) == false) goto L20;
    L23:
        this.zzf = null;
        return;
    L20:
        if ((r11 instanceof zzacr) == true) goto L23;
        this.zzf = new RuntimeException("Wrong stringOrBytes type: ".concat(String.valueOf(r11.getClass())));
        return;
    L25:
        this.zzf = null;
        return;
    L8:
        r3 = true;
        goto L9
    L5:
        r0 = true;
        goto L6
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object r5) {
        zzmv r52 = (zzmv) r5;
        long r0 = r52.zza;
        long r2 = this.zza;
        int r02 = Long.compare(r2, r0);
        if (r02 == 0) goto L5;
        return r02;
    L5:
        if (r2 == 0) goto L9;
        return 0;
    L9:
        return ((String) Preconditions.checkNotNull(this.zzb)).compareTo((String) Preconditions.checkNotNull(r52.zzb));
    }

    public final boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof zzmv) == true) goto L8;
        return false;
    L8:
        zzmv r82 = (zzmv) r8;
        if (this.zza == r82.zza) goto L11;
    L13:
        return false;
    L11:
        if (Objects.equals(this.zzb, r82.zzb) == false) goto L13;
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Long.valueOf(this.zza), this.zzb});
    }

    public final String toString() {
        String r0 = zza();
        String r4 = String.valueOf(zzb());
        int r1 = String.valueOf(r0).length();
        int r12 = r1 + 1;
        StringBuilder r3 = new StringBuilder(r12 + r4.length());
        r3.append(r0);
        r3.append(":");
        r3.append(r4);
        return r3.toString();
    }

    public final String zza() {
        String r0 = this.zzb;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return Long.toString(this.zza);
    }

    public final Object zzb() {
        int r0 = this.zzc;
        if (r0 == 0) goto L39;
        if (r0 == 1) goto L37;
        if (r0 == 2) goto L35;
        if (r0 == 3) goto L33;
        if (r0 != 4) goto L13;
        Object r2 = this.zze;
        Preconditions.checkNotNull(r2);
        return r2;
    L13:
        if (r0 != 5) goto L28;
        Object r02 = this.zze;
        Preconditions.checkNotNull(r02);
    L19:
        th = move-exception;
        RuntimeException r22 = this.zzf;
        if (r22 == null) goto L27;
        th.addSuppressed(r22);
    L27:
        throw th;
    L16:
        if ((r02 instanceof byte[]) == false) goto L22;
        return (byte[]) r02;
    L22:
        return ((zzacr) r02).zzm();
    L28:
        Buffer$$ExternalSyntheticBUOutline2.m("Impossible, this was validated when parsed or created");
        return null;
    L33:
        return Double.valueOf(Double.longBitsToDouble(this.zzd));
    L35:
        return Long.valueOf(this.zzd);
    L37:
        return Boolean.TRUE;
    L39:
        return Boolean.FALSE;
    }
}
