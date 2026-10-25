package com.google.android.gms.internal.vision;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public abstract class zzht implements Serializable, Iterable<Byte> {
    public static final zzht zza = null;
    private static final zzhz zzb = null;
    private static final Comparator<zzht> zzd = null;
    private int zzc;

    static {
        zza = new zzid(zzjf.zzb);
        if (zzhi.zza() == false) goto L5;
        zzhz r0 = new zzic(null);
    L6:
        zzb = r0;
        zzd = new zzhv();
        return;
    L5:
        r0 = new zzhx(null);
        goto L6
    }

    public zzht() {
        this.zzc = 0;
    }

    public static /* synthetic */ int zza(byte r0) {
        return zzb(r0);
    }

    public static zzht zza(String r2) {
        return new zzid(r2.getBytes(zzjf.zza));
    }

    public static zzht zza(byte[] r2, int r3, int r4) {
        zzb(r3, r3 + r4, r2.length);
        return new zzid(zzb.zza(r2, r3, r4));
    }

    private static int zzb(byte r0) {
        return r0 & UByte.MAX_VALUE;
    }

    public static int zzb(int r3, int r4, int r5) {
        int r0 = r4 - r3;
        if ((((r3 | r4) | r0) | (r5 - r4)) >= 0) goto L13;
        if (r3 >= 0) goto L6;
        StringBuilder r52 = new StringBuilder(32);
        r52.append("Beginning index: ");
        r52.append(r3);
        r52.append(" < 0");
        throw new IndexOutOfBoundsException(r52.toString());
    L6:
        if (r4 >= r3) goto L9;
        zzht$$ExternalSyntheticBUOutline0.m(66, "Beginning index larger than ending index: ", r3, ", ", r4);
        return 0;
    L9:
        zzht$$ExternalSyntheticBUOutline0.m(37, "End index: ", r4, " >= ", r5);
        return 0;
    L13:
        return r0;
    }

    public static zzib zzc(int r2) {
        return new zzib(r2, null);
    }

    public abstract boolean equals(Object r1);

    public final int hashCode() {
        int r0 = this.zzc;
        if (r0 != 0) goto L8;
        int r02 = zza();
        r0 = zza(r02, 0, r02);
        if (r0 != 0) goto L7;
        r0 = 1;
    L7:
        this.zzc = r0;
    L8:
        return r0;
    }

    @Override // java.lang.Iterable
    public /* synthetic */ Iterator<Byte> iterator() {
        return new zzhs(this);
    }

    public final String toString() {
        Locale r0 = Locale.ROOT;
        String r1 = Integer.toHexString(System.identityHashCode(this));
        Integer r2 = Integer.valueOf(zza());
        if (zza() > 50) goto L5;
        String r5 = zzlq.zza(this);
    L7:
        return String.format(r0, "<ByteString@%s size=%d contents=\"%s\">", new Object[]{r1, r2, r5});
    L5:
        r5 = String.valueOf(zzlq.zza(zza(0, 47))).concat("...");
        goto L7
    }

    public abstract byte zza(int r1);

    public abstract int zza();

    public abstract int zza(int r1, int r2, int r3);

    public abstract zzht zza(int r1, int r2);

    public abstract String zza(Charset r1);

    public abstract void zza(zzhq r1);

    public abstract byte zzb(int r1);

    public final String zzb() {
        Charset r0 = zzjf.zza;
        if (zza() != 0) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return zza(r0);
    }

    public abstract boolean zzc();

    public final int zzd() {
        return this.zzc;
    }
}
