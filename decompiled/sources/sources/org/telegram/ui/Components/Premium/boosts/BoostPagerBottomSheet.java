package org.telegram.ui.Components.Premium.boosts;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.Premium.boosts.BoostViaGiftsBottomSheet;
import org.telegram.ui.Components.Premium.boosts.SelectorBottomSheet;
import org.telegram.ui.Components.ViewPagerFixed;
import org.telegram.ui.Stories.DarkThemeResourceProvider;

/* loaded from: classes7.dex */
public class BoostPagerBottomSheet extends BottomSheet {
    private static BoostPagerBottomSheet instance;
    private boolean isLandscapeOrientation;
    private BoostViaGiftsBottomSheet leftSheet;
    private final SelectorBottomSheet rightSheet;
    private final ViewPagerFixed viewPager;

    /* renamed from: org.telegram.ui.Components.Premium.boosts.BoostPagerBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewPagerFixed {
        private final Paint backgroundPaint;
        private boolean isKeyboardVisible;
        private boolean isScrolling;
        private final boolean isTablet;
        private final Path path;
        final /* synthetic */ BoostPagerBottomSheet this$0;
        final /* synthetic */ BoostViaGiftsBottomSheet val$leftSheet;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;
        final /* synthetic */ SelectorBottomSheet val$rightSheet;

