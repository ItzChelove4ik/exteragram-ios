package org.telegram.ui.Components;

import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AndroidUtilities$$ExternalSyntheticApiModelOutline1;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public class OverlayActionBarLayoutDialog extends Dialog implements INavigationLayout.INavigationLayoutDelegate {
    private INavigationLayout actionBarLayout;
    private FrameLayout frameLayout;
    private PasscodeView passcodeView;
    private Theme.ResourcesProvider resourcesProvider;

    public final class EmptyFragment extends BaseFragment {
        final /* synthetic */ OverlayActionBarLayoutDialog this$0;

        private EmptyFragment(OverlayActionBarLayoutDialog r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ EmptyFragment(OverlayActionBarLayoutDialog r1, OverlayActionBarLayoutDialogIA r2) {
            this(r1);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public View createView(Context r2) {
            this.hasOwnBackground = true;
            this.actionBar.setAddToContainer(false);
            View r1 = new View(r2);
            r1.setBackgroundColor(0);
            return r1;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public void onTransitionAnimationEnd(boolean r1, boolean r2) {
            if (r1 == false) goto L6;
            if (r2 == false) goto L7;
            this.this$0.dismiss();
            return;
        L7:
            return;
        }
    }

    /* renamed from: $r8$lambda$AKW-EMZGytT31G2x4IT_NdAkiqA, reason: not valid java name */
    public static /* synthetic */ WindowInsets m11319$r8$lambda$AKWEMZGytT31G2x4IT_NdAkiqA(View r2, WindowInsets r3) {
        r2.setPadding(0, 0, 0, r3.getSystemWindowInsetBottom());
        return r3;
    }

    public static /* synthetic */ void $r8$lambda$TZHJEkD7p5g90Hyp2OZMbT2CniY(OverlayActionBarLayoutDialog r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public OverlayActionBarLayoutDialog(Context r6, Theme.ResourcesProvider r7) {
        super(r6, R.style.TransparentDialog);
        this.resourcesProvider = r7;
        INavigationLayout r72 = INavigationLayout.newLayout(r6, false);
        this.actionBarLayout = r72;
        r72.setFragmentStack(new ArrayList());
        this.actionBarLayout.presentFragment(new INavigationLayout.NavigationParams(new EmptyFragment(this, null)).setNoAnimation(true));
        this.actionBarLayout.setDelegate(this);
        FrameLayout r73 = new FrameLayout(r6);
        this.frameLayout = r73;
        r73.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.frameLayout.addView(this.actionBarLayout.getView(), new FrameLayout.LayoutParams(-1, -1, 17));
        if (AndroidUtilities.isTablet() == true) goto L5;
    L9:
        PasscodeView r74 = new PasscodeView(r6);
        this.passcodeView = r74;
        this.frameLayout.addView(r74, LayoutHelper.createFrame(-1, -1.0f));
        setContentView(this.frameLayout);
        return;
    L5:
        if (AndroidUtilities.isInMultiwindow == true) goto L9;
        if (AndroidUtilities.isSmallTablet() == true) goto L9;
        this.frameLayout.setBackgroundColor(-1728053248);
        this.frameLayout.setOnClickListener(new OverlayActionBarLayoutDialog$$ExternalSyntheticLambda0(this));
        this.actionBarLayout.setRemoveActionBarExtraHeight(true);
        VerticalPositionAutoAnimator.attach(this.actionBarLayout.getView());
        goto L9
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        onBackPressed();
    }

    public void addFragment(BaseFragment r2) {
        INavigationLayout r1 = this.actionBarLayout;
        if (AndroidUtilities.isTablet() == true) goto L5;
    L9:
        boolean r0 = false;
    L10:
        r1.presentFragment(r2, r0);
        return;
    L5:
        if (AndroidUtilities.isInMultiwindow == true) goto L9;
        if (AndroidUtilities.isSmallTablet() == true) goto L9;
        r0 = true;
        goto L10
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout.INavigationLayoutDelegate
    public boolean needAddFragmentToStack(BaseFragment r1, INavigationLayout r2) {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout.INavigationLayoutDelegate
    public boolean needCloseLastFragment(INavigationLayout r2) {
        if (r2.getFragmentStack().size() > 1) goto L5;
        dismiss();
    L5:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout.INavigationLayoutDelegate
    public boolean needPresentFragment(BaseFragment r1, boolean r2, boolean r3, INavigationLayout r4) {
        return true;
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        if (this.passcodeView.getVisibility() == 0) goto L5;
        this.actionBarLayout.onBackPressed();
        if (this.actionBarLayout.getFragmentStack().size() > 1) goto L13;
        dismiss();
        return;
    L13:
        return;
    L5:
        if (getOwnerActivity() == null) goto L12;
        getOwnerActivity().finish();
        return;
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle r9) {
        super.onCreate(r9);
        Window r92 = getWindow();
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 30) goto L5;
        r92.addFlags(-2147483392);
    L6:
        r92.setWindowAnimations(R.style.DialogNoAnimation);
        WindowManager.LayoutParams r1 = r92.getAttributes();
        r1.width = -1;
        r1.gravity = 51;
        r1.dimAmount = 0.0f;
        r1.flags &= -3;
        r1.softInputMode = 16;
        r1.height = -1;
        boolean r3 = true;
        if (r0 < 28) goto L9;
        AndroidUtilities$$ExternalSyntheticApiModelOutline1.m(r1, 1);
    L9:
        r92.setAttributes(r1);
        r92.setStatusBarColor(0);
        this.frameLayout.setSystemUiVisibility(1280);
        this.frameLayout.setOnApplyWindowInsetsListener(new OverlayActionBarLayoutDialog$$ExternalSyntheticLambda1());
        if (r0 >= 26) goto L12;
        return;
    L12:
        if (ColorUtils.calculateLuminance(Theme.getColor(Theme.key_windowBackgroundWhite, null, true)) >= 0.9d) goto L15;
        r3 = false;
    L15:
        AndroidUtilities.setLightNavigationBar(this, r3);
        return;
    L5:
        r92.addFlags(-2147417856);
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout.INavigationLayoutDelegate
    public void onMeasureOverride(int[] r4) {
        if (AndroidUtilities.isTablet() == true) goto L5;
        return;
    L5:
        if (AndroidUtilities.isInMultiwindow == false) goto L7;
        return;
    L7:
        if (AndroidUtilities.isSmallTablet() == true) goto L12;
        r4[0] = View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(530.0f), View.MeasureSpec.getSize(r4[0])), TLObject.FLAG_30);
        r4[1] = View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(528.0f), View.MeasureSpec.getSize(r4[1])), TLObject.FLAG_30);
        return;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout.INavigationLayoutDelegate
    public boolean onPreIme() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout.INavigationLayoutDelegate
    public void onRebuildAllFragments(INavigationLayout r1, boolean r2) {
    }

    @Override // android.app.Dialog
    public void onStart() {
        super.onStart();
        Context r0 = getContext();
        if ((r0 instanceof ContextWrapper) == false) goto L8;
        if ((r0 instanceof LaunchActivity) == true) goto L8;
        r0 = ((ContextWrapper) r0).getBaseContext();
    L8:
        if ((r0 instanceof LaunchActivity) == false) goto L11;
        ((LaunchActivity) r0).addOverlayPasscodeView(this.passcodeView);
        return;
    }

    @Override // android.app.Dialog
    public void onStop() {
        super.onStop();
        Context r0 = getContext();
        if ((r0 instanceof ContextWrapper) == false) goto L8;
        if ((r0 instanceof LaunchActivity) == true) goto L8;
        r0 = ((ContextWrapper) r0).getBaseContext();
    L8:
        if ((r0 instanceof LaunchActivity) == false) goto L11;
        ((LaunchActivity) r0).removeOverlayPasscodeView(this.passcodeView);
        return;
    }
}
