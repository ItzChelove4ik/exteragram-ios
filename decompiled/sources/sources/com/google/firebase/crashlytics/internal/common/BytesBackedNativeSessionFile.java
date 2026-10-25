package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes5.dex */
class BytesBackedNativeSessionFile implements NativeSessionFile {
    private final byte[] bytes;
    private final String dataTransportFilename;
    private final String reportsEndpointFilename;

    public BytesBackedNativeSessionFile(String r1, String r2, byte[] r3) {
        this.dataTransportFilename = r1;
        this.reportsEndpointFilename = r2;
        this.bytes = r3;
    }

    private byte[] asGzippedBytes() {
        if (isEmpty() == false) goto L25;
        return null;
    L25:
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();     // Catch: IOException -> L24
        GZIPOutputStream r2 = new GZIPOutputStream(r0);     // Catch: Throwable -> L11
        r2.write(this.bytes);     // Catch: Throwable -> L13
        r2.finish();     // Catch: Throwable -> L13
        byte[] r3 = r0.toByteArray();     // Catch: Throwable -> L13
        r2.close();     // Catch: Throwable -> L11
        r0.close();     // Catch: IOException -> L24
        return r3;
    L13:
        th = move-exception;
        r2.close();     // Catch: Throwable -> L16
    L18:
        throw th;     // Catch: Throwable -> L11
    L16:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L21
    L23:
        throw th;     // Catch: IOException -> L24
    L21:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L24
    L24:
        return null;
    }

    private boolean isEmpty() {
        byte[] r0 = this.bytes;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.length == 0) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // com.google.firebase.crashlytics.internal.common.NativeSessionFile
    public CrashlyticsReport.FilesPayload.File asFilePayload() {
        byte[] r0 = asGzippedBytes();
        if (r0 != null) goto L7;
        return null;
    L7:
        return CrashlyticsReport.FilesPayload.File.builder().setContents(r0).setFilename(this.dataTransportFilename).build();
    }

    @Override // com.google.firebase.crashlytics.internal.common.NativeSessionFile
    public String getReportsEndpointFilename() {
        return this.reportsEndpointFilename;
    }

    @Override // com.google.firebase.crashlytics.internal.common.NativeSessionFile
    public InputStream getStream() {
        if (isEmpty() == false) goto L7;
        return null;
    L7:
        return new ByteArrayInputStream(this.bytes);
    }
}
