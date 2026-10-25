package com.exteragram.messenger.debug;

import android.app.Activity;
import android.os.Build;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.util.SparseArray;
import com.exteragram.messenger.plugins.Plugin;
import com.exteragram.messenger.plugins.PluginsController;
import com.google.android.gms.cast.CastStatusCodes;
import java.io.File;
import java.io.RandomAccessFile;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.NotificationBadge;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ForegroundDetector;
import org.telegram.ui.LaunchActivity;

@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002cdB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0015\u0010\u0013J\u001f\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\tH\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\tH\u0002¢\u0006\u0004\b\u001d\u0010\u001cJ\u000f\u0010\u001f\u001a\u00020\u001eH\u0002¢\u0006\u0004\b\u001f\u0010 J+\u0010&\u001a\b\u0012\u0004\u0012\u00020\t0%2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!2\u0006\u0010$\u001a\u00020\u0016H\u0002¢\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u00020\tH\u0002¢\u0006\u0004\b(\u0010\u001cJ\u000f\u0010)\u001a\u00020\tH\u0002¢\u0006\u0004\b)\u0010\u001cJ\u0017\u0010,\u001a\u00020\t2\u0006\u0010+\u001a\u00020*H\u0002¢\u0006\u0004\b,\u0010-J\u0019\u0010/\u001a\u00020\t2\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b/\u00100J\u000f\u00101\u001a\u00020\tH\u0002¢\u0006\u0004\b1\u0010\u001cJ!\u00104\u001a\u00020\u001e2\u0010\u00103\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003020!H\u0002¢\u0006\u0004\b4\u00105J\u000f\u00106\u001a\u00020\tH\u0002¢\u0006\u0004\b6\u0010\u001cJ\u000f\u00107\u001a\u00020\tH\u0002¢\u0006\u0004\b7\u0010\u001cJ\u0015\u0010:\u001a\u00020\u00042\u0006\u00109\u001a\u000208¢\u0006\u0004\b:\u0010;J\r\u0010<\u001a\u00020\u0016¢\u0006\u0004\b<\u0010=J\u0015\u0010?\u001a\u00020\u00042\u0006\u0010>\u001a\u00020\u0016¢\u0006\u0004\b?\u0010@J\u000f\u0010A\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\bA\u0010\bJ\r\u0010B\u001a\u00020\r¢\u0006\u0004\bB\u0010CJ\u001b\u0010F\u001a\u00020\u00042\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00040D¢\u0006\u0004\bF\u0010GJ\u0015\u0010J\u001a\u00020\u00042\u0006\u0010I\u001a\u00020H¢\u0006\u0004\bJ\u0010KR\u001a\u0010L\u001a\b\u0012\u0004\u0012\u00020\t0!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bL\u0010MR\u001a\u0010N\u001a\b\u0012\u0004\u0012\u00020\t0!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bN\u0010MR\u001a\u0010O\u001a\b\u0012\u0004\u0012\u00020\t0!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bO\u0010MR\u001a\u0010P\u001a\b\u0012\u0004\u0012\u00020\t0!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bP\u0010MR\u0018\u0010R\u001a\u0004\u0018\u00010Q8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bR\u0010SR\u0016\u0010T\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bT\u0010UR\u0014\u0010V\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bV\u0010WR'\u0010]\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\t0X8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bY\u0010Z\u001a\u0004\b[\u0010\\R\u001b\u0010`\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b^\u0010Z\u001a\u0004\b_\u0010\bR\u0014\u0010b\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\ba\u0010\b¨\u0006e"}, d2 = {"Lcom/exteragram/messenger/debug/FreezeMonitor;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "init", "Ljava/io/File;", "copyReport", "()Ljava/io/File;", _UrlKt.FRAGMENT_ENCODE_SET, "text", "appendReport", "(Ljava/lang/String;)V", _UrlKt.FRAGMENT_ENCODE_SET, "durationMs", "showFreezeBulletin", "(J)V", "ms", "formatSeconds", "(J)Ljava/lang/String;", "bytes", "formatMb", _UrlKt.FRAGMENT_ENCODE_SET, NotificationBadge.NewHtcHomeBadger.COUNT, "total", "percent", "(II)Ljava/lang/String;", "appLine", "()Ljava/lang/String;", "heapLine", _UrlKt.FRAGMENT_ENCODE_SET, "gcStats", "()[J", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/StackTraceElement;", "trace", "limit", _UrlKt.FRAGMENT_ENCODE_SET, "frameNames", "([Ljava/lang/StackTraceElement;I)Ljava/util/List;", "screenName", "fragmentStack", "Ljava/lang/Thread;", "mainThread", "otherThreads", "(Ljava/lang/Thread;)Ljava/lang/String;", "delegate", "delegateName", "(Ljava/lang/Object;)Ljava/lang/String;", "observers", "Ljava/lang/Class;", "classes", "countInstances", "([Ljava/lang/Class;)[J", "liveObjects", "environment", _UrlKt.FRAGMENT_ENCODE_SET, "enabled", "setEnabled", "(Z)V", "getThresholdMs", "()I", "thresholdMs", "setThresholdMs", "(I)V", "getReport", "getReportsSize", "()J", "Lkotlin/Function0;", "onDone", "deleteReports", "(Lkotlin/jvm/functions/Function0;)V", "Landroid/app/Activity;", "activity", "shareReport", "(Landroid/app/Activity;)V", "APP_FRAME_PREFIXES", "[Ljava/lang/String;", "IDLE_TOP_FRAMES", "COUNTED_CLASSES", "GC_STATS", "Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;", "watchdog", "Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;", "freezes", "I", "reportLock", "Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "notificationNames$delegate", "Lkotlin/Lazy;", "getNotificationNames", "()Ljava/util/Map;", "notificationNames", "reportsDir$delegate", "getReportsDir", "reportsDir", "getReportFile", "reportFile", "Freeze", "Watchdog", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFreezeMonitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FreezeMonitor.kt\ncom/exteragram/messenger/debug/FreezeMonitor\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,512:1\n1#2:513\n14048#3,2:514\n13213#3,2:520\n3938#3:533\n4474#3,2:534\n14048#3,2:542\n1586#4:516\n1661#4,3:517\n1915#4,2:522\n1080#4:524\n1080#4:525\n777#4:526\n873#4,2:527\n1586#4:529\n1661#4,3:530\n1205#4,2:536\n1282#4,4:538\n*S KotlinDebug\n*F\n+ 1 FreezeMonitor.kt\ncom/exteragram/messenger/debug/FreezeMonitor\n*L\n157#1:514,2\n231#1:520,2\n99#1:533\n99#1:534,2\n135#1:542,2\n210#1:516\n210#1:517,3\n233#1:522,2\n281#1:524\n284#1:525\n316#1:526\n316#1:527,2\n317#1:529\n317#1:530,3\n100#1:536,2\n100#1:538,4\n*E\n"})
/* loaded from: classes4.dex */
public final class FreezeMonitor {
    private static final String[] APP_FRAME_PREFIXES = null;
    private static final String[] COUNTED_CLASSES = null;
    private static final String[] GC_STATS = null;
    private static final String[] IDLE_TOP_FRAMES = null;
    public static final FreezeMonitor INSTANCE = null;
    private static volatile int freezes;
    private static final Lazy notificationNames$delegate = null;
    private static final Object reportLock = null;
    private static final Lazy reportsDir$delegate = null;
    private static volatile Watchdog watchdog;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0019\b\u0002\u0018\u00002\u00020\u0001BÅ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u000f\u0012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u000f\u0012\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u000f\u0012\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u000f\u0012\u0018\u0010\u0013\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0014\u0012\u0004\u0012\u00020\u00050\u000f\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\r\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u000f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u000f¢\u0006\b\n\u0000\u001a\u0004\b'\u0010&R\u001d\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u000f¢\u0006\b\n\u0000\u001a\u0004\b(\u0010&R\u001d\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u000f¢\u0006\b\n\u0000\u001a\u0004\b)\u0010&R#\u0010\u0013\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0014\u0012\u0004\u0012\u00020\u00050\u000f¢\u0006\b\n\u0000\u001a\u0004\b*\u0010&R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b+\u0010 R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b,\u0010 ¨\u0006-"}, d2 = {"Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;", _UrlKt.FRAGMENT_ENCODE_SET, "durationMs", _UrlKt.FRAGMENT_ENCODE_SET, "thresholdMs", _UrlKt.FRAGMENT_ENCODE_SET, "samples", "maxGapMs", "screen", _UrlKt.FRAGMENT_ENCODE_SET, "fragmentStack", "gcBefore", _UrlKt.FRAGMENT_ENCODE_SET, "heapBefore", "states", _UrlKt.FRAGMENT_ENCODE_SET, "hot", "hotDepth", "topFrames", "stacks", _UrlKt.FRAGMENT_ENCODE_SET, "otherThreads", "observers", "<init>", "(JIIJLjava/lang/String;Ljava/lang/String;[JLjava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;)V", "getDurationMs", "()J", "getThresholdMs", "()I", "getSamples", "getMaxGapMs", "getScreen", "()Ljava/lang/String;", "getFragmentStack", "getGcBefore", "()[J", "getHeapBefore", "getStates", "()Ljava/util/Map;", "getHot", "getHotDepth", "getTopFrames", "getStacks", "getOtherThreads", "getObservers", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Freeze {
        private final long durationMs;
        private final String fragmentStack;
        private final long[] gcBefore;
        private final String heapBefore;
        private final Map<String, Integer> hot;
        private final Map<String, Integer> hotDepth;
        private final long maxGapMs;
        private final String observers;
        private final String otherThreads;
        private final int samples;
        private final String screen;
        private final Map<List<String>, Integer> stacks;
        private final Map<String, Integer> states;
        private final int thresholdMs;
        private final Map<String, Integer> topFrames;

