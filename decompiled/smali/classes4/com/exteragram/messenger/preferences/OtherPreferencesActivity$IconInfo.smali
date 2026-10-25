.class public final Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "IconInfo"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0004\n\u0002\u0010\u000e\n\u0002\u0008\u0005\n\u0002\u0010\u000b\n\u0002\u0008\u0006\u0008\u0086\u0008\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\u0008\u0005\u0010\u0006J\u0010\u0010\u0008\u001a\u00020\u0007H\u00d6\u0001\u00a2\u0006\u0004\u0008\u0008\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\u0008\n\u0010\u000bJ\u001a\u0010\u000e\u001a\u00020\r2\u0008\u0010\u000c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\u0008\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0003\u0010\u0010\u001a\u0004\u0008\u0011\u0010\u000bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0004\u0010\u0010\u001a\u0004\u0008\u0012\u0010\u000b\u00a8\u0006\u0013"
    }
    d2 = {
        "Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;",
        "",
        "",
        "iconResId",
        "iconColor",
        "<init>",
        "(II)V",
        "",
        "toString",
        "()Ljava/lang/String;",
        "hashCode",
        "()I",
        "other",
        "",
        "equals",
        "(Ljava/lang/Object;)Z",
        "I",
        "getIconResId",
        "getIconColor",
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
.field private final iconColor:I

.field private final iconResId:I


# direct methods
.method public constructor <init>(II)V
    .locals 0

    .line 82
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p1, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;->iconResId:I

    iput p2, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;->iconColor:I

    return-void
.end method


# virtual methods
.method public equals(Ljava/lang/Object;)Z
    .locals 4

    const/4 v0, 0x1

    if-ne p0, p1, :cond_0

    return v0

    :cond_0
    instance-of v1, p1, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;

    const/4 v2, 0x0

    if-nez v1, :cond_1

    return v2

    :cond_1
    check-cast p1, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;

    iget v1, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;->iconResId:I

    iget v3, p1, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;->iconResId:I

    if-eq v1, v3, :cond_2

    return v2

    :cond_2
    iget p0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;->iconColor:I

    iget p1, p1, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;->iconColor:I

    if-eq p0, p1, :cond_3

    return v2

    :cond_3
    return v0
.end method

.method public final getIconColor()I
    .locals 0

    .line 82
    iget p0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;->iconColor:I

    return p0
.end method

.method public final getIconResId()I
    .locals 0

    .line 82
    iget p0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;->iconResId:I

    return p0
.end method

.method public hashCode()I
    .locals 1

    iget v0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;->iconResId:I

    invoke-static {v0}, Ljava/lang/Integer;->hashCode(I)I

    move-result v0

    mul-int/lit8 v0, v0, 0x1f

    iget p0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;->iconColor:I

    invoke-static {p0}, Ljava/lang/Integer;->hashCode(I)I

    move-result p0

    add-int/2addr v0, p0

    return v0
.end method

.method public toString()Ljava/lang/String;
    .locals 3

    iget v0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;->iconResId:I

    iget p0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;->iconColor:I

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "IconInfo(iconResId="

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, ", iconColor="

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, ")"

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
