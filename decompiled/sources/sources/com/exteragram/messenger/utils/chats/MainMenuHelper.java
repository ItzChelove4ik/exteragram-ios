package com.exteragram.messenger.utils.chats;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.MainMenuItem;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import com.exteragram.messenger.components.QRCodeSheet;
import com.exteragram.messenger.export.api.ApiWrap$HistoryMessageMarkupButton$$ExternalSyntheticRecord0;
import com.exteragram.messenger.feed.ui.FeedActivity;
import com.exteragram.messenger.pillstack.ui.pills.crypto.RatePill$$ExternalSyntheticLambda1;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.hooks.MenuItemRecord;
import com.exteragram.messenger.plugins.ui.PluginsActivity;
import com.exteragram.messenger.plugins.utils.ClassProxy$FieldSpec$$ExternalSyntheticRecord0;
import com.exteragram.messenger.plugins.utils.MenuContextBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.IntPredicate;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionIntroActivity;
import org.telegram.ui.CallLogActivity;
import org.telegram.ui.CameraScanActivity;
import org.telegram.ui.ChannelCreateActivity;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.ContactsActivity;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.GroupCreateActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.SettingsActivity;
import org.telegram.ui.WebAppDisclaimerAlert;
import org.telegram.ui.bots.BotWebViewSheet;
import org.telegram.ui.web.SearchEngine;

/* loaded from: classes4.dex */
public abstract class MainMenuHelper {

    /* renamed from: com.exteragram.messenger.utils.chats.MainMenuHelper$1, reason: invalid class name */
    public class AnonymousClass1 implements CameraScanActivity.CameraScanActivityDelegate {
        public static /* synthetic */ void $r8$lambda$PXFIpgdtIGghglRNcVl91ndQqRc(String r3, Runnable r4) {
            AndroidUtilities.runOnUIThread(new MainMenuHelper$1$$ExternalSyntheticLambda1(r3), 150);
            r4.run();
        }

        public static /* synthetic */ void $r8$lambda$wwDUSw59NnCN0TJApzf27CVLJhA(String r2) {
            BaseFragment r0 = LaunchActivity.getSafeLastFragment();
            if (r0 == null) goto L6;
            new QRCodeSheet(r0, r2).show();
            return;
        }

        public AnonymousClass1() {
        }

        @Override // org.telegram.ui.CameraScanActivity.CameraScanActivityDelegate
        public boolean processQr(String r1, Runnable r2) {
            AndroidUtilities.runOnUIThread(new MainMenuHelper$1$$ExternalSyntheticLambda0(r1, r2), 600);
            return true;
        }
    }

