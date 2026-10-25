package com.exteragram.messenger.plugins.ui.components.templates;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.Utilities;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001!B\u001d\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0018\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\b\u0010\u001c\u001a\u00020\rH\u0014J\u0010\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0017J\u0018\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0014J\b\u0010\u001f\u001a\u00020\rH\u0014J\u0010\u0010 \u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\""}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/templates/UniversalView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "delegate", "Lcom/exteragram/messenger/plugins/ui/components/templates/UniversalView$UniversalViewDelegate;", "<init>", "(Landroid/content/Context;Lcom/exteragram/messenger/plugins/ui/components/templates/UniversalView$UniversalViewDelegate;)V", "getDelegate", "()Lcom/exteragram/messenger/plugins/ui/components/templates/UniversalView$UniversalViewDelegate;", "setDelegate", "(Lcom/exteragram/messenger/plugins/ui/components/templates/UniversalView$UniversalViewDelegate;)V", "callSuperOnDraw", _UrlKt.FRAGMENT_ENCODE_SET, "canvas", "Landroid/graphics/Canvas;", "callSuperOnTouchEvent", _UrlKt.FRAGMENT_ENCODE_SET, "event", "Landroid/view/MotionEvent;", "callSuperOnMeasure", "widthMeasureSpec", _UrlKt.FRAGMENT_ENCODE_SET, "heightMeasureSpec", "callSuperOnInitializeAccessibilityNodeInfo", "info", "Landroid/view/accessibility/AccessibilityNodeInfo;", "onDraw", "onAttachedToWindow", "onTouchEvent", "onMeasure", "onDetachedFromWindow", "onInitializeAccessibilityNodeInfo", "UniversalViewDelegate", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class UniversalView extends View {
    private UniversalViewDelegate delegate;

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016J$\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000b0\u000eH\u0016J,\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u0013H\u0016J\u001e\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00160\u0007H\u0016¨\u0006\u0017À\u0006\u0003"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/templates/UniversalView$UniversalViewDelegate;", _UrlKt.FRAGMENT_ENCODE_SET, "onDraw", _UrlKt.FRAGMENT_ENCODE_SET, "canvas", "Landroid/graphics/Canvas;", "originalMethod", "Lorg/telegram/messenger/Utilities$Callback;", "onAttachedToWindow", "onDetachedFromWindow", "onTouchEvent", _UrlKt.FRAGMENT_ENCODE_SET, "event", "Landroid/view/MotionEvent;", "Lorg/telegram/messenger/Utilities$CallbackReturn;", "onMeasure", "widthMeasureSpec", _UrlKt.FRAGMENT_ENCODE_SET, "heightMeasureSpec", "Lorg/telegram/messenger/Utilities$Callback2;", "onInitializeAccessibilityNodeInfo", "info", "Landroid/view/accessibility/AccessibilityNodeInfo;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface UniversalViewDelegate {

        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class DefaultImpls {
            @Deprecated
            public static void onAttachedToWindow(UniversalViewDelegate r0) {
                UniversalViewDelegate.access$onAttachedToWindow$jd(r0);
            }

            @Deprecated
            public static void onDetachedFromWindow(UniversalViewDelegate r0) {
                UniversalViewDelegate.access$onDetachedFromWindow$jd(r0);
            }

            @Deprecated
            public static void onDraw(UniversalViewDelegate r0, Canvas r1, Utilities.Callback<Canvas> r2) {
                UniversalViewDelegate.access$onDraw$jd(r0, r1, r2);
            }

            @Deprecated
            public static void onInitializeAccessibilityNodeInfo(UniversalViewDelegate r0, AccessibilityNodeInfo r1, Utilities.Callback<AccessibilityNodeInfo> r2) {
                UniversalViewDelegate.access$onInitializeAccessibilityNodeInfo$jd(r0, r1, r2);
            }

            @Deprecated
            public static void onMeasure(UniversalViewDelegate r0, int r1, int r2, Utilities.Callback2<Integer, Integer> r3) {
                UniversalViewDelegate.access$onMeasure$jd(r0, r1, r2, r3);
            }

            @Deprecated
            public static boolean onTouchEvent(UniversalViewDelegate r0, MotionEvent r1, Utilities.CallbackReturn<MotionEvent, Boolean> r2) {
                return UniversalViewDelegate.access$onTouchEvent$jd(r0, r1, r2);
            }
        }

        static /* synthetic */ void access$onAttachedToWindow$jd(UniversalViewDelegate r0) {
            super.onAttachedToWindow();
        }

        static /* synthetic */ void access$onDetachedFromWindow$jd(UniversalViewDelegate r0) {
            super.onDetachedFromWindow();
        }

        static /* synthetic */ void access$onDraw$jd(UniversalViewDelegate r0, Canvas r1, Utilities.Callback r2) {
            super.onDraw(r1, r2);
        }

        static /* synthetic */ void access$onInitializeAccessibilityNodeInfo$jd(UniversalViewDelegate r0, AccessibilityNodeInfo r1, Utilities.Callback r2) {
            super.onInitializeAccessibilityNodeInfo(r1, r2);
        }

        static /* synthetic */ void access$onMeasure$jd(UniversalViewDelegate r0, int r1, int r2, Utilities.Callback2 r3) {
            super.onMeasure(r1, r2, r3);
        }

        static /* synthetic */ boolean access$onTouchEvent$jd(UniversalViewDelegate r0, MotionEvent r1, Utilities.CallbackReturn r2) {
            return super.onTouchEvent(r1, r2);
        }

        default void onAttachedToWindow() {
        }

        default void onDetachedFromWindow() {
        }

        default void onDraw(Canvas r1, Utilities.Callback<Canvas> r2) {
            r2.run(r1);
        }

        default void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1, Utilities.Callback<AccessibilityNodeInfo> r2) {
            r2.run(r1);
        }

        default void onMeasure(int r1, int r2, Utilities.Callback2<Integer, Integer> r3) {
            r3.run(Integer.valueOf(r1), Integer.valueOf(r2));
        }

        default boolean onTouchEvent(MotionEvent r1, Utilities.CallbackReturn<MotionEvent, Boolean> r2) {
            return r2.run(r1).booleanValue();
        }
    }

    public static /* synthetic */ void $r8$lambda$2kKiVr606s4ZPp8FFeiZbo24XoI(UniversalView r0, AccessibilityNodeInfo r1) {
        r0.callSuperOnInitializeAccessibilityNodeInfo(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$SsNSrOGRfTznAxrmS0uU5PZapLk(UniversalView r0, MotionEvent r1) {
        return r0.callSuperOnTouchEvent(r1);
    }

    /* renamed from: $r8$lambda$XO9FG0PO9UGOF4-gNo1hAiLT6K0, reason: not valid java name */
    public static /* synthetic */ void m1621$r8$lambda$XO9FG0PO9UGOF4gNo1hAiLT6K0(UniversalView r0, Canvas r1) {
        r0.callSuperOnDraw(r1);
    }

    public static /* synthetic */ void $r8$lambda$bA9BMC1bhgbp3FN0Ni9GrdXibSA(UniversalView r0, int r1, int r2) {
        r0.callSuperOnMeasure(r1, r2);
    }

    @JvmOverloads
    public UniversalView(Context r3) {
        this(r3, null, 2, null);
    }

    @JvmOverloads
    public UniversalView(Context r1, UniversalViewDelegate r2) {
        super(r1);
        this.delegate = r2;
    }

    public /* synthetic */ UniversalView(Context r1, UniversalViewDelegate r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = null;
    L5:
        this(r1, r2);
    }

    private final void callSuperOnDraw(Canvas r1) {
        super.onDraw(r1);
    }

    private final void callSuperOnInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
        super.onInitializeAccessibilityNodeInfo(r1);
    }

    private final void callSuperOnMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
    }

    private final boolean callSuperOnTouchEvent(MotionEvent r1) {
        return super.onTouchEvent(r1);
    }

    public final UniversalViewDelegate getDelegate() {
        return this.delegate;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        UniversalViewDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onAttachedToWindow();
        return;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        UniversalViewDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onDetachedFromWindow();
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r3) {
        UniversalViewDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onDraw(r3, new UniversalView$$ExternalSyntheticLambda0(this));
        return;
    L6:
        super.onDraw(r3);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
        UniversalViewDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onInitializeAccessibilityNodeInfo(r3, new UniversalView$$ExternalSyntheticLambda2(this));
        return;
    L6:
        super.onInitializeAccessibilityNodeInfo(r3);
    }

    @Override // android.view.View
    public void onMeasure(int r3, int r4) {
        UniversalViewDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onMeasure(r3, r4, new UniversalView$$ExternalSyntheticLambda1(this));
        return;
    L6:
        super.onMeasure(r3, r4);
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent r3) {
        UniversalViewDelegate r0 = this.delegate;
        if (r0 == null) goto L7;
        return r0.onTouchEvent(r3, new UniversalView$$ExternalSyntheticLambda3(this));
    L7:
        return super.onTouchEvent(r3);
    }

    public final void setDelegate(UniversalViewDelegate r1) {
        this.delegate = r1;
    }
}
