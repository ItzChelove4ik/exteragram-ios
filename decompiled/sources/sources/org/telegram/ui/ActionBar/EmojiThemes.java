package org.telegram.ui.ActionBar;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseIntArray;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatThemeController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.tlutils.TlUtils;
import org.telegram.messenger.wallpaper.WallpaperBitmapHolder;
import org.telegram.messenger.wallpaper.WallpaperGiftBitmapDrawable;
import org.telegram.messenger.wallpaper.WallpaperGiftPatternPosition;
import org.telegram.tgnet.ResultCallback;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.theme.ITheme;
import org.telegram.ui.ActionBar.theme.ThemeKey;

/* loaded from: classes3.dex */
public class EmojiThemes {
    private static final int[] previewColorKeys = null;
    public TLRPC.ChatTheme chatTheme;
    private final int currentAccount;
    int currentIndex;
    public String emoji;
    public ArrayList<ThemeItem> items;
    public ThemeKey key;
    public boolean showAsDefaultStub;
    public boolean showAsRemovedStub;
    public TLRPC.WallPaper wallpaper;

    /* loaded from: classes6.dex */
    public interface ColorsLoadedCallback {
        void onColorsLoaded();
    }

    public static class ThemeItem implements ITheme {
        public int accentId;
        public SparseIntArray currentPreviewColors;
        public int inBubbleColor;
        public int outBubbleColor;
        public int outLineColor;
        public int patternBgColor;
        public int patternBgGradientColor1;
        public int patternBgGradientColor2;
        public int patternBgGradientColor3;
        public int patternBgRotation;
        int settingsIndex;
        public Theme.ThemeInfo themeInfo;
        TLRPC.TL_chatThemeUniqueGift tlChatThemeGift;
        TLRPC.TL_theme tlTheme;
        private String wallpaperLink;

