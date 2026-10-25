package org.mvel2;

/* loaded from: classes5.dex */
public class PropertyAccessException extends CompileException {
    public PropertyAccessException(String r1, char[] r2, int r3, Throwable r4, ParserContext r5) {
        super(r1, r2, r3, r4);
        setParserContext(r5);
    }

    public PropertyAccessException(String r1, char[] r2, int r3, ParserContext r4) {
        super(r1, r2, r3);
        setParserContext(r4);
    }

    private void setParserContext(ParserContext r1) {
        if (r1 == null) goto L5;
        setEvaluationContext(r1.getEvaluationContext());
        return;
    }
}
