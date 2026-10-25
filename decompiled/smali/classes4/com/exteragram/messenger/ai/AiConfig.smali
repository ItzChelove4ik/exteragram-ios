.class public abstract Lcom/exteragram/messenger/ai/AiConfig;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/ai/AiConfig$EntriesMappings;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000R\n\u0002\u0010\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0007\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0002\u0008\u0008\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008,\n\u0002\u0010\u000e\n\u0002\u0008\u000f\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0002\u00a2\u0006\u0004\u0008\u0001\u0010\u0002\u001a\r\u0010\u0003\u001a\u00020\u0000\u00a2\u0006\u0004\u0008\u0003\u0010\u0002\u001a\u0019\u0010\u0007\u001a\u00020\u00062\u0008\u0008\u0002\u0010\u0005\u001a\u00020\u0004H\u0007\u00a2\u0006\u0004\u0008\u0007\u0010\u0008\u001a\u0015\u0010\n\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0006\u00a2\u0006\u0004\u0008\n\u0010\u000b\u001a\u0019\u0010\u000c\u001a\u00020\u00062\u0008\u0008\u0002\u0010\u0005\u001a\u00020\u0004H\u0007\u00a2\u0006\u0004\u0008\u000c\u0010\u0008\u001a\u0015\u0010\r\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0006\u00a2\u0006\u0004\u0008\r\u0010\u000b\u001a\r\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\u0008\u000f\u0010\u0010\u001a\u0015\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u000e\u00a2\u0006\u0004\u0008\u0012\u0010\u0013\u001a\r\u0010\u0014\u001a\u00020\u0000\u00a2\u0006\u0004\u0008\u0014\u0010\u0002\u001a\u001d\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0015j\u0008\u0012\u0004\u0012\u00020\u000e`\u0016\u00a2\u0006\u0004\u0008\u0017\u0010\u0018\u001a%\u0010\u001a\u001a\u00020\u00002\u0016\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0015j\u0008\u0012\u0004\u0012\u00020\u000e`\u0016\u00a2\u0006\u0004\u0008\u001a\u0010\u001b\u001a%\u0010\u001e\u001a\u00020\u00002\u0016\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u001c0\u0015j\u0008\u0012\u0004\u0012\u00020\u001c`\u0016\u00a2\u0006\u0004\u0008\u001e\u0010\u001b\u001a\u001d\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\u001c0\u0015j\u0008\u0012\u0004\u0012\u00020\u001c`\u0016\u00a2\u0006\u0004\u0008\u001f\u0010\u0018\u001a\u0015\u0010!\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u001c\u00a2\u0006\u0004\u0008!\u0010\"\u001a\u001d\u0010$\u001a\u0012\u0012\u0004\u0012\u00020#0\u0015j\u0008\u0012\u0004\u0012\u00020#`\u0016\u00a2\u0006\u0004\u0008$\u0010\u0018\u001a%\u0010&\u001a\u00020\u00002\u0016\u0010%\u001a\u0012\u0012\u0004\u0012\u00020#0\u0015j\u0008\u0012\u0004\u0012\u00020#`\u0016\u00a2\u0006\u0004\u0008&\u0010\u001b\u001a\r\u0010\'\u001a\u00020\u0000\u00a2\u0006\u0004\u0008\'\u0010\u0002\u001a\r\u0010(\u001a\u00020\u0000\u00a2\u0006\u0004\u0008(\u0010\u0002\"\u0014\u0010)\u001a\u00020\u000e8\u0006X\u0087\u0004\u00a2\u0006\u0006\n\u0004\u0008)\u0010*\"\u0014\u0010+\u001a\u00020\u000e8\u0006X\u0087\u0004\u00a2\u0006\u0006\n\u0004\u0008+\u0010*\"\u0017\u0010-\u001a\u00020,8\u0006\u00a2\u0006\u000c\n\u0004\u0008-\u0010.\u001a\u0004\u0008/\u00100\"\u001b\u00106\u001a\u0002018FX\u0086\u0084\u0002\u00a2\u0006\u000c\n\u0004\u00082\u00103\u001a\u0004\u00084\u00105\"\u0014\u00107\u001a\u00020\u00008\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\u00087\u00108\"+\u0010?\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00068F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\u0008:\u0010;\u001a\u0004\u0008<\u0010=\"\u0004\u0008>\u0010\u000b\"+\u0010C\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00068F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\u0008@\u0010;\u001a\u0004\u0008A\u0010=\"\u0004\u0008B\u0010\u000b\"+\u0010I\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u00048F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\u0008D\u0010;\u001a\u0004\u0008E\u0010F\"\u0004\u0008G\u0010H\"+\u0010M\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00068F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\u0008J\u0010;\u001a\u0004\u0008K\u0010=\"\u0004\u0008L\u0010\u000b\"+\u0010Q\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00068F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\u0008N\u0010;\u001a\u0004\u0008O\u0010=\"\u0004\u0008P\u0010\u000b\"+\u0010U\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00068F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\u0008R\u0010;\u001a\u0004\u0008S\u0010=\"\u0004\u0008T\u0010\u000b\"+\u0010Y\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00068F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\u0008V\u0010;\u001a\u0004\u0008W\u0010=\"\u0004\u0008X\u0010\u000b\"+\u0010]\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00068F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\u0008Z\u0010;\u001a\u0004\u0008[\u0010=\"\u0004\u0008\\\u0010\u000b\"/\u0010d\u001a\u0004\u0018\u00010^2\u0008\u00109\u001a\u0004\u0018\u00010^8F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\u0008_\u0010;\u001a\u0004\u0008`\u0010a\"\u0004\u0008b\u0010c\"+\u0010h\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u00048B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\u0008e\u0010;\u001a\u0004\u0008f\u0010F\"\u0004\u0008g\u0010H\"+\u0010l\u001a\u00020^2\u0006\u00109\u001a\u00020^8F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\u0008i\u0010;\u001a\u0004\u0008j\u0010a\"\u0004\u0008k\u0010c\u00a8\u0006m"
    }
    d2 = {
        "",
        "migrateLegacyConfig",
        "()V",
        "ensureConfigMigrated",
        "",
        "account",
        "",
        "getReplaceTelegramEditor",
        "(I)Z",
        "value",
        "setReplaceTelegramEditor",
        "(Z)V",
        "getReplaceTelegramSummaries",
        "setReplaceTelegramSummaries",
        "Lcom/exteragram/messenger/ai/data/Service;",
        "getSelectedService",
        "()Lcom/exteragram/messenger/ai/data/Service;",
        "service",
        "setSelectedServices",
        "(Lcom/exteragram/messenger/ai/data/Service;)V",
        "clearSelectedService",
        "Ljava/util/ArrayList;",
        "Lkotlin/collections/ArrayList;",
        "getServices",
        "()Ljava/util/ArrayList;",
        "services",
        "saveServices",
        "(Ljava/util/ArrayList;)V",
        "Lcom/exteragram/messenger/ai/data/Role;",
        "roles",
        "saveRoles",
        "getRoles",
        "role",
        "setSelectedAiRole",
        "(Lcom/exteragram/messenger/ai/data/Role;)V",
        "Lcom/exteragram/messenger/ai/data/Message;",
        "getConversationHistory",
        "history",
        "saveConversationHistory",
        "clearConversationHistory",
        "removeLastFromHistory",
        "DEFAULT_SERVICE",
        "Lcom/exteragram/messenger/ai/data/Service;",
        "ON_DEVICE_SERVICE",
        "Landroid/content/SharedPreferences;",
        "preferences",
        "Landroid/content/SharedPreferences;",
        "getPreferences",
        "()Landroid/content/SharedPreferences;",
        "Landroid/content/SharedPreferences$Editor;",
        "editor$delegate",
        "Lkotlin/Lazy;",
        "getEditor",
        "()Landroid/content/SharedPreferences$Editor;",
        "editor",
        "legacyConfigMigrated",
        "Lkotlin/Unit;",
        "<set-?>",
        "saveHistory$delegate",
        "Lcom/exteragram/messenger/config/BasePref;",
        "getSaveHistory",
        "()Z",
        "setSaveHistory",
        "saveHistory",
        "responseStreaming$delegate",
        "getResponseStreaming",
        "setResponseStreaming",
        "responseStreaming",
        "temperature$delegate",
        "getTemperature",
        "()I",
        "setTemperature",
        "(I)V",
        "temperature",
        "showResponseOnly$delegate",
        "getShowResponseOnly",
        "setShowResponseOnly",
        "showResponseOnly",
        "insertAsQuote$delegate",
        "getInsertAsQuote",
        "setInsertAsQuote",
        "insertAsQuote",
        "onDeviceReasoning$delegate",
        "getOnDeviceReasoning",
        "setOnDeviceReasoning",
        "onDeviceReasoning",
        "onDevicePreviewModel$delegate",
        "getOnDevicePreviewModel",
        "setOnDevicePreviewModel",
        "onDevicePreviewModel",
        "onDeviceFastModel$delegate",
        "getOnDeviceFastModel",
        "setOnDeviceFastModel",
        "onDeviceFastModel",
        "",
        "selectedServiceId$delegate",
        "getSelectedServiceId",
        "()Ljava/lang/String;",
        "setSelectedServiceId",
        "(Ljava/lang/String;)V",
        "selectedServiceId",
        "selectedServiceHash$delegate",
        "getSelectedServiceHash",
        "setSelectedServiceHash",
        "selectedServiceHash",
        "selectedRole$delegate",
        "getSelectedRole",
        "setSelectedRole",
        "selectedRole",
        "TMessagesProj"
    }
    k = 0x2
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation

