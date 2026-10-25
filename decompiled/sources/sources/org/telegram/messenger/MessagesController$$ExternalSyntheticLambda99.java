package org.telegram.messenger;

import java.util.Comparator;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda99 implements Comparator {
    public final /* synthetic */ MessagesController f$0;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda99(MessagesController r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return MessagesController.$r8$lambda$InaQneyu4YxA11fl_2ckA7GJS7k(this.f$0, (TLRPC.Update) r1, (TLRPC.Update) r2);
    }
}
