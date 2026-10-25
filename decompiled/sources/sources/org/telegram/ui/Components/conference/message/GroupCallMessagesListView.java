package org.telegram.ui.Components.conference.message;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.voip.GroupCallMessage;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.conference.message.GroupCallMessageCell;

/* loaded from: classes7.dex */
public class GroupCallMessagesListView extends RecyclerView {
    private final GroupCallMessagesAdapter adapter;
    private View blurRoot;
    private GroupCallMessageCell.Delegate cellDelegate;
    private int clipBottom;
    private int clipTop;
    private Delegate delegate;
    private final Paint maskPaint;
    private RenderNode renderNode;
    private float renderNodeScale;
    private int visibleHeight;

    /* renamed from: org.telegram.ui.Components.conference.message.GroupCallMessagesListView$1, reason: invalid class name */
    public class AnonymousClass1 extends LinearLayoutManager {
        final /* synthetic */ GroupCallMessagesListView this$0;

        public AnonymousClass1(GroupCallMessagesListView r1, Context r2, int r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean canScrollVertically() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.conference.message.GroupCallMessagesListView$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.ItemDecoration {
        final /* synthetic */ GroupCallMessagesListView this$0;

        public AnonymousClass2(GroupCallMessagesListView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            r1.top = AndroidUtilities.dp(6.0f);
        }
    }

    /* renamed from: org.telegram.ui.Components.conference.message.GroupCallMessagesListView$3, reason: invalid class name */
    public class AnonymousClass3 extends GroupCallMessagesAdapter {
        final /* synthetic */ GroupCallMessagesListView this$0;

        public AnonymousClass3(GroupCallMessagesListView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public /* bridge */ /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            return onCreateViewHolder(r1, r2);
        }

        @Override // org.telegram.ui.Components.conference.message.GroupCallMessagesAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public GroupCallMessageCell.VH onCreateViewHolder(ViewGroup r4, int r5) {
            GroupCallMessageCell.VH r42 = super.onCreateViewHolder(r4, r5);
            r42.cell.setRenderNode(GroupCallMessagesListView.m13704$$Nest$fgetblurRoot(this.this$0), GroupCallMessagesListView.m13707$$Nest$fgetrenderNode(this.this$0), GroupCallMessagesListView.m13708$$Nest$fgetrenderNodeScale(this.this$0));
            r42.cell.setDelegate(GroupCallMessagesListView.m13705$$Nest$fgetcellDelegate(this.this$0));
            return r42;
        }
    }

    /* renamed from: org.telegram.ui.Components.conference.message.GroupCallMessagesListView$4, reason: invalid class name */
    public class AnonymousClass4 extends DefaultItemAnimator {
        final /* synthetic */ GroupCallMessagesListView this$0;

        public AnonymousClass4(GroupCallMessagesListView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public float animateByScale(View r1) {
            return 0.6f;
        }

        @Override // androidx.recyclerview.widget.SimpleItemAnimator
        public void onAddFinished(RecyclerView.ViewHolder r3) {
            super.onAddFinished(r3);
            GroupCallMessage r0 = GroupCallMessagesListView.m13703$$Nest$fgetadapter(this.this$0).getMessage(r3.getAdapterPosition());
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.visibleReaction != null) goto L7;
            return;
        L7:
            if ((r3.itemView instanceof GroupCallMessageCell) == true) goto L9;
            return;
        L9:
            if (GroupCallMessagesListView.m13706$$Nest$fgetdelegate(this.this$0) == null) goto L15;
            GroupCallMessagesListView.m13706$$Nest$fgetdelegate(this.this$0).showReaction((GroupCallMessageCell) r3.itemView, r0.visibleReaction);
            return;
        }
    }

