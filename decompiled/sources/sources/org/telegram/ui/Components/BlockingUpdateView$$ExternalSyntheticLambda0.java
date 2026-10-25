package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class BlockingUpdateView$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ BlockingUpdateView f$0;

    public /* synthetic */ BlockingUpdateView$$ExternalSyntheticLambda0(BlockingUpdateView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        BlockingUpdateView.$r8$lambda$ctrkebBI0wGQfMja8oof30dWrGE(this.f$0, (TLRPC.TL_help_appUpdate) r1, (TLRPC.TL_error) r2);
    }
}
