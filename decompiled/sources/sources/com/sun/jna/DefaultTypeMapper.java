package com.sun.jna;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class DefaultTypeMapper implements TypeMapper {
    private List<Entry> fromNativeConverters;
    private List<Entry> toNativeConverters;

    public static class Entry {
        public Object converter;
        public Class<?> type;

        public Entry(Class<?> r1, Object r2) {
            this.type = r1;
            this.converter = r2;
        }
    }

    public DefaultTypeMapper() {
        this.toNativeConverters = new ArrayList();
        this.fromNativeConverters = new ArrayList();
    }

    private Class<?> getAltClass(Class<?> r2) {
        Class<?> r1 = Boolean.TYPE;
        if (r2 != Boolean.class) goto L5;
        return r1;
    L5:
        if (r2 != r1) goto L7;
        return Boolean.class;
    L7:
        Class<?> r12 = Byte.TYPE;
        if (r2 != Byte.class) goto L10;
        return r12;
    L10:
        if (r2 != r12) goto L12;
        return Byte.class;
    L12:
        Class<?> r13 = Character.TYPE;
        if (r2 != Character.class) goto L15;
        return r13;
    L15:
        if (r2 != r13) goto L17;
        return Character.class;
    L17:
        Class<?> r14 = Short.TYPE;
        if (r2 != Short.class) goto L20;
        return r14;
    L20:
        if (r2 != r14) goto L22;
        return Short.class;
    L22:
        Class<?> r15 = Integer.TYPE;
        if (r2 != Integer.class) goto L25;
        return r15;
    L25:
        if (r2 != r15) goto L27;
        return Integer.class;
    L27:
        Class<?> r16 = Long.TYPE;
        if (r2 != Long.class) goto L30;
        return r16;
    L30:
        if (r2 != r16) goto L32;
        return Long.class;
    L32:
        Class<?> r17 = Float.TYPE;
        if (r2 != Float.class) goto L35;
        return r17;
    L35:
        if (r2 != r17) goto L37;
        return Float.class;
    L37:
        Class<?> r18 = Double.TYPE;
        if (r2 != Double.class) goto L40;
        return r18;
    L40:
        if (r2 != r18) goto L42;
        return Double.class;
    L42:
        return null;
    }

    private Object lookupConverter(Class<?> r2, Collection<? extends Entry> r3) {
        Iterator<? extends Entry> r1 = r3.iterator();
    L4:
        if (r1.hasNext() == false) goto L9;
        Entry r32 = r1.next();
        if (r32.type.isAssignableFrom(r2) == false) goto L4;
        return r32.converter;
    L9:
        return null;
    }

    public void addFromNativeConverter(Class<?> r3, FromNativeConverter r4) {
        this.fromNativeConverters.add(new Entry(r3, r4));
        Class<?> r32 = getAltClass(r3);
        if (r32 == null) goto L6;
        this.fromNativeConverters.add(new Entry(r32, r4));
        return;
    }

    public void addToNativeConverter(Class<?> r3, ToNativeConverter r4) {
        this.toNativeConverters.add(new Entry(r3, r4));
        Class<?> r32 = getAltClass(r3);
        if (r32 == null) goto L6;
        this.toNativeConverters.add(new Entry(r32, r4));
        return;
    }

    public void addTypeConverter(Class<?> r1, TypeConverter r2) {
        addFromNativeConverter(r1, r2);
        addToNativeConverter(r1, r2);
    }

    @Override // com.sun.jna.TypeMapper
    public FromNativeConverter getFromNativeConverter(Class<?> r2) {
        return (FromNativeConverter) lookupConverter(r2, this.fromNativeConverters);
    }

    @Override // com.sun.jna.TypeMapper
    public ToNativeConverter getToNativeConverter(Class<?> r2) {
        return (ToNativeConverter) lookupConverter(r2, this.toNativeConverters);
    }
}
