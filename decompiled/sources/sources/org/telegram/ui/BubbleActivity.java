package org.telegram.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.DrawerLayoutContainer;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ActivityWindowEmptyBackgroundDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PasscodeView;
import org.telegram.ui.Components.ThemeEditorView;

/* loaded from: classes.dex */
public class BubbleActivity extends BasePermissionsActivity implements INavigationLayout.INavigationLayoutDelegate {
    public static BubbleActivity instance;
    public INavigationLayout actionBarLayout;
    private long dialogId;
    protected DrawerLayoutContainer drawerLayoutContainer;
    private boolean finished;
    private Runnable lockRunnable;
    private final ArrayList<BaseFragment> mainFragmentsStack;
    private Intent passcodeSaveIntent;
    private int passcodeSaveIntentAccount;
    private boolean passcodeSaveIntentIsNew;
    private boolean passcodeSaveIntentIsRestore;
    private int passcodeSaveIntentState;
    private PasscodeView passcodeView;

    /* renamed from: org.telegram.ui.BubbleActivity$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ BubbleActivity this$0;

        public AnonymousClass1(BubbleActivity r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BubbleActivity.m6304$$Nest$fgetlockRunnable(this.this$0) == this) goto L5;
            return;
        L5:
            if (AndroidUtilities.needShowPasscode(true) == false) goto L11;
            if (BuildVars.LOGS_ENABLED == false) goto L9;
            FileLog.d("lock app");
        L9:
            BubbleActivity.m6306$$Nest$mshowPasscodeActivity(this.this$0);
        L13:
            BubbleActivity.m6305$$Nest$fputlockRunnable(this.this$0, null);
            return;
        L11:
            if (BuildVars.LOGS_ENABLED == false) goto L13;
            FileLog.d("didn't pass lock check");
            goto L13
        }
    }

    public static /* synthetic */ void $r8$lambda$a0AsEIcoeamfrfaa0tDjDYVIcfE(BubbleActivity r0, PasscodeView r1) {
        r0.lambda$showPasscodeActivity$0(r1);
    }

    /* renamed from: -$$Nest$fgetlockRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m6304$$Nest$fgetlockRunnable(BubbleActivity r0) {
        return r0.lockRunnable;
    }

    /* renamed from: -$$Nest$fputlockRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6305$$Nest$fputlockRunnable(BubbleActivity r0, Runnable r1) {
        r0.lockRunnable = r1;
    }

    /* renamed from: -$$Nest$mshowPasscodeActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6306$$Nest$mshowPasscodeActivity(BubbleActivity r0) {
        r0.showPasscodeActivity();
    }

    public BubbleActivity() {
        this.mainFragmentsStack = new ArrayList();
    }

    private boolean handleIntent(Intent r4, boolean r5, boolean r6, boolean r7, int r8, int r9) {
        if (r7 == false) goto L5;
    L10:
        int r52 = r4.getIntExtra("currentAccount", UserConfig.selectedAccount);
        this.currentAccount = r52;
        if (UserConfig.isValidAccount(r52) == true) goto L15;
        finish();
        return false;
    L15:
        if (r4.getAction() != null) goto L17;
    L23:
        ChatActivity r42 = null;
    L24:
        if (r42 != null) goto L27;
        finish();
        return false;
    L27:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.closeChats, new Object[]{Long.valueOf(this.dialogId)});
        this.actionBarLayout.removeAllFragments();
        this.actionBarLayout.addFragmentToStack(r42);
        AccountInstance.getInstance(this.currentAccount).getNotificationsController().setOpenedInBubble(this.dialogId, true);
        AccountInstance.getInstance(this.currentAccount).getConnectionsManager().setAppPaused(false, false);
        this.actionBarLayout.showLastFragment();
        return true;
    L17:
        if (r4.getAction().startsWith("com.tmessages.openchat") == false) goto L23;
        long r82 = r4.getLongExtra("chatId", 0);
        long r43 = r4.getLongExtra("userId", 0);
        Bundle r2 = new Bundle();
        if (r43 == 0) goto L21;
        this.dialogId = r43;
        r2.putLong("user_id", r43);
    L22:
        r42 = new ChatActivity(r2);
        r42.setInBubbleMode(true);
        r42.setCurrentAccount(this.currentAccount);
        goto L24
    L21:
        this.dialogId = -r82;
        r2.putLong("chat_id", r82);
        goto L22
    L5:
        if (AndroidUtilities.needShowPasscode(true) == false) goto L7;
    L8:
        showPasscodeActivity();
        this.passcodeSaveIntent = r4;
        this.passcodeSaveIntentIsNew = r5;
        this.passcodeSaveIntentIsRestore = r6;
        this.passcodeSaveIntentAccount = r8;
        this.passcodeSaveIntentState = r9;
        UserConfig.getInstance(r8).saveConfig(false);
        return false;
    L7:
        if (SharedConfig.isWaitingForPasscodeEnter == false) goto L10;
        goto L8
    }

    private /* synthetic */ void lambda$showPasscodeActivity$0(PasscodeView r9) {
        SharedConfig.isWaitingForPasscodeEnter = false;
        Intent r2 = this.passcodeSaveIntent;
        if (r2 == null) goto L5;
        BubbleActivity r1 = this;
        r1.handleIntent(r2, this.passcodeSaveIntentIsNew, this.passcodeSaveIntentIsRestore, true, this.passcodeSaveIntentAccount, this.passcodeSaveIntentState);
        r1.passcodeSaveIntent = null;
    L6:
        r1.actionBarLayout.showLastFragment();
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
        instance = null;
    }

