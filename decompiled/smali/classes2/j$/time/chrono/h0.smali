.class public final Lj$/time/chrono/h0;
.super Lj$/time/chrono/d;
.source "r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0"


# static fields
.field private static final serialVersionUID:J = -0x790bcfffa3423007L


# instance fields
.field public final transient a:Lj$/time/LocalDate;


# direct methods
.method public constructor <init>(Lj$/time/LocalDate;)V
    .locals 1

    .line 1
    invoke-direct {p0}, Lj$/time/chrono/d;-><init>()V

    .line 2
    .line 3
    .line 4
    const-string v0, "isoDate"

    .line 5
    .line 6
    invoke-static {p1, v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    .line 7
    .line 8
    .line 9
    iput-object p1, p0, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 10
    .line 11
    return-void
.end method

.method private readObject(Ljava/io/ObjectInputStream;)V
    .locals 0

    .line 1
    new-instance p0, Ljava/io/InvalidObjectException;

    .line 2
    .line 3
    const-string p1, "Deserialization via serialization delegate"

    .line 4
    .line 5
    invoke-direct {p0, p1}, Ljava/io/InvalidObjectException;-><init>(Ljava/lang/String;)V

    .line 6
    .line 7
    .line 8
    throw p0
.end method

.method private writeReplace()Ljava/lang/Object;
    .locals 2

    .line 1
    new-instance v0, Lj$/time/chrono/d0;

    .line 2
    .line 3
    const/16 v1, 0x8

    .line 4
    .line 5
    invoke-direct {v0, v1, p0}, Lj$/time/chrono/d0;-><init>(BLjava/lang/Object;)V

    .line 6
    .line 7
    .line 8
    return-object v0
.end method


# virtual methods
.method public final I(J)Lj$/time/chrono/b;
    .locals 1

    .line 1
    iget-object v0, p0, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 2
    .line 3
    invoke-virtual {v0, p1, p2}, Lj$/time/LocalDate;->plusDays(J)Lj$/time/LocalDate;

    .line 4
    .line 5
    .line 6
    move-result-object p1

    .line 7
    invoke-virtual {p0, p1}, Lj$/time/chrono/h0;->b0(Lj$/time/LocalDate;)Lj$/time/chrono/h0;

    .line 8
    .line 9
    .line 10
    move-result-object p0

    .line 11
    return-object p0
.end method

.method public final K(J)Lj$/time/chrono/b;
    .locals 1

    .line 1
    iget-object v0, p0, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 2
    .line 3
    invoke-virtual {v0, p1, p2}, Lj$/time/LocalDate;->j0(J)Lj$/time/LocalDate;

    .line 4
    .line 5
    .line 6
    move-result-object p1

    .line 7
    invoke-virtual {p0, p1}, Lj$/time/chrono/h0;->b0(Lj$/time/LocalDate;)Lj$/time/chrono/h0;

    .line 8
    .line 9
    .line 10
    move-result-object p0

    .line 11
    return-object p0
.end method

.method public final L()J
    .locals 2

    .line 1
    iget-object p0, p0, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 2
    .line 3
    invoke-virtual {p0}, Lj$/time/LocalDate;->L()J

    .line 4
    .line 5
    .line 6
    move-result-wide v0

    .line 7
    return-wide v0
.end method

.method public final M(Lj$/time/LocalTime;)Lj$/time/chrono/ChronoLocalDateTime;
    .locals 1

    .line 1
    new-instance v0, Lj$/time/chrono/f;

    .line 2
    .line 3
    invoke-direct {v0, p0, p1}, Lj$/time/chrono/f;-><init>(Lj$/time/chrono/b;Lj$/time/LocalTime;)V

    .line 4
    .line 5
    .line 6
    return-object v0
.end method

.method public final P()Lj$/time/chrono/l;
    .locals 1

    .line 1
    invoke-virtual {p0}, Lj$/time/chrono/h0;->Y()I

    .line 2
    .line 3
    .line 4
    move-result p0

    .line 5
    const/4 v0, 0x1

    .line 6
    if-lt p0, v0, :cond_0

    .line 7
    .line 8
    sget-object p0, Lj$/time/chrono/i0;->BE:Lj$/time/chrono/i0;

    .line 9
    .line 10
    return-object p0

    .line 11
    :cond_0
    sget-object p0, Lj$/time/chrono/i0;->BEFORE_BE:Lj$/time/chrono/i0;

    .line 12
    .line 13
    return-object p0
.end method

.method public final S(J)Lj$/time/chrono/b;
    .locals 1

    .line 1
    iget-object v0, p0, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 2
    .line 3
    invoke-virtual {v0, p1, p2}, Lj$/time/LocalDate;->l0(J)Lj$/time/LocalDate;

    .line 4
    .line 5
    .line 6
    move-result-object p1

    .line 7
    invoke-virtual {p0, p1}, Lj$/time/chrono/h0;->b0(Lj$/time/LocalDate;)Lj$/time/chrono/h0;

    .line 8
    .line 9
    .line 10
    move-result-object p0

    .line 11
    return-object p0
.end method

.method public final U(Lj$/time/temporal/o;)Lj$/time/chrono/b;
    .locals 0

    .line 1
    invoke-super {p0, p1}, Lj$/time/chrono/d;->U(Lj$/time/temporal/o;)Lj$/time/chrono/b;

    .line 2
    .line 3
    .line 4
    move-result-object p0

    .line 5
    check-cast p0, Lj$/time/chrono/h0;

    .line 6
    .line 7
    return-object p0
.end method

.method public final Y()I
    .locals 0

    .line 1
    iget-object p0, p0, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 2
    .line 3
    invoke-virtual {p0}, Lj$/time/LocalDate;->getYear()I

    .line 4
    .line 5
    .line 6
    move-result p0

    .line 7
    add-int/lit16 p0, p0, 0x21f

    .line 8
    .line 9
    return p0
.end method

.method public final bridge synthetic a(JLj$/time/temporal/p;)Lj$/time/chrono/b;
    .locals 0

    .line 1
    invoke-virtual {p0, p1, p2, p3}, Lj$/time/chrono/h0;->a0(JLj$/time/temporal/p;)Lj$/time/chrono/h0;

    .line 2
    .line 3
    .line 4
    move-result-object p0

    .line 5
    return-object p0
.end method

.method public final bridge synthetic a(JLj$/time/temporal/p;)Lj$/time/temporal/Temporal;
    .locals 0

    .line 6
    invoke-virtual {p0, p1, p2, p3}, Lj$/time/chrono/h0;->a0(JLj$/time/temporal/p;)Lj$/time/chrono/h0;

    move-result-object p0

    return-object p0
.end method

.method public final a0(JLj$/time/temporal/p;)Lj$/time/chrono/h0;
    .locals 7

    .line 1
    instance-of v0, p3, Lj$/time/temporal/a;

    .line 2
    .line 3
    if-eqz v0, :cond_7

    .line 4
    .line 5
    move-object v0, p3

    .line 6
    check-cast v0, Lj$/time/temporal/a;

    .line 7
    .line 8
    invoke-virtual {p0, v0}, Lj$/time/chrono/h0;->k(Lj$/time/temporal/p;)J

    .line 9
    .line 10
    .line 11
    move-result-wide v1

    .line 12
    cmp-long v1, v1, p1

    .line 13
    .line 14
    if-nez v1, :cond_0

    .line 15
    .line 16
    return-object p0

    .line 17
    :cond_0
    sget-object v1, Lj$/time/chrono/g0;->a:[I

    .line 18
    .line 19
    invoke-virtual {v0}, Ljava/lang/Enum;->ordinal()I

    .line 20
    .line 21
    .line 22
    move-result v2

    .line 23
    aget v2, v1, v2

    .line 24
    .line 25
    const/4 v3, 0x7

    .line 26
    const/4 v4, 0x6

    .line 27
    const/4 v5, 0x4

    .line 28
    if-eq v2, v5, :cond_2

    .line 29
    .line 30
    const/4 v6, 0x5

    .line 31
    if-eq v2, v6, :cond_1

    .line 32
    .line 33
    if-eq v2, v4, :cond_2

    .line 34
    .line 35
    if-eq v2, v3, :cond_2

    .line 36
    .line 37
    goto :goto_0

    .line 38
    :cond_1
    sget-object p3, Lj$/time/chrono/f0;->c:Lj$/time/chrono/f0;

    .line 39
    .line 40
    invoke-virtual {p3, v0}, Lj$/time/chrono/f0;->B(Lj$/time/temporal/a;)Lj$/time/temporal/s;

    .line 41
    .line 42
    .line 43
    move-result-object p3

    .line 44
    invoke-virtual {p3, p1, p2, v0}, Lj$/time/temporal/s;->b(JLj$/time/temporal/p;)V

    .line 45
    .line 46
    .line 47
    invoke-virtual {p0}, Lj$/time/chrono/h0;->Y()I

    .line 48
    .line 49
    .line 50
    move-result p3

    .line 51
    int-to-long v0, p3

    .line 52
    const-wide/16 v2, 0xc

    .line 53
    .line 54
    mul-long/2addr v0, v2

    .line 55
    iget-object p3, p0, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 56
    .line 57
    invoke-virtual {p3}, Lj$/time/LocalDate;->getMonthValue()I

    .line 58
    .line 59
    .line 60
    move-result p3

    .line 61
    int-to-long v2, p3

    .line 62
    add-long/2addr v0, v2

    .line 63
    const-wide/16 v2, 0x1

    .line 64
    .line 65
    sub-long/2addr v0, v2

    .line 66
    sub-long/2addr p1, v0

    .line 67
    iget-object p3, p0, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 68
    .line 69
    invoke-virtual {p3, p1, p2}, Lj$/time/LocalDate;->j0(J)Lj$/time/LocalDate;

    .line 70
    .line 71
    .line 72
    move-result-object p1

    .line 73
    invoke-virtual {p0, p1}, Lj$/time/chrono/h0;->b0(Lj$/time/LocalDate;)Lj$/time/chrono/h0;

    .line 74
    .line 75
    .line 76
    move-result-object p0

    .line 77
    return-object p0

    .line 78
    :cond_2
    sget-object v2, Lj$/time/chrono/f0;->c:Lj$/time/chrono/f0;

    .line 79
    .line 80
    invoke-virtual {v2, v0}, Lj$/time/chrono/f0;->B(Lj$/time/temporal/a;)Lj$/time/temporal/s;

    .line 81
    .line 82
    .line 83
    move-result-object v2

    .line 84
    invoke-virtual {v2, p1, p2, v0}, Lj$/time/temporal/s;->a(JLj$/time/temporal/p;)I

    .line 85
    .line 86
    .line 87
    move-result v2

    .line 88
    invoke-virtual {v0}, Ljava/lang/Enum;->ordinal()I

    .line 89
    .line 90
    .line 91
    move-result v0

    .line 92
    aget v0, v1, v0

    .line 93
    .line 94
    if-eq v0, v5, :cond_5

    .line 95
    .line 96
    if-eq v0, v4, :cond_4

    .line 97
    .line 98
    if-eq v0, v3, :cond_3

    .line 99
    .line 100
    :goto_0
    iget-object v0, p0, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 101
    .line 102
    invoke-virtual {v0, p1, p2, p3}, Lj$/time/LocalDate;->n0(JLj$/time/temporal/p;)Lj$/time/LocalDate;

    .line 103
    .line 104
    .line 105
    move-result-object p1

    .line 106
    invoke-virtual {p0, p1}, Lj$/time/chrono/h0;->b0(Lj$/time/LocalDate;)Lj$/time/chrono/h0;

    .line 107
    .line 108
    .line 109
    move-result-object p0

    .line 110
    return-object p0

    .line 111
    :cond_3
    iget-object p1, p0, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 112
    .line 113
    invoke-virtual {p0}, Lj$/time/chrono/h0;->Y()I

    .line 114
    .line 115
    .line 116
    move-result p2

    .line 117
    rsub-int p2, p2, -0x21e

    .line 118
    .line 119
    invoke-virtual {p1, p2}, Lj$/time/LocalDate;->p0(I)Lj$/time/LocalDate;

    .line 120
    .line 121
    .line 122
    move-result-object p1

    .line 123
    invoke-virtual {p0, p1}, Lj$/time/chrono/h0;->b0(Lj$/time/LocalDate;)Lj$/time/chrono/h0;

    .line 124
    .line 125
    .line 126
    move-result-object p0

    .line 127
    return-object p0

    .line 128
    :cond_4
    iget-object p1, p0, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 129
    .line 130
    add-int/lit16 v2, v2, -0x21f

    .line 131
    .line 132
    invoke-virtual {p1, v2}, Lj$/time/LocalDate;->p0(I)Lj$/time/LocalDate;

    .line 133
    .line 134
    .line 135
    move-result-object p1

    .line 136
    invoke-virtual {p0, p1}, Lj$/time/chrono/h0;->b0(Lj$/time/LocalDate;)Lj$/time/chrono/h0;

    .line 137
    .line 138
    .line 139
    move-result-object p0

    .line 140
    return-object p0

    .line 141
    :cond_5
    iget-object p1, p0, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 142
    .line 143
    invoke-virtual {p0}, Lj$/time/chrono/h0;->Y()I

    .line 144
    .line 145
    .line 146
    move-result p2

    .line 147
    const/4 p3, 0x1

    .line 148
    if-lt p2, p3, :cond_6

    .line 149
    .line 150
    goto :goto_1

    .line 151
    :cond_6
    rsub-int/lit8 v2, v2, 0x1

    .line 152
    .line 153
    :goto_1
    add-int/lit16 v2, v2, -0x21f

    .line 154
    .line 155
    invoke-virtual {p1, v2}, Lj$/time/LocalDate;->p0(I)Lj$/time/LocalDate;

    .line 156
    .line 157
    .line 158
    move-result-object p1

    .line 159
    invoke-virtual {p0, p1}, Lj$/time/chrono/h0;->b0(Lj$/time/LocalDate;)Lj$/time/chrono/h0;

    .line 160
    .line 161
    .line 162
    move-result-object p0

    .line 163
    return-object p0

    .line 164
    :cond_7
    invoke-super {p0, p1, p2, p3}, Lj$/time/chrono/d;->a(JLj$/time/temporal/p;)Lj$/time/chrono/b;

    .line 165
    .line 166
    .line 167
    move-result-object p0

    .line 168
    check-cast p0, Lj$/time/chrono/h0;

    .line 169
    .line 170
    return-object p0
.end method

.method public final b(JLj$/time/temporal/TemporalUnit;)Lj$/time/chrono/b;
    .locals 0

    .line 1
    invoke-super {p0, p1, p2, p3}, Lj$/time/chrono/d;->b(JLj$/time/temporal/TemporalUnit;)Lj$/time/chrono/b;

    .line 2
    .line 3
    .line 4
    move-result-object p0

    .line 5
    check-cast p0, Lj$/time/chrono/h0;

    .line 6
    .line 7
    return-object p0
.end method

.method public final b(JLj$/time/temporal/TemporalUnit;)Lj$/time/temporal/Temporal;
    .locals 0

    .line 8
    invoke-super {p0, p1, p2, p3}, Lj$/time/chrono/d;->b(JLj$/time/temporal/TemporalUnit;)Lj$/time/chrono/b;

    move-result-object p0

    check-cast p0, Lj$/time/chrono/h0;

    return-object p0
.end method

.method public final b0(Lj$/time/LocalDate;)Lj$/time/chrono/h0;
    .locals 1

    .line 1
    iget-object v0, p0, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 2
    .line 3
    invoke-virtual {p1, v0}, Lj$/time/LocalDate;->equals(Ljava/lang/Object;)Z

    .line 4
    .line 5
    .line 6
    move-result v0

    .line 7
    if-eqz v0, :cond_0

    .line 8
    .line 9
    return-object p0

    .line 10
    :cond_0
    new-instance p0, Lj$/time/chrono/h0;

    .line 11
    .line 12
    invoke-direct {p0, p1}, Lj$/time/chrono/h0;-><init>(Lj$/time/LocalDate;)V

    .line 13
    .line 14
    .line 15
    return-object p0
.end method

.method public final c(JLj$/time/temporal/TemporalUnit;)Lj$/time/chrono/b;
    .locals 0

    .line 1
    invoke-super {p0, p1, p2, p3}, Lj$/time/chrono/d;->c(JLj$/time/temporal/TemporalUnit;)Lj$/time/chrono/b;

    .line 2
    .line 3
    .line 4
    move-result-object p0

    .line 5
    check-cast p0, Lj$/time/chrono/h0;

    .line 6
    .line 7
    return-object p0
.end method

.method public final c(JLj$/time/temporal/TemporalUnit;)Lj$/time/temporal/Temporal;
    .locals 0

    .line 8
    invoke-super {p0, p1, p2, p3}, Lj$/time/chrono/d;->c(JLj$/time/temporal/TemporalUnit;)Lj$/time/chrono/b;

    move-result-object p0

    check-cast p0, Lj$/time/chrono/h0;

    return-object p0
.end method

.method public final equals(Ljava/lang/Object;)Z
    .locals 1

    .line 1
    if-ne p0, p1, :cond_0

    .line 2
    .line 3
    const/4 p0, 0x1

    .line 4
    return p0

    .line 5
    :cond_0
    instance-of v0, p1, Lj$/time/chrono/h0;

    .line 6
    .line 7
    if-eqz v0, :cond_1

    .line 8
    .line 9
    check-cast p1, Lj$/time/chrono/h0;

    .line 10
    .line 11
    iget-object p0, p0, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 12
    .line 13
    iget-object p1, p1, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 14
    .line 15
    invoke-virtual {p0, p1}, Lj$/time/LocalDate;->equals(Ljava/lang/Object;)Z

    .line 16
    .line 17
    .line 18
    move-result p0

    .line 19
    return p0

    .line 20
    :cond_1
    const/4 p0, 0x0

    .line 21
    return p0
.end method

.method public final f()Lj$/time/chrono/k;
    .locals 0

    .line 1
    sget-object p0, Lj$/time/chrono/f0;->c:Lj$/time/chrono/f0;

    .line 2
    .line 3
    return-object p0
.end method

.method public final h(Lj$/time/LocalDate;)Lj$/time/temporal/Temporal;
    .locals 0

    .line 1
    invoke-super {p0, p1}, Lj$/time/chrono/d;->j(Lj$/time/temporal/m;)Lj$/time/chrono/b;

    .line 2
    .line 3
    .line 4
    move-result-object p0

    .line 5
    check-cast p0, Lj$/time/chrono/h0;

    .line 6
    .line 7
    return-object p0
.end method

.method public final hashCode()I
    .locals 1

    .line 1
    sget-object v0, Lj$/time/chrono/f0;->c:Lj$/time/chrono/f0;

    .line 2
    .line 3
    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 4
    .line 5
    .line 6
    iget-object p0, p0, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 7
    .line 8
    invoke-virtual {p0}, Lj$/time/LocalDate;->hashCode()I

    .line 9
    .line 10
    .line 11
    move-result p0

    .line 12
    const v0, 0x8b59791

    .line 13
    .line 14
    .line 15
    xor-int/2addr p0, v0

    .line 16
    return p0
.end method

.method public final j(Lj$/time/temporal/m;)Lj$/time/chrono/b;
    .locals 0

    .line 1
    invoke-super {p0, p1}, Lj$/time/chrono/d;->j(Lj$/time/temporal/m;)Lj$/time/chrono/b;

    .line 2
    .line 3
    .line 4
    move-result-object p0

    .line 5
    check-cast p0, Lj$/time/chrono/h0;

    .line 6
    .line 7
    return-object p0
.end method

.method public final k(Lj$/time/temporal/p;)J
    .locals 4

    .line 1
    instance-of v0, p1, Lj$/time/temporal/a;

    .line 2
    .line 3
    if-eqz v0, :cond_6

    .line 4
    .line 5
    sget-object v0, Lj$/time/chrono/g0;->a:[I

    .line 6
    .line 7
    move-object v1, p1

    .line 8
    check-cast v1, Lj$/time/temporal/a;

    .line 9
    .line 10
    invoke-virtual {v1}, Ljava/lang/Enum;->ordinal()I

    .line 11
    .line 12
    .line 13
    move-result v1

    .line 14
    aget v0, v0, v1

    .line 15
    .line 16
    const/4 v1, 0x4

    .line 17
    const/4 v2, 0x1

    .line 18
    if-eq v0, v1, :cond_4

    .line 19
    .line 20
    const/4 v1, 0x5

    .line 21
    if-eq v0, v1, :cond_3

    .line 22
    .line 23
    const/4 v1, 0x6

    .line 24
    if-eq v0, v1, :cond_2

    .line 25
    .line 26
    const/4 v1, 0x7

    .line 27
    if-eq v0, v1, :cond_0

    .line 28
    .line 29
    iget-object p0, p0, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 30
    .line 31
    invoke-virtual {p0, p1}, Lj$/time/LocalDate;->k(Lj$/time/temporal/p;)J

    .line 32
    .line 33
    .line 34
    move-result-wide p0

    .line 35
    return-wide p0

    .line 36
    :cond_0
    invoke-virtual {p0}, Lj$/time/chrono/h0;->Y()I

    .line 37
    .line 38
    .line 39
    move-result p0

    .line 40
    if-lt p0, v2, :cond_1

    .line 41
    .line 42
    goto :goto_0

    .line 43
    :cond_1
    const/4 v2, 0x0

    .line 44
    :goto_0
    int-to-long p0, v2

    .line 45
    return-wide p0

    .line 46
    :cond_2
    invoke-virtual {p0}, Lj$/time/chrono/h0;->Y()I

    .line 47
    .line 48
    .line 49
    move-result p0

    .line 50
    int-to-long p0, p0

    .line 51
    return-wide p0

    .line 52
    :cond_3
    invoke-virtual {p0}, Lj$/time/chrono/h0;->Y()I

    .line 53
    .line 54
    .line 55
    move-result p1

    .line 56
    int-to-long v0, p1

    .line 57
    const-wide/16 v2, 0xc

    .line 58
    .line 59
    mul-long/2addr v0, v2

    .line 60
    iget-object p0, p0, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 61
    .line 62
    invoke-virtual {p0}, Lj$/time/LocalDate;->getMonthValue()I

    .line 63
    .line 64
    .line 65
    move-result p0

    .line 66
    int-to-long p0, p0

    .line 67
    add-long/2addr v0, p0

    .line 68
    const-wide/16 p0, 0x1

    .line 69
    .line 70
    sub-long/2addr v0, p0

    .line 71
    return-wide v0

    .line 72
    :cond_4
    invoke-virtual {p0}, Lj$/time/chrono/h0;->Y()I

    .line 73
    .line 74
    .line 75
    move-result p0

    .line 76
    if-lt p0, v2, :cond_5

    .line 77
    .line 78
    goto :goto_1

    .line 79
    :cond_5
    rsub-int/lit8 p0, p0, 0x1

    .line 80
    .line 81
    :goto_1
    int-to-long p0, p0

    .line 82
    return-wide p0

    .line 83
    :cond_6
    invoke-interface {p1, p0}, Lj$/time/temporal/p;->S(Lj$/time/temporal/l;)J

    .line 84
    .line 85
    .line 86
    move-result-wide p0

    .line 87
    return-wide p0
.end method

.method public final l(Lj$/time/temporal/p;)Lj$/time/temporal/s;
    .locals 4

    .line 1
    instance-of v0, p1, Lj$/time/temporal/a;

    .line 2
    .line 3
    if-eqz v0, :cond_4

    .line 4
    .line 5
    invoke-interface {p0, p1}, Lj$/time/chrono/b;->i(Lj$/time/temporal/p;)Z

    .line 6
    .line 7
    .line 8
    move-result v0

    .line 9
    if-eqz v0, :cond_3

    .line 10
    .line 11
    move-object v0, p1

    .line 12
    check-cast v0, Lj$/time/temporal/a;

    .line 13
    .line 14
    sget-object v1, Lj$/time/chrono/g0;->a:[I

    .line 15
    .line 16
    invoke-virtual {v0}, Ljava/lang/Enum;->ordinal()I

    .line 17
    .line 18
    .line 19
    move-result v2

    .line 20
    aget v1, v1, v2

    .line 21
    .line 22
    const/4 v2, 0x1

    .line 23
    if-eq v1, v2, :cond_2

    .line 24
    .line 25
    const/4 v2, 0x2

    .line 26
    if-eq v1, v2, :cond_2

    .line 27
    .line 28
    const/4 v2, 0x3

    .line 29
    if-eq v1, v2, :cond_2

    .line 30
    .line 31
    const/4 p1, 0x4

    .line 32
    if-eq v1, p1, :cond_0

    .line 33
    .line 34
    sget-object p0, Lj$/time/chrono/f0;->c:Lj$/time/chrono/f0;

    .line 35
    .line 36
    invoke-virtual {p0, v0}, Lj$/time/chrono/f0;->B(Lj$/time/temporal/a;)Lj$/time/temporal/s;

    .line 37
    .line 38
    .line 39
    move-result-object p0

    .line 40
    return-object p0

    .line 41
    :cond_0
    sget-object p1, Lj$/time/temporal/a;->YEAR:Lj$/time/temporal/a;

    .line 42
    .line 43
    iget-object p1, p1, Lj$/time/temporal/a;->b:Lj$/time/temporal/s;

    .line 44
    .line 45
    invoke-virtual {p0}, Lj$/time/chrono/h0;->Y()I

    .line 46
    .line 47
    .line 48
    move-result p0

    .line 49
    const-wide/16 v0, 0x1

    .line 50
    .line 51
    const-wide/16 v2, 0x21f

    .line 52
    .line 53
    if-gtz p0, :cond_1

    .line 54
    .line 55
    iget-wide p0, p1, Lj$/time/temporal/s;->a:J

    .line 56
    .line 57
    add-long/2addr p0, v2

    .line 58
    neg-long p0, p0

    .line 59
    add-long/2addr p0, v0

    .line 60
    goto :goto_0

    .line 61
    :cond_1
    iget-wide p0, p1, Lj$/time/temporal/s;->d:J

    .line 62
    .line 63
    add-long/2addr p0, v2

    .line 64
    :goto_0
    invoke-static {v0, v1, p0, p1}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    .line 65
    .line 66
    .line 67
    move-result-object p0

    .line 68
    return-object p0

    .line 69
    :cond_2
    iget-object p0, p0, Lj$/time/chrono/h0;->a:Lj$/time/LocalDate;

    .line 70
    .line 71
    invoke-virtual {p0, p1}, Lj$/time/LocalDate;->l(Lj$/time/temporal/p;)Lj$/time/temporal/s;

    .line 72
    .line 73
    .line 74
    move-result-object p0

    .line 75
    return-object p0

    .line 76
    :cond_3
    new-instance p0, Lj$/time/temporal/r;

    .line 77
    .line 78
    const-string v0, "Unsupported field: "

    .line 79
    .line 80
    invoke-static {v0, p1}, Lj$/time/c;->a(Ljava/lang/String;Lj$/time/temporal/p;)Ljava/lang/String;

    .line 81
    .line 82
    .line 83
    move-result-object p1

    .line 84
    invoke-direct {p0, p1}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    .line 85
    .line 86
    .line 87
    throw p0

    .line 88
    :cond_4
    invoke-interface {p1, p0}, Lj$/time/temporal/p;->D(Lj$/time/temporal/l;)Lj$/time/temporal/s;

    .line 89
    .line 90
    .line 91
    move-result-object p0

    .line 92
    return-object p0
.end method
