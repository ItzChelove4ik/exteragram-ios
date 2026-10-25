package org.telegram.ui;

import android.util.Pair;
import org.telegram.tgnet.ResultCallback;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.MotionBackgroundDrawable;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ThemeDelegate$$ExternalSyntheticLambda7 implements ResultCallback {
    public final /* synthetic */ ChatActivity.ThemeDelegate f$0;
    public final /* synthetic */ MotionBackgroundDrawable f$1;

    public /* synthetic */ ChatActivity$ThemeDelegate$$ExternalSyntheticLambda7(ChatActivity.ThemeDelegate r1, MotionBackgroundDrawable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.ResultCallback
    public final void onComplete(Object r2) {
        ChatActivity.ThemeDelegate.$r8$lambda$EPHLzg2PI2yOQxHWs6uej__6DJc(this.f$0, this.f$1, (Pair) r2);
    }
}
