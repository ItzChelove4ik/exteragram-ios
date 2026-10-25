package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Stories.recorder.ScannedLinkPreview;

/* loaded from: classes7.dex */
public final /* synthetic */ class ScannedLinkPreview$ResolvedLink$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ MessagesController f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ ScannedLinkPreview$ResolvedLink$$ExternalSyntheticLambda0(Utilities.Callback r1, MessagesController r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        ScannedLinkPreview.ResolvedLink.$r8$lambda$reoAkm1DnCABK49Ro2mLuJjhF6g(this.f$0, this.f$1, this.f$2, (Long) r3);
    }
}
