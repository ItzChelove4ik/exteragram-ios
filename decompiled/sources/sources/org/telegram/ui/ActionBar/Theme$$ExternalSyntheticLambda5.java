package org.telegram.ui.ActionBar;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public final /* synthetic */ class Theme$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ Theme.ThemeAccent f$0;
    public final /* synthetic */ Theme.ThemeInfo f$1;
    public final /* synthetic */ TLRPC.TL_theme f$2;

    public /* synthetic */ Theme$$ExternalSyntheticLambda5(Theme.ThemeAccent r1, Theme.ThemeInfo r2, TLRPC.TL_theme r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        Theme.$r8$lambda$av_bcMFY24nVQKe60itCAqBpiU8(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
