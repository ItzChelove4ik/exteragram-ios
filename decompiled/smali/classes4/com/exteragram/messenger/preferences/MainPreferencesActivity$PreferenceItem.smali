.class public final enum Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/preferences/MainPreferencesActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "PreferenceItem"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;",
        ">;"
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0008\r\n\u0002\u0010\u0008\n\u0002\u0008\u0003\u0008\u0086\u0081\u0002\u0018\u00002\u0008\u0012\u0004\u0012\u00020\u00000\u0001B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0010\u0010\u0011j\u0002\u0008\u0004j\u0002\u0008\u0005j\u0002\u0008\u0006j\u0002\u0008\u0007j\u0002\u0008\u0008j\u0002\u0008\tj\u0002\u0008\nj\u0002\u0008\u000bj\u0002\u0008\u000cj\u0002\u0008\r\u00a8\u0006\u0012"
    }
    d2 = {
        "Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;",
        "",
        "<init>",
        "(Ljava/lang/String;I)V",
        "HEADER_CELL",
        "GENERAL_CATEGORY",
        "APPEARANCE_CATEGORY",
        "CHATS_CATEGORY",
        "PLUGINS_CATEGORY",
        "OTHER_CATEGORY",
        "CHANNEL",
        "FORUM",
        "CROWDIN",
        "WEBSITE",
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

.field private static final synthetic $VALUES:[Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

.field public static final enum APPEARANCE_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

.field public static final enum CHANNEL:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

.field public static final enum CHATS_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

.field public static final enum CROWDIN:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

.field public static final enum FORUM:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

.field public static final enum GENERAL_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

.field public static final enum HEADER_CELL:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

.field public static final enum OTHER_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

.field public static final enum PLUGINS_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

.field public static final enum WEBSITE:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;


# instance fields
.field private final id:I


# direct methods
.method private static final synthetic $values()[Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;
    .locals 10

    sget-object v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->HEADER_CELL:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    sget-object v1, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->GENERAL_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    sget-object v2, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->APPEARANCE_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    sget-object v3, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->CHATS_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    sget-object v4, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->PLUGINS_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    sget-object v5, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->OTHER_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    sget-object v6, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->CHANNEL:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    sget-object v7, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->FORUM:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    sget-object v8, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->CROWDIN:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    sget-object v9, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->WEBSITE:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    filled-new-array/range {v0 .. v9}, [Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 3

    .line 38
    new-instance v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    const-string v1, "HEADER_CELL"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->HEADER_CELL:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    .line 39
    new-instance v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    const-string v1, "GENERAL_CATEGORY"

    const/4 v2, 0x1

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->GENERAL_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    .line 40
    new-instance v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    const-string v1, "APPEARANCE_CATEGORY"

    const/4 v2, 0x2

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->APPEARANCE_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    .line 41
    new-instance v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    const-string v1, "CHATS_CATEGORY"

    const/4 v2, 0x3

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->CHATS_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    .line 42
    new-instance v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    const-string v1, "PLUGINS_CATEGORY"

    const/4 v2, 0x4

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->PLUGINS_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    .line 43
    new-instance v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    const-string v1, "OTHER_CATEGORY"

    const/4 v2, 0x5

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->OTHER_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    .line 44
    new-instance v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    const-string v1, "CHANNEL"

    const/4 v2, 0x6

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->CHANNEL:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    .line 45
    new-instance v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    const-string v1, "FORUM"

    const/4 v2, 0x7

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->FORUM:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    .line 46
    new-instance v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    const-string v1, "CROWDIN"

    const/16 v2, 0x8

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->CROWDIN:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    .line 47
    new-instance v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    const-string v1, "WEBSITE"

    const/16 v2, 0x9

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->WEBSITE:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    invoke-static {}, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->$values()[Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->$VALUES:[Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    invoke-static {v0}, Lkotlin/enums/EnumEntriesKt;->enumEntries([Ljava/lang/Enum;)Lkotlin/enums/EnumEntries;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->$ENTRIES:Lkotlin/enums/EnumEntries;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 37
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    .line 49
    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result p1

    add-int/lit8 p1, p1, 0x1

    iput p1, p0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->id:I

    return-void
.end method

.method public static getEntries()Lkotlin/enums/EnumEntries;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lkotlin/enums/EnumEntries<",
            "Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;",
            ">;"
        }
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->$ENTRIES:Lkotlin/enums/EnumEntries;

    return-object v0
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;
    .locals 1

    const-class v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    return-object p0
.end method

.method public static values()[Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;
    .locals 1

    sget-object v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->$VALUES:[Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    invoke-virtual {v0}, Ljava/lang/Object;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    return-object v0
.end method


# virtual methods
.method public final getId()I
    .locals 0

    .line 49
    iget p0, p0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->id:I

    return p0
.end method
