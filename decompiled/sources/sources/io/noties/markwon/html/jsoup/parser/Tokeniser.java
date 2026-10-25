package io.noties.markwon.html.jsoup.parser;

import io.noties.markwon.html.jsoup.helper.Validate;
import io.noties.markwon.html.jsoup.nodes.CommonMarkEntities;
import io.noties.markwon.html.jsoup.parser.Token;
import java.util.Arrays;
import kotlin.text.Typography;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes5.dex */
public final class Tokeniser {
    private static final char[] notCharRefCharsSorted = null;
    static final int[] win1252Extensions = null;
    Token.Character charPending;
    private StringBuilder charsBuilder;
    private String charsString;
    private final int[] codepointHolder;
    Token.Comment commentPending;
    StringBuilder dataBuffer;
    Token.Doctype doctypePending;
    private Token emitPending;
    Token.EndTag endPending;
    private final ParseErrorList errors;
    private boolean isEmitPending;
    private String lastStartTag;
    private final int[] multipointHolder;
    private final CharacterReader reader;
    Token.StartTag startPending;
    private TokeniserState state;
    Token.Tag tagPending;

    static {
        char[] r0 = {'\t', '\n', '\r', '\f', ' ', Typography.less, Typography.amp};
        notCharRefCharsSorted = r0;
        win1252Extensions = new int[]{8364, 129, 8218, 402, 8222, 8230, 8224, 8225, 710, 8240, 352, 8249, 338, 141, 381, 143, 144, 8216, 8217, 8220, 8221, 8226, 8211, 8212, 732, 8482, 353, 8250, 339, 157, 382, 376};
        Arrays.sort(r0);
    }

    public Tokeniser(CharacterReader r3, ParseErrorList r4) {
        this.state = TokeniserState.Data;
        this.isEmitPending = false;
        this.charsString = null;
        this.charsBuilder = new StringBuilder(1024);
        this.dataBuffer = new StringBuilder(1024);
        this.startPending = new Token.StartTag();
        this.endPending = new Token.EndTag();
        this.charPending = new Token.Character();
        this.doctypePending = new Token.Doctype();
        this.commentPending = new Token.Comment();
        this.codepointHolder = new int[1];
        this.multipointHolder = new int[2];
        this.reader = r3;
        this.errors = r4;
    }

    private void characterReferenceError(String r4) {
        if (this.errors.canAddError() == false) goto L6;
        this.errors.add(new ParseError(this.reader.pos(), "Invalid character reference: %s", new Object[]{r4}));
        return;
    }

    public void advanceTransition(TokeniserState r2) {
        this.reader.advance();
        this.state = r2;
    }

    public String appropriateEndTagName() {
        return this.lastStartTag;
    }

    public int[] consumeCharacterReference(Character r8, boolean r9) {
        if (this.reader.isEmpty() == false) goto L5;
        return null;
    L5:
        if (r8 == null) goto L10;
        if (r8.charValue() != this.reader.current()) goto L10;
        return null;
    L10:
        if (this.reader.matchesAnySorted(notCharRefCharsSorted) == false) goto L12;
        return null;
    L12:
        int[] r82 = this.codepointHolder;
        this.reader.mark();
        boolean r0 = this.reader.matchConsume("#");
        CharacterReader r2 = this.reader;
        if (r0 == false) goto L49;
        boolean r92 = r2.matchConsumeIgnoreCase("X");
        CharacterReader r02 = this.reader;
        if (r92 == false) goto L17;
        String r03 = r02.consumeHexSequence();
    L19:
        if (r03.length() != 0) goto L23;
        characterReferenceError("numeric reference with no numerals");
        this.reader.rewindToMark();
        return null;
    L23:
        if (this.reader.matchConsume(";") == true) goto L25;
        characterReferenceError("missing semicolon");
    L25:
        if (r92 == false) goto L27;
        int r93 = 16;
    L78:
        int r94 = Integer.valueOf(r03, r93).intValue();     // Catch: NumberFormatException -> L31
    L32:
        if (r94 != (-1)) goto L34;
    L47:
        characterReferenceError("character outside of valid range");
        r82[0] = 65533;
        return r82;
    L34:
        if (r94 < 55296) goto L38;
        if (r94 <= 57343) goto L47;
    L38:
        if (r94 > 1114111) goto L47;
        if (r94 < 128) goto L45;
        int[] r1 = win1252Extensions;
        if (r94 >= (r1.length + 128)) goto L45;
        characterReferenceError("character is not a valid unicode code point");
        r94 = r1[r94 - 128];
    L45:
        r82[0] = r94;
        return r82;
    L31:
        r94 = -1;
        goto L32
    L27:
        r93 = 10;
        goto L78
    L17:
        r03 = r02.consumeDigitSequence();
        goto L19
    L49:
        String r04 = r2.consumeLetterThenDigitSequence();
        boolean r22 = this.reader.matches(';');
        if (CommonMarkEntities.isNamedEntity(r04) == false) goto L74;
        if (r22 == false) goto L74;
        if (r9 == false) goto L62;
        if (this.reader.matchesLetter() == false) goto L56;
    L59:
        this.reader.rewindToMark();
        return null;
    L56:
        if (this.reader.matchesDigit() == true) goto L59;
        if (this.reader.matchesAny(new char[]{SignatureVisitor.INSTANCEOF, SignatureVisitor.SUPER, '_'}) == true) goto L59;
    L62:
        if (this.reader.matchConsume(";") == true) goto L64;
        characterReferenceError("missing semicolon");
    L64:
        int r95 = CommonMarkEntities.codepointsForName(r04, this.multipointHolder);
        if (r95 != 1) goto L69;
        r82[0] = this.multipointHolder[0];
        return r82;
    L69:
        if (r95 == 2) goto L71;
        Validate.fail("Unexpected characters returned for " + r04);
        return this.multipointHolder;
    L71:
        return this.multipointHolder;
    L74:
        this.reader.rewindToMark();
        if (r22 == false) goto L77;
        characterReferenceError(String.format("invalid named referenece '%s'", new Object[]{r04}));
    L77:
        return null;
    }

