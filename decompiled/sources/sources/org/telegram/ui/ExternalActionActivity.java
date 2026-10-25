package org.telegram.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.DrawerLayoutContainer;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ActivityWindowEmptyBackgroundDrawable;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PasscodeView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;

/* loaded from: classes6.dex */
public class ExternalActionActivity extends Activity implements INavigationLayout.INavigationLayoutDelegate {
    private static final ArrayList<BaseFragment> layerFragmentsStack = null;
    private static final ArrayList<BaseFragment> mainFragmentsStack = null;
    protected INavigationLayout actionBarLayout;
    protected SizeNotifierFrameLayout backgroundTablet;
    protected DrawerLayoutContainer drawerLayoutContainer;
    private boolean finished;
    protected INavigationLayout layersActionBarLayout;
    private Runnable lockRunnable;
    private Intent passcodeSaveIntent;
    private int passcodeSaveIntentAccount;
    private boolean passcodeSaveIntentIsNew;
    private boolean passcodeSaveIntentIsRestore;
    private int passcodeSaveIntentState;
    private PasscodeView passcodeView;

    /* renamed from: org.telegram.ui.ExternalActionActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends SizeNotifierFrameLayout {
        final /* synthetic */ ExternalActionActivity this$0;

        public AnonymousClass1(ExternalActionActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public boolean isActionBarVisible() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.ExternalActionActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends SizeNotifierFrameLayout {
        final /* synthetic */ ExternalActionActivity this$0;

        public AnonymousClass2(ExternalActionActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public boolean isActionBarVisible() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.ExternalActionActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ ExternalActionActivity this$0;

        public AnonymousClass3(ExternalActionActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            this.this$0.needLayout();
            INavigationLayout r0 = this.this$0.actionBarLayout;
            if (r0 == null) goto L6;
            r0.getView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ExternalActionActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements Runnable {
        final /* synthetic */ ExternalActionActivity this$0;

        public AnonymousClass4(ExternalActionActivity r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ExternalActionActivity.m14713$$Nest$fgetlockRunnable(this.this$0) == this) goto L5;
            return;
        L5:
            if (AndroidUtilities.needShowPasscode(true) == false) goto L11;
            if (BuildVars.LOGS_ENABLED == false) goto L9;
            FileLog.d("lock app");
        L9:
            ExternalActionActivity.m14715$$Nest$mshowPasscodeActivity(this.this$0);
        L13:
            ExternalActionActivity.m14714$$Nest$fputlockRunnable(this.this$0, null);
            return;
        L11:
            if (BuildVars.LOGS_ENABLED == false) goto L13;
            FileLog.d("didn't pass lock check");
            goto L13
        }
    }

    /* renamed from: $r8$lambda$-vEiGJdehqhNiEbmsZQFdSM9D8A, reason: not valid java name */
    public static /* synthetic */ void m14710$r8$lambda$vEiGJdehqhNiEbmsZQFdSM9D8A(ExternalActionActivity r0, AlertDialog r1, TLRPC.TL_error r2) {
        r0.lambda$handleIntent$9(r1, r2);
    }

    /* renamed from: $r8$lambda$3CtMwE-uSz4c9KzCuH8jYOeZgRE, reason: not valid java name */
    public static /* synthetic */ void m14711$r8$lambda$3CtMwEuSz4c9KzCuH8jYOeZgRE(ExternalActionActivity r0, int[] r1, int r2, AlertDialog r3, TL_account.getAuthorizationForm r4, String r5, String r6, TLObject r7, TLRPC.TL_error r8) {
        r0.lambda$handleIntent$10(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    /* renamed from: $r8$lambda$LDj9HEe3j4dqi3zd6W-7RCPN7a4, reason: not valid java name */
    public static /* synthetic */ void m14712$r8$lambda$LDj9HEe3j4dqi3zd6W7RCPN7a4(int r0, int[] r1, DialogInterface r2) {
        ConnectionsManager.getInstance(r0).cancelRequest(r1[0], true);
    }

    public static /* synthetic */ void $r8$lambda$Otucmk6IWQ8o503mS__gLl1wHgc(ExternalActionActivity r0, AlertDialog r1, int r2, TL_account.authorizationForm r3, TL_account.getAuthorizationForm r4, String r5, String r6, TLObject r7, TLRPC.TL_error r8) {
        r0.lambda$handleIntent$7(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ boolean $r8$lambda$PmdW3HQSTw_MOFZ8vQScoHSmQQ0(ExternalActionActivity r0, View r1, MotionEvent r2) {
        return r0.lambda$onCreate$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$VG4kU19YlTW2WiomVuwzo0XsGXM(ExternalActionActivity r0, PasscodeView r1) {
        r0.lambda$showPasscodeActivity$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$WvNkhdkbd45JtgQFy3lhB2jcDiY(ExternalActionActivity r0, DialogInterface r1) {
        r0.lambda$handleIntent$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$Ztv40Vl1oPLFTV2TDZbNcJA7kQY(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$hfUCy1DSFusA_MCYjDmMSMq8B_g(ExternalActionActivity r0, int r1, Intent r2, boolean r3, boolean r4, boolean r5, int r6) {
        r0.lambda$handleIntent$3(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$iwzjgAaZfDVMOo8i9695m4pX3v4(ExternalActionActivity r0, AlertDialog r1, TLObject r2, int r3, TL_account.authorizationForm r4, TL_account.getAuthorizationForm r5, String r6, String r7) {
        r0.lambda$handleIntent$6(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$yPHF6DaYfMtRV_2Sdlpg6i0SSOA(ExternalActionActivity r0, TLRPC.TL_error r1, DialogInterface r2) {
        r0.lambda$handleIntent$8(r1, r2);
    }

    /* renamed from: -$$Nest$fgetlockRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m14713$$Nest$fgetlockRunnable(ExternalActionActivity r0) {
        return r0.lockRunnable;
    }

    /* renamed from: -$$Nest$fputlockRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14714$$Nest$fputlockRunnable(ExternalActionActivity r0, Runnable r1) {
        r0.lockRunnable = r1;
    }

    /* renamed from: -$$Nest$mshowPasscodeActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14715$$Nest$mshowPasscodeActivity(ExternalActionActivity r0) {
        r0.showPasscodeActivity();
    }

    static {
        mainFragmentsStack = new ArrayList();
        layerFragmentsStack = new ArrayList();
    }

    public ExternalActionActivity() {
    }

    private /* synthetic */ void lambda$handleIntent$10(int[] r11, int r12, AlertDialog r13, TL_account.getAuthorizationForm r14, String r15, String r16, TLObject r17, TLRPC.TL_error r18) {
        TL_account.authorizationForm r4 = (TL_account.authorizationForm) r17;
        if (r4 == null) goto L6;
        TL_account.getPassword r8 = new TL_account.getPassword();
        r11[0] = ConnectionsManager.getInstance(r12).sendRequest(r8, new ExternalActionActivity$$ExternalSyntheticLambda7(this, r13, r12, r4, r14, r15, r16));
        return;
    L6:
        AndroidUtilities.runOnUIThread(new ExternalActionActivity$$ExternalSyntheticLambda8(this, r13, r18));
    }

    private /* synthetic */ void lambda$handleIntent$3(int r1, Intent r2, boolean r3, boolean r4, boolean r5, int r6) {
        if (r6 == r1) goto L4;
        switchToAccount(r6);
    L4:
        handleIntent(r2, r3, r4, r5, r6, 1);
    }

    private /* synthetic */ void lambda$handleIntent$4(DialogInterface r1) {
        setResult(0);
        finish();
    }

    private /* synthetic */ void lambda$handleIntent$6(AlertDialog r12, TLObject r13, int r14, TL_account.authorizationForm r15, TL_account.getAuthorizationForm r16, String r17, String r18) {
        r12.dismiss();     // Catch: Exception -> L5
    L7:
        if (r13 == null) goto L21;
        TL_account.Password r10 = (TL_account.Password) r13;
        MessagesController.getInstance(r14).putUsers(r15.users, false);
        PassportActivity r0 = new PassportActivity(5, r16.bot_id, r16.scope, r16.public_key, r17, r18, null, r15, r10);
        r0.setNeedActivityResult(true);
        if (AndroidUtilities.isTablet() == false) goto L11;
        this.layersActionBarLayout.addFragmentToStack(r0);
    L13:
        if (AndroidUtilities.isTablet() == true) goto L15;
        this.backgroundTablet.setVisibility(8);
    L15:
        this.actionBarLayout.showLastFragment();
        if (AndroidUtilities.isTablet() == false) goto L22;
        this.layersActionBarLayout.showLastFragment();
        return;
    L22:
        return;
    L11:
        this.actionBarLayout.addFragmentToStack(r0);
        goto L13
    L21:
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    private /* synthetic */ void lambda$handleIntent$7(AlertDialog r2, int r3, TL_account.authorizationForm r4, TL_account.getAuthorizationForm r5, String r6, String r7, TLObject r8, TLRPC.TL_error r9) {
        AndroidUtilities.runOnUIThread(new ExternalActionActivity$$ExternalSyntheticLambda9(this, r2, r8, r3, r4, r5, r6, r7));
    }

    private /* synthetic */ void lambda$handleIntent$8(TLRPC.TL_error r2, DialogInterface r3) {
        setResult(1, new Intent().putExtra("error", r2.text));
        finish();
    }

    private /* synthetic */ void lambda$handleIntent$9(AlertDialog r4, TLRPC.TL_error r5) {
        r4.dismiss();     // Catch: Exception -> L26
        if ("APP_VERSION_OUTDATED".equals(r5.text) == false) goto L12;
        AlertDialog r42 = AlertsCreator.showUpdateAppAlert(this, LocaleController.getString(R.string.UpdateAppAlert), true);     // Catch: Exception -> L26
        if (r42 == null) goto L9;
        r42.setOnDismissListener(new ExternalActionActivity$$ExternalSyntheticLambda10(this, r5));     // Catch: Exception -> L26
        return;
    L9:
        setResult(1, new Intent().putExtra("error", r5.text));     // Catch: Exception -> L26
        finish();     // Catch: Exception -> L26
        return;
    L12:
        if ("BOT_INVALID".equals(r5.text) == false) goto L14;
    L24:
        setResult(1, new Intent().putExtra("error", r5.text));     // Catch: Exception -> L26
        finish();     // Catch: Exception -> L26
        return;
    L14:
        if ("PUBLIC_KEY_REQUIRED".equals(r5.text) == true) goto L24;
        if ("PUBLIC_KEY_INVALID".equals(r5.text) == true) goto L24;
        if ("SCOPE_EMPTY".equals(r5.text) == true) goto L24;
        if ("PAYLOAD_EMPTY".equals(r5.text) == true) goto L24;
        setResult(0);     // Catch: Exception -> L26
        finish();     // Catch: Exception -> L26
        return;
    L26:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ boolean lambda$onCreate$0(View r6, MotionEvent r7) {
        if (this.actionBarLayout.getFragmentStack().isEmpty() == false) goto L5;
    L24:
        return false;
    L5:
        if (r7.getAction() != 1) goto L24;
        float r62 = r7.getX();
        float r72 = r7.getY();
        int[] r2 = new int[2];
        this.layersActionBarLayout.getView().getLocationOnScreen(r2);
        int r3 = r2[0];
        int r22 = r2[1];
        if (this.layersActionBarLayout.checkTransitionAnimation() == true) goto L24;
        if (r62 <= r3) goto L18;
        if (r62 >= (r3 + this.layersActionBarLayout.getView().getWidth())) goto L18;
        if (r72 <= r22) goto L18;
        if (r72 < (r22 + this.layersActionBarLayout.getView().getHeight())) goto L24;
    L18:
        if (this.layersActionBarLayout.getFragmentStack().isEmpty() == true) goto L23;
    L19:
        int r63 = this.layersActionBarLayout.getFragmentStack().size() - 1;
        INavigationLayout r73 = this.layersActionBarLayout;
        if (r63 <= 0) goto L22;
        r73.removeFragmentFromStack(r73.getFragmentStack().get(0));
        goto L19
    L22:
        r73.closeLastFragment(true);
    L23:
        return true;
    }

    private /* synthetic */ void lambda$showPasscodeActivity$2(PasscodeView r9) {
        SharedConfig.isWaitingForPasscodeEnter = false;
        Intent r2 = this.passcodeSaveIntent;
        if (r2 == null) goto L5;
        ExternalActionActivity r1 = this;
        r1.handleIntent(r2, this.passcodeSaveIntentIsNew, this.passcodeSaveIntentIsRestore, true, this.passcodeSaveIntentAccount, this.passcodeSaveIntentState);
        r1.passcodeSaveIntent = null;
    L6:
        r1.actionBarLayout.showLastFragment();
        if (AndroidUtilities.isTablet() == false) goto L9;
        r1.layersActionBarLayout.showLastFragment();
    L9:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.passcodeDismissed, new Object[]{r9});
        return;
    L5:
        r1 = this;
        goto L6
    }

    private void onFinish() {
        if (this.finished == false) goto L5;
        return;
    L5:
        Runnable r0 = this.lockRunnable;
        if (r0 == null) goto L8;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.lockRunnable = null;
    L8:
        this.finished = true;
    }

    private void onPasscodePause() {
        Runnable r0 = this.lockRunnable;
        if (r0 == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.lockRunnable = null;
    L6:
        if (SharedConfig.passcodeHash.isEmpty() == true) goto L15;
        SharedConfig.lastPauseTime = (int) (SystemClock.elapsedRealtime() / 1000);
        AnonymousClass4 r02 = new AnonymousClass4(this);
        this.lockRunnable = r02;
        if (SharedConfig.appLocked == true) goto L14;
        int r6 = SharedConfig.autoLockIn;
        if (r6 == Integer.MAX_VALUE) goto L14;
        if (r6 == 0) goto L16;
        AndroidUtilities.runOnUIThread(r02, (r6 * 1000) + 1000);
    L16:
        SharedConfig.saveConfig();
        return;
    L14:
        AndroidUtilities.runOnUIThread(r02, 1000);
        goto L16
    L15:
        SharedConfig.lastPauseTime = 0;
        goto L16
    }

    private void onPasscodeResume() {
        Runnable r0 = this.lockRunnable;
        if (r0 == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.lockRunnable = null;
    L6:
        if (AndroidUtilities.needShowPasscode(true) == false) goto L9;
        showPasscodeActivity();
    L9:
        if (SharedConfig.lastPauseTime == 0) goto L12;
        SharedConfig.lastPauseTime = 0;
        SharedConfig.saveConfig();
        return;
    }

    private void showPasscodeActivity() {
        if (this.passcodeView != null) goto L5;
        return;
    L5:
        SharedConfig.appLocked = true;
        if (SecretMediaViewer.hasInstance() == false) goto L11;
        if (SecretMediaViewer.getInstance().isVisible() == false) goto L11;
        SecretMediaViewer.getInstance().closePhoto(false, false);
    L20:
        this.passcodeView.onShow(true, false);
        SharedConfig.isWaitingForPasscodeEnter = true;
        this.passcodeView.setDelegate(new ExternalActionActivity$$ExternalSyntheticLambda2(this));
        return;
    L11:
        if (PhotoViewer.hasInstance() == false) goto L16;
        if (PhotoViewer.getInstance().isVisible() == false) goto L16;
        PhotoViewer.getInstance().closePhoto(false, true);
    L16:
        if (ArticleViewer.hasInstance() == false) goto L20;
        if (ArticleViewer.getInstance().isVisible() == false) goto L20;
        ArticleViewer.getInstance().close(false, true);
        goto L20
    }

    public boolean checkPasscode(Intent r2, boolean r3, boolean r4, boolean r5, int r6, int r7) {
        if (r5 == false) goto L5;
    L10:
        return true;
    L5:
        if (AndroidUtilities.needShowPasscode(true) == false) goto L7;
    L8:
        showPasscodeActivity();
        this.passcodeSaveIntent = r2;
        this.passcodeSaveIntentIsNew = r3;
        this.passcodeSaveIntentIsRestore = r4;
        this.passcodeSaveIntentAccount = r6;
        this.passcodeSaveIntentState = r7;
        UserConfig.getInstance(r6).saveConfig(false);
        return false;
    L7:
        if (SharedConfig.isWaitingForPasscodeEnter == false) goto L10;
        goto L8
    }

    public void fixLayout() {
        if (AndroidUtilities.isTablet() == false) goto L10;
        INavigationLayout r0 = this.actionBarLayout;
        if (r0 != null) goto L8;
        return;
    L8:
        r0.getView().getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass3(this));
        return;
    }

    public boolean handleIntent(Intent r10, boolean r11, boolean r12, boolean r13, int r14, int r15) {
        if (checkPasscode(r10, r11, r12, r13, r14, r15) == true) goto L6;
        return false;
    L6:
        if ("org.telegram.passport.AUTHORIZE".equals(r10.getAction()) == false) goto L42;
        if (r15 != 0) goto L27;
        int r1 = UserConfig.getActivatedAccountsCount();
        if (r1 != 0) goto L24;
        this.passcodeSaveIntent = r10;
        this.passcodeSaveIntentIsNew = r11;
        this.passcodeSaveIntentIsRestore = r12;
        this.passcodeSaveIntentAccount = r14;
        this.passcodeSaveIntentState = r15;
        LoginActivity r102 = new LoginActivity();
        if (AndroidUtilities.isTablet() == false) goto L14;
        this.layersActionBarLayout.addFragmentToStack(r102);
    L16:
        if (AndroidUtilities.isTablet() == true) goto L18;
        this.backgroundTablet.setVisibility(8);
    L18:
        this.actionBarLayout.showLastFragment();
        if (AndroidUtilities.isTablet() == false) goto L21;
        this.layersActionBarLayout.showLastFragment();
    L21:
        AlertDialog.Builder r103 = new AlertDialog.Builder(this);
        r103.setTitle(LocaleController.getString(R.string.AppName));
        r103.setMessage(LocaleController.getString(R.string.PleaseLoginPassport));
        r103.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r103.show();
        return true;
    L14:
        this.actionBarLayout.addFragmentToStack(r102);
        goto L16
    L24:
        if (r1 < 2) goto L27;
        AlertDialog r104 = AlertsCreator.createAccountSelectDialog(this, new ExternalActionActivity$$ExternalSyntheticLambda3(this, r14, r10, r11, r12, r13));
        r104.show();
        r104.setCanceledOnTouchOutside(false);
        r104.setOnDismissListener(new ExternalActionActivity$$ExternalSyntheticLambda4(this));
        return true;
    L27:
        long r3 = r10.getLongExtra("bot_id", r10.getIntExtra("bot_id", 0));
        String r122 = r10.getStringExtra("nonce");
        String r6 = r10.getStringExtra("payload");
        TL_account.getAuthorizationForm r5 = new TL_account.getAuthorizationForm();
        r5.bot_id = r3;
        r5.scope = r10.getStringExtra("scope");
        r5.public_key = r10.getStringExtra("public_key");
        if (r3 != 0) goto L30;
    L39:
        finish();
        return false;
    L30:
        if (TextUtils.isEmpty(r6) == false) goto L34;
        if (TextUtils.isEmpty(r122) == true) goto L39;
    L34:
        if (TextUtils.isEmpty(r5.scope) == true) goto L39;
        if (TextUtils.isEmpty(r5.public_key) == true) goto L39;
        int[] r105 = {0};
        AlertDialog r4 = new AlertDialog(this, 3);
        r4.setOnCancelListener(new ExternalActionActivity$$ExternalSyntheticLambda5(r14, r105));
        r4.show();
        r105[0] = ConnectionsManager.getInstance(r14).sendRequest(r5, new ExternalActionActivity$$ExternalSyntheticLambda6(this, r105, r14, r4, r5, r6, r122), 10);
    L56:
        return false;
    L42:
        if (AndroidUtilities.isTablet() == false) goto L47;
        if (this.layersActionBarLayout.getFragmentStack().isEmpty() == false) goto L50;
        this.layersActionBarLayout.addFragmentToStack(new CacheControlActivity());
    L50:
        if (AndroidUtilities.isTablet() == true) goto L52;
        this.backgroundTablet.setVisibility(8);
    L52:
        this.actionBarLayout.showLastFragment();
        if (AndroidUtilities.isTablet() == false) goto L55;
        this.layersActionBarLayout.showLastFragment();
    L55:
        r10.setAction(null);
        goto L56
    L47:
        if (this.actionBarLayout.getFragmentStack().isEmpty() == false) goto L50;
        this.actionBarLayout.addFragmentToStack(new CacheControlActivity());
        goto L50
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout.INavigationLayoutDelegate
    public boolean needAddFragmentToStack(BaseFragment r1, INavigationLayout r2) {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout.INavigationLayoutDelegate
    public boolean needCloseLastFragment(INavigationLayout r4) {
        if (AndroidUtilities.isTablet() == false) goto L19;
        if (r4 != this.actionBarLayout) goto L11;
        if (r4.getFragmentStack().size() > 1) goto L11;
        onFinish();
        finish();
        return false;
    L11:
        if (r4 == this.layersActionBarLayout) goto L13;
    L22:
        return true;
    L13:
        if (this.actionBarLayout.getFragmentStack().isEmpty() == false) goto L22;
        if (this.layersActionBarLayout.getFragmentStack().size() != 1) goto L22;
        onFinish();
        finish();
        return false;
    L19:
        if (r4.getFragmentStack().size() > 1) goto L22;
        onFinish();
        finish();
        return false;
    }

    public void needLayout() {
        if (AndroidUtilities.isTablet() == false) goto L20;
        RelativeLayout.LayoutParams r0 = (RelativeLayout.LayoutParams) this.layersActionBarLayout.getView().getLayoutParams();
        r0.leftMargin = (AndroidUtilities.displaySize.x - r0.width) / 2;
        int r1 = AndroidUtilities.statusBarHeight;
        r0.topMargin = r1 + (((AndroidUtilities.displaySize.y - r0.height) - r1) / 2);
        this.layersActionBarLayout.getView().setLayoutParams(r0);
        if (AndroidUtilities.isSmallTablet() == true) goto L7;
    L11:
        int r02 = (AndroidUtilities.displaySize.x / 100) * 35;
        if (r02 >= AndroidUtilities.dp(320.0f)) goto L14;
        r02 = AndroidUtilities.dp(320.0f);
    L14:
        RelativeLayout.LayoutParams r3 = (RelativeLayout.LayoutParams) this.actionBarLayout.getView().getLayoutParams();
        r3.width = r02;
        r3.height = -1;
        this.actionBarLayout.getView().setLayoutParams(r3);
        if (AndroidUtilities.isSmallTablet() == true) goto L17;
        return;
    L17:
        if (this.actionBarLayout.getFragmentStack().size() != 2) goto L22;
        this.actionBarLayout.getFragmentStack().get(1).onPause();
        this.actionBarLayout.getFragmentStack().remove(1);
        this.actionBarLayout.showLastFragment();
        return;
    L22:
        return;
    L7:
        if (getResources().getConfiguration().orientation == 2) goto L11;
        RelativeLayout.LayoutParams r03 = (RelativeLayout.LayoutParams) this.actionBarLayout.getView().getLayoutParams();
        r03.width = -1;
        r03.height = -1;
        this.actionBarLayout.getView().setLayoutParams(r03);
        return;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout.INavigationLayoutDelegate
    public boolean needPresentFragment(BaseFragment r1, boolean r2, boolean r3, INavigationLayout r4) {
        return true;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.passcodeView.getVisibility() != 0) goto L7;
        finish();
        return;
    L7:
        if (PhotoViewer.getInstance().isVisible() == false) goto L11;
        PhotoViewer.getInstance().closePhoto(true, false);
        return;
    L11:
        if (AndroidUtilities.isTablet() == true) goto L13;
        this.actionBarLayout.onBackPressed();
        return;
    L13:
        if (this.layersActionBarLayout.getView().getVisibility() != 0) goto L16;
        this.layersActionBarLayout.onBackPressed();
        return;
    L16:
        this.actionBarLayout.onBackPressed();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration r2) {
        AndroidUtilities.checkDisplaySize(this, r2);
        AndroidUtilities.setPreferredMaxRefreshRate(getWindow());
        super.onConfigurationChanged(r2);
        fixLayout();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle r11) {
        ApplicationLoader.postInitApplication();
        requestWindowFeature(1);
        setTheme(R.style.Theme_TMessages);
        getWindow().setBackgroundDrawable(new ActivityWindowEmptyBackgroundDrawable());
        if (SharedConfig.passcodeHash.isEmpty() == false) goto L5;
    L10:
        super.onCreate(r11);
        if (SharedConfig.passcodeHash.isEmpty() == false) goto L13;
    L15:
        AndroidUtilities.fillStatusBarHeight(this, false);
        Theme.createDialogsResources(this);
        Theme.createChatResources(this, false);
        this.actionBarLayout = INavigationLayout.newLayout(this, false);
        DrawerLayoutContainer r2 = new DrawerLayoutContainer(this);
        this.drawerLayoutContainer = r2;
        setContentView(r2, new ViewGroup.LayoutParams(-1, -1));
        if (AndroidUtilities.isTablet() == false) goto L22;
        getWindow().setSoftInputMode(16);
        RelativeLayout r22 = new RelativeLayout(this);
        this.drawerLayoutContainer.addView(r22);
        FrameLayout.LayoutParams r5 = (FrameLayout.LayoutParams) r22.getLayoutParams();
        r5.width = -1;
        r5.height = -1;
        r22.setLayoutParams(r5);
        AnonymousClass1 r52 = new AnonymousClass1(this, this);
        this.backgroundTablet = r52;
        r52.setOccupyStatusBar(false);
        this.backgroundTablet.setBackgroundImage(Theme.getCachedWallpaper(), Theme.isWallpaperMotion());
        r22.addView(this.backgroundTablet, LayoutHelper.createRelative(-1, -1));
        r22.addView(this.actionBarLayout.getView(), LayoutHelper.createRelative(-1, -1));
        FrameLayout r53 = new FrameLayout(this);
        r53.setBackgroundColor(2130706432);
        r22.addView(r53, LayoutHelper.createRelative(-1, -1));
        r53.setOnTouchListener(new ExternalActionActivity$$ExternalSyntheticLambda0(this));
        r53.setOnClickListener(new ExternalActionActivity$$ExternalSyntheticLambda1());
        INavigationLayout r6 = INavigationLayout.newLayout(this, false);
        this.layersActionBarLayout = r6;
        r6.setRemoveActionBarExtraHeight(true);
        this.layersActionBarLayout.setBackgroundView(r53);
        this.layersActionBarLayout.setUseAlphaAnimations(true);
        this.layersActionBarLayout.getView().setBackgroundResource(R.drawable.boxshadow);
        ViewGroup r54 = this.layersActionBarLayout.getView();
        if (AndroidUtilities.isSmallTablet() == false) goto L20;
        int r62 = 528;
    L21:
        r22.addView(r54, LayoutHelper.createRelative(530, r62));
        this.layersActionBarLayout.setFragmentStack(layerFragmentsStack);
        this.layersActionBarLayout.setDelegate(this);
        this.layersActionBarLayout.setDrawerLayoutContainer(this.drawerLayoutContainer);
    L23:
        this.drawerLayoutContainer.setParentActionBarLayout(this.actionBarLayout);
        this.actionBarLayout.setDrawerLayoutContainer(this.drawerLayoutContainer);
        this.actionBarLayout.setFragmentStack(mainFragmentsStack);
        this.actionBarLayout.setDelegate(this);
        PasscodeView r23 = new PasscodeView(this);
        this.passcodeView = r23;
        this.drawerLayoutContainer.addView(r23, LayoutHelper.createFrame(-1, -1.0f));
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.closeOtherAppActivities, new Object[]{this});
        INavigationLayout r24 = this.actionBarLayout;
        if (r24 == null) goto L26;
        r24.removeAllFragments();
    L26:
        INavigationLayout r25 = this.layersActionBarLayout;
        if (r25 == null) goto L29;
        r25.removeAllFragments();
    L29:
        Intent r4 = getIntent();
        if (r11 == null) goto L32;
        boolean r63 = true;
    L33:
        handleIntent(r4, false, r63, false, UserConfig.selectedAccount, 0);
        needLayout();
        return;
    L32:
        r63 = false;
        goto L33
    L20:
        r62 = 700;
        goto L21
    L22:
        RelativeLayout r26 = new RelativeLayout(this);
        this.drawerLayoutContainer.addView(r26, LayoutHelper.createFrame(-1, -1.0f));
        AnonymousClass2 r55 = new AnonymousClass2(this, this);
        this.backgroundTablet = r55;
        r55.setOccupyStatusBar(false);
        this.backgroundTablet.setBackgroundImage(Theme.getCachedWallpaper(), Theme.isWallpaperMotion());
        r26.addView(this.backgroundTablet, LayoutHelper.createRelative(-1, -1));
        r26.addView(this.actionBarLayout.getView(), LayoutHelper.createRelative(-1, -1));
        goto L23
    L13:
        if (SharedConfig.appLocked == false) goto L15;
        SharedConfig.lastPauseTime = (int) (SystemClock.elapsedRealtime() / 1000);
        goto L15
    L5:
        if (SharedConfig.allowScreenCapture == true) goto L10;
        getWindow().setFlags(8192, 8192);     // Catch: Exception -> L8
        AndroidUtilities.logFlagSecure();     // Catch: Exception -> L8
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L10
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        onFinish();
    }

    public void onFinishLogin() {
        handleIntent(this.passcodeSaveIntent, this.passcodeSaveIntentIsNew, this.passcodeSaveIntentIsRestore, true, this.passcodeSaveIntentAccount, this.passcodeSaveIntentState);
        INavigationLayout r7 = this.actionBarLayout;
        if (r7 == null) goto L5;
        r7.removeAllFragments();
    L5:
        INavigationLayout r72 = this.layersActionBarLayout;
        if (r72 == null) goto L8;
        r72.removeAllFragments();
    L8:
        SizeNotifierFrameLayout r73 = this.backgroundTablet;
        if (r73 == null) goto L12;
        r73.setVisibility(0);
        return;
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        this.actionBarLayout.onLowMemory();
        if (AndroidUtilities.isTablet() == false) goto L6;
        this.layersActionBarLayout.onLowMemory();
        return;
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent r8) {
        super.onNewIntent(r8);
        handleIntent(r8, true, false, false, UserConfig.selectedAccount, 0);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.actionBarLayout.onPause();
        if (AndroidUtilities.isTablet() == false) goto L5;
        this.layersActionBarLayout.onPause();
    L5:
        ApplicationLoader.externalInterfacePaused = true;
        onPasscodePause();
        PasscodeView r1 = this.passcodeView;
        if (r1 == null) goto L9;
        r1.onPause();
        return;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout.INavigationLayoutDelegate
    public boolean onPreIme() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout.INavigationLayoutDelegate
    public void onRebuildAllFragments(INavigationLayout r2, boolean r3) {
        if (AndroidUtilities.isTablet() == true) goto L5;
        return;
    L5:
        if (r2 != this.layersActionBarLayout) goto L9;
        this.actionBarLayout.rebuildAllFragmentViews(r3, r3);
        return;
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.actionBarLayout.onResume();
        if (AndroidUtilities.isTablet() == false) goto L5;
        this.layersActionBarLayout.onResume();
    L5:
        ApplicationLoader.externalInterfacePaused = false;
        onPasscodeResume();
        int r0 = this.passcodeView.getVisibility();
        INavigationLayout r1 = this.actionBarLayout;
        if (r0 == 0) goto L11;
        r1.onResume();
        if (AndroidUtilities.isTablet() == false) goto L16;
        this.layersActionBarLayout.onResume();
        return;
    L16:
        return;
    L11:
        r1.dismissDialogs();
        if (AndroidUtilities.isTablet() == false) goto L14;
        this.layersActionBarLayout.dismissDialogs();
    L14:
        this.passcodeView.onResume();
    }

    public void switchToAccount(int r3) {
        int r2 = UserConfig.selectedAccount;
        if (r3 == r2) goto L10;
        ConnectionsManager.getInstance(r2).setAppPaused(true, false);
        UserConfig.selectedAccount = r3;
        UserConfig.getInstance(0).saveConfig(false);
        if (ApplicationLoader.mainInterfacePaused == true) goto L9;
        ConnectionsManager.getInstance(UserConfig.selectedAccount).setAppPaused(false, false);
        return;
    L9:
        return;
    }
}
