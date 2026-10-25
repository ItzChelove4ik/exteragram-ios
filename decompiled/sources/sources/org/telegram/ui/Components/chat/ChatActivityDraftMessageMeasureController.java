package org.telegram.ui.Components.chat;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.MessageObject;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes7.dex */
public class ChatActivityDraftMessageMeasureController {
    private long groupIdToOverride;
    private boolean hasAdditionalHeight;
    private int messageIdToOverride;
    private int previousMessageHeight;
    private RecyclerView recyclerView;

    public ChatActivityDraftMessageMeasureController() {
    }

    public boolean filter(View r2) {
        if ((r2 instanceof ChatMessageCell) == false) goto L7;
        return filter(((ChatMessageCell) r2).getMessageObject());
    L7:
        if ((r2 instanceof ChatActionCell) == true) goto L9;
        return false;
    L9:
        return filter(((ChatActionCell) r2).getMessageObject());
    }

    public boolean filter(MessageObject r5) {
        if (r5 != null) goto L4;
        return false;
    L4:
        if (r5.getId() != this.messageIdToOverride) goto L6;
        return true;
    L6:
        if (this.groupIdToOverride != 0) goto L8;
        return false;
    L8:
        if (r5.getGroupId() != this.groupIdToOverride) goto L15;
        return true;
    L15:
        return false;
    }

    public int getOverrideMeasureHeight(MessageObject r4, int r5) {
        if (filter(r4) == true) goto L5;
        return r5;
    L5:
        int r42 = Math.max(0, (((this.recyclerView.getHeight() - this.recyclerView.getPaddingTop()) - this.recyclerView.getPaddingBottom()) - this.previousMessageHeight) - r5);
        if (r42 <= 0) goto L8;
        boolean r1 = true;
    L9:
        this.hasAdditionalHeight = r1;
        if (this.messageIdToOverride <= 0) goto L14;
        if (r1 == true) goto L14;
        setMessageIdToOverride(0, 0);
    L14:
        return r5 + r42;
    L8:
        r1 = false;
        goto L9
    }

    public boolean hasAdditionalHeight() {
        return this.hasAdditionalHeight;
    }

    public boolean onMessageIdChanged(int r2, int r3, long r4) {
        if (this.messageIdToOverride == r2) goto L5;
        return false;
    L5:
        return setMessageIdToOverride(r3, r4);
    }

    public void onRequestLayout() {
        if (this.messageIdToOverride == 0) goto L8;
        int r0 = this.recyclerView.getChildCount();
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L10;
        this.recyclerView.getChildAt(r1).forceLayout();
        r1 = r1 + 1;
        goto L6
    L10:
        return;
    }

    public void onScroll() {
        if (this.messageIdToOverride > 0) goto L5;
        return;
    L5:
        int r0 = this.recyclerView.getChildCount();
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L11;
        if (filter(this.recyclerView.getChildAt(r2)) == true) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return;
    L11:
        setMessageIdToOverride(0, 0);
    }

    public boolean setMessageIdToOverride(int r5, long r6) {
        if (this.messageIdToOverride == r5) goto L5;
    L8:
        this.messageIdToOverride = r5;
        this.groupIdToOverride = r6;
        if (r5 != 0) goto L13;
        this.hasAdditionalHeight = false;
        return true;
    L13:
        return true;
    L5:
        if (this.groupIdToOverride != r6) goto L8;
        return false;
    }

    public void setPreviousMessageHeight(int r1) {
        this.previousMessageHeight = r1;
    }

    public void setRecyclerView(RecyclerView r1) {
        this.recyclerView = r1;
    }
}
