package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes5.dex */
class FileBackedNativeSessionFile implements NativeSessionFile {
    private final String dataTransportFilename;
    private final File file;
    private final String reportsEndpointFilename;

    public FileBackedNativeSessionFile(String r1, String r2, File r3) {
        this.dataTransportFilename = r1;
        this.reportsEndpointFilename = r2;
        this.file = r3;
    }

    private byte[] asGzippedBytes() {
        byte[] r0 = new byte[8192];
        InputStream r6 = getStream();     // Catch: IOException -> L42
        ByteArrayOutputStream r2 = new ByteArrayOutputStream();     // Catch: Throwable -> L12
        GZIPOutputStream r3 = new GZIPOutputStream(r2);     // Catch: Throwable -> L14
        if (r6 != null) goto L45;
        r3.close();     // Catch: Throwable -> L14
        r2.close();     // Catch: Throwable -> L12
        if (r6 == null) goto L11;
        r6.close();     // Catch: IOException -> L42
    L11:
        return null;
    L45:
        int r4 = r6.read(r0);     // Catch: Throwable -> L19
        if (r4 <= 0) goto L21;
        r3.write(r0, 0, r4);     // Catch: Throwable -> L19
        goto L45
    L21:
        r3.finish();     // Catch: Throwable -> L19
        byte[] r02 = r2.toByteArray();     // Catch: Throwable -> L19
        r3.close();     // Catch: Throwable -> L14
        r2.close();     // Catch: Throwable -> L12
        r6.close();     // Catch: IOException -> L42
        return r02;
    L19:
        th = move-exception;
        r3.close();     // Catch: Throwable -> L28
    L30:
        throw th;     // Catch: Throwable -> L14
    L28:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        r2.close();     // Catch: Throwable -> L33
    L35:
        throw th;     // Catch: Throwable -> L12
    L33:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        if (r6 != null) goto L51;
    L41:
        throw th;     // Catch: IOException -> L42
    L51:
        r6.close();     // Catch: Throwable -> L39
    L39:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L42
    L42:
        return null;
    }

    @Override // com.google.firebase.crashlytics.internal.common.NativeSessionFile
    public CrashlyticsReport.FilesPayload.File asFilePayload() {
        byte[] r0 = asGzippedBytes();
        if (r0 != null) goto L5;
        return null;
    L5:
        return CrashlyticsReport.FilesPayload.File.builder().setContents(r0).setFilename(this.dataTransportFilename).build();
    }

    @Override // com.google.firebase.crashlytics.internal.common.NativeSessionFile
    public String getReportsEndpointFilename() {
        return this.reportsEndpointFilename;
    }

    @Override // com.google.firebase.crashlytics.internal.common.NativeSessionFile
    public InputStream getStream() {
        if (this.file.exists() == true) goto L5;
    L9:
        return null;
    L5:
        if (this.file.isFile() == false) goto L9;
        return new FileInputStream(this.file);
    }
}
