package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.vision.zzht$$ExternalSyntheticBUOutline0;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public abstract class zzbb implements Serializable, Iterable<Byte> {
    public static final zzbb zzfi = null;
    private static final zzbf zzfj = null;
    private int zzfk;

    static {
        zzfi = new zzbi(zzci.zzkt);
        if (zzaw.zzx() == false) goto L5;
        zzbf r0 = new zzbj(null);
    L6:
        zzfj = r0;
        return;
    L5:
        r0 = new zzbd(null);
        goto L6
    }

    public zzbb() {
        this.zzfk = 0;
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

    public static zzbb zzb(byte[] r2, int r3, int r4) {
        return new zzbi(zzfj.zzc(r2, r3, r4));
    }

    public static zzbb zzf(String r2) {
        return new zzbi(r2.getBytes(zzci.UTF_8));
    }

    public static zzbg zzk(int r2) {
        return new zzbg(r2, null);
    }

    public abstract boolean equals(Object r1);

    public final int hashCode() {
        int r0 = this.zzfk;
        if (r0 != 0) goto L8;
        int r02 = size();
        r0 = zza(r02, 0, r02);
        if (r0 != 0) goto L7;
        r0 = 1;
    L7:
        this.zzfk = r0;
    L8:
        return r0;
    }

    @Override // java.lang.Iterable
    public /* synthetic */ Iterator<Byte> iterator() {
        return new zzbc(this);
    }

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size())});
    }

    public abstract int zza(int r1, int r2, int r3);

    public abstract zzbb zza(int r1, int r2);

    public abstract String zza(Charset r1);

    public abstract void zza(zzba r1);

    public abstract boolean zzaa();

    public final int zzab() {
        return this.zzfk;
    }

    public abstract byte zzj(int r1);

    public final String zzz() {
        Charset r0 = zzci.UTF_8;
        if (size() != 0) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return zza(r0);
    }
}
