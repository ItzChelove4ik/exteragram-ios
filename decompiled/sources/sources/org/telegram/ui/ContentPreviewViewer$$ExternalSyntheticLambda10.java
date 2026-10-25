package org.telegram.ui;

import java.util.List;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ContentPreviewViewer$$ExternalSyntheticLambda10 implements RequestDelegate {
    public final /* synthetic */ ContentPreviewViewer f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ TLRPC.TL_messages_getMyStickers f$2;

    public /* synthetic */ ContentPreviewViewer$$ExternalSyntheticLambda10(ContentPreviewViewer r1, List r2, TLRPC.TL_messages_getMyStickers r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        ContentPreviewViewer.$r8$lambda$l5USu0UUxXB5kSkdyAPvp5hn1OM(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
