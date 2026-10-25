package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class TopicsFragment$2$$ExternalSyntheticLambda5 implements Utilities.Callback {
    public final /* synthetic */ TopicsFragment.AnonymousClass2 f$0;
    public final /* synthetic */ TLRPC.TL_messages_invitedUsers f$1;
    public final /* synthetic */ int[] f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ ArrayList f$4;
    public final /* synthetic */ long f$5;

    public /* synthetic */ TopicsFragment$2$$ExternalSyntheticLambda5(TopicsFragment.AnonymousClass2 r1, TLRPC.TL_messages_invitedUsers r2, int[] r3, int r4, ArrayList r5, long r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r9) {
        TopicsFragment.AnonymousClass2.$r8$lambda$a5_ejfyVFlLHt_jetnweJw9aNXk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (TLRPC.TL_messages_invitedUsers) r9);
    }
}
