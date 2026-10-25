package okio.internal;

import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import okhttp3.internal.url._UrlKt;
import okio.BufferedSink;
import okio.FileMetadata;
import okio.JvmSystemFileSystem$$ExternalSyntheticBUOutline0;
import okio.Okio;
import okio.Path;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.Source;
import okio.ZipFileSystem$$ExternalSyntheticBUOutline0;
import okio.ZipFileSystem$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u001c\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0006H\u0000\u001a\u001c\u0010\u000b\u001a\u00020\b*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0000\u001a\u001c\u0010\u000e\u001a\u00020\b*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0006H\u0000\u001a\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0006H\u0000\u001aF\u0010\u0014\u001a\u00020\b*\b\u0012\u0004\u0012\u00020\u00040\u00152\u0006\u0010\u0016\u001a\u00020\u00022\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00182\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006H\u0080@¢\u0006\u0002\u0010\u001a\u001a\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u001c"}, d2 = {"commonMetadata", "Lokio/FileMetadata;", "Lokio/FileSystem;", "path", "Lokio/Path;", "commonExists", _UrlKt.FRAGMENT_ENCODE_SET, "commonCreateDirectories", _UrlKt.FRAGMENT_ENCODE_SET, "dir", "mustCreate", "commonCopy", "source", "target", "commonDeleteRecursively", "fileOrDirectory", "mustExist", "commonListRecursively", "Lkotlin/sequences/Sequence;", "followSymlinks", "collectRecursively", "Lkotlin/sequences/SequenceScope;", "fileSystem", "stack", "Lkotlin/collections/ArrayDeque;", "postorder", "(Lkotlin/sequences/SequenceScope;Lokio/FileSystem;Lkotlin/collections/ArrayDeque;Lokio/Path;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "symlinkTarget", "okio"}, k = 2, mv = {2, 2, 0}, xi = 48)
@JvmName(name = "-FileSystem")
@SourceDebugExtension({"SMAP\nFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileSystem.kt\nokio/internal/-FileSystem\n+ 2 Okio.kt\nokio/Okio__OkioKt\n*L\n1#1,155:1\n58#2,4:156\n58#2,22:160\n66#2,10:182\n62#2,3:192\n77#2,3:195\n*S KotlinDebug\n*F\n+ 1 FileSystem.kt\nokio/internal/-FileSystem\n*L\n65#1:156,4\n66#1:160,22\n65#1:182,10\n65#1:192,3\n65#1:195,3\n*E\n"})
/* renamed from: okio.internal.-FileSystem, reason: invalid class name */
/* loaded from: classes5.dex */
public abstract class FileSystem {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "okio.internal.-FileSystem", f = "FileSystem.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2}, l = {116, 135, 145}, m = "collectRecursively", n = {"$this$collectRecursively", "fileSystem", "stack", "path", "followSymlinks", "postorder", "$this$collectRecursively", "fileSystem", "stack", "path", "children", "symlinkPath", "child", "followSymlinks", "postorder", "symlinkCount", "$this$collectRecursively", "fileSystem", "stack", "path", "children", "followSymlinks", "postorder"}, s = {"L$0", "L$1", "L$2", "L$3", "Z$0", "Z$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "Z$0", "Z$1", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "Z$0", "Z$1"}, v = 1)
    /* renamed from: okio.internal.-FileSystem$collectRecursively$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        boolean Z$0;
        boolean Z$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> r1) {
            super(r1);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r8) {
            this.result = r8;
            this.label |= Integer.MIN_VALUE;
            return FileSystem.collectRecursively(null, null, null, null, false, false, this);
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/sequences/SequenceScope;", "Lokio/Path;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "okio.internal.-FileSystem$commonListRecursively$1", f = "FileSystem.kt", i = {0, 0, 0}, l = {96}, m = "invokeSuspend", n = {"$this$sequence", "stack", "child"}, s = {"L$0", "L$1", "L$3"}, v = 1)
    /* renamed from: okio.internal.-FileSystem$commonListRecursively$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01941 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Path $dir;
        final /* synthetic */ boolean $followSymlinks;
        final /* synthetic */ okio.FileSystem $this_commonListRecursively;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        public C01941(Path r1, okio.FileSystem r2, boolean r3, Continuation<? super C01941> r4) {
            this.$dir = r1;
            this.$this_commonListRecursively = r2;
            this.$followSymlinks = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r4, Continuation<?> r5) {
            C01941 r0 = new C01941(this.$dir, this.$this_commonListRecursively, this.$followSymlinks, r5);
            r0.L$0 = r4;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(SequenceScope<? super Path> r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(SequenceScope<? super Path> r1, Continuation<? super Unit> r2) {
            return ((C01941) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0069 -> B:14:0x006c). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r10) {
            SequenceScope r1 = (SequenceScope) this.L$0;
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = this.label;
            if (r2 == 0) goto L8;
            if (r2 != 1) goto L6;
            Path r22 = (Path) this.L$3;
            Iterator<Path> r23 = (Iterator) this.L$2;
            ArrayDeque r3 = (ArrayDeque) this.L$1;
            ResultKt.throwOnFailure(r10);
            C01941 r7 = this;
            Iterator<Path> r102 = r23;
        L14:
            C01941 this = r7;
        L10:
            if (r102.hasNext() == false) goto L16;
            Path r4 = r102.next();
            okio.FileSystem r24 = this.$this_commonListRecursively;
            boolean r5 = this.$followSymlinks;
            this.L$0 = r1;
            this.L$1 = r3;
            this.L$2 = r102;
            this.L$3 = SpillingKt.nullOutSpilledVariable(r4);
            this.label = 1;
            r7 = this;
            if (FileSystem.collectRecursively(r1, r24, r3, r4, r5, false, r7) != r0) goto L14;
            return r0;
        L16:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r10);
            ArrayDeque r103 = new ArrayDeque();
            r103.addLast(this.$dir);
            Iterator<Path> r25 = this.$this_commonListRecursively.list(this.$dir).iterator();
            r3 = r103;
            r102 = r25;
            goto L10
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x0162 -> B:19:0x0085). Please report as a decompilation issue!!! */
    public static final Object collectRecursively(SequenceScope<? super Path> r17, okio.FileSystem r18, ArrayDeque<Path> r19, Path r20, boolean r21, boolean r22, Continuation<? super Unit> r23) {
        Path r1 = r20;
        boolean r2 = r22;
        if ((r23 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r4 = (AnonymousClass1) r23;
        int r5 = r4.label;
        if ((r5 & Integer.MIN_VALUE) == 0) goto L7;
        r4.label = r5 - Integer.MIN_VALUE;
    L8:
        Object r3 = r4.result;
        Object r52 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r6 = r4.label;
        if (r6 == 0) goto L23;
        if (r6 == 1) goto L22;
        if (r6 == 2) goto L16;
        if (r6 != 3) goto L14;
        List r0 = (List) r4.L$4;
        Path r02 = (Path) r4.L$3;
        ArrayDeque r03 = (ArrayDeque) r4.L$2;
        okio.FileSystem r04 = (okio.FileSystem) r4.L$1;
        SequenceScope r05 = (SequenceScope) r4.L$0;
        ResultKt.throwOnFailure(r3);
    L75:
        return Unit.INSTANCE;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        int r06 = r4.I$0;
        boolean r12 = r4.Z$1;
        boolean r24 = r4.Z$0;
        Path r62 = (Path) r4.L$7;
        Iterator<Path> r63 = (Iterator) r4.L$6;
        Path r10 = (Path) r4.L$5;
        List<Path> r11 = (List) r4.L$4;
        Path r122 = (Path) r4.L$3;
        ArrayDeque<Path> r13 = (ArrayDeque) r4.L$2;
        okio.FileSystem r14 = (okio.FileSystem) r4.L$1;
        SequenceScope<? super Path> r15 = (SequenceScope) r4.L$0;
        ResultKt.throwOnFailure(r3);     // Catch: Throwable -> L20
        int r123 = r06;
        boolean r07 = r24;
        r2 = r12;
        ArrayDeque<Path> r16 = r13;
        Path r132 = r10;
        okio.FileSystem r102 = r14;
        Path r142 = r122;
        List<Path> r32 = r11;
    L19:
        SequenceScope<? super Path> r112 = r15;
    L84:
        if (r63.hasNext() == false) goto L64;
        Path r152 = r63.next();     // Catch: Throwable -> L62
        r4.L$0 = r112;     // Catch: Throwable -> L62
        r4.L$1 = r102;     // Catch: Throwable -> L62
        r4.L$2 = r16;     // Catch: Throwable -> L62
        r4.L$3 = r142;     // Catch: Throwable -> L62
        r4.L$4 = SpillingKt.nullOutSpilledVariable(r32);     // Catch: Throwable -> L62
        r4.L$5 = SpillingKt.nullOutSpilledVariable(r132);     // Catch: Throwable -> L62
        r4.L$6 = r63;     // Catch: Throwable -> L62
        r4.L$7 = SpillingKt.nullOutSpilledVariable(r152);     // Catch: Throwable -> L62
        r4.Z$0 = r07;     // Catch: Throwable -> L62
        r4.Z$1 = r2;     // Catch: Throwable -> L62
        r4.I$0 = r123;     // Catch: Throwable -> L62
        r4.label = 2;     // Catch: Throwable -> L62
        boolean r212 = r07;
        ArrayDeque<Path> r192 = r16;
        boolean r222 = r2;
        AnonymousClass1 r232 = r4;
        okio.FileSystem r182 = r102;
        SequenceScope<? super Path> r172 = r112;
        Object r08 = collectRecursively(r172, r182, r192, r152, r212, r222, r232);     // Catch: Throwable -> L59
        r15 = r172;
        r102 = r182;
        r4 = r232;
        if (r08 == r52) goto L73;
        r07 = r212;
        r2 = r222;
        r16 = r192;
    L73:
        return r52;
    L59:
        th = th;
        ArrayDeque<Path> r7 = r192;
    L61:
        r13 = r7;
    L67:
        r13.removeLast();
        throw th;
    L64:
        ArrayDeque<Path> r72 = r16;
        r72.removeLast();
        ArrayDeque<Path> r64 = r72;
        r2 = r2;
        r1 = r142;
    L70:
        if (r2 == false) goto L77;
        r4.L$0 = SpillingKt.nullOutSpilledVariable(r112);
        r4.L$1 = SpillingKt.nullOutSpilledVariable(r102);
        r4.L$2 = SpillingKt.nullOutSpilledVariable(r64);
        r4.L$3 = SpillingKt.nullOutSpilledVariable(r1);
        r4.L$4 = SpillingKt.nullOutSpilledVariable(r32);
        r4.L$5 = null;
        r4.L$6 = null;
        r4.L$7 = null;
        r4.Z$0 = r07;
        r4.Z$1 = r2;
        r4.label = 3;
        if (r112.yield(r1, r4) != r52) goto L75;
    L77:
        return Unit.INSTANCE;
    L62:
        th = th;
        r7 = r16;
    L20:
        th = th;
        goto L67
    L22:
        boolean r09 = r4.Z$1;
        boolean r110 = r4.Z$0;
        Path r25 = (Path) r4.L$3;
        r64 = (ArrayDeque) r4.L$2;
        r102 = (okio.FileSystem) r4.L$1;
        r112 = (SequenceScope) r4.L$0;
        ResultKt.throwOnFailure(r3);
        r2 = r09;
        r07 = r110;
        r1 = r25;
    L30:
        r32 = r102.listOrNull(r1);
        if (r32 != null) goto L34;
        r32 = CollectionsKt.emptyList();
    L34:
        if (r32.isEmpty() == true) goto L70;
        r123 = 0;
        r132 = r1;
    L36:
        if (r07 == true) goto L38;
    L42:
        Path r143 = symlinkTarget(r102, r132);
        if (r143 == null) goto L44;
        r123 = r123 + 1;
        r132 = r143;
        goto L36
    L44:
        if (r07 == true) goto L46;
        if (r123 != 0) goto L70;
    L46:
        r64.addLast(r132);
        r142 = r1;
        r16 = r64;
        r63 = r32.iterator();     // Catch: Throwable -> L65
    L65:
        th = th;
        r13 = r64;
        goto L67
    L38:
        if (r64.contains(r132) == false) goto L42;
        ZipFileSystem$$ExternalSyntheticBUOutline0.m("symlink cycle at ", r1);
        return null;
    L23:
        ResultKt.throwOnFailure(r3);
        if (r2 == true) goto L28;
        r4.L$0 = r17;
        okio.FileSystem r33 = r18;
        r4.L$1 = r33;
        r64 = r19;
        r4.L$2 = r64;
        r4.L$3 = r1;
        boolean r113 = r21;
        r4.Z$0 = r113;
        r4.Z$1 = r2;
        r4.label = 1;
        if (r17.yield(r1, r4) == r52) goto L73;
    L29:
        boolean r103 = r113;
        r112 = r17;
        r07 = r103;
        r102 = r33;
        goto L30
    L28:
        r33 = r18;
        r64 = r19;
        r113 = r21;
    L7:
        r4 = new AnonymousClass1(r23);
        goto L8
    }

    public static final void commonCopy(okio.FileSystem r3, Path r4, Path r5) {
        Source r42 = r3.source(r4);
        Throwable th = null;
        BufferedSink r32 = Okio.buffer(okio.FileSystem.sink$default(r3, r5, false, 2, null));     // Catch: Throwable -> L17
        Long r52 = Long.valueOf(r32.writeAll(r42));     // Catch: Throwable -> L11
        if (r32 != null) goto L45;
    L10:
        Throwable th2 = null;
    L20:
        if (th2 != null) goto L27;
        r52.longValue();     // Catch: Throwable -> L17
        if (r42 != null) goto L43;
    L34:
        if (th != null) goto L36;
        return;
    L36:
        throw th;
    L43:
        r42.close();     // Catch: Throwable -> L25
    L25:
        th = th;
        goto L34
    L27:
        throw th2;     // Catch: Throwable -> L17
    L45:
        r32.close();     // Catch: Throwable -> L8
    L8:
        th2 = th;
        goto L20
    L11:
        th = move-exception;
        if (r32 != null) goto L41;
    L19:
        th2 = th;
        r52 = null;
        goto L20
    L41:
        r32.close();     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        if (r42 != null) goto L39;
    L33:
        th = th;
        goto L34
    L39:
        r42.close();     // Catch: Throwable -> L31
    L31:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);
        goto L33
    }

    public static final void commonCreateDirectories(okio.FileSystem r3, Path r4, boolean r5) {
        ArrayDeque r0 = new ArrayDeque();
        Path r1 = r4;
    L3:
        if (r1 == null) goto L7;
        if (r3.exists(r1) == true) goto L7;
        r0.addFirst(r1);
        r1 = r1.parent();
    L7:
        if (r5 == true) goto L9;
    L13:
        Iterator<E> r42 = r0.iterator();
    L15:
        if (r42.hasNext() == false) goto L17;
        okio.FileSystem.createDirectory$default(r3, (Path) r42.next(), false, 2, null);
        goto L15
    L17:
        return;
    L9:
        if (r0.isEmpty() == false) goto L13;
        JvmSystemFileSystem$$ExternalSyntheticBUOutline0.m(r4, " already exists.");
    }

    public static final void commonDeleteRecursively(okio.FileSystem r2, Path r3, boolean r4) {
        Iterator r32 = SequencesKt.sequence(new FileSystem$commonDeleteRecursively$sequence$1(r2, r3, null)).iterator();
    L4:
        if (r32.hasNext() == false) goto L12;
        Path r0 = (Path) r32.next();
        if (r4 == true) goto L8;
    L10:
        boolean r1 = false;
    L11:
        r2.delete(r0, r1);
        goto L4
    L8:
        if (r32.hasNext() == true) goto L10;
        r1 = true;
        goto L11
    }

    public static final boolean commonExists(okio.FileSystem r0, Path r1) {
        if (r0.metadataOrNull(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public static final Sequence<Path> commonListRecursively(okio.FileSystem r2, Path r3, boolean r4) {
        return SequencesKt.sequence(new C01941(r3, r2, r4, null));
    }

    public static final FileMetadata commonMetadata(okio.FileSystem r0, Path r1) {
        FileMetadata r02 = r0.metadataOrNull(r1);
        if (r02 == null) goto L5;
        return r02;
    L5:
        ZipFileSystem$$ExternalSyntheticBUOutline1.m("no such file: ", r1);
        return null;
    }

    public static final Path symlinkTarget(okio.FileSystem r0, Path r1) {
        Path r02 = r0.metadata(r1).getSymlinkTarget();
        if (r02 != null) goto L7;
        return null;
    L7:
        return r1.parent().resolve(r02);
    }
}
