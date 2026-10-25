package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.cast.zzyd$$ExternalSyntheticBUOutline0;
import com.google.android.gms.internal.measurement.zzadp;
import com.google.android.gms.internal.measurement.zzadu;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class zzadu<MessageType extends zzadu<MessageType, BuilderType>, BuilderType extends zzadp<MessageType, BuilderType>> extends zzacb<MessageType, BuilderType> {
    public static final /* synthetic */ int zzd = 0;
    private static final Map zze = null;
    private int zzb;
    protected zzaga zzc;

    static {
        zze = new ConcurrentHashMap();
    }

    public zzadu() {
        this.zzb = -1;
        this.zzc = zzaga.zza();
    }

    private final int zzc(zzafp r2) {
        return zzafl.zza().zzb(getClass()).zze(this);
    }

    public static zzadu zzcA(zzadu r2, byte[] r3, zzadf r4) {
        zzadu r22 = zze(r2, r3, 0, r3.length, r4);
        zzf(r22);
        return r22;
    }

    public static zzadu zzcB(zzadu r2, InputStream r3, zzadf r4) {
        zzacv r32 = zzacv.zzM(r3, 4096);
        zzadu r22 = r2.zzck();
        zzafp r0 = zzafl.zza().zzb(r22.getClass());     // Catch: RuntimeException -> L6 IOException -> L12 zzafy -> L19 zzaeh -> L22
        r0.zzg(r22, zzacw.zza(r32), r4);     // Catch: RuntimeException -> L6 IOException -> L12 zzafy -> L19 zzaeh -> L22
        r0.zzk(r22);     // Catch: RuntimeException -> L6 IOException -> L12 zzafy -> L19 zzaeh -> L22
        zzf(r22);
        return r22;
    L22:
        e = move-exception;
        if (e.zzb() == true) goto L26;
        throw e;
    L26:
        throw new zzaeh(e);
    L19:
        e = move-exception;
        throw e.zza();
    L12:
        e = move-exception;
        if ((e.getCause() instanceof zzaeh) == false) goto L18;
        throw ((zzaeh) e.getCause());
    L18:
        throw new zzaeh(e);
    L6:
        e = move-exception;
        if ((e.getCause() instanceof zzaeh) == true) goto L10;
        throw e;
    L10:
        throw ((zzaeh) e.getCause());
    }

    public static zzadu zzcC(zzadu r2, zzacv r3, zzadf r4) {
        zzadu r22 = r2.zzck();
        zzafp r0 = zzafl.zza().zzb(r22.getClass());     // Catch: RuntimeException -> L6 IOException -> L12 zzafy -> L19 zzaeh -> L22
        r0.zzg(r22, zzacw.zza(r3), r4);     // Catch: RuntimeException -> L6 IOException -> L12 zzafy -> L19 zzaeh -> L22
        r0.zzk(r22);     // Catch: RuntimeException -> L6 IOException -> L12 zzafy -> L19 zzaeh -> L22
        zzf(r22);
        return r22;
    L22:
        e = move-exception;
        if (e.zzb() == true) goto L26;
        throw e;
    L26:
        throw new zzaeh(e);
    L19:
        e = move-exception;
        throw e.zza();
    L12:
        e = move-exception;
        if ((e.getCause() instanceof zzaeh) == false) goto L18;
        throw ((zzaeh) e.getCause());
    L18:
        throw new zzaeh(e);
    L6:
        e = move-exception;
        if ((e.getCause() instanceof zzaeh) == true) goto L10;
        throw e;
    L10:
        throw ((zzaeh) e.getCause());
    }

    public static /* synthetic */ boolean zzcD(zzadu r0, boolean r1) {
        return zzd(r0, false);
    }

    public static zzadu zzcr(Class r5) {
        Map r0 = zze;
        zzadu r1 = (zzadu) r0.get(r5);
        if (r1 == null) goto L17;
    L9:
        if (r1 != null) goto L16;
        zzadu r12 = (zzadu) ((zzadu) zzagg.zzb(r5)).zzg(6, null, null);
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
        r1 = (zzadu) r0.get(r5);
    L6:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Class initialization cannot fail.", e);
        return null;
    }

    public static void zzcs(Class r1, zzadu r2) {
        r2.zzci();
        zze.put(r1, r2);
    }

    public static Object zzct(zzafc r1, String r2, Object[] r3) {
        return new zzafn(r1, r2, r3);
    }

    public static Object zzcu(Method r1, Object r2, Object... r3) {
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

    public static zzaeb zzcv() {
        return zzadv.zzd();
    }

    public static zzaee zzcw() {
        return zzaeq.zze();
    }

    public static zzaee zzcx(zzaee r1) {
        int r0 = r1.size();
        return r1.zzd(r0 + r0);
    }

    public static zzaef zzcy() {
        return zzafm.zzd();
    }

    public static zzaef zzcz(zzaef r1) {
        int r0 = r1.size();
        return r1.zzg(r0 + r0);
    }

    private static final boolean zzd(zzadu r4, boolean r5) {
        byte r2 = ((Byte) r4.zzg(1, null, null)).byteValue();
        if (r2 != 1) goto L5;
        return true;
    L5:
        if (r2 != 0) goto L8;
        return false;
    L8:
        boolean r22 = zzafl.zza().zzb(r4.getClass()).zzl(r4);
        if (r5 == false) goto L14;
        if (true == r22) goto L12;
        zzadu r52 = null;
    L13:
        r4.zzg(2, r52, null);
        goto L14
    L12:
        r52 = r4;
    L14:
        return r22;
    }

    private static zzadu zze(zzadu r6, byte[] r7, int r8, int r9, zzadf r10) {
        if (r9 != 0) goto L4;
        return r6;
    L4:
        zzadu r1 = r6.zzck();
        zzafp r0 = zzafl.zza().zzb(r1.getClass());     // Catch: IndexOutOfBoundsException -> L7 IOException -> L9 zzafy -> L16 zzaeh -> L19
        r0.zzj(r1, r7, 0, r9, new zzacg(r10));     // Catch: IndexOutOfBoundsException -> L7 IOException -> L9 zzafy -> L16 zzaeh -> L19
        r0.zzk(r1);     // Catch: IndexOutOfBoundsException -> L7 IOException -> L9 zzafy -> L16 zzaeh -> L19
        return r1;
    L19:
        e = move-exception;
        if (e.zzb() == true) goto L23;
        throw e;
    L23:
        throw new zzaeh(e);
    L16:
        e = move-exception;
        throw e.zza();
    L9:
        e = move-exception;
        if ((e.getCause() instanceof zzaeh) == false) goto L15;
        throw ((zzaeh) e.getCause());
    L15:
        throw new zzaeh(e);
    L7:
        zzmw$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return null;
    }

    private static zzadu zzf(zzadu r1) {
        if (r1 != null) goto L4;
    L8:
        return r1;
    L4:
        if (zzd(r1, true) == true) goto L8;
        throw new zzafy(r1).zza();
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
        return zzafl.zza().zzb(r0).zzb(this, (zzadu) r4);
    }

    public final int hashCode() {
        if (zzch() == true) goto L9;
        int r0 = this.zza;
        if (r0 != 0) goto L10;
        int r02 = zzcl();
        this.zza = r02;
        return r02;
    L10:
        return r0;
    L9:
        return zzcl();
    }

    public final String toString() {
        return zzafe.zza(this, super.toString());
    }

    @Override // com.google.android.gms.internal.measurement.zzafc
    public final void zzcH(zzada r3) {
        zzafl.zza().zzb(getClass()).zzf(this, zzadb.zza(r3));
    }

    @Override // com.google.android.gms.internal.measurement.zzafc
    public final /* synthetic */ zzafb zzcI() {
        return (zzadp) zzg(5, null, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzafd
    public final boolean zzcJ() {
        return zzd(this, true);
    }

    @Override // com.google.android.gms.internal.measurement.zzafd
    public final /* synthetic */ zzafc zzcK() {
        return (zzadu) zzg(6, null, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzacb
    public final int zzcf(zzafp r3) {
        if (zzch() == false) goto L10;
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

    public final boolean zzch() {
        if ((this.zzb & Integer.MIN_VALUE) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final void zzci() {
        this.zzb &= Integer.MAX_VALUE;
    }

    @Override // com.google.android.gms.internal.measurement.zzafc
    public final zzafj zzcj() {
        return (zzafj) zzg(7, null, null);
    }

    public final zzadu zzck() {
        return (zzadu) zzg(4, null, null);
    }

    public final int zzcl() {
        return zzafl.zza().zzb(getClass()).zzc(this);
    }

    public final void zzcm() {
        zzafl.zza().zzb(getClass()).zzk(this);
        zzci();
    }

    public final zzadp zzcn() {
        return (zzadp) zzg(5, null, null);
    }

    public final zzadp zzco() {
        zzadp r0 = (zzadp) zzg(5, null, null);
        r0.zzbe(this);
        return r0;
    }

    public final void zzcp(int r2) {
        this.zzb = (this.zzb & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    @Override // com.google.android.gms.internal.measurement.zzafc
    public final int zzcq() {
        if (zzch() == false) goto L10;
        int r3 = zzc(null);
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
        int r02 = zzc(null);
        if (r02 < 0) goto L17;
        this.zzb = (this.zzb & Integer.MIN_VALUE) | r02;
        return r02;
    L17:
        zzyd$$ExternalSyntheticBUOutline0.m(String.valueOf(r02).length() + 42, r02);
        return 0;
    }

    public abstract Object zzg(int r1, Object r2, Object r3);
}
