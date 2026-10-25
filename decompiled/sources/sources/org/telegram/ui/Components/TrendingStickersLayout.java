package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Adapters.StickersSearchAdapter;
import org.telegram.ui.Cells.EmptyCell;
import org.telegram.ui.Cells.FeaturedStickerSetCell2;
import org.telegram.ui.Cells.FeaturedStickerSetInfoCell;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.StickerEmojiCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.StickersAlert;

/* loaded from: classes7.dex */
public class TrendingStickersLayout extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private final TrendingStickersAdapter adapter;
    private final int currentAccount;
    private final Delegate delegate;
    ValueAnimator glueToTopAnimator;
    private boolean gluedToTop;
    private long hash;
    private float highlightProgress;
    private boolean ignoreLayout;
    private final LongSparseArray<TLRPC.StickerSetCovered> installingStickerSets;
    private final GridLayoutManager layoutManager;
    private final RecyclerListView listView;
    private boolean loaded;
    private boolean motionEventCatchedByListView;
    private RecyclerView.OnScrollListener onScrollListener;
    Paint paint;
    private BaseFragment parentFragment;
    private final TLRPC.StickerSetCovered[] primaryInstallingStickerSets;
    private final LongSparseArray<TLRPC.StickerSetCovered> removingStickerSets;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean scrollFromAnimator;
    private TLRPC.StickerSetCovered scrollToSet;
    private final StickersSearchAdapter searchAdapter;
    private final FrameLayout searchLayout;
    private final SearchField searchView;
    private final View shadowView;
    private boolean shadowVisible;
    private int topOffset;
    private boolean wasLayout;

    /* renamed from: org.telegram.ui.Components.TrendingStickersLayout$1, reason: invalid class name */
    public class AnonymousClass1 implements StickersSearchAdapter.Delegate {
        final /* synthetic */ TrendingStickersLayout this$0;
        final /* synthetic */ Delegate val$delegate;

        public AnonymousClass1(TrendingStickersLayout r1, Delegate r2) {
            this.this$0 = r1;
            this.val$delegate = r2;
        }

        @Override // org.telegram.ui.Adapters.StickersSearchAdapter.Delegate
        public String[] getLastSearchKeyboardLanguage() {
            return this.val$delegate.getLastSearchKeyboardLanguage();
        }

        @Override // org.telegram.ui.Adapters.StickersSearchAdapter.Delegate
        public int getStickersPerRow() {
            return TrendingStickersAdapter.m13467$$Nest$fgetstickersPerRow(TrendingStickersLayout.m13438$$Nest$fgetadapter(this.this$0));
        }

        @Override // org.telegram.ui.Adapters.StickersSearchAdapter.Delegate
        public void onSearchStart() {
            TrendingStickersLayout.m13453$$Nest$fgetsearchView(this.this$0).getProgressDrawable().startAnimation();
        }

        @Override // org.telegram.ui.Adapters.StickersSearchAdapter.Delegate
        public void onSearchStop() {
            TrendingStickersLayout.m13453$$Nest$fgetsearchView(this.this$0).getProgressDrawable().stopAnimation();
        }

        @Override // org.telegram.ui.Adapters.StickersSearchAdapter.Delegate
        public void onStickerSetAdd(TLRPC.StickerSetCovered r1, boolean r2) {
            this.val$delegate.onStickerSetAdd(r1, r2);
        }

        @Override // org.telegram.ui.Adapters.StickersSearchAdapter.Delegate
        public void onStickerSetRemove(TLRPC.StickerSetCovered r1) {
            this.val$delegate.onStickerSetRemove(r1);
        }

        @Override // org.telegram.ui.Adapters.StickersSearchAdapter.Delegate
        public void setAdapterVisible(boolean r3) {
            if (r3 == true) goto L4;
        L6:
            if (r3 == false) goto L8;
            return;
        L8:
            if (TrendingStickersLayout.m13445$$Nest$fgetlistView(this.this$0).getAdapter() == TrendingStickersLayout.m13438$$Nest$fgetadapter(this.this$0)) goto L15;
            TrendingStickersLayout.m13445$$Nest$fgetlistView(this.this$0).setAdapter(TrendingStickersLayout.m13438$$Nest$fgetadapter(this.this$0));
        L11:
            if (TrendingStickersLayout.m13445$$Nest$fgetlistView(this.this$0).getAdapter().getItemCount() <= 0) goto L16;
            TrendingStickersLayout.m13444$$Nest$fgetlayoutManager(this.this$0).scrollToPositionWithOffset(0, ((-TrendingStickersLayout.m13445$$Nest$fgetlistView(this.this$0).getPaddingTop()) + AndroidUtilities.dp(58.0f)) + TrendingStickersLayout.m13454$$Nest$fgettopOffset(this.this$0), false);
            return;
        L16:
            return;
        L15:
            return;
        L4:
            if (TrendingStickersLayout.m13445$$Nest$fgetlistView(this.this$0).getAdapter() == TrendingStickersLayout.m13452$$Nest$fgetsearchAdapter(this.this$0)) goto L6;
            TrendingStickersLayout.m13445$$Nest$fgetlistView(this.this$0).setAdapter(TrendingStickersLayout.m13452$$Nest$fgetsearchAdapter(this.this$0));
            goto L11
        }

        @Override // org.telegram.ui.Adapters.StickersSearchAdapter.Delegate
        public void setLastSearchKeyboardLanguage(String[] r1) {
            this.val$delegate.setLastSearchKeyboardLanguage(r1);
        }
    }

    /* renamed from: org.telegram.ui.Components.TrendingStickersLayout$10, reason: invalid class name */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ TrendingStickersLayout this$0;

        public AnonymousClass10(TrendingStickersLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.glueToTopAnimator = null;
        }
    }

    /* renamed from: org.telegram.ui.Components.TrendingStickersLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends SearchField {
        final /* synthetic */ TrendingStickersLayout this$0;

        public AnonymousClass2(TrendingStickersLayout r1, Context r2, boolean r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.SearchField
        public void onTextChange(String r1) {
            TrendingStickersLayout.m13452$$Nest$fgetsearchAdapter(this.this$0).search(r1);
        }
    }

    /* renamed from: org.telegram.ui.Components.TrendingStickersLayout$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerListView {
        final /* synthetic */ TrendingStickersLayout this$0;
        final /* synthetic */ Delegate val$delegate;

        public AnonymousClass3(TrendingStickersLayout r1, Context r2, Delegate r3) {
            this.this$0 = r1;
            this.val$delegate = r3;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public boolean allowSelectChildAtPosition(float r1, float r2) {
            if (r2 < (TrendingStickersLayout.m13454$$Nest$fgettopOffset(this.this$0) + AndroidUtilities.dp(58.0f))) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            TrendingStickersLayout.m13457$$Nest$fputmotionEventCatchedByListView(this.this$0, true);
            return super.dispatchTouchEvent(r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r2) {
            boolean r0 = this.val$delegate.onListViewInterceptTouchEvent(this, r2);
            if (super.onInterceptTouchEvent(r2) == true) goto L8;
            if (r0 == true) goto L10;
            return false;
        L10:
            return true;
        L8:
            return true;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (this.this$0.glueToTopAnimator == null) goto L7;
            return false;
        L7:
            return super.onTouchEvent(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (TrendingStickersLayout.m13442$$Nest$fgetignoreLayout(this.this$0) == true) goto L6;
            super.requestLayout();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.TrendingStickersLayout$4, reason: invalid class name */
    public class AnonymousClass4 extends FillLastGridLayoutManager {
        final /* synthetic */ TrendingStickersLayout this$0;

        public AnonymousClass4(TrendingStickersLayout r1, Context r2, int r3, int r4, RecyclerView r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public boolean isLayoutRTL() {
            return LocaleController.isRTL;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public int scrollVerticallyBy(int r5, RecyclerView.Recycler r6, RecyclerView.State r7) {
            if (TrendingStickersLayout.m13451$$Nest$fgetscrollFromAnimator(this.this$0) == true) goto L5;
            TrendingStickersLayout r0 = this.this$0;
            int r2 = 0;
            if (r0.glueToTopAnimator == null) goto L10;
            return 0;
        L10:
            if (TrendingStickersLayout.m13441$$Nest$fgetgluedToTop(r0) == false) goto L24;
        L11:
            int r1 = 1;
            if (r2 >= getChildCount()) goto L17;
            int r02 = TrendingStickersLayout.m13445$$Nest$fgetlistView(this.this$0).getChildAdapterPosition(getChildAt(r2));
            if (r02 < 1) goto L15;
            r2 = r2 + 1;
            goto L11
        L15:
            r1 = r02;
        L17:
            if (r1 != 0) goto L24;
            View r03 = TrendingStickersLayout.m13444$$Nest$fgetlayoutManager(this.this$0).findViewByPosition(r1);
            if (r03 == null) goto L24;
            if ((r03.getTop() - r5) <= AndroidUtilities.dp(58.0f)) goto L24;
            r5 = r03.getTop() - AndroidUtilities.dp(58.0f);
        L24:
            return super.scrollVerticallyBy(r5, r6, r7);
        L5:
            return super.scrollVerticallyBy(r5, r6, r7);
        }

        @Override // org.telegram.ui.Components.FillLastGridLayoutManager
        public boolean shouldCalcLastItemHeight() {
            if (TrendingStickersLayout.m13445$$Nest$fgetlistView(this.this$0).getAdapter() != TrendingStickersLayout.m13452$$Nest$fgetsearchAdapter(this.this$0)) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.TrendingStickersLayout$5, reason: invalid class name */
    public class AnonymousClass5 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ TrendingStickersLayout this$0;

        public AnonymousClass5(TrendingStickersLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r4) {
            RecyclerView.Adapter r0 = TrendingStickersLayout.m13445$$Nest$fgetlistView(this.this$0).getAdapter();
            TrendingStickersAdapter r1 = TrendingStickersLayout.m13438$$Nest$fgetadapter(this.this$0);
            TrendingStickersLayout r2 = this.this$0;
            if (r0 != r1) goto L14;
            if ((TrendingStickersAdapter.m13461$$Nest$fgetcache(TrendingStickersLayout.m13438$$Nest$fgetadapter(r2)).get(r4) instanceof Integer) == true) goto L12;
            if (r4 >= TrendingStickersAdapter.m13468$$Nest$fgettotalItems(TrendingStickersLayout.m13438$$Nest$fgetadapter(this.this$0))) goto L12;
            return 1;
        L12:
            return TrendingStickersAdapter.m13467$$Nest$fgetstickersPerRow(TrendingStickersLayout.m13438$$Nest$fgetadapter(this.this$0));
        L14:
            return TrendingStickersLayout.m13452$$Nest$fgetsearchAdapter(r2).getSpanSize(r4);
        }
    }

    /* renamed from: org.telegram.ui.Components.TrendingStickersLayout$6, reason: invalid class name */
    public class AnonymousClass6 extends RecyclerView.OnScrollListener {
        final /* synthetic */ TrendingStickersLayout this$0;

        public AnonymousClass6(TrendingStickersLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r2, int r3) {
            if (TrendingStickersLayout.m13447$$Nest$fgetonScrollListener(this.this$0) == null) goto L6;
            TrendingStickersLayout.m13447$$Nest$fgetonScrollListener(this.this$0).onScrollStateChanged(r2, r3);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            if (TrendingStickersLayout.m13447$$Nest$fgetonScrollListener(this.this$0) == null) goto L5;
            TrendingStickersLayout.m13447$$Nest$fgetonScrollListener(this.this$0).onScrolled(TrendingStickersLayout.m13445$$Nest$fgetlistView(this.this$0), r3, r4);
        L5:
            if (r4 > 0) goto L7;
            return;
        L7:
            if (TrendingStickersLayout.m13445$$Nest$fgetlistView(this.this$0).getAdapter() == TrendingStickersLayout.m13438$$Nest$fgetadapter(this.this$0)) goto L9;
            return;
        L9:
            if (TrendingStickersLayout.m13446$$Nest$fgetloaded(this.this$0) == true) goto L11;
            return;
        L11:
            if (TrendingStickersAdapter.m13463$$Nest$fgetloadingMore(TrendingStickersLayout.m13438$$Nest$fgetadapter(this.this$0)) == false) goto L13;
            return;
        L13:
            if (TrendingStickersAdapter.m13462$$Nest$fgetendReached(TrendingStickersLayout.m13438$$Nest$fgetadapter(this.this$0)) == true) goto L22;
            int r22 = (TrendingStickersAdapter.m13467$$Nest$fgetstickersPerRow(TrendingStickersLayout.m13438$$Nest$fgetadapter(this.this$0)) + 1) * 10;
            if (TrendingStickersLayout.m13444$$Nest$fgetlayoutManager(this.this$0).findLastVisibleItemPosition() < ((TrendingStickersLayout.m13438$$Nest$fgetadapter(this.this$0).getItemCount() - r22) - 1)) goto L23;
            TrendingStickersLayout.m13438$$Nest$fgetadapter(this.this$0).loadMoreStickerSets();
            return;
        L23:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.TrendingStickersLayout$7, reason: invalid class name */
    public class AnonymousClass7 implements StickersAlert.StickersAlertDelegate {
        final /* synthetic */ TrendingStickersLayout this$0;

        public AnonymousClass7(TrendingStickersLayout r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.StickersAlert.StickersAlertDelegate
        public boolean canSchedule() {
            return TrendingStickersLayout.m13440$$Nest$fgetdelegate(this.this$0).canSchedule();
        }

        @Override // org.telegram.ui.Components.StickersAlert.StickersAlertDelegate
        public boolean isInScheduleMode() {
            return TrendingStickersLayout.m13440$$Nest$fgetdelegate(this.this$0).isInScheduleMode();
        }

        @Override // org.telegram.ui.Components.StickersAlert.StickersAlertDelegate
        public void onStickerSelected(TLRPC.Document r1, String r2, Object r3, MessageObject.SendAnimationData r4, boolean r5, boolean r6, int r7, int r8) {
            TrendingStickersLayout.m13440$$Nest$fgetdelegate(this.this$0).onStickerSelected(r1, r3, r5, r6, r7);
        }
    }

    /* renamed from: org.telegram.ui.Components.TrendingStickersLayout$8, reason: invalid class name */
    public class AnonymousClass8 implements StickersAlert.StickersAlertInstallDelegate {
        final /* synthetic */ TrendingStickersLayout this$0;
        final /* synthetic */ TLRPC.InputStickerSet val$inputStickerSet;

        public AnonymousClass8(TrendingStickersLayout r1, TLRPC.InputStickerSet r2) {
            this.this$0 = r1;
            this.val$inputStickerSet = r2;
        }

        @Override // org.telegram.ui.Components.StickersAlert.StickersAlertInstallDelegate
        public void onStickerSetInstalled() {
            if (TrendingStickersLayout.m13445$$Nest$fgetlistView(this.this$0).getAdapter() != TrendingStickersLayout.m13438$$Nest$fgetadapter(this.this$0)) goto L13;
            int r0 = 0;
        L6:
            if (r0 >= TrendingStickersAdapter.m13465$$Nest$fgetsets(TrendingStickersLayout.m13438$$Nest$fgetadapter(this.this$0)).size()) goto L12;
            TLRPC.StickerSetCovered r1 = (TLRPC.StickerSetCovered) TrendingStickersAdapter.m13465$$Nest$fgetsets(TrendingStickersLayout.m13438$$Nest$fgetadapter(this.this$0)).get(r0);
            if (r1.set.id == this.val$inputStickerSet.id) goto L9;
            r0 = r0 + 1;
            goto L6
        L9:
            TrendingStickersAdapter.m13469$$Nest$minstallStickerSet(TrendingStickersLayout.m13438$$Nest$fgetadapter(this.this$0), r1, null);
            return;
        L12:
            return;
        L13:
            TrendingStickersLayout.m13452$$Nest$fgetsearchAdapter(this.this$0).installStickerSet(this.val$inputStickerSet);
        }

        @Override // org.telegram.ui.Components.StickersAlert.StickersAlertInstallDelegate
        public void onStickerSetUninstalled() {
        }
    }

    /* renamed from: org.telegram.ui.Components.TrendingStickersLayout$9, reason: invalid class name */
    public class AnonymousClass9 implements ValueAnimator.AnimatorUpdateListener {
        int dy;
        final /* synthetic */ TrendingStickersLayout this$0;
        final /* synthetic */ int val$startFrom;

        public AnonymousClass9(TrendingStickersLayout r1, int r2) {
            this.this$0 = r1;
            this.val$startFrom = r2;
            this.dy = 0;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r4) {
            int r42 = (int) (this.val$startFrom * ((Float) r4.getAnimatedValue()).floatValue());
            TrendingStickersLayout.m13458$$Nest$fputscrollFromAnimator(this.this$0, true);
            TrendingStickersLayout.m13445$$Nest$fgetlistView(this.this$0).scrollBy(0, r42 - this.dy);
            TrendingStickersLayout.m13458$$Nest$fputscrollFromAnimator(this.this$0, false);
            this.dy = r42;
        }
    }

    public static abstract class Delegate {
        private String[] lastSearchKeyboardLanguage;

        public Delegate() {
            this.lastSearchKeyboardLanguage = new String[0];
        }

        public boolean canSchedule() {
            return false;
        }

        public boolean canSendSticker() {
            return false;
        }

        public String[] getLastSearchKeyboardLanguage() {
            return this.lastSearchKeyboardLanguage;
        }

        public boolean isInScheduleMode() {
            return false;
        }

        public boolean onListViewInterceptTouchEvent(RecyclerListView r1, MotionEvent r2) {
            return false;
        }

        public boolean onListViewTouchEvent(RecyclerListView r1, RecyclerListView.OnItemClickListener r2, MotionEvent r3) {
            return false;
        }

        public void onStickerSelected(TLRPC.Document r1, Object r2, boolean r3, boolean r4, int r5) {
        }

        public abstract void onStickerSetAdd(TLRPC.StickerSetCovered r1, boolean r2);

        public abstract void onStickerSetRemove(TLRPC.StickerSetCovered r1);

        public void setLastSearchKeyboardLanguage(String[] r1) {
            this.lastSearchKeyboardLanguage = r1;
        }
    }

    public class TrendingStickersAdapter extends RecyclerListView.SelectionAdapter {
        private final SparseArray<Object> cache;
        private final Context context;
        private boolean endReached;
        private boolean loadingMore;
        private final ArrayList<TLRPC.StickerSetCovered> otherPacks;
        private final SparseArray<TLRPC.StickerSetCovered> positionsToSets;
        private final ArrayList<TLRPC.StickerSetCovered> sets;
        private final HashMap<TLRPC.StickerSetCovered, Integer> setsToPosition;
        private int stickersPerRow;
        final /* synthetic */ TrendingStickersLayout this$0;
        private int totalItems;

        /* renamed from: org.telegram.ui.Components.TrendingStickersLayout$TrendingStickersAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends StickerEmojiCell {
            final /* synthetic */ TrendingStickersAdapter this$1;

            public AnonymousClass1(TrendingStickersAdapter r1, Context r2, boolean r3, Theme.ResourcesProvider r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(82.0f), TLObject.FLAG_30));
            }
        }

        /* renamed from: $r8$lambda$1fOc9QPFyjPUHtO8iJZ-iNIRpUE, reason: not valid java name */
        public static /* synthetic */ void m13459$r8$lambda$1fOc9QPFyjPUHtO8iJZiNIRpUE(TrendingStickersAdapter r0, TLRPC.TL_error r1, TLObject r2) {
            r0.lambda$loadMoreStickerSets$2(r1, r2);
        }

        /* renamed from: $r8$lambda$SRrUkqn0-xFhAP3rZQLaLoMjtIs, reason: not valid java name */
        public static /* synthetic */ void m13460$r8$lambda$SRrUkqn0xFhAP3rZQLaLoMjtIs(TrendingStickersAdapter r0, View r1) {
            r0.lambda$onCreateViewHolder$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$rovthhbD6DQAuzLJ3wn1yWdcXF8(TrendingStickersAdapter r0, View r1) {
            r0.lambda$onCreateViewHolder$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$wIJ2GqiiBAVFUhUQr_0ZxgZhnnE(TrendingStickersAdapter r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$loadMoreStickerSets$3(r1, r2);
        }

        /* renamed from: -$$Nest$fgetcache, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SparseArray m13461$$Nest$fgetcache(TrendingStickersAdapter r0) {
            return r0.cache;
        }

        /* renamed from: -$$Nest$fgetendReached, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m13462$$Nest$fgetendReached(TrendingStickersAdapter r0) {
            return r0.endReached;
        }

        /* renamed from: -$$Nest$fgetloadingMore, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m13463$$Nest$fgetloadingMore(TrendingStickersAdapter r0) {
            return r0.loadingMore;
        }

        /* renamed from: -$$Nest$fgetpositionsToSets, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SparseArray m13464$$Nest$fgetpositionsToSets(TrendingStickersAdapter r0) {
            return r0.positionsToSets;
        }

        /* renamed from: -$$Nest$fgetsets, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m13465$$Nest$fgetsets(TrendingStickersAdapter r0) {
            return r0.sets;
        }

        /* renamed from: -$$Nest$fgetsetsToPosition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ HashMap m13466$$Nest$fgetsetsToPosition(TrendingStickersAdapter r0) {
            return r0.setsToPosition;
        }

        /* renamed from: -$$Nest$fgetstickersPerRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13467$$Nest$fgetstickersPerRow(TrendingStickersAdapter r0) {
            return r0.stickersPerRow;
        }

        /* renamed from: -$$Nest$fgettotalItems, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13468$$Nest$fgettotalItems(TrendingStickersAdapter r0) {
            return r0.totalItems;
        }

        /* renamed from: -$$Nest$minstallStickerSet, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13469$$Nest$minstallStickerSet(TrendingStickersAdapter r0, TLRPC.StickerSetCovered r1, View r2) {
            r0.installStickerSet(r1, r2);
        }

        public TrendingStickersAdapter(TrendingStickersLayout r1, Context r2) {
            this.this$0 = r1;
            this.cache = new SparseArray();
            this.sets = new ArrayList();
            this.positionsToSets = new SparseArray();
            this.setsToPosition = new HashMap();
            this.otherPacks = new ArrayList();
            this.stickersPerRow = 5;
            this.context = r2;
        }

        private void bindStickerSetCell(View r13, int r14, boolean r15) {
            MediaDataController r0 = MediaDataController.getInstance(TrendingStickersLayout.m13439$$Nest$fgetcurrentAccount(this.this$0));
            int r1 = this.totalItems;
            ArrayList<TLRPC.StickerSetCovered> r2 = this.sets;
            boolean r3 = true;
            if (r14 >= r1) goto L14;
            TLRPC.StickerSetCovered r12 = r2.get(((Integer) this.cache.get(r14)).intValue());
            ArrayList<Long> r22 = r0.getUnreadStickerSets();
            if (r22 != null) goto L7;
        L9:
            boolean r23 = false;
        L10:
            if (r23 == false) goto L12;
            r0.markFeaturedStickersByIdAsRead(false, r12.set.id);
        L12:
            boolean r7 = r23;
        L13:
            TLRPC.StickerSetCovered r6 = r12;
            r0.preloadStickerSetThumb(r6);
            int r16 = 0;
        L17:
            if (r16 >= TrendingStickersLayout.m13448$$Nest$fgetprimaryInstallingStickerSets(this.this$0).length) goto L29;
            if (TrendingStickersLayout.m13448$$Nest$fgetprimaryInstallingStickerSets(this.this$0)[r16] == null) goto L28;
            TLRPC.TL_messages_stickerSet r24 = MediaDataController.getInstance(TrendingStickersLayout.m13439$$Nest$fgetcurrentAccount(this.this$0)).getStickerSetById(TrendingStickersLayout.m13448$$Nest$fgetprimaryInstallingStickerSets(this.this$0)[r16].set.id);
            if (r24 == null) goto L26;
            if (r24.set.archived == true) goto L26;
            TrendingStickersLayout.m13448$$Nest$fgetprimaryInstallingStickerSets(this.this$0)[r16] = null;
        L26:
            if (TrendingStickersLayout.m13448$$Nest$fgetprimaryInstallingStickerSets(this.this$0)[r16].set.id != r6.set.id) goto L28;
            boolean r11 = true;
        L30:
            boolean r02 = r0.isStickerPackInstalled(r6.set.id);
            if (TrendingStickersLayout.m13443$$Nest$fgetinstallingStickerSets(this.this$0).indexOfKey(r6.set.id) < 0) goto L33;
            boolean r17 = true;
        L35:
            if (TrendingStickersLayout.m13449$$Nest$fgetremovingStickerSets(this.this$0).indexOfKey(r6.set.id) < 0) goto L37;
            boolean r25 = true;
        L38:
            if (r17 == false) goto L41;
            if (r02 == false) goto L41;
            TrendingStickersLayout.m13443$$Nest$fgetinstallingStickerSets(this.this$0).remove(r6.set.id);
            r17 = false;
        L44:
            FeaturedStickerSetInfoCell r5 = (FeaturedStickerSetInfoCell) r13;
            r5.setStickerSet(r6, r7, r15, 0, 0, r11);
            if (r11 == true) goto L48;
            if (r17 == false) goto L48;
            boolean r132 = true;
        L49:
            r5.setAddDrawProgress(r132, r15);
            if (r14 <= 0) goto L56;
            int r142 = r14 - 1;
            if (this.cache.get(r142) != null) goto L54;
        L57:
            r5.setNeedDivider(r3);
            return;
        L54:
            if (this.cache.get(r142).equals(-1) == false) goto L57;
        L56:
            r3 = false;
        L48:
            r132 = false;
        L41:
            if (r25 == false) goto L44;
            if (r02 == true) goto L44;
            TrendingStickersLayout.m13449$$Nest$fgetremovingStickerSets(this.this$0).remove(r6.set.id);
            goto L44
        L37:
            r25 = false;
            goto L38
        L33:
            r17 = false;
        L28:
            r16 = r16 + 1;
            goto L17
        L29:
            r11 = false;
            goto L30
        L7:
            if (r22.contains(Long.valueOf(r12.set.id)) == false) goto L9;
            r23 = true;
            goto L10
        L14:
            r12 = r2.get(((Integer) this.cache.get(r14)).intValue());
            r7 = false;
            goto L13
        }

        private void installStickerSet(TLRPC.StickerSetCovered r7, View r8) {
            int r1 = 0;
        L4:
            if (r1 >= TrendingStickersLayout.m13448$$Nest$fgetprimaryInstallingStickerSets(this.this$0).length) goto L16;
            if (TrendingStickersLayout.m13448$$Nest$fgetprimaryInstallingStickerSets(this.this$0)[r1] == null) goto L15;
            TLRPC.TL_messages_stickerSet r2 = MediaDataController.getInstance(TrendingStickersLayout.m13439$$Nest$fgetcurrentAccount(this.this$0)).getStickerSetById(TrendingStickersLayout.m13448$$Nest$fgetprimaryInstallingStickerSets(this.this$0)[r1].set.id);
            if (r2 == null) goto L13;
            if (r2.set.archived == true) goto L13;
            TrendingStickersLayout.m13448$$Nest$fgetprimaryInstallingStickerSets(this.this$0)[r1] = null;
        L13:
            if (TrendingStickersLayout.m13448$$Nest$fgetprimaryInstallingStickerSets(this.this$0)[r1].set.id != r7.set.id) goto L15;
            return;
        L15:
            r1 = r1 + 1;
        L16:
            int r12 = 0;
        L18:
            if (r12 >= TrendingStickersLayout.m13448$$Nest$fgetprimaryInstallingStickerSets(this.this$0).length) goto L23;
            if (TrendingStickersLayout.m13448$$Nest$fgetprimaryInstallingStickerSets(this.this$0)[r12] == null) goto L21;
            r12 = r12 + 1;
            goto L18
        L21:
            TrendingStickersLayout.m13448$$Nest$fgetprimaryInstallingStickerSets(this.this$0)[r12] = r7;
            boolean r13 = true;
        L24:
            if (r13 == true) goto L32;
            if (r8 == null) goto L32;
            if ((r8 instanceof FeaturedStickerSetCell2) == false) goto L30;
            ((FeaturedStickerSetCell2) r8).setDrawProgress(true, true);
            goto L32
        L30:
            if ((r8 instanceof FeaturedStickerSetInfoCell) == false) goto L32;
            ((FeaturedStickerSetInfoCell) r8).setAddDrawProgress(true, true);
        L32:
            TrendingStickersLayout.m13443$$Nest$fgetinstallingStickerSets(this.this$0).put(r7.set.id, r7);
            if (r8 == null) goto L36;
            TrendingStickersLayout.m13440$$Nest$fgetdelegate(this.this$0).onStickerSetAdd(r7, r13);
            return;
        L36:
            int r82 = this.positionsToSets.size();
            int r14 = 0;
        L37:
            if (r14 >= r82) goto L57;
            TLRPC.StickerSetCovered r22 = this.positionsToSets.get(r14);
            if (r22 == null) goto L44;
            if (r22.set.id != r7.set.id) goto L44;
            notifyItemChanged(r14, 0);
            return;
        L44:
            r14 = r14 + 1;
            goto L37
        L57:
            return;
        L23:
            r13 = false;
            goto L24
        }

        private /* synthetic */ void lambda$loadMoreStickerSets$2(TLRPC.TL_error r10, TLObject r11) {
            this.loadingMore = false;
            if (r10 == null) goto L5;
        L37:
            this.endReached = true;
            return;
        L5:
            if ((r11 instanceof TLRPC.TL_messages_featuredStickers) == false) goto L37;
            ArrayList<TLRPC.StickerSetCovered> r102 = ((TLRPC.TL_messages_featuredStickers) r11).sets;
            if (r102.size() >= 40) goto L10;
            this.endReached = true;
        L10:
            if (r102.isEmpty() == false) goto L12;
            return;
        L12:
            if (this.otherPacks.isEmpty() == false) goto L14;
            SparseArray<Object> r112 = this.cache;
            int r2 = this.totalItems;
            this.totalItems = r2 + 1;
            r112.put(r2, -1);
        L14:
            this.otherPacks.addAll(r102);
            int r113 = this.sets.size();
            int r22 = 0;
        L16:
            if (r22 >= r102.size()) goto L35;
            TLRPC.StickerSetCovered r3 = r102.get(r22);
            if (r3.covers.isEmpty() == true) goto L20;
        L22:
            this.sets.add(r3);
            this.positionsToSets.put(this.totalItems, r3);
            SparseArray<Object> r4 = this.cache;
            int r5 = this.totalItems;
            this.totalItems = r5 + 1;
            int r6 = r113 + 1;
            r4.put(r5, Integer.valueOf(r113));
            if (r3.covers.isEmpty() == true) goto L28;
            int r114 = (int) Math.ceil(r3.covers.size() / this.stickersPerRow);
            int r42 = 0;
        L26:
            if (r42 >= r3.covers.size()) goto L29;
            this.cache.put(this.totalItems + r42, r3.covers.get(r42));
            r42 = r42 + 1;
        L29:
            int r43 = 0;
        L30:
            int r52 = this.stickersPerRow;
            if (r43 >= (r114 * r52)) goto L33;
            this.positionsToSets.put(this.totalItems + r43, r3);
            r43 = r43 + 1;
            goto L30
        L33:
            this.totalItems += r114 * r52;
            r113 = r6;
        L34:
            r22 = r22 + 1;
            goto L16
        L28:
            this.cache.put(this.totalItems, r3.cover);
            r114 = 1;
            goto L29
        L20:
            if (r3.cover != null) goto L22;
        L35:
            notifyDataSetChanged();
        }

        private /* synthetic */ void lambda$loadMoreStickerSets$3(TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new TrendingStickersLayout$TrendingStickersAdapter$$ExternalSyntheticLambda3(this, r3, r2));
        }

        private /* synthetic */ void lambda$onCreateViewHolder$0(View r5) {
            FeaturedStickerSetInfoCell r52 = (FeaturedStickerSetInfoCell) r5.getParent();
            TLRPC.StickerSetCovered r0 = r52.getStickerSet();
            if (TrendingStickersLayout.m13443$$Nest$fgetinstallingStickerSets(this.this$0).indexOfKey(r0.set.id) < 0) goto L5;
            return;
        L5:
            if (TrendingStickersLayout.m13449$$Nest$fgetremovingStickerSets(this.this$0).indexOfKey(r0.set.id) < 0) goto L8;
            return;
        L8:
            if (r52.isInstalled() == false) goto L11;
            TrendingStickersLayout.m13449$$Nest$fgetremovingStickerSets(this.this$0).put(r0.set.id, r0);
            TrendingStickersLayout.m13440$$Nest$fgetdelegate(this.this$0).onStickerSetRemove(r0);
            return;
        L11:
            installStickerSet(r0, r52);
        }

        private /* synthetic */ void lambda$onCreateViewHolder$1(View r5) {
            FeaturedStickerSetCell2 r52 = (FeaturedStickerSetCell2) r5.getParent();
            TLRPC.StickerSetCovered r0 = r52.getStickerSet();
            if (TrendingStickersLayout.m13443$$Nest$fgetinstallingStickerSets(this.this$0).indexOfKey(r0.set.id) < 0) goto L5;
            return;
        L5:
            if (TrendingStickersLayout.m13449$$Nest$fgetremovingStickerSets(this.this$0).indexOfKey(r0.set.id) < 0) goto L8;
            return;
        L8:
            if (r52.isInstalled() == false) goto L11;
            TrendingStickersLayout.m13449$$Nest$fgetremovingStickerSets(this.this$0).put(r0.set.id, r0);
            TrendingStickersLayout.m13440$$Nest$fgetdelegate(this.this$0).onStickerSetRemove(r0);
            return;
        L11:
            installStickerSet(r0, r52);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.totalItems + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 != (getItemCount() - 1)) goto L6;
            return 3;
        L6:
            Object r2 = this.cache.get(r3);
            if (r2 != null) goto L9;
            return 1;
        L9:
            if ((r2 instanceof TLRPC.Document) == false) goto L13;
            return 0;
        L13:
            if (r2.equals(-1) == false) goto L16;
            return 4;
        L16:
            return 2;
        }

        public void getThemeDescriptions(List<ThemeDescription> r1, RecyclerListView r2, ThemeDescription.ThemeDescriptionDelegate r3) {
            FeaturedStickerSetInfoCell.createThemeDescriptions(r1, r2, r3);
            FeaturedStickerSetCell2.createThemeDescriptions(r1, r2, r3);
            GraySectionCell.createThemeDescriptions(r1, r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 5) goto L6;
            return true;
        L6:
            return false;
        }

        public void loadMoreStickerSets() {
            if (TrendingStickersLayout.m13446$$Nest$fgetloaded(this.this$0) == true) goto L5;
            return;
        L5:
            if (this.loadingMore == false) goto L7;
            return;
        L7:
            if (this.endReached == true) goto L13;
            this.loadingMore = true;
            TLRPC.TL_messages_getOldFeaturedStickers r0 = new TLRPC.TL_messages_getOldFeaturedStickers();
            r0.offset = this.otherPacks.size();
            r0.limit = 40;
            ConnectionsManager.getInstance(TrendingStickersLayout.m13439$$Nest$fgetcurrentAccount(this.this$0)).sendRequest(r0, new TrendingStickersLayout$TrendingStickersAdapter$$ExternalSyntheticLambda2(this));
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r4, int r5) {
            int r0 = r4.getItemViewType();
            if (r0 != 0) goto L5;
            TLRPC.Document r02 = (TLRPC.Document) this.cache.get(r5);
            ((StickerEmojiCell) r4.itemView).setSticker(r02, this.positionsToSets.get(r5), false);
            return;
        L5:
            if (r0 != 1) goto L7;
            ((EmptyCell) r4.itemView).setHeight(AndroidUtilities.dp(82.0f));
            return;
        L7:
            if (r0 != 2) goto L9;
        L15:
            bindStickerSetCell(r4.itemView, r5, false);
            return;
        L9:
            if (r0 != 4) goto L11;
            ((GraySectionCell) r4.itemView).setText(LocaleController.getString(R.string.OtherStickers));
            return;
        L11:
            if (r0 == 5) goto L15;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r2, int r3, List r4) {
            if (r4.contains(0) == false) goto L12;
            int r42 = r2.getItemViewType();
            if (r42 != 2) goto L7;
        L10:
            bindStickerSetCell(r2.itemView, r3, true);
            return;
        L7:
            if (r42 == 5) goto L10;
            return;
        L12:
            super.onBindViewHolder(r2, r3, r4);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            if (r8 != 0) goto L5;
            AnonymousClass1 r82 = new AnonymousClass1(this, this.context, false, TrendingStickersLayout.m13450$$Nest$fgetresourcesProvider(this.this$0));
            r82.getImageView().setLayerNum(3);
            AnonymousClass1 r83 = r82;
        L15:
            View r6 = r83;
        L23:
            return new RecyclerListView.Holder(r6);
        L5:
            if (r8 != 1) goto L7;
            View r72 = new EmptyCell(this.context);
        L17:
            r6 = r72;
            goto L23
        L7:
            if (r8 == 2) goto L19;
            if (r8 != 3) goto L10;
            r72 = new View(this.context);
            goto L17
        L10:
            if (r8 != 4) goto L12;
            r72 = new GraySectionCell(this.context, TrendingStickersLayout.m13450$$Nest$fgetresourcesProvider(this.this$0));
            goto L17
        L12:
            if (r8 == 5) goto L14;
            r6 = null;
            goto L23
        L14:
            FeaturedStickerSetCell2 r84 = new FeaturedStickerSetCell2(this.context, TrendingStickersLayout.m13450$$Nest$fgetresourcesProvider(this.this$0));
            r84.setAddOnClickListener(new TrendingStickersLayout$TrendingStickersAdapter$$ExternalSyntheticLambda1(this));
            r84.getImageView().setLayerNum(3);
            r83 = r84;
            goto L15
        L19:
            FeaturedStickerSetInfoCell r0 = new FeaturedStickerSetInfoCell(this.context, 17, true, true, TrendingStickersLayout.m13450$$Nest$fgetresourcesProvider(this.this$0));
            r0.setAddOnClickListener(new TrendingStickersLayout$TrendingStickersAdapter$$ExternalSyntheticLambda0(this));
            r6 = r0;
            goto L23
        }

        public void refreshStickerSets() {
            int r0 = this.this$0.getMeasuredWidth();
            if (r0 == 0) goto L8;
            this.stickersPerRow = Math.max(5, r0 / AndroidUtilities.dp(72.0f));
            if (TrendingStickersLayout.m13444$$Nest$fgetlayoutManager(this.this$0).getSpanCount() == this.stickersPerRow) goto L8;
            TrendingStickersLayout.m13444$$Nest$fgetlayoutManager(this.this$0).setSpanCount(this.stickersPerRow);
            TrendingStickersLayout.m13456$$Nest$fputloaded(this.this$0, false);
        L8:
            if (TrendingStickersLayout.m13446$$Nest$fgetloaded(this.this$0) == false) goto L10;
            return;
        L10:
            this.cache.clear();
            this.positionsToSets.clear();
            this.setsToPosition.clear();
            this.sets.clear();
            this.totalItems = 0;
            MediaDataController r02 = MediaDataController.getInstance(TrendingStickersLayout.m13439$$Nest$fgetcurrentAccount(this.this$0));
            ArrayList r2 = new ArrayList(r02.getFeaturedStickerSets());
            int r3 = r2.size();
            r2.addAll(this.otherPacks);
            int r4 = 0;
            int r5 = 0;
        L11:
            int r7 = 1;
            if (r4 >= r2.size()) goto L34;
            TLRPC.StickerSetCovered r6 = (TLRPC.StickerSetCovered) r2.get(r4);
            if (r6.covers.isEmpty() == true) goto L16;
        L18:
            if (r4 != r3) goto L20;
            SparseArray<Object> r8 = this.cache;
            int r9 = this.totalItems;
            this.totalItems = r9 + 1;
            r8.put(r9, -1);
        L20:
            this.sets.add(r6);
            this.positionsToSets.put(this.totalItems, r6);
            this.setsToPosition.put(r6, Integer.valueOf(this.totalItems));
            SparseArray<Object> r82 = this.cache;
            int r92 = this.totalItems;
            this.totalItems = r92 + 1;
            int r10 = r5 + 1;
            r82.put(r92, Integer.valueOf(r5));
            if (r6.covers.isEmpty() == true) goto L26;
            r7 = (int) Math.ceil(r6.covers.size() / this.stickersPerRow);
            int r52 = 0;
        L24:
            if (r52 >= r6.covers.size()) goto L27;
            this.cache.put(this.totalItems + r52, r6.covers.get(r52));
            r52 = r52 + 1;
        L27:
            int r53 = 0;
        L28:
            int r83 = this.stickersPerRow;
            if (r53 >= (r7 * r83)) goto L31;
            this.positionsToSets.put(this.totalItems + r53, r6);
            r53 = r53 + 1;
            goto L28
        L31:
            this.totalItems += r7 * r83;
            r5 = r10;
        L32:
            r4 = r4 + 1;
            goto L11
        L26:
            this.cache.put(this.totalItems, r6.cover);
            goto L27
        L16:
            if (r6.cover != null) goto L18;
        L34:
            if (this.totalItems == 0) goto L36;
            TrendingStickersLayout.m13456$$Nest$fputloaded(this.this$0, true);
            TrendingStickersLayout.m13455$$Nest$fputhash(this.this$0, r02.getFeaturedStickersHashWithoutUnread(false));
        L36:
            notifyDataSetChanged();
        }

        public void updateColors(RecyclerListView r4) {
            int r3 = r4.getChildCount();
            int r0 = 0;
        L3:
            if (r0 >= r3) goto L11;
            View r1 = r4.getChildAt(r0);
            if ((r1 instanceof FeaturedStickerSetInfoCell) == false) goto L8;
            ((FeaturedStickerSetInfoCell) r1).updateColors();
        L10:
            r0 = r0 + 1;
            goto L3
        L8:
            if ((r1 instanceof FeaturedStickerSetCell2) == false) goto L10;
            ((FeaturedStickerSetCell2) r1).updateColors();
            goto L10
        }
    }

    public static /* synthetic */ boolean $r8$lambda$5lg4jukpj8C0UiabiwEIat04v84(TrendingStickersLayout r0, Delegate r1, RecyclerListView.OnItemClickListener r2, View r3, MotionEvent r4) {
        return r0.lambda$new$1(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$Qa0nRxE4m26INsvkAyTS4kL1zB8(TrendingStickersLayout r0, View r1, int r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TrendingStickersAdapter m13438$$Nest$fgetadapter(TrendingStickersLayout r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13439$$Nest$fgetcurrentAccount(TrendingStickersLayout r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m13440$$Nest$fgetdelegate(TrendingStickersLayout r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetgluedToTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13441$$Nest$fgetgluedToTop(TrendingStickersLayout r0) {
        return r0.gluedToTop;
    }

    /* renamed from: -$$Nest$fgetignoreLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13442$$Nest$fgetignoreLayout(TrendingStickersLayout r0) {
        return r0.ignoreLayout;
    }

    /* renamed from: -$$Nest$fgetinstallingStickerSets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m13443$$Nest$fgetinstallingStickerSets(TrendingStickersLayout r0) {
        return r0.installingStickerSets;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GridLayoutManager m13444$$Nest$fgetlayoutManager(TrendingStickersLayout r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m13445$$Nest$fgetlistView(TrendingStickersLayout r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetloaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13446$$Nest$fgetloaded(TrendingStickersLayout r0) {
        return r0.loaded;
    }

    /* renamed from: -$$Nest$fgetonScrollListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerView.OnScrollListener m13447$$Nest$fgetonScrollListener(TrendingStickersLayout r0) {
        return r0.onScrollListener;
    }

    /* renamed from: -$$Nest$fgetprimaryInstallingStickerSets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.StickerSetCovered[] m13448$$Nest$fgetprimaryInstallingStickerSets(TrendingStickersLayout r0) {
        return r0.primaryInstallingStickerSets;
    }

    /* renamed from: -$$Nest$fgetremovingStickerSets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m13449$$Nest$fgetremovingStickerSets(TrendingStickersLayout r0) {
        return r0.removingStickerSets;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m13450$$Nest$fgetresourcesProvider(TrendingStickersLayout r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetscrollFromAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13451$$Nest$fgetscrollFromAnimator(TrendingStickersLayout r0) {
        return r0.scrollFromAnimator;
    }

    /* renamed from: -$$Nest$fgetsearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StickersSearchAdapter m13452$$Nest$fgetsearchAdapter(TrendingStickersLayout r0) {
        return r0.searchAdapter;
    }

    /* renamed from: -$$Nest$fgetsearchView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchField m13453$$Nest$fgetsearchView(TrendingStickersLayout r0) {
        return r0.searchView;
    }

    /* renamed from: -$$Nest$fgettopOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13454$$Nest$fgettopOffset(TrendingStickersLayout r0) {
        return r0.topOffset;
    }

    /* renamed from: -$$Nest$fputhash, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13455$$Nest$fputhash(TrendingStickersLayout r0, long r1) {
        r0.hash = r1;
    }

    /* renamed from: -$$Nest$fputloaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13456$$Nest$fputloaded(TrendingStickersLayout r0, boolean r1) {
        r0.loaded = r1;
    }

    /* renamed from: -$$Nest$fputmotionEventCatchedByListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13457$$Nest$fputmotionEventCatchedByListView(TrendingStickersLayout r0, boolean r1) {
        r0.motionEventCatchedByListView = r1;
    }

    /* renamed from: -$$Nest$fputscrollFromAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13458$$Nest$fputscrollFromAnimator(TrendingStickersLayout r0, boolean r1) {
        r0.scrollFromAnimator = r1;
    }

    public TrendingStickersLayout(Context r10, Delegate r11) {
        this(r10, r11, new TLRPC.StickerSetCovered[10], new LongSparseArray(), new LongSparseArray(), null, null);
    }

    public TrendingStickersLayout(Context r19, Delegate r20, TLRPC.StickerSetCovered[] r21, LongSparseArray<TLRPC.StickerSetCovered> r22, LongSparseArray<TLRPC.StickerSetCovered> r23, TLRPC.StickerSetCovered r24, Theme.ResourcesProvider r25) {
        super(r19);
        int r9 = UserConfig.selectedAccount;
        this.currentAccount = r9;
        this.highlightProgress = 1.0f;
        this.paint = new Paint();
        this.delegate = r20;
        this.primaryInstallingStickerSets = r21;
        this.installingStickerSets = r22;
        this.removingStickerSets = r23;
        this.scrollToSet = r24;
        this.resourcesProvider = r25;
        TrendingStickersAdapter r10 = new TrendingStickersAdapter(this, r19);
        this.adapter = r10;
        this.searchAdapter = new StickersSearchAdapter(r19, new AnonymousClass1(this, r20), r21, r22, r23, r25);
        FrameLayout r1 = new FrameLayout(r19);
        this.searchLayout = r1;
        r1.setBackgroundColor(getThemedColor(Theme.key_dialogBackground));
        AnonymousClass2 r3 = new AnonymousClass2(this, r19, true, r25);
        this.searchView = r3;
        r3.setHint(LocaleController.getString(R.string.SearchTrendingStickersHint));
        r1.addView(r3, LayoutHelper.createFrame(-1, -1, 48));
        AnonymousClass3 r32 = new AnonymousClass3(this, r19, r20);
        this.listView = r32;
        TrendingStickersLayout$$ExternalSyntheticLambda0 r4 = new TrendingStickersLayout$$ExternalSyntheticLambda0(this);
        r32.setOnTouchListener(new TrendingStickersLayout$$ExternalSyntheticLambda1(this, r20, r4));
        r32.setOverScrollMode(2);
        r32.setClipToPadding(false);
        r32.setItemAnimator(null);
        r32.setLayoutAnimation(null);
        AnonymousClass4 r6 = new AnonymousClass4(this, r19, 5, AndroidUtilities.dp(58.0f), r32);
        this.layoutManager = r6;
        r32.setLayoutManager(r6);
        r6.setSpanSizeLookup(new AnonymousClass5(this));
        r32.setOnScrollListener(new AnonymousClass6(this));
        r32.setAdapter(r10);
        r32.setOnItemClickListener(r4);
        addView(r32, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        View r33 = new View(r19);
        this.shadowView = r33;
        r33.setBackgroundColor(getThemedColor(Theme.key_dialogShadowLine));
        r33.setAlpha(0.0f);
        FrameLayout.LayoutParams r2 = new FrameLayout.LayoutParams(-1, AndroidUtilities.getShadowHeight());
        r2.topMargin = AndroidUtilities.dp(58.0f);
        addView(r33, r2);
        addView(r1, LayoutHelper.createFrame(-1, 58, 51));
        updateColors();
        NotificationCenter r12 = NotificationCenter.getInstance(r9);
        r12.addObserver(this, NotificationCenter.stickersDidLoad);
        r12.addObserver(this, NotificationCenter.featuredStickersDidLoad);
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$new$0(View r2, int r3) {
        RecyclerView.Adapter r22 = this.listView.getAdapter();
        StickersSearchAdapter r0 = this.searchAdapter;
        if (r22 != r0) goto L6;
        TLRPC.StickerSetCovered r23 = r0.getSetForPosition(r3);
    L9:
        if (r23 == null) goto L12;
        showStickerSet(r23.set);
        return;
    L12:
        return;
    L6:
        if (r3 >= TrendingStickersAdapter.m13468$$Nest$fgettotalItems(this.adapter)) goto L8;
        r23 = (TLRPC.StickerSetCovered) TrendingStickersAdapter.m13464$$Nest$fgetpositionsToSets(this.adapter).get(r3);
        goto L9
    L8:
        r23 = null;
        goto L9
    }

    private /* synthetic */ boolean lambda$new$1(Delegate r1, RecyclerListView.OnItemClickListener r2, View r3, MotionEvent r4) {
        return r1.onListViewTouchEvent(this.listView, r2, r4);
    }

    private void setShadowVisible(boolean r3) {
        if (this.shadowVisible == r3) goto L10;
        this.shadowVisible = r3;
        ViewPropertyAnimator r2 = this.shadowView.animate();
        if (r3 == false) goto L7;
        float r32 = 1.0f;
    L8:
        r2.alpha(r32).setDuration(200).start();
        return;
    L7:
        r32 = 0.0f;
        goto L8
    }

    private void showStickerSet(TLRPC.InputStickerSet r10) {
        if (this.delegate.canSendSticker() == false) goto L6;
        AnonymousClass7 r0 = new AnonymousClass7(this);
    L5:
        AnonymousClass7 r6 = r0;
        StickersAlert r1 = new StickersAlert(getContext(), this.parentFragment, r10, null, r6, this.resourcesProvider, false);
        r1.setShowTooltipWhenToggle(false);
        r1.setInstallDelegate(new AnonymousClass8(this, r10));
        this.parentFragment.showDialog(r1);
        return;
    L6:
        r0 = null;
        goto L5
    }

    private void showStickerSet(TLRPC.StickerSet r2) {
        showStickerSet(r2, null);
    }

    private void updateLastItemInAdapter() {
        this.listView.getAdapter().notifyItemChanged(r1.getItemCount() - 1);
    }

    private void updateVisibleTrendingSets() {
        RecyclerView.Adapter r3 = this.listView.getAdapter();
        if (r3 == null) goto L6;
        r3.notifyItemRangeChanged(0, r3.getItemCount(), 0);
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r4, int r5, Object... r6) {
        if (r4 != NotificationCenter.stickersDidLoad) goto L13;
        if (((Integer) r6[0]).intValue() == 0) goto L7;
        return;
    L7:
        if (this.loaded == false) goto L10;
        updateVisibleTrendingSets();
        return;
    L10:
        this.adapter.refreshStickerSets();
        return;
    L13:
        if (r4 == NotificationCenter.featuredStickersDidLoad) goto L15;
        return;
    L15:
        if (this.hash == MediaDataController.getInstance(this.currentAccount).getFeaturedStickersHashWithoutUnread(false)) goto L18;
        this.loaded = false;
    L18:
        if (this.loaded == false) goto L21;
        updateVisibleTrendingSets();
        return;
    L21:
        this.adapter.refreshStickerSets();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r11) {
        float r0 = this.highlightProgress;
        if (r0 != 0.0f) goto L5;
    L24:
        Canvas r4 = r11;
    L30:
        super.dispatchDraw(r4);
        return;
    L5:
        if (this.scrollToSet == null) goto L24;
        float r02 = r0 - 0.0053333333f;
        this.highlightProgress = r02;
        if (r02 >= 0.0f) goto L9;
        this.highlightProgress = 0.0f;
    L10:
        Integer r03 = (Integer) TrendingStickersAdapter.m13466$$Nest$fgetsetsToPosition(this.adapter).get(this.scrollToSet);
        if (r03 == null) goto L24;
        View r1 = this.layoutManager.findViewByPosition(r03.intValue());
        if (r1 == null) goto L15;
        int r2 = (int) r1.getY();
        int r3 = ((int) r1.getY()) + r1.getMeasuredHeight();
    L16:
        View r04 = this.layoutManager.findViewByPosition(r03.intValue() + 1);
        if (r04 == null) goto L21;
        if (r1 != null) goto L20;
        r2 = (int) r04.getY();
    L20:
        r3 = ((int) r04.getY()) + r04.getMeasuredHeight();
    L21:
        if (r1 != null) goto L25;
        if (r04 == null) goto L24;
    L25:
        this.paint.setColor(Theme.getColor(Theme.key_featuredStickers_addButton));
        float r05 = this.highlightProgress;
        if (r05 >= 0.06f) goto L28;
        float r06 = r05 / 0.06f;
    L29:
        this.paint.setAlpha((int) (r06 * 25.5f));
        r4 = r11;
        r4.drawRect(0.0f, r2, getMeasuredWidth(), r3, this.paint);
        goto L30
    L28:
        r06 = 1.0f;
        goto L29
    L15:
        r2 = -1;
        r3 = -1;
        goto L16
    L9:
        invalidate();
        goto L10
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r3) {
        this.motionEventCatchedByListView = false;
        boolean r0 = super.dispatchTouchEvent(r3);
        if (this.motionEventCatchedByListView == true) goto L5;
        MotionEvent r32 = MotionEvent.obtain(r3);
        this.listView.dispatchTouchEvent(r32);
        r32.recycle();
    L5:
        return r0;
    }

    public int getContentTopOffset() {
        return this.topOffset;
    }

    public void getThemeDescriptions(List<ThemeDescription> r12, ThemeDescription.ThemeDescriptionDelegate r13) {
        this.searchView.getThemeDescriptions(r12);
        this.adapter.getThemeDescriptions(r12, this.listView, r13);
        this.searchAdapter.getThemeDescriptions(r12, this.listView, r13);
        r12.add(new ThemeDescription(this.shadowView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_dialogShadowLine));
        r12.add(new ThemeDescription(this.searchLayout, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_dialogBackground));
    }

    public void glueToTop(boolean r3) {
        this.gluedToTop = r3;
        if (r3 == true) goto L5;
        ValueAnimator r32 = this.glueToTopAnimator;
        if (r32 == null) goto L16;
        r32.removeAllListeners();
        this.glueToTopAnimator.cancel();
        this.glueToTopAnimator = null;
        return;
    L16:
        return;
    L5:
        if (getContentTopOffset() > 0) goto L7;
        return;
    L7:
        if (this.glueToTopAnimator != null) goto L15;
        int r33 = getContentTopOffset();
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.glueToTopAnimator = r0;
        r0.addUpdateListener(new AnonymousClass9(this, r33));
        this.glueToTopAnimator.addListener(new AnonymousClass10(this));
        this.glueToTopAnimator.setDuration(250);
        this.glueToTopAnimator.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        this.glueToTopAnimator.start();
        return;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration r1) {
        super.onConfigurationChanged(r1);
        updateLastItemInAdapter();
        this.wasLayout = false;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        if (this.wasLayout == true) goto L10;
        this.wasLayout = true;
        this.adapter.refreshStickerSets();
        if (this.scrollToSet == null) goto L11;
        Integer r12 = (Integer) TrendingStickersAdapter.m13466$$Nest$fgetsetsToPosition(this.adapter).get(this.scrollToSet);
        if (r12 == null) goto L12;
        this.layoutManager.scrollToPositionWithOffset(r12.intValue(), (-this.listView.getPaddingTop()) + AndroidUtilities.dp(58.0f));
        return;
    L12:
        return;
    L11:
        return;
    }

    public void recycle() {
        NotificationCenter r0 = NotificationCenter.getInstance(this.currentAccount);
        r0.removeObserver(this, NotificationCenter.stickersDidLoad);
        r0.removeObserver(this, NotificationCenter.featuredStickersDidLoad);
    }

    public void setContentViewPaddingTop(int r3) {
        int r32 = r3 + AndroidUtilities.dp(58.0f);
        if (this.listView.getPaddingTop() == r32) goto L6;
        this.ignoreLayout = true;
        this.listView.setPadding(0, r32, 0, 0);
        this.ignoreLayout = false;
        return;
    }

    public void setOnScrollListener(RecyclerView.OnScrollListener r1) {
        this.onScrollListener = r1;
    }

    public void setParentFragment(BaseFragment r1) {
        this.parentFragment = r1;
    }

    public void showStickerSet(TLRPC.StickerSet r3, TLRPC.InputStickerSet r4) {
        if (r3 == null) goto L4;
        r4 = new TLRPC.TL_inputStickerSetID();
        r4.access_hash = r3.access_hash;
        r4.id = r3.id;
    L4:
        if (r4 == null) goto L7;
        showStickerSet(r4);
        return;
    }

    public boolean update() {
        int r0 = this.listView.getChildCount();
        RecyclerListView r1 = this.listView;
        if (r0 > 0) goto L6;
        int r02 = r1.getPaddingTop();
        this.topOffset = r02;
        this.listView.setTopGlowOffset(r02);
        this.searchLayout.setTranslationY(this.topOffset);
        this.shadowView.setTranslationY(this.topOffset);
        setShadowVisible(false);
        return true;
    L6:
        View r03 = r1.getChildAt(0);
        int r12 = 1;
    L7:
        int r4 = this.listView.getChildCount();
        RecyclerListView r5 = this.listView;
        if (r12 >= r4) goto L13;
        View r42 = r5.getChildAt(r12);
        if (r42.getTop() >= r03.getTop()) goto L12;
        r03 = r42;
    L12:
        r12 = r12 + 1;
        goto L7
    L13:
        RecyclerListView.Holder r13 = (RecyclerListView.Holder) r5.findContainingViewHolder(r03);
        int r04 = r03.getTop() - AndroidUtilities.dp(58.0f);
        if (r04 <= 0) goto L19;
        if (r13 == null) goto L19;
        if (r13.getAdapterPosition() != 0) goto L19;
        int r14 = r04;
    L20:
        if (r04 >= 0) goto L22;
        boolean r05 = true;
    L23:
        setShadowVisible(r05);
        if (this.topOffset == r14) goto L27;
        this.topOffset = r14;
        this.listView.setTopGlowOffset(r14 + AndroidUtilities.dp(58.0f));
        this.searchLayout.setTranslationY(this.topOffset);
        this.shadowView.setTranslationY(this.topOffset);
        return true;
    L27:
        return false;
    L22:
        r05 = false;
    L19:
        r14 = 0;
        goto L20
    }

    public void updateColors() {
        RecyclerView.Adapter r0 = this.listView.getAdapter();
        TrendingStickersAdapter r1 = this.adapter;
        if (r0 != r1) goto L6;
        r1.updateColors(this.listView);
        return;
    L6:
        this.searchAdapter.updateColors(this.listView);
    }
}
