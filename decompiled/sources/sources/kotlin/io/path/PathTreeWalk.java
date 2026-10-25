package kotlin.io.path;

import com.android.dx.io.Opcodes;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.Path;
import java.nio.file.FileSystemLoopException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\b\b\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJD\u0010\u0012\u001a\u00020\u0010*\b\u0012\u0004\u0012\u00020\u00020\t2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0018\u0010\u0011\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000f\u0012\u0004\u0012\u00020\u00100\u000eH\u0082H¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0014H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u0014H\u0002¢\u0006\u0004\b\u0017\u0010\u0016J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u0014H\u0096\u0002¢\u0006\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0019R\u001c\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u001aR\u0014\u0010\u001e\u001a\u00020\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0014\u0010$\u001a\u00020\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u001dR\u0014\u0010%\u001a\u00020\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010\u001d¨\u0006&"}, d2 = {"Lkotlin/io/path/PathTreeWalk;", "Lkotlin/sequences/Sequence;", "j$/nio/file/Path", "start", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/io/path/PathWalkOption;", "options", "<init>", "(Lj$/nio/file/Path;[Lkotlin/io/path/PathWalkOption;)V", "Lkotlin/sequences/SequenceScope;", "Lkotlin/io/path/PathNode;", "node", "Lkotlin/io/path/DirectoryEntriesReader;", "entriesReader", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "entriesAction", "yieldIfNeeded", "(Lkotlin/sequences/SequenceScope;Lkotlin/io/path/PathNode;Lkotlin/io/path/DirectoryEntriesReader;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "dfsIterator", "()Ljava/util/Iterator;", "bfsIterator", "iterator", "Lj$/nio/file/Path;", "[Lkotlin/io/path/PathWalkOption;", _UrlKt.FRAGMENT_ENCODE_SET, "getFollowLinks", "()Z", "followLinks", "j$/nio/file/LinkOption", "getLinkOptions", "()[Lj$/nio/file/LinkOption;", "linkOptions", "getIncludeDirectories", "includeDirectories", "isBFS", "kotlin-stdlib-jdk7"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class PathTreeWalk implements Sequence<Path> {
    private final PathWalkOption[] options;
    private final Path start;

    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lkotlin/sequences/SequenceScope;", "j$/nio/file/Path", _UrlKt.FRAGMENT_ENCODE_SET, "<anonymous>", "(Lkotlin/sequences/SequenceScope;)V"}, k = 3, mv = {2, 3, 0})
    @DebugMetadata(c = "kotlin.io.path.PathTreeWalk$bfsIterator$1", f = "PathTreeWalk.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {191, 197}, m = "invokeSuspend", n = {"$this$iterator", "queue", "entriesReader", "pathNode", "this_$iv", "$this$yieldIfNeeded$iv", "node$iv", "entriesReader$iv", "path$iv", "$i$f$yieldIfNeeded", "$this$iterator", "queue", "entriesReader", "pathNode", "this_$iv", "$this$yieldIfNeeded$iv", "node$iv", "entriesReader$iv", "path$iv", "$i$f$yieldIfNeeded"}, nl = {193, 199}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0"}, v = 2)
    @SourceDebugExtension({"SMAP\nPathTreeWalk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathTreeWalk.kt\nkotlin/io/path/PathTreeWalk$bfsIterator$1\n+ 2 PathTreeWalk.kt\nkotlin/io/path/PathTreeWalk\n*L\n1#1,180:1\n44#2,19:181\n*S KotlinDebug\n*F\n+ 1 PathTreeWalk.kt\nkotlin/io/path/PathTreeWalk$bfsIterator$1\n*L\n101#1:181,19\n*E\n"})
    /* renamed from: kotlin.io.path.PathTreeWalk$bfsIterator$1, reason: invalid class name */
    public static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        final /* synthetic */ PathTreeWalk this$0;

        public AnonymousClass1(PathTreeWalk r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            AnonymousClass1 r0 = new AnonymousClass1(this.this$0, r3);
            r0.L$0 = r2;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(SequenceScope<? super Path> r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(SequenceScope<? super Path> r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r11) {
            SequenceScope r0 = (SequenceScope) this.L$0;
            Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = this.label;
            if (r2 == 0) goto L10;
            if (r2 == 1) goto L9;
            if (r2 != 2) goto L7;
            Path r22 = (Path) this.L$8;
            DirectoryEntriesReader r23 = (DirectoryEntriesReader) this.L$7;
            PathNode r24 = (PathNode) this.L$6;
            SequenceScope r25 = (SequenceScope) this.L$5;
            PathTreeWalk r26 = (PathTreeWalk) this.L$4;
            PathNode r27 = (PathNode) this.L$3;
            DirectoryEntriesReader r28 = (DirectoryEntriesReader) this.L$2;
            ArrayDeque r3 = (ArrayDeque) this.L$1;
            ResultKt.throwOnFailure(r11);
        L12:
            if (r3.isEmpty() == true) goto L40;
            PathNode r6 = (PathNode) r3.removeFirst();
            PathTreeWalk r7 = this.this$0;
            Path r112 = r6.getPath();
            if (r6.getParent() == null) goto L16;
            PathsKt__PathRecursiveFunctionsKt.checkFileName(r112);
        L16:
            LinkOption[] r8 = PathTreeWalk.access$getLinkOptions(r7);
            LinkOption[] r82 = (LinkOption[]) Arrays.copyOf(r8, r8.length);
            if (Files.isDirectory(r112, (LinkOption[]) Arrays.copyOf(r82, r82.length)) == true) goto L19;
            if (Files.exists(r112, (LinkOption[]) Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1)) == false) goto L12;
            this.L$0 = r0;
            this.L$1 = r3;
            this.L$2 = r28;
            this.L$3 = SpillingKt.nullOutSpilledVariable(r6);
            this.L$4 = SpillingKt.nullOutSpilledVariable(r7);
            this.L$5 = SpillingKt.nullOutSpilledVariable(r0);
            this.L$6 = SpillingKt.nullOutSpilledVariable(r6);
            this.L$7 = SpillingKt.nullOutSpilledVariable(r28);
            this.L$8 = SpillingKt.nullOutSpilledVariable(r112);
            this.I$0 = 0;
            this.label = 2;
            if (r0.yield(r112, this) != r1) goto L12;
        L38:
            return r1;
        L19:
            if (PathTreeWalkKt.access$createsCycle(r6) == true) goto L33;
            if (PathTreeWalk.access$getIncludeDirectories(r7) == false) goto L27;
            this.L$0 = r0;
            this.L$1 = r3;
            this.L$2 = r28;
            this.L$3 = SpillingKt.nullOutSpilledVariable(r6);
            this.L$4 = r7;
            this.L$5 = SpillingKt.nullOutSpilledVariable(r0);
            this.L$6 = r6;
            this.L$7 = r28;
            this.L$8 = r112;
            this.I$0 = 0;
            this.label = 1;
            if (r0.yield(r112, this) == r1) goto L38;
            DirectoryEntriesReader r83 = r28;
            ArrayDeque r9 = r3;
            Path r29 = r112;
            DirectoryEntriesReader r32 = r83;
        L26:
            r112 = r29;
            r28 = r32;
            r3 = r9;
        L28:
            LinkOption[] r72 = PathTreeWalk.access$getLinkOptions(r7);
            LinkOption[] r73 = (LinkOption[]) Arrays.copyOf(r72, r72.length);
            if (Files.isDirectory(r112, (LinkOption[]) Arrays.copyOf(r73, r73.length)) == false) goto L31;
            r3.addAll(r28.readEntries(r6));
        L31:
            r28 = r83;
            goto L12
        L41:
            r28 = r83;
            goto L12
        L27:
            r83 = r28;
            goto L28
        L33:
            throw new FileSystemLoopException(r112.toString());
        L40:
            return Unit.INSTANCE;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            r29 = (Path) this.L$8;
            r32 = (DirectoryEntriesReader) this.L$7;
            r6 = (PathNode) this.L$6;
            SequenceScope r74 = (SequenceScope) this.L$5;
            r7 = (PathTreeWalk) this.L$4;
            PathNode r84 = (PathNode) this.L$3;
            r83 = (DirectoryEntriesReader) this.L$2;
            r9 = (ArrayDeque) this.L$1;
            ResultKt.throwOnFailure(r11);
            goto L26
        L10:
            ResultKt.throwOnFailure(r11);
            ArrayDeque r113 = new ArrayDeque();
            r28 = new DirectoryEntriesReader(PathTreeWalk.access$getFollowLinks(this.this$0));
            r113.addLast(new PathNode(PathTreeWalk.access$getStart$p(this.this$0), PathTreeWalkKt.access$keyOf(PathTreeWalk.access$getStart$p(this.this$0), PathTreeWalk.access$getLinkOptions(this.this$0)), null));
            r3 = r113;
            goto L12
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lkotlin/sequences/SequenceScope;", "j$/nio/file/Path", _UrlKt.FRAGMENT_ENCODE_SET, "<anonymous>", "(Lkotlin/sequences/SequenceScope;)V"}, k = 3, mv = {2, 3, 0})
    @DebugMetadata(c = "kotlin.io.path.PathTreeWalk$dfsIterator$1", f = "PathTreeWalk.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {191, 197, Opcodes.MUL_INT_LIT16, Opcodes.ADD_INT_LIT8}, m = "invokeSuspend", n = {"$this$iterator", "stack", "entriesReader", "startNode", "this_$iv", "$this$yieldIfNeeded$iv", "node$iv", "entriesReader$iv", "path$iv", "$i$f$yieldIfNeeded", "$this$iterator", "stack", "entriesReader", "startNode", "this_$iv", "$this$yieldIfNeeded$iv", "node$iv", "entriesReader$iv", "path$iv", "$i$f$yieldIfNeeded", "$this$iterator", "stack", "entriesReader", "startNode", "topNode", "topIterator", "pathNode", "this_$iv", "$this$yieldIfNeeded$iv", "node$iv", "entriesReader$iv", "path$iv", "$i$f$yieldIfNeeded", "$this$iterator", "stack", "entriesReader", "startNode", "topNode", "topIterator", "pathNode", "this_$iv", "$this$yieldIfNeeded$iv", "node$iv", "entriesReader$iv", "path$iv", "$i$f$yieldIfNeeded"}, nl = {193, 199, Opcodes.REM_INT_LIT16, Opcodes.MUL_INT_LIT8}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "I$0"}, v = 2)
    @SourceDebugExtension({"SMAP\nPathTreeWalk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathTreeWalk.kt\nkotlin/io/path/PathTreeWalk$dfsIterator$1\n+ 2 PathTreeWalk.kt\nkotlin/io/path/PathTreeWalk\n*L\n1#1,180:1\n44#2,19:181\n44#2,19:200\n*S KotlinDebug\n*F\n+ 1 PathTreeWalk.kt\nkotlin/io/path/PathTreeWalk$dfsIterator$1\n*L\n70#1:181,19\n81#1:200,19\n*E\n"})
    /* renamed from: kotlin.io.path.PathTreeWalk$dfsIterator$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01741 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        final /* synthetic */ PathTreeWalk this$0;

        public C01741(PathTreeWalk r1, Continuation<? super C01741> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            C01741 r0 = new C01741(this.this$0, r3);
            r0.L$0 = r2;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(SequenceScope<? super Path> r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(SequenceScope<? super Path> r1, Continuation<? super Unit> r2) {
            return ((C01741) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r18) {
            SequenceScope r1 = (SequenceScope) this.L$0;
            Object r2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r3 = this.label;
            if (r3 == 0) goto L15;
            if (r3 == 1) goto L14;
            if (r3 == 2) goto L12;
            if (r3 == 3) goto L11;
            if (r3 != 4) goto L9;
            Path r32 = (Path) this.L$11;
            DirectoryEntriesReader r33 = (DirectoryEntriesReader) this.L$10;
            PathNode r34 = (PathNode) this.L$9;
            SequenceScope r35 = (SequenceScope) this.L$8;
            PathTreeWalk r36 = (PathTreeWalk) this.L$7;
            PathNode r37 = (PathNode) this.L$6;
            Iterator r38 = (Iterator) this.L$5;
            PathNode r39 = (PathNode) this.L$4;
        L13:
            PathNode r310 = (PathNode) this.L$3;
            DirectoryEntriesReader r4 = (DirectoryEntriesReader) this.L$2;
            ArrayDeque r8 = (ArrayDeque) this.L$1;
            ResultKt.throwOnFailure(r18);
        L43:
            if (r8.isEmpty() == true) goto L75;
            PathNode r10 = (PathNode) r8.last();
            Iterator<PathNode> r11 = r10.getContentIterator();
            if (r11.hasNext() == true) goto L46;
            r8.removeLast();
            goto L43
        L46:
            PathNode r12 = r11.next();
            PathTreeWalk r13 = this.this$0;
            Path r14 = r12.getPath();
            if (r12.getParent() == null) goto L49;
            PathsKt__PathRecursiveFunctionsKt.checkFileName(r14);
        L49:
            LinkOption[] r15 = PathTreeWalk.access$getLinkOptions(r13);
            LinkOption[] r6 = (LinkOption[]) Arrays.copyOf(r15, r15.length);
            if (Files.isDirectory(r14, (LinkOption[]) Arrays.copyOf(r6, r6.length)) == true) goto L52;
            if (Files.exists(r14, (LinkOption[]) Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1)) == false) goto L65;
            this.L$0 = r1;
            this.L$1 = r8;
            this.L$2 = r4;
            this.L$3 = SpillingKt.nullOutSpilledVariable(r310);
            this.L$4 = SpillingKt.nullOutSpilledVariable(r10);
            this.L$5 = SpillingKt.nullOutSpilledVariable(r11);
            this.L$6 = SpillingKt.nullOutSpilledVariable(r12);
            this.L$7 = SpillingKt.nullOutSpilledVariable(r13);
            this.L$8 = SpillingKt.nullOutSpilledVariable(r1);
            this.L$9 = SpillingKt.nullOutSpilledVariable(r12);
            this.L$10 = SpillingKt.nullOutSpilledVariable(r4);
            this.L$11 = SpillingKt.nullOutSpilledVariable(r14);
            this.I$0 = 0;
            this.label = 4;
            if (r1.yield(r14, this) != r2) goto L43;
        L72:
            return r2;
        L52:
            if (PathTreeWalkKt.access$createsCycle(r12) == true) goto L67;
            if (PathTreeWalk.access$getIncludeDirectories(r13) == false) goto L60;
            this.L$0 = r1;
            this.L$1 = r8;
            this.L$2 = r4;
            this.L$3 = SpillingKt.nullOutSpilledVariable(r310);
            this.L$4 = SpillingKt.nullOutSpilledVariable(r10);
            this.L$5 = SpillingKt.nullOutSpilledVariable(r11);
            this.L$6 = r12;
            this.L$7 = r13;
            this.L$8 = SpillingKt.nullOutSpilledVariable(r1);
            this.L$9 = r12;
            this.L$10 = r4;
            this.L$11 = r14;
            this.I$0 = 0;
            this.label = 3;
            if (r1.yield(r14, this) == r2) goto L72;
            PathNode r112 = r12;
            PathTreeWalk r102 = r13;
            PathNode r122 = r310;
            DirectoryEntriesReader r132 = r4;
            Path r311 = r14;
            ArrayDeque r142 = r8;
            PathNode r82 = r112;
        L59:
            ArrayDeque r16 = r142;
            r14 = r311;
            r310 = r122;
            r12 = r82;
            r8 = r16;
        L61:
            LinkOption[] r62 = PathTreeWalk.access$getLinkOptions(r102);
            LinkOption[] r63 = (LinkOption[]) Arrays.copyOf(r62, r62.length);
            if (Files.isDirectory(r14, (LinkOption[]) Arrays.copyOf(r63, r63.length)) == false) goto L64;
            r112.setContentIterator(r4.readEntries(r12).iterator());
            r8.addLast(r112);
        L64:
            r4 = r132;
            goto L43
        L60:
            r112 = r12;
            r102 = r13;
            r132 = r4;
            goto L61
        L67:
            throw new FileSystemLoopException(r14.toString());
        L75:
            return Unit.INSTANCE;
        L9:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L11:
            r311 = (Path) this.L$11;
            r4 = (DirectoryEntriesReader) this.L$10;
            r82 = (PathNode) this.L$9;
            SequenceScope r103 = (SequenceScope) this.L$8;
            r102 = (PathTreeWalk) this.L$7;
            r112 = (PathNode) this.L$6;
            Iterator r123 = (Iterator) this.L$5;
            PathNode r124 = (PathNode) this.L$4;
            r122 = (PathNode) this.L$3;
            r132 = (DirectoryEntriesReader) this.L$2;
            r142 = (ArrayDeque) this.L$1;
            ResultKt.throwOnFailure(r18);
            goto L59
        L12:
            Path r312 = (Path) this.L$8;
            DirectoryEntriesReader r313 = (DirectoryEntriesReader) this.L$7;
            PathNode r314 = (PathNode) this.L$6;
            SequenceScope r315 = (SequenceScope) this.L$5;
            PathTreeWalk r316 = (PathTreeWalk) this.L$4;
            goto L13
        L14:
            Path r317 = (Path) this.L$8;
            DirectoryEntriesReader r42 = (DirectoryEntriesReader) this.L$7;
            PathNode r83 = (PathNode) this.L$6;
            SequenceScope r104 = (SequenceScope) this.L$5;
            PathTreeWalk r105 = (PathTreeWalk) this.L$4;
            PathNode r113 = (PathNode) this.L$3;
            DirectoryEntriesReader r125 = (DirectoryEntriesReader) this.L$2;
            ArrayDeque r133 = (ArrayDeque) this.L$1;
            ResultKt.throwOnFailure(r18);
        L28:
            ArrayDeque r162 = r133;
            Path r134 = r317;
            ArrayDeque r318 = r162;
            PathTreeWalk r163 = r105;
            DirectoryEntriesReader r106 = r42;
            PathTreeWalk r43 = r163;
            PathNode r164 = r113;
            r113 = r83;
            PathNode r84 = r164;
        L30:
            LinkOption[] r44 = PathTreeWalk.access$getLinkOptions(r43);
            LinkOption[] r45 = (LinkOption[]) Arrays.copyOf(r44, r44.length);
            if (Files.isDirectory(r134, (LinkOption[]) Arrays.copyOf(r45, r45.length)) == false) goto L33;
            r84.setContentIterator(r106.readEntries(r113).iterator());
            r318.addLast(r84);
        L33:
            PathNode r46 = r84;
            r8 = r318;
            r310 = r46;
            r4 = r125;
            goto L43
        L15:
            ResultKt.throwOnFailure(r18);
            r318 = new ArrayDeque();
            r106 = new DirectoryEntriesReader(PathTreeWalk.access$getFollowLinks(this.this$0));
            r113 = new PathNode(PathTreeWalk.access$getStart$p(this.this$0), PathTreeWalkKt.access$keyOf(PathTreeWalk.access$getStart$p(this.this$0), PathTreeWalk.access$getLinkOptions(this.this$0)), null);
            r43 = this.this$0;
            Path r126 = r113.getPath();
        L16:
            if (r113.getParent() == null) goto L18;
            PathsKt__PathRecursiveFunctionsKt.checkFileName(r126);
        L18:
            LinkOption[] r135 = PathTreeWalk.access$getLinkOptions(r43);
            LinkOption[] r136 = (LinkOption[]) Arrays.copyOf(r135, r135.length);
            if (Files.isDirectory(r126, (LinkOption[]) Arrays.copyOf(r136, r136.length)) == false) goto L37;
            if (PathTreeWalkKt.access$createsCycle(r113) == true) goto L35;
            if (PathTreeWalk.access$getIncludeDirectories(r43) == false) goto L29;
            this.L$0 = r1;
            this.L$1 = r318;
            this.L$2 = r106;
            this.L$3 = r113;
            this.L$4 = r43;
            this.L$5 = SpillingKt.nullOutSpilledVariable(r1);
            this.L$6 = r113;
            this.L$7 = r106;
            this.L$8 = r126;
            this.I$0 = 0;
            this.label = 1;
            if (r1.yield(r126, this) == r2) goto L72;
            r133 = r318;
            r83 = r113;
            r317 = r126;
            r125 = r106;
            r105 = r43;
            r42 = r125;
            goto L28
        L29:
            r84 = r113;
            r134 = r126;
            r125 = r106;
            goto L30
        L35:
            throw new FileSystemLoopException(r126.toString());
        L37:
            if (Files.exists(r126, (LinkOption[]) Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1)) == false) goto L41;
            this.L$0 = r1;
            this.L$1 = r318;
            this.L$2 = r106;
            this.L$3 = SpillingKt.nullOutSpilledVariable(r113);
            this.L$4 = SpillingKt.nullOutSpilledVariable(r43);
            this.L$5 = SpillingKt.nullOutSpilledVariable(r1);
            this.L$6 = SpillingKt.nullOutSpilledVariable(r113);
            this.L$7 = SpillingKt.nullOutSpilledVariable(r106);
            this.L$8 = SpillingKt.nullOutSpilledVariable(r126);
            this.I$0 = 0;
            this.label = 2;
            if (r1.yield(r126, this) == r2) goto L72;
        L41:
            r8 = r318;
            r4 = r106;
            r310 = r113;
            goto L43
        }
    }

    public PathTreeWalk(Path r1, PathWalkOption[] r2) {
        this.start = r1;
        this.options = r2;
    }

    public static final /* synthetic */ boolean access$getFollowLinks(PathTreeWalk r0) {
        return r0.getFollowLinks();
    }

    public static final /* synthetic */ boolean access$getIncludeDirectories(PathTreeWalk r0) {
        return r0.getIncludeDirectories();
    }

    public static final /* synthetic */ LinkOption[] access$getLinkOptions(PathTreeWalk r0) {
        return r0.getLinkOptions();
    }

    public static final /* synthetic */ Path access$getStart$p(PathTreeWalk r0) {
        return r0.start;
    }

    private final Iterator<Path> bfsIterator() {
        return SequencesKt.iterator(new AnonymousClass1(this, null));
    }

    private final Iterator<Path> dfsIterator() {
        return SequencesKt.iterator(new C01741(this, null));
    }

    private final boolean getFollowLinks() {
        return ArraysKt.contains(this.options, PathWalkOption.FOLLOW_LINKS);
    }

    private final boolean getIncludeDirectories() {
        return ArraysKt.contains(this.options, PathWalkOption.INCLUDE_DIRECTORIES);
    }

    private final LinkOption[] getLinkOptions() {
        return LinkFollowing.INSTANCE.toLinkOptions(getFollowLinks());
    }

    private final boolean isBFS() {
        return ArraysKt.contains(this.options, PathWalkOption.BREADTH_FIRST);
    }

    private final Object yieldIfNeeded(SequenceScope<? super Path> r5, PathNode r6, DirectoryEntriesReader r7, Function1<? super List<PathNode>, Unit> r8, Continuation<? super Unit> r9) {
        Path r0 = r6.getPath();
        if (r6.getParent() == null) goto L5;
        PathsKt__PathRecursiveFunctionsKt.checkFileName(r0);
    L5:
        LinkOption[] r1 = access$getLinkOptions(this);
        LinkOption[] r12 = (LinkOption[]) Arrays.copyOf(r1, r1.length);
        if (Files.isDirectory(r0, (LinkOption[]) Arrays.copyOf(r12, r12.length)) == false) goto L18;
        if (PathTreeWalkKt.access$createsCycle(r6) == true) goto L16;
        if (access$getIncludeDirectories(this) == false) goto L12;
        InlineMarker.mark(0);
        r5.yield(r0, r9);
        InlineMarker.mark(1);
    L12:
        LinkOption[] r4 = access$getLinkOptions(this);
        LinkOption[] r42 = (LinkOption[]) Arrays.copyOf(r4, r4.length);
        if (Files.isDirectory(r0, (LinkOption[]) Arrays.copyOf(r42, r42.length)) == false) goto L22;
        r8.invoke(r7.readEntries(r6));
    L22:
        return Unit.INSTANCE;
    L16:
        throw new FileSystemLoopException(r0.toString());
    L18:
        if (Files.exists(r0, (LinkOption[]) Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1)) == false) goto L22;
        InlineMarker.mark(0);
        r5.yield(r0, r9);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<Path> iterator() {
        if (isBFS() == false) goto L7;
        return bfsIterator();
    L7:
        return dfsIterator();
    }
}
