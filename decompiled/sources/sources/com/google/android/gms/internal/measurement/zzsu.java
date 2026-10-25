package com.google.android.gms.internal.measurement;

import android.net.Uri;
import android.os.Process;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes4.dex */
abstract class zzsu {
    private static final AtomicLong zza = null;

    static {
        zza = new AtomicLong();
    }

    public static Uri zza(Uri r11) {
        int r0 = Process.myPid();
        long r1 = Thread.currentThread().getId();
        long r3 = System.currentTimeMillis();
        long r5 = zza.getAndIncrement();
        int r7 = String.valueOf(r0).length();
        int r8 = String.valueOf(r1).length();
        int r9 = String.valueOf(r3).length();
        int r72 = ((((r7 + 15) + r8) + 1) + r9) + 1;
        StringBuilder r82 = new StringBuilder(r72 + String.valueOf(r5).length());
        r82.append(".mobstore_tmp-");
        r82.append(r0);
        r82.append("-");
        r82.append(r1);
        r82.append("-");
        r82.append(r3);
        r82.append("-");
        r82.append(r5);
        String r02 = r82.toString();
        return r11.buildUpon().path(String.valueOf(r11.getPath()).concat(r02)).build();
    }
}
