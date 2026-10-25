package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import com.google.common.base.Optional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/* loaded from: classes4.dex */
public abstract class zzlf {
    private static volatile Optional zza;

    static {
    }

    public static Optional zza(Context r16) {
        Optional r0 = zza;
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(zzlf.class);
        Optional r02 = zza;     // Catch: Throwable -> L13
        if (r02 != null) goto L79;
        String r03 = Build.TYPE;     // Catch: Throwable -> L13
        String r2 = Build.TAGS;     // Catch: Throwable -> L13
        int r3 = zzlg.$r8$clinit;     // Catch: Throwable -> L13
        if (r03.equals("eng") == true) goto L16;
        if (r03.equals("userdebug") == true) goto L16;
    L20:
        r02 = Optional.absent();     // Catch: Throwable -> L13
    L76:
        zza = r02;     // Catch: Throwable -> L13
    L16:
        if (r2.contains("dev-keys") == true) goto L22;
        if (r2.contains("test-keys") == false) goto L20;
    L22:
        if (zzky.zza() == true) goto L24;
    L26:
        Context r22 = r16;
    L27:
        StrictMode.ThreadPolicy r32 = StrictMode.allowThreadDiskReads();     // Catch: Throwable -> L13
        StrictMode.allowThreadDiskWrites();     // Catch: Throwable -> L34
        char r4 = 0;
        File r04 = new File(r22.getDir("phenotype_hermetic", 0), "overrides.txt");     // Catch: Throwable -> L34 RuntimeException -> L37
    L32:
        if (r04.exists() == false) goto L36;
        Optional r05 = Optional.of(r04);     // Catch: Throwable -> L34
    L40:
        if (r05.isPresent() == false) goto L74;
        File r06 = (File) r05.get();     // Catch: Throwable -> L34
        BufferedReader r8 = new BufferedReader(new InputStreamReader(new FileInputStream(r06)));     // Catch: Throwable -> L34 IOException -> L65
        SimpleArrayMap r9 = new SimpleArrayMap();     // Catch: Throwable -> L49
        HashMap r10 = new HashMap();     // Catch: Throwable -> L49
    L44:
        String r11 = r8.readLine();     // Catch: Throwable -> L49
        if (r11 == null) goto L61;
        String[] r12 = r11.split(" ", 3);     // Catch: Throwable -> L49
        if (r12.length != 3) goto L48;
        String r13 = new String(r12[r4]);     // Catch: Throwable -> L49
        String r112 = Uri.decode(new String(r12[1]));     // Catch: Throwable -> L49
        String r15 = (String) r10.get(r12[2]);     // Catch: Throwable -> L49
        if (r15 != null) goto L57;
        String r14 = new String(r12[2]);     // Catch: Throwable -> L49
        r15 = Uri.decode(r14);     // Catch: Throwable -> L49
        if (r15.length() < 1024) goto L56;
        if (r15 != r14) goto L57;
    L56:
        r10.put(r14, r15);     // Catch: Throwable -> L49
    L57:
        SimpleArrayMap r42 = (SimpleArrayMap) r9.get(r13);     // Catch: Throwable -> L49
        if (r42 != null) goto L60;
        r42 = new SimpleArrayMap();     // Catch: Throwable -> L49
        r9.put(r13, r42);     // Catch: Throwable -> L49
    L60:
        r42.put(r112, r15);     // Catch: Throwable -> L49
        r4 = 0;
        goto L44
    L48:
        StringBuilder r142 = new StringBuilder(r11.length() + 9);     // Catch: Throwable -> L49
        r142.append("Invalid: ");     // Catch: Throwable -> L49
        r142.append(r11);     // Catch: Throwable -> L49
        Log.e("HermeticFileOverrides", r142.toString());     // Catch: Throwable -> L49
        goto L44
    L61:
        String r07 = r06.toString();     // Catch: Throwable -> L49
        String r23 = r22.getPackageName();     // Catch: Throwable -> L49
        StringBuilder r102 = new StringBuilder((r07.length() + 28) + String.valueOf(r23).length());     // Catch: Throwable -> L49
        r102.append("Parsed ");     // Catch: Throwable -> L49
        r102.append(r07);     // Catch: Throwable -> L49
        r102.append(" for Android package ");     // Catch: Throwable -> L49
        r102.append(r23);     // Catch: Throwable -> L49
        Log.w("HermeticFileOverrides", r102.toString());     // Catch: Throwable -> L49
        zzle r08 = new zzle(r9);     // Catch: Throwable -> L49
        r8.close();     // Catch: Throwable -> L34 IOException -> L65
        r02 = Optional.of(r08);     // Catch: Throwable -> L34
    L75:
        StrictMode.setThreadPolicy(r32);     // Catch: Throwable -> L13
    L49:
        th = move-exception;
        r8.close();     // Catch: Throwable -> L69
        throw th;     // Catch: Throwable -> L34 IOException -> L65
    L69:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L34 IOException -> L65
        throw th;     // Catch: Throwable -> L34 IOException -> L65
    L65:
        e = move-exception;
        throw new RuntimeException(e);     // Catch: Throwable -> L34
    L74:
        r02 = Optional.absent();     // Catch: Throwable -> L34
        goto L75
    L36:
        r05 = Optional.absent();     // Catch: Throwable -> L34
    L37:
        e = move-exception;
        Log.e("HermeticFileOverrides", "no data dir", e);     // Catch: Throwable -> L34
        r05 = Optional.absent();     // Catch: Throwable -> L34
    L34:
        th = move-exception;
        StrictMode.setThreadPolicy(r32);     // Catch: Throwable -> L13
        throw th;     // Catch: Throwable -> L13
    L24:
        if (r16.isDeviceProtectedStorage() == true) goto L26;
        r22 = r16.createDeviceProtectedStorageContext();     // Catch: Throwable -> L13
    L79:
        monitor-exit(zzlf.class);     // Catch: Throwable -> L13
        return r02;
    L13:
        th = move-exception;
        throw th;
    }
}
