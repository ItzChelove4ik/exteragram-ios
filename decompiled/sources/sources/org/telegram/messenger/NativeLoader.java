package org.telegram.messenger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes.dex */
public class NativeLoader {
    private static final String LIB_NAME = "tmessages.49";
    private static final String LIB_SO_NAME = "libtmessages.49.so";
    private static final int LIB_VERSION = 49;
    private static final String LOCALE_LIB_SO_NAME = "libtmessages.49loc.so";
    public static StringBuilder log = null;
    private static volatile boolean nativeLoaded = false;

    static {
        log = new StringBuilder();
    }

    public NativeLoader() {
    }

    public static String getAbiFolder() {
        String r0 = "mips";
        String r5 = "armeabi";
        String r7 = Build.CPU_ABI;     // Catch: Exception -> L25
        if (r7.equalsIgnoreCase("x86_64") == false) goto L7;
        r0 = "x86_64";
    L27:
        r5 = r0;
    L29:
        String r02 = System.getProperty("os.arch");
        if (r02 == null) goto L35;
        if (r02.contains("686") == false) goto L35;
        return "x86";
    L35:
        return r5;
    L7:
        if (r7.equalsIgnoreCase("arm64-v8a") == false) goto L10;
        r0 = "arm64-v8a";
        goto L27
    L10:
        if (r7.equalsIgnoreCase("armeabi-v7a") == false) goto L13;
        r0 = "armeabi-v7a";
        goto L27
    L13:
        if (r7.equalsIgnoreCase("armeabi") == false) goto L16;
    L14:
        r0 = "armeabi";
        goto L27
    L16:
        if (r7.equalsIgnoreCase("x86") == false) goto L19;
        r0 = "x86";
        goto L27
    L19:
        if (r7.equalsIgnoreCase("mips") == true) goto L27;
        if (BuildVars.LOGS_ENABLED == false) goto L14;
        FileLog.e("Unsupported arch: " + r7);     // Catch: Exception -> L25
    L25:
        e = move-exception;
        FileLog.e(e);
        goto L29
    }

    private static File getNativeLibraryDir(Context r4) {
        if (r4 != null) goto L15;
    L8:
        File r1 = null;
    L9:
        if (r1 != null) goto L12;
        r1 = new File(r4.getApplicationInfo().dataDir, "lib");
    L12:
        if (r1.isDirectory() == false) goto L14;
        return r1;
    L14:
        return null;
    L15:
        r1 = new File((String) ApplicationInfo.class.getField("nativeLibraryDir").get(r4.getApplicationInfo()));     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        th.printStackTrace();
        goto L8
    }

