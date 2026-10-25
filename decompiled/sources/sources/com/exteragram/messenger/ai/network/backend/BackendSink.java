package com.exteragram.messenger.ai.network.backend;

/* loaded from: classes4.dex */
public interface BackendSink {
    boolean isActive();

    void onCancellable(Runnable r1);

    void onChunk(String r1);

    void onComplete(String r1);

    void onError(int r1, String r2);

    void onThinking();
}
