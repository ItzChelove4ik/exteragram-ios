package com.exteragram.messenger.utils.chats;

import android.os.Bundle;

/* loaded from: classes4.dex */
public interface ForwardContext {

    public static class ForwardParams {
        public boolean noQuote;

        public ForwardParams() {
        }
    }

    ForwardParams getForwardParams();

    boolean isForwardNoQuote();

    default void readForwardParams(Bundle r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        setForwardParams(r3.getBoolean("forward_noquote", false));
    }

    default void setForwardParams(boolean r1) {
        getForwardParams().noQuote = r1;
    }

    default void writeForwardParams(Bundle r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        r2.putBoolean("forward_noquote", isForwardNoQuote());
    }
}
