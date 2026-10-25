package org.telegram.ui.Stories.recorder;

import android.util.Pair;
import org.telegram.tgnet.ResultCallback;
import org.telegram.ui.ActionBar.EmojiThemes;
import org.telegram.ui.Components.MotionBackgroundDrawable;

/* loaded from: classes7.dex */
public final /* synthetic */ class PreviewView$$ExternalSyntheticLambda0 implements ResultCallback {
    public final /* synthetic */ EmojiThemes f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ MotionBackgroundDrawable f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ PreviewView$$ExternalSyntheticLambda0(EmojiThemes r1, boolean r2, boolean r3, MotionBackgroundDrawable r4, int r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.ResultCallback
    public final void onComplete(Object r7) {
        PreviewView.m20150$r8$lambda$YvX6qPvkc_kWlwMcyzkaJt4bas(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (Pair) r7);
    }
}
