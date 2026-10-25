package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.text.Editable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.OverScroller;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.math.inline.InlineMathController;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.XiaomiUtilities;
import org.telegram.messenger.utils.Choreographer60FpsContent;
import org.telegram.ui.ActionBar.FloatingActionMode;
import org.telegram.ui.ActionBar.FloatingToolbar;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.QuoteSpan;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;

/* loaded from: classes3.dex */
public class EditTextBoldCursor extends EditTextEffects {
    private static final String BLINK_CLASS = "android.widget.Editor$Blink";
    private static Class editorClass;
    private static Method getVerticalOffsetMethod;
    private static Field mCursorDrawableResField;
    private static Field mEditor;
    private static Method mEditorInvalidateDisplayList;
    private static Field mScrollYField;
    private static boolean mScrollYGet;
    private static Field mShowCursorField;
    private int activeLineColor;
    private Paint activeLinePaint;
    private float activeLineWidth;
    private boolean allowDrawCursor;
    private View attachedToWindow;
    BlurredBackgroundDrawableViewFactory blurredBackgroundDrawableViewFactory;
    private boolean currentDrawHintAsHeader;
    ShapeDrawable cursorDrawable;
    private boolean cursorDrawn;
    private int cursorSize;
    private float cursorWidth;
    public Utilities.Callback2<Canvas, Runnable> drawHint;
    boolean drawInMaim;
    private Object editor;
    public boolean ellipsizeByGradient;
    private LinearGradient ellipsizeGradient;
    private Matrix ellipsizeMatrix;
    private Paint ellipsizePaint;
    private int ellipsizeWidth;
    private StaticLayout errorLayout;
    private int errorLineColor;
    private TextPaint errorPaint;
    private CharSequence errorText;
    private boolean fixed;
    private boolean flingBlocked;
    private int flingDownScrollY;
    private int flingDownSelectionEnd;
    private int flingDownSelectionStart;
    private OverScroller flingScroller;
    private VelocityTracker flingVelocityTracker;
    private boolean flinging;
    public FloatingActionMode floatingActionMode;
    private FloatingToolbar floatingToolbar;
    private ViewTreeObserver.OnPreDrawListener floatingToolbarPreDrawListener;
    private boolean forceCursorEnd;
    private GradientDrawable gradientDrawable;
    private float headerAnimationProgress;
    private int headerHintColor;
    private AnimatorSet headerTransformAnimation;
    private CharSequence hint;
    private float hintAlpha;
    private AnimatedTextView.AnimatedTextDrawable hintAnimatedDrawable;
    private AnimatedTextView.AnimatedTextDrawable hintAnimatedDrawable2;
    private SubstringLayoutAnimator hintAnimator;
    private int hintColor;
    private long hintLastUpdateTime;
    private StaticLayout hintLayout;
    public int hintLayoutOffset;
    public float hintLayoutX;
    public float hintLayoutY;
    public boolean hintLayoutYFix;
    private boolean hintVisible;
    private int ignoreBottomCount;
    public boolean ignoreClipTop;
    private int ignoreTopCount;
    private final Choreographer60FpsContent.FrameCallback invalidateCallback;
    private boolean isTextWatchersSuppressed;
    private float lastLineActiveness;
    int lastOffset;
    private int lastSize;
    CharSequence lastText;
    private int lastTouchX;
    private boolean lineActive;
    private float lineActiveness;
    private int lineColor;
    private long lineLastUpdateTime;
    private Paint linePaint;
    private float lineSpacingExtra;
    private boolean lineVisible;
    private float lineY;
    public boolean lineYFix;
    private ViewTreeObserver.OnPreDrawListener listenerFixer;
    private Drawable mCursorDrawable;
    private int mHandlesColor;
    private ColorFilter mHandlesColorFilter;
    private Rect mTempRect;
    private Drawable mTextSelectHandle;
    private Drawable mTextSelectHandleLeft;
    private Drawable mTextSelectHandleRight;
    private boolean nextSetTextAnimated;
    private Runnable onPremiumMenuLockClickListener;
    private Rect padding;
    private Rect rect;
    private List<TextWatcher> registeredTextWatchers;
    float rightHintOffset;
    private int scrollY;
    private boolean showDisableMarkdown;
    private boolean supportRtlHint;
    private boolean transformHintToHeader;
    private boolean transformHintToHeaderOnFocus;
    private View windowView;

    /* renamed from: org.telegram.ui.Components.EditTextBoldCursor$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends AnimatedTextView.AnimatedTextDrawable {
        final /* synthetic */ EditTextBoldCursor this$0;

