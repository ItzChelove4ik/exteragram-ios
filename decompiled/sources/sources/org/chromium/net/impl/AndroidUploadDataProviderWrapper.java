package org.chromium.net.impl;

import android.net.http.UploadDataProvider;
import android.net.http.UploadDataSink;
import java.nio.ByteBuffer;
import java.util.Objects;

/* loaded from: classes5.dex */
class AndroidUploadDataProviderWrapper extends UploadDataProvider {
    private final org.chromium.net.UploadDataProvider mBackend;

    public AndroidUploadDataProviderWrapper(org.chromium.net.UploadDataProvider r2) {
        Objects.requireNonNull(r2, "Invalid UploadDataProvider.");
        this.mBackend = r2;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.mBackend.close();
    }

    public long getLength() {
        return this.mBackend.getLength();
    }

    public void read(UploadDataSink r2, ByteBuffer r3) {
        AndroidUploadDataSinkWrapper r0 = new AndroidUploadDataSinkWrapper(r2);
        this.mBackend.read(r0, r3);
    }

    public void rewind(UploadDataSink r2) {
        AndroidUploadDataSinkWrapper r0 = new AndroidUploadDataSinkWrapper(r2);
        this.mBackend.rewind(r0);
    }
}
