package com.chaquo.python.android;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Build;
import com.chaquo.python.Python;
import com.chaquo.python.internal.Common;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.ZipFileSystem$$ExternalSyntheticBUOutline0;
import org.json.JSONObject;
import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public class AndroidPlatform extends Python.Platform {
    public static String ABI;
    private static final String[] OBSOLETE_CACHE = null;
    private static final String[] OBSOLETE_FILES = null;
    private AssetManager am;
    private JSONObject buildJson;
    public Application mContext;
    private SharedPreferences sp;

    static {
        OBSOLETE_FILES = new String[]{"app.zip", "requirements.zip", "chaquopy.mp3", "stdlib.mp3", "chaquopy.zip", "lib-dynload", "stdlib.zip", "bootstrap.zip", "stdlib-common.zip", "ticket.txt"};
        OBSOLETE_CACHE = new String[]{"AssetFinder"};
    }

    public AndroidPlatform(Context r7) {
        Application r72 = (Application) r7.getApplicationContext();
        this.mContext = r72;
        int r1 = 0;
        this.sp = r72.getSharedPreferences(Common.ASSET_DIR, 0);
        this.am = this.mContext.getAssets();
        this.buildJson = new JSONObject(streamToString(this.am.open("chaquopy/build.json")));     // Catch: Throwable -> L15
        loadNativeLibs();     // Catch: Throwable -> L15
        String[] r73 = Build.SUPPORTED_ABIS;
        int r0 = r73.length;
    L5:
        if (r1 >= r0) goto L11;
        String r2 = r73[r1];
        this.am.open("chaquopy/" + Common.assetZip(Common.ASSET_STDLIB, r2));     // Catch: IOException -> L9
        ABI = r2;     // Catch: IOException -> L9
    L9:
        r1 = r1 + 1;
    L11:
        if (ABI == null) goto L14;
        return;
    L14:
        throw new RuntimeException("None of this device's ABIs " + Arrays.toString(Build.SUPPORTED_ABIS) + " are supported by this app.");
    L15:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        throw null;
    }

    private void cleanExtractedDir(String r9, JSONObject r10) {
        File r0 = new File(this.mContext.getFilesDir(), "chaquopy/" + r9);
        String[] r1 = r0.list();
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L11;
        String r4 = r1[r3];
        File r5 = new File(r0, r4);
        if (r5.isDirectory() == false) goto L8;
        cleanExtractedDir(r9 + "/" + r4, r10);
    L10:
        r3 = r3 + 1;
        goto L3
    L8:
        if (r10.has(r9 + "/" + r4) == true) goto L10;
        r5.delete();
        goto L10
    }

    private void deleteObsolete(File r7, String[] r8) {
        int r0 = r8.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        deleteRecursive(new File(r7, "chaquopy/" + r8[r1].replace("<abi>", ABI)));
        r1 = r1 + 1;
        goto L3
    }

    private void deleteRecursive(File r5) {
        File[] r0 = r5.listFiles();
        if (r0 == null) goto L7;
        int r1 = r0.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L7;
        deleteRecursive(r0[r2]);
        r2 = r2 + 1;
    L7:
        r5.delete();
    }

    private void extractAsset(JSONObject r8, SharedPreferences.Editor r9, String r10) {
        String r0 = "chaquopy/" + r10;
        File r1 = new File(this.mContext.getFilesDir(), r0);
        String r2 = "asset." + r10;
        String r82 = r8.getString(r10);
        if (r1.exists() == true) goto L5;
    L7:
        r1.delete();
        File r102 = r1.getParentFile();
        if (r102.exists() == true) goto L14;
        r102.mkdirs();
        if (r102.isDirectory() == true) goto L14;
        ZipFileSystem$$ExternalSyntheticBUOutline0.m("Failed to create ", r102);
        return;
    L14:
        InputStream r02 = this.am.open(r0);
        File r3 = new File(r102, r1.getName() + ".tmp");
        r3.delete();
        FileOutputStream r103 = new FileOutputStream(r3);
        transferStream(r02, r103);     // Catch: Throwable -> L22
        r103.close();
        if (r3.renameTo(r1) == false) goto L20;
        r9.putString(r2, r82);
        return;
    L20:
        ZipFileSystem$$ExternalSyntheticBUOutline0.m("Failed to create ", r1);
        return;
    L22:
        th = move-exception;
        r103.close();
        throw th;
    L5:
        if (this.sp.getString(r2, _UrlKt.FRAGMENT_ENCODE_SET).equals(r82) == false) goto L7;
    }

    private void extractAssets(List<String> r11) {
        JSONObject r0 = this.buildJson.getJSONObject("assets");
        HashSet r1 = new HashSet(r11);
        HashSet r2 = new HashSet();
        SharedPreferences.Editor r3 = this.sp.edit();
        Iterator<String> r4 = r0.keys();
    L4:
        if (r4.hasNext() == false) goto L16;
        String r5 = r4.next();
        Iterator<String> r6 = r11.iterator();
    L7:
        if (r6.hasNext() == false) goto L4;
        String r7 = r6.next();
        if (r5.equals(r7) == true) goto L12;
        if (r5.startsWith(r7 + "/") == false) goto L7;
    L12:
        extractAsset(r0, r3, r5);
        r1.remove(r7);
        if (r5.startsWith(r7 + "/") == false) goto L4;
        r2.add(r7);
        goto L4
    L16:
        if (r1.isEmpty() == false) goto L23;
        Iterator r112 = r2.iterator();
    L19:
        if (r112.hasNext() == false) goto L21;
        cleanExtractedDir((String) r112.next(), r0);
        goto L19
    L21:
        r3.apply();
        return;
    L23:
        MVEL$$ExternalSyntheticBUOutline0.m("Failed to find assets: ", r1);
    }

    private void loadNativeLibs() {
        Iterator r0 = Arrays.asList(new String[]{Common.ASSET_DIR, PluginsConstants.PYTHON}).iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        String r2 = (String) r0.next();
        System.loadLibrary("crypto_" + r2);
        System.loadLibrary("ssl_" + r2);
        System.loadLibrary("sqlite3_" + r2);
        goto L4
    L6:
        System.loadLibrary(PluginsConstants.PYTHON + this.buildJson.getString("python_version"));
        System.loadLibrary("chaquopy_java");
    }

    private String streamToString(InputStream r2) {
        BufferedReader r1 = new BufferedReader(new InputStreamReader(r2));
        StringBuilder r22 = new StringBuilder();
    L3:
        String r0 = r1.readLine();
        if (r0 == null) goto L7;
        r22.append(r0);
        r22.append("\n");
        goto L3
    L7:
        return r22.toString();
    }

    private void transferStream(InputStream r3, OutputStream r4) {
        byte[] r2 = new byte[1048576];
        int r0 = r3.read(r2);
    L4:
        if (r0 == (-1)) goto L6;
        r4.write(r2, 0, r0);
        r0 = r3.read(r2);
        goto L4
    }

    public Application getApplication() {
        return this.mContext;
    }

    @Override // com.chaquo.python.Python.Platform
    public String getPath() {
        String r0 = this.mContext.getFilesDir() + "/chaquopy";
        ArrayList r1 = new ArrayList(Arrays.asList(new String[]{Common.assetZip(Common.ASSET_STDLIB, Common.ABI_COMMON), Common.assetZip(Common.ASSET_BOOTSTRAP), "bootstrap-native/" + ABI}));
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r3 = 0;
    L4:
        if (r3 >= r1.size()) goto L9;
        r2 = r2 + r0 + "/" + r1.get(r3);
        if (r3 >= (r1.size() - 1)) goto L8;
        r2 = r2.concat(":");
    L8:
        r3 = r3 + 1;
        goto L4
    L9:
        Collections.addAll(r1, new String[]{Common.ASSET_CACERT});
        deleteObsolete(this.mContext.getFilesDir(), OBSOLETE_FILES);     // Catch: Throwable -> L12
        deleteObsolete(this.mContext.getCacheDir(), OBSOLETE_CACHE);     // Catch: Throwable -> L12
        extractAssets(r1);     // Catch: Throwable -> L12
        return r2;
    L12:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    @Override // com.chaquo.python.Python.Platform
    public void onStart(Python r4) {
        r4.getModule("java.android").callAttr("initialize", new Object[]{this.mContext, this.buildJson, new String[]{Common.ASSET_APP, Common.ASSET_REQUIREMENTS, "stdlib-" + ABI}});
    }

    public native void redirectStdioToLogcat();
}
