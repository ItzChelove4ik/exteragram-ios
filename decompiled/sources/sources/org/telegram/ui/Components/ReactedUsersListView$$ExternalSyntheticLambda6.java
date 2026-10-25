package org.telegram.ui.Components;

import java.util.function.ToIntFunction;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class ReactedUsersListView$$ExternalSyntheticLambda6 implements ToIntFunction {
    public /* synthetic */ ReactedUsersListView$$ExternalSyntheticLambda6() {
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object r1) {
        return ReactedUsersListView.$r8$lambda$unLT4OaqL4UEFS31xTXl82zjVas((TLRPC.MessagePeerReaction) r1);
    }
}
