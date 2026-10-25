package com.google.android.gms.internal.play_billing;

import com.google.android.gms.internal.play_billing.zzfe;
import com.google.android.gms.internal.play_billing.zzfi;
import com.google.gson.JsonArray$$ExternalSyntheticBUOutline0;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class zzfi<MessageType extends zzfi<MessageType, BuilderType>, BuilderType extends zzfe<MessageType, BuilderType>> extends zzds<MessageType, BuilderType> {
    private static final Map zzb = null;
    protected zzhi zzc;
    private int zzd;

    static {
        zzb = new ConcurrentHashMap();
    }

    public zzfi() {
        this.zzd = -1;
        this.zzc = zzhi.zzc();
    }

    private static final boolean zzA(zzfi r4, boolean r5) {
        byte r2 = ((Byte) r4.zzb(1, null, null)).byteValue();
        if (r2 != 1) goto L5;
        return true;
    L5:
        if (r2 != 0) goto L8;
        return false;
    L8:
        boolean r22 = zzgs.zza().zzb(r4.getClass()).zzk(r4);
        if (r5 == false) goto L14;
        if (true == r22) goto L12;
        zzfi r52 = null;
    L13:
        r4.zzb(2, r52, null);
        goto L14
    L12:
        r52 = r4;
    L14:
        return r22;
    }

    private final int zzc(zzgv r2) {
        return zzgs.zza().zzb(getClass()).zza(this);
    }

    private static zzfi zzd(zzfi r6, byte[] r7, int r8, int r9, zzeu r10) {
        if (r9 != 0) goto L4;
        return r6;
    L4:
        zzfi r1 = r6.zzo();
        zzgv r0 = zzgs.zza().zzb(r1.getClass());     // Catch: IndexOutOfBoundsException -> L7 IOException -> L9 zzhg -> L16 zzfq -> L19
        r0.zzh(r1, r7, 0, r9, new zzdw(r10));     // Catch: IndexOutOfBoundsException -> L7 IOException -> L9 zzhg -> L16 zzfq -> L19
        r0.zzf(r1);     // Catch: IndexOutOfBoundsException -> L7 IOException -> L9 zzhg -> L16 zzfq -> L19
        return r1;
    L19:
        e = move-exception;
        throw e;
    L16:
        e = move-exception;
        throw e.zza();
    L9:
        e = move-exception;
        if ((e.getCause() instanceof zzfq) == false) goto L15;
        throw ((zzfq) e.getCause());
    L15:
        throw new zzfq(e);
    L7:
        zzdx$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return null;
    }

    public static zzfi zzn(Class r5) {
        Map r0 = zzb;
        zzfi r1 = (zzfi) r0.get(r5);
        if (r1 == null) goto L17;
    L9:
        if (r1 != null) goto L16;
        zzfi r12 = (zzfi) ((zzfi) zzho.zze(r5)).zzb(6, null, null);
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
        r1 = (zzfi) r0.get(r5);
    L6:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Class initialization cannot fail.", e);
        return null;
    }

    public static zzfi zzp(zzfi r2, byte[] r3, zzeu r4) {
        zzfi r22 = zzd(r2, r3, 0, r3.length, r4);
        if (r22 != null) goto L5;
    L9:
        return r22;
    L5:
        if (zzA(r22, true) == true) goto L9;
        throw new zzhg(r22).zza();
    }

    public static zzfm zzq() {
        return zzfj.zzf();
    }

    public static zzfn zzr() {
        return zzgt.zze();
    }

    public static Object zzs(Method r1, Object r2, Object... r3) {
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

    public static Object zzt(zzgl r1, String r2, Object[] r3) {
        return new zzgu(r1, r2, r3);
    }

    public static void zzw(Class r1, zzfi r2) {
        r2.zzv();
        zzb.put(r1, r2);
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
        return zzgs.zza().zzb(r0).zzj(this, (zzfi) r4);
    }

    public final int hashCode() {
        if (zzz() == true) goto L9;
        int r0 = this.zza;
        if (r0 != 0) goto L10;
        int r02 = zzi();
        this.zza = r02;
        return r02;
    L10:
        return r0;
    L9:
        return zzi();
    }

    public final String toString() {
        return zzgn.zza(this, super.toString());
    }

    @Override // com.google.android.gms.internal.play_billing.zzgl
    public final /* synthetic */ zzgk zzK() {
        return (zzfe) zzb(5, null, null);
    }

    @Override // com.google.android.gms.internal.play_billing.zzgl
    public final void zzL(zzep r3) {
        zzgs.zza().zzb(getClass()).zzi(this, zzeq.zza(r3));
    }

    public abstract Object zzb(int r1, Object r2, Object r3);

    @Override // com.google.android.gms.internal.play_billing.zzds
    public final int zze(zzgv r4) {
        if (zzz() == false) goto L10;
        int r3 = r4.zza(this);
        if (r3 < 0) goto L7;
        return r3;
    L7:
        JsonArray$$ExternalSyntheticBUOutline0.m("serialized size must be non-negative, was ", r3);
        return 0;
    L10:
        int r0 = this.zzd & Integer.MAX_VALUE;
        if (r0 != Integer.MAX_VALUE) goto L17;
        int r42 = r4.zza(this);
        if (r42 < 0) goto L16;
        this.zzd = (this.zzd & Integer.MIN_VALUE) | r42;
        return r42;
    L16:
        JsonArray$$ExternalSyntheticBUOutline0.m("serialized size must be non-negative, was ", r42);
        return 0;
    L17:
        return r0;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgm
    public final /* synthetic */ zzgl zzh() {
        return (zzfi) zzb(6, null, null);
    }

    public final int zzi() {
        return zzgs.zza().zzb(getClass()).zzb(this);
    }

    @Override // com.google.android.gms.internal.play_billing.zzgl
    public final int zzj() {
        if (zzz() == false) goto L10;
        int r4 = zzc(null);
        if (r4 < 0) goto L7;
        return r4;
    L7:
        JsonArray$$ExternalSyntheticBUOutline0.m("serialized size must be non-negative, was ", r4);
        return 0;
    L10:
        int r0 = this.zzd & Integer.MAX_VALUE;
        if (r0 == Integer.MAX_VALUE) goto L13;
        return r0;
    L13:
        int r02 = zzc(null);
        if (r02 < 0) goto L17;
        this.zzd = (this.zzd & Integer.MIN_VALUE) | r02;
        return r02;
    L17:
        JsonArray$$ExternalSyntheticBUOutline0.m("serialized size must be non-negative, was ", r02);
        return 0;
    }

    public final boolean zzk() {
        return zzA(this, true);
    }

    public final zzfe zzl() {
        return (zzfe) zzb(5, null, null);
    }

    public final zzfe zzm() {
        zzfe r0 = (zzfe) zzb(5, null, null);
        r0.zzd(this);
        return r0;
    }

    public final zzfi zzo() {
        return (zzfi) zzb(4, null, null);
    }

    public final void zzu() {
        zzgs.zza().zzb(getClass()).zzf(this);
        zzv();
    }

    public final void zzv() {
        this.zzd &= Integer.MAX_VALUE;
    }

    public final void zzx(int r2) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    public final boolean zzz() {
        if ((this.zzd & Integer.MIN_VALUE) == 0) goto L6;
        return true;
    L6:
        return false;
    }
}
