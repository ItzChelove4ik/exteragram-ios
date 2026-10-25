package org.webrtc;

import org.webrtc.Logging;

/* loaded from: classes6.dex */
class JNILogging {
    private final Loggable loggable;

    public JNILogging(Loggable r1) {
        this.loggable = r1;
    }

    @CalledByNative
    public void logToInjectable(String r2, Integer r3, String r4) {
        this.loggable.onLogMessage(r2, Logging.Severity.values()[r3.intValue()], r4);
    }
}
