package org.telegram.messenger;

import android.view.Window;
import java.util.HashMap;

/* loaded from: classes.dex */
public class FlagSecureReason {
    private static HashMap<Window, Integer> currentSecureReasons;
    private boolean attached;
    private final FlagSecureCondition condition;
    private boolean value;
    private final Window window;

    public interface FlagSecureCondition {
        boolean run();
    }

    public FlagSecureReason(Window r2, FlagSecureCondition r3) {
        this.attached = false;
        this.value = false;
        this.window = r2;
        this.condition = r3;
    }

    public static boolean isSecuredNow(Window r1) {
        HashMap<Window, Integer> r0 = currentSecureReasons;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.get(r1) == null) goto L10;
        return true;
    L10:
        return false;
    }

    private void update(int r3) {
        if (currentSecureReasons != null) goto L5;
        currentSecureReasons = new HashMap();
    L5:
        Integer r0 = currentSecureReasons.get(this.window);
        if (r0 != null) goto L8;
        int r02 = 0;
    L9:
        int r32 = Math.max(0, r02 + r3);
        Window r03 = this.window;
        if (r32 > 0) goto L12;
        currentSecureReasons.remove(r03);
    L13:
        updateWindowSecure(this.window);
        return;
    L12:
        currentSecureReasons.put(r03, Integer.valueOf(r32));
        goto L13
    L8:
        r02 = r0.intValue();
        goto L9
    }

    private static void updateWindowSecure(Window r2) {
        if (r2 != null) goto L5;
        return;
    L5:
        if (isSecuredNow(r2) == false) goto L8;
        r2.addFlags(8192);
        AndroidUtilities.logFlagSecure();
        return;
    L8:
        r2.clearFlags(8192);
        AndroidUtilities.logFlagSecure();
    }

    public void attach() {
        if (this.attached == false) goto L5;
        return;
    L5:
        this.attached = true;
        invalidate();
    }

    public void detach() {
        if (this.attached == true) goto L5;
        return;
    L5:
        this.attached = false;
        invalidate();
    }

    public void invalidate() {
        int r1 = 1;
        if (this.attached == false) goto L9;
        FlagSecureCondition r0 = this.condition;
        if (r0 == null) goto L9;
        if (r0.run() == false) goto L9;
        boolean r02 = true;
    L11:
        if (r02 == this.value) goto L18;
        this.value = r02;
        if (r02 == true) goto L16;
        r1 = -1;
    L16:
        update(r1);
        return;
    L18:
        return;
    L9:
        r02 = false;
        goto L11
    }
}
