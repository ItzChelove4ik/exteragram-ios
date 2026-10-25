package com.android.dx.command.annotool;

import com.android.dx.cf.attrib.AttRuntimeInvisibleAnnotations;
import com.android.dx.cf.attrib.AttRuntimeVisibleAnnotations;
import com.android.dx.cf.attrib.BaseAnnotations;
import com.android.dx.cf.direct.ClassPathOpener;
import com.android.dx.cf.direct.DirectClassFile;
import com.android.dx.cf.direct.StdAttributeFactory;
import com.android.dx.cf.iface.Attribute;
import com.android.dx.cf.iface.AttributeList;
import com.android.dx.command.annotool.Main;
import com.android.dx.rop.annotation.Annotation;
import com.android.dx.util.ByteArray;
import java.io.File;
import java.lang.annotation.ElementType;
import java.util.HashSet;
import java.util.Iterator;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
class AnnotationLister {
    private static final String PACKAGE_INFO = "package-info";
    private final Main.Arguments args;
    HashSet<String> matchInnerClassesOf;
    HashSet<String> matchPackages;

    /* renamed from: com.android.dx.command.annotool.AnnotationLister$1, reason: invalid class name */
    public class AnonymousClass1 implements ClassPathOpener.Consumer {
        final /* synthetic */ AnnotationLister this$0;

        public AnonymousClass1(AnnotationLister r1) {
            this.this$0 = r1;
        }

        @Override // com.android.dx.cf.direct.ClassPathOpener.Consumer
        public void onException(Exception r1) {
            throw new RuntimeException(r1);
        }

        @Override // com.android.dx.cf.direct.ClassPathOpener.Consumer
        public void onProcessArchiveStart(File r1) {
        }

        @Override // com.android.dx.cf.direct.ClassPathOpener.Consumer
        public boolean processFileBytes(String r4, long r5, byte[] r7) {
            if (r4.endsWith(".class") == true) goto L5;
            return true;
        L5:
            DirectClassFile r72 = new DirectClassFile(new ByteArray(r7), r4, true);
            r72.setAttributeFactory(StdAttributeFactory.THE_ONE);
            AttributeList r42 = r72.getAttributes();
            String r52 = r72.getThisClass().getClassType().getClassName();
            if (r52.endsWith(AnnotationLister.PACKAGE_INFO) == false) goto L14;
            Attribute r53 = r42.findFirst(AttRuntimeInvisibleAnnotations.ATTRIBUTE_NAME);
        L8:
            if (r53 == null) goto L10;
            AnnotationLister r2 = this.this$0;
            AnnotationLister.access$000(r2, r72, (BaseAnnotations) r53);
            r53 = r42.findNext(r53);
            goto L8
        L10:
            Attribute r54 = r42.findFirst(AttRuntimeVisibleAnnotations.ATTRIBUTE_NAME);
        L11:
            if (r54 == null) goto L25;
            AnnotationLister r1 = this.this$0;
            AnnotationLister.access$000(r1, r72, (BaseAnnotations) r54);
            r54 = r42.findNext(r54);
        L25:
            return true;
        L14:
            if (AnnotationLister.access$100(this.this$0, r52) == false) goto L16;
        L24:
            AnnotationLister.access$300(this.this$0, r72);
            goto L25
        L16:
            if (AnnotationLister.access$200(this.this$0, r52) == true) goto L24;
            Attribute r55 = r42.findFirst(AttRuntimeInvisibleAnnotations.ATTRIBUTE_NAME);
        L19:
            if (r55 == null) goto L21;
            AnnotationLister r22 = this.this$0;
            AnnotationLister.access$400(r22, r72, (BaseAnnotations) r55);
            r55 = r42.findNext(r55);
            goto L19
        L21:
            Attribute r56 = r42.findFirst(AttRuntimeVisibleAnnotations.ATTRIBUTE_NAME);
        L22:
            if (r56 == null) goto L25;
            AnnotationLister r12 = this.this$0;
            AnnotationLister.access$400(r12, r72, (BaseAnnotations) r56);
            r56 = r42.findNext(r56);
            goto L22
        }
    }

