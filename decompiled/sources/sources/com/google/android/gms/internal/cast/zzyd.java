package com.google.android.gms.internal.cast;

import com.google.android.gms.internal.cast.zzya;
import com.google.android.gms.internal.cast.zzyd;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class zzyd<MessageType extends zzyd<MessageType, BuilderType>, BuilderType extends zzya<MessageType, BuilderType>> extends zzwz<MessageType, BuilderType> {
    private static final Map zzd = null;
    private int zzb;
    protected zzaae zzc;

    static {
        zzd = new ConcurrentHashMap();
    }

    public zzyd() {
        this.zzb = -1;
        this.zzc = zzaae.zza();
    }

    public static zzyd zzF(Class r5) {
        Map r0 = zzd;
        zzyd r1 = (zzyd) r0.get(r5);
        if (r1 == null) goto L17;
    L9:
        if (r1 != null) goto L16;
        zzyd r12 = (zzyd) ((zzyd) zzaak.zzc(r5)).zzb(6, null, null);
        if (r12 == null) goto L14;
        r0.put(r5, r12);
        return r12;
    L14:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    L16:
        return r1;
    L17:
        Class.forName(r5.getName(), true, r5.getClassLoader());     // Catch: ClassNotFoundException -> L6
        r1 = (zzyd) r0.get(r5);
    L6:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Class initialization cannot fail.", e);
        return null;
    }

    public static void zzG(Class r1, zzyd r2) {
        r2.zzw();
        zzd.put(r1, r2);
    }

    public static Object zzH(zzzi r1, String r2, Object[] r3) {
        return new zzzr(r1, r2, r3);
    }

    public static Object zzI(Method r1, Object r2, Object... r3) {
        return r1.invoke(r2, r3);
    L16:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Couldn't use Java reflection to implement protocol message reflection.", e);
        return null;
    L5:
        e = move-exception;
        Throwable r12 = e.getCause();
        if ((r12 instanceof RuntimeException) == true) goto L15;
        if ((r12 instanceof Error) == true) goto L13;
        Make$Map$$ExternalSyntheticBUOutline0.m("Unexpected exception thrown by generated accessor method.", r12);
        return null;
    L13:
        throw ((Error) r12);
    L15:
        throw ((RuntimeException) r12);
    }

    public static zzyj zzJ() {
        return zzye.zzd();
    }

    public static zzyk zzK() {
        return zzyx.zzd();
    }

    public static zzyi zzL() {
        return zzxy.zzd();
    }

    public static zzyl zzM() {
        return zzzq.zzd();
    }

    public static zzyl zzN(zzyl r1) {
        int r0 = r1.size();
        return r1.zzf(r0 + r0);
    }

    private final int zza(zzzs r2) {
        return zzzp.zza().zzb(getClass()).zze(this);
    }

    private static final boolean zzc(zzyd r4, boolean r5) {
        byte r2 = ((Byte) r4.zzb(1, null, null)).byteValue();
        if (r2 != 1) goto L5;
        return true;
    L5:
        if (r2 != 0) goto L8;
        return false;
    L8:
        boolean r22 = zzzp.zza().zzb(r4.getClass()).zzh(r4);
        if (r5 == false) goto L14;
        if (true == r22) goto L12;
        zzyd r52 = null;
    L13:
        r4.zzb(2, r52, null);
        goto L14
    L12:
        r52 = r4;
    L14:
        return r22;
    }

    public final boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if (r4 != null) goto L9;
        return false;
    L9:
        if (getClass() == r4.getClass()) goto L11;
        return false;
    L11:
        Class<?> r0 = getClass();
        return zzzp.zza().zzb(r0).zzb(this, (zzyd) r4);
    }

    public final int hashCode() {
        if (zzv() == true) goto L9;
        int r0 = this.zza;
        if (r0 != 0) goto L10;
        int r02 = zzz();
        this.zza = r02;
        return r02;
    L10:
        return r0;
    L9:
        return zzz();
    }

    public final String toString() {
        return zzzk.zza(this, super.toString());
    }

    public final void zzA() {
        zzzp.zza().zzb(getClass()).zzg(this);
        zzw();
    }

    public final zzya zzB() {
        return (zzya) zzb(5, null, null);
    }

    public final void zzC(int r2) {
        this.zzb = (this.zzb & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    @Override // com.google.android.gms.internal.cast.zzzi
    public final void zzD(zzxp r3) {
        zzzp.zza().zzb(getClass()).zzf(this, zzxq.zza(r3));
    }

    @Override // com.google.android.gms.internal.cast.zzzi
    public final int zzE() {
        if (zzv() == false) goto L10;
        int r3 = zza(null);
        if (r3 < 0) goto L7;
        return r3;
    L7:
        zzyd$$ExternalSyntheticBUOutline0.m(String.valueOf(r3).length() + 42, r3);
        return 0;
    L10:
        int r0 = this.zzb & Integer.MAX_VALUE;
        if (r0 == Integer.MAX_VALUE) goto L13;
        return r0;
    L13:
        int r02 = zza(null);
        if (r02 < 0) goto L17;
        this.zzb = (this.zzb & Integer.MIN_VALUE) | r02;
        return r02;
    L17:
        zzyd$$ExternalSyntheticBUOutline0.m(String.valueOf(r02).length() + 42, r02);
        return 0;
    }

    @Override // com.google.android.gms.internal.cast.zzzi
    public final /* synthetic */ zzzh zzO() {
        return (zzya) zzb(5, null, null);
    }

    public abstract Object zzb(int r1, Object r2, Object r3);

    public final boolean zzr() {
        return zzc(this, true);
    }

    @Override // com.google.android.gms.internal.cast.zzwz
    public final int zzt(zzzs r3) {
        if (zzv() == false) goto L10;
        int r2 = r3.zze(this);
        if (r2 < 0) goto L7;
        return r2;
    L7:
        zzyd$$ExternalSyntheticBUOutline0.m(String.valueOf(r2).length() + 42, r2);
        return 0;
    L10:
        int r0 = this.zzb & Integer.MAX_VALUE;
        if (r0 != Integer.MAX_VALUE) goto L17;
        int r32 = r3.zze(this);
        if (r32 < 0) goto L16;
        this.zzb = (this.zzb & Integer.MIN_VALUE) | r32;
        return r32;
    L16:
        zzyd$$ExternalSyntheticBUOutline0.m(String.valueOf(r32).length() + 42, r32);
        return 0;
    L17:
        return r0;
    }

    public final boolean zzv() {
        if ((this.zzb & Integer.MIN_VALUE) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final void zzw() {
        this.zzb &= Integer.MAX_VALUE;
    }

    @Override // com.google.android.gms.internal.cast.zzzj
    public final /* synthetic */ zzzi zzx() {
        return (zzyd) zzb(6, null, null);
    }

    public final zzyd zzy() {
        return (zzyd) zzb(4, null, null);
    }

    public final int zzz() {
        return zzzp.zza().zzb(getClass()).zzc(this);
    }
}
