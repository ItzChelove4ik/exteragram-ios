package org.telegram.ui.Components;

import android.content.SharedPreferences;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public abstract class HintsController {

    /* loaded from: classes3.dex */
    public enum Hint extends Enum<Hint> {
        private static final /* synthetic */ Hint[] $VALUES = null;
        public static final Hint AccountSwitchHint = null;
        public static final Hint ChannelGiftHint = null;
        public static final Hint ChannelSuggestHint = null;
        public static final Hint GiftMessageHint = null;
        public static final Hint GroupEmojiPackHintShown = null;
        public static final Hint GuestBotPrivacy = null;
        public static final Hint PlaybackSpeedHint = null;
        public static final Hint RoundHint2 = null;
        public static final Hint RoundHintChannel2 = null;
        private final String name;
        private final float probability;
        private final int showsLimit;

        private static /* synthetic */ Hint[] $values() {
            return new Hint[]{RoundHint2, RoundHintChannel2, ChannelSuggestHint, ChannelGiftHint, GroupEmojiPackHintShown, AccountSwitchHint, GiftMessageHint, PlaybackSpeedHint, GuestBotPrivacy};
        }

        /* renamed from: -$$Nest$fgetname, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m10912$$Nest$fgetname(Hint r0) {
            return r0.name;
        }

        static {
            RoundHint2 = new Hint("RoundHint2", 0, "needShowRoundHint2", 3, 0.2f);
            RoundHintChannel2 = new Hint("RoundHintChannel2", 1, "needShowRoundHintChannel2", 3, 0.2f);
            ChannelSuggestHint = new Hint("ChannelSuggestHint", 2, "channelsuggesthint", 3, 0.2f);
            ChannelGiftHint = new Hint("ChannelGiftHint", 3, "channelgifthint", 3, 0.2f);
            GroupEmojiPackHintShown = new Hint("GroupEmojiPackHintShown", 4, "groupEmojiPackShownHint", 1, 1.0f);
            AccountSwitchHint = new Hint("AccountSwitchHint", 5, "accountswitchhint", 3, 1.0f);
            GiftMessageHint = new Hint("GiftMessageHint", 6, "giftMessaheHint", 3, 1.0f);
            PlaybackSpeedHint = new Hint("PlaybackSpeedHint", 7, "playbackspeedhint", 3, 0.2f);
            GuestBotPrivacy = new Hint("GuestBotPrivacy", 8, 3, 1.0f);
            $VALUES = $values();
        }

        Hint(String r1, int r2, int r3, float r4) {
            this.name = "hints_controller_" + this;
            this.showsLimit = r3;
            this.probability = r4;
        }

        Hint(String r1, int r2, String r3, int r4, float r5) {
            this.name = r3;
            this.showsLimit = r4;
            this.probability = r5;
        }

        public static Hint valueOf(String r1) {
            return (Hint) Enum.valueOf(Hint.class, r1);
        }

        public static Hint[] values() {
            return (Hint[]) $VALUES.clone();
        }

        public void doNotShowAgain() {
            MessagesController.getGlobalMainSettings().edit().putInt(this.name, this.showsLimit).apply();
        }

        public void increment() {
            int r0 = MessagesController.getGlobalMainSettings().getInt(this.name, 0) + 1;
            MessagesController.getGlobalMainSettings().edit().putInt(this.name, r0).apply();
        }

        public boolean show() {
            if (MessagesController.getGlobalMainSettings().getInt(this.name, 0) >= this.showsLimit) goto L13;
            float r0 = this.probability;
            if (r0 < 1.0f) goto L8;
            return true;
        L8:
            if (r0 > 0.0f) goto L11;
            return false;
        L11:
            if (Utilities.fastRandom.nextFloat() >= this.probability) goto L13;
            return true;
        L13:
            return false;
        }
    }

    public static void resetAll() {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        Hint[] r1 = Hint.values();
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L5;
        r0.remove(Hint.m10912$$Nest$fgetname(r1[r3]));
        r3 = r3 + 1;
        goto L3
    L5:
        r0.apply();
    }
}
