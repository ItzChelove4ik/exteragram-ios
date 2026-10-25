package io.noties.markwon.html;

/* loaded from: classes5.dex */
public class HtmlEmptyTagReplacement {
    public HtmlEmptyTagReplacement() {
    }

    public static HtmlEmptyTagReplacement create() {
        return new HtmlEmptyTagReplacement();
    }

    public String replace(HtmlTag r2) {
        String r1 = r2.name();
        if ("br".equals(r1) == false) goto L7;
        return "\n";
    L7:
        if ("img".equals(r1) == false) goto L17;
        String r12 = r2.attributes().get("alt");
        if (r12 != null) goto L11;
        return "￼";
    L11:
        if (r12.length() == 0) goto L22;
        return r12;
    L22:
        return "￼";
    L17:
        if ("iframe".equals(r1) == false) goto L20;
        return " ";
    L20:
        return null;
    }
}
