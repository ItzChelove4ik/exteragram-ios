package org.commonmark.internal;

import java.util.ArrayList;
import java.util.List;
import org.commonmark.internal.util.Escaping;
import org.commonmark.internal.util.LinkScanner;
import org.commonmark.internal.util.Parsing;
import org.commonmark.node.LinkReferenceDefinition;

/* loaded from: classes5.dex */
public class LinkReferenceDefinitionParser {
    private final List<LinkReferenceDefinition> definitions;
    private String destination;
    private StringBuilder label;
    private String normalizedLabel;
    private final StringBuilder paragraph;
    private boolean referenceValid;
    private State state;
    private StringBuilder title;
    private char titleDelimiter;

    /* renamed from: org.commonmark.internal.LinkReferenceDefinitionParser$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$commonmark$internal$LinkReferenceDefinitionParser$State = null;

        static {
            int[] r0 = new int[State.values().length];
            $SwitchMap$org$commonmark$internal$LinkReferenceDefinitionParser$State = r0;
            r0[State.PARAGRAPH.ordinal()] = 1;     // Catch: NoSuchFieldError -> L10
        L16:
            $SwitchMap$org$commonmark$internal$LinkReferenceDefinitionParser$State[State.START_DEFINITION.ordinal()] = 2;     // Catch: NoSuchFieldError -> L11
        L20:
            $SwitchMap$org$commonmark$internal$LinkReferenceDefinitionParser$State[State.LABEL.ordinal()] = 3;     // Catch: NoSuchFieldError -> L12
        L26:
            $SwitchMap$org$commonmark$internal$LinkReferenceDefinitionParser$State[State.DESTINATION.ordinal()] = 4;     // Catch: NoSuchFieldError -> L13
        L18:
            $SwitchMap$org$commonmark$internal$LinkReferenceDefinitionParser$State[State.START_TITLE.ordinal()] = 5;     // Catch: NoSuchFieldError -> L14
        L22:
            $SwitchMap$org$commonmark$internal$LinkReferenceDefinitionParser$State[State.TITLE.ordinal()] = 6;     // Catch: NoSuchFieldError -> L15
            return;
        }
    }

    public enum State extends Enum<State> {
        private static final /* synthetic */ State[] $VALUES = null;
        public static final State DESTINATION = null;
        public static final State LABEL = null;
        public static final State PARAGRAPH = null;
        public static final State START_DEFINITION = null;
        public static final State START_TITLE = null;
        public static final State TITLE = null;

        static {
            State r0 = new State("START_DEFINITION", 0);
            START_DEFINITION = r0;
            State r1 = new State("LABEL", 1);
            LABEL = r1;
            State r2 = new State("DESTINATION", 2);
            DESTINATION = r2;
            State r3 = new State("START_TITLE", 3);
            START_TITLE = r3;
            State r4 = new State("TITLE", 4);
            TITLE = r4;
            State r5 = new State("PARAGRAPH", 5);
            PARAGRAPH = r5;
            $VALUES = new State[]{r0, r1, r2, r3, r4, r5};
        }

        State(String r1, int r2) {
        }

