package androidx.camera.camera2.compat.workaround;

import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.core.impl.DeferrableSurface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u0014\u001a\u00020\nH\u0016J\u001a\u0010\u0015\u001a\u00020\n*\b\u0012\u0004\u0012\u00020\b0\u00162\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/camera/camera2/compat/workaround/InactiveSurfaceCloserImpl;", "Landroidx/camera/camera2/compat/workaround/InactiveSurfaceCloser;", "<init>", "()V", "lock", _UrlKt.FRAGMENT_ENCODE_SET, "configuredOutputs", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/compat/workaround/InactiveSurfaceCloserImpl$ConfiguredOutput;", "configure", _UrlKt.FRAGMENT_ENCODE_SET, "streamId", "Landroidx/camera/camera2/pipe/StreamId;", "deferrableSurface", "Landroidx/camera/core/impl/DeferrableSurface;", "graph", "Landroidx/camera/camera2/pipe/CameraGraph;", "configure-hB7JTeY", "(ILandroidx/camera/core/impl/DeferrableSurface;Landroidx/camera/camera2/pipe/CameraGraph;)V", "onSurfaceInactive", "closeAll", "closeIfConfigured", _UrlKt.FRAGMENT_ENCODE_SET, "ConfiguredOutput", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nInactiveSurfaceCloser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InactiveSurfaceCloser.kt\nandroidx/camera/camera2/compat/workaround/InactiveSurfaceCloserImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,132:1\n1#2:133\n1869#3,2:134\n1869#3,2:136\n*S KotlinDebug\n*F\n+ 1 InactiveSurfaceCloser.kt\nandroidx/camera/camera2/compat/workaround/InactiveSurfaceCloserImpl\n*L\n87#1:134,2\n108#1:136,2\n*E\n"})
/* loaded from: classes3.dex */
public final class InactiveSurfaceCloserImpl implements InactiveSurfaceCloser {
    private final List<ConfiguredOutput> configuredOutputs;
    private final Object lock;

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0017\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0019\u001a\u0004\b\u001a\u0010\u0015R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Landroidx/camera/camera2/compat/workaround/InactiveSurfaceCloserImpl$ConfiguredOutput;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "streamId", "Landroidx/camera/core/impl/DeferrableSurface;", "deferrableSurface", "Landroidx/camera/camera2/pipe/CameraGraph;", "graph", "<init>", "(ILandroidx/camera/core/impl/DeferrableSurface;Landroidx/camera/camera2/pipe/CameraGraph;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "close", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "contains", "(Landroidx/camera/core/impl/DeferrableSurface;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "I", "getStreamId-ptHMqGs", "Landroidx/camera/core/impl/DeferrableSurface;", "getDeferrableSurface", "()Landroidx/camera/core/impl/DeferrableSurface;", "Landroidx/camera/camera2/pipe/CameraGraph;", "getGraph", "()Landroidx/camera/camera2/pipe/CameraGraph;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ConfiguredOutput {
        private final DeferrableSurface deferrableSurface;
        private final CameraGraph graph;
        private final int streamId;

        private ConfiguredOutput(int r1, DeferrableSurface r2, CameraGraph r3) {
            this.streamId = r1;
            this.deferrableSurface = r2;
            this.graph = r3;
        }

        public /* synthetic */ ConfiguredOutput(int r1, DeferrableSurface r2, CameraGraph r3, DefaultConstructorMarker r4) {
            this(r1, r2, r3);
        }

        public final void close() {
            this.graph.mo226setSurfaceNYG5g8E(this.streamId, null);
            this.deferrableSurface.close();
        }

        public final boolean contains(DeferrableSurface r1) {
            return Intrinsics.areEqual(this.deferrableSurface, r1);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof ConfiguredOutput) == true) goto L8;
            return false;
        L8:
            ConfiguredOutput r52 = (ConfiguredOutput) r5;
            if (StreamId.m404equalsimpl0(this.streamId, r52.streamId) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.deferrableSurface, r52.deferrableSurface) == true) goto L15;
            return false;
        L15:
            if (Intrinsics.areEqual(this.graph, r52.graph) == true) goto L17;
            return false;
        L17:
            return true;
        }

        public int hashCode() {
            return (((StreamId.m405hashCodeimpl(this.streamId) * 31) + this.deferrableSurface.hashCode()) * 31) + this.graph.hashCode();
        }

        public String toString() {
            return "ConfiguredOutput(streamId=" + StreamId.m406toStringimpl(this.streamId) + ", deferrableSurface=" + this.deferrableSurface + ", graph=" + this.graph + ')';
        }
    }

    public InactiveSurfaceCloserImpl() {
        this.lock = new Object();
        this.configuredOutputs = new ArrayList();
    }

    private final void closeIfConfigured(List<ConfiguredOutput> r1, DeferrableSurface r2) {
        Iterator<T> r0 = r1.iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        if (((ConfiguredOutput) r0.next()).contains(r2) == false) goto L4;
        r2.close();
        goto L4
    }

    @Override // androidx.camera.camera2.compat.workaround.InactiveSurfaceCloser
    public void closeAll() {
        Object r0 = this.lock;
        monitor-enter(r0);
        Iterator<T> r1 = this.configuredOutputs.iterator();     // Catch: Throwable -> L8
    L6:
        if (r1.hasNext() == false) goto L10;
        ((ConfiguredOutput) r1.next()).close();     // Catch: Throwable -> L8
        goto L6
    L10:
        this.configuredOutputs.clear();     // Catch: Throwable -> L8
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(r0);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.compat.workaround.InactiveSurfaceCloser
    /* renamed from: configure-hB7JTeY */
    public void mo34configurehB7JTeY(int r4, DeferrableSurface r5, CameraGraph r6) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.configuredOutputs.add(new ConfiguredOutput(r4, r5, r6, null));     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.compat.workaround.InactiveSurfaceCloser
    public void onSurfaceInactive(DeferrableSurface r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
        closeIfConfigured(this.configuredOutputs, r3);     // Catch: Throwable -> L7
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
