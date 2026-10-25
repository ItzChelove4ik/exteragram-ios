package org.simplifiles.internal.io;

import j$.nio.file.Files;
import j$.nio.file.Path;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import org.simplifiles.files.SymlinkPolicy;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/simplifiles/internal/io/SymlinkSupport;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "j$/nio/file/Path", "path", "Lorg/simplifiles/files/SymlinkPolicy;", "policy", "Lorg/simplifiles/internal/io/SymlinkDecision;", "decide", "(Lj$/nio/file/Path;Lorg/simplifiles/files/SymlinkPolicy;)Lorg/simplifiles/internal/io/SymlinkDecision;", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SymlinkSupport {
    public static final SymlinkSupport INSTANCE = null;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[SymlinkPolicy.values().length];
            r0[SymlinkPolicy.FOLLOW.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L11:
            r0[SymlinkPolicy.SKIP.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L15:
            r0[SymlinkPolicy.ERROR.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L6:
            $EnumSwitchMapping$0 = r0;
        }
    }

    static {
        INSTANCE = new SymlinkSupport();
    }

    private SymlinkSupport() {
    }

    public final SymlinkDecision decide(Path r1, SymlinkPolicy r2) {
        if (Files.isSymbolicLink(r1) == false) goto L5;
        int r0 = WhenMappings.$EnumSwitchMapping$0[r2.ordinal()];
        if (r0 == 1) goto L19;
        if (r0 == 2) goto L17;
        if (r0 == 3) goto L13;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L13:
        return SymlinkDecision.FAIL;
    L17:
        return SymlinkDecision.SKIP;
    L19:
        return SymlinkDecision.INCLUDE;
    L5:
        return SymlinkDecision.INCLUDE;
    }
}
