package org.telegram.ui.Delegates;

/* loaded from: classes7.dex */
public final /* synthetic */ class MemberRequestsDelegate$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ MemberRequestsDelegate f$0;

    public /* synthetic */ MemberRequestsDelegate$$ExternalSyntheticLambda6(MemberRequestsDelegate r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.loadMembers();
    }
}
