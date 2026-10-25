package org.telegram.messenger.utils;

import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.telegram.messenger.CodeHighlighting;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MediaDataController;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.QuoteSpan;
import org.telegram.ui.Components.URLSpanReplacement;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.XMLReader;

/* loaded from: classes6.dex */
public abstract class CopyUtilities {

    public static class HTMLTagAttributesHandler implements Html.TagHandler, ContentHandler {
        private final TagHandler handler;
        private ArrayDeque<Boolean> tagStatus;
        private Editable text;
        private ContentHandler wrapped;

        public interface TagHandler {
            boolean handleTag(boolean r1, String r2, Editable r3, Attributes r4);
        }

        private HTMLTagAttributesHandler(TagHandler r2) {
            this.tagStatus = new ArrayDeque();
            this.handler = r2;
        }

        public /* synthetic */ HTMLTagAttributesHandler(TagHandler r1, CopyUtilitiesIA r2) {
            this(r1);
        }

        public static String getValue(Attributes r3, String r4) {
            int r0 = r3.getLength();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L9;
            if (r4.equals(r3.getLocalName(r1)) == true) goto L7;
            r1 = r1 + 1;
            goto L3
        L7:
            return r3.getValue(r1);
        L9:
            return null;
        }

        @Override // org.xml.sax.ContentHandler
        public void characters(char[] r1, int r2, int r3) {
            this.wrapped.characters(r1, r2, r3);
        }

        @Override // org.xml.sax.ContentHandler
        public void endDocument() {
            this.wrapped.endDocument();
        }

        @Override // org.xml.sax.ContentHandler
        public void endElement(String r2, String r3, String r4) {
            if (this.tagStatus.removeLast().booleanValue() == true) goto L5;
            this.wrapped.endElement(r2, r3, r4);
        L5:
            this.handler.handleTag(false, r3, this.text, null);
        }

        @Override // org.xml.sax.ContentHandler
        public void endPrefixMapping(String r1) {
            this.wrapped.endPrefixMapping(r1);
        }

        @Override // android.text.Html.TagHandler
        public void handleTag(boolean r1, String r2, Editable r3, XMLReader r4) {
            if (this.wrapped != null) goto L6;
            this.text = r3;
            this.wrapped = r4.getContentHandler();
            r4.setContentHandler(this);
            this.tagStatus.addLast(Boolean.FALSE);
            return;
        }

        @Override // org.xml.sax.ContentHandler
        public void ignorableWhitespace(char[] r1, int r2, int r3) {
            this.wrapped.ignorableWhitespace(r1, r2, r3);
        }

        @Override // org.xml.sax.ContentHandler
        public void processingInstruction(String r1, String r2) {
            this.wrapped.processingInstruction(r1, r2);
        }

        @Override // org.xml.sax.ContentHandler
        public void setDocumentLocator(Locator r1) {
            this.wrapped.setDocumentLocator(r1);
        }

        @Override // org.xml.sax.ContentHandler
        public void skippedEntity(String r1) {
            this.wrapped.skippedEntity(r1);
        }

        @Override // org.xml.sax.ContentHandler
        public void startDocument() {
            this.wrapped.startDocument();
        }

        @Override // org.xml.sax.ContentHandler
        public void startElement(String r4, String r5, String r6, Attributes r7) {
            boolean r0 = this.handler.handleTag(true, r5, this.text, r7);
            this.tagStatus.addLast(Boolean.valueOf(r0));
            if (r0 == true) goto L6;
            this.wrapped.startElement(r4, r5, r6, r7);
            return;
        }

        @Override // org.xml.sax.ContentHandler
        public void startPrefixMapping(String r1, String r2) {
            this.wrapped.startPrefixMapping(r1, r2);
        }
    }

    public static class HTMLTagHandler implements HTMLTagAttributesHandler.TagHandler {
        private HTMLTagHandler() {
        }

        public /* synthetic */ HTMLTagHandler(CopyUtilitiesIA r1) {
            this();
        }

