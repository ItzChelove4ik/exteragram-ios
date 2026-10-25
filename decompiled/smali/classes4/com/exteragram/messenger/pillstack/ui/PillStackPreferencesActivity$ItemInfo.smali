.class final Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity$ItemInfo;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "ItemInfo"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u000b\u0008\u0002\u0018\u00002\u00020\u0001B\'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\u0004\u0008\u0008\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u000c\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u000f\u0010\r\u00a8\u0006\u0010"
    }
    d2 = {
        "Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity$ItemInfo;",
        "",
        "name",
        "",
        "iconRes",
        "",
        "iconColorTop",
        "iconColorBottom",
        "<init>",
        "(Ljava/lang/CharSequence;III)V",
        "getName",
        "()Ljava/lang/CharSequence;",
        "getIconRes",
        "()I",
        "getIconColorTop",
        "getIconColorBottom",
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


# instance fields
.field private final iconColorBottom:I

.field private final iconColorTop:I

.field private final iconRes:I

.field private final name:Ljava/lang/CharSequence;


# direct methods
.method public constructor <init>(Ljava/lang/CharSequence;III)V
    .locals 0

    .line 41
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 42
    iput-object p1, p0, Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity$ItemInfo;->name:Ljava/lang/CharSequence;

    .line 43
    iput p2, p0, Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity$ItemInfo;->iconRes:I

    .line 44
    iput p3, p0, Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity$ItemInfo;->iconColorTop:I

    .line 45
    iput p4, p0, Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity$ItemInfo;->iconColorBottom:I

    return-void
.end method


# virtual methods
.method public final getIconColorBottom()I
    .locals 0

    .line 45
    iget p0, p0, Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity$ItemInfo;->iconColorBottom:I

    return p0
.end method

.method public final getIconColorTop()I
    .locals 0

    .line 44
    iget p0, p0, Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity$ItemInfo;->iconColorTop:I

    return p0
.end method

.method public final getIconRes()I
    .locals 0

    .line 43
    iget p0, p0, Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity$ItemInfo;->iconRes:I

    return p0
.end method

.method public final getName()Ljava/lang/CharSequence;
    .locals 0

    .line 42
    iget-object p0, p0, Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity$ItemInfo;->name:Ljava/lang/CharSequence;

    return-object p0
.end method
