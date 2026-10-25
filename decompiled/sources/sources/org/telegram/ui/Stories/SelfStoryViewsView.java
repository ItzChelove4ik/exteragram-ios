package org.telegram.ui.Stories;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Stories.SelfStoriesPreviewView;
import org.telegram.ui.Stories.SelfStoryViewsPage;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.StoryViewer;

/* loaded from: classes7.dex */
public class SelfStoryViewsView extends FrameLayout {
    public float bottomPadding;
    private int currentState;
    private long dialogId;
    ArrayList<SelfStoryViewsPage> itemViews;
    int keyboardHeight;
    boolean listenPager;
    public float maxSelfStoriesViewsOffset;
    private final PagerAdapter pagerAdapter;
    float progressToKeyboard;
    float progressToOpen;
    Theme.ResourcesProvider resourcesProvider;
    SelfStoriesPreviewView selfStoriesPreviewView;
    float selfStoriesViewsOffset;
    private Drawable shadowDrawable;
    SelfStoryViewsPage.FiltersState sharedFilterState;
    ArrayList<StoryItemInternal> storyItems;
    StoryViewer storyViewer;
    float toHeight;
    public float toY;
    ViewPagerInner viewPager;
    ContainerView viewPagerContainer;

    /* renamed from: org.telegram.ui.Stories.SelfStoryViewsView$1, reason: invalid class name */
    public class AnonymousClass1 extends SelfStoriesPreviewView {
        final /* synthetic */ SelfStoryViewsView this$0;
        final /* synthetic */ StoryViewer val$storyViewer;

        public AnonymousClass1(SelfStoryViewsView r1, Context r2, StoryViewer r3) {
            this.this$0 = r1;
            this.val$storyViewer = r3;
            super(r2);
        }

        @Override // org.telegram.ui.Stories.SelfStoriesPreviewView
        public void onCenteredImageTap() {
            this.val$storyViewer.cancelSwipeToViews(false);
        }

        @Override // org.telegram.ui.Stories.SelfStoriesPreviewView
        public void onClosestPositionChanged(int r4) {
            super.onClosestPositionChanged(r4);
            SelfStoryViewsView r0 = this.this$0;
            if (r0.listenPager == false) goto L6;
            return;
        L6:
            if (r0.viewPager.getCurrentItem() != r4) goto L22;
        L11:
            StoryViewer r02 = this.val$storyViewer;
            if (r02.storiesList == null) goto L24;
            StoryViewer.PlaceProvider r03 = r02.placeProvider;
            if (r03 != null) goto L16;
            return;
        L16:
            if (r4 >= 10) goto L19;
            r03.loadNext(false);
            return;
        L19:
            if (r4 < (this.storyItems.size() - 10)) goto L27;
            this.val$storyViewer.placeProvider.loadNext(true);
            return;
        L27:
            return;
        L24:
            return;
        L22:
            this.this$0.viewPager.setCurrentItem(r4, false);     // Catch: Throwable -> L9
        L9:
            th = move-exception;
            FileLog.e(th);
            this.this$0.viewPager.getAdapter().notifyDataSetChanged();
            this.this$0.viewPager.setCurrentItem(r4, false);
            goto L11
        }

        @Override // org.telegram.ui.Stories.SelfStoriesPreviewView
        public void onDragging() {
            this.this$0.listenPager = false;
        }
    }

    /* renamed from: org.telegram.ui.Stories.SelfStoryViewsView$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewPagerInner {
        final /* synthetic */ SelfStoryViewsView this$0;

        public AnonymousClass2(SelfStoryViewsView r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        private boolean checkTopOffset(MotionEvent r1) {
            if (r1.getY() >= SelfStoryViewsView.m19680$$Nest$mgetCurrentTopOffset(this.this$0)) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            if (checkTopOffset(r2) == false) goto L9;
            if (r2.getAction() != 0) goto L9;
            return false;
        L9:
            return super.dispatchTouchEvent(r2);
        }

        @Override // org.telegram.ui.Stories.SelfStoryViewsView.ViewPagerInner, androidx.viewpager.widget.ViewPager, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r4) {
            if (checkTopOffset(r4) == false) goto L6;
            return false;
        L6:
            if (Math.abs(SelfStoryViewsView.m19680$$Nest$mgetCurrentTopOffset(this.this$0) - this.this$0.bottomPadding) <= AndroidUtilities.dp(1.0f)) goto L9;
            return false;
        L9:
            return super.onInterceptTouchEvent(r4);
        }

