package org.telegram.ui.Components;

import java.util.function.ToIntFunction;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class ReactedUsersListView$$ExternalSyntheticLambda4 implements ToIntFunction {
    public /* synthetic */ ReactedUsersListView$$ExternalSyntheticLambda4() {
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object r1) {
        return ReactedUsersListView.m12295$r8$lambda$CdvtuBtO2AQTL87BPwQ1yaYY((TLRPC.MessagePeerReaction) r1);
    }
}