    private void onPasscodePause() {
        Runnable r0 = this.lockRunnable;
        if (r0 == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.lockRunnable = null;
    L6:
        if (SharedConfig.passcodeHash.isEmpty() == true) goto L15;
        SharedConfig.lastPauseTime = (int) (SystemClock.elapsedRealtime() / 1000);
        AnonymousClass1 r02 = new AnonymousClass1(this);
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
        this.passcodeView.setDelegate(new BubbleActivity$$ExternalSyntheticLambda0(this));
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

    @Override // org.telegram.ui.ActionBar.INavigationLayout.INavigationLayoutDelegate
    public boolean needCloseLastFragment(INavigationLayout r2) {
        if (r2.getFragmentStack().size() > 1) goto L6;
        onFinish();
        finish();
        return false;
    L6:
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int r2, int r3, Intent r4) {
        super.onActivityResult(r2, r3, r4);
        ThemeEditorView r0 = ThemeEditorView.getInstance();
        if (r0 == null) goto L6;
        r0.onActivityResult(r2, r3, r4);
    L6:
        if (this.actionBarLayout.getFragmentStack().isEmpty() == true) goto L9;
        this.actionBarLayout.getFragmentStack().get(this.actionBarLayout.getFragmentStack().size() - 1).onActivityResultFragment(r2, r3, r4);
        return;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.mainFragmentsStack.size() != 1) goto L7;
        super.onBackPressed();
        return;
    L7:
        if (this.passcodeView.getVisibility() != 0) goto L11;
        finish();
        return;
    L11:
        if (PhotoViewer.getInstance().isVisible() == false) goto L14;
        PhotoViewer.getInstance().closePhoto(true, false);
        return;
    L14:
        this.actionBarLayout.onBackPressed();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration r2) {
        AndroidUtilities.checkDisplaySize(this, r2);
        AndroidUtilities.setPreferredMaxRefreshRate(getWindow());
        super.onConfigurationChanged(r2);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
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
        INavigationLayout r2 = INavigationLayout.newLayout(this, false);
        this.actionBarLayout = r2;
        r2.setInBubbleMode(true);
        this.actionBarLayout.setRemoveActionBarExtraHeight(true);
        DrawerLayoutContainer r22 = new DrawerLayoutContainer(this);
        this.drawerLayoutContainer = r22;
        setContentView(r22, new ViewGroup.LayoutParams(-1, -1));
        RelativeLayout r23 = new RelativeLayout(this);
        this.drawerLayoutContainer.addView(r23, LayoutHelper.createFrame(-1, -1.0f));
        r23.addView(this.actionBarLayout.getView(), LayoutHelper.createRelative(-1, -1));
        this.drawerLayoutContainer.setParentActionBarLayout(this.actionBarLayout);
        this.actionBarLayout.setDrawerLayoutContainer(this.drawerLayoutContainer);
        this.actionBarLayout.setFragmentStack(this.mainFragmentsStack);
        this.actionBarLayout.setDelegate(this);
        PasscodeView r24 = new PasscodeView(this);
        this.passcodeView = r24;
        this.drawerLayoutContainer.addView(r24, LayoutHelper.createFrame(-1, -1.0f));
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.closeOtherAppActivities, new Object[]{this});
        this.actionBarLayout.removeAllFragments();
        Intent r4 = getIntent();
        if (r11 == null) goto L18;
        boolean r6 = true;
    L19:
        handleIntent(r4, false, r6, false, UserConfig.selectedAccount, 0);
        instance = this;
        return;
    L18:
        r6 = false;
        goto L19
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

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        int r0 = this.currentAccount;
        if (r0 == (-1)) goto L5;
        AccountInstance.getInstance(r0).getNotificationsController().setOpenedInBubble(this.dialogId, false);
        AccountInstance.getInstance(this.currentAccount).getConnectionsManager().setAppPaused(false, false);
    L5:
        onFinish();
        instance = null;
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        this.actionBarLayout.onLowMemory();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent r8) {
        super.onNewIntent(r8);
        handleIntent(r8, true, false, false, UserConfig.selectedAccount, 0);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.actionBarLayout.onPause();
        ApplicationLoader.externalInterfacePaused = true;
        onPasscodePause();
        PasscodeView r1 = this.passcodeView;
        if (r1 == null) goto L5;
        r1.onPause();
    L5:
        instance = null;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int r2, String[] r3, int[] r4) {
        super.onRequestPermissionsResult(r2, r3, r4);
        if (checkPermissionsResult(r2, r3, r4) == true) goto L6;
        return;
    L6:
        if (this.actionBarLayout.getFragmentStack().isEmpty() == true) goto L8;
        this.actionBarLayout.getFragmentStack().get(this.actionBarLayout.getFragmentStack().size() - 1).onRequestPermissionsResultFragment(r2, r3, r4);
    L8:
        VoIPFragment.onRequestPermissionsResult(r2, r3, r4);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.actionBarLayout.onResume();
        ApplicationLoader.externalInterfacePaused = false;
        onPasscodeResume();
        int r0 = this.passcodeView.getVisibility();
        INavigationLayout r1 = this.actionBarLayout;
        if (r0 == 0) goto L5;
        r1.onResume();
    L6:
        instance = this;
        return;
    L5:
        r1.dismissDialogs();
        this.passcodeView.onResume();
        goto L6
    }
}
