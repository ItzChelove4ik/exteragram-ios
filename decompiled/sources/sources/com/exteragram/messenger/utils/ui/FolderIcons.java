package com.exteragram.messenger.utils.ui;

import androidx.core.util.Pair;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.TabIconsMode;
import java.util.LinkedHashMap;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;

/* loaded from: classes.dex */
public abstract class FolderIcons {
    public static LinkedHashMap<String, Integer> folderIcons;

    static {
        LinkedHashMap<String, Integer> r0 = new LinkedHashMap();
        folderIcons = r0;
        r0.put("🐱", Integer.valueOf(R.drawable.filter_cat));
        folderIcons.put("📕", Integer.valueOf(R.drawable.filter_book));
        folderIcons.put("💰", Integer.valueOf(R.drawable.filter_money));
        folderIcons.put("🎮", Integer.valueOf(R.drawable.filter_game));
        folderIcons.put("💡", Integer.valueOf(R.drawable.filter_light));
        folderIcons.put("👌", Integer.valueOf(R.drawable.filter_like));
        folderIcons.put("🎵", Integer.valueOf(R.drawable.filter_note));
        folderIcons.put("🎨", Integer.valueOf(R.drawable.filter_palette));
        folderIcons.put("✈", Integer.valueOf(R.drawable.filter_travel));
        folderIcons.put("⚽", Integer.valueOf(R.drawable.filter_sport));
        folderIcons.put("⭐", Integer.valueOf(R.drawable.filter_favorite));
        folderIcons.put("🎓", Integer.valueOf(R.drawable.filter_study));
        folderIcons.put("🛫", Integer.valueOf(R.drawable.filter_airplane));
        folderIcons.put("👤", Integer.valueOf(R.drawable.filter_private));
        folderIcons.put("👥", Integer.valueOf(R.drawable.filter_group));
        folderIcons.put("💬", Integer.valueOf(R.drawable.filter_all));
        folderIcons.put("✅", Integer.valueOf(R.drawable.filter_unread));
        folderIcons.put("🤖", Integer.valueOf(R.drawable.filter_bots));
        folderIcons.put("👑", Integer.valueOf(R.drawable.filter_crown));
        folderIcons.put("🌹", Integer.valueOf(R.drawable.filter_flower));
        folderIcons.put("🏠", Integer.valueOf(R.drawable.filter_home));
        folderIcons.put("❤", Integer.valueOf(R.drawable.filter_love));
        folderIcons.put("🎭", Integer.valueOf(R.drawable.filter_mask));
        folderIcons.put("🍸", Integer.valueOf(R.drawable.filter_party));
        folderIcons.put("📈", Integer.valueOf(R.drawable.filter_trade));
        folderIcons.put("💼", Integer.valueOf(R.drawable.filter_work));
        folderIcons.put("🔔", Integer.valueOf(R.drawable.filter_unmuted));
        folderIcons.put("📢", Integer.valueOf(R.drawable.filter_channels));
        folderIcons.put("📁", Integer.valueOf(R.drawable.filter_custom));
        folderIcons.put("📋", Integer.valueOf(R.drawable.filter_setup));
    }

    public static Pair<String, String> getEmoticonFromFlags(int r3) {
        int r0 = MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS;
        int r1 = r3 & r0;
        if ((r1 & r0) == r0) goto L5;
        int r32 = MessagesController.DIALOG_FILTER_FLAG_CONTACTS;
        if ((r1 & r32) == 0) goto L21;
        int r33 = (~r32) & r1;
        if (r33 != 0) goto L16;
        String r34 = LocaleController.getString(R.string.FilterContacts);
    L15:
        String r02 = "👤";
    L43:
        return Pair.create(r34, r02);
    L16:
        int r03 = MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS;
        if ((r33 & r03) != 0) goto L19;
    L41:
        r34 = _UrlKt.FRAGMENT_ENCODE_SET;
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L43
    L19:
        if ((r33 & (~r03)) != 0) goto L41;
        r34 = LocaleController.getString(R.string.FilterContacts);
        goto L15
    L21:
        int r35 = MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS;
        if ((r1 & r35) != 0) goto L24;
        int r36 = MessagesController.DIALOG_FILTER_FLAG_GROUPS;
        if ((r1 & r36) != 0) goto L29;
        int r37 = MessagesController.DIALOG_FILTER_FLAG_BOTS;
        if ((r1 & r37) != 0) goto L34;
        int r38 = MessagesController.DIALOG_FILTER_FLAG_CHANNELS;
        if ((r1 & r38) == 0) goto L41;
        if (((~r38) & r1) != 0) goto L41;
        r34 = LocaleController.getString(R.string.FilterChannels);
        r02 = "📢";
        goto L43
    L34:
        if (((~r37) & r1) != 0) goto L41;
        r34 = LocaleController.getString(R.string.FilterBots);
        r02 = "🤖";
        goto L43
    L29:
        if (((~r36) & r1) != 0) goto L41;
        r34 = LocaleController.getString(R.string.FilterGroups);
        r02 = "👥";
        goto L43
    L24:
        if (((~r35) & r1) != 0) goto L41;
        r34 = LocaleController.getString(R.string.FilterNonContacts);
        goto L15
    L5:
        if ((MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_READ & r3) == 0) goto L8;
        r34 = LocaleController.getString(R.string.FilterNameUnread);
        r02 = "✅";
        goto L43
    L8:
        if ((r3 & MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_MUTED) == 0) goto L41;
        r34 = LocaleController.getString(R.string.FilterNameNonMuted);
        r02 = "🔔";
        goto L43
    }

    public static int getIconWidth() {
        return AndroidUtilities.dp(24.0f);
    }

    public static int getPadding() {
        if (ExteraConfig.getTabIcons() == TabIconsMode.ICONS_AND_TITLES) goto L5;
        return 0;
    L5:
        return AndroidUtilities.dp(4.0f);
    }

    public static int getPaddingTab() {
        return AndroidUtilities.dp(24.0f);
    }

    public static int getTabIcon(String r1) {
        if (r1 == null) goto L8;
        Integer r12 = folderIcons.get(r1);
        if (r12 == null) goto L8;
        return r12.intValue();
    L8:
        return R.drawable.filter_custom;
    }

    public static int getTotalIconWidth() {
        if (ExteraConfig.getTabIcons() != TabIconsMode.TITLES_ONLY) goto L5;
        return 0;
    L5:
        return getIconWidth() + getPadding();
    }
}
