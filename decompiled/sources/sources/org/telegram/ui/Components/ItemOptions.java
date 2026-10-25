package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.utils.system.VibratorUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.SharedPhotoVideoCell2;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Components.MessagePreviewView;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProvider;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceBitmap;
import org.telegram.ui.Components.blur3.utils.Blur3Utils;
import org.telegram.ui.ContactsActivity;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.Gifts.GiftSheet;
import org.telegram.ui.MainTabsActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.SettingsActivity;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes3.dex */
public class ItemOptions {
    public ActionBarPopupWindow actionBarPopupWindow;
    private boolean allowCenter;
    private boolean allowMoveScrim;
    private int allowMoveScrimGravity;
    private boolean allowShowingOnTopOfKeyboard;
    private int animateToHeight;
    private int animateToWidth;
    private boolean blur;
    private boolean blurForMenu;
    private ViewGroup container;
    private Context context;
    private int dimAlpha;
    private ValueAnimator dimAnimator;
    private DimView dimView;
    private Runnable dismissListener;
    private boolean dismissOnMoveOutside;
    public boolean dismissWithButtons;
    private boolean dontDismiss;
    private boolean dontFocus;
    private boolean drawScrim;
    private int fixedWidthDp;
    private View.OnLayoutChangeListener followLayoutListener;
    private final int[] followLoc;
    private boolean followScrim;
    private ViewTreeObserver.OnScrollChangedListener followScrollListener;
    private View followingView;
    private boolean forceBelowScrim;
    private boolean forceBottom;
    private boolean forceTop;
    private int foregroundIndex;
    private BaseFragment fragment;
    private Integer gapBackgroundColor;
    private int gravity;
    private boolean hasScrimViewPoint;
    private boolean hideScrimUnder;
    private final int[] hoverLoc;
    private View.OnTouchListener hoverReleaseListener;
    private View hoveredItem;
    private Integer iconColor;
    private boolean ignoreX;
    private ActionBarPopupWindow.ActionBarPopupWindowLayout lastLayout;
    private ViewGroup layout;
    private LinearLayout linearLayout;
    private boolean longPressSelectionEnabled;
    private int maxHeight;
    private int minWidthDp;
    public boolean needsFocus;
    private boolean offsetByContainer;
    private float offsetX;
    private float offsetY;
    public boolean onTopOfScrim;
    private boolean overridenSwipebackGravity;
    private final float[] point;
    private ViewGroup pointContainer;
    private ViewTreeObserver.OnPreDrawListener preDrawListener;
    private Theme.ResourcesProvider resourcesProvider;
    private boolean scaleOut;
    private BlurredBackgroundSourceBitmap scrimBlur3SourceBitmap;
    private View scrimView;
    private Drawable scrimViewBackground;
    private int scrimViewBackgroundShadowColor;
    private int scrimViewPadding;
    private float scrimViewPointX;
    private float scrimViewPointY;
    private int scrimViewRoundRadius;
    private Integer selectorColor;
    private int shiftDp;
    public boolean shownFromBottom;
    public boolean swipeback;
    private Integer textColor;
    private float translateX;
    private float translateY;
    public boolean useScrollView;
    private Rect viewAdditionalOffsets;

    /* renamed from: org.telegram.ui.Components.ItemOptions$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends ActionBarPopupWindow.ActionBarPopupWindowLayout {
        final /* synthetic */ ItemOptions this$0;

