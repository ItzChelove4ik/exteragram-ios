package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.internal.mlkit_genai_prompt.zzawu;
import com.google.android.gms.internal.mlkit_genai_prompt.zzawy;

/* loaded from: classes4.dex */
public abstract class zzawu<MessageType extends zzawy<MessageType, BuilderType>, BuilderType extends zzawu<MessageType, BuilderType>> extends zzavn<MessageType, BuilderType> {
    protected zzawy zza;
    private final zzawy zzb;

    public zzawu(MessageType r2) {
        this.zzb = r2;
        if (r2.zzE() == true) goto L6;
        this.zza = r2.zzG();
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Default instance must be immutable.");
        throw null;
    }

    private static void zza(zzawy r2, zzawy r3) {
        zzayd.zza().zzb(r2.getClass()).zzd(r2, r3);
    }

    public final /* bridge */ /* synthetic */ Object clone() {
        return zzE();
    }

    public final void zzB() {
        if (this.zza.zzE() == true) goto L6;
        zzC();
        return;
    }

    public void zzC() {
        zzawy r0 = this.zzb.zzG();
        zza(r0, this.zza);
        this.zza = r0;
    }

    public final zzawu zzE() {
        zzawu r0 = (zzawu) this.zzb.zzb(5, null, null);
        r0.zza = zzF();
        return r0;
    }

    public MessageType zzF() {
        boolean r0 = this.zza.zzE();
        MessageType r1 = (MessageType) this.zza;
        if (r0 == true) goto L5;
        return r1;
    L5:
        r1.zzJ();
        return (MessageType) this.zza;
    }

    public final zzawu zzG(zzawy r3) {
        zzawy r0 = this.zzb;
        if (r0.getClass().isInstance(r3) == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("mergeFrom(MessageLite) can only merge messages of the same type.");
        return null;
    L5:
        if (r0.equals(r3) == false) goto L7;
    L10:
        return this;
    L7:
        if (this.zza.zzE() == true) goto L9;
        zzC();
    L9:
        zza(this.zza, r3);
        goto L10
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaxv
    public /* bridge */ /* synthetic */ zzaxw zzH() {
        return zzF();
    }
}
