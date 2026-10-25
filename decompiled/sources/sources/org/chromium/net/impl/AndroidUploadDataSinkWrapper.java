package org.chromium.net.impl;

import org.chromium.net.UploadDataSink;

/* loaded from: classes5.dex */
class AndroidUploadDataSinkWrapper extends UploadDataSink {
    private final android.net.http.UploadDataSink mBackend;

    public AndroidUploadDataSinkWrapper(android.net.http.UploadDataSink r1) {
        this.mBackend = r1;
    }

    @Override // org.chromium.net.UploadDataSink
    public void onReadSucceeded(boolean r1) {
        this.mBackend.onReadSucceeded(r1);
    }

    @Override // org.chromium.net.UploadDataSink
    public void onRewindSucceeded() {
        this.mBackend.onRewindSucceeded();
    }
}
