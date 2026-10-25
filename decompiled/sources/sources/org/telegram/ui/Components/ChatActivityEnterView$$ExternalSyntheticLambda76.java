package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatActivityEnterView$$ExternalSyntheticLambda76 implements Utilities.Callback4 {
    public final /* synthetic */ ChatActivityEnterView f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ Theme.ResourcesProvider f$2;

    public /* synthetic */ ChatActivityEnterView$$ExternalSyntheticLambda76(ChatActivityEnterView r1, long r2, Theme.ResourcesProvider r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback4
    public final void run(Object r9, Object r10, Object r11, Object r12) {
        ChatActivityEnterView.$r8$lambda$x5TKL3ZdLeYIoMtWfqTL8pcrxYU(this.f$0, this.f$1, this.f$2, (TL_iv.RichMessage) r9, (Integer) r10, (Integer) r11, (Boolean) r12);
    }
}
