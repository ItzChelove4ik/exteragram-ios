package com.google.android.recaptcha.internal;

import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
final class zzahm extends zzaer {
    static final int[] zzb = null;
    private final int zzc;
    private final zzaer zzd;
    private final zzaer zze;
    private final int zzf;
    private final int zzg;

    static {
        zzb = new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, Integer.MAX_VALUE};
    }

    private zzahm(zzaer r3, zzaer r4) {
        this.zzd = r3;
        this.zze = r4;
        int r0 = r3.zzb();
        this.zzf = r0;
        this.zzc = r0 + r4.zzb();
        this.zzg = Math.max(r3.zzo(), r4.zzo()) + 1;
    }

    public /* synthetic */ zzahm(zzaer r1, zzaer r2, byte[] r3) {
        this(r1, r2);
    }

    private static zzaer zzE(zzaer r4, zzaer r5) {
        int r0 = r4.zzb();
        int r1 = r5.zzb();
        byte[] r2 = new byte[r0 + r1];
        r4.zzx(r2, 0, 0, r0);
        r5.zzx(r2, 0, r0, r1);
        return zzaer.zzu(r2);
    }

    public static zzaer zzj(zzaer r6, zzaer r7) {
        if (r7.zzb() != 0) goto L6;
        return r6;
    L6:
        if (r6.zzb() != 0) goto L8;
        return r7;
    L8:
        int r0 = r6.zzb() + r7.zzb();
        if (r0 >= 128) goto L13;
        return zzE(r6, r7);
    L13:
        if ((r6 instanceof zzahm) == false) goto L25;
        zzahm r2 = (zzahm) r6;
        zzaer r3 = r2.zze;
        if ((r3.zzb() + r7.zzb()) >= 128) goto L18;
        zzaer r62 = zzE(r3, r7);
        return new zzahm(r2.zzd, r62);
    L18:
        zzaer r1 = r2.zzd;
        if (r1.zzo() <= r3.zzo()) goto L25;
        if (r2.zzg <= r7.zzo()) goto L25;
        return new zzahm(r1, new zzahm(r3, r7));
    L25:
        if (r0 < zzm(Math.max(r6.zzo(), r7.zzo()) + 1)) goto L29;
        return new zzahm(r6, r7);
    L29:
        return zzahk.zza(r6, r7, new ArrayDeque());
    }

    public static int zzm(int r2) {
        int[] r0 = zzb;
        int r1 = r0.length;
        if (r2 < 47) goto L7;
        return Integer.MAX_VALUE;
    L7:
        return r0[r2];
    }

    @Override // com.google.android.recaptcha.internal.zzaer, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return new zzahj(this);
    }

    public final /* synthetic */ zzaer zzD() {
        return this.zze;
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final byte zza(int r2) {
        int r0 = this.zzf;
        if (r2 >= r0) goto L7;
        return this.zzd.zza(r2);
    L7:
        return this.zze.zza(r2 - r0);
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final int zzb() {
        return this.zzc;
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final zzaer zzc(int r1, int r2) {
        return zzd(r1, r2);
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final zzaer zzd(int r3, int r4) {
        int r0 = this.zzc;
        int r1 = zzaer.zzB(r3, r4, r0);
        if (r1 == 0) goto L5;
        if (r1 != r0) goto L8;
        return this;
    L8:
        int r02 = this.zzf;
        if (r4 <= r02) goto L11;
        int r42 = r4 - r02;
        if (r3 >= r02) goto L15;
        zzaer r03 = this.zzd;
        return new zzahm(r03.zzc(r3, r03.zzb()), this.zze.zzc(0, r42));
    L15:
        return this.zze.zzc(r3 - r02, r42);
    L11:
        return this.zzd.zzc(r3, r4);
    L5:
        return zzaer.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final void zze(byte[] r3, int r4, int r5, int r6) {
        int r0 = r4 + r6;
        int r1 = this.zzf;
        if (r0 > r1) goto L6;
        this.zzd.zze(r3, r4, r5, r6);
        return;
    L6:
        if (r4 < r1) goto L9;
        this.zze.zze(r3, r4 - r1, r5, r6);
        return;
    L9:
        int r12 = r1 - r4;
        this.zzd.zze(r3, r4, r5, r12);
        this.zze.zze(r3, 0, r5 + r12, r6 - r12);
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final void zzg(zzaej r2) {
        this.zzd.zzg(r2);
        this.zze.zzg(r2);
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final String zzh(Charset r2) {
        return new String(zzy(), r2);
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final boolean zzi(zzaer r12) {
        zzahl r0 = new zzahl(this, null);
        zzaeo r2 = r0.zza();
        zzahl r3 = new zzahl(r12, null);
        zzaeo r122 = r3.zza();
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
    L3:
        int r7 = r2.zzb() - r4;
        int r8 = r122.zzb() - r5;
        int r9 = Math.min(r7, r8);
        if (r4 != 0) goto L6;
        boolean r10 = r2.zzj(r122, r5, r9);
    L7:
        if (r10 == false) goto L8;
        r6 = r6 + r9;
        int r102 = this.zzc;
        if (r6 >= r102) goto L11;
        if (r9 != r7) goto L18;
        r4 = 0;
        r2 = r0.zza();
    L19:
        if (r9 == r8) goto L20;
        r5 = r5 + r9;
        goto L3
    L20:
        r122 = r3.zza();
        r5 = 0;
        goto L3
    L18:
        r4 = r4 + r9;
        r2 = r2;
        goto L19
    L11:
        if (r6 != r102) goto L14;
        return true;
    L14:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return false;
    L8:
        return false;
    L6:
        r10 = r122.zzj(r2, r4, r9);
        goto L7
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final int zzk(int r3, int r4, int r5) {
        int r0 = r4 + r5;
        int r1 = this.zzf;
        if (r0 <= r1) goto L5;
        if (r4 >= r1) goto L8;
        int r12 = r1 - r4;
        int r32 = this.zzd.zzk(r3, r4, r12);
        return this.zze.zzk(r32, 0, r5 - r12);
    L8:
        return this.zze.zzk(r3, r4 - r1, r5);
    L5:
        return this.zzd.zzk(r3, r4, r5);
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final zzaev zzl() {
        ArrayList r0 = new ArrayList();
        zzahl r1 = new zzahl(this, null);
    L4:
        if (r1.hasNext() == false) goto L6;
        r0.add(r1.zza().zzf());
        goto L4
    L6:
        int r3 = zzaev.$r8$clinit;
        return new zzaeu(new zzagl(r0), 4096, null);
    }

    public final /* synthetic */ zzaer zzn() {
        return this.zzd;
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final int zzo() {
        return this.zzg;
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final boolean zzp() {
        int r0 = this.zzg;
        if (this.zzc < zzm(r0)) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final zzaen zzq() {
        return new zzahj(this);
    }
}
