package org.telegram.ui.Components.chat;

import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;

/* loaded from: classes7.dex */
public class ChatListViewPaddingsAnimator {
    private int currentAdditionalHeight;
    private final RecyclerView recyclerView;

    /* renamed from: $r8$lambda$bFF-sXCPq3Ccgxl5P8akrY9z9vg, reason: not valid java name */
    public static /* synthetic */ void m13693$r8$lambda$bFFsXCPq3Ccgxl5P8akrY9z9vg(ChatListViewPaddingsAnimator r0, int r1) {
        r0.lambda$setPaddings$0(r1);
    }

    public ChatListViewPaddingsAnimator(RecyclerView r1) {
        this.recyclerView = r1;
    }

    private /* synthetic */ void lambda$setPaddings$0(int r2) {
        this.recyclerView.scrollBy(0, r2);     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        FileLog.e(th);
    }

    public void setPaddings(int r4, float r5, boolean r6) {
        int r0 = this.currentAdditionalHeight;
        if (r0 == 0) goto L5;
        this.currentAdditionalHeight = 0;
        this.recyclerView.requestLayout();
    L7:
        int r52 = (int) r5;
        int r02 = this.recyclerView.getPaddingTop();
        int r1 = this.recyclerView.getPaddingBottom();
        if (r02 != r4) goto L12;
        if (r1 != r52) goto L12;
        return;
    L12:
        int r03 = r02 - r4;
        if (r6 == false) goto L22;
        if (r03 == 0) goto L22;
        boolean r62 = this.recyclerView.canScrollVertically(1);
        boolean r12 = this.recyclerView.canScrollVertically(-1);
        if (r03 >= 0) goto L18;
        if (r62 == false) goto L22;
    L18:
        if (r03 <= 0) goto L21;
        if (r12 == false) goto L22;
    L21:
        AndroidUtilities.doOnLayout(this.recyclerView, new ChatListViewPaddingsAnimator$$ExternalSyntheticLambda0(this, r03));
    L22:
        RecyclerView r63 = this.recyclerView;
        r63.setPadding(r63.getPaddingLeft(), r4, this.recyclerView.getPaddingRight(), r52);
        return;
    L5:
        if (r0 >= 0) goto L7;
        this.currentAdditionalHeight = 0;
        this.recyclerView.requestLayout();
        goto L7
    }
}