        private <T> T getLast(Editable r5, Class<T> r6) {
            Object[] r4 = r5.getSpans(0, r5.length(), r6);
            if (r4.length != 0) goto L5;
            return null;
        L5:
            int r62 = r4.length;
        L6:
            if (r62 <= 0) goto L12;
            int r1 = r62 - 1;
            if (r5.getSpanFlags(r4[r1]) == 17) goto L10;
            r62 = r62 - 1;
            goto L6
        L10:
            return (T) r4[r1];
        L12:
            return null;
        }

        private <T extends ParsedSpan> T getLast(Editable r5, Class<T> r6, int r7) {
            ParsedSpan[] r4 = (ParsedSpan[]) r5.getSpans(0, r5.length(), r6);
            if (r4.length != 0) goto L5;
            return null;
        L5:
            int r62 = r4.length;
        L6:
            if (r62 <= 0) goto L13;
            int r1 = r62 - 1;
            if (r5.getSpanFlags(r4[r1]) != 17) goto L12;
            T r12 = (T) r4[r1];
            if (r12.type != r7) goto L12;
            return r12;
        L12:
            r62 = r62 - 1;
            goto L6
        L13:
            return null;
        }

        private ParsedSpan getLastQuote(Editable r5) {
            ParsedSpan[] r4 = (ParsedSpan[]) r5.getSpans(0, r5.length(), ParsedSpan.class);
            int r0 = r4.length - 1;
        L3:
            if (r0 < 0) goto L12;
            ParsedSpan r1 = r4[r0];
            if (r5.getSpanFlags(r1) != 17) goto L11;
            int r2 = r1.type;
            if (r2 == 2) goto L10;
            if (r2 != 3) goto L11;
        L10:
            return r1;
        L11:
            r0 = r0 - 1;
            goto L3
        L12:
            return null;
        }

        @Override // org.telegram.messenger.utils.CopyUtilities.HTMLTagAttributesHandler.TagHandler
        public boolean handleTag(boolean r9, String r10, Editable r11, Attributes r12) {
            boolean r1 = false;
            if (r10.startsWith("animated-emoji") == false) goto L16;
            if (r9 == false) goto L9;
            String r8 = HTMLTagAttributesHandler.getValue(r12, "data-document-id");
            if (r8 == null) goto L89;
            r11.setSpan(new AnimatedEmojiSpan(Long.parseLong(r8), null), r11.length(), r11.length(), 17);
            return true;
        L89:
            return false;
        L9:
            AnimatedEmojiSpan r82 = (AnimatedEmojiSpan) getLast(r11, AnimatedEmojiSpan.class);
            if (r82 == null) goto L89;
            int r92 = r11.getSpanStart(r82);
            r11.removeSpan(r82);
            if (r92 == r11.length()) goto L14;
            r11.setSpan(r82, r92, r11.length(), 33);
        L14:
            return true;
        L16:
            if (r10.equals("spoiler") == false) goto L27;
            if (r9 == false) goto L20;
            r11.setSpan(new ParsedSpan(0, null), r11.length(), r11.length(), 17);
            return true;
        L20:
            ParsedSpan r83 = getLast(r11, ParsedSpan.class, 0);
            if (r83 == null) goto L89;
            int r93 = r11.getSpanStart(r83);
            r11.removeSpan(r83);
            if (r93 == r11.length()) goto L25;
            r11.setSpan(r83, r93, r11.length(), 33);
        L25:
            return true;
        L27:
            if (r10.equals("pre") == false) goto L29;
        L65:
            boolean r102 = r10.equals("pre");
            if (r102 == false) goto L68;
        L70:
            if (r102 == false) goto L72;
            int r0 = 1;
        L73:
            if (r9 == false) goto L83;
            if (r102 == false) goto L80;
            String r84 = HTMLTagAttributesHandler.getValue(r12, "language");
            if (r84 != null) goto L78;
            r84 = HTMLTagAttributesHandler.getValue(r12, "lang");
        L78:
            if (r84 != null) goto L81;
            r84 = HTMLTagAttributesHandler.getValue(r12, "lng");
        L81:
            r11.setSpan(new ParsedSpan(r0, r84, null), r11.length(), r11.length(), 17);
            return true;
        L80:
            r84 = null;
            goto L81
        L83:
            ParsedSpan r85 = getLast(r11, ParsedSpan.class, r0);
            if (r85 == null) goto L89;
            int r94 = r11.getSpanStart(r85);
            r11.removeSpan(r85);
            if (r94 == r11.length()) goto L88;
            r11.setSpan(r85, r94, r11.length(), 33);
        L88:
            return true;
        L72:
            r0 = 4;
            goto L73
        L68:
            if (getLast(r11, ParsedSpan.class, 1) == null) goto L70;
            return false;
        L29:
            if (r10.equals("code") == true) goto L65;
            if (r10.equals("tt") == true) goto L65;
            int r7 = 3;
            if (r10.equals("blockquote") == false) goto L55;
            if (r9 == false) goto L48;
            String r86 = HTMLTagAttributesHandler.getValue(r12, "class");
            if (HTMLTagAttributesHandler.getValue(r12, "data-collapsed") != null) goto L41;
            if (r86 == null) goto L43;
            if (r86.contains("telegram-collapsed-quote") == true) goto L41;
        L43:
            if (r1 == true) goto L46;
            r7 = 2;
        L46:
            r11.setSpan(new ParsedSpan(r7, null), r11.length(), r11.length(), 17);
            return true;
        L41:
            r1 = true;
            goto L43
        L48:
            ParsedSpan r87 = getLastQuote(r11);
            if (r87 == null) goto L89;
            int r95 = r11.getSpanStart(r87);
            r11.removeSpan(r87);
            if (r95 == r11.length()) goto L53;
            r11.setSpan(r87, r95, r11.length(), 33);
        L53:
            return true;
        L55:
            if (r10.equals("details") == false) goto L89;
            if (r9 == false) goto L59;
            r11.setSpan(new ParsedSpan(3, null), r11.length(), r11.length(), 17);
            return true;
        L59:
            ParsedSpan r88 = getLast(r11, ParsedSpan.class, 3);
            if (r88 == null) goto L89;
            int r96 = r11.getSpanStart(r88);
            r11.removeSpan(r88);
            if (r96 == r11.length()) goto L64;
            r11.setSpan(r88, r96, r11.length(), 33);
        L64:
            return true;
        }
    }

