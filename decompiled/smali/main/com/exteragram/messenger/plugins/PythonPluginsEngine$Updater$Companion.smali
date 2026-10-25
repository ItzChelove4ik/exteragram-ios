.class public final Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Companion"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\n\n\u0002\u0010\t\n\u0002\u0008\u0002\n\u0002\u0010\r\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0008\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\r\n\u0002\u0018\u0002\n\u0002\u0008\u0004\u0008\u0086\u0003\u0018\u00002\u00020\u0001:\u0001MB\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0008\u0010\u001a\u001a\u00020\u001bH\u0007J\n\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0007J\u0008\u0010\u001d\u001a\u00020\u001eH\u0007J\u0008\u0010\u001f\u001a\u00020\u000bH\u0007J\u0006\u0010 \u001a\u00020\u000bJ\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u000bH\u0007J\u0008\u0010$\u001a\u00020%H\u0002J\u0008\u0010&\u001a\u00020\'H\u0002J\u0010\u0010(\u001a\u00020\'2\u0006\u0010)\u001a\u00020\u001eH\u0007J\u0008\u0010*\u001a\u00020%H\u0007J\u0008\u0010+\u001a\u00020%H\u0007J\u0008\u0010,\u001a\u00020%H\u0007J\u0010\u0010-\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020%H\u0007J\u0008\u0010/\u001a\u00020%H\u0007J\u0008\u00100\u001a\u00020\"H\u0007J\u0008\u00101\u001a\u00020\"H\u0007J\u0008\u00102\u001a\u00020\"H\u0007J\u0010\u00102\u001a\u00020\"2\u0006\u00103\u001a\u00020\u000bH\u0007J\u0008\u00104\u001a\u00020\"H\u0007J\u0010\u00105\u001a\u00020\"2\u0006\u00106\u001a\u00020%H\u0002J\u0010\u00107\u001a\u00020\"2\u0006\u00108\u001a\u00020\rH\u0002J\u0012\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010;\u001a\u00020<H\u0007J\u0018\u0010=\u001a\u00020\u000b2\u0006\u0010>\u001a\u00020\'2\u0006\u0010?\u001a\u00020\u000bH\u0007J\u0018\u0010@\u001a\u00020\u000b2\u0006\u0010A\u001a\u00020\'2\u0006\u0010B\u001a\u00020\'H\u0007J\u0018\u0010C\u001a\u00020\u000b2\u0006\u0010A\u001a\u00020\'2\u0006\u0010B\u001a\u00020\'H\u0007J\u0018\u0010D\u001a\u00020\"2\u0006\u0010E\u001a\u00020%2\u0006\u0010F\u001a\u00020%H\u0007J \u0010G\u001a\u00020\"2\u0006\u0010H\u001a\u00020:2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020\u000bH\u0002J\u001a\u0010L\u001a\u00020\"2\u0006\u0010H\u001a\u00020:2\u0008\u0008\u0002\u0010K\u001a\u00020\u000bH\u0007R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0008\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000c\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u000e\u0010\u000f\"\u0004\u0008\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u0013\u0010\u0014\"\u0004\u0008\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006N"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;",
        "",
        "<init>",
        "()V",
        "PYTHON_SDK_APP_VERSION_PATTERN",
        "Ljava/util/regex/Pattern;",
        "kotlin.jvm.PlatformType",
        "PYTHON_SDK_APP_VERSION_CODE_PATTERN",
        "notifyRunnable",
        "Ljava/lang/Runnable;",
        "isLoading",
        "",
        "status",
        "",
        "getStatus",
        "()I",
        "setStatus",
        "(I)V",
        "notifyWhenChangeStatus",
        "getNotifyWhenChangeStatus",
        "()Z",
        "setNotifyWhenChangeStatus",
        "(Z)V",
        "lastCheckUpdateTime",
        "",
        "TAG",
        "getVersion",
        "",
        "getStateString",
        "sdkFromApk",
        "Ljava/io/InputStream;",
        "isSdkFromApk",
        "isSdkFromCurrentApk",
        "setBuildFromApk",
        "",
        "fromApk",
        "sdkFromApkMarker",
        "Ljava/io/File;",
        "currentApkStamp",
        "",
        "hashBytes",
        "inputStream",
        "getPythonSdkUpdateFile",
        "getPythonCurrentSdkFile",
        "getPythonSdkUpdateMetaFile",
        "isPendingUpdateVerified",
        "archive",
        "requestSdkFromApkFile",
        "deleteSdkUpdateFile",
        "enableAutoUpdate",
        "checkUpdates",
        "force",
        "restoreSdkFromApk",
        "touchFile",
        "file",
        "updateStatus",
        "newStatus",
        "parsePythonSdkUpdateResponse",
        "Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;",
        "res",
        "Lorg/telegram/tgnet/TLRPC$messages_Messages;",
        "isSdkVersionNewer",
        "remoteVersion",
        "isBeta",
        "isAppVersionCompatible",
        "operator",
        "targetVersion",
        "isAppVersionCodeCompatible",
        "zipFolder",
        "sourceDir",
        "zipFile",
        "copyArchiveToPluginsDirectory",
        "update",
        "document",
        "Lorg/telegram/tgnet/TLRPC$Document;",
        "autoRestartEngine",
        "savePythonSdkArchive",
        "PythonSdkUpdateInfo",
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


