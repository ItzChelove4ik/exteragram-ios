package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.HashMap;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.EditTextEmoji;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScrollSlidingTextTabStrip;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.PhotoPickerActivity;

/* loaded from: classes6.dex */
public class PhotoPickerSearchActivity extends BaseFragment {
    private static final Interpolator interpolator = null;
    private boolean animatingForward;
    private boolean backAnimation;
    private Paint backgroundPaint;
    private EditTextEmoji commentTextView;
    private PhotoPickerActivity gifsSearch;
    private PhotoPickerActivity imagesSearch;
    private int maximumVelocity;
    private ScrollSlidingTextTabStrip scrollSlidingTextTabStrip;
    private ActionBarMenuItem searchItem;
    private boolean swipeBackEnabled;
    private AnimatorSet tabsAnimation;
    private boolean tabsAnimationInProgress;
    private ViewPage[] viewPages;

    /* renamed from: org.telegram.ui.PhotoPickerSearchActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PhotoPickerSearchActivity this$0;

        public AnonymousClass1(PhotoPickerSearchActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerSearchActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ PhotoPickerSearchActivity this$0;

        public AnonymousClass2(PhotoPickerSearchActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public boolean canCollapseSearch() {
            this.this$0.finishFragment();
            return false;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            PhotoPickerSearchActivity.m16858$$Nest$fgetimagesSearch(this.this$0).getActionBar().openSearchField(_UrlKt.FRAGMENT_ENCODE_SET, false);
            PhotoPickerSearchActivity.m16857$$Nest$fgetgifsSearch(this.this$0).getActionBar().openSearchField(_UrlKt.FRAGMENT_ENCODE_SET, false);
            PhotoPickerSearchActivity.m16861$$Nest$fgetsearchItem(this.this$0).getSearchField().requestFocus();
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchPressed(EditText r1) {
            PhotoPickerSearchActivity.m16858$$Nest$fgetimagesSearch(this.this$0).getActionBar().onSearchPressed();
            PhotoPickerSearchActivity.m16857$$Nest$fgetgifsSearch(this.this$0).getActionBar().onSearchPressed();
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r3) {
            PhotoPickerSearchActivity.m16858$$Nest$fgetimagesSearch(this.this$0).getActionBar().setSearchFieldText(r3.getText().toString());
            PhotoPickerSearchActivity.m16857$$Nest$fgetgifsSearch(this.this$0).getActionBar().setSearchFieldText(r3.getText().toString());
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerSearchActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate {
        final /* synthetic */ PhotoPickerSearchActivity this$0;

