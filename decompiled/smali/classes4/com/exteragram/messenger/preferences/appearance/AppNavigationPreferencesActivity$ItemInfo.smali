.class final Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "ItemInfo"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0003\n\u0002\u0010\u000e\n\u0002\u0008\u0005\n\u0002\u0010\u000b\n\u0002\u0008\u0008\u0008\u0082\u0008\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\u0008\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\u0008H\u00d6\u0001\u00a2\u0006\u0004\u0008\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0004H\u00d6\u0001\u00a2\u0006\u0004\u0008\u000b\u0010\u000cJ\u001a\u0010\u000f\u001a\u00020\u000e2\u0008\u0010\r\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\u0008\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0003\u0010\u0011\u001a\u0004\u0008\u0012\u0010\u0013R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0005\u0010\u0014\u001a\u0004\u0008\u0015\u0010\u000c\u00a8\u0006\u0016"
    }
    d2 = {
        "Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;",
        "",
        "",
        "name",
        "",
        "iconRes",
        "<init>",
        "(Ljava/lang/CharSequence;I)V",
        "",
        "toString",
        "()Ljava/lang/String;",
        "hashCode",
        "()I",
        "other",
        "",
        "equals",
        "(Ljava/lang/Object;)Z",
        "Ljava/lang/CharSequence;",
        "getName",
        "()Ljava/lang/CharSequence;",
        "I",
        "getIconRes",
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
.field private final iconRes:I

.field private final name:Ljava/lang/CharSequence;


# direct methods
.method public constructor <init>(Ljava/lang/CharSequence;I)V
    .locals 0

    .line 82
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;->name:Ljava/lang/CharSequence;

    iput p2, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;->iconRes:I

    return-void
.end method


# virtual methods
.method public equals(Ljava/lang/Object;)Z
    .locals 4

    const/4 v0, 0x1

    if-ne p0, p1, :cond_0

    return v0

    :cond_0
    instance-of v1, p1, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;

    const/4 v2, 0x0

    if-nez v1, :cond_1

    return v2

    :cond_1
    check-cast p1, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;

    iget-object v1, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;->name:Ljava/lang/CharSequence;

    iget-object v3, p1, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;->name:Ljava/lang/CharSequence;

    invoke-static {v1, v3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_2

    return v2

    :cond_2
    iget p0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;->iconRes:I

    iget p1, p1, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;->iconRes:I

    if-eq p0, p1, :cond_3

    return v2

    :cond_3
    return v0
.end method

.method public final getIconRes()I
    .locals 0

    .line 82
    iget p0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;->iconRes:I

    return p0
.end method

.method public final getName()Ljava/lang/CharSequence;
    .locals 0

    .line 82
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;->name:Ljava/lang/CharSequence;

    return-object p0
.end method

.method public hashCode()I
    .locals 1

    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;->name:Ljava/lang/CharSequence;

    invoke-virtual {v0}, Ljava/lang/Object;->hashCode()I

    move-result v0

    mul-int/lit8 v0, v0, 0x1f

    iget p0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;->iconRes:I

    invoke-static {p0}, Ljava/lang/Integer;->hashCode(I)I

    move-result p0

    add-int/2addr v0, p0

    return v0
.end method

.method public toString()Ljava/lang/String;
    .locals 3

    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;->name:Ljava/lang/CharSequence;

    iget p0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;->iconRes:I

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "ItemInfo(name="

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, ", iconRes="

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, ")"

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
