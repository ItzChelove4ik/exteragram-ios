package com.exteragram.messenger.preferences.utils;

import android.text.TextUtils;
import android.view.View;
import androidx.camera.core.ImageCapture$$ExternalSyntheticBackport1;
import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import com.exteragram.messenger.ai.ui.activities.AiPreferencesActivity;
import com.exteragram.messenger.feed.ui.FeedChannelsActivity;
import com.exteragram.messenger.pillstack.ui.PillStackPreferencesActivity;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.ui.PluginsInfoActivity;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import com.exteragram.messenger.preferences.GeneralPreferencesActivity;
import com.exteragram.messenger.preferences.MainPreferencesActivity;
import com.exteragram.messenger.preferences.OtherPreferencesActivity;
import com.exteragram.messenger.preferences.appearance.AppNavigationPreferencesActivity;
import com.exteragram.messenger.preferences.appearance.AppearancePreferencesActivity;
import com.exteragram.messenger.preferences.chats.ChatsPreferencesActivity;
import com.exteragram.messenger.preferences.chats.SwipeActionsPreferencesActivity;
import com.exteragram.messenger.utils.text.LocaleUtils;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes4.dex */
public class SettingsRegistry {
    private static final Map<Class<? extends BaseFragment>, Integer> categoriesIcons = null;
    public static List<String> newFeatures;
    private boolean entriesFetched;
    private String entriesLangCode;
    private final ConcurrentHashMap<String, Entry> entriesStringAlias;
    private final ConcurrentHashMap<Integer, Entry> preparedEntries;

    public static final class Entry extends RecordTag {
        private final Class<? extends BaseFragment> fragmentClass;
        private final int guid;
        private final int icon;
        private final int itemId;
        private final String subtext;
        private final String title;

