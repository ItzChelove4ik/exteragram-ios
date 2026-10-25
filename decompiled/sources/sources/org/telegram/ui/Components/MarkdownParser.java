package org.telegram.ui.Components;

import android.text.TextUtils;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.utils.MarkdownUtils;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.MediaTrack;
import io.noties.markwon.MarkwonPlugin;
import io.noties.markwon.ext.latex.JLatexMathBlock;
import io.noties.markwon.ext.latex.JLatexMathNode;
import io.noties.markwon.ext.latex.JLatexMathPlugin;
import io.noties.markwon.html.HtmlTag;
import io.noties.markwon.html.MarkwonHtmlParser;
import io.noties.markwon.html.MarkwonHtmlParserImpl;
import io.noties.markwon.inlineparser.InlineProcessor;
import io.noties.markwon.inlineparser.MarkwonInlineParserPlugin;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.strikethrough.Strikethrough;
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TableBody;
import org.commonmark.ext.gfm.tables.TableCell;
import org.commonmark.ext.gfm.tables.TableHead;
import org.commonmark.ext.gfm.tables.TableRow;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.BlockQuote;
import org.commonmark.node.BulletList;
import org.commonmark.node.Code;
import org.commonmark.node.CustomBlock;
import org.commonmark.node.CustomNode;
import org.commonmark.node.Document;
import org.commonmark.node.Emphasis;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.node.HardLineBreak;
import org.commonmark.node.Heading;
import org.commonmark.node.HtmlBlock;
import org.commonmark.node.HtmlInline;
import org.commonmark.node.Image;
import org.commonmark.node.IndentedCodeBlock;
import org.commonmark.node.Link;
import org.commonmark.node.ListItem;
import org.commonmark.node.Node;
import org.commonmark.node.OrderedList;
import org.commonmark.node.Paragraph;
import org.commonmark.node.SoftLineBreak;
import org.commonmark.node.StrongEmphasis;
import org.commonmark.node.ThematicBreak;
import org.commonmark.parser.Parser;
import org.scilab.forge.jlatexmath.TeXSymbolParser;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.iv.Latex;

/* loaded from: classes7.dex */
public abstract class MarkdownParser {
    private static final Pattern FOOTNOTE_DEF = null;
    private static final Pattern FOOTNOTE_REF = null;
    private static final Pattern ORDERED_MARKER = null;

    /* renamed from: org.telegram.ui.Components.MarkdownParser$1, reason: invalid class name */
    public class AnonymousClass1 implements MarkwonPlugin.Registry {
        final /* synthetic */ MarkwonInlineParserPlugin val$inlinePlugin;

        public AnonymousClass1(MarkwonInlineParserPlugin r1) {
            this.val$inlinePlugin = r1;
        }

        @Override // io.noties.markwon.MarkwonPlugin.Registry
        public <P extends MarkwonPlugin> P require(Class<P> r2) {
            if (r2 == MarkwonInlineParserPlugin.class) goto L5;
            DexMaker$$ExternalSyntheticBUOutline0.m("plugin not registered: ", r2);
            return null;
        L5:
            return this.val$inlinePlugin;
        }
    }

    public static class BlockVisitor extends AbstractVisitor {
        public final ArrayList<TL_iv.PageBlock> blocks;
        private final MarkwonHtmlParser htmlParser;
        private final List<Item> items;
        private final ArrayDeque<String> orderedMarkers;
        private final StringBuilder synth;
        public TL_iv.RichText title;

        public static final class Item {
            final TL_iv.PageBlock block;
            final int end;
            final int start;

            public Item(TL_iv.PageBlock r1, int r2, int r3) {
                this.block = r1;
                this.start = r2;
                this.end = r3;
            }
        }

        public static final class Scope {
            final List<Object> children;
            final int end;
            final int start;
            final HtmlTag.Block tag;

            public Scope(HtmlTag.Block r2, int r3, int r4) {
                this.children = new ArrayList();
                this.tag = r2;
                this.start = r3;
                this.end = r4;
            }
        }

        public static /* synthetic */ int $r8$lambda$rwbGcvNvTfWRyBs26W2mPxgJxss(HtmlTag.Block r2, HtmlTag.Block r3) {
            int r0 = Integer.compare(r2.start(), r3.start());
            if (r0 == 0) goto L6;
            return r0;
        L6:
            return Integer.compare(r3.end(), r2.end());
        }

        public BlockVisitor(ArrayList<TL_iv.PageBlock> r2) {
            this(r2, new ArrayDeque());
        }

        public BlockVisitor(ArrayList<TL_iv.PageBlock> r2, ArrayDeque<String> r3) {
            this.items = new ArrayList();
            this.synth = new StringBuilder();
            this.htmlParser = MarkwonHtmlParserImpl.create();
            this.blocks = r2;
            this.orderedMarkers = r3;
        }

        private TL_iv.pageBlockTable buildTable(TableBlock r6) {
            TL_iv.pageBlockTable r0 = new TL_iv.pageBlockTable();
            r0.bordered = true;
            r0.title = new TL_iv.textEmpty();
            Node r62 = r6.getFirstChild();
        L3:
            if (r62 == null) goto L16;
            boolean r1 = r62 instanceof TableHead;
            if (r1 == false) goto L7;
        L9:
            Node r2 = r62.getFirstChild();
        L10:
            if (r2 == null) goto L15;
            if ((r2 instanceof TableRow) == false) goto L14;
            r0.rows.add(buildTableRow((TableRow) r2, r1));
        L14:
            r2 = r2.getNext();
        L15:
            r62 = r62.getNext();
            goto L3
        L7:
            if ((r62 instanceof TableBody) == true) goto L9;
        L16:
            return r0;
        }

        private TL_iv.pageTableCell buildTableCell(TableCell r3, boolean r4) {
            TL_iv.pageTableCell r2 = new TL_iv.pageTableCell();
            if (r4 == false) goto L5;
        L8:
            boolean r42 = true;
        L9:
            r2.header = r42;
            TableCell.Alignment r43 = r3.getAlignment();
            if (r43 != TableCell.Alignment.CENTER) goto L13;
            r2.align_center = true;
        L15:
            r2.text = MarkdownParser.m11202$$Nest$smfirst(MarkdownParser.m11205$$Nest$smrichTextOf(r3, null));
            r2.flags |= 128;
            return r2;
        L13:
            if (r43 != TableCell.Alignment.RIGHT) goto L15;
            r2.align_right = true;
            goto L15
        L5:
            if (r3.isHeader() == true) goto L8;
            r42 = false;
            goto L9
        }

        private TL_iv.pageTableRow buildTableRow(TableRow r4, boolean r5) {
            TL_iv.pageTableRow r0 = new TL_iv.pageTableRow();
            Node r42 = r4.getFirstChild();
        L3:
            if (r42 == null) goto L8;
            if ((r42 instanceof TableCell) == false) goto L7;
            r0.cells.add(buildTableCell((TableCell) r42, r5));
        L7:
            r42 = r42.getNext();
            goto L3
        L8:
            return r0;
        }

        private void collectText(List<Object> r4, StringBuilder r5) {
            Iterator<Object> r42 = r4.iterator();
        L4:
            if (r42.hasNext() == false) goto L28;
            Object r0 = r42.next();
            if ((r0 instanceof Item) == true) goto L7;
            if ((r0 instanceof Scope) == false) goto L4;
            collectText(((Scope) r0).children, r5);
            goto L4
        L7:
            TL_iv.PageBlock r02 = ((Item) r0).block;
            if ((r02 instanceof TL_iv.pageBlockParagraph) == true) goto L10;
            if ((r02 instanceof TL_iv.pageBlockHeader) == true) goto L16;
            if ((r02 instanceof TL_iv.pageBlockSubheader) == false) goto L4;
            if (r5.length() <= 0) goto L24;
            r5.append('\n');
        L24:
            r5.append(MarkdownParser.richTextToString(((TL_iv.pageBlockSubheader) r02).text));
            goto L4
        L16:
            if (r5.length() <= 0) goto L18;
            r5.append('\n');
        L18:
            r5.append(MarkdownParser.richTextToString(((TL_iv.pageBlockHeader) r02).text));
            goto L4
        L10:
            if (r5.length() <= 0) goto L12;
            r5.append('\n');
        L12:
            r5.append(MarkdownParser.richTextToString(((TL_iv.pageBlockParagraph) r02).text));
            goto L4
        }

