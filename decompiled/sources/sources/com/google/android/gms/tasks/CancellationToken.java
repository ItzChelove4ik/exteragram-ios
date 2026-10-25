package com.google.android.gms.tasks;

/* loaded from: classes5.dex */
public abstract class CancellationToken {
    public CancellationToken() {
    }

    public abstract boolean isCancellationRequested();

    public abstract CancellationToken onCanceledRequested(OnTokenCanceledListener r1);
}
