package org.commonmark.parser.delimiter;

import org.commonmark.node.Text;

/* loaded from: classes5.dex */
public interface DelimiterProcessor {
    char getClosingCharacter();

    int getDelimiterUse(DelimiterRun r1, DelimiterRun r2);

    int getMinLength();

    char getOpeningCharacter();

    void process(Text r1, Text r2, int r3);
}
