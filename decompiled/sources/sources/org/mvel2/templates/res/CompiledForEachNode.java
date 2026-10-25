package org.mvel2.templates.res;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.ast.Sign$$ExternalSyntheticBUOutline0;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.TemplateRuntimeError;
import org.mvel2.templates.util.ArrayIterator;
import org.mvel2.templates.util.CountIterator;
import org.mvel2.templates.util.TemplateOutputStream;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class CompiledForEachNode extends Node {
    private Serializable cSepExpr;
    private Serializable[] ce;
    private ParserContext context;
    private String[] item;
    public Node nestedNode;
    private char[] sepExpr;

    public CompiledForEachNode(int r1, String r2, char[] r3, int r4, int r5, ParserContext r6) {
        super(r1, r2, r3, r4, r5);
        this.context = r6;
        configure();
    }

    private void configure() {
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        int r2 = this.cStart;
        int r3 = r2;
    L3:
        int r4 = this.cEnd;
        if (r2 >= r4) goto L29;
        char[] r42 = this.contents;
        char r5 = r42[r2];
        if (r5 != '\"') goto L8;
    L27:
        r2 = ParseTools.balancedCapture(r42, r2, r5);
    L28:
        r2 = r2 + 1;
        goto L3
    L8:
        if (r5 != ',') goto L10;
        int r43 = r1.size();
        int r52 = r0.size() - 1;
        char[] r6 = this.contents;
        if (r43 != r52) goto L25;
        r1.add(ParseTools.createStringTrimmed(r6, r3, r2 - r3));
    L21:
        r3 = r2 + 1;
        goto L28
    L25:
        Sign$$ExternalSyntheticBUOutline0.m("unexpected character ',' in foreach tag", r6, this.cStart + r2);
        return;
    L10:
        if (r5 != ':') goto L12;
        r0.add(ParseTools.createStringTrimmed(r42, r3, r2 - r3));
        goto L21
    L12:
        if (r5 == '[') goto L27;
        if (r5 == '{') goto L27;
        if (r5 == '\'') goto L27;
        if (r5 == '(') goto L27;
    L29:
        if (r3 >= r4) goto L35;
        int r22 = r1.size();
        int r44 = r0.size() - 1;
        char[] r53 = this.contents;
        if (r22 != r44) goto L33;
        r1.add(ParseTools.createStringTrimmed(r53, r3, this.cEnd - r3));
        goto L35
    L33:
        Sign$$ExternalSyntheticBUOutline0.m("expected character ':' in foreach tag", r53, this.cEnd);
        return;
    L35:
        this.item = new String[r0.size()];
        int r23 = r0.size();
        int r32 = 0;
        int r45 = 0;
        int r54 = 0;
    L36:
        if (r54 >= r23) goto L38;
        Object r62 = r0.get(r54);
        r54 = r54 + 1;
        this.item[r45] = (String) r62;
        r45 = r45 + 1;
        goto L36
    L38:
        int r02 = r1.size();
        String[] r24 = new String[r02];
        this.ce = new Serializable[r02];
        int r03 = r1.size();
        int r46 = 0;
    L39:
        if (r46 >= r03) goto L41;
        Object r55 = r1.get(r46);
        r46 = r46 + 1;
        String r56 = (String) r55;
        Serializable[] r63 = this.ce;
        r24[r32] = r56;
        r63[r32] = MVEL.compileExpression(r56, this.context);
        r32 = r32 + 1;
        goto L39
    }

    @Override // org.mvel2.templates.res.Node
    public boolean demarcate(Node r1, char[] r2) {
        this.nestedNode = this.next;
        this.next = this.terminus;
        char[] r12 = r1.getContents();
        this.sepExpr = r12;
        if (r12.length != 0) goto L5;
        this.sepExpr = null;
        return false;
    L5:
        this.cSepExpr = MVEL.compileExpression(r12, this.context);
        return false;
    }

    @Override // org.mvel2.templates.res.Node
    public Object eval(TemplateRuntime r10, TemplateOutputStream r11, Object r12, VariableResolverFactory r13) {
        int r0 = this.item.length;
        Iterator[] r1 = new Iterator[r0];
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L16;
        Object r4 = MVEL.executeExpression(this.ce[r3], r12, r13);
        if ((r4 instanceof Iterable) == false) goto L8;
        r1[r3] = ((Iterable) r4).iterator();
    L13:
        r3 = r3 + 1;
        goto L3
    L8:
        if ((r4 instanceof Object[]) == false) goto L11;
        r1[r3] = new ArrayIterator((Object[]) r4);
        goto L13
    L11:
        if ((r4 instanceof Integer) == false) goto L15;
        r1[r3] = new CountIterator(((Integer) r4).intValue());
        goto L13
    L15:
        throw new TemplateRuntimeError("cannot iterate object type: ".concat(r4.getClass().getName()));
    L16:
        HashMap r32 = new HashMap();
        MapVariableResolverFactory r42 = new MapVariableResolverFactory(r32, r13);
        int r5 = r0;
    L17:
        int r6 = 0;
    L18:
        if (r6 >= r0) goto L24;
        boolean r7 = r1[r6].hasNext();
        String[] r8 = this.item;
        if (r7 == true) goto L22;
        r5 = r5 - 1;
        r32.put(r8[r6], _UrlKt.FRAGMENT_ENCODE_SET);
    L23:
        r6 = r6 + 1;
        goto L18
    L22:
        r32.put(r8[r6], r1[r6].next());
        goto L23
    L24:
        if (r5 == 0) goto L33;
        this.nestedNode.eval(r10, r11, r12, r42);
        if (this.sepExpr == null) goto L17;
        int r62 = 0;
    L28:
        if (r62 >= r0) goto L17;
        if (r1[r62].hasNext() == true) goto L31;
        r62 = r62 + 1;
        goto L28
    L31:
        r11.append(String.valueOf(MVEL.executeExpression(this.cSepExpr, r12, r13)));
        goto L17
    L33:
        Node r9 = this.next;
        if (r9 != null) goto L36;
        return null;
    L36:
        return r9.eval(r10, r11, r12, r13);
    }

    public Node getNestedNode() {
        return this.nestedNode;
    }

    public void setNestedNode(Node r1) {
        this.nestedNode = r1;
    }
}
