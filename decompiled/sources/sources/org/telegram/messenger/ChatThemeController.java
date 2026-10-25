package org.telegram.messenger;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.Pair;
import com.exteragram.messenger.ExteraConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.NotificationBadge;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.wallpaper.WallpaperBitmapHolder;
import org.telegram.messenger.wallpaper.WallpaperGiftPatternPosition;
import org.telegram.messenger.wallpaper.pgm.PGMImage;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.ResultCallback;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.EmojiThemes;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.theme.ThemeKey;
import org.telegram.ui.ChatBackgroundDrawable;

/* loaded from: classes.dex */
public class ChatThemeController extends BaseController {
    public static final int THEME_LIST_WITH_DEFAULT = 1;
    public static final int THEME_LIST_WITH_EMOJI = 2;
    public static final int THEME_LIST_WITH_GIFTS = 4;
    public static volatile DispatchQueue chatThemeQueue;
    private static final ChatThemeController[] instances = null;
    private final Map<String, EmojiThemes> allChatGiftThemes;
    private List<EmojiThemes> allChatThemes;
    private final LongSparseArray<ThemeKey> dialogEmoticonsMap;
    private final ThemeList giftsThemeList;
    private volatile long lastReloadTimeMs;
    private final long reloadTimeoutMs;
    private final HashMap<Long, Bitmap> themeIdWallpaperThumbMap;
    private volatile long themesHash;
    private final Map<String, Long> usedGiftThemesBySlug;
    private final Map<Long, String> usedGiftThemesByUsers;

    /* renamed from: org.telegram.messenger.ChatThemeController$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 implements ResultCallback<List<EmojiThemes>> {
        final /* synthetic */ ChatThemeController this$0;
        final /* synthetic */ ResultCallback val$callback;
        final /* synthetic */ ThemeKey val$key;

        /* renamed from: $r8$lambda$W-UOXDGj6ur6Ksiy5DXjFwChPbw, reason: not valid java name */
        public static /* synthetic */ void m3930$r8$lambda$WUOXDGj6ur6Ksiy5DXjFwChPbw(ResultCallback r0, EmojiThemes r1) {
            r0.onComplete(r1);
        }

        public AnonymousClass1(ChatThemeController r1, ThemeKey r2, ResultCallback r3) {
            this.this$0 = r1;
            this.val$key = r2;
            this.val$callback = r3;
        }

        @Override // org.telegram.tgnet.ResultCallback
        public /* bridge */ /* synthetic */ void onComplete(List<EmojiThemes> r1) {
            onComplete2(r1);
        }

        /* renamed from: onComplete, reason: avoid collision after fix types in other method */
        public void onComplete2(List<EmojiThemes> r4) {
            Iterator<EmojiThemes> r42 = r4.iterator();
        L4:
            if (r42.hasNext() == false) goto L9;
            EmojiThemes r0 = r42.next();
            if (this.val$key.equals(r0.getThemeKey()) == false) goto L4;
            r0.initColors(new ChatThemeController$1$$ExternalSyntheticLambda0(this.val$callback, r0));
            return;
        L9:
            this.val$callback.onComplete(null);
        }

