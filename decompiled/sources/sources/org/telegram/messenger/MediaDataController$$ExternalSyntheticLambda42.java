package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda42 implements Utilities.Callback {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ TLRPC.StickerSet f$1;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda42(MediaDataController r1, TLRPC.StickerSet r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        MediaDataController.m4429$r8$lambda$bEgRgMrjYqKX6XULMGLob89k(this.f$0, this.f$1, (ArrayList) r2);
    }
}
