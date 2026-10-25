package org.telegram.tgnet;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public interface ResultCallback<T> {
    void onComplete(T r1);

    default void onError(Throwable r1) {
    }

    default void onError(TLRPC.TL_error r1) {
    }
}