# direct methods
.method public static $r8$lambda$6ut4Ap5MRyyIRCTm-PYnUBe_pW4(Lkotlin/jvm/internal/Ref$BooleanRef;Z)V
    .locals 1

    const/4 v0, 0x0

    .line 2645
    invoke-static {v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->access$setLoading$cp(Z)V

    .line 2646
    iget-boolean p0, p0, Lkotlin/jvm/internal/Ref$BooleanRef;->element:Z

    if-nez p0, :cond_0

    .line 2647
    sget-object p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;

    const/4 p1, 0x2

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->updateStatus(I)V

    return-void

    :cond_0
    if-eqz p1, :cond_1

    .line 2649
    sget-object p0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->restart()V

    return-void

    .line 2651
    :cond_1
    sget-object p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;

    const/4 p1, 0x4

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->updateStatus(I)V

    return-void
.end method

.method public static $r8$lambda$KbqAV-6oc3FrQNFkIEYX01uJMDM(Lorg/telegram/tgnet/TLRPC$messages_Messages;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 5

    const/4 v0, 0x2

    const/4 v1, 0x0

    if-nez p1, :cond_4

    if-eqz p0, :cond_5

    .line 2438
    sget-object p1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;

    invoke-virtual {p1, p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->parsePythonSdkUpdateResponse(Lorg/telegram/tgnet/TLRPC$messages_Messages;)Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;

    move-result-object p0

    if-eqz p0, :cond_5

    .line 2440
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsPySdkAutoUpdate()Z

    move-result v2

    const/4 v3, 0x1

    if-nez v2, :cond_1

    .line 2441
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getSafeLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object v2

    if-eqz v2, :cond_0

    .line 2443
    new-instance v4, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$$ExternalSyntheticLambda2;

    invoke-direct {v4, v2, p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;)V

    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :cond_0
    move v2, v3

    goto :goto_0

    :cond_1
    move v2, v1

    :goto_0
    if-nez v2, :cond_3

    const/4 v4, 0x0

    .line 2452
    :try_start_0
    invoke-static {p1, p0, v1, v0, v4}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->savePythonSdkArchive$default(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;ZILjava/lang/Object;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    move v1, v3

    goto :goto_1

    :catch_0
    move-exception p1

    .line 2456
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->getChannel()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->getMessage()Lorg/telegram/tgnet/TLRPC$Message;

    move-result-object p0

    if-eqz p0, :cond_2

    iget p0, p0, Lorg/telegram/tgnet/TLRPC$Message;->id:I

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    :cond_2
    new-instance p0, Ljava/lang/StringBuilder;

    const-string v3, "Failed to load python-plugins-sdk file ("

    invoke-direct {p0, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", message id = "

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v1, ")"

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 2455
    invoke-static {p0, p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_3
    move v1, v2

    goto :goto_1

    .line 2463
    :cond_4
    iget-object p0, p1, Lorg/telegram/tgnet/TLRPC$TL_error;->text:Ljava/lang/String;

    new-instance p1, Ljava/lang/StringBuilder;

    const-string v2, "Failed to search messages with sdk updates: "

    invoke-direct {p1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;)V

    :cond_5
    :goto_1
    if-nez v1, :cond_6

    .line 2466
    sget-object p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->updateStatus(I)V

    :cond_6
    return-void
.end method

.method public static $r8$lambda$Nb4_qHB-J0JtMgKWq7nD16D-hLg(Ljava/io/File;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;Ljava/io/File;Z)V
    .locals 5

    .line 2624
    const-string/jumbo v0, "|"

    new-instance v1, Lkotlin/jvm/internal/Ref$BooleanRef;

    invoke-direct {v1}, Lkotlin/jvm/internal/Ref$BooleanRef;-><init>()V

    .line 2627
    iget-object v2, p1, Lorg/telegram/tgnet/TLRPC$TL_help_appUpdate;->version:Ljava/lang/String;

    .line 2628
    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->getAbi()Ljava/lang/String;

    move-result-object v3

    .line 2629
    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->getSignature()Ljava/lang/String;

    move-result-object v4

    .line 2625
    invoke-static {p0, v2, v3, v4}, Lcom/exteragram/messenger/plugins/utils/SdkSignature;->verify(Ljava/io/File;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z

    move-result v2

    const/4 v3, 0x0

    if-nez v2, :cond_1

    .line 2632
    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->getChannel()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->getMessage()Lorg/telegram/tgnet/TLRPC$Message;

    move-result-object p1

    if-eqz p1, :cond_0

    iget p1, p1, Lorg/telegram/tgnet/TLRPC$Message;->id:I

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    :cond_0
    new-instance p1, Ljava/lang/StringBuilder;

    const-string p2, "Rejected Python SDK update with an invalid signature ("

    invoke-direct {p1, p2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ", message id = "

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p0, ")"

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;)V

    goto :goto_0

    .line 2635
    :cond_1
    :try_start_0
    invoke-static {p0}, Lorg/simplifiles/SimpliFiles;->file(Ljava/io/File;)Lorg/simplifiles/files/SimpliFile;

    move-result-object p0

    sget-object v2, Lorg/simplifiles/files/OverwritePolicy;->REPLACE:Lorg/simplifiles/files/OverwritePolicy;

    invoke-virtual {p0, p2, v2}, Lorg/simplifiles/files/SimpliFile;->copyTo(Ljava/io/File;Lorg/simplifiles/files/OverwritePolicy;)Lorg/simplifiles/files/SimpliFile;

    .line 2636
    sget-object p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getPythonSdkUpdateMetaFile()Ljava/io/File;

    move-result-object p0

    invoke-static {p0}, Lorg/simplifiles/SimpliFiles;->file(Ljava/io/File;)Lorg/simplifiles/files/SimpliFile;

    move-result-object p0

    .line 2637
    iget-object p2, p1, Lorg/telegram/tgnet/TLRPC$TL_help_appUpdate;->version:Ljava/lang/String;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->getAbi()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->getSignature()Ljava/lang/String;

    move-result-object p1

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const/4 p2, 0x2

    invoke-static {p0, p1, v3, p2, v3}, Lorg/simplifiles/files/SimpliFile;->writeTextAtomic$default(Lorg/simplifiles/files/SimpliFile;Ljava/lang/String;Ljava/nio/charset/Charset;ILjava/lang/Object;)V

    const/4 p0, 0x1

    .line 2638
    iput-boolean p0, v1, Lkotlin/jvm/internal/Ref$BooleanRef;->element:Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p0

    .line 2640
    const-string p1, "Failed to copy plugins-sdk file"

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 2644
    :goto_0
    new-instance p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$$ExternalSyntheticLambda0;

    invoke-direct {p0, v1, p3}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$$ExternalSyntheticLambda0;-><init>(Lkotlin/jvm/internal/Ref$BooleanRef;Z)V

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$VRguszw8oEgH7cLSI7U0KmCyDcA(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;)V
    .locals 1

    .line 2445
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/components/PythonSdkUpdateAlert;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/plugins/ui/components/PythonSdkUpdateAlert;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;)V

    .line 2444
    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 2180
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public synthetic constructor <init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;-><init>()V

    return-void
.end method

.method public static final synthetic access$copyArchiveToPluginsDirectory(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;Lorg/telegram/tgnet/TLRPC$Document;Z)V
    .locals 0

    .line 2180
    invoke-direct {p0, p1, p2, p3}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->copyArchiveToPluginsDirectory(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;Lorg/telegram/tgnet/TLRPC$Document;Z)V

    return-void
.end method

.method public static final synthetic access$updateStatus(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;I)V
    .locals 0

    .line 2180
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->updateStatus(I)V

    return-void
.end method

.method private final copyArchiveToPluginsDirectory(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;Lorg/telegram/tgnet/TLRPC$Document;Z)V
    .locals 2

    .line 2619
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getPythonSdkUpdateFile()Ljava/io/File;

    move-result-object p0

    .line 2621
    sget v0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/FileLoader;->getInstance(I)Lorg/telegram/messenger/FileLoader;

    move-result-object v0

    invoke-virtual {v0, p2}, Lorg/telegram/messenger/FileLoader;->getPathToAttach(Lorg/telegram/tgnet/TLObject;)Ljava/io/File;

    move-result-object p2

    .line 2623
    sget-object v0, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$$ExternalSyntheticLambda3;

    invoke-direct {v1, p2, p1, p0, p3}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$$ExternalSyntheticLambda3;-><init>(Ljava/io/File;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;Ljava/io/File;Z)V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method private final currentApkStamp()Ljava/lang/String;
    .locals 3

    .line 2316
    sget-object p0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {p0}, Landroid/content/Context;->getApplicationInfo()Landroid/content/pm/ApplicationInfo;

    move-result-object p0

    iget-object p0, p0, Landroid/content/pm/ApplicationInfo;->sourceDir:Ljava/lang/String;

    .line 2317
    new-instance v0, Ljava/io/File;

    invoke-direct {v0, p0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->lastModified()J

    move-result-wide v0

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ":"

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic savePythonSdkArchive$default(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;ZILjava/lang/Object;)V
    .locals 0

    and-int/lit8 p3, p3, 0x2

    if-eqz p3, :cond_0

    const/4 p2, 0x0

    .line 2657
    :cond_0
    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->savePythonSdkArchive(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;Z)V

    return-void
.end method

.method private final sdkFromApkMarker()Ljava/io/File;
    .locals 3

    .line 2312
    new-instance p0, Ljava/io/File;

    new-instance v0, Ljava/io/File;

    invoke-static {}, Lorg/telegram/messenger/ApplicationLoader;->getFilesDirFixed()Ljava/io/File;

    move-result-object v1

    const-string v2, "chaquopy"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    const-string v1, ".currentSdkFromApk"

    invoke-direct {p0, v0, v1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    return-object p0
.end method

.method private final touchFile(Ljava/io/File;)V
    .locals 0

    .line 2479
    :try_start_0
    sget-object p0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 2480
    invoke-static {p1}, Lorg/simplifiles/SimpliFiles;->file(Ljava/io/File;)Lorg/simplifiles/files/SimpliFile;

    move-result-object p0

    invoke-virtual {p0}, Lorg/simplifiles/files/SimpliFile;->touch()Lorg/simplifiles/files/SimpliFile;

    move-result-object p0

    .line 2479
    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception p0

    sget-object p1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 2481
    :goto_0
    invoke-static {p0}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 2482
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :cond_0
    return-void
.end method

.method private final updateStatus(I)V
    .locals 2

    .line 2487
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->setStatus(I)V

    .line 2488
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getNotifyWhenChangeStatus()Z

    move-result p0

    if-eqz p0, :cond_1

    .line 2489
    invoke-static {}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->access$getNotifyRunnable$cp()Ljava/lang/Runnable;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    .line 2490
    invoke-static {}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->access$getNotifyRunnable$cp()Ljava/lang/Runnable;

    move-result-object p0

    const/4 v0, 0x1

    if-ne p1, v0, :cond_0

    const-wide/16 v0, 0x0

    goto :goto_0

    :cond_0
    const-wide/16 v0, 0x258

    :goto_0
    invoke-static {p0, v0, v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    :cond_1
    return-void
.end method


# virtual methods
.method public final checkUpdates()V
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    const/4 v0, 0x0

    .line 2408
    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->checkUpdates(Z)V

    return-void
.end method

.method public final checkUpdates(Z)V
    .locals 8
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2413
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    .line 2414
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getStatus()I

    move-result v2

    const/4 v3, 0x1

    if-ne v2, v3, :cond_0

    invoke-static {}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->access$getLastCheckUpdateTime$cp()J

    move-result-wide v4

    sub-long v4, v0, v4

    invoke-static {v4, v5}, Ljava/lang/Math;->abs(J)J

    move-result-wide v4

    const-wide/16 v6, 0x1770

    cmp-long v2, v4, v6

    if-ltz v2, :cond_3

    .line 2416
    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getStatus()I

    move-result v2

    const/4 v4, 0x2

    if-le v2, v4, :cond_1

    goto :goto_1

    :cond_1
    if-nez p1, :cond_4

    .line 2421
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getSdkUpdateScheduleTimestamp()J

    move-result-wide v4

    sub-long v4, v0, v4

    invoke-static {v4, v5}, Ljava/lang/Math;->abs(J)J

    move-result-wide v4

    const-wide/32 v6, 0x36ee80

    cmp-long p1, v4, v6

    if-gez p1, :cond_2

    move p1, v3

    goto :goto_0

    :cond_2
    const/4 p1, 0x0

    .line 2423
    :goto_0
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsEngine()Z

    move-result v2

    if-eqz v2, :cond_3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsSafeMode()Z

    move-result v2

    if-nez v2, :cond_3

    if-eqz p1, :cond_4

    :cond_3
    :goto_1
    return-void

    .line 2427
    :cond_4
    invoke-static {v0, v1}, Lcom/exteragram/messenger/ExteraConfig;->setSdkUpdateScheduleTimestamp(J)V

    .line 2429
    invoke-direct {p0, v3}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->updateStatus(I)V

    .line 2430
    invoke-static {v0, v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->access$setLastCheckUpdateTime$cp(J)V

    .line 2433
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_inputMessagesFilterDocument;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_inputMessagesFilterDocument;-><init>()V

    .line 2434
    new-instance p1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$$ExternalSyntheticLambda1;

    invoke-direct {p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$$ExternalSyntheticLambda1;-><init>()V

    const/16 v0, 0xbb8

    .line 2431
    const-string/jumbo v1, "python_sdk"

    invoke-static {v1, p0, p1, v0}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->searchMessages(Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$MessagesFilter;Lorg/telegram/messenger/Utilities$Callback2;I)V

    return-void
.end method

.method public final deleteSdkUpdateFile()V
    .locals 3
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2390
    sget-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getPythonSdkUpdateMetaFile()Ljava/io/File;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$deleteFileIfExists(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/io/File;)V

    .line 2391
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getPythonSdkUpdateFile()Ljava/io/File;

    move-result-object v1

    .line 2392
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v2

    if-eqz v2, :cond_0

    .line 2393
    invoke-static {v0, v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$deleteFileIfExists(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/io/File;)V

    const/4 v0, 0x0

    .line 2394
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->updateStatus(I)V

    :cond_0
    return-void
.end method

.method public final enableAutoUpdate()V
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    const/4 v0, 0x1

    .line 2400
    invoke-static {v0}, Lcom/exteragram/messenger/ExteraConfig;->setPluginsPySdkAutoUpdate(Z)V

    .line 2401
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getNotifyWhenChangeStatus()Z

    move-result p0

    if-eqz p0, :cond_0

    .line 2402
    invoke-static {}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->access$getNotifyRunnable$cp()Ljava/lang/Runnable;

    move-result-object p0

    invoke-interface {p0}, Ljava/lang/Runnable;->run()V

    :cond_0
    return-void
.end method

.method public final getNotifyWhenChangeStatus()Z
    .locals 0

    .line 2195
    invoke-static {}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->access$getNotifyWhenChangeStatus$cp()Z

    move-result p0

    return p0
.end method

.method public final getPythonCurrentSdkFile()Ljava/io/File;
    .locals 3
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2355
    new-instance p0, Ljava/io/File;

    new-instance v0, Ljava/io/File;

    invoke-static {}, Lorg/telegram/messenger/ApplicationLoader;->getFilesDirFixed()Ljava/io/File;

    move-result-object v1

    const-string v2, "chaquopy"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    const-string v1, "currentSdk"

    invoke-direct {p0, v0, v1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    return-object p0
.end method

.method public final getPythonSdkUpdateFile()Ljava/io/File;
    .locals 3
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2350
    new-instance p0, Ljava/io/File;

    new-instance v0, Ljava/io/File;

    invoke-static {}, Lorg/telegram/messenger/ApplicationLoader;->getFilesDirFixed()Ljava/io/File;

    move-result-object v1

    const-string v2, "chaquopy"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    const-string/jumbo v1, "newSdk"

    invoke-direct {p0, v0, v1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    return-object p0
.end method

.method public final getPythonSdkUpdateMetaFile()Ljava/io/File;
    .locals 3
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2360
    new-instance p0, Ljava/io/File;

    new-instance v0, Ljava/io/File;

    invoke-static {}, Lorg/telegram/messenger/ApplicationLoader;->getFilesDirFixed()Ljava/io/File;

    move-result-object v1

    const-string v2, "chaquopy"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    const-string/jumbo v1, "newSdk.meta"

    invoke-direct {p0, v0, v1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    return-object p0
.end method

.method public final getStateString()Ljava/lang/CharSequence;
    .locals 2
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2217
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getStatus()I

    move-result v0

    if-eqz v0, :cond_4

    const/4 v1, 0x1

    if-eq v0, v1, :cond_3

    const/4 v1, 0x2

    if-eq v0, v1, :cond_2

    const/4 p0, 0x3

    if-eq v0, p0, :cond_1

    const/4 p0, 0x4

    if-eq v0, p0, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 2222
    :cond_0
    sget p0, Lorg/telegram/messenger/R$string;->RestartPluginSystemToApplyUpdate:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 2221
    :cond_1
    sget p0, Lorg/telegram/messenger/R$string;->LoadingUpdate:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 2220
    :cond_2
    sget v0, Lorg/telegram/messenger/R$string;->LatestVersionInstalled:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getVersion()Ljava/lang/CharSequence;

    move-result-object p0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, " ("

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p0, ")"

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 2219
    :cond_3
    sget p0, Lorg/telegram/messenger/R$string;->CheckingForUpdates:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 2218
    :cond_4
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getVersion()Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0
.end method

.method public final getStatus()I
    .locals 0

    .line 2193
    invoke-static {}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->access$getStatus$cp()I

    move-result p0

    return p0
.end method

.method public final getVersion()Ljava/lang/CharSequence;
    .locals 10
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2202
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsEngine()Z

    move-result p0

    const/4 v0, 0x0

    if-eqz p0, :cond_0

    invoke-static {}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->access$getSdkInitialized$cp()Z

    move-result p0

    if-eqz p0, :cond_0

    sget-object p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->getSDK_VERSION()Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    :cond_0
    move-object p0, v0

    .line 2203
    :goto_0
    sget-object v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->getSDK_BETA()Z

    move-result v1

    if-nez p0, :cond_1

    .line 2204
    invoke-static {}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->access$getSDK_DIR$cp()Ljava/io/File;

    move-result-object v2

    if-eqz v2, :cond_1

    .line 2205
    new-instance v2, Ljava/io/File;

    invoke-static {}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->access$getSDK_DIR$cp()Ljava/io/File;

    move-result-object v3

    const-string/jumbo v4, "v.txt"

    invoke-direct {v2, v3, v4}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 2206
    invoke-virtual {v2}, Ljava/io/File;->exists()Z

    move-result v3

    if-eqz v3, :cond_1

    .line 2207
    invoke-static {v2}, Lorg/simplifiles/SimpliFiles;->file(Ljava/io/File;)Lorg/simplifiles/files/SimpliFile;

    move-result-object v4

    const/4 v8, 0x2

    const/4 v9, 0x0

    const-wide/32 v5, 0x10000

    const/4 v7, 0x0

    invoke-static/range {v4 .. v9}, Lorg/simplifiles/files/SimpliFile;->readText$default(Lorg/simplifiles/files/SimpliFile;JLjava/nio/charset/Charset;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    .line 2208
    const-string/jumbo v1, "|1"

    const/4 v2, 0x0

    const/4 v3, 0x2

    invoke-static {p0, v1, v2, v3, v0}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v1

    const/16 v2, 0x7c

    .line 2209
    invoke-static {p0, v2, v0, v3, v0}, Lkotlin/text/StringsKt;->substringBefore$default(Ljava/lang/String;CLjava/lang/String;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    :cond_1
    if-eqz p0, :cond_3

    if-eqz v1, :cond_2

    .line 2212
    const-string v0, "-beta"

    goto :goto_1

    :cond_2
    const-string v0, ""

    :goto_1
    new-instance v1, Ljava/lang/StringBuilder;

    const-string/jumbo v2, "v"

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_3
    const-string p0, "SDK not unpacked"

    return-object p0
.end method

.method public final hashBytes(Ljava/io/InputStream;)Ljava/lang/String;
    .locals 7
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    const/4 p0, 0x0

    .line 2324
    :try_start_0
    const-string v0, "SHA-1"

    invoke-static {v0}, Ljava/security/MessageDigest;->getInstance(Ljava/lang/String;)Ljava/security/MessageDigest;

    move-result-object v0

    const/high16 v1, 0x100000

    .line 2325
    new-array v1, v1, [B

    .line 2327
    :goto_0
    invoke-virtual {p1, v1}, Ljava/io/InputStream;->read([B)I

    move-result v2

    const/4 v3, 0x0

    if-gtz v2, :cond_1

    .line 2334
    invoke-virtual {v0}, Ljava/security/MessageDigest;->digest()[B

    move-result-object v0

    .line 2335
    new-instance v1, Ljava/lang/StringBuilder;

    array-length v2, v0

    mul-int/lit8 v2, v2, 0x2

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(I)V

    .line 2336
    array-length v2, v0

    :goto_1
    if-ge v3, v2, :cond_0

    aget-byte v4, v0, v3

    .line 2337
    sget-object v5, Lkotlin/jvm/internal/StringCompanionObject;->INSTANCE:Lkotlin/jvm/internal/StringCompanionObject;

    const-string v5, "%02x"

    invoke-static {v4}, Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;

    move-result-object v4

    filled-new-array {v4}, [Ljava/lang/Object;

    move-result-object v4

    const/4 v6, 0x1

    invoke-static {v4, v6}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object v4

    invoke-static {v5, v4}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    add-int/lit8 v3, v3, 0x1

    goto :goto_1

    :catchall_0
    move-exception v0

    goto :goto_2

    .line 2339
    :cond_0
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :try_start_1
    invoke-static {p1, p0}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/security/NoSuchAlgorithmException; {:try_start_1 .. :try_end_1} :catch_0

    return-object v0

    :catch_0
    move-exception p1

    goto :goto_3

    :catch_1
    move-exception p1

    goto :goto_4

    .line 2331
    :cond_1
    :try_start_2
    invoke-virtual {v0, v1, v3, v2}, Ljava/security/MessageDigest;->update([BII)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    goto :goto_0

    .line 2339
    :goto_2
    :try_start_3
    throw v0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    :catchall_1
    move-exception v1

    :try_start_4
    invoke-static {p1, v0}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    throw v1
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_1
    .catch Ljava/security/NoSuchAlgorithmException; {:try_start_4 .. :try_end_4} :catch_0

    .line 2344
    :goto_3
    invoke-static {p1}, Lokhttp3/HttpUrl$$ExternalSyntheticBUOutline0;->m(Ljava/lang/Throwable;)V

    return-object p0

    .line 2342
    :goto_4
    invoke-static {p1}, Lokhttp3/HttpUrl$$ExternalSyntheticBUOutline0;->m(Ljava/lang/Throwable;)V

    return-object p0
.end method

.method public final isAppVersionCodeCompatible(Ljava/lang/String;Ljava/lang/String;)Z
    .locals 0
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2603
    sget p0, Lorg/telegram/messenger/BuildVars;->BUILD_VERSION:I

    .line 2604
    invoke-static {p2}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result p2

    .line 2601
    invoke-static {p1, p0, p2}, Lcom/exteragram/messenger/utils/AppUtils;->compareVersions(Ljava/lang/String;II)Z

    move-result p0

    return p0
.end method

.method public final isAppVersionCompatible(Ljava/lang/String;Ljava/lang/String;)Z
    .locals 0
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2594
    sget-object p0, Lorg/telegram/messenger/BuildVars;->BUILD_VERSION_STRING:Ljava/lang/String;

    .line 2592
    invoke-static {p1, p0, p2}, Lcom/exteragram/messenger/utils/AppUtils;->compareVersions(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z

    move-result p0

    return p0
.end method

.method public final isPendingUpdateVerified(Ljava/io/File;)Z
    .locals 8
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2365
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getPythonSdkUpdateMetaFile()Ljava/io/File;

    move-result-object p0

    .line 2366
    invoke-virtual {p0}, Ljava/io/File;->exists()Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    .line 2367
    const-string p0, "Pending Python SDK update has no signature"

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;)V

    return v1

    .line 2371
    :cond_0
    :try_start_0
    invoke-static {p0}, Lorg/simplifiles/SimpliFiles;->file(Ljava/io/File;)Lorg/simplifiles/files/SimpliFile;

    move-result-object v2

    const/4 v6, 0x2

    const/4 v7, 0x0

    const-wide/32 v3, 0x10000

    const/4 v5, 0x0

    invoke-static/range {v2 .. v7}, Lorg/simplifiles/files/SimpliFile;->readText$default(Lorg/simplifiles/files/SimpliFile;JLjava/nio/charset/Charset;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v2

    const-string/jumbo p0, "|"

    filled-new-array {p0}, [Ljava/lang/String;

    move-result-object v3

    const/4 v6, 0x6

    const/4 v7, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-static/range {v2 .. v7}, Lkotlin/text/StringsKt;->split$default(Ljava/lang/CharSequence;[Ljava/lang/String;ZIILjava/lang/Object;)Ljava/util/List;

    move-result-object p0
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 2376
    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result v0

    const/4 v2, 0x3

    if-eq v0, v2, :cond_1

    .line 2377
    const-string p0, "Malformed signature of a pending Python SDK update"

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;)V

    return v1

    .line 2380
    :cond_1
    invoke-interface {p0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    const/4 v1, 0x1

    invoke-interface {p0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    const/4 v2, 0x2

    invoke-interface {p0, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;

    invoke-static {p1, v0, v1, p0}, Lcom/exteragram/messenger/plugins/utils/SdkSignature;->verify(Ljava/io/File;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z

    move-result p0

    return p0

    :catch_0
    move-exception v0

    move-object p0, v0

    .line 2373
    const-string p1, "Failed to read pending Python SDK update signature"

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return v1
.end method

.method public final isSdkFromApk()Z
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2287
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->sdkFromApkMarker()Ljava/io/File;

    move-result-object v0

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v0

    if-nez v0, :cond_1

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->requestSdkFromApkFile()Ljava/io/File;

    move-result-object p0

    invoke-virtual {p0}, Ljava/io/File;->exists()Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method public final isSdkFromCurrentApk()Z
    .locals 8

    .line 2291
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->sdkFromApkMarker()Ljava/io/File;

    move-result-object v0

    .line 2292
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v1

    if-eqz v1, :cond_1

    :try_start_0
    sget-object v1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 2293
    invoke-static {v0}, Lorg/simplifiles/SimpliFiles;->file(Ljava/io/File;)Lorg/simplifiles/files/SimpliFile;

    move-result-object v2

    const/4 v6, 0x2

    const/4 v7, 0x0

    const-wide/32 v3, 0x10000

    const/4 v5, 0x0

    invoke-static/range {v2 .. v7}, Lorg/simplifiles/files/SimpliFile;->readText$default(Lorg/simplifiles/files/SimpliFile;JLjava/nio/charset/Charset;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    .line 2292
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

    .line 2294
    :goto_0
    invoke-static {v0}, Lkotlin/Result;->isFailure-impl(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    const/4 v0, 0x0

    :cond_0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->currentApkStamp()Ljava/lang/String;

    move-result-object p0

    .line 2292
    invoke-static {v0, p0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_1

    const/4 p0, 0x1

    goto :goto_1

    :cond_1
    const/4 p0, 0x0

    :goto_1
    return p0
.end method

.method public final isSdkVersionNewer(Ljava/lang/String;Z)Z
    .locals 0
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2584
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsPySdkBetaVersions()Z

    move-result p0

    if-nez p0, :cond_0

    sget-object p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->getSDK_BETA()Z

    move-result p0

    if-eqz p0, :cond_0

    xor-int/lit8 p0, p2, 0x1

    return p0

    .line 2587
    :cond_0
    sget-object p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->getSDK_VERSION()Ljava/lang/String;

    move-result-object p2

    if-eqz p2, :cond_1

    const-string p2, ">"

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->getSDK_VERSION()Ljava/lang/String;

    move-result-object p0

    invoke-static {p2, p1, p0}, Lcom/exteragram/messenger/utils/AppUtils;->compareVersions(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z

    move-result p0

    return p0

    :cond_1
    const/4 p0, 0x0

    return p0
.end method

.method public final parsePythonSdkUpdateResponse(Lorg/telegram/tgnet/TLRPC$messages_Messages;)Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;
    .locals 19
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2496
    new-instance v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;

    invoke-direct {v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;-><init>()V

    move-object/from16 v1, p1

    .line 2497
    iget-object v1, v1, Lorg/telegram/tgnet/TLRPC$messages_Messages;->messages:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    const/4 v4, 0x0

    const/4 v5, 0x0

    if-eqz v2, :cond_15

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lorg/telegram/tgnet/TLRPC$Message;

    .line 2498
    instance-of v6, v2, Lorg/telegram/tgnet/TLRPC$TL_message;

    if-eqz v6, :cond_0

    move-object v6, v2

    check-cast v6, Lorg/telegram/tgnet/TLRPC$TL_message;

    iget-object v7, v6, Lorg/telegram/tgnet/TLRPC$Message;->message:Ljava/lang/String;

    invoke-static {v7}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v7

    if-nez v7, :cond_0

    iget-object v7, v6, Lorg/telegram/tgnet/TLRPC$Message;->media:Lorg/telegram/tgnet/TLRPC$MessageMedia;

    instance-of v7, v7, Lorg/telegram/tgnet/TLRPC$TL_messageMediaDocument;

    if-nez v7, :cond_1

    goto :goto_0

    .line 2502
    :cond_1
    iget-object v7, v6, Lorg/telegram/tgnet/TLRPC$Message;->message:Ljava/lang/String;

    const-string/jumbo v8, "python_sdk_stable"

    const/4 v9, 0x2

    invoke-static {v7, v8, v5, v9, v4}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result v7

    .line 2503
    iget-object v8, v6, Lorg/telegram/tgnet/TLRPC$Message;->message:Ljava/lang/String;

    const-string/jumbo v10, "python_sdk_beta"

    invoke-static {v8, v10, v5, v9, v4}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result v8

    if-nez v7, :cond_2

    if-nez v8, :cond_2

    goto :goto_0

    :cond_2
    if-eqz v8, :cond_3

    .line 2507
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsPySdkBetaVersions()Z

    move-result v7

    if-nez v7, :cond_3

    goto :goto_0

    .line 2511
    :cond_3
    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    .line 2513
    iget-object v10, v6, Lorg/telegram/tgnet/TLRPC$Message;->message:Ljava/lang/String;

    const-string v11, "\n"

    move-object v12, v11

    filled-new-array {v12}, [Ljava/lang/String;

    move-result-object v11

    const/4 v14, 0x6

    const/4 v15, 0x0

    move-object v13, v12

    const/4 v12, 0x0

    move-object/from16 v16, v13

    const/4 v13, 0x0

    move-object/from16 v3, v16

    invoke-static/range {v10 .. v15}, Lkotlin/text/StringsKt;->split$default(Ljava/lang/CharSequence;[Ljava/lang/String;ZIILjava/lang/Object;)Ljava/util/List;

    move-result-object v10

    .line 2514
    invoke-interface {v10}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v10

    move v11, v5

    :goto_1
    invoke-interface {v10}, Ljava/util/Iterator;->hasNext()Z

    move-result v12

    if-eqz v12, :cond_11

    invoke-interface {v10}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v12

    check-cast v12, Ljava/lang/String;

    .line 2515
    invoke-static {v12}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v12

    invoke-virtual {v12}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v13

    .line 2516
    invoke-static {v13}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v12

    if-eqz v12, :cond_4

    if-eqz v11, :cond_4

    :goto_2
    const/4 v14, 0x1

    goto :goto_1

    .line 2519
    :cond_4
    const-string/jumbo v12, "python_sdk_"

    invoke-static {v13, v12, v5, v9, v4}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v12

    if-eqz v12, :cond_6

    if-eqz v8, :cond_5

    .line 2521
    const-string v11, "beta"

    goto :goto_3

    :cond_5
    const-string/jumbo v11, "stable"

    :goto_3
    invoke-virtual {v0, v11}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->setChannel(Ljava/lang/String;)V

    const/4 v11, 0x1

    goto :goto_1

    :cond_6
    if-nez v11, :cond_7

    .line 2524
    invoke-virtual {v7, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_2

    .line 2528
    :cond_7
    invoke-static {}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->access$getPYTHON_SDK_APP_VERSION_PATTERN$cp()Ljava/util/regex/Pattern;

    move-result-object v12

    invoke-virtual {v12, v13}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object v12

    .line 2529
    invoke-virtual {v12}, Ljava/util/regex/Matcher;->matches()Z

    move-result v14

    if-eqz v14, :cond_9

    const/4 v14, 0x1

    .line 2530
    invoke-virtual {v12, v14}, Ljava/util/regex/Matcher;->group(I)Ljava/lang/String;

    move-result-object v13

    invoke-virtual {v0, v13}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->setAppVersionOperator(Ljava/lang/String;)V

    .line 2531
    invoke-virtual {v12, v9}, Ljava/util/regex/Matcher;->group(I)Ljava/lang/String;

    move-result-object v12

    if-eqz v12, :cond_8

    invoke-static {v12}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v12

    invoke-virtual {v12}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v12

    goto :goto_4

    :cond_8
    move-object v12, v4

    :goto_4
    invoke-virtual {v0, v12}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->setAppVersion(Ljava/lang/String;)V

    goto :goto_2

    .line 2536
    :cond_9
    invoke-static {}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->access$getPYTHON_SDK_APP_VERSION_CODE_PATTERN$cp()Ljava/util/regex/Pattern;

    move-result-object v12

    invoke-virtual {v12, v13}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object v12

    .line 2537
    invoke-virtual {v12}, Ljava/util/regex/Matcher;->matches()Z

    move-result v14

    if-eqz v14, :cond_b

    const/4 v14, 0x1

    .line 2538
    invoke-virtual {v12, v14}, Ljava/util/regex/Matcher;->group(I)Ljava/lang/String;

    move-result-object v13

    invoke-virtual {v0, v13}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->setAppVersionCodeOperator(Ljava/lang/String;)V

    .line 2539
    invoke-virtual {v12, v9}, Ljava/util/regex/Matcher;->group(I)Ljava/lang/String;

    move-result-object v12

    if-eqz v12, :cond_a

    invoke-static {v12}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v12

    invoke-virtual {v12}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v12

    goto :goto_5

    :cond_a
    move-object v12, v4

    :goto_5
    invoke-virtual {v0, v12}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->setAppVersionCode(Ljava/lang/String;)V

    goto :goto_2

    .line 2543
    :cond_b
    const-string v12, "="

    filled-new-array {v12}, [Ljava/lang/String;

    move-result-object v14

    const/16 v17, 0x2

    const/16 v18, 0x0

    const/4 v15, 0x0

    const/16 v16, 0x2

    invoke-static/range {v13 .. v18}, Lkotlin/text/StringsKt;->split$default(Ljava/lang/CharSequence;[Ljava/lang/String;ZIILjava/lang/Object;)Ljava/util/List;

    move-result-object v12

    .line 2544
    invoke-interface {v12}, Ljava/util/List;->size()I

    move-result v13

    if-eq v13, v9, :cond_c

    goto/16 :goto_2

    .line 2547
    :cond_c
    invoke-interface {v12, v5}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v13

    check-cast v13, Ljava/lang/String;

    invoke-static {v13}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v13

    invoke-virtual {v13}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v13

    const/4 v14, 0x1

    .line 2548
    invoke-interface {v12, v14}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v12

    check-cast v12, Ljava/lang/String;

    invoke-static {v12}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v12

    invoke-virtual {v12}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v12

    .line 2549
    invoke-virtual {v13}, Ljava/lang/String;->hashCode()I

    move-result v15

    sparse-switch v15, :sswitch_data_0

    goto/16 :goto_1

    :sswitch_0
    const-string/jumbo v15, "signature"

    invoke-virtual {v13, v15}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v13

    if-nez v13, :cond_d

    goto/16 :goto_1

    .line 2553
    :cond_d
    invoke-virtual {v0, v12}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->setSignature(Ljava/lang/String;)V

    goto/16 :goto_1

    .line 2549
    :sswitch_1
    const-string/jumbo v15, "version"

    invoke-virtual {v13, v15}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v13

    if-nez v13, :cond_e

    goto/16 :goto_1

    .line 2551
    :cond_e
    iput-object v12, v0, Lorg/telegram/tgnet/TLRPC$TL_help_appUpdate;->version:Ljava/lang/String;

    goto/16 :goto_1

    .line 2549
    :sswitch_2
    const-string v15, "abi"

    invoke-virtual {v13, v15}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v13

    if-nez v13, :cond_f

    goto/16 :goto_1

    .line 2552
    :cond_f
    invoke-virtual {v0, v12}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->setAbi(Ljava/lang/String;)V

    goto/16 :goto_1

    .line 2549
    :sswitch_3
    const-string v15, "can_not_skip"

    invoke-virtual {v13, v15}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v13

    if-nez v13, :cond_10

    goto/16 :goto_1

    .line 2550
    :cond_10
    invoke-static {v12}, Ljava/lang/Boolean;->parseBoolean(Ljava/lang/String;)Z

    move-result v12

    iput-boolean v12, v0, Lorg/telegram/tgnet/TLRPC$TL_help_appUpdate;->can_not_skip:Z

    goto/16 :goto_1

    :cond_11
    const/4 v14, 0x1

    .line 2557
    iget-object v3, v6, Lorg/telegram/tgnet/TLRPC$Message;->media:Lorg/telegram/tgnet/TLRPC$MessageMedia;

    iget-object v3, v3, Lorg/telegram/tgnet/TLRPC$MessageMedia;->document:Lorg/telegram/tgnet/TLRPC$Document;

    iput-object v3, v0, Lorg/telegram/tgnet/TLRPC$TL_help_appUpdate;->document:Lorg/telegram/tgnet/TLRPC$Document;

    .line 2558
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->canInstall()Z

    move-result v3

    if-nez v3, :cond_12

    .line 2559
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->clear()V

    goto/16 :goto_0

    .line 2563
    :cond_12
    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$TL_help_appUpdate;->text:Ljava/lang/String;

    .line 2564
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 2565
    iget-object v3, v6, Lorg/telegram/tgnet/TLRPC$Message;->entities:Ljava/util/ArrayList;

    invoke-virtual {v3}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :cond_13
    :goto_6
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v6

    if-eqz v6, :cond_14

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lorg/telegram/tgnet/TLRPC$MessageEntity;

    .line 2566
    instance-of v7, v6, Lorg/telegram/tgnet/TLRPC$TL_messageEntityPre;

    if-nez v7, :cond_13

    .line 2567
    invoke-virtual {v1, v6}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_6

    .line 2570
    :cond_14
    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$TL_help_appUpdate;->entities:Ljava/util/ArrayList;

    .line 2571
    invoke-virtual {v0, v2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->setMessage(Lorg/telegram/tgnet/TLRPC$Message;)V

    goto :goto_7

    :cond_15
    const/4 v14, 0x1

    .line 2575
    :goto_7
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->getMessage()Lorg/telegram/tgnet/TLRPC$Message;

    move-result-object v1

    if-nez v1, :cond_16

    return-object v4

    .line 2578
    :cond_16
    iget-object v1, v0, Lorg/telegram/tgnet/TLRPC$TL_help_appUpdate;->document:Lorg/telegram/tgnet/TLRPC$Document;

    if-eqz v1, :cond_17

    iget-object v1, v0, Lorg/telegram/tgnet/TLRPC$TL_help_appUpdate;->version:Ljava/lang/String;

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_17

    move v3, v14

    goto :goto_8

    :cond_17
    move v3, v5

    :goto_8
    invoke-virtual {v0, v3}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->setAvailable(Z)V

    return-object v0

    :sswitch_data_0
    .sparse-switch
        -0x40b9c506 -> :sswitch_3
        0x17868 -> :sswitch_2
        0x14f51cd8 -> :sswitch_1
        0x3ffd98b8 -> :sswitch_0
    .end sparse-switch
.end method

.method public final requestSdkFromApkFile()Ljava/io/File;
    .locals 3
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2385
    new-instance p0, Ljava/io/File;

    new-instance v0, Ljava/io/File;

    invoke-static {}, Lorg/telegram/messenger/ApplicationLoader;->getFilesDirFixed()Ljava/io/File;

    move-result-object v1

    const-string v2, "chaquopy"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    const-string v1, ".restoreSdk"

    invoke-direct {p0, v0, v1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    return-object p0
.end method

.method public final restoreSdkFromApk()V
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2475
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->requestSdkFromApkFile()Ljava/io/File;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->touchFile(Ljava/io/File;)V

    return-void
.end method

.method public final savePythonSdkArchive(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;Z)V
    .locals 6
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2663
    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;->getMessage()Lorg/telegram/tgnet/TLRPC$Message;

    move-result-object v0

    .line 2664
    iget-object v1, p1, Lorg/telegram/tgnet/TLRPC$TL_help_appUpdate;->document:Lorg/telegram/tgnet/TLRPC$Document;

    .line 2665
    invoke-static {}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->access$isLoading$cp()Z

    move-result v2

    if-nez v2, :cond_2

    if-eqz v0, :cond_2

    if-nez v1, :cond_0

    goto :goto_0

    .line 2669
    :cond_0
    new-instance v2, Lorg/telegram/messenger/MessageObject;

    sget v3, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    const/4 v4, 0x0

    const/4 v5, 0x1

    invoke-direct {v2, v3, v0, v4, v5}, Lorg/telegram/messenger/MessageObject;-><init>(ILorg/telegram/tgnet/TLRPC$Message;ZZ)V

    .line 2670
    invoke-static {v5}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->access$setLoading$cp(Z)V

    const/4 v0, 0x3

    .line 2671
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->updateStatus(I)V

    .line 2673
    iget-boolean v0, v2, Lorg/telegram/messenger/MessageObject;->mediaExists:Z

    if-eqz v0, :cond_1

    .line 2674
    invoke-direct {p0, p1, v1, p2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->copyArchiveToPluginsDirectory(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;Lorg/telegram/tgnet/TLRPC$Document;Z)V

    return-void

    .line 2679
    :cond_1
    sget p0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {p0}, Lorg/telegram/messenger/DownloadController;->getInstance(I)Lorg/telegram/messenger/DownloadController;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/messenger/DownloadController;->generateObserverTag()I

    move-result p0

    .line 2678
    invoke-static {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->access$setTAG$cp(I)V

    .line 2680
    sget p0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {p0}, Lorg/telegram/messenger/FileLoader;->getInstance(I)Lorg/telegram/messenger/FileLoader;

    move-result-object p0

    .line 2681
    invoke-virtual {p0, v1, v2, v5, v4}, Lorg/telegram/messenger/FileLoader;->loadFile(Lorg/telegram/tgnet/TLRPC$Document;Ljava/lang/Object;II)V

    .line 2682
    sget p0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {p0}, Lorg/telegram/messenger/DownloadController;->getInstance(I)Lorg/telegram/messenger/DownloadController;

    move-result-object p0

    .line 2683
    invoke-static {v1}, Lorg/telegram/messenger/FileLoader;->getAttachFileName(Lorg/telegram/tgnet/TLObject;)Ljava/lang/String;

    move-result-object v0

    .line 2685
    new-instance v3, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$savePythonSdkArchive$1;

    invoke-direct {v3, p1, v1, p2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$savePythonSdkArchive$1;-><init>(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;Lorg/telegram/tgnet/TLRPC$Document;Z)V

    .line 2682
    invoke-virtual {p0, v0, v2, v3}, Lorg/telegram/messenger/DownloadController;->addLoadingFileObserver(Ljava/lang/String;Lorg/telegram/messenger/MessageObject;Lorg/telegram/messenger/DownloadController$FileDownloadProgressListener;)V

    :cond_2
    :goto_0
    return-void
.end method

.method public final sdkFromApk()Ljava/io/InputStream;
    .locals 3
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2282
    sget-object p0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {p0}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object p0

    sget-object v0, Landroid/os/Build;->SUPPORTED_ABIS:[Ljava/lang/String;

    const/4 v1, 0x0

    aget-object v0, v0, v1

    new-instance v1, Ljava/lang/StringBuilder;

    const-string/jumbo v2, "plugins_pysdk/sdk-"

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ".zip"

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;)Ljava/io/InputStream;

    move-result-object p0

    return-object p0
.end method

.method public final setBuildFromApk(Z)V
    .locals 2
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2299
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->sdkFromApkMarker()Ljava/io/File;

    move-result-object v0

    if-nez p1, :cond_0

    .line 2301
    sget-object p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-static {p0, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$deleteFileIfExists(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/io/File;)V

    return-void

    .line 2304
    :cond_0
    :try_start_0
    sget-object p1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 2305
    invoke-static {v0}, Lorg/simplifiles/SimpliFiles;->file(Ljava/io/File;)Lorg/simplifiles/files/SimpliFile;

    move-result-object p1

    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->currentApkStamp()Ljava/lang/String;

    move-result-object p0

    const/4 v0, 0x2

    const/4 v1, 0x0

    invoke-static {p1, p0, v1, v0, v1}, Lorg/simplifiles/files/SimpliFile;->writeTextAtomic$default(Lorg/simplifiles/files/SimpliFile;Ljava/lang/String;Ljava/nio/charset/Charset;ILjava/lang/Object;)V

    .line 2306
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    .line 2304
    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception p0

    sget-object p1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 2306
    :goto_0
    invoke-static {p0}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object p0

    if-eqz p0, :cond_1

    .line 2307
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :cond_1
    return-void
.end method

.method public final setNotifyWhenChangeStatus(Z)V
    .locals 0

    .line 2195
    invoke-static {p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->access$setNotifyWhenChangeStatus$cp(Z)V

    return-void
.end method

.method public final setStatus(I)V
    .locals 0

    .line 2193
    invoke-static {p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->access$setStatus$cp(I)V

    return-void
.end method

.method public final zipFolder(Ljava/io/File;Ljava/io/File;)V
    .locals 0
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 2611
    invoke-static {p1}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object p0

    sget-object p1, Lorg/simplifiles/files/OverwritePolicy;->REPLACE:Lorg/simplifiles/files/OverwritePolicy;

    invoke-virtual {p0, p2, p1}, Lorg/simplifiles/files/SimpliDirectory;->zipTo(Ljava/io/File;Lorg/simplifiles/files/OverwritePolicy;)Lorg/simplifiles/files/SimpliFile;

    return-void
.end method
