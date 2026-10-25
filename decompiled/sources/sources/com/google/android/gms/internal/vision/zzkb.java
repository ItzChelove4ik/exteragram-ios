package com.google.android.gms.internal.vision;

/* loaded from: classes5.dex */
final class zzkb implements zzlf {
    private static final zzkl zzb = null;
    private final zzkl zza;

    static {
        zzb = new zzka();
    }

    public zzkb() {
        this(new zzkd(new zzkl[]{zzjc.zza(), zza()}));
    }

    private zzkb(zzkl r2) {
        this.zza = (zzkl) zzjf.zza(r2, "messageInfoFactory");
    }

    private static zzkl zza() {
        return (zzkl) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", null).invoke(null, null);
    L5:
        return zzb;
    }

    private static boolean zza(zzki r1) {
        if (r1.zza() != zzkz.zza) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.internal.vision.zzlf
    public final <T> zzlc<T> zza(Class<T> r8) {
        zzle.zza(r8);
        zzki r1 = this.zza.zzb(r8);
        if (r1.zzb() == false) goto L11;
        if (zzjb.class.isAssignableFrom(r8) == false) goto L9;
        return zzkq.zza(zzle.zzc(), zzir.zza(), r1.zzc());
    L9:
        return zzkq.zza(zzle.zza(), zzir.zzb(), r1.zzc());
    L11:
        if (zzjb.class.isAssignableFrom(r8) == false) goto L19;
        if (zza(r1) == false) goto L17;
        return zzko.zza(r8, r1, zzku.zzb(), zzju.zzb(), zzle.zzc(), zzir.zza(), zzkj.zzb());
    L17:
        return zzko.zza(r8, r1, zzku.zzb(), zzju.zzb(), zzle.zzc(), null, zzkj.zzb());
    L19:
        if (zza(r1) == false) goto L23;
        return zzko.zza(r8, r1, zzku.zza(), zzju.zza(), zzle.zza(), zzir.zzb(), zzkj.zza());
    L23:
        return zzko.zza(r8, r1, zzku.zza(), zzju.zza(), zzle.zzb(), null, zzkj.zza());
    }
}
