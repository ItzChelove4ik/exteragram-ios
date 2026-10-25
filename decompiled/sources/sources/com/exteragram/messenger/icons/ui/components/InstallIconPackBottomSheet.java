package com.exteragram.messenger.icons.ui.components;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.components.CheckBoxRow;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.icons.IconPack;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.utils.text.LocaleUtils;
import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.EffectsTextView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\fB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet;", "Lorg/telegram/ui/ActionBar/BottomSheet;", "context", "Landroid/content/Context;", "iconPack", "Lcom/exteragram/messenger/icons/IconPack;", "installDelegate", "Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$InstallDelegate;", "<init>", "(Landroid/content/Context;Lcom/exteragram/messenger/icons/IconPack;Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$InstallDelegate;)V", "createView", "Landroid/view/View;", "InstallDelegate", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class InstallIconPackBottomSheet extends BottomSheet {
    private final IconPack iconPack;
    private final InstallDelegate installDelegate;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcom/exteragram/messenger/icons/ui/components/InstallIconPackBottomSheet$InstallDelegate;", _UrlKt.FRAGMENT_ENCODE_SET, "onInstall", _UrlKt.FRAGMENT_ENCODE_SET, "enable", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.UPDATE, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface InstallDelegate {
        void onInstall(boolean r1, boolean r2);
    }

    public static void $r8$lambda$16Mca43Svex8CFfxTTCwGRlDHWU(InstallIconPackBottomSheet r0, CheckBoxRow r1, boolean r2, View r3) {
        r0.dismiss();
        AndroidUtilities.runOnUIThread(new InstallIconPackBottomSheet$$ExternalSyntheticLambda5(r0, r1, r2), 200);
    }

    /* renamed from: $r8$lambda$DgxMV-L65kbfZyL8piBwuZ2k21o, reason: not valid java name */
    public static void m1457$r8$lambda$DgxMVL65kbfZyL8piBwuZ2k21o(InstallIconPackBottomSheet r2, CheckBoxRow r3, boolean r4) {
        InstallDelegate r22 = r2.installDelegate;
        boolean r0 = false;
        if (r3 != null) goto L5;
    L7:
        r22.onInstall(r0, r4);
        return;
    L5:
        if (r3.isChecked() != true) goto L7;
        r0 = true;
        goto L7
    }

    /* renamed from: $r8$lambda$ZgxHQQ4XbVm18tEb0C1btImd-l0, reason: not valid java name */
    public static void m1458$r8$lambda$ZgxHQQ4XbVm18tEb0C1btImdl0(InstallIconPackBottomSheet r3) {
        AndroidUtilities.runOnUIThread(new InstallIconPackBottomSheet$$ExternalSyntheticLambda1(r3), 1000);
    }

    public static void $r8$lambda$lYsEcB3VdRolPxkK_mP_jzm3nus(InstallIconPackBottomSheet r0) {
        File r02 = r0.iconPack.getLocation();
        if (r02 == null) goto L6;
        FilesKt.deleteRecursively(r02);
        return;
    }

    public static void $r8$lambda$sCAZGxaJOeaMSR2OcFHWcAF6CWM(InstallIconPackBottomSheet r2) {
        Utilities.globalQueue.postRunnable(new InstallIconPackBottomSheet$$ExternalSyntheticLambda4(r2));
    }

    public InstallIconPackBottomSheet(Context r2, IconPack r3, InstallDelegate r4) {
        super(r2, false);
        this.iconPack = r3;
        this.installDelegate = r4;
        fixNavigationBar();
        setCustomView(createView(r2));
        setOnDismissListener(new InstallIconPackBottomSheet$$ExternalSyntheticLambda0(this));
    }

    private final View createView(Context r15) {
        LinearLayout r0 = new LinearLayout(r15);
        r0.setOrientation(1);
        r0.setPadding(0, AndroidUtilities.dp(16.0f), 0, 0);
        IconPackPreviewView r2 = new IconPackPreviewView(r15);
        r2.setCircularMode(true);
        r2.setRefreshTime(3000);
        r2.setIconPack(this.iconPack);
        r0.addView(r2, LayoutHelper.createLinear(-1, -2, 1, 0, 0, 0, 16));
        TextView r22 = new TextView(r15);
        r22.setTextSize(1, 20.0f);
        r22.setTypeface(AndroidUtilities.bold());
        r22.setGravity(17);
        r22.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r22.setText(this.iconPack.getName());
        r0.addView(r22, LayoutHelper.createLinear(-1, -2, 24.0f, 0.0f, 24.0f, 0.0f));
        BaseFragment r23 = LaunchActivity.getSafeLastFragment();
        IconPack r4 = IconManager.INSTANCE.findPackById(this.iconPack.getId());
        if (r4 == null) goto L5;
        boolean r5 = true;
    L6:
        EffectsTextView r6 = new EffectsTextView(r15, this.resourcesProvider);
        r6.setGravity(1);
        r6.setTypeface(AndroidUtilities.regular());
        r6.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        r6.setLinkTextColor(getThemedColor(Theme.key_dialogTextLink));
        r6.setTextSize(1, 14.0f);
        r6.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
        SpannableStringBuilder r7 = new SpannableStringBuilder(LocaleController.getString(R.string.PluginVersion)).append(" ");
        int r8 = r7.length();
        if (r4 == null) goto L10;
        String r42 = r4.getVersion();
        r7.append(r42).append(" -> ").append(this.iconPack.getVersion());
        r7 = LocaleUtils.replaceArrows(r15, r7, R.drawable.msg_mini_arrow_mediathin);
        r7.setSpan(new StrikethroughSpan(), r8, r42.length() + r8, 33);
    L13:
        if (TextUtils.isEmpty(this.iconPack.getAuthor()) == true) goto L15;
        r7.append(" • ").append(LocaleUtils.formatWithUsernames(this.iconPack.getAuthor(), r23, new InstallIconPackBottomSheet$$ExternalSyntheticLambda2(this)));
    L15:
        r6.setText(r7);
        r0.addView(r6, LayoutHelper.createLinear(-1, -2, 24.0f, 4.0f, 24.0f, 24.0f));
        ButtonWithCounterView r24 = new ButtonWithCounterView(r15, true, this.resourcesProvider);
        r24.setRound();
        r24.setCount(LocaleController.formatPluralString("IconCount", this.iconPack.getIcons().size(), new Object[0]), false);
        if (r5 == false) goto L18;
        r24.setText(LocaleController.getString(R.string.UpdatePack), false);
    L19:
        r0.addView(r24, LayoutHelper.createLinear(-1, 48, 0, 16, 0, 16, 16));
        if (ExteraConfig.getIconPacksLayout().contains(this.iconPack.getId()) == true) goto L22;
        CheckBoxRow r3 = new CheckBoxRow(r15, LocaleController.getString(R.string.EnableAfterInstallation), true, this.resourcesProvider);
        r0.addView(r3, LayoutHelper.createLinear(-2, -2, 1, 0, 0, 0, 8));
    L23:
        r24.setOnClickListener(new InstallIconPackBottomSheet$$ExternalSyntheticLambda3(this, r3, r5));
        return r0;
    L22:
        r3 = null;
        goto L23
    L18:
        r24.setText(LocaleController.getString(R.string.InstallPack), false);
        goto L19
    L10:
        if (TextUtils.isEmpty(this.iconPack.getVersion()) == true) goto L13;
        r7.append(this.iconPack.getVersion());
        goto L13
    L5:
        r5 = false;
        goto L6
    }
}
