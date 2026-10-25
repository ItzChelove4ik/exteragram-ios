package com.exteragram.messenger.pillstack.ui.pills.system;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.exteragram.messenger.pillstack.core.PillType;
import com.exteragram.messenger.pillstack.ui.PillStackPreferencesActivity;
import com.exteragram.messenger.pillstack.ui.pills.BasePill;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ProxyListActivity;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class ProxyPill extends BasePill implements NotificationCenter.NotificationCenterDelegate {
    private final ImageView iconView;
    private int lastAccount;
    private final LinearLayout layout;
    private final AnimatedTextView textView;

    /* renamed from: $r8$lambda$Bw4AuW8-no2h670OQT3qE3zO-WE, reason: not valid java name */
    public static /* synthetic */ void m1540$r8$lambda$Bw4AuW8no2h670OQT3qE3zOWE(BaseFragment r1) {
        r1.presentFragment(new PillStackPreferencesActivity());
    }

    public ProxyPill(Context r13, Theme.ResourcesProvider r14) {
        super(r13, r14);
        LinearLayout r142 = new LinearLayout(r13);
        this.layout = r142;
        r142.setOrientation(0);
        r142.setGravity(17);
        r142.setMinimumWidth(AndroidUtilities.dp(48.0f));
        r142.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(10.0f), 0);
        if (LocaleController.isRTL == false) goto L5;
        int r1 = 3;
    L6:
        addView(r142, LayoutHelper.createFrame(-2, 28, r1 | 16));
        ImageView r12 = new ImageView(r13);
        this.iconView = r12;
        r12.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        r142.addView(r12, LayoutHelper.createLinear(16, 16, 16, 0, 0, 2, 0));
        AnimatedTextView r15 = new AnimatedTextView(r13, true, true, true);
        this.textView = r15;
        r15.setTextSize(AndroidUtilities.dp(13.0f));
        r15.setIncludeFontPadding(false);
        r15.setTypeface(AndroidUtilities.bold());
        r15.adaptWidth = true;
        r142.addView(r15, LayoutHelper.createLinear(-2, -2, 16));
        setLoadingTargetView(r142);
        updateColors();
        ScaleStateListAnimator.apply(r142);
        onUpdateData(false);
        return;
    L5:
        r1 = 5;
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.proxySettingsChanged) goto L5;
    L10:
        onUpdateData(true);
        return;
    L5:
        if (r1 == NotificationCenter.proxyPingUpdated) goto L10;
        if (r1 == NotificationCenter.didUpdateConnectionState) goto L10;
    }

    @Override // android.view.View
    public void drawableHotspotChanged(float r3, float r4) {
        if (this.loading == false) goto L5;
        return;
    L5:
        super.drawableHotspotChanged(r3, r4);
        this.layout.drawableHotspotChanged(r3 - r0.getLeft(), r4 - this.layout.getTop());
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public int getPillId() {
        return PillType.PROXY.getId();
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public long getRefreshInterval() {
        return 0;
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        onUpdateData(true);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.proxySettingsChanged);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.proxyPingUpdated);
        int r0 = UserConfig.selectedAccount;
        this.lastAccount = r0;
        NotificationCenter.getInstance(r0).addObserver(this, NotificationCenter.didUpdateConnectionState);
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.proxySettingsChanged);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.proxyPingUpdated);
        NotificationCenter.getInstance(this.lastAccount).removeObserver(this, NotificationCenter.didUpdateConnectionState);
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public void onPillClicked() {
        BaseFragment r1 = LaunchActivity.getSafeLastFragment();
        if (r1 == null) goto L6;
        r1.presentFragment(new ProxyListActivity());
        return;
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public boolean onPillLongClicked() {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 == null) goto L6;
        ItemOptions.makeOptions(r0, this).add(R.drawable.msg_settings, LocaleController.getString(R.string.Settings), new ProxyPill$$ExternalSyntheticLambda0(r0)).setDrawScrim(false).setDimAlpha(0).show();
        return true;
    L6:
        return false;
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public void onUpdateData(boolean r10) {
        boolean r0 = SharedConfig.isProxyEnabled();
        int r1 = ConnectionsManager.getInstance(UserConfig.selectedAccount).getConnectionState();
        if (r1 != 3) goto L5;
    L8:
        boolean r12 = true;
    L10:
        if (this.textView.getText() == null) goto L12;
        String r2 = this.textView.getText().toString();
    L13:
        if (r0 == false) goto L23;
        SharedConfig.ProxyInfo r02 = SharedConfig.currentProxy;
        if (r02 == null) goto L23;
        if (r12 == false) goto L22;
        long r03 = Utilities.clamp(r02.ping, 9999, 0);
        this.iconView.setImageResource(R.drawable.drawer_proxy_on);
        if (r03 <= 0) goto L20;
        String r04 = LocaleController.formatString(R.string.NavigationDrawerProxyPingShort, new Object[]{Long.valueOf(r03)});
    L21:
        stopLoading();
    L24:
        if (r10 == false) goto L26;
    L27:
        if (r10 == false) goto L29;
        animateSizeChange();
    L29:
        this.textView.setText(r04, r10);
    L30:
        updateColors();
        return;
    L26:
        if (TextUtils.equals(r2, r04) == true) goto L30;
    L20:
        r04 = LocaleController.getString(R.string.MenuProxyConnected);
        goto L21
    L22:
        this.iconView.setImageResource(R.drawable.drawer_proxy_off);
        r04 = LocaleController.getString(R.string.MenuProxyConnecting);
        startLoading();
    L23:
        this.iconView.setImageResource(R.drawable.drawer_proxy_off);
        r04 = LocaleController.getString(R.string.Proxy);
        stopLoading();
        goto L24
    L12:
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L13
    L5:
        if (r1 == 5) goto L8;
        r12 = false;
        goto L10
    }

    @Override // android.view.View
    public void setPressed(boolean r2) {
        if (this.loading == false) goto L5;
        r2 = false;
    L5:
        super.setPressed(r2);
        this.layout.setPressed(r2);
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public void updateColors() {
        boolean r0 = SharedConfig.isProxyEnabled();
        int r1 = ConnectionsManager.getInstance(UserConfig.selectedAccount).getConnectionState();
        if (r1 != 3) goto L5;
    L8:
        boolean r12 = true;
    L9:
        if (r0 == true) goto L11;
    L14:
        int r02 = getThemedColor(Theme.key_windowBackgroundWhiteBlackText, 0.75f);
    L16:
        if (Theme.isCurrentThemeDark() == false) goto L18;
        int r13 = getThemedColor(Theme.key_windowBackgroundWhite);
    L19:
        this.layout.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(14.0f), r13, Theme.multAlpha(r02, 0.1f)));
        this.textView.setTextColor(r02);
        this.iconView.setColorFilter(new PorterDuffColorFilter(r02, PorterDuff.Mode.MULTIPLY));
        updateLoadingColors();
        return;
    L18:
        r13 = Theme.multAlpha(r02, 0.09f);
        goto L19
    L11:
        if (SharedConfig.currentProxy == null) goto L14;
        if (r12 == false) goto L14;
        r02 = getThemedColor(Theme.key_windowBackgroundWhiteGreenText);
        goto L16
    L5:
        if (r1 == 5) goto L8;
        r12 = false;
        goto L9
    }
}
