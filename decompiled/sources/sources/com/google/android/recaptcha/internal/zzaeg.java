package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
final class zzaeg {
    public int zza;
    public long zzb;
    public Object zzc;
    public final zzafh zzd;
    public int zze;

    public zzaeg(zzafh r1) {
        r1.getClass();
        this.zzd = r1;
    }

    public static /* synthetic */ String zza(int r2, int r3, byte r4, String r5, String r6) {
        int r0 = String.valueOf(r3).length();
        int r02 = r0 + r4;
        StringBuilder r42 = new StringBuilder(r02 + String.valueOf(r2).length());
        r42.append(r5);
        r42.append(r3);
        r42.append(r6);
        r42.append(r2);
        return r42.toString();
    }
}