    public interface Delegate {
        void showReaction(GroupCallMessageCell r1, ReactionsLayoutInBubble.VisibleReaction r2);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCallMessagesAdapter m13703$$Nest$fgetadapter(GroupCallMessagesListView r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetblurRoot, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m13704$$Nest$fgetblurRoot(GroupCallMessagesListView r0) {
        return r0.blurRoot;
    }

    /* renamed from: -$$Nest$fgetcellDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCallMessageCell.Delegate m13705$$Nest$fgetcellDelegate(GroupCallMessagesListView r0) {
        return r0.cellDelegate;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m13706$$Nest$fgetdelegate(GroupCallMessagesListView r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetrenderNode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RenderNode m13707$$Nest$fgetrenderNode(GroupCallMessagesListView r0) {
        return r0.renderNode;
    }

    /* renamed from: -$$Nest$fgetrenderNodeScale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13708$$Nest$fgetrenderNodeScale(GroupCallMessagesListView r0) {
        return r0.renderNodeScale;
    }

    public GroupCallMessagesListView(Context r13) {
        super(r13);
        Paint r0 = new Paint(1);
        this.maskPaint = r0;
        this.clipTop = Integer.MIN_VALUE;
        this.clipBottom = Integer.MIN_VALUE;
        r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        r0.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(16.0f), 0, -16777216, Shader.TileMode.CLAMP));
        setLayoutManager(new AnonymousClass1(this, r13, 1, true));
        addItemDecoration(new AnonymousClass2(this));
        AnonymousClass3 r132 = new AnonymousClass3(this);
        this.adapter = r132;
        setAdapter(r132);
        setItemAnimator(createItemAnimator());
    }

    private DefaultItemAnimator createItemAnimator() {
        AnonymousClass4 r0 = new AnonymousClass4(this);
        r0.setSupportsChangeAnimations(false);
        r0.setDelayAnimations(false);
        r0.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r0.setDurations(320);
        return r0;
    }

    private float getMinChildY() {
        int r0 = getChildCount();
        float r1 = 2.1474836E9f;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        View r3 = getChildAt(r2);
        if (r3.getVisibility() != 0) goto L7;
        r1 = Math.min(r1, r3.getY());
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        return r1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r14) {
        int r6 = getMeasuredHeight() - this.visibleHeight;
        int r7 = AndroidUtilities.dp(16.0f);
        int r8 = r6 + r7;
        int r9 = getMeasuredHeight();
        int r10 = getMeasuredWidth();
        float r4 = r8;
        if (r4 >= getMinChildY()) goto L6;
        super.dispatchDraw(r14);
        return;
    L6:
        float r2 = r6;
        float r3 = r10;
        int r11 = r14.saveLayer(0.0f, r2, r3, r4, null);
        r14.clipRect(0, r6, r10, r8);
        this.clipTop = r6;
        this.clipBottom = r8;
        super.dispatchDraw(r14);
        r14.translate(0.0f, r2);
        r14.drawRect(0.0f, 0.0f, r3, r7, this.maskPaint);
        r14.restoreToCount(r11);
        r14.save();
        r14.clipRect(0, r8, r10, r9);
        this.clipTop = r8;
        this.clipBottom = getMeasuredHeight();
        super.dispatchDraw(r14);
        r14.restore();
        this.clipTop = Integer.MIN_VALUE;
        this.clipBottom = Integer.MIN_VALUE;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r10) {
        if (r10.getAction() != 0) goto L19;
        int r0 = (int) r10.getX();
        int r1 = (int) r10.getY();
        if (r1 >= (getMeasuredHeight() - this.visibleHeight)) goto L7;
        return false;
    L7:
        int r2 = getChildCount();
        int r4 = 0;
    L8:
        if (r4 >= r2) goto L17;
        View r5 = getChildAt(r4);
        if ((r5 instanceof GroupCallMessageCell) == false) goto L16;
        GroupCallMessageCell r6 = (GroupCallMessageCell) r5;
        if (r6.getVisibility() != 0) goto L16;
        if (r6.isInsideBubble(r0 - r5.getX(), r1 - r5.getY()) == true) goto L19;
    L16:
        r4 = r4 + 1;
        goto L8
    L17:
        return false;
    L19:
        return super.dispatchTouchEvent(r10);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean drawChild(Canvas r5, View r6, long r7) {
        if (this.clipTop == Integer.MIN_VALUE) goto L8;
        if ((r6.getY() + r6.getHeight()) >= this.clipTop) goto L8;
        return true;
    L8:
        if (this.clipBottom == Integer.MIN_VALUE) goto L13;
        if (r6.getY() <= this.clipBottom) goto L13;
        return true;
    L13:
        return super.drawChild(r5, r6, r7);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.adapter.attach();
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.adapter.detach();
    }

    public void setBlurRoot(View r1) {
        this.blurRoot = r1;
    }

    public void setClickCellDelegate(GroupCallMessageCell.Delegate r1) {
        this.cellDelegate = r1;
    }

    public void setDelegate(Delegate r1) {
        this.delegate = r1;
    }

    public void setGroupCall(int r1, TLRPC.InputGroupCall r2) {
        this.adapter.setGroupCall(r1, r2);
    }

    public void setRenderNode(RenderNode r1, float r2) {
        this.renderNode = r1;
        this.renderNodeScale = r2;
    }

    @Override // android.view.View
    public void setTranslationY(float r3) {
        if (getTranslationY() == r3) goto L7;
        super.setTranslationY(r3);
        invalidate();
        int r32 = getChildCount();
        int r0 = 0;
    L5:
        if (r0 >= r32) goto L9;
        getChildAt(r0).invalidate();
        r0 = r0 + 1;
        goto L5
    L9:
        return;
    }

    public void setVisibleHeight(int r2) {
        if (this.visibleHeight == r2) goto L6;
        this.visibleHeight = r2;
        invalidate();
        return;
    }
}
