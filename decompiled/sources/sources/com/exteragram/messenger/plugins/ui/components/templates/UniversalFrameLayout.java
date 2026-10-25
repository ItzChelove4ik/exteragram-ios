package com.exteragram.messenger.plugins.ui.components.templates;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.Utilities;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0017\u0018\u00002\u00020\u0001:\u0001GB\u001d\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J0\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011H\u0002J\u0018\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0011H\u0002J\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u001aH\u0002J\b\u0010\u001d\u001a\u00020\rH\u0002J\b\u0010\u001e\u001a\u00020\rH\u0002J\u0010\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020\rH\u0002J\b\u0010#\u001a\u00020\rH\u0002J\u0010\u0010$\u001a\u00020\r2\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020'H\u0002J\u0010\u0010(\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020*H\u0002J \u0010-\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020!2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0002J\u0010\u00102\u001a\u00020\r2\u0006\u00103\u001a\u00020\u0011H\u0002J0\u00104\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011H\u0014J\u0018\u00105\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0011H\u0014J\u0010\u00106\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u00107\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u001aH\u0016J\b\u00108\u001a\u00020\rH\u0014J\b\u00109\u001a\u00020\rH\u0014J\u0010\u0010:\u001a\u00020\r2\u0006\u0010 \u001a\u00020!H\u0014J\b\u0010;\u001a\u00020\rH\u0016J\b\u0010<\u001a\u00020\rH\u0016J(\u0010<\u001a\u00020\r2\u0006\u0010=\u001a\u00020\u00112\u0006\u0010>\u001a\u00020\u00112\u0006\u0010?\u001a\u00020\u00112\u0006\u0010@\u001a\u00020\u0011H\u0017J\u0010\u0010A\u001a\u00020\r2\u0006\u0010 \u001a\u00020!H\u0014J\u0010\u0010B\u001a\u00020\r2\u0006\u0010&\u001a\u00020'H\u0016J\u0010\u0010C\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020*H\u0016J\u0010\u0010D\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020*H\u0017J \u0010E\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020!2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0014J\u0010\u0010F\u001a\u00020\r2\u0006\u00103\u001a\u00020\u0011H\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006H"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/templates/UniversalFrameLayout;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "universalFrameLayoutListener", "Lcom/exteragram/messenger/plugins/ui/components/templates/UniversalFrameLayout$UniversalFrameLayoutListener;", "<init>", "(Landroid/content/Context;Lcom/exteragram/messenger/plugins/ui/components/templates/UniversalFrameLayout$UniversalFrameLayoutListener;)V", "getUniversalFrameLayoutListener", "()Lcom/exteragram/messenger/plugins/ui/components/templates/UniversalFrameLayout$UniversalFrameLayoutListener;", "setUniversalFrameLayoutListener", "(Lcom/exteragram/messenger/plugins/ui/components/templates/UniversalFrameLayout$UniversalFrameLayoutListener;)V", "callSuperOnLayout", _UrlKt.FRAGMENT_ENCODE_SET, "changed", _UrlKt.FRAGMENT_ENCODE_SET, "left", _UrlKt.FRAGMENT_ENCODE_SET, "top", "right", "bottom", "callSuperOnMeasure", "widthMeasureSpec", "heightMeasureSpec", "callSuperSetTranslationX", "translationX", _UrlKt.FRAGMENT_ENCODE_SET, "callSuperSetTranslationY", "translationY", "callSuperOnDetachedFromWindow", "callSuperOnAttachedToWindow", "callSuperDispatchDraw", "canvas", "Landroid/graphics/Canvas;", "callSuperRequestLayout", "callSuperInvalidate", "callSuperOnDraw", "callSuperOnInitializeAccessibilityNodeInfo", "info", "Landroid/view/accessibility/AccessibilityNodeInfo;", "callSuperOnInterceptTouchEvent", "ev", "Landroid/view/MotionEvent;", "callSuperOnTouchEvent", "event", "callSuperDrawChild", "child", "Landroid/view/View;", "drawingTime", _UrlKt.FRAGMENT_ENCODE_SET, "callSuperSetVisibility", "visibility", "onLayout", "onMeasure", "setTranslationX", "setTranslationY", "onAttachedToWindow", "onDetachedFromWindow", "dispatchDraw", "requestLayout", "invalidate", "l", "t", "r", "b", "onDraw", "onInitializeAccessibilityNodeInfo", "onInterceptTouchEvent", "onTouchEvent", "drawChild", "setVisibility", "UniversalFrameLayoutListener", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class UniversalFrameLayout extends FrameLayout {
    private UniversalFrameLayoutListener universalFrameLayoutListener;

    @Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001JV\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072$\u0010\u000b\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\fH\u0016J,\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0010H\u0016J\u001e\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00130\u0014H\u0016J\u001e\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00132\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00130\u0014H\u0016J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0018H\u0016J\u001e\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0014H\u0016J\u0010\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0018H\u0016J\u0010\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0018H\u0016J0\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0018H\u0016J\u001e\u0010#\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0014H\u0016J\u001e\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020&0\u0014H\u0016J$\u0010'\u001a\u00020\u00052\u0006\u0010(\u001a\u00020)2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00050*H\u0016J$\u0010+\u001a\u00020\u00052\u0006\u0010,\u001a\u00020)2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00050*H\u0016J@\u0010-\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u001e\u0010\u000b\u001a\u001a\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u000502H\u0016J\u001e\u00103\u001a\u00020\u00032\u0006\u00104\u001a\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014H\u0016¨\u00065À\u0006\u0003"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/templates/UniversalFrameLayout$UniversalFrameLayoutListener;", _UrlKt.FRAGMENT_ENCODE_SET, "onLayout", _UrlKt.FRAGMENT_ENCODE_SET, "changed", _UrlKt.FRAGMENT_ENCODE_SET, "left", _UrlKt.FRAGMENT_ENCODE_SET, "top", "right", "bottom", "originalMethod", "Lorg/telegram/messenger/Utilities$Callback5;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "Lorg/telegram/messenger/Utilities$Callback2;", "setTranslationX", "translationX", _UrlKt.FRAGMENT_ENCODE_SET, "Lorg/telegram/messenger/Utilities$Callback;", "setTranslationY", "translationY", "onAttachedToWindow", "Ljava/lang/Runnable;", "onDetachedFromWindow", "dispatchDraw", "canvas", "Landroid/graphics/Canvas;", "requestLayout", "invalidate", "l", "t", "r", "b", "onDraw", "onInitializeAccessibilityNodeInfo", "info", "Landroid/view/accessibility/AccessibilityNodeInfo;", "onInterceptTouchEvent", "ev", "Landroid/view/MotionEvent;", "Lorg/telegram/messenger/Utilities$CallbackReturn;", "onTouchEvent", "event", "drawChild", "child", "Landroid/view/View;", "drawingTime", _UrlKt.FRAGMENT_ENCODE_SET, "Lorg/telegram/messenger/Utilities$Callback3Return;", "setVisibility", "visibility", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface UniversalFrameLayoutListener {

        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class DefaultImpls {
            @Deprecated
            public static void dispatchDraw(UniversalFrameLayoutListener r0, Canvas r1, Utilities.Callback<Canvas> r2) {
                UniversalFrameLayoutListener.access$dispatchDraw$jd(r0, r1, r2);
            }

            @Deprecated
            public static boolean drawChild(UniversalFrameLayoutListener r0, Canvas r1, View r2, long r3, Utilities.Callback3Return<Canvas, View, Long, Boolean> r5) {
                return UniversalFrameLayoutListener.access$drawChild$jd(r0, r1, r2, r3, r5);
            }

            @Deprecated
            public static void invalidate(UniversalFrameLayoutListener r0, int r1, int r2, int r3, int r4, Runnable r5) {
                UniversalFrameLayoutListener.access$invalidate$jd(r0, r1, r2, r3, r4, r5);
            }

            @Deprecated
            public static void invalidate(UniversalFrameLayoutListener r0, Runnable r1) {
                UniversalFrameLayoutListener.access$invalidate$jd(r0, r1);
            }

            @Deprecated
            public static void onAttachedToWindow(UniversalFrameLayoutListener r0, Runnable r1) {
                UniversalFrameLayoutListener.access$onAttachedToWindow$jd(r0, r1);
            }

            @Deprecated
            public static void onDetachedFromWindow(UniversalFrameLayoutListener r0, Runnable r1) {
                UniversalFrameLayoutListener.access$onDetachedFromWindow$jd(r0, r1);
            }

            @Deprecated
            public static void onDraw(UniversalFrameLayoutListener r0, Canvas r1, Utilities.Callback<Canvas> r2) {
                UniversalFrameLayoutListener.access$onDraw$jd(r0, r1, r2);
            }

            @Deprecated
            public static void onInitializeAccessibilityNodeInfo(UniversalFrameLayoutListener r0, AccessibilityNodeInfo r1, Utilities.Callback<AccessibilityNodeInfo> r2) {
                UniversalFrameLayoutListener.access$onInitializeAccessibilityNodeInfo$jd(r0, r1, r2);
            }

            @Deprecated
            public static boolean onInterceptTouchEvent(UniversalFrameLayoutListener r0, MotionEvent r1, Utilities.CallbackReturn<MotionEvent, Boolean> r2) {
                return UniversalFrameLayoutListener.access$onInterceptTouchEvent$jd(r0, r1, r2);
            }

            @Deprecated
            public static void onLayout(UniversalFrameLayoutListener r0, boolean r1, int r2, int r3, int r4, int r5, Utilities.Callback5<Boolean, Integer, Integer, Integer, Integer> r6) {
                UniversalFrameLayoutListener.access$onLayout$jd(r0, r1, r2, r3, r4, r5, r6);
            }

            @Deprecated
            public static void onMeasure(UniversalFrameLayoutListener r0, int r1, int r2, Utilities.Callback2<Integer, Integer> r3) {
                UniversalFrameLayoutListener.access$onMeasure$jd(r0, r1, r2, r3);
            }

            @Deprecated
            public static boolean onTouchEvent(UniversalFrameLayoutListener r0, MotionEvent r1, Utilities.CallbackReturn<MotionEvent, Boolean> r2) {
                return UniversalFrameLayoutListener.access$onTouchEvent$jd(r0, r1, r2);
            }

            @Deprecated
            public static void requestLayout(UniversalFrameLayoutListener r0, Runnable r1) {
                UniversalFrameLayoutListener.access$requestLayout$jd(r0, r1);
            }

            @Deprecated
            public static void setTranslationX(UniversalFrameLayoutListener r0, float r1, Utilities.Callback<Float> r2) {
                UniversalFrameLayoutListener.access$setTranslationX$jd(r0, r1, r2);
            }

            @Deprecated
            public static void setTranslationY(UniversalFrameLayoutListener r0, float r1, Utilities.Callback<Float> r2) {
                UniversalFrameLayoutListener.access$setTranslationY$jd(r0, r1, r2);
            }

            @Deprecated
            public static void setVisibility(UniversalFrameLayoutListener r0, int r1, Utilities.Callback<Integer> r2) {
                UniversalFrameLayoutListener.access$setVisibility$jd(r0, r1, r2);
            }
        }

        static /* synthetic */ void access$dispatchDraw$jd(UniversalFrameLayoutListener r0, Canvas r1, Utilities.Callback r2) {
            super.dispatchDraw(r1, r2);
        }

        static /* synthetic */ boolean access$drawChild$jd(UniversalFrameLayoutListener r0, Canvas r1, View r2, long r3, Utilities.Callback3Return r5) {
            return super.drawChild(r1, r2, r3, r5);
        }

        static /* synthetic */ void access$invalidate$jd(UniversalFrameLayoutListener r0, int r1, int r2, int r3, int r4, Runnable r5) {
            super.invalidate(r1, r2, r3, r4, r5);
        }

        static /* synthetic */ void access$invalidate$jd(UniversalFrameLayoutListener r0, Runnable r1) {
            super.invalidate(r1);
        }

        static /* synthetic */ void access$onAttachedToWindow$jd(UniversalFrameLayoutListener r0, Runnable r1) {
            super.onAttachedToWindow(r1);
        }

        static /* synthetic */ void access$onDetachedFromWindow$jd(UniversalFrameLayoutListener r0, Runnable r1) {
            super.onDetachedFromWindow(r1);
        }

        static /* synthetic */ void access$onDraw$jd(UniversalFrameLayoutListener r0, Canvas r1, Utilities.Callback r2) {
            super.onDraw(r1, r2);
        }

        static /* synthetic */ void access$onInitializeAccessibilityNodeInfo$jd(UniversalFrameLayoutListener r0, AccessibilityNodeInfo r1, Utilities.Callback r2) {
            super.onInitializeAccessibilityNodeInfo(r1, r2);
        }

        static /* synthetic */ boolean access$onInterceptTouchEvent$jd(UniversalFrameLayoutListener r0, MotionEvent r1, Utilities.CallbackReturn r2) {
            return super.onInterceptTouchEvent(r1, r2);
        }

        static /* synthetic */ void access$onLayout$jd(UniversalFrameLayoutListener r0, boolean r1, int r2, int r3, int r4, int r5, Utilities.Callback5 r6) {
            super.onLayout(r1, r2, r3, r4, r5, r6);
        }

        static /* synthetic */ void access$onMeasure$jd(UniversalFrameLayoutListener r0, int r1, int r2, Utilities.Callback2 r3) {
            super.onMeasure(r1, r2, r3);
        }

        static /* synthetic */ boolean access$onTouchEvent$jd(UniversalFrameLayoutListener r0, MotionEvent r1, Utilities.CallbackReturn r2) {
            return super.onTouchEvent(r1, r2);
        }

        static /* synthetic */ void access$requestLayout$jd(UniversalFrameLayoutListener r0, Runnable r1) {
            super.requestLayout(r1);
        }

        static /* synthetic */ void access$setTranslationX$jd(UniversalFrameLayoutListener r0, float r1, Utilities.Callback r2) {
            super.setTranslationX(r1, r2);
        }

        static /* synthetic */ void access$setTranslationY$jd(UniversalFrameLayoutListener r0, float r1, Utilities.Callback r2) {
            super.setTranslationY(r1, r2);
        }

        static /* synthetic */ void access$setVisibility$jd(UniversalFrameLayoutListener r0, int r1, Utilities.Callback r2) {
            super.setVisibility(r1, r2);
        }

        default void dispatchDraw(Canvas r1, Utilities.Callback<Canvas> r2) {
            r2.run(r1);
        }

        default boolean drawChild(Canvas r1, View r2, long r3, Utilities.Callback3Return<Canvas, View, Long, Boolean> r5) {
            return r5.run(r1, r2, Long.valueOf(r3)).booleanValue();
        }

        default void invalidate(int r1, int r2, int r3, int r4, Runnable r5) {
            r5.run();
        }

        default void invalidate(Runnable r1) {
            r1.run();
        }

        default void onAttachedToWindow(Runnable r1) {
            r1.run();
        }

        default void onDetachedFromWindow(Runnable r1) {
            r1.run();
        }

        default void onDraw(Canvas r1, Utilities.Callback<Canvas> r2) {
            r2.run(r1);
        }

        default void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1, Utilities.Callback<AccessibilityNodeInfo> r2) {
            r2.run(r1);
        }

        default boolean onInterceptTouchEvent(MotionEvent r1, Utilities.CallbackReturn<MotionEvent, Boolean> r2) {
            return r2.run(r1).booleanValue();
        }

        default void onLayout(boolean r1, int r2, int r3, int r4, int r5, Utilities.Callback5<Boolean, Integer, Integer, Integer, Integer> r6) {
            r6.run(Boolean.valueOf(r1), Integer.valueOf(r2), Integer.valueOf(r3), Integer.valueOf(r4), Integer.valueOf(r5));
        }

        default void onMeasure(int r1, int r2, Utilities.Callback2<Integer, Integer> r3) {
            r3.run(Integer.valueOf(r1), Integer.valueOf(r2));
        }

        default boolean onTouchEvent(MotionEvent r1, Utilities.CallbackReturn<MotionEvent, Boolean> r2) {
            return r2.run(r1).booleanValue();
        }

        default void requestLayout(Runnable r1) {
            r1.run();
        }

        default void setTranslationX(float r1, Utilities.Callback<Float> r2) {
            r2.run(Float.valueOf(r1));
        }

        default void setTranslationY(float r1, Utilities.Callback<Float> r2) {
            r2.run(Float.valueOf(r1));
        }

        default void setVisibility(int r1, Utilities.Callback<Integer> r2) {
            r2.run(Integer.valueOf(r1));
        }
    }

    public static /* synthetic */ void $r8$lambda$6EZrhbw695mSUWnY8Yvnfa8VmiM(UniversalFrameLayout r0, float r1) {
        r0.callSuperSetTranslationY(r1);
    }

    /* renamed from: $r8$lambda$BENOy44nJ_CJaCn-RTM-2dvDnvY, reason: not valid java name */
    public static /* synthetic */ boolean m1615$r8$lambda$BENOy44nJ_CJaCnRTM2dvDnvY(UniversalFrameLayout r0, MotionEvent r1) {
        return r0.callSuperOnTouchEvent(r1);
    }

    /* renamed from: $r8$lambda$DWB-NjRtwrvczsDMU-KJOJLyt6Y, reason: not valid java name */
    public static /* synthetic */ boolean m1616$r8$lambda$DWBNjRtwrvczsDMUKJOJLyt6Y(UniversalFrameLayout r0, Canvas r1, View r2, long r3) {
        return r0.callSuperDrawChild(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$EpD_QfNsWDQ0jXO2vaCpAmC7-G4, reason: not valid java name */
    public static /* synthetic */ void m1617$r8$lambda$EpD_QfNsWDQ0jXO2vaCpAmC7G4(UniversalFrameLayout r0, boolean r1, int r2, int r3, int r4, int r5) {
        r0.callSuperOnLayout(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$JYzkoCwApGdUvO51bS8u_BGxSWE(UniversalFrameLayout r0, AccessibilityNodeInfo r1) {
        r0.callSuperOnInitializeAccessibilityNodeInfo(r1);
    }

    /* renamed from: $r8$lambda$MfJ-35vs8eaKDFgkI6LvkQ6TN40, reason: not valid java name */
    public static /* synthetic */ void m1618$r8$lambda$MfJ35vs8eaKDFgkI6LvkQ6TN40(UniversalFrameLayout r0) {
        r0.callSuperOnAttachedToWindow();
    }

    public static /* synthetic */ void $r8$lambda$WC5S9VercWkrWWtJzGYPcGshxkI(UniversalFrameLayout r0, float r1) {
        r0.callSuperSetTranslationX(r1);
    }

    public static /* synthetic */ void $r8$lambda$WYpNe4IvV3YTPqpq9sS3Szwj41o(UniversalFrameLayout r0, int r1, int r2) {
        r0.callSuperOnMeasure(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$eEtAFa1PI6WTo8HJwZdhyB0ElUI(UniversalFrameLayout r0, int r1) {
        r0.callSuperSetVisibility(r1);
    }

    public static /* synthetic */ void $r8$lambda$fBJt1J4L6j4O19Tx3fMvUKMorhA(UniversalFrameLayout r0) {
        r0.callSuperRequestLayout();
    }

    /* renamed from: $r8$lambda$jPEfZrpKRbZ7p3kXzpGT5IqU-5I, reason: not valid java name */
    public static /* synthetic */ void m1619$r8$lambda$jPEfZrpKRbZ7p3kXzpGT5IqU5I(UniversalFrameLayout r0, Canvas r1) {
        r0.callSuperOnDraw(r1);
    }

    public static /* synthetic */ void $r8$lambda$nEFV3QxwQd_Y7mMovhxAhT1bRJo(UniversalFrameLayout r0, Canvas r1) {
        r0.callSuperDispatchDraw(r1);
    }

    /* renamed from: $r8$lambda$tT63hvh2scXo-LhQvWGyw5u3Cos, reason: not valid java name */
    public static /* synthetic */ void m1620$r8$lambda$tT63hvh2scXoLhQvWGyw5u3Cos(UniversalFrameLayout r0) {
        r0.callSuperOnDetachedFromWindow();
    }

    public static /* synthetic */ boolean $r8$lambda$wBqDW9Z8fusjiqiVQjHC3kEj0LY(UniversalFrameLayout r0, MotionEvent r1) {
        return r0.callSuperOnInterceptTouchEvent(r1);
    }

    public static /* synthetic */ void $r8$lambda$z_UaO_XgJnUJ4pX75Ww5SLc6G2Q(UniversalFrameLayout r0) {
        r0.callSuperInvalidate();
    }

    @JvmOverloads
    public UniversalFrameLayout(Context r3) {
        this(r3, null, 2, null);
    }

    @JvmOverloads
    public UniversalFrameLayout(Context r1, UniversalFrameLayoutListener r2) {
        super(r1);
        this.universalFrameLayoutListener = r2;
    }

    public /* synthetic */ UniversalFrameLayout(Context r1, UniversalFrameLayoutListener r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = null;
    L5:
        this(r1, r2);
    }

    private final void callSuperDispatchDraw(Canvas r1) {
        super.dispatchDraw(r1);
    }

    private final boolean callSuperDrawChild(Canvas r1, View r2, long r3) {
        return super.drawChild(r1, r2, r3);
    }

    private final void callSuperInvalidate() {
        super.invalidate();
    }

    private final void callSuperOnAttachedToWindow() {
        super.onAttachedToWindow();
    }

    private final void callSuperOnDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private final void callSuperOnDraw(Canvas r1) {
        super.onDraw(r1);
    }

    private final void callSuperOnInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
        super.onInitializeAccessibilityNodeInfo(r1);
    }

    private final boolean callSuperOnInterceptTouchEvent(MotionEvent r1) {
        return super.onInterceptTouchEvent(r1);
    }

    private final void callSuperOnLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
    }

    private final void callSuperOnMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
    }

    private final boolean callSuperOnTouchEvent(MotionEvent r1) {
        return super.onTouchEvent(r1);
    }

    private final void callSuperRequestLayout() {
        super.requestLayout();
    }

    private final void callSuperSetTranslationX(float r1) {
        super.setTranslationX(r1);
    }

    private final void callSuperSetTranslationY(float r1) {
        super.setTranslationY(r1);
    }

    private final void callSuperSetVisibility(int r1) {
        super.setVisibility(r1);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r3) {
        UniversalFrameLayoutListener r0 = this.universalFrameLayoutListener;
        if (r0 == null) goto L6;
        r0.dispatchDraw(r3, new UniversalFrameLayout$$ExternalSyntheticLambda6(this));
        return;
    L6:
        super.dispatchDraw(r3);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r7, View r8, long r9) {
        UniversalFrameLayoutListener r0 = this.universalFrameLayoutListener;
        if (r0 == null) goto L7;
        return r0.drawChild(r7, r8, r9, new UniversalFrameLayout$$ExternalSyntheticLambda1(this));
    L7:
        return super.drawChild(r7, r8, r9);
    }

    public final UniversalFrameLayoutListener getUniversalFrameLayoutListener() {
        return this.universalFrameLayoutListener;
    }

    @Override // android.view.View
    public void invalidate() {
        UniversalFrameLayoutListener r0 = this.universalFrameLayoutListener;
        if (r0 == null) goto L6;
        r0.invalidate(new UniversalFrameLayout$$ExternalSyntheticLambda3(this));
        return;
    L6:
        super.invalidate();
    }

    @Override // android.view.View
    @Deprecated(message = "Deprecated in Java")
    public void invalidate(int r7, int r8, int r9, int r10) {
        UniversalFrameLayoutListener r0 = this.universalFrameLayoutListener;
        if (r0 == null) goto L6;
        r0.invalidate(r7, r8, r9, r10, new UniversalFrameLayout$$ExternalSyntheticLambda3(this));
        return;
    L6:
        super.invalidate(r7, r8, r9, r10);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        UniversalFrameLayoutListener r0 = this.universalFrameLayoutListener;
        if (r0 == null) goto L6;
        r0.onAttachedToWindow(new UniversalFrameLayout$$ExternalSyntheticLambda11(this));
        return;
    L6:
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        UniversalFrameLayoutListener r0 = this.universalFrameLayoutListener;
        if (r0 == null) goto L6;
        r0.onDetachedFromWindow(new UniversalFrameLayout$$ExternalSyntheticLambda5(this));
        return;
    L6:
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas r3) {
        UniversalFrameLayoutListener r0 = this.universalFrameLayoutListener;
        if (r0 == null) goto L6;
        r0.onDraw(r3, new UniversalFrameLayout$$ExternalSyntheticLambda8(this));
        return;
    L6:
        super.onDraw(r3);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
        UniversalFrameLayoutListener r0 = this.universalFrameLayoutListener;
        if (r0 == null) goto L6;
        r0.onInitializeAccessibilityNodeInfo(r3, new UniversalFrameLayout$$ExternalSyntheticLambda10(this));
        return;
    L6:
        super.onInitializeAccessibilityNodeInfo(r3);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r3) {
        UniversalFrameLayoutListener r0 = this.universalFrameLayoutListener;
        if (r0 == null) goto L7;
        return r0.onInterceptTouchEvent(r3, new UniversalFrameLayout$$ExternalSyntheticLambda4(this));
    L7:
        return super.onInterceptTouchEvent(r3);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r8, int r9, int r10, int r11, int r12) {
        UniversalFrameLayoutListener r0 = this.universalFrameLayoutListener;
        if (r0 == null) goto L6;
        r0.onLayout(r8, r9, r10, r11, r12, new UniversalFrameLayout$$ExternalSyntheticLambda9(this));
        return;
    L6:
        super.onLayout(r8, r9, r10, r11, r12);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        UniversalFrameLayoutListener r0 = this.universalFrameLayoutListener;
        if (r0 == null) goto L6;
        r0.onMeasure(r3, r4, new UniversalFrameLayout$$ExternalSyntheticLambda12(this));
        return;
    L6:
        super.onMeasure(r3, r4);
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent r3) {
        UniversalFrameLayoutListener r0 = this.universalFrameLayoutListener;
        if (r0 == null) goto L7;
        return r0.onTouchEvent(r3, new UniversalFrameLayout$$ExternalSyntheticLambda2(this));
    L7:
        return super.onTouchEvent(r3);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        UniversalFrameLayoutListener r0 = this.universalFrameLayoutListener;
        if (r0 == null) goto L6;
        r0.requestLayout(new UniversalFrameLayout$$ExternalSyntheticLambda13(this));
        return;
    L6:
        super.requestLayout();
    }

    @Override // android.view.View
    public void setTranslationX(float r3) {
        UniversalFrameLayoutListener r0 = this.universalFrameLayoutListener;
        if (r0 == null) goto L6;
        r0.setTranslationX(r3, new UniversalFrameLayout$$ExternalSyntheticLambda7(this));
        return;
    L6:
        super.setTranslationX(r3);
    }

    @Override // android.view.View
    public void setTranslationY(float r3) {
        UniversalFrameLayoutListener r0 = this.universalFrameLayoutListener;
        if (r0 == null) goto L6;
        r0.setTranslationY(r3, new UniversalFrameLayout$$ExternalSyntheticLambda0(this));
        return;
    L6:
        super.setTranslationY(r3);
    }

    public final void setUniversalFrameLayoutListener(UniversalFrameLayoutListener r1) {
        this.universalFrameLayoutListener = r1;
    }

    @Override // android.view.View
    public void setVisibility(int r3) {
        UniversalFrameLayoutListener r0 = this.universalFrameLayoutListener;
        if (r0 == null) goto L6;
        r0.setVisibility(r3, new UniversalFrameLayout$$ExternalSyntheticLambda14(this));
        return;
    L6:
        super.setVisibility(r3);
    }
}
