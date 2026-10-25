package org.telegram.ui;

import java.util.function.Predicate;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda233 implements Predicate {
    public final /* synthetic */ ChatActivity f$0;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda233(ChatActivity r1) {
        this.f$0 = r1;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object r1) {
        return ChatActivity.$r8$lambda$mTJg310G77nXEeUaZNwiwhUI1LI(this.f$0, (TLRPC.MessageEntity) r1);
    }
}
