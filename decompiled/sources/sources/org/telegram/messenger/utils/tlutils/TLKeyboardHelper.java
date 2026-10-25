package org.telegram.messenger.utils.tlutils;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_keyboard;

/* loaded from: classes6.dex */
public abstract class TLKeyboardHelper {
    public static <T extends TL_keyboard.ButtonTypeProto> T getType(TL_keyboard.KeyboardButtonProto r2, Class<T> r3) {
        if (r2 == null) goto L10;
        if (r3 == null) goto L10;
        TL_keyboard.ButtonTypeProto r22 = r2.getType();
        if (r3.isInstance(r22) == false) goto L10;
        return r3.cast(r22);
    L10:
        return null;
    }

    public static boolean isButtonWebView(TL_keyboard.KeyboardButtonProto r1) {
        if (isType(r1, TL_keyboard.TL_inlineButtonTypeWebView.class) == false) goto L5;
        return true;
    L5:
        if (isType(r1, TL_keyboard.TL_buttonTypeSimpleWebView.class) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isForceReply(TLRPC.ReplyMarkup r2) {
        if (r2 != null) goto L6;
        return false;
    L6:
        if ((r2 instanceof TLRPC.TL_replyKeyboardForceReply) == false) goto L10;
        return true;
    L10:
        if ((r2 instanceof TLRPC.TL_replyInlineMarkup) == false) goto L14;
        return r2.force_reply;
    L14:
        if ((r2 instanceof TLRPC.TL_replyKeyboardMarkup) == true) goto L16;
        return false;
    L16:
        return r2.force_reply;
    }

    public static <T extends TL_keyboard.ButtonTypeProto> boolean isType(TL_keyboard.KeyboardButtonProto r0, Class<T> r1) {
        if (getType(r0, r1) == null) goto L6;
        return true;
    L6:
        return false;
    }
}
