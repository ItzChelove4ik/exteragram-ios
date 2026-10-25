package com.exteragram.messenger.utils.chats;

import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public enum SwipeAction extends Enum<SwipeAction> {
    private static final /* synthetic */ SwipeAction[] $VALUES = null;
    public static final SwipeAction COPY = null;
    public static final SwipeAction DELETE = null;
    public static final SwipeAction EDIT = null;
    public static final SwipeAction FORWARD = null;
    public static final SwipeAction REACTION = null;
    public static final SwipeAction REPEAT = null;
    public static final SwipeAction REPLY = null;
    public static final SwipeAction SAVE = null;
    public static final SwipeAction TRANSLATE = null;
    public final int actionId;
    public final int iconRes;
    public final float iconTrim;
    public final int titleRes;

    private static /* synthetic */ SwipeAction[] $values() {
        return new SwipeAction[]{REACTION, REPLY, COPY, FORWARD, EDIT, SAVE, REPEAT, DELETE, TRANSLATE};
    }

    static {
        REACTION = new SwipeAction("REACTION", 0, 1, R.drawable.msg_reactions2, R.string.DoubleTapSetting, 1.0f);
        REPLY = new SwipeAction("REPLY", 1, 2, R.drawable.menu_reply, R.string.Reply, 1.09f);
        COPY = new SwipeAction("COPY", 2, 3, R.drawable.msg_copy, R.string.Copy, 1.0f);
        FORWARD = new SwipeAction("FORWARD", 3, 4, R.drawable.msg_forward, R.string.Forward, 1.09f);
        EDIT = new SwipeAction("EDIT", 4, 5, R.drawable.msg_edit, R.string.Edit, 1.0f);
        SAVE = new SwipeAction("SAVE", 5, 6, R.drawable.msg_saved, R.string.Save, 1.0f);
        REPEAT = new SwipeAction("REPEAT", 6, 7, R.drawable.msg_repeat, R.string.Repeat, 1.09f);
        DELETE = new SwipeAction("DELETE", 7, 8, R.drawable.msg_delete, R.string.Delete, 1.09f);
        TRANSLATE = new SwipeAction("TRANSLATE", 8, 9, R.drawable.msg_translate, R.string.TranslateMessage, 1.09f);
        $VALUES = $values();
    }

    SwipeAction(String r1, int r2, int r3, int r4, int r5, float r6) {
        this.actionId = r3;
        this.iconRes = r4;
        this.titleRes = r5;
        this.iconTrim = r6;
    }

    public static List<SwipeAction> disabled() {
        ArrayList r0 = new ArrayList(Arrays.asList(values()));
        r0.removeAll(enabled());
        return r0;
    }

    public static List<SwipeAction> enabled() {
        ArrayList r0 = new ArrayList();
        String[] r1 = ExteraConfig.getSwipeActions().split(",");
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L11;
        SwipeAction r4 = of(Integer.parseInt(r1[r3].trim()));     // Catch: NumberFormatException -> L12
        if (r4 == null) goto L10;
        if (r0.contains(r4) == true) goto L10;
        r0.add(r4);
    L10:
        r3 = r3 + 1;
        goto L3
    L11:
        return r0;
    }

    public static SwipeAction of(int r5) {
        SwipeAction[] r0 = values();
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L8;
        SwipeAction r3 = r0[r2];
        if (r3.actionId == r5) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return r3;
    L8:
        return null;
    }

    public static String quickReactionEmoticon(int r3) {
        MediaDataController r32 = MediaDataController.getInstance(r3);
        String r0 = r32.getDoubleTapReaction();
        if (r0 != null) goto L6;
        return null;
    L6:
        if (r0.startsWith("animated_") == false) goto L8;
        return r0;
    L8:
        TLRPC.TL_availableReaction r33 = r32.getReactionsMap().get(r0);
        if (r33 != null) goto L11;
        return null;
    L11:
        return r33.reaction;
    }

    public static void setEnabled(List<SwipeAction> r3) {
        StringBuilder r0 = new StringBuilder();
        Iterator<SwipeAction> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L9;
        SwipeAction r1 = r32.next();
        if (r0.length() <= 0) goto L8;
        r0.append(',');
    L8:
        r0.append(r1.actionId);
        goto L4
    L9:
        ExteraConfig.setSwipeActions(r0.toString());
    }

    public static SwipeAction valueOf(String r1) {
        return (SwipeAction) Enum.valueOf(SwipeAction.class, r1);
    }

    public static SwipeAction[] values() {
        return (SwipeAction[]) $VALUES.clone();
    }

    public boolean isEnabled() {
        return enabled().contains(this);
    }

    public void setEnabled(boolean r6) {
        List<SwipeAction> r0 = enabled();
        if (r6 != r0.contains(this)) goto L5;
        return;
    L5:
        if (r6 == false) goto L16;
        SwipeAction[] r62 = values();
        int r1 = r62.length;
        int r2 = 0;
        int r3 = 0;
    L7:
        if (r2 >= r1) goto L15;
        SwipeAction r4 = r62[r2];
        if (r4 == this) goto L15;
        if (r0.contains(r4) == false) goto L14;
        r3 = r3 + 1;
    L14:
        r2 = r2 + 1;
    L15:
        r0.add(r3, this);
    L17:
        setEnabled(r0);
        return;
    L16:
        r0.remove(this);
        goto L17
    }
}
