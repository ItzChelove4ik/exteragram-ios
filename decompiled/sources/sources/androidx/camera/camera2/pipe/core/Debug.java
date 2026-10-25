package androidx.camera.camera2.pipe.core;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.CameraStream;
import androidx.camera.camera2.pipe.ConcurrentCameraGraphs;
import androidx.camera.camera2.pipe.InputStream;
import androidx.camera.camera2.pipe.InputStreamId;
import androidx.camera.camera2.pipe.OutputId;
import androidx.camera.camera2.pipe.OutputStream;
import androidx.camera.camera2.pipe.RequestTemplate;
import androidx.camera.camera2.pipe.StreamFormat;
import androidx.camera.camera2.pipe.StreamId;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J7\u0010\f\u001a\u00020\u000b2\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\b\u001a\u00020\u00072\u0012\u0010\n\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tH\u0002¢\u0006\u0004\b\f\u0010\rJ5\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u000f0\u000e2\u0012\u0010\n\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\u0013\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0016\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\u0016\u0010\u0014J+\u0010\u0019\u001a\u00020\u00072\u0012\u0010\n\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t2\b\b\u0002\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ%\u0010!\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001f¢\u0006\u0004\b!\u0010\"R\u001a\u0010$\u001a\u00020#8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'¨\u0006("}, d2 = {"Landroidx/camera/camera2/pipe/core/Debug;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "builder", _UrlKt.FRAGMENT_ENCODE_SET, "name", _UrlKt.FRAGMENT_ENCODE_SET, "parameters", _UrlKt.FRAGMENT_ENCODE_SET, "appendParameters", "(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/util/Map;)V", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Pair;", "parametersToSortedStringPairs", "(Ljava/util/Map;)Ljava/util/List;", PluginsConstants.Settings.KEY, "keyNameToString", "(Ljava/lang/Object;)Ljava/lang/String;", "value", "valueToString", _UrlKt.FRAGMENT_ENCODE_SET, "limit", "formatParameterMap", "(Ljava/util/Map;I)Ljava/lang/String;", "Landroidx/camera/camera2/pipe/CameraMetadata;", "metadata", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "graphConfig", "Landroidx/camera/camera2/pipe/CameraGraph;", "cameraGraph", "formatCameraGraphProperties", "(Landroidx/camera/camera2/pipe/CameraMetadata;Landroidx/camera/camera2/pipe/CameraGraph$Config;Landroidx/camera/camera2/pipe/CameraGraph;)Ljava/lang/String;", "Landroidx/camera/camera2/pipe/core/SystemTimeSource;", "systemTimeSource", "Landroidx/camera/camera2/pipe/core/SystemTimeSource;", "getSystemTimeSource$camera_camera2_pipe", "()Landroidx/camera/camera2/pipe/core/SystemTimeSource;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDebug.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n+ 2 Timestamps.kt\nandroidx/camera/camera2/pipe/core/TimestampNs\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 4 Timestamps.kt\nandroidx/camera/camera2/pipe/core/Timestamps\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 6 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 7 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,281:1\n71#1,4:282\n78#1,4:286\n71#1,4:290\n78#1,4:294\n29#2:298\n50#3:299\n51#3:302\n74#4,2:300\n1869#5,2:303\n1056#5:309\n1878#5,2:310\n1880#5:313\n126#6:305\n153#6,3:306\n1#7:312\n*S KotlinDebug\n*F\n+ 1 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n*L\n49#1:282,4\n52#1:286,4\n60#1:290,4\n63#1:294,4\n64#1:298\n65#1:299\n65#1:302\n65#1:300,2\n89#1:303,2\n133#1:309\n204#1:310,2\n204#1:313\n133#1:305\n133#1:306,3\n*E\n"})
/* loaded from: classes3.dex */
public final class Debug {
    public static final Debug INSTANCE = null;
    private static final SystemTimeSource systemTimeSource = null;

    /* renamed from: $r8$lambda$Sn9a-H_uwDlIe6WXrB7ByV3vFWw, reason: not valid java name */
    public static CharSequence m495$r8$lambda$Sn9aH_uwDlIe6WXrB7ByV3vFWw(Object r1) {
        return INSTANCE.valueToString(r1);
    }

    public static CharSequence $r8$lambda$qyoCxQw_NlDyB2t4IONaMXi63jI(Pair r2) {
        return ((String) r2.getFirst()) + SignatureVisitor.INSTANCEOF + ((String) r2.getSecond());
    }

    static {
        INSTANCE = new Debug();
        systemTimeSource = new SystemTimeSource();
    }

    private Debug() {
    }

    private final void appendParameters(StringBuilder r5, String r6, Map<?, ? extends Object> r7) {
        if (r7.isEmpty() == false) goto L6;
        r5.append(r6 + ": (None)\n");
        return;
    L6:
        r5.append(r6 + '\n');
        Iterator<T> r4 = INSTANCE.parametersToSortedStringPairs(r7).iterator();
    L8:
        if (r4.hasNext() == false) goto L10;
        Pair r72 = (Pair) r4.next();
        r5.append("  " + StringsKt.padEnd((String) r72.getFirst(), 50, ' ') + ' ' + ((String) r72.getSecond()) + '\n');
        goto L8
    }

