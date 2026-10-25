package com.exteragram.messenger.utils.chats;

import android.graphics.Bitmap;
import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceBitmap;

/* loaded from: classes4.dex */
public final /* synthetic */ class GlassMenuHelper$$ExternalSyntheticLambda1 implements Utilities.Callback2 {
    public final /* synthetic */ BlurredBackgroundSourceBitmap f$0;
    public final /* synthetic */ View f$1;
    public final /* synthetic */ BlurredBackgroundDrawableViewFactory f$2;

    public /* synthetic */ GlassMenuHelper$$ExternalSyntheticLambda1(BlurredBackgroundSourceBitmap r1, View r2, BlurredBackgroundDrawableViewFactory r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        GlassMenuHelper.m1777$r8$lambda$VhYqc1_r5IlxZtuR_mFy6_NTTk(this.f$0, this.f$1, this.f$2, (Bitmap) r3, (Bitmap) r4);
    }
}