    /* renamed from: com.exteragram.messenger.utils.chats.MainMenuHelper$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$MainMenuItem = null;

        static {
            int[] r0 = new int[MainMenuItem.values().length];
            $SwitchMap$com$exteragram$messenger$MainMenuItem = r0;
            r0[MainMenuItem.BOTS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L17
        L34:
            $SwitchMap$com$exteragram$messenger$MainMenuItem[MainMenuItem.PLUGINS.ordinal()] = 2;     // Catch: NoSuchFieldError -> L18
        L40:
            $SwitchMap$com$exteragram$messenger$MainMenuItem[MainMenuItem.PROFILE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L19
        L54:
            $SwitchMap$com$exteragram$messenger$MainMenuItem[MainMenuItem.ARCHIVE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L20
        L36:
            $SwitchMap$com$exteragram$messenger$MainMenuItem[MainMenuItem.NEW_GROUP.ordinal()] = 5;     // Catch: NoSuchFieldError -> L21
        L42:
            $SwitchMap$com$exteragram$messenger$MainMenuItem[MainMenuItem.CONTACTS.ordinal()] = 6;     // Catch: NoSuchFieldError -> L22
        L46:
            $SwitchMap$com$exteragram$messenger$MainMenuItem[MainMenuItem.CALLS.ordinal()] = 7;     // Catch: NoSuchFieldError -> L23
        L50:
            $SwitchMap$com$exteragram$messenger$MainMenuItem[MainMenuItem.NEW_CHANNEL.ordinal()] = 8;     // Catch: NoSuchFieldError -> L24
        L32:
            $SwitchMap$com$exteragram$messenger$MainMenuItem[MainMenuItem.SAVED.ordinal()] = 9;     // Catch: NoSuchFieldError -> L25
        L38:
            $SwitchMap$com$exteragram$messenger$MainMenuItem[MainMenuItem.FEED.ordinal()] = 10;     // Catch: NoSuchFieldError -> L26
        L44:
            $SwitchMap$com$exteragram$messenger$MainMenuItem[MainMenuItem.SETTINGS.ordinal()] = 11;     // Catch: NoSuchFieldError -> L27
        L48:
            $SwitchMap$com$exteragram$messenger$MainMenuItem[MainMenuItem.BROWSER.ordinal()] = 12;     // Catch: NoSuchFieldError -> L28
        L30:
            $SwitchMap$com$exteragram$messenger$MainMenuItem[MainMenuItem.QR.ordinal()] = 13;     // Catch: NoSuchFieldError -> L29
            return;
        }
    }

    public static final class AttachMenuBotInfo extends RecordTag {
        private final TLRPC.TL_attachMenuBot bot;
        private final int iconRes;
        private final Runnable onClick;
        private final Runnable onLongClick;
        private final CharSequence text;

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof AttachMenuBotInfo) == false) goto L16;
            AttachMenuBotInfo r32 = (AttachMenuBotInfo) r3;
            if (this.iconRes == r32.iconRes) goto L7;
            return false;
        L7:
            if (Objects.equals(this.text, r32.text) == true) goto L9;
            return false;
        L9:
            if (Objects.equals(this.bot, r32.bot) == true) goto L11;
            return false;
        L11:
            if (Objects.equals(this.onClick, r32.onClick) == true) goto L13;
            return false;
        L13:
            if (Objects.equals(this.onLongClick, r32.onLongClick) == false) goto L22;
            return true;
        L22:
            return false;
        L16:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{Integer.valueOf(this.iconRes), this.text, this.bot, this.onClick, this.onLongClick};
        }

        public AttachMenuBotInfo(int r1, CharSequence r2, TLRPC.TL_attachMenuBot r3, Runnable r4, Runnable r5) {
            this.iconRes = r1;
            this.text = r2;
            this.bot = r3;
            this.onClick = r4;
            this.onLongClick = r5;
        }

        public TLRPC.TL_attachMenuBot bot() {
            return this.bot;
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public final int hashCode() {
            return ApiWrap$HistoryMessageMarkupButton$$ExternalSyntheticRecord0.m(this.iconRes, this.text, this.bot, this.onClick, this.onLongClick);
        }

        public int iconRes() {
            return this.iconRes;
        }

        public Runnable onClick() {
            return this.onClick;
        }

        public Runnable onLongClick() {
            return this.onLongClick;
        }

        public CharSequence text() {
            return this.text;
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), AttachMenuBotInfo.class, "iconRes;text;bot;onClick;onLongClick");
        }
    }

    public static final class MenuContext extends RecordTag {
        private final Runnable archiveClick;
        private final int currentAccount;
        private final BaseFragment fragment;
        private final Map<String, Object> pluginContextData;

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof MenuContext) == false) goto L14;
            MenuContext r32 = (MenuContext) r3;
            if (this.currentAccount == r32.currentAccount) goto L7;
            return false;
        L7:
            if (Objects.equals(this.fragment, r32.fragment) == true) goto L9;
            return false;
        L9:
            if (Objects.equals(this.archiveClick, r32.archiveClick) == true) goto L11;
            return false;
        L11:
            if (Objects.equals(this.pluginContextData, r32.pluginContextData) == false) goto L19;
            return true;
        L19:
            return false;
        L14:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{Integer.valueOf(this.currentAccount), this.fragment, this.archiveClick, this.pluginContextData};
        }

        public MenuContext(int r1, BaseFragment r2, Runnable r3, Map<String, Object> r4) {
            this.currentAccount = r1;
            this.fragment = r2;
            this.archiveClick = r3;
            this.pluginContextData = r4;
        }

        public Runnable archiveClick() {
            return this.archiveClick;
        }

        public int currentAccount() {
            return this.currentAccount;
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public BaseFragment fragment() {
            return this.fragment;
        }

        public final int hashCode() {
            return ClassProxy$FieldSpec$$ExternalSyntheticRecord0.m(this.currentAccount, this.fragment, this.archiveClick, this.pluginContextData);
        }

        public Map<String, Object> pluginContextData() {
            return this.pluginContextData;
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), MenuContext.class, "currentAccount;fragment;archiveClick;pluginContextData");
        }
    }

    public static final class MenuItemInfo extends RecordTag {
        private final int iconRes;
        private final Runnable onClick;
        private final Runnable onLongClick;
        private final CharSequence text;

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof MenuItemInfo) == false) goto L14;
            MenuItemInfo r32 = (MenuItemInfo) r3;
            if (this.iconRes == r32.iconRes) goto L7;
            return false;
        L7:
            if (Objects.equals(this.text, r32.text) == true) goto L9;
            return false;
        L9:
            if (Objects.equals(this.onClick, r32.onClick) == true) goto L11;
            return false;
        L11:
            if (Objects.equals(this.onLongClick, r32.onLongClick) == false) goto L19;
            return true;
        L19:
            return false;
        L14:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{Integer.valueOf(this.iconRes), this.text, this.onClick, this.onLongClick};
        }

        public MenuItemInfo(int r1, CharSequence r2, Runnable r3, Runnable r4) {
            this.iconRes = r1;
            this.text = r2;
            this.onClick = r3;
            this.onLongClick = r4;
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public final int hashCode() {
            return ClassProxy$FieldSpec$$ExternalSyntheticRecord0.m(this.iconRes, this.text, this.onClick, this.onLongClick);
        }

        public int iconRes() {
            return this.iconRes;
        }

        public Runnable onClick() {
            return this.onClick;
        }

        public Runnable onLongClick() {
            return this.onLongClick;
        }

        public CharSequence text() {
            return this.text;
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), MenuItemInfo.class, "iconRes;text;onClick;onLongClick");
        }
    }

    /* renamed from: $r8$lambda$-Jxy_f6itoaiwRE26qy73G9W0AI, reason: not valid java name */
    public static /* synthetic */ boolean m1778$r8$lambda$Jxy_f6itoaiwRE26qy73G9W0AI(ItemOptions r0, Runnable r1, View r2) {
        r0.dismiss();
        r1.run();
        return true;
    }

