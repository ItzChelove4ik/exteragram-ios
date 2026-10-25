package org.scilab.forge.jlatexmath;

import java.util.HashMap;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.noties.jlatexmath.awt.Color;

/* loaded from: classes5.dex */
public class TeXFormulaParser {
    private static final String ARG_OBJ_ATTR = "formula";
    private static final String ARG_VAL_ATTR = "value";
    private static final int COMMAND = 0;
    private static final String RETURN_EL = "Return";
    private static final int TEXFORMULA = 1;
    private static Map<String, Class<?>> classMappings;
    private final Map<String, ActionParser> actionParsers;
    private final Map<String, ArgumentValueParser> argValueParsers;
    private final Element formula;
    private final String formulaName;
    private Object result;
    private final Map<String, MacroInfo> tempCommands;
    private final Map<String, TeXFormula> tempFormulas;
    private int type;

    public interface ActionParser {
        void parse(Element r1);
    }

    public interface ArgumentValueParser {
        Object parseValue(String r1, String r2);
    }

    public class BooleanValueParser implements ArgumentValueParser {
        final /* synthetic */ TeXFormulaParser this$0;

        public BooleanValueParser(TeXFormulaParser r1) {
            this.this$0 = r1;
        }

        @Override // org.scilab.forge.jlatexmath.TeXFormulaParser.ArgumentValueParser
        public Object parseValue(String r3, String r4) {
            TeXFormulaParser.m3856$$Nest$smcheckNullValue(r3, r4);
            if ("true".equals(r3) == false) goto L7;
            return Boolean.TRUE;
        L7:
            if ("false".equals(r3) == false) goto L11;
            return Boolean.FALSE;
        L11:
            throw new XMLResourceParseException(PredefinedTeXFormulaParser.RESOURCE_NAME, "Argument", TeXFormulaParser.ARG_VAL_ATTR, "has an invalid '" + r4 + "'-value : '" + r3 + "'!");
        }
    }

    public class CharValueParser implements ArgumentValueParser {
        final /* synthetic */ TeXFormulaParser this$0;

        public CharValueParser(TeXFormulaParser r1) {
            this.this$0 = r1;
        }

        @Override // org.scilab.forge.jlatexmath.TeXFormulaParser.ArgumentValueParser
        public Object parseValue(String r3, String r4) {
            TeXFormulaParser.m3856$$Nest$smcheckNullValue(r3, r4);
            if (r3.length() != 1) goto L7;
            return new Character(r3.charAt(0));
        L7:
            throw new XMLResourceParseException(PredefinedTeXFormulaParser.RESOURCE_NAME, "Argument", TeXFormulaParser.ARG_VAL_ATTR, "must have a value that consists of exactly 1 character!");
        }
    }

    public class ColorConstantValueParser implements ArgumentValueParser {
        final /* synthetic */ TeXFormulaParser this$0;

        public ColorConstantValueParser(TeXFormulaParser r1) {
            this.this$0 = r1;
        }

        @Override // org.scilab.forge.jlatexmath.TeXFormulaParser.ArgumentValueParser
        public Object parseValue(String r7, String r8) {
            TeXFormulaParser.m3856$$Nest$smcheckNullValue(r7, r8);
            return Color.class.getDeclaredField(r7).get(null);
        L5:
            e = move-exception;
            throw new XMLResourceParseException(PredefinedTeXFormulaParser.RESOURCE_NAME, "Argument", TeXFormulaParser.ARG_VAL_ATTR, "has an unknown color constant name as value : '" + r7 + "'!", e);
        }
    }

    public class CreateCommandParser implements ActionParser {
        final /* synthetic */ TeXFormulaParser this$0;

        public CreateCommandParser(TeXFormulaParser r1) {
            this.this$0 = r1;
        }

