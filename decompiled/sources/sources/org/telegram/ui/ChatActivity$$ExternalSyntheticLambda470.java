package org.telegram.ui;

import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import androidx.viewpager.widget.ViewPager;
import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.ui.Components.ReactionTabHolderView;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda470 implements View.OnClickListener {
    public final /* synthetic */ ViewPager f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ LinearLayout f$2;
    public final /* synthetic */ AtomicBoolean f$3;
    public final /* synthetic */ HorizontalScrollView f$4;
    public final /* synthetic */ ReactionTabHolderView f$5;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda470(ViewPager r1, int r2, LinearLayout r3, AtomicBoolean r4, HorizontalScrollView r5, ReactionTabHolderView r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r8) {
        ChatActivity.$r8$lambda$S4PKaURmJxjigb3lIJxRO3CFcMM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r8);
    }
}
