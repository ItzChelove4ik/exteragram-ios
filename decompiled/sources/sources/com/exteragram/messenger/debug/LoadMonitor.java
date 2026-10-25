package com.exteragram.messenger.debug;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.TrafficStats;
import android.os.Build;
import android.os.PowerManager;
import android.os.Process;
import android.os.SystemClock;
import android.os.health.HealthStats;
import android.os.health.SystemHealthManager;
import android.os.health.TimerStat;
import android.system.Os;
import android.system.OsConstants;
import com.chaquo.python.internal.Common;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.File;
import java.io.FileInputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
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
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ForegroundDetector;
import org.telegram.ui.LaunchActivity;

@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\bÆ\u0002\u0018\u00002\u00020\u0001:\b\u0087\u0001\u0088\u0001\u0089\u0001\u008a\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\n\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\t¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u001a\u001a\u00020\u00062\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b\u001e\u0010\u001fJ\u0015\u0010 \u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b \u0010\u001fJ\u000f\u0010!\u001a\u00020\u0006H\u0007¢\u0006\u0004\b!\u0010\u0003J\u001f\u0010#\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020\u0012H\u0002¢\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\u0006H\u0002¢\u0006\u0004\b%\u0010\u0003J\u000f\u0010&\u001a\u00020\tH\u0002¢\u0006\u0004\b&\u0010\u000bJ\u0011\u0010'\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\b'\u0010(J\u000f\u0010*\u001a\u00020)H\u0002¢\u0006\u0004\b*\u0010+J\u000f\u0010,\u001a\u00020\u0015H\u0002¢\u0006\u0004\b,\u0010\u0017J\u000f\u0010-\u001a\u00020\u0015H\u0002¢\u0006\u0004\b-\u0010\u0017J+\u00101\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020/0.j\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020/`0H\u0002¢\u0006\u0004\b1\u00102J\u0019\u00104\u001a\u0004\u0018\u00010/2\u0006\u00103\u001a\u00020\u000fH\u0002¢\u0006\u0004\b4\u00105J\u0017\u00107\u001a\u00020\u00152\u0006\u00106\u001a\u00020\u0015H\u0002¢\u0006\u0004\b7\u00108JS\u0010<\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00150.j\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0015`02\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020/092\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020/09H\u0002¢\u0006\u0004\b<\u0010=J=\u0010B\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00150A0@2\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0015092\u0006\u0010?\u001a\u00020\tH\u0002¢\u0006\u0004\bB\u0010CJ3\u0010F\u001a\u00020E2\u0018\u0010>\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00150A0@2\b\b\u0002\u0010D\u001a\u00020\u0004H\u0002¢\u0006\u0004\bF\u0010GJ\u0019\u0010I\u001a\u0004\u0018\u00010\u000f2\u0006\u0010H\u001a\u00020\u000fH\u0002¢\u0006\u0004\bI\u0010JJ\u001d\u0010L\u001a\u00020\u00062\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u000f0@H\u0002¢\u0006\u0004\bL\u0010MJ\u001f\u0010Q\u001a\u0004\u0018\u00010\u000f2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020O0NH\u0002¢\u0006\u0004\bQ\u0010RJ\u0011\u0010T\u001a\u0004\u0018\u00010SH\u0002¢\u0006\u0004\bT\u0010UJ\u001f\u0010X\u001a\u00020\u000f2\u0006\u0010V\u001a\u00020\u000f2\u0006\u0010W\u001a\u00020\tH\u0002¢\u0006\u0004\bX\u0010YJ\u0017\u0010[\u001a\u00020)2\u0006\u0010Z\u001a\u00020SH\u0002¢\u0006\u0004\b[\u0010\\J\u0017\u0010^\u001a\u00020)2\u0006\u0010]\u001a\u00020\u000fH\u0002¢\u0006\u0004\b^\u0010_J\u001f\u0010a\u001a\u00020\u00122\u0006\u0010`\u001a\u00020)2\u0006\u0010]\u001a\u00020\u000fH\u0002¢\u0006\u0004\ba\u0010bJ\u0017\u0010d\u001a\u00020\u000f2\u0006\u0010c\u001a\u00020\u0015H\u0002¢\u0006\u0004\bd\u0010eJ\u001f\u0010h\u001a\u00020\u000f2\u0006\u0010f\u001a\u00020\u00152\u0006\u0010g\u001a\u00020\u0015H\u0002¢\u0006\u0004\bh\u0010iJI\u0010l\u001a\u00020\u000f2\u0006\u0010g\u001a\u00020\u00152\u0006\u0010f\u001a\u00020\u00152\u0006\u0010j\u001a\u00020\u00152\u0006\u0010k\u001a\u00020\u00152\u0018\u0010>\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00150A0@H\u0002¢\u0006\u0004\bl\u0010mR\u001a\u0010n\u001a\b\u0012\u0004\u0012\u00020\u000f0N8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bn\u0010oR\"\u0010q\u001a\u0010\u0012\f\u0012\n p*\u0004\u0018\u00010\u000f0\u000f0N8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bq\u0010oR\u0018\u0010s\u001a\u0004\u0018\u00010r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bs\u0010tR\u0018\u0010v\u001a\u0004\u0018\u00010u8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bv\u0010wR\u001e\u0010z\u001a\n\u0012\u0004\u0012\u00020y\u0018\u00010x8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bz\u0010{RH\u0010|\u001a6\u0012\u0004\u0012\u00020\u000f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f090.j\u001a\u0012\u0004\u0012\u00020\u000f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f09`08\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b|\u0010}R0\u0010~\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0.j\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f`08\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b~\u0010}R0\u0010\u007f\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t0.j\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t`08\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u007f\u0010}R\u001f\u0010\u0083\u0001\u001a\u00020\u00128FX\u0086\u0084\u0002¢\u0006\u000f\n\u0006\b\u0080\u0001\u0010\u0081\u0001\u001a\u0005\b\u0082\u0001\u0010\u0014R\u001f\u0010\u0086\u0001\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\u000f\n\u0006\b\u0084\u0001\u0010\u0081\u0001\u001a\u0005\b\u0085\u0001\u0010\u0017¨\u0006\u008b\u0001"}, d2 = {"Lcom/exteragram/messenger/debug/LoadMonitor;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "enabled", _UrlKt.FRAGMENT_ENCODE_SET, "setEnabled", "(Z)V", _UrlKt.FRAGMENT_ENCODE_SET, "getCpuPercent", "()I", "percent", "setCpuPercent", "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, "getSummary", "()Ljava/lang/String;", "Ljava/io/File;", "getLastReport", "()Ljava/io/File;", _UrlKt.FRAGMENT_ENCODE_SET, "getReportsSize", "()J", "Lkotlin/Function0;", "onDone", "deleteReports", "(Lkotlin/jvm/functions/Function0;)V", "Landroid/app/Activity;", "activity", "shareLastReport", "(Landroid/app/Activity;)V", "captureAndShare", "init", "file", "share", "(Landroid/app/Activity;Ljava/io/File;)V", "showPendingAlert", "currentState", "isPlugged", "()Ljava/lang/Boolean;", "Lorg/json/JSONObject;", "batteryJson", "()Lorg/json/JSONObject;", "uidRxBytes", "uidTxBytes", "Ljava/util/HashMap;", "Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;", "Lkotlin/collections/HashMap;", "readThreads", "()Ljava/util/HashMap;", "stat", "parseStat", "(Ljava/lang/String;)Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;", "ticks", "ticksToMs", "(J)J", _UrlKt.FRAGMENT_ENCODE_SET, "previous", "current", "threadDeltas", "(Ljava/util/Map;Ljava/util/Map;)Ljava/util/HashMap;", "threads", "limit", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Pair;", "topThreads", "(Ljava/util/Map;I)Ljava/util/List;", "withOrigin", "Lorg/json/JSONArray;", "threadsJson", "(Ljava/util/List;Z)Lorg/json/JSONArray;", "name", "originOf", "(Ljava/lang/String;)Ljava/lang/String;", "names", "resolveOrigins", "(Ljava/util/List;)V", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/StackTraceElement;", "trace", "appFrame", "([Ljava/lang/StackTraceElement;)Ljava/lang/String;", "Landroid/os/health/HealthStats;", "takeHealthStats", "()Landroid/os/health/HealthStats;", "dataType", PluginsConstants.Settings.KEY, "healthKeyName", "(Ljava/lang/String;I)Ljava/lang/String;", "stats", "healthJson", "(Landroid/os/health/HealthStats;)Lorg/json/JSONObject;", "trigger", "baseReport", "(Ljava/lang/String;)Lorg/json/JSONObject;", "report", "writeReport", "(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/io/File;", "ms", "formatDuration", "(J)Ljava/lang/String;", "cpuMs", "wallMs", "formatPercent", "(JJ)Ljava/lang/String;", "rxBytes", "txBytes", "describeEpisode", "(JJJJLjava/util/List;)Ljava/lang/String;", "STATE_NAMES", "[Ljava/lang/String;", "kotlin.jvm.PlatformType", "SKIPPED_FRAMES", "Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;", "sampler", "Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;", "Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;", "pendingAlert", "Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;", "Ljava/lang/ref/WeakReference;", "Lorg/telegram/ui/ActionBar/AlertDialog;", "alertRef", "Ljava/lang/ref/WeakReference;", "healthKeyNames", "Ljava/util/HashMap;", "threadOrigins", "originAttempts", "reportsDir$delegate", "Lkotlin/Lazy;", "getReportsDir", "reportsDir", "clockTicks$delegate", "getClockTicks", "clockTicks", "ThreadStat", "PendingAlert", "Episode", "Sampler", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLoadMonitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LoadMonitor.kt\ncom/exteragram/messenger/debug/LoadMonitor\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,696:1\n3938#2:697\n4474#2,2:698\n13213#2,2:723\n3938#2:728\n4474#2,2:729\n3938#2:745\n4474#2,2:746\n14048#2,2:751\n2045#3,14:700\n1080#3:715\n1586#3:716\n1661#3,3:717\n777#3:720\n873#3,2:721\n1205#3,2:731\n1282#3,4:733\n1586#3:741\n1661#3,3:742\n1080#3:748\n1915#3,2:749\n1#4:714\n410#5,3:725\n413#5,4:737\n*S KotlinDebug\n*F\n+ 1 LoadMonitor.kt\ncom/exteragram/messenger/debug/LoadMonitor\n*L\n130#1:697\n130#1:698,2\n291#1:723,2\n308#1:728\n308#1:729,2\n417#1:745\n417#1:746,2\n137#1:751,2\n131#1:700,14\n253#1:715\n253#1:716\n253#1:717,3\n271#1:720\n271#1:721,2\n309#1:731,2\n309#1:733,4\n382#1:741\n382#1:742,3\n418#1:748\n420#1:749,2\n304#1:725,3\n304#1:737,4\n*E\n"})
/* loaded from: classes4.dex */
public final class LoadMonitor {
    public static final LoadMonitor INSTANCE = null;
    private static final String[] SKIPPED_FRAMES = null;
    private static final String[] STATE_NAMES = null;
    private static WeakReference<AlertDialog> alertRef;
    private static final Lazy clockTicks$delegate = null;
    private static final HashMap<String, Map<Integer, String>> healthKeyNames = null;
    private static final HashMap<String, Integer> originAttempts = null;
    private static volatile PendingAlert pendingAlert;
    private static final Lazy reportsDir$delegate = null;
    private static volatile Sampler sampler;
    private static final HashMap<String, String> threadOrigins = null;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"Lcom/exteragram/messenger/debug/LoadMonitor$Episode;", _UrlKt.FRAGMENT_ENCODE_SET, "startedAt", _UrlKt.FRAGMENT_ENCODE_SET, "startRealtime", "cpuMs", "rxBytes", "txBytes", "threads", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;", "health", "Landroid/os/health/HealthStats;", "<init>", "(JJJJJLjava/util/Map;Landroid/os/health/HealthStats;)V", "getStartedAt", "()J", "getStartRealtime", "getCpuMs", "getRxBytes", "getTxBytes", "getThreads", "()Ljava/util/Map;", "getHealth", "()Landroid/os/health/HealthStats;", "reported", _UrlKt.FRAGMENT_ENCODE_SET, "getReported", "()Z", "setReported", "(Z)V", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Episode {
        private final long cpuMs;
        private final HealthStats health;
        private boolean reported;
        private final long rxBytes;
        private final long startRealtime;
        private final long startedAt;
        private final Map<Integer, ThreadStat> threads;
        private final long txBytes;

