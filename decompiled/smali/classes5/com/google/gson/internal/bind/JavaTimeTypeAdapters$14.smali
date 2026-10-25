.class Lcom/google/gson/internal/bind/JavaTimeTypeAdapters$14;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/gson/TypeAdapterFactory;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/google/gson/internal/bind/JavaTimeTypeAdapters;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 394
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public create(Lcom/google/gson/Gson;Lcom/google/gson/reflect/TypeToken;)Lcom/google/gson/TypeAdapter;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Lcom/google/gson/Gson;",
            "Lcom/google/gson/reflect/TypeToken<",
            "TT;>;)",
            "Lcom/google/gson/TypeAdapter<",
            "TT;>;"
        }
    .end annotation

    .line 397
    invoke-virtual {p2}, Lcom/google/gson/reflect/TypeToken;->getRawType()Ljava/lang/Class;

    move-result-object p0

    .line 398
    invoke-virtual {p0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p2

    const-string v0, "java.time."

    invoke-virtual {p2, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p2

    const/4 v0, 0x0

    if-nez p2, :cond_0

    return-object v0

    .line 404
    :cond_0
    const-class p2, Lj$/time/Duration;

    if-ne p0, p2, :cond_1

    .line 405
    invoke-static {}, Lcom/google/gson/internal/bind/JavaTimeTypeAdapters;->access$100()Lcom/google/gson/TypeAdapter;

    move-result-object p0

    return-object p0

    .line 406
    :cond_1
    const-class p2, Lj$/time/Instant;

    if-ne p0, p2, :cond_2

    .line 407
    invoke-static {}, Lcom/google/gson/internal/bind/JavaTimeTypeAdapters;->access$200()Lcom/google/gson/TypeAdapter;

    move-result-object p0

    return-object p0

    .line 408
    :cond_2
    const-class p2, Lj$/time/LocalDate;

    if-ne p0, p2, :cond_3

    .line 409
    invoke-static {}, Lcom/google/gson/internal/bind/JavaTimeTypeAdapters;->access$300()Lcom/google/gson/TypeAdapter;

    move-result-object p0

    return-object p0

    .line 410
    :cond_3
    const-class p2, Lj$/time/LocalTime;

    if-ne p0, p2, :cond_4

    .line 411
    sget-object p0, Lcom/google/gson/internal/bind/JavaTimeTypeAdapters;->LOCAL_TIME:Lcom/google/gson/TypeAdapter;

    return-object p0

    .line 412
    :cond_4
    const-class p2, Lj$/time/LocalDateTime;

    if-ne p0, p2, :cond_5

    .line 413
    invoke-static {p1}, Lcom/google/gson/internal/bind/JavaTimeTypeAdapters;->access$400(Lcom/google/gson/Gson;)Lcom/google/gson/TypeAdapter;

    move-result-object p0

    return-object p0

    .line 414
    :cond_5
    const-class p2, Lj$/time/MonthDay;

    if-ne p0, p2, :cond_6

    .line 415
    invoke-static {}, Lcom/google/gson/internal/bind/JavaTimeTypeAdapters;->access$500()Lcom/google/gson/TypeAdapter;

    move-result-object p0

    return-object p0

    .line 416
    :cond_6
    const-class p2, Lj$/time/OffsetDateTime;

    if-ne p0, p2, :cond_7

    .line 417
    invoke-static {p1}, Lcom/google/gson/internal/bind/JavaTimeTypeAdapters;->access$600(Lcom/google/gson/Gson;)Lcom/google/gson/TypeAdapter;

    move-result-object p0

    return-object p0

    .line 418
    :cond_7
    const-class p2, Lj$/time/OffsetTime;

    if-ne p0, p2, :cond_8

    .line 419
    invoke-static {p1}, Lcom/google/gson/internal/bind/JavaTimeTypeAdapters;->access$700(Lcom/google/gson/Gson;)Lcom/google/gson/TypeAdapter;

    move-result-object p0

    return-object p0

    .line 420
    :cond_8
    const-class p2, Lj$/time/Period;

    if-ne p0, p2, :cond_9

    .line 421
    invoke-static {}, Lcom/google/gson/internal/bind/JavaTimeTypeAdapters;->access$800()Lcom/google/gson/TypeAdapter;

    move-result-object p0

    return-object p0

    .line 422
    :cond_9
    const-class p2, Lj$/time/Year;

    if-ne p0, p2, :cond_a

    .line 423
    invoke-static {}, Lcom/google/gson/internal/bind/JavaTimeTypeAdapters;->access$900()Lcom/google/gson/TypeAdapter;

    move-result-object p0

    return-object p0

    .line 424
    :cond_a
    const-class p2, Lj$/time/YearMonth;

    if-ne p0, p2, :cond_b

    .line 425
    invoke-static {}, Lcom/google/gson/internal/bind/JavaTimeTypeAdapters;->access$1000()Lcom/google/gson/TypeAdapter;

    move-result-object p0

    return-object p0

    .line 426
    :cond_b
    const-class p2, Lj$/time/ZoneId;

    if-eq p0, p2, :cond_e

    const-class p2, Lj$/time/ZoneOffset;

    if-ne p0, p2, :cond_c

    goto :goto_0

    .line 433
    :cond_c
    const-class p2, Lj$/time/ZonedDateTime;

    if-ne p0, p2, :cond_d

    .line 434
    invoke-static {p1}, Lcom/google/gson/internal/bind/JavaTimeTypeAdapters;->access$1200(Lcom/google/gson/Gson;)Lcom/google/gson/TypeAdapter;

    move-result-object p0

    return-object p0

    :cond_d
    return-object v0

    .line 432
    :cond_e
    :goto_0
    invoke-static {}, Lcom/google/gson/internal/bind/JavaTimeTypeAdapters;->access$1100()Lcom/google/gson/TypeAdapter;

    move-result-object p0

    return-object p0
.end method
