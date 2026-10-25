package org.telegram.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.voip.GroupCallGridCell;
import org.telegram.ui.Components.voip.GroupCallMiniTextureView;
import org.telegram.ui.Components.voip.GroupCallRenderersContainer;

/* loaded from: classes6.dex */
public class GroupCallTabletGridAdapter extends RecyclerListView.SelectionAdapter {
    private final GroupCallActivity activity;
    private ArrayList<GroupCallMiniTextureView> attachedRenderers;
    private final int currentAccount;
    private ChatObject.Call groupCall;
    private GroupCallRenderersContainer renderersContainer;
    private final ArrayList<ChatObject.VideoParticipant> videoParticipants;
    private boolean visible;

    /* renamed from: org.telegram.ui.GroupCallTabletGridAdapter$1, reason: invalid class name */
    public class AnonymousClass1 extends GroupCallGridCell {
        final /* synthetic */ GroupCallTabletGridAdapter this$0;

        public AnonymousClass1(GroupCallTabletGridAdapter r1, Context r2, boolean r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.voip.GroupCallGridCell, android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (GroupCallTabletGridAdapter.m15338$$Nest$fgetvisible(this.this$0) == true) goto L5;
            return;
        L5:
            if (getParticipant() == null) goto L9;
            GroupCallTabletGridAdapter.m15339$$Nest$mattachRenderer(this.this$0, this, true);
            return;
        }

        @Override // org.telegram.ui.Components.voip.GroupCallGridCell, android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            GroupCallTabletGridAdapter.m15339$$Nest$mattachRenderer(this.this$0, this, false);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallTabletGridAdapter$2, reason: invalid class name */
    public class AnonymousClass2 extends DiffUtil.Callback {
        final /* synthetic */ GroupCallTabletGridAdapter this$0;
        final /* synthetic */ ArrayList val$oldVideoParticipants;

