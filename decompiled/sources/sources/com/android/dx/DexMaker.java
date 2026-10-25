package com.android.dx;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.dex.DexOptions;
import com.android.dx.dex.code.DalvCode;
import com.android.dx.dex.code.RopTranslator;
import com.android.dx.dex.file.ClassDefItem;
import com.android.dx.dex.file.DexFile;
import com.android.dx.dex.file.EncodedField;
import com.android.dx.dex.file.EncodedMethod;
import com.android.dx.rop.code.RopMethod;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.StdTypeList;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.OkHttpClient$Builder$$ExternalSyntheticBUOutline0;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;
import okio.Buffer$$ExternalSyntheticBUOutline4;

/* loaded from: classes.dex */
public final class DexMaker {
    private static boolean didWarnBlacklistedMethods;
    private static boolean didWarnNonBaseDexClassLoader;
    private boolean markAsTrusted;
    private DexFile outputDex;
    private ClassLoader sharedClassLoader;
    private final Map<TypeId<?>, TypeDeclaration> types;

    public static class FieldDeclaration {
        private final int accessFlags;
        final FieldId<?, ?> fieldId;
        private final Object staticValue;

        public FieldDeclaration(FieldId<?, ?> r2, int r3, Object r4) {
            if ((r3 & 8) != 0) goto L8;
            if (r4 == null) goto L8;
            f$$ExternalSyntheticBUOutline1.m("instance fields may not have a value");
            throw null;
        L8:
            this.fieldId = r2;
            this.accessFlags = r3;
            this.staticValue = r4;
        }

        public static /* synthetic */ Object access$800(FieldDeclaration r0) {
            return r0.staticValue;
        }

