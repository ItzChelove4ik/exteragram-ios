package com.exteragram.messenger.plugins.ui.components;

import android.app.Activity;
import android.content.Context;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.exteragram.messenger.components.CheckBoxRow;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PythonPluginsEngine;
import com.exteragram.messenger.updater.UpdateAppAlertDialog;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u000b\u001a\u00020\tH\u0002J\b\u0010\f\u001a\u00020\tH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u000eH\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0013H\u0014J<\u0010\u0017\u001a\u00020\u0013*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001aH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/PythonSdkUpdateAlert;", "Lcom/exteragram/messenger/updater/UpdateAppAlertDialog;", "fragment", "Lorg/telegram/ui/ActionBar/BaseFragment;", PluginsConstants.UPDATE, "Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;", "<init>", "(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;)V", "enableAutoUpdate", _UrlKt.FRAGMENT_ENCODE_SET, "installStarted", "isActivityGone", "hasSecondaryButton", "getDoneButtonText", _UrlKt.FRAGMENT_ENCODE_SET, "getTitleText", "getExtraBottomContentHeight", _UrlKt.FRAGMENT_ENCODE_SET, "addContentAfterDoneButton", _UrlKt.FRAGMENT_ENCODE_SET, "container", "Landroid/widget/FrameLayout;", "onDone", "restyle", "Landroid/widget/TextView;", "textSizeDp", _UrlKt.FRAGMENT_ENCODE_SET, "colorKey", "left", "top", "right", "bottom", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class PythonSdkUpdateAlert extends UpdateAppAlertDialog {
    private static final int AUTO_UPDATE_ROW_HEIGHT = 36;
    private static final int BUTTON_GAP = 12;
    public static final Companion Companion = null;
    private boolean enableAutoUpdate;
    private boolean installStarted;
    private final PythonPluginsEngine.Updater.Companion.PythonSdkUpdateInfo update;

    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/exteragram/messenger/plugins/ui/components/PythonSdkUpdateAlert$1", "Lorg/telegram/ui/ActionBar/BottomSheet$BottomSheetDelegate;", "canDismiss", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.plugins.ui.components.PythonSdkUpdateAlert$1, reason: invalid class name */
    public static final class AnonymousClass1 extends BottomSheet.BottomSheetDelegate {
        final /* synthetic */ PythonSdkUpdateAlert this$0;

        public AnonymousClass1(PythonSdkUpdateAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet.BottomSheetDelegate, org.telegram.ui.ActionBar.BottomSheet.BottomSheetDelegateInterface
        public boolean canDismiss() {
            if (PythonSdkUpdateAlert.access$getInstallStarted$p(this.this$0) == false) goto L5;
            return true;
        L5:
            if (PythonSdkUpdateAlert.access$isActivityGone(this.this$0) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/PythonSdkUpdateAlert$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "AUTO_UPDATE_ROW_HEIGHT", _UrlKt.FRAGMENT_ENCODE_SET, "BUTTON_GAP", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    public static Unit $r8$lambda$iuilDbtJf0qlamnol_4f5Zg4NB4(PythonSdkUpdateAlert r0, boolean r1) {
        r0.enableAutoUpdate = r1;
        return Unit.INSTANCE;
    }

    static {
        Companion = new Companion(null);
    }

    public PythonSdkUpdateAlert(BaseFragment r11, PythonPluginsEngine.Updater.Companion.PythonSdkUpdateInfo r12) {
        super(r11.getParentActivity(), r12, r11.getCurrentAccount());
        this.update = r12;
        this.textView.setSingleLine(false);
        this.textView.setEllipsize(null);
        this.textView.setGravity(1);
        TextView r1 = this.textView;
        int r3 = Theme.key_windowBackgroundWhiteBlackText;
        restyle(r1, 18.0f, r3, 40.0f, 24.0f, 40.0f, 0.0f);
        restyle(this.messageTextView, 14.0f, Theme.key_windowBackgroundWhiteGrayText, 21.0f, 4.0f, 21.0f, 0.0f);
        restyle(this.changelogTextView, 15.0f, r3, 22.0f, 20.0f, 22.0f, 12.0f);
        if (this.appUpdate.can_not_skip == false) goto L6;
        setCanDismissWithSwipe(false);
        this.allowNestedScroll = false;
        setCanDismissWithTouchOutside(false);
        setCancelable(false);
        setDelegate(new AnonymousClass1(this));
        return;
    }

    public static final /* synthetic */ boolean access$getInstallStarted$p(PythonSdkUpdateAlert r0) {
        return r0.installStarted;
    }

    public static final /* synthetic */ boolean access$isActivityGone(PythonSdkUpdateAlert r0) {
        return r0.isActivityGone();
    }

    private final boolean isActivityGone() {
        Context r2 = getContext();
        if ((r2 instanceof Activity) == false) goto L5;
        Activity r22 = (Activity) r2;
    L7:
        if (r22 != null) goto L10;
        return false;
    L10:
        if (r22.isFinishing() == false) goto L12;
        return true;
    L12:
        if (r22.isDestroyed() == true) goto L17;
        return false;
    L17:
        return true;
    L5:
        r22 = null;
        goto L7
    }

    private final void restyle(TextView r2, float r3, int r4, float r5, float r6, float r7, float r8) {
        r2.setTextSize(1, r3);
        r2.setTextColor(getThemedColor(r4));
        ((LinearLayout.LayoutParams) r2.getLayoutParams()).setMargins(AndroidUtilities.dp(r5), AndroidUtilities.dp(r6), AndroidUtilities.dp(r7), AndroidUtilities.dp(r8));
    }

    @Override // com.exteragram.messenger.updater.UpdateAppAlertDialog
    public void addContentAfterDoneButton(FrameLayout r10) {
        CheckBoxRow r0 = new CheckBoxRow(getContext(), LocaleController.getString(R.string.EnableAutoUpdate), false, this.resourcesProvider, 4, null);
        r0.setOnCheckedChange(new PythonSdkUpdateAlert$$ExternalSyntheticLambda0(this));
        r10.addView(r0, LayoutHelper.createFrame(-2, -2.0f, 81, 0.0f, 0.0f, 0.0f, 8.0f));
    }

    @Override // com.exteragram.messenger.updater.UpdateAppAlertDialog
    public String getDoneButtonText() {
        return LocaleController.getString(R.string.AppUpdateNow);
    }

    @Override // com.exteragram.messenger.updater.UpdateAppAlertDialog
    public int getExtraBottomContentHeight() {
        return 48;
    }

    @Override // com.exteragram.messenger.updater.UpdateAppAlertDialog
    public String getTitleText() {
        return LocaleController.getString(R.string.PluginsPySdkUpdate);
    }

    @Override // com.exteragram.messenger.updater.UpdateAppAlertDialog
    public boolean hasSecondaryButton() {
        return false;
    }

    @Override // com.exteragram.messenger.updater.UpdateAppAlertDialog
    public void onDone() {
        if (this.enableAutoUpdate == false) goto L16;
        PythonPluginsEngine.Updater.Companion.enableAutoUpdate();
    L16:
        PythonPluginsEngine.Updater.Companion.savePythonSdkArchive(this.update, true);     // Catch: IOException -> L8
    L14:
        this.installStarted = true;
        dismiss();
        return;
    L8:
        e = move-exception;
        String r2 = this.update.getChannel();
        TLRPC.Message r3 = this.update.getMessage();
        if (r3 == null) goto L12;
        Integer r32 = Integer.valueOf(r3.id);
    L13:
        FileLog.e("Failed to load python-plugins-sdk file (" + r2 + ", message id = " + r32 + ")", e);
        goto L14
    L12:
        r32 = null;
        goto L13
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public /* bridge */ /* synthetic */ void setLastVisible(boolean r1) {
        super.setLastVisible(r1);
    }
}
