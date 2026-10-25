package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.iv.RichEditor;

/* loaded from: classes3.dex */
public class UniversalRecyclerView extends RecyclerListView {
    public final UniversalAdapter adapter;
    private boolean doNotDetachViews;
    public ItemTouchHelper itemTouchHelper;
    public LinearLayoutManager layoutManager;
    private boolean reorderHandleOnly;
    private boolean reorderingAllowed;
    private View reorderingBackgroundView;
    private boolean reorderingLongPressEnabled;
    private boolean reorderingOnOtherAxis;
    private Drawable reorderingOriginalBackground;
    private RecyclerView.ViewHolder reorderingViewHolder;

    /* renamed from: org.telegram.ui.Components.UniversalRecyclerView$1, reason: invalid class name */
    public class AnonymousClass1 extends LinearLayoutManager {
        final /* synthetic */ UniversalRecyclerView this$0;

        public AnonymousClass1(UniversalRecyclerView r1, Context r2, int r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public int getExtraLayoutSpace(RecyclerView.State r2) {
            if (UniversalRecyclerView.m13483$$Nest$fgetdoNotDetachViews(this.this$0) == false) goto L7;
            return AndroidUtilities.displaySize.y;
        L7:
            return super.getExtraLayoutSpace(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.UniversalRecyclerView$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends ExtendedGridLayoutManager {
        final /* synthetic */ UniversalRecyclerView this$0;

        public AnonymousClass2(UniversalRecyclerView r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public int getExtraLayoutSpace(RecyclerView.State r2) {
            if (UniversalRecyclerView.m13483$$Nest$fgetdoNotDetachViews(this.this$0) == false) goto L7;
            return AndroidUtilities.displaySize.y;
        L7:
            return super.getExtraLayoutSpace(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.UniversalRecyclerView$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ UniversalRecyclerView this$0;
        final /* synthetic */ ExtendedGridLayoutManager val$layoutManager1;

        public AnonymousClass3(UniversalRecyclerView r1, ExtendedGridLayoutManager r2) {
            this.this$0 = r1;
            this.val$layoutManager1 = r2;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r2) {
            UniversalAdapter r0 = this.this$0.adapter;
            if (r0 == null) goto L5;
            UItem r22 = r0.getItem(r2);
            if (r22 == null) goto L13;
            int r23 = r22.spanCount;
            if (r23 == (-1)) goto L13;
            return r23;
        L13:
            return this.val$layoutManager1.getSpanCount();
        L5:
            return this.val$layoutManager1.getSpanCount();
        }
    }

    /* renamed from: org.telegram.ui.Components.UniversalRecyclerView$4, reason: invalid class name */
    public class AnonymousClass4 extends DefaultItemAnimator {
        final /* synthetic */ UniversalRecyclerView this$0;

        public AnonymousClass4(UniversalRecyclerView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onAddAnimationUpdate(RecyclerView.ViewHolder r1) {
            super.onAddAnimationUpdate(r1);
            if (this.this$0.hasSections() == false) goto L5;
            this.this$0.invalidate();
        L5:
            this.this$0.onLayoutUpdate();
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onChangeAnimationUpdate(RecyclerView.ViewHolder r1) {
            super.onChangeAnimationUpdate(r1);
            if (this.this$0.hasSections() == false) goto L5;
            this.this$0.invalidate();
        L5:
            this.this$0.onLayoutUpdate();
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            super.onMoveAnimationUpdate(r1);
            this.this$0.invalidate();
            this.this$0.onLayoutUpdate();
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onRemoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            super.onRemoveAnimationUpdate(r1);
            if (this.this$0.hasSections() == false) goto L5;
            this.this$0.invalidate();
        L5:
            this.this$0.onLayoutUpdate();
        }
    }

    /* renamed from: org.telegram.ui.Components.UniversalRecyclerView$5, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass5 extends LinearLayoutManager {
        final /* synthetic */ UniversalRecyclerView this$0;

        public AnonymousClass5(UniversalRecyclerView r1, Context r2, int r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public int getExtraLayoutSpace(RecyclerView.State r2) {
            if (UniversalRecyclerView.m13483$$Nest$fgetdoNotDetachViews(this.this$0) == false) goto L7;
            return AndroidUtilities.displaySize.y;
        L7:
            return super.getExtraLayoutSpace(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.UniversalRecyclerView$6, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass6 extends ExtendedGridLayoutManager {
        final /* synthetic */ UniversalRecyclerView this$0;

        public AnonymousClass6(UniversalRecyclerView r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public int getExtraLayoutSpace(RecyclerView.State r2) {
            if (UniversalRecyclerView.m13483$$Nest$fgetdoNotDetachViews(this.this$0) == false) goto L7;
            return AndroidUtilities.displaySize.y;
        L7:
            return super.getExtraLayoutSpace(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.UniversalRecyclerView$7, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass7 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ UniversalRecyclerView this$0;
        final /* synthetic */ ExtendedGridLayoutManager val$layoutManager1;

        public AnonymousClass7(UniversalRecyclerView r1, ExtendedGridLayoutManager r2) {
            this.this$0 = r1;
            this.val$layoutManager1 = r2;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r2) {
            UniversalAdapter r0 = this.this$0.adapter;
            if (r0 == null) goto L5;
            UItem r22 = r0.getItem(r2);
            if (r22 == null) goto L13;
            int r23 = r22.spanCount;
            if (r23 == (-1)) goto L13;
            return r23;
        L13:
            return this.val$layoutManager1.getSpanCount();
        L5:
            return this.val$layoutManager1.getSpanCount();
        }
    }

    /* loaded from: classes7.dex */
    public class TouchHelperCallback extends ItemTouchHelper.Callback {
        final /* synthetic */ UniversalRecyclerView this$0;

        private TouchHelperCallback(UniversalRecyclerView r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ TouchHelperCallback(UniversalRecyclerView r1, UniversalRecyclerViewIA r2) {
            this(r1);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void clearView(RecyclerView r4, RecyclerView.ViewHolder r5) {
            super.clearView(r4, r5);
            this.this$0.setDraggingChild(null);
            r5.itemView.setPressed(false);
            r5.itemView.setAlpha(1.0f);
            boolean r42 = r5.itemView.getBackground() instanceof RichEditor.DraggingDrawable;
            View r2 = r5.itemView;
            if (r42 == false) goto L6;
            ((RichEditor.DraggingDrawable) r2.getBackground()).setDragging(false);
        L9:
            if (this.this$0.isReorderRemoving() == false) goto L12;
            this.this$0.onReorderRemove(r5);
            r5.itemView.animate().scaleX(0.5f).scaleY(0.5f).setDuration(200).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
            return;
        L12:
            return;
        L6:
            if (r2 != UniversalRecyclerView.m13486$$Nest$fgetreorderingBackgroundView(this.this$0)) goto L9;
            r5.itemView.setBackground(UniversalRecyclerView.m13489$$Nest$fgetreorderingOriginalBackground(this.this$0));
            UniversalRecyclerView.m13491$$Nest$fputreorderingBackgroundView(this.this$0, null);
            UniversalRecyclerView.m13492$$Nest$fputreorderingOriginalBackground(this.this$0, null);
            goto L9
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(RecyclerView r2, RecyclerView.ViewHolder r3) {
            if (UniversalRecyclerView.m13485$$Nest$fgetreorderingAllowed(this.this$0) == false) goto L19;
            if (this.this$0.adapter.isReorderItem(r3.getAdapterPosition()) == false) goto L19;
            int r22 = this.this$0.layoutManager.getOrientation();
            UniversalRecyclerView r1 = this.this$0;
            int r32 = 15;
            if (r22 != 0) goto L13;
            if (UniversalRecyclerView.m13488$$Nest$fgetreorderingOnOtherAxis(r1) == true) goto L17;
            r32 = 12;
        L17:
            return ItemTouchHelper.Callback.makeMovementFlags(r32, 0);
        L13:
            if (UniversalRecyclerView.m13488$$Nest$fgetreorderingOnOtherAxis(r1) == true) goto L17;
            r32 = 3;
        L19:
            return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean isLongPressDragEnabled() {
            if (UniversalRecyclerView.m13485$$Nest$fgetreorderingAllowed(this.this$0) == true) goto L5;
            return false;
        L5:
            if (UniversalRecyclerView.m13484$$Nest$fgetreorderHandleOnly(this.this$0) == false) goto L7;
            return false;
        L7:
            if (UniversalRecyclerView.m13487$$Nest$fgetreorderingLongPressEnabled(this.this$0) == false) goto L13;
            return true;
        L13:
            return false;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onChildDraw(Canvas r3, RecyclerView r4, RecyclerView.ViewHolder r5, float r6, float r7, int r8, boolean r9) {
            if (r8 != 2) goto L8;
            if (r9 == true) goto L8;
            if (this.this$0.isReorderRemoving() == false) goto L8;
            return;
        L8:
            super.onChildDraw(r3, r4, r5, r6, r7, r8, r9);
            if (r8 != 2) goto L13;
            if (r9 == false) goto L14;
            this.this$0.onReorderMoved(r5);
            return;
        L14:
            return;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(RecyclerView r3, RecyclerView.ViewHolder r4, RecyclerView.ViewHolder r5) {
            if (this.this$0.adapter.isReorderItem(r4.getAdapterPosition()) == true) goto L5;
            return false;
        L5:
            if (this.this$0.adapter.getReorderSectionId(r4.getAdapterPosition()) != this.this$0.adapter.getReorderSectionId(r5.getAdapterPosition())) goto L11;
            this.this$0.adapter.swapElements(r4.getAdapterPosition(), r5.getAdapterPosition());
            this.this$0.swappedElements();
            return true;
        L11:
            return false;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onMoved(RecyclerView r1, RecyclerView.ViewHolder r2, int r3, RecyclerView.ViewHolder r4, int r5, int r6, int r7) {
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSelectedChanged(RecyclerView.ViewHolder r6, int r7) {
            if (r6 == null) goto L5;
            this.this$0.hideSelector(false);
        L5:
            UniversalRecyclerView r1 = this.this$0;
            if (r7 != 0) goto L10;
            r1.adapter.reorderDone();
            if (UniversalRecyclerView.m13490$$Nest$fgetreorderingViewHolder(this.this$0) == null) goto L19;
            UniversalRecyclerView r0 = this.this$0;
            r0.onReorderEnd(UniversalRecyclerView.m13490$$Nest$fgetreorderingViewHolder(r0));
            UniversalRecyclerView.m13493$$Nest$fputreorderingViewHolder(this.this$0, null);
        L19:
            super.onSelectedChanged(r6, r7);
            return;
        L10:
            r1.cancelClickRunnables(false);
            if (r6 == null) goto L19;
            this.this$0.setDraggingChild(r6.itemView);
            r6.itemView.setAlpha(1.0f);
            r6.itemView.setPressed(true);
            r6.itemView.bringToFront();
            if ((r6.itemView.getBackground() instanceof RichEditor.DraggingDrawable) == false) goto L15;
            ((RichEditor.DraggingDrawable) r6.itemView.getBackground()).setDragging(true);
        L17:
            if (r7 != 2) goto L19;
            UniversalRecyclerView.m13493$$Nest$fputreorderingViewHolder(this.this$0, r6);
            this.this$0.onReorderStart(r6);
            goto L19
        L15:
            if (r7 != 2) goto L17;
            UniversalRecyclerView.m13491$$Nest$fputreorderingBackgroundView(this.this$0, r6.itemView);
            UniversalRecyclerView.m13492$$Nest$fputreorderingOriginalBackground(this.this$0, r6.itemView.getBackground());
            r6.itemView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(ExteraConfig.getSectionRadiusDp()), Theme.getColor(Theme.key_windowBackgroundWhite, this.this$0.resourcesProvider)));
            goto L17
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(RecyclerView.ViewHolder r1, int r2) {
        }
    }

    public static /* synthetic */ void $r8$lambda$OJWSyc48YOjL4jMQnIJby0jYBZE(UniversalRecyclerView r0, Canvas r1, RectF r2, float r3, float r4, float r5) {
        r0.lambda$setSections$5(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$_GCRbihvXsZB7pfhHKEzm-EVHLE, reason: not valid java name */
    public static /* synthetic */ boolean m13480$r8$lambda$_GCRbihvXsZB7pfhHKEzmEVHLE(UniversalRecyclerView r0, Utilities.Callback5Return r1, View r2, int r3, float r4, float r5) {
        return r0.lambda$new$1(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$hq9uB7g_KzJNItBrWJmoTCDIBuU(UniversalRecyclerView r0, Utilities.Callback5 r1, View r2, int r3, float r4, float r5) {
        r0.lambda$new$0(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$pm5WGy9z2eFNC6ZBZ-Nn11viv8Y, reason: not valid java name */
    public static /* synthetic */ Boolean m13481$r8$lambda$pm5WGy9z2eFNC6ZBZNn11viv8Y(UniversalRecyclerView r0, View r1) {
        return r0.lambda$setSections$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$szDyhgDxTaRLouYv1d4VwabTxFM(UniversalRecyclerView r0, View r1) {
        r0.lambda$allowReorder$2(r1);
    }

    /* renamed from: $r8$lambda$tje2B_21SK7qgTrPQodBU3J-OPY, reason: not valid java name */
    public static /* synthetic */ Boolean m13482$r8$lambda$tje2B_21SK7qgTrPQodBU3JOPY(Integer r1) {
        if (UniversalAdapter.isShadow(r1.intValue()) == false) goto L5;
    L9:
        boolean r12 = false;
    L11:
        return Boolean.valueOf(r12);
    L5:
        if (ExteraConfig.getSectionsSeparatedHeaders() == true) goto L7;
    L8:
        r12 = true;
        goto L11
    L7:
        if (UniversalAdapter.isHeader(r1.intValue()) == true) goto L9;
        goto L8
    }

    /* renamed from: -$$Nest$fgetdoNotDetachViews, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13483$$Nest$fgetdoNotDetachViews(UniversalRecyclerView r0) {
        return r0.doNotDetachViews;
    }

    /* renamed from: -$$Nest$fgetreorderHandleOnly, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13484$$Nest$fgetreorderHandleOnly(UniversalRecyclerView r0) {
        return r0.reorderHandleOnly;
    }

    /* renamed from: -$$Nest$fgetreorderingAllowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13485$$Nest$fgetreorderingAllowed(UniversalRecyclerView r0) {
        return r0.reorderingAllowed;
    }

    /* renamed from: -$$Nest$fgetreorderingBackgroundView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m13486$$Nest$fgetreorderingBackgroundView(UniversalRecyclerView r0) {
        return r0.reorderingBackgroundView;
    }

    /* renamed from: -$$Nest$fgetreorderingLongPressEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13487$$Nest$fgetreorderingLongPressEnabled(UniversalRecyclerView r0) {
        return r0.reorderingLongPressEnabled;
    }

    /* renamed from: -$$Nest$fgetreorderingOnOtherAxis, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13488$$Nest$fgetreorderingOnOtherAxis(UniversalRecyclerView r0) {
        return r0.reorderingOnOtherAxis;
    }

    /* renamed from: -$$Nest$fgetreorderingOriginalBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m13489$$Nest$fgetreorderingOriginalBackground(UniversalRecyclerView r0) {
        return r0.reorderingOriginalBackground;
    }

    /* renamed from: -$$Nest$fgetreorderingViewHolder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerView.ViewHolder m13490$$Nest$fgetreorderingViewHolder(UniversalRecyclerView r0) {
        return r0.reorderingViewHolder;
    }

    /* renamed from: -$$Nest$fputreorderingBackgroundView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13491$$Nest$fputreorderingBackgroundView(UniversalRecyclerView r0, View r1) {
        r0.reorderingBackgroundView = r1;
    }

    /* renamed from: -$$Nest$fputreorderingOriginalBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13492$$Nest$fputreorderingOriginalBackground(UniversalRecyclerView r0, Drawable r1) {
        r0.reorderingOriginalBackground = r1;
    }

    /* renamed from: -$$Nest$fputreorderingViewHolder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13493$$Nest$fputreorderingViewHolder(UniversalRecyclerView r0, RecyclerView.ViewHolder r1) {
        r0.reorderingViewHolder = r1;
    }

    public UniversalRecyclerView(Context r10, int r11, int r12, Utilities.Callback2<ArrayList<UItem>, UniversalAdapter> r13, Utilities.Callback5<UItem, View, Integer, Float, Float> r14, Utilities.Callback5Return<UItem, View, Integer, Float, Float, Boolean> r15, Theme.ResourcesProvider r16) {
        this(r10, r11, r12, false, r13, r14, r15, r16);
    }

    public UniversalRecyclerView(Context r12, int r13, int r14, boolean r15, Utilities.Callback2<ArrayList<UItem>, UniversalAdapter> r16, Utilities.Callback5<UItem, View, Integer, Float, Float> r17, Utilities.Callback5Return<UItem, View, Integer, Float, Float, Boolean> r18, Theme.ResourcesProvider r19) {
        this(r12, r13, r14, r15, r16, r17, r18, r19, -1, 1);
    }

    public UniversalRecyclerView(Context r12, int r13, int r14, boolean r15, Utilities.Callback2<ArrayList<UItem>, UniversalAdapter> r16, Utilities.Callback5<UItem, View, Integer, Float, Float> r17, Utilities.Callback5Return<UItem, View, Integer, Float, Float, Boolean> r18, Theme.ResourcesProvider r19, int r20, int r21) {
        super(r12, r19);
        this.reorderingLongPressEnabled = true;
        if (r20 != (-1)) goto L5;
        AnonymousClass1 r2 = new AnonymousClass1(this, r12, r21, false);
        this.layoutManager = r2;
        setLayoutManager(r2);
    L6:
        UniversalAdapter r22 = new UniversalAdapter(this, r12, r13, r14, r15, r16, r19);
        this.adapter = r22;
        setAdapter(r22);
        if (r17 == null) goto L9;
        setOnItemClickListener(new UniversalRecyclerView$$ExternalSyntheticLambda4(this, r17));
    L9:
        if (r18 == null) goto L11;
        setOnItemLongClickListener(new UniversalRecyclerView$$ExternalSyntheticLambda5(this, r18));
    L11:
        AnonymousClass4 r122 = new AnonymousClass4(this);
        r122.setSupportsChangeAnimations(false);
        r122.setDelayAnimations(false);
        r122.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r122.setDurations(350);
        setItemAnimator(r122);
        return;
    L5:
        AnonymousClass2 r3 = new AnonymousClass2(this, r12, r20);
        r3.setSpanSizeLookup(new AnonymousClass3(this, r3));
        this.layoutManager = r3;
        setLayoutManager(r3);
        goto L6
    }

    public UniversalRecyclerView(BaseFragment r9, Utilities.Callback2<ArrayList<UItem>, UniversalAdapter> r10, Utilities.Callback5<UItem, View, Integer, Float, Float> r11, Utilities.Callback5Return<UItem, View, Integer, Float, Float, Boolean> r12) {
        this(r9.getContext(), r9.getCurrentAccount(), r9.getClassGuid(), r10, r11, r12, r9.getResourceProvider());
    }

    private /* synthetic */ void lambda$allowReorder$2(View r2) {
        this.adapter.updateReorder(getChildViewHolder(r2), this.reorderingAllowed);
    }

    private /* synthetic */ void lambda$new$0(Utilities.Callback5 r7, View r8, int r9, float r10, float r11) {
        UItem r1 = this.adapter.getItem(r9);
        if (r1 != null) goto L5;
        return;
    L5:
        r7.run(r1, r8, Integer.valueOf(r9), Float.valueOf(r10), Float.valueOf(r11));
    }

    private /* synthetic */ boolean lambda$new$1(Utilities.Callback5Return r7, View r8, int r9, float r10, float r11) {
        UItem r1 = this.adapter.getItem(r9);
        if (r1 != null) goto L7;
        return false;
    L7:
        return ((Boolean) r7.run(r1, r8, Integer.valueOf(r9), Float.valueOf(r10), Float.valueOf(r11))).booleanValue();
    }

    private /* synthetic */ Boolean lambda$setSections$3(View r3) {
        if (r3.getParent() != this) goto L5;
        RecyclerView.ViewHolder r32 = getChildViewHolder(r3);
        Object r0 = r32.itemView.getTag(R.id.parent_tag);
        if ((r0 instanceof Boolean) == true) goto L9;
    L12:
        int r02 = r32.getAdapterPosition();
        if (r02 == (-1)) goto L20;
        UItem r2 = this.adapter.getItem(r02);
        if (r2 == null) goto L20;
        if (r2.transparent == false) goto L20;
        return Boolean.FALSE;
    L20:
        int r22 = r32.getItemViewType();
        if (ExteraConfig.getSectionsSeparatedHeaders() == false) goto L27;
        if (UniversalAdapter.isHeader(r22) == false) goto L27;
        return Boolean.FALSE;
    L27:
        return Boolean.valueOf(!UniversalAdapter.isShadow(r22));
    L9:
        if (((Boolean) r0).booleanValue() == false) goto L12;
        return Boolean.FALSE;
    L5:
        return Boolean.FALSE;
    }

    private /* synthetic */ void lambda$setSections$5(Canvas r1, RectF r2, float r3, float r4, float r5) {
        super.drawBackgroundRect(r1, r2, r3, r4, r5);
    }

    public void allowReorder(boolean r2) {
        if (this.reorderingAllowed != r2) goto L5;
        return;
    L5:
        UniversalAdapter r0 = this.adapter;
        this.reorderingAllowed = r2;
        r0.updateReorder(r2);
        AndroidUtilities.forEachViews(this, new UniversalRecyclerView$$ExternalSyntheticLambda3(this));
    }

    @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r2) {
        if (hasSections() == true) goto L5;
        this.adapter.drawWhiteSections(r2, this);
    L5:
        super.dispatchDraw(r2);
    }

    public void doNotDetachViews() {
        this.doNotDetachViews = true;
    }

    public void doNotDetachViews(boolean r1) {
        this.doNotDetachViews = r1;
    }

    public UItem findItemByItemId(int r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.adapter.getItemCount()) goto L11;
        UItem r1 = this.adapter.getItem(r0);
        if (r1 == null) goto L10;
        if (r1.id != r4) goto L10;
        return r1;
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        return null;
    }

    public int findPositionByItemId(int r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.adapter.getItemCount()) goto L11;
        UItem r1 = this.adapter.getItem(r0);
        if (r1 == null) goto L10;
        if (r1.id != r3) goto L10;
        return r0;
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        return -1;
    }

    public View findViewByItemId(int r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.adapter.getItemCount()) goto L11;
        UItem r1 = this.adapter.getItem(r0);
        if (r1 == null) goto L10;
        if (r1.id != r3) goto L10;
    L13:
        return findViewByPosition(r0);
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        r0 = -1;
        goto L13
    }

    public View findViewByItemObject(Object r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.adapter.getItemCount()) goto L11;
        UItem r1 = this.adapter.getItem(r0);
        if (r1 == null) goto L10;
        if (r1.object != r3) goto L10;
    L13:
        return findViewByPosition(r0);
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        r0 = -1;
        goto L13
    }

    public int getSpanCount() {
        LinearLayoutManager r1 = this.layoutManager;
        if ((r1 instanceof ExtendedGridLayoutManager) == true) goto L5;
        return -1;
    L5:
        return ((ExtendedGridLayoutManager) r1).getSpanCount();
    }

    public boolean isReorderAllowed() {
        return this.reorderingAllowed;
    }

    public boolean isReorderRemoving() {
        return false;
    }

    public void listenReorder(Utilities.Callback2<Integer, ArrayList<UItem>> r2) {
        listenReorder(r2, false);
    }

    public void listenReorder(Utilities.Callback2<Integer, ArrayList<UItem>> r3, boolean r4) {
        this.reorderingOnOtherAxis = r4;
        ItemTouchHelper r42 = new ItemTouchHelper(new TouchHelperCallback(this, null));
        this.itemTouchHelper = r42;
        r42.attachToRecyclerView(this);
        this.adapter.listenReorder(r3);
    }

    public void makeHorizontal() {
        AnonymousClass5 r0 = new AnonymousClass5(this, getContext(), 0, false);
        this.layoutManager = r0;
        setLayoutManager(r0);
    }

    public void onLayoutUpdate() {
    }

    public void onReorderEnd(RecyclerView.ViewHolder r1) {
    }

    public void onReorderMoved(RecyclerView.ViewHolder r1) {
    }

    public void onReorderRemove(RecyclerView.ViewHolder r1) {
    }

    public void onReorderStart(RecyclerView.ViewHolder r1) {
    }

    public void setReorderHandleOnly(boolean r1) {
        this.reorderHandleOnly = r1;
    }

    public void setReorderLongPressEnabled(boolean r1) {
        this.reorderingLongPressEnabled = r1;
    }

    @Override // org.telegram.ui.Components.RecyclerListView
    public void setSections() {
        setSections(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(ExteraConfig.getSectionRadiusDp()), false);
    }

    @Override // org.telegram.ui.Components.RecyclerListView
    public void setSections(int r8, float r9, boolean r10) {
        super.setSections(new UniversalRecyclerView$$ExternalSyntheticLambda0(this), new UniversalRecyclerView$$ExternalSyntheticLambda1(), r8, r9, new UniversalRecyclerView$$ExternalSyntheticLambda2(this), r10);
    }

    @Override // org.telegram.ui.Components.RecyclerListView
    public void setSections(boolean r3) {
        setSections(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(ExteraConfig.getSectionRadiusDp()), r3);
    }

    public void setSpanCount(int r3) {
        LinearLayoutManager r0 = this.layoutManager;
        if ((r0 instanceof ExtendedGridLayoutManager) == false) goto L6;
        ((ExtendedGridLayoutManager) r0).setSpanCount(r3);
        return;
    L6:
        if (r0 != null) goto L8;
        return;
    L8:
        if (r3 == (-1)) goto L12;
        AnonymousClass6 r02 = new AnonymousClass6(this, getContext(), r3);
        r02.setSpanSizeLookup(new AnonymousClass7(this, r02));
        this.layoutManager = r02;
        setLayoutManager(r02);
        return;
    }

    public void startDrag(RecyclerView.ViewHolder r1) {
        ItemTouchHelper r0 = this.itemTouchHelper;
        if (r0 == null) goto L7;
        if (r1 == null) goto L8;
        r0.startDrag(r1);
        return;
    L8:
        return;
    }

    public void swappedElements() {
    }
}
