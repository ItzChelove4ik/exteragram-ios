package org.telegram.messenger;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.TextStyleSpan;

/* loaded from: classes.dex */
public class CodeHighlighting {
    public static final int MATCH_COMMENT = 6;
    public static final int MATCH_CONSTANT = 3;
    public static final int MATCH_FUNCTION = 7;
    public static final int MATCH_KEYWORD = 1;
    public static final int MATCH_NONE = 0;
    public static final int MATCH_NUMBER = 5;
    public static final int MATCH_OPERATOR = 2;
    public static final int MATCH_STRING = 4;
    private static HashMap<String, TokenPattern[]> compiledPatterns;
    private static HashSet<String> languages;
    private static final ConcurrentHashMap<String, Highlighting> processedHighlighting = null;

    /* loaded from: classes5.dex */
    public static class CachedPattern {
        private Pattern pattern;
        private String patternSource;
        private int patternSourceFlags;

        /* renamed from: -$$Nest$fgetpatternSource, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m3944$$Nest$fgetpatternSource(CachedPattern r0) {
            return r0.patternSource;
        }

        public CachedPattern(String r1, int r2) {
            this.patternSource = r1;
            this.patternSourceFlags = r2;
        }

        public Pattern getPattern() {
            if (this.pattern != null) goto L6;
            this.pattern = Pattern.compile(this.patternSource, this.patternSourceFlags);
        L6:
            return this.pattern;
        }
    }

    /* loaded from: classes5.dex */
    public static class CachedToSpan {
        public int end;
        public int group;
        public int start;

        public CachedToSpan(int r1, int r2, int r3) {
            this.group = r1;
            this.start = r2;
            this.end = r3;
        }
    }

    public static class ColorSpan extends CharacterStyle {
        public int group;

        public ColorSpan(int r1) {
            this.group = r1;
        }

        public int getColorKey() {
            switch(this.group) {
                case 1: goto L19;
                case 2: goto L17;
                case 3: goto L15;
                case 4: goto L13;
                case 5: goto L11;
                case 6: goto L9;
                case 7: goto L7;
                default: goto L4;
            };
        L4:
            return -1;
        L7:
            return Theme.key_code_function;
        L9:
            return Theme.key_code_comment;
        L11:
            return Theme.key_code_number;
        L13:
            return Theme.key_code_string;
        L15:
            return Theme.key_code_constant;
        L17:
            return Theme.key_code_operator;
        L19:
            return Theme.key_code_keyword;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            r1.setColor(Theme.getColor(getColorKey()));
        }
    }

    /* loaded from: classes5.dex */
    public static class Highlighting {
        String language;
        SpannableString result;
        CharSequence text;

        private Highlighting() {
        }

        public /* synthetic */ Highlighting(CodeHighlightingIA r1) {
            this();
        }
    }

    /* loaded from: classes5.dex */
    public static class LinkedList {
        public Node head;
        public int length;
        public Node tail;

        public LinkedList() {
            this.length = 0;
            this.head = new Node(null);
            Node r0 = new Node(null);
            this.tail = r0;
            Node r2 = this.head;
            r2.next = r0;
            r0.prev = r2;
        }

        public Node addAfter(Node r4, StringToken r5) {
            Node r0 = r4.next;
            Node r1 = new Node(null);
            r1.value = r5;
            r1.prev = r4;
            r1.next = r0;
            r4.next = r1;
            r0.prev = r1;
            this.length++;
            return r1;
        }

        public void removeRange(Node r4, int r5) {
            Node r0 = r4.next;
            int r1 = 0;
        L3:
            if (r1 >= r5) goto L7;
            if (r0 == this.tail) goto L7;
            r0 = r0.next;
            r1 = r1 + 1;
        L7:
            r4.next = r0;
            r0.prev = r4;
            this.length -= r1;
        }

        public StringToken[] toArray() {
            StringToken[] r0 = new StringToken[this.length];
            Node r1 = this.head.next;
            int r2 = 0;
        L4:
            if (r2 >= this.length) goto L8;
            if (r1 == this.tail) goto L8;
            r0[r2] = r1.value;
            r1 = r1.next;
            r2 = r2 + 1;
        L8:
            return r0;
        }
    }

    /* loaded from: classes5.dex */
    public static class LockedSpannableString extends SpannableString {
        public boolean ready;

        public LockedSpannableString(CharSequence r1) {
            super(r1);
            this.ready = false;
        }

        @Override // android.text.SpannableString, android.text.Spanned
        public int getSpanEnd(Object r2) {
            if (this.ready == true) goto L7;
            return -1;
        L7:
            return super.getSpanEnd(r2);
        }

        @Override // android.text.SpannableString, android.text.Spanned
        public int getSpanFlags(Object r2) {
            if (this.ready == true) goto L7;
            return 0;
        L7:
            return super.getSpanFlags(r2);
        }

        @Override // android.text.SpannableString, android.text.Spanned
        public int getSpanStart(Object r2) {
            if (this.ready == true) goto L7;
            return -1;
        L7:
            return super.getSpanStart(r2);
        }

