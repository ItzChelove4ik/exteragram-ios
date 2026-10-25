package org.telegram.ui.Gifts;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.UniversalAdapter;

/* loaded from: classes7.dex */
public final /* synthetic */ class SendGiftSheet$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ SendGiftSheet f$0;

    public /* synthetic */ SendGiftSheet$$ExternalSyntheticLambda0(SendGiftSheet r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        this.f$0.fillItems((ArrayList) r1, (UniversalAdapter) r2);
    }
}
