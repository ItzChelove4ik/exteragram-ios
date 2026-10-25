package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class PollVotesAlert$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ PollVotesAlert f$0;
    public final /* synthetic */ Integer[] f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ ArrayList f$4;
    public final /* synthetic */ TLRPC.PollAnswerVoters f$5;

    public /* synthetic */ PollVotesAlert$$ExternalSyntheticLambda4(PollVotesAlert r1, Integer[] r2, int r3, TLObject r4, ArrayList r5, TLRPC.PollAnswerVoters r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PollVotesAlert.$r8$lambda$pP9FYE42IrkER1vnleHVSCNdqH8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
