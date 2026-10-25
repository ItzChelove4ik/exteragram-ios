package org.telegram.ui.Components;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickersDialogs$$ExternalSyntheticLambda8 implements Utilities.Callback3Return {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ TLRPC.TL_messages_stickerSet f$2;

    public /* synthetic */ StickersDialogs$$ExternalSyntheticLambda8(int r1, Context r2, TLRPC.TL_messages_stickerSet r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback3Return
    public final Object run(Object r7, Object r8, Object r9) {
        return StickersDialogs.m13137$r8$lambda$o7KvI6jticMZZAlTouYSxsHIg(this.f$0, this.f$1, this.f$2, r7, (TLRPC.Document) r8, (Boolean) r9);
    }
}
