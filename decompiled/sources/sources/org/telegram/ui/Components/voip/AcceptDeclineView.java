package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.widget.Button;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.voip.ImageWithWavesView;

/* loaded from: classes7.dex */
public class AcceptDeclineView extends View {
    private final ButtonBounce acceptBounce;
    private final Paint acceptCirclePaint;
    private final FabBackgroundDrawable acceptDrawable;
    private final StaticLayout acceptLayout;
    Rect acceptRect;
    private Drawable acceptVideoDrawable;
    private RLottieDrawable acceptVoiceDrawable;
    private AcceptDeclineAccessibilityNodeProvider accessibilityNodeProvider;
    private final ImageWithWavesView.AvatarWavesDrawable avatarWavesDrawable;
    float bigRadius;
    private int buttonWidth;
    private ValueAnimator callAnimator;
    private final Drawable callDrawable;
    private final Drawable cancelDrawable;
    boolean captured;
    private final ButtonBounce declineBounce;
    private final FabBackgroundDrawable declineDrawable;
    private final StaticLayout declineLayout;
    Rect declineRect;
    boolean expandBigRadius;
    boolean expandSmallRadius;
    private boolean isVideo;
    Animator leftAnimator;
    boolean leftDrag;
    float leftOffsetX;
    Paint linePaint;
    Listener listener;
    private final Paint maskPaint;
    float maxOffset;
    private final StaticLayout retryLayout;
    boolean retryMod;
    Animator rightAnimator;
    float rigthOffsetX;
    Drawable rippleDrawable;
    float smallRadius;
    boolean startDrag;
    float startX;
    float startY;
    float touchSlop;

    /* renamed from: org.telegram.ui.Components.voip.AcceptDeclineView$1, reason: invalid class name */
    public class AnonymousClass1 extends AcceptDeclineAccessibilityNodeProvider {
        private final int[] coords;
        final /* synthetic */ AcceptDeclineView this$0;

        public AnonymousClass1(AcceptDeclineView r1, View r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
            this.coords = new int[]{0, 0};
        }

        @Override // org.telegram.ui.Components.voip.AcceptDeclineView.AcceptDeclineAccessibilityNodeProvider
        public void getVirtualViewBoundsInParent(int r2, Rect r3) {
            if (r2 != 0) goto L6;
            r3.set(this.this$0.acceptRect);
            return;
        L6:
            if (r2 != 1) goto L9;
            r3.set(this.this$0.declineRect);
            return;
        L9:
            r3.setEmpty();
        }

        @Override // org.telegram.ui.Components.voip.AcceptDeclineView.AcceptDeclineAccessibilityNodeProvider
        public void getVirtualViewBoundsInScreen(int r2, Rect r3) {
            getVirtualViewBoundsInParent(r2, r3);
            this.this$0.getLocationOnScreen(this.coords);
            int[] r1 = this.coords;
            r3.offset(r1[0], r1[1]);
        }

        @Override // org.telegram.ui.Components.voip.AcceptDeclineView.AcceptDeclineAccessibilityNodeProvider
        public CharSequence getVirtualViewText(int r2) {
            if (r2 != 0) goto L14;
            AcceptDeclineView r22 = this.this$0;
            if (r22.retryMod == false) goto L10;
            if (AcceptDeclineView.m13809$$Nest$fgetretryLayout(r22) != null) goto L8;
            return null;
        L8:
            return AcceptDeclineView.m13809$$Nest$fgetretryLayout(this.this$0).getText();
        L10:
            if (AcceptDeclineView.m13807$$Nest$fgetacceptLayout(r22) != null) goto L12;
            return null;
        L12:
            return AcceptDeclineView.m13807$$Nest$fgetacceptLayout(this.this$0).getText();
        L14:
            if (r2 == 1) goto L16;
            return null;
        L16:
            if (AcceptDeclineView.m13808$$Nest$fgetdeclineLayout(this.this$0) != null) goto L18;
            return null;
        L18:
            return AcceptDeclineView.m13808$$Nest$fgetdeclineLayout(this.this$0).getText();
        }

        @Override // org.telegram.ui.Components.voip.AcceptDeclineView.AcceptDeclineAccessibilityNodeProvider
        public void onVirtualViewClick(int r2) {
            Listener r1 = this.this$0.listener;
            if (r1 == null) goto L11;
            if (r2 != 0) goto L8;
            r1.onAccept();
            return;
        L8:
            if (r2 != 1) goto L12;
            r1.onDecline();
            return;
        L12:
            return;
        }
    }

