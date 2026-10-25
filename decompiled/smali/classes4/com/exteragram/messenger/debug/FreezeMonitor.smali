.class public final Lcom/exteragram/messenger/debug/FreezeMonitor;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;,
        Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u000e\n\u0002\u0008\u0003\n\u0002\u0010\t\n\u0002\u0008\u0008\n\u0002\u0010\u0008\n\u0002\u0008\u0007\n\u0002\u0010\u0016\n\u0002\u0008\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010 \n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0007\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0010\u000b\n\u0002\u0008\u000b\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0008\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0010$\n\u0002\u0008\r\u0008\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002cdB\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0007\u00a2\u0006\u0004\u0008\u0005\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\u0008\u0007\u0010\u0008J\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\u0008\u000b\u0010\u000cJ\u0017\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\u0008\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\rH\u0002\u00a2\u0006\u0004\u0008\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\rH\u0002\u00a2\u0006\u0004\u0008\u0015\u0010\u0013J\u001f\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\u0008\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\tH\u0002\u00a2\u0006\u0004\u0008\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\tH\u0002\u00a2\u0006\u0004\u0008\u001d\u0010\u001cJ\u000f\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\u0008\u001f\u0010 J+\u0010&\u001a\u0008\u0012\u0004\u0012\u00020\t0%2\u000c\u0010#\u001a\u0008\u0012\u0004\u0012\u00020\"0!2\u0006\u0010$\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\u0008&\u0010\'J\u000f\u0010(\u001a\u00020\tH\u0002\u00a2\u0006\u0004\u0008(\u0010\u001cJ\u000f\u0010)\u001a\u00020\tH\u0002\u00a2\u0006\u0004\u0008)\u0010\u001cJ\u0017\u0010,\u001a\u00020\t2\u0006\u0010+\u001a\u00020*H\u0002\u00a2\u0006\u0004\u0008,\u0010-J\u0019\u0010/\u001a\u00020\t2\u0008\u0010.\u001a\u0004\u0018\u00010\u0001H\u0002\u00a2\u0006\u0004\u0008/\u00100J\u000f\u00101\u001a\u00020\tH\u0002\u00a2\u0006\u0004\u00081\u0010\u001cJ!\u00104\u001a\u00020\u001e2\u0010\u00103\u001a\u000c\u0012\u0008\u0012\u0006\u0012\u0002\u0008\u0003020!H\u0002\u00a2\u0006\u0004\u00084\u00105J\u000f\u00106\u001a\u00020\tH\u0002\u00a2\u0006\u0004\u00086\u0010\u001cJ\u000f\u00107\u001a\u00020\tH\u0002\u00a2\u0006\u0004\u00087\u0010\u001cJ\u0015\u0010:\u001a\u00020\u00042\u0006\u00109\u001a\u000208\u00a2\u0006\u0004\u0008:\u0010;J\r\u0010<\u001a\u00020\u0016\u00a2\u0006\u0004\u0008<\u0010=J\u0015\u0010?\u001a\u00020\u00042\u0006\u0010>\u001a\u00020\u0016\u00a2\u0006\u0004\u0008?\u0010@J\u000f\u0010A\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0004\u0008A\u0010\u0008J\r\u0010B\u001a\u00020\r\u00a2\u0006\u0004\u0008B\u0010CJ\u001b\u0010F\u001a\u00020\u00042\u000c\u0010E\u001a\u0008\u0012\u0004\u0012\u00020\u00040D\u00a2\u0006\u0004\u0008F\u0010GJ\u0015\u0010J\u001a\u00020\u00042\u0006\u0010I\u001a\u00020H\u00a2\u0006\u0004\u0008J\u0010KR\u001a\u0010L\u001a\u0008\u0012\u0004\u0012\u00020\t0!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\u0008L\u0010MR\u001a\u0010N\u001a\u0008\u0012\u0004\u0012\u00020\t0!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\u0008N\u0010MR\u001a\u0010O\u001a\u0008\u0012\u0004\u0012\u00020\t0!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\u0008O\u0010MR\u001a\u0010P\u001a\u0008\u0012\u0004\u0012\u00020\t0!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\u0008P\u0010MR\u0018\u0010R\u001a\u0004\u0018\u00010Q8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\u0008R\u0010SR\u0016\u0010T\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\u0008T\u0010UR\u0014\u0010V\u001a\u00020\u00018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\u0008V\u0010WR\'\u0010]\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\t0X8BX\u0082\u0084\u0002\u00a2\u0006\u000c\n\u0004\u0008Y\u0010Z\u001a\u0004\u0008[\u0010\\R\u001b\u0010`\u001a\u00020\u00068FX\u0086\u0084\u0002\u00a2\u0006\u000c\n\u0004\u0008^\u0010Z\u001a\u0004\u0008_\u0010\u0008R\u0014\u0010b\u001a\u00020\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\u0008a\u0010\u0008\u00a8\u0006e"
    }
    d2 = {
        "Lcom/exteragram/messenger/debug/FreezeMonitor;",
        "",
        "<init>",
        "()V",
        "",
        "init",
        "Ljava/io/File;",
        "copyReport",
        "()Ljava/io/File;",
        "",
        "text",
        "appendReport",
        "(Ljava/lang/String;)V",
        "",
        "durationMs",
        "showFreezeBulletin",
        "(J)V",
        "ms",
        "formatSeconds",
        "(J)Ljava/lang/String;",
        "bytes",
        "formatMb",
        "",
        "count",
        "total",
        "percent",
        "(II)Ljava/lang/String;",
        "appLine",
        "()Ljava/lang/String;",
        "heapLine",
        "",
        "gcStats",
        "()[J",
        "",
        "Ljava/lang/StackTraceElement;",
        "trace",
        "limit",
        "",
        "frameNames",
        "([Ljava/lang/StackTraceElement;I)Ljava/util/List;",
        "screenName",
        "fragmentStack",
        "Ljava/lang/Thread;",
        "mainThread",
        "otherThreads",
        "(Ljava/lang/Thread;)Ljava/lang/String;",
        "delegate",
        "delegateName",
        "(Ljava/lang/Object;)Ljava/lang/String;",
        "observers",
        "Ljava/lang/Class;",
        "classes",
        "countInstances",
        "([Ljava/lang/Class;)[J",
        "liveObjects",
        "environment",
        "",
        "enabled",
        "setEnabled",
        "(Z)V",
        "getThresholdMs",
        "()I",
        "thresholdMs",
        "setThresholdMs",
        "(I)V",
        "getReport",
        "getReportsSize",
        "()J",
        "Lkotlin/Function0;",
        "onDone",
        "deleteReports",
        "(Lkotlin/jvm/functions/Function0;)V",
        "Landroid/app/Activity;",
        "activity",
        "shareReport",
        "(Landroid/app/Activity;)V",
        "APP_FRAME_PREFIXES",
        "[Ljava/lang/String;",
        "IDLE_TOP_FRAMES",
        "COUNTED_CLASSES",
        "GC_STATS",
        "Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;",
        "watchdog",
        "Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;",
        "freezes",
        "I",
        "reportLock",
        "Ljava/lang/Object;",
        "",
        "notificationNames$delegate",
        "Lkotlin/Lazy;",
        "getNotificationNames",
        "()Ljava/util/Map;",
        "notificationNames",
        "reportsDir$delegate",
        "getReportsDir",
        "reportsDir",
        "getReportFile",
        "reportFile",
        "Freeze",
        "Watchdog",
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
        "SMAP\nFreezeMonitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FreezeMonitor.kt\ncom/exteragram/messenger/debug/FreezeMonitor\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,512:1\n1#2:513\n14048#3,2:514\n13213#3,2:520\n3938#3:533\n4474#3,2:534\n14048#3,2:542\n1586#4:516\n1661#4,3:517\n1915#4,2:522\n1080#4:524\n1080#4:525\n777#4:526\n873#4,2:527\n1586#4:529\n1661#4,3:530\n1205#4,2:536\n1282#4,4:538\n*S KotlinDebug\n*F\n+ 1 FreezeMonitor.kt\ncom/exteragram/messenger/debug/FreezeMonitor\n*L\n157#1:514,2\n231#1:520,2\n99#1:533\n99#1:534,2\n135#1:542,2\n210#1:516\n210#1:517,3\n233#1:522,2\n281#1:524\n284#1:525\n316#1:526\n316#1:527,2\n317#1:529\n317#1:530,3\n100#1:536,2\n100#1:538,4\n*E\n"
    }
