package com.exteragram.messenger.plugins.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.Html;
import android.text.SpannableString;
import android.view.View;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.PythonPluginsEngine;
import com.exteragram.messenger.plugins.ui.components.UnknownSourcesBottomSheet;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import com.exteragram.messenger.preferences.utils.SettingsRegistry;
import com.exteragram.messenger.utils.text.LocaleUtils;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.browser.Browser;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\"B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J5\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0016\u0010\u000f\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0010\"\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0002\u0010\u0012J(\u0010\u0013\u001a\u00020\n2\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0014J0\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0014¨\u0006#"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;", "<init>", "()V", "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "onFragmentCreate", _UrlKt.FRAGMENT_ENCODE_SET, "onFragmentDestroy", _UrlKt.FRAGMENT_ENCODE_SET, "didReceivedNotification", "id", _UrlKt.FRAGMENT_ENCODE_SET, "account", "args", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(II[Ljava/lang/Object;)V", "fillItems", PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "Lkotlin/collections/ArrayList;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "Landroid/view/View;", "position", "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "PreferenceItem", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPluginsInfoActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginsInfoActivity.kt\ncom/exteragram/messenger/plugins/ui/PluginsInfoActivity\n+ 2 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n*L\n1#1,318:1\n41#2,12:319\n*S KotlinDebug\n*F\n+ 1 PluginsInfoActivity.kt\ncom/exteragram/messenger/plugins/ui/PluginsInfoActivity\n*L\n277#1:319,12\n*E\n"})
/* loaded from: classes.dex */
public final class PluginsInfoActivity extends BasePreferencesActivity implements NotificationCenter.NotificationCenterDelegate {

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0010\u001a\u00020\u0011j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0012"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "DEVELOPER_MODE", "COMPACT_VIEW", "SAFE_MODE", "SDK_AUTO_UPDATE", "SDK_BETA_VERSIONS", "CHECK_SDK_UPDATES", "RESTORE_SDK_FROM_APK", "DOCUMENTATION", "TRUSTED_PLUGINS", "PLUGINS_DISABLE_ART_OPTS", "SDK_HEADER", "UNKNOWN_SOURCES", "getId", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public enum PreferenceItem extends Enum<PreferenceItem> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ PreferenceItem[] $VALUES = null;
        public static final PreferenceItem CHECK_SDK_UPDATES = null;
        public static final PreferenceItem COMPACT_VIEW = null;
        public static final PreferenceItem DEVELOPER_MODE = null;
        public static final PreferenceItem DOCUMENTATION = null;
        public static final PreferenceItem PLUGINS_DISABLE_ART_OPTS = null;
        public static final PreferenceItem RESTORE_SDK_FROM_APK = null;
        public static final PreferenceItem SAFE_MODE = null;
        public static final PreferenceItem SDK_AUTO_UPDATE = null;
        public static final PreferenceItem SDK_BETA_VERSIONS = null;
        public static final PreferenceItem SDK_HEADER = null;
        public static final PreferenceItem TRUSTED_PLUGINS = null;
        public static final PreferenceItem UNKNOWN_SOURCES = null;

        private static final /* synthetic */ PreferenceItem[] $values() {
            return new PreferenceItem[]{DEVELOPER_MODE, COMPACT_VIEW, SAFE_MODE, SDK_AUTO_UPDATE, SDK_BETA_VERSIONS, CHECK_SDK_UPDATES, RESTORE_SDK_FROM_APK, DOCUMENTATION, TRUSTED_PLUGINS, PLUGINS_DISABLE_ART_OPTS, SDK_HEADER, UNKNOWN_SOURCES};
        }