        /* renamed from: -$$Nest$fgetwallpaperLink, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m5751$$Nest$fgetwallpaperLink(ThemeItem r0) {
            return r0.wallpaperLink;
        }

        /* renamed from: -$$Nest$fputwallpaperLink, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5752$$Nest$fputwallpaperLink(ThemeItem r0, String r1) {
            r0.wallpaperLink = r1;
        }

        public ThemeItem() {
            this.accentId = -1;
        }

        @Override // org.telegram.ui.ActionBar.theme.ITheme
        public long getThemeId() {
            TLRPC.TL_theme r0 = this.tlTheme;
            if (r0 != null) goto L5;
            TLRPC.TL_chatThemeUniqueGift r2 = this.tlChatThemeGift;
            if (r2 != null) goto L9;
            return 0;
        L9:
            return r2.gift.gift_id;
        L5:
            return r0.id;
        }

        @Override // org.telegram.ui.ActionBar.theme.ITheme
        public TLRPC.ThemeSettings getThemeSettings(int r3) {
            TLRPC.TL_theme r0 = this.tlTheme;
            if (r0 == null) goto L5;
            ArrayList<TLRPC.ThemeSettings> r2 = r0.settings;
        L8:
            if (r2 == null) goto L14;
            if (r3 < 0) goto L14;
            if (r2.size() <= r3) goto L14;
            return r2.get(r3);
        L14:
            return null;
        L5:
            TLRPC.TL_chatThemeUniqueGift r22 = this.tlChatThemeGift;
            if (r22 == null) goto L14;
            r2 = r22.theme_settings;
            goto L8
        }

        public TLRPC.WallPaper getThemeWallPaper(int r1) {
            TLRPC.ThemeSettings r0 = getThemeSettings(r1);
            if (r0 != null) goto L5;
            return null;
        L5:
            return r0.wallpaper;
        }
    }

    public static /* synthetic */ void $r8$lambda$9EcbEa6nTIyD4XFtSD5qj8xCzVE(File r2, Bitmap r3) {
        FileOutputStream r0 = new FileOutputStream(r2);     // Catch: Exception -> L12
        r3.compress(Bitmap.CompressFormat.PNG, 87, r0);     // Catch: Throwable -> L6
        r0.close();     // Catch: Exception -> L12
        return;
    L6:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L9
    L11:
        throw th;     // Catch: Exception -> L12
    L9:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L12
    L12:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$AvX0wQGb28fjzxukk3muL7Rltzs(EmojiThemes r0, ColorsLoadedCallback r1) {
        r0.lambda$initColors$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$HUzepghFisrH5drtBM375ntzctQ(ResultCallback r0, long r1, ImageReceiver r3, boolean r4, boolean r5, boolean r6) {
        ImageReceiver.BitmapHolder r32 = r3.getBitmapSafe();
        if (r4 == false) goto L14;
        if (r32 == null) goto L16;
        Bitmap r42 = r32.bitmap;
        if (r42 != null) goto L11;
        Drawable r33 = r32.drawable;
        if ((r33 instanceof BitmapDrawable) == false) goto L11;
        r42 = ((BitmapDrawable) r33).getBitmap();
    L11:
        if (r0 == null) goto L15;
        r0.onComplete(new Pair(Long.valueOf(r1), r42));
        return;
    L15:
        return;
    L16:
        return;
    }

    public static /* synthetic */ void $r8$lambda$VCkI0d8L2PeP2148z7K7_BYAE3Y(Utilities.Callback r7, TLRPC.WallPaper r8, int r9, int r10, long r11, WallpaperBitmapHolder r13) {
        if (r13 == null) goto L6;
        if (r7 == null) goto L6;
        r7.run(r13);
        return;
    L6:
        ImageLocation r1 = ImageLocation.getForDocument(r8.document);
        ImageReceiver r0 = new ImageReceiver();
        r0.setAllowLoadingOnAttachedOnly(false);
        Point r132 = AndroidUtilities.displaySize;
        int r133 = Math.min(r132.x, r132.y);
        Point r2 = AndroidUtilities.displaySize;
        r0.setImage(r1, (r133 / AndroidUtilities.density) + "_" + (Math.max(r2.x, r2.y) / AndroidUtilities.density) + "_f", null, ".jpg", r8, 1);
        r0.setDelegate(new EmojiThemes$$ExternalSyntheticLambda6(r9, r7, r10, r11));
        ImageLoader.getInstance().loadImageForImageReceiver(r0);
    }

    /* renamed from: $r8$lambda$jK27KEF1yFf-zj46vlIaI1Pon0c, reason: not valid java name */
    public static /* synthetic */ void m5749$r8$lambda$jK27KEF1yFfzj46vlIaI1Pon0c(ResultCallback r0, long r1, File r3, ImageReceiver r4, boolean r5, boolean r6, boolean r7) {
        ImageReceiver.BitmapHolder r42 = r4.getBitmapSafe();
        if (r5 == false) goto L21;
        if (r42 != null) goto L6;
        return;
    L6:
        if (r42.bitmap.isRecycled() == true) goto L24;
        Bitmap r52 = r42.bitmap;
        if (r52 != null) goto L13;
        Drawable r43 = r42.drawable;
        if ((r43 instanceof BitmapDrawable) == false) goto L13;
        r52 = ((BitmapDrawable) r43).getBitmap();
    L13:
        if (r52 == null) goto L18;
        if (r0 == null) goto L16;
        r0.onComplete(new Pair(Long.valueOf(r1), r52));
    L16:
        Utilities.globalQueue.postRunnable(new EmojiThemes$$ExternalSyntheticLambda5(r3, r52));
        return;
    L18:
        if (r0 == null) goto L23;
        r0.onComplete(null);
        return;
    L23:
        return;
    L24:
        return;
    }

    public static /* synthetic */ void $r8$lambda$nI0EPMEf3fJW0kk3pTsEpmqvnGU(ResultCallback r1, long r2, WallpaperBitmapHolder r4) {
        if (r1 == null) goto L5;
        r1.onComplete(new Pair(Long.valueOf(r2), r4));
        return;
    }

    public static /* synthetic */ void $r8$lambda$nIpUDzSgZp8aPztPq9D3lh5zA1s(ColorsLoadedCallback r0) {
        if (r0 == null) goto L5;
        r0.onColorsLoaded();
        return;
    }

    /* renamed from: $r8$lambda$y3j-iPzp6fdq2s-i5rHBAam_1H8, reason: not valid java name */
    public static /* synthetic */ void m5750$r8$lambda$y3jiPzp6fdq2si5rHBAam_1H8(int r0, Utilities.Callback r1, int r2, long r3, ImageReceiver r5, boolean r6, boolean r7, boolean r8) {
        ImageReceiver.BitmapHolder r72 = r5.getBitmapSafe();
        ImageReceiver.BitmapHolder r52 = r5.getDrawableSafe();
        if (r6 == false) goto L21;
        if (r72 == null) goto L22;
        if (r52 == null) goto L10;
        Drawable r53 = r52.drawable;
        if ((r53 instanceof WallpaperGiftBitmapDrawable) == false) goto L10;
        List<WallpaperGiftPatternPosition> r54 = ((WallpaperGiftBitmapDrawable) r53).patternPositions;
    L11:
        Bitmap r62 = r72.bitmap;
        if (r62 != null) goto L16;
        Drawable r73 = r72.drawable;
        if ((r73 instanceof BitmapDrawable) == false) goto L16;
        r62 = ((BitmapDrawable) r73).getBitmap();
    L16:
        WallpaperBitmapHolder r74 = new WallpaperBitmapHolder(r62, r0, r54);
        if (r1 == null) goto L19;
        r1.run(r74);
    L19:
        ChatThemeController.getInstance(r2).saveWallpaperBitmap(r74, r3);
        return;
    L10:
        r54 = null;
        goto L11
    L22:
        return;
    }

    static {
        previewColorKeys = new int[]{Theme.key_chat_inBubble, Theme.key_chat_outBubble, Theme.key_featuredStickers_addButton, Theme.key_chat_wallpaper, Theme.key_chat_wallpaper_gradient_to1, Theme.key_chat_wallpaper_gradient_to2, Theme.key_chat_wallpaper_gradient_to3, Theme.key_chat_wallpaper_gradient_rotation};
    }

    public EmojiThemes(int r2) {
        this.currentIndex = 0;
        this.items = new ArrayList();
        this.currentAccount = r2;
    }

    public EmojiThemes(int r3, TLRPC.TL_chatThemeUniqueGift r4) {
        this.currentIndex = 0;
        this.items = new ArrayList();
        this.currentAccount = r3;
        this.showAsDefaultStub = false;
        this.emoji = r4.gift.slug;
        this.key = ThemeKey.of(r4);
        this.chatTheme = r4;
        ThemeItem r32 = new ThemeItem();
        r32.tlChatThemeGift = r4;
        r32.settingsIndex = 0;
        this.items.add(r32);
        ThemeItem r33 = new ThemeItem();
        r33.tlChatThemeGift = r4;
        r33.settingsIndex = 1;
        this.items.add(r33);
    }

    public EmojiThemes(int r3, TLRPC.TL_theme r4, boolean r5) {
        this.currentIndex = 0;
        this.items = new ArrayList();
        this.currentAccount = r3;
        this.showAsDefaultStub = r5;
        this.emoji = r4.emoticon;
        this.key = ThemeKey.of(r4);
        this.chatTheme = TLRPC.ChatTheme.ofEmoticon(r4.emoticon);
        if (r5 == true) goto L6;
        ThemeItem r32 = new ThemeItem();
        r32.tlTheme = r4;
        r32.settingsIndex = 0;
        this.items.add(r32);
        ThemeItem r33 = new ThemeItem();
        r33.tlTheme = r4;
        r33.settingsIndex = 1;
        this.items.add(r33);
        return;
    }

    public static EmojiThemes createChatThemesDefault(int r2) {
        EmojiThemes r0 = new EmojiThemes(r2);
        r0.emoji = "❌";
        r0.key = ThemeKey.ofEmoticon("❌");
        r0.chatTheme = TLRPC.ChatTheme.ofEmoticon("❌");
        r0.showAsDefaultStub = true;
        ThemeItem r1 = new ThemeItem();
        r1.themeInfo = getDefaultThemeInfo(true);
        r0.items.add(r1);
        ThemeItem r22 = new ThemeItem();
        r22.themeInfo = getDefaultThemeInfo(false);
        r0.items.add(r22);
        return r0;
    }

    public static EmojiThemes createChatThemesRemoved(int r2) {
        EmojiThemes r0 = new EmojiThemes(r2);
        r0.emoji = "❌";
        r0.key = ThemeKey.ofEmoticon("❌");
        r0.chatTheme = TLRPC.ChatTheme.ofEmoticon("❌");
        r0.showAsRemovedStub = true;
        ThemeItem r1 = new ThemeItem();
        r1.themeInfo = getDefaultThemeInfo(true);
        r0.items.add(r1);
        ThemeItem r22 = new ThemeItem();
        r22.themeInfo = getDefaultThemeInfo(false);
        r0.items.add(r22);
        return r0;
    }

    public static EmojiThemes createHomePreviewTheme(int r3) {
        EmojiThemes r0 = new EmojiThemes(r3);
        r0.emoji = "🏠";
        r0.key = ThemeKey.ofEmoticon("🏠");
        r0.chatTheme = TLRPC.ChatTheme.ofEmoticon(r0.emoji);
        ThemeItem r32 = new ThemeItem();
        r32.themeInfo = Theme.getTheme("Blue");
        r32.accentId = 99;
        r0.items.add(r32);
        ThemeItem r33 = new ThemeItem();
        r33.themeInfo = Theme.getTheme("Day");
        r33.accentId = 9;
        r0.items.add(r33);
        ThemeItem r34 = new ThemeItem();
        r34.themeInfo = Theme.getTheme("Night");
        r34.accentId = 0;
        r0.items.add(r34);
        ThemeItem r35 = new ThemeItem();
        r35.themeInfo = Theme.getTheme("Dark Blue");
        r35.accentId = 0;
        r0.items.add(r35);
        return r0;
    }

    public static EmojiThemes createHomeQrTheme(int r2) {
        EmojiThemes r0 = new EmojiThemes(r2);
        r0.emoji = "🏠";
        r0.key = ThemeKey.ofEmoticon("🏠");
        r0.chatTheme = TLRPC.ChatTheme.ofEmoticon(r0.emoji);
        ThemeItem r22 = new ThemeItem();
        r22.themeInfo = Theme.getTheme("Blue");
        r22.accentId = 99;
        r0.items.add(r22);
        ThemeItem r23 = new ThemeItem();
        r23.themeInfo = Theme.getTheme("Dark Blue");
        r23.accentId = 0;
        r0.items.add(r23);
        return r0;
    }

    public static EmojiThemes createPreviewCustom(int r11) {
        EmojiThemes r0 = new EmojiThemes(r11);
        r0.emoji = "🎨";
        r0.key = ThemeKey.ofEmoticon("🎨");
        r0.chatTheme = TLRPC.ChatTheme.ofEmoticon(r0.emoji);
        int r2 = 0;
        SharedPreferences r112 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0);
        String r4 = r112.getString("lastDayCustomTheme", null);
        int r5 = r112.getInt("lastDayCustomThemeAccentId", -1);
        int r7 = 99;
        String r8 = "Blue";
        if (r4 != null) goto L5;
    L9:
        r4 = r112.getString("lastDayTheme", "Blue");
        Theme.ThemeInfo r52 = Theme.getTheme(r4);
        if (r52 != null) goto L12;
        r5 = 99;
        r4 = "Blue";
    L13:
        r112.edit().putString("lastDayCustomTheme", r4).apply();
    L14:
        if (r5 == (-1)) goto L17;
        r8 = r4;
        r7 = r5;
    L17:
        String r42 = r112.getString("lastDarkCustomTheme", null);
        int r53 = r112.getInt("lastDarkCustomThemeAccentId", -1);
        String r9 = "Dark Blue";
        if (r42 != null) goto L20;
    L24:
        r42 = r112.getString("lastDarkTheme", "Dark Blue");
        Theme.ThemeInfo r54 = Theme.getTheme(r42);
        if (r54 != null) goto L27;
        r53 = 0;
        r42 = "Dark Blue";
    L28:
        r112.edit().putString("lastDarkCustomTheme", r42).apply();
    L29:
        if (r53 == (-1)) goto L32;
        r9 = r42;
        r2 = r53;
    L32:
        ThemeItem r113 = new ThemeItem();
        r113.themeInfo = Theme.getTheme(r8);
        r113.accentId = r7;
        r0.items.add(r113);
        r0.items.add(null);
        ThemeItem r114 = new ThemeItem();
        r114.themeInfo = Theme.getTheme(r9);
        r114.accentId = r2;
        r0.items.add(r114);
        r0.items.add(null);
        return r0;
    L27:
        r53 = r54.currentAccentId;
        goto L28
    L20:
        if (Theme.getTheme(r42) == null) goto L24;
        if (r53 != (-1)) goto L29;
        r53 = Theme.getTheme(r8).lastAccentId;
        goto L29
    L12:
        r5 = r52.currentAccentId;
        goto L13
    L5:
        if (Theme.getTheme(r4) == null) goto L9;
        if (r5 != (-1)) goto L14;
        r5 = Theme.getTheme(r4).lastAccentId;
        goto L14
    }

    public static EmojiThemes createPreviewFullTheme(int r3, TLRPC.TL_theme r4) {
        EmojiThemes r0 = new EmojiThemes(r3);
        r0.emoji = r4.emoticon;
        r0.key = ThemeKey.of(r4);
        r0.chatTheme = TLRPC.ChatTheme.ofEmoticon(r4.emoticon);
        int r32 = 0;
    L4:
        if (r32 >= r4.settings.size()) goto L6;
        ThemeItem r1 = new ThemeItem();
        r1.tlTheme = r4;
        r1.settingsIndex = r32;
        r0.items.add(r1);
        r32 = r32 + 1;
        goto L4
    L6:
        return r0;
    }

    public static Theme.ThemeInfo getDefaultThemeInfo(boolean r4) {
        if (r4 == false) goto L4;
        Theme.ThemeInfo r0 = Theme.getCurrentNightTheme();
    L6:
        if (r4 == r0.isDark()) goto L17;
        SharedPreferences r02 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0);
        String r1 = "Blue";
        if (r4 == false) goto L10;
        String r03 = r02.getString("lastDarkTheme", "Dark Blue");
    L11:
        r0 = Theme.getTheme(r03);
        if (r0 != null) goto L17;
        if (r4 == false) goto L15;
        r1 = "Dark Blue";
    L15:
        r0 = Theme.getTheme(r1);
        goto L17
    L10:
        r03 = r02.getString("lastDayTheme", "Blue");
    L17:
        return new Theme.ThemeInfo(r0);
    L4:
        r0 = Theme.getCurrentTheme();
        goto L6
    }

