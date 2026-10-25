package org.vosk.android;

/* loaded from: classes6.dex */
public interface RecognitionListener {
    void onError(Exception r1);

    void onFinalResult(String r1);

    void onPartialResult(String r1);

    void onResult(String r1);

    void onTimeout();
}
