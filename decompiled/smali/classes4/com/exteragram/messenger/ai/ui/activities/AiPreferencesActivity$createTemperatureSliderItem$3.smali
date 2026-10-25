.class final synthetic Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$createTemperatureSliderItem$3;
.super Lkotlin/jvm/internal/MutablePropertyReference0Impl;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity;->createTemperatureSliderItem()Lorg/telegram/ui/Components/UItem;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1019
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    k = 0x3
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation


# static fields
.field public static final INSTANCE:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$createTemperatureSliderItem$3;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$createTemperatureSliderItem$3;

    invoke-direct {v0}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$createTemperatureSliderItem$3;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$createTemperatureSliderItem$3;->INSTANCE:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$createTemperatureSliderItem$3;

    return-void
.end method

.method public constructor <init>()V
    .locals 4

    const-string v0, "getTemperature()I"

    const/4 v1, 0x1

    const-class v2, Lcom/exteragram/messenger/ai/AiConfig;

    const-string v3, "temperature"

    invoke-direct {p0, v2, v3, v0, v1}, Lkotlin/jvm/internal/MutablePropertyReference0Impl;-><init>(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V

    return-void
.end method


# virtual methods
.method public get()Ljava/lang/Object;
    .locals 0

    .line 118
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getTemperature()I

    move-result p0

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    return-object p0
.end method

.method public set(Ljava/lang/Object;)V
    .locals 0

    .line 118
    check-cast p1, Ljava/lang/Number;

    invoke-virtual {p1}, Ljava/lang/Number;->intValue()I

    move-result p0

    invoke-static {p0}, Lcom/exteragram/messenger/ai/AiConfig;->setTemperature(I)V

    return-void
.end method
