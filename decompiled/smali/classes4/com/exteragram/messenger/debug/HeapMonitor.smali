.class public final Lcom/exteragram/messenger/debug/HeapMonitor;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0010\u000b\n\u0002\u0008\u000e\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\n\u0008\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001DB\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0007\u00a2\u0006\u0004\u0008\u0005\u0010\u0003J\u001f\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0008\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\u0008\t\u0010\nJ#\u0010\u000f\u001a\u00020\u000e2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000c\u0012\u0004\u0012\u00020\u00040\u000bH\u0002\u00a2\u0006\u0004\u0008\u000f\u0010\u0010J3\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000c\u0012\u0004\u0012\u00020\u00040\u000bH\u0002\u00a2\u0006\u0004\u0008\u0013\u0010\u0014J\u001f\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\u0008\u0018\u0010\u0019J\u0015\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001a\u00a2\u0006\u0004\u0008\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\u000c\u00a2\u0006\u0004\u0008\u001e\u0010\u001fJ\r\u0010 \u001a\u00020\u000c\u00a2\u0006\u0004\u0008 \u0010\u001fJ\u0015\u0010\"\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u000c\u00a2\u0006\u0004\u0008\"\u0010#J\r\u0010$\u001a\u00020\u0006\u00a2\u0006\u0004\u0008$\u0010%J\u000f\u0010&\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\u0004\u0008&\u0010\'J\r\u0010(\u001a\u00020\u0006\u00a2\u0006\u0004\u0008(\u0010%J\u001b\u0010+\u001a\u00020\u00042\u000c\u0010*\u001a\u0008\u0012\u0004\u0012\u00020\u00040)\u00a2\u0006\u0004\u0008+\u0010,J\u0015\u0010-\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\u0004\u0008-\u0010.J\u001f\u00101\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00152\u0008\u00100\u001a\u0004\u0018\u00010/\u00a2\u0006\u0004\u00081\u00102J\u000f\u00104\u001a\u00020\u0004H\u0000\u00a2\u0006\u0004\u00083\u0010\u0003R\u0018\u00106\u001a\u0004\u0018\u0001058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\u00086\u00107R\u0016\u00108\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\u00088\u00109R\u001e\u0010<\u001a\n\u0012\u0004\u0012\u00020;\u0018\u00010:8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\u0008<\u0010=R\u001b\u0010A\u001a\u00020\u000e8FX\u0086\u0084\u0002\u00a2\u0006\u000c\n\u0004\u0008>\u0010?\u001a\u0004\u0008@\u0010\'R\u0011\u0010C\u001a\u00020\u00068F\u00a2\u0006\u0006\u001a\u0004\u0008B\u0010%\u00a8\u0006E"
    }
    d2 = {
        "Lcom/exteragram/messenger/debug/HeapMonitor;",
        "",
        "<init>",
        "()V",
        "",
        "init",
        "",
        "used",
        "limit",
        "showLimitAlert",
        "(JJ)V",
        "Lkotlin/Function1;",
        "",
        "onProgress",
        "Ljava/io/File;",
        "dumpHeap",
        "(Lkotlin/jvm/functions/Function1;)Ljava/io/File;",
        "source",
        "target",
        "compress",
        "(Ljava/io/File;Ljava/io/File;Lkotlin/jvm/functions/Function1;)V",
        "Landroid/app/Activity;",
        "activity",
        "file",
        "share",
        "(Landroid/app/Activity;Ljava/io/File;)V",
        "",
        "enabled",
        "setEnabled",
        "(Z)V",
        "getLimitMb",
        "()I",
        "getMaxLimitMb",
        "limitMb",
        "setLimitMb",
        "(I)V",
        "getUsedBytes",
        "()J",
        "getLastDump",
        "()Ljava/io/File;",
        "getDumpsSize",
        "Lkotlin/Function0;",
        "onDone",
        "deleteDumps",
        "(Lkotlin/jvm/functions/Function0;)V",
        "shareLastDump",
        "(Landroid/app/Activity;)V",
        "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;",
        "resourcesProvider",
        "dumpAndShare",
        "(Landroid/app/Activity;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V",
        "collectGarbage$TMessagesProj",
        "collectGarbage",
        "Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;",
        "watcher",
        "Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;",
        "dumping",
        "Z",
        "Ljava/lang/ref/WeakReference;",
        "Lorg/telegram/ui/ActionBar/AlertDialog;",
        "alertRef",
        "Ljava/lang/ref/WeakReference;",
        "dumpsDir$delegate",
        "Lkotlin/Lazy;",
        "getDumpsDir",
        "dumpsDir",
        "getPromptStepBytes",
        "promptStepBytes",
        "LimitWatcher",
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
        "SMAP\nHeapMonitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HeapMonitor.kt\ncom/exteragram/messenger/debug/HeapMonitor\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,308:1\n3938#2:309\n4474#2,2:310\n14048#2,2:327\n14048#2,2:329\n2045#3,14:312\n1#4:326\n*S KotlinDebug\n*F\n+ 1 HeapMonitor.kt\ncom/exteragram/messenger/debug/HeapMonitor\n*L\n96#1:309\n96#1:310,2\n200#1:327,2\n104#1:329,2\n97#1:312,14\n*E\n"
    }
