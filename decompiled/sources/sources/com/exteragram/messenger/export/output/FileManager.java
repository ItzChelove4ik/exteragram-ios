package com.exteragram.messenger.export.output;

import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import kotlin.text.Typography;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;

/* loaded from: classes4.dex */
public abstract class FileManager {
    public static final File defaultSavePath = null;

    static {
        defaultSavePath = new File(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "exteraGram"), "DataExport_" + new SimpleDateFormat("yyyy-MM-dd_HH_mm").format(Calendar.getInstance().getTime()));
    }

    public static void copyAssets() {
        copyAssets("js", false);
        copyAssets("css", false);
        copyAssets("images", true);
    }

    public static void copyAssets(String r12, boolean r13) {
        AssetManager r3 = ApplicationLoader.applicationContext.getAssets();
        String[] r4 = r3.list("extera/export/" + r12 + "/");     // Catch: IOException -> L5
    L7:
        if (r4 != null) goto L10;
        FileLog.e("export: failed to copy assets");
        return;
    L10:
        int r5 = r4.length;
        int r6 = 0;
    L11:
        if (r6 >= r5) goto L18;
        String r7 = r4[r6];
        InputStream r8 = r3.open("extera/export/" + r12 + "/" + r7);     // Catch: IOException -> L15
        File r9 = new File(defaultSavePath + "/" + r12 + "/", r7);     // Catch: IOException -> L15
        r9.createNewFile();     // Catch: IOException -> L15
        AndroidUtilities.copyFile(r8, new FileOutputStream(r9));     // Catch: IOException -> L15
        r6 = r6 + 1;
    L15:
        e = move-exception;
        Log.e("exteraGram", "Failed to copy asset file: " + r7, e);
        Make$Map$$ExternalSyntheticBUOutline0.m("exteraGram assets exception: ", e);
        return;
    L18:
        if (r13 == false) goto L26;
        AndroidUtilities.createEmptyFile(new File(defaultSavePath + "/" + r12, ".nomedia"));
        return;
    L26:
        return;
    L5:
        e = move-exception;
        Log.e("exteraGram", "Failed to get asset file list", e);
        r4 = null;
        goto L7
    }

    public static String fileNameFromUserString(String r7) {
        HashSet r0 = new HashSet();
        r0.add((char) 8206);
        r0.add((char) 8207);
        r0.add((char) 8234);
        r0.add((char) 8235);
        r0.add((char) 8237);
        r0.add((char) 8238);
        r0.add((char) 8294);
        r0.add((char) 8295);
        r0.add('/');
        r0.add('\\');
        r0.add(Character.valueOf(Typography.less));
        r0.add(Character.valueOf(Typography.greater));
        r0.add(':');
        r0.add(Character.valueOf(Typography.quote));
        r0.add('|');
        r0.add('?');
        r0.add('*');
        StringBuilder r1 = new StringBuilder();
        char[] r72 = r7.toCharArray();
        int r2 = r72.length;
        int r3 = 0;
    L4:
        if (r3 >= r2) goto L14;
        char r6 = r72[r3];
        if (r6 >= ' ') goto L8;
    L11:
        r1.append('_');
    L12:
        r3 = r3 + 1;
        goto L4
    L8:
        if (r0.contains(Character.valueOf(r6)) == true) goto L11;
        r1.append(r6);
        goto L12
    L14:
        if (r1.length() != 0) goto L16;
    L19:
        r1.append('_');
    L21:
        return r1.toString();
    L16:
        if (r1.charAt(r1.length() - 1) == ' ') goto L19;
        if (r1.charAt(r1.length() - 1) != '.') goto L21;
        goto L19
    }

    public static String readFileContent(File r3) {
        FileInputStream r0 = new FileInputStream(r3);     // Catch: Exception -> L8
        BufferedReader r32 = new BufferedReader(new InputStreamReader(r0));     // Catch: Exception -> L8
        StringBuilder r1 = new StringBuilder();     // Catch: Exception -> L8
    L3:
        String r2 = r32.readLine();     // Catch: Exception -> L8
        if (r2 == null) goto L6;
        r1.append(r2);     // Catch: Exception -> L8
        goto L3
    L6:
        r32.close();     // Catch: Exception -> L8
        r0.close();     // Catch: Exception -> L8
        return r1.toString();
    L8:
        e = move-exception;
        Log.e("exteraGram", "failed to read: " + e);
        return null;
    }
}
