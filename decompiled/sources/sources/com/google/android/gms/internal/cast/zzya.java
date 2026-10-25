package com.google.android.gms.internal.cast;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.internal.cast.zzya;
import com.google.android.gms.internal.cast.zzyd;

/* loaded from: classes4.dex */
public abstract class zzya<MessageType extends zzyd<MessageType, BuilderType>, BuilderType extends zzya<MessageType, BuilderType>> extends zzwy<MessageType, BuilderType> {
    protected zzyd zza;
    private final zzyd zzb;

    public zzya(MessageType r2) {
        this.zzb = r2;
        if (r2.zzv() == true) goto L6;
        this.zza = r2.zzy();
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Default instance must be immutable.");
        throw null;
    }

    private static void zza(Object r2, Object r3) {
        zzzp.zza().zzb(r2.getClass()).zzd(r2, r3);
    }

    public final /* bridge */ /* synthetic */ Object clone() {
        return zzs();
    }

    public final void zzp() {
        if (this.zza.zzv() == true) goto L6;
        zzq();
        return;
    }

    public void zzq() {
        zzyd r0 = this.zzb.zzy();
        zza(r0, this.zza);
        this.zza = r0;
    }

    public final zzya zzs() {
        zzya r0 = (zzya) this.zzb.zzb(5, null, null);
        r0.zza = zzt();
        return r0;
    }

    public MessageType zzt() {
        boolean r0 = this.zza.zzv();
        MessageType r1 = (MessageType) this.zza;
        if (r0 == true) goto L5;
        return r1;
    L5:
        r1.zzA();
        return (MessageType) this.zza;
    }

    public final MessageType zzu() {
        MessageType r1 = (MessageType) zzt();
        if (r1.zzr() == false) goto L6;
        return r1;
    L6:
        throw new zzaac(r1);
    }

    public final zzya zzv(zzyd r2) {
        if (this.zzb.equals(r2) == false) goto L5;
    L8:
        return this;
    L5:
        if (this.zza.zzv() == true) goto L7;
        zzq();
    L7:
        zza(this.zza, r2);
        goto L8
    }

    @Override // com.google.android.gms.internal.cast.zzzh
    public /* bridge */ /* synthetic */ zzzi zzw() {
        return zzt();
    }
}
