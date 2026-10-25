package org.telegram.ui.Components;

import org.telegram.messenger.ImageReceiver;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ChatThemeBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class ThemeSmallPreviewView$$ExternalSyntheticLambda3 implements ImageReceiver.ImageReceiverDelegate {
    public final /* synthetic */ ThemeSmallPreviewView f$0;
    public final /* synthetic */ ChatThemeBottomSheet.ChatThemeItem f$1;
    public final /* synthetic */ TLRPC.WallPaper f$2;

    public /* synthetic */ ThemeSmallPreviewView$$ExternalSyntheticLambda3(ThemeSmallPreviewView r1, ChatThemeBottomSheet.ChatThemeItem r2, TLRPC.WallPaper r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
    public final void didSetImage(ImageReceiver r8, boolean r9, boolean r10, boolean r11) {
        ThemeSmallPreviewView.$r8$lambda$c3FhGGpnuJJJCGSHcZlVd9y4_Dg(this.f$0, this.f$1, this.f$2, r8, r9, r10, r11);
    }
}