        @Override // android.text.SpannableString, android.text.Spanned
        public <T> T[] getSpans(int r2, int r3, Class<T> r4) {
            if (this.ready == true) goto L7;
            return (T[]) ((Object[]) Array.newInstance(r4, 0));
        L7:
            return (T[]) super.getSpans(r2, r3, r4);
        }

        @Override // android.text.SpannableString, android.text.Spanned
        public int nextSpanTransition(int r2, int r3, Class r4) {
            if (this.ready == true) goto L6;
            return r3;
        L6:
            return super.nextSpanTransition(r2, r3, r4);
        }

        public void unlock() {
            this.ready = true;
        }
    }

    /* loaded from: classes5.dex */
    public static class LockedWithFallbackSpannableString extends LockedSpannableString {
        public SpannableStringBuilder fallback;

        public LockedWithFallbackSpannableString(CharSequence r1, SpannableStringBuilder r2) {
            super(r1);
            this.fallback = r2;
        }

        @Override // org.telegram.messenger.CodeHighlighting.LockedSpannableString, android.text.SpannableString, android.text.Spanned
        public int getSpanEnd(Object r2) {
            if (this.ready == true) goto L9;
            SpannableStringBuilder r0 = this.fallback;
            if (r0 == null) goto L9;
            return r0.getSpanEnd(r2);
        L9:
            return super.getSpanEnd(r2);
        }

        @Override // org.telegram.messenger.CodeHighlighting.LockedSpannableString, android.text.SpannableString, android.text.Spanned
        public int getSpanFlags(Object r2) {
            if (this.ready == true) goto L9;
            SpannableStringBuilder r0 = this.fallback;
            if (r0 == null) goto L9;
            return r0.getSpanFlags(r2);
        L9:
            return super.getSpanFlags(r2);
        }

        @Override // org.telegram.messenger.CodeHighlighting.LockedSpannableString, android.text.SpannableString, android.text.Spanned
        public int getSpanStart(Object r2) {
            if (this.ready == true) goto L9;
            SpannableStringBuilder r0 = this.fallback;
            if (r0 == null) goto L9;
            return r0.getSpanStart(r2);
        L9:
            return super.getSpanStart(r2);
        }

        @Override // org.telegram.messenger.CodeHighlighting.LockedSpannableString, android.text.SpannableString, android.text.Spanned
        public <T> T[] getSpans(int r2, int r3, Class<T> r4) {
            if (this.ready == true) goto L9;
            SpannableStringBuilder r0 = this.fallback;
            if (r0 == null) goto L9;
            return (T[]) r0.getSpans(r2, r3, r4);
        L9:
            return (T[]) super.getSpans(r2, r3, r4);
        }

        @Override // org.telegram.messenger.CodeHighlighting.LockedSpannableString, android.text.SpannableString, android.text.Spanned
        public int nextSpanTransition(int r2, int r3, Class r4) {
            if (this.ready == true) goto L9;
            SpannableStringBuilder r0 = this.fallback;
            if (r0 == null) goto L9;
            return r0.nextSpanTransition(r2, r3, r4);
        L9:
            return super.nextSpanTransition(r2, r3, r4);
        }
    }

    /* loaded from: classes5.dex */
    public static class Match {
        int index;
        int length;
        String string;

        private Match() {
        }

        public /* synthetic */ Match(CodeHighlightingIA r1) {
            this();
        }
    }

    /* loaded from: classes5.dex */
    public static class Node {
        public Node next;
        public Node prev;
        public StringToken value;

        private Node() {
        }

        public /* synthetic */ Node(CodeHighlightingIA r1) {
            this();
        }
    }

    /* loaded from: classes5.dex */
    public static class ParsedPattern {
        private CachedPattern cachedPattern;
        boolean caseInsensitive;
        boolean multiline;
        String pattern;

        private ParsedPattern() {
        }

        public /* synthetic */ ParsedPattern(CodeHighlightingIA r1) {
            this();
        }

        public int flags() {
            int r1 = 0;
            if (this.multiline == false) goto L5;
            int r0 = 8;
        L7:
            if (this.caseInsensitive == false) goto L10;
            r1 = 2;
        L10:
            return r0 | r1;
        L5:
            r0 = 0;
            goto L7
        }

        public CachedPattern getCachedPattern() {
            if (this.cachedPattern != null) goto L6;
            this.cachedPattern = new CachedPattern(this.pattern, flags());
        L6:
            return this.cachedPattern;
        }
    }

    /* loaded from: classes5.dex */
    public static class RematchOptions {
        TokenPattern cause;
        int reach;

        private RematchOptions() {
        }

        public /* synthetic */ RematchOptions(CodeHighlightingIA r1) {
            this();
        }
    }

    public static class Span extends CharacterStyle {
        public final String code;
        public final int currentType;
        public final float decrementSize;
        public final String lng;
        public final boolean smallerSize;
        public final TextStyleSpan.TextStyleRun style;

