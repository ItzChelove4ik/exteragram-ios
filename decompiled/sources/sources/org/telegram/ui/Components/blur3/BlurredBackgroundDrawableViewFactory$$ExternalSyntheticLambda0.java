package org.telegram.ui.Components.blur3;

import android.graphics.RectF;
import android.view.View;
import java.lang.ref.WeakReference;
import org.telegram.ui.Components.chat.ViewPositionWatcher;

/* loaded from: classes3.dex */
public final /* synthetic */ class BlurredBackgroundDrawableViewFactory$$ExternalSyntheticLambda0 implements ViewPositionWatcher.OnChangedListener {
    public final /* synthetic */ WeakReference f$0;

    public /* synthetic */ BlurredBackgroundDrawableViewFactory$$ExternalSyntheticLambda0(WeakReference r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.chat.ViewPositionWatcher.OnChangedListener
    public final void onPositionChanged(View r1, RectF r2) {
        BlurredBackgroundDrawableViewFactory.$r8$lambda$zQYw2Ior9IE1MD7jp9GiuMfrb8I(this.f$0, r1, r2);
    }
}