    public static class ParsedSpan {
        final String lng;
        final int type;

        private ParsedSpan(int r1) {
            this.type = r1;
            this.lng = null;
        }

        private ParsedSpan(int r1, String r2) {
            this.type = r1;
            this.lng = r2;
        }

        public /* synthetic */ ParsedSpan(int r1, String r2, CopyUtilitiesIA r3) {
            this(r1, r2);
        }

        public /* synthetic */ ParsedSpan(int r1, CopyUtilitiesIA r2) {
            this(r1);
        }
    }

    public static Spannable fromHTML(String r19) {
        Spanned r0 = Html.fromHtml("<inject>" + r19 + "</inject>", 63, null, new HTMLTagAttributesHandler(new HTMLTagHandler(null), null));     // Catch: Exception -> L67
        if (r0 != null) goto L6;
        return null;
    L6:
        Object[] r2 = r0.getSpans(0, r0.length(), Object.class);
        ArrayList r3 = new ArrayList(r2.length);
        ArrayList r5 = new ArrayList();
        ArrayList r6 = new ArrayList();
        int r7 = 0;
    L8:
        if (r7 >= r2.length) goto L44;
        Object r8 = r2[r7];
        int r11 = r0.getSpanStart(r8);
        int r12 = r0.getSpanEnd(r8);
        if ((r8 instanceof StyleSpan) == false) goto L18;
        int r82 = ((StyleSpan) r8).getStyle();
        if ((r82 & 1) <= 0) goto L15;
        r3.add(setEntityStartEnd(new TLRPC.TL_messageEntityBold(), r11, r12));
    L15:
        if ((r82 & 2) <= 0) goto L43;
        r3.add(setEntityStartEnd(new TLRPC.TL_messageEntityItalic(), r11, r12));
    L43:
        r7 = r7 + 1;
        goto L8
    L18:
        if ((r8 instanceof UnderlineSpan) == false) goto L21;
        r3.add(setEntityStartEnd(new TLRPC.TL_messageEntityUnderline(), r11, r12));
        goto L43
    L21:
        if ((r8 instanceof StrikethroughSpan) == false) goto L24;
        r3.add(setEntityStartEnd(new TLRPC.TL_messageEntityStrike(), r11, r12));
        goto L43
    L24:
        if ((r8 instanceof ParsedSpan) == false) goto L41;
        ParsedSpan r83 = (ParsedSpan) r8;
        int r13 = r83.type;
        if (r13 != 0) goto L28;
        r3.add(setEntityStartEnd(new TLRPC.TL_messageEntitySpoiler(), r11, r12));
        goto L43
    L28:
        if (r13 != 1) goto L34;
        if (TextUtils.isEmpty(r83.lng) == true) goto L32;
        r5.add(r83);
        goto L43
    L32:
        r3.add(setEntityStartEnd(new TLRPC.TL_messageEntityPre(), r11, r12));
        goto L43
    L34:
        if (r13 != 4) goto L37;
        r3.add(setEntityStartEnd(new TLRPC.TL_messageEntityCode(), r11, r12));
        goto L43
    L37:
        if (r13 == 2) goto L39;
        if (r13 != 3) goto L43;
    L39:
        r6.add(r83);
        goto L43
    L41:
        if ((r8 instanceof AnimatedEmojiSpan) == false) goto L43;
        TLRPC.TL_messageEntityCustomEmoji r9 = new TLRPC.TL_messageEntityCustomEmoji();
        AnimatedEmojiSpan r84 = (AnimatedEmojiSpan) r8;
        r9.document_id = r84.documentId;
        r9.document = r84.document;
        r9.local = r84.local;
        r3.add(setEntityStartEnd(r9, r11, r12));
        goto L43
    L44:
        SpannableStringBuilder r72 = new SpannableStringBuilder(r0.toString());
        MediaDataController.addTextStyleRuns(r3, r72, r72);
        int r85 = 0;
    L46:
        if (r85 >= r2.length) goto L54;
        Object r112 = r2[r85];
        if ((r112 instanceof URLSpan) == false) goto L53;
        int r132 = r0.getSpanStart(r112);
        int r14 = r0.getSpanEnd(r112);
        String r15 = r0.subSequence(r132, r14).toString();
        String r113 = ((URLSpan) r112).getURL();
        if (r15.equals(r113) == false) goto L52;
        r72.setSpan(new URLSpan(r113), r132, r14, 33);
        goto L53
    L52:
        r72.setSpan(new URLSpanReplacement(r113), r132, r14, 33);
    L53:
        r85 = r85 + 1;
        goto L46
    L54:
        MediaDataController.addAnimatedEmojiSpans(r3, r72, null);
        int r1 = 0;
    L56:
        if (r1 >= r5.size()) goto L58;
        ParsedSpan r22 = (ParsedSpan) r5.get(r1);
        int r32 = r0.getSpanStart(r22);
        int r86 = r0.getSpanEnd(r22);
        r72.setSpan(new CodeHighlighting.Span(true, 0, null, r22.lng, r72.subSequence(r32, r86).toString()), r32, r86, 33);
        r1 = r1 + 1;
        goto L56
    L58:
        int r16 = 0;
    L60:
        if (r16 >= r6.size()) goto L66;
        ParsedSpan r23 = (ParsedSpan) r6.get(r16);
        int r33 = r0.getSpanStart(r23);
        int r52 = r0.getSpanEnd(r23);
        if (r23.type != 3) goto L64;
        boolean r24 = true;
    L65:
        QuoteSpan.putQuoteToEditable(r72, r33, r52, r24);
        r16 = r16 + 1;
        goto L60
    L64:
        r24 = false;
        goto L65
    L66:
        return r72;
    L67:
        e = move-exception;
        FileLog.e("Html.fromHtml", e);
        return null;
    }

    private static TLRPC.MessageEntity setEntityStartEnd(TLRPC.MessageEntity r0, int r1, int r2) {
        r0.offset = r1;
        r0.length = r2 - r1;
        return r0;
    }
}
