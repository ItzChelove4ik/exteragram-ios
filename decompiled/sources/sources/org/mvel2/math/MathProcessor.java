package org.mvel2.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collection;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import org.mvel2.DataConversion;
import org.mvel2.Unit;
import org.mvel2.compiler.BlankLiteral;
import org.mvel2.debug.DebugTools;
import org.mvel2.util.ParseTools;
import org.mvel2.util.Soundex;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class MathProcessor {
    private static final MathContext MATH_CONTEXT = null;

    static {
        MATH_CONTEXT = MathContext.DECIMAL128;
    }

    public MathProcessor() {
    }

    private static Object _doOperations(int r3, Object r4, int r5, int r6, Object r7) {
        if (r5 >= 20) goto L52;
        if (r3 <= 49) goto L7;
    L10:
        if (r3 == r6) goto L50;
    L12:
        if (isIntegerType(r3) == true) goto L14;
    L20:
        if (r7 != null) goto L22;
    L25:
        if (r5 == 0) goto L36;
        if (r3 == 15) goto L29;
        if (r6 != 15) goto L36;
    L29:
        if (r3 == r6) goto L36;
        if (r3 == 200) goto L36;
        if (r6 == 200) goto L36;
        return doOperationNonNumeric(r3, DataConversion.convert(r4, Boolean.class), r5, DataConversion.convert(r7, Boolean.class));
    L36:
        if (r3 == 1) goto L39;
        if (r6 != 1) goto L52;
    L39:
        if (r3 != 8) goto L41;
    L44:
        if (r3 != 1) goto L48;
        return doOperationNonNumeric(r3, r4, r5, String.valueOf(r7));
    L48:
        return doOperationNonNumeric(r3, String.valueOf(r4), r5, r7);
    L41:
        if (r3 == 112) goto L44;
        if (r6 == 8) goto L44;
        if (r6 != 112) goto L52;
    L22:
        if (isNumericOperation(r3, r4, r5, r6, r7) == false) goto L25;
        return doPrimWrapperArithmetic(getNumber(r4, r3), r5, getNumber(r7, r6), Math.max(box(r6), box(r3)));
    L14:
        if (isIntegerType(r6) == false) goto L20;
        if (r5 < 6) goto L20;
        if (r5 > 13) goto L20;
    L50:
        return doOperationsSameType(r3, r4, r5, r7);
    L7:
        if (r5 == 18) goto L10;
        if (r5 != 19) goto L12;
    L52:
        return doOperationNonNumeric(r3, r4, r5, r7);
    }

    private static BigDecimal asBigDecimal(Object r3) {
        if (r3 != null) goto L4;
        return null;
    L4:
        if (r3 != BlankLiteral.INSTANCE) goto L7;
        return null;
    L7:
        if ((r3 instanceof BigDecimal) == false) goto L11;
        return (BigDecimal) r3;
    L11:
        if ((r3 instanceof String) == false) goto L15;
        return new BigDecimal((String) r3);
    L15:
        if ((r3 instanceof Number) == true) goto L17;
        StringBuilder r1 = new StringBuilder("cannot convert <");
        r1.append(r3);
        Class<?> r32 = r3.getClass();
        r1.append("> to a numeric type: ");
        r1.append(r32);
        throw new RuntimeException(r1.toString());
    L17:
        return BigDecimal.valueOf(((Number) r3).doubleValue());
    }

    private static int box(int r1) {
        if (r1 != 7) goto L5;
        return 15;
    L5:
        if (r1 != 8) goto L7;
        return 112;
    L7:
        if (r1 == 9) goto L20;
        switch(r1) {
            case 100: goto L18;
            case 101: goto L16;
            case 102: goto L14;
            case 103: goto L12;
            case 104: goto L10;
            default: goto L9;
        };
    L9:
        return r1;
    L10:
        return 108;
    L12:
        return 109;
    L14:
        return 107;
    L16:
        return 106;
    L18:
        return 105;
    L20:
        return 113;
    }

    private static Object doBigDecimalArithmetic(BigDecimal r2, int r3, BigDecimal r4, boolean r5, int r6) {
        if (r3 == 0) goto L92;
        boolean r0 = true;
        if (r3 != 1) goto L6;
        if (r5 == false) goto L91;
        return ParseTools.narrowType(r2.subtract(r4, MATH_CONTEXT), r6);
    L91:
        return r2.subtract(r4, MATH_CONTEXT);
    L6:
        if (r3 != 2) goto L8;
        if (r5 == false) goto L86;
        return ParseTools.narrowType(r2.multiply(r4, MATH_CONTEXT), r6);
    L86:
        return r2.multiply(r4, MATH_CONTEXT);
    L8:
        if (r3 != 3) goto L10;
        if (r5 == false) goto L81;
        return ParseTools.narrowType(r2.divide(r4, MATH_CONTEXT), r6);
    L81:
        return r2.divide(r4, MATH_CONTEXT);
    L10:
        if (r3 != 4) goto L12;
        if (r5 == false) goto L76;
        return ParseTools.narrowType(r2.remainder(r4), r6);
    L76:
        return r2.remainder(r4);
    L12:
        if (r3 != 5) goto L14;
        if (r5 == false) goto L71;
        return ParseTools.narrowType(r2.pow(r4.intValue(), MATH_CONTEXT), r6);
    L71:
        return r2.pow(r4.intValue(), MATH_CONTEXT);
    L14:
        switch(r3) {
            case 14: goto L59;
            case 15: goto L51;
            case 16: goto L43;
            case 17: goto L35;
            case 18: goto L26;
            case 19: goto L17;
            default: goto L15;
        };
    L15:
        return null;
    L17:
        if (r2 != null) goto L21;
        if (r4 != null) goto L25;
    L20:
        r0 = false;
    L25:
        return Boolean.valueOf(r0);
    L21:
        if (r4 == null) goto L25;
        if (r2.compareTo(r4) == 0) goto L20;
    L26:
        if (r2 != null) goto L30;
        if (r4 == null) goto L34;
    L29:
        r0 = false;
    L34:
        return Boolean.valueOf(r0);
    L30:
        if (r4 == null) goto L29;
        if (r2.compareTo(r4) != 0) goto L29;
    L35:
        if (r2 == null) goto L40;
        if (r4 == null) goto L40;
        if (r2.compareTo(r4) < 0) goto L40;
    L42:
        return Boolean.valueOf(r0);
    L40:
        r0 = false;
        goto L42
    L43:
        if (r2 == null) goto L48;
        if (r4 == null) goto L48;
        if (r2.compareTo(r4) > 0) goto L48;
    L50:
        return Boolean.valueOf(r0);
    L48:
        r0 = false;
        goto L50
    L51:
        if (r2 == null) goto L56;
        if (r4 == null) goto L56;
        if (r2.compareTo(r4) <= 0) goto L56;
    L58:
        return Boolean.valueOf(r0);
    L56:
        r0 = false;
        goto L58
    L59:
        if (r2 == null) goto L64;
        if (r4 == null) goto L64;
        if (r2.compareTo(r4) >= 0) goto L64;
    L66:
        return Boolean.valueOf(r0);
    L64:
        r0 = false;
        goto L66
    L92:
        if (r5 == false) goto L96;
        return ParseTools.narrowType(r2.add(r4, MATH_CONTEXT), r6);
    L96:
        return r2.add(r4, MATH_CONTEXT);
    }

    private static Object doOperationNonNumeric(int r3, Object r4, int r5, Object r6) {
        if (r5 == 0) goto L93;
        boolean r0 = true;
        if (r5 != 1) goto L6;
        return null;
    L6:
        if (r5 != 2) goto L8;
        return null;
    L8:
        if (r5 != 3) goto L10;
        return null;
    L10:
        if (r5 != 4) goto L12;
        return null;
    L12:
        if (r5 == 27) goto L91;
        switch(r5) {
            case 14: goto L76;
            case 15: goto L61;
            case 16: goto L46;
            case 17: goto L31;
            case 18: goto L29;
            case 19: goto L27;
            case 20: goto L25;
            default: goto L15;
        };
    L15:
        StringBuilder r02 = new StringBuilder("could not perform numeric operation on non-numeric types: left-type=");
        String r1 = "null";
        if (r4 == null) goto L18;
        String r2 = r4.getClass().getName();
    L19:
        r02.append(r2);
        r02.append("; right-type=");
        if (r6 == null) goto L22;
        r1 = r6.getClass().getName();
    L22:
        r02.append(r1);
        r02.append(" [vals (");
        r02.append(String.valueOf(r4));
        r02.append(", ");
        r02.append(String.valueOf(r6));
        r02.append(") operation=");
        r02.append(DebugTools.getOperatorName(r5));
        r02.append(" (opcode:");
        r02.append(r5);
        r02.append(") ]");
        throw new RuntimeException(r02.toString());
    L18:
        r2 = "null";
        goto L19
    L25:
        return String.valueOf(r4).concat(String.valueOf(r6));
    L27:
        return safeNotEquals(r6, r4);
    L29:
        return safeEquals(r6, r4);
    L31:
        if ((r4 instanceof Comparable) == false) goto L44;
        if (r6 != null) goto L100;
    L38:
        r0 = false;
    L39:
        return Boolean.valueOf(r0);
    L36:
        e = move-exception;
        MathProcessor$$ExternalSyntheticBUOutline1.m(r4, r6, e);
        return null;
    L100:
        if (((Comparable) r4).compareTo(r6) < 0) goto L38;
    L44:
        return Boolean.FALSE;
    L46:
        if ((r4 instanceof Comparable) == false) goto L59;
        if (r6 != null) goto L98;
    L53:
        r0 = false;
    L54:
        return Boolean.valueOf(r0);
    L51:
        e = move-exception;
        MathProcessor$$ExternalSyntheticBUOutline1.m(r4, r6, e);
        return null;
    L98:
        if (((Comparable) r4).compareTo(r6) > 0) goto L53;
    L59:
        return Boolean.FALSE;
    L61:
        if ((r4 instanceof Comparable) == false) goto L74;
        if (r6 != null) goto L104;
    L68:
        r0 = false;
    L69:
        return Boolean.valueOf(r0);
    L66:
        e = move-exception;
        MathProcessor$$ExternalSyntheticBUOutline1.m(r4, r6, e);
        return null;
    L104:
        if (((Comparable) r4).compareTo(r6) < 1) goto L68;
    L74:
        return Boolean.FALSE;
    L76:
        if ((r4 instanceof Comparable) == false) goto L89;
        if (r6 != null) goto L102;
    L83:
        r0 = false;
    L84:
        return Boolean.valueOf(r0);
    L81:
        e = move-exception;
        MathProcessor$$ExternalSyntheticBUOutline1.m(r4, r6, e);
        return null;
    L102:
        if (((Comparable) r4).compareTo(r6) > (-1)) goto L83;
    L89:
        return Boolean.FALSE;
    L91:
        return Boolean.valueOf(Soundex.soundex(String.valueOf(r4)).equals(Soundex.soundex(String.valueOf(r6))));
    L93:
        if (r3 != 50) goto L97;
        ArrayList r32 = new ArrayList((Collection) r4);
        r32.add(r6);
        return r32;
    L97:
        return String.valueOf(r4).concat(String.valueOf(r6));
    }

    public static Object doOperations(int r3, Object r4, int r5, int r6, Object r7) {
        if (r3 >= 1) goto L7;
        if (r4 != null) goto L6;
        r3 = 0;
        goto L7
    L6:
        r3 = ParseTools.__resolveType(r4.getClass());
    L7:
        if (r6 >= 1) goto L12;
        if (r7 != null) goto L10;
        r6 = 0;
        goto L12
    L10:
        r6 = ParseTools.__resolveType(r7.getClass());
    L12:
        if (r3 != 110) goto L24;
        if (r6 != 110) goto L18;
        return doBigDecimalArithmetic((BigDecimal) r4, r5, (BigDecimal) r7, false, -1);
    L18:
        if (r6 <= 99) goto L22;
        return doBigDecimalArithmetic((BigDecimal) r4, r5, asBigDecimal(r7), false, -1);
    L22:
        return _doOperations(r3, r4, r5, r6, r7);
    L24:
        return _doOperations(r3, r4, r5, r6, r7);
    }

    public static Object doOperations(Object r1, int r2, int r3, Object r4) {
        if (r1 != null) goto L4;
        int r0 = 0;
    L6:
        return doOperations(r0, r1, r2, r3, r4);
    L4:
        r0 = ParseTools.__resolveType(r1.getClass());
        goto L6
    }

    public static Object doOperations(Object r2, int r3, Object r4) {
        if (r2 != null) goto L4;
        int r0 = 0;
    L5:
        if (r4 != null) goto L7;
        int r1 = -1;
    L9:
        return doOperations(r0, r2, r3, r1, r4);
    L7:
        r1 = ParseTools.__resolveType(r4.getClass());
        goto L9
    L4:
        r0 = ParseTools.__resolveType(r2.getClass());
        goto L5
    }

    private static Object doOperationsSameType(int r8, Object r9, int r10, Object r11) {
        boolean r5 = false;
        if (r8 != 8) goto L5;
    L486:
        switch(r10) {
            case 0: goto L621;
            case 1: goto L619;
            case 2: goto L617;
            case 3: goto L615;
            case 4: goto L613;
            case 5: goto L606;
            case 6: goto L605;
            case 7: goto L603;
            case 8: goto L601;
            case 9: goto L599;
            case 10: goto L597;
            case 11: goto L595;
            case 12: goto L592;
            case 13: goto L487;
            case 14: goto L585;
            case 15: goto L578;
            case 16: goto L571;
            case 17: goto L564;
            case 18: goto L563;
            case 19: goto L561;
            default: goto L487;
        };
    L487:
        switch(r10) {
            case 0: goto L559;
            case 1: goto L557;
            case 2: goto L555;
            case 3: goto L553;
            case 4: goto L551;
            case 5: goto L544;
            case 6: goto L543;
            case 7: goto L541;
            case 8: goto L539;
            case 9: goto L537;
            case 10: goto L535;
            case 11: goto L533;
            case 12: goto L530;
            case 13: goto L488;
            case 14: goto L523;
            case 15: goto L516;
            case 16: goto L509;
            case 17: goto L502;
            case 18: goto L501;
            case 19: goto L499;
            default: goto L488;
        };
    L488:
        if (r10 == 0) goto L497;
        if (r10 == 18) goto L495;
        if (r10 == 19) goto L493;
        return null;
    L493:
        return safeNotEquals(r11, r9);
    L495:
        return safeEquals(r11, r9);
    L497:
        return String.valueOf(r9).concat(String.valueOf(r11));
    L502:
        if (r9 == null) goto L508;
        if (r11 == null) goto L508;
        if (toByte(r9) < toByte(r11)) goto L508;
        r5 = true;
    L508:
        return Boolean.valueOf(r5);
    L509:
        if (r9 == null) goto L515;
        if (r11 == null) goto L515;
        if (toByte(r9) > toByte(r11)) goto L515;
        r5 = true;
    L515:
        return Boolean.valueOf(r5);
    L516:
        if (r9 == null) goto L522;
        if (r11 == null) goto L522;
        if (toByte(r9) <= toByte(r11)) goto L522;
        r5 = true;
    L522:
        return Boolean.valueOf(r5);
    L523:
        if (r9 == null) goto L529;
        if (r11 == null) goto L529;
        if (toByte(r9) >= toByte(r11)) goto L529;
        r5 = true;
    L529:
        return Boolean.valueOf(r5);
    L530:
        ByteString$$ExternalSyntheticBUOutline0.m("unsigned left-shift not supported");
        return null;
    L544:
        double r82 = Math.pow(toByte(r9), toByte(r11));
        if (r82 <= 127.0d) goto L549;
        return Double.valueOf(r82);
    L549:
        return Byte.valueOf((byte) r82);
    L499:
        return safeNotEquals(r11, r9);
    L501:
        return safeEquals(r11, r9);
    L533:
        return Integer.valueOf(toByte(r9) >>> toByte(r11));
    L535:
        return Integer.valueOf(toByte(r9) << toByte(r11));
    L537:
        return Integer.valueOf(toByte(r9) >> toByte(r11));
    L539:
        return Integer.valueOf(toByte(r9) ^ toByte(r11));
    L541:
        return Integer.valueOf(toByte(r9) | toByte(r11));
    L543:
        return Integer.valueOf(toByte(r9) & toByte(r11));
    L551:
        return Integer.valueOf(toByte(r9) % toByte(r11));
    L553:
        return Double.valueOf(toDouble(r9) / toDouble(r11));
    L555:
        return Integer.valueOf(toByte(r9) * toByte(r11));
    L557:
        return Integer.valueOf(toByte(r9) - toByte(r11));
    L559:
        return Integer.valueOf(toByte(r9) + toByte(r11));
    L564:
        if (r9 == null) goto L570;
        if (r11 == null) goto L570;
        if (toChar(r9) < toChar(r11)) goto L570;
        r5 = true;
    L570:
        return Boolean.valueOf(r5);
    L571:
        if (r9 == null) goto L577;
        if (r11 == null) goto L577;
        if (toChar(r9) > toChar(r11)) goto L577;
        r5 = true;
    L577:
        return Boolean.valueOf(r5);
    L578:
        if (r9 == null) goto L584;
        if (r11 == null) goto L584;
        if (toChar(r9) <= toChar(r11)) goto L584;
        r5 = true;
    L584:
        return Boolean.valueOf(r5);
    L585:
        if (r9 == null) goto L591;
        if (r11 == null) goto L591;
        if (toChar(r9) >= toChar(r11)) goto L591;
        r5 = true;
    L591:
        return Boolean.valueOf(r5);
    L592:
        ByteString$$ExternalSyntheticBUOutline0.m("unsigned left-shift not supported");
        return null;
    L606:
        double r83 = Math.pow(toChar(r9), toChar(r11));
        if (r83 <= 65535.0d) goto L611;
        return Double.valueOf(r83);
    L611:
        return Character.valueOf((char) r83);
    L561:
        return safeNotEquals(r11, r9);
    L563:
        return safeEquals(r11, r9);
    L595:
        return Integer.valueOf(toChar(r9) >>> toChar(r11));
    L597:
        return Integer.valueOf(toChar(r9) << toChar(r11));
    L599:
        return Integer.valueOf(toChar(r9) >> toChar(r11));
    L601:
        return Integer.valueOf(toChar(r9) ^ toChar(r11));
    L603:
        return Integer.valueOf(toChar(r9) | toChar(r11));
    L605:
        return Integer.valueOf(toChar(r9) & toChar(r11));
    L613:
        return Integer.valueOf(toChar(r9) % toChar(r11));
    L615:
        return Double.valueOf(toChar(r9) / toChar(r11));
    L617:
        return Integer.valueOf(toChar(r9) * toChar(r11));
    L619:
        return Integer.valueOf(toChar(r9) - toChar(r11));
    L621:
        return String.valueOf(r9).concat(String.valueOf(r11));
    L5:
        if (r8 == 9) goto L487;
        if (r8 != 50) goto L9;
        if (r10 == 0) goto L484;
        if (r10 == 18) goto L483;
        if (r10 == 19) goto L479;
        ByteString$$ExternalSyntheticBUOutline0.m("illegal operation on Collection type");
        return null;
    L479:
        return Boolean.valueOf(!r9.equals(r11));
    L483:
        return Boolean.valueOf(r9.equals(r11));
    L484:
        ArrayList r84 = new ArrayList((Collection) r9);
        r84.addAll((Collection) r11);
        return r84;
    L9:
        if (r8 == 300) goto L290;
        switch(r8) {
            case 100: goto L113;
            case 101: goto L13;
            case 102: goto L114;
            case 103: goto L291;
            case 104: goto L292;
            case 105: goto L113;
            case 106: goto L13;
            case 107: goto L114;
            case 108: goto L292;
            case 109: goto L291;
            default: goto L11;
        };
    L11:
        switch(r8) {
            case 111: goto L293;
            case 112: goto L486;
            case 113: goto L487;
            default: goto L488;
        };
    L293:
        switch(r10) {
            case 0: goto L354;
            case 1: goto L352;
            case 2: goto L350;
            case 3: goto L348;
            case 4: goto L346;
            case 5: goto L344;
            case 6: goto L341;
            case 7: goto L341;
            case 8: goto L341;
            case 9: goto L341;
            case 10: goto L341;
            case 11: goto L341;
            case 12: goto L486;
            case 13: goto L486;
            case 14: goto L334;
            case 15: goto L327;
            case 16: goto L320;
            case 17: goto L313;
            case 18: goto L304;
            case 19: goto L295;
            default: goto L486;
        };
    L295:
        if (r9 == null) goto L300;
        if (r11 == null) goto L300;
        if (((BigInteger) r9).compareTo((BigInteger) r11) == 0) goto L303;
    L299:
        r5 = true;
    L303:
        return Boolean.valueOf(r5);
    L300:
        if (r9 == r11) goto L303;
    L304:
        if (r9 == null) goto L309;
        if (r11 == null) goto L309;
        if (((BigInteger) r9).compareTo((BigInteger) r11) != 0) goto L312;
    L308:
        r5 = true;
    L312:
        return Boolean.valueOf(r5);
    L309:
        if (r9 != r11) goto L312;
    L313:
        if (r9 == null) goto L319;
        if (r11 == null) goto L319;
        if (((BigInteger) r9).compareTo((BigInteger) r11) < 0) goto L319;
        r5 = true;
    L319:
        return Boolean.valueOf(r5);
    L320:
        if (r9 == null) goto L326;
        if (r11 == null) goto L326;
        if (((BigInteger) r9).compareTo((BigInteger) r11) > 0) goto L326;
        r5 = true;
    L326:
        return Boolean.valueOf(r5);
    L327:
        if (r9 == null) goto L333;
        if (r11 == null) goto L333;
        if (((BigInteger) r9).compareTo((BigInteger) r11) <= 0) goto L333;
        r5 = true;
    L333:
        return Boolean.valueOf(r5);
    L334:
        if (r9 == null) goto L340;
        if (r11 == null) goto L340;
        if (((BigInteger) r9).compareTo((BigInteger) r11) >= 0) goto L340;
        r5 = true;
    L340:
        return Boolean.valueOf(r5);
    L341:
        GlShader$$ExternalSyntheticBUOutline1.m("bitwise operation on a number greater than 32-bits not possible");
        return null;
    L344:
        return ((BigInteger) r9).pow(((BigInteger) r11).intValue());
    L346:
        return ((BigInteger) r9).remainder((BigInteger) r11);
    L348:
        return ((BigInteger) r9).divide((BigInteger) r11);
    L350:
        return ((BigInteger) r9).multiply((BigInteger) r11);
    L352:
        return ((BigInteger) r9).subtract((BigInteger) r11);
    L354:
        return ((BigInteger) r9).add((BigInteger) r11);
    L13:
        switch(r10) {
            case 0: goto L112;
            case 1: goto L110;
            case 2: goto L108;
            case 3: goto L106;
            case 4: goto L104;
            case 5: goto L97;
            case 6: goto L92;
            case 7: goto L86;
            case 8: goto L80;
            case 9: goto L74;
            case 10: goto L68;
            case 11: goto L62;
            case 12: goto L113;
            case 13: goto L113;
            case 14: goto L54;
            case 15: goto L47;
            case 16: goto L40;
            case 17: goto L33;
            case 18: goto L24;
            case 19: goto L15;
            default: goto L113;
        };
    L15:
        if (r9 == null) goto L20;
        if (r11 == null) goto L20;
        if (toInteger(r9) == toInteger(r11)) goto L23;
    L19:
        r5 = true;
    L23:
        return Boolean.valueOf(r5);
    L20:
        if (r9 == r11) goto L23;
    L24:
        if (r9 == null) goto L29;
        if (r11 == null) goto L29;
        if (toInteger(r9) != toInteger(r11)) goto L32;
    L28:
        r5 = true;
    L32:
        return Boolean.valueOf(r5);
    L29:
        if (r9 != r11) goto L32;
    L33:
        if (r9 == null) goto L39;
        if (r11 == null) goto L39;
        if (toInteger(r9) < toInteger(r11)) goto L39;
        r5 = true;
    L39:
        return Boolean.valueOf(r5);
    L40:
        if (r9 == null) goto L46;
        if (r11 == null) goto L46;
        if (toInteger(r9) > toInteger(r11)) goto L46;
        r5 = true;
    L46:
        return Boolean.valueOf(r5);
    L47:
        if (r9 == null) goto L53;
        if (r11 == null) goto L53;
        if (toInteger(r9) <= toInteger(r11)) goto L53;
        r5 = true;
    L53:
        return Boolean.valueOf(r5);
    L54:
        if (r9 == null) goto L60;
        if (r11 == null) goto L60;
        if (toInteger(r9) >= toInteger(r11)) goto L60;
        r5 = true;
    L60:
        return Boolean.valueOf(r5);
    L97:
        double r85 = Math.pow(toInteger(r9), toInteger(r11));
        if (r85 <= 2.147483647E9d) goto L102;
        return Double.valueOf(r85);
    L102:
        return Integer.valueOf((int) r85);
    L62:
        if ((r11 instanceof Long) == false) goto L66;
        return Integer.valueOf(((Integer) r9).intValue() >>> ((int) ((Long) r11).longValue()));
    L66:
        return Integer.valueOf(((Integer) r9).intValue() >>> ((Integer) r11).intValue());
    L68:
        if ((r11 instanceof Long) == false) goto L72;
        return Integer.valueOf(((Integer) r9).intValue() << ((int) ((Long) r11).longValue()));
    L72:
        return Integer.valueOf(((Integer) r9).intValue() << ((Integer) r11).intValue());
    L74:
        if ((r11 instanceof Long) == false) goto L78;
        return Integer.valueOf(((Integer) r9).intValue() >> ((int) ((Long) r11).longValue()));
    L78:
        return Integer.valueOf(((Integer) r9).intValue() >> ((Integer) r11).intValue());
    L80:
        if ((r11 instanceof Long) == false) goto L84;
        return Long.valueOf(((Integer) r9).intValue() ^ ((Long) r11).longValue());
    L84:
        return Integer.valueOf(((Integer) r9).intValue() ^ ((Integer) r11).intValue());
    L86:
        if ((r11 instanceof Long) == false) goto L90;
        return Long.valueOf(((Integer) r9).intValue() | ((Long) r11).longValue());
    L90:
        return Integer.valueOf(((Integer) r9).intValue() | ((Integer) r11).intValue());
    L92:
        if ((r11 instanceof Long) == false) goto L96;
        return Long.valueOf(((Integer) r9).intValue() & ((Long) r11).longValue());
    L96:
        return Integer.valueOf(((Integer) r9).intValue() & ((Integer) r11).intValue());
    L104:
        return Integer.valueOf(toInteger(r9) % toInteger(r11));
    L106:
        return Double.valueOf(toDouble(r9) / toInteger(r11));
    L108:
        return Integer.valueOf(toInteger(r9) * toInteger(r11));
    L110:
        return Integer.valueOf(toInteger(r9) - toInteger(r11));
    L112:
        return Integer.valueOf(toInteger(r9) + toInteger(r11));
    L113:
        switch(r10) {
            case 0: goto L289;
            case 1: goto L287;
            case 2: goto L285;
            case 3: goto L283;
            case 4: goto L281;
            case 5: goto L274;
            case 6: goto L273;
            case 7: goto L271;
            case 8: goto L269;
            case 9: goto L267;
            case 10: goto L265;
            case 11: goto L263;
            case 12: goto L114;
            case 13: goto L114;
            case 14: goto L255;
            case 15: goto L248;
            case 16: goto L241;
            case 17: goto L234;
            case 18: goto L225;
            case 19: goto L216;
            default: goto L114;
        };
    L216:
        if (r9 == null) goto L221;
        if (r11 == null) goto L221;
        if (toShort(r9) == toShort(r11)) goto L224;
    L220:
        r5 = true;
    L224:
        return Boolean.valueOf(r5);
    L221:
        if (r9 == r11) goto L224;
    L225:
        if (r9 == null) goto L230;
        if (r11 == null) goto L230;
        if (toShort(r9) != toShort(r11)) goto L233;
    L229:
        r5 = true;
    L233:
        return Boolean.valueOf(r5);
    L230:
        if (r9 != r11) goto L233;
    L234:
        if (r9 == null) goto L240;
        if (r11 == null) goto L240;
        if (toShort(r9) < toShort(r11)) goto L240;
        r5 = true;
    L240:
        return Boolean.valueOf(r5);
    L241:
        if (r9 == null) goto L247;
        if (r11 == null) goto L247;
        if (toShort(r9) > toShort(r11)) goto L247;
        r5 = true;
    L247:
        return Boolean.valueOf(r5);
    L248:
        if (r9 == null) goto L254;
        if (r11 == null) goto L254;
        if (toShort(r9) <= toShort(r11)) goto L254;
        r5 = true;
    L254:
        return Boolean.valueOf(r5);
    L255:
        if (r9 == null) goto L261;
        if (r11 == null) goto L261;
        if (toShort(r9) >= toShort(r11)) goto L261;
        r5 = true;
    L261:
        return Boolean.valueOf(r5);
    L274:
        double r86 = Math.pow(toShort(r9), toShort(r11));
        if (r86 <= 32767.0d) goto L279;
        return Double.valueOf(r86);
    L279:
        return Short.valueOf((short) r86);
    L263:
        return Integer.valueOf(((Short) r9).shortValue() >>> ((Short) r11).shortValue());
    L265:
        return Integer.valueOf(((Short) r9).shortValue() << ((Short) r11).shortValue());
    L267:
        return Integer.valueOf(((Short) r9).shortValue() >> ((Short) r11).shortValue());
    L269:
        return Integer.valueOf(((Short) r9).shortValue() ^ ((Short) r11).shortValue());
    L271:
        return Integer.valueOf(((Short) r9).shortValue() | ((Short) r11).shortValue());
    L273:
        return Integer.valueOf(((Short) r9).shortValue() & ((Short) r11).shortValue());
    L281:
        return Integer.valueOf(toShort(r9) % toShort(r11));
    L283:
        return Double.valueOf(toDouble(r9) / toDouble(r11));
    L285:
        return Integer.valueOf(toShort(r9) * toShort(r11));
    L287:
        return Integer.valueOf(toShort(r9) - toShort(r11));
    L289:
        return Integer.valueOf(toShort(r9) + toShort(r11));
    L114:
        switch(r10) {
            case 0: goto L215;
            case 1: goto L213;
            case 2: goto L211;
            case 3: goto L209;
            case 4: goto L207;
            case 5: goto L200;
            case 6: goto L195;
            case 7: goto L189;
            case 8: goto L183;
            case 9: goto L177;
            case 10: goto L171;
            case 11: goto L165;
            case 12: goto L162;
            case 13: goto L290;
            case 14: goto L155;
            case 15: goto L148;
            case 16: goto L141;
            case 17: goto L134;
            case 18: goto L125;
            case 19: goto L116;
            default: goto L290;
        };
    L116:
        if (r9 == null) goto L121;
        if (r11 == null) goto L121;
        if (toLong(r9) == toLong(r11)) goto L124;
    L120:
        r5 = true;
    L124:
        return Boolean.valueOf(r5);
    L121:
        if (r9 == r11) goto L124;
    L125:
        if (r9 == null) goto L130;
        if (r11 == null) goto L130;
        if (toLong(r9) != toLong(r11)) goto L133;
    L129:
        r5 = true;
    L133:
        return Boolean.valueOf(r5);
    L130:
        if (r9 != r11) goto L133;
    L134:
        if (r9 == null) goto L140;
        if (r11 == null) goto L140;
        if (toLong(r9) < toLong(r11)) goto L140;
        r5 = true;
    L140:
        return Boolean.valueOf(r5);
    L141:
        if (r9 == null) goto L147;
        if (r11 == null) goto L147;
        if (toLong(r9) > toLong(r11)) goto L147;
        r5 = true;
    L147:
        return Boolean.valueOf(r5);
    L148:
        if (r9 == null) goto L154;
        if (r11 == null) goto L154;
        if (toLong(r9) <= toLong(r11)) goto L154;
        r5 = true;
    L154:
        return Boolean.valueOf(r5);
    L155:
        if (r9 == null) goto L161;
        if (r11 == null) goto L161;
        if (toLong(r9) >= toLong(r11)) goto L161;
        r5 = true;
    L161:
        return Boolean.valueOf(r5);
    L162:
        ByteString$$ExternalSyntheticBUOutline0.m("unsigned left-shift not supported");
        return null;
    L200:
        double r87 = Math.pow(toLong(r9), toLong(r11));
        if (r87 <= 9.223372036854776E18d) goto L205;
        return Double.valueOf(r87);
    L205:
        return Long.valueOf((long) r87);
    L165:
        if ((r11 instanceof Integer) == false) goto L169;
        return Long.valueOf(((Long) r9).longValue() >>> ((Integer) r11).intValue());
    L169:
        return Long.valueOf(((Long) r9).longValue() >>> ((int) ((Long) r11).longValue()));
    L171:
        if ((r11 instanceof Integer) == false) goto L175;
        return Long.valueOf(((Long) r9).longValue() << ((Integer) r11).intValue());
    L175:
        return Long.valueOf(((Long) r9).longValue() << ((int) ((Long) r11).longValue()));
    L177:
        if ((r11 instanceof Integer) == false) goto L181;
        return Long.valueOf(((Long) r9).longValue() >> ((Integer) r11).intValue());
    L181:
        return Long.valueOf(((Long) r9).longValue() >> ((int) ((Long) r11).longValue()));
    L183:
        if ((r11 instanceof Integer) == false) goto L187;
        return Long.valueOf(((Long) r9).longValue() ^ ((Integer) r11).intValue());
    L187:
        return Long.valueOf(((Long) r9).longValue() ^ ((Long) r11).longValue());
    L189:
        if ((r11 instanceof Integer) == false) goto L193;
        return Long.valueOf(((Long) r9).longValue() | ((Integer) r11).intValue());
    L193:
        return Long.valueOf(((Long) r9).longValue() | ((Long) r11).longValue());
    L195:
        if ((r11 instanceof Integer) == false) goto L199;
        return Long.valueOf(((Long) r9).longValue() & ((Integer) r11).intValue());
    L199:
        return Long.valueOf(((Long) r9).longValue() & ((Long) r11).longValue());
    L207:
        return Long.valueOf(toLong(r9) % toLong(r11));
    L209:
        return Double.valueOf(toDouble(r9) / toDouble(r11));
    L211:
        return Long.valueOf(toLong(r9) * toLong(r11));
    L213:
        return Long.valueOf(toLong(r9) - toLong(r11));
    L215:
        return Long.valueOf(toLong(r9) + toLong(r11));
    L291:
        switch(r10) {
            case 0: goto L474;
            case 1: goto L472;
            case 2: goto L470;
            case 3: goto L468;
            case 4: goto L466;
            case 5: goto L464;
            case 6: goto L461;
            case 7: goto L461;
            case 8: goto L461;
            case 9: goto L461;
            case 10: goto L461;
            case 11: goto L461;
            case 12: goto L292;
            case 13: goto L292;
            case 14: goto L454;
            case 15: goto L447;
            case 16: goto L440;
            case 17: goto L433;
            case 18: goto L424;
            case 19: goto L415;
            default: goto L292;
        };
    L415:
        if (r9 == null) goto L420;
        if (r11 == null) goto L420;
        if (toDouble(r9) == toDouble(r11)) goto L423;
    L419:
        r5 = true;
    L423:
        return Boolean.valueOf(r5);
    L420:
        if (r9 == r11) goto L423;
    L424:
        if (r9 == null) goto L429;
        if (r11 == null) goto L429;
        if (toDouble(r9) != toDouble(r11)) goto L432;
    L428:
        r5 = true;
    L432:
        return Boolean.valueOf(r5);
    L429:
        if (r9 != r11) goto L432;
    L433:
        if (r9 == null) goto L439;
        if (r11 == null) goto L439;
        if (toDouble(r9) < toDouble(r11)) goto L439;
        r5 = true;
    L439:
        return Boolean.valueOf(r5);
    L440:
        if (r9 == null) goto L446;
        if (r11 == null) goto L446;
        if (toDouble(r9) > toDouble(r11)) goto L446;
        r5 = true;
    L446:
        return Boolean.valueOf(r5);
    L447:
        if (r9 == null) goto L453;
        if (r11 == null) goto L453;
        if (toDouble(r9) <= toDouble(r11)) goto L453;
        r5 = true;
    L453:
        return Boolean.valueOf(r5);
    L454:
        if (r9 == null) goto L460;
        if (r11 == null) goto L460;
        if (toDouble(r9) >= toDouble(r11)) goto L460;
        r5 = true;
    L460:
        return Boolean.valueOf(r5);
    L461:
        GlShader$$ExternalSyntheticBUOutline1.m("bitwise operation on a non-fixed-point number.");
        return null;
    L464:
        return Double.valueOf(Math.pow(toDouble(r9), toDouble(r11)));
    L466:
        return Double.valueOf(toDouble(r9) % toDouble(r11));
    L468:
        return Double.valueOf(toDouble(r9) / toDouble(r11));
    L470:
        return Double.valueOf(toDouble(r9) * toDouble(r11));
    L472:
        return Double.valueOf(toDouble(r9) - toDouble(r11));
    L474:
        return Double.valueOf(toDouble(r9) + toDouble(r11));
    L292:
        switch(r10) {
            case 0: goto L414;
            case 1: goto L412;
            case 2: goto L410;
            case 3: goto L408;
            case 4: goto L406;
            case 5: goto L404;
            case 6: goto L401;
            case 7: goto L401;
            case 8: goto L401;
            case 9: goto L401;
            case 10: goto L401;
            case 11: goto L401;
            case 12: goto L293;
            case 13: goto L293;
            case 14: goto L394;
            case 15: goto L387;
            case 16: goto L380;
            case 17: goto L373;
            case 18: goto L364;
            case 19: goto L355;
            default: goto L293;
        };
    L355:
        if (r9 == null) goto L360;
        if (r11 == null) goto L360;
        if (toFloat(r9) == toFloat(r11)) goto L363;
    L359:
        r5 = true;
    L363:
        return Boolean.valueOf(r5);
    L360:
        if (r9 == r11) goto L363;
    L364:
        if (r9 == null) goto L369;
        if (r11 == null) goto L369;
        if (toFloat(r9) != toFloat(r11)) goto L372;
    L368:
        r5 = true;
    L372:
        return Boolean.valueOf(r5);
    L369:
        if (r9 != r11) goto L372;
    L373:
        if (r9 == null) goto L379;
        if (r11 == null) goto L379;
        if (toFloat(r9) < toFloat(r11)) goto L379;
        r5 = true;
    L379:
        return Boolean.valueOf(r5);
    L380:
        if (r9 == null) goto L386;
        if (r11 == null) goto L386;
        if (toFloat(r9) > toFloat(r11)) goto L386;
        r5 = true;
    L386:
        return Boolean.valueOf(r5);
    L387:
        if (r9 == null) goto L393;
        if (r11 == null) goto L393;
        if (toFloat(r9) <= toFloat(r11)) goto L393;
        r5 = true;
    L393:
        return Boolean.valueOf(r5);
    L394:
        if (r9 == null) goto L400;
        if (r11 == null) goto L400;
        if (toFloat(r9) >= toFloat(r11)) goto L400;
        r5 = true;
    L400:
        return Boolean.valueOf(r5);
    L401:
        GlShader$$ExternalSyntheticBUOutline1.m("bitwise operation on a non-fixed-point number.");
        return null;
    L404:
        return ParseTools.narrowType(asBigDecimal(r9).pow(((Number) r11).intValue(), MATH_CONTEXT), -1);
    L406:
        return Float.valueOf(toFloat(r9) % toFloat(r11));
    L408:
        return Double.valueOf(toDouble(r9) / toDouble(r11));
    L410:
        return Float.valueOf(toFloat(r9) * toFloat(r11));
    L412:
        return Float.valueOf(toFloat(r9) - toFloat(r11));
    L414:
        return Float.valueOf(toFloat(r9) + toFloat(r11));
    L290:
        Unit r92 = (Unit) r9;
        r11 = r92.convertFrom(r11);
        r9 = Double.valueOf(r92.getValue());
        goto L291
    }

    private static Object doPrimWrapperArithmetic(Number r3, int r4, Number r5, int r6) {
        if (r4 == 0) goto L64;
        boolean r0 = true;
        if (r4 == 1) goto L62;
        if (r4 == 2) goto L60;
        if (r4 == 3) goto L58;
        if (r4 == 4) goto L56;
        if (r4 == 5) goto L54;
        switch(r4) {
            case 14: goto L48;
            case 15: goto L42;
            case 16: goto L36;
            case 17: goto L30;
            case 18: goto L24;
            case 19: goto L18;
            default: goto L15;
        };
    L15:
        return null;
    L18:
        if (r3.doubleValue() != r5.doubleValue()) goto L22;
        r0 = false;
    L22:
        return Boolean.valueOf(r0);
    L24:
        if (r3.doubleValue() == r5.doubleValue()) goto L28;
        r0 = false;
    L28:
        return Boolean.valueOf(r0);
    L30:
        if (r3.doubleValue() >= r5.doubleValue()) goto L34;
        r0 = false;
    L34:
        return Boolean.valueOf(r0);
    L36:
        if (r3.doubleValue() <= r5.doubleValue()) goto L40;
        r0 = false;
    L40:
        return Boolean.valueOf(r0);
    L42:
        if (r3.doubleValue() > r5.doubleValue()) goto L46;
        r0 = false;
    L46:
        return Boolean.valueOf(r0);
    L48:
        if (r3.doubleValue() < r5.doubleValue()) goto L52;
        r0 = false;
    L52:
        return Boolean.valueOf(r0);
    L54:
        return toType(Double.valueOf(Math.pow(r3.doubleValue(), r5.doubleValue())), r6);
    L56:
        return toType(Double.valueOf(r3.doubleValue() % r5.doubleValue()), r6);
    L58:
        return toType(Double.valueOf(r3.doubleValue() / r5.doubleValue()), r6);
    L60:
        return toType(Double.valueOf(r3.doubleValue() * r5.doubleValue()), r6);
    L62:
        return toType(Double.valueOf(r3.doubleValue() - r5.doubleValue()), r6);
    L64:
        return toType(Double.valueOf(r3.doubleValue() + r5.doubleValue()), r6);
    }

    private static Double getNumber(Object r3, int r4) {
        double r0 = 0.0d;
        if (r3 == null) goto L41;
        if (r3 == BlankLiteral.INSTANCE) goto L41;
        if (r4 == 0) goto L35;
        if (r4 == 1) goto L33;
        if (r4 == 7) goto L28;
        if (r4 == 8) goto L26;
        if (r4 == 9) goto L24;
        if (r4 == 15) goto L28;
        switch(r4) {
            case 100: goto L22;
            case 101: goto L22;
            case 102: goto L22;
            case 103: goto L22;
            case 104: goto L22;
            case 105: goto L22;
            case 106: goto L22;
            case 107: goto L22;
            case 108: goto L22;
            case 109: goto L22;
            case 110: goto L22;
            case 111: goto L22;
            case 112: goto L26;
            case 113: goto L24;
            default: goto L19;
        };
    L19:
        StringBuilder r1 = new StringBuilder("cannot convert <");
        r1.append(r3);
        Class<?> r32 = r3.getClass();
        r1.append("> to a numeric type: ");
        r1.append(r32);
        r1.append(" [");
        r1.append(r4);
        r1.append("]");
        throw new RuntimeException(r1.toString());
    L22:
        return Double.valueOf(((Number) r3).doubleValue());
    L24:
        return Double.valueOf(((Byte) r3).doubleValue());
    L26:
        return Double.valueOf(Double.parseDouble(String.valueOf(r3)));
    L28:
        if (((Boolean) r3).booleanValue() == false) goto L31;
        r0 = 1.0d;
    L31:
        return Double.valueOf(r0);
    L33:
        return Double.valueOf(Double.parseDouble((String) r3));
    L35:
        if ((r3 instanceof Number) == false) goto L37;
        double r33 = ((Number) r3).doubleValue();
    L39:
        return Double.valueOf(r33);
    L37:
        r33 = Double.parseDouble((String) r3);
    L41:
        return Double.valueOf(0.0d);
    }

    private static boolean isIntegerType(int r1) {
        if (r1 != 101) goto L5;
        return true;
    L5:
        if (r1 != 106) goto L7;
        return true;
    L7:
        if (r1 != 102) goto L9;
        return true;
    L9:
        if (r1 == 107) goto L17;
        return false;
    L17:
        return true;
    }

    private static boolean isNumericOperation(int r1, Object r2, int r3, int r4, Object r5) {
        if (r1 <= 99) goto L5;
        if (r4 <= 99) goto L5;
        return true;
    L5:
        if (r3 == 0) goto L18;
        if (r1 > 99) goto L13;
        if (r4 > 99) goto L13;
        if (r3 < 14) goto L13;
        if (r3 > 17) goto L13;
        return false;
    L13:
        if (ParseTools.isNumber(r2) == true) goto L15;
        return false;
    L15:
        if (ParseTools.isNumber(r5) == false) goto L23;
        return true;
    L23:
        return false;
    L18:
        return false;
    }

    private static Boolean safeEquals(Object r0, Object r1) {
        if (r0 != null) goto L4;
        if (r1 != null) goto L7;
        boolean r02 = true;
    L9:
        return Boolean.valueOf(r02);
    L7:
        r02 = false;
        goto L9
    L4:
        return Boolean.valueOf(r0.equals(r1));
    }

    private static Boolean safeNotEquals(Object r1, Object r2) {
        boolean r0 = true;
        if (r1 != null) goto L5;
        if (r2 != null) goto L10;
        r0 = false;
    L10:
        return Boolean.valueOf(r0);
    L5:
        return Boolean.valueOf(!r1.equals(r2));
    }

    private static byte toByte(Object r1) {
        if ((r1 instanceof Byte) == false) goto L7;
        return ((Byte) r1).byteValue();
    L7:
        return ((Number) r1).byteValue();
    }

    private static char toChar(Object r0) {
        return ((Character) r0).charValue();
    }

    private static double toDouble(Object r2) {
        if ((r2 instanceof Double) == false) goto L7;
        return ((Double) r2).doubleValue();
    L7:
        return ((Number) r2).doubleValue();
    }

    private static float toFloat(Object r1) {
        if ((r1 instanceof Float) == false) goto L7;
        return ((Float) r1).floatValue();
    L7:
        return ((Number) r1).floatValue();
    }

    private static int toInteger(Object r1) {
        if ((r1 instanceof Integer) == false) goto L7;
        return ((Integer) r1).intValue();
    L7:
        return ((Number) r1).intValue();
    }

    private static long toLong(Object r2) {
        if ((r2 instanceof Long) == false) goto L7;
        return ((Long) r2).longValue();
    L7:
        return ((Number) r2).longValue();
    }

    private static short toShort(Object r1) {
        if ((r1 instanceof Short) == false) goto L7;
        return ((Short) r1).shortValue();
    L7:
        return ((Number) r1).shortValue();
    }

    private static Object toType(Number r2, int r3) {
        if (r3 == 1) goto L22;
        switch(r3) {
            case 100: goto L20;
            case 101: goto L18;
            case 102: goto L16;
            case 103: goto L14;
            case 104: goto L12;
            case 105: goto L20;
            case 106: goto L18;
            case 107: goto L16;
            case 108: goto L12;
            case 109: goto L14;
            case 110: goto L10;
            case 111: goto L8;
            default: goto L5;
        };
    L5:
        MathProcessor$$ExternalSyntheticBUOutline0.m("internal error: ", r3);
        return null;
    L8:
        return BigInteger.valueOf(r2.longValue());
    L10:
        return new BigDecimal(r2.doubleValue());
    L12:
        return Float.valueOf(r2.floatValue());
    L14:
        return Double.valueOf(r2.doubleValue());
    L16:
        return Long.valueOf(r2.longValue());
    L18:
        return Integer.valueOf(r2.intValue());
    L20:
        return Short.valueOf(r2.shortValue());
    L22:
        return Double.valueOf(r2.doubleValue());
    }
}
