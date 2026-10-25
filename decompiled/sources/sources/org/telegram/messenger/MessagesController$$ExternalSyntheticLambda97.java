package org.telegram.messenger;

import java.util.Comparator;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda97 implements Comparator {
    public final /* synthetic */ MessagesController f$0;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda97(MessagesController r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return MessagesController.m4512$r8$lambda$A2OZ4YaDWUa0xoVAV91d2wTvs(this.f$0, (TLRPC.Dialog) r1, (TLRPC.Dialog) r2);
    }
}