    private int getOrDefault(SparseIntArray r1, int r2) {
        if (r1 == null) goto L4;
        int r0 = r1.indexOfKey(r2);     // Catch: Exception -> L9
        if (r0 < 0) goto L12;
        return r1.valueAt(r0);
    L12:
        return Theme.getDefaultColor(r2);
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L12
    L4:
        return Theme.getDefaultColor(r2);
    }

    private File getWallpaperThumbFile(long r4) {
        return new File(ApplicationLoader.getFilesDirFixed(), "wallpaper_thumb_" + r4 + ".png");
    }

    private /* synthetic */ void lambda$initColors$1(ColorsLoadedCallback r3) {
        getPreviewColors(this.currentAccount, 0);
        getPreviewColors(this.currentAccount, 1);
        AndroidUtilities.runOnUIThread(new EmojiThemes$$ExternalSyntheticLambda4(r3));
    }

    public static void loadWallpaperGiftPattern(int r7, long r8, TL_stars.StarGift r10, ResultCallback<Pair<Long, Bitmap>> r11) {
        TLRPC.Document r5 = TlUtils.getGiftDocumentPattern(r10);
        ImageLocation r1 = ImageLocation.getForDocument(r5);
        ImageReceiver r0 = new ImageReceiver();
        r0.setAllowLoadingOnAttachedOnly(false);
        r0.setImage(r1, "40_40_firstframe", null, ".jpg", r5, 1);
        r0.setDelegate(new EmojiThemes$$ExternalSyntheticLambda7(r11, r8));
        ImageLoader.getInstance().loadImageForImageReceiver(r0);
    }

