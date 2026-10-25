package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_keyboard;

/* loaded from: classes5.dex */
public class BotInlineKeyboard {

    public enum BackgroundColor extends Enum<BackgroundColor> {
        private static final /* synthetic */ BackgroundColor[] $VALUES = null;
        public static final BackgroundColor DANGER = null;
        public static final BackgroundColor NONE = null;
        public static final BackgroundColor PRIMARY = null;
        public static final BackgroundColor SUCCESS = null;

        private static /* synthetic */ BackgroundColor[] $values() {
            return new BackgroundColor[]{NONE, PRIMARY, SUCCESS, DANGER};
        }

        static {
            NONE = new BackgroundColor("NONE", 0);
            PRIMARY = new BackgroundColor("PRIMARY", 1);
            SUCCESS = new BackgroundColor("SUCCESS", 2);
            DANGER = new BackgroundColor("DANGER", 3);
            $VALUES = $values();
        }

        BackgroundColor(String r1, int r2) {
        }

        public static BackgroundColor valueOf(String r1) {
            return (BackgroundColor) Enum.valueOf(BackgroundColor.class, r1);
        }

        public static BackgroundColor[] values() {
            return (BackgroundColor[]) $VALUES.clone();
        }
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private final ArrayList<Button[]> buttons;
        private int separators;

        public Builder() {
            this.buttons = new ArrayList();
        }

        public void addBotKeyboard(TLRPC.TL_replyInlineMarkup r8) {
            int r1 = 0;
        L4:
            if (r1 >= r8.rows.size()) goto L10;
            ArrayList<TL_keyboard.KeyboardInlineButton> r2 = r8.rows.get(r1).buttons;
            ButtonBot[] r3 = new ButtonBot[r2.size()];
            int r4 = 0;
        L7:
            if (r4 >= r2.size()) goto L9;
            r3[r4] = new ButtonBot(r2.get(r4));
            r4 = r4 + 1;
            goto L7
        L9:
            this.buttons.add(r3);
            r1 = r1 + 1;
            goto L4
        }

        public void addContinueThreadKeyboard() {
            this.buttons.add(new Button[]{new ButtonCustom(4, R.string.BotForumContinueChat, 0)});
        }

        public void addGiftOfferKeyboard() {
            this.buttons.add(new Button[]{new ButtonCustom(5, R.string.GiftOfferDecline, R.drawable.filled_bot_decline_24), new ButtonCustom(6, R.string.GiftOfferAccept, R.drawable.filled_bot_approve_24)});
        }

        public void addKeyboardSource(Source r8) {
            if (r8 == null) goto L13;
            int r0 = r8.getRowsCount();
            int r2 = 0;
        L5:
            if (r2 >= r0) goto L18;
            int r3 = r8.getColumnsCount(r2);
            Button[] r4 = new Button[r3];
            int r5 = 0;
        L7:
            if (r5 >= r3) goto L9;
            r4[r5] = r8.getButton(r2, r5);
            r5 = r5 + 1;
            goto L7
        L9:
            this.buttons.add(r4);
            if (r8.hasSeparator(r2) == false) goto L12;
            addSeparator();
        L12:
            r2 = r2 + 1;
            goto L5
        L18:
            return;
        }

        public void addSeparator() {
            if (this.buttons.isEmpty() == true) goto L6;
            this.separators |= 1 << (this.buttons.size() - 1);
            return;
        }

        public void addSharingOfferKeyboard() {
            this.buttons.add(new Button[]{new ButtonCustom(7, R.string.DisableSharingOfferDecline, R.drawable.filled_bot_decline_24), new ButtonCustom(8, R.string.DisableSharingOfferAccept, R.drawable.filled_bot_approve_24)});
        }

        public void addSuggestionKeyboard() {
            this.buttons.add(new Button[]{new ButtonCustom(1, R.string.PostSuggestionsInlineDecline, R.drawable.filled_bot_decline_24), new ButtonCustom(2, R.string.PostSuggestionsInlineAccept, R.drawable.filled_bot_approve_24)});
            this.buttons.add(new Button[]{new ButtonCustom(3, R.string.PostSuggestionsInlineEdit, R.drawable.filled_bot_suggest_24)});
        }

