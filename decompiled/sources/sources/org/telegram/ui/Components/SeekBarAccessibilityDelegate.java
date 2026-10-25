package org.telegram.ui.Components;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public abstract class SeekBarAccessibilityDelegate extends View.AccessibilityDelegate {
    private static final CharSequence SEEK_BAR_CLASS_NAME = null;
    private final Map<View, Runnable> accessibilityEventRunnables;
    private final View.OnAttachStateChangeListener onAttachStateChangeListener;

    /* renamed from: org.telegram.ui.Components.SeekBarAccessibilityDelegate$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ SeekBarAccessibilityDelegate this$0;

        public AnonymousClass1(SeekBarAccessibilityDelegate r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r2) {
            r2.removeCallbacks((Runnable) SeekBarAccessibilityDelegate.m12609$$Nest$fgetaccessibilityEventRunnables(this.this$0).remove(r2));
            r2.removeOnAttachStateChangeListener(this);
        }
    }

    /* renamed from: -$$Nest$fgetaccessibilityEventRunnables, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Map m12609$$Nest$fgetaccessibilityEventRunnables(SeekBarAccessibilityDelegate r0) {
        return r0.accessibilityEventRunnables;
    }

    static {
        SEEK_BAR_CLASS_NAME = android.widget.SeekBar.class.getName();
    }

    public SeekBarAccessibilityDelegate() {
        this.accessibilityEventRunnables = new HashMap(4);
        this.onAttachStateChangeListener = new AnonymousClass1(this);
    }

    public abstract boolean canScrollBackward(View r1);

    public abstract boolean canScrollForward(View r1);

    public abstract void doScroll(View r1, boolean r2);

    public CharSequence getContentDescription(View r1) {
        return null;
    }

    @Override // android.view.View.AccessibilityDelegate
    public void onInitializeAccessibilityEvent(View r1, AccessibilityEvent r2) {
        super.onInitializeAccessibilityEvent(r1, r2);
        r2.setClassName(SEEK_BAR_CLASS_NAME);
    }

    @Override // android.view.View.AccessibilityDelegate
    public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r1, r2);
        onInitializeAccessibilityNodeInfoInternal(r1, r2);
    }

    public void onInitializeAccessibilityNodeInfoInternal(View r3, AccessibilityNodeInfo r4) {
        r4.setClassName(SEEK_BAR_CLASS_NAME);
        CharSequence r0 = getContentDescription(r3);
        if (TextUtils.isEmpty(r0) == true) goto L6;
        r4.setText(r0);
    L6:
        if (canScrollBackward(r3) == false) goto L9;
        r4.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
    L9:
        if (canScrollForward(r3) == false) goto L12;
        r4.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
        return;
    }

    public final void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo r2) {
        onInitializeAccessibilityNodeInfoInternal(null, r2);
    }

    @Override // android.view.View.AccessibilityDelegate
    public boolean performAccessibilityAction(View r2, int r3, Bundle r4) {
        if (super.performAccessibilityAction(r2, r3, r4) == false) goto L7;
        return true;
    L7:
        return performAccessibilityActionInternal(r2, r3, r4);
    }

    public final boolean performAccessibilityActionInternal(int r2, Bundle r3) {
        return performAccessibilityActionInternal(null, r2, r3);
    }

    public boolean performAccessibilityActionInternal(View r3, int r4, Bundle r5) {
        boolean r0 = false;
        if (r4 == 4096) goto L8;
        if (r4 == 8192) goto L8;
        return false;
    L8:
        if (r4 != 8192) goto L10;
        r0 = true;
    L10:
        doScroll(r3, r0);
        return true;
    }
}
