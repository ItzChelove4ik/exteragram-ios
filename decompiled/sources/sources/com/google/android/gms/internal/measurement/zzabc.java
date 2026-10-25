package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
final class zzabc extends AbstractMap {
    private static final Comparator zza = null;
    private final Object[] zzb;
    private final int[] zzc;
    private final Set zzd;
    private Integer zze;
    private String zzf;

    static {
        zza = new zzaaz();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.internal.measurement.zzabc, java.util.AbstractMap] */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.gms.internal.measurement.zzabc] */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v8 */
    public zzabc(zzabc r21, zzabc r22) {
        ?? r0 = new AbstractMap();
        r0.zzd = new zzabb(r0, -1);
        r0.zze = null;
        r0.zzf = null;
        int r1 = r21.size() + r22.size();
        int r9 = r21.zzc[r21.size()] + r22.zzc[r22.size()];
        int r10 = r1 + 1;
        Object[] r4 = new Object[r9];
        int[] r5 = new int[r10];
        int r11 = 0;
        r5[0] = r1;
        Map.Entry r2 = r21.zzg(0);
        Map.Entry r12 = r22.zzg(0);
        int r13 = 0;
        int r14 = 0;
        int r3 = r1;
        Map.Entry r15 = r2;
        int r23 = 0;
    L3:
        int r152 = 1;
        if (r15 != null) goto L6;
        if (r12 != null) goto L6;
        int r16 = r5[r11];
        int r32 = r16 - r23;
        if (r32 == 0) goto L18;
        int r6 = r11;
    L11:
        if (r6 > r23) goto L13;
        r5[r6] = r5[r6] - r32;
        r6 = r6 + 1;
        goto L11
    L13:
        int r33 = r5[r23];
        int r62 = r33 - r23;
        if (zze(r9, r33) == false) goto L16;
        Object[] r34 = new Object[r33];
        System.arraycopy(r4, r11, r34, r11, r23);
    L17:
        System.arraycopy(r4, r16, r34, r23, r62);
        r4 = r34;
        goto L18
    L16:
        r34 = r4;
    L18:
        r0.zzb = r4;
        int r17 = r5[r11] + 1;
        if (zze(r10, r17) == false) goto L21;
        r5 = Arrays.copyOf(r5, r17);
    L21:
        r0.zzc = r5;
        return;
    L6:
        int r162 = r23 + 1;
        if (r15 == null) goto L24;
        if (r12 == null) goto L53;
        int r8 = ((String) r15.getKey()).compareTo((String) r12.getKey());
        if (r8 != 0) goto L52;
        int r112 = r13 + 1;
        int r82 = r14 + 1;
        r4[r23] = r0.zzf((String) r15.getKey(), r23);
        zzabb r18 = (zzabb) r15.getValue();
        zzabb r24 = (zzabb) r12.getValue();
        int r122 = 0;
        int r132 = 0;
        r0 = r0;
    L31:
        if (r122 < (r18.zzc() - r18.zzb())) goto L37;
        if (r132 < (r24.zzc() - r24.zzb())) goto L37;
        r5[r162] = r3;
        r15 = r21.zzg(r82);
        r12 = r22.zzg(r112);
        r14 = r82;
        r13 = r112;
        r23 = r162;
        r11 = 0;
    L37:
        if (r122 != (r18.zzc() - r18.zzb())) goto L40;
        int r142 = r152;
    L43:
        if (r142 != 0) goto L45;
        int r143 = zzabe.$r8$clinit;
        r142 = zzabe.zze().compare(r18.zza(r122), r24.zza(r132));
    L45:
        if (r142 >= 0) goto L47;
        int r02 = r122 + 1;
        Object r123 = r18.zza(r122);
    L51:
        r4[r3] = r123;
        r122 = r02;
        r3 = r3 + 1;
        r152 = 1;
        r0 = this;
        goto L31
    L47:
        int r03 = r132 + 1;
        Object r133 = r24.zza(r132);
        if (r142 != 0) goto L50;
        r122 = r122 + 1;
    L50:
        r132 = r03;
        r02 = r122;
        r123 = r133;
        goto L51
    L40:
        if (r132 != (r24.zzc() - r24.zzb())) goto L42;
        r142 = -1;
        goto L43
    L42:
        r142 = 0;
        goto L43
    L52:
        if (r8 >= 0) goto L24;
    L53:
        r14 = r14 + 1;
        r3 = zzd(r15, r23, r3, r4, r5);
        r15 = r21.zzg(r14);
    L55:
        r23 = r162;
        r11 = 0;
        r0 = this;
    L24:
        Map.Entry r83 = r15;
        r13 = r13 + 1;
        int r19 = zzd(r12, r23, r3, r4, r5);
        r12 = r22.zzg(r13);
        r3 = r19;
        r15 = r83;
        goto L55
    }

    public zzabc(List r5) {
        this.zzd = new zzabb(this, -1);
        this.zze = null;
        this.zzf = null;
        Iterator r1 = r5.iterator();
        if (r1.hasNext() == true) goto L13;
        int r12 = r5.size();
        Object[] r2 = new Object[r12];
        Iterator r52 = r5.iterator();
        if (r52.hasNext() == true) goto L11;
        int[] r0 = {0};
        if (zze(r12, 0) == false) goto L9;
        r2 = Arrays.copyOf(r2, 0);
    L9:
        this.zzb = r2;
        this.zzc = r0;
        return;
    L11:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r52.next());
        throw null;
    L13:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());
        throw null;
    }

    public static /* synthetic */ Comparator zza() {
        return zza;
    }

    private final int zzd(Map.Entry r4, int r5, int r6, Object[] r7, int[] r8) {
        zzabb r0 = (zzabb) r4.getValue();
        int r1 = r0.zzc() - r0.zzb();
        System.arraycopy(r0.zzb.zzb, r0.zzb(), r7, r6, r1);
        r7[r5] = zzf((String) r4.getKey(), r5);
        int r62 = r6 + r1;
        r8[r5 + 1] = r62;
        return r62;
    }

    private static boolean zze(int r1, int r2) {
        if (r1 > 16) goto L5;
        return false;
    L5:
        if ((r1 * 9) <= (r2 * 10)) goto L10;
        return true;
    L10:
        return false;
    }

    private final Map.Entry zzf(String r3, int r4) {
        return new AbstractMap.SimpleImmutableEntry(r3, new zzabb(this, r4));
    }

    private final Map.Entry zzg(int r3) {
        if (r3 < this.zzc[0]) goto L5;
        return null;
    L5:
        return (Map.Entry) this.zzb[r3];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        return this.zzd;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        if (this.zze != null) goto L6;
        this.zze = Integer.valueOf(super.hashCode());
    L6:
        return this.zze.intValue();
    }

    @Override // java.util.AbstractMap
    public final String toString() {
        if (this.zzf != null) goto L6;
        this.zzf = super.toString();
    L6:
        return this.zzf;
    }

    public final /* synthetic */ Object[] zzb() {
        return this.zzb;
    }

    public final /* synthetic */ int[] zzc() {
        return this.zzc;
    }
}