        @Override // org.scilab.forge.jlatexmath.TeXFormulaParser.ActionParser
        public void parse(Element r13) {
            String r3 = TeXFormulaParser.m3858$$Nest$smgetAttrValueAndCheckIfNotNull("name", r13);
            NodeList r132 = r13.getElementsByTagName("Argument");
            Class[] r4 = TeXFormulaParser.m3857$$Nest$smgetArgumentClasses(r132);
            Object[] r133 = TeXFormulaParser.m3855$$Nest$mgetArgumentValues(this.this$0, r132);
            TeXFormulaParser.m3851$$Nest$fgettempCommands(this.this$0).put(r3, (MacroInfo) MacroInfo.class.getConstructor(r4).newInstance(r133));     // Catch: Exception -> L5 IllegalArgumentException -> L8
            return;
        L8:
            String r5 = "IllegalArgumentException:\nClassLoader to load this class (TeXFormulaParser): " + getClass().getClassLoader() + "\n";
            int r7 = r4.length;
            int r8 = 0;
            int r9 = 0;
        L9:
            if (r9 >= r7) goto L11;
            Class r10 = r4[r9];
            r5 = r5 + "Created class: " + r10 + " loaded with the ClassLoader: " + r10.getClassLoader() + "\n";
            r9 = r9 + 1;
            goto L9
        L11:
            int r42 = r133.length;
        L12:
            if (r8 >= r42) goto L15;
            r5 = r5 + "Created object: " + r133[r8] + "\n";
            r8 = r8 + 1;
            goto L12
        L15:
            throw new XMLResourceParseException("Error creating the temporary command '" + r3 + "' while constructing the predefined command '" + TeXFormulaParser.m3850$$Nest$fgetformulaName(this.this$0) + "'!\n" + r5);
        L5:
            e = move-exception;
            throw new XMLResourceParseException("Error creating the temporary command '" + r3 + "' while constructing the predefined command '" + TeXFormulaParser.m3850$$Nest$fgetformulaName(this.this$0) + "'!\n" + e.toString());
        }
    }

    public class CreateTeXFormulaParser implements ActionParser {
        final /* synthetic */ TeXFormulaParser this$0;

        public CreateTeXFormulaParser(TeXFormulaParser r1) {
            this.this$0 = r1;
        }

        @Override // org.scilab.forge.jlatexmath.TeXFormulaParser.ActionParser
        public void parse(Element r5) {
            String r0 = TeXFormulaParser.m3858$$Nest$smgetAttrValueAndCheckIfNotNull("name", r5);
            NodeList r52 = r5.getElementsByTagName("Argument");
            Class[] r1 = TeXFormulaParser.m3857$$Nest$smgetArgumentClasses(r52);
            TeXFormulaParser.m3852$$Nest$fgettempFormulas(this.this$0).put(r0, (TeXFormula) TeXFormula.class.getConstructor(r1).newInstance(TeXFormulaParser.m3855$$Nest$mgetArgumentValues(this.this$0, r52)));     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            throw new XMLResourceParseException("Error creating the temporary TeXFormula '" + r0 + "' while constructing the predefined TeXFormula '" + TeXFormulaParser.m3850$$Nest$fgetformulaName(this.this$0) + "'!\n" + e.toString());
        }
    }

    public class FloatValueParser implements ArgumentValueParser {
        final /* synthetic */ TeXFormulaParser this$0;

        public FloatValueParser(TeXFormulaParser r1) {
            this.this$0 = r1;
        }

        @Override // org.scilab.forge.jlatexmath.TeXFormulaParser.ArgumentValueParser
        public Object parseValue(String r7, String r8) {
            TeXFormulaParser.m3856$$Nest$smcheckNullValue(r7, r8);
            return new Float(Float.parseFloat(r7));
        L5:
            e = move-exception;
            throw new XMLResourceParseException(PredefinedTeXFormulaParser.RESOURCE_NAME, "Argument", TeXFormulaParser.ARG_VAL_ATTR, "has an invalid '" + r8 + "'-value : '" + r7 + "'!", e);
        }
    }

    public class IntValueParser implements ArgumentValueParser {
        final /* synthetic */ TeXFormulaParser this$0;

