.class public final Lcom/exteragram/messenger/debug/LoadMonitor;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/debug/LoadMonitor$Episode;,
        Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;,
        Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;,
        Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u00ae\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0002\u0008\u0005\n\u0002\u0010\u000e\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\t\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u000c\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0008\n\u0002\u0010$\n\u0002\u0008\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0008\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u001e\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0012\u0008\u00c6\u0002\u0018\u00002\u00020\u0001:\u0008\u0087\u0001\u0088\u0001\u0089\u0001\u008a\u0001B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\u0008\u0007\u0010\u0008J\r\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\u0008\n\u0010\u000bJ\u0015\u0010\r\u001a\u00020\u00062\u0006\u0010\u000c\u001a\u00020\t\u00a2\u0006\u0004\u0008\r\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\u0008\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\u0004\u0008\u0013\u0010\u0014J\r\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\u0004\u0008\u0016\u0010\u0017J\u001b\u0010\u001a\u001a\u00020\u00062\u000c\u0010\u0019\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0018\u00a2\u0006\u0004\u0008\u001a\u0010\u001bJ\u0015\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001c\u00a2\u0006\u0004\u0008\u001e\u0010\u001fJ\u0015\u0010 \u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001c\u00a2\u0006\u0004\u0008 \u0010\u001fJ\u000f\u0010!\u001a\u00020\u0006H\u0007\u00a2\u0006\u0004\u0008!\u0010\u0003J\u001f\u0010#\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\u0008#\u0010$J\u000f\u0010%\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\u0008%\u0010\u0003J\u000f\u0010&\u001a\u00020\tH\u0002\u00a2\u0006\u0004\u0008&\u0010\u000bJ\u0011\u0010\'\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\u0008\'\u0010(J\u000f\u0010*\u001a\u00020)H\u0002\u00a2\u0006\u0004\u0008*\u0010+J\u000f\u0010,\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\u0008,\u0010\u0017J\u000f\u0010-\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\u0008-\u0010\u0017J+\u00101\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020/0.j\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020/`0H\u0002\u00a2\u0006\u0004\u00081\u00102J\u0019\u00104\u001a\u0004\u0018\u00010/2\u0006\u00103\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\u00084\u00105J\u0017\u00107\u001a\u00020\u00152\u0006\u00106\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\u00087\u00108JS\u0010<\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00150.j\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0015`02\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020/092\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020/09H\u0002\u00a2\u0006\u0004\u0008<\u0010=J=\u0010B\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00150A0@2\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0015092\u0006\u0010?\u001a\u00020\tH\u0002\u00a2\u0006\u0004\u0008B\u0010CJ3\u0010F\u001a\u00020E2\u0018\u0010>\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00150A0@2\u0008\u0008\u0002\u0010D\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\u0008F\u0010GJ\u0019\u0010I\u001a\u0004\u0018\u00010\u000f2\u0006\u0010H\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\u0008I\u0010JJ\u001d\u0010L\u001a\u00020\u00062\u000c\u0010K\u001a\u0008\u0012\u0004\u0012\u00020\u000f0@H\u0002\u00a2\u0006\u0004\u0008L\u0010MJ\u001f\u0010Q\u001a\u0004\u0018\u00010\u000f2\u000c\u0010P\u001a\u0008\u0012\u0004\u0012\u00020O0NH\u0002\u00a2\u0006\u0004\u0008Q\u0010RJ\u0011\u0010T\u001a\u0004\u0018\u00010SH\u0002\u00a2\u0006\u0004\u0008T\u0010UJ\u001f\u0010X\u001a\u00020\u000f2\u0006\u0010V\u001a\u00020\u000f2\u0006\u0010W\u001a\u00020\tH\u0002\u00a2\u0006\u0004\u0008X\u0010YJ\u0017\u0010[\u001a\u00020)2\u0006\u0010Z\u001a\u00020SH\u0002\u00a2\u0006\u0004\u0008[\u0010\\J\u0017\u0010^\u001a\u00020)2\u0006\u0010]\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\u0008^\u0010_J\u001f\u0010a\u001a\u00020\u00122\u0006\u0010`\u001a\u00020)2\u0006\u0010]\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\u0008a\u0010bJ\u0017\u0010d\u001a\u00020\u000f2\u0006\u0010c\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\u0008d\u0010eJ\u001f\u0010h\u001a\u00020\u000f2\u0006\u0010f\u001a\u00020\u00152\u0006\u0010g\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\u0008h\u0010iJI\u0010l\u001a\u00020\u000f2\u0006\u0010g\u001a\u00020\u00152\u0006\u0010f\u001a\u00020\u00152\u0006\u0010j\u001a\u00020\u00152\u0006\u0010k\u001a\u00020\u00152\u0018\u0010>\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00150A0@H\u0002\u00a2\u0006\u0004\u0008l\u0010mR\u001a\u0010n\u001a\u0008\u0012\u0004\u0012\u00020\u000f0N8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\u0008n\u0010oR\"\u0010q\u001a\u0010\u0012\u000c\u0012\n p*\u0004\u0018\u00010\u000f0\u000f0N8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\u0008q\u0010oR\u0018\u0010s\u001a\u0004\u0018\u00010r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\u0008s\u0010tR\u0018\u0010v\u001a\u0004\u0018\u00010u8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\u0008v\u0010wR\u001e\u0010z\u001a\n\u0012\u0004\u0012\u00020y\u0018\u00010x8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\u0008z\u0010{RH\u0010|\u001a6\u0012\u0004\u0012\u00020\u000f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f090.j\u001a\u0012\u0004\u0012\u00020\u000f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f09`08\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\u0008|\u0010}R0\u0010~\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0.j\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f`08\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\u0008~\u0010}R0\u0010\u007f\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t0.j\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t`08\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\u0008\u007f\u0010}R\u001f\u0010\u0083\u0001\u001a\u00020\u00128FX\u0086\u0084\u0002\u00a2\u0006\u000f\n\u0006\u0008\u0080\u0001\u0010\u0081\u0001\u001a\u0005\u0008\u0082\u0001\u0010\u0014R\u001f\u0010\u0086\u0001\u001a\u00020\u00158BX\u0082\u0084\u0002\u00a2\u0006\u000f\n\u0006\u0008\u0084\u0001\u0010\u0081\u0001\u001a\u0005\u0008\u0085\u0001\u0010\u0017\u00a8\u0006\u008b\u0001"
    }
    d2 = {
        "Lcom/exteragram/messenger/debug/LoadMonitor;",
        "",
        "<init>",
        "()V",
        "",
        "enabled",
        "",
        "setEnabled",
        "(Z)V",
        "",
        "getCpuPercent",
        "()I",
        "percent",
        "setCpuPercent",
        "(I)V",
        "",
        "getSummary",
        "()Ljava/lang/String;",
        "Ljava/io/File;",
        "getLastReport",
        "()Ljava/io/File;",
        "",
        "getReportsSize",
        "()J",
        "Lkotlin/Function0;",
        "onDone",
        "deleteReports",
        "(Lkotlin/jvm/functions/Function0;)V",
        "Landroid/app/Activity;",
        "activity",
        "shareLastReport",
        "(Landroid/app/Activity;)V",
        "captureAndShare",
        "init",
        "file",
        "share",
        "(Landroid/app/Activity;Ljava/io/File;)V",
        "showPendingAlert",
        "currentState",
        "isPlugged",
        "()Ljava/lang/Boolean;",
        "Lorg/json/JSONObject;",
        "batteryJson",
        "()Lorg/json/JSONObject;",
        "uidRxBytes",
        "uidTxBytes",
        "Ljava/util/HashMap;",
        "Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;",
        "Lkotlin/collections/HashMap;",
        "readThreads",
        "()Ljava/util/HashMap;",
        "stat",
        "parseStat",
        "(Ljava/lang/String;)Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;",
        "ticks",
        "ticksToMs",
        "(J)J",
        "",
        "previous",
        "current",
        "threadDeltas",
        "(Ljava/util/Map;Ljava/util/Map;)Ljava/util/HashMap;",
        "threads",
        "limit",
        "",
        "Lkotlin/Pair;",
        "topThreads",
        "(Ljava/util/Map;I)Ljava/util/List;",
        "withOrigin",
        "Lorg/json/JSONArray;",
        "threadsJson",
        "(Ljava/util/List;Z)Lorg/json/JSONArray;",
        "name",
        "originOf",
        "(Ljava/lang/String;)Ljava/lang/String;",
        "names",
        "resolveOrigins",
        "(Ljava/util/List;)V",
        "",
        "Ljava/lang/StackTraceElement;",
        "trace",
        "appFrame",
        "([Ljava/lang/StackTraceElement;)Ljava/lang/String;",
        "Landroid/os/health/HealthStats;",
        "takeHealthStats",
        "()Landroid/os/health/HealthStats;",
        "dataType",
        "key",
        "healthKeyName",
        "(Ljava/lang/String;I)Ljava/lang/String;",
        "stats",
        "healthJson",
        "(Landroid/os/health/HealthStats;)Lorg/json/JSONObject;",
        "trigger",
        "baseReport",
        "(Ljava/lang/String;)Lorg/json/JSONObject;",
        "report",
        "writeReport",
        "(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/io/File;",
        "ms",
        "formatDuration",
        "(J)Ljava/lang/String;",
        "cpuMs",
        "wallMs",
        "formatPercent",
        "(JJ)Ljava/lang/String;",
        "rxBytes",
        "txBytes",
        "describeEpisode",
        "(JJJJLjava/util/List;)Ljava/lang/String;",
        "STATE_NAMES",
        "[Ljava/lang/String;",
        "kotlin.jvm.PlatformType",
        "SKIPPED_FRAMES",
        "Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;",
        "sampler",
        "Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;",
        "Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;",
        "pendingAlert",
        "Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;",
        "Ljava/lang/ref/WeakReference;",
        "Lorg/telegram/ui/ActionBar/AlertDialog;",
        "alertRef",
        "Ljava/lang/ref/WeakReference;",
        "healthKeyNames",
        "Ljava/util/HashMap;",
        "threadOrigins",
        "originAttempts",
        "reportsDir$delegate",
        "Lkotlin/Lazy;",
        "getReportsDir",
        "reportsDir",
        "clockTicks$delegate",
        "getClockTicks",
        "clockTicks",
        "ThreadStat",
        "PendingAlert",
        "Episode",
        "Sampler",
        "TMessagesProj"
    }
    k = 0x1
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nLoadMonitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LoadMonitor.kt\ncom/exteragram/messenger/debug/LoadMonitor\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,696:1\n3938#2:697\n4474#2,2:698\n13213#2,2:723\n3938#2:728\n4474#2,2:729\n3938#2:745\n4474#2,2:746\n14048#2,2:751\n2045#3,14:700\n1080#3:715\n1586#3:716\n1661#3,3:717\n777#3:720\n873#3,2:721\n1205#3,2:731\n1282#3,4:733\n1586#3:741\n1661#3,3:742\n1080#3:748\n1915#3,2:749\n1#4:714\n410#5,3:725\n413#5,4:737\n*S KotlinDebug\n*F\n+ 1 LoadMonitor.kt\ncom/exteragram/messenger/debug/LoadMonitor\n*L\n130#1:697\n130#1:698,2\n291#1:723,2\n308#1:728\n308#1:729,2\n417#1:745\n417#1:746,2\n137#1:751,2\n131#1:700,14\n253#1:715\n253#1:716\n253#1:717,3\n271#1:720\n271#1:721,2\n309#1:731,2\n309#1:733,4\n382#1:741\n382#1:742,3\n418#1:748\n420#1:749,2\n304#1:725,3\n304#1:737,4\n*E\n"
    }
