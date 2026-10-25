package org.chromium.net;

import java.io.Closeable;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public abstract class UploadDataProvider implements Closeable {
    public UploadDataProvider() {
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public abstract long getLength();

    public abstract void read(UploadDataSink r1, ByteBuffer r2);

    public abstract void rewind(UploadDataSink r1);
}