        public AnonymousClass3(PhotoPickerSearchActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate
        public void onPageScrolled(float r7) {
            if (r7 == 1.0f) goto L5;
        L7:
            boolean r2 = PhotoPickerSearchActivity.m16853$$Nest$fgetanimatingForward(this.this$0);
            PhotoPickerSearchActivity r3 = this.this$0;
            if (r2 == false) goto L10;
            PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(r3)[0].setTranslationX((-r7) * PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth());
            PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1].setTranslationX(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth() - (r7 * PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth()));
        L11:
            if (r7 != 1.0f) goto L14;
            ViewPage r72 = PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0];
            PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0] = PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1];
            PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1] = r72;
            PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1].setVisibility(8);
            return;
        L14:
            return;
        L10:
            PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(r3)[0].setTranslationX(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth() * r7);
            PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1].setTranslationX((r7 * PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth()) - PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth());
            goto L11
        L5:
            if (PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1].getVisibility() == 0) goto L7;
        }

        @Override // org.telegram.ui.Components.ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate
        public void onPageSelected(int r5, boolean r6) {
            if (ViewPage.m16880$$Nest$fgetselectedType(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0]) != r5) goto L5;
            return;
        L5:
            PhotoPickerSearchActivity r0 = this.this$0;
            if (r5 != PhotoPickerSearchActivity.m16860$$Nest$fgetscrollSlidingTextTabStrip(r0).getFirstTabId()) goto L8;
            boolean r2 = true;
        L9:
            PhotoPickerSearchActivity.m16867$$Nest$fputswipeBackEnabled(r0, r2);
            ViewPage.m16885$$Nest$fputselectedType(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1], r5);
            PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1].setVisibility(0);
            PhotoPickerSearchActivity.m16872$$Nest$mswitchToCurrentSelectedMode(this.this$0, true);
            PhotoPickerSearchActivity.m16865$$Nest$fputanimatingForward(this.this$0, r6);
            PhotoPickerSearchActivity r4 = this.this$0;
            if (r5 != 0) goto L13;
            PhotoPickerSearchActivity.m16861$$Nest$fgetsearchItem(r4).setSearchFieldHint(LocaleController.getString(R.string.SearchImagesTitle));
            return;
        L13:
            PhotoPickerSearchActivity.m16861$$Nest$fgetsearchItem(r4).setSearchFieldHint(LocaleController.getString(R.string.SearchGifsTitle));
            return;
        L8:
            r2 = false;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerSearchActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends SizeNotifierFrameLayout {
        private boolean globalIgnoreLayout;
        private boolean maybeStartTracking;
        private boolean startedTracking;
        private int startedTrackingPointerId;
        private int startedTrackingX;
        private int startedTrackingY;
        final /* synthetic */ PhotoPickerSearchActivity this$0;
        private VelocityTracker velocityTracker;

        /* renamed from: org.telegram.ui.PhotoPickerSearchActivity$4$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass4 this$1;

            public AnonymousClass1(AnonymousClass4 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r6) {
                PhotoPickerSearchActivity.m16868$$Nest$fputtabsAnimation(this.this$1.this$0, null);
                boolean r62 = PhotoPickerSearchActivity.m16854$$Nest$fgetbackAnimation(this.this$1.this$0);
                AnonymousClass4 r0 = this.this$1;
                if (r62 == false) goto L5;
                PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(r0.this$0)[1].setVisibility(8);
            L10:
                PhotoPickerSearchActivity.m16869$$Nest$fputtabsAnimationInProgress(this.this$1.this$0, false);
                AnonymousClass4.m16874$$Nest$fputmaybeStartTracking(this.this$1, false);
                AnonymousClass4.m16875$$Nest$fputstartedTracking(this.this$1, false);
                PhotoPickerSearchActivity.access$900(this.this$1.this$0).setEnabled(true);
                PhotoPickerSearchActivity.m16860$$Nest$fgetscrollSlidingTextTabStrip(this.this$1.this$0).setEnabled(true);
                return;
            L5:
                ViewPage r63 = PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(r0.this$0)[0];
                PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$1.this$0)[0] = PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$1.this$0)[1];
                PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$1.this$0)[1] = r63;
                PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$1.this$0)[1].setVisibility(8);
                PhotoPickerSearchActivity r64 = this.this$1.this$0;
                if (ViewPage.m16880$$Nest$fgetselectedType(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(r64)[0]) != PhotoPickerSearchActivity.m16860$$Nest$fgetscrollSlidingTextTabStrip(this.this$1.this$0).getFirstTabId()) goto L8;
                boolean r02 = true;
            L9:
                PhotoPickerSearchActivity.m16867$$Nest$fputswipeBackEnabled(r64, r02);
                PhotoPickerSearchActivity.m16860$$Nest$fgetscrollSlidingTextTabStrip(this.this$1.this$0).selectTabWithId(ViewPage.m16880$$Nest$fgetselectedType(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$1.this$0)[0]), 1.0f);
                goto L10
            L8:
                r02 = false;
                goto L9
            }
        }

        /* renamed from: -$$Nest$fputmaybeStartTracking, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16874$$Nest$fputmaybeStartTracking(AnonymousClass4 r0, boolean r1) {
            r0.maybeStartTracking = r1;
        }

        /* renamed from: -$$Nest$fputstartedTracking, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16875$$Nest$fputstartedTracking(AnonymousClass4 r0, boolean r1) {
            r0.startedTracking = r1;
        }

        public AnonymousClass4(PhotoPickerSearchActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private boolean prepareForMoving(MotionEvent r5, boolean r6) {
            int r0 = PhotoPickerSearchActivity.m16860$$Nest$fgetscrollSlidingTextTabStrip(this.this$0).getNextPageId(r6);
            if (r0 >= 0) goto L5;
            return false;
        L5:
            getParent().requestDisallowInterceptTouchEvent(true);
            this.maybeStartTracking = false;
            this.startedTracking = true;
            this.startedTrackingX = (int) r5.getX();
            PhotoPickerSearchActivity.access$000(this.this$0).setEnabled(false);
            PhotoPickerSearchActivity.m16860$$Nest$fgetscrollSlidingTextTabStrip(this.this$0).setEnabled(false);
            ViewPage.m16885$$Nest$fputselectedType(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1], r0);
            PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1].setVisibility(0);
            PhotoPickerSearchActivity.m16865$$Nest$fputanimatingForward(this.this$0, r6);
            PhotoPickerSearchActivity.m16872$$Nest$mswitchToCurrentSelectedMode(this.this$0, true);
            PhotoPickerSearchActivity r52 = this.this$0;
            if (r6 == false) goto L8;
            PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(r52)[1].setTranslationX(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth());
        L9:
            return true;
        L8:
            PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(r52)[1].setTranslationX(-PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth());
            goto L9
        }

        public boolean checkTabsAnimationInProgress() {
            if (PhotoPickerSearchActivity.m16863$$Nest$fgettabsAnimationInProgress(this.this$0) == false) goto L25;
            boolean r0 = PhotoPickerSearchActivity.m16854$$Nest$fgetbackAnimation(this.this$0);
            PhotoPickerSearchActivity r2 = this.this$0;
            int r3 = -1;
            if (r0 == false) goto L13;
            if (Math.abs(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(r2)[0].getTranslationX()) >= 1.0f) goto L24;
            PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].setTranslationX(0.0f);
            ViewPage r02 = PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1];
            int r22 = PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth();
            if (PhotoPickerSearchActivity.m16853$$Nest$fgetanimatingForward(this.this$0) == false) goto L11;
            r3 = 1;
        L11:
            r02.setTranslationX(r22 * r3);
        L20:
            if (PhotoPickerSearchActivity.m16862$$Nest$fgettabsAnimation(this.this$0) == null) goto L22;
            PhotoPickerSearchActivity.m16862$$Nest$fgettabsAnimation(this.this$0).cancel();
            PhotoPickerSearchActivity.m16868$$Nest$fputtabsAnimation(this.this$0, null);
        L22:
            PhotoPickerSearchActivity.m16869$$Nest$fputtabsAnimationInProgress(this.this$0, false);
        L24:
            return PhotoPickerSearchActivity.m16863$$Nest$fgettabsAnimationInProgress(this.this$0);
        L13:
            if (Math.abs(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(r2)[1].getTranslationX()) >= 1.0f) goto L24;
            ViewPage r03 = PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0];
            int r23 = PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth();
            if (PhotoPickerSearchActivity.m16853$$Nest$fgetanimatingForward(this.this$0) == true) goto L18;
            r3 = 1;
        L18:
            r03.setTranslationX(r23 * r3);
            PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1].setTranslationX(0.0f);
            goto L20
        L25:
            return false;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            super.dispatchDraw(r8);
            float r3 = PhotoPickerSearchActivity.access$400(this.this$0).getMeasuredHeight() + ((int) PhotoPickerSearchActivity.access$500(this.this$0).getTranslationY());
            r8.drawLine(0.0f, r3, getWidth(), r3, Theme.dividerPaint);
        }

        @Override // android.view.View
        public void forceHasOverlappingRendering(boolean r1) {
            super.forceHasOverlappingRendering(r1);
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            PhotoPickerSearchActivity.m16855$$Nest$fgetbackgroundPaint(this.this$0).setColor(Theme.getColor(Theme.key_windowBackgroundGray));
            r9.drawRect(0.0f, PhotoPickerSearchActivity.access$600(this.this$0).getMeasuredHeight() + PhotoPickerSearchActivity.access$700(this.this$0).getTranslationY(), getMeasuredWidth(), getMeasuredHeight(), PhotoPickerSearchActivity.m16855$$Nest$fgetbackgroundPaint(this.this$0));
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r2) {
            if (checkTabsAnimationInProgress() == false) goto L5;
            return true;
        L5:
            if (PhotoPickerSearchActivity.m16860$$Nest$fgetscrollSlidingTextTabStrip(this.this$0).isAnimatingIndicator() == false) goto L7;
            return true;
        L7:
            if (onTouchEvent(r2) == true) goto L14;
            return false;
        L14:
            return true;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r10, int r11, int r12, int r13, int r14) {
            int r102 = getChildCount();
            int r1 = 0;
            if (AndroidUtilities.dp(20.0f) >= 0) goto L5;
        L9:
            int r0 = 0;
        L10:
            setBottomClip(r0);
        L11:
            if (r1 >= r102) goto L48;
            View r2 = getChildAt(r1);
            if (r2.getVisibility() == 8) goto L47;
            FrameLayout.LayoutParams r3 = (FrameLayout.LayoutParams) r2.getLayoutParams();
            int r4 = r2.getMeasuredWidth();
            int r5 = r2.getMeasuredHeight();
            int r6 = r3.gravity;
            if (r6 != (-1)) goto L18;
            r6 = 51;
        L18:
            int r7 = r6 & 112;
            int r62 = r6 & 7;
            if (r62 != 1) goto L21;
            int r63 = (((r13 - r11) - r4) / 2) + r3.leftMargin;
            int r8 = r3.rightMargin;
        L24:
            int r64 = r63 - r8;
        L27:
            if (r7 != 16) goto L29;
            int r72 = ((((r14 - r0) - r12) - r5) / 2) + r3.topMargin;
            int r32 = r3.bottomMargin;
        L34:
            int r33 = r72 - r32;
        L38:
            if (PhotoPickerSearchActivity.m16856$$Nest$fgetcommentTextView(this.this$0) != null) goto L40;
        L46:
            r2.layout(r64, r33, r4 + r64, r5 + r33);
            goto L47
        L40:
            if (PhotoPickerSearchActivity.m16856$$Nest$fgetcommentTextView(this.this$0).isPopupView(r2) == false) goto L46;
            if (AndroidUtilities.isTablet() == false) goto L45;
            int r34 = getMeasuredHeight();
            int r73 = r2.getMeasuredHeight();
        L44:
            r33 = r34 - r73;
            goto L46
        L45:
            r34 = getMeasuredHeight();
            r73 = r2.getMeasuredHeight();
            goto L44
        L29:
            if (r7 != 48) goto L31;
            r33 = r3.topMargin + getPaddingTop();
            goto L38
        L31:
            if (r7 == 80) goto L33;
            r33 = r3.topMargin;
            goto L38
        L33:
            r72 = ((r14 - r0) - r12) - r5;
            r32 = r3.bottomMargin;
            goto L34
        L21:
            if (r62 == 5) goto L23;
            r64 = r3.leftMargin + getPaddingLeft();
            goto L27
        L23:
            r63 = ((r13 - r11) - r4) - r3.rightMargin;
            r8 = getPaddingRight();
        L47:
            r1 = r1 + 1;
            goto L11
        L48:
            notifyHeightChanged();
            return;
        L5:
            if (AndroidUtilities.isInMultiwindow == true) goto L9;
            if (AndroidUtilities.isTablet() == true) goto L9;
            r0 = PhotoPickerSearchActivity.m16856$$Nest$fgetcommentTextView(this.this$0).getEmojiPadding();
            goto L10
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r14, int r15) {
            int r6 = View.MeasureSpec.getSize(r14);
            int r7 = View.MeasureSpec.getSize(r15);
            setMeasuredDimension(r6, r7);
            measureChildWithMargins(PhotoPickerSearchActivity.access$100(this.this$0), r14, 0, r15, 0);
            if (AndroidUtilities.dp(20.0f) >= 0) goto L5;
            this.globalIgnoreLayout = true;
            PhotoPickerSearchActivity.m16856$$Nest$fgetcommentTextView(this.this$0).hideEmojiView();
            this.globalIgnoreLayout = false;
        L8:
            int r4 = r15;
        L9:
            int r1 = PhotoPickerSearchActivity.access$200(this.this$0).getMeasuredHeight();
            this.globalIgnoreLayout = true;
            int r3 = 0;
        L11:
            if (r3 >= PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0).length) goto L19;
            if (PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[r3] == null) goto L18;
            if (ViewPage.m16878$$Nest$fgetlistView(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[r3]) == null) goto L18;
            ViewPage.m16878$$Nest$fgetlistView(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[r3]).setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f) + r1, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
        L18:
            r3 = r3 + 1;
            goto L11
        L19:
            this.globalIgnoreLayout = false;
            int r9 = getChildCount();
            int r10 = 0;
        L20:
            if (r10 >= r9) goto L48;
            View r12 = getChildAt(r10);
            if (r12 == null) goto L47;
            if (r12.getVisibility() == 8) goto L47;
            if (r12 == PhotoPickerSearchActivity.access$300(this.this$0)) goto L47;
            if (PhotoPickerSearchActivity.m16856$$Nest$fgetcommentTextView(this.this$0) != null) goto L31;
        L46:
            measureChildWithMargins(r12, r14, 0, r4, 0);
            goto L47
        L31:
            if (PhotoPickerSearchActivity.m16856$$Nest$fgetcommentTextView(this.this$0).isPopupView(r12) == false) goto L46;
            if (AndroidUtilities.isInMultiwindow == true) goto L39;
            if (AndroidUtilities.isTablet() == true) goto L39;
            r12.measure(View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r12.getLayoutParams().height, TLObject.FLAG_30));
        L39:
            if (AndroidUtilities.isTablet() == false) goto L45;
            int r2 = View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30);
            if (AndroidUtilities.isTablet() == false) goto L43;
            float r32 = 200.0f;
        L44:
            r12.measure(r2, View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(r32), (r7 - AndroidUtilities.statusBarHeight) + getPaddingTop()), TLObject.FLAG_30));
            goto L47
        L43:
            r32 = 320.0f;
            goto L44
        L45:
            r12.measure(View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((r7 - AndroidUtilities.statusBarHeight) + getPaddingTop(), TLObject.FLAG_30));
        L47:
            r10 = r10 + 1;
            goto L20
        L48:
            return;
        L5:
            if (AndroidUtilities.isInMultiwindow == true) goto L8;
            r7 = r7 - PhotoPickerSearchActivity.m16856$$Nest$fgetcommentTextView(this.this$0).getEmojiPadding();
            r4 = View.MeasureSpec.makeMeasureSpec(r7, TLObject.FLAG_30);
            goto L9
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r12) {
            boolean r1 = false;
            if (PhotoPickerSearchActivity.access$800(this.this$0).checkTransitionAnimation() == false) goto L5;
        L120:
            return false;
        L5:
            if (checkTabsAnimationInProgress() == true) goto L120;
            if (r12 == null) goto L12;
            if (this.velocityTracker != null) goto L10;
            this.velocityTracker = VelocityTracker.obtain();
        L10:
            this.velocityTracker.addMovement(r12);
        L12:
            if (r12 == null) goto L21;
            if (r12.getAction() != 0) goto L21;
            if (this.startedTracking == true) goto L21;
            if (this.maybeStartTracking == true) goto L21;
            this.startedTrackingPointerId = r12.getPointerId(0);
            this.maybeStartTracking = true;
            this.startedTrackingX = (int) r12.getX();
            this.startedTrackingY = (int) r12.getY();
            this.velocityTracker.clear();
        L119:
            return this.startedTracking;
        L21:
            if (r12 == null) goto L64;
            if (r12.getAction() != 2) goto L64;
            if (r12.getPointerId(0) != this.startedTrackingPointerId) goto L64;
            int r2 = (int) (r12.getX() - this.startedTrackingX);
            int r4 = Math.abs(((int) r12.getY()) - this.startedTrackingY);
            if (this.startedTracking == false) goto L46;
            if (PhotoPickerSearchActivity.m16853$$Nest$fgetanimatingForward(this.this$0) == false) goto L32;
            if (r2 <= 0) goto L32;
        L34:
            if (r2 >= 0) goto L36;
            boolean r5 = true;
        L38:
            if (prepareForMoving(r12, r5) == true) goto L46;
            this.maybeStartTracking = true;
            this.startedTracking = false;
            PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].setTranslationX(0.0f);
            ViewPage r52 = PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1];
            boolean r6 = PhotoPickerSearchActivity.m16853$$Nest$fgetanimatingForward(this.this$0);
            PhotoPickerSearchActivity r7 = this.this$0;
            if (r6 == false) goto L43;
            int r62 = PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(r7)[0].getMeasuredWidth();
        L44:
            r52.setTranslationX(r62);
            PhotoPickerSearchActivity.m16860$$Nest$fgetscrollSlidingTextTabStrip(this.this$0).selectTabWithId(ViewPage.m16880$$Nest$fgetselectedType(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1]), 0.0f);
            goto L46
        L43:
            r62 = -PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(r7)[0].getMeasuredWidth();
            goto L44
        L36:
            r5 = false;
        L32:
            if (PhotoPickerSearchActivity.m16853$$Nest$fgetanimatingForward(this.this$0) == true) goto L46;
            if (r2 < 0) goto L34;
        L46:
            if (this.maybeStartTracking == false) goto L57;
            if (this.startedTracking == true) goto L57;
            if (Math.abs(r2) < AndroidUtilities.getPixelsInCM(0.3f, true)) goto L119;
            if (Math.abs(r2) <= r4) goto L119;
            if (r2 >= 0) goto L55;
            r1 = true;
        L55:
            prepareForMoving(r12, r1);
        L57:
            if (this.startedTracking == false) goto L119;
            PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].setTranslationX(r2);
            boolean r122 = PhotoPickerSearchActivity.m16853$$Nest$fgetanimatingForward(this.this$0);
            PhotoPickerSearchActivity r3 = this.this$0;
            if (r122 == false) goto L61;
            PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(r3)[1].setTranslationX(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth() + r2);
        L62:
            PhotoPickerSearchActivity.m16860$$Nest$fgetscrollSlidingTextTabStrip(this.this$0).selectTabWithId(ViewPage.m16880$$Nest$fgetselectedType(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1]), Math.abs(r2) / PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth());
            goto L119
        L61:
            PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(r3)[1].setTranslationX(r2 - PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth());
        L64:
            if (r12 != null) goto L66;
        L73:
            this.velocityTracker.computeCurrentVelocity(1000, PhotoPickerSearchActivity.m16859$$Nest$fgetmaximumVelocity(this.this$0));
            if (r12 != null) goto L76;
        L88:
            float r42 = 0.0f;
            float r53 = 0.0f;
        L90:
            if (this.startedTracking == false) goto L114;
            float r123 = PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getX();
            PhotoPickerSearchActivity.m16868$$Nest$fputtabsAnimation(this.this$0, new AnimatorSet());
            PhotoPickerSearchActivity r63 = this.this$0;
            if (Math.abs(r123) < (PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth() / 3.0f)) goto L94;
        L98:
            boolean r54 = false;
        L99:
            PhotoPickerSearchActivity.m16866$$Nest$fputbackAnimation(r63, r54);
            boolean r55 = PhotoPickerSearchActivity.m16854$$Nest$fgetbackAnimation(this.this$0);
            Property r64 = View.TRANSLATION_X;
            if (r55 == false) goto L105;
            float r124 = Math.abs(r123);
            boolean r56 = PhotoPickerSearchActivity.m16853$$Nest$fgetanimatingForward(this.this$0);
            PhotoPickerSearchActivity r72 = this.this$0;
            if (r56 == false) goto L104;
            PhotoPickerSearchActivity.m16862$$Nest$fgettabsAnimation(r72).playTogether(new Animator[]{ObjectAnimator.ofFloat(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0], r64, new float[]{0.0f}), ObjectAnimator.ofFloat(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1], r64, new float[]{PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1].getMeasuredWidth()})});
        L109:
            PhotoPickerSearchActivity.m16862$$Nest$fgettabsAnimation(this.this$0).setInterpolator(PhotoPickerSearchActivity.m16873$$Nest$sfgetinterpolator());
            int r22 = getMeasuredWidth();
            float r57 = r22 / 2;
            float r58 = r57 + (AndroidUtilities.distanceInfluenceForSnapDuration(Math.min(1.0f, (r124 * 1.0f) / r22)) * r57);
            float r23 = Math.abs(r42);
            if (r23 <= 0.0f) goto L112;
            int r125 = Math.round(Math.abs(r58 / r23) * 1000.0f) * 4;
        L113:
            PhotoPickerSearchActivity.m16862$$Nest$fgettabsAnimation(this.this$0).setDuration(Math.max(150, Math.min(r125, MediaError.DetailedErrorCode.TEXT_UNKNOWN)));
            PhotoPickerSearchActivity.m16862$$Nest$fgettabsAnimation(this.this$0).addListener(new AnonymousClass1(this));
            PhotoPickerSearchActivity.m16862$$Nest$fgettabsAnimation(this.this$0).start();
            PhotoPickerSearchActivity.m16869$$Nest$fputtabsAnimationInProgress(this.this$0, true);
            this.startedTracking = false;
        L115:
            VelocityTracker r126 = this.velocityTracker;
            if (r126 == null) goto L119;
            r126.recycle();
            this.velocityTracker = null;
            goto L119
        L112:
            r125 = (int) (((r124 / getMeasuredWidth()) + 1.0f) * 100.0f);
            goto L113
        L104:
            PhotoPickerSearchActivity.m16862$$Nest$fgettabsAnimation(r72).playTogether(new Animator[]{ObjectAnimator.ofFloat(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0], r64, new float[]{0.0f}), ObjectAnimator.ofFloat(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1], r64, new float[]{-PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1].getMeasuredWidth()})});
            goto L109
        L105:
            r124 = PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth() - Math.abs(r123);
            boolean r59 = PhotoPickerSearchActivity.m16853$$Nest$fgetanimatingForward(this.this$0);
            PhotoPickerSearchActivity r73 = this.this$0;
            if (r59 == false) goto L108;
            PhotoPickerSearchActivity.m16862$$Nest$fgettabsAnimation(r73).playTogether(new Animator[]{ObjectAnimator.ofFloat(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0], r64, new float[]{-PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth()}), ObjectAnimator.ofFloat(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1], r64, new float[]{0.0f})});
            goto L109
        L108:
            PhotoPickerSearchActivity.m16862$$Nest$fgettabsAnimation(r73).playTogether(new Animator[]{ObjectAnimator.ofFloat(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0], r64, new float[]{PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth()}), ObjectAnimator.ofFloat(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1], r64, new float[]{0.0f})});
            goto L109
        L94:
            if (Math.abs(r42) >= 3500.0f) goto L96;
        L97:
            r54 = true;
            goto L99
        L96:
            if (Math.abs(r42) >= Math.abs(r53)) goto L98;
        L114:
            this.maybeStartTracking = false;
            PhotoPickerSearchActivity.access$1000(this.this$0).setEnabled(true);
            PhotoPickerSearchActivity.m16860$$Nest$fgetscrollSlidingTextTabStrip(this.this$0).setEnabled(true);
            goto L115
        L76:
            if (r12.getAction() == 3) goto L88;
            r42 = this.velocityTracker.getXVelocity();
            r53 = this.velocityTracker.getYVelocity();
            if (this.startedTracking == true) goto L90;
            if (Math.abs(r42) < 3000.0f) goto L90;
            if (Math.abs(r42) <= Math.abs(r53)) goto L90;
            if (r42 >= 0.0f) goto L86;
            boolean r65 = true;
        L87:
            prepareForMoving(r12, r65);
            goto L90
        L86:
            r65 = false;
            goto L87
        L66:
            if (r12.getPointerId(0) != this.startedTrackingPointerId) goto L119;
            if (r12.getAction() == 3) goto L73;
            if (r12.getAction() == 1) goto L73;
            if (r12.getAction() != 6) goto L119;
            goto L73
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.globalIgnoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerSearchActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends ViewPage {
        final /* synthetic */ PhotoPickerSearchActivity this$0;

        public AnonymousClass5(PhotoPickerSearchActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setTranslationX(float r3) {
            super.setTranslationX(r3);
            if (PhotoPickerSearchActivity.m16863$$Nest$fgettabsAnimationInProgress(this.this$0) == true) goto L5;
            return;
        L5:
            if (PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0] != this) goto L9;
            PhotoPickerSearchActivity.m16860$$Nest$fgetscrollSlidingTextTabStrip(this.this$0).selectTabWithId(ViewPage.m16880$$Nest$fgetselectedType(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[1]), Math.abs(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getTranslationX()) / PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth());
            return;
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerSearchActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends RecyclerView.OnScrollListener {
        final /* synthetic */ PhotoPickerSearchActivity this$0;
        final /* synthetic */ RecyclerView.OnScrollListener val$onScrollListener;

        public AnonymousClass6(PhotoPickerSearchActivity r1, RecyclerView.OnScrollListener r2) {
            this.this$0 = r1;
            this.val$onScrollListener = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r3, int r4) {
            this.val$onScrollListener.onScrollStateChanged(r3, r4);
            if (r4 == 1) goto L13;
            int r32 = (int) (-PhotoPickerSearchActivity.access$1100(this.this$0).getTranslationY());
            int r42 = ActionBar.getCurrentActionBarHeight();
            if (r32 == 0) goto L14;
            if (r32 == r42) goto L15;
            int r0 = r42 / 2;
            PhotoPickerSearchActivity r2 = this.this$0;
            if (r32 >= r0) goto L11;
            ViewPage.m16878$$Nest$fgetlistView(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(r2)[0]).smoothScrollBy(0, -r32);
            return;
        L11:
            ViewPage.m16878$$Nest$fgetlistView(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(r2)[0]).smoothScrollBy(0, r42 - r32);
            return;
        L15:
            return;
        L14:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            this.val$onScrollListener.onScrolled(r2, r3, r4);
            if (r2 != ViewPage.m16878$$Nest$fgetlistView(PhotoPickerSearchActivity.m16864$$Nest$fgetviewPages(this.this$0)[0])) goto L14;
            float r22 = PhotoPickerSearchActivity.access$1200(this.this$0).getTranslationY();
            float r32 = r22 - r4;
            if (r32 >= (-ActionBar.getCurrentActionBarHeight())) goto L8;
            r32 = -ActionBar.getCurrentActionBarHeight();
        L11:
            if (r32 == r22) goto L15;
            PhotoPickerSearchActivity.m16871$$Nest$msetScrollY(this.this$0, r32);
            return;
        L15:
            return;
        L8:
            if (r32 <= 0.0f) goto L11;
            r32 = 0.0f;
            goto L11
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerSearchActivity$7, reason: invalid class name */
    public class AnonymousClass7 implements PhotoPickerActivity.PhotoPickerActivitySearchDelegate {
        final /* synthetic */ PhotoPickerSearchActivity this$0;

        public AnonymousClass7(PhotoPickerSearchActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivitySearchDelegate
        public void shouldClearRecentSearch() {
            PhotoPickerSearchActivity.m16858$$Nest$fgetimagesSearch(this.this$0).clearRecentSearch();
            PhotoPickerSearchActivity.m16857$$Nest$fgetgifsSearch(this.this$0).clearRecentSearch();
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivitySearchDelegate
        public void shouldSearchText(String r1) {
            PhotoPickerSearchActivity.m16870$$Nest$msearchText(this.this$0, r1);
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerSearchActivity$8, reason: invalid class name */
    public class AnonymousClass8 implements PhotoPickerActivity.PhotoPickerActivitySearchDelegate {
        final /* synthetic */ PhotoPickerSearchActivity this$0;

        public AnonymousClass8(PhotoPickerSearchActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivitySearchDelegate
        public void shouldClearRecentSearch() {
            PhotoPickerSearchActivity.m16858$$Nest$fgetimagesSearch(this.this$0).clearRecentSearch();
            PhotoPickerSearchActivity.m16857$$Nest$fgetgifsSearch(this.this$0).clearRecentSearch();
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivitySearchDelegate
        public void shouldSearchText(String r1) {
            PhotoPickerSearchActivity.m16870$$Nest$msearchText(this.this$0, r1);
        }
    }

    public static class ViewPage extends FrameLayout {
        private ActionBar actionBar;
        private FrameLayout fragmentView;
        private RecyclerListView listView;
        private BaseFragment parentFragment;
        private int selectedType;

        /* renamed from: -$$Nest$fgetactionBar, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ActionBar m16876$$Nest$fgetactionBar(ViewPage r0) {
            return r0.actionBar;
        }

        /* renamed from: -$$Nest$fgetfragmentView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m16877$$Nest$fgetfragmentView(ViewPage r0) {
            return r0.fragmentView;
        }

        /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RecyclerListView m16878$$Nest$fgetlistView(ViewPage r0) {
            return r0.listView;
        }

        /* renamed from: -$$Nest$fgetparentFragment, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BaseFragment m16879$$Nest$fgetparentFragment(ViewPage r0) {
            return r0.parentFragment;
        }

        /* renamed from: -$$Nest$fgetselectedType, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m16880$$Nest$fgetselectedType(ViewPage r0) {
            return r0.selectedType;
        }

        /* renamed from: -$$Nest$fputactionBar, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16881$$Nest$fputactionBar(ViewPage r0, ActionBar r1) {
            r0.actionBar = r1;
        }

        /* renamed from: -$$Nest$fputfragmentView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16882$$Nest$fputfragmentView(ViewPage r0, FrameLayout r1) {
            r0.fragmentView = r1;
        }

        /* renamed from: -$$Nest$fputlistView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16883$$Nest$fputlistView(ViewPage r0, RecyclerListView r1) {
            r0.listView = r1;
        }

        /* renamed from: -$$Nest$fputparentFragment, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16884$$Nest$fputparentFragment(ViewPage r0, BaseFragment r1) {
            r0.parentFragment = r1;
        }

        /* renamed from: -$$Nest$fputselectedType, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16885$$Nest$fputselectedType(ViewPage r0, int r1) {
            r0.selectedType = r1;
        }

        public ViewPage(Context r1) {
            super(r1);
        }
    }

    public static /* synthetic */ float $r8$lambda$kao_7BN7wkVBpYWnDaLbyRDV4AE(float r2) {
        float r22 = r2 - 1.0f;
        return ((((r22 * r22) * r22) * r22) * r22) + 1.0f;
    }

    /* renamed from: -$$Nest$fgetanimatingForward, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16853$$Nest$fgetanimatingForward(PhotoPickerSearchActivity r0) {
        return r0.animatingForward;
    }

    /* renamed from: -$$Nest$fgetbackAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16854$$Nest$fgetbackAnimation(PhotoPickerSearchActivity r0) {
        return r0.backAnimation;
    }

    /* renamed from: -$$Nest$fgetbackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m16855$$Nest$fgetbackgroundPaint(PhotoPickerSearchActivity r0) {
        return r0.backgroundPaint;
    }

    /* renamed from: -$$Nest$fgetcommentTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextEmoji m16856$$Nest$fgetcommentTextView(PhotoPickerSearchActivity r0) {
        return r0.commentTextView;
    }

    /* renamed from: -$$Nest$fgetgifsSearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoPickerActivity m16857$$Nest$fgetgifsSearch(PhotoPickerSearchActivity r0) {
        return r0.gifsSearch;
    }

    /* renamed from: -$$Nest$fgetimagesSearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoPickerActivity m16858$$Nest$fgetimagesSearch(PhotoPickerSearchActivity r0) {
        return r0.imagesSearch;
    }

    /* renamed from: -$$Nest$fgetmaximumVelocity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16859$$Nest$fgetmaximumVelocity(PhotoPickerSearchActivity r0) {
        return r0.maximumVelocity;
    }

    /* renamed from: -$$Nest$fgetscrollSlidingTextTabStrip, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ScrollSlidingTextTabStrip m16860$$Nest$fgetscrollSlidingTextTabStrip(PhotoPickerSearchActivity r0) {
        return r0.scrollSlidingTextTabStrip;
    }

    /* renamed from: -$$Nest$fgetsearchItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m16861$$Nest$fgetsearchItem(PhotoPickerSearchActivity r0) {
        return r0.searchItem;
    }

    /* renamed from: -$$Nest$fgettabsAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m16862$$Nest$fgettabsAnimation(PhotoPickerSearchActivity r0) {
        return r0.tabsAnimation;
    }

    /* renamed from: -$$Nest$fgettabsAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16863$$Nest$fgettabsAnimationInProgress(PhotoPickerSearchActivity r0) {
        return r0.tabsAnimationInProgress;
    }

    /* renamed from: -$$Nest$fgetviewPages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPage[] m16864$$Nest$fgetviewPages(PhotoPickerSearchActivity r0) {
        return r0.viewPages;
    }

    /* renamed from: -$$Nest$fputanimatingForward, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16865$$Nest$fputanimatingForward(PhotoPickerSearchActivity r0, boolean r1) {
        r0.animatingForward = r1;
    }

    /* renamed from: -$$Nest$fputbackAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16866$$Nest$fputbackAnimation(PhotoPickerSearchActivity r0, boolean r1) {
        r0.backAnimation = r1;
    }

    /* renamed from: -$$Nest$fputswipeBackEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16867$$Nest$fputswipeBackEnabled(PhotoPickerSearchActivity r0, boolean r1) {
        r0.swipeBackEnabled = r1;
    }

    /* renamed from: -$$Nest$fputtabsAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16868$$Nest$fputtabsAnimation(PhotoPickerSearchActivity r0, AnimatorSet r1) {
        r0.tabsAnimation = r1;
    }

    /* renamed from: -$$Nest$fputtabsAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16869$$Nest$fputtabsAnimationInProgress(PhotoPickerSearchActivity r0, boolean r1) {
        r0.tabsAnimationInProgress = r1;
    }

    /* renamed from: -$$Nest$msearchText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16870$$Nest$msearchText(PhotoPickerSearchActivity r0, String r1) {
        r0.searchText(r1);
    }

    /* renamed from: -$$Nest$msetScrollY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16871$$Nest$msetScrollY(PhotoPickerSearchActivity r0, float r1) {
        r0.setScrollY(r1);
    }

    /* renamed from: -$$Nest$mswitchToCurrentSelectedMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16872$$Nest$mswitchToCurrentSelectedMode(PhotoPickerSearchActivity r0, boolean r1) {
        r0.switchToCurrentSelectedMode(r1);
    }

    /* renamed from: -$$Nest$sfgetinterpolator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Interpolator m16873$$Nest$sfgetinterpolator() {
        return interpolator;
    }

    static {
        interpolator = new PhotoPickerSearchActivity$$ExternalSyntheticLambda0();
    }

    public PhotoPickerSearchActivity(HashMap<Object, Object> r21, ArrayList<Object> r22, int r23, boolean r24, ChatActivity r25) {
        this.swipeBackEnabled = true;
        this.backgroundPaint = new Paint();
        this.viewPages = new ViewPage[2];
        this.imagesSearch = new PhotoPickerActivity(0, null, r21, r22, r23, r24, r25, false);
        this.gifsSearch = new PhotoPickerActivity(1, null, r21, r22, r23, r24, r25, false);
    }

    public static /* synthetic */ ActionBar access$000(PhotoPickerSearchActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(PhotoPickerSearchActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$1000(PhotoPickerSearchActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$1100(PhotoPickerSearchActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$1200(PhotoPickerSearchActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$200(PhotoPickerSearchActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$300(PhotoPickerSearchActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$400(PhotoPickerSearchActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$500(PhotoPickerSearchActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$600(PhotoPickerSearchActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$700(PhotoPickerSearchActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ INavigationLayout access$800(PhotoPickerSearchActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ ActionBar access$900(PhotoPickerSearchActivity r0) {
        return r0.actionBar;
    }

    private void searchText(String r2) {
        this.searchItem.getSearchField().setText(r2);
        this.searchItem.getSearchField().setSelection(r2.length());
        this.actionBar.onSearchPressed();
    }

    private void setScrollY(float r4) {
        this.actionBar.setTranslationY(r4);
        int r0 = 0;
    L3:
        ViewPage[] r1 = this.viewPages;
        if (r0 >= r1.length) goto L6;
        ViewPage.m16878$$Nest$fgetlistView(r1[r0]).setPinnedSectionOffsetY((int) r4);
        r0 = r0 + 1;
        goto L3
    L6:
        this.fragmentView.invalidate();
    }

    private void switchToCurrentSelectedMode(boolean r5) {
        int r1 = 0;
    L3:
        ViewPage[] r2 = this.viewPages;
        if (r1 >= r2.length) goto L6;
        ViewPage.m16878$$Nest$fgetlistView(r2[r1]).stopScroll();
        r1 = r1 + 1;
        goto L3
    L6:
        ViewPage.m16878$$Nest$fgetlistView(r2[r5 ? 1 : 0]).getAdapter();
        ViewPage.m16878$$Nest$fgetlistView(this.viewPages[r5 ? 1 : 0]).setPinnedHeaderShadowDrawable(null);
        if (this.actionBar.getTranslationY() == 0.0f) goto L11;
        ((LinearLayoutManager) ViewPage.m16878$$Nest$fgetlistView(this.viewPages[r5 ? 1 : 0]).getLayoutManager()).scrollToPositionWithOffset(0, (int) this.actionBar.getTranslationY());
        return;
    }

    private void updateTabs() {
        ScrollSlidingTextTabStrip r0 = this.scrollSlidingTextTabStrip;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.addTextTab(0, LocaleController.getString(R.string.ImagesTab2));
        this.scrollSlidingTextTabStrip.addTextTab(1, LocaleController.getString(R.string.GifsTab2));
        this.scrollSlidingTextTabStrip.setVisibility(0);
        this.actionBar.setExtraHeight(AndroidUtilities.dp(44.0f));
        int r02 = this.scrollSlidingTextTabStrip.getCurrentTabId();
        if (r02 < 0) goto L8;
        ViewPage.m16885$$Nest$fputselectedType(this.viewPages[0], r02);
    L8:
        this.scrollSlidingTextTabStrip.finishAddingTabs();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r14) {
        this.actionBar.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground));
        ActionBar r0 = this.actionBar;
        int r1 = Theme.key_dialogTextBlack;
        r0.setTitleColor(Theme.getColor(r1));
        boolean r3 = false;
        this.actionBar.setItemsColor(Theme.getColor(r1), false);
        ActionBar r02 = this.actionBar;
        int r2 = Theme.key_dialogButtonSelector;
        r02.setItemsBackgroundColor(Theme.getColor(r2), false);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        INavigationLayout r03 = this.parentLayout;
        if (r03 != null) goto L5;
    L7:
        this.actionBar.setExtraHeight(AndroidUtilities.dp(44.0f));
        this.actionBar.setAllowOverlayTitle(false);
        this.actionBar.setAddToContainer(false);
        this.actionBar.setClipContent(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.hasOwnBackground = true;
        ActionBarMenuItem r04 = this.actionBar.createMenu().addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass2(this));
        this.searchItem = r04;
        r04.setSearchFieldHint(LocaleController.getString(R.string.SearchImagesTitle));
        EditTextBoldCursor r05 = this.searchItem.getSearchField();
        r05.setTextColor(Theme.getColor(r1));
        r05.setCursorColor(Theme.getColor(r1));
        r05.setHintTextColor(Theme.getColor(Theme.key_chat_messagePanelHint));
        ScrollSlidingTextTabStrip r06 = new ScrollSlidingTextTabStrip(r14);
        this.scrollSlidingTextTabStrip = r06;
        r06.setUseSameWidth(true);
        ScrollSlidingTextTabStrip r07 = this.scrollSlidingTextTabStrip;
        int r12 = Theme.key_chat_attachActiveTab;
        r07.setColors(r12, r12, Theme.key_chat_attachUnactiveTab, r2);
        this.actionBar.addView(this.scrollSlidingTextTabStrip, LayoutHelper.createFrame(-1, 44, 83));
        this.scrollSlidingTextTabStrip.setDelegate(new AnonymousClass3(this));
        this.maximumVelocity = ViewConfiguration.get(r14).getScaledMaximumFlingVelocity();
        AnonymousClass4 r08 = new AnonymousClass4(this, r14);
        this.fragmentView = r08;
        r08.setWillNotDraw(false);
        this.imagesSearch.setParentFragment(this);
        EditTextEmoji r13 = this.imagesSearch.commentTextView;
        this.commentTextView = r13;
        r13.setSizeNotifierLayout(r08);
        int r15 = 0;
    L9:
        if (r15 >= 4) goto L19;
        if (r15 == 0) goto L17;
        if (r15 == 1) goto L16;
        PhotoPickerActivity r22 = this.imagesSearch;
        if (r15 == 2) goto L15;
        View r23 = r22.shadow;
    L18:
        ((ViewGroup) r23.getParent()).removeView(r23);
        r15 = r15 + 1;
        goto L9
    L15:
        r23 = r22.selectedCountView;
        goto L18
    L16:
        r23 = this.imagesSearch.writeButtonContainer;
        goto L18
    L17:
        r23 = this.imagesSearch.frameLayout2;
        goto L18
    L19:
        PhotoPickerActivity r7 = this.gifsSearch;
        PhotoPickerActivity r16 = this.imagesSearch;
        r7.setLayoutViews(r16.frameLayout2, r16.writeButtonContainer, r16.selectedCountView, r16.shadow, r16.commentTextView);
        this.gifsSearch.setParentFragment(this);
        int r17 = 0;
    L20:
        ViewPage[] r24 = this.viewPages;
        if (r17 >= r24.length) goto L28;
        r24[r17] = new AnonymousClass5(this, r14);
        r08.addView(this.viewPages[r17], LayoutHelper.createFrame(-1, -1.0f));
        if (r17 != 0) goto L25;
        ViewPage.m16884$$Nest$fputparentFragment(this.viewPages[r17], this.imagesSearch);
        ViewPage.m16883$$Nest$fputlistView(this.viewPages[r17], this.imagesSearch.getListView());
    L27:
        ViewPage.m16878$$Nest$fgetlistView(this.viewPages[r17]).setScrollingTouchSlop(1);
        ViewPage r9 = this.viewPages[r17];
        ViewPage.m16882$$Nest$fputfragmentView(r9, (FrameLayout) ViewPage.m16879$$Nest$fgetparentFragment(r9).getFragmentView());
        ViewPage.m16878$$Nest$fgetlistView(this.viewPages[r17]).setClipToPadding(false);
        ViewPage r92 = this.viewPages[r17];
        ViewPage.m16881$$Nest$fputactionBar(r92, ViewPage.m16879$$Nest$fgetparentFragment(r92).getActionBar());
        ViewPage r93 = this.viewPages[r17];
        r93.addView(ViewPage.m16877$$Nest$fgetfragmentView(r93), LayoutHelper.createFrame(-1, -1.0f));
        ViewPage r72 = this.viewPages[r17];
        r72.addView(ViewPage.m16876$$Nest$fgetactionBar(r72), LayoutHelper.createFrame(-1, -2.0f));
        ViewPage.m16876$$Nest$fgetactionBar(this.viewPages[r17]).setVisibility(8);
        ViewPage.m16878$$Nest$fgetlistView(this.viewPages[r17]).setOnScrollListener(new AnonymousClass6(this, ViewPage.m16878$$Nest$fgetlistView(this.viewPages[r17]).getOnScrollListener()));
        r17 = r17 + 1;
        goto L20
    L25:
        if (r17 != 1) goto L27;
        ViewPage.m16884$$Nest$fputparentFragment(this.viewPages[r17], this.gifsSearch);
        ViewPage.m16883$$Nest$fputlistView(this.viewPages[r17], this.gifsSearch.getListView());
        this.viewPages[r17].setVisibility(8);
        goto L27
    L28:
        r08.addView(this.actionBar, LayoutHelper.createFrame(-1, -2.0f));
        r08.addView(this.imagesSearch.frameLayout2, LayoutHelper.createFrame(-1, 48, 83));
        r08.addView(this.imagesSearch.writeButtonContainer, LayoutHelper.createFrame(60, 60.0f, 85, 0.0f, 0.0f, 12.0f, 10.0f));
        r08.addView(this.imagesSearch.selectedCountView, LayoutHelper.createFrame(42, 24.0f, 85, 0.0f, 0.0f, -2.0f, 9.0f));
        updateTabs();
        switchToCurrentSelectedMode(false);
        if (this.scrollSlidingTextTabStrip.getCurrentTabId() != this.scrollSlidingTextTabStrip.getFirstTabId()) goto L31;
        r3 = true;
    L31:
        this.swipeBackEnabled = r3;
        if (AndroidUtilities.computePerceivedBrightness(Theme.getColor(Theme.key_dialogBackground)) < 0.721f) goto L35;
        View r142 = this.fragmentView;
        r142.setSystemUiVisibility(r142.getSystemUiVisibility() | 8192);
    L35:
        return this.fragmentView;
    L5:
        if (r03.isLayersLayout() == false) goto L7;
        this.actionBar.setOccupyStatusBar(false);
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        View r3 = this.fragmentView;
        int r4 = ThemeDescription.FLAG_BACKGROUND;
        int r9 = Theme.key_dialogBackground;
        r1.add(new ThemeDescription(r3, r4, null, null, null, null, r9));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r9));
        ActionBar r7 = this.actionBar;
        int r8 = ThemeDescription.FLAG_AB_ITEMSCOLOR;
        int r16 = Theme.key_dialogTextBlack;
        r1.add(new ThemeDescription(r7, r8, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, r16));
        ActionBar r2 = this.actionBar;
        int r19 = ThemeDescription.FLAG_AB_SELECTORCOLOR;
        int r24 = Theme.key_dialogButtonSelector;
        r1.add(new ThemeDescription(r2, r19, null, null, null, null, r24));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCH, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCHPLACEHOLDER, null, null, null, null, Theme.key_chat_messagePanelHint));
        r1.add(new ThemeDescription(this.searchItem.getSearchField(), ThemeDescription.FLAG_CURSORCOLOR, null, null, null, null, r16));
        int r17 = Theme.key_chat_attachActiveTab;
        r1.add(new ThemeDescription(this.scrollSlidingTextTabStrip.getTabsContainer(), ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{TextView.class}, null, null, null, r17));
        r1.add(new ThemeDescription(this.scrollSlidingTextTabStrip.getTabsContainer(), ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{TextView.class}, null, null, null, Theme.key_chat_attachUnactiveTab));
        r1.add(new ThemeDescription(this.scrollSlidingTextTabStrip.getTabsContainer(), ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, new Class[]{TextView.class}, null, null, null, r24));
        r1.add(new ThemeDescription(null, 0, null, null, new Drawable[]{this.scrollSlidingTextTabStrip.getSelectorDrawable()}, null, r17));
        r1.addAll(this.imagesSearch.getThemeDescriptions());
        r1.addAll(this.gifsSearch.getThemeDescriptions());
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r1) {
        return this.swipeBackEnabled;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onConfigurationChanged(Configuration r2) {
        super.onConfigurationChanged(r2);
        PhotoPickerActivity r0 = this.imagesSearch;
        if (r0 == null) goto L5;
        r0.onConfigurationChanged(r2);
    L5:
        PhotoPickerActivity r1 = this.gifsSearch;
        if (r1 == null) goto L9;
        r1.onConfigurationChanged(r2);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        PhotoPickerActivity r0 = this.imagesSearch;
        if (r0 == null) goto L5;
        r0.onFragmentDestroy();
    L5:
        PhotoPickerActivity r02 = this.gifsSearch;
        if (r02 == null) goto L8;
        r02.onFragmentDestroy();
    L8:
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        PhotoPickerActivity r0 = this.imagesSearch;
        if (r0 == null) goto L5;
        r0.onPause();
    L5:
        PhotoPickerActivity r1 = this.gifsSearch;
        if (r1 == null) goto L9;
        r1.onPause();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ActionBarMenuItem r0 = this.searchItem;
        if (r0 == null) goto L5;
        r0.openSearch(true);
        getParentActivity().getWindow().setSoftInputMode(32);
    L5:
        PhotoPickerActivity r02 = this.imagesSearch;
        if (r02 == null) goto L8;
        r02.onResume();
    L8:
        PhotoPickerActivity r2 = this.gifsSearch;
        if (r2 == null) goto L12;
        r2.onResume();
        return;
    }

    public void setCaption(CharSequence r1) {
        PhotoPickerActivity r0 = this.imagesSearch;
        if (r0 == null) goto L6;
        r0.setCaption(r1);
        return;
    }

    public void setDelegate(PhotoPickerActivity.PhotoPickerActivityDelegate r2) {
        this.imagesSearch.setDelegate(r2);
        this.gifsSearch.setDelegate(r2);
        this.imagesSearch.setSearchDelegate(new AnonymousClass7(this));
        this.gifsSearch.setSearchDelegate(new AnonymousClass8(this));
    }

    public void setMaxSelectedPhotos(int r2, boolean r3) {
        this.imagesSearch.setMaxSelectedPhotos(r2, r3);
        this.gifsSearch.setMaxSelectedPhotos(r2, r3);
    }
}