        static {
            DEVELOPER_MODE = new PreferenceItem("DEVELOPER_MODE", 0);
            COMPACT_VIEW = new PreferenceItem("COMPACT_VIEW", 1);
            SAFE_MODE = new PreferenceItem("SAFE_MODE", 2);
            SDK_AUTO_UPDATE = new PreferenceItem("SDK_AUTO_UPDATE", 3);
            SDK_BETA_VERSIONS = new PreferenceItem("SDK_BETA_VERSIONS", 4);
            CHECK_SDK_UPDATES = new PreferenceItem("CHECK_SDK_UPDATES", 5);
            RESTORE_SDK_FROM_APK = new PreferenceItem("RESTORE_SDK_FROM_APK", 6);
            DOCUMENTATION = new PreferenceItem("DOCUMENTATION", 7);
            TRUSTED_PLUGINS = new PreferenceItem("TRUSTED_PLUGINS", 8);
            PLUGINS_DISABLE_ART_OPTS = new PreferenceItem("PLUGINS_DISABLE_ART_OPTS", 9);
            SDK_HEADER = new PreferenceItem("SDK_HEADER", 10);
            UNKNOWN_SOURCES = new PreferenceItem("UNKNOWN_SOURCES", 11);
            PreferenceItem[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        PreferenceItem(String r1, int r2) {
        }

        public static EnumEntries<PreferenceItem> getEntries() {
            return $ENTRIES;
        }

        public static PreferenceItem valueOf(String r1) {
            return (PreferenceItem) Enum.valueOf(PreferenceItem.class, r1);
        }

        public static PreferenceItem[] values() {
            return (PreferenceItem[]) $VALUES.clone();
        }

        public final int getId() {
            return ordinal() + 1;
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[PreferenceItem.values().length];
            r0[PreferenceItem.DEVELOPER_MODE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L12
        L21:
            r0[PreferenceItem.COMPACT_VIEW.ordinal()] = 2;     // Catch: NoSuchFieldError -> L13
        L31:
            r0[PreferenceItem.UNKNOWN_SOURCES.ordinal()] = 3;     // Catch: NoSuchFieldError -> L14
        L23:
            r0[PreferenceItem.SAFE_MODE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L15
        L25:
            r0[PreferenceItem.PLUGINS_DISABLE_ART_OPTS.ordinal()] = 5;     // Catch: NoSuchFieldError -> L16
        L19:
            r0[PreferenceItem.SDK_AUTO_UPDATE.ordinal()] = 6;     // Catch: NoSuchFieldError -> L17
        L27:
            r0[PreferenceItem.SDK_BETA_VERSIONS.ordinal()] = 7;     // Catch: NoSuchFieldError -> L18
        L10:
            $EnumSwitchMapping$0 = r0;
        }
    }

    /* renamed from: $r8$lambda$75FvA-8Wx6_ammvo-Vdr0N3hTJI, reason: not valid java name */
    public static void m1594$r8$lambda$75FvA8Wx6_ammvoVdr0N3hTJI(SharedPreferences r2, boolean r3) {
        ExteraConfig.setPluginsSafeMode(r3);
        SharedPreferences.Editor r22 = r2.edit();
        if (r3 == false) goto L5;
        r22.putString("crashed_plugin_id", "manual!");
    L6:
        r22.apply();
        PluginsController.Companion.getInstance().restart(r3);
        return;
    L5:
        r22.remove("crashed_plugin_id");
        goto L6
    }

    public static void $r8$lambda$GLVYn2y9h85S4ccCKuNG76GBJNM(boolean r0) {
        ExteraConfig.setPluginsUnknownSources(r0);
    }

    public static void $r8$lambda$MNahqCzadSU3IcAO5MTHf7QGiw4(PluginsInfoActivity r1, boolean r2) {
        ExteraConfig.setPluginsDevMode(r2);
        PluginsController.Companion.getInstance().checkDevServers();
        BulletinFactory r12 = BulletinFactory.of(r1);
        if (ExteraConfig.getPluginsDevMode() == false) goto L5;
        int r22 = R.raw.contact_check;
    L7:
        if (ExteraConfig.getPluginsDevMode() == false) goto L9;
        int r0 = R.string.PluginsDevServerLaunched;
    L10:
        r12.createSimpleBulletin(r22, LocaleController.getString(r0)).show();
        return;
    L9:
        r0 = R.string.PluginsDevServerStopped;
        goto L10
    L5:
        r22 = R.raw.error;
        goto L7
    }

    /* renamed from: $r8$lambda$ZKxgRHkSxBXEH09P-5GrwvXrtn0, reason: not valid java name */
    public static void m1595$r8$lambda$ZKxgRHkSxBXEH09P5GrwvXrtn0(boolean r1) {
        ExteraConfig.setPluginsPySdkBetaVersions(r1);
        PythonPluginsEngine.Updater.Companion.checkUpdates(true);
    }

    public static void $r8$lambda$_nJnP4umXGYo32iu7HFZxPD0DwA(boolean r0) {
        ExteraConfig.setPluginsUnknownSources(r0);
    }

    public static Unit $r8$lambda$jZWitV_uc5kJ6EDjZA6AlqmjReQ(PluginsInfoActivity r1, UItem r2) {
        r1.toggleBooleanSettingAndRefresh$TMessagesProj(r2, new PluginsInfoActivity$$ExternalSyntheticLambda0());
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$jyKoHtubfEZWYmGRcWJUtJHu3g0(boolean r2) {
        ExteraConfig.setPluginsCompactView(r2);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.reloadInterface, new Object[0]);
    }

    public static void $r8$lambda$l326Pi8wPZIW7xHkIdztqb2hsTI(boolean r0) {
        ExteraConfig.setPluginsPySdkAutoUpdate(r0);
    }

    public static void $r8$lambda$oRmSB9poUPcKGSoweQ5zECk8nLc(PluginsInfoActivity r0, boolean r1) {
        ExteraConfig.setPluginsDisableArtOpts(r1);
        PluginsController.Companion.applyArtOpts();
        r0.showRestartBulletin();
    }

    public PluginsInfoActivity() {
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.pluginsPySdkInfoChanged) goto L6;
        getListView$TMessagesProj().adapter.update(true);
        return;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r6, UniversalAdapter r7) {
        r6.add(UItem.asHeader(LocaleController.getString(R.string.Settings)));
        UItem r72 = UItem.asCheck(PreferenceItem.DEVELOPER_MODE.getId(), LocaleController.getString(R.string.PluginsDevMode), R.drawable.msg_settings).setChecked(ExteraConfig.getPluginsDevMode());
        boolean r2 = true;
        if (ExteraConfig.getPluginsEngine() == true) goto L5;
    L7:
        boolean r0 = false;
    L8:
        r6.add(r72.setEnabled(r0).setSearchable(this).setLinkAlias("pluginsDeveloperMode", this));
        r6.add(UItem.asCheck(PreferenceItem.COMPACT_VIEW.getId(), LocaleController.getString(R.string.PluginsCompactView), R.drawable.msg_topics).setChecked(ExteraConfig.getPluginsCompactView()).setEnabled(ExteraConfig.getPluginsEngine()).setSearchable(this).setLinkAlias("pluginsCompactView", this));
        r6.add(UItem.asCheck(PreferenceItem.PLUGINS_DISABLE_ART_OPTS.getId(), LocaleController.getString(R.string.PluginsDisableArt), R.drawable.msg_link2).setChecked(ExteraConfig.getPluginsDisableArtOpts()).setEnabled(ExteraConfig.getPluginsEngine()).setSearchable(this).setValue(LocaleController.getString(R.string.PluginsDisableArtInfo)).setMultiline(true).setLinkAlias("pluginsDisableArtOpts", this));
        r6.add(UItem.asCheck(PreferenceItem.UNKNOWN_SOURCES.getId(), LocaleController.getString(R.string.PluginsUnknownSources), R.drawable.msg_download).setChecked(ExteraConfig.getPluginsUnknownSources()).setEnabled(ExteraConfig.getPluginsEngine()).setSearchable(this).setValue(LocaleController.getString(R.string.PluginsUnknownSourcesInfo2)).setMultiline(true).setLinkAlias("pluginsUnknownSources", this));
        r6.add(UItem.asCheck(PreferenceItem.SAFE_MODE.getId(), LocaleController.getString(R.string.PluginsSafeMode), R.drawable.msg_secret).setChecked(ExteraConfig.getPluginsSafeMode()).setSearchable(this).setLinkAlias("pluginsSafeMode", this));
        r6.add(UItem.asShadow(LocaleController.getString(R.string.PluginsSafeModeInfo2)));
        int r73 = PreferenceItem.SDK_HEADER.getId();
        CharSequence r3 = "Python SDK";
        if (SettingsRegistry.markAsNewFeature("Plugins-Python-SDK") == false) goto L11;
        r3 = LocaleUtils.applyNewSpan("Python SDK");
    L11:
        r6.add(UItem.asHeader(r73, r3));
        UItem r74 = UItem.asCheck(PreferenceItem.SDK_AUTO_UPDATE.getId(), LocaleController.getString(R.string.PluginsPySdkAutoUpdate)).setChecked(ExteraConfig.getPluginsPySdkAutoUpdate()).setSearchable(this);
        PythonPluginsEngine.Updater.Companion r02 = PythonPluginsEngine.Updater.Companion;
        if (r02.getStatus() >= 3) goto L14;
        boolean r32 = true;
    L15:
        r6.add(r74.setEnabled(r32).setValue(r02.getStateString()).setMultiline(true).setLinkAlias("pluginsPySdkAutoUpdate", this));
        UItem r75 = UItem.asCheck(PreferenceItem.SDK_BETA_VERSIONS.getId(), LocaleController.getString(R.string.PluginsPySdkEnableBetaVersion)).setChecked(ExteraConfig.getPluginsPySdkBetaVersions()).setSearchable(this);
        if (r02.getStatus() >= 3) goto L18;
        boolean r33 = true;
    L19:
        r6.add(r75.setEnabled(r33).setLinkAlias("pluginsPySdkBetaVersions", this));
        UItem r76 = UItem.asButton(PreferenceItem.CHECK_SDK_UPDATES.getId(), LocaleController.getString(R.string.PluginsPySdkCheckUpdates)).accent().setSearchable(this);
        if (r02.getStatus() < 3) goto L23;
        r2 = false;
    L23:
        r6.add(r76.setEnabled(r2).setIcon(R.drawable.msg_retry).setLinkAlias("pluginsPySdkCheckUpdates", this));
        if (ExteraConfig.getPluginsDevMode() == true) goto L26;
    L30:
        r6.add(UItem.asShadow());
        r6.add(UItem.asHeader(LocaleController.getString(R.string.Links)));
        r6.add(UItem.asButton(PreferenceItem.DOCUMENTATION.getId(), LocaleController.getString(R.string.PluginsDocumentation)).setSearchable(this).setIcon(R.drawable.menu_intro).setLinkAlias("pluginsDocumentation", this));
        r6.add(UItem.asButton(PreferenceItem.TRUSTED_PLUGINS.getId(), LocaleController.getString(R.string.PluginsTrusted)).accent().setIcon(R.drawable.msg2_policy).setSearchable(this).setLinkAlias("trustedPlugins", this));
        r6.add(UItem.asShadow(LocaleUtils.formatWithHtmlURLs(new SpannableString(Html.fromHtml(LocaleController.getString(R.string.PluginsPoweredBy), 0)))));
        return;
    L26:
        if (ExteraConfig.getPluginsEngine() == true) goto L30;
        if (r02.isSdkFromApk() == true) goto L30;
        r6.add(UItem.asButton(PreferenceItem.RESTORE_SDK_FROM_APK.getId(), LocaleController.getString(R.string.RestoreSdkFromApk)).red().setIcon(R.drawable.msg_reset));
        goto L30
    L18:
        r33 = false;
        goto L19
    L14:
        r32 = false;
        goto L15
    L5:
        if (ExteraConfig.getPluginsSafeMode() == true) goto L7;
        r0 = true;
        goto L8
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return LocaleController.getString(R.string.PluginsEngine);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        int r32 = r1.id;
        if (r32 > 0) goto L5;
        return;
    L5:
        if (r32 > PreferenceItem.getEntries().size()) goto L59;
        PreferenceItem r33 = PreferenceItem.getEntries().get(r1.id - 1);
        if ((r2 instanceof TextCheckCell) == true) goto L10;
    L38:
        PreferenceItem r12 = PreferenceItem.DOCUMENTATION;
        if (r33 != r12) goto L41;
    L51:
        Activity r0 = getParentActivity();
        if (r33 != r12) goto L54;
        String r13 = "https://plugins.exteragram.app/";
    L55:
        Browser.openUrl(r0, r13);
        return;
    L54:
        r13 = "https://t.me/addlist/pPhOtEq00KhjYTc6";
        goto L55
    L41:
        if (r33 == PreferenceItem.TRUSTED_PLUGINS) goto L51;
        if (r33 != PreferenceItem.CHECK_SDK_UPDATES) goto L48;
        PythonPluginsEngine.Updater.Companion.checkUpdates(true);
        return;
    L48:
        if (r33 != PreferenceItem.RESTORE_SDK_FROM_APK) goto L58;
        PythonPluginsEngine.Updater.Companion.restoreSdkFromApk();
        BulletinFactory.of(this).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.RestartRequired)).show();
        getListView$TMessagesProj().adapter.update(true);
        return;
    L58:
        return;
    L10:
        if (ExteraConfig.getPluginsEngine() == true) goto L18;
        if (r33 == PreferenceItem.SAFE_MODE) goto L18;
        if (r33 == PreferenceItem.SDK_AUTO_UPDATE) goto L18;
        if (r33 != PreferenceItem.SDK_BETA_VERSIONS) goto L38;
    L18:
        switch(WhenMappings.$EnumSwitchMapping$0[r33.ordinal()]) {
            case 1: goto L36;
            case 2: goto L34;
            case 3: goto L29;
            case 4: goto L26;
            case 5: goto L24;
            case 6: goto L22;
            case 7: goto L20;
            default: goto L60;
        };
    L20:
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new PluginsInfoActivity$$ExternalSyntheticLambda8());
        return;
    L22:
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new PluginsInfoActivity$$ExternalSyntheticLambda7());
        return;
    L24:
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new PluginsInfoActivity$$ExternalSyntheticLambda6(this));
        return;
    L26:
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new PluginsInfoActivity$$ExternalSyntheticLambda5(PluginsController.Companion.getInstance().getPreferences()));
        return;
    L34:
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new PluginsInfoActivity$$ExternalSyntheticLambda2());
        return;
    L36:
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new PluginsInfoActivity$$ExternalSyntheticLambda1(this));
        return;
    L60:
        return;
    L29:
        if (ExteraConfig.getPluginsUnknownSources() == false) goto L32;
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new PluginsInfoActivity$$ExternalSyntheticLambda3());
        return;
    L32:
        new UnknownSourcesBottomSheet(this, new PluginsInfoActivity$$ExternalSyntheticLambda4(this, r1)).show();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.pluginsPySdkInfoChanged);
        PythonPluginsEngine.Updater.Companion.setNotifyWhenChangeStatus(true);
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.pluginsPySdkInfoChanged);
        PythonPluginsEngine.Updater.Companion r3 = PythonPluginsEngine.Updater.Companion;
        r3.setNotifyWhenChangeStatus(false);
        if (r3.getStatus() != 2) goto L6;
        r3.setStatus(0);
        return;
    }
}