        public Span(boolean r1, int r2, TextStyleSpan.TextStyleRun r3, String r4, String r5) {
            this.smallerSize = r1;
            this.lng = r4;
            this.code = r5;
            if (r5 != null) goto L5;
            int r12 = 0;
        L6:
            this.decrementSize = CodeHighlighting.getTextSizeDecrement(r12);
            this.currentType = r2;
            this.style = r3;
            return;
        L5:
            r12 = r5.length();
            goto L6
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r3) {
            if (this.smallerSize == false) goto L5;
            r3.setTextSize(AndroidUtilities.dp(SharedConfig.fontSize - this.decrementSize));
        L5:
            int r0 = this.currentType;
            if (r0 != 2) goto L9;
            r3.setColor(-1);
        L12:
            TextStyleSpan.TextStyleRun r2 = this.style;
            if (r2 == null) goto L16;
            r2.applyStyle(r3);
            return;
        L16:
            r3.setTypeface(Typeface.MONOSPACE);
            r3.setUnderlineText(false);
            return;
        L9:
            if (r0 != 1) goto L11;
            r3.setColor(Theme.getColor(Theme.key_chat_messageTextOut));
            goto L12
        L11:
            r3.setColor(Theme.getColor(Theme.key_chat_messageTextIn));
            goto L12
        }
    }

    /* loaded from: classes5.dex */
    public static class StreamReader {
        private final InputStream is;

        public StreamReader(InputStream r1) {
            this.is = r1;
        }

        public String readString() {
            int r0 = this.is.read();
            if (r0 < 254) goto L5;
            r0 = (this.is.read() | (this.is.read() << 8)) | (this.is.read() << 16);
        L5:
            byte[] r1 = new byte[r0];
            int r2 = 0;
        L6:
            if (r2 >= r0) goto L9;
            r1[r2] = (byte) this.is.read();
            r2 = r2 + 1;
            goto L6
        L9:
            return new String(r1, StandardCharsets.US_ASCII);
        }

        public int readUint16() {
            int r0 = this.is.read() & 255;
            return ((this.is.read() & 255) << 8) | r0;
        }

        public int readUint8() {
            return this.is.read() & 255;
        }
    }

    /* loaded from: classes5.dex */
    public static class StringToken {
        final int group;
        final LinkedList inside;
        final int insideLength;
        final String string;
        final boolean token;

        public StringToken(int r2, String r3) {
            this.token = true;
            this.group = r2;
            this.string = r3;
            this.inside = null;
            this.insideLength = 0;
        }

        public StringToken(int r2, LinkedList r3, int r4) {
            this.token = true;
            this.group = r2;
            this.string = null;
            this.inside = r3;
            this.insideLength = r4;
        }

        public StringToken(String r3) {
            this.token = false;
            this.group = -1;
            this.string = r3;
            this.inside = null;
            this.insideLength = 0;
        }

        public int length() {
            String r0 = this.string;
            if (r0 == null) goto L7;
            return r0.length();
        L7:
            return this.insideLength;
        }
    }

    /* loaded from: classes5.dex */
    public static class TokenPattern {
        public boolean greedy;
        public int group;
        public String insideLanguage;
        public TokenPattern[] insideTokenPatterns;
        public boolean lookbehind;
        public final CachedPattern pattern;

        public TokenPattern(int r1, CachedPattern r2) {
            this.pattern = r2;
            this.group = r1;
        }

        public TokenPattern(int r1, CachedPattern r2, TokenPattern... r3) {
            this.group = r1;
            this.pattern = r2;
            this.insideTokenPatterns = r3;
        }

        public TokenPattern(CachedPattern r2, String r3) {
            this.group = -1;
            this.pattern = r2;
            this.insideLanguage = r3;
        }

        public TokenPattern(CachedPattern r2, TokenPattern... r3) {
            this.group = -1;
            this.pattern = r2;
            this.insideTokenPatterns = r3;
        }
    }

    public static /* synthetic */ void $r8$lambda$FUf8UFflQpy8dsJzr9b6qK27Hmk(Spannable r2) {
        ((LockedSpannableString) r2).unlock();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.emojiLoaded, new Object[0]);
    }

    /* renamed from: $r8$lambda$GnQc0UFV-OreAy2pkYwEC22lbb4, reason: not valid java name */
    public static /* synthetic */ void m3941$r8$lambda$GnQc0UFVOreAy2pkYwEC22lbb4(String r7, String r8, SpannableString r9, Utilities.Callback r10) {
        if (compiledPatterns != null) goto L5;
        parse();
    L5:
        ArrayList r6 = new ArrayList();
        HashMap<String, TokenPattern[]> r0 = compiledPatterns;     // Catch: Exception -> L16
        if (r0 != null) goto L9;
        TokenPattern[] r82 = null;
    L10:
        StringToken[] r4 = tokenize(r7, r82, 0).toArray();     // Catch: Exception -> L16
        SpannableString r1 = r9;
        colorize(r1, 0, r9.length(), r4, -1, r6);     // Catch: Exception -> L14
    L19:
        AndroidUtilities.runOnUIThread(new CodeHighlighting$$ExternalSyntheticLambda0(r6, r1, r10));
        return;
    L14:
        e = e;
    L18:
        FileLog.e(e);
        goto L19
    L9:
        r82 = r0.get(r8);     // Catch: Exception -> L16
    L16:
        e = e;
        r1 = r9;
        goto L18
    }

