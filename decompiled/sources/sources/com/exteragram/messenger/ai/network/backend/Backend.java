package com.exteragram.messenger.ai.network.backend;

/* loaded from: classes4.dex */
public interface Backend {
    void execute(BackendRequest r1, BackendSink r2);

    int getHistoryCharsLimit();

    int getHistoryMessagesLimit();

    int getMaxOutputTokens();

    float getMaxTemperature();

    boolean supportsImages();
}
