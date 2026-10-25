.class public final Landroidx/media3/common/util/LongArray;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private size:I

.field private values:[J


# direct methods
.method public constructor <init>()V
    .locals 1

    const/16 v0, 0x20

    .line 32
    invoke-direct {p0, v0}, Landroidx/media3/common/util/LongArray;-><init>(I)V

    return-void
.end method

.method public constructor <init>(I)V
    .locals 0

    .line 38
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 39
    new-array p1, p1, [J

    iput-object p1, p0, Landroidx/media3/common/util/LongArray;->values:[J

    return-void
.end method


# virtual methods
.method public add(J)V
    .locals 3

    .line 48
    iget v0, p0, Landroidx/media3/common/util/LongArray;->size:I

    iget-object v1, p0, Landroidx/media3/common/util/LongArray;->values:[J

    array-length v2, v1

    if-ne v0, v2, :cond_0

    mul-int/lit8 v0, v0, 0x2

    .line 49
    invoke-static {v1, v0}, Ljava/util/Arrays;->copyOf([JI)[J

    move-result-object v0

    iput-object v0, p0, Landroidx/media3/common/util/LongArray;->values:[J

    .line 51
    :cond_0
    iget-object v0, p0, Landroidx/media3/common/util/LongArray;->values:[J

    iget v1, p0, Landroidx/media3/common/util/LongArray;->size:I

    add-int/lit8 v2, v1, 0x1

    iput v2, p0, Landroidx/media3/common/util/LongArray;->size:I

    aput-wide p1, v0, v1

    return-void
.end method

.method public addAll([J)V
    .locals 5

    .line 60
    iget v0, p0, Landroidx/media3/common/util/LongArray;->size:I

    array-length v1, p1

    add-int/2addr v0, v1

    .line 61
    iget-object v1, p0, Landroidx/media3/common/util/LongArray;->values:[J

    array-length v2, v1

    if-le v0, v2, :cond_0

    .line 62
    array-length v2, v1

    mul-int/lit8 v2, v2, 0x2

    invoke-static {v2, v0}, Ljava/lang/Math;->max(II)I

    move-result v2

    invoke-static {v1, v2}, Ljava/util/Arrays;->copyOf([JI)[J

    move-result-object v1

    iput-object v1, p0, Landroidx/media3/common/util/LongArray;->values:[J

    .line 64
    :cond_0
    iget-object v1, p0, Landroidx/media3/common/util/LongArray;->values:[J

    iget v2, p0, Landroidx/media3/common/util/LongArray;->size:I

    array-length v3, p1

    const/4 v4, 0x0

    invoke-static {p1, v4, v1, v2, v3}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 65
    iput v0, p0, Landroidx/media3/common/util/LongArray;->size:I

    return-void
.end method

.method public get(I)J
    .locals 2

    if-ltz p1, :cond_0

    .line 77
    iget v0, p0, Landroidx/media3/common/util/LongArray;->size:I

    if-ge p1, v0, :cond_0

    .line 80
    iget-object p0, p0, Landroidx/media3/common/util/LongArray;->values:[J

    aget-wide v0, p0, p1

    return-wide v0

    .line 78
    :cond_0
    const-string v0, ", size is "

    iget p0, p0, Landroidx/media3/common/util/LongArray;->size:I

    const-string v1, "Invalid index "

    invoke-static {v1, p1, v0, p0}, Lkotlin/text/Regex$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;ILjava/lang/Object;I)V

    const-wide/16 p0, 0x0

    return-wide p0
.end method

.method public size()I
    .locals 0

    .line 85
    iget p0, p0, Landroidx/media3/common/util/LongArray;->size:I

    return p0
.end method
