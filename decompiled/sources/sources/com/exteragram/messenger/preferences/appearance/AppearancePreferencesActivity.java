package com.exteragram.messenger.preferences.appearance;

import android.content.Context;
import android.view.View;
import com.exteragram.messenger.DividerStyle;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.GlassOutlineStyle;
import com.exteragram.messenger.TabCounterMode;
import com.exteragram.messenger.TabIconsMode;
import com.exteragram.messenger.appicons.AppIcon;
import com.exteragram.messenger.appicons.AppIconController;
import com.exteragram.messenger.appicons.AppIconPreviewDrawable;
import com.exteragram.messenger.appicons.ui.AppIconsActivity;
import com.exteragram.messenger.icons.ui.IconPacksActivity;
import com.exteragram.messenger.pillstack.ui.PillStackPreferencesActivity;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import com.exteragram.messenger.preferences.SwitchGroup;
import com.exteragram.messenger.preferences.appearance.components.AvatarCornersPreviewCell;
import com.exteragram.messenger.preferences.appearance.components.ChatListPreviewCell;
import com.exteragram.messenger.preferences.appearance.components.FabShapeCell;
import com.exteragram.messenger.preferences.appearance.components.FilterTabsPreviewCell;
import com.exteragram.messenger.preferences.utils.SettingsRegistry;
import com.exteragram.messenger.utils.ExtensionsKt;
import com.exteragram.messenger.utils.chats.FolderCounters;
import com.exteragram.messenger.utils.network.RemoteUtils;
import com.exteragram.messenger.utils.text.LocaleUtils;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0013\u0018\u0000 A2\u00020\u0001:\u0002ABB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u001c\u001a\u00020\u001dH\u0014J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0014J\b\u0010\"\u001a\u00020#H\u0016J\u001e\u0010$\u001a\u00020\u001d2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&2\u0006\u0010(\u001a\u00020)H\u0014J0\u0010*\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020'2\u0006\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000200H\u0014J\b\u00102\u001a\u00020'H\u0002J\u0010\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u00020.H\u0002J\b\u00105\u001a\u00020\u001dH\u0002J\b\u00106\u001a\u00020\u001dH\u0002J\b\u00107\u001a\u00020\u001dH\u0002J\b\u00108\u001a\u00020\u001dH\u0002J\b\u00109\u001a\u00020\u001dH\u0002J\b\u0010:\u001a\u00020\u001dH\u0002J\b\u0010;\u001a\u00020\u0019H\u0002J\b\u0010<\u001a\u00020\u001dH\u0002J\b\u0010=\u001a\u00020\u001dH\u0002J\b\u0010>\u001a\u00020\u001dH\u0002J\b\u0010?\u001a\u00020\u001dH\u0002J\u0010\u0010@\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020'H\u0002R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "<init>", "()V", "titles", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/CharSequence;", "tabIconsOptions", "tabCounterModes", "dividerStyles", "glassOutlineStyles", "avatarCornersPreviewCell", "Lcom/exteragram/messenger/preferences/appearance/components/AvatarCornersPreviewCell;", "chatListPreviewCell", "Lcom/exteragram/messenger/preferences/appearance/components/ChatListPreviewCell;", "filterTabsPreviewCell", "Lcom/exteragram/messenger/preferences/appearance/components/FilterTabsPreviewCell;", "fabShapeCell", "Lcom/exteragram/messenger/preferences/appearance/components/FabShapeCell;", "appIconDrawableIcon", "Lcom/exteragram/messenger/appicons/AppIcon;", "appIconDrawableShape", _UrlKt.FRAGMENT_ENCODE_SET, "appIconDrawable", "Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;", "md3Styles", "Lcom/exteragram/messenger/preferences/SwitchGroup;", "initializeOptionStrings", _UrlKt.FRAGMENT_ENCODE_SET, "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "fillItems", PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "position", _UrlKt.FRAGMENT_ENCODE_SET, "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "createSectionRadiusSliderItem", "formatSectionRadius", "value", "handleDividerStyleChange", "handleSectionRadiusChange", "updateMD3Styles", "handleActionBarTitleClick", "handleTabTitleClick", "handleTabCounterClick", "getAppIconDrawable", "handleHideAllChatsClick", "handleUseSystemFontsClick", "handleForceBlurChange", "handleGlassMessageMenuChange", "handleUseSystemEmojiClick", "Companion", "AppearanceItem", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class AppearancePreferencesActivity extends BasePreferencesActivity {
    public static final Companion Companion = null;
    private AppIconPreviewDrawable appIconDrawable;
    private AppIcon appIconDrawableIcon;
    private boolean appIconDrawableShape;
    private AvatarCornersPreviewCell avatarCornersPreviewCell;
    private ChatListPreviewCell chatListPreviewCell;
    private CharSequence[] dividerStyles;
    private FabShapeCell fabShapeCell;
    private FilterTabsPreviewCell filterTabsPreviewCell;
    private CharSequence[] glassOutlineStyles;
    private final SwitchGroup md3Styles;
    private CharSequence[] tabCounterModes;
    private CharSequence[] tabIconsOptions;
    private CharSequence[] titles;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b(\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010)\u001a\u00020*¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(¨\u0006-"}, d2 = {"Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "AVATAR_CORNERS_PREVIEW", "SINGLE_CORNER_RADIUS", "CHAT_LIST_PREVIEW", "FORCE_SNOW", "HIDE_ACTION_BAR_STATUS", "CENTER_TITLE", "HIDE_STORIES", "HIDE_FLOATING_BUTTON", "HIDE_DIALOGS_SEARCH_BAR", "SENDER_MINI_AVATARS", "ACTION_BAR_TITLE", "PILL_STACK", "FOLDERS_PREVIEW", "TAB_TITLE", "TAB_COUNTER", "HIDE_ALL_CHATS", "APP_NAVIGATION_SETTINGS", "APP_ICON", "ICON_PACKS", "FAB_SHAPE", "SECTION_RADIUS", "SEPARATED_HEADERS", "DIVIDER_STYLE", "MD3_STYLES", "NEW_LOADING_STYLE", "NEW_SLIDER_STYLE", "NEW_SWITCH_STYLE", "USE_SYSTEM_FONTS", "USE_SYSTEM_EMOJI", "GOOEY_AVATAR_ANIMATION", "CUSTOM_THEMES", "GLASS_OUTLINE_STYLE", "FORCE_BLUR", "GLASS_MESSAGE_MENU", "NEW_CHAT_HEADER_STYLE", "NEW_NAVIGATION_BAR_STYLE", "NEW_FAB_STYLE", "id", _UrlKt.FRAGMENT_ENCODE_SET, "getId", "()I", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum AppearanceItem extends Enum<AppearanceItem> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ AppearanceItem[] $VALUES = null;
        public static final AppearanceItem ACTION_BAR_TITLE = null;
        public static final AppearanceItem APP_ICON = null;
        public static final AppearanceItem APP_NAVIGATION_SETTINGS = null;
        public static final AppearanceItem AVATAR_CORNERS_PREVIEW = null;
        public static final AppearanceItem CENTER_TITLE = null;
        public static final AppearanceItem CHAT_LIST_PREVIEW = null;
        public static final AppearanceItem CUSTOM_THEMES = null;
        public static final AppearanceItem DIVIDER_STYLE = null;
        public static final AppearanceItem FAB_SHAPE = null;
        public static final AppearanceItem FOLDERS_PREVIEW = null;
        public static final AppearanceItem FORCE_BLUR = null;
        public static final AppearanceItem FORCE_SNOW = null;
        public static final AppearanceItem GLASS_MESSAGE_MENU = null;
        public static final AppearanceItem GLASS_OUTLINE_STYLE = null;
        public static final AppearanceItem GOOEY_AVATAR_ANIMATION = null;
        public static final AppearanceItem HIDE_ACTION_BAR_STATUS = null;
        public static final AppearanceItem HIDE_ALL_CHATS = null;
        public static final AppearanceItem HIDE_DIALOGS_SEARCH_BAR = null;
        public static final AppearanceItem HIDE_FLOATING_BUTTON = null;
        public static final AppearanceItem HIDE_STORIES = null;
        public static final AppearanceItem ICON_PACKS = null;
        public static final AppearanceItem MD3_STYLES = null;
        public static final AppearanceItem NEW_CHAT_HEADER_STYLE = null;
        public static final AppearanceItem NEW_FAB_STYLE = null;
        public static final AppearanceItem NEW_LOADING_STYLE = null;
        public static final AppearanceItem NEW_NAVIGATION_BAR_STYLE = null;
        public static final AppearanceItem NEW_SLIDER_STYLE = null;
        public static final AppearanceItem NEW_SWITCH_STYLE = null;
        public static final AppearanceItem PILL_STACK = null;
        public static final AppearanceItem SECTION_RADIUS = null;
        public static final AppearanceItem SENDER_MINI_AVATARS = null;
        public static final AppearanceItem SEPARATED_HEADERS = null;
        public static final AppearanceItem SINGLE_CORNER_RADIUS = null;
        public static final AppearanceItem TAB_COUNTER = null;
        public static final AppearanceItem TAB_TITLE = null;
        public static final AppearanceItem USE_SYSTEM_EMOJI = null;
        public static final AppearanceItem USE_SYSTEM_FONTS = null;
        private final int id;

        private static final /* synthetic */ AppearanceItem[] $values() {
            return new AppearanceItem[]{AVATAR_CORNERS_PREVIEW, SINGLE_CORNER_RADIUS, CHAT_LIST_PREVIEW, FORCE_SNOW, HIDE_ACTION_BAR_STATUS, CENTER_TITLE, HIDE_STORIES, HIDE_FLOATING_BUTTON, HIDE_DIALOGS_SEARCH_BAR, SENDER_MINI_AVATARS, ACTION_BAR_TITLE, PILL_STACK, FOLDERS_PREVIEW, TAB_TITLE, TAB_COUNTER, HIDE_ALL_CHATS, APP_NAVIGATION_SETTINGS, APP_ICON, ICON_PACKS, FAB_SHAPE, SECTION_RADIUS, SEPARATED_HEADERS, DIVIDER_STYLE, MD3_STYLES, NEW_LOADING_STYLE, NEW_SLIDER_STYLE, NEW_SWITCH_STYLE, USE_SYSTEM_FONTS, USE_SYSTEM_EMOJI, GOOEY_AVATAR_ANIMATION, CUSTOM_THEMES, GLASS_OUTLINE_STYLE, FORCE_BLUR, GLASS_MESSAGE_MENU, NEW_CHAT_HEADER_STYLE, NEW_NAVIGATION_BAR_STYLE, NEW_FAB_STYLE};
        }

        static {
            AVATAR_CORNERS_PREVIEW = new AppearanceItem("AVATAR_CORNERS_PREVIEW", 0);
            SINGLE_CORNER_RADIUS = new AppearanceItem("SINGLE_CORNER_RADIUS", 1);
            CHAT_LIST_PREVIEW = new AppearanceItem("CHAT_LIST_PREVIEW", 2);
            FORCE_SNOW = new AppearanceItem("FORCE_SNOW", 3);
            HIDE_ACTION_BAR_STATUS = new AppearanceItem("HIDE_ACTION_BAR_STATUS", 4);
            CENTER_TITLE = new AppearanceItem("CENTER_TITLE", 5);
            HIDE_STORIES = new AppearanceItem("HIDE_STORIES", 6);
            HIDE_FLOATING_BUTTON = new AppearanceItem("HIDE_FLOATING_BUTTON", 7);
            HIDE_DIALOGS_SEARCH_BAR = new AppearanceItem("HIDE_DIALOGS_SEARCH_BAR", 8);
            SENDER_MINI_AVATARS = new AppearanceItem("SENDER_MINI_AVATARS", 9);
            ACTION_BAR_TITLE = new AppearanceItem("ACTION_BAR_TITLE", 10);
            PILL_STACK = new AppearanceItem("PILL_STACK", 11);
            FOLDERS_PREVIEW = new AppearanceItem("FOLDERS_PREVIEW", 12);
            TAB_TITLE = new AppearanceItem("TAB_TITLE", 13);
            TAB_COUNTER = new AppearanceItem("TAB_COUNTER", 14);
            HIDE_ALL_CHATS = new AppearanceItem("HIDE_ALL_CHATS", 15);
            APP_NAVIGATION_SETTINGS = new AppearanceItem("APP_NAVIGATION_SETTINGS", 16);
            APP_ICON = new AppearanceItem("APP_ICON", 17);
            ICON_PACKS = new AppearanceItem("ICON_PACKS", 18);
            FAB_SHAPE = new AppearanceItem("FAB_SHAPE", 19);
            SECTION_RADIUS = new AppearanceItem("SECTION_RADIUS", 20);
            SEPARATED_HEADERS = new AppearanceItem("SEPARATED_HEADERS", 21);
            DIVIDER_STYLE = new AppearanceItem("DIVIDER_STYLE", 22);
            MD3_STYLES = new AppearanceItem("MD3_STYLES", 23);
            NEW_LOADING_STYLE = new AppearanceItem("NEW_LOADING_STYLE", 24);
            NEW_SLIDER_STYLE = new AppearanceItem("NEW_SLIDER_STYLE", 25);
            NEW_SWITCH_STYLE = new AppearanceItem("NEW_SWITCH_STYLE", 26);
            USE_SYSTEM_FONTS = new AppearanceItem("USE_SYSTEM_FONTS", 27);
            USE_SYSTEM_EMOJI = new AppearanceItem("USE_SYSTEM_EMOJI", 28);
            GOOEY_AVATAR_ANIMATION = new AppearanceItem("GOOEY_AVATAR_ANIMATION", 29);
            CUSTOM_THEMES = new AppearanceItem("CUSTOM_THEMES", 30);
            GLASS_OUTLINE_STYLE = new AppearanceItem("GLASS_OUTLINE_STYLE", 31);
            FORCE_BLUR = new AppearanceItem("FORCE_BLUR", 32);
            GLASS_MESSAGE_MENU = new AppearanceItem("GLASS_MESSAGE_MENU", 33);
            NEW_CHAT_HEADER_STYLE = new AppearanceItem("NEW_CHAT_HEADER_STYLE", 34);
            NEW_NAVIGATION_BAR_STYLE = new AppearanceItem("NEW_NAVIGATION_BAR_STYLE", 35);
            NEW_FAB_STYLE = new AppearanceItem("NEW_FAB_STYLE", 36);
            AppearanceItem[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        AppearanceItem(String r1, int r2) {
            this.id = ordinal() + 1;
        }

        public static EnumEntries<AppearanceItem> getEntries() {
            return $ENTRIES;
        }

        public static AppearanceItem valueOf(String r1) {
            return (AppearanceItem) Enum.valueOf(AppearanceItem.class, r1);
        }

        public static AppearanceItem[] values() {
            return (AppearanceItem[]) $VALUES.clone();
        }

        public final int getId() {
            return this.id;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "APP_ICON_PREVIEW_SIZE_DP", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[AppearanceItem.values().length];
            r0[AppearanceItem.SINGLE_CORNER_RADIUS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L37
        L87:
            r0[AppearanceItem.ACTION_BAR_TITLE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L38
        L99:
            r0[AppearanceItem.PILL_STACK.ordinal()] = 3;     // Catch: NoSuchFieldError -> L39
        L107:
            r0[AppearanceItem.HIDE_STORIES.ordinal()] = 4;     // Catch: NoSuchFieldError -> L40
        L111:
            r0[AppearanceItem.HIDE_ACTION_BAR_STATUS.ordinal()] = 5;     // Catch: NoSuchFieldError -> L41
        L117:
            r0[AppearanceItem.CENTER_TITLE.ordinal()] = 6;     // Catch: NoSuchFieldError -> L42
        L127:
            r0[AppearanceItem.HIDE_FLOATING_BUTTON.ordinal()] = 7;     // Catch: NoSuchFieldError -> L43
        L77:
            r0[AppearanceItem.HIDE_DIALOGS_SEARCH_BAR.ordinal()] = 8;     // Catch: NoSuchFieldError -> L44
        L89:
            r0[AppearanceItem.SENDER_MINI_AVATARS.ordinal()] = 9;     // Catch: NoSuchFieldError -> L45
        L91:
            r0[AppearanceItem.TAB_TITLE.ordinal()] = 10;     // Catch: NoSuchFieldError -> L46
        L103:
            r0[AppearanceItem.TAB_COUNTER.ordinal()] = 11;     // Catch: NoSuchFieldError -> L47
        L121:
            r0[AppearanceItem.HIDE_ALL_CHATS.ordinal()] = 12;     // Catch: NoSuchFieldError -> L48
        L131:
            r0[AppearanceItem.APP_NAVIGATION_SETTINGS.ordinal()] = 13;     // Catch: NoSuchFieldError -> L49
        L69:
            r0[AppearanceItem.APP_ICON.ordinal()] = 14;     // Catch: NoSuchFieldError -> L50
        L75:
            r0[AppearanceItem.ICON_PACKS.ordinal()] = 15;     // Catch: NoSuchFieldError -> L51
        L95:
            r0[AppearanceItem.SEPARATED_HEADERS.ordinal()] = 16;     // Catch: NoSuchFieldError -> L52
        L105:
            r0[AppearanceItem.DIVIDER_STYLE.ordinal()] = 17;     // Catch: NoSuchFieldError -> L53
        L109:
            r0[AppearanceItem.USE_SYSTEM_FONTS.ordinal()] = 18;     // Catch: NoSuchFieldError -> L54
        L119:
            r0[AppearanceItem.USE_SYSTEM_EMOJI.ordinal()] = 19;     // Catch: NoSuchFieldError -> L55
        L73:
            r0[AppearanceItem.MD3_STYLES.ordinal()] = 20;     // Catch: NoSuchFieldError -> L56
        L81:
            r0[AppearanceItem.NEW_LOADING_STYLE.ordinal()] = 21;     // Catch: NoSuchFieldError -> L57
        L83:
            r0[AppearanceItem.NEW_SLIDER_STYLE.ordinal()] = 22;     // Catch: NoSuchFieldError -> L58
        L93:
            r0[AppearanceItem.NEW_SWITCH_STYLE.ordinal()] = 23;     // Catch: NoSuchFieldError -> L59
        L115:
            r0[AppearanceItem.NEW_CHAT_HEADER_STYLE.ordinal()] = 24;     // Catch: NoSuchFieldError -> L60
        L123:
            r0[AppearanceItem.NEW_NAVIGATION_BAR_STYLE.ordinal()] = 25;     // Catch: NoSuchFieldError -> L61
        L125:
            r0[AppearanceItem.NEW_FAB_STYLE.ordinal()] = 26;     // Catch: NoSuchFieldError -> L62
        L71:
            r0[AppearanceItem.GOOEY_AVATAR_ANIMATION.ordinal()] = 27;     // Catch: NoSuchFieldError -> L63
        L85:
            r0[AppearanceItem.CUSTOM_THEMES.ordinal()] = 28;     // Catch: NoSuchFieldError -> L64
        L97:
            r0[AppearanceItem.FORCE_SNOW.ordinal()] = 29;     // Catch: NoSuchFieldError -> L65
        L101:
            r0[AppearanceItem.GLASS_OUTLINE_STYLE.ordinal()] = 30;     // Catch: NoSuchFieldError -> L66
        L113:
            r0[AppearanceItem.FORCE_BLUR.ordinal()] = 31;     // Catch: NoSuchFieldError -> L67
        L129:
            r0[AppearanceItem.GLASS_MESSAGE_MENU.ordinal()] = 32;     // Catch: NoSuchFieldError -> L68
        L35:
            $EnumSwitchMapping$0 = r0;
        }
    }

    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0014¨\u0006\u0004"}, d2 = {"com/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$createView$1", "Lcom/exteragram/messenger/preferences/appearance/components/FabShapeCell;", "rebuildFragments", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.preferences.appearance.AppearancePreferencesActivity$createView$1, reason: invalid class name */
    public static final class AnonymousClass1 extends FabShapeCell {
        final /* synthetic */ AppearancePreferencesActivity this$0;

        public AnonymousClass1(Context r1, AppearancePreferencesActivity r2) {
            this.this$0 = r2;
            super(r1);
        }

        @Override // com.exteragram.messenger.preferences.appearance.components.FabShapeCell
        public void rebuildFragments() {
            AppearancePreferencesActivity.access$getParentLayout$p$s1161066243(this.this$0).rebuildFragments(0);
        }
    }

    /* renamed from: $r8$lambda$1ca0TsQHxg_ve-Cd4fD2LnWERcQ, reason: not valid java name */
    public static void m1654$r8$lambda$1ca0TsQHxg_veCd4fD2LnWERcQ(AppearancePreferencesActivity r1, int r2) {
        ExteraConfig.setDividerStyle(DividerStyle.getEntries().get(r2));
        if (ExteraConfig.getDividerStyle() != DividerStyle.SEGMENTS) goto L5;
        ExteraConfig.setSectionsSeparatedHeaders(true);
    L5:
        r1.handleDividerStyleChange();
    }

    public static void $r8$lambda$1i_bKYI7Svw2f89kxHihDzOOCPw(boolean r0) {
        ExteraConfig.setGlassMessageMenu(r0);
    }

    /* renamed from: $r8$lambda$3oT2MwPy8dc-GSiyp6BtbgfQwGQ, reason: not valid java name */
    public static void m1655$r8$lambda$3oT2MwPy8dcGSiyp6BtbgfQwGQ(boolean r0) {
        ExteraConfig.setHideAllChats(r0);
    }

    /* renamed from: $r8$lambda$8MK4p-OYTj227hoMiyoUvZFq2ZU, reason: not valid java name */
    public static void m1656$r8$lambda$8MK4pOYTj227hoMiyoUvZFq2ZU(boolean r0) {
        ExteraConfig.setForceBlur(r0);
    }

    public static void $r8$lambda$8sCJUOy7Prq_rFsAIvofP19hxxI(boolean r0) {
        ExteraConfig.setSectionsSeparatedHeaders(r0);
    }

    public static void $r8$lambda$BQktZMK8L3x7TpZfpqssg2gNJSE(AppearancePreferencesActivity r0, Integer r1) {
        ExteraConfig.setSectionRadius(r1.intValue());
        r0.handleSectionRadiusChange();
    }

    public static void $r8$lambda$BWpsglEKp91Y3TBO2mIytxIjNyw(AppearancePreferencesActivity r1, int r2) {
        ExteraConfig.setTabCounterMode(TabCounterMode.getEntries().get(r2));
        r1.handleTabCounterClick();
    }

    /* renamed from: $r8$lambda$BroMj8zTOlcCXqcxN3abB-0FVwI, reason: not valid java name */
    public static void m1657$r8$lambda$BroMj8zTOlcCXqcxN3abB0FVwI(AppearancePreferencesActivity r2, View r3) {
        if ((r3 instanceof TextCell) == true) goto L5;
        return;
    L5:
        TextCell r32 = (TextCell) r3;
        r32.getValueImageView().setVisibility(0);
        r32.getValueImageView().setImageDrawable(r2.getAppIconDrawable());
        r32.valueImageRight = (r32.heightDp - 34) / 2;
    }

    public static void $r8$lambda$F4Fe_nJRTIDZDn3cVyuEZIxTvYI(boolean r0) {
        ExteraConfig.setCenterTitle(r0);
    }

    public static void $r8$lambda$Lqn7yqkMgPDCvNKpHuRzAydUlIE(boolean r0) {
        ExteraConfig.setSingleCornerRadius(r0);
    }

    /* renamed from: $r8$lambda$LvtQV71VS-Iyx7gXN2zM6odocTU, reason: not valid java name */
    public static /* synthetic */ CharSequence m1658$r8$lambda$LvtQV71VSIyx7gXN2zM6odocTU(AppearancePreferencesActivity r0, int r1) {
        return r0.formatSectionRadius(r1);
    }

    /* renamed from: $r8$lambda$O_77vuOi_-yVX3xqwoH81AIX8Gg, reason: not valid java name */
    public static void m1659$r8$lambda$O_77vuOi_yVX3xqwoH81AIX8Gg(boolean r0) {
        ExteraConfig.setHideFloatingButton(r0);
    }

    /* renamed from: $r8$lambda$R6BuQrMpk6ELdoJwdYr15BU-lVE, reason: not valid java name */
    public static void m1660$r8$lambda$R6BuQrMpk6ELdoJwdYr15BUlVE(boolean r0) {
        ExteraConfig.setUseSystemFonts(r0);
    }

    public static void $r8$lambda$TOvQ7PIAyv4Dww5dvkxIWgyKYV0(boolean r0) {
        ExteraConfig.setCustomThemes(r0);
    }

    public static void $r8$lambda$aWZ13KjUGE1ZDBxm6o6bnXoUZog(boolean r0) {
        ExteraConfig.setHideStories(r0);
    }

    /* renamed from: $r8$lambda$adkCZXH_7cfSg-0UHOTeb2dnYio, reason: not valid java name */
    public static void m1661$r8$lambda$adkCZXH_7cfSg0UHOTeb2dnYio(boolean r0) {
        ExteraConfig.setSenderMiniAvatars(r0);
    }

    public static void $r8$lambda$bnTvQSZ4KR0iSISWLhmrYR3liWM(AppearancePreferencesActivity r0, int r1) {
        ExteraConfig.setTitleText(r1);
        r0.handleActionBarTitleClick();
    }

    public static void $r8$lambda$diPBCnaehDMw2zIgTWBxe4Djmyc(boolean r0) {
        ExteraConfig.setHideDialogsSearchBar(r0);
    }

    public static void $r8$lambda$kJCSt1WlfaYfeCZTu0oGE2JtegI(boolean r0) {
        ExteraConfig.setForceSnow(r0);
    }

    public static void $r8$lambda$kaybrtE5Evj9ZdKXmh8rIZuvcFg(AppearancePreferencesActivity r1, int r2) {
        ExteraConfig.setTabIcons(TabIconsMode.getEntries().get(r2));
        r1.handleTabTitleClick();
    }

    /* renamed from: $r8$lambda$o0XG8-7MBSw9iCPkdSYz77htMTU, reason: not valid java name */
    public static void m1662$r8$lambda$o0XG87MBSw9iCPkdSYz77htMTU(AppearancePreferencesActivity r1, int r2) {
        ExteraConfig.setGlassOutlineStyle(GlassOutlineStyle.getEntries().get(r2));
        r1.parentLayout.rebuildFragments(0);
    }

    public static void $r8$lambda$wN3nROGrT1gfQxqZrXvRJ6FnN4E(boolean r0) {
        ExteraConfig.setHideActionBarStatus(r0);
    }

    public static void $r8$lambda$ztcBy6t6GatknCPYwJLgnrCl0ns(boolean r0) {
        ExteraConfig.setGooeyAvatarAnimation(r0);
    }

    static {
        Companion = new Companion(null);
    }

    public AppearancePreferencesActivity() {
        SwitchGroup r0 = new SwitchGroup(this, AppearanceItem.MD3_STYLES, R.string.MaterialDesign3, null, "md3Styles", false, 40, null);
        r0.onChanged(new AppearancePreferencesActivity$md3Styles$1$1(this));
        SwitchGroup.add$default(r0, AppearanceItem.NEW_LOADING_STYLE, R.string.NewLoadingStyle, AppearancePreferencesActivity$md3Styles$1$2.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r0, AppearanceItem.NEW_SLIDER_STYLE, R.string.NewSliderStyle, AppearancePreferencesActivity$md3Styles$1$3.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r0, AppearanceItem.NEW_SWITCH_STYLE, R.string.NewSwitchStyle, AppearancePreferencesActivity$md3Styles$1$4.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r0, AppearanceItem.NEW_CHAT_HEADER_STYLE, R.string.ChatHeader, AppearancePreferencesActivity$md3Styles$1$5.INSTANCE, null, 8, null);
        r0.markNew("Appearance-M3Styles-ChatHeader");
        SwitchGroup.add$default(r0, AppearanceItem.NEW_NAVIGATION_BAR_STYLE, R.string.BottomNavigationBarMode, AppearancePreferencesActivity$md3Styles$1$6.INSTANCE, null, 8, null);
        r0.markNew("Appearance-M3Styles-NavigationBar");
        SwitchGroup.add$default(r0, AppearanceItem.NEW_FAB_STYLE, R.string.NewFabStyle, AppearancePreferencesActivity$md3Styles$1$7.INSTANCE, null, 8, null);
        r0.markNew("Appearance-M3Styles-FloatingButton");
        this.md3Styles = r0;
    }

    public static final /* synthetic */ INavigationLayout access$getParentLayout$p$s1161066243(AppearancePreferencesActivity r0) {
        return r0.parentLayout;
    }

    public static final /* synthetic */ void access$updateMD3Styles(AppearancePreferencesActivity r0) {
        r0.updateMD3Styles();
    }

    private final UItem createSectionRadiusSliderItem() {
        UItem r6 = UItem.asIntSlideView(1, 0, ExteraConfig.getSectionRadiusDp(), 28, new AppearancePreferencesActivity$$ExternalSyntheticLambda22(this), new AppearancePreferencesActivity$$ExternalSyntheticLambda23(this));
        r6.id = AppearanceItem.SECTION_RADIUS.getId();
        r6.text = LocaleController.getString(R.string.Sections);
        return r6;
    }

    private final CharSequence formatSectionRadius(int r1) {
        if (r1 == 0) goto L10;
        if (r1 == 28) goto L8;
        return r1 + " dp";
    L8:
        return LocaleController.getString(R.string.PredictiveBackMax);
    L10:
        return LocaleController.getString(R.string.BlurOff);
    }

    private final AppIconPreviewDrawable getAppIconDrawable() {
        AppIcon r0 = AppIconController.getSelectedIcon();
        boolean r1 = ExteraConfig.getUseSystemIconShape();
        AppIconPreviewDrawable r2 = this.appIconDrawable;
        if (r2 != null) goto L5;
    L10:
        this.appIconDrawableIcon = r0;
        this.appIconDrawableShape = r1;
        AppIconPreviewDrawable r12 = new AppIconPreviewDrawable(r0, AndroidUtilities.dp(34.0f));
        this.appIconDrawable = r12;
        return r12;
    L5:
        if (Intrinsics.areEqual(this.appIconDrawableIcon, r0) == false) goto L10;
        if (this.appIconDrawableShape != r1) goto L10;
        return r2;
    }

    private final void handleActionBarTitleClick() {
        ChatListPreviewCell r0 = this.chatListPreviewCell;
        if (r0 == null) goto L5;
        r0.updateStatus(true);
    L5:
        getNotificationCenter().postNotificationName(NotificationCenter.currentUserPremiumStatusChanged, new Object[0]);
    }

    private final void handleDividerStyleChange() {
        Theme.applyCommonTheme();
        getListView$TMessagesProj().invalidate();
        getListView$TMessagesProj().invalidateItemDecorations();
        AvatarCornersPreviewCell r0 = this.avatarCornersPreviewCell;
        if (r0 == null) goto L5;
        r0.invalidate();
    L5:
        ChatListPreviewCell r02 = this.chatListPreviewCell;
        if (r02 == null) goto L8;
        r02.invalidate();
    L8:
        FabShapeCell r03 = this.fabShapeCell;
        if (r03 == null) goto L11;
        r03.invalidate();
    L11:
        FilterTabsPreviewCell r04 = this.filterTabsPreviewCell;
        if (r04 == null) goto L14;
        r04.invalidate();
    L14:
        this.parentLayout.rebuildFragments(0);
    }

    private final void handleForceBlurChange() {
        if (SharedConfig.chatBlurEnabled() == false) goto L5;
        return;
    L5:
        if (ExteraConfig.getForceBlur() == false) goto L9;
        SharedConfig.toggleChatBlur();
        return;
    }

    private final void handleGlassMessageMenuChange() {
        if (ExteraConfig.getGlassMessageMenu() == true) goto L5;
        return;
    L5:
        if (SharedConfig.chatBlurEnabled() == true) goto L10;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.info, LocaleController.getString(R.string.GlassMessageMenuBlurOff), LocaleController.getString(R.string.Enable), new AppearancePreferencesActivity$$ExternalSyntheticLambda21()).show();
        return;
    }

    private final void handleHideAllChatsClick() {
        getNotificationCenter().postNotificationName(NotificationCenter.dialogFiltersUpdated, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
    }

    private final void handleSectionRadiusChange() {
        UniversalRecyclerView r0 = getListView$TMessagesProj();
        r0.setSections();
        r0.invalidate();
        r0.invalidateItemDecorations();
        this.parentLayout.rebuildFragments(0);
    }

    private final void handleTabCounterClick() {
        FolderCounters.Companion.recountAll();
        getNotificationCenter().postNotificationName(NotificationCenter.dialogFiltersUpdated, new Object[0]);
    }

    private final void handleTabTitleClick() {
        getNotificationCenter().postNotificationName(NotificationCenter.dialogFiltersUpdated, new Object[0]);
    }

    private final void handleUseSystemEmojiClick(UItem r3) {
        SharedConfig.toggleUseSystemEmoji();
        r3.setChecked(!SharedConfig.useSystemEmoji);
        this.parentLayout.rebuildFragments(0);
        UniversalAdapter r2 = getListView$TMessagesProj().adapter;
        if (r2 == null) goto L6;
        r2.update(true);
        return;
    }

    private final void handleUseSystemFontsClick() {
        AndroidUtilities.clearTypefaceCache();
        this.parentLayout.rebuildFragments(1);
    }

    private final void updateMD3Styles() {
        AvatarCornersPreviewCell r0 = this.avatarCornersPreviewCell;
        if (r0 == null) goto L5;
        r0.updateSliderStyle();
    L5:
        this.parentLayout.rebuildFragments(0);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r5) {
        this.avatarCornersPreviewCell = new AvatarCornersPreviewCell(r5, this, this.resourceProvider, RemoteUtils.getIntConfigValue("preferences_preview_style", 0).intValue());
        this.chatListPreviewCell = new ChatListPreviewCell(r5);
        this.filterTabsPreviewCell = new FilterTabsPreviewCell(r5);
        this.fabShapeCell = new AnonymousClass1(r5, this);
        return super.createView(r5);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r17, UniversalAdapter r18) {
        r17.add(UItem.asCustom(AppearanceItem.AVATAR_CORNERS_PREVIEW.getId(), this.avatarCornersPreviewCell).setLinkAlias("avatarCorners", this));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, AppearanceItem.SINGLE_CORNER_RADIUS, LocaleController.getString(R.string.SingleCornerRadius), ExteraConfig.getSingleCornerRadius(), null, 0, false, "singleCornerRadius", false, 0, 440, null));
        r17.add(UItem.asShadow(LocaleController.getString(R.string.SingleCornerRadiusInfo)));
        r17.add(UItem.asHeader(LocaleController.getString(R.string.ListOfChats)));
        r17.add(UItem.asCustom(AppearanceItem.CHAT_LIST_PREVIEW.getId(), this.chatListPreviewCell));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, AppearanceItem.FORCE_SNOW, LocaleController.getString(R.string.ForceSnow), ExteraConfig.getForceSnow(), LocaleController.getString(R.string.ForceSnowInfo), 0, false, "forceSnow", false, 0, 432, null));
        ExtensionsKt.addIf(r17, getUserConfig().isPremium(), BasePreferencesActivity.uCheck$TMessagesProj$default(this, AppearanceItem.HIDE_ACTION_BAR_STATUS, LocaleController.getString(R.string.HideActionBarStatus), ExteraConfig.getHideActionBarStatus(), null, 0, false, "hideActionBarStatus", false, 0, 440, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, AppearanceItem.CENTER_TITLE, LocaleController.getString(R.string.CenterTitle), ExteraConfig.getCenterTitle(), null, 0, false, "centerTitle", false, 0, 440, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, AppearanceItem.HIDE_STORIES, LocaleController.getString(R.string.HideStories), ExteraConfig.getHideStories(), null, 0, false, "hideStories", false, 0, 440, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, AppearanceItem.HIDE_FLOATING_BUTTON, LocaleController.getString(R.string.HideFloatingButton), ExteraConfig.getHideFloatingButton(), null, 0, false, "hideFloatingButton", false, 0, 440, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, AppearanceItem.HIDE_DIALOGS_SEARCH_BAR, LocaleController.getString(R.string.HideDialogsSearchBar), ExteraConfig.getHideDialogsSearchBar(), null, 0, false, "hideDialogsSearchBar", false, 0, 440, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, AppearanceItem.SENDER_MINI_AVATARS, LocaleController.getString(R.string.SenderMiniAvatars), ExteraConfig.getSenderMiniAvatars(), null, 0, false, "senderMiniAvatars", false, 0, 440, null));
        AppearanceItem r1 = AppearanceItem.ACTION_BAR_TITLE;
        String r2 = LocaleController.getString(R.string.ActionBarTitle);
        CharSequence[] r3 = this.titles;
        CharSequence[] r15 = null;
        if (r3 != null) goto L5;
        r3 = null;
    L5:
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, r1, r2, 0, r3[ExteraConfig.getTitleText()], null, null, false, "actionBarTitle", false, false, null, 1908, null));
        r17.add(UItem.asShadow(LocaleController.getString(R.string.ListOfChatsInfo)));
        r17.add(UItem.asHeader(LocaleController.getString(R.string.Filters)));
        r17.add(UItem.asCustom(AppearanceItem.FOLDERS_PREVIEW.getId(), this.filterTabsPreviewCell));
        AppearanceItem r12 = AppearanceItem.TAB_TITLE;
        String r22 = LocaleController.getString(R.string.TabTitleStyle);
        CharSequence[] r32 = this.tabIconsOptions;
        if (r32 != null) goto L8;
        r32 = null;
    L8:
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, r12, r22, 0, r32[TabIconsMode.getEntries().indexOf(ExteraConfig.getTabIcons())], null, null, false, "tabTitleStyle", false, false, null, 1908, null));
        AppearanceItem r13 = AppearanceItem.TAB_COUNTER;
        String r23 = LocaleController.getString(R.string.TabCounter);
        CharSequence[] r33 = this.tabCounterModes;
        if (r33 != null) goto L11;
        r33 = null;
    L11:
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, r13, r23, 0, r33[ExteraConfig.getTabCounterMode().ordinal()], null, null, false, "tabCounter", false, false, null, 1908, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, AppearanceItem.HIDE_ALL_CHATS, LocaleController.formatString(R.string.HideAllChats, new Object[]{LocaleController.getString(R.string.FilterAllChats)}), ExteraConfig.getHideAllChats(), null, 0, false, "hideAllChats", false, 0, 440, null));
        r17.add(UItem.asShadow(LocaleController.getString(R.string.FoldersInfo)));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, AppearanceItem.APP_NAVIGATION_SETTINGS, LocaleController.getString(R.string.AppNavigation), R.drawable.msg_newphone, null, LocaleController.getString(R.string.AppNavigationInfo), null, false, "appNavigationSettings", false, false, null, 1896, null));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, AppearanceItem.APP_ICON, LocaleController.getString(R.string.AppIcon), R.drawable.menu_edit_appearance, null, AppIconController.getSelectedIcon().getTitle(), null, false, "appIcon", false, false, null, 1896, null).onBind(new AppearancePreferencesActivity$$ExternalSyntheticLambda20(this)));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, AppearanceItem.ICON_PACKS, LocaleController.getString(R.string.IconPacks), R.drawable.msg_sticker, null, LocaleController.getString(R.string.IconPacksInfo), null, false, "iconPacks", false, false, null, 1896, null));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, AppearanceItem.PILL_STACK, LocaleController.getString(R.string.PillStackPills), R.drawable.outline_header_search, null, LocaleController.getString(R.string.PillStackPillsInfo), null, false, "pillStack", false, false, null, 1896, null));
        r17.add(UItem.asShadow());
        r17.add(UItem.asHeader(LocaleController.getString(R.string.Appearance)));
        r17.add(UItem.asCustom(AppearanceItem.FAB_SHAPE.getId(), this.fabShapeCell).setLinkAlias("fabShape", this));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, AppearanceItem.USE_SYSTEM_FONTS, LocaleController.getString(R.string.UseSystemFonts), ExteraConfig.getUseSystemFonts(), null, 0, false, "useSystemFonts", false, 0, 440, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, AppearanceItem.USE_SYSTEM_EMOJI, LocaleController.getString(R.string.UseSystemEmoji), SharedConfig.useSystemEmoji, null, 0, false, "useSystemEmoji", false, 0, 440, null));
        this.md3Styles.fill(r17);
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, AppearanceItem.GOOEY_AVATAR_ANIMATION, LocaleController.getString(R.string.GooeyAvatarAnimation), ExteraConfig.getGooeyAvatarAnimation(), null, 0, false, "gooeyAvatarAnimation", false, 0, 440, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, AppearanceItem.CUSTOM_THEMES, LocaleController.getString(R.string.CustomChatThemes), ExteraConfig.getCustomThemes(), null, 0, false, "customThemes", false, 0, 440, null));
        r17.add(UItem.asShadow(LocaleController.getString(R.string.CustomChatThemesInfo)));
        if (SettingsRegistry.markAsNewFeature("Appearance-Sections") == false) goto L14;
        CharSequence r14 = LocaleUtils.applyNewSpan(LocaleController.getString(R.string.Sections));
    L15:
        r17.add(UItem.asHeader(r14));
        r17.add(createSectionRadiusSliderItem().setSearchable(this).setLinkAlias("sectionRadius", this));
        if (ExteraConfig.getDividerStyle() != DividerStyle.SEGMENTS) goto L18;
        boolean r132 = true;
    L19:
        UItem r16 = BasePreferencesActivity.uCheck$TMessagesProj$default(this, AppearanceItem.SEPARATED_HEADERS, LocaleController.getString(R.string.SeparateHeaders), ExteraConfig.getSectionsSeparatedHeaders(), null, 0, false, "sectionsSeparatedHeaders", false, 0, 440, null);
        r16.setEnabled(!r132);
        r17.add(r16);
        AppearanceItem r19 = AppearanceItem.DIVIDER_STYLE;
        String r24 = LocaleController.getString(R.string.DividerStyle);
        CharSequence[] r34 = this.dividerStyles;
        if (r34 != null) goto L22;
        r34 = null;
    L22:
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, r19, r24, 0, r34[ExteraConfig.getDividerStyle().ordinal()], null, null, false, "dividerStyle", false, false, null, 1908, null));
        r17.add(UItem.asShadow());
        r17.add(UItem.asHeader(LocaleController.getString(R.string.BlurOptions)));
        AppearanceItem r110 = AppearanceItem.GLASS_OUTLINE_STYLE;
        String r25 = LocaleController.getString(R.string.GlassOutlineStyle);
        CharSequence[] r35 = this.glassOutlineStyles;
        if (r35 == null) goto L26;
        r15 = r35;
    L26:
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, r110, r25, 0, r15[ExteraConfig.getGlassOutlineStyle().ordinal()], null, null, false, "glassOutlineStyle", false, false, null, 1908, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, AppearanceItem.GLASS_MESSAGE_MENU, LocaleController.getString(R.string.GlassMessageMenu), ExteraConfig.getGlassMessageMenu(), LocaleController.getString(R.string.GlassMessageMenuInfo), 0, false, "glassMessageMenu", false, 0, 432, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, AppearanceItem.FORCE_BLUR, LocaleController.getString(R.string.ForceBlur), ExteraConfig.getForceBlur(), null, 0, false, "forceBlur", false, 0, 440, null));
        r17.add(UItem.asShadow(LocaleController.getString(R.string.ForceBlurInfo)));
        return;
    L18:
        r132 = false;
        goto L19
    L14:
        r14 = LocaleController.getString(R.string.Sections);
        goto L15
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return LocaleController.getString(R.string.Appearance);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void initializeOptionStrings() {
        this.titles = new CharSequence[]{LocaleController.getString(R.string.exteraAppName), LocaleController.getString(R.string.ActionBarTitleUsername), LocaleController.getString(R.string.ActionBarTitleName), LocaleController.getString(R.string.FilterChats)};
        this.tabIconsOptions = new CharSequence[]{LocaleController.getString(R.string.TabTitleStyleTextWithIcons), LocaleController.getString(R.string.TabTitleStyleTextOnly), LocaleController.getString(R.string.TabTitleStyleIconsOnly)};
        this.tabCounterModes = new CharSequence[]{LocaleController.getString(R.string.FilterAllChats), LocaleController.getString(R.string.TabCounterUnmuted), LocaleController.getString(R.string.BlurOff)};
        this.dividerStyles = new CharSequence[]{LocaleController.getString(R.string.DividerStyleHidden), LocaleController.getString(R.string.DividerStyleLine), LocaleController.getString(R.string.DividerStyleSegments)};
        this.glassOutlineStyles = new CharSequence[]{LocaleController.getString(R.string.GlassOutlineGlare), LocaleController.getString(R.string.GlassOutlineSolid), LocaleController.getString(R.string.GlassOutlineHidden)};
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r7, View r8, int r9, float r10, float r11) {
        int r82 = r7.id;
        if (r82 > 0) goto L5;
        return;
    L5:
        if (r82 <= AppearanceItem.getEntries().size()) goto L8;
        return;
    L8:
        switch(WhenMappings.$EnumSwitchMapping$0[AppearanceItem.getEntries().get(r7.id - 1).ordinal()]) {
            case 1: goto L87;
            case 2: goto L82;
            case 3: goto L80;
            case 4: goto L78;
            case 5: goto L73;
            case 6: goto L68;
            case 7: goto L66;
            case 8: goto L64;
            case 9: goto L62;
            case 10: goto L57;
            case 11: goto L52;
            case 12: goto L50;
            case 13: goto L48;
            case 14: goto L46;
            case 15: goto L44;
            case 16: goto L40;
            case 17: goto L34;
            case 18: goto L32;
            case 19: goto L30;
            case 20: goto L28;
            case 21: goto L28;
            case 22: goto L28;
            case 23: goto L28;
            case 24: goto L28;
            case 25: goto L28;
            case 26: goto L28;
            case 27: goto L26;
            case 28: goto L24;
            case 29: goto L20;
            case 30: goto L14;
            case 31: goto L12;
            case 32: goto L10;
            default: goto L92;
        };
    L10:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new AppearancePreferencesActivity$$ExternalSyntheticLambda10());
        handleGlassMessageMenuChange();
        return;
    L12:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new AppearancePreferencesActivity$$ExternalSyntheticLambda9());
        handleForceBlurChange();
        return;
    L14:
        CharSequence[] r83 = this.glassOutlineStyles;
        if (r83 != null) goto L17;
        CharSequence[] r2 = null;
    L18:
        showListDialog(r7, r2, LocaleController.getString(R.string.GlassOutlineStyle), ExteraConfig.getGlassOutlineStyle().ordinal(), new AppearancePreferencesActivity$$ExternalSyntheticLambda8(this));
        return;
    L17:
        r2 = r83;
        goto L18
    L20:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new AppearancePreferencesActivity$$ExternalSyntheticLambda7());
        ChatListPreviewCell r6 = this.chatListPreviewCell;
        if (r6 == null) goto L90;
        r6.invalidate();
        return;
    L90:
        return;
    L24:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new AppearancePreferencesActivity$$ExternalSyntheticLambda6());
        return;
    L26:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new AppearancePreferencesActivity$$ExternalSyntheticLambda5());
        return;
    L28:
        this.md3Styles.onClick(r7);
        return;
    L30:
        handleUseSystemEmojiClick(r7);
        return;
    L32:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new AppearancePreferencesActivity$$ExternalSyntheticLambda4());
        handleUseSystemFontsClick();
        return;
    L34:
        CharSequence[] r84 = this.dividerStyles;
        if (r84 != null) goto L37;
        r84 = null;
    L37:
        showListDialog(r7, r84, LocaleController.getString(R.string.DividerStyle), ExteraConfig.getDividerStyle().ordinal(), new AppearancePreferencesActivity$$ExternalSyntheticLambda3(this));
        return;
    L44:
        presentFragment(new IconPacksActivity());
        return;
    L46:
        presentFragment(new AppIconsActivity());
        return;
    L48:
        presentFragment(new AppNavigationPreferencesActivity());
        return;
    L50:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new AppearancePreferencesActivity$$ExternalSyntheticLambda1());
        handleHideAllChatsClick();
        return;
    L52:
        CharSequence[] r85 = this.tabCounterModes;
        if (r85 != null) goto L55;
        r85 = null;
    L55:
        showListDialog(r7, r85, LocaleController.getString(R.string.TabCounter), ExteraConfig.getTabCounterMode().ordinal(), new AppearancePreferencesActivity$$ExternalSyntheticLambda19(this));
        return;
    L57:
        CharSequence[] r86 = this.tabIconsOptions;
        if (r86 != null) goto L60;
        r86 = null;
    L60:
        showListDialog(r7, r86, LocaleController.getString(R.string.TabTitleStyle), TabIconsMode.getEntries().indexOf(ExteraConfig.getTabIcons()), new AppearancePreferencesActivity$$ExternalSyntheticLambda18(this));
        return;
    L62:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new AppearancePreferencesActivity$$ExternalSyntheticLambda17());
        return;
    L64:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new AppearancePreferencesActivity$$ExternalSyntheticLambda16());
        this.parentLayout.rebuildFragments(0);
        return;
    L66:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new AppearancePreferencesActivity$$ExternalSyntheticLambda15());
        this.parentLayout.rebuildFragments(0);
        return;
    L68:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new AppearancePreferencesActivity$$ExternalSyntheticLambda14());
        ChatListPreviewCell r72 = this.chatListPreviewCell;
        if (r72 == null) goto L71;
        r72.updateCentered(true);
    L71:
        this.actionBar.refreshTitlePosition(true);
        this.parentLayout.rebuildFragments(0);
        return;
    L73:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new AppearancePreferencesActivity$$ExternalSyntheticLambda13());
        ChatListPreviewCell r73 = this.chatListPreviewCell;
        if (r73 == null) goto L76;
        r73.updateStatus(true);
    L76:
        this.parentLayout.rebuildFragments(0);
        return;
    L78:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new AppearancePreferencesActivity$$ExternalSyntheticLambda12());
        getNotificationCenter().postNotificationName(NotificationCenter.storiesEnabledUpdate, new Object[0]);
        return;
    L80:
        presentFragment(new PillStackPreferencesActivity());
        return;
    L82:
        CharSequence[] r87 = this.titles;
        if (r87 != null) goto L85;
        r87 = null;
    L85:
        showListDialog(r7, r87, LocaleController.getString(R.string.ActionBarTitle), ExteraConfig.getTitleText(), new AppearancePreferencesActivity$$ExternalSyntheticLambda11(this));
        return;
    L87:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new AppearancePreferencesActivity$$ExternalSyntheticLambda0());
        this.parentLayout.rebuildFragments(0);
        return;
    L92:
        return;
    L40:
        if (ExteraConfig.getDividerStyle() == DividerStyle.SEGMENTS) goto L93;
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new AppearancePreferencesActivity$$ExternalSyntheticLambda2());
        getListView$TMessagesProj().invalidateItemDecorations();
        return;
    }
}
