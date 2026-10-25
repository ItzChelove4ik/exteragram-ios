package com.google.android.gms.internal.mlkit_vision_label;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.telegram.tgnet.TLObject;

/* loaded from: classes4.dex */
final class zzau extends AbstractMap implements Serializable {
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

    public zzau(int r1) {
        zzo(12);
    }

    private final Object[] zzA() {
        Object[] r0 = this.zzb;
        r0.getClass();
        return r0;
    }

    private final Object[] zzB() {
        Object[] r0 = this.zzc;
        r0.getClass();
        return r0;
    }

    public static /* synthetic */ int zza(zzau r0) {
        return r0.zzf;
    }

    public static /* synthetic */ int zzb(zzau r2) {
        int r0 = r2.zzg;
        r2.zzg = r0 - 1;
        return r0;
    }

    public static /* synthetic */ int zzc(zzau r0, Object r1) {
        return r0.zzv(r1);
    }

    public static /* synthetic */ int zzd(zzau r0) {
        return r0.zzu();
    }

    public static /* synthetic */ Object zzg(zzau r0, int r1) {
        return r0.zzA()[r1];
    }

    public static /* synthetic */ Object zzh(zzau r0, Object r1) {
        return r0.zzx(r1);
    }

    public static /* synthetic */ Object zzi() {
        return zzd;
    }

    public static /* synthetic */ Object zzj(zzau r0, int r1) {
        return r0.zzB()[r1];
    }

    public static /* synthetic */ Object zzk(zzau r0) {
        Object r02 = r0.zze;
        r02.getClass();
        return r02;
    }

    public static /* synthetic */ void zzm(zzau r0, int r1, Object r2) {
        r0.zzB()[r1] = r2;
    }

    public static /* synthetic */ int[] zzr(zzau r0) {
        return r0.zzz();
    }

    public static /* synthetic */ Object[] zzs(zzau r0) {
        return r0.zzA();
    }

    public static /* synthetic */ Object[] zzt(zzau r0) {
        return r0.zzB();
    }

    private final int zzu() {
        return (1 << (this.zzf & 31)) - 1;
    }

    private final int zzv(Object r8) {
        if (zzq() == false) goto L5;
        return -1;
    L5:
        int r0 = zzaw.zza(r8);
        int r2 = zzu();
        Object r3 = this.zze;
        r3.getClass();
        int r32 = zzav.zzc(r3, r0 & r2);
        if (r32 == 0) goto L16;
        int r4 = ~r2;
        int r02 = r0 & r4;
    L8:
        int r33 = r32 - 1;
        int r5 = zzz()[r33];
        if ((r5 & r4) == r02) goto L11;
    L14:
        r32 = r5 & r2;
        if (r32 != 0) goto L8;
    L11:
        if (zzo.zza(r8, zzA()[r33]) == false) goto L14;
        return r33;
    L16:
        return -1;
    }

    private final int zzw(int r9, int r10, int r11, int r12) {
        Object r0 = zzav.zzd(r10);
        int r102 = r10 - 1;
        if (r12 == 0) goto L5;
        zzav.zze(r0, r11 & r102, r12 + 1);
    L5:
        Object r112 = this.zze;
        r112.getClass();
        int[] r122 = zzz();
        int r1 = 0;
    L6:
        if (r1 > r9) goto L11;
        int r2 = zzav.zzc(r112, r1);
    L8:
        if (r2 == 0) goto L10;
        int r3 = r2 - 1;
        int r4 = r122[r3];
        int r5 = ((~r9) & r4) | r1;
        int r6 = r5 & r102;
        int r7 = zzav.zzc(r0, r6);
        zzav.zze(r0, r6, r2);
        r122[r3] = ((~r102) & r5) | (r7 & r102);
        r2 = r4 & r9;
        goto L8
    L10:
        r1 = r1 + 1;
        goto L6
    L11:
        this.zze = r0;
        zzy(r102);
        return r102;
    }

