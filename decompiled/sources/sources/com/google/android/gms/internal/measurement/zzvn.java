package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
abstract class zzvn implements zzws {
    private final zzws zza;
    private final UUID zzb;
    private final String zzc;
    private final String zzd;
    private Thread zze;

    public zzvn(String r1, zzws r2, zzwq r3) {
        this.zzd = (String) Preconditions.checkNotNull(r1);
        this.zza = r2;
        this.zzb = r2.zzc();
        this.zzc = r2.zzd();
        r3.getClass();
        this.zze = Thread.currentThread();
    }

    public zzvn(String r1, UUID r2, String r3, zzwq r4) {
        this.zzd = (String) Preconditions.checkNotNull(r1);
        this.zza = null;
        this.zzb = r2;
        this.zzc = r3;
        r4.getClass();
        this.zze = Thread.currentThread();
    }

    public static String zzcL(UUID r2) {
        return "tk-trace-id: ".concat(String.valueOf(Long.toString(r2.getLeastSignificantBits() >>> 1, 36)));
    }

    @Override // com.google.android.gms.internal.measurement.zzwt, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        zzrg r0 = zzvy.zza;
        Preconditions.checkNotNull(this);
        zzwq r02 = zzvy.zzd();
        zzws r1 = r02.zzb;
        if (r1 == null) goto L9;
        if (this != r1) goto L7;
        zzvy.zzc(r02, r1.zzb());
        this.zze = null;
        return;
    L7:
        String r4 = zze();
        String r03 = r1.zze();
        int r2 = String.valueOf(r4).length();
        int r22 = r2 + 79;
        StringBuilder r3 = new StringBuilder((r22 + String.valueOf(r03).length()) + 1);
        r3.append("Tried to end span ");
        r3.append(r4);
        r3.append(", but that span is not the current span. The current span is ");
        r3.append(r03);
        r3.append(".");
        throw new zzvw(r3.toString());
    L9:
        String r42 = zze();
        StringBuilder r23 = new StringBuilder(String.valueOf(r42).length() + 101);
        r23.append("Tried to end [");
        r23.append(r42);
        r23.append("], but no trace was active. This is caused by mismatched or missing calls to beginSpan.");
        throw new zzvv(r23.toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [com.google.android.gms.internal.measurement.zzvn] */
    /* JADX WARN: Type inference failed for: r11v1, types: [com.google.android.gms.internal.measurement.zzws] */
    /* JADX WARN: Type inference failed for: r11v3, types: [com.google.android.gms.internal.measurement.zzws] */
    public final String toString() {
        zzrg r0 = zzvy.zza;
        zzws r1 = this;
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r1 == null) goto L8;
        r2 = r2 + 1;
        r3 = r3 + r1.zze().length();
        r1 = r1.zzb();
        if (r1 == null) goto L3;
        r3 = r3 + 4;
        goto L3
    L8:
        if (r2 <= 250) goto L41;
        int r12 = r2 - 1;
        String[] r5 = new String[r2];
        zzws r6 = this;
    L10:
        if (r12 < 0) goto L12;
        r5[r12] = r6.zze();
        r6 = r6.zzb();
        r12 = r12 - 1;
        goto L10
    L12:
        ImmutableMap.Builder r13 = ImmutableMap.builder();
        UnmodifiableIterator r62 = ImmutableSet.copyOf(r5).iterator();
        int r7 = 0;
    L14:
        if (r62.hasNext() == false) goto L16;
        r13.put(r62.next(), Integer.valueOf(r7));
        r7 = r7 + 1;
        goto L14
    L16:
        ImmutableMap r14 = r13.buildOrThrow();
        int r72 = r2 >> 2;
        zzwo r8 = null;
        if (r14.size() > r72) goto L26;
        int[] r63 = new int[r2 + 1];
        int r9 = 0;
    L20:
        if (r9 >= r2) goto L22;
        r63[r9] = ((Integer) r14.get(r5[r9])).intValue();
        r9 = r9 + 1;
        goto L20
    L22:
        r63[r2] = r14.size();
        zzwo r15 = zzwp.zza(r63).zzd();
        if ((r15.zzc * (r15.zzb - r15.zza)) < r72) goto L26;
        r8 = r15;
    L26:
        String r16 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r8 == null) goto L38;
        int r64 = r8.zza;
        if (r64 <= 0) goto L32;
        String r73 = String.valueOf(TextUtils.join(" -> ", Arrays.copyOf(r5, r64))).concat(" -> ");
    L33:
        int r92 = r8.zzb;
        int r82 = r8.zzc;
        int r10 = ((r92 - r64) * r82) + r64;
        if (r10 >= r2) goto L36;
        r16 = " -> ".concat(String.valueOf(TextUtils.join(" -> ", Arrays.copyOfRange(r5, r10, r2))));
    L36:
        r16 = String.format(Locale.US, "%s{%s}x%d%s", new Object[]{r73, TextUtils.join(" -> ", Arrays.copyOfRange(r5, r64, r92)), Integer.valueOf(r82), r16});
        goto L38
    L32:
        r73 = _UrlKt.FRAGMENT_ENCODE_SET;
    L38:
        if (r16.isEmpty() == true) goto L41;
        return r16;
    L41:
        char[] r17 = new char[r3];
    L42:
        if (this == 0) goto L47;
        String r22 = this.zze();
        r3 = r3 - r22.length();
        r22.getChars(0, r22.length(), r17, r3);
        zzvn this = this.zzb();
        if (this == 0) goto L42;
        r3 = r3 - 4;
        " -> ".getChars(0, 4, r17, r3);
        goto L42
    L47:
        return new String(r17);
    }

    @Override // com.google.android.gms.internal.measurement.zzws
    public final Thread zza() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.measurement.zzws
    public final zzws zzb() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzws
    public final UUID zzc() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzws
    public final String zzd() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzws
    public final String zze() {
        return this.zzd;
    }
}
