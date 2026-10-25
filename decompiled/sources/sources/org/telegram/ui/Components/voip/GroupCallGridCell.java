package org.telegram.ui.Components.voip;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.GroupCallActivity;
import org.telegram.ui.GroupCallTabletGridAdapter;

/* loaded from: classes7.dex */
public abstract class GroupCallGridCell extends FrameLayout {
    public boolean attached;
    public GroupCallTabletGridAdapter gridAdapter;
    private final boolean isTabletGrid;
    ChatObject.VideoParticipant participant;
    public int position;
    GroupCallMiniTextureView renderer;
    public int spanCount;

    public GroupCallGridCell(Context r1, boolean r2) {
        super(r1);
        this.isTabletGrid = r2;
    }

    public float getItemHeight() {
        GroupCallTabletGridAdapter r0 = this.gridAdapter;
        if (r0 == null) goto L7;
        int r1 = r0.getItemHeight(this.position);
    L6:
        return r1;
    L7:
        r1 = getMeasuredHeight();
        goto L6
    }

    public ChatObject.VideoParticipant getParticipant() {
        return this.participant;
    }

    public GroupCallMiniTextureView getRenderer() {
        return this.renderer;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attached = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attached = false;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        if (this.isTabletGrid == false) goto L7;
        ((View) getParent()).getMeasuredWidth();
        super.onMeasure(r5, View.MeasureSpec.makeMeasureSpec(this.gridAdapter.getItemHeight(this.position), TLObject.FLAG_30));
        return;
    L7:
        if (GroupCallActivity.isLandscapeMode == false) goto L9;
        float r62 = 3.0f;
    L10:
        int r2 = AndroidUtilities.displaySize.x - (AndroidUtilities.dp(14.0f) * 2);
        if (GroupCallActivity.isLandscapeMode == false) goto L13;
        int r3 = -AndroidUtilities.dp(90.0f);
    L14:
        float r22 = r2 + r3;
        if (GroupCallActivity.isTabletMode == false) goto L17;
        float r23 = r22 / 2.0f;
    L18:
        super.onMeasure(r5, View.MeasureSpec.makeMeasureSpec((int) (r23 + AndroidUtilities.dp(4.0f)), TLObject.FLAG_30));
        return;
    L17:
        r23 = r22 / r62;
        goto L18
    L13:
        r3 = 0;
        goto L14
    L9:
        r62 = 2.0f;
        goto L10
    }

    public void setData(AccountInstance r1, ChatObject.VideoParticipant r2, ChatObject.Call r3, long r4) {
        this.participant = r2;
    }

    public void setRenderer(GroupCallMiniTextureView r1) {
        this.renderer = r1;
    }
}
