package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.internal.measurement.zzadp;
import com.google.android.gms.internal.measurement.zzadu;
import java.io.IOException;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class zzadp<MessageType extends zzadu<MessageType, BuilderType>, BuilderType extends zzadp<MessageType, BuilderType>> extends zzaca<MessageType, BuilderType> {
    protected zzadu zza;
    private final zzadu zzb;

    public zzadp(MessageType r2) {
        this.zzb = r2;
        if (r2.zzch() == true) goto L6;
        this.zza = r2.zzck();
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Default instance must be immutable.");
        throw null;
    }

    private static void zza(Object r2, Object r3) {
        zzafl.zza().zzb(r2.getClass()).zzd(r2, r3);
    }

    public final /* bridge */ /* synthetic */ Object clone() {
        return zzbb();
    }

    @Override // com.google.android.gms.internal.measurement.zzaca
    public final /* bridge */ /* synthetic */ zzaca zzaT(byte[] r2, int r3, int r4) {
        zzadf r32 = zzadf.zza;
        int r33 = zzacf.$r8$clinit;
        zzbf(r2, 0, r4, zzadf.zza);
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zzaca
    public final /* bridge */ /* synthetic */ zzaca zzaU(byte[] r1, int r2, int r3, zzadf r4) {
        zzbf(r1, 0, r3, r4);
        return this;
    }

    public final void zzaY() {
        if (this.zza.zzch() == true) goto L6;
        zzaZ();
        return;
    }

    public void zzaZ() {
        zzadu r0 = this.zzb.zzck();
        zza(r0, this.zza);
        this.zza = r0;
    }

    public final zzadp zzbb() {
        zzadp r0 = (zzadp) this.zzb.zzg(5, null, null);
        r0.zza = zzbc();
        return r0;
    }

    public MessageType zzbc() {
        boolean r0 = this.zza.zzch();
        MessageType r1 = (MessageType) this.zza;
        if (r0 == true) goto L5;
        return r1;
    L5:
        r1.zzcm();
        return (MessageType) this.zza;
    }

    public final MessageType zzbd() {
        MessageType r1 = (MessageType) zzbc();
        if (r1.zzcJ() == false) goto L6;
        return r1;
    L6:
        throw new zzafy(r1);
    }

    public final zzadp zzbe(zzadu r2) {
        if (this.zzb.equals(r2) == false) goto L5;
    L8:
        return this;
    L5:
        if (this.zza.zzch() == true) goto L7;
        zzaZ();
    L7:
        zza(this.zza, r2);
        goto L8
    }

    public final zzadp zzbf(byte[] r9, int r10, int r11, zzadf r12) {
        if (this.zza.zzch() == true) goto L16;
        zzaZ();
    L16:
        zzafl.zza().zzb(this.zza.getClass()).zzj(this.zza, r9, 0, r11, new zzacg(r12));     // Catch: IOException -> L8 IndexOutOfBoundsException -> L11 zzaeh -> L13
        return this;
    L13:
        e = move-exception;
        throw e;
    L8:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Reading from byte array should not throw IOException.", e);
        return null;
    L11:
        zzmw$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzafb
    public /* bridge */ /* synthetic */ zzafc zzbg() {
        return zzbc();
    }

    @Override // com.google.android.gms.internal.measurement.zzafd
    public final boolean zzcJ() {
        return zzadu.zzcD(this.zza, false);
    }
}