    private final Object zzx(Object r8) {
        if (zzq() == true) goto L5;
        int r2 = zzu();
        Object r3 = this.zze;
        r3.getClass();
        int r82 = zzav.zzb(r8, null, r2, r3, zzz(), zzA(), null);
        if (r82 == (-1)) goto L9;
        Object r1 = zzB()[r82];
        zzp(r82, r2);
        this.zzg--;
        zzn();
        return r1;
    L9:
        return zzd;
    L5:
        return zzd;
    }

    private final void zzy(int r2) {
        int r22 = Integer.numberOfLeadingZeros(r2);
        int r23 = (32 - r22) & 31;
        this.zzf = r23 | (this.zzf & (-32));
    }

    private final int[] zzz() {
        int[] r0 = this.zza;
        r0.getClass();
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        if (zzq() == false) goto L5;
        return;
    L5:
        zzn();
        Map r0 = zzl();
        if (r0 == null) goto L9;
        this.zzf = zzcf.zza(size(), 3, 1073741823);
        r0.clear();
        this.zze = null;
        this.zzg = 0;
        return;
    L9:
        Arrays.fill(zzA(), 0, this.zzg, null);
        Arrays.fill(zzB(), 0, this.zzg, null);
        Object r02 = this.zze;
        r02.getClass();
        if ((r02 instanceof byte[]) == false) goto L13;
        Arrays.fill((byte[]) r02, (byte) 0);
    L16:
        Arrays.fill(zzz(), 0, this.zzg, 0);
        this.zzg = 0;
        return;
    L13:
        if ((r02 instanceof short[]) == false) goto L15;
        Arrays.fill((short[]) r02, 0);
        goto L16
    L15:
        Arrays.fill((int[]) r02, 0);
        goto L16
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object r2) {
        Map r0 = zzl();
        if (r0 == null) goto L7;
        return r0.containsKey(r2);
    L7:
        if (zzv(r2) != (-1)) goto L10;
        return false;
    L10:
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object r4) {
        Map r0 = zzl();
        if (r0 != null) goto L14;
        int r1 = 0;
    L6:
        if (r1 >= this.zzg) goto L12;
        if (zzo.zza(r4, zzB()[r1]) == true) goto L9;
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
        zzap r02 = new zzap(this);
        this.zzi = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object r2) {
        Map r0 = zzl();
        if (r0 != null) goto L5;
        int r22 = zzv(r2);
        if (r22 != (-1)) goto L11;
        return null;
    L11:
        return zzB()[r22];
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
        zzar r02 = new zzar(this);
        this.zzh = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object r20, Object r21) {
        if (zzq() == false) goto L10;
        zzs.zzd(zzq(), "Arrays already allocated");
        int r3 = this.zzf;
        int r4 = Math.max(r3 + 1, 2);
        int r5 = Integer.highestOneBit(r4);
        if (r4 <= r5) goto L9;
        r5 = r5 + r5;
        if (r5 > 0) goto L9;
        r5 = TLObject.FLAG_30;
    L9:
        int r42 = Math.max(4, r5);
        this.zze = zzav.zzd(r42);
        zzy(r42 - 1);
        this.zza = new int[r3];
        this.zzb = new Object[r3];
        this.zzc = new Object[r3];
    L10:
        Map r32 = zzl();
        if (r32 != null) goto L13;
        int[] r33 = zzz();
        Object[] r43 = zzA();
        Object[] r52 = zzB();
        int r6 = this.zzg;
        int r7 = r6 + 1;
        int r8 = zzaw.zza(r20);
        int r9 = zzu();
        int r10 = r8 & r9;
        Object r11 = this.zze;
        r11.getClass();
        int r112 = zzav.zzc(r11, r10);
        if (r112 != 0) goto L20;
        if (r7 <= r9) goto L19;
        r9 = zzw(r9, zzav.zza(r9), r8, r6);
    L18:
        int r17 = 1;
    L40:
        int r34 = zzz().length;
        if (r7 <= r34) goto L45;
        int r113 = r17;
        int r44 = Math.min(1073741823, (Math.max(r113, r34 >>> 1) + r34) | r113);
        if (r44 == r34) goto L45;
        this.zza = Arrays.copyOf(zzz(), r44);
        this.zzb = Arrays.copyOf(zzA(), r44);
        this.zzc = Arrays.copyOf(zzB(), r44);
    L45:
        zzz()[r6] = (~r9) & r8;
        zzA()[r6] = r20;
        zzB()[r6] = r21;
        this.zzg = r7;
        zzn();
        return null;
    L19:
        Object r35 = this.zze;
        r35.getClass();
        zzav.zze(r35, r10, r7);
        goto L18
    L20:
        int r102 = ~r9;
        int r14 = r8 & r102;
        int r15 = 0;
    L21:
        int r114 = r112 - 1;
        int r16 = r33[r114];
        r17 = 1;
        int r13 = r16 & r102;
        if (r13 == r14) goto L24;
    L28:
        int r12 = r16 & r9;
        r15 = r15 + 1;
        if (r12 == 0) goto L31;
        r112 = r12;
        goto L21
    L31:
        if (r15 < 9) goto L37;
        LinkedHashMap r45 = new LinkedHashMap(zzu() + 1, 1.0f);
        int r36 = zze();
    L33:
        if (r36 < 0) goto L35;
        r45.put(zzA()[r36], zzB()[r36]);
        r36 = zzf(r36);
        goto L33
    L35:
        this.zze = r45;
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
        zzn();
        return r45.put(r20, r21);
    L37:
        if (r7 <= r9) goto L39;
        r9 = zzw(r9, zzav.zza(r9), r8, r6);
        goto L40
    L39:
        r33[r114] = (r7 & r9) | r13;
        goto L40
    L24:
        if (zzo.zza(r20, r43[r114]) == false) goto L28;
        Object r0 = r52[r114];
        r52[r114] = r21;
        return r0;
    L13:
        return r32.put(r20, r21);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object r2) {
        Map r0 = zzl();
        if (r0 != null) goto L5;
        Object r1 = zzx(r2);
        if (r1 != zzd) goto L10;
        return null;
    L10:
        return r1;
    L5:
        return r0.remove(r2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        Map r0 = zzl();
        if (r0 == null) goto L7;
        return r0.size();
    L7:
        return this.zzg;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        Collection r0 = this.zzj;
        if (r0 != null) goto L6;
        zzat r02 = new zzat(this);
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

    public final Map zzl() {
        Object r1 = this.zze;
        if ((r1 instanceof Map) == true) goto L5;
        return null;
    L5:
        return (Map) r1;
    }

    public final void zzn() {
        this.zzf += 32;
    }

    public final void zzo(int r3) {
        this.zzf = zzcf.zza(12, 1, 1073741823);
    }

    public final void zzp(int r10, int r11) {
        Object r0 = this.zze;
        r0.getClass();
        int[] r1 = zzz();
        Object[] r2 = zzA();
        Object[] r3 = zzB();
        int r9 = size();
        int r4 = r9 - 1;
        if (r10 >= r4) goto L13;
        Object r7 = r2[r4];
        r2[r10] = r7;
        r3[r10] = r3[r4];
        r2[r4] = null;
        r3[r4] = null;
        r1[r10] = r1[r4];
        r1[r4] = 0;
        int r22 = zzaw.zza(r7) & r11;
        int r32 = zzav.zzc(r0, r22);
        if (r32 == r9) goto L11;
    L6:
        int r33 = r32 - 1;
        int r02 = r1[r33];
        int r23 = r02 & r11;
        if (r23 == r9) goto L9;
        r32 = r23;
        goto L6
    L9:
        r1[r33] = ((~r11) & r02) | ((r10 + 1) & r11);
        return;
    L11:
        zzav.zze(r0, r22, r10 + 1);
        return;
    L13:
        r2[r10] = null;
        r3[r10] = null;
        r1[r10] = 0;
    }

    public final boolean zzq() {
        if (this.zze != null) goto L6;
        return true;
    L6:
        return false;
    }
}
