package androidx.camera.camera2.adapter;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.MediaRecorder;
import android.os.Build;
import android.util.Log;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import androidx.camera.camera2.compat.StreamConfigurationMapCompat;
import androidx.camera.camera2.compat.workaround.ExtraSupportedSurfaceCombinationsContainer;
import androidx.camera.camera2.compat.workaround.OutputSizesCorrector;
import androidx.camera.camera2.compat.workaround.ResolutionCorrector;
import androidx.camera.camera2.compat.workaround.TargetAspectRatio;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.impl.DisplayInfoManager;
import androidx.camera.camera2.internal.DynamicRangeResolver;
import androidx.camera.camera2.internal.HighSpeedResolver;
import androidx.camera.camera2.internal.StreamUseCaseUtil;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.featuregroup.impl.FeatureCombinationQuery;
import androidx.camera.core.featuregroup.impl.feature.FpsRangeFeature;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.CameraMode;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.EncoderProfilesProvider;
import androidx.camera.core.impl.EncoderProfilesProxy;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.StreamUseCase;
import androidx.camera.core.impl.SurfaceCombination;
import androidx.camera.core.impl.SurfaceConfig;
import androidx.camera.core.impl.SurfaceSizeDefinition;
import androidx.camera.core.impl.SurfaceStreamSpecQueryResult;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.stabilization.VideoStabilization;
import androidx.camera.core.impl.utils.AspectRatioUtil;
import androidx.camera.core.impl.utils.CompareSizesByArea;
import androidx.camera.core.internal.utils.SizeUtil;
import androidx.core.util.Preconditions;
import androidx.lifecycle.SavedStateHandleSupport$$ExternalSyntheticBUOutline0;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.HttpUrl$Builder$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u009c\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\b\u001a\n\u0002\u0010\u0011\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 Ñ\u00012\u00020\u0001:\bÎ\u0001Ï\u0001Ð\u0001Ñ\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJV\u0010;\u001a\u00020\u001f2\u0006\u0010<\u001a\u00020\u001a2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020>0\u001b2\u0014\b\u0002\u0010?\u001a\u000e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020A0@2\u0012\b\u0002\u0010B\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030C0\u001b2\u000e\b\u0002\u0010D\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001bJR\u0010E\u001a\u00020F2\u0006\u0010<\u001a\u00020\u001a2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020>0\u001b2\u0012\u0010?\u001a\u000e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020A0@2\u0010\u0010B\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030C0\u001b2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001bH\u0002JV\u0010H\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010\u001b2\u0006\u0010<\u001a\u00020\u001a2\u0010\u0010=\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010>\u0018\u00010\u001b2\u0012\u0010I\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020J0\u00192\u0016\u0010K\u001a\u0012\u0012\u0004\u0012\u00020\u000f\u0012\b\u0012\u0006\u0012\u0002\b\u00030C0\u0019H\u0002J\u0016\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00120\u001b2\u0006\u0010<\u001a\u00020\u001aH\u0002J&\u0010M\u001a\u00020>2\u0006\u0010N\u001a\u00020\u000f2\u0006\u0010O\u001a\u00020\u000f2\u0006\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020SJ^\u0010T\u001a\u00020U2\u0006\u0010N\u001a\u00020\u000f2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020J0\u001b2\u001c\u0010W\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030C\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0\u001b0@2\b\b\u0002\u0010X\u001a\u00020Y2\b\b\u0002\u0010Z\u001a\u00020\u001f2\u0006\u0010[\u001a\u00020\u001f2\u0006\u0010\\\u001a\u00020\u001fJ\u0084\u0001\u0010]\u001a\u00020U2\u0006\u0010^\u001a\u00020_2\u0006\u0010<\u001a\u00020\u001a2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020J0\u001b2\u001c\u0010`\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030C\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0\u001b0@2\u0010\u0010B\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030C0\u001b2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001b2\u0016\u0010a\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030C\u0012\u0004\u0012\u00020A0@2\u0006\u0010\\\u001a\u00020\u001fH\u0002J|\u0010b\u001a\u00020U2\u0006\u0010<\u001a\u00020\u001a2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020J0\u001b2\u001c\u0010`\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030C\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0\u001b0@2\u0010\u0010B\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030C0\u001b2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001b2\u0016\u0010a\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030C\u0012\u0004\u0012\u00020A0@2\u0006\u0010\\\u001a\u00020\u001fH\u0002J>\u0010c\u001a\u00020_2\f\u0010d\u001a\b\u0012\u0004\u0012\u00020A0e2\u000e\u0010f\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010g2\u0006\u0010X\u001a\u00020Y2\u0006\u0010h\u001a\u00020\u001f2\u0006\u0010[\u001a\u00020\u001fH\u0002Jn\u0010i\u001a\u00020\u001a2\u0006\u0010N\u001a\u00020\u000f2\u0006\u0010Z\u001a\u00020\u001f2\u0016\u0010a\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030C\u0012\u0004\u0012\u00020A0@2\u0006\u0010X\u001a\u00020Y2\u0006\u0010h\u001a\u00020\u001f2\u0006\u0010j\u001a\u00020\u001f2\u0006\u0010[\u001a\u00020\u001f2\u0006\u0010k\u001a\u00020\u001f2\f\u0010l\u001a\b\u0012\u0004\u0012\u00020\u000f0g2\u0006\u0010m\u001a\u00020\u001fH\u0002J\f\u0010n\u001a\u00020\u001a*\u00020\u001aH\u0002J<\u0010o\u001a\u00020\u001f2\u0006\u0010<\u001a\u00020\u001a2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020J0\u001b2\u001c\u0010W\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030C\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0\u001b0@H\u0002J\u0086\u0001\u0010p\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010\u001b2\u0012\u0010q\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0\u001b0\u001b2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020J0\u001b2\u0010\u0010B\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030C0\u001b2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001b2\u0006\u0010<\u001a\u00020\u001a2\u0012\u0010I\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020J0\u00192\u0016\u0010K\u001a\u0012\u0012\u0004\u0012\u00020\u000f\u0012\b\u0012\u0006\u0012\u0002\b\u00030C0\u0019H\u0002J\u0086\u0001\u0010r\u001a\u00020s2\u0006\u0010t\u001a\u00020u2\u000e\u0010v\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010\u001b2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020J0\u001b2\u0012\u0010w\u001a\u000e\u0012\u0004\u0012\u00020J\u0012\u0004\u0012\u00020x0\u00192\u0016\u0010y\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030C\u0012\u0004\u0012\u00020x0\u00192\u0012\u0010I\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020J0\u00192\u0016\u0010K\u001a\u0012\u0012\u0004\u0012\u00020\u000f\u0012\b\u0012\u0006\u0012\u0002\b\u00030C0\u0019H\u0002JR\u0010z\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0\u001b0\u001b2\u001c\u0010W\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030C\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0\u001b0@2\u0010\u0010B\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030C0\u001b2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001bH\u0002JD\u0010{\u001a\b\u0012\u0004\u0012\u00020\u000f0g2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020J0\u001b2\u0010\u0010B\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030C0\u001b2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001b2\u0006\u0010m\u001a\u00020\u001fH\u0002J(\u0010m\u001a\u00020\u001f2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020J0\u001b2\u0010\u0010B\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030C0\u001bH\u0002J\u001e\u0010|\u001a\u00020\u000f2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020J0\u001b2\u0006\u0010j\u001a\u00020\u001fH\u0002JS\u0010}\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030C\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0\u001b0@2\u001c\u0010W\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030C\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0\u001b0@2\u0006\u0010<\u001a\u00020\u001a2\b\b\u0002\u0010~\u001a\u00020\u001fH\u0001¢\u0006\u0002\b\u007fJf\u0010\u0080\u0001\u001a\u00020s2\u0006\u0010<\u001a\u00020\u001a2\u0006\u0010P\u001a\u00020Q2\u0006\u0010O\u001a\u00020\u000f2\u0007\u0010\u0081\u0001\u001a\u00020\u000f2\u0006\u0010R\u001a\u00020S2\u0006\u0010~\u001a\u00020\u001f2\u001b\u0010\u0082\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u0083\u0001\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\u000f0\u0084\u00010\u00192\r\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020Q0\u0011H\u0002J\u008f\u0001\u0010\u0086\u0001\u001a\u0004\u0018\u00010u2\u0012\u0010q\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0\u001b0\u001b2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020J0\u001b2\u0010\u0010B\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030C0\u001b2\u0007\u0010\u0087\u0001\u001a\u00020\u000f2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001b2\u0006\u0010<\u001a\u00020\u001a2\u000e\u0010v\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010\u001b2\u0016\u0010a\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030C\u0012\u0004\u0012\u00020A0@2\u0007\u0010\u0088\u0001\u001a\u00020\u001fH\u0002J)\u0010\u0089\u0001\u001a\u00020\u001f2\u0007\u0010\u0087\u0001\u001a\u00020\u000f2\f\u0010l\u001a\b\u0012\u0004\u0012\u00020\u000f0g2\u0007\u0010\u008a\u0001\u001a\u00020\u000fH\u0002Ja\u0010\u008b\u0001\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030C\u0012\u0004\u0012\u00020x0\u00192\u0006\u0010t\u001a\u00020u2\u0010\u0010B\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030C0\u001b2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001b2\u0016\u0010a\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030C\u0012\u0004\u0012\u00020A0@2\u0006\u0010<\u001a\u00020\u001aH\u0002J!\u0010\u008c\u0001\u001a\u00020\u000f2\u0016\u0010a\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030C\u0012\u0004\u0012\u00020A0@H\u0002J\u008d\u0001\u0010\u008d\u0001\u001a\b\u0012\u0004\u0012\u00020>0\u001b2\u0006\u0010N\u001a\u00020\u000f2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020J0\u001b2\r\u0010\u008e\u0001\u001a\b\u0012\u0004\u0012\u00020Q0\u001b2\u0010\u0010B\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030C0\u001b2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001b2\u0014\u0010I\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020J\u0018\u00010\u00192\u0018\u0010K\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\b\u0012\u0006\u0012\u0002\b\u00030C\u0018\u00010\u00192\u0007\u0010\u008f\u0001\u001a\u00020\u001fH\u0002JI\u0010\u0090\u0001\u001a\u00020\u000f2\r\u0010\u008e\u0001\u001a\b\u0012\u0004\u0012\u00020Q0\u001b2\u0010\u0010B\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030C0\u001b2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001b2\u0007\u0010\u008a\u0001\u001a\u00020\u000f2\u0006\u0010j\u001a\u00020\u001fH\u0002J*\u0010\u0091\u0001\u001a\u00020\u000f2\u0006\u0010O\u001a\u00020\u000f2\u0006\u0010P\u001a\u00020Q2\u0006\u0010j\u001a\u00020\u001f2\u0007\u0010\u0081\u0001\u001a\u00020\u000fH\u0002J\u0019\u0010\u0091\u0001\u001a\u00020\u000f2\u0006\u0010O\u001a\u00020\u000f2\u0006\u0010P\u001a\u00020QH\u0002J\u0018\u0010\u0092\u0001\u001a\u00020\u000f2\r\u0010\u0093\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0gH\u0002J'\u0010\u0094\u0001\u001a\u00020\u000f2\r\u0010\u0095\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0g2\r\u0010\u0096\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0gH\u0002J<\u0010\u0097\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0g2\r\u0010\u0098\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0g2\r\u0010\u0099\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0g2\r\u0010\u009a\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0gH\u0002JG\u0010\u009b\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0g2\r\u0010\u009c\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0g2\u0007\u0010\u009d\u0001\u001a\u00020\u000f2\u0018\u0010\u009e\u0001\u001a\u0013\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020\u000f0g\u0018\u00010\u009f\u0001H\u0002¢\u0006\u0003\u0010 \u0001J5\u0010¡\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0g2\r\u0010¢\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0g2\r\u0010£\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0g2\u0006\u0010m\u001a\u00020\u001fH\u0002J#\u0010¤\u0001\u001a\u00020\u001f2\u0007\u0010¥\u0001\u001a\u00020\u001f2\t\u0010¦\u0001\u001a\u0004\u0018\u00010\u001fH\u0002¢\u0006\u0003\u0010§\u0001J3\u0010¨\u0001\u001a\u00020\u000f2\u0007\u0010©\u0001\u001a\u00020\u000f2\u0006\u0010O\u001a\u00020\u000f2\u0006\u0010P\u001a\u00020Q2\u0006\u0010j\u001a\u00020\u001f2\u0007\u0010\u0081\u0001\u001a\u00020\u000fH\u0002J&\u0010ª\u0001\u001a\b\u0012\u0004\u0012\u00020Q0\u001b2\r\u0010«\u0001\u001a\b\u0012\u0004\u0012\u00020Q0\u001b2\u0006\u0010O\u001a\u00020\u000fH\u0007J\t\u0010¬\u0001\u001a\u00020sH\u0002J\t\u0010\u00ad\u0001\u001a\u00020sH\u0002J\t\u0010®\u0001\u001a\u00020sH\u0002J\t\u0010¯\u0001\u001a\u00020sH\u0002J\t\u0010°\u0001\u001a\u00020sH\u0002J\t\u0010±\u0001\u001a\u00020sH\u0002J\t\u0010²\u0001\u001a\u00020sH\u0002J\t\u0010³\u0001\u001a\u00020sH\u0002J\t\u0010´\u0001\u001a\u00020sH\u0002J\t\u0010µ\u0001\u001a\u00020sH\u0002J\t\u0010¶\u0001\u001a\u00020sH\u0002J\u0012\u0010·\u0001\u001a\u00020'2\u0007\u0010¸\u0001\u001a\u00020\u000fH\u0007J0\u0010¹\u0001\u001a\u00020s2\u0013\u0010º\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020Q0\u00192\u0007\u0010»\u0001\u001a\u00020Q2\u0007\u0010¸\u0001\u001a\u00020\u000fH\u0002J5\u0010¼\u0001\u001a\u00020s2\u0013\u0010º\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020Q0\u00192\u0007\u0010¸\u0001\u001a\u00020\u000f2\f\b\u0002\u0010½\u0001\u001a\u0005\u0018\u00010¾\u0001H\u0002J'\u0010¿\u0001\u001a\u00020s2\u0013\u0010º\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020Q0\u00192\u0007\u0010¸\u0001\u001a\u00020\u000fH\u0002J\t\u0010À\u0001\u001a\u00020QH\u0002J\t\u0010Á\u0001\u001a\u00020.H\u0002J\u000b\u0010Â\u0001\u001a\u0004\u0018\u00010QH\u0002J\u000b\u0010Ã\u0001\u001a\u0004\u0018\u00010QH\u0002J!\u0010Ä\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001b2\u0010\u0010B\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030C0\u001bH\u0002J<\u0010Å\u0001\u001a\u0004\u0018\u00010Q2\n\u0010Æ\u0001\u001a\u0005\u0018\u00010Ç\u00012\u0006\u0010O\u001a\u00020\u000f2\u0007\u0010È\u0001\u001a\u00020\u001f2\f\b\u0002\u0010½\u0001\u001a\u0005\u0018\u00010¾\u0001H\u0000¢\u0006\u0003\bÉ\u0001J:\u0010Ê\u0001\u001a\u000b\u0012\u0004\u0012\u00020Q\u0018\u00010\u009f\u00012\n\u0010Æ\u0001\u001a\u0005\u0018\u00010Ç\u00012\u0006\u0010O\u001a\u00020\u000f2\f\b\u0002\u0010½\u0001\u001a\u0005\u0018\u00010¾\u0001H\u0002¢\u0006\u0003\u0010Ë\u0001J*\u0010Ì\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0\u00110\u001b2\u0013\u0010Í\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0\u001b0\u001bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u001b0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u00020'X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000208X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020:X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006Ò\u0001²\u0006\u000b\u0010Ó\u0001\u001a\u00020\u001fX\u008a\u0084\u0002"}, d2 = {"Landroidx/camera/camera2/adapter/SupportedSurfaceCombination;", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Landroid/content/Context;", "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "encoderProfilesProvider", "Landroidx/camera/core/impl/EncoderProfilesProvider;", "featureCombinationQuery", "Landroidx/camera/core/featuregroup/impl/FeatureCombinationQuery;", "<init>", "(Landroid/content/Context;Landroidx/camera/camera2/pipe/CameraMetadata;Landroidx/camera/core/impl/EncoderProfilesProvider;Landroidx/camera/core/featuregroup/impl/FeatureCombinationQuery;)V", "cameraId", _UrlKt.FRAGMENT_ENCODE_SET, "hardwareLevel", _UrlKt.FRAGMENT_ENCODE_SET, "concurrentSurfaceCombinations", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/SurfaceCombination;", "surfaceCombinations", "surfaceCombinationsStreamUseCase", "ultraHighSurfaceCombinations", "previewStabilizationSurfaceCombinations", "highSpeedSurfaceCombinations", "featureSettingsToSupportedCombinationsMap", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/adapter/SupportedSurfaceCombination$FeatureSettings;", _UrlKt.FRAGMENT_ENCODE_SET, "surfaceCombinations10Bit", "surfaceCombinationsUltraHdr", "isRawSupported", _UrlKt.FRAGMENT_ENCODE_SET, "isBurstCaptureSupported", "isConcurrentCameraModeSupported", "isStreamUseCaseSupported", "isUltraHighResolutionSensorSupported", "isPreviewStabilizationSupported", "isManualSensorSupported", "surfaceSizeDefinition", "Landroidx/camera/core/impl/SurfaceSizeDefinition;", "getSurfaceSizeDefinition$camera_camera2", "()Landroidx/camera/core/impl/SurfaceSizeDefinition;", "setSurfaceSizeDefinition$camera_camera2", "(Landroidx/camera/core/impl/SurfaceSizeDefinition;)V", "surfaceSizeDefinitionFormats", "streamConfigurationMapCompat", "Landroidx/camera/camera2/compat/StreamConfigurationMapCompat;", "extraSupportedSurfaceCombinationsContainer", "Landroidx/camera/camera2/compat/workaround/ExtraSupportedSurfaceCombinationsContainer;", "displayInfoManager", "Landroidx/camera/camera2/impl/DisplayInfoManager;", "resolutionCorrector", "Landroidx/camera/camera2/compat/workaround/ResolutionCorrector;", "targetAspectRatio", "Landroidx/camera/camera2/compat/workaround/TargetAspectRatio;", "dynamicRangeResolver", "Landroidx/camera/camera2/internal/DynamicRangeResolver;", "highSpeedResolver", "Landroidx/camera/camera2/internal/HighSpeedResolver;", "checkSupported", "featureSettings", "surfaceConfigList", "Landroidx/camera/core/impl/SurfaceConfig;", "dynamicRangesBySurfaceConfig", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/DynamicRange;", "newUseCaseConfigs", "Landroidx/camera/core/impl/UseCaseConfig;", "useCasesPriorityOrder", "createFeatureComboSessionConfig", "Landroidx/camera/core/impl/SessionConfig;", "useCasePriorityOrder", "getOrderedSupportedStreamUseCaseSurfaceConfigList", "surfaceConfigIndexAttachedSurfaceInfoMap", "Landroidx/camera/core/impl/AttachedSurfaceInfo;", "surfaceConfigIndexUseCaseConfigMap", "getSurfaceCombinationsByFeatureSettings", "transformSurfaceConfig", "cameraMode", "imageFormat", "size", "Landroid/util/Size;", "streamUseCase", "Landroidx/camera/core/impl/StreamUseCase;", "getSuggestedStreamSpecifications", "Landroidx/camera/core/impl/SurfaceStreamSpecQueryResult;", "attachedSurfaces", "newUseCaseConfigsSupportedSizeMap", "videoStabilization", "Landroidx/camera/core/impl/stabilization/VideoStabilization;", "hasVideoCapture", "isFeatureComboInvocation", "findMaxSupportedFrameRate", "resolveSpecsByCheckingMethod", "checkingMethod", "Landroidx/camera/camera2/adapter/SupportedSurfaceCombination$CheckingMethod;", "filteredNewUseCaseConfigsSupportedSizeMap", "resolvedDynamicRanges", "resolveSpecsBySettings", "getCheckingMethod", "dynamicRanges", _UrlKt.FRAGMENT_ENCODE_SET, "fps", "Landroid/util/Range;", "isUltraHdrOn", "createFeatureSettings", "isHighSpeedOn", "requiresFeatureComboQuery", "targetFpsRange", "isStrictFpsRequired", "validateSelf", "isUseCasesCombinationSupported", "getOrderedSurfaceConfigListForStreamUseCase", "allPossibleSizeArrangements", "populateStreamUseCaseIfSameSavedSizes", _UrlKt.FRAGMENT_ENCODE_SET, "bestSizesAndMaxFps", "Landroidx/camera/camera2/adapter/SupportedSurfaceCombination$BestSizesAndMaxFpsForConfigs;", "orderedSurfaceConfigListForStreamUseCase", "attachedSurfaceStreamSpecMap", "Landroidx/camera/core/impl/StreamSpec;", "suggestedStreamSpecMap", "getSupportedOutputSizesList", "getTargetFpsRange", "getMaxSupportedFpsFromAttachedSurfaces", "filterSupportedSizes", "forceUniqueMaxFpsFiltering", "filterSupportedSizes$camera_camera2", "populateReducedSizeListAndUniqueMaxFpsMap", "customMaxFps", "configSizeUniqueMaxFpsMap", "Landroidx/camera/core/impl/SurfaceConfig$ConfigSize;", _UrlKt.FRAGMENT_ENCODE_SET, "reducedSizeList", "findBestSizesAndFps", "existingSurfaceFrameRateCeiling", "findMaxFpsForAllSizes", "isConfigFrameRateAcceptable", "currentConfigFrameRateCeiling", "generateSuggestedStreamSpecMap", "getRequiredMaxBitDepth", "getSurfaceConfigList", "possibleSizeList", "checkViaFeatureComboQuery", "getCurrentConfigFrameRateCeiling", "getMaxFrameRate", "getRangeLength", "range", "getRangeDistance", "firstRange", "secondRange", "compareIntersectingRanges", "targetFps", "storedRange", "newRange", "getClosestSupportedDeviceFrameRate", "targetFrameRate", "maxFps", "availableFpsRanges", _UrlKt.FRAGMENT_ENCODE_SET, "(Landroid/util/Range;I[Landroid/util/Range;)Landroid/util/Range;", "getUpdatedTargetFrameRate", "newTargetFrameRate", "storedTargetFrameRate", "getAndValidateIsStrictFpsRequired", "newIsStrictFpsRequired", "storedIsStrictFpsRequired", "(ZLjava/lang/Boolean;)Z", "getCombinedMaximumFps", "combinedMaxFps", "applyResolutionSelectionOrderRelatedWorkarounds", "sizeList", "refreshPreviewSize", "checkCapabilities", "generateSupportedCombinationList", "generateUltraHighResolutionSupportedCombinationList", "generateConcurrentSupportedCombinationList", "generatePreviewStabilizationSupportedCombinationList", "generateHighSpeedSupportedCombinationList", "generate10BitSupportedCombinationList", "generateUltraHdrSupportedCombinationList", "generateStreamUseCaseSupportedCombinationList", "generateSurfaceSizeDefinition", "getUpdatedSurfaceSizeDefinitionByFormat", "format", "updateS720pOrS1440pSizeByFormat", "sizeMap", "targetSize", "updateMaximumSizeByFormat", "aspectRatio", "Landroid/util/Rational;", "updateUltraMaximumSizeByFormat", "getRecordSize", "getStreamConfigurationMapCompat", "getRecordSizeFromStreamConfigurationMapCompat", "getRecordSizeFromCamcorderProfile", "getUseCasesPriorityOrder", "getMaxOutputSizeByFormat", "map", "Landroid/hardware/camera2/params/StreamConfigurationMap;", "highResolutionIncluded", "getMaxOutputSizeByFormat$camera_camera2", "getOutputSizes", "(Landroid/hardware/camera2/params/StreamConfigurationMap;ILandroid/util/Rational;)[Landroid/util/Size;", "getAllPossibleSizeArrangements", "supportedOutputSizesList", "FeatureSettings", "BestSizesAndMaxFpsForConfigs", "CheckingMethod", "Companion", "camera-camera2", "isSupported"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSupportedSurfaceCombination.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SupportedSurfaceCombination.kt\nandroidx/camera/camera2/adapter/SupportedSurfaceCombination\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 6 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,2417:1\n1761#2,3:2418\n1869#2,2:2421\n1878#2,3:2423\n1761#2,3:2454\n1878#2,3:2457\n85#3,4:2426\n85#3,4:2430\n85#3,4:2434\n95#3,4:2438\n85#3,4:2442\n85#3,4:2446\n85#3,4:2450\n119#3,4:2460\n1#4:2464\n3829#5:2465\n4344#5,2:2466\n37#6:2468\n36#6,3:2469\n*S KotlinDebug\n*F\n+ 1 SupportedSurfaceCombination.kt\nandroidx/camera/camera2/adapter/SupportedSurfaceCombination\n*L\n182#1:2418,3\n198#1:2421,2\n214#1:2423,3\n972#1:2454,3\n1265#1:2457,3\n423#1:2426,4\n447#1:2430,4\n527#1:2434,4\n580#1:2438,4\n617#1:2442,4\n678#1:2446,4\n706#1:2450,4\n1582#1:2460,4\n2302#1:2465\n2302#1:2466,2\n2303#1:2468\n2303#1:2469,3\n*E\n"})
/* loaded from: classes3.dex */
public final class SupportedSurfaceCombination {
    public static final Companion Companion = null;
    private final String cameraId;
    private final CameraMetadata cameraMetadata;
    private final List<SurfaceCombination> concurrentSurfaceCombinations;
    private final DisplayInfoManager displayInfoManager;
    private final DynamicRangeResolver dynamicRangeResolver;
    private final EncoderProfilesProvider encoderProfilesProvider;
    private final ExtraSupportedSurfaceCombinationsContainer extraSupportedSurfaceCombinationsContainer;
    private final FeatureCombinationQuery featureCombinationQuery;
    private final Map<FeatureSettings, List<SurfaceCombination>> featureSettingsToSupportedCombinationsMap;
    private final int hardwareLevel;
    private final HighSpeedResolver highSpeedResolver;
    private final List<SurfaceCombination> highSpeedSurfaceCombinations;
    private boolean isBurstCaptureSupported;
    private final boolean isConcurrentCameraModeSupported;
    private boolean isManualSensorSupported;
    private boolean isPreviewStabilizationSupported;
    private boolean isRawSupported;
    private final boolean isStreamUseCaseSupported;
    private boolean isUltraHighResolutionSensorSupported;
    private final List<SurfaceCombination> previewStabilizationSurfaceCombinations;
    private final ResolutionCorrector resolutionCorrector;
    private final StreamConfigurationMapCompat streamConfigurationMapCompat;
    private final List<SurfaceCombination> surfaceCombinations;
    private final List<SurfaceCombination> surfaceCombinations10Bit;
    private final List<SurfaceCombination> surfaceCombinationsStreamUseCase;
    private final List<SurfaceCombination> surfaceCombinationsUltraHdr;
    public SurfaceSizeDefinition surfaceSizeDefinition;
    private final List<Integer> surfaceSizeDefinitionFormats;
    private final TargetAspectRatio targetAspectRatio;
    private final List<SurfaceCombination> ultraHighSurfaceCombinations;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001f\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0018\u0010\u0017R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0019\u001a\u0004\b\u001a\u0010\u0010R\u0017\u0010\b\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010\u0019\u001a\u0004\b\u001b\u0010\u0010R\u0017\u0010\t\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\t\u0010\u0019\u001a\u0004\b\u001c\u0010\u0010¨\u0006\u001d"}, d2 = {"Landroidx/camera/camera2/adapter/SupportedSurfaceCombination$BestSizesAndMaxFpsForConfigs;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/util/Size;", "bestSizes", "bestSizesForStreamUseCase", _UrlKt.FRAGMENT_ENCODE_SET, "maxFpsForBestSizes", "maxFpsForStreamUseCase", "maxFpsForAllSizes", "<init>", "(Ljava/util/List;Ljava/util/List;III)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/util/List;", "getBestSizes", "()Ljava/util/List;", "getBestSizesForStreamUseCase", "I", "getMaxFpsForBestSizes", "getMaxFpsForStreamUseCase", "getMaxFpsForAllSizes", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class BestSizesAndMaxFpsForConfigs {
        private final List<Size> bestSizes;
        private final List<Size> bestSizesForStreamUseCase;
        private final int maxFpsForAllSizes;
        private final int maxFpsForBestSizes;
        private final int maxFpsForStreamUseCase;

        public BestSizesAndMaxFpsForConfigs(List<Size> r1, List<Size> r2, int r3, int r4, int r5) {
            this.bestSizes = r1;
            this.bestSizesForStreamUseCase = r2;
            this.maxFpsForBestSizes = r3;
            this.maxFpsForStreamUseCase = r4;
            this.maxFpsForAllSizes = r5;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof BestSizesAndMaxFpsForConfigs) == true) goto L8;
            return false;
        L8:
            BestSizesAndMaxFpsForConfigs r52 = (BestSizesAndMaxFpsForConfigs) r5;
            if (Intrinsics.areEqual(this.bestSizes, r52.bestSizes) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.bestSizesForStreamUseCase, r52.bestSizesForStreamUseCase) == true) goto L15;
            return false;
        L15:
            if (this.maxFpsForBestSizes == r52.maxFpsForBestSizes) goto L18;
            return false;
        L18:
            if (this.maxFpsForStreamUseCase == r52.maxFpsForStreamUseCase) goto L21;
            return false;
        L21:
            if (this.maxFpsForAllSizes == r52.maxFpsForAllSizes) goto L23;
            return false;
        L23:
            return true;
        }

        public final List<Size> getBestSizes() {
            return this.bestSizes;
        }

        public final List<Size> getBestSizesForStreamUseCase() {
            return this.bestSizesForStreamUseCase;
        }

        public final int getMaxFpsForAllSizes() {
            return this.maxFpsForAllSizes;
        }

        public final int getMaxFpsForBestSizes() {
            return this.maxFpsForBestSizes;
        }

        public final int getMaxFpsForStreamUseCase() {
            return this.maxFpsForStreamUseCase;
        }

        public int hashCode() {
            int r0 = this.bestSizes.hashCode() * 31;
            List<Size> r1 = this.bestSizesForStreamUseCase;
            if (r1 != null) goto L5;
            int r12 = 0;
        L7:
            return ((((((r0 + r12) * 31) + Integer.hashCode(this.maxFpsForBestSizes)) * 31) + Integer.hashCode(this.maxFpsForStreamUseCase)) * 31) + Integer.hashCode(this.maxFpsForAllSizes);
        L5:
            r12 = r1.hashCode();
            goto L7
        }

        public String toString() {
            return "BestSizesAndMaxFpsForConfigs(bestSizes=" + this.bestSizes + ", bestSizesForStreamUseCase=" + this.bestSizesForStreamUseCase + ", maxFpsForBestSizes=" + this.maxFpsForBestSizes + ", maxFpsForStreamUseCase=" + this.maxFpsForStreamUseCase + ", maxFpsForAllSizes=" + this.maxFpsForAllSizes + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/camera/camera2/adapter/SupportedSurfaceCombination$CheckingMethod;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "WITHOUT_FEATURE_COMBO", "WITH_FEATURE_COMBO", "WITHOUT_FEATURE_COMBO_FIRST_AND_THEN_WITH_IT", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum CheckingMethod extends Enum<CheckingMethod> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ CheckingMethod[] $VALUES = null;
        public static final CheckingMethod WITHOUT_FEATURE_COMBO = null;
        public static final CheckingMethod WITHOUT_FEATURE_COMBO_FIRST_AND_THEN_WITH_IT = null;
        public static final CheckingMethod WITH_FEATURE_COMBO = null;

        private static final /* synthetic */ CheckingMethod[] $values() {
            return new CheckingMethod[]{WITHOUT_FEATURE_COMBO, WITH_FEATURE_COMBO, WITHOUT_FEATURE_COMBO_FIRST_AND_THEN_WITH_IT};
        }

        static {
            WITHOUT_FEATURE_COMBO = new CheckingMethod("WITHOUT_FEATURE_COMBO", 0);
            WITH_FEATURE_COMBO = new CheckingMethod("WITH_FEATURE_COMBO", 1);
            WITHOUT_FEATURE_COMBO_FIRST_AND_THEN_WITH_IT = new CheckingMethod("WITHOUT_FEATURE_COMBO_FIRST_AND_THEN_WITH_IT", 2);
            CheckingMethod[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        CheckingMethod(String r1, int r2) {
        }

        public static CheckingMethod valueOf(String r1) {
            return (CheckingMethod) Enum.valueOf(CheckingMethod.class, r1);
        }

        public static CheckingMethod[] values() {
            return (CheckingMethod[]) $VALUES.clone();
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J4\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u001c\u0010\t\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\nH\u0002¨\u0006\r"}, d2 = {"Landroidx/camera/camera2/adapter/SupportedSurfaceCombination$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isUltraHdrOn", _UrlKt.FRAGMENT_ENCODE_SET, "attachedSurfaces", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/AttachedSurfaceInfo;", "newUseCaseConfigsSupportedSizeMap", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/UseCaseConfig;", "Landroid/util/Size;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ boolean access$isUltraHdrOn(Companion r0, List r1, Map r2) {
            return r0.isUltraHdrOn(r1, r2);
        }

        private final boolean isUltraHdrOn(List<? extends AttachedSurfaceInfo> r3, Map<UseCaseConfig<?>, ? extends List<Size>> r4) {
            Iterator<? extends AttachedSurfaceInfo> r2 = r3.iterator();
        L4:
            if (r2.hasNext() == false) goto L8;
            if (r2.next().getImageFormat() != 4101) goto L4;
            return true;
        L8:
            Iterator<UseCaseConfig<?>> r22 = r4.keySet().iterator();
        L10:
            if (r22.hasNext() == false) goto L14;
            if (r22.next().getInputFormat() != 4101) goto L10;
            return true;
        L14:
            return false;
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0015\b\u0086\b\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005¢\u0006\u0004\b\u0010\u0010\u0011Jz\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u0005HÆ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0014HÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u001a\u001a\u00020\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u0018R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001c\u001a\u0004\b\u001e\u0010\u0018R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b \u0010!R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\"\u001a\u0004\b#\u0010$R\u0017\u0010\t\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\t\u0010\u001f\u001a\u0004\b\t\u0010!R\u0017\u0010\n\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\n\u0010\u001f\u001a\u0004\b\n\u0010!R\u0017\u0010\u000b\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u001f\u001a\u0004\b\u000b\u0010!R\u0017\u0010\f\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\f\u0010\u001f\u001a\u0004\b%\u0010!R\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010&\u001a\u0004\b'\u0010(R\u0017\u0010\u000f\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u001f\u001a\u0004\b\u000f\u0010!¨\u0006)"}, d2 = {"Landroidx/camera/camera2/adapter/SupportedSurfaceCombination$FeatureSettings;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "cameraMode", "requiredMaxBitDepth", _UrlKt.FRAGMENT_ENCODE_SET, "hasVideoCapture", "Landroidx/camera/core/impl/stabilization/VideoStabilization;", "videoStabilization", "isUltraHdrOn", "isHighSpeedOn", "isFeatureComboInvocation", "requiresFeatureComboQuery", "Landroid/util/Range;", "targetFpsRange", "isStrictFpsRequired", "<init>", "(IIZLandroidx/camera/core/impl/stabilization/VideoStabilization;ZZZZLandroid/util/Range;Z)V", "copy", "(IIZLandroidx/camera/core/impl/stabilization/VideoStabilization;ZZZZLandroid/util/Range;Z)Landroidx/camera/camera2/adapter/SupportedSurfaceCombination$FeatureSettings;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "I", "getCameraMode", "getRequiredMaxBitDepth", "Z", "getHasVideoCapture", "()Z", "Landroidx/camera/core/impl/stabilization/VideoStabilization;", "getVideoStabilization", "()Landroidx/camera/core/impl/stabilization/VideoStabilization;", "getRequiresFeatureComboQuery", "Landroid/util/Range;", "getTargetFpsRange", "()Landroid/util/Range;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class FeatureSettings {
        private final int cameraMode;
        private final boolean hasVideoCapture;
        private final boolean isFeatureComboInvocation;
        private final boolean isHighSpeedOn;
        private final boolean isStrictFpsRequired;
        private final boolean isUltraHdrOn;
        private final int requiredMaxBitDepth;
        private final boolean requiresFeatureComboQuery;
        private final Range<Integer> targetFpsRange;
        private final VideoStabilization videoStabilization;

        public FeatureSettings(int r1, int r2, boolean r3, VideoStabilization r4, boolean r5, boolean r6, boolean r7, boolean r8, Range<Integer> r9, boolean r10) {
            this.cameraMode = r1;
            this.requiredMaxBitDepth = r2;
            this.hasVideoCapture = r3;
            this.videoStabilization = r4;
            this.isUltraHdrOn = r5;
            this.isHighSpeedOn = r6;
            this.isFeatureComboInvocation = r7;
            this.requiresFeatureComboQuery = r8;
            this.targetFpsRange = r9;
            this.isStrictFpsRequired = r10;
        }

        public /* synthetic */ FeatureSettings(int r2, int r3, boolean r4, VideoStabilization r5, boolean r6, boolean r7, boolean r8, boolean r9, Range r10, boolean r11, int r12, DefaultConstructorMarker r13) {
            if ((r12 & 4) == 0) goto L6;
            r4 = false;
        L6:
            if ((r12 & 8) == 0) goto L9;
            r5 = VideoStabilization.UNSPECIFIED;
        L9:
            if ((r12 & 16) == 0) goto L12;
            r6 = false;
        L12:
            if ((r12 & 32) == 0) goto L15;
            r7 = false;
        L15:
            if ((r12 & 64) == 0) goto L18;
            r8 = false;
        L18:
            if ((r12 & 128) == 0) goto L21;
            r9 = false;
        L21:
            if ((r12 & 256) == 0) goto L24;
            r10 = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        L24:
            if ((r12 & 512) == 0) goto L26;
            r11 = false;
        L26:
            this(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
        }

        public static /* synthetic */ FeatureSettings copy$default(FeatureSettings r0, int r1, int r2, boolean r3, VideoStabilization r4, boolean r5, boolean r6, boolean r7, boolean r8, Range r9, boolean r10, int r11, Object r12) {
            if ((r11 & 1) == 0) goto L6;
            r1 = r0.cameraMode;
        L6:
            if ((r11 & 2) == 0) goto L9;
            r2 = r0.requiredMaxBitDepth;
        L9:
            if ((r11 & 4) == 0) goto L12;
            r3 = r0.hasVideoCapture;
        L12:
            if ((r11 & 8) == 0) goto L15;
            r4 = r0.videoStabilization;
        L15:
            if ((r11 & 16) == 0) goto L18;
            r5 = r0.isUltraHdrOn;
        L18:
            if ((r11 & 32) == 0) goto L21;
            r6 = r0.isHighSpeedOn;
        L21:
            if ((r11 & 64) == 0) goto L24;
            r7 = r0.isFeatureComboInvocation;
        L24:
            if ((r11 & 128) == 0) goto L27;
            r8 = r0.requiresFeatureComboQuery;
        L27:
            if ((r11 & 256) == 0) goto L30;
            r9 = r0.targetFpsRange;
        L30:
            if ((r11 & 512) == 0) goto L32;
            r10 = r0.isStrictFpsRequired;
        L32:
            Range r112 = r9;
            boolean r122 = r10;
            boolean r92 = r7;
            boolean r102 = r8;
            boolean r72 = r5;
            boolean r82 = r6;
            boolean r52 = r3;
            VideoStabilization r62 = r4;
            return r0.copy(r1, r2, r52, r62, r72, r82, r92, r102, r112, r122);
        }

        public final FeatureSettings copy(int r1, int r2, boolean r3, VideoStabilization r4, boolean r5, boolean r6, boolean r7, boolean r8, Range<Integer> r9, boolean r10) {
            return new FeatureSettings(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof FeatureSettings) == true) goto L8;
            return false;
        L8:
            FeatureSettings r52 = (FeatureSettings) r5;
            if (this.cameraMode == r52.cameraMode) goto L12;
            return false;
        L12:
            if (this.requiredMaxBitDepth == r52.requiredMaxBitDepth) goto L15;
            return false;
        L15:
            if (this.hasVideoCapture == r52.hasVideoCapture) goto L18;
            return false;
        L18:
            if (this.videoStabilization == r52.videoStabilization) goto L21;
            return false;
        L21:
            if (this.isUltraHdrOn == r52.isUltraHdrOn) goto L24;
            return false;
        L24:
            if (this.isHighSpeedOn == r52.isHighSpeedOn) goto L27;
            return false;
        L27:
            if (this.isFeatureComboInvocation == r52.isFeatureComboInvocation) goto L30;
            return false;
        L30:
            if (this.requiresFeatureComboQuery == r52.requiresFeatureComboQuery) goto L33;
            return false;
        L33:
            if (Intrinsics.areEqual(this.targetFpsRange, r52.targetFpsRange) == true) goto L36;
            return false;
        L36:
            if (this.isStrictFpsRequired == r52.isStrictFpsRequired) goto L38;
            return false;
        L38:
            return true;
        }

        public final int getCameraMode() {
            return this.cameraMode;
        }

        public final boolean getHasVideoCapture() {
            return this.hasVideoCapture;
        }

        public final int getRequiredMaxBitDepth() {
            return this.requiredMaxBitDepth;
        }

        public final boolean getRequiresFeatureComboQuery() {
            return this.requiresFeatureComboQuery;
        }

        public final Range<Integer> getTargetFpsRange() {
            return this.targetFpsRange;
        }

        public final VideoStabilization getVideoStabilization() {
            return this.videoStabilization;
        }

        public int hashCode() {
            return (((((((((((((((((Integer.hashCode(this.cameraMode) * 31) + Integer.hashCode(this.requiredMaxBitDepth)) * 31) + Boolean.hashCode(this.hasVideoCapture)) * 31) + this.videoStabilization.hashCode()) * 31) + Boolean.hashCode(this.isUltraHdrOn)) * 31) + Boolean.hashCode(this.isHighSpeedOn)) * 31) + Boolean.hashCode(this.isFeatureComboInvocation)) * 31) + Boolean.hashCode(this.requiresFeatureComboQuery)) * 31) + this.targetFpsRange.hashCode()) * 31) + Boolean.hashCode(this.isStrictFpsRequired);
        }

        public final boolean isFeatureComboInvocation() {
            return this.isFeatureComboInvocation;
        }

        public final boolean isHighSpeedOn() {
            return this.isHighSpeedOn;
        }

        public final boolean isStrictFpsRequired() {
            return this.isStrictFpsRequired;
        }

        public final boolean isUltraHdrOn() {
            return this.isUltraHdrOn;
        }

        public String toString() {
            return "FeatureSettings(cameraMode=" + this.cameraMode + ", requiredMaxBitDepth=" + this.requiredMaxBitDepth + ", hasVideoCapture=" + this.hasVideoCapture + ", videoStabilization=" + this.videoStabilization + ", isUltraHdrOn=" + this.isUltraHdrOn + ", isHighSpeedOn=" + this.isHighSpeedOn + ", isFeatureComboInvocation=" + this.isFeatureComboInvocation + ", requiresFeatureComboQuery=" + this.requiresFeatureComboQuery + ", targetFpsRange=" + this.targetFpsRange + ", isStrictFpsRequired=" + this.isStrictFpsRequired + ')';
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[CheckingMethod.values().length];
            r0[CheckingMethod.WITHOUT_FEATURE_COMBO.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L11:
            r0[CheckingMethod.WITH_FEATURE_COMBO.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L15:
            r0[CheckingMethod.WITHOUT_FEATURE_COMBO_FIRST_AND_THEN_WITH_IT.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L6:
            $EnumSwitchMapping$0 = r0;
        }
    }

    /* renamed from: $r8$lambda$AIKuh7GQDbRn08R1-pkBcnmI0Eg, reason: not valid java name */
    public static boolean m23$r8$lambda$AIKuh7GQDbRn08R1pkBcnmI0Eg(SupportedSurfaceCombination r0, FeatureSettings r1, List r2, Map r3, List r4, List r5) {
        return r0.checkSupported(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$ZAyBRcPqp063M8JGaa-rqhb9m9A, reason: not valid java name */
    public static boolean m24$r8$lambda$ZAyBRcPqp063M8JGaarqhb9m9A(SupportedSurfaceCombination r1, List r2) {
        return StreamUseCaseUtil.INSTANCE.areStreamUseCasesAvailableForSurfaceConfigs(r1.cameraMetadata, r2);
    }

    static {
        Companion = new Companion(null);
    }

    public SupportedSurfaceCombination(Context r1, CameraMetadata r2, EncoderProfilesProvider r3, FeatureCombinationQuery r4) {
        this.cameraMetadata = r2;
        this.encoderProfilesProvider = r3;
        this.featureCombinationQuery = r4;
        this.cameraId = r2.mo237getCameraDz_R5H8();
        Integer r32 = (Integer) r2.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        if (r32 == null) goto L5;
        int r33 = r32.intValue();
    L6:
        this.hardwareLevel = r33;
        this.concurrentSurfaceCombinations = new ArrayList();
        this.surfaceCombinations = new ArrayList();
        this.surfaceCombinationsStreamUseCase = new ArrayList();
        this.ultraHighSurfaceCombinations = new ArrayList();
        this.previewStabilizationSurfaceCombinations = new ArrayList();
        this.highSpeedSurfaceCombinations = new ArrayList();
        this.featureSettingsToSupportedCombinationsMap = new LinkedHashMap();
        this.surfaceCombinations10Bit = new ArrayList();
        this.surfaceCombinationsUltraHdr = new ArrayList();
        this.isPreviewStabilizationSupported = CameraMetadata.Companion.getSupportsPreviewStabilization(r2);
        this.surfaceSizeDefinitionFormats = new ArrayList();
        this.streamConfigurationMapCompat = getStreamConfigurationMapCompat();
        this.extraSupportedSurfaceCombinationsContainer = new ExtraSupportedSurfaceCombinationsContainer();
        this.displayInfoManager = DisplayInfoManager.Companion.getInstance(r1);
        this.resolutionCorrector = new ResolutionCorrector();
        this.targetAspectRatio = new TargetAspectRatio();
        DynamicRangeResolver r34 = new DynamicRangeResolver(r2);
        this.dynamicRangeResolver = r34;
        this.highSpeedResolver = new HighSpeedResolver(r2);
        checkCapabilities();
        generateSupportedCombinationList();
        if (this.isUltraHighResolutionSensorSupported == false) goto L9;
        generateUltraHighResolutionSupportedCombinationList();
    L9:
        boolean r12 = r1.getPackageManager().hasSystemFeature("android.hardware.camera.concurrent");
        this.isConcurrentCameraModeSupported = r12;
        if (r12 == false) goto L13;
        generateConcurrentSupportedCombinationList();
    L13:
        if (r34.is10BitDynamicRangeSupported() == false) goto L16;
        generate10BitSupportedCombinationList();
    L16:
        if (this.isPreviewStabilizationSupported == false) goto L18;
        generatePreviewStabilizationSupportedCombinationList();
    L18:
        boolean r13 = StreamUseCaseUtil.INSTANCE.isStreamUseCaseSupported(r2);
        this.isStreamUseCaseSupported = r13;
        if (r13 == false) goto L21;
        generateStreamUseCaseSupportedCombinationList();
    L21:
        generateSurfaceSizeDefinition();
        return;
    L5:
        r33 = 2;
        goto L6
    }

    public static final /* synthetic */ boolean access$isPreviewStabilizationSupported$p(SupportedSurfaceCombination r0) {
        return r0.isPreviewStabilizationSupported;
    }

    private final void checkCapabilities() {
        int[] r0 = (int[]) this.cameraMetadata.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
        if (r0 == null) goto L6;
        this.isRawSupported = ArraysKt.contains(r0, 3);
        this.isBurstCaptureSupported = ArraysKt.contains(r0, 6);
        this.isUltraHighResolutionSensorSupported = ArraysKt.contains(r0, 16);
        this.isManualSensorSupported = ArraysKt.contains(r0, 1);
        return;
    }

    public static /* synthetic */ boolean checkSupported$default(SupportedSurfaceCombination r6, FeatureSettings r7, List r8, Map r9, List r10, List r11, int r12, Object r13) {
        if ((r12 & 4) == 0) goto L5;
        r9 = MapsKt.emptyMap();
    L5:
        Map r3 = r9;
        if ((r12 & 8) == 0) goto L8;
        r10 = CollectionsKt.emptyList();
    L8:
        List r4 = r10;
        if ((r12 & 16) == 0) goto L12;
        r11 = CollectionsKt.emptyList();
    L12:
        return r6.checkSupported(r7, r8, r3, r4, r11);
    }

    private final Range<Integer> compareIntersectingRanges(Range<Integer> r10, Range<Integer> r11, Range<Integer> r12) {
        double r0 = getRangeLength(r11.intersect(r10));
        double r2 = getRangeLength(r12.intersect(r10));
        double r4 = r2 / getRangeLength(r12);
        double r9 = r0 / getRangeLength(r11);
        if (r2 <= r0) goto L10;
        if (r4 < 0.5d) goto L7;
    L24:
        return r12;
    L7:
        if (r4 >= r9) goto L24;
    L25:
        return r11;
    L10:
        if (r2 != r0) goto L21;
        if (r4 > r9) goto L24;
        if (r4 == r9) goto L17;
        return r11;
    L17:
        if (((Number) r12.getLower()).intValue() <= ((Number) r11.getLower()).intValue()) goto L25;
    L21:
        if (r9 >= 0.5d) goto L25;
        if (r4 <= r9) goto L25;
        goto L24
    }

    private final SessionConfig createFeatureComboSessionConfig(FeatureSettings r10, List<SurfaceConfig> r11, Map<SurfaceConfig, DynamicRange> r12, List<? extends UseCaseConfig<?>> r13, List<Integer> r14) {
        SessionConfig.ValidatingBuilder r0 = new SessionConfig.ValidatingBuilder();
        Iterator<T> r1 = r11.iterator();
        int r2 = 0;
    L4:
        if (r1.hasNext() == false) goto L25;
        Object r3 = r1.next();
        int r4 = r2 + 1;
        if (r2 >= 0) goto L8;
        CollectionsKt.throwIndexOverflow();
    L8:
        SurfaceConfig r32 = (SurfaceConfig) r3;
        Size r5 = r32.getResolution(getUpdatedSurfaceSizeDefinitionByFormat(r32.getImageFormat()));
        UseCaseConfig<?> r22 = r13.get(r14.get(r2).intValue());
        FeatureCombinationQuery.Companion r6 = FeatureCombinationQuery.Companion;
        DynamicRange r7 = r12.get(r32);
        Range<Integer> r8 = null;
        if (r7 == null) goto L22;
        SessionConfig.Builder r52 = r6.createSessionConfigBuilder(r22, r5, r7);
        Range<Integer> r62 = r10.getTargetFpsRange();
        if (Intrinsics.areEqual(r62, StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED) == true) goto L13;
        r8 = r62;
    L13:
        if (r8 != null) goto L15;
        r8 = FpsRangeFeature.DEFAULT_FPS_RANGE;
    L15:
        r52.setExpectedFrameRateRange(r8);
        if (r10.getVideoStabilization() != VideoStabilization.PREVIEW) goto L19;
        r52.setPreviewStabilization(2);
    L21:
        r0.add(r52.build());
        Preconditions.checkState(r0.isValid(), "Cannot create a combined SessionConfig for feature combo after adding " + r22 + " with " + r32 + " due to [" + r0.getInvalidReason() + "]; surfaceConfigList = " + r11 + ", featureSettings = " + r10 + ", newUseCaseConfigs = " + r13);
        r2 = r4;
        goto L4
    L19:
        if (r10.getVideoStabilization() != VideoStabilization.ON) goto L21;
        r52.setVideoStabilization(2);
        goto L21
    L22:
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L25:
        return r0.build();
    }

    private final FeatureSettings createFeatureSettings(int r12, boolean r13, Map<UseCaseConfig<?>, DynamicRange> r14, VideoStabilization r15, boolean r16, boolean r17, boolean r18, boolean r19, Range<Integer> r20, boolean r21) {
        return validateSelf(new FeatureSettings(r12, getRequiredMaxBitDepth(r14), r13, r15, r16, r17, r18, r19, r20, r21));
    }

    private final BestSizesAndMaxFpsForConfigs findBestSizesAndFps(List<? extends List<Size>> r25, List<? extends AttachedSurfaceInfo> r26, List<? extends UseCaseConfig<?>> r27, int r28, List<Integer> r29, FeatureSettings r30, List<SurfaceConfig> r31, Map<UseCaseConfig<?>, DynamicRange> r32, boolean r33) {
        Iterator<? extends List<Size>> r9 = r25.iterator();
        int r0 = Integer.MAX_VALUE;
        int r13 = Integer.MAX_VALUE;
        int r14 = Integer.MAX_VALUE;
        boolean r15 = false;
        boolean r16 = false;
        List<Size> r17 = null;
        List<Size> r18 = null;
    L4:
        if (r9.hasNext() == false) goto L63;
        List<Size> r1 = r9.next();
        LinkedHashMap r6 = new LinkedHashMap();
        LinkedHashMap r7 = new LinkedHashMap();
        int r10 = r0;
        BestSizesAndMaxFpsForConfigs r252 = null;
        List<SurfaceConfig> r8 = getSurfaceConfigList(r30.getCameraMode(), r26, r1, r27, r29, r6, r7, r30.getRequiresFeatureComboQuery());
        int r5 = getCurrentConfigFrameRateCeiling(r1, r27, r29, r28, r30.isHighSpeedOn());
        boolean r20 = isConfigFrameRateAcceptable(r28, r30.getTargetFpsRange(), r5);
        LinkedHashMap r4 = new LinkedHashMap();
        Iterator<T> r12 = r8.iterator();
        int r3 = 0;
    L7:
        if (r12.hasNext() == false) goto L23;
        Object r21 = r12.next();
        int r22 = r3 + 1;
        if (r3 >= 0) goto L11;
        CollectionsKt.throwIndexOverflow();
    L11:
        SurfaceConfig r122 = (SurfaceConfig) r21;
        AttachedSurfaceInfo r11 = r6.get(Integer.valueOf(r3));
        if (r11 == null) goto L17;
        DynamicRange r112 = r11.getDynamicRange();
        if (r112 == null) goto L17;
        DynamicRange r34 = r112;
    L20:
        r4.put(r122, r34);
        r3 = r22;
    L17:
        DynamicRange r35 = r32.get(r7.get(Integer.valueOf(r3)));
        if (r35 == null) goto L21;
        r34 = r35;
        goto L20
    L21:
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L23:
        Iterator<? extends List<Size>> r222 = r9;
        Lazy r19 = LazyKt.lazy(LazyThreadSafetyMode.NONE, new SupportedSurfaceCombination$$ExternalSyntheticLambda2(this, r30, r8, r4, r27, r29));
        if (r33 == false) goto L33;
        if (findBestSizesAndFps$lambda$2(r19) == false) goto L33;
        if (r13 == Integer.MAX_VALUE) goto L31;
        if (r13 >= r5) goto L33;
    L31:
        r13 = r5;
    L33:
        if (r15 == false) goto L35;
    L46:
        if (r31 == null) goto L61;
        if (r16 == true) goto L61;
        FeatureSettings r110 = r30;
        if (getOrderedSupportedStreamUseCaseSurfaceConfigList(r110, r8, r6, r7) == null) goto L61;
        if (r10 == Integer.MAX_VALUE) goto L54;
        if (r10 < r5) goto L54;
    L55:
        if (r20 == false) goto L61;
        if (r15 == false) goto L59;
        if (r33 == true) goto L59;
        r0 = r5;
        r18 = r1;
    L64:
        if (r17 != null) goto L67;
        return r252;
    L67:
        if (r110.isFeatureComboInvocation() == false) goto L76;
        if (Intrinsics.areEqual(r110.getTargetFpsRange(), StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED) == true) goto L76;
        if (r14 != Integer.MAX_VALUE) goto L73;
    L74:
        return r252;
    L73:
        if (r14 < ((Number) r110.getTargetFpsRange().getUpper()).intValue()) goto L74;
    L76:
        return new BestSizesAndMaxFpsForConfigs(r17, r18, r14, r0, r13);
    L59:
        r16 = true;
        r0 = r5;
        r18 = r1;
    L60:
        r9 = r222;
    L54:
        r10 = r5;
        r18 = r1;
    L61:
        r0 = r10;
        goto L60
    L35:
        if (findBestSizesAndFps$lambda$2(r19) == false) goto L46;
        if (r14 == Integer.MAX_VALUE) goto L40;
        if (r14 < r5) goto L40;
    L41:
        if (r20 == false) goto L46;
        if (r16 == false) goto L45;
        if (r33 == true) goto L45;
        r110 = r30;
        r14 = r5;
        r0 = r10;
        r17 = r1;
    L45:
        r15 = true;
        r14 = r5;
        r17 = r1;
    L40:
        r14 = r5;
        r17 = r1;
        goto L41
    L63:
        r110 = r30;
        r252 = null;
        goto L64
    }

    private static final boolean findBestSizesAndFps$lambda$2(Lazy<Boolean> r0) {
        return r0.getValue().booleanValue();
    }

    private final void generate10BitSupportedCombinationList() {
        this.surfaceCombinations10Bit.addAll(GuaranteedConfigurationsUtil.get10BitSupportedCombinationList());
    }

    private final void generateConcurrentSupportedCombinationList() {
        this.concurrentSurfaceCombinations.addAll(GuaranteedConfigurationsUtil.getConcurrentSupportedCombinationList());
    }

    private final void generateHighSpeedSupportedCombinationList() {
        if (this.highSpeedResolver.isHighSpeedSupported() == false) goto L10;
        this.highSpeedSurfaceCombinations.clear();
        Size r0 = this.highSpeedResolver.getMaxSize();
        if (r0 == null) goto L9;
        this.highSpeedSurfaceCombinations.addAll(GuaranteedConfigurationsUtil.generateHighSpeedSupportedCombinationList(r0, getUpdatedSurfaceSizeDefinitionByFormat(34)));
        return;
    L9:
        return;
    }

    private final void generatePreviewStabilizationSupportedCombinationList() {
        this.previewStabilizationSurfaceCombinations.addAll(GuaranteedConfigurationsUtil.getPreviewStabilizationSupportedCombinationList());
    }

    private final void generateStreamUseCaseSupportedCombinationList() {
        if (Build.VERSION.SDK_INT < 33) goto L6;
        this.surfaceCombinationsStreamUseCase.addAll(GuaranteedConfigurationsUtil.INSTANCE.getStreamUseCaseSupportedCombinationList());
        return;
    }

    private final Map<UseCaseConfig<?>, StreamSpec> generateSuggestedStreamSpecMap(BestSizesAndMaxFpsForConfigs r6, List<? extends UseCaseConfig<?>> r7, List<Integer> r8, Map<UseCaseConfig<?>, DynamicRange> r9, FeatureSettings r10) {
        LinkedHashMap r0 = new LinkedHashMap();
        Range<Integer> r1 = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        if (Intrinsics.areEqual(r10.getTargetFpsRange(), r1) == true) goto L18;
        if (r10.isHighSpeedOn() == false) goto L7;
        Range<Integer>[] r12 = this.highSpeedResolver.getFrameRateRangesFor(r6.getBestSizes());
    L8:
        Range<Integer> r5 = getClosestSupportedDeviceFrameRate(r10.getTargetFpsRange(), r6.getMaxFpsForBestSizes(), r12);
        if (r10.isFeatureComboInvocation() == true) goto L13;
        if (r10.isStrictFpsRequired() == true) goto L13;
    L14:
        r1 = r5;
    L20:
        Iterator<T> r52 = r7.iterator();
        int r72 = 0;
    L22:
        if (r52.hasNext() == false) goto L31;
        int r2 = r72 + 1;
        UseCaseConfig<?> r3 = (UseCaseConfig) r52.next();
        StreamSpec.Builder r73 = StreamSpec.builder(r6.getBestSizes().get(r8.indexOf(Integer.valueOf(r72)))).setSessionType(r10.isHighSpeedOn() ? 1 : 0);
        DynamicRange r4 = r9.get(r3);
        if (r4 == null) goto L29;
        StreamSpec.Builder r74 = r73.setDynamicRange(r4).setImplementationOptions(StreamUseCaseUtil.INSTANCE.getStreamSpecImplementationOptions(r3)).setZslDisabled(r10.getHasVideoCapture());
        if (Intrinsics.areEqual(r1, StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED) == true) goto L28;
        r74.setExpectedFrameRateRange(r1);
    L28:
        r0.put(r3, r74.build());
        r72 = r2;
        goto L22
    L29:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L31:
        return r0;
    L13:
        if (Intrinsics.areEqual(r5, r10.getTargetFpsRange()) == true) goto L14;
        StringBuilder r75 = new StringBuilder("Target FPS range ");
        r75.append(r10.getTargetFpsRange());
        r75.append(" is not supported. Max FPS supported by the calculated best combination: ");
        r75.append(r6.getMaxFpsForBestSizes());
        r75.append(". Calculated best FPS range for device: ");
        r75.append(r5);
        String r53 = Arrays.toString(r12);
        r75.append(". Device supported FPS ranges: ");
        r75.append(r53);
        r75.append('.');
        throw new IllegalArgumentException(r75.toString().toString());
    L7:
        r12 = (Range[]) this.cameraMetadata.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        goto L8
    L18:
        if (r10.isHighSpeedOn() == false) goto L20;
        Range<Integer>[] r13 = this.highSpeedResolver.getFrameRateRangesFor(r6.getBestSizes());
        r1 = getClosestSupportedDeviceFrameRate(HighSpeedResolver.Companion.getDEFAULT_FPS(), r6.getMaxFpsForBestSizes(), r13);
        goto L20
    }

    private final void generateSupportedCombinationList() {
        this.surfaceCombinations.addAll(GuaranteedConfigurationsUtil.generateSupportedCombinationList(this.hardwareLevel, this.isRawSupported, this.isBurstCaptureSupported));
        this.surfaceCombinations.addAll(this.extraSupportedSurfaceCombinationsContainer.get(this.cameraId));
    }

    private final void generateSurfaceSizeDefinition() {
        Size r3 = this.displayInfoManager.getPreviewSize();
        Size r5 = getRecordSize();
        setSurfaceSizeDefinition$camera_camera2(SurfaceSizeDefinition.create(SizeUtil.RESOLUTION_VGA, new LinkedHashMap(), r3, new LinkedHashMap(), r5, new LinkedHashMap(), new LinkedHashMap(), new LinkedHashMap(), new LinkedHashMap()));
    }

    private final void generateUltraHdrSupportedCombinationList() {
        this.surfaceCombinationsUltraHdr.addAll(GuaranteedConfigurationsUtil.getUltraHdrSupportedCombinationList());
    }

    private final void generateUltraHighResolutionSupportedCombinationList() {
        this.ultraHighSurfaceCombinations.addAll(GuaranteedConfigurationsUtil.getUltraHighResolutionSupportedCombinationList());
    }

    private final List<List<Size>> getAllPossibleSizeArrangements(List<? extends List<Size>> r13) {
        Iterator<? extends List<Size>> r12 = r13.iterator();
        int r1 = 1;
    L4:
        if (r12.hasNext() == false) goto L6;
        r1 = r1 * r12.next().size();
        goto L4
    L6:
        if (r1 == 0) goto L20;
        ArrayList r122 = new ArrayList();
        int r3 = 0;
    L8:
        if (r3 >= r1) goto L10;
        r122.add(new ArrayList());
        r3 = r3 + 1;
        goto L8
    L10:
        int r32 = r1 / r13.get(0).size();
        int r4 = r13.size();
        int r6 = r1;
        int r5 = 0;
    L11:
        if (r5 >= r4) goto L19;
        List<Size> r7 = r13.get(r5);
        int r8 = 0;
    L13:
        if (r8 >= r1) goto L16;
        ((List) r122.get(r8)).add(r7.get((r8 % r6) / r32));
        r8 = r8 + 1;
        goto L13
    L16:
        if (r5 >= (r13.size() - 1)) goto L18;
        r6 = r32;
        r32 = r32 / r13.get(r5 + 1).size();
    L18:
        r5 = r5 + 1;
        goto L11
    L19:
        return r122;
    L20:
        f$$ExternalSyntheticBUOutline1.m("Failed to find supported resolutions.");
        return null;
    }

    private final boolean getAndValidateIsStrictFpsRequired(boolean r1, Boolean r2) {
        if (r2 != null) goto L4;
    L8:
        return r1;
    L4:
        if (Intrinsics.areEqual(r2, Boolean.valueOf(r1)) == true) goto L8;
        Segment$$ExternalSyntheticBUOutline1.m("All isStrictFpsRequired should be the same");
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [boolean, int] */
    private final CheckingMethod getCheckingMethod(Collection<DynamicRange> r1, Range<Integer> r2, VideoStabilization r3, boolean r4, boolean r5) {
        if (r5 == false) goto L4;
        ?? r0 = r1.contains(DynamicRange.HLG_10_BIT);
        int r02 = r0;
        if (r2 == null) goto L14;
        Integer r12 = (Integer) r2.getUpper();
        r02 = r0;
        if (r12 == null) goto L14;
        r02 = r0;
        if (r12.intValue() != 60) goto L14;
        r02 = r0 + 1;
    L14:
        if (r3 == VideoStabilization.ON) goto L17;
        int r03 = r02;
        if (r3 == VideoStabilization.PREVIEW) goto L17;
    L18:
        if (r4 == false) goto L21;
        r03 = r03 + 1;
    L21:
        if (r03 > 1) goto L23;
        if (r03 != 1) goto L28;
        return CheckingMethod.WITHOUT_FEATURE_COMBO_FIRST_AND_THEN_WITH_IT;
    L28:
        return CheckingMethod.WITHOUT_FEATURE_COMBO;
    L23:
        return CheckingMethod.WITH_FEATURE_COMBO;
    L17:
        r03 = r02 + 1;
        goto L18
    L4:
        return CheckingMethod.WITHOUT_FEATURE_COMBO;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Range<Integer> getClosestSupportedDeviceFrameRate(Range<Integer> r8, int r9, Range<Integer>[] r10) {
        Range<Integer> r0 = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        if (Intrinsics.areEqual(r8, r0) == false) goto L5;
        return r0;
    L5:
        if (r10 != null) goto L7;
        return r0;
    L7:
        Range<T> r1 = new Range(Integer.valueOf(Math.min(((Number) r8.getLower()).intValue(), r9)), Integer.valueOf(Math.min(((Number) r8.getUpper()).intValue(), r9)));
        int r82 = r10.length;
        int r2 = 0;
        int r3 = 0;
    L8:
        if (r2 >= r82) goto L51;
        Range<Integer> r4 = r10[r2];
        if (r9 < ((Number) r4.getLower()).intValue()) goto L37;
        if (Intrinsics.areEqual(r0, StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED) == false) goto L16;
        r0 = r4;
    L16:
        if (Intrinsics.areEqual(r4, r1) == true) goto L38;
        int r5 = getRangeLength(r4.intersect(r1));     // Catch: IllegalArgumentException -> L24
        if (r3 != 0) goto L21;
        r0 = r4;
        r3 = r5;
        goto L37
    L21:
        if (r5 < r3) goto L37;
        r0 = compareIntersectingRanges(r1, r0, r4);     // Catch: IllegalArgumentException -> L24
        r3 = getRangeLength(r1.intersect(r0));     // Catch: IllegalArgumentException -> L24
    L24:
        if (r3 != 0) goto L37;
        if (getRangeDistance(r4, r1) >= getRangeDistance(r0, r1)) goto L30;
    L36:
        r0 = r4;
        goto L37
    L30:
        if (getRangeDistance(r4, r1) != getRangeDistance(r0, r1)) goto L37;
        if (((Number) r4.getLower()).intValue() > ((Number) r0.getUpper()).intValue()) goto L36;
        if (getRangeLength(r4) >= getRangeLength(r0)) goto L37;
    L38:
        return r4;
    L37:
        r2 = r2 + 1;
        goto L8
    L51:
        return r0;
    }

    private final int getCombinedMaximumFps(int r1, int r2, Size r3, boolean r4, int r5) {
        return Math.min(r1, getMaxFrameRate(r2, r3, r4, r5));
    }

    private final int getCurrentConfigFrameRateCeiling(List<Size> r8, List<? extends UseCaseConfig<?>> r9, List<Integer> r10, int r11, boolean r12) {
        Iterator<T> r82 = r8.iterator();
        int r0 = 0;
        int r2 = r11;
    L4:
        if (r82.hasNext() == false) goto L6;
        int r112 = r0 + 1;
        Size r4 = (Size) r82.next();
        UseCaseConfig<?> r02 = r9.get(r10.get(r0).intValue());
        r2 = getCombinedMaximumFps(r2, r02.getInputFormat(), r4, r12, r02.getCustomMaxFrameRate(r4));
        r0 = r112;
        goto L4
    L6:
        return r2;
    }

    private final int getMaxFrameRate(int r5, Size r6) {
        long r0 = getStreamConfigurationMapCompat().getOutputMinFrameDuration(r5, r6);
        if (r0 > 0) goto L14;
        if (this.isManualSensorSupported == false) goto L11;
        Camera2Logger r4 = Camera2Logger.INSTANCE;
        if (Logger.isWarnEnabled("CXCP") == false) goto L15;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "minFrameDuration: " + r0 + " is invalid for imageFormat = " + r5 + ", size = " + r6);
        return 0;
    L15:
        return 0;
    L11:
        return Integer.MAX_VALUE;
    L14:
        return (int) (1.0E9d / r0);
    }

    private final int getMaxFrameRate(int r1, Size r2, boolean r3, int r4) {
        if (r3 == true) goto L4;
        int r0 = getMaxFrameRate(r1, r2);
    L10:
        return Math.min(r4, r0);
    L4:
        if (r1 != 34) goto L6;
        r0 = this.highSpeedResolver.getMaxFrameRate(r2);
        goto L10
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return 0;
    }

    public static /* synthetic */ Size getMaxOutputSizeByFormat$camera_camera2$default(SupportedSurfaceCombination r0, StreamConfigurationMap r1, int r2, boolean r3, Rational r4, int r5, Object r6) {
        if ((r5 & 8) == 0) goto L6;
        r4 = null;
    L6:
        return r0.getMaxOutputSizeByFormat$camera_camera2(r1, r2, r3, r4);
    }

    private final int getMaxSupportedFpsFromAttachedSurfaces(List<? extends AttachedSurfaceInfo> r8, boolean r9) {
        Iterator<? extends AttachedSurfaceInfo> r82 = r8.iterator();
        int r2 = Integer.MAX_VALUE;
    L4:
        if (r82.hasNext() == false) goto L6;
        AttachedSurfaceInfo r0 = r82.next();
        r2 = getCombinedMaximumFps(r2, r0.getImageFormat(), r0.getSize(), r9, r0.getCustomMaxFrameRate());
        goto L4
    L6:
        return r2;
    }

    private final List<SurfaceConfig> getOrderedSupportedStreamUseCaseSurfaceConfigList(FeatureSettings r5, List<SurfaceConfig> r6, Map<Integer, AttachedSurfaceInfo> r7, Map<Integer, UseCaseConfig<?>> r8) {
        if (StreamUseCaseUtil.INSTANCE.shouldUseStreamUseCase(r5) == true) goto L5;
        return null;
    L5:
        Iterator<SurfaceCombination> r52 = this.surfaceCombinationsStreamUseCase.iterator();
    L7:
        if (r52.hasNext() == false) goto L15;
        List<SurfaceConfig> r1 = r52.next().getOrderedSupportedSurfaceConfigList(r6);
        if (r1 == null) goto L7;
        boolean r2 = StreamUseCaseUtil.INSTANCE.areCaptureTypesEligible(r7, r8, r1);
        Lazy r3 = LazyKt.lazy(new SupportedSurfaceCombination$$ExternalSyntheticLambda1(this, r1));
        if (r2 == false) goto L7;
        if (((Boolean) r3.getValue()).booleanValue() == false) goto L7;
        return r1;
    L15:
        return null;
    }

    private final List<SurfaceConfig> getOrderedSurfaceConfigListForStreamUseCase(List<? extends List<Size>> r11, List<? extends AttachedSurfaceInfo> r12, List<? extends UseCaseConfig<?>> r13, List<Integer> r14, FeatureSettings r15, Map<Integer, AttachedSurfaceInfo> r16, Map<Integer, UseCaseConfig<?>> r17) {
        Iterator<? extends List<Size>> r112 = r11.iterator();
        List<SurfaceConfig> r0 = null;
    L4:
        if (r112.hasNext() == false) goto L9;
        List<Size> r4 = r112.next();
        r0 = getOrderedSupportedStreamUseCaseSurfaceConfigList(r15, getSurfaceConfigList(r15.getCameraMode(), r12, r4, r13, r14, r16, r17, false), r16, r17);
        if (r0 != null) goto L7;
        r16.clear();
        r17.clear();
        goto L4
    L7:
        return r0;
    L9:
        return r0;
    }

    private final Size[] getOutputSizes(StreamConfigurationMap r5, int r6, Rational r7) {
        Result.Companion r0 = Result.Companion;     // Catch: Throwable -> L7
        if (r6 != 34) goto L10;
        if (r5 == null) goto L9;
        Size[] r52 = r5.getOutputSizes(SurfaceTexture.class);     // Catch: Throwable -> L7
    L12:
        Object r53 = Result.m2018constructorimpl(r52);     // Catch: Throwable -> L7
    L16:
        if (Result.m2024isFailureimpl(r53) == false) goto L18;
        r53 = null;
    L18:
        Size[] r54 = (Size[]) r53;
        if (r54 == null) goto L35;
        if (r7 == null) goto L29;
        ArrayList r4 = new ArrayList();
        int r62 = r54.length;
        int r1 = 0;
    L22:
        if (r1 >= r62) goto L36;
        Size r2 = r54[r1];
        if (AspectRatioUtil.hasMatchingAspectRatio(r2, r7) == false) goto L26;
        r4.add(r2);
    L26:
        r1 = r1 + 1;
        goto L22
    L36:
        return (Size[]) r4.toArray(new Size[0]);
    L29:
        return r54;
    L35:
        return null;
    L9:
        r52 = null;
        goto L12
    L10:
        if (r5 == null) goto L9;
        r52 = r5.getOutputSizes(r6);     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        Result.Companion r63 = Result.Companion;
        r53 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L16
    }

    private final int getRangeDistance(Range<Integer> r2, Range<Integer> r3) {
        if (r2.contains(r3.getUpper()) == false) goto L5;
    L12:
        f$$ExternalSyntheticBUOutline1.m("Ranges must not intersect");
        return 0;
    L5:
        if (r2.contains(r3.getLower()) == true) goto L12;
        if (((Number) r2.getLower()).intValue() <= ((Number) r3.getUpper()).intValue()) goto L11;
        return ((Number) r2.getLower()).intValue() - ((Number) r3.getUpper()).intValue();
    L11:
        return ((Number) r3.getLower()).intValue() - ((Number) r2.getUpper()).intValue();
    }

    private final int getRangeLength(Range<Integer> r1) {
        return (((Number) r1.getUpper()).intValue() - ((Number) r1.getLower()).intValue()) + 1;
    }

    private final Size getRecordSize() {
        Integer.parseInt(this.cameraId);     // Catch: NumberFormatException -> L10
        Size r0 = getRecordSizeFromCamcorderProfile();     // Catch: NumberFormatException -> L10
        if (r0 == null) goto L5;
        return r0;
    L5:
        Size r1 = getRecordSizeFromStreamConfigurationMapCompat();
        if (r1 == null) goto L9;
        return r1;
    L9:
        return SizeUtil.RESOLUTION_480P;
    }

    private final Size getRecordSizeFromCamcorderProfile() {
        Iterator r0 = CollectionsKt.listOf(new Integer[]{1, 13, 10, 8, 12, 6, 5, 4}).iterator();
    L4:
        if (r0.hasNext() == false) goto L13;
        int r1 = ((Number) r0.next()).intValue();
        if (this.encoderProfilesProvider.hasProfile(r1) == false) goto L4;
        EncoderProfilesProxy r12 = this.encoderProfilesProvider.getAll(r1);
        if (r12 == null) goto L4;
        if (r12.getVideoProfiles().isEmpty() == true) goto L4;
        return r12.getVideoProfiles().get(0).getResolution();
    L13:
        return null;
    }

    private final Size getRecordSizeFromStreamConfigurationMapCompat() {
        StreamConfigurationMap r7 = this.streamConfigurationMapCompat.toStreamConfigurationMap();
        Result.Companion r1 = Result.Companion;     // Catch: Throwable -> L6
        if (r7 == null) goto L8;
        Size[] r72 = r7.getOutputSizes(MediaRecorder.class);     // Catch: Throwable -> L6
    L9:
        Object r73 = Result.m2018constructorimpl(r72);     // Catch: Throwable -> L6
    L13:
        if (Result.m2024isFailureimpl(r73) == false) goto L15;
        r73 = null;
    L15:
        Size[] r74 = (Size[]) r73;
        if (r74 != null) goto L18;
        return null;
    L18:
        Arrays.sort(r74, new CompareSizesByArea(true));
        int r12 = r74.length;
        int r2 = 0;
    L19:
        if (r2 >= r12) goto L26;
        Size r3 = r74[r2];
        int r4 = r3.getWidth();
        Size r5 = SizeUtil.RESOLUTION_1080P;
        if (r4 > r5.getWidth()) goto L25;
        if (r3.getHeight() > r5.getHeight()) goto L25;
        return r3;
    L25:
        r2 = r2 + 1;
        goto L19
    L26:
        return null;
    L8:
        r72 = null;
    L6:
        th = move-exception;
        Result.Companion r13 = Result.Companion;
        r73 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L13
    }

    private final int getRequiredMaxBitDepth(Map<UseCaseConfig<?>, DynamicRange> r2) {
        Iterator<DynamicRange> r1 = r2.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L8;
        if (r1.next().getBitDepth() != 10) goto L4;
        return 10;
    L8:
        return 8;
    }

    private final StreamConfigurationMapCompat getStreamConfigurationMapCompat() {
        StreamConfigurationMap r0 = (StreamConfigurationMap) this.cameraMetadata.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (r0 != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Cannot retrieve SCALER_STREAM_CONFIGURATION_MAP");
        return null;
    L5:
        return new StreamConfigurationMapCompat(r0, new OutputSizesCorrector(this.cameraMetadata, r0));
    }

    private final List<List<Size>> getSupportedOutputSizesList(Map<UseCaseConfig<?>, ? extends List<Size>> r4, List<? extends UseCaseConfig<?>> r5, List<Integer> r6) {
        ArrayList r0 = new ArrayList();
        Iterator<Integer> r62 = r6.iterator();
    L4:
        if (r62.hasNext() == false) goto L6;
        int r1 = r62.next().intValue();
        r0.add(applyResolutionSelectionOrderRelatedWorkarounds(r4.get(r5.get(r1)), r5.get(r1).getInputFormat()));
        goto L4
    L6:
        return r0;
    }

    private final List<SurfaceCombination> getSurfaceCombinationsByFeatureSettings(FeatureSettings r5) {
        if (this.featureSettingsToSupportedCombinationsMap.containsKey(r5) == true) goto L5;
        List<SurfaceCombination> r0 = new ArrayList();
        if (r5.getRequiresFeatureComboQuery() == false) goto L10;
        r0.addAll(GuaranteedConfigurationsUtil.INSTANCE.getQueryableFcqCombinations$camera_camera2(this.cameraMetadata, r5.getVideoStabilization()));
    L41:
        this.featureSettingsToSupportedCombinationsMap.put(r5, r0);
        return r0;
    L10:
        if (r5.isUltraHdrOn() == false) goto L18;
        if (this.surfaceCombinationsUltraHdr.isEmpty() == false) goto L15;
        generateUltraHdrSupportedCombinationList();
    L15:
        if (r5.getCameraMode() != 0) goto L41;
        r0.addAll(this.surfaceCombinationsUltraHdr);
        goto L41
    L18:
        if (r5.isHighSpeedOn() == false) goto L24;
        if (this.highSpeedSurfaceCombinations.isEmpty() == false) goto L22;
        generateHighSpeedSupportedCombinationList();
    L22:
        r0.addAll(this.highSpeedSurfaceCombinations);
        goto L41
    L24:
        if (r5.getRequiredMaxBitDepth() != 8) goto L37;
        int r1 = r5.getCameraMode();
        if (r1 != 1) goto L28;
        r0 = this.concurrentSurfaceCombinations;
        goto L41
    L28:
        if (r1 != 2) goto L30;
        r0.addAll(this.ultraHighSurfaceCombinations);
        r0.addAll(this.surfaceCombinations);
        goto L41
    L30:
        if (r5.getVideoStabilization() != VideoStabilization.PREVIEW) goto L32;
        List<SurfaceCombination> r12 = this.previewStabilizationSurfaceCombinations;
    L33:
        r0.addAll(r12);
        goto L41
    L32:
        r12 = this.surfaceCombinations;
        goto L33
    L37:
        if (r5.getRequiredMaxBitDepth() != 10) goto L41;
        if (r5.getCameraMode() != 0) goto L41;
        r0.addAll(this.surfaceCombinations10Bit);
        goto L41
    L5:
        return this.featureSettingsToSupportedCombinationsMap.get(r5);
    }

    private final List<SurfaceConfig> getSurfaceConfigList(int r15, List<? extends AttachedSurfaceInfo> r16, List<Size> r17, List<? extends UseCaseConfig<?>> r18, List<Integer> r19, Map<Integer, AttachedSurfaceInfo> r20, Map<Integer, UseCaseConfig<?>> r21, boolean r22) {
        ArrayList r2 = new ArrayList();
        Iterator<? extends AttachedSurfaceInfo> r3 = r16.iterator();
    L4:
        if (r3.hasNext() == false) goto L8;
        AttachedSurfaceInfo r4 = r3.next();
        r2.add(r4.getSurfaceConfig());
        if (r20 == null) goto L4;
        r20.put(Integer.valueOf(r2.size() - 1), r4);
        goto L4
    L8:
        Iterator<T> r0 = r17.iterator();
        int r32 = 0;
    L10:
        if (r0.hasNext() == false) goto L20;
        int r42 = r32 + 1;
        Size r8 = (Size) r0.next();
        UseCaseConfig<?> r33 = r18.get(r19.get(r32).intValue());
        int r7 = r33.getInputFormat();
        StreamUseCase r12 = r33.getStreamUseCase();
        SurfaceConfig.Companion r6 = SurfaceConfig.Companion;
        SurfaceSizeDefinition r9 = getUpdatedSurfaceSizeDefinitionByFormat(r7);
        if (r22 == false) goto L15;
        SurfaceConfig.ConfigSource r10 = SurfaceConfig.ConfigSource.FEATURE_COMBINATION_TABLE;
    L16:
        r2.add(r6.transformSurfaceConfig(r7, r8, r9, r15, r10, r12));
        if (r21 == null) goto L19;
        r21.put(Integer.valueOf(r2.size() - 1), r33);
    L19:
        r32 = r42;
        goto L10
    L15:
        r10 = SurfaceConfig.ConfigSource.CAPTURE_SESSION_TABLES;
        goto L16
    L20:
        return r2;
    }

    private final Range<Integer> getTargetFpsRange(List<? extends AttachedSurfaceInfo> r3, List<? extends UseCaseConfig<?>> r4, List<Integer> r5, boolean r6) {
        Range<Integer> r0 = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        Iterator<? extends AttachedSurfaceInfo> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        r0 = getUpdatedTargetFrameRate(r32.next().getTargetFrameRate(), r0, r6);
        goto L4
    L6:
        Iterator<Integer> r33 = r5.iterator();
    L8:
        if (r33.hasNext() == false) goto L10;
        r0 = getUpdatedTargetFrameRate(r4.get(r33.next().intValue()).getTargetFrameRate(StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED), r0, r6);
        goto L8
    L10:
        return r0;
    }

    private final Range<Integer> getUpdatedTargetFrameRate(Range<Integer> r2, Range<Integer> r3, boolean r4) {
        Range<Integer> r1 = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        if (Intrinsics.areEqual(r3, r1) == false) goto L8;
        if (Intrinsics.areEqual(r2, r1) == false) goto L8;
        return r1;
    L8:
        if (Intrinsics.areEqual(r3, r1) == false) goto L11;
        return r2;
    L11:
        if (Intrinsics.areEqual(r2, r1) == true) goto L18;
        if (r4 == false) goto L20;
        Preconditions.checkState(Intrinsics.areEqual(r2, r3), "All targetFrameRate should be the same if strict fps is required");
        return r2;
    L20:
        return r3.intersect(r2);
    L18:
        return r3;
    }

    private final List<Integer> getUseCasesPriorityOrder(List<? extends UseCaseConfig<?>> r9) {
        ArrayList r8 = new ArrayList();
        ArrayList r0 = new ArrayList();
        Iterator<? extends UseCaseConfig<?>> r1 = r9.iterator();
    L4:
        if (r1.hasNext() == false) goto L8;
        int r2 = r1.next().getSurfaceOccupancyPriority(0);
        if (r0.contains(Integer.valueOf(r2)) == true) goto L4;
        r0.add(Integer.valueOf(r2));
        goto L4
    L8:
        CollectionsKt.sort(r0);
        CollectionsKt.reverse(r0);
        int r12 = r0.size();
        int r22 = 0;
    L9:
        if (r22 >= r12) goto L16;
        Object r4 = r0.get(r22);
        r22 = r22 + 1;
        int r42 = ((Number) r4).intValue();
        Iterator<? extends UseCaseConfig<?>> r5 = r9.iterator();
    L12:
        if (r5.hasNext() == false) goto L9;
        UseCaseConfig<?> r6 = r5.next();
        if (r42 != r6.getSurfaceOccupancyPriority(0)) goto L12;
        r8.add(Integer.valueOf(r9.indexOf(r6)));
        goto L12
    L16:
        return r8;
    }

    private final boolean isConfigFrameRateAcceptable(int r1, Range<Integer> r2, int r3) {
        if (Intrinsics.areEqual(r2, StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED) == true) goto L9;
        if (r3 < r1) goto L6;
        return true;
    L6:
        if (r3 >= ((Number) r2.getUpper()).intValue()) goto L12;
        return false;
    L12:
        return true;
    L9:
        return true;
    }

    private final boolean isStrictFpsRequired(List<? extends AttachedSurfaceInfo> r3, List<? extends UseCaseConfig<?>> r4) {
        Iterator<? extends AttachedSurfaceInfo> r32 = r3.iterator();
        Boolean r0 = null;
    L4:
        if (r32.hasNext() == false) goto L6;
        r0 = Boolean.valueOf(getAndValidateIsStrictFpsRequired(r32.next().isStrictFrameRateRequired(), r0));
        goto L4
    L6:
        Iterator<? extends UseCaseConfig<?>> r33 = r4.iterator();
    L8:
        if (r33.hasNext() == false) goto L10;
        r0 = Boolean.valueOf(getAndValidateIsStrictFpsRequired(r33.next().isStrictFrameRateRequired(), r0));
        goto L8
    L10:
        if (r0 != null) goto L12;
        return false;
    L12:
        return r0.booleanValue();
    }

    private final boolean isUseCasesCombinationSupported(FeatureSettings r12, List<? extends AttachedSurfaceInfo> r13, Map<UseCaseConfig<?>, ? extends List<Size>> r14) {
        ArrayList r2 = new ArrayList();
        Iterator<? extends AttachedSurfaceInfo> r132 = r13.iterator();
    L4:
        if (r132.hasNext() == false) goto L6;
        r2.add(r132.next().getSurfaceConfig());
        goto L4
    L6:
        CompareSizesByArea r133 = new CompareSizesByArea();
        Iterator<UseCaseConfig<?>> r0 = r14.keySet().iterator();
    L8:
        if (r0.hasNext() == false) goto L21;
        UseCaseConfig<?> r1 = r0.next();
        List<Size> r3 = r14.get(r1);
        if (r3 != null) goto L12;
    L15:
        boolean r5 = true;
    L16:
        if (r5 == true) goto L18;
        Size r6 = (Size) Collections.min(r3, r133);
        int r52 = r1.getInputFormat();
        r2.add(SurfaceConfig.Companion.transformSurfaceConfig(r52, r6, getUpdatedSurfaceSizeDefinitionByFormat(r52), r12.getCameraMode(), SurfaceConfig.ConfigSource.CAPTURE_SESSION_TABLES, r1.getStreamUseCase()));
        goto L8
    L18:
        HttpUrl$Builder$$ExternalSyntheticBUOutline0.m("No available output size is found for ", r1, 46);
        return false;
    L12:
        if (r3.isEmpty() == true) goto L15;
        r5 = false;
        goto L16
    L21:
        return checkSupported$default(this, r12, r2, null, null, null, 28, null);
    }

    private final void populateReducedSizeListAndUniqueMaxFpsMap(FeatureSettings r8, Size r9, int r10, int r11, StreamUseCase r12, boolean r13, Map<SurfaceConfig.ConfigSize, Set<Integer>> r14, List<Size> r15) {
        SurfaceConfig.Companion r0 = SurfaceConfig.Companion;
        SurfaceSizeDefinition r3 = getUpdatedSurfaceSizeDefinitionByFormat(r10);
        int r4 = r8.getCameraMode();
        if (r8.getRequiresFeatureComboQuery() == false) goto L6;
        SurfaceConfig.ConfigSource r1 = SurfaceConfig.ConfigSource.FEATURE_COMBINATION_TABLE;
    L7:
        SurfaceConfig.ConfigSize r92 = r0.transformSurfaceConfig(r10, r9, r3, r4, r1, r12).getConfigSize();
        Range<Integer> r102 = r8.getTargetFpsRange();
        Range<Integer> r122 = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        if (Intrinsics.areEqual(r102, r122) == false) goto L12;
        if (r13 == true) goto L12;
        int r7 = Integer.MAX_VALUE;
    L14:
        if (r8.isFeatureComboInvocation() == true) goto L16;
    L22:
        Set<Integer> r82 = r14.get(r92);
        if (r82 != null) goto L26;
        r82 = new LinkedHashSet();
        r14.put(r92, r82);
    L26:
        if (r82.contains(Integer.valueOf(r7)) == true) goto L30;
        r15.add(r9);
        r82.add(Integer.valueOf(r7));
        return;
    L30:
        return;
    L16:
        if (r92 != SurfaceConfig.ConfigSize.NOT_SUPPORT) goto L18;
        return;
    L18:
        if (Intrinsics.areEqual(r8.getTargetFpsRange(), r122) == true) goto L22;
        if (r7 >= ((Number) r8.getTargetFpsRange().getUpper()).intValue()) goto L22;
        return;
    L12:
        r7 = getMaxFrameRate(r10, r9, r8.isHighSpeedOn(), r11);
        goto L14
    L6:
        r1 = SurfaceConfig.ConfigSource.CAPTURE_SESSION_TABLES;
        goto L7
    }

    private final void populateStreamUseCaseIfSameSavedSizes(BestSizesAndMaxFpsForConfigs r3, List<SurfaceConfig> r4, List<? extends AttachedSurfaceInfo> r5, Map<AttachedSurfaceInfo, StreamSpec> r6, Map<UseCaseConfig<?>, StreamSpec> r7, Map<Integer, AttachedSurfaceInfo> r8, Map<Integer, UseCaseConfig<?>> r9) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (r3.getMaxFpsForBestSizes() == r3.getMaxFpsForStreamUseCase()) goto L6;
        return;
    L6:
        if (r3.getBestSizes().size() != r3.getBestSizesForStreamUseCase().size()) goto L27;
        List r32 = CollectionsKt.zip(r3.getBestSizes(), r3.getBestSizesForStreamUseCase());
        if ((r32 instanceof Collection) == true) goto L10;
    L12:
        Iterator r33 = r32.iterator();
    L14:
        if (r33.hasNext() == false) goto L18;
        Pair r0 = (Pair) r33.next();
        if (Intrinsics.areEqual(r0.getFirst(), r0.getSecond()) == true) goto L14;
        return;
    L18:
        StreamUseCaseUtil r42 = StreamUseCaseUtil.INSTANCE;
        if (r42.populateStreamUseCaseStreamSpecOptionWithInteropOverride(this.cameraMetadata, r5, r7, r6) == true) goto L28;
        r42.populateStreamUseCaseStreamSpecOptionWithSupportedSurfaceConfigs(r7, r6, r8, r9, r4);
        return;
    L28:
        return;
    L10:
        if (r32.isEmpty() == false) goto L12;
    }

    private final void refreshPreviewSize() {
        this.displayInfoManager.refreshPreviewSize();
        if (this.surfaceSizeDefinition != null) goto L6;
        generateSurfaceSizeDefinition();
        return;
    L6:
        Size r3 = this.displayInfoManager.getPreviewSize();
        setSurfaceSizeDefinition$camera_camera2(SurfaceSizeDefinition.create(getSurfaceSizeDefinition$camera_camera2().getAnalysisSize(), getSurfaceSizeDefinition$camera_camera2().getS720pSizeMap(), r3, getSurfaceSizeDefinition$camera_camera2().getS1440pSizeMap(), getSurfaceSizeDefinition$camera_camera2().getRecordSize(), getSurfaceSizeDefinition$camera_camera2().getMaximumSizeMap(), getSurfaceSizeDefinition$camera_camera2().getMaximum4x3SizeMap(), getSurfaceSizeDefinition$camera_camera2().getMaximum16x9SizeMap(), getSurfaceSizeDefinition$camera_camera2().getUltraMaximumSizeMap()));
    }

    private final SurfaceStreamSpecQueryResult resolveSpecsByCheckingMethod(CheckingMethod r24, FeatureSettings r25, List<? extends AttachedSurfaceInfo> r26, Map<UseCaseConfig<?>, ? extends List<Size>> r27, List<? extends UseCaseConfig<?>> r28, List<Integer> r29, Map<UseCaseConfig<?>, DynamicRange> r30, boolean r31) {
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L5;
        String r02 = Camera2Logger.access$getTRUNCATED_TAG$p();
        StringBuilder r2 = new StringBuilder("resolveSpecsByCheckingMethod: checkingMethod = ");
        CheckingMethod r3 = r24;
        r2.append(r3);
        Log.d(r02, r2.toString());
    L6:
        int r03 = WhenMappings.$EnumSwitchMapping$0[r3.ordinal()];
        if (r03 == 1) goto L36;
        if (r03 == 2) goto L24;
        if (r03 == 3) goto L37;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L37:
        return resolveSpecsBySettings(validateSelf(FeatureSettings.copy$default(r25, 0, 0, false, null, false, false, false, false, null, false, 895, null)), r26, r27, r28, r29, r30, r31);
    L15:
        e = move-exception;
        Camera2Logger r22 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L20;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Failed to find a supported combination without feature combo, trying again with feature combo", e);
    L20:
        return resolveSpecsBySettings(validateSelf(FeatureSettings.copy$default(r25, 0, 0, false, null, false, false, false, true, null, false, 895, null)), r26, r27, r28, r29, r30, r31);
    L24:
        if (r25.isFeatureComboInvocation() == true) goto L26;
    L32:
        Range<Integer> r04 = r25.getTargetFpsRange();
    L34:
        return resolveSpecsBySettings(validateSelf(FeatureSettings.copy$default(r25, 0, 0, false, null, false, false, false, true, r04, false, 639, null)), r26, r27, r28, r29, r30, r31);
    L26:
        if (r25.getTargetFpsRange() != StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED) goto L32;
        if (r25.getRequiresFeatureComboQuery() == false) goto L31;
        r04 = FpsRangeFeature.DEFAULT_FPS_RANGE;
        goto L34
    L31:
        r04 = r25.getTargetFpsRange();
        goto L34
    L36:
        return resolveSpecsBySettings(validateSelf(FeatureSettings.copy$default(r25, 0, 0, false, null, false, false, false, false, null, false, 895, null)), r26, r27, r28, r29, r30, r31);
    L5:
        r3 = r24;
        goto L6
    }

    private final SurfaceStreamSpecQueryResult resolveSpecsBySettings(FeatureSettings r17, List<? extends AttachedSurfaceInfo> r18, Map<UseCaseConfig<?>, ? extends List<Size>> r19, List<? extends UseCaseConfig<?>> r20, List<Integer> r21, Map<UseCaseConfig<?>, DynamicRange> r22, boolean r23) {
        Camera2Logger r1 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L6;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "resolveSpecsBySettings: featureSettings = " + r17);
    L6:
        if (r17.isFeatureComboInvocation() == true) goto L12;
        if (isUseCasesCombinationSupported(r17, r18, r19) == true) goto L12;
        throw new IllegalArgumentException(("No supported surface combination is found for camera device - Id : " + this.cameraId + ". May be attempting to bind too many use cases. Existing surfaces: " + r18 + ". New configs: " + r20 + ". GroupableFeature settings: " + r17 + '.').toString());
    L12:
        List<List<Size>> r12 = getSupportedOutputSizesList(filterSupportedSizes$camera_camera2(r19, r17, r23), r20, r21);
        LinkedHashMap r6 = new LinkedHashMap();
        LinkedHashMap r7 = new LinkedHashMap();
        if (r17.isHighSpeedOn() == false) goto L15;
        List<List<Size>> r13 = this.highSpeedResolver.getSizeArrangements(r12);
    L16:
        boolean r8 = StreamUseCaseUtil.INSTANCE.containsZslUseCase(r18, r20);
        if (this.isStreamUseCaseSupported == false) goto L23;
        if (r8 == true) goto L23;
        List<SurfaceConfig> r82 = getOrderedSurfaceConfigListForStreamUseCase(r13, r18, r20, r21, r17, r6, r7);
        LinkedHashMap r14 = r6;
        LinkedHashMap r15 = r7;
        if (Logger.isDebugEnabled("CXCP") == false) goto L22;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "orderedSurfaceConfigListForStreamUseCase = " + r82);
    L22:
        List<SurfaceConfig> r72 = r82;
        BestSizesAndMaxFpsForConfigs r16 = findBestSizesAndFps(r13, r18, r20, getMaxSupportedFpsFromAttachedSurfaces(r18, r17.isHighSpeedOn()), r21, r17, r72, r22, r23);
        if (r16 == null) goto L32;
        if (Logger.isDebugEnabled("CXCP") == false) goto L29;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "resolveSpecsBySettings: bestSizesAndFps = " + r16);
    L29:
        Map<UseCaseConfig<?>, StreamSpec> r5 = generateSuggestedStreamSpecMap(r16, r20, r21, r22, r17);
        LinkedHashMap r4 = new LinkedHashMap();
        populateStreamUseCaseIfSameSavedSizes(r16, r72, r18, r4, r5, r14, r15);
        return new SurfaceStreamSpecQueryResult(r5, r4, r16.getMaxFpsForAllSizes());
    L32:
        throw new IllegalArgumentException(("No supported surface combination is found for camera device - Id : " + this.cameraId + " and Hardware level: " + this.hardwareLevel + ". May be the specified resolution is too large and not supported. Existing surfaces: " + r18 + ". New configs: " + r20 + '.').toString());
    L23:
        r14 = r6;
        r15 = r7;
        r82 = null;
        goto L22
    L15:
        r13 = getAllPossibleSizeArrangements(r12);
        goto L16
    }

    private final void updateMaximumSizeByFormat(Map<Integer, Size> r3, int r4, Rational r5) {
        Size r2 = getMaxOutputSizeByFormat$camera_camera2(this.streamConfigurationMapCompat.toStreamConfigurationMap(), r4, true, r5);
        if (r2 == null) goto L6;
        r3.put(Integer.valueOf(r4), r2);
        return;
    }

    public static /* synthetic */ void updateMaximumSizeByFormat$default(SupportedSurfaceCombination r0, Map r1, int r2, Rational r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L5;
        r3 = null;
    L5:
        r0.updateMaximumSizeByFormat(r1, r2, r3);
    }

    private final void updateS720pOrS1440pSizeByFormat(Map<Integer, Size> r9, Size r10, int r11) {
        if (this.isConcurrentCameraModeSupported == true) goto L5;
        return;
    L5:
        Size r8 = getMaxOutputSizeByFormat$camera_camera2$default(this, this.streamConfigurationMapCompat.toStreamConfigurationMap(), r11, false, null, 8, null);
        Integer r112 = Integer.valueOf(r11);
        if (r8 == null) goto L9;
        r10 = (Size) Collections.min(CollectionsKt.listOf(new Size[]{r10, r8}), new CompareSizesByArea());
    L9:
        r9.put(r112, r10);
    }

    private final void updateUltraMaximumSizeByFormat(Map<Integer, Size> r9, int r10) {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.isUltraHighResolutionSensorSupported == false) goto L16;
        StreamConfigurationMap r2 = (StreamConfigurationMap) this.cameraMetadata.get(SupportedSurfaceCombination$$ExternalSyntheticApiModelOutline0.m());
        if (r2 == null) goto L17;
        Size r8 = getMaxOutputSizeByFormat$camera_camera2$default(this, r2, r10, true, null, 8, null);
        if (r8 == null) goto L15;
        r9.put(Integer.valueOf(r10), r8);
        return;
    L15:
        return;
    L17:
        return;
    }

    private final FeatureSettings validateSelf(FeatureSettings r6) {
        if (r6.getCameraMode() == 0) goto L10;
        if (r6.isUltraHdrOn() == false) goto L10;
        SavedStateHandleSupport$$ExternalSyntheticBUOutline0.m("Camera device Id is ", this.cameraId, ". Ultra HDR is not currently supported in ", CameraMode.toLabelString(r6.getCameraMode()), " camera mode.");
        return null;
    L10:
        if (r6.getCameraMode() == 0) goto L17;
        if (r6.getRequiredMaxBitDepth() != 10) goto L17;
        SavedStateHandleSupport$$ExternalSyntheticBUOutline0.m("Camera device Id is ", this.cameraId, ". 10 bit dynamic range is not currently supported in ", CameraMode.toLabelString(r6.getCameraMode()), " camera mode.");
        return null;
    L17:
        if (r6.getCameraMode() == 0) goto L24;
        if (r6.isFeatureComboInvocation() == false) goto L24;
        SavedStateHandleSupport$$ExternalSyntheticBUOutline0.m("Camera device Id is ", this.cameraId, ". feature combination is not currently supported in ", CameraMode.toLabelString(r6.getCameraMode()), " camera mode.");
        return null;
    L24:
        if (r6.isHighSpeedOn() == false) goto L31;
        if (r6.isFeatureComboInvocation() == false) goto L31;
        f$$ExternalSyntheticBUOutline1.m("High-speed session is not supported with feature combination");
        return null;
    L31:
        if (r6.isHighSpeedOn() == true) goto L33;
    L37:
        return r6;
    L33:
        if (this.highSpeedResolver.isHighSpeedSupported() == true) goto L37;
        f$$ExternalSyntheticBUOutline1.m("High-speed session is not supported on this device.");
        return null;
    }

    public final List<Size> applyResolutionSelectionOrderRelatedWorkarounds(List<Size> r6, int r7) {
        int r0 = this.targetAspectRatio.get(this.cameraMetadata, this.streamConfigurationMapCompat);
        if (r0 != 0) goto L5;
        Rational r2 = AspectRatioUtil.ASPECT_RATIO_4_3;
    L18:
        if (r2 != null) goto L20;
        List<Size> r62 = CollectionsKt.toMutableList(r6);
    L29:
        return this.resolutionCorrector.insertOrPrioritize(SurfaceConfig.Companion.getConfigType(r7), r62);
    L20:
        ArrayList r02 = new ArrayList();
        ArrayList r1 = new ArrayList();
        Iterator<Size> r63 = r6.iterator();
    L22:
        if (r63.hasNext() == false) goto L27;
        Size r3 = r63.next();
        if (AspectRatioUtil.hasMatchingAspectRatio(r3, r2) == true) goto L25;
        r1.add(r3);
        goto L22
    L25:
        r02.add(r3);
        goto L22
    L27:
        r1.addAll(0, r02);
        r62 = r1;
        goto L29
    L5:
        if (r0 == 1) goto L16;
        r2 = null;
        if (r0 != 2) goto L9;
        Size r03 = getUpdatedSurfaceSizeDefinitionByFormat(256).getMaximumSize(256);
        if (r03 == null) goto L18;
        r2 = new Rational(r03.getWidth(), r03.getHeight());
        goto L18
    L9:
        if (r0 == 3) goto L18;
        throw new AssertionError("Undefined targetAspectRatio: " + this.targetAspectRatio);
    L16:
        r2 = AspectRatioUtil.ASPECT_RATIO_16_9;
        goto L18
    }

    public final boolean checkSupported(FeatureSettings r4, List<SurfaceConfig> r5, Map<SurfaceConfig, DynamicRange> r6, List<? extends UseCaseConfig<?>> r7, List<Integer> r8) {
        List<SurfaceCombination> r0 = getSurfaceCombinationsByFeatureSettings(r4);
        boolean r2 = false;
        if ((r0 instanceof Collection) == true) goto L5;
    L7:
        Iterator<T> r02 = r0.iterator();
    L9:
        if (r02.hasNext() == false) goto L13;
        if (((SurfaceCombination) r02.next()).getOrderedSupportedSurfaceConfigList(r5) == null) goto L9;
        r2 = true;
    L13:
        if (r2 == true) goto L15;
    L21:
        return r2;
    L15:
        if (r4.getRequiresFeatureComboQuery() == false) goto L21;
        SessionConfig r42 = createFeatureComboSessionConfig(r4, r5, r6, r7, r8);
        boolean r3 = this.featureCombinationQuery.isSupported(r42);
        Iterator<T> r43 = r42.getSurfaces().iterator();
    L18:
        if (r43.hasNext() == false) goto L20;
        ((DeferrableSurface) r43.next()).close();
        goto L18
    L20:
        return r3;
    L5:
        if (r0.isEmpty() == false) goto L7;
        goto L7
    }

    public final Map<UseCaseConfig<?>, List<Size>> filterSupportedSizes$camera_camera2(Map<UseCaseConfig<?>, ? extends List<Size>> r14, FeatureSettings r15, boolean r16) {
        LinkedHashMap r0 = new LinkedHashMap();
        Iterator<UseCaseConfig<?>> r1 = r14.keySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L10;
        UseCaseConfig<?> r2 = r1.next();
        ArrayList r11 = new ArrayList();
        LinkedHashMap r10 = new LinkedHashMap();
        Iterator<Size> r12 = r14.get(r2).iterator();
    L7:
        if (r12.hasNext() == false) goto L9;
        Size r5 = r12.next();
        populateReducedSizeListAndUniqueMaxFpsMap(r15, r5, r2.getInputFormat(), r2.getCustomMaxFrameRate(r5), r2.getStreamUseCase(), r16, r10, r11);
        goto L7
    L9:
        r0.put(r2, r11);
        goto L4
    L10:
        return r0;
    }

    public final Size getMaxOutputSizeByFormat$camera_camera2(StreamConfigurationMap r3, int r4, boolean r5, Rational r6) {
        Size[] r2 = getOutputSizes(r3, r4, r6);
        Size[] r62 = null;
        if (r2 != null) goto L5;
    L18:
        return null;
    L5:
        if (r2.length == 0) goto L18;
        CompareSizesByArea r0 = new CompareSizesByArea();
        Size r22 = (Size) Collections.max(ArraysKt.asList(r2), r0);
        Size r1 = SizeUtil.RESOLUTION_ZERO;
        if (r5 == false) goto L17;
        if (r3 == null) goto L11;
        r62 = r3.getHighResolutionOutputSizes(r4);
    L11:
        if (r62 == null) goto L17;
        if (r62.length == 0) goto L17;
        r1 = (Size) Collections.max(ArraysKt.asList(r62), r0);
    L17:
        return (Size) Collections.max(CollectionsKt.listOf(new Size[]{r22, r1}), r0);
    }

    public final SurfaceStreamSpecQueryResult getSuggestedStreamSpecifications(int r17, List<? extends AttachedSurfaceInfo> r18, Map<UseCaseConfig<?>, ? extends List<Size>> r19, VideoStabilization r20, boolean r21, boolean r22, boolean r23) {
        refreshPreviewSize();
        boolean r6 = HighSpeedResolver.Companion.isHighSpeedOn(r18, r19.keySet());
        if (r6 == false) goto L5;
        Map<UseCaseConfig<?>, ? extends List<Size>> r12 = this.highSpeedResolver.filterCommonSupportedSizes(r19);
    L6:
        List<? extends UseCaseConfig<?>> r13 = CollectionsKt.toList(r12.keySet());
        List<Integer> r14 = getUseCasesPriorityOrder(r13);
        Map<UseCaseConfig<?>, DynamicRange> r3 = this.dynamicRangeResolver.resolveAndValidateDynamicRanges(r18, r13, r14);
        Camera2Logger r1 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L9;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "resolvedDynamicRanges = " + r3);
    L9:
        boolean r4 = Companion.access$isUltraHdrOn(Companion, r18, r12);
        if (r23 == false) goto L12;
        Pair r2 = TuplesKt.to(Boolean.FALSE, StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED);
    L13:
        boolean r10 = ((Boolean) r2.component1()).booleanValue();
        Range<Integer> r9 = (Range) r2.component2();
        if (r20 != VideoStabilization.PREVIEW) goto L16;
        boolean r24 = true;
    L18:
        if (Logger.isDebugEnabled("CXCP") == false) goto L20;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "getSuggestedStreamSpecifications: isPreviewStabilizationSupported = " + access$isPreviewStabilizationSupported$p(this) + ", isFeatureComboInvocation = " + r22);
    L20:
        if (r24 == false) goto L28;
        if (this.isPreviewStabilizationSupported == true) goto L28;
        if (r22 == false) goto L28;
        f$$ExternalSyntheticBUOutline1.m("Preview stabilization is not supported by the camera.");
        return null;
    L28:
        return resolveSpecsByCheckingMethod(getCheckingMethod(r3.values(), r9, r20, r4, r22), createFeatureSettings(r17, r21, r3, r20, r4, r6, r22, false, r9, r10), r18, r12, r13, r14, r3, r23);
    L16:
        r24 = false;
        goto L18
    L12:
        boolean r25 = isStrictFpsRequired(r18, r13);
        r2 = TuplesKt.to(Boolean.valueOf(r25), getTargetFpsRange(r18, r13, r14, r25));
        goto L13
    L5:
        r12 = r19;
        goto L6
    }

    public final SurfaceSizeDefinition getSurfaceSizeDefinition$camera_camera2() {
        SurfaceSizeDefinition r0 = this.surfaceSizeDefinition;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return null;
    }

    public final SurfaceSizeDefinition getUpdatedSurfaceSizeDefinitionByFormat(int r8) {
        if (this.surfaceSizeDefinitionFormats.contains(Integer.valueOf(r8)) == true) goto L5;
        updateS720pOrS1440pSizeByFormat(getSurfaceSizeDefinition$camera_camera2().getS720pSizeMap(), SizeUtil.RESOLUTION_720P, r8);
        updateS720pOrS1440pSizeByFormat(getSurfaceSizeDefinition$camera_camera2().getS1440pSizeMap(), SizeUtil.RESOLUTION_1440P, r8);
        SupportedSurfaceCombination r1 = this;
        updateMaximumSizeByFormat$default(r1, getSurfaceSizeDefinition$camera_camera2().getMaximumSizeMap(), r8, null, 4, null);
        r1.updateMaximumSizeByFormat(r1.getSurfaceSizeDefinition$camera_camera2().getMaximum4x3SizeMap(), r8, AspectRatioUtil.ASPECT_RATIO_4_3);
        r1.updateMaximumSizeByFormat(r1.getSurfaceSizeDefinition$camera_camera2().getMaximum16x9SizeMap(), r8, AspectRatioUtil.ASPECT_RATIO_16_9);
        r1.updateUltraMaximumSizeByFormat(r1.getSurfaceSizeDefinition$camera_camera2().getUltraMaximumSizeMap(), r8);
        r1.surfaceSizeDefinitionFormats.add(Integer.valueOf(r8));
    L7:
        return r1.getSurfaceSizeDefinition$camera_camera2();
    L5:
        r1 = this;
        goto L7
    }

    public final void setSurfaceSizeDefinition$camera_camera2(SurfaceSizeDefinition r1) {
        this.surfaceSizeDefinition = r1;
    }

    public final SurfaceConfig transformSurfaceConfig(int r8, int r9, Size r10, StreamUseCase r11) {
        return SurfaceConfig.Companion.transformSurfaceConfig(r9, r10, getUpdatedSurfaceSizeDefinitionByFormat(r9), r8, SurfaceConfig.ConfigSource.CAPTURE_SESSION_TABLES, r11);
    }
}