    /* renamed from: com.android.dx.command.annotool.AnnotationLister$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$android$dx$command$annotool$Main$PrintType = null;

        static {
            int[] r0 = new int[Main.PrintType.values().length];
            $SwitchMap$com$android$dx$command$annotool$Main$PrintType = r0;
            r0[Main.PrintType.CLASS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$com$android$dx$command$annotool$Main$PrintType[Main.PrintType.INNERCLASS.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$com$android$dx$command$annotool$Main$PrintType[Main.PrintType.METHOD.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$com$android$dx$command$annotool$Main$PrintType[Main.PrintType.PACKAGE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L11
            return;
        }
    }

    public AnnotationLister(Main.Arguments r2) {
        this.matchInnerClassesOf = new HashSet();
        this.matchPackages = new HashSet();
        this.args = r2;
    }

    public static /* synthetic */ void access$000(AnnotationLister r0, DirectClassFile r1, BaseAnnotations r2) {
        r0.visitPackageAnnotation(r1, r2);
    }

    public static /* synthetic */ boolean access$100(AnnotationLister r0, String r1) {
        return r0.isMatchingInnerClass(r1);
    }

    public static /* synthetic */ boolean access$200(AnnotationLister r0, String r1) {
        return r0.isMatchingPackage(r1);
    }

    public static /* synthetic */ void access$300(AnnotationLister r0, DirectClassFile r1) {
        r0.printMatch(r1);
    }

    public static /* synthetic */ void access$400(AnnotationLister r0, DirectClassFile r1, BaseAnnotations r2) {
        r0.visitClassAnnotation(r1, r2);
    }

    private boolean isMatchingInnerClass(String r3) {
    L2:
        int r0 = r3.lastIndexOf(36);
        if (r0 <= 0) goto L8;
        r3 = r3.substring(0, r0);
        if (this.matchInnerClassesOf.contains(r3) == false) goto L2;
        return true;
    L8:
        return false;
    }

    private boolean isMatchingPackage(String r3) {
        int r0 = r3.lastIndexOf(47);
        if (r0 != (-1)) goto L5;
        String r32 = _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return this.matchPackages.contains(r32);
    L5:
        r32 = r3.substring(0, r0);
        goto L7
    }

    private void printMatch(DirectClassFile r5) {
        Iterator<Main.PrintType> r0 = this.args.printTypes.iterator();
    L4:
        if (r0.hasNext() == false) goto L12;
        Main.PrintType r1 = r0.next();
        int r12 = AnonymousClass2.$SwitchMap$com$android$dx$command$annotool$Main$PrintType[r1.ordinal()];
        if (r12 != 1) goto L8;
        String r13 = r5.getThisClass().getClassType().getClassName().replace('/', '.');
        System.out.println(r13);
        goto L4
    L8:
        if (r12 != 2) goto L4;
        this.matchInnerClassesOf.add(r5.getThisClass().getClassType().getClassName());
        goto L4
    }

    private void printMatchPackage(String r5) {
        Iterator<Main.PrintType> r0 = this.args.printTypes.iterator();
    L4:
        if (r0.hasNext() == false) goto L16;
        Main.PrintType r1 = r0.next();
        int r12 = AnonymousClass2.$SwitchMap$com$android$dx$command$annotool$Main$PrintType[r1.ordinal()];
        if (r12 == 1) goto L15;
        if (r12 == 2) goto L15;
        if (r12 == 3) goto L15;
        if (r12 != 4) goto L4;
        System.out.println(r5.replace('/', '.'));
    L15:
        this.matchPackages.add(r5);
        goto L4
    }

    private void visitClassAnnotation(DirectClassFile r3, BaseAnnotations r4) {
        if (this.args.eTypes.contains(ElementType.TYPE) == false) goto L11;
        Iterator<Annotation> r42 = r4.getAnnotations().getAnnotations().iterator();
    L7:
        if (r42.hasNext() == false) goto L17;
        String r0 = r42.next().getType().getClassType().getClassName();
        if (this.args.aclass.equals(r0) == false) goto L7;
        printMatch(r3);
        goto L7
    L17:
        return;
    }

    private void visitPackageAnnotation(DirectClassFile r3, BaseAnnotations r4) {
        if (this.args.eTypes.contains(ElementType.PACKAGE) == false) goto L15;
        String r32 = r3.getThisClass().getClassType().getClassName();
        int r0 = r32.lastIndexOf(47);
        if (r0 != (-1)) goto L8;
        String r33 = _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        Iterator<Annotation> r42 = r4.getAnnotations().getAnnotations().iterator();
    L11:
        if (r42.hasNext() == false) goto L21;
        String r02 = r42.next().getType().getClassType().getClassName();
        if (this.args.aclass.equals(r02) == false) goto L11;
        printMatchPackage(r33);
        goto L11
    L21:
        return;
    L8:
        r33 = r32.substring(0, r0);
        goto L9
    }

    public void process() {
        String[] r0 = this.args.files;
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        new ClassPathOpener(r0[r2], true, new AnonymousClass1(this)).process();
        r2 = r2 + 1;
        goto L3
    }
}
