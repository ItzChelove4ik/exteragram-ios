package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.android.gms.internal.mlkit_genai_prompt.zzawu;
import com.google.android.gms.internal.mlkit_genai_prompt.zzawy;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class zzawy<MessageType extends zzawy<MessageType, BuilderType>, BuilderType extends zzawu<MessageType, BuilderType>> extends zzavo<MessageType, BuilderType> {
    private static final Map zzd = null;
    private int zzb;
    protected zzayr zzc;

    static {
        zzd = new ConcurrentHashMap();
    }

    public zzawy() {
        this.zzb = -1;
        this.zzc = zzayr.zza();
    }

    public static zzawy zzM(Class r5) {
        Map r0 = zzd;
        zzawy r1 = (zzawy) r0.get(r5);
        if (r1 == null) goto L17;
    L9:
        if (r1 != null) goto L16;
        zzawy r12 = (zzawy) ((zzawy) zzayx.zza(r5)).zzb(6, null, null);
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
        r1 = (zzawy) r0.get(r5);
    L6:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Class initialization cannot fail.", e);
        return null;
    }

    public static void zzN(Class r1, zzawy r2) {
        r2.zzF();
        zzd.put(r1, r2);
    }

    public static Object zzO(zzaxw r1, String r2, Object[] r3) {
        return new zzayf(r1, r2, r3);
    }

    public static Object zzP(Method r1, Object r2, Object... r3) {
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

    public static zzaxh zzQ() {
        return zzaye.zzd();
    }

    public static zzawy zzR(zzawy r2, byte[] r3, zzawl r4) {
        zzawy r22 = zzc(r2, r3, 0, r3.length, r4);
        if (r22 != null) goto L5;
    L9:
        return r22;
    L5:
        if (zza(r22, true) == true) goto L9;
        throw new zzayp(r22).zza();
    }

    private static final boolean zza(zzawy r4, boolean r5) {
        byte r2 = ((Byte) r4.zzb(1, null, null)).byteValue();
        if (r2 != 1) goto L5;
        return true;
    L5:
        if (r2 != 0) goto L8;
        return false;
    L8:
        boolean r22 = zzayd.zza().zzb(r4.getClass()).zzi(r4);
        if (r5 == false) goto L14;
        if (true == r22) goto L12;
        zzawy r52 = null;
    L13:
        r4.zzb(2, r52, null);
        goto L14
    L12:
        r52 = r4;
    L14:
        return r22;
    }

    private static zzawy zzc(zzawy r6, byte[] r7, int r8, int r9, zzawl r10) {
        if (r9 != 0) goto L4;
        return r6;
    L4:
        zzawy r1 = r6.zzG();
        zzayg r0 = zzayd.zza().zzb(r1.getClass());     // Catch: IndexOutOfBoundsException -> L7 IOException -> L9 zzayp -> L16 zzaxm -> L19
        r0.zzg(r1, r7, 0, r9, new zzavr(r10));     // Catch: IndexOutOfBoundsException -> L7 IOException -> L9 zzayp -> L16 zzaxm -> L19
        r0.zzh(r1);     // Catch: IndexOutOfBoundsException -> L7 IOException -> L9 zzayp -> L16 zzaxm -> L19
        return r1;
    L19:
        e = move-exception;
        throw e;
    L16:
        e = move-exception;
        throw e.zza();
    L9:
        e = move-exception;
        if ((e.getCause() instanceof zzaxm) == false) goto L15;
        throw ((zzaxm) e.getCause());
    L15:
        throw new zzaxm(e);
    L7:
        zzavs$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return null;
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
        return zzayd.zza().zzb(r0).zzb(this, (zzawy) r4);
    }

    public final int hashCode() {
        if (zzE() == true) goto L9;
        int r0 = this.zza;
        if (r0 != 0) goto L10;
        int r02 = zzH();
        this.zza = r02;
        return r02;
    L10:
        return r0;
    L9:
        return zzH();
    }

    public final String toString() {
        return zzaxy.zza(this, super.toString());
    }

    public final boolean zzE() {
        if ((this.zzb & Integer.MIN_VALUE) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final void zzF() {
        this.zzb &= Integer.MAX_VALUE;
    }

    public final zzawy zzG() {
        return (zzawy) zzb(4, null, null);
    }

    public final int zzH() {
        return zzayd.zza().zzb(getClass()).zzc(this);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaxx
    public final /* synthetic */ zzaxw zzI() {
        return (zzawy) zzb(6, null, null);
    }

    public final void zzJ() {
        zzayd.zza().zzb(getClass()).zzh(this);
        zzF();
    }

    public final zzawu zzK() {
        zzawu r0 = (zzawu) zzb(5, null, null);
        r0.zzG(this);
        return r0;
    }

    public final void zzL(int r2) {
        this.zzb = (this.zzb & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaxw
    public final /* synthetic */ zzaxv zzS() {
        return (zzawu) zzb(5, null, null);
    }

    public abstract Object zzb(int r1, Object r2, Object r3);
}
