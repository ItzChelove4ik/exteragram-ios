package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ColorPicker;
import org.telegram.ui.Components.ImageUpdater;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.SelectAnimatedEmojiDialog;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class AvatarConstructorFragment extends BaseFragment {
    public static final int[][] defaultColors = null;
    public static final int[][] premiumColors = null;
    Paint actionBarPaint;
    CanvasButton avatarClickableArea;
    final ImageUpdater.AvatarFor avatarFor;
    private BackgroundSelectView backgroundSelectView;
    private FrameLayout bottomBulletinContainer;
    private BottomSheet bottomSheet;
    private ButtonWithCounterView button;
    private boolean buttonIsLocked;
    private CharSequence buttonText;
    private TextView chooseBackgroundHint;
    private TextView chooseEmojiHint;
    int collapsedHeight;
    BackgroundGradient colorPickerGradient;
    boolean colorPickerInAnimatoin;
    View colorPickerPreviewView;
    Delegate delegate;
    boolean drawForBlur;
    ValueAnimator expandAnimator;
    boolean expandWithKeyboard;
    int expandedHeight;
    public boolean finishOnDone;
    boolean forGroup;
    private boolean forUser;
    private int gradientBackgroundItemWidth;
    ImageUpdater imageUpdater;
    boolean isLandscapeMode;
    boolean isLightInternal;
    ValueAnimator keyboardVisibilityAnimator;
    boolean keyboardVisible;
    float keyboardVisibleProgress;
    ValueAnimator lightProgressAnimator;
    LinearLayout linearLayout;
    private CharSequence lockedButtonText;
    protected ActionBar overlayActionBar;
    PreviewView previewView;
    float progressToExpand;
    float progressToLightStatusBar;
    private SelectAnimatedEmojiDialog selectAnimatedEmojiDialog;
    private ActionBarMenuItem setPhotoItem;
    boolean wasChanged;

    /* renamed from: org.telegram.ui.Components.AvatarConstructorFragment$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ AvatarConstructorFragment this$0;

        public AnonymousClass1(AvatarConstructorFragment r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            AvatarConstructorFragment.m8945$$Nest$mdiscardEditor(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.AvatarConstructorFragment$10, reason: invalid class name */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ AvatarConstructorFragment this$0;
        final /* synthetic */ boolean val$expanded;
        final /* synthetic */ boolean val$fromClick;

        public AnonymousClass10(AvatarConstructorFragment r1, boolean r2, boolean r3) {
            this.this$0 = r1;
            this.val$expanded = r2;
            this.val$fromClick = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            AvatarConstructorFragment r32 = this.this$0;
            r32.expandAnimator = null;
            if (this.val$expanded == false) goto L5;
            float r0 = 1.0f;
        L6:
            AvatarConstructorFragment.m8949$$Nest$msetProgressToExpand(r32, r0, false);
            if (this.val$fromClick == false) goto L10;
            PreviewView r33 = this.this$0.previewView;
            r33.overrideExpandProgress = -1.0f;
            r33.setExpanded(this.val$expanded);
            return;
        L10:
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.AvatarConstructorFragment$11, reason: invalid class name */
    public class AnonymousClass11 extends BottomSheet {
        final /* synthetic */ AvatarConstructorFragment this$0;

        /* renamed from: org.telegram.ui.Components.AvatarConstructorFragment$11$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass11 this$1;

            public AnonymousClass1(AnonymousClass11 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                AvatarConstructorFragment r0 = this.this$1.this$0;
                r0.colorPickerInAnimatoin = false;
                r0.colorPickerPreviewView.setVisibility(8);
            }
        }

        public AnonymousClass11(AvatarConstructorFragment r1, Context r2, boolean r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
        public void dismiss() {
            super.dismiss();
            AvatarConstructorFragment.m8936$$Nest$fgetbackgroundSelectView(this.this$0).selectGradient(this.this$0.colorPickerGradient);
            AvatarConstructorFragment r0 = this.this$0;
            r0.colorPickerInAnimatoin = true;
            r0.fragmentView.invalidate();
            this.this$0.colorPickerPreviewView.animate().setListener(new AnonymousClass1(this)).alpha(0.0f).setDuration(200).start();
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public void dismissInternal() {
            super.dismissInternal();
            AndroidUtilities.requestAdjustResize(this.this$0.getParentActivity(), this.this$0.getClassGuid());
            AvatarConstructorFragment.m8942$$Nest$fputbottomSheet(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.AvatarConstructorFragment$12, reason: invalid class name */
    public class AnonymousClass12 extends AnimatorListenerAdapter {
        final /* synthetic */ AvatarConstructorFragment this$0;

        public AnonymousClass12(AvatarConstructorFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            super.onAnimationEnd(r1);
            this.this$0.colorPickerInAnimatoin = false;
        }
    }

    /* renamed from: org.telegram.ui.Components.AvatarConstructorFragment$13, reason: invalid class name */
    public class AnonymousClass13 extends ColorPicker {
        final /* synthetic */ AvatarConstructorFragment this$0;

        public AnonymousClass13(AvatarConstructorFragment r1, Context r2, boolean r3, ColorPicker.ColorPickerDelegate r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(300.0f), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Components.AvatarConstructorFragment$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ AvatarConstructorFragment this$0;

        public AnonymousClass2(AvatarConstructorFragment r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            AvatarConstructorFragment.m8945$$Nest$mdiscardEditor(this.this$0);
        L6:
            if (r2 != 1) goto L9;
            AvatarConstructorFragment.m8946$$Nest$monDonePressed(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.AvatarConstructorFragment$3, reason: invalid class name */
    public class AnonymousClass3 extends LinearLayout {
        final /* synthetic */ AvatarConstructorFragment this$0;

        public AnonymousClass3(AvatarConstructorFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if (r3 != this.this$0.previewView) goto L7;
            return true;
        L7:
            return super.drawChild(r2, r3, r4);
        }
    }

    /* renamed from: org.telegram.ui.Components.AvatarConstructorFragment$4, reason: invalid class name */
    public class AnonymousClass4 extends ContainerLayout {
        boolean isScrolling;
        boolean maybeScroll;
        float scrollFromX;
        float scrollFromY;
        float startFromProgressToExpand;
        final /* synthetic */ AvatarConstructorFragment this$0;

        public AnonymousClass4(AvatarConstructorFragment r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r11) {
            int r0 = r11.save();
            super.dispatchDraw(r11);
            AvatarConstructorFragment r1 = this.this$0;
            if (r1.isLandscapeMode == true) goto L20;
            if (r1.drawForBlur == true) goto L7;
            r11.save();
            float r12 = this.this$0.linearLayout.getX() + this.this$0.previewView.getX();
            float r2 = this.this$0.linearLayout.getY() + this.this$0.previewView.getY();
            AvatarConstructorFragment r3 = this.this$0;
            int r4 = r3.expandedHeight - r3.collapsedHeight;
            int r32 = AndroidUtilities.statusBarHeight;
            int r5 = ActionBar.getCurrentActionBarHeight();
            float r22 = AndroidUtilities.lerp(r2, r32 + ((r5 - r6.collapsedHeight) >> 1), this.this$0.keyboardVisibleProgress);
            r11.translate(r12, r22);
            this.this$0.previewView.draw(r11);
            float r42 = r4 / 2.0f;
            AndroidUtilities.rectTmp.set(r12, r22 - (this.this$0.progressToExpand * r42), r5.previewView.getMeasuredWidth() + r12, (this.this$0.previewView.getMeasuredHeight() + r22) + (r42 * this.this$0.progressToExpand));
            float r13 = r12 + PreviewView.m8953$$Nest$fgetcx(this.this$0.previewView);
            float r23 = r22 + PreviewView.m8954$$Nest$fgetcy(this.this$0.previewView);
            AvatarConstructorFragment r33 = this.this$0;
            r33.avatarClickableArea.setRect((int) (r13 - PreviewView.m8955$$Nest$fgetsize(r33.previewView)), (int) (r23 - PreviewView.m8955$$Nest$fgetsize(this.this$0.previewView)), (int) (r13 + PreviewView.m8955$$Nest$fgetsize(this.this$0.previewView)), (int) (r23 + PreviewView.m8955$$Nest$fgetsize(this.this$0.previewView)));
            r11.restore();
        L7:
            r11.restoreToCount(r0);
            float r02 = this.this$0.previewView.expandProgress.get();
            if (this.this$0.colorPickerPreviewView.getVisibility() != 0) goto L10;
            float r14 = this.this$0.colorPickerPreviewView.getAlpha();
        L11:
            float r03 = r02 * (1.0f - r14);
            AvatarConstructorFragment r24 = this.this$0;
            if (r03 == 0.0f) goto L18;
            r24.overlayActionBar.setVisibility(0);
            int r15 = r11.save();
            r11.translate(this.this$0.overlayActionBar.getX(), this.this$0.overlayActionBar.getY());
            if (r03 == 1.0f) goto L16;
            Canvas r34 = r11;
            r34.saveLayerAlpha(0.0f, 0.0f, this.this$0.overlayActionBar.getMeasuredWidth(), this.this$0.overlayActionBar.getMeasuredHeight(), (int) (r03 * 255.0f), 31);
        L17:
            this.this$0.overlayActionBar.draw(r34);
            r34.restoreToCount(r15);
            goto L20
        L16:
            r34 = r11;
            goto L17
        L18:
            r24.overlayActionBar.setVisibility(8);
            goto L20
        L10:
            r14 = 0.0f;
        L20:
            if (this.this$0.colorPickerInAnimatoin == false) goto L23;
            invalidate();
            return;
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r10, View r11, long r12) {
            AvatarConstructorFragment r0 = this.this$0;
            if (r11 != r0.overlayActionBar) goto L7;
            return true;
        L7:
            if (r11 != AvatarConstructorFragment.access$100(r0)) goto L11;
            AvatarConstructorFragment r02 = this.this$0;
            if (r02.keyboardVisibleProgress <= 0.0f) goto L11;
            r02.actionBarPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            AvatarConstructorFragment r03 = this.this$0;
            r03.actionBarPaint.setAlpha((int) (r03.keyboardVisibleProgress * 255.0f));
            Canvas r3 = r10;
            r3.drawRect(0.0f, 0.0f, r11.getMeasuredWidth(), r11.getMeasuredHeight(), this.this$0.actionBarPaint);
            this.this$0.getParentLayout().drawHeaderShadow(r3, (int) (this.this$0.keyboardVisibleProgress * 255.0f), r11.getMeasuredHeight());
        L13:
            return super.drawChild(r3, r11, r12);
        L11:
            r3 = r10;
            goto L13
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r3) {
            if (this.this$0.keyboardVisibleProgress != 0.0f) goto L7;
            return false;
        L7:
            return onTouchEvent(r3);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r8, int r9) {
            measureKeyboardHeight();
            boolean r2 = true;
            int r3 = 0;
            if (View.MeasureSpec.getSize(r8) <= (View.MeasureSpec.getSize(r9) + this.keyboardHeight)) goto L5;
            boolean r0 = true;
        L6:
            AvatarConstructorFragment r1 = this.this$0;
            if (r0 == r1.isLandscapeMode) goto L13;
            r1.isLandscapeMode = r0;
            AndroidUtilities.removeFromParent(r1.previewView);
            AndroidUtilities.requestAdjustNothing(this.this$0.getParentActivity(), this.this$0.getClassGuid());
            AvatarConstructorFragment r02 = this.this$0;
            if (r02.isLandscapeMode == false) goto L11;
            AvatarConstructorFragment.m8949$$Nest$msetProgressToExpand(r02, 0.0f, false);
            this.this$0.previewView.setExpanded(false);
            addView(this.this$0.previewView, 0, LayoutHelper.createFrame(-1, -1.0f));
        L12:
            AndroidUtilities.requestAdjustResize(this.this$0.getParentActivity(), this.this$0.getClassGuid());
            goto L13
        L11:
            r02.linearLayout.addView(r02.previewView, 0, LayoutHelper.createFrame(-1, -2.0f));
        L13:
            AvatarConstructorFragment r03 = this.this$0;
            if (r03.isLandscapeMode == false) goto L16;
            int r12 = (int) (View.MeasureSpec.getSize(r8) * 0.55f);
            ((ViewGroup.MarginLayoutParams) this.this$0.linearLayout.getLayoutParams()).bottomMargin = 0;
            ((ViewGroup.MarginLayoutParams) this.this$0.linearLayout.getLayoutParams()).leftMargin = (int) (View.MeasureSpec.getSize(r8) * 0.45f);
            ((ViewGroup.MarginLayoutParams) this.this$0.previewView.getLayoutParams()).rightMargin = r12;
            ((ViewGroup.MarginLayoutParams) AvatarConstructorFragment.m8937$$Nest$fgetbutton(this.this$0).getLayoutParams()).rightMargin = r12 + AndroidUtilities.dp(16.0f);
            ((ViewGroup.MarginLayoutParams) AvatarConstructorFragment.m8938$$Nest$fgetchooseBackgroundHint(this.this$0).getLayoutParams()).topMargin = 0;
            ((ViewGroup.MarginLayoutParams) AvatarConstructorFragment.m8939$$Nest$fgetchooseEmojiHint(this.this$0).getLayoutParams()).topMargin = AndroidUtilities.dp(10.0f);
        L17:
            AvatarConstructorFragment r04 = this.this$0;
            boolean r13 = r04.keyboardVisible;
            if (this.keyboardHeight >= AndroidUtilities.dp(20.0f)) goto L21;
            r2 = false;
        L21:
            r04.keyboardVisible = r2;
            if (r13 == this.this$0.keyboardVisible) goto L27;
            super.onMeasure(r8, r9);
            AvatarConstructorFragment r05 = this.this$0;
            if (r05.keyboardVisible == false) goto L26;
            r3 = ((-AvatarConstructorFragment.m8941$$Nest$fgetselectAnimatedEmojiDialog(r05).getTop()) + AvatarConstructorFragment.access$000(this.this$0).getMeasuredHeight()) + AndroidUtilities.dp(8.0f);
        L26:
            LinearLayout r06 = this.this$0.linearLayout;
            r06.setTranslationY((r06.getTranslationY() + ((ViewGroup.MarginLayoutParams) this.this$0.linearLayout.getLayoutParams()).topMargin) - r3);
            ((ViewGroup.MarginLayoutParams) this.this$0.linearLayout.getLayoutParams()).topMargin = r3;
            AvatarConstructorFragment r07 = this.this$0;
            AvatarConstructorFragment.m8944$$Nest$mcreateKeyboardVisibleAnimator(r07, r07.keyboardVisible);
        L27:
            super.onMeasure(r8, r9);
            AvatarConstructorFragment r82 = this.this$0;
            r82.collapsedHeight = r82.previewView.getMeasuredHeight();
            AvatarConstructorFragment r7 = this.this$0;
            r7.expandedHeight = r7.previewView.getMeasuredWidth();
            return;
        L16:
            ((ViewGroup.MarginLayoutParams) r03.linearLayout.getLayoutParams()).bottomMargin = AndroidUtilities.dp(64.0f);
            ((ViewGroup.MarginLayoutParams) this.this$0.linearLayout.getLayoutParams()).leftMargin = 0;
            ((ViewGroup.MarginLayoutParams) this.this$0.previewView.getLayoutParams()).rightMargin = 0;
            ((ViewGroup.MarginLayoutParams) AvatarConstructorFragment.m8937$$Nest$fgetbutton(this.this$0).getLayoutParams()).rightMargin = AndroidUtilities.dp(16.0f);
            ((ViewGroup.MarginLayoutParams) AvatarConstructorFragment.m8938$$Nest$fgetchooseBackgroundHint(this.this$0).getLayoutParams()).topMargin = AndroidUtilities.dp(10.0f);
            ((ViewGroup.MarginLayoutParams) AvatarConstructorFragment.m8939$$Nest$fgetchooseEmojiHint(this.this$0).getLayoutParams()).topMargin = AndroidUtilities.dp(18.0f);
            goto L17
        L5:
            r0 = false;
            goto L6
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r7) {
            if (this.this$0.avatarClickableArea.checkTouchEvent(r7) == false) goto L6;
            return true;
        L6:
            if (this.this$0.isLandscapeMode == true) goto L39;
            if (r7.getAction() != 0) goto L15;
            SelectAnimatedEmojiDialog r0 = AvatarConstructorFragment.m8941$$Nest$fgetselectAnimatedEmojiDialog(this.this$0);
            Rect r4 = AndroidUtilities.rectTmp2;
            r0.getHitRect(r4);
            r4.offset(0, (int) this.this$0.linearLayout.getY());
            if (this.this$0.keyboardVisibleProgress != 0.0f) goto L39;
            if (r4.contains((int) r7.getX(), (int) r7.getY()) == true) goto L39;
            this.maybeScroll = true;
            this.scrollFromX = r7.getX();
            this.scrollFromY = r7.getY();
            goto L39
        L15:
            if (r7.getAction() != 2) goto L27;
            boolean r02 = this.maybeScroll;
            if (r02 == false) goto L19;
        L20:
            float r42 = this.scrollFromY;
            if (r02 == true) goto L23;
            AvatarConstructorFragment.m8949$$Nest$msetProgressToExpand(this.this$0, Utilities.clamp(this.startFromProgressToExpand + ((-(r42 - r7.getY())) / this.this$0.expandedHeight), 1.0f, 0.0f), true);
            goto L39
        L23:
            if (Math.abs(r42 - r7.getY()) <= AndroidUtilities.touchSlop) goto L39;
            this.maybeScroll = false;
            this.isScrolling = true;
            this.startFromProgressToExpand = this.this$0.progressToExpand;
            this.scrollFromX = r7.getX();
            this.scrollFromY = r7.getY();
            goto L39
        L19:
            if (this.isScrolling == true) goto L20;
        L27:
            if (r7.getAction() == 1) goto L31;
            if (r7.getAction() != 3) goto L39;
        L31:
            if (this.isScrolling == false) goto L37;
            AvatarConstructorFragment r03 = this.this$0;
            if (r03.progressToExpand <= 0.5f) goto L35;
            boolean r3 = true;
        L36:
            AvatarConstructorFragment.m8947$$Nest$msetExpanded(r03, r3, false, false);
            goto L37
        L35:
            r3 = false;
        L37:
            this.maybeScroll = false;
            this.isScrolling = false;
        L39:
            if (this.isScrolling == false) goto L41;
        L46:
            return true;
        L41:
            if (super.onTouchEvent(r7) == true) goto L46;
            if (this.maybeScroll == true) goto L46;
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.AvatarConstructorFragment$5, reason: invalid class name */
    public class AnonymousClass5 extends PreviewView {
        final /* synthetic */ AvatarConstructorFragment this$0;
        final /* synthetic */ ContainerLayout val$nestedSizeNotifierLayout;

        public AnonymousClass5(AvatarConstructorFragment r1, Context r2, ContainerLayout r3) {
            this.this$0 = r1;
            this.val$nestedSizeNotifierLayout = r3;
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.AvatarConstructorFragment.PreviewView, android.view.View
        public void invalidate() {
            super.invalidate();
            this.val$nestedSizeNotifierLayout.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.AvatarConstructorFragment$6, reason: invalid class name */
    public class AnonymousClass6 extends FrameLayout {
        private Paint paint;
        private Path path;
        final /* synthetic */ AvatarConstructorFragment this$0;

        public AnonymousClass6(AvatarConstructorFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.path = new Path();
            this.paint = new Paint(1);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            Theme.applyDefaultShadow(this.paint);
            this.paint.setColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, this.this$0.getResourceProvider()));
            this.paint.setAlpha((int) (getAlpha() * 255.0f));
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            this.path.rewind();
            this.path.addRoundRect(r0, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), Path.Direction.CW);
            r6.drawPath(this.path, this.paint);
            super.dispatchDraw(r6);
        }
    }

    /* renamed from: org.telegram.ui.Components.AvatarConstructorFragment$7, reason: invalid class name */
    public class AnonymousClass7 extends SelectAnimatedEmojiDialog {
        private boolean firstLayout;
        final /* synthetic */ AvatarConstructorFragment this$0;

        public AnonymousClass7(AvatarConstructorFragment r1, BaseFragment r2, Context r3, boolean r4, Integer r5, int r6, boolean r7, Theme.ResourcesProvider r8, int r9, int r10) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8, r9, r10);
            this.firstLayout = true;
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog
        public void onEmojiSelected(View r3, Long r4, TLRPC.Document r5, TL_stars.TL_starGiftUnique r6, Integer r7) {
            boolean r32 = this.forUser;
            AvatarConstructorFragment r62 = this.this$0;
            if (r32 == false) goto L5;
            TLRPC.TL_emojiList r33 = MediaDataController.getInstance(AvatarConstructorFragment.access$200(r62)).profileAvatarConstructorDefault;
        L6:
            long r63 = 0;
            if (r33 == null) goto L14;
            if (r5 == null) goto L10;
            long r0 = r5.id;
        L13:
            boolean r34 = r33.document_id.contains(Long.valueOf(r0));
        L15:
            if (r4 == null) goto L18;
            r63 = r4.longValue();
        L18:
            AvatarConstructorFragment.m8948$$Nest$msetPreview(this.this$0, r34, r63, r5);
            return;
        L10:
            if (r4 == null) goto L12;
            r0 = r4.longValue();
            goto L13
        L12:
            r0 = 0;
            goto L13
        L14:
            r34 = false;
            goto L15
        L5:
            r33 = MediaDataController.getInstance(AvatarConstructorFragment.access$300(r62)).groupAvatarConstructorDefault;
            goto L6
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if (this.firstLayout == false) goto L6;
            this.firstLayout = false;
            AvatarConstructorFragment.m8941$$Nest$fgetselectAnimatedEmojiDialog(this.this$0).onShow(null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.AvatarConstructorFragment$8, reason: invalid class name */
    public class AnonymousClass8 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ AvatarConstructorFragment this$0;
        final /* synthetic */ boolean val$keyboardVisible;
        final /* synthetic */ float val$translationYFrom;
        final /* synthetic */ float val$translationYTo;

        public AnonymousClass8(AvatarConstructorFragment r1, float r2, float r3, boolean r4) {
            this.this$0 = r1;
            this.val$translationYFrom = r2;
            this.val$translationYTo = r3;
            this.val$keyboardVisible = r4;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r4) {
            AvatarConstructorFragment r0 = this.this$0;
            r0.keyboardVisibleProgress = ((Float) r4.getAnimatedValue()).floatValue();
            float r42 = AndroidUtilities.lerp(this.val$translationYFrom, this.val$translationYTo, this.this$0.keyboardVisibleProgress);
            AvatarConstructorFragment.access$400(this.this$0).getTitleTextView().setAlpha(this.this$0.keyboardVisibleProgress);
            AvatarConstructorFragment r02 = this.this$0;
            if (r02.expandWithKeyboard == true) goto L5;
        L7:
            this.this$0.linearLayout.setTranslationY(r42);
            AvatarConstructorFragment.m8937$$Nest$fgetbutton(this.this$0).setTranslationY(r42);
            this.this$0.fragmentView.invalidate();
            AvatarConstructorFragment.access$500(this.this$0).invalidate();
            return;
        L5:
            if (this.val$keyboardVisible == true) goto L7;
            AvatarConstructorFragment.m8949$$Nest$msetProgressToExpand(r02, 1.0f - r02.keyboardVisibleProgress, false);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Components.AvatarConstructorFragment$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ AvatarConstructorFragment this$0;

        public AnonymousClass9(AvatarConstructorFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            AvatarConstructorFragment r32 = this.this$0;
            if (r32.expandWithKeyboard == false) goto L5;
            float r0 = 1.0f;
        L6:
            AvatarConstructorFragment.m8949$$Nest$msetProgressToExpand(r32, r0, false);
            this.this$0.expandWithKeyboard = false;
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    public static class BackgroundGradient {
        int color1;
        int color2;
        int color3;
        int color4;
        public boolean premium;
        public int stableId;

        public BackgroundGradient() {
        }

        public int colorsCount() {
            if (this.color4 == 0) goto L7;
            return 4;
        L7:
            if (this.color3 == 0) goto L11;
            return 3;
        L11:
            if (this.color2 == 0) goto L14;
            return 2;
        L14:
            return 1;
        }

        public BackgroundGradient copy() {
            BackgroundGradient r0 = new BackgroundGradient();
            r0.color1 = this.color1;
            r0.color2 = this.color2;
            r0.color3 = this.color3;
            r0.color4 = this.color4;
            r0.premium = this.premium;
            return r0;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof BackgroundGradient) == true) goto L8;
            return false;
        L8:
            BackgroundGradient r52 = (BackgroundGradient) r5;
            if (this.color1 == r52.color1) goto L11;
        L17:
            return false;
        L11:
            if (this.color2 != r52.color2) goto L17;
            if (this.color3 != r52.color3) goto L17;
            if (this.color4 != r52.color4) goto L17;
            return true;
        }

        public int getAverageColor() {
            int r0 = this.color1;
            int r1 = this.color2;
            if (r1 == 0) goto L5;
            r0 = ColorUtils.blendARGB(r0, r1, 0.5f);
        L5:
            int r12 = this.color3;
            if (r12 == 0) goto L8;
            r0 = ColorUtils.blendARGB(r0, r12, 0.5f);
        L8:
            int r3 = this.color4;
            if (r3 != 0) goto L11;
            return r0;
        L11:
            return ColorUtils.blendARGB(r0, r3, 0.5f);
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Integer.valueOf(this.stableId), Integer.valueOf(this.color1), Integer.valueOf(this.color2), Integer.valueOf(this.color3), Integer.valueOf(this.color4)});
        }
    }

    public class BackgroundSelectView extends RecyclerListView {
        RecyclerView.Adapter adapter;
        BackgroundGradient customSelectedGradient;
        ArrayList<BackgroundGradient> gradients;
        int selectedItemId;
        int stableIdPointer;
        final /* synthetic */ AvatarConstructorFragment this$0;

        /* renamed from: org.telegram.ui.Components.AvatarConstructorFragment$BackgroundSelectView$1, reason: invalid class name */
        public class AnonymousClass1 extends RecyclerView.Adapter {
            final /* synthetic */ BackgroundSelectView this$1;
            final /* synthetic */ AvatarConstructorFragment val$this$0;

            public AnonymousClass1(BackgroundSelectView r1, AvatarConstructorFragment r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return this.this$1.gradients.size() + 1;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public long getItemId(int r2) {
                if (r2 < this.this$1.gradients.size()) goto L7;
                return 1;
            L7:
                return this.this$1.gradients.get(r2).stableId;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemViewType(int r1) {
                if (r1 < this.this$1.gradients.size()) goto L6;
                return 1;
            L6:
                return 0;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r5, int r6) {
                GradientSelectorView r0 = (GradientSelectorView) r5.itemView;
                boolean r1 = false;
                if (r5.getItemViewType() != 0) goto L15;
                r0.setCustom(false);
                BackgroundGradient r52 = this.this$1.gradients.get(r6);
                if (r52.premium == true) goto L7;
            L9:
                boolean r3 = false;
            L10:
                r0.setLocked(r3);
                r0.setGradient(r52);
                BackgroundSelectView r4 = this.this$1;
                if (r4.selectedItemId != r4.gradients.get(r6).stableId) goto L13;
                r1 = true;
            L13:
                r0.setSelectedInternal(r1, true);
                return;
            L7:
                if (UserConfig.getInstance(AvatarConstructorFragment.access$600(this.this$1.this$0)).isPremium() == true) goto L9;
                r3 = true;
                goto L10
            L15:
                r0.setCustom(true);
                r0.setLocked(!UserConfig.getInstance(AvatarConstructorFragment.access$700(this.this$1.this$0)).isPremium());
                r0.setGradient(this.this$1.customSelectedGradient);
                if (this.this$1.selectedItemId != 1) goto L18;
                r1 = true;
            L18:
                r0.setSelectedInternal(r1, true);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
                BackgroundSelectView r0 = this.this$1;
                return new RecyclerListView.Holder(new GradientSelectorView(r0.this$0, r0.getContext()));
            }
        }

        /* renamed from: $r8$lambda$Ate4-hnN1Rc2TCPnl5PxMWN-MOk, reason: not valid java name */
        public static /* synthetic */ void m8952$r8$lambda$Ate4hnN1Rc2TCPnl5PxMWNMOk(BackgroundSelectView r0, View r1, int r2) {
            r0.lambda$new$0(r1, r2);
        }

        public BackgroundSelectView(AvatarConstructorFragment r7, Context r8) {
            this.this$0 = r7;
            super(r8);
            this.gradients = new ArrayList();
            this.stableIdPointer = 200;
            this.selectedItemId = -1;
            LinearLayoutManager r0 = new LinearLayoutManager(r8);
            r0.setOrientation(0);
            setLayoutManager(r0);
            int r02 = 0;
        L3:
            int[][] r1 = AvatarConstructorFragment.defaultColors;
            if (r02 >= r1.length) goto L6;
            BackgroundGradient r2 = new BackgroundGradient();
            int r4 = this.stableIdPointer;
            this.stableIdPointer = r4 + 1;
            r2.stableId = r4;
            int[] r12 = r1[r02];
            r2.color1 = r12[0];
            r2.color2 = r12[1];
            r2.color3 = r12[2];
            r2.color4 = r12[3];
            this.gradients.add(r2);
            r02 = r02 + 1;
            goto L3
        L6:
            int r03 = 0;
        L7:
            int[][] r13 = AvatarConstructorFragment.premiumColors;
            if (r03 >= r13.length) goto L10;
            BackgroundGradient r22 = new BackgroundGradient();
            int r42 = this.stableIdPointer;
            this.stableIdPointer = r42 + 1;
            r22.stableId = r42;
            int[] r14 = r13[r03];
            r22.color1 = r14[0];
            r22.color2 = r14[1];
            r22.color3 = 0;
            r22.color4 = 0;
            r22.premium = true;
            this.gradients.add(r22);
            r03 = r03 + 1;
            goto L7
        L10:
            setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
            setClipToPadding(false);
            this.useLayoutPositionOnClick = true;
            setOnItemClickListener(new AvatarConstructorFragment$BackgroundSelectView$$ExternalSyntheticLambda0(this));
            RecyclerView.Adapter r82 = new AnonymousClass1(this, r7);
            this.adapter = r82;
            setAdapter(r82);
            setOverScrollMode(1);
        }

        private /* synthetic */ void lambda$new$0(View r2, int r3) {
            if ((r2 instanceof GradientSelectorView) == false) goto L12;
            GradientSelectorView r22 = (GradientSelectorView) r2;
            if (r22.isCustom == true) goto L12;
            BackgroundGradient r23 = r22.backgroundGradient;
            this.selectedItemId = r23.stableId;
            this.this$0.previewView.setGradient(r23, false);
            RecyclerView.Adapter r24 = this.adapter;
            if (r24 == null) goto L9;
            r24.notifyDataSetChanged();
        L9:
            AvatarConstructorFragment.m8951$$Nest$mupdateButton(this.this$0);
            return;
        L12:
            if (this.selectedItemId == 1) goto L20;
            BackgroundGradient r25 = this.customSelectedGradient;
            if (r25 == null) goto L20;
            this.selectedItemId = 1;
            this.this$0.previewView.setGradient(r25, true);
            RecyclerView.Adapter r26 = this.adapter;
            if (r26 == null) goto L18;
            r26.notifyDataSetChanged();
        L18:
            AvatarConstructorFragment.m8951$$Nest$mupdateButton(this.this$0);
            return;
        L20:
            AvatarConstructorFragment.m8950$$Nest$mshowColorPicker(this.this$0);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r5, int r6) {
            int r0 = View.MeasureSpec.getSize(r5);
            int r1 = this.adapter.getItemCount();
            AvatarConstructorFragment.m8943$$Nest$fputgradientBackgroundItemWidth(this.this$0, r0 / r1);
            int r02 = AvatarConstructorFragment.m8940$$Nest$fgetgradientBackgroundItemWidth(this.this$0);
            int r2 = AndroidUtilities.dp(39.0f);
            AvatarConstructorFragment r3 = this.this$0;
            if (r02 >= r2) goto L6;
            AvatarConstructorFragment.m8943$$Nest$fputgradientBackgroundItemWidth(r3, AndroidUtilities.dp(39.0f));
        L8:
            super.onMeasure(r5, r6);
            return;
        L6:
            if (AvatarConstructorFragment.m8940$$Nest$fgetgradientBackgroundItemWidth(r3) <= AndroidUtilities.dp(150.0f)) goto L8;
            AvatarConstructorFragment.m8943$$Nest$fputgradientBackgroundItemWidth(this.this$0, AndroidUtilities.dp(48.0f));
            goto L8
        }

        public void selectGradient(BackgroundGradient r3) {
            int r0 = 0;
        L4:
            if (r0 >= this.gradients.size()) goto L9;
            if (this.gradients.get(r0).equals(r3) == true) goto L7;
            r0 = r0 + 1;
            goto L4
        L7:
            this.selectedItemId = this.gradients.get(r0).stableId;
        L10:
            this.adapter.notifyDataSetChanged();
            return;
        L9:
            this.customSelectedGradient = r3;
            this.selectedItemId = 1;
            goto L10
        }
    }

    public class ContainerLayout extends SizeNotifierFrameLayout {
        private NestedScrollingParentHelper nestedScrollingParentHelper;
        final /* synthetic */ AvatarConstructorFragment this$0;

        public ContainerLayout(AvatarConstructorFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        }

        @Override // android.view.ViewGroup
        public int getNestedScrollAxes() {
            return this.nestedScrollingParentHelper.getNestedScrollAxes();
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public boolean onNestedFling(View r1, float r2, float r3, boolean r4) {
            return false;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public boolean onNestedPreFling(View r1, float r2, float r3) {
            return false;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void onNestedPreScroll(View r3, int r4, int r5, int[] r6) {
            AvatarConstructorFragment r32 = this.this$0;
            if (r32.keyboardVisibleProgress <= 0.0f) goto L5;
            return;
        L5:
            if (r32.isLandscapeMode == true) goto L15;
            if (r5 > 0) goto L9;
            return;
        L9:
            if (r32.progressToExpand <= 0.0f) goto L14;
            r32.cancelExpandAnimator();
            AvatarConstructorFragment.m8949$$Nest$msetProgressToExpand(this.this$0, Utilities.clamp(this.this$0.progressToExpand - (r5 / r3.expandedHeight), 1.0f, 0.0f), true);
            r6[1] = r5;
            return;
        L14:
            return;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void onNestedScroll(View r1, int r2, int r3, int r4, int r5) {
            AvatarConstructorFragment r12 = this.this$0;
            if (r12.keyboardVisibleProgress <= 0.0f) goto L5;
            return;
        L5:
            if (r12.isLandscapeMode == true) goto L12;
            if (r5 == 0) goto L11;
            r12.cancelExpandAnimator();
            AvatarConstructorFragment.m8949$$Nest$msetProgressToExpand(this.this$0, Utilities.clamp(this.this$0.progressToExpand - (r5 / r1.expandedHeight), 1.0f, 0.0f), true);
            return;
        L11:
            return;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void onNestedScrollAccepted(View r2, View r3, int r4) {
            this.nestedScrollingParentHelper.onNestedScrollAccepted(r2, r3, r4);
            this.this$0.cancelExpandAnimator();
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public boolean onStartNestedScroll(View r1, View r2, int r3) {
            AvatarConstructorFragment r0 = this.this$0;
            if (r0.keyboardVisibleProgress <= 0.0f) goto L5;
            return false;
        L5:
            if (r0.isLandscapeMode == true) goto L11;
            return true;
        L11:
            return false;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void onStopNestedScroll(View r2) {
            this.nestedScrollingParentHelper.onStopNestedScroll(r2);
            AvatarConstructorFragment r1 = this.this$0;
            if (r1.progressToExpand <= 0.5f) goto L5;
            boolean r22 = true;
        L6:
            AvatarConstructorFragment.m8947$$Nest$msetExpanded(r1, r22, false, false);
            return;
        L5:
            r22 = false;
            goto L6
        }
    }

    public interface Delegate {
        void onDone(BackgroundGradient r1, long r2, TLRPC.Document r4, PreviewView r5);
    }

    public class GradientSelectorView extends View {
        Drawable addIcon;
        BackgroundGradient backgroundGradient;
        Paint defaultPaint;
        GradientTools gradientTools;
        boolean isCustom;
        private boolean isLocked;
        Drawable lockIcon;
        boolean lockIconIsEmptyCustom;
        Paint optionsPaint;
        AnimatedFloat progressToSelect;
        boolean selected;
        final /* synthetic */ AvatarConstructorFragment this$0;

        public GradientSelectorView(AvatarConstructorFragment r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.progressToSelect = new AnimatedFloat(400, AndroidUtilities.overshootInterpolator);
            this.gradientTools = new GradientTools();
            this.progressToSelect.setParent(this);
        }

        @Override // android.view.View
        public void onDraw(Canvas r13) {
            super.onDraw(r13);
            AnimatedFloat r0 = this.progressToSelect;
            if (this.selected == false) goto L5;
            float r1 = 1.0f;
        L6:
            boolean r4 = false;
            r0.set(r1, false);
            float r02 = getMeasuredWidth() / 2.0f;
            float r5 = getMeasuredHeight() / 2.0f;
            BackgroundGradient r6 = this.backgroundGradient;
            if (r6 == null) goto L10;
            this.gradientTools.setColors(r6.color1, r6.color2, r6.color3, r6.color4);
            this.gradientTools.setBounds(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            Paint r62 = this.gradientTools.paint;
        L14:
            if (this.progressToSelect.get() != 0.0f) goto L16;
            r13.drawCircle(r02, r5, AndroidUtilities.dp(15.0f), r62);
        L17:
            int r8 = -1;
            if (this.isLocked == false) goto L40;
            if (this.lockIcon == null) goto L28;
            boolean r2 = this.lockIconIsEmptyCustom;
            if (this.isCustom == true) goto L24;
        L26:
            boolean r3 = false;
        L27:
            if (r2 != r3) goto L28;
        L37:
            this.lockIcon.setBounds((int) (r02 - (r2.getIntrinsicWidth() / 2.0f)), (int) (r5 - (this.lockIcon.getIntrinsicHeight() / 2.0f)), (int) ((this.lockIcon.getIntrinsicWidth() / 2.0f) + r02), (int) ((this.lockIcon.getIntrinsicHeight() / 2.0f) + r5));
            float r12 = AndroidUtilities.lerp(1.05f, 0.92f, this.progressToSelect.get());
            r13.save();
            r13.scale(r12, r12, r02, r5);
            this.lockIcon.draw(r13);
            r13.restore();
            return;
        L24:
            if (this.backgroundGradient != null) goto L26;
            r3 = true;
        L28:
            this.lockIcon = getContext().getResources().getDrawable(R.drawable.msg_mini_lock2).mutate();
            if (this.isCustom == true) goto L31;
        L33:
            this.lockIconIsEmptyCustom = r4;
            if (r4 == false) goto L36;
            r8 = Theme.getColor(Theme.key_chat_emojiSearchIcon);
        L36:
            this.lockIcon.setColorFilter(new PorterDuffColorFilter(r8, PorterDuff.Mode.SRC_IN));
            goto L37
        L31:
            if (this.backgroundGradient != null) goto L33;
            r4 = true;
            goto L33
        L40:
            if (this.isCustom == true) goto L42;
            return;
        L42:
            if (this.backgroundGradient != null) goto L49;
            if (this.addIcon != null) goto L46;
            Drawable r22 = ContextCompat.getDrawable(getContext(), R.drawable.msg_filled_plus);
            this.addIcon = r22;
            r22.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_emojiSearchIcon), PorterDuff.Mode.MULTIPLY));
        L46:
            this.addIcon.setBounds((int) (r02 - (r2.getIntrinsicWidth() / 2.0f)), (int) (r5 - (this.addIcon.getIntrinsicHeight() / 2.0f)), (int) (r02 + (this.addIcon.getIntrinsicWidth() / 2.0f)), (int) (r5 + (this.addIcon.getIntrinsicHeight() / 2.0f)));
            this.addIcon.draw(r13);
            return;
        L49:
            if (this.optionsPaint != null) goto L51;
            Paint r14 = new Paint(1);
            this.optionsPaint = r14;
            r14.setColor(-1);
        L51:
            this.optionsPaint.setAlpha(Math.round(Utilities.clamp(this.progressToSelect.get(), 1.0f, 0.0f) * 255.0f));
            r13.drawCircle(r02, r5, AndroidUtilities.dp(1.5f), this.optionsPaint);
            r13.drawCircle(r02 - (AndroidUtilities.dp(5.0f) * this.progressToSelect.get()), r5, AndroidUtilities.dp(1.5f), this.optionsPaint);
            r13.drawCircle(r02 + (AndroidUtilities.dp(5.0f) * this.progressToSelect.get()), r5, AndroidUtilities.dp(1.5f), this.optionsPaint);
            return;
        L16:
            r62.setStyle(Paint.Style.STROKE);
            r62.setStrokeWidth(AndroidUtilities.dp(2.0f));
            r13.drawCircle(r02, r5, AndroidUtilities.dpf2(13.5f), r62);
            r62.setStyle(Paint.Style.FILL);
            r13.drawCircle(r02, r5, AndroidUtilities.dp(10.0f) + (AndroidUtilities.dp(5.0f) * (1.0f - this.progressToSelect.get())), r62);
            goto L17
        L10:
            if (this.defaultPaint != null) goto L12;
            Paint r63 = new Paint(1);
            this.defaultPaint = r63;
            r63.setColor(Theme.getColor(Theme.key_chat_emojiPanelBackground));
        L12:
            r62 = this.defaultPaint;
            goto L14
        L5:
            r1 = 0.0f;
            goto L6
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(AvatarConstructorFragment.m8940$$Nest$fgetgradientBackgroundItemWidth(this.this$0), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
        }

        public void setCustom(boolean r1) {
            this.isCustom = r1;
        }

        public void setGradient(BackgroundGradient r1) {
            this.backgroundGradient = r1;
        }

        public void setLocked(boolean r2) {
            if (this.isLocked != r2) goto L5;
            return;
        L5:
            this.isLocked = r2;
            invalidate();
        }

        public void setSelectedInternal(boolean r2, boolean r3) {
            if (this.selected == r2) goto L5;
            this.selected = r2;
            invalidate();
        L5:
            if (r3 == true) goto L12;
            AnimatedFloat r1 = this.progressToSelect;
            if (r2 == false) goto L9;
            float r22 = 1.0f;
        L10:
            r1.set(r22, false);
            return;
        L9:
            r22 = 0.0f;
            goto L10
        }
    }

    public class PreviewView extends FrameLayout {
        BackgroundGradient backgroundGradient;
        BackupImageView backupImageView;
        float changeBackgroundProgress;
        private ColorFilter colorFilter;
        private float cx;
        private float cy;
        public TLRPC.Document document;
        public long documentId;
        AnimatedFloat expandProgress;
        boolean expanded;
        public boolean freeEmoji;
        GradientTools gradientTools;
        boolean isCustomGradient;
        GradientTools outGradientTools;
        float overrideExpandProgress;
        private float size;
        final /* synthetic */ AvatarConstructorFragment this$0;

        /* renamed from: org.telegram.ui.Components.AvatarConstructorFragment$PreviewView$1, reason: invalid class name */
        public class AnonymousClass1 extends BackupImageView {
            final /* synthetic */ PreviewView this$1;
            final /* synthetic */ AvatarConstructorFragment val$this$0;

            public AnonymousClass1(PreviewView r1, Context r2, AvatarConstructorFragment r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.view.View
            public void invalidate() {
                super.invalidate();
                this.this$1.invalidate();
            }

            @Override // android.view.View
            public void invalidate(int r1, int r2, int r3, int r4) {
                super.invalidate(r1, r2, r3, r4);
                this.this$1.invalidate();
            }

            @Override // android.view.View
            public void invalidate(Rect r1) {
                super.invalidate(r1);
                this.this$1.invalidate();
            }
        }

        /* renamed from: -$$Nest$fgetcx, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m8953$$Nest$fgetcx(PreviewView r0) {
            return r0.cx;
        }

        /* renamed from: -$$Nest$fgetcy, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m8954$$Nest$fgetcy(PreviewView r0) {
            return r0.cy;
        }

        /* renamed from: -$$Nest$fgetsize, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m8955$$Nest$fgetsize(PreviewView r0) {
            return r0.size;
        }

        public PreviewView(AvatarConstructorFragment r5, Context r6) {
            this.this$0 = r5;
            super(r6);
            this.gradientTools = new GradientTools();
            this.outGradientTools = new GradientTools();
            this.changeBackgroundProgress = 1.0f;
            this.colorFilter = new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN);
            this.expandProgress = new AnimatedFloat(this, 200, CubicBezierInterpolator.EASE_OUT);
            this.overrideExpandProgress = -1.0f;
            AnonymousClass1 r0 = new AnonymousClass1(this, r6, r5);
            this.backupImageView = r0;
            r0.getImageReceiver().setAutoRepeatCount(1);
            this.backupImageView.getImageReceiver().setAspectFit(true);
            setClipChildren(false);
            addView(this.backupImageView, LayoutHelper.createFrame(70, 70, 17));
        }

        private void drawBackround(Canvas r12, float r13, float r14, float r15, float r16, Paint r17) {
            float r11 = this.expandProgress.get();
            if (r11 != 0.0f) goto L6;
            float r112 = r16 * 2.0f;
            r12.drawRoundRect(r13 - r16, r14 - r16, r13 + r16, r14 + r16, ExteraConfig.getAvatarCorners(r112, true), ExteraConfig.getAvatarCorners(r112, true), r17);
            return;
        L6:
            float r113 = AndroidUtilities.lerp(ExteraConfig.getAvatarCorners(2.0f * r16, true), 0, r11);
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(r13 - r16, r14 - r16, r13 + r16, r14 + r16);
            r12.drawRoundRect(r0, r113, r113, r17);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r11) {
            this.cx = getMeasuredWidth() / 2.0f;
            this.cy = getMeasuredHeight() / 2.0f;
            if (this.this$0.isLandscapeMode == false) goto L6;
            float r0 = getMeasuredWidth() * 0.3f;
        L5:
            float r6 = r0;
            AnimatedFloat r02 = this.expandProgress;
            if (this.expanded == false) goto L10;
            float r2 = 1.0f;
        L11:
            r02.set(r2);
            float r03 = this.overrideExpandProgress;
            if (r03 < 0.0f) goto L14;
            this.expandProgress.set(r03, true);
        L14:
            float r04 = AndroidUtilities.lerp(r6, getMeasuredWidth() / 2.0f, this.expandProgress.get());
            this.size = r04;
            this.size = AndroidUtilities.lerp(r04, AndroidUtilities.dp(21.0f), this.this$0.keyboardVisibleProgress);
            this.cx = AndroidUtilities.lerp(this.cx, (getMeasuredWidth() - AndroidUtilities.dp(12.0f)) - AndroidUtilities.dp(21.0f), this.this$0.keyboardVisibleProgress);
            r11.save();
            AvatarConstructorFragment r05 = this.this$0;
            r11.clipRect(0.0f, (-r2) / 2.0f, getMeasuredWidth(), getMeasuredHeight() + (((r05.expandedHeight - r05.collapsedHeight) / 2.0f) * this.this$0.progressToExpand));
            BackgroundGradient r06 = this.backgroundGradient;
            if (r06 == null) goto L23;
            this.gradientTools.setColors(r06.color1, r06.color2, r06.color3, r06.color4);
            GradientTools r07 = this.gradientTools;
            float r22 = this.cx;
            float r3 = this.size;
            float r5 = this.cy;
            r07.setBounds(r22 - r3, r5 - r3, r22 + r3, r5 + r3);
            if (this.changeBackgroundProgress == 1.0f) goto L22;
            GradientTools r08 = this.outGradientTools;
            float r32 = this.cx;
            float r4 = this.size;
            float r7 = this.cy;
            r08.setBounds(r32 - r4, r7 - r4, r32 + r4, r7 + r4);
            this.outGradientTools.paint.setAlpha(255);
            Canvas r33 = r11;
            drawBackround(r33, this.cx, this.cy, r6, this.size, this.outGradientTools.paint);
            this.gradientTools.paint.setAlpha((int) (this.changeBackgroundProgress * 255.0f));
            drawBackround(r33, this.cx, this.cy, r6, this.size, this.gradientTools.paint);
            float r112 = this.changeBackgroundProgress + 0.064f;
            this.changeBackgroundProgress = r112;
            if (r112 <= 1.0f) goto L21;
            this.changeBackgroundProgress = 1.0f;
        L21:
            invalidate();
            PreviewView r23 = this;
        L25:
            if (r23.this$0.isLandscapeMode == false) goto L27;
            int r10 = (int) ((r6 * 2.0f) * 0.7f);
        L28:
            int r09 = (int) (r23.getMeasuredWidth() * 0.7f);
            float r102 = AndroidUtilities.lerp(AndroidUtilities.lerp(r10, r09, r23.expandProgress.get()), (int) (AndroidUtilities.dp(42.0f) * 0.7f), r23.this$0.keyboardVisibleProgress) / 2.0f;
            BackupImageView r113 = r23.backupImageView;
            AnimatedEmojiDrawable r010 = r113.animatedEmojiDrawable;
            if (r010 != null) goto L31;
            ImageReceiver r114 = r113.imageReceiver;
            float r011 = r23.cx - r102;
            float r52 = r23.cy - r102;
            float r103 = r102 * 2.0f;
            r114.setImageCoords(r011, r52, r103, r103);
            r23.backupImageView.imageReceiver.setRoundRadius((int) (r103 * 0.13f));
            r23.backupImageView.imageReceiver.draw(r33);
            return;
        L31:
            if (r010.getImageReceiver() == null) goto L33;
            r23.backupImageView.animatedEmojiDrawable.getImageReceiver().setRoundRadius((int) ((2.0f * r102) * 0.13f));
        L33:
            AnimatedEmojiDrawable r115 = r23.backupImageView.animatedEmojiDrawable;
            float r012 = r23.cx;
            float r42 = r23.cy;
            r115.setBounds((int) (r012 - r102), (int) (r42 - r102), (int) (r012 + r102), (int) (r42 + r102));
            r23.backupImageView.animatedEmojiDrawable.setColorFilter(r23.colorFilter);
            r23.backupImageView.animatedEmojiDrawable.draw(r33);
            return;
        L27:
            r10 = AndroidUtilities.dp(70.0f);
            goto L28
        L22:
            r33 = r11;
            this.gradientTools.paint.setAlpha(255);
            r23 = this;
            r23.drawBackround(r33, this.cx, this.cy, r6, this.size, this.gradientTools.paint);
            goto L25
        L23:
            r23 = this;
            r33 = r11;
            goto L25
        L10:
            r2 = 0.0f;
            goto L11
        L6:
            r0 = AndroidUtilities.dp(50.0f);
            goto L5
        }

        public long getDuration() {
            ImageReceiver r0 = this.backupImageView.getImageReceiver();
            AnimatedEmojiDrawable r3 = this.backupImageView.animatedEmojiDrawable;
            if (r3 == null) goto L6;
            r0 = r3.getImageReceiver();
        L6:
            if (r0 != null) goto L9;
            return 5000;
        L9:
            if (r0.getLottieAnimation() != null) goto L11;
            return 5000;
        L11:
            return r0.getLottieAnimation().getDuration();
        }

        public ImageReceiver getImageReceiver() {
            ImageReceiver r0 = this.backupImageView.getImageReceiver();
            AnimatedEmojiDrawable r1 = this.backupImageView.animatedEmojiDrawable;
            if (r1 == null) goto L6;
            ImageReceiver r02 = r1.getImageReceiver();
            this.backupImageView.animatedEmojiDrawable.setColorFilter(this.colorFilter);
            return r02;
        L6:
            return r0;
        }

        public boolean hasAnimation() {
            if (getImageReceiver().getAnimation() == null) goto L5;
            return true;
        L5:
            if (getImageReceiver().getLottieAnimation() != null) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            this.this$0.fragmentView.invalidate();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            if (this.this$0.isLandscapeMode == false) goto L6;
            super.onMeasure(r2, r3);
            return;
        L6:
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(140.0f), TLObject.FLAG_30));
        }

        public void setExpanded(boolean r3) {
            if (this.expanded != r3) goto L5;
            return;
        L5:
            this.expanded = r3;
            if (r3 == false) goto L13;
            AnimatedEmojiDrawable r32 = this.backupImageView.animatedEmojiDrawable;
            if (r32 != null) goto L10;
        L12:
            this.backupImageView.imageReceiver.startAnimation();
            goto L13
        L10:
            if (r32.getImageReceiver() == null) goto L12;
            this.backupImageView.animatedEmojiDrawable.getImageReceiver().startAnimation();
        L13:
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needCheckSystemBarColors, new Object[0]);
            invalidate();
        }

        public void setGradient(BackgroundGradient r6, boolean r7) {
            BackgroundGradient r0 = this.backgroundGradient;
            if (r0 == null) goto L5;
            this.outGradientTools.setColors(r0.color1, r0.color2, r0.color3, r0.color4);
            this.changeBackgroundProgress = 0.0f;
            this.this$0.wasChanged = true;
        L5:
            this.backgroundGradient = r6;
            this.isCustomGradient = r7;
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needCheckSystemBarColors, new Object[0]);
            invalidate();
        }
    }

    public static /* synthetic */ void $r8$lambda$03O1myZznNkrRmTFbjKjHyp4siE(AvatarConstructorFragment r0) {
        r0.lambda$createView$1();
    }

    /* renamed from: $r8$lambda$5a464XYkxCH0lu2wx1i_MVEnh-c, reason: not valid java name */
    public static /* synthetic */ void m8932$r8$lambda$5a464XYkxCH0lu2wx1i_MVEnhc(AvatarConstructorFragment r0, int r1, int r2, boolean r3) {
        r0.lambda$showColorPicker$5(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$6kYdyFotoCiobM-Rz79epqRyYKM, reason: not valid java name */
    public static /* synthetic */ void m8933$r8$lambda$6kYdyFotoCiobMRz79epqRyYKM(AvatarConstructorFragment r0) {
        r0.lambda$onDonePressed$3();
    }

    public static /* synthetic */ void $r8$lambda$GhftkL6meHv0dUp3vRJB1Ou0Dig(AvatarConstructorFragment r0, boolean r1, ValueAnimator r2) {
        r0.lambda$setExpanded$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$HvKJIgv55IxCfnJfMxobo2S55XY(AvatarConstructorFragment r0, AlertDialog r1, int r2) {
        r0.lambda$discardEditor$2(r1, r2);
    }

    /* renamed from: $r8$lambda$QiuU2N2VMKkxo-UWydCcl4FKA9M, reason: not valid java name */
    public static /* synthetic */ void m8934$r8$lambda$QiuU2N2VMKkxoUWydCcl4FKA9M(AvatarConstructorFragment r0, boolean[] r1, View r2) {
        r0.lambda$showColorPicker$6(r1, r2);
    }

    /* renamed from: $r8$lambda$VfaYcCBbtphcdsjDnN-bt99Phh0, reason: not valid java name */
    public static /* synthetic */ void m8935$r8$lambda$VfaYcCBbtphcdsjDnNbt99Phh0(AvatarConstructorFragment r0, View r1) {
        r0.lambda$createView$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$YDBYf1zv54hfAPsmz2duHTt59ig(AvatarConstructorFragment r0, AlertDialog r1, int r2) {
        r0.lambda$onBackPressed$8(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$rdrrIORwotlzU17zX04Ivp2WHeE(AvatarConstructorFragment r0, ValueAnimator r1) {
        r0.lambda$isLightStatusBar$7(r1);
    }

    /* renamed from: -$$Nest$fgetbackgroundSelectView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackgroundSelectView m8936$$Nest$fgetbackgroundSelectView(AvatarConstructorFragment r0) {
        return r0.backgroundSelectView;
    }

    /* renamed from: -$$Nest$fgetbutton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ButtonWithCounterView m8937$$Nest$fgetbutton(AvatarConstructorFragment r0) {
        return r0.button;
    }

    /* renamed from: -$$Nest$fgetchooseBackgroundHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m8938$$Nest$fgetchooseBackgroundHint(AvatarConstructorFragment r0) {
        return r0.chooseBackgroundHint;
    }

    /* renamed from: -$$Nest$fgetchooseEmojiHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m8939$$Nest$fgetchooseEmojiHint(AvatarConstructorFragment r0) {
        return r0.chooseEmojiHint;
    }

    /* renamed from: -$$Nest$fgetgradientBackgroundItemWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8940$$Nest$fgetgradientBackgroundItemWidth(AvatarConstructorFragment r0) {
        return r0.gradientBackgroundItemWidth;
    }

    /* renamed from: -$$Nest$fgetselectAnimatedEmojiDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SelectAnimatedEmojiDialog m8941$$Nest$fgetselectAnimatedEmojiDialog(AvatarConstructorFragment r0) {
        return r0.selectAnimatedEmojiDialog;
    }

    /* renamed from: -$$Nest$fputbottomSheet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8942$$Nest$fputbottomSheet(AvatarConstructorFragment r0, BottomSheet r1) {
        r0.bottomSheet = r1;
    }

    /* renamed from: -$$Nest$fputgradientBackgroundItemWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8943$$Nest$fputgradientBackgroundItemWidth(AvatarConstructorFragment r0, int r1) {
        r0.gradientBackgroundItemWidth = r1;
    }

    /* renamed from: -$$Nest$mcreateKeyboardVisibleAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8944$$Nest$mcreateKeyboardVisibleAnimator(AvatarConstructorFragment r0, boolean r1) {
        r0.createKeyboardVisibleAnimator(r1);
    }

    /* renamed from: -$$Nest$mdiscardEditor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8945$$Nest$mdiscardEditor(AvatarConstructorFragment r0) {
        r0.discardEditor();
    }

    /* renamed from: -$$Nest$monDonePressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8946$$Nest$monDonePressed(AvatarConstructorFragment r0) {
        r0.onDonePressed();
    }

    /* renamed from: -$$Nest$msetExpanded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8947$$Nest$msetExpanded(AvatarConstructorFragment r0, boolean r1, boolean r2, boolean r3) {
        r0.setExpanded(r1, r2, r3);
    }

    /* renamed from: -$$Nest$msetPreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8948$$Nest$msetPreview(AvatarConstructorFragment r0, boolean r1, long r2, TLRPC.Document r4) {
        r0.setPreview(r1, r2, r4);
    }

    /* renamed from: -$$Nest$msetProgressToExpand, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8949$$Nest$msetProgressToExpand(AvatarConstructorFragment r0, float r1, boolean r2) {
        r0.setProgressToExpand(r1, r2);
    }

    /* renamed from: -$$Nest$mshowColorPicker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8950$$Nest$mshowColorPicker(AvatarConstructorFragment r0) {
        r0.showColorPicker();
    }

    /* renamed from: -$$Nest$mupdateButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8951$$Nest$mupdateButton(AvatarConstructorFragment r0) {
        r0.updateButton();
    }

    static {
        defaultColors = new int[][]{new int[]{-11302949, -11562789, -10430789, -11480359}, new int[]{-11229725, -12014137, -10234219, -10819908}, new int[]{-12927610, -11158198, -3355566, -5191850}, new int[]{-8164117, -5281560, -2200166, -2525971}, new int[]{-1287263, -1350281, -1337532, -885148}, new int[]{-1419145, -1936819, -742839, -1014448}, new int[]{-1017772, -1212871, -998847, -1003446}};
        premiumColors = new int[][]{new int[]{-7035984, -9667705}, new int[]{-1334949, -6199504}, new int[]{-1525432, -4686800}, new int[]{-11117215, -12893369}, new int[]{-15000805, -16777216}, new int[]{-10588271, -12496267}, new int[]{-5344541, -7842635}, new int[]{-5278276, -7777898}, new int[]{-4036162, -7650428}, new int[]{-2459992, -5351279}, new int[]{-1678221, -5814951}, new int[]{-9659148, -10720532}, new int[]{-12149549, -13731672}, new int[]{-12350279, -13802877}, new int[]{-10046854, -13404051}, new int[]{-8276302, -11822442}, new int[]{-10760507, -13200754}, new int[]{-10496401, -13525130}, new int[]{-1668548, -2862189}, new int[]{-9706766, -10062345}, new int[]{-3838476, -10456076}, new int[]{-1324753, -11225016}, new int[]{-10046854, -13404051}, new int[]{-3492512, -7569348}, new int[]{-5394320, -9732780}, new int[]{-7039865, -9408414}, new int[]{-5202023, -7373198}, new int[]{-3701922, -6397115}, new int[]{-4427695, -6859449}, new int[]{-7379371, -9944001}};
    }

    public AvatarConstructorFragment(ImageUpdater r2, ImageUpdater.AvatarFor r3) {
        this.actionBarPaint = new Paint();
        this.finishOnDone = true;
        this.isLightInternal = false;
        this.progressToLightStatusBar = 0.0f;
        this.imageUpdater = r2;
        this.avatarFor = r3;
    }

    public static /* synthetic */ ActionBar access$000(AvatarConstructorFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(AvatarConstructorFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$200(AvatarConstructorFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$300(AvatarConstructorFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$400(AvatarConstructorFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$500(AvatarConstructorFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$600(AvatarConstructorFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$700(AvatarConstructorFragment r0) {
        return r0.currentAccount;
    }

    private void createKeyboardVisibleAnimator(boolean r6) {
        if (this.isLandscapeMode == false) goto L5;
        return;
    L5:
        float r0 = this.keyboardVisibleProgress;
        float r1 = 0.0f;
        if (r6 == false) goto L8;
        float r2 = 1.0f;
    L9:
        this.keyboardVisibilityAnimator = ValueAnimator.ofFloat(new float[]{r0, r2});
        float r22 = ((this.expandedHeight - this.collapsedHeight) - AndroidUtilities.statusBarHeight) * this.progressToExpand;
        if (r6 == false) goto L12;
        this.previewView.setExpanded(false);
        r22 = this.linearLayout.getTranslationY();
    L14:
        if (this.expandWithKeyboard == false) goto L17;
        if (r6 == true) goto L17;
        this.previewView.setExpanded(true);
    L18:
        this.keyboardVisibilityAnimator.addUpdateListener(new AnonymousClass8(this, r22, r1, r6));
        this.keyboardVisibilityAnimator.addListener(new AnonymousClass9(this));
        this.keyboardVisibilityAnimator.setDuration(250);
        this.keyboardVisibilityAnimator.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        this.keyboardVisibilityAnimator.start();
        return;
    L17:
        this.expandWithKeyboard = false;
        goto L18
    L12:
        r1 = this.linearLayout.getTranslationY();
        goto L14
    L8:
        r2 = 0.0f;
        goto L9
    }

    private void discardEditor() {
        if (getParentActivity() != null) goto L6;
        return;
    L6:
        if (this.wasChanged == false) goto L9;
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setMessage(LocaleController.getString(R.string.PhotoEditorDiscardAlert));
        r0.setTitle(LocaleController.getString(R.string.DiscardChanges));
        r0.setPositiveButton(LocaleController.getString(R.string.PassportDiscard), new AvatarConstructorFragment$$ExternalSyntheticLambda6(this));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r02 = r0.create();
        showDialog(r02);
        r02.redPositive();
        return;
    L9:
        finishFragment();
    }

    private boolean isLocked() {
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L5;
        return false;
    L5:
        PreviewView r2 = this.previewView;
        BackgroundGradient r0 = r2.backgroundGradient;
        if (r0 == null) goto L10;
        if (r0.premium == false) goto L10;
        return true;
    L10:
        if (r2.isCustomGradient == true) goto L15;
        boolean r22 = r2.freeEmoji;
        return false;
    L15:
        return true;
    }

    private /* synthetic */ void lambda$createView$0(View r1) {
        onDonePressed();
    }

    private /* synthetic */ void lambda$createView$1() {
        onPreviewClick();
    }

    private /* synthetic */ void lambda$discardEditor$2(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$isLightStatusBar$7(ValueAnimator r1) {
        setProgressToLightStatusBar(((Float) r1.getAnimatedValue()).floatValue());
    }

    private /* synthetic */ void lambda$onBackPressed$8(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$onDonePressed$3() {
        presentFragment(new PremiumPreviewFragment("avatar"));
    }

    private /* synthetic */ void lambda$setExpanded$4(boolean r2, ValueAnimator r3) {
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        setProgressToExpand(r32, false);
        if (r2 == false) goto L6;
        PreviewView r1 = this.previewView;
        r1.overrideExpandProgress = r32;
        r1.invalidate();
        return;
    }

    private /* synthetic */ void lambda$showColorPicker$5(int r2, int r3, boolean r4) {
        if (r3 == 0) goto L28;
        if (r3 != 1) goto L6;
        BackgroundGradient r32 = this.colorPickerGradient;
        int r0 = r32.color2;
        if (r0 == r2) goto L27;
        if (r0 == 0) goto L26;
        if (r2 != 0) goto L27;
    L26:
        BackgroundGradient r33 = r32.copy();
        this.colorPickerGradient = r33;
        this.previewView.setGradient(r33, true);
        updateButton();
    L27:
        this.colorPickerGradient.color2 = r2;
    L34:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needCheckSystemBarColors, new Object[0]);
        this.previewView.invalidate();
        return;
    L6:
        if (r3 != 2) goto L8;
        BackgroundGradient r34 = this.colorPickerGradient;
        int r02 = r34.color3;
        if (r02 == r2) goto L21;
        if (r02 == 0) goto L20;
        if (r2 != 0) goto L21;
    L20:
        BackgroundGradient r35 = r34.copy();
        this.colorPickerGradient = r35;
        this.previewView.setGradient(r35, true);
        updateButton();
    L21:
        this.colorPickerGradient.color3 = r2;
        goto L34
    L8:
        if (r3 != 3) goto L34;
        BackgroundGradient r36 = this.colorPickerGradient;
        int r03 = r36.color4;
        if (r03 == r2) goto L15;
        if (r03 == 0) goto L14;
        if (r2 != 0) goto L15;
    L14:
        BackgroundGradient r37 = r36.copy();
        this.colorPickerGradient = r37;
        this.previewView.setGradient(r37, true);
        updateButton();
    L15:
        this.colorPickerGradient.color4 = r2;
        goto L34
    L28:
        BackgroundGradient r38 = this.colorPickerGradient;
        int r04 = r38.color1;
        if (r04 == r2) goto L33;
        if (r04 == 0) goto L32;
        if (r2 != 0) goto L33;
    L32:
        BackgroundGradient r39 = r38.copy();
        this.colorPickerGradient = r39;
        this.previewView.setGradient(r39, true);
        updateButton();
    L33:
        this.colorPickerGradient.color1 = r2;
        goto L34
    }

    private /* synthetic */ void lambda$showColorPicker$6(boolean[] r2, View r3) {
        r2[0] = true;
        this.backgroundSelectView.selectGradient(this.colorPickerGradient);
        this.bottomSheet.dismiss();
    }

    private void onDonePressed() {
        if (this.previewView.getImageReceiver() != null) goto L5;
        return;
    L5:
        if (this.previewView.getImageReceiver().hasImageLoaded() == true) goto L8;
        return;
    L8:
        if (isLocked() == false) goto L11;
        BulletinFactory.of(this.bottomBulletinContainer, this.resourceProvider).createSimpleBulletin(R.raw.star_premium_2, AndroidUtilities.premiumText(LocaleController.getString(R.string.PremiumAvatarToast), new AvatarConstructorFragment$$ExternalSyntheticLambda5(this))).show();
        return;
    L11:
        Delegate r0 = this.delegate;
        if (r0 == null) goto L15;
        PreviewView r5 = this.previewView;
        r0.onDone(r5.backgroundGradient, r5.documentId, r5.document, r5);
    L15:
        if (this.finishOnDone == false) goto L19;
        finishFragment();
        return;
    }

    private void setExpanded(boolean r5, boolean r6, boolean r7) {
        if (this.isLandscapeMode == false) goto L5;
        return;
    L5:
        cancelExpandAnimator();
        float r0 = this.progressToExpand;
        if (r5 == false) goto L8;
        float r1 = 1.0f;
    L9:
        this.expandAnimator = ValueAnimator.ofFloat(new float[]{r0, r1});
        if (r6 == false) goto L12;
        this.previewView.overrideExpandProgress = this.progressToExpand;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needCheckSystemBarColors, new Object[0]);
    L12:
        this.expandAnimator.addUpdateListener(new AvatarConstructorFragment$$ExternalSyntheticLambda4(this, r6));
        this.expandAnimator.addListener(new AnonymousClass10(this, r5, r6));
        ValueAnimator r52 = this.expandAnimator;
        if (r7 == false) goto L15;
        r52.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.expandAnimator.setDuration(350);
        this.expandAnimator.setStartDelay(150);
    L16:
        this.expandAnimator.start();
        return;
    L15:
        r52.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.expandAnimator.setDuration(250);
        goto L16
    L8:
        r1 = 0.0f;
        goto L9
    }

    private void setPreview(boolean r14, long r15, TLRPC.Document r17) {
        PreviewView r2 = this.previewView;
        r2.documentId = r15;
        r2.document = r17;
        r2.freeEmoji = r14;
        if (r15 != 0) goto L5;
        r2.backupImageView.setAnimatedEmojiDrawable(null);
        SvgHelper.SvgDrawable r5 = DocumentObject.getSvgThumb(r17, Theme.key_windowBackgroundWhiteGrayIcon, 0.2f);
        this.previewView.backupImageView.getImageReceiver().setImage(ImageLocation.getForDocument(r17), "100_100", null, null, r5, 0, "tgs", r17, 0);
    L7:
        if (this.previewView.getImageReceiver() == null) goto L12;
        if (this.previewView.getImageReceiver().getAnimation() == null) goto L12;
        this.previewView.getImageReceiver().getAnimation().seekTo(0, true);
    L12:
        if (this.previewView.getImageReceiver() != null) goto L14;
    L16:
        this.wasChanged = true;
        updateButton();
        return;
    L14:
        if (this.previewView.getImageReceiver().getLottieAnimation() == null) goto L16;
        this.previewView.getImageReceiver().getLottieAnimation().setCurrentFrame(0, false, true);
        goto L16
    L5:
        r2.backupImageView.setAnimatedEmojiDrawable(new AnimatedEmojiDrawable(14, this.currentAccount, r15));
        this.previewView.backupImageView.getImageReceiver().clearImage();
        goto L7
    }

    private void setProgressToExpand(float r4, boolean r5) {
        this.progressToExpand = r4;
        float r0 = ((this.expandedHeight - this.collapsedHeight) - AndroidUtilities.statusBarHeight) * r4;
        if (this.keyboardVisibleProgress != 0.0f) goto L5;
        this.linearLayout.setTranslationY(r0);
        this.button.setTranslationY(r0);
    L5:
        this.previewView.setTranslationY(((-(this.expandedHeight - this.collapsedHeight)) / 2.0f) * r4);
        this.fragmentView.invalidate();
        if (r5 == false) goto L13;
        PreviewView r3 = this.previewView;
        if (r4 <= 0.5f) goto L10;
        boolean r42 = true;
    L11:
        r3.setExpanded(r42);
        return;
    L10:
        r42 = false;
        goto L11
    }

    private void setProgressToLightStatusBar(float r4) {
        if (this.progressToLightStatusBar == r4) goto L6;
        this.progressToLightStatusBar = r4;
        int r42 = ColorUtils.blendARGB(-16777216, -1, r4);
        int r0 = ColorUtils.setAlphaComponent(r42, 60);
        this.overlayActionBar.setItemsColor(r42, false);
        this.setPhotoItem.setBackground(Theme.createSelectorDrawable(r0, 3));
        return;
    }

    private void showColorPicker() {
        if (this.bottomSheet == null) goto L6;
        return;
    L6:
        if (this.previewView.expanded == true) goto L8;
        setExpanded(true, true, true);
    L8:
        BackgroundGradient r0 = this.previewView.backgroundGradient;
        boolean[] r02 = {false};
        AndroidUtilities.requestAdjustNothing(getParentActivity(), getClassGuid());
        AnonymousClass11 r3 = new AnonymousClass11(this, getContext(), true);
        this.bottomSheet = r3;
        r3.fixNavigationBar();
        this.bottomSheet.pauseAllHeavyOperations = false;
        this.drawForBlur = true;
        this.colorPickerPreviewView.setBackground(new BitmapDrawable(getContext().getResources(), AndroidUtilities.makeBlurBitmap(this.fragmentView, 12.0f, 10)));
        this.drawForBlur = false;
        this.colorPickerPreviewView.setVisibility(0);
        this.colorPickerPreviewView.setAlpha(0.0f);
        this.colorPickerInAnimatoin = true;
        this.fragmentView.invalidate();
        this.colorPickerPreviewView.animate().setListener(new AnonymousClass12(this)).alpha(1.0f).setDuration(200).start();
        this.colorPickerGradient = new BackgroundGradient();
        AnonymousClass13 r4 = new AnonymousClass13(this, getContext(), false, new AvatarConstructorFragment$$ExternalSyntheticLambda7(this));
        BackgroundGradient r32 = this.previewView.backgroundGradient;
        if (r32 == null) goto L11;
        BackgroundGradient r5 = this.colorPickerGradient;
        int r33 = r32.color4;
        r5.color4 = r33;
        r4.setColor(r33, 3);
        BackgroundGradient r34 = this.colorPickerGradient;
        int r52 = this.previewView.backgroundGradient.color3;
        r34.color3 = r52;
        r4.setColor(r52, 2);
        BackgroundGradient r35 = this.colorPickerGradient;
        int r53 = this.previewView.backgroundGradient.color2;
        r35.color2 = r53;
        r4.setColor(r53, 1);
        BackgroundGradient r36 = this.colorPickerGradient;
        int r54 = this.previewView.backgroundGradient.color1;
        r36.color1 = r54;
        r4.setColor(r54, 0);
    L11:
        r4.setType(-1, true, 4, this.colorPickerGradient.colorsCount(), false, 0, false);
        this.previewView.setGradient(this.colorPickerGradient, true);
        updateButton();
        LinearLayout r37 = new LinearLayout(getContext());
        r37.setOrientation(1);
        r37.setPadding(0, AndroidUtilities.dp(8.0f), 0, 0);
        r37.addView(r4);
        FrameLayout r42 = new FrameLayout(getContext());
        r42.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{8.0f}));
        TextView r55 = new TextView(getContext());
        r55.setTextSize(1, 14.0f);
        r55.setText(LocaleController.getString(R.string.SetColor));
        r55.setGravity(17);
        r55.setTypeface(AndroidUtilities.bold());
        r55.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        r42.addView(r55, LayoutHelper.createFrame(-2, -2, 17));
        r37.addView(r42, LayoutHelper.createFrame(-1, 48.0f, 0, 16.0f, -8.0f, 16.0f, 16.0f));
        r42.setOnClickListener(new AvatarConstructorFragment$$ExternalSyntheticLambda8(this, r02));
        this.bottomSheet.setCustomView(r37);
        BottomSheet r03 = this.bottomSheet;
        r03.smoothKeyboardAnimationEnabled = true;
        r03.setDimBehind(false);
        this.bottomSheet.show();
        isLightStatusBar();
    }

    private void updateButton() {
        boolean r0 = isLocked();
        if (this.buttonIsLocked == r0) goto L10;
        ButtonWithCounterView r1 = this.button;
        this.buttonIsLocked = r0;
        if (r0 == false) goto L7;
        CharSequence r2 = this.lockedButtonText;
    L8:
        r1.setText(r2, true);
        return;
    L7:
        r2 = this.buttonText;
        goto L8
    }

    public void cancelExpandAnimator() {
        ValueAnimator r0 = this.expandAnimator;
        if (r0 == null) goto L6;
        r0.removeAllListeners();
        this.expandAnimator.cancel();
        this.expandAnimator = null;
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r24) {
        this.hasOwnBackground = true;
        this.actionBar.setBackgroundDrawable(null);
        this.actionBar.setCastShadows(false);
        this.actionBar.setAddToContainer(false);
        this.actionBar.setOccupyStatusBar(true);
        ActionBar r0 = this.actionBar;
        int r2 = Theme.key_windowBackgroundWhiteBlackText;
        r0.setTitleColor(Theme.getColor(r2));
        this.actionBar.setItemsColor(Theme.getColor(r2), false);
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_listSelector), false);
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setAllowOverlayTitle(false);
        this.actionBar.setTitle(LocaleController.getString(R.string.PhotoEditor));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.actionBar.getTitleTextView().setAlpha(0.0f);
        ActionBar r02 = new ActionBar(getContext());
        this.overlayActionBar = r02;
        r02.setCastShadows(false);
        this.overlayActionBar.setAddToContainer(false);
        this.overlayActionBar.setOccupyStatusBar(true);
        this.overlayActionBar.setClipChildren(false);
        int r03 = ColorUtils.setAlphaComponent(-1, 60);
        this.overlayActionBar.setItemsColor(-1, false);
        this.overlayActionBar.setBackButtonDrawable(new BackDrawable(false));
        this.overlayActionBar.setAllowOverlayTitle(false);
        this.overlayActionBar.setItemsBackgroundColor(r03, false);
        ActionBarMenu r3 = this.overlayActionBar.createMenu();
        r3.setClipChildren(false);
        ImageUpdater.AvatarFor r4 = this.avatarFor;
        if (r4 != null) goto L5;
    L7:
        String r42 = LocaleController.getString(R.string.SetPhoto);
    L8:
        ActionBarMenuItem r32 = r3.addItem(1, r42);
        this.setPhotoItem = r32;
        r32.setBackground(Theme.createSelectorDrawable(r03, 3));
        this.overlayActionBar.setActionBarMenuOnItemClick(new AnonymousClass2(this));
        this.linearLayout = new AnonymousClass3(this, getContext());
        AnonymousClass4 r04 = new AnonymousClass4(this, r24);
        r04.setFitsSystemWindows(true);
        r04.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.linearLayout.setClipChildren(false);
        this.linearLayout.setClipToPadding(false);
        this.linearLayout.setPadding(0, AndroidUtilities.statusBarHeight, 0, 0);
        this.linearLayout.setOrientation(1);
        LinearLayout r33 = this.linearLayout;
        AnonymousClass5 r5 = new AnonymousClass5(this, getContext(), r04);
        this.previewView = r5;
        r33.addView(r5);
        TextView r34 = new TextView(getContext());
        this.chooseBackgroundHint = r34;
        r34.setText(LocaleController.getString(R.string.ChooseBackground));
        TextView r35 = this.chooseBackgroundHint;
        int r52 = Theme.key_windowBackgroundWhiteGrayText;
        r35.setTextColor(Theme.getColor(r52));
        this.chooseBackgroundHint.setTextSize(1, 14.0f);
        this.chooseBackgroundHint.setGravity(17);
        this.linearLayout.addView(this.chooseBackgroundHint, LayoutHelper.createLinear(-1, -2, 0, 21, 10, 21, 10));
        AnonymousClass6 r36 = new AnonymousClass6(this, getContext());
        BackgroundSelectView r8 = new BackgroundSelectView(this, getContext());
        this.backgroundSelectView = r8;
        r36.addView(r8);
        this.linearLayout.addView(r36, LayoutHelper.createLinear(-1, 48, 0, 12, 0, 12, 0));
        TextView r37 = new TextView(getContext());
        this.chooseEmojiHint = r37;
        r37.setText(LocaleController.getString(R.string.ChooseEmojiOrSticker));
        this.chooseEmojiHint.setTextColor(Theme.getColor(r52));
        this.chooseEmojiHint.setTextSize(1, 14.0f);
        this.chooseEmojiHint.setGravity(17);
        this.linearLayout.addView(this.chooseEmojiHint, LayoutHelper.createLinear(-1, -2, 0, 21, 18, 21, 10));
        AnonymousClass7 r05 = new AnonymousClass7(this, this, getContext(), false, null, 4, true, null, 16, getThemedColor(r2));
        this.selectAnimatedEmojiDialog = r05;
        r05.forUser = !this.forGroup;
        r05.setAnimationsEnabled(this.fragmentBeginToShow);
        this.selectAnimatedEmojiDialog.setClipChildren(false);
        this.linearLayout.addView(this.selectAnimatedEmojiDialog, LayoutHelper.createLinear(-1, -1, 0, 12, 0, 12, 12));
        this.linearLayout.setClipChildren(false);
        r04.addView(this.linearLayout, LayoutHelper.createFrame(-1, -1.0f, 0, 0.0f, 0.0f, 0.0f, 64.0f));
        View r06 = new View(getContext());
        this.colorPickerPreviewView = r06;
        r06.setVisibility(8);
        ButtonWithCounterView r07 = new ButtonWithCounterView(r24, this.resourceProvider);
        this.button = r07;
        r07.setRound();
        this.button.text.setHacks(false, true, false);
        int r08 = this.imageUpdater.setForType;
        if (r08 != 1) goto L11;
        this.buttonText = LocaleController.getString(R.string.SetChannelPhoto);
    L22:
        this.buttonText = new SpannableStringBuilder(this.buttonText);
        SpannableStringBuilder r09 = new SpannableStringBuilder(this.buttonText);
        r09.append(" l");
        r09.setSpan(new ColoredImageSpan(R.drawable.msg_mini_lock2), r09.length() - 1, r09.length(), 33);
        this.lockedButtonText = r09;
        this.buttonIsLocked = false;
        this.button.setText(this.buttonText, false);
        this.button.setOnClickListener(new AvatarConstructorFragment$$ExternalSyntheticLambda1(this));
        this.bottomBulletinContainer = new FrameLayout(r24);
        r04.addView(this.button, LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 16.0f, 16.0f, 16.0f));
        r04.addView(this.bottomBulletinContainer, LayoutHelper.createFrame(-1, 80.0f, 80, 8.0f, 16.0f, 8.0f, 64.0f));
        r04.addView(this.actionBar);
        r04.addView(this.overlayActionBar);
        r04.addView(this.colorPickerPreviewView, LayoutHelper.createFrame(-1, -1.0f));
        CanvasButton r010 = new CanvasButton(r04);
        this.avatarClickableArea = r010;
        r010.setDelegate(new AvatarConstructorFragment$$ExternalSyntheticLambda2(this));
        this.fragmentView = r04;
        return r04;
    L11:
        if (r08 != 3) goto L14;
        this.buttonText = LocaleController.getString(R.string.SetCommunityPhoto);
        goto L22
    L14:
        if (r08 != 2) goto L16;
        this.buttonText = LocaleController.getString(R.string.SetGroupPhoto);
        goto L22
    L16:
        ImageUpdater.AvatarFor r011 = this.avatarFor;
        if (r011 != null) goto L19;
    L21:
        this.buttonText = LocaleController.getString(R.string.SetMyProfilePhotoAvatarConstructor);
        goto L22
    L19:
        if (r011.type != 2) goto L21;
        this.buttonText = LocaleController.getString(R.string.SuggestPhoto);
        goto L22
    L5:
        if (r4.type != 2) goto L7;
        r42 = LocaleController.getString(R.string.SuggestPhoto);
        goto L8
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        PreviewView r0 = this.previewView;
        float r4 = 0.0f;
        if (r0 == null) goto L15;
        if (r0.expanded == true) goto L11;
        if (r0.overrideExpandProgress < 0.0f) goto L15;
        if (r0.backgroundGradient == null) goto L15;
    L11:
        if (AndroidUtilities.computePerceivedBrightness(r0.backgroundGradient.getAverageColor()) <= 0.721f) goto L13;
    L12:
        boolean r02 = true;
    L18:
        if (this.isLightInternal == r02) goto L33;
        this.isLightInternal = r02;
        if (this.actionBar.getAlpha() != 0.0f) goto L25;
        if (r02 == true) goto L24;
        r4 = 1.0f;
    L24:
        setProgressToLightStatusBar(r4);
        goto L33
    L25:
        ValueAnimator r3 = this.lightProgressAnimator;
        if (r3 == null) goto L28;
        r3.removeAllListeners();
        this.lightProgressAnimator.cancel();
    L28:
        float r32 = this.progressToLightStatusBar;
        if (r02 == true) goto L32;
        r4 = 1.0f;
    L32:
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{r32, r4});
        this.lightProgressAnimator = r1;
        r1.addUpdateListener(new AvatarConstructorFragment$$ExternalSyntheticLambda0(this));
        this.lightProgressAnimator.setDuration(150).start();
    L33:
        BottomSheet r6 = this.bottomSheet;
        if (r6 == null) goto L36;
        AndroidUtilities.setLightStatusBar(r6, r02);
    L36:
        return r02;
    L13:
        r02 = false;
    L15:
        if (AndroidUtilities.computePerceivedBrightness(Theme.getColor(Theme.key_windowBackgroundGray)) <= 0.721f) goto L13;
        goto L13
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r1) {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r3) {
        if (this.wasChanged == false) goto L9;
        if (r3 == false) goto L10;
        AlertDialog.Builder r32 = new AlertDialog.Builder(getParentActivity());
        r32.setMessage(LocaleController.getString(R.string.PhotoEditorDiscardAlert));
        r32.setTitle(LocaleController.getString(R.string.DiscardChanges));
        r32.setPositiveButton(LocaleController.getString(R.string.PassportDiscard), new AvatarConstructorFragment$$ExternalSyntheticLambda3(this));
        r32.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r33 = r32.create();
        showDialog(r33);
        r33.redPositive();
        return false;
    L10:
        return false;
    L9:
        return super.onBackPressed(r3);
    }

    public void onPreviewClick() {
        if (this.isLandscapeMode == false) goto L6;
        return;
    L6:
        if (this.keyboardVisibleProgress > 0.0f) goto L8;
        setExpanded(!this.previewView.expanded, true, false);
        return;
    L8:
        if (this.keyboardVisibilityAnimator == null) goto L10;
        this.progressToExpand = 1.0f;
        this.expandWithKeyboard = true;
    L10:
        AndroidUtilities.hideKeyboard(this.fragmentView);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        AndroidUtilities.requestAdjustResize(getParentActivity(), getClassGuid());
    }

    public void setDelegate(Delegate r1) {
        this.delegate = r1;
    }

    public void startFrom(long r6, boolean r8) {
        if (this.previewView != null) goto L5;
        return;
    L5:
        BackgroundGradient r0 = new BackgroundGradient();
        int[] r1 = defaultColors[0];
        r0.color1 = r1[0];
        r0.color2 = r1[1];
        r0.color3 = r1[2];
        r0.color4 = r1[3];
        this.previewView.setGradient(r0, false);
        updateButton();
        PreviewView r12 = this.previewView;
        r12.documentId = r6;
        r12.backupImageView.setAnimatedEmojiDrawable(new AnimatedEmojiDrawable(14, this.currentAccount, r6));
        this.backgroundSelectView.selectGradient(r0);
        SelectAnimatedEmojiDialog r62 = this.selectAnimatedEmojiDialog;
        this.forUser = r8;
        r62.setForUser(r8);
    }

    public void startFrom(TLRPC.VideoSize r11) {
        BackgroundGradient r0 = new BackgroundGradient();
        r0.color1 = ColorUtils.setAlphaComponent(r11.background_colors.get(0).intValue(), 255);
        if (r11.background_colors.size() <= 1) goto L5;
        int r1 = ColorUtils.setAlphaComponent(r11.background_colors.get(1).intValue(), 255);
    L6:
        r0.color2 = r1;
        if (r11.background_colors.size() <= 2) goto L9;
        int r12 = ColorUtils.setAlphaComponent(r11.background_colors.get(2).intValue(), 255);
    L10:
        r0.color3 = r12;
        if (r11.background_colors.size() <= 3) goto L13;
        int r13 = ColorUtils.setAlphaComponent(r11.background_colors.get(3).intValue(), 255);
    L14:
        r0.color4 = r13;
        this.previewView.setGradient(r0, false);
        updateButton();
        TLRPC.Document r3 = null;
        if ((r11 instanceof TLRPC.TL_videoSizeEmojiMarkup) == false) goto L17;
        setPreview(false, ((TLRPC.TL_videoSizeEmojiMarkup) r11).emoji_id, null);
    L27:
        this.backgroundSelectView.selectGradient(r0);
        SelectAnimatedEmojiDialog r112 = this.selectAnimatedEmojiDialog;
        this.forUser = true;
        r112.setForUser(true);
        return;
    L17:
        TLRPC.TL_videoSizeStickerMarkup r113 = new TLRPC.TL_videoSizeStickerMarkup();
        TLRPC.TL_messages_stickerSet r14 = MediaDataController.getInstance(this.currentAccount).getStickerSet(r113.stickerset, false);
        if (r14 == null) goto L26;
        int r5 = 0;
    L21:
        if (r5 >= r14.documents.size()) goto L26;
        if (r14.documents.get(r5).id != r113.sticker_id) goto L25;
        r3 = r14.documents.get(r5);
    L25:
        r5 = r5 + 1;
    L26:
        setPreview(false, 0, r3);
        goto L27
    L13:
        r13 = 0;
        goto L14
    L9:
        r12 = 0;
        goto L10
    L5:
        r1 = 0;
        goto L6
    }

    public void startFrom(AvatarConstructorPreviewCell r8) {
        BackgroundGradient r0 = r8.getBackgroundGradient();
        PreviewView r1 = this.previewView;
        if (r1 != null) goto L5;
        return;
    L5:
        r1.setGradient(r0, false);
        updateButton();
        if (r8.getAnimatedEmoji() == null) goto L8;
        long r12 = r8.getAnimatedEmoji().getDocumentId();
        PreviewView r3 = this.previewView;
        r3.documentId = r12;
        r3.backupImageView.setAnimatedEmojiDrawable(new AnimatedEmojiDrawable(14, this.currentAccount, r12));
    L8:
        this.backgroundSelectView.selectGradient(r0);
        SelectAnimatedEmojiDialog r02 = this.selectAnimatedEmojiDialog;
        boolean r82 = r8.forUser;
        this.forUser = r82;
        r02.setForUser(r82);
    }
}
