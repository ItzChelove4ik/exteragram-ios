package com.caverock.androidsvg;

import android.util.Log;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.text.Typography;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes4.dex */
class CSSParser {
    private MediaType deviceMediaType;
    private boolean inMediaRule;
    private Source source;

    /* renamed from: com.caverock.androidsvg.CSSParser$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$caverock$androidsvg$CSSParser$AttribOp = null;
        static final /* synthetic */ int[] $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents = null;

        static {
            int[] r0 = new int[PseudoClassIdents.values().length];
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents = r0;
            r0[PseudoClassIdents.first_child.ordinal()] = 1;     // Catch: NoSuchFieldError -> L34
        L77:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.last_child.ordinal()] = 2;     // Catch: NoSuchFieldError -> L35
        L91:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.only_child.ordinal()] = 3;     // Catch: NoSuchFieldError -> L36
        L99:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.first_of_type.ordinal()] = 4;     // Catch: NoSuchFieldError -> L37
        L103:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.last_of_type.ordinal()] = 5;     // Catch: NoSuchFieldError -> L38
        L65:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.only_of_type.ordinal()] = 6;     // Catch: NoSuchFieldError -> L39
        L69:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.root.ordinal()] = 7;     // Catch: NoSuchFieldError -> L40
        L85:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.empty.ordinal()] = 8;     // Catch: NoSuchFieldError -> L41
        L87:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.nth_child.ordinal()] = 9;     // Catch: NoSuchFieldError -> L42
        L105:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.nth_last_child.ordinal()] = 10;     // Catch: NoSuchFieldError -> L43
        L109:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.nth_of_type.ordinal()] = 11;     // Catch: NoSuchFieldError -> L44
        L71:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.nth_last_of_type.ordinal()] = 12;     // Catch: NoSuchFieldError -> L45
        L75:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.not.ordinal()] = 13;     // Catch: NoSuchFieldError -> L46
        L89:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.target.ordinal()] = 14;     // Catch: NoSuchFieldError -> L47
        L93:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.lang.ordinal()] = 15;     // Catch: NoSuchFieldError -> L48
        L113:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.link.ordinal()] = 16;     // Catch: NoSuchFieldError -> L49
        L61:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.visited.ordinal()] = 17;     // Catch: NoSuchFieldError -> L50
        L79:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.hover.ordinal()] = 18;     // Catch: NoSuchFieldError -> L51
        L81:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.active.ordinal()] = 19;     // Catch: NoSuchFieldError -> L52
        L95:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.focus.ordinal()] = 20;     // Catch: NoSuchFieldError -> L53
        L101:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.enabled.ordinal()] = 21;     // Catch: NoSuchFieldError -> L54
        L63:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.disabled.ordinal()] = 22;     // Catch: NoSuchFieldError -> L55
        L67:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.checked.ordinal()] = 23;     // Catch: NoSuchFieldError -> L56
        L83:
            $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[PseudoClassIdents.indeterminate.ordinal()] = 24;     // Catch: NoSuchFieldError -> L57
        L29:
            int[] r3 = new int[AttribOp.values().length];
            $SwitchMap$com$caverock$androidsvg$CSSParser$AttribOp = r3;
            r3[AttribOp.EQUALS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L58
        L107:
            $SwitchMap$com$caverock$androidsvg$CSSParser$AttribOp[AttribOp.INCLUDES.ordinal()] = 2;     // Catch: NoSuchFieldError -> L59
        L111:
            $SwitchMap$com$caverock$androidsvg$CSSParser$AttribOp[AttribOp.DASHMATCH.ordinal()] = 3;     // Catch: NoSuchFieldError -> L60
            return;
        }
    }

    public static class Attrib {
        public final String name;
        final AttribOp operation;
        public final String value;

        public Attrib(String r1, AttribOp r2, String r3) {
            this.name = r1;
            this.operation = r2;
            this.value = r3;
        }
    }

    public enum AttribOp extends Enum<AttribOp> {
        private static final /* synthetic */ AttribOp[] $VALUES = null;
        public static final AttribOp DASHMATCH = null;
        public static final AttribOp EQUALS = null;
        public static final AttribOp EXISTS = null;
        public static final AttribOp INCLUDES = null;

        static {
            AttribOp r0 = new AttribOp("EXISTS", 0);
            EXISTS = r0;
            AttribOp r1 = new AttribOp("EQUALS", 1);
            EQUALS = r1;
            AttribOp r2 = new AttribOp("INCLUDES", 2);
            INCLUDES = r2;
            AttribOp r3 = new AttribOp("DASHMATCH", 3);
            DASHMATCH = r3;
            $VALUES = new AttribOp[]{r0, r1, r2, r3};
        }

        AttribOp(String r1, int r2) {
        }

        public static AttribOp valueOf(String r1) {
            return (AttribOp) Enum.valueOf(AttribOp.class, r1);
        }

        public static AttribOp[] values() {
            return (AttribOp[]) $VALUES.clone();
        }
    }

    public static class CSSTextScanner extends SVGParser.TextScanner {

        public static class AnPlusB {

            /* renamed from: a, reason: collision with root package name */
            public int f31a;

            /* renamed from: b, reason: collision with root package name */
            public int f32b;

            public AnPlusB(int r1, int r2) {
                this.f31a = r1;
                this.f32b = r2;
            }
        }

        public CSSTextScanner(String r3) {
            super(r3.replaceAll("(?s)/\\*.*?\\*/", _UrlKt.FRAGMENT_ENCODE_SET));
        }

        public static /* synthetic */ List access$400(CSSTextScanner r0) {
            return r0.nextSelectorGroup();
        }

        private int hexChar(int r2) {
            if (r2 < 48) goto L9;
            if (r2 > 57) goto L9;
            return r2 - 48;
        L9:
            if (r2 < 65) goto L15;
            if (r2 > 70) goto L15;
            return r2 - 55;
        L15:
            if (r2 >= 97) goto L17;
            return -1;
        L17:
            if (r2 <= 102) goto L19;
            return -1;
        L19:
            return r2 - 87;
        }

        private AnPlusB nextAnPlusB() {
            if (empty() == false) goto L5;
            return null;
        L5:
            int r0 = this.position;
            if (consume('(') == true) goto L8;
            return null;
        L8:
            skipWhitespace();
            int r4 = 1;
            if (consume("odd") == false) goto L11;
            AnPlusB r2 = new AnPlusB(2, 1);
        L53:
            skipWhitespace();
            if (consume(')') == false) goto L56;
            return r2;
        L56:
            this.position = r0;
            return null;
        L11:
            int r5 = 0;
            if (consume("even") == false) goto L15;
            r2 = new AnPlusB(2, 0);
            goto L53
        L15:
            if (consume(SignatureVisitor.EXTENDS) == false) goto L18;
        L20:
            int r3 = 1;
        L21:
            IntegerParser r8 = IntegerParser.parseInt(this.input, this.position, this.inputLength, false);
            if (r8 == null) goto L25;
            this.position = r8.getEndPos();
        L25:
            if (consume('n') == false) goto L27;
        L30:
            if (r8 != null) goto L33;
            r8 = new IntegerParser(1, this.position);
        L33:
            skipWhitespace();
            boolean r22 = consume(SignatureVisitor.EXTENDS);
            if (r22 == true) goto L38;
            r22 = consume(SignatureVisitor.SUPER);
            if (r22 == false) goto L38;
            r4 = -1;
        L38:
            if (r22 == false) goto L44;
            skipWhitespace();
            IntegerParser r23 = IntegerParser.parseInt(this.input, this.position, this.inputLength, false);
            if (r23 == null) goto L42;
            this.position = r23.getEndPos();
            int r12 = r4;
            r4 = r3;
            r3 = r12;
        L46:
            if (r8 != null) goto L48;
            int r42 = 0;
        L49:
            if (r23 == null) goto L52;
            r5 = r3 * r23.value();
        L52:
            r2 = new AnPlusB(r42, r5);
            goto L53
        L48:
            r42 = r4 * r8.value();
            goto L49
        L42:
            this.position = r0;
            return null;
        L44:
            int r24 = r4;
            r4 = r3;
            r3 = r24;
            r23 = null;
            goto L46
        L27:
            if (consume('N') == true) goto L30;
            r23 = r8;
            r8 = null;
            goto L46
        L18:
            if (consume(SignatureVisitor.SUPER) == false) goto L20;
            r3 = -1;
            goto L21
        }

        private String nextAttribValue() {
            if (empty() == false) goto L6;
            return null;
        L6:
            String r0 = nextQuotedString();
            if (r0 == null) goto L10;
            return r0;
        L10:
            return nextIdentifier();
        }

        private List<String> nextIdentListParam() {
            if (empty() == false) goto L5;
            return null;
        L5:
            int r0 = this.position;
            if (consume('(') == true) goto L8;
            return null;
        L8:
            skipWhitespace();
            ArrayList r2 = null;
        L9:
            String r3 = nextIdentifier();
            if (r3 == null) goto L11;
            if (r2 != null) goto L15;
            r2 = new ArrayList();
        L15:
            r2.add(r3);
            skipWhitespace();
            if (skipCommaWhitespace() == true) goto L9;
            if (consume(')') == false) goto L20;
            return r2;
        L20:
            this.position = r0;
            return null;
        L11:
            this.position = r0;
            return null;
        }

        private List<Selector> nextPseudoNotParam() {
            if (empty() == false) goto L5;
            return null;
        L5:
            int r0 = this.position;
            if (consume('(') == true) goto L8;
            return null;
        L8:
            skipWhitespace();
            List<Selector> r2 = nextSelectorGroup();
            if (r2 != null) goto L13;
            this.position = r0;
            return null;
        L13:
            if (consume(')') == true) goto L16;
            this.position = r0;
            return null;
        L16:
            Iterator<Selector> r5 = r2.iterator();
        L18:
            if (r5.hasNext() == false) goto L34;
            List<SimpleSelector> r02 = r5.next().simpleSelectors;
            if (r02 == null) goto L34;
            Iterator<SimpleSelector> r03 = r02.iterator();
        L24:
            if (r03.hasNext() == false) goto L18;
            List<PseudoClass> r3 = r03.next().pseudos;
            if (r3 == null) goto L18;
            Iterator<PseudoClass> r32 = r3.iterator();
        L30:
            if (r32.hasNext() == false) goto L24;
            if ((r32.next() instanceof PseudoClassNot) == false) goto L30;
            return null;
        L34:
            return r2;
        }

        private List<Selector> nextSelectorGroup() {
            if (empty() == false) goto L5;
            return null;
        L5:
            ArrayList r0 = new ArrayList(1);
            Selector r2 = new Selector(null);
        L7:
            if (empty() == true) goto L15;
            if (nextSimpleSelector(r2) == false) goto L15;
            if (skipCommaWhitespace() == false) goto L7;
            r0.add(r2);
            r2 = new Selector(null);
        L15:
            if (r2.isEmpty() == true) goto L17;
            r0.add(r2);
        L17:
            return r0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void parsePseudoClass(Selector r14, SimpleSelector r15) {
            String r0 = nextIdentifier();
            if (r0 == null) goto L48;
            PseudoClassIdents r1 = PseudoClassIdents.fromString(r0);
            switch(AnonymousClass1.$SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents[r1.ordinal()]) {
                case 1: goto L44;
                case 2: goto L43;
                case 3: goto L42;
                case 4: goto L41;
                case 5: goto L40;
                case 6: goto L39;
                case 7: goto L38;
                case 8: goto L37;
                case 9: goto L18;
                case 10: goto L18;
                case 11: goto L18;
                case 12: goto L18;
                case 13: goto L11;
                case 14: goto L10;
                case 15: goto L9;
                case 16: goto L8;
                case 17: goto L8;
                case 18: goto L8;
                case 19: goto L8;
                case 20: goto L8;
                case 21: goto L8;
                case 22: goto L8;
                case 23: goto L8;
                case 24: goto L8;
                default: goto L7;
            };
        L8:
            PseudoClass r13 = new PseudoClassNotSupported(r0);
            r14.addedAttributeOrPseudo();
        L45:
            r15.addPseudo(r13);
            return;
        L9:
            nextIdentListParam();
            r13 = new PseudoClassNotSupported(r0);
            r14.addedAttributeOrPseudo();
            goto L45
        L10:
            r13 = new PseudoClassTarget(null);
            r14.addedAttributeOrPseudo();
            goto L45
        L11:
            List<Selector> r132 = nextPseudoNotParam();
            if (r132 == null) goto L16;
            PseudoClassNot r02 = new PseudoClassNot(r132);
            r14.specificity = r02.getSpecificity();
            PseudoClassAnPlusB r03 = r02;
        L14:
            r13 = r03;
            goto L45
        L16:
            throw new CSSParseException("Invalid or missing parameter section for pseudo class: ".concat(r0));
        L37:
            r13 = new PseudoClassEmpty(null);
            r14.addedAttributeOrPseudo();
            goto L45
        L38:
            r13 = new PseudoClassRoot(null);
            r14.addedAttributeOrPseudo();
            goto L45
        L39:
            r13 = new PseudoClassOnlyChild(true, r15.tag);
            r14.addedAttributeOrPseudo();
            goto L45
        L40:
            PseudoClassAnPlusB r5 = new PseudoClassAnPlusB(0, 1, false, true, r15.tag);
            r14.addedAttributeOrPseudo();
            r13 = r5;
            goto L45
        L41:
            PseudoClassAnPlusB r6 = new PseudoClassAnPlusB(0, 1, true, true, r15.tag);
            r14.addedAttributeOrPseudo();
        L34:
            r13 = r6;
            goto L45
        L42:
            r13 = new PseudoClassOnlyChild(false, null);
            r14.addedAttributeOrPseudo();
            goto L45
        L43:
            PseudoClassAnPlusB r7 = new PseudoClassAnPlusB(0, 1, false, false, null);
            r14.addedAttributeOrPseudo();
            r13 = r7;
            goto L45
        L44:
            PseudoClassAnPlusB r04 = new PseudoClassAnPlusB(0, 1, true, false, null);
            r14.addedAttributeOrPseudo();
            r03 = r04;
            goto L14
        L7:
            throw new CSSParseException("Unsupported pseudo class: ".concat(r0));
        L18:
            if (r1 != PseudoClassIdents.nth_child) goto L20;
        L23:
            boolean r9 = true;
        L25:
            if (r1 != PseudoClassIdents.nth_of_type) goto L27;
        L30:
            boolean r10 = true;
        L31:
            AnPlusB r133 = nextAnPlusB();
            if (r133 == null) goto L36;
            r6 = new PseudoClassAnPlusB(r133.f31a, r133.f32b, r9, r10, r15.tag);
            r14.addedAttributeOrPseudo();
            goto L34
        L36:
            throw new CSSParseException("Invalid or missing parameter section for pseudo class: ".concat(r0));
        L27:
            if (r1 == PseudoClassIdents.nth_last_of_type) goto L30;
            r10 = false;
            goto L31
        L20:
            if (r1 == PseudoClassIdents.nth_of_type) goto L23;
            r9 = false;
            goto L25
        L48:
            throw new CSSParseException("Invalid pseudo class");
        }

        private int scanForIdentifier() {
            boolean r0 = empty();
            int r1 = this.position;
            if (r0 == false) goto L5;
            return r1;
        L5:
            int r02 = this.input.charAt(r1);
            if (r02 != 45) goto L9;
            r02 = advanceChar();
        L9:
            if (r02 < 65) goto L11;
            if (r02 > 90) goto L11;
        L14:
            int r03 = advanceChar();
        L15:
            if (r03 < 65) goto L17;
            if (r03 > 90) goto L17;
        L27:
            r03 = advanceChar();
        L17:
            if (r03 < 97) goto L20;
            if (r03 <= 122) goto L27;
        L20:
            if (r03 >= 48) goto L22;
        L23:
            if (r03 == 45) goto L27;
            if (r03 == 95) goto L27;
            int r04 = this.position;
        L29:
            this.position = r1;
            return r04;
        L22:
            if (r03 <= 57) goto L27;
        L11:
            if (r02 < 97) goto L13;
            if (r02 <= 122) goto L14;
        L13:
            if (r02 == 95) goto L14;
            r04 = r1;
            goto L29
        }

        public String nextCSSString() {
            if (empty() == false) goto L5;
            return null;
        L5:
            char r0 = this.input.charAt(this.position);
            if (r0 != '\'') goto L8;
        L10:
            StringBuilder r1 = new StringBuilder();
            this.position++;
            int r2 = nextChar().intValue();
        L12:
            if (r2 == (-1)) goto L39;
            if (r2 == r0) goto L39;
            if (r2 != 92) goto L37;
            r2 = nextChar().intValue();
            if (r2 == (-1)) goto L12;
            if (r2 == 10) goto L36;
            if (r2 == 13) goto L36;
            if (r2 == 12) goto L36;
            int r5 = hexChar(r2);
            if (r5 == (-1)) goto L37;
            int r6 = 1;
        L30:
            if (r6 > 5) goto L35;
            r2 = nextChar().intValue();
            int r7 = hexChar(r2);
            if (r7 == (-1)) goto L35;
            r5 = (r5 * 16) + r7;
            r6 = r6 + 1;
        L35:
            r1.append((char) r5);
        L36:
            r2 = nextChar().intValue();
        L37:
            r1.append((char) r2);
            r2 = nextChar().intValue();
        L39:
            return r1.toString();
        L8:
            if (r0 == '\"') goto L10;
            return null;
        }

        public String nextIdentifier() {
            int r0 = scanForIdentifier();
            int r1 = this.position;
            if (r0 != r1) goto L6;
            return null;
        L6:
            String r12 = this.input.substring(r1, r0);
            this.position = r0;
            return r12;
        }

        public String nextLegacyURL() {
            StringBuilder r0 = new StringBuilder();
        L4:
            if (empty() == true) goto L45;
            char r1 = this.input.charAt(this.position);
            if (r1 == '\'') goto L45;
            if (r1 == '\"') goto L45;
            if (r1 == '(') goto L45;
            if (r1 == ')') goto L45;
            if (isWhitespace(r1) == true) goto L45;
            if (Character.isISOControl(r1) == true) goto L45;
            this.position++;
            if (r1 != '\\') goto L43;
            if (empty() == true) goto L4;
            String r12 = this.input;
            int r2 = this.position;
            this.position = r2 + 1;
            r1 = r12.charAt(r2);
            if (r1 == '\n') goto L4;
            if (r1 == '\r') goto L4;
            if (r1 == '\f') goto L4;
            int r22 = hexChar(r1);
            if (r22 == (-1)) goto L43;
            int r13 = 1;
        L34:
            if (r13 > 5) goto L42;
            if (empty() == true) goto L42;
            int r5 = hexChar(this.input.charAt(this.position));
            if (r5 == (-1)) goto L42;
            this.position++;
            r22 = (r22 * 16) + r5;
            r13 = r13 + 1;
        L42:
            r0.append((char) r22);
        L43:
            r0.append(r1);
        L45:
            if (r0.length() != 0) goto L49;
            return null;
        L49:
            return r0.toString();
        }

        public String nextPropertyValue() {
            if (empty() == false) goto L5;
            return null;
        L5:
            int r0 = this.position;
            int r2 = this.input.charAt(r0);
            int r3 = r0;
        L7:
            if (r2 == (-1)) goto L21;
            if (r2 == 59) goto L21;
            if (r2 == 125) goto L21;
            if (r2 == 33) goto L21;
            if (isEOL(r2) == true) goto L21;
            if (isWhitespace(r2) == true) goto L19;
            r3 = this.position + 1;
        L19:
            r2 = advanceChar();
        L21:
            if (this.position > r0) goto L23;
            this.position = r0;
            return null;
        L23:
            return this.input.substring(r0, r3);
        }

        public boolean nextSimpleSelector(Selector r11) {
            if (empty() == false) goto L5;
            return false;
        L5:
            int r0 = this.position;
            if (r11.isEmpty() == false) goto L8;
        L13:
            Combinator r2 = null;
        L15:
            if (consume('*') == false) goto L17;
            SimpleSelector r4 = new SimpleSelector(r2, null);
        L22:
            if (empty() == true) goto L78;
            if (consume('.') == true) goto L25;
            if (consume('#') == true) goto L34;
            if (consume('[') == true) goto L43;
            if (consume(':') == false) goto L78;
            if (r4 != null) goto L77;
            r4 = new SimpleSelector(r2, null);
        L77:
            parsePseudoClass(r11, r4);
            goto L22
        L43:
            if (r4 != null) goto L45;
            r4 = new SimpleSelector(r2, null);
        L45:
            skipWhitespace();
            String r5 = nextIdentifier();
            if (r5 == null) goto L72;
            skipWhitespace();
            if (consume(SignatureVisitor.INSTANCEOF) == false) goto L51;
            AttribOp r7 = AttribOp.EQUALS;
        L57:
            if (r7 == null) goto L63;
            skipWhitespace();
            String r8 = nextAttribValue();
            if (r8 == null) goto L62;
            skipWhitespace();
        L65:
            if (consume(']') == false) goto L70;
            if (r7 != null) goto L68;
            r7 = AttribOp.EXISTS;
        L68:
            r4.addAttrib(r5, r7, r8);
            r11.addedAttributeOrPseudo();
            goto L22
        L70:
            throw new CSSParseException("Invalid attribute simpleSelectors");
        L62:
            throw new CSSParseException("Invalid attribute simpleSelectors");
        L63:
            r8 = null;
            goto L65
        L51:
            if (consume("~=") == false) goto L54;
            r7 = AttribOp.INCLUDES;
            goto L57
        L54:
            if (consume("|=") == false) goto L56;
            r7 = AttribOp.DASHMATCH;
            goto L57
        L56:
            r7 = null;
            goto L57
        L72:
            throw new CSSParseException("Invalid attribute simpleSelectors");
        L34:
            if (r4 != null) goto L36;
            r4 = new SimpleSelector(r2, null);
        L36:
            String r52 = nextIdentifier();
            if (r52 == null) goto L40;
            r4.addAttrib("id", AttribOp.EQUALS, r52);
            r11.addedIdAttribute();
            goto L22
        L40:
            throw new CSSParseException("Invalid \"#id\" simpleSelectors");
        L25:
            if (r4 != null) goto L27;
            r4 = new SimpleSelector(r2, null);
        L27:
            String r53 = nextIdentifier();
            if (r53 == null) goto L31;
            r4.addAttrib("class", AttribOp.EQUALS, r53);
            r11.addedAttributeOrPseudo();
            goto L22
        L31:
            throw new CSSParseException("Invalid \".class\" simpleSelectors");
        L78:
            if (r4 == null) goto L81;
            r11.add(r4);
            return true;
        L81:
            this.position = r0;
            return false;
        L17:
            String r42 = nextIdentifier();
            if (r42 == null) goto L20;
            SimpleSelector r54 = new SimpleSelector(r2, r42);
            r11.addedElement();
            r4 = r54;
            goto L22
        L20:
            r4 = null;
            goto L22
        L8:
            if (consume(Typography.greater) == false) goto L11;
            r2 = Combinator.CHILD;
            skipWhitespace();
            goto L15
        L11:
            if (consume(SignatureVisitor.EXTENDS) == false) goto L13;
            r2 = Combinator.FOLLOWS;
            skipWhitespace();
            goto L15
        }

        public String nextURL() {
            if (empty() == false) goto L5;
            return null;
        L5:
            int r0 = this.position;
            if (consume("url(") == true) goto L8;
            return null;
        L8:
            skipWhitespace();
            String r2 = nextCSSString();
            if (r2 != null) goto L11;
            r2 = nextLegacyURL();
        L11:
            if (r2 != null) goto L14;
            this.position = r0;
            return null;
        L14:
            skipWhitespace();
            if (empty() == false) goto L17;
        L21:
            return r2;
        L17:
            if (consume(")") == true) goto L21;
            this.position = r0;
            return null;
        }
    }

    public enum Combinator extends Enum<Combinator> {
        private static final /* synthetic */ Combinator[] $VALUES = null;
        public static final Combinator CHILD = null;
        public static final Combinator DESCENDANT = null;
        public static final Combinator FOLLOWS = null;

        static {
            Combinator r0 = new Combinator("DESCENDANT", 0);
            DESCENDANT = r0;
            Combinator r1 = new Combinator("CHILD", 1);
            CHILD = r1;
            Combinator r2 = new Combinator("FOLLOWS", 2);
            FOLLOWS = r2;
            $VALUES = new Combinator[]{r0, r1, r2};
        }

        Combinator(String r1, int r2) {
        }

        public static Combinator valueOf(String r1) {
            return (Combinator) Enum.valueOf(Combinator.class, r1);
        }

        public static Combinator[] values() {
            return (Combinator[]) $VALUES.clone();
        }
    }

    public enum MediaType extends Enum<MediaType> {
        private static final /* synthetic */ MediaType[] $VALUES = null;
        public static final MediaType all = null;
        public static final MediaType aural = null;
        public static final MediaType braille = null;
        public static final MediaType embossed = null;
        public static final MediaType handheld = null;
        public static final MediaType print = null;
        public static final MediaType projection = null;
        public static final MediaType screen = null;
        public static final MediaType speech = null;
        public static final MediaType tty = null;
        public static final MediaType tv = null;

        static {
            MediaType r0 = new MediaType("all", 0);
            all = r0;
            MediaType r1 = new MediaType("aural", 1);
            aural = r1;
            MediaType r2 = new MediaType("braille", 2);
            braille = r2;
            MediaType r3 = new MediaType("embossed", 3);
            embossed = r3;
            MediaType r4 = new MediaType("handheld", 4);
            handheld = r4;
            MediaType r5 = new MediaType("print", 5);
            print = r5;
            MediaType r6 = new MediaType("projection", 6);
            projection = r6;
            MediaType r7 = new MediaType("screen", 7);
            screen = r7;
            MediaType r8 = new MediaType("speech", 8);
            speech = r8;
            MediaType r9 = new MediaType("tty", 9);
            tty = r9;
            MediaType r10 = new MediaType("tv", 10);
            tv = r10;
            $VALUES = new MediaType[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10};
        }

        MediaType(String r1, int r2) {
        }

        public static MediaType valueOf(String r1) {
            return (MediaType) Enum.valueOf(MediaType.class, r1);
        }

        public static MediaType[] values() {
            return (MediaType[]) $VALUES.clone();
        }
    }

    public interface PseudoClass {
        boolean matches(RuleMatchContext r1, SVG.SvgElementBase r2);
    }

    public static class PseudoClassAnPlusB implements PseudoClass {

        /* renamed from: a, reason: collision with root package name */
        private int f33a;

        /* renamed from: b, reason: collision with root package name */
        private int f34b;
        private boolean isFromStart;
        private boolean isOfType;
        private String nodeName;

        public PseudoClassAnPlusB(int r1, int r2, boolean r3, boolean r4, String r5) {
            this.f33a = r1;
            this.f34b = r2;
            this.isFromStart = r3;
            this.isOfType = r4;
            this.nodeName = r5;
        }

        @Override // com.caverock.androidsvg.CSSParser.PseudoClass
        public boolean matches(RuleMatchContext r7, SVG.SvgElementBase r8) {
            if (this.isOfType == true) goto L5;
        L7:
            String r72 = this.nodeName;
        L8:
            SVG.SvgContainer r0 = r8.parent;
            if (r0 == null) goto L20;
            Iterator<SVG.SvgObject> r02 = r0.getChildren().iterator();
            int r3 = 0;
            int r4 = 0;
        L12:
            if (r02.hasNext() == false) goto L22;
            SVG.SvgElementBase r5 = (SVG.SvgElementBase) r02.next();
            if (r5 != r8) goto L16;
            r3 = r4;
        L16:
            if (r72 == null) goto L19;
            if (r5.getNodeName().equals(r72) == false) goto L12;
        L19:
            r4 = r4 + 1;
        L22:
            if (this.isFromStart == false) goto L24;
            int r32 = r3 + 1;
        L25:
            int r73 = this.f33a;
            int r82 = this.f34b;
            if (r73 != 0) goto L31;
            if (r32 != r82) goto L29;
            return true;
        L29:
            return false;
        L31:
            if (((r32 - r82) % r73) == 0) goto L33;
        L37:
            return false;
        L33:
            if (Integer.signum(r32 - r82) != 0) goto L35;
        L36:
            return true;
        L35:
            if (Integer.signum(r32 - this.f34b) != Integer.signum(this.f33a)) goto L37;
        L24:
            r32 = r4 - r3;
            goto L25
        L20:
            r3 = 0;
            r4 = 1;
            goto L22
        L5:
            if (this.nodeName != null) goto L7;
            r72 = r8.getNodeName();
            goto L8
        }

        public String toString() {
            if (this.isFromStart == false) goto L5;
            String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            boolean r1 = this.isOfType;
            int r2 = this.f33a;
            if (r1 == false) goto L11;
            return String.format("nth-%schild(%dn%+d of type <%s>)", new Object[]{r0, Integer.valueOf(r2), Integer.valueOf(this.f34b), this.nodeName});
        L11:
            return String.format("nth-%schild(%dn%+d)", new Object[]{r0, Integer.valueOf(r2), Integer.valueOf(this.f34b)});
        L5:
            r0 = "last-";
            goto L6
        }
    }

    public static class PseudoClassEmpty implements PseudoClass {
        private PseudoClassEmpty() {
        }

        public /* synthetic */ PseudoClassEmpty(AnonymousClass1 r1) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.caverock.androidsvg.CSSParser.PseudoClass
        public boolean matches(RuleMatchContext r1, SVG.SvgElementBase r2) {
            if ((r2 instanceof SVG.SvgContainer) == true) goto L5;
            return true;
        L5:
            if (((SVG.SvgContainer) r2).getChildren().size() != 0) goto L7;
            return true;
        L7:
            return false;
        }

        public String toString() {
            return "empty";
        }
    }

    public enum PseudoClassIdents extends Enum<PseudoClassIdents> {
        private static final /* synthetic */ PseudoClassIdents[] $VALUES = null;
        public static final PseudoClassIdents UNSUPPORTED = null;
        public static final PseudoClassIdents active = null;
        private static final Map<String, PseudoClassIdents> cache = null;
        public static final PseudoClassIdents checked = null;
        public static final PseudoClassIdents disabled = null;
        public static final PseudoClassIdents empty = null;
        public static final PseudoClassIdents enabled = null;
        public static final PseudoClassIdents first_child = null;
        public static final PseudoClassIdents first_of_type = null;
        public static final PseudoClassIdents focus = null;
        public static final PseudoClassIdents hover = null;
        public static final PseudoClassIdents indeterminate = null;
        public static final PseudoClassIdents lang = null;
        public static final PseudoClassIdents last_child = null;
        public static final PseudoClassIdents last_of_type = null;
        public static final PseudoClassIdents link = null;
        public static final PseudoClassIdents not = null;
        public static final PseudoClassIdents nth_child = null;
        public static final PseudoClassIdents nth_last_child = null;
        public static final PseudoClassIdents nth_last_of_type = null;
        public static final PseudoClassIdents nth_of_type = null;
        public static final PseudoClassIdents only_child = null;
        public static final PseudoClassIdents only_of_type = null;
        public static final PseudoClassIdents root = null;
        public static final PseudoClassIdents target = null;
        public static final PseudoClassIdents visited = null;

        static {
            PseudoClassIdents r1 = new PseudoClassIdents("target", 0);
            target = r1;
            PseudoClassIdents r2 = new PseudoClassIdents("root", 1);
            root = r2;
            PseudoClassIdents r3 = new PseudoClassIdents("nth_child", 2);
            nth_child = r3;
            PseudoClassIdents r4 = new PseudoClassIdents("nth_last_child", 3);
            nth_last_child = r4;
            PseudoClassIdents r5 = new PseudoClassIdents("nth_of_type", 4);
            nth_of_type = r5;
            PseudoClassIdents r6 = new PseudoClassIdents("nth_last_of_type", 5);
            nth_last_of_type = r6;
            PseudoClassIdents r7 = new PseudoClassIdents("first_child", 6);
            first_child = r7;
            PseudoClassIdents r8 = new PseudoClassIdents("last_child", 7);
            last_child = r8;
            PseudoClassIdents r9 = new PseudoClassIdents("first_of_type", 8);
            first_of_type = r9;
            PseudoClassIdents r10 = new PseudoClassIdents("last_of_type", 9);
            last_of_type = r10;
            PseudoClassIdents r11 = new PseudoClassIdents("only_child", 10);
            only_child = r11;
            PseudoClassIdents r12 = new PseudoClassIdents("only_of_type", 11);
            only_of_type = r12;
            PseudoClassIdents r13 = new PseudoClassIdents("empty", 12);
            empty = r13;
            PseudoClassIdents r14 = new PseudoClassIdents("not", 13);
            not = r14;
            PseudoClassIdents r15 = new PseudoClassIdents("lang", 14);
            lang = r15;
            PseudoClassIdents r0 = new PseudoClassIdents("link", 15);
            link = r0;
            PseudoClassIdents r16 = new PseudoClassIdents("visited", 16);
            visited = r16;
            PseudoClassIdents r02 = new PseudoClassIdents("hover", 17);
            hover = r02;
            PseudoClassIdents r17 = new PseudoClassIdents("active", 18);
            active = r17;
            PseudoClassIdents r03 = new PseudoClassIdents("focus", 19);
            focus = r03;
            PseudoClassIdents r18 = new PseudoClassIdents("enabled", 20);
            enabled = r18;
            PseudoClassIdents r04 = new PseudoClassIdents("disabled", 21);
            disabled = r04;
            PseudoClassIdents r19 = new PseudoClassIdents("checked", 22);
            checked = r19;
            PseudoClassIdents r05 = new PseudoClassIdents("indeterminate", 23);
            indeterminate = r05;
            PseudoClassIdents r110 = new PseudoClassIdents("UNSUPPORTED", 24);
            UNSUPPORTED = r110;
            int r06 = 0;
            $VALUES = new PseudoClassIdents[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r0, r16, r02, r17, r03, r18, r04, r19, r05, r110};
            cache = new HashMap();
            PseudoClassIdents[] r111 = values();
            int r22 = r111.length;
        L3:
            if (r06 >= r22) goto L8;
            PseudoClassIdents r32 = r111[r06];
            if (r32 == UNSUPPORTED) goto L7;
            String r42 = r32.name().replace('_', SignatureVisitor.SUPER);
            cache.put(r42, r32);
        L7:
            r06 = r06 + 1;
            goto L3
        }

        PseudoClassIdents(String r1, int r2) {
        }

        public static PseudoClassIdents fromString(String r1) {
            PseudoClassIdents r12 = cache.get(r1);
            if (r12 == null) goto L6;
            return r12;
        L6:
            return UNSUPPORTED;
        }

        public static PseudoClassIdents valueOf(String r1) {
            return (PseudoClassIdents) Enum.valueOf(PseudoClassIdents.class, r1);
        }

        public static PseudoClassIdents[] values() {
            return (PseudoClassIdents[]) $VALUES.clone();
        }
    }

    public static class PseudoClassNot implements PseudoClass {
        private List<Selector> selectorGroup;

        public PseudoClassNot(List<Selector> r1) {
            this.selectorGroup = r1;
        }

        public int getSpecificity() {
            Iterator<Selector> r2 = this.selectorGroup.iterator();
            int r0 = Integer.MIN_VALUE;
        L4:
            if (r2.hasNext() == false) goto L8;
            int r1 = r2.next().specificity;
            if (r1 <= r0) goto L4;
            r0 = r1;
            goto L4
        L8:
            return r0;
        }

        @Override // com.caverock.androidsvg.CSSParser.PseudoClass
        public boolean matches(RuleMatchContext r2, SVG.SvgElementBase r3) {
            Iterator<Selector> r1 = this.selectorGroup.iterator();
        L4:
            if (r1.hasNext() == false) goto L9;
            if (CSSParser.ruleMatch(r2, r1.next(), r3) == false) goto L4;
            return false;
        L9:
            return true;
        }

        public String toString() {
            return "not(" + this.selectorGroup + ")";
        }
    }

    public static class PseudoClassNotSupported implements PseudoClass {
        private String clazz;

        public PseudoClassNotSupported(String r1) {
            this.clazz = r1;
        }

        @Override // com.caverock.androidsvg.CSSParser.PseudoClass
        public boolean matches(RuleMatchContext r1, SVG.SvgElementBase r2) {
            return false;
        }

        public String toString() {
            return this.clazz;
        }
    }

    public static class PseudoClassOnlyChild implements PseudoClass {
        private boolean isOfType;
        private String nodeName;

        public PseudoClassOnlyChild(boolean r1, String r2) {
            this.isOfType = r1;
            this.nodeName = r2;
        }

        @Override // com.caverock.androidsvg.CSSParser.PseudoClass
        public boolean matches(RuleMatchContext r4, SVG.SvgElementBase r5) {
            if (this.isOfType == true) goto L5;
        L7:
            String r3 = this.nodeName;
        L8:
            SVG.SvgContainer r42 = r5.parent;
            if (r42 == null) goto L18;
            Iterator<SVG.SvgObject> r43 = r42.getChildren().iterator();
            int r1 = 0;
        L12:
            if (r43.hasNext() == false) goto L19;
            SVG.SvgElementBase r2 = (SVG.SvgElementBase) r43.next();
            if (r3 == null) goto L17;
            if (r2.getNodeName().equals(r3) == false) goto L12;
        L17:
            r1 = r1 + 1;
        L19:
            if (r1 != 1) goto L21;
            return true;
        L21:
            return false;
        L18:
            r1 = 1;
            goto L19
        L5:
            if (this.nodeName != null) goto L7;
            r3 = r5.getNodeName();
            goto L8
        }

        public String toString() {
            if (this.isOfType == false) goto L7;
            return String.format("only-of-type <%s>", new Object[]{this.nodeName});
        L7:
            return String.format("only-child", new Object[0]);
        }
    }

    public static class PseudoClassRoot implements PseudoClass {
        private PseudoClassRoot() {
        }

        public /* synthetic */ PseudoClassRoot(AnonymousClass1 r1) {
            this();
        }

        @Override // com.caverock.androidsvg.CSSParser.PseudoClass
        public boolean matches(RuleMatchContext r1, SVG.SvgElementBase r2) {
            if (r2.parent != null) goto L6;
            return true;
        L6:
            return false;
        }

        public String toString() {
            return "root";
        }
    }

    public static class PseudoClassTarget implements PseudoClass {
        private PseudoClassTarget() {
        }

        public /* synthetic */ PseudoClassTarget(AnonymousClass1 r1) {
            this();
        }

        @Override // com.caverock.androidsvg.CSSParser.PseudoClass
        public boolean matches(RuleMatchContext r1, SVG.SvgElementBase r2) {
            if (r1 != null) goto L5;
            return false;
        L5:
            if (r2 != r1.targetElement) goto L9;
            return true;
        L9:
            return false;
        }

        public String toString() {
            return "target";
        }
    }

    public static class Rule {
        Selector selector;
        Source source;
        SVG.Style style;

        public Rule(Selector r1, SVG.Style r2, Source r3) {
            this.selector = r1;
            this.style = r2;
            this.source = r3;
        }

        public String toString() {
            return String.valueOf(this.selector) + " {...} (src=" + this.source + ")";
        }
    }

    public static class RuleMatchContext {
        SVG.SvgElementBase targetElement;

        public RuleMatchContext() {
        }

        public String toString() {
            SVG.SvgElementBase r0 = this.targetElement;
            if (r0 != null) goto L5;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            return String.format("<%s id=\"%s\">", new Object[]{r0.getNodeName(), this.targetElement.id});
        }
    }

    public static class Ruleset {
        private List<Rule> rules;

        public Ruleset() {
            this.rules = null;
        }

        public void add(Rule r4) {
            if (this.rules != null) goto L5;
            this.rules = new ArrayList();
        L5:
            int r0 = 0;
        L6:
            int r1 = this.rules.size();
            List<Rule> r2 = this.rules;
            if (r0 >= r1) goto L13;
            if (r2.get(r0).selector.specificity > r4.selector.specificity) goto L10;
            r0 = r0 + 1;
            goto L6
        L10:
            this.rules.add(r0, r4);
            return;
        L13:
            r2.add(r4);
        }

        public void addAll(Ruleset r3) {
            if (r3.rules != null) goto L6;
            return;
        L6:
            if (this.rules != null) goto L8;
            this.rules = new ArrayList(r3.rules.size());
        L8:
            Iterator<Rule> r32 = r3.rules.iterator();
        L10:
            if (r32.hasNext() == false) goto L14;
            add(r32.next());
            goto L10
        }

        public List<Rule> getRules() {
            return this.rules;
        }

        public boolean isEmpty() {
            List<Rule> r0 = this.rules;
            if (r0 != null) goto L5;
            return true;
        L5:
            if (r0.isEmpty() == true) goto L11;
            return false;
        L11:
            return true;
        }

        public void removeFromSource(Source r2) {
            List<Rule> r1 = this.rules;
            if (r1 == null) goto L11;
            Iterator<Rule> r12 = r1.iterator();
        L7:
            if (r12.hasNext() == false) goto L17;
            if (r12.next().source != r2) goto L7;
            r12.remove();
            goto L7
        L17:
            return;
        }

        public int ruleCount() {
            List<Rule> r0 = this.rules;
            if (r0 != null) goto L5;
            return 0;
        L5:
            return r0.size();
        }

        public String toString() {
            if (this.rules != null) goto L6;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            StringBuilder r0 = new StringBuilder();
            Iterator<Rule> r2 = this.rules.iterator();
        L8:
            if (r2.hasNext() == false) goto L11;
            r0.append(r2.next().toString());
            r0.append('\n');
            goto L8
        L11:
            return r0.toString();
        }
    }

    public static class Selector {
        List<SimpleSelector> simpleSelectors;
        int specificity;

        private Selector() {
            this.simpleSelectors = null;
            this.specificity = 0;
        }

        public /* synthetic */ Selector(AnonymousClass1 r1) {
            this();
        }

        public void add(SimpleSelector r2) {
            if (this.simpleSelectors != null) goto L5;
            this.simpleSelectors = new ArrayList();
        L5:
            this.simpleSelectors.add(r2);
        }

        public void addedAttributeOrPseudo() {
            this.specificity += 1000;
        }

        public void addedElement() {
            this.specificity++;
        }

        public void addedIdAttribute() {
            this.specificity += DurationKt.NANOS_IN_MILLIS;
        }

        public SimpleSelector get(int r1) {
            return this.simpleSelectors.get(r1);
        }

        public boolean isEmpty() {
            List<SimpleSelector> r0 = this.simpleSelectors;
            if (r0 != null) goto L5;
            return true;
        L5:
            if (r0.isEmpty() == true) goto L11;
            return false;
        L11:
            return true;
        }

        public int size() {
            List<SimpleSelector> r0 = this.simpleSelectors;
            if (r0 != null) goto L7;
            return 0;
        L7:
            return r0.size();
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder();
            Iterator<SimpleSelector> r1 = this.simpleSelectors.iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            r0.append(r1.next());
            r0.append(' ');
            goto L4
        L6:
            r0.append('[');
            r0.append(this.specificity);
            r0.append(']');
            return r0.toString();
        }
    }

    public static class SimpleSelector {
        List<Attrib> attribs;
        Combinator combinator;
        List<PseudoClass> pseudos;
        String tag;

        public SimpleSelector(Combinator r2, String r3) {
            this.combinator = null;
            this.tag = null;
            this.attribs = null;
            this.pseudos = null;
            if (r2 != null) goto L6;
            r2 = Combinator.DESCENDANT;
        L6:
            this.combinator = r2;
            this.tag = r3;
        }

        public void addAttrib(String r2, AttribOp r3, String r4) {
            if (this.attribs != null) goto L5;
            this.attribs = new ArrayList();
        L5:
            this.attribs.add(new Attrib(r2, r3, r4));
        }

        public void addPseudo(PseudoClass r2) {
            if (this.pseudos != null) goto L5;
            this.pseudos = new ArrayList();
        L5:
            this.pseudos.add(r2);
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder();
            Combinator r1 = this.combinator;
            if (r1 != Combinator.CHILD) goto L6;
            r0.append("> ");
        L8:
            String r12 = this.tag;
            if (r12 != null) goto L11;
            r12 = "*";
        L11:
            r0.append(r12);
            List<Attrib> r13 = this.attribs;
            if (r13 == null) goto L27;
            Iterator<Attrib> r14 = r13.iterator();
        L15:
            if (r14.hasNext() == false) goto L27;
            Attrib r2 = r14.next();
            r0.append('[');
            r0.append(r2.name);
            int r3 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$CSSParser$AttribOp[r2.operation.ordinal()];
            if (r3 != 1) goto L19;
            r0.append(SignatureVisitor.INSTANCEOF);
            r0.append(r2.value);
        L26:
            r0.append(']');
            goto L15
        L19:
            if (r3 != 2) goto L21;
            r0.append("~=");
            r0.append(r2.value);
            goto L26
        L21:
            if (r3 != 3) goto L26;
            r0.append("|=");
            r0.append(r2.value);
        L27:
            List<PseudoClass> r5 = this.pseudos;
            if (r5 == null) goto L34;
            Iterator<PseudoClass> r52 = r5.iterator();
        L31:
            if (r52.hasNext() == false) goto L34;
            PseudoClass r15 = r52.next();
            r0.append(':');
            r0.append(r15);
        L34:
            return r0.toString();
        L6:
            if (r1 != Combinator.FOLLOWS) goto L8;
            r0.append("+ ");
            goto L8
        }
    }

    public enum Source extends Enum<Source> {
        private static final /* synthetic */ Source[] $VALUES = null;
        public static final Source Document = null;
        public static final Source RenderOptions = null;

        static {
            Source r0 = new Source("Document", 0);
            Document = r0;
            Source r1 = new Source("RenderOptions", 1);
            RenderOptions = r1;
            $VALUES = new Source[]{r0, r1};
        }

        Source(String r1, int r2) {
        }

        public static Source valueOf(String r1) {
            return (Source) Enum.valueOf(Source.class, r1);
        }

        public static Source[] values() {
            return (Source[]) $VALUES.clone();
        }
    }

    public CSSParser(MediaType r2, Source r3) {
        this.inMediaRule = false;
        this.deviceMediaType = r2;
        this.source = r3;
    }

    private static int getChildPosition(List<SVG.SvgContainer> r2, int r3, SVG.SvgElementBase r4) {
        int r0 = 0;
        if (r3 >= 0) goto L5;
        return 0;
    L5:
        SVG.SvgContainer r22 = r2.get(r3);
        SVG.SvgContainer r32 = r4.parent;
        if (r22 == r32) goto L8;
        return -1;
    L8:
        Iterator<SVG.SvgObject> r23 = r32.getChildren().iterator();
    L10:
        if (r23.hasNext() == false) goto L15;
        if (r23.next() == r4) goto L13;
        r0 = r0 + 1;
        goto L10
    L13:
        return r0;
    L15:
        return -1;
    }

    public static boolean mediaMatches(String r1, MediaType r2) {
        CSSTextScanner r0 = new CSSTextScanner(r1);
        r0.skipWhitespace();
        return mediaMatches(parseMediaList(r0), r2);
    }

    private static boolean mediaMatches(List<MediaType> r2, MediaType r3) {
        Iterator<MediaType> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L10;
        MediaType r0 = r22.next();
        if (r0 == MediaType.all) goto L8;
        if (r0 != r3) goto L4;
        return true;
    L8:
        return true;
    L10:
        return false;
    }

    private void parseAtRule(Ruleset r4, CSSTextScanner r5) {
        String r0 = r5.nextIdentifier();
        r5.skipWhitespace();
        if (r0 == null) goto L45;
        if (this.inMediaRule == true) goto L24;
        if (r0.equals("media") == false) goto L24;
        List<MediaType> r02 = parseMediaList(r5);
        if (r5.consume('{') == false) goto L22;
        r5.skipWhitespace();
        if (mediaMatches(r02, this.deviceMediaType) == false) goto L13;
        this.inMediaRule = true;
        r4.addAll(parseRuleset(r5));
        this.inMediaRule = false;
    L15:
        if (r5.empty() == false) goto L17;
    L42:
        r5.skipWhitespace();
        return;
    L17:
        if (r5.consume('}') == true) goto L42;
        throw new CSSParseException("Invalid @media rule: expected '}' at end of rule set");
    L13:
        parseRuleset(r5);
        goto L15
    L22:
        throw new CSSParseException("Invalid @media rule: missing rule set");
    L24:
        if (this.inMediaRule == false) goto L26;
    L41:
        warn("Ignoring @%s rule", new Object[]{r0});
        skipAtRule(r5);
        goto L42
    L26:
        if (r0.equals("import") == false) goto L41;
        String r3 = r5.nextURL();
        if (r3 != null) goto L30;
        r3 = r5.nextCSSString();
    L30:
        if (r3 == null) goto L40;
        r5.skipWhitespace();
        parseMediaList(r5);
        if (r5.empty() == false) goto L34;
    L38:
        SVG.getFileResolver();
        goto L42
    L34:
        if (r5.consume(';') == true) goto L38;
        throw new CSSParseException("Invalid @media rule: expected '}' at end of rule set");
    L40:
        throw new CSSParseException("Invalid @import rule: expected string or url()");
    L45:
        throw new CSSParseException("Invalid '@' rule");
    }

    public static List<String> parseClassAttribute(String r2) {
        CSSTextScanner r0 = new CSSTextScanner(r2);
        ArrayList r22 = null;
    L4:
        if (r0.empty() == true) goto L11;
        String r1 = r0.nextToken();
        if (r1 == null) goto L4;
        if (r22 != null) goto L10;
        r22 = new ArrayList();
    L10:
        r22.add(r1);
        r0.skipWhitespace();
        goto L4
    L11:
        return r22;
    }

    private SVG.Style parseDeclarations(CSSTextScanner r4) {
        SVG.Style r3 = new SVG.Style();
    L3:
        String r0 = r4.nextIdentifier();
        r4.skipWhitespace();
        if (r4.consume(':') == false) goto L22;
        r4.skipWhitespace();
        String r1 = r4.nextPropertyValue();
        if (r1 == null) goto L20;
        r4.skipWhitespace();
        if (r4.consume('!') == false) goto L14;
        r4.skipWhitespace();
        if (r4.consume("important") == false) goto L13;
        r4.skipWhitespace();
        goto L14
    L13:
        throw new CSSParseException("Malformed rule set: found unexpected '!'");
    L14:
        r4.consume(';');
        SVGParser.processStyleProperty(r3, r0, r1);
        r4.skipWhitespace();
        if (r4.empty() == true) goto L18;
        if (r4.consume('}') == false) goto L3;
    L18:
        return r3;
    L20:
        throw new CSSParseException("Expected property value");
    L22:
        throw new CSSParseException("Expected ':'");
    }

    private static List<MediaType> parseMediaList(CSSTextScanner r2) {
        ArrayList r0 = new ArrayList();
    L4:
        if (r2.empty() == true) goto L11;
        String r1 = r2.nextWord();
        if (r1 == null) goto L11;
        r0.add(MediaType.valueOf(r1));     // Catch: IllegalArgumentException -> L12
    L10:
        if (r2.skipCommaWhitespace() == true) goto L4;
    L11:
        return r0;
    }

    private boolean parseRule(Ruleset r5, CSSTextScanner r6) {
        List r0 = CSSTextScanner.access$400(r6);
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isEmpty() == false) goto L7;
        return false;
    L7:
        if (r6.consume('{') == false) goto L15;
        r6.skipWhitespace();
        SVG.Style r1 = parseDeclarations(r6);
        r6.skipWhitespace();
        Iterator r62 = r0.iterator();
    L10:
        if (r62.hasNext() == false) goto L12;
        r5.add(new Rule((Selector) r62.next(), r1, this.source));
        goto L10
    L12:
        return true;
    L15:
        throw new CSSParseException("Malformed rule block: expected '{'");
    }

    private Ruleset parseRuleset(CSSTextScanner r3) {
        Ruleset r0 = new Ruleset();
    L22:
        if (r3.empty() == true) goto L19;
        if (r3.consume("<!--") == true) goto L22;
        if (r3.consume("-->") == true) goto L22;
        if (r3.consume('@') == true) goto L13;
        if (parseRule(r0, r3) == false) goto L19;
    L13:
        parseAtRule(r0, r3);     // Catch: CSSParseException -> L14
    L19:
        return r0;
    L14:
        e = move-exception;
        Log.e("CSSParser", "CSS parser terminated early due to error: " + e.getMessage());
        return r0;
    }

    private static boolean ruleMatch(RuleMatchContext r10, Selector r11, int r12, List<SVG.SvgContainer> r13, int r14, SVG.SvgElementBase r15) {
        SimpleSelector r0 = r11.get(r12);
        if (selectorMatch(r10, r0, r13, r14, r15) == true) goto L5;
        return false;
    L5:
        Combinator r02 = r0.combinator;
        if (r02 != Combinator.DESCENDANT) goto L16;
        if (r12 != 0) goto L9;
        return true;
    L9:
        if (r14 < 0) goto L14;
        if (ruleMatchOnAncestors(r10, r11, r12 - 1, r13, r14) == true) goto L12;
        r14 = r14 - 1;
        goto L9
    L12:
        return true;
    L14:
        return false;
    L16:
        if (r02 == Combinator.CHILD) goto L18;
        int r03 = getChildPosition(r13, r14, r15);
        if (r03 > 0) goto L23;
        return false;
    L23:
        return ruleMatch(r10, r11, r12 - 1, r13, r14, (SVG.SvgElementBase) r15.parent.getChildren().get(r03 - 1));
    L18:
        return ruleMatchOnAncestors(r10, r11, r12 - 1, r13, r14);
    }

    public static boolean ruleMatch(RuleMatchContext r6, Selector r7, SVG.SvgElementBase r8) {
        ArrayList r3 = new ArrayList();
        Object r0 = r8.parent;
    L4:
        if (r0 == null) goto L6;
        r3.add(0, r0);
        r0 = ((SVG.SvgObject) r0).parent;
        goto L4
    L6:
        int r4 = r3.size() - 1;
        if (r7.size() != 1) goto L11;
        return selectorMatch(r6, r7.get(0), r3, r4, r8);
    L11:
        return ruleMatch(r6, r7, r7.size() - 1, r3, r4, r8);
    }

    private static boolean ruleMatchOnAncestors(RuleMatchContext r11, Selector r12, int r13, List<SVG.SvgContainer> r14, int r15) {
        SimpleSelector r0 = r12.get(r13);
        SVG.SvgElementBase r1 = (SVG.SvgElementBase) r14.get(r15);
        if (selectorMatch(r11, r0, r14, r15, r1) == true) goto L5;
        return false;
    L5:
        Combinator r02 = r0.combinator;
        if (r02 != Combinator.DESCENDANT) goto L15;
        if (r13 != 0) goto L9;
        return true;
    L9:
        if (r15 <= 0) goto L13;
        r15 = r15 - 1;
        if (ruleMatchOnAncestors(r11, r12, r13 - 1, r14, r15) == false) goto L9;
        return true;
    L13:
        return false;
    L15:
        if (r02 == Combinator.CHILD) goto L17;
        int r03 = getChildPosition(r14, r15, r1);
        if (r03 > 0) goto L22;
        return false;
    L22:
        return ruleMatch(r11, r12, r13 - 1, r14, r15, (SVG.SvgElementBase) r1.parent.getChildren().get(r03 - 1));
    L17:
        return ruleMatchOnAncestors(r11, r12, r13 - 1, r14, r15 - 1);
    }

    private static boolean selectorMatch(RuleMatchContext r3, SimpleSelector r4, List<SVG.SvgContainer> r5, int r6, SVG.SvgElementBase r7) {
        String r52 = r4.tag;
        if (r52 != null) goto L5;
    L7:
        List<Attrib> r53 = r4.attribs;
        if (r53 == null) goto L26;
        Iterator<Attrib> r54 = r53.iterator();
    L11:
        if (r54.hasNext() == false) goto L26;
        Attrib r0 = r54.next();
        String r1 = r0.name;
        r1.getClass();
        if (r1.equals("id") == false) goto L15;
        if (r0.value.equals(r7.id) == true) goto L11;
        return false;
    L15:
        if (r1.equals("class") == false) goto L16;
        List<String> r12 = r7.classNames;
        if (r12 == null) goto L19;
        if (r12.contains(r0.value) == true) goto L11;
        return false;
    L19:
        return false;
    L16:
        return false;
    L26:
        List<PseudoClass> r42 = r4.pseudos;
        if (r42 == null) goto L34;
        Iterator<PseudoClass> r43 = r42.iterator();
    L30:
        if (r43.hasNext() == false) goto L49;
        if (r43.next().matches(r3, r7) == true) goto L30;
        return false;
    L49:
        return true;
    L34:
        return true;
    L5:
        if (r52.equals(r7.getNodeName().toLowerCase(Locale.US)) == true) goto L7;
        return false;
    }

    private void skipAtRule(CSSTextScanner r3) {
        int r2 = 0;
    L4:
        if (r3.empty() == true) goto L17;
        int r0 = r3.nextChar().intValue();
        if (r0 != 59) goto L10;
        if (r2 != 0) goto L10;
        return;
    L10:
        if (r0 == 123) goto L11;
        if (r0 != 125) goto L4;
        if (r2 <= 0) goto L4;
        r2 = r2 - 1;
        if (r2 != 0) goto L4;
        return;
    L11:
        r2 = r2 + 1;
        goto L4
    }

    private static void warn(String r1, Object... r2) {
        Log.w("CSSParser", String.format(r1, r2));
    }

    public Ruleset parse(String r2) {
        CSSTextScanner r0 = new CSSTextScanner(r2);
        r0.skipWhitespace();
        return parseRuleset(r0);
    }
}
