package com.google.android.datatransport.runtime;

import android.annotation.SuppressLint;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.runtime.logging.Logging;

/* loaded from: classes4.dex */
public abstract class ForcedSender {
    @SuppressLint({"DiscouragedApi"})
    public static void sendBlocking(Transport<?> r1, Priority r2) {
        if ((r1 instanceof TransportImpl) == false) goto L6;
        TransportContext r12 = ((TransportImpl) r1).getTransportContext().withPriority(r2);
        TransportRuntime.getInstance().getUploader().logAndUpdateState(r12, 1);
        return;
    L6:
        Logging.w("ForcedSender", "Expected instance of `TransportImpl`, got `%s`.", r1);
    }
}
