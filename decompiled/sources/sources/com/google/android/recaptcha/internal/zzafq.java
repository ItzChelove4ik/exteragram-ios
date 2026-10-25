package com.google.android.recaptcha.internal;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.recaptcha.internal.zzafq;
import com.google.android.recaptcha.internal.zzafv;

/* loaded from: classes5.dex */
public abstract class zzafq<MessageType extends zzafv<MessageType, BuilderType>, BuilderType extends zzafq<MessageType, BuilderType>> extends zzaeb<MessageType, BuilderType> {
    protected zzafv zza;
    private final zzafv zzb;

    public zzafq(MessageType r2) {
        this.zzb = r2;
        if (r2.zzz() == true) goto L6;
        this.zza = r2.zzC();
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Default instance must be immutable.");
        throw null;
    }

    private static void zza(zzafv r2, zzafv r3) {
        zzahg.zza().zzb(r2.getClass()).zzd(r2, r3);
    }

    public final /* bridge */ /* synthetic */ Object clone() {
        return zzn();
    }

    @Override // com.google.android.recaptcha.internal.zzagy
    public final boolean zzab() {
        return zzafv.zzY(this.zza, false);
    }

    public final void zzk() {
        if (this.zza.zzz() == true) goto L6;
        zzl();
        return;
    }

    public void zzl() {
        zzafv r0 = this.zzb.zzC();
        zza(r0, this.zza);
        this.zza = r0;
    }

    public final zzafq zzn() {
        zzafq r0 = (zzafq) this.zzb.zzf(5, null, null);
        r0.zza = zzo();
        return r0;
    }

    public MessageType zzo() {
        boolean r0 = this.zza.zzz();
        MessageType r1 = (MessageType) this.zza;
        if (r0 == true) goto L5;
        return r1;
    L5:
        r1.zzE();
        return (MessageType) this.zza;
    }

    public final MessageType zzp() {
        MessageType r1 = (MessageType) zzo();
        if (r1.zzab() == false) goto L6;
        return r1;
    L6:
        throw new zzahy(r1);
    }

    public final zzafq zzq(zzafv r2) {
        if (r2 == null) goto L9;
        if (this.zzb.getClass().isInstance(r2) == true) goto L9;
        f$$ExternalSyntheticBUOutline1.m("mergeFrom(MessageLite) can only merge messages of the same type.");
        return null;
    L9:
        if (this.zzb.equals(r2) == false) goto L11;
    L14:
        return this;
    L11:
        if (this.zza.zzz() == true) goto L13;
        zzl();
    L13:
        zza(this.zza, r2);
        goto L14
    }

    @Override // com.google.android.recaptcha.internal.zzagw
    public /* bridge */ /* synthetic */ zzagx zzr() {
        return zzo();
    }
}