        public boolean isStatic() {
            if ((this.accessFlags & 8) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        public EncodedField toEncodedField() {
            return new EncodedField(this.fieldId.constant, this.accessFlags);
        }
    }

    public static class MethodDeclaration {
        private final Code code;
        private final int flags;
        final MethodId<?, ?> method;

        public MethodDeclaration(MethodId<?, ?> r1, int r2) {
            this.method = r1;
            this.flags = r2;
            this.code = new Code(this);
        }

        public static /* synthetic */ Code access$600(MethodDeclaration r0) {
            return r0.code;
        }

        public boolean isDirect() {
            if ((this.flags & 65546) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean isStatic() {
            if ((this.flags & 8) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        public EncodedMethod toEncodedMethod(DexOptions r5) {
            int r0 = this.flags;
            if ((r0 & 1024) != 0) goto L10;
            if ((r0 & 256) != 0) goto L10;
            DalvCode r52 = RopTranslator.translate(new RopMethod(this.code.toBasicBlocks(), 0), 1, null, this.code.paramSize(), r5);
            return new EncodedMethod(this.method.constant, this.flags, r52, StdTypeList.EMPTY);
        L10:
            return new EncodedMethod(this.method.constant, r0, null, StdTypeList.EMPTY);
        }
    }

    public static class TypeDeclaration {
        private ClassDefItem classDefItem;
        private boolean declared;
        private final Map<FieldId, FieldDeclaration> fields;
        private int flags;
        private TypeList interfaces;
        private final Map<MethodId, MethodDeclaration> methods;
        private String sourceFile;
        private TypeId<?> supertype;
        private final TypeId<?> type;

        public TypeDeclaration(TypeId<?> r2) {
            this.fields = new LinkedHashMap();
            this.methods = new LinkedHashMap();
            this.type = r2;
        }

        public static /* synthetic */ boolean access$000(TypeDeclaration r0) {
            return r0.declared;
        }

        public static /* synthetic */ boolean access$002(TypeDeclaration r0, boolean r1) {
            r0.declared = r1;
            return r1;
        }

        public static /* synthetic */ int access$102(TypeDeclaration r0, int r1) {
            r0.flags = r1;
            return r1;
        }

        public static /* synthetic */ TypeId access$200(TypeDeclaration r0) {
            return r0.supertype;
        }

        public static /* synthetic */ TypeId access$202(TypeDeclaration r0, TypeId r1) {
            r0.supertype = r1;
            return r1;
        }

        public static /* synthetic */ String access$302(TypeDeclaration r0, String r1) {
            r0.sourceFile = r1;
            return r1;
        }

        public static /* synthetic */ TypeList access$400(TypeDeclaration r0) {
            return r0.interfaces;
        }

        public static /* synthetic */ TypeList access$402(TypeDeclaration r0, TypeList r1) {
            r0.interfaces = r1;
            return r1;
        }

        public static /* synthetic */ Map access$500(TypeDeclaration r0) {
            return r0.methods;
        }

        public static /* synthetic */ Map access$700(TypeDeclaration r0) {
            return r0.fields;
        }

        public ClassDefItem toClassDefItem() {
            if (this.declared == false) goto L22;
            DexOptions r0 = new DexOptions();
            r0.minSdkVersion = 13;
            CstType r3 = this.type.constant;
            if (this.classDefItem != null) goto L21;
            this.classDefItem = new ClassDefItem(r3, this.flags, this.supertype.constant, this.interfaces.ropTypes, new CstString(this.sourceFile));
            Iterator<MethodDeclaration> r1 = this.methods.values().iterator();
        L8:
            if (r1.hasNext() == false) goto L13;
            MethodDeclaration r2 = r1.next();
            EncodedMethod r32 = r2.toEncodedMethod(r0);
            boolean r22 = r2.isDirect();
            ClassDefItem r4 = this.classDefItem;
            if (r22 == true) goto L11;
            r4.addVirtualMethod(r32);
            goto L8
        L11:
            r4.addDirectMethod(r32);
            goto L8
        L13:
            Iterator<FieldDeclaration> r02 = this.fields.values().iterator();
        L15:
            if (r02.hasNext() == false) goto L21;
            FieldDeclaration r12 = r02.next();
            EncodedField r23 = r12.toEncodedField();
            boolean r33 = r12.isStatic();
            ClassDefItem r42 = this.classDefItem;
            if (r33 == true) goto L18;
            r42.addInstanceField(r23);
            goto L15
        L18:
            r42.addStaticField(r23, Constants.getConstant(FieldDeclaration.access$800(r12)));
        L21:
            return this.classDefItem;
        L22:
            StringBuilder r03 = new StringBuilder("Undeclared type ");
            r03.append(this.type);
            r03.append(" declares members: ");
            r03.append(this.fields.keySet());
            OkHttpClient$Builder$$ExternalSyntheticBUOutline0.m(r03, " ", this.methods.keySet());
            return null;
        }
    }

    public DexMaker() {
        this.types = new LinkedHashMap();
    }

    private ClassLoader generateClassLoader(File r11, File r12, ClassLoader r13) {
        ClassLoader r3 = this.sharedClassLoader;     // Catch: InvocationTargetException -> L21 IllegalAccessException -> L46 NoSuchMethodException -> L48 InstantiationException -> L50 ClassNotFoundException -> L54
        boolean r4 = false;
        if (r3 == null) goto L6;
        boolean r6 = true;
    L7:
        if (r13 != null) goto L12;
        if (r3 == null) goto L11;
        r13 = r3;
        goto L12
    L11:
        r13 = null;
    L12:
        Class<?> r32 = Class.forName("dalvik.system.BaseDexClassLoader");     // Catch: InvocationTargetException -> L21 IllegalAccessException -> L46 NoSuchMethodException -> L48 InstantiationException -> L50 ClassNotFoundException -> L54
        if (r6 == true) goto L15;
    L23:
        r4 = r6;
    L26:
        if (this.markAsTrusted == false) goto L41;
        Class r10 = Boolean.TYPE;
        if (r4 == false) goto L34;
        r13.getClass().getMethod("addDexPath", new Class[]{String.class, r10}).invoke(r13, new Object[]{r11.getPath(), Boolean.TRUE});     // Catch: InvocationTargetException -> L31 IllegalAccessException -> L46 NoSuchMethodException -> L48 InstantiationException -> L50 ClassNotFoundException -> L54
        return r13;
    L34:
        return (ClassLoader) r32.getConstructor(new Class[]{String.class, File.class, String.class, ClassLoader.class, r10}).newInstance(new Object[]{r11.getPath(), r12.getAbsoluteFile(), null, r13, Boolean.TRUE});
    L31:
        e = move-exception;
        if ((e.getCause() instanceof SecurityException) == true) goto L38;
        throw e;     // Catch: InvocationTargetException -> L21 IllegalAccessException -> L46 NoSuchMethodException -> L48 InstantiationException -> L50 ClassNotFoundException -> L54
    L38:
        if (didWarnBlacklistedMethods == true) goto L41;
        System.err.println("Cannot allow to call blacklisted super methods. This might break spying on system classes." + e.getCause());     // Catch: InvocationTargetException -> L21 IllegalAccessException -> L46 NoSuchMethodException -> L48 InstantiationException -> L50 ClassNotFoundException -> L54
        didWarnBlacklistedMethods = true;     // Catch: InvocationTargetException -> L21 IllegalAccessException -> L46 NoSuchMethodException -> L48 InstantiationException -> L50 ClassNotFoundException -> L54
    L41:
        if (r4 == false) goto L45;
        r13.getClass().getMethod("addDexPath", new Class[]{String.class}).invoke(r13, new Object[]{r11.getPath()});     // Catch: InvocationTargetException -> L21 IllegalAccessException -> L46 NoSuchMethodException -> L48 InstantiationException -> L50 ClassNotFoundException -> L54
        return r13;
    L45:
        return (ClassLoader) Class.forName("dalvik.system.DexClassLoader").getConstructor(new Class[]{String.class, String.class, String.class, ClassLoader.class}).newInstance(new Object[]{r11.getPath(), r12.getAbsolutePath(), null, r13});
    L15:
        if (r32.isAssignableFrom(r13.getClass()) == true) goto L23;
        if (r13.getClass().getName().equals("java.lang.BootClassLoader") == true) goto L26;
        if (didWarnNonBaseDexClassLoader == true) goto L26;
        System.err.println("Cannot share classloader as shared classloader '" + r13 + "' is not a subclass of '" + r32 + "'");     // Catch: InvocationTargetException -> L21 IllegalAccessException -> L46 NoSuchMethodException -> L48 InstantiationException -> L50 ClassNotFoundException -> L54
        didWarnNonBaseDexClassLoader = true;     // Catch: InvocationTargetException -> L21 IllegalAccessException -> L46 NoSuchMethodException -> L48 InstantiationException -> L50 ClassNotFoundException -> L54
        goto L26
    L6:
        r6 = false;
    L21:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e.getCause());
        return null;
    L54:
        e = move-exception;
        throw new UnsupportedOperationException("load() requires a Dalvik VM", e);
    L46:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return null;
    L50:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return null;
    L48:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    private String generateFileName() {
        Set<TypeId<?>> r0 = this.types.keySet();
        Iterator<TypeId<?>> r1 = r0.iterator();
        int r02 = r0.size();
        int[] r2 = new int[r02];
        int r3 = 0;
        int r4 = 0;
    L4:
        if (r1.hasNext() == false) goto L8;
        TypeDeclaration r5 = getTypeDeclaration(r1.next());
        Set r6 = TypeDeclaration.access$500(r5).keySet();
        if (TypeDeclaration.access$200(r5) == null) goto L4;
        r2[r4] = (((TypeDeclaration.access$200(r5).hashCode() * 31) + TypeDeclaration.access$400(r5).hashCode()) * 31) + r6.hashCode();
        r4 = r4 + 1;
        goto L4
    L8:
        Arrays.sort(r2);
        int r8 = 1;
    L9:
        if (r3 >= r02) goto L12;
        r8 = (r8 * 31) + r2[r3];
        r3 = r3 + 1;
        goto L9
    L12:
        return "Generated_" + r8 + ".jar";
    }

    public Code declare(MethodId<?, ?> r3, int r4) {
        TypeDeclaration r2 = getTypeDeclaration(r3.declaringType);
        if (TypeDeclaration.access$500(r2).containsKey(r3) == false) goto L5;
        DexMaker$$ExternalSyntheticBUOutline0.m("already declared: ", r3);
        return null;
    L5:
        if ((r4 & (-5504)) == 0) goto L7;
        Buffer$$ExternalSyntheticBUOutline4.m("Unexpected flag: ", Integer.toHexString(r4));
        return null;
    L7:
        if ((r4 & 32) == 0) goto L10;
        r4 = (r4 & (-33)) | 131072;
    L10:
        if (r3.isConstructor() == false) goto L12;
    L13:
        r4 = r4 | 65536;
    L14:
        MethodDeclaration r0 = new MethodDeclaration(r3, r4);
        TypeDeclaration.access$500(r2).put(r3, r0);
        return MethodDeclaration.access$600(r0);
    L12:
        if (r3.isStaticInitializer() == false) goto L14;
        goto L13
    }

    public void declare(FieldId<?, ?> r2, int r3, Object r4) {
        TypeDeclaration r1 = getTypeDeclaration(r2.declaringType);
        if (TypeDeclaration.access$700(r1).containsKey(r2) == false) goto L5;
        DexMaker$$ExternalSyntheticBUOutline0.m("already declared: ", r2);
        return;
    L5:
        if ((r3 & (-4320)) == 0) goto L7;
        Buffer$$ExternalSyntheticBUOutline4.m("Unexpected flag: ", Integer.toHexString(r3));
        return;
    L7:
        if ((r3 & 8) != 0) goto L12;
        if (r4 == null) goto L12;
        f$$ExternalSyntheticBUOutline1.m("staticValue is non-null, but field is not static");
        return;
    L12:
        FieldDeclaration r0 = new FieldDeclaration(r2, r3, r4);
        TypeDeclaration.access$700(r1).put(r2, r0);
    }

    public void declare(TypeId<?> r2, String r3, int r4, TypeId<?> r5, TypeId<?>... r6) {
        TypeDeclaration r1 = getTypeDeclaration(r2);
        if ((r4 & (-5138)) == 0) goto L5;
        Buffer$$ExternalSyntheticBUOutline4.m("Unexpected flag: ", Integer.toHexString(r4));
        return;
    L5:
        if (TypeDeclaration.access$000(r1) == true) goto L8;
        TypeDeclaration.access$002(r1, true);
        TypeDeclaration.access$102(r1, r4);
        TypeDeclaration.access$202(r1, r5);
        TypeDeclaration.access$302(r1, r3);
        TypeDeclaration.access$402(r1, new TypeList(r6));
        return;
    L8:
        DexMaker$$ExternalSyntheticBUOutline0.m("already declared: ", r2);
    }

    public byte[] generate() {
        if (this.outputDex != null) goto L5;
        DexOptions r0 = new DexOptions();
        r0.minSdkVersion = 13;
        this.outputDex = new DexFile(r0);
    L5:
        Iterator<TypeDeclaration> r02 = this.types.values().iterator();
    L7:
        if (r02.hasNext() == false) goto L15;
        TypeDeclaration r1 = r02.next();
        this.outputDex.add(r1.toClassDefItem());
        goto L7
    L15:
        return this.outputDex.toDex(null, false);
    L12:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public ClassLoader generateAndLoad(ClassLoader r7, File r8) {
        if (r8 != null) goto L11;
        String r82 = System.getProperty("dexmaker.dexcache");
        if (r82 == null) goto L6;
        r8 = new File(r82);
        goto L11
    L6:
        r8 = new AppDataDirGuesser().guess();
        if (r8 != null) goto L11;
        f$$ExternalSyntheticBUOutline1.m("dexcache == null (and no default could be found; consider setting the 'dexmaker.dexcache' system property)");
        return null;
    L11:
        File r0 = new File(r8, generateFileName());
        if (r0.exists() == true) goto L14;
    L18:
        byte[] r1 = generate();
        JarOutputStream r2 = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(r0)));
        r0.setReadOnly();
        JarEntry r3 = new JarEntry("classes.dex");     // Catch: Throwable -> L24
        r3.setSize(r1.length);     // Catch: Throwable -> L24
        r2.putNextEntry(r3);     // Catch: Throwable -> L24
        r2.write(r1);     // Catch: Throwable -> L26
        r2.closeEntry();     // Catch: Throwable -> L24
        r2.close();
        return generateClassLoader(r0, r8, r7);
    L26:
        th = move-exception;
        r2.closeEntry();     // Catch: Throwable -> L24
        throw th;     // Catch: Throwable -> L24
    L24:
        th = move-exception;
        r2.close();
        throw th;
    L14:
        if (r0.canWrite() == false) goto L16;
        r0.delete();
        goto L18
    L16:
        return generateClassLoader(r0, r8, r7);
    }

    public DexFile getDexFile() {
        if (this.outputDex != null) goto L6;
        DexOptions r0 = new DexOptions();
        r0.minSdkVersion = 13;
        this.outputDex = new DexFile(r0);
    L6:
        return this.outputDex;
    }

    public TypeDeclaration getTypeDeclaration(TypeId<?> r2) {
        TypeDeclaration r0 = this.types.get(r2);
        if (r0 != null) goto L6;
        TypeDeclaration r02 = new TypeDeclaration(r2);
        this.types.put(r2, r02);
        return r02;
    L6:
        return r0;
    }

    public void markAsTrusted() {
        this.markAsTrusted = true;
    }

    public void setSharedClassLoader(ClassLoader r1) {
        this.sharedClassLoader = r1;
    }
}
