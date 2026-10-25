package org.telegram.ui.ActionBar.theme;

import android.text.TextUtils;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes3.dex */
public class ThemeKey {
    public final String emoticon;
    public final String giftSlug;

    private ThemeKey(String r1, String r2) {
        this.emoticon = r1;
        this.giftSlug = r2;
    }

    public static boolean equals(ThemeKey r0, ThemeKey r1) {
        if (r0 != r1) goto L5;
        return true;
    L5:
        if (r0 == null) goto L10;
        if (r1 != null) goto L9;
        return false;
    L9:
        return r0.equals(r1);
    L10:
        return false;
    }

    public static ThemeKey fromSavedString(String r3) {
        if (r3 != null) goto L6;
        return null;
    L6:
        if (r3.startsWith("gift_") == false) goto L10;
        return new ThemeKey(null, r3.substring(5));
    L10:
        if (r3.startsWith("emoticon_") == false) goto L14;
        return new ThemeKey(r3.substring(9), null);
    L14:
        if (TextUtils.isEmpty(r3) == false) goto L16;
        return null;
    L16:
        return new ThemeKey(r3, null);
    }

    public static ThemeKey of(TLRPC.ChatTheme r2) {
        if ((r2 instanceof TLRPC.TL_chatTheme) == false) goto L7;
        return new ThemeKey(((TLRPC.TL_chatTheme) r2).emoticon, null);
    L7:
        if ((r2 instanceof TLRPC.TL_chatThemeUniqueGift) == true) goto L9;
        return null;
    L9:
        return new ThemeKey(null, ((TLRPC.TL_chatThemeUniqueGift) r2).gift.slug);
    }

    public static ThemeKey of(TLRPC.TL_theme r2) {
        return new ThemeKey(r2.emoticon, null);
    }

    public static ThemeKey ofEmoticon(String r2) {
        return new ThemeKey(r2, null);
    }

    public static ThemeKey ofGiftSlug(String r2) {
        return new ThemeKey(null, r2);
    }

    public static TLRPC.InputChatTheme toInputTheme(ThemeKey r1) {
        if (r1 != null) goto L4;
    L7:
        if (r1 == null) goto L13;
        if (TextUtils.isEmpty(r1.giftSlug) == true) goto L13;
        TLRPC.Tl_inputChatThemeUniqueGift r0 = new TLRPC.Tl_inputChatThemeUniqueGift();
        r0.slug = r1.giftSlug;
        return r0;
    L13:
        return new TLRPC.Tl_inputChatThemeEmpty();
    L4:
        if (TextUtils.isEmpty(r1.emoticon) == true) goto L7;
        TLRPC.Tl_inputChatTheme r02 = new TLRPC.Tl_inputChatTheme();
        r02.emoticon = r1.emoticon;
        return r02;
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof ThemeKey) == false) goto L10;
        ThemeKey r42 = (ThemeKey) r4;
        if (TextUtils.equals(this.emoticon, r42.emoticon) == false) goto L10;
        if (TextUtils.equals(this.giftSlug, r42.giftSlug) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public int hashCode() {
        String r0 = this.emoticon;
        int r1 = 0;
        if (r0 == null) goto L5;
        int r02 = r0.hashCode();
    L6:
        String r2 = this.giftSlug;
        if (r2 == null) goto L10;
        r1 = r2.hashCode();
    L10:
        return r02 ^ r1;
    L5:
        r02 = 0;
        goto L6
    }

    public boolean isEmpty() {
        if (TextUtils.isEmpty(this.emoticon) == true) goto L5;
        return false;
    L5:
        if (TextUtils.isEmpty(this.giftSlug) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public String toSavedString() {
        if (this.giftSlug == null) goto L7;
        return "gift_" + this.giftSlug;
    L7:
        if (this.emoticon != null) goto L9;
        return null;
    L9:
        return "emoticon_" + this.emoticon;
    }
}
