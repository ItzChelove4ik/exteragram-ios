package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class PollVotesAlert$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ PollVotesAlert f$0;
    public final /* synthetic */ Integer[] f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ ArrayList f$3;
    public final /* synthetic */ TLRPC.PollAnswerVoters f$4;

    public /* synthetic */ PollVotesAlert$$ExternalSyntheticLambda0(PollVotesAlert r1, Integer[] r2, int r3, ArrayList r4, TLRPC.PollAnswerVoters r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        PollVotesAlert.$r8$lambda$VyOXvVbh60tbrIOvgxhTNooiX_Y(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
