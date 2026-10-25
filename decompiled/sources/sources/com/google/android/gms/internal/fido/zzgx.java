package com.google.android.gms.internal.fido;

import com.android.dex.Dex$$ExternalSyntheticBUOutline0;
import com.google.protobuf.ByteString$$ExternalSyntheticBUOutline2;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes4.dex */
public abstract class zzgx implements Iterable<Byte>, Serializable {
    private static final Comparator zza = null;
    public static final zzgx zzb = null;
    private static final zzgw zzd = null;
    private int zzc;

    static {
        zzb = new zzgu(zzhc.zzd);
        int r0 = zzgn.$r8$clinit;
        zzd = new zzgw(null);
        zza = new zzgp();
    }

    public zzgx() {
        this.zzc = 0;
    }

    public static int zzj(int r3, int r4, int r5) {
        int r1 = r4 - r3;
        if ((((r3 | r4) | r1) | (r5 - r4)) >= 0) goto L11;
        if (r3 < 0) goto L10;
        if (r4 >= r3) goto L9;
        Dex$$ExternalSyntheticBUOutline0.m("Beginning index larger than ending index: ", r3, ", ", r4);
        return 0;
    L9:
        Dex$$ExternalSyntheticBUOutline0.m("End index: ", r4, " >= ", r5);
        return 0;
    L10:
        ByteString$$ExternalSyntheticBUOutline2.m("Beginning index: ", r3, " < 0");
        return 0;
    L11:
        return r1;
    }

    public static zzgx zzl(byte[] r2, int r3, int r4) {
        zzj(0, r4, r2.length);
        byte[] r1 = new byte[r4];
        System.arraycopy(r2, 0, r1, 0, r4);
        return new zzgu(r1);
    }

    public abstract boolean equals(Object r1);

    public final int hashCode() {
        int r0 = this.zzc;
        if (r0 != 0) goto L8;
        int r1 = zzd();
        r0 = zzf(r1, 0, r1);
        if (r0 != 0) goto L7;
        r0 = 1;
    L7:
        this.zzc = r0;
    L8:
        return r0;
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ Iterator<Byte> iterator() {
        return new zzgo(this);
    }

    public final String toString() {
        Locale r0 = Locale.ROOT;
        String r1 = Integer.toHexString(System.identityHashCode(this));
        Integer r2 = Integer.valueOf(zzd());
        if (zzd() > 50) goto L5;
        String r5 = zzhe.zza(this);
    L7:
        return String.format(r0, "<ByteString@%s size=%d contents=\"%s\">", new Object[]{r1, r2, r5});
    L5:
        r5 = zzhe.zza(zzg(0, 47)).concat("...");
        goto L7
    }

    public abstract byte zza(int r1);

    public abstract byte zzb(int r1);

    public abstract int zzd();

    public abstract void zze(byte[] r1, int r2, int r3, int r4);

    public abstract int zzf(int r1, int r2, int r3);

    public abstract zzgx zzg(int r1, int r2);

    public abstract InputStream zzh();

    public abstract ByteBuffer zzi();

    public final int zzk() {
        return this.zzc;
    }

    public final byte[] zzm() {
        int r0 = zzd();
        if (r0 == 0) goto L5;
        byte[] r1 = new byte[r0];
        zze(r1, 0, 0, r0);
        return r1;
    L5:
        return zzhc.zzd;
    }
}
