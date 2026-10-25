package org.telegram.ui.Components.Reactions;

import android.view.View;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.SharedConfig;

/* loaded from: classes3.dex */
public abstract class HwEmojis {
    private static boolean firstOpen;
    private static volatile boolean hwEnabled;
    private static final Set<View> hwViews = null;
    private static boolean isBeforePreparing;
    private static boolean isCascade;
    private static boolean isPreparing;
    private static Boolean isWeakDevice;
    private static Runnable task;

    static {
        hwViews = new HashSet();
        hwEnabled = false;
        firstOpen = true;
        isPreparing = false;
        isCascade = false;
        isBeforePreparing = false;
    }

    public static void beforePreparing() {
        ImageLoader.getInstance().getCacheOutQueue().pause();
        isBeforePreparing = true;
    }

    public static void disableHw() {
        ImageLoader.getInstance().getCacheOutQueue().resume();
        hwEnabled = false;
        isPreparing = false;
        isBeforePreparing = false;
        task = null;
        Iterator<View> r0 = hwViews.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().invalidate();
        goto L4
    L6:
        hwViews.clear();
    }

    public static void enableHw() {
        ImageLoader.getInstance().getCacheOutQueue().pause();
        hwEnabled = true;
        isPreparing = false;
        isBeforePreparing = false;
    }

    public static void exec() {
        Runnable r0 = task;
        if (r0 == null) goto L6;
        r0.run();
        task = null;
        return;
    }

    public static boolean grab(View r1) {
        if (hwEnabled == false) goto L6;
        hwViews.add(r1);
    L6:
        return hwEnabled;
    }

    public static boolean grabIfWeakDevice(View... r3) {
        if (isWeakDevice != null) goto L10;
        if (SharedConfig.getDevicePerformanceClass() == 2) goto L7;
        boolean r0 = true;
    L8:
        isWeakDevice = Boolean.valueOf(r0);
        goto L10
    L7:
        r0 = false;
    L10:
        if (isWeakDevice.booleanValue() == true) goto L13;
        return false;
    L13:
        if (hwEnabled == false) goto L16;
        hwViews.addAll(Arrays.asList(r3));
    L16:
        return hwEnabled;
    }

    public static boolean isCascade() {
        return isCascade;
    }

    public static boolean isFirstOpen() {
        return firstOpen;
    }

    public static boolean isHwEnabled() {
        return hwEnabled;
    }

    public static boolean isHwEnabledOrPreparing() {
        if (hwEnabled == false) goto L5;
        return true;
    L5:
        if (isPreparing == false) goto L7;
        return true;
    L7:
        if (isBeforePreparing == true) goto L14;
        return false;
    L14:
        return true;
    }

    public static boolean isPreparing() {
        return isPreparing;
    }

    public static void prepare(Runnable r1, boolean r2) {
        isCascade = r2;
        isPreparing = true;
        isBeforePreparing = false;
        if (firstOpen == false) goto L5;
        firstOpen = false;
    L5:
        task = r1;
    }
}
