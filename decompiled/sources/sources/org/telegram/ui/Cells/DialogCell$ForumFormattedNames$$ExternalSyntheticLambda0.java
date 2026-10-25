package org.telegram.ui.Cells;

import java.util.function.ToIntFunction;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.DialogCell;

/* loaded from: classes3.dex */
public final /* synthetic */ class DialogCell$ForumFormattedNames$$ExternalSyntheticLambda0 implements ToIntFunction {
    public /* synthetic */ DialogCell$ForumFormattedNames$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object r1) {
        return DialogCell.ForumFormattedNames.$r8$lambda$uICq3jFQRNqxUSEcmTlTge6AEXo((TLRPC.TL_forumTopic) r1);
    }
}
