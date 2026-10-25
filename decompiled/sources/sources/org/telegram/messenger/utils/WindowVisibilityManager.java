package org.telegram.messenger.utils;

import android.view.View;
import android.view.Window;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class WindowVisibilityManager {
    private boolean isHidden;
    private final OnVisibilityChangedListener listener;
    private int reasonsToHide;

    public interface Controller {
        void destroy();

        void setHidden(boolean r1);
    }

    /* loaded from: classes6.dex */
    public class ControllerImpl implements Controller {
        private boolean destroyed;
        private boolean hidden;
        final /* synthetic */ WindowVisibilityManager this$0;

        private ControllerImpl(WindowVisibilityManager r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ ControllerImpl(WindowVisibilityManager r1, WindowVisibilityManagerIA r2) {
            this(r1);
        }

        @Override // org.telegram.messenger.utils.WindowVisibilityManager.Controller
        public void destroy() {
            setHidden(false);
            this.destroyed = true;
        }

        @Override // org.telegram.messenger.utils.WindowVisibilityManager.Controller
        public void setHidden(boolean r3) {
            if (this.hidden != r3) goto L5;
            return;
        L5:
            if (this.destroyed == true) goto L17;
            this.hidden = r3;
            WindowVisibilityManager r0 = this.this$0;
            boolean r1 = true;
            if (r3 == false) goto L9;
            WindowVisibilityManager.m5205$$Nest$fputreasonsToHide(r0, WindowVisibilityManager.m5204$$Nest$fgetreasonsToHide(r0) + 1);
        L10:
            WindowVisibilityManager r2 = this.this$0;
            if (WindowVisibilityManager.m5204$$Nest$fgetreasonsToHide(r2) > 0) goto L14;
            r1 = false;
        L14:
            WindowVisibilityManager.m5206$$Nest$msetIsHidden(r2, r1);
            return;
        L9:
            WindowVisibilityManager.m5205$$Nest$fputreasonsToHide(r0, WindowVisibilityManager.m5204$$Nest$fgetreasonsToHide(r0) - 1);
            goto L10
        }
    }

    /* loaded from: classes6.dex */
    public interface OnVisibilityChangedListener {
        void onVisibilityChanged(boolean r1);
    }

    public static /* synthetic */ void $r8$lambda$5BlAw3AoEPCLn11rm_5hye_Xvw0(WeakReference r0, boolean r1) {
        Window r02 = (Window) r0.get();
        if (r02 == null) goto L10;
        View r03 = r02.getDecorView();
        if (r1 == false) goto L7;
        int r12 = 0;
    L8:
        r03.setVisibility(r12);
        return;
    L7:
        r12 = 8;
        goto L8
    }

    /* renamed from: -$$Nest$fgetreasonsToHide, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5204$$Nest$fgetreasonsToHide(WindowVisibilityManager r0) {
        return r0.reasonsToHide;
    }

    /* renamed from: -$$Nest$fputreasonsToHide, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5205$$Nest$fputreasonsToHide(WindowVisibilityManager r0, int r1) {
        r0.reasonsToHide = r1;
    }

    /* renamed from: -$$Nest$msetIsHidden, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5206$$Nest$msetIsHidden(WindowVisibilityManager r0, boolean r1) {
        r0.setIsHidden(r1);
    }

    public WindowVisibilityManager(Window r2) {
        this.listener = new WindowVisibilityManager$$ExternalSyntheticLambda0(new WeakReference(r2));
    }

    private void setIsHidden(boolean r2) {
        if (this.isHidden == r2) goto L6;
        this.isHidden = r2;
        this.listener.onVisibilityChanged(!r2);
        return;
    }

    public Controller obtainController() {
        return new ControllerImpl(this, null);
    }
}
