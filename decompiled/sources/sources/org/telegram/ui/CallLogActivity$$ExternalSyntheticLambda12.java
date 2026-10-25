package org.telegram.ui;

import java.util.function.Function;
import org.telegram.messenger.DialogObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class CallLogActivity$$ExternalSyntheticLambda12 implements Function {
    public /* synthetic */ CallLogActivity$$ExternalSyntheticLambda12() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object r1) {
        return Long.valueOf(DialogObject.getPeerDialogId((TLRPC.Peer) r1));
    }
}