    public void createCommentPending() {
        this.commentPending.reset();
    }

    public void createDoctypePending() {
        this.doctypePending.reset();
    }

    public Token.Tag createTagPending(boolean r1) {
        if (r1 == false) goto L4;
        Token.Tag r12 = this.startPending.reset();
    L5:
        this.tagPending = r12;
        return r12;
    L4:
        r12 = this.endPending.reset();
        goto L5
    }

    public void createTempBuffer() {
        Token.reset(this.dataBuffer);
    }

    public void emit(char r1) {
        emit(String.valueOf(r1));
    }

    public void emit(Token r3) {
        Validate.isFalse(this.isEmitPending, "There is an unread token pending!");
        this.emitPending = r3;
        this.isEmitPending = true;
        Token.TokenType r0 = r3.type;
        if (r0 != Token.TokenType.StartTag) goto L7;
        this.lastStartTag = ((Token.StartTag) r3).tagName;
        return;
    L7:
        if (r0 == Token.TokenType.EndTag) goto L9;
        return;
    L9:
        if (((Token.EndTag) r3).attributes == null) goto L13;
        error("Attributes incorrectly present on end tag");
        return;
    }

    public void emit(String r3) {
        if (this.charsString != null) goto L7;
        this.charsString = r3;
        return;
    L7:
        if (this.charsBuilder.length() != 0) goto L9;
        this.charsBuilder.append(this.charsString);
    L9:
        this.charsBuilder.append(r3);
    }

    public void emit(int[] r4) {
        emit(new String(r4, 0, r4.length));
    }

    public void emitCommentPending() {
        emit(this.commentPending);
    }

    public void emitDoctypePending() {
        emit(this.doctypePending);
    }

    public void emitTagPending() {
        this.tagPending.finaliseTag();
        emit(this.tagPending);
    }

    public void eofError(TokeniserState r4) {
        if (this.errors.canAddError() == false) goto L6;
        this.errors.add(new ParseError(this.reader.pos(), "Unexpectedly reached end of file (EOF) in input state [%s]", new Object[]{r4}));
        return;
    }

    public void error(TokeniserState r4) {
        if (this.errors.canAddError() == false) goto L6;
        this.errors.add(new ParseError(this.reader.pos(), "Unexpected character '%s' in input state [%s]", new Object[]{Character.valueOf(this.reader.current()), r4}));
        return;
    }

    public void error(String r3) {
        if (this.errors.canAddError() == false) goto L6;
        this.errors.add(new ParseError(this.reader.pos(), r3));
        return;
    }

    public boolean isAppropriateEndTagToken() {
        if (this.lastStartTag != null) goto L5;
        return false;
    L5:
        if (this.tagPending.name().equalsIgnoreCase(this.lastStartTag) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public Token read() {
    L3:
        if (this.isEmitPending == true) goto L6;
        this.state.read(this, this.reader);
        goto L3
    L6:
        if (this.charsBuilder.length() <= 0) goto L9;
        String r0 = this.charsBuilder.toString();
        StringBuilder r3 = this.charsBuilder;
        r3.delete(0, r3.length());
        this.charsString = null;
        return this.charPending.data(r0);
    L9:
        String r02 = this.charsString;
        if (r02 == null) goto L13;
        Token.Character r03 = this.charPending.data(r02);
        this.charsString = null;
        return r03;
    L13:
        this.isEmitPending = false;
        return this.emitPending;
    }

    public void transition(TokeniserState r1) {
        this.state = r1;
    }
}
