package androidx.core.graphics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import com.google.android.gms.cast.MediaError;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
abstract class TypefaceCompatBaseImpl {

    @SuppressLint({"BanConcurrentHashMap"})
    private ConcurrentHashMap<Long, FontResourcesParserCompat.FontFamilyFilesResourceEntry> mFontFamilies;

    /* renamed from: androidx.core.graphics.TypefaceCompatBaseImpl$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass1 implements StyleExtractor<FontsContractCompat.FontInfo> {
        final /* synthetic */ TypefaceCompatBaseImpl this$0;

        public AnonymousClass1(TypefaceCompatBaseImpl r1) {
            this.this$0 = r1;
        }

        /* renamed from: getWeight, reason: avoid collision after fix types in other method */
        public int getWeight2(FontsContractCompat.FontInfo r1) {
            return r1.getWeight();
        }

        @Override // androidx.core.graphics.TypefaceCompatBaseImpl.StyleExtractor
        public /* bridge */ /* synthetic */ int getWeight(FontsContractCompat.FontInfo r1) {
            return getWeight2(r1);
        }

        /* renamed from: isItalic, reason: avoid collision after fix types in other method */
        public boolean isItalic2(FontsContractCompat.FontInfo r1) {
            return r1.isItalic();
        }

        @Override // androidx.core.graphics.TypefaceCompatBaseImpl.StyleExtractor
        public /* bridge */ /* synthetic */ boolean isItalic(FontsContractCompat.FontInfo r1) {
            return isItalic2(r1);
        }
    }

    /* loaded from: classes3.dex */
    public interface StyleExtractor<T> {
        int getWeight(T r1);

        boolean isItalic(T r1);
    }

    public TypefaceCompatBaseImpl() {
        this.mFontFamilies = new ConcurrentHashMap();
    }

    private static <T> T findBestFont(T[] r1, int r2, StyleExtractor<T> r3) {
        if ((r2 & 1) != 0) goto L5;
        int r0 = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
    L7:
        if ((r2 & 2) == 0) goto L9;
        boolean r22 = true;
    L11:
        return (T) findBestFont(r1, r0, r22, r3);
    L9:
        r22 = false;
        goto L11
    L5:
        r0 = 700;
        goto L7
    }

    private static <T> T findBestFont(T[] r8, int r9, boolean r10, StyleExtractor<T> r11) {
        int r0 = r8.length;
        T r1 = null;
        int r2 = Integer.MAX_VALUE;
        int r4 = 0;
    L3:
        if (r4 >= r0) goto L13;
        T r5 = r8[r4];
        int r6 = Math.abs(r11.getWeight(r5) - r9) * 2;
        if (r11.isItalic(r5) != r10) goto L7;
        int r7 = 0;
    L8:
        int r62 = r6 + r7;
        if (r1 == null) goto L11;
        if (r2 > r62) goto L11;
    L12:
        r4 = r4 + 1;
    L11:
        r1 = r5;
        r2 = r62;
        goto L12
    L7:
        r7 = 1;
        goto L8
    L13:
        return r1;
    }

    public abstract Typeface createFromFontFamilyFilesResourceEntry(Context r1, FontResourcesParserCompat.FontFamilyFilesResourceEntry r2, Resources r3, int r4);

    public abstract Typeface createFromFontInfo(Context r1, CancellationSignal r2, FontsContractCompat.FontInfo[] r3, int r4);

    public Typeface createFromFontInfoWithFallback(Context r1, CancellationSignal r2, List<FontsContractCompat.FontInfo[]> r3, int r4) {
        throw new IllegalStateException("createFromFontInfoWithFallback must only be called on API 29+");
    }

    public Typeface createFromInputStream(Context r1, InputStream r2) {
        /*  JADX ERROR: Simple mode code generation failed
            java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
            	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
            	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
            	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
            	at java.base/java.util.Objects.checkIndex(Objects.java:385)
            	at java.base/java.util.ArrayList.get(ArrayList.java:427)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:361)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            */
        /*
            this = this;
            java.io.File r0 = androidx.core.graphics.TypefaceCompatUtil.getTempFile(r1)
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            boolean r2 = androidx.core.graphics.TypefaceCompatUtil.copyToFile(r0, r2)     // Catch: java.lang.Throwable -> L1e java.lang.RuntimeException -> L23
            if (r2 != 0) goto L12
            r0.delete()
            return r1
        L12:
            java.lang.String r2 = r0.getPath()     // Catch: java.lang.Throwable -> L1e java.lang.RuntimeException -> L23
            android.graphics.Typeface r1 = android.graphics.Typeface.createFromFile(r2)     // Catch: java.lang.Throwable -> L1e java.lang.RuntimeException -> L23
            r0.delete()
            return r1
        L1e:
            r1 = move-exception
            r0.delete()
            throw r1
        L23:
            r0.delete()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.TypefaceCompatBaseImpl.createFromInputStream(android.content.Context, java.io.InputStream):android.graphics.Typeface");
    }

    public Typeface createFromResourcesFontFile(Context r1, Resources r2, int r3, String r4, int r5) {
        /*  JADX ERROR: Simple mode code generation failed
            java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
            	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
            	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
            	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
            	at java.base/java.util.Objects.checkIndex(Objects.java:385)
            	at java.base/java.util.ArrayList.get(ArrayList.java:427)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:361)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            */
        /*
            this = this;
            java.io.File r0 = androidx.core.graphics.TypefaceCompatUtil.getTempFile(r1)
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            boolean r2 = androidx.core.graphics.TypefaceCompatUtil.copyToFile(r0, r2, r3)     // Catch: java.lang.Throwable -> L1e java.lang.RuntimeException -> L23
            if (r2 != 0) goto L12
            r0.delete()
            return r1
        L12:
            java.lang.String r2 = r0.getPath()     // Catch: java.lang.Throwable -> L1e java.lang.RuntimeException -> L23
            android.graphics.Typeface r1 = android.graphics.Typeface.createFromFile(r2)     // Catch: java.lang.Throwable -> L1e java.lang.RuntimeException -> L23
            r0.delete()
            return r1
        L1e:
            r1 = move-exception
            r0.delete()
            throw r1
        L23:
            r0.delete()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.TypefaceCompatBaseImpl.createFromResourcesFontFile(android.content.Context, android.content.res.Resources, int, java.lang.String, int):android.graphics.Typeface");
    }

    public FontsContractCompat.FontInfo findBestInfo(FontsContractCompat.FontInfo[] r2, int r3) {
        return (FontsContractCompat.FontInfo) findBestFont(r2, r3, new AnonymousClass1(this));
    }
}
