package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public abstract class PendingResult<R extends Result> {

    public interface StatusListener {
        void onComplete(Status r1);
    }

    public PendingResult() {
    }

    public abstract void addStatusListener(StatusListener r1);

    public abstract R await(long r1, TimeUnit r3);

    public abstract void cancel();

    public abstract void setResultCallback(ResultCallback<? super R> r1);
}
