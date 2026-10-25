package com.android.dx.command.annotool;

import java.lang.annotation.ElementType;
import java.util.AbstractCollection;
import java.util.EnumSet;
import java.util.Locale;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class Main {

    public static class Arguments {
        String aclass;
        EnumSet<ElementType> eTypes;
        String[] files;
        EnumSet<PrintType> printTypes;

        public Arguments() {
            this.eTypes = EnumSet.noneOf(ElementType.class);
            this.printTypes = EnumSet.noneOf(PrintType.class);
        }

        public void parse(String[] r9) {
            int r1 = 0;
        L4:
            if (r1 >= r9.length) goto L33;
            String r2 = r9[r1];
            if (r2.startsWith("--annotation=") == false) goto L13;
            String r22 = r2.substring(r2.indexOf(61) + 1);
            if (this.aclass != null) goto L11;
            this.aclass = r22.replace('.', '/');
        L28:
            r1 = r1 + 1;
            goto L4
        L11:
            throw new InvalidArgumentException("--annotation can only be specified once.");
        L13:
            if (r2.startsWith("--element=") == false) goto L22;
            String[] r23 = r2.substring(r2.indexOf(61) + 1).split(",");     // Catch: IllegalArgumentException -> L19
            int r3 = r23.length;     // Catch: IllegalArgumentException -> L19
            int r4 = 0;
        L16:
            if (r4 >= r3) goto L28;
            this.eTypes.add(ElementType.valueOf(r23[r4].toUpperCase(Locale.ROOT)));     // Catch: IllegalArgumentException -> L19
            r4 = r4 + 1;
        L20:
            throw new InvalidArgumentException("invalid --element");
        L22:
            if (r2.startsWith("--print=") == false) goto L31;
            String[] r24 = r2.substring(r2.indexOf(61) + 1).split(",");     // Catch: IllegalArgumentException -> L29
            int r32 = r24.length;     // Catch: IllegalArgumentException -> L29
            int r42 = 0;
        L25:
            if (r42 >= r32) goto L28;
            this.printTypes.add(PrintType.valueOf(r24[r42].toUpperCase(Locale.ROOT)));     // Catch: IllegalArgumentException -> L29
            r42 = r42 + 1;
        L30:
            throw new InvalidArgumentException("invalid --print");
        L31:
            String[] r25 = new String[r9.length - r1];
            this.files = r25;
            System.arraycopy(r9, r1, r25, 0, r25.length);
        L33:
            if (this.aclass == null) goto L46;
            if (this.printTypes.isEmpty() == false) goto L38;
            this.printTypes.add(PrintType.CLASS);
        L38:
            if (this.eTypes.isEmpty() == false) goto L40;
            this.eTypes.add(ElementType.TYPE);
        L40:
            AbstractCollection r8 = this.eTypes.clone();
            r8.remove(ElementType.TYPE);
            r8.remove(ElementType.PACKAGE);
            if (r8.isEmpty() == false) goto L44;
            return;
        L44:
            throw new InvalidArgumentException("only --element parameters 'type' and 'package' supported");
        L46:
            throw new InvalidArgumentException("--annotation must be specified");
        }
    }

    public static class InvalidArgumentException extends Exception {
        public InvalidArgumentException() {
        }

        public InvalidArgumentException(String r1) {
            super(r1);
        }
    }

    public enum PrintType extends Enum<PrintType> {
        private static final /* synthetic */ PrintType[] $VALUES = null;
        public static final PrintType CLASS = null;
        public static final PrintType INNERCLASS = null;
        public static final PrintType METHOD = null;
        public static final PrintType PACKAGE = null;

        static {
            PrintType r0 = new PrintType("CLASS", 0);
            CLASS = r0;
            PrintType r1 = new PrintType("INNERCLASS", 1);
            INNERCLASS = r1;
            PrintType r2 = new PrintType("METHOD", 2);
            METHOD = r2;
            PrintType r3 = new PrintType("PACKAGE", 3);
            PACKAGE = r3;
            $VALUES = new PrintType[]{r0, r1, r2, r3};
        }

        PrintType(String r1, int r2) {
        }

        public static PrintType valueOf(String r1) {
            return (PrintType) Enum.valueOf(PrintType.class, r1);
        }

        public static PrintType[] values() {
            return (PrintType[]) $VALUES.clone();
        }
    }

    private Main() {
    }

    public static void main(String[] r1) {
        Arguments r0 = new Arguments();
        r0.parse(r1);     // Catch: InvalidArgumentException -> L6
        new AnnotationLister(r0).process();
        return;
    L6:
        e = move-exception;
        System.err.println(e.getMessage());
        GlShader$$ExternalSyntheticBUOutline1.m("usage");
    }
}
