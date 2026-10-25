package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;

/* loaded from: classes4.dex */
final class zzdd implements zzeg {
    private static final zzdn zzlz = null;
    private final zzdn zzly;

    static {
        zzlz = new zzde();
    }

    public zzdd() {
        this(new zzdf(new zzdn[]{zzcf.zzay(), zzby()}));
    }

    private zzdd(zzdn r2) {
        this.zzly = (zzdn) zzci.zza(r2, "messageInfoFactory");
    }

    private static boolean zza(zzdm r1) {
        if (r1.zzcf() != zzcg.zzg.zzkl) goto L6;
        return true;
    L6:
        return false;
    }

    private static zzdn zzby() {
        return (zzdn) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", null).invoke(null, null);
    L5:
        return zzlz;
    }

    @Override // com.google.android.gms.internal.clearcut.zzeg
    public final <T> zzef<T> zzd(Class<T> r8) {
        zzeh.zzf(r8);
        zzdm r1 = this.zzly.zzb(r8);
        if (r1.zzcg() == false) goto L11;
        if (zzcg.class.isAssignableFrom(r8) == false) goto L9;
        return zzdu.zza(zzeh.zzdo(), zzbx.zzap(), r1.zzch());
    L9:
        return zzdu.zza(zzeh.zzdm(), zzbx.zzaq(), r1.zzch());
    L11:
        if (zzcg.class.isAssignableFrom(r8) == true) goto L13;
        boolean r7 = zza(r1);
        zzdw r2 = zzdy.zzcj();
        zzcy r3 = zzcy.zzbv();
        if (r7 == false) goto L23;
        return zzds.zza(r8, r1, r2, r3, zzeh.zzdm(), zzbx.zzaq(), zzdl.zzcc());
    L23:
        return zzds.zza(r8, r1, r2, r3, zzeh.zzdn(), null, zzdl.zzcc());
    L13:
        if (zza(r1) == false) goto L17;
        return zzds.zza(r8, r1, zzdy.zzck(), zzcy.zzbw(), zzeh.zzdo(), zzbx.zzap(), zzdl.zzcd());
    L17:
        return zzds.zza(r8, r1, zzdy.zzck(), zzcy.zzbw(), zzeh.zzdo(), null, zzdl.zzcd());
    }
}
