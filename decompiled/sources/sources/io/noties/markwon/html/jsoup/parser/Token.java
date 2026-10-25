package io.noties.markwon.html.jsoup.parser;

import io.noties.markwon.html.jsoup.helper.Normalizer;
import io.noties.markwon.html.jsoup.helper.Validate;
import io.noties.markwon.html.jsoup.nodes.Attributes;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public abstract class Token {
    public final TokenType type;

    public static final class CData extends Character {
        public CData(String r1) {
            data(r1);
        }

        @Override // io.noties.markwon.html.jsoup.parser.Token.Character
        public String toString() {
            return "<![CDATA[" + getData() + "]]>";
        }
    }

    public static class Character extends Token {
        private String data;

        public Character() {
            super(TokenType.Character);
        }

        public Character data(String r1) {
            this.data = r1;
            return this;
        }

        public String getData() {
            return this.data;
        }

        @Override // io.noties.markwon.html.jsoup.parser.Token
        public Token reset() {
            this.data = null;
            return this;
        }

        public String toString() {
            return getData();
        }
    }

    public static final class Comment extends Token {
        boolean bogus;
        final StringBuilder data;

        public Comment() {
            super(TokenType.Comment);
            this.data = new StringBuilder();
            this.bogus = false;
        }

        public String getData() {
            return this.data.toString();
        }

        @Override // io.noties.markwon.html.jsoup.parser.Token
        public Token reset() {
            Token.reset(this.data);
            this.bogus = false;
            return this;
        }

        public String toString() {
            return "<!--" + getData() + "-->";
        }
    }

    public static final class Doctype extends Token {
        boolean forceQuirks;
        final StringBuilder name;
        String pubSysKey;
        final StringBuilder publicIdentifier;
        final StringBuilder systemIdentifier;

        public Doctype() {
            super(TokenType.Doctype);
            this.name = new StringBuilder();
            this.pubSysKey = null;
            this.publicIdentifier = new StringBuilder();
            this.systemIdentifier = new StringBuilder();
            this.forceQuirks = false;
        }

        @Override // io.noties.markwon.html.jsoup.parser.Token
        public Token reset() {
            Token.reset(this.name);
            this.pubSysKey = null;
            Token.reset(this.publicIdentifier);
            Token.reset(this.systemIdentifier);
            this.forceQuirks = false;
            return this;
        }
    }

    public static final class EOF extends Token {
        public EOF() {
            super(TokenType.EOF);
        }

        @Override // io.noties.markwon.html.jsoup.parser.Token
        public Token reset() {
            return this;
        }
    }

    public static final class EndTag extends Tag {
        public EndTag() {
            super(TokenType.EndTag);
        }

        public String toString() {
            return "</" + name() + ">";
        }
    }

    public static final class StartTag extends Tag {
        public StartTag() {
            super(TokenType.StartTag);
            this.attributes = new Attributes();
        }

        @Override // io.noties.markwon.html.jsoup.parser.Token.Tag, io.noties.markwon.html.jsoup.parser.Token
        public Tag reset() {
            super.reset();
            this.attributes = new Attributes();
            return this;
        }

        @Override // io.noties.markwon.html.jsoup.parser.Token.Tag, io.noties.markwon.html.jsoup.parser.Token
        public /* bridge */ /* synthetic */ Token reset() {
            return reset();
        }

        public String toString() {
            Attributes r0 = this.attributes;
            if (r0 == null) goto L9;
            if (r0.size() <= 0) goto L9;
            return "<" + name() + " " + this.attributes.toString() + ">";
        L9:
            return "<" + name() + ">";
        }
    }

    public static abstract class Tag extends Token {
        public Attributes attributes;
        private boolean hasEmptyAttributeValue;
        private boolean hasPendingAttributeValue;
        public String normalName;
        private String pendingAttributeName;
        private StringBuilder pendingAttributeValue;
        private String pendingAttributeValueS;
        public boolean selfClosing;
        public String tagName;

        public Tag(TokenType r1) {
            super(r1);
            this.pendingAttributeValue = new StringBuilder();
            this.hasEmptyAttributeValue = false;
            this.hasPendingAttributeValue = false;
            this.selfClosing = false;
        }

        private void ensureAttributeValue() {
            this.hasPendingAttributeValue = true;
            String r0 = this.pendingAttributeValueS;
            if (r0 == null) goto L6;
            this.pendingAttributeValue.append(r0);
            this.pendingAttributeValueS = null;
            return;
        }

        public final void appendAttributeName(char r1) {
            appendAttributeName(String.valueOf(r1));
        }

        public final void appendAttributeName(String r2) {
            String r0 = this.pendingAttributeName;
            if (r0 == null) goto L6;
            r2 = r0.concat(r2);
        L6:
            this.pendingAttributeName = r2;
        }

        public final void appendAttributeValue(char r1) {
            ensureAttributeValue();
            this.pendingAttributeValue.append(r1);
        }

        public final void appendAttributeValue(String r2) {
            ensureAttributeValue();
            if (this.pendingAttributeValue.length() != 0) goto L6;
            this.pendingAttributeValueS = r2;
            return;
        L6:
            this.pendingAttributeValue.append(r2);
        }

        public final void appendAttributeValue(int[] r5) {
            ensureAttributeValue();
            int r0 = r5.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            int r2 = r5[r1];
            this.pendingAttributeValue.appendCodePoint(r2);
            r1 = r1 + 1;
            goto L3
        }

        public final void appendTagName(char r1) {
            appendTagName(String.valueOf(r1));
        }

        public final void appendTagName(String r2) {
            String r0 = this.tagName;
            if (r0 == null) goto L6;
            r2 = r0.concat(r2);
        L6:
            this.tagName = r2;
            this.normalName = Normalizer.lowerCase(r2);
        }

        public final void finaliseTag() {
            if (this.pendingAttributeName == null) goto L6;
            newAttribute();
            return;
        }

        public final Tag name(String r1) {
            this.tagName = r1;
            this.normalName = Normalizer.lowerCase(r1);
            return this;
        }

        public final String name() {
            String r0 = this.tagName;
            if (r0 != null) goto L5;
        L8:
            boolean r02 = true;
        L9:
            Validate.isFalse(r02);
            return this.tagName;
        L5:
            if (r0.length() == 0) goto L8;
            r02 = false;
            goto L9
        }

        public final void newAttribute() {
            if (this.attributes != null) goto L5;
            this.attributes = new Attributes();
        L5:
            String r0 = this.pendingAttributeName;
            if (r0 == null) goto L20;
            String r02 = r0.trim();
            this.pendingAttributeName = r02;
            if (r02.length() <= 0) goto L20;
            if (this.hasPendingAttributeValue == false) goto L16;
            if (this.pendingAttributeValue.length() <= 0) goto L14;
            String r03 = this.pendingAttributeValue.toString();
        L19:
            this.attributes.put(this.pendingAttributeName, r03);
            goto L20
        L14:
            r03 = this.pendingAttributeValueS;
            goto L19
        L16:
            if (this.hasEmptyAttributeValue == false) goto L18;
            r03 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L19
        L18:
            r03 = null;
        L20:
            this.pendingAttributeName = null;
            this.hasEmptyAttributeValue = false;
            this.hasPendingAttributeValue = false;
            Token.reset(this.pendingAttributeValue);
            this.pendingAttributeValueS = null;
        }

        @Override // io.noties.markwon.html.jsoup.parser.Token
        public Tag reset() {
            this.tagName = null;
            this.normalName = null;
            this.pendingAttributeName = null;
            Token.reset(this.pendingAttributeValue);
            this.pendingAttributeValueS = null;
            this.hasEmptyAttributeValue = false;
            this.hasPendingAttributeValue = false;
            this.selfClosing = false;
            this.attributes = null;
            return this;
        }

        @Override // io.noties.markwon.html.jsoup.parser.Token
        public /* bridge */ /* synthetic */ Token reset() {
            return reset();
        }

        public final void setEmptyAttributeValue() {
            this.hasEmptyAttributeValue = true;
        }
    }

    public enum TokenType extends Enum<TokenType> {
        private static final /* synthetic */ TokenType[] $VALUES = null;
        public static final TokenType Character = null;
        public static final TokenType Comment = null;
        public static final TokenType Doctype = null;
        public static final TokenType EOF = null;
        public static final TokenType EndTag = null;
        public static final TokenType StartTag = null;

        static {
            TokenType r0 = new TokenType("Doctype", 0);
            Doctype = r0;
            TokenType r1 = new TokenType("StartTag", 1);
            StartTag = r1;
            TokenType r2 = new TokenType("EndTag", 2);
            EndTag = r2;
            TokenType r3 = new TokenType("Comment", 3);
            Comment = r3;
            TokenType r4 = new TokenType("Character", 4);
            Character = r4;
            TokenType r5 = new TokenType("EOF", 5);
            EOF = r5;
            $VALUES = new TokenType[]{r0, r1, r2, r3, r4, r5};
        }

        TokenType(String r1, int r2) {
        }

        public static TokenType valueOf(String r1) {
            return (TokenType) Enum.valueOf(TokenType.class, r1);
        }

        public static TokenType[] values() {
            return (TokenType[]) $VALUES.clone();
        }
    }

    public Token(TokenType r1) {
        this.type = r1;
    }

    public static void reset(StringBuilder r2) {
        if (r2 == null) goto L5;
        r2.delete(0, r2.length());
        return;
    }

    public abstract Token reset();
}