    private final String keyNameToString(Object r1) {
        if ((r1 instanceof CameraCharacteristics.Key) == false) goto L7;
        return ((CameraCharacteristics.Key) r1).getName();
    L7:
        if ((r1 instanceof CaptureRequest.Key) == false) goto L11;
        return ((CaptureRequest.Key) r1).getName();
    L11:
        if ((r1 instanceof CaptureResult.Key) == false) goto L15;
        return ((CaptureResult.Key) r1).getName();
    L15:
        return String.valueOf(r1);
    }

    private final List<Pair<String, String>> parametersToSortedStringPairs(Map<?, ? extends Object> r4) {
        ArrayList r3 = new ArrayList(r4.size());
        Iterator<Map.Entry<?, ? extends Object>> r42 = r4.entrySet().iterator();
    L4:
        if (r42.hasNext() == false) goto L7;
        Map.Entry<?, ? extends Object> r0 = r42.next();
        Debug r1 = INSTANCE;
        r3.add(TuplesKt.to(r1.keyNameToString(r0.getKey()), r1.valueToString(r0.getValue())));
        goto L4
    L7:
        return CollectionsKt.sortedWith(r3, new Debug$parametersToSortedStringPairs$$inlined$sortedBy$1());
    }

    private final String valueToString(Object r10) {
        if ((r10 instanceof Object[]) == false) goto L7;
        return ArraysKt.joinToString$default((Object[]) r10, null, "[", "]", 0, null, new Debug$$ExternalSyntheticLambda1(), 25, null);
    L7:
        return String.valueOf(r10);
    }