        @Override // org.telegram.ui.Stories.SelfStoryViewsView.ViewPagerInner, androidx.viewpager.widget.ViewPager, android.view.View
        public boolean onTouchEvent(MotionEvent r4) {
            if (checkTopOffset(r4) == false) goto L6;
            return false;
        L6:
            if (Math.abs(SelfStoryViewsView.m19680$$Nest$mgetCurrentTopOffset(this.this$0) - this.this$0.bottomPadding) <= AndroidUtilities.dp(1.0f)) goto L9;
            return false;
        L9:
            return super.onTouchEvent(r4);
        }
    }

    /* renamed from: org.telegram.ui.Stories.SelfStoryViewsView$3, reason: invalid class name */
    public class AnonymousClass3 implements ViewPager.OnPageChangeListener {
        final /* synthetic */ SelfStoryViewsView this$0;

        public AnonymousClass3(SelfStoryViewsView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int r2) {
            SelfStoryViewsView.m19679$$Nest$fputcurrentState(this.this$0, r2);
            if (SelfStoryViewsView.m19676$$Nest$fgetcurrentState(this.this$0) != 1) goto L6;
            this.this$0.listenPager = true;
            return;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int r1, float r2, int r3) {
            SelfStoryViewsView r0 = this.this$0;
            if (r0.listenPager == false) goto L6;
            r0.selfStoriesPreviewView.scrollToPositionWithOffset(r1, r2);
            return;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int r1) {
        }
    }

    /* renamed from: org.telegram.ui.Stories.SelfStoryViewsView$4, reason: invalid class name */
    public class AnonymousClass4 extends PagerAdapter {
        final /* synthetic */ SelfStoryViewsView this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ StoryViewer val$storyViewer;

        /* renamed from: org.telegram.ui.Stories.SelfStoryViewsView$4$1, reason: invalid class name */
        public class AnonymousClass1 extends SelfStoryViewsPage {
            final /* synthetic */ AnonymousClass4 this$1;

            public AnonymousClass1(AnonymousClass4 r1, StoryViewer r2, Context r3, SelfStoryViewsPage.FiltersState r4, Utilities.Callback r5) {
                this.this$1 = r1;
                super(r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Stories.SelfStoryViewsPage
            public void onTopOffsetChanged(int r4) {
                super.onTopOffsetChanged(r4);
                if (((Integer) getTag()).intValue() != this.this$1.this$0.viewPager.getCurrentItem()) goto L6;
                float r42 = r4;
                this.this$1.this$0.selfStoriesPreviewView.setAlpha(Utilities.clamp(r42 / this.this$1.this$0.bottomPadding, 1.0f, 0.0f));
                SelfStoryViewsView r3 = this.this$1.this$0;
                r3.selfStoriesPreviewView.setTranslationY((-(r3.bottomPadding - r42)) / 2.0f);
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$KZ__W5iPFd1UbsVXGD2LpYdOBsQ(AnonymousClass4 r0, SelfStoryViewsPage r1) {
            r0.lambda$instantiateItem$0(r1);
        }

        public AnonymousClass4(SelfStoryViewsView r1, StoryViewer r2, Context r3) {
            this.this$0 = r1;
            this.val$storyViewer = r2;
            this.val$context = r3;
        }

        private /* synthetic */ void lambda$instantiateItem$0(SelfStoryViewsPage r3) {
            int r0 = 0;
        L4:
            if (r0 >= this.this$0.itemViews.size()) goto L9;
            if (r3 == this.this$0.itemViews.get(r0)) goto L8;
            this.this$0.itemViews.get(r0).updateSharedState();
        L8:
            r0 = r0 + 1;
            goto L4
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup r1, int r2, Object r3) {
            r1.removeView((View) r3);
            this.this$0.itemViews.remove(r3);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.this$0.storyItems.size();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup r7, int r8) {
            AnonymousClass1 r0 = new AnonymousClass1(this, this.val$storyViewer, this.val$context, this.this$0.sharedFilterState, new SelfStoryViewsView$4$$ExternalSyntheticLambda0(this));
            r0.setTag(Integer.valueOf(r8));
            r0.setShadowDrawable(SelfStoryViewsView.m19678$$Nest$fgetshadowDrawable(this.this$0));
            r0.setPadding(0, AndroidUtilities.dp(16.0f), 0, 0);
            r0.setStoryItem(SelfStoryViewsView.m19677$$Nest$fgetdialogId(this.this$0), this.this$0.storyItems.get(r8));
            r0.setListBottomPadding(this.this$0.bottomPadding);
            r7.addView(r0);
            this.this$0.itemViews.add(r0);
            return r0;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View r1, Object r2) {
            if (r1 != r2) goto L5;
            return true;
        L5:
            return false;
        }
    }

    public class ContainerView extends FrameLayout implements NestedScrollingParent3 {
        private final NestedScrollingParentHelper nestedScrollingParentHelper;
        final /* synthetic */ SelfStoryViewsView this$0;

        public ContainerView(SelfStoryViewsView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        }

        @Override // androidx.core.view.NestedScrollingParent2
        public void onNestedPreScroll(View r2, int r3, int r4, int[] r5, int r6) {
            SelfStoryViewsView r22 = this.this$0;
            if (r22.keyboardHeight > 0) goto L16;
            float r32 = r22.storyViewer.selfStoriesViewsOffset;
            float r62 = r22.maxSelfStoriesViewsOffset;
            if (r32 >= r62) goto L14;
            if (r4 <= 0) goto L15;
            float r33 = r32 + r4;
            r5[1] = r4;
            if (r33 > r62) goto L12;
            r62 = r33;
        L12:
            r22.setOffset(r62);
            this.this$0.storyViewer.setSelfStoriesViewsOffset(r62);
            return;
        L15:
            return;
        L14:
            return;
        }

        @Override // androidx.core.view.NestedScrollingParent2
        public void onNestedScroll(View r1, int r2, int r3, int r4, int r5, int r6) {
        }

        @Override // androidx.core.view.NestedScrollingParent3
        public void onNestedScroll(View r1, int r2, int r3, int r4, int r5, int r6, int[] r7) {
            SelfStoryViewsView r12 = this.this$0;
            if (r12.keyboardHeight > 0) goto L15;
            if (r5 == 0) goto L13;
            if (r3 != 0) goto L14;
            float r22 = r12.storyViewer.selfStoriesViewsOffset;
            float r32 = r5 + r22;
            if (r32 > r22) goto L11;
            r22 = r32;
        L11:
            r12.setOffset(r22);
            this.this$0.storyViewer.setSelfStoriesViewsOffset(r22);
            return;
        L14:
            return;
        L13:
            return;
        }

        @Override // androidx.core.view.NestedScrollingParent2
        public void onNestedScrollAccepted(View r1, View r2, int r3, int r4) {
            this.nestedScrollingParentHelper.onNestedScrollAccepted(r1, r2, r3);
        }

        @Override // androidx.core.view.NestedScrollingParent2
        public boolean onStartNestedScroll(View r1, View r2, int r3, int r4) {
            if (this.this$0.keyboardHeight <= 0) goto L6;
            return false;
        L6:
            if (r3 != 2) goto L9;
            return true;
        L9:
            return false;
        }

        @Override // androidx.core.view.NestedScrollingParent2
        public void onStopNestedScroll(View r1, int r2) {
            this.nestedScrollingParentHelper.onStopNestedScroll(r1);
        }
    }

    public class StoryItemInternal {
        public TL_stories.StoryItem storyItem;
        final /* synthetic */ SelfStoryViewsView this$0;
        public StoriesController.UploadingStory uploadingStory;

        public StoryItemInternal(SelfStoryViewsView r1, TL_stories.StoryItem r2) {
            this.this$0 = r1;
            this.storyItem = r2;
        }

        public StoryItemInternal(SelfStoryViewsView r1, StoriesController.UploadingStory r2) {
            this.this$0 = r1;
            this.uploadingStory = r2;
        }
    }

    public class ViewPagerInner extends ViewPager {
        boolean gesturesEnabled;
        final /* synthetic */ SelfStoryViewsView this$0;

        public ViewPagerInner(SelfStoryViewsView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L6;
            this.gesturesEnabled = true;
        L6:
            if (this.gesturesEnabled == true) goto L8;
        L12:
            return false;
        L8:
            if (this.this$0.keyboardHeight > 0) goto L12;
            return super.onInterceptTouchEvent(r3);
        }

        @Override // androidx.viewpager.widget.ViewPager, android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (r2.getAction() != 0) goto L6;
            this.gesturesEnabled = true;
        L6:
            if (this.gesturesEnabled == true) goto L8;
            return false;
        L8:
            if (this.this$0.keyboardHeight <= 0) goto L11;
            return false;
        L11:
            return super.onTouchEvent(r2);
        }
    }

    public static /* synthetic */ void $r8$lambda$nGJ8cGur1GMGHYb0_k2l5JvRig8(SelfStoryViewsView r0, ValueAnimator r1) {
        r0.lambda$setKeyboardHeight$0(r1);
    }

    /* renamed from: -$$Nest$fgetcurrentState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19676$$Nest$fgetcurrentState(SelfStoryViewsView r0) {
        return r0.currentState;
    }

    /* renamed from: -$$Nest$fgetdialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m19677$$Nest$fgetdialogId(SelfStoryViewsView r2) {
        return r2.dialogId;
    }

    /* renamed from: -$$Nest$fgetshadowDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m19678$$Nest$fgetshadowDrawable(SelfStoryViewsView r0) {
        return r0.shadowDrawable;
    }

    /* renamed from: -$$Nest$fputcurrentState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19679$$Nest$fputcurrentState(SelfStoryViewsView r0, int r1) {
        r0.currentState = r1;
    }

    /* renamed from: -$$Nest$mgetCurrentTopOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19680$$Nest$mgetCurrentTopOffset(SelfStoryViewsView r0) {
        return r0.getCurrentTopOffset();
    }

    public SelfStoryViewsView(Context r8, StoryViewer r9) {
        super(r8);
        this.storyItems = new ArrayList();
        this.itemViews = new ArrayList();
        this.sharedFilterState = new SelfStoryViewsPage.FiltersState();
        this.resourcesProvider = r9.resourcesProvider;
        this.storyViewer = r9;
        this.selfStoriesPreviewView = new AnonymousClass1(this, getContext(), r9);
        Drawable r0 = r8.getResources().getDrawable(R.drawable.sheet_shadow_round).mutate();
        this.shadowDrawable = r0;
        r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogBackground, this.resourcesProvider), PorterDuff.Mode.MULTIPLY));
        this.viewPagerContainer = new ContainerView(this, r8);
        AnonymousClass2 r02 = new AnonymousClass2(this, r8);
        this.viewPager = r02;
        r02.addOnPageChangeListener(new AnonymousClass3(this));
        ViewPagerInner r03 = this.viewPager;
        AnonymousClass4 r1 = new AnonymousClass4(this, r9, r8);
        this.pagerAdapter = r1;
        r03.setAdapter(r1);
        this.viewPagerContainer.addView(this.viewPager, LayoutHelper.createFrame(-1, -1.0f, 0, 0.0f, 0.0f, 0.0f, 0.0f));
        addView(this.selfStoriesPreviewView, LayoutHelper.createFrame(-1, -1.0f));
        addView(this.viewPagerContainer);
        setVisibility(4);
    }

    private float getCurrentTopOffset() {
        float r0 = this.bottomPadding;
        SelfStoryViewsPage r1 = getCurrentPage();
        if (r1 != null) goto L5;
        return r0;
    L5:
        return r1.getTopOffset();
    }

    private /* synthetic */ void lambda$setKeyboardHeight$0(ValueAnimator r1) {
        this.progressToKeyboard = ((Float) r1.getAnimatedValue()).floatValue();
        updateTranslation();
    }

    private void updateTranslation() {
        this.viewPagerContainer.setTranslationY(((-this.bottomPadding) + getMeasuredHeight()) - this.selfStoriesViewsOffset);
    }

    public SelfStoriesPreviewView.ImageHolder getCrossfadeToImage() {
        return this.selfStoriesPreviewView.getCenteredImageReciever();
    }

    public SelfStoryViewsPage getCurrentPage() {
        int r0 = 0;
    L4:
        if (r0 >= this.itemViews.size()) goto L10;
        if (((Integer) this.itemViews.get(r0).getTag()).intValue() == this.viewPager.getCurrentItem()) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return this.itemViews.get(r0);
    L10:
        return null;
    }

    public TL_stories.StoryItem getSelectedStory() {
        int r0 = this.selfStoriesPreviewView.getClosestPosition();
        if (r0 >= 0) goto L5;
        return null;
    L5:
        if (r0 < this.storyItems.size()) goto L8;
        return null;
    L8:
        return this.storyItems.get(r0).storyItem;
    }

    public boolean onBackPressed() {
        if (this.keyboardHeight <= 0) goto L6;
        AndroidUtilities.hideKeyboard(this);
        return true;
    L6:
        SelfStoryViewsPage r1 = getCurrentPage();
        if (r1 != null) goto L9;
        return false;
    L9:
        return r1.onBackPressed();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r7, int r8) {
        int r1 = 0;
        if (this.storyViewer.ATTACH_TO_FRAGMENT == false) goto L5;
        int r0 = AndroidUtilities.statusBarHeight;
    L6:
        int r2 = View.MeasureSpec.getSize(r8);
        ((FrameLayout.LayoutParams) this.selfStoriesPreviewView.getLayoutParams()).topMargin = r0;
        this.toHeight = this.selfStoriesPreviewView.getFinalHeight();
        this.toY = AndroidUtilities.dp(20.0f) + r0;
        ((FrameLayout.LayoutParams) this.viewPagerContainer.getLayoutParams()).topMargin = AndroidUtilities.statusBarHeight;
        float r02 = (((r0 + AndroidUtilities.dp(20.0f)) + this.toHeight) + AndroidUtilities.dp(24.0f)) - AndroidUtilities.statusBarHeight;
        this.bottomPadding = r02;
        this.maxSelfStoriesViewsOffset = r2 - r02;
    L8:
        if (r1 >= this.itemViews.size()) goto L10;
        this.itemViews.get(r1).setListBottomPadding(this.bottomPadding);
        r1 = r1 + 1;
        goto L8
    L10:
        super.onMeasure(r7, r8);
        return;
    L5:
        r0 = 0;
        goto L6
    }

    public void setItems(long r4, ArrayList<TL_stories.StoryItem> r6, int r7) {
        this.storyItems.clear();
        this.dialogId = r4;
        int r42 = 0;
        int r5 = 0;
    L4:
        if (r5 >= r6.size()) goto L6;
        this.storyItems.add(new StoryItemInternal(this, r6.get(r5)));
        r5 = r5 + 1;
        goto L4
    L6:
        ArrayList<StoriesController.UploadingStory> r52 = MessagesController.getInstance(this.storyViewer.currentAccount).storiesController.getUploadingStories(UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId());
        if (r52 != null) goto L9;
    L11:
        this.selfStoriesPreviewView.setItems(this.storyItems, r7);
        this.viewPager.setAdapter(null);
        this.viewPager.setAdapter(this.pagerAdapter);
        this.pagerAdapter.notifyDataSetChanged();
        this.viewPager.setCurrentItem(r7);
        return;
    L9:
        if (r42 >= r52.size()) goto L11;
        this.storyItems.add(new StoryItemInternal(this, r52.get(r42)));
        r42 = r42 + 1;
        goto L9
    }

    public void setKeyboardHeight(int r6) {
        if (this.keyboardHeight < AndroidUtilities.dp(20.0f)) goto L5;
        boolean r0 = true;
    L7:
        if (r6 < AndroidUtilities.dp(20.0f)) goto L9;
        boolean r1 = true;
    L10:
        if (r1 == r0) goto L16;
        float r02 = this.progressToKeyboard;
        if (r1 == false) goto L14;
        float r12 = 1.0f;
    L15:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r12});
        r03.addUpdateListener(new SelfStoryViewsView$$ExternalSyntheticLambda0(this));
        r03.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        r03.setDuration(250);
        r03.start();
        goto L16
    L14:
        r12 = 0.0f;
    L16:
        this.keyboardHeight = r6;
        if (r6 <= 0) goto L22;
        SelfStoryViewsPage r5 = getCurrentPage();
        if (r5 == null) goto L23;
        r5.onKeyboardShown();
        return;
    L23:
        return;
    L22:
        return;
    L9:
        r1 = false;
        goto L10
    L5:
        r0 = false;
        goto L7
    }

    public void setOffset(float r9) {
        if (this.selfStoriesViewsOffset == r9) goto L34;
        this.selfStoriesViewsOffset = r9;
        updateTranslation();
        float r0 = this.progressToOpen;
        float r92 = Utilities.clamp(r9 / this.maxSelfStoriesViewsOffset, 1.0f, 0.0f);
        this.progressToOpen = r92;
        Utilities.clamp(r92 / 0.5f, 1.0f, 0.0f);
        PeerStoriesView r93 = this.storyViewer.getCurrentPeerView();
        if (r0 == 1.0f) goto L8;
    L18:
        if (r93 == null) goto L20;
        this.selfStoriesPreviewView.imagesFromY = r93.storyContainer.getTop();
        this.selfStoriesPreviewView.imagesFromW = r93.storyContainer.getMeasuredWidth();
        this.selfStoriesPreviewView.imagesFromH = r93.storyContainer.getMeasuredHeight();
    L20:
        this.selfStoriesPreviewView.setProgressToOpen(this.progressToOpen);
        ViewPagerInner r94 = this.viewPager;
        if (r94.gesturesEnabled == false) goto L26;
        if (this.progressToOpen == 1.0f) goto L26;
        r94.onTouchEvent(AndroidUtilities.emptyMotionEvent());
    L26:
        if (this.progressToOpen != 0.0f) goto L28;
        int r95 = 4;
    L29:
        setVisibility(r95);
        if (this.progressToOpen == 1.0f) goto L33;
        this.viewPager.gesturesEnabled = false;
        return;
    L33:
        return;
    L28:
        r95 = 0;
        goto L29
    L8:
        if (this.progressToOpen == 1.0f) goto L18;
        if (this.storyViewer.storiesList == null) goto L15;
        MessageObject r02 = this.storyViewer.storiesList.messageObjects.get(Utilities.clamp(this.selfStoriesPreviewView.getClosestPosition(), this.storyViewer.storiesList.messageObjects.size() - 1, 0));
        long r6 = StoriesController.StoriesList.day(r02);
        ImageReceiver r4 = this.storyViewer.transitionViewHolder.storyImage;
        if (r4 == null) goto L14;
        r4.setVisible(true, true);
        this.storyViewer.transitionViewHolder.storyImage = null;
    L14:
        this.storyViewer.storiesViewPager.setCurrentDate(r6, r02.storyItem.id);
    L17:
        this.selfStoriesPreviewView.abortScroll();
        goto L18
    L15:
        if (r93 == null) goto L17;
        r93.selectPosition(this.selfStoriesPreviewView.getClosestPosition());
        goto L17
    }
}
