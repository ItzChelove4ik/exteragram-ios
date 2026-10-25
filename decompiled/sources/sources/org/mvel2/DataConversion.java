package org.mvel2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.mvel2.conversion.ArrayHandler;
import org.mvel2.conversion.BigDecimalCH;
import org.mvel2.conversion.BigIntegerCH;
import org.mvel2.conversion.BooleanCH;
import org.mvel2.conversion.ByteCH;
import org.mvel2.conversion.CharArrayCH;
import org.mvel2.conversion.CharCH;
import org.mvel2.conversion.CompositeCH;
import org.mvel2.conversion.DoubleCH;
import org.mvel2.conversion.FloatCH;
import org.mvel2.conversion.IntArrayCH;
import org.mvel2.conversion.IntegerCH;
import org.mvel2.conversion.ListCH;
import org.mvel2.conversion.LongCH;
import org.mvel2.conversion.ObjectCH;
import org.mvel2.conversion.SetCH;
import org.mvel2.conversion.ShortCH;
import org.mvel2.conversion.StringArrayCH;
import org.mvel2.conversion.StringCH;
import org.mvel2.util.FastList;
import org.mvel2.util.ReflectionUtil;

/* loaded from: classes5.dex */
public class DataConversion {
    private static final Map<Class, ConversionHandler> CONVERTERS = null;

    public interface ArrayTypeMarker {
    }

    static {
        HashMap r0 = new HashMap(76, 0.5f);
        CONVERTERS = r0;
        IntegerCH r1 = new IntegerCH();
        r0.put(Integer.class, r1);
        r0.put(Integer.TYPE, r1);
        ShortCH r12 = new ShortCH();
        r0.put(Short.class, r12);
        r0.put(Short.TYPE, r12);
        LongCH r13 = new LongCH();
        r0.put(Long.class, r13);
        r0.put(Long.TYPE, r13);
        CharCH r14 = new CharCH();
        r0.put(Character.class, r14);
        r0.put(Character.TYPE, r14);
        ByteCH r15 = new ByteCH();
        r0.put(Byte.class, r15);
        r0.put(Byte.TYPE, r15);
        FloatCH r16 = new FloatCH();
        r0.put(Float.class, r16);
        r0.put(Float.TYPE, r16);
        DoubleCH r17 = new DoubleCH();
        r0.put(Double.class, r17);
        r0.put(Double.TYPE, r17);
        BooleanCH r18 = new BooleanCH();
        r0.put(Boolean.class, r18);
        r0.put(Boolean.TYPE, r18);
        r0.put(String.class, new StringCH());
        r0.put(Object.class, new ObjectCH());
        CharArrayCH r19 = new CharArrayCH();
        r0.put(Character[].class, r19);
        r0.put(char[].class, new CompositeCH(new ConversionHandler[]{r19, new ArrayHandler(char[].class)}));
        r0.put(String[].class, new StringArrayCH());
        r0.put(Integer[].class, new IntArrayCH());
        r0.put(int[].class, new ArrayHandler(int[].class));
        r0.put(long[].class, new ArrayHandler(long[].class));
        r0.put(double[].class, new ArrayHandler(double[].class));
        r0.put(float[].class, new ArrayHandler(float[].class));
        r0.put(short[].class, new ArrayHandler(short[].class));
        r0.put(boolean[].class, new ArrayHandler(boolean[].class));
        r0.put(byte[].class, new ArrayHandler(byte[].class));
        r0.put(BigDecimal.class, new BigDecimalCH());
        r0.put(BigInteger.class, new BigIntegerCH());
        ListCH r110 = new ListCH();
        r0.put(List.class, r110);
        r0.put(FastList.class, r110);
        r0.put(ArrayList.class, r110);
        r0.put(LinkedList.class, r110);
        SetCH r111 = new SetCH();
        r0.put(Set.class, r111);
        r0.put(HashSet.class, r111);
        r0.put(LinkedHashSet.class, r111);
        r0.put(TreeSet.class, r111);
    }

    public DataConversion() {
    }

    public static void addConversionHandler(Class r1, ConversionHandler r2) {
        CONVERTERS.put(r1, r2);
    }

    public static boolean canConvert(Class r3, Class r4) {
        if (ReflectionUtil.isAssignableFrom(r3, r4) == false) goto L5;
        return true;
    L5:
        Map<Class, ConversionHandler> r0 = CONVERTERS;
        if (r0.containsKey(r3) == false) goto L10;
        return r0.get(r3).canConvertFrom(ReflectionUtil.toNonPrimitiveType(r4));
    L10:
        if (r3.isArray() == true) goto L12;
        return false;
    L12:
        if (canConvert(r3.getComponentType(), r4) == false) goto L16;
        return true;
    L16:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T convert(Object r3, Class<T> r4) {
        if (r3 != 0) goto L6;
        return null;
    L6:
        if (r4 != r3.getClass()) goto L8;
        return r3;
    L8:
        if (r4.isAssignableFrom(r3.getClass()) == true) goto L19;
        Map<Class, ConversionHandler> r0 = CONVERTERS;
        ConversionHandler r1 = r0.get(r4);
        if (r1 != null) goto L17;
        if (r4.isArray() == false) goto L17;
        ArrayHandler r12 = new ArrayHandler(r4);
        r0.put(r4, r12);
        return (T) r12.convertFrom(r3);
    L17:
        return (T) r1.convertFrom(r3);
    L19:
        return r3;
    }

    public static void main(String[] r1) {
        System.out.println(char[][].class);
    }
}
