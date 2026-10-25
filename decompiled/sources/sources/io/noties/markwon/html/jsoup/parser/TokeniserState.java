package io.noties.markwon.html.jsoup.parser;

import io.noties.markwon.html.jsoup.parser.Token;
import kotlin.text.Typography;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes5.dex */
enum TokeniserState extends Enum<TokeniserState> {
    private static final /* synthetic */ TokeniserState[] $VALUES = null;
    public static final TokeniserState AfterAttributeName = null;
    public static final TokeniserState AfterAttributeValue_quoted = null;
    public static final TokeniserState AfterDoctypeName = null;
    public static final TokeniserState AfterDoctypePublicIdentifier = null;
    public static final TokeniserState AfterDoctypePublicKeyword = null;
    public static final TokeniserState AfterDoctypeSystemIdentifier = null;
    public static final TokeniserState AfterDoctypeSystemKeyword = null;
    public static final TokeniserState AttributeName = null;
    public static final TokeniserState AttributeValue_doubleQuoted = null;
    public static final TokeniserState AttributeValue_singleQuoted = null;
    public static final TokeniserState AttributeValue_unquoted = null;
    public static final TokeniserState BeforeAttributeName = null;
    public static final TokeniserState BeforeAttributeValue = null;
    public static final TokeniserState BeforeDoctypeName = null;
    public static final TokeniserState BeforeDoctypePublicIdentifier = null;
    public static final TokeniserState BeforeDoctypeSystemIdentifier = null;
    public static final TokeniserState BetweenDoctypePublicAndSystemIdentifiers = null;
    public static final TokeniserState BogusComment = null;
    public static final TokeniserState BogusDoctype = null;
    public static final TokeniserState CdataSection = null;
    public static final TokeniserState CharacterReferenceInData = null;
    public static final TokeniserState CharacterReferenceInRcdata = null;
    public static final TokeniserState Comment = null;
    public static final TokeniserState CommentEnd = null;
    public static final TokeniserState CommentEndBang = null;
    public static final TokeniserState CommentEndDash = null;
    public static final TokeniserState CommentStart = null;
    public static final TokeniserState CommentStartDash = null;
    public static final TokeniserState Data = null;
    public static final TokeniserState Doctype = null;
    public static final TokeniserState DoctypeName = null;
    public static final TokeniserState DoctypePublicIdentifier_doubleQuoted = null;
    public static final TokeniserState DoctypePublicIdentifier_singleQuoted = null;
    public static final TokeniserState DoctypeSystemIdentifier_doubleQuoted = null;
    public static final TokeniserState DoctypeSystemIdentifier_singleQuoted = null;
    public static final TokeniserState EndTagOpen = null;
    public static final TokeniserState MarkupDeclarationOpen = null;
    public static final TokeniserState PLAINTEXT = null;
    public static final TokeniserState RCDATAEndTagName = null;
    public static final TokeniserState RCDATAEndTagOpen = null;
    public static final TokeniserState Rawtext = null;
    public static final TokeniserState RawtextEndTagName = null;
    public static final TokeniserState RawtextEndTagOpen = null;
    public static final TokeniserState RawtextLessthanSign = null;
    public static final TokeniserState Rcdata = null;
    public static final TokeniserState RcdataLessthanSign = null;
    public static final TokeniserState ScriptData = null;
    public static final TokeniserState ScriptDataDoubleEscapeEnd = null;
    public static final TokeniserState ScriptDataDoubleEscapeStart = null;
    public static final TokeniserState ScriptDataDoubleEscaped = null;
    public static final TokeniserState ScriptDataDoubleEscapedDash = null;
    public static final TokeniserState ScriptDataDoubleEscapedDashDash = null;
    public static final TokeniserState ScriptDataDoubleEscapedLessthanSign = null;
    public static final TokeniserState ScriptDataEndTagName = null;
    public static final TokeniserState ScriptDataEndTagOpen = null;
    public static final TokeniserState ScriptDataEscapeStart = null;
    public static final TokeniserState ScriptDataEscapeStartDash = null;
    public static final TokeniserState ScriptDataEscaped = null;
    public static final TokeniserState ScriptDataEscapedDash = null;
    public static final TokeniserState ScriptDataEscapedDashDash = null;
    public static final TokeniserState ScriptDataEscapedEndTagName = null;
    public static final TokeniserState ScriptDataEscapedEndTagOpen = null;
    public static final TokeniserState ScriptDataEscapedLessthanSign = null;
    public static final TokeniserState ScriptDataLessthanSign = null;
    public static final TokeniserState SelfClosingStartTag = null;
    public static final TokeniserState TagName = null;
    public static final TokeniserState TagOpen = null;
    static final char[] attributeDoubleValueCharsSorted = null;
    static final char[] attributeNameCharsSorted = null;
    static final char[] attributeSingleValueCharsSorted = null;
    static final char[] attributeValueUnquoted = null;
    private static final String replacementStr = null;

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$1, reason: invalid class name */
    public enum AnonymousClass1 extends TokeniserState {
        public AnonymousClass1(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            char r0 = r3.current();
            if (r0 != 0) goto L5;
            r2.error(this);
            r2.emit(r3.consume());
            return;
        L5:
            if (r0 != '&') goto L7;
            r2.advanceTransition(TokeniserState.CharacterReferenceInData);
            return;
        L7:
            if (r0 != '<') goto L9;
            r2.advanceTransition(TokeniserState.TagOpen);
            return;
        L9:
            if (r0 == 65535) goto L12;
            r2.emit(r3.consumeData());
            return;
        L12:
            r2.emit(new Token.EOF());
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$10, reason: invalid class name */
    public enum AnonymousClass10 extends TokeniserState {
        public AnonymousClass10(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            String r0 = r4.consumeTagName();
            r3.tagPending.appendTagName(r0);
            char r42 = r4.consume();
            if (r42 != 0) goto L5;
            r3.tagPending.appendTagName(TokeniserState.access$300());
            return;
        L5:
            if (r42 != ' ') goto L7;
        L28:
            r3.transition(TokeniserState.BeforeAttributeName);
            return;
        L7:
            if (r42 != '/') goto L9;
            r3.transition(TokeniserState.SelfClosingStartTag);
            return;
        L9:
            if (r42 != '>') goto L11;
            r3.emitTagPending();
            r3.transition(TokeniserState.Data);
            return;
        L11:
            if (r42 != 65535) goto L13;
            r3.eofError(this);
            r3.transition(TokeniserState.Data);
            return;
        L13:
            if (r42 == '\t') goto L28;
            if (r42 == '\n') goto L28;
            if (r42 == '\f') goto L28;
            if (r42 == '\r') goto L28;
            r3.tagPending.appendTagName(r42);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$11, reason: invalid class name */
    public enum AnonymousClass11 extends TokeniserState {
        public AnonymousClass11(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            if (r3.matches('/') == false) goto L7;
            r2.createTempBuffer();
            r2.advanceTransition(TokeniserState.RCDATAEndTagOpen);
            return;
        L7:
            if (r3.matchesLetter() == true) goto L9;
        L14:
            r2.emit("<");
            r2.transition(TokeniserState.Rcdata);
            return;
        L9:
            if (r2.appropriateEndTagName() == null) goto L14;
            if (r3.containsIgnoreCase("</" + r2.appropriateEndTagName()) == true) goto L14;
            r2.tagPending = r2.createTagPending(false).name(r2.appropriateEndTagName());
            r2.emitTagPending();
            r3.unconsume();
            r2.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$12, reason: invalid class name */
    public enum AnonymousClass12 extends TokeniserState {
        public AnonymousClass12(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            if (r3.matchesLetter() == false) goto L6;
            r2.createTagPending(false);
            r2.tagPending.appendTagName(r3.current());
            r2.dataBuffer.append(r3.current());
            r2.advanceTransition(TokeniserState.RCDATAEndTagName);
            return;
        L6:
            r2.emit("</");
            r2.transition(TokeniserState.Rcdata);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$13, reason: invalid class name */
    public enum AnonymousClass13 extends TokeniserState {
        public AnonymousClass13(String r2, int r3) {
            super(r2, r3, null);
        }

        private void anythingElse(Tokeniser r2, CharacterReader r3) {
            r2.emit("</".concat(r2.dataBuffer.toString()));
            r3.unconsume();
            r2.transition(TokeniserState.Rcdata);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            if (r4.matchesLetter() == false) goto L6;
            String r2 = r4.consumeLetterSequence();
            r3.tagPending.appendTagName(r2);
            r3.dataBuffer.append(r2);
            return;
        L6:
            char r0 = r4.consume();
            if (r0 == '\t') goto L35;
            if (r0 == '\n') goto L35;
            if (r0 == '\f') goto L35;
            if (r0 == '\r') goto L35;
            if (r0 == ' ') goto L35;
            if (r0 == '/') goto L29;
            if (r0 == '>') goto L23;
            anythingElse(r3, r4);
            return;
        L23:
            if (r3.isAppropriateEndTagToken() == false) goto L26;
            r3.emitTagPending();
            r3.transition(TokeniserState.Data);
            return;
        L26:
            anythingElse(r3, r4);
            return;
        L29:
            if (r3.isAppropriateEndTagToken() == false) goto L32;
            r3.transition(TokeniserState.SelfClosingStartTag);
            return;
        L32:
            anythingElse(r3, r4);
            return;
        L35:
            if (r3.isAppropriateEndTagToken() == false) goto L38;
            r3.transition(TokeniserState.BeforeAttributeName);
            return;
        L38:
            anythingElse(r3, r4);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$14, reason: invalid class name */
    public enum AnonymousClass14 extends TokeniserState {
        public AnonymousClass14(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r1, CharacterReader r2) {
            if (r2.matches('/') == false) goto L6;
            r1.createTempBuffer();
            r1.advanceTransition(TokeniserState.RawtextEndTagOpen);
            return;
        L6:
            r1.emit(Typography.less);
            r1.transition(TokeniserState.Rawtext);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$15, reason: invalid class name */
    public enum AnonymousClass15 extends TokeniserState {
        public AnonymousClass15(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            TokeniserState.access$400(r2, r3, TokeniserState.RawtextEndTagName, TokeniserState.Rawtext);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$16, reason: invalid class name */
    public enum AnonymousClass16 extends TokeniserState {
        public AnonymousClass16(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r1, CharacterReader r2) {
            TokeniserState.access$500(r1, r2, TokeniserState.Rawtext);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$17, reason: invalid class name */
    public enum AnonymousClass17 extends TokeniserState {
        public AnonymousClass17(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            char r1 = r3.consume();
            if (r1 != '!') goto L5;
            r2.emit("<!");
            r2.transition(TokeniserState.ScriptDataEscapeStart);
            return;
        L5:
            if (r1 == '/') goto L8;
            r2.emit("<");
            r3.unconsume();
            r2.transition(TokeniserState.ScriptData);
            return;
        L8:
            r2.createTempBuffer();
            r2.transition(TokeniserState.ScriptDataEndTagOpen);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$18, reason: invalid class name */
    public enum AnonymousClass18 extends TokeniserState {
        public AnonymousClass18(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            TokeniserState.access$400(r2, r3, TokeniserState.ScriptDataEndTagName, TokeniserState.ScriptData);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$19, reason: invalid class name */
    public enum AnonymousClass19 extends TokeniserState {
        public AnonymousClass19(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r1, CharacterReader r2) {
            TokeniserState.access$500(r1, r2, TokeniserState.ScriptData);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$2, reason: invalid class name */
    public enum AnonymousClass2 extends TokeniserState {
        public AnonymousClass2(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r1, CharacterReader r2) {
            TokeniserState.access$100(r1, TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$20, reason: invalid class name */
    public enum AnonymousClass20 extends TokeniserState {
        public AnonymousClass20(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r1, CharacterReader r2) {
            if (r2.matches(SignatureVisitor.SUPER) == false) goto L6;
            r1.emit(SignatureVisitor.SUPER);
            r1.advanceTransition(TokeniserState.ScriptDataEscapeStartDash);
            return;
        L6:
            r1.transition(TokeniserState.ScriptData);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$21, reason: invalid class name */
    public enum AnonymousClass21 extends TokeniserState {
        public AnonymousClass21(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r1, CharacterReader r2) {
            if (r2.matches(SignatureVisitor.SUPER) == false) goto L6;
            r1.emit(SignatureVisitor.SUPER);
            r1.advanceTransition(TokeniserState.ScriptDataEscapedDashDash);
            return;
        L6:
            r1.transition(TokeniserState.ScriptData);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$22, reason: invalid class name */
    public enum AnonymousClass22 extends TokeniserState {
        public AnonymousClass22(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            if (r3.isEmpty() == false) goto L6;
            r2.eofError(this);
            r2.transition(TokeniserState.Data);
            return;
        L6:
            char r0 = r3.current();
            if (r0 != 0) goto L9;
            r2.error(this);
            r3.advance();
            r2.emit(65533);
            return;
        L9:
            if (r0 != '-') goto L11;
            r2.emit(SignatureVisitor.SUPER);
            r2.advanceTransition(TokeniserState.ScriptDataEscapedDash);
            return;
        L11:
            if (r0 == '<') goto L14;
            r2.emit(r3.consumeToAny(new char[]{SignatureVisitor.SUPER, Typography.less, 0}));
            return;
        L14:
            r2.advanceTransition(TokeniserState.ScriptDataEscapedLessthanSign);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$23, reason: invalid class name */
    public enum AnonymousClass23 extends TokeniserState {
        public AnonymousClass23(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            if (r3.isEmpty() == false) goto L6;
            r2.eofError(this);
            r2.transition(TokeniserState.Data);
            return;
        L6:
            char r32 = r3.consume();
            if (r32 != 0) goto L9;
            r2.error(this);
            r2.emit(65533);
            r2.transition(TokeniserState.ScriptDataEscaped);
            return;
        L9:
            if (r32 != '-') goto L11;
            r2.emit(r32);
            r2.transition(TokeniserState.ScriptDataEscapedDashDash);
            return;
        L11:
            if (r32 == '<') goto L14;
            r2.emit(r32);
            r2.transition(TokeniserState.ScriptDataEscaped);
            return;
        L14:
            r2.transition(TokeniserState.ScriptDataEscapedLessthanSign);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$24, reason: invalid class name */
    public enum AnonymousClass24 extends TokeniserState {
        public AnonymousClass24(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            if (r3.isEmpty() == false) goto L6;
            r2.eofError(this);
            r2.transition(TokeniserState.Data);
            return;
        L6:
            char r32 = r3.consume();
            if (r32 != 0) goto L9;
            r2.error(this);
            r2.emit(65533);
            r2.transition(TokeniserState.ScriptDataEscaped);
            return;
        L9:
            if (r32 != '-') goto L11;
            r2.emit(r32);
            return;
        L11:
            if (r32 != '<') goto L13;
            r2.transition(TokeniserState.ScriptDataEscapedLessthanSign);
            return;
        L13:
            if (r32 == '>') goto L16;
            r2.emit(r32);
            r2.transition(TokeniserState.ScriptDataEscaped);
            return;
        L16:
            r2.emit(r32);
            r2.transition(TokeniserState.ScriptData);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$25, reason: invalid class name */
    public enum AnonymousClass25 extends TokeniserState {
        public AnonymousClass25(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            if (r3.matchesLetter() == false) goto L7;
            r2.createTempBuffer();
            r2.dataBuffer.append(r3.current());
            r2.emit("<" + r3.current());
            r2.advanceTransition(TokeniserState.ScriptDataDoubleEscapeStart);
            return;
        L7:
            if (r3.matches('/') == false) goto L10;
            r2.createTempBuffer();
            r2.advanceTransition(TokeniserState.ScriptDataEscapedEndTagOpen);
            return;
        L10:
            r2.emit(Typography.less);
            r2.transition(TokeniserState.ScriptDataEscaped);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$26, reason: invalid class name */
    public enum AnonymousClass26 extends TokeniserState {
        public AnonymousClass26(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            if (r3.matchesLetter() == false) goto L6;
            r2.createTagPending(false);
            r2.tagPending.appendTagName(r3.current());
            r2.dataBuffer.append(r3.current());
            r2.advanceTransition(TokeniserState.ScriptDataEscapedEndTagName);
            return;
        L6:
            r2.emit("</");
            r2.transition(TokeniserState.ScriptDataEscaped);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$27, reason: invalid class name */
    public enum AnonymousClass27 extends TokeniserState {
        public AnonymousClass27(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r1, CharacterReader r2) {
            TokeniserState.access$500(r1, r2, TokeniserState.ScriptDataEscaped);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$28, reason: invalid class name */
    public enum AnonymousClass28 extends TokeniserState {
        public AnonymousClass28(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            TokeniserState.access$600(r2, r3, TokeniserState.ScriptDataDoubleEscaped, TokeniserState.ScriptDataEscaped);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$29, reason: invalid class name */
    public enum AnonymousClass29 extends TokeniserState {
        public AnonymousClass29(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r0 = r4.current();
            if (r0 != 0) goto L5;
            r3.error(this);
            r4.advance();
            r3.emit(65533);
            return;
        L5:
            if (r0 != '-') goto L7;
            r3.emit(r0);
            r3.advanceTransition(TokeniserState.ScriptDataDoubleEscapedDash);
            return;
        L7:
            if (r0 != '<') goto L9;
            r3.emit(r0);
            r3.advanceTransition(TokeniserState.ScriptDataDoubleEscapedLessthanSign);
            return;
        L9:
            if (r0 == 65535) goto L12;
            r3.emit(r4.consumeToAny(new char[]{SignatureVisitor.SUPER, Typography.less, 0}));
            return;
        L12:
            r3.eofError(this);
            r3.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$3, reason: invalid class name */
    public enum AnonymousClass3 extends TokeniserState {
        public AnonymousClass3(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            char r0 = r3.current();
            if (r0 != 0) goto L5;
            r2.error(this);
            r3.advance();
            r2.emit(65533);
            return;
        L5:
            if (r0 != '&') goto L7;
            r2.advanceTransition(TokeniserState.CharacterReferenceInRcdata);
            return;
        L7:
            if (r0 != '<') goto L9;
            r2.advanceTransition(TokeniserState.RcdataLessthanSign);
            return;
        L9:
            if (r0 == 65535) goto L12;
            r2.emit(r3.consumeToAny(new char[]{Typography.amp, Typography.less, 0}));
            return;
        L12:
            r2.emit(new Token.EOF());
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$30, reason: invalid class name */
    public enum AnonymousClass30 extends TokeniserState {
        public AnonymousClass30(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            char r32 = r3.consume();
            if (r32 != 0) goto L5;
            r2.error(this);
            r2.emit(65533);
            r2.transition(TokeniserState.ScriptDataDoubleEscaped);
            return;
        L5:
            if (r32 != '-') goto L7;
            r2.emit(r32);
            r2.transition(TokeniserState.ScriptDataDoubleEscapedDashDash);
            return;
        L7:
            if (r32 != '<') goto L9;
            r2.emit(r32);
            r2.transition(TokeniserState.ScriptDataDoubleEscapedLessthanSign);
            return;
        L9:
            if (r32 == 65535) goto L12;
            r2.emit(r32);
            r2.transition(TokeniserState.ScriptDataDoubleEscaped);
            return;
        L12:
            r2.eofError(this);
            r2.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$31, reason: invalid class name */
    public enum AnonymousClass31 extends TokeniserState {
        public AnonymousClass31(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            char r32 = r3.consume();
            if (r32 != 0) goto L5;
            r2.error(this);
            r2.emit(65533);
            r2.transition(TokeniserState.ScriptDataDoubleEscaped);
            return;
        L5:
            if (r32 != '-') goto L7;
            r2.emit(r32);
            return;
        L7:
            if (r32 != '<') goto L9;
            r2.emit(r32);
            r2.transition(TokeniserState.ScriptDataDoubleEscapedLessthanSign);
            return;
        L9:
            if (r32 != '>') goto L11;
            r2.emit(r32);
            r2.transition(TokeniserState.ScriptData);
            return;
        L11:
            if (r32 == 65535) goto L14;
            r2.emit(r32);
            r2.transition(TokeniserState.ScriptDataDoubleEscaped);
            return;
        L14:
            r2.eofError(this);
            r2.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$32, reason: invalid class name */
    public enum AnonymousClass32 extends TokeniserState {
        public AnonymousClass32(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r1, CharacterReader r2) {
            if (r2.matches('/') == false) goto L6;
            r1.emit('/');
            r1.createTempBuffer();
            r1.advanceTransition(TokeniserState.ScriptDataDoubleEscapeEnd);
            return;
        L6:
            r1.transition(TokeniserState.ScriptDataDoubleEscaped);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$33, reason: invalid class name */
    public enum AnonymousClass33 extends TokeniserState {
        public AnonymousClass33(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            TokeniserState.access$600(r2, r3, TokeniserState.ScriptDataEscaped, TokeniserState.ScriptDataDoubleEscaped);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$34, reason: invalid class name */
    public enum AnonymousClass34 extends TokeniserState {
        public AnonymousClass34(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r0 = r4.consume();
            if (r0 != 0) goto L5;
            r3.error(this);
            r3.tagPending.newAttribute();
            r4.unconsume();
            r3.transition(TokeniserState.AttributeName);
            return;
        L5:
            if (r0 != ' ') goto L7;
            return;
        L7:
            if (r0 != '\"') goto L9;
        L31:
            r3.error(this);
            r3.tagPending.newAttribute();
            r3.tagPending.appendAttributeName(r0);
            r3.transition(TokeniserState.AttributeName);
            return;
        L9:
            if (r0 == '\'') goto L31;
            if (r0 != '/') goto L13;
            r3.transition(TokeniserState.SelfClosingStartTag);
            return;
        L13:
            if (r0 != 65535) goto L15;
            r3.eofError(this);
            r3.transition(TokeniserState.Data);
            return;
        L15:
            if (r0 != '\t') goto L17;
            return;
        L17:
            if (r0 != '\n') goto L19;
            return;
        L19:
            if (r0 != '\f') goto L21;
            return;
        L21:
            if (r0 == '\r') goto L39;
            switch(r0) {
                case 60: goto L31;
                case 61: goto L31;
                case 62: goto L25;
                default: goto L23;
            };
        L23:
            r3.tagPending.newAttribute();
            r4.unconsume();
            r3.transition(TokeniserState.AttributeName);
            return;
        L25:
            r3.emitTagPending();
            r3.transition(TokeniserState.Data);
            return;
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$35, reason: invalid class name */
    public enum AnonymousClass35 extends TokeniserState {
        public AnonymousClass35(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            String r0 = r4.consumeToAnySorted(TokeniserState.attributeNameCharsSorted);
            r3.tagPending.appendAttributeName(r0);
            char r42 = r4.consume();
            if (r42 != 0) goto L5;
            r3.error(this);
            r3.tagPending.appendAttributeName(65533);
            return;
        L5:
            if (r42 != ' ') goto L7;
        L35:
            r3.transition(TokeniserState.AfterAttributeName);
            return;
        L7:
            if (r42 != '\"') goto L9;
        L33:
            r3.error(this);
            r3.tagPending.appendAttributeName(r42);
            return;
        L9:
            if (r42 == '\'') goto L33;
            if (r42 != '/') goto L13;
            r3.transition(TokeniserState.SelfClosingStartTag);
            return;
        L13:
            if (r42 != 65535) goto L15;
            r3.eofError(this);
            r3.transition(TokeniserState.Data);
            return;
        L15:
            if (r42 == '\t') goto L35;
            if (r42 == '\n') goto L35;
            if (r42 == '\f') goto L35;
            if (r42 == '\r') goto L35;
            switch(r42) {
                case 60: goto L33;
                case 61: goto L27;
                case 62: goto L25;
                default: goto L23;
            };
        L23:
            r3.tagPending.appendAttributeName(r42);
            return;
        L25:
            r3.emitTagPending();
            r3.transition(TokeniserState.Data);
            return;
        L27:
            r3.transition(TokeniserState.BeforeAttributeValue);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$36, reason: invalid class name */
    public enum AnonymousClass36 extends TokeniserState {
        public AnonymousClass36(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r0 = r4.consume();
            if (r0 != 0) goto L5;
            r3.error(this);
            r3.tagPending.appendAttributeName(65533);
            r3.transition(TokeniserState.AttributeName);
            return;
        L5:
            if (r0 != ' ') goto L7;
            return;
        L7:
            if (r0 != '\"') goto L9;
        L33:
            r3.error(this);
            r3.tagPending.newAttribute();
            r3.tagPending.appendAttributeName(r0);
            r3.transition(TokeniserState.AttributeName);
            return;
        L9:
            if (r0 == '\'') goto L33;
            if (r0 != '/') goto L13;
            r3.transition(TokeniserState.SelfClosingStartTag);
            return;
        L13:
            if (r0 != 65535) goto L15;
            r3.eofError(this);
            r3.transition(TokeniserState.Data);
            return;
        L15:
            if (r0 != '\t') goto L17;
            return;
        L17:
            if (r0 != '\n') goto L19;
            return;
        L19:
            if (r0 != '\f') goto L21;
            return;
        L21:
            if (r0 == '\r') goto L41;
            switch(r0) {
                case 60: goto L33;
                case 61: goto L27;
                case 62: goto L25;
                default: goto L23;
            };
        L23:
            r3.tagPending.newAttribute();
            r4.unconsume();
            r3.transition(TokeniserState.AttributeName);
            return;
        L25:
            r3.emitTagPending();
            r3.transition(TokeniserState.Data);
            return;
        L27:
            r3.transition(TokeniserState.BeforeAttributeValue);
            return;
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$37, reason: invalid class name */
    public enum AnonymousClass37 extends TokeniserState {
        public AnonymousClass37(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r0 = r4.consume();
            if (r0 != 0) goto L5;
            r3.error(this);
            r3.tagPending.appendAttributeValue(65533);
            r3.transition(TokeniserState.AttributeValue_unquoted);
            return;
        L5:
            if (r0 != ' ') goto L7;
            return;
        L7:
            if (r0 != '\"') goto L9;
            r3.transition(TokeniserState.AttributeValue_doubleQuoted);
            return;
        L9:
            if (r0 != '`') goto L11;
        L35:
            r3.error(this);
            r3.tagPending.appendAttributeValue(r0);
            r3.transition(TokeniserState.AttributeValue_unquoted);
            return;
        L11:
            if (r0 != 65535) goto L13;
            r3.eofError(this);
            r3.emitTagPending();
            r3.transition(TokeniserState.Data);
            return;
        L13:
            if (r0 != '\t') goto L15;
            return;
        L15:
            if (r0 != '\n') goto L17;
            return;
        L17:
            if (r0 != '\f') goto L19;
            return;
        L19:
            if (r0 != '\r') goto L21;
            return;
        L21:
            if (r0 != '&') goto L23;
            r4.unconsume();
            r3.transition(TokeniserState.AttributeValue_unquoted);
            return;
        L23:
            if (r0 == '\'') goto L29;
            switch(r0) {
                case 60: goto L35;
                case 61: goto L35;
                case 62: goto L27;
                default: goto L25;
            };
        L25:
            r4.unconsume();
            r3.transition(TokeniserState.AttributeValue_unquoted);
            return;
        L27:
            r3.error(this);
            r3.emitTagPending();
            r3.transition(TokeniserState.Data);
            return;
        L29:
            r3.transition(TokeniserState.AttributeValue_singleQuoted);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$38, reason: invalid class name */
    public enum AnonymousClass38 extends TokeniserState {
        public AnonymousClass38(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            String r0 = r4.consumeToAny(TokeniserState.attributeDoubleValueCharsSorted);
            if (r0.length() <= 0) goto L5;
            r3.tagPending.appendAttributeValue(r0);
        L6:
            char r42 = r4.consume();
            if (r42 != 0) goto L9;
            r3.error(this);
            r3.tagPending.appendAttributeValue(65533);
            return;
        L9:
            if (r42 != '\"') goto L11;
            r3.transition(TokeniserState.AfterAttributeValue_quoted);
            return;
        L11:
            if (r42 != '&') goto L13;
            int[] r2 = r3.consumeCharacterReference(Character.valueOf(Typography.quote), true);
            Token.Tag r32 = r3.tagPending;
            if (r2 == null) goto L22;
            r32.appendAttributeValue(r2);
            return;
        L22:
            r32.appendAttributeValue(Typography.amp);
            return;
        L13:
            if (r42 == 65535) goto L16;
            r3.tagPending.appendAttributeValue(r42);
            return;
        L16:
            r3.eofError(this);
            r3.transition(TokeniserState.Data);
            return;
        L5:
            r3.tagPending.setEmptyAttributeValue();
            goto L6
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$39, reason: invalid class name */
    public enum AnonymousClass39 extends TokeniserState {
        public AnonymousClass39(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            String r0 = r4.consumeToAny(TokeniserState.attributeSingleValueCharsSorted);
            if (r0.length() <= 0) goto L5;
            r3.tagPending.appendAttributeValue(r0);
        L6:
            char r42 = r4.consume();
            if (r42 != 0) goto L9;
            r3.error(this);
            r3.tagPending.appendAttributeValue(65533);
            return;
        L9:
            if (r42 != 65535) goto L11;
            r3.eofError(this);
            r3.transition(TokeniserState.Data);
            return;
        L11:
            if (r42 == '&') goto L17;
            if (r42 == '\'') goto L15;
            r3.tagPending.appendAttributeValue(r42);
            return;
        L15:
            r3.transition(TokeniserState.AfterAttributeValue_quoted);
            return;
        L17:
            int[] r2 = r3.consumeCharacterReference('\'', true);
            Token.Tag r32 = r3.tagPending;
            if (r2 == null) goto L21;
            r32.appendAttributeValue(r2);
            return;
        L21:
            r32.appendAttributeValue(Typography.amp);
            return;
        L5:
            r3.tagPending.setEmptyAttributeValue();
            goto L6
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$4, reason: invalid class name */
    public enum AnonymousClass4 extends TokeniserState {
        public AnonymousClass4(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r1, CharacterReader r2) {
            TokeniserState.access$100(r1, TokeniserState.Rcdata);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$40, reason: invalid class name */
    public enum AnonymousClass40 extends TokeniserState {
        public AnonymousClass40(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            String r0 = r4.consumeToAnySorted(TokeniserState.attributeValueUnquoted);
            if (r0.length() <= 0) goto L5;
            r3.tagPending.appendAttributeValue(r0);
        L5:
            char r42 = r4.consume();
            if (r42 != 0) goto L8;
            r3.error(this);
            r3.tagPending.appendAttributeValue(65533);
            return;
        L8:
            if (r42 != ' ') goto L10;
        L42:
            r3.transition(TokeniserState.BeforeAttributeName);
            return;
        L10:
            if (r42 != '\"') goto L12;
        L40:
            r3.error(this);
            r3.tagPending.appendAttributeValue(r42);
            return;
        L12:
            if (r42 == '`') goto L40;
            if (r42 != 65535) goto L16;
            r3.eofError(this);
            r3.transition(TokeniserState.Data);
            return;
        L16:
            if (r42 == '\t') goto L42;
            if (r42 == '\n') goto L42;
            if (r42 == '\f') goto L42;
            if (r42 == '\r') goto L42;
            if (r42 != '&') goto L26;
            int[] r2 = r3.consumeCharacterReference(Character.valueOf(Typography.greater), true);
            Token.Tag r32 = r3.tagPending;
            if (r2 == null) goto L36;
            r32.appendAttributeValue(r2);
            return;
        L36:
            r32.appendAttributeValue(Typography.amp);
            return;
        L26:
            if (r42 == '\'') goto L40;
            switch(r42) {
                case 60: goto L40;
                case 61: goto L40;
                case 62: goto L30;
                default: goto L28;
            };
        L28:
            r3.tagPending.appendAttributeValue(r42);
            return;
        L30:
            r3.emitTagPending();
            r3.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$41, reason: invalid class name */
    public enum AnonymousClass41 extends TokeniserState {
        public AnonymousClass41(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r0 = r4.consume();
            if (r0 != '\t') goto L5;
        L26:
            r3.transition(TokeniserState.BeforeAttributeName);
            return;
        L5:
            if (r0 == '\n') goto L26;
            if (r0 == '\f') goto L26;
            if (r0 == '\r') goto L26;
            if (r0 == ' ') goto L26;
            if (r0 != '/') goto L15;
            r3.transition(TokeniserState.SelfClosingStartTag);
            return;
        L15:
            if (r0 != '>') goto L17;
            r3.emitTagPending();
            r3.transition(TokeniserState.Data);
            return;
        L17:
            if (r0 == 65535) goto L20;
            r3.error(this);
            r4.unconsume();
            r3.transition(TokeniserState.BeforeAttributeName);
            return;
        L20:
            r3.eofError(this);
            r3.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$42, reason: invalid class name */
    public enum AnonymousClass42 extends TokeniserState {
        public AnonymousClass42(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r0 = r4.consume();
            if (r0 != '>') goto L5;
            r3.tagPending.selfClosing = true;
            r3.emitTagPending();
            r3.transition(TokeniserState.Data);
            return;
        L5:
            if (r0 == 65535) goto L8;
            r3.error(this);
            r4.unconsume();
            r3.transition(TokeniserState.BeforeAttributeName);
            return;
        L8:
            r3.eofError(this);
            r3.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$43, reason: invalid class name */
    public enum AnonymousClass43 extends TokeniserState {
        public AnonymousClass43(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            r4.unconsume();
            Token.Comment r2 = new Token.Comment();
            r2.bogus = true;
            r2.data.append(r4.consumeTo(Typography.greater));
            r3.emit(r2);
            r3.advanceTransition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$44, reason: invalid class name */
    public enum AnonymousClass44 extends TokeniserState {
        public AnonymousClass44(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            if (r3.matchConsume("--") == false) goto L7;
            r2.createCommentPending();
            r2.transition(TokeniserState.CommentStart);
            return;
        L7:
            if (r3.matchConsumeIgnoreCase("DOCTYPE") == false) goto L11;
            r2.transition(TokeniserState.Doctype);
            return;
        L11:
            if (r3.matchConsume("[CDATA[") == false) goto L14;
            r2.createTempBuffer();
            r2.transition(TokeniserState.CdataSection);
            return;
        L14:
            r2.error(this);
            r2.advanceTransition(TokeniserState.BogusComment);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$45, reason: invalid class name */
    public enum AnonymousClass45 extends TokeniserState {
        public AnonymousClass45(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            char r32 = r3.consume();
            if (r32 != 0) goto L5;
            r2.error(this);
            r2.commentPending.data.append(65533);
            r2.transition(TokeniserState.Comment);
            return;
        L5:
            if (r32 != '-') goto L7;
            r2.transition(TokeniserState.CommentStartDash);
            return;
        L7:
            if (r32 != '>') goto L9;
            r2.error(this);
            r2.emitCommentPending();
            r2.transition(TokeniserState.Data);
            return;
        L9:
            if (r32 == 65535) goto L12;
            r2.commentPending.data.append(r32);
            r2.transition(TokeniserState.Comment);
            return;
        L12:
            r2.eofError(this);
            r2.emitCommentPending();
            r2.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$46, reason: invalid class name */
    public enum AnonymousClass46 extends TokeniserState {
        public AnonymousClass46(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            char r32 = r3.consume();
            if (r32 != 0) goto L5;
            r2.error(this);
            r2.commentPending.data.append(65533);
            r2.transition(TokeniserState.Comment);
            return;
        L5:
            if (r32 != '-') goto L7;
            r2.transition(TokeniserState.CommentStartDash);
            return;
        L7:
            if (r32 != '>') goto L9;
            r2.error(this);
            r2.emitCommentPending();
            r2.transition(TokeniserState.Data);
            return;
        L9:
            if (r32 == 65535) goto L12;
            r2.commentPending.data.append(r32);
            r2.transition(TokeniserState.Comment);
            return;
        L12:
            r2.eofError(this);
            r2.emitCommentPending();
            r2.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$47, reason: invalid class name */
    public enum AnonymousClass47 extends TokeniserState {
        public AnonymousClass47(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r0 = r4.current();
            if (r0 != 0) goto L5;
            r3.error(this);
            r4.advance();
            r3.commentPending.data.append(65533);
            return;
        L5:
            if (r0 != '-') goto L7;
            r3.advanceTransition(TokeniserState.CommentEndDash);
            return;
        L7:
            if (r0 == 65535) goto L10;
            r3.commentPending.data.append(r4.consumeToAny(new char[]{SignatureVisitor.SUPER, 0}));
            return;
        L10:
            r3.eofError(this);
            r3.emitCommentPending();
            r3.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$48, reason: invalid class name */
    public enum AnonymousClass48 extends TokeniserState {
        public AnonymousClass48(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r42 = r4.consume();
            if (r42 == 0) goto L13;
            if (r42 != '-') goto L6;
            r3.transition(TokeniserState.CommentEnd);
            return;
        L6:
            if (r42 == 65535) goto L9;
            StringBuilder r2 = r3.commentPending.data;
            r2.append(SignatureVisitor.SUPER);
            r2.append(r42);
            r3.transition(TokeniserState.Comment);
            return;
        L9:
            r3.eofError(this);
            r3.emitCommentPending();
            r3.transition(TokeniserState.Data);
            return;
        L13:
            r3.error(this);
            StringBuilder r22 = r3.commentPending.data;
            r22.append(SignatureVisitor.SUPER);
            r22.append(65533);
            r3.transition(TokeniserState.Comment);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$49, reason: invalid class name */
    public enum AnonymousClass49 extends TokeniserState {
        public AnonymousClass49(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r42 = r4.consume();
            if (r42 != 0) goto L5;
            r3.error(this);
            StringBuilder r2 = r3.commentPending.data;
            r2.append("--");
            r2.append(65533);
            r3.transition(TokeniserState.Comment);
            return;
        L5:
            if (r42 != '!') goto L7;
            r3.error(this);
            r3.transition(TokeniserState.CommentEndBang);
            return;
        L7:
            if (r42 != '-') goto L9;
            r3.error(this);
            r3.commentPending.data.append(SignatureVisitor.SUPER);
            return;
        L9:
            if (r42 != '>') goto L11;
            r3.emitCommentPending();
            r3.transition(TokeniserState.Data);
            return;
        L11:
            if (r42 == 65535) goto L14;
            r3.error(this);
            StringBuilder r22 = r3.commentPending.data;
            r22.append("--");
            r22.append(r42);
            r3.transition(TokeniserState.Comment);
            return;
        L14:
            r3.eofError(this);
            r3.emitCommentPending();
            r3.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$5, reason: invalid class name */
    public enum AnonymousClass5 extends TokeniserState {
        public AnonymousClass5(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            TokeniserState.access$200(r2, r3, this, TokeniserState.RawtextLessthanSign);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$50, reason: invalid class name */
    public enum AnonymousClass50 extends TokeniserState {
        public AnonymousClass50(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r42 = r4.consume();
            if (r42 != 0) goto L5;
            r3.error(this);
            StringBuilder r2 = r3.commentPending.data;
            r2.append("--!");
            r2.append(65533);
            r3.transition(TokeniserState.Comment);
            return;
        L5:
            if (r42 != '-') goto L7;
            r3.commentPending.data.append("--!");
            r3.transition(TokeniserState.CommentEndDash);
            return;
        L7:
            if (r42 != '>') goto L9;
            r3.emitCommentPending();
            r3.transition(TokeniserState.Data);
            return;
        L9:
            if (r42 == 65535) goto L12;
            StringBuilder r22 = r3.commentPending.data;
            r22.append("--!");
            r22.append(r42);
            r3.transition(TokeniserState.Comment);
            return;
        L12:
            r3.eofError(this);
            r3.emitCommentPending();
            r3.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$51, reason: invalid class name */
    public enum AnonymousClass51 extends TokeniserState {
        public AnonymousClass51(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            char r32 = r3.consume();
            if (r32 != '\t') goto L5;
        L21:
            r2.transition(TokeniserState.BeforeDoctypeName);
            return;
        L5:
            if (r32 == '\n') goto L21;
            if (r32 == '\f') goto L21;
            if (r32 == '\r') goto L21;
            if (r32 == ' ') goto L21;
            if (r32 != '>') goto L15;
        L19:
            r2.error(this);
            r2.createDoctypePending();
            r2.doctypePending.forceQuirks = true;
            r2.emitDoctypePending();
            r2.transition(TokeniserState.Data);
            return;
        L15:
            if (r32 == 65535) goto L18;
            r2.error(this);
            r2.transition(TokeniserState.BeforeDoctypeName);
            return;
        L18:
            r2.eofError(this);
            goto L19
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$52, reason: invalid class name */
    public enum AnonymousClass52 extends TokeniserState {
        public AnonymousClass52(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            if (r3.matchesLetter() == false) goto L6;
            r2.createDoctypePending();
            r2.transition(TokeniserState.DoctypeName);
            return;
        L6:
            char r32 = r3.consume();
            if (r32 != 0) goto L9;
            r2.error(this);
            r2.createDoctypePending();
            r2.doctypePending.name.append(65533);
            r2.transition(TokeniserState.DoctypeName);
            return;
        L9:
            if (r32 != ' ') goto L11;
            return;
        L11:
            if (r32 != 65535) goto L13;
            r2.eofError(this);
            r2.createDoctypePending();
            r2.doctypePending.forceQuirks = true;
            r2.emitDoctypePending();
            r2.transition(TokeniserState.Data);
            return;
        L13:
            if (r32 != '\t') goto L15;
            return;
        L15:
            if (r32 != '\n') goto L17;
            return;
        L17:
            if (r32 != '\f') goto L19;
            return;
        L19:
            if (r32 == '\r') goto L30;
            r2.createDoctypePending();
            r2.doctypePending.name.append(r32);
            r2.transition(TokeniserState.DoctypeName);
            return;
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$53, reason: invalid class name */
    public enum AnonymousClass53 extends TokeniserState {
        public AnonymousClass53(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            if (r3.matchesLetter() == false) goto L6;
            String r1 = r3.consumeLetterSequence();
            r2.doctypePending.name.append(r1);
            return;
        L6:
            char r32 = r3.consume();
            if (r32 != 0) goto L9;
            r2.error(this);
            r2.doctypePending.name.append(65533);
            return;
        L9:
            if (r32 != ' ') goto L11;
        L28:
            r2.transition(TokeniserState.AfterDoctypeName);
            return;
        L11:
            if (r32 != '>') goto L13;
            r2.emitDoctypePending();
            r2.transition(TokeniserState.Data);
            return;
        L13:
            if (r32 != 65535) goto L15;
            r2.eofError(this);
            r2.doctypePending.forceQuirks = true;
            r2.emitDoctypePending();
            r2.transition(TokeniserState.Data);
            return;
        L15:
            if (r32 == '\t') goto L28;
            if (r32 == '\n') goto L28;
            if (r32 == '\f') goto L28;
            if (r32 == '\r') goto L28;
            r2.doctypePending.name.append(r32);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$54, reason: invalid class name */
    public enum AnonymousClass54 extends TokeniserState {
        public AnonymousClass54(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r4, CharacterReader r5) {
            if (r5.isEmpty() == false) goto L7;
            r4.eofError(this);
            r4.doctypePending.forceQuirks = true;
            r4.emitDoctypePending();
            r4.transition(TokeniserState.Data);
            return;
        L7:
            if (r5.matchesAny(new char[]{'\t', '\n', '\r', '\f', ' '}) == false) goto L11;
            r5.advance();
            return;
        L11:
            if (r5.matches(Typography.greater) == false) goto L15;
            r4.emitDoctypePending();
            r4.advanceTransition(TokeniserState.Data);
            return;
        L15:
            if (r5.matchConsumeIgnoreCase("PUBLIC") == false) goto L19;
            r4.doctypePending.pubSysKey = "PUBLIC";
            r4.transition(TokeniserState.AfterDoctypePublicKeyword);
            return;
        L19:
            if (r5.matchConsumeIgnoreCase("SYSTEM") == false) goto L22;
            r4.doctypePending.pubSysKey = "SYSTEM";
            r4.transition(TokeniserState.AfterDoctypeSystemKeyword);
            return;
        L22:
            r4.error(this);
            r4.doctypePending.forceQuirks = true;
            r4.advanceTransition(TokeniserState.BogusDoctype);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$55, reason: invalid class name */
    public enum AnonymousClass55 extends TokeniserState {
        public AnonymousClass55(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r42 = r4.consume();
            if (r42 != '\t') goto L5;
        L30:
            r3.transition(TokeniserState.BeforeDoctypePublicIdentifier);
            return;
        L5:
            if (r42 == '\n') goto L30;
            if (r42 == '\f') goto L30;
            if (r42 == '\r') goto L30;
            if (r42 == ' ') goto L30;
            if (r42 != '\"') goto L15;
            r3.error(this);
            r3.transition(TokeniserState.DoctypePublicIdentifier_doubleQuoted);
            return;
        L15:
            if (r42 != '\'') goto L17;
            r3.error(this);
            r3.transition(TokeniserState.DoctypePublicIdentifier_singleQuoted);
            return;
        L17:
            if (r42 != '>') goto L19;
            r3.error(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
            return;
        L19:
            if (r42 == 65535) goto L22;
            r3.error(this);
            r3.doctypePending.forceQuirks = true;
            r3.transition(TokeniserState.BogusDoctype);
            return;
        L22:
            r3.eofError(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$56, reason: invalid class name */
    public enum AnonymousClass56 extends TokeniserState {
        public AnonymousClass56(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r42 = r4.consume();
            if (r42 != '\t') goto L5;
            return;
        L5:
            if (r42 != '\n') goto L7;
            return;
        L7:
            if (r42 != '\f') goto L9;
            return;
        L9:
            if (r42 != '\r') goto L11;
            return;
        L11:
            if (r42 != ' ') goto L13;
            return;
        L13:
            if (r42 != '\"') goto L15;
            r3.transition(TokeniserState.DoctypePublicIdentifier_doubleQuoted);
            return;
        L15:
            if (r42 != '\'') goto L17;
            r3.transition(TokeniserState.DoctypePublicIdentifier_singleQuoted);
            return;
        L17:
            if (r42 != '>') goto L19;
            r3.error(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
            return;
        L19:
            if (r42 == 65535) goto L22;
            r3.error(this);
            r3.doctypePending.forceQuirks = true;
            r3.transition(TokeniserState.BogusDoctype);
            return;
        L22:
            r3.eofError(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$57, reason: invalid class name */
    public enum AnonymousClass57 extends TokeniserState {
        public AnonymousClass57(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r42 = r4.consume();
            if (r42 != 0) goto L5;
            r3.error(this);
            r3.doctypePending.publicIdentifier.append(65533);
            return;
        L5:
            if (r42 != '\"') goto L7;
            r3.transition(TokeniserState.AfterDoctypePublicIdentifier);
            return;
        L7:
            if (r42 != '>') goto L9;
            r3.error(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
            return;
        L9:
            if (r42 == 65535) goto L12;
            r3.doctypePending.publicIdentifier.append(r42);
            return;
        L12:
            r3.eofError(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$58, reason: invalid class name */
    public enum AnonymousClass58 extends TokeniserState {
        public AnonymousClass58(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r42 = r4.consume();
            if (r42 != 0) goto L5;
            r3.error(this);
            r3.doctypePending.publicIdentifier.append(65533);
            return;
        L5:
            if (r42 != '\'') goto L7;
            r3.transition(TokeniserState.AfterDoctypePublicIdentifier);
            return;
        L7:
            if (r42 != '>') goto L9;
            r3.error(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
            return;
        L9:
            if (r42 == 65535) goto L12;
            r3.doctypePending.publicIdentifier.append(r42);
            return;
        L12:
            r3.eofError(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$59, reason: invalid class name */
    public enum AnonymousClass59 extends TokeniserState {
        public AnonymousClass59(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r42 = r4.consume();
            if (r42 != '\t') goto L5;
        L30:
            r3.transition(TokeniserState.BetweenDoctypePublicAndSystemIdentifiers);
            return;
        L5:
            if (r42 == '\n') goto L30;
            if (r42 == '\f') goto L30;
            if (r42 == '\r') goto L30;
            if (r42 == ' ') goto L30;
            if (r42 != '\"') goto L15;
            r3.error(this);
            r3.transition(TokeniserState.DoctypeSystemIdentifier_doubleQuoted);
            return;
        L15:
            if (r42 != '\'') goto L17;
            r3.error(this);
            r3.transition(TokeniserState.DoctypeSystemIdentifier_singleQuoted);
            return;
        L17:
            if (r42 != '>') goto L19;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
            return;
        L19:
            if (r42 == 65535) goto L22;
            r3.error(this);
            r3.doctypePending.forceQuirks = true;
            r3.transition(TokeniserState.BogusDoctype);
            return;
        L22:
            r3.eofError(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$6, reason: invalid class name */
    public enum AnonymousClass6 extends TokeniserState {
        public AnonymousClass6(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            TokeniserState.access$200(r2, r3, this, TokeniserState.ScriptDataLessthanSign);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$60, reason: invalid class name */
    public enum AnonymousClass60 extends TokeniserState {
        public AnonymousClass60(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r42 = r4.consume();
            if (r42 != '\t') goto L5;
            return;
        L5:
            if (r42 != '\n') goto L7;
            return;
        L7:
            if (r42 != '\f') goto L9;
            return;
        L9:
            if (r42 != '\r') goto L11;
            return;
        L11:
            if (r42 != ' ') goto L13;
            return;
        L13:
            if (r42 != '\"') goto L15;
            r3.error(this);
            r3.transition(TokeniserState.DoctypeSystemIdentifier_doubleQuoted);
            return;
        L15:
            if (r42 != '\'') goto L17;
            r3.error(this);
            r3.transition(TokeniserState.DoctypeSystemIdentifier_singleQuoted);
            return;
        L17:
            if (r42 != '>') goto L19;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
            return;
        L19:
            if (r42 == 65535) goto L22;
            r3.error(this);
            r3.doctypePending.forceQuirks = true;
            r3.transition(TokeniserState.BogusDoctype);
            return;
        L22:
            r3.eofError(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$61, reason: invalid class name */
    public enum AnonymousClass61 extends TokeniserState {
        public AnonymousClass61(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r42 = r4.consume();
            if (r42 != '\t') goto L5;
        L30:
            r3.transition(TokeniserState.BeforeDoctypeSystemIdentifier);
            return;
        L5:
            if (r42 == '\n') goto L30;
            if (r42 == '\f') goto L30;
            if (r42 == '\r') goto L30;
            if (r42 == ' ') goto L30;
            if (r42 != '\"') goto L15;
            r3.error(this);
            r3.transition(TokeniserState.DoctypeSystemIdentifier_doubleQuoted);
            return;
        L15:
            if (r42 != '\'') goto L17;
            r3.error(this);
            r3.transition(TokeniserState.DoctypeSystemIdentifier_singleQuoted);
            return;
        L17:
            if (r42 != '>') goto L19;
            r3.error(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
            return;
        L19:
            if (r42 == 65535) goto L22;
            r3.error(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            return;
        L22:
            r3.eofError(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$62, reason: invalid class name */
    public enum AnonymousClass62 extends TokeniserState {
        public AnonymousClass62(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r42 = r4.consume();
            if (r42 != '\t') goto L5;
            return;
        L5:
            if (r42 != '\n') goto L7;
            return;
        L7:
            if (r42 != '\f') goto L9;
            return;
        L9:
            if (r42 != '\r') goto L11;
            return;
        L11:
            if (r42 != ' ') goto L13;
            return;
        L13:
            if (r42 != '\"') goto L15;
            r3.transition(TokeniserState.DoctypeSystemIdentifier_doubleQuoted);
            return;
        L15:
            if (r42 != '\'') goto L17;
            r3.transition(TokeniserState.DoctypeSystemIdentifier_singleQuoted);
            return;
        L17:
            if (r42 != '>') goto L19;
            r3.error(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
            return;
        L19:
            if (r42 == 65535) goto L22;
            r3.error(this);
            r3.doctypePending.forceQuirks = true;
            r3.transition(TokeniserState.BogusDoctype);
            return;
        L22:
            r3.eofError(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$63, reason: invalid class name */
    public enum AnonymousClass63 extends TokeniserState {
        public AnonymousClass63(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r42 = r4.consume();
            if (r42 != 0) goto L5;
            r3.error(this);
            r3.doctypePending.systemIdentifier.append(65533);
            return;
        L5:
            if (r42 != '\"') goto L7;
            r3.transition(TokeniserState.AfterDoctypeSystemIdentifier);
            return;
        L7:
            if (r42 != '>') goto L9;
            r3.error(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
            return;
        L9:
            if (r42 == 65535) goto L12;
            r3.doctypePending.systemIdentifier.append(r42);
            return;
        L12:
            r3.eofError(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$64, reason: invalid class name */
    public enum AnonymousClass64 extends TokeniserState {
        public AnonymousClass64(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r42 = r4.consume();
            if (r42 != 0) goto L5;
            r3.error(this);
            r3.doctypePending.systemIdentifier.append(65533);
            return;
        L5:
            if (r42 != '\'') goto L7;
            r3.transition(TokeniserState.AfterDoctypeSystemIdentifier);
            return;
        L7:
            if (r42 != '>') goto L9;
            r3.error(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
            return;
        L9:
            if (r42 == 65535) goto L12;
            r3.doctypePending.systemIdentifier.append(r42);
            return;
        L12:
            r3.eofError(this);
            r3.doctypePending.forceQuirks = true;
            r3.emitDoctypePending();
            r3.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$65, reason: invalid class name */
    public enum AnonymousClass65 extends TokeniserState {
        public AnonymousClass65(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            char r32 = r3.consume();
            if (r32 != '\t') goto L5;
            return;
        L5:
            if (r32 != '\n') goto L7;
            return;
        L7:
            if (r32 != '\f') goto L9;
            return;
        L9:
            if (r32 != '\r') goto L11;
            return;
        L11:
            if (r32 != ' ') goto L13;
            return;
        L13:
            if (r32 != '>') goto L15;
            r2.emitDoctypePending();
            r2.transition(TokeniserState.Data);
            return;
        L15:
            if (r32 == 65535) goto L18;
            r2.error(this);
            r2.transition(TokeniserState.BogusDoctype);
            return;
        L18:
            r2.eofError(this);
            r2.doctypePending.forceQuirks = true;
            r2.emitDoctypePending();
            r2.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$66, reason: invalid class name */
    public enum AnonymousClass66 extends TokeniserState {
        public AnonymousClass66(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r1, CharacterReader r2) {
            char r0 = r2.consume();
            if (r0 != '>') goto L5;
            r1.emitDoctypePending();
            r1.transition(TokeniserState.Data);
            return;
        L5:
            if (r0 == 65535) goto L7;
            return;
        L7:
            r1.emitDoctypePending();
            r1.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$67, reason: invalid class name */
    public enum AnonymousClass67 extends TokeniserState {
        public AnonymousClass67(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            String r0 = r4.consumeTo("]]>");
            r3.dataBuffer.append(r0);
            if (r4.matchConsume("]]>") == false) goto L5;
        L8:
            r3.emit(new Token.CData(r3.dataBuffer.toString()));
            r3.transition(TokeniserState.Data);
            return;
        L5:
            if (r4.isEmpty() == true) goto L8;
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$7, reason: invalid class name */
    public enum AnonymousClass7 extends TokeniserState {
        public AnonymousClass7(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            char r0 = r3.current();
            if (r0 != 0) goto L5;
            r2.error(this);
            r3.advance();
            r2.emit(65533);
            return;
        L5:
            if (r0 == 65535) goto L8;
            r2.emit(r3.consumeTo(0));
            return;
        L8:
            r2.emit(new Token.EOF());
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$8, reason: invalid class name */
    public enum AnonymousClass8 extends TokeniserState {
        public AnonymousClass8(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r3, CharacterReader r4) {
            char r0 = r4.current();
            if (r0 != '!') goto L5;
            r3.advanceTransition(TokeniserState.MarkupDeclarationOpen);
            return;
        L5:
            if (r0 != '/') goto L7;
            r3.advanceTransition(TokeniserState.EndTagOpen);
            return;
        L7:
            if (r0 != '?') goto L9;
            r3.advanceTransition(TokeniserState.BogusComment);
            return;
        L9:
            if (r4.matchesLetter() == false) goto L12;
            r3.createTagPending(true);
            r3.transition(TokeniserState.TagName);
            return;
        L12:
            r3.error(this);
            r3.emit(Typography.less);
            r3.transition(TokeniserState.Data);
        }
    }

    /* renamed from: io.noties.markwon.html.jsoup.parser.TokeniserState$9, reason: invalid class name */
    public enum AnonymousClass9 extends TokeniserState {
        public AnonymousClass9(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // io.noties.markwon.html.jsoup.parser.TokeniserState
        public void read(Tokeniser r2, CharacterReader r3) {
            if (r3.isEmpty() == false) goto L7;
            r2.eofError(this);
            r2.emit("</");
            r2.transition(TokeniserState.Data);
            return;
        L7:
            if (r3.matchesLetter() == false) goto L11;
            r2.createTagPending(false);
            r2.transition(TokeniserState.TagName);
            return;
        L11:
            if (r3.matches(Typography.greater) == false) goto L14;
            r2.error(this);
            r2.advanceTransition(TokeniserState.Data);
            return;
        L14:
            r2.error(this);
            r2.advanceTransition(TokeniserState.BogusComment);
        }
    }

    static {
        AnonymousClass1 r0 = new AnonymousClass1("Data", 0);
        Data = r0;
        AnonymousClass2 r1 = new AnonymousClass2("CharacterReferenceInData", 1);
        CharacterReferenceInData = r1;
        AnonymousClass3 r3 = new AnonymousClass3("Rcdata", 2);
        Rcdata = r3;
        AnonymousClass4 r5 = new AnonymousClass4("CharacterReferenceInRcdata", 3);
        CharacterReferenceInRcdata = r5;
        AnonymousClass5 r7 = new AnonymousClass5("Rawtext", 4);
        Rawtext = r7;
        AnonymousClass6 r9 = new AnonymousClass6("ScriptData", 5);
        ScriptData = r9;
        AnonymousClass7 r11 = new AnonymousClass7("PLAINTEXT", 6);
        PLAINTEXT = r11;
        AnonymousClass8 r13 = new AnonymousClass8("TagOpen", 7);
        TagOpen = r13;
        AnonymousClass9 r15 = new AnonymousClass9("EndTagOpen", 8);
        EndTagOpen = r15;
        AnonymousClass10 r2 = new AnonymousClass10("TagName", 9);
        TagName = r2;
        AnonymousClass11 r4 = new AnonymousClass11("RcdataLessthanSign", 10);
        RcdataLessthanSign = r4;
        AnonymousClass12 r6 = new AnonymousClass12("RCDATAEndTagOpen", 11);
        RCDATAEndTagOpen = r6;
        AnonymousClass13 r10 = new AnonymousClass13("RCDATAEndTagName", 12);
        RCDATAEndTagName = r10;
        AnonymousClass14 r12 = new AnonymousClass14("RawtextLessthanSign", 13);
        RawtextLessthanSign = r12;
        AnonymousClass15 r14 = new AnonymousClass15("RawtextEndTagOpen", 14);
        RawtextEndTagOpen = r14;
        AnonymousClass16 r02 = new AnonymousClass16("RawtextEndTagName", 15);
        RawtextEndTagName = r02;
        AnonymousClass17 r16 = new AnonymousClass17("ScriptDataLessthanSign", 16);
        ScriptDataLessthanSign = r16;
        AnonymousClass18 r03 = new AnonymousClass18("ScriptDataEndTagOpen", 17);
        ScriptDataEndTagOpen = r03;
        AnonymousClass19 r17 = new AnonymousClass19("ScriptDataEndTagName", 18);
        ScriptDataEndTagName = r17;
        AnonymousClass20 r04 = new AnonymousClass20("ScriptDataEscapeStart", 19);
        ScriptDataEscapeStart = r04;
        AnonymousClass21 r18 = new AnonymousClass21("ScriptDataEscapeStartDash", 20);
        ScriptDataEscapeStartDash = r18;
        AnonymousClass22 r05 = new AnonymousClass22("ScriptDataEscaped", 21);
        ScriptDataEscaped = r05;
        AnonymousClass23 r19 = new AnonymousClass23("ScriptDataEscapedDash", 22);
        ScriptDataEscapedDash = r19;
        AnonymousClass24 r06 = new AnonymousClass24("ScriptDataEscapedDashDash", 23);
        ScriptDataEscapedDashDash = r06;
        AnonymousClass25 r110 = new AnonymousClass25("ScriptDataEscapedLessthanSign", 24);
        ScriptDataEscapedLessthanSign = r110;
        AnonymousClass26 r07 = new AnonymousClass26("ScriptDataEscapedEndTagOpen", 25);
        ScriptDataEscapedEndTagOpen = r07;
        AnonymousClass27 r111 = new AnonymousClass27("ScriptDataEscapedEndTagName", 26);
        ScriptDataEscapedEndTagName = r111;
        AnonymousClass28 r08 = new AnonymousClass28("ScriptDataDoubleEscapeStart", 27);
        ScriptDataDoubleEscapeStart = r08;
        AnonymousClass29 r112 = new AnonymousClass29("ScriptDataDoubleEscaped", 28);
        ScriptDataDoubleEscaped = r112;
        AnonymousClass30 r09 = new AnonymousClass30("ScriptDataDoubleEscapedDash", 29);
        ScriptDataDoubleEscapedDash = r09;
        AnonymousClass31 r113 = new AnonymousClass31("ScriptDataDoubleEscapedDashDash", 30);
        ScriptDataDoubleEscapedDashDash = r113;
        AnonymousClass32 r010 = new AnonymousClass32("ScriptDataDoubleEscapedLessthanSign", 31);
        ScriptDataDoubleEscapedLessthanSign = r010;
        AnonymousClass33 r114 = new AnonymousClass33("ScriptDataDoubleEscapeEnd", 32);
        ScriptDataDoubleEscapeEnd = r114;
        AnonymousClass34 r8 = new AnonymousClass34("BeforeAttributeName", 33);
        BeforeAttributeName = r8;
        AnonymousClass35 r011 = new AnonymousClass35("AttributeName", 34);
        AttributeName = r011;
        AnonymousClass36 r115 = new AnonymousClass36("AfterAttributeName", 35);
        AfterAttributeName = r115;
        AnonymousClass37 r012 = new AnonymousClass37("BeforeAttributeValue", 36);
        BeforeAttributeValue = r012;
        AnonymousClass38 r116 = new AnonymousClass38("AttributeValue_doubleQuoted", 37);
        AttributeValue_doubleQuoted = r116;
        AnonymousClass39 r013 = new AnonymousClass39("AttributeValue_singleQuoted", 38);
        AttributeValue_singleQuoted = r013;
        AnonymousClass40 r22 = new AnonymousClass40("AttributeValue_unquoted", 39);
        AttributeValue_unquoted = r22;
        AnonymousClass41 r117 = new AnonymousClass41("AfterAttributeValue_quoted", 40);
        AfterAttributeValue_quoted = r117;
        AnonymousClass42 r014 = new AnonymousClass42("SelfClosingStartTag", 41);
        SelfClosingStartTag = r014;
        AnonymousClass43 r118 = new AnonymousClass43("BogusComment", 42);
        BogusComment = r118;
        AnonymousClass44 r015 = new AnonymousClass44("MarkupDeclarationOpen", 43);
        MarkupDeclarationOpen = r015;
        AnonymousClass45 r119 = new AnonymousClass45("CommentStart", 44);
        CommentStart = r119;
        AnonymousClass46 r016 = new AnonymousClass46("CommentStartDash", 45);
        CommentStartDash = r016;
        AnonymousClass47 r120 = new AnonymousClass47("Comment", 46);
        Comment = r120;
        AnonymousClass48 r017 = new AnonymousClass48("CommentEndDash", 47);
        CommentEndDash = r017;
        AnonymousClass49 r23 = new AnonymousClass49("CommentEnd", 48);
        CommentEnd = r23;
        AnonymousClass50 r018 = new AnonymousClass50("CommentEndBang", 49);
        CommentEndBang = r018;
        AnonymousClass51 r121 = new AnonymousClass51("Doctype", 50);
        Doctype = r121;
        AnonymousClass52 r019 = new AnonymousClass52("BeforeDoctypeName", 51);
        BeforeDoctypeName = r019;
        AnonymousClass53 r122 = new AnonymousClass53("DoctypeName", 52);
        DoctypeName = r122;
        AnonymousClass54 r020 = new AnonymousClass54("AfterDoctypeName", 53);
        AfterDoctypeName = r020;
        AnonymousClass55 r123 = new AnonymousClass55("AfterDoctypePublicKeyword", 54);
        AfterDoctypePublicKeyword = r123;
        AnonymousClass56 r021 = new AnonymousClass56("BeforeDoctypePublicIdentifier", 55);
        BeforeDoctypePublicIdentifier = r021;
        AnonymousClass57 r124 = new AnonymousClass57("DoctypePublicIdentifier_doubleQuoted", 56);
        DoctypePublicIdentifier_doubleQuoted = r124;
        AnonymousClass58 r022 = new AnonymousClass58("DoctypePublicIdentifier_singleQuoted", 57);
        DoctypePublicIdentifier_singleQuoted = r022;
        AnonymousClass59 r125 = new AnonymousClass59("AfterDoctypePublicIdentifier", 58);
        AfterDoctypePublicIdentifier = r125;
        AnonymousClass60 r023 = new AnonymousClass60("BetweenDoctypePublicAndSystemIdentifiers", 59);
        BetweenDoctypePublicAndSystemIdentifiers = r023;
        AnonymousClass61 r126 = new AnonymousClass61("AfterDoctypeSystemKeyword", 60);
        AfterDoctypeSystemKeyword = r126;
        AnonymousClass62 r24 = new AnonymousClass62("BeforeDoctypeSystemIdentifier", 61);
        BeforeDoctypeSystemIdentifier = r24;
        AnonymousClass63 r024 = new AnonymousClass63("DoctypeSystemIdentifier_doubleQuoted", 62);
        DoctypeSystemIdentifier_doubleQuoted = r024;
        AnonymousClass64 r127 = new AnonymousClass64("DoctypeSystemIdentifier_singleQuoted", 63);
        DoctypeSystemIdentifier_singleQuoted = r127;
        AnonymousClass65 r025 = new AnonymousClass65("AfterDoctypeSystemIdentifier", 64);
        AfterDoctypeSystemIdentifier = r025;
        AnonymousClass66 r128 = new AnonymousClass66("BogusDoctype", 65);
        BogusDoctype = r128;
        AnonymousClass67 r026 = new AnonymousClass67("CdataSection", 66);
        CdataSection = r026;
        $VALUES = new TokeniserState[]{r0, r1, r3, r5, r7, r9, r11, r13, r15, r2, r4, r6, r10, r12, r14, r02, r16, r03, r17, r04, r18, r05, r19, r06, r110, r07, r111, r08, r112, r09, r113, r010, r114, r8, r011, r115, r012, r116, r013, r22, r117, r014, r118, r015, r119, r016, r120, r017, r23, r018, r121, r019, r122, r020, r123, r021, r124, r022, r125, r023, r126, r24, r024, r127, r025, r128, r026};
        attributeSingleValueCharsSorted = new char[]{0, Typography.amp, '\''};
        attributeDoubleValueCharsSorted = new char[]{0, Typography.quote, Typography.amp};
        attributeNameCharsSorted = new char[]{0, '\t', '\n', '\f', '\r', ' ', Typography.quote, '\'', '/', Typography.less, SignatureVisitor.INSTANCEOF, Typography.greater};
        attributeValueUnquoted = new char[]{0, '\t', '\n', '\f', '\r', ' ', Typography.quote, Typography.amp, '\'', Typography.less, SignatureVisitor.INSTANCEOF, Typography.greater, '`'};
        replacementStr = String.valueOf(65533);
    }

    TokeniserState(String r1, int r2) {
    }

    /* synthetic */ TokeniserState(String r1, int r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public static /* synthetic */ void access$100(Tokeniser r0, TokeniserState r1) {
        readCharRef(r0, r1);
    }

    public static /* synthetic */ void access$200(Tokeniser r0, CharacterReader r1, TokeniserState r2, TokeniserState r3) {
        readData(r0, r1, r2, r3);
    }

    public static /* synthetic */ String access$300() {
        return replacementStr;
    }

    public static /* synthetic */ void access$400(Tokeniser r0, CharacterReader r1, TokeniserState r2, TokeniserState r3) {
        readEndTag(r0, r1, r2, r3);
    }

    public static /* synthetic */ void access$500(Tokeniser r0, CharacterReader r1, TokeniserState r2) {
        handleDataEndTag(r0, r1, r2);
    }

    public static /* synthetic */ void access$600(Tokeniser r0, CharacterReader r1, TokeniserState r2, TokeniserState r3) {
        handleDataDoubleEscapeTag(r0, r1, r2, r3);
    }

    private static void handleDataDoubleEscapeTag(Tokeniser r2, CharacterReader r3, TokeniserState r4, TokeniserState r5) {
        if (r3.matchesLetter() == false) goto L6;
        String r32 = r3.consumeLetterSequence();
        r2.dataBuffer.append(r32);
        r2.emit(r32);
        return;
    L6:
        char r0 = r3.consume();
        if (r0 == '\t') goto L23;
        if (r0 == '\n') goto L23;
        if (r0 == '\f') goto L23;
        if (r0 == '\r') goto L23;
        if (r0 == ' ') goto L23;
        if (r0 == '/') goto L23;
        if (r0 == '>') goto L23;
        r3.unconsume();
        r2.transition(r5);
        return;
    L23:
        if (r2.dataBuffer.toString().equals("script") == false) goto L25;
        r2.transition(r4);
    L26:
        r2.emit(r0);
        return;
    L25:
        r2.transition(r5);
        goto L26
    }

    private static void handleDataEndTag(Tokeniser r1, CharacterReader r2, TokeniserState r3) {
        if (r2.matchesLetter() == false) goto L7;
        String r22 = r2.consumeLetterSequence();
        r1.tagPending.appendTagName(r22);
        r1.dataBuffer.append(r22);
        return;
    L7:
        if (r1.isAppropriateEndTagToken() == true) goto L9;
    L31:
        r1.emit("</".concat(r1.dataBuffer.toString()));
        r1.transition(r3);
        return;
    L9:
        if (r2.isEmpty() == true) goto L31;
        char r23 = r2.consume();
        if (r23 != '\t') goto L13;
    L29:
        r1.transition(BeforeAttributeName);
        return;
    L13:
        if (r23 == '\n') goto L29;
        if (r23 == '\f') goto L29;
        if (r23 == '\r') goto L29;
        if (r23 == ' ') goto L29;
        if (r23 != '/') goto L23;
        r1.transition(SelfClosingStartTag);
        return;
    L23:
        if (r23 == '>') goto L25;
        r1.dataBuffer.append(r23);
        goto L31
    L25:
        r1.emitTagPending();
        r1.transition(Data);
    }

    private static void readCharRef(Tokeniser r2, TokeniserState r3) {
        int[] r0 = r2.consumeCharacterReference(null, false);
        if (r0 != null) goto L5;
        r2.emit(Typography.amp);
    L6:
        r2.transition(r3);
        return;
    L5:
        r2.emit(r0);
        goto L6
    }

    private static void readData(Tokeniser r1, CharacterReader r2, TokeniserState r3, TokeniserState r4) {
        char r0 = r2.current();
        if (r0 != 0) goto L5;
        r1.error(r3);
        r2.advance();
        r1.emit(65533);
        return;
    L5:
        if (r0 != '<') goto L7;
        r1.advanceTransition(r4);
        return;
    L7:
        if (r0 == 65535) goto L10;
        r1.emit(r2.consumeToAny(new char[]{Typography.less, 0}));
        return;
    L10:
        r1.emit(new Token.EOF());
    }

    private static void readEndTag(Tokeniser r0, CharacterReader r1, TokeniserState r2, TokeniserState r3) {
        if (r1.matchesLetter() == false) goto L6;
        r0.createTagPending(false);
        r0.transition(r2);
        return;
    L6:
        r0.emit("</");
        r0.transition(r3);
    }

    public static TokeniserState valueOf(String r1) {
        return (TokeniserState) Enum.valueOf(TokeniserState.class, r1);
    }

    public static TokeniserState[] values() {
        return (TokeniserState[]) $VALUES.clone();
    }

    public abstract void read(Tokeniser r1, CharacterReader r2);
}