    public static abstract class AcceptDeclineAccessibilityNodeProvider extends AccessibilityNodeProvider {
        private final AccessibilityManager accessibilityManager;
        private int currentFocusedVirtualViewId;
        private final View hostView;
        private final Rect rect;
        private final int virtualViewsCount;

        public AcceptDeclineAccessibilityNodeProvider(View r2, int r3) {
            this.rect = new Rect();
            this.currentFocusedVirtualViewId = -1;
            this.hostView = r2;
            this.virtualViewsCount = r3;
            this.accessibilityManager = (AccessibilityManager) ContextCompat.getSystemService(r2.getContext(), AccessibilityManager.class);
        }

        private void sendAccessibilityEventForVirtualView(int r3, int r4) {
            if (this.accessibilityManager.isTouchExplorationEnabled() == false) goto L8;
            ViewParent r0 = this.hostView.getParent();
            if (r0 == null) goto L9;
            AccessibilityEvent r42 = AccessibilityEvent.obtain(r4);
            r42.setPackageName(this.hostView.getContext().getPackageName());
            r42.setSource(this.hostView, r3);
            r0.requestSendAccessibilityEvent(this.hostView, r42);
            return;
        L9:
            return;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int r3) {
            View r0 = this.hostView;
            if (r3 != (-1)) goto L9;
            AccessibilityNodeInfo r32 = AccessibilityNodeInfo.obtain(r0);
            r32.setPackageName(this.hostView.getContext().getPackageName());
            int r02 = 0;
        L6:
            if (r02 >= this.virtualViewsCount) goto L8;
            r32.addChild(this.hostView, r02);
            r02 = r02 + 1;
            goto L6
        L8:
            return r32;
        L9:
            AccessibilityNodeInfo r03 = AccessibilityNodeInfo.obtain(r0, r3);
            r03.setPackageName(this.hostView.getContext().getPackageName());
            r03.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
            r03.setText(getVirtualViewText(r3));
            r03.setClassName(Button.class.getName());
            r03.setImportantForAccessibility(true);
            r03.setVisibleToUser(true);
            r03.setClickable(true);
            r03.setEnabled(true);
            r03.setParent(this.hostView);
            getVirtualViewBoundsInScreen(r3, this.rect);
            r03.setBoundsInScreen(this.rect);
            return r03;
        }

        public abstract void getVirtualViewBoundsInParent(int r1, Rect r2);

        public abstract void getVirtualViewBoundsInScreen(int r1, Rect r2);

        public abstract CharSequence getVirtualViewText(int r1);

        public boolean onHoverEvent(MotionEvent r7) {
            int r0 = (int) r7.getX();
            int r1 = (int) r7.getY();
            if (r7.getAction() != 9) goto L5;
        L13:
            int r72 = 0;
        L15:
            if (r72 >= this.virtualViewsCount) goto L23;
            getVirtualViewBoundsInParent(r72, this.rect);
            if (this.rect.contains(r0, r1) == true) goto L19;
            r72 = r72 + 1;
            goto L15
        L19:
            if (r72 == this.currentFocusedVirtualViewId) goto L21;
            this.currentFocusedVirtualViewId = r72;
            sendAccessibilityEventForVirtualView(r72, 32768);
        L21:
            return true;
        L23:
            return false;
        L5:
            if (r7.getAction() == 7) goto L13;
            if (r7.getAction() != 10) goto L23;
            if (this.currentFocusedVirtualViewId == (-1)) goto L23;
            this.currentFocusedVirtualViewId = -1;
            return true;
        }