.end annotation


# static fields
.field public static final INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

.field private static final SKIPPED_FRAMES:[Ljava/lang/String;

.field private static final STATE_NAMES:[Ljava/lang/String;

.field private static alertRef:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference<",
            "Lorg/telegram/ui/ActionBar/AlertDialog;",
            ">;"
        }
    .end annotation
.end field

.field private static final clockTicks$delegate:Lkotlin/Lazy;

.field private static final healthKeyNames:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/util/Map<",
            "Ljava/lang/Integer;",
            "Ljava/lang/String;",
            ">;>;"
        }
    .end annotation
.end field

.field private static final originAttempts:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private static volatile pendingAlert:Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;

.field private static final reportsDir$delegate:Lkotlin/Lazy;

.field private static volatile sampler:Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;

.field private static final threadOrigins:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public static $r8$lambda$EvWZU_-AiIxct8eo7VUHXa2A0bw(Lkotlin/Pair;)Ljava/lang/CharSequence;
    .locals 4

    .line 447
    invoke-virtual {p0}, Lkotlin/Pair;->getFirst()Ljava/lang/Object;

    move-result-object v0

    sget-object v1, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-virtual {p0}, Lkotlin/Pair;->getSecond()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Number;

    invoke-virtual {p0}, Ljava/lang/Number;->longValue()J

    move-result-wide v2

    invoke-direct {v1, v2, v3}, Lcom/exteragram/messenger/debug/LoadMonitor;->formatDuration(J)Ljava/lang/String;

    move-result-object p0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, " "

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$G5I7fuj0kLwvbL4wsMtBJkoYexs(Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;Landroid/app/Activity;)V
    .locals 2

    .line 150
    sget-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    :try_start_0
    sget-object v1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 151
    const-string v1, "manual"

    if-eqz p0, :cond_0

    :try_start_1
    invoke-virtual {p0, v1}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->captureReport(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object p0

    if-nez p0, :cond_1

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    :cond_0
    :goto_0
    invoke-direct {v0, v1}, Lcom/exteragram/messenger/debug/LoadMonitor;->baseReport(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object p0

    :cond_1
    invoke-direct {v0, p0, v1}, Lcom/exteragram/messenger/debug/LoadMonitor;->writeReport(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/io/File;

    move-result-object p0

    .line 150
    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_2

    :goto_1
    sget-object v0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 153
    :goto_2
    new-instance v0, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda7;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda7;-><init>(Ljava/lang/Object;Landroid/app/Activity;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$HqwllwjZJtclxFPulNWPmnOfRKg()J
    .locals 5

    .line 98
    sget v0, Landroid/system/OsConstants;->_SC_CLK_TCK:I

    invoke-static {v0}, Landroid/system/Os;->sysconf(I)J

    move-result-wide v0

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    const-wide/16 v3, 0x0

    cmp-long v0, v0, v3

    if-lez v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v2, 0x0

    :goto_0
    if-eqz v2, :cond_1

    invoke-virtual {v2}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    return-wide v0

    :cond_1
    const-wide/16 v0, 0x64

    return-wide v0
.end method

.method public static $r8$lambda$NdeRMlyzkcIMr7a3YcyRA_FFvjU(Lkotlin/jvm/functions/Function0;)V
    .locals 4

    .line 137
    sget-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/LoadMonitor;->getReportsDir()Ljava/io/File;

    move-result-object v0

    invoke-virtual {v0}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 14048
    array-length v1, v0

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_0

    aget-object v3, v0, v2

    .line 137
    invoke-virtual {v3}, Ljava/io/File;->delete()Z

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 138
    :cond_0
    new-instance v0, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda6;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda6;-><init>(Lkotlin/jvm/functions/Function0;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$TBAZBRFuhd4VBPd6EhR6HEUFtmc(Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 177
    sget-object p0, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    const/4 p1, 0x0

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/debug/LoadMonitor;->setEnabled(Z)V

    return-void
.end method

.method public static $r8$lambda$WeeawD0afuH0k272RT_v_fiH5FA(Lorg/telegram/ui/LaunchActivity;Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 176
    sget-object p2, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-virtual {p1}, Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;->getFile()Ljava/io/File;

    move-result-object p1

    invoke-direct {p2, p0, p1}, Lcom/exteragram/messenger/debug/LoadMonitor;->share(Landroid/app/Activity;Ljava/io/File;)V

    return-void
.end method

.method public static $r8$lambda$eRg4SnlNz0SMf510-JcmUPE_G5g(Lkotlin/jvm/functions/Function0;)V
    .locals 0

    .line 138
    invoke-interface {p0}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    return-void
.end method

.method public static $r8$lambda$gUlE6fLgWq1L7vM9V_8T3RhwJmA()Ljava/io/File;
    .locals 1

    .line 95
    const-string v0, "loadreports"

    invoke-static {v0}, Lcom/exteragram/messenger/debug/DebugFilesKt;->debugFilesDir(Ljava/lang/String;)Ljava/io/File;

    move-result-object v0

    return-object v0
.end method

.method public static $r8$lambda$yclvq93EyksPCxohhOabqVuRpcY(Ljava/lang/Object;Landroid/app/Activity;)V
    .locals 3

    .line 154
    invoke-static {p0}, Lkotlin/Result;->isSuccess-impl(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    move-object v0, p0

    check-cast v0, Ljava/io/File;

    sget-object v1, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-direct {v1, p1, v0}, Lcom/exteragram/messenger/debug/LoadMonitor;->share(Landroid/app/Activity;Ljava/io/File;)V

    :cond_0
    invoke-static {p0}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object p0

    if-eqz p0, :cond_1

    .line 155
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 156
    invoke-static {}, Lorg/telegram/ui/Components/BulletinFactory;->global()Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p1

    sget v0, Lorg/telegram/messenger/R$raw;->error:I

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Load report failed: "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p1, v0, p0}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    :cond_1
    return-void
.end method

.method static constructor <clinit>()V
    .locals 12

    new-instance v0, Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-direct {v0}, Lcom/exteragram/messenger/debug/LoadMonitor;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    .line 80
    const-string v0, "background"

    const-string v1, "screenOff"

    const-string v2, "foreground"

    filled-new-array {v2, v0, v1}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->STATE_NAMES:[Ljava/lang/String;

    .line 83
    const-class v0, Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v11

    const-string v1, "java."

    const-string v2, "javax."

    const-string v3, "kotlin."

    const-string v4, "android."

    const-string v5, "com.android."

    const-string v6, "dalvik."

    const-string v7, "libcore."

    const-string v8, "sun."

    const-string v9, "jdk."

    const-string v10, "de.robv.android.xposed."

    filled-new-array/range {v1 .. v11}, [Ljava/lang/String;

    move-result-object v0

    .line 81
    sput-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->SKIPPED_FRAMES:[Ljava/lang/String;

    .line 91
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->healthKeyNames:Ljava/util/HashMap;

    .line 92
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->threadOrigins:Ljava/util/HashMap;

    .line 93
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->originAttempts:Ljava/util/HashMap;

    .line 95
    new-instance v0, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda0;

    invoke-direct {v0}, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda0;-><init>()V

    invoke-static {v0}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->reportsDir$delegate:Lkotlin/Lazy;

    .line 97
    new-instance v0, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda1;

    invoke-direct {v0}, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda1;-><init>()V

    invoke-static {v0}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->clockTicks$delegate:Lkotlin/Lazy;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 53
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static final synthetic access$baseReport(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/lang/String;)Lorg/json/JSONObject;
    .locals 0

    .line 53
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/debug/LoadMonitor;->baseReport(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$batteryJson(Lcom/exteragram/messenger/debug/LoadMonitor;)Lorg/json/JSONObject;
    .locals 0

    .line 53
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->batteryJson()Lorg/json/JSONObject;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$currentState(Lcom/exteragram/messenger/debug/LoadMonitor;)I
    .locals 0

    .line 53
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->currentState()I

    move-result p0

    return p0
.end method

.method public static final synthetic access$describeEpisode(Lcom/exteragram/messenger/debug/LoadMonitor;JJJJLjava/util/List;)Ljava/lang/String;
    .locals 0

    .line 53
    invoke-direct/range {p0 .. p9}, Lcom/exteragram/messenger/debug/LoadMonitor;->describeEpisode(JJJJLjava/util/List;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$formatDuration(Lcom/exteragram/messenger/debug/LoadMonitor;J)Ljava/lang/String;
    .locals 0

    .line 53
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/debug/LoadMonitor;->formatDuration(J)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$getSTATE_NAMES$p()[Ljava/lang/String;
    .locals 1

    .line 53
    sget-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->STATE_NAMES:[Ljava/lang/String;

    return-object v0
.end method

.method public static final synthetic access$healthJson(Lcom/exteragram/messenger/debug/LoadMonitor;Landroid/os/health/HealthStats;)Lorg/json/JSONObject;
    .locals 0

    .line 53
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/debug/LoadMonitor;->healthJson(Landroid/os/health/HealthStats;)Lorg/json/JSONObject;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$isPlugged(Lcom/exteragram/messenger/debug/LoadMonitor;)Ljava/lang/Boolean;
    .locals 0

    .line 53
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->isPlugged()Ljava/lang/Boolean;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$originOf(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    .line 53
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/debug/LoadMonitor;->originOf(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$readThreads(Lcom/exteragram/messenger/debug/LoadMonitor;)Ljava/util/HashMap;
    .locals 0

    .line 53
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->readThreads()Ljava/util/HashMap;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$resolveOrigins(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/util/List;)V
    .locals 0

    .line 53
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/debug/LoadMonitor;->resolveOrigins(Ljava/util/List;)V

    return-void
.end method

.method public static final synthetic access$setPendingAlert$p(Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;)V
    .locals 0

    .line 53
    sput-object p0, Lcom/exteragram/messenger/debug/LoadMonitor;->pendingAlert:Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;

    return-void
.end method

.method public static final synthetic access$showPendingAlert(Lcom/exteragram/messenger/debug/LoadMonitor;)V
    .locals 0

    .line 53
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->showPendingAlert()V

    return-void
.end method

.method public static final synthetic access$takeHealthStats(Lcom/exteragram/messenger/debug/LoadMonitor;)Landroid/os/health/HealthStats;
    .locals 0

    .line 53
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->takeHealthStats()Landroid/os/health/HealthStats;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$threadDeltas(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/util/Map;Ljava/util/Map;)Ljava/util/HashMap;
    .locals 0

    .line 53
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/debug/LoadMonitor;->threadDeltas(Ljava/util/Map;Ljava/util/Map;)Ljava/util/HashMap;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$threadsJson(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/util/List;Z)Lorg/json/JSONArray;
    .locals 0

    .line 53
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/debug/LoadMonitor;->threadsJson(Ljava/util/List;Z)Lorg/json/JSONArray;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$topThreads(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/util/Map;I)Ljava/util/List;
    .locals 0

    .line 53
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/debug/LoadMonitor;->topThreads(Ljava/util/Map;I)Ljava/util/List;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$uidRxBytes(Lcom/exteragram/messenger/debug/LoadMonitor;)J
    .locals 2

    .line 53
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->uidRxBytes()J

    move-result-wide v0

    return-wide v0
.end method

.method public static final synthetic access$uidTxBytes(Lcom/exteragram/messenger/debug/LoadMonitor;)J
    .locals 2

    .line 53
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->uidTxBytes()J

    move-result-wide v0

    return-wide v0
.end method

.method public static final synthetic access$writeReport(Lcom/exteragram/messenger/debug/LoadMonitor;Lorg/json/JSONObject;Ljava/lang/String;)Ljava/io/File;
    .locals 0

    .line 53
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/debug/LoadMonitor;->writeReport(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/io/File;

    move-result-object p0

    return-object p0
.end method

.method private final appFrame([Ljava/lang/StackTraceElement;)Ljava/lang/String;
    .locals 10

    .line 290
    array-length p0, p1

    const/4 v0, 0x0

    move v1, v0

    :goto_0
    const/4 v2, 0x0

    if-ge v1, p0, :cond_2

    aget-object v3, p1, v1

    .line 291
    sget-object v4, Lcom/exteragram/messenger/debug/LoadMonitor;->SKIPPED_FRAMES:[Ljava/lang/String;

    .line 13213
    array-length v5, v4

    move v6, v0

    :goto_1
    const/4 v7, 0x2

    if-ge v6, v5, :cond_1

    aget-object v8, v4, v6

    .line 291
    invoke-virtual {v3}, Ljava/lang/StackTraceElement;->getClassName()Ljava/lang/String;

    move-result-object v9

    invoke-static {v9, v8, v0, v7, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v7

    if-eqz v7, :cond_0

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_0
    add-int/lit8 v6, v6, 0x1

    goto :goto_1

    .line 292
    :cond_1
    invoke-virtual {v3}, Ljava/lang/StackTraceElement;->getClassName()Ljava/lang/String;

    move-result-object p0

    const/16 p1, 0x2e

    invoke-static {p0, p1, v2, v7, v2}, Lkotlin/text/StringsKt;->substringAfterLast$default(Ljava/lang/String;CLjava/lang/String;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v3}, Ljava/lang/StackTraceElement;->getMethodName()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v3}, Ljava/lang/StackTraceElement;->getLineNumber()I

    move-result v0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "."

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ":"

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_2
    return-object v2
.end method

.method private final baseReport(Ljava/lang/String;)Lorg/json/JSONObject;
    .locals 11

    .line 375
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    .line 376
    const-string v1, "power"

    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    instance-of v2, v1, Landroid/os/PowerManager;

    const/4 v3, 0x0

    if-eqz v2, :cond_0

    check-cast v1, Landroid/os/PowerManager;

    goto :goto_0

    :cond_0
    move-object v1, v3

    .line 377
    :goto_0
    new-instance v2, Ljava/util/HashMap;

    invoke-direct {v2}, Ljava/util/HashMap;-><init>()V

    .line 378
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->readThreads()Ljava/util/HashMap;

    move-result-object v4

    invoke-virtual {v4}, Ljava/util/HashMap;->values()Ljava/util/Collection;

    move-result-object v4

    invoke-interface {v4}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v4

    :goto_1
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_2

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;

    .line 379
    invoke-virtual {v5}, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;->getName()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5}, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;->getName()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v2, v7}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/Long;

    if-eqz v7, :cond_1

    invoke-virtual {v7}, Ljava/lang/Long;->longValue()J

    move-result-wide v7

    goto :goto_2

    :cond_1
    const-wide/16 v7, 0x0

    :goto_2
    invoke-virtual {v5}, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;->getTicks()J

    move-result-wide v9

    invoke-direct {p0, v9, v10}, Lcom/exteragram/messenger/debug/LoadMonitor;->ticksToMs(J)J

    move-result-wide v9

    add-long/2addr v7, v9

    invoke-static {v7, v8}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    invoke-interface {v2, v6, v5}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_1

    :cond_2
    const/16 v4, 0x1e

    .line 381
    invoke-direct {p0, v2, v4}, Lcom/exteragram/messenger/debug/LoadMonitor;->topThreads(Ljava/util/Map;I)Ljava/util/List;

    move-result-object v2

    .line 382
    move-object v4, v2

    check-cast v4, Ljava/lang/Iterable;

    .line 1586
    new-instance v5, Ljava/util/ArrayList;

    const/16 v6, 0xa

    invoke-static {v4, v6}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v6

    invoke-direct {v5, v6}, Ljava/util/ArrayList;-><init>(I)V

    .line 1661
    invoke-interface {v4}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v4

    :goto_3
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v6

    if-eqz v6, :cond_3

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v6

    .line 1662
    check-cast v6, Lkotlin/Pair;

    .line 382
    invoke-virtual {v6}, Lkotlin/Pair;->getFirst()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    .line 1662
    invoke-interface {v5, v6}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_3

    .line 382
    :cond_3
    invoke-direct {p0, v5}, Lcom/exteragram/messenger/debug/LoadMonitor;->resolveOrigins(Ljava/util/List;)V

    .line 383
    new-instance v4, Lorg/json/JSONObject;

    invoke-direct {v4}, Lorg/json/JSONObject;-><init>()V

    .line 384
    const-string v5, "format"

    const/4 v6, 0x1

    invoke-virtual {v4, v5, v6}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    move-result-object v4

    .line 385
    const-string v5, "trigger"

    invoke-virtual {v4, v5, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p1

    .line 386
    const-string v4, "createdAt"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v7

    invoke-virtual {p1, v4, v7, v8}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object p1

    .line 387
    new-instance v4, Lorg/json/JSONObject;

    invoke-direct {v4}, Lorg/json/JSONObject;-><init>()V

    .line 388
    const-string v5, "version"

    sget-object v7, Lorg/telegram/messenger/BuildVars;->BUILD_VERSION_STRING:Ljava/lang/String;

    invoke-virtual {v4, v5, v7}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v4

    .line 389
    const-string v5, "code"

    sget v7, Lorg/telegram/messenger/BuildVars;->BUILD_VERSION:I

    invoke-virtual {v4, v5, v7}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    move-result-object v4

    .line 390
    const-string v5, "debug"

    sget-boolean v7, Lorg/telegram/messenger/BuildVars;->DEBUG_VERSION:Z

    invoke-virtual {v4, v5, v7}, Lorg/json/JSONObject;->put(Ljava/lang/String;Z)Lorg/json/JSONObject;

    move-result-object v4

    .line 387
    const-string v5, "app"

    invoke-virtual {p1, v5, v4}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p1

    .line 391
    new-instance v4, Lorg/json/JSONObject;

    invoke-direct {v4}, Lorg/json/JSONObject;-><init>()V

    .line 392
    const-string v5, "manufacturer"

    sget-object v7, Landroid/os/Build;->MANUFACTURER:Ljava/lang/String;

    invoke-virtual {v4, v5, v7}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v4

    .line 393
    const-string v5, "model"

    sget-object v7, Landroid/os/Build;->MODEL:Ljava/lang/String;

    invoke-virtual {v4, v5, v7}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v4

    .line 394
    const-string v5, "sdk"

    sget v7, Landroid/os/Build$VERSION;->SDK_INT:I

    invoke-virtual {v4, v5, v7}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    move-result-object v4

    .line 395
    invoke-static {}, Ljava/lang/Runtime;->getRuntime()Ljava/lang/Runtime;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/Runtime;->availableProcessors()I

    move-result v5

    const-string v7, "cores"

    invoke-virtual {v4, v7, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    move-result-object v4

    .line 391
    const-string v5, "device"

    invoke-virtual {p1, v5, v4}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p1

    .line 396
    const-string v4, "battery"

    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->batteryJson()Lorg/json/JSONObject;

    move-result-object v5

    invoke-virtual {p1, v4, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p1

    .line 397
    new-instance v4, Lorg/json/JSONObject;

    invoke-direct {v4}, Lorg/json/JSONObject;-><init>()V

    .line 398
    const-string v5, "accounts"

    invoke-static {}, Lorg/telegram/messenger/UserConfig;->getActivatedAccountsCount()I

    move-result v7

    invoke-virtual {v4, v5, v7}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    move-result-object v4

    .line 399
    const-string v5, "proxy"

    invoke-static {}, Lorg/telegram/messenger/SharedConfig;->isProxyEnabled()Z

    move-result v7

    invoke-virtual {v4, v5, v7}, Lorg/json/JSONObject;->put(Ljava/lang/String;Z)Lorg/json/JSONObject;

    move-result-object v4

    .line 400
    const-string v5, "logs"

    sget-boolean v7, Lorg/telegram/messenger/BuildVars;->LOGS_ENABLED:Z

    invoke-virtual {v4, v5, v7}, Lorg/json/JSONObject;->put(Ljava/lang/String;Z)Lorg/json/JSONObject;

    move-result-object v4

    const/4 v5, 0x0

    if-eqz v1, :cond_4

    .line 401
    invoke-virtual {v1}, Landroid/os/PowerManager;->isPowerSaveMode()Z

    move-result v7

    if-ne v7, v6, :cond_4

    move v7, v6

    goto :goto_4

    :cond_4
    move v7, v5

    :goto_4
    const-string v8, "powerSave"

    invoke-virtual {v4, v8, v7}, Lorg/json/JSONObject;->put(Ljava/lang/String;Z)Lorg/json/JSONObject;

    move-result-object v4

    if-eqz v1, :cond_5

    .line 402
    invoke-virtual {v0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Landroid/os/PowerManager;->isIgnoringBatteryOptimizations(Ljava/lang/String;)Z

    move-result v0

    if-ne v0, v6, :cond_5

    move v5, v6

    :cond_5
    const-string v0, "ignoringBatteryOptimizations"

    invoke-virtual {v4, v0, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;Z)Lorg/json/JSONObject;

    move-result-object v0

    .line 397
    const-string v1, "settings"

    invoke-virtual {p1, v1, v0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p1

    .line 403
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    .line 404
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v4

    invoke-static {}, Landroid/os/Process;->getStartElapsedRealtime()J

    move-result-wide v7

    sub-long/2addr v4, v7

    const-string v1, "uptimeMs"

    invoke-virtual {v0, v1, v4, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v0

    .line 405
    const-string v1, "cpuMs"

    invoke-static {}, Landroid/os/Process;->getElapsedCpuTime()J

    move-result-wide v4

    invoke-virtual {v0, v1, v4, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v0

    .line 406
    sget-object v1, Lcom/exteragram/messenger/debug/LoadMonitor;->STATE_NAMES:[Ljava/lang/String;

    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->currentState()I

    move-result v4

    aget-object v1, v1, v4

    const-string v4, "state"

    invoke-virtual {v0, v4, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    .line 407
    const-string v1, "threads"

    invoke-direct {p0, v2, v6}, Lcom/exteragram/messenger/debug/LoadMonitor;->threadsJson(Ljava/util/List;Z)Lorg/json/JSONArray;

    move-result-object v2

    invoke-virtual {v0, v1, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    .line 403
    const-string v1, "process"

    invoke-virtual {p1, v1, v0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p1

    .line 408
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->takeHealthStats()Landroid/os/health/HealthStats;

    move-result-object p0

    if-eqz p0, :cond_6

    sget-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->healthJson(Landroid/os/health/HealthStats;)Lorg/json/JSONObject;

    move-result-object v3

    :cond_6
    const-string p0, "healthNow"

    invoke-virtual {p1, p0, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p0

    return-object p0
.end method

.method private final batteryJson()Lorg/json/JSONObject;
    .locals 3

    .line 201
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    .line 202
    const-string v1, "level"

    invoke-static {}, Lorg/telegram/messenger/LiteMode;->getBatteryLevel()I

    move-result v2

    invoke-virtual {v0, v1, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    move-result-object v0

    .line 203
    const-string v1, "plugged"

    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->isPlugged()Ljava/lang/Boolean;

    move-result-object p0

    invoke-virtual {v0, v1, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p0

    return-object p0
.end method

.method private final currentState()I
    .locals 2

    .line 189
    sget-object p0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const-string v0, "power"

    invoke-virtual {p0, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    instance-of v0, p0, Landroid/os/PowerManager;

    if-eqz v0, :cond_0

    check-cast p0, Landroid/os/PowerManager;

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    if-eqz p0, :cond_1

    .line 190
    invoke-virtual {p0}, Landroid/os/PowerManager;->isInteractive()Z

    move-result p0

    if-nez p0, :cond_1

    const/4 p0, 0x2

    return p0

    .line 193
    :cond_1
    invoke-static {}, Lorg/telegram/ui/Components/ForegroundDetector;->getInstance()Lorg/telegram/ui/Components/ForegroundDetector;

    move-result-object p0

    const/4 v0, 0x0

    const/4 v1, 0x1

    if-eqz p0, :cond_2

    invoke-virtual {p0}, Lorg/telegram/ui/Components/ForegroundDetector;->isForeground()Z

    move-result p0

    if-ne p0, v1, :cond_2

    move v0, v1

    :cond_2
    xor-int/lit8 p0, v0, 0x1

    return p0
.end method

.method private final describeEpisode(JJJJLjava/util/List;)Ljava/lang/String;
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(JJJJ",
            "Ljava/util/List<",
            "Lkotlin/Pair<",
            "Ljava/lang/String;",
            "Ljava/lang/Long;",
            ">;>;)",
            "Ljava/lang/String;"
        }
    .end annotation

    .line 438
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    .line 439
    invoke-direct/range {p0 .. p2}, Lcom/exteragram/messenger/debug/LoadMonitor;->formatDuration(J)Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, p3, p4, p1, p2}, Lcom/exteragram/messenger/debug/LoadMonitor;->formatPercent(JJ)Ljava/lang/String;

    move-result-object p1

    new-instance p2, Ljava/lang/StringBuilder;

    const-string v2, "In "

    invoke-direct {p2, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " in the background, the app used "

    invoke-virtual {p2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " of one core: "

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 440
    invoke-direct {p0, p3, p4}, Lcom/exteragram/messenger/debug/LoadMonitor;->formatDuration(J)Ljava/lang/String;

    move-result-object p0

    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " of CPU"

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-wide/16 p0, 0x0

    cmp-long p2, p5, p0

    if-ltz p2, :cond_0

    cmp-long p0, p7, p0

    if-ltz p0, :cond_0

    .line 442
    invoke-static/range {p5 .. p6}, Lorg/telegram/messenger/AndroidUtilities;->formatFileSize(J)Ljava/lang/String;

    move-result-object p0

    invoke-static/range {p7 .. p8}, Lorg/telegram/messenger/AndroidUtilities;->formatFileSize(J)Ljava/lang/String;

    move-result-object p1

    new-instance p2, Ljava/lang/StringBuilder;

    const-string p3, ", "

    invoke-direct {p2, p3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " received, "

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " sent"

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_0
    const/16 p0, 0x2e

    .line 444
    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 445
    move-object/from16 p1, p9

    check-cast p1, Ljava/util/Collection;

    invoke-interface {p1}, Ljava/util/Collection;->isEmpty()Z

    move-result p1

    if-nez p1, :cond_1

    .line 446
    const-string p1, "\n\nBusiest threads: "

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 447
    move-object/from16 v1, p9

    check-cast v1, Ljava/lang/Iterable;

    new-instance v7, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda8;

    invoke-direct {v7}, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda8;-><init>()V

    const/16 v8, 0x1e

    const/4 v9, 0x0

    const-string v2, ", "

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    invoke-static/range {v1 .. v9}, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 448
    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 450
    :cond_1
    const-string p0, "\n\nThe report holds thread names, traffic counters and battery stats, no message content."

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 451
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final formatDuration(J)Ljava/lang/String;
    .locals 6

    const-wide/16 v0, 0x3e8

    .line 425
    div-long v2, p1, v0

    cmp-long p0, p1, v0

    if-gez p0, :cond_0

    .line 427
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0, p1, p2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p1, "ms"

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_0
    const-wide/16 p0, 0x3c

    cmp-long p2, v2, p0

    .line 428
    const-string v0, "s"

    if-gez p2, :cond_1

    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_1
    const-wide/16 v4, 0xe10

    cmp-long p2, v2, v4

    if-gez p2, :cond_2

    .line 429
    div-long v4, v2, p0

    rem-long/2addr v2, p0

    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0, v4, v5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p1, "m "

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 430
    :cond_2
    div-long v0, v2, v4

    rem-long/2addr v2, v4

    div-long/2addr v2, p0

    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p1, "h "

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p1, "m"

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final formatPercent(JJ)Ljava/lang/String;
    .locals 2

    .line 435
    sget-object p0, Lkotlin/jvm/internal/StringCompanionObject;->INSTANCE:Lkotlin/jvm/internal/StringCompanionObject;

    sget-object p0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const-wide/16 v0, 0x0

    cmp-long v0, p3, v0

    if-lez v0, :cond_0

    long-to-double p1, p1

    const-wide/high16 v0, 0x4059000000000000L    # 100.0

    mul-double/2addr p1, v0

    long-to-double p3, p3

    div-double/2addr p1, p3

    goto :goto_0

    :cond_0
    const-wide/16 p1, 0x0

    :goto_0
    invoke-static {p1, p2}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

    move-result-object p1

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    const/4 p2, 0x1

    invoke-static {p1, p2}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object p1

    const-string p2, "%.1f%%"

    invoke-static {p0, p2, p1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final getClockTicks()J
    .locals 2

    .line 97
    sget-object p0, Lcom/exteragram/messenger/debug/LoadMonitor;->clockTicks$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Number;

    invoke-virtual {p0}, Ljava/lang/Number;->longValue()J

    move-result-wide v0

    return-wide v0
.end method

.method private final healthJson(Landroid/os/health/HealthStats;)Lorg/json/JSONObject;
    .locals 14

    .line 317
    invoke-virtual {p1}, Landroid/os/health/HealthStats;->getDataType()Ljava/lang/String;

    move-result-object v0

    .line 318
    new-instance v1, Lorg/json/JSONObject;

    invoke-direct {v1}, Lorg/json/JSONObject;-><init>()V

    const-string v2, "type"

    invoke-virtual {v1, v2, v0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v1

    .line 319
    invoke-virtual {p1}, Landroid/os/health/HealthStats;->getMeasurementKeyCount()I

    move-result v2

    const/4 v3, 0x0

    if-lez v2, :cond_1

    .line 320
    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2}, Lorg/json/JSONObject;-><init>()V

    .line 321
    invoke-virtual {p1}, Landroid/os/health/HealthStats;->getMeasurementKeyCount()I

    move-result v4

    move v5, v3

    :goto_0
    if-ge v5, v4, :cond_0

    .line 322
    invoke-virtual {p1, v5}, Landroid/os/health/HealthStats;->getMeasurementKeyAt(I)I

    move-result v6

    .line 323
    invoke-direct {p0, v0, v6}, Lcom/exteragram/messenger/debug/LoadMonitor;->healthKeyName(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v7

    invoke-virtual {p1, v6}, Landroid/os/health/HealthStats;->getMeasurement(I)J

    move-result-wide v8

    invoke-virtual {v2, v7, v8, v9}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    add-int/lit8 v5, v5, 0x1

    goto :goto_0

    .line 325
    :cond_0
    const-string v4, "measurements"

    invoke-virtual {v1, v4, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 327
    :cond_1
    invoke-virtual {p1}, Landroid/os/health/HealthStats;->getTimerKeyCount()I

    move-result v2

    if-lez v2, :cond_3

    .line 328
    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2}, Lorg/json/JSONObject;-><init>()V

    .line 329
    invoke-virtual {p1}, Landroid/os/health/HealthStats;->getTimerKeyCount()I

    move-result v4

    move v5, v3

    :goto_1
    if-ge v5, v4, :cond_2

    .line 330
    invoke-virtual {p1, v5}, Landroid/os/health/HealthStats;->getTimerKeyAt(I)I

    move-result v6

    .line 331
    invoke-direct {p0, v0, v6}, Lcom/exteragram/messenger/debug/LoadMonitor;->healthKeyName(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v7

    new-instance v8, Lorg/json/JSONArray;

    invoke-direct {v8}, Lorg/json/JSONArray;-><init>()V

    invoke-virtual {p1, v6}, Landroid/os/health/HealthStats;->getTimerCount(I)I

    move-result v9

    invoke-virtual {v8, v9}, Lorg/json/JSONArray;->put(I)Lorg/json/JSONArray;

    move-result-object v8

    invoke-virtual {p1, v6}, Landroid/os/health/HealthStats;->getTimerTime(I)J

    move-result-wide v9

    invoke-virtual {v8, v9, v10}, Lorg/json/JSONArray;->put(J)Lorg/json/JSONArray;

    move-result-object v6

    invoke-virtual {v2, v7, v6}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    add-int/lit8 v5, v5, 0x1

    goto :goto_1

    .line 333
    :cond_2
    const-string v4, "timers"

    invoke-virtual {v1, v4, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 335
    :cond_3
    invoke-virtual {p1}, Landroid/os/health/HealthStats;->getMeasurementsKeyCount()I

    move-result v2

    if-lez v2, :cond_6

    .line 336
    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2}, Lorg/json/JSONObject;-><init>()V

    .line 337
    invoke-virtual {p1}, Landroid/os/health/HealthStats;->getMeasurementsKeyCount()I

    move-result v4

    move v5, v3

    :goto_2
    if-ge v5, v4, :cond_5

    .line 338
    invoke-virtual {p1, v5}, Landroid/os/health/HealthStats;->getMeasurementsKeyAt(I)I

    move-result v6

    .line 339
    new-instance v7, Lorg/json/JSONObject;

    invoke-direct {v7}, Lorg/json/JSONObject;-><init>()V

    .line 340
    invoke-virtual {p1, v6}, Landroid/os/health/HealthStats;->getMeasurements(I)Ljava/util/Map;

    move-result-object v8

    invoke-interface {v8}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v8

    invoke-interface {v8}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v8

    :goto_3
    invoke-interface {v8}, Ljava/util/Iterator;->hasNext()Z

    move-result v9

    if-eqz v9, :cond_4

    invoke-interface {v8}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v9

    check-cast v9, Ljava/util/Map$Entry;

    invoke-interface {v9}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Ljava/lang/String;

    invoke-interface {v9}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v9

    check-cast v9, Ljava/lang/Long;

    .line 341
    invoke-virtual {v9}, Ljava/lang/Long;->longValue()J

    move-result-wide v11

    invoke-virtual {v7, v10, v11, v12}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    goto :goto_3

    .line 343
    :cond_4
    invoke-direct {p0, v0, v6}, Lcom/exteragram/messenger/debug/LoadMonitor;->healthKeyName(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v2, v6, v7}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    add-int/lit8 v5, v5, 0x1

    goto :goto_2

    .line 345
    :cond_5
    const-string v4, "measurementMaps"

    invoke-virtual {v1, v4, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 347
    :cond_6
    invoke-virtual {p1}, Landroid/os/health/HealthStats;->getTimersKeyCount()I

    move-result v2

    if-lez v2, :cond_9

    .line 348
    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2}, Lorg/json/JSONObject;-><init>()V

    .line 349
    invoke-virtual {p1}, Landroid/os/health/HealthStats;->getTimersKeyCount()I

    move-result v4

    move v5, v3

    :goto_4
    if-ge v5, v4, :cond_8

    .line 350
    invoke-virtual {p1, v5}, Landroid/os/health/HealthStats;->getTimersKeyAt(I)I

    move-result v6

    .line 351
    new-instance v7, Lorg/json/JSONObject;

    invoke-direct {v7}, Lorg/json/JSONObject;-><init>()V

    .line 352
    invoke-virtual {p1, v6}, Landroid/os/health/HealthStats;->getTimers(I)Ljava/util/Map;

    move-result-object v8

    invoke-interface {v8}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v8

    invoke-interface {v8}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v8

    :goto_5
    invoke-interface {v8}, Ljava/util/Iterator;->hasNext()Z

    move-result v9

    if-eqz v9, :cond_7

    invoke-interface {v8}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v9

    check-cast v9, Ljava/util/Map$Entry;

    invoke-interface {v9}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Ljava/lang/String;

    invoke-interface {v9}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v9

    check-cast v9, Landroid/os/health/TimerStat;

    .line 353
    new-instance v11, Lorg/json/JSONArray;

    invoke-direct {v11}, Lorg/json/JSONArray;-><init>()V

    invoke-virtual {v9}, Landroid/os/health/TimerStat;->getCount()I

    move-result v12

    invoke-virtual {v11, v12}, Lorg/json/JSONArray;->put(I)Lorg/json/JSONArray;

    move-result-object v11

    invoke-virtual {v9}, Landroid/os/health/TimerStat;->getTime()J

    move-result-wide v12

    invoke-virtual {v11, v12, v13}, Lorg/json/JSONArray;->put(J)Lorg/json/JSONArray;

    move-result-object v9

    invoke-virtual {v7, v10, v9}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    goto :goto_5

    .line 355
    :cond_7
    invoke-direct {p0, v0, v6}, Lcom/exteragram/messenger/debug/LoadMonitor;->healthKeyName(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v2, v6, v7}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    add-int/lit8 v5, v5, 0x1

    goto :goto_4

    .line 357
    :cond_8
    const-string v4, "timerMaps"

    invoke-virtual {v1, v4, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 359
    :cond_9
    invoke-virtual {p1}, Landroid/os/health/HealthStats;->getStatsKeyCount()I

    move-result v2

    if-lez v2, :cond_c

    .line 360
    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2}, Lorg/json/JSONObject;-><init>()V

    .line 361
    invoke-virtual {p1}, Landroid/os/health/HealthStats;->getStatsKeyCount()I

    move-result v4

    :goto_6
    if-ge v3, v4, :cond_b

    .line 362
    invoke-virtual {p1, v3}, Landroid/os/health/HealthStats;->getStatsKeyAt(I)I

    move-result v5

    .line 363
    new-instance v6, Lorg/json/JSONObject;

    invoke-direct {v6}, Lorg/json/JSONObject;-><init>()V

    .line 364
    invoke-virtual {p1, v5}, Landroid/os/health/HealthStats;->getStats(I)Ljava/util/Map;

    move-result-object v7

    invoke-interface {v7}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v7

    invoke-interface {v7}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v7

    :goto_7
    invoke-interface {v7}, Ljava/util/Iterator;->hasNext()Z

    move-result v8

    if-eqz v8, :cond_a

    invoke-interface {v7}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Ljava/util/Map$Entry;

    invoke-interface {v8}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v9

    check-cast v9, Ljava/lang/String;

    invoke-interface {v8}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Landroid/os/health/HealthStats;

    .line 365
    invoke-direct {p0, v8}, Lcom/exteragram/messenger/debug/LoadMonitor;->healthJson(Landroid/os/health/HealthStats;)Lorg/json/JSONObject;

    move-result-object v8

    invoke-virtual {v6, v9, v8}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    goto :goto_7

    .line 367
    :cond_a
    invoke-direct {p0, v0, v5}, Lcom/exteragram/messenger/debug/LoadMonitor;->healthKeyName(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v2, v5, v6}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    add-int/lit8 v3, v3, 0x1

    goto :goto_6

    .line 369
    :cond_b
    const-string p0, "stats"

    invoke-virtual {v1, p0, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    :cond_c
    return-object v1
.end method

.method private final healthKeyName(Ljava/lang/String;I)Ljava/lang/String;
    .locals 9

    .line 303
    const-string p0, "android.os.health."

    sget-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->healthKeyNames:Ljava/util/HashMap;

    monitor-enter v0

    .line 410
    :try_start_0
    invoke-interface {v0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    if-nez v1, :cond_5

    .line 305
    :try_start_1
    sget-object v1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    const/16 v1, 0x2e

    const/4 v2, 0x2

    const/4 v3, 0x0

    const/4 v4, 0x0

    .line 306
    invoke-static {p1, v1, v4, v2, v3}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;CZILjava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    move-object p0, p1

    goto :goto_0

    :cond_0
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1, p0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 307
    :goto_0
    invoke-static {p0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Class;->getFields()[Ljava/lang/reflect/Field;

    move-result-object p0

    .line 3938
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 4474
    array-length v2, p0

    move v5, v4

    :goto_1
    if-ge v5, v2, :cond_2

    aget-object v6, p0, v5

    .line 308
    invoke-virtual {v6}, Ljava/lang/reflect/Field;->getModifiers()I

    move-result v7

    invoke-static {v7}, Ljava/lang/reflect/Modifier;->isStatic(I)Z

    move-result v7

    if-eqz v7, :cond_1

    invoke-virtual {v6}, Ljava/lang/reflect/Field;->getType()Ljava/lang/Class;

    move-result-object v7

    sget-object v8, Ljava/lang/Integer;->TYPE:Ljava/lang/Class;

    invoke-static {v7, v8}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v7

    if-eqz v7, :cond_1

    .line 4474
    invoke-interface {v1, v6}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_2

    :catchall_0
    move-exception p0

    goto :goto_4

    :cond_1
    :goto_2
    add-int/lit8 v5, v5, 0x1

    goto :goto_1

    :cond_2
    const/16 p0, 0xa

    .line 1205
    invoke-static {v1, p0}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result p0

    invoke-static {p0}, Lkotlin/collections/MapsKt;->mapCapacity(I)I

    move-result p0

    const/16 v2, 0x10

    invoke-static {p0, v2}, Lkotlin/ranges/RangesKt;->coerceAtLeast(II)I

    move-result p0

    .line 1206
    new-instance v2, Ljava/util/LinkedHashMap;

    invoke-direct {v2, p0}, Ljava/util/LinkedHashMap;-><init>(I)V

    .line 1282
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result p0

    :goto_3
    if-ge v4, p0, :cond_3

    invoke-virtual {v1, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    add-int/lit8 v4, v4, 0x1

    .line 1283
    check-cast v5, Ljava/lang/reflect/Field;

    .line 309
    invoke-virtual {v5, v3}, Ljava/lang/reflect/Field;->getInt(Ljava/lang/Object;)I

    move-result v6

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    invoke-virtual {v5}, Ljava/lang/reflect/Field;->getName()Ljava/lang/String;

    move-result-object v5

    invoke-static {v6, v5}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object v5

    .line 1283
    invoke-virtual {v5}, Lkotlin/Pair;->getFirst()Ljava/lang/Object;

    move-result-object v6

    invoke-virtual {v5}, Lkotlin/Pair;->getSecond()Ljava/lang/Object;

    move-result-object v5

    invoke-interface {v2, v6, v5}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_3

    .line 305
    :cond_3
    invoke-static {v2}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_5

    :goto_4
    :try_start_2
    sget-object v1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 310
    :goto_5
    invoke-static {}, Lkotlin/collections/MapsKt;->emptyMap()Ljava/util/Map;

    move-result-object v1

    invoke-static {p0}, Lkotlin/Result;->isFailure-impl(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_4

    move-object p0, v1

    :cond_4
    move-object v1, p0

    check-cast v1, Ljava/util/Map;

    .line 413
    invoke-interface {v0, p1, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_6

    :catchall_1
    move-exception p0

    goto :goto_7

    .line 411
    :cond_5
    :goto_6
    check-cast v1, Ljava/util/Map;
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    .line 303
    monitor-exit v0

    .line 313
    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    invoke-interface {v1, p0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;

    if-nez p0, :cond_6

    invoke-static {p2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    :cond_6
    return-object p0

    .line 303
    :goto_7
    monitor-exit v0

    throw p0
.end method

.method public static final init()V
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 103
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getLoadMonitorEnabled()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 104
    new-instance v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;

    invoke-direct {v0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->sampler:Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;

    :cond_0
    return-void
.end method

.method private final isPlugged()Ljava/lang/Boolean;
    .locals 3

    const/4 p0, 0x0

    .line 196
    :try_start_0
    sget-object v0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 197
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    new-instance v1, Landroid/content/IntentFilter;

    const-string v2, "android.intent.action.BATTERY_CHANGED"

    invoke-direct {v1, v2}, Landroid/content/IntentFilter;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p0, v1}, Landroid/content/Context;->registerReceiver(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 198
    const-string v1, "plugged"

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result v0

    if-eqz v0, :cond_0

    const/4 v2, 0x1

    :cond_0
    invoke-static {v2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    goto :goto_0

    :catchall_0
    move-exception v0

    goto :goto_1

    :cond_1
    move-object v0, p0

    .line 196
    :goto_0
    invoke-static {v0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_2

    :goto_1
    sget-object v1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {v0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object v0

    invoke-static {v0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 199
    :goto_2
    invoke-static {v0}, Lkotlin/Result;->isFailure-impl(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    goto :goto_3

    :cond_2
    move-object p0, v0

    :goto_3
    check-cast p0, Ljava/lang/Boolean;

    return-object p0
.end method

.method private final originOf(Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    .line 267
    sget-object p0, Lcom/exteragram/messenger/debug/LoadMonitor;->threadOrigins:Ljava/util/HashMap;

    monitor-enter p0

    :try_start_0
    invoke-virtual {p0, p1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    :catchall_0
    move-exception p1

    monitor-exit p0

    throw p1
.end method

.method private final parseStat(Ljava/lang/String;)Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;
    .locals 10

    const/4 v4, 0x6

    const/4 v5, 0x0

    const/16 v1, 0x28

    const/4 v2, 0x0

    const/4 v3, 0x0

    move-object v0, p1

    .line 228
    invoke-static/range {v0 .. v5}, Lkotlin/text/StringsKt;->indexOf$default(Ljava/lang/CharSequence;CIZILjava/lang/Object;)I

    move-result p0

    const/16 v1, 0x29

    .line 229
    invoke-static/range {v0 .. v5}, Lkotlin/text/StringsKt;->lastIndexOf$default(Ljava/lang/CharSequence;CIZILjava/lang/Object;)I

    move-result p1

    const/4 v1, 0x0

    if-ltz p0, :cond_2

    if-le p1, p0, :cond_2

    add-int/lit8 v2, p1, 0x2

    .line 230
    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v3

    if-lt v2, v3, :cond_0

    goto :goto_0

    .line 231
    :cond_0
    invoke-virtual {v0, v2}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v4

    const/4 v2, 0x1

    new-array v5, v2, [C

    const/16 v3, 0x20

    const/4 v6, 0x0

    aput-char v3, v5, v6

    const/4 v8, 0x6

    const/4 v9, 0x0

    const/4 v7, 0x0

    invoke-static/range {v4 .. v9}, Lkotlin/text/StringsKt;->split$default(Ljava/lang/CharSequence;[CZIILjava/lang/Object;)Ljava/util/List;

    move-result-object v3

    .line 232
    invoke-interface {v3}, Ljava/util/List;->size()I

    move-result v4

    const/16 v5, 0xd

    if-ge v4, v5, :cond_1

    return-object v1

    :cond_1
    const/16 v4, 0xb

    .line 233
    invoke-interface {v3, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    invoke-static {v4}, Lkotlin/text/StringsKt;->toLongOrNull(Ljava/lang/String;)Ljava/lang/Long;

    move-result-object v4

    if-eqz v4, :cond_2

    invoke-virtual {v4}, Ljava/lang/Long;->longValue()J

    move-result-wide v4

    const/16 v6, 0xc

    .line 234
    invoke-interface {v3, v6}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    invoke-static {v3}, Lkotlin/text/StringsKt;->toLongOrNull(Ljava/lang/String;)Ljava/lang/Long;

    move-result-object v3

    if-eqz v3, :cond_2

    invoke-virtual {v3}, Ljava/lang/Long;->longValue()J

    move-result-wide v6

    .line 235
    new-instance v1, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;

    add-int/2addr p0, v2

    invoke-virtual {v0, p0, p1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p0

    add-long/2addr v4, v6

    invoke-direct {v1, p0, v4, v5}, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;-><init>(Ljava/lang/String;J)V

    :cond_2
    :goto_0
    return-object v1
.end method

.method private final readThreads()Ljava/util/HashMap;
    .locals 11
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/HashMap<",
            "Ljava/lang/Integer;",
            "Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;",
            ">;"
        }
    .end annotation

    .line 210
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    .line 211
    new-instance v1, Ljava/io/File;

    const-string v2, "/proc/self/task"

    invoke-direct {v1, v2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1}, Ljava/io/File;->list()[Ljava/lang/String;

    move-result-object v1

    if-nez v1, :cond_0

    goto :goto_2

    :cond_0
    const/16 v2, 0x400

    .line 212
    new-array v2, v2, [B

    .line 213
    array-length v3, v1

    const/4 v4, 0x0

    move v5, v4

    :goto_0
    if-ge v5, v3, :cond_2

    aget-object v6, v1, v5

    .line 214
    invoke-static {v6}, Lkotlin/text/StringsKt;->toIntOrNull(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v7

    if-eqz v7, :cond_1

    .line 216
    :try_start_0
    new-instance v8, Ljava/io/FileInputStream;

    new-instance v9, Ljava/lang/StringBuilder;

    invoke-direct {v9}, Ljava/lang/StringBuilder;-><init>()V

    const-string v10, "/proc/self/task/"

    invoke-virtual {v9, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, "/stat"

    invoke-virtual {v9, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-direct {v8, v6}, Ljava/io/FileInputStream;-><init>(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :try_start_1
    invoke-virtual {v8, v2}, Ljava/io/FileInputStream;->read([B)I

    move-result v6
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    const/4 v9, 0x0

    :try_start_2
    invoke-static {v8, v9}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    if-lez v6, :cond_1

    .line 221
    new-instance v8, Ljava/lang/String;

    sget-object v9, Lkotlin/text/Charsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v8, v2, v4, v6, v9}, Ljava/lang/String;-><init>([BIILjava/nio/charset/Charset;)V

    invoke-direct {p0, v8}, Lcom/exteragram/messenger/debug/LoadMonitor;->parseStat(Ljava/lang/String;)Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;

    move-result-object v6

    if-eqz v6, :cond_1

    invoke-interface {v0, v7, v6}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_1

    :catchall_0
    move-exception v6

    .line 216
    :try_start_3
    throw v6
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    :catchall_1
    move-exception v7

    :try_start_4
    invoke-static {v8, v6}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    throw v7
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_0

    :catch_0
    :cond_1
    :goto_1
    add-int/lit8 v5, v5, 0x1

    goto :goto_0

    :cond_2
    :goto_2
    return-object v0
.end method

.method private final resolveOrigins(Ljava/util/List;)V
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    .line 270
    sget-object p0, Lcom/exteragram/messenger/debug/LoadMonitor;->threadOrigins:Ljava/util/HashMap;

    monitor-enter p0

    .line 271
    :try_start_0
    check-cast p1, Ljava/lang/Iterable;

    .line 777
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 873
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_0
    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-eqz v1, :cond_3

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    move-object v4, v1

    check-cast v4, Ljava/lang/String;

    .line 272
    const-string v5, "<unattributed>"

    invoke-static {v4, v5}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_2

    sget-object v5, Lcom/exteragram/messenger/debug/LoadMonitor;->threadOrigins:Ljava/util/HashMap;

    invoke-virtual {v5, v4}, Ljava/util/HashMap;->containsKey(Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_2

    sget-object v5, Lcom/exteragram/messenger/debug/LoadMonitor;->originAttempts:Ljava/util/HashMap;

    invoke-virtual {v5, v4}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/Integer;

    if-eqz v4, :cond_1

    invoke-virtual {v4}, Ljava/lang/Integer;->intValue()I

    move-result v4

    goto :goto_1

    :catchall_0
    move-exception p1

    goto/16 :goto_9

    :cond_1
    move v4, v3

    :goto_1
    const/16 v5, 0x8

    if-ge v4, v5, :cond_2

    goto :goto_2

    :cond_2
    move v2, v3

    :goto_2
    if-eqz v2, :cond_0

    .line 873
    invoke-interface {v0, v1}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    .line 270
    :cond_3
    monitor-exit p0

    .line 275
    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result p0

    if-eqz p0, :cond_4

    goto :goto_4

    .line 276
    :cond_4
    :try_start_1
    sget-object p0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {}, Ljava/lang/Thread;->getAllStackTraces()Ljava/util/Map;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_3

    :catchall_1
    move-exception p0

    sget-object p1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    :goto_3
    invoke-static {p0}, Lkotlin/Result;->isFailure-impl(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_5

    const/4 p0, 0x0

    :cond_5
    check-cast p0, Ljava/util/Map;

    if-nez p0, :cond_6

    :goto_4
    return-void

    .line 277
    :cond_6
    sget-object p1, Lcom/exteragram/messenger/debug/LoadMonitor;->threadOrigins:Ljava/util/HashMap;

    monitor-enter p1

    .line 278
    :try_start_2
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    move v4, v3

    :goto_5
    if-ge v4, v1, :cond_8

    invoke-virtual {v0, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    add-int/lit8 v4, v4, 0x1

    check-cast v5, Ljava/lang/String;

    .line 279
    sget-object v6, Lcom/exteragram/messenger/debug/LoadMonitor;->originAttempts:Ljava/util/HashMap;

    invoke-virtual {v6, v5}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/Integer;

    if-eqz v7, :cond_7

    invoke-virtual {v7}, Ljava/lang/Integer;->intValue()I

    move-result v7

    goto :goto_6

    :catchall_2
    move-exception p0

    goto :goto_8

    :cond_7
    move v7, v3

    :goto_6
    add-int/2addr v7, v2

    invoke-static {v7}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    invoke-interface {v6, v5, v7}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_5

    .line 281
    :cond_8
    invoke-interface {p0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_9
    :goto_7
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_b

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Thread;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [Ljava/lang/StackTraceElement;

    .line 282
    invoke-virtual {v2}, Ljava/lang/Thread;->getName()Ljava/lang/String;

    move-result-object v2

    const/16 v3, 0xf

    invoke-static {v2, v3}, Lkotlin/text/StringsKt;->take(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v2

    .line 283
    invoke-interface {v0, v2}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_9

    sget-object v3, Lcom/exteragram/messenger/debug/LoadMonitor;->threadOrigins:Ljava/util/HashMap;

    invoke-virtual {v3, v2}, Ljava/util/HashMap;->containsKey(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_a

    goto :goto_7

    .line 284
    :cond_a
    sget-object v4, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-direct {v4, v1}, Lcom/exteragram/messenger/debug/LoadMonitor;->appFrame([Ljava/lang/StackTraceElement;)Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_9

    invoke-interface {v3, v2, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_7

    .line 286
    :cond_b
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_2

    .line 277
    monitor-exit p1

    return-void

    :goto_8
    monitor-exit p1

    throw p0

    .line 270
    :goto_9
    monitor-exit p0

    throw p1
.end method

.method private final share(Landroid/app/Activity;Ljava/io/File;)V
    .locals 1

    .line 163
    const-string p0, "application/json"

    const-string v0, "Share load report"

    invoke-static {p1, p2, p0, v0}, Lcom/exteragram/messenger/debug/DebugFilesKt;->shareDebugFile(Landroid/app/Activity;Ljava/io/File;Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method private final showPendingAlert()V
    .locals 4

    .line 167
    sget-object p0, Lcom/exteragram/messenger/debug/LoadMonitor;->pendingAlert:Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;

    if-nez p0, :cond_0

    goto :goto_0

    .line 168
    :cond_0
    sget-object v0, Lorg/telegram/ui/LaunchActivity;->instance:Lorg/telegram/ui/LaunchActivity;

    .line 169
    sget-object v1, Lcom/exteragram/messenger/debug/LoadMonitor;->sampler:Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;

    if-eqz v1, :cond_2

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Landroid/app/Activity;->isFinishing()Z

    move-result v1

    if-nez v1, :cond_2

    sget-object v1, Lcom/exteragram/messenger/debug/LoadMonitor;->alertRef:Ljava/lang/ref/WeakReference;

    if-eqz v1, :cond_1

    invoke-virtual {v1}, Ljava/lang/ref/Reference;->get()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lorg/telegram/ui/ActionBar/AlertDialog;

    if-eqz v1, :cond_1

    invoke-virtual {v1}, Landroid/app/Dialog;->isShowing()Z

    move-result v1

    const/4 v2, 0x1

    if-ne v1, v2, :cond_1

    return-void

    :cond_1
    const/4 v1, 0x0

    .line 172
    sput-object v1, Lcom/exteragram/messenger/debug/LoadMonitor;->pendingAlert:Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;

    .line 173
    new-instance v2, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-direct {v2, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 174
    const-string v3, "High background load"

    invoke-virtual {v2, v3}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object v2

    .line 175
    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;->getMessage()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object v2

    .line 176
    new-instance v3, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda2;

    invoke-direct {v3, v0, p0}, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/ui/LaunchActivity;Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;)V

    const-string p0, "Share"

    invoke-virtual {v2, p0, v3}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p0

    .line 177
    new-instance v0, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda3;

    invoke-direct {v0}, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda3;-><init>()V

    const-string v2, "Turn off"

    invoke-virtual {p0, v2, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNeutralButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p0

    .line 178
    const-string v0, "Later"

    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p0

    .line 179
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->create()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object p0

    .line 180
    new-instance v0, Ljava/lang/ref/WeakReference;

    invoke-direct {v0, p0}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    sput-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->alertRef:Ljava/lang/ref/WeakReference;

    .line 182
    :try_start_0
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->show()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 184
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :cond_2
    :goto_0
    return-void
.end method

.method private final takeHealthStats()Landroid/os/health/HealthStats;
    .locals 2

    const/4 p0, 0x0

    .line 297
    :try_start_0
    sget-object v0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 298
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const-string v1, "systemhealth"

    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    instance-of v1, v0, Landroid/os/health/SystemHealthManager;

    if-eqz v1, :cond_0

    check-cast v0, Landroid/os/health/SystemHealthManager;

    goto :goto_0

    :catchall_0
    move-exception v0

    goto :goto_2

    :cond_0
    move-object v0, p0

    :goto_0
    if-eqz v0, :cond_1

    .line 299
    invoke-virtual {v0}, Landroid/os/health/SystemHealthManager;->takeMyUidSnapshot()Landroid/os/health/HealthStats;

    move-result-object v0

    goto :goto_1

    :cond_1
    move-object v0, p0

    .line 297
    :goto_1
    invoke-static {v0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_3

    :goto_2
    sget-object v1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {v0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object v0

    invoke-static {v0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 300
    :goto_3
    invoke-static {v0}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object v1

    if-eqz v1, :cond_2

    invoke-static {v1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :cond_2
    invoke-static {v0}, Lkotlin/Result;->isFailure-impl(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_3

    goto :goto_4

    :cond_3
    move-object p0, v0

    :goto_4
    check-cast p0, Landroid/os/health/HealthStats;

    return-object p0
.end method

.method private final threadDeltas(Ljava/util/Map;Ljava/util/Map;)Ljava/util/HashMap;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map<",
            "Ljava/lang/Integer;",
            "Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;",
            ">;",
            "Ljava/util/Map<",
            "Ljava/lang/Integer;",
            "Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;",
            ">;)",
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation

    .line 241
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    .line 242
    invoke-interface {p2}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p2

    invoke-interface {p2}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p2

    :cond_0
    :goto_0
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_3

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Number;

    invoke-virtual {v2}, Ljava/lang/Number;->intValue()I

    move-result v2

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;

    .line 243
    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {p1, v2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;

    if-eqz v2, :cond_1

    .line 244
    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;->getName()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1}, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;->getName()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    invoke-virtual {v1}, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;->getTicks()J

    move-result-wide v3

    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;->getTicks()J

    move-result-wide v5

    sub-long/2addr v3, v5

    goto :goto_1

    :cond_1
    invoke-virtual {v1}, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;->getTicks()J

    move-result-wide v3

    :goto_1
    const-wide/16 v5, 0x0

    cmp-long v2, v3, v5

    if-lez v2, :cond_0

    .line 246
    invoke-virtual {v1}, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;->getName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1}, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Long;

    if-eqz v1, :cond_2

    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v5

    :cond_2
    invoke-direct {p0, v3, v4}, Lcom/exteragram/messenger/debug/LoadMonitor;->ticksToMs(J)J

    move-result-wide v3

    add-long/2addr v5, v3

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-interface {v0, v2, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0

    :cond_3
    return-object v0
.end method

.method private final threadsJson(Ljava/util/List;Z)Lorg/json/JSONArray;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lkotlin/Pair<",
            "Ljava/lang/String;",
            "Ljava/lang/Long;",
            ">;>;Z)",
            "Lorg/json/JSONArray;"
        }
    .end annotation

    .line 256
    new-instance v0, Lorg/json/JSONArray;

    invoke-direct {v0}, Lorg/json/JSONArray;-><init>()V

    .line 257
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lkotlin/Pair;

    invoke-virtual {v1}, Lkotlin/Pair;->component1()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-virtual {v1}, Lkotlin/Pair;->component2()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Number;

    invoke-virtual {v1}, Ljava/lang/Number;->longValue()J

    move-result-wide v3

    .line 258
    new-instance v1, Lorg/json/JSONObject;

    invoke-direct {v1}, Lorg/json/JSONObject;-><init>()V

    const-string v5, "name"

    invoke-virtual {v1, v5, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v1

    const-string v5, "cpuMs"

    invoke-virtual {v1, v5, v3, v4}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v1

    if-eqz p2, :cond_0

    .line 260
    const-string v3, "at"

    invoke-direct {p0, v2}, Lcom/exteragram/messenger/debug/LoadMonitor;->originOf(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v3, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 262
    :cond_0
    invoke-virtual {v0, v1}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;

    goto :goto_0

    :cond_1
    return-object v0
.end method

.method public static synthetic threadsJson$default(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/util/List;ZILjava/lang/Object;)Lorg/json/JSONArray;
    .locals 0

    and-int/lit8 p3, p3, 0x2

    if-eqz p3, :cond_0

    const/4 p2, 0x0

    .line 255
    :cond_0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/debug/LoadMonitor;->threadsJson(Ljava/util/List;Z)Lorg/json/JSONArray;

    move-result-object p0

    return-object p0
.end method

.method private final ticksToMs(J)J
    .locals 2

    const-wide/16 v0, 0x3e8

    mul-long/2addr p1, v0

    .line 238
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->getClockTicks()J

    move-result-wide v0

    div-long/2addr p1, v0

    return-wide p1
.end method

.method private final topThreads(Ljava/util/Map;I)Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Long;",
            ">;I)",
            "Ljava/util/List<",
            "Lkotlin/Pair<",
            "Ljava/lang/String;",
            "Ljava/lang/Long;",
            ">;>;"
        }
    .end annotation

    .line 253
    invoke-interface {p1}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p0

    .line 1080
    new-instance p1, Lcom/exteragram/messenger/debug/LoadMonitor$topThreads$$inlined$sortedByDescending$1;

    invoke-direct {p1}, Lcom/exteragram/messenger/debug/LoadMonitor$topThreads$$inlined$sortedByDescending$1;-><init>()V

    invoke-static {p0, p1}, Lkotlin/collections/CollectionsKt;->sortedWith(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/util/List;

    move-result-object p0

    check-cast p0, Ljava/lang/Iterable;

    .line 253
    invoke-static {p0, p2}, Lkotlin/collections/CollectionsKt;->take(Ljava/lang/Iterable;I)Ljava/util/List;

    move-result-object p0

    check-cast p0, Ljava/lang/Iterable;

    .line 1586
    new-instance p1, Ljava/util/ArrayList;

    const/16 p2, 0xa

    invoke-static {p0, p2}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result p2

    invoke-direct {p1, p2}, Ljava/util/ArrayList;-><init>(I)V

    .line 1661
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p2

    if-eqz p2, :cond_0

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p2

    .line 1662
    check-cast p2, Ljava/util/Map$Entry;

    .line 253
    invoke-interface {p2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v0

    invoke-interface {p2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object p2

    invoke-static {v0, p2}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object p2

    .line 1662
    invoke-interface {p1, p2}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_0
    return-object p1
.end method

.method private final uidRxBytes()J
    .locals 2

    .line 205
    invoke-static {}, Landroid/os/Process;->myUid()I

    move-result p0

    invoke-static {p0}, Landroid/net/TrafficStats;->getUidRxBytes(I)J

    move-result-wide v0

    return-wide v0
.end method

.method private final uidTxBytes()J
    .locals 2

    .line 207
    invoke-static {}, Landroid/os/Process;->myUid()I

    move-result p0

    invoke-static {p0}, Landroid/net/TrafficStats;->getUidTxBytes(I)J

    move-result-wide v0

    return-wide v0
.end method

.method private final writeReport(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/io/File;
    .locals 8

    .line 412
    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->getReportsDir()Ljava/io/File;

    move-result-object v0

    invoke-virtual {v0}, Ljava/io/File;->mkdirs()Z

    .line 413
    sget-object v0, Lorg/telegram/messenger/BuildVars;->BUILD_VERSION_STRING:Ljava/lang/String;

    new-instance v1, Ljava/text/SimpleDateFormat;

    const-string v2, "yyyyMMdd_HHmmss"

    sget-object v3, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-direct {v1, v2, v3}, Ljava/text/SimpleDateFormat;-><init>(Ljava/lang/String;Ljava/util/Locale;)V

    new-instance v2, Ljava/util/Date;

    invoke-direct {v2}, Ljava/util/Date;-><init>()V

    invoke-virtual {v1, v2}, Ljava/text/DateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object v1

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "load_"

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, "_"

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p2, ".json"

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 414
    new-instance v1, Ljava/io/File;

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->getReportsDir()Ljava/io/File;

    move-result-object v2

    invoke-direct {v1, v2, v0}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    const/4 v0, 0x1

    .line 415
    invoke-virtual {p1, v0}, Lorg/json/JSONObject;->toString(I)Ljava/lang/String;

    move-result-object p1

    const/4 v0, 0x0

    const/4 v2, 0x2

    invoke-static {v1, p1, v0, v2, v0}, Lkotlin/io/FilesKt;->writeText$default(Ljava/io/File;Ljava/lang/String;Ljava/nio/charset/Charset;ILjava/lang/Object;)V

    .line 416
    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->getReportsDir()Ljava/io/File;

    move-result-object p0

    invoke-virtual {p0}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object p0

    if-eqz p0, :cond_2

    .line 3938
    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    .line 4474
    array-length v3, p0

    const/4 v4, 0x0

    move v5, v4

    :goto_0
    if-ge v5, v3, :cond_1

    aget-object v6, p0, v5

    .line 417
    invoke-virtual {v6}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v7

    invoke-static {v7, p2, v4, v2, v0}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v7

    if-eqz v7, :cond_0

    .line 4474
    invoke-interface {p1, v6}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    :cond_0
    add-int/lit8 v5, v5, 0x1

    goto :goto_0

    .line 1080
    :cond_1
    new-instance p0, Lcom/exteragram/messenger/debug/LoadMonitor$writeReport$$inlined$sortedByDescending$1;

    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor$writeReport$$inlined$sortedByDescending$1;-><init>()V

    invoke-static {p1, p0}, Lkotlin/collections/CollectionsKt;->sortedWith(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/util/List;

    move-result-object p0

    if-eqz p0, :cond_2

    .line 416
    check-cast p0, Ljava/lang/Iterable;

    const/16 p1, 0xa

    .line 419
    invoke-static {p0, p1}, Lkotlin/collections/CollectionsKt;->drop(Ljava/lang/Iterable;I)Ljava/util/List;

    move-result-object p0

    if-eqz p0, :cond_2

    .line 416
    check-cast p0, Ljava/lang/Iterable;

    .line 1915
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_1
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_2

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/io/File;

    .line 420
    invoke-virtual {p1}, Ljava/io/File;->delete()Z

    goto :goto_1

    :cond_2
    return-object v1
.end method


# virtual methods
.method public final captureAndShare(Landroid/app/Activity;)V
    .locals 2

    .line 147
    sget-object p0, Lcom/exteragram/messenger/debug/LoadMonitor;->sampler:Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;

    if-eqz p0, :cond_0

    .line 148
    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->getQueue()Lorg/telegram/messenger/DispatchQueue;

    move-result-object v0

    if-nez v0, :cond_1

    :cond_0
    sget-object v0, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    .line 149
    :cond_1
    new-instance v1, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda5;

    invoke-direct {v1, p0, p1}, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;Landroid/app/Activity;)V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public final deleteReports(Lkotlin/jvm/functions/Function0;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/jvm/functions/Function0<",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    .line 136
    sget-object p0, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v0, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda4;

    invoke-direct {v0, p1}, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda4;-><init>(Lkotlin/jvm/functions/Function0;)V

    invoke-virtual {p0, v0}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public final getCpuPercent()I
    .locals 2

    .line 121
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getLoadMonitorCpuPercent()I

    move-result p0

    const/4 v0, 0x1

    const/16 v1, 0x32

    invoke-static {p0, v0, v1}, Lkotlin/ranges/RangesKt;->coerceIn(III)I

    move-result p0

    return p0
.end method

.method public final getLastReport()Ljava/io/File;
    .locals 9

    .line 129
    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->getReportsDir()Ljava/io/File;

    move-result-object p0

    invoke-virtual {p0}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object p0

    const/4 v0, 0x0

    if-eqz p0, :cond_6

    .line 3938
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 4474
    array-length v2, p0

    const/4 v3, 0x0

    move v4, v3

    :goto_0
    if-ge v4, v2, :cond_1

    aget-object v5, p0, v4

    .line 130
    invoke-virtual {v5}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v6

    const-string v7, ".json"

    const/4 v8, 0x2

    invoke-static {v6, v7, v3, v8, v0}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v6

    if-eqz v6, :cond_0

    .line 4474
    invoke-interface {v1, v5}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    :cond_0
    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    .line 2045
    :cond_1
    invoke-interface {v1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    .line 2046
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-nez v1, :cond_2

    goto :goto_1

    .line 2047
    :cond_2
    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    .line 2048
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-nez v1, :cond_3

    goto :goto_1

    .line 2049
    :cond_3
    move-object v1, v0

    check-cast v1, Ljava/io/File;

    .line 131
    invoke-virtual {v1}, Ljava/io/File;->lastModified()J

    move-result-wide v1

    .line 2051
    :cond_4
    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    .line 2052
    move-object v4, v3

    check-cast v4, Ljava/io/File;

    .line 131
    invoke-virtual {v4}, Ljava/io/File;->lastModified()J

    move-result-wide v4

    cmp-long v6, v1, v4

    if-gez v6, :cond_5

    move-object v0, v3

    move-wide v1, v4

    .line 2057
    :cond_5
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-nez v3, :cond_4

    .line 2058
    :goto_1
    check-cast v0, Ljava/io/File;

    :cond_6
    return-object v0
.end method

.method public final getReportsDir()Ljava/io/File;
    .locals 0

    .line 95
    sget-object p0, Lcom/exteragram/messenger/debug/LoadMonitor;->reportsDir$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/io/File;

    return-object p0
.end method

.method public final getReportsSize()J
    .locals 6

    .line 133
    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->getReportsDir()Ljava/io/File;

    move-result-object p0

    invoke-virtual {p0}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object p0

    const-wide/16 v0, 0x0

    if-eqz p0, :cond_0

    array-length v2, p0

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_0

    aget-object v4, p0, v3

    invoke-virtual {v4}, Ljava/io/File;->length()J

    move-result-wide v4

    add-long/2addr v0, v4

    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_0
    return-wide v0
.end method

.method public final getSummary()Ljava/lang/String;
    .locals 2

    .line 127
    sget-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->sampler:Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->getSummary()Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    return-object v0

    :cond_1
    :goto_0
    invoke-static {}, Landroid/os/Process;->getElapsedCpuTime()J

    move-result-wide v0

    invoke-direct {p0, v0, v1}, Lcom/exteragram/messenger/debug/LoadMonitor;->formatDuration(J)Ljava/lang/String;

    move-result-object p0

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " CPU since launch"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public final setCpuPercent(I)V
    .locals 0

    .line 124
    invoke-static {p1}, Lcom/exteragram/messenger/debug/DebugConfig;->setLoadMonitorCpuPercent(I)V

    return-void
.end method

.method public final setEnabled(Z)V
    .locals 1

    .line 112
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getLoadMonitorEnabled()Z

    move-result p0

    if-ne p0, p1, :cond_0

    goto :goto_1

    .line 113
    :cond_0
    invoke-static {p1}, Lcom/exteragram/messenger/debug/DebugConfig;->setLoadMonitorEnabled(Z)V

    .line 114
    sget-object p0, Lcom/exteragram/messenger/debug/LoadMonitor;->sampler:Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;

    if-eqz p0, :cond_1

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->stop()V

    :cond_1
    const/4 p0, 0x0

    if-eqz p1, :cond_2

    .line 115
    new-instance v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;

    invoke-direct {v0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;-><init>()V

    goto :goto_0

    :cond_2
    move-object v0, p0

    :goto_0
    sput-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->sampler:Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;

    if-nez p1, :cond_3

    .line 117
    sput-object p0, Lcom/exteragram/messenger/debug/LoadMonitor;->pendingAlert:Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;

    :cond_3
    :goto_1
    return-void
.end method

.method public final shareLastReport(Landroid/app/Activity;)V
    .locals 1

    .line 143
    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->getLastReport()Ljava/io/File;

    move-result-object p0

    if-eqz p0, :cond_0

    sget-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-direct {v0, p1, p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->share(Landroid/app/Activity;Ljava/io/File;)V

    :cond_0
    return-void
.end method
