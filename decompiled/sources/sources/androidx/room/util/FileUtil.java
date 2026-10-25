package androidx.room.util;

import android.annotation.SuppressLint;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"copy", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.TYPE_INPUT, "Ljava/nio/channels/ReadableByteChannel;", "output", "Ljava/nio/channels/FileChannel;", "room-runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
@JvmName(name = "FileUtil")
@SourceDebugExtension({"SMAP\nFileUtil.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileUtil.android.kt\nandroidx/room/util/FileUtil\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,59:1\n1#2:60\n*E\n"})
/* loaded from: classes4.dex */
public abstract class FileUtil {
    @SuppressLint({"LambdaLast"})
    public static final void copy(ReadableByteChannel r7, FileChannel r8) {
        r8.transferFrom(r7, 0, LongCompanionObject.MAX_VALUE);     // Catch: Throwable -> L6
        r8.force(false);     // Catch: Throwable -> L6
        r7.close();
        r8.close();
        return;
    L6:
        th = move-exception;
        r7.close();
        r8.close();
        throw th;
    }
}