        public Episode(long r1, long r3, long r5, long r7, long r9, Map<Integer, ThreadStat> r11, HealthStats r12) {
            this.startedAt = r1;
            this.startRealtime = r3;
            this.cpuMs = r5;
            this.rxBytes = r7;
            this.txBytes = r9;
            this.threads = r11;
            this.health = r12;
        }

        public final long getCpuMs() {
            return this.cpuMs;
        }

        public final HealthStats getHealth() {
            return this.health;
        }

        public final boolean getReported() {
            return this.reported;
        }

        public final long getRxBytes() {
            return this.rxBytes;
        }

        public final long getStartRealtime() {
            return this.startRealtime;
        }

        public final long getStartedAt() {
            return this.startedAt;
        }

        public final Map<Integer, ThreadStat> getThreads() {
            return this.threads;
        }

        public final long getTxBytes() {
            return this.txBytes;
        }

        public final void setReported(boolean r1) {
            this.reported = r1;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;", _UrlKt.FRAGMENT_ENCODE_SET, "file", "Ljava/io/File;", "message", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/io/File;Ljava/lang/String;)V", "getFile", "()Ljava/io/File;", "getMessage", "()Ljava/lang/String;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class PendingAlert {
        private final File file;
        private final String message;

        public PendingAlert(File r1, String r2) {
            this.file = r1;
            this.message = r2;
        }

        public final File getFile() {
            return this.file;
        }

        public final String getMessage() {
            return this.message;
        }
    }

