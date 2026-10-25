package com.exteragram.messenger.ai.network;

/* loaded from: classes.dex */
public interface GenerationCallback {
    void onChunk(String r1);

    void onError(int r1, String r2);

    void onResponse(String r1);

    default void onThinking() {
    }
}
