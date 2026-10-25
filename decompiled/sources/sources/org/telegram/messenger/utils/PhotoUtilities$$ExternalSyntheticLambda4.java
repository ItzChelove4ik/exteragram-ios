package org.telegram.messenger.utils;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.Components.ImageUpdater;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoUtilities$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ TLRPC.InputFile f$0;
    public final /* synthetic */ TLRPC.InputFile f$1;
    public final /* synthetic */ double f$2;
    public final /* synthetic */ TLRPC.VideoSize f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ TLRPC.PhotoSize f$5;
    public final /* synthetic */ TLRPC.PhotoSize f$6;
    public final /* synthetic */ Runnable f$7;
    public final /* synthetic */ INavigationLayout f$8;
    public final /* synthetic */ ImageUpdater f$9;

    public /* synthetic */ PhotoUtilities$$ExternalSyntheticLambda4(TLRPC.InputFile r1, TLRPC.InputFile r2, double r3, TLRPC.VideoSize r5, int r6, TLRPC.PhotoSize r7, TLRPC.PhotoSize r8, Runnable r9, INavigationLayout r10, ImageUpdater r11) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
        this.f$9 = r11;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PhotoUtilities.m5199$r8$lambda$7MejB92G72HNHArTnHvAEjZ0Ys(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9);
    }
}
