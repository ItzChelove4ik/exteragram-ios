package com.google.android.gms.internal.cast;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import org.telegram.tgnet.TLObject;

/* loaded from: classes4.dex */
public abstract class zzhz extends zzhr implements Set {
    private transient zzhv zza;

    public zzhz() {
    }

    public static zzhz zzh() {
        return zzii.zza;
    }

    public static int zzi(int r5) {
        int r52 = Math.max(r5, 2);
        if (r52 >= 751619276) goto L10;
        int r0 = Integer.highestOneBit(r52 - 1);
    L5:
        r0 = r0 + r0;
        if ((r0 * 0.7d) < r52) goto L5;
        return r0;
    L10:
        if (r52 >= 1073741824) goto L12;
        return TLObject.FLAG_30;
    L12:
        f$$ExternalSyntheticBUOutline1.m("collection too large");
        return 0;
    }

    public static zzhz zzj(Collection r1) {
        Object[] r12 = r1.toArray();
        return zzm(r12.length, r12);
    }

    private static zzhz zzm(int r13, Object... r14) {
        if (r13 == 0) goto L31;
        if (r13 == 1) goto L28;
        int r2 = zzi(r13);
        Object[] r6 = new Object[r2];
        int r7 = r2 - 1;
        int r3 = 0;
        int r5 = 0;
        int r8 = 0;
    L6:
        if (r3 >= r13) goto L15;
        Object r4 = r14[r3];
        zzib.zzb(r4, r3);
        int r9 = r4.hashCode();
        int r10 = zzho.zza(r9);
    L8:
        int r11 = r10 & r7;
        Object r12 = r6[r11];
        if (r12 == null) goto L10;
        if (r12.equals(r4) == true) goto L14;
        r10 = r10 + 1;
    L14:
        r3 = r3 + 1;
        goto L6
    L10:
        r14[r8] = r4;
        r6[r11] = r4;
        r5 = r5 + r9;
        r8 = r8 + 1;
        goto L14
    L15:
        Arrays.fill(r14, r8, r13, null);
        if (r8 != 1) goto L20;
        Object r132 = r14[0];
        Objects.requireNonNull(r132);
        return new zzik(r132);
    L20:
        if (zzi(r8) < (r2 / 2)) goto L22;
        int r133 = r14.length;
        if (r8 >= ((r133 >> 1) + (r133 >> 2))) goto L27;
        r14 = Arrays.copyOf(r14, r8);
    L27:
        return new zzii(r14, r5, r6, r7, r8);
    L22:
        return zzm(r8, r14);
    L28:
        Object r134 = r14[0];
        Objects.requireNonNull(r134);
        return new zzik(r134);
    L31:
        return zzii.zza;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzhz) == true) goto L8;
    L15:
        if (r5 != this) goto L18;
        return true;
    L18:
        if ((r5 instanceof Set) == false) goto L26;
        Set r52 = (Set) r5;
        if (size() != r52.size()) goto L26;
        if (containsAll(r52) == true) goto L25;
        return false;
    L25:
        return true;
    L26:
        return false;
    L8:
        if (zzk() == false) goto L15;
        if (((zzhz) r5).zzk() == false) goto L15;
        if (hashCode() == r5.hashCode()) goto L15;
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return zzij.zza(this);
    }

    @Override // com.google.android.gms.internal.cast.zzhr
    public zzhv zze() {
        zzhv r0 = this.zza;
        if (r0 != null) goto L6;
        zzhv r02 = zzl();
        this.zza = r02;
        return r02;
    L6:
        return r0;
    }

    public boolean zzk() {
        return false;
    }

    public zzhv zzl() {
        Object[] r1 = toArray();
        int r0 = zzhv.$r8$clinit;
        return zzhv.zzk(r1, r1.length);
    }
}
