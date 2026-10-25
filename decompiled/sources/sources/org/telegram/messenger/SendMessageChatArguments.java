package org.telegram.messenger;

/* loaded from: classes5.dex */
public class SendMessageChatArguments {
    public static final SendMessageChatArguments EMPTY = null;
    public final String quickReplyShortcut;
    public final int quickReplyShortcutId;
    public final long welcomeMessageChatId;

    public static class Builder {
        private String quickReplyShortcut;
        private int quickReplyShortcutId;
        private long welcomeMessageChatId;

        /* renamed from: -$$Nest$fgetquickReplyShortcut, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m4900$$Nest$fgetquickReplyShortcut(Builder r0) {
            return r0.quickReplyShortcut;
        }

        /* renamed from: -$$Nest$fgetquickReplyShortcutId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m4901$$Nest$fgetquickReplyShortcutId(Builder r0) {
            return r0.quickReplyShortcutId;
        }

        /* renamed from: -$$Nest$fgetwelcomeMessageChatId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m4902$$Nest$fgetwelcomeMessageChatId(Builder r2) {
            return r2.welcomeMessageChatId;
        }

        public Builder() {
        }

        public SendMessageChatArguments build() {
            return new SendMessageChatArguments(this, null);
        }

        public void setQuickReplyShortcut(String r1, int r2) {
            this.quickReplyShortcut = r1;
            this.quickReplyShortcutId = r2;
        }

        public void setWelcomeMessageChatId(long r1) {
            this.welcomeMessageChatId = r1;
        }
    }

    static {
        EMPTY = new Builder().build();
    }

    private SendMessageChatArguments(Builder r3) {
        this.welcomeMessageChatId = Builder.m4902$$Nest$fgetwelcomeMessageChatId(r3);
        this.quickReplyShortcut = Builder.m4900$$Nest$fgetquickReplyShortcut(r3);
        this.quickReplyShortcutId = Builder.m4901$$Nest$fgetquickReplyShortcutId(r3);
    }

    public /* synthetic */ SendMessageChatArguments(Builder r1, SendMessageChatArgumentsIA r2) {
        this(r1);
    }
}
