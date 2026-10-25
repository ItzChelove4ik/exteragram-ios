.class public final enum Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "HistoryItem"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;",
        ">;"
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0008\u0005\n\u0002\u0010\u0008\n\u0002\u0008\u0003\u0008\u0086\u0081\u0002\u0018\u00002\u0008\u0012\u0004\u0012\u00020\u00000\u0001B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0008\u0010\tj\u0002\u0008\u0004j\u0002\u0008\u0005\u00a8\u0006\n"
    }
    d2 = {
        "Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;",
        "",
        "<init>",
        "(Ljava/lang/String;I)V",
        "SAVE_HISTORY",
        "CLEAR_HISTORY",
        "id",
        "",
        "getId",
        "()I",
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

.field private static final synthetic $VALUES:[Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;

.field public static final enum CLEAR_HISTORY:Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;

.field public static final enum SAVE_HISTORY:Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;


# instance fields
.field private final id:I


# direct methods
.method private static final synthetic $values()[Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;
    .locals 2

    sget-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;->SAVE_HISTORY:Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;

    sget-object v1, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;->CLEAR_HISTORY:Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;

    filled-new-array {v0, v1}, [Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 3

    .line 36
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;

    const-string v1, "SAVE_HISTORY"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;->SAVE_HISTORY:Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;

    .line 37
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;

    const-string v1, "CLEAR_HISTORY"

    const/4 v2, 0x1

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;->CLEAR_HISTORY:Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;

    invoke-static {}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;->$values()[Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;->$VALUES:[Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;

    invoke-static {v0}, Lkotlin/enums/EnumEntriesKt;->enumEntries([Ljava/lang/Enum;)Lkotlin/enums/EnumEntries;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;->$ENTRIES:Lkotlin/enums/EnumEntries;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 35
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    .line 39
    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result p1

    add-int/lit8 p1, p1, 0x1

    iput p1, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;->id:I

    return-void
.end method

.method public static getEntries()Lkotlin/enums/EnumEntries;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lkotlin/enums/EnumEntries<",
            "Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;",
            ">;"
        }
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;->$ENTRIES:Lkotlin/enums/EnumEntries;

    return-object v0
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;
    .locals 1

    const-class v0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;

    return-object p0
.end method

.method public static values()[Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;
    .locals 1

    sget-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;->$VALUES:[Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;

    invoke-virtual {v0}, Ljava/lang/Object;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;

    return-object v0
.end method


# virtual methods
.method public final getId()I
    .locals 0

    .line 39
    iget p0, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;->id:I

    return p0
.end method
