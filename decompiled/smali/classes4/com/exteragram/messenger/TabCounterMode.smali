.class public final enum Lcom/exteragram/messenger/TabCounterMode;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/exteragram/messenger/TabCounterMode;",
        ">;"
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u000c\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0008\u0006\u0008\u0086\u0081\u0002\u0018\u00002\u0008\u0012\u0004\u0012\u00020\u00000\u0001B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003j\u0002\u0008\u0004j\u0002\u0008\u0005j\u0002\u0008\u0006\u00a8\u0006\u0007"
    }
    d2 = {
        "Lcom/exteragram/messenger/TabCounterMode;",
        "",
        "<init>",
        "(Ljava/lang/String;I)V",
        "ALL",
        "UNMUTED",
        "HIDDEN",
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
.field private static final synthetic $ENTRIES:Lkotlin/enums/EnumEntries;

.field private static final synthetic $VALUES:[Lcom/exteragram/messenger/TabCounterMode;

.field public static final enum ALL:Lcom/exteragram/messenger/TabCounterMode;

.field public static final enum HIDDEN:Lcom/exteragram/messenger/TabCounterMode;

.field public static final enum UNMUTED:Lcom/exteragram/messenger/TabCounterMode;


# direct methods
.method private static final synthetic $values()[Lcom/exteragram/messenger/TabCounterMode;
    .locals 3

    sget-object v0, Lcom/exteragram/messenger/TabCounterMode;->ALL:Lcom/exteragram/messenger/TabCounterMode;

    sget-object v1, Lcom/exteragram/messenger/TabCounterMode;->UNMUTED:Lcom/exteragram/messenger/TabCounterMode;

    sget-object v2, Lcom/exteragram/messenger/TabCounterMode;->HIDDEN:Lcom/exteragram/messenger/TabCounterMode;

    filled-new-array {v0, v1, v2}, [Lcom/exteragram/messenger/TabCounterMode;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 3

    .line 87
    new-instance v0, Lcom/exteragram/messenger/TabCounterMode;

    const-string v1, "ALL"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/TabCounterMode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/TabCounterMode;->ALL:Lcom/exteragram/messenger/TabCounterMode;

    .line 88
    new-instance v0, Lcom/exteragram/messenger/TabCounterMode;

    const-string v1, "UNMUTED"

    const/4 v2, 0x1

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/TabCounterMode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/TabCounterMode;->UNMUTED:Lcom/exteragram/messenger/TabCounterMode;

    .line 89
    new-instance v0, Lcom/exteragram/messenger/TabCounterMode;

    const-string v1, "HIDDEN"

    const/4 v2, 0x2

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/TabCounterMode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/TabCounterMode;->HIDDEN:Lcom/exteragram/messenger/TabCounterMode;

    invoke-static {}, Lcom/exteragram/messenger/TabCounterMode;->$values()[Lcom/exteragram/messenger/TabCounterMode;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/TabCounterMode;->$VALUES:[Lcom/exteragram/messenger/TabCounterMode;

    invoke-static {v0}, Lkotlin/enums/EnumEntriesKt;->enumEntries([Ljava/lang/Enum;)Lkotlin/enums/EnumEntries;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/TabCounterMode;->$ENTRIES:Lkotlin/enums/EnumEntries;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 86
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static getEntries()Lkotlin/enums/EnumEntries;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lkotlin/enums/EnumEntries<",
            "Lcom/exteragram/messenger/TabCounterMode;",
            ">;"
        }
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/TabCounterMode;->$ENTRIES:Lkotlin/enums/EnumEntries;

    return-object v0
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/exteragram/messenger/TabCounterMode;
    .locals 1

    const-class v0, Lcom/exteragram/messenger/TabCounterMode;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/TabCounterMode;

    return-object p0
.end method

.method public static values()[Lcom/exteragram/messenger/TabCounterMode;
    .locals 1

    sget-object v0, Lcom/exteragram/messenger/TabCounterMode;->$VALUES:[Lcom/exteragram/messenger/TabCounterMode;

    invoke-virtual {v0}, Ljava/lang/Object;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/exteragram/messenger/TabCounterMode;

    return-object v0
.end method