    public static void loadWallpaperImage(int r8, long r9, TLRPC.WallPaper r11, Utilities.Callback<WallpaperBitmapHolder> r12) {
        boolean r3 = r11.pattern;
        ChatThemeController r7 = ChatThemeController.getInstance(r8);
        EmojiThemes$$ExternalSyntheticLambda1 r0 = new EmojiThemes$$ExternalSyntheticLambda1(r12, r11, r3 ? 1 : 0, r8, r9);
        r7.loadWallpaperBitmap(r9, r3 ? 1 : 0, r0);
    }

    public static void saveCustomTheme(Theme.ThemeInfo r5, int r6) {
        if (r5 == null) goto L40;
        if (r6 < 0) goto L13;
        SparseArray<Theme.ThemeAccent> r0 = r5.themeAccentsMap;
        if (r0 == null) goto L13;
        Theme.ThemeAccent r02 = r0.get(r6);
        if (r02 != null) goto L10;
        return;
    L10:
        if (r02.isDefault == false) goto L13;
        return;
    L13:
        if (r5.getKey().equals("Blue") == false) goto L18;
        if (r6 != 99) goto L18;
        return;
    L18:
        if (r5.getKey().equals("Day") == false) goto L23;
        if (r6 != 9) goto L23;
        return;
    L23:
        if (r5.getKey().equals("Night") == false) goto L27;
        if (r6 != 0) goto L27;
        return;
    L27:
        if (r5.getKey().equals("Dark Blue") == false) goto L30;
        if (r6 != 0) goto L30;
        return;
    L30:
        boolean r03 = r5.isDark();
        if (r03 == false) goto L33;
        String r1 = "lastDarkCustomTheme";
    L34:
        if (r03 == false) goto L36;
        String r04 = "lastDarkCustomThemeAccentId";
    L37:
        ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0).edit().putString(r1, r5.getKey()).putInt(r04, r6).apply();
        return;
    L36:
        r04 = "lastDayCustomThemeAccentId";
        goto L37
    L33:
        r1 = "lastDayCustomTheme";
        goto L34
    }