        public AnonymousClass1(EditTextBoldCursor r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.drawable.Drawable
        public void invalidateSelf() {
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.EditTextBoldCursor$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends AnimatedTextView.AnimatedTextDrawable {
        final /* synthetic */ EditTextBoldCursor this$0;

        public AnonymousClass2(EditTextBoldCursor r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.drawable.Drawable
        public void invalidateSelf() {
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.EditTextBoldCursor$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends ShapeDrawable {
        final /* synthetic */ EditTextBoldCursor this$0;

        public AnonymousClass3(EditTextBoldCursor r1, Shape r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas r1) {
            super.draw(r1);
            EditTextBoldCursor.m10138$$Nest$fputcursorDrawn(this.this$0, true);
        }
    }

    /* renamed from: org.telegram.ui.Components.EditTextBoldCursor$4, reason: invalid class name */
    public class AnonymousClass4 extends ShapeDrawable {
        final /* synthetic */ EditTextBoldCursor this$0;

        public AnonymousClass4(EditTextBoldCursor r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas r3) {
            EditTextBoldCursor r0 = this.this$0;
            if (r0.drawInMaim == false) goto L6;
            EditTextBoldCursor.m10138$$Nest$fputcursorDrawn(r0, true);
            return;
        L6:
            super.draw(r3);
        }

        @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(EditTextBoldCursor.m10136$$Nest$fgetcursorSize(this.this$0) + 20);
        }

        @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return AndroidUtilities.dp(EditTextBoldCursor.m10137$$Nest$fgetcursorWidth(this.this$0));
        }
    }

    @TargetApi(23)
    /* loaded from: classes7.dex */
    public class ActionModeCallback2Wrapper extends ActionMode.Callback2 {
        private final ActionMode.Callback mWrapped;
        final /* synthetic */ EditTextBoldCursor this$0;

        public ActionModeCallback2Wrapper(EditTextBoldCursor r1, ActionMode.Callback r2) {
            this.this$0 = r1;
            this.mWrapped = r2;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode r1, MenuItem r2) {
            return this.mWrapped.onActionItemClicked(r1, r2);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode r1, Menu r2) {
            return this.mWrapped.onCreateActionMode(r1, r2);
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode r2) {
            this.mWrapped.onDestroyActionMode(r2);
            EditTextBoldCursor.m10139$$Nest$mcleanupFloatingActionModeViews(this.this$0);
            this.this$0.floatingActionMode = null;
        }

        @Override // android.view.ActionMode.Callback2
        public void onGetContentRect(ActionMode r3, View r4, Rect r5) {
            ActionMode.Callback r0 = this.mWrapped;
            if ((r0 instanceof ActionMode.Callback2) == false) goto L6;
            ((ActionMode.Callback2) r0).onGetContentRect(r3, r4, r5);
            return;
        L6:
            super.onGetContentRect(r3, r4, r5);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode r1, Menu r2) {
            return this.mWrapped.onPrepareActionMode(r1, r2);
        }
    }

    public static /* synthetic */ void $r8$lambda$BeBRNsUCTsZxkZmxcTXKkxsSLdA(EditTextBoldCursor r0, long r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$TDajs1yojVv9Z2uHcY73v_0LrwA(EditTextBoldCursor r0, Canvas r1) {
        r0.lambda$drawHint$1(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$YAKuiqN2njHiQJRxdTG2KGdkfEM(EditTextBoldCursor r0) {
        return r0.shouldShowQuoteButton();
    }

    public static /* synthetic */ boolean $r8$lambda$nhY0chjqOJg7rt224hgRt__WIwI(EditTextBoldCursor r0) {
        return r0.lambda$startActionMode$2();
    }

    /* renamed from: -$$Nest$fgetcursorSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10136$$Nest$fgetcursorSize(EditTextBoldCursor r0) {
        return r0.cursorSize;
    }

    /* renamed from: -$$Nest$fgetcursorWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10137$$Nest$fgetcursorWidth(EditTextBoldCursor r0) {
        return r0.cursorWidth;
    }

    /* renamed from: -$$Nest$fputcursorDrawn, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10138$$Nest$fputcursorDrawn(EditTextBoldCursor r0, boolean r1) {
        r0.cursorDrawn = r1;
    }

    /* renamed from: -$$Nest$mcleanupFloatingActionModeViews, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10139$$Nest$mcleanupFloatingActionModeViews(EditTextBoldCursor r0) {
        r0.cleanupFloatingActionModeViews();
    }

    public EditTextBoldCursor(Context r3) {
        super(r3);
        this.invalidateCallback = new EditTextBoldCursor$$ExternalSyntheticLambda1(this);
        this.rect = new Rect();
        this.hintVisible = true;
        this.hintAlpha = 1.0f;
        this.allowDrawCursor = true;
        this.forceCursorEnd = false;
        this.cursorWidth = 2.0f;
        this.lineVisible = false;
        this.lineActive = false;
        this.lineActiveness = 0.0f;
        this.lastLineActiveness = 0.0f;
        this.activeLineWidth = 0.0f;
        this.transformHintToHeaderOnFocus = true;
        this.lastOffset = -1;
        this.registeredTextWatchers = new ArrayList();
        this.isTextWatchersSuppressed = false;
        this.padding = new Rect();
        this.lastTouchX = -1;
        if (Build.VERSION.SDK_INT < 26) goto L5;
        setImportantForAutofill(2);
    L5:
        init();
    }

    private void addMarkdownToggle(Menu r4) {
        if (this.showDisableMarkdown == false) goto L10;
        int r3 = R.id.menu_groupbolditalic;
        int r0 = R.id.menu_markdown;
        if (ExteraConfig.getDisableMarkdown() == false) goto L7;
        int r1 = R.string.EnableMarkdown;
    L8:
        r4.add(r3, r0, 20, LocaleController.getString(r1));
        return;
    L7:
        r1 = R.string.DisableMarkdown;
        goto L8
    }

    private void checkHeaderVisibility(boolean r6) {
        if (this.transformHintToHeader == true) goto L5;
    L11:
        boolean r0 = false;
    L13:
        if (this.currentDrawHintAsHeader == r0) goto L28;
        AnimatorSet r3 = this.headerTransformAnimation;
        if (r3 == null) goto L17;
        r3.cancel();
        this.headerTransformAnimation = null;
    L17:
        this.currentDrawHintAsHeader = r0;
        float r32 = 0.0f;
        if (r6 == false) goto L23;
        AnimatorSet r62 = new AnimatorSet();
        this.headerTransformAnimation = r62;
        if (r0 == false) goto L22;
        r32 = 1.0f;
    L22:
        r62.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "headerAnimationProgress", new float[]{r32})});
        this.headerTransformAnimation.setDuration(200);
        this.headerTransformAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.headerTransformAnimation.start();
    L26:
        invalidate();
        return;
    L23:
        if (r0 == false) goto L25;
        r32 = 1.0f;
    L25:
        this.headerAnimationProgress = r32;
        goto L26
    L28:
        return;
    L5:
        if (getText().length() <= 0) goto L7;
    L10:
        r0 = true;
        goto L13
    L7:
        if (this.transformHintToHeaderOnFocus == false) goto L11;
        if (isFocused() == false) goto L11;
        goto L10
    }

    private int clampHorizontalPosition(Drawable r6, float r7, boolean r8) {
        float r72 = Math.max(0.5f, r7 - 0.5f);
        if (this.mTempRect != null) goto L5;
        this.mTempRect = new Rect();
    L5:
        Rect r0 = this.mTempRect;
        if (r6 == null) goto L8;
        r6.getPadding(r0);
        int r62 = r6.getIntrinsicWidth();
    L9:
        int r02 = getScrollX();
        float r1 = r72 - r02;
        int r2 = (getWidth() - getCompoundPaddingLeft()) - getCompoundPaddingRight();
        if (r8 == true) goto L12;
    L15:
        if (r8 == false) goto L27;
        if (Math.abs(r1) <= 1.0f) goto L25;
        if (TextUtils.isEmpty(getText()) == false) goto L27;
        if ((1048576 - r02) > (r2 + 1.0f)) goto L27;
        if (r72 > 1.0f) goto L27;
    L25:
        return r02 - this.mTempRect.left;
    L27:
        return ((int) r72) - this.mTempRect.left;
    L12:
        if (r1 < (r2 - 1.0f)) goto L15;
        return (r2 + r02) - (r62 - this.mTempRect.right);
    L8:
        r0.setEmpty();
        r62 = 0;
        goto L9
    }

    private void cleanupFloatingActionModeViews() {
        FloatingToolbar r0 = this.floatingToolbar;
        if (r0 == null) goto L6;
        r0.dismiss();
        this.floatingToolbar = null;
    L6:
        if (this.floatingToolbarPreDrawListener == null) goto L9;
        getViewTreeObserver().removeOnPreDrawListener(this.floatingToolbarPreDrawListener);
        this.floatingToolbarPreDrawListener = null;
        return;
    }

    private void drawHint(Canvas r12) {
        if (length() != 0) goto L5;
    L7:
        boolean r0 = this.hintVisible;
        if (r0 == true) goto L10;
    L11:
        if (r0 == false) goto L13;
    L28:
        AnimatedTextView.AnimatedTextDrawable r02 = this.hintAnimatedDrawable;
        if (r02 == null) goto L46;
        if (TextUtils.isEmpty(r02.getText()) == true) goto L46;
        if (this.hintVisible == false) goto L35;
    L36:
        AnimatedTextView.AnimatedTextDrawable r03 = this.hintAnimatedDrawable2;
        AnimatedTextView.AnimatedTextDrawable r1 = this.hintAnimatedDrawable;
        if (r03 != null) goto L39;
        r1.setRightPadding(0.0f);
    L43:
        this.hintAnimatedDrawable.setAlpha((int) (Color.alpha(this.hintColor) * this.hintAlpha));
        this.hintAnimatedDrawable.draw(r12);
        return;
    L39:
        if ((r1.getCurrentWidth() + this.hintAnimatedDrawable2.getCurrentWidth()) >= getMeasuredWidth()) goto L41;
        r12.save();
        r12.translate((this.hintAnimatedDrawable2.getCurrentWidth() - getMeasuredWidth()) + this.hintAnimatedDrawable.getCurrentWidth(), 0.0f);
        this.hintAnimatedDrawable2.setAlpha((int) (Color.alpha(this.hintColor) * this.hintAlpha));
        this.hintAnimatedDrawable2.draw(r12);
        r12.restore();
        this.hintAnimatedDrawable.setRightPadding(0.0f);
        goto L43
    L41:
        r12.save();
        r12.translate(this.rightHintOffset, 0.0f);
        this.hintAnimatedDrawable2.setAlpha((int) (Color.alpha(this.hintColor) * this.hintAlpha));
        this.hintAnimatedDrawable2.draw(r12);
        r12.restore();
        this.hintAnimatedDrawable.setRightPadding((this.hintAnimatedDrawable2.getCurrentWidth() + AndroidUtilities.dp(2.0f)) - this.rightHintOffset);
        goto L43
    L35:
        if (this.hintAlpha != 0.0f) goto L36;
    L46:
        if (this.hintLayout != null) goto L48;
        return;
    L48:
        if (this.hintVisible == false) goto L50;
    L51:
        int r04 = getPaint().getColor();
        r12.save();
        float r3 = this.hintLayout.getLineLeft(0);
        float r5 = this.hintLayout.getLineWidth(0);
        if (r3 == 0.0f) goto L54;
        int r7 = (int) (0.0f - r3);
    L56:
        if (this.supportRtlHint == true) goto L58;
    L60:
        float r72 = (r7 + getScrollX()) + this.hintLayoutOffset;
        this.hintLayoutX = r72;
        float r8 = (this.lineY - this.hintLayout.getHeight()) - AndroidUtilities.dp2(7.0f);
        this.hintLayoutY = r8;
        r12.translate(r72, r8);
    L62:
        if (this.transformHintToHeader == false) goto L71;
        float r73 = 1.0f - (this.headerAnimationProgress * 0.3f);
        if (this.supportRtlHint == true) goto L66;
    L68:
        if (r3 == 0.0f) goto L70;
        r12.translate(r3 * (1.0f - r73), 0.0f);
    L70:
        r12.scale(r73, r73);
        r12.translate(0.0f, (-AndroidUtilities.dp(22.0f)) * this.headerAnimationProgress);
        getPaint().setColor(ColorUtils.blendARGB(this.hintColor, this.headerHintColor, this.headerAnimationProgress));
    L72:
        SubstringLayoutAnimator r13 = this.hintAnimator;
        if (r13 != null) goto L75;
    L77:
        Utilities.Callback2<Canvas, Runnable> r14 = this.drawHint;
        if (r14 == null) goto L80;
        r14.run(r12, new EditTextBoldCursor$$ExternalSyntheticLambda2(this, r12));
    L81:
        getPaint().setTypeface(AndroidUtilities.regular());
        getPaint().setColor(r04);
        r12.restore();
        return;
    L80:
        this.hintLayout.draw(r12);
        goto L81
    L75:
        if (r13.animateTextChange == false) goto L77;
        r12.save();
        r12.clipRect(0, 0, getMeasuredWidth(), getMeasuredHeight());
        this.hintAnimator.draw(r12, getPaint());
        r12.restore();
        goto L81
    L66:
        if (LocaleController.isRTL == false) goto L68;
        float r52 = r5 + r3;
        r12.translate(r52 - (r52 * r73), 0.0f);
        goto L70
    L71:
        getPaint().setColor(this.hintColor);
        getPaint().setAlpha((int) ((this.hintAlpha * 255.0f) * (Color.alpha(this.hintColor) / 255.0f)));
        goto L72
    L58:
        if (LocaleController.isRTL == false) goto L60;
        float r74 = (r7 + getScrollX()) + (getMeasuredWidth() - r5);
        this.hintLayoutX = r74;
        float r82 = (this.lineY - this.hintLayout.getHeight()) - AndroidUtilities.dp(7.0f);
        this.hintLayoutY = r82;
        r12.translate(r74, r82);
        goto L62
    L54:
        r7 = 0;
        goto L56
    L50:
        if (this.hintAlpha != 0.0f) goto L51;
        return;
    L13:
        if (this.hintAlpha == 0.0f) goto L28;
    L14:
        long r32 = System.currentTimeMillis();
        long r53 = r32 - this.hintLastUpdateTime;
        if (r53 >= 0) goto L17;
    L18:
        r53 = 17;
    L19:
        this.hintLastUpdateTime = r32;
        boolean r05 = this.hintVisible;
        float r33 = this.hintAlpha;
        if (r05 == false) goto L24;
        float r34 = r33 + (r53 / 150.0f);
        this.hintAlpha = r34;
        if (r34 <= 1.0f) goto L27;
        this.hintAlpha = 1.0f;
    L27:
        invalidate();
        goto L28
    L24:
        float r35 = r33 - (r53 / 150.0f);
        this.hintAlpha = r35;
        if (r35 >= 0.0f) goto L27;
        this.hintAlpha = 0.0f;
        goto L27
    L17:
        if (r53 <= 17) goto L19;
    L10:
        if (this.hintAlpha != 1.0f) goto L14;
    L5:
        if (this.transformHintToHeader == true) goto L7;
    }

    private int getMaxFlingScrollY() {
        Layout r0 = getLayout();
        if (r0 != null) goto L6;
        return 0;
    L6:
        return Math.max(0, r0.getHeight() - ((getHeight() - super.getExtendedPaddingTop()) - super.getExtendedPaddingBottom()));
    }

    @SuppressLint({"PrivateApi"})
    private void init() {
        this.linePaint = new Paint();
        this.activeLinePaint = new Paint();
        TextPaint r0 = new TextPaint(1);
        this.errorPaint = r0;
        r0.setTextSize(AndroidUtilities.dp(11.0f));
        int r02 = Build.VERSION.SDK_INT;
        if (r02 < 26) goto L6;
        setImportantForAutofill(2);
    L6:
        if (r02 < 29) goto L8;
        AnonymousClass4 r03 = new AnonymousClass4(this);
        this.cursorDrawable = r03;
        char r16 = 0;
        float r17 = 10.0f;
        int r18 = 2;
        r03.setShape(new RoundRectShape(new float[]{AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f)}, null, null));
        GradientDrawable r04 = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-11230757, -11230757});
        this.gradientDrawable = r04;
        r04.setCornerRadius(AndroidUtilities.dp(10.0f));
        setTextCursorDrawable(this.cursorDrawable);
    L46:
        if (mScrollYGet == true) goto L52;
        if (mScrollYField != null) goto L52;
        mScrollYGet = true;     // Catch: Throwable -> L39
        Field r05 = View.class.getDeclaredField("mScrollY");     // Catch: Throwable -> L39
        mScrollYField = r05;     // Catch: Throwable -> L39
        r05.setAccessible(true);     // Catch: Throwable -> L39
    L52:
    L19:
        th = move-exception;
        FileLog.e(th);
    L26:
        if (this.cursorDrawable == null) goto L54;
    L37:
        this.cursorSize = AndroidUtilities.dp(24.0f);
        return;
    L54:
        GradientDrawable.Orientation r4 = GradientDrawable.Orientation.TOP_BOTTOM;     // Catch: Throwable -> L43
        int[] r5 = new int[r18];     // Catch: Throwable -> L43
        r5[r16] = -11230757;     // Catch: Throwable -> L43
        r5[1] = -11230757;     // Catch: Throwable -> L43
        GradientDrawable r06 = new GradientDrawable(r4, r5);     // Catch: Throwable -> L43
        this.gradientDrawable = r06;     // Catch: Throwable -> L43
        r06.setCornerRadius(AndroidUtilities.dp(r17));     // Catch: Throwable -> L43
        if (Build.VERSION.SDK_INT < 29) goto L30;
        setTextCursorDrawable(this.gradientDrawable);     // Catch: Throwable -> L43
    L30:
        this.editor = mEditor.get(this);     // Catch: Throwable -> L43
    L50:
        if (mCursorDrawableResField != null) goto L34;
        Field r07 = TextView.class.getDeclaredField("mCursorDrawableRes");     // Catch: Throwable -> L42
        mCursorDrawableResField = r07;     // Catch: Throwable -> L42
        r07.setAccessible(true);     // Catch: Throwable -> L42
    L34:
        Field r08 = mCursorDrawableResField;     // Catch: Throwable -> L42
        if (r08 == null) goto L37;
        r08.set(this, Integer.valueOf(R.drawable.field_carret_empty));     // Catch: Throwable -> L42
        goto L37
    L15:
        if (editorClass != null) goto L26;
        Field r09 = TextView.class.getDeclaredField("mEditor");     // Catch: Throwable -> L19
        mEditor = r09;     // Catch: Throwable -> L19
        r09.setAccessible(true);     // Catch: Throwable -> L19
        Class<?> r010 = Class.forName("android.widget.Editor");     // Catch: Throwable -> L19
        editorClass = r010;     // Catch: Throwable -> L19
        Field r011 = r010.getDeclaredField("mShowCursor");     // Catch: Throwable -> L19 Exception -> L40
        mShowCursorField = r011;     // Catch: Throwable -> L19 Exception -> L40
        r011.setAccessible(true);     // Catch: Throwable -> L19 Exception -> L40
    L48:
        Method r012 = editorClass.getDeclaredMethod("invalidateTextDisplayList", null);     // Catch: Throwable -> L19 Exception -> L41
        mEditorInvalidateDisplayList = r012;     // Catch: Throwable -> L19 Exception -> L41
        r012.setAccessible(true);     // Catch: Throwable -> L19 Exception -> L41
    L22:
        Method r013 = TextView.class.getDeclaredMethod("getVerticalOffset", new Class[]{Boolean.TYPE});     // Catch: Throwable -> L19
        getVerticalOffsetMethod = r013;     // Catch: Throwable -> L19
        r013.setAccessible(true);     // Catch: Throwable -> L19
        goto L26
    L8:
        r18 = 2;
        r17 = 10.0f;
        r16 = 0;
        goto L46
    }

    private /* synthetic */ void lambda$drawHint$1(Canvas r1) {
        this.hintLayout.draw(r1);
    }

    private /* synthetic */ void lambda$new$0(long r1) {
        invalidate();
    }

    private /* synthetic */ boolean lambda$startActionMode$2() {
        FloatingActionMode r0 = this.floatingActionMode;
        if (r0 == null) goto L7;
        r0.updateViewLocationInWindow();
        return true;
    L7:
        return true;
    }

    private boolean shouldShowQuoteButton() {
        if (hasSelection() == true) goto L5;
    L22:
        return false;
    L5:
        if (getSelectionStart() < 0) goto L22;
        if (getSelectionEnd() < 0) goto L22;
        if (getSelectionStart() == getSelectionEnd()) goto L22;
        Editable r0 = getText();
        if (r0 != null) goto L14;
        return false;
    L14:
        QuoteSpan.QuoteStyleSpan[] r4 = (QuoteSpan.QuoteStyleSpan[]) r0.getSpans(getSelectionStart(), getSelectionEnd(), QuoteSpan.QuoteStyleSpan.class);
        if (r4 != null) goto L17;
        return true;
    L17:
        if (r4.length == 0) goto L23;
        return false;
    L23:
        return true;
    }

    private void stopFling() {
        if (this.flinging == false) goto L6;
        this.flinging = false;
        this.flingScroller.abortAnimation();
        return;
    }

    private void updateCursorPosition(int r4, int r5, float r6) {
        InlineMathController r0 = getInlineMath();
        GradientDrawable r1 = this.gradientDrawable;
        if (r0 != null) goto L5;
    L8:
        boolean r02 = true;
    L9:
        int r62 = clampHorizontalPosition(r1, r6, r02);
        int r03 = AndroidUtilities.dp(this.cursorWidth);
        GradientDrawable r12 = this.gradientDrawable;
        Rect r3 = this.mTempRect;
        r12.setBounds(r62, r4 - r3.top, r03 + r62, r5 + r3.bottom);
        return;
    L5:
        if (r0.hasCursorShift() == false) goto L8;
        r02 = false;
        goto L9
    }

    private boolean updateCursorPosition() {
        Layout r0 = getLayout();
        if (this.forceCursorEnd == false) goto L5;
        int r1 = r0.getText().length();
    L6:
        int r2 = r0.getLineForOffset(r1);
        updateCursorPosition(r0.getLineTop(r2), r0.getLineTop(r2 + 1), r0.getPrimaryHorizontal(r1));
        this.lastText = r0.getText();
        this.lastOffset = r1;
        return true;
    L5:
        r1 = getSelectionStart();
        goto L6
    }

    private Drawable updateHandleDrawable(Drawable r1, boolean r2) {
        if (r1 == null) goto L8;
        if (r2 == false) goto L5;
        r1 = r1.mutate();
    L5:
        ColorFilter r0 = this.mHandlesColorFilter;
        if (r0 == null) goto L8;
        r1.setColorFilter(r0);
    L8:
        return r1;
    }

    @Override // android.widget.TextView
    public void addTextChangedListener(TextWatcher r2) {
        this.registeredTextWatchers.add(r2);
        if (this.isTextWatchersSuppressed == false) goto L5;
        return;
    L5:
        super.addTextChangedListener(r2);
    }

    @Override // android.widget.TextView, android.view.View
    public void computeScroll() {
        super.computeScroll();
        if (this.flinging == true) goto L6;
        return;
    L6:
        if (this.flingScroller.computeScrollOffset() == false) goto L12;
        int r0 = Math.max(0, Math.min(this.flingScroller.getCurrY(), getMaxFlingScrollY()));
        if (r0 == getScrollY()) goto L10;
        scrollTo(getScrollX(), r0);
    L10:
        postInvalidateOnAnimation();
        return;
    L12:
        this.flinging = false;
        moveCursorToVisibleOffset();
    }

    public void dispatchTextWatchersTextChanged() {
        Iterator<TextWatcher> r0 = this.registeredTextWatchers.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        TextWatcher r1 = r0.next();
        r1.beforeTextChanged(_UrlKt.FRAGMENT_ENCODE_SET, 0, length(), length());
        r1.onTextChanged(getText(), 0, length(), length());
        r1.afterTextChanged(getText());
        goto L4
    }

    public void extendActionMode(ActionMode r1, Menu r2) {
    }

    @SuppressLint({"PrivateApi"})
    public void fixHandleView(boolean r5) {
        if (r5 == false) goto L6;
        this.fixed = false;
        return;
    L6:
        if (this.fixed == false) goto L18;
        return;
    L18:
        if (editorClass != null) goto L12;
        editorClass = Class.forName("android.widget.Editor");     // Catch: Throwable -> L17
        Field r0 = TextView.class.getDeclaredField("mEditor");     // Catch: Throwable -> L17
        mEditor = r0;     // Catch: Throwable -> L17
        r0.setAccessible(true);     // Catch: Throwable -> L17
        this.editor = mEditor.get(this);     // Catch: Throwable -> L17
    L12:
        if (this.listenerFixer != null) goto L14;
        Method r02 = editorClass.getDeclaredMethod("getPositionListener", null);     // Catch: Throwable -> L17
        r02.setAccessible(true);     // Catch: Throwable -> L17
        this.listenerFixer = (ViewTreeObserver.OnPreDrawListener) r02.invoke(this.editor, null);     // Catch: Throwable -> L17
    L14:
        ViewTreeObserver.OnPreDrawListener r03 = this.listenerFixer;     // Catch: Throwable -> L17
        Objects.requireNonNull(r03);     // Catch: Throwable -> L17
        AndroidUtilities.runOnUIThread(new EditTextBoldCursor$$ExternalSyntheticLambda0(r03), 500);     // Catch: Throwable -> L17
    L15:
        this.fixed = true;
    }

    public int getActionModeStyle() {
        return 1;
    }

    @Override // android.widget.TextView, android.view.View
    @TargetApi(26)
    public int getAutofillType() {
        return 0;
    }

    @Override // org.telegram.ui.Components.EditTextEffects
    public int getDrawScrollY() {
        if (this.drawInMaim == false) goto L10;
        if (mScrollYField == null) goto L10;
        int r0 = this.scrollY;
        if (r0 == Integer.MAX_VALUE) goto L10;
        return r0;
    L10:
        return super.getDrawScrollY();
    }

    public StaticLayout getErrorLayout(int r9) {
        if (TextUtils.isEmpty(this.errorText) == false) goto L7;
        return null;
    L7:
        return new StaticLayout(this.errorText, this.errorPaint, r9, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
    }

    @Override // android.widget.TextView
    public int getExtendedPaddingBottom() {
        int r0 = this.ignoreBottomCount;
        if (r0 == 0) goto L11;
        this.ignoreBottomCount = r0 - 1;
        int r1 = this.scrollY;
        if (r1 != Integer.MAX_VALUE) goto L7;
        return 0;
    L7:
        return -r1;
    L11:
        return super.getExtendedPaddingBottom();
    }

    @Override // android.widget.TextView
    public int getExtendedPaddingTop() {
        int r0 = this.ignoreTopCount;
        if (r0 == 0) goto L7;
        this.ignoreTopCount = r0 - 1;
        return 0;
    L7:
        return super.getExtendedPaddingTop();
    }

    @Keep
    public float getHeaderAnimationProgress() {
        return this.headerAnimationProgress;
    }

    public Layout getHintLayoutEx() {
        return this.hintLayout;
    }

    @Override // android.widget.TextView
    public float getLineSpacingExtra() {
        return super.getLineSpacingExtra();
    }

    public float getLineY() {
        return this.lineY;
    }

    public Runnable getOnPremiumMenuLockClickListener() {
        return this.onPremiumMenuLockClickListener;
    }

    public Theme.ResourcesProvider getResourcesProvider() {
        return null;
    }

    @Override // android.widget.TextView
    public Drawable getTextCursorDrawable() {
        if (this.cursorDrawable != null) goto L5;
        AnonymousClass3 r0 = new AnonymousClass3(this, new RoundRectShape(new float[]{AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f)}, null, null));
        r0.getPaint().setColor(0);
        return r0;
    L5:
        return super.getTextCursorDrawable();
    }

    @Override // android.widget.TextView
    public Drawable getTextSelectHandle() {
        if (this.mTextSelectHandle != null) goto L6;
        this.mTextSelectHandle = updateHandleDrawable(super.getTextSelectHandle(), true);
    L6:
        return this.mTextSelectHandle;
    }

    @Override // android.widget.TextView
    public Drawable getTextSelectHandleLeft() {
        if (this.mTextSelectHandleLeft != null) goto L6;
        this.mTextSelectHandleLeft = updateHandleDrawable(super.getTextSelectHandleLeft(), true);
    L6:
        return this.mTextSelectHandleLeft;
    }

    @Override // android.widget.TextView
    public Drawable getTextSelectHandleRight() {
        if (this.mTextSelectHandleRight != null) goto L6;
        this.mTextSelectHandleRight = updateHandleDrawable(super.getTextSelectHandleRight(), true);
    L6:
        return this.mTextSelectHandleRight;
    }

    public boolean hasErrorText() {
        return !TextUtils.isEmpty(this.errorText);
    }

    public void hideActionMode() {
        cleanupFloatingActionModeViews();
    }

    public void invalidateForce() {
        invalidate();
        if (isHardwareAccelerated() == false) goto L19;
        if (mEditorInvalidateDisplayList != null) goto L8;
        return;
    L8:
        if (this.editor != null) goto L10;
        this.editor = mEditor.get(this);     // Catch: Exception -> L14
    L10:
        Object r2 = this.editor;     // Catch: Exception -> L14
        if (r2 == null) goto L18;
        mEditorInvalidateDisplayList.invoke(r2, null);     // Catch: Exception -> L14
        return;
    L18:
        return;
    L20:
        return;
    }

    public boolean isTextWatchersSuppressed() {
        return this.isTextWatchersSuppressed;
    }

    @Override // org.telegram.ui.Components.EditTextEffects, android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();     // Catch: Exception -> L4
    L6:
        this.attachedToWindow = getRootView();
        if (Build.VERSION.SDK_INT >= 29) goto L12;
        Choreographer60FpsContent.getInstance().addFrameCallback(this.invalidateCallback, 2);
        return;
    L12:
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    @Override // org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attachedToWindow = null;
        stopFling();
        if (Build.VERSION.SDK_INT >= 29) goto L6;
        Choreographer60FpsContent.getInstance().removeFrameCallback(this.invalidateCallback);
        return;
    }

    @Override // org.telegram.ui.Components.EditTextEffects, android.widget.TextView, android.view.View
    public void onDraw(Canvas r18) {
        drawHint(r18);
        if (this.ellipsizeByGradient == false) goto L5;
        Canvas r2 = r18;
        r2.saveLayerAlpha((getScrollX() + getPaddingLeft()) - this.ellipsizeWidth, 0.0f, ((getScrollX() + getWidth()) - getPaddingRight()) + this.ellipsizeWidth, getHeight(), 255, 31);
    L6:
        int r3 = getExtendedPaddingTop();
        InlineMathController r4 = getInlineMath();
        if (r4 != null) goto L9;
        int r5 = 0;
    L10:
        if (r4 != null) goto L12;
        int r6 = 0;
    L14:
        if (r4 != null) goto L16;
        float r7 = 0.0f;
    L17:
        if (r4 != null) goto L19;
        float r10 = 0.0f;
    L20:
        this.scrollY = Integer.MAX_VALUE;
        Field r0 = mScrollYField;     // Catch: Exception -> L24
        if (r0 == null) goto L26;
        this.scrollY = r0.getInt(this);     // Catch: Exception -> L24
        mScrollYField.set(this, 0);     // Catch: Exception -> L24
    L189:
    L30:
        this.ignoreTopCount = 1;
        this.ignoreBottomCount = 1;
        r2.save();
        if (r4 == null) goto L33;
        r4.clipReplacedParagraph(r2, r6);
    L33:
        r2.translate(0.0f, r3);
        this.drawInMaim = true;     // Catch: Exception -> L36
        super.onDraw(r18);     // Catch: Exception -> L36
        this.drawInMaim = false;     // Catch: Exception -> L36
    L39:
        Field r02 = mScrollYField;
        if (r02 == null) goto L51;
        int r32 = this.scrollY;
        if (r32 == Integer.MAX_VALUE) goto L51;
        r02.set(this, Integer.valueOf(r32));     // Catch: Exception -> L45
    L45:
        e = move-exception;
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L51;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return;
    L51:
        r2.restore();
        if (this.cursorDrawable != null) goto L91;
        Field r03 = mShowCursorField;     // Catch: Throwable -> L62
        if (r03 == null) goto L65;
        Object r11 = this.editor;     // Catch: Throwable -> L62
        if (r11 == null) goto L65;
        if (((SystemClock.uptimeMillis() - r03.getLong(r11)) % 1000) < 500) goto L60;
    L64:
        boolean r04 = false;
    L67:
        if (this.allowDrawCursor == false) goto L117;
        if (r04 == false) goto L117;
        r2.save();     // Catch: Throwable -> L62
        if (getVerticalOffsetMethod == null) goto L75;
        if ((getGravity() & 112) == 48) goto L77;
        int r05 = ((Integer) getVerticalOffsetMethod.invoke(this, new Object[]{Boolean.TRUE})).intValue();     // Catch: Throwable -> L62
    L78:
        r2.translate(getPaddingLeft() + r7, (getExtendedPaddingTop() + r05) + r10);     // Catch: Throwable -> L62
        Layout r06 = getLayout();     // Catch: Throwable -> L62
        int r33 = r06.getLineForOffset(getSelectionStart());     // Catch: Throwable -> L62
        int r07 = r06.getLineCount();     // Catch: Throwable -> L62
        updateCursorPosition();     // Catch: Throwable -> L62
        Rect r72 = this.gradientDrawable.getBounds();     // Catch: Throwable -> L62
        Rect r102 = this.rect;     // Catch: Throwable -> L62
        r102.left = r72.left;     // Catch: Throwable -> L62
        r102.right = r72.left + AndroidUtilities.dp(this.cursorWidth);     // Catch: Throwable -> L62
        Rect r103 = this.rect;     // Catch: Throwable -> L62
        int r112 = r72.bottom;     // Catch: Throwable -> L62
        r103.bottom = r112;     // Catch: Throwable -> L62
        r103.top = r72.top;     // Catch: Throwable -> L62
        float r73 = this.lineSpacingExtra;     // Catch: Throwable -> L62
        if (r73 != 0.0f) goto L81;
    L83:
        int r08 = r103.centerY();     // Catch: Throwable -> L62
        int r34 = this.cursorSize;     // Catch: Throwable -> L62
        r103.top = r08 - (r34 / 2);     // Catch: Throwable -> L62
        Rect r09 = this.rect;     // Catch: Throwable -> L62
        r09.bottom = r09.top + r34;     // Catch: Throwable -> L62
        this.gradientDrawable.setBounds(r09);     // Catch: Throwable -> L62
        this.gradientDrawable.draw(r2);     // Catch: Throwable -> L62
        r2.restore();     // Catch: Throwable -> L62
        goto L117
    L81:
        if (r33 >= (r07 - 1)) goto L83;
        r103.bottom = (int) (r112 - r73);     // Catch: Throwable -> L62
    L77:
        r05 = 0;
        goto L78
    L75:
        if ((getGravity() & 112) == 48) goto L77;
        r05 = getTotalPaddingTop() - getExtendedPaddingTop();     // Catch: Throwable -> L62
    L117:
        if (r4 == null) goto L120;
        r4.draw(r2, r5, r6, getScrollY() - this.offsetY, (getScrollY() + getMeasuredHeight()) - this.offsetY);
    L120:
        if (this.lineVisible == true) goto L122;
    L172:
        Canvas r22 = r18;
    L174:
        if (this.ellipsizeByGradient == false) goto L192;
        r22.save();
        r22.translate(getScrollX(), 0.0f);
        this.ellipsizePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.ellipsizeMatrix.reset();
        this.ellipsizeGradient.setLocalMatrix(this.ellipsizeMatrix);
        r22.drawRect(getPaddingLeft() - this.ellipsizeWidth, 0.0f, getPaddingLeft(), getHeight(), this.ellipsizePaint);
        this.ellipsizeMatrix.reset();
        this.ellipsizeMatrix.postScale(-1.0f, 1.0f, this.ellipsizeWidth / 2.0f, 0.0f);
        this.ellipsizeMatrix.postTranslate(getWidth() - getPaddingRight(), 0.0f);
        this.ellipsizeGradient.setLocalMatrix(this.ellipsizeMatrix);
        r18.drawRect(getWidth() - getPaddingRight(), 0.0f, (getWidth() - getPaddingRight()) + this.ellipsizeWidth, getHeight(), this.ellipsizePaint);
        r18.restore();
        r18.restore();
        return;
    L192:
        return;
    L122:
        if (this.lineColor == 0) goto L172;
        int r010 = AndroidUtilities.dp(1.0f);
        boolean r23 = this.lineActive;
        if (TextUtils.isEmpty(this.errorText) == true) goto L127;
        this.linePaint.setColor(this.errorLineColor);
        r010 = AndroidUtilities.dp(2.0f);
        this.lineActive = false;
    L131:
        if (this.lineActive == r23) goto L133;
        this.lineLastUpdateTime = SystemClock.elapsedRealtime();
        this.lastLineActiveness = this.lineActiveness;
    L133:
        float r24 = (SystemClock.elapsedRealtime() - this.lineLastUpdateTime) / 150.0f;
        if (r24 < 1.0f) goto L142;
        boolean r42 = this.lineActive;
        if (r42 == true) goto L138;
    L139:
        if (r42 == true) goto L150;
        if (this.lineActiveness != 0.0f) goto L142;
    L150:
        if (getLayout() != null) goto L152;
        int r25 = 0;
    L153:
        int r26 = ((r25 - getMeasuredHeight()) + getPaddingBottom()) + getPaddingTop();
        if (this.lineYFix == false) goto L157;
        int r27 = getMeasuredHeight() - AndroidUtilities.dp(2.0f);
    L156:
        int r8 = r27;
        int r28 = this.lastTouchX;
        if (r28 >= 0) goto L161;
        r28 = getMeasuredWidth() / 2;
    L161:
        int r12 = r28;
        int r13 = Math.max(r12, getMeasuredWidth() - r12) * 2;
        if (this.lineActiveness >= 1.0f) goto L164;
        r18.drawRect(getScrollX(), r8 - r010, getScrollX() + getMeasuredWidth(), r8, this.linePaint);
    L164:
        float r011 = this.lineActiveness;
        if (r011 <= 0.0f) goto L172;
        float r012 = CubicBezierInterpolator.EASE_BOTH.getInterpolation(r011);
        boolean r29 = this.lineActive;
        if (r29 == false) goto L169;
        this.activeLineWidth = r13 * r012;
    L169:
        if (r29 == false) goto L171;
        r012 = 1.0f;
    L171:
        int r013 = (int) (r012 * AndroidUtilities.dp(2.0f));
        float r35 = r12;
        r22 = r18;
        r22.drawRect(getScrollX() + Math.max(0.0f, r35 - (this.activeLineWidth / 2.0f)), r8 - r013, getScrollX() + Math.min(r35 + (this.activeLineWidth / 2.0f), getMeasuredWidth()), r8, this.activeLinePaint);
        goto L174
    L157:
        r27 = Math.min(Math.max(0, r26 - getScrollY()), AndroidUtilities.dp(2.0f)) + (((int) this.lineY) + getScrollY());
        goto L156
    L152:
        r25 = getLayout().getHeight();
        goto L153
    L138:
        if (this.lineActiveness == 1.0f) goto L139;
    L142:
        float r43 = this.lastLineActiveness;
        if (this.lineActive == false) goto L145;
        float r52 = 1.0f;
    L146:
        this.lineActiveness = AndroidUtilities.lerp(r43, r52, Math.max(0.0f, Math.min(1.0f, r24)));
        if (r24 >= 1.0f) goto L150;
        invalidate();
        goto L150
    L145:
        r52 = 0.0f;
        goto L146
    L127:
        if (isFocused() == false) goto L129;
        this.lineActive = true;
        goto L131
    L129:
        this.linePaint.setColor(this.lineColor);
        this.lineActive = false;
        goto L131
    L60:
        if (isFocused() == false) goto L64;
        r04 = true;
    L65:
        r04 = this.cursorDrawn;     // Catch: Throwable -> L62
        this.cursorDrawn = false;     // Catch: Throwable -> L62
    L62:
        th = move-exception;
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L117;
        HttpUrl$$ExternalSyntheticBUOutline0.m(th);
        return;
    L91:
        if (this.cursorDrawn == false) goto L117;
        if (this.allowDrawCursor == false) goto L117;
        r2.save();     // Catch: Throwable -> L99
        if (getVerticalOffsetMethod == null) goto L102;
        if ((getGravity() & 112) == 48) goto L104;
        int r014 = ((Integer) getVerticalOffsetMethod.invoke(this, new Object[]{Boolean.TRUE})).intValue();     // Catch: Throwable -> L99
    L105:
        r2.translate(getPaddingLeft() + r7, (getExtendedPaddingTop() + r014) + r10);     // Catch: Throwable -> L99
        Layout r015 = getLayout();     // Catch: Throwable -> L99
        int r36 = r015.getLineForOffset(getSelectionStart());     // Catch: Throwable -> L99
        int r016 = r015.getLineCount();     // Catch: Throwable -> L99
        updateCursorPosition();     // Catch: Throwable -> L99
        Rect r74 = this.gradientDrawable.getBounds();     // Catch: Throwable -> L99
        Rect r104 = this.rect;     // Catch: Throwable -> L99
        r104.left = r74.left;     // Catch: Throwable -> L99
        r104.right = r74.left + AndroidUtilities.dp(this.cursorWidth);     // Catch: Throwable -> L99
        Rect r105 = this.rect;     // Catch: Throwable -> L99
        int r113 = r74.bottom;     // Catch: Throwable -> L99
        r105.bottom = r113;     // Catch: Throwable -> L99
        r105.top = r74.top;     // Catch: Throwable -> L99
        float r75 = this.lineSpacingExtra;     // Catch: Throwable -> L99
        if (r75 != 0.0f) goto L108;
    L110:
        int r017 = r105.centerY();     // Catch: Throwable -> L99
        int r37 = this.cursorSize;     // Catch: Throwable -> L99
        r105.top = r017 - (r37 / 2);     // Catch: Throwable -> L99
        Rect r018 = this.rect;     // Catch: Throwable -> L99
        r018.bottom = r018.top + r37;     // Catch: Throwable -> L99
        this.gradientDrawable.setBounds(r018);     // Catch: Throwable -> L99
        this.gradientDrawable.draw(r2);     // Catch: Throwable -> L99
        r2.restore();     // Catch: Throwable -> L99
        this.cursorDrawn = false;     // Catch: Throwable -> L99
        goto L117
    L108:
        if (r36 >= (r016 - 1)) goto L110;
        r105.bottom = (int) (r113 - r75);     // Catch: Throwable -> L99
    L104:
        r014 = 0;
        goto L105
    L102:
        if ((getGravity() & 112) == 48) goto L104;
        r014 = getTotalPaddingTop() - getExtendedPaddingTop();     // Catch: Throwable -> L99
    L99:
        th = move-exception;
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L117;
        HttpUrl$$ExternalSyntheticBUOutline0.m(th);
        return;
    L36:
        e = move-exception;
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L39;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return;
    L26:
        this.scrollY = getScrollX();     // Catch: Exception -> L24
    L24:
        e = move-exception;
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L30;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return;
    L19:
        r10 = r4.getCursorShiftY();
        goto L20
    L16:
        r7 = r4.getCursorShiftX();
        goto L17
    L12:
        r6 = getTotalPaddingTop();
        goto L14
    L9:
        r5 = getTotalPaddingLeft();
        goto L10
    L5:
        r2 = r18;
        goto L6
    }

    @Override // com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
    public void onFocusChanged(boolean r1, int r2, Rect r3) {
        super.onFocusChanged(r1, r2, r3);     // Catch: Exception -> L4
    L6:
        checkHeaderVisibility(true);
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        r2.setClassName("android.widget.EditText");
        if (this.hintLayout != null) goto L5;
        return;
    L5:
        if (getText().length() > 0) goto L8;
        r2.setText(this.hintLayout.getText());
        return;
    L8:
        AccessibilityNodeInfoCompat.wrap(r2).setHintText(this.hintLayout.getText());
    }

    @Override // com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
    public void onMeasure(int r6, int r7) {
        super.onMeasure(r6, r7);
        int r62 = getMeasuredHeight() + (getMeasuredWidth() << 16);
        AnimatedTextView.AnimatedTextDrawable r72 = this.hintAnimatedDrawable;
        if (r72 == null) goto L5;
        r72.setBounds(getPaddingLeft(), getPaddingTop(), getMeasuredWidth() - getPaddingRight(), getMeasuredHeight() - getPaddingBottom());
    L5:
        AnimatedTextView.AnimatedTextDrawable r73 = this.hintAnimatedDrawable2;
        if (r73 == null) goto L8;
        r73.setBounds(getPaddingLeft(), getPaddingTop(), getMeasuredWidth() - getPaddingRight(), getMeasuredHeight() - getPaddingBottom());
    L8:
        StaticLayout r74 = this.hintLayout;
        if (r74 != null) goto L11;
    L19:
        this.lineY = getMeasuredHeight() - AndroidUtilities.dp(2.0f);
    L20:
        this.lastSize = r62;
        return;
    L11:
        if (this.hintAnimatedDrawable != null) goto L19;
        if (this.lastSize == r62) goto L16;
        setHintText(this.hint, false, r74.getPaint());
    L16:
        if (this.hintLayoutYFix == false) goto L18;
        this.lineY = (((getExtendedPaddingTop() + getPaddingTop()) + ((((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - this.hintLayout.getHeight()) / 2.0f)) + this.hintLayout.getHeight()) - AndroidUtilities.dp(1.0f);
        goto L20
    L18:
        this.lineY = (((getMeasuredHeight() - this.hintLayout.getHeight()) / 2.0f) + this.hintLayout.getHeight()) + AndroidUtilities.dp(6.0f);
        goto L20
    }

    @Override // android.widget.TextView, android.view.View
    public void onScrollChanged(int r1, int r2, int r3, int r4) {
        super.onScrollChanged(r1, r2, r3, r4);
        if (r1 == r3) goto L6;
        getParent().requestDisallowInterceptTouchEvent(true);
        return;
    }

    @Override // org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView
    public void onSelectionChanged(int r1, int r2) {
        super.onSelectionChanged(r1, r2);
        stopFling();
    }

    @Override // org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView
    public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        super.onTextChanged(r1, r2, r3, r4);
        stopFling();
        if (this.transformHintToHeader == true) goto L5;
        return;
    L5:
        if (this.transformHintToHeaderOnFocus == true) goto L9;
        checkHeaderVisibility(true);
        return;
    }

    @Override // com.exteragram.messenger.components.ReceiveContentEditText, android.widget.EditText, android.widget.TextView
    public boolean onTextContextMenuItem(int r2) {
        if (r2 != R.id.menu_markdown) goto L9;
        ExteraConfig.setDisableMarkdown(!ExteraConfig.getDisableMarkdown());
        FloatingActionMode r1 = this.floatingActionMode;
        if (r1 == null) goto L7;
        r1.finish();
    L7:
        return true;
    L9:
        return super.onTextContextMenuItem(r2);
    }

    @Override // com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent r15) {
        int r0 = r15.getActionMasked();
        if (r0 != 0) goto L9;
        this.lastTouchX = (int) r15.getX();
        stopFling();
        this.flingBlocked = false;
        this.flingDownScrollY = getScrollY();
        this.flingDownSelectionStart = getSelectionStart();
        this.flingDownSelectionEnd = getSelectionEnd();
        VelocityTracker r2 = this.flingVelocityTracker;
        if (r2 != null) goto L7;
        this.flingVelocityTracker = VelocityTracker.obtain();
    L11:
        VelocityTracker r22 = this.flingVelocityTracker;
        if (r22 == null) goto L15;
        r22.addMovement(r15);
    L15:
        if (r0 == 1) goto L17;
    L28:
        float r4 = 0.0f;
    L29:
        boolean r152 = super.onTouchEvent(r15);
        if (r0 != 1) goto L32;
    L33:
        VelocityTracker r02 = this.flingVelocityTracker;
        if (r02 == null) goto L37;
        r02.recycle();
        this.flingVelocityTracker = null;
    L37:
        if (r4 != 0.0f) goto L39;
    L42:
        return r152;
    L39:
        if (this.flingScroller != null) goto L41;
        this.flingScroller = new OverScroller(getContext());
    L41:
        this.flingScroller.fling(0, getScrollY(), 0, (int) r4, 0, 0, 0, getMaxFlingScrollY());
        this.flinging = true;
        postInvalidateOnAnimation();
        goto L42
    L32:
        if (r0 != 3) goto L42;
    L17:
        if (this.flingVelocityTracker == null) goto L28;
        if (this.flingBlocked == true) goto L28;
        if (getScrollY() == this.flingDownScrollY) goto L28;
        if (getSelectionStart() != this.flingDownSelectionStart) goto L28;
        if (getSelectionEnd() != this.flingDownSelectionEnd) goto L28;
        ViewConfiguration r3 = ViewConfiguration.get(getContext());
        this.flingVelocityTracker.computeCurrentVelocity(1000, r3.getScaledMaximumFlingVelocity());
        r4 = -this.flingVelocityTracker.getYVelocity();
        if (Math.abs(r4) >= r3.getScaledMinimumFlingVelocity()) goto L29;
    L7:
        r2.clear();
        goto L11
    L9:
        if (r0 != 5) goto L11;
        this.flingBlocked = true;
        goto L11
    }

    @Override // android.widget.TextView, android.view.View
    public boolean performLongClick() {
        this.flingBlocked = true;
        return super.performLongClick();
    }

    @Override // android.view.View
    public boolean postDelayed(Runnable r3, long r4) {
        if (Build.VERSION.SDK_INT < 29) goto L14;
        if (r3 == null) goto L14;
        if (r4 != 500) goto L14;
        if (BLINK_CLASS.equals(r3.getClass().getName()) == false) goto L14;
        if (Looper.myLooper() != Looper.getMainLooper()) goto L14;
        Choreographer60FpsContent.getInstance().addFrameCallbackOnce(r3, 2);
        return true;
    L14:
        return super.postDelayed(r3, r4);
    }

    @Override // android.view.View
    public boolean removeCallbacks(Runnable r3) {
        if (Looper.myLooper() != Looper.getMainLooper()) goto L6;
        Choreographer60FpsContent.getInstance().removeFrameCallbackOnce(r3);
    L6:
        return super.removeCallbacks(r3);
    }

    @Override // android.widget.TextView
    public void removeTextChangedListener(TextWatcher r2) {
        this.registeredTextWatchers.remove(r2);
        if (this.isTextWatchersSuppressed == false) goto L5;
        return;
    L5:
        super.removeTextChangedListener(r2);
    }

    public void setAllowDrawCursor(boolean r1) {
        this.allowDrawCursor = r1;
        invalidate();
    }

    public void setBlurredBackgroundDrawableViewFactory(BlurredBackgroundDrawableViewFactory r1) {
        this.blurredBackgroundDrawableViewFactory = r1;
    }

    public void setCursorColor(int r2) {
        ShapeDrawable r0 = this.cursorDrawable;
        if (r0 == null) goto L5;
        r0.getPaint().setColor(r2);
    L5:
        GradientDrawable r02 = this.gradientDrawable;
        if (r02 == null) goto L8;
        r02.setColor(r2);
    L8:
        invalidate();
    }

    public void setCursorSize(int r1) {
        this.cursorSize = r1;
    }

    public void setCursorWidth(float r1) {
        this.cursorWidth = r1;
    }

    public void setEllipsizeByGradient(boolean r10) {
        this.ellipsizeByGradient = r10;
        if (r10 == false) goto L6;
        this.ellipsizeWidth = AndroidUtilities.dp(12.0f);
        this.ellipsizePaint = new Paint(1);
        LinearGradient r1 = new LinearGradient(0.0f, 0.0f, this.ellipsizeWidth, 0.0f, new int[]{-1, 16777215}, new float[]{0.4f, 1.0f}, Shader.TileMode.CLAMP);
        this.ellipsizeGradient = r1;
        this.ellipsizePaint.setShader(r1);
        this.ellipsizeMatrix = new Matrix();
        return;
    }

    public void setErrorLineColor(int r2) {
        this.errorLineColor = r2;
        this.errorPaint.setColor(r2);
        invalidate();
    }

    public void setErrorText(CharSequence r2) {
        if (TextUtils.equals(r2, this.errorText) == false) goto L5;
        return;
    L5:
        this.errorText = r2;
        requestLayout();
    }

    public void setForceCursorEnd(boolean r1) {
        this.forceCursorEnd = r1;
        invalidate();
    }

    public void setHandlesColor(int r3) {
        if (Build.VERSION.SDK_INT >= 29) goto L5;
        return;
    L5:
        if (XiaomiUtilities.isMIUI() == false) goto L8;
        return;
    L8:
        if (this.mHandlesColor == r3) goto L12;
        this.mHandlesColor = r3;
        this.mHandlesColorFilter = new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN);
        updateHandleDrawable(this.mTextSelectHandleLeft, false);
        updateHandleDrawable(this.mTextSelectHandleRight, false);
        updateHandleDrawable(this.mTextSelectHandle, false);
        return;
    }

    @Keep
    public void setHeaderAnimationProgress(float r1) {
        this.headerAnimationProgress = r1;
        invalidate();
    }

    public void setHeaderHintColor(int r1) {
        this.headerHintColor = r1;
        invalidate();
    }

    public void setHintColor(int r2) {
        this.hintColor = r2;
        AnimatedTextView.AnimatedTextDrawable r0 = this.hintAnimatedDrawable;
        if (r0 == null) goto L5;
        r0.setTextColor(r2);
    L5:
        AnimatedTextView.AnimatedTextDrawable r22 = this.hintAnimatedDrawable2;
        if (r22 == null) goto L8;
        r22.setTextColor(this.hintColor);
    L8:
        invalidate();
    }

    public void setHintRightOffset(int r2) {
        float r22 = r2;
        if (this.rightHintOffset != r22) goto L5;
        return;
    L5:
        this.rightHintOffset = r22;
        invalidate();
    }

    public void setHintText(CharSequence r3) {
        setHintText(r3, false, getPaint());
    }

    public void setHintText(CharSequence r2, boolean r3) {
        setHintText(r2, r3, getPaint());
    }

    public void setHintText(CharSequence r9, boolean r10, TextPaint r11) {
        AnimatedTextView.AnimatedTextDrawable r0 = this.hintAnimatedDrawable;
        if (r0 == null) goto L6;
        r0.setText(r9, !LocaleController.isRTL);
        return;
    L6:
        if (r9 != null) goto L9;
        r9 = _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        if (getMeasuredWidth() != 0) goto L11;
        r10 = false;
    L11:
        SubstringLayoutAnimator r02 = this.hintAnimator;
        if (r10 == false) goto L16;
        if (r02 != null) goto L15;
        this.hintAnimator = new SubstringLayoutAnimator(this);
    L15:
        this.hintAnimator.create(this.hintLayout, this.hint, r9, r11);
    L18:
        this.hint = r9;
        if (getMeasuredWidth() == 0) goto L25;
        r9 = TextUtils.ellipsize(r9, r11, getMeasuredWidth(), TextUtils.TruncateAt.END);
        StaticLayout r102 = this.hintLayout;
        if (r102 == null) goto L25;
        if (TextUtils.equals(r102.getText(), r9) == false) goto L25;
        return;
    L25:
        this.hintLayout = new StaticLayout(r9, r11, AndroidUtilities.dp(1000.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        invalidate();
        return;
    L16:
        if (r02 == null) goto L18;
        r02.cancel();
        goto L18
    }

    public void setHintText2(CharSequence r2, boolean r3) {
        AnimatedTextView.AnimatedTextDrawable r1 = this.hintAnimatedDrawable2;
        if (r1 != null) goto L5;
        return;
    L5:
        if (LocaleController.isRTL == true) goto L8;
        if (r3 == false) goto L8;
        boolean r32 = true;
    L9:
        r1.setText(r2, r32);
        return;
    L8:
        r32 = false;
        goto L9
    }

    public void setHintVisible(boolean r3, boolean r4) {
        if (this.hintVisible != r3) goto L5;
        return;
    L5:
        this.hintLastUpdateTime = System.currentTimeMillis();
        this.hintVisible = r3;
        if (r4 == true) goto L11;
        if (r3 == false) goto L9;
        float r32 = 1.0f;
    L10:
        this.hintAlpha = r32;
        goto L11
    L9:
        r32 = 0.0f;
    L11:
        invalidate();
    }

    public void setLineColors(int r5, int r6, int r7) {
        this.lineVisible = true;
        getContext().getResources().getDrawable(R.drawable.search_dark).getPadding(this.padding);
        Rect r0 = this.padding;
        setPadding(r0.left, r0.top, r0.right, r0.bottom);
        this.lineColor = r5;
        this.activeLineColor = r6;
        this.activeLinePaint.setColor(r6);
        this.errorLineColor = r7;
        this.errorPaint.setColor(r7);
        invalidate();
    }

    @Override // android.widget.TextView
    public void setLineSpacing(float r1, float r2) {
        super.setLineSpacing(r1, r2);
        this.lineSpacingExtra = r1;
    }

    public void setNextSetTextAnimated(boolean r1) {
        this.nextSetTextAnimated = r1;
    }

    public void setOnPremiumMenuLockClickListener(Runnable r1) {
        this.onPremiumMenuLockClickListener = r1;
    }

    @Override // android.widget.EditText
    public void setSelection(int r1) {
        super.setSelection(r1);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    @Override // android.widget.EditText
    public void setSelection(int r1, int r2) {
        super.setSelection(r1, r2);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    public void setShowDisableMarkdown(boolean r1) {
        this.showDisableMarkdown = r1;
    }

    public void setSupportRtlHint(boolean r1) {
        this.supportRtlHint = r1;
    }

    @Override // org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.EditText, android.widget.TextView
    public void setText(CharSequence r1, TextView.BufferType r2) {
        super.setText(r1, r2);
        checkHeaderVisibility(this.nextSetTextAnimated);
        this.nextSetTextAnimated = false;
    }

    @Override // android.widget.TextView
    public void setTextSelectHandle(Drawable r2) {
        Drawable r22 = updateHandleDrawable(r2, true);
        this.mTextSelectHandle = r22;
        super.setTextSelectHandle(r22);
    }

    @Override // android.widget.TextView
    public void setTextSelectHandleLeft(Drawable r2) {
        Drawable r22 = updateHandleDrawable(r2, true);
        this.mTextSelectHandleLeft = r22;
        super.setTextSelectHandleLeft(r22);
    }

    @Override // android.widget.TextView
    public void setTextSelectHandleRight(Drawable r2) {
        Drawable r22 = updateHandleDrawable(r2, true);
        this.mTextSelectHandleRight = r22;
        super.setTextSelectHandleRight(r22);
    }

    @Override // android.widget.TextView
    public void setTextSize(int r3, float r4) {
        AnimatedTextView.AnimatedTextDrawable r0 = this.hintAnimatedDrawable;
        if (r0 == null) goto L5;
        r0.setTextSize(AndroidUtilities.dp(r4));
    L5:
        AnimatedTextView.AnimatedTextDrawable r02 = this.hintAnimatedDrawable2;
        if (r02 == null) goto L8;
        r02.setTextSize(AndroidUtilities.dp(r4));
    L8:
        super.setTextSize(r3, r4);
    }

    public void setTextWatchersSuppressed(boolean r6, boolean r7) {
        if (this.isTextWatchersSuppressed == r6) goto L17;
        this.isTextWatchersSuppressed = r6;
        List<TextWatcher> r0 = this.registeredTextWatchers;
        if (r6 == false) goto L11;
        Iterator<TextWatcher> r62 = r0.iterator();
    L9:
        if (r62.hasNext() == false) goto L24;
        super.removeTextChangedListener(r62.next());
        goto L9
    L24:
        return;
    L11:
        Iterator<TextWatcher> r63 = r0.iterator();
    L13:
        if (r63.hasNext() == false) goto L25;
        TextWatcher r02 = r63.next();
        super.addTextChangedListener(r02);
        if (r7 == false) goto L13;
        r02.beforeTextChanged(_UrlKt.FRAGMENT_ENCODE_SET, 0, length(), length());
        r02.onTextChanged(getText(), 0, length(), length());
        r02.afterTextChanged(getText());
        goto L13
    L25:
        return;
    }

    public void setTransformHintToHeader(boolean r2) {
        if (this.transformHintToHeader == r2) goto L10;
        this.transformHintToHeader = r2;
        AnimatorSet r22 = this.headerTransformAnimation;
        if (r22 == null) goto L9;
        r22.cancel();
        this.headerTransformAnimation = null;
        return;
    L9:
        return;
    }

    public void setTransformHintToHeaderOnFocus(boolean r2) {
        if (this.transformHintToHeaderOnFocus != r2) goto L5;
        return;
    L5:
        this.transformHintToHeaderOnFocus = r2;
        checkHeaderVisibility(false);
    }

    public void setWindowView(View r1) {
        this.windowView = r1;
    }

    @Override // android.view.View
    public ActionMode startActionMode(ActionMode.Callback r8) {
        if (this.windowView == null) goto L5;
    L9:
        FloatingActionMode r0 = this.floatingActionMode;
        if (r0 == null) goto L12;
        r0.finish();
    L12:
        cleanupFloatingActionModeViews();
        Context r2 = getContext();
        View r02 = this.windowView;
        if (r02 == null) goto L15;
    L14:
        View r3 = r02;
        FloatingToolbar r1 = new FloatingToolbar(r2, r3, getActionModeStyle(), getResourcesProvider(), this.blurredBackgroundDrawableViewFactory);
        this.floatingToolbar = r1;
        r1.setOnPremiumLockClick(this.onPremiumMenuLockClickListener);
        this.floatingToolbar.setQuoteShowVisible(new EditTextBoldCursor$$ExternalSyntheticLambda3(this));
        this.floatingActionMode = new FloatingActionMode(getContext(), new ActionModeCallback2Wrapper(this, r8), this, this.floatingToolbar);
        this.floatingToolbarPreDrawListener = new EditTextBoldCursor$$ExternalSyntheticLambda4(this);
        FloatingActionMode r03 = this.floatingActionMode;
        r8.onCreateActionMode(r03, r03.getMenu());
        FloatingActionMode r82 = this.floatingActionMode;
        extendActionMode(r82, r82.getMenu());
        addMarkdownToggle(this.floatingActionMode.getMenu());
        this.floatingActionMode.invalidate();
        getViewTreeObserver().addOnPreDrawListener(this.floatingToolbarPreDrawListener);
        invalidate();
        return this.floatingActionMode;
    L15:
        r02 = this.attachedToWindow;
        goto L14
    L5:
        if (this.attachedToWindow != null) goto L9;
        return super.startActionMode(r8);
    }

    @Override // android.view.View
    public ActionMode startActionMode(ActionMode.Callback r2, int r3) {
        if (this.windowView != null) goto L10;
        if (this.attachedToWindow != null) goto L10;
        return super.startActionMode(r2, r3);
    L10:
        return startActionMode(r2);
    }

    public void useAnimatedTextDrawable() {
        AnonymousClass1 r0 = new AnonymousClass1(this);
        this.hintAnimatedDrawable = r0;
        r0.setEllipsizeByGradient(true);
        this.hintAnimatedDrawable.setTextColor(this.hintColor);
        this.hintAnimatedDrawable.setTextSize(getPaint().getTextSize());
        AnonymousClass2 r02 = new AnonymousClass2(this);
        this.hintAnimatedDrawable2 = r02;
        r02.setGravity(5);
        this.hintAnimatedDrawable2.setTextColor(this.hintColor);
        this.hintAnimatedDrawable2.setTextSize(getPaint().getTextSize());
    }
}
