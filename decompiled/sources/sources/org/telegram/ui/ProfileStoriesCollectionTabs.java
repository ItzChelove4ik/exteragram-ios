package org.telegram.ui;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.text.SpannableStringBuilder;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BlurredFrameLayout;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.ViewPagerFixed;
import org.telegram.ui.Stories.StoriesController;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes6.dex */
public abstract class ProfileStoriesCollectionTabs extends BlurredFrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private final Adapter adapter;
    private final Rect clipRect;
    private final StoriesController.StoriesCollections collections;
    int initialAlbumId;
    private boolean reorderingCollections;
    private final Runnable sendCollectionsOrder;
    public final ViewPagerFixed.TabsView tabsView;
    private final ViewPagerFixed viewPager;
    private ValueAnimator visibilityAnimator;
    private float visibilityFactor;
    private boolean visibilityValue;

    /* renamed from: org.telegram.ui.ProfileStoriesCollectionTabs$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewPagerFixed {
        final /* synthetic */ ProfileStoriesCollectionTabs this$0;
        final /* synthetic */ Delegate val$delegate;

        public AnonymousClass1(ProfileStoriesCollectionTabs r1, Context r2, Delegate r3) {
            this.this$0 = r1;
            this.val$delegate = r3;
            super(r2);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabAnimationUpdate(boolean r1) {
            super.onTabAnimationUpdate(r1);
            Delegate r12 = this.val$delegate;
            if (r12 == null) goto L6;
            r12.onTabAlbumAnimationUpdate(this.this$0.tabsView.getAnimatingIndicatorProgress());
            return;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabPageSelected(int r2, boolean r3) {
            Delegate r0 = this.val$delegate;
            if (r0 == null) goto L6;
            r0.onTabAlbumSelected(ProfileStoriesCollectionTabs.m18338$$Nest$mgetAlbumIdByPosition(this.this$0, r2), r3);
            return;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabScrollEnd(int r2) {
            super.onTabScrollEnd(r2);
            Delegate r0 = this.val$delegate;
            if (r0 == null) goto L6;
            r0.onTabAlbumScrollEnd(ProfileStoriesCollectionTabs.m18338$$Nest$mgetAlbumIdByPosition(this.this$0, r2));
            return;
        }
    }

    public class Adapter extends ViewPagerFixed.Adapter {
        private boolean canCreateNewAlbum;
        final /* synthetic */ ProfileStoriesCollectionTabs this$0;

        /* renamed from: -$$Nest$fputcanCreateNewAlbum, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18339$$Nest$fputcanCreateNewAlbum(Adapter r0, boolean r1) {
            r0.canCreateNewAlbum = r1;
        }

        private Adapter(ProfileStoriesCollectionTabs r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(ProfileStoriesCollectionTabs r1, ProfileStoriesCollectionTabsIA r2) {
            this(r1);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void applyReorder(ArrayList<Integer> r7) {
            ArrayList<Integer> r0 = new ArrayList();
            int r1 = r7.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L11;
            Integer r3 = r7.get(r2);
            r2 = r2 + 1;
            Integer r32 = r3;
            int r4 = r32.intValue();
            if (r4 == (-1)) goto L3;
            if (r4 == (-2)) goto L3;
            if (r4 == 0) goto L3;
            r0.add(r32);
            goto L3
        L11:
            int r72 = getItemId(this.this$0.tabsView.getCurrentPosition());
            ProfileStoriesCollectionTabs.m18336$$Nest$fgetcollections(this.this$0).reorderStep(r0);
            if (r72 < 0) goto L14;
            int r73 = getItemPosition(r72);
            this.this$0.tabsView.selectTab(r73, r73, 0.0f);
        L14:
            AndroidUtilities.cancelRunOnUIThread(ProfileStoriesCollectionTabs.m18337$$Nest$fgetsendCollectionsOrder(this.this$0));
            AndroidUtilities.runOnUIThread(ProfileStoriesCollectionTabs.m18337$$Nest$fgetsendCollectionsOrder(this.this$0), 1000);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void bindView(View r1, int r2, int r3) {
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public boolean canReorder(int r4) {
            if (r4 != 0) goto L6;
            return false;
        L6:
            if (this.canCreateNewAlbum == true) goto L8;
        L10:
            return true;
        L8:
            if (r4 != (getItemCount() - 1)) goto L10;
            return false;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public View createView(int r2) {
            if (r2 != (-1)) goto L7;
            return null;
        L7:
            return new View(this.this$0.getContext());
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            return (ProfileStoriesCollectionTabs.m18336$$Nest$fgetcollections(this.this$0).collections.size() + 1) + (this.canCreateNewAlbum ? 1 : 0);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemId(int r2) {
            if (r2 != 0) goto L6;
            return 0;
        L6:
            if (this.canCreateNewAlbum == false) goto L12;
            if (r2 != (getItemCount() - 1)) goto L12;
            return -1;
        L12:
            return ProfileStoriesCollectionTabs.m18336$$Nest$fgetcollections(this.this$0).collections.get(r2 - 1).album_id;
        }

        public int getItemPosition(int r1) {
            if (r1 != 0) goto L5;
            return 0;
        L5:
            int r0 = ProfileStoriesCollectionTabs.m18336$$Nest$fgetcollections(this.this$0).indexOf(r1);
            if (r0 != (-1)) goto L9;
            return -1;
        L9:
            return r0 + 1;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public CharSequence getItemTitle(int r4) {
            if (r4 != 0) goto L6;
            return LocaleController.getString(R.string.StoriesAlbumNameAllStories);
        L6:
            if (this.canCreateNewAlbum == false) goto L12;
            if (r4 != (getItemCount() - 1)) goto L12;
            SpannableStringBuilder r3 = new SpannableStringBuilder("+ ");
            r3.append(LocaleController.getString(R.string.StoriesAlbumAddAlbum));
            ColoredImageSpan r42 = new ColoredImageSpan(R.drawable.poll_add_plus);
            r42.spaceScaleX = 0.8f;
            r3.setSpan(r42, 0, 1, 33);
            return r3;
        L12:
            return ProfileStoriesCollectionTabs.m18336$$Nest$fgetcollections(this.this$0).collections.get(r4 - 1).title;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemViewType(int r2) {
            if (this.canCreateNewAlbum == true) goto L5;
        L8:
            return r2;
        L5:
            if (r2 != (getItemCount() - 1)) goto L8;
            return -1;
        }
    }

    public interface Delegate {
        void onTabAlbumAnimationUpdate(float r1);

        void onTabAlbumCreateCollection();

        void onTabAlbumLongClick(View r1, int r2);

        void onTabAlbumScrollEnd(int r1);

        void onTabAlbumSelected(int r1, boolean r2);
    }

    /* renamed from: $r8$lambda$9sGG_2U8Bx59-U4qWVQH-owuZvw, reason: not valid java name */
    public static /* synthetic */ void m18334$r8$lambda$9sGG_2U8Bx59U4qWVQHowuZvw(BaseFragment r1) {
        ((ProfileActivity) r1).scrollToSharedMedia(true);
    }

    public static /* synthetic */ void $r8$lambda$Hp9mY48pPcnDx569UBI7R4vNNWg(ProfileStoriesCollectionTabs r0, int r1) {
        r0.lambda$didReceivedNotification$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$P_DnKGFA1FR4zcdZuyW3QrgNsVQ(ProfileStoriesCollectionTabs r0, ValueAnimator r1) {
        r0.lambda$setVisibility$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$Qak6SGhq6gGGpWlxgsaoG2m6O8A(ProfileStoriesCollectionTabs r0, int r1) {
        r0.lambda$setInitialTabId$2(r1);
    }

    /* renamed from: $r8$lambda$cx5M1qmKxMe8ESHdte-_VIQLKjE, reason: not valid java name */
    public static /* synthetic */ Boolean m18335$r8$lambda$cx5M1qmKxMe8ESHdte_VIQLKjE(ProfileStoriesCollectionTabs r0, Delegate r1, Integer r2, View r3) {
        return r0.lambda$new$1(r1, r2, r3);
    }

    public static /* synthetic */ Boolean $r8$lambda$vZ9vwPLR35Ibrt68x7OqxItTeQc(ProfileStoriesCollectionTabs r0, Delegate r1, Integer r2, Integer r3) {
        return r0.lambda$new$0(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetcollections, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesController.StoriesCollections m18336$$Nest$fgetcollections(ProfileStoriesCollectionTabs r0) {
        return r0.collections;
    }

    /* renamed from: -$$Nest$fgetsendCollectionsOrder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m18337$$Nest$fgetsendCollectionsOrder(ProfileStoriesCollectionTabs r0) {
        return r0.sendCollectionsOrder;
    }

    /* renamed from: -$$Nest$mgetAlbumIdByPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18338$$Nest$mgetAlbumIdByPosition(ProfileStoriesCollectionTabs r0, int r1) {
        return r0.getAlbumIdByPosition(r1);
    }

    public ProfileStoriesCollectionTabs(Context r8, SizeNotifierFrameLayout r9, StoriesController.StoriesCollections r10, Delegate r11) {
        super(r8, r9);
        this.clipRect = new Rect();
        this.collections = r10;
        Objects.requireNonNull(r10);
        this.sendCollectionsOrder = new ProfileStoriesCollectionTabs$$ExternalSyntheticLambda3(r10);
        AnonymousClass1 r92 = new AnonymousClass1(this, r8, r11);
        this.viewPager = r92;
        r92.setAllowDisallowInterceptTouch(true);
        Adapter r0 = new Adapter(this, null);
        this.adapter = r0;
        Adapter.m18339$$Nest$fputcanCreateNewAlbum(r0, r10.canCreateNewAlbum());
        r92.setAdapter(r0);
        r92.setTranslationY(AndroidUtilities.dp(42.0f));
        ViewPagerFixed.TabsView r1 = r92.createTabsView(true, 10);
        this.tabsView = r1;
        r1.setColors(Theme.key_profile_tabSelectedLine, Theme.key_windowBackgroundWhiteBlackText, Theme.key_profile_tabText, Theme.key_profile_tabSelector, Theme.key_actionBarDefault);
        r1.tabMarginDp = 12;
        r1.setPreTabClick(new ProfileStoriesCollectionTabs$$ExternalSyntheticLambda4(this, r11));
        r1.setOnTabLongClick(new ProfileStoriesCollectionTabs$$ExternalSyntheticLambda5(this, r11));
        addView(r1, LayoutHelper.createFrame(-1, 42, 48));
        setVisibility(!r10.collections.isEmpty(), false, true);
    }

    private void checkUi_clipRect() {
        this.clipRect.set(0, 0, getMeasuredWidth(), (int) getVisualHeight());
        setClipBounds(this.clipRect);
    }

    private int getAlbumIdByPosition(int r1) {
        return this.tabsView.getPageIdByPosition(r1);
    }

    private /* synthetic */ void lambda$didReceivedNotification$3(int r1) {
        scrollToAlbumId(r1);
    }

    private /* synthetic */ Boolean lambda$new$0(Delegate r1, Integer r2, Integer r3) {
        if (this.reorderingCollections == false) goto L7;
        return Boolean.TRUE;
    L7:
        if (r2.intValue() != (-1)) goto L13;
        if (r1 == null) goto L11;
        r1.onTabAlbumCreateCollection();
    L11:
        return Boolean.TRUE;
    L13:
        return Boolean.FALSE;
    }

    private /* synthetic */ Boolean lambda$new$1(Delegate r3, Integer r4, View r5) {
        if (r4.intValue() == (-1)) goto L14;
        if (r4.intValue() == 0) goto L14;
        if (this.reorderingCollections == true) goto L14;
        if (r3 == null) goto L12;
        r3.onTabAlbumLongClick(r5, r4.intValue());
    L12:
        return Boolean.TRUE;
    L14:
        return Boolean.FALSE;
    }

    private /* synthetic */ void lambda$setInitialTabId$2(int r1) {
        scrollToAlbumId(r1);
    }

    private /* synthetic */ void lambda$setVisibility$5(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.visibilityFactor = r12;
        onVisibilityChange(r12);
    }

    private void setVisibility(boolean r2, boolean r3, boolean r4) {
        if (this.visibilityValue != r2) goto L6;
        if (r4 == true) goto L6;
        return;
    L6:
        this.visibilityValue = r2;
        setEnabled(r2);
        ValueAnimator r42 = this.visibilityAnimator;
        if (r42 == null) goto L9;
        r42.cancel();
        this.visibilityAnimator = null;
    L9:
        float r43 = 0.0f;
        if (r3 == true) goto L15;
        if (r2 == false) goto L13;
        r43 = 1.0f;
    L13:
        this.visibilityFactor = r43;
        onVisibilityChange(r43);
        return;
    L15:
        float r32 = this.visibilityFactor;
        if (r2 == false) goto L18;
        r43 = 1.0f;
    L18:
        ValueAnimator r22 = ValueAnimator.ofFloat(new float[]{r32, r43});
        this.visibilityAnimator = r22;
        r22.setDuration(480);
        this.visibilityAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.visibilityAnimator.addUpdateListener(new ProfileStoriesCollectionTabs$$ExternalSyntheticLambda6(this));
        this.visibilityAnimator.start();
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 == NotificationCenter.storyAlbumsCollectionsUpdate) goto L5;
        return;
    L5:
        if (((Long) r5[0]).longValue() != this.collections.dialogId) goto L29;
        ViewPagerFixed.TabsView r42 = this.tabsView;
        if (r42 == null) goto L10;
        int r43 = r42.getCurrentTabId();
    L11:
        Adapter.m18339$$Nest$fputcanCreateNewAlbum(this.adapter, this.collections.canCreateNewAlbum());
        this.viewPager.fillTabs(true);
        setVisibility(!this.collections.collections.isEmpty(), true, false);
        int r52 = this.initialAlbumId;
        if (r52 <= 0) goto L18;
        if (this.adapter.getItemPosition(r52) == (-1)) goto L25;
        AndroidUtilities.runOnUIThread(new ProfileStoriesCollectionTabs$$ExternalSyntheticLambda2(this, this.initialAlbumId), 500);
        this.initialAlbumId = 0;
        return;
    L25:
        return;
    L18:
        if (this.tabsView == null) goto L26;
        if (r43 > 0) goto L21;
        return;
    L21:
        if (this.collections.findById(r43) != null) goto L28;
        this.tabsView.scrollToTab(0, 0);
        return;
    L28:
        return;
    L26:
        return;
    L10:
        r43 = 0;
        goto L11
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r2) {
        if (this.visibilityValue == true) goto L5;
        return false;
    L5:
        if (super.dispatchTouchEvent(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public int getCurrentAlbumId() {
        return this.adapter.getItemId(this.tabsView.getCurrentPosition());
    }

    public int getNextAlbumId(boolean r1) {
        return this.tabsView.getNextPageId(r1);
    }

    public float getVisibilityFactor() {
        return this.visibilityFactor;
    }

    public float getVisualHeight() {
        return getMeasuredHeight() * this.visibilityFactor;
    }

    public boolean isReordering() {
        return this.reorderingCollections;
    }

    @Override // org.telegram.ui.Components.BlurredFrameLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.collections.currentAccount).addObserver(this, NotificationCenter.storyAlbumsCollectionsUpdate);
    }

    @Override // org.telegram.ui.Components.BlurredFrameLayout, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.collections.currentAccount).removeObserver(this, NotificationCenter.storyAlbumsCollectionsUpdate);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        checkUi_clipRect();
    }

    public void onVisibilityChange(float r1) {
        checkUi_clipRect();
        invalidate();
    }

    public void resetReordering() {
        setReorderingAlbums(false);
    }

    public void scrollToAlbumId(int r2) {
        this.tabsView.scrollToTab(r2, this.adapter.getItemPosition(r2));
    }

    public void selectTabWithId(int r1, float r2) {
        this.tabsView.selectTabWithId(r1, r2);
    }

    public void setInitialTabId(int r3) {
        if (this.adapter.getItemPosition(r3) == (-1)) goto L6;
        AndroidUtilities.runOnUIThread(new ProfileStoriesCollectionTabs$$ExternalSyntheticLambda0(this, r3), 500);
        return;
    L6:
        this.initialAlbumId = r3;
    }

    public void setReorderingAlbums(boolean r4) {
        if (this.reorderingCollections == r4) goto L14;
        this.reorderingCollections = r4;
        this.tabsView.setReordering(r4);
        updatedReordering(isReordering());
        if (r4 == false) goto L10;
        BaseFragment r1 = LaunchActivity.getSafeLastFragment();
        if ((r1 instanceof ProfileActivity) == false) goto L10;
        ((ProfileActivity) r1).scrollToSharedMedia(false);
        AndroidUtilities.runOnUIThread(new ProfileStoriesCollectionTabs$$ExternalSyntheticLambda1(r1));
    L10:
        if (r4 == true) goto L13;
        AndroidUtilities.cancelRunOnUIThread(this.sendCollectionsOrder);
        this.collections.reorderComplete(false);
        int r42 = this.adapter.getItemId(this.tabsView.getCurrentPosition());
        this.viewPager.fillTabs(true);
        int r43 = this.adapter.getItemPosition(r42);
        this.tabsView.selectTab(r43, r43, 0.0f);
        return;
    L13:
        return;
    }

    public abstract void updatedReordering(boolean r1);
}