        public AnonymousClass1(BoostPagerBottomSheet r1, Context r2, SelectorBottomSheet r3, Theme.ResourcesProvider r4, BoostViaGiftsBottomSheet r5) {
            this.this$0 = r1;
            this.val$rightSheet = r3;
            this.val$resourcesProvider = r4;
            this.val$leftSheet = r5;
            super(r2);
            this.path = new Path();
            this.backgroundPaint = new Paint(1);
            this.isTablet = AndroidUtilities.isTablet();
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public boolean canScroll(MotionEvent r1) {
            if (BoostPagerBottomSheet.m12077$$Nest$fgetviewPager(this.this$0).getCurrentPosition() != 1) goto L5;
            return true;
        L5:
            return false;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r7) {
            this.backgroundPaint.setColor(Theme.getColor(Theme.key_dialogBackground, this.val$resourcesProvider));
            if (this.isScrolling == false) goto L20;
            int r0 = this.val$leftSheet.getTop() + AndroidUtilities.dp(10.0f);
            int r1 = this.val$rightSheet.getTop();
            int r2 = Math.abs(r0 - r1);
            int r3 = BoostPagerBottomSheet.m12077$$Nest$fgetviewPager(this.this$0).getCurrentPosition();
            BoostPagerBottomSheet r4 = this.this$0;
            if (r3 != 0) goto L13;
            float r22 = r2 * BoostPagerBottomSheet.m12077$$Nest$fgetviewPager(r4).getPositionAnimated();
            if (r0 >= r1) goto L11;
            float r02 = r0;
        L9:
            float r03 = r02 + r22;
        L10:
            int r04 = (int) r03;
            float r12 = AndroidUtilities.dp(14.0f);
            RectF r23 = AndroidUtilities.rectTmp;
            r23.set(0.0f, r04, getWidth(), getHeight() + AndroidUtilities.dp(8.0f));
            r7.drawRoundRect(r23, r12, r12, this.backgroundPaint);
            r7.save();
            this.path.rewind();
            this.path.addRoundRect(r23, r12, r12, Path.Direction.CW);
            r7.clipPath(this.path);
            super.dispatchDraw(r7);
            r7.restore();
            return;
        L11:
            float r05 = r0;
        L12:
            r03 = r05 - r22;
            goto L10
        L13:
            r22 = r2 * (1.0f - BoostPagerBottomSheet.m12077$$Nest$fgetviewPager(r4).getPositionAnimated());
            if (r1 >= r0) goto L16;
            r02 = r1;
            goto L9
        L16:
            r05 = r1;
            goto L12
        L20:
            if (this.isTablet == false) goto L22;
        L23:
            r7.clipRect(0, 0, getMeasuredWidth(), getMeasuredHeight());
        L24:
            super.dispatchDraw(r7);
            return;
        L22:
            if (BoostPagerBottomSheet.m12076$$Nest$fgetisLandscapeOrientation(this.this$0) == false) goto L24;
            goto L23
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public float getAvailableTranslationX() {
            if (this.isTablet == true) goto L10;
            if (BoostPagerBottomSheet.m12076$$Nest$fgetisLandscapeOrientation(this.this$0) == true) goto L10;
            return super.getAvailableTranslationX();
        L10:
            return getMeasuredWidth();
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if (this.isKeyboardVisible == this.this$0.isKeyboardVisible()) goto L8;
            boolean r12 = this.this$0.isKeyboardVisible();
            this.isKeyboardVisible = r12;
            if (r12 == false) goto L9;
            this.val$rightSheet.scrollToTop(true);
            return;
        L9:
            return;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onScrollEnd() {
            this.isScrolling = false;
            BoostPagerBottomSheet.m12077$$Nest$fgetviewPager(this.this$0).invalidate();
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabAnimationUpdate(boolean r2) {
            float r22 = BoostPagerBottomSheet.m12077$$Nest$fgetviewPager(this.this$0).getPositionAnimated();
            if (r22 > 0.0f) goto L5;
        L9:
            this.isScrolling = false;
        L10:
            BoostPagerBottomSheet.m12077$$Nest$fgetviewPager(this.this$0).invalidate();
            return;
        L5:
            if (r22 >= 1.0f) goto L9;
            if (this.isScrolling == true) goto L10;
            this.isScrolling = true;
            BoostPagerBottomSheet.m12078$$Nest$mhideKeyboardIfVisible(this.this$0);
            goto L10
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.BoostPagerBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewPagerFixed.Adapter {
        final /* synthetic */ BoostPagerBottomSheet this$0;
        final /* synthetic */ BoostViaGiftsBottomSheet val$leftSheet;
        final /* synthetic */ SelectorBottomSheet val$rightSheet;

        public AnonymousClass2(BoostPagerBottomSheet r1, BoostViaGiftsBottomSheet r2, SelectorBottomSheet r3) {
            this.this$0 = r1;
            this.val$leftSheet = r2;
            this.val$rightSheet = r3;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void bindView(View r1, int r2, int r3) {
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public View createView(int r1) {
            if (r1 != 0) goto L6;
            return this.val$leftSheet.getContainerView();
        L6:
            return this.val$rightSheet.getContainerView();
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            return 2;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemViewType(int r1) {
            return r1;
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.BoostPagerBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 implements BoostViaGiftsBottomSheet.ActionListener {
        final /* synthetic */ BoostPagerBottomSheet this$0;
        final /* synthetic */ SelectorBottomSheet val$rightSheet;

        public AnonymousClass3(BoostPagerBottomSheet r1, SelectorBottomSheet r2) {
            this.this$0 = r1;
            this.val$rightSheet = r2;
        }

        @Override // org.telegram.ui.Components.Premium.boosts.BoostViaGiftsBottomSheet.ActionListener
        public void onAddChat(List<TLObject> r3) {
            this.val$rightSheet.prepare(r3, 2);
            BoostPagerBottomSheet.m12077$$Nest$fgetviewPager(this.this$0).scrollToPosition(1);
        }

        @Override // org.telegram.ui.Components.Premium.boosts.BoostViaGiftsBottomSheet.ActionListener
        public void onSelectCountries(List<TLObject> r3) {
            this.val$rightSheet.prepare(r3, 3);
            BoostPagerBottomSheet.m12077$$Nest$fgetviewPager(this.this$0).scrollToPosition(1);
        }

        @Override // org.telegram.ui.Components.Premium.boosts.BoostViaGiftsBottomSheet.ActionListener
        public void onSelectUser(List<TLObject> r3) {
            this.val$rightSheet.prepare(r3, 1);
            BoostPagerBottomSheet.m12077$$Nest$fgetviewPager(this.this$0).scrollToPosition(1);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.BoostPagerBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 implements SelectorBottomSheet.SelectedObjectsListener {
        final /* synthetic */ BoostPagerBottomSheet this$0;
        final /* synthetic */ BoostViaGiftsBottomSheet val$leftSheet;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass4(BoostPagerBottomSheet r1, BoostViaGiftsBottomSheet r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            this.val$leftSheet = r2;
            this.val$resourcesProvider = r3;
        }

        @Override // org.telegram.ui.Components.Premium.boosts.SelectorBottomSheet.SelectedObjectsListener
        public void onChatsSelected(List<TLRPC.Chat> r2, boolean r3) {
            BoostPagerBottomSheet.m12077$$Nest$fgetviewPager(this.this$0).scrollToPosition(0);
            this.val$leftSheet.onChatsSelected(r2, !this.this$0.isKeyboardVisible());
        }

        @Override // org.telegram.ui.Components.Premium.boosts.SelectorBottomSheet.SelectedObjectsListener
        public void onCountrySelected(List<TLRPC.TL_help_country> r3) {
            BoostPagerBottomSheet.m12077$$Nest$fgetviewPager(this.this$0).scrollToPosition(0);
            this.val$leftSheet.onCountrySelected(r3);
        }

        @Override // org.telegram.ui.Components.Premium.boosts.SelectorBottomSheet.SelectedObjectsListener
        public void onShowToast(String r2) {
            BulletinFactory.of(this.this$0.container, this.val$resourcesProvider).createSimpleBulletin(R.raw.chats_infotip, r2).show(true);
        }

        @Override // org.telegram.ui.Components.Premium.boosts.SelectorBottomSheet.SelectedObjectsListener
        public void onUsersSelected(List<TLRPC.User> r3) {
            BoostPagerBottomSheet.m12077$$Nest$fgetviewPager(this.this$0).scrollToPosition(0);
            this.val$leftSheet.onUsersSelected(r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.BoostPagerBottomSheet$5, reason: invalid class name */
    public class AnonymousClass5 implements Bulletin.Delegate {
        final /* synthetic */ BoostPagerBottomSheet this$0;

        public AnonymousClass5(BoostPagerBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getTopOffset(int r1) {
            return AndroidUtilities.statusBarHeight;
        }
    }

    /* renamed from: -$$Nest$fgetisLandscapeOrientation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12076$$Nest$fgetisLandscapeOrientation(BoostPagerBottomSheet r0) {
        return r0.isLandscapeOrientation;
    }

    /* renamed from: -$$Nest$fgetviewPager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPagerFixed m12077$$Nest$fgetviewPager(BoostPagerBottomSheet r0) {
        return r0.viewPager;
    }

    /* renamed from: -$$Nest$mhideKeyboardIfVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12078$$Nest$mhideKeyboardIfVisible(BoostPagerBottomSheet r0) {
        r0.hideKeyboardIfVisible();
    }

    public BoostPagerBottomSheet(Context r7, boolean r8, BoostViaGiftsBottomSheet r9, SelectorBottomSheet r10, Theme.ResourcesProvider r11, boolean r12) {
        super(r7, r8, r11);
        this.leftSheet = r9;
        this.rightSheet = r10;
        setApplyBottomPadding(false);
        setApplyTopPadding(false);
        this.useBackgroundTopPadding = false;
        setBackgroundColor(0);
        fixNavigationBar();
        AndroidUtilities.setLightStatusBar(this, isLightStatusBar());
        checkScreenOrientation();
        AnonymousClass1 r0 = new AnonymousClass1(this, getContext(), r10, r11, r9);
        this.viewPager = r0;
        r0.setOverScrollMode(2);
        r0.setClipToPadding(false);
        r0.setAdapter(new AnonymousClass2(this, r9, r10));
        r0.setPosition(0);
        setCustomView(r0);
        r9.setOnCloseClick(new BoostPagerBottomSheet$$ExternalSyntheticLambda0(this));
        r9.setActionListener(new AnonymousClass3(this, r10));
        r10.setSelectedObjectsListener(new AnonymousClass4(this, r9, r11));
        r10.setOnCloseClick(new BoostPagerBottomSheet$$ExternalSyntheticLambda1(this));
        loadData(r12);
        Bulletin.addDelegate(this.container, new AnonymousClass5(this));
    }

    private void checkScreenOrientation() {
        if (getContext().getResources().getConfiguration().orientation != 2) goto L5;
        boolean r0 = true;
    L6:
        this.isLandscapeOrientation = r0;
        return;
    L5:
        r0 = false;
        goto L6
    }

    public static BoostPagerBottomSheet getInstance() {
        return instance;
    }

    private void hideKeyboardIfVisible() {
        if (isKeyboardVisible() == false) goto L6;
        AndroidUtilities.hideKeyboard(this.rightSheet.getContainerView());
        return;
    }

    private boolean isLightStatusBar() {
        if (ColorUtils.calculateLuminance(Theme.getColor(Theme.key_dialogBackground, this.resourcesProvider)) <= 0.699999988079071d) goto L6;
        return true;
    L6:
        return false;
    }

    private void loadData(boolean r1) {
        if (r1 == true) goto L5;
        MessagesController.getInstance(this.currentAccount).getStoriesController().loadSendAs();
        return;
    }

    public static void show(BaseFragment r1, long r2, Theme.ResourcesProvider r4) {
        show(r1, r4, r2, null);
    }

    public static void show(BaseFragment r9, Theme.ResourcesProvider r10, long r11, TL_stories.PrepaidGiveaway r13) {
        if (instance == null) goto L5;
        return;
    L5:
        boolean r7 = r10 instanceof DarkThemeResourceProvider;
        if (r7 == false) goto L8;
        BaseFragment r1 = new DarkFragmentWrapper(r9);
    L9:
        Theme.ResourcesProvider r102 = r1.getResourceProvider();
        BoostPagerBottomSheet r8 = new BoostPagerBottomSheet(r9.getParentActivity(), true, new BoostViaGiftsBottomSheet(r1, false, false, r11, r13), new SelectorBottomSheet(r1, false, r11), r102, r7);
        r8.show();
        instance = r8;
        return;
    L8:
        r1 = r9;
        goto L9
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        super.dismissInternal();
        Bulletin.removeDelegate(this.container);
        BoostViaGiftsBottomSheet r0 = this.leftSheet;
        if (r0 == null) goto L5;
        r0.dismiss();
    L5:
        instance = null;
        this.leftSheet = null;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onBackPressed() {
        if (this.viewPager.getCurrentPosition() > 0) goto L5;
        super.onBackPressed();
        return;
    L5:
        if (this.rightSheet.hasChanges() == false) goto L7;
        return;
    L7:
        hideKeyboardIfVisible();
        this.viewPager.scrollToPosition(0);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onConfigurationChanged(Configuration r2) {
        this.rightSheet.onConfigurationChanged(r2);
        checkScreenOrientation();
        super.onConfigurationChanged(r2);
    }
}