    public SparseIntArray createColors(int r11, int r12) {
        Theme.ThemeInfo r0 = getThemeInfo(r12);
        if (r0 != null) goto L5;
        int r02 = getSettingsIndex(r12);
        ITheme r2 = getITheme(r12);
        TLRPC.ThemeSettings r6 = r2.getThemeSettings(r02);
        TLRPC.TL_theme r7 = getTlTheme(r12);
        Theme.ThemeInfo r3 = new Theme.ThemeInfo(Theme.getTheme(Theme.getBaseThemeKey(r6)));
        Theme.ThemeAccent r112 = r3.createNewAccent(r2.getThemeId(), r6, r7, r11, true);
        r3.setCurrentAccentId(r112.id);
        r0 = r3;
    L9:
        String[] r22 = new String[1];
        if (r0.pathToFile == null) goto L12;
        SparseIntArray r03 = Theme.getThemeFileValues(new File(r0.pathToFile), null, r22);
    L16:
        int r1 = 0;
        ThemeItem.m5752$$Nest$fputwallpaperLink(this.items.get(r12), r22[0]);
        if (r112 == null) goto L26;
        SparseIntArray r122 = r03.clone();
        r112.fillAccentColors(r03, r122);
        if (isGiftTheme() == false) goto L25;
        Theme.ThemeInfo r10 = r112.parentTheme;
        if (r10 == null) goto L25;
        if (r10.isLight() == false) goto L25;
        r112.resetAccentColorsForMyMessagesGiftThemeLight(r122);
    L25:
        r03 = r122;
    L26:
        SparseIntArray r102 = Theme.getFallbackKeys();
        int r113 = 0;
    L28:
        if (r113 >= r102.size()) goto L35;
        int r123 = r102.keyAt(r113);
        int r23 = r102.valueAt(r113);
        if (r03.indexOfKey(r123) >= 0) goto L34;
        int r24 = r03.indexOfKey(r23);
        if (r24 < 0) goto L34;
        r03.put(r123, r03.valueAt(r24));
    L34:
        r113 = r113 + 1;
        goto L28
    L35:
        int[] r103 = Theme.getDefaultColors();
    L37:
        if (r1 >= r103.length) goto L42;
        if (r03.indexOfKey(r1) >= 0) goto L41;
        r03.put(r1, r103[r1]);
    L41:
        r1 = r1 + 1;
        goto L37
    L42:
        return r03;
    L12:
        String r04 = r0.assetName;
        if (r04 == null) goto L15;
        r03 = Theme.getThemeFileValues(null, r04, r22);
        goto L16
    L15:
        r03 = new SparseIntArray();
        goto L16
    L5:
        SparseArray<Theme.ThemeAccent> r114 = r0.themeAccentsMap;
        if (r114 == null) goto L8;
        r112 = r114.get(this.items.get(r12).accentId);
        goto L9
    L8:
        r112 = null;
        goto L9
    }

