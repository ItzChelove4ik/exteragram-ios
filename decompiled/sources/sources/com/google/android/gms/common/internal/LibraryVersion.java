package com.google.android.gms.common.internal;

import com.google.android.gms.common.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.internal.url._UrlKt;

@Deprecated
/* loaded from: classes4.dex */
public class LibraryVersion {
    private static final GmsLogger zza = null;
    private static final LibraryVersion zzb = null;
    private final ConcurrentHashMap zzc;

    static {
        zza = new GmsLogger("LibraryVersion", _UrlKt.FRAGMENT_ENCODE_SET);
        zzb = new LibraryVersion();
    }

    public LibraryVersion() {
        this.zzc = new ConcurrentHashMap();
    }

    public static LibraryVersion getInstance() {
        return zzb;
    }

    @Deprecated
    public String getVersion(String r9) {
        Preconditions.checkNotEmpty(r9, "Please provide a valid libraryName");
        ConcurrentHashMap r3 = this.zzc;
        if (r3.containsKey(r9) == true) goto L5;
        Properties r32 = new Properties();
        InputStream r4 = null;
        String r42 = null;
        r42 = null;
        r4 = null;
        InputStream r5 = LibraryVersion.class.getResourceAsStream(String.format("/%s.properties", new Object[]{r9}));     // Catch: Throwable -> L17 IOException -> L19
        if (r5 == null) goto L14;
        r32.load(r5);     // Catch: Throwable -> L10 IOException -> L12
        r42 = r32.getProperty("version", null);     // Catch: Throwable -> L10 IOException -> L12
        GmsLogger r33 = zza;     // Catch: Throwable -> L10 IOException -> L12
        StringBuilder r7 = new StringBuilder((String.valueOf(r9).length() + 12) + String.valueOf(r42).length());     // Catch: Throwable -> L10 IOException -> L12
        r7.append(r9);     // Catch: Throwable -> L10 IOException -> L12
        r7.append(" version is ");     // Catch: Throwable -> L10 IOException -> L12
        r7.append(r42);     // Catch: Throwable -> L10 IOException -> L12
        r33.v("LibraryVersion", r7.toString());     // Catch: Throwable -> L10 IOException -> L12
    L35:
    L23:
        if (r5 == null) goto L25;
        IOUtils.closeQuietly(r5);
    L25:
        if (r42 != null) goto L27;
        zza.d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version is expected during Google internal testing where locally-built libraries are used");
        r42 = "UNKNOWN";
    L27:
        this.zzc.put(r9, r42);
        return r42;
    L14:
        GmsLogger r1 = zza;     // Catch: Throwable -> L10 IOException -> L12
        StringBuilder r6 = new StringBuilder(String.valueOf(r9).length() + 43);     // Catch: Throwable -> L10 IOException -> L12
        r6.append("Failed to get app version for libraryName: ");     // Catch: Throwable -> L10 IOException -> L12
        r6.append(r9);     // Catch: Throwable -> L10 IOException -> L12
        r1.w("LibraryVersion", r6.toString());     // Catch: Throwable -> L10 IOException -> L12
    L12:
        e = e;
        String r34 = r42;
        r4 = r5;
    L21:
        GmsLogger r52 = zza;     // Catch: Throwable -> L17
        StringBuilder r72 = new StringBuilder(String.valueOf(r9).length() + 43);     // Catch: Throwable -> L17
        r72.append("Failed to get app version for libraryName: ");     // Catch: Throwable -> L17
        r72.append(r9);     // Catch: Throwable -> L17
        r52.e("LibraryVersion", r72.toString(), e);     // Catch: Throwable -> L17
        r5 = r4;
        r42 = r34;
    L10:
        th = th;
        r4 = r5;
    L29:
        if (r4 == null) goto L31;
        IOUtils.closeQuietly(r4);
    L31:
        throw th;
    L19:
        e = e;
        r34 = null;
    L17:
        th = th;
        goto L29
    L5:
        return (String) r3.get(r9);
    }
}
