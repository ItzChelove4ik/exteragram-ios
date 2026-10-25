package io.noties.markwon.inlineparser;

import java.util.regex.Pattern;
import org.commonmark.internal.Bracket;
import org.commonmark.internal.Delimiter;
import org.commonmark.node.Node;
import org.commonmark.node.Text;

/* loaded from: classes5.dex */
public abstract class InlineProcessor {
    protected Node block;
    protected MarkwonInlineParserContext context;
    protected int index;
    protected String input;

    public InlineProcessor() {
    }

    public void addBracket(Bracket r1) {
        this.context.addBracket(r1);
    }

    public Bracket lastBracket() {
        return this.context.lastBracket();
    }

    public Delimiter lastDelimiter() {
        return this.context.lastDelimiter();
    }

    public String match(Pattern r3) {
        this.context.setIndex(this.index);
        String r32 = this.context.match(r3);
        this.index = this.context.index();
        return r32;
    }

    public abstract Node parse();

    public Node parse(MarkwonInlineParserContext r2) {
        this.context = r2;
        this.block = r2.block();
        this.input = r2.input();
        this.index = r2.index();
        Node r0 = parse();
        r2.setIndex(this.index);
        return r0;
    }

    public String parseLinkDestination() {
        this.context.setIndex(this.index);
        String r0 = this.context.parseLinkDestination();
        this.index = this.context.index();
        return r0;
    }

    public int parseLinkLabel() {
        this.context.setIndex(this.index);
        int r0 = this.context.parseLinkLabel();
        this.index = this.context.index();
        return r0;
    }

    public String parseLinkTitle() {
        this.context.setIndex(this.index);
        String r0 = this.context.parseLinkTitle();
        this.index = this.context.index();
        return r0;
    }

    public char peek() {
        this.context.setIndex(this.index);
        return this.context.peek();
    }

    public void processDelimiters(Delimiter r3) {
        this.context.setIndex(this.index);
        this.context.processDelimiters(r3);
        this.index = this.context.index();
    }

    public void removeLastBracket() {
        this.context.removeLastBracket();
    }

    public abstract char specialCharacter();

    public void spnl() {
        this.context.setIndex(this.index);
        this.context.spnl();
        this.index = this.context.index();
    }

    public Text text(String r1) {
        return this.context.text(r1);
    }

    public Text text(String r1, int r2, int r3) {
        return this.context.text(r1, r2, r3);
    }
}