        public abstract void onVirtualViewClick(int r1);

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int r2, int r3, Bundle r4) {
            if (r2 != (-1)) goto L7;
            return this.hostView.performAccessibilityAction(r3, r4);
        L7:
            if (r3 != 64) goto L10;
            sendAccessibilityEventForVirtualView(r2, 32768);
            return false;
        L10:
            if (r3 != 16) goto L15;
            onVirtualViewClick(r2);
            return true;
        L15:
            return false;
        }
    }

    public interface Listener {
        void onAccept();

        void onDecline();
    }

    /* renamed from: $r8$lambda$Xuh_OXXObMevGSs05BIBImr-xSo, reason: not valid java name */
    public static /* synthetic */ void m13806$r8$lambda$Xuh_OXXObMevGSs05BIBImrxSo(AcceptDeclineView r0, ValueAnimator r1) {
        r0.lambda$onTouchEvent$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$aOEfzo5g7KDZ6sjWe7jOYSqHoWs(AcceptDeclineView r0, ValueAnimator r1) {
        r0.lambda$setRetryMod$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$eyX23Ogjddf6_2Yhv5T0SVzFD3I(AcceptDeclineView r0, ValueAnimator r1) {
        r0.lambda$onTouchEvent$0(r1);
    }

    /* renamed from: -$$Nest$fgetacceptLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StaticLayout m13807$$Nest$fgetacceptLayout(AcceptDeclineView r0) {
        return r0.acceptLayout;
    }

    /* renamed from: -$$Nest$fgetdeclineLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StaticLayout m13808$$Nest$fgetdeclineLayout(AcceptDeclineView r0) {
        return r0.declineLayout;
    }

    /* renamed from: -$$Nest$fgetretryLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StaticLayout m13809$$Nest$fgetretryLayout(AcceptDeclineView r0) {
        return r0.retryLayout;
    }

    public AcceptDeclineView(Context r18) {
        super(r18);
        Paint r2 = new Paint(1);
        this.acceptCirclePaint = r2;
        this.acceptBounce = new ButtonBounce(this);
        this.declineBounce = new ButtonBounce(this);
        this.expandSmallRadius = true;
        this.expandBigRadius = true;
        this.acceptRect = new Rect();
        this.declineRect = new Rect();
        this.linePaint = new Paint(1);
        Paint r4 = new Paint(1);
        this.maskPaint = r4;
        ImageWithWavesView.AvatarWavesDrawable r5 = new ImageWithWavesView.AvatarWavesDrawable(AndroidUtilities.dp(45.0f), AndroidUtilities.dp(50.0f), AndroidUtilities.dp(8.0f), 4);
        this.avatarWavesDrawable = r5;
        r5.muteToStatic = true;
        r5.muteToStaticProgress = 0.0f;
        r5.wavesEnter = 0.0f;
        r5.setAmplitude(0.0d);
        r4.setColor(-16777216);
        r4.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.touchSlop = ViewConfiguration.get(r18).getScaledTouchSlop();
        this.buttonWidth = AndroidUtilities.dp(60.0f);
        FabBackgroundDrawable r42 = new FabBackgroundDrawable();
        this.acceptDrawable = r42;
        r42.setColor(-12531895);
        FabBackgroundDrawable r6 = new FabBackgroundDrawable();
        this.declineDrawable = r6;
        r6.setColor(-1041108);
        int r7 = this.buttonWidth;
        r6.setBounds(0, 0, r7, r7);
        int r62 = this.buttonWidth;
        r42.setBounds(0, 0, r62, r62);
        TextPaint r11 = new TextPaint(1);
        r11.setTextSize(AndroidUtilities.dp(11.0f));
        r11.setColor(-1);
        String r10 = LocaleController.getString(R.string.AcceptCall);
        String r63 = LocaleController.getString(R.string.DeclineCall);
        String r72 = LocaleController.getString(R.string.RetryCall);
        int r12 = (int) r11.measureText(r10);
        Layout.Alignment r13 = Layout.Alignment.ALIGN_NORMAL;
        this.acceptLayout = new StaticLayout(r10, r11, r12, r13, 1.0f, 0.0f, false);
        this.declineLayout = new StaticLayout(r63, r11, (int) r11.measureText(r63), r13, 1.0f, 0.0f, false);
        this.retryLayout = new StaticLayout(r72, r11, (int) r11.measureText(r72), r13, 1.0f, 0.0f, false);
        this.callDrawable = ContextCompat.getDrawable(r18, R.drawable.calls_decline).mutate();
        Drawable r64 = ContextCompat.getDrawable(r18, R.drawable.ic_close_white).mutate();
        this.cancelDrawable = r64;
        r64.setColorFilter(new PorterDuffColorFilter(-16777216, PorterDuff.Mode.MULTIPLY));
        RLottieDrawable r102 = new RLottieDrawable(R.raw.call_accept, AndroidUtilities.dp(48.0f), AndroidUtilities.dp(48.0f), true, null);
        this.acceptVoiceDrawable = r102;
        r102.setAutoRepeat(1);
        this.acceptVoiceDrawable.setCustomEndFrame(90);
        this.acceptVoiceDrawable.setMasterParent(this);
        this.acceptVideoDrawable = ContextCompat.getDrawable(r18, R.drawable.calls_video).mutate();
        r2.setColor(-1);
        r2.setAlpha(20);
        Drawable r1 = Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(52.0f), 0, ColorUtils.setAlphaComponent(-1, 76));
        this.rippleDrawable = r1;
        r1.setCallback(this);
    }

    private /* synthetic */ void lambda$onTouchEvent$0(ValueAnimator r1) {
        this.leftOffsetX = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
        this.leftAnimator = null;
    }

    private /* synthetic */ void lambda$onTouchEvent$1(ValueAnimator r1) {
        this.rigthOffsetX = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
        this.rightAnimator = null;
    }

    private /* synthetic */ void lambda$setRetryMod$2(ValueAnimator r3) {
        this.avatarWavesDrawable.setAmplitude(((Integer) r3.getAnimatedValue()).intValue());
    }

    @Override // android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.rippleDrawable.setState(getDrawableState());
    }

    @Override // android.view.View
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        if (this.accessibilityNodeProvider != null) goto L6;
        this.accessibilityNodeProvider = new AnonymousClass1(this, this, 2);
    L6:
        return this.accessibilityNodeProvider;
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable r0 = this.rippleDrawable;
        if (r0 == null) goto L6;
        r0.jumpToCurrentState();
        return;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimations();
    }

    @Override // android.view.View
    public void onDraw(Canvas r15) {
        if (this.retryMod == true) goto L21;
        boolean r1 = this.expandSmallRadius;
        float r2 = this.smallRadius;
        if (r1 == false) goto L9;
        float r22 = r2 + (AndroidUtilities.dp(2.0f) * 0.04f);
        this.smallRadius = r22;
        if (r22 <= AndroidUtilities.dp(4.0f)) goto L12;
        this.smallRadius = AndroidUtilities.dp(4.0f);
        this.expandSmallRadius = false;
    L12:
        boolean r12 = this.expandBigRadius;
        float r23 = this.bigRadius;
        if (r12 == false) goto L17;
        float r24 = r23 + (AndroidUtilities.dp(4.0f) * 0.03f);
        this.bigRadius = r24;
        if (r24 <= AndroidUtilities.dp(10.0f)) goto L20;
        this.bigRadius = AndroidUtilities.dp(10.0f);
        this.expandBigRadius = false;
    L20:
        invalidate();
        goto L21
    L17:
        float r25 = r23 - (AndroidUtilities.dp(5.0f) * 0.03f);
        this.bigRadius = r25;
        if (r25 >= AndroidUtilities.dp(5.0f)) goto L20;
        this.bigRadius = AndroidUtilities.dp(5.0f);
        this.expandBigRadius = true;
        goto L20
    L9:
        float r26 = r2 - (AndroidUtilities.dp(2.0f) * 0.04f);
        this.smallRadius = r26;
        if (r26 >= 0.0f) goto L12;
        this.smallRadius = 0.0f;
        this.expandSmallRadius = true;
    L21:
        this.bigRadius += AndroidUtilities.dp(8.0f) * 0.005f;
        this.declineRect.set((getMeasuredWidth() - AndroidUtilities.dp(46.0f)) - this.buttonWidth, AndroidUtilities.dp(40.0f), getMeasuredWidth() - AndroidUtilities.dp(46.0f), AndroidUtilities.dp(40.0f) + this.buttonWidth);
        r15.save();
        r15.translate(0.0f, AndroidUtilities.dp(40.0f));
        r15.save();
        float r13 = this.declineBounce.getScale(0.1f);
        r15.scale(r13, r13, this.declineRect.centerX(), this.declineRect.top + (this.buttonWidth / 2.0f));
        r15.translate(((this.rigthOffsetX + getMeasuredWidth()) - AndroidUtilities.dp(46.0f)) - this.buttonWidth, 0.0f);
        if (this.retryMod == false) goto L29;
        r15.saveLayer(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.linePaint, 31);
        this.declineDrawable.draw(r15);
        Drawable r14 = this.cancelDrawable;
        if ((r14 instanceof BitmapDrawable) == false) goto L28;
        BitmapDrawable r16 = (BitmapDrawable) r14;
        if (r16.getBitmap() == null) goto L28;
        r15.drawBitmap(r16.getBitmap(), null, r16.getBounds(), this.maskPaint);
    L28:
        r15.restore();
    L30:
        r15.save();
        r15.translate((this.buttonWidth / 2.0f) - (this.declineLayout.getWidth() / 2.0f), this.buttonWidth + AndroidUtilities.dp(4.0f));
        this.declineLayout.draw(r15);
        r15.restore();
        if (this.leftDrag == false) goto L33;
        this.rippleDrawable.setBounds(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.buttonWidth - AndroidUtilities.dp(4.0f), this.buttonWidth - AndroidUtilities.dp(4.0f));
        this.rippleDrawable.draw(r15);
    L33:
        r15.restore();
        this.acceptRect.set(AndroidUtilities.dp(46.0f), AndroidUtilities.dp(40.0f), AndroidUtilities.dp(46.0f) + this.buttonWidth, AndroidUtilities.dp(40.0f) + this.buttonWidth);
        r15.save();
        float r17 = this.acceptBounce.getScale(0.1f);
        r15.scale(r17, r17, this.acceptRect.centerX(), this.acceptRect.top + (this.buttonWidth / 2.0f));
        r15.translate(this.leftOffsetX + AndroidUtilities.dp(46.0f), 0.0f);
        if (this.retryMod == true) goto L36;
        this.avatarWavesDrawable.update();
        float r18 = (int) (this.buttonWidth / 2.0f);
        this.avatarWavesDrawable.draw(r15, r18, r18, this);
    L36:
        this.acceptDrawable.draw(r15);
        if (this.retryMod == false) goto L39;
        r15.save();
        r15.translate((this.buttonWidth / 2.0f) - (this.retryLayout.getWidth() / 2.0f), this.buttonWidth + AndroidUtilities.dp(4.0f));
        this.retryLayout.draw(r15);
        r15.restore();
    L40:
        r15.save();
        r15.translate(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f));
        if (this.isVideo == false) goto L43;
        int r19 = AndroidUtilities.dp(28.0f);
        r15.translate((AndroidUtilities.dp(48.0f) - r19) / 2.0f, (AndroidUtilities.dp(48.0f) - r19) / 2.0f);
        this.acceptVideoDrawable.setBounds(0, 0, r19, r19);
        this.acceptVideoDrawable.draw(r15);
    L44:
        r15.restore();
        if (this.leftDrag == true) goto L47;
        this.rippleDrawable.setBounds(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.buttonWidth - AndroidUtilities.dp(4.0f), this.buttonWidth - AndroidUtilities.dp(4.0f));
        this.rippleDrawable.draw(r15);
    L47:
        r15.restore();
        r15.restore();
        if (this.captured == false) goto L51;
        invalidate();
        return;
    L51:
        return;
    L43:
        this.acceptVoiceDrawable.draw(r15);
        goto L44
    L39:
        r15.save();
        r15.translate((this.buttonWidth / 2.0f) - (this.acceptLayout.getWidth() / 2.0f), this.buttonWidth + AndroidUtilities.dp(4.0f));
        this.acceptLayout.draw(r15);
        r15.restore();
        goto L40
    L29:
        this.declineDrawable.draw(r15);
        this.callDrawable.draw(r15);
        goto L30
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent r2) {
        AcceptDeclineAccessibilityNodeProvider r0 = this.accessibilityNodeProvider;
        if (r0 == null) goto L9;
        if (r0.onHoverEvent(r2) == false) goto L9;
        return true;
    L9:
        return super.onHoverEvent(r2);
    }

    @Override // android.view.View
    public void onMeasure(int r4, int r5) {
        super.onMeasure(r4, r5);
        this.maxOffset = (getMeasuredWidth() / 2.0f) - ((this.buttonWidth / 2.0f) + AndroidUtilities.dp(46.0f));
        int r42 = (this.buttonWidth - AndroidUtilities.dp(28.0f)) / 2;
        this.callDrawable.setBounds(r42, r42, AndroidUtilities.dp(28.0f) + r42, AndroidUtilities.dp(28.0f) + r42);
        this.cancelDrawable.setBounds(r42, r42, AndroidUtilities.dp(28.0f) + r42, AndroidUtilities.dp(28.0f) + r42);
        this.linePaint.setStrokeWidth(AndroidUtilities.dp(3.0f));
        this.linePaint.setColor(-1);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r7) {
        if (isEnabled() == true) goto L5;
        return false;
    L5:
        int r0 = r7.getAction();
        if (r0 != 0) goto L8;
        this.startX = r7.getX();
        this.startY = r7.getY();
        if (this.leftAnimator != null) goto L50;
        if (this.declineRect.contains((int) r7.getX(), (int) r7.getY()) == false) goto L50;
        int r72 = AndroidUtilities.dp(52.0f);
        if (this.retryMod == false) goto L46;
        int r02 = Theme.getColor(Theme.key_listSelector);
    L47:
        this.rippleDrawable = Theme.createSimpleSelectorCircleDrawable(r72, 0, r02);
        this.captured = true;
        this.leftDrag = true;
        this.declineBounce.setPressed(true);
        this.acceptBounce.setPressed(false);
        setPressed(true);
        invalidate();
        return true;
    L46:
        r02 = -51130;
    L50:
        if (this.rightAnimator == null) goto L52;
    L58:
        return false;
    L52:
        if (this.acceptRect.contains((int) r7.getX(), (int) r7.getY()) == false) goto L58;
        this.rippleDrawable = Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(52.0f), 0, -11677354);
        this.captured = true;
        this.leftDrag = false;
        this.declineBounce.setPressed(false);
        this.acceptBounce.setPressed(true);
        setPressed(true);
        Animator r73 = this.rightAnimator;
        if (r73 == null) goto L56;
        r73.cancel();
    L56:
        invalidate();
        return true;
    L8:
        if (r0 == 1) goto L16;
        if (r0 == 2) goto L14;
        if (r0 == 3) goto L16;
    L14:
        if (this.captured == false) goto L58;
        return true;
    L16:
        float r74 = r7.getY() - this.startY;
        if (this.captured == true) goto L19;
    L38:
        getParent().requestDisallowInterceptTouchEvent(false);
        this.captured = false;
        this.startDrag = false;
        this.declineBounce.setPressed(false);
        this.acceptBounce.setPressed(false);
        setPressed(false);
        goto L58
    L19:
        if (this.leftDrag == false) goto L29;
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{this.leftOffsetX, 0.0f});
        r03.addUpdateListener(new AcceptDeclineView$$ExternalSyntheticLambda0(this));
        r03.start();
        this.leftAnimator = r03;
        if (this.listener == null) goto L38;
        if (this.startDrag == true) goto L27;
        if (Math.abs(r74) >= this.touchSlop) goto L27;
    L28:
        this.listener.onDecline();
    L27:
        if (this.leftOffsetX <= (this.maxOffset * 0.8f)) goto L38;
    L29:
        ValueAnimator r04 = ValueAnimator.ofFloat(new float[]{this.rigthOffsetX, 0.0f});
        r04.addUpdateListener(new AcceptDeclineView$$ExternalSyntheticLambda1(this));
        r04.start();
        this.rightAnimator = r04;
        if (this.listener == null) goto L38;
        if (this.startDrag == true) goto L36;
        if (Math.abs(r74) >= this.touchSlop) goto L36;
    L37:
        this.listener.onAccept();
    L36:
        if ((-this.rigthOffsetX) <= (this.maxOffset * 0.8f)) goto L38;
        goto L37
    }

    public void setListener(Listener r1) {
        this.listener = r1;
    }

    public void setRetryMod(boolean r5) {
        this.retryMod = r5;
        if (r5 == false) goto L6;
        this.declineDrawable.setColor(-1);
        return;
    L6:
        this.acceptVoiceDrawable.start();
        this.avatarWavesDrawable.setShowWaves(true, this);
        this.declineDrawable.setColor(-1041108);
        ValueAnimator r52 = ValueAnimator.ofInt(new int[]{0, 60, 0, 0, 60, 0, 0, 0, 0});
        this.callAnimator = r52;
        r52.addUpdateListener(new AcceptDeclineView$$ExternalSyntheticLambda2(this));
        this.callAnimator.setDuration(1500);
        this.callAnimator.setRepeatMode(1);
        this.callAnimator.setRepeatCount(-1);
        this.callAnimator.start();
    }

    public void stopAnimations() {
        ValueAnimator r0 = this.callAnimator;
        if (r0 == null) goto L6;
        r0.cancel();
        this.callAnimator = null;
        this.acceptVoiceDrawable.stop();
        return;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (this.rippleDrawable != r2) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