        public static State valueOf(String r1) {
            return (State) Enum.valueOf(State.class, r1);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    public LinkReferenceDefinitionParser() {
        this.state = State.START_DEFINITION;
        this.paragraph = new StringBuilder();
        this.definitions = new ArrayList();
        this.referenceValid = false;
    }

    private int destination(CharSequence r6, int r7) {
        int r72 = Parsing.skipSpaceTab(r6, r7, r6.length());
        int r0 = LinkScanner.scanLinkDestination(r6, r72);
        if (r0 != (-1)) goto L6;
        return -1;
    L6:
        if (r6.charAt(r72) != '<') goto L8;
        String r73 = r6.subSequence(r72 + 1, r0 - 1).toString();
    L9:
        this.destination = r73;
        int r74 = Parsing.skipSpaceTab(r6, r0, r6.length());
        if (r74 < r6.length()) goto L12;
        this.referenceValid = true;
        this.paragraph.setLength(0);
    L14:
        this.state = State.START_TITLE;
        return r74;
    L12:
        if (r74 != r0) goto L14;
        return -1;
    L8:
        r73 = r6.subSequence(r72, r0).toString();
        goto L9
    }

    private void finishReference() {
        if (this.referenceValid == true) goto L5;
        return;
    L5:
        String r0 = Escaping.unescapeString(this.destination);
        StringBuilder r1 = this.title;
        if (r1 == null) goto L8;
        String r12 = Escaping.unescapeString(r1.toString());
    L9:
        this.definitions.add(new LinkReferenceDefinition(this.normalizedLabel, r0, r12));
        this.label = null;
        this.referenceValid = false;
        this.normalizedLabel = null;
        this.destination = null;
        this.title = null;
        return;
    L8:
        r12 = null;
        goto L9
    }

    private int label(CharSequence r4, int r5) {
        int r0 = LinkScanner.scanLinkLabelContent(r4, r5);
        if (r0 != (-1)) goto L5;
        return -1;
    L5:
        this.label.append(r4, r5, r0);
        if (r0 < r4.length()) goto L10;
        this.label.append('\n');
        return r0;
    L10:
        if (r4.charAt(r0) != ']') goto L24;
        int r52 = r0 + 1;
        if (r52 >= r4.length()) goto L24;
        if (r4.charAt(r52) != ':') goto L24;
        if (this.label.length() <= 999) goto L19;
        return -1;
    L19:
        String r53 = Escaping.normalizeLabelContent(this.label.toString());
        if (r53.isEmpty() == false) goto L22;
        return -1;
    L22:
        this.normalizedLabel = r53;
        this.state = State.DESTINATION;
        return Parsing.skipSpaceTab(r4, r0 + 2, r4.length());
    L24:
        return -1;
    }

    private int startDefinition(CharSequence r3, int r4) {
        int r42 = Parsing.skipSpaceTab(r3, r4, r3.length());
        if (r42 < r3.length()) goto L5;
        return -1;
    L5:
        if (r3.charAt(r42) != '[') goto L13;
        this.state = State.LABEL;
        this.label = new StringBuilder();
        int r43 = r42 + 1;
        if (r43 < r3.length()) goto L10;
        this.label.append('\n');
    L10:
        return r43;
    L13:
        return -1;
    }

    private int startTitle(CharSequence r3, int r4) {
        int r42 = Parsing.skipSpaceTab(r3, r4, r3.length());
        if (r42 < r3.length()) goto L6;
        this.state = State.START_DEFINITION;
        return r42;
    L6:
        this.titleDelimiter = 0;
        char r0 = r3.charAt(r42);
        if (r0 != '\"') goto L9;
    L14:
        this.titleDelimiter = r0;
    L16:
        if (this.titleDelimiter == 0) goto L21;
        this.state = State.TITLE;
        this.title = new StringBuilder();
        int r43 = r42 + 1;
        if (r43 != r3.length()) goto L20;
        this.title.append('\n');
    L20:
        return r43;
    L21:
        finishReference();
        this.state = State.START_DEFINITION;
        return r42;
    L9:
        if (r0 == '\'') goto L14;
        if (r0 != '(') goto L16;
        this.titleDelimiter = ')';
        goto L16
    }

    private int title(CharSequence r4, int r5) {
        int r0 = LinkScanner.scanLinkTitleContent(r4, r5, this.titleDelimiter);
        if (r0 != (-1)) goto L5;
        return -1;
    L5:
        this.title.append(r4.subSequence(r5, r0));
        if (r0 < r4.length()) goto L9;
        this.title.append('\n');
        return r0;
    L9:
        int r02 = Parsing.skipSpaceTab(r4, r0 + 1, r4.length());
        if (r02 == r4.length()) goto L12;
        return -1;
    L12:
        this.referenceValid = true;
        finishReference();
        this.paragraph.setLength(0);
        this.state = State.START_DEFINITION;
        return r02;
    }

    public List<LinkReferenceDefinition> getDefinitions() {
        finishReference();
        return this.definitions;
    }

    public CharSequence getParagraphContent() {
        return this.paragraph;
    }

    public void parse(CharSequence r4) {
        if (this.paragraph.length() == 0) goto L5;
        this.paragraph.append('\n');
    L5:
        this.paragraph.append(r4);
        int r0 = 0;
    L7:
        if (r0 >= r4.length()) goto L24;
        switch(AnonymousClass1.$SwitchMap$org$commonmark$internal$LinkReferenceDefinitionParser$State[this.state.ordinal()]) {
            case 1: goto L25;
            case 2: goto L15;
            case 3: goto L14;
            case 4: goto L13;
            case 5: goto L12;
            case 6: goto L11;
            default: goto L17;
        };
    L11:
        r0 = title(r4, r0);
        goto L17
    L12:
        r0 = startTitle(r4, r0);
        goto L17
    L13:
        r0 = destination(r4, r0);
        goto L17
    L14:
        r0 = label(r4, r0);
        goto L17
    L15:
        r0 = startDefinition(r4, r0);
    L17:
        if (r0 != (-1)) goto L7;
        this.state = State.PARAGRAPH;
        return;
    L25:
        return;
    }
}
