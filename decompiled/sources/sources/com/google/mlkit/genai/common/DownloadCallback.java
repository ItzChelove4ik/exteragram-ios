package com.google.mlkit.genai.common;

/* loaded from: classes5.dex */
public interface DownloadCallback {
    default void onDownloadCompleted() {
    }

    void onDownloadFailed(GenAiException r1);

    void onDownloadProgress(long r1);

    void onDownloadStarted(long r1);
}