    @Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0010$\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0006\u0010.\u001a\u00020/J\b\u00100\u001a\u00020/H\u0016J\b\u00101\u001a\u00020/H\u0016J5\u00102\u001a\u00020/2\u0006\u00103\u001a\u00020\u00182\u0006\u00104\u001a\u00020\u00182\u0016\u00105\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010706\"\u0004\u0018\u000107H\u0016¢\u0006\u0002\u00108J\b\u00109\u001a\u00020/H\u0002J\b\u0010:\u001a\u00020/H\u0002J\b\u0010;\u001a\u00020/H\u0002J<\u0010<\u001a\u00020/2\u0006\u0010=\u001a\u00020\f2\u0006\u0010>\u001a\u00020\f2\u0006\u0010?\u001a\u00020\f2\u0006\u0010@\u001a\u00020\f2\u0012\u0010A\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u001b0BH\u0002J\n\u0010C\u001a\u0004\u0018\u00010\u000eH\u0002J\u000e\u0010D\u001a\u00020\u00102\u0006\u0010E\u001a\u00020%J\u000e\u0010F\u001a\u00020\u00102\u0006\u0010E\u001a\u00020%R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0019\u001a\u001e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u001b0\u001aj\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u001b`\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010$\u001a\u001e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020 0\u001aj\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020 `\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00100'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010+\u001a\u0004\u0018\u00010%2\b\u0010*\u001a\u0004\u0018\u00010%@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-¨\u0006G"}, d2 = {"Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;", "Lorg/telegram/ui/Components/ForegroundDetector$Listener;", "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;", "<init>", "()V", "queue", "Lorg/telegram/messenger/DispatchQueue;", "getQueue", "()Lorg/telegram/messenger/DispatchQueue;", "sampleRunnable", "Ljava/lang/Runnable;", "startedAt", _UrlKt.FRAGMENT_ENCODE_SET, "startHealth", "Landroid/os/health/HealthStats;", "startBattery", "Lorg/json/JSONObject;", "recentHealth", "recentHealthRealtime", "lastRealtime", "lastCpuMs", "lastRxBytes", "lastTxBytes", "lastState", _UrlKt.FRAGMENT_ENCODE_SET, "threads", "Ljava/util/HashMap;", "Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;", "Lkotlin/collections/HashMap;", "episode", "Lcom/exteragram/messenger/debug/LoadMonitor$Episode;", "wallMs", _UrlKt.FRAGMENT_ENCODE_SET, "cpuMs", "rxBytes", "txBytes", "threadMs", _UrlKt.FRAGMENT_ENCODE_SET, "samples", "Lkotlin/collections/ArrayDeque;", "observers", "Lorg/telegram/messenger/NotificationCenter$ObserversGroup;", "value", "summary", "getSummary", "()Ljava/lang/String;", "stop", _UrlKt.FRAGMENT_ENCODE_SET, "onBecameForeground", "onBecameBackground", "didReceivedNotification", "id", "account", "args", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(II[Ljava/lang/Object;)V", "scheduleNext", "sample", "updateState", "checkEpisode", "now", "cpu", "rx", "tx", "current", _UrlKt.FRAGMENT_ENCODE_SET, "recentHealthStats", "captureReport", "trigger", "buildReport", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nLoadMonitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LoadMonitor.kt\ncom/exteragram/messenger/debug/LoadMonitor$Sampler\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,696:1\n777#2:697\n873#2,2:698\n1586#2:700\n1661#2,3:701\n1080#2:712\n410#3,7:704\n1#4:711\n*S KotlinDebug\n*F\n+ 1 LoadMonitor.kt\ncom/exteragram/messenger/debug/LoadMonitor$Sampler\n*L\n567#1:697\n567#1:698,2\n567#1:700\n567#1:701,3\n663#1:712\n574#1:704,7\n*E\n"})
    public static final class Sampler implements ForegroundDetector.Listener, NotificationCenter.NotificationCenterDelegate {
        private final long[] cpuMs;
        private Episode episode;
        private long lastCpuMs;
        private long lastRealtime;
        private long lastRxBytes;
        private int lastState;
        private long lastTxBytes;
        private NotificationCenter.ObserversGroup observers;
        private final DispatchQueue queue;
        private HealthStats recentHealth;
        private long recentHealthRealtime;
        private final long[] rxBytes;
        private final Runnable sampleRunnable;
        private final ArrayDeque<JSONObject> samples;
        private JSONObject startBattery;
        private HealthStats startHealth;
        private final long startedAt;
        private volatile String summary;
        private final HashMap<String, long[]> threadMs;
        private HashMap<Integer, ThreadStat> threads;
        private final long[] txBytes;
        private final long[] wallMs;

        public static void $r8$lambda$8INNKAQux6GXlf2aMK87Ll_uRVI(Sampler r0) {
            r0.sample();
        }

        /* renamed from: $r8$lambda$E-VTY6NAYOMQOkky9ZN8dYzdoOw, reason: not valid java name */
        public static void m1251$r8$lambda$EVTY6NAYOMQOkky9ZN8dYzdoOw(Sampler r1) {
            NotificationCenter.ObserversGroup r0 = r1.observers;
            if (r0 == null) goto L5;
            r0.removeAllObservers();
        L5:
            r1.observers = null;
        }

        public static void $r8$lambda$NjQid2hKNihO_YcjNN6UibHKtD0() {
            LoadMonitor.access$showPendingAlert(LoadMonitor.INSTANCE);
        }

        /* renamed from: $r8$lambda$R-MRcxRWS30DFE1iO256K0agQLQ, reason: not valid java name */
        public static void m1252$r8$lambda$RMRcxRWS30DFE1iO256K0agQLQ(Sampler r2) {
            r2.observers = NotificationCenter.getGlobalInstance().createObserversGroup(r2).add(NotificationCenter.screenStateChanged);
        }

        public static void $r8$lambda$glfliRZC8QH3MoG1_ZtmW_f9wTg(Sampler r3) {
            r3.startHealth = r3.recentHealthStats();
            LoadMonitor r0 = LoadMonitor.INSTANCE;
            r3.startBattery = LoadMonitor.access$batteryJson(r0);
            r3.lastRealtime = SystemClock.elapsedRealtime();
            r3.lastCpuMs = Process.getElapsedCpuTime();
            r3.lastRxBytes = LoadMonitor.access$uidRxBytes(r0);
            r3.lastTxBytes = LoadMonitor.access$uidTxBytes(r0);
            r3.threads = LoadMonitor.access$readThreads(r0);
            r3.updateState();
            r3.scheduleNext();
        }

        public static void $r8$lambda$kogUcZhXgMzzA6XabLRzcV5MgzI(Sampler r0) {
            r0.sample();
            r0.scheduleNext();
        }

        public static void $r8$lambda$skf2wH9h9ICrpnKHS9Si8q7MxEE(Sampler r0) {
            r0.sample();
        }

        /* renamed from: $r8$lambda$ux4TTTD-F0B74u88CV9Sz2UU4ko, reason: not valid java name */
        public static void m1253$r8$lambda$ux4TTTDF0B74u88CV9Sz2UU4ko() {
            LoadMonitor.access$showPendingAlert(LoadMonitor.INSTANCE);
        }

        public static void $r8$lambda$xrMe0cjs6B9Bs_6qQChp3wImB6I(Sampler r0) {
            r0.sample();
        }

        public Sampler() {
            DispatchQueue r0 = new DispatchQueue("loadMonitorQueue");
            this.queue = r0;
            this.sampleRunnable = new LoadMonitor$Sampler$$ExternalSyntheticLambda3(this);
            this.startedAt = System.currentTimeMillis();
            this.lastRxBytes = -1;
            this.lastTxBytes = -1;
            this.threads = new HashMap();
            this.wallMs = new long[LoadMonitor.access$getSTATE_NAMES$p().length];
            this.cpuMs = new long[LoadMonitor.access$getSTATE_NAMES$p().length];
            this.rxBytes = new long[LoadMonitor.access$getSTATE_NAMES$p().length];
            this.txBytes = new long[LoadMonitor.access$getSTATE_NAMES$p().length];
            this.threadMs = new HashMap();
            this.samples = new ArrayDeque();
            ForegroundDetector r1 = ForegroundDetector.getInstance();
            if (r1 == null) goto L5;
            r1.addListener(this);
        L5:
            AndroidUtilities.runOnUIThread(new LoadMonitor$Sampler$$ExternalSyntheticLambda4(this));
            r0.postRunnable(new LoadMonitor$Sampler$$ExternalSyntheticLambda5(this));
        }

        private final void checkEpisode(long r17, long r19, long r21, long r23, Map<Integer, ThreadStat> r25) {
            Episode r2 = this.episode;
            if (r2 == null) goto L49;
            long r3 = r17 - r2.getStartRealtime();
            if (r2.getReported() == false) goto L8;
            return;
        L8:
            if (r3 < 300000) goto L50;
            long r5 = r19 - r2.getCpuMs();
            long r7 = 100 * r5;
            LoadMonitor r9 = LoadMonitor.INSTANCE;
            if (r7 < (r9.getCpuPercent() * r3)) goto L51;
            r2.setReported(true);
            Result.Companion r8 = Result.Companion;     // Catch: Throwable -> L16
            Object r0 = Result.m2018constructorimpl(LoadMonitor.access$writeReport(r9, buildReport("background"), "background"));     // Catch: Throwable -> L16
        L18:
            Throwable r1 = Result.m2021exceptionOrNullimpl(r0);
            if (r1 == null) goto L22;
            FileLog.e(r1);
        L22:
            if (Result.m2024isFailureimpl(r0) == false) goto L24;
            r0 = null;
        L24:
            File r02 = (File) r0;
            if (r02 == null) goto L52;
            long r10 = -1;
            if (r21 >= 0) goto L30;
        L32:
            long r12 = -1;
        L34:
            if (r23 >= 0) goto L36;
        L38:
            LoadMonitor r13 = LoadMonitor.INSTANCE;
            long r212 = r12;
            LoadMonitor.access$setPendingAlert$p(new PendingAlert(r02, LoadMonitor.access$describeEpisode(r13, r3, r5, r212, r10, LoadMonitor.access$topThreads(r13, LoadMonitor.access$threadDeltas(r13, r2.getThreads(), r25), 3))));
            ForegroundDetector r03 = ForegroundDetector.getInstance();
            if (r03 != null) goto L41;
            return;
        L41:
            if (r03.isForeground() != true) goto L48;
            AndroidUtilities.runOnUIThread(new LoadMonitor$Sampler$$ExternalSyntheticLambda8());
            return;
        L48:
            return;
        L36:
            if (r2.getTxBytes() < 0) goto L38;
            r10 = r23 - r2.getTxBytes();
            goto L38
        L30:
            if (r2.getRxBytes() < 0) goto L32;
            r12 = r21 - r2.getRxBytes();
            goto L34
        L52:
            return;
        L16:
            th = move-exception;
            Result.Companion r14 = Result.Companion;
            r0 = Result.m2018constructorimpl(ResultKt.createFailure(th));
            goto L18
        L51:
            return;
        L50:
            return;
        }

        private final HealthStats recentHealthStats() {
            long r0 = SystemClock.elapsedRealtime();
            if (this.recentHealth != null) goto L5;
        L6:
            this.recentHealth = LoadMonitor.access$takeHealthStats(LoadMonitor.INSTANCE);
            this.recentHealthRealtime = r0;
        L8:
            return this.recentHealth;
        L5:
            if ((r0 - this.recentHealthRealtime) <= 60000) goto L8;
            goto L6
        }

        private final void sample() {
            long r1 = SystemClock.elapsedRealtime();
            long r3 = Process.getElapsedCpuTime();
            LoadMonitor r5 = LoadMonitor.INSTANCE;
            long r6 = LoadMonitor.access$uidRxBytes(r5);
            long r8 = LoadMonitor.access$uidTxBytes(r5);
            HashMap<Integer, ThreadStat> r10 = LoadMonitor.access$readThreads(r5);
            int r11 = this.lastState;
            long r12 = r1 - this.lastRealtime;
            long r14 = r3 - this.lastCpuMs;
            if (r6 < 0) goto L7;
            long r18 = r8;
            long r82 = this.lastRxBytes;
            if (r82 < 0) goto L8;
            long r83 = r6 - r82;
        L10:
            if (r18 < 0) goto L15;
            long r20 = r6;
            long r62 = this.lastTxBytes;
            if (r62 < 0) goto L16;
            long r63 = r18 - r62;
        L17:
            HashMap r112 = LoadMonitor.access$threadDeltas(r5, this.threads, r10);
            long r23 = r14 - CollectionsKt.sumOfLong(r112.values());
            if (r23 <= 0) goto L20;
            HashMap<Integer, ThreadStat> r16 = r10;
            long r25 = r3;
            r112.put("<unattributed>", Long.valueOf(r23));
        L21:
            List r4 = LoadMonitor.access$topThreads(r5, r112, 3);
            ArrayList r102 = new ArrayList();
            Iterator r42 = r4.iterator();
        L23:
            if (r42.hasNext() == false) goto L28;
            Object r32 = r42.next();
            if (((Number) ((Pair) r32).getSecond()).longValue() < 200) goto L23;
            r102.add(r32);
            goto L23
        L28:
            ArrayList r33 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r102, 10));
            int r43 = r102.size();
            int r2 = 0;
        L29:
            if (r2 >= r43) goto L31;
            Object r27 = r102.get(r2);
            r2 = r2 + 1;
            r33.add((String) ((Pair) r27).getFirst());
            goto L29
        L31:
            LoadMonitor.access$resolveOrigins(r5, r33);
            long[] r13 = this.wallMs;
            r13[r11] = r13[r11] + r12;
            long[] r15 = this.cpuMs;
            r15[r11] = r15[r11] + r14;
            long[] r17 = this.rxBytes;
            r17[r11] = r17[r11] + r83;
            long[] r19 = this.txBytes;
            r19[r11] = r19[r11] + r63;
            Iterator r110 = r112.entrySet().iterator();
        L33:
            if (r110.hasNext() == false) goto L39;
            Map.Entry r22 = (Map.Entry) r110.next();
            String r34 = (String) r22.getKey();
            long r44 = ((Number) r22.getValue()).longValue();
            HashMap<String, long[]> r24 = this.threadMs;
            long[] r103 = r24.get(r34);
            if (r103 != null) goto L37;
            r103 = new long[LoadMonitor.access$getSTATE_NAMES$p().length];
            r24.put(r34, r103);
        L37:
            long[] r104 = r103;
            r104[r11] = r104[r11] + r44;
            goto L33
        L39:
            if (r12 < 1000) goto L44;
            ArrayDeque<JSONObject> r111 = this.samples;
            JSONObject r26 = new JSONObject().put("t", System.currentTimeMillis()).put("state", LoadMonitor.access$getSTATE_NAMES$p()[r11]).put("wallMs", r12).put("cpuMs", r14).put("rxBytes", r83).put("txBytes", r63);
            LoadMonitor r35 = LoadMonitor.INSTANCE;
            r111.addLast(r26.put("plugged", LoadMonitor.access$isPlugged(r35)).put("threads", LoadMonitor.threadsJson$default(r35, LoadMonitor.access$topThreads(r35, r112, 3), false, 2, null)));
        L42:
            if (this.samples.size() <= 240) goto L44;
            this.samples.removeFirst();
        L44:
            this.lastRealtime = r1;
            long r36 = r25;
            this.lastCpuMs = r36;
            long r52 = r20;
            this.lastRxBytes = r52;
            long r7 = r18;
            this.lastTxBytes = r7;
            HashMap<Integer, ThreadStat> r9 = r16;
            this.threads = r9;
            this.summary = LoadMonitor.access$formatDuration(LoadMonitor.INSTANCE, ArraysKt.sum(this.cpuMs)) + " CPU, " + AndroidUtilities.formatFileSize(ArraysKt.sum(this.rxBytes) + ArraysKt.sum(this.txBytes));
            checkEpisode(r1, r36, r52, r7, r9);
            updateState();
            return;
        L20:
            r25 = r3;
            r16 = r10;
        L16:
            r63 = 0;
            goto L17
        L15:
            r20 = r6;
        L8:
            r83 = 0;
            goto L10
        L7:
            r18 = r8;
            goto L8
        }

        private final void scheduleNext() {
            this.queue.cancelRunnable(this.sampleRunnable);
            this.queue.postRunnable(this.sampleRunnable, 60000);
        }

        private final void updateState() {
            int r0 = LoadMonitor.access$currentState(LoadMonitor.INSTANCE);
            this.lastState = r0;
            if (r0 != 0) goto L7;
            this.episode = null;
            return;
        L7:
            if (this.episode != null) goto L10;
            this.episode = new Episode(System.currentTimeMillis(), this.lastRealtime, this.lastCpuMs, this.lastRxBytes, this.lastTxBytes, this.threads, recentHealthStats());
            return;
        }

        public final JSONObject buildReport(String r22) {
            JSONObject r1 = LoadMonitor.access$baseReport(LoadMonitor.INSTANCE, r22);
            JSONObject r2 = new JSONObject();
            int r3 = LoadMonitor.access$getSTATE_NAMES$p().length;
            int r5 = 0;
        L4:
            if (r5 >= r3) goto L6;
            r2.put(LoadMonitor.access$getSTATE_NAMES$p()[r5], new JSONObject().put("wallMs", this.wallMs[r5]).put("cpuMs", this.cpuMs[r5]).put("rxBytes", this.rxBytes[r5]).put("txBytes", this.txBytes[r5]));
            r5 = r5 + 1;
            goto L4
        L6:
            JSONArray r32 = new JSONArray();
            Iterator r52 = CollectionsKt.take(CollectionsKt.sortedWith(this.threadMs.entrySet(), new LoadMonitor$Sampler$buildReport$$inlined$sortedByDescending$1()), 30).iterator();
        L8:
            if (r52.hasNext() == false) goto L13;
            Map.Entry r11 = (Map.Entry) r52.next();
            String r12 = (String) r11.getKey();
            long[] r112 = (long[]) r11.getValue();
            JSONObject r122 = new JSONObject().put("name", r12).put("cpuMs", ArraysKt.sum(r112)).put("at", LoadMonitor.access$originOf(LoadMonitor.INSTANCE, r12));
            int r13 = LoadMonitor.access$getSTATE_NAMES$p().length;
            int r14 = 0;
        L10:
            if (r14 >= r13) goto L12;
            r122.put(LoadMonitor.access$getSTATE_NAMES$p()[r14], r112[r14]);
            r14 = r14 + 1;
            r52 = r52;
            goto L10
        L12:
            r32.put(r122);
            goto L8
        L13:
            r1.put("monitor", new JSONObject().put("startedAt", this.startedAt).put("sampleIntervalMs", 60000).put("batteryAtStart", this.startBattery).put("states", r2).put("threads", r32).put("samples", new JSONArray(this.samples)));
            Episode r23 = this.episode;
            if (r23 == null) goto L29;
            long r113 = SystemClock.elapsedRealtime();
            long r132 = Process.getElapsedCpuTime();
            LoadMonitor r33 = LoadMonitor.INSTANCE;
            long r15 = LoadMonitor.access$uidRxBytes(r33);
            long r17 = LoadMonitor.access$uidTxBytes(r33);
            JSONObject r53 = new JSONObject().put("startedAt", r23.getStartedAt()).put("wallMs", r113 - r23.getStartRealtime()).put("cpuMs", r132 - r23.getCpuMs());
            long r114 = -1;
            if (r15 >= 0) goto L18;
        L20:
            long r133 = -1;
        L21:
            JSONObject r54 = r53.put("rxBytes", r133);
            if (r17 >= 0) goto L24;
        L26:
            r1.put("episode", r54.put("txBytes", r114).put("threads", LoadMonitor.access$threadsJson(r33, LoadMonitor.access$topThreads(r33, LoadMonitor.access$threadDeltas(r33, r23.getThreads(), LoadMonitor.access$readThreads(r33)), 30), true)));
            HealthStats r24 = r23.getHealth();
            if (r24 == null) goto L29;
            r1.put("healthEpisodeStart", LoadMonitor.access$healthJson(r33, r24));
            goto L29
        L24:
            if (r23.getTxBytes() < 0) goto L26;
            r114 = r17 - r23.getTxBytes();
            goto L26
        L18:
            if (r23.getRxBytes() < 0) goto L20;
            r133 = r15 - r23.getRxBytes();
        L29:
            HealthStats r0 = this.startHealth;
            if (r0 == null) goto L32;
            r1.put("healthStart", LoadMonitor.access$healthJson(LoadMonitor.INSTANCE, r0));
        L32:
            return r1;
        }

        public final JSONObject captureReport(String r1) {
            sample();
            return buildReport(r1);
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 != NotificationCenter.screenStateChanged) goto L6;
            this.queue.postRunnable(new LoadMonitor$Sampler$$ExternalSyntheticLambda6(this));
            return;
        }

        public final DispatchQueue getQueue() {
            return this.queue;
        }

        public final String getSummary() {
            return this.summary;
        }

        @Override // org.telegram.ui.Components.ForegroundDetector.Listener
        public void onBecameBackground() {
            this.queue.postRunnable(new LoadMonitor$Sampler$$ExternalSyntheticLambda2(this));
        }

        @Override // org.telegram.ui.Components.ForegroundDetector.Listener
        public void onBecameForeground() {
            this.queue.postRunnable(new LoadMonitor$Sampler$$ExternalSyntheticLambda0(this));
            AndroidUtilities.runOnUIThread(new LoadMonitor$Sampler$$ExternalSyntheticLambda1(), 1000);
        }

        public final void stop() {
            ForegroundDetector r0 = ForegroundDetector.getInstance();
            if (r0 == null) goto L5;
            r0.removeListener(this);
        L5:
            AndroidUtilities.runOnUIThread(new LoadMonitor$Sampler$$ExternalSyntheticLambda7(this));
            this.queue.cleanupQueue();
            this.queue.recycle();
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;", _UrlKt.FRAGMENT_ENCODE_SET, "name", _UrlKt.FRAGMENT_ENCODE_SET, "ticks", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;J)V", "getName", "()Ljava/lang/String;", "getTicks", "()J", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ThreadStat {
        private final String name;
        private final long ticks;

        public ThreadStat(String r1, long r2) {
            this.name = r1;
            this.ticks = r2;
        }

        public final String getName() {
            return this.name;
        }

        public final long getTicks() {
            return this.ticks;
        }
    }

    /* renamed from: $r8$lambda$EvWZU_-AiIxct8eo7VUHXa2A0bw, reason: not valid java name */
    public static CharSequence m1249$r8$lambda$EvWZU_AiIxct8eo7VUHXa2A0bw(Pair r4) {
        return r4.getFirst() + " " + INSTANCE.formatDuration(((Number) r4.getSecond()).longValue());
    }

    public static void $r8$lambda$G5I7fuj0kLwvbL4wsMtBJkoYexs(Sampler r2, Activity r3) {
        LoadMonitor r0 = INSTANCE;
        Result.Companion r1 = Result.Companion;     // Catch: Throwable -> L9
        if (r2 == null) goto L11;
        JSONObject r22 = r2.captureReport("manual");     // Catch: Throwable -> L9
        if (r22 == null) goto L11;
    L12:
        Object r23 = Result.m2018constructorimpl(r0.writeReport(r22, "manual"));     // Catch: Throwable -> L9
    L15:
        AndroidUtilities.runOnUIThread(new LoadMonitor$$ExternalSyntheticLambda7(r23, r3));
        return;
    L11:
        r22 = r0.baseReport("manual");     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        Result.Companion r02 = Result.Companion;
        r23 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L15
    }

    public static long $r8$lambda$HqwllwjZJtclxFPulNWPmnOfRKg() {
        long r0 = Os.sysconf(OsConstants._SC_CLK_TCK);
        Long r2 = Long.valueOf(r0);
        if (r0 > 0) goto L6;
        r2 = null;
    L6:
        if (r2 != null) goto L8;
        return 100;
    L8:
        return r2.longValue();
    }

    public static void $r8$lambda$NdeRMlyzkcIMr7a3YcyRA_FFvjU(Function0 r4) {
        File[] r0 = INSTANCE.getReportsDir().listFiles();
        if (r0 == null) goto L7;
        int r1 = r0.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L7;
        r0[r2].delete();
        r2 = r2 + 1;
    L7:
        AndroidUtilities.runOnUIThread(new LoadMonitor$$ExternalSyntheticLambda6(r4));
    }

    public static void $r8$lambda$TBAZBRFuhd4VBPd6EhR6HEUFtmc(AlertDialog r0, int r1) {
        INSTANCE.setEnabled(false);
    }

    public static void $r8$lambda$WeeawD0afuH0k272RT_v_fiH5FA(LaunchActivity r0, PendingAlert r1, AlertDialog r2, int r3) {
        INSTANCE.share(r0, r1.getFile());
    }

    /* renamed from: $r8$lambda$eRg4SnlNz0SMf510-JcmUPE_G5g, reason: not valid java name */
    public static void m1250$r8$lambda$eRg4SnlNz0SMf510JcmUPE_G5g(Function0 r0) {
        r0.invoke();
    }

    public static File $r8$lambda$gUlE6fLgWq1L7vM9V_8T3RhwJmA() {
        return DebugFilesKt.debugFilesDir("loadreports");
    }

    public static void $r8$lambda$yclvq93EyksPCxohhOabqVuRpcY(Object r3, Activity r4) {
        if (Result.m2025isSuccessimpl(r3) == false) goto L5;
        INSTANCE.share(r4, (File) r3);
    L5:
        Throwable r32 = Result.m2021exceptionOrNullimpl(r3);
        if (r32 == null) goto L9;
        FileLog.e(r32);
        BulletinFactory.global().createSimpleBulletin(R.raw.error, "Load report failed: " + r32).show();
        return;
    }

    static {
        INSTANCE = new LoadMonitor();
        STATE_NAMES = new String[]{"foreground", "background", "screenOff"};
        SKIPPED_FRAMES = new String[]{"java.", "javax.", "kotlin.", "android.", "com.android.", "dalvik.", "libcore.", "sun.", "jdk.", "de.robv.android.xposed.", LoadMonitor.class.getName()};
        healthKeyNames = new HashMap();
        threadOrigins = new HashMap();
        originAttempts = new HashMap();
        reportsDir$delegate = LazyKt.lazy(new LoadMonitor$$ExternalSyntheticLambda0());
        clockTicks$delegate = LazyKt.lazy(new LoadMonitor$$ExternalSyntheticLambda1());
    }

    private LoadMonitor() {
    }

    public static final /* synthetic */ JSONObject access$baseReport(LoadMonitor r0, String r1) {
        return r0.baseReport(r1);
    }

    public static final /* synthetic */ JSONObject access$batteryJson(LoadMonitor r0) {
        return r0.batteryJson();
    }

    public static final /* synthetic */ int access$currentState(LoadMonitor r0) {
        return r0.currentState();
    }

    public static final /* synthetic */ String access$describeEpisode(LoadMonitor r0, long r1, long r3, long r5, long r7, List r9) {
        return r0.describeEpisode(r1, r3, r5, r7, r9);
    }

    public static final /* synthetic */ String access$formatDuration(LoadMonitor r0, long r1) {
        return r0.formatDuration(r1);
    }

    public static final /* synthetic */ String[] access$getSTATE_NAMES$p() {
        return STATE_NAMES;
    }

    public static final /* synthetic */ JSONObject access$healthJson(LoadMonitor r0, HealthStats r1) {
        return r0.healthJson(r1);
    }

    public static final /* synthetic */ Boolean access$isPlugged(LoadMonitor r0) {
        return r0.isPlugged();
    }

    public static final /* synthetic */ String access$originOf(LoadMonitor r0, String r1) {
        return r0.originOf(r1);
    }

    public static final /* synthetic */ HashMap access$readThreads(LoadMonitor r0) {
        return r0.readThreads();
    }

    public static final /* synthetic */ void access$resolveOrigins(LoadMonitor r0, List r1) {
        r0.resolveOrigins(r1);
    }

    public static final /* synthetic */ void access$setPendingAlert$p(PendingAlert r0) {
        pendingAlert = r0;
    }

    public static final /* synthetic */ void access$showPendingAlert(LoadMonitor r0) {
        r0.showPendingAlert();
    }

    public static final /* synthetic */ HealthStats access$takeHealthStats(LoadMonitor r0) {
        return r0.takeHealthStats();
    }

    public static final /* synthetic */ HashMap access$threadDeltas(LoadMonitor r0, Map r1, Map r2) {
        return r0.threadDeltas(r1, r2);
    }

    public static final /* synthetic */ JSONArray access$threadsJson(LoadMonitor r0, List r1, boolean r2) {
        return r0.threadsJson(r1, r2);
    }

    public static final /* synthetic */ List access$topThreads(LoadMonitor r0, Map r1, int r2) {
        return r0.topThreads(r1, r2);
    }

    public static final /* synthetic */ long access$uidRxBytes(LoadMonitor r2) {
        return r2.uidRxBytes();
    }

    public static final /* synthetic */ long access$uidTxBytes(LoadMonitor r2) {
        return r2.uidTxBytes();
    }

    public static final /* synthetic */ File access$writeReport(LoadMonitor r0, JSONObject r1, String r2) {
        return r0.writeReport(r1, r2);
    }

    private final String appFrame(StackTraceElement[] r11) {
        int r10 = r11.length;
        int r1 = 0;
    L4:
        if (r1 >= r10) goto L14;
        StackTraceElement r3 = r11[r1];
        String[] r4 = SKIPPED_FRAMES;
        int r5 = r4.length;
        int r6 = 0;
    L7:
        if (r6 >= r5) goto L13;
        if (StringsKt.startsWith$default(r3.getClassName(), r4[r6], false, 2, null) == true) goto L10;
        r6 = r6 + 1;
        goto L7
    L10:
        r1 = r1 + 1;
        goto L4
    L13:
        return StringsKt.substringAfterLast$default(r3.getClassName(), '.', null, 2, null) + "." + r3.getMethodName() + ":" + r3.getLineNumber();
    L14:
        return null;
    }

    private final JSONObject baseReport(String r12) {
        Context r0 = ApplicationLoader.applicationContext;
        Object r1 = r0.getSystemService("power");
        JSONObject r3 = null;
        if ((r1 instanceof PowerManager) == false) goto L5;
        PowerManager r13 = (PowerManager) r1;
    L6:
        HashMap r2 = new HashMap();
        Iterator<ThreadStat> r4 = readThreads().values().iterator();
    L8:
        if (r4.hasNext() == false) goto L14;
        ThreadStat r5 = r4.next();
        String r6 = r5.getName();
        Long r7 = (Long) r2.get(r5.getName());
        if (r7 == null) goto L12;
        long r72 = r7.longValue();
    L13:
        r2.put(r6, Long.valueOf(r72 + ticksToMs(r5.getTicks())));
        goto L8
    L12:
        r72 = 0;
        goto L13
    L14:
        List<Pair<String, Long>> r22 = topThreads(r2, 30);
        List<Pair<String, Long>> r42 = r22;
        List<String> r52 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r42, 10));
        Iterator<T> r43 = r42.iterator();
    L16:
        if (r43.hasNext() == false) goto L18;
        r52.add((String) ((Pair) r43.next()).getFirst());
        goto L16
    L18:
        resolveOrigins(r52);
        JSONObject r122 = new JSONObject().put("format", 1).put("trigger", r12).put("createdAt", System.currentTimeMillis()).put(Common.ASSET_APP, new JSONObject().put("version", BuildVars.BUILD_VERSION_STRING).put("code", BuildVars.BUILD_VERSION).put("debug", BuildVars.DEBUG_VERSION)).put("device", new JSONObject().put("manufacturer", Build.MANUFACTURER).put("model", Build.MODEL).put("sdk", Build.VERSION.SDK_INT).put("cores", Runtime.getRuntime().availableProcessors())).put("battery", batteryJson());
        JSONObject r44 = new JSONObject().put("accounts", UserConfig.getActivatedAccountsCount()).put("proxy", SharedConfig.isProxyEnabled()).put("logs", BuildVars.LOGS_ENABLED);
        boolean r53 = false;
        if (r13 != null) goto L21;
    L23:
        boolean r73 = false;
    L24:
        JSONObject r45 = r44.put("powerSave", r73);
        if (r13 != null) goto L27;
    L29:
        JSONObject r123 = r122.put("settings", r45.put("ignoringBatteryOptimizations", r53)).put("process", new JSONObject().put("uptimeMs", SystemClock.elapsedRealtime() - Process.getStartElapsedRealtime()).put("cpuMs", Process.getElapsedCpuTime()).put("state", STATE_NAMES[currentState()]).put("threads", threadsJson(r22, true)));
        HealthStats r11 = takeHealthStats();
        if (r11 == null) goto L33;
        r3 = INSTANCE.healthJson(r11);
    L33:
        return r123.put("healthNow", r3);
    L27:
        if (r13.isIgnoringBatteryOptimizations(r0.getPackageName()) != true) goto L29;
        r53 = true;
        goto L29
    L21:
        if (r13.isPowerSaveMode() != true) goto L23;
        r73 = true;
        goto L24
    L5:
        r13 = null;
        goto L6
    }

    private final JSONObject batteryJson() {
        return new JSONObject().put("level", LiteMode.getBatteryLevel()).put("plugged", isPlugged());
    }

    private final int currentState() {
        Object r2 = ApplicationLoader.applicationContext.getSystemService("power");
        if ((r2 instanceof PowerManager) == false) goto L5;
        PowerManager r22 = (PowerManager) r2;
    L6:
        if (r22 != null) goto L8;
    L11:
        ForegroundDetector r23 = ForegroundDetector.getInstance();
        int r0 = 0;
        if (r23 == null) goto L17;
        if (r23.isForeground() != true) goto L17;
        r0 = 1;
    L17:
        return r0 ^ 1;
    L8:
        if (r22.isInteractive() == true) goto L11;
        return 2;
    L5:
        r22 = null;
        goto L6
    }

    private final String describeEpisode(long r11, long r13, long r15, long r17, List<Pair<String, Long>> r19) {
        StringBuilder r0 = new StringBuilder();
        r0.append("In " + formatDuration(r11) + " in the background, the app used " + formatPercent(r13, r11) + " of one core: ");
        String r10 = formatDuration(r13);
        StringBuilder r112 = new StringBuilder();
        r112.append(r10);
        r112.append(" of CPU");
        r0.append(r112.toString());
        if (r15 >= 0) goto L5;
    L7:
        r0.append('.');
        if (r19.isEmpty() == true) goto L10;
        r0.append("\n\nBusiest threads: ");
        r0.append(CollectionsKt.joinToString$default(r19, ", ", null, null, 0, null, new LoadMonitor$$ExternalSyntheticLambda8(), 30, null));
        r0.append('.');
    L10:
        r0.append("\n\nThe report holds thread names, traffic counters and battery stats, no message content.");
        return r0.toString();
    L5:
        if (r17 < 0) goto L7;
        r0.append(", " + AndroidUtilities.formatFileSize(r15) + " received, " + AndroidUtilities.formatFileSize(r17) + " sent");
        goto L7
    }

    private final String formatDuration(long r7) {
        long r2 = r7 / 1000;
        if (r7 >= 1000) goto L7;
        return r7 + "ms";
    L7:
        if (r2 >= 60) goto L11;
        return r2 + "s";
    L11:
        if (r2 >= 3600) goto L15;
        return (r2 / 60) + "m " + (r2 % 60) + "s";
    L15:
        return (r2 / 3600) + "h " + ((r2 % 3600) / 60) + "m";
    }

    private final String formatPercent(long r3, long r5) {
        StringCompanionObject r2 = StringCompanionObject.INSTANCE;
        Locale r22 = Locale.US;
        if (r5 <= 0) goto L5;
        double r32 = (r3 * 100.0d) / r5;
    L7:
        return String.format(r22, "%.1f%%", Arrays.copyOf(new Object[]{Double.valueOf(r32)}, 1));
    L5:
        r32 = 0.0d;
        goto L7
    }

    private final long getClockTicks() {
        return ((Number) clockTicks$delegate.getValue()).longValue();
    }

    private final JSONObject healthJson(HealthStats r15) {
        String r0 = r15.getDataType();
        JSONObject r1 = new JSONObject().put("type", r0);
        int r3 = 0;
        if (r15.getMeasurementKeyCount() <= 0) goto L9;
        JSONObject r2 = new JSONObject();
        int r4 = r15.getMeasurementKeyCount();
        int r5 = 0;
    L5:
        if (r5 >= r4) goto L7;
        int r6 = r15.getMeasurementKeyAt(r5);
        r2.put(healthKeyName(r0, r6), r15.getMeasurement(r6));
        r5 = r5 + 1;
        goto L5
    L7:
        r1.put("measurements", r2);
    L9:
        if (r15.getTimerKeyCount() <= 0) goto L15;
        JSONObject r22 = new JSONObject();
        int r42 = r15.getTimerKeyCount();
        int r52 = 0;
    L11:
        if (r52 >= r42) goto L13;
        int r62 = r15.getTimerKeyAt(r52);
        r22.put(healthKeyName(r0, r62), new JSONArray().put(r15.getTimerCount(r62)).put(r15.getTimerTime(r62)));
        r52 = r52 + 1;
        goto L11
    L13:
        r1.put("timers", r22);
    L15:
        if (r15.getMeasurementsKeyCount() <= 0) goto L25;
        JSONObject r23 = new JSONObject();
        int r43 = r15.getMeasurementsKeyCount();
        int r53 = 0;
    L17:
        if (r53 >= r43) goto L23;
        int r63 = r15.getMeasurementsKeyAt(r53);
        JSONObject r7 = new JSONObject();
        Iterator<Map.Entry<String, Long>> r8 = r15.getMeasurements(r63).entrySet().iterator();
    L20:
        if (r8.hasNext() == false) goto L22;
        Map.Entry<String, Long> r9 = r8.next();
        r7.put(r9.getKey(), r9.getValue().longValue());
        goto L20
    L22:
        r23.put(healthKeyName(r0, r63), r7);
        r53 = r53 + 1;
        goto L17
    L23:
        r1.put("measurementMaps", r23);
    L25:
        if (r15.getTimersKeyCount() <= 0) goto L35;
        JSONObject r24 = new JSONObject();
        int r44 = r15.getTimersKeyCount();
        int r54 = 0;
    L27:
        if (r54 >= r44) goto L33;
        int r64 = r15.getTimersKeyAt(r54);
        JSONObject r72 = new JSONObject();
        Iterator<Map.Entry<String, TimerStat>> r82 = r15.getTimers(r64).entrySet().iterator();
    L30:
        if (r82.hasNext() == false) goto L32;
        Map.Entry<String, TimerStat> r92 = r82.next();
        String r10 = r92.getKey();
        TimerStat r93 = r92.getValue();
        r72.put(r10, new JSONArray().put(r93.getCount()).put(r93.getTime()));
        goto L30
    L32:
        r24.put(healthKeyName(r0, r64), r72);
        r54 = r54 + 1;
        goto L27
    L33:
        r1.put("timerMaps", r24);
    L35:
        if (r15.getStatsKeyCount() <= 0) goto L44;
        JSONObject r25 = new JSONObject();
        int r45 = r15.getStatsKeyCount();
    L37:
        if (r3 >= r45) goto L43;
        int r55 = r15.getStatsKeyAt(r3);
        JSONObject r65 = new JSONObject();
        Iterator<Map.Entry<String, HealthStats>> r73 = r15.getStats(r55).entrySet().iterator();
    L40:
        if (r73.hasNext() == false) goto L42;
        Map.Entry<String, HealthStats> r83 = r73.next();
        r65.put(r83.getKey(), healthJson(r83.getValue()));
        goto L40
    L42:
        r25.put(healthKeyName(r0, r55), r65);
        r3 = r3 + 1;
        goto L37
    L43:
        r1.put("stats", r25);
    L44:
        return r1;
    }

    private final String healthKeyName(String r10, int r11) {
        HashMap<String, Map<Integer, String>> r0 = healthKeyNames;
        monitor-enter(r0);
        Map<Integer, String> r1 = r0.get(r10);     // Catch: Throwable -> L30
        if (r1 == null) goto L40;
    L32:
        Map<Integer, String> r12 = r1;     // Catch: Throwable -> L30
        monitor-exit(r0);
        String r9 = r12.get(Integer.valueOf(r11));
        if (r9 == null) goto L37;
        return r9;
    L37:
        return String.valueOf(r11);
    L40:
        Result.Companion r13 = Result.Companion;     // Catch: Throwable -> L17
        int r4 = 0;
        if (StringsKt.contains$default(r10, '.', false, 2, null) == false) goto L9;
        String r92 = r10;
    L10:
        Field[] r93 = Class.forName(r92).getFields();     // Catch: Throwable -> L17
        ArrayList r14 = new ArrayList();     // Catch: Throwable -> L17
        int r2 = r93.length;     // Catch: Throwable -> L17
        int r5 = 0;
    L11:
        if (r5 >= r2) goto L20;
        Field r6 = r93[r5];     // Catch: Throwable -> L17
        if (Modifier.isStatic(r6.getModifiers()) == false) goto L19;
        if (Intrinsics.areEqual(r6.getType(), Integer.TYPE) == false) goto L19;
        r14.add(r6);     // Catch: Throwable -> L17
    L19:
        r5 = r5 + 1;     // Catch: Throwable -> L17
        goto L11
    L20:
        LinkedHashMap r22 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(r14, 10)), 16));     // Catch: Throwable -> L17
        int r94 = r14.size();     // Catch: Throwable -> L17
    L21:
        if (r4 >= r94) goto L23;
        Object r52 = r14.get(r4);     // Catch: Throwable -> L17
        r4 = r4 + 1;     // Catch: Throwable -> L17
        Field r53 = (Field) r52;     // Catch: Throwable -> L17
        Pair r54 = TuplesKt.to(Integer.valueOf(r53.getInt(null)), r53.getName());     // Catch: Throwable -> L17
        r22.put(r54.getFirst(), r54.getSecond());     // Catch: Throwable -> L17
        goto L21
    L23:
        Object r95 = Result.m2018constructorimpl(r22);     // Catch: Throwable -> L17
    L26:
        Object r15 = MapsKt.emptyMap();     // Catch: Throwable -> L30
        boolean r23 = Result.m2024isFailureimpl(r95);     // Catch: Throwable -> L30
        Object r96 = r95;
        if (r23 == false) goto L29;
        r96 = r15;
    L29:
        r1 = (Map) r96;     // Catch: Throwable -> L30
        r0.put(r10, r1);     // Catch: Throwable -> L30
        goto L32
    L9:
        r92 = "android.os.health." + r10;     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        Result.Companion r16 = Result.Companion;     // Catch: Throwable -> L30
        r95 = Result.m2018constructorimpl(ResultKt.createFailure(th));     // Catch: Throwable -> L30
    L30:
        th = move-exception;
        throw th;
    }

    @JvmStatic
    public static final void init() {
        if (DebugConfig.getLoadMonitorEnabled() == false) goto L6;
        sampler = new Sampler();
        return;
    }

    private final Boolean isPlugged() {
        Object r3 = null;
        Result.Companion r0 = Result.Companion;     // Catch: Throwable -> L9
        Intent r02 = ApplicationLoader.applicationContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));     // Catch: Throwable -> L9
        if (r02 == null) goto L11;
        boolean r2 = false;
        if (r02.getIntExtra("plugged", 0) == 0) goto L8;
        r2 = true;
    L8:
        Boolean r03 = Boolean.valueOf(r2);     // Catch: Throwable -> L9
    L12:
        Object r04 = Result.m2018constructorimpl(r03);     // Catch: Throwable -> L9
    L16:
        if (Result.m2024isFailureimpl(r04) == true) goto L20;
        r3 = r04;
    L20:
        return (Boolean) r3;
    L11:
        r03 = null;
    L9:
        th = move-exception;
        Result.Companion r1 = Result.Companion;
        r04 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L16
    }

    private final String originOf(String r1) {
        HashMap<String, String> r0 = threadOrigins;
        monitor-enter(r0);
        String r12 = r0.get(r1);     // Catch: Throwable -> L7
        monitor-exit(r0);
        return r12;
    L7:
        th = move-exception;
        throw th;
    }

    private final ThreadStat parseStat(String r11) {
        int r10 = StringsKt.indexOf$default(r11, '(', 0, false, 6, null);
        int r112 = StringsKt.lastIndexOf$default(r11, ')', 0, false, 6, null);
        if (r10 < 0) goto L17;
        if (r112 <= r10) goto L18;
        int r2 = r112 + 2;
        if (r2 >= r11.length()) goto L21;
        List r3 = StringsKt.split$default(r11.substring(r2), new char[]{' '}, false, 0, 6, null);
        if (r3.size() >= 13) goto L11;
        return null;
    L11:
        Long r4 = StringsKt.toLongOrNull((String) r3.get(11));
        if (r4 == null) goto L19;
        long r42 = r4.longValue();
        Long r32 = StringsKt.toLongOrNull((String) r3.get(12));
        if (r32 != null) goto L16;
        return null;
    L16:
        return new ThreadStat(r11.substring(r10 + 1, r112), r42 + r32.longValue());
    L19:
        return null;
    L21:
        return null;
    L18:
        return null;
    L17:
        return null;
    }

    private final HashMap<Integer, ThreadStat> readThreads() {
        HashMap<Integer, ThreadStat> r0 = new HashMap();
        String[] r1 = new File("/proc/self/task").list();
        if (r1 == null) goto L23;
        byte[] r2 = new byte[1024];
        int r3 = r1.length;
        int r5 = 0;
    L6:
        if (r5 >= r3) goto L23;
        String r6 = r1[r5];
        Integer r7 = StringsKt.toIntOrNull(r6);
        if (r7 == null) goto L22;
        FileInputStream r8 = new FileInputStream("/proc/self/task/" + r6 + "/stat");     // Catch: Exception -> L24
        int r62 = r8.read(r2);     // Catch: Throwable -> L17
        goto L22
    L12:
        CloseableKt.closeFinally(r8, null);     // Catch: Exception -> L24
        if (r62 <= 0) goto L22;
        ThreadStat r63 = parseStat(new String(r2, 0, r62, Charsets.UTF_8));
        if (r63 == null) goto L22;
        r0.put(r7, r63);
    L17:
        th = move-exception;
        throw th;     // Catch: Throwable -> L19
    L19:
        th = move-exception;
        CloseableKt.closeFinally(r8, th);     // Catch: Exception -> L24
        throw th;     // Catch: Exception -> L24
    L22:
        r5 = r5 + 1;
    L23:
        return r0;
    }

    private final void resolveOrigins(List<String> r9) {
        HashMap<String, String> r8 = threadOrigins;
        monitor-enter(r8);
        ArrayList r0 = new ArrayList();     // Catch: Throwable -> L14
        Iterator<T> r92 = r9.iterator();     // Catch: Throwable -> L14
    L5:
        boolean r2 = true;
        if (r92.hasNext() == false) goto L24;
        Object r1 = r92.next();     // Catch: Throwable -> L14
        String r4 = (String) r1;     // Catch: Throwable -> L14
        if (Intrinsics.areEqual(r4, "<unattributed>") == false) goto L10;
    L20:
        r2 = false;
    L21:
        if (r2 == false) goto L5;
        r0.add(r1);     // Catch: Throwable -> L14
        goto L5
    L10:
        if (threadOrigins.containsKey(r4) == true) goto L20;
        Integer r42 = originAttempts.get(r4);     // Catch: Throwable -> L14
        if (r42 == null) goto L16;
        int r43 = r42.intValue();     // Catch: Throwable -> L14
    L18:
        if (r43 >= 8) goto L20;
    L16:
        r43 = 0;
        goto L18
    L24:
        monitor-exit(r8);
        if (r0.isEmpty() == true) goto L91;
        Result.Companion r82 = Result.Companion;     // Catch: Throwable -> L30
        Object r83 = Result.m2018constructorimpl(Thread.getAllStackTraces());     // Catch: Throwable -> L30
    L33:
        if (Result.m2024isFailureimpl(r83) == false) goto L35;
        r83 = null;
    L35:
        Map r84 = (Map) r83;
        if (r84 != null) goto L38;
        return;
    L38:
        HashMap<String, String> r93 = threadOrigins;
        monitor-enter(r93);
        int r12 = r0.size();     // Catch: Throwable -> L45
        int r44 = 0;
    L41:
        if (r44 >= r12) goto L49;
        Object r5 = r0.get(r44);     // Catch: Throwable -> L45
        r44 = r44 + 1;     // Catch: Throwable -> L45
        String r52 = (String) r5;     // Catch: Throwable -> L45
        HashMap<String, Integer> r6 = originAttempts;     // Catch: Throwable -> L45
        Integer r7 = r6.get(r52);     // Catch: Throwable -> L45
        if (r7 == null) goto L47;
        int r72 = r7.intValue();     // Catch: Throwable -> L45
    L48:
        r6.put(r52, Integer.valueOf(r72 + 1));     // Catch: Throwable -> L45
        goto L41
    L47:
        r72 = 0;
        goto L48
    L49:
        Iterator r85 = r84.entrySet().iterator();     // Catch: Throwable -> L45
    L51:
        if (r85.hasNext() == false) goto L60;
        Map.Entry r13 = (Map.Entry) r85.next();     // Catch: Throwable -> L45
        Thread r22 = (Thread) r13.getKey();     // Catch: Throwable -> L45
        StackTraceElement[] r14 = (StackTraceElement[]) r13.getValue();     // Catch: Throwable -> L45
        String r23 = StringsKt.take(r22.getName(), 15);     // Catch: Throwable -> L45
        if (r0.contains(r23) == false) goto L51;
        HashMap<String, String> r3 = threadOrigins;     // Catch: Throwable -> L45
        if (r3.containsKey(r23) == true) goto L51;
        String r15 = INSTANCE.appFrame(r14);     // Catch: Throwable -> L45
        if (r15 == null) goto L51;
        r3.put(r23, r15);     // Catch: Throwable -> L45
        goto L51
    L60:
        Unit r86 = Unit.INSTANCE;     // Catch: Throwable -> L45
        monitor-exit(r93);
        return;
    L45:
        th = move-exception;
        throw th;
    L30:
        th = move-exception;
        Result.Companion r94 = Result.Companion;
        r83 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L33
    L91:
        return;
    L14:
        th = move-exception;
        throw th;
    }

    private final void share(Activity r2, File r3) {
        DebugFilesKt.shareDebugFile(r2, r3, "application/json", "Share load report");
    }

    private final void showPendingAlert() {
        PendingAlert r4 = pendingAlert;
        if (r4 == null) goto L28;
        LaunchActivity r0 = LaunchActivity.instance;
        if (sampler == null) goto L25;
        if (r0 != null) goto L9;
        return;
    L9:
        if (r0.isFinishing() == true) goto L27;
        WeakReference<AlertDialog> r1 = alertRef;
        if (r1 == null) goto L17;
        AlertDialog r12 = r1.get();
        if (r12 == null) goto L17;
        if (r12.isShowing() != true) goto L17;
        return;
    L17:
        pendingAlert = null;
        AlertDialog r42 = new AlertDialog.Builder(r0).setTitle("High background load").setMessage(r4.getMessage()).setPositiveButton("Share", new LoadMonitor$$ExternalSyntheticLambda2(r0, r4)).setNeutralButton("Turn off", new LoadMonitor$$ExternalSyntheticLambda3()).setNegativeButton("Later", null).create();
        alertRef = new WeakReference(r42);
        r42.show();     // Catch: Exception -> L20
        return;
    L20:
        e = move-exception;
        FileLog.e(e);
        return;
    L27:
        return;
    L25:
        return;
    }

    private final HealthStats takeHealthStats() {
        Object r2 = null;
        Result.Companion r0 = Result.Companion;     // Catch: Throwable -> L6
        Object r02 = ApplicationLoader.applicationContext.getSystemService("systemhealth");     // Catch: Throwable -> L6
        if ((r02 instanceof SystemHealthManager) == false) goto L8;
        SystemHealthManager r03 = (SystemHealthManager) r02;     // Catch: Throwable -> L6
    L9:
        if (r03 == null) goto L11;
        HealthStats r04 = r03.takeMyUidSnapshot();     // Catch: Throwable -> L6
    L12:
        Object r05 = Result.m2018constructorimpl(r04);     // Catch: Throwable -> L6
    L15:
        Throwable r1 = Result.m2021exceptionOrNullimpl(r05);
        if (r1 == null) goto L19;
        FileLog.e(r1);
    L19:
        if (Result.m2024isFailureimpl(r05) == true) goto L23;
        r2 = r05;
    L23:
        return (HealthStats) r2;
    L11:
        r04 = null;
        goto L12
    L8:
        r03 = null;
    L6:
        th = move-exception;
        Result.Companion r12 = Result.Companion;
        r05 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L15
    }

    private final HashMap<String, Long> threadDeltas(Map<Integer, ThreadStat> r8, Map<Integer, ThreadStat> r9) {
        HashMap<String, Long> r0 = new HashMap();
        Iterator<Map.Entry<Integer, ThreadStat>> r92 = r9.entrySet().iterator();
    L4:
        if (r92.hasNext() == false) goto L17;
        Map.Entry<Integer, ThreadStat> r1 = r92.next();
        int r2 = r1.getKey().intValue();
        ThreadStat r12 = r1.getValue();
        ThreadStat r22 = r8.get(Integer.valueOf(r2));
        if (r22 != null) goto L8;
    L10:
        long r3 = r12.getTicks();
    L11:
        long r5 = 0;
        if (r3 <= 0) goto L4;
        String r23 = r12.getName();
        Long r13 = r0.get(r12.getName());
        if (r13 == null) goto L16;
        r5 = r13.longValue();
    L16:
        r0.put(r23, Long.valueOf(r5 + ticksToMs(r3)));
        goto L4
    L8:
        if (Intrinsics.areEqual(r22.getName(), r12.getName()) == false) goto L10;
        r3 = r12.getTicks() - r22.getTicks();
        goto L11
    L17:
        return r0;
    }

    private final JSONArray threadsJson(List<Pair<String, Long>> r7, boolean r8) {
        JSONArray r0 = new JSONArray();
        Iterator<Pair<String, Long>> r72 = r7.iterator();
    L4:
        if (r72.hasNext() == false) goto L9;
        Pair<String, Long> r1 = r72.next();
        String r2 = r1.component1();
        long r3 = r1.component2().longValue();
        JSONObject r12 = new JSONObject().put("name", r2).put("cpuMs", r3);
        if (r8 == false) goto L8;
        r12.put("at", originOf(r2));
    L8:
        r0.put(r12);
        goto L4
    L9:
        return r0;
    }

    public static /* synthetic */ JSONArray threadsJson$default(LoadMonitor r0, List r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        return r0.threadsJson(r1, r2);
    }

    private final long ticksToMs(long r3) {
        return (r3 * 1000) / getClockTicks();
    }

    private final List<Pair<String, Long>> topThreads(Map<String, Long> r2, int r3) {
        List r1 = CollectionsKt.take(CollectionsKt.sortedWith(r2.entrySet(), new LoadMonitor$topThreads$$inlined$sortedByDescending$1()), r3);
        ArrayList r22 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r1, 10));
        Iterator r12 = r1.iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        Map.Entry r32 = (Map.Entry) r12.next();
        r22.add(TuplesKt.to(r32.getKey(), r32.getValue()));
        goto L4
    L6:
        return r22;
    }

    private final long uidRxBytes() {
        return TrafficStats.getUidRxBytes(Process.myUid());
    }

    private final long uidTxBytes() {
        return TrafficStats.getUidTxBytes(Process.myUid());
    }

    private final File writeReport(JSONObject r9, String r10) {
        getReportsDir().mkdirs();
        File r1 = new File(getReportsDir(), "load_" + BuildVars.BUILD_VERSION_STRING + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date()) + "_" + r10 + ".json");
        FilesKt.writeText$default(r1, r9.toString(1), null, 2, null);
        File[] r8 = getReportsDir().listFiles();
        if (r8 == null) goto L18;
        ArrayList r92 = new ArrayList();
        int r3 = r8.length;
        int r5 = 0;
    L5:
        if (r5 >= r3) goto L10;
        File r6 = r8[r5];
        if (StringsKt.endsWith$default(r6.getName(), ".json", false, 2, null) == false) goto L9;
        r92.add(r6);
    L9:
        r5 = r5 + 1;
        goto L5
    L10:
        List r82 = CollectionsKt.sortedWith(r92, new LoadMonitor$writeReport$$inlined$sortedByDescending$1());
        if (r82 == null) goto L18;
        List r83 = CollectionsKt.drop(r82, 10);
        if (r83 == null) goto L18;
        Iterator r84 = r83.iterator();
    L16:
        if (r84.hasNext() == false) goto L18;
        ((File) r84.next()).delete();
    L18:
        return r1;
    }

    public final void captureAndShare(Activity r3) {
        Sampler r2 = sampler;
        if (r2 == null) goto L6;
        DispatchQueue r0 = r2.getQueue();
        if (r0 == null) goto L6;
    L7:
        r0.postRunnable(new LoadMonitor$$ExternalSyntheticLambda5(r2, r3));
        return;
    L6:
        r0 = Utilities.globalQueue;
        goto L7
    }

    public final void deleteReports(Function0<Unit> r2) {
        Utilities.globalQueue.postRunnable(new LoadMonitor$$ExternalSyntheticLambda4(r2));
    }

    public final int getCpuPercent() {
        return RangesKt.coerceIn(DebugConfig.getLoadMonitorCpuPercent(), 1, 50);
    }

    public final File getLastReport() {
        File[] r9 = getReportsDir().listFiles();
        Object r0 = null;
        if (r9 == null) goto L29;
        ArrayList r1 = new ArrayList();
        int r2 = r9.length;
        int r4 = 0;
    L5:
        if (r4 >= r2) goto L10;
        File r5 = r9[r4];
        if (StringsKt.endsWith$default(r5.getName(), ".json", false, 2, null) == false) goto L9;
        r1.add(r5);
    L9:
        r4 = r4 + 1;
        goto L5
    L10:
        Iterator r92 = r1.iterator();
        if (r92.hasNext() == false) goto L23;
        r0 = r92.next();
        if (r92.hasNext() == false) goto L23;
        long r12 = ((File) r0).lastModified();
    L17:
        Object r3 = r92.next();
        long r42 = ((File) r3).lastModified();
        if (r12 >= r42) goto L21;
        r0 = r3;
        r12 = r42;
    L21:
        if (r92.hasNext() == true) goto L17;
    L23:
        return (File) r0;
    L29:
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

    public final String getSummary() {
        Sampler r0 = sampler;
        if (r0 == null) goto L9;
        String r02 = r0.getSummary();
        if (r02 == null) goto L9;
        return r02;
    L9:
        return formatDuration(Process.getElapsedCpuTime()) + " CPU since launch";
    }

    public final void setCpuPercent(int r1) {
        DebugConfig.setLoadMonitorCpuPercent(r1);
    }

    public final void setEnabled(boolean r2) {
        if (DebugConfig.getLoadMonitorEnabled() == r2) goto L17;
        DebugConfig.setLoadMonitorEnabled(r2);
        Sampler r1 = sampler;
        if (r1 == null) goto L9;
        r1.stop();
    L9:
        if (r2 == false) goto L11;
        Sampler r0 = new Sampler();
    L12:
        sampler = r0;
        if (r2 == true) goto L16;
        pendingAlert = null;
        return;
    L16:
        return;
    L11:
        r0 = null;
        goto L12
    }

    public final void shareLastReport(Activity r2) {
        File r1 = getLastReport();
        if (r1 == null) goto L6;
        INSTANCE.share(r2, r1);
        return;
    }
}