.end annotation


# static fields
.field private static final APP_FRAME_PREFIXES:[Ljava/lang/String;

.field private static final COUNTED_CLASSES:[Ljava/lang/String;

.field private static final GC_STATS:[Ljava/lang/String;

.field private static final IDLE_TOP_FRAMES:[Ljava/lang/String;

.field public static final INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

.field private static volatile freezes:I

.field private static final notificationNames$delegate:Lkotlin/Lazy;

.field private static final reportLock:Ljava/lang/Object;

.field private static final reportsDir$delegate:Lkotlin/Lazy;

.field private static volatile watchdog:Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;


# direct methods
.method public static $r8$lambda$3IFPcz9IrhrqK662FxzsQxw-z70(Ljava/util/Map$Entry;)Ljava/lang/CharSequence;
    .locals 2

    .line 284
    invoke-interface {p0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v0

    invoke-interface {p0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object p0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, " "

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$RBr0NeeKCHpE5s3sEw4Cjr8pHLo(Lkotlin/jvm/functions/Function0;)V
    .locals 6

    .line 134
    sget-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->reportLock:Ljava/lang/Object;

    monitor-enter v0

    .line 135
    :try_start_0
    sget-object v1, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    invoke-virtual {v1}, Lcom/exteragram/messenger/debug/FreezeMonitor;->getReportsDir()Ljava/io/File;

    move-result-object v1

    invoke-virtual {v1}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object v1

    const/4 v2, 0x0

    if-eqz v1, :cond_0

    .line 14048
    array-length v3, v1

    move v4, v2

    :goto_0
    if-ge v4, v3, :cond_0

    aget-object v5, v1, v4

    .line 135
    invoke-virtual {v5}, Ljava/io/File;->delete()Z

    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 136
    :cond_0
    sput v2, Lcom/exteragram/messenger/debug/FreezeMonitor;->freezes:I

    .line 137
    sget-object v1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 134
    monitor-exit v0

    .line 138
    new-instance v0, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda6;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda6;-><init>(Lkotlin/jvm/functions/Function0;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    .line 134
    :goto_1
    monitor-exit v0

    throw p0
.end method

.method public static $r8$lambda$WFJTKfhrB5bCkafatRIEgMdAoHY(Lkotlin/jvm/functions/Function0;)V
    .locals 0

    .line 138
    invoke-interface {p0}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    return-void
.end method

.method public static $r8$lambda$WNWcCznrmBRP0CYEU2ePZwl41b4()Ljava/io/File;
    .locals 1

    .line 103
    const-string v0, "freezereports"

    invoke-static {v0}, Lcom/exteragram/messenger/debug/DebugFilesKt;->debugFilesDir(Ljava/lang/String;)Ljava/io/File;

    move-result-object v0

    return-object v0
.end method

.method public static $r8$lambda$_4ZBAwpZNPPivCMaGPNiCgkjd9c([J[Ljava/lang/Class;I)Ljava/lang/CharSequence;
    .locals 4

    .line 308
    aget-wide v0, p0, p2

    invoke-static {v0, v1}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p0

    const/4 v0, 0x2

    const/4 v1, 0x0

    const/4 v2, 0x7

    const/4 v3, 0x0

    invoke-static {p0, v2, v3, v0, v1}, Lkotlin/text/StringsKt;->padStart$default(Ljava/lang/String;ICILjava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    aget-object p1, p1, p2

    invoke-virtual {p1}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p1

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "  "

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$_T9zH3KzMoOoXwuKJLL2JlMLHKY(Landroid/app/Activity;)V
    .locals 2

    .line 144
    sget-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    :try_start_0
    sget-object v1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-direct {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->copyReport()Ljava/io/File;

    move-result-object v0

    invoke-static {v0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception v0

    sget-object v1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {v0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object v0

    invoke-static {v0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 145
    :goto_0
    new-instance v1, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda4;

    invoke-direct {v1, v0, p0}, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda4;-><init>(Ljava/lang/Object;Landroid/app/Activity;)V

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$_VIBI8BiQDyqSUoDdECA0ceA6vk(Lorg/telegram/ui/ActionBar/BaseFragment;)Ljava/lang/CharSequence;
    .locals 0

    .line 218
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$iH7wEvOi8j8d6bXeHFvOgBDAzTw(Lorg/telegram/ui/LaunchActivity;)V
    .locals 1

    .line 187
    sget-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->shareReport(Landroid/app/Activity;)V

    return-void
.end method

.method public static $r8$lambda$pL8L44MmmW1-BoAP12TaTM5KrvU(Ljava/lang/Object;Landroid/app/Activity;)V
    .locals 3

    .line 146
    invoke-static {p0}, Lkotlin/Result;->isSuccess-impl(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    move-object v0, p0

    check-cast v0, Ljava/io/File;

    const-string v1, "text/plain"

    const-string v2, "Share freeze report"

    invoke-static {p1, v0, v1, v2}, Lcom/exteragram/messenger/debug/DebugFilesKt;->shareDebugFile(Landroid/app/Activity;Ljava/io/File;Ljava/lang/String;Ljava/lang/String;)V

    :cond_0
    invoke-static {p0}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object p0

    if-eqz p0, :cond_1

    .line 147
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 148
    invoke-static {}, Lorg/telegram/ui/Components/BulletinFactory;->global()Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p1

    sget v0, Lorg/telegram/messenger/R$raw;->error:I

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Freeze report failed: "

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

.method public static $r8$lambda$sZJ7txUfGJHOdvGde6glWf-p5qw(Lkotlin/Pair;)Ljava/lang/CharSequence;
    .locals 2

    .line 281
    invoke-virtual {p0}, Lkotlin/Pair;->getSecond()Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {p0}, Lkotlin/Pair;->getFirst()Ljava/lang/Object;

    move-result-object p0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, " "

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$tMil1XBAiJwy7TGwpE9xl4xRcZc()Ljava/util/Map;
    .locals 8

    .line 98
    const-class v0, Lorg/telegram/messenger/NotificationCenter;

    invoke-virtual {v0}, Ljava/lang/Class;->getFields()[Ljava/lang/reflect/Field;

    move-result-object v0

    .line 3938
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 4474
    array-length v2, v0

    const/4 v3, 0x0

    move v4, v3

    :goto_0
    if-ge v4, v2, :cond_1

    aget-object v5, v0, v4

    .line 99
    invoke-virtual {v5}, Ljava/lang/reflect/Field;->getModifiers()I

    move-result v6

    invoke-static {v6}, Ljava/lang/reflect/Modifier;->isStatic(I)Z

    move-result v6

    if-eqz v6, :cond_0

    invoke-virtual {v5}, Ljava/lang/reflect/Field;->getType()Ljava/lang/Class;

    move-result-object v6

    sget-object v7, Ljava/lang/Integer;->TYPE:Ljava/lang/Class;

    invoke-static {v6, v7}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v6

    if-eqz v6, :cond_0

    .line 4474
    invoke-interface {v1, v5}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    :cond_0
    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    :cond_1
    const/16 v0, 0xa

    .line 1205
    invoke-static {v1, v0}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v0

    invoke-static {v0}, Lkotlin/collections/MapsKt;->mapCapacity(I)I

    move-result v0

    const/16 v2, 0x10

    invoke-static {v0, v2}, Lkotlin/ranges/RangesKt;->coerceAtLeast(II)I

    move-result v0

    .line 1206
    new-instance v2, Ljava/util/LinkedHashMap;

    invoke-direct {v2, v0}, Ljava/util/LinkedHashMap;-><init>(I)V

    .line 1282
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v0

    :goto_1
    if-ge v3, v0, :cond_2

    invoke-virtual {v1, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    add-int/lit8 v3, v3, 0x1

    .line 1283
    check-cast v4, Ljava/lang/reflect/Field;

    const/4 v5, 0x0

    .line 100
    invoke-virtual {v4, v5}, Ljava/lang/reflect/Field;->getInt(Ljava/lang/Object;)I

    move-result v5

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-virtual {v4}, Ljava/lang/reflect/Field;->getName()Ljava/lang/String;

    move-result-object v4

    invoke-static {v5, v4}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object v4

    .line 1283
    invoke-virtual {v4}, Lkotlin/Pair;->getFirst()Ljava/lang/Object;

    move-result-object v5

    invoke-virtual {v4}, Lkotlin/Pair;->getSecond()Ljava/lang/Object;

    move-result-object v4

    invoke-interface {v2, v5, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_1

    :cond_2
    return-object v2
.end method

.method static constructor <clinit>()V
    .locals 14

    new-instance v0, Lcom/exteragram/messenger/debug/FreezeMonitor;

    invoke-direct {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    .line 69
    const-string v0, "com.chaquo."

    const-string v1, "dev.exterahook."

    const-string v2, "org.telegram."

    const-string v3, "com.exteragram."

    filled-new-array {v2, v3, v0, v1}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->APP_FRAME_PREFIXES:[Ljava/lang/String;

    .line 73
    const-string v8, "android.system.Os."

    const-string v9, "dalvik.system.VMStack.getThreadStackTrace"

    const-string v1, "android.os.MessageQueue.nativePollOnce"

    const-string v2, "java.lang.Object.wait"

    const-string v3, "java.lang.Thread.sleep"

    const-string v4, "sun.misc.Unsafe.park"

    const-string v5, "jdk.internal.misc.Unsafe.park"

    const-string v6, "java.net."

    const-string v7, "libcore.io.Linux."

    filled-new-array/range {v1 .. v9}, [Ljava/lang/String;

    move-result-object v0

    .line 70
    sput-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->IDLE_TOP_FRAMES:[Ljava/lang/String;

    .line 87
    const-string v12, "org.telegram.ui.Components.AnimatedEmojiDrawable"

    .line 88
    const-string v13, "android.graphics.Bitmap"

    const-string v1, "org.telegram.ui.LaunchActivity"

    const-string v2, "org.telegram.ui.ActionBar.BaseFragment"

    const-string v3, "org.telegram.ui.ChatActivity"

    const-string v4, "org.telegram.ui.DialogsActivity"

    const-string v5, "org.telegram.ui.ProfileActivity"

    const-string v6, "org.telegram.ui.Components.ChatActivityEnterView"

    const-string v7, "org.telegram.ui.Components.EmojiView"

    const-string v8, "org.telegram.ui.Cells.ChatMessageCell"

    const-string v9, "org.telegram.messenger.ImageReceiver"

    const-string v10, "org.telegram.ui.Components.RLottieDrawable"

    const-string v11, "org.telegram.ui.Components.AnimatedFileDrawable"

    filled-new-array/range {v1 .. v13}, [Ljava/lang/String;

    move-result-object v0

    .line 75
    sput-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->COUNTED_CLASSES:[Ljava/lang/String;

    .line 90
    const-string v0, "art.gc.gc-count"

    const-string v1, "art.gc.gc-time"

    const-string v2, "art.gc.blocking-gc-count"

    const-string v3, "art.gc.blocking-gc-time"

    filled-new-array {v2, v3, v0, v1}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->GC_STATS:[Ljava/lang/String;

    .line 96
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->reportLock:Ljava/lang/Object;

    .line 97
    new-instance v0, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda0;

    invoke-direct {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda0;-><init>()V

    invoke-static {v0}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->notificationNames$delegate:Lkotlin/Lazy;

    .line 103
    new-instance v0, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda1;

    invoke-direct {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda1;-><init>()V

    invoke-static {v0}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->reportsDir$delegate:Lkotlin/Lazy;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 44
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static final synthetic access$appendReport(Lcom/exteragram/messenger/debug/FreezeMonitor;Ljava/lang/String;)V
    .locals 0

    .line 44
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/debug/FreezeMonitor;->appendReport(Ljava/lang/String;)V

    return-void
.end method

.method public static final synthetic access$environment(Lcom/exteragram/messenger/debug/FreezeMonitor;)Ljava/lang/String;
    .locals 0

    .line 44
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->environment()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$fragmentStack(Lcom/exteragram/messenger/debug/FreezeMonitor;)Ljava/lang/String;
    .locals 0

    .line 44
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->fragmentStack()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$frameNames(Lcom/exteragram/messenger/debug/FreezeMonitor;[Ljava/lang/StackTraceElement;I)Ljava/util/List;
    .locals 0

    .line 44
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/debug/FreezeMonitor;->frameNames([Ljava/lang/StackTraceElement;I)Ljava/util/List;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$gcStats(Lcom/exteragram/messenger/debug/FreezeMonitor;)[J
    .locals 0

    .line 44
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->gcStats()[J

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$getAPP_FRAME_PREFIXES$p()[Ljava/lang/String;
    .locals 1

    .line 44
    sget-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->APP_FRAME_PREFIXES:[Ljava/lang/String;

    return-object v0
.end method

.method public static final synthetic access$getFreezes$p()I
    .locals 1

    .line 44
    sget v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->freezes:I

    return v0
.end method

.method public static final synthetic access$heapLine(Lcom/exteragram/messenger/debug/FreezeMonitor;)Ljava/lang/String;
    .locals 0

    .line 44
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->heapLine()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$liveObjects(Lcom/exteragram/messenger/debug/FreezeMonitor;)Ljava/lang/String;
    .locals 0

    .line 44
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->liveObjects()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$observers(Lcom/exteragram/messenger/debug/FreezeMonitor;)Ljava/lang/String;
    .locals 0

    .line 44
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->observers()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$otherThreads(Lcom/exteragram/messenger/debug/FreezeMonitor;Ljava/lang/Thread;)Ljava/lang/String;
    .locals 0

    .line 44
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/debug/FreezeMonitor;->otherThreads(Ljava/lang/Thread;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$percent(Lcom/exteragram/messenger/debug/FreezeMonitor;II)Ljava/lang/String;
    .locals 0

    .line 44
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/debug/FreezeMonitor;->percent(II)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$screenName(Lcom/exteragram/messenger/debug/FreezeMonitor;)Ljava/lang/String;
    .locals 0

    .line 44
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->screenName()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$setFreezes$p(I)V
    .locals 0

    .line 44
    sput p0, Lcom/exteragram/messenger/debug/FreezeMonitor;->freezes:I

    return-void
.end method

.method public static final synthetic access$showFreezeBulletin(Lcom/exteragram/messenger/debug/FreezeMonitor;J)V
    .locals 0

    .line 44
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/debug/FreezeMonitor;->showFreezeBulletin(J)V

    return-void
.end method

.method private final appLine()Ljava/lang/String;
    .locals 7

    .line 198
    sget-object p0, Lorg/telegram/messenger/BuildVars;->BUILD_VERSION_STRING:Ljava/lang/String;

    sget v0, Lorg/telegram/messenger/BuildVars;->BUILD_VERSION:I

    sget-object v1, Landroid/os/Build;->MANUFACTURER:Ljava/lang/String;

    sget-object v2, Landroid/os/Build;->MODEL:Ljava/lang/String;

    .line 199
    sget-object v3, Landroid/os/Build$VERSION;->RELEASE:Ljava/lang/String;

    sget v4, Landroid/os/Build$VERSION;->SDK_INT:I

    new-instance v5, Ljava/lang/StringBuilder;

    const-string v6, "app "

    invoke-direct {v5, v6}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v5, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " ("

    invoke-virtual {v5, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, "), "

    invoke-virtual {v5, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " "

    invoke-virtual {v5, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ", Android "

    invoke-virtual {v5, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " (API "

    invoke-virtual {v5, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, ")"

    invoke-virtual {v5, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final appendReport(Ljava/lang/String;)V
    .locals 7

    .line 164
    sget-object p0, Lcom/exteragram/messenger/debug/FreezeMonitor;->reportLock:Ljava/lang/Object;

    monitor-enter p0

    .line 166
    :try_start_0
    sget-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->getReportsDir()Ljava/io/File;

    move-result-object v1

    invoke-virtual {v1}, Ljava/io/File;->mkdirs()Z

    .line 167
    invoke-direct {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->getReportFile()Ljava/io/File;

    move-result-object v0

    const/4 v1, 0x2

    const/4 v2, 0x0

    .line 168
    invoke-static {v0, p1, v2, v1, v2}, Lkotlin/io/FilesKt;->appendText$default(Ljava/io/File;Ljava/lang/String;Ljava/nio/charset/Charset;ILjava/lang/Object;)V

    .line 169
    invoke-virtual {v0}, Ljava/io/File;->length()J

    move-result-wide v3

    const-wide/32 v5, 0x1e8480

    cmp-long p1, v3, v5

    if-lez p1, :cond_0

    const p1, 0xf4240

    .line 170
    new-array p1, p1, [B

    .line 171
    new-instance v1, Ljava/io/RandomAccessFile;

    const-string v3, "r"

    invoke-direct {v1, v0, v3}, Ljava/io/RandomAccessFile;-><init>(Ljava/io/File;Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 172
    :try_start_1
    invoke-virtual {v1}, Ljava/io/RandomAccessFile;->length()J

    move-result-wide v3

    const-wide/32 v5, 0xf4240

    sub-long/2addr v3, v5

    invoke-virtual {v1, v3, v4}, Ljava/io/RandomAccessFile;->seek(J)V

    .line 173
    invoke-virtual {v1, p1}, Ljava/io/RandomAccessFile;->readFully([B)V

    .line 174
    sget-object v3, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    .line 171
    :try_start_2
    invoke-static {v1, v2}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    .line 175
    const-string v1, "##### (older records trimmed)\n"

    sget-object v2, Lkotlin/text/Charsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {v1, v2}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object v1

    invoke-static {v1, p1}, Lkotlin/collections/ArraysKt;->plus([B[B)[B

    move-result-object p1

    invoke-static {v0, p1}, Lkotlin/io/FilesKt;->writeBytes(Ljava/io/File;[B)V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    goto :goto_1

    :catchall_0
    move-exception p1

    goto :goto_2

    :catch_0
    move-exception p1

    goto :goto_0

    :catchall_1
    move-exception p1

    .line 171
    :try_start_3
    throw p1
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_2

    :catchall_2
    move-exception v0

    :try_start_4
    invoke-static {v1, p1}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    throw v0
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_0
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    .line 178
    :goto_0
    :try_start_5
    invoke-static {p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 180
    :cond_0
    :goto_1
    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    .line 164
    monitor-exit p0

    return-void

    :goto_2
    monitor-exit p0

    throw p1
.end method

.method private final copyReport()Ljava/io/File;
    .locals 8

    .line 155
    const-string p0, "freeze_"

    sget-object v1, Lcom/exteragram/messenger/debug/FreezeMonitor;->reportLock:Ljava/lang/Object;

    monitor-enter v1

    .line 156
    :try_start_0
    new-instance v3, Ljava/io/File;

    sget-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->getReportsDir()Ljava/io/File;

    move-result-object v2

    sget-object v4, Lorg/telegram/messenger/BuildVars;->BUILD_VERSION_STRING:Ljava/lang/String;

    new-instance v5, Ljava/text/SimpleDateFormat;

    const-string v6, "yyyyMMdd_HHmmss"

    sget-object v7, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-direct {v5, v6, v7}, Ljava/text/SimpleDateFormat;-><init>(Ljava/lang/String;Ljava/util/Locale;)V

    new-instance v6, Ljava/util/Date;

    invoke-direct {v6}, Ljava/util/Date;-><init>()V

    invoke-virtual {v5, v6}, Ljava/text/DateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object v5

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6, p0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "_"

    invoke-virtual {v6, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ".txt"

    invoke-virtual {v6, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v3, v2, p0}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 157
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->getReportsDir()Ljava/io/File;

    move-result-object p0

    invoke-virtual {p0}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object p0

    if-eqz p0, :cond_1

    .line 14048
    array-length v0, p0

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v0, :cond_1

    aget-object v4, p0, v2

    .line 157
    invoke-virtual {v4}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v5

    const-string v6, "freeze_report.txt"

    invoke-static {v5, v6}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_0

    invoke-virtual {v4}, Ljava/io/File;->delete()Z

    goto :goto_1

    :catchall_0
    move-exception v0

    move-object p0, v0

    goto :goto_2

    :cond_0
    :goto_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 158
    :cond_1
    sget-object p0, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    invoke-direct {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->getReportFile()Ljava/io/File;

    move-result-object v2

    const/4 v6, 0x4

    const/4 v7, 0x0

    const/4 v4, 0x1

    const/4 v5, 0x0

    invoke-static/range {v2 .. v7}, Lkotlin/io/FilesKt;->copyTo$default(Ljava/io/File;Ljava/io/File;ZIILjava/lang/Object;)Ljava/io/File;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 159
    monitor-exit v1

    return-object v3

    :goto_2
    monitor-exit v1

    throw p0
.end method

.method private final countInstances([Ljava/lang/Class;)[J
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "([",
            "Ljava/lang/Class<",
            "*>;)[J"
        }
    .end annotation

    .line 294
    sget-object p0, Ljava/lang/Boolean;->TYPE:Ljava/lang/Class;

    const-string v0, "dalvik.system.VMDebug"

    invoke-static {v0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    const/4 v1, 0x0

    .line 296
    :try_start_0
    const-string v2, "countInstancesOfClasses"

    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v3

    filled-new-array {v3, p0}, [Ljava/lang/Class;

    move-result-object v3

    invoke-virtual {v0, v2, v3}, Ljava/lang/Class;->getDeclaredMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v2

    .line 297
    sget-object v3, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    filled-new-array {p1, v3}, [Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v2, v1, v3}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    .line 296
    check-cast v2, [J
    :try_end_0
    .catch Ljava/lang/ReflectiveOperationException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v2

    .line 299
    :catch_0
    const-class v2, Ljava/lang/Class;

    filled-new-array {v2, p0}, [Ljava/lang/Class;

    move-result-object p0

    const-string v2, "countInstancesOfClass"

    invoke-virtual {v0, v2, p0}, Ljava/lang/Class;->getDeclaredMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object p0

    .line 300
    array-length v0, p1

    new-array v2, v0, [J

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v0, :cond_0

    aget-object v4, p1, v3

    sget-object v5, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    filled-new-array {v4, v5}, [Ljava/lang/Object;

    move-result-object v4

    invoke-virtual {p0, v1, v4}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/Long;

    invoke-virtual {v4}, Ljava/lang/Long;->longValue()J

    move-result-wide v4

    aput-wide v4, v2, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_0
    return-object v2
.end method

.method private final delegateName(Ljava/lang/Object;)Ljava/lang/String;
    .locals 5

    if-nez p1, :cond_0

    .line 243
    const-string p0, "null"

    return-object p0

    .line 244
    :cond_0
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p0

    .line 245
    invoke-virtual {p0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x2

    const/4 v2, 0x0

    const-string v3, "WeakObserversGroupImpl"

    const/4 v4, 0x0

    invoke-static {v0, v3, v4, v1, v2}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 246
    :try_start_0
    sget-object v0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 247
    const-string v0, "reference"

    invoke-virtual {p0, v0}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    const/4 v1, 0x1

    .line 248
    invoke-virtual {v0, v1}, Ljava/lang/reflect/AccessibleObject;->setAccessible(Z)V

    .line 249
    invoke-virtual {v0, p1}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/ref/WeakReference;

    invoke-virtual {p1}, Ljava/lang/ref/Reference;->get()Ljava/lang/Object;

    move-result-object p1

    .line 246
    invoke-static {p1}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception p1

    sget-object v0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p1}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p1

    invoke-static {p1}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    .line 250
    :goto_0
    invoke-static {p1}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object v0

    if-nez v0, :cond_2

    if-eqz p1, :cond_1

    .line 251
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p0

    goto :goto_1

    :cond_1
    const-string p0, "cleared"

    :goto_1
    const-string p1, " (weak)"

    invoke-virtual {p0, p1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 250
    :cond_2
    invoke-virtual {p0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 253
    :cond_3
    invoke-virtual {p0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final environment()Ljava/lang/String;
    .locals 10

    .line 314
    :try_start_0
    sget-object v0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 315
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentHashMap;->values()Ljava/util/Collection;

    move-result-object v0

    check-cast v0, Ljava/lang/Iterable;

    .line 777
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 873
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    move-object v3, v2

    check-cast v3, Lcom/exteragram/messenger/plugins/Plugin;

    .line 316
    invoke-virtual {v3}, Lcom/exteragram/messenger/plugins/Plugin;->isEnabled()Z

    move-result v3

    if-eqz v3, :cond_0

    .line 873
    invoke-interface {v1, v2}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :catchall_0
    move-exception v0

    goto :goto_2

    .line 1586
    :cond_1
    new-instance v0, Ljava/util/ArrayList;

    const/16 v2, 0xa

    invoke-static {v1, v2}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v2

    invoke-direct {v0, v2}, Ljava/util/ArrayList;-><init>(I)V

    .line 1661
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v2

    const/4 v3, 0x0

    :goto_1
    if-ge v3, v2, :cond_2

    invoke-virtual {v1, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    add-int/lit8 v3, v3, 0x1

    .line 1662
    check-cast v4, Lcom/exteragram/messenger/plugins/Plugin;

    .line 317
    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/Plugin;->getVersion()Ljava/lang/String;

    move-result-object v4

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v6, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, " "

    invoke-virtual {v6, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    .line 1662
    invoke-interface {v0, v4}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_1

    .line 318
    :cond_2
    invoke-static {v0}, Lkotlin/collections/CollectionsKt;->sorted(Ljava/lang/Iterable;)Ljava/util/List;

    move-result-object v0

    move-object v1, v0

    check-cast v1, Ljava/lang/Iterable;

    .line 319
    const-string v2, ", "

    const/16 v8, 0x3e

    const/4 v9, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    invoke-static/range {v1 .. v9}, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    .line 320
    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v1

    if-nez v1, :cond_3

    const-string v0, "none"

    .line 314
    :cond_3
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

    .line 321
    :goto_3
    invoke-static {v0}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object v1

    if-nez v1, :cond_4

    goto :goto_4

    :cond_4
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v2, "unavailable: "

    invoke-direct {v0, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    :goto_4
    move-object v1, v0

    check-cast v1, Ljava/lang/String;

    .line 322
    :try_start_1
    sget v0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/messenger/UserConfig;->isPremium()Z

    move-result v0

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    invoke-static {v0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_5

    :catchall_1
    move-exception v0

    sget-object v2, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {v0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object v0

    invoke-static {v0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    :goto_5
    invoke-static {v0}, Lkotlin/Result;->isFailure-impl(Ljava/lang/Object;)Z

    move-result v2

    const/4 v3, 0x0

    if-eqz v2, :cond_5

    move-object v0, v3

    :cond_5
    check-cast v0, Ljava/lang/Boolean;

    .line 324
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->appLine()Ljava/lang/String;

    move-result-object v2

    .line 325
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v4

    invoke-static {}, Landroid/os/Process;->getStartElapsedRealtime()J

    move-result-wide v6

    sub-long/2addr v4, v6

    const-wide/16 v6, 0x3e8

    div-long/2addr v4, v6

    .line 326
    invoke-static {}, Lorg/telegram/ui/Components/ForegroundDetector;->getInstance()Lorg/telegram/ui/Components/ForegroundDetector;

    move-result-object v6

    if-eqz v6, :cond_6

    invoke-virtual {v6}, Lorg/telegram/ui/Components/ForegroundDetector;->isForeground()Z

    move-result v3

    invoke-static {v3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v3

    .line 327
    :cond_6
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->heapLine()Ljava/lang/String;

    move-result-object p0

    .line 328
    invoke-static {}, Lorg/telegram/messenger/UserConfig;->getActivatedAccountsCount()I

    move-result v6

    .line 329
    new-instance v7, Ljava/lang/StringBuilder;

    const-string v8, "-- environment --\n"

    invoke-direct {v7, v8}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v7, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "\nprocess uptime "

    invoke-virtual {v7, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v4, v5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v2, " s, foreground "

    invoke-virtual {v7, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v2, "\nheap now: "

    invoke-virtual {v7, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "\naccounts active "

    invoke-virtual {v7, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, ", premium "

    invoke-virtual {v7, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p0, "\nenabled plugins: "

    invoke-virtual {v7, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "\n"

    invoke-virtual {v7, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final formatMb(J)Ljava/lang/String;
    .locals 2

    .line 193
    sget-object p0, Lkotlin/jvm/internal/StringCompanionObject;->INSTANCE:Lkotlin/jvm/internal/StringCompanionObject;

    sget-object p0, Ljava/util/Locale;->US:Ljava/util/Locale;

    long-to-double p1, p1

    const-wide/high16 v0, 0x4130000000000000L    # 1048576.0

    div-double/2addr p1, v0

    invoke-static {p1, p2}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

    move-result-object p1

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    const/4 p2, 0x1

    invoke-static {p1, p2}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object p1

    const-string p2, "%.1f MB"

    invoke-static {p0, p2, p1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final formatSeconds(J)Ljava/lang/String;
    .locals 2

    .line 191
    sget-object p0, Lkotlin/jvm/internal/StringCompanionObject;->INSTANCE:Lkotlin/jvm/internal/StringCompanionObject;

    sget-object p0, Ljava/util/Locale;->US:Ljava/util/Locale;

    long-to-double p1, p1

    const-wide v0, 0x408f400000000000L    # 1000.0

    div-double/2addr p1, v0

    invoke-static {p1, p2}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

    move-result-object p1

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    const/4 p2, 0x1

    invoke-static {p1, p2}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object p1

    const-string p2, "%.1f s"

    invoke-static {p0, p2, p1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final fragmentStack()Ljava/lang/String;
    .locals 10

    .line 216
    const-string p0, "("

    :try_start_0
    sget-object v0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 217
    new-instance v1, Ljava/util/ArrayList;

    sget-object v0, Lorg/telegram/ui/LaunchActivity;->instance:Lorg/telegram/ui/LaunchActivity;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lorg/telegram/ui/LaunchActivity;->getActionBarLayout()Lorg/telegram/ui/ActionBar/INavigationLayout;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-interface {v0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->getFragmentStack()Ljava/util/List;

    move-result-object v0

    if-eqz v0, :cond_0

    :goto_0
    check-cast v0, Ljava/util/Collection;

    goto :goto_1

    :cond_0
    invoke-static {}, Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;

    move-result-object v0

    goto :goto_0

    :goto_1
    invoke-direct {v1, v0}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 218
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v0

    const-string v2, " > "

    new-instance v7, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda5;

    invoke-direct {v7}, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda5;-><init>()V

    const/16 v8, 0x1e

    const/4 v9, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    invoke-static/range {v1 .. v9}, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, p0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, ") "

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 216
    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_2

    :catchall_0
    move-exception v0

    move-object p0, v0

    sget-object v0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 219
    :goto_2
    invoke-static {p0}, Lkotlin/Result;->isFailure-impl(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    const-string p0, "unavailable"

    :cond_1
    check-cast p0, Ljava/lang/String;

    return-object p0
.end method

.method private final frameNames([Ljava/lang/StackTraceElement;I)Ljava/util/List;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "([",
            "Ljava/lang/StackTraceElement;",
            "I)",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 210
    invoke-static {p1, p2}, Lkotlin/collections/ArraysKt;->take([Ljava/lang/Object;I)Ljava/util/List;

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
    check-cast p2, Ljava/lang/StackTraceElement;

    .line 210
    invoke-virtual {p2}, Ljava/lang/StackTraceElement;->toString()Ljava/lang/String;

    move-result-object p2

    .line 1662
    invoke-interface {p1, p2}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_0
    return-object p1
.end method

.method private final gcStats()[J
    .locals 4

    .line 207
    sget-object p0, Lcom/exteragram/messenger/debug/FreezeMonitor;->GC_STATS:[Ljava/lang/String;

    array-length p0, p0

    new-array v0, p0, [J

    const/4 v1, 0x0

    :goto_0
    if-ge v1, p0, :cond_1

    sget-object v2, Lcom/exteragram/messenger/debug/FreezeMonitor;->GC_STATS:[Ljava/lang/String;

    aget-object v2, v2, v1

    invoke-static {v2}, Landroid/os/Debug;->getRuntimeStat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_0

    invoke-static {v2}, Lkotlin/text/StringsKt;->toLongOrNull(Ljava/lang/String;)Ljava/lang/Long;

    move-result-object v2

    if-eqz v2, :cond_0

    invoke-virtual {v2}, Ljava/lang/Long;->longValue()J

    move-result-wide v2

    goto :goto_1

    :cond_0
    const-wide/16 v2, 0x0

    :goto_1
    aput-wide v2, v0, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_1
    return-object v0
.end method

.method private final getNotificationNames()Ljava/util/Map;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "Ljava/lang/Integer;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 97
    sget-object p0, Lcom/exteragram/messenger/debug/FreezeMonitor;->notificationNames$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/util/Map;

    return-object p0
.end method

.method private final getReportFile()Ljava/io/File;
    .locals 2

    .line 106
    new-instance v0, Ljava/io/File;

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->getReportsDir()Ljava/io/File;

    move-result-object p0

    const-string v1, "freeze_report.txt"

    invoke-direct {v0, p0, v1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    return-object v0
.end method

.method private final heapLine()Ljava/lang/String;
    .locals 8

    .line 202
    sget-object v0, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getUsedBytes()J

    move-result-wide v0

    .line 203
    invoke-static {}, Ljava/lang/Runtime;->getRuntime()Ljava/lang/Runtime;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Runtime;->maxMemory()J

    move-result-wide v2

    .line 204
    invoke-direct {p0, v0, v1}, Lcom/exteragram/messenger/debug/FreezeMonitor;->formatMb(J)Ljava/lang/String;

    move-result-object v4

    invoke-direct {p0, v2, v3}, Lcom/exteragram/messenger/debug/FreezeMonitor;->formatMb(J)Ljava/lang/String;

    move-result-object v5

    const-wide/16 v6, 0x64

    mul-long/2addr v0, v6

    div-long/2addr v0, v2

    invoke-static {}, Landroid/os/Debug;->getNativeHeapAllocatedSize()J

    move-result-wide v2

    invoke-direct {p0, v2, v3}, Lcom/exteragram/messenger/debug/FreezeMonitor;->formatMb(J)Ljava/lang/String;

    move-result-object p0

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "java "

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, " / "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, " ("

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, "%), native "

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static final init()V
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 110
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getFreezeMonitorEnabled()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 111
    new-instance v0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;

    invoke-direct {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->watchdog:Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;

    :cond_0
    return-void
.end method

.method private final liveObjects()Ljava/lang/String;
    .locals 11

    .line 305
    :try_start_0
    sget-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->COUNTED_CLASSES:[Ljava/lang/String;

    array-length v0, v0

    new-array v1, v0, [Ljava/lang/Class;

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v0, :cond_0

    sget-object v3, Lcom/exteragram/messenger/debug/FreezeMonitor;->COUNTED_CLASSES:[Ljava/lang/String;

    aget-object v3, v3, v2

    invoke-static {v3}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v3

    aput-object v3, v1, v2

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 306
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/HeapMonitor;->collectGarbage$TMessagesProj()V

    .line 307
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/debug/FreezeMonitor;->countInstances([Ljava/lang/Class;)[J

    move-result-object p0

    .line 308
    invoke-static {v1}, Lkotlin/collections/ArraysKt;->getIndices([Ljava/lang/Object;)Lkotlin/ranges/IntRange;

    move-result-object v2

    const-string v3, "\n"

    const-string v5, "\n"

    new-instance v8, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda9;

    invoke-direct {v8, p0, v1}, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda9;-><init>([J[Ljava/lang/Class;)V

    const/16 v9, 0x1a

    const/4 v10, 0x0

    const/4 v4, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    invoke-static/range {v2 .. v10}, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-object p0

    :catchall_0
    move-exception v0

    move-object p0, v0

    .line 310
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "unavailable: "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p0, "\n"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final observers()Ljava/lang/String;
    .locals 27

    .line 257
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    .line 259
    sget v0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    const-string v2, "account"

    invoke-static {v2, v0}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object v0

    .line 260
    const-string v2, "global"

    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v3

    invoke-static {v2, v3}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object v2

    filled-new-array {v0, v2}, [Lkotlin/Pair;

    move-result-object v2

    const/4 v4, 0x0

    :goto_0
    const/4 v0, 0x2

    if-ge v4, v0, :cond_4

    .line 262
    aget-object v5, v2, v4

    invoke-virtual {v5}, Lkotlin/Pair;->component1()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    invoke-virtual {v5}, Lkotlin/Pair;->component2()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lorg/telegram/messenger/NotificationCenter;

    .line 264
    :try_start_0
    const-class v7, Lorg/telegram/messenger/NotificationCenter;

    const-string v8, "observers"

    invoke-virtual {v7, v8}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v7

    const/4 v8, 0x1

    .line 265
    invoke-virtual {v7, v8}, Ljava/lang/reflect/AccessibleObject;->setAccessible(Z)V

    .line 266
    invoke-virtual {v7, v5}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Landroid/util/SparseArray;

    .line 268
    new-instance v7, Ljava/util/ArrayList;

    invoke-direct {v7}, Ljava/util/ArrayList;-><init>()V

    .line 269
    new-instance v9, Ljava/util/HashMap;

    invoke-direct {v9}, Ljava/util/HashMap;-><init>()V

    .line 270
    invoke-virtual {v5}, Landroid/util/SparseArray;->size()I

    move-result v10

    const/4 v11, 0x0

    const/4 v12, 0x0

    :goto_1
    if-ge v11, v10, :cond_3

    .line 271
    invoke-virtual {v5, v11}, Landroid/util/SparseArray;->valueAt(I)Ljava/lang/Object;

    move-result-object v13

    check-cast v13, Ljava/util/List;

    .line 272
    invoke-interface {v13}, Ljava/util/List;->size()I

    move-result v14

    add-int/2addr v12, v14

    .line 273
    invoke-interface {v13}, Ljava/util/List;->size()I

    move-result v14

    invoke-static {v14}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v14

    invoke-direct/range {p0 .. p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->getNotificationNames()Ljava/util/Map;

    move-result-object v15

    invoke-virtual {v5, v11}, Landroid/util/SparseArray;->keyAt(I)I

    move-result v16

    invoke-static/range {v16 .. v16}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-interface {v15, v3}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    if-nez v3, :cond_0

    invoke-virtual {v5, v11}, Landroid/util/SparseArray;->keyAt(I)I

    move-result v3

    invoke-static {v3}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v3

    goto :goto_3

    :catch_0
    move-exception v0

    move-object/from16 v8, p0

    :goto_2
    move-object/from16 v17, v2

    goto/16 :goto_6

    :cond_0
    :goto_3
    invoke-static {v14, v3}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object v3

    invoke-virtual {v7, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 274
    move-object v3, v13

    check-cast v3, Ljava/util/Collection;

    invoke-interface {v3}, Ljava/util/Collection;->size()I

    move-result v3

    const/4 v14, 0x0

    :goto_4
    if-ge v14, v3, :cond_2

    .line 275
    invoke-interface {v13, v14}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v15
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move/from16 v16, v8

    move-object/from16 v8, p0

    :try_start_1
    invoke-direct {v8, v15}, Lcom/exteragram/messenger/debug/FreezeMonitor;->delegateName(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v15
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_2

    move-object/from16 v17, v2

    const/16 v2, 0x2e

    move/from16 v18, v3

    const/4 v3, 0x0

    :try_start_2
    invoke-static {v15, v2, v3, v0, v3}, Lkotlin/text/StringsKt;->substringAfterLast$default(Ljava/lang/String;CLjava/lang/String;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    .line 276
    invoke-virtual {v9, v2}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/Integer;

    if-eqz v3, :cond_1

    invoke-virtual {v3}, Ljava/lang/Integer;->intValue()I

    move-result v3

    goto :goto_5

    :catch_1
    move-exception v0

    goto/16 :goto_6

    :cond_1
    const/4 v3, 0x0

    :goto_5
    add-int/lit8 v3, v3, 0x1

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-interface {v9, v2, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    add-int/lit8 v14, v14, 0x1

    move/from16 v8, v16

    move-object/from16 v2, v17

    move/from16 v3, v18

    goto :goto_4

    :catch_2
    move-exception v0

    goto :goto_2

    :cond_2
    move-object/from16 v17, v2

    move/from16 v16, v8

    move-object/from16 v8, p0

    add-int/lit8 v11, v11, 0x1

    move/from16 v8, v16

    goto/16 :goto_1

    :cond_3
    move-object/from16 v8, p0

    move-object/from16 v17, v2

    .line 279
    invoke-virtual {v7}, Ljava/util/ArrayList;->size()I

    move-result v0

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, ": "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v12}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v3, " registrations in "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, " ids\n"

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 280
    const-string v0, "  biggest ids: "

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1080
    new-instance v0, Lcom/exteragram/messenger/debug/FreezeMonitor$observers$$inlined$sortedByDescending$1;

    invoke-direct {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$observers$$inlined$sortedByDescending$1;-><init>()V

    invoke-static {v7, v0}, Lkotlin/collections/CollectionsKt;->sortedWith(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/util/List;

    move-result-object v0

    check-cast v0, Ljava/lang/Iterable;

    const/16 v2, 0x8

    .line 281
    invoke-static {v0, v2}, Lkotlin/collections/CollectionsKt;->take(Ljava/lang/Iterable;I)Ljava/util/List;

    move-result-object v0

    move-object/from16 v18, v0

    check-cast v18, Ljava/lang/Iterable;

    const-string v19, ", "

    new-instance v24, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda7;

    invoke-direct/range {v24 .. v24}, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda7;-><init>()V

    const/16 v25, 0x1e

    const/16 v26, 0x0

    const/16 v20, 0x0

    const/16 v21, 0x0

    const/16 v22, 0x0

    const/16 v23, 0x0

    invoke-static/range {v18 .. v26}, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v0, 0xa

    .line 282
    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 283
    const-string v2, "  by class: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 284
    invoke-virtual {v9}, Ljava/util/HashMap;->entrySet()Ljava/util/Set;

    move-result-object v2

    .line 1080
    new-instance v3, Lcom/exteragram/messenger/debug/FreezeMonitor$observers$$inlined$sortedByDescending$2;

    invoke-direct {v3}, Lcom/exteragram/messenger/debug/FreezeMonitor$observers$$inlined$sortedByDescending$2;-><init>()V

    invoke-static {v2, v3}, Lkotlin/collections/CollectionsKt;->sortedWith(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/util/List;

    move-result-object v2

    check-cast v2, Ljava/lang/Iterable;

    const/16 v3, 0xc

    .line 284
    invoke-static {v2, v3}, Lkotlin/collections/CollectionsKt;->take(Ljava/lang/Iterable;I)Ljava/util/List;

    move-result-object v2

    move-object/from16 v18, v2

    check-cast v18, Ljava/lang/Iterable;

    const-string v19, ", "

    new-instance v24, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda8;

    invoke-direct/range {v24 .. v24}, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda8;-><init>()V

    const/16 v25, 0x1e

    const/16 v26, 0x0

    const/16 v20, 0x0

    const/16 v21, 0x0

    const/16 v22, 0x0

    const/16 v23, 0x0

    invoke-static/range {v18 .. v26}, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 285
    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_1

    goto :goto_7

    .line 287
    :goto_6
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, ": failed "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, "\n"

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :goto_7
    add-int/lit8 v4, v4, 0x1

    move-object/from16 v2, v17

    goto/16 :goto_0

    .line 290
    :cond_4
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method private final otherThreads(Ljava/lang/Thread;)Ljava/lang/String;
    .locals 16

    .line 222
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    .line 225
    :try_start_0
    invoke-static {}, Ljava/lang/Thread;->getAllStackTraces()Ljava/util/Map;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    const/4 v3, 0x0

    move v4, v3

    :cond_0
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_8

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/Map$Entry;

    invoke-interface {v5}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/Thread;

    invoke-interface {v5}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, [Ljava/lang/StackTraceElement;

    move-object/from16 v7, p1

    .line 226
    invoke-static {v6, v7}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v8

    if-nez v8, :cond_1

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v8

    invoke-static {v6, v8}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v8

    if-eqz v8, :cond_2

    :cond_1
    move-object/from16 v10, p0

    goto :goto_0

    .line 227
    :cond_2
    invoke-virtual {v6}, Ljava/lang/Thread;->getState()Ljava/lang/Thread$State;

    move-result-object v8

    .line 228
    sget-object v9, Ljava/lang/Thread$State;->RUNNABLE:Ljava/lang/Thread$State;

    if-eq v8, v9, :cond_3

    sget-object v9, Ljava/lang/Thread$State;->BLOCKED:Ljava/lang/Thread$State;

    if-ne v8, v9, :cond_1

    goto :goto_1

    :catch_0
    move-exception v0

    goto :goto_4

    :cond_3
    :goto_1
    const/16 v9, 0x14

    move-object/from16 v10, p0

    .line 229
    invoke-direct {v10, v5, v9}, Lcom/exteragram/messenger/debug/FreezeMonitor;->frameNames([Ljava/lang/StackTraceElement;I)Ljava/util/List;

    move-result-object v5

    .line 230
    invoke-static {v5}, Lkotlin/collections/CollectionsKt;->firstOrNull(Ljava/util/List;)Ljava/lang/Object;

    move-result-object v9

    check-cast v9, Ljava/lang/String;

    if-nez v9, :cond_4

    goto :goto_0

    .line 231
    :cond_4
    sget-object v11, Lcom/exteragram/messenger/debug/FreezeMonitor;->IDLE_TOP_FRAMES:[Ljava/lang/String;

    .line 13213
    array-length v12, v11

    move v13, v3

    :goto_2
    if-ge v13, v12, :cond_6

    aget-object v14, v11, v13

    const/4 v15, 0x2

    const/4 v2, 0x0

    .line 231
    invoke-static {v9, v14, v3, v15, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_5

    goto :goto_0

    :cond_5
    add-int/lit8 v13, v13, 0x1

    goto :goto_2

    :cond_6
    const/16 v2, 0x22

    .line 232
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/Thread;->getName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "\" "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const/16 v2, 0xa

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 233
    check-cast v5, Ljava/lang/Iterable;

    .line 1915
    invoke-interface {v5}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_3
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_7

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/String;

    .line 233
    const-string v6, "    at "

    invoke-virtual {v1, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v5, 0xa

    invoke-virtual {v1, v5}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_3

    :cond_7
    add-int/lit8 v4, v4, 0x1

    const/16 v2, 0x28

    if-lt v4, v2, :cond_0

    goto :goto_5

    .line 237
    :goto_4
    const-string v2, "thread dump failed: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const/16 v2, 0xa

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 239
    :cond_8
    :goto_5
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->length()I

    move-result v0

    if-lez v0, :cond_9

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    goto :goto_6

    :cond_9
    const-string v0, "(none)\n"

    :goto_6
    return-object v0
.end method

.method private final percent(II)Ljava/lang/String;
    .locals 2

    mul-int/lit8 p1, p1, 0x64

    .line 195
    div-int/2addr p1, p2

    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    const/4 p1, 0x2

    const/4 p2, 0x0

    const/4 v0, 0x4

    const/4 v1, 0x0

    invoke-static {p0, v0, v1, p1, p2}, Lkotlin/text/StringsKt;->padStart$default(Ljava/lang/String;ICILjava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final screenName()Ljava/lang/String;
    .locals 1

    .line 212
    :try_start_0
    sget-object p0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 213
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getLastFragmentIncludeMainTabs()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    :cond_0
    const-string p0, "none"

    .line 212
    :goto_0
    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_1

    :catchall_0
    move-exception p0

    sget-object v0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 214
    :goto_1
    invoke-static {p0}, Lkotlin/Result;->isFailure-impl(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    const-string p0, "unknown"

    :cond_1
    check-cast p0, Ljava/lang/String;

    return-object p0
.end method

.method private final showFreezeBulletin(J)V
    .locals 3

    .line 184
    sget-object v0, Lorg/telegram/ui/LaunchActivity;->instance:Lorg/telegram/ui/LaunchActivity;

    .line 185
    sget-object v1, Lcom/exteragram/messenger/debug/FreezeMonitor;->watchdog:Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;

    if-eqz v1, :cond_1

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Landroid/app/Activity;->isFinishing()Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_0

    .line 186
    :cond_0
    invoke-static {}, Lorg/telegram/ui/Components/BulletinFactory;->global()Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object v1

    sget v2, Lorg/telegram/messenger/R$raw;->error:I

    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/debug/FreezeMonitor;->formatSeconds(J)Ljava/lang/String;

    move-result-object p0

    new-instance p1, Ljava/lang/StringBuilder;

    const-string p2, "Main thread froze for "

    invoke-direct {p1, p2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    new-instance p1, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda10;

    invoke-direct {p1, v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda10;-><init>(Lorg/telegram/ui/LaunchActivity;)V

    const-string p2, "Share"

    invoke-virtual {v1, v2, p0, p2, p1}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 188
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    :cond_1
    :goto_0
    return-void
.end method


# virtual methods
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

    .line 133
    sget-object p0, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v0, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda2;

    invoke-direct {v0, p1}, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda2;-><init>(Lkotlin/jvm/functions/Function0;)V

    invoke-virtual {p0, v0}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public final getReport()Ljava/io/File;
    .locals 4

    .line 128
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->getReportFile()Ljava/io/File;

    move-result-object p0

    invoke-virtual {p0}, Ljava/io/File;->length()J

    move-result-wide v0

    const-wide/16 v2, 0x0

    cmp-long v0, v0, v2

    if-lez v0, :cond_0

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public final getReportsDir()Ljava/io/File;
    .locals 0

    .line 103
    sget-object p0, Lcom/exteragram/messenger/debug/FreezeMonitor;->reportsDir$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/io/File;

    return-object p0
.end method

.method public final getReportsSize()J
    .locals 6

    .line 130
    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->getReportsDir()Ljava/io/File;

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

.method public final getThresholdMs()I
    .locals 2

    .line 122
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getFreezeMonitorThresholdMs()I

    move-result p0

    const/16 v0, 0xc8

    const/16 v1, 0x7d0

    invoke-static {p0, v0, v1}, Lkotlin/ranges/RangesKt;->coerceIn(III)I

    move-result p0

    return p0
.end method

.method public final setEnabled(Z)V
    .locals 0

    .line 116
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getFreezeMonitorEnabled()Z

    move-result p0

    if-ne p0, p1, :cond_0

    return-void

    .line 117
    :cond_0
    invoke-static {p1}, Lcom/exteragram/messenger/debug/DebugConfig;->setFreezeMonitorEnabled(Z)V

    .line 118
    sget-object p0, Lcom/exteragram/messenger/debug/FreezeMonitor;->watchdog:Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;

    if-eqz p0, :cond_1

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->stop()V

    :cond_1
    if-eqz p1, :cond_2

    .line 119
    new-instance p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;

    invoke-direct {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;-><init>()V

    goto :goto_0

    :cond_2
    const/4 p0, 0x0

    :goto_0
    sput-object p0, Lcom/exteragram/messenger/debug/FreezeMonitor;->watchdog:Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;

    return-void
.end method

.method public final setThresholdMs(I)V
    .locals 0

    .line 125
    invoke-static {p1}, Lcom/exteragram/messenger/debug/DebugConfig;->setFreezeMonitorThresholdMs(I)V

    return-void
.end method

.method public final shareReport(Landroid/app/Activity;)V
    .locals 1

    .line 143
    sget-object p0, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v0, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda3;

    invoke-direct {v0, p1}, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda3;-><init>(Landroid/app/Activity;)V

    invoke-virtual {p0, v0}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method