        @Override // org.telegram.tgnet.ResultCallback
        public void onError(TLRPC.TL_error r1) {
            this.val$callback.onComplete(null);
        }
    }

    public static class ThemeList {
        private boolean completed;
        private long hash;
        private long lastReloadTimeMs;
        private String offset;
        private List<EmojiThemes> themes;

        /* renamed from: -$$Nest$fgetcompleted, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m3931$$Nest$fgetcompleted(ThemeList r0) {
            return r0.completed;
        }

        /* renamed from: -$$Nest$fgethash, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m3932$$Nest$fgethash(ThemeList r2) {
            return r2.hash;
        }

        /* renamed from: -$$Nest$fgetlastReloadTimeMs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m3933$$Nest$fgetlastReloadTimeMs(ThemeList r2) {
            return r2.lastReloadTimeMs;
        }

        /* renamed from: -$$Nest$fgetoffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m3934$$Nest$fgetoffset(ThemeList r0) {
            return r0.offset;
        }

        /* renamed from: -$$Nest$fgetthemes, reason: not valid java name */
        public static /* bridge */ /* synthetic */ List m3935$$Nest$fgetthemes(ThemeList r0) {
            return r0.themes;
        }

        /* renamed from: -$$Nest$fputcompleted, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3936$$Nest$fputcompleted(ThemeList r0, boolean r1) {
            r0.completed = r1;
        }

        /* renamed from: -$$Nest$fputhash, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3937$$Nest$fputhash(ThemeList r0, long r1) {
            r0.hash = r1;
        }

        /* renamed from: -$$Nest$fputlastReloadTimeMs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3938$$Nest$fputlastReloadTimeMs(ThemeList r0, long r1) {
            r0.lastReloadTimeMs = r1;
        }

        /* renamed from: -$$Nest$fputoffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3939$$Nest$fputoffset(ThemeList r0, String r1) {
            r0.offset = r1;
        }

        /* renamed from: -$$Nest$fputthemes, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3940$$Nest$fputthemes(ThemeList r0, List r1) {
            r0.themes = r1;
        }

        private ThemeList() {
        }

        public /* synthetic */ ThemeList(ChatThemeControllerIA r1) {
            this();
        }
    }

    public static /* synthetic */ void $r8$lambda$8Na7zulZalXIe__rhLaQ2WiSe8w(Utilities.Callback r2, Bitmap r3) {
        if (r3 == null) goto L5;
        r2.run(new WallpaperBitmapHolder(r3, 0));
        return;
    L5:
        r2.run(null);
    }

    public static /* synthetic */ void $r8$lambda$9ljL1XJFcrdtWan4GgdCkHeA1hI(ResultCallback r0, EmojiThemes r1) {
        r0.onComplete(r1);
    }

    public static /* synthetic */ void $r8$lambda$BbziMLBR9ndND54MZeoCa83AWNE(ChatThemeController r0, TLRPC.TL_chatThemeUniqueGift r1, EmojiThemes r2) {
        r0.lambda$putThemeIfNeeded$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$CZY0ep9kvRBHPwJUOO1LpHzFWPE(ResultCallback r0, TLRPC.TL_error r1) {
        r0.onError(r1);
    }

    /* renamed from: $r8$lambda$G3j5Hi-oHYW8g2375iNZBBkeAd8, reason: not valid java name */
    public static /* synthetic */ void m3923$r8$lambda$G3j5HioHYW8g2375iNZBBkeAd8(File r13, Utilities.Callback r14) {
        WallpaperBitmapHolder r1 = null;
        FileInputStream r2 = new FileInputStream(r13);     // Catch: Exception -> L44
        GZIPInputStream r132 = new GZIPInputStream(r2);     // Catch: Throwable -> L37
        ArrayList r3 = new ArrayList(1);     // Catch: Throwable -> L30
        Bitmap r4 = PGMImage.read(r132, r3);     // Catch: Throwable -> L30
        int r5 = r3.size();     // Catch: Throwable -> L28
        ArrayList r7 = null;
        int r8 = 0;
    L8:
        if (r8 >= r5) goto L21;
        Object r9 = r3.get(r8);     // Catch: Throwable -> L19
        r8 = r8 + 1;     // Catch: Throwable -> L19
        String r92 = (String) r9;     // Catch: Throwable -> L19
        if (r92.startsWith("patterns = ") == false) goto L8;
        byte[] r93 = Utilities.hexToBytes(r92.substring(11));     // Catch: Throwable -> L19
        int r10 = r93.length / 52;     // Catch: Throwable -> L19
        SerializedData r11 = new SerializedData(r93);     // Catch: Throwable -> L19
        ArrayList r94 = new ArrayList(r10);     // Catch: Throwable -> L19
        int r72 = 0;
    L13:
        if (r72 >= r10) goto L17;
        r94.add(WallpaperGiftPatternPosition.deserialize(r11));     // Catch: Throwable -> L15
        r72 = r72 + 1;     // Catch: Throwable -> L15
    L15:
        th = th;
        r7 = r94;
    L51:
        r132.close();     // Catch: Throwable -> L34
    L36:
        throw th;     // Catch: Throwable -> L26
    L34:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L26
        goto L36
    L17:
        r11.cleanup();     // Catch: Throwable -> L15
        r7 = r94;
    L19:
        th = th;
        goto L51
    L21:
        r132.close();     // Catch: Throwable -> L26
        r2.close();     // Catch: Exception -> L24
    L47:
        if (r4 == null) goto L49;
        r1 = new WallpaperBitmapHolder(r4, 1, r7);
    L49:
        AndroidUtilities.runOnUIThread(new ChatThemeController$$ExternalSyntheticLambda17(r14, r1));
        return;
    L28:
        th = th;
        r7 = null;
    L30:
        th = th;
        r4 = null;
        r7 = null;
    L26:
        th = th;
    L65:
        r2.close();     // Catch: Throwable -> L41
    L43:
        throw th;     // Catch: Exception -> L24
    L41:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L24
    L37:
        th = th;
        r4 = null;
        r7 = null;
    L24:
        e = e;
    L46:
        FileLog.e(e);
    L44:
        e = e;
        r4 = null;
        r7 = null;
        goto L46
    }

    public static /* synthetic */ void $r8$lambda$JEArpfdnL8b8n60s46CXjA5lc2M(ResultCallback r0, Bitmap r1) {
        r0.onComplete(r1);
    }

    public static /* synthetic */ void $r8$lambda$LlcK1XWgjpGqT7gIImemEXTrZwI(ChatThemeController r0, Pair r1) {
        r0.lambda$preloadAllWallpaperThumbs$7(r1);
    }

    /* renamed from: $r8$lambda$QUzV-3-vCFENvtAWpiFT0VyTgZg, reason: not valid java name */
    public static /* synthetic */ void m3924$r8$lambda$QUzV3vCFENvtAWpiFT0VyTgZg(ChatThemeController r0, TLRPC.ChatFull r1) {
        r0.lambda$processUpdate$16(r1);
    }

    /* renamed from: $r8$lambda$T6jSDG816nBfdXxCbRTlr-TjMNg, reason: not valid java name */
    public static /* synthetic */ void m3925$r8$lambda$T6jSDG816nBfdXxCbRTlrTjMNg(ChatThemeController r0, TL_account.Tl_chatThemes r1, List r2, List r3, ResultCallback r4) {
        r0.lambda$requestNextChatThemes$21(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$T_4fBksxetLsV5HAWGFiG_2_ySM(ChatThemeController r0, TLRPC.Updates r1, TLRPC.TL_error r2) {
        r0.lambda$setDialogTheme$6(r1, r2);
    }

    /* renamed from: $r8$lambda$Ta7_jJnF83wl-CX-wFca2i6wvnY, reason: not valid java name */
    public static /* synthetic */ void m3926$r8$lambda$Ta7_jJnF83wlCXwFca2i6wvnY(ChatThemeController r0, ResultCallback r1, boolean r2, TL_account.Themes r3, TLRPC.TL_error r4) {
        r0.lambda$requestAllChatThemes$4(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$UuPeIPP92izRevFT1ZR3WLEHvvc(ChatThemeController r0, long r1, TLRPC.UserFull r3) {
        r0.lambda$processUpdate$15(r1, r3);
    }

    /* renamed from: $r8$lambda$dr00Ssey-JI-8nxcIUsEgc8hy4M, reason: not valid java name */
    public static /* synthetic */ void m3927$r8$lambda$dr00SseyJI8nxcIUsEgc8hy4M(File r3, List r4, Bitmap r5) {
        FileOutputStream r0 = new FileOutputStream(r3);     // Catch: Exception -> L34
        GZIPOutputStream r32 = new GZIPOutputStream(r0);     // Catch: Throwable -> L22
        if (r4 != null) goto L37;
    L14:
        List r42 = null;
    L16:
        if (r5.getConfig() != Bitmap.Config.ALPHA_8) goto L18;
        PGMImage.write(r5, r32, r42);     // Catch: Throwable -> L11
    L19:
        r32.close();     // Catch: Throwable -> L22
        r0.close();     // Catch: Exception -> L34
        return;
    L18:
        Bitmap r52 = r5.extractAlpha();     // Catch: Throwable -> L11
        PGMImage.write(r52, r32, r42);     // Catch: Throwable -> L11
        r52.recycle();     // Catch: Throwable -> L11
        goto L19
    L37:
    L11:
        th = move-exception;
        r32.close();     // Catch: Throwable -> L26
    L28:
        throw th;     // Catch: Throwable -> L22
    L26:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L22
        goto L28
    L6:
        if (r4.isEmpty() == true) goto L14;
        SerializedData r1 = new SerializedData(r4.size() * 52);     // Catch: Throwable -> L11
        Iterator r43 = r4.iterator();     // Catch: Throwable -> L11
    L9:
        if (r43.hasNext() == false) goto L13;
        ((WallpaperGiftPatternPosition) r43.next()).serialize(r1);     // Catch: Throwable -> L11
        goto L9
    L13:
        r42 = Collections.singletonList("patterns = " + Utilities.bytesToHex(r1.toByteArray()));     // Catch: Throwable -> L11
        r1.cleanup();     // Catch: Throwable -> L11
    L22:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L31
    L33:
        throw th;     // Catch: Exception -> L34
    L31:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L34
    L34:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$gWwjGqvcOQqb4D4FPxSNVrDiH74(ChatThemeController r0, List r1) {
        r0.lambda$init$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$nJ5_4MFWZlRingpABu3V9ucLTQA(ChatThemeController r0, ResultCallback r1, TL_account.ChatThemes r2, TLRPC.TL_error r3) {
        r0.lambda$requestNextChatThemes$23(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$nkFFfKgHFgAw65TNhpApu9M4wPk(ChatThemeController r0, List r1, ResultCallback r2, boolean r3) {
        r0.lambda$requestAllChatThemes$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$nl8E30xElOiHenWIlvDaS3YYBKo(File r2, ResultCallback r3) {
        Bitmap r0 = null;
    L7:
        e = move-exception;
        FileLog.e(e);
    L9:
        if (r3 == null) goto L14;
        AndroidUtilities.runOnUIThread(new ChatThemeController$$ExternalSyntheticLambda20(r3, r0));
        return;
    L14:
        return;
    L4:
        if (r2.exists() == false) goto L9;
        r0 = BitmapFactory.decodeFile(r2.getAbsolutePath());     // Catch: Exception -> L7
        goto L9
    }

    /* renamed from: $r8$lambda$o1-MsfmvVGAloVaHDkaRXcpSlkE, reason: not valid java name */
    public static /* synthetic */ void m3928$r8$lambda$o1MsfmvVGAloVaHDkaRXcpSlkE(ChatThemeController r0, TLObject r1, long r2, boolean r4, String r5, Runnable r6) {
        r0.lambda$setWallpaperToPeer$18(r1, r2, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$tFUyRd9CWqhIdY2lGiWtO4Us05c(ChatThemeController r0, ResultCallback r1) {
        r0.lambda$requestNextChatThemes$22(r1);
    }

    public static /* synthetic */ void $r8$lambda$tkK58zKdK6xmgJ5ztu1TsHvw3hc(ChatThemeController r0, long r1, boolean r3, String r4, Runnable r5, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$setWallpaperToPeer$19(r1, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$wktjABmk4cyHF_ZuprGRybyD9hg(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$xA5_CC1ftJ0s4EhnsPndKcJt69M(Utilities.Callback r0, WallpaperBitmapHolder r1) {
        r0.run(r1);
    }

    /* renamed from: $r8$lambda$y-kBmjJGB4-msxwXltDwEbhBgUI, reason: not valid java name */
    public static /* synthetic */ void m3929$r8$lambda$ykBmjJGB4msxwXltDwEbhBgUI(ResultCallback r0, TLRPC.TL_error r1) {
        r0.onError(r1);
    }

    public static /* synthetic */ void $r8$lambda$y6bifoDt_9R1svMWTVvWbYqFHI8(File r2, Bitmap r3) {
        FileOutputStream r0 = new FileOutputStream(r2);     // Catch: Exception -> L4
        r3.compress(Bitmap.CompressFormat.PNG, 87, r0);     // Catch: Exception -> L4
        r0.close();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    static {
        chatThemeQueue = new DispatchQueue("chatThemeQueue");
        instances = new ChatThemeController[16];
    }

    private ChatThemeController(int r3) {
        super(r3);
        this.reloadTimeoutMs = 7200000;
        this.themeIdWallpaperThumbMap = new HashMap();
        this.allChatGiftThemes = new HashMap();
        this.giftsThemeList = new ThemeList(null);
        this.dialogEmoticonsMap = new LongSparseArray();
        this.usedGiftThemesByUsers = new HashMap();
        this.usedGiftThemesBySlug = new HashMap();
        init();
    }

    public static boolean equals(TLRPC.WallPaper r6, TLRPC.WallPaper r7) {
        if (r6 != null) goto L7;
        if (r7 != null) goto L7;
        return true;
    L7:
        if (r6 == null) goto L20;
        if (r7 == null) goto L20;
        String r2 = r6.uploadingImage;
        if (r2 == null) goto L14;
        return TextUtils.equals(r7.uploadingImage, r2);
    L14:
        if (r6.id != r7.id) goto L20;
        if (TextUtils.equals(ChatBackgroundDrawable.hash(r6.settings), ChatBackgroundDrawable.hash(r7.settings)) == false) goto L20;
        if (TextUtils.equals(getWallpaperEmoticon(r6), getWallpaperEmoticon(r7)) == false) goto L20;
        return true;
    L20:
        return false;
    }

    private List<EmojiThemes> getAllChatThemesFromPrefs() {
        SharedPreferences r0 = getSharedPreferences();
        int r1 = r0.getInt(NotificationBadge.NewHtcHomeBadger.COUNT, 0);
        ArrayList r3 = new ArrayList(r1);
        int r4 = 0;
    L3:
        if (r4 >= r1) goto L12;
        SerializedData r6 = new SerializedData(Utilities.hexToBytes(r0.getString("theme_" + r4, _UrlKt.FRAGMENT_ENCODE_SET)));
        TLRPC.TL_theme r5 = TLRPC.Theme.TLdeserialize(r6, r6.readInt32(true), true);     // Catch: Throwable -> L9
        if (r5 == null) goto L11;
        r3.add(new EmojiThemes(this.currentAccount, r5, false));     // Catch: Throwable -> L9
    L11:
        r4 = r4 + 1;
    L9:
        th = move-exception;
        FileLog.e(th);
        goto L11
    L12:
        return r3;
    }

    private SharedPreferences getEmojiSharedPreferences() {
        return ApplicationLoader.applicationContext.getSharedPreferences("chatthemeconfig_emoji", 0);
    }

    public static ChatThemeController getInstance(int r3) {
        ChatThemeController[] r0 = instances;
        ChatThemeController r1 = r0[r3];
        if (r1 == null) goto L5;
        return r1;
    L5:
        monitor-enter(ChatThemeController.class);
        ChatThemeController r12 = r0[r3];     // Catch: Throwable -> L9
        if (r12 != null) goto L11;
        r12 = new ChatThemeController(r3);     // Catch: Throwable -> L9
        r0[r3] = r12;     // Catch: Throwable -> L9
    L11:
        monitor-exit(ChatThemeController.class);     // Catch: Throwable -> L9
        return r12;
    L9:
        th = move-exception;
        throw th;
    }

    private File getPatternFile(long r6) {
        return new File(ApplicationLoader.getFilesDirFixed(), String.format(Locale.US, "%d_%d.jpg", new Object[]{Long.valueOf(r6), Long.valueOf(this.themesHash)}));
    }

    private SharedPreferences getSharedPreferences() {
        return ApplicationLoader.applicationContext.getSharedPreferences("chatthemeconfig_" + this.currentAccount, 0);
    }

    private void getWallpaperBitmap(long r5, ResultCallback<Bitmap> r7) {
        if (this.themesHash != 0) goto L6;
        r7.onComplete(null);
        return;
    L6:
        File r4 = getPatternFile(r5);
        chatThemeQueue.postRunnable(new ChatThemeController$$ExternalSyntheticLambda8(r4, r7));
    }

    public static String getWallpaperEmoticon(TLRPC.WallPaper r1) {
        if (r1 == null) goto L11;
        TLRPC.WallPaperSettings r0 = r1.settings;
        if (r0 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        if (TextUtils.isEmpty(r0.emoticon) == false) goto L8;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        return r1.settings.emoticon;
    L11:
        return null;
    }

    private void init() {
        SharedPreferences r0 = getSharedPreferences();
        this.themesHash = 0;
        this.lastReloadTimeMs = 0;
        this.themesHash = r0.getLong("hash", 0);     // Catch: Exception -> L5
        this.lastReloadTimeMs = r0.getLong("lastReload", 0);     // Catch: Exception -> L5
    L7:
        this.allChatThemes = getAllChatThemesFromPrefs();
        getMessagesStorage().loadGiftChatTheme(new ChatThemeController$$ExternalSyntheticLambda10(this));
        preloadSticker("❌");
        if (this.allChatThemes.isEmpty() == true) goto L13;
        Iterator<EmojiThemes> r02 = this.allChatThemes.iterator();
    L11:
        if (r02.hasNext() == false) goto L17;
        preloadSticker(r02.next().getEmoticon());
        goto L11
    L17:
        return;
    L13:
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    public static boolean isNotEmoticonWallpaper(TLRPC.WallPaper r0) {
        String r02 = getWallpaperEmoticon(r0);
        if (r02 != null) goto L5;
        return false;
    L5:
        if (r02.length() != 0) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$init$0(List r4) {
        if (r4 == null) goto L7;
        Iterator r42 = r4.iterator();
    L5:
        if (r42.hasNext() == false) goto L9;
        TLRPC.TL_chatThemeUniqueGift r0 = (TLRPC.TL_chatThemeUniqueGift) r42.next();
        EmojiThemes r1 = new EmojiThemes(this.currentAccount, r0);
        this.allChatGiftThemes.put(r0.gift.slug, r1);
        goto L5
    L9:
        return;
    }

    private /* synthetic */ void lambda$preloadAllWallpaperThumbs$7(Pair r2) {
        if (r2 == null) goto L5;
        this.themeIdWallpaperThumbMap.put((Long) r2.first, (Bitmap) r2.second);
        return;
    }

    private /* synthetic */ void lambda$processUpdate$15(long r2, TLRPC.UserFull r4) {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.userInfoDidLoad, new Object[]{Long.valueOf(r2), r4});
    }

    private /* synthetic */ void lambda$processUpdate$16(TLRPC.ChatFull r4) {
        NotificationCenter r3 = NotificationCenter.getInstance(this.currentAccount);
        int r0 = NotificationCenter.chatInfoDidLoad;
        Boolean r2 = Boolean.FALSE;
        r3.postNotificationName(r0, new Object[]{r4, 0, r2, r2});
    }

    private /* synthetic */ void lambda$putThemeIfNeeded$1(TLRPC.TL_chatThemeUniqueGift r1, EmojiThemes r2) {
        this.allChatGiftThemes.put(r1.gift.slug, r2);
    }

    private /* synthetic */ void lambda$requestAllChatThemes$3(List r2, ResultCallback r3, boolean r4) {
        this.allChatThemes = new ArrayList(r2);
        r3.onComplete(getEmojiThemes((r4 ? 1 : 0) | 2));
    }

    private /* synthetic */ void lambda$requestAllChatThemes$4(ResultCallback r8, boolean r9, TL_account.Themes r10, TLRPC.TL_error r11) {
        boolean r1 = false;
        if ((r10 instanceof TL_account.TL_themes) == false) goto L10;
        TL_account.TL_themes r102 = (TL_account.TL_themes) r10;
        this.themesHash = r102.hash;
        this.lastReloadTimeMs = System.currentTimeMillis();
        SharedPreferences.Editor r112 = getSharedPreferences().edit();
        r112.clear();
        r112.putLong("hash", this.themesHash);
        r112.putLong("lastReload", this.lastReloadTimeMs);
        r112.putInt(NotificationBadge.NewHtcHomeBadger.COUNT, r102.themes.size());
        List<EmojiThemes> r0 = new ArrayList(r102.themes.size());
        int r2 = 0;
    L6:
        if (r2 >= r102.themes.size()) goto L8;
        TLRPC.TL_theme r3 = r102.themes.get(r2);
        Emoji.preloadEmoji(r3.emoticon);
        SerializedData r4 = new SerializedData(r3.getObjectSize());
        r3.serializeToStream(r4);
        r112.putString("theme_" + r2, Utilities.bytesToHex(r4.toByteArray()));
        EmojiThemes r42 = new EmojiThemes(this.currentAccount, r3, false);
        r42.preloadWallpaper();
        r0.add(r42);
        r2 = r2 + 1;
        goto L6
    L8:
        r112.apply();
    L13:
        if (r1 == true) goto L17;
        AndroidUtilities.runOnUIThread(new ChatThemeController$$ExternalSyntheticLambda2(this, r0, r8, r9));
        return;
    L17:
        return;
    L10:
        if ((r10 instanceof TL_account.TL_themesNotModified) == false) goto L12;
        r0 = getAllChatThemesFromPrefs();
        goto L13
    L12:
        AndroidUtilities.runOnUIThread(new ChatThemeController$$ExternalSyntheticLambda1(r8, r11));
        r1 = true;
        r0 = null;
        goto L13
    }

    private /* synthetic */ void lambda$requestNextChatThemes$21(TL_account.Tl_chatThemes r4, List r5, List r6, ResultCallback r7) {
        ThemeList.m3939$$Nest$fputoffset(this.giftsThemeList, r4.next_offset);
        ThemeList.m3937$$Nest$fputhash(this.giftsThemeList, r4.hash);
        ThemeList.m3938$$Nest$fputlastReloadTimeMs(this.giftsThemeList, System.currentTimeMillis());
        List r0 = ThemeList.m3935$$Nest$fgetthemes(this.giftsThemeList);
        ThemeList r1 = this.giftsThemeList;
        if (r0 != null) goto L5;
        ThemeList.m3940$$Nest$fputthemes(r1, new ArrayList(r5));
    L7:
        if (TextUtils.isEmpty(r4.next_offset) == false) goto L9;
        ThemeList.m3936$$Nest$fputcompleted(this.giftsThemeList, true);
    L9:
        Iterator r42 = r5.iterator();
    L11:
        if (r42.hasNext() == false) goto L13;
        EmojiThemes r52 = (EmojiThemes) r42.next();
        this.allChatGiftThemes.put(r52.getEmoticonOrSlug(), r52);
        goto L11
    L13:
        Iterator r43 = r6.iterator();
    L15:
        if (r43.hasNext() == false) goto L17;
        TLRPC.TL_chatThemeUniqueGift r53 = (TLRPC.TL_chatThemeUniqueGift) r43.next();
        long r02 = DialogObject.getPeerDialogId(r53.gift.theme_peer);
        setGiftThemeUser(r53.gift.slug, r02);
        goto L15
    L17:
        r7.onComplete(null);
        return;
    L5:
        ThemeList.m3935$$Nest$fgetthemes(r1).addAll(r5);
        goto L7
    }

    private /* synthetic */ void lambda$requestNextChatThemes$22(ResultCallback r4) {
        ThemeList.m3938$$Nest$fputlastReloadTimeMs(this.giftsThemeList, System.currentTimeMillis());
        ThemeList.m3936$$Nest$fputcompleted(this.giftsThemeList, true);
        r4.onComplete(null);
    }

    private /* synthetic */ void lambda$requestNextChatThemes$23(ResultCallback r7, TL_account.ChatThemes r8, TLRPC.TL_error r9) {
        if (r9 == null) goto L5;
        AndroidUtilities.runOnUIThread(new ChatThemeController$$ExternalSyntheticLambda22(r7, r9));
        return;
    L5:
        ArrayList r4 = new ArrayList();
        if ((r8 instanceof TL_account.Tl_chatThemes) == false) goto L19;
        TL_account.Tl_chatThemes r2 = (TL_account.Tl_chatThemes) r8;
        getMessagesStorage().putGiftChatThemes(r2.themes);
        getMessagesStorage().putUsersAndChats(r2.users, r2.chats, true, true);
        int r0 = 0;
        getMessagesController().putUsers(r2.users, false);
        getMessagesController().putChats(r2.chats, false);
        ArrayList<TLRPC.ChatTheme> r82 = r2.themes;
        int r92 = r82.size();
        int r1 = 0;
    L8:
        if (r1 >= r92) goto L12;
        TLRPC.ChatTheme r3 = r82.get(r1);
        r1 = r1 + 1;
        TLRPC.ChatTheme r32 = r3;
        if ((r32 instanceof TLRPC.TL_chatThemeUniqueGift) == false) goto L8;
        r4.add((TLRPC.TL_chatThemeUniqueGift) r32);
        goto L8
    L12:
        ArrayList r33 = new ArrayList(r4.size());
    L14:
        if (r0 >= r4.size()) goto L16;
        EmojiThemes r93 = new EmojiThemes(this.currentAccount, (TLRPC.TL_chatThemeUniqueGift) r4.get(r0));
        r93.preloadWallpaper();
        r33.add(r93);
        r0 = r0 + 1;
        goto L14
    L16:
        AndroidUtilities.runOnUIThread(new ChatThemeController$$ExternalSyntheticLambda23(this, r2, r33, r4, r7));
        return;
    L19:
        if ((r8 instanceof TL_account.TL_chatThemesNotModified) == false) goto L28;
        AndroidUtilities.runOnUIThread(new ChatThemeController$$ExternalSyntheticLambda24(this, r7));
        return;
    }

    private /* synthetic */ void lambda$setDialogTheme$6(TLRPC.Updates r1, TLRPC.TL_error r2) {
        if (r1 == null) goto L5;
        getMessagesController().processUpdates(r1, false);
        return;
    }

    private /* synthetic */ void lambda$setWallpaperToPeer$18(TLObject r15, long r16, boolean r18, String r19, Runnable r20) {
        if ((r15 instanceof TLRPC.Updates) == false) goto L41;
        TLRPC.Updates r0 = (TLRPC.Updates) r15;
        int r5 = this.currentAccount;
        TLRPC.WallPaper r6 = null;
        if (r16 < 0) goto L7;
        TLRPC.UserFull r4 = MessagesController.getInstance(r5).getUserFull(r16);
        TLRPC.ChatFull r52 = null;
    L8:
        if (r4 == null) goto L10;
        r6 = r4.wallpaper;
    L12:
        int r8 = 0;
    L14:
        if (r8 >= r0.updates.size()) goto L32;
        if ((r0.updates.get(r8) instanceof TL_update.TL_updateNewMessage) == false) goto L31;
        TLRPC.MessageAction r9 = ((TL_update.TL_updateNewMessage) r0.updates.get(r8)).message.action;
        if ((r9 instanceof TLRPC.TL_messageActionSetChatWallPaper) == false) goto L31;
        if (r18 == false) goto L32;
        TLRPC.TL_messageActionSetChatWallPaper r92 = (TLRPC.TL_messageActionSetChatWallPaper) r9;
        r92.wallpaper.uploadingImage = r19;
        if (r6 == null) goto L27;
        String r82 = r6.uploadingImage;
        if (r82 == null) goto L27;
        if (r82.equals(r19) == false) goto L27;
        r92.wallpaper.stripedThumb = r6.stripedThumb;
    L27:
        if (r4 == null) goto L29;
        TLRPC.WallPaper r3 = r92.wallpaper;
        r4.wallpaper = r3;
        r4.flags |= 16777216;
        saveChatWallpaper(r16, r3);
        getMessagesStorage().updateUserInfo(r4, false);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.userInfoDidLoad, new Object[]{Long.valueOf(r16), r4});
        goto L32
    L29:
        if (r52 == null) goto L32;
        TLRPC.WallPaper r32 = r92.wallpaper;
        r52.wallpaper = r32;
        r52.flags2 |= 128;
        saveChatWallpaper(r16, r32);
        getMessagesStorage().updateChatInfo(r52, false);
        NotificationCenter r1 = NotificationCenter.getInstance(this.currentAccount);
        int r2 = NotificationCenter.chatInfoDidLoad;
        Boolean r42 = Boolean.FALSE;
        r1.postNotificationName(r2, new Object[]{r52, 0, r42, r42});
    L31:
        r8 = r8 + 1;
    L32:
        MessagesController.getInstance(this.currentAccount).processUpdateArray(r0.updates, r0.users, r0.chats, false, r0.date);
        if (r20 == null) goto L35;
        r20.run();
    L35:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.wallpaperSettedToUser, new Object[0]);
        return;
    L10:
        if (r52 == null) goto L12;
        r6 = r52.wallpaper;
        goto L12
    L7:
        r52 = MessagesController.getInstance(r5).getChatFull(-r16);
        r4 = null;
        goto L8
    }

    private /* synthetic */ void lambda$setWallpaperToPeer$19(long r3, boolean r5, String r6, Runnable r7, TLObject r8, TLRPC.TL_error r9) {
        AndroidUtilities.runOnUIThread(new ChatThemeController$$ExternalSyntheticLambda0(this, r8, r3, r5, r6, r7));
    }

    private void loadWallpaperPatternBitmap(long r3, Utilities.Callback<WallpaperBitmapHolder> r5) {
        File r2 = new File(ApplicationLoader.getFilesDirFixed("rasterized/wallpaper"), String.format(Locale.US, "pattern_%d.pgm.gz", new Object[]{Long.valueOf(r3)}));
        chatThemeQueue.postRunnable(new ChatThemeController$$ExternalSyntheticLambda16(r2, r5));
    }

    private void preloadSticker(String r8) {
        new ImageReceiver().setImage(ImageLocation.getForDocument(MediaDataController.getInstance(UserConfig.selectedAccount).getEmojiAnimatedSticker(r8)), "50_50", null, null, null, 0);
        Emoji.preloadEmoji(r8);
    }

    private void requestNextChatThemes(ResultCallback<Void> r5) {
        if (ThemeList.m3932$$Nest$fgethash(this.giftsThemeList) == 0) goto L6;
        ThemeList.m3933$$Nest$fgetlastReloadTimeMs(this.giftsThemeList);
    L6:
        if ((System.currentTimeMillis() - ThemeList.m3933$$Nest$fgetlastReloadTimeMs(this.giftsThemeList)) <= 7200000) goto L8;
        boolean r0 = true;
    L10:
        if (ThemeList.m3935$$Nest$fgetthemes(this.giftsThemeList) != null) goto L12;
    L16:
        TL_account.Tl_getUniqueGiftChatThemes r02 = new TL_account.Tl_getUniqueGiftChatThemes();
        r02.offset = ThemeList.m3934$$Nest$fgetoffset(this.giftsThemeList);
        r02.hash = ThemeList.m3932$$Nest$fgethash(this.giftsThemeList);
        r02.limit = 50;
        ConnectionsManager r1 = getConnectionsManager();
        DispatchQueue r2 = chatThemeQueue;
        Objects.requireNonNull(r2);
        r1.sendRequestTyped(r02, new ChatThemeController$$ExternalSyntheticLambda6(r2), new ChatThemeController$$ExternalSyntheticLambda7(this, r5));
        return;
    L12:
        if (ThemeList.m3931$$Nest$fgetcompleted(this.giftsThemeList) == false) goto L16;
        if (r0 == true) goto L16;
        return;
    L8:
        r0 = false;
        goto L10
    }

    private void saveWallpaperBitmap(Bitmap r1, long r2) {
        File r0 = getPatternFile(r2);
        chatThemeQueue.postRunnable(new ChatThemeController$$ExternalSyntheticLambda5(r0, r1));
    }

    private void saveWallpaperPatternBitmap(Bitmap r3, List<WallpaperGiftPatternPosition> r4, long r5) {
        File r2 = new File(ApplicationLoader.getFilesDirFixed("rasterized/wallpaper"), String.format(Locale.US, "pattern_%d.pgm.gz", new Object[]{Long.valueOf(r5)}));
        chatThemeQueue.postRunnable(new ChatThemeController$$ExternalSyntheticLambda18(r2, r4, r3));
    }

    private void setDialogTheme(long r6, ThemeKey r8, TLRPC.ChatTheme r9, boolean r10) {
        if (ThemeKey.equals(this.dialogEmoticonsMap.get(r6), r8) == true) goto L37;
        LongSparseArray<ThemeKey> r0 = this.dialogEmoticonsMap;
        if (r8 != null) goto L8;
        r0.delete(r6);
    L10:
        if (r8 == null) goto L12;
        String r1 = r8.giftSlug;
    L13:
        setGiftThemeUser(r1, r6);
        if (r6 < 0) goto L22;
        TLRPC.UserFull r12 = getMessagesController().getUserFull(r6);
        if (r12 == null) goto L28;
        if (r8 != null) goto L19;
    L21:
        r12.theme = r9;
        getMessagesStorage().updateUserInfo(r12, true);
        goto L28
    L19:
        if (r8.isEmpty() == true) goto L21;
        if (r9 != null) goto L21;
    L28:
        SharedPreferences.Editor r92 = getEmojiSharedPreferences().edit();
        String r13 = "chatTheme_" + this.currentAccount + "_" + r6;
        if (r8 == null) goto L31;
        String r2 = r8.toSavedString();
    L32:
        r92.putString(r13, r2).apply();
        if (r10 == false) goto L36;
        TLRPC.TL_messages_setChatTheme r93 = new TLRPC.TL_messages_setChatTheme();
        r93.theme = ThemeKey.toInputTheme(r8);
        r93.peer = getMessagesController().getInputPeer(r6);
        getConnectionsManager().sendRequestTyped(r93, null, new ChatThemeController$$ExternalSyntheticLambda9(this));
        return;
    L36:
        return;
    L31:
        r2 = null;
        goto L32
    L22:
        TLRPC.ChatFull r94 = getMessagesController().getChatFull(-r6);
        if (r94 == null) goto L28;
        if (r8 == null) goto L26;
        String r14 = r8.emoticon;
    L27:
        r94.theme_emoticon = r14;
        getMessagesStorage().updateChatInfo(r94, true);
        goto L28
    L26:
        r14 = null;
        goto L27
    L12:
        r1 = null;
        goto L13
    L8:
        r0.put(r6, r8);
        goto L10
    }

    private void setGiftThemeUser(String r5, long r6) {
        if (TextUtils.isEmpty(r5) == false) goto L9;
        String r52 = this.usedGiftThemesByUsers.remove(Long.valueOf(r6));
        if (r52 == null) goto L24;
        this.usedGiftThemesBySlug.remove(r52);
        return;
    L24:
        return;
    L9:
        if (r6 != 0) goto L14;
        Long r53 = this.usedGiftThemesBySlug.remove(r5);
        if (r53 == null) goto L25;
        this.usedGiftThemesByUsers.remove(r53);
        return;
    L25:
        return;
    L14:
        String r0 = this.usedGiftThemesByUsers.put(Long.valueOf(r6), r5);
        Long r1 = this.usedGiftThemesBySlug.put(r5, Long.valueOf(r6));
        if (r0 != null) goto L17;
    L19:
        if (r1 != null) goto L21;
        return;
    L21:
        if (r1.longValue() == r6) goto L27;
        this.usedGiftThemesByUsers.remove(r1);
        return;
    L27:
        return;
    L17:
        if (TextUtils.equals(r5, r0) == true) goto L19;
        this.usedGiftThemesBySlug.remove(r0);
        goto L19
    }

    public static boolean wallpaperEquals(TLRPC.WallPaper r5, TLRPC.WallPaper r6) {
        if (r5 != null) goto L7;
        if (r6 != null) goto L7;
        return true;
    L7:
        if ((r5 instanceof TLRPC.TL_wallPaper) == false) goto L15;
        if ((r6 instanceof TLRPC.TL_wallPaper) == false) goto L15;
        if (r5.id != r6.id) goto L13;
        return true;
    L13:
        return false;
    L15:
        if ((r5 instanceof TLRPC.TL_wallPaperNoFile) == true) goto L17;
    L27:
        return false;
    L17:
        if ((r6 instanceof TLRPC.TL_wallPaperNoFile) == false) goto L27;
        if (r5.settings == null) goto L25;
        if (r6.settings == null) goto L25;
        return TextUtils.equals(getWallpaperEmoticon(r5), getWallpaperEmoticon(r6));
    L25:
        if (r5.id != r6.id) goto L27;
        return true;
    }

    public void clearCache() {
        this.themesHash = 0;
        this.lastReloadTimeMs = 0;
        getSharedPreferences().edit().clear().apply();
    }

    public void clearWallpaper(long r2, boolean r4) {
        clearWallpaper(r2, r4, false);
    }

    public void clearWallpaper(long r7, boolean r9, boolean r10) {
        TLRPC.TL_messages_setChatWallPaper r0 = new TLRPC.TL_messages_setChatWallPaper();
        int r2 = this.currentAccount;
        if (r7 < 0) goto L12;
        r0.peer = MessagesController.getInputPeer(MessagesController.getInstance(r2).getUser(Long.valueOf(r7)));
        r0.revert = r10;
        if (r10 == true) goto L18;
        TLRPC.UserFull r102 = getMessagesController().getUserFull(r7);
        if (r102 == null) goto L9;
        r102.wallpaper = null;
        r102.flags &= -16777217;
        getMessagesStorage().updateUserInfo(r102, false);
    L9:
        saveChatWallpaper(r7, null);
        if (r9 == false) goto L18;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.userInfoDidLoad, new Object[]{Long.valueOf(r7), r102});
    L18:
        getConnectionsManager().sendRequest(r0, new ChatThemeController$$ExternalSyntheticLambda4());
        return;
    L12:
        long r1 = -r7;
        r0.peer = MessagesController.getInputPeer(MessagesController.getInstance(r2).getChat(Long.valueOf(r1)));
        TLRPC.ChatFull r103 = getMessagesController().getChatFull(r1);
        if (r103 == null) goto L15;
        r103.wallpaper = null;
        r103.flags2 &= -129;
        getMessagesStorage().updateChatInfo(r103, false);
    L15:
        saveChatWallpaper(r7, null);
        if (r9 == false) goto L18;
        NotificationCenter r72 = NotificationCenter.getInstance(this.currentAccount);
        int r8 = NotificationCenter.chatInfoDidLoad;
        Boolean r12 = Boolean.FALSE;
        r72.postNotificationName(r8, new Object[]{r103, 0, r12, r12});
        goto L18
    }

    public void clearWallpaperImages() {
    }

    public void clearWallpaperThumbImages() {
        this.themeIdWallpaperThumbMap.clear();
    }

    public EmojiThemes getDialogTheme(long r4) {
        ThemeKey r0 = this.dialogEmoticonsMap.get(r4);
        if (r0 != null) goto L6;
        r0 = ThemeKey.fromSavedString(getEmojiSharedPreferences().getString("chatTheme_" + this.currentAccount + "_" + r4, null));
        this.dialogEmoticonsMap.put(r4, r0);
    L6:
        return getTheme(r0);
    }

    public TLRPC.WallPaper getDialogWallpaper(long r5) {
        if (ExteraConfig.getCustomThemes() == true) goto L6;
        return null;
    L6:
        if (r5 < 0) goto L11;
        TLRPC.UserFull r0 = getMessagesController().getUserFull(r5);
        if (r0 != null) goto L10;
    L15:
        String r4 = getEmojiSharedPreferences().getString("chatWallpaper_" + this.currentAccount + "_" + r5, null);
        if (r4 == null) goto L22;
        SerializedData r52 = new SerializedData(Utilities.hexToBytes(r4));
        return TLRPC.WallPaper.TLdeserialize(r52, r52.readInt32(true), true);
    L20:
        th = move-exception;
        FileLog.e(th);
    L22:
        return null;
    L10:
        return r0.wallpaper;
    L11:
        TLRPC.ChatFull r02 = getMessagesController().getChatFull(-r5);
        if (r02 == null) goto L15;
        return r02.wallpaper;
    }

    public List<EmojiThemes> getEmojiThemes(int r4) {
        boolean r0 = TLObject.hasFlag(r4, 1);
        boolean r1 = TLObject.hasFlag(r4, 2);
        boolean r42 = TLObject.hasFlag(r4, 4);
        ArrayList r2 = new ArrayList();
        if (r42 == true) goto L5;
    L7:
        if (r1 == false) goto L11;
        List<EmojiThemes> r43 = this.allChatThemes;
        if (r43 == null) goto L11;
        r2.addAll(r43);
    L11:
        int r44 = 0;
        if (r0 == true) goto L14;
    L18:
        int r3 = r2.size();
    L19:
        if (r44 >= r3) goto L21;
        Object r02 = r2.get(r44);
        r44 = r44 + 1;
        ((EmojiThemes) r02).initColors(null);
        goto L19
    L21:
        return r2;
    L14:
        if (r2.isEmpty() == false) goto L16;
    L17:
        r2.add(0, EmojiThemes.createChatThemesDefault(this.currentAccount));
        goto L18
    L16:
        if (((EmojiThemes) r2.get(0)).showAsDefaultStub == true) goto L18;
    L5:
        if (ThemeList.m3935$$Nest$fgetthemes(this.giftsThemeList) == null) goto L7;
        r2.addAll(ThemeList.m3935$$Nest$fgetthemes(this.giftsThemeList));
        goto L7
    }

    public long getGiftThemeUser(String r1) {
        Long r0 = this.usedGiftThemesBySlug.get(r1);
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.longValue();
    }

    public EmojiThemes getTheme(ThemeKey r3) {
        if (r3 != null) goto L4;
        return null;
    L4:
        if (TextUtils.isEmpty(r3.giftSlug) == false) goto L6;
        Iterator<EmojiThemes> r2 = this.allChatThemes.iterator();
    L9:
        if (r2.hasNext() == false) goto L18;
        EmojiThemes r0 = r2.next();
        if (r3.equals(r0.getThemeKey()) == false) goto L9;
        return r0;
    L18:
        return null;
    L6:
        return this.allChatGiftThemes.get(r3.giftSlug);
    }

    public Bitmap getWallpaperThumbBitmap(long r1) {
        return this.themeIdWallpaperThumbMap.get(Long.valueOf(r1));
    }

    public boolean isAllThemesFullyLoaded() {
        if (isGiftThemesFullyLoaded() == false) goto L10;
        List<EmojiThemes> r1 = this.allChatThemes;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.isEmpty() == true) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public boolean isGiftThemesFullyLoaded() {
        return ThemeList.m3931$$Nest$fgetcompleted(this.giftsThemeList);
    }

    public void loadNextChatThemes(ResultCallback<Void> r1) {
        requestNextChatThemes(r1);
    }

    public void loadWallpaperBitmap(long r2, int r4, Utilities.Callback<WallpaperBitmapHolder> r5) {
        if (r4 != 0) goto L6;
        getWallpaperBitmap(r2, new ChatThemeController$$ExternalSyntheticLambda14(r5));
        return;
    L6:
        if (r4 != 1) goto L9;
        loadWallpaperPatternBitmap(r2, r5);
        return;
    }

    public void preloadAllWallpaperImages(boolean r7) {
        Iterator<EmojiThemes> r0 = this.allChatThemes.iterator();
    L4:
        if (r0.hasNext() == false) goto L12;
        EmojiThemes r1 = r0.next();
        long r2 = r1.getThemeId(r7 ? 1 : 0);
        if (r2 == 0) goto L4;
        if (getPatternFile(r2).exists() == true) goto L4;
        r1.loadWallpaper(r7 ? 1 : 0, null);
        goto L4
    }

    public void preloadAllWallpaperThumbs(boolean r7) {
        Iterator<EmojiThemes> r0 = this.allChatThemes.iterator();
    L4:
        if (r0.hasNext() == false) goto L12;
        EmojiThemes r1 = r0.next();
        long r2 = r1.getThemeId(r7 ? 1 : 0);
        if (r2 == 0) goto L4;
        if (this.themeIdWallpaperThumbMap.containsKey(Long.valueOf(r2)) == true) goto L4;
        ChatThemeController$$ExternalSyntheticLambda21 r22 = new ChatThemeController$$ExternalSyntheticLambda21(this);
        r1.loadWallpaperThumb(r7 ? 1 : 0, r22);
        goto L4
    }

    public void processUpdate(TL_update.TL_updatePeerWallpaper r7) {
        if ((r7.peer instanceof TLRPC.TL_peerUser) == false) goto L15;
        TLRPC.UserFull r0 = getMessagesController().getUserFull(r7.peer.user_id);
        if (r0 != null) goto L7;
        return;
    L7:
        if (wallpaperEquals(r0.wallpaper, r7.wallpaper) == true) goto L28;
        long r3 = r0.id;
        if ((r7.flags & 1) == 0) goto L12;
        r0.wallpaper_overridden = r7.wallpaper_overridden;
        r0.wallpaper = r7.wallpaper;
        r0.flags |= 16777216;
    L13:
        getMessagesStorage().updateUserInfo(r0, false);
        saveChatWallpaper(r3, r0.wallpaper);
        AndroidUtilities.runOnUIThread(new ChatThemeController$$ExternalSyntheticLambda11(this, r3, r0));
        return;
    L12:
        r0.wallpaper_overridden = false;
        r0.wallpaper = null;
        r0.flags &= -16777217;
        goto L13
    L28:
        return;
    L15:
        TLRPC.ChatFull r02 = getMessagesController().getChatFull(-DialogObject.getPeerDialogId(r7.peer));
        if (r02 != null) goto L18;
        return;
    L18:
        if (wallpaperEquals(r02.wallpaper, r7.wallpaper) == true) goto L29;
        long r32 = -r02.id;
        if ((r7.flags & 1) == 0) goto L23;
        r02.wallpaper = r7.wallpaper;
        r02.flags2 |= 128;
    L24:
        getMessagesStorage().updateChatInfo(r02, false);
        saveChatWallpaper(r32, r02.wallpaper);
        AndroidUtilities.runOnUIThread(new ChatThemeController$$ExternalSyntheticLambda12(this, r02));
        return;
    L23:
        r02.wallpaper = null;
        r02.flags2 &= -129;
        goto L24
    }

    public void putThemeIfNeeded(TLRPC.ChatTheme r4) {
        if ((r4 instanceof TLRPC.TL_chatThemeUniqueGift) == false) goto L8;
        TLRPC.TL_chatThemeUniqueGift r0 = (TLRPC.TL_chatThemeUniqueGift) r4;
        if (this.allChatGiftThemes.containsKey(r0.gift.slug) == true) goto L9;
        EmojiThemes r1 = new EmojiThemes(this.currentAccount, r0);
        r1.initColors(new ChatThemeController$$ExternalSyntheticLambda3(this, r0, r1));
        getMessagesStorage().putGiftChatTheme(r4);
        return;
    L9:
        return;
    }

    public void requestAllChatThemes(ResultCallback<List<EmojiThemes>> r5, boolean r6) {
        if (this.themesHash != 0) goto L5;
    L6:
        init();
    L8:
        if ((System.currentTimeMillis() - this.lastReloadTimeMs) <= 7200000) goto L10;
        boolean r0 = true;
    L11:
        List<EmojiThemes> r1 = this.allChatThemes;
        if (r1 != null) goto L14;
    L16:
        TL_account.getChatThemes r02 = new TL_account.getChatThemes();
        r02.hash = this.themesHash;
        ConnectionsManager r12 = getConnectionsManager();
        DispatchQueue r2 = chatThemeQueue;
        Objects.requireNonNull(r2);
        r12.sendRequestTyped(r02, new ChatThemeController$$ExternalSyntheticLambda6(r2), new ChatThemeController$$ExternalSyntheticLambda19(this, r5, r6));
    L17:
        List<EmojiThemes> r03 = this.allChatThemes;
        if (r03 != null) goto L20;
        return;
    L20:
        if (r03.isEmpty() == true) goto L24;
        r5.onComplete(getEmojiThemes((r6 ? 1 : 0) | 2));
        return;
    L24:
        return;
    L14:
        if (r1.isEmpty() == true) goto L16;
        if (r0 == false) goto L17;
    L10:
        r0 = false;
        goto L11
    L5:
        if (this.lastReloadTimeMs != 0) goto L8;
        goto L6
    }

    public void requestChatTheme(ThemeKey r3, ResultCallback<EmojiThemes> r4) {
        if (r3 != null) goto L5;
    L17:
        r4.onComplete(null);
        return;
    L5:
        if (r3.isEmpty() == true) goto L17;
        if (TextUtils.isEmpty(r3.giftSlug) == true) goto L15;
        EmojiThemes r2 = this.allChatGiftThemes.get(r3.giftSlug);
        if (r2 == null) goto L13;
        r2.initColors(new ChatThemeController$$ExternalSyntheticLambda15(r4, r2));
        return;
    L13:
        r4.onComplete(null);
        return;
    L15:
        requestAllChatThemes(new AnonymousClass1(this, r3, r4), false);
    }

    public void saveChatWallpaper(long r5, TLRPC.WallPaper r7) {
        if (r7 != null) goto L5;
        getEmojiSharedPreferences().edit().remove("chatWallpaper_" + this.currentAccount + "_" + r5).apply();
        return;
    L5:
        if (r7.document != null) goto L7;
        return;
    L7:
        SerializedData r2 = new SerializedData(r7.getObjectSize());
        r7.serializeToStream(r2);
        String r72 = Utilities.bytesToHex(r2.toByteArray());
        getEmojiSharedPreferences().edit().putString("chatWallpaper_" + this.currentAccount + "_" + r5, r72).apply();
    }

    public void saveWallpaperBitmap(WallpaperBitmapHolder r4, long r5) {
        Bitmap r0 = r4.bitmap;
        int r1 = r4.mode;
        if (r1 != 0) goto L7;
        saveWallpaperBitmap(r0, r5);
        return;
    L7:
        if (r1 != 1) goto L10;
        saveWallpaperPatternBitmap(r0, r4.giftPatternPositions, r5);
        return;
    }

    public void setDialogTheme(long r7, TLRPC.ChatTheme r9, boolean r10) {
        setDialogTheme(r7, ThemeKey.of(r9), r9, r10);
    }

    public void setDialogTheme(long r7, ThemeKey r9) {
        setDialogTheme(r7, r9, null, true);
    }

    public int setWallpaperToPeer(long r10, String r12, Theme.OverrideWallpaperInfo r13, MessageObject r14, Runnable r15) {
        TLRPC.TL_messages_setChatWallPaper r0 = new TLRPC.TL_messages_setChatWallPaper();
        int r2 = this.currentAccount;
        if (r10 < 0) goto L5;
        r0.peer = MessagesController.getInputPeer(MessagesController.getInstance(r2).getUser(Long.valueOf(r10)));
    L6:
        r0.for_both = r13.forBoth;
        if (r14 != null) goto L9;
    L33:
        r0.flags |= 1;
        r0.wallpaper = MessagesController.getInputWallpaper(r13);
        boolean r6 = true;
    L34:
        r0.flags |= 4;
        r0.settings = MessagesController.getWallpaperSetting(r13);
        return ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new ChatThemeController$$ExternalSyntheticLambda13(this, r10, r6, r12, r15));
    L9:
        if ((r14.messageOwner.action instanceof TLRPC.TL_messageActionSetChatWallPaper) == false) goto L33;
        r0.flags |= 2;
        r0.id = r14.getId();
        int r22 = this.currentAccount;
        TLRPC.WallPaper r3 = null;
        if (r10 < 0) goto L13;
        TLRPC.UserFull r1 = MessagesController.getInstance(r22).getUserFull(r10);
        TLRPC.ChatFull r23 = null;
    L14:
        TLRPC.TL_messageActionSetChatWallPaper r142 = (TLRPC.TL_messageActionSetChatWallPaper) r14.messageOwner.action;
        TLRPC.TL_wallPaper r4 = new TLRPC.TL_wallPaper();
        TLRPC.WallPaper r5 = r142.wallpaper;
        r4.id = r5.id;
        r4.document = r5.document;
        TLRPC.TL_wallPaperSettings r52 = new TLRPC.TL_wallPaperSettings();
        r4.settings = r52;
        r52.intensity = (int) (r13.intensity * 100.0f);
        r52.motion = r13.isMotion;
        r52.blur = r13.isBlurred;
        r52.background_color = r13.color;
        r52.second_background_color = r13.gradientColor1;
        r52.third_background_color = r13.gradientColor2;
        r52.fourth_background_color = r13.gradientColor3;
        r52.rotation = r13.rotation;
        r4.uploadingImage = r12;
        if (r1 == null) goto L17;
        r3 = r1.wallpaper;
    L19:
        if (r3 == null) goto L25;
        String r53 = r3.uploadingImage;
        if (r53 == null) goto L25;
        if (r53.equals(r12) == false) goto L25;
        r4.stripedThumb = r3.stripedThumb;
    L25:
        r4.settings.flags |= 121;
        TLRPC.TL_wallPaper r32 = new TLRPC.TL_wallPaper();
        TLRPC.WallPaper r143 = r142.wallpaper;
        r32.pattern = r143.pattern;
        r32.id = r143.id;
        r32.document = r143.document;
        int r54 = r143.flags;
        r32.flags = r54;
        r32.creator = r143.creator;
        r32.dark = r143.dark;
        r32.isDefault = r143.isDefault;
        r32.slug = r143.slug;
        r32.access_hash = r143.access_hash;
        r32.stripedThumb = r143.stripedThumb;
        r32.settings = r4.settings;
        r32.flags = r54 | 4;
        if (r1 == null) goto L28;
        r1.wallpaper = r32;
        r1.flags |= 16777216;
        getMessagesStorage().updateUserInfo(r1, false);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.userInfoDidLoad, new Object[]{Long.valueOf(r10), r1});
    L30:
        if (r15 == null) goto L32;
        r15.run();
    L32:
        r6 = false;
        goto L34
    L28:
        if (r23 == null) goto L30;
        r23.wallpaper = r32;
        r23.flags2 |= 128;
        getMessagesStorage().updateChatInfo(r23, false);
        NotificationCenter r16 = NotificationCenter.getInstance(this.currentAccount);
        int r33 = NotificationCenter.chatInfoDidLoad;
        Boolean r55 = Boolean.FALSE;
        r16.postNotificationName(r33, new Object[]{r23, 0, r55, r55});
        goto L30
    L17:
        if (r23 == null) goto L19;
        r3 = r23.wallpaper;
        goto L19
    L13:
        r23 = MessagesController.getInstance(r22).getChatFull(-r10);
        r1 = null;
        goto L14
    L5:
        r0.peer = MessagesController.getInputPeer(MessagesController.getInstance(r2).getChat(Long.valueOf(-r10)));
        goto L6
    }
}
