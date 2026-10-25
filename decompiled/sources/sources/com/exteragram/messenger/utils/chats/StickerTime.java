package com.exteragram.messenger.utils.chats;

import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.StickerTimeMode;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessageObject;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes4.dex */
public abstract class StickerTime {

    public static final class Row {
        public float anchorY;
        public float cellWidth;
        public boolean hasName;
        public MessageObject message;
        public float nameHeight;
        public float nameLeft;
        public float nameTop;
        public float nameWidth;
        public float overStickerOffsetX;
        public boolean sideButton;
        public float stickerLeft;
        public float stickerRight;
        public float stickerWidth;
        public float timeWidth;

        public Row() {
        }
    }

    public static float getSideButtonTouchLeft(Row r1, float r2) {
        if (isBeside(r1) == true) goto L5;
    L8:
        return r2;
    L5:
        if (r1.message.isOutOwner() == true) goto L8;
        return AndroidUtilities.dp(8.0f);
    }

    public static float getSideButtonTouchRight(Row r1, float r2) {
        if (isBeside(r1) == true) goto L5;
    L8:
        return r2;
    L5:
        if (r1.message.isOutOwner() == false) goto L8;
        return AndroidUtilities.dp(8.0f) + AndroidUtilities.dp(32.0f);
    }

    public static float getSideButtonX(Row r3, float r4) {
        if (isBeside(r3) == true) goto L5;
        return r4;
    L5:
        float r1 = AndroidUtilities.dp(8.0f) + AndroidUtilities.dp(32.0f);
        if (r3.message.isOutOwner() == false) goto L10;
        return Math.min(r4, getTimeLeft(r3) - r1);
    L10:
        return Math.max(r4, (getTimeLeft(r3) + getTimeWidth(r3)) + AndroidUtilities.dp(8.0f));
    }

    private static float getTimeLeft(Row r3) {
        float r0 = AndroidUtilities.dp(8.0f);
        if (r3.hasName == false) goto L7;
        float r1 = r3.nameTop;
        if (overlapsRow(r3, r1, r3.nameHeight + r1) == false) goto L7;
        boolean r12 = true;
    L9:
        if (r3.message.isOutOwner() == false) goto L15;
        float r2 = r3.stickerLeft - r0;
        if (r12 == false) goto L14;
        r2 = Math.min(r2, r3.nameLeft - r0);
    L14:
        return r2 - getTimeWidth(r3);
    L15:
        float r22 = r3.stickerRight + r0;
        if (r12 == true) goto L18;
        return r22;
    L18:
        return Math.max(r22, (r3.nameLeft + r3.nameWidth) + r0);
    L7:
        r12 = false;
        goto L9
    }

    public static float getTimeOffsetX(Row r1, float r2) {
        MessageObject r0 = r1.message;
        if (r0 != null) goto L5;
        return 0.0f;
    L5:
        if (r0.isAnyKindOfSticker() == true) goto L8;
        return 0.0f;
    L8:
        if (isBeside(r1) == true) goto L12;
        return r1.overStickerOffsetX;
    L12:
        return (getTimeLeft(r1) + AndroidUtilities.dp(6.0f)) - r2;
    }

    private static float getTimeWidth(Row r2) {
        float r0 = r2.timeWidth + AndroidUtilities.dp(12.0f);
        if (r2.message.isOutOwner() == false) goto L5;
        int r22 = AndroidUtilities.dp(20.0f);
    L7:
        return r0 + r22;
    L5:
        r22 = 0;
        goto L7
    }

    private static boolean isBeside(Row r6) {
        MessageObject r0 = r6.message;
        if (r0 != null) goto L5;
    L25:
        return false;
    L5:
        if (r0.isAnyKindOfSticker() == false) goto L25;
        if (ExteraConfig.getStickerTimeMode() != StickerTimeMode.SIDE) goto L25;
        if (r0.type == 19) goto L25;
        if (r6.stickerWidth <= 0.0f) goto L25;
        float r2 = AndroidUtilities.dp(8.0f);
        float r3 = getTimeLeft(r6);
        float r4 = getTimeWidth(r6) + r3;
        if (r6.sideButton == false) goto L20;
        if (r0.isOutOwner() == false) goto L18;
        r3 = r3 - (AndroidUtilities.dp(32.0f) + r2);
        goto L20
    L18:
        r4 = r4 + (AndroidUtilities.dp(32.0f) + r2);
    L20:
        if (r3 < r2) goto L25;
        if (r4 > (r6.cellWidth - r2)) goto L25;
        return true;
    }

    public static boolean isHidden(MessageObject r2) {
        if (ExteraConfig.getStickerTimeMode() != StickerTimeMode.HIDDEN) goto L9;
        if (r2 != null) goto L6;
        return false;
    L6:
        if (r2.isAnyKindOfSticker() == false) goto L12;
        return true;
    L12:
        return false;
    L9:
        return false;
    }

    private static boolean overlapsRow(Row r3, float r4, float r5) {
        float r32 = r3.anchorY - AndroidUtilities.dp(23.0f);
        if (r4 < (Math.max(AndroidUtilities.dp(17.0f), Theme.chat_timePaint.getTextSize() + AndroidUtilities.dp(5.0f)) + r32)) goto L5;
        return false;
    L5:
        if (r5 <= r32) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean shouldPreserveOnPreview(MessageObject r1, MessageObject r2) {
        if (r1 == null) goto L14;
        if (r2 != null) goto L5;
        return false;
    L5:
        if (r1.getId() == r2.getId()) goto L7;
        return false;
    L7:
        if (r2.preview == true) goto L9;
        return false;
    L9:
        if (r2.isAnyKindOfSticker() == true) goto L11;
        return false;
    L11:
        if (ExteraConfig.getStickerTimeMode() == StickerTimeMode.HIDDEN) goto L20;
        return true;
    L20:
        return false;
    L14:
        return false;
    }
}
