package org.telegram.messenger;

import java.util.ArrayList;
import java.util.Comparator;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda233 implements Comparator {
    public final /* synthetic */ ArrayList f$0;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda233(ArrayList r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return MediaDataController.m4382$r8$lambda$Bzjcfvtjj7mVZfuRSlZJcFXIwk(this.f$0, (TLRPC.TL_messages_stickerSet) r1, (TLRPC.TL_messages_stickerSet) r2);
    }
}
