.class public final Lcom/exteragram/messenger/debug/DebugActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;,
        Lcom/exteragram/messenger/debug/DebugActivity$WhenMappings;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u0007\n\u0002\u0008\t\u0018\u00002\u00020\u0001:\u0001\u001dB\u0007\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0008\u0010\u0004\u001a\u00020\u0005H\u0016J(\u0010\u0006\u001a\u00020\u00072\u0016\u0010\u0008\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\u0008\u0012\u0004\u0012\u00020\n`\u000b2\u0006\u0010\u000c\u001a\u00020\rH\u0014J0\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0014J\u0008\u0010\u0017\u001a\u00020\nH\u0002J\u0008\u0010\u0018\u001a\u00020\nH\u0002J\u0008\u0010\u0019\u001a\u00020\nH\u0002J\u0008\u0010\u001a\u001a\u00020\u0005H\u0002J\u0008\u0010\u001b\u001a\u00020\u0005H\u0002J\u0008\u0010\u001c\u001a\u00020\u0007H\u0002\u00a8\u0006\u001e"
    }
    d2 = {
        "Lcom/exteragram/messenger/debug/DebugActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "<init>",
        "()V",
        "getTitle",
        "",
        "fillItems",
        "",
        "items",
        "Ljava/util/ArrayList;",
        "Lorg/telegram/ui/Components/UItem;",
        "Lkotlin/collections/ArrayList;",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "onClick",
        "item",
        "view",
        "Landroid/view/View;",
        "position",
        "",
        "x",
        "",
        "y",
        "createFreezeThresholdSliderItem",
        "createLoadThresholdSliderItem",
        "createHeapLimitSliderItem",
        "getHeapUsageValue",
        "getIpConfigOverrideValue",
        "showIpConfigOverrideDialog",
        "DebugItem",
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
        "SMAP\nDebugActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DebugActivity.kt\ncom/exteragram/messenger/debug/DebugActivity\n+ 2 SparseArray.kt\nandroidx/core/util/SparseArrayKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,352:1\n25#2:353\n1#3:354\n*S KotlinDebug\n*F\n+ 1 DebugActivity.kt\ncom/exteragram/messenger/debug/DebugActivity\n*L\n176#1:353\n*E\n"
    }
.end annotation


# direct methods
.method public static $r8$lambda$0H08GdubpMnhY7z1VPaMUMp0YVg(Lcom/exteragram/messenger/debug/DebugActivity;)Lkotlin/Unit;
    .locals 2

    .line 212
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v0

    iget-object v0, v0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    .line 213
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    .line 214
    sget v0, Lorg/telegram/messenger/R$raw;->contact_check:I

    .line 215
    const-string v1, "Heap dumps deleted."

    .line 213
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 216
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    .line 217
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$2gZKtkRetst5P45VLoPMSHCuyhk(Lcom/exteragram/messenger/debug/DebugActivity;)V
    .locals 2

    .line 193
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    .line 194
    sget v0, Lorg/telegram/messenger/R$raw;->contact_check:I

    .line 195
    const-string v1, "Translation cache cleared."

    .line 193
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 196
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method public static $r8$lambda$3HuiuI9fIVgRDzaMKeItB0b4lQU(ILjava/lang/Integer;)V
    .locals 1

    .line 271
    sget-object v0, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    mul-int/2addr p1, p0

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/debug/HeapMonitor;->setLimitMb(I)V

    return-void
.end method

.method public static $r8$lambda$3ftFdNOmn0AoZ3uOqD7uoYEEzXE(Lcom/exteragram/messenger/debug/DebugActivity;)V
    .locals 2

    .line 149
    sget-object v0, Lcom/exteragram/messenger/api/db/ExteraDatabase;->Companion:Lcom/exteragram/messenger/api/db/ExteraDatabase$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/api/db/ExteraDatabase$Companion;->getInstance()Lcom/exteragram/messenger/api/db/ExteraDatabase;

    move-result-object v0

    invoke-virtual {v0}, Landroidx/room/RoomDatabase;->clearAllTables()V

    const/4 v0, 0x0

    const/4 v1, 0x1

    .line 150
    invoke-static {v0, v1, v0}, Lcom/exteragram/messenger/api/ApiController;->resetSyncState$default(Landroid/content/SharedPreferences;ILjava/lang/Object;)V

    .line 151
    new-instance v0, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda19;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda19;-><init>(Lcom/exteragram/messenger/debug/DebugActivity;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$77RamS_C6OiBWYNEOLpxH5fXQak(Z)V
    .locals 0

    .line 144
    invoke-static {p0}, Lcom/exteragram/messenger/debug/DebugConfig;->setDisableChatFadeWallpaperBlend(Z)V

    return-void
.end method

.method public static $r8$lambda$7adC3wPZzincW442S_BS0QlhFJo(Z)V
    .locals 1

    .line 218
    sget-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->setEnabled(Z)V

    return-void
.end method

.method public static $r8$lambda$AZJ401Rohj7ho11R6f40saRWxB4(ILjava/lang/Integer;)Ljava/lang/CharSequence;
    .locals 0

    .line 247
    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    mul-int/2addr p1, p0

    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p1, " ms"

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$CbguHwmSr_eAdbsmez-XuoYkLaE(ILjava/lang/Integer;)Ljava/lang/CharSequence;
    .locals 0

    .line 270
    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    mul-int/2addr p1, p0

    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p1, " MB"

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$DwC3d-sDDK3RMjtCUOUet7rjvWk(Z)V
    .locals 1

    .line 228
    sget-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->setEnabled(Z)V

    return-void
.end method

.method public static $r8$lambda$FXqHAInsOSCxaiN3tolJfNNz8Vg(Lcom/exteragram/messenger/debug/DebugActivity;)Lkotlin/Unit;
    .locals 2

    .line 231
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v0

    iget-object v0, v0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    .line 232
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    .line 233
    sget v0, Lorg/telegram/messenger/R$raw;->contact_check:I

    .line 234
    const-string v1, "Freeze reports deleted."

    .line 232
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 235
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    .line 236
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$M3FuCoLT6hNuzwgZD4llqu1--yE(Ljava/lang/Integer;)V
    .locals 1

    .line 259
    sget-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->setCpuPercent(I)V

    return-void
.end method

.method public static $r8$lambda$SJdEHBZnL9NoA7X68vORHRou62M(Lcom/exteragram/messenger/debug/DebugActivity;)Lkotlin/Unit;
    .locals 2

    .line 222
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v0

    iget-object v0, v0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    .line 223
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    .line 224
    sget v0, Lorg/telegram/messenger/R$raw;->contact_check:I

    .line 225
    const-string v1, "Load reports deleted."

    .line 223
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 226
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    .line 227
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$TSIsEP8Zu81ieOsxL1x8oUKcIAA(Lorg/telegram/ui/Components/EditTextBoldCursor;Lcom/exteragram/messenger/debug/DebugActivity;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 1

    .line 329
    invoke-virtual {p0}, Lcom/exteragram/messenger/components/ReceiveContentEditText;->getText()Landroid/text/Editable;

    move-result-object p0

    const/4 p2, 0x0

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-static {p0}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    :cond_0
    move-object p0, p2

    :goto_0
    if-nez p0, :cond_1

    const-string p0, ""

    .line 330
    :cond_1
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p3

    const/4 v0, 0x1

    if-eqz p3, :cond_2

    .line 331
    invoke-static {p2}, Lorg/telegram/tgnet/ConnectionsManager;->setDebugDnsConfigOverride(Ljava/lang/String;)V

    .line 332
    invoke-virtual {p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    .line 333
    invoke-static {p1}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    .line 334
    sget p1, Lorg/telegram/messenger/R$raw;->contact_check:I

    .line 335
    const-string p2, "ipconfigv3 override cleared."

    .line 333
    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 336
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void

    .line 340
    :cond_2
    iget p2, p1, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {p2, p0}, Lorg/telegram/tgnet/ConnectionsManager;->setAndApplyDebugDnsConfigOverride(ILjava/lang/String;)Z

    move-result p0

    .line 341
    invoke-virtual {p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p2

    iget-object p2, p2, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    .line 342
    invoke-static {p1}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p1

    if-eqz p0, :cond_3

    .line 343
    sget p2, Lorg/telegram/messenger/R$raw;->contact_check:I

    goto :goto_1

    :cond_3
    sget p2, Lorg/telegram/messenger/R$raw;->error:I

    :goto_1
    if-eqz p0, :cond_4

    .line 344
    const-string p0, "ipconfigv3 override saved and submitted."

    goto :goto_2

    :cond_4
    const-string p0, "Failed to decode ipconfigv3 override."

    .line 342
    :goto_2
    invoke-virtual {p1, p2, p0}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 345
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method public static $r8$lambda$V9rk6H6byQOV8r82VudFyLqtxZc(Z)V
    .locals 1

    .line 208
    sget-object v0, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->setEnabled(Z)V

    return-void
.end method

.method public static $r8$lambda$W9lQE8eAEF3_4jJ_BLykbIqcH1c(Lcom/exteragram/messenger/debug/DebugActivity;)V
    .locals 2

    .line 152
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    .line 153
    sget v0, Lorg/telegram/messenger/R$raw;->contact_check:I

    .line 154
    const-string v1, "Successfully cleared all tables."

    .line 152
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 155
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method public static $r8$lambda$e2nrzEVKUQt9LORey3r4IIWe8dU(Z)V
    .locals 0

    .line 142
    invoke-static {p0}, Lcom/exteragram/messenger/debug/DebugConfig;->setForceCompactSavedMusic(Z)V

    return-void
.end method

.method public static $r8$lambda$fwK_7Z5MlqQ9cVB6ltUWJjyzrWE(Ljava/lang/Integer;)Ljava/lang/CharSequence;
    .locals 1

    .line 258
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p0, "% of a core"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$hHOCITIPCaVcH_OtOkujnw_jtRI(Z)V
    .locals 0

    .line 145
    invoke-static {p0}, Lcom/exteragram/messenger/debug/DebugConfig;->setChatFadeUseWhiteBackground(Z)V

    return-void
.end method

.method public static $r8$lambda$lzo-wUdGdWliwSXzF7c5jQegn1E(ILjava/lang/Integer;)V
    .locals 1

    .line 248
    sget-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    mul-int/2addr p1, p0

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/debug/FreezeMonitor;->setThresholdMs(I)V

    return-void
.end method

.method public static $r8$lambda$oRUDN5OsGz-ENNFOvogpUjB8-Hk(Z)V
    .locals 0

    .line 141
    invoke-static {p0}, Lcom/exteragram/messenger/debug/DebugConfig;->setDebugCameraMetrics(Z)V

    return-void
.end method

.method public static $r8$lambda$w76dGtnD41ADWvGSvDkImgYRL_M(Z)V
    .locals 0

    .line 143
    invoke-static {p0}, Lcom/exteragram/messenger/debug/DebugConfig;->setDisableApiRequests(Z)V

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 38
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    return-void
.end method

.method private final createFreezeThresholdSliderItem()Lorg/telegram/ui/Components/UItem;
    .locals 7

    .line 245
    sget-object p0, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->getThresholdMs()I

    move-result p0

    const/16 v0, 0x64

    div-int/lit8 v3, p0, 0x64

    .line 242
    new-instance v5, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda2;

    invoke-direct {v5, v0}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda2;-><init>(I)V

    new-instance v6, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda3;

    invoke-direct {v6, v0}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda3;-><init>(I)V

    const/4 v1, 0x1

    const/4 v2, 0x2

    const/16 v4, 0x14

    invoke-static/range {v1 .. v6}, Lorg/telegram/ui/Components/UItem;->asIntSlideView(IIIILorg/telegram/messenger/Utilities$CallbackReturn;Lorg/telegram/messenger/Utilities$Callback;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    return-object p0
.end method

.method private final createHeapLimitSliderItem()Lorg/telegram/ui/Components/UItem;
    .locals 8

    .line 268
    sget-object p0, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getLimitMb()I

    move-result v0

    const/16 v1, 0x10

    div-int/lit8 v4, v0, 0x10

    .line 269
    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getMaxLimitMb()I

    move-result p0

    div-int/lit8 v5, p0, 0x10

    .line 265
    new-instance v6, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda0;

    invoke-direct {v6, v1}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda0;-><init>(I)V

    new-instance v7, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda1;

    invoke-direct {v7, v1}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda1;-><init>(I)V

    const/4 v2, 0x1

    const/4 v3, 0x4

    invoke-static/range {v2 .. v7}, Lorg/telegram/ui/Components/UItem;->asIntSlideView(IIIILorg/telegram/messenger/Utilities$CallbackReturn;Lorg/telegram/messenger/Utilities$Callback;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    return-object p0
.end method

.method private final createLoadThresholdSliderItem()Lorg/telegram/ui/Components/UItem;
    .locals 6

    .line 256
    sget-object p0, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->getCpuPercent()I

    move-result v2

    .line 258
    new-instance v4, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda4;

    invoke-direct {v4}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda4;-><init>()V

    .line 259
    new-instance v5, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda5;

    invoke-direct {v5}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda5;-><init>()V

    const/4 v0, 0x1

    const/4 v1, 0x1

    const/16 v3, 0x32

    .line 253
    invoke-static/range {v0 .. v5}, Lorg/telegram/ui/Components/UItem;->asIntSlideView(IIIILorg/telegram/messenger/Utilities$CallbackReturn;Lorg/telegram/messenger/Utilities$Callback;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    return-object p0
.end method

.method private final getHeapUsageValue()Ljava/lang/String;
    .locals 5

    .line 276
    sget-object p0, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getUsedBytes()J

    move-result-wide v0

    const/4 p0, 0x1

    const/4 v2, 0x0

    invoke-static {v0, v1, p0, v2}, Lorg/telegram/messenger/AndroidUtilities;->formatFileSize(JZZ)Ljava/lang/String;

    move-result-object v0

    .line 277
    invoke-static {}, Ljava/lang/Runtime;->getRuntime()Ljava/lang/Runtime;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Runtime;->maxMemory()J

    move-result-wide v3

    invoke-static {v3, v4, p0, v2}, Lorg/telegram/messenger/AndroidUtilities;->formatFileSize(JZZ)Ljava/lang/String;

    move-result-object p0

    .line 278
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, " / "

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final getIpConfigOverrideValue()Ljava/lang/String;
    .locals 1

    .line 282
    invoke-static {}, Lorg/telegram/tgnet/ConnectionsManager;->getDebugDnsConfigOverride()Ljava/lang/String;

    move-result-object p0

    if-eqz p0, :cond_1

    .line 283
    invoke-static {p0}, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 284
    :cond_0
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, " chars"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 283
    :cond_1
    :goto_0
    const-string p0, "Not set"

    return-object p0
.end method

.method private final showIpConfigOverrideDialog()V
    .locals 11

    .line 288
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_0

    return-void

    .line 289
    :cond_0
    new-instance v1, Lorg/telegram/ui/Components/EditTextBoldCursor;

    invoke-direct {v1, v0}, Lorg/telegram/ui/Components/EditTextBoldCursor;-><init>(Landroid/content/Context;)V

    const/4 v2, 0x1

    .line 290
    iput-boolean v2, v1, Lorg/telegram/ui/Components/EditTextBoldCursor;->lineYFix:Z

    const/high16 v3, 0x41900000    # 18.0f

    .line 291
    invoke-virtual {v1, v2, v3}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setTextSize(IF)V

    .line 292
    invoke-static {}, Lorg/telegram/tgnet/ConnectionsManager;->getDebugDnsConfigOverride()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 293
    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_dialogTextBlack:I

    iget-object v3, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v2, v3}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v2

    invoke-virtual {v1, v2}, Lorg/telegram/ui/Components/EditTextEffects;->setTextColor(I)V

    .line 294
    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_dialogTextHint:I

    iget-object v3, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v2, v3}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v2

    invoke-virtual {v1, v2}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setHintColor(I)V

    .line 295
    const-string v2, "Base64 from Firebase Remote Config"

    invoke-virtual {v1, v2}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setHintText(Ljava/lang/CharSequence;)V

    const v2, 0x800033

    .line 296
    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setGravity(I)V

    const/4 v2, 0x6

    .line 297
    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setMinLines(I)V

    const/16 v2, 0xa

    .line 298
    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setMaxLines(I)V

    const v2, 0xa0001

    .line 299
    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setInputType(I)V

    .line 300
    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_dialogInputFieldActivated:I

    iget-object v3, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v2, v3}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v3

    invoke-virtual {v1, v3}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setCursorColor(I)V

    .line 302
    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_dialogInputField:I

    iget-object v4, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v3, v4}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v3

    .line 303
    iget-object v4, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v2, v4}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v2

    .line 304
    sget v4, Lorg/telegram/ui/ActionBar/Theme;->key_text_RedRegular:I

    iget-object v5, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v4, v5}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v4

    .line 301
    invoke-virtual {v1, v3, v2, v4}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setLineColors(III)V

    const/4 v2, 0x0

    .line 306
    invoke-virtual {v1, v2}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    const/high16 v3, 0x40c00000    # 6.0f

    .line 307
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    const/4 v5, 0x0

    invoke-virtual {v1, v5, v4, v5, v3}, Landroid/view/View;->setPadding(IIII)V

    .line 308
    invoke-virtual {v1}, Lcom/exteragram/messenger/components/ReceiveContentEditText;->getText()Landroid/text/Editable;

    move-result-object v3

    if-eqz v3, :cond_1

    invoke-interface {v3}, Ljava/lang/CharSequence;->length()I

    move-result v5

    :cond_1
    invoke-virtual {v1, v5}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setSelection(I)V

    .line 311
    new-instance v3, Landroid/widget/FrameLayout;

    invoke-direct {v3, v0}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    const/high16 v9, 0x41c00000    # 24.0f

    const/4 v10, 0x0

    const/4 v4, -0x1

    const/high16 v5, -0x40000000    # -2.0f

    const v6, 0x800033

    const/high16 v7, 0x41c00000    # 24.0f

    const/high16 v8, 0x41400000    # 12.0f

    .line 314
    invoke-static/range {v4 .. v10}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v4

    .line 312
    invoke-virtual {v3, v1, v4}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 325
    new-instance v4, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-direct {v4, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 326
    const-string v0, "ipconfigv3 override"

    invoke-virtual {v4, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object v0

    .line 327
    invoke-virtual {v0, v3}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setView(Landroid/view/View;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object v0

    .line 328
    new-instance v3, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda20;

    invoke-direct {v3, v1, p0}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda20;-><init>(Lorg/telegram/ui/Components/EditTextBoldCursor;Lcom/exteragram/messenger/debug/DebugActivity;)V

    const-string v1, "Apply"

    invoke-virtual {v0, v1, v3}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object v0

    .line 347
    const-string v1, "Cancel"

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object v0

    .line 348
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->create()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object v0

    .line 349
    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    return-void
.end method


# virtual methods
.method public fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/UItem;",
            ">;",
            "Lorg/telegram/ui/Components/UniversalAdapter;",
            ")V"
        }
    .end annotation

    .line 69
    sget-object p2, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->DEBUG_CAMERA_METRICS:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result p2

    const-string v0, "Metrics in InstantCameraView"

    invoke-static {p2, v0}, Lorg/telegram/ui/Components/UItem;->asCheck(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 70
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getDebugCameraMetrics()Z

    move-result v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 69
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 71
    sget-object p2, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->FORCE_COMPACT_SAVED_MUSIC:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result p2

    const-string v0, "Compact Saved Music view"

    invoke-static {p2, v0}, Lorg/telegram/ui/Components/UItem;->asCheck(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 72
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getForceCompactSavedMusic()Z

    move-result v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 71
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 73
    sget-object p2, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->DISABLE_API_REQUESTS:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result p2

    const-string v0, "Disable exteraGram API requests"

    invoke-static {p2, v0}, Lorg/telegram/ui/Components/UItem;->asCheck(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 74
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getDisableApiRequests()Z

    move-result v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 73
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 75
    sget-object p2, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->DISABLE_CHAT_FADE_WALLPAPER_BLEND:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result p2

    const-string v0, "Disable chat fade wallpaper blend"

    invoke-static {p2, v0}, Lorg/telegram/ui/Components/UItem;->asCheck(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 76
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getDisableChatFadeWallpaperBlend()Z

    move-result v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 75
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 77
    sget-object p2, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->CHAT_FADE_USE_WHITE_BACKGROUND:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result p2

    const-string v0, "Use windowBackgroundWhite for chat fade"

    invoke-static {p2, v0}, Lorg/telegram/ui/Components/UItem;->asCheck(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 78
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getChatFadeUseWhiteBackground()Z

    move-result v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 77
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 79
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 81
    sget-object p2, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->FORCE_REFRESH_REMOTE_CONFIG:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result p2

    const-string v0, "Refresh Remote Config"

    invoke-static {p2, v0}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 82
    sget-object p2, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->CLEAR_DB:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result p2

    const-string v0, "Clear exteraDatabase"

    invoke-static {p2, v0}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 83
    sget-object p2, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->CLEAR_TRANSLATIONS:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result p2

    const-string v0, "Clear translations cache"

    invoke-static {p2, v0}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 84
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 86
    sget-object p2, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->SET_IPCONFIG_OVERRIDE:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result p2

    const-string v0, "Set ipconfigv3 override"

    invoke-direct {p0}, Lcom/exteragram/messenger/debug/DebugActivity;->getIpConfigOverrideValue()Ljava/lang/String;

    move-result-object v1

    invoke-static {p2, v0, v1}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 87
    sget-object p2, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->CLEAR_IPCONFIG_OVERRIDE:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result p2

    const-string v0, "Clear ipconfigv3 override"

    invoke-static {p2, v0}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 88
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 90
    sget-object p2, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->HEAP_MONITOR:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result p2

    const-string v0, "Heap limit monitor"

    invoke-static {p2, v0}, Lorg/telegram/ui/Components/UItem;->asCheck(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 91
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getHeapMonitorEnabled()Z

    move-result v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 90
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 92
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getHeapMonitorEnabled()Z

    move-result p2

    if-eqz p2, :cond_0

    .line 93
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/DebugActivity;->createHeapLimitSliderItem()Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 95
    :cond_0
    sget-object p2, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->DUMP_HEAP:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result p2

    const-string v0, "Dump & share heap"

    invoke-direct {p0}, Lcom/exteragram/messenger/debug/DebugActivity;->getHeapUsageValue()Ljava/lang/String;

    move-result-object v1

    invoke-static {p2, v0, v1}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 96
    sget-object p2, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/HeapMonitor;->getLastDump()Ljava/io/File;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 98
    sget-object v1, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->SHARE_HEAP_DUMP:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result v1

    invoke-virtual {v0}, Ljava/io/File;->length()J

    move-result-wide v2

    invoke-static {v2, v3}, Lorg/telegram/messenger/AndroidUtilities;->formatFileSize(J)Ljava/lang/String;

    move-result-object v0

    const-string v2, "Share last heap dump"

    invoke-static {v1, v2, v0}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 100
    :cond_1
    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/HeapMonitor;->getDumpsSize()J

    move-result-wide v0

    const-wide/16 v2, 0x0

    cmp-long p2, v0, v2

    if-lez p2, :cond_2

    .line 102
    sget-object p2, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->DELETE_HEAP_DUMPS:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result p2

    const-string v4, "Delete heap dumps"

    invoke-static {v0, v1}, Lorg/telegram/messenger/AndroidUtilities;->formatFileSize(J)Ljava/lang/String;

    move-result-object v0

    invoke-static {p2, v4, v0}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 104
    :cond_2
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 106
    sget-object p2, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->LOAD_MONITOR:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result p2

    const-string v0, "Load monitor"

    invoke-static {p2, v0}, Lorg/telegram/ui/Components/UItem;->asCheck(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 107
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getLoadMonitorEnabled()Z

    move-result v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 106
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 108
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getLoadMonitorEnabled()Z

    move-result p2

    if-eqz p2, :cond_3

    .line 109
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/DebugActivity;->createLoadThresholdSliderItem()Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 111
    :cond_3
    sget-object p2, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->CAPTURE_LOAD_REPORT:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result p2

    sget-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/LoadMonitor;->getSummary()Ljava/lang/String;

    move-result-object v1

    const-string v4, "Capture & share load report"

    invoke-static {p2, v4, v1}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 112
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/LoadMonitor;->getLastReport()Ljava/io/File;

    move-result-object p2

    if-eqz p2, :cond_4

    .line 114
    sget-object v1, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->SHARE_LOAD_REPORT:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result v1

    invoke-virtual {p2}, Ljava/io/File;->length()J

    move-result-wide v4

    invoke-static {v4, v5}, Lorg/telegram/messenger/AndroidUtilities;->formatFileSize(J)Ljava/lang/String;

    move-result-object p2

    const-string v4, "Share last load report"

    invoke-static {v1, v4, p2}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 116
    :cond_4
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/LoadMonitor;->getReportsSize()J

    move-result-wide v0

    cmp-long p2, v0, v2

    if-lez p2, :cond_5

    .line 118
    sget-object p2, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->DELETE_LOAD_REPORTS:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result p2

    const-string v4, "Delete load reports"

    invoke-static {v0, v1}, Lorg/telegram/messenger/AndroidUtilities;->formatFileSize(J)Ljava/lang/String;

    move-result-object v0

    invoke-static {p2, v4, v0}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 120
    :cond_5
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 122
    sget-object p2, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->FREEZE_MONITOR:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result p2

    const-string v0, "Freeze monitor"

    invoke-static {p2, v0}, Lorg/telegram/ui/Components/UItem;->asCheck(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 123
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getFreezeMonitorEnabled()Z

    move-result v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 122
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 124
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getFreezeMonitorEnabled()Z

    move-result p2

    if-eqz p2, :cond_6

    .line 125
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/DebugActivity;->createFreezeThresholdSliderItem()Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 127
    :cond_6
    sget-object p0, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->getReport()Ljava/io/File;

    move-result-object p2

    if-eqz p2, :cond_7

    .line 129
    sget-object v0, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->SHARE_FREEZE_REPORT:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result v0

    invoke-virtual {p2}, Ljava/io/File;->length()J

    move-result-wide v4

    invoke-static {v4, v5}, Lorg/telegram/messenger/AndroidUtilities;->formatFileSize(J)Ljava/lang/String;

    move-result-object p2

    const-string v1, "Share freeze report"

    invoke-static {v0, v1, p2}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 131
    :cond_7
    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->getReportsSize()J

    move-result-wide v0

    cmp-long p0, v0, v2

    if-lez p0, :cond_8

    .line 133
    sget-object p0, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->DELETE_FREEZE_REPORTS:Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getId()I

    move-result p0

    const-string p2, "Delete freeze reports"

    invoke-static {v0, v1}, Lorg/telegram/messenger/AndroidUtilities;->formatFileSize(J)Ljava/lang/String;

    move-result-object v0

    invoke-static {p0, p2, v0}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 135
    :cond_8
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 66
    const-string p0, "Debug"

    return-object p0
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 4

    .line 139
    iget p2, p1, Lorg/telegram/ui/Components/UItem;->id:I

    if-lez p2, :cond_6

    invoke-static {}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p3

    invoke-interface {p3}, Ljava/util/List;->size()I

    move-result p3

    if-le p2, p3, :cond_0

    goto/16 :goto_5

    .line 140
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p2

    iget p3, p1, Lorg/telegram/ui/Components/UItem;->id:I

    const/4 p4, 0x1

    sub-int/2addr p3, p4

    invoke-interface {p2, p3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;

    sget-object p3, Lcom/exteragram/messenger/debug/DebugActivity$WhenMappings;->$EnumSwitchMapping$0:[I

    invoke-virtual {p2}, Ljava/lang/Enum;->ordinal()I

    move-result p2

    aget p2, p3, p2

    packed-switch p2, :pswitch_data_0

    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 230
    :pswitch_0
    sget-object p1, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    new-instance p2, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda9;

    invoke-direct {p2, p0}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda9;-><init>(Lcom/exteragram/messenger/debug/DebugActivity;)V

    invoke-virtual {p1, p2}, Lcom/exteragram/messenger/debug/FreezeMonitor;->deleteReports(Lkotlin/jvm/functions/Function0;)V

    return-void

    .line 229
    :pswitch_1
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p0

    if-eqz p0, :cond_6

    sget-object p1, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    invoke-virtual {p1, p0}, Lcom/exteragram/messenger/debug/FreezeMonitor;->shareReport(Landroid/app/Activity;)V

    return-void

    .line 228
    :pswitch_2
    new-instance p2, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda8;

    invoke-direct {p2}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda8;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 221
    :pswitch_3
    sget-object p1, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    new-instance p2, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda7;

    invoke-direct {p2, p0}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda7;-><init>(Lcom/exteragram/messenger/debug/DebugActivity;)V

    invoke-virtual {p1, p2}, Lcom/exteragram/messenger/debug/LoadMonitor;->deleteReports(Lkotlin/jvm/functions/Function0;)V

    return-void

    .line 220
    :pswitch_4
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p0

    if-eqz p0, :cond_6

    sget-object p1, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-virtual {p1, p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->shareLastReport(Landroid/app/Activity;)V

    return-void

    .line 219
    :pswitch_5
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p0

    if-eqz p0, :cond_6

    sget-object p1, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-virtual {p1, p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->captureAndShare(Landroid/app/Activity;)V

    return-void

    .line 218
    :pswitch_6
    new-instance p2, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda18;

    invoke-direct {p2}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda18;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 211
    :pswitch_7
    sget-object p1, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    new-instance p2, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda17;

    invoke-direct {p2, p0}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda17;-><init>(Lcom/exteragram/messenger/debug/DebugActivity;)V

    invoke-virtual {p1, p2}, Lcom/exteragram/messenger/debug/HeapMonitor;->deleteDumps(Lkotlin/jvm/functions/Function0;)V

    return-void

    .line 210
    :pswitch_8
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p0

    if-eqz p0, :cond_6

    sget-object p1, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    invoke-virtual {p1, p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->shareLastDump(Landroid/app/Activity;)V

    return-void

    .line 209
    :pswitch_9
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p1

    if-eqz p1, :cond_6

    sget-object p2, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-virtual {p2, p1, p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->dumpAndShare(Landroid/app/Activity;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    return-void

    .line 208
    :pswitch_a
    new-instance p2, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda16;

    invoke-direct {p2}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda16;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    :pswitch_b
    const/4 p1, 0x0

    .line 201
    invoke-static {p1}, Lorg/telegram/tgnet/ConnectionsManager;->setDebugDnsConfigOverride(Ljava/lang/String;)V

    .line 202
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p1

    iget-object p1, p1, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    invoke-virtual {p1, p4}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    .line 203
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    .line 204
    sget p1, Lorg/telegram/messenger/R$raw;->contact_check:I

    .line 205
    const-string p2, "ipconfigv3 override cleared."

    .line 203
    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 206
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void

    .line 199
    :pswitch_c
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/DebugActivity;->showIpConfigOverrideDialog()V

    return-void

    .line 160
    :pswitch_d
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object p1

    invoke-virtual {p1}, Lorg/telegram/messenger/MessagesController;->getTranslateController()Lorg/telegram/messenger/TranslateController;

    move-result-object p1

    invoke-virtual {p1}, Lorg/telegram/messenger/TranslateController;->clearTranslationCache()V

    .line 162
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object p1

    iget-object p1, p1, Lorg/telegram/messenger/MessagesController;->dialogMessage:Landroidx/collection/LongSparseArray;

    const/4 p2, 0x0

    if-eqz p1, :cond_3

    .line 164
    invoke-virtual {p1}, Landroidx/collection/LongSparseArray;->size()I

    move-result p3

    move p4, p2

    :goto_0
    if-ge p4, p3, :cond_3

    .line 165
    invoke-virtual {p1, p4}, Landroidx/collection/LongSparseArray;->valueAt(I)Ljava/lang/Object;

    move-result-object p5

    check-cast p5, Ljava/util/ArrayList;

    if-nez p5, :cond_1

    goto :goto_2

    .line 166
    :cond_1
    invoke-virtual {p5}, Ljava/util/ArrayList;->size()I

    move-result v0

    move v1, p2

    :goto_1
    if-ge v1, v0, :cond_2

    .line 167
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object v2

    invoke-virtual {v2}, Lorg/telegram/messenger/MessagesController;->getTranslateController()Lorg/telegram/messenger/TranslateController;

    move-result-object v2

    .line 168
    invoke-virtual {p5, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lorg/telegram/messenger/MessageObject;

    .line 167
    invoke-virtual {v2, v3}, Lorg/telegram/messenger/TranslateController;->clearMessageTranslationState(Lorg/telegram/messenger/MessageObject;)V

    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    :cond_2
    :goto_2
    add-int/lit8 p4, p4, 0x1

    goto :goto_0

    .line 174
    :cond_3
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object p1

    iget-object p1, p1, Lorg/telegram/messenger/MessagesController;->dialogMessagesByIds:Landroid/util/SparseArray;

    if-eqz p1, :cond_4

    .line 25
    invoke-virtual {p1}, Landroid/util/SparseArray;->size()I

    move-result p3

    move p4, p2

    :goto_3
    if-ge p4, p3, :cond_4

    .line 177
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object p5

    invoke-virtual {p5}, Lorg/telegram/messenger/MessagesController;->getTranslateController()Lorg/telegram/messenger/TranslateController;

    move-result-object p5

    .line 178
    invoke-virtual {p1, p4}, Landroid/util/SparseArray;->valueAt(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/messenger/MessageObject;

    .line 177
    invoke-virtual {p5, v0}, Lorg/telegram/messenger/TranslateController;->clearMessageTranslationState(Lorg/telegram/messenger/MessageObject;)V

    add-int/lit8 p4, p4, 0x1

    goto :goto_3

    .line 183
    :cond_4
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object p1

    iget-object p1, p1, Lorg/telegram/messenger/MessagesController;->dialogMessagesByRandomIds:Landroidx/collection/LongSparseArray;

    if-eqz p1, :cond_5

    .line 185
    invoke-virtual {p1}, Landroidx/collection/LongSparseArray;->size()I

    move-result p3

    :goto_4
    if-ge p2, p3, :cond_5

    .line 186
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object p4

    invoke-virtual {p4}, Lorg/telegram/messenger/MessagesController;->getTranslateController()Lorg/telegram/messenger/TranslateController;

    move-result-object p4

    .line 187
    invoke-virtual {p1, p2}, Landroidx/collection/LongSparseArray;->valueAt(I)Ljava/lang/Object;

    move-result-object p5

    check-cast p5, Lorg/telegram/messenger/MessageObject;

    .line 186
    invoke-virtual {p4, p5}, Lorg/telegram/messenger/TranslateController;->clearMessageTranslationState(Lorg/telegram/messenger/MessageObject;)V

    add-int/lit8 p2, p2, 0x1

    goto :goto_4

    .line 192
    :cond_5
    new-instance p1, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda15;

    invoke-direct {p1, p0}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda15;-><init>(Lcom/exteragram/messenger/debug/DebugActivity;)V

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    .line 148
    :pswitch_e
    sget-object p1, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance p2, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda14;

    invoke-direct {p2, p0}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda14;-><init>(Lcom/exteragram/messenger/debug/DebugActivity;)V

    invoke-virtual {p1, p2}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void

    .line 146
    :pswitch_f
    invoke-static {}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->forceRefresh()V

    return-void

    .line 145
    :pswitch_10
    new-instance p2, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda13;

    invoke-direct {p2}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda13;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 144
    :pswitch_11
    new-instance p2, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda12;

    invoke-direct {p2}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda12;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 143
    :pswitch_12
    new-instance p2, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda11;

    invoke-direct {p2}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda11;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 142
    :pswitch_13
    new-instance p2, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda10;

    invoke-direct {p2}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda10;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 141
    :pswitch_14
    new-instance p2, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda6;

    invoke-direct {p2}, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda6;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    :cond_6
    :goto_5
    return-void

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_14
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method