        public IntValueParser(TeXFormulaParser r1) {
            this.this$0 = r1;
        }

        @Override // org.scilab.forge.jlatexmath.TeXFormulaParser.ArgumentValueParser
        public Object parseValue(String r7, String r8) {
            TeXFormulaParser.m3856$$Nest$smcheckNullValue(r7, r8);
            return new Float(Integer.parseInt(r7));
        L5:
            e = move-exception;
            throw new XMLResourceParseException(PredefinedTeXFormulaParser.RESOURCE_NAME, "Argument", TeXFormulaParser.ARG_VAL_ATTR, "has an invalid '" + r8 + "'-value : '" + r7 + "'!", e);
        }
    }

    public class MethodInvocationParser implements ActionParser {
        final /* synthetic */ TeXFormulaParser this$0;

        public MethodInvocationParser(TeXFormulaParser r1) {
            this.this$0 = r1;
        }

        @Override // org.scilab.forge.jlatexmath.TeXFormulaParser.ActionParser
        public void parse(Element r6) {
            String r0 = TeXFormulaParser.m3858$$Nest$smgetAttrValueAndCheckIfNotNull("name", r6);
            String r2 = TeXFormulaParser.m3858$$Nest$smgetAttrValueAndCheckIfNotNull(TeXFormulaParser.ARG_OBJ_ATTR, r6);
            Object r3 = TeXFormulaParser.m3852$$Nest$fgettempFormulas(this.this$0).get(r2);
            if (r3 == null) goto L10;
            NodeList r62 = r6.getElementsByTagName("Argument");
            Class[] r1 = TeXFormulaParser.m3857$$Nest$smgetArgumentClasses(r62);
            TeXFormula.class.getMethod(r0, r1).invoke((TeXFormula) r3, TeXFormulaParser.m3855$$Nest$mgetArgumentValues(this.this$0, r62));     // Catch: Exception -> L7
            return;
        L7:
            e = move-exception;
            throw new XMLResourceParseException("Error invoking the method '" + r0 + "' on the temporary TeXFormula '" + r2 + "' while constructing the predefined TeXFormula '" + TeXFormulaParser.m3850$$Nest$fgetformulaName(this.this$0) + "'!\n" + e.toString());
        L10:
            TeXSymbolParser$$ExternalSyntheticBUOutline0.m("has an unknown temporary TeXFormula name as value : '", r2, PredefinedTeXFormulaParser.RESOURCE_NAME, "Argument", TeXFormulaParser.ARG_OBJ_ATTR);
        }
    }

    public class ReturnParser implements ActionParser {
        final /* synthetic */ TeXFormulaParser this$0;

        public ReturnParser(TeXFormulaParser r1) {
            this.this$0 = r1;
        }

        @Override // org.scilab.forge.jlatexmath.TeXFormulaParser.ActionParser
        public void parse(Element r5) {
            String r52 = TeXFormulaParser.m3858$$Nest$smgetAttrValueAndCheckIfNotNull("name", r5);
            int r1 = TeXFormulaParser.m3853$$Nest$fgettype(this.this$0);
            TeXFormulaParser r2 = this.this$0;
            if (r1 != 0) goto L6;
            Map r12 = TeXFormulaParser.m3851$$Nest$fgettempCommands(r2);
        L5:
            Object r13 = r12.get(r52);
            if (r13 == null) goto L11;
            TeXFormulaParser.m3854$$Nest$fputresult(this.this$0, r13);
            return;
        L11:
            throw new XMLResourceParseException(PredefinedTeXFormulaParser.RESOURCE_NAME, TeXFormulaParser.RETURN_EL, "name", "contains an unknown temporary TeXFormula variable name '" + r52 + "' for the predefined TeXFormula '" + TeXFormulaParser.m3850$$Nest$fgetformulaName(this.this$0) + "'!");
        L6:
            r12 = TeXFormulaParser.m3852$$Nest$fgettempFormulas(r2);
            goto L5
        }
    }

    public class StringValueParser implements ArgumentValueParser {
        final /* synthetic */ TeXFormulaParser this$0;

