package org.telegram.ui.ActionBar;

import java.io.File;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public final /* synthetic */ class Theme$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Theme.OverrideWallpaperInfo f$0;
    public final /* synthetic */ File f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ TLRPC.Document f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ Theme$$ExternalSyntheticLambda0(Theme.OverrideWallpaperInfo r1, File r2, int r3, boolean r4, TLRPC.Document r5, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Theme.$r8$lambda$gvgZQHt_aksR0W7l4hMLnrufm7A(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
