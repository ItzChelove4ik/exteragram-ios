package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.cast.MediaError;
import kotlin.jvm.internal.ByteCompanionObject;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzef extends zzem {
    private ParcelFileDescriptor zza;
    private int zzb;
    private int zzc;
    private int zzd;
    private String zze;
    private byte zzf;
    private int zzg;
    private zzwh zzh;

    public zzef() {
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzem
    public final zzem zza(ParcelFileDescriptor r1) {
        this.zza = r1;
        return this;
    }

    public final zzem zzb(float r1) {
        this.zzf = (byte) (this.zzf | 1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzem
    public final zzem zzc(int r1) {
        this.zzb = 1;
        this.zzf = (byte) (this.zzf | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzem
    public final zzem zzd(int r1) {
        this.zzc = MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
        this.zzf = (byte) (this.zzf | 4);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzem
    public final zzem zze(int r1) {
        this.zzd = 1;
        this.zzf = (byte) (this.zzf | 8);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzem
    public final zzem zzf(int r1) {
        this.zzf = (byte) (this.zzf | 16);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzem
    public final zzem zzg(int r1) {
        this.zzf = (byte) (this.zzf | 32);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzem
    public final zzem zzh(int r1) {
        this.zzf = (byte) (this.zzf | 64);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzem
    public final zzem zzi(String r1) {
        this.zze = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzem
    public final zzem zzj(int r1) {
        this.zzf = (byte) (this.zzf | ByteCompanionObject.MIN_VALUE);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzem
    public final zzeo zzk() {
        if (this.zzf != (-1)) goto L11;
        ParcelFileDescriptor r5 = this.zza;
        if (r5 == null) goto L11;
        int r15 = this.zzg;
        if (r15 == 0) goto L11;
        return new zzeg(null, r5, null, 0.0f, this.zzb, this.zzc, this.zzd, null, 0, 0, 0, r15, this.zze, null, 0, this.zzh, null);
    L11:
        StringBuilder r1 = new StringBuilder();
        if (this.zza != null) goto L15;
        r1.append(" cortanaStateFileDescriptorToWrite");
    L15:
        if ((this.zzf & 1) != 0) goto L18;
        r1.append(" temperature");
    L18:
        if ((this.zzf & 2) != 0) goto L21;
        r1.append(" topK");
    L21:
        if ((this.zzf & 4) != 0) goto L24;
        r1.append(" targetReplyLengthMax");
    L24:
        if ((this.zzf & 8) != 0) goto L27;
        r1.append(" numSamples");
    L27:
        if ((this.zzf & 16) != 0) goto L30;
        r1.append(" preferredImageWidth");
    L30:
        if ((this.zzf & 32) != 0) goto L33;
        r1.append(" preferredImageHeight");
    L33:
        if ((this.zzf & 64) != 0) goto L36;
        r1.append(" numSoftTokens");
    L36:
        if (this.zzg != 0) goto L39;
        r1.append(" cortanaType");
    L39:
        if ((this.zzf & ByteCompanionObject.MIN_VALUE) != 0) goto L41;
        r1.append(" tokenOffset");
    L41:
        Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r1.toString()));
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzem
    public final zzem zzl(int r1) {
        this.zzg = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzem
    public final zzem zzm(zzwh r1) {
        this.zzh = r1;
        return this;
    }
}
