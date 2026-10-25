package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda234 implements Utilities.Callback {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ TLRPC.StickerSet f$1;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda234(MediaDataController r1, TLRPC.StickerSet r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        MediaDataController.$r8$lambda$1pr0f8yw5PUZ7jCvOLZCjBchtAk(this.f$0, this.f$1, (ArrayList) r2);
    }
}
