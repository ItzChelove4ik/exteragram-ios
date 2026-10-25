package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChatMessagesMetadataController$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ ChatMessagesMetadataController f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ ChatMessagesMetadataController$$ExternalSyntheticLambda3(ChatMessagesMetadataController r1, int r2, MessageObject r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        ChatMessagesMetadataController.$r8$lambda$ikVNc0FvbMcnhIs8OFgqQddkooI(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9);
    }
}
