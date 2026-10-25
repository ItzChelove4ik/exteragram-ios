package org.telegram.ui.Components;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.DrawerLayoutContainer;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public class PasscodeViewDialog extends Dialog {
    public final Context context;
    public final PasscodeView passcodeView;
    private final FrameLayout windowView;

    /* renamed from: org.telegram.ui.Components.PasscodeViewDialog$1, reason: invalid class name */
    public class AnonymousClass1 extends PasscodeView {
        final /* synthetic */ PasscodeViewDialog this$0;

        public AnonymousClass1(PasscodeViewDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.PasscodeView
        public void onAnimationUpdate(float r4) {
            LaunchActivity r3 = LaunchActivity.instance;
            if (r3 != null) goto L5;
            return;
        L5:
            DrawerLayoutContainer r32 = r3.drawerLayoutContainer;
            r32.setScaleX(AndroidUtilities.lerp(1.0f, 1.25f, r4));
            r32.setScaleY(AndroidUtilities.lerp(1.0f, 1.25f, r4));
        }

        @Override // org.telegram.ui.Components.PasscodeView
        public void onHidden() {
            PasscodeViewDialog.access$001(this.this$0);     // Catch: Exception -> L8
        L3:
            LaunchActivity r1 = LaunchActivity.instance;
            if (r1 != null) goto L6;
            return;
        L6:
            DrawerLayoutContainer r12 = r1.drawerLayoutContainer;
            r12.setScaleX(1.0f);
            r12.setScaleY(1.0f);
        }
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$rJei0jRC9D05r5KOWTtD7G0FCHQ(View r0, WindowInsetsCompat r1) {
        return WindowInsetsCompat.CONSUMED;
    }

    public PasscodeViewDialog(Context r3) {
        super(r3, R.style.TransparentDialog);
        this.context = r3;
        AndroidUtilities.enableEdgeToEdge(getWindow());
        FrameLayout r0 = new FrameLayout(r3);
        this.windowView = r0;
        ViewCompat.setOnApplyWindowInsetsListener(r0, new PasscodeViewDialog$$ExternalSyntheticLambda0());
        AnonymousClass1 r1 = new AnonymousClass1(this, r3);
        this.passcodeView = r1;
        r0.addView(r1, LayoutHelper.createFrame(-1, -1, 119));
    }

    public static /* synthetic */ void access$001(PasscodeViewDialog r0) {
        super.dismiss();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (this.passcodeView.onBackPressed() == false) goto L8;
        LaunchActivity r1 = LaunchActivity.instance;
        if (r1 == null) goto L9;
        r1.moveTaskToBack(true);
        return;
    L9:
        return;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent r3) {
        if (r3.getKeyCode() != 4) goto L13;
        if (r3.getRepeatCount() != 0) goto L13;
        if (this.passcodeView.onBackPressed() == false) goto L11;
        LaunchActivity r2 = LaunchActivity.instance;
        if (r2 == null) goto L11;
        r2.moveTaskToBack(true);
    L11:
        return true;
    L13:
        return super.dispatchKeyEvent(r3);
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        if (this.passcodeView.onBackPressed() == false) goto L8;
        LaunchActivity r1 = LaunchActivity.instance;
        if (r1 == null) goto L9;
        r1.moveTaskToBack(true);
        return;
    L9:
        return;
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle r4) {
        super.onCreate(r4);
        Window r42 = getWindow();
        r42.setWindowAnimations(R.style.DialogNoAnimation);
        setContentView(this.windowView, new ViewGroup.LayoutParams(-1, -1));
        WindowManager.LayoutParams r0 = r42.getAttributes();
        r0.width = -1;
        r0.height = -1;
        r0.gravity = 119;
        r0.dimAmount = 0.0f;
        int r1 = r0.flags & (-3);
        r0.flags = r1;
        r0.softInputMode = 16;
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L5;
        r0.flags = r1 | 8192;
        AndroidUtilities.logFlagSecure();
    L5:
        r0.flags |= -2013198976;
        r42.setAttributes(r0);
        this.windowView.setSystemUiVisibility(256);
        AndroidUtilities.setLightNavigationBar(this, false);
    }
}