        public Source build() {
            ArrayList<Button[]> r1 = this.buttons;
            return new KeyboardSourceArray((Button[][]) r1.toArray(new Button[r1.size()][]), this.separators, null);
        }

        public boolean isEmpty() {
            return this.buttons.isEmpty();
        }

        public boolean isNotEmpty() {
            return !this.buttons.isEmpty();
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Button {
        public Button() {
        }

        public BackgroundColor getColor() {
            return BackgroundColor.NONE;
        }

        public long getIconEmoji() {
            return 0;
        }

        public int getIconRes() {
            return 0;
        }

        public abstract String getText();
    }

    /* loaded from: classes.dex */
    public static class ButtonBot extends Button {
        public final TL_keyboard.KeyboardInlineButton button;

        public ButtonBot(TL_keyboard.KeyboardInlineButton r1) {
            this.button = r1;
        }

        @Override // org.telegram.messenger.BotInlineKeyboard.Button
        public BackgroundColor getColor() {
            TL_keyboard.KeyboardButtonStyle r1 = this.button.style;
            if (r1 == null) goto L17;
            if (r1.bg_success == false) goto L9;
            return BackgroundColor.SUCCESS;
        L9:
            if (r1.bg_danger == false) goto L13;
            return BackgroundColor.DANGER;
        L13:
            if (r1.bg_primary == false) goto L17;
            return BackgroundColor.PRIMARY;
        L17:
            return BackgroundColor.NONE;
        }

        @Override // org.telegram.messenger.BotInlineKeyboard.Button
        public long getIconEmoji() {
            TL_keyboard.KeyboardButtonStyle r2 = this.button.style;
            if (r2 != null) goto L5;
            return 0;
        L5:
            return r2.icon;
        }

        @Override // org.telegram.messenger.BotInlineKeyboard.Button
        public String getText() {
            return this.button.text;
        }
    }

    public static class ButtonCustom extends Button {
        public static final int GIFT_OFFER_ACCEPT = 6;
        public static final int GIFT_OFFER_DECLINE = 5;
        public static final int OPEN_MESSAGE_THREAD = 4;
        public static final int SHARING_OFFER_ACCEPT = 8;
        public static final int SHARING_OFFER_DECLINE = 7;
        public static final int SUGGESTION_ACCEPT = 2;
        public static final int SUGGESTION_DECLINE = 1;
        public static final int SUGGESTION_EDIT = 3;
        public final int icon;
        public final int id;
        public final int text;

        public ButtonCustom(int r1, int r2, int r3) {
            this.id = r1;
            this.text = r2;
            this.icon = r3;
        }

        @Override // org.telegram.messenger.BotInlineKeyboard.Button
        public int getIconRes() {
            return this.icon;
        }

        @Override // org.telegram.messenger.BotInlineKeyboard.Button
        public String getText() {
            return LocaleController.getString(this.text);
        }
    }

    /* loaded from: classes.dex */
    public static class KeyboardSourceArray implements Source {
        private final Button[][] buttons;
        private final int separators;

        private KeyboardSourceArray(Button[][] r1, int r2) {
            this.buttons = r1;
            this.separators = r2;
        }

        public /* synthetic */ KeyboardSourceArray(Button[][] r1, int r2, BotInlineKeyboardIA r3) {
            this(r1, r2);
        }

        @Override // org.telegram.messenger.BotInlineKeyboard.Source
        public Button getButton(int r1, int r2) {
            return this.buttons[r1][r2];
        }

        @Override // org.telegram.messenger.BotInlineKeyboard.Source
        public int getColumnsCount(int r1) {
            return this.buttons[r1].length;
        }

        @Override // org.telegram.messenger.BotInlineKeyboard.Source
        public int getRowsCount() {
            return this.buttons.length;
        }

        @Override // org.telegram.messenger.BotInlineKeyboard.Source
        public boolean hasSeparator(int r2) {
            if ((this.separators & (1 << r2)) == 0) goto L5;
            return true;
        L5:
            return false;
        }
    }

    /* loaded from: classes.dex */
    public interface Source {
        Button getButton(int r1, int r2);

        int getColumnsCount(int r1);

        int getRowsCount();

        boolean hasSeparator(int r1);

        default boolean isEmpty() {
            if (getRowsCount() != 0) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public BotInlineKeyboard() {
    }
}
