package com.google.gson;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public class FormattingStyle {
    public static final FormattingStyle COMPACT = null;
    public static final FormattingStyle PRETTY = null;
    private final String indent;
    private final String newline;
    private final boolean spaceAfterSeparators;

    static {
        COMPACT = new FormattingStyle(_UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, false);
        PRETTY = new FormattingStyle("\n", "  ", true);
    }

    private FormattingStyle(String r3, String r4, boolean r5) {
        Objects.requireNonNull(r3, "newline == null");
        Objects.requireNonNull(r4, "indent == null");
        if (r3.matches("[\r\n]*") == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Only combinations of \\n and \\r are allowed in newline.");
        throw null;
    L5:
        if (r4.matches("[ \t]*") == false) goto L8;
        this.newline = r3;
        this.indent = r4;
        this.spaceAfterSeparators = r5;
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("Only combinations of spaces and tabs are allowed in indent.");
        throw null;
    }

    public String getIndent() {
        return this.indent;
    }

    public String getNewline() {
        return this.newline;
    }

    public boolean usesSpaceAfterSeparators() {
        return this.spaceAfterSeparators;
    }
}
