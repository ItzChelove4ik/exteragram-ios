package io.noties.markwon.inlineparser;

import java.util.regex.Pattern;
import org.commonmark.internal.Bracket;
import org.commonmark.internal.Delimiter;
import org.commonmark.node.LinkReferenceDefinition;
import org.commonmark.node.Node;
import org.commonmark.node.Text;

/* loaded from: classes5.dex */
public interface MarkwonInlineParserContext {
    void addBracket(Bracket r1);

    Node block();

    LinkReferenceDefinition getLinkReferenceDefinition(String r1);

    int index();

    String input();

    Bracket lastBracket();

    Delimiter lastDelimiter();

    String match(Pattern r1);

    String parseLinkDestination();

    int parseLinkLabel();

    String parseLinkTitle();

    char peek();

    void processDelimiters(Delimiter r1);

    void removeLastBracket();

    void setIndex(int r1);

    void spnl();

    Text text(String r1);

    Text text(String r1, int r2, int r3);
}
