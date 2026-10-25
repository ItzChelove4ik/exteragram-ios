package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda430 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.TL_theme f$1;
    public final /* synthetic */ Theme.ThemeInfo f$2;
    public final /* synthetic */ TLRPC.TL_inputThemeSettings f$3;
    public final /* synthetic */ Theme.ThemeAccent f$4;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda430(MessagesController r1, TLRPC.TL_theme r2, Theme.ThemeInfo r3, TLRPC.TL_inputThemeSettings r4, Theme.ThemeAccent r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        MessagesController.m4536$r8$lambda$JnGs7baPF5jtXawIvcFM71qPfk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
