package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ChatThemeBottomSheet;
import org.telegram.ui.ThemePreviewActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatThemeBottomSheet$13$$ExternalSyntheticLambda0 implements ThemePreviewActivity.WallpaperActivityDelegate {
    public final /* synthetic */ ChatThemeBottomSheet.AnonymousClass13 f$0;

    public /* synthetic */ ChatThemeBottomSheet$13$$ExternalSyntheticLambda0(ChatThemeBottomSheet.AnonymousClass13 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.ThemePreviewActivity.WallpaperActivityDelegate
    public final void didSetNewBackground(TLRPC.WallPaper r1) {
        ChatThemeBottomSheet.AnonymousClass13.$r8$lambda$u2qOyqFFJX6pUCOBNMb4MZ0LNNA(this.f$0, r1);
    }
}