    /* renamed from: $r8$lambda$jQO9jOIHZCkw36qQVzRg3-QK6o8, reason: not valid java name */
    public static /* synthetic */ void m3942$r8$lambda$jQO9jOIHZCkw36qQVzRg3QK6o8(Spannable r11, int r12, int r13, String r14) {
        if (compiledPatterns != null) goto L5;
        parse();
    L5:
        long r1 = System.currentTimeMillis();
        StringToken[][] r3 = new StringToken[1][];
        int r4 = 0;
        String r0 = r11.subSequence(r12, r13).toString();     // Catch: Exception -> L12
        HashMap<String, TokenPattern[]> r5 = compiledPatterns;     // Catch: Exception -> L12
        if (r5 != null) goto L9;
        TokenPattern[] r142 = null;
    L10:
        r3[0] = tokenize(r0, r142, 0).toArray();     // Catch: Exception -> L12
    L14:
        FileLog.d("[CodeHighlighter] tokenize took " + (System.currentTimeMillis() - r1) + "ms");
        long r15 = System.currentTimeMillis();
        ArrayList r10 = new ArrayList();
        colorize(r11, r12, r13, r3[0], -1, r10);
        FileLog.d("[CodeHighlighter] colorize took " + (System.currentTimeMillis() - r15) + "ms");
        if (r10.isEmpty() == false) goto L17;
        return;
    L17:
        if ((r11 instanceof LockedSpannableString) == false) goto L23;
        long r112 = System.currentTimeMillis();
    L20:
        if (r4 >= r10.size()) goto L22;
        CachedToSpan r132 = (CachedToSpan) r10.get(r4);
        r11.setSpan(new ColorSpan(r132.group), r132.start, r132.end, 33);
        r4 = r4 + 1;
        goto L20
    L22:
        FileLog.d("[CodeHighlighter] applying " + r10.size() + " colorize spans took " + (System.currentTimeMillis() - r112) + "ms in another thread");
        AndroidUtilities.runOnUIThread(new CodeHighlighting$$ExternalSyntheticLambda4(r11));
        return;
    L23:
        AndroidUtilities.runOnUIThread(new CodeHighlighting$$ExternalSyntheticLambda5(r10, r11));
        return;
    L9:
        r142 = r5.get(r14);     // Catch: Exception -> L12
    L12:
        e = move-exception;
        FileLog.e(e);
        goto L14
    }

