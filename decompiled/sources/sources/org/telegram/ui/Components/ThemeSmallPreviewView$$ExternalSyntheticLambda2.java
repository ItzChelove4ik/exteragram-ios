package org.telegram.ui.Components;

import android.util.Pair;
import org.telegram.tgnet.ResultCallback;
import org.telegram.ui.Components.ChatThemeBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class ThemeSmallPreviewView$$ExternalSyntheticLambda2 implements ResultCallback {
    public final /* synthetic */ ThemeSmallPreviewView f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ ChatThemeBottomSheet.ChatThemeItem f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ ThemeSmallPreviewView$$ExternalSyntheticLambda2(ThemeSmallPreviewView r1, long r2, ChatThemeBottomSheet.ChatThemeItem r4, int r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
    }

    @Override // org.telegram.tgnet.ResultCallback
    public final void onComplete(Object r7) {
        ThemeSmallPreviewView.$r8$lambda$QMSOJIft2nL9m8T1onAzRNoH3TQ(this.f$0, this.f$1, this.f$2, this.f$3, (Pair) r7);
    }
}