        public AnonymousClass2(GroupCallTabletGridAdapter r1, ArrayList r2) {
            this.this$0 = r1;
            this.val$oldVideoParticipants = r2;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areContentsTheSame(int r1, int r2) {
            return true;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areItemsTheSame(int r2, int r3) {
            if (r2 < this.val$oldVideoParticipants.size()) goto L5;
            return false;
        L5:
            if (r3 < GroupCallTabletGridAdapter.m15337$$Nest$fgetvideoParticipants(this.this$0).size()) goto L7;
            return false;
        L7:
            return ((ChatObject.VideoParticipant) this.val$oldVideoParticipants.get(r2)).equals(GroupCallTabletGridAdapter.m15337$$Nest$fgetvideoParticipants(this.this$0).get(r3));
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getNewListSize() {
            return GroupCallTabletGridAdapter.m15337$$Nest$fgetvideoParticipants(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getOldListSize() {
            return this.val$oldVideoParticipants.size();
        }
    }

    /* renamed from: -$$Nest$fgetvideoParticipants, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m15337$$Nest$fgetvideoParticipants(GroupCallTabletGridAdapter r0) {
        return r0.videoParticipants;
    }

    /* renamed from: -$$Nest$fgetvisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15338$$Nest$fgetvisible(GroupCallTabletGridAdapter r0) {
        return r0.visible;
    }

    /* renamed from: -$$Nest$mattachRenderer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15339$$Nest$mattachRenderer(GroupCallTabletGridAdapter r0, GroupCallGridCell r1, boolean r2) {
        r0.attachRenderer(r1, r2);
    }

    public GroupCallTabletGridAdapter(ChatObject.Call r2, int r3, GroupCallActivity r4) {
        this.videoParticipants = new ArrayList();
        this.visible = false;
        this.groupCall = r2;
        this.currentAccount = r3;
        this.activity = r4;
    }

    private void attachRenderer(GroupCallGridCell r10, boolean r11) {
        if (r11 == false) goto L8;
        if (r10.getRenderer() != null) goto L8;
        r10.setRenderer(GroupCallMiniTextureView.getOrCreate(this.attachedRenderers, this.renderersContainer, null, null, r10, r10.getParticipant(), this.groupCall, this.activity));
        return;
    L8:
        if (r11 == false) goto L10;
        return;
    L10:
        if (r10.getRenderer() == null) goto L14;
        r10.getRenderer().setTabletGridView(null);
        r10.setRenderer(null);
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.videoParticipants.size();
    }

    public int getItemHeight(int r2) {
        RecyclerListView r22 = this.activity.tabletVideoGridView;
        int r1 = getItemCount();
        if (r1 > 1) goto L7;
        return r22.getMeasuredHeight();
    L7:
        if (r1 > 4) goto L11;
        return r22.getMeasuredHeight() / 2;
    L11:
        return (int) (r22.getMeasuredHeight() / 2.5f);
    }

    public int getSpanCount(int r4) {
        int r3 = getItemCount();
        if (r3 > 1) goto L6;
        return 6;
    L6:
        if (r3 != 2) goto L9;
        return 6;
    L9:
        if (r3 != 3) goto L14;
        if (r4 == 0) goto L14;
        if (r4 == 1) goto L14;
        return 6;
    L14:
        return 3;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
    public boolean isEnabled(RecyclerView.ViewHolder r1) {
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder r7, int r8) {
        GroupCallGridCell r0 = (GroupCallGridCell) r7.itemView;
        ChatObject.VideoParticipant r72 = r0.getParticipant();
        ChatObject.VideoParticipant r2 = this.videoParticipants.get(r8);
        TLRPC.GroupCallParticipant r1 = this.videoParticipants.get(r8).participant;
        r0.spanCount = getSpanCount(r8);
        r0.position = r8;
        r0.gridAdapter = this;
        if (r0.getMeasuredHeight() == getItemHeight(r8)) goto L5;
        r0.requestLayout();
    L5:
        AccountInstance r12 = AccountInstance.getInstance(this.currentAccount);
        ChatObject.Call r3 = this.groupCall;
        r0.setData(r12, r2, r3, MessageObject.getPeerId(r3.selfPeer));
        if (r72 == null) goto L16;
        if (r72.equals(r2) == true) goto L16;
        if (r0.attached == false) goto L16;
        if (r0.getRenderer() == null) goto L16;
        attachRenderer(r0, false);
        attachRenderer(r0, true);
        return;
    L16:
        if (r0.getRenderer() == null) goto L19;
        r0.getRenderer().updateAttachState(true);
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
        return new RecyclerListView.Holder(new AnonymousClass1(this, r3.getContext(), true));
    }

    public void setGroupCall(ChatObject.Call r1) {
        this.groupCall = r1;
    }

    public void setRenderersPool(ArrayList<GroupCallMiniTextureView> r1, GroupCallRenderersContainer r2) {
        this.attachedRenderers = r1;
        this.renderersContainer = r2;
    }

    public void setVisibility(RecyclerListView r3, boolean r4, boolean r5) {
        this.visible = r4;
        if (r5 == false) goto L13;
        int r52 = 0;
    L6:
        if (r52 >= r3.getChildCount()) goto L18;
        View r0 = r3.getChildAt(r52);
        if ((r0 instanceof GroupCallGridCell) == false) goto L12;
        GroupCallGridCell r02 = (GroupCallGridCell) r0;
        if (r02.getParticipant() == null) goto L12;
        attachRenderer(r02, r4);
    L12:
        r52 = r52 + 1;
        goto L6
    L18:
        return;
    }

    public void update(boolean r3, RecyclerListView r4) {
        if (this.groupCall != null) goto L5;
        return;
    L5:
        if (r3 == false) goto L8;
        ArrayList r32 = new ArrayList();
        r32.addAll(this.videoParticipants);
        this.videoParticipants.clear();
        this.videoParticipants.addAll(this.groupCall.visibleVideoParticipants);
        DiffUtil.calculateDiff(new AnonymousClass2(this, r32)).dispatchUpdatesTo(this);
        AndroidUtilities.updateVisibleRows(r4);
        return;
    L8:
        this.videoParticipants.clear();
        this.videoParticipants.addAll(this.groupCall.visibleVideoParticipants);
        notifyDataSetChanged();
    }
}
