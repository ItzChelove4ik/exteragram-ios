.class Lcom/google/gson/internal/bind/JavaTimeTypeAdapters$4;
.super Lcom/google/gson/internal/bind/TypeAdapters$IntegerFieldsTypeAdapter;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/google/gson/internal/bind/JavaTimeTypeAdapters;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/google/gson/internal/bind/TypeAdapters$IntegerFieldsTypeAdapter<",
        "Lj$/time/LocalTime;",
        ">;"
    }
.end annotation


# direct methods
.method public varargs constructor <init>([Ljava/lang/String;)V
    .locals 0

    .line 95
    invoke-direct {p0, p1}, Lcom/google/gson/internal/bind/TypeAdapters$IntegerFieldsTypeAdapter;-><init>([Ljava/lang/String;)V

    return-void
.end method


# virtual methods
.method public create([J)Lj$/time/LocalTime;
    .locals 4

    const/4 p0, 0x0

    .line 98
    aget-wide v0, p1, p0

    .line 99
    invoke-static {v0, v1}, Ljava/lang/Math;->toIntExact(J)I

    move-result p0

    const/4 v0, 0x1

    aget-wide v0, p1, v0

    .line 100
    invoke-static {v0, v1}, Ljava/lang/Math;->toIntExact(J)I

    move-result v0

    const/4 v1, 0x2

    aget-wide v1, p1, v1

    .line 101
    invoke-static {v1, v2}, Ljava/lang/Math;->toIntExact(J)I

    move-result v1

    const/4 v2, 0x3

    aget-wide v2, p1, v2

    .line 102
    invoke-static {v2, v3}, Ljava/lang/Math;->toIntExact(J)I

    move-result p1

    .line 98
    invoke-static {p0, v0, v1, p1}, Lj$/time/LocalTime;->of(IIII)Lj$/time/LocalTime;

    move-result-object p0

    return-object p0
.end method

.method public bridge synthetic create([J)Ljava/lang/Object;
    .locals 0

    .line 95
    invoke-virtual {p0, p1}, Lcom/google/gson/internal/bind/JavaTimeTypeAdapters$4;->create([J)Lj$/time/LocalTime;

    move-result-object p0

    return-object p0
.end method

.method public integerValues(Lj$/time/LocalTime;)[J
    .locals 8

    .line 108
    invoke-virtual {p1}, Lj$/time/LocalTime;->getHour()I

    move-result p0

    int-to-long v0, p0

    invoke-virtual {p1}, Lj$/time/LocalTime;->getMinute()I

    move-result p0

    int-to-long v2, p0

    invoke-virtual {p1}, Lj$/time/LocalTime;->getSecond()I

    move-result p0

    int-to-long v4, p0

    invoke-virtual {p1}, Lj$/time/LocalTime;->getNano()I

    move-result p0

    int-to-long p0, p0

    const/4 v6, 0x4

    new-array v6, v6, [J

    const/4 v7, 0x0

    aput-wide v0, v6, v7

    const/4 v0, 0x1

    aput-wide v2, v6, v0

    const/4 v0, 0x2

    aput-wide v4, v6, v0

    const/4 v0, 0x3

    aput-wide p0, v6, v0

    return-object v6
.end method

.method public bridge synthetic integerValues(Ljava/lang/Object;)[J
    .locals 0

    .line 95
    check-cast p1, Lj$/time/LocalTime;

    invoke-virtual {p0, p1}, Lcom/google/gson/internal/bind/JavaTimeTypeAdapters$4;->integerValues(Lj$/time/LocalTime;)[J

    move-result-object p0

    return-object p0
.end method
