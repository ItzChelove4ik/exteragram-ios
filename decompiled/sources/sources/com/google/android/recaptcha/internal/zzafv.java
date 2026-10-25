package com.google.android.recaptcha.internal;

import com.google.android.gms.internal.cast.zzyd$$ExternalSyntheticBUOutline0;
import com.google.android.recaptcha.internal.zzafq;
import com.google.android.recaptcha.internal.zzafv;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class zzafv<MessageType extends zzafv<MessageType, BuilderType>, BuilderType extends zzafq<MessageType, BuilderType>> extends zzaec<MessageType, BuilderType> {
    private static final Map zzd = null;
    private int zza;
    protected zzaia zzc;

    static {
        zzd = new ConcurrentHashMap();
    }

    public zzafv() {
        this.zza = -1;
        this.zzc = zzaia.zza();
    }

    public static zzafv zzK(Class r5) {
        Map r0 = zzd;
        zzafv r1 = (zzafv) r0.get(r5);
        if (r1 == null) goto L17;
    L9:
        if (r1 != null) goto L16;
        zzafv r12 = (zzafv) ((zzafv) zzaig.zza(r5)).zzf(6, null, null);
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
        r1 = (zzafv) r0.get(r5);
    L6:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Class initialization cannot fail.", e);
        return null;
    }

    public static void zzL(Class r1, zzafv r2) {
        r2.zzA();
        zzd.put(r1, r2);
    }

    public static Object zzM(zzagx r1, String r2, Object[] r3) {
        return new zzahi(r1, r2, r3);
    }

    public static Object zzN(Method r1, Object r2, Object... r3) {
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

    public static zzagc zzO() {
        return zzafw.zzd();
    }

    public static zzagc zzP(zzagc r1) {
        int r0 = r1.size();
        return r1.zze(r0 + r0);
    }

    public static zzagd zzQ() {
        return zzagq.zzd();
    }

    public static zzage zzR() {
        return zzahh.zzd();
    }

    public static zzage zzS(zzage r1) {
        int r0 = r1.size();
        return r1.zzg(r0 + r0);
    }

    public static zzafv zzT(zzafv r3, zzaer r4) {
        zzafh r0 = zzafh.zza;
        int r02 = zzaef.$r8$clinit;
        zzafh r03 = zzafh.zza;
        zzaev r42 = r4.zzl();
        zzafv r32 = r3.zzC();
        zzahn r1 = zzahg.zza().zzb(r32.getClass());     // Catch: RuntimeException -> L6 IOException -> L12 zzahy -> L19 zzagk -> L22
        r1.zzg(r32, zzaew.zza(r42), r03);     // Catch: RuntimeException -> L6 IOException -> L12 zzahy -> L19 zzagk -> L22
        r1.zzk(r32);     // Catch: RuntimeException -> L6 IOException -> L12 zzahy -> L19 zzagk -> L22
        r42.zzb(0);
        zzd(r32);
        zzd(r32);
        return r32;
    L22:
        e = move-exception;
        if (e.zzb() == true) goto L26;
        throw e;
    L26:
        throw new zzagk(e);
    L19:
        e = move-exception;
        throw e.zza();
    L12:
        e = move-exception;
        if ((e.getCause() instanceof zzagk) == false) goto L18;
        throw ((zzagk) e.getCause());
    L18:
        throw new zzagk(e);
    L6:
        e = move-exception;
        if ((e.getCause() instanceof zzagk) == true) goto L10;
        throw e;
    L10:
        throw ((zzagk) e.getCause());
    }

    public static zzafv zzU(zzafv r2, zzaer r3, zzafh r4) {
        zzaev r32 = r3.zzl();
        zzafv r22 = r2.zzC();
        zzahn r0 = zzahg.zza().zzb(r22.getClass());     // Catch: RuntimeException -> L6 IOException -> L12 zzahy -> L19 zzagk -> L22
        r0.zzg(r22, zzaew.zza(r32), r4);     // Catch: RuntimeException -> L6 IOException -> L12 zzahy -> L19 zzagk -> L22
        r0.zzk(r22);     // Catch: RuntimeException -> L6 IOException -> L12 zzahy -> L19 zzagk -> L22
        r32.zzb(0);
        zzd(r22);
        return r22;
    L22:
        e = move-exception;
        if (e.zzb() == true) goto L26;
        throw e;
    L26:
        throw new zzagk(e);
    L19:
        e = move-exception;
        throw e.zza();
    L12:
        e = move-exception;
        if ((e.getCause() instanceof zzagk) == false) goto L18;
        throw ((zzagk) e.getCause());
    L18:
        throw new zzagk(e);
    L6:
        e = move-exception;
        if ((e.getCause() instanceof zzagk) == true) goto L10;
        throw e;
    L10:
        throw ((zzagk) e.getCause());
    }

    public static zzafv zzV(zzafv r3, byte[] r4) {
        int r0 = r4.length;
        zzafh r1 = zzafh.zza;
        int r12 = zzaef.$r8$clinit;
        zzafv r32 = zzc(r3, r4, 0, r0, zzafh.zza);
        zzd(r32);
        return r32;
    }

    public static zzafv zzW(zzafv r2, byte[] r3, zzafh r4) {
        zzafv r22 = zzc(r2, r3, 0, r3.length, r4);
        zzd(r22);
        return r22;
    }

    public static /* synthetic */ boolean zzY(zzafv r0, boolean r1) {
        return zzb(r0, false);
    }

    public static /* synthetic */ zzafv zzZ(zzafv r0, byte[] r1, int r2, int r3, zzafh r4) {
        return zzc(r0, r1, 0, r3, r4);
    }

    private final int zza(zzahn r2) {
        return zzahg.zza().zzb(getClass()).zze(this);
    }

    private static final boolean zzb(zzafv r4, boolean r5) {
        byte r2 = ((Byte) r4.zzf(1, null, null)).byteValue();
        if (r2 != 1) goto L5;
        return true;
    L5:
        if (r2 != 0) goto L8;
        return false;
    L8:
        boolean r22 = zzahg.zza().zzb(r4.getClass()).zzl(r4);
        if (r5 == false) goto L14;
        if (true == r22) goto L12;
        zzafv r52 = null;
    L13:
        r4.zzf(2, r52, null);
        goto L14
    L12:
        r52 = r4;
    L14:
        return r22;
    }

    private static zzafv zzc(zzafv r6, byte[] r7, int r8, int r9, zzafh r10) {
        if (r9 != 0) goto L4;
        return r6;
    L4:
        zzafv r1 = r6.zzC();
        zzahn r0 = zzahg.zza().zzb(r1.getClass());     // Catch: IndexOutOfBoundsException -> L7 IOException -> L9 zzahy -> L16 zzagk -> L19
        r0.zzj(r1, r7, 0, r9, new zzaeg(r10));     // Catch: IndexOutOfBoundsException -> L7 IOException -> L9 zzahy -> L16 zzagk -> L19
        r0.zzk(r1);     // Catch: IndexOutOfBoundsException -> L7 IOException -> L9 zzahy -> L16 zzagk -> L19
        return r1;
    L19:
        e = move-exception;
        if (e.zzb() == true) goto L23;
        throw e;
    L23:
        throw new zzagk(e);
    L16:
        e = move-exception;
        throw e.zza();
    L9:
        e = move-exception;
        if ((e.getCause() instanceof zzagk) == false) goto L15;
        throw ((zzagk) e.getCause());
    L15:
        throw new zzagk(e);
    L7:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return null;
    }

    private static zzafv zzd(zzafv r1) {
        if (r1 != null) goto L4;
    L8:
        return r1;
    L4:
        if (zzb(r1, true) == true) goto L8;
        throw new zzahy(r1).zza();
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
        return zzahg.zza().zzb(r0).zzb(this, (zzafv) r4);
    }

    public final int hashCode() {
        if (zzz() == true) goto L9;
        int r0 = this.zzb;
        if (r0 != 0) goto L10;
        int r02 = zzD();
        this.zzb = r02;
        return r02;
    L10:
        return r0;
    L9:
        return zzD();
    }

    public final String toString() {
        return zzagz.zza(this, super.toString());
    }

    public final void zzA() {
        this.zza &= Integer.MAX_VALUE;
    }

    @Override // com.google.android.recaptcha.internal.zzagx
    public final zzahe zzB() {
        return (zzahe) zzf(7, null, null);
    }

    public final zzafv zzC() {
        return (zzafv) zzf(4, null, null);
    }

    public final int zzD() {
        return zzahg.zza().zzb(getClass()).zzc(this);
    }

    public final void zzE() {
        zzahg.zza().zzb(getClass()).zzk(this);
        zzA();
    }

    public final zzafq zzF() {
        return (zzafq) zzf(5, null, null);
    }

    public final zzafq zzG() {
        zzafq r0 = (zzafq) zzf(5, null, null);
        r0.zzq(this);
        return r0;
    }

    public final void zzH(int r2) {
        this.zza = (this.zza & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    @Override // com.google.android.recaptcha.internal.zzagx
    public final void zzI(zzafa r3) {
        zzahg.zza().zzb(getClass()).zzf(this, zzafb.zza(r3));
    }

    @Override // com.google.android.recaptcha.internal.zzagx
    public final int zzJ() {
        if (zzz() == false) goto L10;
        int r3 = zza(null);
        if (r3 < 0) goto L7;
        return r3;
    L7:
        zzyd$$ExternalSyntheticBUOutline0.m(String.valueOf(r3).length() + 42, r3);
        return 0;
    L10:
        int r0 = this.zza & Integer.MAX_VALUE;
        if (r0 == Integer.MAX_VALUE) goto L13;
        return r0;
    L13:
        int r02 = zza(null);
        if (r02 < 0) goto L17;
        this.zza = (this.zza & Integer.MIN_VALUE) | r02;
        return r02;
    L17:
        zzyd$$ExternalSyntheticBUOutline0.m(String.valueOf(r02).length() + 42, r02);
        return 0;
    }

    @Override // com.google.android.recaptcha.internal.zzagx
    public final /* synthetic */ zzagw zzX() {
        return (zzafq) zzf(5, null, null);
    }

    @Override // com.google.android.recaptcha.internal.zzagy
    public final boolean zzab() {
        return zzb(this, true);
    }

    @Override // com.google.android.recaptcha.internal.zzagy
    public final /* synthetic */ zzagx zzac() {
        return (zzafv) zzf(6, null, null);
    }

    public abstract Object zzf(int r1, Object r2, Object r3);

    @Override // com.google.android.recaptcha.internal.zzaec
    public final int zzx(zzahn r3) {
        if (zzz() == false) goto L10;
        int r2 = r3.zze(this);
        if (r2 < 0) goto L7;
        return r2;
    L7:
        zzyd$$ExternalSyntheticBUOutline0.m(String.valueOf(r2).length() + 42, r2);
        return 0;
    L10:
        int r0 = this.zza & Integer.MAX_VALUE;
        if (r0 != Integer.MAX_VALUE) goto L17;
        int r32 = r3.zze(this);
        if (r32 < 0) goto L16;
        this.zza = (this.zza & Integer.MIN_VALUE) | r32;
        return r32;
    L16:
        zzyd$$ExternalSyntheticBUOutline0.m(String.valueOf(r32).length() + 42, r32);
        return 0;
    L17:
        return r0;
    }

    public final boolean zzz() {
        if ((this.zza & Integer.MIN_VALUE) == 0) goto L6;
        return true;
    L6:
        return false;
    }
}
