package com.exteragram.messenger.export.api;

import com.android.dx.cf.attrib.AttCode;

/* loaded from: classes4.dex */
public class ApiWrap$TextPart {
    public String additional;
    public String text;
    public Type type;

    public enum Type extends Enum<Type> {
        private static final /* synthetic */ Type[] $VALUES = null;
        public static final Type BankCard = null;
        public static final Type Blockquote = null;
        public static final Type Bold = null;
        public static final Type BotCommand = null;
        public static final Type Cashtag = null;
        public static final Type Code = null;
        public static final Type CustomEmoji = null;
        public static final Type Email = null;
        public static final Type Hashtag = null;
        public static final Type Italic = null;
        public static final Type Mention = null;
        public static final Type MentionName = null;
        public static final Type Phone = null;
        public static final Type Pre = null;
        public static final Type Spoiler = null;
        public static final Type Strike = null;
        public static final Type Text = null;
        public static final Type TextUrl = null;
        public static final Type Underline = null;
        public static final Type Unknown = null;
        public static final Type Url = null;

        private static /* synthetic */ Type[] $values() {
            return new Type[]{Text, Unknown, Mention, Hashtag, BotCommand, Url, Email, Bold, Italic, Code, Pre, TextUrl, MentionName, Phone, Cashtag, Underline, Strike, Blockquote, BankCard, Spoiler, CustomEmoji};
        }

        static {
            Text = new Type("Text", 0);
            Unknown = new Type("Unknown", 1);
            Mention = new Type("Mention", 2);
            Hashtag = new Type("Hashtag", 3);
            BotCommand = new Type("BotCommand", 4);
            Url = new Type("Url", 5);
            Email = new Type("Email", 6);
            Bold = new Type("Bold", 7);
            Italic = new Type("Italic", 8);
            Code = new Type(AttCode.ATTRIBUTE_NAME, 9);
            Pre = new Type("Pre", 10);
            TextUrl = new Type("TextUrl", 11);
            MentionName = new Type("MentionName", 12);
            Phone = new Type("Phone", 13);
            Cashtag = new Type("Cashtag", 14);
            Underline = new Type("Underline", 15);
            Strike = new Type("Strike", 16);
            Blockquote = new Type("Blockquote", 17);
            BankCard = new Type("BankCard", 18);
            Spoiler = new Type("Spoiler", 19);
            CustomEmoji = new Type("CustomEmoji", 20);
            $VALUES = $values();
        }

        Type(String r1, int r2) {
        }

        public static Type valueOf(String r1) {
            return (Type) Enum.valueOf(Type.class, r1);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }

    public ApiWrap$TextPart() {
        this.type = Type.Text;
    }

    public static String UnavailableEmoji() {
        return "(unavailable)";
    }
}
