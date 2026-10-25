package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes3.dex */
public abstract class BottomSheetWithRecyclerListView extends BottomSheet {
    protected ActionBar actionBar;
    protected boolean actionBarIgnoreTouchEvents;
    protected AnimatedFloat actionBarSlideProgress;
    private ActionBarType actionBarType;
    protected int additionalTitleX;
    private BaseFragment baseFragment;
    protected boolean centerTitle;
    protected boolean clipToActionBar;
    protected int contentHeight;
    EditTextEmoji editTextEmoji;
    protected boolean handleOffset;
    private RectF handleRect;
    public final boolean hasFixedSize;
    protected int headerHeight;
    protected int headerMoveTop;
    protected int headerPaddingBottom;
    protected int headerPaddingTop;
    private final Drawable headerShadowDrawable;
    protected int headerTotalHeight;
    protected boolean ignoreTouchActionBar;
    private float lastTop;
    protected LinearLayoutManager layoutManager;
    public NestedSizeNotifierLayout nestedSizeNotifierLayout;
    protected RecyclerListView recyclerListView;
    private boolean restore;
    public boolean reverseLayout;
    private int savedScrollOffset;
    private int savedScrollPosition;
    private float shadowAlpha;
    private boolean showHandle;
    protected boolean showShadow;
    public final boolean stackFromEnd;
    protected boolean takeTranslationIntoAccount;
    public float topPadding;
    boolean wasDrawn;

    /* renamed from: org.telegram.ui.Components.BottomSheetWithRecyclerListView$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends NestedSizeNotifierLayout {
        final /* synthetic */ BottomSheetWithRecyclerListView this$0;
        final /* synthetic */ boolean val$hasFixedSize;
        final /* synthetic */ boolean val$stackFromEnd;

