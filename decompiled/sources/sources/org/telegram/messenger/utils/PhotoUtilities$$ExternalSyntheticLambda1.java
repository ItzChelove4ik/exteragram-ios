package org.telegram.messenger.utils;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.Components.ImageUpdater;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoUtilities$$ExternalSyntheticLambda1 implements ImageUpdater.ImageUpdaterDelegate {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ INavigationLayout f$2;
    public final /* synthetic */ ImageUpdater f$3;

    public /* synthetic */ PhotoUtilities$$ExternalSyntheticLambda1(int r1, Runnable r2, INavigationLayout r3, ImageUpdater r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public final void didUploadPhoto(TLRPC.InputFile r14, TLRPC.InputFile r15, double r16, String r18, TLRPC.PhotoSize r19, TLRPC.PhotoSize r20, boolean r21, TLRPC.VideoSize r22) {
        PhotoUtilities.$r8$lambda$otNObLZ6D1u9ArXEpcP1ZYbBJfs(this.f$0, this.f$1, this.f$2, this.f$3, r14, r15, r16, r18, r19, r20, r21, r22);
    }
}