    public final String formatCameraGraphProperties(CameraMetadata r13, CameraGraph.Config r14, CameraGraph r15) {
        ConcurrentCameraGraphs r12 = r14.getConcurrentCameraGraphs$camera_camera2_pipe();
        if (r12 == null) goto L5;
        Set<CameraId> r122 = r12.getCameraIds();
    L6:
        Integer r0 = (Integer) r13.get(CameraCharacteristics.LENS_FACING);
        String r1 = "External";
        String r3 = "Unknown";
        if (r0 != null) goto L10;
    L12:
        if (r0 != null) goto L15;
    L17:
        if (r0 != null) goto L20;
    L22:
        String r02 = "Unknown";
    L23:
        Integer r5 = (Integer) r13.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        if (r5 != null) goto L27;
    L29:
        if (r5 != null) goto L32;
    L34:
        if (r5 != null) goto L37;
    L39:
        if (r5 != null) goto L42;
    L44:
        if (r5 != null) goto L47;
    L49:
        r1 = "Unknown";
    L50:
        int r2 = r14.m204getSessionMode2uNL3no();
        CameraGraph.OperatingMode.Companion r4 = CameraGraph.OperatingMode.Companion;
        if (CameraGraph.OperatingMode.m216equalsimpl0(r2, r4.m221getHIGH_SPEED2uNL3no()) == false) goto L54;
        r3 = "High Speed";
    L59:
        int[] r132 = (int[]) r13.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
        if (r132 != null) goto L62;
    L64:
        String r133 = "Physical";
    L65:
        StringBuilder r22 = new StringBuilder();
        r22.append(r15 + " (Camera " + r14.m200getCameraDz_R5H8() + ")\n");
        if (r122 == null) goto L68;
        r22.append("  Concurrent: " + r122 + '\n');
    L68:
        r22.append("  Facing:    " + r02 + " (" + r133 + ", " + r1 + ")\n");
        StringBuilder r123 = new StringBuilder("  Mode:      ");
        r123.append(r3);
        r123.append('\n');
        r22.append(r123.toString());
        r22.append("Outputs:\n");
        Iterator<CameraStream> r124 = r15.getStreams().getStreams().iterator();
    L70:
        if (r124.hasNext() == false) goto L98;
        Iterator<T> r134 = r124.next().getOutputs().iterator();
        int r52 = 0;
    L73:
        if (r134.hasNext() == false) goto L70;
        Object r6 = r134.next();
        int r7 = r52 + 1;
        if (r52 >= 0) goto L77;
        CollectionsKt.throwIndexOverflow();
    L77:
        OutputStream r62 = (OutputStream) r6;
        r22.append("  ");
        if (r52 != 0) goto L80;
        String r53 = StreamId.m406toStringimpl(r62.getStream().m241getIdptHMqGs());
    L81:
        r22.append(StringsKt.padEnd(r53, 12, ' '));
        r22.append(StringsKt.padEnd(OutputId.m295toStringimpl(r62.mo311getId4LaLFng()), 12, ' '));
        r22.append(StringsKt.padEnd(r62.getSize().toString(), 12, ' '));
        r22.append(StringsKt.padEnd(StreamFormat.m393getNameimpl(r62.mo310getFormat8FPWQzE()), 16, ' '));
        OutputStream.MirrorMode r54 = r62.mo312getMirrorModedO1_9xk();
        if (r54 == null) goto L84;
        r22.append(" [" + OutputStream.MirrorMode.m338toStringimpl(r54.m339unboximpl()) + ']');
    L84:
        r62.mo315getTimestampBasepcPfPbY();
        OutputStream.DynamicRangeProfile r55 = r62.mo309getDynamicRangeProfileOoVcG5w();
        if (r55 == null) goto L87;
        r22.append(" [" + OutputStream.DynamicRangeProfile.m330toStringimpl(r55.m331unboximpl()) + ']');
    L87:
        OutputStream.StreamUseCase r56 = r62.mo313getStreamUseCase8x2ez34();
        if (r56 == null) goto L90;
        r22.append(" [" + OutputStream.StreamUseCase.m346toStringimpl(r56.m347unboximpl()) + ']');
    L90:
        OutputStream.StreamUseHint r57 = r62.mo314getStreamUseHintHIPxoCc();
        if (r57 == null) goto L94;
        r22.append(" [" + OutputStream.StreamUseHint.m356toStringimpl(r57.m357unboximpl()) + ']');
    L94:
        if (CameraId.m230equalsimpl0(r62.mo308getCameraDz_R5H8(), r14.m200getCameraDz_R5H8()) == true) goto L96;
        r22.append(" [");
        r22.append(CameraId.m227boximpl(r62.mo308getCameraDz_R5H8()));
        r22.append("]");
    L96:
        r22.append("\n");
        r52 = r7;
        goto L73
    L80:
        r53 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L81
    L98:
        if (r15.getStreams().getInputs().isEmpty() == true) goto L103;
        r22.append("Inputs:\n");
        Iterator<InputStream> r125 = r15.getStreams().getInputs().iterator();
    L101:
        if (r125.hasNext() == false) goto L103;
        InputStream r135 = r125.next();
        r22.append(" ");
        r22.append(StringsKt.padEnd(InputStreamId.m279toStringimpl(r135.mo276getIdm1bwn9M()), 12, ' '));
        r22.append(StringsKt.padEnd(StreamFormat.m395toStringimpl(r135.mo275getFormat8FPWQzE()), 12, ' '));
        r22.append(StringsKt.padEnd(String.valueOf(r135.getMaxImages()), 12, ' '));
        r22.append("\n");
    L103:
        r22.append("Session Template: " + RequestTemplate.m374getNameimpl(r14.m205getSessionTemplatefGx8uWA()) + '\n');
        Debug r126 = INSTANCE;
        r126.appendParameters(r22, "Session Parameters", r14.getSessionParameters());
        r22.append("Default Template: " + RequestTemplate.m374getNameimpl(r14.m202getDefaultTemplatefGx8uWA()) + '\n');
        r126.appendParameters(r22, "Default Parameters", r14.getDefaultParameters());
        r126.appendParameters(r22, "Required Parameters", r14.getRequiredParameters());
        return r22.toString();
    L62:
        if (ArraysKt.contains(r132, 11) == false) goto L64;
        r133 = "Logical";
        goto L65
    L54:
        if (CameraGraph.OperatingMode.m216equalsimpl0(r2, r4.m222getNORMAL2uNL3no()) == false) goto L57;
        r3 = "Normal";
        goto L59
    L57:
        if (CameraGraph.OperatingMode.m216equalsimpl0(r2, r4.m220getEXTENSION2uNL3no()) == false) goto L59;
        r3 = "Extension";
        goto L59
    L47:
        if (r5.intValue() != 4) goto L49;
    L42:
        if (r5.intValue() != 3) goto L44;
        r1 = "Level 3";
        goto L50
    L37:
        if (r5.intValue() != 2) goto L39;
        r1 = "Legacy";
        goto L50
    L32:
        if (r5.intValue() != 1) goto L34;
        r1 = "Full";
        goto L50
    L27:
        if (r5.intValue() != 0) goto L29;
        r1 = "Limited";
        goto L50
    L20:
        if (r0.intValue() != 2) goto L22;
        r02 = "External";
        goto L23
    L15:
        if (r0.intValue() != 1) goto L17;
        r02 = "Back";
        goto L23
    L10:
        if (r0.intValue() != 0) goto L12;
        r02 = "Front";
        goto L23
    L5:
        r122 = null;
        goto L6
    }

    public final String formatParameterMap(Map<?, ? extends Object> r10, int r11) {
        return CollectionsKt.joinToString$default(parametersToSortedStringPairs(r10), null, "{", "}", r11, null, new Debug$$ExternalSyntheticLambda0(), 17, null);
    }

    public final SystemTimeSource getSystemTimeSource$camera_camera2_pipe() {
        return systemTimeSource;
    }
}
