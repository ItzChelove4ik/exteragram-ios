package org.telegram.ui.iv;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.scilab.forge.jlatexmath.TeXSymbolParser;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.URLSpanReplacement;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes7.dex */
public abstract class RichHtml {

    public static class ListState {
        final ArrayList<Boolean> stack;

        private ListState() {
            this.stack = new ArrayList();
        }

        public /* synthetic */ ListState(RichHtmlIA r1) {
            this();
        }

        private void close(StringBuilder r2) {
            if (this.stack.remove(r1.size() - 1).booleanValue() == false) goto L5;
            String r1 = "</ol>";
        L6:
            r2.append(r1);
            return;
        L5:
            r1 = "</ul>";
            goto L6
        }

        private void open(StringBuilder r2, boolean r3) {
            if (r3 == false) goto L4;
            String r0 = "<ol>";
        L5:
            r2.append(r0);
            this.stack.add(Boolean.valueOf(r3));
            return;
        L4:
            r0 = "<ul>";
            goto L5
        }

        public void closeAll(StringBuilder r2) {
        L3:
            if (this.stack.isEmpty() == true) goto L5;
            close(r2);
            goto L3
        }

        public void sync(StringBuilder r2, int r3, boolean r4) {
        L3:
            if (this.stack.size() <= r3) goto L6;
            close(r2);
        L6:
            if (this.stack.size() >= r3) goto L9;
            open(r2, r4);
            goto L6
        L9:
            if (this.stack.isEmpty() == false) goto L11;
            return;
        L11:
            if (this.stack.get(this.stack.size() - 1).booleanValue() == r4) goto L17;
            close(r2);
            open(r2, r4);
            return;
        }
    }

    public static class Node {
        Map<String, String> attrs;
        final ArrayList<Node> children;
        boolean isText;
        String tag;
        String text;

        private Node() {
            this.children = new ArrayList();
        }

        public static Node el(String r1) {
            Node r0 = new Node();
            r0.tag = r1;
            return r0;
        }

        public static Node text(String r2) {
            Node r0 = new Node();
            r0.isText = true;
            r0.text = r2;
            return r0;
        }

        public String attr(String r1) {
            Map<String, String> r0 = this.attrs;
            if (r0 != null) goto L7;
            return null;
        L7:
            return r0.get(r1);
        }

