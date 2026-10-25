package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SavedMessagesController$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ SavedMessagesController f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;

    public /* synthetic */ SavedMessagesController$$ExternalSyntheticLambda12(SavedMessagesController r1, TLObject r2, ArrayList r3, TLRPC.TL_error r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SavedMessagesController.$r8$lambda$QUUbn8Zz3sQYft_7bZAsAQB2jY4(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
