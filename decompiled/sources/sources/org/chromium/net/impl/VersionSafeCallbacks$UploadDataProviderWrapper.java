package org.chromium.net.impl;

import java.nio.ByteBuffer;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UploadDataSink;

/* loaded from: classes5.dex */
public final class VersionSafeCallbacks$UploadDataProviderWrapper extends UploadDataProvider {
    private final UploadDataProvider mWrappedProvider;

    public VersionSafeCallbacks$UploadDataProviderWrapper(UploadDataProvider r1) {
        this.mWrappedProvider = r1;
    }

    @Override // org.chromium.net.UploadDataProvider, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.mWrappedProvider.close();
    }

    @Override // org.chromium.net.UploadDataProvider
    public long getLength() {
        return this.mWrappedProvider.getLength();
    }

    @Override // org.chromium.net.UploadDataProvider
    public void read(UploadDataSink r1, ByteBuffer r2) {
        this.mWrappedProvider.read(r1, r2);
    }

    @Override // org.chromium.net.UploadDataProvider
    public void rewind(UploadDataSink r1) {
        this.mWrappedProvider.rewind(r1);
    }
}
