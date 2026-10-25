package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Property;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScrollSlidingTextTabStrip;

/* loaded from: classes6.dex */
public class DialogOrContactPickerActivity extends BaseFragment {
    private static final Interpolator interpolator = null;
    private boolean animatingForward;
    private boolean backAnimation;
    private Paint backgroundPaint;
    private ContactsActivity contactsActivity;
    private DialogsActivity dialogsActivity;
    private int maximumVelocity;
    private ScrollSlidingTextTabStrip scrollSlidingTextTabStrip;
    private ActionBarMenuItem searchItem;
    private boolean swipeBackEnabled;
    private AnimatorSet tabsAnimation;
    private boolean tabsAnimationInProgress;
    private ViewPage[] viewPages;

    /* renamed from: org.telegram.ui.DialogOrContactPickerActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ DialogOrContactPickerActivity this$0;

        public AnonymousClass1(DialogOrContactPickerActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.DialogOrContactPickerActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ DialogOrContactPickerActivity this$0;

        public AnonymousClass2(DialogOrContactPickerActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            DialogOrContactPickerActivity.m14319$$Nest$fgetdialogsActivity(this.this$0).getActionBar().closeSearchField(false);
            DialogOrContactPickerActivity.m14318$$Nest$fgetcontactsActivity(this.this$0).getActionBar().closeSearchField(false);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            DialogOrContactPickerActivity.m14319$$Nest$fgetdialogsActivity(this.this$0).getActionBar().openSearchField(_UrlKt.FRAGMENT_ENCODE_SET, false);
            DialogOrContactPickerActivity.m14318$$Nest$fgetcontactsActivity(this.this$0).getActionBar().openSearchField(_UrlKt.FRAGMENT_ENCODE_SET, false);
            DialogOrContactPickerActivity.m14322$$Nest$fgetsearchItem(this.this$0).getSearchField().requestFocus();
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r3) {
            DialogOrContactPickerActivity.m14319$$Nest$fgetdialogsActivity(this.this$0).getActionBar().setSearchFieldText(r3.getText().toString());
            DialogOrContactPickerActivity.m14318$$Nest$fgetcontactsActivity(this.this$0).getActionBar().setSearchFieldText(r3.getText().toString());
        }
    }

    /* renamed from: org.telegram.ui.DialogOrContactPickerActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate {
        final /* synthetic */ DialogOrContactPickerActivity this$0;