        private void emit(TL_iv.PageBlock r4) {
            int r0 = this.synth.length();
            this.synth.append(1);
            this.items.add(new Item(r4, r0, this.synth.length()));
        }

        private static void flattenBlockTags(List<HtmlTag.Block> r1, List<HtmlTag.Block> r2) {
            Iterator<HtmlTag.Block> r12 = r1.iterator();
        L4:
            if (r12.hasNext() == false) goto L6;
            HtmlTag.Block r0 = r12.next();
            r2.add(r0);
            flattenBlockTags(r0.children(), r2);
            goto L4
        }

        private void materialize(List<Object> r2, List<TL_iv.PageBlock> r3) {
            materialize(r2, r3, 0);
        }

        private void materialize(List<Object> r3, List<TL_iv.PageBlock> r4, int r5) {
            Iterator<Object> r32 = r3.iterator();
        L4:
            if (r32.hasNext() == false) goto L13;
            Object r0 = r32.next();
            if ((r0 instanceof Item) == true) goto L7;
            if ((r0 instanceof Scope) == false) goto L4;
            wrapScope((Scope) r0, r4, r5);
            goto L4
        L7:
            TL_iv.PageBlock r02 = ((Item) r0).block;
            if (r02 == null) goto L4;
            r4.add(r02);
            goto L4
        }

        private TL_iv.RichText scopeToRichText(Scope r2) {
            StringBuilder r0 = new StringBuilder();
            collectText(r2.children, r0);
            String r1 = r0.toString().trim();
            if (r1.isEmpty() == false) goto L7;
            return new TL_iv.textEmpty();
        L7:
            return MarkdownParser.m11204$$Nest$smplain(r1);
        }

        private static int stripCheckboxPrefix(Node r7) {
            Node r72 = r7.getFirstChild();
            if ((r72 instanceof Paragraph) == true) goto L5;
            return -1;
        L5:
            Node r73 = r72.getFirstChild();
            if ((r73 instanceof org.commonmark.node.Text) == true) goto L8;
            return -1;
        L8:
            org.commonmark.node.Text r74 = (org.commonmark.node.Text) r73;
            String r0 = r74.getLiteral();
            if (r0 == null) goto L34;
            int r3 = 3;
            if (r0.length() < 3) goto L34;
            int r2 = 0;
            if (r0.charAt(0) != '[') goto L34;
            if (r0.charAt(2) != ']') goto L34;
            char r5 = r0.charAt(1);
            if (r5 == ' ') goto L28;
            if (r5 != 'x') goto L23;
        L26:
            r2 = 1;
            goto L28
        L23:
            if (r5 == 'X') goto L26;
            return -1;
        L28:
            if (r0.length() > 3) goto L30;
        L32:
            r74.setLiteral(r0.substring(r3));
            return r2;
        L30:
            if (r0.charAt(3) != ' ') goto L32;
            r3 = 4;
        L34:
            return -1;
        }

        private static String trimCodeBlock(String r3) {
            if (r3 != null) goto L5;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            int r0 = r3.length();
            if (r0 > 0) goto L8;
        L10:
            if (r0 <= 0) goto L15;
            if (r3.charAt(r0 - 1) != '\r') goto L15;
            r0 = r0 - 1;
        L15:
            return r3.substring(0, r0);
        L8:
            if (r3.charAt(r0 - 1) != '\n') goto L10;
            r0 = r0 - 1;
            goto L10
        }

        private void wrapScope(Scope r9, List<TL_iv.PageBlock> r10, int r11) {
            if (r9.tag.name() != null) goto L5;
            String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        L7:
            if (r11 < 64) goto L11;
            materialize(r9.children, r10, r11 + 1);
            return;
        L11:
            switch(r0.hashCode()) {
                case -1857640538: goto L49;
                case -1268861541: goto L47;
                case -1221270899: goto L46;
                case -732377866: goto L45;
                case 112: goto L44;
                case 99473: goto L43;
                case 108835: goto L42;
                case 3343801: goto L41;
                case 93111608: goto L40;
                case 1557721666: goto L16;
                case 1970241253: goto L13;
                default: goto L51;
            };
        L13:
            String r1 = "section";
        L14:
            r0.equals(r1);
            goto L51
        L40:
            r1 = "aside";
            goto L14
        L41:
            r1 = MediaTrack.ROLE_MAIN;
            goto L14
        L42:
            r1 = "nav";
            goto L14
        L43:
            r1 = "div";
            goto L14
        L44:
            r1 = "p";
            goto L14
        L45:
            r1 = "article";
            goto L14
        L46:
            r1 = PluginsConstants.Settings.TYPE_HEADER;
            goto L14
        L47:
            r1 = "footer";
        L51:
            materialize(r9.children, r10, r11 + 1);
            return;
        L16:
            if (r0.equals("details") == false) goto L51;
            TL_iv.pageBlockDetails r02 = new TL_iv.pageBlockDetails();
            if (r9.tag.attributes() != null) goto L20;
        L22:
            boolean r12 = false;
        L23:
            r02.open = r12;
            r02.title = new TL_iv.textEmpty();
            ArrayList r13 = new ArrayList();
            Iterator<Object> r92 = r9.children.iterator();
        L25:
            if (r92.hasNext() == false) goto L38;
            Object r4 = r92.next();
            boolean r5 = r4 instanceof Scope;
            if (r5 == false) goto L32;
            Scope r6 = (Scope) r4;
            if ("summary".equalsIgnoreCase(r6.tag.name()) == false) goto L32;
            r02.title = scopeToRichText(r6);
        L32:
            if ((r4 instanceof Item) == true) goto L33;
            if (r5 == false) goto L25;
            wrapScope((Scope) r4, r13, r11 + 1);
            goto L25
        L33:
            TL_iv.PageBlock r42 = ((Item) r4).block;
            if (r42 == null) goto L25;
            r13.add(r42);
            goto L25
        L38:
            r02.blocks.addAll(r13);
            r10.add(r02);
            return;
        L20:
            if (r9.tag.attributes().containsKey("open") == false) goto L22;
            r12 = true;
            goto L23
        L49:
            if (r0.equals("summary") == false) goto L51;
            return;
        L5:
            r0 = r9.tag.name().toLowerCase();
            goto L7
        }

