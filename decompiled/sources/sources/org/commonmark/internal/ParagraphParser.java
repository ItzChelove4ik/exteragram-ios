package org.commonmark.internal;

import java.util.List;
import org.commonmark.node.Block;
import org.commonmark.node.LinkReferenceDefinition;
import org.commonmark.node.Paragraph;
import org.commonmark.parser.InlineParser;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.ParserState;

/* loaded from: classes5.dex */
public class ParagraphParser extends AbstractBlockParser {
    private final Paragraph block;
    private LinkReferenceDefinitionParser linkReferenceDefinitionParser;

    public ParagraphParser() {
        this.block = new Paragraph();
        this.linkReferenceDefinitionParser = new LinkReferenceDefinitionParser();
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void addLine(CharSequence r1) {
        this.linkReferenceDefinitionParser.parse(r1);
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public boolean canHaveLazyContinuationLines() {
        return true;
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void closeBlock() {
        if (this.linkReferenceDefinitionParser.getParagraphContent().length() != 0) goto L6;
        this.block.unlink();
        return;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    public CharSequence getContentString() {
        return this.linkReferenceDefinitionParser.getParagraphContent();
    }

    public List<LinkReferenceDefinition> getDefinitions() {
        return this.linkReferenceDefinitionParser.getDefinitions();
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void parseInlines(InlineParser r3) {
        CharSequence r0 = this.linkReferenceDefinitionParser.getParagraphContent();
        if (r0.length() <= 0) goto L6;
        r3.parse(r0.toString(), this.block);
        return;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState r1) {
        if (r1.isBlank() == true) goto L7;
        return BlockContinue.atIndex(r1.getIndex());
    L7:
        return BlockContinue.none();
    }
}
