package com.exteragram.messenger.utils.text;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.text.style.ReplacementSpan;
import android.text.style.URLSpan;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.network.RemoteUtils;
import com.exteragram.messenger.utils.ui.ColorRectSpan;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LinkifyPort;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.URLSpanNoUnderline;
import org.telegram.ui.Components.URLSpanReplacement;
import org.telegram.ui.FilterCreateActivity;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes.dex */
public abstract class LocaleUtils {
    private static final Pattern HEX_PATTERN = null;
    private static final Pattern MARKDOWN_LINK_PATTERN = null;

    /* renamed from: com.exteragram.messenger.utils.text.LocaleUtils$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass1 extends URLSpanNoUnderline {
        final /* synthetic */ BaseFragment val$fragment;
        final /* synthetic */ Runnable val$onClick;
        final /* synthetic */ String val$username;

        public AnonymousClass1(String r1, Runnable r2, BaseFragment r3, String r4) {
            this.val$onClick = r2;
            this.val$fragment = r3;
            this.val$username = r4;
            super(r1);
        }

        @Override // org.telegram.ui.Components.URLSpanNoUnderline, android.text.style.URLSpan, android.text.style.ClickableSpan
        public void onClick(View r3) {
            Runnable r32 = this.val$onClick;
            if (r32 == null) goto L5;
            r32.run();
        L5:
            BaseFragment r33 = this.val$fragment;
            if (r33 != null) goto L8;
            return;
        L8:
            if (r33.getMessagesController() == null) goto L12;
            this.val$fragment.getMessagesController().openByUserName(this.val$username.substring(1), this.val$fragment, 1);
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.utils.text.LocaleUtils$2, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass2 extends URLSpanNoUnderline {
        public AnonymousClass2(String r1) {
            super(r1);
        }

        @Override // org.telegram.ui.Components.URLSpanNoUnderline, android.text.style.URLSpan, android.text.style.ClickableSpan
        public void onClick(View r1) {
            super.onClick(r1);
        }
    }

    /* renamed from: com.exteragram.messenger.utils.text.LocaleUtils$3, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass3 extends URLSpanNoUnderline {
        public AnonymousClass3(String r1) {
            super(r1);
        }

        @Override // org.telegram.ui.Components.URLSpanNoUnderline, android.text.style.URLSpan, android.text.style.ClickableSpan
        public void onClick(View r1) {
            super.onClick(r1);
        }
    }

    /* renamed from: com.exteragram.messenger.utils.text.LocaleUtils$4, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass4 extends URLSpanReplacement {
        final /* synthetic */ Runnable val$onClick;

        public AnonymousClass4(String r1, Runnable r2) {
            this.val$onClick = r2;
            super(r1);
        }

        @Override // org.telegram.ui.Components.URLSpanReplacement, android.text.style.URLSpan, android.text.style.ClickableSpan
        public void onClick(View r2) {
            Runnable r0 = this.val$onClick;
            if (r0 == null) goto L5;
            r0.run();
        L5:
            super.onClick(r2);
        }
    }

    static {
        MARKDOWN_LINK_PATTERN = Pattern.compile("\\[([^]]+?)]\\(" + LinkifyPort.WEB_URL_REGEX + "\\)");
        HEX_PATTERN = Pattern.compile("(?<![a-zA-Z0-9])#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{8})(?![a-zA-Z0-9])");
    }

