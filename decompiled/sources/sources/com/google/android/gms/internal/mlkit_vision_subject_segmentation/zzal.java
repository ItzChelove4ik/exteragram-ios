package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.telegram.tgnet.TLObject;

/* loaded from: classes4.dex */
final class zzal extends AbstractMap implements Serializable {
    private static final Object zzd = null;
    transient int[] zza;
    transient Object[] zzb;
    transient Object[] zzc;
    private transient Object zze;
    private transient int zzf;
    private transient int zzg;
    private transient Set zzh;
    private transient Set zzi;
    private transient Collection zzj;

    static {
        zzd = new Object();
    }

    public zzal(int r1) {
        zzm(12);
    }

    public static /* bridge */ /* synthetic */ int zza(zzal r0) {
        return r0.zzf;
    }

    public static /* bridge */ /* synthetic */ int zzb(zzal r0) {
        return r0.zzg;
    }

    public static /* bridge */ /* synthetic */ int zzc(zzal r0) {
        return r0.zzp();
    }

    public static /* bridge */ /* synthetic */ int zzd(zzal r0, Object r1) {
        return r0.zzq(r1);
    }

    public static /* bridge */ /* synthetic */ Object zzg(zzal r0, Object r1) {
        return r0.zzs(r1);
    }

    public static /* synthetic */ Object zzh(zzal r0) {
        Object r02 = r0.zze;
        r02.getClass();
        return r02;
    }

    public static /* bridge */ /* synthetic */ Object zzi() {
        return zzd;
    }

    public static /* bridge */ /* synthetic */ void zzk(zzal r0, int r1) {
        r0.zzg = r1;
    }

    private final int zzp() {
        return (1 << (this.zzf & 31)) - 1;
    }

    private final int zzq(Object r8) {
        if (zzo() == false) goto L5;
        return -1;
    L5:
        int r0 = zzan.zza(r8);
        int r2 = zzp();
        Object r3 = this.zze;
        r3.getClass();
        int r32 = zzam.zzc(r3, r0 & r2);
        if (r32 == 0) goto L16;
        int r4 = ~r2;
        int r02 = r0 & r4;
    L8:
        int r33 = r32 - 1;
        int[] r5 = this.zza;
        r5.getClass();
        int r52 = r5[r33];
        if ((r52 & r4) != r02) goto L14;
        Object[] r6 = this.zzb;
        r6.getClass();
        if (zze.zza(r8, r6[r33]) == false) goto L14;
        return r33;
    L14:
        r32 = r52 & r2;
        if (r32 != 0) goto L8;
    L16:
        return -1;
    }

    private final int zzr(int r9, int r10, int r11, int r12) {
        int r0 = r10 - 1;
        Object r102 = zzam.zzd(r10);
        if (r12 == 0) goto L5;
        zzam.zze(r102, r11 & r0, r12 + 1);
    L5:
        Object r112 = this.zze;
        r112.getClass();
        int[] r122 = this.zza;
        r122.getClass();
        int r1 = 0;
    L6:
        if (r1 > r9) goto L11;
        int r2 = zzam.zzc(r112, r1);
    L8:
        if (r2 == 0) goto L10;
        int r3 = r2 - 1;
        int r4 = r122[r3];
        int r5 = ((~r9) & r4) | r1;
        int r6 = r5 & r0;
        int r7 = zzam.zzc(r102, r6);
        zzam.zze(r102, r6, r2);
        r122[r3] = ((~r0) & r5) | (r7 & r0);
        r2 = r4 & r9;
        goto L8
    L10:
        r1 = r1 + 1;
        goto L6
    L11:
        this.zze = r102;
        zzt(r0);
        return r0;
    }

    private final Object zzs(Object r8) {
        if (zzo() == true) goto L5;
        int r2 = zzp();
        Object r3 = this.zze;
        r3.getClass();
        int[] r4 = this.zza;
        r4.getClass();
        Object[] r5 = this.zzb;
        r5.getClass();
        int r82 = zzam.zzb(r8, null, r2, r3, r4, r5, null);
        if (r82 == (-1)) goto L9;
        Object[] r1 = this.zzc;
        r1.getClass();
        Object r12 = r1[r82];
        zzn(r82, r2);
        this.zzg--;
        zzl();
        return r12;
    L9:
        return zzd;
    L5:
        return zzd;
    }

