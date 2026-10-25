package com.google.android.gms.flags;

@Deprecated
/* loaded from: classes4.dex */
public abstract class Flag<T> {
    private final int zza;
    private final String zzb;
    private final T zzc;

    @Deprecated
    public static class BooleanFlag extends Flag<Boolean> {
        public BooleanFlag(int r2, String r3, Boolean r4) {
            super(r2, r3, r4, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ Flag(int r1, String r2, Object r3, zza r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        Singletons.flagRegistry().zza(this);
    }

    @Deprecated
    public static BooleanFlag define(int r1, String r2, Boolean r3) {
        return new BooleanFlag(r1, r2, r3);
    }
}
