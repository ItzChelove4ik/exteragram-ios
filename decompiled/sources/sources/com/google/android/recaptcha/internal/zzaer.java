package com.google.android.recaptcha.internal;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.internal.cast.zzxg$$ExternalSyntheticBUOutline0;
import com.google.android.gms.internal.cast.zzxk$$ExternalSyntheticBUOutline0;
import com.google.android.gms.internal.cast.zzxk$$ExternalSyntheticBUOutline1;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public abstract class zzaer implements Iterable, Serializable {
    public static final zzaer zza = null;
    private int zzb;

    static {
        zza = new zzaep(zzagf.zza);
        int r0 = zzaef.$r8$clinit;
    }

    public zzaer() {
        this.zzb = 0;
    }

    public static zzaeq zzA() {
        return new zzaeq(128);
    }

    public static int zzB(int r3, int r4, int r5) {
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

    public static /* synthetic */ boolean zzC(byte[] r2, int r3, byte[] r4, int r5, int r6) {
        int r0 = r3 + r6;
        zzB(r3, r0, r2.length);
        zzB(r5, r6 + r5, r4.length);
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

    private static zzaer zzj(Iterator r4, int r5) {
        if (r5 > 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m(String.format(Locale.ROOT, "length (%s) must be >= 1", new Object[]{Integer.valueOf(r5)}));
        return null;
    L5:
        if (r5 == 1) goto L7;
        int r1 = r5 >>> 1;
        zzaer r2 = zzj(r4, r1);
        zzaer r42 = zzj(r4, r5 - r1);
        if ((Integer.MAX_VALUE - r2.zzb()) >= r42.zzb()) goto L11;
        int r52 = r2.zzb();
        int r43 = r42.zzb();
        int r12 = String.valueOf(r52).length();
        zzxg$$ExternalSyntheticBUOutline0.m((r12 + 31) + String.valueOf(r43).length(), "ByteString would be too long: ", r52, "+", r43);
        return null;
    L11:
        return zzahm.zzj(r2, r42);
    L7:
        return (zzaer) r4.next();
    }

    public static zzaer zzs(byte[] r1, int r2, int r3) {
        return zzt(r1, r2, r3, false);
    L5:
        e = move-exception;
        throw new AssertionError("Expected no InvalidProtocolBufferException as data UTF8 validity is not checked.", e);
    }

    public static zzaer zzt(byte[] r1, int r2, int r3, boolean r4) {
        if (r3 == 0) goto L4;
        zzB(r2, r2 + r3, r1.length);
        byte[] r42 = new byte[r3];
        System.arraycopy(r1, r2, r42, 0, r3);
        return new zzaep(r42);
    L4:
        return zza;
    }

    public static zzaer zzu(byte[] r2) {
        return zzv(r2, false);
    L5:
        e = move-exception;
        throw new AssertionError("Expected no InvalidProtocolBufferException as data UTF8 validity is not checked.", e);
    }

    public static zzaer zzv(byte[] r0, boolean r1) {
        if (r0.length != 0) goto L7;
        return zza;
    L7:
        return new zzaep(r0);
    }

    public static zzaer zzw(Iterable r3) {
        if ((r3 instanceof Collection) == true) goto L8;
        Iterator r0 = r3.iterator();
        int r1 = 0;
    L6:
        if (r0.hasNext() == false) goto L9;
        r0.next();
        r1 = r1 + 1;
    L9:
        if (r1 != 0) goto L13;
        return zza;
    L13:
        return zzj(r3.iterator(), r1);
    L8:
        r1 = ((Collection) r3).size();
        goto L9
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzaer) == true) goto L8;
        return false;
    L8:
        zzaer r52 = (zzaer) r5;
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
        return zzi(r52);
    L19:
        return true;
    }

    public final int hashCode() {
        int r0 = this.zzb;
        if (r0 != 0) goto L8;
        int r1 = zzb();
        r0 = zzk(r1, 0, r1);
        if (r0 != 0) goto L7;
        r0 = 1;
    L7:
        this.zzb = r0;
    L8:
        return r0;
    }

    @Override // java.lang.Iterable
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return zzq();
    }

    public final String toString() {
        Locale r0 = Locale.ROOT;
        String r1 = Integer.toHexString(System.identityHashCode(this));
        Integer r2 = Integer.valueOf(zzb());
        if (zzb() > 50) goto L5;
        String r5 = zzahv.zza(zzy());
    L7:
        return String.format(r0, "<ByteString@%s size=%d contents=\"%s\">", new Object[]{r1, r2, r5});
    L5:
        r5 = zzahv.zza(zzd(0, 47).zzy()).concat("...");
        goto L7
    }

    public abstract byte zza(int r1);

    public abstract int zzb();

    public abstract zzaer zzc(int r1, int r2);

    public abstract zzaer zzd(int r1, int r2);

    public abstract void zze(byte[] r1, int r2, int r3, int r4);

    public abstract ByteBuffer zzf();

    public abstract void zzg(zzaej r1);

    public abstract String zzh(Charset r1);

    public abstract boolean zzi(zzaer r1);

    public abstract int zzk(int r1, int r2, int r3);

    public abstract zzaev zzl();

    public abstract int zzo();

    public abstract boolean zzp();

    public zzaen zzq() {
        return new zzaek(this);
    }

    public final boolean zzr() {
        if (zzb() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Deprecated
    public final void zzx(byte[] r3, int r4, int r5, int r6) {
        zzB(0, r6, zzb());
        zzB(r5, r5 + r6, r3.length);
        if (r6 <= 0) goto L6;
        zze(r3, 0, r5, r6);
        return;
    }

    public final byte[] zzy() {
        int r0 = zzb();
        if (r0 == 0) goto L5;
        byte[] r1 = new byte[r0];
        zze(r1, 0, 0, r0);
        return r1;
    L5:
        return zzagf.zza;
    }

    public final String zzz(Charset r2) {
        if (zzr() == false) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return zzh(r2);
    }
}
