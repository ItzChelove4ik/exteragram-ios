package org.mvel2.sh.command.basic;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;
import org.mvel2.sh.Command;
import org.mvel2.sh.ShellSession;
import org.mvel2.sh.text.TextUtil;
import org.mvel2.util.StringAppender;

/* loaded from: classes5.dex */
public class ObjectInspector implements Command {
    private static final int PADDING = 17;

    /* renamed from: org.mvel2.sh.command.basic.ObjectInspector$1, reason: invalid class name */
    public class AnonymousClass1 implements Comparator<Method> {
        public AnonymousClass1() {
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Method r1, Method r2) {
            return compare2(r1, r2);
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(Method r1, Method r2) {
            return r1.getName().compareTo(r2.getName());
        }
    }

    public ObjectInspector() {
    }

    private static String renderClassHeirarchy(Class r4) {
        LinkedList r0 = new LinkedList();
        r0.add(r4.getName());
    L3:
        r4 = r4.getSuperclass();
        if (r4 == null) goto L6;
        r0.add(r4.getName());
        goto L3
    L6:
        StringAppender r42 = new StringAppender();
        int r1 = r0.size();
    L7:
        r1 = r1 - 1;
        if (r1 == (-1)) goto L14;
        r42.append((String) r0.get(r1));
        if ((r1 - 1) == (-1)) goto L7;
        r42.append(" -> ");
        goto L7
    L14:
        return r42.toString();
    }

    private static void renderFields(Class r3) {
        Field[] r32 = r3.getFields();
        int r0 = 0;
    L4:
        if (r0 >= r32.length) goto L6;
        write(_UrlKt.FRAGMENT_ENCODE_SET, r32[r0].getType().getName() + " " + r32[r0].getName());
        r0 = r0 + 1;
        goto L4
    }

    private static void renderMethods(Class r9) {
        Method[] r0 = r9.getMethods();
        Arrays.sort(r0, new AnonymousClass1());
        StringAppender r1 = new StringAppender();
        int r3 = 0;
    L4:
        if (r3 >= r0.length) goto L24;
        r1.append(TextUtil.paint(' ', 19));
        Method r4 = r0[r3];
        r1.append(r4.getName()).append("(");
        Class<?>[] r5 = r4.getParameterTypes();
        int r6 = 0;
    L7:
        if (r6 >= r5.length) goto L15;
        if (r5[r6].isArray() == false) goto L11;
        r1.append(shorter(r5[r6].getComponentType().getName()) + _UrlKt.PATH_SEGMENT_ENCODE_SET_URI);
    L12:
        r6 = r6 + 1;
        if (r6 >= r5.length) goto L7;
        r1.append(", ");
        goto L7
    L11:
        r1.append(shorter(r5[r6].getName()));
        goto L12
    L15:
        r1.append(")");
        String r52 = r4.getReturnType().getName();
        if ("void".equals(r52) == true) goto L19;
        r1.append(" -> ").append(shorter(r52));
    L19:
        if (r4.getDeclaringClass() == r9) goto L21;
        r1.append("    [").append(shorter(r4.getDeclaringClass().getName())).append("]");
    L21:
        r3 = r3 + 1;
        if (r3 >= r0.length) goto L4;
        r1.append('\n');
        goto L4
    L24:
        System.out.println(r1.toString());
    }

    private static String shorter(String r8) {
        if (r8.startsWith("java.lang.") == false) goto L10;
        Iterator r1 = Arrays.asList(new String[]{"annotation", "instrument", "invoke", "management", "ref", "reflect"}).iterator();
    L7:
        if (r1.hasNext() == false) goto L12;
        if (r8.startsWith("java.lang." + ((String) r1.next()) + ".") == false) goto L7;
    L12:
        return r8.replaceAll("^java\\.lang\\.", _UrlKt.FRAGMENT_ENCODE_SET);
    L10:
        return r8;
    }

    private static void write(Object r3, Object r4) {
        System.out.println(TextUtil.padTwo(r3, ": " + r4, 17));
    }

    @Override // org.mvel2.sh.Command
    public Object execute(ShellSession r7, String[] r8) {
        if (r8.length != 0) goto L9;
        Object r6 = r7.getCtxObject();
        if (r6 != null) goto L13;
        System.out.println("inspect: requires an argument or a session context.");
        return null;
    L13:
        System.out.println("Object Inspector");
        System.out.println(TextUtil.paint(SignatureVisitor.SUPER, 17));
        if (r6 != null) goto L17;
        System.out.println("[Value is Null]");
        return null;
    L17:
        Class<?> r72 = r6.getClass();
        ByteArrayOutputStream r2 = new ByteArrayOutputStream();     // Catch: Exception -> L20
        ObjectOutputStream r3 = new ObjectOutputStream(new BufferedOutputStream(r2));     // Catch: Exception -> L20
        r3.writeObject(r6);     // Catch: Exception -> L20
        r3.flush();     // Catch: Exception -> L20
        r2.flush();     // Catch: Exception -> L20
        long r22 = r2.size();     // Catch: Exception -> L20
        boolean r4 = true;
    L22:
        if (r8.length <= 0) goto L24;
        write("VariableName", r8[0]);
    L24:
        write("Hashcode", Integer.valueOf(r6.hashCode()));
        write("ClassType", r72.getName());
        write("Serializable", Boolean.valueOf(r4));
        if (r4 == false) goto L27;
        write("SerializedSize", r22 + " bytes");
    L27:
        write("ClassHierarchy", renderClassHeirarchy(r72));
        write("Fields", Integer.valueOf(r72.getFields().length));
        renderFields(r72);
        write("Methods", Integer.valueOf(r72.getMethods().length));
        renderMethods(r72);
        System.out.println();
        return null;
    L20:
        r22 = 0;
        r4 = false;
        goto L22
    L9:
        if (r7.getVariables().containsKey(r8[0]) == true) goto L12;
        System.out.println("inspect: no such variable: " + r8[0]);
        return null;
    L12:
        r6 = r7.getVariables().get(r8[0]);
        goto L13
    }

    @Override // org.mvel2.sh.Command
    public String getDescription() {
        return "inspects an object";
    }

    @Override // org.mvel2.sh.Command
    public String getHelp() {
        return "No help yet";
    }
}
