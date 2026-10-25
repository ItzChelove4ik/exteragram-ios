package org.mvel2.debug;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.mvel2.ast.ASTNode;
import org.mvel2.ast.BinaryOperation;
import org.mvel2.ast.NestedStatement;
import org.mvel2.ast.Substatement;
import org.mvel2.compiler.CompiledExpression;
import org.mvel2.compiler.ExecutableAccessor;
import org.mvel2.compiler.ExecutableLiteral;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ASTLinkedList;

/* loaded from: classes5.dex */
public class DebugTools {

    /* renamed from: org.mvel2.debug.DebugTools$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class DecompileContext {
        public int node;

        private DecompileContext() {
            this.node = 0;
        }

        public /* synthetic */ DecompileContext(AnonymousClass1 r1) {
            this();
        }
    }

    public DebugTools() {
    }

    public static String decompile(Serializable r2) {
        if ((r2 instanceof CompiledExpression) == false) goto L7;
        return decompile((CompiledExpression) r2);
    L7:
        if ((r2 instanceof ExecutableAccessor) == false) goto L11;
        return "CANNOT DECOMPILE OPTIMIZED STATEMENT (Run with -Dmvel.optimizer=false)";
    L11:
        if ((r2 instanceof ExecutableLiteral) == false) goto L15;
        return "LITERAL: " + ((ExecutableLiteral) r2).getValue(null, null);
    L15:
        return "NOT A KNOWN PAYLOAD: ".concat(r2.getClass().getName());
    }

    public static String decompile(CompiledExpression r2) {
        return decompile(r2, false, new DecompileContext(null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static String decompile(CompiledExpression r8, boolean r9, DecompileContext r10) {
        ASTLinkedList r0 = new ASTLinkedList(r8.getFirstNode());
        StringBuffer r82 = new StringBuffer();
        if (r9 == true) goto L6;
        r82.append("Expression Decompile\n-------------\n");
    L6:
        if (r0.hasMoreNodes() == false) goto L36;
        r82.append("(");
        int r92 = r10.node;
        r10.node = r92 + 1;
        r82.append(r92);
        r82.append(") ");
        ASTNode r93 = r0.nextNode();
        if ((r93 instanceof NestedStatement) == false) goto L13;
        if ((((NestedStatement) r93).getNestedStatement() instanceof CompiledExpression) == false) goto L13;
        r82.append("NEST [" + r93.getClass().getSimpleName() + "]: { " + r93.getName() + " }\n");
        r82.append(decompile((CompiledExpression) ((NestedStatement) r93).getNestedStatement(), true, r10));
    L13:
        if ((r93 instanceof Substatement) == false) goto L18;
        Substatement r1 = (Substatement) r93;
        if ((r1.getStatement() instanceof CompiledExpression) == false) goto L18;
        r82.append("NEST [" + r93.getClass().getSimpleName() + "]: { " + r93.getName() + " }\n");
        r82.append(decompile((CompiledExpression) r1.getStatement(), true, r10));
    L35:
        r82.append("\n");
    L18:
        if (r93.isDebuggingSymbol() == false) goto L21;
        r82.append("DEBUG_SYMBOL :: " + r93.toString());
        goto L35
    L21:
        if (r93.isLiteral() == false) goto L24;
        r82.append("LITERAL :: ");
        r82.append(r93.getLiteralValue());
        r82.append("'");
        goto L35
    L24:
        if (r93.isOperator() == false) goto L29;
        r82.append("OPERATOR [");
        r82.append(getOperatorName(r93.getOperator().intValue()));
        r82.append("]: ");
        r82.append(r93.getName());
        if (r93.isOperator(37) == false) goto L35;
        r82.append("\n");
        goto L35
    L29:
        if (r93.isIdentifier() == false) goto L32;
        r82.append("REFERENCE :: ");
        r82.append(r93.getClass().getSimpleName());
        r82.append(":");
        r82.append(r93.getName());
        goto L35
    L32:
        if ((r93 instanceof BinaryOperation) == false) goto L34;
        BinaryOperation r94 = (BinaryOperation) r93;
        r82.append("OPERATION [" + getOperatorName(r94.getOperation()) + "] {");
        r82.append(r94.getLeft().getName());
        r82.append("} {");
        r82.append(r94.getRight().getName());
        r82.append("}");
        goto L35
    L34:
        r82.append("NODE [" + r93.getClass().getSimpleName() + "] :: " + r93.getName());
        goto L35
    L36:
        r82.append("==END==");
        return r82.toString();
    }

    public static Class determineType(String r2, CompiledExpression r3) {
        ASTLinkedList r0 = new ASTLinkedList(r3.getFirstNode());
    L4:
        if (r0.hasMoreNodes() == false) goto L11;
        ASTNode r32 = r0.nextNode();
        if (r2.equals(r32.getName()) == false) goto L4;
        if (r32.isAssignment() == false) goto L4;
        return r32.getEgressType();
    L11:
        return null;
    }

    public static Map<String, VariableResolver> getAllVariableResolvers(VariableResolverFactory r4) {
        HashMap r0 = new HashMap();
    L3:
        Iterator<String> r1 = r4.getKnownVariables().iterator();
    L5:
        if (r1.hasNext() == false) goto L7;
        String r2 = r1.next();
        r0.put(r2, r4.getVariableResolver(r2));
        goto L5
    L7:
        r4 = r4.getNextFactory();
        if (r4 != null) goto L3;
        return r0;
    }

    public static String getOperatorName(int r0) {
        switch(r0) {
            case 0: goto L111;
            case 1: goto L109;
            case 2: goto L107;
            case 3: goto L105;
            case 4: goto L103;
            case 5: goto L101;
            case 6: goto L99;
            case 7: goto L97;
            case 8: goto L95;
            case 9: goto L93;
            case 10: goto L91;
            case 11: goto L89;
            case 12: goto L87;
            default: goto L3;
        };
    L3:
        switch(r0) {
            case 14: goto L85;
            case 15: goto L83;
            case 16: goto L81;
            case 17: goto L79;
            case 18: goto L77;
            case 19: goto L75;
            case 20: goto L73;
            case 21: goto L71;
            case 22: goto L69;
            case 23: goto L67;
            case 24: goto L65;
            case 25: goto L63;
            case 26: goto L61;
            case 27: goto L59;
            case 28: goto L57;
            case 29: goto L55;
            case 30: goto L53;
            case 31: goto L51;
            case 32: goto L49;
            case 33: goto L47;
            case 34: goto L45;
            case 35: goto L43;
            case 36: goto L41;
            case 37: goto L39;
            case 38: goto L37;
            case 39: goto L35;
            case 40: goto L33;
            case 41: goto L31;
            default: goto L4;
        };
    L4:
        switch(r0) {
            case 43: goto L29;
            case 44: goto L27;
            case 45: goto L25;
            default: goto L5;
        };
    L5:
        switch(r0) {
            case 50: goto L23;
            case 51: goto L21;
            case 52: goto L19;
            case 53: goto L17;
            case 54: goto L15;
            default: goto L6;
        };
    L6:
        switch(r0) {
            case 99: goto L13;
            case 100: goto L11;
            case 101: goto L9;
            default: goto L7;
        };
    L7:
        return "UNKNOWN_OPERATOR";
    L9:
        return "STACKLANG";
    L11:
        return "FUNCTION";
    L13:
        return "RETURN";
    L15:
        return "ASSIGN_STR_APPEND";
    L17:
        return "ASSIGN_SUB";
    L19:
        return "ASSIGN_ADD";
    L21:
        return "DECREMENT";
    L23:
        return "INCREMENT";
    L25:
        return "DO";
    L27:
        return "SWITCH";
    L29:
        return "FOR";
    L31:
        return "WHILE";
    L33:
        return "ELSE";
    L35:
        return "IF";
    L37:
        return "FOREACH";
    L39:
        return "END_OF_STATEMENT";
    L41:
        return "CONVERTABLE_TO";
    L43:
        return "PROJECT";
    L45:
        return "NEW_OBJECT";
    L47:
        return "DECREMENT_ASSIGN";
    L49:
        return "INCREMENT_ASSIGN";
    L51:
        return "ASSIGN";
    L53:
        return "TERNARY_ELSE";
    L55:
        return "TERNARY_IF";
    L57:
        return "SIMILARITY";
    L59:
        return "SOUNDEX";
    L61:
        return "CONTAINS";
    L63:
        return "INSTANCEOF";
    L65:
        return "REGEX";
    L67:
        return "CHAINED_OR";
    L69:
        return "OR";
    L71:
        return "AND";
    L73:
        return "STR_APPEND";
    L75:
        return "NOT_EQUAL";
    L77:
        return "EQUAL";
    L79:
        return "GREATER_THAN_OR_EQUAL";
    L81:
        return "LESS_THAN_OR_EQUAL";
    L83:
        return "GREATHER_THAN";
    L85:
        return "LESS_THAN";
    L87:
        return "BIT_UNSIGNED_SHIFT_LEFT";
    L89:
        return "BIT_UNSIGNED_SHIFT_RIGHT";
    L91:
        return "BIT_SHIFT_LEFT";
    L93:
        return "BIT_SHIFT_RIGHT";
    L95:
        return "BIT_XOR";
    L97:
        return "BIT_OR";
    L99:
        return "BIT_AND";
    L101:
        return "POWER_OF";
    L103:
        return "MODULUS";
    L105:
        return "DIVIDE";
    L107:
        return "MULTIPLY";
    L109:
        return "SUBTRACT";
    L111:
        return "ADD";
    }

    public static String getOperatorSymbol(int r3) {
        switch(r3) {
            case 0: goto L105;
            case 1: goto L103;
            case 2: goto L101;
            case 3: goto L99;
            case 4: goto L97;
            case 5: goto L95;
            case 6: goto L93;
            case 7: goto L91;
            case 8: goto L89;
            case 9: goto L87;
            case 10: goto L85;
            case 11: goto L83;
            case 12: goto L81;
            default: goto L5;
        };
    L81:
        return "<<<";
    L83:
        return ">>>";
    L85:
        return "<<";
    L87:
        return ">>";
    L89:
        return "^";
    L91:
        return "|";
    L93:
        return "&";
    L95:
        return "**";
    L97:
        return "%";
    L99:
        return "/";
    L101:
        return "*";
    L103:
        return "-";
    L105:
        return "+";
    L5:
        switch(r3) {
            case 14: goto L79;
            case 15: goto L77;
            case 16: goto L75;
            case 17: goto L73;
            case 18: goto L71;
            case 19: goto L69;
            case 20: goto L68;
            case 21: goto L66;
            case 22: goto L64;
            case 23: goto L62;
            case 24: goto L60;
            case 25: goto L58;
            case 26: goto L56;
            case 27: goto L54;
            case 28: goto L52;
            case 29: goto L50;
            case 30: goto L48;
            case 31: goto L47;
            case 32: goto L46;
            case 33: goto L46;
            case 34: goto L44;
            case 35: goto L42;
            case 36: goto L40;
            case 37: goto L38;
            case 38: goto L36;
            case 39: goto L34;
            case 40: goto L32;
            case 41: goto L30;
            default: goto L6;
        };
    L6:
        switch(r3) {
            case 43: goto L28;
            case 44: goto L26;
            case 45: goto L24;
            default: goto L8;
        };
    L24:
        return "do";
    L26:
        return "SWITCH";
    L28:
        return "for";
    L8:
        switch(r3) {
            case 50: goto L23;
            case 51: goto L21;
            case 52: goto L20;
            case 53: goto L19;
            case 54: goto L18;
            default: goto L9;
        };
    L9:
        switch(r3) {
            case 99: goto L16;
            case 100: goto L14;
            case 101: goto L12;
            default: goto L10;
        };
    L10:
        return "UNKNOWN_OPERATOR";
    L12:
        return "stacklang";
    L14:
        return "function";
    L16:
        return "RETURN";
    L18:
        return "=+";
    L19:
        return "=";
    L20:
        return "=+";
    L21:
        return "--";
    L23:
        return "++";
    L30:
        return "while";
    L32:
        return "else";
    L34:
        return "if";
    L36:
        return "foreach";
    L38:
        return ";";
    L40:
        return "convertable_to";
    L42:
        return "PROJECT";
    L44:
        return "new";
    L46:
        return "++";
    L47:
        return "=";
    L48:
        return "TERNARY_ELSE";
    L50:
        return "TERNARY_IF";
    L52:
        return "SIMILARITY";
    L54:
        return "SOUNDEX";
    L56:
        return "contains";
    L58:
        return "instanceof";
    L60:
        return "REGEX";
    L62:
        return PluginsConstants.HookFilterTypes.OR;
    L64:
        return "||";
    L66:
        return "&&";
    L68:
        return "+";
    L69:
        return "!=";
    L71:
        return "==";
    L73:
        return ">=";
    L75:
        return "<=";
    L77:
        return ">";
    L79:
        return "<";
    }
}
