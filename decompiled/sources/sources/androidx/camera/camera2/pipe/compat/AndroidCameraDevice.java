package androidx.camera.camera2.pipe.compat;

import android.graphics.ColorSpace;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.ExtensionSessionConfiguration;
import android.hardware.camera2.params.InputConfiguration;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.os.Build;
import android.os.Trace;
import android.view.Surface;
import androidx.camera.camera2.pipe.CameraColorSpace;
import androidx.camera.camera2.pipe.CameraError;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraInterop;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.RequestsKt;
import androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper;
import androidx.camera.camera2.pipe.compat.CameraExtensionSessionWrapper;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.DurationNs;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.core.Timestamps;
import androidx.camera.camera2.pipe.internal.CameraErrorListener;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ%\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0013\u0010\u0017\u001a\u00020\u0016*\u00020\u0010H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0016*\u00020\u0010H\u0002¢\u0006\u0004\b\u0019\u0010\u0018J%\u0010\u001e\u001a\u00020\u00132\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u0011\u001a\u00020\u001dH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010\"\u001a\u00020\u00132\u0006\u0010!\u001a\u00020 H\u0017¢\u0006\u0004\b\"\u0010#J-\u0010&\u001a\u00020\u00132\u0006\u0010%\u001a\u00020$2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u0011\u001a\u00020\u001dH\u0016¢\u0006\u0004\b&\u0010'J%\u0010(\u001a\u00020\u00132\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u0011\u001a\u00020\u001dH\u0016¢\u0006\u0004\b(\u0010\u001fJ%\u0010+\u001a\u00020\u00132\f\u0010*\u001a\b\u0012\u0004\u0012\u00020)0\u001a2\u0006\u0010\u0011\u001a\u00020\u001dH\u0017¢\u0006\u0004\b+\u0010\u001fJ-\u0010.\u001a\u00020\u00132\u0006\u0010-\u001a\u00020,2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020)0\u001a2\u0006\u0010\u0011\u001a\u00020\u001dH\u0017¢\u0006\u0004\b.\u0010/J\u0017\u0010\u001e\u001a\u00020\u00132\u0006\u0010!\u001a\u000200H\u0017¢\u0006\u0004\b\u001e\u00101J\u0019\u00107\u001a\u0004\u0018\u0001042\u0006\u00103\u001a\u000202H\u0016¢\u0006\u0004\b5\u00106J\u0019\u0010:\u001a\u0004\u0018\u0001042\u0006\u00109\u001a\u000208H\u0016¢\u0006\u0004\b:\u0010;J\u0017\u0010@\u001a\u00020\u00162\u0006\u0010=\u001a\u00020<H\u0017¢\u0006\u0004\b>\u0010?J\u000f\u0010A\u001a\u00020\u0016H\u0016¢\u0006\u0004\bA\u0010BJ\u000f\u0010C\u001a\u00020\u0016H\u0016¢\u0006\u0004\bC\u0010BJ)\u0010H\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010E*\u00020D2\f\u0010G\u001a\b\u0012\u0004\u0012\u00028\u00000FH\u0016¢\u0006\u0004\bH\u0010IJ\u000f\u0010K\u001a\u00020JH\u0016¢\u0006\u0004\bK\u0010LR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010MR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010NR\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010O\u001a\u0004\bP\u0010LR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010QR\u0016\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010RR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010SR\u0014\u0010U\u001a\u00020T8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bU\u0010VR\u001c\u0010X\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100W8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bX\u0010Y¨\u0006Z"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AndroidCameraDevice;", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "Landroidx/camera/camera2/pipe/CameraMetadata;", "cameraMetadata", "Landroid/hardware/camera2/CameraDevice;", "cameraDevice", "Landroidx/camera/camera2/pipe/CameraId;", "cameraId", "Landroidx/camera/camera2/pipe/internal/CameraErrorListener;", "cameraErrorListener", "Landroidx/camera/camera2/pipe/CameraInterop$CaptureSessionListener;", "interopCaptureSessionListener", "Landroidx/camera/camera2/pipe/core/Threads;", "threads", "<init>", "(Landroidx/camera/camera2/pipe/CameraMetadata;Landroid/hardware/camera2/CameraDevice;Ljava/lang/String;Landroidx/camera/camera2/pipe/internal/CameraErrorListener;Landroidx/camera/camera2/pipe/CameraInterop$CaptureSessionListener;Landroidx/camera/camera2/pipe/core/Threads;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "Landroidx/camera/camera2/pipe/compat/SessionStateCallback;", "stateCallback", "Lkotlin/Pair;", _UrlKt.FRAGMENT_ENCODE_SET, "checkAndSetStateCallback", "(Landroidx/camera/camera2/pipe/compat/SessionStateCallback;)Lkotlin/Pair;", _UrlKt.FRAGMENT_ENCODE_SET, "onSessionDisconnectedWithTrace", "(Landroidx/camera/camera2/pipe/compat/SessionStateCallback;)V", "onSessionFinalizedWithTrace", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/view/Surface;", "outputs", "Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;", "createCaptureSession", "(Ljava/util/List;Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;)Z", "Landroidx/camera/camera2/pipe/compat/ExtensionSessionConfigData;", "config", "createExtensionSession", "(Landroidx/camera/camera2/pipe/compat/ExtensionSessionConfigData;)Z", "Landroid/hardware/camera2/params/InputConfiguration;", PluginsConstants.Settings.TYPE_INPUT, "createReprocessableCaptureSession", "(Landroid/hardware/camera2/params/InputConfiguration;Ljava/util/List;Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;)Z", "createConstrainedHighSpeedCaptureSession", "Landroidx/camera/camera2/pipe/compat/OutputConfigurationWrapper;", "outputConfigurations", "createCaptureSessionByOutputConfigurations", "Landroidx/camera/camera2/pipe/compat/InputConfigData;", "inputConfig", "createReprocessableCaptureSessionByConfigurations", "(Landroidx/camera/camera2/pipe/compat/InputConfigData;Ljava/util/List;Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;)Z", "Landroidx/camera/camera2/pipe/compat/SessionConfigData;", "(Landroidx/camera/camera2/pipe/compat/SessionConfigData;)Z", "Landroidx/camera/camera2/pipe/RequestTemplate;", "template", "Landroid/hardware/camera2/CaptureRequest$Builder;", "createCaptureRequest-2PPcXtw", "(I)Landroid/hardware/camera2/CaptureRequest$Builder;", "createCaptureRequest", "Landroid/hardware/camera2/TotalCaptureResult;", "inputResult", "createReprocessCaptureRequest", "(Landroid/hardware/camera2/TotalCaptureResult;)Landroid/hardware/camera2/CaptureRequest$Builder;", "Landroidx/camera/camera2/pipe/AudioRestrictionMode;", "mode", "onCameraAudioRestrictionUpdated-LwUUkyU", "(I)V", "onCameraAudioRestrictionUpdated", "onDeviceClosing", "()V", "onDeviceClosed", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Lkotlin/reflect/KClass;", "type", "unwrapAs", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroidx/camera/camera2/pipe/CameraMetadata;", "Landroid/hardware/camera2/CameraDevice;", "Ljava/lang/String;", "getCameraId-Dz_R5H8", "Landroidx/camera/camera2/pipe/internal/CameraErrorListener;", "Landroidx/camera/camera2/pipe/CameraInterop$CaptureSessionListener;", "Landroidx/camera/camera2/pipe/core/Threads;", "Lkotlinx/atomicfu/AtomicBoolean;", "closed", "Lkotlinx/atomicfu/AtomicBoolean;", "Lkotlinx/atomicfu/AtomicRef;", "_lastStateCallback", "Lkotlinx/atomicfu/AtomicRef;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraDeviceWrapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraDeviceWrapper.kt\nandroidx/camera/camera2/pipe/compat/AndroidCameraDevice\n+ 2 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n+ 3 Exceptions.kt\nandroidx/camera/camera2/pipe/compat/ExceptionsKt\n+ 4 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 5 Timestamps.kt\nandroidx/camera/camera2/pipe/core/TimestampNs\n+ 6 Timestamps.kt\nandroidx/camera/camera2/pipe/core/Timestamps\n+ 7 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 8 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,714:1\n528#1:715\n529#1:724\n530#1:772\n528#1:775\n529#1:784\n530#1:837\n528#1:840\n529#1:849\n530#1:897\n528#1:900\n529#1:909\n530#1:957\n528#1:960\n529#1:969\n530#1:1021\n528#1:1024\n529#1:1033\n530#1:1085\n528#1:1088\n529#1:1097\n530#1:1183\n528#1:1186\n529#1:1195\n530#1:1243\n528#1:1244\n529#1:1253\n530#1:1301\n58#2,3:716\n71#2,4:719\n61#2:723\n63#2:762\n78#2,4:763\n64#2:767\n65#2:769\n58#2,3:776\n71#2,4:779\n61#2:783\n63#2:827\n78#2,4:828\n64#2:832\n65#2:834\n58#2,3:841\n71#2,4:844\n61#2:848\n63#2:887\n78#2,4:888\n64#2:892\n65#2:894\n58#2,3:901\n71#2,4:904\n61#2:908\n63#2:947\n78#2,4:948\n64#2:952\n65#2:954\n58#2,3:961\n71#2,4:964\n61#2:968\n63#2:1011\n78#2,4:1012\n64#2:1016\n65#2:1018\n58#2,3:1025\n71#2,4:1028\n61#2:1032\n63#2:1075\n78#2,4:1076\n64#2:1080\n65#2:1082\n58#2,3:1089\n71#2,4:1092\n61#2:1096\n48#2,2:1108\n71#2,4:1110\n50#2,3:1114\n78#2,4:1117\n48#2,2:1125\n71#2,4:1127\n50#2,3:1131\n78#2,4:1134\n63#2:1173\n78#2,4:1174\n64#2:1178\n65#2:1180\n58#2,3:1187\n71#2,4:1190\n61#2:1194\n63#2:1233\n78#2,4:1234\n64#2:1238\n65#2:1240\n58#2,3:1245\n71#2,4:1248\n61#2:1252\n63#2:1291\n78#2,4:1292\n64#2:1296\n65#2:1298\n48#2,2:1302\n71#2,4:1304\n50#2,3:1308\n78#2,4:1311\n48#2,2:1315\n71#2,4:1317\n50#2:1321\n52#2:1359\n78#2,4:1360\n58#2,3:1364\n71#2,4:1367\n61#2:1371\n63#2:1409\n78#2,4:1410\n64#2:1414\n65#2:1416\n48#2,2:1421\n71#2,4:1423\n50#2,3:1427\n78#2,4:1430\n48#2,2:1434\n71#2,4:1436\n50#2,3:1440\n78#2,4:1443\n53#3,6:725\n59#3,24:733\n83#3,3:759\n53#3,2:785\n55#3,4:792\n59#3,24:798\n83#3,3:824\n53#3,6:850\n59#3,24:858\n83#3,3:884\n53#3,6:910\n59#3,24:918\n83#3,3:944\n53#3,2:970\n55#3,4:976\n59#3,24:982\n83#3,3:1008\n53#3,2:1034\n55#3,4:1040\n59#3,24:1046\n83#3,3:1072\n53#3,2:1098\n55#3,4:1138\n59#3,24:1144\n83#3,3:1170\n53#3,6:1196\n59#3,24:1204\n83#3,3:1230\n53#3,6:1254\n59#3,24:1262\n83#3,3:1288\n53#3,6:1322\n59#3,24:1330\n83#3,3:1356\n53#3,6:1372\n59#3,24:1380\n83#3,3:1406\n71#4,2:731\n50#4,2:757\n71#4,2:773\n71#4,2:796\n50#4,2:822\n71#4,2:838\n71#4,2:856\n50#4,2:882\n71#4,2:898\n71#4,2:916\n50#4,2:942\n71#4,2:958\n71#4,2:980\n50#4,2:1006\n71#4,2:1022\n71#4,2:1044\n50#4,2:1070\n71#4,2:1086\n71#4,2:1104\n71#4,2:1106\n71#4,2:1142\n50#4,2:1168\n71#4,2:1184\n71#4,2:1202\n50#4,2:1228\n71#4,2:1260\n50#4,2:1286\n71#4,2:1328\n50#4,2:1354\n71#4,2:1378\n50#4,2:1404\n50#4:1417\n51#4:1420\n29#5:768\n29#5:833\n29#5:893\n29#5:953\n29#5:1017\n29#5:1081\n29#5:1179\n29#5:1239\n29#5:1297\n29#5:1415\n74#6,2:770\n74#6,2:835\n74#6,2:895\n74#6,2:955\n74#6,2:1019\n74#6,2:1083\n74#6,2:1181\n74#6,2:1241\n74#6,2:1299\n74#6,2:1418\n1563#7:787\n1634#7,3:788\n1563#7:972\n1634#7,3:973\n1563#7:1036\n1634#7,3:1037\n1563#7:1100\n1634#7,3:1101\n1563#7:1121\n1634#7,3:1122\n1#8:791\n*S KotlinDebug\n*F\n+ 1 CameraDeviceWrapper.kt\nandroidx/camera/camera2/pipe/compat/AndroidCameraDevice\n*L\n142#1:715\n142#1:724\n142#1:772\n184#1:775\n184#1:784\n184#1:837\n234#1:840\n234#1:849\n234#1:897\n272#1:900\n272#1:909\n272#1:957\n310#1:960\n310#1:969\n310#1:1021\n350#1:1024\n350#1:1033\n350#1:1085\n386#1:1088\n386#1:1097\n386#1:1183\n477#1:1186\n477#1:1195\n477#1:1243\n484#1:1244\n484#1:1253\n484#1:1301\n142#1:716,3\n142#1:719,4\n142#1:723\n142#1:762\n142#1:763,4\n142#1:767\n142#1:769\n184#1:776,3\n184#1:779,4\n184#1:783\n184#1:827\n184#1:828,4\n184#1:832\n184#1:834\n234#1:841,3\n234#1:844,4\n234#1:848\n234#1:887\n234#1:888,4\n234#1:892\n234#1:894\n272#1:901,3\n272#1:904,4\n272#1:908\n272#1:947\n272#1:948,4\n272#1:952\n272#1:954\n310#1:961,3\n310#1:964,4\n310#1:968\n310#1:1011\n310#1:1012,4\n310#1:1016\n310#1:1018\n350#1:1025,3\n350#1:1028,4\n350#1:1032\n350#1:1075\n350#1:1076,4\n350#1:1080\n350#1:1082\n386#1:1089,3\n386#1:1092,4\n386#1:1096\n442#1:1108,2\n442#1:1110,4\n442#1:1114,3\n442#1:1117,4\n460#1:1125,2\n460#1:1127,4\n460#1:1131,3\n460#1:1134,4\n386#1:1173\n386#1:1174,4\n386#1:1178\n386#1:1180\n477#1:1187,3\n477#1:1190,4\n477#1:1194\n477#1:1233\n477#1:1234,4\n477#1:1238\n477#1:1240\n484#1:1245,3\n484#1:1248,4\n484#1:1252\n484#1:1291\n484#1:1292,4\n484#1:1296\n484#1:1298\n490#1:1302,2\n490#1:1304,4\n490#1:1308,3\n490#1:1311,4\n496#1:1315,2\n496#1:1317,4\n496#1:1321\n496#1:1359\n496#1:1360,4\n528#1:1364,3\n528#1:1367,4\n528#1:1371\n528#1:1409\n528#1:1410,4\n528#1:1414\n528#1:1416\n543#1:1421,2\n543#1:1423,4\n543#1:1427,3\n543#1:1430,4\n547#1:1434,2\n547#1:1436,4\n547#1:1440,3\n547#1:1443,4\n142#1:725,6\n142#1:733,24\n142#1:759,3\n184#1:785,2\n184#1:792,4\n184#1:798,24\n184#1:824,3\n234#1:850,6\n234#1:858,24\n234#1:884,3\n272#1:910,6\n272#1:918,24\n272#1:944,3\n310#1:970,2\n310#1:976,4\n310#1:982,24\n310#1:1008,3\n350#1:1034,2\n350#1:1040,4\n350#1:1046,24\n350#1:1072,3\n386#1:1098,2\n386#1:1138,4\n386#1:1144,24\n386#1:1170,3\n477#1:1196,6\n477#1:1204,24\n477#1:1230,3\n484#1:1254,6\n484#1:1262,24\n484#1:1288,3\n497#1:1322,6\n497#1:1330,24\n497#1:1356,3\n529#1:1372,6\n529#1:1380,24\n529#1:1406,3\n142#1:731,2\n142#1:757,2\n163#1:773,2\n184#1:796,2\n184#1:822,2\n216#1:838,2\n234#1:856,2\n234#1:882,2\n255#1:898,2\n272#1:916,2\n272#1:942,2\n293#1:958,2\n310#1:980,2\n310#1:1006,2\n332#1:1022,2\n350#1:1044,2\n350#1:1070,2\n372#1:1086,2\n431#1:1104,2\n436#1:1106,2\n386#1:1142,2\n386#1:1168,2\n468#1:1184,2\n477#1:1202,2\n477#1:1228,2\n484#1:1260,2\n484#1:1286,2\n497#1:1328,2\n497#1:1354,2\n529#1:1378,2\n529#1:1404,2\n528#1:1417\n528#1:1420\n142#1:768\n184#1:833\n234#1:893\n272#1:953\n310#1:1017\n350#1:1081\n386#1:1179\n477#1:1239\n484#1:1297\n528#1:1415\n142#1:770,2\n184#1:835,2\n234#1:895,2\n272#1:955,2\n310#1:1019,2\n350#1:1083,2\n386#1:1181,2\n477#1:1241,2\n484#1:1299,2\n528#1:1418,2\n188#1:787\n188#1:788,3\n316#1:972\n316#1:973,3\n356#1:1036\n356#1:1037,3\n390#1:1100\n390#1:1101,3\n449#1:1121\n449#1:1122,3\n*E\n"})
/* loaded from: classes3.dex */
public final class AndroidCameraDevice implements CameraDeviceWrapper {
    private final AtomicRef<SessionStateCallback> _lastStateCallback;
    private final CameraDevice cameraDevice;
    private final CameraErrorListener cameraErrorListener;
    private final String cameraId;
    private final CameraMetadata cameraMetadata;
    private final AtomicBoolean closed;
    private final CameraInterop.CaptureSessionListener interopCaptureSessionListener;
    private final Threads threads;

    private AndroidCameraDevice(CameraMetadata r1, CameraDevice r2, String r3, CameraErrorListener r4, CameraInterop.CaptureSessionListener r5, Threads r6) {
        this.cameraMetadata = r1;
        this.cameraDevice = r2;
        this.cameraId = r3;
        this.cameraErrorListener = r4;
        this.interopCaptureSessionListener = r5;
        this.threads = r6;
        this.closed = AtomicFU.atomic(false);
        this._lastStateCallback = AtomicFU.atomic(null);
    }

    public /* synthetic */ AndroidCameraDevice(CameraMetadata r1, CameraDevice r2, String r3, CameraErrorListener r4, CameraInterop.CaptureSessionListener r5, Threads r6, DefaultConstructorMarker r7) {
        this(r1, r2, r3, r4, r5, r6);
    }

    public static final /* synthetic */ CameraDevice access$getCameraDevice$p(AndroidCameraDevice r0) {
        return r0.cameraDevice;
    }

    public static final /* synthetic */ CameraErrorListener access$getCameraErrorListener$p(AndroidCameraDevice r0) {
        return r0.cameraErrorListener;
    }

    public static final /* synthetic */ CameraMetadata access$getCameraMetadata$p(AndroidCameraDevice r0) {
        return r0.cameraMetadata;
    }

    public static final /* synthetic */ CameraInterop.CaptureSessionListener access$getInteropCaptureSessionListener$p(AndroidCameraDevice r0) {
        return r0.interopCaptureSessionListener;
    }

    public static final /* synthetic */ Threads access$getThreads$p(AndroidCameraDevice r0) {
        return r0.threads;
    }

    private final Pair<Boolean, SessionStateCallback> checkAndSetStateCallback(SessionStateCallback r3) {
        if (this.closed.getValue() == false) goto L7;
        onSessionFinalizedWithTrace(r3);
        return new Pair(Boolean.FALSE, null);
    L7:
        return new Pair(Boolean.TRUE, this._lastStateCallback.getAndSet(r3));
    }

    private final void onSessionDisconnectedWithTrace(SessionStateCallback r2) {
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection(this + "#onSessionDisconnected");     // Catch: Throwable -> L6
        r2.onSessionDisconnected();     // Catch: Throwable -> L6
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L6
        Trace.endSection();
        return;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    private final void onSessionFinalizedWithTrace(SessionStateCallback r2) {
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection(this + "#onSessionFinalized");     // Catch: Throwable -> L6
        r2.onSessionFinalized();     // Catch: Throwable -> L6
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L6
        Trace.endSection();
        return;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    /* renamed from: createCaptureRequest-2PPcXtw, reason: not valid java name */
    public CaptureRequest.Builder mo411createCaptureRequest2PPcXtw(int r18) {
        Debug r6 = Debug.INSTANCE;
        String r7 = "CXCP#createCaptureRequest-" + mo412getCameraIdDz_R5H8();
        long r8 = r6.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU();
        Trace.beginSection(r7);     // Catch: Throwable -> L12
        String r13 = mo412getCameraIdDz_R5H8();     // Catch: Throwable -> L12
        CameraErrorListener r14 = access$getCameraErrorListener$p(this);     // Catch: Throwable -> L12
        double r15 = 1000000.0d;
        CaptureRequest.Builder r0 = access$getCameraDevice$p(this).createCaptureRequest(r18);     // Catch: Throwable -> L8 Exception -> L10
    L43:
        Trace.endSection();
        long r4 = DurationNs.m498constructorimpl(r6.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r8);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L46;
        StringBuilder r62 = new StringBuilder();
        r62.append(r7);
        r62.append(" - ");
        Timestamps r2 = Timestamps.INSTANCE;
        r62.append(String.format(null, "%.3f ms", Arrays.copyOf(new Object[]{Double.valueOf(r4 / r15)}, 1)));
        android.util.Log.d("CXCP", r62.toString());
    L46:
        return r0;
    L10:
        e = e;
    L17:
        if ((e instanceof CameraAccessException) == false) goto L24;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L21;
        android.util.Log.w("CXCP", "Failed to execute call: Camera encountered an error: " + e.getMessage());     // Catch: Throwable -> L8
    L21:
        r14.mo447onCameraError3M5Xam4(r13, CameraError.Companion.m184fromPVuDhNw$camera_camera2_pipe((CameraAccessException) e), true);     // Catch: Throwable -> L8
    L22:
        r0 = null;
        goto L43
    L24:
        if ((e instanceof IllegalArgumentException) == true) goto L39;
        if ((e instanceof SecurityException) == true) goto L39;
        if ((e instanceof UnsupportedOperationException) == true) goto L39;
        if ((e instanceof NullPointerException) == true) goto L39;
        if ((e instanceof IllegalStateException) == true) goto L35;
        throw e;     // Catch: Throwable -> L8
    L35:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L22;
        android.util.Log.d("CXCP", "Failed to execute call: Camera may be closed");     // Catch: Throwable -> L8
    L39:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L41;
        android.util.Log.w("CXCP", "Failed to execute call: Unexpected exception: " + e.getMessage());     // Catch: Throwable -> L8
    L41:
        r14.mo447onCameraError3M5Xam4(r13, CameraError.Companion.m195getERROR_GRAPH_CONFIGv7Vf74A(), false);     // Catch: Throwable -> L8
        goto L22
    L14:
        e = e;
        r15 = 1000000.0d;
    L8:
        th = th;
    L47:
        Trace.endSection();
        long r42 = DurationNs.m498constructorimpl(r6.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r8);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L50;
        StringBuilder r63 = new StringBuilder();
        r63.append(r7);
        r63.append(" - ");
        Timestamps r22 = Timestamps.INSTANCE;
        r63.append(String.format(null, "%.3f ms", Arrays.copyOf(new Object[]{Double.valueOf(r42 / 1000000.0d)}, 1)));
        android.util.Log.d("CXCP", r63.toString());
    L50:
        throw th;
    L12:
        th = th;
        goto L47
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v9, types: [int] */
    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public boolean createCaptureSession(SessionConfigData r27) {
        ?? r7 = "%.3f ms";
        Pair<Boolean, SessionStateCallback> r0 = checkAndSetStateCallback(r27.getStateCallback());
        boolean r2 = r0.component1().booleanValue();
        SessionStateCallback r3 = r0.component2();
        if (r2 == true) goto L5;
        return false;
    L5:
        if (r3 == null) goto L7;
        onSessionDisconnectedWithTrace(r3);
        Unit r02 = Unit.INSTANCE;
    L7:
        Debug r11 = Debug.INSTANCE;
        String r12 = "CXCP#createCaptureSession-" + mo412getCameraIdDz_R5H8();
        long r13 = r11.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU();
        Trace.beginSection(r12);     // Catch: Throwable -> L121
        String r5 = mo412getCameraIdDz_R5H8();     // Catch: Throwable -> L121
        CameraErrorListener r6 = access$getCameraErrorListener$p(this);     // Catch: Throwable -> L121
        int r03 = r27.getSessionType();     // Catch: Throwable -> L76 Exception -> L78
        List<OutputConfigurationWrapper> r22 = r27.getOutputConfigurations();     // Catch: Throwable -> L76 Exception -> L78
        double r18 = 1000000.0d;
        ArrayList r15 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r22, 10));     // Catch: Exception -> L18 Throwable -> L72
        Iterator r23 = r22.iterator();     // Catch: Exception -> L18 Throwable -> L72
    L13:
        if (r23.hasNext() == false) goto L22;
        r15.add((OutputConfiguration) ((OutputConfigurationWrapper) r23.next()).unwrapAs(Reflection.getOrCreateKotlinClass(OutputConfiguration.class)));     // Catch: Throwable -> L16 Exception -> L18
    L16:
        th = th;
        String r62 = "%.3f ms";
        Debug r232 = r11;
        long r21 = r13;
        int r72 = 1;
    L123:
        Trace.endSection();
        long r1 = DurationNs.m498constructorimpl(r232.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r21);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L126;
        StringBuilder r32 = new StringBuilder();
        r32.append(r12);
        r32.append(" - ");
        Timestamps r4 = Timestamps.INSTANCE;
        r32.append(String.format(null, r62, Arrays.copyOf(new Object[]{Double.valueOf(r1 / r18)}, r72)));
        android.util.Log.d("CXCP", r32.toString());
    L126:
        throw th;
    L22:
        String r17 = "%.3f ms";
        Debug r233 = r11;
        String r112 = r5;
        boolean r73 = true;
        boolean r74 = 1;
        CameraErrorListener r132 = r6;
        long r212 = r13;
        SessionConfiguration r04 = Api28Compat.newSessionConfiguration(r03, r15, r27.getExecutor(), new AndroidCaptureSessionStateCallback(this, r27.getStateCallback(), r3, access$getCameraErrorListener$p(this), access$getInteropCaptureSessionListener$p(this), access$getThreads$p(this).getCamera2Handler()));     // Catch: Throwable -> L31 Exception -> L33
        if (r27.getInputConfiguration() != null) goto L29;
    L36:
        String r24 = r27.m484getSessionColorSpacedxVZaPA();     // Catch: Throwable -> L31 Exception -> L33
        if (Build.VERSION.SDK_INT < 34) goto L45;
        if (r24 == null) goto L45;
        ColorSpace.Named r42 = CameraColorSpace.m162toColorSpaceNamedimpl(r24);     // Catch: Throwable -> L31 Exception -> L33
        if (r42 == null) goto L43;
        Api34Compat.setColorSpace(r04, r42);     // Catch: Throwable -> L31 Exception -> L33
    L49:
        Debug r25 = Debug.INSTANCE;     // Catch: Throwable -> L31 Exception -> L33
        Trace.beginSection("createCaptureRequest");     // Catch: Throwable -> L69
        CaptureRequest.Builder r26 = access$getCameraDevice$p(this).createCaptureRequest(r27.getSessionTemplateId());     // Catch: Throwable -> L69
        Trace.endSection();     // Catch: Throwable -> L31 Exception -> L33
        Set<CaptureRequest.Key<?>> r43 = access$getCameraMetadata$p(this).getSessionKeys();     // Catch: Throwable -> L31 Exception -> L33
        ArrayList r52 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r43, 10));     // Catch: Throwable -> L31 Exception -> L33
        Iterator r44 = r43.iterator();     // Catch: Throwable -> L31 Exception -> L33
    L53:
        if (r44.hasNext() == false) goto L55;
        r52.add(((CaptureRequest.Key) r44.next()).getName());     // Catch: Throwable -> L31 Exception -> L33
        goto L53
    L55:
        Iterator<Map.Entry<?, Object>> r45 = r27.getSessionParameters().entrySet().iterator();     // Catch: Throwable -> L31 Exception -> L33
    L57:
        if (r45.hasNext() == false) goto L63;
        Map.Entry<?, Object> r63 = r45.next();     // Catch: Throwable -> L31 Exception -> L33
        Object r10 = r63.getKey();     // Catch: Throwable -> L31 Exception -> L33
        Object r64 = r63.getValue();     // Catch: Throwable -> L31 Exception -> L33
        if ((r10 instanceof CaptureRequest.Key) == false) goto L57;
        if (r52.contains(((CaptureRequest.Key) r10).getName()) == false) goto L57;
        RequestsKt.writeParameter(r26, r10, r64);     // Catch: Throwable -> L31 Exception -> L33
        goto L57
    L63:
        Api28Compat.setSessionParameters(r04, r26.build());     // Catch: Throwable -> L31 Exception -> L33
        Debug r28 = Debug.INSTANCE;     // Catch: Throwable -> L31 Exception -> L33
        Trace.beginSection("Api28Compat.createCaptureSession");     // Catch: Throwable -> L66
        Api28Compat.createCaptureSession(access$getCameraDevice$p(this), r04);     // Catch: Throwable -> L66
        Unit r05 = Unit.INSTANCE;     // Catch: Throwable -> L66
        Trace.endSection();     // Catch: Throwable -> L31 Exception -> L33
        Unit r46 = Unit.INSTANCE;     // Catch: Throwable -> L31 Exception -> L33
        boolean r29 = false;
    L108:
        Trace.endSection();
        long r53 = DurationNs.m498constructorimpl(r233.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r212);
        Log r06 = Log.INSTANCE;
        if (r06.getDEBUG_LOGGABLE() == false) goto L111;
        StringBuilder r102 = new StringBuilder();
        r102.append(r12);
        r102.append(" - ");
        Timestamps r8 = Timestamps.INSTANCE;
        r102.append(String.format(null, r17, Arrays.copyOf(new Object[]{Double.valueOf(r53 / r18)}, r74)));
        android.util.Log.d("CXCP", r102.toString());
    L111:
        if (r46 == null) goto L113;
    L117:
        if (r46 == null) goto L120;
        return r74;
    L120:
        return r29;
    L113:
        if (r06.getWARN_LOGGABLE() == false) goto L115;
        android.util.Log.w("CXCP", "Failed to create capture session from " + access$getCameraDevice$p(this) + ". Finalizing previous session");
    L115:
        if (r3 == null) goto L117;
        onSessionFinalizedWithTrace(r3);
        Unit r07 = Unit.INSTANCE;
    L66:
        th = move-exception;
        Trace.endSection();     // Catch: Throwable -> L31 Exception -> L33
        throw th;     // Catch: Throwable -> L31 Exception -> L33
    L69:
        th = move-exception;
        Trace.endSection();     // Catch: Throwable -> L31 Exception -> L33
        throw th;     // Catch: Throwable -> L31 Exception -> L33
    L43:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L49;
        android.util.Log.w("CXCP", "Provided session color space " + r24 + " is not supported");     // Catch: Throwable -> L31 Exception -> L33
    L45:
        if (r24 == null) goto L49;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L49;
        android.util.Log.w("CXCP", "Failed to set session color space to " + r24 + ", at least API level 34 is required");     // Catch: Throwable -> L31 Exception -> L33
        goto L49
    L29:
        if (Build.VERSION.SDK_INT < 31) goto L35;
        Api28Compat.setInputConfiguration(r04, Api31Compat.newInputConfiguration(r27.getInputConfiguration(), mo412getCameraIdDz_R5H8()));     // Catch: Throwable -> L31 Exception -> L33
        goto L36
    L35:
        Api28Compat.setInputConfiguration(r04, new InputConfiguration(((InputConfigData) CollectionsKt.single(r27.getInputConfiguration())).getWidth(), ((InputConfigData) CollectionsKt.single(r27.getInputConfiguration())).getHeight(), ((InputConfigData) CollectionsKt.single(r27.getInputConfiguration())).getFormat()));     // Catch: Throwable -> L31 Exception -> L33
    L33:
        e = e;
    L81:
        if ((e instanceof CameraAccessException) == false) goto L89;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L85;
        android.util.Log.w("CXCP", "Failed to execute call: Camera encountered an error: " + e.getMessage());     // Catch: Throwable -> L31
    L85:
        r132.mo447onCameraError3M5Xam4(r112, CameraError.Companion.m184fromPVuDhNw$camera_camera2_pipe((CameraAccessException) e), r73);     // Catch: Throwable -> L31
    L86:
        r29 = false;
    L87:
        r46 = null;
        r74 = r73;
        goto L108
    L89:
        if ((e instanceof IllegalArgumentException) == true) goto L104;
        if ((e instanceof SecurityException) == true) goto L104;
        if ((e instanceof UnsupportedOperationException) == true) goto L104;
        if ((e instanceof NullPointerException) == true) goto L104;
        if ((e instanceof IllegalStateException) == true) goto L100;
        throw e;     // Catch: Throwable -> L31
    L100:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L86;
        android.util.Log.d("CXCP", "Failed to execute call: Camera may be closed");     // Catch: Throwable -> L31
    L104:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L106;
        android.util.Log.w("CXCP", "Failed to execute call: Unexpected exception: " + e.getMessage());     // Catch: Throwable -> L31
    L106:
        r29 = false;
        r132.mo447onCameraError3M5Xam4(r112, CameraError.Companion.m195getERROR_GRAPH_CONFIGv7Vf74A(), false);     // Catch: Throwable -> L31
        goto L87
    L74:
        e = e;
        r17 = "%.3f ms";
        r233 = r11;
        r112 = r5;
        boolean r75 = true;
        r212 = r13;
    L21:
        r132 = r6;
        r73 = r75;
    L18:
        e = e;
        r17 = "%.3f ms";
        r233 = r11;
        r212 = r13;
        boolean r76 = true;
    L20:
        r112 = r5;
        r75 = r76;
    L72:
        th = th;
        String r172 = "%.3f ms";
        r232 = r11;
        r21 = r13;
        r7 = 1;
    L32:
        r62 = r172;
        r72 = r7;
        goto L123
    L78:
        e = e;
        r17 = "%.3f ms";
        r233 = r11;
        r212 = r13;
        r76 = true;
        r18 = 1000000.0d;
    L76:
        th = th;
        r172 = "%.3f ms";
        r232 = r11;
        r21 = r13;
        r7 = 1;
        r18 = 1000000.0d;
    L121:
        th = th;
        r62 = "%.3f ms";
        r232 = r11;
        r21 = r13;
        r72 = 1;
        r18 = 1000000.0d;
    L31:
        th = th;
        goto L32
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v1, types: [androidx.camera.camera2.pipe.internal.CameraErrorListener] */
    /* JADX WARN: Type inference failed for: r12v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public boolean createCaptureSession(List<? extends Surface> r27, CameraCaptureSessionWrapper.StateCallback r28) {
        Pair<Boolean, SessionStateCallback> r0 = checkAndSetStateCallback(r28);
        boolean r3 = r0.component1().booleanValue();
        SessionStateCallback r4 = r0.component2();
        if (r3 == true) goto L5;
        return false;
    L5:
        if (r4 == null) goto L7;
        onSessionDisconnectedWithTrace(r4);
    L7:
        Debug r13 = Debug.INSTANCE;
        String r14 = "CXCP#createCaptureSession-" + mo412getCameraIdDz_R5H8();
        long r15 = r13.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU();
        Trace.beginSection(r14);     // Catch: Throwable -> L68
        String r6 = mo412getCameraIdDz_R5H8();     // Catch: Throwable -> L68
        ?? r12 = access$getCameraErrorListener$p(this);     // Catch: Throwable -> L68
        SessionStateCallback r32 = r4;
        Debug r23 = r13;
        String r132 = r6;
        String r16 = "%.3f ms";
        ?? r7 = 1;
        long r19 = r15;
        access$getCameraDevice$p(this).createCaptureSession(r27, new AndroidCaptureSessionStateCallback(this, r28, r32, access$getCameraErrorListener$p(this), access$getInteropCaptureSessionListener$p(this), access$getThreads$p(this).getCamera2Handler()), access$getThreads$p(this).getCamera2Handler());     // Catch: Throwable -> L15 Exception -> L17
        Unit r5 = Unit.INSTANCE;     // Catch: Throwable -> L15 Exception -> L17
        boolean r2 = false;
        int r72 = r7;
    L55:
        Trace.endSection();
        long r10 = DurationNs.m498constructorimpl(r23.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r19);
        Log r02 = Log.INSTANCE;
        if (r02.getDEBUG_LOGGABLE() == false) goto L58;
        StringBuilder r42 = new StringBuilder();
        r42.append(r14);
        r42.append(" - ");
        Timestamps r62 = Timestamps.INSTANCE;
        r42.append(String.format(null, r16, Arrays.copyOf(new Object[]{Double.valueOf(r10 / 1000000.0d)}, r72)));
        android.util.Log.d("CXCP", r42.toString());
    L58:
        if (r5 == null) goto L60;
    L64:
        if (r5 == null) goto L67;
        return r72;
    L67:
        return r2;
    L60:
        if (r02.getWARN_LOGGABLE() == false) goto L62;
        android.util.Log.w("CXCP", "Failed to create capture session from " + access$getCameraDevice$p(this) + ". Finalizing previous session");
    L62:
        if (r32 == null) goto L64;
        onSessionFinalizedWithTrace(r32);
    L17:
        e = e;
    L28:
        if ((e instanceof CameraAccessException) == false) goto L36;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L32;
        android.util.Log.w("CXCP", "Failed to execute call: Camera encountered an error: " + e.getMessage());     // Catch: Throwable -> L15
    L32:
        r12.mo447onCameraError3M5Xam4(r132, CameraError.Companion.m184fromPVuDhNw$camera_camera2_pipe((CameraAccessException) e), r7);     // Catch: Throwable -> L15
    L33:
        r2 = false;
    L34:
        r5 = null;
        r72 = r7;
        goto L55
    L36:
        if ((e instanceof IllegalArgumentException) == true) goto L51;
        if ((e instanceof SecurityException) == true) goto L51;
        if ((e instanceof UnsupportedOperationException) == true) goto L51;
        if ((e instanceof NullPointerException) == true) goto L51;
        if ((e instanceof IllegalStateException) == true) goto L47;
        throw e;     // Catch: Throwable -> L15
    L47:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L33;
        android.util.Log.d("CXCP", "Failed to execute call: Camera may be closed");     // Catch: Throwable -> L15
    L51:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L53;
        android.util.Log.w("CXCP", "Failed to execute call: Unexpected exception: " + e.getMessage());     // Catch: Throwable -> L15
    L53:
        r2 = false;
        r12.mo447onCameraError3M5Xam4(r132, CameraError.Companion.m195getERROR_GRAPH_CONFIGv7Vf74A(), false);     // Catch: Throwable -> L15
    L15:
        th = th;
    L16:
        String r102 = r16;
        int r73 = r7;
    L70:
        Trace.endSection();
        long r1 = DurationNs.m498constructorimpl(r23.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r19);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L73;
        StringBuilder r33 = new StringBuilder();
        r33.append(r14);
        r33.append(" - ");
        Timestamps r43 = Timestamps.INSTANCE;
        r33.append(String.format(null, r102, Arrays.copyOf(new Object[]{Double.valueOf(r1 / 1000000.0d)}, r73)));
        android.util.Log.d("CXCP", r33.toString());
    L73:
        throw th;
    L21:
        e = e;
        r16 = "%.3f ms";
        r7 = 1;
        r19 = r15;
        r23 = r13;
        r132 = r6;
    L19:
        th = th;
        r16 = "%.3f ms";
        r7 = 1;
        r19 = r15;
        r23 = r13;
        goto L16
    L25:
        e = e;
        r23 = r13;
        r19 = r15;
        r132 = r6;
        r16 = "%.3f ms";
        r7 = 1;
        r32 = r4;
    L23:
        th = th;
        r23 = r13;
        r19 = r15;
        r16 = "%.3f ms";
        r7 = 1;
    L68:
        th = th;
        r102 = "%.3f ms";
        r23 = r13;
        r19 = r15;
        r73 = 1;
        goto L70
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v5, types: [int] */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r14v8 */
    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public boolean createCaptureSessionByOutputConfigurations(List<? extends OutputConfigurationWrapper> r24, CameraCaptureSessionWrapper.StateCallback r25) {
        Pair<Boolean, SessionStateCallback> r0 = checkAndSetStateCallback(r25);
        boolean r3 = r0.component1().booleanValue();
        SessionStateCallback r4 = r0.component2();
        if (r3 == true) goto L5;
        return false;
    L5:
        if (r4 == null) goto L7;
        onSessionDisconnectedWithTrace(r4);
    L7:
        Debug r11 = Debug.INSTANCE;
        String r12 = "CXCP#createCaptureSessionByOutputConfigurations-" + mo412getCameraIdDz_R5H8();
        long r13 = r11.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU();
        Trace.beginSection(r12);     // Catch: Throwable -> L73
        String r6 = mo412getCameraIdDz_R5H8();     // Catch: Throwable -> L73
        double r16 = 1000000.0d;
        CameraErrorListener r15 = access$getCameraErrorListener$p(this);     // Catch: Throwable -> L15
        CameraDevice r02 = access$getCameraDevice$p(this);     // Catch: Throwable -> L15 Exception -> L17
        List<? extends OutputConfigurationWrapper> r32 = r24;     // Catch: Throwable -> L15 Exception -> L17
        ArrayList r10 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r32, 10));     // Catch: Throwable -> L15 Exception -> L17
        Iterator r33 = r32.iterator();     // Catch: Throwable -> L15 Exception -> L17
    L13:
        if (r33.hasNext() == false) goto L20;
        r10.add((OutputConfiguration) ((OutputConfigurationWrapper) r33.next()).unwrapAs(Reflection.getOrCreateKotlinClass(OutputConfiguration.class)));     // Catch: Throwable -> L15 Exception -> L17
        r02 = r02;
        goto L13
    L20:
        SessionStateCallback r34 = r4;
        Debug r20 = r11;
        String r112 = r6;
        long r18 = r13;
        boolean r14 = true;
        boolean r142 = 1;
        Api24Compat.createCaptureSessionByOutputConfigurations(r02, r10, new AndroidCaptureSessionStateCallback(this, r25, r34, access$getCameraErrorListener$p(this), access$getInteropCaptureSessionListener$p(this), access$getThreads$p(this).getCamera2Handler()), access$getThreads$p(this).getCamera2Handler());     // Catch: Throwable -> L26 Exception -> L28
        Unit r5 = Unit.INSTANCE;     // Catch: Throwable -> L26 Exception -> L28
        boolean r2 = false;
    L60:
        Trace.endSection();
        long r102 = DurationNs.m498constructorimpl(r20.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r18);
        Log r03 = Log.INSTANCE;
        if (r03.getDEBUG_LOGGABLE() == false) goto L63;
        StringBuilder r42 = new StringBuilder();
        r42.append(r12);
        r42.append(" - ");
        Timestamps r62 = Timestamps.INSTANCE;
        r42.append(String.format(null, "%.3f ms", Arrays.copyOf(new Object[]{Double.valueOf(r102 / 1000000.0d)}, r142)));
        android.util.Log.d("CXCP", r42.toString());
    L63:
        if (r5 == null) goto L65;
    L69:
        if (r5 == null) goto L72;
        return r142;
    L72:
        return r2;
    L65:
        if (r03.getWARN_LOGGABLE() == false) goto L67;
        android.util.Log.w("CXCP", "Failed to create capture session from " + access$getCameraDevice$p(this) + ". Finalizing previous session");
    L67:
        if (r34 == null) goto L69;
        onSessionFinalizedWithTrace(r34);
    L28:
        e = e;
    L33:
        if ((e instanceof CameraAccessException) == false) goto L41;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L37;
        android.util.Log.w("CXCP", "Failed to execute call: Camera encountered an error: " + e.getMessage());     // Catch: Throwable -> L26
    L37:
        r15.mo447onCameraError3M5Xam4(r112, CameraError.Companion.m184fromPVuDhNw$camera_camera2_pipe((CameraAccessException) e), r14);     // Catch: Throwable -> L26
    L38:
        r2 = false;
    L39:
        r5 = null;
        r142 = r14;
        goto L60
    L41:
        if ((e instanceof IllegalArgumentException) == true) goto L56;
        if ((e instanceof SecurityException) == true) goto L56;
        if ((e instanceof UnsupportedOperationException) == true) goto L56;
        if ((e instanceof NullPointerException) == true) goto L56;
        if ((e instanceof IllegalStateException) == true) goto L52;
        throw e;     // Catch: Throwable -> L26
    L52:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L38;
        android.util.Log.d("CXCP", "Failed to execute call: Camera may be closed");     // Catch: Throwable -> L26
    L56:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L58;
        android.util.Log.w("CXCP", "Failed to execute call: Unexpected exception: " + e.getMessage());     // Catch: Throwable -> L26
    L58:
        r2 = false;
        r15.mo447onCameraError3M5Xam4(r112, CameraError.Companion.m195getERROR_GRAPH_CONFIGv7Vf74A(), false);     // Catch: Throwable -> L26
        goto L39
    L30:
        e = e;
    L19:
        r20 = r11;
        r18 = r13;
        r14 = true;
        r112 = r6;
    L17:
        e = e;
        r34 = r4;
    L26:
        th = th;
    L75:
        Trace.endSection();
        long r1 = DurationNs.m498constructorimpl(r20.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r18);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L78;
        StringBuilder r35 = new StringBuilder();
        r35.append(r12);
        r35.append(" - ");
        Timestamps r43 = Timestamps.INSTANCE;
        r35.append(String.format(null, "%.3f ms", Arrays.copyOf(new Object[]{Double.valueOf(r1 / r16)}, r14)));
        android.util.Log.d("CXCP", r35.toString());
    L78:
        throw th;
    L15:
        th = th;
        Debug r202 = r11;
        long r182 = r13;
        int r143 = 1;
    L73:
        th = th;
        r202 = r11;
        r182 = r13;
        r143 = 1;
        r16 = 1000000.0d;
        goto L75
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v1, types: [androidx.camera.camera2.pipe.internal.CameraErrorListener] */
    /* JADX WARN: Type inference failed for: r12v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public boolean createConstrainedHighSpeedCaptureSession(List<? extends Surface> r27, CameraCaptureSessionWrapper.StateCallback r28) {
        Pair<Boolean, SessionStateCallback> r0 = checkAndSetStateCallback(r28);
        boolean r3 = r0.component1().booleanValue();
        SessionStateCallback r4 = r0.component2();
        if (r3 == true) goto L5;
        return false;
    L5:
        if (r4 == null) goto L7;
        onSessionDisconnectedWithTrace(r4);
    L7:
        Debug r13 = Debug.INSTANCE;
        String r14 = "CXCP#createConstrainedHighSpeedCaptureSession-" + mo412getCameraIdDz_R5H8();
        long r15 = r13.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU();
        Trace.beginSection(r14);     // Catch: Throwable -> L68
        String r6 = mo412getCameraIdDz_R5H8();     // Catch: Throwable -> L68
        ?? r12 = access$getCameraErrorListener$p(this);     // Catch: Throwable -> L68
        SessionStateCallback r32 = r4;
        Debug r23 = r13;
        String r132 = r6;
        String r16 = "%.3f ms";
        ?? r7 = 1;
        long r19 = r15;
        access$getCameraDevice$p(this).createConstrainedHighSpeedCaptureSession(r27, new AndroidCaptureSessionStateCallback(this, r28, r32, access$getCameraErrorListener$p(this), access$getInteropCaptureSessionListener$p(this), access$getThreads$p(this).getCamera2Handler()), access$getThreads$p(this).getCamera2Handler());     // Catch: Throwable -> L15 Exception -> L17
        Unit r5 = Unit.INSTANCE;     // Catch: Throwable -> L15 Exception -> L17
        boolean r2 = false;
        int r72 = r7;
    L55:
        Trace.endSection();
        long r10 = DurationNs.m498constructorimpl(r23.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r19);
        Log r02 = Log.INSTANCE;
        if (r02.getDEBUG_LOGGABLE() == false) goto L58;
        StringBuilder r42 = new StringBuilder();
        r42.append(r14);
        r42.append(" - ");
        Timestamps r62 = Timestamps.INSTANCE;
        r42.append(String.format(null, r16, Arrays.copyOf(new Object[]{Double.valueOf(r10 / 1000000.0d)}, r72)));
        android.util.Log.d("CXCP", r42.toString());
    L58:
        if (r5 == null) goto L60;
    L64:
        if (r5 == null) goto L67;
        return r72;
    L67:
        return r2;
    L60:
        if (r02.getWARN_LOGGABLE() == false) goto L62;
        android.util.Log.w("CXCP", "Failed to create capture session from " + access$getCameraDevice$p(this) + ". Finalizing previous session");
    L62:
        if (r32 == null) goto L64;
        onSessionFinalizedWithTrace(r32);
    L17:
        e = e;
    L28:
        if ((e instanceof CameraAccessException) == false) goto L36;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L32;
        android.util.Log.w("CXCP", "Failed to execute call: Camera encountered an error: " + e.getMessage());     // Catch: Throwable -> L15
    L32:
        r12.mo447onCameraError3M5Xam4(r132, CameraError.Companion.m184fromPVuDhNw$camera_camera2_pipe((CameraAccessException) e), r7);     // Catch: Throwable -> L15
    L33:
        r2 = false;
    L34:
        r5 = null;
        r72 = r7;
        goto L55
    L36:
        if ((e instanceof IllegalArgumentException) == true) goto L51;
        if ((e instanceof SecurityException) == true) goto L51;
        if ((e instanceof UnsupportedOperationException) == true) goto L51;
        if ((e instanceof NullPointerException) == true) goto L51;
        if ((e instanceof IllegalStateException) == true) goto L47;
        throw e;     // Catch: Throwable -> L15
    L47:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L33;
        android.util.Log.d("CXCP", "Failed to execute call: Camera may be closed");     // Catch: Throwable -> L15
    L51:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L53;
        android.util.Log.w("CXCP", "Failed to execute call: Unexpected exception: " + e.getMessage());     // Catch: Throwable -> L15
    L53:
        r2 = false;
        r12.mo447onCameraError3M5Xam4(r132, CameraError.Companion.m195getERROR_GRAPH_CONFIGv7Vf74A(), false);     // Catch: Throwable -> L15
    L15:
        th = th;
    L16:
        String r102 = r16;
        int r73 = r7;
    L70:
        Trace.endSection();
        long r1 = DurationNs.m498constructorimpl(r23.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r19);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L73;
        StringBuilder r33 = new StringBuilder();
        r33.append(r14);
        r33.append(" - ");
        Timestamps r43 = Timestamps.INSTANCE;
        r33.append(String.format(null, r102, Arrays.copyOf(new Object[]{Double.valueOf(r1 / 1000000.0d)}, r73)));
        android.util.Log.d("CXCP", r33.toString());
    L73:
        throw th;
    L21:
        e = e;
        r16 = "%.3f ms";
        r7 = 1;
        r19 = r15;
        r23 = r13;
        r132 = r6;
    L19:
        th = th;
        r16 = "%.3f ms";
        r7 = 1;
        r19 = r15;
        r23 = r13;
        goto L16
    L25:
        e = e;
        r23 = r13;
        r19 = r15;
        r132 = r6;
        r16 = "%.3f ms";
        r7 = 1;
        r32 = r4;
    L23:
        th = th;
        r23 = r13;
        r19 = r15;
        r16 = "%.3f ms";
        r7 = 1;
    L68:
        th = th;
        r102 = "%.3f ms";
        r23 = r13;
        r19 = r15;
        r73 = 1;
        goto L70
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v8, types: [int] */
    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public boolean createExtensionSession(ExtensionSessionConfigData r28) {
        ?? r7 = "%.3f ms";
        CameraExtensionSessionWrapper.StateCallback r2 = r28.getExtensionStateCallback();
        if (r2 != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("extensionStateCallback must be set to create Extension session");
        return false;
    L5:
        if (r28.getExtensionMode() == null) goto L100;
        Pair<Boolean, SessionStateCallback> r0 = checkAndSetStateCallback(r2);
        boolean r3 = r0.component1().booleanValue();
        SessionStateCallback r4 = r0.component2();
        if (r3 == true) goto L9;
        return false;
    L9:
        if (r4 == null) goto L11;
        onSessionDisconnectedWithTrace(r4);
    L11:
        Debug r11 = Debug.INSTANCE;
        String r12 = "CXCP#createExtensionSession-" + mo412getCameraIdDz_R5H8();
        long r13 = r11.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU();
        Trace.beginSection(r12);     // Catch: Throwable -> L94
        String r6 = mo412getCameraIdDz_R5H8();     // Catch: Throwable -> L94
        double r17 = 1000000.0d;
        CameraErrorListener r15 = access$getCameraErrorListener$p(this);     // Catch: Throwable -> L21
        int r02 = r28.getExtensionMode().intValue();     // Catch: Exception -> L23 Throwable -> L48
        List<OutputConfigurationWrapper> r32 = r28.getOutputConfigurations();     // Catch: Exception -> L23 Throwable -> L48
        ArrayList r10 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r32, 10));     // Catch: Exception -> L23 Throwable -> L48
        Iterator r33 = r32.iterator();     // Catch: Exception -> L23 Throwable -> L48
    L18:
        if (r33.hasNext() == false) goto L27;
        int r21 = r02;
        r10.add((OutputConfiguration) ((OutputConfigurationWrapper) r33.next()).unwrapAs(Reflection.getOrCreateKotlinClass(OutputConfiguration.class)));     // Catch: Throwable -> L21 Exception -> L23
        r02 = r21;
        goto L18
    L27:
        SessionStateCallback r34 = r4;
        String r19 = "%.3f ms";
        Debug r24 = r11;
        String r112 = r6;
        boolean r72 = true;
        boolean r73 = 1;
        long r212 = r13;
        ExtensionSessionConfiguration r03 = Api31Compat.newExtensionSessionConfiguration(r02, r10, r28.getExecutor(), new AndroidExtensionSessionStateCallback(this, r2, r34, access$getCameraErrorListener$p(this), access$getInteropCaptureSessionListener$p(this), r28.getExecutor()));     // Catch: Throwable -> L40 Exception -> L42
        if (r28.getPostviewOutputConfiguration() != null) goto L36;
    L46:
        Api31Compat.createExtensionCaptureSession(access$getCameraDevice$p(this), r03);     // Catch: Throwable -> L40 Exception -> L42
        Unit r5 = Unit.INSTANCE;     // Catch: Throwable -> L40 Exception -> L42
    L81:
        Trace.endSection();
        long r102 = DurationNs.m498constructorimpl(r24.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r212);
        Log r04 = Log.INSTANCE;
        if (r04.getDEBUG_LOGGABLE() == false) goto L84;
        StringBuilder r22 = new StringBuilder();
        r22.append(r12);
        r22.append(" - ");
        Timestamps r42 = Timestamps.INSTANCE;
        r22.append(String.format(null, r19, Arrays.copyOf(new Object[]{Double.valueOf(r102 / 1000000.0d)}, r73)));
        android.util.Log.d("CXCP", r22.toString());
    L84:
        if (r5 == null) goto L86;
    L90:
        if (r5 != null) goto L116;
        return false;
    L116:
        return r73;
    L86:
        if (r04.getWARN_LOGGABLE() == false) goto L88;
        android.util.Log.w("CXCP", "Failed to create extension session from " + access$getCameraDevice$p(this) + ". Finalizing previous session");
    L88:
        if (r34 == null) goto L90;
        onSessionFinalizedWithTrace(r34);
        goto L90
    L36:
        if (Build.VERSION.SDK_INT < 34) goto L46;
        OutputConfiguration r23 = (OutputConfiguration) r28.getPostviewOutputConfiguration().unwrapAs(Reflection.getOrCreateKotlinClass(OutputConfiguration.class));     // Catch: Throwable -> L40 Exception -> L42
        if (r23 == null) goto L45;
        Api34Compat.setPostviewOutputConfiguration(r03, r23);     // Catch: Throwable -> L40 Exception -> L42
        goto L46
    L45:
        throw new IllegalStateException("Failed to unwrap Postview OutputConfiguration");     // Catch: Throwable -> L40 Exception -> L42
    L42:
        e = e;
    L55:
        if ((e instanceof CameraAccessException) == false) goto L62;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L59;
        android.util.Log.w("CXCP", "Failed to execute call: Camera encountered an error: " + e.getMessage());     // Catch: Throwable -> L40
    L59:
        r15.mo447onCameraError3M5Xam4(r112, CameraError.Companion.m184fromPVuDhNw$camera_camera2_pipe((CameraAccessException) e), r72);     // Catch: Throwable -> L40
    L60:
        r5 = null;
        r73 = r72;
        goto L81
    L62:
        if ((e instanceof IllegalArgumentException) == true) goto L77;
        if ((e instanceof SecurityException) == true) goto L77;
        if ((e instanceof UnsupportedOperationException) == true) goto L77;
        if ((e instanceof NullPointerException) == true) goto L77;
        if ((e instanceof IllegalStateException) == true) goto L73;
        throw e;     // Catch: Throwable -> L40
    L73:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L60;
        android.util.Log.d("CXCP", "Failed to execute call: Camera may be closed");     // Catch: Throwable -> L40
    L77:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L79;
        android.util.Log.w("CXCP", "Failed to execute call: Unexpected exception: " + e.getMessage());     // Catch: Throwable -> L40
    L79:
        r15.mo447onCameraError3M5Xam4(r112, CameraError.Companion.m195getERROR_GRAPH_CONFIGv7Vf74A(), false);     // Catch: Throwable -> L40
        goto L60
    L50:
        e = e;
        r19 = "%.3f ms";
        r24 = r11;
        r212 = r13;
        r112 = r6;
        r72 = true;
    L52:
        e = e;
    L25:
        r19 = "%.3f ms";
        r24 = r11;
        r212 = r13;
        r72 = true;
        r112 = r6;
    L23:
        e = e;
        r34 = r4;
    L48:
        th = th;
        Debug r242 = r11;
        long r213 = r13;
        r7 = 1;
    L41:
        String r62 = "%.3f ms";
        int r74 = r7;
    L96:
        Trace.endSection();
        long r1 = DurationNs.m498constructorimpl(r242.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r213);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L99;
        StringBuilder r35 = new StringBuilder();
        r35.append(r12);
        r35.append(" - ");
        Timestamps r43 = Timestamps.INSTANCE;
        r35.append(String.format(null, r62, Arrays.copyOf(new Object[]{Double.valueOf(r1 / r17)}, r74)));
        android.util.Log.d("CXCP", r35.toString());
    L99:
        throw th;
    L40:
        th = th;
    L21:
        th = th;
        r62 = "%.3f ms";
        r242 = r11;
        r213 = r13;
        r74 = 1;
    L94:
        th = th;
        r62 = "%.3f ms";
        r242 = r11;
        r213 = r13;
        r74 = 1;
        r17 = 1000000.0d;
        goto L96
    L100:
        Segment$$ExternalSyntheticBUOutline1.m("extensionMode must be set to create Extension session");
        return false;
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public CaptureRequest.Builder createReprocessCaptureRequest(TotalCaptureResult r18) {
        Debug r6 = Debug.INSTANCE;
        String r7 = "CXCP#createReprocessCaptureRequest-" + mo412getCameraIdDz_R5H8();
        long r8 = r6.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU();
        Trace.beginSection(r7);     // Catch: Throwable -> L12
        String r13 = mo412getCameraIdDz_R5H8();     // Catch: Throwable -> L12
        CameraErrorListener r14 = access$getCameraErrorListener$p(this);     // Catch: Throwable -> L12
        double r15 = 1000000.0d;
        CaptureRequest.Builder r0 = access$getCameraDevice$p(this).createReprocessCaptureRequest(r18);     // Catch: Throwable -> L8 Exception -> L10
    L43:
        Trace.endSection();
        long r4 = DurationNs.m498constructorimpl(r6.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r8);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L46;
        StringBuilder r62 = new StringBuilder();
        r62.append(r7);
        r62.append(" - ");
        Timestamps r2 = Timestamps.INSTANCE;
        r62.append(String.format(null, "%.3f ms", Arrays.copyOf(new Object[]{Double.valueOf(r4 / r15)}, 1)));
        android.util.Log.d("CXCP", r62.toString());
    L46:
        return r0;
    L10:
        e = e;
    L17:
        if ((e instanceof CameraAccessException) == false) goto L24;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L21;
        android.util.Log.w("CXCP", "Failed to execute call: Camera encountered an error: " + e.getMessage());     // Catch: Throwable -> L8
    L21:
        r14.mo447onCameraError3M5Xam4(r13, CameraError.Companion.m184fromPVuDhNw$camera_camera2_pipe((CameraAccessException) e), true);     // Catch: Throwable -> L8
    L22:
        r0 = null;
        goto L43
    L24:
        if ((e instanceof IllegalArgumentException) == true) goto L39;
        if ((e instanceof SecurityException) == true) goto L39;
        if ((e instanceof UnsupportedOperationException) == true) goto L39;
        if ((e instanceof NullPointerException) == true) goto L39;
        if ((e instanceof IllegalStateException) == true) goto L35;
        throw e;     // Catch: Throwable -> L8
    L35:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L22;
        android.util.Log.d("CXCP", "Failed to execute call: Camera may be closed");     // Catch: Throwable -> L8
    L39:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L41;
        android.util.Log.w("CXCP", "Failed to execute call: Unexpected exception: " + e.getMessage());     // Catch: Throwable -> L8
    L41:
        r14.mo447onCameraError3M5Xam4(r13, CameraError.Companion.m195getERROR_GRAPH_CONFIGv7Vf74A(), false);     // Catch: Throwable -> L8
        goto L22
    L14:
        e = e;
        r15 = 1000000.0d;
    L8:
        th = th;
    L47:
        Trace.endSection();
        long r42 = DurationNs.m498constructorimpl(r6.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r8);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L50;
        StringBuilder r63 = new StringBuilder();
        r63.append(r7);
        r63.append(" - ");
        Timestamps r22 = Timestamps.INSTANCE;
        r63.append(String.format(null, "%.3f ms", Arrays.copyOf(new Object[]{Double.valueOf(r42 / 1000000.0d)}, 1)));
        android.util.Log.d("CXCP", r63.toString());
    L50:
        throw th;
    L12:
        th = th;
        goto L47
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v2, types: [int] */
    /* JADX WARN: Type inference failed for: r7v7, types: [int] */
    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public boolean createReprocessableCaptureSession(InputConfiguration r27, List<? extends Surface> r28, CameraCaptureSessionWrapper.StateCallback r29) {
        String r7 = "%.3f ms";
        Pair<Boolean, SessionStateCallback> r0 = checkAndSetStateCallback(r29);
        boolean r3 = r0.component1().booleanValue();
        SessionStateCallback r4 = r0.component2();
        if (r3 == true) goto L5;
        return false;
    L5:
        if (r4 == null) goto L7;
        onSessionDisconnectedWithTrace(r4);
    L7:
        Debug r13 = Debug.INSTANCE;
        String r14 = "CXCP#createReprocessableCaptureSession-" + mo412getCameraIdDz_R5H8();
        long r15 = r13.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU();
        Trace.beginSection(r14);     // Catch: Throwable -> L68
        String r6 = mo412getCameraIdDz_R5H8();     // Catch: Throwable -> L68
        CameraErrorListener r12 = access$getCameraErrorListener$p(this);     // Catch: Throwable -> L68
        SessionStateCallback r32 = r4;
        Debug r23 = r13;
        String r132 = r6;
        String r16 = "%.3f ms";
        boolean r72 = true;
        long r19 = r15;
        access$getCameraDevice$p(this).createReprocessableCaptureSession(r27, r28, new AndroidCaptureSessionStateCallback(this, r29, r32, access$getCameraErrorListener$p(this), access$getInteropCaptureSessionListener$p(this), access$getThreads$p(this).getCamera2Handler()), access$getThreads$p(this).getCamera2Handler());     // Catch: Throwable -> L15 Exception -> L17
        Unit r5 = Unit.INSTANCE;     // Catch: Throwable -> L15 Exception -> L17
        boolean r2 = false;
        boolean r73 = r72;
    L55:
        Trace.endSection();
        long r10 = DurationNs.m498constructorimpl(r23.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r19);
        Log r02 = Log.INSTANCE;
        if (r02.getDEBUG_LOGGABLE() == false) goto L58;
        StringBuilder r42 = new StringBuilder();
        r42.append(r14);
        r42.append(" - ");
        Timestamps r62 = Timestamps.INSTANCE;
        r42.append(String.format(null, r16, Arrays.copyOf(new Object[]{Double.valueOf(r10 / 1000000.0d)}, r73)));
        android.util.Log.d("CXCP", r42.toString());
    L58:
        if (r5 == null) goto L60;
    L64:
        if (r5 == null) goto L67;
        return r73;
    L67:
        return r2;
    L60:
        if (r02.getWARN_LOGGABLE() == false) goto L62;
        android.util.Log.w("CXCP", "Failed to create reprocess session from " + access$getCameraDevice$p(this) + ". Finalizing previous session");
    L62:
        if (r32 == null) goto L64;
        onSessionFinalizedWithTrace(r32);
    L17:
        e = e;
    L28:
        if ((e instanceof CameraAccessException) == false) goto L36;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L32;
        android.util.Log.w("CXCP", "Failed to execute call: Camera encountered an error: " + e.getMessage());     // Catch: Throwable -> L15
    L32:
        r12.mo447onCameraError3M5Xam4(r132, CameraError.Companion.m184fromPVuDhNw$camera_camera2_pipe((CameraAccessException) e), r72);     // Catch: Throwable -> L15
    L33:
        r2 = false;
    L34:
        r5 = null;
        r73 = r72;
        goto L55
    L36:
        if ((e instanceof IllegalArgumentException) == true) goto L51;
        if ((e instanceof SecurityException) == true) goto L51;
        if ((e instanceof UnsupportedOperationException) == true) goto L51;
        if ((e instanceof NullPointerException) == true) goto L51;
        if ((e instanceof IllegalStateException) == true) goto L47;
        throw e;     // Catch: Throwable -> L15
    L47:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L33;
        android.util.Log.d("CXCP", "Failed to execute call: Camera may be closed");     // Catch: Throwable -> L15
    L51:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L53;
        android.util.Log.w("CXCP", "Failed to execute call: Unexpected exception: " + e.getMessage());     // Catch: Throwable -> L15
    L53:
        r2 = false;
        r12.mo447onCameraError3M5Xam4(r132, CameraError.Companion.m195getERROR_GRAPH_CONFIGv7Vf74A(), false);     // Catch: Throwable -> L15
        goto L34
    L21:
        e = e;
        r16 = "%.3f ms";
        r72 = true;
        r19 = r15;
        r23 = r13;
        r132 = r6;
    L19:
        th = th;
        String r162 = "%.3f ms";
        r7 = 1;
        long r192 = r15;
        Debug r232 = r13;
    L16:
        String r102 = r162;
        ?? r74 = r7;
    L70:
        Trace.endSection();
        long r1 = DurationNs.m498constructorimpl(r232.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r192);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L73;
        StringBuilder r33 = new StringBuilder();
        r33.append(r14);
        r33.append(" - ");
        Timestamps r43 = Timestamps.INSTANCE;
        r33.append(String.format(null, r102, Arrays.copyOf(new Object[]{Double.valueOf(r1 / 1000000.0d)}, r74)));
        android.util.Log.d("CXCP", r33.toString());
    L73:
        throw th;
    L25:
        e = e;
        r23 = r13;
        r19 = r15;
        r132 = r6;
        r16 = "%.3f ms";
        r72 = true;
        r32 = r4;
    L23:
        th = th;
        r232 = r13;
        r192 = r15;
        r162 = "%.3f ms";
        r7 = 1;
    L68:
        th = th;
        r102 = "%.3f ms";
        r232 = r13;
        r192 = r15;
        r74 = 1;
    L15:
        th = th;
        goto L16
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v8, types: [int] */
    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public boolean createReprocessableCaptureSessionByConfigurations(InputConfigData r26, List<? extends OutputConfigurationWrapper> r27, CameraCaptureSessionWrapper.StateCallback r28) {
        Pair<Boolean, SessionStateCallback> r0 = checkAndSetStateCallback(r28);
        boolean r3 = r0.component1().booleanValue();
        SessionStateCallback r4 = r0.component2();
        if (r3 == true) goto L5;
        return false;
    L5:
        if (r4 == null) goto L7;
        onSessionDisconnectedWithTrace(r4);
    L7:
        Debug r11 = Debug.INSTANCE;
        String r12 = "CXCP#createReprocessableCaptureSessionByConfigurations-" + mo412getCameraIdDz_R5H8();
        long r13 = r11.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU();
        Trace.beginSection(r12);     // Catch: Throwable -> L76
        String r6 = mo412getCameraIdDz_R5H8();     // Catch: Throwable -> L76
        double r16 = 1000000.0d;
        CameraErrorListener r15 = access$getCameraErrorListener$p(this);     // Catch: Throwable -> L16
        CameraDevice r02 = access$getCameraDevice$p(this);     // Catch: Exception -> L18 Throwable -> L31
        InputConfiguration r10 = new InputConfiguration(r26.getWidth(), r26.getHeight(), r26.getFormat());     // Catch: Exception -> L18 Throwable -> L31
        List<? extends OutputConfigurationWrapper> r03 = r27;     // Catch: Exception -> L18 Throwable -> L31
        ArrayList r32 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r03, 10));     // Catch: Exception -> L18 Throwable -> L31
        Iterator r04 = r03.iterator();     // Catch: Exception -> L18 Throwable -> L31
    L13:
        if (r04.hasNext() == false) goto L21;
        Iterator r262 = r04;
        r32.add((OutputConfiguration) ((OutputConfigurationWrapper) r04.next()).unwrapAs(Reflection.getOrCreateKotlinClass(OutputConfiguration.class)));     // Catch: Throwable -> L16 Exception -> L18
        r04 = r262;
        goto L13
    L21:
        SessionStateCallback r33 = r4;
        String r18 = "%.3f ms";
        Debug r22 = r11;
        int r7 = 1;
        boolean r72 = 1;
        boolean r73 = true;
        String r112 = r6;
        long r19 = r13;
        Api24Compat.createReprocessableCaptureSessionByConfigurations(r02, r10, r32, new AndroidCaptureSessionStateCallback(this, r28, r33, access$getCameraErrorListener$p(this), access$getInteropCaptureSessionListener$p(this), access$getThreads$p(this).getCamera2Handler()), access$getThreads$p(this).getCamera2Handler());     // Catch: Throwable -> L27 Exception -> L29
        Unit r5 = Unit.INSTANCE;     // Catch: Throwable -> L27 Exception -> L29
        boolean r2 = false;
    L63:
        Trace.endSection();
        long r102 = DurationNs.m498constructorimpl(r22.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r19);
        Log r05 = Log.INSTANCE;
        if (r05.getDEBUG_LOGGABLE() == false) goto L66;
        StringBuilder r42 = new StringBuilder();
        r42.append(r12);
        r42.append(" - ");
        Timestamps r62 = Timestamps.INSTANCE;
        r42.append(String.format(null, r18, Arrays.copyOf(new Object[]{Double.valueOf(r102 / 1000000.0d)}, r72)));
        android.util.Log.d("CXCP", r42.toString());
    L66:
        if (r5 == null) goto L68;
    L72:
        if (r5 == null) goto L75;
        return r72;
    L75:
        return r2;
    L68:
        if (r05.getWARN_LOGGABLE() == false) goto L70;
        android.util.Log.w("CXCP", "Failed to create reprocess session from " + access$getCameraDevice$p(this) + ". Finalizing previous session");
    L70:
        if (r33 == null) goto L72;
        onSessionFinalizedWithTrace(r33);
    L29:
        e = e;
    L36:
        if ((e instanceof CameraAccessException) == false) goto L44;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L40;
        android.util.Log.w("CXCP", "Failed to execute call: Camera encountered an error: " + e.getMessage());     // Catch: Throwable -> L27
    L40:
        r15.mo447onCameraError3M5Xam4(r112, CameraError.Companion.m184fromPVuDhNw$camera_camera2_pipe((CameraAccessException) e), r73);     // Catch: Throwable -> L27
    L41:
        r2 = false;
    L42:
        r5 = null;
        r72 = r73;
        goto L63
    L44:
        if ((e instanceof IllegalArgumentException) == true) goto L59;
        if ((e instanceof SecurityException) == true) goto L59;
        if ((e instanceof UnsupportedOperationException) == true) goto L59;
        if ((e instanceof NullPointerException) == true) goto L59;
        if ((e instanceof IllegalStateException) == true) goto L55;
        throw e;     // Catch: Throwable -> L27
    L55:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L41;
        android.util.Log.d("CXCP", "Failed to execute call: Camera may be closed");     // Catch: Throwable -> L27
    L59:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L61;
        android.util.Log.w("CXCP", "Failed to execute call: Unexpected exception: " + e.getMessage());     // Catch: Throwable -> L27
    L61:
        r2 = false;
        r15.mo447onCameraError3M5Xam4(r112, CameraError.Companion.m195getERROR_GRAPH_CONFIGv7Vf74A(), false);     // Catch: Throwable -> L27
    L27:
        th = th;
    L28:
        String r103 = r18;
    L78:
        Trace.endSection();
        long r1 = DurationNs.m498constructorimpl(r22.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r19);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L81;
        StringBuilder r34 = new StringBuilder();
        r34.append(r12);
        r34.append(" - ");
        Timestamps r43 = Timestamps.INSTANCE;
        r34.append(String.format(null, r103, Arrays.copyOf(new Object[]{Double.valueOf(r1 / r16)}, r7)));
        android.util.Log.d("CXCP", r34.toString());
    L81:
        throw th;
    L33:
        e = e;
    L20:
        r18 = "%.3f ms";
        r22 = r11;
        r19 = r13;
        r73 = true;
        r112 = r6;
    L31:
        th = th;
        r18 = "%.3f ms";
        r22 = r11;
        r19 = r13;
        r7 = 1;
    L18:
        e = e;
        r33 = r4;
    L16:
        th = th;
        r103 = "%.3f ms";
        r22 = r11;
        r19 = r13;
        r7 = 1;
    L76:
        th = th;
        r103 = "%.3f ms";
        r22 = r11;
        r19 = r13;
        r7 = 1;
        r16 = 1000000.0d;
        goto L78
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    /* renamed from: getCameraId-Dz_R5H8, reason: not valid java name */
    public String mo412getCameraIdDz_R5H8() {
        return this.cameraId;
    }

    @Override // androidx.camera.camera2.pipe.compat.AudioRestrictionController.Listener
    /* renamed from: onCameraAudioRestrictionUpdated-LwUUkyU, reason: not valid java name */
    public void mo413onCameraAudioRestrictionUpdatedLwUUkyU(int r6) {
        Debug r2 = Debug.INSTANCE;
        Trace.beginSection("setCameraAudioRestriction");     // Catch: Throwable -> L35
        String r22 = mo412getCameraIdDz_R5H8();     // Catch: Throwable -> L35
        CameraErrorListener r3 = access$getCameraErrorListener$p(this);     // Catch: Throwable -> L35
        Api30Compat.setCameraAudioRestriction(access$getCameraDevice$p(this), r6);     // Catch: Exception -> L6 Throwable -> L35
        Unit r5 = Unit.INSTANCE;     // Catch: Exception -> L6 Throwable -> L35
    L33:
        Trace.endSection();
        return;
    L6:
        e = move-exception;
        if ((e instanceof CameraAccessException) == false) goto L15;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L13;
        android.util.Log.w("CXCP", "Failed to execute call: Camera encountered an error: " + e.getMessage());     // Catch: Throwable -> L35
    L13:
        r3.mo447onCameraError3M5Xam4(r22, CameraError.Companion.m184fromPVuDhNw$camera_camera2_pipe((CameraAccessException) e), true);     // Catch: Throwable -> L35
        goto L33
    L15:
        if ((e instanceof IllegalArgumentException) == true) goto L30;
        if ((e instanceof SecurityException) == true) goto L30;
        if ((e instanceof UnsupportedOperationException) == true) goto L30;
        if ((e instanceof NullPointerException) == true) goto L30;
        if ((e instanceof IllegalStateException) == true) goto L26;
        throw e;     // Catch: Throwable -> L35
    L26:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L33;
        android.util.Log.d("CXCP", "Failed to execute call: Camera may be closed");     // Catch: Throwable -> L35
    L30:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L32;
        android.util.Log.w("CXCP", "Failed to execute call: Unexpected exception: " + e.getMessage());     // Catch: Throwable -> L35
    L32:
        r3.mo447onCameraError3M5Xam4(r22, CameraError.Companion.m195getERROR_GRAPH_CONFIGv7Vf74A(), false);     // Catch: Throwable -> L35
    L35:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public void onDeviceClosed() {
        if (this.closed.getValue() == false) goto L8;
        SessionStateCallback r0 = this._lastStateCallback.getAndSet(null);
        if (r0 == null) goto L10;
        onSessionFinalizedWithTrace(r0);
        return;
    L10:
        return;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public void onDeviceClosing() {
        if (this.closed.compareAndSet(false, true) == false) goto L8;
        SessionStateCallback r0 = this._lastStateCallback.getValue();
        if (r0 == null) goto L9;
        onSessionDisconnectedWithTrace(r0);
        return;
    L9:
        return;
    }

    public String toString() {
        return "AndroidCameraDevice(camera=" + CameraId.m232toStringimpl(mo412getCameraIdDz_R5H8()) + ')';
    }

    @Override // androidx.camera.camera2.pipe.UnsafeWrapper
    public <T> T unwrapAs(KClass<T> r2) {
        if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(CameraDevice.class)) == true) goto L5;
        return null;
    L5:
        return (T) this.cameraDevice;
    }
}