        public Freeze(long r1, int r3, int r4, long r5, String r7, String r8, long[] r9, String r10, Map<String, Integer> r11, Map<String, Integer> r12, Map<String, Integer> r13, Map<String, Integer> r14, Map<List<String>, Integer> r15, String r16, String r17) {
            this.durationMs = r1;
            this.thresholdMs = r3;
            this.samples = r4;
            this.maxGapMs = r5;
            this.screen = r7;
            this.fragmentStack = r8;
            this.gcBefore = r9;
            this.heapBefore = r10;
            this.states = r11;
            this.hot = r12;
            this.hotDepth = r13;
            this.topFrames = r14;
            this.stacks = r15;
            this.otherThreads = r16;
            this.observers = r17;
        }

        public final long getDurationMs() {
            return this.durationMs;
        }

        public final String getFragmentStack() {
            return this.fragmentStack;
        }

        public final long[] getGcBefore() {
            return this.gcBefore;
        }

        public final String getHeapBefore() {
            return this.heapBefore;
        }

        public final Map<String, Integer> getHot() {
            return this.hot;
        }

        public final Map<String, Integer> getHotDepth() {
            return this.hotDepth;
        }

        public final long getMaxGapMs() {
            return this.maxGapMs;
        }

        public final String getObservers() {
            return this.observers;
        }

        public final String getOtherThreads() {
            return this.otherThreads;
        }

        public final int getSamples() {
            return this.samples;
        }

        public final String getScreen() {
            return this.screen;
        }

        public final Map<List<String>, Integer> getStacks() {
            return this.stacks;
        }

        public final Map<String, Integer> getStates() {
            return this.states;
        }

        public final int getThresholdMs() {
            return this.thresholdMs;
        }

