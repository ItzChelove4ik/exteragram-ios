.class public final Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Companion;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Companion"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\u0008\u0086\u0003\u0018\u00002\u00020\u0001B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0010\u0010\u000e\u001a\u00020\u000c2\u0006\u0010\u000f\u001a\u00020\u000bH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0008\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R*\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000c0\nj\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000c`\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Companion;",
        "",
        "<init>",
        "()V",
        "TRANSLATION_TAG",
        "",
        "MAX_TEXTS_PER_REQUEST",
        "",
        "DETECTION_SAMPLE_LENGTH",
        "instances",
        "Ljava/util/HashMap;",
        "",
        "Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;",
        "Lkotlin/collections/HashMap;",
        "of",
        "pluginId",
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
        "SMAP\nPluginSettingsTranslation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginSettingsTranslation.kt\ncom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Companion\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,243:1\n410#2,7:244\n*S KotlinDebug\n*F\n+ 1 PluginSettingsTranslation.kt\ncom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Companion\n*L\n240#1:244,7\n*E\n"
    }
.end annotation


# direct methods
.method private constructor <init>()V
    .locals 0

    .line 231
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public synthetic constructor <init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Companion;-><init>()V

    return-void
.end method


# virtual methods
.method public final of(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;
    .locals 2
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 240
    invoke-static {}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->access$getInstances$cp()Ljava/util/HashMap;

    move-result-object p0

    .line 410
    invoke-interface {p0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    if-nez v0, :cond_0

    .line 240
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 413
    invoke-interface {p0, p1, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 411
    :cond_0
    check-cast v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    return-object v0
.end method
