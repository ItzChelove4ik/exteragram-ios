.class public final enum Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "PreferenceItem"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;",
        ">;"
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0008\u000c\n\u0002\u0010\u0008\n\u0002\u0008\u0003\u0008\u0086\u0081\u0002\u0018\u00002\u0008\u0012\u0004\u0012\u00020\u00000\u0001B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u000f\u0010\u0010j\u0002\u0008\u0004j\u0002\u0008\u0005j\u0002\u0008\u0006j\u0002\u0008\u0007j\u0002\u0008\u0008j\u0002\u0008\tj\u0002\u0008\nj\u0002\u0008\u000bj\u0002\u0008\u000c\u00a8\u0006\u0011"
    }
    d2 = {
        "Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;",
        "",
        "<init>",
        "(Ljava/lang/String;I)V",
        "ENDPOINT",
        "ROLE",
        "HISTORY",
        "TEMPERATURE",
        "RESPONSE_STREAMING",
        "SHOW_RESPONSE_ONLY",
        "INSERT_AS_QUOTE",
        "REPLACE_TELEGRAM_EDITOR",
        "REPLACE_TELEGRAM_SUMMARIES",
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

.field private static final synthetic $VALUES:[Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

.field public static final enum ENDPOINT:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

.field public static final enum HISTORY:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

.field public static final enum INSERT_AS_QUOTE:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

.field public static final enum REPLACE_TELEGRAM_EDITOR:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

.field public static final enum REPLACE_TELEGRAM_SUMMARIES:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

.field public static final enum RESPONSE_STREAMING:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

.field public static final enum ROLE:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

.field public static final enum SHOW_RESPONSE_ONLY:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

.field public static final enum TEMPERATURE:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;


# instance fields
.field private final id:I


# direct methods
.method private static final synthetic $values()[Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;
    .locals 9

    sget-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->ENDPOINT:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    sget-object v1, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->ROLE:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    sget-object v2, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->HISTORY:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    sget-object v3, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->TEMPERATURE:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    sget-object v4, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->RESPONSE_STREAMING:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    sget-object v5, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->SHOW_RESPONSE_ONLY:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    sget-object v6, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->INSERT_AS_QUOTE:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    sget-object v7, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->REPLACE_TELEGRAM_EDITOR:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    sget-object v8, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->REPLACE_TELEGRAM_SUMMARIES:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    filled-new-array/range {v0 .. v8}, [Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 3

    .line 43
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    const-string v1, "ENDPOINT"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->ENDPOINT:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    .line 44
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    const-string v1, "ROLE"

    const/4 v2, 0x1

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->ROLE:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    .line 45
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    const-string v1, "HISTORY"

    const/4 v2, 0x2

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->HISTORY:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    .line 46
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    const-string v1, "TEMPERATURE"

    const/4 v2, 0x3

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->TEMPERATURE:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    .line 47
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    const-string v1, "RESPONSE_STREAMING"

    const/4 v2, 0x4

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->RESPONSE_STREAMING:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    .line 48
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    const-string v1, "SHOW_RESPONSE_ONLY"

    const/4 v2, 0x5

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->SHOW_RESPONSE_ONLY:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    .line 49
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    const-string v1, "INSERT_AS_QUOTE"

    const/4 v2, 0x6

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->INSERT_AS_QUOTE:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    .line 50
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    const-string v1, "REPLACE_TELEGRAM_EDITOR"

    const/4 v2, 0x7

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->REPLACE_TELEGRAM_EDITOR:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    .line 51
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    const-string v1, "REPLACE_TELEGRAM_SUMMARIES"

    const/16 v2, 0x8

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->REPLACE_TELEGRAM_SUMMARIES:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    invoke-static {}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->$values()[Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->$VALUES:[Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    invoke-static {v0}, Lkotlin/enums/EnumEntriesKt;->enumEntries([Ljava/lang/Enum;)Lkotlin/enums/EnumEntries;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->$ENTRIES:Lkotlin/enums/EnumEntries;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 42
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    .line 53
    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result p1

    add-int/lit8 p1, p1, 0x1

    iput p1, p0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->id:I

    return-void
.end method

.method public static getEntries()Lkotlin/enums/EnumEntries;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lkotlin/enums/EnumEntries<",
            "Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;",
            ">;"
        }
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->$ENTRIES:Lkotlin/enums/EnumEntries;

    return-object v0
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;
    .locals 1

    const-class v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    return-object p0
.end method

.method public static values()[Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;
    .locals 1

    sget-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->$VALUES:[Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    invoke-virtual {v0}, Ljava/lang/Object;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    return-object v0
.end method


# virtual methods
.method public final getId()I
    .locals 0

    .line 53
    iget p0, p0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->id:I

    return p0
.end method
