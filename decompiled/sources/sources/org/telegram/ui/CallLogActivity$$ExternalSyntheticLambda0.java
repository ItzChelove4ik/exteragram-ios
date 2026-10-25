package org.telegram.ui;

import android.animation.ValueAnimator;
import android.widget.FrameLayout;
import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.ui.Components.LinkSpanDrawable;

/* loaded from: classes6.dex */
public final /* synthetic */ class CallLogActivity$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ FrameLayout f$0;
    public final /* synthetic */ AtomicBoolean f$1;
    public final /* synthetic */ LinkSpanDrawable.LinksTextView f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ CallLogActivity$$ExternalSyntheticLambda0(FrameLayout r1, AtomicBoolean r2, LinkSpanDrawable.LinksTextView r3, String r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r4) {
        CallLogActivity.$r8$lambda$QNeaNZs9dHyaPKY9Uvb0ULRNf5w(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
