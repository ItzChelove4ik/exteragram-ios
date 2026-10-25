package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzu extends zzco {
    private int zza;
    private String zzb;
    private String zzc;
    private Boolean zzd;
    private List zze;
    private zzcp zzf;
    private Long zzg;
    private Long zzh;
    private List zzi;
    private Double zzj;
    private Double zzk;
    private List zzl;
    private List zzm;
    private String zzn;
    private Map zzo;
    private byte zzp;

    public zzu() {
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzco
    public final zzco zza(int r1) {
        this.zza = r1;
        this.zzp = 1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzco
    public final zzco zzb(String r1) {
        this.zzb = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzco
    public final zzco zzc(String r1) {
        this.zzc = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzco
    public final zzco zzd(boolean r1) {
        this.zzd = Boolean.valueOf(r1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzco
    public final zzco zze(List r1) {
        this.zze = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzco
    public final zzco zzf(zzcp r1) {
        this.zzf = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzco
    public final zzco zzg(long r1) {
        this.zzg = Long.valueOf(r1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzco
    public final zzco zzh(long r1) {
        this.zzh = Long.valueOf(r1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzco
    public final zzco zzi(List r1) {
        this.zzi = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzco
    public final zzco zzj(double r1) {
        this.zzj = Double.valueOf(r1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzco
    public final zzco zzk(double r1) {
        this.zzk = Double.valueOf(r1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzco
    public final zzco zzl(List r1) {
        this.zzl = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzco
    public final zzco zzm(List r1) {
        this.zzm = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzco
    public final zzco zzn(String r1) {
        this.zzn = "FunctionCall";
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzco
    public final zzco zzo(Map r1) {
        this.zzo = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzco
    public final zzcp zzp() {
        if (this.zzp != 1) goto L44;
        int r5 = this.zza;
        String r6 = this.zzb;
        String r7 = this.zzc;
        Boolean r8 = this.zzd;
        List r1 = this.zze;
        zzcp r10 = this.zzf;
        Long r11 = this.zzg;
        Long r12 = this.zzh;
        List r2 = this.zzi;
        Double r4 = this.zzj;
        Double r9 = this.zzk;
        List r13 = this.zzl;
        List r14 = this.zzm;
        String r15 = this.zzn;
        Map r0 = this.zzo;
        Parcelable.Creator<zzcp> r16 = zzcp.CREATOR;
        if (r2 == null) goto L21;
        if (r2.isEmpty() == true) goto L21;
        if (r0 == null) goto L18;
        Iterator r162 = r2.iterator();
    L11:
        if (r162.hasNext() == false) goto L21;
        String r3 = (String) r162.next();
        if (r0.containsKey(r3) == true) goto L11;
        StringBuilder r22 = new StringBuilder(String.valueOf(r3).length() + 50);
        r22.append("Required property '");
        r22.append(r3);
        r22.append("' is not present in properties.");
        throw new IllegalArgumentException(r22.toString());
    L18:
        f$$ExternalSyntheticBUOutline1.m("Required property is set but properties are null or empty.");
        return null;
    L21:
        if (r1 != null) goto L23;
        zzml r17 = null;
    L24:
        if (r2 != null) goto L26;
        zzml r23 = null;
    L27:
        if (r13 != null) goto L29;
        zzml r21 = null;
    L30:
        if (r14 != null) goto L32;
        zzml r222 = null;
    L33:
        if (r0 != null) goto L35;
    L36:
        zzml r172 = r17;
        zzml r132 = r23;
        Bundle r24 = null;
    L43:
        return new zzcp(r5, r6, r7, r8, r172, r10, r11, r12, r132, null, null, r4, r9, null, null, null, r21, r222, r15, r24);
    L35:
        if (r0.isEmpty() == true) goto L36;
        Bundle r32 = new Bundle();
        Iterator r02 = ((zzmo) r0).zze().iterator();
    L39:
        if (r02.hasNext() == false) goto L41;
        Map.Entry r133 = (Map.Entry) r02.next();
        zzcp r142 = (zzcp) r133.getValue();
        String r134 = (String) r133.getKey();
        Iterator r26 = r02;
        Bundle r03 = new Bundle();
        zzml r173 = r17;
        Parcel r18 = Parcel.obtain();
        zzcq.zza(r142, r18, 0);
        r03.putByteArray("schema_data", r18.marshall());
        r18.recycle();
        r32.putBundle(r134, r03);
        r02 = r26;
        r17 = r173;
        r23 = r23;
        goto L39
    L41:
        r172 = r17;
        r132 = r23;
        r24 = r32;
        goto L43
    L32:
        r222 = zzml.zzq(r14);
        goto L33
    L29:
        r21 = zzml.zzq(r13);
        goto L30
    L26:
        r23 = zzml.zzq(r2);
        goto L27
    L23:
        r17 = zzml.zzq(r1);
        goto L24
    L44:
        Segment$$ExternalSyntheticBUOutline1.m("Missing required properties: type");
        return null;
    }
}