        public AnonymousClass1(BottomSheetWithRecyclerListView r1, Context r2, boolean r3, boolean r4) {
            this.this$0 = r1;
            this.val$stackFromEnd = r3;
            this.val$hasFixedSize = r4;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r2) {
            this.this$0.preDrawInternal(r2, this);
            super.dispatchDraw(r2);
            this.this$0.postDrawInternal(r2, this);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L8;
            if (r3.getY() >= BottomSheetWithRecyclerListView.access$000(this.this$0).getBounds().top) goto L8;
            this.this$0.dismiss();
        L8:
            return super.dispatchTouchEvent(r3);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r5, View r6, long r7) {
            if (this.val$hasFixedSize == true) goto L11;
            BottomSheetWithRecyclerListView r0 = this.this$0;
            if (r0.clipToActionBar == false) goto L11;
            if (r6 != r0.recyclerListView) goto L11;
            r5.save();
            r5.clipRect(0, this.this$0.actionBar.getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight());
            super.drawChild(r5, r6, r7);
            r5.restore();
            return true;
        L11:
            return super.drawChild(r5, r6, r7);
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            BottomSheetWithRecyclerListView r0 = this.this$0;
            r0.contentHeight = View.MeasureSpec.getSize(r4);
            this.this$0.onPreMeasure(r3, r4);
            if (this.val$stackFromEnd == false) goto L5;
            r4 = View.MeasureSpec.makeMeasureSpec(this.this$0.contentHeight, TLObject.FLAG_30);
        L5:
            super.onMeasure(r3, r4);
        }
    }

    /* renamed from: org.telegram.ui.Components.BottomSheetWithRecyclerListView$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends SizeNotifierFrameLayout {
        private boolean ignoreLayout;
        final /* synthetic */ BottomSheetWithRecyclerListView this$0;
        final /* synthetic */ boolean val$hasFixedSize;
        final /* synthetic */ boolean val$stackFromEnd;

        public AnonymousClass2(BottomSheetWithRecyclerListView r1, Context r2, boolean r3, boolean r4) {
            this.this$0 = r1;
            this.val$stackFromEnd = r3;
            this.val$hasFixedSize = r4;
            super(r2);
            this.ignoreLayout = false;
        }

        private void onMeasureInternal(int r12, int r13) {
            int r0 = View.MeasureSpec.getSize(r12);
            int r1 = View.MeasureSpec.getSize(r13);
            setMeasuredDimension(r0, r1);
            EditTextEmoji r2 = this.this$0.editTextEmoji;
            int r4 = 0;
            if (r2 == null) goto L14;
            if (r2.isWaitingForKeyboardOpen() == true) goto L14;
            if (AndroidUtilities.dp(20.0f) < 0) goto L14;
            if (this.this$0.editTextEmoji.isPopupShowing() == true) goto L14;
            if (this.this$0.editTextEmoji.isAnimatePopupClosing() == true) goto L14;
            this.ignoreLayout = true;
            this.this$0.editTextEmoji.hideEmojiView();
            this.ignoreLayout = false;
        L14:
            if (AndroidUtilities.dp(20.0f) >= 0) goto L16;
        L24:
            int r9 = r13;
            int r132 = getChildCount();
        L25:
            if (r4 >= r132) goto L51;
            View r6 = this.getChildAt(r4);
            if (r6 != null) goto L29;
        L30:
            AnonymousClass2 r5 = this;
            int r7 = r12;
        L50:
            r4 = r4 + 1;
            AnonymousClass2 this = r5;
            r12 = r7;
            goto L25
        L29:
            if (r6.getVisibility() == 8) goto L30;
            EditTextEmoji r22 = this.this$0.editTextEmoji;
            if (r22 != null) goto L34;
        L49:
            r5 = this;
            r7 = r12;
            r5.measureChildWithMargins(r6, r7, 0, r9, 0);
            goto L50
        L34:
            if (r22.isPopupView(r6) == false) goto L49;
            if (AndroidUtilities.isInMultiwindow == true) goto L42;
            if (AndroidUtilities.isTablet() == true) goto L42;
            r6.measure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r6.getLayoutParams().height, TLObject.FLAG_30));
        L42:
            if (AndroidUtilities.isTablet() == false) goto L48;
            int r23 = View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30);
            if (AndroidUtilities.isTablet() == false) goto L46;
            float r52 = 200.0f;
        L47:
            r6.measure(r23, View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(r52), (r1 - AndroidUtilities.statusBarHeight) + this.getPaddingTop()), TLObject.FLAG_30));
            goto L30
        L46:
            r52 = 320.0f;
            goto L47
        L48:
            r6.measure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((r1 - AndroidUtilities.statusBarHeight) + this.getPaddingTop(), TLObject.FLAG_30));
            goto L30
        L51:
            return;
        L16:
            if (BottomSheetWithRecyclerListView.access$100(this.this$0) == true) goto L20;
            EditTextEmoji r24 = this.this$0.editTextEmoji;
            if (r24 == null) goto L20;
            int r25 = r24.getEmojiPadding();
        L22:
            if (AndroidUtilities.isInMultiwindow == true) goto L24;
            r1 = r1 - r25;
            r13 = View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30);
        L20:
            r25 = 0;
            goto L22
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r2) {
            this.this$0.preDrawInternal(r2, this);
            super.dispatchDraw(r2);
            this.this$0.postDrawInternal(r2, this);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L8;
            if (r3.getY() >= BottomSheetWithRecyclerListView.access$200(this.this$0).getBounds().top) goto L8;
            this.this$0.dismiss();
        L8:
            return super.dispatchTouchEvent(r3);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r5, View r6, long r7) {
            if (this.val$hasFixedSize == true) goto L11;
            BottomSheetWithRecyclerListView r0 = this.this$0;
            if (r0.clipToActionBar == false) goto L11;
            if (r6 != r0.recyclerListView) goto L11;
            r5.save();
            r5.clipRect(0, this.this$0.actionBar.getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight());
            super.drawChild(r5, r6, r7);
            r5.restore();
            return true;
        L11:
            return super.drawChild(r5, r6, r7);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r11, int r12, int r13, int r14, int r15) {
            if (this.this$0.editTextEmoji != null) goto L6;
            super.onLayout(r11, r12, r13, r14, r15);
            return;
        L6:
            int r112 = getChildCount();
            int r0 = measureKeyboardHeight();
            int r1 = getPaddingBottom();
            if (BottomSheetWithRecyclerListView.access$300(this.this$0) == false) goto L9;
        L17:
            setBottomClip(r1);
            int r2 = 0;
        L18:
            if (r2 >= r112) goto L53;
            View r3 = getChildAt(r2);
            if (r3.getVisibility() == 8) goto L52;
            FrameLayout.LayoutParams r4 = (FrameLayout.LayoutParams) r3.getLayoutParams();
            int r5 = r3.getMeasuredWidth();
            int r6 = r3.getMeasuredHeight();
            int r7 = r4.gravity;
            if (r7 != (-1)) goto L25;
            r7 = 51;
        L25:
            int r8 = r7 & 112;
            int r72 = r7 & 7;
            if (r72 != 1) goto L28;
            int r73 = (((r14 - r12) - r5) / 2) + r4.leftMargin;
            int r9 = r4.rightMargin;
        L31:
            int r74 = r73 - r9;
        L34:
            if (r8 != 16) goto L36;
            int r82 = ((((r15 - r1) - r13) - r6) / 2) + r4.topMargin;
            int r42 = r4.bottomMargin;
        L41:
            int r43 = r82 - r42;
        L45:
            if ((r3 instanceof EmojiView) == true) goto L47;
        L51:
            r3.layout(r74, r43, r5 + r74, r6 + r43);
            goto L52
        L47:
            if (AndroidUtilities.isTablet() == false) goto L50;
            int r44 = getMeasuredHeight();
            int r83 = r3.getMeasuredHeight();
        L49:
            r43 = r44 - r83;
            goto L51
        L50:
            r44 = getMeasuredHeight() + r0;
            r83 = r3.getMeasuredHeight();
            goto L49
        L36:
            if (r8 != 48) goto L38;
            r43 = r4.topMargin + getPaddingTop();
            goto L45
        L38:
            if (r8 == 80) goto L40;
            r43 = r4.topMargin;
            goto L45
        L40:
            r82 = ((r15 - r1) - r13) - r6;
            r42 = r4.bottomMargin;
            goto L41
        L28:
            if (r72 == 5) goto L30;
            r74 = r4.leftMargin + getPaddingLeft();
            goto L34
        L30:
            r73 = (((r14 - r12) - r5) - r4.rightMargin) - getPaddingRight();
            r9 = BottomSheetWithRecyclerListView.access$400(this.this$0);
        L52:
            r2 = r2 + 1;
            goto L18
        L53:
            notifyHeightChanged();
            return;
        L9:
            if (this.this$0.editTextEmoji == null) goto L17;
            if (r0 > AndroidUtilities.dp(20.0f)) goto L17;
            if (AndroidUtilities.isInMultiwindow == true) goto L17;
            if (AndroidUtilities.isTablet() == true) goto L17;
            r1 = r1 + this.this$0.editTextEmoji.getEmojiPadding();
            goto L17
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            BottomSheetWithRecyclerListView r0 = this.this$0;
            r0.contentHeight = View.MeasureSpec.getSize(r4);
            this.this$0.onPreMeasure(r3, r4);
            if (this.val$stackFromEnd == false) goto L6;
            r4 = View.MeasureSpec.makeMeasureSpec(this.this$0.contentHeight, TLObject.FLAG_30);
        L6:
            if (this.this$0.editTextEmoji == null) goto L9;
            onMeasureInternal(r3, r4);
            return;
        L9:
            super.onMeasure(r3, r4);
        }
    }

    /* renamed from: org.telegram.ui.Components.BottomSheetWithRecyclerListView$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends LinearLayoutManager {
        final /* synthetic */ BottomSheetWithRecyclerListView this$0;

        public AnonymousClass3(BottomSheetWithRecyclerListView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void scrollToPosition(int r1) {
            super.scrollToPosition(r1);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void scrollToPositionWithOffset(int r1, int r2) {
            super.scrollToPositionWithOffset(r1, r2);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void scrollToPositionWithOffset(int r1, int r2, boolean r3) {
            super.scrollToPositionWithOffset(r1, r2, r3);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void smoothScrollToPosition(RecyclerView r1, RecyclerView.State r2, int r3) {
            super.smoothScrollToPosition(r1, r2, r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.BottomSheetWithRecyclerListView$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 extends ActionBar {
        final /* synthetic */ BottomSheetWithRecyclerListView this$0;
        final /* synthetic */ SizeNotifierFrameLayout val$containerView;

        public AnonymousClass4(BottomSheetWithRecyclerListView r1, Context r2, SizeNotifierFrameLayout r3) {
            this.this$0 = r1;
            this.val$containerView = r3;
            super(r2);
        }

        @Override // org.telegram.ui.ActionBar.ActionBar, android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            BottomSheetWithRecyclerListView r0 = this.this$0;
            if (r0.ignoreTouchActionBar == false) goto L9;
            if (r0.actionBarIgnoreTouchEvents == false) goto L9;
            return false;
        L9:
            return super.dispatchTouchEvent(r3);
        }

        @Override // android.view.View
        public void setAlpha(float r2) {
            if (getAlpha() == r2) goto L6;
            super.setAlpha(r2);
            this.val$containerView.invalidate();
            return;
        }

        @Override // android.view.View
        public void setTag(Object r1) {
            super.setTag(r1);
            BottomSheetWithRecyclerListView.m9034$$Nest$mupdateStatusBar(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.BottomSheetWithRecyclerListView$5, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass5 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ BottomSheetWithRecyclerListView this$0;

        public AnonymousClass5(BottomSheetWithRecyclerListView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.dismiss();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.BottomSheetWithRecyclerListView$6, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass6 extends RecyclerView.OnScrollListener {
        final /* synthetic */ BottomSheetWithRecyclerListView this$0;
        final /* synthetic */ SizeNotifierFrameLayout val$containerView;

        public AnonymousClass6(BottomSheetWithRecyclerListView r1, SizeNotifierFrameLayout r2) {
            this.this$0 = r1;
            this.val$containerView = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            this.val$containerView.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.BottomSheetWithRecyclerListView$7, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass7 extends RecyclerListView {
        final /* synthetic */ BottomSheetWithRecyclerListView this$0;

        public AnonymousClass7(BottomSheetWithRecyclerListView r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public boolean canHighlightChildAt(View r1, float r2, float r3) {
            return this.this$0.canHighlightChildAt(r1, r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
            this.this$0.applyScrolledPosition();
            super.onLayout(r2, r3, r4, r5, r6);
        }
    }

    /* renamed from: org.telegram.ui.Components.BottomSheetWithRecyclerListView$8, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass8 extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ BottomSheetWithRecyclerListView this$0;
        final /* synthetic */ RecyclerListView.SelectionAdapter val$adapter;
        final /* synthetic */ Context val$context;

        /* renamed from: org.telegram.ui.Components.BottomSheetWithRecyclerListView$8$1, reason: invalid class name */
        public class AnonymousClass1 extends RecyclerView.AdapterDataObserver {
            final /* synthetic */ AnonymousClass8 this$1;
            final /* synthetic */ RecyclerView.AdapterDataObserver val$observer;

            public AnonymousClass1(AnonymousClass8 r1, RecyclerView.AdapterDataObserver r2) {
                this.this$1 = r1;
                this.val$observer = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                this.val$observer.onChanged();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeChanged(int r2, int r3) {
                this.val$observer.onItemRangeChanged(r2 + (!this.this$1.this$0.reverseLayout ? 1 : 0), r3);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeChanged(int r2, int r3, Object r4) {
                this.val$observer.onItemRangeChanged(r2 + (!this.this$1.this$0.reverseLayout ? 1 : 0), r3, r4);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeInserted(int r2, int r3) {
                this.val$observer.onItemRangeInserted(r2 + (!this.this$1.this$0.reverseLayout ? 1 : 0), r3);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeMoved(int r3, int r4, int r5) {
                RecyclerView.AdapterDataObserver r0 = this.val$observer;
                boolean r2 = this.this$1.this$0.reverseLayout;
                r0.onItemRangeMoved(r3 + (!r2 ? 1 : 0), r4 + (!r2 ? 1 : 0), r5);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeRemoved(int r2, int r3) {
                this.val$observer.onItemRangeRemoved(r2 + (!this.this$1.this$0.reverseLayout ? 1 : 0), r3);
            }
        }

        public AnonymousClass8(BottomSheetWithRecyclerListView r1, RecyclerListView.SelectionAdapter r2, Context r3) {
            this.this$0 = r1;
            this.val$adapter = r2;
            this.val$context = r3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.val$adapter.getItemCount() + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (this.this$0.reverseLayout == false) goto L5;
            int r0 = getItemCount() - 1;
        L6:
            if (r2 != r0) goto L10;
            return -1000;
        L10:
            return this.val$adapter.getItemViewType(r2 - (!this.this$0.reverseLayout ? 1 : 0));
        L5:
            r0 = 0;
            goto L6
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return this.val$adapter.isEnabled(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r2, int r3) {
            if (this.this$0.reverseLayout == false) goto L5;
            int r0 = getItemCount() - 1;
        L6:
            if (r3 == r0) goto L9;
            this.val$adapter.onBindViewHolder(r2, r3 - (!this.this$0.reverseLayout ? 1 : 0));
            return;
        L9:
            return;
        L5:
            r0 = 0;
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != (-1000)) goto L7;
            return new RecyclerListView.Holder(new PaddingView(this.this$0, this.val$context));
        L7:
            return this.val$adapter.onCreateViewHolder(r2, r3);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver r3) {
            this.val$adapter.registerAdapterDataObserver(new AnonymousClass1(this, r3));
        }
    }

    /* loaded from: classes7.dex */
    public enum ActionBarType extends Enum<ActionBarType> {
        private static final /* synthetic */ ActionBarType[] $VALUES = null;
        public static final ActionBarType FADING = null;
        public static final ActionBarType SLIDING = null;

        private static /* synthetic */ ActionBarType[] $values() {
            return new ActionBarType[]{FADING, SLIDING};
        }

        static {
            FADING = new ActionBarType("FADING", 0);
            SLIDING = new ActionBarType("SLIDING", 1);
            $VALUES = $values();
        }

        ActionBarType(String r1, int r2) {
        }

        public static ActionBarType valueOf(String r1) {
            return (ActionBarType) Enum.valueOf(ActionBarType.class, r1);
        }

        public static ActionBarType[] values() {
            return (ActionBarType[]) $VALUES.clone();
        }
    }

    /* loaded from: classes7.dex */
    public class PaddingView extends View {
        final /* synthetic */ BottomSheetWithRecyclerListView this$0;

        public PaddingView(BottomSheetWithRecyclerListView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            setTag(-33024);
        }

        @Override // android.view.View
        public void onMeasure(int r4, int r5) {
            BottomSheetWithRecyclerListView r52 = this.this$0;
            int r0 = r52.contentHeight;
            if (r0 != 0) goto L5;
            int r53 = AndroidUtilities.dp(300.0f);
        L6:
            BottomSheetWithRecyclerListView r02 = this.this$0;
            int r54 = r53 - (((r02.headerTotalHeight - r02.headerHeight) - r02.headerPaddingTop) - r02.headerPaddingBottom);
            if (r54 >= 1) goto L9;
            r54 = 1;
        L9:
            super.onMeasure(r4, View.MeasureSpec.makeMeasureSpec(r54, TLObject.FLAG_30));
            return;
        L5:
            r53 = (int) (r0 * r52.topPadding);
            goto L6
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
            BottomSheetWithRecyclerListView.access$500(this.this$0).invalidate();
        }
    }

    /* loaded from: classes7.dex */
    public static final class Params {
        public final ActionBarType actionBarType;
        public final BottomSheet.EdgeToEdge edgeToEdge;
        public final boolean hasFixedSize;
        public final boolean needFocus;
        public final Theme.ResourcesProvider resourcesProvider;
        public final boolean stackFromEnd;
        public final boolean useNested;

        public static final class Builder {
            private ActionBarType actionBarType;
            private BottomSheet.EdgeToEdge edgeToEdge;
            private boolean hasFixedSize;
            private boolean needFocus;
            private Theme.ResourcesProvider resourcesProvider;
            private boolean stackFromEnd;
            private boolean useNested;

            /* renamed from: -$$Nest$fgetactionBarType, reason: not valid java name */
            public static /* bridge */ /* synthetic */ ActionBarType m9035$$Nest$fgetactionBarType(Builder r0) {
                return r0.actionBarType;
            }

            /* renamed from: -$$Nest$fgetedgeToEdge, reason: not valid java name */
            public static /* bridge */ /* synthetic */ BottomSheet.EdgeToEdge m9036$$Nest$fgetedgeToEdge(Builder r0) {
                return r0.edgeToEdge;
            }

            /* renamed from: -$$Nest$fgethasFixedSize, reason: not valid java name */
            public static /* bridge */ /* synthetic */ boolean m9037$$Nest$fgethasFixedSize(Builder r0) {
                return r0.hasFixedSize;
            }

            /* renamed from: -$$Nest$fgetneedFocus, reason: not valid java name */
            public static /* bridge */ /* synthetic */ boolean m9038$$Nest$fgetneedFocus(Builder r0) {
                return r0.needFocus;
            }

            /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
            public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m9039$$Nest$fgetresourcesProvider(Builder r0) {
                return r0.resourcesProvider;
            }

            /* renamed from: -$$Nest$fgetstackFromEnd, reason: not valid java name */
            public static /* bridge */ /* synthetic */ boolean m9040$$Nest$fgetstackFromEnd(Builder r0) {
                return r0.stackFromEnd;
            }

            /* renamed from: -$$Nest$fgetuseNested, reason: not valid java name */
            public static /* bridge */ /* synthetic */ boolean m9041$$Nest$fgetuseNested(Builder r0) {
                return r0.useNested;
            }

            public Builder() {
                this.edgeToEdge = BottomSheet.EdgeToEdge.NONE;
                this.actionBarType = ActionBarType.FADING;
            }

            public Builder actionBarType(ActionBarType r1) {
                this.actionBarType = r1;
                return this;
            }

            public Params build() {
                return new Params(this, null);
            }

            public Builder edgeToEdge(BottomSheet.EdgeToEdge r1) {
                this.edgeToEdge = r1;
                return this;
            }

            public Builder hasFixedSize(boolean r1) {
                this.hasFixedSize = r1;
                return this;
            }

            public Builder needFocus(boolean r1) {
                this.needFocus = r1;
                return this;
            }

            public Builder resourcesProvider(Theme.ResourcesProvider r1) {
                this.resourcesProvider = r1;
                return this;
            }

            public Builder stackFromEnd(boolean r1) {
                this.stackFromEnd = r1;
                return this;
            }

            public Builder useNested(boolean r1) {
                this.useNested = r1;
                return this;
            }
        }

        private Params(Builder r2) {
            this.needFocus = Builder.m9038$$Nest$fgetneedFocus(r2);
            this.edgeToEdge = Builder.m9036$$Nest$fgetedgeToEdge(r2);
            this.hasFixedSize = Builder.m9037$$Nest$fgethasFixedSize(r2);
            this.useNested = Builder.m9041$$Nest$fgetuseNested(r2);
            this.stackFromEnd = Builder.m9040$$Nest$fgetstackFromEnd(r2);
            this.actionBarType = Builder.m9035$$Nest$fgetactionBarType(r2);
            this.resourcesProvider = Builder.m9039$$Nest$fgetresourcesProvider(r2);
        }

        public /* synthetic */ Params(Builder r1, BottomSheetWithRecyclerListViewIA r2) {
            this(r1);
        }
    }

    /* renamed from: -$$Nest$mupdateStatusBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9034$$Nest$mupdateStatusBar(BottomSheetWithRecyclerListView r0) {
        r0.updateStatusBar();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [org.telegram.ui.ActionBar.BottomSheet, org.telegram.ui.Components.BottomSheetWithRecyclerListView] */
    /* JADX WARN: Type inference failed for: r9v6, types: [org.telegram.ui.Components.BottomSheetWithRecyclerListView$1, org.telegram.ui.Components.NestedSizeNotifierLayout] */
    @SuppressLint({"AppCompatCustomView"})
    public BottomSheetWithRecyclerListView(Context r8, BaseFragment r9, Params r10) {
        super(r8, r10.needFocus, r10.edgeToEdge, r10.resourcesProvider);
        this.topPadding = 0.4f;
        this.showShadow = true;
        this.shadowAlpha = 1.0f;
        this.showHandle = false;
        this.handleRect = new RectF();
        this.actionBarType = ActionBarType.FADING;
        this.headerTotalHeight = 0;
        this.headerHeight = 0;
        this.headerPaddingTop = 0;
        this.headerPaddingBottom = 0;
        this.headerMoveTop = 0;
        this.ignoreTouchActionBar = true;
        this.actionBarIgnoreTouchEvents = false;
        this.takeTranslationIntoAccount = false;
        this.savedScrollPosition = -1;
        boolean r3 = r10.hasFixedSize;
        boolean r4 = r10.useNested;
        boolean r5 = r10.stackFromEnd;
        ActionBarType r102 = r10.actionBarType;
        this.baseFragment = r9;
        this.hasFixedSize = r3;
        this.stackFromEnd = r5;
        this.headerShadowDrawable = ContextCompat.getDrawable(r8, R.drawable.header_shadow).mutate();
        if (r4 == false) goto L5;
        ?? r92 = new AnonymousClass1(this, r8, r5, r3);
        this.nestedSizeNotifierLayout = r92;
        AnonymousClass2 r93 = r92;
    L6:
        this.recyclerListView = createRecyclerView(r8);
        AnonymousClass3 r42 = new AnonymousClass3(this, r8);
        this.layoutManager = r42;
        if (r5 == false) goto L9;
        r42.setStackFromEnd(true);
    L9:
        this.recyclerListView.setLayoutManager(this.layoutManager);
        NestedSizeNotifierLayout r43 = this.nestedSizeNotifierLayout;
        if (r43 == null) goto L12;
        r43.setBottomSheetContainerView(getContainer());
        this.nestedSizeNotifierLayout.setTargetListView(this.recyclerListView);
    L12:
        if (r3 == false) goto L14;
        this.recyclerListView.setHasFixedSize(true);
        RecyclerListView r82 = this.recyclerListView;
        r82.setAdapter(createAdapter(r82));
        setCustomView(r93);
        r93.addView(this.recyclerListView, LayoutHelper.createFrame(-1, -2.0f));
    L16:
        if (r102 != ActionBarType.SLIDING) goto L18;
        setSlidingActionBar();
    L18:
        onViewCreated(r93);
        updateStatusBar();
        return;
    L14:
        resetAdapter(r8);
        this.containerView = r93;
        AnonymousClass4 r2 = new AnonymousClass4(this, r8, r93);
        this.actionBar = r2;
        r2.setBackgroundColor(getThemedColor(Theme.key_dialogBackground));
        this.actionBar.setTitleColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        this.actionBar.setItemsBackgroundColor(getThemedColor(Theme.key_actionBarActionModeDefaultSelector), false);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setItemsColor(getThemedColor(Theme.key_actionBarActionModeDefaultIcon), false);
        this.actionBar.setCastShadows(true);
        this.actionBar.setTitle(getTitle());
        this.actionBar.setForceDisableCenterTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass5(this));
        r93.addView(this.recyclerListView);
        r93.addView(this.actionBar, LayoutHelper.createFrame(-1, -2.0f, 0, 6.0f, 0.0f, 6.0f, 0.0f));
        this.recyclerListView.addOnScrollListener(new AnonymousClass6(this, r93));
        goto L16
    L5:
        r93 = new AnonymousClass2(this, r8, r5, r3);
        goto L6
    }

    public BottomSheetWithRecyclerListView(Context r2, BaseFragment r3, boolean r4, boolean r5, boolean r6, Theme.ResourcesProvider r7) {
        this(r2, r3, new Params.Builder().needFocus(r4).hasFixedSize(r5).useNested(r6).resourcesProvider(r7).build());
    }

    @SuppressLint({"AppCompatCustomView"})
    public BottomSheetWithRecyclerListView(Context r2, BaseFragment r3, boolean r4, boolean r5, boolean r6, ActionBarType r7, Theme.ResourcesProvider r8) {
        this(r2, r3, new Params.Builder().needFocus(r4).hasFixedSize(r5).useNested(r6).actionBarType(r7).resourcesProvider(r8).build());
    }

    @SuppressLint({"AppCompatCustomView"})
    public BottomSheetWithRecyclerListView(Context r2, BaseFragment r3, boolean r4, boolean r5, boolean r6, boolean r7, ActionBarType r8, Theme.ResourcesProvider r9) {
        this(r2, r3, new Params.Builder().needFocus(r4).hasFixedSize(r5).useNested(r6).stackFromEnd(r7).actionBarType(r8).resourcesProvider(r9).build());
    }

    @SuppressLint({"AppCompatCustomView"})
    public BottomSheetWithRecyclerListView(Context r2, BaseFragment r3, boolean r4, boolean r5, boolean r6, boolean r7, boolean r8, ActionBarType r9, Theme.ResourcesProvider r10) {
        Params.Builder r42 = new Params.Builder().needFocus(r4);
        if (r5 == false) goto L5;
        BottomSheet.EdgeToEdge r52 = BottomSheet.EdgeToEdge.V1;
    L6:
        this(r2, r3, r42.edgeToEdge(r52).hasFixedSize(r6).useNested(r7).stackFromEnd(r8).actionBarType(r9).resourcesProvider(r10).build());
        return;
    L5:
        r52 = BottomSheet.EdgeToEdge.NONE;
        goto L6
    }

    public BottomSheetWithRecyclerListView(BaseFragment r3, boolean r4, boolean r5) {
        this(r3.getParentActivity(), r3, new Params.Builder().needFocus(r4).hasFixedSize(r5).resourcesProvider(r3.getResourceProvider()).build());
    }

    public BottomSheetWithRecyclerListView(BaseFragment r3, boolean r4, boolean r5, ActionBarType r6) {
        this(r3.getParentActivity(), r3, new Params.Builder().needFocus(r4).hasFixedSize(r5).actionBarType(r6).resourcesProvider(r3.getResourceProvider()).build());
    }

    public BottomSheetWithRecyclerListView(BaseFragment r3, boolean r4, boolean r5, boolean r6, Theme.ResourcesProvider r7) {
        this(r3.getParentActivity(), r3, new Params.Builder().needFocus(r4).hasFixedSize(r5).useNested(r6).resourcesProvider(r7).build());
    }

    public static /* synthetic */ Drawable access$000(BottomSheetWithRecyclerListView r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ boolean access$100(BottomSheetWithRecyclerListView r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ Drawable access$200(BottomSheetWithRecyclerListView r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ boolean access$300(BottomSheetWithRecyclerListView r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ int access$400(BottomSheetWithRecyclerListView r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ ViewGroup access$500(BottomSheetWithRecyclerListView r0) {
        return r0.containerView;
    }

    private void checkBackDrawableInsets() {
        if (this.backDrawable != null) goto L5;
        return;
    L5:
        if (this.containerView != null) goto L7;
        return;
    L7:
        if (this.shadowDrawable != null) goto L9;
        return;
    L9:
        if (shouldDrawBackground() == true) goto L11;
        return;
    L11:
        if (this.hasFixedSize == true) goto L23;
        Rect r0 = this.shadowDrawable.getBounds();
        int r1 = this.containerView.getMeasuredWidth();
        int r2 = this.container.getMeasuredWidth();
        BottomSheet.SheetBackDrawable r3 = this.backDrawable;
        if (r1 < r2) goto L17;
        r3.setBackgroundInsets(0, 0, 0, ((this.containerView.getMeasuredHeight() - r0.top) - AndroidUtilities.dp(30.0f)) - ((int) this.containerView.getTranslationY()));
        return;
    L17:
        r3.setBackgroundInsets(0, 0, 0, 0);
        return;
    }

    private boolean isLightStatusBar() {
        if (ColorUtils.calculateLuminance(Theme.getColor(Theme.key_dialogBackground, this.resourcesProvider)) <= 0.699999988079071d) goto L6;
        return true;
    L6:
        return false;
    }

    private void updateStatusBar() {
        if (this.attachedFragment == null) goto L6;
        LaunchActivity.instance.checkSystemBarColors(true, true, true);
        return;
    L6:
        ActionBar r0 = this.actionBar;
        if (r0 != null) goto L9;
    L12:
        BaseFragment r02 = this.baseFragment;
        if (r02 == null) goto L16;
        AndroidUtilities.setLightStatusBar(this, r02.isLightStatusBar());
        return;
    L16:
        return;
    L9:
        if (r0.getTag() == null) goto L12;
        AndroidUtilities.setLightStatusBar(this, isLightStatusBar());
    }

    public void applyScrolledPosition() {
        applyScrolledPosition(false);
    }

    public void applyScrolledPosition(boolean r3) {
        RecyclerListView r32 = this.recyclerListView;
        if (r32 != null) goto L5;
        return;
    L5:
        if (r32.getLayoutManager() != null) goto L7;
        return;
    L7:
        if (this.savedScrollPosition < 0) goto L15;
        int r33 = (this.savedScrollOffset - this.containerView.getTop()) - this.recyclerListView.getPaddingTop();
        if ((this.recyclerListView.getLayoutManager() instanceof LinearLayoutManager) == false) goto L11;
        ((LinearLayoutManager) this.recyclerListView.getLayoutManager()).scrollToPositionWithOffset(this.savedScrollPosition, r33);
    L11:
        this.savedScrollPosition = -1;
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    public boolean canHighlightChildAt(View r1, float r2, float r3) {
        return true;
    }

    public abstract RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r1);

    public RecyclerListView createRecyclerView(Context r3) {
        return new AnonymousClass7(this, r3, this.resourcesProvider);
    }

    public int getActionBarProgressHeight() {
        return AndroidUtilities.dp(56.0f);
    }

    public BaseFragment getBaseFragment() {
        return this.baseFragment;
    }

    public abstract CharSequence getTitle();

    @Override // org.telegram.ui.ActionBar.BottomSheet, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public boolean isAttachedLightStatusBar() {
        ActionBar r0 = this.actionBar;
        if (r0 != null) goto L5;
    L8:
        BaseFragment r02 = this.baseFragment;
        if (r02 == null) goto L13;
        return r02.isLightStatusBar();
    L13:
        return isLightStatusBar();
    L5:
        if (r0.getTag() == null) goto L8;
        return isLightStatusBar();
    }

    public boolean needPaddingShadow() {
        return true;
    }

    public void notifyDataSetChanged() {
        this.recyclerListView.getAdapter().notifyDataSetChanged();
    }

    public void onActionBarAlpha(float r1) {
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onContainerViewTranslation() {
        onSheetTop(this.lastTop);
        checkBackDrawableInsets();
    }

    public void onPreDraw(Canvas r1, int r2, float r3) {
    }

    public void onPreMeasure(int r1, int r2) {
    }

    public void onSheetTop(float r1) {
    }

    public void onViewCreated(FrameLayout r1) {
    }

    public void postDrawInternal(Canvas r8, View r9) {
        ActionBarType r0 = this.actionBarType;
        if (r0 != ActionBarType.FADING) goto L26;
        boolean r02 = this.showShadow;
        if (r02 == false) goto L9;
        float r5 = this.shadowAlpha;
        if (r5 == 1.0f) goto L9;
        this.shadowAlpha = r5 + 0.10666667f;
        r9.invalidate();
    L13:
        this.shadowAlpha = Utilities.clamp(this.shadowAlpha, 1.0f, 0.0f);
        ActionBar r03 = this.actionBar;
        if (r03 != null) goto L16;
    L24:
        this.wasDrawn = true;
    L33:
        if (this.restore == false) goto L36;
        r8.restore();
        this.restore = false;
        return;
    L36:
        return;
    L16:
        if (r03.getVisibility() != 0) goto L24;
        if (this.actionBar.getAlpha() == 0.0f) goto L24;
        if (this.shadowAlpha == 0.0f) goto L24;
        this.headerShadowDrawable.setBounds(this.backgroundPaddingLeft, this.actionBar.getBottom(), r9.getMeasuredWidth() - this.backgroundPaddingLeft, this.actionBar.getBottom() + this.headerShadowDrawable.getIntrinsicHeight());
        this.headerShadowDrawable.setAlpha((int) ((this.actionBar.getAlpha() * 255.0f) * this.shadowAlpha));
        this.headerShadowDrawable.draw(r8);
        if (this.headerShadowDrawable.getAlpha() >= 255) goto L24;
        r9.invalidate();
    L9:
        if (r02 == true) goto L13;
        float r04 = this.shadowAlpha;
        if (r04 == 0.0f) goto L13;
        this.shadowAlpha = r04 - 0.10666667f;
        r9.invalidate();
        goto L13
    L26:
        if (r0 != ActionBarType.SLIDING) goto L33;
        if (((int) (this.shadowAlpha * 255.0f)) == 0) goto L33;
        if (this.showShadow == false) goto L33;
        this.headerShadowDrawable.setBounds(this.backgroundPaddingLeft, this.actionBar.getBottom() + ((int) this.actionBar.getTranslationY()), r9.getMeasuredWidth() - this.backgroundPaddingLeft, (this.actionBar.getBottom() + ((int) this.actionBar.getTranslationY())) + this.headerShadowDrawable.getIntrinsicHeight());
        this.headerShadowDrawable.setAlpha((int) (this.shadowAlpha * 255.0f));
        this.headerShadowDrawable.draw(r8);
        goto L33
    }

    public void preDrawInternal(Canvas r13, View r14) {
        this.restore = false;
        if (this.hasFixedSize == true) goto L84;
        boolean r1 = this.reverseLayout;
        RecyclerListView r2 = this.recyclerListView;
        boolean r4 = true;
        if (r1 == false) goto L20;
        int r12 = r2.getHeight();
        int r22 = 0;
    L8:
        if (r22 >= this.recyclerListView.getChildCount()) goto L19;
        View r5 = this.recyclerListView.getChildAt(r22);
        int r6 = this.recyclerListView.getChildAdapterPosition(r5);
        if (r6 == (-1)) goto L18;
        if (r6 == (this.recyclerListView.getAdapter().getItemCount() - 1)) goto L18;
        int r62 = r5.getTop();
        if (this.takeTranslationIntoAccount == false) goto L16;
        int r52 = (int) r5.getTranslationY();
    L17:
        r12 = Math.min(r12, r62 + r52);
        goto L18
    L16:
        r52 = 0;
    L18:
        r22 = r22 + 1;
        goto L8
    L19:
        int r15 = r12 - AndroidUtilities.dp(16.0f);
    L26:
        int r16 = (r15 - ((this.headerHeight + this.headerPaddingTop) + this.headerPaddingBottom)) + this.headerMoveTop;
        if (this.showHandle == true) goto L29;
    L35:
        float r23 = r16;
        this.lastTop = r23;
        onSheetTop(r23);
        ActionBarType r24 = this.actionBarType;
        float r8 = 1.0f;
        if (r24 != ActionBarType.FADING) goto L46;
        float r25 = 1.0f - ((AndroidUtilities.dp(16.0f) + r16) / getActionBarProgressHeight());
        if (r25 >= 0.0f) goto L40;
        r25 = 0.0f;
    L40:
        ActionBar r3 = this.actionBar;
        if (r25 != 0.0f) goto L44;
        r4 = false;
    L44:
        AndroidUtilities.updateViewVisibilityAnimated(r3, r4, 1.0f, this.wasDrawn);
    L68:
        if (shouldDrawBackground() == false) goto L78;
        boolean r32 = needPaddingShadow();
        Drawable r42 = this.shadowDrawable;
        if (r32 == false) goto L72;
        r42.setBounds(0, r16, r14.getMeasuredWidth(), r14.getMeasuredHeight());
    L73:
        checkBackDrawableInsets();
        this.shadowDrawable.draw(r13);
        if (this.showHandle == false) goto L78;
        if (r8 <= 0.0f) goto L78;
        int r0 = AndroidUtilities.dp(36.0f);
        this.handleRect.set((r14.getMeasuredWidth() - r0) / 2.0f, AndroidUtilities.dp(20.0f) + r16, (r14.getMeasuredWidth() + r0) / 2.0f, r3 + AndroidUtilities.dp(4.0f));
        Theme.dialogs_onlineCirclePaint.setColor(getThemedColor(Theme.key_sheet_scrollUp));
        Theme.dialogs_onlineCirclePaint.setAlpha((int) (r14.getAlpha() * r8));
        r13.drawRoundRect(this.handleRect, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), Theme.dialogs_onlineCirclePaint);
        goto L78
    L72:
        r42.setBounds(-AndroidUtilities.dp(6.0f), r16, r14.getMeasuredWidth() + AndroidUtilities.dp(6.0f), r14.getMeasuredHeight());
    L78:
        onPreDraw(r13, r16, r25);
        return;
    L46:
        if (r24 != ActionBarType.SLIDING) goto L66;
        float r26 = Math.max((((r16 - this.headerMoveTop) + AndroidUtilities.dp(8.0f)) + this.headerPaddingTop) - AndroidUtilities.statusBarHeight, 0.0f);
        AnimatedFloat r33 = this.actionBarSlideProgress;
        if (r26 != 0.0f) goto L50;
        float r53 = 1.0f;
    L51:
        float r34 = r33.set(r53);
        if (r34 != 0.0f) goto L54;
    L56:
        this.shadowAlpha = r34;
        r8 = AndroidUtilities.lerp(1.0f, 0.5f, r34);
        this.actionBar.backButtonImageView.setAlpha(r34);
        onActionBarAlpha(r34);
        this.actionBar.backButtonImageView.setScaleX(r34);
        this.actionBar.backButtonImageView.setPivotY(r6.getMeasuredHeight() / 2.0f);
        this.actionBar.backButtonImageView.setScaleY(r34);
        SimpleTextView r63 = this.actionBar.getTitleTextView();
        r63.setTranslationX(AndroidUtilities.lerp(AndroidUtilities.dp(21.0f) - r63.getLeft(), 0.0f, r34) + this.additionalTitleX);
        if (this.centerTitle == false) goto L59;
        r63.setTranslationX(((this.actionBar.getMeasuredWidth() - r63.getTextWidth()) / 2.0f) - r63.getLeft());
    L59:
        this.actionBar.setTranslationY(r26);
        r16 = r16 - AndroidUtilities.lerp(0, (((this.headerTotalHeight - this.headerHeight) - this.headerPaddingTop) - this.headerPaddingBottom) + AndroidUtilities.dp(13.0f), r34);
        this.actionBar.getBackground().setBounds(0, AndroidUtilities.lerp(this.actionBar.getHeight(), 0, r34), this.actionBar.getWidth(), this.actionBar.getHeight());
        boolean r54 = this.actionBarIgnoreTouchEvents;
        if (r34 <= 0.5f) goto L63;
        if (r54 == false) goto L65;
        this.actionBarIgnoreTouchEvents = false;
        this.actionBar.setTag(1);
    L65:
        r25 = r34;
        goto L68
    L63:
        if (r54 == true) goto L65;
        this.actionBarIgnoreTouchEvents = true;
        this.actionBar.setTag(null);
        goto L65
    L54:
        if (r34 == 1.0f) goto L56;
        r13.save();
        r13.clipRect(0.0f, r26, this.containerView.getMeasuredWidth(), this.containerView.getMeasuredHeight());
        this.restore = true;
        goto L56
    L50:
        r53 = 0.0f;
        goto L51
    L66:
        r25 = 0.0f;
        goto L68
    L29:
        if (this.handleOffset == false) goto L35;
        if (this.actionBarType != ActionBarType.SLIDING) goto L33;
        float r27 = 8.0f;
    L34:
        r16 = r16 - AndroidUtilities.dp(r27);
        goto L35
    L33:
        r27 = 16.0f;
        goto L34
    L20:
        RecyclerView.ViewHolder r17 = r2.findViewHolderForAdapterPosition(0);
        int r28 = -AndroidUtilities.dp(16.0f);
        if (r17 == null) goto L25;
        r28 = r17.itemView.getBottom() - AndroidUtilities.dp(16.0f);
        if (this.takeTranslationIntoAccount == false) goto L25;
        r15 = ((int) r17.itemView.getTranslationY()) + r28;
    L25:
        r15 = r28;
        goto L26
    }

    public void resetAdapter(Context r3) {
        AnonymousClass8 r1 = new AnonymousClass8(this, createAdapter(this.recyclerListView), r3);
        this.recyclerListView.setAdapter(r1);
    }

    public void saveScrollPosition() {
        RecyclerListView r0 = this.recyclerListView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.layoutManager != null) goto L7;
        return;
    L7:
        if (r0.getChildCount() <= 0) goto L26;
        View r02 = null;
        int r1 = -1;
        int r2 = Integer.MAX_VALUE;
        int r3 = 0;
    L10:
        if (r3 >= this.recyclerListView.getChildCount()) goto L17;
        View r4 = this.recyclerListView.getChildAt(r3);
        int r5 = this.recyclerListView.getChildAdapterPosition(r4);
        if (r5 < 0) goto L16;
        if (r4.getTop() >= r2) goto L16;
        r2 = r4.getTop();
        r02 = r4;
        r1 = r5;
    L16:
        r3 = r3 + 1;
        goto L10
    L17:
        if (r02 == null) goto L27;
        this.savedScrollPosition = r1;
        this.savedScrollOffset = r02.getTop() + this.containerView.getTop();
        smoothContainerViewLayout();
        return;
    L27:
        return;
    }

    public void setEditTextEmoji(EditTextEmoji r1) {
        this.editTextEmoji = r1;
    }

    public void setShowHandle(boolean r1) {
        this.showHandle = r1;
    }

    public void setShowShadow(boolean r1) {
        this.showShadow = r1;
        this.nestedSizeNotifierLayout.invalidate();
    }

    public void setSlidingActionBar() {
        if (this.hasFixedSize == false) goto L5;
        return;
    L5:
        this.actionBarType = ActionBarType.SLIDING;
        int r0 = ActionBar.getCurrentActionBarHeight();
        this.headerHeight = r0;
        this.headerTotalHeight = r0 + AndroidUtilities.statusBarHeight;
        this.headerPaddingTop = AndroidUtilities.dp(16.0f);
        this.headerPaddingBottom = AndroidUtilities.dp(-20.0f);
        this.actionBarSlideProgress = new AnimatedFloat(this.containerView, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.actionBar.backButtonImageView.setPivotX(0.0f);
        this.recyclerListView.setClipToPadding(true);
    }

    public boolean shouldDrawBackground() {
        return true;
    }

    public void updateTitle() {
        ActionBar r0 = this.actionBar;
        if (r0 == null) goto L6;
        r0.setTitle(getTitle());
        return;
    }

    public void updateTitleAnimated() {
        updateTitleAnimated(true);
    }

    public void updateTitleAnimated(boolean r7) {
        if (this.actionBar == null) goto L11;
        CharSequence r0 = getTitle();
        if (r7 == true) goto L7;
    L9:
        this.actionBar.setTitleAnimated(getTitle(), false, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        return;
    L7:
        if (TextUtils.equals(r0, this.actionBar.getTitle()) == false) goto L9;
        return;
    }
}
