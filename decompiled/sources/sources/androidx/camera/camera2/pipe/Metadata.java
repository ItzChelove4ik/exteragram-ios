package androidx.camera.camera2.pipe;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001:\u0001\nJ$\u0010\u0002\u001a\u0004\u0018\u0001H\u0003\"\u0004\b\u0000\u0010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0005H¦\u0002¢\u0006\u0002\u0010\u0006J)\u0010\u0007\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00052\u0006\u0010\b\u001a\u0002H\u0003H&¢\u0006\u0002\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/Metadata;", _UrlKt.FRAGMENT_ENCODE_SET, "get", "T", PluginsConstants.Settings.KEY, "Landroidx/camera/camera2/pipe/Metadata$Key;", "(Landroidx/camera/camera2/pipe/Metadata$Key;)Ljava/lang/Object;", "getOrDefault", PluginsConstants.Settings.DEFAULT, "(Landroidx/camera/camera2/pipe/Metadata$Key;Ljava/lang/Object;)Ljava/lang/Object;", "Key", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface Metadata {

    @kotlin.Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \n*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\nB\u001d\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\t\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/pipe/Metadata$Key;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "name", _UrlKt.FRAGMENT_ENCODE_SET, "type", "Lkotlin/reflect/KClass;", "<init>", "(Ljava/lang/String;Lkotlin/reflect/KClass;)V", "toString", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Key<T> {
        public static final Companion Companion = null;
        private static final Map<String, Key<?>> keys = null;
        private final String name;
        private final KClass<?> type;

        @kotlin.Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J3\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\t\"\b\b\u0001\u0010\u0004*\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007¢\u0006\u0004\b\n\u0010\u000bR0\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\f8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u0012\u0004\b\u0011\u0010\u0003\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Landroidx/camera/camera2/pipe/Metadata$Key$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "T", _UrlKt.FRAGMENT_ENCODE_SET, "name", "Lkotlin/reflect/KClass;", "type", "Landroidx/camera/camera2/pipe/Metadata$Key;", "create", "(Ljava/lang/String;Lkotlin/reflect/KClass;)Landroidx/camera/camera2/pipe/Metadata$Key;", _UrlKt.FRAGMENT_ENCODE_SET, "keys", "Ljava/util/Map;", "getKeys$camera_camera2_pipe", "()Ljava/util/Map;", "getKeys$camera_camera2_pipe$annotations", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nMetadata.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Metadata.kt\nandroidx/camera/camera2/pipe/Metadata$Key$Companion\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,63:1\n384#2,7:64\n*S KotlinDebug\n*F\n+ 1 Metadata.kt\nandroidx/camera/camera2/pipe/Metadata$Key$Companion\n*L\n52#1:64,7\n*E\n"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final <T> Key<T> create(String r4, KClass<T> r5) {
                Map<String, Key<?>> r3 = getKeys$camera_camera2_pipe();
                monitor-enter(r3);
                Map<String, Key<?>> r0 = Key.Companion.getKeys$camera_camera2_pipe();     // Catch: Throwable -> L7
                Object r1 = r0.get(r4);     // Catch: Throwable -> L7
                if (r1 != null) goto L9;
                r1 = new Key(r4, r5, null);     // Catch: Throwable -> L7
                r0.put(r4, r1);     // Catch: Throwable -> L7
            L9:
                Key<T> r12 = (Key) r1;     // Catch: Throwable -> L7
                if (Intrinsics.areEqual(Key.access$getType$p(r12), r5) == false) goto L14;
                monitor-exit(r3);
                return r12;
            L14:
                throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L7
            L7:
                th = move-exception;
                throw th;
            }

            public final Map<String, Key<?>> getKeys$camera_camera2_pipe() {
                return Key.access$getKeys$cp();
            }
        }

        static {
            Companion = new Companion(null);
            keys = new HashMap();
        }

        private Key(String r1, KClass<?> r2) {
            this.name = r1;
            this.type = r2;
        }

        public /* synthetic */ Key(String r1, KClass r2, DefaultConstructorMarker r3) {
            this(r1, r2);
        }

        public static final /* synthetic */ Map access$getKeys$cp() {
            return keys;
        }

        public static final /* synthetic */ KClass access$getType$p(Key r0) {
            return r0.type;
        }

        public String toString() {
            return "Metadata.Key(" + this.name + ')';
        }
    }

    <T> T get(Key<T> r1);

    <T> T getOrDefault(Key<T> r1, T r2);
}
