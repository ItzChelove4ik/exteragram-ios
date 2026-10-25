package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
final class zzahi implements zzagv {
    private final zzagx zza;
    private final String zzb;
    private final Object[] zzc;
    private final int zzd;

    public zzahi(zzagx r4, String r5, Object[] r6) {
        this.zza = r4;
        this.zzb = r5;
        this.zzc = r6;
        char r42 = r5.charAt(0);
        if (r42 >= 55296) goto L6;
        this.zzd = r42;
        return;
    L6:
        int r43 = r42 & 8191;
        int r0 = 1;
        int r1 = 13;
    L7:
        int r2 = r0 + 1;
        char r02 = r5.charAt(r0);
        if (r02 < 55296) goto L10;
        r43 = r43 | ((r02 & 8191) << r1);
        r1 = r1 + 13;
        r0 = r2;
        goto L7
    L10:
        this.zzd = r43 | (r02 << r1);
    }

    @Override // com.google.android.recaptcha.internal.zzagv
    public final boolean zza() {
        if ((this.zzd & 2) != 2) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzagv
    public final zzagx zzb() {
        return this.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzagv
    public final int zzc() {
        int r1 = this.zzd;
        if ((r1 & 1) == 0) goto L7;
        return 1;
    L7:
        if ((r1 & 4) != 4) goto L10;
        return 3;
    L10:
        return 2;
    }

    public final String zzd() {
        return this.zzb;
    }

    public final Object[] zze() {
        return this.zzc;
    }
}