        public StringValueParser(TeXFormulaParser r1) {
            this.this$0 = r1;
        }

        @Override // org.scilab.forge.jlatexmath.TeXFormulaParser.ArgumentValueParser
        public Object parseValue(String r1, String r2) {
            return r1;
        }
    }

    public class TeXConstantsValueParser implements ArgumentValueParser {
        final /* synthetic */ TeXFormulaParser this$0;

        public TeXConstantsValueParser(TeXFormulaParser r1) {
            this.this$0 = r1;
        }

        @Override // org.scilab.forge.jlatexmath.TeXFormulaParser.ArgumentValueParser
        public Object parseValue(String r7, String r8) {
            TeXFormulaParser.m3856$$Nest$smcheckNullValue(r7, r8);
            return Integer.valueOf(TeXConstants.class.getDeclaredField(r7).getInt(null));
        L5:
            e = move-exception;
            throw new XMLResourceParseException(PredefinedTeXFormulaParser.RESOURCE_NAME, "Argument", TeXFormulaParser.ARG_VAL_ATTR, "has an unknown constant name as value : '" + r7 + "'!", e);
        }
    }

    public class TeXFormulaValueParser implements ArgumentValueParser {
        final /* synthetic */ TeXFormulaParser this$0;

        public TeXFormulaValueParser(TeXFormulaParser r1) {
            this.this$0 = r1;
        }

        @Override // org.scilab.forge.jlatexmath.TeXFormulaParser.ArgumentValueParser
        public Object parseValue(String r4, String r5) {
            if (r4 != null) goto L5;
            return null;
        L5:
            Object r3 = TeXFormulaParser.m3852$$Nest$fgettempFormulas(this.this$0).get(r4);
            if (r3 != null) goto L8;
            TeXSymbolParser$$ExternalSyntheticBUOutline0.m("has an unknown temporary TeXFormula name as value : '", r4, PredefinedTeXFormulaParser.RESOURCE_NAME, "Argument", TeXFormulaParser.ARG_VAL_ATTR);
            return null;
        L8:
            return (TeXFormula) r3;
        }
    }

