package com.google.firebase.remoteconfig.internal;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ConfigStorageClient {
    private static final Map<String, ConfigStorageClient> clientInstances = null;
    private final Context context;
    private final String fileName;

    static {
        clientInstances = new HashMap();
    }

    private ConfigStorageClient(Context r1, String r2) {
        this.context = r1;
        this.fileName = r2;
    }

    public static synchronized ConfigStorageClient getInstance(Context r3, String r4) {
        monitor-enter(ConfigStorageClient.class);
        Map<String, ConfigStorageClient> r1 = clientInstances;     // Catch: Throwable -> L7
        if (r1.containsKey(r4) == true) goto L9;
        r1.put(r4, new ConfigStorageClient(r3, r4));     // Catch: Throwable -> L7
    L9:
        ConfigStorageClient r32 = r1.get(r4);     // Catch: Throwable -> L7
        monitor-exit(ConfigStorageClient.class);
        return r32;
    L7:
        th = move-exception;
        throw th;
    }

    public String getFileName() {
        return this.fileName;
    }

    public synchronized ConfigContainer read() {
        monitor-enter(this);
        FileInputStream r1 = this.context.openFileInput(this.fileName);     // Catch: Throwable -> L13 Throwable -> L15
    L28:
        int r2 = r1.available();     // Catch: Throwable -> L11 Throwable -> L25
        byte[] r3 = new byte[r2];     // Catch: Throwable -> L11 Throwable -> L25
        r1.read(r3, 0, r2);     // Catch: Throwable -> L11 Throwable -> L25
        ConfigContainer r0 = ConfigContainer.copyOf(new JSONObject(new String(r3, "UTF-8")));     // Catch: Throwable -> L11 Throwable -> L25
        r1.close();     // Catch: Throwable -> L9
        monitor-exit(this);
        return r0;
    L11:
        Throwable th = th;
    L16:
        if (r1 == null) goto L18;
        r1.close();     // Catch: Throwable -> L9
    L18:
        throw th;     // Catch: Throwable -> L9
    L19:
        if (r1 == null) goto L23;
        r1.close();     // Catch: Throwable -> L9
    L23:
        monitor-exit(this);
        return null;
    L9:
        th = move-exception;
        throw th;
    L15:
        r1 = null;
    L13:
        th = move-exception;
        r1 = null;
        th = th;
        goto L16
    }

    public synchronized Void write(ConfigContainer r4) {
        monitor-enter(this);
        FileOutputStream r0 = this.context.openFileOutput(this.fileName, 0);     // Catch: Throwable -> L9
        r0.write(r4.toString().getBytes("UTF-8"));     // Catch: Throwable -> L11
        r0.close();     // Catch: Throwable -> L9
        monitor-exit(this);
        return null;
    L11:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L9
        throw th;     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }
}