    public static /* synthetic */ void $r8$lambda$1UDS6cF5oxxIoHi5hFvo4jGO7uo(ItemOptions r0, List r1, int r2, MenuContext r3) {
        r0.dismiss();
        createPluginClickAction((MenuItemRecord) r1.get(r2), r3).run();
    }

    public static /* synthetic */ void $r8$lambda$3Es3NV7wzYIj2dMr_UqHI4LaXgk(BaseFragment r1) {
        r1.presentFragment(new SettingsActivity());
    }

    /* renamed from: $r8$lambda$5R26hUL6du44J7o-K11A-rWCGqc, reason: not valid java name */
    public static /* synthetic */ void m1779$r8$lambda$5R26hUL6du44J7oK11ArWCGqc(MenuItemRecord r0, MenuContext r1) {
        r0.executeClick(getPluginContextData(r1));
    }

    public static /* synthetic */ void $r8$lambda$5uPuRuPTDkgduimtGHAPMc39cgk(BaseFragment r2) {
        r2.presentFragment(new GroupCreateActivity(new Bundle()));
    }

    public static /* synthetic */ void $r8$lambda$DBvqCdKhylAKzGqV97R3a3xob4U(BaseFragment r0) {
        FeedActivity.presentFeed(r0);
    }

    /* renamed from: $r8$lambda$DjPIIdSukBvUFDTjUNeT-Jfx0YY, reason: not valid java name */
    public static /* synthetic */ void m1780$r8$lambda$DjPIIdSukBvUFDTjUNeTJfx0YY(MenuContext r3, TLRPC.TL_attachMenuBot r4, LaunchActivity r5, Boolean r6) {
        TLRPC.TL_messages_toggleBotInAttachMenu r62 = new TLRPC.TL_messages_toggleBotInAttachMenu();
        r62.bot = MessagesController.getInstance(r3.currentAccount()).getInputUser(r4.bot_id);
        r62.enabled = true;
        r62.write_allowed = true;
        ConnectionsManager.getInstance(r3.currentAccount()).sendRequest(r62, new MainMenuHelper$$ExternalSyntheticLambda22(r4, r5, r3), 66);
    }

    public static /* synthetic */ void $r8$lambda$GKoGYhAKUyDtUH2m5JihZwABHOc(MenuContext r2, TLRPC.TL_attachMenuBot r3) {
        BotWebViewSheet.deleteBot(r2.currentAccount(), r3.bot_id, null);
    }