        public AnonymousClass1(ItemOptions r1, Context r2, int r3, Theme.ResourcesProvider r4, int r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarPopupWindow.ActionBarPopupWindowLayout, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            if (this == ItemOptions.m11141$$Nest$fgetlayout(this.this$0)) goto L5;
        L7:
            super.onMeasure(r3, r4);
            return;
        L5:
            if (ItemOptions.m11142$$Nest$fgetmaxHeight(this.this$0) <= 0) goto L7;
            r4 = View.MeasureSpec.makeMeasureSpec(Math.min(ItemOptions.m11142$$Nest$fgetmaxHeight(this.this$0), View.MeasureSpec.getSize(r4)), View.MeasureSpec.getMode(r4));
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Components.ItemOptions$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends ScrollView {
        final /* synthetic */ ItemOptions this$0;

        public AnonymousClass2(ItemOptions r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(380.0f), View.MeasureSpec.getSize(r4)), View.MeasureSpec.getMode(r4)));
        }
    }

    /* renamed from: org.telegram.ui.Components.ItemOptions$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends TextView {
        final /* synthetic */ ItemOptions this$0;

        public AnonymousClass3(ItemOptions r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.ItemOptions$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ ItemOptions this$0;

        public AnonymousClass4(ItemOptions r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (ItemOptions.m11136$$Nest$fgetdimView(this.this$0) == null) goto L5;
            ItemOptions.m11136$$Nest$fgetdimView(this.this$0).setProgress(1.0f);
            ItemOptions.m11136$$Nest$fgetdimView(this.this$0).invalidate();
        L5:
            ItemOptions.m11153$$Nest$fputdimAnimator(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.ItemOptions$5, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass5 extends ActionBarPopupWindow {
        final /* synthetic */ ItemOptions this$0;
        final /* synthetic */ ViewGroup val$container;

        public AnonymousClass5(ItemOptions r1, View r2, int r3, int r4, ViewGroup r5) {
            this.this$0 = r1;
            this.val$container = r5;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarPopupWindow, android.widget.PopupWindow
        public void dismiss() {
            super.dismiss();
            ItemOptions.m11156$$Nest$mdismissDim(this.this$0, this.val$container);
            if (ItemOptions.m11137$$Nest$fgetdismissListener(this.this$0) == null) goto L6;
            ItemOptions.m11137$$Nest$fgetdismissListener(this.this$0).run();
            ItemOptions.m11154$$Nest$fputdismissListener(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ItemOptions$6, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass6 implements PopupWindow.OnDismissListener {
        final /* synthetic */ ItemOptions this$0;
        final /* synthetic */ ViewGroup val$container;

        public AnonymousClass6(ItemOptions r1, ViewGroup r2) {
            this.this$0 = r1;
            this.val$container = r2;
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            ItemOptions r0 = this.this$0;
            r0.actionBarPopupWindow = null;
            ItemOptions.m11156$$Nest$mdismissDim(r0, this.val$container);
            ItemOptions.m11155$$Nest$mclearHoverListener(this.this$0);
            ItemOptions.m11157$$Nest$mremoveFollowListeners(this.this$0);
            if (ItemOptions.m11137$$Nest$fgetdismissListener(this.this$0) == null) goto L6;
            ItemOptions.m11137$$Nest$fgetdismissListener(this.this$0).run();
            ItemOptions.m11154$$Nest$fputdismissListener(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ItemOptions$7, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ ItemOptions this$0;
        final /* synthetic */ ViewGroup val$container;
        final /* synthetic */ DimView val$dimViewFinal;

        public AnonymousClass7(ItemOptions r1, DimView r2, ViewGroup r3) {
            this.this$0 = r1;
            this.val$dimViewFinal = r2;
            this.val$container = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.val$dimViewFinal.setProgress(0.0f);
            this.val$dimViewFinal.invalidate();
            AndroidUtilities.removeFromParent(this.val$dimViewFinal);
            this.val$container.getViewTreeObserver().removeOnPreDrawListener(ItemOptions.m11145$$Nest$fgetpreDrawListener(this.this$0));
            if (ItemOptions.m11140$$Nest$fgethideScrimUnder(this.this$0) == false) goto L8;
            ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).setVisibility(0);
            if ((ItemOptions.m11147$$Nest$fgetscrimView(this.this$0) instanceof GiftSheet.GiftCell) == false) goto L9;
            ((GiftSheet.GiftCell) ItemOptions.m11147$$Nest$fgetscrimView(this.this$0)).invalidateCustom();
            return;
        L9:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ItemOptions$8, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass8 extends ScrollView {
        public AnonymousClass8(Context r1) {
            super(r1);
        }

        @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(260.0f), View.MeasureSpec.getSize(r4)), View.MeasureSpec.getMode(r4)));
        }
    }

    /* loaded from: classes7.dex */
    public class DimView extends View {
        private Bitmap blurBitmap;
        private Paint blurPaint;
        private final RectF bounds;
        private final Bitmap cachedBitmap;
        private final Paint cachedBitmapPaint;
        public final float clipBottom;
        private final Path clipPath;
        public final float clipTop;
        private final int dim;
        public float dimProgress;
        private float moveToX;
        private float moveToY;
        final /* synthetic */ ItemOptions this$0;

        /* renamed from: $r8$lambda$35qznMiFL-C6iPT_HOkCTZAxYDg, reason: not valid java name */
        public static /* synthetic */ void m11158$r8$lambda$35qznMiFLC6iPT_HOkCTZAxYDg(DimView r0, Bitmap r1, Bitmap r2) {
            r0.lambda$new$0(r1, r2);
        }

        /* renamed from: -$$Nest$fgetmoveToX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m11159$$Nest$fgetmoveToX(DimView r0) {
            return r0.moveToX;
        }

        /* renamed from: -$$Nest$fgetmoveToY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m11160$$Nest$fgetmoveToY(DimView r0) {
            return r0.moveToY;
        }

        /* renamed from: -$$Nest$fputmoveToX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11161$$Nest$fputmoveToX(DimView r0, float r1) {
            r0.moveToX = r1;
        }

        /* renamed from: -$$Nest$fputmoveToY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11162$$Nest$fputmoveToY(DimView r0, float r1) {
            r0.moveToY = r1;
        }

        public DimView(ItemOptions r5, Context r6) {
            this.this$0 = r5;
            super(r6);
            this.clipPath = new Path();
            this.bounds = new RectF();
            if (ItemOptions.m11147$$Nest$fgetscrimView(r5) != null) goto L5;
        L11:
            this.clipTop = 0.0f;
            this.clipBottom = 0.0f;
        L12:
            this.dim = ColorUtils.setAlphaComponent(0, ItemOptions.m11135$$Nest$fgetdimAlpha(r5));
            if (ItemOptions.m11138$$Nest$fgetdrawScrim(r5) == true) goto L15;
        L19:
            this.cachedBitmapPaint = null;
            this.cachedBitmap = null;
        L21:
            if (ItemOptions.m11133$$Nest$fgetblur(r5) == false) goto L23;
        L26:
            this.blurPaint = new Paint(3);
            ItemOptions.m11147$$Nest$fgetscrimView(r5).setAlpha(0.0f);
            ScrimOptions.makeGlobalBlurBitmaps(ItemOptions.m11144$$Nest$fgetpointContainer(r5), new ItemOptions$DimView$$ExternalSyntheticLambda0(this));
            return;
        L23:
            if (ItemOptions.m11134$$Nest$fgetblurForMenu(r5) == true) goto L26;
            return;
        L15:
            if ((ItemOptions.m11147$$Nest$fgetscrimView(r5) instanceof UserCell) == false) goto L19;
            if ((ItemOptions.m11139$$Nest$fgetfragment(r5) instanceof ProfileActivity) == false) goto L19;
            this.cachedBitmapPaint = new Paint(3);
            Bitmap r62 = Bitmap.createBitmap(ItemOptions.m11147$$Nest$fgetscrimView(r5).getWidth() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(r5).width(), ItemOptions.m11147$$Nest$fgetscrimView(r5).getHeight() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(r5).height(), Bitmap.Config.ARGB_8888);
            this.cachedBitmap = r62;
            Canvas r2 = new Canvas(r62);
            r2.translate(ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(r5).left, ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(r5).top);
            ItemOptions.m11147$$Nest$fgetscrimView(r5).draw(r2);
            goto L21
        L5:
            if ((ItemOptions.m11147$$Nest$fgetscrimView(r5).getParent() instanceof View) == false) goto L11;
            this.clipTop = ((View) ItemOptions.m11147$$Nest$fgetscrimView(r5).getParent()).getY() + ItemOptions.m11147$$Nest$fgetscrimView(r5).getY();
            if (ItemOptions.m11130$$Nest$fgetallowMoveScrim(r5) == false) goto L9;
            float r63 = Math.min(AndroidUtilities.dp(68.0f), Math.max(0.0f, (((View) ItemOptions.m11147$$Nest$fgetscrimView(r5).getParent()).getY() + ItemOptions.m11147$$Nest$fgetscrimView(r5).getY()) + ItemOptions.m11147$$Nest$fgetscrimView(r5).getHeight()));
        L10:
            this.clipBottom = r63;
            goto L12
        L9:
            r63 = 0.0f;
            goto L10
        }

        private /* synthetic */ void lambda$new$0(Bitmap r3, Bitmap r4) {
            ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).setAlpha(1.0f);
            if (ItemOptions.m11133$$Nest$fgetblur(this.this$0) == false) goto L6;
            this.blurBitmap = r3;
        L6:
            if (ItemOptions.m11146$$Nest$fgetscrimBlur3SourceBitmap(this.this$0) == null) goto L11;
            ItemOptions.m11146$$Nest$fgetscrimBlur3SourceBitmap(this.this$0).setBitmap(r4);
            Blur3Utils.checkBitmapSourceMatrixScale(ItemOptions.m11146$$Nest$fgetscrimBlur3SourceBitmap(this.this$0), this);
            if (ItemOptions.m11141$$Nest$fgetlayout(this.this$0) == null) goto L12;
            ItemOptions.m11141$$Nest$fgetlayout(this.this$0).invalidate();
            return;
        L12:
            return;
        }

        @Override // android.view.View
        public void onDraw(Canvas r15) {
            super.onDraw(r15);
            if (this.blurBitmap == null) goto L5;
            r15.save();
            float r0 = Math.max(getWidth() / this.blurBitmap.getWidth(), getHeight() / this.blurBitmap.getHeight());
            r15.scale(r0, r0);
            this.blurPaint.setAlpha((int) (this.dimProgress * 255.0f));
            r15.drawBitmap(this.blurBitmap, 0.0f, 0.0f, this.blurPaint);
            r15.restore();
        L7:
            if (ItemOptions.m11138$$Nest$fgetdrawScrim(this.this$0) == true) goto L10;
            return;
        L10:
            if (this.cachedBitmap == null) goto L41;
            if ((ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getParent() instanceof View) == false) goto L41;
            r15.save();
            if (this.clipTop >= 1.0f) goto L20;
            float r02 = -ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).left;
            float r3 = (-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).top) + ItemOptions.m11143$$Nest$fgetpoint(this.this$0)[1];
            float r4 = this.clipTop;
            if (ItemOptions.m11133$$Nest$fgetblur(this.this$0) == false) goto L18;
            float r8 = 1.0f - this.dimProgress;
        L19:
            r15.clipRect(r02, (r3 - (r4 * r8)) + 1.0f, getMeasuredWidth() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).right, getMeasuredHeight() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).bottom);
            goto L20
        L18:
            r8 = 1.0f;
        L20:
            boolean r03 = ItemOptions.m11130$$Nest$fgetallowMoveScrim(this.this$0);
            ItemOptions r32 = this.this$0;
            if (r03 == false) goto L23;
            ItemOptions.getPointOnScreen(ItemOptions.m11147$$Nest$fgetscrimView(r32), ItemOptions.m11144$$Nest$fgetpointContainer(this.this$0), ItemOptions.m11143$$Nest$fgetpoint(this.this$0));
            r15.translate(AndroidUtilities.lerp(ItemOptions.m11143$$Nest$fgetpoint(this.this$0)[0], this.moveToX, this.dimProgress), AndroidUtilities.lerp(ItemOptions.m11143$$Nest$fgetpoint(this.this$0)[1], this.moveToY, this.dimProgress));
        L25:
            if (ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0) == null) goto L34;
            if (ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).getIntrinsicWidth() > 0) goto L29;
        L31:
            ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).setBounds(-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).left, -ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).top, ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getWidth() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).right, ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getHeight() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).bottom);
        L32:
            ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).draw(r15);
            goto L34
        L29:
            if (ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).getIntrinsicHeight() <= 0) goto L31;
            ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).setBounds((-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).left) + (((ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getWidth() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).right) - ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).getIntrinsicWidth()) / 2), (-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).top) + (((ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getHeight() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).bottom) - ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).getIntrinsicHeight()) / 2), (-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).left) + (((ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getWidth() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).right) + ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).getIntrinsicWidth()) / 2), (-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).top) + (((ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getHeight() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).bottom) + ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).getIntrinsicHeight()) / 2));
        L34:
            if (ItemOptions.m11150$$Nest$fgetscrimViewPadding(this.this$0) <= 0) goto L36;
        L37:
            this.clipPath.rewind();
            RectF r04 = AndroidUtilities.rectTmp;
            r04.set((-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).left) + (ItemOptions.m11150$$Nest$fgetscrimViewPadding(this.this$0) * this.dimProgress), (-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).top) + (ItemOptions.m11150$$Nest$fgetscrimViewPadding(this.this$0) * getAlpha()), ((-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).left) + this.cachedBitmap.getWidth()) - (ItemOptions.m11150$$Nest$fgetscrimViewPadding(this.this$0) * getAlpha()), ((-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).top) + this.cachedBitmap.getHeight()) - (ItemOptions.m11150$$Nest$fgetscrimViewPadding(this.this$0) * getAlpha()));
            this.clipPath.addRoundRect(r04, ItemOptions.m11151$$Nest$fgetscrimViewRoundRadius(this.this$0) * this.dimProgress, ItemOptions.m11151$$Nest$fgetscrimViewRoundRadius(this.this$0) * this.dimProgress, Path.Direction.CW);
            r15.clipPath(this.clipPath);
        L38:
            this.cachedBitmapPaint.setAlpha(255);
            r15.drawBitmap(this.cachedBitmap, -ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).left, -ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).top, this.cachedBitmapPaint);
            r15.restore();
            return;
        L36:
            if (ItemOptions.m11151$$Nest$fgetscrimViewRoundRadius(this.this$0) <= 0) goto L38;
        L23:
            r15.translate(ItemOptions.m11143$$Nest$fgetpoint(r32)[0], ItemOptions.m11143$$Nest$fgetpoint(this.this$0)[1]);
        L41:
            if (ItemOptions.m11147$$Nest$fgetscrimView(this.this$0) != null) goto L43;
            return;
        L43:
            if ((ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getParent() instanceof View) == false) goto L122;
            r15.save();
            if (this.clipTop >= 1.0f) goto L47;
        L48:
            boolean r05 = ItemOptions.m11130$$Nest$fgetallowMoveScrim(this.this$0);
            ItemOptions r82 = this.this$0;
            if (r05 == false) goto L55;
            float r06 = -ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(r82).left;
            float r83 = (-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).top) + ItemOptions.m11143$$Nest$fgetpoint(this.this$0)[1];
            float r9 = this.clipTop;
            if (ItemOptions.m11133$$Nest$fgetblur(this.this$0) == false) goto L53;
            float r10 = 1.0f - this.dimProgress;
        L54:
            r15.clipRect(r06, AndroidUtilities.lerp((r83 - (r9 * r10)) + 1.0f, 0.0f, this.dimProgress), getMeasuredWidth() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).right, (getMeasuredHeight() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).bottom) - (this.clipBottom * (1.0f - this.dimProgress)));
        L60:
            float r07 = this.dimProgress;
            boolean r84 = ItemOptions.m11130$$Nest$fgetallowMoveScrim(this.this$0);
            ItemOptions r92 = this.this$0;
            if (r84 == false) goto L63;
            ItemOptions.getPointOnScreen(ItemOptions.m11147$$Nest$fgetscrimView(r92), ItemOptions.m11144$$Nest$fgetpointContainer(this.this$0), ItemOptions.m11143$$Nest$fgetpoint(this.this$0));
            r15.translate(AndroidUtilities.lerp(ItemOptions.m11143$$Nest$fgetpoint(this.this$0)[0], this.moveToX, r07), AndroidUtilities.lerp(ItemOptions.m11143$$Nest$fgetpoint(this.this$0)[1], this.moveToY, r07));
        L65:
            if (ItemOptions.m11132$$Nest$fgetanimateToWidth(this.this$0) != 0) goto L67;
        L70:
            float r5 = ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getWidth();
            int r08 = ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getHeight();
        L69:
            float r09 = r08;
            if (ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0) == null) goto L86;
            if (ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).getIntrinsicWidth() > 0) goto L76;
        L78:
            ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).setBounds(-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).left, -ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).top, ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getWidth() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).right, ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getHeight() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).bottom);
        L79:
            ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).setAlpha((int) (this.dimProgress * 255.0f));
            if (Build.VERSION.SDK_INT >= 29) goto L82;
        L84:
            ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).draw(r15);
            goto L86
        L82:
            if ((ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0) instanceof ShapeDrawable) == false) goto L84;
            Paint r6 = ((ShapeDrawable) ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0)).getPaint();
            r6.setShadowLayer(r6.getShadowLayerRadius(), r6.getShadowLayerDx(), r6.getShadowLayerDy(), Theme.multAlpha(ItemOptions.m11149$$Nest$fgetscrimViewBackgroundShadowColor(this.this$0), this.dimProgress));
            goto L84
        L76:
            if (ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).getIntrinsicHeight() <= 0) goto L78;
            ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).setBounds((-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).left) + (((ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getWidth() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).right) - ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).getIntrinsicWidth()) / 2), (-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).top) + (((ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getHeight() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).bottom) - ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).getIntrinsicHeight()) / 2), (-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).left) + (((ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getWidth() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).right) + ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).getIntrinsicWidth()) / 2), (-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).top) + (((ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getHeight() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).bottom) + ItemOptions.m11148$$Nest$fgetscrimViewBackground(this.this$0).getIntrinsicHeight()) / 2));
        L86:
            if (ItemOptions.m11150$$Nest$fgetscrimViewPadding(this.this$0) <= 0) goto L88;
        L89:
            this.clipPath.rewind();
            if ((ItemOptions.m11147$$Nest$fgetscrimView(this.this$0) instanceof ScrimView) == false) goto L92;
            ((ScrimView) ItemOptions.m11147$$Nest$fgetscrimView(this.this$0)).getBounds(this.bounds);
        L93:
            RectF r42 = AndroidUtilities.rectTmp;
            r42.set(((-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).left) + this.bounds.left) + (ItemOptions.m11150$$Nest$fgetscrimViewPadding(this.this$0) * this.dimProgress), ((-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).top) + this.bounds.top) + (ItemOptions.m11150$$Nest$fgetscrimViewPadding(this.this$0) * this.dimProgress), ((-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).left) + this.bounds.right) - (ItemOptions.m11150$$Nest$fgetscrimViewPadding(this.this$0) * this.dimProgress), ((-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).top) + this.bounds.bottom) - (ItemOptions.m11150$$Nest$fgetscrimViewPadding(this.this$0) * this.dimProgress));
            this.clipPath.addRoundRect(r42, ItemOptions.m11151$$Nest$fgetscrimViewRoundRadius(this.this$0) * this.dimProgress, ItemOptions.m11151$$Nest$fgetscrimViewRoundRadius(this.this$0) * this.dimProgress, Path.Direction.CW);
            r15.clipPath(this.clipPath);
        L94:
            boolean r43 = ItemOptions.m11147$$Nest$fgetscrimView(this.this$0) instanceof SharedPhotoVideoCell2;
            ItemOptions r62 = this.this$0;
            if (r43 == true) goto L97;
            float r33 = r5;
            if ((ItemOptions.m11147$$Nest$fgetscrimView(r62) instanceof GiftSheet.GiftCell) == false) goto L111;
            if (ItemOptions.m11132$$Nest$fgetanimateToWidth(this.this$0) == 0) goto L111;
            if (ItemOptions.m11131$$Nest$fgetanimateToHeight(this.this$0) == 0) goto L111;
            if (ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getAlpha() < 1.0f) goto L109;
            ((GiftSheet.GiftCell) ItemOptions.m11147$$Nest$fgetscrimView(this.this$0)).customDraw(this, r15, r33, r09, this.dimProgress);
        L119:
            r15.restore();
            return;
        L109:
            r15.saveLayerAlpha(0.0f, 0.0f, r33, r09, (int) (this.dimProgress * 255.0f), 31);
            float r010 = AndroidUtilities.lerp(1.0f, 0.9f, this.dimProgress);
            r15.scale(r010, r010, r33 / 2.0f, r09 / 2.0f);
            ((GiftSheet.GiftCell) ItemOptions.m11147$$Nest$fgetscrimView(this.this$0)).customDraw(this, r15, r33, r09, this.dimProgress);
            r15.restore();
        L111:
            if (ItemOptions.m11130$$Nest$fgetallowMoveScrim(this.this$0) == false) goto L113;
            r15.saveLayerAlpha(0.0f, 0.0f, ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getWidth(), ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getHeight(), (int) (this.dimProgress * 255.0f), 31);
        L114:
            boolean r011 = ItemOptions.m11147$$Nest$fgetscrimView(this.this$0) instanceof ScrimView;
            ItemOptions r1 = this.this$0;
            if (r011 == false) goto L117;
            ((ScrimView) ItemOptions.m11147$$Nest$fgetscrimView(r1)).drawScrim(r15, this.dimProgress);
        L118:
            r15.restore();
            goto L119
        L117:
            r15.translate(-ItemOptions.m11147$$Nest$fgetscrimView(r1).getScrollX(), -ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getScrollY());
            ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).draw(r15);
            goto L118
        L113:
            r15.save();
            goto L114
        L97:
            if (ItemOptions.m11147$$Nest$fgetscrimView(r62).getAlpha() < 1.0f) goto L99;
            ((SharedPhotoVideoCell2) ItemOptions.m11147$$Nest$fgetscrimView(this.this$0)).customDraw(this, r15, r5, r09, this.dimProgress);
            goto L119
        L99:
            float r34 = r5;
            r15.saveLayerAlpha(0.0f, 0.0f, r34, r09, (int) (this.dimProgress * 255.0f), 31);
            float r012 = AndroidUtilities.lerp(1.0f, 0.9f, this.dimProgress);
            r15.scale(r012, r012, r34 / 2.0f, r09 / 2.0f);
            ((SharedPhotoVideoCell2) ItemOptions.m11147$$Nest$fgetscrimView(this.this$0)).customDraw(this, r15, r34, r09, this.dimProgress);
            r15.restore();
            goto L119
        L92:
            this.bounds.set(0.0f, 0.0f, getWidth(), getHeight());
            goto L93
        L88:
            if (ItemOptions.m11151$$Nest$fgetscrimViewRoundRadius(this.this$0) <= 0) goto L94;
        L67:
            if (ItemOptions.m11131$$Nest$fgetanimateToHeight(this.this$0) == 0) goto L70;
            r5 = AndroidUtilities.lerp(ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getWidth(), ItemOptions.m11132$$Nest$fgetanimateToWidth(this.this$0), r07);
            r08 = AndroidUtilities.lerp(ItemOptions.m11147$$Nest$fgetscrimView(this.this$0).getHeight(), ItemOptions.m11131$$Nest$fgetanimateToHeight(this.this$0), r07);
            goto L69
        L63:
            r15.translate(ItemOptions.m11143$$Nest$fgetpoint(r92)[0], ItemOptions.m11143$$Nest$fgetpoint(this.this$0)[1]);
            goto L65
        L53:
            r10 = 1.0f;
            goto L54
        L55:
            float r013 = -ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(r82).left;
            float r85 = (-ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).top) + ItemOptions.m11143$$Nest$fgetpoint(this.this$0)[1];
            float r93 = this.clipTop;
            if (ItemOptions.m11133$$Nest$fgetblur(this.this$0) == false) goto L58;
            float r102 = 1.0f - this.dimProgress;
        L59:
            r15.clipRect(r013, (r85 - (r93 * r102)) + 1.0f, getMeasuredWidth() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).right, getMeasuredHeight() + ItemOptions.m11152$$Nest$fgetviewAdditionalOffsets(this.this$0).bottom);
            goto L60
        L58:
            r102 = 1.0f;
            goto L59
        L47:
            if (this.clipBottom == 0.0f) goto L60;
        L122:
            return;
        L5:
            r15.drawColor(Theme.multAlpha(this.dim, this.dimProgress));
            goto L7
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            Blur3Utils.checkBitmapSourceMatrixScale(ItemOptions.m11146$$Nest$fgetscrimBlur3SourceBitmap(this.this$0), this);
            if (ItemOptions.m11141$$Nest$fgetlayout(this.this$0) == null) goto L6;
            ItemOptions.m11141$$Nest$fgetlayout(this.this$0).invalidate();
            return;
        }

        public void setProgress(float r2) {
            if (this.dimProgress != r2) goto L5;
            return;
        L5:
            this.dimProgress = r2;
            invalidate();
        }
    }

    public interface ScrimView {
        void drawScrim(Canvas r1, float r2);

        /* JADX WARN: Multi-variable type inference failed */
        default void getBounds(RectF r3) {
            if ((this instanceof View) == false) goto L6;
            View r2 = (View) this;
            r3.set(0.0f, 0.0f, r2.getWidth(), r2.getHeight());
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$9IjnIXChlJlPs492gQQ5iWEGIQU(ItemOptions r0, Runnable r1, View r2) {
        r0.lambda$putPremiumLock$10(r1, r2);
    }

    /* renamed from: $r8$lambda$9iWVJZMQ29P-Ac_crTL8IAKbqrk, reason: not valid java name */
    public static /* synthetic */ void m11123$r8$lambda$9iWVJZMQ29PAc_crTL8IAKbqrk(ItemOptions r0, Runnable r1, View r2) {
        r0.lambda$addChecked$3(r1, r2);
    }

    /* renamed from: $r8$lambda$BUhHQma1xskccHGOUC_-yilj5WM, reason: not valid java name */
    public static /* synthetic */ void m11124$r8$lambda$BUhHQma1xskccHGOUC_yilj5WM(ItemOptions r0, PopupSwipeBackLayout r1, float r2, float r3) {
        r0.lambda$makeSwipeback$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$ClL4jawq1IwmKtTZInqRg3zP2Hw(ItemOptions r0, View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        r0.lambda$installFollowListeners$18(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$EOS6U8CGdwHEy6SFA1UeUUeLuSU(ItemOptions r0) {
        r0.lambda$installFollowListeners$17();
    }

    public static /* synthetic */ void $r8$lambda$IU_cW_K_lzYaLOpihhjqyb0LOBY(ItemOptions r0, Runnable r1, View r2) {
        r0.lambda$addChat$7(r1, r2);
    }

    /* renamed from: $r8$lambda$JdlnDv1S2iw88B5r4Qh0zll-YpM, reason: not valid java name */
    public static /* synthetic */ void m11125$r8$lambda$JdlnDv1S2iw88B5r4Qh0zllYpM(ItemOptions r0, KeyEvent r1) {
        r0.lambda$init$0(r1);
    }

    /* renamed from: $r8$lambda$P5S9Tlq9xZfF-gvcS2T00vqa2EM, reason: not valid java name */
    public static /* synthetic */ void m11126$r8$lambda$P5S9Tlq9xZfFgvcS2T00vqa2EM(ItemOptions r0, Runnable r1, View r2) {
        r0.lambda$addProfile$13(r1, r2);
    }

    /* renamed from: $r8$lambda$Q-3EFUlgu-QMZR53r-DZ3J3-eP0, reason: not valid java name */
    public static /* synthetic */ boolean m11127$r8$lambda$Q3EFUlguQMZR53rDZ3J3eP0(DimView r0) {
        r0.invalidate();
        return true;
    }

    /* renamed from: $r8$lambda$V_HhOruIuOuYFIRh2k-2pAz1DLA, reason: not valid java name */
    public static /* synthetic */ boolean m11128$r8$lambda$V_HhOruIuOuYFIRh2k2pAz1DLA(ItemOptions r0, Runnable r1, View r2) {
        return r0.lambda$addBot$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$d9Eg07xeckUnTf5ZYyeOwMcKcU4(ItemOptions r0, Runnable r1, View r2) {
        r0.lambda$addAccount$8(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$fbr4EuECeuaHsnCnoCn0mU2U3Us(ItemOptions r0, Runnable r1, View r2) {
        return r0.lambda$addChecked$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$iSibFNqWjwA5BV23rz5zC_y1wuE(ItemOptions r0, Runnable r1, View r2) {
        r0.lambda$add$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$jX6QVIUQ8d_uI5KymFMY0BvI7uU(ItemOptions r0, Runnable r1, View r2) {
        r0.lambda$addProfileCustom$14(r1, r2);
    }

    /* renamed from: $r8$lambda$o-BiutaayYipCA_15J_vewANvKQ, reason: not valid java name */
    public static /* synthetic */ boolean m11129$r8$lambda$oBiutaayYipCA_15J_vewANvKQ(WeakReference r4, View r5, MotionEvent r6) {
        ItemOptions r42 = (ItemOptions) r4.get();
        if (r42 == null) goto L23;
        ActionBarPopupWindow r1 = r42.actionBarPopupWindow;
        if (r1 == null) goto L23;
        if (r1.isShowing() == false) goto L23;
        if (r5.getParent() == null) goto L12;
        r5.getParent().requestDisallowInterceptTouchEvent(true);
    L12:
        int r12 = r6.getActionMasked();
        if (r12 != 2) goto L16;
        r42.updateHover((int) r6.getRawX(), (int) r6.getRawY());
        return true;
    L16:
        if (r12 != 1) goto L20;
        r42.releaseHover((int) r6.getRawX(), (int) r6.getRawY());
        r5.setOnTouchListener(null);
        r42.hoverReleaseListener = null;
        return true;
    L20:
        if (r12 != 3) goto L22;
        r42.cancelHover();
        r5.setOnTouchListener(null);
        r42.hoverReleaseListener = null;
    L22:
        return true;
    L23:
        r5.setOnTouchListener(null);
        return false;
    }

    public static /* synthetic */ void $r8$lambda$qUsXdeKwn4JFlHiRgjWuAHfgRpg(DimView r0, ValueAnimator r1) {
        r0.setProgress(((Float) r1.getAnimatedValue()).floatValue());
    }

    public static /* synthetic */ void $r8$lambda$rthUlxeDxDmkKqdcYYxFvk62QVQ(ItemOptions r0, KeyEvent r1) {
        r0.lambda$addSpaceGap$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$sq6G7HJLLiw1C5A7_PncUddzhY4(ItemOptions r0, ValueAnimator r1) {
        r0.lambda$show$16(r1);
    }

    public static /* synthetic */ void $r8$lambda$vSz0p4NuWPEworjG4CXrdcrV69Q(boolean r0, HashSet r1, int r2, Utilities.Callback r3, StoriesController.StoryAlbum r4, View r5) {
        if (r0 == false) goto L4;
        r1.remove(Integer.valueOf(r2));
    L5:
        r3.run(r4);
        return;
    L4:
        r1.add(Integer.valueOf(r2));
        goto L5
    }

    public static /* synthetic */ void $r8$lambda$wBZWM0Rm2EMVtz5qlkClUoxbnRM(ItemOptions r0, Runnable r1, View r2) {
        r0.lambda$add$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$xVFP49gFWyQ9hgjaurd2eWMZ3K0(Runnable r0, View r1) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$xxoyc40g_YsWpV8u7TbRnZpAM6g(ItemOptions r0, Runnable r1, View r2) {
        r0.lambda$addBot$5(r1, r2);
    }

    /* renamed from: -$$Nest$fgetallowMoveScrim, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11130$$Nest$fgetallowMoveScrim(ItemOptions r0) {
        return r0.allowMoveScrim;
    }

    /* renamed from: -$$Nest$fgetanimateToHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11131$$Nest$fgetanimateToHeight(ItemOptions r0) {
        return r0.animateToHeight;
    }

    /* renamed from: -$$Nest$fgetanimateToWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11132$$Nest$fgetanimateToWidth(ItemOptions r0) {
        return r0.animateToWidth;
    }

    /* renamed from: -$$Nest$fgetblur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11133$$Nest$fgetblur(ItemOptions r0) {
        return r0.blur;
    }

    /* renamed from: -$$Nest$fgetblurForMenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11134$$Nest$fgetblurForMenu(ItemOptions r0) {
        return r0.blurForMenu;
    }

    /* renamed from: -$$Nest$fgetdimAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11135$$Nest$fgetdimAlpha(ItemOptions r0) {
        return r0.dimAlpha;
    }

    /* renamed from: -$$Nest$fgetdimView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DimView m11136$$Nest$fgetdimView(ItemOptions r0) {
        return r0.dimView;
    }

    /* renamed from: -$$Nest$fgetdismissListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m11137$$Nest$fgetdismissListener(ItemOptions r0) {
        return r0.dismissListener;
    }

    /* renamed from: -$$Nest$fgetdrawScrim, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11138$$Nest$fgetdrawScrim(ItemOptions r0) {
        return r0.drawScrim;
    }

    /* renamed from: -$$Nest$fgetfragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m11139$$Nest$fgetfragment(ItemOptions r0) {
        return r0.fragment;
    }

    /* renamed from: -$$Nest$fgethideScrimUnder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11140$$Nest$fgethideScrimUnder(ItemOptions r0) {
        return r0.hideScrimUnder;
    }

    /* renamed from: -$$Nest$fgetlayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewGroup m11141$$Nest$fgetlayout(ItemOptions r0) {
        return r0.layout;
    }

    /* renamed from: -$$Nest$fgetmaxHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11142$$Nest$fgetmaxHeight(ItemOptions r0) {
        return r0.maxHeight;
    }

    /* renamed from: -$$Nest$fgetpoint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m11143$$Nest$fgetpoint(ItemOptions r0) {
        return r0.point;
    }

    /* renamed from: -$$Nest$fgetpointContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewGroup m11144$$Nest$fgetpointContainer(ItemOptions r0) {
        return r0.pointContainer;
    }

    /* renamed from: -$$Nest$fgetpreDrawListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewTreeObserver.OnPreDrawListener m11145$$Nest$fgetpreDrawListener(ItemOptions r0) {
        return r0.preDrawListener;
    }

    /* renamed from: -$$Nest$fgetscrimBlur3SourceBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceBitmap m11146$$Nest$fgetscrimBlur3SourceBitmap(ItemOptions r0) {
        return r0.scrimBlur3SourceBitmap;
    }

    /* renamed from: -$$Nest$fgetscrimView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m11147$$Nest$fgetscrimView(ItemOptions r0) {
        return r0.scrimView;
    }

    /* renamed from: -$$Nest$fgetscrimViewBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m11148$$Nest$fgetscrimViewBackground(ItemOptions r0) {
        return r0.scrimViewBackground;
    }

    /* renamed from: -$$Nest$fgetscrimViewBackgroundShadowColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11149$$Nest$fgetscrimViewBackgroundShadowColor(ItemOptions r0) {
        return r0.scrimViewBackgroundShadowColor;
    }

    /* renamed from: -$$Nest$fgetscrimViewPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11150$$Nest$fgetscrimViewPadding(ItemOptions r0) {
        return r0.scrimViewPadding;
    }

    /* renamed from: -$$Nest$fgetscrimViewRoundRadius, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11151$$Nest$fgetscrimViewRoundRadius(ItemOptions r0) {
        return r0.scrimViewRoundRadius;
    }

    /* renamed from: -$$Nest$fgetviewAdditionalOffsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Rect m11152$$Nest$fgetviewAdditionalOffsets(ItemOptions r0) {
        return r0.viewAdditionalOffsets;
    }

    /* renamed from: -$$Nest$fputdimAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11153$$Nest$fputdimAnimator(ItemOptions r0, ValueAnimator r1) {
        r0.dimAnimator = r1;
    }

    /* renamed from: -$$Nest$fputdismissListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11154$$Nest$fputdismissListener(ItemOptions r0, Runnable r1) {
        r0.dismissListener = r1;
    }

    /* renamed from: -$$Nest$mclearHoverListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11155$$Nest$mclearHoverListener(ItemOptions r0) {
        r0.clearHoverListener();
    }

    /* renamed from: -$$Nest$mdismissDim, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11156$$Nest$mdismissDim(ItemOptions r0, ViewGroup r1) {
        r0.dismissDim(r1);
    }

    /* renamed from: -$$Nest$mremoveFollowListeners, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11157$$Nest$mremoveFollowListeners(ItemOptions r0) {
        r0.removeFollowListeners();
    }

    private ItemOptions(ViewGroup r8, Theme.ResourcesProvider r9, View r10, boolean r11, boolean r12) {
        this(r8, r9, r10, r11, r12, false);
    }

    private ItemOptions(ViewGroup r4, Theme.ResourcesProvider r5, View r6, boolean r7, boolean r8, boolean r9) {
        this.gravity = 5;
        this.point = new float[2];
        this.drawScrim = true;
        this.longPressSelectionEnabled = true;
        this.viewAdditionalOffsets = new Rect();
        this.dismissOnMoveOutside = false;
        this.dismissWithButtons = true;
        this.shiftDp = -4;
        this.followLoc = new int[2];
        this.hoverLoc = new int[2];
        if (r4 != null) goto L5;
        return;
    L5:
        if (r4.getContext() == null) goto L14;
        this.container = r4;
        this.resourcesProvider = r5;
        this.context = r4.getContext();
        this.scrimView = r6;
        if (AndroidUtilities.computePerceivedBrightness(Theme.getColor(Theme.key_windowBackgroundWhite, r5)) <= 0.705d) goto L10;
        int r42 = 102;
    L11:
        this.dimAlpha = r42;
        this.swipeback = r7;
        this.shownFromBottom = r8;
        this.useScrollView = r9;
        init();
        return;
    L10:
        r42 = 51;
        goto L11
    }

    private ItemOptions(ActionBarPopupWindow.ActionBarPopupWindowLayout r4, Theme.ResourcesProvider r5) {
        this.gravity = 5;
        this.point = new float[2];
        this.drawScrim = true;
        this.longPressSelectionEnabled = true;
        this.viewAdditionalOffsets = new Rect();
        this.dismissOnMoveOutside = false;
        this.dismissWithButtons = true;
        this.shiftDp = -4;
        this.followLoc = new int[2];
        this.hoverLoc = new int[2];
        this.context = r4.getContext();
        LinearLayout r42 = new LinearLayout(this.context);
        this.linearLayout = r42;
        r42.setOrientation(1);
        this.resourcesProvider = r5;
    }

    private ItemOptions(BaseFragment r4, View r5, boolean r6, boolean r7, boolean r8) {
        this.gravity = 5;
        this.point = new float[2];
        this.drawScrim = true;
        this.longPressSelectionEnabled = true;
        this.viewAdditionalOffsets = new Rect();
        this.dismissOnMoveOutside = false;
        this.dismissWithButtons = true;
        this.shiftDp = -4;
        this.followLoc = new int[2];
        this.hoverLoc = new int[2];
        if (r4.getContext() != null) goto L5;
        return;
    L5:
        BaseFragment r42 = downFragment(r4);
        this.fragment = r42;
        this.resourcesProvider = r42.getResourceProvider();
        this.context = r42.getContext();
        this.scrimView = r5;
        if (AndroidUtilities.computePerceivedBrightness(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider)) <= 0.705d) goto L8;
        int r43 = 102;
    L9:
        this.dimAlpha = r43;
        this.swipeback = r6;
        this.useScrollView = r7;
        this.shownFromBottom = r8;
        init();
        return;
    L8:
        r43 = 51;
        goto L9
    }

    public static void addAlbumsItemOptions(ItemOptions r23, StoriesController.StoriesCollections r24, HashSet<Integer> r25, boolean r26, Runnable r27, Utilities.Callback<StoriesController.StoryAlbum> r28) {
        AnonymousClass8 r2 = new AnonymousClass8(r23.getContext());
        LinearLayout r3 = new LinearLayout(r23.getContext());
        r2.addView(r3);
        r3.setOrientation(1);
        r23.addView(r2, LayoutHelper.createLinear(-1, -2));
        float r7 = 18.0f;
        if (r26 == false) goto L6;
        if (r27 == null) goto L6;
        ActionBarMenuSubItem r9 = new ActionBarMenuSubItem(r23.getContext(), 2, false, false, r23.resourcesProvider);
        r9.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        int r10 = Theme.key_actionBarDefaultSubmenuItem;
        r9.setColors(Theme.getColor(r10, r23.resourcesProvider), Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon, r23.resourcesProvider));
        r9.setSelectorColor(Theme.multAlpha(Theme.getColor(r10, r23.resourcesProvider), 0.12f));
        r9.setTextAndIcon(LocaleController.getString(R.string.StoriesAlbumNewAlbum), R.drawable.menu_album_add);
        r9.setOnClickListener(new ItemOptions$$ExternalSyntheticLambda18(r27));
        r3.addView(r9, LayoutHelper.createLinear(-1, -2));
    L6:
        ArrayList<StoriesController.StoryAlbum> r1 = r24.collections;
        int r92 = r1.size();
        int r102 = 0;
    L7:
        if (r102 >= r92) goto L15;
        StoriesController.StoryAlbum r11 = r1.get(r102);
        r102 = r102 + 1;
        StoriesController.StoryAlbum r112 = r11;
        int r15 = r112.album_id;
        boolean r13 = r25.contains(Integer.valueOf(r15));
        ActionBarMenuSubItem r16 = new ActionBarMenuSubItem(r23.getContext(), 2, false, false, r23.resourcesProvider);
        r16.setChecked(r13);
        float r22 = r7;
        r16.setPadding(AndroidUtilities.dp(r22), 0, AndroidUtilities.dp(r22), 0);
        int r5 = Theme.key_actionBarDefaultSubmenuItem;
        r16.setColors(Theme.getColor(r5, r23.resourcesProvider), Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon, r23.resourcesProvider));
        r16.setSelectorColor(Theme.multAlpha(Theme.getColor(r5, r23.resourcesProvider), 0.12f));
        TLRPC.Photo r4 = r112.icon_photo;
        if (r4 == null) goto L13;
        ArrayList<TLRPC.PhotoSize> r42 = r4.sizes;
        if (r42 == null) goto L13;
        boolean r72 = true;
        r16.setTextAndIcon(r112.title, ImageLocation.getForObject(FileLoader.getClosestPhotoSizeWithSize(r112.icon_photo.sizes, AndroidUtilities.dp(24.0f), false, FileLoader.getClosestPhotoSizeWithSize(r42, 50), true), r112.icon_photo), "50_50", null, null);
        ActionBarMenuSubItem r43 = r16;
    L14:
        r43.setOnClickListener(new ItemOptions$$ExternalSyntheticLambda19(r13, r25, r15, r28, r112));
        r3.addView(r43, LayoutHelper.createLinear(-1, -2));
        r7 = r22;
    L13:
        r43 = r16;
        r72 = true;
        r43.setTextAndIcon(r112.title, R.drawable.msg_folders);
        goto L14
    }

    private boolean canHandleCapturedTouch() {
        if (this.layout == null) goto L12;
        ActionBarPopupWindow r0 = this.actionBarPopupWindow;
        if (r0 == null) goto L9;
        if (r0.isShowing() == false) goto L9;
        return true;
    L9:
        if (this.layout.getParent() == null) goto L15;
        return true;
    L15:
        return false;
    L12:
        return false;
    }

    private void cancelHover() {
        View r0 = this.hoveredItem;
        if (r0 == null) goto L6;
        r0.setPressed(false);
        this.hoveredItem = null;
        return;
    }

    private void clearHoverListener() {
        cancelHover();
        if (this.hoverReleaseListener == null) goto L7;
        View r0 = this.scrimView;
        if (r0 == null) goto L7;
        r0.setOnTouchListener(null);
    L7:
        this.hoverReleaseListener = null;
    }

    private static void clearPressedViews(ViewGroup r4) {
        int r1 = 0;
    L4:
        if (r1 >= r4.getChildCount()) goto L15;
        View r2 = r4.getChildAt(r1);
        if (r2 == null) goto L14;
        if (r2.isPressed() == false) goto L12;
        r2.setPressed(false);
    L12:
        if ((r2 instanceof ViewGroup) == false) goto L14;
        clearPressedViews((ViewGroup) r2);
    L14:
        r1 = r1 + 1;
        goto L4
    }

    private void dismissDim(ViewGroup r5) {
        DimView r0 = this.dimView;
        if (r0 != null) goto L5;
        return;
    L5:
        this.dimView = null;
        ValueAnimator r1 = this.dimAnimator;
        if (r1 == null) goto L8;
        r1.cancel();
    L8:
        ValueAnimator r12 = ValueAnimator.ofFloat(new float[]{r0.dimProgress, 0.0f});
        this.dimAnimator = r12;
        r12.addUpdateListener(new ItemOptions$$ExternalSyntheticLambda16(r0));
        this.dimAnimator.addListener(new AnonymousClass7(this, r0, r5));
        boolean r52 = this.allowMoveScrim;
        ValueAnimator r02 = this.dimAnimator;
        if (r52 == false) goto L11;
        r02.setDuration(380);
        this.dimAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
    L12:
        this.dimAnimator.start();
        return;
    L11:
        r02.setDuration(150);
        goto L12
    }

    public static boolean dispatchCapturedTouchEvent(ViewGroup r6, MotionEvent r7) {
        boolean r0 = false;
        if (r6 != null) goto L5;
    L17:
        return r0;
    L5:
        if (r6.getParent() == null) goto L17;
        MotionEvent r1 = MotionEvent.obtain(r7);
        r6.getLocationOnScreen(new int[2]);
        r1.offsetLocation(-r3[0], -r3[1]);
        r6.dispatchTouchEvent(r1);
        if (r7.getAction() != 2) goto L11;
        findAndHighlightViews(r6, r7.getRawX(), r7.getRawY());
    L16:
        r1.recycle();
        goto L17
    L11:
        if (r7.getAction() != 1) goto L14;
        r0 = performClickAtViewGroup(r6, r7.getRawX(), r7.getRawY());
        clearPressedViews(r6);
        goto L16
    L14:
        if (r7.getAction() != 3) goto L16;
        clearPressedViews(r6);
        goto L16
    }

    private static BaseFragment downFragment(BaseFragment r2) {
        if ((r2 instanceof ProfileActivity) == false) goto L7;
        if (((ProfileActivity) r2).hasMainTabs == false) goto L7;
    L18:
        INavigationLayout r0 = r2.getParentLayout();
        if (r0 == null) goto L23;
        BaseFragment r02 = r0.getSafeLastFragment();
        if ((r02 instanceof MainTabsActivity) == false) goto L23;
        return r02;
    L23:
        return r2;
    L7:
        if ((r2 instanceof DialogsActivity) == false) goto L11;
        if (((DialogsActivity) r2).hasMainTabs == true) goto L18;
    L11:
        if ((r2 instanceof ContactsActivity) == false) goto L15;
        if (((ContactsActivity) r2).hasMainTabs == true) goto L18;
    L15:
        if ((r2 instanceof SettingsActivity) == false) goto L23;
        if (((SettingsActivity) r2).hasMainTabs == false) goto L23;
        goto L18
    }

    private static void findAndHighlightViews(ViewGroup r7, float r8, float r9) {
        int r1 = 0;
    L4:
        if (r1 >= r7.getChildCount()) goto L35;
        View r2 = r7.getChildAt(r1);
        if (r2 == null) goto L34;
        if (r2.getVisibility() != 0) goto L34;
        int[] r3 = new int[2];
        r2.getLocationOnScreen(r3);
        if (r8 >= r3[0]) goto L13;
    L19:
        boolean r4 = false;
    L21:
        if (r2.isClickable() == false) goto L32;
        if (r4 == false) goto L27;
        if (r2.isPressed() == true) goto L27;
        r2.performHapticFeedback(VibratorUtils.getType(4), 1);
    L27:
        if (r2.isPressed() == r4) goto L29;
        r2.setPressed(r4);
    L29:
        if (r4 == false) goto L32;
        r2.drawableHotspotChanged(r8 - r3[0], r9 - r3[1]);
    L32:
        if ((r2 instanceof ViewGroup) == false) goto L34;
        findAndHighlightViews((ViewGroup) r2, r8, r9);
        goto L34
    L13:
        if (r8 > (r4 + r2.getWidth())) goto L19;
        if (r9 < r3[1]) goto L19;
        if (r9 > (r4 + r2.getHeight())) goto L19;
        r4 = true;
    L34:
        r1 = r1 + 1;
        goto L4
    }

    private static View findItemAt(View r6, int r7, int r8) {
        if (r6 != null) goto L5;
    L28:
        return null;
    L5:
        if (r6.getVisibility() != 0) goto L28;
        int[] r1 = new int[2];
        r6.getLocationOnScreen(r1);
        int r2 = r1[0];
        int r12 = r1[1];
        int r4 = r6.getWidth() + r2;
        int r5 = r6.getHeight() + r12;
        if (r7 < r2) goto L28;
        if (r7 >= r4) goto L28;
        if (r8 < r12) goto L28;
        if (r8 >= r5) goto L28;
        if ((r6 instanceof ViewGroup) == false) goto L22;
        ViewGroup r13 = (ViewGroup) r6;
        int r22 = r13.getChildCount() - 1;
    L16:
        if (r22 < 0) goto L22;
        View r3 = findItemAt(r13.getChildAt(r22), r7, r8);
        if (r3 != null) goto L19;
        r22 = r22 - 1;
        goto L16
    L19:
        return r3;
    L22:
        if (r6.isClickable() == false) goto L28;
        if (r6.isEnabled() == false) goto L28;
        if ((r6 instanceof ActionBarPopupWindow.GapView) == true) goto L28;
        return r6;
    }

    public static void getPointOnScreen(View r3, ViewGroup r4, float[] r5) {
        if (r3 == null) goto L24;
        if (r4 == null) goto L25;
        float r0 = 0.0f;
        float r1 = 0.0f;
    L6:
        if (r3 == r4) goto L18;
        r0 = r0 + r3.getY();
        r1 = r1 + r3.getX();
        if ((r3 instanceof ScrollView) == false) goto L10;
    L11:
        r1 = r1 - r3.getScrollX();
        r0 = r0 - r3.getScrollY();
    L13:
        if ((r3.getParent() instanceof View) == false) goto L18;
        r3 = (View) r3.getParent();
        if ((r3 instanceof ViewGroup) == true) goto L6;
        return;
    L10:
        if ((r3 instanceof HorizontalScrollView) == false) goto L13;
    L18:
        r5[0] = r1 - r4.getPaddingLeft();
        r5[1] = r0 - r4.getPaddingTop();
        return;
    L25:
        return;
    }

    private void init() {
        Context r2 = this.context;
        int r3 = R.drawable.popup_fixed_alert4;
        Theme.ResourcesProvider r4 = this.resourcesProvider;
        boolean r1 = this.swipeback;
        int r6 = 0;
        if (this.shownFromBottom == false) goto L5;
        int r5 = 2;
    L6:
        int r12 = (r1 ? 1 : 0) | r5;
        if (this.useScrollView == true) goto L9;
        r6 = 4;
    L9:
        AnonymousClass1 r0 = new AnonymousClass1(this, r2, r3, r4, r12 | r6);
        this.lastLayout = r0;
        r0.setDispatchKeyEventListener(new ItemOptions$$ExternalSyntheticLambda0(this));
        this.layout = this.lastLayout;
        return;
    L5:
        r5 = 0;
        goto L6
    }

    private void installFollowListeners() {
        removeFollowListeners();
        View r0 = this.scrimView;
        if (r0 != null) goto L5;
        return;
    L5:
        this.followingView = r0;
        r0.getLocationOnScreen(this.followLoc);
        this.followScrollListener = new ItemOptions$$ExternalSyntheticLambda12(this);
        this.followingView.getViewTreeObserver().addOnScrollChangedListener(this.followScrollListener);
        ItemOptions$$ExternalSyntheticLambda13 r02 = new ItemOptions$$ExternalSyntheticLambda13(this);
        this.followLayoutListener = r02;
        this.followingView.addOnLayoutChangeListener(r02);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void installHoverReleaseListener() {
        View r0 = this.scrimView;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.getParent() == null) goto L8;
        this.scrimView.getParent().requestDisallowInterceptTouchEvent(true);
    L8:
        WeakReference r02 = new WeakReference(this);
        View r1 = this.scrimView;
        ItemOptions$$ExternalSyntheticLambda5 r2 = new ItemOptions$$ExternalSyntheticLambda5(r02);
        this.hoverReleaseListener = r2;
        r1.setOnTouchListener(r2);
    }

    private /* synthetic */ void lambda$add$2(Runnable r1, View r2) {
        if (r1 == null) goto L5;
        r1.run();
    L5:
        if (this.dismissWithButtons == false) goto L8;
        dismiss();
        return;
    }

    private /* synthetic */ void lambda$add$9(Runnable r1, View r2) {
        if (r1 == null) goto L5;
        r1.run();
    L5:
        if (this.dismissWithButtons == false) goto L8;
        dismiss();
        return;
    }

    private /* synthetic */ void lambda$addAccount$8(Runnable r1, View r2) {
        if (r1 == null) goto L5;
        r1.run();
    L5:
        if (this.dismissWithButtons == false) goto L8;
        dismiss();
        return;
    }

    private /* synthetic */ void lambda$addBot$5(Runnable r1, View r2) {
        if (r1 == null) goto L5;
        r1.run();
    L5:
        if (this.dismissWithButtons == false) goto L8;
        dismiss();
        return;
    }

    private /* synthetic */ boolean lambda$addBot$6(Runnable r1, View r2) {
        if (r1 == null) goto L5;
        r1.run();
    L5:
        if (this.dismissWithButtons == false) goto L9;
        dismiss();
        return true;
    L9:
        return true;
    }

    private /* synthetic */ void lambda$addChat$7(Runnable r1, View r2) {
        if (r1 == null) goto L5;
        r1.run();
    L5:
        if (this.dismissWithButtons == false) goto L8;
        dismiss();
        return;
    }

    private /* synthetic */ void lambda$addChecked$3(Runnable r1, View r2) {
        if (r1 == null) goto L5;
        r1.run();
    L5:
        if (this.dismissWithButtons == false) goto L8;
        dismiss();
        return;
    }

    private /* synthetic */ boolean lambda$addChecked$4(Runnable r1, View r2) {
        if (r1 == null) goto L5;
        r1.run();
    L5:
        if (this.dismissWithButtons == false) goto L9;
        dismiss();
        return true;
    L9:
        return true;
    }

    private /* synthetic */ void lambda$addProfile$13(Runnable r1, View r2) {
        dismiss();
        if (r1 == null) goto L6;
        r1.run();
        return;
    }

    private /* synthetic */ void lambda$addProfileCustom$14(Runnable r1, View r2) {
        dismiss();
        if (r1 == null) goto L6;
        r1.run();
        return;
    }

    private /* synthetic */ void lambda$addSpaceGap$11(KeyEvent r3) {
        if (r3.getKeyCode() == 4) goto L5;
        return;
    L5:
        if (r3.getRepeatCount() != 0) goto L13;
        ActionBarPopupWindow r32 = this.actionBarPopupWindow;
        if (r32 != null) goto L9;
        return;
    L9:
        if (r32.isShowing() == false) goto L15;
        dismiss();
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$init$0(KeyEvent r3) {
        if (r3.getKeyCode() == 4) goto L5;
        return;
    L5:
        if (r3.getRepeatCount() != 0) goto L13;
        ActionBarPopupWindow r32 = this.actionBarPopupWindow;
        if (r32 != null) goto L9;
        return;
    L9:
        if (r32.isShowing() == false) goto L15;
        dismiss();
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$installFollowListeners$17() {
        if (this.followingView == null) goto L15;
        ActionBarPopupWindow r0 = this.actionBarPopupWindow;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.isShowing() == false) goto L18;
        int[] r02 = new int[2];
        this.followingView.getLocationOnScreen(r02);
        int r2 = r02[0];
        int[] r3 = this.followLoc;
        if (r2 == r3[0]) goto L12;
    L13:
        r3[0] = r2;
        r3[1] = r02[1];
        reposition();
        return;
    L12:
        if (r02[1] != r3[1]) goto L13;
        return;
    L18:
        return;
    }

    private /* synthetic */ void lambda$installFollowListeners$18(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        if (isShown() == false) goto L6;
        reposition();
        return;
    }

    private /* synthetic */ void lambda$makeSwipeback$1(PopupSwipeBackLayout r2, float r3, float r4) {
        if (r3 != 0.0f) goto L7;
        if (r4 != 0.0f) goto L7;
    L10:
        this.dontDismiss = false;
        return;
    L7:
        if (r3 == 1.0f) goto L9;
        return;
    L9:
        if (r4 == 1.0f) goto L10;
    }

    private /* synthetic */ void lambda$putPremiumLock$10(Runnable r2, View r3) {
        if (r2 == null) goto L5;
        int r0 = -this.shiftDp;
        this.shiftDp = r0;
        AndroidUtilities.shakeViewSpring(r3, r0);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        r2.run();
        return;
    }

    private /* synthetic */ void lambda$show$16(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        DimView r0 = this.dimView;
        if (r0 == null) goto L6;
        r0.setProgress(r12);
        return;
    }

    public static ItemOptions makeOptions(ViewGroup r1, View r2) {
        return makeOptions(r1, null, r2);
    }

    public static ItemOptions makeOptions(ViewGroup r6, Theme.ResourcesProvider r7, View r8) {
        return new ItemOptions(r6, r7, r8, false, false);
    }

    public static ItemOptions makeOptions(ViewGroup r6, Theme.ResourcesProvider r7, View r8, boolean r9) {
        return new ItemOptions(r6, r7, r8, r9, false);
    }

    public static ItemOptions makeOptions(ViewGroup r6, Theme.ResourcesProvider r7, View r8, boolean r9, boolean r10) {
        return new ItemOptions(r6, r7, r8, r9, r10);
    }

    public static ItemOptions makeOptions(ViewGroup r7, Theme.ResourcesProvider r8, View r9, boolean r10, boolean r11, boolean r12) {
        return new ItemOptions(r7, r8, r9, r10, r11, r12);
    }

    public static ItemOptions makeOptions(BaseFragment r6, View r7) {
        return new ItemOptions(r6, r7, false, true, false);
    }

    public static ItemOptions makeOptions(BaseFragment r6, View r7, boolean r8) {
        return new ItemOptions(r6, r7, r8, true, false);
    }

    public static ItemOptions makeOptions(BaseFragment r6, View r7, boolean r8, boolean r9) {
        return new ItemOptions(r6, r7, r8, !r9, false);
    }

    private static boolean performClickAtViewGroup(ViewGroup r6, float r7, float r8) {
        int r1 = 0;
    L4:
        if (r1 >= r6.getChildCount()) goto L28;
        View r2 = r6.getChildAt(r1);
        if (r2 == null) goto L27;
        if (r2.getVisibility() != 0) goto L27;
        int[] r3 = new int[2];
        r2.getLocationOnScreen(r3);
        if (r7 < r3[0]) goto L27;
        if (r7 > (r4 + r2.getWidth())) goto L27;
        if (r8 < r3[1]) goto L27;
        if (r8 > (r3 + r2.getHeight())) goto L27;
        if (r2.isClickable() == true) goto L20;
        if ((r2 instanceof ViewGroup) == false) goto L27;
        if (performClickAtViewGroup((ViewGroup) r2, r7, r8) == false) goto L27;
        return true;
    L20:
        r2.performClick();
        return true;
    L27:
        r1 = r1 + 1;
        goto L4
    L28:
        return false;
    }

    private void releaseHover(int r4, int r5) {
        updateHover(r4, r5);
        View r0 = this.hoveredItem;
        if (r0 == null) goto L7;
        this.hoveredItem = null;
        r0.setPressed(false);
        r0.performClick();
        return;
    L7:
        if (this.dismissOnMoveOutside == false) goto L18;
        int[] r02 = new int[2];
        this.scrimView.getLocationOnScreen(r02);
        int r1 = r02[0];
        if (r4 >= r1) goto L11;
    L16:
        dismiss();
        return;
    L11:
        if (r4 > (r1 + this.scrimView.getWidth())) goto L16;
        int r42 = r02[1];
        if (r5 < r42) goto L16;
        if (r5 > (r42 + this.scrimView.getHeight())) goto L16;
        return;
    }

    private void removeFollowListeners() {
        View r0 = this.followingView;
        if (r0 != null) goto L5;
    L12:
        this.followScrollListener = null;
        this.followLayoutListener = null;
        this.followingView = null;
        return;
    L5:
        if (this.followScrollListener == null) goto L9;
        ViewTreeObserver r02 = r0.getViewTreeObserver();
        if (r02.isAlive() == false) goto L9;
        r02.removeOnScrollChangedListener(this.followScrollListener);
    L9:
        View.OnLayoutChangeListener r03 = this.followLayoutListener;
        if (r03 == null) goto L12;
        this.followingView.removeOnLayoutChangeListener(r03);
        goto L12
    }

    public static ItemOptions swipeback(ActionBarPopupWindow.ActionBarPopupWindowLayout r1, Theme.ResourcesProvider r2) {
        return new ItemOptions(r1, r2);
    }

    private void updateHover(int r5, int r6) {
        View r0 = findItemAt(this.layout, r5, r6);
        View r1 = this.hoveredItem;
        if (r0 == r1) goto L9;
        if (r1 == null) goto L6;
        r1.setPressed(false);
    L6:
        this.hoveredItem = r0;
        if (r0 == null) goto L9;
        r0.performHapticFeedback(VibratorUtils.getType(4), 1);
        this.hoveredItem.setPressed(true);
    L9:
        View r02 = this.hoveredItem;
        if (r02 == null) goto L13;
        r02.getLocationOnScreen(this.hoverLoc);
        View r03 = this.hoveredItem;
        int[] r4 = this.hoverLoc;
        r03.drawableHotspotChanged(r5 - r4[0], r6 - r4[1]);
        return;
    }

    public ActionBarMenuSubItem add() {
        ActionBarMenuSubItem r0 = new ActionBarMenuSubItem(this.context, false, false, this.resourcesProvider);
        add(r0);
        return r0;
    }

    public ItemOptions add(int r5, Drawable r6, CharSequence r7, int r8, int r9, Runnable r10) {
        if (this.context != null) goto L5;
        return this;
    L5:
        ActionBarMenuSubItem r0 = new ActionBarMenuSubItem(this.context, false, false, this.resourcesProvider);
        r0.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        if (r5 != 0) goto L10;
        if (r6 != null) goto L10;
        r0.setText(r7);
    L11:
        Integer r52 = this.textColor;
        if (r52 == null) goto L14;
        int r53 = r52.intValue();
    L15:
        Integer r62 = this.iconColor;
        if (r62 == null) goto L18;
        int r63 = r62.intValue();
    L19:
        r0.setColors(r53, r63);
        Integer r54 = this.selectorColor;
        if (r54 == null) goto L22;
        int r55 = r54.intValue();
    L23:
        r0.setSelectorColor(r55);
        r0.setOnClickListener(new ItemOptions$$ExternalSyntheticLambda2(this, r10));
        int r56 = this.minWidthDp;
        if (r56 <= 0) goto L27;
        r0.setMinimumWidth(AndroidUtilities.dp(r56));
        addView(r0, LayoutHelper.createLinear(this.minWidthDp, -2));
        return this;
    L27:
        addView(r0, LayoutHelper.createLinear(-1, -2));
        return this;
    L22:
        r55 = Theme.multAlpha(Theme.getColor(r9, this.resourcesProvider), 0.12f);
        goto L23
    L18:
        r63 = Theme.getColor(r8, this.resourcesProvider);
        goto L19
    L14:
        r53 = Theme.getColor(r9, this.resourcesProvider);
    L10:
        r0.setTextAndIcon(r7, r5, r6);
        goto L11
    }

    public ItemOptions add(int r8, CharSequence r9, int r10, int r11, Runnable r12) {
        return add(r8, null, r9, r10, r11, r12);
    }

    public ItemOptions add(int r7, CharSequence r8, int r9, Runnable r10) {
        return add(r7, r8, r9, r9, r10);
    }

    public ItemOptions add(int r5, CharSequence r6, CharSequence r7, Runnable r8) {
        if (this.context != null) goto L5;
        return this;
    L5:
        ActionBarMenuSubItem r0 = new ActionBarMenuSubItem(this.context, false, false, this.resourcesProvider);
        r0.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        if (r5 == 0) goto L8;
        r0.setTextAndIcon(r6, r5);
    L9:
        r0.setItemHeight(56);
        r0.setSubtext(r7);
        Integer r52 = this.textColor;
        if (r52 == null) goto L12;
        int r53 = r52.intValue();
    L13:
        Integer r62 = this.iconColor;
        if (r62 == null) goto L16;
        int r63 = r62.intValue();
    L17:
        r0.setColors(r53, r63);
        Integer r54 = this.selectorColor;
        if (r54 == null) goto L20;
        int r55 = r54.intValue();
    L21:
        r0.setSelectorColor(r55);
        r0.setOnClickListener(new ItemOptions$$ExternalSyntheticLambda11(this, r8));
        int r56 = this.minWidthDp;
        if (r56 <= 0) goto L25;
        r0.setMinimumWidth(AndroidUtilities.dp(r56));
        addView(r0, LayoutHelper.createLinear(this.minWidthDp, -2));
        return this;
    L25:
        addView(r0, LayoutHelper.createLinear(-1, -2));
        return this;
    L20:
        r55 = Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, this.resourcesProvider), 0.12f);
        goto L21
    L16:
        r63 = Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon, this.resourcesProvider);
        goto L17
    L12:
        r53 = Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, this.resourcesProvider);
        goto L13
    L8:
        r0.setText(r6);
        goto L9
    }

    public ItemOptions add(int r2, CharSequence r3, Runnable r4) {
        return add(r2, r3, false, r4);
    }

    public ItemOptions add(int r8, CharSequence r9, boolean r10, Runnable r11) {
        if (r10 == false) goto L5;
        int r0 = Theme.key_text_RedRegular;
    L4:
        int r4 = r0;
        if (r10 == false) goto L9;
        int r102 = Theme.key_text_RedRegular;
    L11:
        return add(r8, r9, r4, r102, r11);
    L9:
        r102 = Theme.key_actionBarDefaultSubmenuItem;
        goto L11
    L5:
        r0 = Theme.key_actionBarDefaultSubmenuItemIcon;
        goto L4
    }

    public ItemOptions add(Drawable r8, CharSequence r9, Runnable r10) {
        return add(0, r8, r9, Theme.key_actionBarDefaultSubmenuItemIcon, Theme.key_actionBarDefaultSubmenuItem, r10);
    }

    public ItemOptions add(CharSequence r2, CharSequence r3, Runnable r4) {
        return add(0, r2, r3, r4);
    }

    public ItemOptions add(CharSequence r2, Runnable r3) {
        return add(0, r2, false, r3);
    }

    public ItemOptions add(ActionBarMenuSubItem r4) {
        AndroidUtilities.removeFromParent(r4);
        r4.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        Integer r0 = this.textColor;
        if (r0 == null) goto L5;
        int r02 = r0.intValue();
    L6:
        Integer r1 = this.iconColor;
        if (r1 == null) goto L9;
        int r12 = r1.intValue();
    L10:
        r4.setColors(r02, r12);
        r4.setSelectorColor(Theme.getColor(Theme.key_groupcreate_sectionText, this.resourcesProvider));
        Integer r03 = this.selectorColor;
        if (r03 == null) goto L13;
        int r04 = r03.intValue();
    L14:
        r4.setSelectorColor(r04);
        int r05 = this.minWidthDp;
        if (r05 <= 0) goto L18;
        r4.setMinimumWidth(AndroidUtilities.dp(r05));
        addView(r4, LayoutHelper.createLinear(this.minWidthDp, -2));
        return this;
    L18:
        addView(r4, LayoutHelper.createLinear(-1, -2));
        return this;
    L13:
        r04 = Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, this.resourcesProvider), 0.12f);
        goto L14
    L9:
        r12 = Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon, this.resourcesProvider);
        goto L10
    L5:
        r02 = Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, this.resourcesProvider);
        goto L6
    }

    public ItemOptions addAccount(int r17, boolean r18, Runnable r19) {
        if (this.context != null) goto L5;
        return this;
    L5:
        int r1 = Theme.key_actionBarDefaultSubmenuItem;
        int r2 = Theme.key_actionBarDefaultSubmenuItemIcon;
        TLRPC.User r3 = UserConfig.getInstance(r17).getCurrentUser();
        ActionBarMenuSubItem r4 = new ActionBarMenuSubItem(this.context, false, false, this.resourcesProvider);
        r4.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        r4.setText(UserObject.getUserName(r3));
        r4.setClipToPadding(false);
        AnimatedEmojiSpan.TextViewEmojis r5 = r4.textView;
        if (r4.checkViewLeft == true) goto L8;
    L9:
        int r6 = AndroidUtilities.dp(43.0f);
    L12:
        if (r4.checkViewLeft == false) goto L15;
    L13:
        int r8 = AndroidUtilities.dp(43.0f);
    L18:
        r5.setPadding(r6, 0, r8, 0);
        BackupImageView r52 = new BackupImageView(this.context);
        r52.getImageReceiver().setCurrentAccount(r17);
        AvatarDrawable r62 = new AvatarDrawable();
        r62.setInfo(r3);
        r52.setRoundRadius(AndroidUtilities.dp(34.0f));
        r52.setForUserOrChat(r3, r62);
        float r32 = 1.0f;
        if (r18 == false) goto L21;
        float r82 = 0.84f;
    L22:
        r52.setScaleX(r82);
        if (r18 == false) goto L25;
        r32 = 0.84f;
    L25:
        r52.setScaleY(r32);
        int r63 = 3;
        if (LocaleController.isRTL == false) goto L28;
        int r33 = 5;
    L29:
        r4.addView(r52, LayoutHelper.createFrame(34, 34.0f, r33 | 16, -5.0f, 0.0f, -5.0f, 0.0f));
        if (r18 == false) goto L35;
        View r34 = new View(this.context);
        r34.setBackground(Theme.createOutlineCircleDrawable(AndroidUtilities.dp(34.0f), Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider), AndroidUtilities.dp(2.0f)));
        if (LocaleController.isRTL == false) goto L34;
        r63 = 5;
    L34:
        r4.addView(r34, LayoutHelper.createFrame(36.0f, 36.0f, r63 | 16, -6.0f, 0.0f, -5.0f, 0.0f));
    L35:
        Integer r35 = this.textColor;
        if (r35 == null) goto L38;
        int r36 = r35.intValue();
    L39:
        Integer r53 = this.iconColor;
        if (r53 == null) goto L42;
        int r22 = r53.intValue();
    L43:
        r4.setColors(r36, r22);
        Integer r23 = this.selectorColor;
        if (r23 == null) goto L46;
        int r12 = r23.intValue();
    L47:
        r4.setSelectorColor(r12);
        r4.setOnClickListener(new ItemOptions$$ExternalSyntheticLambda6(this, r19));
        int r13 = this.minWidthDp;
        if (r13 <= 0) goto L51;
        r4.setMinimumWidth(AndroidUtilities.dp(r13));
        addView(r4, LayoutHelper.createLinear(this.minWidthDp, -2));
        return this;
    L51:
        addView(r4, LayoutHelper.createLinear(-1, -2));
        return this;
    L46:
        r12 = Theme.multAlpha(Theme.getColor(r1, this.resourcesProvider), 0.12f);
        goto L47
    L42:
        r22 = Theme.getColor(r2, this.resourcesProvider);
        goto L43
    L38:
        r36 = Theme.getColor(r1, this.resourcesProvider);
        goto L39
    L28:
        r33 = 3;
        goto L29
    L21:
        r82 = 1.0f;
        goto L22
    L15:
        if (r4.checkView != null) goto L13;
        r8 = 0;
        goto L18
    L8:
        if (r4.checkView != null) goto L9;
        r6 = 0;
        goto L12
    }

    public ItemOptions addBot(TLRPC.TL_attachMenuBot r10, Runnable r11, Runnable r12) {
        if (this.context != null) goto L5;
        return this;
    L5:
        int r0 = Theme.key_actionBarDefaultSubmenuItemIcon;
        int r1 = Theme.key_actionBarDefaultSubmenuItem;
        ActionBarMenuSubItem r2 = new ActionBarMenuSubItem(this.context, false, false, this.resourcesProvider);
        r2.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        boolean r3 = r10.side_menu_disclaimer_needed;
        CharSequence r4 = r10.short_name;
        if (r3 == false) goto L8;
        r4 = TextCell.applyNewSpan(r4);
    L8:
        CharSequence r32 = r4;
        TLRPC.TL_attachMenuBotIcon r42 = MediaDataController.getSideAttachMenuBotIcon(r10);
        if (r10.bot_id != 1985737506) goto L11;
        r2.setTextAndIcon(r32, R.drawable.menu_wallet);
    L21:
        Integer r102 = this.textColor;
        if (r102 == null) goto L24;
        int r103 = r102.intValue();
    L25:
        Integer r33 = this.iconColor;
        if (r33 == null) goto L28;
        int r34 = r33.intValue();
    L29:
        r2.setColors(r103, r34);
        Integer r104 = this.iconColor;
        if (r104 == null) goto L32;
        int r105 = r104.intValue();
    L33:
        r2.setIconColorImage(r105);
        Integer r106 = this.selectorColor;
        if (r106 == null) goto L36;
        int r107 = r106.intValue();
    L37:
        r2.setSelectorColor(r107);
        r2.setOnClickListener(new ItemOptions$$ExternalSyntheticLambda9(this, r11));
        r2.setOnLongClickListener(new ItemOptions$$ExternalSyntheticLambda10(this, r12));
        int r108 = this.minWidthDp;
        if (r108 <= 0) goto L41;
        r2.setMinimumWidth(AndroidUtilities.dp(r108));
        addView(r2, LayoutHelper.createLinear(this.minWidthDp, -2));
        return this;
    L41:
        addView(r2, LayoutHelper.createLinear(-1, -2));
        return this;
    L36:
        r107 = Theme.multAlpha(Theme.getColor(r1, this.resourcesProvider), 0.12f);
        goto L37
    L32:
        r105 = Theme.getColor(r0, this.resourcesProvider);
        goto L33
    L28:
        r34 = Theme.getColor(r0, this.resourcesProvider);
        goto L29
    L24:
        r103 = Theme.getColor(r1, this.resourcesProvider);
        goto L25
    L11:
        if (r42 == null) goto L20;
        SvgHelper.SvgDrawable r6 = DocumentObject.getSvgThumb(r42.icon, Theme.key_emptyListPlaceholder, 1.0f);
        if (r6 == null) goto L19;
        Integer r7 = this.iconColor;
        if (r7 == null) goto L17;
        int r72 = r7.intValue();
    L18:
        r6.setColorFilter(new PorterDuffColorFilter(r72, PorterDuff.Mode.SRC_IN));
        goto L19
    L17:
        r72 = Theme.getColor(r0, this.resourcesProvider);
    L19:
        r2.setTextAndIcon(r32, ImageLocation.getForDocument(r42.icon), "24_24", r6, r10);
        r2.setImageSize(24, 24);
        goto L21
    L20:
        r2.setTextAndIcon(r32, R.drawable.msg_bot);
        goto L21
    }

    public ItemOptions addChat(TLObject r17, boolean r18, Runnable r19) {
        if (this.context != null) goto L5;
        return this;
    L5:
        int r2 = Theme.key_actionBarDefaultSubmenuItem;
        int r3 = Theme.key_actionBarDefaultSubmenuItemIcon;
        ActionBarMenuSubItem r4 = new ActionBarMenuSubItem(this.context, false, false, this.resourcesProvider);
        r4.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        if ((r17 instanceof TLRPC.Chat) == false) goto L13;
        TLRPC.Chat r5 = (TLRPC.Chat) r17;
        r4.setText(r5.title);
        if (ChatObject.isChannelAndNotMegaGroup(r5) == false) goto L10;
        String r52 = LocaleController.getString(R.string.DiscussChannel);
    L11:
        r4.setSubtext(r52);
    L20:
        r4.setClipToPadding(false);
        AnimatedEmojiSpan.TextViewEmojis r53 = r4.textView;
        if (r4.checkViewLeft == true) goto L23;
    L24:
        int r6 = AndroidUtilities.dp(43.0f);
    L27:
        if (r4.checkViewLeft == false) goto L30;
    L28:
        int r8 = AndroidUtilities.dp(43.0f);
    L33:
        r53.setPadding(r6, 0, r8, 0);
        r4.subtextView.setPadding(r4.textView.getPaddingLeft(), 0, r4.textView.getPaddingRight(), 0);
        BackupImageView r54 = new BackupImageView(this.context);
        AvatarDrawable r62 = new AvatarDrawable();
        r62.setInfo(r17);
        r54.setRoundRadius(AndroidUtilities.dp(34.0f));
        r54.setForUserOrChat(r17, r62);
        float r1 = 1.0f;
        if (r18 == false) goto L36;
        float r82 = 0.84f;
    L37:
        r54.setScaleX(r82);
        if (r18 == false) goto L40;
        r1 = 0.84f;
    L40:
        r54.setScaleY(r1);
        int r63 = 3;
        if (LocaleController.isRTL == false) goto L43;
        int r12 = 5;
    L44:
        r4.addView(r54, LayoutHelper.createFrame(34, 34.0f, r12 | 16, -5.0f, 0.0f, -5.0f, 0.0f));
        if (r18 == false) goto L50;
        View r13 = new View(this.context);
        r13.setBackground(Theme.createOutlineCircleDrawable(AndroidUtilities.dp(34.0f), Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider), AndroidUtilities.dp(2.0f)));
        if (LocaleController.isRTL == false) goto L49;
        r63 = 5;
    L49:
        r4.addView(r13, LayoutHelper.createFrame(36.0f, 36.0f, r63 | 16, -6.0f, 0.0f, -5.0f, 0.0f));
    L50:
        Integer r14 = this.textColor;
        if (r14 == null) goto L53;
        int r15 = r14.intValue();
    L54:
        Integer r55 = this.iconColor;
        if (r55 == null) goto L57;
        int r32 = r55.intValue();
    L58:
        r4.setColors(r15, r32);
        Integer r16 = this.selectorColor;
        if (r16 == null) goto L61;
        int r110 = r16.intValue();
    L62:
        r4.setSelectorColor(r110);
        r4.setOnClickListener(new ItemOptions$$ExternalSyntheticLambda21(this, r19));
        int r111 = this.minWidthDp;
        if (r111 <= 0) goto L66;
        r4.setMinimumWidth(AndroidUtilities.dp(r111));
        addView(r4, LayoutHelper.createLinear(this.minWidthDp, -2));
        return this;
    L66:
        addView(r4, LayoutHelper.createLinear(-1, -2));
        return this;
    L61:
        r110 = Theme.multAlpha(Theme.getColor(r2, this.resourcesProvider), 0.12f);
        goto L62
    L57:
        r32 = Theme.getColor(r3, this.resourcesProvider);
        goto L58
    L53:
        r15 = Theme.getColor(r2, this.resourcesProvider);
        goto L54
    L43:
        r12 = 3;
        goto L44
    L36:
        r82 = 1.0f;
        goto L37
    L30:
        if (r4.checkView != null) goto L28;
        r8 = 0;
        goto L33
    L23:
        if (r4.checkView != null) goto L24;
        r6 = 0;
        goto L27
    L10:
        r52 = LocaleController.getString(R.string.AccDescrGroup).toLowerCase();
        goto L11
    L13:
        if ((r17 instanceof TLRPC.User) == false) goto L20;
        TLRPC.User r56 = (TLRPC.User) r17;
        r4.setText(UserObject.getUserName(r56));
        if (r56.id != UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId()) goto L18;
        r4.setSubtext(LocaleController.getString(R.string.VoipGroupPersonalAccount));
        goto L20
    L18:
        if (UserObject.isBot(r56) == false) goto L20;
        r4.setSubtext(LocaleController.getString(R.string.Bot));
        goto L20
    }

    public ActionBarMenuSubItem addChecked() {
        int r0 = Theme.key_actionBarDefaultSubmenuItem;
        int r1 = Theme.key_actionBarDefaultSubmenuItemIcon;
        ActionBarMenuSubItem r2 = new ActionBarMenuSubItem(this.context, true, false, false, this.resourcesProvider);
        r2.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        Integer r3 = this.textColor;
        if (r3 == null) goto L5;
        int r32 = r3.intValue();
    L6:
        Integer r4 = this.iconColor;
        if (r4 == null) goto L9;
        int r12 = r4.intValue();
    L10:
        r2.setColors(r32, r12);
        Integer r13 = this.selectorColor;
        if (r13 == null) goto L13;
        int r02 = r13.intValue();
    L14:
        r2.setSelectorColor(r02);
        int r03 = this.minWidthDp;
        if (r03 <= 0) goto L18;
        r2.setMinimumWidth(AndroidUtilities.dp(r03));
        addView(r2, LayoutHelper.createLinear(this.minWidthDp, -2));
        return r2;
    L18:
        addView(r2, LayoutHelper.createLinear(-1, -2));
        return r2;
    L13:
        r02 = Theme.multAlpha(Theme.getColor(r0, this.resourcesProvider), 0.12f);
        goto L14
    L9:
        r12 = Theme.getColor(r1, this.resourcesProvider);
        goto L10
    L5:
        r32 = Theme.getColor(r0, this.resourcesProvider);
        goto L6
    }

    public ItemOptions addChecked(boolean r9, int r10, Drawable r11, CharSequence r12, Runnable r13, Runnable r14) {
        if (this.context != null) goto L5;
        return this;
    L5:
        int r0 = Theme.key_actionBarDefaultSubmenuItem;
        int r1 = Theme.key_actionBarDefaultSubmenuItemIcon;
        Context r3 = this.context;
        if (r10 != 0) goto L10;
        if (r11 != null) goto L10;
        int r4 = 1;
    L11:
        ActionBarMenuSubItem r2 = new ActionBarMenuSubItem(r3, r4, false, false, this.resourcesProvider);
        r2.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        if (r11 == null) goto L14;
        r2.setTextAndIcon(r12, 0, r11);
    L17:
        r2.setChecked(r9);
        Integer r92 = this.textColor;
        if (r92 == null) goto L20;
        int r93 = r92.intValue();
    L21:
        Integer r102 = this.iconColor;
        if (r102 == null) goto L24;
        int r103 = r102.intValue();
    L25:
        r2.setColors(r93, r103);
        Integer r94 = this.selectorColor;
        if (r94 == null) goto L28;
        int r95 = r94.intValue();
    L29:
        r2.setSelectorColor(r95);
        r2.setOnClickListener(new ItemOptions$$ExternalSyntheticLambda7(this, r13));
        if (r14 == null) goto L32;
        r2.setOnLongClickListener(new ItemOptions$$ExternalSyntheticLambda8(this, r14));
    L32:
        int r96 = this.minWidthDp;
        if (r96 <= 0) goto L36;
        r2.setMinimumWidth(AndroidUtilities.dp(r96));
        addView(r2, LayoutHelper.createLinear(this.minWidthDp, -2));
        return this;
    L36:
        addView(r2, LayoutHelper.createLinear(-1, -2));
        return this;
    L28:
        r95 = Theme.multAlpha(Theme.getColor(r0, this.resourcesProvider), 0.12f);
        goto L29
    L24:
        r103 = Theme.getColor(r1, this.resourcesProvider);
        goto L25
    L20:
        r93 = Theme.getColor(r0, this.resourcesProvider);
        goto L21
    L14:
        if (r10 == 0) goto L16;
        r2.setTextAndIcon(r12, r10);
        goto L17
    L16:
        r2.setText(r12);
    L10:
        r4 = 2;
        goto L11
    }

    public ItemOptions addChecked(boolean r7, int r8, CharSequence r9, Runnable r10) {
        return addChecked(r7, r8, r9, r10, null);
    }

    public ItemOptions addChecked(boolean r8, int r9, CharSequence r10, Runnable r11, Runnable r12) {
        return addChecked(r8, r9, null, r10, r11, r12);
    }

    public ItemOptions addChecked(boolean r8, Drawable r9, CharSequence r10, Runnable r11) {
        return addChecked(r8, 0, r9, r10, r11, null);
    }

    public ItemOptions addChecked(boolean r2, CharSequence r3, Runnable r4) {
        return addChecked(r2, r3, r4, null);
    }

    public ItemOptions addChecked(boolean r7, CharSequence r8, Runnable r9, Runnable r10) {
        return addChecked(r7, 0, r8, r9, r10);
    }

    public ItemOptions addGap() {
        if ((getLastView() instanceof ActionBarPopupWindow.GapView) == false) goto L5;
        return this;
    L5:
        ActionBarPopupWindow.GapView r0 = new ActionBarPopupWindow.GapView(this.context, this.resourcesProvider);
        r0.setTag(R.id.fit_width_tag, 1);
        Integer r1 = this.gapBackgroundColor;
        if (r1 == null) goto L8;
        r0.setColor(r1.intValue());
    L8:
        addView(r0, LayoutHelper.createLinear(-1, 8));
        return this;
    }

    public ItemOptions addGapIf(boolean r1) {
        if (r1 == true) goto L5;
        return this;
    L5:
        return addGap();
    }

    public ItemOptions addIf(boolean r8, int r9, Drawable r10, CharSequence r11, Runnable r12) {
        if (r8 == true) goto L5;
        return this;
    L5:
        return add(r9, r10, r11, Theme.key_actionBarDefaultSubmenuItemIcon, Theme.key_actionBarDefaultSubmenuItem, r12);
    }

    public ItemOptions addIf(boolean r7, int r8, CharSequence r9, Runnable r10) {
        if (r7 == true) goto L5;
        return this;
    L5:
        return add(r8, r9, Theme.key_actionBarDefaultSubmenuItemIcon, Theme.key_actionBarDefaultSubmenuItem, r10);
    }

    public ItemOptions addIf(boolean r1, int r2, CharSequence r3, boolean r4, Runnable r5) {
        if (r1 == true) goto L5;
        return this;
    L5:
        return add(r2, r3, r4, r5);
    }

    public ItemOptions addProfile(TLObject r13, CharSequence r14, Runnable r15) {
        FrameLayout r0 = new FrameLayout(this.context);
        r0.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, this.resourcesProvider), 0, 12));
        BackupImageView r1 = new BackupImageView(this.context);
        r1.setRoundRadius(AndroidUtilities.dp(17.0f));
        AvatarDrawable r2 = new AvatarDrawable();
        r2.setInfo(r13);
        r1.setForUserOrChat(r13, r2);
        r0.addView(r1, LayoutHelper.createFrame(34, 34.0f, 19, 13.0f, 0.0f, 0.0f, 0.0f));
        TextView r12 = new TextView(this.context);
        r12.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourcesProvider));
        r12.setTextSize(1, 16.0f);
        r12.setEllipsize(TextUtils.TruncateAt.END);
        r12.setSingleLine(true);
        if ((r13 instanceof TLRPC.User) == false) goto L6;
        r12.setText(UserObject.getUserName((TLRPC.User) r13));
    L8:
        r0.addView(r12, LayoutHelper.createFrame(-2, -2.0f, 55, 59.0f, 6.0f, 16.0f, 0.0f));
        TextView r132 = new TextView(this.context);
        r132.setTextColor(Theme.getColor(Theme.key_dialogTextGray2, this.resourcesProvider));
        r132.setTextSize(1, 13.0f);
        r132.setText(AndroidUtilities.replaceArrows(r14, false, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(0.66f)));
        r0.addView(r132, LayoutHelper.createFrame(-2, -2.0f, 55, 59.0f, 27.0f, 16.0f, 0.0f));
        r0.setOnClickListener(new ItemOptions$$ExternalSyntheticLambda14(this, r15));
        addView(r0, LayoutHelper.createLinear(-1, 52));
        return this;
    L6:
        if ((r13 instanceof TLRPC.Chat) == false) goto L8;
        r12.setText(((TLRPC.Chat) r13).title);
        goto L8
    }

    public ItemOptions addProfileCustom(TLObject r11, CharSequence r12, Runnable r13) {
        FrameLayout r0 = new FrameLayout(this.context);
        r0.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, this.resourcesProvider), 0, 12));
        BackupImageView r1 = new BackupImageView(this.context);
        r1.setRoundRadius(AndroidUtilities.dp(17.0f));
        AvatarDrawable r2 = new AvatarDrawable();
        r2.setInfo(r11);
        r1.setForUserOrChat(r11, r2);
        r0.addView(r1, LayoutHelper.createFrame(34, 34.0f, 51, 13.0f, 11.0f, 0.0f, 11.0f));
        TextView r112 = new TextView(this.context);
        r112.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourcesProvider));
        r112.setTextSize(1, 14.0f);
        r112.setText(r12);
        r112.setMaxWidth(AndroidUtilities.dp(150.0f));
        r112.setLineSpacing(AndroidUtilities.dp(3.0f), 1.0f);
        r0.addView(r112, LayoutHelper.createFrame(-2, -2.0f, 55, 59.0f, 8.0f, 16.0f, 0.0f));
        r0.setOnClickListener(new ItemOptions$$ExternalSyntheticLambda20(this, r13));
        addView(r0, LayoutHelper.createLinear(-1, -2));
        return this;
    }

    public ItemOptions addSpaceGap() {
        return addSpaceGap(true);
    }

    public ItemOptions addSpaceGap(boolean r13) {
        if ((this.layout instanceof LinearLayout) == true) goto L9;
        LinearLayout r0 = new LinearLayout(this.context);
        this.layout = r0;
        r0.setOrientation(r13 ? 1 : 0);
        ViewGroup r02 = this.layout;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r1 = this.lastLayout;
        int r2 = this.maxHeight;
        if (r2 <= 0) goto L7;
        float r22 = r2 / AndroidUtilities.density;
    L8:
        r02.addView(r1, LayoutHelper.createLinear(-1.0f, r22, 48));
        goto L9
    L7:
        r22 = -2.0f;
    L9:
        Context r12 = this.context;
        int r23 = R.drawable.popup_fixed_alert4;
        Theme.ResourcesProvider r3 = this.resourcesProvider;
        if (r13 == true) goto L12;
        int r5 = 4;
    L13:
        ActionBarPopupWindow.ActionBarPopupWindowLayout r03 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(r12, r23, r3, r5);
        this.lastLayout = r03;
        r03.setDispatchKeyEventListener(new ItemOptions$$ExternalSyntheticLambda15(this));
        ViewGroup r04 = this.layout;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r14 = this.lastLayout;
        if (r13 == true) goto L16;
        int r8 = -8;
    L17:
        if (r13 == false) goto L19;
        int r9 = -8;
    L20:
        r04.addView(r14, LayoutHelper.createLinear(-1, -2, 48, r8, r9, 0, 0));
        return this;
    L19:
        r9 = 0;
        goto L20
    L16:
        r8 = 0;
        goto L17
    L12:
        r5 = 0;
        goto L13
    }

    public ItemOptions addText(CharSequence r2, int r3) {
        return addText(r2, r3, -1);
    }

    public ItemOptions addText(CharSequence r2, int r3, int r4) {
        return addText(r2, r3, null, r4);
    }

    public ItemOptions addText(CharSequence r6, int r7, Typeface r8, int r9) {
        AnonymousClass3 r0 = new AnonymousClass3(this, this.context);
        r0.setTextSize(1, r7);
        r0.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourcesProvider));
        r0.setPadding(AndroidUtilities.dp(13.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(8.0f));
        r0.setText(Emoji.replaceEmoji(r6, r0.getPaint().getFontMetricsInt(), false));
        r0.setTag(R.id.fit_width_tag, 1);
        r0.setTypeface(r8);
        NotificationCenter.listenEmojiLoading(r0);
        if (r9 <= 0) goto L6;
        r0.setMaxWidth(r9);
        LinearLayout r82 = new LinearLayout(this.context);
        r82.setTag(R.id.fit_width_tag, 1);
        r82.addView(r0, LayoutHelper.createLinear(-2, -2));
        addView(r82, LayoutHelper.createLinear(-1, -2));
        return this;
    L6:
        addView(r0, LayoutHelper.createLinear(-1, -2));
        return this;
    }

    public ItemOptions addView(View r3) {
        if (r3 != null) goto L4;
        return this;
    L4:
        r3.setTag(R.id.fit_width_tag, 1);
        addView(r3, LayoutHelper.createLinear(-1, -2));
        return this;
    }

    public ItemOptions addView(View r2, LinearLayout.LayoutParams r3) {
        if (r2 != null) goto L4;
        return this;
    L4:
        LinearLayout r0 = this.linearLayout;
        if (r0 == null) goto L8;
        r0.addView(r2, r3);
        return this;
    L8:
        this.lastLayout.addView(r2, r3);
        return this;
    }

    public ItemOptions allowCenter(boolean r1) {
        this.allowCenter = r1;
        return this;
    }

    public ItemOptions allowMoveScrim() {
        this.allowMoveScrim = true;
        return this;
    }

    public ItemOptions allowMoveScrimGravity(int r1) {
        this.allowMoveScrimGravity = r1;
        return this;
    }

    public ItemOptions allowShowingOnTopOfKeyboard() {
        this.allowShowingOnTopOfKeyboard = true;
        return this;
    }

    public ItemOptions animateToSize(int r1, int r2) {
        this.animateToWidth = r1;
        this.animateToHeight = r2;
        return this;
    }

    public void closeSwipeback() {
        dontDismiss();
        this.lastLayout.getSwipeBack().closeForeground();
    }

    public ItemOptions cutTextInFancyHalf() {
        if (this.context != null) goto L5;
    L10:
        return this;
    L5:
        if (this.lastLayout.getItemsCount() <= 0) goto L10;
        View r0 = this.lastLayout.getItemAt(r0.getItemsCount() - 1);
        if ((r0 instanceof ActionBarMenuSubItem) == false) goto L10;
        AnimatedEmojiSpan.TextViewEmojis r02 = ((ActionBarMenuSubItem) r0).getTextView();
        r02.setMaxWidth((HintView2.cutInFancyHalf(r02.getText(), r02.getPaint()) + r02.getPaddingLeft()) + r02.getPaddingRight());
        goto L10
    }

    public void dismiss() {
        if (this.dontDismiss == false) goto L6;
        this.dontDismiss = false;
        return;
    L6:
        ActionBarPopupWindow r0 = this.actionBarPopupWindow;
        if (r0 == null) goto L10;
        r0.dismiss();
        return;
    L10:
        Runnable r1 = this.dismissListener;
        if (r1 == null) goto L14;
        r1.run();
        return;
    }

    public void dispatchCapturedTouchEvent(MotionEvent r6) {
        if (canHandleCapturedTouch() == true) goto L5;
        return;
    L5:
        MotionEvent r0 = MotionEvent.obtain(r6);
        this.layout.getLocationOnScreen(new int[2]);
        r0.offsetLocation(-r2[0], -r2[1]);
        this.layout.dispatchTouchEvent(r0);
        int r2 = r6.getActionMasked();
        if (r2 != 2) goto L8;
        updateHover((int) r6.getRawX(), (int) r6.getRawY());
    L13:
        r0.recycle();
        return;
    L8:
        if (r2 != 1) goto L11;
        releaseHover((int) r6.getRawX(), (int) r6.getRawY());
        goto L13
    L11:
        if (r2 != 3) goto L13;
        cancelHover();
        goto L13
    }

    public void dontDismiss() {
        this.dontDismiss = true;
    }

    public ItemOptions dontFocus() {
        this.dontFocus = true;
        return this;
    }

    public ItemOptions followScrimView() {
        this.followScrim = true;
        if (isShown() == false) goto L5;
        installFollowListeners();
    L5:
        return this;
    }

    public ItemOptions forceBelowScrim(boolean r1) {
        this.forceBelowScrim = r1;
        return this;
    }

    public ItemOptions forceBottom(boolean r1) {
        this.forceBottom = r1;
        return this;
    }

    public ItemOptions forceTop(boolean r1) {
        this.forceTop = r1;
        return this;
    }

    public Context getContext() {
        return this.context;
    }

    public View getItemAt(int r5) {
        ActionBarPopupWindow.ActionBarPopupWindowLayout r0 = this.lastLayout;
        if (r0 != null) goto L8;
        if (this.layout != null) goto L8;
        return null;
    L8:
        if (r0 == this.layout) goto L10;
        int r02 = 0;
    L13:
        if (r02 >= (this.layout.getChildCount() - 1)) goto L25;
        if (r02 != (this.layout.getChildCount() - 1)) goto L17;
        View r2 = this.lastLayout;
    L19:
        if ((r2 instanceof ActionBarPopupWindow.ActionBarPopupWindowLayout) == false) goto L24;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r22 = (ActionBarPopupWindow.ActionBarPopupWindowLayout) r2;
        View r3 = r22.getItemAt(r5);
        if (r3 != null) goto L22;
        r5 = r5 - r22.getItemsCount();
        goto L24
    L22:
        return r3;
    L24:
        r02 = r02 + 1;
        goto L13
    L17:
        r2 = this.layout.getChildAt(r02);
        goto L19
    L25:
        return null;
    L10:
        return r0.getItemAt(r5);
    }

    public int getItemsCount() {
        ActionBarPopupWindow.ActionBarPopupWindowLayout r0 = this.lastLayout;
        int r1 = 0;
        if (r0 != null) goto L8;
        if (this.layout != null) goto L8;
        return 0;
    L8:
        if (r0 == this.layout) goto L10;
        int r02 = 0;
    L13:
        if (r1 >= (this.layout.getChildCount() - 1)) goto L22;
        if (r1 != (this.layout.getChildCount() - 1)) goto L17;
        View r2 = this.lastLayout;
    L19:
        if ((r2 instanceof ActionBarPopupWindow.ActionBarPopupWindowLayout) == false) goto L21;
        r02 = r02 + ((ActionBarPopupWindow.ActionBarPopupWindowLayout) r2).getItemsCount();
    L21:
        r1 = r1 + 1;
        goto L13
    L17:
        r2 = this.layout.getChildAt(r1);
        goto L19
    L22:
        return r02;
    L10:
        return r0.getItemsCount();
    }

    public ActionBarMenuSubItem getLast() {
        LinearLayout r0 = this.linearLayout;
        if (r0 != null) goto L5;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r02 = this.lastLayout;
        if (r02 != null) goto L15;
        return null;
    L15:
        if (r02.getItemsCount() > 0) goto L17;
        return null;
    L17:
        View r2 = this.lastLayout.getItemAt(r2.getItemsCount() - 1);
        if ((r2 instanceof ActionBarMenuSubItem) == true) goto L21;
        return null;
    L21:
        return (ActionBarMenuSubItem) r2;
    L5:
        if (r0.getChildCount() > 0) goto L7;
        return null;
    L7:
        View r22 = this.linearLayout.getChildAt(r2.getChildCount() - 1);
        if ((r22 instanceof ActionBarMenuSubItem) == true) goto L11;
        return null;
    L11:
        return (ActionBarMenuSubItem) r22;
    }

    public View getLastView() {
        LinearLayout r0 = this.linearLayout;
        if (r0 != null) goto L5;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r02 = this.lastLayout;
        if (r02 != null) goto L12;
        return null;
    L12:
        if (r02.getItemsCount() > 0) goto L15;
        return null;
    L15:
        return this.lastLayout.getItemAt(r2.getItemsCount() - 1);
    L5:
        if (r0.getChildCount() > 0) goto L8;
        return null;
    L8:
        return this.linearLayout.getChildAt(r2.getChildCount() - 1);
    }

    public ViewGroup getLayout() {
        return this.layout;
    }

    public LinearLayout getLinearLayout() {
        return this.linearLayout;
    }

    public ItemOptions hideScrimUnder() {
        this.hideScrimUnder = true;
        return this;
    }

    public ItemOptions ignoreX() {
        this.ignoreX = true;
        return this;
    }

    @Keep
    public boolean isDismissOnMoveOutside() {
        return this.dismissOnMoveOutside;
    }

    public boolean isShown() {
        ActionBarPopupWindow r0 = this.actionBarPopupWindow;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isShowing() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public ItemOptions makeMultiline(boolean r3) {
        if (this.context != null) goto L5;
    L10:
        return this;
    L5:
        if (this.lastLayout.getItemsCount() <= 0) goto L10;
        View r0 = this.lastLayout.getItemAt(r0.getItemsCount() - 1);
        if ((r0 instanceof ActionBarMenuSubItem) == false) goto L10;
        ((ActionBarMenuSubItem) r0).setMultiline(r3);
        goto L10
    }

    public ItemOptions makeSwipeback() {
        return makeSwipeback(false);
    }

    public ItemOptions makeSwipeback(boolean r6) {
        ItemOptions r0 = new ItemOptions(this.lastLayout, this.resourcesProvider);
        if (r6 == false) goto L5;
        AnonymousClass2 r62 = new AnonymousClass2(this, this.context);
        r62.setVerticalScrollBarEnabled(false);
        r62.addView(r0.linearLayout, LayoutHelper.createScroll(-1, -2, 48));
        r0.foregroundIndex = this.lastLayout.addViewToSwipeBack(r62);
    L7:
        if (this.lastLayout.getSwipeBack() == null) goto L9;
        this.lastLayout.getSwipeBack().addOnSwipeBackProgressListener(new ItemOptions$$ExternalSyntheticLambda1(this));
    L9:
        return r0;
    L5:
        r0.foregroundIndex = this.lastLayout.addViewToSwipeBack(r0.linearLayout);
        goto L7
    }

    public ItemOptions needsFocus() {
        this.needsFocus = true;
        return this;
    }

    public ItemOptions offsetByContainer() {
        this.offsetByContainer = true;
        return this;
    }

    public void openSwipeback(ItemOptions r1) {
        dontDismiss();
        this.lastLayout.getSwipeBack().openForeground(r1.foregroundIndex);
    }

    public ItemOptions putCheck() {
        if (this.context != null) goto L5;
    L11:
        return this;
    L5:
        if (this.lastLayout.getItemsCount() <= 0) goto L11;
        View r0 = this.lastLayout.getItemAt(r0.getItemsCount() - 1);
        if ((r0 instanceof ActionBarMenuSubItem) == false) goto L11;
        ActionBarMenuSubItem r02 = (ActionBarMenuSubItem) r0;
        r02.setRightIcon(R.drawable.msg_text_check);
        r02.getRightIcon().setColorFilter(-1, PorterDuff.Mode.MULTIPLY);
        r02.getRightIcon().setScaleX(0.85f);
        r02.getRightIcon().setScaleY(0.85f);
        goto L11
    }

    public ItemOptions putPremiumLock(Runnable r4) {
        if (r4 != null) goto L4;
    L12:
        return this;
    L4:
        if (this.context == null) goto L12;
        if (this.lastLayout.getItemsCount() <= 0) goto L12;
        View r0 = this.lastLayout.getItemAt(r0.getItemsCount() - 1);
        if ((r0 instanceof ActionBarMenuSubItem) == false) goto L12;
        ActionBarMenuSubItem r02 = (ActionBarMenuSubItem) r0;
        r02.setRightIcon(R.drawable.msg_mini_lock3);
        r02.getRightIcon().setAlpha(0.4f);
        r02.setOnClickListener(new ItemOptions$$ExternalSyntheticLambda17(this, r4));
        goto L12
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void reposition() {
        ActionBarPopupWindow r0 = this.actionBarPopupWindow;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isShowing() == false) goto L73;
        View r02 = this.scrimView;
        if (r02 == null) goto L70;
        ViewGroup r1 = this.pointContainer;
        if (r1 != null) goto L12;
        return;
    L12:
        if (this.layout != null) goto L14;
        return;
    L14:
        if (this.lastLayout == null) goto L74;
        getPointOnScreen(r02, r1, this.point);
        float[] r03 = this.point;
        float r3 = r03[1];
        float r04 = r03[0];
        if (this.offsetByContainer == false) goto L19;
        r1.getLocationOnScreen(new int[2]);
        r04 = r04 + r5[0];
        r3 = r3 + r5[1];
    L19:
        RectF r5 = new RectF();
        View r6 = this.scrimView;
        if ((r6 instanceof ScrimView) == false) goto L22;
        ((ScrimView) r6).getBounds(r5);
    L28:
        float r05 = r04 + r5.left;
        float r32 = r3 + r5.top;
        if (this.ignoreX == false) goto L31;
        this.point[0] = 0.0f;
        r05 = 0.0f;
    L31:
        this.layout.measure(View.MeasureSpec.makeMeasureSpec(r1.getMeasuredWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r1.getMeasuredHeight(), Integer.MIN_VALUE));
        RectF r4 = new RectF();
        Rect r62 = this.lastLayout.getPadding();
        r4.set(r62.left, r62.top, this.layout.getMeasuredWidth() - r62.right, this.layout.getMeasuredHeight() - r62.bottom);
        if (AndroidUtilities.isTablet() == false) goto L34;
        r32 = r32 + r1.getPaddingTop();
        r05 = r05 - r1.getPaddingLeft();
    L34:
        int r63 = this.gravity;
        if (r63 != 3) goto L39;
        float r42 = r1.getX() + r05;
    L37:
        int r06 = (int) r42;
    L49:
        if (this.onTopOfScrim == false) goto L51;
        float r43 = 0.0f;
    L53:
        if (this.forceBottom == false) goto L56;
        int r12 = (int) ((Math.min(r32 + r43, AndroidUtilities.displaySize.y) - this.layout.getMeasuredHeight()) + r1.getY());
    L67:
        float r07 = r06 + this.translateX;
        this.offsetX = r07;
        float r13 = r12 + this.translateY;
        this.offsetY = r13;
        this.actionBarPopupWindow.update((int) r07, (int) r13, -1, -1);
        return;
    L56:
        if (this.forceTop == false) goto L58;
    L59:
        r32 = (r32 - r43) - this.layout.getMeasuredHeight();
        if (this.allowCenter == true) goto L62;
    L66:
        r12 = (int) ((r32 + r43) + r1.getY());
        goto L67
    L62:
        if ((Math.max(0.0f, r32 + r43) + this.layout.getMeasuredHeight()) <= (this.point[1] + r5.top)) goto L66;
        if (r5.height() != this.scrimView.getHeight()) goto L66;
        r32 = (((r1.getHeight() - this.layout.getMeasuredHeight()) / 2.0f) - r43) - r1.getY();
        goto L66
    L58:
        if ((((r32 + r43) + this.layout.getMeasuredHeight()) + AndroidUtilities.dp(16.0f)) <= (AndroidUtilities.displaySize.y - AndroidUtilities.navigationBarHeight)) goto L66;
    L51:
        r43 = r5.height();
        goto L53
    L39:
        if (r63 != 5) goto L42;
        float r64 = (r1.getX() + r05) + r5.width();
        float r08 = r4.right;
    L41:
        r06 = (int) (r64 - r08);
        goto L49
    L42:
        if (r63 != 1) goto L45;
        r42 = ((r1.getX() + r05) + (r5.width() / 2.0f)) - (this.layout.getMeasuredWidth() / 2.0f);
        goto L37
    L45:
        if ((r4.width() + r05) <= r1.getWidth()) goto L47;
        r64 = (r1.getX() + r05) + r5.width();
        r08 = r4.right;
        goto L41
    L47:
        r64 = r1.getX() + r05;
        r08 = r4.left;
        goto L41
    L22:
        int r7 = this.animateToWidth;
        if (r7 == 0) goto L27;
        int r9 = this.animateToHeight;
        if (r9 == 0) goto L27;
        r5.set(0.0f, 0.0f, r7, r9);
    L27:
        r5.set(0.0f, 0.0f, r6.getMeasuredWidth(), this.scrimView.getMeasuredHeight());
        goto L28
    L74:
        return;
    L70:
        return;
    }

    public ItemOptions setBackgroundColor(int r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.layout.getChildCount()) goto L13;
        if (r0 != (this.layout.getChildCount() - 1)) goto L8;
        View r1 = this.lastLayout;
    L10:
        if ((r1 instanceof ActionBarPopupWindow.ActionBarPopupWindowLayout) == false) goto L12;
        r1.setBackgroundColor(r4);
    L12:
        r0 = r0 + 1;
        goto L4
    L8:
        r1 = this.layout.getChildAt(r0);
        goto L10
    L13:
        return this;
    }

    public ItemOptions setBlur(boolean r1) {
        this.blur = r1;
        this.blurForMenu = r1;
        return this;
    }

    public ItemOptions setBlur(boolean r1, boolean r2) {
        this.blur = r1;
        this.blurForMenu = r2;
        return this;
    }

    public ItemOptions setBlurBackground(BlurringShader.BlurManager r10, float r11, float r12) {
        Drawable r0 = this.context.getResources().getDrawable(R.drawable.popup_fixed_alert4).mutate();
        ViewGroup r1 = this.layout;
        if ((r1 instanceof ActionBarPopupWindow.ActionBarPopupWindowLayout) == false) goto L6;
        r1.setBackground(new BlurringShader.StoryBlurDrawer(r10, r1, 5).makeDrawable((this.offsetX + r11) + this.layout.getX(), (this.offsetY + r12) + this.layout.getY(), r0, AndroidUtilities.dp(12.0f)));
        return this;
    L6:
        int r13 = 0;
    L8:
        if (r13 >= this.layout.getChildCount()) goto L13;
        View r2 = this.layout.getChildAt(r13);
        if ((r2 instanceof ActionBarPopupWindow.ActionBarPopupWindowLayout) == false) goto L12;
        r2.setBackground(new BlurringShader.StoryBlurDrawer(r10, r2, 5).makeDrawable(((this.offsetX + r11) + this.layout.getX()) + r2.getX(), ((this.offsetY + r12) + this.layout.getY()) + r2.getY(), r0, AndroidUtilities.dp(12.0f)));
    L12:
        r13 = r13 + 1;
        goto L8
    L13:
        return this;
    }

    public ItemOptions setBlurBackground(BlurredBackgroundDrawableViewFactory r3, BlurredBackgroundProvider r4, boolean r5) {
        ViewGroup r0 = this.layout;
        if ((r0 instanceof ActionBarPopupWindow.ActionBarPopupWindowLayout) == false) goto L5;
        r0.setBackground(r3.create(r0, r5).setColorProvider(r4).setPadding(AndroidUtilities.dp(8.0f)).setHasPadding(true).setRadius(AndroidUtilities.dp(12.0f)));
    L5:
        return this;
    }

    public ItemOptions setBlurBackgroundForSwipeback(BlurredBackgroundDrawableViewFactory r2, BlurredBackgroundProvider r3, boolean r4) {
        LinearLayout r0 = this.linearLayout;
        if (r0 == null) goto L5;
        r0.setBackground(r2.create(r0, r4).setColorProvider(r3));
    L5:
        return this;
    }

    public ItemOptions setColors(int r7, int r8) {
        this.textColor = Integer.valueOf(r7);
        this.iconColor = Integer.valueOf(r8);
        int r1 = 0;
    L4:
        if (r1 >= this.layout.getChildCount()) goto L22;
        if (r1 != (this.layout.getChildCount() - 1)) goto L8;
        View r2 = this.lastLayout;
    L10:
        if ((r2 instanceof ActionBarPopupWindow.ActionBarPopupWindowLayout) == false) goto L19;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r22 = (ActionBarPopupWindow.ActionBarPopupWindowLayout) r2;
        int r3 = 0;
    L13:
        if (r3 >= r22.getItemsCount()) goto L21;
        View r4 = r22.getItemAt(r3);
        if ((r4 instanceof ActionBarMenuSubItem) == false) goto L17;
        ((ActionBarMenuSubItem) r4).setColors(r7, r8);
    L17:
        r3 = r3 + 1;
    L21:
        r1 = r1 + 1;
        goto L4
    L19:
        if ((r2 instanceof ActionBarMenuSubItem) == false) goto L21;
        ((ActionBarMenuSubItem) r2).setColors(r7, r8);
        goto L21
    L8:
        r2 = this.layout.getChildAt(r1);
        goto L10
    L22:
        return this;
    }

    public ItemOptions setDimAlpha(int r1) {
        this.dimAlpha = r1;
        return this;
    }

    public ItemOptions setDismissOnMoveOutside(boolean r1) {
        this.dismissOnMoveOutside = r1;
        return this;
    }

    public ItemOptions setDismissWithButtons(boolean r1) {
        this.dismissWithButtons = r1;
        return this;
    }

    public ItemOptions setDrawScrim(boolean r1) {
        this.drawScrim = r1;
        return this;
    }

    public ItemOptions setFixedWidth(int r1) {
        this.fixedWidthDp = r1;
        return this;
    }

    public ItemOptions setGapBackgroundColor(int r7) {
        this.gapBackgroundColor = Integer.valueOf(r7);
        if (this.layout == null) goto L24;
        int r1 = 0;
    L6:
        if (r1 >= this.layout.getChildCount()) goto L24;
        if (r1 != (this.layout.getChildCount() - 1)) goto L10;
        View r2 = this.lastLayout;
    L12:
        if ((r2 instanceof ActionBarPopupWindow.ActionBarPopupWindowLayout) == false) goto L21;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r22 = (ActionBarPopupWindow.ActionBarPopupWindowLayout) r2;
        int r3 = 0;
    L15:
        if (r3 >= r22.getItemsCount()) goto L23;
        View r4 = r22.getItemAt(r3);
        if ((r4 instanceof ActionBarPopupWindow.GapView) == false) goto L19;
        ((ActionBarPopupWindow.GapView) r4).setColor(r7);
    L19:
        r3 = r3 + 1;
    L23:
        r1 = r1 + 1;
        goto L6
    L21:
        if ((r2 instanceof ActionBarPopupWindow.GapView) == false) goto L23;
        ((ActionBarPopupWindow.GapView) r2).setColor(r7);
        goto L23
    L10:
        r2 = this.layout.getChildAt(r1);
    L24:
        return this;
    }

    public ItemOptions setGravity(int r3) {
        this.gravity = r3;
        if (r3 == 5) goto L5;
    L11:
        return this;
    L5:
        if (this.swipeback == false) goto L11;
        ViewGroup r32 = this.layout;
        if ((r32 instanceof ActionBarPopupWindow.ActionBarPopupWindowLayout) == false) goto L11;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r33 = (ActionBarPopupWindow.ActionBarPopupWindowLayout) r32;
        r33.swipeBackGravityRight = true;
        r33.swipeBackGravityCenterHorizontal = false;
        if (r33.getSwipeBack() == null) goto L11;
        r33.getSwipeBack().setStickToCenterHorizontal(false);
        goto L11
    }

    public ItemOptions setLongPressSelectionEnabled(boolean r1) {
        this.longPressSelectionEnabled = r1;
        return this;
    }

    public ItemOptions setMaxHeight(int r1) {
        this.maxHeight = r1;
        return this;
    }

    public ItemOptions setMinWidth(int r1) {
        this.minWidthDp = r1;
        return this;
    }

    public ItemOptions setOnDismiss(Runnable r1) {
        this.dismissListener = r1;
        return this;
    }

    public ItemOptions setOnTopOfScrim() {
        this.onTopOfScrim = true;
        return this;
    }

    public ItemOptions setRoundRadius(int r1, int r2) {
        this.scrimViewRoundRadius = r1;
        this.scrimViewPadding = r2;
        return this;
    }

    public ItemOptions setScaleOut(boolean r1) {
        this.scaleOut = r1;
        return this;
    }

    public ItemOptions setScrimViewBackground(Drawable r3) {
        this.scrimViewBackground = r3;
        this.scrimViewBackgroundShadowColor = 0;
        if ((r3 instanceof ShapeDrawable) == true) goto L5;
    L7:
        return this;
    L5:
        if (Build.VERSION.SDK_INT < 29) goto L7;
        this.scrimViewBackgroundShadowColor = ((ShapeDrawable) r3).getPaint().getShadowLayerColor();
        goto L7
    }

    public ItemOptions setScrimViewPoint(float r2, float r3) {
        this.hasScrimViewPoint = true;
        this.scrimViewPointX = r2;
        this.scrimViewPointY = r3;
        return this;
    }

    public ItemOptions setSelectorColor(int r7) {
        this.selectorColor = Integer.valueOf(r7);
        int r1 = 0;
    L4:
        if (r1 >= this.layout.getChildCount()) goto L22;
        if (r1 != (this.layout.getChildCount() - 1)) goto L8;
        View r2 = this.lastLayout;
    L10:
        if ((r2 instanceof ActionBarPopupWindow.ActionBarPopupWindowLayout) == false) goto L19;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r22 = (ActionBarPopupWindow.ActionBarPopupWindowLayout) r2;
        int r3 = 0;
    L13:
        if (r3 >= r22.getItemsCount()) goto L21;
        View r4 = r22.getItemAt(r3);
        if ((r4 instanceof ActionBarMenuSubItem) == false) goto L17;
        ((ActionBarMenuSubItem) r4).setSelectorColor(r7);
    L17:
        r3 = r3 + 1;
    L21:
        r1 = r1 + 1;
        goto L4
    L19:
        if ((r2 instanceof ActionBarMenuSubItem) == false) goto L21;
        ((ActionBarMenuSubItem) r2).setSelectorColor(r7);
        goto L21
    L8:
        r2 = this.layout.getChildAt(r1);
        goto L10
    L22:
        return this;
    }

    public ItemOptions setSwipebackCenterHorizontal(boolean r3) {
        this.overridenSwipebackGravity = true;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r0 = this.lastLayout;
        r0.swipeBackGravityRight = false;
        r0.swipeBackGravityCenterHorizontal = r3;
        if (r0.getSwipeBack() == null) goto L5;
        this.lastLayout.getSwipeBack().setStickToCenterHorizontal(r3);
    L5:
        return this;
    }

    public ItemOptions setSwipebackGravity(boolean r2, boolean r3) {
        this.overridenSwipebackGravity = true;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r0 = this.lastLayout;
        r0.swipeBackGravityRight = r2;
        r0.swipeBackGravityCenterHorizontal = false;
        r0.swipeBackGravityBottom = r3;
        if (r0.getSwipeBack() == null) goto L5;
        this.lastLayout.getSwipeBack().setStickToCenterHorizontal(false);
    L5:
        return this;
    }

    public void setTranslationY(float r3) {
        ActionBarPopupWindow r0 = this.actionBarPopupWindow;
        if (r0 == null) goto L6;
        r0.update((int) this.offsetX, (int) (this.offsetY + r3), -1, -1);
        return;
    }

    public ItemOptions setViewAdditionalOffsets(int r2, int r3, int r4, int r5) {
        this.viewAdditionalOffsets.set(r2, r3, r4, r5);
        return this;
    }

    public void setupSelectors() {
        if (this.layout == null) goto L68;
        int r1 = 0;
    L7:
        if (r1 >= this.layout.getChildCount()) goto L77;
        if (r1 != (this.layout.getChildCount() - 1)) goto L11;
        View r2 = this.lastLayout;
    L13:
        if ((r2 instanceof ActionBarPopupWindow.ActionBarPopupWindowLayout) == false) goto L67;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r22 = (ActionBarPopupWindow.ActionBarPopupWindowLayout) r2;
        if (r22.getItemsCount() <= 0) goto L67;
        View r4 = r22.getItemAt(0);
        View r23 = r22.getItemAt(r22.getItemsCount() - 1);
        if ((r4 instanceof ActionBarMenuSubItem) == false) goto L25;
        ActionBarMenuSubItem r5 = (ActionBarMenuSubItem) r4;
        if (r4 != r23) goto L22;
        boolean r7 = true;
    L23:
        r5.updateSelectorBackground(true, r7, 12);
    L43:
        if ((r23 instanceof ActionBarMenuSubItem) == false) goto L50;
        ActionBarMenuSubItem r52 = (ActionBarMenuSubItem) r23;
        if (r23 != r4) goto L47;
        boolean r24 = true;
    L48:
        r52.updateSelectorBackground(r24, true, 12);
        goto L67
    L47:
        r24 = false;
        goto L48
    L50:
        if ((r23 instanceof MessagePreviewView.ToggleButton) == false) goto L52;
    L62:
        int r3 = Theme.getColor(Theme.key_dialogButtonSelector, this.resourcesProvider);
        if (r4 != r23) goto L65;
        int r42 = 12;
    L66:
        r23.setBackground(Theme.createRadSelectorDrawable(r3, r42, 12));
        goto L67
    L65:
        r42 = 0;
        goto L66
    L52:
        if ((r23 instanceof FrameLayout) == true) goto L62;
        if (r23 == null) goto L67;
        if ((r23.getBackground() instanceof RippleDrawable) == false) goto L67;
        int r32 = Theme.getColor(Theme.key_dialogButtonSelector, this.resourcesProvider);
        if (r4 != r23) goto L60;
        int r43 = 12;
    L61:
        r23.setBackground(Theme.createRadSelectorDrawable(r32, r43, 12));
        goto L67
    L60:
        r43 = 0;
        goto L61
    L22:
        r7 = false;
        goto L23
    L25:
        if ((r4 instanceof MessagePreviewView.ToggleButton) == false) goto L27;
    L37:
        int r53 = Theme.getColor(Theme.key_dialogButtonSelector, this.resourcesProvider);
        if (r4 != r23) goto L40;
        int r72 = 12;
    L41:
        r4.setBackground(Theme.createRadSelectorDrawable(r53, 12, r72));
        goto L43
    L40:
        r72 = 0;
        goto L41
    L27:
        if ((r4 instanceof FrameLayout) == true) goto L37;
        if (r4 == null) goto L43;
        if ((r4.getBackground() instanceof RippleDrawable) == false) goto L43;
        int r54 = Theme.getColor(Theme.key_dialogButtonSelector, this.resourcesProvider);
        if (r4 != r23) goto L35;
        int r73 = 12;
    L36:
        r4.setBackground(Theme.createRadSelectorDrawable(r54, 12, r73));
        goto L43
    L35:
        r73 = 0;
    L67:
        r1 = r1 + 1;
        goto L7
    L11:
        r2 = this.layout.getChildAt(r1);
        goto L13
    L77:
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ItemOptions show() {
        if (this.actionBarPopupWindow == null) goto L5;
    L216:
        return this;
    L5:
        if (this.linearLayout != null) goto L216;
        if ((getLastView() instanceof ActionBarPopupWindow.GapView) == false) goto L11;
        AndroidUtilities.removeFromParent(getLastView());
    L11:
        if (getItemsCount() <= 0) goto L216;
        setupSelectors();
        if (this.fixedWidthDp <= 0) goto L30;
        int r0 = 0;
    L17:
        if (r0 >= (this.layout.getChildCount() - 1)) goto L46;
        if (r0 != (this.layout.getChildCount() - 1)) goto L21;
        View r2 = this.lastLayout;
    L23:
        if ((r2 instanceof ActionBarPopupWindow.ActionBarPopupWindowLayout) == false) goto L28;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r22 = (ActionBarPopupWindow.ActionBarPopupWindowLayout) r2;
        int r3 = 0;
    L26:
        if (r3 >= r22.getItemsCount()) goto L28;
        r22.getItemAt(r3).getLayoutParams().width = AndroidUtilities.dp(this.fixedWidthDp);
        r3 = r3 + 1;
    L28:
        r0 = r0 + 1;
        goto L17
    L21:
        r2 = this.layout.getChildAt(r0);
    L46:
        if (this.blur == true) goto L50;
        if (this.blurForMenu == true) goto L50;
    L52:
        ViewGroup r02 = this.container;
        if (r02 != null) goto L55;
        r02 = this.fragment.getParentLayout().getOverlayContainerView();
    L55:
        ViewGroup r5 = r02;
        this.pointContainer = r5;
        if (this.context == null) goto L216;
        if (r5 == null) goto L216;
        float r03 = AndroidUtilities.displaySize.y / 2.0f;
        View r23 = this.scrimView;
        if (r23 == null) goto L64;
        getPointOnScreen(r23, r5, this.point);
        float[] r04 = this.point;
        float r24 = r04[1];
        float r05 = r04[0];
        if (this.offsetByContainer == false) goto L65;
        r5.getLocationOnScreen(new int[2]);
        r05 = r05 + r3[0];
        r24 = r24 + r3[1];
    L65:
        RectF r11 = new RectF();
        View r32 = this.scrimView;
        if ((r32 instanceof ScrimView) == false) goto L68;
        ((ScrimView) r32).getBounds(r11);
    L75:
        if (this.hasScrimViewPoint == false) goto L77;
        float r33 = this.scrimViewPointX;
        float r4 = this.scrimViewPointY;
        r11.set(r33, r4, r33, r4);
    L77:
        float r06 = r05 + r11.left;
        float r12 = r24 + r11.top;
        if (this.ignoreX == false) goto L81;
        this.point[0] = 0.0f;
        r06 = 0.0f;
    L81:
        if (this.dimAlpha <= 0) goto L83;
    L86:
        DimView r25 = new DimView(this, this.context);
        this.dimView = r25;
        this.preDrawListener = new ItemOptions$$ExternalSyntheticLambda3(r25);
        r5.getViewTreeObserver().addOnPreDrawListener(this.preDrawListener);
        r5.addView(this.dimView, LayoutHelper.createFrame(-1, -1.0f));
        this.dimView.setProgress(0.0f);
        if (this.hideScrimUnder == false) goto L89;
        this.scrimView.setVisibility(4);
    L89:
        ValueAnimator r26 = this.dimAnimator;
        if (r26 == null) goto L92;
        r26.cancel();
        this.dimAnimator = null;
    L92:
        ValueAnimator r27 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.dimAnimator = r27;
        r27.addUpdateListener(new ItemOptions$$ExternalSyntheticLambda4(this));
        this.dimAnimator.addListener(new AnonymousClass4(this));
        boolean r28 = this.allowMoveScrim;
        ValueAnimator r34 = this.dimAnimator;
        if (r28 == false) goto L95;
        r34.setDuration(380);
        this.dimAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
    L96:
        this.dimAnimator.start();
    L98:
        if (this.allowMoveScrim == false) goto L107;
        DimView r29 = this.dimView;
        if (r29 == null) goto L107;
        if (this.animateToWidth == 0) goto L107;
        DimView.m11161$$Nest$fputmoveToX(r29, (r5.getWidth() - this.animateToWidth) / 2.0f);
        if (this.allowMoveScrimGravity != 3) goto L106;
        DimView.m11161$$Nest$fputmoveToX(this.dimView, AndroidUtilities.dp(36.0f));
    L106:
        r06 = r06 + ((-this.point[0]) + DimView.m11159$$Nest$fgetmoveToX(this.dimView));
    L107:
        float r14 = r06;
        this.layout.measure(View.MeasureSpec.makeMeasureSpec(r5.getMeasuredWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r5.getMeasuredHeight(), Integer.MIN_VALUE));
        RectF r15 = new RectF();
        Rect r07 = this.lastLayout.getPadding();
        r15.set(r07.left, r07.top, this.layout.getMeasuredWidth() - r07.right, this.layout.getMeasuredHeight() - r07.bottom);
        AnonymousClass5 r08 = new AnonymousClass5(this, this.layout, -2, -2, r5);
        this.actionBarPopupWindow = r08;
        r08.setOnDismissListener(new AnonymousClass6(this, r5));
        this.actionBarPopupWindow.setClippingEnabled(true);
        this.actionBarPopupWindow.getContentView().setFocusableInTouchMode(true);
        this.actionBarPopupWindow.setOutsideTouchable(true);
        this.actionBarPopupWindow.setFocusable(!this.dontFocus);
        this.actionBarPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.actionBarPopupWindow.setAnimationStyle(R.style.PopupContextAnimation);
        if (this.allowShowingOnTopOfKeyboard == false) goto L111;
        this.actionBarPopupWindow.setInputMethodMode(2);
        this.actionBarPopupWindow.setSoftInputMode(0);
    L118:
        if (AndroidUtilities.isTablet() == false) goto L121;
        r12 = r12 + r5.getPaddingTop();
        r14 = r14 - r5.getPaddingLeft();
    L121:
        if (this.scrimView == null) goto L136;
        int r09 = this.gravity;
        if (r09 != 3) goto L127;
        float r010 = r5.getX() + r14;
    L125:
        int r011 = (int) r010;
    L138:
        if (this.allowShowingOnTopOfKeyboard == true) goto L144;
        Rect r210 = new Rect();
        View r35 = r5.getRootView();
        r5.getWindowVisibleDisplayFrame(r210);
        int r42 = r35.getHeight();
        if (r210.top == 0) goto L142;
        int r8 = AndroidUtilities.statusBarHeight;
    L143:
        int r211 = Math.max(0, ((r42 - r8) - AndroidUtilities.getViewInset(r35)) - (r210.bottom - r210.top));
    L145:
        int r36 = (AndroidUtilities.displaySize.y - AndroidUtilities.navigationBarHeight) - r211;
        if (this.onTopOfScrim == false) goto L148;
        float r212 = 0.0f;
    L150:
        if (this.forceBelowScrim == false) goto L157;
        if (this.scrimView == null) goto L157;
        r12 = r12 + r212;
        r212 = r5.getY();
    L154:
        int r213 = (int) (r12 + r212);
    L155:
        boolean r37 = false;
    L181:
        if (this.swipeback == false) goto L189;
        if (r37 == false) goto L189;
        if (this.overridenSwipebackGravity == true) goto L189;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r38 = this.lastLayout;
        if (r38 == null) goto L189;
        r38.swipeBackGravityBottom = true;
    L189:
        if (this.allowMoveScrim == false) goto L196;
        DimView r39 = this.dimView;
        if (r39 == null) goto L196;
        DimView.m11162$$Nest$fputmoveToY(r39, (r5.getHeight() - (this.layout.getMeasuredHeight() + r11.bottom)) / 2.0f);
        r213 = (int) (DimView.m11160$$Nest$fgetmoveToY(this.dimView) + r11.bottom);
        r011 = (int) (((DimView.m11159$$Nest$fgetmoveToX(this.dimView) + r11.right) - this.layout.getMeasuredWidth()) + AndroidUtilities.dp(4.0f));
        if (this.allowMoveScrimGravity != 3) goto L196;
        r011 = (int) (DimView.m11159$$Nest$fgetmoveToX(this.dimView) - AndroidUtilities.dp(8.0f));
    L196:
        if (this.longPressSelectionEnabled == true) goto L206;
        BaseFragment r310 = this.fragment;
        if (r310 == null) goto L203;
        if (r310.getFragmentView() == null) goto L203;
        this.fragment.getFragmentView().getRootView().dispatchTouchEvent(AndroidUtilities.emptyMotionEvent());
    L203:
        if (this.container == null) goto L206;
        r5.dispatchTouchEvent(AndroidUtilities.emptyMotionEvent());
    L206:
        if (this.blurForMenu == true) goto L208;
    L210:
        this.actionBarPopupWindow.setScaleOut(this.scaleOut);
        ActionBarPopupWindow r311 = this.actionBarPopupWindow;
        float r012 = r011 + this.translateX;
        this.offsetX = r012;
        float r214 = r213 + this.translateY;
        this.offsetY = r214;
        r311.showAtLocation(r5, 0, (int) r012, (int) r214);
        if (this.longPressSelectionEnabled == false) goto L214;
        installHoverReleaseListener();
    L214:
        if (this.followScrim == false) goto L216;
        installFollowListeners();
        goto L216
    L208:
        if (this.scrimBlur3SourceBitmap == null) goto L210;
        setGapBackgroundColor(Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, this.resourcesProvider), 0.06f));
        BlurredBackgroundDrawable r312 = new BlurredBackgroundDrawableViewFactory(this.scrimBlur3SourceBitmap).create(this.layout, true).setColorProvider(BlurredBackgroundProviderImpl.scrimMenuBackground(this.resourcesProvider)).setPadding(AndroidUtilities.dp(8.0f)).setHasPadding(true).setRadius(AndroidUtilities.dp(12.0f));
        r312.setSourceOffset(r011 + this.translateX, r213 + this.translateY);
        this.layout.setBackground(r312);
    L157:
        if (this.forceBottom == false) goto L163;
        if (this.allowMoveScrim == true) goto L154;
        r213 = (int) ((Math.min(r12 + r212, r36) - this.layout.getMeasuredHeight()) + r5.getY());
        goto L155
    L163:
        if (this.scrimView != null) goto L165;
        r213 = (r5.getHeight() - this.layout.getMeasuredHeight()) / 2;
        goto L155
    L165:
        if (this.forceTop == false) goto L167;
    L170:
        r12 = (r12 - r212) - this.layout.getMeasuredHeight();
        if (this.allowCenter == true) goto L173;
    L177:
        r37 = true;
    L178:
        r213 = (int) ((r12 + r212) + r5.getY());
        goto L181
    L173:
        if ((Math.max(0.0f, r12 + r212) + this.layout.getMeasuredHeight()) <= (this.point[1] + r11.top)) goto L177;
        if (r11.height() != this.scrimView.getHeight()) goto L177;
        r12 = (((r5.getHeight() - this.layout.getMeasuredHeight()) / 2.0f) - r212) - r5.getY();
    L169:
        r37 = false;
        goto L178
    L167:
        if ((((r12 + r212) + this.layout.getMeasuredHeight()) + AndroidUtilities.dp(16.0f)) <= r36) goto L169;
    L148:
        r212 = r11.height();
        goto L150
    L142:
        r8 = 0;
        goto L143
    L144:
        r211 = 0;
        goto L145
    L127:
        if (r09 != 5) goto L130;
        float r013 = (r5.getX() + r14) + r11.width();
        float r215 = r15.right;
    L129:
        r010 = r013 - r215;
        goto L125
    L130:
        if (r09 != 1) goto L133;
        r013 = (r5.getX() + r14) + (r11.width() / 2.0f);
        r215 = this.layout.getMeasuredWidth() / 2.0f;
        goto L129
    L133:
        if ((r15.width() + r14) <= r5.getWidth()) goto L135;
        r013 = (r5.getX() + r14) + r11.width();
        r215 = r15.right;
        goto L129
    L135:
        r013 = r5.getX() + r14;
        r215 = r15.left;
        goto L129
    L136:
        r011 = (r5.getWidth() - this.layout.getMeasuredWidth()) / 2;
        goto L138
    L111:
        if (this.dontFocus == false) goto L113;
        this.actionBarPopupWindow.setInputMethodMode(1);
        this.actionBarPopupWindow.setSoftInputMode(32);
        goto L118
    L113:
        boolean r014 = this.needsFocus;
        ActionBarPopupWindow r313 = this.actionBarPopupWindow;
        if (r014 == false) goto L116;
        r313.setInputMethodMode(1);
        this.actionBarPopupWindow.setSoftInputMode(32);
        goto L118
    L116:
        r313.setInputMethodMode(2);
        this.actionBarPopupWindow.setSoftInputMode(0);
        goto L118
    L95:
        r34.setDuration(150);
        goto L96
    L83:
        if (this.blur == true) goto L86;
        if (this.blurForMenu == false) goto L98;
    L68:
        int r43 = this.animateToWidth;
        if (r43 == 0) goto L73;
        int r122 = this.animateToHeight;
        if (r122 == 0) goto L73;
        r11.set(0.0f, 0.0f, r43, r122);
    L73:
        r11.set(0.0f, 0.0f, r32.getMeasuredWidth(), this.scrimView.getMeasuredHeight());
        goto L75
    L64:
        r24 = r03;
        r05 = 0.0f;
    L50:
        if (this.scrimBlur3SourceBitmap != null) goto L52;
        this.scrimBlur3SourceBitmap = new BlurredBackgroundSourceBitmap();
        goto L52
    L30:
        if (this.minWidthDp <= 0) goto L46;
        int r015 = 0;
    L33:
        if (r015 >= (this.layout.getChildCount() - 1)) goto L46;
        if (r015 != (this.layout.getChildCount() - 1)) goto L37;
        View r216 = this.lastLayout;
    L39:
        if ((r216 instanceof ActionBarPopupWindow.ActionBarPopupWindowLayout) == false) goto L44;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r217 = (ActionBarPopupWindow.ActionBarPopupWindowLayout) r216;
        int r314 = 0;
    L42:
        if (r314 >= r217.getItemsCount()) goto L44;
        r217.getItemAt(r314).setMinimumWidth(AndroidUtilities.dp(this.minWidthDp));
        r314 = r314 + 1;
    L44:
        r015 = r015 + 1;
        goto L33
    L37:
        r216 = this.layout.getChildAt(r015);
        goto L39
    }

    public ItemOptions translate(float r2, float r3) {
        this.translateX += r2;
        this.translateY += r3;
        return this;
    }

    public void updateColors() {
    }
}
