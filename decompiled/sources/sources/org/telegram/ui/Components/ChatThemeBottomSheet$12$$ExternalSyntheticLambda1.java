package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ChatThemeBottomSheet;
import org.telegram.ui.ThemePreviewActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatThemeBottomSheet$12$$ExternalSyntheticLambda1 implements ThemePreviewActivity.WallpaperActivityDelegate {
    public final /* synthetic */ ChatAttachAlert f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ ChatThemeBottomSheet$12$$ExternalSyntheticLambda1(ChatAttachAlert r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ThemePreviewActivity.WallpaperActivityDelegate
    public final void didSetNewBackground(TLRPC.WallPaper r2) {
        ChatThemeBottomSheet.AnonymousClass12.m9998$r8$lambda$6dRX3czrryVZ6jB_jue3GVp8Y(this.f$0, this.f$1, r2);
    }
}
