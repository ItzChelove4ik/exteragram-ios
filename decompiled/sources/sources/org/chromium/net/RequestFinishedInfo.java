package org.chromium.net;

import java.util.concurrent.Executor;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public abstract class RequestFinishedInfo {

    public static abstract class Listener {
        private final Executor mExecutor;

        public Listener(Executor r1) {
            if (r1 == null) goto L6;
            this.mExecutor = r1;
            return;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("Executor must not be null");
            throw null;
        }

        public abstract Executor getExecutor();

        public abstract void onRequestFinished(RequestFinishedInfo r1);
    }

    public static abstract class Metrics {
        public Metrics() {
        }
    }

    public RequestFinishedInfo() {
    }
}
