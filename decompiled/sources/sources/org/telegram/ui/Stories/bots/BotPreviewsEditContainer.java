package org.telegram.ui.Stories.bots;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.TranslateController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.SharedPhotoVideoCell;
import org.telegram.ui.Cells.SharedPhotoVideoCell2;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ExtendedGridLayoutManager;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerAnimationScrollHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SharedMediaLayout;
import org.telegram.ui.Components.Size;
import org.telegram.ui.Components.StickerEmptyView;
import org.telegram.ui.Components.TranslateAlert2;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.ViewPagerFixed;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.StoriesListPlaceProvider;
import org.telegram.ui.Stories.StoryViewer;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.StoryEntry;
import org.telegram.ui.Stories.recorder.StoryRecorder;

/* loaded from: classes7.dex */
public abstract class BotPreviewsEditContainer extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private static LongSparseArray<LongSparseArray<BotPreviewsEditContainer>> attachedContainers;
    private static LongSparseArray<LongSparseArray<StoriesController.BotPreviewsList>> cachedLists;
    private final long bot_id;
    private final int currentAccount;
    private final BaseFragment fragment;
    private final ArrayList<StoriesController.BotPreviewsList> langLists;
    private final ArrayList<String> localLangs;
    private final StoriesController.BotPreviewsList mainList;
    private final Theme.ResourcesProvider resourcesProvider;
    private int setColumnsCount;
    private Boolean shownTabs;
    private float tabsAlpha;
    private ValueAnimator tabsAnimator;
    private final ViewPagerFixed.TabsView tabsView;
    private final ViewPagerFixed viewPager;
    private int visibleHeight;

    /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewPagerFixed {
        private String lastLang;
        final /* synthetic */ BotPreviewsEditContainer this$0;

        public AnonymousClass1(BotPreviewsEditContainer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public boolean canScroll(MotionEvent r2) {
            if (this.this$0.isActionModeShowed() == false) goto L7;
            return false;
        L7:
            return super.canScroll(r2);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabAnimationUpdate(boolean r2) {
            String r22 = this.this$0.getCurrentLang();
            if (TextUtils.equals(this.lastLang, r22) == true) goto L6;
            this.lastLang = r22;
            this.this$0.onSelectedTabChanged();
            return;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabPageSelected(int r2) {
            String r22 = this.this$0.getCurrentLang();
            if (TextUtils.equals(this.lastLang, r22) == true) goto L6;
            this.lastLang = r22;
            this.this$0.onSelectedTabChanged();
            return;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabScrollEnd(int r2) {
            super.onTabScrollEnd(r2);
            String r22 = this.this$0.getCurrentLang();
            if (TextUtils.equals(this.lastLang, r22) == true) goto L6;
            this.lastLang = r22;
            this.this$0.onSelectedTabChanged();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewPagerFixed.Adapter {
        final /* synthetic */ BotPreviewsEditContainer this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass2(BotPreviewsEditContainer r1, Context r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void bindView(View r3, int r4, int r5) {
            BotPreviewsEditLangContainer r32 = (BotPreviewsEditLangContainer) r3;
            BotPreviewsEditContainer r52 = this.this$0;
            if (r4 != 0) goto L5;
            StoriesController.BotPreviewsList r42 = BotPreviewsEditContainer.m19842$$Nest$fgetmainList(r52);
        L6:
            r42.load(true, 0, null);
            r32.setList(r42);
            r32.setVisibleHeight(BotPreviewsEditContainer.m19847$$Nest$fgetvisibleHeight(this.this$0));
            return;
        L5:
            r42 = (StoriesController.BotPreviewsList) BotPreviewsEditContainer.m19841$$Nest$fgetlangLists(r52).get(r4 - 1);
            goto L6
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public View createView(int r2) {
            return new BotPreviewsEditLangContainer(this.this$0, this.val$context);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            return BotPreviewsEditContainer.m19841$$Nest$fgetlangLists(this.this$0).size() + 1;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemId(int r1) {
            if (r1 != 0) goto L6;
            return 0;
        L6:
            return ((StoriesController.BotPreviewsList) BotPreviewsEditContainer.m19841$$Nest$fgetlangLists(this.this$0).get(r1 - 1)).lang_code.hashCode();
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public /* bridge */ /* synthetic */ CharSequence getItemTitle(int r1) {
            return getItemTitle(r1);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public String getItemTitle(int r1) {
            if (r1 != 0) goto L6;
            return LocaleController.getString(R.string.ProfileBotLanguageGeneral);
        L6:
            return TranslateAlert2.languageNameCapital(((StoriesController.BotPreviewsList) BotPreviewsEditContainer.m19841$$Nest$fgetlangLists(this.this$0).get(r1 - 1)).lang_code);
        }
    }

    /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ BotPreviewsEditContainer this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass3(BotPreviewsEditContainer r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            BotPreviewsEditContainer r32 = this.this$0;
            float r1 = 0.0f;
            if (this.val$show == false) goto L5;
            float r0 = 1.0f;
        L6:
            BotPreviewsEditContainer.m19849$$Nest$fputtabsAlpha(r32, r0);
            ViewPagerFixed.TabsView r33 = BotPreviewsEditContainer.m19845$$Nest$fgettabsView(this.this$0);
            if (this.val$show == false) goto L9;
            float r02 = 0.0f;
        L10:
            r33.setTranslationY(AndroidUtilities.dp(r02));
            ViewPagerFixed r34 = BotPreviewsEditContainer.m19846$$Nest$fgetviewPager(this.this$0);
            if (this.val$show == false) goto L13;
            r1 = 42.0f;
        L13:
            r34.setTranslationY(AndroidUtilities.dp(r1));
            return;
        L9:
            r02 = -42.0f;
            goto L10
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$4, reason: invalid class name */
    public class AnonymousClass4 implements ChatAttachAlert.ChatAttachViewDelegate {
        final /* synthetic */ BotPreviewsEditContainer this$0;
        final /* synthetic */ ChatAttachAlert val$chatAttachAlert;
        final /* synthetic */ String val$lang_code;

        public AnonymousClass4(BotPreviewsEditContainer r1, ChatAttachAlert r2, String r3) {
            this.this$0 = r1;
            this.val$chatAttachAlert = r2;
            this.val$lang_code = r3;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didPressedButton(int r1, boolean r2, boolean r3, int r4, int r5, long r6, boolean r8, boolean r9, long r10) {
            if (this.val$chatAttachAlert.getPhotoLayout().getSelectedPhotos().isEmpty() == true) goto L12;
            HashMap<Object, Object> r12 = this.val$chatAttachAlert.getPhotoLayout().getSelectedPhotos();
            this.val$chatAttachAlert.getPhotoLayout().getSelectedPhotosOrder();
            if (r12.size() != 1) goto L13;
            Object r13 = r12.values().iterator().next();
            if ((r13 instanceof MediaController.PhotoEntry) == false) goto L14;
            StoryEntry r62 = StoryEntry.fromPhotoEntry((MediaController.PhotoEntry) r13);
            r62.botId = BotPreviewsEditContainer.m19838$$Nest$fgetbot_id(this.this$0);
            r62.botLang = this.val$lang_code;
            r62.setupMatrix();
            StoryRecorder.getInstance(BotPreviewsEditContainer.m19840$$Nest$fgetfragment(this.this$0).getParentActivity(), BotPreviewsEditContainer.m19839$$Nest$fgetcurrentAccount(this.this$0)).openBotEntry(BotPreviewsEditContainer.m19838$$Nest$fgetbot_id(this.this$0), this.val$lang_code, r62, null);
            ChatAttachAlert r0 = this.val$chatAttachAlert;
            Objects.requireNonNull(r0);
            AndroidUtilities.runOnUIThread(new BotPreviewsEditContainer$4$$ExternalSyntheticLambda0(r0), 400);
            return;
        L14:
            return;
        L13:
            return;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public boolean selectItemOnClicking() {
            return true;
        }
    }

    public class BotPreviewsEditLangContainer extends FrameLayout {
        private final StoriesAdapter adapter;
        private boolean allowStoriesSingleColumn;
        private int animateToColumnsCount;
        private boolean columnsAnimation;
        private float columnsAnimationProgress;
        private int columnsCount;
        private final StickerEmptyView emptyView;
        private final ButtonWithCounterView emptyViewButton2;
        private final TextView emptyViewOr;
        private final FooterView footer;
        boolean isInPinchToZoomTouchMode;
        private final DefaultItemAnimator itemAnimator;
        private final ExtendedGridLayoutManager layoutManager;
        private StoriesController.BotPreviewsList list;
        private final SharedMediaLayout.SharedMediaListView listView;
        boolean maybePinchToZoomTouchMode;
        boolean maybePinchToZoomTouchMode2;
        int pinchCenterOffset;
        int pinchCenterPosition;
        int pinchCenterX;
        int pinchCenterY;
        float pinchScale;
        boolean pinchScaleUp;
        float pinchStartDistance;
        private int pointerId1;
        private int pointerId2;
        private final FlickerLoadingView progressView;
        Rect rect;
        private ItemTouchHelper reorder;
        private final RecyclerAnimationScrollHelper scrollHelper;
        private boolean storiesColumnsCountSet;
        private final StoriesAdapter supportingAdapter;
        private final GridLayoutManager supportingLayoutManager;
        private final SharedMediaLayout.InternalListView supportingListView;
        final /* synthetic */ BotPreviewsEditContainer this$0;

        /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$BotPreviewsEditLangContainer$1, reason: invalid class name */
        public class AnonymousClass1 extends ExtendedGridLayoutManager {
            private final Size size;
            final /* synthetic */ BotPreviewsEditLangContainer this$1;
            final /* synthetic */ BotPreviewsEditContainer val$this$0;

            public AnonymousClass1(BotPreviewsEditLangContainer r1, Context r2, int r3, BotPreviewsEditContainer r4) {
                this.this$1 = r1;
                this.val$this$0 = r4;
                super(r2, r3);
                this.size = new Size();
            }

            @Override // androidx.recyclerview.widget.LinearLayoutManager
            public void calculateExtraLayoutSpace(RecyclerView.State r2, int[] r3) {
                super.calculateExtraLayoutSpace(r2, r3);
                r3[1] = Math.max(r3[1], SharedPhotoVideoCell.getItemSize(1) * 2);
            }

            @Override // org.telegram.ui.Components.ExtendedGridLayoutManager
            public int getFlowItemCount() {
                return 0;
            }

            @Override // org.telegram.ui.Components.ExtendedGridLayoutManager
            public Size getSizeForItem(int r1) {
                Size r0 = this.size;
                r0.height = 100.0f;
                r0.width = 100.0f;
                return r0;
            }

            @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler r2, RecyclerView.State r3, View r4, AccessibilityNodeInfoCompat r5) {
                super.onInitializeAccessibilityNodeInfoForItem(r2, r3, r4, r5);
                AccessibilityNodeInfoCompat.CollectionItemInfoCompat r1 = r5.getCollectionItemInfo();
                if (r1 != null) goto L5;
                return;
            L5:
                if (r1.isHeading() == false) goto L9;
                r5.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(r1.getRowIndex(), r1.getRowSpan(), r1.getColumnIndex(), r1.getColumnSpan(), false));
                return;
            }

            @Override // androidx.recyclerview.widget.GridLayoutManager
            public void setSpanCount(int r1) {
                super.setSpanCount(r1);
            }

            @Override // org.telegram.ui.Components.ExtendedGridLayoutManager, androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        }

        /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$BotPreviewsEditLangContainer$10, reason: invalid class name */
        public class AnonymousClass10 extends ItemTouchHelper.Callback {
            final /* synthetic */ BotPreviewsEditLangContainer this$1;
            final /* synthetic */ BotPreviewsEditContainer val$this$0;

            public AnonymousClass10(BotPreviewsEditLangContainer r1, BotPreviewsEditContainer r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void clearView(RecyclerView r1, RecyclerView.ViewHolder r2) {
                super.clearView(r1, r2);
                r2.itemView.setPressed(false);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public int getMovementFlags(RecyclerView r2, RecyclerView.ViewHolder r3) {
                if (this.this$1.this$0.isActionModeShowed() == false) goto L9;
                if (BotPreviewsEditLangContainer.m19852$$Nest$fgetadapter(this.this$1).canReorder(r3.getAdapterPosition()) == false) goto L9;
                BotPreviewsEditLangContainer.m19863$$Nest$fgetlistView(this.this$1).setItemAnimator(BotPreviewsEditLangContainer.m19860$$Nest$fgetitemAnimator(this.this$1));
                return ItemTouchHelper.Callback.makeMovementFlags(15, 0);
            L9:
                return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean isLongPressDragEnabled() {
                return this.this$1.this$0.isActionModeShowed();
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean onMove(RecyclerView r2, RecyclerView.ViewHolder r3, RecyclerView.ViewHolder r4) {
                if (BotPreviewsEditLangContainer.m19852$$Nest$fgetadapter(this.this$1).canReorder(r3.getAdapterPosition()) == true) goto L5;
                return false;
            L5:
                if (BotPreviewsEditLangContainer.m19852$$Nest$fgetadapter(this.this$1).canReorder(r4.getAdapterPosition()) == false) goto L11;
                BotPreviewsEditLangContainer.m19852$$Nest$fgetadapter(this.this$1).swapElements(r3.getAdapterPosition(), r4.getAdapterPosition());
                return true;
            L11:
                return false;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void onSelectedChanged(RecyclerView.ViewHolder r3, int r4) {
                if (r3 == null) goto L5;
                BotPreviewsEditLangContainer.m19863$$Nest$fgetlistView(this.this$1).hideSelector(false);
            L5:
                BotPreviewsEditLangContainer r1 = this.this$1;
                if (r4 != 0) goto L8;
                BotPreviewsEditLangContainer.m19852$$Nest$fgetadapter(r1).reorderDone();
                BotPreviewsEditLangContainer.m19863$$Nest$fgetlistView(this.this$1).setItemAnimator(null);
            L11:
                super.onSelectedChanged(r3, r4);
                return;
            L8:
                BotPreviewsEditLangContainer.m19863$$Nest$fgetlistView(r1).cancelClickRunnables(false);
                if (r3 == null) goto L11;
                r3.itemView.setPressed(true);
                goto L11
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void onSwiped(RecyclerView.ViewHolder r1, int r2) {
            }
        }

        /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$BotPreviewsEditLangContainer$11, reason: invalid class name */
        public class AnonymousClass11 extends GridLayoutManager.SpanSizeLookup {
            final /* synthetic */ BotPreviewsEditLangContainer this$1;

            public AnonymousClass11(BotPreviewsEditLangContainer r1) {
                this.this$1 = r1;
            }

            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int r2) {
                if (BotPreviewsEditLangContainer.m19852$$Nest$fgetadapter(this.this$1).getItemViewType(r2) == 2) goto L5;
                return 1;
            L5:
                return BotPreviewsEditLangContainer.m19857$$Nest$fgetcolumnsCount(this.this$1);
            }
        }

        /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$BotPreviewsEditLangContainer$12, reason: invalid class name */
        public class AnonymousClass12 implements ValueAnimator.AnimatorUpdateListener {
            final /* synthetic */ BotPreviewsEditLangContainer this$1;

            public AnonymousClass12(BotPreviewsEditLangContainer r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator r2) {
                BotPreviewsEditLangContainer.m19871$$Nest$fputcolumnsAnimationProgress(this.this$1, ((Float) r2.getAnimatedValue()).floatValue());
                BotPreviewsEditLangContainer.m19863$$Nest$fgetlistView(this.this$1).invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$BotPreviewsEditLangContainer$13, reason: invalid class name */
        public class AnonymousClass13 extends AnimatorListenerAdapter {
            final /* synthetic */ BotPreviewsEditLangContainer this$1;
            final /* synthetic */ boolean val$forward;

            public AnonymousClass13(BotPreviewsEditLangContainer r1, boolean r2) {
                this.this$1 = r1;
                this.val$forward = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r5) {
                BotPreviewsEditLangContainer.m19870$$Nest$fputcolumnsAnimation(this.this$1, false);
                if (this.val$forward == false) goto L5;
                BotPreviewsEditLangContainer r0 = this.this$1;
                BotPreviewsEditLangContainer.m19872$$Nest$fputcolumnsCount(r0, BotPreviewsEditLangContainer.m19854$$Nest$fgetanimateToColumnsCount(r0));
                BotPreviewsEditLangContainer r02 = this.this$1;
                BotPreviewsEditContainer.m19848$$Nest$fputsetColumnsCount(r02.this$0, BotPreviewsEditLangContainer.m19857$$Nest$fgetcolumnsCount(r02));
                SharedConfig.setStoriesColumnsCount(BotPreviewsEditLangContainer.m19854$$Nest$fgetanimateToColumnsCount(this.this$1));
            L5:
                int r03 = BotPreviewsEditLangContainer.m19852$$Nest$fgetadapter(this.this$1).getItemCount();
                if (this.val$forward == false) goto L11;
                BotPreviewsEditLangContainer.m19861$$Nest$fgetlayoutManager(this.this$1).setSpanCount(BotPreviewsEditLangContainer.m19857$$Nest$fgetcolumnsCount(this.this$1));
                BotPreviewsEditLangContainer.m19863$$Nest$fgetlistView(this.this$1).invalidateItemDecorations();
                int r1 = BotPreviewsEditLangContainer.m19852$$Nest$fgetadapter(this.this$1).getItemCount();
                BotPreviewsEditLangContainer r2 = this.this$1;
                if (r1 != r03) goto L10;
                AndroidUtilities.updateVisibleRows(BotPreviewsEditLangContainer.m19863$$Nest$fgetlistView(r2));
                goto L11
            L10:
                BotPreviewsEditLangContainer.m19852$$Nest$fgetadapter(r2).notifyDataSetChanged();
            L11:
                BotPreviewsEditLangContainer.m19868$$Nest$fgetsupportingListView(this.this$1).setVisibility(8);
                BotPreviewsEditLangContainer r04 = this.this$1;
                if (r04.pinchCenterPosition >= 0) goto L14;
                BotPreviewsEditLangContainer.m19874$$Nest$msaveScrollPosition(r04);
            L20:
                super.onAnimationEnd(r5);
                return;
            L14:
                if (this.val$forward == false) goto L18;
                View r05 = BotPreviewsEditLangContainer.m19867$$Nest$fgetsupportingLayoutManager(r04).findViewByPosition(this.this$1.pinchCenterPosition);
                if (r05 == null) goto L18;
                BotPreviewsEditLangContainer r12 = this.this$1;
                r12.pinchCenterOffset = r05.getTop();
            L18:
                ExtendedGridLayoutManager r06 = BotPreviewsEditLangContainer.m19861$$Nest$fgetlayoutManager(this.this$1);
                BotPreviewsEditLangContainer r13 = this.this$1;
                r06.scrollToPositionWithOffset(r13.pinchCenterPosition, (-BotPreviewsEditLangContainer.m19863$$Nest$fgetlistView(r13).getPaddingTop()) + this.this$1.pinchCenterOffset);
                goto L20
            }
        }

        /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$BotPreviewsEditLangContainer$2, reason: invalid class name */
        public class AnonymousClass2 extends GridLayoutManager.SpanSizeLookup {
            final /* synthetic */ BotPreviewsEditLangContainer this$1;
            final /* synthetic */ BotPreviewsEditContainer val$this$0;

            public AnonymousClass2(BotPreviewsEditLangContainer r1, BotPreviewsEditContainer r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int r2) {
                if (BotPreviewsEditLangContainer.m19852$$Nest$fgetadapter(this.this$1).getItemViewType(r2) == 2) goto L5;
                return 1;
            L5:
                return BotPreviewsEditLangContainer.m19857$$Nest$fgetcolumnsCount(this.this$1);
            }
        }

        /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$BotPreviewsEditLangContainer$3, reason: invalid class name */
        public class AnonymousClass3 extends SharedMediaLayout.SharedMediaListView {
            final /* synthetic */ BotPreviewsEditLangContainer this$1;
            final /* synthetic */ BotPreviewsEditContainer val$this$0;

            public AnonymousClass3(BotPreviewsEditLangContainer r1, Context r2, BotPreviewsEditContainer r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            private int getListBottom(ViewGroup r4) {
                int r3 = 0;
                int r0 = 0;
            L4:
                if (r3 >= r4.getChildCount()) goto L9;
                int r1 = r4.getChildAt(r3).getBottom() - r4.getPaddingTop();
                if (r1 <= r0) goto L8;
                r0 = r1;
            L8:
                r3 = r3 + 1;
                goto L4
            L9:
                return r0;
            }

            @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView, org.telegram.ui.Components.BlurredRecyclerView, org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r3) {
                super.dispatchDraw(r3);
                float r32 = getListBottom(this);
                if (BotPreviewsEditLangContainer.m19855$$Nest$fgetcolumnsAnimation(this.this$1) == false) goto L5;
                r32 = AndroidUtilities.lerp(r32, getListBottom(BotPreviewsEditLangContainer.m19868$$Nest$fgetsupportingListView(this.this$1)), BotPreviewsEditLangContainer.m19856$$Nest$fgetcolumnsAnimationProgress(this.this$1));
            L5:
                FooterView r0 = BotPreviewsEditLangContainer.m19859$$Nest$fgetfooter(this.this$1);
                if (BotPreviewsEditLangContainer.m19852$$Nest$fgetadapter(this.this$1).getItemCount() <= 0) goto L8;
                int r1 = 0;
            L9:
                r0.setVisibility(r1);
                BotPreviewsEditLangContainer.m19859$$Nest$fgetfooter(this.this$1).setTranslationY(r32);
                return;
            L8:
                r1 = 8;
                goto L9
            }

            @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
            public int getAnimateToColumnsCount() {
                return BotPreviewsEditLangContainer.m19854$$Nest$fgetanimateToColumnsCount(this.this$1);
            }

            @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
            public float getChangeColumnsProgress() {
                return BotPreviewsEditLangContainer.m19856$$Nest$fgetcolumnsAnimationProgress(this.this$1);
            }

            @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
            public int getColumnsCount() {
                return BotPreviewsEditLangContainer.m19857$$Nest$fgetcolumnsCount(this.this$1);
            }

            @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
            public RecyclerListView.FastScrollAdapter getMovingAdapter() {
                if (BotPreviewsEditLangContainer.m19864$$Nest$fgetreorder(this.this$1).isIdle() == true) goto L5;
                return null;
            L5:
                if (this.this$1.this$0.isActionModeShowed() == false) goto L8;
                return null;
            L8:
                return BotPreviewsEditLangContainer.m19852$$Nest$fgetadapter(this.this$1);
            }

            @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
            public RecyclerListView.FastScrollAdapter getSupportingAdapter() {
                return BotPreviewsEditLangContainer.m19866$$Nest$fgetsupportingAdapter(this.this$1);
            }

            @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
            public SharedMediaLayout.InternalListView getSupportingListView() {
                return BotPreviewsEditLangContainer.m19868$$Nest$fgetsupportingListView(this.this$1);
            }

            @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
            public boolean isChangeColumnsAnimation() {
                return BotPreviewsEditLangContainer.m19855$$Nest$fgetcolumnsAnimation(this.this$1);
            }

            @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
            public boolean isStories() {
                return true;
            }
        }

        /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$BotPreviewsEditLangContainer$4, reason: invalid class name */
        public class AnonymousClass4 extends RecyclerView.ItemDecoration {
            final /* synthetic */ BotPreviewsEditLangContainer this$1;
            final /* synthetic */ BotPreviewsEditContainer val$this$0;

            public AnonymousClass4(BotPreviewsEditLangContainer r1, BotPreviewsEditContainer r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect r3, View r4, RecyclerView r5, RecyclerView.State r6) {
                if ((r4 instanceof SharedPhotoVideoCell2) == false) goto L18;
                SharedPhotoVideoCell2 r42 = (SharedPhotoVideoCell2) r4;
                int r52 = BotPreviewsEditLangContainer.m19863$$Nest$fgetlistView(this.this$1).getChildAdapterPosition(r42);
                int r2 = BotPreviewsEditLangContainer.m19861$$Nest$fgetlayoutManager(this.this$1).getSpanCount();
                boolean r0 = true;
                if (r52 >= r2) goto L7;
                boolean r1 = true;
            L8:
                r42.isTop = r1;
                int r53 = r52 % r2;
                if (r53 != 0) goto L11;
                boolean r12 = true;
            L12:
                r42.isFirst = r12;
                if (r53 == (r2 - 1)) goto L16;
                r0 = false;
            L16:
                r42.isLast = r0;
                r3.left = 0;
                r3.top = 0;
                r3.bottom = 0;
                r3.right = 0;
                return;
            L11:
                r12 = false;
                goto L12
            L7:
                r1 = false;
                goto L8
            L18:
                r3.left = 0;
                r3.top = 0;
                r3.bottom = 0;
                r3.right = 0;
            }
        }

        /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$BotPreviewsEditLangContainer$5, reason: invalid class name */
        public class AnonymousClass5 extends GridLayoutManager {
            final /* synthetic */ BotPreviewsEditLangContainer this$1;
            final /* synthetic */ BotPreviewsEditContainer val$this$0;

            public AnonymousClass5(BotPreviewsEditLangContainer r1, Context r2, int r3, BotPreviewsEditContainer r4) {
                this.this$1 = r1;
                this.val$this$0 = r4;
                super(r2, r3);
            }

            @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public int scrollVerticallyBy(int r2, RecyclerView.Recycler r3, RecyclerView.State r4) {
                if (BotPreviewsEditLangContainer.m19855$$Nest$fgetcolumnsAnimation(this.this$1) == false) goto L6;
                r2 = 0;
            L6:
                return super.scrollVerticallyBy(r2, r3, r4);
            }

            @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        }

        /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$BotPreviewsEditLangContainer$6, reason: invalid class name */
        public class AnonymousClass6 extends RecyclerView.ItemDecoration {
            final /* synthetic */ BotPreviewsEditLangContainer this$1;
            final /* synthetic */ BotPreviewsEditContainer val$this$0;

            public AnonymousClass6(BotPreviewsEditLangContainer r1, BotPreviewsEditContainer r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect r3, View r4, RecyclerView r5, RecyclerView.State r6) {
                if ((r4 instanceof SharedPhotoVideoCell2) == false) goto L18;
                SharedPhotoVideoCell2 r42 = (SharedPhotoVideoCell2) r4;
                int r52 = BotPreviewsEditLangContainer.m19868$$Nest$fgetsupportingListView(this.this$1).getChildAdapterPosition(r42);
                int r2 = BotPreviewsEditLangContainer.m19867$$Nest$fgetsupportingLayoutManager(this.this$1).getSpanCount();
                boolean r0 = true;
                if (r52 >= r2) goto L7;
                boolean r1 = true;
            L8:
                r42.isTop = r1;
                int r53 = r52 % r2;
                if (r53 != 0) goto L11;
                boolean r12 = true;
            L12:
                r42.isFirst = r12;
                if (r53 == (r2 - 1)) goto L16;
                r0 = false;
            L16:
                r42.isLast = r0;
                r3.left = 0;
                r3.top = 0;
                r3.bottom = 0;
                r3.right = 0;
                return;
            L11:
                r12 = false;
                goto L12
            L7:
                r1 = false;
                goto L8
            L18:
                r3.left = 0;
                r3.top = 0;
                r3.bottom = 0;
                r3.right = 0;
            }
        }

        /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$BotPreviewsEditLangContainer$7, reason: invalid class name */
        public class AnonymousClass7 extends StoriesAdapter {
            final /* synthetic */ BotPreviewsEditLangContainer this$1;
            final /* synthetic */ BotPreviewsEditContainer val$this$0;

            public AnonymousClass7(BotPreviewsEditLangContainer r1, Context r2, BotPreviewsEditContainer r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r1, r2);
            }

            @Override // org.telegram.ui.Stories.bots.BotPreviewsEditContainer.BotPreviewsEditLangContainer.StoriesAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
            public void notifyDataSetChanged() {
                super.notifyDataSetChanged();
                if (BotPreviewsEditLangContainer.m19868$$Nest$fgetsupportingListView(this.this$1).getVisibility() != 0) goto L6;
                BotPreviewsEditLangContainer.m19866$$Nest$fgetsupportingAdapter(this.this$1).notifyDataSetChanged();
            L6:
                if (BotPreviewsEditLangContainer.m19858$$Nest$fgetemptyView(this.this$1) == null) goto L15;
                StickerEmptyView r0 = BotPreviewsEditLangContainer.m19858$$Nest$fgetemptyView(this.this$1);
                StoriesController.StoriesList r1 = this.storiesList;
                if (r1 != null) goto L10;
            L12:
                boolean r12 = false;
            L13:
                r0.showProgress(r12);
                return;
            L10:
                if (r1.isLoading() == false) goto L12;
                r12 = true;
                goto L13
            }
        }

        /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$BotPreviewsEditLangContainer$8, reason: invalid class name */
        public class AnonymousClass8 extends FlickerLoadingView {
            private final Paint backgroundPaint;
            final /* synthetic */ BotPreviewsEditLangContainer this$1;
            final /* synthetic */ BotPreviewsEditContainer val$this$0;

            public AnonymousClass8(BotPreviewsEditLangContainer r1, Context r2, BotPreviewsEditContainer r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
                this.backgroundPaint = new Paint();
            }

            @Override // org.telegram.ui.Components.FlickerLoadingView
            public int getColumnsCount() {
                return BotPreviewsEditLangContainer.m19857$$Nest$fgetcolumnsCount(this.this$1);
            }

            @Override // org.telegram.ui.Components.FlickerLoadingView
            public int getViewType() {
                setIsSingleCell(false);
                return 27;
            }

            @Override // org.telegram.ui.Components.FlickerLoadingView, android.view.View
            public void onDraw(Canvas r8) {
                this.backgroundPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhite, BotPreviewsEditContainer.m19843$$Nest$fgetresourcesProvider(this.this$1.this$0)));
                r8.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.backgroundPaint);
                super.onDraw(r8);
            }
        }

        /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$BotPreviewsEditLangContainer$9, reason: invalid class name */
        public class AnonymousClass9 extends TextView {
            private final Paint paint;
            final /* synthetic */ BotPreviewsEditLangContainer this$1;
            final /* synthetic */ BotPreviewsEditContainer val$this$0;

            public AnonymousClass9(BotPreviewsEditLangContainer r1, Context r2, BotPreviewsEditContainer r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
                this.paint = new Paint(1);
            }

            @Override // android.view.View
            public void dispatchDraw(Canvas r13) {
                int r0 = (getHeight() / 2) + AndroidUtilities.dp(1.0f);
                int r1 = Math.max(1, AndroidUtilities.dp(0.66f));
                Layout r2 = getLayout();
                if (r2 == null) goto L5;
                this.paint.setColor(Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, BotPreviewsEditContainer.m19843$$Nest$fgetresourcesProvider(this.this$1.this$0)), 0.45f));
                float r02 = r0;
                float r12 = r1 / 2.0f;
                float r6 = r02 - r12;
                float r8 = r02 + r12;
                Canvas r4 = r13;
                r4.drawRect(0.0f, r6, (getWidth() - (r2.getLineWidth(0) + AndroidUtilities.dp(16.0f))) / 2.0f, r8, this.paint);
                r4.drawRect(((getWidth() + r2.getLineWidth(0)) + AndroidUtilities.dp(16.0f)) / 2.0f, r6, getWidth(), r8, this.paint);
            L6:
                super.dispatchDraw(r4);
                return;
            L5:
                r4 = r13;
                goto L6
            }
        }

        public class FooterView extends LinearLayout {
            private final ButtonWithCounterView button2View;
            private final ButtonWithCounterView buttonView;
            private final TextView orTextView;
            private final TextView textView;
            final /* synthetic */ BotPreviewsEditLangContainer this$1;

            /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$BotPreviewsEditLangContainer$FooterView$1, reason: invalid class name */
            public class AnonymousClass1 extends ButtonWithCounterView {
                final /* synthetic */ FooterView this$2;
                final /* synthetic */ BotPreviewsEditLangContainer val$this$1;

                public AnonymousClass1(FooterView r1, Context r2, Theme.ResourcesProvider r3, BotPreviewsEditLangContainer r4) {
                    this.this$2 = r1;
                    this.val$this$1 = r4;
                    super(r2, r3);
                }

                @Override // org.telegram.ui.Stories.recorder.ButtonWithCounterView, android.widget.FrameLayout, android.view.View
                public void onMeasure(int r1, int r2) {
                    super.onMeasure(r1, r2);
                }
            }

            /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$BotPreviewsEditLangContainer$FooterView$2, reason: invalid class name */
            public class AnonymousClass2 extends TextView {
                private final Paint paint;
                final /* synthetic */ FooterView this$2;
                final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;
                final /* synthetic */ BotPreviewsEditLangContainer val$this$1;

                public AnonymousClass2(FooterView r1, Context r2, BotPreviewsEditLangContainer r3, Theme.ResourcesProvider r4) {
                    this.this$2 = r1;
                    this.val$this$1 = r3;
                    this.val$resourcesProvider = r4;
                    super(r2);
                    this.paint = new Paint(1);
                }

                @Override // android.view.View
                public void dispatchDraw(Canvas r13) {
                    int r0 = (getHeight() / 2) + AndroidUtilities.dp(1.0f);
                    int r1 = Math.max(1, AndroidUtilities.dp(0.66f));
                    Layout r2 = getLayout();
                    if (r2 == null) goto L5;
                    this.paint.setColor(Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, this.val$resourcesProvider), 0.45f));
                    float r02 = r0;
                    float r12 = r1 / 2.0f;
                    float r6 = r02 - r12;
                    float r8 = r02 + r12;
                    Canvas r4 = r13;
                    r4.drawRect(0.0f, r6, (getWidth() - (r2.getLineWidth(0) + AndroidUtilities.dp(16.0f))) / 2.0f, r8, this.paint);
                    r4.drawRect(((getWidth() + r2.getLineWidth(0)) + AndroidUtilities.dp(16.0f)) / 2.0f, r6, getWidth(), r8, this.paint);
                L6:
                    super.dispatchDraw(r4);
                    return;
                L5:
                    r4 = r13;
                    goto L6
                }
            }

            public static /* synthetic */ void $r8$lambda$7GSeMbWZWMIn95fx2b38bkVzQAA(Runnable r0, View r1) {
                r0.run();
            }

            public static /* synthetic */ void $r8$lambda$dJvtewi1BtezM5iDI28DfA0q5RQ(Runnable r0, View r1) {
                r0.run();
            }

            public FooterView(BotPreviewsEditLangContainer r22, Context r23, Theme.ResourcesProvider r24) {
                this.this$1 = r22;
                super(r23);
                setPadding(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(21.0f), AndroidUtilities.dp(24.0f), AndroidUtilities.dp(21.0f));
                setOrientation(1);
                TextView r5 = new TextView(r23);
                this.textView = r5;
                int r6 = Theme.key_windowBackgroundWhiteGrayText;
                r5.setTextColor(Theme.getColor(r6, r24));
                r5.setTextSize(1, 14.0f);
                r5.setGravity(17);
                r5.setTextAlignment(4);
                addView(r5, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 19.0f));
                AnonymousClass1 r52 = new AnonymousClass1(this, r23, r24, r22);
                this.buttonView = r52;
                r52.setMinWidth(AndroidUtilities.dp(200.0f));
                r52.setText(LocaleController.getString(R.string.ProfileBotAddPreview), false);
                addView(r52, LayoutHelper.createLinear(-2, 44, 17));
                AnonymousClass2 r53 = new AnonymousClass2(this, r23, r22, r24);
                this.orTextView = r53;
                r53.setTextColor(Theme.getColor(r6, r24));
                r53.setText(LocaleController.getString(R.string.ProfileBotOr));
                r53.setTextSize(1, 14.0f);
                r53.setTextAlignment(4);
                r53.setGravity(17);
                r53.setTypeface(AndroidUtilities.bold());
                addView(r53, LayoutHelper.createLinear(165, -2, 17, 0, 17, 0, 12));
                ButtonWithCounterView r1 = new ButtonWithCounterView(r23, false, r24);
                this.button2View = r1;
                r1.setMinWidth(AndroidUtilities.dp(200.0f));
                addView(r1, LayoutHelper.createLinear(-2, 44, 17));
            }

            public void set(CharSequence r2, CharSequence r3, Runnable r4, CharSequence r5, Runnable r6) {
                this.textView.setText(r2);
                this.buttonView.setText(r3, false);
                this.buttonView.setOnClickListener(new BotPreviewsEditContainer$BotPreviewsEditLangContainer$FooterView$$ExternalSyntheticLambda0(r4));
                TextView r22 = this.orTextView;
                if (r5 != null) goto L6;
                r22.setVisibility(8);
                this.button2View.setVisibility(8);
                return;
            L6:
                r22.setVisibility(0);
                this.button2View.setVisibility(0);
                this.button2View.setText(r5, false);
                this.button2View.setOnClickListener(new BotPreviewsEditContainer$BotPreviewsEditLangContainer$FooterView$$ExternalSyntheticLambda1(r6));
            }
        }

        public class StoriesAdapter extends RecyclerListView.FastScrollAdapter {
            public boolean applyingReorder;
            private final Context context;
            FlickerLoadingView globalGradientView;
            public ArrayList<Integer> lastPinnedIds;
            private SharedPhotoVideoCell2.SharedResources sharedResources;
            public StoriesController.StoriesList storiesList;
            private StoriesAdapter supportingAdapter;
            final /* synthetic */ BotPreviewsEditLangContainer this$1;
            private final ArrayList<StoriesController.UploadingStory> uploadingStories;

            /* renamed from: org.telegram.ui.Stories.bots.BotPreviewsEditContainer$BotPreviewsEditLangContainer$StoriesAdapter$1, reason: invalid class name */
            public class AnonymousClass1 extends MessageObject {
                final /* synthetic */ StoriesAdapter this$2;

                public AnonymousClass1(StoriesAdapter r1, int r2, TL_stories.StoryItem r3) {
                    this.this$2 = r1;
                    super(r2, r3);
                }

                @Override // org.telegram.messenger.MessageObject
                public float getProgress() {
                    return this.uploadingStory.progress;
                }
            }

            public StoriesAdapter(BotPreviewsEditLangContainer r1, Context r2) {
                this.this$1 = r1;
                this.uploadingStories = new ArrayList();
                this.lastPinnedIds = new ArrayList();
                this.context = r2;
                checkColumns();
            }

            private void checkColumns() {
                if (this.storiesList != null) goto L6;
                return;
            L6:
                if (BotPreviewsEditLangContainer.m19865$$Nest$fgetstoriesColumnsCountSet(this.this$1) == false) goto L12;
                if (BotPreviewsEditLangContainer.m19853$$Nest$fgetallowStoriesSingleColumn(this.this$1) == true) goto L10;
                return;
            L10:
                if (getItemCount() > 1) goto L12;
                return;
            L12:
                if (getItemCount() <= 0) goto L28;
                int r0 = getItemCount();
                BotPreviewsEditLangContainer r2 = this.this$1;
                boolean r3 = false;
                if (r0 >= 5) goto L20;
                BotPreviewsEditLangContainer.m19872$$Nest$fputcolumnsCount(r2, Math.max(1, getItemCount()));
                BotPreviewsEditLangContainer r02 = this.this$1;
                if (BotPreviewsEditLangContainer.m19857$$Nest$fgetcolumnsCount(r02) != 1) goto L18;
                r3 = true;
            L18:
                BotPreviewsEditLangContainer.m19869$$Nest$fputallowStoriesSingleColumn(r02, r3);
            L24:
                BotPreviewsEditLangContainer.m19861$$Nest$fgetlayoutManager(this.this$1).setSpanCount(BotPreviewsEditLangContainer.m19857$$Nest$fgetcolumnsCount(this.this$1));
                BotPreviewsEditLangContainer.m19873$$Nest$fputstoriesColumnsCountSet(this.this$1, true);
                return;
            L20:
                if (BotPreviewsEditLangContainer.m19853$$Nest$fgetallowStoriesSingleColumn(r2) == false) goto L22;
            L23:
                BotPreviewsEditLangContainer.m19869$$Nest$fputallowStoriesSingleColumn(this.this$1, false);
                BotPreviewsEditLangContainer.m19872$$Nest$fputcolumnsCount(this.this$1, Math.max(2, SharedConfig.storiesColumnsCount));
                goto L24
            L22:
                if (BotPreviewsEditLangContainer.m19857$$Nest$fgetcolumnsCount(this.this$1) != 1) goto L24;
            }

            private int columnsCount() {
                StoriesAdapter r0 = this.supportingAdapter;
                BotPreviewsEditLangContainer r1 = this.this$1;
                if (this != r0) goto L7;
                return BotPreviewsEditLangContainer.m19854$$Nest$fgetanimateToColumnsCount(r1);
            L7:
                return BotPreviewsEditLangContainer.m19857$$Nest$fgetcolumnsCount(r1);
            }

            public boolean canReorder(int r5) {
                StoriesController.StoriesList r0 = this.storiesList;
                if (r0 != null) goto L6;
                return false;
            L6:
                if ((r0 instanceof StoriesController.BotPreviewsList) == false) goto L18;
                TLRPC.User r4 = MessagesController.getInstance(BotPreviewsEditContainer.m19839$$Nest$fgetcurrentAccount(this.this$1.this$0)).getUser(Long.valueOf(BotPreviewsEditContainer.m19838$$Nest$fgetbot_id(this.this$1.this$0)));
                if (r4 != null) goto L10;
            L17:
                return false;
            L10:
                if (r4.bot == false) goto L17;
                if (r4.bot_has_main_app == false) goto L17;
                if (r4.bot_can_edit == false) goto L17;
                return true;
            L18:
                if (r5 >= 0) goto L20;
            L24:
                return false;
            L20:
                if (r5 >= r0.messageObjects.size()) goto L24;
                MessageObject r52 = this.storiesList.messageObjects.get(r5);
                return this.storiesList.isPinned(r52.getId());
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                if (this.storiesList != null) goto L7;
                return 0;
            L7:
                return this.uploadingStories.size() + this.storiesList.getCount();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemViewType(int r1) {
                return 19;
            }

            @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
            public String getLetter(int r3) {
                StoriesController.StoriesList r0 = this.storiesList;
                if (r0 != null) goto L5;
                return null;
            L5:
                if (r3 >= 0) goto L7;
            L16:
                return null;
            L7:
                if (r3 >= r0.messageObjects.size()) goto L16;
                MessageObject r2 = this.storiesList.messageObjects.get(r3);
                if (r2 == null) goto L16;
                if (r2.storyItem == null) goto L16;
                return LocaleController.formatYearMont(r2.date, true);
            }

            @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
            public void getPositionForScrollProgress(RecyclerListView r8, float r9, int[] r10) {
                int r1 = r8.getChildAt(0).getMeasuredHeight();
                int r2 = columnsCount();
                int r7 = (int) (Math.ceil(getTotalItemsCount() / r2) * r1);
                int r3 = r8.getMeasuredHeight() - r8.getPaddingTop();
                if (r1 != 0) goto L6;
                r10[1] = 0;
                r10[0] = 0;
                return;
            L6:
                float r92 = r9 * (r7 - r3);
                r10[0] = ((int) (r92 / r1)) * r2;
                r10[1] = ((int) r92) % r1;
            }

            @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
            public int getTotalItemsCount() {
                return getItemCount();
            }

            @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
            public boolean isEnabled(RecyclerView.ViewHolder r1) {
                return false;
            }

            public StoriesAdapter makeSupporting() {
                BotPreviewsEditLangContainer r1 = this.this$1;
                StoriesAdapter r0 = new StoriesAdapter(r1, r1.getContext());
                this.supportingAdapter = r0;
                return r0;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void notifyDataSetChanged() {
                StoriesController.StoriesList r0 = this.storiesList;
                if ((r0 instanceof StoriesController.BotPreviewsList) == false) goto L17;
                StoriesController.BotPreviewsList r02 = (StoriesController.BotPreviewsList) r0;
                this.uploadingStories.clear();
                ArrayList<StoriesController.UploadingStory> r1 = MessagesController.getInstance(this.storiesList.currentAccount).getStoriesController().getUploadingStories(BotPreviewsEditContainer.m19838$$Nest$fgetbot_id(this.this$1.this$0));
                if (r1 == null) goto L17;
                int r2 = 0;
            L8:
                if (r2 >= r1.size()) goto L17;
                StoriesController.UploadingStory r3 = r1.get(r2);
                StoryEntry r4 = r3.entry;
                if (r4 == null) goto L16;
                if (r4.isEdit == true) goto L16;
                if (TextUtils.equals(r4.botLang, r02.lang_code) == false) goto L16;
                this.uploadingStories.add(r3);
            L16:
                r2 = r2 + 1;
            L17:
                super.notifyDataSetChanged();
                StoriesAdapter r03 = this.supportingAdapter;
                if (r03 == null) goto L21;
                r03.notifyDataSetChanged();
            L21:
                if (this == BotPreviewsEditLangContainer.m19866$$Nest$fgetsupportingAdapter(this.this$1)) goto L29;
                checkColumns();
                BotPreviewsEditLangContainer.m19875$$Nest$mupdateFooter(this.this$1);
                return;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r6, int r7) {
                if (this.storiesList == null) goto L37;
                r6.getItemViewType();
                View r62 = r6.itemView;
                if ((r62 instanceof SharedPhotoVideoCell2) == true) goto L8;
                return;
            L8:
                SharedPhotoVideoCell2 r63 = (SharedPhotoVideoCell2) r62;
                r63.isStory = true;
                if (r7 >= 0) goto L11;
            L17:
                int r72 = r7 - this.uploadingStories.size();
                if (r72 >= 0) goto L20;
            L35:
                r63.isStoryPinned = false;
                r63.setMessageObject(null, columnsCount());
                r63.isStory = true;
                return;
            L20:
                if (r72 >= this.storiesList.messageObjects.size()) goto L35;
                MessageObject r73 = this.storiesList.messageObjects.get(r72);
                if (r73 != null) goto L25;
            L27:
                boolean r2 = false;
            L28:
                r63.isStoryPinned = r2;
                r63.setReorder(true);
                r63.setMessageObject(r73, columnsCount());
                if (this.this$1.this$0.isActionModeShowed() == false) goto L33;
                if (r73 == null) goto L33;
                r63.setChecked(this.this$1.this$0.isSelected(r73), true);
                return;
            L33:
                r63.setChecked(false, false);
                return;
            L25:
                if (this.storiesList.isPinned(r73.getId()) == false) goto L27;
                r2 = true;
                goto L28
            L11:
                if (r7 >= this.uploadingStories.size()) goto L17;
                StoriesController.UploadingStory r74 = this.uploadingStories.get(r7);
                r63.isStoryPinned = false;
                if (r74.sharedMessageObject != null) goto L15;
                TL_stories.TL_storyItem r22 = new TL_stories.TL_storyItem();
                int r3 = Long.hashCode(r74.random_id);
                r22.messageId = r3;
                r22.id = r3;
                r22.attachPath = r74.firstFramePath;
                AnonymousClass1 r32 = new AnonymousClass1(this, this.storiesList.currentAccount, r22);
                r74.sharedMessageObject = r32;
                r32.uploadingStory = r74;
            L15:
                r63.setMessageObject(r74.sharedMessageObject, columnsCount());
                r63.isStory = true;
                r63.setReorder(false);
                r63.setChecked(false, false);
                return;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
                if (this.sharedResources != null) goto L5;
                this.sharedResources = new SharedPhotoVideoCell2.SharedResources(r3.getContext(), BotPreviewsEditContainer.m19843$$Nest$fgetresourcesProvider(this.this$1.this$0));
            L5:
                SharedPhotoVideoCell2 r32 = new SharedPhotoVideoCell2(this.context, this.sharedResources, BotPreviewsEditContainer.m19839$$Nest$fgetcurrentAccount(this.this$1.this$0));
                r32.setCheck2();
                r32.setGradientView(this.globalGradientView);
                r32.isStory = true;
                return new RecyclerListView.Holder(r32);
            }

            @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
            public void onFastScrollSingleTap() {
            }

            public void reorderDone() {
                StoriesController.StoriesList r0 = this.storiesList;
                if (r0 != null) goto L6;
                return;
            L6:
                if (this.applyingReorder == true) goto L9;
                return;
            L9:
                if ((r0 instanceof StoriesController.BotPreviewsList) == false) goto L14;
                ArrayList<Integer> r02 = new ArrayList();
                int r1 = 0;
            L12:
                if (r1 >= this.storiesList.messageObjects.size()) goto L16;
                r02.add(Integer.valueOf(this.storiesList.messageObjects.get(r1).getId()));
                r1 = r1 + 1;
            L16:
                if (this.lastPinnedIds.size() == r02.size()) goto L18;
                boolean r12 = true;
            L19:
                if (r12 == true) goto L27;
                int r3 = 0;
            L22:
                if (r3 >= this.lastPinnedIds.size()) goto L27;
                if (this.lastPinnedIds.get(r3) != r02.get(r3)) goto L25;
                r3 = r3 + 1;
                goto L22
            L25:
                r12 = true;
            L27:
                if (r12 == false) goto L29;
                this.storiesList.updatePinnedOrder(r02, true);
            L29:
                this.applyingReorder = false;
                return;
            L18:
                r12 = false;
                goto L19
            L14:
                r02 = r0.pinnedIds;
                goto L16
            }

            public void setList(StoriesController.StoriesList r1) {
                this.storiesList = r1;
                if (this == BotPreviewsEditLangContainer.m19866$$Nest$fgetsupportingAdapter(this.this$1)) goto L5;
                checkColumns();
            L5:
                notifyDataSetChanged();
            }

            public boolean swapElements(int r6, int r7) {
                StoriesController.StoriesList r0 = this.storiesList;
                if (r0 != null) goto L5;
                return false;
            L5:
                if (r6 >= 0) goto L7;
            L25:
                return false;
            L7:
                if (r6 >= r0.messageObjects.size()) goto L25;
                if (r7 < 0) goto L25;
                if (r7 >= this.storiesList.messageObjects.size()) goto L25;
                if ((this.storiesList instanceof StoriesController.BotPreviewsList) == false) goto L19;
                ArrayList<Integer> r02 = new ArrayList();
                int r2 = 0;
            L17:
                if (r2 >= this.storiesList.messageObjects.size()) goto L21;
                r02.add(Integer.valueOf(this.storiesList.messageObjects.get(r2).getId()));
                r2 = r2 + 1;
            L21:
                if (this.applyingReorder == true) goto L23;
                this.lastPinnedIds.clear();
                this.lastPinnedIds.addAll(r02);
                this.applyingReorder = true;
            L23:
                MessageObject r22 = this.storiesList.messageObjects.get(r6);
                MessageObject r4 = this.storiesList.messageObjects.get(r7);
                r02.remove(Integer.valueOf(r22.getId()));
                r02.add(Utilities.clamp(r7, r02.size(), 0), Integer.valueOf(r22.getId()));
                this.storiesList.updatePinnedOrder(r02, false);
                notifyItemMoved(r6, r7);
                return true;
            L19:
                r02 = new ArrayList(this.storiesList.pinnedIds);
                goto L21
            }
        }

        /* renamed from: $r8$lambda$1QYC9Jg-3WVrfq0q-Walmn8REBU, reason: not valid java name */
        public static /* synthetic */ void m19850$r8$lambda$1QYC9Jg3WVrfq0qWalmn8REBU(BotPreviewsEditLangContainer r0, View r1) {
            r0.lambda$new$6(r1);
        }

        public static /* synthetic */ boolean $r8$lambda$UDXAPm1EybWHkcBQjKLVRqKyFb8(View r0, MotionEvent r1) {
            return true;
        }

        public static /* synthetic */ boolean $r8$lambda$W1NhB41CWXI3edHEa3i7lzyciG4(BotPreviewsEditLangContainer r0, View r1, int r2) {
            return r0.lambda$new$4(r1, r2);
        }

        /* renamed from: $r8$lambda$hk-BRkhEtW_3E5HNYw-naInBTYE, reason: not valid java name */
        public static /* synthetic */ void m19851$r8$lambda$hkBRkhEtW_3E5HNYwnaInBTYE(BotPreviewsEditLangContainer r0, boolean r1) {
            r0.lambda$updateFooter$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$kyeE8BwS_GuuOrlYz29nYuJ1Wl0(BotPreviewsEditLangContainer r0, View r1) {
            r0.lambda$updateFooter$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$mC7xDqRCKKA0sXn3E82Uqxbewdk(BotPreviewsEditLangContainer r0, View r1, int r2) {
            r0.lambda$new$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$tfmCYp0S_bTYuX1BZWfqUimKPVg(BotPreviewsEditLangContainer r0) {
            r0.lambda$updateFooter$0();
        }

        /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ StoriesAdapter m19852$$Nest$fgetadapter(BotPreviewsEditLangContainer r0) {
            return r0.adapter;
        }

        /* renamed from: -$$Nest$fgetallowStoriesSingleColumn, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m19853$$Nest$fgetallowStoriesSingleColumn(BotPreviewsEditLangContainer r0) {
            return r0.allowStoriesSingleColumn;
        }

        /* renamed from: -$$Nest$fgetanimateToColumnsCount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m19854$$Nest$fgetanimateToColumnsCount(BotPreviewsEditLangContainer r0) {
            return r0.animateToColumnsCount;
        }

        /* renamed from: -$$Nest$fgetcolumnsAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m19855$$Nest$fgetcolumnsAnimation(BotPreviewsEditLangContainer r0) {
            return r0.columnsAnimation;
        }

        /* renamed from: -$$Nest$fgetcolumnsAnimationProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m19856$$Nest$fgetcolumnsAnimationProgress(BotPreviewsEditLangContainer r0) {
            return r0.columnsAnimationProgress;
        }

        /* renamed from: -$$Nest$fgetcolumnsCount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m19857$$Nest$fgetcolumnsCount(BotPreviewsEditLangContainer r0) {
            return r0.columnsCount;
        }

        /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ StickerEmptyView m19858$$Nest$fgetemptyView(BotPreviewsEditLangContainer r0) {
            return r0.emptyView;
        }

        /* renamed from: -$$Nest$fgetfooter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FooterView m19859$$Nest$fgetfooter(BotPreviewsEditLangContainer r0) {
            return r0.footer;
        }

        /* renamed from: -$$Nest$fgetitemAnimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ DefaultItemAnimator m19860$$Nest$fgetitemAnimator(BotPreviewsEditLangContainer r0) {
            return r0.itemAnimator;
        }

        /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ExtendedGridLayoutManager m19861$$Nest$fgetlayoutManager(BotPreviewsEditLangContainer r0) {
            return r0.layoutManager;
        }

        /* renamed from: -$$Nest$fgetlist, reason: not valid java name */
        public static /* bridge */ /* synthetic */ StoriesController.BotPreviewsList m19862$$Nest$fgetlist(BotPreviewsEditLangContainer r0) {
            return r0.list;
        }

        /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SharedMediaLayout.SharedMediaListView m19863$$Nest$fgetlistView(BotPreviewsEditLangContainer r0) {
            return r0.listView;
        }

        /* renamed from: -$$Nest$fgetreorder, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ItemTouchHelper m19864$$Nest$fgetreorder(BotPreviewsEditLangContainer r0) {
            return r0.reorder;
        }

        /* renamed from: -$$Nest$fgetstoriesColumnsCountSet, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m19865$$Nest$fgetstoriesColumnsCountSet(BotPreviewsEditLangContainer r0) {
            return r0.storiesColumnsCountSet;
        }

        /* renamed from: -$$Nest$fgetsupportingAdapter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ StoriesAdapter m19866$$Nest$fgetsupportingAdapter(BotPreviewsEditLangContainer r0) {
            return r0.supportingAdapter;
        }

        /* renamed from: -$$Nest$fgetsupportingLayoutManager, reason: not valid java name */
        public static /* bridge */ /* synthetic */ GridLayoutManager m19867$$Nest$fgetsupportingLayoutManager(BotPreviewsEditLangContainer r0) {
            return r0.supportingLayoutManager;
        }

        /* renamed from: -$$Nest$fgetsupportingListView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SharedMediaLayout.InternalListView m19868$$Nest$fgetsupportingListView(BotPreviewsEditLangContainer r0) {
            return r0.supportingListView;
        }

        /* renamed from: -$$Nest$fputallowStoriesSingleColumn, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19869$$Nest$fputallowStoriesSingleColumn(BotPreviewsEditLangContainer r0, boolean r1) {
            r0.allowStoriesSingleColumn = r1;
        }

        /* renamed from: -$$Nest$fputcolumnsAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19870$$Nest$fputcolumnsAnimation(BotPreviewsEditLangContainer r0, boolean r1) {
            r0.columnsAnimation = r1;
        }

        /* renamed from: -$$Nest$fputcolumnsAnimationProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19871$$Nest$fputcolumnsAnimationProgress(BotPreviewsEditLangContainer r0, float r1) {
            r0.columnsAnimationProgress = r1;
        }

        /* renamed from: -$$Nest$fputcolumnsCount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19872$$Nest$fputcolumnsCount(BotPreviewsEditLangContainer r0, int r1) {
            r0.columnsCount = r1;
        }

        /* renamed from: -$$Nest$fputstoriesColumnsCountSet, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19873$$Nest$fputstoriesColumnsCountSet(BotPreviewsEditLangContainer r0, boolean r1) {
            r0.storiesColumnsCountSet = r1;
        }

        /* renamed from: -$$Nest$msaveScrollPosition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19874$$Nest$msaveScrollPosition(BotPreviewsEditLangContainer r0) {
            r0.saveScrollPosition();
        }

        /* renamed from: -$$Nest$mupdateFooter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19875$$Nest$mupdateFooter(BotPreviewsEditLangContainer r0) {
            r0.updateFooter();
        }

        public BotPreviewsEditLangContainer(BotPreviewsEditContainer r22, Context r23) {
            this.this$0 = r22;
            super(r23);
            this.columnsCount = Utilities.clamp(SharedConfig.storiesColumnsCount, 6, 2);
            this.animateToColumnsCount = Utilities.clamp(SharedConfig.storiesColumnsCount, 6, 2);
            this.allowStoriesSingleColumn = false;
            this.storiesColumnsCountSet = false;
            this.rect = new Rect();
            AnonymousClass1 r4 = new AnonymousClass1(this, r23, 100, r22);
            this.layoutManager = r4;
            r4.setSpanSizeLookup(new AnonymousClass2(this, r22));
            r4.setSpanCount(this.columnsCount);
            DefaultItemAnimator r6 = new DefaultItemAnimator();
            this.itemAnimator = r6;
            r6.setDurations(280);
            r6.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            r6.setSupportsChangeAnimations(false);
            AnonymousClass3 r62 = new AnonymousClass3(this, r23, r22);
            this.listView = r62;
            r62.setScrollingTouchSlop(1);
            r62.setPinnedSectionOffsetY(-AndroidUtilities.dp(2.0f));
            r62.setPadding(0, 0, 0, 0);
            r62.setItemAnimator(null);
            r62.setClipToPadding(false);
            r62.setSectionsType(2);
            r62.setLayoutManager(r4);
            addView(r62, LayoutHelper.createFrame(-1, -1.0f));
            r62.addItemDecoration(new AnonymousClass4(this, r22));
            r62.setOnItemClickListener(new BotPreviewsEditContainer$BotPreviewsEditLangContainer$$ExternalSyntheticLambda0(this));
            r62.setOnItemLongClickListener(new BotPreviewsEditContainer$BotPreviewsEditLangContainer$$ExternalSyntheticLambda1(this));
            SharedMediaLayout.InternalListView r9 = new SharedMediaLayout.InternalListView(r23);
            this.supportingListView = r9;
            AnonymousClass5 r10 = new AnonymousClass5(this, r23, 3, r22);
            this.supportingLayoutManager = r10;
            r9.setLayoutManager(r10);
            r9.addItemDecoration(new AnonymousClass6(this, r22));
            r10.setSpanCount(this.animateToColumnsCount);
            r9.setVisibility(8);
            addView(r9, LayoutHelper.createFrame(-1, -1.0f));
            AnonymousClass7 r11 = new AnonymousClass7(this, r23, r22);
            this.adapter = r11;
            r62.setAdapter(r11);
            StoriesAdapter r112 = r11.makeSupporting();
            this.supportingAdapter = r112;
            r9.setAdapter(r112);
            AnonymousClass8 r92 = new AnonymousClass8(this, r23, r22);
            this.progressView = r92;
            r92.showDate(false);
            StickerEmptyView r113 = new StickerEmptyView(r23, r92, 1);
            this.emptyView = r113;
            r113.setVisibility(8);
            r113.setAnimateLayoutChange(true);
            addView(r113, LayoutHelper.createFrame(-1, -1.0f));
            r113.setOnTouchListener(new BotPreviewsEditContainer$BotPreviewsEditLangContainer$$ExternalSyntheticLambda2());
            r113.showProgress(true, false);
            r113.stickerView.setVisibility(8);
            r113.title.setText(LocaleController.getString(R.string.ProfileBotPreviewEmptyTitle));
            r113.subtitle.setText(LocaleController.formatPluralString("ProfileBotPreviewEmptyText", MessagesController.getInstance(BotPreviewsEditContainer.m19839$$Nest$fgetcurrentAccount(r22)).botPreviewMediasMax, new Object[0]));
            r113.button.setText(LocaleController.getString(R.string.ProfileBotPreviewEmptyButton), false);
            r113.button.setVisibility(0);
            r113.button.setOnClickListener(new BotPreviewsEditContainer$BotPreviewsEditLangContainer$$ExternalSyntheticLambda3(this));
            AnonymousClass9 r102 = new AnonymousClass9(this, r23, r22);
            this.emptyViewOr = r102;
            r102.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, BotPreviewsEditContainer.m19843$$Nest$fgetresourcesProvider(r22)));
            r102.setText(LocaleController.getString(R.string.ProfileBotOr));
            r102.setTextSize(1, 14.0f);
            r102.setTextAlignment(4);
            r102.setGravity(17);
            r102.setTypeface(AndroidUtilities.bold());
            r113.linearLayout.addView(r102, LayoutHelper.createLinear(165, -2, 17, 0, 17, 0, 12));
            ButtonWithCounterView r103 = new ButtonWithCounterView(r23, false, BotPreviewsEditContainer.m19843$$Nest$fgetresourcesProvider(r22));
            this.emptyViewButton2 = r103;
            r103.setMinWidth(AndroidUtilities.dp(200.0f));
            r113.linearLayout.addView(r103, LayoutHelper.createLinear(-2, 44, 17));
            r113.addView(r92, 0, LayoutHelper.createFrame(-1, -1.0f));
            r62.setEmptyView(r113);
            r62.setAnimateEmptyView(true, 0);
            this.scrollHelper = new RecyclerAnimationScrollHelper(r62, r4);
            ItemTouchHelper r3 = new ItemTouchHelper(new AnonymousClass10(this, r22));
            this.reorder = r3;
            r3.attachToRecyclerView(r62);
            FooterView r32 = new FooterView(this, r23, BotPreviewsEditContainer.m19843$$Nest$fgetresourcesProvider(r22));
            this.footer = r32;
            addView(r32, LayoutHelper.createFrame(-1, -2, 48));
        }

        private boolean checkPointerIds(MotionEvent r5) {
            if (r5.getPointerCount() >= 2) goto L6;
            return false;
        L6:
            if (this.pointerId1 != r5.getPointerId(0)) goto L11;
            if (this.pointerId2 != r5.getPointerId(1)) goto L11;
            return true;
        L11:
            if (this.pointerId1 == r5.getPointerId(1)) goto L13;
        L15:
            return false;
        L13:
            if (this.pointerId2 != r5.getPointerId(0)) goto L15;
            return true;
        }

        private void finishPinchToMediaColumnsCount() {
            if (this.columnsAnimation == false) goto L32;
            float r0 = this.columnsAnimationProgress;
            float r1 = 1.0f;
            if (r0 != 1.0f) goto L20;
            this.columnsAnimation = false;
            BotPreviewsEditContainer r02 = this.this$0;
            int r12 = this.animateToColumnsCount;
            this.columnsCount = r12;
            BotPreviewsEditContainer.m19848$$Nest$fputsetColumnsCount(r02, r12);
            SharedConfig.setStoriesColumnsCount(this.animateToColumnsCount);
            int r03 = this.adapter.getItemCount();
            this.supportingListView.setVisibility(8);
            this.layoutManager.setSpanCount(this.columnsCount);
            this.listView.invalidateItemDecorations();
            this.listView.invalidate();
            if (this.adapter.getItemCount() != r03) goto L9;
            AndroidUtilities.updateVisibleRows(this.listView);
        L10:
            int r04 = this.pinchCenterPosition;
            if (r04 < 0) goto L17;
            View r05 = this.supportingLayoutManager.findViewByPosition(r04);
            if (r05 == null) goto L15;
            this.pinchCenterOffset = r05.getTop();
        L15:
            this.layoutManager.scrollToPositionWithOffset(this.pinchCenterPosition, (-this.listView.getPaddingTop()) + this.pinchCenterOffset);
            return;
        L17:
            saveScrollPosition();
            return;
        L9:
            this.adapter.notifyDataSetChanged();
            goto L10
        L20:
            if (r0 != 0.0f) goto L24;
            this.columnsAnimation = false;
            this.supportingListView.setVisibility(8);
            this.listView.invalidate();
            return;
        L24:
            if (r0 <= 0.2f) goto L26;
            boolean r3 = true;
        L27:
            if (r3 == true) goto L30;
            r1 = 0.0f;
        L30:
            ValueAnimator r06 = ValueAnimator.ofFloat(new float[]{r0, r1});
            r06.addUpdateListener(new AnonymousClass12(this));
            r06.addListener(new AnonymousClass13(this, r3));
            r06.setInterpolator(CubicBezierInterpolator.DEFAULT);
            r06.setDuration(200);
            r06.start();
            return;
        L26:
            r3 = false;
            goto L27
        }

        private /* synthetic */ void lambda$new$3(View r5, int r6) {
            if ((r5 instanceof SharedPhotoVideoCell2) == false) goto L20;
            MessageObject r52 = ((SharedPhotoVideoCell2) r5).getMessageObject();
            boolean r62 = this.this$0.isActionModeShowed();
            BotPreviewsEditContainer r0 = this.this$0;
            if (r62 == false) goto L12;
            boolean r63 = r0.isSelected(r52);
            BotPreviewsEditContainer r4 = this.this$0;
            if (r63 == false) goto L10;
            r4.unselect(r52);
            return;
        L10:
            r4.select(r52);
            return;
        L12:
            StoryViewer r64 = BotPreviewsEditContainer.m19840$$Nest$fgetfragment(r0).getOrCreateStoryViewer();
            Context r02 = getContext();
            int r53 = r52.getId();
            StoriesController.BotPreviewsList r1 = this.list;
            StoriesListPlaceProvider r2 = StoriesListPlaceProvider.of(this.listView);
            if ((BotPreviewsEditContainer.m19840$$Nest$fgetfragment(this.this$0) instanceof ProfileActivity) == true) goto L15;
        L17:
            int r42 = 0;
        L18:
            r64.open(r02, r53, r1, r2.addBottomClip(r42));
            return;
        L15:
            if (((ProfileActivity) BotPreviewsEditContainer.m19840$$Nest$fgetfragment(this.this$0)).myProfile == false) goto L17;
            r42 = AndroidUtilities.dp(68.0f);
            goto L18
        }

        private /* synthetic */ boolean lambda$new$4(View r2, int r3) {
            if (this.this$0.isActionModeShowed() == false) goto L6;
            return false;
        L6:
            if ((r2 instanceof SharedPhotoVideoCell2) == false) goto L13;
            MessageObject r22 = ((SharedPhotoVideoCell2) r2).getMessageObject();
            boolean r32 = this.this$0.isSelected(r22);
            BotPreviewsEditContainer r1 = this.this$0;
            if (r32 == false) goto L10;
            r1.unselect(r22);
            return true;
        L10:
            r1.select(r22);
            return true;
        L13:
            return false;
        }

        private /* synthetic */ void lambda$new$6(View r1) {
            BotPreviewsEditContainer r12 = this.this$0;
            StoriesController.BotPreviewsList r0 = this.list;
            if (r0 != null) goto L5;
            String r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            r12.createStory(r02);
            return;
        L5:
            r02 = r0.lang_code;
            goto L6
        }

        private /* synthetic */ void lambda$updateFooter$0() {
            BotPreviewsEditContainer r0 = this.this$0;
            StoriesController.BotPreviewsList r1 = this.list;
            if (r1 != null) goto L5;
            String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            r0.createStory(r12);
            return;
        L5:
            r12 = r1.lang_code;
            goto L6
        }

        private /* synthetic */ void lambda$updateFooter$1(boolean r2) {
            BotPreviewsEditContainer r0 = this.this$0;
            if (r2 == false) goto L6;
            r0.addTranslation();
            return;
        L6:
            r0.deleteLang(this.list.lang_code);
        }

        private /* synthetic */ void lambda$updateFooter$2(View r1) {
            this.this$0.deleteLang(this.list.lang_code);
        }

        private void saveScrollPosition() {
        }

        private void selectPinchPosition(int r4, int r5) {
            this.pinchCenterPosition = -1;
            int r52 = r5 + this.listView.blurTopPadding;
            int r0 = 0;
        L4:
            if (r0 >= this.listView.getChildCount()) goto L9;
            View r1 = this.listView.getChildAt(r0);
            r1.getHitRect(this.rect);
            if (this.rect.contains(r4, r52) == false) goto L8;
            this.pinchCenterPosition = this.listView.getChildLayoutPosition(r1);
            this.pinchCenterOffset = r1.getTop();
        L8:
            r0 = r0 + 1;
            goto L4
        }

        private void startPinchToMediaColumnsCount(boolean r7) {
            if (this.columnsAnimation == false) goto L6;
            return;
        L6:
            if (this.this$0.isActionModeShowed() == true) goto L21;
            int r72 = getNextMediaColumnsCount(this.columnsCount, r7);
            this.animateToColumnsCount = r72;
            if (r72 != this.columnsCount) goto L11;
            return;
        L11:
            if (this.allowStoriesSingleColumn == true) goto L22;
            this.supportingListView.setVisibility(0);
            this.supportingListView.setAdapter(this.supportingAdapter);
            SharedMediaLayout.InternalListView r0 = this.supportingListView;
            r0.setPadding(r0.getPaddingLeft(), 0, this.supportingListView.getPaddingRight(), AndroidUtilities.dp(42.0f) + this.footer.getMeasuredHeight());
            this.supportingLayoutManager.setSpanCount(r72);
            this.supportingListView.invalidateItemDecorations();
            this.supportingLayoutManager.setSpanSizeLookup(new AnonymousClass11(this));
            AndroidUtilities.updateVisibleRows(this.listView);
            this.columnsAnimation = true;
            this.columnsAnimationProgress = 0.0f;
            int r73 = this.pinchCenterPosition;
            if (r73 < 0) goto L17;
            this.supportingLayoutManager.scrollToPositionWithOffset(r73, this.pinchCenterOffset - this.supportingListView.getPaddingTop());
            return;
        L17:
            saveScrollPosition();
            return;
        L22:
            return;
        }

        private void updateFooter() {
            StoriesController.BotPreviewsList r0 = this.list;
            int r1 = 0;
            if (r0 != null) goto L5;
            int r02 = 0;
        L6:
            StoriesController.BotPreviewsList r2 = this.list;
            if (r2 != null) goto L9;
        L12:
            boolean r22 = true;
        L13:
            FooterView r3 = this.footer;
            if (r02 <= 0) goto L16;
            int r5 = 0;
        L17:
            r3.setVisibility(r5);
            FooterView r6 = this.footer;
            if (r22 == false) goto L21;
            String r32 = LocaleController.getString(R.string.ProfileBotPreviewFooterGeneral);
        L20:
            String r7 = r32;
            String r8 = LocaleController.getString(R.string.ProfileBotAddPreview);
            BotPreviewsEditContainer$BotPreviewsEditLangContainer$$ExternalSyntheticLambda4 r9 = new BotPreviewsEditContainer$BotPreviewsEditLangContainer$$ExternalSyntheticLambda4(this);
            BotPreviewsEditContainer$BotPreviewsEditLangContainer$$ExternalSyntheticLambda5 r33 = null;
            if (r22 == true) goto L26;
            if (r02 <= 0) goto L26;
            String r10 = null;
        L30:
            if (r22 == true) goto L33;
            if (r02 <= 0) goto L33;
        L34:
            r6.set(r7, r8, r9, r10, r33);
            StickerEmptyView r03 = this.emptyView;
            if (r22 == false) goto L37;
            r03.title.setVisibility(0);
            this.emptyView.title.setText(LocaleController.getString(R.string.ProfileBotPreviewEmptyTitle));
            this.emptyView.subtitle.setText(LocaleController.formatPluralString("ProfileBotPreviewEmptyText", MessagesController.getInstance(BotPreviewsEditContainer.m19839$$Nest$fgetcurrentAccount(this.this$0)).botPreviewMediasMax, new Object[0]));
            this.emptyView.button.setText(LocaleController.getString(R.string.ProfileBotPreviewEmptyButton), false);
            this.emptyViewOr.setVisibility(8);
            this.emptyViewButton2.setVisibility(8);
        L38:
            ButtonWithCounterView r04 = this.emptyView.button;
            if (this.adapter.getItemCount() < MessagesController.getInstance(BotPreviewsEditContainer.m19839$$Nest$fgetcurrentAccount(this.this$0)).botPreviewMediasMax) goto L41;
            r1 = 8;
        L41:
            r04.setVisibility(r1);
            return;
        L37:
            r03.title.setVisibility(8);
            this.emptyView.subtitle.setText(LocaleController.formatString(R.string.ProfileBotPreviewFooterLanguage, new Object[]{TranslateAlert2.languageName(this.list.lang_code)}));
            this.emptyView.button.setText(LocaleController.getString(R.string.ProfileBotPreviewEmptyButton), false);
            this.emptyViewOr.setVisibility(0);
            this.emptyViewButton2.setVisibility(0);
            this.emptyViewButton2.setText(LocaleController.getString(R.string.ProfileBotPreviewFooterDeleteTranslation), false);
            this.emptyViewButton2.setOnClickListener(new BotPreviewsEditContainer$BotPreviewsEditLangContainer$$ExternalSyntheticLambda6(this));
        L33:
            r33 = new BotPreviewsEditContainer$BotPreviewsEditLangContainer$$ExternalSyntheticLambda5(this, r22);
        L26:
            if (r22 == false) goto L28;
            int r52 = R.string.ProfileBotPreviewFooterCreateTranslation;
        L29:
            r10 = LocaleController.getString(r52);
            goto L30
        L28:
            r52 = R.string.ProfileBotPreviewFooterDeleteTranslation;
            goto L29
        L21:
            r32 = LocaleController.formatString(R.string.ProfileBotPreviewFooterLanguage, new Object[]{TranslateAlert2.languageName(this.list.lang_code)});
            goto L20
        L16:
            r5 = 8;
            goto L17
        L9:
            if (TextUtils.isEmpty(r2.lang_code) == true) goto L12;
            r22 = false;
            goto L13
        L5:
            r02 = r0.getCount();
            goto L6
        }

        public boolean checkPinchToZoom(MotionEvent r15) {
            if (this.list != null) goto L5;
        L104:
            return false;
        L5:
            if (getParent() == null) goto L104;
            if (this.columnsAnimation == false) goto L13;
            if (this.isInPinchToZoomTouchMode == true) goto L13;
            return true;
        L13:
            if (r15.getActionMasked() == 0) goto L91;
            if (r15.getActionMasked() == 5) goto L91;
            if (r15.getActionMasked() != 2) goto L80;
            if (this.isInPinchToZoomTouchMode == false) goto L22;
        L23:
            int r7 = -1;
            int r8 = -1;
            int r3 = 0;
        L25:
            if (r3 >= r15.getPointerCount()) goto L33;
            if (this.pointerId1 != r15.getPointerId(r3)) goto L30;
            r7 = r3;
        L30:
            if (this.pointerId2 != r15.getPointerId(r3)) goto L32;
            r8 = r3;
        L32:
            r3 = r3 + 1;
            goto L25
        L33:
            if (r7 == (-1)) goto L77;
            if (r8 == (-1)) goto L77;
            float r0 = ((float) Math.hypot(r15.getX(r8) - r15.getX(r7), r15.getY(r8) - r15.getY(r7))) / this.pinchStartDistance;
            this.pinchScale = r0;
            if (this.isInPinchToZoomTouchMode == true) goto L48;
            if (r0 <= 1.01f) goto L41;
        L42:
            this.isInPinchToZoomTouchMode = true;
            if (r0 <= 1.0f) goto L45;
            boolean r02 = true;
        L46:
            this.pinchScaleUp = r02;
            startPinchToMediaColumnsCount(r02);
            goto L48
        L45:
            r02 = false;
            goto L46
        L41:
            if (r0 < 0.99f) goto L42;
        L48:
            if (this.isInPinchToZoomTouchMode == false) goto L103;
            boolean r03 = this.pinchScaleUp;
            if (r03 == true) goto L52;
        L53:
            if (r03 == false) goto L55;
        L57:
            float r32 = this.pinchScale;
            if (r03 == false) goto L60;
            float r04 = 1.0f - ((2.0f - r32) / 1.0f);
        L61:
            this.columnsAnimationProgress = Math.max(0.0f, Math.min(1.0f, r04));
        L62:
            float r05 = this.columnsAnimationProgress;
            if (r05 == 1.0f) goto L67;
            if (r05 == 0.0f) goto L67;
        L76:
            this.listView.invalidate();
        L67:
            if (r05 != 1.0f) goto L72;
            int r33 = (int) (this.listView.getMeasuredWidth() / this.animateToColumnsCount);
            float r4 = this.this$0.getStartedTrackingX() / (this.listView.getMeasuredWidth() - r33);
            int r06 = (((int) Math.ceil(this.pinchCenterPosition / this.animateToColumnsCount)) * this.animateToColumnsCount) + ((int) (r4 * (r3 - 1)));
            if (r06 < this.adapter.getItemCount()) goto L71;
            r06 = this.adapter.getItemCount() - 1;
        L71:
            this.pinchCenterPosition = r06;
        L72:
            finishPinchToMediaColumnsCount();
            if (this.columnsAnimationProgress != 0.0f) goto L75;
            this.pinchScaleUp = !this.pinchScaleUp;
        L75:
            startPinchToMediaColumnsCount(this.pinchScaleUp);
            this.pinchStartDistance = (float) Math.hypot(r15.getX(1) - r15.getX(0), r15.getY(1) - r15.getY(0));
            goto L76
        L60:
            r04 = (1.0f - r32) / 0.5f;
            goto L61
        L55:
            if (this.pinchScale <= 1.0f) goto L57;
        L56:
            this.columnsAnimationProgress = 0.0f;
            goto L62
        L52:
            if (this.pinchScale < 1.0f) goto L56;
        L103:
            return this.isInPinchToZoomTouchMode;
        L77:
            this.maybePinchToZoomTouchMode = false;
            this.maybePinchToZoomTouchMode2 = false;
            this.isInPinchToZoomTouchMode = false;
            finishPinchToMediaColumnsCount();
            return false;
        L22:
            if (this.maybePinchToZoomTouchMode2 == true) goto L23;
        L80:
            if (r15.getActionMasked() == 1) goto L88;
            if (r15.getActionMasked() != 6) goto L86;
            if (checkPointerIds(r15) == true) goto L88;
        L86:
            if (r15.getActionMasked() != 3) goto L103;
        L88:
            if (this.isInPinchToZoomTouchMode == false) goto L103;
            this.maybePinchToZoomTouchMode2 = false;
            this.maybePinchToZoomTouchMode = false;
            this.isInPinchToZoomTouchMode = false;
            finishPinchToMediaColumnsCount();
        L91:
            if (this.maybePinchToZoomTouchMode == false) goto L98;
            if (this.isInPinchToZoomTouchMode == true) goto L98;
            if (r15.getPointerCount() != 2) goto L98;
            this.pinchStartDistance = (float) Math.hypot(r15.getX(1) - r15.getX(0), r15.getY(1) - r15.getY(0));
            this.pinchScale = 1.0f;
            this.pointerId1 = r15.getPointerId(0);
            this.pointerId2 = r15.getPointerId(1);
            this.listView.cancelClickRunnables(false);
            this.listView.cancelLongPress();
            this.listView.dispatchTouchEvent(MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0));
            View r07 = (View) getParent();
            this.pinchCenterX = (int) ((((int) ((r15.getX(0) + r15.getX(1)) / 2.0f)) - r07.getX()) - getX());
            int r08 = (int) ((((int) ((r15.getY(0) + r15.getY(1)) / 2.0f)) - r07.getY()) - getY());
            this.pinchCenterY = r08;
            selectPinchPosition(this.pinchCenterX, r08);
            this.maybePinchToZoomTouchMode2 = true;
        L98:
            if (r15.getActionMasked() != 0) goto L103;
            if (((r15.getY() - ((View) getParent()).getY()) - getY()) <= 0.0f) goto L103;
            this.maybePinchToZoomTouchMode = true;
            goto L103
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if (r3 != this.supportingListView) goto L7;
            return true;
        L7:
            return super.drawChild(r2, r3, r4);
        }

        public int getNextMediaColumnsCount(int r3, boolean r4) {
            int r0 = 1;
            if (r4 == true) goto L5;
            int r1 = 1;
        L6:
            int r32 = r3 + r1;
            if (r32 <= 6) goto L12;
            if (r4 == true) goto L10;
            r32 = 9;
            goto L12
        L10:
            r32 = 6;
        L12:
            if (this.allowStoriesSingleColumn == true) goto L16;
            r0 = 2;
        L16:
            return Utilities.clamp(r32, 6, r0);
        L5:
            r1 = -1;
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            super.onMeasure(r4, r5);
            SharedMediaLayout.SharedMediaListView r42 = this.listView;
            int r52 = r42.getPaddingLeft();
            SharedMediaLayout.SharedMediaListView r0 = this.listView;
            r42.setPadding(r52, r0.topPadding, r0.getPaddingRight(), AndroidUtilities.dp(42.0f) + this.footer.getMeasuredHeight());
        }

        public void setList(StoriesController.BotPreviewsList r2) {
            if (this.list == r2) goto L5;
            this.allowStoriesSingleColumn = false;
            this.storiesColumnsCountSet = false;
            this.columnsCount = BotPreviewsEditContainer.m19844$$Nest$fgetsetColumnsCount(this.this$0);
        L5:
            this.list = r2;
            this.adapter.setList(r2);
            this.supportingAdapter.setList(r2);
            updateFooter();
        }

        public void setVisibleHeight(int r3) {
            float r32 = (-(getMeasuredHeight() - Math.max(r3, AndroidUtilities.dp(280.0f)))) / 2.0f;
            this.emptyView.setTranslationY(r32);
            this.progressView.setTranslationY(-r32);
        }

        public void updateSelection(boolean r5) {
            int r0 = 0;
        L4:
            if (r0 >= this.listView.getChildCount()) goto L9;
            View r1 = this.listView.getChildAt(r0);
            if ((r1 instanceof SharedPhotoVideoCell2) == false) goto L8;
            SharedPhotoVideoCell2 r12 = (SharedPhotoVideoCell2) r1;
            r12.setChecked(this.this$0.isSelected(r12.getMessageObject()), r5);
        L8:
            r0 = r0 + 1;
            goto L4
        }
    }

    public static class ChooseLanguageSheet extends BottomSheetWithRecyclerListView {
        private UniversalAdapter adapter;
        private final int currentAccount;
        private FrameLayout searchContainer;
        private ImageView searchImageView;
        private final CharSequence title;

        public static class LanguageView extends LinearLayout {
            private boolean needDivider;
            private final TextView subtitle;
            private final TextView title;

            public static class Factory extends UItem.UItemFactory<LanguageView> {
                static {
                    UItem.UItemFactory.setup(new Factory());
                }

                public Factory() {
                }

                public static UItem of(TranslateController.Language r1) {
                    UItem r0 = UItem.ofFactory(Factory.class);
                    r0.object = r1;
                    return r0;
                }

                @Override // org.telegram.ui.Components.UItem.UItemFactory
                public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                    ((LanguageView) r1).set((TranslateController.Language) r2.object, r3);
                }

                @Override // org.telegram.ui.Components.UItem.UItemFactory
                public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                    return createView(r1, r2, r3, r4, r5);
                }

                @Override // org.telegram.ui.Components.UItem.UItemFactory
                public LanguageView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                    return new LanguageView(r1);
                }
            }

            public LanguageView(Context r13) {
                super(r13);
                setPadding(AndroidUtilities.dp(22.0f), 0, AndroidUtilities.dp(22.0f), 0);
                setOrientation(1);
                TextView r1 = new TextView(r13);
                this.title = r1;
                r1.setTextSize(1, 16.0f);
                r1.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
                int r3 = 3;
                if (LocaleController.isRTL == false) goto L5;
                int r2 = 5;
            L6:
                r1.setGravity(r2);
                addView(r1, LayoutHelper.createLinear(-1, -2, 51, 0, 7, 0, 0));
                TextView r12 = new TextView(r13);
                this.subtitle = r12;
                r12.setTextSize(1, 13.0f);
                r12.setTextColor(Theme.getColor(Theme.key_dialogTextGray2));
                if (LocaleController.isRTL == false) goto L9;
                r3 = 5;
            L9:
                r12.setGravity(r3);
                addView(r12, LayoutHelper.createLinear(-1, -2, 51, 0, 4, 0, 0));
                return;
            L5:
                r2 = 3;
                goto L6
            }

            @Override // android.widget.LinearLayout, android.view.View
            public void onDraw(Canvas r8) {
                super.onDraw(r8);
                if (this.needDivider == false) goto L6;
                r8.drawRect(getPaddingLeft(), getHeight() - 1, getWidth(), getHeight(), Theme.dividerPaint);
                return;
            }

            @Override // android.widget.LinearLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
            }

            public void set(TranslateController.Language r3, boolean r4) {
                this.title.setText(r3.displayName);
                this.subtitle.setText(r3.ownDisplayName);
                if (this.needDivider == r4) goto L5;
                invalidate();
            L5:
                this.needDivider = r4;
                setWillNotDraw(!r4);
            }
        }

        public static /* synthetic */ void $r8$lambda$27LP07mT_xYlBOaIdZFuvxCt3SA(ChooseLanguageSheet r0, ArrayList r1, UniversalAdapter r2) {
            r0.fillItems(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$uRHFdD4Wss1GdvRoFpEqO52NUVU(ChooseLanguageSheet r0, Utilities.Callback r1, View r2, int r3) {
            r0.lambda$new$0(r1, r2, r3);
        }

        public ChooseLanguageSheet(BaseFragment r7, CharSequence r8, Utilities.Callback<String> r9) {
            super(r7, true, false, false, r7.getResourceProvider());
            this.searchContainer = new FrameLayout(getContext());
            this.searchImageView = new ImageView(getContext());
            this.currentAccount = r7.getCurrentAccount();
            this.title = r8;
            updateTitle();
            this.topPadding = 0.6f;
            setShowHandle(true);
            this.handleOffset = true;
            fixNavigationBar();
            setSlidingActionBar();
            RecyclerListView r6 = this.recyclerListView;
            int r72 = this.backgroundPaddingLeft;
            r6.setPadding(r72, 0, r72, 0);
            this.recyclerListView.setOnItemClickListener(new BotPreviewsEditContainer$ChooseLanguageSheet$$ExternalSyntheticLambda0(this, r9));
        }

        private void fillItems(ArrayList<UItem> r3, UniversalAdapter r4) {
            ArrayList<TranslateController.Language> r2 = TranslateController.getLanguages();
            int r42 = r2.size();
            int r0 = 0;
        L3:
            if (r0 >= r42) goto L5;
            TranslateController.Language r1 = r2.get(r0);
            r0 = r0 + 1;
            r3.add(LanguageView.Factory.of(r1));
            goto L3
        }

        private /* synthetic */ void lambda$new$0(Utilities.Callback r1, View r2, int r3) {
            UniversalAdapter r22 = this.adapter;
            if (r22 == null) goto L13;
            UItem r23 = r22.getItem(r3 - 1);
            if (r23 == null) goto L11;
            Object r24 = r23.object;
            if ((r24 instanceof TranslateController.Language) == false) goto L12;
            r1.run(((TranslateController.Language) r24).code);
            dismiss();
            return;
        L12:
            return;
        L11:
            return;
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r8) {
            UniversalAdapter r0 = new UniversalAdapter(r8, getContext(), this.currentAccount, 0, new BotPreviewsEditContainer$ChooseLanguageSheet$$ExternalSyntheticLambda1(this), this.resourcesProvider);
            this.adapter = r0;
            r0.setApplyBackground(false);
            return this.adapter;
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public CharSequence getTitle() {
            return this.title;
        }
    }

    /* renamed from: $r8$lambda$5SaLxY-sL6lU9K9ejVUIi95oTbM, reason: not valid java name */
    public static /* synthetic */ void m19837$r8$lambda$5SaLxYsL6lU9K9ejVUIi95oTbM(BotPreviewsEditContainer r0, ValueAnimator r1) {
        r0.lambda$updateTabs$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$WJgv_3rxqipQCINO_VfBD6fj2rU(BotPreviewsEditContainer r0, String r1) {
        r0.lambda$addTranslation$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$by5FZbFvLZ_vB8HjxsJdvtt1eWE(BotPreviewsEditContainer r0, String r1) {
        r0.lambda$addTranslation$1(r1);
    }

    public static /* synthetic */ Boolean $r8$lambda$hmCgD1fasmUCbVDCtGbsgi3pCe4(BotPreviewsEditContainer r0, Integer r1, Integer r2) {
        return r0.lambda$new$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetbot_id, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m19838$$Nest$fgetbot_id(BotPreviewsEditContainer r2) {
        return r2.bot_id;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19839$$Nest$fgetcurrentAccount(BotPreviewsEditContainer r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetfragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m19840$$Nest$fgetfragment(BotPreviewsEditContainer r0) {
        return r0.fragment;
    }

    /* renamed from: -$$Nest$fgetlangLists, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m19841$$Nest$fgetlangLists(BotPreviewsEditContainer r0) {
        return r0.langLists;
    }

    /* renamed from: -$$Nest$fgetmainList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesController.BotPreviewsList m19842$$Nest$fgetmainList(BotPreviewsEditContainer r0) {
        return r0.mainList;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m19843$$Nest$fgetresourcesProvider(BotPreviewsEditContainer r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetsetColumnsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19844$$Nest$fgetsetColumnsCount(BotPreviewsEditContainer r0) {
        return r0.setColumnsCount;
    }

    /* renamed from: -$$Nest$fgettabsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPagerFixed.TabsView m19845$$Nest$fgettabsView(BotPreviewsEditContainer r0) {
        return r0.tabsView;
    }

    /* renamed from: -$$Nest$fgetviewPager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPagerFixed m19846$$Nest$fgetviewPager(BotPreviewsEditContainer r0) {
        return r0.viewPager;
    }

    /* renamed from: -$$Nest$fgetvisibleHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19847$$Nest$fgetvisibleHeight(BotPreviewsEditContainer r0) {
        return r0.visibleHeight;
    }

    /* renamed from: -$$Nest$fputsetColumnsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19848$$Nest$fputsetColumnsCount(BotPreviewsEditContainer r0, int r1) {
        r0.setColumnsCount = r1;
    }

    /* renamed from: -$$Nest$fputtabsAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19849$$Nest$fputtabsAlpha(BotPreviewsEditContainer r0, float r1) {
        r0.tabsAlpha = r1;
    }

    public BotPreviewsEditContainer(Context r8, BaseFragment r9, long r10) {
        super(r8);
        this.langLists = new ArrayList();
        this.localLangs = new ArrayList();
        this.shownTabs = null;
        this.visibleHeight = AndroidUtilities.displaySize.y;
        this.setColumnsCount = Utilities.clamp(SharedConfig.storiesColumnsCount, 6, 2);
        this.fragment = r9;
        int r2 = r9.getCurrentAccount();
        this.currentAccount = r2;
        Theme.ResourcesProvider r92 = r9.getResourceProvider();
        this.resourcesProvider = r92;
        this.bot_id = r10;
        setBackgroundColor(Theme.blendOver(Theme.getColor(Theme.key_windowBackgroundWhite, r92), Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r92), 0.04f)));
        if (cachedLists != null) goto L5;
        cachedLists = new LongSparseArray();
    L5:
        LongSparseArray<StoriesController.BotPreviewsList> r93 = cachedLists.get(r2);
        if (r93 != null) goto L8;
        LongSparseArray<StoriesController.BotPreviewsList> r3 = new LongSparseArray();
        cachedLists.put(r2, r3);
        r93 = r3;
    L8:
        StoriesController.BotPreviewsList r0 = r93.get(r10);
        if (r0 != null) goto L11;
        StoriesController.BotPreviewsList r1 = new StoriesController.BotPreviewsList(r2, r10, _UrlKt.FRAGMENT_ENCODE_SET, null);
        r93.put(r10, r1);
        r0 = r1;
    L11:
        this.mainList = r0;
        AnonymousClass1 r94 = new AnonymousClass1(this, r8);
        this.viewPager = r94;
        r94.setAllowDisallowInterceptTouch(true);
        r94.setAdapter(new AnonymousClass2(this, r8));
        addView(r94, LayoutHelper.createFrame(-1, -1, 119));
        ViewPagerFixed.TabsView r82 = r94.createTabsView(true, 9);
        this.tabsView = r82;
        r82.tabMarginDp = 12;
        r82.setPreTabClick(new BotPreviewsEditContainer$$ExternalSyntheticLambda0(this));
        addView(r82, LayoutHelper.createFrame(-1, 42, 48));
        updateLangs(false);
    }

    public static void edit(int r3, long r4, String r6, TLRPC.InputMedia r7, TL_bots.botPreviewMedia r8) {
        LongSparseArray<LongSparseArray<StoriesController.BotPreviewsList>> r0 = cachedLists;
        if (r0 == null) goto L16;
        LongSparseArray<StoriesController.BotPreviewsList> r02 = r0.get(r3);
        if (r02 == null) goto L16;
        StoriesController.BotPreviewsList r03 = r02.get(r4);
        if (r03.currentAccount != r3) goto L16;
        if (TextUtils.equals(r03.lang_code, r6) == false) goto L12;
        r03.edit(r7, r8);
        goto L16
    L12:
        if (TextUtils.isEmpty(r6) == true) goto L16;
        if (r03.lang_codes.contains(r6) == true) goto L16;
        r03.lang_codes.add(r6);
        r03.notifyUpdate();
    L16:
        LongSparseArray<LongSparseArray<BotPreviewsEditContainer>> r04 = attachedContainers;
        if (r04 == null) goto L31;
        LongSparseArray<BotPreviewsEditContainer> r05 = r04.get(r3);
        if (r05 == null) goto L36;
        BotPreviewsEditContainer r42 = r05.get(r4);
        if (r42 == null) goto L37;
        int r5 = 0;
    L24:
        if (r5 >= r42.langLists.size()) goto L38;
        StoriesController.BotPreviewsList r06 = r42.langLists.get(r5);
        if (r06.currentAccount != r3) goto L30;
        if (TextUtils.equals(r06.lang_code, r6) == false) goto L30;
        r06.edit(r7, r8);
    L30:
        r5 = r5 + 1;
        goto L24
    L38:
        return;
    L37:
        return;
    L36:
        return;
    }

    private /* synthetic */ void lambda$addTranslation$1(String r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.langLists.size()) goto L9;
        if (TextUtils.equals(this.langLists.get(r0).lang_code, r3) == true) goto L10;
        r0 = r0 + 1;
    L10:
        if (r0 < 0) goto L15;
        this.tabsView.scrollToTab(r3.hashCode(), r0 + 1);
        return;
    L15:
        return;
    L9:
        r0 = -1;
        goto L10
    }

    private /* synthetic */ void lambda$addTranslation$2(String r2) {
        if (this.localLangs.contains(r2) == true) goto L5;
        this.localLangs.add(r2);
        updateLangs(true);
    L5:
        AndroidUtilities.runOnUIThread(new BotPreviewsEditContainer$$ExternalSyntheticLambda3(this, r2), 120);
    }

    private /* synthetic */ Boolean lambda$new$0(Integer r1, Integer r2) {
        if (r1.intValue() != (-1)) goto L7;
        addTranslation();
        return Boolean.TRUE;
    L7:
        return Boolean.FALSE;
    }

    private /* synthetic */ void lambda$updateTabs$3(ValueAnimator r5) {
        this.tabsAlpha = ((Float) r5.getAnimatedValue()).floatValue();
        this.tabsView.setTranslationY(AndroidUtilities.lerp(-AndroidUtilities.dp(42.0f), 0, this.tabsAlpha));
        this.viewPager.setTranslationY(AndroidUtilities.lerp(0, AndroidUtilities.dp(42.0f), this.tabsAlpha));
    }

    public static void push(int r3, long r4, String r6, TL_bots.botPreviewMedia r7) {
        LongSparseArray<LongSparseArray<StoriesController.BotPreviewsList>> r0 = cachedLists;
        if (r0 == null) goto L16;
        LongSparseArray<StoriesController.BotPreviewsList> r02 = r0.get(r3);
        if (r02 == null) goto L16;
        StoriesController.BotPreviewsList r03 = r02.get(r4);
        if (r03.currentAccount != r3) goto L16;
        if (TextUtils.equals(r03.lang_code, r6) == false) goto L12;
        r03.push(r7);
        goto L16
    L12:
        if (TextUtils.isEmpty(r6) == true) goto L16;
        if (r03.lang_codes.contains(r6) == true) goto L16;
        r03.lang_codes.add(r6);
        r03.notifyUpdate();
    L16:
        LongSparseArray<LongSparseArray<BotPreviewsEditContainer>> r04 = attachedContainers;
        if (r04 == null) goto L31;
        LongSparseArray<BotPreviewsEditContainer> r05 = r04.get(r3);
        if (r05 == null) goto L36;
        BotPreviewsEditContainer r42 = r05.get(r4);
        if (r42 == null) goto L37;
        int r5 = 0;
    L24:
        if (r5 >= r42.langLists.size()) goto L38;
        StoriesController.BotPreviewsList r06 = r42.langLists.get(r5);
        if (r06.currentAccount != r3) goto L30;
        if (TextUtils.equals(r06.lang_code, r6) == false) goto L30;
        r06.push(r7);
    L30:
        r5 = r5 + 1;
        goto L24
    L38:
        return;
    L37:
        return;
    L36:
        return;
    }

    private void updateLangs(boolean r15) {
        ArrayList r0 = new ArrayList(this.mainList.lang_codes);
        ArrayList<String> r1 = this.localLangs;
        int r2 = r1.size();
        boolean r3 = false;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L7;
        String r5 = r1.get(r4);
        r4 = r4 + 1;
        String r52 = r5;
        if (r0.contains(r52) == true) goto L3;
        r0.add(r52);
        goto L3
    L7:
        ArrayList<StoriesController.UploadingStory> r12 = MessagesController.getInstance(this.currentAccount).getStoriesController().getUploadingStories(this.bot_id);
        if (r12 == null) goto L22;
        int r22 = r12.size();
        int r42 = 0;
    L10:
        if (r42 >= r22) goto L22;
        StoriesController.UploadingStory r53 = r12.get(r42);
        r42 = r42 + 1;
        StoriesController.UploadingStory r54 = r53;
        if (r54 == null) goto L10;
        StoryEntry r6 = r54.entry;
        if (r6 == null) goto L10;
        if (r6.botId != this.bot_id) goto L10;
        if (TextUtils.isEmpty(r6.botLang) == true) goto L10;
        if (r0.contains(r54.entry.botLang) == true) goto L10;
        r0.add(r54.entry.botLang);
    L22:
        ArrayList r13 = new ArrayList(this.langLists);
        this.langLists.clear();
        int r23 = r0.size();
        int r43 = 0;
    L24:
        if (r43 >= r23) goto L36;
        Object r62 = r0.get(r43);
        r43 = r43 + 1;
        String r11 = (String) r62;
        int r63 = 0;
    L27:
        if (r63 >= r13.size()) goto L32;
        if (TextUtils.equals(((StoriesController.BotPreviewsList) r13.get(r63)).lang_code, r11) == true) goto L30;
        r63 = r63 + 1;
        goto L27
    L30:
        StoriesController.BotPreviewsList r64 = (StoriesController.BotPreviewsList) r13.get(r63);
    L33:
        if (r64 != null) goto L35;
        StoriesController.BotPreviewsList r7 = new StoriesController.BotPreviewsList(this.currentAccount, this.bot_id, r11, null);
        r7.load(true, 0, null);
        r64 = r7;
    L35:
        this.langLists.add(r64);
        goto L24
    L32:
        r64 = null;
        goto L33
    L36:
        this.viewPager.fillTabs(true);
        SpannableString r02 = new SpannableString("+ " + LocaleController.getString(R.string.ProfileBotLanguageAdd));
        ColoredImageSpan r14 = new ColoredImageSpan(R.drawable.msg_filled_plus);
        r14.setScale(0.9f, 0.9f);
        r14.spaceScaleX = 0.85f;
        r02.setSpan(r14, 0, 1, 33);
        this.tabsView.addTab(-1, r02);
        this.tabsView.finishAddingTabs();
        if ((this.langLists.size() + 1) <= 1) goto L39;
        r3 = true;
    L39:
        updateTabs(r3, r15);
    }

    private void updateTabs(boolean r4, boolean r5) {
        Boolean r0 = this.shownTabs;
        if (r0 != null) goto L5;
    L7:
        ValueAnimator r02 = this.tabsAnimator;
        if (r02 == null) goto L10;
        r02.cancel();
    L10:
        this.shownTabs = Boolean.valueOf(r4);
        float r03 = 1.0f;
        float r1 = 0.0f;
        if (r5 == true) goto L24;
        if (r4 == true) goto L15;
        r03 = 0.0f;
    L15:
        this.tabsAlpha = r03;
        ViewPagerFixed.TabsView r52 = this.tabsView;
        if (r4 == false) goto L18;
        float r04 = 0.0f;
    L19:
        r52.setTranslationY(AndroidUtilities.dp(r04));
        ViewPagerFixed r3 = this.viewPager;
        if (r4 == false) goto L22;
        r1 = 42.0f;
    L22:
        r3.setTranslationY(AndroidUtilities.dp(r1));
        return;
    L18:
        r04 = -42.0f;
        goto L19
    L24:
        float r53 = this.tabsAlpha;
        if (r4 == true) goto L28;
        r03 = 0.0f;
    L28:
        ValueAnimator r54 = ValueAnimator.ofFloat(new float[]{r53, r03});
        this.tabsAnimator = r54;
        r54.addUpdateListener(new BotPreviewsEditContainer$$ExternalSyntheticLambda2(this));
        this.tabsAnimator.addListener(new AnonymousClass3(this, r4));
        this.tabsAnimator.setDuration(320);
        this.tabsAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.tabsAnimator.start();
        return;
    L5:
        if (r0.booleanValue() != r4) goto L7;
    }

    public void addTranslation() {
        new ChooseLanguageSheet(this.fragment, LocaleController.getString(R.string.ProfileBotPreviewLanguageChoose), new BotPreviewsEditContainer$$ExternalSyntheticLambda1(this)).show();
    }

    public boolean canScroll(boolean r4) {
        ViewPagerFixed r0 = this.viewPager;
        if (r4 == false) goto L9;
        if (r0.getCurrentPosition() != this.langLists.size()) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (r0.getCurrentPosition() != 0) goto L11;
        return true;
    L11:
        return false;
    }

    public boolean checkPinchToZoom(MotionEvent r2) {
        View r1 = this.viewPager.getCurrentView();
        if ((r1 instanceof BotPreviewsEditLangContainer) == true) goto L5;
        return false;
    L5:
        return ((BotPreviewsEditLangContainer) r1).checkPinchToZoom(r2);
    }

    public void createStory(String r9) {
        BaseFragment r0 = this.fragment;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getParentActivity() == null) goto L10;
        ChatAttachAlert r1 = new ChatAttachAlert(this.fragment.getParentActivity(), this.fragment, false, false, false, this.resourcesProvider);
        r1.setMaxSelectedPhotos(1, false);
        r1.setStoryMediaPicker();
        r1.getPhotoLayout().loadGalleryPhotos();
        r1.setDelegate(new AnonymousClass4(this, r1, r9));
        r1.init();
        r1.show();
        return;
    }

    public void deleteLang(String r8) {
        if (TextUtils.isEmpty(r8) == false) goto L5;
        return;
    L5:
        this.mainList.lang_codes.remove(r8);
        this.localLangs.remove(r8);
        int r1 = 0;
    L7:
        if (r1 >= this.langLists.size()) goto L14;
        StoriesController.BotPreviewsList r2 = this.langLists.get(r1);
        if (r2 == null) goto L13;
        if (TextUtils.equals(r2.lang_code, r8) == false) goto L13;
    L15:
        if (r2 == null) goto L26;
        TL_bots.deletePreviewMedia r12 = new TL_bots.deletePreviewMedia();
        r12.bot = MessagesController.getInstance(this.currentAccount).getInputUser(this.bot_id);
        r12.lang_code = r8;
        int r82 = 0;
    L18:
        if (r82 >= r2.messageObjects.size()) goto L25;
        TL_stories.StoryItem r4 = r2.messageObjects.get(r82).storyItem;
        if (r4 == null) goto L24;
        TLRPC.MessageMedia r42 = r4.media;
        if (r42 == null) goto L24;
        r12.media.add(MessagesController.toInputMedia(r42));
    L24:
        r82 = r82 + 1;
        goto L18
    L25:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r12, null);
    L26:
        updateLangs(true);
        this.tabsView.scrollToTab(-1, 0);
        return;
    L13:
        r1 = r1 + 1;
        goto L7
    L14:
        r2 = null;
        goto L15
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r5, int r6, Object... r7) {
        int r1 = 0;
        if (r5 != NotificationCenter.storiesListUpdated) goto L25;
        Object r52 = r7[0];
        if (r52 != this.mainList) goto L15;
        updateLangs(true);
        View[] r53 = this.viewPager.getViewPages();
        int r62 = r53.length;
    L7:
        if (r1 >= r62) goto L45;
        View r72 = r53[r1];
        if ((r72 instanceof BotPreviewsEditLangContainer) == false) goto L13;
        BotPreviewsEditLangContainer r73 = (BotPreviewsEditLangContainer) r72;
        if (BotPreviewsEditLangContainer.m19862$$Nest$fgetlist(r73) != this.mainList) goto L13;
        BotPreviewsEditLangContainer.m19852$$Nest$fgetadapter(r73).notifyDataSetChanged();
    L13:
        r1 = r1 + 1;
        goto L7
    L45:
        return;
    L15:
        if (this.langLists.indexOf(r52) < 0) goto L32;
        View[] r4 = this.viewPager.getViewPages();
        int r54 = r4.length;
        int r63 = 0;
    L17:
        if (r63 >= r54) goto L46;
        View r0 = r4[r63];
        if ((r0 instanceof BotPreviewsEditLangContainer) == false) goto L23;
        BotPreviewsEditLangContainer r02 = (BotPreviewsEditLangContainer) r0;
        if (BotPreviewsEditLangContainer.m19862$$Nest$fgetlist(r02) != r7[0]) goto L23;
        BotPreviewsEditLangContainer.m19852$$Nest$fgetadapter(r02).notifyDataSetChanged();
    L23:
        r63 = r63 + 1;
        goto L17
    L46:
        return;
    L32:
        return;
    L25:
        if (r5 != NotificationCenter.storiesUpdated) goto L44;
        updateLangs(true);
        View[] r42 = this.viewPager.getViewPages();
        int r55 = r42.length;
    L27:
        if (r1 >= r55) goto L47;
        View r64 = r42[r1];
        if ((r64 instanceof BotPreviewsEditLangContainer) == false) goto L31;
        BotPreviewsEditLangContainer.m19852$$Nest$fgetadapter((BotPreviewsEditLangContainer) r64).notifyDataSetChanged();
    L31:
        r1 = r1 + 1;
        goto L27
    L47:
        return;
    }

    public String getBotPreviewsSubtitle() {
        StringBuilder r0 = new StringBuilder();
        View r7 = this.viewPager.getCurrentView();
        if ((r7 instanceof BotPreviewsEditLangContainer) == false) goto L33;
        StoriesController.BotPreviewsList r72 = BotPreviewsEditLangContainer.m19862$$Nest$fgetlist((BotPreviewsEditLangContainer) r7);
        if (r72 == null) goto L20;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
    L8:
        if (r2 >= r72.messageObjects.size()) goto L21;
        MessageObject r5 = r72.messageObjects.get(r2);
        TL_stories.StoryItem r6 = r5.storyItem;
        if (r6 == null) goto L19;
        TLRPC.MessageMedia r62 = r6.media;
        if (r62 == null) goto L19;
        if (MessageObject.isVideoDocument(r62.document) == false) goto L17;
        r4 = r4 + 1;
        goto L19
    L17:
        if (r5.storyItem.media.photo == null) goto L19;
        r3 = r3 + 1;
    L19:
        r2 = r2 + 1;
    L21:
        if (r3 != 0) goto L25;
        if (r4 != 0) goto L25;
        return LocaleController.getString(R.string.BotPreviewEmpty);
    L25:
        if (r3 <= 0) goto L27;
        r0.append(LocaleController.formatPluralString("Images", r3, new Object[0]));
    L27:
        if (r4 <= 0) goto L33;
        if (r0.length() <= 0) goto L31;
        r0.append(", ");
    L31:
        r0.append(LocaleController.formatPluralString("Videos", r4, new Object[0]));
        goto L33
    L20:
        r3 = 0;
        r4 = 0;
    L33:
        return r0.toString();
    }

    public String getCurrentLang() {
        View[] r0 = this.viewPager.getViewPages();
        if (Math.abs(this.viewPager.getCurrentPosition() - this.viewPager.getPositionAnimated()) >= 0.5f) goto L7;
        View r2 = r0[1];
        if (r2 == null) goto L7;
    L9:
        if ((r2 instanceof BotPreviewsEditLangContainer) == false) goto L14;
        BotPreviewsEditLangContainer r22 = (BotPreviewsEditLangContainer) r2;
        if (BotPreviewsEditLangContainer.m19862$$Nest$fgetlist(r22) != null) goto L13;
        return null;
    L13:
        return BotPreviewsEditLangContainer.m19862$$Nest$fgetlist(r22).lang_code;
    L14:
        return null;
    L7:
        r2 = r0[0];
        goto L9
    }

    public StoriesController.BotPreviewsList getCurrentList() {
        View r1 = this.viewPager.getCurrentView();
        if ((r1 instanceof BotPreviewsEditLangContainer) == false) goto L8;
        BotPreviewsEditLangContainer r12 = (BotPreviewsEditLangContainer) r1;
        if (BotPreviewsEditLangContainer.m19862$$Nest$fgetlist(r12) != null) goto L7;
        return null;
    L7:
        return BotPreviewsEditLangContainer.m19862$$Nest$fgetlist(r12);
    L8:
        return null;
    }

    public RecyclerListView getCurrentListView() {
        View r1 = this.viewPager.getCurrentView();
        if ((r1 instanceof BotPreviewsEditLangContainer) == true) goto L5;
        return null;
    L5:
        return BotPreviewsEditLangContainer.m19863$$Nest$fgetlistView((BotPreviewsEditLangContainer) r1);
    }

    public int getItemsCount() {
        View r1 = this.viewPager.getCurrentView();
        if ((r1 instanceof BotPreviewsEditLangContainer) == false) goto L8;
        BotPreviewsEditLangContainer r12 = (BotPreviewsEditLangContainer) r1;
        if (BotPreviewsEditLangContainer.m19862$$Nest$fgetlist(r12) != null) goto L7;
        return 0;
    L7:
        return BotPreviewsEditLangContainer.m19862$$Nest$fgetlist(r12).getCount();
    L8:
        return 0;
    }

    public int getStartedTrackingX() {
        return 0;
    }

    public abstract boolean isActionModeShowed();

    public abstract boolean isSelected(MessageObject r1);

    public boolean isSelectedAll() {
        View r0 = this.viewPager.getCurrentView();
        if ((r0 instanceof BotPreviewsEditLangContainer) == false) goto L13;
        StoriesController.BotPreviewsList r02 = BotPreviewsEditLangContainer.m19862$$Nest$fgetlist((BotPreviewsEditLangContainer) r0);
        if (r02 == null) goto L13;
        int r3 = 0;
    L8:
        if (r3 >= r02.messageObjects.size()) goto L13;
        if (isSelected(r02.messageObjects.get(r3)) == false) goto L11;
        r3 = r3 + 1;
        goto L8
    L11:
        return false;
    L13:
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (attachedContainers != null) goto L5;
        attachedContainers = new LongSparseArray();
    L5:
        LongSparseArray<BotPreviewsEditContainer> r0 = attachedContainers.get(this.currentAccount);
        if (r0 != null) goto L8;
        LongSparseArray<LongSparseArray<BotPreviewsEditContainer>> r02 = attachedContainers;
        long r1 = this.currentAccount;
        LongSparseArray<BotPreviewsEditContainer> r3 = new LongSparseArray();
        r02.put(r1, r3);
        r0 = r3;
    L8:
        r0.put(this.bot_id, this);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesListUpdated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesUpdated);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (attachedContainers != null) goto L5;
        attachedContainers = new LongSparseArray();
    L5:
        LongSparseArray<BotPreviewsEditContainer> r0 = attachedContainers.get(this.currentAccount);
        if (r0 == null) goto L8;
        r0.remove(this.bot_id);
    L8:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesListUpdated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesUpdated);
    }

    public abstract void onSelectedTabChanged();

    public abstract boolean select(MessageObject r1);

    public void selectAll() {
        View r0 = this.viewPager.getCurrentView();
        if ((r0 instanceof BotPreviewsEditLangContainer) == false) goto L13;
        StoriesController.BotPreviewsList r02 = BotPreviewsEditLangContainer.m19862$$Nest$fgetlist((BotPreviewsEditLangContainer) r0);
        if (r02 == null) goto L17;
        int r1 = 0;
    L8:
        if (r1 >= r02.messageObjects.size()) goto L18;
        if (isSelected(r02.messageObjects.get(r1)) == true) goto L12;
        select(r02.messageObjects.get(r1));
    L12:
        r1 = r1 + 1;
        goto L8
    L18:
        return;
    L17:
        return;
    }

    public void setVisibleHeight(int r4) {
        this.visibleHeight = r4;
        View[] r3 = this.viewPager.getViewPages();
        if (r3 == null) goto L11;
        int r0 = 0;
    L6:
        if (r0 >= r3.length) goto L15;
        View r1 = r3[r0];
        if ((r1 instanceof BotPreviewsEditLangContainer) == false) goto L10;
        ((BotPreviewsEditLangContainer) r1).setVisibleHeight(r4);
    L10:
        r0 = r0 + 1;
        goto L6
    L15:
        return;
    }

    public abstract boolean unselect(MessageObject r1);

    public void unselectAll() {
        View r0 = this.viewPager.getCurrentView();
        if ((r0 instanceof BotPreviewsEditLangContainer) == false) goto L13;
        StoriesController.BotPreviewsList r02 = BotPreviewsEditLangContainer.m19862$$Nest$fgetlist((BotPreviewsEditLangContainer) r0);
        if (r02 == null) goto L17;
        int r1 = 0;
    L8:
        if (r1 >= r02.messageObjects.size()) goto L18;
        if (isSelected(r02.messageObjects.get(r1)) == false) goto L12;
        unselect(r02.messageObjects.get(r1));
    L12:
        r1 = r1 + 1;
        goto L8
    L18:
        return;
    L17:
        return;
    }

    public void updateSelection(boolean r2) {
        View r1 = this.viewPager.getCurrentView();
        if ((r1 instanceof BotPreviewsEditLangContainer) == false) goto L6;
        ((BotPreviewsEditLangContainer) r1).updateSelection(r2);
        return;
    }
}