        public AnonymousClass3(DialogOrContactPickerActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate
        public void onPageScrolled(float r7) {
            if (r7 == 1.0f) goto L5;
        L7:
            boolean r2 = DialogOrContactPickerActivity.m14315$$Nest$fgetanimatingForward(this.this$0);
            DialogOrContactPickerActivity r3 = this.this$0;
            if (r2 == false) goto L10;
            DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(r3)[0].setTranslationX((-r7) * DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth());
            DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1].setTranslationX(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth() - (r7 * DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth()));
        L11:
            if (r7 != 1.0f) goto L14;
            ViewPage r72 = DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0];
            DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0] = DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1];
            DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1] = r72;
            DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1].setVisibility(8);
            return;
        L14:
            return;
        L10:
            DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(r3)[0].setTranslationX(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth() * r7);
            DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1].setTranslationX((r7 * DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth()) - DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth());
            goto L11
        L5:
            if (DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1].getVisibility() == 0) goto L7;
        }

        @Override // org.telegram.ui.Components.ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate
        public void onPageSelected(int r5, boolean r6) {
            if (ViewPage.m14341$$Nest$fgetselectedType(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0]) != r5) goto L5;
            return;
        L5:
            DialogOrContactPickerActivity r0 = this.this$0;
            if (r5 != DialogOrContactPickerActivity.m14321$$Nest$fgetscrollSlidingTextTabStrip(r0).getFirstTabId()) goto L8;
            boolean r2 = true;
        L9:
            DialogOrContactPickerActivity.m14328$$Nest$fputswipeBackEnabled(r0, r2);
            ViewPage.m14347$$Nest$fputselectedType(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1], r5);
            DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1].setVisibility(0);
            DialogOrContactPickerActivity.m14332$$Nest$mswitchToCurrentSelectedMode(this.this$0, true);
            DialogOrContactPickerActivity.m14326$$Nest$fputanimatingForward(this.this$0, r6);
            return;
        L8:
            r2 = false;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.DialogOrContactPickerActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends FrameLayout {
        private boolean globalIgnoreLayout;
        private boolean maybeStartTracking;
        private boolean startedTracking;
        private int startedTrackingPointerId;
        private int startedTrackingX;
        private int startedTrackingY;
        final /* synthetic */ DialogOrContactPickerActivity this$0;
        private VelocityTracker velocityTracker;

        /* renamed from: org.telegram.ui.DialogOrContactPickerActivity$4$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass4 this$1;

            public AnonymousClass1(AnonymousClass4 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r6) {
                DialogOrContactPickerActivity.m14329$$Nest$fputtabsAnimation(this.this$1.this$0, null);
                boolean r62 = DialogOrContactPickerActivity.m14316$$Nest$fgetbackAnimation(this.this$1.this$0);
                AnonymousClass4 r0 = this.this$1;
                if (r62 == false) goto L5;
                DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(r0.this$0)[1].setVisibility(8);
            L10:
                DialogOrContactPickerActivity.m14330$$Nest$fputtabsAnimationInProgress(this.this$1.this$0, false);
                AnonymousClass4.m14334$$Nest$fputmaybeStartTracking(this.this$1, false);
                AnonymousClass4.m14335$$Nest$fputstartedTracking(this.this$1, false);
                DialogOrContactPickerActivity.access$1100(this.this$1.this$0).setEnabled(true);
                DialogOrContactPickerActivity.m14321$$Nest$fgetscrollSlidingTextTabStrip(this.this$1.this$0).setEnabled(true);
                return;
            L5:
                ViewPage r63 = DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(r0.this$0)[0];
                DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$1.this$0)[0] = DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$1.this$0)[1];
                DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$1.this$0)[1] = r63;
                DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$1.this$0)[1].setVisibility(8);
                DialogOrContactPickerActivity r64 = this.this$1.this$0;
                if (ViewPage.m14341$$Nest$fgetselectedType(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(r64)[0]) != DialogOrContactPickerActivity.m14321$$Nest$fgetscrollSlidingTextTabStrip(this.this$1.this$0).getFirstTabId()) goto L8;
                boolean r02 = true;
            L9:
                DialogOrContactPickerActivity.m14328$$Nest$fputswipeBackEnabled(r64, r02);
                DialogOrContactPickerActivity.m14321$$Nest$fgetscrollSlidingTextTabStrip(this.this$1.this$0).selectTabWithId(ViewPage.m14341$$Nest$fgetselectedType(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$1.this$0)[0]), 1.0f);
                goto L10
            L8:
                r02 = false;
                goto L9
            }
        }

        /* renamed from: -$$Nest$fputmaybeStartTracking, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14334$$Nest$fputmaybeStartTracking(AnonymousClass4 r0, boolean r1) {
            r0.maybeStartTracking = r1;
        }

        /* renamed from: -$$Nest$fputstartedTracking, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14335$$Nest$fputstartedTracking(AnonymousClass4 r0, boolean r1) {
            r0.startedTracking = r1;
        }

        public AnonymousClass4(DialogOrContactPickerActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private boolean prepareForMoving(MotionEvent r5, boolean r6) {
            int r0 = DialogOrContactPickerActivity.m14321$$Nest$fgetscrollSlidingTextTabStrip(this.this$0).getNextPageId(r6);
            if (r0 >= 0) goto L5;
            return false;
        L5:
            getParent().requestDisallowInterceptTouchEvent(true);
            this.maybeStartTracking = false;
            this.startedTracking = true;
            this.startedTrackingX = (int) r5.getX();
            DialogOrContactPickerActivity.access$000(this.this$0).setEnabled(false);
            DialogOrContactPickerActivity.m14321$$Nest$fgetscrollSlidingTextTabStrip(this.this$0).setEnabled(false);
            ViewPage.m14347$$Nest$fputselectedType(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1], r0);
            DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1].setVisibility(0);
            DialogOrContactPickerActivity.m14326$$Nest$fputanimatingForward(this.this$0, r6);
            DialogOrContactPickerActivity.m14332$$Nest$mswitchToCurrentSelectedMode(this.this$0, true);
            DialogOrContactPickerActivity r52 = this.this$0;
            if (r6 == false) goto L8;
            DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(r52)[1].setTranslationX(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth());
        L9:
            return true;
        L8:
            DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(r52)[1].setTranslationX(-DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth());
            goto L9
        }

        public boolean checkTabsAnimationInProgress() {
            if (DialogOrContactPickerActivity.m14324$$Nest$fgettabsAnimationInProgress(this.this$0) == false) goto L25;
            boolean r0 = DialogOrContactPickerActivity.m14316$$Nest$fgetbackAnimation(this.this$0);
            DialogOrContactPickerActivity r2 = this.this$0;
            int r3 = -1;
            if (r0 == false) goto L13;
            if (Math.abs(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(r2)[0].getTranslationX()) >= 1.0f) goto L24;
            DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].setTranslationX(0.0f);
            ViewPage r02 = DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1];
            int r22 = DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth();
            if (DialogOrContactPickerActivity.m14315$$Nest$fgetanimatingForward(this.this$0) == false) goto L11;
            r3 = 1;
        L11:
            r02.setTranslationX(r22 * r3);
        L20:
            if (DialogOrContactPickerActivity.m14323$$Nest$fgettabsAnimation(this.this$0) == null) goto L22;
            DialogOrContactPickerActivity.m14323$$Nest$fgettabsAnimation(this.this$0).cancel();
            DialogOrContactPickerActivity.m14329$$Nest$fputtabsAnimation(this.this$0, null);
        L22:
            DialogOrContactPickerActivity.m14330$$Nest$fputtabsAnimationInProgress(this.this$0, false);
        L24:
            return DialogOrContactPickerActivity.m14324$$Nest$fgettabsAnimationInProgress(this.this$0);
        L13:
            if (Math.abs(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(r2)[1].getTranslationX()) >= 1.0f) goto L24;
            ViewPage r03 = DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0];
            int r23 = DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth();
            if (DialogOrContactPickerActivity.m14315$$Nest$fgetanimatingForward(this.this$0) == true) goto L18;
            r3 = 1;
        L18:
            r03.setTranslationX(r23 * r3);
            DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1].setTranslationX(0.0f);
            goto L20
        L25:
            return false;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r3) {
            super.dispatchDraw(r3);
            if (DialogOrContactPickerActivity.access$400(this.this$0) == null) goto L6;
            DialogOrContactPickerActivity.access$700(this.this$0).drawHeaderShadow(r3, DialogOrContactPickerActivity.access$500(this.this$0).getMeasuredHeight() + ((int) DialogOrContactPickerActivity.access$600(this.this$0).getTranslationY()));
            return;
        }

        @Override // android.view.View
        public void forceHasOverlappingRendering(boolean r1) {
            super.forceHasOverlappingRendering(r1);
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            DialogOrContactPickerActivity.m14317$$Nest$fgetbackgroundPaint(this.this$0).setColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r9.drawRect(0.0f, DialogOrContactPickerActivity.access$800(this.this$0).getMeasuredHeight() + DialogOrContactPickerActivity.access$900(this.this$0).getTranslationY(), getMeasuredWidth(), getMeasuredHeight(), DialogOrContactPickerActivity.m14317$$Nest$fgetbackgroundPaint(this.this$0));
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r2) {
            if (checkTabsAnimationInProgress() == false) goto L5;
            return true;
        L5:
            if (DialogOrContactPickerActivity.m14321$$Nest$fgetscrollSlidingTextTabStrip(this.this$0).isAnimatingIndicator() == false) goto L7;
            return true;
        L7:
            if (onTouchEvent(r2) == true) goto L14;
            return false;
        L14:
            return true;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r14, int r15) {
            setMeasuredDimension(View.MeasureSpec.getSize(r14), View.MeasureSpec.getSize(r15));
            measureChildWithMargins(DialogOrContactPickerActivity.access$100(this.this$0), r14, 0, r15, 0);
            int r13 = DialogOrContactPickerActivity.access$200(this.this$0).getMeasuredHeight();
            this.globalIgnoreLayout = true;
            int r142 = 0;
            int r152 = 0;
        L4:
            if (r152 >= DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0).length) goto L15;
            if (DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[r152] == null) goto L14;
            if (ViewPage.m14338$$Nest$fgetlistView(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[r152]) == null) goto L12;
            ViewPage.m14338$$Nest$fgetlistView(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[r152]).setPadding(0, r13, 0, 0);
        L12:
            if (ViewPage.m14339$$Nest$fgetlistView2(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[r152]) == null) goto L14;
            ViewPage.m14339$$Nest$fgetlistView2(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[r152]).setPadding(0, r13, 0, 0);
        L14:
            r152 = r152 + 1;
            goto L4
        L15:
            this.globalIgnoreLayout = false;
            int r132 = getChildCount();
        L16:
            if (r142 >= r132) goto L26;
            View r8 = getChildAt(r142);
            if (r8 == null) goto L25;
            if (r8.getVisibility() == 8) goto L25;
            if (r8 == DialogOrContactPickerActivity.access$300(this.this$0)) goto L25;
            measureChildWithMargins(r8, r14, 0, r15, 0);
        L25:
            r142 = r142 + 1;
            goto L16
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r12) {
            boolean r1 = false;
            if (DialogOrContactPickerActivity.access$1000(this.this$0).checkTransitionAnimation() == false) goto L5;
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
            if (DialogOrContactPickerActivity.m14315$$Nest$fgetanimatingForward(this.this$0) == false) goto L32;
            if (r2 <= 0) goto L32;
        L34:
            if (r2 >= 0) goto L36;
            boolean r5 = true;
        L38:
            if (prepareForMoving(r12, r5) == true) goto L46;
            this.maybeStartTracking = true;
            this.startedTracking = false;
            DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].setTranslationX(0.0f);
            ViewPage r52 = DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1];
            boolean r6 = DialogOrContactPickerActivity.m14315$$Nest$fgetanimatingForward(this.this$0);
            DialogOrContactPickerActivity r7 = this.this$0;
            if (r6 == false) goto L43;
            int r62 = DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(r7)[0].getMeasuredWidth();
        L44:
            r52.setTranslationX(r62);
            DialogOrContactPickerActivity.m14321$$Nest$fgetscrollSlidingTextTabStrip(this.this$0).selectTabWithId(ViewPage.m14341$$Nest$fgetselectedType(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1]), 0.0f);
            goto L46
        L43:
            r62 = -DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(r7)[0].getMeasuredWidth();
            goto L44
        L36:
            r5 = false;
        L32:
            if (DialogOrContactPickerActivity.m14315$$Nest$fgetanimatingForward(this.this$0) == true) goto L46;
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
            DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].setTranslationX(r2);
            boolean r122 = DialogOrContactPickerActivity.m14315$$Nest$fgetanimatingForward(this.this$0);
            DialogOrContactPickerActivity r3 = this.this$0;
            if (r122 == false) goto L61;
            DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(r3)[1].setTranslationX(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth() + r2);
        L62:
            DialogOrContactPickerActivity.m14321$$Nest$fgetscrollSlidingTextTabStrip(this.this$0).selectTabWithId(ViewPage.m14341$$Nest$fgetselectedType(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1]), Math.abs(r2) / DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth());
            goto L119
        L61:
            DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(r3)[1].setTranslationX(r2 - DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth());
        L64:
            if (r12 != null) goto L66;
        L73:
            this.velocityTracker.computeCurrentVelocity(1000, DialogOrContactPickerActivity.m14320$$Nest$fgetmaximumVelocity(this.this$0));
            if (r12 != null) goto L76;
        L88:
            float r42 = 0.0f;
            float r53 = 0.0f;
        L90:
            if (this.startedTracking == false) goto L114;
            float r123 = DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getX();
            DialogOrContactPickerActivity.m14329$$Nest$fputtabsAnimation(this.this$0, new AnimatorSet());
            DialogOrContactPickerActivity r63 = this.this$0;
            if (Math.abs(r123) < (DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth() / 3.0f)) goto L94;
        L98:
            boolean r54 = false;
        L99:
            DialogOrContactPickerActivity.m14327$$Nest$fputbackAnimation(r63, r54);
            boolean r55 = DialogOrContactPickerActivity.m14316$$Nest$fgetbackAnimation(this.this$0);
            Property r64 = View.TRANSLATION_X;
            if (r55 == false) goto L105;
            float r124 = Math.abs(r123);
            boolean r56 = DialogOrContactPickerActivity.m14315$$Nest$fgetanimatingForward(this.this$0);
            DialogOrContactPickerActivity r72 = this.this$0;
            if (r56 == false) goto L104;
            DialogOrContactPickerActivity.m14323$$Nest$fgettabsAnimation(r72).playTogether(new Animator[]{ObjectAnimator.ofFloat(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0], r64, new float[]{0.0f}), ObjectAnimator.ofFloat(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1], r64, new float[]{DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1].getMeasuredWidth()})});
        L109:
            DialogOrContactPickerActivity.m14323$$Nest$fgettabsAnimation(this.this$0).setInterpolator(DialogOrContactPickerActivity.m14333$$Nest$sfgetinterpolator());
            int r22 = getMeasuredWidth();
            float r57 = r22 / 2;
            float r58 = r57 + (AndroidUtilities.distanceInfluenceForSnapDuration(Math.min(1.0f, (r124 * 1.0f) / r22)) * r57);
            float r23 = Math.abs(r42);
            if (r23 <= 0.0f) goto L112;
            int r125 = Math.round(Math.abs(r58 / r23) * 1000.0f) * 4;
        L113:
            DialogOrContactPickerActivity.m14323$$Nest$fgettabsAnimation(this.this$0).setDuration(Math.max(150, Math.min(r125, MediaError.DetailedErrorCode.TEXT_UNKNOWN)));
            DialogOrContactPickerActivity.m14323$$Nest$fgettabsAnimation(this.this$0).addListener(new AnonymousClass1(this));
            DialogOrContactPickerActivity.m14323$$Nest$fgettabsAnimation(this.this$0).start();
            DialogOrContactPickerActivity.m14330$$Nest$fputtabsAnimationInProgress(this.this$0, true);
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
            DialogOrContactPickerActivity.m14323$$Nest$fgettabsAnimation(r72).playTogether(new Animator[]{ObjectAnimator.ofFloat(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0], r64, new float[]{0.0f}), ObjectAnimator.ofFloat(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1], r64, new float[]{-DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1].getMeasuredWidth()})});
            goto L109
        L105:
            r124 = DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth() - Math.abs(r123);
            boolean r59 = DialogOrContactPickerActivity.m14315$$Nest$fgetanimatingForward(this.this$0);
            DialogOrContactPickerActivity r73 = this.this$0;
            if (r59 == false) goto L108;
            DialogOrContactPickerActivity.m14323$$Nest$fgettabsAnimation(r73).playTogether(new Animator[]{ObjectAnimator.ofFloat(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0], r64, new float[]{-DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth()}), ObjectAnimator.ofFloat(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1], r64, new float[]{0.0f})});
            goto L109
        L108:
            DialogOrContactPickerActivity.m14323$$Nest$fgettabsAnimation(r73).playTogether(new Animator[]{ObjectAnimator.ofFloat(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0], r64, new float[]{DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth()}), ObjectAnimator.ofFloat(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1], r64, new float[]{0.0f})});
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
            DialogOrContactPickerActivity.access$1200(this.this$0).setEnabled(true);
            DialogOrContactPickerActivity.m14321$$Nest$fgetscrollSlidingTextTabStrip(this.this$0).setEnabled(true);
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

    /* renamed from: org.telegram.ui.DialogOrContactPickerActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends ViewPage {
        final /* synthetic */ DialogOrContactPickerActivity this$0;

        public AnonymousClass5(DialogOrContactPickerActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setTranslationX(float r3) {
            super.setTranslationX(r3);
            if (DialogOrContactPickerActivity.m14324$$Nest$fgettabsAnimationInProgress(this.this$0) == true) goto L5;
            return;
        L5:
            if (DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0] != this) goto L9;
            DialogOrContactPickerActivity.m14321$$Nest$fgetscrollSlidingTextTabStrip(this.this$0).selectTabWithId(ViewPage.m14341$$Nest$fgetselectedType(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[1]), Math.abs(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getTranslationX()) / DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0].getMeasuredWidth());
            return;
        }
    }

    /* renamed from: org.telegram.ui.DialogOrContactPickerActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends RecyclerView.OnScrollListener {
        final /* synthetic */ DialogOrContactPickerActivity this$0;
        final /* synthetic */ RecyclerView.OnScrollListener val$onScrollListener;

        public AnonymousClass6(DialogOrContactPickerActivity r1, RecyclerView.OnScrollListener r2) {
            this.this$0 = r1;
            this.val$onScrollListener = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r4, int r5) {
            this.val$onScrollListener.onScrollStateChanged(r4, r5);
            if (r5 == 1) goto L17;
            int r42 = (int) (-DialogOrContactPickerActivity.access$1300(this.this$0).getTranslationY());
            int r52 = ActionBar.getCurrentActionBarHeight();
            if (r42 == 0) goto L18;
            if (r42 == r52) goto L19;
            int r0 = r52 / 2;
            DialogOrContactPickerActivity r1 = this.this$0;
            if (r42 >= r0) goto L13;
            int r43 = -r42;
            ViewPage.m14338$$Nest$fgetlistView(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(r1)[0]).smoothScrollBy(0, r43);
            if (ViewPage.m14339$$Nest$fgetlistView2(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0]) == null) goto L20;
            ViewPage.m14339$$Nest$fgetlistView2(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0]).smoothScrollBy(0, r43);
            return;
        L20:
            return;
        L13:
            int r53 = r52 - r42;
            ViewPage.m14338$$Nest$fgetlistView(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(r1)[0]).smoothScrollBy(0, r53);
            if (ViewPage.m14339$$Nest$fgetlistView2(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0]) == null) goto L21;
            ViewPage.m14339$$Nest$fgetlistView2(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0]).smoothScrollBy(0, r53);
            return;
        L21:
            return;
        L19:
            return;
        L18:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            this.val$onScrollListener.onScrolled(r2, r3, r4);
            if (r2 != ViewPage.m14338$$Nest$fgetlistView(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0])) goto L5;
        L6:
            float r22 = DialogOrContactPickerActivity.access$1400(this.this$0).getTranslationY();
            float r32 = r22 - r4;
            if (r32 >= (-ActionBar.getCurrentActionBarHeight())) goto L10;
            r32 = -ActionBar.getCurrentActionBarHeight();
        L13:
            if (r32 == r22) goto L17;
            DialogOrContactPickerActivity.m14331$$Nest$msetScrollY(this.this$0, r32);
            return;
        L17:
            return;
        L10:
            if (r32 <= 0.0f) goto L13;
            r32 = 0.0f;
            goto L13
        L5:
            if (r2 == ViewPage.m14339$$Nest$fgetlistView2(DialogOrContactPickerActivity.m14325$$Nest$fgetviewPages(this.this$0)[0])) goto L6;
        }
    }

    public static class ViewPage extends FrameLayout {
        private ActionBar actionBar;
        private FrameLayout fragmentView;
        private RecyclerListView listView;
        private RecyclerListView listView2;
        private BaseFragment parentFragment;
        private int selectedType;

        /* renamed from: -$$Nest$fgetactionBar, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ActionBar m14336$$Nest$fgetactionBar(ViewPage r0) {
            return r0.actionBar;
        }

        /* renamed from: -$$Nest$fgetfragmentView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m14337$$Nest$fgetfragmentView(ViewPage r0) {
            return r0.fragmentView;
        }

        /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RecyclerListView m14338$$Nest$fgetlistView(ViewPage r0) {
            return r0.listView;
        }

        /* renamed from: -$$Nest$fgetlistView2, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RecyclerListView m14339$$Nest$fgetlistView2(ViewPage r0) {
            return r0.listView2;
        }

        /* renamed from: -$$Nest$fgetparentFragment, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BaseFragment m14340$$Nest$fgetparentFragment(ViewPage r0) {
            return r0.parentFragment;
        }

        /* renamed from: -$$Nest$fgetselectedType, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m14341$$Nest$fgetselectedType(ViewPage r0) {
            return r0.selectedType;
        }

        /* renamed from: -$$Nest$fputactionBar, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14342$$Nest$fputactionBar(ViewPage r0, ActionBar r1) {
            r0.actionBar = r1;
        }

        /* renamed from: -$$Nest$fputfragmentView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14343$$Nest$fputfragmentView(ViewPage r0, FrameLayout r1) {
            r0.fragmentView = r1;
        }

        /* renamed from: -$$Nest$fputlistView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14344$$Nest$fputlistView(ViewPage r0, RecyclerListView r1) {
            r0.listView = r1;
        }

        /* renamed from: -$$Nest$fputlistView2, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14345$$Nest$fputlistView2(ViewPage r0, RecyclerListView r1) {
            r0.listView2 = r1;
        }

        /* renamed from: -$$Nest$fputparentFragment, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14346$$Nest$fputparentFragment(ViewPage r0, BaseFragment r1) {
            r0.parentFragment = r1;
        }

        /* renamed from: -$$Nest$fputselectedType, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14347$$Nest$fputselectedType(ViewPage r0, int r1) {
            r0.selectedType = r1;
        }

        public ViewPage(Context r1) {
            super(r1);
        }
    }

    public static /* synthetic */ float $r8$lambda$WPiIeeW8Ap_FcV7NtOQ6L2oUzsY(float r2) {
        float r22 = r2 - 1.0f;
        return ((((r22 * r22) * r22) * r22) * r22) + 1.0f;
    }

    /* renamed from: $r8$lambda$j9CmMM8FdM3Da-Wm78Gt23cHuCg, reason: not valid java name */
    public static /* synthetic */ boolean m14313$r8$lambda$j9CmMM8FdM3DaWm78Gt23cHuCg(DialogOrContactPickerActivity r0, DialogsActivity r1, ArrayList r2, CharSequence r3, boolean r4, boolean r5, int r6, int r7, TopicsFragment r8) {
        return r0.lambda$new$1(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    /* renamed from: $r8$lambda$mA-l6c87Jm_tZ6xrSa4D54wnYhU, reason: not valid java name */
    public static /* synthetic */ void m14314$r8$lambda$mAl6c87Jm_tZ6xrSa4D54wnYhU(DialogOrContactPickerActivity r0, TLRPC.User r1, AlertDialog r2, int r3) {
        r0.lambda$showBlockAlert$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$pOoGcMWm6HrAbA6ZwAeeztsd1H0(DialogOrContactPickerActivity r0, TLRPC.User r1, String r2, ContactsActivity r3) {
        r0.lambda$new$2(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetanimatingForward, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14315$$Nest$fgetanimatingForward(DialogOrContactPickerActivity r0) {
        return r0.animatingForward;
    }

    /* renamed from: -$$Nest$fgetbackAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14316$$Nest$fgetbackAnimation(DialogOrContactPickerActivity r0) {
        return r0.backAnimation;
    }

    /* renamed from: -$$Nest$fgetbackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m14317$$Nest$fgetbackgroundPaint(DialogOrContactPickerActivity r0) {
        return r0.backgroundPaint;
    }

    /* renamed from: -$$Nest$fgetcontactsActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ContactsActivity m14318$$Nest$fgetcontactsActivity(DialogOrContactPickerActivity r0) {
        return r0.contactsActivity;
    }

    /* renamed from: -$$Nest$fgetdialogsActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DialogsActivity m14319$$Nest$fgetdialogsActivity(DialogOrContactPickerActivity r0) {
        return r0.dialogsActivity;
    }

    /* renamed from: -$$Nest$fgetmaximumVelocity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14320$$Nest$fgetmaximumVelocity(DialogOrContactPickerActivity r0) {
        return r0.maximumVelocity;
    }

    /* renamed from: -$$Nest$fgetscrollSlidingTextTabStrip, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ScrollSlidingTextTabStrip m14321$$Nest$fgetscrollSlidingTextTabStrip(DialogOrContactPickerActivity r0) {
        return r0.scrollSlidingTextTabStrip;
    }

    /* renamed from: -$$Nest$fgetsearchItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m14322$$Nest$fgetsearchItem(DialogOrContactPickerActivity r0) {
        return r0.searchItem;
    }

    /* renamed from: -$$Nest$fgettabsAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m14323$$Nest$fgettabsAnimation(DialogOrContactPickerActivity r0) {
        return r0.tabsAnimation;
    }

    /* renamed from: -$$Nest$fgettabsAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14324$$Nest$fgettabsAnimationInProgress(DialogOrContactPickerActivity r0) {
        return r0.tabsAnimationInProgress;
    }

    /* renamed from: -$$Nest$fgetviewPages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPage[] m14325$$Nest$fgetviewPages(DialogOrContactPickerActivity r0) {
        return r0.viewPages;
    }

    /* renamed from: -$$Nest$fputanimatingForward, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14326$$Nest$fputanimatingForward(DialogOrContactPickerActivity r0, boolean r1) {
        r0.animatingForward = r1;
    }

    /* renamed from: -$$Nest$fputbackAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14327$$Nest$fputbackAnimation(DialogOrContactPickerActivity r0, boolean r1) {
        r0.backAnimation = r1;
    }

    /* renamed from: -$$Nest$fputswipeBackEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14328$$Nest$fputswipeBackEnabled(DialogOrContactPickerActivity r0, boolean r1) {
        r0.swipeBackEnabled = r1;
    }

    /* renamed from: -$$Nest$fputtabsAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14329$$Nest$fputtabsAnimation(DialogOrContactPickerActivity r0, AnimatorSet r1) {
        r0.tabsAnimation = r1;
    }

    /* renamed from: -$$Nest$fputtabsAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14330$$Nest$fputtabsAnimationInProgress(DialogOrContactPickerActivity r0, boolean r1) {
        r0.tabsAnimationInProgress = r1;
    }

    /* renamed from: -$$Nest$msetScrollY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14331$$Nest$msetScrollY(DialogOrContactPickerActivity r0, float r1) {
        r0.setScrollY(r1);
    }

    /* renamed from: -$$Nest$mswitchToCurrentSelectedMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14332$$Nest$mswitchToCurrentSelectedMode(DialogOrContactPickerActivity r0, boolean r1) {
        r0.switchToCurrentSelectedMode(r1);
    }

    /* renamed from: -$$Nest$sfgetinterpolator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Interpolator m14333$$Nest$sfgetinterpolator() {
        return interpolator;
    }

    static {
        interpolator = new DialogOrContactPickerActivity$$ExternalSyntheticLambda2();
    }

    public DialogOrContactPickerActivity() {
        this.backgroundPaint = new Paint();
        this.viewPages = new ViewPage[2];
        this.swipeBackEnabled = true;
        Bundle r1 = new Bundle();
        r1.putBoolean("onlySelect", true);
        r1.putBoolean("checkCanWrite", false);
        r1.putBoolean("resetDelegate", false);
        r1.putInt("dialogsType", 9);
        DialogsActivity r4 = new DialogsActivity(r1);
        this.dialogsActivity = r4;
        r4.setDelegate(new DialogOrContactPickerActivity$$ExternalSyntheticLambda0(this));
        this.dialogsActivity.onFragmentCreate();
        Bundle r12 = new Bundle();
        r12.putBoolean("onlyUsers", true);
        r12.putBoolean("destroyAfterSelect", true);
        r12.putBoolean("returnAsResult", true);
        r12.putBoolean("disableSections", true);
        r12.putBoolean("needFinishFragment", false);
        r12.putBoolean("resetDelegate", false);
        r12.putBoolean("allowSelf", false);
        ContactsActivity r0 = new ContactsActivity(r12);
        this.contactsActivity = r0;
        r0.setDelegate(new DialogOrContactPickerActivity$$ExternalSyntheticLambda1(this));
        this.contactsActivity.onFragmentCreate();
    }

    public static /* synthetic */ ActionBar access$000(DialogOrContactPickerActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(DialogOrContactPickerActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ INavigationLayout access$1000(DialogOrContactPickerActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ ActionBar access$1100(DialogOrContactPickerActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$1200(DialogOrContactPickerActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$1300(DialogOrContactPickerActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$1400(DialogOrContactPickerActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$200(DialogOrContactPickerActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$300(DialogOrContactPickerActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ INavigationLayout access$400(DialogOrContactPickerActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ ActionBar access$500(DialogOrContactPickerActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$600(DialogOrContactPickerActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ INavigationLayout access$700(DialogOrContactPickerActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ ActionBar access$800(DialogOrContactPickerActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$900(DialogOrContactPickerActivity r0) {
        return r0.actionBar;
    }

    private /* synthetic */ boolean lambda$new$1(DialogsActivity r1, ArrayList r2, CharSequence r3, boolean r4, boolean r5, int r6, int r7, TopicsFragment r8) {
        if (r2.isEmpty() == false) goto L5;
        return true;
    L5:
        long r12 = ((MessagesStorage.TopicKey) r2.get(0)).dialogId;
        if (DialogObject.isUserDialog(r12) == true) goto L8;
        return true;
    L8:
        showBlockAlert(getMessagesController().getUser(Long.valueOf(r12)));
        return true;
    }

    private /* synthetic */ void lambda$new$2(TLRPC.User r1, String r2, ContactsActivity r3) {
        showBlockAlert(r1);
    }

    private /* synthetic */ void lambda$showBlockAlert$3(TLRPC.User r3, AlertDialog r4, int r5) {
        if (MessagesController.isSupportUser(r3) == false) goto L5;
        AlertsCreator.showSimpleToast(this, LocaleController.getString(R.string.ErrorOccurred));
    L6:
        finishFragment();
        return;
    L5:
        MessagesController.getInstance(this.currentAccount).blockPeer(r3.id);
        AlertsCreator.showSimpleToast(this, LocaleController.getString(R.string.UserBlocked));
        goto L6
    }

    private void setScrollY(float r4) {
        this.actionBar.setTranslationY(r4);
        int r0 = 0;
    L3:
        ViewPage[] r1 = this.viewPages;
        if (r0 >= r1.length) goto L9;
        int r2 = (int) r4;
        ViewPage.m14338$$Nest$fgetlistView(r1[r0]).setPinnedSectionOffsetY(r2);
        if (ViewPage.m14339$$Nest$fgetlistView2(this.viewPages[r0]) == null) goto L8;
        ViewPage.m14339$$Nest$fgetlistView2(this.viewPages[r0]).setPinnedSectionOffsetY(r2);
    L8:
        r0 = r0 + 1;
        goto L3
    L9:
        this.fragmentView.invalidate();
    }

    private void showBlockAlert(TLRPC.User r5) {
        if (r5 == null) goto L9;
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setTitle(LocaleController.getString(R.string.BlockUser));
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("AreYouSureBlockContact2", R.string.AreYouSureBlockContact2, new Object[]{ContactsController.formatName(r5.first_name, r5.last_name)})));
        r0.setPositiveButton(LocaleController.getString(R.string.BlockContact), new DialogOrContactPickerActivity$$ExternalSyntheticLambda3(this, r5));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r52 = r0.create();
        showDialog(r52);
        TextView r4 = (TextView) r52.getButton(-1);
        if (r4 == null) goto L8;
        r4.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L8:
        return;
    }

    private void switchToCurrentSelectedMode(boolean r6) {
        int r1 = 0;
    L3:
        ViewPage[] r2 = this.viewPages;
        if (r1 >= r2.length) goto L9;
        ViewPage.m14338$$Nest$fgetlistView(r2[r1]).stopScroll();
        if (ViewPage.m14339$$Nest$fgetlistView2(this.viewPages[r1]) == null) goto L8;
        ViewPage.m14339$$Nest$fgetlistView2(this.viewPages[r1]).stopScroll();
    L8:
        r1 = r1 + 1;
        goto L3
    L9:
        int r12 = 0;
    L11:
        if (r12 >= 2) goto L22;
        ViewPage[] r22 = this.viewPages;
        if (r12 != 0) goto L15;
        RecyclerListView r23 = ViewPage.m14338$$Nest$fgetlistView(r22[r6 ? 1 : 0]);
    L16:
        if (r23 == null) goto L21;
        r23.getAdapter();
        r23.setPinnedHeaderShadowDrawable(null);
        if (this.actionBar.getTranslationY() == 0.0f) goto L21;
        ((LinearLayoutManager) r23.getLayoutManager()).scrollToPositionWithOffset(0, (int) this.actionBar.getTranslationY());
    L21:
        r12 = r12 + 1;
        goto L11
    L15:
        r23 = ViewPage.m14339$$Nest$fgetlistView2(r22[r6 ? 1 : 0]);
        goto L16
    }

    private void updateTabs() {
        ScrollSlidingTextTabStrip r0 = this.scrollSlidingTextTabStrip;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.addTextTab(0, LocaleController.getString(R.string.BlockUserChatsTitle));
        this.scrollSlidingTextTabStrip.addTextTab(1, LocaleController.getString(R.string.BlockUserContactsTitle));
        this.scrollSlidingTextTabStrip.setVisibility(0);
        this.actionBar.setExtraHeight(AndroidUtilities.dp(44.0f));
        int r02 = this.scrollSlidingTextTabStrip.getCurrentTabId();
        if (r02 < 0) goto L8;
        ViewPage.m14347$$Nest$fputselectedType(this.viewPages[0], r02);
    L8:
        this.scrollSlidingTextTabStrip.finishAddingTabs();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r11) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setTitle(LocaleController.getString(R.string.BlockUserMultiTitle));
        INavigationLayout r0 = this.parentLayout;
        boolean r1 = false;
        if (r0 != null) goto L5;
    L7:
        this.actionBar.setExtraHeight(AndroidUtilities.dp(44.0f));
        this.actionBar.setAllowOverlayTitle(false);
        this.actionBar.setAddToContainer(false);
        this.actionBar.setClipContent(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.hasOwnBackground = true;
        ActionBarMenuItem r02 = this.actionBar.createMenu().addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass2(this));
        this.searchItem = r02;
        r02.setSearchFieldHint(LocaleController.getString(R.string.Search));
        ScrollSlidingTextTabStrip r03 = new ScrollSlidingTextTabStrip(r11);
        this.scrollSlidingTextTabStrip = r03;
        r03.setUseSameWidth(true);
        this.actionBar.addView(this.scrollSlidingTextTabStrip, LayoutHelper.createFrame(-1, 44, 83));
        this.scrollSlidingTextTabStrip.setDelegate(new AnonymousClass3(this));
        this.maximumVelocity = ViewConfiguration.get(r11).getScaledMaximumFlingVelocity();
        AnonymousClass4 r04 = new AnonymousClass4(this, r11);
        this.fragmentView = r04;
        r04.setWillNotDraw(false);
        this.dialogsActivity.setParentFragment(this);
        this.contactsActivity.setParentFragment(this);
        int r3 = 0;
    L8:
        ViewPage[] r4 = this.viewPages;
        if (r3 >= r4.length) goto L27;
        r4[r3] = new AnonymousClass5(this, r11);
        r04.addView(this.viewPages[r3], LayoutHelper.createFrame(-1, -1.0f));
        if (r3 != 0) goto L13;
        ViewPage.m14346$$Nest$fputparentFragment(this.viewPages[r3], this.dialogsActivity);
        ViewPage.m14344$$Nest$fputlistView(this.viewPages[r3], this.dialogsActivity.getListView());
        ViewPage.m14345$$Nest$fputlistView2(this.viewPages[r3], this.dialogsActivity.getSearchListView());
    L15:
        ViewPage.m14338$$Nest$fgetlistView(this.viewPages[r3]).setScrollingTouchSlop(1);
        ViewPage r8 = this.viewPages[r3];
        ViewPage.m14343$$Nest$fputfragmentView(r8, (FrameLayout) ViewPage.m14340$$Nest$fgetparentFragment(r8).getFragmentView());
        ViewPage r82 = this.viewPages[r3];
        ViewPage.m14342$$Nest$fputactionBar(r82, ViewPage.m14340$$Nest$fgetparentFragment(r82).getActionBar());
        ViewPage r83 = this.viewPages[r3];
        r83.addView(ViewPage.m14337$$Nest$fgetfragmentView(r83), LayoutHelper.createFrame(-1, -1.0f));
        AndroidUtilities.removeFromParent(ViewPage.m14336$$Nest$fgetactionBar(this.viewPages[r3]));
        ViewPage r5 = this.viewPages[r3];
        r5.addView(ViewPage.m14336$$Nest$fgetactionBar(r5), LayoutHelper.createFrame(-1, -2.0f));
        ViewPage.m14336$$Nest$fgetactionBar(this.viewPages[r3]).setVisibility(8);
        int r42 = 0;
    L17:
        if (r42 >= 2) goto L26;
        ViewPage[] r52 = this.viewPages;
        if (r42 != 0) goto L21;
        RecyclerListView r53 = ViewPage.m14338$$Nest$fgetlistView(r52[r3]);
    L22:
        if (r53 == null) goto L25;
        r53.setClipToPadding(false);
        r53.setOnScrollListener(new AnonymousClass6(this, r53.getOnScrollListener()));
    L25:
        r42 = r42 + 1;
        goto L17
    L21:
        r53 = ViewPage.m14339$$Nest$fgetlistView2(r52[r3]);
        goto L22
    L26:
        r3 = r3 + 1;
        goto L8
    L13:
        if (r3 != 1) goto L15;
        ViewPage.m14346$$Nest$fputparentFragment(this.viewPages[r3], this.contactsActivity);
        ViewPage.m14344$$Nest$fputlistView(this.viewPages[r3], this.contactsActivity.getListView());
        this.viewPages[r3].setVisibility(8);
        goto L15
    L27:
        r04.addView(this.actionBar, LayoutHelper.createFrame(-1, -2.0f));
        updateTabs();
        switchToCurrentSelectedMode(false);
        if (this.scrollSlidingTextTabStrip.getCurrentTabId() != this.scrollSlidingTextTabStrip.getFirstTabId()) goto L30;
        r1 = true;
    L30:
        this.swipeBackEnabled = r1;
        return this.fragmentView;
    L5:
        if (r0.isLayersLayout() == false) goto L7;
        this.actionBar.setOccupyStatusBar(false);
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.fragmentView, 0, null, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.scrollSlidingTextTabStrip.getTabsContainer(), ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{TextView.class}, null, null, null, Theme.key_actionBarTabActiveText));
        r1.add(new ThemeDescription(this.scrollSlidingTextTabStrip.getTabsContainer(), ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{TextView.class}, null, null, null, Theme.key_actionBarTabUnactiveText));
        r1.add(new ThemeDescription(this.scrollSlidingTextTabStrip.getTabsContainer(), ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, new Class[]{TextView.class}, null, null, null, Theme.key_actionBarTabLine));
        r1.add(new ThemeDescription(null, 0, null, null, new Drawable[]{this.scrollSlidingTextTabStrip.getSelectorDrawable()}, null, Theme.key_actionBarTabSelector));
        r1.addAll(this.dialogsActivity.getThemeDescriptions());
        r1.addAll(this.contactsActivity.getThemeDescriptions());
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r1) {
        return this.swipeBackEnabled;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        DialogsActivity r0 = this.dialogsActivity;
        if (r0 == null) goto L5;
        r0.onFragmentDestroy();
    L5:
        ContactsActivity r02 = this.contactsActivity;
        if (r02 == null) goto L8;
        r02.onFragmentDestroy();
    L8:
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        DialogsActivity r0 = this.dialogsActivity;
        if (r0 == null) goto L5;
        r0.onPause();
    L5:
        ContactsActivity r1 = this.contactsActivity;
        if (r1 == null) goto L9;
        r1.onPause();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        DialogsActivity r0 = this.dialogsActivity;
        if (r0 == null) goto L5;
        r0.onResume();
    L5:
        ContactsActivity r1 = this.contactsActivity;
        if (r1 == null) goto L9;
        r1.onResume();
        return;
    }
}