        public boolean has(String r1) {
            Map<String, String> r0 = this.attrs;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.containsKey(r1) == false) goto L10;
            return true;
        L10:
            return false;
        }
    }

    public static class Parser {
        int p;
        final String s;

        public Parser(String r1) {
            if (r1 != null) goto L5;
            r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            this.s = r1;
        }

        private void addChild(ArrayList<Node> r1, ArrayList<Node> r2, Node r3) {
            if (r1.isEmpty() == false) goto L6;
            r2.add(r3);
            return;
        L6:
            r1.get(r1.size() - 1).children.add(r3);
        }

        private void addText(ArrayList<Node> r2, ArrayList<Node> r3, String r4) {
            if (r4.isEmpty() == false) goto L5;
            return;
        L5:
            addChild(r2, r3, Node.text(r4));
        }

        private void closeTag(ArrayList<Node> r2, String r3) {
            int r1 = r2.size() - 1;
        L3:
            if (r1 < 0) goto L10;
            if (r2.get(r1).tag.equals(r3) == true) goto L7;
            r1 = r1 - 1;
        L7:
            if (r2.size() <= r1) goto L14;
            r2.remove(r2.size() - 1);
            goto L7
        L14:
            return;
        }

        private int findTagEnd(int r7) {
            int r72 = r7 + 1;
            boolean r2 = false;
            char r3 = 0;
        L4:
            if (r72 >= this.s.length()) goto L19;
            char r4 = this.s.charAt(r72);
            if (r2 == false) goto L10;
            if (r4 != r3) goto L18;
            r2 = false;
        L18:
            r72 = r72 + 1;
            goto L4
        L10:
            if (r4 != '\"') goto L12;
        L17:
            r2 = true;
            r3 = r4;
            goto L18
        L12:
            if (r4 == '\'') goto L17;
            if (r4 != '>') goto L18;
            return r72;
        L19:
            return -1;
        }

        private boolean isSpace(char r1) {
            if (r1 != ' ') goto L5;
            return true;
        L5:
            if (r1 != '\t') goto L7;
            return true;
        L7:
            if (r1 != '\n') goto L9;
            return true;
        L9:
            if (r1 != '\r') goto L11;
            return true;
        L11:
            if (r1 == '\f') goto L20;
            return false;
        L20:
            return true;
        }

        private Node parseTag(String r8) {
            String r82 = r8.trim();
            if (r82.isEmpty() == false) goto L5;
            return null;
        L5:
            int r2 = 0;
        L7:
            if (r2 >= r82.length()) goto L11;
            if (isSpace(r82.charAt(r2)) == true) goto L11;
            r2 = r2 + 1;
        L11:
            String r0 = r82.substring(0, r2).toLowerCase();
            if (r0.isEmpty() == false) goto L14;
            return null;
        L14:
            Node r02 = Node.el(r0);
        L16:
            if (r2 >= r82.length()) goto L79;
        L18:
            if (r2 >= r82.length()) goto L23;
            if (isSpace(r82.charAt(r2)) == false) goto L23;
            r2 = r2 + 1;
        L23:
            if (r2 >= r82.length()) goto L79;
            int r1 = r2;
        L27:
            if (r1 >= r82.length()) goto L33;
            if (r82.charAt(r1) == '=') goto L33;
            if (isSpace(r82.charAt(r1)) == true) goto L33;
            r1 = r1 + 1;
        L33:
            String r22 = r82.substring(r2, r1).toLowerCase();
        L35:
            if (r1 >= r82.length()) goto L40;
            if (isSpace(r82.charAt(r1)) == false) goto L40;
            r1 = r1 + 1;
        L40:
            if (r1 < r82.length()) goto L42;
        L71:
            int r3 = r1;
            String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        L73:
            if (r22.isEmpty() == true) goto L78;
            if (r02.attrs != null) goto L77;
            r02.attrs = new HashMap();
        L77:
            r02.attrs.put(r22, RichHtml.m21868$$Nest$smdecode(r12));
        L78:
            r2 = r3;
            goto L16
        L42:
            if (r82.charAt(r1) != '=') goto L71;
        L43:
            r1 = r1 + 1;
            if (r1 >= r82.length()) goto L49;
            if (isSpace(r82.charAt(r1)) == true) goto L43;
        L49:
            if (r1 < r82.length()) goto L51;
        L64:
            r3 = r1;
        L66:
            if (r3 >= r82.length()) goto L70;
            if (isSpace(r82.charAt(r3)) == true) goto L70;
            r3 = r3 + 1;
        L70:
            r12 = r82.substring(r1, r3);
            goto L73
        L51:
            if (r82.charAt(r1) != '\"') goto L53;
        L54:
            char r32 = r82.charAt(r1);
            int r13 = r1 + 1;
            int r4 = r13;
        L56:
            if (r4 >= r82.length()) goto L60;
            if (r82.charAt(r4) == r32) goto L60;
            r4 = r4 + 1;
        L60:
            r12 = r82.substring(r13, Math.min(r4, r82.length()));
            if (r4 >= r82.length()) goto L63;
            r4 = r4 + 1;
        L63:
            r3 = r4;
            goto L73
        L53:
            if (r82.charAt(r1) != '\'') goto L64;
        L79:
            return r02;
        }

        public List<Node> parse() {
            ArrayList<Node> r0 = new ArrayList();
            ArrayList<Node> r1 = new ArrayList();
        L4:
            if (this.p >= this.s.length()) goto L55;
            char r2 = this.s.charAt(this.p);
            String r3 = this.s;
            if (r2 == '<') goto L8;
            int r22 = r3.indexOf(60, this.p);
            if (r22 >= 0) goto L54;
            r22 = this.s.length();
        L54:
            addText(r1, r0, this.s.substring(this.p, r22));
            this.p = r22;
            goto L4
        L8:
            if (r3.startsWith("<!--", this.p) == true) goto L9;
            if ((this.p + 1) >= this.s.length()) goto L24;
            if (this.s.charAt(this.p + 1) != '!') goto L24;
            int r23 = this.s.indexOf(62, this.p);
            if (r23 >= 0) goto L21;
            int r24 = this.s.length();
        L22:
            this.p = r24;
            goto L4
        L21:
            r24 = r23 + 1;
        L24:
            if ((this.p + 1) >= this.s.length()) goto L36;
            if (this.s.charAt(this.p + 1) != '/') goto L36;
            int r25 = this.s.indexOf(62, this.p);
            String r32 = this.s;
            int r4 = this.p + 2;
            if (r25 >= 0) goto L30;
            int r5 = r32.length();
        L31:
            String r33 = r32.substring(r4, r5).trim().toLowerCase();
            if (r25 >= 0) goto L34;
            int r26 = this.s.length();
        L35:
            this.p = r26;
            closeTag(r1, r33);
            goto L4
        L34:
            r26 = r25 + 1;
            goto L35
        L30:
            r5 = r25;
        L36:
            int r27 = findTagEnd(this.p);
            String r34 = this.s;
            if (r27 < 0) goto L38;
            String r35 = r34.substring(this.p + 1, r27);
            this.p = r27 + 1;
            boolean r28 = r35.endsWith("/");
            if (r28 == false) goto L43;
            r35 = r35.substring(0, r35.length() - 1);
        L43:
            Node r36 = parseTag(r35);
            if (r36 == null) goto L4;
            addChild(r1, r0, r36);
            if (r28 == true) goto L4;
            if (RichHtml.m21869$$Nest$smisVoid(r36.tag) == true) goto L4;
            r1.add(r36);
            goto L4
        L38:
            addText(r1, r0, r34.substring(this.p));
            return r0;
        L9:
            int r29 = this.s.indexOf("-->", this.p + 4);
            if (r29 >= 0) goto L12;
            int r210 = this.s.length();
        L13:
            this.p = r210;
            goto L4
        L12:
            r210 = r29 + 3;
            goto L13
        L55:
            return r0;
        }
    }

    /* renamed from: -$$Nest$smdecode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m21868$$Nest$smdecode(String r0) {
        return decode(r0);
    }

    /* renamed from: -$$Nest$smisVoid, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21869$$Nest$smisVoid(String r0) {
        return isVoid(r0);
    }

    private static void addRow(ArrayList<BlockRow> r0, BlockRow r1) {
        if (r1 == null) goto L5;
        r0.add(r1);
        return;
    }

    private static void addText(ArrayList<BlockRow> r6, TL_iv.PageBlock r7, Node r8, int r9) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        appendChildrenInline(r0, r8, 0, null, 0);
        RichTextCell.applyStyledTextToBlock(r7, trim(r0));
        r6.add(new BlockRow(r7, r9, 0));
    }

    private static String alignFromStyle(String r4) {
        if (r4 != null) goto L5;
        return null;
    L5:
        String r42 = r4.toLowerCase();
        int r1 = r42.indexOf("text-align");
        if (r1 >= 0) goto L9;
        return null;
    L9:
        if (r42.indexOf("center", r1) < 0) goto L12;
        return "center";
    L12:
        if (r42.indexOf("right", r1) < 0) goto L14;
        return "right";
    L14:
        return null;
    }

    private static void appendAuthorCite(StringBuilder r1, CharSequence r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.length() == 0) goto L9;
        r1.append("<cite>");
        appendInline(r1, r2);
        r1.append("</cite>");
        return;
    }

    private static void appendButton(StringBuilder r4, TL_iv.RichText r5, TL_keyboard.InlineButtonType r6, TL_keyboard.RichButtonStyle r7) {
        if (RichInlineButtonSpan.isSupported(r6) == true) goto L5;
        return;
    L5:
        r4.append("<button");
        if ((r6 instanceof TL_keyboard.TL_inlineButtonTypeUrl) == false) goto L9;
        r4.append(" data-type=\"url\" data-url=\"");
        r4.append(escapeAttr(((TL_keyboard.TL_inlineButtonTypeUrl) r6).url));
        r4.append("\"");
    L14:
        if (r7 != null) goto L16;
    L26:
        r4.append(">");
        appendInline(r4, RichTextStyle.toSpannable(r5));
        r4.append("</button>");
        return;
    L16:
        if (r7.bg_primary == false) goto L19;
        String r62 = "primary";
    L25:
        r4.append(" data-style=\"");
        r4.append(r62);
        r4.append("\"");
        goto L26
    L19:
        if (r7.bg_danger == false) goto L22;
        r62 = "danger";
        goto L25
    L22:
        if (r7.bg_success == false) goto L24;
        r62 = "success";
        goto L25
    L24:
        r62 = PluginsConstants.Settings.DEFAULT;
        goto L25
    L9:
        if ((r6 instanceof TL_keyboard.TL_inlineButtonTypeCopy) == false) goto L12;
        r4.append(" data-type=\"copy\" data-copy-text=\"");
        r4.append(escapeAttr(((TL_keyboard.TL_inlineButtonTypeCopy) r6).copy_text));
        r4.append("\"");
        goto L14
    L12:
        if ((r6 instanceof TL_keyboard.TL_inlineButtonTypeUserProfile) == false) goto L14;
        r4.append(" data-type=\"user-profile\" data-user-id=\"");
        r4.append(((TL_keyboard.TL_inlineButtonTypeUserProfile) r6).user_id);
        r4.append("\"");
        goto L14
    }

    private static void appendChildrenInline(SpannableStringBuilder r9, Node r10, int r11, String r12, long r13) {
        ArrayList<Node> r102 = r10.children;
        int r0 = r102.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        Node r2 = r102.get(r1);
        r1 = r1 + 1;
        Node r4 = r2;
        if (r4.isText == false) goto L7;
        SpannableStringBuilder r3 = r9;
        int r5 = r11;
        String r6 = r12;
        long r7 = r13;
        appendStyled(r3, decode(r4.text), r5, r6, r7);
    L8:
        r9 = r3;
        r11 = r5;
        r12 = r6;
        r13 = r7;
        goto L3
    L7:
        r3 = r9;
        r5 = r11;
        r6 = r12;
        r7 = r13;
        appendInlineNode(r3, r4, r5, r6, r7);
        goto L8
    }

    private static void appendChildrenInlineExcept(SpannableStringBuilder r9, Node r10, String r11) {
        ArrayList<Node> r102 = r10.children;
        int r0 = r102.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L11;
        Node r2 = r102.get(r1);
        r1 = r1 + 1;
        Node r4 = r2;
        if (r4.isText == false) goto L7;
        SpannableStringBuilder r3 = r9;
        appendStyled(r3, decode(r4.text), 0, null, 0);
    L10:
        r9 = r3;
        goto L3
    L7:
        r3 = r9;
        if (r11.equals(r4.tag) == true) goto L10;
        appendInlineNode(r3, r4, 0, null, 0);
        goto L10
    }

    private static void appendInline(StringBuilder r11, CharSequence r12) {
        if (r12 != null) goto L4;
        return;
    L4:
        if (r12.length() != 0) goto L7;
        return;
    L7:
        if ((r12 instanceof Spanned) == true) goto L10;
        escape(r11, r12, 0, r12.length());
        return;
    L10:
        Spanned r0 = (Spanned) r12;
        int r2 = r12.length();
        int r3 = 0;
    L11:
        if (r3 >= r2) goto L54;
        RichInlineButtonSpan[] r4 = (RichInlineButtonSpan[]) r0.getSpans(r3, Math.min(r2, r3 + 1), RichInlineButtonSpan.class);
        int r5 = r4.length;
        int r6 = 0;
    L13:
        String r7 = null;
        if (r6 >= r5) goto L20;
        RichInlineButtonSpan r8 = r4[r6];
        int r9 = r0.getSpanStart(r8);
        int r10 = r0.getSpanEnd(r8);
        if (r9 > r3) goto L19;
        if (r10 <= r3) goto L19;
    L21:
        if (r8 != null) goto L22;
        int r42 = r0.nextSpanTransition(r3, r2, CharacterStyle.class);
        TextStyleSpan[] r52 = (TextStyleSpan[]) r0.getSpans(r3, r42, TextStyleSpan.class);
        int r62 = r52.length;
        int r82 = 0;
        int r92 = 0;
    L24:
        if (r82 >= r62) goto L29;
        TextStyleSpan.TextStyleRun r102 = r52[r82].getTextStyleRun();
        if (r102 == null) goto L28;
        r92 = r92 | r102.flags;
    L28:
        r82 = r82 + 1;
        goto L24
    L29:
        URLSpanReplacement[] r53 = (URLSpanReplacement[]) r0.getSpans(r3, r42, URLSpanReplacement.class);
        if (r53.length <= 0) goto L32;
        r7 = r53[0].getURL();
    L32:
        AnimatedEmojiSpan[] r54 = (AnimatedEmojiSpan[]) r0.getSpans(r3, r42, AnimatedEmojiSpan.class);
        if (r54.length <= 0) goto L37;
        AnimatedEmojiSpan r55 = r54[0];
        if (r55.standard == true) goto L37;
        long r56 = r55.getDocumentId();
    L38:
        openInline(r11, r92, r7, r56);
        escape(r11, r12, r3, r42);
        closeInline(r11, r92, r7, r56);
        r3 = r42;
    L37:
        r56 = 0;
        goto L38
    L22:
        appendInlineButton(r11, r8.getButton());
        r3 = Math.min(r2, r10);
    L19:
        r6 = r6 + 1;
        goto L13
    L20:
        r10 = -1;
        r8 = null;
        goto L21
    }

    private static void appendInlineButton(StringBuilder r2, TL_iv.textButton r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (RichInlineButtonSpan.isSupported(r3.type) == false) goto L9;
        appendButton(r2, r3.text, r3.type, r3.style);
        return;
    }

    private static void appendInlineNode(SpannableStringBuilder r7, Node r8, int r9, String r10, long r11) {
        if ("button".equals(r8.tag) == false) goto L10;
        TL_keyboard.InlineButtonType r0 = inlineButtonTypeOf(r8);
        if (r0 == null) goto L10;
        SpannableStringBuilder r1 = new SpannableStringBuilder();
        appendChildrenInline(r1, r8, r9, r10, r11);
        if (r1.length() <= 0) goto L109;
        int r92 = r7.length();
        r7.append(r1);
        TL_iv.textButton r102 = new TL_iv.textButton();
        r102.text = RichTextStyle.fromSpannable(r1);
        r102.type = r0;
        r102.style = inlineButtonStyleOf(r8);
        r7.setSpan(new RichInlineButtonSpan(r102), r92, r7.length(), 33);
        return;
    L109:
        return;
    L10:
        String r93 = r8.tag;
        r93.getClass();
        char r112 = 65535;
        switch(r93.hashCode()) {
            case -1999048254: goto L78;
            case -891985998: goto L74;
            case -891980137: goto L70;
            case 97: goto L66;
            case 98: goto L62;
            case 105: goto L58;
            case 115: goto L54;
            case 117: goto L50;
            case 3152: goto L46;
            case 3240: goto L42;
            case 3712: goto L38;
            case 99339: goto L34;
            case 114240: goto L30;
            case 114254: goto L26;
            case 3059181: goto L22;
            case 3344077: goto L18;
            case 1438365596: goto L14;
            default: goto L81;
        };
    L81:
        switch(r112) {
            case 0: goto L104;
            case 1: goto L103;
            case 2: goto L102;
            case 3: goto L98;
            case 4: goto L102;
            case 5: goto L97;
            case 6: goto L103;
            case 7: goto L96;
            case 8: goto L94;
            case 9: goto L97;
            case 10: goto L93;
            case 11: goto L103;
            case 12: goto L92;
            case 13: goto L91;
            case 14: goto L93;
            case 15: goto L87;
            case 16: goto L83;
            default: goto L101;
        };
    L83:
        String r94 = r8.attr("data-document-id");
        if (r94 == null) goto L101;
        long r113 = Long.parseLong(r94.trim());     // Catch: Exception -> L112
        int r95 = r9;
        String r103 = r10;
    L106:
        if (r8.children.isEmpty() == true) goto L108;
    L110:
        appendChildrenInline(r7, r8, r95, r103, r113);
        return;
    L108:
        if (r8.isText == true) goto L110;
        return;
    L87:
        int r96 = 65536;
    L88:
        r95 = r96 | r9;
    L89:
        r103 = r10;
    L90:
        r113 = r11;
        goto L106
    L91:
        r96 = 32768;
        goto L88
    L92:
        r95 = r9 | 16384;
        goto L89
    L93:
        r95 = r9 | 4;
        goto L89
    L94:
        appendStyled(r7, "\n", r9, r10, r11);
        return;
    L96:
        r95 = r9 | 16;
        goto L89
    L97:
        r95 = r9 | 2;
        goto L89
    L98:
        String r97 = r8.attr("href");
        if (r97 == null) goto L101;
        r103 = r97;
        r95 = r9;
        goto L90
    L102:
        r95 = r9 | 1;
        goto L89
    L103:
        r95 = r9 | 8;
        goto L89
    L104:
        r95 = r9 | 256;
    L101:
        r95 = r9;
        goto L89
    L14:
        if (r93.equals("animated-emoji") == false) goto L81;
        r112 = 16;
        goto L81
    L18:
        if (r93.equals("mark") == false) goto L81;
        r112 = 15;
        goto L81
    L22:
        if (r93.equals("code") == false) goto L81;
        r112 = 14;
        goto L81
    L26:
        if (r93.equals("sup") == false) goto L81;
        r112 = '\r';
        goto L81
    L30:
        if (r93.equals("sub") == false) goto L81;
        r112 = '\f';
        goto L81
    L34:
        if (r93.equals(TeXSymbolParser.DELIMITER_ATTR) == false) goto L81;
        r112 = 11;
        goto L81
    L38:
        if (r93.equals("tt") == false) goto L81;
        r112 = '\n';
        goto L81
    L42:
        if (r93.equals("em") == false) goto L81;
        r112 = '\t';
        goto L81
    L46:
        if (r93.equals("br") == false) goto L81;
        r112 = '\b';
        goto L81
    L50:
        if (r93.equals("u") == false) goto L81;
        r112 = 7;
        goto L81
    L54:
        if (r93.equals("s") == false) goto L81;
        r112 = 6;
        goto L81
    L58:
        if (r93.equals("i") == false) goto L81;
        r112 = 5;
        goto L81
    L62:
        if (r93.equals("b") == false) goto L81;
        r112 = 4;
        goto L81
    L66:
        if (r93.equals("a") == false) goto L81;
        r112 = 3;
        goto L81
    L70:
        if (r93.equals("strong") == false) goto L81;
        r112 = 2;
        goto L81
    L74:
        if (r93.equals("strike") == false) goto L81;
        r112 = 1;
        goto L81
    L78:
        if (r93.equals("spoiler") == false) goto L81;
        r112 = 0;
        goto L81
    }

    private static void appendMediaTag(StringBuilder r1, String r2, long r3, MediaUploadState r5, TL_iv.PageBlock r6) {
        r1.append(Typography.less);
        r1.append(r2);
        r1.append(" src=\"");
        r1.append(r3);
        r1.append(Typography.quote);
        if (r5 == null) goto L11;
        if (r5.width <= 0) goto L8;
        r1.append(" width=\"");
        r1.append(r5.width);
        r1.append(Typography.quote);
    L8:
        if (r5.height <= 0) goto L11;
        r1.append(" height=\"");
        r1.append(r5.height);
        r1.append(Typography.quote);
    L11:
        if ((r6 instanceof TL_iv.pageBlockPhoto) == false) goto L16;
        if (((TL_iv.pageBlockPhoto) r6).spoiler == false) goto L16;
        r1.append(" data-spoiler=\"1\"");
    L16:
        if ((r6 instanceof TL_iv.pageBlockVideo) == true) goto L18;
    L20:
        r1.append(" />");
        return;
    L18:
        if (((TL_iv.pageBlockVideo) r6).spoiler == false) goto L20;
        r1.append(" data-spoiler=\"1\"");
        goto L20
    }

    private static void appendStyled(SpannableStringBuilder r4, CharSequence r5, int r6, String r7, long r8) {
        if (r5 != null) goto L4;
        return;
    L4:
        if (r5.length() == 0) goto L16;
        int r0 = r4.length();
        r4.append(r5);
        int r52 = r4.length();
        if (r8 == 0) goto L9;
        r4.setSpan(new AnimatedEmojiSpan(r8, null), r0, r52, 33);
    L9:
        if (r6 == 0) goto L11;
        TextStyleSpan.TextStyleRun r82 = new TextStyleSpan.TextStyleRun();
        r82.flags = r6 & 114975;
        r4.setSpan(new TextStyleSpan(r82, AndroidUtilities.dp(SharedConfig.fontSize)), r0, r52, 33);
    L11:
        if (r7 == null) goto L15;
        r4.setSpan(RichTextStyle.linkSpan(r7), r0, r52, 33);
        return;
    L15:
        return;
    }

    private static CharSequence authorText(TL_iv.RichText r2) {
        if (r2 != null) goto L5;
    L12:
        return null;
    L5:
        if ((r2 instanceof TL_iv.textEmpty) == true) goto L12;
        CharSequence r22 = RichTextStyle.toSpannable(r2);
        if (r22 == null) goto L12;
        if (r22.length() <= 0) goto L12;
        return r22;
    }

    private static String blockTag(TL_iv.PageBlock r2) {
        if ((r2 instanceof TL_iv.pageBlockHeading1) == false) goto L7;
        return "h1";
    L7:
        if ((r2 instanceof TL_iv.pageBlockHeading2) == false) goto L11;
        return "h2";
    L11:
        if ((r2 instanceof TL_iv.pageBlockHeading3) == false) goto L15;
        return "h3";
    L15:
        if ((r2 instanceof TL_iv.pageBlockHeading4) == false) goto L19;
        return "h4";
    L19:
        if ((r2 instanceof TL_iv.pageBlockHeading5) == false) goto L23;
        return "h5";
    L23:
        if ((r2 instanceof TL_iv.pageBlockHeading6) == false) goto L27;
        return "h6";
    L27:
        if ((r2 instanceof TL_iv.pageBlockBlockquote) == false) goto L30;
        return "blockquote";
    L30:
        if ((r2 instanceof TL_iv.pageBlockPullquote) == false) goto L33;
        return "blockquote";
    L33:
        if ((r2 instanceof TL_iv.pageBlockPreformatted) == false) goto L37;
        return "pre";
    L37:
        if ((r2 instanceof TL_iv.pageBlockFooter) == false) goto L41;
        return "footer";
    L41:
        if ((r2 instanceof TL_iv.pageBlockParagraph) == false) goto L44;
        return "p";
    L44:
        return null;
    }

    private static BlockRow buildAudio(Node r4) {
        long r2 = parseLongAttr(r4.attr("src"), 0);
        if (r2 > 0) goto L6;
        return null;
    L6:
        TL_iv.pageBlockAudio r42 = new TL_iv.pageBlockAudio();
        r42.audio_id = r2;
        setEmptyCaption(r42);
        return new BlockRow(r42);
    }

    private static BlockRow buildDocument(Node r4) {
        long r2 = parseLongAttr(r4.attr("src"), 0);
        if (r2 > 0) goto L6;
        return null;
    L6:
        TL_iv.pageBlockDocument r42 = new TL_iv.pageBlockDocument();
        r42.document_id = r2;
        setEmptyCaption(r42);
        return new BlockRow(r42);
    }

    private static BlockRow buildMap(Node r7) {
        TL_iv.pageBlockMap r0 = new TL_iv.pageBlockMap();
        TLRPC.TL_geoPoint r1 = new TLRPC.TL_geoPoint();
        r1.lat = parseDoubleAttr(r7.attr("lat"), 0.0d);
        r1._long = parseDoubleAttr(r7.attr("long"), 0.0d);
        r1.access_hash = parseLongAttr(r7.attr("access"), 0);
        r0.geo = r1;
        r0.zoom = parseIntAttr(r7.attr("zoom"), 15);
        r0.w = parseIntAttr(r7.attr("w"), MediaError.DetailedErrorCode.TEXT_UNKNOWN);
        r0.h = parseIntAttr(r7.attr("h"), MediaError.DetailedErrorCode.MANIFEST_UNKNOWN);
        setEmptyCaption(r0);
        return new BlockRow(r0);
    }

    private static BlockRow buildMediaRow(Node r5) {
        String r0 = r5.tag;
        r0.getClass();
        char r4 = 65535;
        switch(r0.hashCode()) {
            case 99473: goto L26;
            case 104387: goto L22;
            case 93166550: goto L18;
            case 112202875: goto L14;
            case 861720859: goto L10;
            case 1901043637: goto L6;
            default: goto L30;
        };
    L6:
        if (r0.equals("location") == false) goto L30;
        r4 = 5;
        goto L30
    L10:
        if (r0.equals("document") == false) goto L30;
        r4 = 4;
        goto L30
    L14:
        if (r0.equals(MediaStreamTrack.VIDEO_TRACK_KIND) == false) goto L30;
        r4 = 3;
        goto L30
    L18:
        if (r0.equals(MediaStreamTrack.AUDIO_TRACK_KIND) == false) goto L30;
        r4 = 2;
        goto L30
    L22:
        if (r0.equals("img") == false) goto L30;
        r4 = 1;
        goto L30
    L26:
        if (r0.equals("div") == false) goto L30;
        r4 = 0;
    L30:
        switch(r4) {
            case 0: goto L42;
            case 1: goto L41;
            case 2: goto L39;
            case 3: goto L37;
            case 4: goto L35;
            case 5: goto L33;
            default: goto L31;
        };
    L31:
        return null;
    L42:
        String r1 = r5.attr("class");
        if (r1 != null) goto L45;
        String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
    L47:
        if (r12.contains("slideshow") == false) goto L51;
        return parseGallery(r5, true);
    L51:
        if (r12.contains("collage") == true) goto L53;
        return null;
    L53:
        return parseGallery(r5, false);
    L45:
        r12 = r1.toLowerCase();
        goto L47
    L33:
        return buildMap(r5);
    L35:
        return buildDocument(r5);
    L37:
        return buildPhotoOrVideo(r5, true);
    L39:
        return buildAudio(r5);
    L41:
        return buildPhotoOrVideo(r5, false);
    }

    private static BlockRow buildPhotoOrVideo(Node r5, boolean r6) {
        long r3 = parseLongAttr(r5.attr("src"), 0);
        if (r3 > 0) goto L6;
        return null;
    L6:
        TL_iv.PageBlock r52 = newMediaItemBlock(r6, r3, r5.has("data-spoiler"));
        setEmptyCaption(r52);
        return new BlockRow(r52);
    }

    private static CharSequence captionOf(TL_iv.PageBlock r2) {
        if (r2 == null) goto L14;
        TL_iv.PageCaption r22 = r2.caption;
        if (r22 == null) goto L14;
        TL_iv.RichText r23 = r22.text;
        if (r23 == null) goto L14;
        CharSequence r24 = RichTextStyle.toSpannable(r23);
        if (r24 == null) goto L14;
        if (r24.length() <= 0) goto L14;
        return r24;
    L14:
        return null;
    }

    private static TL_iv.RichText citeAuthor(Node r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        CharSequence r22 = inlineOf(r2);
        if (r22 != null) goto L8;
    L12:
        return null;
    L8:
        if (r22.length() == 0) goto L12;
        return RichTextStyle.fromSpannable(r22);
    }

    private static void closeInline(StringBuilder r2, int r3, String r4, long r5) {
        if (r5 == 0) goto L5;
        r2.append("</animated-emoji>");
    L5:
        if (r4 == null) goto L8;
        r2.append("</a>");
    L8:
        if ((65536 & r3) == 0) goto L11;
        r2.append("</mark>");
    L11:
        if ((32768 & r3) == 0) goto L14;
        r2.append("</sup>");
    L14:
        if ((r3 & 16384) == 0) goto L17;
        r2.append("</sub>");
    L17:
        if ((r3 & 4) == 0) goto L20;
        r2.append("</code>");
    L20:
        if ((r3 & 8) == 0) goto L23;
        r2.append("</s>");
    L23:
        if ((r3 & 16) == 0) goto L26;
        r2.append("</u>");
    L26:
        if ((r3 & 2) == 0) goto L29;
        r2.append("</i>");
    L29:
        if ((r3 & 1) == 0) goto L32;
        r2.append("</b>");
    L32:
        if ((r3 & 256) == 0) goto L35;
        r2.append("</spoiler>");
        return;
    }

    private static void collectTableRows(Node r7, TL_iv.pageBlockTable r8) {
        ArrayList<Node> r72 = r7.children;
        int r0 = r72.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L35;
        Node r3 = r72.get(r2);
        r2 = r2 + 1;
        Node r32 = r3;
        if (r32.isText == true) goto L3;
        String r4 = r32.tag;
        r4.getClass();
        char r6 = 65535;
        switch(r4.hashCode()) {
            case 3710: goto L27;
            case 110157846: goto L23;
            case 110277346: goto L19;
            case 110326868: goto L15;
            case 552573414: goto L11;
            default: goto L30;
        };
    L30:
        switch(r6) {
            case 0: goto L34;
            case 1: goto L33;
            case 2: goto L33;
            case 3: goto L33;
            case 4: goto L32;
            default: goto L3;
        };
    L32:
        r8.title = RichTextStyle.fromSpannable(inlineOf(r32));
        goto L3
    L33:
        collectTableRows(r32, r8);
        goto L3
    L34:
        r8.rows.add(parseTableRow(r32));
        goto L3
    L11:
        if (r4.equals(MediaTrack.ROLE_CAPTION) == false) goto L30;
        r6 = 4;
        goto L30
    L15:
        if (r4.equals("thead") == false) goto L30;
        r6 = 3;
        goto L30
    L19:
        if (r4.equals("tfoot") == false) goto L30;
        r6 = 2;
        goto L30
    L23:
        if (r4.equals("tbody") == false) goto L30;
        r6 = 1;
        goto L30
    L27:
        if (r4.equals("tr") == false) goto L30;
        r6 = 0;
        goto L30
    }

    private static String decode(String r8) {
        if (r8 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        if (r8.indexOf(38) >= 0) goto L8;
        return r8;
    L8:
        StringBuilder r1 = new StringBuilder(r8.length());
        int r2 = 0;
    L10:
        if (r2 >= r8.length()) goto L26;
        char r3 = r8.charAt(r2);
        if (r3 == '&') goto L14;
        r1.append(r3);
    L24:
        r2 = r2 + 1;
        goto L10
    L14:
        int r4 = r2 + 1;
        int r5 = r8.indexOf(59, r4);
        if (r5 >= 0) goto L17;
    L23:
        r1.append(r3);
        goto L24
    L17:
        if ((r5 - r2) > 12) goto L23;
        String r42 = entity(r8.substring(r4, r5));
        if (r42 == null) goto L22;
        r1.append(r42);
        r2 = r5;
        goto L24
    L22:
        r1.append(r3);
        goto L24
    L26:
        return r1.toString();
    }

    private static String entity(String r5) {
        r5.getClass();
        char r4 = 65535;
        switch(r5.hashCode()) {
            case 3309: goto L26;
            case 3464: goto L22;
            case 96708: goto L18;
            case 3000915: goto L14;
            case 3374865: goto L10;
            case 3482377: goto L6;
            default: goto L29;
        };
    L29:
        switch(r4) {
            case 0: goto L54;
            case 1: goto L52;
            case 2: goto L50;
            case 3: goto L48;
            case 4: goto L46;
            case 5: goto L44;
            default: goto L31;
        };
    L44:
        return "\"";
    L46:
        return " ";
    L48:
        return "'";
    L50:
        return "&";
    L52:
        return "<";
    L54:
        return ">";
    L31:
        if (r5.length() > 1) goto L33;
    L43:
        return null;
    L33:
        if (r5.charAt(0) != '#') goto L43;
        if (r5.charAt(1) != 'x') goto L37;
    L40:
        int r52 = Integer.parseInt(r5.substring(2), 16);     // Catch: Exception -> L56
    L41:
        return new String(Character.toChars(r52));
    L37:
        if (r5.charAt(1) == 'X') goto L40;
        r52 = Integer.parseInt(r5.substring(1));     // Catch: Exception -> L56
        goto L41
    L6:
        if (r5.equals("quot") == false) goto L29;
        r4 = 5;
        goto L29
    L10:
        if (r5.equals("nbsp") == false) goto L29;
        r4 = 4;
        goto L29
    L14:
        if (r5.equals("apos") == false) goto L29;
        r4 = 3;
        goto L29
    L18:
        if (r5.equals("amp") == false) goto L29;
        r4 = 2;
        goto L29
    L22:
        if (r5.equals("lt") == false) goto L29;
        r4 = 1;
        goto L29
    L26:
        if (r5.equals("gt") == false) goto L29;
        r4 = 0;
        goto L29
    }

    private static void escape(StringBuilder r2, CharSequence r3, int r4, int r5) {
    L2:
        if (r4 >= r5) goto L17;
        char r0 = r3.charAt(r4);
        if (r0 != '\n') goto L7;
        r2.append("<br>");
    L16:
        r4 = r4 + 1;
        goto L2
    L7:
        if (r0 != '<') goto L10;
        r2.append("&lt;");
        goto L16
    L10:
        if (r0 != '>') goto L13;
        r2.append("&gt;");
        goto L16
    L13:
        if (r0 != '&') goto L15;
        r2.append("&amp;");
        goto L16
    L15:
        r2.append(r0);
        goto L16
    }

    private static String escapeAttr(String r2) {
        if (r2 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return r2.replace("&", "&amp;").replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;");
    }

    private static String firstAttr(Node r3, String... r4) {
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        String r2 = r3.attr(r4[r1]);
        if (r2 != null) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r2;
    L8:
        return null;
    }

    private static SpannableStringBuilder flushParagraph(ArrayList<BlockRow> r2, SpannableStringBuilder r3, int r4) {
        if (r3 != null) goto L4;
        return null;
    L4:
        if (isBlank(r3.toString()) == true) goto L9;
        TL_iv.pageBlockParagraph r0 = new TL_iv.pageBlockParagraph();
        r0.text = RichTextStyle.fromSpannable(trim(r3));
        r2.add(new BlockRow(r0, r4, 0));
        return null;
    L9:
        return null;
    }

    private static boolean hasCheckboxClass(Node r1) {
        String r12 = r1.attr("class");
        if (r12 != null) goto L5;
        return false;
    L5:
        if (r12.toLowerCase().contains("checkbox") == false) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean hasPullClass(Node r1) {
        String r12 = r1.attr("class");
        if (r12 != null) goto L5;
        return false;
    L5:
        if (r12.toLowerCase().contains("pull") == false) goto L10;
        return true;
    L10:
        return false;
    }

    private static TL_keyboard.RichButtonStyle inlineButtonStyleOf(Node r2) {
        TL_keyboard.RichButtonStyle r0 = new TL_keyboard.RichButtonStyle();
        String r22 = r2.attr("data-style");
        r0.bg_primary = "primary".equals(r22);
        r0.bg_danger = "danger".equals(r22);
        r0.bg_success = "success".equals(r22);
        return r0;
    }

    private static TL_keyboard.InlineButtonType inlineButtonTypeOf(Node r5) {
        String r0 = r5.attr("data-type");
        if ("url".equals(r0) == false) goto L10;
        String r52 = r5.attr("data-url");
        if (TextUtils.isEmpty(r52) == false) goto L7;
        return null;
    L7:
        TL_keyboard.TL_inlineButtonTypeUrl r02 = new TL_keyboard.TL_inlineButtonTypeUrl();
        r02.url = r52;
        return r02;
    L10:
        if ("copy".equals(r0) == false) goto L17;
        String r53 = r5.attr("data-copy-text");
        if (TextUtils.isEmpty(r53) == false) goto L14;
        return null;
    L14:
        TL_keyboard.TL_inlineButtonTypeCopy r03 = new TL_keyboard.TL_inlineButtonTypeCopy();
        r03.copy_text = r53;
        return r03;
    L17:
        if ("user-profile".equals(r0) == false) goto L23;
        long r3 = parseLongAttr(r5.attr("data-user-id"), 0);
        if (r3 > 0) goto L21;
        return null;
    L21:
        TL_keyboard.TL_inlineButtonTypeUserProfile r54 = new TL_keyboard.TL_inlineButtonTypeUserProfile();
        r54.user_id = r3;
        return r54;
    L23:
        return null;
    }

    private static CharSequence inlineOf(Node r6) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        appendChildrenInline(r0, r6, 0, null, 0);
        return trim(r0);
    }

    public static String inlineToHtml(CharSequence r1) {
        StringBuilder r0 = new StringBuilder();
        appendInline(r0, r1);
        return r0.toString();
    }

    private static boolean isBlank(String r5) {
        if (r5 != null) goto L5;
        return true;
    L5:
        int r2 = 0;
    L7:
        if (r2 >= r5.length()) goto L20;
        char r3 = r5.charAt(r2);
        if (r3 == ' ') goto L19;
        if (r3 == '\n') goto L19;
        if (r3 == '\t') goto L19;
        if (r3 == '\r') goto L19;
        if (r3 == 160) goto L19;
        return false;
    L19:
        r2 = r2 + 1;
        goto L7
    L20:
        return true;
    }

    private static boolean isInlineTag(String r4) {
        r4.getClass();
        char r3 = 65535;
        switch(r4.hashCode()) {
            case -1999048254: goto L78;
            case -1377687758: goto L74;
            case -891985998: goto L70;
            case -891980137: goto L66;
            case 97: goto L62;
            case 98: goto L58;
            case 105: goto L54;
            case 115: goto L50;
            case 117: goto L46;
            case 3152: goto L42;
            case 3240: goto L38;
            case 3712: goto L34;
            case 99339: goto L30;
            case 114240: goto L26;
            case 114254: goto L22;
            case 3059181: goto L18;
            case 3148879: goto L14;
            case 3536714: goto L10;
            case 1438365596: goto L6;
            default: goto L81;
        };
    L81:
        switch(r3) {
            case 0: goto L83;
            case 1: goto L83;
            case 2: goto L83;
            case 3: goto L83;
            case 4: goto L83;
            case 5: goto L83;
            case 6: goto L83;
            case 7: goto L83;
            case 8: goto L83;
            case 9: goto L83;
            case 10: goto L83;
            case 11: goto L83;
            case 12: goto L83;
            case 13: goto L83;
            case 14: goto L83;
            case 15: goto L83;
            case 16: goto L83;
            case 17: goto L83;
            case 18: goto L83;
            default: goto L82;
        };
    L82:
        return false;
    L83:
        return true;
    L6:
        if (r4.equals("animated-emoji") == false) goto L81;
        r3 = 18;
        goto L81
    L10:
        if (r4.equals("span") == false) goto L81;
        r3 = 17;
        goto L81
    L14:
        if (r4.equals("font") == false) goto L81;
        r3 = 16;
        goto L81
    L18:
        if (r4.equals("code") == false) goto L81;
        r3 = 15;
        goto L81
    L22:
        if (r4.equals("sup") == false) goto L81;
        r3 = 14;
        goto L81
    L26:
        if (r4.equals("sub") == false) goto L81;
        r3 = '\r';
        goto L81
    L30:
        if (r4.equals(TeXSymbolParser.DELIMITER_ATTR) == false) goto L81;
        r3 = '\f';
        goto L81
    L34:
        if (r4.equals("tt") == false) goto L81;
        r3 = 11;
        goto L81
    L38:
        if (r4.equals("em") == false) goto L81;
        r3 = '\n';
        goto L81
    L42:
        if (r4.equals("br") == false) goto L81;
        r3 = '\t';
        goto L81
    L46:
        if (r4.equals("u") == false) goto L81;
        r3 = '\b';
        goto L81
    L50:
        if (r4.equals("s") == false) goto L81;
        r3 = 7;
        goto L81
    L54:
        if (r4.equals("i") == false) goto L81;
        r3 = 6;
        goto L81
    L58:
        if (r4.equals("b") == false) goto L81;
        r3 = 5;
        goto L81
    L62:
        if (r4.equals("a") == false) goto L81;
        r3 = 4;
        goto L81
    L66:
        if (r4.equals("strong") == false) goto L81;
        r3 = 3;
        goto L81
    L70:
        if (r4.equals("strike") == false) goto L81;
        r3 = 2;
        goto L81
    L74:
        if (r4.equals("button") == false) goto L81;
        r3 = 1;
        goto L81
    L78:
        if (r4.equals("spoiler") == false) goto L81;
        r3 = 0;
        goto L81
    }

    private static boolean isTextBlock(TL_iv.PageBlock r0) {
        if (blockTag(r0) == null) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean isVoid(String r4) {
        r4.getClass();
        char r3 = 65535;
        switch(r4.hashCode()) {
            case 3152: goto L30;
            case 3338: goto L26;
            case 104387: goto L22;
            case 117511: goto L18;
            case 3321850: goto L14;
            case 3347973: goto L10;
            case 100358090: goto L6;
            default: goto L33;
        };
    L33:
        switch(r3) {
            case 0: goto L35;
            case 1: goto L35;
            case 2: goto L35;
            case 3: goto L35;
            case 4: goto L35;
            case 5: goto L35;
            case 6: goto L35;
            default: goto L34;
        };
    L34:
        return false;
    L35:
        return true;
    L6:
        if (r4.equals(PluginsConstants.Settings.TYPE_INPUT) == false) goto L33;
        r3 = 6;
        goto L33
    L10:
        if (r4.equals("meta") == false) goto L33;
        r3 = 5;
        goto L33
    L14:
        if (r4.equals("link") == false) goto L33;
        r3 = 4;
        goto L33
    L18:
        if (r4.equals("wbr") == false) goto L33;
        r3 = 3;
        goto L33
    L22:
        if (r4.equals("img") == false) goto L33;
        r3 = 2;
        goto L33
    L26:
        if (r4.equals("hr") == false) goto L33;
        r3 = 1;
        goto L33
    L30:
        if (r4.equals("br") == false) goto L33;
        r3 = 0;
        goto L33
    }

    private static boolean isWs(char r1) {
        if (r1 != ' ') goto L5;
        return true;
    L5:
        if (r1 != '\n') goto L7;
        return true;
    L7:
        if (r1 != '\t') goto L9;
        return true;
    L9:
        if (r1 == '\r') goto L17;
        return false;
    L17:
        return true;
    }

    private static TL_iv.PageBlock newMediaItemBlock(boolean r3, long r4, boolean r6) {
        if (r3 == false) goto L10;
        TL_iv.pageBlockVideo r32 = new TL_iv.pageBlockVideo();
        if (r4 > 0) goto L8;
        r4 = 0;
    L8:
        r32.video_id = r4;
        r32.spoiler = r6;
        return r32;
    L10:
        TL_iv.pageBlockPhoto r33 = new TL_iv.pageBlockPhoto();
        if (r4 > 0) goto L14;
        r4 = 0;
    L14:
        r33.photo_id = r4;
        r33.spoiler = r6;
        return r33;
    }

    private static void openInline(StringBuilder r2, int r3, String r4, long r5) {
        if ((r3 & 256) == 0) goto L6;
        r2.append("<spoiler>");
    L6:
        if ((r3 & 1) == 0) goto L9;
        r2.append("<b>");
    L9:
        if ((r3 & 2) == 0) goto L12;
        r2.append("<i>");
    L12:
        if ((r3 & 16) == 0) goto L15;
        r2.append("<u>");
    L15:
        if ((r3 & 8) == 0) goto L18;
        r2.append("<s>");
    L18:
        if ((r3 & 4) == 0) goto L21;
        r2.append("<code>");
    L21:
        if ((r3 & 16384) == 0) goto L24;
        r2.append("<sub>");
    L24:
        if ((32768 & r3) == 0) goto L27;
        r2.append("<sup>");
    L27:
        if ((r3 & 65536) == 0) goto L30;
        r2.append("<mark>");
    L30:
        if (r4 == null) goto L33;
        r2.append("<a href=\"");
        r2.append(escapeAttr(r4));
        r2.append("\">");
    L33:
        if (r5 == 0) goto L36;
        r2.append("<animated-emoji data-document-id=\"");
        r2.append(r5);
        r2.append("\">");
        return;
    }

    public static List<BlockRow> parse(String r2, Map<Long, TL_iv.RichText> r3) {
        ArrayList r0 = new ArrayList();
        if (r2 == null) goto L8;
        parseBlocks(new Parser(r2).parse(), r0, 0, r3);
        if (r0.isEmpty() == false) goto L8;
        r0.add(new BlockRow(new TL_iv.pageBlockParagraph()));
    L8:
        return r0;
    }

    private static void parseBlockquote(Node r11, ArrayList<BlockRow> r12, int r13, Map<Long, TL_iv.RichText> r14) {
        ArrayList<Node> r0 = r11.children;
        int r1 = r0.size();
        Node r2 = null;
        boolean r4 = false;
        int r5 = 0;
    L3:
        boolean r7 = true;
        if (r5 >= r1) goto L17;
        Node r8 = r0.get(r5);
        r5 = r5 + 1;
        Node r82 = r8;
        if (r82.isText == true) goto L3;
        String r9 = r82.tag;
        if (r9 == null) goto L3;
        if ("cite".equals(r9) == true) goto L12;
        if (isInlineTag(r82.tag) == true) goto L3;
        r4 = true;
        goto L3
    L12:
        if (r2 != null) goto L3;
        r2 = r82;
        goto L3
    L17:
        TL_iv.RichText r02 = citeAuthor(r2);
        if (r4 == true) goto L30;
        SpannableStringBuilder r142 = new SpannableStringBuilder();
        appendChildrenInlineExcept(r142, r11, "cite");
        TL_iv.pageBlockBlockquote r15 = new TL_iv.pageBlockBlockquote();
        if (r11.has("data-collapsed") == false) goto L22;
    L25:
        r15.collapsed = r7;
        RichTextCell.applyStyledTextToBlock(r15, trim(r142));
        if (r02 == null) goto L28;
        r15.caption = r02;
    L28:
        r12.add(new BlockRow(r15, r13, 0));
        return;
    L22:
        if (r11.has("collapsed") == true) goto L25;
        r7 = false;
        goto L25
    L30:
        long r16 = RichContainer.newId();
        int r42 = r12.size();
        ArrayList r52 = new ArrayList();
        ArrayList<Node> r112 = r11.children;
        int r72 = r112.size();
        int r83 = 0;
    L31:
        if (r83 >= r72) goto L38;
        Node r92 = r112.get(r83);
        r83 = r83 + 1;
        Node r93 = r92;
        if (r93.isText == true) goto L37;
        if ("cite".equals(r93.tag) == true) goto L31;
    L37:
        r52.add(r93);
        goto L31
    L38:
        parseBlocks(r52, r12, r13, r14);
        if (r12.size() != r42) goto L42;
        r12.add(new BlockRow(new TL_iv.pageBlockParagraph(), r13, 0));
    L42:
        if (r42 >= r12.size()) goto L44;
        r12.get(r42).quoteIds.add(0, Long.valueOf(r16));
        r42 = r42 + 1;
        goto L42
    L44:
        if (r02 == null) goto L70;
        if (r14 == null) goto L71;
        r14.put(Long.valueOf(r16), r02);
        return;
    L71:
        return;
    }

    private static void parseBlocks(List<Node> r8, ArrayList<BlockRow> r9, int r10, Map<Long, TL_iv.RichText> r11) {
        Iterator<Node> r82 = r8.iterator();
        SpannableStringBuilder r0 = null;
    L4:
        if (r82.hasNext() == false) goto L170;
        Node r3 = r82.next();
        if (r3.isText == true) goto L8;
        String r1 = r3.tag;
        if (isInlineTag(r1) == true) goto L14;
        r0 = flushParagraph(r9, r0, r10);
        r1.getClass();
        char r7 = 65535;
        switch(r1.hashCode()) {
            case -1857640538: goto L125;
            case -1274639644: goto L121;
            case -1268861541: goto L117;
            case 112: goto L113;
            case 3273: goto L109;
            case 3274: goto L105;
            case 3275: goto L101;
            case 3276: goto L97;
            case 3277: goto L93;
            case 3278: goto L89;
            case 3338: goto L85;
            case 3549: goto L81;
            case 3696: goto L77;
            case 3700: goto L73;
            case 3710: goto L69;
            case 3735: goto L65;
            case 99473: goto L61;
            case 104387: goto L57;
            case 111267: goto L53;
            case 93166550: goto L49;
            case 110115790: goto L45;
            case 110157846: goto L41;
            case 110326868: goto L37;
            case 112202875: goto L33;
            case 1303202319: goto L29;
            case 1557721666: goto L25;
            case 1901043637: goto L21;
            default: goto L128;
        };
    L128:
        switch(r7) {
            case 0: goto L169;
            case 1: goto L168;
            case 2: goto L167;
            case 3: goto L166;
            case 4: goto L165;
            case 5: goto L164;
            case 6: goto L163;
            case 7: goto L162;
            case 8: goto L161;
            case 9: goto L160;
            case 10: goto L159;
            case 11: goto L158;
            case 12: goto L157;
            case 13: goto L157;
            case 14: goto L157;
            case 15: goto L158;
            case 16: goto L143;
            case 17: goto L142;
            case 18: goto L141;
            case 19: goto L140;
            case 20: goto L139;
            case 21: goto L157;
            case 22: goto L157;
            case 23: goto L138;
            case 24: goto L135;
            case 25: goto L133;
            case 26: goto L132;
            default: goto L130;
        };
    L132:
        addRow(r9, buildMediaRow(r3));
        goto L4
    L133:
        parseDetails(r3, r9, r10, r11);
        goto L4
    L135:
        if (hasPullClass(r3) == true) goto L136;
        parseBlockquote(r3, r9, r10, r11);
        goto L4
    L136:
        parsePullquote(r3, r9, r10);
        goto L4
    L138:
        addRow(r9, buildMediaRow(r3));
        goto L4
    L139:
        r9.add(parseTable(r3));
        goto L4
    L140:
        addRow(r9, buildMediaRow(r3));
        goto L4
    L141:
        TL_iv.pageBlockPreformatted r12 = new TL_iv.pageBlockPreformatted();
        r12.language = firstAttr(r3, new String[]{"language", "lang", "lng"});
        addText(r9, r12, r3, r10);
        goto L4
    L142:
        addRow(r9, buildMediaRow(r3));
        goto L4
    L143:
        String r13 = r3.attr("class");
        if (r13 != null) goto L146;
        String r14 = _UrlKt.FRAGMENT_ENCODE_SET;
    L148:
        if (r14.contains("button-row") == true) goto L149;
        if (r14.contains("collage") == true) goto L152;
        if (r14.contains("slideshow") == true) goto L155;
        addText(r9, new TL_iv.pageBlockParagraph(), r3, r10);
        goto L4
    L155:
        addRow(r9, parseGallery(r3, true));
        goto L4
    L152:
        addRow(r9, parseGallery(r3, false));
        goto L4
    L149:
        r9.add(parseButtonRow(r3));
        goto L4
    L146:
        r14 = r13.toLowerCase();
        goto L148
    L157:
        parseBlocks(r3.children, r9, r10, r11);
        goto L4
    L158:
        parseList(r3, r9, r10, "ol".equals(r1));
        goto L4
    L159:
        r9.add(new BlockRow(new TL_iv.pageBlockDivider()));
        goto L4
    L160:
        addText(r9, new TL_iv.pageBlockHeading6(), r3, r10);
        goto L4
    L161:
        addText(r9, new TL_iv.pageBlockHeading5(), r3, r10);
        goto L4
    L162:
        addText(r9, new TL_iv.pageBlockHeading4(), r3, r10);
        goto L4
    L163:
        addText(r9, new TL_iv.pageBlockHeading3(), r3, r10);
        goto L4
    L164:
        addText(r9, new TL_iv.pageBlockHeading2(), r3, r10);
        goto L4
    L165:
        addText(r9, new TL_iv.pageBlockHeading1(), r3, r10);
        goto L4
    L166:
        addText(r9, new TL_iv.pageBlockParagraph(), r3, r10);
        goto L4
    L167:
        addText(r9, new TL_iv.pageBlockFooter(), r3, r10);
        goto L4
    L168:
        parseFigure(r3, r9, r10);
        goto L4
    L169:
        addText(r9, new TL_iv.pageBlockParagraph(), r3, r10);
        goto L4
    L130:
        if (r3.children.isEmpty() == true) goto L4;
        parseBlocks(r3.children, r9, r10, r11);
        goto L4
    L21:
        if (r1.equals("location") == false) goto L128;
        r7 = 26;
        goto L128
    L25:
        if (r1.equals("details") == false) goto L128;
        r7 = 25;
        goto L128
    L29:
        if (r1.equals("blockquote") == false) goto L128;
        r7 = 24;
        goto L128
    L33:
        if (r1.equals(MediaStreamTrack.VIDEO_TRACK_KIND) == false) goto L128;
        r7 = 23;
        goto L128
    L37:
        if (r1.equals("thead") == false) goto L128;
        r7 = 22;
        goto L128
    L41:
        if (r1.equals("tbody") == false) goto L128;
        r7 = 21;
        goto L128
    L45:
        if (r1.equals("table") == false) goto L128;
        r7 = 20;
        goto L128
    L49:
        if (r1.equals(MediaStreamTrack.AUDIO_TRACK_KIND) == false) goto L128;
        r7 = 19;
        goto L128
    L53:
        if (r1.equals("pre") == false) goto L128;
        r7 = 18;
        goto L128
    L57:
        if (r1.equals("img") == false) goto L128;
        r7 = 17;
        goto L128
    L61:
        if (r1.equals("div") == false) goto L128;
        r7 = 16;
        goto L128
    L65:
        if (r1.equals("ul") == false) goto L128;
        r7 = 15;
        goto L128
    L69:
        if (r1.equals("tr") == false) goto L128;
        r7 = 14;
        goto L128
    L73:
        if (r1.equals("th") == false) goto L128;
        r7 = '\r';
        goto L128
    L77:
        if (r1.equals("td") == false) goto L128;
        r7 = '\f';
        goto L128
    L81:
        if (r1.equals("ol") == false) goto L128;
        r7 = 11;
        goto L128
    L85:
        if (r1.equals("hr") == false) goto L128;
        r7 = '\n';
        goto L128
    L89:
        if (r1.equals("h6") == false) goto L128;
        r7 = '\t';
        goto L128
    L93:
        if (r1.equals("h5") == false) goto L128;
        r7 = '\b';
        goto L128
    L97:
        if (r1.equals("h4") == false) goto L128;
        r7 = 7;
        goto L128
    L101:
        if (r1.equals("h3") == false) goto L128;
        r7 = 6;
        goto L128
    L105:
        if (r1.equals("h2") == false) goto L128;
        r7 = 5;
        goto L128
    L109:
        if (r1.equals("h1") == false) goto L128;
        r7 = 4;
        goto L128
    L113:
        if (r1.equals("p") == false) goto L128;
        r7 = 3;
        goto L128
    L117:
        if (r1.equals("footer") == false) goto L128;
        r7 = 2;
        goto L128
    L121:
        if (r1.equals("figure") == false) goto L128;
        r7 = 1;
        goto L128
    L125:
        if (r1.equals("summary") == false) goto L128;
        r7 = 0;
        goto L128
    L14:
        if (r0 != null) goto L16;
        r0 = new SpannableStringBuilder();
    L16:
        SpannableStringBuilder r2 = r0;
        appendInlineNode(r2, r3, 0, null, 0);
        r0 = r2;
        goto L4
    L8:
        if (isBlank(r3.text) == true) goto L4;
        if (r0 != null) goto L11;
        r0 = new SpannableStringBuilder();
    L11:
        r0.append(decode(r3.text));
        goto L4
    L170:
        flushParagraph(r9, r0, r10);
    }

    private static BlockRow parseButtonRow(Node r10) {
        TL_iv.pageBlockButtonRow r0 = new TL_iv.pageBlockButtonRow();
        String r1 = r10.attr("data-align");
        r0.align_left = "left".equalsIgnoreCase(r1);
        r0.align_center = "center".equalsIgnoreCase(r1);
        r0.align_right = "right".equalsIgnoreCase(r1);
        ArrayList<Node> r102 = r10.children;
        int r12 = r102.size();
        int r2 = 0;
    L3:
        if (r2 >= r12) goto L20;
        Node r3 = r102.get(r2);
        r2 = r2 + 1;
        Node r5 = r3;
        if (r0.buttons.size() >= 8) goto L20;
        if (r5.isText == true) goto L3;
        if ("button".equals(r5.tag) == false) goto L3;
        TL_keyboard.InlineButtonType r32 = inlineButtonTypeOf(r5);
        if (r32 == null) goto L3;
        SpannableStringBuilder r4 = new SpannableStringBuilder();
        appendChildrenInline(r4, r5, 0, null, 0);
        CharSequence r42 = trim(r4);
        if (r42.length() == 0) goto L3;
        TL_keyboard.PageButton r6 = new TL_keyboard.PageButton();
        r6.text = RichTextStyle.fromSpannable(r42);
        r6.type = r32;
        r6.style = inlineButtonStyleOf(r5);
        r0.buttons.add(r6);
    L20:
        return new BlockRow(r0);
    }

    private static void parseDetails(Node r10, ArrayList<BlockRow> r11, int r12, Map<Long, TL_iv.RichText> r13) {
        TL_iv.pageBlockDetails r0 = new TL_iv.pageBlockDetails();
        r0.open = r10.has("open");
        r0.blocks = new ArrayList();
        SpannableStringBuilder r2 = new SpannableStringBuilder();
        ArrayList r1 = new ArrayList();
        ArrayList<Node> r102 = r10.children;
        int r8 = r102.size();
        int r3 = 0;
    L3:
        if (r3 >= r8) goto L11;
        int r9 = r3 + 1;
        Node r32 = r102.get(r3);
        if (r32.isText == false) goto L7;
    L9:
        r1.add(r32);
    L10:
        r3 = r9;
        goto L3
    L7:
        if ("summary".equals(r32.tag) == false) goto L9;
        appendChildrenInline(r2, r32, 0, null, 0);
        goto L10
    L11:
        r0.title = RichTextStyle.fromSpannable(trim(r2));
        r11.add(new BlockRow(r0));
        int r103 = r11.size();
        parseBlocks(r1, r11, r12, r13);
        if (r11.size() != r103) goto L14;
        r11.add(new BlockRow(new TL_iv.pageBlockParagraph()));
    L14:
        BlockRow r104 = new BlockRow(new TL_iv.pageBlockParagraph());
        r104.detailsEnd = true;
        r11.add(r104);
    }

    private static double parseDoubleAttr(String r0, double r1) {
        if (r0 != null) goto L8;
    L6:
        return r1;
    L8:
        return Double.parseDouble(r0.trim());
    }

    private static void parseFigure(Node r8, ArrayList<BlockRow> r9, int r10) {
        ArrayList<Node> r82 = r8.children;
        int r0 = r82.size();
        BlockRow r1 = null;
        CharSequence r3 = null;
        int r4 = 0;
    L3:
        if (r4 >= r0) goto L12;
        Node r5 = r82.get(r4);
        r4 = r4 + 1;
        Node r52 = r5;
        if (r52.isText == true) goto L3;
        if ("figcaption".equals(r52.tag) == true) goto L9;
        if (r1 != null) goto L3;
        r1 = buildMediaRow(r52);
        goto L3
    L9:
        r3 = inlineOf(r52);
        goto L3
    L12:
        if (r1 == null) goto L19;
        if (r3 != null) goto L15;
    L17:
        r9.add(r1);
        return;
    L15:
        if (r3.length() <= 0) goto L17;
        setCaption(r1.block, r3);
        goto L17
    L19:
        if (r3 != null) goto L21;
        return;
    L21:
        if (r3.length() <= 0) goto L35;
        TL_iv.pageBlockParagraph r83 = new TL_iv.pageBlockParagraph();
        r83.text = RichTextStyle.fromSpannable(r3);
        r9.add(new BlockRow(r83, r10, 0));
        return;
    }

    private static BlockRow parseGallery(Node r13, boolean r14) {
        if (r14 == false) goto L4;
        TL_iv.PageBlock r142 = new TL_iv.pageBlockSlideshow();
    L5:
        ArrayList<TL_iv.PageBlock> r0 = RichEditorListView.galleryItems(r142);
        ArrayList<Node> r132 = r13.children;
        int r1 = r132.size();
        int r5 = 0;
        CharSequence r4 = null;
    L6:
        if (r5 >= r1) goto L23;
        Node r6 = r132.get(r5);
        r5 = r5 + 1;
        Node r62 = r6;
        if (r62.isText == true) goto L6;
        if ("figcaption".equals(r62.tag) == true) goto L12;
        boolean r7 = MediaStreamTrack.VIDEO_TRACK_KIND.equals(r62.tag);
        if (r7 == true) goto L18;
        if ("img".equals(r62.tag) == false) goto L6;
    L18:
        long r11 = parseLongAttr(r62.attr("src"), 0);
        if (r11 <= 0) goto L6;
        TL_iv.PageBlock r63 = newMediaItemBlock(r7, r11, r62.has("data-spoiler"));
        setEmptyCaption(r63);
        r0.add(r63);
        goto L6
    L12:
        r4 = inlineOf(r62);
        goto L6
    L23:
        if (r0.isEmpty() == false) goto L26;
        return null;
    L26:
        if (r0.size() != 1) goto L34;
        TL_iv.PageBlock r133 = r0.get(0);
        if (r4 == null) goto L33;
        if (r4.length() <= 0) goto L33;
        setCaption(r133, r4);
    L33:
        return new BlockRow(r133);
    L34:
        setEmptyCaption(r142);
        if (r4 == null) goto L40;
        if (r4.length() <= 0) goto L40;
        setCaption(r142, r4);
    L40:
        return new BlockRow(r142);
    L4:
        r142 = new TL_iv.pageBlockCollage();
        goto L5
    }

    private static int parseIntAttr(String r0, int r1) {
        if (r0 != null) goto L8;
    L6:
        return r1;
    L8:
        return Integer.parseInt(r0.trim());
    }

    private static void parseList(Node r18, ArrayList<BlockRow> r19, int r20, boolean r21) {
        int r2 = r20 + 1;
        ArrayList<Node> r3 = r18.children;
        int r4 = r3.size();
        int r6 = 1;
        int r7 = 0;
    L3:
        if (r7 >= r4) goto L41;
        Node r8 = r3.get(r7);
        r7 = r7 + 1;
        Node r82 = r8;
        if (r82.isText == true) goto L3;
        if ("li".equals(r82.tag) == false) goto L3;
        SpannableStringBuilder r10 = new SpannableStringBuilder();
        ArrayList r9 = new ArrayList();
        ArrayList<Node> r11 = r82.children;
        int r12 = r11.size();
        int r13 = 0;
    L11:
        if (r13 >= r12) goto L25;
        int r16 = r13 + 1;
        Node r15 = r11.get(r13);
        if (r15.isText == true) goto L21;
        if ("ul".equals(r15.tag) == false) goto L17;
    L18:
        r9.add(r15);
    L19:
        ArrayList<Node> r1 = r11;
        int r17 = r12;
    L24:
        r11 = r1;
        r13 = r16;
        r12 = r17;
        goto L11
    L17:
        if ("ol".equals(r15.tag) == true) goto L18;
    L21:
        if (r15.isText == false) goto L23;
        r10.append(decode(r15.text));
        goto L19
    L23:
        r1 = r11;
        r17 = r12;
        appendInlineNode(r10, r15, 0, null, 0);
        goto L24
    L25:
        TL_iv.pageBlockParagraph r14 = new TL_iv.pageBlockParagraph();
        r14.text = RichTextStyle.fromSpannable(trim(r10));
        if (r21 == false) goto L28;
        int r112 = r6;
    L29:
        BlockRow r102 = new BlockRow(r14, r2, r112);
        if (r82.has("data-checkbox") == false) goto L32;
    L35:
        boolean r110 = true;
    L36:
        r102.checkbox = r110;
        r102.checked = r82.has("data-checked");
        r19.add(r102);
        int r111 = r9.size();
        int r83 = 0;
    L37:
        if (r83 >= r111) goto L39;
        Object r103 = r9.get(r83);
        r83 = r83 + 1;
        Node r104 = (Node) r103;
        parseList(r104, r19, r2, "ol".equals(r104.tag));
        goto L37
    L39:
        r6 = r6 + 1;
        goto L3
    L32:
        if (hasCheckboxClass(r82) == true) goto L35;
        r110 = false;
        goto L36
    L28:
        r112 = 0;
        goto L29
    }

    private static long parseLongAttr(String r0, long r1) {
        if (r0 != null) goto L8;
    L6:
        return r1;
    L8:
        return Long.parseLong(r0.trim());
    }

    private static void parsePullquote(Node r7, ArrayList<BlockRow> r8, int r9) {
        ArrayList<Node> r0 = r7.children;
        int r1 = r0.size();
        int r3 = 0;
    L4:
        if (r3 >= r1) goto L10;
        Node r5 = r0.get(r3);
        r3 = r3 + 1;
        Node r52 = r5;
        if (r52.isText == true) goto L4;
        if ("cite".equals(r52.tag) == false) goto L4;
    L11:
        SpannableStringBuilder r02 = new SpannableStringBuilder();
        appendChildrenInlineExcept(r02, r7, "cite");
        TL_iv.pageBlockPullquote r72 = new TL_iv.pageBlockPullquote();
        RichTextCell.applyStyledTextToBlock(r72, trim(r02));
        TL_iv.RichText r03 = citeAuthor(r52);
        if (r03 == null) goto L14;
        r72.caption = r03;
    L14:
        r8.add(new BlockRow(r72, r9, 0));
        return;
    L10:
        r52 = null;
        goto L11
    }

    private static BlockRow parseTable(Node r6) {
        TL_iv.pageBlockTable r0 = new TL_iv.pageBlockTable();
        r0.title = new TL_iv.textEmpty();
        r0.rows = new ArrayList();
        r0.bordered = r6.has("border");
        String r1 = r6.attr("class");
        boolean r2 = false;
        if (r1 != null) goto L5;
    L7:
        boolean r4 = false;
    L8:
        r0.striped = r4;
        if (r1 != null) goto L11;
    L13:
        r0.compact = r2;
        collectTableRows(r6, r0);
        if (r0.rows.isEmpty() == false) goto L17;
        TL_iv.pageTableRow r62 = new TL_iv.pageTableRow();
        ArrayList<TL_iv.pageTableCell> r12 = new ArrayList();
        r62.cells = r12;
        r12.add(TableModel.newEmptyCell());
        r0.rows.add(r62);
    L17:
        return new BlockRow(r0);
    L11:
        if (r1.toLowerCase().contains("compact") == false) goto L13;
        r2 = true;
        goto L13
    L5:
        if (r1.toLowerCase().contains("striped") == false) goto L7;
        r4 = true;
        goto L8
    }

    private static TL_iv.pageTableRow parseTableRow(Node r10) {
        TL_iv.pageTableRow r0 = new TL_iv.pageTableRow();
        r0.cells = new ArrayList();
        ArrayList<Node> r102 = r10.children;
        int r1 = r102.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L35;
        Node r4 = r102.get(r3);
        r3 = r3 + 1;
        Node r42 = r4;
        if (r42.isText == true) goto L3;
        if ("td".equals(r42.tag) == true) goto L11;
        if ("th".equals(r42.tag) == false) goto L3;
    L11:
        TL_iv.pageTableCell r5 = new TL_iv.pageTableCell();
        r5.colspan = parseIntAttr(r42.attr("colspan"), 0);
        r5.rowspan = parseIntAttr(r42.attr("rowspan"), 0);
        TableModel.applyStyledText(r5, inlineOf(r42));
        if ("th".equals(r42.tag) == false) goto L14;
    L17:
        boolean r6 = true;
    L18:
        TableModel.setHeader(r5, r6);
        String r62 = r42.attr("align");
        if (r62 != null) goto L22;
        r62 = alignFromStyle(r42.attr("style"));
    L22:
        if ("center".equalsIgnoreCase(r62) == false) goto L25;
        TableModel.setAlign(r5, 1);
    L27:
        String r43 = r42.attr("valign");
        if ("middle".equalsIgnoreCase(r43) == false) goto L31;
        TableModel.setVAlign(r5, 1);
    L33:
        r0.cells.add(r5);
        goto L3
    L31:
        if ("bottom".equalsIgnoreCase(r43) == false) goto L33;
        TableModel.setVAlign(r5, 2);
        goto L33
    L25:
        if ("right".equalsIgnoreCase(r62) == false) goto L27;
        TableModel.setAlign(r5, 2);
        goto L27
    L14:
        if (r42.has(PluginsConstants.Settings.TYPE_HEADER) == true) goto L17;
        r6 = false;
        goto L18
    L35:
        if (r0.cells.isEmpty() == false) goto L37;
        r0.cells.add(TableModel.newEmptyCell());
    L37:
        return r0;
    }

    public static String preToHtml(CharSequence r2, String r3) {
        String r22 = inlineToHtml(r2);
        if (r22.isEmpty() == false) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        StringBuilder r0 = new StringBuilder();
        if (TextUtils.isEmpty(r3) == true) goto L9;
        r0.append("<pre language=\"");
        r0.append(escapeAttr(r3));
        r0.append("\">");
    L10:
        r0.append(r22);
        r0.append("</pre>");
        return r0.toString();
    L9:
        r0.append("<pre>");
        goto L10
    }

    public static String serialize(List<BlockRow> r12, int r13, int r14, int r15, int r16, Map<Long, TL_iv.RichText> r17) {
        StringBuilder r0 = new StringBuilder();
        ListState r8 = new ListState(null);
        serializeRange(r0, r12, new int[]{r13}, r14, r13, r14, r15, r16, r8, false, 0, r17);
        r8.closeAll(r0);
        return r0.toString();
    }

    private static void serializeButtonRow(StringBuilder r5, TL_iv.pageBlockButtonRow r6) {
        r5.append("<div class=\"button-row\"");
        if (r6.align_left == false) goto L6;
        r5.append(" data-align=\"left\"");
    L12:
        r5.append(">");
        ArrayList<TL_keyboard.PageButton> r62 = r6.buttons;
        if (r62 == null) goto L19;
        int r0 = r62.size();
        int r1 = 0;
    L15:
        if (r1 >= r0) goto L19;
        TL_keyboard.PageButton r2 = r62.get(r1);
        r1 = r1 + 1;
        TL_keyboard.PageButton r22 = r2;
        if (r22 == null) goto L15;
        appendButton(r5, r22.text, r22.type, r22.style);
    L19:
        r5.append("</div>");
        return;
    L6:
        if (r6.align_center == false) goto L9;
        r5.append(" data-align=\"center\"");
        goto L12
    L9:
        if (r6.align_right == false) goto L11;
        r5.append(" data-align=\"right\"");
        goto L12
    L11:
        r5.append(" data-align=\"fill\"");
        goto L12
    }

    private static void serializeDetails(StringBuilder r13, List<BlockRow> r14, int[] r15, int r16, int r17, int r18, int r19, int r20, int r21, Map<Long, TL_iv.RichText> r22) {
        BlockRow r3 = r14.get(r15[0]);
        if (((TL_iv.pageBlockDetails) r3.block).open == false) goto L5;
        String r2 = "<details open>";
    L6:
        r13.append(r2);
        r13.append("<summary>");
        appendInline(r13, slicedStyled(r3, r15[0], r17, r18, r19, r20));
        r13.append("</summary>");
        r15[0] = r15[0] + 1;
        ListState r8 = new ListState(null);
        serializeRange(r13, r14, r15, r16, r17, r18, r19, r20, r8, true, r21, r22);
        r8.closeAll(r13);
        int r23 = r15[0];
        if (r23 <= r16) goto L9;
    L13:
        r13.append("</details>");
        return;
    L9:
        if (r23 >= r14.size()) goto L13;
        if (r14.get(r15[0]).detailsEnd == false) goto L13;
        r15[0] = r15[0] + 1;
        goto L13
    L5:
        r2 = "<details>";
        goto L6
    }

    private static void serializeGallery(StringBuilder r9, TL_iv.PageBlock r10, BlockRow r11) {
        if ((r10 instanceof TL_iv.pageBlockSlideshow) == false) goto L5;
        String r0 = "slideshow";
    L6:
        r9.append("<div class=\"");
        r9.append(r0);
        r9.append("\">");
        ArrayList<TL_iv.PageBlock> r02 = RichEditorListView.galleryItems(r10);
        if (r02 == null) goto L30;
        int r1 = 0;
    L10:
        if (r1 >= r02.size()) goto L30;
        TL_iv.PageBlock r8 = r02.get(r1);
        ArrayList<MediaUploadState> r2 = r11.medias;
        if (r2 != null) goto L14;
    L17:
        MediaUploadState r22 = null;
    L16:
        MediaUploadState r7 = r22;
        if ((r8 instanceof TL_iv.pageBlockVideo) == false) goto L25;
        long r5 = ((TL_iv.pageBlockVideo) r8).video_id;
        if (r5 == 0) goto L23;
        appendMediaTag(r9, MediaStreamTrack.VIDEO_TRACK_KIND, r5, r7, r8);
    L23:
        StringBuilder r3 = r9;
    L29:
        r1 = r1 + 1;
        r9 = r3;
        goto L10
    L25:
        if ((r8 instanceof TL_iv.pageBlockPhoto) == false) goto L23;
        long r52 = ((TL_iv.pageBlockPhoto) r8).photo_id;
        if (r52 == 0) goto L23;
        r3 = r9;
        appendMediaTag(r3, "img", r52, r7, r8);
        goto L29
    L14:
        if (r1 >= r2.size()) goto L17;
        r22 = r11.medias.get(r1);
    L30:
        StringBuilder r32 = r9;
        CharSequence r92 = captionOf(r10);
        if (r92 != null) goto L33;
    L35:
        r32.append("</div>");
        return;
    L33:
        if (r92.length() <= 0) goto L35;
        r32.append("<figcaption>");
        appendInline(r32, r92);
        r32.append("</figcaption>");
        goto L35
    L5:
        r0 = "collage";
        goto L6
    }

    private static void serializeLeaf(StringBuilder r7, BlockRow r8, int r9, int r10, int r11, int r12, int r13) {
        TL_iv.PageBlock r122 = r8.block;
        if ((r122 instanceof TL_iv.pageBlockDivider) == false) goto L7;
        r7.append("<hr>");
        return;
    L7:
        if ((r122 instanceof TL_iv.pageBlockButtonRow) == false) goto L11;
        serializeButtonRow(r7, (TL_iv.pageBlockButtonRow) r122);
        return;
    L11:
        if ((r122 instanceof TL_iv.pageBlockTable) == false) goto L15;
        serializeTable(r7, (TL_iv.pageBlockTable) r122);
        return;
    L15:
        if ((r122 instanceof TL_iv.pageBlockPhoto) == false) goto L19;
        serializeSingleMedia(r7, "img", ((TL_iv.pageBlockPhoto) r122).photo_id, r8.media, r122);
        return;
    L19:
        if ((r122 instanceof TL_iv.pageBlockVideo) == false) goto L23;
        serializeSingleMedia(r7, MediaStreamTrack.VIDEO_TRACK_KIND, ((TL_iv.pageBlockVideo) r122).video_id, r8.media, r122);
        return;
    L23:
        if ((r122 instanceof TL_iv.pageBlockAudio) == false) goto L27;
        serializeSingleMedia(r7, MediaStreamTrack.AUDIO_TRACK_KIND, ((TL_iv.pageBlockAudio) r122).audio_id, r8.media, r122);
        return;
    L27:
        if ((r122 instanceof TL_iv.pageBlockDocument) == false) goto L31;
        serializeSingleMedia(r7, "document", ((TL_iv.pageBlockDocument) r122).document_id, r8.media, r122);
        return;
    L31:
        if (RichEditorListView.isGallery(r122) == false) goto L35;
        serializeGallery(r7, r122, r8);
        return;
    L35:
        if ((r122 instanceof TL_iv.pageBlockMap) == false) goto L38;
        serializeMap(r7, (TL_iv.pageBlockMap) r122);
        return;
    L38:
        String r6 = blockTag(r122);
        if (r6 != null) goto L47;
        CharSequence r82 = captionOf(r122);
        if (r82 != null) goto L43;
        return;
    L43:
        if (r82.length() <= 0) goto L70;
        r7.append("<p>");
        appendInline(r7, r82);
        r7.append("</p>");
        return;
    L70:
        return;
    L47:
        if ((r122 instanceof TL_iv.pageBlockPreformatted) == false) goto L56;
        String r123 = ((TL_iv.pageBlockPreformatted) r122).language;
        if (TextUtils.isEmpty(r123) == true) goto L52;
        r7.append("<pre language=\"");
        r7.append(escapeAttr(r123));
        r7.append("\">");
    L53:
        appendInline(r7, slicedStyled(r8, r9, r10, r11, r12, r13));
        r7.append("</pre>");
        return;
    L52:
        r7.append("<pre>");
        goto L53
    L56:
        if ((r122 instanceof TL_iv.pageBlockPullquote) == false) goto L60;
        r7.append("<blockquote class=\"pull\">");
        appendInline(r7, slicedStyled(r8, r9, r10, r11, r12, r13));
        appendAuthorCite(r7, authorText(((TL_iv.pageBlockPullquote) r122).caption));
        r7.append("</blockquote>");
        return;
    L60:
        if ((r122 instanceof TL_iv.pageBlockBlockquote) == false) goto L67;
        TL_iv.pageBlockBlockquote r124 = (TL_iv.pageBlockBlockquote) r122;
        if (r124.collapsed == false) goto L64;
        r7.append("<blockquote collapsed>");
    L65:
        appendInline(r7, slicedStyled(r8, r9, r10, r11, r12, r13));
        appendAuthorCite(r7, authorText(r124.caption));
        r7.append("</blockquote>");
        return;
    L64:
        r7.append("<blockquote>");
        goto L65
    L67:
        r7.append(Typography.less);
        r7.append(r6);
        r7.append(Typography.greater);
        appendInline(r7, slicedStyled(r8, r9, r10, r11, r12, r13));
        r7.append("</");
        r7.append(r6);
        r7.append(Typography.greater);
    }

    private static void serializeMap(StringBuilder r8, TL_iv.pageBlockMap r9) {
        CharSequence r0 = captionOf(r9);
        if (r0 != null) goto L5;
    L7:
        boolean r1 = false;
    L8:
        if (r1 == false) goto L10;
        r8.append("<figure>");
    L10:
        r8.append("<location");
        if (r9.geo == null) goto L16;
        r8.append(" lat=\"");
        r8.append(r9.geo.lat);
        r8.append(Typography.quote);
        r8.append(" long=\"");
        r8.append(r9.geo._long);
        r8.append(Typography.quote);
        if (r9.geo.access_hash == 0) goto L16;
        r8.append(" access=\"");
        r8.append(r9.geo.access_hash);
        r8.append(Typography.quote);
    L16:
        if (r9.zoom == 0) goto L19;
        r8.append(" zoom=\"");
        r8.append(r9.zoom);
        r8.append(Typography.quote);
    L19:
        if (r9.w == 0) goto L22;
        r8.append(" w=\"");
        r8.append(r9.w);
        r8.append(Typography.quote);
    L22:
        if (r9.h == 0) goto L24;
        r8.append(" h=\"");
        r8.append(r9.h);
        r8.append(Typography.quote);
    L24:
        r8.append(" />");
        if (r1 == false) goto L28;
        r8.append("<figcaption>");
        appendInline(r8, r0);
        r8.append("</figcaption></figure>");
        return;
    L28:
        return;
    L5:
        if (r0.length() <= 0) goto L7;
        r1 = true;
        goto L8
    }

    private static void serializeQuote(StringBuilder r14, List<BlockRow> r15, int[] r16, int r17, int r18, int r19, int r20, int r21, boolean r22, int r23, Map<Long, TL_iv.RichText> r24) {
        Long r12 = r15.get(r16[0]).quoteIds.get(r23);
        long r2 = r12.longValue();
        int r1 = r16[0];
    L3:
        int r4 = r1 + 1;
        if (r4 > r17) goto L10;
        BlockRow r6 = r15.get(r4);
        if (r6.quoteIds.size() <= r23) goto L10;
        if (r6.quoteIds.get(r23).longValue() != r2) goto L10;
        r1 = r4;
    L10:
        r14.append("<blockquote>");
        CharSequence r13 = null;
        ListState r8 = new ListState(null);
        int r3 = r1;
        serializeRange(r14, r15, r16, r3, r18, r19, r20, r21, r8, r22, r23 + 1, r24);
        r8.closeAll(r14);
        if (r24 == null) goto L14;
        r13 = authorText(r24.get(r12));
    L14:
        appendAuthorCite(r14, r13);
        r14.append("</blockquote>");
    }

    private static void serializeRange(StringBuilder r18, List<BlockRow> r19, int[] r20, int r21, int r22, int r23, int r24, int r25, ListState r26, boolean r27, int r28, Map<Long, TL_iv.RichText> r29) {
        StringBuilder r0 = r18;
    L3:
        int r1 = r20[0];
        if (r1 > r21) goto L26;
        BlockRow r12 = r19.get(r1);
        if (r12.detailsEnd == true) goto L7;
        if (r12.quoteIds.size() > r28) goto L12;
        if (RichEditorListView.isDetailsHeader(r12) == true) goto L15;
        if (r12.level <= 0) goto L25;
        if (isTextBlock(r12.block) == false) goto L25;
        int r13 = r12.level;
        if (r12.num <= 0) goto L23;
        boolean r2 = true;
    L24:
        r26.sync(r0, r13, r2);
        r0.append("<li>");
        appendInline(r0, slicedStyled(r12, r20[0], r22, r23, r24, r25));
        r0.append("</li>");
        r20[0] = r20[0] + 1;
        goto L3
    L23:
        r2 = false;
    L25:
        r26.closeAll(r0);
        serializeLeaf(r0, r12, r20[0], r22, r23, r24, r25);
        r20[0] = r20[0] + 1;
        r0 = r18;
        goto L3
    L15:
        r26.closeAll(r0);
        serializeDetails(r0, r19, r20, r21, r22, r23, r24, r25, r28, r29);
        goto L3
    L12:
        r26.closeAll(r0);
        serializeQuote(r0, r19, r20, r21, r22, r23, r24, r25, r27, r28, r29);
        goto L3
    L7:
        if (r27 == true) goto L44;
        r20[0] = r20[0] + 1;
        goto L3
    L44:
        return;
    }

    private static void serializeSingleMedia(StringBuilder r3, String r4, long r5, MediaUploadState r7, TL_iv.PageBlock r8) {
        if (r5 == 0) goto L18;
        CharSequence r0 = captionOf(r8);
        if (r0 != null) goto L8;
    L10:
        boolean r1 = false;
    L11:
        if (r1 == false) goto L13;
        r3.append("<figure>");
    L13:
        appendMediaTag(r3, r4, r5, r7, r8);
        if (r1 == false) goto L17;
        r3.append("<figcaption>");
        appendInline(r3, r0);
        r3.append("</figcaption></figure>");
        return;
    L17:
        return;
    L8:
        if (r0.length() <= 0) goto L10;
        r1 = true;
        goto L11
    }

    private static void serializeTable(StringBuilder r14, TL_iv.pageBlockTable r15) {
        r14.append("<table");
        if (r15.bordered == false) goto L6;
        r14.append(" border=\"1\"");
    L6:
        if (r15.striped == false) goto L8;
    L9:
        r14.append(" class=\"");
        if (r15.striped == false) goto L13;
        r14.append("striped");
    L13:
        if (r15.striped == false) goto L18;
        if (r15.compact == false) goto L18;
        r14.append(' ');
    L18:
        if (r15.compact == false) goto L20;
        r14.append("compact");
    L20:
        r14.append(Typography.quote);
    L21:
        r14.append(Typography.greater);
        TL_iv.RichText r2 = r15.title;
        if (r2 == null) goto L24;
        CharSequence r22 = RichTextStyle.toSpannable(r2);
    L25:
        if (r22 != null) goto L27;
    L29:
        ArrayList<TL_iv.pageTableRow> r152 = r15.rows;
        if (r152 == null) goto L78;
        int r23 = r152.size();
        int r5 = 0;
    L32:
        if (r5 >= r23) goto L78;
        TL_iv.pageTableRow r6 = r152.get(r5);
        r5 = r5 + 1;
        TL_iv.pageTableRow r62 = r6;
        r14.append("<tr>");
        if (r62 == null) goto L77;
        ArrayList<TL_iv.pageTableCell> r63 = r62.cells;
        if (r63 == null) goto L77;
        int r7 = r63.size();
        int r8 = 0;
    L38:
        if (r8 >= r7) goto L77;
        TL_iv.pageTableCell r9 = r63.get(r8);
        r8 = r8 + 1;
        TL_iv.pageTableCell r92 = r9;
        if (r92 == null) goto L38;
        if (r92.header == false) goto L45;
        String r10 = "th";
    L46:
        r14.append(Typography.less);
        r14.append(r10);
        int r11 = r92.colspan;
        if (r11 > 1) goto L50;
        r11 = 0;
    L50:
        if (r11 <= 0) goto L52;
        r14.append(" colspan=\"");
        r14.append(r11);
        r14.append(Typography.quote);
    L52:
        int r112 = r92.rowspan;
        if (r112 > 1) goto L56;
        r112 = 0;
    L56:
        if (r112 <= 0) goto L59;
        r14.append(" rowspan=\"");
        r14.append(r112);
        r14.append(Typography.quote);
    L59:
        if (r92.align_right == false) goto L62;
        String r113 = "right";
    L65:
        if (r113 == null) goto L68;
        r14.append(" align=\"");
        r14.append(r113);
        r14.append(Typography.quote);
    L68:
        if (r92.valign_bottom == false) goto L71;
        String r114 = "bottom";
    L74:
        if (r114 == null) goto L76;
        r14.append(" valign=\"");
        r14.append(r114);
        r14.append(Typography.quote);
    L76:
        r14.append(Typography.greater);
        appendInline(r14, TableModel.readStyledText(r92));
        r14.append("</");
        r14.append(r10);
        r14.append(Typography.greater);
        goto L38
    L71:
        if (r92.valign_middle == false) goto L73;
        r114 = "middle";
        goto L74
    L73:
        r114 = null;
        goto L74
    L62:
        if (r92.align_center == false) goto L64;
        r113 = "center";
        goto L65
    L64:
        r113 = null;
        goto L65
    L45:
        r10 = "td";
    L77:
        r14.append("</tr>");
    L78:
        r14.append("</table>");
        return;
    L27:
        if (r22.length() <= 0) goto L29;
        r14.append("<caption>");
        appendInline(r14, r22);
        r14.append("</caption>");
        goto L29
    L24:
        r22 = null;
        goto L25
    L8:
        if (r15.compact == false) goto L21;
        goto L9
    }

    private static void setCaption(TL_iv.PageBlock r1, CharSequence r2) {
        TL_iv.PageCaption r0 = new TL_iv.PageCaption();
        r0.text = RichTextStyle.fromSpannable(r2);
        r0.credit = new TL_iv.textEmpty();
        r1.caption = r0;
    }

    private static void setEmptyCaption(TL_iv.PageBlock r2) {
        TL_iv.PageCaption r0 = new TL_iv.PageCaption();
        r0.text = new TL_iv.textEmpty();
        r0.credit = new TL_iv.textEmpty();
        r2.caption = r0;
    }

    private static CharSequence slicedStyled(BlockRow r3, int r4, int r5, int r6, int r7, int r8) {
        if (RichEditorListView.isDetailsHeader(r3) == false) goto L5;
        CharSequence r32 = RichTextStyle.toSpannable(((TL_iv.pageBlockDetails) r3.block).title);
    L6:
        if (r32 != null) goto L8;
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        int r0 = r32.length();
        if (r4 != r5) goto L11;
        int r52 = Math.max(0, Math.min(r7, r0));
    L12:
        if (r4 != r6) goto L14;
        int r42 = Math.max(0, Math.min(r8, r0));
    L15:
        if (r52 <= r42) goto L17;
        int r2 = r52;
        r52 = r42;
        r42 = r2;
    L17:
        if (r52 != 0) goto L21;
        if (r42 != r0) goto L21;
        return r32;
    L21:
        return r32.subSequence(r52, r42);
    L14:
        r42 = r0;
        goto L15
    L11:
        r52 = 0;
        goto L12
    L5:
        r32 = RichTextCell.readStyledText(r3.block);
        goto L6
    }

    public static String tableToHtml(TL_iv.pageBlockTable r1) {
        if (r1 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        StringBuilder r0 = new StringBuilder();
        serializeTable(r0, r1);
        return r0.toString();
    }

    private static CharSequence trim(SpannableStringBuilder r3) {
        int r0 = r3.length();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        if (isWs(r3.charAt(r1)) == false) goto L7;
        r1 = r1 + 1;
    L7:
        if (r0 <= r1) goto L11;
        if (isWs(r3.charAt(r0 - 1)) == false) goto L11;
        r0 = r0 - 1;
    L11:
        if (r1 != 0) goto L16;
        if (r0 != r3.length()) goto L16;
        return r3;
    L16:
        return r3.subSequence(r1, r0);
    }
}
