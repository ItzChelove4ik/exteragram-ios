package org.telegram.messenger.voip;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallMessagesController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ GroupCallMessage f$0;

    public /* synthetic */ GroupCallMessagesController$$ExternalSyntheticLambda0(GroupCallMessage r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.notifyStateUpdate();
    }
}
