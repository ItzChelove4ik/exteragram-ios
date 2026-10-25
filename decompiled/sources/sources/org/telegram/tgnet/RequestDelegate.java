package org.telegram.tgnet;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public interface RequestDelegate {
    void run(TLObject r1, TLRPC.TL_error r2);
}
