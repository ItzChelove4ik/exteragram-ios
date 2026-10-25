package com.google.android.gms.internal.measurement;

import android.os.Build;
import dalvik.system.VMStack;

/* loaded from: classes4.dex */
public final class zzaaj extends zzaad {
    private static final boolean zza = false;
    private static final boolean zzb = false;
    private static final zzaac zzc = null;

    /* renamed from: com.google.android.gms.internal.measurement.zzaaj$1, reason: invalid class name */
    public class AnonymousClass1 extends zzaac {
        public AnonymousClass1() {
        }

        @Override // com.google.android.gms.internal.measurement.zzaac
        public zzyc zzb(Class<?> r1, int r2) {
            return zzyc.zza;
        }
    }

    abstract class zza {
        public static boolean zza() {
            return zzaaj.zzp();
        }
    }

    static {
        zza = zza.zza();
        String r0 = Build.FINGERPRINT;
        boolean r1 = true;
        if (r0 != null) goto L5;
    L8:
        zzb = r1;
        zzc = new AnonymousClass1();
        return;
    L5:
        if ("robolectric".equals(r0) == true) goto L8;
        r1 = false;
        goto L8
    }

    public zzaaj() {
    }

    public static boolean zzp() {
        Class.forName("dalvik.system.VMStack").getMethod("getStackClass2", null);     // Catch: Throwable -> L4
        String r0 = zzq();     // Catch: Throwable -> L4
        return zza.class.getName().equals(r0);
    L4:
        return false;
    }

    public static String zzq() {
        return VMStack.getStackClass2().getName();
    L4:
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzaad
    public zzaac zzc() {
        return zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzaad
    public zzzf zze(String r1) {
        return zzaao.zze(r1);
    }

    @Override // com.google.android.gms.internal.measurement.zzaad
    public zzaat zzg() {
        return zzaap.zza();
    }
}