        public static /* synthetic */ void $r8$lambda$3Hm7Oq9JGkNqNaUUphtAbUkBFwg(Entry r0) {
            r0.lambda$toSearchResult$0();
        }

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof Entry) == false) goto L18;
            Entry r32 = (Entry) r3;
            if (this.guid == r32.guid) goto L7;
            return false;
        L7:
            if (this.itemId == r32.itemId) goto L9;
            return false;
        L9:
            if (this.icon == r32.icon) goto L11;
            return false;
        L11:
            if (Objects.equals(this.title, r32.title) == true) goto L13;
            return false;
        L13:
            if (Objects.equals(this.subtext, r32.subtext) == true) goto L15;
            return false;
        L15:
            if (Objects.equals(this.fragmentClass, r32.fragmentClass) == false) goto L25;
            return true;
        L25:
            return false;
        L18:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{Integer.valueOf(this.guid), Integer.valueOf(this.itemId), this.title, this.subtext, Integer.valueOf(this.icon), this.fragmentClass};
        }

        /* renamed from: -$$Nest$fgetfragmentClass, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Class m1717$$Nest$fgetfragmentClass(Entry r0) {
            return r0.fragmentClass;
        }

        /* renamed from: -$$Nest$fgetguid, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m1718$$Nest$fgetguid(Entry r0) {
            return r0.guid;
        }

        /* renamed from: -$$Nest$fgetitemId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m1719$$Nest$fgetitemId(Entry r0) {
            return r0.itemId;
        }

        private Entry(int r1, int r2, String r3, String r4, int r5, Class<? extends BaseFragment> r6) {
            this.guid = r1;
            this.itemId = r2;
            this.title = r3;
            this.subtext = r4;
            this.icon = r5;
            this.fragmentClass = r6;
        }

        public static Entry fromUItem(BaseFragment r7, UItem r8) {
            Class<?> r6 = r7.getClass();
            CharSequence r0 = r8.text;
            String r1 = null;
            if (r0 != null) goto L5;
            String r3 = null;
        L7:
            if ((r7 instanceof BasePreferencesActivity) == false) goto L10;
            r1 = ((BasePreferencesActivity) r7).getTitle();
        L10:
            return new Entry(SettingsRegistry.m1716$$Nest$smgenerateGUIDForUItem(r6, r8), r8.id, r3, r1, SettingsRegistry.m1714$$Nest$mgetCategoryIcon(SettingsRegistry.getInstance(), r6), r6);
        L5:
            r3 = String.valueOf(r0);
            goto L7
        }

        private /* synthetic */ void lambda$toSearchResult$0() {
            SettingsRegistry.m1715$$Nest$mopenActivity(SettingsRegistry.getInstance(), this.fragmentClass, Integer.valueOf(this.itemId));
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public final int hashCode() {
            return SettingsRegistry$Entry$$ExternalSyntheticRecord0.m(this.guid, this.itemId, this.icon, this.title, this.subtext, this.fragmentClass);
        }

        public ProfileActivity.SearchAdapter.SearchResult toSearchResult(ProfileActivity.SearchAdapter r9) {
            Objects.requireNonNull(r9);
            return new ProfileActivity.SearchAdapter.SearchResult(r9, this.guid, this.title, String.valueOf(this.itemId), this.subtext, this.icon, new SettingsRegistry$Entry$$ExternalSyntheticLambda1(this));
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), Entry.class, "guid;itemId;title;subtext;icon;fragmentClass");
        }
    }

    public static class SingletonHolder {
        private static final SettingsRegistry INSTANCE = null;

        /* renamed from: -$$Nest$sfgetINSTANCE, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SettingsRegistry m1720$$Nest$sfgetINSTANCE() {
            return INSTANCE;
        }

        static {
            INSTANCE = new SettingsRegistry();
        }
    }

    public static /* synthetic */ BaseFragment $r8$lambda$5BgUDgfoi8XV0nRdEnZiS39v1xo(SettingsRegistry r0, Class r1) {
        return r0.initiateFragment(r1);
    }

    /* renamed from: $r8$lambda$SyXtQzmTfhU1wD-1sJKZt7kETcE, reason: not valid java name */
    public static /* synthetic */ void m1711$r8$lambda$SyXtQzmTfhU1wD1sJKZt7kETcE(BaseFragment r0, BaseFragment r1) {
        r0.presentFragment(r1);
    }

    public static /* synthetic */ ProfileActivity.SearchAdapter.SearchResult[] $r8$lambda$Uq61hhP3TlTJmft55hT5WjpJsx0(int r0) {
        return new ProfileActivity.SearchAdapter.SearchResult[r0];
    }

    /* renamed from: $r8$lambda$ZSV5le-9Aoe5iKUSpyrFZhUOY0I, reason: not valid java name */
    public static /* synthetic */ void m1712$r8$lambda$ZSV5le9Aoe5iKUSpyrFZhUOY0I(BasePreferencesActivity r0, Integer r1) {
        r0.scrollToItem(r1.intValue());
    }

    public static /* synthetic */ boolean $r8$lambda$p4BQB7CEiJF3IYJpUIO4QjqPKlE(int r0, Map.Entry r1) {
        if (Entry.m1718$$Nest$fgetguid((Entry) r1.getValue()) != r0) goto L6;
        return true;
    L6:
        return false;
    }

    /* renamed from: $r8$lambda$u4Vru8P-2Kf-KU8n6Gmuv_2EbMk, reason: not valid java name */
    public static /* synthetic */ ProfileActivity.SearchAdapter.SearchResult m1713$r8$lambda$u4Vru8P2KfKU8n6Gmuv_2EbMk(ProfileActivity.SearchAdapter r0, Entry r1) {
        return r1.toSearchResult(r0);
    }

    /* renamed from: -$$Nest$mgetCategoryIcon, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1714$$Nest$mgetCategoryIcon(SettingsRegistry r0, Class r1) {
        return r0.getCategoryIcon(r1);
    }

    /* renamed from: -$$Nest$mopenActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1715$$Nest$mopenActivity(SettingsRegistry r0, Class r1, Integer r2) {
        r0.openActivity(r1, r2);
    }

    /* renamed from: -$$Nest$smgenerateGUIDForUItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1716$$Nest$smgenerateGUIDForUItem(Class r0, UItem r1) {
        return generateGUIDForUItem(r0, r1);
    }

    static {
        categoriesIcons = SettingsRegistry$$ExternalSyntheticBackport1.m(new Map.Entry[]{SettingsRegistry$$ExternalSyntheticBackport0.m(MainPreferencesActivity.class, Integer.valueOf(R.drawable.extera_outline)), SettingsRegistry$$ExternalSyntheticBackport0.m(GeneralPreferencesActivity.class, Integer.valueOf(R.drawable.msg_media)), SettingsRegistry$$ExternalSyntheticBackport0.m(AppearancePreferencesActivity.class, Integer.valueOf(R.drawable.msg_theme)), SettingsRegistry$$ExternalSyntheticBackport0.m(ChatsPreferencesActivity.class, Integer.valueOf(R.drawable.msg_discussion)), SettingsRegistry$$ExternalSyntheticBackport0.m(PluginsInfoActivity.class, Integer.valueOf(R.drawable.msg_plugins)), SettingsRegistry$$ExternalSyntheticBackport0.m(OtherPreferencesActivity.class, Integer.valueOf(R.drawable.msg_fave)), SettingsRegistry$$ExternalSyntheticBackport0.m(AiPreferencesActivity.class, Integer.valueOf(R.drawable.msg_bot)), SettingsRegistry$$ExternalSyntheticBackport0.m(AppNavigationPreferencesActivity.class, Integer.valueOf(R.drawable.msg_list)), SettingsRegistry$$ExternalSyntheticBackport0.m(PillStackPreferencesActivity.class, Integer.valueOf(R.drawable.outline_header_search)), SettingsRegistry$$ExternalSyntheticBackport0.m(FeedChannelsActivity.class, Integer.valueOf(R.drawable.ic_feed)), SettingsRegistry$$ExternalSyntheticBackport0.m(SwipeActionsPreferencesActivity.class, Integer.valueOf(R.drawable.menu_reply))});
        newFeatures = ImageCapture$$ExternalSyntheticBackport1.m(new String[]{"customSavePath", "disableNotificationDelay", "Camera-ExtendedSettings-StartWithWideAngle", "zoomSlider", "widePosts", "swipeActions", "swipeActionsLoop", "swipeActionsReversed", "aiFeatures", "inlineMathResult", "stickerTime", "hideDialogsSearchBar", "Appearance-M3Styles-ChatHeader", "Appearance-M3Styles-NavigationBar", "Appearance-M3Styles-FloatingButton", "Appearance-Sections", "glassOutlineStyle", "glassMessageMenu", "feedBottomTab", "aiTemperature", "AI-Service-Reasoning"});
    }

    public SettingsRegistry() {
        this.preparedEntries = new ConcurrentHashMap();
        this.entriesStringAlias = new ConcurrentHashMap();
    }

    private void createEntriesIfNeeded() {
        String r0 = getCurrentLangCode();
        if (this.entriesFetched == true) goto L5;
    L8:
        FileLog.d("[Extera] Initialising activities...");
        categoriesIcons.keySet().forEach(new SettingsRegistry$$ExternalSyntheticLambda8(this));
        this.entriesFetched = true;
        this.entriesLangCode = r0;
        return;
    L5:
        if (TextUtils.equals(this.entriesLangCode, r0) == false) goto L7;
        return;
    L7:
        FileLog.d("[Extera] Language changed to " + r0 + ", rebuilding entries...");
        this.preparedEntries.clear();
        goto L8
    }

    private static int generateGUIDForUItem(Class<?> r0, UItem r1) {
        return Objects.hash(new Object[]{r0.getName(), Integer.valueOf(r1.id)});
    }

    private int getCategoryIcon(Class<? extends BaseFragment> r1) {
        return ((Integer) SettingsRegistry$$ExternalSyntheticBackport2.m(categoriesIcons.get(r1), 0)).intValue();
    }

    private static String getCurrentLangCode() {
        LocaleController.LocaleInfo r0 = LocaleController.getInstance().getCurrentLocaleInfo();
        if (r0 != null) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return r0.getKey();
    }

    public static SettingsRegistry getInstance() {
        return SingletonHolder.m1720$$Nest$sfgetINSTANCE();
    }

    private BaseFragment initiateFragment(Class<? extends BaseFragment> r2) {
        BaseFragment r0 = LaunchActivity.getLastFragment();     // Catch: Exception -> L8
        if (r0 != null) goto L6;
        return null;
    L6:
        BaseFragment r22 = r2.getDeclaredConstructor(null).newInstance(null);     // Catch: Exception -> L8
        r22.setParentFragment(r0);     // Catch: Exception -> L8
        r22.createActionBar(r0.getContext());     // Catch: Exception -> L8
        return r22;
    L8:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static boolean isValidForLinkAliases(UItem r8) {
        int r0 = r8.id;
        if (r0 != 0) goto L10;
        Integer r02 = Integer.valueOf(r0);
        Integer r2 = Integer.valueOf(r8.viewType);
        View r3 = r8.view;
        if (r3 != null) goto L7;
        String r32 = null;
    L8:
        FileLog.e(String.format("[Extera] Cannot set link aliases for UItems with ID 0. (UItem ID: %s; View type: %s; View: %s; Text: %s; Subtext: %s)", new Object[]{r02, r2, r32, r8.text, TextUtils.concat(new CharSequence[]{r8.subtext, r8.animatedText})}));
        return false;
    L7:
        r32 = r3.getClass().getName();
        goto L8
    L10:
        return true;
    }

    public static boolean isValidForSearch(UItem r8) {
        if (r8.id != 0) goto L5;
    L8:
        Integer r0 = Integer.valueOf(r8.id);
        Integer r2 = Integer.valueOf(r8.viewType);
        View r3 = r8.view;
        if (r3 != null) goto L11;
        String r32 = null;
    L12:
        FileLog.e(String.format("[Extera] UItems with ID 0 or empty text cannot be added as search result. (UItem ID: %s; View type: %s; View: %s; Text: %s; Subtext: %s)", new Object[]{r0, r2, r32, r8.text, TextUtils.concat(new CharSequence[]{r8.subtext, r8.animatedText})}));
        return false;
    L11:
        r32 = r3.getClass().getName();
        goto L12
    L5:
        if (TextUtils.isEmpty(r8.text) == true) goto L8;
        return true;
    }

    public static boolean markAsNewFeature(String r7) {
        if (newFeatures.contains(r7) == true) goto L5;
    L19:
        return false;
    L5:
        if (ExteraConfig.getDoNotMarkAsNew().contains(r7) == true) goto L19;
        Long r0 = ExteraConfig.getNewFeaturesShowedAt().get(r7);
        if (r0 != null) goto L10;
    L16:
        ExteraConfig.getNewFeaturesShowedAt().put(r7, Long.valueOf(System.currentTimeMillis()));
        ExteraConfig.getEditor().putString("newFeaturesShowedAt", ExteraConfig.getGSON().toJson(ExteraConfig.getNewFeaturesShowedAt())).apply();
        return true;
    L10:
        if (r0.longValue() == 0) goto L16;
        if (Math.abs(System.currentTimeMillis() - r0.longValue()) <= DurationKt.MILLIS_IN_DAY) goto L20;
        ExteraConfig.getNewFeaturesShowedAt().remove(r7);
        ExteraConfig.getEditor().putString("newFeaturesShowedAt", ExteraConfig.getGSON().toJson(ExteraConfig.getNewFeaturesShowedAt()));
        ExteraConfig.getDoNotMarkAsNew().add(r7);
        ExteraConfig.getEditor().putString("doNotMarkAsNew", ExteraConfig.getGSON().toJson(ExteraConfig.getDoNotMarkAsNew())).apply();
        return false;
    L20:
        return true;
    }

    private void openActivity(Class<? extends BaseFragment> r2, Integer r3) {
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0 == null) goto L16;
        BaseFragment r1 = initiateFragment(r2);
        if (r1 == null) goto L17;
        AndroidUtilities.runOnUIThread(new SettingsRegistry$$ExternalSyntheticLambda6(r0, r1));
        if (r3 != null) goto L11;
        return;
    L11:
        if ((r1 instanceof BasePreferencesActivity) == false) goto L15;
        AndroidUtilities.runOnUIThread(new SettingsRegistry$$ExternalSyntheticLambda7((BasePreferencesActivity) r1, r3));
        return;
    L15:
        return;
    L17:
        return;
    }

    public void addLinkAliasForOption(String r3, BaseFragment r4, UItem r5) {
        if (isValidForLinkAliases(r5) == true) goto L6;
        return;
    L6:
        if (markAsNewFeature(r3) == false) goto L15;
        CharSequence r0 = r5.text;
        if (r0 == null) goto L15;
        if (r0.length() <= 0) goto L15;
        if (r5.text.toString().charAt(r5.text.toString().length() - 1) == 'd') goto L15;
        r5.text = LocaleUtils.applyNewSpan(r5.text.toString());
    L15:
        if (this.entriesStringAlias.containsKey(r3) == false) goto L18;
        FileLog.d("[Extera] Key '" + r3 + "' already linked to an entry.");
        return;
    L18:
        Entry r02 = this.preparedEntries.get(Integer.valueOf(generateGUIDForUItem(r4.getClass(), r5)));
        if (r02 != null) goto L21;
        r02 = Entry.fromUItem(r4, r5);
    L21:
        FileLog.d(String.format("[Extera] Added link alias %s for an entry %s", new Object[]{r3, r02}));
        this.entriesStringAlias.put(r3, r02);
    }

    public void addSearchEntry(BaseFragment r2, UItem r3) {
        if (isValidForSearch(r3) == true) goto L5;
        return;
    L5:
        Entry r0 = Entry.fromUItem(r2, r3);
        if (this.preparedEntries.containsKey(Integer.valueOf(generateGUIDForUItem(r2.getClass(), r3))) == true) goto L8;
        FileLog.d("[Extera] Added an entry: " + r0);
    L8:
        this.preparedEntries.putIfAbsent(Integer.valueOf(Entry.m1718$$Nest$fgetguid(r0)), r0);
    }

    public String getFirstSettingLink(Class<? extends BaseFragment> r1, UItem r2) {
        Map.Entry<String, Entry> r0 = this.entriesStringAlias.entrySet().stream().filter(new SettingsRegistry$$ExternalSyntheticLambda3(generateGUIDForUItem(r1, r2))).findFirst().orElse(null);
        if (r0 != null) goto L6;
        return null;
    L6:
        return "https://t.me/exteraSettings?s=" + r0.getKey();
    }

    public ProfileActivity.SearchAdapter.SearchResult[] getSearchResults(ProfileActivity.SearchAdapter r2) {
        createEntriesIfNeeded();
        return (ProfileActivity.SearchAdapter.SearchResult[]) this.preparedEntries.values().stream().map(new SettingsRegistry$$ExternalSyntheticLambda4(r2)).toArray(new SettingsRegistry$$ExternalSyntheticLambda5());
    }

    public void handleLink(String r3, String r4) {
        FileLog.d("[Extera] Setting link handler called with alias " + r3);
        if (r4 != null) goto L5;
    L8:
        createEntriesIfNeeded();
        Entry r32 = this.entriesStringAlias.get(r3);
        if (r32 != null) goto L12;
        onSettingNotFound();
        return;
    L12:
        FileLog.d("[Extera] Found entry for alias: " + r32);
        FileLog.d("[Extera] Opening fragment...");
        openActivity(Entry.m1717$$Nest$fgetfragmentClass(r32), Integer.valueOf(Entry.m1719$$Nest$fgetitemId(r32)));
        return;
    L5:
        if (TextUtils.isEmpty(r4) == true) goto L8;
        PluginsController.openPluginSettings(r4, r3);
    }

    public boolean isSearchIndexReady() {
        if (this.entriesFetched == true) goto L5;
        return false;
    L5:
        if (TextUtils.equals(this.entriesLangCode, getCurrentLangCode()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void onSettingNotFound() {
        onSettingNotFound(LaunchActivity.getLastFragment());
    }

    public void onSettingNotFound(BaseFragment r2) {
        BulletinFactory.of(r2).createEmojiBulletin("🤷\u200d♂️", LocaleController.getString(R.string.NoSuchSetting)).show();
    }
}
