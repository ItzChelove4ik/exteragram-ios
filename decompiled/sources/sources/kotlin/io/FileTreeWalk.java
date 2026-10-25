package kotlin.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u001b\u001c\u001dB\u008b\u0001\bB\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007\u00128\u0010\u000b\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\n\u0018\u00010\f\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015B\u001b\bP\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0014\u0010\u0016J\u0010\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u0018H\u0096\u0082\u0004J\u001e\u0010\u0006\u001a\u00020\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0007H\u0086\u0080\u0004J\u001e\u0010\t\u001a\u00020\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\n0\u0007H\u0086\u0080\u0004J$\u0010\u000b\u001a\u00020\u00002\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\n0\fH\u0086\u0080\u0004J\u0012\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0013H\u0086\u0080\u0004R\u000f\u0010\u0003\u001a\u00020\u0002X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u001d\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000RA\u0010\u000b\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\n\u0018\u00010\fX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0012\u001a\u00020\u0013X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lkotlin/io/FileTreeWalk;", "Lkotlin/sequences/Sequence;", "Ljava/io/File;", "start", "direction", "Lkotlin/io/FileWalkDirection;", "onEnter", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "onLeave", _UrlKt.FRAGMENT_ENCODE_SET, "onFail", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "f", "Ljava/io/IOException;", "e", "maxDepth", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;I)V", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;)V", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "function", "depth", "WalkState", "DirectoryState", "FileTreeWalkIterator", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class FileTreeWalk implements Sequence<File> {
    private final FileWalkDirection direction;
    private final int maxDepth;
    private final Function1<File, Boolean> onEnter;
    private final Function2<File, IOException, Unit> onFail;
    private final Function1<File, Unit> onLeave;
    private final File start;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\"\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/io/FileTreeWalk$DirectoryState;", "Lkotlin/io/FileTreeWalk$WalkState;", "rootDir", "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nFileTreeWalk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileTreeWalk.kt\nkotlin/io/FileTreeWalk$DirectoryState\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,273:1\n1#2:274\n*E\n"})
    public static abstract class DirectoryState extends WalkState {
        public DirectoryState(File r1) {
            super(r1);
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u000e\u000f\u0010B\t\bF¢\u0006\u0004\b\u0003\u0010\u0004J\n\u0010\b\u001a\u00020\tH\u0094\u0080\u0004J\u0012\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0002H\u0082\u0080\u0004J\f\u0010\r\u001a\u0004\u0018\u00010\u0002H\u0082\u0090\u0004R\u0015\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;", "Lkotlin/collections/AbstractIterator;", "Ljava/io/File;", "<init>", "(Lkotlin/io/FileTreeWalk;)V", "state", "Ljava/util/ArrayDeque;", "Lkotlin/io/FileTreeWalk$WalkState;", "computeNext", _UrlKt.FRAGMENT_ENCODE_SET, "directoryState", "Lkotlin/io/FileTreeWalk$DirectoryState;", "root", "gotoNext", "BottomUpDirectoryState", "TopDownDirectoryState", "SingleFileState", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public final class FileTreeWalkIterator extends AbstractIterator<File> {
        private final ArrayDeque<WalkState> state;
        final /* synthetic */ FileTreeWalk this$0;

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0096\u0080\u0004R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tX\u0082\u008e\b¢\u0006\u0004\n\u0002\u0010\nR\u000f\u0010\u000b\u001a\u00020\fX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\r\u001a\u00020\u0007X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$BottomUpDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "rootDir", "Ljava/io/File;", "<init>", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "rootVisited", _UrlKt.FRAGMENT_ENCODE_SET, "fileList", _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/io/File;", "fileIndex", _UrlKt.FRAGMENT_ENCODE_SET, "failed", "step", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public final class BottomUpDirectoryState extends DirectoryState {
            private boolean failed;
            private int fileIndex;
            private File[] fileList;
            private boolean rootVisited;
            final /* synthetic */ FileTreeWalkIterator this$0;

            public BottomUpDirectoryState(FileTreeWalkIterator r1, File r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // kotlin.io.FileTreeWalk.WalkState
            public File step() {
                if (this.failed == false) goto L5;
            L17:
                File[] r0 = this.fileList;
                if (r0 == null) goto L24;
                int r3 = this.fileIndex;
                if (r3 >= r0.length) goto L24;
                this.fileIndex = r3 + 1;
                return r0[r3];
            L24:
                if (this.rootVisited == true) goto L27;
                this.rootVisited = true;
                return getRoot();
            L27:
                Function1 r02 = FileTreeWalk.access$getOnLeave$p(this.this$0.this$0);
                if (r02 == null) goto L30;
                r02.invoke(getRoot());
            L30:
                return null;
            L5:
                if (this.fileList != null) goto L17;
                Function1 r03 = FileTreeWalk.access$getOnEnter$p(this.this$0.this$0);
                if (r03 != null) goto L9;
            L11:
                File[] r04 = getRoot().listFiles();
                this.fileList = r04;
                if (r04 != null) goto L17;
                Function2 r05 = FileTreeWalk.access$getOnFail$p(this.this$0.this$0);
                if (r05 == null) goto L16;
                r05.invoke(getRoot(), new AccessDeniedException(getRoot(), null, "Cannot list files in a directory", 2, null));
            L16:
                this.failed = true;
                goto L17
            L9:
                if (((Boolean) r03.invoke(getRoot())).booleanValue() == true) goto L11;
                return null;
            }
        }

        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\b\u001a\u0004\u0018\u00010\u0003H\u0096\u0080\u0004R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$SingleFileState;", "Lkotlin/io/FileTreeWalk$WalkState;", "rootFile", "Ljava/io/File;", "<init>", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "visited", _UrlKt.FRAGMENT_ENCODE_SET, "step", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nFileTreeWalk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileTreeWalk.kt\nkotlin/io/FileTreeWalk$FileTreeWalkIterator$SingleFileState\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,273:1\n1#2:274\n*E\n"})
        public final class SingleFileState extends WalkState {
            final /* synthetic */ FileTreeWalkIterator this$0;
            private boolean visited;

            public SingleFileState(FileTreeWalkIterator r1, File r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // kotlin.io.FileTreeWalk.WalkState
            public File step() {
                if (this.visited == false) goto L6;
                return null;
            L6:
                this.visited = true;
                return getRoot();
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\r\u001a\u0004\u0018\u00010\u0003H\u0096\u0080\u0004R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tX\u0082\u008e\b¢\u0006\u0004\n\u0002\u0010\nR\u000f\u0010\u000b\u001a\u00020\fX\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$TopDownDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "rootDir", "Ljava/io/File;", "<init>", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "rootVisited", _UrlKt.FRAGMENT_ENCODE_SET, "fileList", _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/io/File;", "fileIndex", _UrlKt.FRAGMENT_ENCODE_SET, "step", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public final class TopDownDirectoryState extends DirectoryState {
            private int fileIndex;
            private File[] fileList;
            private boolean rootVisited;
            final /* synthetic */ FileTreeWalkIterator this$0;

            public TopDownDirectoryState(FileTreeWalkIterator r1, File r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // kotlin.io.FileTreeWalk.WalkState
            public File step() {
                if (this.rootVisited == true) goto L11;
                Function1 r0 = FileTreeWalk.access$getOnEnter$p(this.this$0.this$0);
                if (r0 != null) goto L7;
            L9:
                this.rootVisited = true;
                return getRoot();
            L7:
                if (((Boolean) r0.invoke(getRoot())).booleanValue() == true) goto L9;
                return null;
            L11:
                File[] r02 = this.fileList;
                if (r02 != null) goto L14;
            L20:
                if (r02 != null) goto L34;
                File[] r03 = getRoot().listFiles();
                this.fileList = r03;
                if (r03 != null) goto L26;
                Function2 r04 = FileTreeWalk.access$getOnFail$p(this.this$0.this$0);
                if (r04 == null) goto L26;
                r04.invoke(getRoot(), new AccessDeniedException(getRoot(), null, "Cannot list files in a directory", 2, null));
            L26:
                File[] r05 = this.fileList;
                if (r05 != null) goto L29;
            L30:
                Function1 r06 = FileTreeWalk.access$getOnLeave$p(this.this$0.this$0);
                if (r06 == null) goto L33;
                r06.invoke(getRoot());
            L33:
                return null;
            L29:
                if (r05.length == 0) goto L30;
            L34:
                File[] r07 = this.fileList;
                int r1 = this.fileIndex;
                this.fileIndex = r1 + 1;
                return r07[r1];
            L14:
                if (this.fileIndex < r02.length) goto L20;
                Function1 r08 = FileTreeWalk.access$getOnLeave$p(this.this$0.this$0);
                if (r08 == null) goto L19;
                r08.invoke(getRoot());
            L19:
                return null;
            }
        }

        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

            static {
                int[] r0 = new int[FileWalkDirection.values().length];
                r0[FileWalkDirection.TOP_DOWN.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
            L9:
                r0[FileWalkDirection.BOTTOM_UP.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
            L5:
                $EnumSwitchMapping$0 = r0;
            }
        }

        public FileTreeWalkIterator(FileTreeWalk r3) {
            this.this$0 = r3;
            ArrayDeque<WalkState> r0 = new ArrayDeque();
            this.state = r0;
            if (FileTreeWalk.access$getStart$p(r3).isDirectory() == false) goto L7;
            r0.push(directoryState(FileTreeWalk.access$getStart$p(r3)));
            return;
        L7:
            if (FileTreeWalk.access$getStart$p(r3).isFile() == false) goto L10;
            r0.push(new SingleFileState(this, FileTreeWalk.access$getStart$p(r3)));
            return;
        L10:
            done();
        }

        private final DirectoryState directoryState(File r3) {
            FileWalkDirection r0 = FileTreeWalk.access$getDirection$p(this.this$0);
            int r02 = WhenMappings.$EnumSwitchMapping$0[r0.ordinal()];
            if (r02 == 1) goto L11;
            if (r02 == 2) goto L7;
            LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
            return null;
        L7:
            return new BottomUpDirectoryState(this, r3);
        L11:
            return new TopDownDirectoryState(this, r3);
        }

        private final File gotoNext() {
        L2:
            WalkState r0 = this.state.peek();
            if (r0 == null) goto L4;
            File r1 = r0.step();
            if (r1 == null) goto L8;
            if (Intrinsics.areEqual(r1, r0.getRoot()) == true) goto L17;
            if (r1.isDirectory() == false) goto L17;
            if (this.state.size() >= FileTreeWalk.access$getMaxDepth$p(this.this$0)) goto L17;
            this.state.push(directoryState(r1));
        L17:
            return r1;
        L8:
            this.state.pop();
            goto L2
        L4:
            return null;
        }

        @Override // kotlin.collections.AbstractIterator
        public void computeNext() {
            File r0 = gotoNext();
            if (r0 == null) goto L6;
            setNext(r0);
            return;
        L6:
            done();
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\"\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\b\u001a\u0004\u0018\u00010\u0003H¦\u0080\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lkotlin/io/FileTreeWalk$WalkState;", _UrlKt.FRAGMENT_ENCODE_SET, "root", "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "getRoot", "()Ljava/io/File;", "step", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static abstract class WalkState {
        private final File root;

        public WalkState(File r1) {
            this.root = r1;
        }

        public final File getRoot() {
            return this.root;
        }

        public abstract File step();
    }

    public FileTreeWalk(File r10, FileWalkDirection r11) {
        this(r10, r11, null, null, null, 0, 32, null);
    }

    public /* synthetic */ FileTreeWalk(File r1, FileWalkDirection r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = FileWalkDirection.TOP_DOWN;
    L5:
        this(r1, r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private FileTreeWalk(File r1, FileWalkDirection r2, Function1<? super File, Boolean> r3, Function1<? super File, Unit> r4, Function2<? super File, ? super IOException, Unit> r5, int r6) {
        this.start = r1;
        this.direction = r2;
        this.onEnter = r3;
        this.onLeave = r4;
        this.onFail = r5;
        this.maxDepth = r6;
    }

    public /* synthetic */ FileTreeWalk(File r8, FileWalkDirection r9, Function1 r10, Function1 r11, Function2 r12, int r13, int r14, DefaultConstructorMarker r15) {
        if ((r14 & 2) == 0) goto L5;
        r9 = FileWalkDirection.TOP_DOWN;
    L5:
        FileWalkDirection r2 = r9;
        if ((r14 & 32) == 0) goto L8;
        r13 = Integer.MAX_VALUE;
    L8:
        this(r8, r2, r10, r11, r12, r13);
    }

    public static final /* synthetic */ FileWalkDirection access$getDirection$p(FileTreeWalk r0) {
        return r0.direction;
    }

    public static final /* synthetic */ int access$getMaxDepth$p(FileTreeWalk r0) {
        return r0.maxDepth;
    }

    public static final /* synthetic */ Function1 access$getOnEnter$p(FileTreeWalk r0) {
        return r0.onEnter;
    }

    public static final /* synthetic */ Function2 access$getOnFail$p(FileTreeWalk r0) {
        return r0.onFail;
    }

    public static final /* synthetic */ Function1 access$getOnLeave$p(FileTreeWalk r0) {
        return r0.onLeave;
    }

    public static final /* synthetic */ File access$getStart$p(FileTreeWalk r0) {
        return r0.start;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<File> iterator() {
        return new FileTreeWalkIterator(this);
    }

    public final FileTreeWalk maxDepth(int r8) {
        if (r8 <= 0) goto L6;
        return new FileTreeWalk(this.start, this.direction, this.onEnter, this.onLeave, this.onFail, r8);
    L6:
        throw new IllegalArgumentException("depth must be positive, but was " + r8 + '.');
    }

    public final FileTreeWalk onEnter(Function1<? super File, Boolean> r8) {
        return new FileTreeWalk(this.start, this.direction, r8, this.onLeave, this.onFail, this.maxDepth);
    }

    public final FileTreeWalk onFail(Function2<? super File, ? super IOException, Unit> r8) {
        return new FileTreeWalk(this.start, this.direction, this.onEnter, this.onLeave, r8, this.maxDepth);
    }

    public final FileTreeWalk onLeave(Function1<? super File, Unit> r8) {
        return new FileTreeWalk(this.start, this.direction, this.onEnter, r8, this.onFail, this.maxDepth);
    }
}
