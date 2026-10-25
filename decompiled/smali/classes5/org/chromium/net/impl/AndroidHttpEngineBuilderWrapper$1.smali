.class abstract synthetic Lorg/chromium/net/impl/AndroidHttpEngineBuilderWrapper$1;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/chromium/net/impl/AndroidHttpEngineBuilderWrapper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1009
    name = null
.end annotation


# static fields
.field static final synthetic $SwitchMap$org$chromium$net$telemetry$OptionalBoolean:[I


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 260
    invoke-static {}, Lorg/chromium/net/telemetry/OptionalBoolean;->values()[Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v0

    array-length v0, v0

    new-array v0, v0, [I

    sput-object v0, Lorg/chromium/net/impl/AndroidHttpEngineBuilderWrapper$1;->$SwitchMap$org$chromium$net$telemetry$OptionalBoolean:[I

    :try_start_0
    sget-object v1, Lorg/chromium/net/telemetry/OptionalBoolean;->TRUE:Lorg/chromium/net/telemetry/OptionalBoolean;

    invoke-virtual {v1}, Ljava/lang/Enum;->ordinal()I

    move-result v1

    const/4 v2, 0x1

    aput v2, v0, v1
    :try_end_0
    .catch Ljava/lang/NoSuchFieldError; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    :try_start_1
    sget-object v0, Lorg/chromium/net/impl/AndroidHttpEngineBuilderWrapper$1;->$SwitchMap$org$chromium$net$telemetry$OptionalBoolean:[I

    sget-object v1, Lorg/chromium/net/telemetry/OptionalBoolean;->FALSE:Lorg/chromium/net/telemetry/OptionalBoolean;

    invoke-virtual {v1}, Ljava/lang/Enum;->ordinal()I

    move-result v1

    const/4 v2, 0x2

    aput v2, v0, v1
    :try_end_1
    .catch Ljava/lang/NoSuchFieldError; {:try_start_1 .. :try_end_1} :catch_1

    :catch_1
    :try_start_2
    sget-object v0, Lorg/chromium/net/impl/AndroidHttpEngineBuilderWrapper$1;->$SwitchMap$org$chromium$net$telemetry$OptionalBoolean:[I

    sget-object v1, Lorg/chromium/net/telemetry/OptionalBoolean;->UNSET:Lorg/chromium/net/telemetry/OptionalBoolean;

    invoke-virtual {v1}, Ljava/lang/Enum;->ordinal()I

    move-result v1

    const/4 v2, 0x3

    aput v2, v0, v1
    :try_end_2
    .catch Ljava/lang/NoSuchFieldError; {:try_start_2 .. :try_end_2} :catch_2

    :catch_2
    return-void
.end method