    public int getAccentId(int r1) {
        return this.items.get(r1).accentId;
    }

    public long getBusyByUserId() {
        if ((this.chatTheme instanceof TLRPC.TL_chatThemeUniqueGift) == true) goto L5;
        return 0;
    L5:
        return ChatThemeController.getInstance(this.currentAccount).getGiftThemeUser(((TLRPC.TL_chatThemeUniqueGift) this.chatTheme).gift.slug);
    }

    public TLRPC.ChatTheme getChatTheme() {
        return this.chatTheme;
    }

    public TLRPC.Document getEmojiAnimatedSticker() {
        TLRPC.ChatTheme r0 = this.chatTheme;
        if ((r0 instanceof TLRPC.TL_chatThemeUniqueGift) == false) goto L7;
        return TlUtils.getGiftDocument(((TLRPC.TL_chatThemeUniqueGift) r0).gift);
    L7:
        if ((r0 instanceof TLRPC.TL_chatTheme) == true) goto L9;
        return null;
    L9:
        return MediaDataController.getInstance(this.currentAccount).getEmojiAnimatedSticker(((TLRPC.TL_chatTheme) this.chatTheme).emoticon);
    }

    @Deprecated
    public String getEmoticon() {
        return this.emoji;
    }

    public String getEmoticonOrSlug() {
        ThemeKey r1 = this.key;
        if (r1 != null) goto L6;
        return null;
    L6:
        String r0 = r1.giftSlug;
        if (r0 == null) goto L10;
        return r0;
    L10:
        return r1.emoticon;
    }

    public ITheme getITheme(int r1) {
        return this.items.get(r1);
    }

