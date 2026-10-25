package org.telegram.messenger;

import java.util.Comparator;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda242 implements Comparator {
    public final /* synthetic */ MessagesController f$0;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda242(MessagesController r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return MessagesController.$r8$lambda$cg5v16RFaFWHJmNb6F3JWWrS4Co(this.f$0, (TLRPC.TL_savedReactionTag) r1, (TLRPC.TL_savedReactionTag) r2);
    }
}