.annotation build Lkotlin/jvm/JvmName;
    name = "AiConfig"
.end annotation

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nAiConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AiConfig.kt\ncom/exteragram/messenger/ai/AiConfig\n+ 2 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,217:1\n41#2,6:218\n47#2,6:226\n1915#3,2:224\n1915#3,2:233\n1#4:232\n*S KotlinDebug\n*F\n+ 1 AiConfig.kt\ncom/exteragram/messenger/ai/AiConfig\n*L\n65#1:218,6\n65#1:226,6\n66#1:224,2\n149#1:233,2\n*E\n"
    }
.end annotation


# static fields
.field static final synthetic $$delegatedProperties:[Lkotlin/reflect/KProperty;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "[",
            "Lkotlin/reflect/KProperty<",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field

.field public static final DEFAULT_SERVICE:Lcom/exteragram/messenger/ai/data/Service;
    .annotation build Lkotlin/jvm/JvmField;
    .end annotation
.end field

.field public static final ON_DEVICE_SERVICE:Lcom/exteragram/messenger/ai/data/Service;
    .annotation build Lkotlin/jvm/JvmField;
    .end annotation
.end field

.field private static final editor$delegate:Lkotlin/Lazy;

.field private static final insertAsQuote$delegate:Lcom/exteragram/messenger/config/BasePref;

.field private static final legacyConfigMigrated:Lkotlin/Unit;

.field private static final onDeviceFastModel$delegate:Lcom/exteragram/messenger/config/BasePref;

.field private static final onDevicePreviewModel$delegate:Lcom/exteragram/messenger/config/BasePref;

.field private static final onDeviceReasoning$delegate:Lcom/exteragram/messenger/config/BasePref;

.field private static final preferences:Landroid/content/SharedPreferences;

.field private static final responseStreaming$delegate:Lcom/exteragram/messenger/config/BasePref;

.field private static final saveHistory$delegate:Lcom/exteragram/messenger/config/BasePref;

.field private static final selectedRole$delegate:Lcom/exteragram/messenger/config/BasePref;

.field private static final selectedServiceHash$delegate:Lcom/exteragram/messenger/config/BasePref;

.field private static final selectedServiceId$delegate:Lcom/exteragram/messenger/config/BasePref;

.field private static final showResponseOnly$delegate:Lcom/exteragram/messenger/config/BasePref;

.field private static final temperature$delegate:Lcom/exteragram/messenger/config/BasePref;


# direct methods
.method public static $r8$lambda$PZg0RSpjfILGqc_v0Q8lJ-F_FU8()Landroid/content/SharedPreferences$Editor;
    .locals 1

    .line 45
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->preferences:Landroid/content/SharedPreferences;

    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 18

    new-instance v0, Lkotlin/jvm/internal/MutablePropertyReference0Impl;

    const-class v1, Lcom/exteragram/messenger/ai/AiConfig;

    const-string v2, "saveHistory"

    const-string v3, "getSaveHistory()Z"

    const/4 v4, 0x1

    invoke-direct {v0, v1, v2, v3, v4}, Lkotlin/jvm/internal/MutablePropertyReference0Impl;-><init>(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V

    invoke-static {v0}, Lkotlin/jvm/internal/Reflection;->mutableProperty0(Lkotlin/jvm/internal/MutablePropertyReference0;)Lkotlin/reflect/KMutableProperty0;

    move-result-object v0

    new-instance v2, Lkotlin/jvm/internal/MutablePropertyReference0Impl;

    const-string v3, "responseStreaming"

    const-string v5, "getResponseStreaming()Z"

    invoke-direct {v2, v1, v3, v5, v4}, Lkotlin/jvm/internal/MutablePropertyReference0Impl;-><init>(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V

    invoke-static {v2}, Lkotlin/jvm/internal/Reflection;->mutableProperty0(Lkotlin/jvm/internal/MutablePropertyReference0;)Lkotlin/reflect/KMutableProperty0;

    move-result-object v2

    new-instance v3, Lkotlin/jvm/internal/MutablePropertyReference0Impl;

    const-string v5, "temperature"

    const-string v6, "getTemperature()I"

    invoke-direct {v3, v1, v5, v6, v4}, Lkotlin/jvm/internal/MutablePropertyReference0Impl;-><init>(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V

    invoke-static {v3}, Lkotlin/jvm/internal/Reflection;->mutableProperty0(Lkotlin/jvm/internal/MutablePropertyReference0;)Lkotlin/reflect/KMutableProperty0;

    move-result-object v3

    new-instance v5, Lkotlin/jvm/internal/MutablePropertyReference0Impl;

    const-string v6, "showResponseOnly"

    const-string v7, "getShowResponseOnly()Z"

    invoke-direct {v5, v1, v6, v7, v4}, Lkotlin/jvm/internal/MutablePropertyReference0Impl;-><init>(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V

    invoke-static {v5}, Lkotlin/jvm/internal/Reflection;->mutableProperty0(Lkotlin/jvm/internal/MutablePropertyReference0;)Lkotlin/reflect/KMutableProperty0;

    move-result-object v5

    new-instance v6, Lkotlin/jvm/internal/MutablePropertyReference0Impl;

    const-string v7, "insertAsQuote"

    const-string v8, "getInsertAsQuote()Z"

    invoke-direct {v6, v1, v7, v8, v4}, Lkotlin/jvm/internal/MutablePropertyReference0Impl;-><init>(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V

    invoke-static {v6}, Lkotlin/jvm/internal/Reflection;->mutableProperty0(Lkotlin/jvm/internal/MutablePropertyReference0;)Lkotlin/reflect/KMutableProperty0;

    move-result-object v6

    new-instance v7, Lkotlin/jvm/internal/MutablePropertyReference0Impl;

    const-string v8, "onDeviceReasoning"

    const-string v9, "getOnDeviceReasoning()Z"

    invoke-direct {v7, v1, v8, v9, v4}, Lkotlin/jvm/internal/MutablePropertyReference0Impl;-><init>(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V

    invoke-static {v7}, Lkotlin/jvm/internal/Reflection;->mutableProperty0(Lkotlin/jvm/internal/MutablePropertyReference0;)Lkotlin/reflect/KMutableProperty0;

    move-result-object v7

    new-instance v8, Lkotlin/jvm/internal/MutablePropertyReference0Impl;

    const-string v9, "onDevicePreviewModel"

    const-string v10, "getOnDevicePreviewModel()Z"

    invoke-direct {v8, v1, v9, v10, v4}, Lkotlin/jvm/internal/MutablePropertyReference0Impl;-><init>(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V

    invoke-static {v8}, Lkotlin/jvm/internal/Reflection;->mutableProperty0(Lkotlin/jvm/internal/MutablePropertyReference0;)Lkotlin/reflect/KMutableProperty0;

    move-result-object v8

    new-instance v9, Lkotlin/jvm/internal/MutablePropertyReference0Impl;

    const-string v10, "onDeviceFastModel"

    const-string v11, "getOnDeviceFastModel()Z"

    invoke-direct {v9, v1, v10, v11, v4}, Lkotlin/jvm/internal/MutablePropertyReference0Impl;-><init>(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V

    invoke-static {v9}, Lkotlin/jvm/internal/Reflection;->mutableProperty0(Lkotlin/jvm/internal/MutablePropertyReference0;)Lkotlin/reflect/KMutableProperty0;

    move-result-object v9

    new-instance v10, Lkotlin/jvm/internal/MutablePropertyReference0Impl;

    const-string v11, "getSelectedServiceId()Ljava/lang/String;"

    const-string v12, "selectedServiceId"

    invoke-direct {v10, v1, v12, v11, v4}, Lkotlin/jvm/internal/MutablePropertyReference0Impl;-><init>(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V

    invoke-static {v10}, Lkotlin/jvm/internal/Reflection;->mutableProperty0(Lkotlin/jvm/internal/MutablePropertyReference0;)Lkotlin/reflect/KMutableProperty0;

    move-result-object v10

    new-instance v11, Lkotlin/jvm/internal/MutablePropertyReference0Impl;

    const-string v13, "selectedServiceHash"

    const-string v14, "getSelectedServiceHash()I"

    invoke-direct {v11, v1, v13, v14, v4}, Lkotlin/jvm/internal/MutablePropertyReference0Impl;-><init>(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V

    invoke-static {v11}, Lkotlin/jvm/internal/Reflection;->mutableProperty0(Lkotlin/jvm/internal/MutablePropertyReference0;)Lkotlin/reflect/KMutableProperty0;

    move-result-object v11

    new-instance v13, Lkotlin/jvm/internal/MutablePropertyReference0Impl;

    const-string v14, "selectedRole"

    const-string v15, "getSelectedRole()Ljava/lang/String;"

    invoke-direct {v13, v1, v14, v15, v4}, Lkotlin/jvm/internal/MutablePropertyReference0Impl;-><init>(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V

    invoke-static {v13}, Lkotlin/jvm/internal/Reflection;->mutableProperty0(Lkotlin/jvm/internal/MutablePropertyReference0;)Lkotlin/reflect/KMutableProperty0;

    move-result-object v1

    const/16 v13, 0xb

    new-array v13, v13, [Lkotlin/reflect/KProperty;

    const/4 v14, 0x0

    aput-object v0, v13, v14

    aput-object v2, v13, v4

    const/4 v0, 0x2

    aput-object v3, v13, v0

    const/4 v2, 0x3

    aput-object v5, v13, v2

    const/4 v3, 0x4

    aput-object v6, v13, v3

    const/4 v5, 0x5

    aput-object v7, v13, v5

    const/4 v6, 0x6

    aput-object v8, v13, v6

    const/4 v7, 0x7

    aput-object v9, v13, v7

    const/16 v8, 0x8

    aput-object v10, v13, v8

    const/16 v9, 0x9

    aput-object v11, v13, v9

    const/16 v10, 0xa

    aput-object v1, v13, v10

    sput-object v13, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    new-instance v1, Lcom/exteragram/messenger/ai/data/Service;

    const-string v11, "default"

    const-string v15, "https://generativelanguage.googleapis.com/v1beta"

    move/from16 v16, v2

    const-string v2, "gemini-3.5-flash"

    move/from16 v17, v3

    const/4 v3, 0x0

    invoke-direct {v1, v11, v15, v2, v3}, Lcom/exteragram/messenger/ai/data/Service;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    sput-object v1, Lcom/exteragram/messenger/ai/AiConfig;->DEFAULT_SERVICE:Lcom/exteragram/messenger/ai/data/Service;

    new-instance v2, Lcom/exteragram/messenger/ai/data/Service;

    const-string v11, "on-device"

    const-string v15, "Gemini Nano"

    invoke-direct {v2, v11, v3, v15, v3}, Lcom/exteragram/messenger/ai/data/Service;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    .line 37
    sget-object v11, Lcom/exteragram/messenger/ai/data/Service$Kind;->ON_DEVICE:Lcom/exteragram/messenger/ai/data/Service$Kind;

    invoke-virtual {v2, v11}, Lcom/exteragram/messenger/ai/data/Service;->setKind(Lcom/exteragram/messenger/ai/data/Service$Kind;)V

    sput-object v2, Lcom/exteragram/messenger/ai/AiConfig;->ON_DEVICE_SERVICE:Lcom/exteragram/messenger/ai/data/Service;

    const-string v2, "aiConfig"

    invoke-static {v2}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getPreferences(Ljava/lang/String;)Landroid/content/SharedPreferences;

    move-result-object v2

    sput-object v2, Lcom/exteragram/messenger/ai/AiConfig;->preferences:Landroid/content/SharedPreferences;

    new-instance v2, Lcom/exteragram/messenger/ai/AiConfig$$ExternalSyntheticLambda0;

    invoke-direct {v2}, Lcom/exteragram/messenger/ai/AiConfig$$ExternalSyntheticLambda0;-><init>()V

    invoke-static {v2}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object v2

    sput-object v2, Lcom/exteragram/messenger/ai/AiConfig;->editor$delegate:Lkotlin/Lazy;

    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->migrateLegacyConfig()V

    sget-object v2, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    sput-object v2, Lcom/exteragram/messenger/ai/AiConfig;->legacyConfigMigrated:Lkotlin/Unit;

    new-instance v2, Lcom/exteragram/messenger/config/BooleanPref;

    invoke-direct {v2, v4, v3, v0, v3}, Lcom/exteragram/messenger/config/BooleanPref;-><init>(ZLjava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    aget-object v11, v13, v14

    invoke-virtual {v2, v3, v11}, Lcom/exteragram/messenger/config/BasePref;->provideDelegate(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Lcom/exteragram/messenger/config/BasePref;

    move-result-object v2

    sput-object v2, Lcom/exteragram/messenger/ai/AiConfig;->saveHistory$delegate:Lcom/exteragram/messenger/config/BasePref;

    new-instance v2, Lcom/exteragram/messenger/config/BooleanPref;

    invoke-direct {v2, v4, v3, v0, v3}, Lcom/exteragram/messenger/config/BooleanPref;-><init>(ZLjava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    aget-object v11, v13, v4

    invoke-virtual {v2, v3, v11}, Lcom/exteragram/messenger/config/BasePref;->provideDelegate(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Lcom/exteragram/messenger/config/BasePref;

    move-result-object v2

    sput-object v2, Lcom/exteragram/messenger/ai/AiConfig;->responseStreaming$delegate:Lcom/exteragram/messenger/config/BasePref;

    new-instance v2, Lcom/exteragram/messenger/config/IntegerPref;

    invoke-direct {v2, v10, v3, v0, v3}, Lcom/exteragram/messenger/config/IntegerPref;-><init>(ILjava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    aget-object v11, v13, v0

    invoke-virtual {v2, v3, v11}, Lcom/exteragram/messenger/config/BasePref;->provideDelegate(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Lcom/exteragram/messenger/config/BasePref;

    move-result-object v2

    sput-object v2, Lcom/exteragram/messenger/ai/AiConfig;->temperature$delegate:Lcom/exteragram/messenger/config/BasePref;

    new-instance v2, Lcom/exteragram/messenger/config/BooleanPref;

    invoke-direct {v2, v14, v3, v0, v3}, Lcom/exteragram/messenger/config/BooleanPref;-><init>(ZLjava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    aget-object v11, v13, v16

    invoke-virtual {v2, v3, v11}, Lcom/exteragram/messenger/config/BasePref;->provideDelegate(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Lcom/exteragram/messenger/config/BasePref;

    move-result-object v2

    sput-object v2, Lcom/exteragram/messenger/ai/AiConfig;->showResponseOnly$delegate:Lcom/exteragram/messenger/config/BasePref;

    new-instance v2, Lcom/exteragram/messenger/config/BooleanPref;

    invoke-direct {v2, v4, v3, v0, v3}, Lcom/exteragram/messenger/config/BooleanPref;-><init>(ZLjava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    aget-object v4, v13, v17

    invoke-virtual {v2, v3, v4}, Lcom/exteragram/messenger/config/BasePref;->provideDelegate(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Lcom/exteragram/messenger/config/BasePref;

    move-result-object v2

    sput-object v2, Lcom/exteragram/messenger/ai/AiConfig;->insertAsQuote$delegate:Lcom/exteragram/messenger/config/BasePref;

    new-instance v2, Lcom/exteragram/messenger/config/BooleanPref;

    invoke-direct {v2, v14, v3, v0, v3}, Lcom/exteragram/messenger/config/BooleanPref;-><init>(ZLjava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    aget-object v4, v13, v5

    invoke-virtual {v2, v3, v4}, Lcom/exteragram/messenger/config/BasePref;->provideDelegate(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Lcom/exteragram/messenger/config/BasePref;

    move-result-object v2

    sput-object v2, Lcom/exteragram/messenger/ai/AiConfig;->onDeviceReasoning$delegate:Lcom/exteragram/messenger/config/BasePref;

    new-instance v2, Lcom/exteragram/messenger/config/BooleanPref;

    invoke-direct {v2, v14, v3, v0, v3}, Lcom/exteragram/messenger/config/BooleanPref;-><init>(ZLjava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    aget-object v4, v13, v6

    invoke-virtual {v2, v3, v4}, Lcom/exteragram/messenger/config/BasePref;->provideDelegate(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Lcom/exteragram/messenger/config/BasePref;

    move-result-object v2

    sput-object v2, Lcom/exteragram/messenger/ai/AiConfig;->onDevicePreviewModel$delegate:Lcom/exteragram/messenger/config/BasePref;

    new-instance v2, Lcom/exteragram/messenger/config/BooleanPref;

    invoke-direct {v2, v14, v3, v0, v3}, Lcom/exteragram/messenger/config/BooleanPref;-><init>(ZLjava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    aget-object v4, v13, v7

    invoke-virtual {v2, v3, v4}, Lcom/exteragram/messenger/config/BasePref;->provideDelegate(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Lcom/exteragram/messenger/config/BasePref;

    move-result-object v2

    sput-object v2, Lcom/exteragram/messenger/ai/AiConfig;->onDeviceFastModel$delegate:Lcom/exteragram/messenger/config/BasePref;

    new-instance v2, Lcom/exteragram/messenger/config/NullableStringPref;

    invoke-direct {v2, v3, v12}, Lcom/exteragram/messenger/config/NullableStringPref;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    aget-object v4, v13, v8

    invoke-virtual {v2, v3, v4}, Lcom/exteragram/messenger/config/BasePref;->provideDelegate(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Lcom/exteragram/messenger/config/BasePref;

    move-result-object v2

    sput-object v2, Lcom/exteragram/messenger/ai/AiConfig;->selectedServiceId$delegate:Lcom/exteragram/messenger/config/BasePref;

    new-instance v2, Lcom/exteragram/messenger/config/IntegerPref;

    invoke-virtual {v1}, Lcom/exteragram/messenger/ai/data/Service;->getLegacyHash()I

    move-result v1

    const-string v4, "selectedService"

    invoke-direct {v2, v1, v4}, Lcom/exteragram/messenger/config/IntegerPref;-><init>(ILjava/lang/String;)V

    aget-object v1, v13, v9

    invoke-virtual {v2, v3, v1}, Lcom/exteragram/messenger/config/BasePref;->provideDelegate(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Lcom/exteragram/messenger/config/BasePref;

    move-result-object v1

    sput-object v1, Lcom/exteragram/messenger/ai/AiConfig;->selectedServiceHash$delegate:Lcom/exteragram/messenger/config/BasePref;

    new-instance v1, Lcom/exteragram/messenger/config/StringPref;

    sget-object v2, Lcom/exteragram/messenger/ai/AiConfig$EntriesMappings;->entries$0:Lkotlin/enums/EnumEntries;

    invoke-interface {v2, v14}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/exteragram/messenger/ai/data/Suggestions;

    invoke-virtual {v2}, Lcom/exteragram/messenger/ai/data/Suggestions;->getRole()Lcom/exteragram/messenger/ai/data/Role;

    move-result-object v2

    invoke-virtual {v2}, Lcom/exteragram/messenger/ai/data/Role;->getName()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v1, v2, v3, v0, v3}, Lcom/exteragram/messenger/config/StringPref;-><init>(Ljava/lang/String;Ljava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    aget-object v0, v13, v10

    invoke-virtual {v1, v3, v0}, Lcom/exteragram/messenger/config/BasePref;->provideDelegate(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Lcom/exteragram/messenger/config/BasePref;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/ai/AiConfig;->selectedRole$delegate:Lcom/exteragram/messenger/config/BasePref;

    return-void
.end method

.method public static final clearConversationHistory()V
    .locals 2

    .line 207
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "history"

    invoke-interface {v0, v1}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method public static final clearSelectedService()V
    .locals 2

    const/4 v0, 0x0

    .line 139
    invoke-static {v0}, Lcom/exteragram/messenger/ai/AiConfig;->setSelectedServiceId(Ljava/lang/String;)V

    .line 140
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "selectedService"

    invoke-interface {v0, v1}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method public static final ensureConfigMigrated()V
    .locals 0

    return-void
.end method

.method public static final getConversationHistory()Ljava/util/ArrayList;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/ai/data/Message;",
            ">;"
        }
    .end annotation

    .line 192
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->preferences:Landroid/content/SharedPreferences;

    const-string v1, "history"

    const/4 v2, 0x0

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_0

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    return-object v0

    .line 194
    :cond_0
    :try_start_0
    new-instance v1, Lcom/exteragram/messenger/ai/AiConfig$getConversationHistory$type$1;

    invoke-direct {v1}, Lcom/exteragram/messenger/ai/AiConfig$getConversationHistory$type$1;-><init>()V

    invoke-virtual {v1}, Lcom/google/gson/reflect/TypeToken;->getType()Ljava/lang/reflect/Type;

    move-result-object v1

    .line 195
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getGSON()Lcom/google/gson/Gson;

    move-result-object v2

    invoke-virtual {v2, v0, v1}, Lcom/google/gson/Gson;->fromJson(Ljava/lang/String;Ljava/lang/reflect/Type;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/ArrayList;

    if-nez v0, :cond_1

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :cond_1
    return-object v0

    :catch_0
    move-exception v0

    .line 197
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 198
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    return-object v0
.end method

.method public static final getEditor()Landroid/content/SharedPreferences$Editor;
    .locals 1

    .line 45
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->editor$delegate:Lkotlin/Lazy;

    invoke-interface {v0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/content/SharedPreferences$Editor;

    return-object v0
.end method

.method public static final getInsertAsQuote()Z
    .locals 3

    .line 87
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->insertAsQuote$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x4

    aget-object v1, v1, v2

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1}, Lcom/exteragram/messenger/config/BasePref;->getValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    return v0
.end method

.method public static final getOnDeviceFastModel()Z
    .locals 3

    .line 90
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->onDeviceFastModel$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x7

    aget-object v1, v1, v2

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1}, Lcom/exteragram/messenger/config/BasePref;->getValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    return v0
.end method

.method public static final getOnDevicePreviewModel()Z
    .locals 3

    .line 89
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->onDevicePreviewModel$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x6

    aget-object v1, v1, v2

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1}, Lcom/exteragram/messenger/config/BasePref;->getValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    return v0
.end method

.method public static final getOnDeviceReasoning()Z
    .locals 3

    .line 88
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->onDeviceReasoning$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x5

    aget-object v1, v1, v2

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1}, Lcom/exteragram/messenger/config/BasePref;->getValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    return v0
.end method

.method public static final getPreferences()Landroid/content/SharedPreferences;
    .locals 1

    .line 44
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->preferences:Landroid/content/SharedPreferences;

    return-object v0
.end method

.method public static final getReplaceTelegramEditor(I)Z
    .locals 2
    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    .line 94
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v0

    invoke-static {p0}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/messenger/UserConfig;->isPremium()Z

    move-result p0

    xor-int/lit8 p0, p0, 0x1

    const-string v1, "replaceTelegramEditor"

    invoke-interface {v0, v1, p0}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    return p0
.end method

.method public static synthetic getReplaceTelegramEditor$default(IILjava/lang/Object;)Z
    .locals 0

    and-int/lit8 p1, p1, 0x1

    if-eqz p1, :cond_0

    .line 93
    sget p0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    .line 92
    :cond_0
    invoke-static {p0}, Lcom/exteragram/messenger/ai/AiConfig;->getReplaceTelegramEditor(I)Z

    move-result p0

    return p0
.end method

.method public static final getReplaceTelegramSummaries(I)Z
    .locals 2
    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    .line 100
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v0

    invoke-static {p0}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/messenger/UserConfig;->isPremium()Z

    move-result p0

    xor-int/lit8 p0, p0, 0x1

    const-string v1, "replaceTelegramSummaries"

    invoke-interface {v0, v1, p0}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    return p0
.end method

.method public static synthetic getReplaceTelegramSummaries$default(IILjava/lang/Object;)Z
    .locals 0

    and-int/lit8 p1, p1, 0x1

    if-eqz p1, :cond_0

    .line 99
    sget p0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    .line 98
    :cond_0
    invoke-static {p0}, Lcom/exteragram/messenger/ai/AiConfig;->getReplaceTelegramSummaries(I)Z

    move-result p0

    return p0
.end method

.method public static final getResponseStreaming()Z
    .locals 3

    .line 84
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->responseStreaming$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x1

    aget-object v1, v1, v2

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1}, Lcom/exteragram/messenger/config/BasePref;->getValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    return v0
.end method

.method public static final getRoles()Ljava/util/ArrayList;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/ai/data/Role;",
            ">;"
        }
    .end annotation

    .line 173
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->preferences:Landroid/content/SharedPreferences;

    const-string v1, "roles"

    const/4 v2, 0x0

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_0

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    return-object v0

    .line 175
    :cond_0
    :try_start_0
    new-instance v1, Lcom/exteragram/messenger/ai/AiConfig$getRoles$type$1;

    invoke-direct {v1}, Lcom/exteragram/messenger/ai/AiConfig$getRoles$type$1;-><init>()V

    invoke-virtual {v1}, Lcom/google/gson/reflect/TypeToken;->getType()Ljava/lang/reflect/Type;

    move-result-object v1

    .line 176
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getGSON()Lcom/google/gson/Gson;

    move-result-object v2

    invoke-virtual {v2, v0, v1}, Lcom/google/gson/Gson;->fromJson(Ljava/lang/String;Ljava/lang/reflect/Type;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/ArrayList;

    if-nez v0, :cond_1

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :cond_1
    return-object v0

    :catch_0
    move-exception v0

    .line 178
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 179
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    return-object v0
.end method

.method public static final getSaveHistory()Z
    .locals 3

    .line 83
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->saveHistory$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x0

    aget-object v1, v1, v2

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1}, Lcom/exteragram/messenger/config/BasePref;->getValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    return v0
.end method

.method public static final getSelectedRole()Ljava/lang/String;
    .locals 3

    .line 106
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->selectedRole$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/16 v2, 0xa

    aget-object v1, v1, v2

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1}, Lcom/exteragram/messenger/config/BasePref;->getValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    return-object v0
.end method

.method public static final getSelectedService()Lcom/exteragram/messenger/ai/data/Service;
    .locals 8

    .line 109
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getServices()Ljava/util/ArrayList;

    move-result-object v0

    .line 110
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getSelectedServiceId()Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    const/4 v3, 0x0

    if-eqz v1, :cond_4

    .line 111
    invoke-interface {v1}, Ljava/lang/CharSequence;->length()I

    move-result v4

    if-nez v4, :cond_0

    goto :goto_1

    .line 112
    :cond_0
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v4

    move v5, v2

    :cond_1
    if-ge v5, v4, :cond_2

    invoke-virtual {v0, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    add-int/lit8 v5, v5, 0x1

    move-object v7, v6

    check-cast v7, Lcom/exteragram/messenger/ai/data/Service;

    invoke-virtual {v7}, Lcom/exteragram/messenger/ai/data/Service;->getId()Ljava/lang/String;

    move-result-object v7

    invoke-static {v7, v1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v7

    if-eqz v7, :cond_1

    goto :goto_0

    :cond_2
    move-object v6, v3

    :goto_0
    check-cast v6, Lcom/exteragram/messenger/ai/data/Service;

    if-eqz v6, :cond_3

    return-object v6

    .line 116
    :cond_3
    invoke-static {v3}, Lcom/exteragram/messenger/ai/AiConfig;->setSelectedServiceId(Ljava/lang/String;)V

    .line 119
    :cond_4
    :goto_1
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v1

    const-string v4, "selectedService"

    invoke-interface {v1, v4}, Landroid/content/SharedPreferences;->contains(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_8

    .line 120
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getSelectedServiceHash()I

    move-result v1

    .line 121
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v5

    :cond_5
    if-ge v2, v5, :cond_6

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    add-int/lit8 v2, v2, 0x1

    move-object v7, v6

    check-cast v7, Lcom/exteragram/messenger/ai/data/Service;

    invoke-virtual {v7}, Lcom/exteragram/messenger/ai/data/Service;->getLegacyHash()I

    move-result v7

    if-ne v7, v1, :cond_5

    move-object v3, v6

    :cond_6
    check-cast v3, Lcom/exteragram/messenger/ai/data/Service;

    if-eqz v3, :cond_7

    .line 123
    invoke-virtual {v3}, Lcom/exteragram/messenger/ai/data/Service;->getId()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/exteragram/messenger/ai/AiConfig;->setSelectedServiceId(Ljava/lang/String;)V

    .line 124
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-interface {v0, v4}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-object v3

    .line 127
    :cond_7
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object v1

    invoke-interface {v1, v4}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v1

    invoke-interface {v1}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 130
    :cond_8
    invoke-static {v0}, Lkotlin/collections/CollectionsKt;->firstOrNull(Ljava/util/List;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/ai/data/Service;

    if-nez v0, :cond_9

    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->DEFAULT_SERVICE:Lcom/exteragram/messenger/ai/data/Service;

    :cond_9
    return-object v0
.end method

.method private static final getSelectedServiceHash()I
    .locals 3

    .line 105
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->selectedServiceHash$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/16 v2, 0x9

    aget-object v1, v1, v2

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1}, Lcom/exteragram/messenger/config/BasePref;->getValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Number;

    invoke-virtual {v0}, Ljava/lang/Number;->intValue()I

    move-result v0

    return v0
.end method

.method public static final getSelectedServiceId()Ljava/lang/String;
    .locals 3

    .line 104
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->selectedServiceId$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/16 v2, 0x8

    aget-object v1, v1, v2

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1}, Lcom/exteragram/messenger/config/BasePref;->getValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    return-object v0
.end method

.method public static final getServices()Ljava/util/ArrayList;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/ai/data/Service;",
            ">;"
        }
    .end annotation

    .line 144
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->preferences:Landroid/content/SharedPreferences;

    const-string v1, "services"

    const/4 v2, 0x0

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_0

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    return-object v0

    .line 146
    :cond_0
    :try_start_0
    new-instance v1, Lcom/exteragram/messenger/ai/AiConfig$getServices$type$1;

    invoke-direct {v1}, Lcom/exteragram/messenger/ai/AiConfig$getServices$type$1;-><init>()V

    invoke-virtual {v1}, Lcom/google/gson/reflect/TypeToken;->getType()Ljava/lang/reflect/Type;

    move-result-object v1

    .line 147
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getGSON()Lcom/google/gson/Gson;

    move-result-object v2

    invoke-virtual {v2, v0, v1}, Lcom/google/gson/Gson;->fromJson(Ljava/lang/String;Ljava/lang/reflect/Type;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/ArrayList;

    if-nez v0, :cond_1

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 1915
    :cond_1
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    const/4 v2, 0x0

    move v3, v2

    :cond_2
    :goto_0
    if-ge v3, v1, :cond_3

    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    add-int/lit8 v3, v3, 0x1

    check-cast v4, Lcom/exteragram/messenger/ai/data/Service;

    .line 150
    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/data/Service;->ensureId()Z

    move-result v4

    if-eqz v4, :cond_2

    const/4 v2, 0x1

    goto :goto_0

    :cond_3
    if-eqz v2, :cond_4

    .line 155
    invoke-static {v0}, Lcom/exteragram/messenger/ai/AiConfig;->saveServices(Ljava/util/ArrayList;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :cond_4
    return-object v0

    :catch_0
    move-exception v0

    .line 159
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 160
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    return-object v0
.end method

.method public static final getShowResponseOnly()Z
    .locals 3

    .line 86
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->showResponseOnly$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x3

    aget-object v1, v1, v2

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1}, Lcom/exteragram/messenger/config/BasePref;->getValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    return v0
.end method

.method public static final getTemperature()I
    .locals 3

    .line 85
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->temperature$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x2

    aget-object v1, v1, v2

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1}, Lcom/exteragram/messenger/config/BasePref;->getValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Number;

    invoke-virtual {v0}, Ljava/lang/Number;->intValue()I

    move-result v0

    return v0
.end method

.method private static final migrateLegacyConfig()V
    .locals 9

    .line 51
    const-string v0, "pillstackconfig"

    invoke-static {v0}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getPreferences(Ljava/lang/String;)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 52
    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->preferences:Landroid/content/SharedPreferences;

    invoke-interface {v1}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v1

    .line 53
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 55
    const-string v3, "roles"

    const-string v4, "history"

    const-string v5, "services"

    filled-new-array {v5, v3, v4}, [Ljava/lang/String;

    move-result-object v3

    const/4 v4, 0x0

    move v5, v4

    :goto_0
    const/4 v6, 0x3

    if-ge v5, v6, :cond_2

    aget-object v6, v3, v5

    .line 56
    sget-object v7, Lcom/exteragram/messenger/ai/AiConfig;->preferences:Landroid/content/SharedPreferences;

    invoke-interface {v7, v6}, Landroid/content/SharedPreferences;->contains(Ljava/lang/String;)Z

    move-result v7

    if-nez v7, :cond_1

    .line 57
    invoke-interface {v0}, Landroid/content/SharedPreferences;->getAll()Ljava/util/Map;

    move-result-object v7

    invoke-interface {v7, v6}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v7

    instance-of v8, v7, Ljava/lang/String;

    if-eqz v8, :cond_0

    check-cast v7, Ljava/lang/String;

    goto :goto_1

    :cond_0
    const/4 v7, 0x0

    :goto_1
    if-eqz v7, :cond_1

    .line 58
    invoke-interface {v1, v6, v7}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 59
    invoke-virtual {v2, v6}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_1
    add-int/lit8 v5, v5, 0x1

    goto :goto_0

    .line 64
    :cond_2
    invoke-interface {v2}, Ljava/util/Collection;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_4

    invoke-interface {v1}, Landroid/content/SharedPreferences$Editor;->commit()Z

    move-result v1

    if-eqz v1, :cond_4

    .line 45
    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 1915
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v1

    :goto_2
    if-ge v4, v1, :cond_3

    invoke-virtual {v2, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v4, v4, 0x1

    check-cast v3, Ljava/lang/String;

    .line 66
    invoke-interface {v0, v3}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    goto :goto_2

    .line 50
    :cond_3
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    :cond_4
    return-void
.end method

.method public static final removeLastFromHistory()V
    .locals 3

    .line 211
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getConversationHistory()Ljava/util/ArrayList;

    move-result-object v0

    .line 212
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    const/4 v2, 0x2

    if-lt v1, v2, :cond_0

    .line 213
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    add-int/lit8 v1, v1, -0x1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    .line 214
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    add-int/lit8 v1, v1, -0x1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    .line 215
    invoke-static {v0}, Lcom/exteragram/messenger/ai/AiConfig;->saveConversationHistory(Ljava/util/ArrayList;)V

    :cond_0
    return-void
.end method

.method public static final saveConversationHistory(Ljava/util/ArrayList;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/ai/data/Message;",
            ">;)V"
        }
    .end annotation

    .line 203
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getGSON()Lcom/google/gson/Gson;

    move-result-object v1

    invoke-virtual {v1, p0}, Lcom/google/gson/Gson;->toJson(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    const-string v1, "history"

    invoke-interface {v0, v1, p0}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method public static final saveRoles(Ljava/util/ArrayList;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/ai/data/Role;",
            ">;)V"
        }
    .end annotation

    .line 169
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getGSON()Lcom/google/gson/Gson;

    move-result-object v1

    invoke-virtual {v1, p0}, Lcom/google/gson/Gson;->toJson(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    const-string v1, "roles"

    invoke-interface {v0, v1, p0}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method public static final saveServices(Ljava/util/ArrayList;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/ai/data/Service;",
            ">;)V"
        }
    .end annotation

    .line 165
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getGSON()Lcom/google/gson/Gson;

    move-result-object v1

    invoke-virtual {v1, p0}, Lcom/google/gson/Gson;->toJson(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    const-string v1, "services"

    invoke-interface {v0, v1, p0}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method public static final setInsertAsQuote(Z)V
    .locals 3

    .line 87
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->insertAsQuote$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x4

    aget-object v1, v1, v2

    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1, p0}, Lcom/exteragram/messenger/config/BasePref;->setValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V

    return-void
.end method

.method public static final setOnDeviceFastModel(Z)V
    .locals 3

    .line 90
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->onDeviceFastModel$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x7

    aget-object v1, v1, v2

    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1, p0}, Lcom/exteragram/messenger/config/BasePref;->setValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V

    return-void
.end method

.method public static final setOnDevicePreviewModel(Z)V
    .locals 3

    .line 89
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->onDevicePreviewModel$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x6

    aget-object v1, v1, v2

    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1, p0}, Lcom/exteragram/messenger/config/BasePref;->setValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V

    return-void
.end method

.method public static final setOnDeviceReasoning(Z)V
    .locals 3

    .line 88
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->onDeviceReasoning$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x5

    aget-object v1, v1, v2

    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1, p0}, Lcom/exteragram/messenger/config/BasePref;->setValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V

    return-void
.end method

.method public static final setReplaceTelegramEditor(Z)V
    .locals 2

    .line 96
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "replaceTelegramEditor"

    invoke-interface {v0, v1, p0}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method public static final setReplaceTelegramSummaries(Z)V
    .locals 2

    .line 102
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "replaceTelegramSummaries"

    invoke-interface {v0, v1, p0}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method public static final setResponseStreaming(Z)V
    .locals 3

    .line 84
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->responseStreaming$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x1

    aget-object v1, v1, v2

    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1, p0}, Lcom/exteragram/messenger/config/BasePref;->setValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V

    return-void
.end method

.method public static final setSaveHistory(Z)V
    .locals 3

    .line 83
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->saveHistory$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x0

    aget-object v1, v1, v2

    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1, p0}, Lcom/exteragram/messenger/config/BasePref;->setValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V

    return-void
.end method

.method public static final setSelectedAiRole(Lcom/exteragram/messenger/ai/data/Role;)V
    .locals 0

    .line 184
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Role;->getName()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lcom/exteragram/messenger/ai/AiConfig;->setSelectedRole(Ljava/lang/String;)V

    return-void
.end method

.method public static final setSelectedRole(Ljava/lang/String;)V
    .locals 3

    .line 106
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->selectedRole$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/16 v2, 0xa

    aget-object v1, v1, v2

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1, p0}, Lcom/exteragram/messenger/config/BasePref;->setValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V

    return-void
.end method

.method public static final setSelectedServiceId(Ljava/lang/String;)V
    .locals 3

    .line 104
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->selectedServiceId$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/16 v2, 0x8

    aget-object v1, v1, v2

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1, p0}, Lcom/exteragram/messenger/config/BasePref;->setValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V

    return-void
.end method

.method public static final setSelectedServices(Lcom/exteragram/messenger/ai/data/Service;)V
    .locals 1

    .line 134
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Service;->getId()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lcom/exteragram/messenger/ai/AiConfig;->setSelectedServiceId(Ljava/lang/String;)V

    .line 135
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    const-string v0, "selectedService"

    invoke-interface {p0, v0}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method public static final setShowResponseOnly(Z)V
    .locals 3

    .line 86
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->showResponseOnly$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x3

    aget-object v1, v1, v2

    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1, p0}, Lcom/exteragram/messenger/config/BasePref;->setValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V

    return-void
.end method

.method public static final setTemperature(I)V
    .locals 3

    .line 85
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->temperature$delegate:Lcom/exteragram/messenger/config/BasePref;

    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x2

    aget-object v1, v1, v2

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1, p0}, Lcom/exteragram/messenger/config/BasePref;->setValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V

    return-void
.end method
