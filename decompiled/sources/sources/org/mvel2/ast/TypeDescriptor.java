package org.mvel2.ast;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import org.mvel2.ParserContext;
import org.mvel2.compiler.AbstractParser;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ArrayTools;
import org.mvel2.util.ParseTools;
import org.mvel2.util.ReflectionUtil;

/* loaded from: classes5.dex */
public class TypeDescriptor implements Serializable {
    private ArraySize[] arraySize;
    private String className;
    private ExecutableStatement[] compiledArraySize;
    int endRange;
    private char[] expr;
    private int offset;
    private int start;

    public TypeDescriptor(char[] r1, int r2, int r3, int r4) {
        this.expr = r1;
        this.start = r2;
        this.offset = r3;
        updateClassName(r1, r2, r3, r4);
    }

    public static Class getClassReference(Class r2, TypeDescriptor r3, VariableResolverFactory r4, ParserContext r5) {
        return ParseTools.findClass(r4, ParseTools.repeatChar('[', r3.arraySize.length) + "L" + r2.getName() + ";", r5);
    }

    public static Class getClassReference(ParserContext r2, Class r3, TypeDescriptor r4) {
        if (r4.isArray() == true) goto L5;
        return r3;
    L5:
        if (r3.isPrimitive() == false) goto L9;
        return ReflectionUtil.toPrimitiveArrayType(r3);
    L9:
        return ParseTools.findClass(null, ParseTools.repeatChar('[', r4.arraySize.length) + "L" + r3.getName() + ";", r2);
    }

    public static Class getClassReference(ParserContext r6, TypeDescriptor r7) {
        if (r6 != null) goto L5;
    L15:
        if (r6 == null) goto L17;
    L27:
        Class r4 = ParseTools.createClass(r7.getClassName(), r6);
        if (r7.isArray() == true) goto L30;
        return r4;
    L30:
        if (r4.isPrimitive() == false) goto L34;
        return ReflectionUtil.toPrimitiveArrayType(r4);
    L34:
        return ParseTools.findClass(null, ParseTools.repeatChar('[', r7.arraySize.length) + "L" + r4.getName() + ";", r6);
    L17:
        if (hasContextFreeImport(r7.className) == false) goto L27;
        Class r42 = getContextFreeImport(r7.className);
        if (r7.isArray() == true) goto L21;
        return r42;
    L21:
        if (r42.isPrimitive() == false) goto L25;
        return ReflectionUtil.toPrimitiveArrayType(r42);
    L25:
        return ParseTools.findClass(null, ParseTools.repeatChar('[', r7.arraySize.length) + "L" + r42.getName() + ";", r6);
    L5:
        if (r6.hasImport(r7.className) == false) goto L15;
        Class r43 = r6.getImport(r7.className);
        if (r7.isArray() == true) goto L9;
        return r43;
    L9:
        if (r43.isPrimitive() == false) goto L13;
        return ReflectionUtil.toPrimitiveArrayType(r43);
    L13:
        return ParseTools.findClass(null, ParseTools.repeatChar('[', r7.arraySize.length) + "L" + r43.getName() + ";", r6);
    }

    public static Class getContextFreeImport(String r1) {
        return (Class) AbstractParser.LITERALS.get(r1);
    }

    public static boolean hasContextFreeImport(String r1) {
        if (AbstractParser.LITERALS.containsKey(r1) == true) goto L5;
        return false;
    L5:
        if ((AbstractParser.LITERALS.get(r1) instanceof Class) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public int getArrayLength() {
        return this.arraySize.length;
    }

    public ArraySize[] getArraySize() {
        return this.arraySize;
    }

    public String getClassName() {
        return this.className;
    }

    public Class<?> getClassReference() {
        return getClassReference(null, this);
    }

    public Class<?> getClassReference(ParserContext r1) {
        return getClassReference(r1, this);
    }

    public ExecutableStatement[] getCompiledArraySize() {
        return this.compiledArraySize;
    }

    public int getEndRange() {
        return this.endRange;
    }

    public char[] getExpr() {
        return this.expr;
    }

    public int getOffset() {
        return this.offset;
    }

    public int getStart() {
        return this.start;
    }

    public boolean isArray() {
        if (this.arraySize == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isClass() {
        String r0 = this.className;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.length() == 0) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isUndimensionedArray() {
        ArraySize[] r4 = this.arraySize;
        if (r4 == null) goto L11;
        int r1 = r4.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L11;
        if (r4[r2].value.length == 0) goto L8;
        r2 = r2 + 1;
        goto L5
    L8:
        return true;
    L11:
        return false;
    }

    public void setClassName(String r1) {
        this.className = r1;
    }

    public void setEndRange(int r1) {
        this.endRange = r1;
    }

    public void updateClassName(char[] r5, int r6, int r7, int r8) {
        this.expr = r5;
        if (r7 != 0) goto L5;
        return;
    L5:
        if (ParseTools.isIdentifierPart(r5[r6]) == true) goto L7;
        return;
    L7:
        if (Character.isDigit(r5[r6]) == true) goto L55;
        int r0 = ArrayTools.findFirst('(', r6, r7, r5);
        this.endRange = r0;
        if (r0 != (-1)) goto L42;
        int r2 = ArrayTools.findFirst('[', r6, r7, r5);
        this.endRange = r2;
        if (r2 == (-1)) goto L40;
        this.className = new String(r5, r6, r2 - r6).trim();
        LinkedList r1 = new LinkedList();
        int r62 = r6 + r7;
    L15:
        if (this.endRange >= r62) goto L30;
    L16:
        int r72 = this.endRange;
        if (r72 >= r62) goto L21;
        if (ParseTools.isWhitespace(r5[r72]) == false) goto L21;
        this.endRange++;
    L21:
        int r73 = this.endRange;
        if (r73 == r62) goto L30;
        char r22 = r5[r73];
        if (r22 == '{') goto L30;
        if (r22 != '[') goto L28;
        int r74 = ParseTools.balancedCapture(r5, r73, r62, '[');
        int r23 = this.endRange + 1;
        this.endRange = r23;
        r1.add(ParseTools.subset(r5, r23, r74 - r23));
        this.endRange = r74 + 1;
        goto L15
    L28:
        Sign$$ExternalSyntheticBUOutline0.m("unexpected token in constructor", r5, r73);
        return;
    L30:
        Iterator r52 = r1.iterator();
        this.arraySize = new ArraySize[r1.size()];
        int r63 = 0;
        int r75 = 0;
    L31:
        ArraySize[] r02 = this.arraySize;
        if (r75 >= r02.length) goto L35;
        r02[r75] = new ArraySize((char[]) r52.next());
        r75 = r75 + 1;
        goto L31
    L35:
        if ((r8 & 16) == 0) goto L54;
        this.compiledArraySize = new ExecutableStatement[r02.length];
    L37:
        ExecutableStatement[] r53 = this.compiledArraySize;
        if (r63 >= r53.length) goto L56;
        r53[r63] = (ExecutableStatement) ParseTools.subCompileExpression(this.arraySize[r63].value);
        r63 = r63 + 1;
        goto L37
    L56:
        return;
    L54:
        return;
    L40:
        this.className = new String(r5, r6, r7).trim();
        return;
    L42:
        this.className = new String(r5, r6, r0 - r6).trim();
        return;
    }
}
