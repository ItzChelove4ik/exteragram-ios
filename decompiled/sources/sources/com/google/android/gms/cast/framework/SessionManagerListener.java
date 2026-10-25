package com.google.android.gms.cast.framework;

import com.google.android.gms.cast.framework.Session;

/* loaded from: classes4.dex */
public interface SessionManagerListener<T extends Session> {
    void onSessionEnded(T r1, int r2);

    void onSessionEnding(T r1);

    void onSessionResumeFailed(T r1, int r2);

    void onSessionResumed(T r1, boolean r2);

    void onSessionResuming(T r1, String r2);

    void onSessionStartFailed(T r1, int r2);

    void onSessionStarted(T r1, String r2);

    void onSessionStarting(T r1);

    void onSessionSuspended(T r1, int r2);
}