    public SparseIntArray getPreviewColors(int r13, int r14) {
        SparseIntArray r0 = this.items.get(r14).currentPreviewColors;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Theme.ThemeInfo r1 = getThemeInfo(r14);
        if (r1 != null) goto L19;
        int r3 = getSettingsIndex(r14);
        ITheme r4 = getITheme(r14);
        TLRPC.TL_theme r9 = getTlTheme(r14);
        if (r4 == null) goto L10;
        Theme.ThemeInfo r5 = Theme.getTheme(Theme.getBaseThemeKey(r4.getThemeSettings(r3)));
    L11:
        if (r5 == null) goto L22;
        Theme.ThemeInfo r52 = new Theme.ThemeInfo(r5);
        if (r4 == null) goto L15;
        Theme.ThemeAccent r132 = r52.createNewAccent(r4.getThemeId(), r4.getThemeSettings(r3), r9, r13, true);
    L16:
        if (r132 == null) goto L18;
        r52.setCurrentAccentId(r132.id);
    L18:
        r1 = r52;
    L23:
        if (r1 != null) goto L25;
        return r0;
    L25:
        String[] r02 = new String[1];
        if (r1.pathToFile == null) goto L28;
        SparseIntArray r12 = Theme.getThemeFileValues(new File(r1.pathToFile), null, r02);
    L32:
        int r32 = 0;
        ThemeItem.m5752$$Nest$fputwallpaperLink(this.items.get(r14), r02[0]);
        if (r132 == null) goto L42;
        SparseIntArray r03 = r12.clone();
        r132.fillAccentColors(r12, r03);
        if (isGiftTheme() == false) goto L41;
        Theme.ThemeInfo r15 = r132.parentTheme;
        if (r15 == null) goto L41;
        if (r15.isLight() == false) goto L41;
        r132.resetAccentColorsForMyMessagesGiftThemeLight(r03);
    L41:
        r12 = r03;
    L42:
        SparseIntArray r133 = Theme.getFallbackKeys();
        SparseIntArray r2 = new SparseIntArray();
        this.items.get(r14).currentPreviewColors = r2;
    L59:
        int[] r122 = previewColorKeys;     // Catch: Exception -> L48
        if (r32 >= r122.length) goto L56;
        int r123 = r122[r32];     // Catch: Exception -> L48
        int r142 = r12.indexOfKey(r123);     // Catch: Exception -> L48
        if (r142 < 0) goto L50;
        r2.put(r123, r12.valueAt(r142));     // Catch: Exception -> L48
    L55:
        r32 = r32 + 1;
        goto L59
    L50:
        int r143 = r133.get(r123, -1);     // Catch: Exception -> L48
        if (r143 < 0) goto L55;
        int r144 = r12.indexOfKey(r143);     // Catch: Exception -> L48
        if (r144 < 0) goto L55;
        r2.put(r123, r12.valueAt(r144));     // Catch: Exception -> L48
        goto L55
    L56:
        return r2;
    L48:
        e = move-exception;
        FileLog.e(e);
        return r2;
    L28:
        String r16 = r1.assetName;
        if (r16 == null) goto L31;
        r12 = Theme.getThemeFileValues(null, r16, r02);
        goto L32
    L31:
        r12 = new SparseIntArray();
        goto L32
    L15:
        r132 = null;
    L22:
        r132 = null;
        goto L23
    L10:
        r5 = Theme.getTheme("Blue");
        goto L11
    L19:
        SparseArray<Theme.ThemeAccent> r134 = r1.themeAccentsMap;
        if (r134 == null) goto L22;
        r132 = r134.get(this.items.get(r14).accentId);
        goto L23
    }

    public int getSettingsIndex(int r1) {
        return this.items.get(r1).settingsIndex;
    }

    public TL_stars.TL_starGiftUnique getThemeGift() {
        TLRPC.ChatTheme r1 = this.chatTheme;
        if ((r1 instanceof TLRPC.TL_chatThemeUniqueGift) == false) goto L8;
        TL_stars.StarGift r12 = ((TLRPC.TL_chatThemeUniqueGift) r1).gift;
        if ((r12 instanceof TL_stars.TL_starGiftUnique) == true) goto L7;
        return null;
    L7:
        return (TL_stars.TL_starGiftUnique) r12;
    L8:
        return null;
    }

    public long getThemeId(int r1) {
        return this.items.get(r1).getThemeId();
    }

    public Theme.ThemeInfo getThemeInfo(int r1) {
        return this.items.get(r1).themeInfo;
    }

    public ThemeItem getThemeItem(int r1) {
        return this.items.get(r1);
    }

    public ThemeKey getThemeKey() {
        return this.key;
    }

    public TLRPC.TL_theme getTlTheme(int r1) {
        return this.items.get(r1).tlTheme;
    }

    public TLRPC.WallPaper getWallpaper(int r1) {
        ThemeItem r0 = this.items.get(r1);
        return r0.getThemeWallPaper(r0.settingsIndex);
    }

    public String getWallpaperLink(int r1) {
        return ThemeItem.m5751$$Nest$fgetwallpaperLink(this.items.get(r1));
    }

    public void initColors(ColorsLoadedCallback r3) {
        if (this.items.get(0).currentPreviewColors != null) goto L5;
    L9:
        Utilities.themeQueue.postRunnable(new EmojiThemes$$ExternalSyntheticLambda2(this, r3));
        return;
    L5:
        if (this.items.get(1).currentPreviewColors == null) goto L9;
        if (r3 == null) goto L11;
        r3.onColorsLoaded();
        return;
    }