        public final Map<String, Integer> getTopFrames() {
            return this.topFrames;
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\u0006\u0010\u0012\u001a\u00020\u0010J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\u001a\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0016H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;", "Lorg/telegram/ui/Components/ForegroundDetector$Listener;", "<init>", "()V", "queue", "Lorg/telegram/messenger/DispatchQueue;", "mainHandler", "Landroid/os/Handler;", "mainThread", "Ljava/lang/Thread;", "checkRunnable", "Ljava/lang/Runnable;", "running", _UrlKt.FRAGMENT_ENCODE_SET, "stopped", "onBecameForeground", _UrlKt.FRAGMENT_ENCODE_SET, "onBecameBackground", "stop", "updateRunning", "check", "sample", "Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;", "ping", "Ljava/util/concurrent/CountDownLatch;", "posted", _UrlKt.FRAGMENT_ENCODE_SET, "record", "freeze", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nFreezeMonitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FreezeMonitor.kt\ncom/exteragram/messenger/debug/FreezeMonitor$Watchdog\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,512:1\n1#2:513\n1924#3,3:514\n1080#3:517\n777#3:518\n873#3:519\n874#3:522\n1915#3,2:523\n1080#3:525\n1915#3,2:526\n1080#3:528\n1915#3:529\n1915#3,2:530\n1916#3:532\n13213#4,2:520\n*S KotlinDebug\n*F\n+ 1 FreezeMonitor.kt\ncom/exteragram/messenger/debug/FreezeMonitor$Watchdog\n*L\n437#1:514,3\n479#1:517\n485#1:518\n485#1:519\n485#1:522\n488#1:523,2\n490#1:525\n491#1:526,2\n493#1:528\n493#1:529\n495#1:530,2\n493#1:532\n485#1:520,2\n*E\n"})
    public static final class Watchdog implements ForegroundDetector.Listener {
        private final Runnable checkRunnable;
        private final Handler mainHandler;
        private final Thread mainThread;
        private final DispatchQueue queue;
        private volatile boolean running;
        private volatile boolean stopped;

        public static void $r8$lambda$7qpSQkuRdaiCfCudJ_cXY00_KCw(Freeze r3) {
            FreezeMonitor.access$showFreezeBulletin(FreezeMonitor.INSTANCE, r3.getDurationMs());
        }

        public static void $r8$lambda$S_pSjC2swheQoZFMbm_beXGVDIQ(CountDownLatch r0) {
            r0.countDown();
        }

        public static CharSequence $r8$lambda$h1v9OQjRKZY3Ae1g5HeNBHyC0Xg(Map.Entry r2) {
            return r2.getKey() + " " + r2.getValue();
        }

        public static void $r8$lambda$qrdnrFM8lNht_nQeViM9Po0aSSY(Watchdog r0) {
            r0.check();
        }

        public static void $r8$lambda$uyHhUaXyy6G60GDDS44GZP7uBiU(Watchdog r2) {
            r2.queue.cancelRunnable(r2.checkRunnable);
            if (r2.running == false) goto L6;
            r2.queue.postRunnable(r2.checkRunnable);
            return;
        }

        public Watchdog() {
            this.queue = new DispatchQueue("freezeMonitorQueue");
            this.mainHandler = new Handler(Looper.getMainLooper());
            this.mainThread = Looper.getMainLooper().getThread();
            this.checkRunnable = new FreezeMonitor$Watchdog$$ExternalSyntheticLambda1(this);
            ForegroundDetector r0 = ForegroundDetector.getInstance();
            if (r0 == null) goto L5;
            r0.addListener(this);
        L5:
            updateRunning();
        }

        private final void check() {
            if (this.running == false) goto L22;
            CountDownLatch r0 = new CountDownLatch(1);     // Catch: Exception -> L13
            long r1 = SystemClock.uptimeMillis();     // Catch: Exception -> L13
            if (this.mainHandler.post(new FreezeMonitor$Watchdog$$ExternalSyntheticLambda2(r0)) == false) goto L16;
            if (r0.await(100, TimeUnit.MILLISECONDS) == true) goto L16;
            Freeze r02 = sample(r0, r1);     // Catch: Exception -> L13
            if (r02 == null) goto L16;
            record(r02);     // Catch: Exception -> L13
        L16:
            if (this.running == false) goto L21;
            this.queue.postRunnable(this.checkRunnable, 200);
            return;
        L21:
            return;
        L13:
            e = move-exception;
            FileLog.e(e);
            goto L16
        }

        private final void record(Freeze r18) {
            FreezeMonitor r1 = FreezeMonitor.INSTANCE;
            long[] r2 = FreezeMonitor.access$gcStats(r1);
            FreezeMonitor.access$setFreezes$p(FreezeMonitor.access$getFreezes$p() + 1);
            int r3 = FreezeMonitor.access$getFreezes$p();
            int r5 = r18.getSamples();
            StringBuilder r6 = new StringBuilder();
            r6.append("\n=== Freeze #" + r3 + " at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date()) + ": ");
            long r7 = r18.getDurationMs();
            StringBuilder r32 = new StringBuilder("main thread blocked ");
            r32.append(r7);
            r32.append(" ms\n");
            r6.append(r32.toString());
            r6.append("threshold " + r18.getThresholdMs() + " ms, " + r5 + " samples every 20 ms from 100 ms\n");
            if (r18.getMaxGapMs() <= 2000) goto L5;
            r6.append("WARNING: the monitor thread itself stalled for " + r18.getMaxGapMs() + " ms, the system probably froze the whole process; this record may be bogus\n");
        L5:
            r6.append("screen at start: " + r18.getScreen() + "\n");
            r6.append("fragment stack " + r18.getFragmentStack() + "\n");
            r6.append("GC during freeze: blocking " + (r2[0] - r18.getGcBefore()[0]) + " (" + (r2[1] - r18.getGcBefore()[1]) + " ms), ");
            r6.append("all " + (r2[2] - r18.getGcBefore()[2]) + " (" + (r2[3] - r18.getGcBefore()[3]) + " ms)\n");
            String r22 = r18.getHeapBefore();
            StringBuilder r8 = new StringBuilder("heap before: ");
            r8.append(r22);
            r8.append("\n");
            r6.append(r8.toString());
            r6.append("heap after:  " + FreezeMonitor.access$heapLine(r1) + "\n");
            r6.append("main thread states: ");
            r6.append(CollectionsKt.joinToString$default(CollectionsKt.sortedWith(r18.getStates().entrySet(), new FreezeMonitor$Watchdog$record$$inlined$sortedByDescending$1()), ", ", null, null, 0, null, new FreezeMonitor$Watchdog$$ExternalSyntheticLambda3(), 30, null));
            r6.append('\n');
            if (r5 <= 0) goto L35;
            r6.append("\n-- hot app frames (share of samples, outermost first) --\n");
            Set<Map.Entry<String, Integer>> r23 = r18.getHot().entrySet();
            ArrayList r82 = new ArrayList();
            Iterator<T> r24 = r23.iterator();
        L9:
            if (r24.hasNext() == false) goto L19;
            Object r9 = r24.next();
            Map.Entry r10 = (Map.Entry) r9;
            if ((((Number) r10.getValue()).intValue() * 100) < (r5 * 5)) goto L9;
            String[] r11 = FreezeMonitor.access$getAPP_FRAME_PREFIXES$p();
            int r12 = r11.length;
            int r13 = 0;
        L13:
            if (r13 >= r12) goto L9;
            if (StringsKt.startsWith$default((String) r10.getKey(), r11[r13], false, 2, null) == true) goto L16;
            r13 = r13 + 1;
            goto L13
        L16:
            r82.add(r9);
            goto L9
        L19:
            Iterator r14 = CollectionsKt.take(CollectionsKt.sortedWith(r82, new FreezeMonitor$Watchdog$record$$inlined$thenByDescending$1(new FreezeMonitor$Watchdog$record$$inlined$compareByDescending$1(), r18)), 60).iterator();
        L21:
            if (r14.hasNext() == false) goto L23;
            Map.Entry r25 = (Map.Entry) r14.next();
            r6.append(FreezeMonitor.access$percent(FreezeMonitor.INSTANCE, ((Number) r25.getValue()).intValue(), r5) + "%  " + r25.getKey() + "\n");
            goto L21
        L23:
            r6.append("\n-- top of stack --\n");
            Iterator r15 = CollectionsKt.take(CollectionsKt.sortedWith(r18.getTopFrames().entrySet(), new FreezeMonitor$Watchdog$record$$inlined$sortedByDescending$2()), 10).iterator();
        L25:
            if (r15.hasNext() == false) goto L27;
            Map.Entry r26 = (Map.Entry) r15.next();
            r6.append(FreezeMonitor.access$percent(FreezeMonitor.INSTANCE, ((Number) r26.getValue()).intValue(), r5) + "%  " + r26.getKey() + "\n");
            goto L25
        L27:
            r6.append("\n-- most frequent stacks --\n");
            Iterator r16 = CollectionsKt.take(CollectionsKt.sortedWith(r18.getStacks().entrySet(), new FreezeMonitor$Watchdog$record$$inlined$sortedByDescending$3()), 5).iterator();
        L29:
            if (r16.hasNext() == false) goto L35;
            Map.Entry r27 = (Map.Entry) r16.next();
            List r33 = (List) r27.getKey();
            int r28 = ((Number) r27.getValue()).intValue();
            r6.append("[" + r28 + " samples, " + ((r28 * 100) / r5) + "%]\n");
            Iterator r29 = r33.iterator();
        L32:
            if (r29.hasNext() == false) goto L29;
            String r34 = (String) r29.next();
            r6.append("    at ");
            r6.append(r34);
            r6.append('\n');
        L35:
            if (r18.getOtherThreads() == null) goto L38;
            r6.append("\n-- other busy threads at the threshold --\n");
            r6.append(r18.getOtherThreads());
        L38:
            if (r18.getObservers() == null) goto L40;
            r6.append("\n-- NotificationCenter observers at the threshold --\n");
            r6.append(r18.getObservers());
        L40:
            r6.append('\n');
            FreezeMonitor r17 = FreezeMonitor.INSTANCE;
            r6.append(FreezeMonitor.access$environment(r17));
            r6.append("\n-- live objects (after GC) --\n");
            r6.append(FreezeMonitor.access$liveObjects(r17));
            FreezeMonitor.access$appendReport(r17, r6.toString());
            AndroidUtilities.runOnUIThread(new FreezeMonitor$Watchdog$$ExternalSyntheticLambda4(r18));
        }

        private final Freeze sample(CountDownLatch r31, long r32) {
            FreezeMonitor r1 = FreezeMonitor.INSTANCE;
            int r5 = r1.getThresholdMs();
            long[] r11 = FreezeMonitor.access$gcStats(r1);
            String r12 = FreezeMonitor.access$heapLine(r1);
            String r9 = FreezeMonitor.access$screenName(r1);
            String r10 = FreezeMonitor.access$fragmentStack(r1);
            HashMap r13 = new HashMap();
            HashMap r14 = new HashMap();
            HashMap r15 = new HashMap();
            HashMap r16 = new HashMap();
            HashMap r2 = new HashMap();
            long r6 = 0;
            long r162 = r32;
            int r8 = 0;
            String r18 = null;
            String r19 = null;
        L4:
            if (this.stopped == true) goto L5;
            long r21 = SystemClock.uptimeMillis();
            int r3 = r5;
            long r4 = Math.max(r6, r21 - r162);
            long r62 = r21 - r32;
            if (r62 >= 120000) goto L50;
            String r52 = this.mainThread.getState().name();
            long r26 = 20;
            List r33 = FreezeMonitor.access$frameNames(FreezeMonitor.INSTANCE, this.mainThread.getStackTrace(), 150);
            r8 = r8 + 1;
            Integer r42 = (Integer) r13.get(r52);
            if (r42 == null) goto L11;
            int r43 = r42.intValue();
        L12:
            r13.put(r52, Integer.valueOf(r43 + 1));
            if (r33.isEmpty() == true) goto L44;
            Object r53 = r33.get(0);
            Integer r63 = (Integer) r16.get(r33.get(0));
            if (r63 == null) goto L17;
            int r64 = r63.intValue();
        L18:
            r16.put(r53, Integer.valueOf(r64 + 1));
            List r34 = r33;
            List r54 = CollectionsKt.take(r34, 40);
            Integer r65 = (Integer) r2.get(r54);
            if (r65 == null) goto L21;
            int r66 = r65.intValue();
        L22:
            r2.put(r54, Integer.valueOf(r66 + 1));
            Iterator r55 = r34.iterator();
            int r67 = 0;
        L24:
            if (r55.hasNext() == false) goto L36;
            Object r7 = r55.next();
            int r163 = r67 + 1;
            if (r67 >= 0) goto L28;
            CollectionsKt.throwIndexOverflow();
        L28:
            String r72 = (String) r7;
            Integer r23 = (Integer) r15.get(r72);
            if (r23 == null) goto L32;
            int r232 = r23.intValue();
        L33:
            if (r67 <= r232) goto L35;
            r15.put(r72, Integer.valueOf(r67));
        L35:
            r67 = r163;
            goto L24
        L32:
            r232 = -1;
            goto L33
        L36:
            Iterator r35 = CollectionsKt.toHashSet(r34).iterator();
        L38:
            if (r35.hasNext() == false) goto L44;
            String r44 = (String) r35.next();
            Integer r56 = (Integer) r14.get(r44);
            if (r56 == null) goto L42;
            int r57 = r56.intValue();
        L43:
            r14.put(r44, Integer.valueOf(r57 + 1));
            goto L38
        L42:
            r57 = 0;
            goto L43
        L21:
            r66 = 0;
            goto L22
        L17:
            r64 = 0;
        L44:
            if (r18 != null) goto L49;
            int r36 = r3;
            if ((r62 + 20) < r36) goto L48;
            FreezeMonitor r45 = FreezeMonitor.INSTANCE;
            r18 = FreezeMonitor.access$otherThreads(r45, this.mainThread);
            r19 = FreezeMonitor.access$observers(r45);
        L48:
            int r68 = r8;
            if (r31.await(r26, TimeUnit.MILLISECONDS) == true) goto L53;
            r8 = r68;
            r6 = r4;
            r5 = r36;
            r162 = r21;
            goto L4
        L53:
            long r46 = SystemClock.uptimeMillis() - r32;
            if (r46 >= r36) goto L57;
            return null;
        L57:
            return new Freeze(r46, r36, r68, r4, r9, r10, r11, r12, r13, r14, r15, r16, r2, r18, r19);
        L49:
            r36 = r3;
            goto L48
        L11:
            r43 = 0;
            goto L12
        L50:
            r36 = r3;
            r26 = 20;
            goto L48
        L5:
            return null;
        }

        private final void updateRunning() {
            if (this.stopped == true) goto L9;
            ForegroundDetector r0 = ForegroundDetector.getInstance();
            if (r0 == null) goto L9;
            boolean r1 = true;
            if (r0.isForeground() != true) goto L9;
        L11:
            if (this.running != r1) goto L13;
            return;
        L13:
            this.running = r1;
            this.queue.postRunnable(new FreezeMonitor$Watchdog$$ExternalSyntheticLambda0(this));
            return;
        L9:
            r1 = false;
            goto L11
        }

        @Override // org.telegram.ui.Components.ForegroundDetector.Listener
        public void onBecameBackground() {
            updateRunning();
        }

        @Override // org.telegram.ui.Components.ForegroundDetector.Listener
        public void onBecameForeground() {
            updateRunning();
        }

        public final void stop() {
            ForegroundDetector r0 = ForegroundDetector.getInstance();
            if (r0 == null) goto L5;
            r0.removeListener(this);
        L5:
            this.stopped = true;
            this.running = false;
            this.queue.cleanupQueue();
            this.queue.recycle();
        }
    }

    /* renamed from: $r8$lambda$3IFPcz9IrhrqK662FxzsQxw-z70, reason: not valid java name */
    public static CharSequence m1240$r8$lambda$3IFPcz9IrhrqK662FxzsQxwz70(Map.Entry r2) {
        return r2.getKey() + " " + r2.getValue();
    }

    public static void $r8$lambda$RBr0NeeKCHpE5s3sEw4Cjr8pHLo(Function0 r6) {
        Object r0 = reportLock;
        monitor-enter(r0);
        File[] r1 = INSTANCE.getReportsDir().listFiles();     // Catch: Throwable -> L9
        if (r1 == null) goto L11;
        int r3 = r1.length;     // Catch: Throwable -> L9
        int r4 = 0;
    L7:
        if (r4 >= r3) goto L11;
        r1[r4].delete();     // Catch: Throwable -> L9
        r4 = r4 + 1;     // Catch: Throwable -> L9
    L11:
        freezes = 0;     // Catch: Throwable -> L9
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r0);
        AndroidUtilities.runOnUIThread(new FreezeMonitor$$ExternalSyntheticLambda6(r6));
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public static void $r8$lambda$WFJTKfhrB5bCkafatRIEgMdAoHY(Function0 r0) {
        r0.invoke();
    }

    public static File $r8$lambda$WNWcCznrmBRP0CYEU2ePZwl41b4() {
        return DebugFilesKt.debugFilesDir("freezereports");
    }

    public static CharSequence $r8$lambda$_4ZBAwpZNPPivCMaGPNiCgkjd9c(long[] r4, Class[] r5, int r6) {
        return StringsKt.padStart$default(String.valueOf(r4[r6]), 7, 0, 2, null) + "  " + r5[r6].getName();
    }

    public static void $r8$lambda$_T9zH3KzMoOoXwuKJLL2JlMLHKY(Activity r2) {
        FreezeMonitor r0 = INSTANCE;
        Result.Companion r1 = Result.Companion;     // Catch: Throwable -> L5
        Object r02 = Result.m2018constructorimpl(r0.copyReport());     // Catch: Throwable -> L5
    L7:
        AndroidUtilities.runOnUIThread(new FreezeMonitor$$ExternalSyntheticLambda4(r02, r2));
        return;
    L5:
        th = move-exception;
        Result.Companion r12 = Result.Companion;
        r02 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L7
    }

    public static CharSequence $r8$lambda$_VIBI8BiQDyqSUoDdECA0ceA6vk(BaseFragment r0) {
        return r0.getClass().getSimpleName();
    }

    public static void $r8$lambda$iH7wEvOi8j8d6bXeHFvOgBDAzTw(LaunchActivity r1) {
        INSTANCE.shareReport(r1);
    }

    /* renamed from: $r8$lambda$pL8L44MmmW1-BoAP12TaTM5KrvU, reason: not valid java name */
    public static void m1241$r8$lambda$pL8L44MmmW1BoAP12TaTM5KrvU(Object r3, Activity r4) {
        if (Result.m2025isSuccessimpl(r3) == false) goto L5;
        DebugFilesKt.shareDebugFile(r4, (File) r3, "text/plain", "Share freeze report");
    L5:
        Throwable r32 = Result.m2021exceptionOrNullimpl(r3);
        if (r32 == null) goto L9;
        FileLog.e(r32);
        BulletinFactory.global().createSimpleBulletin(R.raw.error, "Freeze report failed: " + r32).show();
        return;
    }

    /* renamed from: $r8$lambda$sZJ7txUfGJHOdvGde6glWf-p5qw, reason: not valid java name */
    public static CharSequence m1242$r8$lambda$sZJ7txUfGJHOdvGde6glWfp5qw(Pair r2) {
        return r2.getSecond() + " " + r2.getFirst();
    }

    public static Map $r8$lambda$tMil1XBAiJwy7TGwpE9xl4xRcZc() {
        Field[] r0 = NotificationCenter.class.getFields();
        ArrayList r1 = new ArrayList();
        int r2 = r0.length;
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L10;
        Field r5 = r0[r4];
        if (Modifier.isStatic(r5.getModifiers()) == false) goto L9;
        if (Intrinsics.areEqual(r5.getType(), Integer.TYPE) == false) goto L9;
        r1.add(r5);
    L9:
        r4 = r4 + 1;
        goto L3
    L10:
        LinkedHashMap r22 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(r1, 10)), 16));
        int r02 = r1.size();
    L11:
        if (r3 >= r02) goto L13;
        Object r42 = r1.get(r3);
        r3 = r3 + 1;
        Field r43 = (Field) r42;
        Pair r44 = TuplesKt.to(Integer.valueOf(r43.getInt(null)), r43.getName());
        r22.put(r44.getFirst(), r44.getSecond());
        goto L11
    L13:
        return r22;
    }

    static {
        INSTANCE = new FreezeMonitor();
        APP_FRAME_PREFIXES = new String[]{"org.telegram.", "com.exteragram.", "com.chaquo.", "dev.exterahook."};
        IDLE_TOP_FRAMES = new String[]{"android.os.MessageQueue.nativePollOnce", "java.lang.Object.wait", "java.lang.Thread.sleep", "sun.misc.Unsafe.park", "jdk.internal.misc.Unsafe.park", "java.net.", "libcore.io.Linux.", "android.system.Os.", "dalvik.system.VMStack.getThreadStackTrace"};
        COUNTED_CLASSES = new String[]{"org.telegram.ui.LaunchActivity", "org.telegram.ui.ActionBar.BaseFragment", "org.telegram.ui.ChatActivity", "org.telegram.ui.DialogsActivity", "org.telegram.ui.ProfileActivity", "org.telegram.ui.Components.ChatActivityEnterView", "org.telegram.ui.Components.EmojiView", "org.telegram.ui.Cells.ChatMessageCell", "org.telegram.messenger.ImageReceiver", "org.telegram.ui.Components.RLottieDrawable", "org.telegram.ui.Components.AnimatedFileDrawable", "org.telegram.ui.Components.AnimatedEmojiDrawable", "android.graphics.Bitmap"};
        GC_STATS = new String[]{"art.gc.blocking-gc-count", "art.gc.blocking-gc-time", "art.gc.gc-count", "art.gc.gc-time"};
        reportLock = new Object();
        notificationNames$delegate = LazyKt.lazy(new FreezeMonitor$$ExternalSyntheticLambda0());
        reportsDir$delegate = LazyKt.lazy(new FreezeMonitor$$ExternalSyntheticLambda1());
    }

    private FreezeMonitor() {
    }

    public static final /* synthetic */ void access$appendReport(FreezeMonitor r0, String r1) {
        r0.appendReport(r1);
    }

    public static final /* synthetic */ String access$environment(FreezeMonitor r0) {
        return r0.environment();
    }

    public static final /* synthetic */ String access$fragmentStack(FreezeMonitor r0) {
        return r0.fragmentStack();
    }

    public static final /* synthetic */ List access$frameNames(FreezeMonitor r0, StackTraceElement[] r1, int r2) {
        return r0.frameNames(r1, r2);
    }

    public static final /* synthetic */ long[] access$gcStats(FreezeMonitor r0) {
        return r0.gcStats();
    }

    public static final /* synthetic */ String[] access$getAPP_FRAME_PREFIXES$p() {
        return APP_FRAME_PREFIXES;
    }

    public static final /* synthetic */ int access$getFreezes$p() {
        return freezes;
    }

    public static final /* synthetic */ String access$heapLine(FreezeMonitor r0) {
        return r0.heapLine();
    }

    public static final /* synthetic */ String access$liveObjects(FreezeMonitor r0) {
        return r0.liveObjects();
    }

    public static final /* synthetic */ String access$observers(FreezeMonitor r0) {
        return r0.observers();
    }

    public static final /* synthetic */ String access$otherThreads(FreezeMonitor r0, Thread r1) {
        return r0.otherThreads(r1);
    }

    public static final /* synthetic */ String access$percent(FreezeMonitor r0, int r1, int r2) {
        return r0.percent(r1, r2);
    }

    public static final /* synthetic */ String access$screenName(FreezeMonitor r0) {
        return r0.screenName();
    }

    public static final /* synthetic */ void access$setFreezes$p(int r0) {
        freezes = r0;
    }

    public static final /* synthetic */ void access$showFreezeBulletin(FreezeMonitor r0, long r1) {
        r0.showFreezeBulletin(r1);
    }

    private final String appLine() {
        return "app " + BuildVars.BUILD_VERSION_STRING + " (" + BuildVars.BUILD_VERSION + "), " + Build.MANUFACTURER + " " + Build.MODEL + ", Android " + Build.VERSION.RELEASE + " (API " + Build.VERSION.SDK_INT + ")";
    }

    private final void appendReport(String r8) {
        Object r7 = reportLock;
        monitor-enter(r7);
        FreezeMonitor r0 = INSTANCE;     // Catch: Throwable -> L10 Exception -> L12
        r0.getReportsDir().mkdirs();     // Catch: Throwable -> L10 Exception -> L12
        File r02 = r0.getReportFile();     // Catch: Throwable -> L10 Exception -> L12
        FilesKt.appendText$default(r02, r8, null, 2, null);     // Catch: Throwable -> L10 Exception -> L12
        if (r02.length() <= 2000000) goto L20;
        byte[] r82 = new byte[DurationKt.NANOS_IN_MILLIS];     // Catch: Throwable -> L10 Exception -> L12
        RandomAccessFile r1 = new RandomAccessFile(r02, "r");     // Catch: Throwable -> L10 Exception -> L12
        r1.seek(r1.length() - 1000000);     // Catch: Throwable -> L14
        r1.readFully(r82);     // Catch: Throwable -> L14
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L14
        CloseableKt.closeFinally(r1, null);     // Catch: Throwable -> L10 Exception -> L12
        FilesKt.writeBytes(r02, ArraysKt.plus("##### (older records trimmed)\n".getBytes(Charsets.UTF_8), r82));     // Catch: Throwable -> L10 Exception -> L12
    L14:
        th = move-exception;
        throw th;     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        CloseableKt.closeFinally(r1, th);     // Catch: Throwable -> L10 Exception -> L12
        throw th;     // Catch: Throwable -> L10 Exception -> L12
    L20:
        Unit r83 = Unit.INSTANCE;     // Catch: Throwable -> L10
        monitor-exit(r7);
        return;
    L12:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }

    private final File copyReport() {
        Object r1 = reportLock;
        monitor-enter(r1);
        FreezeMonitor r0 = INSTANCE;     // Catch: Throwable -> L11
        File r3 = new File(r0.getReportsDir(), "freeze_" + BuildVars.BUILD_VERSION_STRING + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date()) + ".txt");     // Catch: Throwable -> L11
        File[] r8 = r0.getReportsDir().listFiles();     // Catch: Throwable -> L11
        if (r8 == null) goto L14;
        int r02 = r8.length;     // Catch: Throwable -> L11
        int r2 = 0;
    L7:
        if (r2 >= r02) goto L14;
        File r4 = r8[r2];     // Catch: Throwable -> L11
        if (Intrinsics.areEqual(r4.getName(), "freeze_report.txt") == true) goto L13;
        r4.delete();     // Catch: Throwable -> L11
    L13:
        r2 = r2 + 1;     // Catch: Throwable -> L11
    L14:
        FilesKt.copyTo$default(INSTANCE.getReportFile(), r3, true, 0, 4, null);     // Catch: Throwable -> L11
        monitor-exit(r1);
        return r3;
    L11:
        th = move-exception;
        throw th;
    }

    private final long[] countInstances(Class<?>[] r7) {
        Class r6 = Boolean.TYPE;
        Class<?> r0 = Class.forName("dalvik.system.VMDebug");
        return (long[]) r0.getDeclaredMethod("countInstancesOfClasses", new Class[]{r7.getClass(), r6}).invoke(null, new Object[]{r7, Boolean.TRUE});
    L5:
        Method r62 = r0.getDeclaredMethod("countInstancesOfClass", new Class[]{Class.class, r6});
        int r02 = r7.length;
        long[] r2 = new long[r02];
        int r3 = 0;
    L6:
        if (r3 >= r02) goto L8;
        r2[r3] = ((Long) r62.invoke(null, new Object[]{r7[r3], Boolean.TRUE})).longValue();
        r3 = r3 + 1;
        goto L6
    L8:
        return r2;
    }

    private final String delegateName(Object r6) {
        if (r6 != null) goto L5;
        return "null";
    L5:
        Class<?> r5 = r6.getClass();
        if (StringsKt.endsWith$default(r5.getName(), "WeakObserversGroupImpl", false, 2, null) == false) goto L21;
        Result.Companion r0 = Result.Companion;     // Catch: Throwable -> L9
        Field r02 = r5.getDeclaredField("reference");     // Catch: Throwable -> L9
        r02.setAccessible(true);     // Catch: Throwable -> L9
        Object r62 = Result.m2018constructorimpl(((WeakReference) r02.get(r6)).get());     // Catch: Throwable -> L9
    L12:
        if (Result.m2021exceptionOrNullimpl(r62) != null) goto L19;
        if (r62 == null) goto L15;
        String r52 = r62.getClass().getName();
    L17:
        return r52.concat(" (weak)");
    L15:
        r52 = "cleared";
        goto L17
    L19:
        return r5.getName();
    L9:
        th = move-exception;
        Result.Companion r03 = Result.Companion;
        r62 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L12
    L21:
        return r5.getName();
    }

    private final String environment() {
        Result.Companion r0 = Result.Companion;     // Catch: Throwable -> L8
        Collection<Plugin> r02 = PluginsController.Companion.getInstance().getPlugins().values();     // Catch: Throwable -> L8
        ArrayList r1 = new ArrayList();     // Catch: Throwable -> L8
        Iterator<T> r03 = r02.iterator();     // Catch: Throwable -> L8
    L4:
        if (r03.hasNext() == false) goto L10;
        Object r2 = r03.next();     // Catch: Throwable -> L8
        if (((Plugin) r2).isEnabled() == false) goto L4;
        r1.add(r2);     // Catch: Throwable -> L8
        goto L4
    L10:
        ArrayList r04 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r1, 10));     // Catch: Throwable -> L8
        int r22 = r1.size();     // Catch: Throwable -> L8
        int r3 = 0;
    L11:
        if (r3 >= r22) goto L13;
        Object r4 = r1.get(r3);     // Catch: Throwable -> L8
        r3 = r3 + 1;     // Catch: Throwable -> L8
        Plugin r42 = (Plugin) r4;     // Catch: Throwable -> L8
        r04.add(r42.getId() + " " + r42.getVersion());     // Catch: Throwable -> L8
        goto L11
    L13:
        String r05 = CollectionsKt.joinToString$default(CollectionsKt.sorted(r04), ", ", null, null, 0, null, null, 62, null);     // Catch: Throwable -> L8
        if (r05.length() != 0) goto L16;
        r05 = "none";
    L16:
        Object r06 = Result.m2018constructorimpl(r05);     // Catch: Throwable -> L8
    L19:
        Throwable r12 = Result.m2021exceptionOrNullimpl(r06);
        if (r12 == null) goto L23;
        r06 = "unavailable: " + r12;
    L23:
        String r13 = (String) r06;
        Object r07 = Result.m2018constructorimpl(Boolean.valueOf(UserConfig.getInstance(UserConfig.selectedAccount).isPremium()));     // Catch: Throwable -> L26
    L28:
        Boolean r32 = null;
        if (Result.m2024isFailureimpl(r07) == false) goto L31;
        r07 = null;
    L31:
        Boolean r08 = (Boolean) r07;
        String r23 = appLine();
        long r43 = (SystemClock.elapsedRealtime() - Process.getStartElapsedRealtime()) / 1000;
        ForegroundDetector r6 = ForegroundDetector.getInstance();
        if (r6 == null) goto L35;
        r32 = Boolean.valueOf(r6.isForeground());
    L35:
        return "-- environment --\n" + r23 + "\nprocess uptime " + r43 + " s, foreground " + r32 + "\nheap now: " + heapLine() + "\naccounts active " + UserConfig.getActivatedAccountsCount() + ", premium " + r08 + "\nenabled plugins: " + r13 + "\n";
    L26:
        th = move-exception;
        Result.Companion r24 = Result.Companion;
        r07 = Result.m2018constructorimpl(ResultKt.createFailure(th));
    L8:
        th = move-exception;
        Result.Companion r14 = Result.Companion;
        r06 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L19
    }

    private final String formatMb(long r3) {
        StringCompanionObject r2 = StringCompanionObject.INSTANCE;
        return String.format(Locale.US, "%.1f MB", Arrays.copyOf(new Object[]{Double.valueOf(r3 / 1048576.0d)}, 1));
    }

    private final String formatSeconds(long r3) {
        StringCompanionObject r2 = StringCompanionObject.INSTANCE;
        return String.format(Locale.US, "%.1f s", Arrays.copyOf(new Object[]{Double.valueOf(r3 / 1000.0d)}, 1));
    }

    private final String fragmentStack() {
        Result.Companion r0 = Result.Companion;     // Catch: Throwable -> L13
        LaunchActivity r02 = LaunchActivity.instance;     // Catch: Throwable -> L13
        if (r02 == null) goto L10;
        INavigationLayout r03 = r02.getActionBarLayout();     // Catch: Throwable -> L13
        if (r03 == null) goto L10;
        List<BaseFragment> r04 = r03.getFragmentStack();     // Catch: Throwable -> L13
        if (r04 == null) goto L10;
    L11:
        ArrayList r1 = new ArrayList(r04);     // Catch: Throwable -> L13
        Object r10 = Result.m2018constructorimpl("(" + r1.size() + ") " + CollectionsKt.joinToString$default(r1, " > ", null, null, 0, null, new FreezeMonitor$$ExternalSyntheticLambda5(), 30, null));     // Catch: Throwable -> L13
    L16:
        if (Result.m2024isFailureimpl(r10) == false) goto L19;
        r10 = "unavailable";
    L19:
        return (String) r10;
    L10:
        r04 = CollectionsKt.emptyList();     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        Result.Companion r05 = Result.Companion;
        r10 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L16
    }

    private final List<String> frameNames(StackTraceElement[] r1, int r2) {
        List r0 = ArraysKt.take(r1, r2);
        ArrayList r12 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r0, 10));
        Iterator r02 = r0.iterator();
    L4:
        if (r02.hasNext() == false) goto L6;
        r12.add(((StackTraceElement) r02.next()).toString());
        goto L4
    L6:
        return r12;
    }

    private final long[] gcStats() {
        int r4 = GC_STATS.length;
        long[] r0 = new long[r4];
        int r1 = 0;
    L3:
        if (r1 >= r4) goto L11;
        String r2 = Debug.getRuntimeStat(GC_STATS[r1]);
        if (r2 == null) goto L9;
        Long r22 = StringsKt.toLongOrNull(r2);
        if (r22 == null) goto L9;
        long r23 = r22.longValue();
    L10:
        r0[r1] = r23;
        r1 = r1 + 1;
    L9:
        r23 = 0;
        goto L10
    L11:
        return r0;
    }

    private final Map<Integer, String> getNotificationNames() {
        return (Map) notificationNames$delegate.getValue();
    }

    private final File getReportFile() {
        return new File(getReportsDir(), "freeze_report.txt");
    }

    private final String heapLine() {
        long r0 = HeapMonitor.INSTANCE.getUsedBytes();
        long r2 = Runtime.getRuntime().maxMemory();
        return "java " + formatMb(r0) + " / " + formatMb(r2) + " (" + ((r0 * 100) / r2) + "%), native " + formatMb(Debug.getNativeHeapAllocatedSize());
    }

    @JvmStatic
    public static final void init() {
        if (DebugConfig.getFreezeMonitorEnabled() == false) goto L6;
        watchdog = new Watchdog();
        return;
    }

    private final String liveObjects() {
        int r0 = COUNTED_CLASSES.length;     // Catch: Throwable -> L7
        Class<?>[] r1 = new Class[r0];     // Catch: Throwable -> L7
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1[r2] = Class.forName(COUNTED_CLASSES[r2]);     // Catch: Throwable -> L7
        r2 = r2 + 1;     // Catch: Throwable -> L7
        goto L3
    L5:
        HeapMonitor.INSTANCE.collectGarbage$TMessagesProj();     // Catch: Throwable -> L7
        return CollectionsKt.joinToString$default(ArraysKt.getIndices(r1), "\n", null, "\n", 0, null, new FreezeMonitor$$ExternalSyntheticLambda9(countInstances(r1), r1), 26, null);
    L7:
        th = move-exception;
        return "unavailable: " + th + "\n";
    }

    private final String observers() {
        StringBuilder r1 = new StringBuilder();
        Pair[] r2 = {TuplesKt.to("account", NotificationCenter.getInstance(UserConfig.selectedAccount)), TuplesKt.to("global", NotificationCenter.getGlobalInstance())};
        int r4 = 0;
    L4:
        if (r4 >= 2) goto L35;
        Pair r5 = r2[r4];
        String r6 = (String) r5.component1();
        NotificationCenter r52 = (NotificationCenter) r5.component2();
        Field r7 = NotificationCenter.class.getDeclaredField("observers");     // Catch: Exception -> L11
        boolean r8 = true;
        r7.setAccessible(true);     // Catch: Exception -> L11
        SparseArray r53 = (SparseArray) r7.get(r52);     // Catch: Exception -> L11
        ArrayList r72 = new ArrayList();     // Catch: Exception -> L11
        HashMap r9 = new HashMap();     // Catch: Exception -> L11
        int r10 = r53.size();     // Catch: Exception -> L11
        int r11 = 0;
        int r12 = 0;
    L7:
        if (r11 >= r10) goto L30;
        List r13 = (List) r53.valueAt(r11);     // Catch: Exception -> L11
        r12 = r12 + r13.size();     // Catch: Exception -> L11
        Integer r14 = Integer.valueOf(r13.size());     // Catch: Exception -> L11
        String r3 = getNotificationNames().get(Integer.valueOf(r53.keyAt(r11)));     // Catch: Exception -> L11
        if (r3 != null) goto L14;
        r3 = String.valueOf(r53.keyAt(r11));     // Catch: Exception -> L11
    L14:
        r72.add(TuplesKt.to(r14, r3));     // Catch: Exception -> L11
        int r32 = r13.size();     // Catch: Exception -> L11
        int r142 = 0;
    L15:
        if (r142 >= r32) goto L29;
        boolean r16 = r8;
        Pair[] r17 = r2;
        int r18 = r32;
        String r22 = StringsKt.substringAfterLast$default(delegateName(r13.get(r142)), '.', null, 2, null);     // Catch: Exception -> L23
        Integer r33 = (Integer) r9.get(r22);     // Catch: Exception -> L23
        if (r33 == null) goto L25;
        int r34 = r33.intValue();     // Catch: Exception -> L23
    L26:
        r9.put(r22, Integer.valueOf(r34 + 1));     // Catch: Exception -> L23
        r142 = r142 + 1;     // Catch: Exception -> L23
        r8 = r16;
        r2 = r17;
        r32 = r18;
        goto L15
    L25:
        r34 = 0;
    L23:
        e = e;
    L32:
        r1.append(r6 + ": failed " + e + "\n");
    L33:
        r4 = r4 + 1;
        r2 = r17;
    L27:
        e = e;
    L13:
        r17 = r2;
        goto L32
    L29:
        r11 = r11 + 1;     // Catch: Exception -> L23
        r8 = r8;
        goto L7
    L30:
        r17 = r2;
        r1.append(r6 + ": " + r12 + " registrations in " + r72.size() + " ids\n");     // Catch: Exception -> L23
        r1.append("  biggest ids: ");     // Catch: Exception -> L23
        r1.append(CollectionsKt.joinToString$default(CollectionsKt.take(CollectionsKt.sortedWith(r72, new FreezeMonitor$observers$$inlined$sortedByDescending$1()), 8), ", ", null, null, 0, null, new FreezeMonitor$$ExternalSyntheticLambda7(), 30, null));     // Catch: Exception -> L23
        r1.append('\n');     // Catch: Exception -> L23
        r1.append("  by class: ");     // Catch: Exception -> L23
        r1.append(CollectionsKt.joinToString$default(CollectionsKt.take(CollectionsKt.sortedWith(r9.entrySet(), new FreezeMonitor$observers$$inlined$sortedByDescending$2()), 12), ", ", null, null, 0, null, new FreezeMonitor$$ExternalSyntheticLambda8(), 30, null));     // Catch: Exception -> L23
        r1.append('\n');     // Catch: Exception -> L23
    L11:
        e = e;
        goto L13
    L35:
        return r1.toString();
    }

    private final String otherThreads(Thread r17) {
        StringBuilder r1 = new StringBuilder();
        Iterator<Map.Entry<Thread, StackTraceElement[]>> r0 = Thread.getAllStackTraces().entrySet().iterator();     // Catch: Exception -> L16
        int r4 = 0;
    L4:
        if (r0.hasNext() == false) goto L37;
        Map.Entry<Thread, StackTraceElement[]> r5 = r0.next();     // Catch: Exception -> L16
        Thread r6 = r5.getKey();     // Catch: Exception -> L16
        StackTraceElement[] r52 = r5.getValue();     // Catch: Exception -> L16
        if (Intrinsics.areEqual(r6, r17) == true) goto L4;
        if (Intrinsics.areEqual(r6, Thread.currentThread()) == true) goto L4;
        Thread.State r8 = r6.getState();     // Catch: Exception -> L16
        if (r8 == Thread.State.RUNNABLE) goto L18;
        if (r8 != Thread.State.BLOCKED) goto L4;
    L18:
        List<String> r53 = frameNames(r52, 20);     // Catch: Exception -> L16
        String r9 = (String) CollectionsKt.firstOrNull(r53);     // Catch: Exception -> L16
        if (r9 == null) goto L4;
        String[] r11 = IDLE_TOP_FRAMES;     // Catch: Exception -> L16
        int r12 = r11.length;     // Catch: Exception -> L16
        int r13 = 0;
    L22:
        if (r13 >= r12) goto L27;
        if (StringsKt.startsWith$default(r9, r11[r13], false, 2, null) == true) goto L4;
        r13 = r13 + 1;     // Catch: Exception -> L16
        goto L22
    L27:
        r1.append(Typography.quote);     // Catch: Exception -> L16
        r1.append(r6.getName());     // Catch: Exception -> L16
        r1.append("\" ");     // Catch: Exception -> L16
        r1.append(r8);     // Catch: Exception -> L16
        r1.append('\n');     // Catch: Exception -> L16
        Iterator<T> r2 = r53.iterator();     // Catch: Exception -> L16
    L29:
        if (r2.hasNext() == false) goto L32;
        String r54 = (String) r2.next();     // Catch: Exception -> L16
        r1.append("    at ");     // Catch: Exception -> L16
        r1.append(r54);     // Catch: Exception -> L16
        r1.append('\n');     // Catch: Exception -> L16
        goto L29
    L32:
        r4 = r4 + 1;
        if (r4 < 40) goto L4;
    L37:
        if (r1.length() > 0) goto L60;
        return "(none)\n";
    L60:
        return r1.toString();
    L16:
        e = move-exception;
        r1.append("thread dump failed: ");
        r1.append(e);
        r1.append('\n');
        goto L37
    }

    private final String percent(int r3, int r4) {
        return StringsKt.padStart$default(String.valueOf((r3 * 100) / r4), 4, 0, 2, null);
    }

    private final String screenName() {
        Result.Companion r1 = Result.Companion;     // Catch: Throwable -> L8
        BaseFragment r12 = LaunchActivity.getLastFragmentIncludeMainTabs();     // Catch: Throwable -> L8
        if (r12 == null) goto L5;
        String r13 = r12.getClass().getName();     // Catch: Throwable -> L8
    L6:
        Object r14 = Result.m2018constructorimpl(r13);     // Catch: Throwable -> L8
    L11:
        if (Result.m2024isFailureimpl(r14) == false) goto L14;
        r14 = "unknown";
    L14:
        return (String) r14;
    L5:
        r13 = "none";
    L8:
        th = move-exception;
        Result.Companion r0 = Result.Companion;
        r14 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L11
    }

    private final void showFreezeBulletin(long r4) {
        LaunchActivity r0 = LaunchActivity.instance;
        if (watchdog == null) goto L10;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.isFinishing() == true) goto L12;
        BulletinFactory.global().createSimpleBulletin(R.raw.error, "Main thread froze for " + formatSeconds(r4), "Share", new FreezeMonitor$$ExternalSyntheticLambda10(r0)).show();
        return;
    L12:
        return;
    }

    public final void deleteReports(Function0<Unit> r2) {
        Utilities.globalQueue.postRunnable(new FreezeMonitor$$ExternalSyntheticLambda2(r2));
    }

    public final File getReport() {
        File r4 = getReportFile();
        if (r4.length() <= 0) goto L5;
        return r4;
    L5:
        return null;
    }

    public final File getReportsDir() {
        return (File) reportsDir$delegate.getValue();
    }

    public final long getReportsSize() {
        File[] r6 = getReportsDir().listFiles();
        long r0 = 0;
        if (r6 == null) goto L7;
        int r2 = r6.length;
        int r3 = 0;
    L5:
        if (r3 >= r2) goto L7;
        r0 = r0 + r6[r3].length();
        r3 = r3 + 1;
    L7:
        return r0;
    }

    public final int getThresholdMs() {
        return RangesKt.coerceIn(DebugConfig.getFreezeMonitorThresholdMs(), 200, CastStatusCodes.AUTHENTICATION_FAILED);
    }

    public final void setEnabled(boolean r1) {
        if (DebugConfig.getFreezeMonitorEnabled() != r1) goto L5;
        return;
    L5:
        DebugConfig.setFreezeMonitorEnabled(r1);
        Watchdog r0 = watchdog;
        if (r0 == null) goto L8;
        r0.stop();
    L8:
        if (r1 == false) goto L10;
        Watchdog r02 = new Watchdog();
    L11:
        watchdog = r02;
        return;
    L10:
        r02 = null;
        goto L11
    }

    public final void setThresholdMs(int r1) {
        DebugConfig.setFreezeMonitorThresholdMs(r1);
    }

    public final void shareReport(Activity r2) {
        Utilities.globalQueue.postRunnable(new FreezeMonitor$$ExternalSyntheticLambda3(r2));
    }
}
