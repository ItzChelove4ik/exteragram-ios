package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda220 implements RequestDelegate {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ LongSparseArray f$3;
    public final /* synthetic */ TLRPC.StickerSet f$4;
    public final /* synthetic */ TLRPC.TL_messages_allStickers f$5;
    public final /* synthetic */ int f$6;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda220(MediaDataController r1, ArrayList r2, int r3, LongSparseArray r4, TLRPC.StickerSet r5, TLRPC.TL_messages_allStickers r6, int r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r10, TLRPC.TL_error r11) {
        MediaDataController.$r8$lambda$Pj0xEgyMXmipuBXfkzAy3XAvHfc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r10, r11);
    }
}