    @SuppressLint({"UnsafeDynamicallyLoadedCode"})
    public static synchronized void initNativeLibs(Context r10) {
        monitor-enter(NativeLoader.class);
    L40:
        th = move-exception;
        throw th;
    L5:
        if (nativeLoaded == false) goto L50;
        monitor-exit(NativeLoader.class);
        return;
    L50:
        System.loadLibrary(LIB_NAME);     // Catch: Throwable -> L13 Error -> L15
        nativeLoaded = true;     // Catch: Throwable -> L13 Error -> L15
        if (BuildVars.LOGS_ENABLED == false) goto L17;
        FileLog.d("loaded normal lib");     // Catch: Throwable -> L13 Error -> L15
    L17:
        monitor-exit(NativeLoader.class);
        return;
    L13:
        th = move-exception;
        th.printStackTrace();     // Catch: Throwable -> L40
        StringBuilder r0 = log;     // Catch: Throwable -> L40
        r0.append("176: ");     // Catch: Throwable -> L40
        r0.append(th);     // Catch: Throwable -> L40
        r0.append("\n");     // Catch: Throwable -> L40
    L48:
        System.loadLibrary(LIB_NAME);     // Catch: Throwable -> L40 Error -> L42
        nativeLoaded = true;     // Catch: Throwable -> L40 Error -> L42
    L44:
        monitor-exit(NativeLoader.class);
        return;
    L42:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L40
        StringBuilder r02 = log;     // Catch: Throwable -> L40
        r02.append("184: ");     // Catch: Throwable -> L40
        r02.append(e);     // Catch: Throwable -> L40
        r02.append("\n");     // Catch: Throwable -> L40
    L15:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L13
        StringBuilder r5 = log;     // Catch: Throwable -> L13
        r5.append("128: ");     // Catch: Throwable -> L13
        r5.append(e);     // Catch: Throwable -> L13
        r5.append("\n");     // Catch: Throwable -> L13
        String r4 = getAbiFolder();     // Catch: Throwable -> L13
        File r52 = new File(r10.getFilesDir(), "lib");     // Catch: Throwable -> L13
        r52.mkdirs();     // Catch: Throwable -> L13
        File r6 = new File(r52, LOCALE_LIB_SO_NAME);     // Catch: Throwable -> L13
        if (r6.exists() == false) goto L31;
    L24:
        e = move-exception;
        StringBuilder r8 = log;     // Catch: Throwable -> L13
        r8.append(e);     // Catch: Throwable -> L13
        r8.append("\n");     // Catch: Throwable -> L13
        FileLog.e(e);     // Catch: Throwable -> L13
        r6.delete();     // Catch: Throwable -> L13
        goto L31
    L22:
        if (BuildVars.LOGS_ENABLED == false) goto L26;
        FileLog.d("Load local lib");     // Catch: Throwable -> L13 Error -> L24
    L26:
        System.load(r6.getAbsolutePath());     // Catch: Throwable -> L13 Error -> L24
        nativeLoaded = true;     // Catch: Throwable -> L13 Error -> L24
    L28:
        return;
    L31:
        if (BuildVars.LOGS_ENABLED == false) goto L34;
        FileLog.e("Library not found, arch = " + r4);     // Catch: Throwable -> L13
        StringBuilder r1 = log;     // Catch: Throwable -> L13
        r1.append("Library not found, arch = " + r4);     // Catch: Throwable -> L13
        r1.append("\n");     // Catch: Throwable -> L13
    L34:
        if (loadFromZip(r10, r52, r6, r4) == false) goto L48;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SuppressLint({"UnsafeDynamicallyLoadedCode", "SetWorldReadable"})
    private static boolean loadFromZip(Context r5, File r6, File r7, String r8) {
        File[] r62 = r6.listFiles();     // Catch: Exception -> L7
        int r2 = r62.length;     // Catch: Exception -> L7
        int r3 = 0;
    L4:
        if (r3 >= r2) goto L9;
        r62[r3].delete();     // Catch: Exception -> L7
        r3 = r3 + 1;
    L9:
        InputStream r63 = null;
        ZipFile r22 = new ZipFile(r5.getApplicationInfo().sourceDir);     // Catch: Throwable -> L37 Exception -> L39
        ZipEntry r52 = r22.getEntry("lib/" + r8 + "/" + LIB_SO_NAME);     // Catch: Throwable -> L17 Exception -> L19
        if (r52 == null) goto L36;
        InputStream r64 = r22.getInputStream(r52);     // Catch: Throwable -> L17 Exception -> L19
        FileOutputStream r53 = new FileOutputStream(r7);     // Catch: Throwable -> L17 Exception -> L19
        byte[] r82 = new byte[4096];     // Catch: Throwable -> L17 Exception -> L19
    L14:
        int r0 = r64.read(r82);     // Catch: Throwable -> L17 Exception -> L19
        if (r0 <= 0) goto L21;
        Thread.yield();     // Catch: Throwable -> L17 Exception -> L19
        r53.write(r82, 0, r0);     // Catch: Throwable -> L17 Exception -> L19
        goto L14
    L21:
        r53.close();     // Catch: Throwable -> L17 Exception -> L19
        r7.setReadable(true, false);     // Catch: Throwable -> L17 Exception -> L19
        r7.setExecutable(true, false);     // Catch: Throwable -> L17 Exception -> L19
        r7.setWritable(true);     // Catch: Throwable -> L17 Exception -> L19
        System.load(r7.getAbsolutePath());     // Catch: Throwable -> L17 Exception -> L19 Error -> L24
        nativeLoaded = true;     // Catch: Throwable -> L17 Exception -> L19 Error -> L24
    L78:
        r64.close();     // Catch: Exception -> L28
    L68:
        r22.close();     // Catch: Exception -> L32
    L34:
        return true;
    L32:
        e = move-exception;
        FileLog.e(e);
    L28:
        e = move-exception;
        FileLog.e(e);
    L24:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L17 Exception -> L19
        goto L78
    L36:
        throw new Exception("Unable to find file in apk:lib/" + r8 + "/" + LIB_NAME);     // Catch: Throwable -> L17 Exception -> L19
    L19:
        e = e;
    L41:
        FileLog.e(e);     // Catch: Throwable -> L17
        if (0 != 0) goto L64;
    L47:
        if (r22 != null) goto L72;
    L52:
        return false;
    L72:
        r22.close();     // Catch: Exception -> L50
    L50:
        e = move-exception;
        FileLog.e(e);
        goto L52
    L64:
        r63.close();     // Catch: Exception -> L45
    L45:
        e = move-exception;
        FileLog.e(e);
        goto L47
    L17:
        th = th;
        ZipFile r23 = r2;
    L53:
        if (0 != 0) goto L76;
    L58:
        if (r23 == null) goto L85;
        r23.close();     // Catch: Exception -> L61
        throw th;
    L61:
        e = move-exception;
        FileLog.e(e);
        throw th;
    L85:
        throw th;
    L76:
        r63.close();     // Catch: Exception -> L56
    L56:
        e = move-exception;
        FileLog.e(e);
    L39:
        e = e;
        r22 = null;
    L37:
        th = th;
        r23 = null;
    L7:
        e = move-exception;
        FileLog.e(e);
        goto L9
    }

    public static boolean loaded() {
        return nativeLoaded;
    }
}
