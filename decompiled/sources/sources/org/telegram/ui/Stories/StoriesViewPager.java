package org.telegram.ui.Stories;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.PeerStoriesView;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.StoryViewer;

/* loaded from: classes7.dex */
public abstract class StoriesViewPager extends ViewPager {
    int currentAccount;
    public int currentState;
    ArrayList<ArrayList<Integer>> days;
    long daysDialogId;
    PeerStoriesView.Delegate delegate;
    ArrayList<Long> dialogs;
    public boolean dissallowInterceptCalled;
    Runnable doOnNextIdle;
    int keyboardHeight;
    float lastProgressToDismiss;
    Runnable lockTouchRunnable;
    PagerAdapter pagerAdapter;
    float progress;
    PeerStoriesView.SharedResources resources;
    int selectedPosition;
    private int selectedPositionInPage;
    StoryViewer storyViewer;
    int toPosition;
    boolean touchEnabled;
    private boolean touchLocked;
    boolean updateDelegate;
    private int updateVisibleItemPosition;

    /* renamed from: org.telegram.ui.Stories.StoriesViewPager$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ StoriesViewPager this$0;

        public AnonymousClass1(StoriesViewPager r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            StoriesViewPager.m19736$$Nest$fputtouchLocked(this.this$0, false);
        }
    }

    /* renamed from: org.telegram.ui.Stories.StoriesViewPager$2, reason: invalid class name */
    public class AnonymousClass2 extends PagerAdapter {
        private final ArrayList<PeerStoriesView> cachedViews;
        final /* synthetic */ StoriesViewPager this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;
        final /* synthetic */ StoryViewer val$storyViewer;

        /* renamed from: org.telegram.ui.Stories.StoriesViewPager$2$1, reason: invalid class name */
        public class AnonymousClass1 extends HwPeerStoriesView {
            final /* synthetic */ AnonymousClass2 this$1;