        public void finish() {
            ArrayList r0 = new ArrayList();
            this.htmlParser.flushBlockTags(this.synth.length(), new MarkdownParser$BlockVisitor$$ExternalSyntheticLambda0(r0));     // Catch: Throwable -> L5
        L7:
            ArrayList r1 = new ArrayList();
            flattenBlockTags(r0, r1);
            HashMap r02 = new HashMap();
            Iterator<Item> r2 = this.items.iterator();
        L9:
            if (r2.hasNext() == false) goto L11;
            Item r3 = r2.next();
            r02.put(Integer.valueOf(r3.start), r3);
            goto L9
        L11:
            TreeSet r22 = new TreeSet();
            int r32 = 0;
            r22.add(0);
            r22.add(Integer.valueOf(this.synth.length()));
            Iterator r4 = r02.keySet().iterator();
        L13:
            if (r4.hasNext() == false) goto L15;
            Integer r5 = (Integer) r4.next();
            r22.add(r5);
            r22.add(Integer.valueOf(r5.intValue() + 1));
            goto L13
        L15:
            int r42 = r1.size();
            int r52 = 0;
        L16:
            if (r52 >= r42) goto L18;
            Object r7 = r1.get(r52);
            r52 = r52 + 1;
            HtmlTag.Block r72 = (HtmlTag.Block) r7;
            r22.add(Integer.valueOf(r72.start()));
            r22.add(Integer.valueOf(r72.end()));
            goto L16
        L18:
            ArrayList r43 = new ArrayList();
            Iterator r23 = r22.iterator();
            Integer r73 = null;
        L20:
            if (r23.hasNext() == false) goto L34;
            Integer r8 = (Integer) r23.next();
            if (r73 == null) goto L33;
            if (r8.intValue() <= r73.intValue()) goto L33;
            int r9 = r73.intValue();
            int r10 = r8.intValue();
            if ((r10 - r9) == 1) goto L28;
        L30:
            String r74 = this.synth.substring(r9, r10).trim();
            if (r74.isEmpty() == true) goto L33;
            TL_iv.pageBlockParagraph r11 = new TL_iv.pageBlockParagraph();
            r11.text = MarkdownParser.m11202$$Nest$smfirst(MarkdownParser.m11204$$Nest$smplain(r74));
            r43.add(new Item(r11, r9, r10));
            goto L33
        L28:
            if (r02.containsKey(r73) == false) goto L30;
            r43.add((Item) r02.get(r73));
        L33:
            r73 = r8;
            goto L20
        L34:
            Collections.sort(r1, new MarkdownParser$BlockVisitor$$ExternalSyntheticLambda1());
            Scope r03 = new Scope(null, 0, Integer.MAX_VALUE);
            ArrayDeque r24 = new ArrayDeque();
            r24.push(r03);
            int r53 = r43.size();
            int r6 = 0;
        L35:
            if (r6 >= r53) goto L59;
            Object r75 = r43.get(r6);
            r6 = r6 + 1;
            Item r76 = (Item) r75;
        L38:
            if (r32 >= r1.size()) goto L52;
            if (((HtmlTag.Block) r1.get(r32)).start() > r76.start) goto L52;
            int r82 = r32 + 1;
            HtmlTag.Block r33 = (HtmlTag.Block) r1.get(r32);
            if (r33.end() < r76.start) goto L50;
        L45:
            if (r24.peek() == r03) goto L49;
            if (((Scope) r24.peek()).end > r33.start()) goto L49;
            r24.pop();
        L49:
            Scope r92 = new Scope(r33, r33.start(), r33.end());
            ((Scope) r24.peek()).children.add(r92);
            r24.push(r92);
        L50:
            r32 = r82;
        L52:
            if (r24.peek() == r03) goto L57;
            if (((Scope) r24.peek()).end > r76.start) goto L57;
            r24.pop();
        L57:
            if (r76.block == null) goto L35;
            ((Scope) r24.peek()).children.add(r76);
            goto L35
        L59:
            materialize(r03.children, this.blocks);
            return;
        L5:
            th = move-exception;
            FileLog.e(th);
            goto L7
        }

        @Override // org.commonmark.node.Visitor
        public void visit(BlockQuote r3) {
            Iterator r32 = MarkdownParser.m11206$$Nest$smsplit(MarkdownParser.m11205$$Nest$smrichTextOf(r3, null)).iterator();
        L4:
            if (r32.hasNext() == false) goto L6;
            TL_iv.RichText r0 = (TL_iv.RichText) r32.next();
            TL_iv.pageBlockBlockquote r1 = new TL_iv.pageBlockBlockquote();
            r1.text = r0;
            r1.caption = new TL_iv.textEmpty();
            emit(r1);
            goto L4
        }