    public boolean isAnyStub() {
        if (this.showAsDefaultStub == false) goto L5;
        return true;
    L5:
        if (this.showAsRemovedStub == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isGiftTheme() {
        ThemeKey r0 = this.key;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (TextUtils.isEmpty(r0.giftSlug) == true) goto L10;
        return true;
    L10:
        return false;
    }

    public void loadPreviewColors(int r6) {
        int r1 = 0;
    L4:
        if (r1 >= this.items.size()) goto L20;
        if (this.items.get(r1) == null) goto L19;
        SparseIntArray r2 = getPreviewColors(r6, r1);
        this.items.get(r1).inBubbleColor = getOrDefault(r2, Theme.key_chat_inBubble);
        this.items.get(r1).outBubbleColor = getOrDefault(r2, Theme.key_chat_outBubble);
        this.items.get(r1).outLineColor = getOrDefault(r2, Theme.key_featuredStickers_addButton);
        this.items.get(r1).patternBgColor = r2.get(Theme.key_chat_wallpaper, 0);
        this.items.get(r1).patternBgGradientColor1 = r2.get(Theme.key_chat_wallpaper_gradient_to1, 0);
        this.items.get(r1).patternBgGradientColor2 = r2.get(Theme.key_chat_wallpaper_gradient_to2, 0);
        this.items.get(r1).patternBgGradientColor3 = r2.get(Theme.key_chat_wallpaper_gradient_to3, 0);
        this.items.get(r1).patternBgRotation = r2.get(Theme.key_chat_wallpaper_gradient_rotation, 0);
        if (this.items.get(r1).themeInfo == null) goto L19;
        if (this.items.get(r1).themeInfo.getKey().equals("Blue") == false) goto L19;
        int r22 = this.items.get(r1).accentId;
        ArrayList<ThemeItem> r3 = this.items;
        if (r22 < 0) goto L15;
        int r23 = r3.get(r1).accentId;
    L17:
        if (r23 != 99) goto L19;
        this.items.get(r1).patternBgColor = -2368069;
        this.items.get(r1).patternBgGradientColor1 = -9722489;
        this.items.get(r1).patternBgGradientColor2 = -2762611;
        this.items.get(r1).patternBgGradientColor3 = -7817084;
        goto L19
    L15:
        r23 = r3.get(r1).themeInfo.currentAccentId;
    L19:
        r1 = r1 + 1;
        goto L4
    }

    public void loadWallpaper(int r6, ResultCallback<Pair<Long, WallpaperBitmapHolder>> r7) {
        TLRPC.WallPaper r0 = getWallpaper(r6);
        if (r0 != null) goto L7;
        if (r7 == null) goto L9;
        r7.onComplete(null);
        return;
    L9:
        return;
    L7:
        long r1 = getThemeId(r6);
        loadWallpaperImage(this.currentAccount, r0.id, r0, new EmojiThemes$$ExternalSyntheticLambda0(r7, r1));
    }

    public void loadWallpaperGiftPattern(int r4, ResultCallback<Pair<Long, Bitmap>> r5) {
        ThemeItem r0 = getThemeItem(r4);
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.tlChatThemeGift == null) goto L9;
        long r1 = getThemeId(r4);
        loadWallpaperGiftPattern(this.currentAccount, r1, r0.tlChatThemeGift.gift, r5);
        return;
    }

    public void loadWallpaperThumb(int r13, ResultCallback<Pair<Long, Bitmap>> r14) {
        TLRPC.WallPaper r1 = getWallpaper(r13);
        if (r1 != null) goto L7;
        if (r14 == null) goto L34;
        r14.onComplete(null);
        return;
    L34:
        return;
    L7:
        long r3 = getThemeId(r13);
        if (r3 != 0) goto L12;
        if (r14 == null) goto L35;
        r14.onComplete(null);
        return;
    L35:
        return;
    L12:
        Bitmap r132 = ChatThemeController.getInstance(this.currentAccount).getWallpaperThumbBitmap(r3);
        File r12 = getWallpaperThumbFile(r3);
        if (r132 == null) goto L15;
    L22:
        if (r132 == null) goto L25;
        if (r14 == null) goto L36;
        r14.onComplete(new Pair(Long.valueOf(r3), r132));
        return;
    L36:
        return;
    L25:
        TLRPC.Document r133 = r1.document;
        if (r133 != null) goto L30;
        if (r14 == null) goto L38;
        r14.onComplete(new Pair(Long.valueOf(r3), null));
        return;
    L38:
        return;
    L30:
        ImageLocation r6 = ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r133.thumbs, 140), r1.document);
        ImageReceiver r5 = new ImageReceiver();
        r5.setAllowLoadingOnAttachedOnly(false);
        r5.setImage(r6, "120_140", null, null, null, 1);
        r5.setDelegate(new EmojiThemes$$ExternalSyntheticLambda3(r14, r3, r12));
        ImageLoader.getInstance().loadImageForImageReceiver(r5);
        return;
    L15:
        if (r12.exists() == false) goto L22;
        if (r12.length() <= 0) goto L22;
        r132 = BitmapFactory.decodeFile(r12.getAbsolutePath());     // Catch: Exception -> L20
    L20:
        e = move-exception;
        FileLog.e(e);
        goto L22
    }

    public void preloadWallpaper() {
        loadWallpaperThumb(0, null);
        loadWallpaperThumb(1, null);
        loadWallpaper(0, null);
        loadWallpaper(1, null);
    }
}