            public AnonymousClass1(AnonymousClass2 r1, Context r2, StoryViewer r3, PeerStoriesView.SharedResources r4, Theme.ResourcesProvider r5) {
                this.this$1 = r1;
                super(r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Stories.PeerStoriesView
            public boolean isSelectedPeer() {
                if (getParent() != null) goto L6;
                return false;
            L6:
                if (((Integer) ((View) getParent()).getTag()).intValue() != this.this$1.this$0.getCurrentItem()) goto L9;
                return true;
            L9:
                return false;
            }
        }

        public AnonymousClass2(StoriesViewPager r1, Context r2, StoryViewer r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            this.val$context = r2;
            this.val$storyViewer = r3;
            this.val$resourcesProvider = r4;
            this.cachedViews = new ArrayList();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup r1, int r2, Object r3) {
            FrameLayout r32 = (FrameLayout) r3;
            r1.removeView(r32);
            PeerStoriesView r12 = (PeerStoriesView) r32.getChildAt(0);
            AndroidUtilities.removeFromParent(r12);
            this.cachedViews.add(r12);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            StoriesViewPager r1 = this.this$0;
            ArrayList<ArrayList<Integer>> r0 = r1.days;
            if (r0 == null) goto L7;
            return r0.size();
        L7:
            return r1.dialogs.size();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup r10, int r11) {
            PageLayout r0 = new PageLayout(this.this$0, this.val$context);
            if (this.cachedViews.isEmpty() == true) goto L5;
            PeerStoriesView r1 = this.cachedViews.remove(0);
            r1.reset();
            AnonymousClass2 r4 = this;
        L6:
            r0.peerStoryView = r1;
            r1.setAccount(r4.this$0.currentAccount);
            r1.setDelegate(r4.this$0.delegate);
            r1.setLongpressed(r4.val$storyViewer.isLongpressed);
            r0.setTag(Integer.valueOf(r11));
            StoriesViewPager r9 = r4.this$0;
            ArrayList<ArrayList<Integer>> r3 = r9.days;
            if (r3 != null) goto L9;
            r0.day = null;
            r0.dialogId = r9.dialogs.get(r11).longValue();
        L23:
            r0.addView(r1);
            r1.requestLayout();
            r10.addView(r0);
            return r0;
        L9:
            if (r4.val$storyViewer.reversed == false) goto L11;
            r11 = (r3.size() - 1) - r11;
        L11:
            ArrayList<Integer> r92 = r3.get(r11);
            r0.day = r92;
            StoriesController.StoriesList r112 = r4.val$storyViewer.storiesList;
            if ((r112 instanceof StoriesController.SearchStoriesList) == false) goto L14;
        L17:
            MessageObject r93 = r112.findMessageObject(r92.get(0).intValue());
            if (r93 != null) goto L20;
            long r2 = r4.this$0.daysDialogId;
        L21:
            r0.dialogId = r2;
            goto L23
        L20:
            r2 = r93.getDialogId();
            goto L21
        L14:
            if ((r112 instanceof StoriesController.StoryRepostsList) == true) goto L17;
            r0.dialogId = r4.this$0.daysDialogId;
            goto L23
        L5:
            r4 = this;
            r1 = new AnonymousClass1(r4, this.val$context, this.val$storyViewer, this.this$0.resources, this.val$resourcesProvider);
            goto L6
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View r1, Object r2) {
            if (r1 != r2) goto L5;
            return true;
        L5:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Stories.StoriesViewPager$3, reason: invalid class name */
    public class AnonymousClass3 implements ViewPager.OnPageChangeListener {
        final /* synthetic */ StoriesViewPager this$0;
        final /* synthetic */ StoryViewer val$storyViewer;

        public AnonymousClass3(StoriesViewPager r1, StoryViewer r2) {
            this.this$0 = r1;
            this.val$storyViewer = r2;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int r3) {
            PeerStoriesView.Delegate r0 = this.this$0.delegate;
            if (r3 == 0) goto L5;
            boolean r1 = true;
        L6:
            r0.setAllowTouchesByViewPager(r1);
            Runnable r02 = this.this$0.doOnNextIdle;
            if (r02 == null) goto L10;
            if (r3 != 0) goto L10;
            r02.run();
            this.this$0.doOnNextIdle = null;
        L10:
            StoriesViewPager r2 = this.this$0;
            r2.currentState = r3;
            r2.onStateChanged();
            return;
        L5:
            r1 = false;
            goto L6
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int r3, float r4, int r5) {
            StoriesViewPager r0 = this.this$0;
            r0.selectedPosition = r3;
            if (r5 <= 0) goto L5;
            int r32 = r3 + 1;
        L6:
            r0.toPosition = r32;
            r0.progress = r4;
            long r33 = UserConfig.getInstance(r0.currentAccount).clientUserId;
            StoriesViewPager r52 = this.this$0;
            int r02 = r52.selectedPosition;
            if (r02 >= 0) goto L9;
        L19:
            StoriesViewPager r53 = this.this$0;
            int r03 = r53.toPosition;
            if (r03 >= 0) goto L22;
        L32:
            this.this$0.delegate.setHideEnterViewProgress(0.0f);
            return;
        L22:
            if (r53.days != null) goto L29;
            if (r03 >= r53.dialogs.size()) goto L32;
            StoriesViewPager r54 = this.this$0;
            if (r54.dialogs.get(r54.toPosition).longValue() != r33) goto L32;
        L30:
            StoriesViewPager r2 = this.this$0;
            r2.delegate.setHideEnterViewProgress(r2.progress);
            return;
        L29:
            if (r53.daysDialogId != r33) goto L32;
        L9:
            if (r52.days != null) goto L16;
            if (r02 >= r52.dialogs.size()) goto L19;
            StoriesViewPager r55 = this.this$0;
            if (r55.dialogs.get(r55.selectedPosition).longValue() != r33) goto L19;
        L17:
            StoriesViewPager r22 = this.this$0;
            r22.delegate.setHideEnterViewProgress(1.0f - r22.progress);
            return;
        L16:
            if (r52.daysDialogId != r33) goto L19;
        L5:
            r32 = r3 - 1;
            goto L6
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int r5) {
            PeerStoriesView r0 = this.this$0.getCurrentPeerView();
            if (r0 == null) goto L17;
            this.this$0.delegate.onPeerSelected(r0.getCurrentPeer(), r0.getSelectedPosition());
            StoriesViewPager.m19737$$Nest$mupdateActiveStory(this.this$0);
            StoryViewer.PlaceProvider r02 = this.val$storyViewer.placeProvider;
            if (r02 != null) goto L8;
            return;
        L8:
            if (r5 >= 3) goto L12;
            r02.loadNext(false);
            return;
        L12:
            if (r5 <= (this.this$0.pagerAdapter.getCount() - 4)) goto L16;
            this.val$storyViewer.placeProvider.loadNext(true);
            return;
        L16:
            return;
        }
    }

    public class PageLayout extends FrameLayout {
        ArrayList<Integer> day;
        long dialogId;
        boolean isVisible;
        public PeerStoriesView peerStoryView;
        final /* synthetic */ StoriesViewPager this$0;

        public PageLayout(StoriesViewPager r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r2) {
            if (this.isVisible == true) goto L5;
            return;
        L5:
            super.dispatchDraw(r2);
        }

        public void setVisible(boolean r2) {
            if (this.isVisible == r2) goto L6;
            this.isVisible = r2;
            invalidate();
            this.peerStoryView.setIsVisible(r2);
            this.this$0.checkAllowScreenshots();
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$9CHsGpUu_NGtik9WFwY0kDWgpxw(PageLayout r3) {
        ArrayList<Integer> r0 = r3.day;
        if (r0 == null) goto L5;
        r3.peerStoryView.day = r0;
    L5:
        r3.peerStoryView.preloadMainImage(r3.dialogId);
    }

    public static /* synthetic */ void $r8$lambda$qZvaRgm6O1_Q_u0m_pqATNeXetg(StoriesViewPager r0, View r1, float r2) {
        r0.lambda$new$1(r1, r2);
    }

    /* renamed from: -$$Nest$fputtouchLocked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19736$$Nest$fputtouchLocked(StoriesViewPager r0, boolean r1) {
        r0.touchLocked = r1;
    }

    /* renamed from: -$$Nest$mupdateActiveStory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19737$$Nest$mupdateActiveStory(StoriesViewPager r0) {
        r0.updateActiveStory();
    }

    public StoriesViewPager(int r2, Context r3, StoryViewer r4, Theme.ResourcesProvider r5) {
        super(r3);
        this.dialogs = new ArrayList();
        this.touchEnabled = true;
        this.lockTouchRunnable = new AnonymousClass1(this);
        this.updateVisibleItemPosition = -1;
        this.currentAccount = r2;
        this.resources = new PeerStoriesView.SharedResources(r3);
        this.storyViewer = r4;
        AnonymousClass2 r22 = new AnonymousClass2(this, r3, r4, r5);
        this.pagerAdapter = r22;
        setAdapter(r22);
        setPageTransformer(false, new StoriesViewPager$$ExternalSyntheticLambda0(this));
        setOffscreenPageLimit(0);
        addOnPageChangeListener(new AnonymousClass3(this, r4));
        setOverScrollMode(2);
    }

    public static boolean eq(ArrayList<Integer> r5, ArrayList<Integer> r6) {
        if (r5 != null) goto L7;
        if (r6 != null) goto L7;
        return true;
    L7:
        if (r5 == null) goto L21;
        if (r6 == null) goto L21;
        if (r5.size() == r6.size()) goto L13;
        return false;
    L13:
        int r2 = 0;
    L15:
        if (r2 >= r5.size()) goto L20;
        if (r5.get(r2) != r6.get(r2)) goto L18;
        r2 = r2 + 1;
        goto L15
    L18:
        return false;
    L20:
        return true;
    L21:
        return false;
    }

    private static boolean eqA(ArrayList<ArrayList<Integer>> r5, ArrayList<ArrayList<Integer>> r6) {
        if (r5 != null) goto L7;
        if (r6 != null) goto L7;
        return true;
    L7:
        if (r5 == null) goto L21;
        if (r6 == null) goto L21;
        if (r5.size() == r6.size()) goto L13;
        return false;
    L13:
        int r2 = 0;
    L15:
        if (r2 >= r5.size()) goto L20;
        if (eq(r5.get(r2), r6.get(r2)) == false) goto L18;
        r2 = r2 + 1;
        goto L15
    L18:
        return false;
    L20:
        return true;
    L21:
        return false;
    }

    private static boolean isPrefix(ArrayList<ArrayList<Integer>> r4, ArrayList<ArrayList<Integer>> r5) {
        if (r4 == null) goto L18;
        if (r5 == null) goto L18;
        if (r5.size() > r4.size()) goto L9;
        return false;
    L9:
        int r1 = 0;
    L11:
        if (r1 >= r4.size()) goto L16;
        if (eq(r4.get(r1), r5.get(r1)) == false) goto L14;
        r1 = r1 + 1;
        goto L11
    L14:
        return false;
    L16:
        return true;
    L18:
        return false;
    }

    private /* synthetic */ void lambda$new$1(View r6, float r7) {
        PageLayout r0 = (PageLayout) r6;
        if (Math.abs(r7) < 1.0f) goto L7;
        r0.setVisible(false);
        AndroidUtilities.runOnUIThread(new StoriesViewPager$$ExternalSyntheticLambda1(r0), 16);
        return;
    L7:
        if (r0.isVisible == true) goto L12;
        r0.setVisible(true);
        ArrayList<ArrayList<Integer>> r5 = this.days;
        PeerStoriesView r1 = r0.peerStoryView;
        if (r5 == null) goto L11;
        r1.setDay(r0.dialogId, r0.day, -1);
        goto L12
    L11:
        r1.setDialogId(r0.dialogId, -1);
    L12:
        r0.peerStoryView.setOffset(r7);
        r6.setCameraDistance(r6.getWidth() * 15);
        float r52 = 0.0f;
        if (r7 >= 0.0f) goto L15;
        r52 = r6.getWidth();
    L15:
        r6.setPivotX(r52);
        r6.setPivotY(r6.getHeight() * 0.5f);
        r6.setRotationY(r7 * 90.0f);
    }

    private void updateActiveStory() {
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L12;
        PeerStoriesView r2 = (PeerStoriesView) ((FrameLayout) getChildAt(r1)).getChildAt(0);
        if (((Integer) getChildAt(r1).getTag()).intValue() == getCurrentItem()) goto L8;
    L10:
        boolean r3 = false;
    L11:
        r2.setActive(r3);
        r1 = r1 + 1;
        goto L4
    L8:
        if (r2.editOpened == true) goto L10;
        r3 = true;
        goto L11
    }

    public boolean canScroll(float r6) {
        int r0 = this.selectedPosition;
        if (r0 != 0) goto L10;
        if (this.progress != 0.0f) goto L10;
        if (r6 >= 0.0f) goto L10;
        return false;
    L10:
        if (r0 == (getAdapter().getCount() - 1)) goto L12;
    L16:
        return true;
    L12:
        if (this.progress != 0.0f) goto L16;
        if (r6 <= 0.0f) goto L16;
        return false;
    }

    public void checkAllowScreenshots() {
        boolean r0 = false;
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L11;
        PageLayout r2 = (PageLayout) getChildAt(r1);
        if (r2.isVisible == false) goto L10;
        if (r2.peerStoryView.currentStory.allowScreenshots() == true) goto L10;
    L12:
        this.storyViewer.allowScreenshots(r0);
        return;
    L10:
        r1 = r1 + 1;
        goto L4
    L11:
        r0 = true;
        goto L12
    }

    public void checkPageVisibility() {
        if (this.updateVisibleItemPosition < 0) goto L18;
        int r0 = 0;
    L6:
        if (r0 >= getChildCount()) goto L25;
        if (((Integer) getChildAt(r0).getTag()).intValue() != getCurrentItem()) goto L17;
        if (getCurrentItem() != this.updateVisibleItemPosition) goto L17;
        PageLayout r1 = (PageLayout) getChildAt(r0);
        if (r1.isVisible == true) goto L17;
        this.updateVisibleItemPosition = -1;
        r1.setVisible(true);
        ArrayList<ArrayList<Integer>> r2 = this.days;
        PeerStoriesView r3 = r1.peerStoryView;
        if (r2 == null) goto L16;
        r3.setDay(r1.dialogId, r1.day, this.selectedPositionInPage);
        goto L17
    L16:
        r3.setDialogId(r1.dialogId, this.selectedPositionInPage);
    L17:
        r0 = r0 + 1;
        goto L6
    L25:
        return;
    }

    public void enableTouch(boolean r1) {
        this.touchEnabled = r1;
    }

    public long getCurrentDialogId() {
        if (this.days == null) goto L7;
        return this.daysDialogId;
    L7:
        if (getCurrentItem() < this.dialogs.size()) goto L9;
        return 0;
    L9:
        return this.dialogs.get(getCurrentItem()).longValue();
    }

    public PeerStoriesView getCurrentPeerView() {
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L10;
        if (((Integer) getChildAt(r1).getTag()).intValue() == getCurrentItem()) goto L8;
        r1 = r1 + 1;
        goto L4
    L8:
        return (PeerStoriesView) ((FrameLayout) getChildAt(r1)).getChildAt(0);
    L10:
        return null;
    }

    public ArrayList<Long> getDialogIds() {
        return this.dialogs;
    }

    public void lockTouchEvent(long r10) {
        this.touchLocked = true;
        onTouchEvent(MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0));
        AndroidUtilities.cancelRunOnUIThread(this.lockTouchRunnable);
        AndroidUtilities.runOnUIThread(this.lockTouchRunnable, r10);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r3) {
        if (this.touchEnabled == true) goto L5;
    L11:
        return false;
    L5:
        if (this.touchLocked == true) goto L11;
        return super.onInterceptTouchEvent(r3);
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L11
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        if (this.updateDelegate == false) goto L7;
        this.updateDelegate = false;
        PeerStoriesView r12 = getCurrentPeerView();
        if (r12 == null) goto L7;
        this.delegate.onPeerSelected(r12.getCurrentPeer(), r12.getSelectedPosition());
    L7:
        checkPageVisibility();
        updateActiveStory();
    }

    public void onNextIdle(Runnable r1) {
        this.doOnNextIdle = r1;
    }

    public abstract void onStateChanged();

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent r2) {
        if (this.touchEnabled == false) goto L10;
        if (this.touchLocked == true) goto L10;
        return super.onTouchEvent(r2);
    L10:
        if (this.touchLocked == true) goto L12;
        return false;
    L12:
        if (r2.getAction() != 0) goto L14;
        return true;
    L14:
        if (r2.getAction() != 2) goto L20;
        return true;
    L20:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean r2) {
        if (r2 == false) goto L4;
        this.dissallowInterceptCalled = true;
    L4:
        super.requestDisallowInterceptTouchEvent(r2);
    }

    public void setCurrentDate(long r5, int r7) {
        int r1 = 0;
    L4:
        if (r1 >= this.days.size()) goto L40;
        if (r5 == StoriesController.StoriesList.day(this.storyViewer.storiesList.findMessageObject(this.days.get(r1).get(0).intValue()))) goto L8;
        r1 = r1 + 1;
        goto L4
    L8:
        if (this.storyViewer.reversed == false) goto L10;
        int r52 = (this.days.size() - 1) - r1;
    L11:
        int r2 = 0;
    L13:
        if (r2 >= this.days.get(r1).size()) goto L18;
        if (this.days.get(r1).get(r2).intValue() == r7) goto L20;
        r2 = r2 + 1;
    L20:
        if (getCurrentPeerView() != null) goto L22;
    L26:
        setCurrentItem(r52, false);
        PeerStoriesView r53 = getCurrentPeerView();
        if (r53 == null) goto L35;
        PageLayout r54 = (PageLayout) r53.getParent();
        r54.setVisible(true);
        ArrayList<ArrayList<Integer>> r4 = this.days;
        PeerStoriesView r6 = r54.peerStoryView;
        if (r4 == null) goto L32;
        r6.setDay(r54.dialogId, r54.day, r2);
        return;
    L32:
        r6.setDialogId(r54.dialogId, r2);
        return;
    L35:
        return;
    L22:
        if (getCurrentItem() != r52) goto L26;
        getCurrentPeerView().selectPosition(r2);
        return;
    L18:
        r2 = 0;
        goto L20
    L10:
        r52 = r1;
        goto L11
    }

    public void setDays(long r4, ArrayList<ArrayList<Integer>> r6, int r7) {
        if (this.daysDialogId == r4) goto L5;
    L9:
        int r1 = 0;
        if (this.daysDialogId == r4) goto L12;
    L16:
        boolean r0 = false;
    L17:
        this.daysDialogId = r4;
        this.days = r6;
        this.currentAccount = r7;
        if (r0 == false) goto L23;
        PagerAdapter r42 = this.pagerAdapter;
        if (r42 == null) goto L23;
        r42.notifyDataSetChanged();
        this.updateDelegate = true;
        return;
    L23:
        setAdapter(null);
        setAdapter(this.pagerAdapter);
    L25:
        if (r1 >= r6.size()) goto L31;
        if (r6.get(r1).contains(Integer.valueOf(this.storyViewer.dayStoryId)) == true) goto L31;
        r1 = r1 + 1;
    L31:
        if (this.storyViewer.reversed == false) goto L33;
        r1 = (r6.size() - 1) - r1;
    L33:
        setCurrentItem(r1);
        this.updateDelegate = true;
        return;
    L12:
        if (this.currentAccount != r7) goto L16;
        if (isPrefix(this.days, r6) == false) goto L16;
        r0 = true;
        goto L17
    L5:
        if (eqA(this.days, r6) == false) goto L9;
        if (this.currentAccount != r7) goto L9;
    }

    public void setDelegate(PeerStoriesView.Delegate r1) {
        this.delegate = r1;
    }

    public void setHorizontalProgressToDismiss(float r3) {
        if (Math.abs(r3) <= 1.0f) goto L5;
        return;
    L5:
        if (this.lastProgressToDismiss == r3) goto L13;
        this.lastProgressToDismiss = r3;
        setCameraDistance(getWidth() * 15);
        float r0 = 0.0f;
        if (r3 >= 0.0f) goto L10;
        r0 = getWidth();
    L10:
        setPivotX(r0);
        setPivotY(getHeight() * 0.5f);
        setRotationY(r3 * 90.0f);
        return;
    }

    public void setKeyboardHeight(int r2) {
        if (this.keyboardHeight == r2) goto L8;
        this.keyboardHeight = r2;
        PeerStoriesView r1 = getCurrentPeerView();
        if (r1 == null) goto L9;
        r1.requestLayout();
        return;
    L9:
        return;
    }

    public void setPaused(boolean r4) {
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L6;
        ((PeerStoriesView) ((FrameLayout) getChildAt(r1)).getChildAt(0)).setPaused(r4);
        r1 = r1 + 1;
        goto L4
    }

    public void setPeerIds(ArrayList<Long> r1, int r2, int r3) {
        this.dialogs = r1;
        this.currentAccount = r2;
        setAdapter(null);
        setAdapter(this.pagerAdapter);
        setCurrentItem(r3);
        this.updateDelegate = true;
    }

    public boolean switchToNext(boolean r4) {
        if (r4 == false) goto L12;
        int r1 = getCurrentItem();
        ArrayList r2 = this.days;
        if (r2 != null) goto L9;
        r2 = this.dialogs;
    L9:
        if (r1 >= (r2.size() - 1)) goto L12;
        setCurrentItem(getCurrentItem() + 1, !useSurfaceInViewPagerWorkAround());
        return true;
    L12:
        if (r4 == false) goto L14;
        return false;
    L14:
        if (getCurrentItem() <= 0) goto L19;
        setCurrentItem(getCurrentItem() - 1, !useSurfaceInViewPagerWorkAround());
        return true;
    L19:
        return false;
    }

    public boolean useSurfaceInViewPagerWorkAround() {
        if (this.storyViewer.USE_SURFACE_VIEW == true) goto L5;
        return false;
    L5:
        if (Build.VERSION.SDK_INT >= 33) goto L10;
        return true;
    L10:
        return false;
    }
}