    /* renamed from: $r8$lambda$plxG-IoXIX1WFe9rdhmpCX8CFw4, reason: not valid java name */
    public static /* synthetic */ void m3943$r8$lambda$plxGIoXIX1WFe9rdhmpCX8CFw4(ArrayList r8, Spannable r9) {
        long r0 = System.currentTimeMillis();
        int r3 = 0;
    L4:
        if (r3 >= r8.size()) goto L6;
        CachedToSpan r4 = (CachedToSpan) r8.get(r3);
        r9.setSpan(new ColorSpan(r4.group), r4.start, r4.end, 33);
        r3 = r3 + 1;
        goto L4
    L6:
        FileLog.d("[CodeHighlighter] applying " + r8.size() + " colorize spans took " + (System.currentTimeMillis() - r0) + "ms");
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.emojiLoaded, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$uJk7sehDZe0ZSZDsLPpJs_l6bMw(ArrayList r5, SpannableString r6, Utilities.Callback r7) {
        int r0 = 0;
    L4:
        if (r0 >= r5.size()) goto L6;
        CachedToSpan r1 = (CachedToSpan) r5.get(r0);
        r6.setSpan(new ColorSpan(r1.group), r1.start, r1.end, 33);
        r0 = r0 + 1;
        goto L4
    L6:
        r7.run(r6);
    }

    public static /* synthetic */ void $r8$lambda$yc7DAs30Il1LjoB2owuVZpqSbS0() {
        if (compiledPatterns != null) goto L6;
        parse();
        return;
    }

    static {
        processedHighlighting = new ConcurrentHashMap();
    }

    public CodeHighlighting() {
    }

    private static void colorize(Spannable r7, int r8, int r9, StringToken[] r10, int r11, ArrayList<CachedToSpan> r12) {
        if (r10 == null) goto L25;
        int r0 = 0;
        int r2 = r8;
    L6:
        if (r0 >= r10.length) goto L30;
        if (r2 >= r9) goto L31;
        StringToken r82 = r10[r0];
        if (r82 != null) goto L12;
    L10:
        Spannable r1 = r7;
        ArrayList<CachedToSpan> r6 = r12;
    L24:
        r0 = r0 + 1;
        r7 = r1;
        r12 = r6;
        goto L6
    L12:
        if (r82.string == null) goto L21;
        int r13 = r82.group;
        if (r11 == (-1)) goto L16;
        r13 = r11;
    L16:
        if (r13 != (-1)) goto L18;
        r2 = r2 + r82.length();
        goto L10
    L18:
        r12.add(new CachedToSpan(r13, r2, r82.length() + r2));
    L19:
        r1 = r7;
        r6 = r12;
    L23:
        r2 = r2 + r82.length();
        goto L24
    L21:
        if (r82.inside == null) goto L19;
        r1 = r7;
        r6 = r12;
        colorize(r1, r2, r2 + r82.length(), r82.inside.toArray(), r82.group, r6);
        goto L23
    L31:
        return;
    L30:
        return;
    }

    private static TokenPattern[] flatRest(TokenPattern[] r5) {
        ArrayList r0 = null;
        if (r5 != null) goto L5;
        return null;
    L5:
        int r2 = 0;
    L7:
        if (r2 >= r5.length) goto L22;
        CachedPattern r3 = r5[r2].pattern;
        if (r3 == null) goto L21;
        if ("REST".equals(CachedPattern.m3944$$Nest$fgetpatternSource(r3)) == false) goto L21;
        if (r0 != null) goto L14;
        r0 = new ArrayList();
        Collections.addAll(r0, r5);
    L14:
        r0.remove(r5[r2]);
        if (TextUtils.isEmpty(r5[r2].insideLanguage) == true) goto L21;
        HashMap<String, TokenPattern[]> r32 = compiledPatterns;
        if (r32 == null) goto L21;
        TokenPattern[] r33 = r32.get(r5[r2].insideLanguage);
        if (r33 == null) goto L21;
        Collections.addAll(r0, r33);
    L21:
        r2 = r2 + 1;
        goto L7
    L22:
        if (r0 != null) goto L24;
        return r5;
    L24:
        return (TokenPattern[]) r0.toArray(new TokenPattern[0]);
    }

    public static SpannableString getHighlighted(CharSequence r11, String r12) {
        if (TextUtils.isEmpty(r12) == true) goto L5;
        String r0 = r12 + "`" + r11;
        ConcurrentHashMap<String, Highlighting> r1 = processedHighlighting;
        Highlighting r2 = r1.get(r0);
        if (r2 != null) goto L16;
        r2 = new Highlighting(null);
        r2.text = r11;
        r2.language = r12;
        LockedSpannableString r4 = new LockedSpannableString(r11);
        r2.result = r4;
        highlight(r4, 0, r4.length(), r12, 0, null, true);
        Iterator<String> r112 = r1.keySet().iterator();
    L10:
        if (r112.hasNext() == false) goto L14;
        if (processedHighlighting.size() <= 8) goto L14;
        r112.next();
        r112.remove();
    L14:
        processedHighlighting.put(r0, r2);
    L16:
        return r2.result;
    L5:
        return new SpannableString(r11);
    }

    public static Set<String> getLanguages() {
        HashSet<String> r0 = languages;
        if (r0 != null) goto L6;
        return null;
    L6:
        return r0;
    }

    public static int getTextSizeDecrement(int r1) {
        if (r1 <= 120) goto L7;
        return 5;
    L7:
        if (r1 <= 50) goto L10;
        return 3;
    L10:
        return 2;
    }

    public static void highlight(Spannable r0, int r1, int r2, String r3, int r4, TextStyleSpan.TextStyleRun r5, boolean r6) {
        if (r0 != null) goto L4;
        return;
    L4:
        Utilities.searchQueue.postRunnable(new CodeHighlighting$$ExternalSyntheticLambda1(r0, r1, r2, r3));
    }

    public static void highlightEditable(CharSequence r3, String r4, Utilities.Callback<SpannableString> r5) {
        if (r5 != null) goto L5;
        return;
    L5:
        if (r3 != null) goto L7;
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        SpannableString r0 = new SpannableString(r3);
        if (TextUtils.isEmpty(r4) == false) goto L10;
    L14:
        r5.run(r0);
        return;
    L10:
        if (r0.length() == 0) goto L14;
        String r32 = r0.toString();
        Utilities.searchQueue.postRunnable(new CodeHighlighting$$ExternalSyntheticLambda2(r32, r4, r0, r5));
    }

    private static void matchGrammar(String r17, LinkedList r18, TokenPattern[] r19, Node r20, int r21, RematchOptions r22, TokenPattern r23, int r24) {
        String r0 = r17;
        LinkedList r1 = r18;
        TokenPattern[] r2 = r19;
        if (r2 != null) goto L5;
        return;
    L5:
        if (r24 > 20) goto L107;
        int r10 = r2.length;
        int r12 = 0;
    L8:
        if (r12 >= r10) goto L108;
        TokenPattern r13 = r2[r12];
        if (r13 == r23) goto L109;
        if (r22 != null) goto L13;
    L15:
        Node r3 = r20.next;
        int r4 = r21;
    L17:
        if (r3 == r1.tail) goto L86;
        if (r22 == null) goto L23;
        if (r4 < r22.reach) goto L23;
        return;
    L23:
        if (r1.length > r0.length()) goto L24;
        StringToken r5 = r3.value;
        String r7 = r5.string;
        if (r7 == null) goto L85;
        if (r5.token == true) goto L85;
        if (r13.greedy == false) goto L55;
        Match r52 = matchPattern(r13, r4, r0);
        if (r52 == null) goto L86;
        if (r52.index >= r0.length()) goto L86;
        int r72 = r52.index;
        int r15 = r52.length + r72;
        int r11 = r3.value.length();
    L39:
        r4 = r4 + r11;
        if (r72 < r4) goto L42;
        r3 = r3.next;
        r11 = r3.value.length();
        goto L39
    L42:
        r4 = r4 - r3.value.length();
        StringToken r73 = r3.value;
        if (r73.string == null) goto L85;
        if (r73.token == true) goto L85;
        Node r74 = r3;
        int r112 = r4;
        int r16 = 1;
    L49:
        if (r74 == r1.tail) goto L54;
        if (r112 < r15) goto L53;
        if (r74.value.token == true) goto L54;
    L53:
        r16 = r16 + 1;
        r112 = r112 + r74.value.length();
        r74 = r74.next;
    L54:
        r7 = r0.substring(r4, r112);
        r52.index -= r4;
        int r25 = r16 - 1;
        int r113 = 0;
    L59:
        int r152 = r52.index;
        String r02 = r7.substring(r113, r152);
        String r114 = r7.substring(r152 + r52.length);
        int r75 = r7.length() + r4;
        if (r22 != null) goto L62;
    L64:
        Node r32 = r3.prev;
        if (r02.length() <= 0) goto L67;
        r32 = r1.addAfter(r32, new StringToken(r02));
        r4 = r4 + r02.length();
    L67:
        r1.removeRange(r32, r25);
        TokenPattern[] r03 = r13.insideTokenPatterns;
        if (r03 == null) goto L70;
        int r162 = r4;
        StringToken r153 = new StringToken(r13.group, tokenize(r52.string, r03, r13, r24 + 1), r52.length);
    L74:
        Node r9 = r1.addAfter(r32, r153);
        if (r114.length() <= 0) goto L78;
        r1.addAfter(r9, new StringToken(r114));
    L78:
        if (r25 <= 1) goto L84;
        RematchOptions r53 = new RematchOptions(null);
        r53.cause = r13;
        r53.reach = r75;
        matchGrammar(r17, r1, r19, r9.prev, r162, r53, r23, r24 + 1);
        if (r22 == null) goto L84;
        int r04 = r53.reach;
        if (r04 <= r22.reach) goto L84;
        r22.reach = r04;
    L84:
        r3 = r9;
        r4 = r162;
        goto L85
    L70:
        r162 = r4;
        String r05 = r13.insideLanguage;
        int r42 = r13.group;
        if (r05 == null) goto L73;
        r153 = new StringToken(r42, tokenize(r52.string, compiledPatterns.get(r05), r13, r24 + 1), r52.length);
        goto L74
    L73:
        r153 = new StringToken(r42, r52.string);
        goto L74
    L62:
        if (r75 <= r22.reach) goto L64;
        r22.reach = r75;
        goto L64
    L55:
        r113 = 0;
        r52 = matchPattern(r13, 0, r7);
        if (r52 == null) goto L85;
        r25 = 1;
    L85:
        r4 = r4 + r3.value.length();
        r3 = r3.next;
        r0 = r17;
        r1 = r18;
        goto L17
    L24:
        FileLog.e("[CodeHighlighter] Something went terribly wrong, ABORT, ABORT!");
        return;
    L86:
        r12 = r12 + 1;
        r0 = r17;
        r1 = r18;
        r2 = r19;
        goto L8
    L13:
        if (r22.cause != r13) goto L15;
        return;
    L109:
        return;
    L108:
        return;
    }

    private static Match matchPattern(TokenPattern r4, int r5, String r6) {
        Matcher r1 = r4.pattern.getPattern().matcher(r6);     // Catch: Exception -> L11
        r1.region(r5, r6.length());     // Catch: Exception -> L11
        if (r1.find() == true) goto L6;
        return null;
    L6:
        Match r52 = new Match(null);     // Catch: Exception -> L11
        r52.index = r1.start();     // Catch: Exception -> L11
        if (r4.lookbehind == true) goto L9;
    L13:
        int r42 = r1.end();     // Catch: Exception -> L11
        int r12 = r52.index;     // Catch: Exception -> L11
        int r43 = r42 - r12;     // Catch: Exception -> L11
        r52.length = r43;     // Catch: Exception -> L11
        r52.string = r6.substring(r12, r43 + r12);     // Catch: Exception -> L11
        return r52;
    L9:
        if (r1.groupCount() < 1) goto L13;
        r52.index += r1.end(1) - r1.start(1);
    L11:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static String normalizeLanguage(String r4) {
        if (TextUtils.isEmpty(r4) == false) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        String r42 = r4.trim();
        if (r42.isEmpty() == false) goto L9;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        if (compiledPatterns != null) goto L11;
        parse();
    L11:
        HashMap<String, TokenPattern[]> r0 = compiledPatterns;
        if (r0 == null) goto L27;
        if (r0.containsKey(r42) == false) goto L16;
        return r42;
    L16:
        String r02 = r42.toLowerCase(Locale.ROOT);
        if (compiledPatterns.containsKey(r02) == true) goto L25;
        Iterator<String> r1 = compiledPatterns.keySet().iterator();
    L21:
        if (r1.hasNext() == false) goto L25;
        String r2 = r1.next();
        if (r2.equalsIgnoreCase(r42) == false) goto L21;
        return r2;
    L25:
        return r02;
    L27:
        return r42.toLowerCase(Locale.ROOT);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v7 */
    private static void parse() {
        GZIPInputStream r1 = null;
        long r2 = System.currentTimeMillis();     // Catch: Throwable -> L64 Exception -> L66
        InputStream r4 = ApplicationLoader.applicationContext.getAssets().open("codelng.gzip");     // Catch: Throwable -> L64 Exception -> L66
        GZIPInputStream r5 = new GZIPInputStream(r4, 65536);     // Catch: Throwable -> L59 Exception -> L62
    L98:
        BufferedInputStream r6 = new BufferedInputStream(r5, 65536);     // Catch: Throwable -> L55 Exception -> L57
        StreamReader r0 = new StreamReader(r6);     // Catch: Throwable -> L11 Exception -> L13
        HashMap r7 = new HashMap();     // Catch: Throwable -> L11 Exception -> L13
        int r8 = r0.readUint8();     // Catch: Throwable -> L11 Exception -> L13
        int r10 = 0;
    L7:
        if (r10 >= r8) goto L16;
        int r11 = r0.readUint8();     // Catch: Throwable -> L11 Exception -> L13
        int r12 = r0.readUint8();     // Catch: Throwable -> L11 Exception -> L13
        String[] r13 = new String[r12];     // Catch: Throwable -> L11 Exception -> L13
        int r14 = 0;
    L9:
        if (r14 >= r12) goto L15;
        r13[r14] = r0.readString();     // Catch: Throwable -> L11 Exception -> L13
        r14 = r14 + 1;     // Catch: Throwable -> L11 Exception -> L13
        goto L9
    L15:
        r7.put(Integer.valueOf(r11), r13);     // Catch: Throwable -> L11 Exception -> L13
        r10 = r10 + 1;     // Catch: Throwable -> L11 Exception -> L13
        goto L7
    L16:
        int r102 = r0.readUint16();     // Catch: Throwable -> L11 Exception -> L13
        ParsedPattern[] r112 = new ParsedPattern[r102];     // Catch: Throwable -> L11 Exception -> L13
        int r122 = 0;
    L17:
        if (r122 >= r102) goto L28;
        r112[r122] = new ParsedPattern(null);     // Catch: Throwable -> L11 Exception -> L13
        int r132 = r0.readUint8();     // Catch: Throwable -> L11 Exception -> L13
        ParsedPattern r142 = r112[r122];     // Catch: Throwable -> L11 Exception -> L13
        if ((r132 & 1) == 0) goto L21;
        boolean r15 = true;
    L22:
        r142.multiline = r15;     // Catch: Throwable -> L11 Exception -> L13
        if ((r132 & 2) == 0) goto L25;
        boolean r133 = true;
    L26:
        r142.caseInsensitive = r133;     // Catch: Throwable -> L11 Exception -> L13
        r142.pattern = r0.readString();     // Catch: Throwable -> L11 Exception -> L13
        r122 = r122 + 1;     // Catch: Throwable -> L11 Exception -> L13
        goto L17
    L25:
        r133 = false;
        goto L26
    L21:
        r15 = false;
        goto L22
    L28:
        if (compiledPatterns != null) goto L31;
        compiledPatterns = new HashMap();     // Catch: Throwable -> L11 Exception -> L13
    L31:
        if (languages != null) goto L33;
        languages = new HashSet();     // Catch: Throwable -> L11 Exception -> L13
    L33:
        int r16 = 0;
    L34:
        if (r16 >= r8) goto L48;
        int r123 = r0.readUint8();     // Catch: Throwable -> L11 Exception -> L13
        TokenPattern[] r134 = readTokens(r0, r112, r7);     // Catch: Throwable -> L11 Exception -> L13
        String[] r124 = (String[]) r7.get(Integer.valueOf(r123));     // Catch: Throwable -> L11 Exception -> L13
        int r143 = r124.length;     // Catch: Throwable -> L11 Exception -> L13
        int r152 = 0;
    L36:
        if (r152 >= r143) goto L38;
        compiledPatterns.put(r124[r152], r134);     // Catch: Throwable -> L11 Exception -> L13
        r152 = r152 + 1;     // Catch: Throwable -> L11 Exception -> L13
        r0 = r0;
        goto L36
    L38:
        StreamReader r17 = r0;
        if (r124.length <= 0) goto L47;
        if ("plain".equals(r124[0]) == true) goto L47;
        if (r124[0].endsWith("like") == true) goto L47;
        if (r124[0].startsWith("markup") == true) goto L47;
        languages.add(r124[0]);     // Catch: Throwable -> L11 Exception -> L13
    L47:
        r16 = r16 + 1;     // Catch: Throwable -> L11 Exception -> L13
        r0 = r17;
        goto L34
    L48:
        FileLog.d("[CodeHighlighter] Successfully read " + r8 + " languages, " + r102 + " patterns in " + (System.currentTimeMillis() - r2) + "ms from codelng.gzip");     // Catch: Throwable -> L11 Exception -> L13
        r5.close();     // Catch: Exception -> L53
        r6.close();     // Catch: Exception -> L53
        if (r4 == null) goto L113;
        r4.close();     // Catch: Exception -> L53
        return;
    L113:
        return;
    L13:
        e = e;
    L14:
        r1 = r5;
        r6 = r6;
    L90:
        FileLog.e(e);     // Catch: Throwable -> L76
        if (r1 == null) goto L71;
        r1.close();     // Catch: Exception -> L53
    L71:
        if (r6 == 0) goto L73;
        r6.close();     // Catch: Exception -> L53
    L73:
        if (r4 == null) goto L115;
        r4.close();     // Catch: Exception -> L53
        return;
    L115:
        return;
    L76:
        th = th;
        r5 = r1;
    L12:
        Throwable r18 = th;
        if (r5 != null) goto L92;
    L82:
        if (r6 == 0) goto L84;
        r6.close();     // Catch: Exception -> L80
    L84:
        if (r4 == null) goto L116;
        r4.close();     // Catch: Exception -> L80
        throw r18;
    L80:
        e = move-exception;
        FileLog.e(e);
        throw r18;
    L116:
        throw r18;
    L92:
        r5.close();     // Catch: Exception -> L80
    L11:
        th = th;
        goto L12
    L57:
        e = e;
        r6 = 0;
    L55:
        th = th;
        r6 = 0;
    L62:
        e = e;
        r6 = 0;
    L59:
        th = th;
        r5 = null;
    L61:
        r6 = r5;
        goto L12
    L53:
        e = move-exception;
        FileLog.e(e);
        return;
    L66:
        e = e;
        r4 = null;
        r6 = 0;
    L64:
        th = th;
        r4 = null;
        r5 = null;
        goto L61
    }

    public static void prepare() {
        if (compiledPatterns == null) goto L5;
        return;
    L5:
        Utilities.searchQueue.postRunnable(new CodeHighlighting$$ExternalSyntheticLambda3());
    }

    private static TokenPattern[] readTokens(StreamReader r11, ParsedPattern[] r12, HashMap<Integer, String[]> r13) {
        int r0 = r11.readUint8();
        TokenPattern[] r1 = new TokenPattern[r0];
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L27;
        int r4 = r11.readUint8();
        int r5 = r4 & 3;
        int r6 = (r4 >> 2) & 7;
        if ((r4 & 32) == 0) goto L7;
        boolean r7 = true;
    L9:
        if ((r4 & 64) == 0) goto L11;
        boolean r42 = true;
    L12:
        int r9 = r11.readUint16();
        if (r5 != 0) goto L15;
        r1[r3] = new TokenPattern(r6, r12[r9].getCachedPattern());
    L22:
        if (r7 == false) goto L24;
        r1[r3].greedy = true;
    L24:
        if (r42 == false) goto L26;
        r1[r3].lookbehind = true;
    L26:
        r3 = r3 + 1;
        goto L3
    L15:
        if (r5 != 1) goto L20;
        if (r6 != 0) goto L18;
        r1[r3] = new TokenPattern(r12[r9].getCachedPattern(), readTokens(r11, r12, r13));
        goto L22
    L18:
        r1[r3] = new TokenPattern(r6, r12[r9].getCachedPattern(), readTokens(r11, r12, r13));
        goto L22
    L20:
        if (r5 != 2) goto L22;
        r1[r3] = new TokenPattern(r12[r9].getCachedPattern(), r13.get(Integer.valueOf(r11.readUint8()))[0]);
        goto L22
    L11:
        r42 = false;
        goto L12
    L7:
        r7 = false;
        goto L9
    L27:
        return r1;
    }

    private static LinkedList tokenize(String r1, TokenPattern[] r2, int r3) {
        return tokenize(r1, r2, null, r3);
    }

    private static LinkedList tokenize(String r8, TokenPattern[] r9, TokenPattern r10, int r11) {
        LinkedList r1 = new LinkedList();
        r1.addAfter(r1.head, new StringToken(r8));
        matchGrammar(r8, r1, flatRest(r9), r1.head, 0, null, r10, r11);
        return r1;
    }
}