.end annotation


# static fields
.field public static final INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

.field private static alertRef:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference<",
            "Lorg/telegram/ui/ActionBar/AlertDialog;",
            ">;"
        }
    .end annotation
.end field

.field private static volatile dumping:Z

.field private static final dumpsDir$delegate:Lkotlin/Lazy;

.field private static volatile watcher:Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;


# direct methods
.method public static $r8$lambda$4bQhVXYDNdN6H_VjUQxdVfUhGBo(Lorg/telegram/ui/ActionBar/AlertDialog;Landroid/app/Activity;)Lkotlin/Unit;
    .locals 2

    const-wide/16 v0, 0x12c

    .line 122
    invoke-static {v0, v1}, Landroid/os/SystemClock;->sleep(J)V

    .line 123
    sget-object v0, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    :try_start_0
    sget-object v1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 124
    new-instance v1, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda4;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda4;-><init>(Lorg/telegram/ui/ActionBar/AlertDialog;)V

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/debug/HeapMonitor;->dumpHeap(Lkotlin/jvm/functions/Function1;)Ljava/io/File;

    move-result-object v0

    .line 123
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

    .line 133
    :goto_0
    new-instance v1, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda5;

    invoke-direct {v1, p0, v0, p1}, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda5;-><init>(Lorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/Object;Landroid/app/Activity;)V

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    .line 145
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$5zrQMeJT7ZjO2ogkU3UeAGxPFBY(ILorg/telegram/ui/ActionBar/AlertDialog;)V
    .locals 1

    if-nez p0, :cond_0

    .line 127
    const-string v0, "Compressing\u2026"

    invoke-virtual {p1, v0}, Lorg/telegram/ui/ActionBar/AlertDialog;->setMessage(Ljava/lang/CharSequence;)V

    .line 129
    :cond_0
    invoke-virtual {p1, p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->setProgress(I)V

    return-void
.end method

.method public static $r8$lambda$ONRMsNbfuk3xDA6TyEiuMcLOfCY(Lorg/telegram/ui/ActionBar/AlertDialog;I)Lkotlin/Unit;
    .locals 1

    .line 125
    new-instance v0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda8;

    invoke-direct {v0, p1, p0}, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda8;-><init>(ILorg/telegram/ui/ActionBar/AlertDialog;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    .line 131
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$R6tXhA8smJy6OzCZSJ7-sboifr4(Lkotlin/jvm/functions/Function0;)V
    .locals 4

    .line 104
    sget-object v0, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getDumpsDir()Ljava/io/File;

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

    .line 104
    invoke-virtual {v3}, Ljava/io/File;->delete()Z

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 105
    :cond_0
    new-instance v0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda3;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda3;-><init>(Lkotlin/jvm/functions/Function0;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$SuKPShm5yaocICP1T8WTyS_VAZ4()Ljava/io/File;
    .locals 1

    .line 59
    const-string v0, "heapdumps"

    invoke-static {v0}, Lcom/exteragram/messenger/debug/DebugFilesKt;->debugFilesDir(Ljava/lang/String;)Ljava/io/File;

    move-result-object v0

    return-object v0
.end method

.method public static $r8$lambda$_SL_LURkOvouHUUv8lRwaIOGDz4(Lorg/telegram/ui/LaunchActivity;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 162
    sget-object p1, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    const/4 p2, 0x0

    invoke-virtual {p1, p0, p2}, Lcom/exteragram/messenger/debug/HeapMonitor;->dumpAndShare(Landroid/app/Activity;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    return-void
.end method

.method public static $r8$lambda$euY017UpxHSPivzCOWIKbGRnYdQ(Lorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/Object;Landroid/app/Activity;)V
    .locals 2

    const/4 v0, 0x0

    .line 134
    sput-boolean v0, Lcom/exteragram/messenger/debug/HeapMonitor;->dumping:Z

    .line 136
    :try_start_0
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->dismiss()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p0

    .line 138
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 140
    :goto_0
    invoke-static {p1}, Lkotlin/Result;->isSuccess-impl(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    move-object p0, p1

    check-cast p0, Ljava/io/File;

    sget-object v0, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    invoke-direct {v0, p2, p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->share(Landroid/app/Activity;Ljava/io/File;)V

    :cond_0
    invoke-static {p1}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object p0

    if-eqz p0, :cond_1

    .line 141
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 142
    invoke-static {}, Lorg/telegram/ui/Components/BulletinFactory;->global()Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p1

    sget p2, Lorg/telegram/messenger/R$raw;->error:I

    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Heap dump failed: "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p1, p2, p0}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    :cond_1
    return-void
.end method

.method public static $r8$lambda$hztOoxM3SZHZ7Awcb7ppNqu-12s(Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 163
    sget-object p0, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    const/4 p1, 0x0

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/debug/HeapMonitor;->setEnabled(Z)V

    return-void
.end method

.method public static $r8$lambda$lHyX6_s-ZCLcuzAxtweoZ59QQ5M(Lkotlin/jvm/functions/Function0;)V
    .locals 0

    .line 105
    invoke-interface {p0}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    return-void
.end method

.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/exteragram/messenger/debug/HeapMonitor;

    invoke-direct {v0}, Lcom/exteragram/messenger/debug/HeapMonitor;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    .line 59
    new-instance v0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda0;

    invoke-direct {v0}, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda0;-><init>()V

    invoke-static {v0}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/debug/HeapMonitor;->dumpsDir$delegate:Lkotlin/Lazy;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 43
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static final synthetic access$getDumping$p()Z
    .locals 1

    .line 43
    sget-boolean v0, Lcom/exteragram/messenger/debug/HeapMonitor;->dumping:Z

    return v0
.end method

.method public static final synthetic access$showLimitAlert(Lcom/exteragram/messenger/debug/HeapMonitor;JJ)V
    .locals 0

    .line 43
    invoke-direct {p0, p1, p2, p3, p4}, Lcom/exteragram/messenger/debug/HeapMonitor;->showLimitAlert(JJ)V

    return-void
.end method

.method private final compress(Ljava/io/File;Ljava/io/File;Lkotlin/jvm/functions/Function1;)V
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/io/File;",
            "Ljava/io/File;",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Ljava/lang/Integer;",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    const-wide/16 v0, 0x1

    .line 205
    invoke-virtual {p1}, Ljava/io/File;->length()J

    move-result-wide v2

    invoke-static {v0, v1, v2, v3}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v0

    const/4 p0, 0x0

    .line 208
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {p3, v2}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    .line 209
    new-instance v2, Ljava/util/zip/ZipOutputStream;

    new-instance v3, Ljava/io/BufferedOutputStream;

    new-instance v4, Ljava/io/FileOutputStream;

    invoke-direct {v4, p2}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    const/high16 p2, 0x10000

    invoke-direct {v3, v4, p2}, Ljava/io/BufferedOutputStream;-><init>(Ljava/io/OutputStream;I)V

    invoke-direct {v2, v3}, Ljava/util/zip/ZipOutputStream;-><init>(Ljava/io/OutputStream;)V

    const/4 v3, 0x1

    .line 210
    :try_start_0
    invoke-virtual {v2, v3}, Ljava/util/zip/ZipOutputStream;->setLevel(I)V

    .line 211
    new-instance v3, Ljava/util/zip/ZipEntry;

    invoke-virtual {p1}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v3, v4}, Ljava/util/zip/ZipEntry;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v3}, Ljava/util/zip/ZipOutputStream;->putNextEntry(Ljava/util/zip/ZipEntry;)V

    .line 212
    new-instance v3, Ljava/io/FileInputStream;

    invoke-direct {v3, p1}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 213
    :try_start_1
    new-array p1, p2, [B

    const-wide/16 v4, 0x0

    move p2, p0

    .line 215
    :cond_0
    :goto_0
    invoke-virtual {v3, p1}, Ljava/io/FileInputStream;->read([B)I

    move-result v6

    if-ltz v6, :cond_1

    .line 217
    invoke-virtual {v2, p1, p0, v6}, Ljava/util/zip/ZipOutputStream;->write([BII)V

    int-to-long v6, v6

    add-long/2addr v4, v6

    const-wide/16 v6, 0x64

    mul-long/2addr v6, v4

    .line 219
    div-long/2addr v6, v0

    long-to-int v6, v6

    if-eq v6, p2, :cond_0

    .line 222
    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-interface {p3, p2}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move p2, v6

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 225
    :cond_1
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    const/4 p0, 0x0

    .line 212
    :try_start_2
    invoke-static {v3, p0}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    .line 226
    invoke-virtual {v2}, Ljava/util/zip/ZipOutputStream;->closeEntry()V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    .line 209
    invoke-static {v2, p0}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    return-void

    :catchall_1
    move-exception p0

    goto :goto_2

    .line 212
    :goto_1
    :try_start_3
    throw p0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_2

    :catchall_2
    move-exception p1

    :try_start_4
    invoke-static {v3, p0}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    throw p1
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    .line 209
    :goto_2
    :try_start_5
    throw p0
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_3

    :catchall_3
    move-exception p1

    invoke-static {v2, p0}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    throw p1
.end method

.method private final dumpHeap(Lkotlin/jvm/functions/Function1;)Ljava/io/File;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Ljava/lang/Integer;",
            "Lkotlin/Unit;",
            ">;)",
            "Ljava/io/File;"
        }
    .end annotation

    .line 183
    const-string v0, "Can\'t rename "

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getDumpsDir()Ljava/io/File;

    move-result-object v1

    invoke-virtual {v1}, Ljava/io/File;->mkdirs()Z

    .line 184
    sget-object v1, Lorg/telegram/messenger/BuildVars;->BUILD_VERSION_STRING:Ljava/lang/String;

    new-instance v2, Ljava/text/SimpleDateFormat;

    const-string v3, "yyyyMMdd_HHmmss"

    sget-object v4, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-direct {v2, v3, v4}, Ljava/text/SimpleDateFormat;-><init>(Ljava/lang/String;Ljava/util/Locale;)V

    new-instance v3, Ljava/util/Date;

    invoke-direct {v3}, Ljava/util/Date;-><init>()V

    invoke-virtual {v2, v3}, Ljava/text/DateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object v2

    new-instance v3, Ljava/lang/StringBuilder;

    const-string v4, "heap_"

    invoke-direct {v3, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "_"

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 185
    new-instance v2, Ljava/io/File;

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getDumpsDir()Ljava/io/File;

    move-result-object v3

    const-string v4, ".hprof"

    invoke-virtual {v1, v4}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-direct {v2, v3, v4}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 186
    new-instance v3, Ljava/io/File;

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getDumpsDir()Ljava/io/File;

    move-result-object v4

    const-string v5, ".zip.part"

    invoke-virtual {v1, v5}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-direct {v3, v4, v5}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 187
    new-instance v4, Ljava/io/File;

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getDumpsDir()Ljava/io/File;

    move-result-object v5

    const-string v6, ".zip"

    invoke-virtual {v1, v6}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {v4, v5, v1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 189
    :try_start_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->collectGarbage$TMessagesProj()V

    .line 190
    sget-object v1, Lcom/exteragram/messenger/debug/HeapMonitor;->watcher:Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;

    if-eqz v1, :cond_0

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getUsedBytes()J

    move-result-wide v5

    invoke-virtual {v1, v5, v6}, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->postponePrompt(J)V

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_2

    .line 191
    :cond_0
    :goto_0
    invoke-virtual {v2}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Landroid/os/Debug;->dumpHprofData(Ljava/lang/String;)V

    .line 192
    invoke-direct {p0, v2, v3, p1}, Lcom/exteragram/messenger/debug/HeapMonitor;->compress(Ljava/io/File;Ljava/io/File;Lkotlin/jvm/functions/Function1;)V

    .line 193
    invoke-virtual {v3, v4}, Ljava/io/File;->renameTo(Ljava/io/File;)Z

    move-result p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p1, :cond_3

    .line 197
    invoke-virtual {v2}, Ljava/io/File;->delete()Z

    .line 198
    invoke-virtual {v3}, Ljava/io/File;->delete()Z

    .line 200
    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getDumpsDir()Ljava/io/File;

    move-result-object p0

    invoke-virtual {p0}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object p0

    if-eqz p0, :cond_2

    .line 14048
    array-length p1, p0

    const/4 v0, 0x0

    :goto_1
    if-ge v0, p1, :cond_2

    aget-object v1, p0, v0

    .line 200
    invoke-static {v1, v4}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_1

    invoke-virtual {v1}, Ljava/io/File;->delete()Z

    :cond_1
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    :cond_2
    return-object v4

    .line 194
    :cond_3
    :try_start_1
    new-instance p0, Ljava/io/IOException;

    invoke-virtual {v3}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object p1

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 197
    :goto_2
    invoke-virtual {v2}, Ljava/io/File;->delete()Z

    .line 198
    invoke-virtual {v3}, Ljava/io/File;->delete()Z

    throw p0
.end method

.method public static final init()V
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 66
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getHeapMonitorEnabled()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 67
    new-instance v0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;

    invoke-direct {v0}, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/debug/HeapMonitor;->watcher:Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;

    :cond_0
    return-void
.end method

.method private final share(Landroid/app/Activity;Ljava/io/File;)V
    .locals 1

    .line 231
    const-string p0, "application/zip"

    const-string v0, "Share heap dump"

    invoke-static {p1, p2, p0, v0}, Lcom/exteragram/messenger/debug/DebugFilesKt;->shareDebugFile(Landroid/app/Activity;Ljava/io/File;Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method private final showLimitAlert(JJ)V
    .locals 5

    .line 149
    sget-object p0, Lorg/telegram/ui/LaunchActivity;->instance:Lorg/telegram/ui/LaunchActivity;

    .line 150
    sget-object v0, Lcom/exteragram/messenger/debug/HeapMonitor;->watcher:Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;

    if-eqz v0, :cond_1

    sget-boolean v0, Lcom/exteragram/messenger/debug/HeapMonitor;->dumping:Z

    if-nez v0, :cond_1

    if-eqz p0, :cond_1

    invoke-virtual {p0}, Landroid/app/Activity;->isFinishing()Z

    move-result v0

    if-nez v0, :cond_1

    sget-object v0, Lcom/exteragram/messenger/debug/HeapMonitor;->alertRef:Ljava/lang/ref/WeakReference;

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Ljava/lang/ref/Reference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/ui/ActionBar/AlertDialog;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/app/Dialog;->isShowing()Z

    move-result v0

    if-ne v0, v1, :cond_0

    return-void

    .line 153
    :cond_0
    invoke-static {}, Ljava/lang/Runtime;->getRuntime()Ljava/lang/Runtime;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Runtime;->maxMemory()J

    move-result-wide v2

    .line 154
    new-instance v0, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 155
    const-string v4, "Heap limit exceeded"

    invoke-virtual {v0, v4}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object v0

    const/4 v4, 0x0

    .line 157
    invoke-static {p1, p2, v1, v4}, Lorg/telegram/messenger/AndroidUtilities;->formatFileSize(JZZ)Ljava/lang/String;

    move-result-object p1

    .line 158
    invoke-static {v2, v3, v1, v4}, Lorg/telegram/messenger/AndroidUtilities;->formatFileSize(JZZ)Ljava/lang/String;

    move-result-object p2

    .line 159
    invoke-static {p3, p4, v1, v4}, Lorg/telegram/messenger/AndroidUtilities;->formatFileSize(JZZ)Ljava/lang/String;

    move-result-object p3

    new-instance p4, Ljava/lang/StringBuilder;

    const-string v1, "Java heap is at "

    invoke-direct {p4, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " of "

    invoke-virtual {p4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p4, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " after GC, over the "

    invoke-virtual {p4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p4, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " limit.\n\nThe app freezes while dumping. The dump holds everything in memory, so share it only with people you trust."

    invoke-virtual {p4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 156
    invoke-virtual {v0, p1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p1

    .line 162
    new-instance p2, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda6;

    invoke-direct {p2, p0}, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda6;-><init>(Lorg/telegram/ui/LaunchActivity;)V

    const-string p0, "Dump & share"

    invoke-virtual {p1, p0, p2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p0

    .line 163
    new-instance p1, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda7;

    invoke-direct {p1}, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda7;-><init>()V

    const-string p2, "Turn off"

    invoke-virtual {p0, p2, p1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNeutralButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p0

    .line 164
    const-string p1, "Later"

    const/4 p2, 0x0

    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p0

    .line 165
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->create()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object p0

    .line 166
    new-instance p1, Ljava/lang/ref/WeakReference;

    invoke-direct {p1, p0}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    sput-object p1, Lcom/exteragram/messenger/debug/HeapMonitor;->alertRef:Ljava/lang/ref/WeakReference;

    .line 168
    :try_start_0
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->show()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 170
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :cond_1
    return-void
.end method


# virtual methods
.method public final collectGarbage$TMessagesProj()V
    .locals 0

    .line 176
    invoke-static {}, Ljava/lang/Runtime;->getRuntime()Ljava/lang/Runtime;

    move-result-object p0

    .line 177
    invoke-virtual {p0}, Ljava/lang/Runtime;->gc()V

    .line 178
    invoke-virtual {p0}, Ljava/lang/Runtime;->runFinalization()V

    .line 179
    invoke-virtual {p0}, Ljava/lang/Runtime;->gc()V

    return-void
.end method

.method public final deleteDumps(Lkotlin/jvm/functions/Function0;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/jvm/functions/Function0<",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    .line 102
    sget-boolean p0, Lcom/exteragram/messenger/debug/HeapMonitor;->dumping:Z

    if-eqz p0, :cond_0

    return-void

    .line 103
    :cond_0
    sget-object p0, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda1;

    invoke-direct {v0, p1}, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda1;-><init>(Lkotlin/jvm/functions/Function0;)V

    invoke-virtual {p0, v0}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public final dumpAndShare(Landroid/app/Activity;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
    .locals 8

    .line 114
    sget-boolean p0, Lcom/exteragram/messenger/debug/HeapMonitor;->dumping:Z

    if-eqz p0, :cond_0

    return-void

    :cond_0
    const/4 p0, 0x1

    .line 115
    sput-boolean p0, Lcom/exteragram/messenger/debug/HeapMonitor;->dumping:Z

    .line 116
    new-instance p0, Lorg/telegram/ui/ActionBar/AlertDialog;

    const/4 v0, 0x2

    invoke-direct {p0, p1, v0, p2}, Lorg/telegram/ui/ActionBar/AlertDialog;-><init>(Landroid/content/Context;ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 117
    const-string p2, "Dumping heap\u2026"

    invoke-virtual {p0, p2}, Lorg/telegram/ui/ActionBar/AlertDialog;->setMessage(Ljava/lang/CharSequence;)V

    const/4 p2, 0x0

    .line 118
    invoke-virtual {p0, p2}, Lorg/telegram/ui/ActionBar/AlertDialog;->setCanceledOnTouchOutside(Z)V

    .line 119
    invoke-virtual {p0, p2}, Landroid/app/Dialog;->setCancelable(Z)V

    .line 120
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->show()V

    .line 121
    new-instance v5, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda2;

    invoke-direct {v5, p0, p1}, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/ui/ActionBar/AlertDialog;Landroid/app/Activity;)V

    const/16 v6, 0x17

    const/4 v7, 0x0

    const/4 v0, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    const-string v3, "heapDump"

    const/4 v4, 0x0

    invoke-static/range {v0 .. v7}, Lkotlin/concurrent/ThreadsKt;->thread$default(ZZLjava/lang/ClassLoader;Ljava/lang/String;ILkotlin/jvm/functions/Function0;ILjava/lang/Object;)Ljava/lang/Thread;

    return-void
.end method

.method public final getDumpsDir()Ljava/io/File;
    .locals 0

    .line 59
    sget-object p0, Lcom/exteragram/messenger/debug/HeapMonitor;->dumpsDir$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/io/File;

    return-object p0
.end method

.method public final getDumpsSize()J
    .locals 6

    .line 99
    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getDumpsDir()Ljava/io/File;

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

.method public final getLastDump()Ljava/io/File;
    .locals 9

    .line 95
    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getDumpsDir()Ljava/io/File;

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

    .line 96
    invoke-virtual {v5}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v6

    const-string v7, ".zip"

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

    .line 97
    invoke-virtual {v1}, Ljava/io/File;->lastModified()J

    move-result-wide v1

    .line 2051
    :cond_4
    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    .line 2052
    move-object v4, v3

    check-cast v4, Ljava/io/File;

    .line 97
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

.method public final getLimitMb()I
    .locals 2

    .line 78
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getHeapMonitorLimitMb()I

    move-result v0

    const/16 v1, 0x40

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getMaxLimitMb()I

    move-result p0

    invoke-static {v0, v1, p0}, Lkotlin/ranges/RangesKt;->coerceIn(III)I

    move-result p0

    return p0
.end method

.method public final getMaxLimitMb()I
    .locals 2

    .line 81
    invoke-static {}, Ljava/lang/Runtime;->getRuntime()Ljava/lang/Runtime;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Runtime;->maxMemory()J

    move-result-wide v0

    const/16 p0, 0x14

    shr-long/2addr v0, p0

    long-to-int p0, v0

    .line 82
    div-int/lit8 p0, p0, 0x10

    add-int/lit8 p0, p0, -0x1

    mul-int/lit8 p0, p0, 0x10

    const/16 v0, 0x40

    invoke-static {v0, p0}, Ljava/lang/Math;->max(II)I

    move-result p0

    return p0
.end method

.method public final getPromptStepBytes()J
    .locals 4

    .line 62
    invoke-static {}, Ljava/lang/Runtime;->getRuntime()Ljava/lang/Runtime;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Runtime;->maxMemory()J

    move-result-wide v0

    const-wide/16 v2, 0x8

    div-long/2addr v0, v2

    return-wide v0
.end method

.method public final getUsedBytes()J
    .locals 4

    .line 91
    invoke-static {}, Ljava/lang/Runtime;->getRuntime()Ljava/lang/Runtime;

    move-result-object p0

    .line 92
    invoke-virtual {p0}, Ljava/lang/Runtime;->totalMemory()J

    move-result-wide v0

    invoke-virtual {p0}, Ljava/lang/Runtime;->freeMemory()J

    move-result-wide v2

    sub-long/2addr v0, v2

    return-wide v0
.end method

.method public final setEnabled(Z)V
    .locals 0

    .line 72
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getHeapMonitorEnabled()Z

    move-result p0

    if-ne p0, p1, :cond_0

    return-void

    .line 73
    :cond_0
    invoke-static {p1}, Lcom/exteragram/messenger/debug/DebugConfig;->setHeapMonitorEnabled(Z)V

    .line 74
    sget-object p0, Lcom/exteragram/messenger/debug/HeapMonitor;->watcher:Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;

    if-eqz p0, :cond_1

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->stop()V

    :cond_1
    if-eqz p1, :cond_2

    .line 75
    new-instance p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;

    invoke-direct {p0}, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;-><init>()V

    goto :goto_0

    :cond_2
    const/4 p0, 0x0

    :goto_0
    sput-object p0, Lcom/exteragram/messenger/debug/HeapMonitor;->watcher:Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;

    return-void
.end method

.method public final setLimitMb(I)V
    .locals 0

    .line 86
    invoke-static {p1}, Lcom/exteragram/messenger/debug/DebugConfig;->setHeapMonitorLimitMb(I)V

    .line 87
    sget-object p0, Lcom/exteragram/messenger/debug/HeapMonitor;->watcher:Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->resetPrompt()V

    :cond_0
    return-void
.end method

.method public final shareLastDump(Landroid/app/Activity;)V
    .locals 1

    .line 110
    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getLastDump()Ljava/io/File;

    move-result-object p0

    if-eqz p0, :cond_0

    sget-object v0, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    invoke-direct {v0, p1, p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->share(Landroid/app/Activity;Ljava/io/File;)V

    :cond_0
    return-void
.end method
