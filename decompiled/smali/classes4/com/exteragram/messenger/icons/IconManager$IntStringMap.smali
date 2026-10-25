.class final Lcom/exteragram/messenger/icons/IconManager$IntStringMap;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/icons/IconManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "IntStringMap"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/icons/IconManager$IntStringMap$Companion;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\u0008\u0005\n\u0002\u0010\u0002\n\u0002\u0008\u0008\u0008\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\u0008\u0004\u0010\u0005J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H\u0002J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0013\u001a\u00020\u0003J\u0016\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\nJ\u0008\u0010\u0016\u001a\u00020\u0010H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0018\u0010\u0008\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\u000c\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"
    }
    d2 = {
        "Lcom/exteragram/messenger/icons/IconManager$IntStringMap;",
        "",
        "expected",
        "",
        "<init>",
        "(I)V",
        "keys",
        "",
        "values",
        "",
        "",
        "[Ljava/lang/String;",
        "mask",
        "shift",
        "size",
        "allocate",
        "",
        "capacity",
        "get",
        "key",
        "put",
        "value",
        "grow",
        "Companion",
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
.field public static final Companion:Lcom/exteragram/messenger/icons/IconManager$IntStringMap$Companion;


# instance fields
.field private keys:[I

.field private mask:I

.field private shift:I

.field private size:I

.field private values:[Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/icons/IconManager$IntStringMap$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->Companion:Lcom/exteragram/messenger/icons/IconManager$IntStringMap$Companion;

    return-void
.end method

.method public constructor <init>(I)V
    .locals 2

    .line 138
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    .line 139
    new-array v1, v0, [I

    iput-object v1, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->keys:[I

    .line 140
    new-array v0, v0, [Ljava/lang/String;

    iput-object v0, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->values:[Ljava/lang/String;

    const/4 v0, 0x4

    :goto_0
    mul-int/lit8 v1, p1, 0x2

    if-ge v0, v1, :cond_0

    shl-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 148
    :cond_0
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->allocate(I)V

    return-void
.end method

.method private final allocate(I)V
    .locals 1

    .line 152
    new-array v0, p1, [I

    iput-object v0, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->keys:[I

    .line 153
    new-array v0, p1, [Ljava/lang/String;

    iput-object v0, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->values:[Ljava/lang/String;

    add-int/lit8 v0, p1, -0x1

    .line 154
    iput v0, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->mask:I

    .line 155
    invoke-static {p1}, Ljava/lang/Integer;->numberOfTrailingZeros(I)I

    move-result p1

    rsub-int/lit8 p1, p1, 0x20

    iput p1, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->shift:I

    return-void
.end method

.method private final grow()V
    .locals 6

    .line 190
    iget-object v0, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->keys:[I

    .line 191
    iget-object v1, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->values:[Ljava/lang/String;

    .line 192
    array-length v2, v0

    shl-int/lit8 v2, v2, 0x1

    invoke-direct {p0, v2}, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->allocate(I)V

    const/4 v2, 0x0

    .line 193
    iput v2, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->size:I

    .line 194
    array-length v3, v0

    :goto_0
    if-ge v2, v3, :cond_1

    .line 195
    aget v4, v0, v2

    if-eqz v4, :cond_0

    .line 196
    aget-object v5, v1, v2

    invoke-virtual {p0, v4, v5}, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->put(ILjava/lang/String;)V

    :cond_0
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_1
    return-void
.end method


# virtual methods
.method public final get(I)Ljava/lang/String;
    .locals 3

    const/4 v0, 0x0

    if-nez p1, :cond_0

    return-object v0

    :cond_0
    const v1, -0x61c88647

    mul-int/2addr v1, p1

    .line 160
    iget v2, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->shift:I

    ushr-int/2addr v1, v2

    .line 162
    :goto_0
    iget-object v2, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->keys:[I

    aget v2, v2, v1

    if-ne v2, p1, :cond_1

    .line 163
    iget-object p0, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->values:[Ljava/lang/String;

    aget-object p0, p0, v1

    return-object p0

    :cond_1
    if-nez v2, :cond_2

    return-object v0

    :cond_2
    add-int/lit8 v1, v1, 0x1

    .line 165
    iget v2, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->mask:I

    and-int/2addr v1, v2

    goto :goto_0
.end method

.method public final put(ILjava/lang/String;)V
    .locals 3

    if-nez p1, :cond_0

    return-void

    .line 171
    :cond_0
    iget v0, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->size:I

    add-int/lit8 v0, v0, 0x1

    mul-int/lit8 v0, v0, 0x2

    iget-object v1, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->keys:[I

    array-length v1, v1

    if-le v0, v1, :cond_1

    invoke-direct {p0}, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->grow()V

    :cond_1
    const v0, -0x61c88647

    mul-int/2addr v0, p1

    .line 172
    iget v1, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->shift:I

    ushr-int/2addr v0, v1

    .line 174
    :goto_0
    iget-object v1, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->keys:[I

    aget v2, v1, v0

    if-nez v2, :cond_2

    .line 176
    aput p1, v1, v0

    .line 177
    iget-object p1, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->values:[Ljava/lang/String;

    aput-object p2, p1, v0

    .line 178
    iget p1, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->size:I

    add-int/lit8 p1, p1, 0x1

    iput p1, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->size:I

    return-void

    :cond_2
    if-ne v2, p1, :cond_3

    .line 182
    iget-object p0, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->values:[Ljava/lang/String;

    aput-object p2, p0, v0

    return-void

    :cond_3
    add-int/lit8 v0, v0, 0x1

    .line 185
    iget v1, p0, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->mask:I

    and-int/2addr v0, v1

    goto :goto_0
.end method
