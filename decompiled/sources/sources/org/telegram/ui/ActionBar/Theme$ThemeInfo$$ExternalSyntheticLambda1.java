package org.telegram.ui.ActionBar;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public final /* synthetic */ class Theme$ThemeInfo$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ Theme.ThemeInfo f$0;
    public final /* synthetic */ Theme.ThemeInfo f$1;

    public /* synthetic */ Theme$ThemeInfo$$ExternalSyntheticLambda1(Theme.ThemeInfo r1, Theme.ThemeInfo r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        Theme.ThemeInfo.m5826$r8$lambda$8BL_TRFk6MdGRJ7NNF1qoRaA1k(this.f$0, this.f$1, r2, r3);
    }
}
