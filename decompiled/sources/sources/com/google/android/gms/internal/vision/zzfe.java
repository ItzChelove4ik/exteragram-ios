package com.google.android.gms.internal.vision;

import java.io.PrintStream;

/* loaded from: classes5.dex */
public abstract class zzfe {
    private static final zzfd zza = null;
    private static final int zzb = 0;

    public static final class zza extends zzfd {
        public zza() {
        }

        @Override // com.google.android.gms.internal.vision.zzfd
        public final void zza(Throwable r1) {
            r1.printStackTrace();
        }
    }

    static {
        Integer r0 = zza();     // Catch: Throwable -> L14
        if (r0 == null) goto L10;
    L7:
        th = th;
    L16:
        PrintStream r2 = System.err;
        String r3 = zza.class.getName();
        StringBuilder r5 = new StringBuilder(r3.length() + 133);
        r5.append("An error has occurred when initializing the try-with-resources desuguring strategy. The default strategy ");
        r5.append(r3);
        r5.append("will be used. The error is: ");
        r2.println(r5.toString());
        th.printStackTrace(System.err);
        zzfd r1 = new zza();
    L17:
        zza = r1;
        if (r0 != null) goto L20;
        int r02 = 1;
    L21:
        zzb = r02;
        return;
    L20:
        r02 = r0.intValue();
        goto L21
    L5:
        if (r0.intValue() < 19) goto L10;
        r1 = new zzfj();     // Catch: Throwable -> L7
    L23:
    L10:
        if (Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") == true) goto L12;
        r1 = new zzfh();     // Catch: Throwable -> L7
        goto L23
    L12:
        r1 = new zza();     // Catch: Throwable -> L7
    L14:
        th = th;
        r0 = null;
        goto L16
    }

    private static Integer zza() {
        return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
    L5:
        e = move-exception;
        System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
        e.printStackTrace(System.err);
        return null;
    }

    public static void zza(Throwable r1) {
        zza.zza(r1);
    }
}
