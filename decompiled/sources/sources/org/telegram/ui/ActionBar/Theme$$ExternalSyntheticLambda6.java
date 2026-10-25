package org.telegram.ui.ActionBar;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public final /* synthetic */ class Theme$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ TLObject f$0;
    public final /* synthetic */ Theme.ThemeAccent f$1;
    public final /* synthetic */ Theme.ThemeInfo f$2;
    public final /* synthetic */ TLRPC.TL_theme f$3;

    public /* synthetic */ Theme$$ExternalSyntheticLambda6(TLObject r1, Theme.ThemeAccent r2, Theme.ThemeInfo r3, TLRPC.TL_theme r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Theme.m5795$r8$lambda$YaQiMDK_ThvoQBl6AnzeXT7s8(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
