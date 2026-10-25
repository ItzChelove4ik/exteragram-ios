package androidx.camera.camera2.pipe;

import android.hardware.camera2.params.MeteringRectangle;
import android.os.Build;
import androidx.camera.camera2.pipe.CameraStream;
import androidx.camera.camera2.pipe.InputStream;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.compat.Camera2Quirks;
import androidx.camera.camera2.pipe.core.Log;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.RandomKt$$ExternalSyntheticBUOutline0;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

@kotlin.Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0007\u0004\u0005\u0006\u0007\b\t\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/CameraGraph;", "Landroidx/camera/camera2/pipe/CameraGraphBase;", "Landroidx/camera/camera2/pipe/CameraGraph$Session;", "Landroidx/camera/camera2/pipe/CameraControls3A;", "Config", "ConcurrentConfig", "RepeatingRequestRequirementsBeforeCapture", "Flags", "OperatingMode", "Constants3A", "Session", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraGraph extends CameraGraphBase<Session>, CameraControls3A {

    @kotlin.Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/pipe/CameraGraph$ConcurrentConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "graphConfigs", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "<init>", "(Ljava/util/List;)V", "getGraphConfigs", "()Ljava/util/List;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCameraGraph.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraGraph.kt\nandroidx/camera/camera2/pipe/CameraGraph$ConcurrentConfig\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,835:1\n1740#2,3:836\n1563#2:839\n1634#2,3:840\n*S KotlinDebug\n*F\n+ 1 CameraGraph.kt\nandroidx/camera/camera2/pipe/CameraGraph$ConcurrentConfig\n*L\n113#1:836,3\n117#1:839\n117#1:840,3\n*E\n"})
    public static final class ConcurrentConfig {
        private final List<Config> graphConfigs;

        public ConcurrentConfig(List<Config> r7) {
            this.graphConfigs = r7;
            if (r7.size() < 2) goto L36;
            Config r0 = (Config) CollectionsKt.first(r7);
            List<Config> r72 = r7;
            boolean r3 = false;
            if ((r72 instanceof Collection) == true) goto L7;
        L9:
            Iterator<T> r73 = r72.iterator();
        L11:
            if (r73.hasNext() == false) goto L8;
            String r1 = ((Config) r73.next()).m201getCameraBackendIdAKmI2lo();
            String r5 = r0.m201getCameraBackendIdAKmI2lo();
            if (r1 != null) goto L17;
            if (r5 != null) goto L16;
            boolean r12 = true;
        L20:
            if (r12 == true) goto L11;
            boolean r74 = false;
        L22:
            if (r74 == false) goto L34;
            List<Config> r75 = this.graphConfigs;
            ArrayList r02 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r75, 10));
            Iterator<T> r76 = r75.iterator();
        L25:
            if (r76.hasNext() == false) goto L28;
            r02.add(CameraId.m227boximpl(((Config) r76.next()).m200getCameraDz_R5H8()));
            goto L25
        L28:
            if (CollectionsKt.distinct(r02).size() != this.graphConfigs.size()) goto L30;
            r3 = true;
        L30:
            if (r3 == false) goto L32;
            return;
        L32:
            Segment$$ExternalSyntheticBUOutline1.m("Each CameraGraph.Config must have a distinct camera id!");
            throw null;
        L34:
            Segment$$ExternalSyntheticBUOutline1.m("Each CameraGraph.Config must use the same camera backend!");
            throw null;
        L16:
            r12 = false;
            goto L20
        L17:
            if (r5 == null) goto L16;
            r12 = CameraBackendId.m154equalsimpl0(r1, r5);
        L8:
            r74 = true;
            goto L22
        L7:
            if (r72.isEmpty() == false) goto L9;
        L36:
            Segment$$ExternalSyntheticBUOutline1.m("Cannot create ConcurrentGraphConfig without 2 or more CameraGraph.Config(s)");
            throw null;
        }

        public final List<Config> getGraphConfigs() {
            return this.graphConfigs;
        }
    }

    @kotlin.Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001B\u0089\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0004\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0004\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\r\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000b\u0012\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\r\u0012\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0004\u0012\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0004\u0012\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\r\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0018\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u001c\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u001e\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b\"\u0010#J\u0010\u0010%\u001a\u00020$HÖ\u0001¢\u0006\u0004\b%\u0010&J\u0010\u0010(\u001a\u00020'HÖ\u0001¢\u0006\u0004\b(\u0010)J\u001a\u0010,\u001a\u00020+2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b,\u0010-R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010.\u001a\u0004\b/\u0010&R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u00100\u001a\u0004\b1\u00102R#\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u00100\u001a\u0004\b3\u00102R\u001f\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\t\u00100\u001a\u0004\b4\u00102R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\n\u00105\u001a\u0004\b6\u00107R\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u00108\u001a\u0004\b9\u0010)R#\u0010\u000e\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010:\u001a\u0004\b;\u0010<R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u00108\u001a\u0004\b=\u0010)R\u0017\u0010\u0011\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\u0011\u00108\u001a\u0004\b>\u0010)R#\u0010\u0012\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\r8\u0006¢\u0006\f\n\u0004\b\u0012\u0010:\u001a\u0004\b?\u0010<R\u001d\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00048\u0006¢\u0006\f\n\u0004\b\u0014\u00100\u001a\u0004\b@\u00102R\u001d\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00048\u0006¢\u0006\f\n\u0004\b\u0016\u00100\u001a\u0004\bA\u00102R#\u0010\u0017\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\r8\u0006¢\u0006\f\n\u0004\b\u0017\u0010:\u001a\u0004\bB\u0010<R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010.\u001a\u0004\bC\u0010&R\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0006¢\u0006\f\n\u0004\b\u001b\u0010D\u001a\u0004\bE\u0010FR\u0017\u0010\u001d\u001a\u00020\u001c8\u0006¢\u0006\f\n\u0004\b\u001d\u0010G\u001a\u0004\bH\u0010IR\u0017\u0010\u001f\u001a\u00020\u001e8\u0006¢\u0006\f\n\u0004\b\u001f\u0010J\u001a\u0004\bK\u0010LR\u0019\u0010!\u001a\u0004\u0018\u00010 8\u0006¢\u0006\f\n\u0004\b!\u0010.\u001a\u0004\bM\u0010&R$\u0010O\u001a\u0004\u0018\u00010N8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bO\u0010P\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010T¨\u0006U"}, d2 = {"Landroidx/camera/camera2/pipe/CameraGraph$Config;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraId;", "camera", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraStream$Config;", "streams", "exclusiveStreamGroups", "Landroidx/camera/camera2/pipe/InputStream$Config;", PluginsConstants.Settings.TYPE_INPUT, "postviewStream", "Landroidx/camera/camera2/pipe/RequestTemplate;", "sessionTemplate", _UrlKt.FRAGMENT_ENCODE_SET, "sessionParameters", "Landroidx/camera/camera2/pipe/CameraGraph$OperatingMode;", "sessionMode", "defaultTemplate", "defaultParameters", "Landroidx/camera/camera2/pipe/Request$Listener;", "defaultListeners", "Landroidx/camera/camera2/pipe/GraphStateListener;", "graphStateListeners", "requiredParameters", "Landroidx/camera/camera2/pipe/CameraBackendId;", "cameraBackendId", "Landroidx/camera/camera2/pipe/CameraBackendFactory;", "customCameraBackend", "Landroidx/camera/camera2/pipe/MetadataTransform;", "metadataTransform", "Landroidx/camera/camera2/pipe/CameraGraph$Flags;", "flags", "Landroidx/camera/camera2/pipe/CameraColorSpace;", "sessionColorSpace", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Landroidx/camera/camera2/pipe/CameraStream$Config;ILjava/util/Map;IILjava/util/Map;Ljava/util/List;Ljava/util/List;Ljava/util/Map;Ljava/lang/String;Landroidx/camera/camera2/pipe/CameraBackendFactory;Landroidx/camera/camera2/pipe/MetadataTransform;Landroidx/camera/camera2/pipe/CameraGraph$Flags;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getCamera-Dz_R5H8", "Ljava/util/List;", "getStreams", "()Ljava/util/List;", "getExclusiveStreamGroups", "getInput", "Landroidx/camera/camera2/pipe/CameraStream$Config;", "getPostviewStream", "()Landroidx/camera/camera2/pipe/CameraStream$Config;", "I", "getSessionTemplate-fGx8uWA", "Ljava/util/Map;", "getSessionParameters", "()Ljava/util/Map;", "getSessionMode-2uNL3no", "getDefaultTemplate-fGx8uWA", "getDefaultParameters", "getDefaultListeners", "getGraphStateListeners", "getRequiredParameters", "getCameraBackendId-AKmI2lo", "Landroidx/camera/camera2/pipe/CameraBackendFactory;", "getCustomCameraBackend", "()Landroidx/camera/camera2/pipe/CameraBackendFactory;", "Landroidx/camera/camera2/pipe/MetadataTransform;", "getMetadataTransform", "()Landroidx/camera/camera2/pipe/MetadataTransform;", "Landroidx/camera/camera2/pipe/CameraGraph$Flags;", "getFlags", "()Landroidx/camera/camera2/pipe/CameraGraph$Flags;", "getSessionColorSpace-dxVZaPA", "Landroidx/camera/camera2/pipe/ConcurrentCameraGraphs;", "concurrentCameraGraphs", "Landroidx/camera/camera2/pipe/ConcurrentCameraGraphs;", "getConcurrentCameraGraphs$camera_camera2_pipe", "()Landroidx/camera/camera2/pipe/ConcurrentCameraGraphs;", "setConcurrentCameraGraphs$camera_camera2_pipe", "(Landroidx/camera/camera2/pipe/ConcurrentCameraGraphs;)V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Config {
        private final String camera;
        private final String cameraBackendId;
        private ConcurrentCameraGraphs concurrentCameraGraphs;
        private final CameraBackendFactory customCameraBackend;
        private final List<Request.Listener> defaultListeners;
        private final Map<?, Object> defaultParameters;
        private final int defaultTemplate;
        private final List<List<CameraStream.Config>> exclusiveStreamGroups;
        private final Flags flags;
        private final List<GraphStateListener> graphStateListeners;
        private final List<InputStream.Config> input;
        private final MetadataTransform metadataTransform;
        private final CameraStream.Config postviewStream;
        private final Map<?, Object> requiredParameters;
        private final String sessionColorSpace;
        private final int sessionMode;
        private final Map<?, Object> sessionParameters;
        private final int sessionTemplate;
        private final List<CameraStream.Config> streams;

        /* JADX WARN: Multi-variable type inference failed */
        private Config(String r3, List<CameraStream.Config> r4, List<? extends List<CameraStream.Config>> r5, List<InputStream.Config> r6, CameraStream.Config r7, int r8, Map<?, ? extends Object> r9, int r10, int r11, Map<?, ? extends Object> r12, List<? extends Request.Listener> r13, List<? extends GraphStateListener> r14, Map<?, ? extends Object> r15, String r16, CameraBackendFactory r17, MetadataTransform r18, Flags r19, String r20) {
            this.camera = r3;
            this.streams = r4;
            this.exclusiveStreamGroups = r5;
            this.input = r6;
            this.postviewStream = r7;
            this.sessionTemplate = r8;
            this.sessionParameters = r9;
            this.sessionMode = r10;
            this.defaultTemplate = r11;
            this.defaultParameters = r12;
            this.defaultListeners = r13;
            this.graphStateListeners = r14;
            this.requiredParameters = r15;
            this.cameraBackendId = r16;
            this.customCameraBackend = r17;
            this.metadataTransform = r18;
            this.flags = r19;
            this.sessionColorSpace = r20;
            if (r16 == null) goto L8;
            if (r17 == null) goto L9;
            Segment$$ExternalSyntheticBUOutline1.m("Setting both cameraBackendId and customCameraBackend is not supported.");
            throw null;
        L9:
            return;
        }

        public /* synthetic */ Config(String r28, List r29, List r30, List r31, CameraStream.Config r32, int r33, Map r34, int r35, int r36, Map r37, List r38, List r39, Map r40, String r41, CameraBackendFactory r42, MetadataTransform r43, Flags r44, String r45, int r46, DefaultConstructorMarker r47) {
            if ((r46 & 4) == 0) goto L5;
            List r5 = CollectionsKt.emptyList();
        L7:
            if ((r46 & 8) == 0) goto L9;
            List r6 = null;
        L11:
            if ((r46 & 16) == 0) goto L13;
            CameraStream.Config r7 = null;
        L15:
            if ((r46 & 32) == 0) goto L17;
            int r8 = RequestTemplate.m371constructorimpl(1);
        L19:
            if ((r46 & 64) == 0) goto L21;
            Map r9 = MapsKt.emptyMap();
        L23:
            if ((r46 & 128) == 0) goto L25;
            int r10 = OperatingMode.Companion.m222getNORMAL2uNL3no();
        L27:
            if ((r46 & 256) == 0) goto L29;
            int r11 = RequestTemplate.m371constructorimpl(1);
        L31:
            if ((r46 & 512) == 0) goto L33;
            Map r12 = MapsKt.emptyMap();
        L35:
            if ((r46 & 1024) == 0) goto L37;
            List r13 = CollectionsKt.emptyList();
        L39:
            if ((r46 & 2048) == 0) goto L41;
            List r14 = CollectionsKt.emptyList();
        L43:
            if ((r46 & 4096) == 0) goto L45;
            Map r15 = MapsKt.emptyMap();
        L47:
            if ((r46 & 8192) == 0) goto L49;
            String r16 = null;
        L51:
            if ((r46 & 16384) == 0) goto L53;
            CameraBackendFactory r17 = null;
        L55:
            if ((32768 & r46) == 0) goto L57;
            MetadataTransform r18 = new MetadataTransform(0, 0, null, 7, null);
        L59:
            if ((65536 & r46) == 0) goto L61;
            Flags r19 = new Flags(false, false, null, null, 0, false, false, false, 255, null);
        L63:
            if ((r46 & 131072) == 0) goto L65;
            String r20 = null;
        L66:
            this(r28, r29, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, null);
            return;
        L65:
            r20 = r45;
            goto L66
        L61:
            r19 = r44;
            goto L63
        L57:
            r18 = r43;
            goto L59
        L53:
            r17 = r42;
            goto L55
        L49:
            r16 = r41;
            goto L51
        L45:
            r15 = r40;
            goto L47
        L41:
            r14 = r39;
            goto L43
        L37:
            r13 = r38;
            goto L39
        L33:
            r12 = r37;
            goto L35
        L29:
            r11 = r36;
            goto L31
        L25:
            r10 = r35;
            goto L27
        L21:
            r9 = r34;
            goto L23
        L17:
            r8 = r33;
            goto L19
        L13:
            r7 = r32;
            goto L15
        L9:
            r6 = r31;
            goto L11
        L5:
            r5 = r30;
            goto L7
        }

        public /* synthetic */ Config(String r1, List r2, List r3, List r4, CameraStream.Config r5, int r6, Map r7, int r8, int r9, Map r10, List r11, List r12, Map r13, String r14, CameraBackendFactory r15, MetadataTransform r16, Flags r17, String r18, DefaultConstructorMarker r19) {
            this(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof Config) == true) goto L8;
            return false;
        L8:
            Config r52 = (Config) r5;
            if (CameraId.m230equalsimpl0(this.camera, r52.camera) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.streams, r52.streams) == true) goto L15;
            return false;
        L15:
            if (Intrinsics.areEqual(this.exclusiveStreamGroups, r52.exclusiveStreamGroups) == true) goto L18;
            return false;
        L18:
            if (Intrinsics.areEqual(this.input, r52.input) == true) goto L21;
            return false;
        L21:
            if (Intrinsics.areEqual(this.postviewStream, r52.postviewStream) == true) goto L24;
            return false;
        L24:
            if (RequestTemplate.m373equalsimpl0(this.sessionTemplate, r52.sessionTemplate) == true) goto L27;
            return false;
        L27:
            if (Intrinsics.areEqual(this.sessionParameters, r52.sessionParameters) == true) goto L30;
            return false;
        L30:
            if (OperatingMode.m216equalsimpl0(this.sessionMode, r52.sessionMode) == true) goto L33;
            return false;
        L33:
            if (RequestTemplate.m373equalsimpl0(this.defaultTemplate, r52.defaultTemplate) == true) goto L36;
            return false;
        L36:
            if (Intrinsics.areEqual(this.defaultParameters, r52.defaultParameters) == true) goto L39;
            return false;
        L39:
            if (Intrinsics.areEqual(this.defaultListeners, r52.defaultListeners) == true) goto L42;
            return false;
        L42:
            if (Intrinsics.areEqual(this.graphStateListeners, r52.graphStateListeners) == true) goto L45;
            return false;
        L45:
            if (Intrinsics.areEqual(this.requiredParameters, r52.requiredParameters) == true) goto L47;
            return false;
        L47:
            String r1 = this.cameraBackendId;
            String r3 = r52.cameraBackendId;
            if (r1 != null) goto L52;
            if (r3 != null) goto L51;
            boolean r12 = true;
        L55:
            if (r12 == true) goto L58;
            return false;
        L58:
            if (Intrinsics.areEqual(this.customCameraBackend, r52.customCameraBackend) == true) goto L61;
            return false;
        L61:
            if (Intrinsics.areEqual(this.metadataTransform, r52.metadataTransform) == true) goto L64;
            return false;
        L64:
            if (Intrinsics.areEqual(this.flags, r52.flags) == true) goto L66;
            return false;
        L66:
            String r4 = this.sessionColorSpace;
            String r53 = r52.sessionColorSpace;
            if (r4 != null) goto L71;
            if (r53 != null) goto L70;
            boolean r42 = true;
        L74:
            if (r42 == true) goto L76;
            return false;
        L76:
            return true;
        L70:
            r42 = false;
            goto L74
        L71:
            if (r53 == null) goto L70;
            r42 = CameraColorSpace.m160equalsimpl0(r4, r53);
        L51:
            r12 = false;
            goto L55
        L52:
            if (r3 == null) goto L51;
            r12 = CameraBackendId.m154equalsimpl0(r1, r3);
            goto L55
        }

        /* renamed from: getCamera-Dz_R5H8, reason: not valid java name */
        public final String m200getCameraDz_R5H8() {
            return this.camera;
        }

        /* renamed from: getCameraBackendId-AKmI2lo, reason: not valid java name */
        public final String m201getCameraBackendIdAKmI2lo() {
            return this.cameraBackendId;
        }

        public final ConcurrentCameraGraphs getConcurrentCameraGraphs$camera_camera2_pipe() {
            return this.concurrentCameraGraphs;
        }

        public final CameraBackendFactory getCustomCameraBackend() {
            return this.customCameraBackend;
        }

        public final List<Request.Listener> getDefaultListeners() {
            return this.defaultListeners;
        }

        public final Map<?, Object> getDefaultParameters() {
            return this.defaultParameters;
        }

        /* renamed from: getDefaultTemplate-fGx8uWA, reason: not valid java name */
        public final int m202getDefaultTemplatefGx8uWA() {
            return this.defaultTemplate;
        }

        public final List<List<CameraStream.Config>> getExclusiveStreamGroups() {
            return this.exclusiveStreamGroups;
        }

        public final Flags getFlags() {
            return this.flags;
        }

        public final List<GraphStateListener> getGraphStateListeners() {
            return this.graphStateListeners;
        }

        public final List<InputStream.Config> getInput() {
            return this.input;
        }

        public final CameraStream.Config getPostviewStream() {
            return this.postviewStream;
        }

        public final Map<?, Object> getRequiredParameters() {
            return this.requiredParameters;
        }

        /* renamed from: getSessionColorSpace-dxVZaPA, reason: not valid java name */
        public final String m203getSessionColorSpacedxVZaPA() {
            return this.sessionColorSpace;
        }

        /* renamed from: getSessionMode-2uNL3no, reason: not valid java name */
        public final int m204getSessionMode2uNL3no() {
            return this.sessionMode;
        }

        public final Map<?, Object> getSessionParameters() {
            return this.sessionParameters;
        }

        /* renamed from: getSessionTemplate-fGx8uWA, reason: not valid java name */
        public final int m205getSessionTemplatefGx8uWA() {
            return this.sessionTemplate;
        }

        public final List<CameraStream.Config> getStreams() {
            return this.streams;
        }

        public int hashCode() {
            int r0 = ((((CameraId.m231hashCodeimpl(this.camera) * 31) + this.streams.hashCode()) * 31) + this.exclusiveStreamGroups.hashCode()) * 31;
            List<InputStream.Config> r1 = this.input;
            int r2 = 0;
            if (r1 != null) goto L5;
            int r12 = 0;
        L6:
            int r02 = (r0 + r12) * 31;
            CameraStream.Config r13 = this.postviewStream;
            if (r13 != null) goto L9;
            int r14 = 0;
        L10:
            int r03 = (((((((((((((((((r02 + r14) * 31) + RequestTemplate.m375hashCodeimpl(this.sessionTemplate)) * 31) + this.sessionParameters.hashCode()) * 31) + OperatingMode.m217hashCodeimpl(this.sessionMode)) * 31) + RequestTemplate.m375hashCodeimpl(this.defaultTemplate)) * 31) + this.defaultParameters.hashCode()) * 31) + this.defaultListeners.hashCode()) * 31) + this.graphStateListeners.hashCode()) * 31) + this.requiredParameters.hashCode()) * 31;
            String r15 = this.cameraBackendId;
            if (r15 != null) goto L13;
            int r16 = 0;
        L14:
            int r04 = (r03 + r16) * 31;
            CameraBackendFactory r17 = this.customCameraBackend;
            if (r17 != null) goto L17;
            int r18 = 0;
        L18:
            int r05 = (((((r04 + r18) * 31) + this.metadataTransform.hashCode()) * 31) + this.flags.hashCode()) * 31;
            String r3 = this.sessionColorSpace;
            if (r3 == null) goto L23;
            r2 = CameraColorSpace.m161hashCodeimpl(r3);
        L23:
            return r05 + r2;
        L17:
            r18 = r17.hashCode();
            goto L18
        L13:
            r16 = CameraBackendId.m155hashCodeimpl(r15);
            goto L14
        L9:
            r14 = r13.hashCode();
            goto L10
        L5:
            r12 = r1.hashCode();
            goto L6
        }

        public final void setConcurrentCameraGraphs$camera_camera2_pipe(ConcurrentCameraGraphs r1) {
            this.concurrentCameraGraphs = r1;
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder("Config(camera=");
            r0.append(CameraId.m232toStringimpl(this.camera));
            r0.append(", streams=");
            r0.append(this.streams);
            r0.append(", exclusiveStreamGroups=");
            r0.append(this.exclusiveStreamGroups);
            r0.append(", input=");
            r0.append(this.input);
            r0.append(", postviewStream=");
            r0.append(this.postviewStream);
            r0.append(", sessionTemplate=");
            r0.append(RequestTemplate.m376toStringimpl(this.sessionTemplate));
            r0.append(", sessionParameters=");
            r0.append(this.sessionParameters);
            r0.append(", sessionMode=");
            r0.append(OperatingMode.m218toStringimpl(this.sessionMode));
            r0.append(", defaultTemplate=");
            r0.append(RequestTemplate.m376toStringimpl(this.defaultTemplate));
            r0.append(", defaultParameters=");
            r0.append(this.defaultParameters);
            r0.append(", defaultListeners=");
            r0.append(this.defaultListeners);
            r0.append(", graphStateListeners=");
            r0.append(this.graphStateListeners);
            r0.append(", requiredParameters=");
            r0.append(this.requiredParameters);
            r0.append(", cameraBackendId=");
            String r1 = this.cameraBackendId;
            String r2 = "null";
            if (r1 != null) goto L5;
            String r12 = "null";
        L6:
            r0.append(r12);
            r0.append(", customCameraBackend=");
            r0.append(this.customCameraBackend);
            r0.append(", metadataTransform=");
            r0.append(this.metadataTransform);
            r0.append(", flags=");
            r0.append(this.flags);
            r0.append(", sessionColorSpace=");
            String r3 = this.sessionColorSpace;
            if (r3 == null) goto L10;
            r2 = CameraColorSpace.m163toStringimpl(r3);
        L10:
            r0.append(r2);
            r0.append(')');
            return r0.toString();
        L5:
            r12 = CameraBackendId.m156toStringimpl(r1);
            goto L6
        }
    }

    @kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\n\u0010\u0007\u001a\u0004\b\u000b\u0010\tR\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/camera/camera2/pipe/CameraGraph$Constants3A;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/params/MeteringRectangle;", "METERING_REGIONS_EMPTY", "[Landroid/hardware/camera2/params/MeteringRectangle;", "getMETERING_REGIONS_EMPTY", "()[Landroid/hardware/camera2/params/MeteringRectangle;", "METERING_REGIONS_DEFAULT", "getMETERING_REGIONS_DEFAULT", "Landroidx/camera/camera2/pipe/FrameNumber;", "FRAME_NUMBER_INVALID", "J", "getFRAME_NUMBER_INVALID-Ugla2oM", "()J", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Constants3A {
        private static final long FRAME_NUMBER_INVALID = 0;
        public static final Constants3A INSTANCE = null;
        private static final MeteringRectangle[] METERING_REGIONS_DEFAULT = null;
        private static final MeteringRectangle[] METERING_REGIONS_EMPTY = null;

        static {
            INSTANCE = new Constants3A();
            METERING_REGIONS_EMPTY = new MeteringRectangle[0];
            METERING_REGIONS_DEFAULT = new MeteringRectangle[]{new MeteringRectangle(0, 0, 0, 0, 0)};
            FRAME_NUMBER_INVALID = FrameNumber.m268constructorimpl(-1);
        }

        private Constants3A() {
        }

        public final MeteringRectangle[] getMETERING_REGIONS_DEFAULT() {
            return METERING_REGIONS_DEFAULT;
        }
    }

    @kotlin.Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0016\b\u0087\b\u0018\u00002\u00020\u0001:\u0001'BY\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0002\u0012\b\b\u0002\u0010\f\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0016\u001a\u00020\u00022\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u001b\u0010\u001aR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001f\u001a\u0004\b \u0010!R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\"\u001a\u0004\b#\u0010\u0014R\u0017\u0010\n\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u0018\u001a\u0004\b$\u0010\u001aR\u0017\u0010\u000b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0018\u001a\u0004\b%\u0010\u001aR\u0017\u0010\f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\f\u0010\u0018\u001a\u0004\b&\u0010\u001a¨\u0006("}, d2 = {"Landroidx/camera/camera2/pipe/CameraGraph$Flags;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "configureBlankSessionOnStop", "abortCapturesOnStop", "Landroidx/camera/camera2/pipe/CameraGraph$RepeatingRequestRequirementsBeforeCapture;", "awaitRepeatingRequestBeforeCapture", "awaitRepeatingRequestOnDisconnect", "Landroidx/camera/camera2/pipe/CameraGraph$Flags$FinalizeSessionOnCloseBehavior;", "finalizeSessionOnCloseBehavior", "closeCaptureSessionOnDisconnect", "closeCameraDeviceOnClose", "enableRestartDelays", "<init>", "(ZZLandroidx/camera/camera2/pipe/CameraGraph$RepeatingRequestRequirementsBeforeCapture;Ljava/lang/Boolean;IZZZLkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Z", "getConfigureBlankSessionOnStop", "()Z", "getAbortCapturesOnStop", "Landroidx/camera/camera2/pipe/CameraGraph$RepeatingRequestRequirementsBeforeCapture;", "getAwaitRepeatingRequestBeforeCapture", "()Landroidx/camera/camera2/pipe/CameraGraph$RepeatingRequestRequirementsBeforeCapture;", "Ljava/lang/Boolean;", "getAwaitRepeatingRequestOnDisconnect", "()Ljava/lang/Boolean;", "I", "getFinalizeSessionOnCloseBehavior-Bm6Tfm4", "getCloseCaptureSessionOnDisconnect", "getCloseCameraDeviceOnClose", "getEnableRestartDelays", "FinalizeSessionOnCloseBehavior", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Flags {
        private final boolean abortCapturesOnStop;
        private final RepeatingRequestRequirementsBeforeCapture awaitRepeatingRequestBeforeCapture;
        private final Boolean awaitRepeatingRequestOnDisconnect;
        private final boolean closeCameraDeviceOnClose;
        private final boolean closeCaptureSessionOnDisconnect;
        private final boolean configureBlankSessionOnStop;
        private final boolean enableRestartDelays;
        private final int finalizeSessionOnCloseBehavior;

        @kotlin.Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0087@\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u0005\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\r"}, d2 = {"Landroidx/camera/camera2/pipe/CameraGraph$Flags$FinalizeSessionOnCloseBehavior;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(I)Ljava/lang/String;", "toString", "hashCode-impl", "hashCode", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        @JvmInline
        public static final class FinalizeSessionOnCloseBehavior {
            public static final Companion Companion = null;
            private static final int IMMEDIATE = 0;
            private static final int OFF = 0;
            private static final int TIMEOUT = 0;

            @kotlin.Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007¨\u0006\r"}, d2 = {"Landroidx/camera/camera2/pipe/CameraGraph$Flags$FinalizeSessionOnCloseBehavior$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "OFF", "Landroidx/camera/camera2/pipe/CameraGraph$Flags$FinalizeSessionOnCloseBehavior;", "getOFF-Bm6Tfm4", "()I", "I", "IMMEDIATE", "getIMMEDIATE-Bm6Tfm4", "TIMEOUT", "getTIMEOUT-Bm6Tfm4", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                    this();
                }

                /* renamed from: getIMMEDIATE-Bm6Tfm4, reason: not valid java name */
                public final int m211getIMMEDIATEBm6Tfm4() {
                    return FinalizeSessionOnCloseBehavior.access$getIMMEDIATE$cp();
                }

                /* renamed from: getOFF-Bm6Tfm4, reason: not valid java name */
                public final int m212getOFFBm6Tfm4() {
                    return FinalizeSessionOnCloseBehavior.access$getOFF$cp();
                }

                /* renamed from: getTIMEOUT-Bm6Tfm4, reason: not valid java name */
                public final int m213getTIMEOUTBm6Tfm4() {
                    return FinalizeSessionOnCloseBehavior.access$getTIMEOUT$cp();
                }
            }

            static {
                Companion = new Companion(null);
                OFF = m207constructorimpl(0);
                IMMEDIATE = m207constructorimpl(1);
                TIMEOUT = m207constructorimpl(2);
            }

            public static final /* synthetic */ int access$getIMMEDIATE$cp() {
                return IMMEDIATE;
            }

            public static final /* synthetic */ int access$getOFF$cp() {
                return OFF;
            }

            public static final /* synthetic */ int access$getTIMEOUT$cp() {
                return TIMEOUT;
            }

            /* renamed from: constructor-impl, reason: not valid java name */
            private static int m207constructorimpl(int r0) {
                return r0;
            }

            /* renamed from: equals-impl0, reason: not valid java name */
            public static final boolean m208equalsimpl0(int r0, int r1) {
                if (r0 != r1) goto L5;
                return true;
            L5:
                return false;
            }

            /* renamed from: hashCode-impl, reason: not valid java name */
            public static int m209hashCodeimpl(int r0) {
                return Integer.hashCode(r0);
            }

            /* renamed from: toString-impl, reason: not valid java name */
            public static String m210toStringimpl(int r2) {
                return "FinalizeSessionOnCloseBehavior(value=" + r2 + ')';
            }
        }

        private Flags(boolean r1, boolean r2, RepeatingRequestRequirementsBeforeCapture r3, Boolean r4, int r5, boolean r6, boolean r7, boolean r8) {
            this.configureBlankSessionOnStop = r1;
            this.abortCapturesOnStop = r2;
            this.awaitRepeatingRequestBeforeCapture = r3;
            this.awaitRepeatingRequestOnDisconnect = r4;
            this.finalizeSessionOnCloseBehavior = r5;
            this.closeCaptureSessionOnDisconnect = r6;
            this.closeCameraDeviceOnClose = r7;
            this.enableRestartDelays = r8;
        }

        public /* synthetic */ Flags(boolean r3, boolean r4, RepeatingRequestRequirementsBeforeCapture r5, Boolean r6, int r7, boolean r8, boolean r9, boolean r10, int r11, DefaultConstructorMarker r12) {
            if ((r11 & 1) == 0) goto L6;
            r3 = false;
        L6:
            if ((r11 & 2) == 0) goto L12;
            if (Build.VERSION.SDK_INT < 30) goto L10;
            r4 = true;
            goto L12
        L10:
            r4 = false;
        L12:
            if ((r11 & 4) == 0) goto L15;
            r5 = new RepeatingRequestRequirementsBeforeCapture(0, null, 3, null);
        L15:
            if ((r11 & 8) == 0) goto L18;
            r6 = null;
        L18:
            if ((r11 & 16) == 0) goto L21;
            r7 = FinalizeSessionOnCloseBehavior.Companion.m212getOFFBm6Tfm4();
        L21:
            if ((r11 & 32) == 0) goto L24;
            r8 = Camera2Quirks.Companion.shouldCloseCaptureSessionOnDisconnect$camera_camera2_pipe();
        L24:
            if ((r11 & 64) == 0) goto L27;
            r9 = false;
        L27:
            if ((r11 & 128) == 0) goto L29;
            r10 = false;
        L29:
            this(r3, r4, r5, r6, r7, r8, r9, r10, null);
        }

        public /* synthetic */ Flags(boolean r1, boolean r2, RepeatingRequestRequirementsBeforeCapture r3, Boolean r4, int r5, boolean r6, boolean r7, boolean r8, DefaultConstructorMarker r9) {
            this(r1, r2, r3, r4, r5, r6, r7, r8);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof Flags) == true) goto L8;
            return false;
        L8:
            Flags r52 = (Flags) r5;
            if (this.configureBlankSessionOnStop == r52.configureBlankSessionOnStop) goto L12;
            return false;
        L12:
            if (this.abortCapturesOnStop == r52.abortCapturesOnStop) goto L15;
            return false;
        L15:
            if (Intrinsics.areEqual(this.awaitRepeatingRequestBeforeCapture, r52.awaitRepeatingRequestBeforeCapture) == true) goto L18;
            return false;
        L18:
            if (Intrinsics.areEqual(this.awaitRepeatingRequestOnDisconnect, r52.awaitRepeatingRequestOnDisconnect) == true) goto L21;
            return false;
        L21:
            if (FinalizeSessionOnCloseBehavior.m208equalsimpl0(this.finalizeSessionOnCloseBehavior, r52.finalizeSessionOnCloseBehavior) == true) goto L24;
            return false;
        L24:
            if (this.closeCaptureSessionOnDisconnect == r52.closeCaptureSessionOnDisconnect) goto L27;
            return false;
        L27:
            if (this.closeCameraDeviceOnClose == r52.closeCameraDeviceOnClose) goto L30;
            return false;
        L30:
            if (this.enableRestartDelays == r52.enableRestartDelays) goto L32;
            return false;
        L32:
            return true;
        }

        public final boolean getAbortCapturesOnStop() {
            return this.abortCapturesOnStop;
        }

        public final RepeatingRequestRequirementsBeforeCapture getAwaitRepeatingRequestBeforeCapture() {
            return this.awaitRepeatingRequestBeforeCapture;
        }

        public final Boolean getAwaitRepeatingRequestOnDisconnect() {
            return this.awaitRepeatingRequestOnDisconnect;
        }

        public final boolean getCloseCameraDeviceOnClose() {
            return this.closeCameraDeviceOnClose;
        }

        public final boolean getCloseCaptureSessionOnDisconnect() {
            return this.closeCaptureSessionOnDisconnect;
        }

        public final boolean getEnableRestartDelays() {
            return this.enableRestartDelays;
        }

        /* renamed from: getFinalizeSessionOnCloseBehavior-Bm6Tfm4, reason: not valid java name */
        public final int m206getFinalizeSessionOnCloseBehaviorBm6Tfm4() {
            return this.finalizeSessionOnCloseBehavior;
        }

        public int hashCode() {
            int r0 = ((((Boolean.hashCode(this.configureBlankSessionOnStop) * 31) + Boolean.hashCode(this.abortCapturesOnStop)) * 31) + this.awaitRepeatingRequestBeforeCapture.hashCode()) * 31;
            Boolean r1 = this.awaitRepeatingRequestOnDisconnect;
            if (r1 != null) goto L5;
            int r12 = 0;
        L7:
            return ((((((((r0 + r12) * 31) + FinalizeSessionOnCloseBehavior.m209hashCodeimpl(this.finalizeSessionOnCloseBehavior)) * 31) + Boolean.hashCode(this.closeCaptureSessionOnDisconnect)) * 31) + Boolean.hashCode(this.closeCameraDeviceOnClose)) * 31) + Boolean.hashCode(this.enableRestartDelays);
        L5:
            r12 = r1.hashCode();
            goto L7
        }

        public String toString() {
            return "Flags(configureBlankSessionOnStop=" + this.configureBlankSessionOnStop + ", abortCapturesOnStop=" + this.abortCapturesOnStop + ", awaitRepeatingRequestBeforeCapture=" + this.awaitRepeatingRequestBeforeCapture + ", awaitRepeatingRequestOnDisconnect=" + this.awaitRepeatingRequestOnDisconnect + ", finalizeSessionOnCloseBehavior=" + FinalizeSessionOnCloseBehavior.m210toStringimpl(this.finalizeSessionOnCloseBehavior) + ", closeCaptureSessionOnDisconnect=" + this.closeCaptureSessionOnDisconnect + ", closeCameraDeviceOnClose=" + this.closeCameraDeviceOnClose + ", enableRestartDelays=" + this.enableRestartDelays + ')';
        }
    }

    @kotlin.Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0087@\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u0005\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\r"}, d2 = {"Landroidx/camera/camera2/pipe/CameraGraph$OperatingMode;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "mode", "constructor-impl", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(I)Ljava/lang/String;", "toString", "hashCode-impl", "hashCode", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @JvmInline
    public static final class OperatingMode {
        public static final Companion Companion = null;
        private static final int EXTENSION = 0;
        private static final int HIGH_SPEED = 0;
        private static final int NORMAL = 0;

        @kotlin.Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007¨\u0006\u0012"}, d2 = {"Landroidx/camera/camera2/pipe/CameraGraph$OperatingMode$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "NORMAL", "Landroidx/camera/camera2/pipe/CameraGraph$OperatingMode;", "getNORMAL-2uNL3no", "()I", "I", "HIGH_SPEED", "getHIGH_SPEED-2uNL3no", "EXTENSION", "getEXTENSION-2uNL3no", PluginsConstants.Settings.TYPE_CUSTOM, "mode", _UrlKt.FRAGMENT_ENCODE_SET, "custom-EP6OhB0", "(I)I", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nCameraGraph.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraGraph.kt\nandroidx/camera/camera2/pipe/CameraGraph$OperatingMode$Companion\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,835:1\n82#2,2:836\n*S KotlinDebug\n*F\n+ 1 CameraGraph.kt\nandroidx/camera/camera2/pipe/CameraGraph$OperatingMode$Companion\n*L\n314#1:836,2\n*E\n"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            /* renamed from: custom-EP6OhB0, reason: not valid java name */
            public final int m219customEP6OhB0(int r2) {
                if (r2 == m222getNORMAL2uNL3no()) goto L9;
                if (r2 == m221getHIGH_SPEED2uNL3no()) goto L9;
                return OperatingMode.m214access$constructorimpl(r2);
            L9:
                if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L11;
                android.util.Log.e("CXCP", "Custom operating mode " + r2 + " conflicts with standard modes");
            L11:
                RandomKt$$ExternalSyntheticBUOutline0.m(Unit.INSTANCE);
                return 0;
            }

            /* renamed from: getEXTENSION-2uNL3no, reason: not valid java name */
            public final int m220getEXTENSION2uNL3no() {
                return OperatingMode.access$getEXTENSION$cp();
            }

            /* renamed from: getHIGH_SPEED-2uNL3no, reason: not valid java name */
            public final int m221getHIGH_SPEED2uNL3no() {
                return OperatingMode.access$getHIGH_SPEED$cp();
            }

            /* renamed from: getNORMAL-2uNL3no, reason: not valid java name */
            public final int m222getNORMAL2uNL3no() {
                return OperatingMode.access$getNORMAL$cp();
            }
        }

        static {
            Companion = new Companion(null);
            NORMAL = m215constructorimpl(0);
            HIGH_SPEED = m215constructorimpl(1);
            EXTENSION = m215constructorimpl(2);
        }

        /* renamed from: access$constructor-impl, reason: not valid java name */
        public static final /* synthetic */ int m214access$constructorimpl(int r0) {
            return m215constructorimpl(r0);
        }

        public static final /* synthetic */ int access$getEXTENSION$cp() {
            return EXTENSION;
        }

        public static final /* synthetic */ int access$getHIGH_SPEED$cp() {
            return HIGH_SPEED;
        }

        public static final /* synthetic */ int access$getNORMAL$cp() {
            return NORMAL;
        }

        /* renamed from: constructor-impl, reason: not valid java name */
        private static int m215constructorimpl(int r0) {
            return r0;
        }

        /* renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m216equalsimpl0(int r0, int r1) {
            if (r0 != r1) goto L5;
            return true;
        L5:
            return false;
        }

        /* renamed from: hashCode-impl, reason: not valid java name */
        public static int m217hashCodeimpl(int r0) {
            return Integer.hashCode(r0);
        }

        /* renamed from: toString-impl, reason: not valid java name */
        public static String m218toStringimpl(int r2) {
            return "OperatingMode(mode=" + r2 + ')';
        }
    }

    @kotlin.Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0001\rB\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Landroidx/camera/camera2/pipe/CameraGraph$RepeatingRequestRequirementsBeforeCapture;", _UrlKt.FRAGMENT_ENCODE_SET, "repeatingFramesToComplete", "Lkotlin/UInt;", "completionBehavior", "Landroidx/camera/camera2/pipe/CameraGraph$RepeatingRequestRequirementsBeforeCapture$CompletionBehavior;", "<init>", "(ILandroidx/camera/camera2/pipe/CameraGraph$RepeatingRequestRequirementsBeforeCapture$CompletionBehavior;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getRepeatingFramesToComplete-pVg5ArA", "()I", "I", "getCompletionBehavior", "()Landroidx/camera/camera2/pipe/CameraGraph$RepeatingRequestRequirementsBeforeCapture$CompletionBehavior;", "CompletionBehavior", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class RepeatingRequestRequirementsBeforeCapture {
        private final CompletionBehavior completionBehavior;
        private final int repeatingFramesToComplete;

        @kotlin.Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/camera/camera2/pipe/CameraGraph$RepeatingRequestRequirementsBeforeCapture$CompletionBehavior;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "AT_LEAST", "EXACT", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public enum CompletionBehavior extends Enum<CompletionBehavior> {
            private static final /* synthetic */ EnumEntries $ENTRIES = null;
            private static final /* synthetic */ CompletionBehavior[] $VALUES = null;
            public static final CompletionBehavior AT_LEAST = null;
            public static final CompletionBehavior EXACT = null;

            private static final /* synthetic */ CompletionBehavior[] $values() {
                return new CompletionBehavior[]{AT_LEAST, EXACT};
            }

            static {
                AT_LEAST = new CompletionBehavior("AT_LEAST", 0);
                EXACT = new CompletionBehavior("EXACT", 1);
                CompletionBehavior[] r0 = $values();
                $VALUES = r0;
                $ENTRIES = EnumEntriesKt.enumEntries(r0);
            }

            CompletionBehavior(String r1, int r2) {
            }

            public static CompletionBehavior valueOf(String r1) {
                return (CompletionBehavior) Enum.valueOf(CompletionBehavior.class, r1);
            }

            public static CompletionBehavior[] values() {
                return (CompletionBehavior[]) $VALUES.clone();
            }
        }

        private RepeatingRequestRequirementsBeforeCapture(int r1, CompletionBehavior r2) {
            this.repeatingFramesToComplete = r1;
            this.completionBehavior = r2;
        }

        public /* synthetic */ RepeatingRequestRequirementsBeforeCapture(int r1, CompletionBehavior r2, int r3, DefaultConstructorMarker r4) {
            if ((r3 & 1) == 0) goto L6;
            r1 = 0;
        L6:
            if ((r3 & 2) == 0) goto L8;
            r2 = CompletionBehavior.AT_LEAST;
        L8:
            this(r1, r2, null);
        }

        public /* synthetic */ RepeatingRequestRequirementsBeforeCapture(int r1, CompletionBehavior r2, DefaultConstructorMarker r3) {
            this(r1, r2);
        }

        public final CompletionBehavior getCompletionBehavior() {
            return this.completionBehavior;
        }

        /* renamed from: getRepeatingFramesToComplete-pVg5ArA, reason: not valid java name */
        public final int m223getRepeatingFramesToCompletepVg5ArA() {
            return this.repeatingFramesToComplete;
        }
    }

    @kotlin.Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\bg\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H&¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\r\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bH&¢\u0006\u0004\b\r\u0010\u000eJî\u0001\u0010,\u001a\b\u0012\u0004\u0012\u00020)0(2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u000b2\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u000b2\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u000b2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u000f2\u0016\b\u0002\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 \u0018\u00010\u001e2\u0016\b\u0002\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 \u0018\u00010\u001e2\b\b\u0002\u0010$\u001a\u00020#2\b\b\u0002\u0010&\u001a\u00020%2\b\b\u0002\u0010'\u001a\u00020%H¦@¢\u0006\u0004\b*\u0010+Jf\u00102\u001a\b\u0012\u0004\u0012\u00020)0(2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010 2\u0016\b\u0002\u00100\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 \u0018\u00010\u001e2\b\b\u0002\u0010$\u001a\u00020#2\b\b\u0002\u00101\u001a\u00020%H¦@¢\u0006\u0004\b2\u00103J>\u00106\u001a\b\u0012\u0004\u0012\u00020)0(2\b\b\u0002\u00104\u001a\u00020 2\b\b\u0002\u00105\u001a\u00020 2\b\b\u0002\u0010$\u001a\u00020#2\b\b\u0002\u00101\u001a\u00020%H¦@¢\u0006\u0004\b6\u00107J \u00109\u001a\b\u0012\u0004\u0012\u00020)0(2\b\b\u0002\u00108\u001a\u00020 H¦@¢\u0006\u0004\b9\u0010:ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006;À\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/pipe/CameraGraph$Session;", "Landroidx/camera/camera2/pipe/CameraControls3A;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "Landroidx/camera/camera2/pipe/Request;", PluginsConstants.REQUEST, _UrlKt.FRAGMENT_ENCODE_SET, "startRepeating", "(Landroidx/camera/camera2/pipe/Request;)V", "stopRepeating", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "requests", "submit", "(Ljava/util/List;)V", "Landroidx/camera/camera2/pipe/AeMode;", "aeMode", "Landroidx/camera/camera2/pipe/AfMode;", "afMode", "Landroidx/camera/camera2/pipe/AwbMode;", "awbMode", "Landroid/hardware/camera2/params/MeteringRectangle;", "aeRegions", "afRegions", "awbRegions", "Landroidx/camera/camera2/pipe/Lock3ABehavior;", "aeLockBehavior", "afLockBehavior", "awbLockBehavior", "afTriggerStartAeMode", "Lkotlin/Function1;", "Landroidx/camera/camera2/pipe/FrameMetadata;", _UrlKt.FRAGMENT_ENCODE_SET, "convergedCondition", "lockedCondition", _UrlKt.FRAGMENT_ENCODE_SET, "frameLimit", _UrlKt.FRAGMENT_ENCODE_SET, "convergedTimeLimitNs", "lockedTimeLimitNs", "Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/pipe/Result3A;", "lock3A--tS25XM", "(Landroidx/camera/camera2/pipe/AeMode;Landroidx/camera/camera2/pipe/AfMode;Landroidx/camera/camera2/pipe/AwbMode;Ljava/util/List;Ljava/util/List;Ljava/util/List;Landroidx/camera/camera2/pipe/Lock3ABehavior;Landroidx/camera/camera2/pipe/Lock3ABehavior;Landroidx/camera/camera2/pipe/Lock3ABehavior;Landroidx/camera/camera2/pipe/AeMode;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;IJJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lock3A", "ae", "af", "awb", "unlockedCondition", "timeLimitNs", "unlock3A", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Lkotlin/jvm/functions/Function1;IJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "triggerAf", "waitForAwb", "lock3AForCapture", "(ZZIJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelAf", "unlock3APostCapture", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface Session extends CameraControls3A, AutoCloseable {
        /* renamed from: lock3A--tS25XM$default, reason: not valid java name */
        static /* synthetic */ Object m224lock3AtS25XM$default(Session r22, AeMode r23, AfMode r24, AwbMode r25, List r26, List r27, List r28, Lock3ABehavior r29, Lock3ABehavior r30, Lock3ABehavior r31, AeMode r32, Function1 r33, Function1 r34, int r35, long r36, long r38, Continuation r40, int r41, Object r42) {
            if (r42 == null) goto L5;
            ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: lock3A--tS25XM");
            return null;
        L5:
            if ((r41 & 1) == 0) goto L7;
            AeMode r4 = null;
        L9:
            if ((r41 & 2) == 0) goto L11;
            AfMode r5 = null;
        L13:
            if ((r41 & 4) == 0) goto L15;
            AwbMode r6 = null;
        L17:
            if ((r41 & 8) == 0) goto L19;
            List r7 = null;
        L21:
            if ((r41 & 16) == 0) goto L23;
            List r8 = null;
        L25:
            if ((r41 & 32) == 0) goto L27;
            List r9 = null;
        L29:
            if ((r41 & 64) == 0) goto L31;
            Lock3ABehavior r10 = null;
        L33:
            if ((r41 & 128) == 0) goto L35;
            Lock3ABehavior r11 = null;
        L37:
            if ((r41 & 256) == 0) goto L39;
            Lock3ABehavior r12 = null;
        L41:
            if ((r41 & 512) == 0) goto L43;
            AeMode r13 = null;
        L45:
            if ((r41 & 1024) == 0) goto L47;
            Function1 r14 = null;
        L49:
            if ((r41 & 2048) == 0) goto L51;
            Function1 r15 = null;
        L53:
            if ((r41 & 4096) == 0) goto L55;
            int r16 = 60;
        L57:
            if ((r41 & 8192) == 0) goto L59;
            long r17 = 3000000000L;
        L61:
            if ((r41 & 16384) == 0) goto L63;
            long r19 = 3000000000L;
            Continuation r21 = r40;
            Session r3 = r22;
        L65:
            return r3.mo225lock3AtS25XM(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r19, r21);
        L63:
            r19 = r38;
            r3 = r22;
            r21 = r40;
            goto L65
        L59:
            r17 = r36;
            goto L61
        L55:
            r16 = r35;
            goto L57
        L51:
            r15 = r34;
            goto L53
        L47:
            r14 = r33;
            goto L49
        L43:
            r13 = r32;
            goto L45
        L39:
            r12 = r31;
            goto L41
        L35:
            r11 = r30;
            goto L37
        L31:
            r10 = r29;
            goto L33
        L27:
            r9 = r28;
            goto L29
        L23:
            r8 = r27;
            goto L25
        L19:
            r7 = r26;
            goto L21
        L15:
            r6 = r25;
            goto L17
        L11:
            r5 = r24;
            goto L13
        L7:
            r4 = r23;
            goto L9
        }

        static /* synthetic */ Object lock3AForCapture$default(Session r0, boolean r1, boolean r2, int r3, long r4, Continuation r6, int r7, Object r8) {
            if (r8 == null) goto L4;
            ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: lock3AForCapture");
            return null;
        L4:
            if ((r7 & 1) == 0) goto L7;
            r1 = true;
        L7:
            if ((r7 & 2) == 0) goto L10;
            r2 = false;
        L10:
            if ((r7 & 4) == 0) goto L13;
            r3 = 60;
        L13:
            if ((r7 & 8) == 0) goto L15;
            r4 = 3000000000L;
        L15:
            long r62 = r4;
            int r5 = r3;
            boolean r32 = r1;
            return r0.lock3AForCapture(r32, r2, r5, r62, r6);
        }

        /* JADX WARN: Multi-variable type inference failed */
        static /* synthetic */ Object unlock3A$default(Session r1, Boolean r2, Boolean r3, Boolean r4, Function1 r5, int r6, long r7, Continuation r9, int r10, Object r11) {
            if (r11 == null) goto L4;
            ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: unlock3A");
            return null;
        L4:
            if ((r10 & 1) == 0) goto L7;
            r2 = null;
        L7:
            if ((r10 & 2) == 0) goto L10;
            r3 = null;
        L10:
            if ((r10 & 4) == 0) goto L13;
            r4 = null;
        L13:
            if ((r10 & 8) == 0) goto L16;
            r5 = null;
        L16:
            if ((r10 & 16) == 0) goto L19;
            r6 = 60;
        L19:
            if ((r10 & 32) == 0) goto L22;
            r7 = 3000000000L;
        L22:
            return r1.unlock3A(r2, r3, r4, r5, r6, r7, r9);
        }

        /* renamed from: lock3A--tS25XM, reason: not valid java name */
        Object mo225lock3AtS25XM(AeMode r1, AfMode r2, AwbMode r3, List<MeteringRectangle> r4, List<MeteringRectangle> r5, List<MeteringRectangle> r6, Lock3ABehavior r7, Lock3ABehavior r8, Lock3ABehavior r9, AeMode r10, Function1<? super FrameMetadata, Boolean> r11, Function1<? super FrameMetadata, Boolean> r12, int r13, long r14, long r16, Continuation<? super Deferred<Result3A>> r18);

        Object lock3AForCapture(boolean r1, boolean r2, int r3, long r4, Continuation<? super Deferred<Result3A>> r6);

        void startRepeating(Request r1);

        void stopRepeating();

        void submit(List<Request> r1);

        Object unlock3A(Boolean r1, Boolean r2, Boolean r3, Function1<? super FrameMetadata, Boolean> r4, int r5, long r6, Continuation<? super Deferred<Result3A>> r8);

        Object unlock3APostCapture(boolean r1, Continuation<? super Deferred<Result3A>> r2);
    }
}