    public static CharSequence applyBadge(CharSequence r3, CharSequence r4, int r5, int r6) {
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r3 != null) goto L5;
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        SpannableStringBuilder r1 = new SpannableStringBuilder(r3);
        r1.append("  d");
        FilterCreateActivity.NewSpan r32 = new FilterCreateActivity.NewSpan(10.0f);
        if (r4 == null) goto L9;
        r0 = r4.toString();
    L9:
        r32.setText(r0);
        r32.setTypeface(AndroidUtilities.bold());
        r32.setColor(Theme.getColor(r5));
        r32.setTextColor(Theme.getColor(r6));
        r1.setSpan(r32, r1.length() - 1, r1.length(), 33);
        return r1;
    }

    public static CharSequence applyNewSpan(CharSequence r3) {
        return applyBadge(r3, "NEW", Theme.key_featuredStickers_addButton, Theme.key_featuredStickers_buttonText);
    }

    public static boolean canUseLocalPremiumEmojis() {
        return canUseLocalPremiumEmojis(UserConfig.selectedAccount);
    }

    public static boolean canUseLocalPremiumEmojis(int r2) {
        if (RemoteUtils.getBooleanConfigValue("local_premium_emojis", false).booleanValue() == true) goto L5;
    L8:
        return false;
    L5:
        if (UserConfig.getInstance(r2).isPremium() == true) goto L8;
        return true;
    }

    private static boolean containsHash(CharSequence r4) {
        int r1 = 0;
    L4:
        if (r1 >= r4.length()) goto L10;
        if (r4.charAt(r1) == '#') goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    public static Spannable createCopySpan(BaseFragment r4) {
        SpannableString r0 = new SpannableString(" ");
        Drawable r1 = ContextCompat.getDrawable(r4.getParentActivity(), R.drawable.msg_copy).mutate();
        r1.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_undo_cancelColor, r4.getResourceProvider()), PorterDuff.Mode.SRC_IN));
        r1.setBounds(0, 0, AndroidUtilities.dp(22.0f), AndroidUtilities.dp(22.0f));
        r0.setSpan(new ImageSpan(r1, 0), 0, 1, 33);
        return r0;
    }

    public static String ensureUrlHasHttps(String r2) {
        if (r2 != null) goto L6;
        return null;
    L6:
        if (LinkifyPort.WEB_URL.matcher(r2).matches() == true) goto L8;
        return r2;
    L8:
        if (r2.startsWith("http://") == false) goto L10;
        return r2;
    L10:
        if (r2.startsWith("https://") == false) goto L12;
        return r2;
    L12:
        if (r2.contains("://") == false) goto L14;
        return r2;
    L14:
        return AndroidUtilities.defaultUrlScheme(r2) + r2;
    }

    public static CharSequence formatWithHtmlURLs(CharSequence r7) {
        if (TextUtils.isEmpty(r7) == false) goto L5;
        return r7;
    L5:
        SpannableString r0 = new SpannableString(r7);
        int r2 = 0;
        URLSpan[] r72 = (URLSpan[]) r0.getSpans(0, r7.length(), URLSpan.class);
        SpannableStringBuilder r1 = new SpannableStringBuilder(r0);
        int r02 = r72.length;
    L6:
        if (r2 >= r02) goto L8;
        URLSpan r3 = r72[r2];
        int r4 = r1.getSpanStart(r3);
        int r5 = r1.getSpanEnd(r3);
        String r6 = r3.getURL();
        r1.removeSpan(r3);
        r1.setSpan(new AnonymousClass2(r6), r4, r5, 33);
        r2 = r2 + 1;
        goto L6
    L8:
        return r1;
    }

    public static CharSequence formatWithURLs(CharSequence r5) {
        if (TextUtils.isEmpty(r5) == false) goto L5;
        return r5;
    L5:
        SpannableStringBuilder r0 = new SpannableStringBuilder(r5);
        Matcher r52 = LinkifyPort.WEB_URL.matcher(r5);
    L7:
        if (r52.find() == false) goto L12;
        r0.setSpan(new AnonymousClass3(ensureUrlHasHttps(r52.group(0))), r52.start(), r52.end(), 33);     // Catch: Exception -> L10
    L10:
        e = move-exception;
        FileLog.e(e);
        goto L7
    L12:
        return r0;
    }

    public static CharSequence formatWithUsernames(CharSequence r1) {
        return formatWithUsernames(r1, LaunchActivity.getSafeLastFragment());
    }

    public static CharSequence formatWithUsernames(CharSequence r1, BaseFragment r2) {
        return formatWithUsernames(r1, r2, null);
    }

    public static CharSequence formatWithUsernames(CharSequence r7, BaseFragment r8, Runnable r9) {
        if (TextUtils.isEmpty(r7) == false) goto L5;
        return r7;
    L5:
        SpannableStringBuilder r0 = new SpannableStringBuilder(r7);
        int r2 = 0;
        int r3 = -1;
    L7:
        if (r2 >= r7.length()) goto L31;
        if (r7.charAt(r2) != '@') goto L11;
        r3 = r2;
    L30:
        r2 = r2 + 1;
        goto L7
    L11:
        if (r3 == (-1)) goto L30;
        int r4 = r2 + 1;
        if (r4 == r7.length()) goto L19;
        if (Character.isLetterOrDigit(r7.charAt(r4)) == true) goto L30;
        if (r7.charAt(r4) == '_') goto L30;
    L19:
        if ((r4 - r3) <= 1) goto L24;
        URLSpan[] r5 = (URLSpan[]) r0.getSpans(r3, r4, URLSpan.class);
        if (r5 != null) goto L23;
    L25:
        String r52 = r7.subSequence(r3, r4).toString();
        r0.setSpan(new AnonymousClass1(r52, r9, r8, r52), r3, r4, 33);     // Catch: Exception -> L28
    L28:
        e = move-exception;
        FileLog.e(e);
        goto L24
    L23:
        if (r5.length <= 0) goto L25;
    L24:
        r3 = -1;
        goto L30
    L31:
        return r0;
    }

    public static CharSequence fullyFormatText(CharSequence r1) {
        return fullyFormatText(r1, null, null);
    }

    public static CharSequence fullyFormatText(CharSequence r2, BaseFragment r3, Runnable r4) {
        if (TextUtils.isEmpty(r2) == false) goto L5;
        return r2;
    L5:
        CharSequence[] r0 = {formatWithURLs(r2)};
        parseMarkdownLinks(r0, r4);
        CharSequence r22 = r0[0];
        if (r3 == null) goto L9;
        if (r4 == null) goto L9;
        CharSequence r23 = formatWithUsernames(r22, r3, r4);
    L11:
        return AndroidUtilities.replaceTags(r23);
    L9:
        r23 = formatWithUsernames(r22);
        goto L11
    }

    public static String getActionBarTitle() {
        return getActionBarTitle(UserConfig.selectedAccount);
    }

    public static String getActionBarTitle(int r2) {
        int r0 = ExteraConfig.getTitleText();
        if (r0 == 0) goto L17;
        if (r0 == 3) goto L15;
        TLRPC.User r22 = UserConfig.getInstance(r2).getCurrentUser();
        if (r0 != 1) goto L13;
        if (TextUtils.isEmpty(UserObject.getPublicUsername(r22)) == true) goto L13;
        return UserObject.getPublicUsername(r22);
    L13:
        return UserObject.getFirstName(r22);
    L15:
        return LocaleController.getString(R.string.FilterChats);
    L17:
        return LocaleController.getString(R.string.exteraAppName);
    }

    public static String getAppName() {
        return ApplicationLoader.applicationContext.getString(R.string.exteraAppName);
    L4:
        return "exteraGram";
    }

    private static boolean hasConflictingHexPreviewSpan(Spannable r7, int r8, int r9) {
        Object[] r0 = r7.getSpans(r8, r9, Object.class);
        int r1 = r0.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L16;
        Object r4 = r0[r3];
        if ((r4 instanceof ColorRectSpan) == true) goto L15;
        int r5 = r7.getSpanStart(r4);
        int r6 = r7.getSpanEnd(r4);
        if (r5 >= r9) goto L15;
        if (r6 <= r8) goto L15;
        if ((r4 instanceof ReplacementSpan) == false) goto L15;
        return true;
    L15:
        r3 = r3 + 1;
        goto L3
    L16:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.regex.Pattern] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [android.text.Spannable, android.text.Spanned, java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r5v5 */
    public static CharSequence insertHexColorsPreview(CharSequence r5) {
        if (TextUtils.isEmpty(r5) == false) goto L5;
    L23:
        return r5;
    L5:
        if (containsHash(r5) == false) goto L23;
        if ((r5 instanceof Spannable) == false) goto L10;
        r5 = (Spannable) r5;
    L11:
        int r2 = 0;
        ColorRectSpan[] r0 = (ColorRectSpan[]) r5.getSpans(0, r5.length(), ColorRectSpan.class);
        int r1 = r0.length;
    L12:
        if (r2 >= r1) goto L14;
        r5.removeSpan(r0[r2]);
        r2 = r2 + 1;
        goto L12
    L14:
        Matcher r02 = HEX_PATTERN.matcher(r5);
    L16:
        if (r02.find() == false) goto L23;
        int r12 = r02.end();
        int r22 = r12 - 1;
        if (hasConflictingHexPreviewSpan(r5, r22, r12) == true) goto L16;
        r5.setSpan(new ColorRectSpan(Color.parseColor(r02.group())), r22, r12, 33);     // Catch: IllegalArgumentException -> L22
    L22:
        FileLog.e("Invalid HEX color: " + r02.group());
        goto L16
    L10:
        r5 = new SpannableString(r5);
        goto L11
    }

    public static boolean isCustomEmojiOnlyLinkMessage(TLRPC.Message r6) {
        if (r6 == null) goto L37;
        ArrayList<TLRPC.MessageEntity> r1 = r6.entities;
        if (r1 == null) goto L37;
        if (r1.isEmpty() == true) goto L37;
        if (MessageObject.isMediaEmptyWebpage(r6) == true) goto L12;
        return false;
    L12:
        int r12 = 0;
        boolean r2 = false;
    L14:
        if (r12 >= r6.entities.size()) goto L36;
        TLRPC.MessageEntity r3 = r6.entities.get(r12);
        if ((r3 instanceof TLRPC.TL_messageEntityTextUrl) == false) goto L25;
        String r22 = ((TLRPC.TL_messageEntityTextUrl) r3).url;
        if (r22 == null) goto L23;
        if (r22.startsWith("tg://emoji?id=") == false) goto L23;
    L22:
        r2 = true;
    L34:
        r12 = r12 + 1;
    L23:
        return false;
    L25:
        if ((r3 instanceof TLRPC.TL_messageEntityCustomEmoji) == false) goto L30;
        if (((TLRPC.TL_messageEntityCustomEmoji) r3).local == false) goto L34;
    L30:
        if ((r3 instanceof TLRPC.TL_messageEntityUrl) == true) goto L35;
        if ((r3 instanceof TLRPC.TL_messageEntityEmail) == false) goto L34;
    L35:
        return false;
    L36:
        return r2;
    L37:
        return false;
    }

    public static String normalizeResourceLanguage(String r1) {
        if (TextUtils.isEmpty(r1) == false) goto L6;
        return null;
    L6:
        String r12 = r1.toLowerCase(Locale.US);
        if ("he".equals(r12) == false) goto L11;
        return "iw";
    L11:
        if ("no".equals(r12) == false) goto L14;
        return "nb";
    L14:
        return r12;
    }

    public static String normalizeResourceRegion(String r1, String r2) {
        if (TextUtils.isEmpty(r2) == false) goto L6;
        return null;
    L6:
        String r12 = normalizeResourceLanguage(r1);
        String r22 = r2.toUpperCase(Locale.US);
        if ("zh".equals(r12) == false) goto L24;
        String r0 = "CN";
        if ("HANS".equals(r22) == false) goto L11;
    L23:
        return r0;
    L11:
        if ("CN".equals(r22) == true) goto L23;
        if ("SG".equals(r22) == true) goto L23;
        r0 = "TW";
        if ("HANT".equals(r22) == true) goto L23;
        if ("TW".equals(r22) == true) goto L23;
        if ("HK".equals(r22) == true) goto L23;
        if ("MO".equals(r22) == true) goto L23;
    L24:
        return r22;
    }

    public static boolean parseCustomEmojis(CharSequence r10, ArrayList<TLRPC.MessageEntity> r11) {
        if (r10 == null) goto L36;
        if (r11 == null) goto L36;
        if (r11.isEmpty() == true) goto L36;
        int r1 = 0;
        boolean r2 = false;
    L10:
        if (r1 >= r11.size()) goto L35;
        TLRPC.MessageEntity r3 = r11.get(r1);
        if ((r3 instanceof TLRPC.TL_messageEntityTextUrl) == false) goto L34;
        TLRPC.TL_messageEntityTextUrl r32 = (TLRPC.TL_messageEntityTextUrl) r3;
        String r4 = r32.url;
        if (r4 == null) goto L34;
        if (r4.startsWith("tg://emoji?id=") == false) goto L34;
        int r42 = r32.offset;
        if (r42 < 0) goto L34;
        int r5 = r32.length;
        if (r5 <= 0) goto L34;
        if ((r42 + r5) > r10.length()) goto L34;
        long r43 = Long.parseLong(r32.url.substring(14));     // Catch: NumberFormatException -> L32
        int[] r7 = new int[1];
        int r8 = r32.offset;
        ArrayList<Emoji.EmojiSpanRange> r82 = Emoji.parseEmojis(r10.subSequence(r8, r32.length + r8).toString(), r7);
        if (r7[0] <= 0) goto L34;
        if (r82.size() != 1) goto L34;
        TLRPC.TL_messageEntityCustomEmoji r22 = new TLRPC.TL_messageEntityCustomEmoji();
        r22.document_id = r43;
        r22.offset = r32.offset;
        r22.length = r32.length;
        r22.local = true;
        r11.set(r1, r22);
        r2 = true;
    L32:
        e = move-exception;
        FileLog.e("Failed to parse custom emoji id: " + r32.url, e);
    L34:
        r1 = r1 + 1;
        goto L10
    L35:
        return r2;
    L36:
        return false;
    }

    public static void parseMarkdownLinks(CharSequence[] r1) {
        parseMarkdownLinks(r1, null);
    }

    public static void parseMarkdownLinks(CharSequence[] r9, Runnable r10) {
        if (r9 != null) goto L4;
        return;
    L4:
        if (r9.length == 0) goto L38;
        CharSequence r1 = r9[0];
        if (r1 != null) goto L9;
        return;
    L9:
        if ((r1 instanceof Spannable) == false) goto L11;
        Spannable r12 = (Spannable) r1;
    L12:
        Matcher r2 = MARKDOWN_LINK_PATTERN.matcher(r12);
        ArrayList r3 = new ArrayList();
        ArrayList r4 = new ArrayList();
    L14:
        if (r2.find() == false) goto L23;
        int r6 = r2.start(1);
        int r5 = r2.end(1);
        if (r6 < 0) goto L14;
        if (r5 < 0) goto L14;
        if (r6 > r5) goto L14;
        if (r5 > r12.length()) goto L14;
        SpannableStringBuilder r7 = new SpannableStringBuilder(r12.subSequence(r6, r5));
        r7.setSpan(new AnonymousClass4(ensureUrlHasHttps(r2.group(2)), r10), 0, r7.length(), 33);
        r3.add(r2.group(0));
        r4.add(r7);
        goto L14
    L23:
        if (r3.isEmpty() == true) goto L39;
        r9[0] = TextUtils.replace(r9[0], (String[]) r3.toArray(new String[0]), (CharSequence[]) r4.toArray(new CharSequence[0]));
        return;
    L39:
        return;
    L11:
        r12 = Spannable.Factory.getInstance().newSpannable(r9[0].toString());
        goto L12
    }

    public static SpannableStringBuilder replaceArrows(Context r5, CharSequence r6, int r7) {
        SpannableStringBuilder r0 = new SpannableStringBuilder(r6);
        int r1 = TextUtils.indexOf(r0, "->");
    L3:
        if (r1 < 0) goto L8;
        ColoredImageSpan r2 = new ColoredImageSpan(ContextCompat.getDrawable(r5, r7).mutate(), 2);
        if (LocaleController.isRTL == false) goto L7;
        r2.rotate(180.0f);
    L7:
        r0.replace(r1, r1 + 2, ">");
        int r3 = r1 + 1;
        r0.setSpan(r2, r1, r3, 33);
        r1 = TextUtils.indexOf(r0, "->", r3);
        goto L3
    L8:
        return r0;
    }

    public static void replaceCustomEmojis(int r5, long r6, ArrayList<TLRPC.MessageEntity> r8) {
        if (r8 != null) goto L4;
        return;
    L4:
        if (r8.isEmpty() == false) goto L6;
        return;
    L6:
        if (canUseLocalPremiumEmojis(r5) == true) goto L9;
        return;
    L9:
        if (r6 == UserConfig.getInstance(r5).getClientUserId()) goto L53;
        int r1 = 0;
        if (r6 >= 0) goto L24;
        TLRPC.ChatFull r62 = MessagesController.getInstance(r5).getChatFull(-r6);
        if (r62 == null) goto L24;
        if (r62.emojiset == null) goto L24;
        TLRPC.TL_messages_stickerSet r63 = MediaDataController.getInstance(r5).getGroupStickerSetById(r62.emojiset);
        if (r63 == null) goto L24;
        if (r63.documents == null) goto L24;
        HashSet r7 = new HashSet();
        ArrayList<TLRPC.Document> r64 = r63.documents;
        int r0 = r64.size();
        int r2 = 0;
    L22:
        if (r2 >= r0) goto L26;
        TLRPC.Document r3 = r64.get(r2);
        r2 = r2 + 1;
        r7.add(Long.valueOf(r3.id));
    L26:
        if (r1 >= r8.size()) goto L54;
        TLRPC.MessageEntity r65 = r8.get(r1);
        if ((r65 instanceof TLRPC.TL_messageEntityCustomEmoji) == false) goto L42;
        TLRPC.TL_messageEntityCustomEmoji r66 = (TLRPC.TL_messageEntityCustomEmoji) r65;
        if (r7 != null) goto L32;
    L34:
        TLRPC.Document r02 = r66.document;
        if (r02 != null) goto L39;
        r02 = AnimatedEmojiDrawable.findDocument(r5, r66.document_id);
    L39:
        if (MessageObject.isFreeEmoji(r02) == true) goto L42;
        r8.set(r1, toCustomEmojiLink(r66));
        goto L42
    L32:
        if (r7.contains(Long.valueOf(r66.document_id)) == false) goto L34;
    L42:
        r1 = r1 + 1;
        goto L26
    L54:
        return;
    L24:
        r7 = null;
        goto L26
    }

    public static void replaceLocalCustomEmojis(ArrayList<TLRPC.MessageEntity> r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.isEmpty() == true) goto L20;
        int r0 = 0;
    L8:
        if (r0 >= r3.size()) goto L21;
        TLRPC.MessageEntity r1 = r3.get(r0);
        if ((r1 instanceof TLRPC.TL_messageEntityCustomEmoji) == false) goto L14;
        TLRPC.TL_messageEntityCustomEmoji r12 = (TLRPC.TL_messageEntityCustomEmoji) r1;
        if (r12.local == false) goto L14;
        r3.set(r0, toCustomEmojiLink(r12));
    L14:
        r0 = r0 + 1;
        goto L8
    L21:
        return;
    }

    public static void restoreLocalCustomEmojis(TLRPC.Message r0, ArrayList<TLRPC.MessageEntity> r1) {
        if (r0 == null) goto L6;
        if (r1 == null) goto L7;
        r0.entities = r1;
        return;
    L7:
        return;
    }

    public static ArrayList<TLRPC.MessageEntity> swapLocalCustomEmojis(TLRPC.Message r4) {
        if (r4 != null) goto L5;
    L17:
        return null;
    L5:
        if (r4.entities == null) goto L17;
        int r1 = 0;
    L9:
        if (r1 >= r4.entities.size()) goto L17;
        TLRPC.MessageEntity r2 = r4.entities.get(r1);
        if ((r2 instanceof TLRPC.TL_messageEntityCustomEmoji) == false) goto L16;
        if (((TLRPC.TL_messageEntityCustomEmoji) r2).local == false) goto L16;
        ArrayList<TLRPC.MessageEntity> r0 = r4.entities;
        ArrayList<TLRPC.MessageEntity> r12 = new ArrayList(r0);
        r4.entities = r12;
        replaceLocalCustomEmojis(r12);
        return r0;
    L16:
        r1 = r1 + 1;
        goto L9
    }

    private static TLRPC.TL_messageEntityTextUrl toCustomEmojiLink(TLRPC.TL_messageEntityCustomEmoji r4) {
        TLRPC.TL_messageEntityTextUrl r0 = new TLRPC.TL_messageEntityTextUrl();
        r0.offset = r4.offset;
        r0.length = r4.length;
        r0.url = "tg://emoji?id=" + r4.document_id;
        return r0;
    }
}
