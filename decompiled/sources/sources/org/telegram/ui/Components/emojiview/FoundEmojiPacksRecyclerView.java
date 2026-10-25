package org.telegram.ui.Components.emojiview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public abstract class FoundEmojiPacksRecyclerView extends UniversalRecyclerView {
    public static /* synthetic */ void $r8$lambda$E3vZfckU5T_ayjZyEFQxbr4Ts8Q(FoundEmojiPacksRecyclerView r0, View r1, int r2) {
        r0.lambda$scrollOnSelect$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$nN_InxYUc8wLWM9idijr4fOKUJU(FoundEmojiPacksRecyclerView r0, int r1) {
        r0.lambda$scrollOnSelect$0(r1);
    }

    public FoundEmojiPacksRecyclerView(Context r1, int r2, int r3, boolean r4, Utilities.Callback2<ArrayList<UItem>, UniversalAdapter> r5, Utilities.Callback5<UItem, View, Integer, Float, Float> r6, Utilities.Callback5Return<UItem, View, Integer, Float, Float, Boolean> r7, Theme.ResourcesProvider r8, int r9, int r10) {
        super(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
        setAdaptiveOverScroll();
    }

    private /* synthetic */ void lambda$scrollOnSelect$0(int r2) {
        smoothScrollBy(r2, 0);
    }

    private /* synthetic */ void lambda$scrollOnSelect$1(View r2, int r3) {
        r2.postOnAnimation(new FoundEmojiPacksRecyclerView$$ExternalSyntheticLambda1(this, r3));
    }

    public void scrollOnSelect(View r6) {
        if (r6 == null) goto L15;
        float r0 = AndroidUtilities.dp(92.0f);
        float r1 = getWidth() - r0;
        float r2 = r6.getX();
        float r3 = r6.getWidth() + r2;
        if (r2 >= r0) goto L8;
        int r02 = (int) (r2 - r0);
    L11:
        if (r02 == 0) goto L14;
        AndroidUtilities.doOnLayout(this, new FoundEmojiPacksRecyclerView$$ExternalSyntheticLambda0(this, r6, r02));
        return;
    L14:
        return;
    L8:
        if (r3 <= r1) goto L10;
        r02 = (int) (r3 - r1);
        goto L11
    L10:
        r02 = 0;
        goto L11
    }
}
