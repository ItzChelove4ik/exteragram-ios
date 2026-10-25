package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class XMLResourceParseException extends ResourceParseException {
    private static final long serialVersionUID = 2091302779298293946L;

    public XMLResourceParseException(String r1) {
        super(r1);
    }

    public XMLResourceParseException(String r2, String r3) {
        super(r2 + ": the required <" + r3 + ">-element is not found!");
    }

    public XMLResourceParseException(String r2, String r3, String r4, String r5) {
        StringBuilder r0 = new StringBuilder();
        r0.append(r2);
        r0.append(": invalid <");
        r0.append(r3);
        r0.append(">-element found: attribute '");
        r0.append(r4);
        r0.append("' ");
        if (r5 != null) goto L5;
        r5 = "is required!";
    L5:
        r0.append(r5);
        super(r0.toString());
    }

    public XMLResourceParseException(String r2, String r3, String r4, String r5, Throwable r6) {
        StringBuilder r0 = new StringBuilder();
        r0.append(r2);
        r0.append(": invalid <");
        r0.append(r3);
        r0.append(">-element found: attribute '");
        r0.append(r4);
        r0.append("' ");
        if (r5 != null) goto L5;
        r5 = "is required!";
    L5:
        r0.append(r5);
        super(r0.toString(), r6);
    }

    public XMLResourceParseException(String r1, Throwable r2) {
        super(r1, r2);
    }
}
