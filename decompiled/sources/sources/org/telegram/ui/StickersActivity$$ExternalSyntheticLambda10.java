package org.telegram.ui;

import java.util.Comparator;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class StickersActivity$$ExternalSyntheticLambda10 implements Comparator {
    public final /* synthetic */ StickersActivity f$0;

    public /* synthetic */ StickersActivity$$ExternalSyntheticLambda10(StickersActivity r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return StickersActivity.$r8$lambda$epCMCTLMBkxBVdFrCyoe1qrvRjM(this.f$0, (TLRPC.TL_messages_stickerSet) r1, (TLRPC.TL_messages_stickerSet) r2);
    }
}