    /* renamed from: -$$Nest$fgetformulaName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m3850$$Nest$fgetformulaName(TeXFormulaParser r0) {
        return r0.formulaName;
    }

    /* renamed from: -$$Nest$fgettempCommands, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Map m3851$$Nest$fgettempCommands(TeXFormulaParser r0) {
        return r0.tempCommands;
    }

    /* renamed from: -$$Nest$fgettempFormulas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Map m3852$$Nest$fgettempFormulas(TeXFormulaParser r0) {
        return r0.tempFormulas;
    }

    /* renamed from: -$$Nest$fgettype, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m3853$$Nest$fgettype(TeXFormulaParser r0) {
        return r0.type;
    }

    /* renamed from: -$$Nest$fputresult, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3854$$Nest$fputresult(TeXFormulaParser r0, Object r1) {
        r0.result = r1;
    }

    /* renamed from: -$$Nest$mgetArgumentValues, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Object[] m3855$$Nest$mgetArgumentValues(TeXFormulaParser r0, NodeList r1) {
        return r0.getArgumentValues(r1);
    }

    /* renamed from: -$$Nest$smcheckNullValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3856$$Nest$smcheckNullValue(String r0, String r1) {
        checkNullValue(r0, r1);
    }

    /* renamed from: -$$Nest$smgetArgumentClasses, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Class[] m3857$$Nest$smgetArgumentClasses(NodeList r0) {
        return getArgumentClasses(r0);
    }

    /* renamed from: -$$Nest$smgetAttrValueAndCheckIfNotNull, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m3858$$Nest$smgetAttrValueAndCheckIfNotNull(String r0, Element r1) {
        return getAttrValueAndCheckIfNotNull(r0, r1);
    }

    static {
        HashMap r0 = new HashMap();
        classMappings = r0;
        Class<?> r2 = Integer.TYPE;
        r0.put("TeXConstants", r2);
        classMappings.put("TeXFormula", TeXFormula.class);
        classMappings.put("String", String.class);
        classMappings.put("float", Float.TYPE);
        classMappings.put("int", r2);
        classMappings.put("boolean", Boolean.TYPE);
        classMappings.put("char", Character.TYPE);
        classMappings.put("ColorConstant", Color.class);
    }

    public TeXFormulaParser(String r4, Element r5, String r6) {
        HashMap r0 = new HashMap();
        this.argValueParsers = r0;
        HashMap r1 = new HashMap();
        this.actionParsers = r1;
        this.tempFormulas = new HashMap();
        this.tempCommands = new HashMap();
        this.result = new Object();
        this.formulaName = r4;
        this.formula = r5;
        this.type = !"Command".equals(r6) ? 1 : 0;
        if ("Command".equals(r6) == false) goto L5;
        r1.put("CreateCommand", new CreateCommandParser(this));
    L6:
        r1.put("MethodInvocation", new MethodInvocationParser(this));
        r1.put(RETURN_EL, new ReturnParser(this));
        r0.put("TeXConstants", new TeXConstantsValueParser(this));
        r0.put("TeXFormula", new TeXFormulaValueParser(this));
        r0.put("String", new StringValueParser(this));
        r0.put("float", new FloatValueParser(this));
        r0.put("int", new IntValueParser(this));
        r0.put("boolean", new BooleanValueParser(this));
        r0.put("char", new CharValueParser(this));
        r0.put("ColorConstant", new ColorConstantValueParser(this));
        return;
    L5:
        r1.put("CreateTeXFormula", new CreateTeXFormulaParser(this));
        goto L6
    }

    private static void checkNullValue(String r3, String r4) {
        if (r3.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L5;
        return;
    L5:
        TeXSymbolParser$$ExternalSyntheticBUOutline0.m("is required for an argument of type '", r4, PredefinedTeXFormulaParser.RESOURCE_NAME, "Argument", ARG_VAL_ATTR);
    }

    private static Class<?>[] getArgumentClasses(NodeList r6) {
        Class<?>[] r0 = new Class[r6.getLength()];
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= r6.getLength()) goto L10;
        Class<?> r3 = classMappings.get(getAttrValueAndCheckIfNotNull("type", (Element) r6.item(r1)));
        if (r3 == null) goto L9;
        r0[r2] = r3;
        r2 = r2 + 1;
        r1 = r1 + 1;
        goto L4
    L9:
        throw new XMLResourceParseException(PredefinedTeXFormulaParser.RESOURCE_NAME, "Argument", "type", "has an invalid class name value!");
    L10:
        return r0;
    }

    private Object[] getArgumentValues(NodeList r7) {
        Object[] r0 = new Object[r7.getLength()];
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= r7.getLength()) goto L6;
        Element r3 = (Element) r7.item(r1);
        String r4 = getAttrValueAndCheckIfNotNull("type", r3);
        r0[r2] = this.argValueParsers.get(r4).parseValue(r3.getAttribute(ARG_VAL_ATTR), r4);
        r2 = r2 + 1;
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    private static String getAttrValueAndCheckIfNotNull(String r3, Element r4) {
        String r0 = r4.getAttribute(r3);
        if (r0.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L6;
        return r0;
    L6:
        throw new XMLResourceParseException(PredefinedTeXFormulaParser.RESOURCE_NAME, r4.getTagName(), r3, null);
    }

    public Object parse() {
        NodeList r0 = this.formula.getChildNodes();
        int r1 = 0;
    L4:
        if (r1 >= r0.getLength()) goto L12;
        Node r2 = r0.item(r1);
        if (r2.getNodeType() == 3) goto L10;
        Element r22 = (Element) r2;
        ActionParser r3 = this.actionParsers.get(r22.getTagName());
        if (r3 == null) goto L10;
        r3.parse(r22);
    L10:
        r1 = r1 + 1;
        goto L4
    L12:
        return this.result;
    }
}
