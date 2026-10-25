package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda163 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ LongSparseArray f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ MessagesController.SendAsPeersInfo f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda163(MessagesController r1, LongSparseArray r2, long r3, MessagesController.SendAsPeersInfo r5, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        MessagesController.$r8$lambda$fN9fORX8jmtogVSeD9L54H6j8G4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10);
    }
}