        @Override // org.commonmark.node.Visitor
        public void visit(BulletList r5) {
            TL_iv.pageBlockList r0 = new TL_iv.pageBlockList();
            Node r52 = r5.getFirstChild();
        L3:
            if (r52 == null) goto L15;
            if ((r52 instanceof ListItem) == false) goto L14;
            int r1 = stripCheckboxPrefix(r52);
            TL_iv.TL_pageListItemText r2 = new TL_iv.TL_pageListItemText();
            if (r1 < 0) goto L13;
            boolean r3 = true;
            r2.checkbox = true;
            if (r1 == 1) goto L12;
            r3 = false;
        L12:
            r2.checked = r3;
        L13:
            r2.text = MarkdownParser.m11202$$Nest$smfirst(MarkdownParser.m11205$$Nest$smrichTextOf(r52, r0));
            r0.items.add(r2);
        L14:
            r52 = r52.getNext();
            goto L3
        L15:
            emit(r0);
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(CustomBlock r2) {
            if ((r2 instanceof TableBlock) == false) goto L7;
            emit(buildTable((TableBlock) r2));
            return;
        L7:
            if ((r2 instanceof JLatexMathBlock) == false) goto L10;
            TL_iv.pageBlockParagraph r0 = new TL_iv.pageBlockParagraph();
            r0.text = MarkdownParser.m11203$$Nest$smmakeLatex(((JLatexMathBlock) r2).latex());
            emit(r0);
            return;
        L10:
            super.visit(r2);
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(FencedCodeBlock r3) {
            TL_iv.pageBlockPreformatted r0 = new TL_iv.pageBlockPreformatted();
            r0.text = MarkdownParser.m11202$$Nest$smfirst(MarkdownParser.m11204$$Nest$smplain(trimCodeBlock(r3.getLiteral())));
            if (r3.getInfo() != null) goto L5;
            String r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            r0.language = r32;
            emit(r0);
            return;
        L5:
            r32 = r3.getInfo();
            goto L6
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(Heading r3) {
            TL_iv.RichText r0 = MarkdownParser.m11202$$Nest$smfirst(MarkdownParser.m11205$$Nest$smrichTextOf(r3, null));
            if (this.items.isEmpty() == false) goto L6;
            this.title = r0;
        L6:
            switch(r3.getLevel()) {
                case 1: goto L19;
                case 2: goto L17;
                case 3: goto L15;
                case 4: goto L13;
                case 5: goto L11;
                case 6: goto L9;
                default: goto L7;
            };
        L7:
            TL_iv.pageBlockHeader r32 = new TL_iv.pageBlockHeader();
            r32.text = r0;
            emit(r32);
            return;
        L9:
            TL_iv.pageBlockHeading6 r33 = new TL_iv.pageBlockHeading6();
            r33.text = r0;
            emit(r33);
            return;
        L11:
            TL_iv.pageBlockHeading5 r34 = new TL_iv.pageBlockHeading5();
            r34.text = r0;
            emit(r34);
            return;
        L13:
            TL_iv.pageBlockHeading4 r35 = new TL_iv.pageBlockHeading4();
            r35.text = r0;
            emit(r35);
            return;
        L15:
            TL_iv.pageBlockHeading3 r36 = new TL_iv.pageBlockHeading3();
            r36.text = r0;
            emit(r36);
            return;
        L17:
            TL_iv.pageBlockHeading2 r37 = new TL_iv.pageBlockHeading2();
            r37.text = r0;
            emit(r37);
            return;
        L19:
            TL_iv.pageBlockHeading1 r38 = new TL_iv.pageBlockHeading1();
            r38.text = r0;
            emit(r38);
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(HtmlBlock r3) {
            String r32 = r3.getLiteral();
            if (r32 != null) goto L10;
            return;
        L10:
            this.htmlParser.processFragment(this.synth, r32);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            FileLog.e(th);
            this.synth.append(r32);
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(IndentedCodeBlock r2) {
            TL_iv.pageBlockPreformatted r0 = new TL_iv.pageBlockPreformatted();
            r0.text = MarkdownParser.m11202$$Nest$smfirst(MarkdownParser.m11204$$Nest$smplain(trimCodeBlock(r2.getLiteral())));
            r0.language = _UrlKt.FRAGMENT_ENCODE_SET;
            emit(r0);
        }

        @Override // org.commonmark.node.Visitor
        public void visit(OrderedList r9) {
            TL_iv.pageBlockOrderedList r0 = new TL_iv.pageBlockOrderedList();
            boolean r1 = r9.getParent() instanceof Document;
            int r2 = r9.getStartNumber();
            Node r92 = r9.getFirstChild();
        L3:
            if (r92 == null) goto L20;
            if ((r92 instanceof ListItem) == false) goto L19;
            if (r1 == true) goto L8;
        L10:
            int r3 = r2 + 1;
            String r32 = String.valueOf(r2);
            r2 = r3;
        L11:
            int r4 = stripCheckboxPrefix(r92);
            TL_iv.TL_pageListOrderedItemText r5 = new TL_iv.TL_pageListOrderedItemText();
            if (r4 < 0) goto L18;
            boolean r6 = true;
            r5.checkbox = true;
            if (r4 == 1) goto L17;
            r6 = false;
        L17:
            r5.checked = r6;
        L18:
            r5.num = r32;
            r5.text = MarkdownParser.m11202$$Nest$smfirst(MarkdownParser.m11205$$Nest$smrichTextOf(r92, r0));
            r0.items.add(r5);
            goto L19
        L8:
            if (this.orderedMarkers.isEmpty() == true) goto L10;
            r32 = this.orderedMarkers.poll();
        L19:
            r92 = r92.getNext();
            goto L3
        L20:
            emit(r0);
        }

        @Override // org.commonmark.node.Visitor
        public void visit(Paragraph r3) {
            Iterator r32 = MarkdownParser.m11206$$Nest$smsplit(MarkdownParser.m11205$$Nest$smrichTextOf(r3, null)).iterator();
        L4:
            if (r32.hasNext() == false) goto L6;
            TL_iv.RichText r0 = (TL_iv.RichText) r32.next();
            TL_iv.pageBlockParagraph r1 = new TL_iv.pageBlockParagraph();
            r1.text = r0;
            emit(r1);
            goto L4
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(ThematicBreak r1) {
            emit(new TL_iv.pageBlockDivider());
        }
    }

    public static class RichTextParser extends AbstractVisitor {
        private final TL_iv.PageBlock block;
        private int blockDepth;
        private TL_iv.textConcat current;

        public RichTextParser(TL_iv.PageBlock r2) {
            this.current = new TL_iv.textConcat();
            this.block = r2;
        }

        private void append(TL_iv.RichText r1) {
            this.current.texts.add(r1);
        }

        private static TL_iv.RichText collapse(TL_iv.textConcat r2) {
            if (r2.texts.isEmpty() == false) goto L7;
            return new TL_iv.textEmpty();
        L7:
            if (r2.texts.size() == 1) goto L9;
            return r2;
        L9:
            return r2.texts.get(0);
        }

        private TL_iv.RichText collectChildren(Node r3) {
            TL_iv.textConcat r0 = this.current;
            this.current = new TL_iv.textConcat();
            visitChildren(r3);
            TL_iv.RichText r32 = collapse(this.current);
            this.current = r0;
            return r32;
        }

        public TL_iv.RichText getText() {
            return collapse(this.current);
        }

        @Override // org.commonmark.node.Visitor
        public void visit(BlockQuote r3) {
            int r0 = this.blockDepth;
            if (r0 < 64) goto L5;
            return;
        L5:
            this.blockDepth = r0 + 1;
            visitChildren(r3);     // Catch: Throwable -> L9
            this.blockDepth--;
            return;
        L9:
            th = move-exception;
            this.blockDepth--;
            throw th;
        }

        @Override // org.commonmark.node.Visitor
        public void visit(BulletList r3) {
            int r0 = this.blockDepth;
            if (r0 < 64) goto L5;
            return;
        L5:
            this.blockDepth = r0 + 1;
            visitChildren(r3);     // Catch: Throwable -> L9
            this.blockDepth--;
            return;
        L9:
            th = move-exception;
            this.blockDepth--;
            throw th;
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(Code r2) {
            TL_iv.textFixed r0 = new TL_iv.textFixed();
            r0.text = MarkdownParser.m11204$$Nest$smplain(r2.getLiteral());
            append(r0);
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(CustomBlock r3) {
            if ((r3 instanceof JLatexMathBlock) == true) goto L5;
            super.visit(r3);
            return;
        L5:
            if (this.current.texts.isEmpty() == true) goto L7;
            append(MarkdownParser.m11204$$Nest$smplain("\n"));
        L7:
            append(MarkdownParser.m11203$$Nest$smmakeLatex(((JLatexMathBlock) r3).latex()));
            append(MarkdownParser.m11204$$Nest$smplain("\n"));
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(CustomNode r2) {
            if ((r2 instanceof Strikethrough) == false) goto L7;
            TL_iv.textStrike r0 = new TL_iv.textStrike();
            r0.text = collectChildren(r2);
            append(r0);
            return;
        L7:
            if ((r2 instanceof JLatexMathNode) == false) goto L10;
            append(MarkdownParser.m11203$$Nest$smmakeLatex(((JLatexMathNode) r2).latex()));
            return;
        L10:
            super.visit(r2);
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(Emphasis r2) {
            TL_iv.textItalic r0 = new TL_iv.textItalic();
            r0.text = collectChildren(r2);
            append(r0);
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(HardLineBreak r1) {
            append(MarkdownParser.m11204$$Nest$smplain("\n"));
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(HtmlInline r1) {
            append(MarkdownParser.m11204$$Nest$smplain(r1.getLiteral()));
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(Image r1) {
            append(collectChildren(r1));
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(Link r3) {
            if (r3.getDestination() != null) goto L5;
            String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            String r02 = r0.trim();
            if (r02.startsWith("mailto:") == false) goto L11;
            TL_iv.RichText r1 = new TL_iv.textEmail();
            r1.text = collectChildren(r3);
            r1.email = r02.substring(7);
            append(r1);
            return;
        L11:
            if (r02.startsWith("tel:") == false) goto L14;
            TL_iv.textPhone r12 = new TL_iv.textPhone();
            r12.text = collectChildren(r3);
            r12.phone = r02.substring(4);
            append(r12);
            return;
        L14:
            TL_iv.RichText r13 = new TL_iv.textUrl();
            r13.text = collectChildren(r3);
            r13.url = r02;
            append(r13);
            return;
        L5:
            r0 = r3.getDestination();
            goto L6
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(ListItem r3) {
            int r0 = this.blockDepth;
            if (r0 < 64) goto L5;
            return;
        L5:
            this.blockDepth = r0 + 1;
            visitChildren(r3);     // Catch: Throwable -> L9
            this.blockDepth--;
            return;
        L9:
            th = move-exception;
            this.blockDepth--;
            throw th;
        }

        @Override // org.commonmark.node.Visitor
        public void visit(OrderedList r3) {
            int r0 = this.blockDepth;
            if (r0 < 64) goto L5;
            return;
        L5:
            this.blockDepth = r0 + 1;
            visitChildren(r3);     // Catch: Throwable -> L9
            this.blockDepth--;
            return;
        L9:
            th = move-exception;
            this.blockDepth--;
            throw th;
        }

        @Override // org.commonmark.node.Visitor
        public void visit(Paragraph r2) {
            if (this.current.texts.isEmpty() == true) goto L5;
            append(MarkdownParser.m11204$$Nest$smplain("\n\n"));
        L5:
            visitChildren(r2);
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(SoftLineBreak r1) {
            if ((this.block instanceof TL_iv.pageBlockBlockquote) == false) goto L5;
            String r12 = "\n";
        L6:
            append(MarkdownParser.m11204$$Nest$smplain(r12));
            return;
        L5:
            r12 = " ";
            goto L6
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(StrongEmphasis r2) {
            TL_iv.textBold r0 = new TL_iv.textBold();
            r0.text = collectChildren(r2);
            append(r0);
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(org.commonmark.node.Text r1) {
            append(MarkdownParser.m11204$$Nest$smplain(r1.getLiteral()));
        }
    }

    public static class SingleDollarLatexInlineProcessor extends InlineProcessor {
        private static final Pattern RE = null;

        static {
            RE = Pattern.compile("\\$([^\\s\\$][^\\$]*?)(?<!\\s)\\$(?![0-9])");
        }

        public SingleDollarLatexInlineProcessor() {
        }

        @Override // io.noties.markwon.inlineparser.InlineProcessor
        public Node parse() {
            String r3 = match(RE);
            if (r3 != null) goto L6;
            return null;
        L6:
            JLatexMathNode r0 = new JLatexMathNode();
            r0.latex(r3.substring(1, r3.length() - 1));
            return r0;
        }

        @Override // io.noties.markwon.inlineparser.InlineProcessor
        public char specialCharacter() {
            return Typography.dollar;
        }
    }

    public static final class TextStyle extends TL_iv.RichText {
        int styleFlags;

        private TextStyle() {
        }

        public /* synthetic */ TextStyle(MarkdownParserIA r1) {
            this();
        }
    }

    /* renamed from: $r8$lambda$GhuTps9siHk-oCSVBi7SubkxSTM, reason: not valid java name */
    public static /* synthetic */ void m11198$r8$lambda$GhuTps9siHkoCSVBi7SubkxSTM(List r0, List r1) {
        flattenBlocks(r1, r0);
    }

    /* renamed from: $r8$lambda$QHYxxwDqb0-HAzzPzCH9WFjGn0k, reason: not valid java name */
    public static /* synthetic */ void m11199$r8$lambda$QHYxxwDqb0HAzzPzCH9WFjGn0k(JLatexMathPlugin.Builder r1) {
        r1.inlinesEnabled(true);
    }

    /* renamed from: $r8$lambda$TSB6PtQnqwUe3b0sV6pwV-mIyyw, reason: not valid java name */
    public static /* synthetic */ int m11200$r8$lambda$TSB6PtQnqwUe3b0sV6pwVmIyyw(HtmlTag r1) {
        return r1.end() - r1.start();
    }

    /* renamed from: $r8$lambda$YT1EE50xPZAomEnl-EOulk5FKJk, reason: not valid java name */
    public static /* synthetic */ void m11201$r8$lambda$YT1EE50xPZAomEnlEOulk5FKJk(List r0, List r1) {
        r0.addAll(r1);
    }

    /* renamed from: -$$Nest$smfirst, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_iv.RichText m11202$$Nest$smfirst(TL_iv.RichText r0) {
        return first(r0);
    }

    /* renamed from: -$$Nest$smmakeLatex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_iv.textMath m11203$$Nest$smmakeLatex(String r0) {
        return makeLatex(r0);
    }

    /* renamed from: -$$Nest$smplain, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_iv.RichText m11204$$Nest$smplain(String r0) {
        return plain(r0);
    }

    /* renamed from: -$$Nest$smrichTextOf, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_iv.RichText m11205$$Nest$smrichTextOf(Node r0, TL_iv.PageBlock r1) {
        return richTextOf(r0, r1);
    }

    /* renamed from: -$$Nest$smsplit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m11206$$Nest$smsplit(TL_iv.RichText r0) {
        return split(r0);
    }

    static {
        FOOTNOTE_DEF = Pattern.compile("^\\[\\^([^\\]]+)\\]:[ \\t]*(.*)$");
        FOOTNOTE_REF = Pattern.compile("\\[\\^([^\\]]+)\\]");
        ORDERED_MARKER = Pattern.compile("^(\\d+)[.)]\\s");
    }

    private static void appendFootnotes(Parser r6, ArrayList<TL_iv.PageBlock> r7, LinkedHashMap<String, String> r8) {
        if (r8.isEmpty() == false) goto L5;
        return;
    L5:
        TL_iv.pageBlockDetails r0 = new TL_iv.pageBlockDetails();
        r0.title = bold(LocaleController.getString(R.string.InstantViewReferences));
        Iterator<Map.Entry<String, String>> r82 = r8.entrySet().iterator();
    L7:
        if (r82.hasNext() == false) goto L9;
        Map.Entry<String, String> r1 = r82.next();
        String r2 = r1.getKey();
        String r12 = r1.getValue();
        ArrayList r3 = new ArrayList();
        BlockVisitor r4 = new BlockVisitor(r3);
        r6.parse(r12).accept(r4);
        r4.finish();
        TL_iv.RichText r13 = first(combineParagraphs(r3));
        TL_iv.textAnchor r32 = new TL_iv.textAnchor();
        r32.name = "fn-" + r2;
        r32.text = r13;
        TL_iv.pageBlockParagraph r14 = new TL_iv.pageBlockParagraph();
        r14.text = concat(new TL_iv.RichText[]{bold(r2 + ". "), r32});
        r0.blocks.add(r14);
        goto L7
    L9:
        r7.add(r0);
    }

    private static TL_iv.RichText bold(String r1) {
        TL_iv.textBold r0 = new TL_iv.textBold();
        r0.text = plain(r1);
        return r0;
    }

    private static TL_iv.RichText combineParagraphs(ArrayList<TL_iv.PageBlock> r7) {
        TL_iv.textConcat r0 = new TL_iv.textConcat();
        int r1 = r7.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L26;
        TL_iv.PageBlock r4 = r7.get(r3);
        r3 = r3 + 1;
        TL_iv.PageBlock r42 = r4;
        if ((r42 instanceof TL_iv.pageBlockParagraph) == false) goto L8;
        TL_iv.RichText r43 = ((TL_iv.pageBlockParagraph) r42).text;
    L17:
        if (r43 == null) goto L3;
        if ((r43 instanceof TL_iv.textEmpty) == true) goto L3;
        if (r0.texts.isEmpty() == true) goto L24;
        r0.texts.add(plain("\n\n"));
    L24:
        r0.texts.add(r43);
        goto L3
    L8:
        if ((r42 instanceof TL_iv.pageBlockHeader) == false) goto L11;
        r43 = ((TL_iv.pageBlockHeader) r42).text;
        goto L17
    L11:
        if ((r42 instanceof TL_iv.pageBlockSubheader) == false) goto L14;
        r43 = ((TL_iv.pageBlockSubheader) r42).text;
        goto L17
    L14:
        if ((r42 instanceof TL_iv.pageBlockTitle) == false) goto L16;
        r43 = ((TL_iv.pageBlockTitle) r42).text;
        goto L17
    L16:
        r43 = null;
        goto L17
    L26:
        if (r0.texts.isEmpty() == false) goto L30;
        return new TL_iv.textEmpty();
    L30:
        if (r0.texts.size() == 1) goto L32;
        return r0;
    L32:
        return r0.texts.get(0);
    }

    private static TL_iv.RichText concat(TL_iv.RichText... r5) {
        TL_iv.textConcat r0 = new TL_iv.textConcat();
        int r1 = r5.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        TL_iv.RichText r3 = r5[r2];
        r0.texts.add(r3);
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    private static String extractFootnoteDefs(String r11, LinkedHashMap<String, String> r12) {
        String[] r112 = r11.split("\n", -1);
        StringBuilder r0 = new StringBuilder();
        int r1 = 0;
    L4:
        if (r1 >= r112.length) goto L42;
        Matcher r2 = FOOTNOTE_DEF.matcher(r112[r1]);
        if (r2.matches() == true) goto L7;
        r0.append(r112[r1]);
        if (r1 >= (r112.length - 1)) goto L40;
        r0.append('\n');
    L40:
        r1 = r1 + 1;
        goto L4
    L7:
        String r3 = r2.group(1);
        StringBuilder r6 = new StringBuilder(r2.group(2));
    L8:
        r1 = r1 + 1;
    L10:
        if (r1 >= r112.length) goto L36;
        String r22 = r112[r1];
        if (r22.startsWith("    ") == true) goto L31;
        if (r22.startsWith("\t") == true) goto L31;
        if (r22.trim().isEmpty() == false) goto L36;
        int r23 = r1 + 1;
        int r8 = r23;
    L20:
        if (r8 >= r112.length) goto L25;
        if (r112[r8].trim().isEmpty() == false) goto L25;
        r8 = r8 + 1;
    L25:
        if (r8 >= r112.length) goto L36;
        if (r112[r8].startsWith("    ") == true) goto L30;
        if (r112[r8].startsWith("\t") == false) goto L36;
    L30:
        r6.append('\n');
        r1 = r23;
    L31:
        r6.append('\n');
        if (r22.startsWith("\t") == false) goto L34;
        String r24 = r22.substring(1);
    L35:
        r6.append(r24);
        goto L8
    L34:
        r24 = r22.substring(4);
    L36:
        r12.put(r3, r6.toString().trim());
        goto L4
    L42:
        return r0.toString();
    }

    private static TL_iv.RichText first(TL_iv.RichText r2) {
        if (r2 != null) goto L6;
        return null;
    L6:
        if (richTextLength(r2) > 8192) goto L8;
        return r2;
    L8:
        String r22 = richTextToString(r2);
        return plain(r22.substring(0, Math.min(r22.length(), 8192)));
    }

    private static int flagFor(String r6) {
        if (r6 != null) goto L5;
        return 0;
    L5:
        String r62 = r6.toLowerCase();
        r62.getClass();
        char r5 = 65535;
        switch(r62.hashCode()) {
            case -891985998: goto L61;
            case -891980137: goto L57;
            case 98: goto L53;
            case 105: goto L49;
            case 115: goto L45;
            case 117: goto L41;
            case 3240: goto L37;
            case 3712: goto L33;
            case 99339: goto L29;
            case 104430: goto L25;
            case 114240: goto L21;
            case 114254: goto L17;
            case 3059181: goto L13;
            case 3344077: goto L9;
            default: goto L64;
        };
    L64:
        switch(r5) {
            case 0: goto L77;
            case 1: goto L76;
            case 2: goto L76;
            case 3: goto L75;
            case 4: goto L77;
            case 5: goto L73;
            case 6: goto L75;
            case 7: goto L72;
            case 8: goto L77;
            case 9: goto L73;
            case 10: goto L70;
            case 11: goto L68;
            case 12: goto L72;
            case 13: goto L66;
            default: goto L65;
        };
    L65:
        return 0;
    L66:
        return 64;
    L68:
        return 256;
    L70:
        return 128;
    L72:
        return 4;
    L73:
        return 16;
    L75:
        return 2;
    L76:
        return 1;
    L77:
        return 32;
    L9:
        if (r62.equals("mark") == false) goto L64;
        r5 = '\r';
        goto L64
    L13:
        if (r62.equals("code") == false) goto L64;
        r5 = '\f';
        goto L64
    L17:
        if (r62.equals("sup") == false) goto L64;
        r5 = 11;
        goto L64
    L21:
        if (r62.equals("sub") == false) goto L64;
        r5 = '\n';
        goto L64
    L25:
        if (r62.equals("ins") == false) goto L64;
        r5 = '\t';
        goto L64
    L29:
        if (r62.equals(TeXSymbolParser.DELIMITER_ATTR) == false) goto L64;
        r5 = '\b';
        goto L64
    L33:
        if (r62.equals("tt") == false) goto L64;
        r5 = 7;
        goto L64
    L37:
        if (r62.equals("em") == false) goto L64;
        r5 = 6;
        goto L64
    L41:
        if (r62.equals("u") == false) goto L64;
        r5 = 5;
        goto L64
    L45:
        if (r62.equals("s") == false) goto L64;
        r5 = 4;
        goto L64
    L49:
        if (r62.equals("i") == false) goto L64;
        r5 = 3;
        goto L64
    L53:
        if (r62.equals("b") == false) goto L64;
        r5 = 2;
        goto L64
    L57:
        if (r62.equals("strong") == false) goto L64;
        r5 = 1;
        goto L64
    L61:
        if (r62.equals("strike") == false) goto L64;
        r5 = 0;
        goto L64
    }

    private static void flattenBlocks(List<HtmlTag.Block> r1, List<HtmlTag> r2) {
        Iterator<HtmlTag.Block> r12 = r1.iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        HtmlTag.Block r0 = r12.next();
        r2.add(r0);
        flattenBlocks(r0.children(), r2);
        goto L4
    }

    public static TLRPC.WebPage fromMarkdown(MessageObject r8) {
        if (r8 != null) goto L5;
    L66:
        return null;
    L5:
        if (r8.messageOwner == null) goto L66;
        TLRPC.Document r1 = r8.getDocument();
        if (r1 != null) goto L11;
        return null;
    L11:
        if (TextUtils.isEmpty(r8.messageOwner.attachPath) == true) goto L13;
        File r2 = new File(r8.messageOwner.attachPath);
    L15:
        if (r2 != null) goto L17;
    L18:
        r2 = FileLoader.getInstance(r8.currentAccount).getPathToMessage(r8.messageOwner, true);
    L19:
        if (r2 != null) goto L21;
    L22:
        r2 = FileLoader.getInstance(r8.currentAccount).getPathToMessage(r8.messageOwner, true, true);
    L23:
        if (r2 == null) goto L66;
        if (r2.exists() == false) goto L66;
        if (r2.length() <= MediaStatus.COMMAND_FOLLOW) goto L30;
        return null;
    L30:
        String r12 = FileLoader.getDocumentFileName(r1);
        TLRPC.TL_webPage r3 = new TLRPC.TL_webPage();
        String r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r12 != null) goto L33;
        String r5 = _UrlKt.FRAGMENT_ENCODE_SET;
    L34:
        r3.url = r5;
        if (r12 == null) goto L38;
        r4 = r12;
    L38:
        r3.display_url = r4;
        if (TextUtils.isEmpty(r12) == true) goto L41;
        r3.flags |= 4;
        r3.title = r12;
    L41:
        TL_iv.TL_page r42 = new TL_iv.TL_page();
        r42.local = r2;
        r42.url = r3.url;
        FileInputStream r52 = new FileInputStream(r2);     // Catch: Exception -> L52
        byte[] r22 = new byte[(int) r2.length()];     // Catch: Throwable -> L59
        r52.read(r22);     // Catch: Throwable -> L59
        String r6 = new String(r22, StandardCharsets.UTF_8);     // Catch: Throwable -> L59
        r52.close();     // Catch: Exception -> L52
        if (r6.length() <= 65536) goto L47;
        return null;
    L47:
        String r82 = MarkdownUtils.getPreformattedLanguage(r12, r8.getExtension(), r8.getMimeType());     // Catch: Exception -> L52
        boolean r13 = TextUtils.isEmpty(r82);     // Catch: Exception -> L52
        ArrayList<TL_iv.PageBlock> r23 = r42.blocks;
        if (r13 == false) goto L51;
        String r83 = parse(r6, r23);     // Catch: Exception -> L52
        if (TextUtils.isEmpty(r83) == true) goto L57;
        r3.flags |= 4;
        r3.title = r83;     // Catch: Exception -> L52
    L57:
        r3.flags |= 1024;
        r3.cached_page = r42;
        return r3;
    L51:
        MarkdownUtils.appendPreformattedBlocks(r23, r6, r82, 8192);     // Catch: Exception -> L52
        goto L57
    L59:
        th = move-exception;
        r52.close();     // Catch: Throwable -> L62
    L64:
        throw th;     // Catch: Exception -> L52
    L62:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L52
    L52:
        e = move-exception;
        FileLog.e(e);
        goto L66
    L33:
        r5 = r12;
        goto L34
    L21:
        if (r2.exists() == true) goto L23;
    L17:
        if (r2.exists() == true) goto L19;
    L13:
        r2 = null;
        goto L15
    }

    public static boolean isExtensionMarkdown(String r1) {
        if ("md".equalsIgnoreCase(r1) == false) goto L5;
        return true;
    L5:
        if ("mkd".equalsIgnoreCase(r1) == false) goto L7;
        return true;
    L7:
        if ("mdwn".equalsIgnoreCase(r1) == false) goto L9;
        return true;
    L9:
        if ("mkdn".equalsIgnoreCase(r1) == false) goto L11;
        return true;
    L11:
        if ("mdown".equalsIgnoreCase(r1) == false) goto L13;
        return true;
    L13:
        if ("markdown".equalsIgnoreCase(r1) == true) goto L23;
        return false;
    L23:
        return true;
    }

    public static boolean isMarkdown(MessageObject r2) {
        if (r2 != null) goto L5;
    L17:
        return false;
    L5:
        if (MarkdownUtils.isTheme(r2) == true) goto L17;
        if (isExtensionMarkdown(r2.getExtension()) == false) goto L10;
        return true;
    L10:
        if (isMimeMarkdown(r2.getMimeType()) == false) goto L12;
        return true;
    L12:
        if (MarkdownUtils.isExteraMarkdown(r2) == true) goto L19;
        return false;
    L19:
        return true;
    }

    public static boolean isMimeMarkdown(String r2) {
        if (r2 != null) goto L5;
        return false;
    L5:
        String r22 = r2.toLowerCase();
        if (r22.startsWith("text/markdown") == false) goto L8;
        return true;
    L8:
        if (r22.startsWith("text/x-markdown") == false) goto L10;
        return true;
    L10:
        if (r22.startsWith("text/x-web-markdown") == true) goto L16;
        return false;
    L16:
        return true;
    }

    private static boolean looksLikeHtmlTag(String r3) {
        if (r3 != null) goto L5;
    L12:
        return false;
    L5:
        if (r3.length() < 2) goto L12;
        if (r3.charAt(0) != '<') goto L12;
        if (r3.charAt(r3.length() - 1) != '>') goto L12;
        return true;
    }

    private static TL_iv.textMath makeLatex(String r3) {
        TL_iv.textMath r0 = new TL_iv.textMath();
        if (r3 != null) goto L5;
        String r32 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r0.source = r32;
        r0.tried = true;
        Latex r33 = Latex.render(r32, AndroidUtilities.dp(20.0f), true);
        if (r33 == null) goto L9;
        r0.w = r33.width;
        r0.h = r33.height;
        r0.depth = r33.depth;
        r0.bitmap = r33.bitmap;
    L9:
        return r0;
    L5:
        r32 = r3.trim();
        goto L6
    }

    private static TL_iv.RichText materializeStyles(TL_iv.RichText r3) {
        if (r3 != null) goto L6;
        return null;
    L6:
        if ((r3 instanceof TL_iv.textConcat) == false) goto L13;
        TL_iv.textConcat r32 = (TL_iv.textConcat) r3;
        int r0 = 0;
    L9:
        if (r0 >= r32.texts.size()) goto L11;
        ArrayList<TL_iv.RichText> r1 = r32.texts;
        r1.set(r0, materializeStyles(r1.get(r0)));
        r0 = r0 + 1;
        goto L9
    L11:
        return r32;
    L13:
        if ((r3 instanceof TextStyle) == false) goto L40;
        TextStyle r33 = (TextStyle) r3;
        TL_iv.RichText r02 = materializeStyles(r33.text);
        int r34 = r33.styleFlags;
        if ((r34 & 4) == 0) goto L18;
        r02 = wrapStyle(new TL_iv.textFixed(), r02);
    L18:
        if ((r34 & 32) == 0) goto L21;
        r02 = wrapStyle(new TL_iv.textStrike(), r02);
    L21:
        if ((r34 & 16) == 0) goto L24;
        r02 = wrapStyle(new TL_iv.textUnderline(), r02);
    L24:
        if ((r34 & 64) == 0) goto L27;
        r02 = wrapStyle(new TL_iv.textMarked(), r02);
    L27:
        if ((r34 & 128) == 0) goto L30;
        r02 = wrapStyle(new TL_iv.textSubscript(), r02);
    L30:
        if ((r34 & 256) == 0) goto L33;
        r02 = wrapStyle(new TL_iv.textSuperscript(), r02);
    L33:
        if ((r34 & 2) == 0) goto L36;
        r02 = wrapStyle(new TL_iv.textItalic(), r02);
    L36:
        if ((r34 & 1) != 0) goto L38;
        return r02;
    L38:
        return wrapStyle(new TL_iv.textBold(), r02);
    L40:
        TL_iv.RichText r03 = r3.text;
        if (r03 == null) goto L43;
        r3.text = materializeStyles(r03);
    L43:
        return r3;
    }

    private static TL_iv.RichText pairHtml(TL_iv.RichText r3) {
        if (r3 != null) goto L6;
        return null;
    L6:
        if ((r3 instanceof TL_iv.textConcat) == false) goto L13;
        TL_iv.textConcat r32 = (TL_iv.textConcat) r3;
        int r0 = 0;
    L9:
        if (r0 >= r32.texts.size()) goto L12;
        ArrayList<TL_iv.RichText> r1 = r32.texts;
        r1.set(r0, pairHtml(r1.get(r0)));
        r0 = r0 + 1;
        goto L9
    L12:
        return pairHtmlConcat(r32);
    L13:
        TL_iv.RichText r02 = r3;
    L14:
        if (r02 == null) goto L22;
        TL_iv.RichText r12 = r02.text;
        if (r12 == null) goto L22;
        if ((r12 instanceof TL_iv.textConcat) == true) goto L19;
        r02 = r12;
        goto L14
    L19:
        r02.text = pairHtml(r12);
        return r3;
    L22:
        return r3;
    }

    private static TL_iv.RichText pairHtmlConcat(TL_iv.textConcat r15) {
        StringBuilder r0 = new StringBuilder();
        ArrayList r1 = new ArrayList();
        ArrayList r2 = new ArrayList();
        MarkwonHtmlParserImpl r3 = MarkwonHtmlParserImpl.create();
        ArrayList<TL_iv.RichText> r152 = r15.texts;
        int r4 = r152.size();
        int r6 = 0;
    L3:
        if (r6 >= r4) goto L17;
        TL_iv.RichText r7 = r152.get(r6);
        r6 = r6 + 1;
        TL_iv.RichText r72 = r7;
        if ((r72 instanceof TL_iv.textPlain) == false) goto L16;
        TL_iv.textPlain r8 = (TL_iv.textPlain) r72;
        if (looksLikeHtmlTag(r8.text) == false) goto L16;
        int r9 = r0.length();
        r3.processFragment(r0, ((TL_iv.textPlain) r72).text);     // Catch: Throwable -> L11
    L13:
        int r73 = r0.length();
        if (r73 <= r9) goto L3;
        r1.add(plain(r0.substring(r9, r73)));
        r2.add(new int[]{r9, r73});
    L11:
        th = move-exception;
        FileLog.e(th);
        r0.append(r8.text);
    L16:
        String r82 = richTextToString(r72);
        int r92 = r0.length();
        r0.append(r82);
        int r83 = r0.length();
        r1.add(r72);
        r2.add(new int[]{r92, r83});
        goto L3
    L17:
        ArrayList r153 = new ArrayList();
        r3.flushInlineTags(r0.length(), new MarkdownParser$$ExternalSyntheticLambda1(r153));     // Catch: Throwable -> L20
    L70:
        r3.flushBlockTags(r0.length(), new MarkdownParser$$ExternalSyntheticLambda2(r153));     // Catch: Throwable -> L24
    L26:
        Collections.sort(r153, Comparator.comparingInt(new MarkdownParser$$ExternalSyntheticLambda3()));
        int r02 = r153.size();
        int r32 = 0;
    L28:
        if (r32 >= r02) goto L54;
        Object r62 = r153.get(r32);
        r32 = r32 + 1;
        HtmlTag r63 = (HtmlTag) r62;
        if (r63.isClosed() == false) goto L28;
        int r74 = r63.start();
        int r84 = r63.end();
        int r10 = 0;
        int r11 = -1;
        int r12 = -1;
    L34:
        if (r10 >= r2.size()) goto L42;
        int r13 = ((int[]) r2.get(r10))[0];
        int r14 = ((int[]) r2.get(r10))[1];
        if (r13 < r74) goto L41;
        if (r14 > r84) goto L41;
        if (r11 != (-1)) goto L40;
        r11 = r10;
    L40:
        r12 = r10;
    L41:
        r10 = r10 + 1;
        goto L34
    L42:
        if (r11 == (-1)) goto L28;
        if (r11 != r12) goto L46;
        TL_iv.RichText r42 = (TL_iv.RichText) r1.get(r11);
    L49:
        TL_iv.RichText r43 = wrapByTag(r63.name(), r42);
    L50:
        if (r12 < r11) goto L52;
        r1.remove(r12);
        r2.remove(r12);
        r12 = r12 - 1;
        goto L50
    L52:
        r1.add(r11, r43);
        r2.add(r11, new int[]{r74, r84});
        goto L28
    L46:
        r42 = new TL_iv.textConcat();
        int r93 = r11;
    L47:
        if (r93 > r12) goto L49;
        r42.texts.add((TL_iv.RichText) r1.get(r93));
        r93 = r93 + 1;
        goto L47
    L54:
        if (r1.isEmpty() == false) goto L58;
        return new TL_iv.textEmpty();
    L58:
        if (r1.size() != 1) goto L64;
        TL_iv.RichText r154 = (TL_iv.RichText) r1.get(0);
        if ((r154 instanceof TL_iv.textPlain) == false) goto L62;
    L63:
        return r154;
    L62:
        if ((r154 instanceof TL_iv.textEmpty) == true) goto L63;
    L64:
        TL_iv.textConcat r155 = new TL_iv.textConcat();
        r155.texts.addAll(r1);
        return r155;
    L24:
        th = move-exception;
        FileLog.e(th);
    L20:
        th = move-exception;
        FileLog.e(th);
        goto L70
    }

    public static String parse(String r6, ArrayList<TL_iv.PageBlock> r7) {
        LinkedHashMap r0 = new LinkedHashMap();
        String r62 = rewriteFootnoteRefs(extractFootnoteDefs(r6, r0));
        List r1 = Arrays.asList(new Extension[]{StrikethroughExtension.create(), TablesExtension.create()});
        MarkwonInlineParserPlugin r2 = MarkwonInlineParserPlugin.create();
        JLatexMathPlugin r3 = JLatexMathPlugin.create(AndroidUtilities.dp(18.0f), new MarkdownParser$$ExternalSyntheticLambda0());
        r3.configure(new AnonymousClass1(r2));
        r2.factoryBuilder().addInlineProcessor(new SingleDollarLatexInlineProcessor());
        Parser.Builder r12 = Parser.builder().extensions(r1);
        r2.configureParser(r12);
        r3.configureParser(r12);
        Parser r13 = r12.build();
        BlockVisitor r32 = new BlockVisitor(r7, scanOrderedListMarkers(r62));
        r13.parse(r62).accept(r32);
        r32.finish();
        appendFootnotes(r13, r7, r0);
        TL_iv.RichText r63 = r32.title;
        if (r63 != null) goto L5;
        return null;
    L5:
        return richTextToString(r63);
    }

    private static TL_iv.RichText plain(String r1) {
        TL_iv.textPlain r0 = new TL_iv.textPlain();
        if (r1 != null) goto L5;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        r0.text = r1;
        return r0;
    }

    private static String rewriteFootnoteRefs(String r4) {
        Matcher r42 = FOOTNOTE_REF.matcher(r4);
        StringBuffer r0 = new StringBuffer();
    L4:
        if (r42.find() == false) goto L6;
        String r1 = r42.group(1);
        r42.appendReplacement(r0, Matcher.quoteReplacement("<sup>[\\[" + r1 + "\\]](#fn-" + r1 + ")</sup>"));
        goto L4
    L6:
        r42.appendTail(r0);
        return r0.toString();
    }

    private static int richTextLength(TL_iv.RichText r4) {
        int r0 = 0;
        if (r4 != null) goto L5;
    L22:
        return 0;
    L5:
        if ((r4 instanceof TL_iv.textEmpty) == true) goto L22;
        if ((r4 instanceof TL_iv.textPlain) == false) goto L15;
        String r42 = ((TL_iv.textPlain) r4).text;
        if (r42 != null) goto L13;
        return 0;
    L13:
        return r42.length();
    L15:
        if ((r4 instanceof TL_iv.textConcat) == false) goto L21;
        ArrayList<TL_iv.RichText> r43 = r4.texts;
        int r1 = r43.size();
        int r2 = 0;
    L17:
        if (r2 >= r1) goto L19;
        TL_iv.RichText r3 = r43.get(r2);
        r2 = r2 + 1;
        r0 = r0 + richTextLength(r3);
        goto L17
    L19:
        return r0;
    L21:
        return richTextLength(r4.text);
    }

    private static TL_iv.RichText richTextOf(Node r1, TL_iv.PageBlock r2) {
        RichTextParser r0 = new RichTextParser(r2);
        r1.accept(r0);
        return materializeStyles(pairHtml(r0.getText()));
    }

    public static String richTextToString(TL_iv.RichText r4) {
        if (r4 != null) goto L4;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L4:
        if ((r4 instanceof TL_iv.textEmpty) == false) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        if ((r4 instanceof TL_iv.textPlain) == false) goto L11;
        return ((TL_iv.textPlain) r4).text;
    L11:
        if ((r4 instanceof TL_iv.textConcat) == false) goto L18;
        StringBuilder r0 = new StringBuilder();
        ArrayList<TL_iv.RichText> r42 = r4.texts;
        int r1 = r42.size();
        int r2 = 0;
    L13:
        if (r2 >= r1) goto L16;
        TL_iv.RichText r3 = r42.get(r2);
        r2 = r2 + 1;
        r0.append(richTextToString(r3));
        goto L13
    L16:
        return r0.toString();
    L18:
        return richTextToString(r4.text);
    }

    private static ArrayDeque<String> scanOrderedListMarkers(String r12) {
        ArrayDeque<String> r0 = new ArrayDeque();
        String[] r122 = r12.split("\n", -1);
        int r1 = r122.length;
        int r4 = 0;
        boolean r5 = false;
        String r6 = null;
    L3:
        if (r4 >= r1) goto L27;
        String r7 = r122[r4];
        int r8 = 0;
    L6:
        if (r8 >= r7.length()) goto L12;
        if (r8 >= 3) goto L12;
        if (r7.charAt(r8) != ' ') goto L12;
        r8 = r8 + 1;
    L12:
        String r82 = r7.substring(r8);
        if (r5 == true) goto L15;
        String r9 = "```";
        if (r82.startsWith("```") == false) goto L20;
    L19:
        r6 = r9;
        r5 = true;
    L26:
        r4 = r4 + 1;
        goto L3
    L20:
        r9 = "~~~";
        if (r82.startsWith("~~~") == true) goto L19;
        Matcher r72 = ORDERED_MARKER.matcher(r7);
        if (r72.find() == false) goto L26;
        r0.add(r72.group(1));
        goto L26
    L15:
        if (r82.startsWith(r6) == false) goto L26;
        r5 = false;
        r6 = null;
        goto L26
    L27:
        return r0;
    }

    private static List<TL_iv.RichText> split(TL_iv.RichText r7) {
        if (r7 != null) goto L6;
        return Collections.singletonList(plain(_UrlKt.FRAGMENT_ENCODE_SET));
    L6:
        if (richTextLength(r7) <= 8192) goto L8;
        String r72 = richTextToString(r7);
        ArrayList r0 = new ArrayList();
        int r3 = 0;
    L11:
        if (r3 >= r72.length()) goto L23;
        if ((r72.length() - r3) <= 8192) goto L14;
        int r4 = r3 + 8192;
        int r5 = r3 + 8191;
        int r6 = r72.lastIndexOf(10, r5);
        if (r6 > r3) goto L19;
        r6 = r72.lastIndexOf(32, r5);
    L19:
        if (r6 > r3) goto L21;
        int r52 = 0;
    L22:
        r0.add(plain(r72.substring(r3, r4)));
        r3 = r4 + r52;
        goto L11
    L21:
        r52 = 1;
        r4 = r6;
        goto L22
    L14:
        r0.add(plain(r72.substring(r3)));
        return r0;
    L23:
        return r0;
    L8:
        return Collections.singletonList(r7);
    }

    private static TL_iv.RichText wrapByTag(String r2, TL_iv.RichText r3) {
        int r22 = flagFor(r2);
        if (r22 != 0) goto L6;
        return r3;
    L6:
        if ((r3 instanceof TextStyle) == false) goto L9;
        TextStyle r0 = (TextStyle) r3;
        r0.styleFlags = r22 | r0.styleFlags;
        return r3;
    L9:
        TextStyle r02 = new TextStyle(null);
        r02.styleFlags = r22;
        r02.text = r3;
        return r02;
    }

    private static TL_iv.RichText wrapStyle(TL_iv.RichText r0, TL_iv.RichText r1) {
        r0.text = r1;
        return r0;
    }
}
