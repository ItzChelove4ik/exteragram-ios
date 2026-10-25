package com.google.android.material.color;

import android.content.Context;
import android.content.res.loader.ResourcesLoader;
import android.content.res.loader.ResourcesProvider;
import android.os.ParcelFileDescriptor;
import android.system.Os;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.util.Map;

/* loaded from: classes5.dex */
final class ColorResourcesLoaderCreator {
    private static final String TAG = "ColorResLoaderCreator";

    private ColorResourcesLoaderCreator() {
    }

    public static ResourcesLoader create(Context r5, Map<Integer, Integer> r6) {
        byte[] r52 = ColorResourcesTableCreator.create(r5, r6);     // Catch: Exception -> L12
        Log.i(TAG, "Table created, length: " + r52.length);     // Catch: Exception -> L12
        if (r52.length != 0) goto L52;
        return null;
    L52:
        FileDescriptor r62 = Os.memfd_create("temp.arsc", 0);     // Catch: Throwable -> L40
        if (r62 != null) goto L17;
        Log.w(TAG, "Cannot create memory file descriptor.");     // Catch: Throwable -> L15
        if (r62 == null) goto L14;
        Os.close(r62);     // Catch: Exception -> L12
        return null;
    L14:
        return null;
    L17:
        FileOutputStream r1 = new FileOutputStream(r62);     // Catch: Throwable -> L15
        r1.write(r52);     // Catch: Throwable -> L23
        ParcelFileDescriptor r53 = ParcelFileDescriptor.dup(r62);     // Catch: Throwable -> L23
        ColorResourcesLoaderCreator$$ExternalSyntheticApiModelOutline1.m();     // Catch: Throwable -> L28
        ResourcesLoader r3 = ColorResourcesLoaderCreator$$ExternalSyntheticApiModelOutline0.m();     // Catch: Throwable -> L28
        r3.addProvider(ResourcesProvider.loadFromTable(r53, null));     // Catch: Throwable -> L28
        if (r53 == null) goto L25;
        r53.close();     // Catch: Throwable -> L23
    L25:
        r1.close();     // Catch: Throwable -> L15
        Os.close(r62);     // Catch: Exception -> L12
        return r3;
    L28:
        th = move-exception;
        if (r53 != null) goto L54;
    L34:
        throw th;     // Catch: Throwable -> L23
    L54:
        r53.close();     // Catch: Throwable -> L32
    L32:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L23
    L23:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L37
    L39:
        throw th;     // Catch: Throwable -> L15
    L37:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L15
    L15:
        th = th;
    L42:
        if (r62 == null) goto L44;
        Os.close(r62);     // Catch: Exception -> L12
    L44:
        throw th;     // Catch: Exception -> L12
    L40:
        th = th;
        r62 = null;
    L12:
        e = move-exception;
        Log.e(TAG, "Failed to create the ColorResourcesTableCreator.", e);
        return null;
    }
}