    private final void zzt(int r2) {
        int r22 = 32 - Integer.numberOfLeadingZeros(r2);
        int r23 = r22 & 31;
        this.zzf = r23 | (this.zzf & (-32));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        if (zzo() == false) goto L5;
        return;
    L5:
        zzl();
        Map r0 = zzj();
        if (r0 != null) goto L16;
        Object[] r02 = this.zzb;
        r02.getClass();
        Arrays.fill(r02, 0, this.zzg, null);
        Object[] r03 = this.zzc;
        r03.getClass();
        Arrays.fill(r03, 0, this.zzg, null);
        Object r04 = this.zze;
        r04.getClass();
        if ((r04 instanceof byte[]) == false) goto L11;
        Arrays.fill((byte[]) r04, (byte) 0);
    L14:
        int[] r05 = this.zza;
        r05.getClass();
        Arrays.fill(r05, 0, this.zzg, 0);
        this.zzg = 0;
        return;
    L11:
        if ((r04 instanceof short[]) == false) goto L13;
        Arrays.fill((short[]) r04, 0);
        goto L14
    L13:
        Arrays.fill((int[]) r04, 0);
        goto L14
    L16:
        this.zzf = zzbw.zza(size(), 3, 1073741823);
        r0.clear();
        this.zze = null;
        this.zzg = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object r2) {
        Map r0 = zzj();
        if (r0 == null) goto L7;
        return r0.containsKey(r2);
    L7:
        if (zzq(r2) != (-1)) goto L10;
        return false;
    L10:
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object r4) {
        Map r0 = zzj();
        if (r0 != null) goto L14;
        int r1 = 0;
    L6:
        if (r1 >= this.zzg) goto L12;
        Object[] r2 = this.zzc;
        r2.getClass();
        if (zze.zza(r4, r2[r1]) == true) goto L9;
        r1 = r1 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    L14:
        return r0.containsValue(r4);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        Set r0 = this.zzi;
        if (r0 != null) goto L6;
        zzaf r02 = new zzaf(this);
        this.zzi = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object r2) {
        Map r0 = zzj();
        if (r0 != null) goto L5;
        int r22 = zzq(r2);
        if (r22 != (-1)) goto L10;
        return null;
    L10:
        Object[] r1 = this.zzc;
        r1.getClass();
        return r1[r22];
    L5:
        return r0.get(r2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean isEmpty() {
        if (size() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        Set r0 = this.zzh;
        if (r0 != null) goto L6;
        zzai r02 = new zzai(this);
        this.zzh = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object r20, Object r21) {
        if (zzo() == false) goto L10;
        zzi.zzd(zzo(), "Arrays already allocated");
        int r3 = this.zzf;
        int r4 = Math.max(r3 + 1, 2);
        int r5 = Integer.highestOneBit(r4);
        if (r4 <= r5) goto L9;
        r5 = r5 + r5;
        if (r5 > 0) goto L9;
        r5 = TLObject.FLAG_30;
    L9:
        int r42 = Math.max(4, r5);
        this.zze = zzam.zzd(r42);
        zzt(r42 - 1);
        this.zza = new int[r3];
        this.zzb = new Object[r3];
        this.zzc = new Object[r3];
    L10:
        Map r32 = zzj();
        if (r32 != null) goto L47;
        int[] r43 = this.zza;
        r43.getClass();
        Object[] r52 = this.zzb;
        r52.getClass();
        Object[] r6 = this.zzc;
        r6.getClass();
        int r7 = this.zzg;
        int r8 = r7 + 1;
        int r9 = zzan.zza(r20);
        int r10 = zzp();
        int r33 = r9 & r10;
        Object r11 = this.zze;
        r11.getClass();
        int r112 = zzam.zzc(r11, r33);
        if (r112 != 0) goto L18;
        if (r8 <= r10) goto L17;
        r10 = zzr(r10, zzam.zza(r10), r9, r7);
    L16:
        int r17 = 1;
    L38:
        int[] r34 = this.zza;
        r34.getClass();
        int r35 = r34.length;
        if (r8 <= r35) goto L43;
        int r113 = r17;
        int r44 = Math.min(1073741823, (Math.max(r113, r35 >>> 1) + r35) | r113);
        if (r44 == r35) goto L43;
        int[] r36 = this.zza;
        r36.getClass();
        this.zza = Arrays.copyOf(r36, r44);
        Object[] r37 = this.zzb;
        r37.getClass();
        this.zzb = Arrays.copyOf(r37, r44);
        Object[] r38 = this.zzc;
        r38.getClass();
        this.zzc = Arrays.copyOf(r38, r44);
    L43:
        int r39 = (~r10) & r9;
        int[] r45 = this.zza;
        r45.getClass();
        r45[r7] = r39;
        Object[] r310 = this.zzb;
        r310.getClass();
        r310[r7] = r20;
        Object[] r1 = this.zzc;
        r1.getClass();
        r1[r7] = r21;
        this.zzg = r8;
        zzl();
        return null;
    L17:
        Object r46 = this.zze;
        r46.getClass();
        zzam.zze(r46, r33, r8);
        goto L16
    L18:
        int r14 = ~r10;
        int r15 = r9 & r14;
        int r311 = 0;
    L19:
        int r114 = r112 - 1;
        int r16 = r43[r114];
        r17 = 1;
        int r13 = r16 & r14;
        if (r13 == r15) goto L22;
    L26:
        int r12 = r16 & r10;
        r311 = r311 + 1;
        if (r12 == 0) goto L29;
        r112 = r12;
        goto L19
    L29:
        if (r311 < 9) goto L35;
        LinkedHashMap r47 = new LinkedHashMap(zzp() + 1, 1.0f);
        int r312 = zze();
    L31:
        if (r312 < 0) goto L33;
        Object[] r53 = this.zzb;
        r53.getClass();
        Object r54 = r53[r312];
        Object[] r62 = this.zzc;
        r62.getClass();
        r47.put(r54, r62[r312]);
        r312 = zzf(r312);
        goto L31
    L33:
        this.zze = r47;
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
        zzl();
        return r47.put(r20, r21);
    L35:
        if (r8 <= r10) goto L37;
        r10 = zzr(r10, zzam.zza(r10), r9, r7);
        goto L38
    L37:
        r43[r114] = (r8 & r10) | r13;
        goto L38
    L22:
        if (zze.zza(r20, r52[r114]) == false) goto L26;
        Object r0 = r6[r114];
        r6[r114] = r21;
        return r0;
    L47:
        return r32.put(r20, r21);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object r2) {
        Map r0 = zzj();
        if (r0 != null) goto L5;
        Object r1 = zzs(r2);
        if (r1 != zzd) goto L10;
        return null;
    L10:
        return r1;
    L5:
        return r0.remove(r2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        Map r0 = zzj();
        if (r0 == null) goto L7;
        return r0.size();
    L7:
        return this.zzg;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        Collection r0 = this.zzj;
        if (r0 != null) goto L6;
        zzak r02 = new zzak(this);
        this.zzj = r02;
        return r02;
    L6:
        return r0;
    }

    public final int zze() {
        if (isEmpty() == false) goto L6;
        return -1;
    L6:
        return 0;
    }

    public final int zzf(int r1) {
        int r12 = r1 + 1;
        if (r12 >= this.zzg) goto L5;
        return r12;
    L5:
        return -1;
    }

    public final Map zzj() {
        Object r1 = this.zze;
        if ((r1 instanceof Map) == true) goto L5;
        return null;
    L5:
        return (Map) r1;
    }

    public final void zzl() {
        this.zzf += 32;
    }

    public final void zzm(int r3) {
        this.zzf = zzbw.zza(12, 1, 1073741823);
    }

    public final void zzn(int r11, int r12) {
        Object r0 = this.zze;
        r0.getClass();
        int[] r1 = this.zza;
        r1.getClass();
        Object[] r2 = this.zzb;
        r2.getClass();
        Object[] r3 = this.zzc;
        r3.getClass();
        int r10 = size();
        int r4 = r10 - 1;
        if (r11 >= r4) goto L13;
        int r7 = r11 + 1;
        Object r8 = r2[r4];
        r2[r11] = r8;
        r3[r11] = r3[r4];
        r2[r4] = null;
        r3[r4] = null;
        r1[r11] = r1[r4];
        r1[r4] = 0;
        int r112 = zzan.zza(r8) & r12;
        int r22 = zzam.zzc(r0, r112);
        if (r22 == r10) goto L11;
    L6:
        int r23 = r22 - 1;
        int r113 = r1[r23];
        int r02 = r113 & r12;
        if (r02 == r10) goto L9;
        r22 = r02;
        goto L6
    L9:
        r1[r23] = ((~r12) & r113) | (r7 & r12);
        return;
    L11:
        zzam.zze(r0, r112, r7);
        return;
    L13:
        r2[r11] = null;
        r3[r11] = null;
        r1[r11] = 0;
    }

    public final boolean zzo() {
        if (this.zze != null) goto L6;
        return true;
    L6:
        return false;
    }
}
