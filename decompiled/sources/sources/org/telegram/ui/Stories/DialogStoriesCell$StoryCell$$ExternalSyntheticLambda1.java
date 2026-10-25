package org.telegram.ui.Stories;

import android.animation.ValueAnimator;
import android.view.View;
import org.telegram.ui.Stories.DialogStoriesCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class DialogStoriesCell$StoryCell$$ExternalSyntheticLambda1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ DialogStoriesCell.StoryCell f$0;
    public final /* synthetic */ View f$1;

    public /* synthetic */ DialogStoriesCell$StoryCell$$ExternalSyntheticLambda1(DialogStoriesCell.StoryCell r1, View r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        DialogStoriesCell.StoryCell.$r8$lambda$3ZnTv2Lx6FObaSBnotR50v39svQ(this.f$0, this.f$1, r2);
    }
}
