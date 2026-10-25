.class public final Lcom/exteragram/messenger/plugins/PluginsConstants$HookFilterTypes;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/plugins/PluginsConstants;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "HookFilterTypes"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/plugins/PluginsConstants$HookFilterTypes$Companion;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u000c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003\u00a8\u0006\u0005"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/PluginsConstants$HookFilterTypes;",
        "",
        "<init>",
        "()V",
        "Companion",
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


# static fields
.field public static final ARGUMENT_EQUAL:Ljava/lang/String; = "argument_equal"

.field public static final ARGUMENT_IS_FALSE:Ljava/lang/String; = "argument_is_false"

.field public static final ARGUMENT_IS_INSTANCE_OF:Ljava/lang/String; = "argument_is_instance_of"

.field public static final ARGUMENT_IS_NULL:Ljava/lang/String; = "argument_is_null"

.field public static final ARGUMENT_IS_TRUE:Ljava/lang/String; = "argument_is_true"

.field public static final ARGUMENT_NOT_EQUAL:Ljava/lang/String; = "argument_not_equal"

.field public static final ARGUMENT_NOT_NULL:Ljava/lang/String; = "argument_not_null"

.field public static final CONDITION:Ljava/lang/String; = "condition"

.field public static final Companion:Lcom/exteragram/messenger/plugins/PluginsConstants$HookFilterTypes$Companion;

.field public static final OR:Ljava/lang/String; = "or"

.field public static final RESULT_EQUAL:Ljava/lang/String; = "result_equal"

.field public static final RESULT_IS_FALSE:Ljava/lang/String; = "result_is_false"

.field public static final RESULT_IS_INSTANCE_OF:Ljava/lang/String; = "result_is_instance_of"

.field public static final RESULT_IS_NULL:Ljava/lang/String; = "result_is_null"

.field public static final RESULT_IS_TRUE:Ljava/lang/String; = "result_is_true"

.field public static final RESULT_NOT_EQUAL:Ljava/lang/String; = "result_not_equal"

.field public static final RESULT_NOT_NULL:Ljava/lang/String; = "result_not_null"


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/plugins/PluginsConstants$HookFilterTypes$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/plugins/PluginsConstants$HookFilterTypes$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/plugins/PluginsConstants$HookFilterTypes;->Companion:Lcom/exteragram/messenger/plugins/PluginsConstants$HookFilterTypes$Companion;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 127
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
