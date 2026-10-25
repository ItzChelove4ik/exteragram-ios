.class public final enum Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "OtherItem"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;",
        ">;"
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0008\n\n\u0002\u0010\u0008\n\u0002\u0008\u0003\u0008\u0086\u0081\u0002\u0018\u00002\u0008\u0012\u0004\u0012\u00020\u00000\u0001B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003R\u0011\u0010\u000b\u001a\u00020\u000c\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\r\u0010\u000ej\u0002\u0008\u0004j\u0002\u0008\u0005j\u0002\u0008\u0006j\u0002\u0008\u0007j\u0002\u0008\u0008j\u0002\u0008\tj\u0002\u0008\n\u00a8\u0006\u000f"
    }
    d2 = {
        "Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;",
        "",
        "<init>",
        "(Ljava/lang/String;I)V",
        "CRASHLYTICS",
        "ANALYTICS",
        "EXPORT_SETTINGS",
        "EXPORT_DATA",
        "RESET_SETTINGS",
        "DELETE_ACCOUNT",
        "DONATE",
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

.field private static final synthetic $VALUES:[Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

.field public static final enum ANALYTICS:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

.field public static final enum CRASHLYTICS:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

.field public static final enum DELETE_ACCOUNT:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

.field public static final enum DONATE:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

.field public static final enum EXPORT_DATA:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

.field public static final enum EXPORT_SETTINGS:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

.field public static final enum RESET_SETTINGS:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;


# instance fields
.field private final id:I


# direct methods
.method private static final synthetic $values()[Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;
    .locals 7

    sget-object v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->CRASHLYTICS:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    sget-object v1, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->ANALYTICS:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    sget-object v2, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->EXPORT_SETTINGS:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    sget-object v3, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->EXPORT_DATA:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    sget-object v4, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->RESET_SETTINGS:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    sget-object v5, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->DELETE_ACCOUNT:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    sget-object v6, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->DONATE:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    filled-new-array/range {v0 .. v6}, [Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 3

    .line 71
    new-instance v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    const-string v1, "CRASHLYTICS"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->CRASHLYTICS:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    .line 72
    new-instance v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    const-string v1, "ANALYTICS"

    const/4 v2, 0x1

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->ANALYTICS:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    .line 73
    new-instance v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    const-string v1, "EXPORT_SETTINGS"

    const/4 v2, 0x2

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->EXPORT_SETTINGS:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    .line 74
    new-instance v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    const-string v1, "EXPORT_DATA"

    const/4 v2, 0x3

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->EXPORT_DATA:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    .line 75
    new-instance v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    const-string v1, "RESET_SETTINGS"

    const/4 v2, 0x4

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->RESET_SETTINGS:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    .line 76
    new-instance v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    const-string v1, "DELETE_ACCOUNT"

    const/4 v2, 0x5

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->DELETE_ACCOUNT:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    .line 77
    new-instance v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    const-string v1, "DONATE"

    const/4 v2, 0x6

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->DONATE:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    invoke-static {}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->$values()[Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->$VALUES:[Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    invoke-static {v0}, Lkotlin/enums/EnumEntriesKt;->enumEntries([Ljava/lang/Enum;)Lkotlin/enums/EnumEntries;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->$ENTRIES:Lkotlin/enums/EnumEntries;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 70
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    .line 79
    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result p1

    add-int/lit8 p1, p1, 0x1

    iput p1, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->id:I

    return-void
.end method

.method public static getEntries()Lkotlin/enums/EnumEntries;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lkotlin/enums/EnumEntries<",
            "Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;",
            ">;"
        }
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->$ENTRIES:Lkotlin/enums/EnumEntries;

    return-object v0
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;
    .locals 1

    const-class v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    return-object p0
.end method

.method public static values()[Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;
    .locals 1

    sget-object v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->$VALUES:[Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    invoke-virtual {v0}, Ljava/lang/Object;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    return-object v0
.end method


# virtual methods
.method public final getId()I
    .locals 0

    .line 79
    iget p0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->id:I

    return p0
.end method
