package com.exteragram.messenger.preferences;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.ui.PluginsActivity;
import com.exteragram.messenger.preferences.appearance.AppearancePreferencesActivity;
import com.exteragram.messenger.preferences.chats.ChatsPreferencesActivity;
import com.exteragram.messenger.preferences.components.HeaderSettingsCell;
import com.exteragram.messenger.utils.ExtensionsKt;
import com.exteragram.messenger.utils.system.VibratorUtils;
import java.util.ArrayList;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.browser.Browser;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.LaunchActivity;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001 B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0014J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0014J\u001e\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J0\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0014J0\u0010\u001d\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0014J\u0010\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u0019H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/exteragram/messenger/preferences/MainPreferencesActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "<init>", "()V", "headerSettingsCell", "Lcom/exteragram/messenger/preferences/components/HeaderSettingsCell;", "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "needHideTitle", _UrlKt.FRAGMENT_ENCODE_SET, "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "fillItems", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "position", _UrlKt.FRAGMENT_ENCODE_SET, "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "onLongClick", "getListTopPadding", "topInset", "PreferenceItem", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class MainPreferencesActivity extends BasePreferencesActivity {
    private HeaderSettingsCell headerSettingsCell;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u0012"}, d2 = {"Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "HEADER_CELL", "GENERAL_CATEGORY", "APPEARANCE_CATEGORY", "CHATS_CATEGORY", "PLUGINS_CATEGORY", "OTHER_CATEGORY", "CHANNEL", "FORUM", "CROWDIN", "WEBSITE", "id", _UrlKt.FRAGMENT_ENCODE_SET, "getId", "()I", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum PreferenceItem extends Enum<PreferenceItem> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ PreferenceItem[] $VALUES = null;
        public static final PreferenceItem APPEARANCE_CATEGORY = null;
        public static final PreferenceItem CHANNEL = null;
        public static final PreferenceItem CHATS_CATEGORY = null;
        public static final PreferenceItem CROWDIN = null;
        public static final PreferenceItem FORUM = null;
        public static final PreferenceItem GENERAL_CATEGORY = null;
        public static final PreferenceItem HEADER_CELL = null;
        public static final PreferenceItem OTHER_CATEGORY = null;
        public static final PreferenceItem PLUGINS_CATEGORY = null;
        public static final PreferenceItem WEBSITE = null;
        private final int id;

        private static final /* synthetic */ PreferenceItem[] $values() {
            return new PreferenceItem[]{HEADER_CELL, GENERAL_CATEGORY, APPEARANCE_CATEGORY, CHATS_CATEGORY, PLUGINS_CATEGORY, OTHER_CATEGORY, CHANNEL, FORUM, CROWDIN, WEBSITE};
        }

        static {
            HEADER_CELL = new PreferenceItem("HEADER_CELL", 0);
            GENERAL_CATEGORY = new PreferenceItem("GENERAL_CATEGORY", 1);
            APPEARANCE_CATEGORY = new PreferenceItem("APPEARANCE_CATEGORY", 2);
            CHATS_CATEGORY = new PreferenceItem("CHATS_CATEGORY", 3);
            PLUGINS_CATEGORY = new PreferenceItem("PLUGINS_CATEGORY", 4);
            OTHER_CATEGORY = new PreferenceItem("OTHER_CATEGORY", 5);
            CHANNEL = new PreferenceItem("CHANNEL", 6);
            FORUM = new PreferenceItem("FORUM", 7);
            CROWDIN = new PreferenceItem("CROWDIN", 8);
            WEBSITE = new PreferenceItem("WEBSITE", 9);
            PreferenceItem[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        PreferenceItem(String r1, int r2) {
            this.id = ordinal() + 1;
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
            return this.id;
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[PreferenceItem.values().length];
            r0[PreferenceItem.HEADER_CELL.ordinal()] = 1;     // Catch: NoSuchFieldError -> L15
        L27:
            r0[PreferenceItem.GENERAL_CATEGORY.ordinal()] = 2;     // Catch: NoSuchFieldError -> L16
        L43:
            r0[PreferenceItem.APPEARANCE_CATEGORY.ordinal()] = 3;     // Catch: NoSuchFieldError -> L17
        L31:
            r0[PreferenceItem.CHATS_CATEGORY.ordinal()] = 4;     // Catch: NoSuchFieldError -> L18
        L35:
            r0[PreferenceItem.PLUGINS_CATEGORY.ordinal()] = 5;     // Catch: NoSuchFieldError -> L19
        L25:
            r0[PreferenceItem.OTHER_CATEGORY.ordinal()] = 6;     // Catch: NoSuchFieldError -> L20
        L37:
            r0[PreferenceItem.CHANNEL.ordinal()] = 7;     // Catch: NoSuchFieldError -> L21
        L39:
            r0[PreferenceItem.FORUM.ordinal()] = 8;     // Catch: NoSuchFieldError -> L22
        L29:
            r0[PreferenceItem.CROWDIN.ordinal()] = 9;     // Catch: NoSuchFieldError -> L23
        L33:
            r0[PreferenceItem.WEBSITE.ordinal()] = 10;     // Catch: NoSuchFieldError -> L24
        L13:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public MainPreferencesActivity() {
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r2) {
        this.headerSettingsCell = new HeaderSettingsCell(r2);
        View r22 = super.createView(r2);
        this.fragmentView = r22;
        return r22;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r17, UniversalAdapter r18) {
        HeaderSettingsCell r2 = this.headerSettingsCell;
        if (r2 != null) goto L5;
        r2 = null;
    L5:
        r17.add(UItem.asCustomShadow(r2, 198).setId(PreferenceItem.HEADER_CELL.getId()));
        r17.add(UItem.asHeader(LocaleController.getString(R.string.Categories)));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, PreferenceItem.GENERAL_CATEGORY, LocaleController.getString(R.string.General), R.drawable.msg_media, null, null, null, false, "general", false, false, null, 1912, null));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, PreferenceItem.APPEARANCE_CATEGORY, LocaleController.getString(R.string.Appearance), R.drawable.msg_theme, null, null, null, false, "appearance", false, false, null, 1912, null));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, PreferenceItem.CHATS_CATEGORY, LocaleController.getString(R.string.SearchAllChatsShort), R.drawable.msg_discussion, null, null, null, false, "chats", false, false, null, 1912, null));
        ExtensionsKt.addIf(r17, PluginsController.Companion.isPluginEngineSupported(), BasePreferencesActivity.uButton$TMessagesProj$default(this, PreferenceItem.PLUGINS_CATEGORY, LocaleController.getString(R.string.Plugins), R.drawable.msg_plugins, null, null, null, false, PluginsConstants.PLUGINS, false, false, null, 1912, null));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, PreferenceItem.OTHER_CATEGORY, LocaleController.getString(R.string.LocalOther), R.drawable.msg_fave, null, null, null, false, "other", false, false, null, 1912, null));
        r17.add(UItem.asShadow());
        r17.add(UItem.asHeader(LocaleController.getString(R.string.Links)));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, PreferenceItem.CHANNEL, LocaleController.getString(R.string.ProfileChannel), R.drawable.msg_channel, "@exteraGram", null, null, false, "channel", false, false, null, 1904, null));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, PreferenceItem.FORUM, LocaleController.getString(R.string.SearchAllChatsShort), R.drawable.msg_groups, "@exteraForum", null, null, false, "chat", false, false, null, 1904, null));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, PreferenceItem.CROWDIN, LocaleController.getString(R.string.Crowdin), R.drawable.msg_translate, "Crowdin", null, null, false, "crowdin", false, false, null, 1904, null));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, PreferenceItem.WEBSITE, LocaleController.getString(R.string.Website), R.drawable.msg_language, "exteraGram.app", null, Boolean.FALSE, false, "website", false, false, null, 1872, null));
        r17.add(UItem.asShadow());
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public int getListTopPadding(int r1) {
        return r1 + AndroidUtilities.dp(12.0f);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return LocaleController.getString(R.string.Preferences);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public boolean needHideTitle() {
        return true;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        int r22 = r1.id;
        if (r22 > 0) goto L5;
        return;
    L5:
        if (r22 > PreferenceItem.getEntries().size()) goto L41;
        PreferenceItem r12 = PreferenceItem.getEntries().get(r1.id - 1);
        switch(WhenMappings.$EnumSwitchMapping$0[r12.ordinal()]) {
            case 1: goto L30;
            case 2: goto L27;
            case 3: goto L25;
            case 4: goto L23;
            case 5: goto L21;
            case 6: goto L19;
            case 7: goto L17;
            case 8: goto L15;
            case 9: goto L13;
            case 10: goto L11;
            default: goto L9;
        };
    L9:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return;
    L11:
        Browser.openUrl(getParentActivity(), "https://exteraGram.app");
        return;
    L13:
        Browser.openUrl(getParentActivity(), "https://crowdin.com/project/exteralocales");
        return;
    L15:
        getMessagesController().openByUserName("exteraForum", this, 1);
        return;
    L17:
        getMessagesController().openByUserName("exteraGram", this, 1);
        return;
    L19:
        presentFragment(new OtherPreferencesActivity());
        return;
    L21:
        presentFragment(new PluginsActivity());
        return;
    L23:
        presentFragment(new ChatsPreferencesActivity());
        return;
    L25:
        presentFragment(new AppearancePreferencesActivity());
        return;
    L27:
        presentFragment(new GeneralPreferencesActivity());
        return;
    L30:
        if (BuildVars.PM_BUILD == true) goto L39;
        Activity r0 = getParentActivity();
        if ((r0 instanceof LaunchActivity) == false) goto L34;
        LaunchActivity r02 = (LaunchActivity) r0;
    L35:
        if (r02 == null) goto L40;
        r02.checkAppUpdate(true);
        return;
    L40:
        return;
    L34:
        r02 = null;
        goto L35
    L39:
        return;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public boolean onLongClick(UItem r10, View r11, int r12, float r13, float r14) {
        int r0 = r10.id;
        if (r0 > 0) goto L5;
        return false;
    L5:
        if (r0 <= PreferenceItem.getEntries().size()) goto L8;
        return false;
    L8:
        if (PreferenceItem.getEntries().get(r10.id - 1) != PreferenceItem.HEADER_CELL) goto L12;
        ExteraConfig.setUseSystemIconShape(!ExteraConfig.getUseSystemIconShape());
        r11.performHapticFeedback(VibratorUtils.getType(3), 1);
        r11.invalidate();
        return true;
    L12:
        return super.onLongClick(r10, r11, r12, r13, r14);
    }
}
