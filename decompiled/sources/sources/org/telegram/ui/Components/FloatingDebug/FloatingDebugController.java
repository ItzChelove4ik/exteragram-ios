package org.telegram.ui.Components.FloatingDebug;

import android.annotation.SuppressLint;
import android.widget.FrameLayout;
import org.telegram.messenger.SharedConfig;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes3.dex */
public abstract class FloatingDebugController {
    private static FloatingDebugView debugView;

    /* loaded from: classes7.dex */
    public static class DebugItem {
        Runnable action;
        AnimationProperties.FloatProperty floatProperty;
        float from;
        final CharSequence title;
        float to;
        final DebugItemType type;

        public DebugItem(CharSequence r2) {
            this.type = DebugItemType.HEADER;
            this.title = r2;
        }

        public DebugItem(CharSequence r2, float r3, float r4, AnimationProperties.FloatProperty r5) {
            this.type = DebugItemType.SEEKBAR;
            this.title = r2;
            this.from = r3;
            this.to = r4;
            this.floatProperty = r5;
        }

        public DebugItem(CharSequence r2, Runnable r3) {
            this.type = DebugItemType.SIMPLE;
            this.title = r2;
            this.action = r3;
        }
    }

    /* loaded from: classes7.dex */
    public enum DebugItemType extends Enum<DebugItemType> {
        private static final /* synthetic */ DebugItemType[] $VALUES = null;
        public static final DebugItemType HEADER = null;
        public static final DebugItemType SEEKBAR = null;
        public static final DebugItemType SIMPLE = null;

        private static /* synthetic */ DebugItemType[] $values() {
            return new DebugItemType[]{SIMPLE, HEADER, SEEKBAR};
        }

        static {
            SIMPLE = new DebugItemType("SIMPLE", 0);
            HEADER = new DebugItemType("HEADER", 1);
            SEEKBAR = new DebugItemType("SEEKBAR", 2);
            $VALUES = $values();
        }

        DebugItemType(String r1, int r2) {
        }

        public static DebugItemType valueOf(String r1) {
            return (DebugItemType) Enum.valueOf(DebugItemType.class, r1);
        }

        public static DebugItemType[] values() {
            return (DebugItemType[]) $VALUES.clone();
        }
    }

    /* renamed from: $r8$lambda$vjnbHU-wRPIFZGxpn6HtKtrH0fU, reason: not valid java name */
    public static /* synthetic */ void m10673$r8$lambda$vjnbHUwRPIFZGxpn6HtKtrH0fU(LaunchActivity r1) {
        r1.getMainContainerFrameLayout().removeView(debugView);
        debugView = null;
    }

    public static boolean isActive() {
        return SharedConfig.isFloatingDebugActive;
    }

    public static boolean onBackPressed(boolean r1) {
        FloatingDebugView r0 = debugView;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.onBackPressed(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static void onDestroy() {
        FloatingDebugView r0 = debugView;
        if (r0 == null) goto L5;
        r0.saveConfig();
    L5:
        debugView = null;
    }

    public static void setActive(LaunchActivity r1, boolean r2) {
        setActive(r1, r2, true);
    }

    @SuppressLint({"WrongConstant"})
    public static void setActive(LaunchActivity r3, boolean r4, boolean r5) {
        FloatingDebugView r0 = debugView;
        if (r0 == null) goto L5;
        boolean r1 = true;
    L6:
        if (r4 == r1) goto L15;
        if (r4 == false) goto L10;
        debugView = new FloatingDebugView(r3);
        r3.getMainContainerFrameLayout().addView(debugView, new FrameLayout.LayoutParams(-1, -1));
        debugView.showFab();
    L11:
        if (r5 == false) goto L14;
        SharedConfig.isFloatingDebugActive = r4;
        SharedConfig.saveConfig();
        return;
    L14:
        return;
    L10:
        r0.dismiss(new FloatingDebugController$$ExternalSyntheticLambda0(r3));
        goto L11
    L15:
        return;
    L5:
        r1 = false;
        goto L6
    }
}
