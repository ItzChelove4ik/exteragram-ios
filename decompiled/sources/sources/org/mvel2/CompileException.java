package org.mvel2;

import java.util.Collections;
import java.util.List;
import org.mvel2.util.ParseTools;
import org.mvel2.util.StringAppender;

/* loaded from: classes5.dex */
public class CompileException extends RuntimeException {
    private int column;
    private int cursor;
    private List<ErrorDetail> errors;
    private Object evaluationContext;
    private char[] expr;
    private int lastLineStart;
    private int lineNumber;
    private int msgOffset;

    public CompileException(String r1, List<ErrorDetail> r2, char[] r3, int r4, ParserContext r5) {
        super(r1);
        this.msgOffset = 0;
        this.lineNumber = 1;
        this.column = 0;
        this.lastLineStart = 0;
        this.expr = r3;
        this.cursor = r4;
        if (r2.isEmpty() == true) goto L5;
        ErrorDetail r12 = r2.iterator().next();
        this.cursor = r12.getCursor();
        this.lineNumber = r12.getLineNumber();
        this.column = r12.getColumn();
    L5:
        this.errors = r2;
    }

    public CompileException(String r2, char[] r3, int r4) {
        super(r2);
        this.msgOffset = 0;
        this.lineNumber = 1;
        this.column = 0;
        this.lastLineStart = 0;
        this.expr = r3;
        this.cursor = r4;
    }

    public CompileException(String r1, char[] r2, int r3, Throwable r4) {
        super(r1, r4);
        this.msgOffset = 0;
        this.lineNumber = 1;
        this.column = 0;
        this.lastLineStart = 0;
        this.expr = r2;
        this.cursor = r3;
    }

    private void calcRowAndColumn() {
        int r0 = this.lineNumber;
        if (r0 > 1) goto L33;
        int r2 = this.column;
        if (r2 > 1) goto L36;
        if (r0 == 0) goto L9;
        if (r2 == 0) goto L9;
        return;
    L9:
        char[] r02 = this.expr;
        if (r02 != null) goto L12;
        return;
    L12:
        if (r02.length == 0) goto L37;
        int r03 = 0;
        int r22 = 1;
        int r3 = 1;
    L16:
        if (r03 >= this.cursor) goto L26;
        char[] r4 = this.expr;
        if (r03 >= r4.length) goto L26;
        char r42 = r4[r03];
        if (r42 != '\n') goto L22;
        r22 = r22 + 1;
        r3 = 1;
    L25:
        r03 = r03 + 1;
        goto L16
    L22:
        if (r42 == '\r') goto L25;
        r3 = r3 + 1;
    L26:
        this.lineNumber = r22;
        this.column = r3;
        return;
    L37:
        return;
    L36:
        return;
    }

    private String generateErrorMessage() {
        StringAppender r0 = new StringAppender().append("[Error: " + super.getMessage() + "]\n");
        int r1 = r0.length();
        r0.append("[Near : {... ");
        r0.append(showCodeNearError(this.expr, this.cursor)).append(" ....}]\n").append(ParseTools.repeatChar(' ', r0.length() - r1));
        if (this.msgOffset >= 0) goto L5;
        this.msgOffset = 0;
    L5:
        r0.append(ParseTools.repeatChar(' ', this.msgOffset)).append('^');
        calcRowAndColumn();
        if (this.evaluationContext == null) goto L9;
        r0.append("\n").append("In ").append(this.evaluationContext);
    L12:
        return r0.toString();
    L9:
        if (this.lineNumber == (-1)) goto L12;
        r0.append("\n").append("[Line: " + this.lineNumber + ", Column: " + this.column + "]");
        goto L12
    }

    private CharSequence showCodeNearError(char[] r9, int r10) {
        if (r9 != null) goto L6;
        return "Unknown";
    L6:
        if (r10 >= 0) goto L8;
        r10 = 0;
    L8:
        int r1 = r10 - 20;
        int r2 = r10 + 30;
        if (r2 <= r9.length) goto L11;
        r2 = r9.length;
        r1 = r10 - 50;
    L11:
        if (r1 >= 0) goto L13;
        r1 = 0;
    L13:
        String r12 = String.copyValueOf(r9, r1, r2 - r1).trim();
        if (r10 >= r2) goto L33;
        int r22 = r10;
        if (r10 <= 0) goto L21;
    L17:
        if (r22 <= 0) goto L21;
        if (ParseTools.isWhitespace(r9[r22 - 1]) == true) goto L21;
        r22 = r22 - 1;
    L21:
        int r4 = r10 - r22;
        String r5 = new String(r9, r22, r9.length - r22);
        int r92 = 0;
    L23:
        if (r92 >= r5.length()) goto L31;
        char r23 = r5.charAt(r92);
        if (r23 == '\n') goto L29;
        if (r23 == ')') goto L29;
        r92 = r92 + 1;
    L29:
        r5 = r5.substring(0, r92);
    L31:
        if (r5.length() < 30) goto L34;
        r5 = r5.substring(0, 30);
    L34:
        int r93 = r12.indexOf(10);
        int r24 = r12.lastIndexOf(10);
        if (r93 == (-1)) goto L36;
        if (r5 != null) goto L47;
        int r7 = 0;
    L48:
        if (r93 == (-1)) goto L55;
        if (r93 != r24) goto L55;
        if (r93 > r7) goto L51;
        if (r93 >= r7) goto L34;
        String r94 = r12.substring(r93 + 1, r12.length());
    L52:
        r12 = r94;
        goto L34
    L51:
        r94 = r12.substring(0, r93);
    L55:
        if (r93 >= r7) goto L57;
        r94 = r12.substring(r93 + 1, r24);
        goto L52
    L57:
        r94 = r12.substring(0, r93);
        goto L52
    L47:
        r7 = r12.indexOf(r5);
        goto L48
    L36:
        String r95 = r12.trim();
        if (r5 == null) goto L39;
        this.msgOffset = r95.indexOf(r5) + r4;
    L41:
        if (this.msgOffset != 0) goto L44;
        if (r4 != 0) goto L44;
        this.msgOffset = r10;
    L44:
        return r95;
    L39:
        this.msgOffset = r12.length() - (r12.length() - r95.length());
        goto L41
    L33:
        r5 = null;
        r4 = 0;
        goto L34
    }

    public CharSequence getCodeNearError() {
        return showCodeNearError(this.expr, this.cursor);
    }

    public int getColumn() {
        return this.column;
    }

    public int getCursor() {
        return this.cursor;
    }

    public int getCursorOffet() {
        return this.msgOffset;
    }

    public List<ErrorDetail> getErrors() {
        List<ErrorDetail> r0 = this.errors;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return Collections.EMPTY_LIST;
    }

    public char[] getExpr() {
        return this.expr;
    }

    public int getLastLineStart() {
        return this.lastLineStart;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return generateErrorMessage();
    }

    public void setColumn(int r1) {
        this.column = r1;
    }

    public void setCursor(int r1) {
        this.cursor = r1;
    }

    public void setErrors(List<ErrorDetail> r1) {
        this.errors = r1;
    }

    public void setEvaluationContext(Object r1) {
        this.evaluationContext = r1;
    }

    public void setExpr(char[] r1) {
        this.expr = r1;
    }

    public void setLastLineStart(int r1) {
        this.lastLineStart = r1;
    }

    public void setLineNumber(int r1) {
        this.lineNumber = r1;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return generateErrorMessage();
    }
}
