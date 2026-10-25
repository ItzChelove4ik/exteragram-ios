package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.android.gms.internal.cast.zzxk$$ExternalSyntheticBUOutline0;
import com.google.android.gms.internal.cast.zzxk$$ExternalSyntheticBUOutline1;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes4.dex */
public abstract class zzawb implements Iterable, Serializable {
    public static final zzawb zza = null;
    private int zzb;

    static {
        zza = new zzawa(zzaxi.zza);
        int r0 = zzavq.$r8$clinit;
    }

    public zzawb() {
        this.zzb = 0;
    }

    public static zzawb zzi(byte[] r2, int r3, int r4) {
        int r1 = r3 + r4;     // Catch: zzaxm -> L4
        zzk(r3, r1, r2.length);     // Catch: zzaxm -> L4
        byte[] r0 = new byte[r4];     // Catch: zzaxm -> L4
        System.arraycopy(r2, r3, r0, 0, r4);     // Catch: zzaxm -> L4
        return new zzawa(r0);
    L4:
        e = move-exception;
        throw new AssertionError("Expected no InvalidProtocolBufferException as data UTF8 validity is not checked.", e);
    }

    public static int zzk(int r3, int r4, int r5) {
        int r1 = r4 - r3;
        if ((((r3 | r4) | r1) | (r5 - r4)) >= 0) goto L11;
        if (r3 < 0) goto L10;
        if (r4 >= r3) goto L9;
        int r52 = String.valueOf(r3).length();
        zzxk$$ExternalSyntheticBUOutline0.m((r52 + 44) + String.valueOf(r4).length(), "Beginning index larger than ending index: ", r3, ", ", r4);
        return 0;
    L9:
        int r32 = String.valueOf(r4).length();
        zzxk$$ExternalSyntheticBUOutline0.m((r32 + 15) + String.valueOf(r5).length(), "End index: ", r4, " >= ", r5);
        return 0;
    L10:
        zzxk$$ExternalSyntheticBUOutline1.m(String.valueOf(r3).length() + 21, r3);
        return 0;
    L11:
        return r1;
    }

    public static /* synthetic */ boolean zzl(byte[] r2, int r3, byte[] r4, int r5, int r6) {
        int r0 = r3 + r6;
        zzk(r3, r0, r2.length);
        zzk(r5, r6 + r5, r4.length);
    L3:
        if (r3 >= r0) goto L9;
        if (r2[r3] != r4[r5]) goto L6;
        r3 = r3 + 1;
        r5 = r5 + 1;
        goto L3
    L6:
        return false;
    L9:
        return true;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzawb) == true) goto L8;
        return false;
    L8:
        zzawb r52 = (zzawb) r5;
        int r1 = zzb();
        if (r1 == r52.zzb()) goto L11;
        return false;
    L11:
        if (r1 == 0) goto L19;
        int r0 = this.zzb;
        int r12 = r52.zzb;
        if (r0 == 0) goto L18;
        if (r12 == 0) goto L18;
        if (r0 == r12) goto L18;
        return false;
    L18:
        return zze(r52);
    L19:
        return true;
    }

    public final int hashCode() {
        int r0 = this.zzb;
        if (r0 != 0) goto L8;
        int r1 = zzb();
        r0 = zzf(r1, 0, r1);
        if (r0 != 0) goto L7;
        r0 = 1;
    L7:
        this.zzb = r0;
    L8:
        return r0;
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return new zzavv(this);
    }

    public final String toString() {
        Locale r0 = Locale.ROOT;
        String r1 = Integer.toHexString(System.identityHashCode(this));
        Integer r2 = Integer.valueOf(zzb());
        if (zzb() > 50) goto L5;
        String r5 = zzayo.zza(zzj());
    L7:
        return String.format(r0, "<ByteString@%s size=%d contents=\"%s\">", new Object[]{r1, r2, r5});
    L5:
        r5 = zzayo.zza(zzc(0, 47).zzj()).concat("...");
        goto L7
    }

    public abstract byte zza(int r1);

    public abstract int zzb();

    public abstract zzawb zzc(int r1, int r2);

    public abstract void zzd(byte[] r1, int r2, int r3, int r4);

    public abstract boolean zze(zzawb r1);

    public abstract int zzf(int r1, int r2, int r3);

    public final byte[] zzj() {
        int r0 = zzb();
        if (r0 == 0) goto L5;
        byte[] r1 = new byte[r0];
        zzd(r1, 0, 0, r0);
        return r1;
    L5:
        return zzaxi.zza;
    }
}
