.class Lcom/google/gson/internal/bind/JavaTimeTypeAdapters$3;
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
        "Lj$/time/LocalDate;",
        ">;"
    }
.end annotation


# direct methods
.method public varargs constructor <init>([Ljava/lang/String;)V
    .locals 0

    .line 80
    invoke-direct {p0, p1}, Lcom/google/gson/internal/bind/TypeAdapters$IntegerFieldsTypeAdapter;-><init>([Ljava/lang/String;)V

    return-void
.end method


# virtual methods
.method public create([J)Lj$/time/LocalDate;
    .locals 3

    const/4 p0, 0x0

    .line 83
    aget-wide v0, p1, p0

    invoke-static {v0, v1}, Ljava/lang/Math;->toIntExact(J)I

    move-result p0

    const/4 v0, 0x1

    aget-wide v0, p1, v0

    invoke-static {v0, v1}, Ljava/lang/Math;->toIntExact(J)I

    move-result v0

    const/4 v1, 0x2

    aget-wide v1, p1, v1

    invoke-static {v1, v2}, Ljava/lang/Math;->toIntExact(J)I

    move-result p1

    invoke-static {p0, v0, p1}, Lj$/time/LocalDate;->of(III)Lj$/time/LocalDate;

    move-result-object p0

    return-object p0
.end method

.method public bridge synthetic create([J)Ljava/lang/Object;
    .locals 0

    .line 80
    invoke-virtual {p0, p1}, Lcom/google/gson/internal/bind/JavaTimeTypeAdapters$3;->create([J)Lj$/time/LocalDate;

    move-result-object p0

    return-object p0
.end method

.method public integerValues(Lj$/time/LocalDate;)[J
    .locals 6

    .line 89
    invoke-virtual {p1}, Lj$/time/LocalDate;->getYear()I

    move-result p0

    int-to-long v0, p0

    invoke-virtual {p1}, Lj$/time/LocalDate;->getMonthValue()I

    move-result p0

    int-to-long v2, p0

    invoke-virtual {p1}, Lj$/time/LocalDate;->getDayOfMonth()I

    move-result p0

    int-to-long p0, p0

    const/4 v4, 0x3

    new-array v4, v4, [J

    const/4 v5, 0x0

    aput-wide v0, v4, v5

    const/4 v0, 0x1

    aput-wide v2, v4, v0

    const/4 v0, 0x2

    aput-wide p0, v4, v0

    return-object v4
.end method

.method public bridge synthetic integerValues(Ljava/lang/Object;)[J
    .locals 0

    .line 80
    check-cast p1, Lj$/time/LocalDate;

    invoke-virtual {p0, p1}, Lcom/google/gson/internal/bind/JavaTimeTypeAdapters$3;->integerValues(Lj$/time/LocalDate;)[J

    move-result-object p0

    return-object p0
.end method
