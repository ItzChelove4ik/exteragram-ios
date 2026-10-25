package com.google.android.gms.internal.play_billing;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.internal.play_billing.zzfe;
import com.google.android.gms.internal.play_billing.zzfi;

/* loaded from: classes5.dex */
public abstract class zzfe<MessageType extends zzfi<MessageType, BuilderType>, BuilderType extends zzfe<MessageType, BuilderType>> extends zzdr<MessageType, BuilderType> {
    protected zzfi zza;
    private final zzfi zzb;

    public zzfe(MessageType r2) {
        this.zzb = r2;
        if (r2.zzz() == true) goto L6;
        this.zza = r2.zzo();
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Default instance must be immutable.");
        throw null;
    }

    private static void zza(Object r2, Object r3) {
        zzgs.zza().zzb(r2.getClass()).zzg(r2, r3);
    }

    public final /* bridge */ /* synthetic */ Object clone() {
        return zzc();
    }

    public final zzfe zzc() {
        zzfe r0 = (zzfe) this.zzb.zzb(5, null, null);
        r0.zza = zzf();
        return r0;
    }

    public final zzfe zzd(zzfi r2) {
        if (this.zzb.equals(r2) == false) goto L5;
    L8:
        return this;
    L5:
        if (this.zza.zzz() == true) goto L7;
        zzj();
    L7:
        zza(this.zza, r2);
        goto L8
    }

    public final MessageType zze() {
        MessageType r1 = (MessageType) zzf();
        if (r1.zzk() == false) goto L6;
        return r1;
    L6:
        throw new zzhg(r1);
    }

    public MessageType zzf() {
        boolean r0 = this.zza.zzz();
        MessageType r1 = (MessageType) this.zza;
        if (r0 == true) goto L5;
        return r1;
    L5:
        r1.zzu();
        return (MessageType) this.zza;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgk
    public /* bridge */ /* synthetic */ zzgl zzg() {
        return zzf();
    }

    public final void zzi() {
        if (this.zza.zzz() == true) goto L6;
        zzj();
        return;
    }

    public void zzj() {
        zzfi r0 = this.zzb.zzo();
        zza(r0, this.zza);
        this.zza = r0;
    }
}
