package com.exteragram.messenger.export.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public abstract class AndroidSDUtils {
    private static final String EMULATED_STORAGE_TARGET = null;
    private static final String EXTERNAL_STORAGE = null;

    @SuppressLint({"SdCardPath"})
    private static final String[] KNOWN_PHYSICAL_PATHS = null;
    private static final String SECONDARY_STORAGES = null;

    static {
        EXTERNAL_STORAGE = System.getenv("EXTERNAL_STORAGE");
        SECONDARY_STORAGES = System.getenv("SECONDARY_STORAGE");
        EMULATED_STORAGE_TARGET = System.getenv("EMULATED_STORAGE_TARGET");
        KNOWN_PHYSICAL_PATHS = new String[]{"/storage/sdcard0", "/storage/sdcard1", "/storage/extsdcard", "/storage/sdcard0/external_sdcard", "/mnt/extsdcard", "/mnt/sdcard/external_sd", "/mnt/sdcard/ext_sd", "/mnt/external_sd", "/mnt/media_rw/sdcard1", "/removable/microsd", "/mnt/emmc", "/storage/external_SD", "/storage/ext_sd", "/storage/removable/sdcard1", "/data/sdext", "/data/sdext2", "/data/sdext3", "/data/sdext4", "/sdcard1", "/sdcard2", "/storage/microsd"};
    }

    private static String[] getAllSecondaryStorages() {
        String r0 = SECONDARY_STORAGES;
        if (TextUtils.isEmpty(r0) == true) goto L7;
        return r0.split(File.pathSeparator);
    L7:
        return new String[0];
    }

    private static String getEmulatedStorageTarget() {
        String r0 = Environment.getExternalStorageDirectory().getAbsolutePath();
        String r1 = File.separator;
        String r02 = r0.split(r1)[r0.length - 1];
        if (TextUtils.isEmpty(r02) == false) goto L5;
    L7:
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        if (TextUtils.isEmpty(r02) == false) goto L13;
        return EMULATED_STORAGE_TARGET;
    L13:
        return EMULATED_STORAGE_TARGET + r1 + r02;
    L5:
        if (TextUtils.isDigitsOnly(r02) == false) goto L7;
        goto L7
    }

    private static File[] getExternalFilesDirs(Context r1) {
        return r1.getExternalFilesDirs(null);
    }

    private static Set<String> getExternalStorage(Context r7) {
        HashSet r0 = new HashSet();
        File[] r72 = getExternalFilesDirs(r7);
        int r1 = r72.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L10;
        File r4 = r72[r3];
        if (r4 == null) goto L9;
        String r42 = r4.getAbsolutePath();
        String r43 = r42.substring(9, r42.indexOf("Android/data"));
        String r44 = r43.substring(r43.indexOf("/storage/") + 1);
        String r45 = r44.substring(0, r44.indexOf("/"));
        if (r45.equals("emulated") == true) goto L9;
        r0.add(r45);
    L9:
        r3 = r3 + 1;
        goto L3
    L10:
        return r0;
    }

    public static String[] getStorageDirectories(Context r2) {
        HashSet r0 = new HashSet();
        if (TextUtils.isEmpty(EMULATED_STORAGE_TARGET) == true) goto L5;
        r0.add(getEmulatedStorageTarget());
    L6:
        Collections.addAll(r0, getAllSecondaryStorages());
        return (String[]) r0.toArray(new String[r0.size()]);
    L5:
        r0.addAll(getExternalStorage(r2));
        goto L6
    }
}
