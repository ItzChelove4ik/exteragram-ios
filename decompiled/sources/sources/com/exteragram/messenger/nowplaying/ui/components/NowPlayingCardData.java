package com.exteragram.messenger.nowplaying.ui.components;

import android.graphics.Bitmap;
import androidx.core.graphics.ColorUtils;
import androidx.palette.graphics.Palette;
import com.exteragram.messenger.api.dto.NowPlayingDTO;
import com.exteragram.messenger.utils.ui.UIUtil;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0017\b\u0086\b\u0018\u0000 *2\u00020\u0001:\u0002+*BC\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b\u001e\u0010\u001dR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001f\u001a\u0004\b \u0010!R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\"\u001a\u0004\b#\u0010$R\"\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u0006,"}, d2 = {"Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/api/dto/NowPlayingDTO;", "nowPlayingDTO", _UrlKt.FRAGMENT_ENCODE_SET, "backgroundColor", "accentColor", "Landroid/graphics/Bitmap;", "coverBitmap", "Lorg/telegram/messenger/ImageLocation;", "imageLocation", _UrlKt.FRAGMENT_ENCODE_SET, "userEmoji", "<init>", "(Lcom/exteragram/messenger/api/dto/NowPlayingDTO;Ljava/lang/Integer;Ljava/lang/Integer;Landroid/graphics/Bitmap;Lorg/telegram/messenger/ImageLocation;J)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Lcom/exteragram/messenger/api/dto/NowPlayingDTO;", "getNowPlayingDTO", "()Lcom/exteragram/messenger/api/dto/NowPlayingDTO;", "Ljava/lang/Integer;", "getBackgroundColor", "()Ljava/lang/Integer;", "getAccentColor", "Landroid/graphics/Bitmap;", "getCoverBitmap", "()Landroid/graphics/Bitmap;", "Lorg/telegram/messenger/ImageLocation;", "getImageLocation", "()Lorg/telegram/messenger/ImageLocation;", "J", "getUserEmoji", "()J", "setUserEmoji", "(J)V", "Companion", "Callback", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class NowPlayingCardData {
    public static final Companion Companion = null;
    private final Integer accentColor;
    private final Integer backgroundColor;
    private final Bitmap coverBitmap;
    private final ImageLocation imageLocation;
    private final NowPlayingDTO nowPlayingDTO;
    private long userEmoji;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData$Callback;", _UrlKt.FRAGMENT_ENCODE_SET, "onDataLoaded", _UrlKt.FRAGMENT_ENCODE_SET, "data", "Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Callback {
        void onDataLoaded(NowPlayingCardData r1);
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\"\u0010\f\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002¨\u0006\u0011"}, d2 = {"Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "create", _UrlKt.FRAGMENT_ENCODE_SET, "nowPlayingDTO", "Lcom/exteragram/messenger/api/dto/NowPlayingDTO;", "savedMusic", "Lorg/telegram/tgnet/TLRPC$Document;", com.sun.jna.Callback.METHOD_NAME, "Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData$Callback;", "extractColors", "Lkotlin/Pair;", _UrlKt.FRAGMENT_ENCODE_SET, "bitmap", "Landroid/graphics/Bitmap;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public static void $r8$lambda$2u7j2oskFsvnNiuPoirbvlLqfvc(NowPlayingDTO r10, Integer r11, Integer r12, Bitmap r13, ImageLocation r14, AtomicBoolean r15, Ref.ObjectRef r16, ImageReceiver r17, Callback r18) {
            create$finish(r15, r16, r17, r18, new NowPlayingCardData(r10, r11, r12, r13, r14, 0, 32, null));
        }

        public static void $r8$lambda$6P9lS5wgcdlqEm8f33JBkYmM3QQ(Bitmap r11, NowPlayingDTO r12, ImageLocation r13, AtomicBoolean r14, Ref.ObjectRef r15, ImageReceiver r16, Callback r17) {
            Pair<Integer, Integer> r0 = NowPlayingCardData.Companion.extractColors(r11);
            AndroidUtilities.runOnUIThread(new NowPlayingCardData$Companion$$ExternalSyntheticLambda5(r12, r0.component1(), r0.component2(), r11, r13, r14, r15, r16, r17));
        }

        /* renamed from: $r8$lambda$BLCb8OxhFAr78-ldS5qeufW-04M, reason: not valid java name */
        public static void m1509$r8$lambda$BLCb8OxhFAr78ldS5qeufW04M(NowPlayingDTO r0, ImageLocation r1, AtomicBoolean r2, Ref.ObjectRef r3, ImageReceiver r4, Callback r5, ImageReceiver r6, boolean r7, boolean r8, boolean r9) {
            if (r7 == false) goto L6;
            if (r8 == true) goto L7;
            Bitmap r62 = r6.getBitmap();
            Utilities.themeQueue.postRunnable(new NowPlayingCardData$Companion$$ExternalSyntheticLambda4(r62, r0, r1, r2, r3, r4, r5));
            return;
        L7:
            return;
        }

        /* renamed from: $r8$lambda$CkJytQz-mKuKqumf6IFQhPZcbKY, reason: not valid java name */
        public static void m1510$r8$lambda$CkJytQzmKuKqumf6IFQhPZcbKY(Callback r10, NowPlayingDTO r11) {
            r10.onDataLoaded(new NowPlayingCardData(r11, null, null, null, null, 0, 32, null));
        }

        public static void $r8$lambda$X_wQNOTpfLny2GfXbMGJYqF5qRY(NowPlayingDTO r10, ImageLocation r11, AtomicBoolean r12, Ref.ObjectRef r13, ImageReceiver r14, Callback r15) {
            create$finish(r12, r13, r14, r15, new NowPlayingCardData(r10, null, null, null, r11, 0, 32, null));
        }

        public static void $r8$lambda$jgxY1JuRy8LY2jKCm85wpHU4Lj4(ImageReceiver r8, Ref.ObjectRef r9, ImageLocation r10, NowPlayingDTO r11, AtomicBoolean r12, Callback r13) {
            r8.onAttachedToWindow();
            r8.setDelegate(new NowPlayingCardData$Companion$$ExternalSyntheticLambda3(r11, r10, r12, r9, r8, r13));
            T r82 = r9.element;
            if (r82 != 0) goto L5;
            Runnable r83 = null;
        L6:
            AndroidUtilities.runOnUIThread(r83, 15000);
            r8.setImage(r10, null, null, null, null, 0);
            return;
        L5:
            r83 = (Runnable) r82;
            goto L6
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private static final void create$finish(AtomicBoolean r2, Ref.ObjectRef<Runnable> r3, ImageReceiver r4, Callback r5, NowPlayingCardData r6) {
            if (r2.compareAndSet(false, true) == true) goto L5;
            return;
        L5:
            Runnable r22 = r3.element;
            if (r22 != null) goto L8;
            Runnable r23 = null;
        L9:
            AndroidUtilities.cancelRunOnUIThread(r23);
            r4.setDelegate(null);
            r4.onDetachedFromWindow();
            r5.onDataLoaded(r6);
            return;
        L8:
            r23 = r22;
            goto L9
        }

        private final Pair<Integer, Integer> extractColors(Bitmap r9) {
            Integer r8 = null;
            if (r9 == null) goto L5;
            Palette r0 = Palette.from(r9).generate();
            Palette.Swatch r1 = r0.getDarkVibrantSwatch();
            if (r1 == null) goto L10;
            int r82 = r1.getRgb();
        L9:
            int r12 = r82;
            double r2 = ColorUtils.calculateContrast(-1, r12);
            if (r2 <= 15.0d) goto L29;
            r12 = UIUtil.adjustHsl$default(UIUtil.INSTANCE, r12, 2.0f, 0.0f, 4, null);
        L32:
            if (ColorUtils.calculateContrast(-1, r12) >= 3.0d) goto L34;
            r12 = ColorUtils.blendARGB(r12, -16777216, 0.3f);
        L34:
            int r3 = r12;
            float[] r83 = new float[3];
            ColorUtils.colorToHSL(r3, r83);
            float r84 = r83[2];
            if (0.0f > r84) goto L41;
            if (r84 > 0.25f) goto L41;
            float r85 = 2.0f;
        L39:
            float r4 = r85;
        L52:
            return TuplesKt.to(Integer.valueOf(r3), Integer.valueOf(UIUtil.adjustHsl$default(UIUtil.INSTANCE, r3, r4, 0.0f, 4, null)));
        L41:
            if (0.25f > r84) goto L46;
            if (r84 > 0.5f) goto L46;
            r85 = 1.5f;
        L46:
            if (0.5f <= r84) goto L48;
        L50:
            r4 = 0.5f;
            goto L52
        L48:
            if (r84 > 0.75f) goto L50;
            r85 = 1.0f;
            goto L39
        L29:
            if (r2 >= 10.0d) goto L32;
            r12 = UIUtil.adjustHsl$default(UIUtil.INSTANCE, r12, 0.5f, 0.0f, 4, null);
            goto L32
        L10:
            Palette.Swatch r13 = r0.getMutedSwatch();
            if (r13 == null) goto L13;
            r82 = r13.getRgb();
            goto L9
        L13:
            Palette.Swatch r14 = r0.getDarkMutedSwatch();
            if (r14 == null) goto L16;
            Integer r15 = Integer.valueOf(r14.getRgb());
        L17:
            if (r15 == null) goto L19;
            r82 = r15.intValue();
            goto L9
        L19:
            Palette.Swatch r02 = r0.getDominantSwatch();
            if (r02 == null) goto L22;
            r8 = Integer.valueOf(r02.getRgb());
        L22:
            if (r8 == null) goto L24;
            r82 = r8.intValue();
            goto L9
        L24:
            r82 = AndroidUtilities.getDominantColor(r9);
            goto L9
        L16:
            r15 = null;
            goto L17
        L5:
            return TuplesKt.to(null, null);
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [T, com.exteragram.messenger.nowplaying.ui.components.NowPlayingCardData$Companion$$ExternalSyntheticLambda1] */
        @JvmStatic
        public final void create(NowPlayingDTO r10, TLRPC.Document r11, Callback r12) {
            if (Intrinsics.areEqual(r10.getPlatform(), "TELEGRAM") == false) goto L8;
            if (r11 == null) goto L8;
            TLRPC.PhotoSize r9 = FileLoader.getClosestPhotoSizeWithSize(r11.thumbs, 1000);
            if (r9 == null) goto L8;
            ImageLocation r92 = ImageLocation.getForDocument(r9, r11);
        L9:
            if (r92 != null) goto L16;
            String r112 = r10.getCoverUrl();
            if (r112 == null) goto L16;
            if (r112.length() == 0) goto L16;
            r92 = ImageLocation.getForPath(r10.getCoverUrl());
        L16:
            ImageLocation r3 = r92;
            if (r3 != null) goto L20;
            AndroidUtilities.runOnUIThread(new NowPlayingCardData$Companion$$ExternalSyntheticLambda0(r12, r10));
            return;
        L20:
            ImageReceiver r2 = new ImageReceiver(null);
            AtomicBoolean r4 = new AtomicBoolean(false);
            Ref.ObjectRef r5 = new Ref.ObjectRef();
            r5.element = new NowPlayingCardData$Companion$$ExternalSyntheticLambda1(r10, r3, r4, r5, r2, r12);
            AndroidUtilities.runOnUIThread(new NowPlayingCardData$Companion$$ExternalSyntheticLambda2(r2, r5, r3, r10, r4, r12));
            return;
        L8:
            r92 = null;
            goto L9
        }
    }

    static {
        Companion = new Companion(null);
    }

    public NowPlayingCardData(NowPlayingDTO r1, Integer r2, Integer r3, Bitmap r4, ImageLocation r5, long r6) {
        this.nowPlayingDTO = r1;
        this.backgroundColor = r2;
        this.accentColor = r3;
        this.coverBitmap = r4;
        this.imageLocation = r5;
        this.userEmoji = r6;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.getPhiList()" because "resultVar" is null
        	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:119)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:82)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    public /* synthetic */ NowPlayingCardData(com.exteragram.messenger.api.dto.NowPlayingDTO r9, java.lang.Integer r10, java.lang.Integer r11, android.graphics.Bitmap r12, org.telegram.messenger.ImageLocation r13, long r14, int r16, kotlin.jvm.internal.DefaultConstructorMarker r17) {
        /*  JADX ERROR: Simple mode code generation failed
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0000: ARITH (r0v0 ?? I:??[int, boolean]) = (r16v0 ?? I:??[int, boolean, short, byte, char]) & (16 ??[int, float, short, byte, char]) A[DECLARE_VAR] in method: com.exteragram.messenger.nowplaying.ui.components.NowPlayingCardData.<init>(com.exteragram.messenger.api.dto.NowPlayingDTO, java.lang.Integer, java.lang.Integer, android.graphics.Bitmap, org.telegram.messenger.ImageLocation, long, int, kotlin.jvm.internal.DefaultConstructorMarker):void, file: classes4.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:355)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.ArgType.getPrimitiveType()" because "type" is null
            	at jadx.core.codegen.ClassGen.useType(ClassGen.java:554)
            	at jadx.core.codegen.InsnGen.useType(InsnGen.java:269)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:166)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:159)
            	at jadx.core.codegen.InsnGen.assignVar(InsnGen.java:152)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:299)
            	... 11 more
            */
        /*
            r8 = this;
            r0 = r16 & 16
            if (r0 == 0) goto L5
            r13 = 0
        L5:
            r5 = r13
            r13 = r16 & 32
            if (r13 == 0) goto L13
            r0 = -1
            r6 = r0
            r2 = r10
            r3 = r11
            r4 = r12
            r0 = r8
            r1 = r9
            goto L19
        L13:
            r6 = r14
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r12
        L19:
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.exteragram.messenger.nowplaying.ui.components.NowPlayingCardData.<init>(com.exteragram.messenger.api.dto.NowPlayingDTO, java.lang.Integer, java.lang.Integer, android.graphics.Bitmap, org.telegram.messenger.ImageLocation, long, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    @JvmStatic
    public static final void create(NowPlayingDTO r1, TLRPC.Document r2, Callback r3) {
        Companion.create(r1, r2, r3);
    }

    public boolean equals(Object r6) {
        if (this != r6) goto L6;
        return true;
    L6:
        if ((r6 instanceof NowPlayingCardData) == true) goto L8;
        return false;
    L8:
        NowPlayingCardData r62 = (NowPlayingCardData) r6;
        if (Intrinsics.areEqual(this.nowPlayingDTO, r62.nowPlayingDTO) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.backgroundColor, r62.backgroundColor) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.accentColor, r62.accentColor) == true) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.coverBitmap, r62.coverBitmap) == true) goto L21;
        return false;
    L21:
        if (Intrinsics.areEqual(this.imageLocation, r62.imageLocation) == true) goto L24;
        return false;
    L24:
        if (this.userEmoji == r62.userEmoji) goto L26;
        return false;
    L26:
        return true;
    }

    public final Integer getAccentColor() {
        return this.accentColor;
    }

    public final Integer getBackgroundColor() {
        return this.backgroundColor;
    }

    public final Bitmap getCoverBitmap() {
        return this.coverBitmap;
    }

    public final ImageLocation getImageLocation() {
        return this.imageLocation;
    }

    public final NowPlayingDTO getNowPlayingDTO() {
        return this.nowPlayingDTO;
    }

    public final long getUserEmoji() {
        return this.userEmoji;
    }

    public int hashCode() {
        int r0 = this.nowPlayingDTO.hashCode() * 31;
        Integer r1 = this.backgroundColor;
        int r2 = 0;
        if (r1 != null) goto L5;
        int r12 = 0;
    L6:
        int r02 = (r0 + r12) * 31;
        Integer r13 = this.accentColor;
        if (r13 != null) goto L9;
        int r14 = 0;
    L10:
        int r03 = (r02 + r14) * 31;
        Bitmap r15 = this.coverBitmap;
        if (r15 != null) goto L13;
        int r16 = 0;
    L14:
        int r04 = (r03 + r16) * 31;
        ImageLocation r17 = this.imageLocation;
        if (r17 == null) goto L19;
        r2 = r17.hashCode();
    L19:
        return ((r04 + r2) * 31) + Long.hashCode(this.userEmoji);
    L13:
        r16 = r15.hashCode();
        goto L14
    L9:
        r14 = r13.hashCode();
        goto L10
    L5:
        r12 = r1.hashCode();
        goto L6
    }

    public final void setUserEmoji(long r1) {
        this.userEmoji = r1;
    }

    public String toString() {
        return "NowPlayingCardData(nowPlayingDTO=" + this.nowPlayingDTO + ", backgroundColor=" + this.backgroundColor + ", accentColor=" + this.accentColor + ", coverBitmap=" + this.coverBitmap + ", imageLocation=" + this.imageLocation + ", userEmoji=" + this.userEmoji + ")";
    }
}
