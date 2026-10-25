package com.google.android.gms.internal.measurement;

import com.exteragram.messenger.math.MathFormat$$ExternalSyntheticBackportWithForwarding0;
import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzah implements zzao {
    private final Double zza;

    public zzah(Double r3) {
        if (r3 != null) goto L6;
        this.zza = Double.valueOf(Double.NaN);
        return;
    L6:
        this.zza = r3;
    }

    public final boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof zzah) == true) goto L10;
        return false;
    L10:
        return this.zza.equals(((zzah) r2).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        return zzc();
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final String zzc() {
        Double r4 = this.zza;
        if (Double.isNaN(r4.doubleValue()) == false) goto L7;
        return "NaN";
    L7:
        if (Double.isInfinite(r4.doubleValue()) == true) goto L9;
        BigDecimal r42 = MathFormat$$ExternalSyntheticBackportWithForwarding0.m(BigDecimal.valueOf(r4.doubleValue()));
        DecimalFormat r0 = new DecimalFormat("0E0");
        r0.setRoundingMode(RoundingMode.HALF_UP);
        if (r42.scale() <= 0) goto L18;
        int r1 = r42.precision();
    L19:
        r0.setMinimumFractionDigits(r1 - 1);
        String r02 = r0.format(r42);
        int r2 = r02.indexOf("E");
        if (r2 <= 0) goto L32;
        int r22 = Integer.parseInt(r02.substring(r2 + 1));
        if (r22 < 0) goto L24;
    L25:
        if (r22 < 0) goto L31;
        if (r22 >= 21) goto L31;
    L29:
        return r42.toPlainString();
    L31:
        return r02.replace("E-", "e-").replace("E", "e+");
    L24:
        if (r22 > (-7)) goto L29;
    L32:
        return r02;
    L18:
        r1 = r42.scale();
        goto L19
    L9:
        if (r4.doubleValue() <= 0.0d) goto L12;
        return "Infinity";
    L12:
        return "-Infinity";
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final zzao zzcG(String r1, zzg r2, List r3) {
        if ("toString".equals(r1) == true) goto L5;
        FieldSet$$ExternalSyntheticBUOutline0.m("%s.%s is not a function.", new Object[]{zzc(), r1});
        return null;
    L5:
        return new zzas(zzc());
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Double zzd() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Boolean zze() {
        Double r6 = this.zza;
        boolean r1 = false;
        if (Double.isNaN(r6.doubleValue()) == true) goto L8;
        if (r6.doubleValue() == 0.0d) goto L8;
        r1 = true;
    L8:
        return Boolean.valueOf(r1);
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Iterator zzf() {
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final zzao zzt() {
        return new zzah(this.zza);
    }
}