    public static /* synthetic */ void $r8$lambda$J1OwKnqXdzc6kbDpmVdBOkDhjxc(TLRPC.TL_attachMenuBot r0, LaunchActivity r1, MenuContext r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new MainMenuHelper$$ExternalSyntheticLambda23(r0, r1, r2));
    }

    /* renamed from: $r8$lambda$KDLBDKsgHSE6LBLdB8g6-2MSI00, reason: not valid java name */
    public static /* synthetic */ void m1781$r8$lambda$KDLBDKsgHSE6LBLdB8g62MSI00(BaseFragment r3) {
        Bundle r0 = new Bundle();
        r0.putInt("folderId", 1);
        r3.presentFragment(new DialogsActivity(r0));
    }

    /* renamed from: $r8$lambda$Of-wWLwqHXMfyCwlbp7mrVF8fKA, reason: not valid java name */
    public static /* synthetic */ void m1782$r8$lambda$OfwWLwqHXMfyCwlbp7mrVF8fKA(boolean r0, ItemOptions r1, ItemOptions r2, MenuItemInfo r3) {
        if (r0 == false) goto L5;
        r1.openSwipeback(r2);
        return;
    L5:
        r3.onClick().run();
    }

    public static /* synthetic */ void $r8$lambda$Q8IakieKJqFN62CokMs4nVB419M(BaseFragment r4) {
        SharedPreferences r0 = MessagesController.getGlobalMainSettings();
        if (BuildVars.DEBUG_VERSION == false) goto L5;
    L8:
        r4.presentFragment(new ActionIntroActivity(0));
        r0.edit().putBoolean("channel_intro", true).apply();
        return;
    L5:
        if (r0.getBoolean("channel_intro", false) == false) goto L8;
        Bundle r02 = new Bundle();
        r02.putInt("step", 0);
        r4.presentFragment(new ChannelCreateActivity(r02));
    }

    public static /* synthetic */ void $r8$lambda$TSIhEbVdCMjRIBRygQDcrN3tWYY(BaseFragment r1) {
        r1.presentFragment(new PluginsActivity());
    }

    public static /* synthetic */ void $r8$lambda$a5bq_jLS447MCh9JamahNb2Edmk(int r3, BaseFragment r4) {
        Bundle r0 = new Bundle();
        r0.putLong("user_id", UserConfig.getInstance(r3).getClientUserId());
        r4.presentFragment(new ChatActivity(r0));
    }

    /* renamed from: $r8$lambda$aYRdTJDw7yxjIZWrVvebLh-_5nU, reason: not valid java name */
    public static /* synthetic */ void m1783$r8$lambda$aYRdTJDw7yxjIZWrVvebLh_5nU(MenuContext r1) {
        r1.fragment().presentFragment(new PluginsActivity());
    }

    public static /* synthetic */ void $r8$lambda$aYhptRjLYU6aWVNMAzGjtgDV_Ec(TLRPC.TL_attachMenuBot r3, MenuContext r4, LaunchActivity r5) {
        if (r3.inactive == true) goto L10;
        if (r3.side_menu_disclaimer_needed == true) goto L10;
        LaunchActivity.showAttachMenuBot(r5, r4.currentAccount(), r3, null, true);
        return;
    L10:
        if (r4.fragment().getContext() == null) goto L12;
        Context r0 = r4.fragment().getContext();
    L13:
        WebAppDisclaimerAlert.show(r0, new MainMenuHelper$$ExternalSyntheticLambda21(r4, r3, r5), null, null);
        return;
    L12:
        r0 = r5;
        goto L13
    }

    /* renamed from: $r8$lambda$eUXP3XG-XU2KMhueQHg7MuztKQM, reason: not valid java name */
    public static /* synthetic */ void m1784$r8$lambda$eUXP3XGXU2KMhueQHg7MuztKQM(TLRPC.TL_attachMenuBot r3, LaunchActivity r4, MenuContext r5) {
        r3.side_menu_disclaimer_needed = false;
        r3.inactive = false;
        LaunchActivity.showAttachMenuBot(r4, r5.currentAccount(), r3, null, true);
        MediaDataController.getInstance(r5.currentAccount()).updateAttachMenuBotsInCache();
    }

    public static /* synthetic */ boolean $r8$lambda$f5kxgmhG0LQrmPuZjc0uz002N0k(ItemOptions r0, MenuItemInfo r1, View r2) {
        r0.dismiss();
        r1.onClick().run();
        return true;
    }

    /* renamed from: $r8$lambda$i2DoK-P95qCY24IrhC1YzQwp56E, reason: not valid java name */
    public static /* synthetic */ void m1785$r8$lambda$i2DoKP95qCY24IrhC1YzQwp56E(BaseFragment r2) {
        LaunchActivity r0 = LaunchActivity.instance;
        if (r0 != null) goto L5;
    L8:
        CameraScanActivity.showAsSheet(r2, true, 1, new AnonymousClass1());
        return;
    L5:
        if (r0.checkSelfPermission("android.permission.CAMERA") == 0) goto L8;
        LaunchActivity.instance.requestPermissions(new String[]{"android.permission.CAMERA"}, 34);
    }

    /* renamed from: $r8$lambda$je-2ITcORHHV0tfyHwX-LWEPGuI, reason: not valid java name */
    public static /* synthetic */ boolean m1786$r8$lambda$je2ITcORHHV0tfyHwXLWEPGuI(int r0) {
        return false;
    }

    public static /* synthetic */ void $r8$lambda$rf1zv95SfLPiRo0xj0q30wQjI60(BaseFragment r1) {
        r1.presentFragment(new CallLogActivity());
    }

    public static /* synthetic */ void $r8$lambda$rtSRDj8a2WJm0LXRVKfT4uBArCY(BaseFragment r3) {
        Bundle r0 = new Bundle();
        r0.putBoolean("needPhonebook", true);
        r0.putBoolean("needFinishFragment", false);
        r3.presentFragment(new ContactsActivity(r0));
    }

    public static /* synthetic */ void $r8$lambda$saqA7TDqFSmi2ZXfA6fwir6uv4I(BaseFragment r3) {
        SearchEngine r0 = SearchEngine.getCurrent();
        String r1 = r0.getHomepage();
        Activity r32 = r3.getParentActivity();
        if (TextUtils.isEmpty(r1) == false) goto L6;
        r1 = r0.search_url;
    L6:
        Browser.openInTelegramBrowser(r32, r1, null);
    }

    /* renamed from: $r8$lambda$v0h8pBSNeYqoHv-MbcetfzvFNlU, reason: not valid java name */
    public static /* synthetic */ void m1787$r8$lambda$v0h8pBSNeYqoHvMbcetfzvFNlU(int r3, BaseFragment r4) {
        Bundle r0 = new Bundle();
        r0.putLong("user_id", UserConfig.getInstance(r3).getClientUserId());
        r0.putBoolean("my_profile", true);
        r4.presentFragment(new ProfileActivity(r0));
    }

    private static boolean addAttachMenuBotMenuItems(ItemOptions r3, MenuContext r4) {
        List<AttachMenuBotInfo> r42 = getAttachMenuBotItems(r4);
        if (r42.isEmpty() == false) goto L6;
        return false;
    L6:
        Iterator<AttachMenuBotInfo> r43 = r42.iterator();
    L8:
        if (r43.hasNext() == false) goto L10;
        AttachMenuBotInfo r0 = r43.next();
        r3.addBot(r0.bot(), r0.onClick(), r0.onLongClick());
        goto L8
    L10:
        return true;
    }

    private static boolean addConfiguredItemOption(ItemOptions r3, MenuContext r4, int r5) {
        MainMenuItem r0 = MainMenuItem.getById(r5);
        if (r0 != null) goto L6;
        return false;
    L6:
        if (r0 != MainMenuItem.ARCHIVE) goto L11;
        if (ChatUtils.getInstance(r4.currentAccount()).hasArchivedChats() == true) goto L11;
        return false;
    L11:
        if (r0 != MainMenuItem.BOTS) goto L15;
        return addAttachMenuBotMenuItems(r3, r4);
    L15:
        if (r0 == MainMenuItem.PLUGINS) goto L17;
        MenuItemInfo r42 = resolveMenuItem(r5, r4);
        if (r42 != null) goto L21;
    L25:
        return false;
    L21:
        if (r42.onClick() == null) goto L25;
        r3.add(r42.iconRes(), r42.text(), r42.onClick());
        bindLongClick(r3, r42.onLongClick());
        return true;
    L17:
        return addPluginConfiguredItem(r3, r4, r0);
    }

    public static void addConfiguredItemOptions(ItemOptions r1, MenuContext r2) {
        addConfiguredItemOptions(r1, r2, new MainMenuHelper$$ExternalSyntheticLambda20());
    }

    public static void addConfiguredItemOptions(ItemOptions r8, MenuContext r9, IntPredicate r10) {
        int r1 = 0;
        boolean r2 = false;
        boolean r3 = false;
    L4:
        if (r1 >= ExteraConfig.getMainMenuLayout().size()) goto L23;
        Integer r4 = ExteraConfig.getMainMenuLayout().get(r1);
        if (r4 == null) goto L22;
        if (ExteraConfig.getMainMenuHiddenItems().contains(r4) == true) goto L22;
        if (r4.intValue() != MainMenuItem.DIVIDER.getId()) goto L15;
        if (r2 == false) goto L22;
        r3 = true;
        goto L22
    L15:
        if (r10.test(r4.intValue()) == true) goto L22;
        if (r3 == false) goto L20;
        r8.addGap();
        r3 = false;
    L20:
        if (addConfiguredItemOption(r8, r9, r4.intValue()) == false) goto L22;
        r2 = true;
    L22:
        r1 = r1 + 1;
        goto L4
    }

    private static boolean addPluginConfiguredItem(ItemOptions r8, MenuContext r9, MainMenuItem r10) {
        MenuItemInfo r102 = resolveMenuItem(r10.getId(), r9);
        int r0 = 0;
        if (r102 != null) goto L5;
    L23:
        return false;
    L5:
        if (r102.onClick() == null) goto L23;
        List<MenuItemRecord> r1 = getPluginMenuItems(r9);
        ItemOptions r2 = r8.makeSwipeback();
        boolean r3 = r1.isEmpty();
        boolean r4 = !r3;
        if (r3 == true) goto L17;
        r2.add(R.drawable.ic_ab_back, LocaleController.getString(R.string.Back), new RatePill$$ExternalSyntheticLambda1(r8));
        r2.addGap();
    L11:
        if (r0 >= r1.size()) goto L17;
        MenuItemRecord r5 = r1.get(r0);
        String r6 = r5.getText();
        if (r6 == null) goto L16;
        r2.add(r5.getIconResId(), r6, new MainMenuHelper$$ExternalSyntheticLambda14(r8, r1, r0, r9));
    L16:
        r0 = r0 + 1;
    L17:
        r8.add(r102.iconRes(), r102.text(), new MainMenuHelper$$ExternalSyntheticLambda15(r4, r8, r2, r102));
        ActionBarMenuSubItem r92 = r8.getLast();
        if (r3 == true) goto L27;
        if (r92 == null) goto L28;
        r92.setOnLongClickListener(new MainMenuHelper$$ExternalSyntheticLambda16(r8, r102));
        r92.setRightIcon(R.drawable.msg_arrowright);
        return true;
    L28:
        return true;
    L27:
        return true;
    }

    private static void bindLongClick(ItemOptions r2, Runnable r3) {
        if (r3 == null) goto L9;
        ActionBarMenuSubItem r0 = r2.getLast();
        if (r0 != null) goto L7;
        return;
    L7:
        r0.setOnLongClickListener(new MainMenuHelper$$ExternalSyntheticLambda0(r2, r3));
        return;
    }

    private static Runnable createAttachMenuBotClickAction(MenuContext r1, TLRPC.TL_attachMenuBot r2, LaunchActivity r3) {
        return new MainMenuHelper$$ExternalSyntheticLambda18(r2, r1, r3);
    }

    public static MenuContext createMenuContext(int r2, BaseFragment r3) {
        return new MenuContext(r2, r3, null, null);
    }

    public static MenuContext createMenuContext(int r1, BaseFragment r2, Runnable r3, Map<String, Object> r4) {
        return new MenuContext(r1, r2, r3, r4);
    }

    public static Runnable createPluginClickAction(MenuItemRecord r1, MenuContext r2) {
        return new MainMenuHelper$$ExternalSyntheticLambda19(r1, r2);
    }

    public static Map<String, Object> createPluginContextData(int r2, BaseFragment r3) {
        MenuContextBuilder r0 = MenuContextBuilder.create().withAccount(r2);
        if (r3 != null) goto L5;
    L9:
        TLRPC.User r22 = UserConfig.getInstance(r2).getCurrentUser();
        if (r22 == null) goto L13;
        r0.withUser(r22);
    L13:
        return r0.build();
    L5:
        if (r3.getContext() == null) goto L7;
        Context r32 = r3.getContext();
    L8:
        r0.withContext(r32);
        goto L9
    L7:
        r32 = r3.getParentActivity();
        goto L8
    }

    private static LaunchActivity findLaunchActivity(BaseFragment r1) {
        if (r1 != null) goto L6;
        return LaunchActivity.instance;
    L6:
        if (r1.getContext() == null) goto L8;
        Context r12 = r1.getContext();
    L9:
        Activity r13 = AndroidUtilities.findActivity(r12);
        if ((r13 instanceof LaunchActivity) == false) goto L14;
        return (LaunchActivity) r13;
    L14:
        return LaunchActivity.instance;
    L8:
        r12 = r1.getParentActivity();
        goto L9
    }

    private static int getAttachMenuBotIconRes(TLRPC.TL_attachMenuBot r4) {
        if (r4.bot_id != 1985737506) goto L7;
        return R.drawable.menu_wallet;
    L7:
        return R.drawable.msg_bot;
    }

    public static List<AttachMenuBotInfo> getAttachMenuBotItems(MenuContext r12) {
        BaseFragment r0 = r12.fragment();
        LaunchActivity r1 = findLaunchActivity(r0);
        TLRPC.TL_attachMenuBots r2 = MediaDataController.getInstance(r12.currentAccount()).getAttachMenuBots();
        if (r0 == null) goto L19;
        if (r1 == null) goto L19;
        if (r2 == null) goto L19;
        ArrayList<TLRPC.TL_attachMenuBot> r02 = r2.bots;
        if (r02 == null) goto L19;
        if (r02.isEmpty() == true) goto L19;
        ArrayList r03 = new ArrayList();
        ArrayList<TLRPC.TL_attachMenuBot> r22 = r2.bots;
        int r3 = r22.size();
        int r4 = 0;
    L12:
        if (r4 >= r3) goto L17;
        TLRPC.TL_attachMenuBot r5 = r22.get(r4);
        r4 = r4 + 1;
        TLRPC.TL_attachMenuBot r9 = r5;
        if (r9.show_in_side_menu == false) goto L12;
        r03.add(new AttachMenuBotInfo(getAttachMenuBotIconRes(r9), r9.short_name, r9, createAttachMenuBotClickAction(r12, r9, r1), new MainMenuHelper$$ExternalSyntheticLambda17(r12, r9)));
        goto L12
    L17:
        return r03;
    L19:
        return Collections.EMPTY_LIST;
    }

    private static Map<String, Object> getPluginContextData(MenuContext r1) {
        if (r1.pluginContextData() == null) goto L7;
        return r1.pluginContextData();
    L7:
        return createPluginContextData(r1.currentAccount(), r1.fragment());
    }

    public static List<MenuItemRecord> getPluginMenuItems(MenuContext r2) {
        return PluginsController.getInstance().getMenuItemsForLocation(PluginsConstants.MenuItemTypes.MAIN_MENU, getPluginContextData(r2));
    }

    private static List<MenuItemInfo> resolveDrawerBotMenuItems(MenuContext r6) {
        List<AttachMenuBotInfo> r62 = getAttachMenuBotItems(r6);
        if (r62.isEmpty() == true) goto L5;
        ArrayList r0 = new ArrayList(r62.size());
        Iterator<AttachMenuBotInfo> r63 = r62.iterator();
    L8:
        if (r63.hasNext() == false) goto L10;
        AttachMenuBotInfo r1 = r63.next();
        r0.add(new MenuItemInfo(r1.iconRes(), r1.text(), r1.onClick(), r1.onLongClick()));
        goto L8
    L10:
        return r0;
    L5:
        return Collections.EMPTY_LIST;
    }

    public static List<MenuItemInfo> resolveDrawerMenuItems(int r2, MenuContext r3) {
        MainMenuItem r0 = MainMenuItem.getById(r2);
        if (r0 == null) goto L5;
        int r02 = AnonymousClass2.$SwitchMap$com$exteragram$messenger$MainMenuItem[r0.ordinal()];
        if (r02 == 1) goto L19;
        if (r02 == 2) goto L17;
        MenuItemInfo r22 = resolveMenuItem(r2, r3);
        if (r22 != null) goto L15;
        return Collections.EMPTY_LIST;
    L15:
        return Collections.singletonList(r22);
    L17:
        return resolveDrawerPluginMenuItems(r3);
    L19:
        return resolveDrawerBotMenuItems(r3);
    L5:
        return Collections.EMPTY_LIST;
    }

    private static List<MenuItemInfo> resolveDrawerPluginMenuItems(MenuContext r8) {
        if (r8.fragment() == null) goto L5;
        MenuItemInfo r0 = resolveMenuItem(MainMenuItem.PLUGINS.getId(), r8);
        List<MenuItemRecord> r1 = getPluginMenuItems(r8);
        if (r1.isEmpty() == false) goto L13;
        if (r0 != null) goto L12;
        return Collections.EMPTY_LIST;
    L12:
        return Collections.singletonList(r0);
    L13:
        int r3 = r1.size();
        if (r0 == null) goto L16;
        int r4 = 1;
    L17:
        ArrayList r2 = new ArrayList(r3 + r4);
        if (r0 == null) goto L20;
        r2.add(r0);
    L20:
        Iterator<MenuItemRecord> r12 = r1.iterator();
    L22:
        if (r12.hasNext() == false) goto L34;
        MenuItemRecord r32 = r12.next();
        if (r32 == null) goto L22;
        if (TextUtils.isEmpty(r32.getText()) == true) goto L22;
        if (r32.getIconResId() == 0) goto L31;
        int r5 = r32.getIconResId();
    L32:
        r2.add(new MenuItemInfo(r5, r32.getText(), createPluginClickAction(r32, r8), new MainMenuHelper$$ExternalSyntheticLambda1(r8)));
        goto L22
    L31:
        r5 = R.drawable.msg_plugins;
        goto L32
    L34:
        if (r2.isEmpty() == false) goto L40;
        if (r0 != null) goto L39;
        return Collections.EMPTY_LIST;
    L39:
        return Collections.singletonList(r0);
    L40:
        return r2;
    L16:
        r4 = 0;
        goto L17
    L5:
        return Collections.EMPTY_LIST;
    }

    public static MenuItemInfo resolveMenuItem(int r5, MenuContext r6) {
        MainMenuItem r52 = MainMenuItem.getById(r5);
        if (r52 != null) goto L5;
    L40:
        return null;
    L5:
        if (r6.fragment() == null) goto L40;
        int r1 = r6.currentAccount();
        BaseFragment r2 = r6.fragment();
        switch(AnonymousClass2.$SwitchMap$com$exteragram$messenger$MainMenuItem[r52.ordinal()]) {
            case 2: goto L37;
            case 3: goto L35;
            case 4: goto L28;
            case 5: goto L27;
            case 6: goto L25;
            case 7: goto L23;
            case 8: goto L21;
            case 9: goto L19;
            case 10: goto L17;
            case 11: goto L15;
            case 12: goto L13;
            case 13: goto L11;
            default: goto L9;
        };
    L9:
        return null;
    L28:
        int r12 = R.drawable.msg_archive;
        String r3 = LocaleController.getString(R.string.ArchivedChats);
        if (r6.archiveClick() == null) goto L31;
        Runnable r62 = r6.archiveClick();
    L33:
        return new MenuItemInfo(r12, r3, r62, null);
    L31:
        r62 = new MainMenuHelper$$ExternalSyntheticLambda5(r2);
        goto L33
    L11:
        return new MenuItemInfo(R.drawable.msg_qrcode, LocaleController.getString(R.string.AuthAnotherClient), new MainMenuHelper$$ExternalSyntheticLambda4(r2), null);
    L13:
        return new MenuItemInfo(R.drawable.msg2_language, LocaleController.getString(R.string.BrowserSettingsTitle), new MainMenuHelper$$ExternalSyntheticLambda3(r2), null);
    L15:
        return new MenuItemInfo(R.drawable.msg_settings, LocaleController.getString(R.string.Settings), new MainMenuHelper$$ExternalSyntheticLambda12(r2), null);
    L17:
        return new MenuItemInfo(R.drawable.ic_feed, LocaleController.getString(R.string.Feed), new MainMenuHelper$$ExternalSyntheticLambda11(r2), null);
    L19:
        return new MenuItemInfo(R.drawable.msg_saved, LocaleController.getString(R.string.SavedMessages), new MainMenuHelper$$ExternalSyntheticLambda10(r1, r2), null);
    L21:
        return new MenuItemInfo(R.drawable.msg_channel, LocaleController.getString(R.string.NewChannel), new MainMenuHelper$$ExternalSyntheticLambda9(r2), null);
    L23:
        return new MenuItemInfo(R.drawable.msg_calls, LocaleController.getString(R.string.Calls), new MainMenuHelper$$ExternalSyntheticLambda8(r2), null);
    L25:
        return new MenuItemInfo(R.drawable.msg_contacts, LocaleController.getString(R.string.Contacts), new MainMenuHelper$$ExternalSyntheticLambda7(r2), null);
    L27:
        return new MenuItemInfo(R.drawable.msg_groups, LocaleController.getString(R.string.NewGroup), new MainMenuHelper$$ExternalSyntheticLambda6(r2), null);
    L35:
        return new MenuItemInfo(R.drawable.left_status_profile, LocaleController.getString(R.string.MyProfile), new MainMenuHelper$$ExternalSyntheticLambda2(r1, r2), null);
    L37:
        if (PluginsController.isPluginEngineSupported() == false) goto L40;
        return new MenuItemInfo(R.drawable.msg_plugins, LocaleController.getString(R.string.Plugins), new MainMenuHelper$$ExternalSyntheticLambda13(r2), null);
    }
}
