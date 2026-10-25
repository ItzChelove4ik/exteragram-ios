package org.telegram.messenger;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$4$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ TLRPC.messages_AvailableEffects f$1;

    public /* synthetic */ MessagesController$4$$ExternalSyntheticLambda2(int r1, TLRPC.messages_AvailableEffects r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.AnonymousClass4.m4657$r8$lambda$lPMUGMTQ2PorSpUbpqFTXhYm6M(this.f$0, this.f$1);
    }
}
