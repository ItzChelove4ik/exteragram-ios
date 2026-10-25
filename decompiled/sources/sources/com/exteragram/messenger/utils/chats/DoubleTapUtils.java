package com.exteragram.messenger.utils.chats;

import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;

/* loaded from: classes4.dex */
public abstract class DoubleTapUtils {
    public static int getActionId(int r9, boolean r10) {
        if (r9 != 9) goto L5;
        int r92 = 9;
    L7:
        if (r10 == true) goto L18;
        switch(r92) {
            case 1: goto L17;
            case 2: goto L16;
            case 3: goto L15;
            case 4: goto L14;
            case 5: goto L13;
            case 6: goto L12;
            case 7: goto L11;
            case 8: goto L10;
            default: goto L9;
        };
    L9:
        return 0;
    L10:
        return 9;
    L11:
        return 8;
    L12:
        return 7;
    L13:
        return 6;
    L14:
        return 4;
    L15:
        return 3;
    L16:
        return 2;
    L17:
        return 1;
    L18:
        switch(r92) {
            case 1: goto L29;
            case 2: goto L28;
            case 3: goto L27;
            case 4: goto L26;
            case 5: goto L24;
            case 6: goto L23;
            case 7: goto L22;
            case 8: goto L21;
            case 9: goto L20;
            default: goto L19;
        };
    L19:
        return 0;
    L20:
        return 9;
    L21:
        return 8;
    L22:
        return 7;
    L23:
        return 6;
    L24:
        return 5;
    L26:
        return 4;
    L27:
        return 3;
    L28:
        return 2;
    L29:
        return 1;
    L5:
        r92 = sanitizeSetting(r9);
        goto L7
    }

    public static int getDoubleTapActionIcon(int r1, boolean r2) {
        return getDoubleTapIcons(r2)[Math.min(Math.max(r1, 0), r2.length - 1)];
    }

    public static CharSequence getDoubleTapActionLabel(int r1, boolean r2) {
        return getDoubleTapActions(r2)[Math.min(Math.max(r1, 0), r2.length - 1)];
    }

    public static CharSequence[] getDoubleTapActions(boolean r21) {
        if (r21 == true) goto L7;
        return new CharSequence[]{LocaleController.getString(R.string.Disable), LocaleController.getString(R.string.Reactions), LocaleController.getString(R.string.Reply), LocaleController.getString(R.string.Copy), LocaleController.getString(R.string.Forward), LocaleController.getString(R.string.Save), LocaleController.getString(R.string.Repeat), LocaleController.getString(R.string.Delete), LocaleController.getString(R.string.TranslateMessage)};
    L7:
        return new CharSequence[]{LocaleController.getString(R.string.Disable), LocaleController.getString(R.string.Reactions), LocaleController.getString(R.string.Reply), LocaleController.getString(R.string.Copy), LocaleController.getString(R.string.Forward), LocaleController.getString(R.string.Edit), LocaleController.getString(R.string.Save), LocaleController.getString(R.string.Repeat), LocaleController.getString(R.string.Delete), LocaleController.getString(R.string.TranslateMessage)};
    }

    public static int[] getDoubleTapIcons(boolean r10) {
        if (r10 == true) goto L6;
        return new int[]{R.drawable.msg_block, R.drawable.msg_reactions2, R.drawable.menu_reply, R.drawable.msg_copy, R.drawable.msg_forward, R.drawable.msg_saved, R.drawable.msg_repeat, R.drawable.msg_delete, R.drawable.msg_translate};
    L6:
        return new int[]{R.drawable.msg_block, R.drawable.msg_reactions2, R.drawable.menu_reply, R.drawable.msg_copy, R.drawable.msg_forward, R.drawable.msg_edit, R.drawable.msg_saved, R.drawable.msg_repeat, R.drawable.msg_delete, R.drawable.msg_translate};
    }

    public static int sanitizeSetting(int r1) {
        if (r1 >= 0) goto L6;
        return 0;
    L6:
        return Math.min(r1, 9);
    }
}
