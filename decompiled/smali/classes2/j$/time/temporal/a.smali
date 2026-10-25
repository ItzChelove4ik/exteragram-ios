.class public final enum Lj$/time/temporal/a;
.super Ljava/lang/Enum;
.source "r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0"

# interfaces
.implements Lj$/time/temporal/p;


# static fields
.field public static final enum ALIGNED_DAY_OF_WEEK_IN_MONTH:Lj$/time/temporal/a;

.field public static final enum ALIGNED_DAY_OF_WEEK_IN_YEAR:Lj$/time/temporal/a;

.field public static final enum ALIGNED_WEEK_OF_MONTH:Lj$/time/temporal/a;

.field public static final enum ALIGNED_WEEK_OF_YEAR:Lj$/time/temporal/a;

.field public static final enum AMPM_OF_DAY:Lj$/time/temporal/a;

.field public static final enum CLOCK_HOUR_OF_AMPM:Lj$/time/temporal/a;

.field public static final enum CLOCK_HOUR_OF_DAY:Lj$/time/temporal/a;

.field public static final enum DAY_OF_MONTH:Lj$/time/temporal/a;

.field public static final enum DAY_OF_WEEK:Lj$/time/temporal/a;

.field public static final enum DAY_OF_YEAR:Lj$/time/temporal/a;

.field public static final enum EPOCH_DAY:Lj$/time/temporal/a;

.field public static final enum ERA:Lj$/time/temporal/a;

.field public static final enum HOUR_OF_AMPM:Lj$/time/temporal/a;

.field public static final enum HOUR_OF_DAY:Lj$/time/temporal/a;

.field public static final enum INSTANT_SECONDS:Lj$/time/temporal/a;

.field public static final enum MICRO_OF_DAY:Lj$/time/temporal/a;

.field public static final enum MICRO_OF_SECOND:Lj$/time/temporal/a;

.field public static final enum MILLI_OF_DAY:Lj$/time/temporal/a;

.field public static final enum MILLI_OF_SECOND:Lj$/time/temporal/a;

.field public static final enum MINUTE_OF_DAY:Lj$/time/temporal/a;

.field public static final enum MINUTE_OF_HOUR:Lj$/time/temporal/a;

.field public static final enum MONTH_OF_YEAR:Lj$/time/temporal/a;

.field public static final enum NANO_OF_DAY:Lj$/time/temporal/a;

.field public static final enum NANO_OF_SECOND:Lj$/time/temporal/a;

.field public static final enum OFFSET_SECONDS:Lj$/time/temporal/a;

.field public static final enum PROLEPTIC_MONTH:Lj$/time/temporal/a;

.field public static final enum SECOND_OF_DAY:Lj$/time/temporal/a;

.field public static final enum SECOND_OF_MINUTE:Lj$/time/temporal/a;

.field public static final enum YEAR:Lj$/time/temporal/a;

.field public static final enum YEAR_OF_ERA:Lj$/time/temporal/a;

.field public static final synthetic c:[Lj$/time/temporal/a;


# instance fields
.field public final a:Ljava/lang/String;

.field public final b:Lj$/time/temporal/s;


# direct methods
.method static constructor <clinit>()V
    .locals 64

    .line 1
    new-instance v0, Lj$/time/temporal/a;

    sget-object v4, Lj$/time/temporal/ChronoUnit;->NANOS:Lj$/time/temporal/ChronoUnit;

    sget-object v9, Lj$/time/temporal/ChronoUnit;->SECONDS:Lj$/time/temporal/ChronoUnit;

    const-wide/16 v13, 0x0

    const-wide/32 v10, 0x3b9ac9ff

    invoke-static {v13, v14, v10, v11}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v6

    const-string v1, "NANO_OF_SECOND"

    const/4 v2, 0x0

    const-string v3, "NanoOfSecond"

    move-object v5, v9

    invoke-direct/range {v0 .. v6}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    sput-object v0, Lj$/time/temporal/a;->NANO_OF_SECOND:Lj$/time/temporal/a;

    .line 2
    new-instance v1, Lj$/time/temporal/a;

    sget-object v19, Lj$/time/temporal/ChronoUnit;->DAYS:Lj$/time/temporal/ChronoUnit;

    const-wide v2, 0x4e94914effffL

    invoke-static {v13, v14, v2, v3}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v7

    const-string v2, "NANO_OF_DAY"

    const/4 v3, 0x1

    move-object v5, v4

    const-string v4, "NanoOfDay"

    move-object/from16 v6, v19

    invoke-direct/range {v1 .. v7}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    sput-object v1, Lj$/time/temporal/a;->NANO_OF_DAY:Lj$/time/temporal/a;

    .line 3
    new-instance v3, Lj$/time/temporal/a;

    move-object v5, v9

    sget-object v9, Lj$/time/temporal/ChronoUnit;->MICROS:Lj$/time/temporal/ChronoUnit;

    const-wide/32 v6, 0xf423f

    invoke-static {v13, v14, v6, v7}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v2

    const-string v6, "MICRO_OF_SECOND"

    const/4 v7, 0x2

    const-string v8, "MicroOfSecond"

    move-wide/from16 v62, v10

    move-object v11, v2

    move-object v10, v5

    move-object v5, v3

    move-wide/from16 v2, v62

    invoke-direct/range {v5 .. v11}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    move-object v4, v5

    move-object/from16 v20, v19

    move-object/from16 v19, v9

    move-object v9, v10

    sput-object v4, Lj$/time/temporal/a;->MICRO_OF_SECOND:Lj$/time/temporal/a;

    .line 4
    new-instance v15, Lj$/time/temporal/a;

    const-wide v5, 0x141dd75fffL

    invoke-static {v13, v14, v5, v6}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v21

    const-string v16, "MICRO_OF_DAY"

    const/16 v17, 0x3

    const-string v18, "MicroOfDay"

    invoke-direct/range {v15 .. v21}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    move-object/from16 v23, v4

    move-object v4, v15

    move-object/from16 v19, v20

    sput-object v4, Lj$/time/temporal/a;->MICRO_OF_DAY:Lj$/time/temporal/a;

    .line 5
    new-instance v5, Lj$/time/temporal/a;

    sget-object v9, Lj$/time/temporal/ChronoUnit;->MILLIS:Lj$/time/temporal/ChronoUnit;

    const-wide/16 v6, 0x3e7

    invoke-static {v13, v14, v6, v7}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v11

    const-string v6, "MILLI_OF_SECOND"

    const/4 v7, 0x4

    const-string v8, "MilliOfSecond"

    invoke-direct/range {v5 .. v11}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    move-object/from16 v24, v5

    move-object/from16 v19, v9

    move-object v9, v10

    sput-object v24, Lj$/time/temporal/a;->MILLI_OF_SECOND:Lj$/time/temporal/a;

    .line 6
    new-instance v6, Lj$/time/temporal/a;

    const-wide/32 v7, 0x5265bff

    invoke-static {v13, v14, v7, v8}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v21

    const-string v16, "MILLI_OF_DAY"

    const/16 v17, 0x5

    const-string v18, "MilliOfDay"

    move-object v15, v6

    invoke-direct/range {v15 .. v21}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    move-object/from16 v25, v15

    move-object/from16 v19, v20

    sput-object v25, Lj$/time/temporal/a;->MILLI_OF_DAY:Lj$/time/temporal/a;

    .line 7
    new-instance v5, Lj$/time/temporal/a;

    sget-object v10, Lj$/time/temporal/ChronoUnit;->MINUTES:Lj$/time/temporal/ChronoUnit;

    const-wide/16 v6, 0x3b

    invoke-static {v13, v14, v6, v7}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v11

    const/4 v12, 0x0

    move-wide v7, v6

    const-string v6, "SECOND_OF_MINUTE"

    move-wide v15, v7

    const/4 v7, 0x6

    const-string v8, "SecondOfMinute"

    move-wide v2, v15

    invoke-direct/range {v5 .. v12}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;I)V

    move-object v12, v5

    move-object/from16 v30, v10

    sput-object v12, Lj$/time/temporal/a;->SECOND_OF_MINUTE:Lj$/time/temporal/a;

    .line 8
    new-instance v5, Lj$/time/temporal/a;

    const-wide/32 v6, 0x1517f

    invoke-static {v13, v14, v6, v7}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v11

    const-string v6, "SECOND_OF_DAY"

    const/4 v7, 0x7

    const-string v8, "SecondOfDay"

    move-object/from16 v10, v19

    invoke-direct/range {v5 .. v11}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    move-object/from16 v34, v5

    sput-object v34, Lj$/time/temporal/a;->SECOND_OF_DAY:Lj$/time/temporal/a;

    .line 9
    new-instance v26, Lj$/time/temporal/a;

    sget-object v39, Lj$/time/temporal/ChronoUnit;->HOURS:Lj$/time/temporal/ChronoUnit;

    invoke-static {v13, v14, v2, v3}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v32

    const/16 v33, 0x0

    const-string v27, "MINUTE_OF_HOUR"

    const/16 v28, 0x8

    const-string v29, "MinuteOfHour"

    move-object/from16 v31, v39

    invoke-direct/range {v26 .. v33}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;I)V

    sput-object v26, Lj$/time/temporal/a;->MINUTE_OF_HOUR:Lj$/time/temporal/a;

    .line 10
    new-instance v10, Lj$/time/temporal/a;

    const-wide/16 v2, 0x59f

    invoke-static {v13, v14, v2, v3}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v21

    const-string v16, "MINUTE_OF_DAY"

    const/16 v17, 0x9

    const-string v18, "MinuteOfDay"

    move-object v15, v10

    move-object/from16 v19, v30

    invoke-direct/range {v15 .. v21}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    move-object v2, v15

    move-object/from16 v19, v20

    sput-object v2, Lj$/time/temporal/a;->MINUTE_OF_DAY:Lj$/time/temporal/a;

    .line 11
    new-instance v35, Lj$/time/temporal/a;

    sget-object v40, Lj$/time/temporal/ChronoUnit;->HALF_DAYS:Lj$/time/temporal/ChronoUnit;

    const-wide/16 v5, 0xb

    invoke-static {v13, v14, v5, v6}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v41

    const-string v36, "HOUR_OF_AMPM"

    const/16 v37, 0xa

    const-string v38, "HourOfAmPm"

    invoke-direct/range {v35 .. v41}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    move-object/from16 v3, v35

    sput-object v3, Lj$/time/temporal/a;->HOUR_OF_AMPM:Lj$/time/temporal/a;

    .line 12
    new-instance v35, Lj$/time/temporal/a;

    const-wide/16 v5, 0x1

    const-wide/16 v7, 0xc

    invoke-static {v5, v6, v7, v8}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v41

    const-string v36, "CLOCK_HOUR_OF_AMPM"

    const/16 v37, 0xb

    const-string v38, "ClockHourOfAmPm"

    invoke-direct/range {v35 .. v41}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    sput-object v35, Lj$/time/temporal/a;->CLOCK_HOUR_OF_AMPM:Lj$/time/temporal/a;

    .line 13
    new-instance v15, Lj$/time/temporal/a;

    const-wide/16 v10, 0x17

    invoke-static {v13, v14, v10, v11}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v21

    const/16 v22, 0x0

    const-string v16, "HOUR_OF_DAY"

    const/16 v17, 0xc

    const-string v18, "HourOfDay"

    move-object/from16 v19, v39

    invoke-direct/range {v15 .. v22}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;I)V

    move-object/from16 v27, v15

    move-object/from16 v19, v20

    sput-object v27, Lj$/time/temporal/a;->HOUR_OF_DAY:Lj$/time/temporal/a;

    .line 14
    new-instance v15, Lj$/time/temporal/a;

    const-wide/16 v10, 0x18

    invoke-static {v5, v6, v10, v11}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v21

    const-string v16, "CLOCK_HOUR_OF_DAY"

    const/16 v17, 0xd

    const-string v18, "ClockHourOfDay"

    move-object/from16 v19, v39

    invoke-direct/range {v15 .. v21}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    move-object/from16 v28, v15

    move-object/from16 v19, v20

    sput-object v28, Lj$/time/temporal/a;->CLOCK_HOUR_OF_DAY:Lj$/time/temporal/a;

    .line 15
    new-instance v15, Lj$/time/temporal/a;

    invoke-static {v13, v14, v5, v6}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v21

    const-string v16, "AMPM_OF_DAY"

    const/16 v17, 0xe

    const-string v18, "AmPmOfDay"

    move-object/from16 v19, v40

    invoke-direct/range {v15 .. v22}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;I)V

    move-object/from16 v29, v15

    move-object/from16 v19, v20

    sput-object v29, Lj$/time/temporal/a;->AMPM_OF_DAY:Lj$/time/temporal/a;

    .line 16
    new-instance v16, Lj$/time/temporal/a;

    sget-object v20, Lj$/time/temporal/ChronoUnit;->WEEKS:Lj$/time/temporal/ChronoUnit;

    const-wide/16 v10, 0x7

    invoke-static {v5, v6, v10, v11}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v21

    move-object/from16 v15, v16

    const-string v16, "DAY_OF_WEEK"

    const/16 v17, 0xf

    const-string v18, "DayOfWeek"

    invoke-direct/range {v15 .. v22}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;I)V

    move-object/from16 v30, v15

    sput-object v30, Lj$/time/temporal/a;->DAY_OF_WEEK:Lj$/time/temporal/a;

    .line 17
    new-instance v17, Lj$/time/temporal/a;

    const-string v18, "AlignedDayOfWeekInMonth"

    invoke-static {v5, v6, v10, v11}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v21

    const-string v16, "ALIGNED_DAY_OF_WEEK_IN_MONTH"

    move-object/from16 v15, v17

    const/16 v17, 0x10

    invoke-direct/range {v15 .. v21}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    move-object/from16 v31, v15

    sput-object v31, Lj$/time/temporal/a;->ALIGNED_DAY_OF_WEEK_IN_MONTH:Lj$/time/temporal/a;

    .line 18
    new-instance v18, Lj$/time/temporal/a;

    move-object/from16 v15, v18

    const-string v18, "AlignedDayOfWeekInYear"

    invoke-static {v5, v6, v10, v11}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v21

    const-string v16, "ALIGNED_DAY_OF_WEEK_IN_YEAR"

    const/16 v17, 0x11

    invoke-direct/range {v15 .. v21}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    move-object/from16 v32, v15

    move-object/from16 v40, v20

    sput-object v32, Lj$/time/temporal/a;->ALIGNED_DAY_OF_WEEK_IN_YEAR:Lj$/time/temporal/a;

    .line 19
    new-instance v15, Lj$/time/temporal/a;

    sget-object v45, Lj$/time/temporal/ChronoUnit;->MONTHS:Lj$/time/temporal/ChronoUnit;

    const-wide/16 v10, 0x1c

    const-wide/16 v13, 0x1f

    .line 20
    invoke-static {v10, v11, v13, v14}, Lj$/time/temporal/s;->g(JJ)Lj$/time/temporal/s;

    move-result-object v21

    .line 21
    const-string v16, "DAY_OF_MONTH"

    const/16 v17, 0x12

    const-string v18, "DayOfMonth"

    move-object/from16 v20, v45

    invoke-direct/range {v15 .. v22}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;I)V

    move-object v13, v15

    sput-object v13, Lj$/time/temporal/a;->DAY_OF_MONTH:Lj$/time/temporal/a;

    .line 22
    new-instance v20, Lj$/time/temporal/a;

    sget-object v53, Lj$/time/temporal/ChronoUnit;->YEARS:Lj$/time/temporal/ChronoUnit;

    const-wide/16 v10, 0x16d

    const-wide/16 v14, 0x16e

    .line 23
    invoke-static {v10, v11, v14, v15}, Lj$/time/temporal/s;->g(JJ)Lj$/time/temporal/s;

    move-result-object v21

    .line 24
    const-string v16, "DAY_OF_YEAR"

    const/16 v17, 0x13

    const-string v18, "DayOfYear"

    move-object/from16 v15, v20

    move-object/from16 v20, v53

    invoke-direct/range {v15 .. v21}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    move-object v14, v15

    sput-object v14, Lj$/time/temporal/a;->DAY_OF_YEAR:Lj$/time/temporal/a;

    .line 25
    new-instance v21, Lj$/time/temporal/a;

    sget-object v54, Lj$/time/temporal/ChronoUnit;->FOREVER:Lj$/time/temporal/ChronoUnit;

    const-wide v10, -0x550a313cdaL

    const-wide v7, 0x550a1b48f7L

    invoke-static {v10, v11, v7, v8}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v7

    const-string v16, "EPOCH_DAY"

    const/16 v17, 0x14

    const-string v18, "EpochDay"

    move-object/from16 v15, v21

    move-object/from16 v20, v54

    move-object/from16 v21, v7

    invoke-direct/range {v15 .. v21}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    move-object/from16 v21, v15

    sput-object v21, Lj$/time/temporal/a;->EPOCH_DAY:Lj$/time/temporal/a;

    .line 26
    new-instance v36, Lj$/time/temporal/a;

    const-wide/16 v7, 0x4

    const-wide/16 v10, 0x5

    .line 27
    invoke-static {v7, v8, v10, v11}, Lj$/time/temporal/s;->g(JJ)Lj$/time/temporal/s;

    move-result-object v42

    .line 28
    const-string v37, "ALIGNED_WEEK_OF_MONTH"

    const/16 v38, 0x15

    const-string v39, "AlignedWeekOfMonth"

    move-object/from16 v41, v45

    invoke-direct/range {v36 .. v42}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    move-object/from16 v22, v36

    move-object/from16 v20, v40

    sput-object v22, Lj$/time/temporal/a;->ALIGNED_WEEK_OF_MONTH:Lj$/time/temporal/a;

    .line 29
    new-instance v36, Lj$/time/temporal/a;

    const-wide/16 v7, 0x35

    invoke-static {v5, v6, v7, v8}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v42

    const-string v37, "ALIGNED_WEEK_OF_YEAR"

    const/16 v38, 0x16

    const-string v39, "AlignedWeekOfYear"

    move-object/from16 v41, v53

    invoke-direct/range {v36 .. v42}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    sput-object v36, Lj$/time/temporal/a;->ALIGNED_WEEK_OF_YEAR:Lj$/time/temporal/a;

    .line 30
    new-instance v41, Lj$/time/temporal/a;

    const-wide/16 v7, 0xc

    invoke-static {v5, v6, v7, v8}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v47

    const/16 v48, 0x0

    const-string v42, "MONTH_OF_YEAR"

    const/16 v43, 0x17

    const-string v44, "MonthOfYear"

    move-object/from16 v46, v53

    invoke-direct/range {v41 .. v48}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;I)V

    move-object/from16 v15, v24

    move-object/from16 v24, v41

    sput-object v24, Lj$/time/temporal/a;->MONTH_OF_YEAR:Lj$/time/temporal/a;

    .line 31
    new-instance v41, Lj$/time/temporal/a;

    const-wide v7, -0x2cb4177f4L

    const-wide v10, 0x2cb4177ffL

    invoke-static {v7, v8, v10, v11}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v47

    const-string v42, "PROLEPTIC_MONTH"

    const/16 v43, 0x18

    const-string v44, "ProlepticMonth"

    move-object/from16 v46, v54

    invoke-direct/range {v41 .. v47}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    sput-object v41, Lj$/time/temporal/a;->PROLEPTIC_MONTH:Lj$/time/temporal/a;

    .line 32
    new-instance v49, Lj$/time/temporal/a;

    const-wide/32 v7, 0x3b9aca00

    const-wide/32 v10, 0x3b9ac9ff

    .line 33
    invoke-static {v10, v11, v7, v8}, Lj$/time/temporal/s;->g(JJ)Lj$/time/temporal/s;

    move-result-object v55

    .line 34
    const-string v50, "YEAR_OF_ERA"

    const/16 v51, 0x19

    const-string v52, "YearOfEra"

    invoke-direct/range {v49 .. v55}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    move-object/from16 v16, v26

    move-object/from16 v26, v49

    sput-object v26, Lj$/time/temporal/a;->YEAR_OF_ERA:Lj$/time/temporal/a;

    .line 35
    new-instance v49, Lj$/time/temporal/a;

    const-wide/32 v7, -0x3b9ac9ff

    invoke-static {v7, v8, v10, v11}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v55

    const/16 v56, 0x0

    const-string v50, "YEAR"

    const/16 v51, 0x1a

    const-string v52, "Year"

    invoke-direct/range {v49 .. v56}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;I)V

    sput-object v49, Lj$/time/temporal/a;->YEAR:Lj$/time/temporal/a;

    move-object/from16 v10, v54

    .line 36
    new-instance v54, Lj$/time/temporal/a;

    sget-object v58, Lj$/time/temporal/ChronoUnit;->ERAS:Lj$/time/temporal/ChronoUnit;

    const-wide/16 v7, 0x0

    invoke-static {v7, v8, v5, v6}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v60

    const/16 v61, 0x0

    const-string v55, "ERA"

    const/16 v56, 0x1b

    const-string v57, "Era"

    move-object/from16 v59, v10

    invoke-direct/range {v54 .. v61}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;I)V

    move-object/from16 v20, v14

    move-object/from16 v14, v28

    move-object/from16 v28, v54

    move-object/from16 v54, v59

    sput-object v28, Lj$/time/temporal/a;->ERA:Lj$/time/temporal/a;

    .line 37
    new-instance v5, Lj$/time/temporal/a;

    const-wide/high16 v6, -0x8000000000000000L

    const-wide v10, 0x7fffffffffffffffL

    invoke-static {v6, v7, v10, v11}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v11

    const-string v6, "INSTANT_SECONDS"

    const/16 v7, 0x1c

    const-string v8, "InstantSeconds"

    move-object/from16 v10, v54

    invoke-direct/range {v5 .. v11}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    move-object/from16 v17, v15

    move-object/from16 v15, v29

    move-object/from16 v29, v5

    sput-object v29, Lj$/time/temporal/a;->INSTANT_SECONDS:Lj$/time/temporal/a;

    .line 38
    new-instance v5, Lj$/time/temporal/a;

    const-wide/32 v6, -0xfd20

    const-wide/32 v10, 0xfd20

    invoke-static {v6, v7, v10, v11}, Lj$/time/temporal/s;->f(JJ)Lj$/time/temporal/s;

    move-result-object v11

    const-string v6, "OFFSET_SECONDS"

    const/16 v7, 0x1d

    const-string v8, "OffsetSeconds"

    move-object/from16 v10, v54

    invoke-direct/range {v5 .. v11}, Lj$/time/temporal/a;-><init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V

    sput-object v5, Lj$/time/temporal/a;->OFFSET_SECONDS:Lj$/time/temporal/a;

    move-object v10, v2

    move-object v11, v3

    move-object v7, v12

    move-object/from16 v19, v13

    move-object/from16 v9, v16

    move-object/from16 v3, v23

    move-object/from16 v6, v25

    move-object/from16 v13, v27

    move-object/from16 v16, v30

    move-object/from16 v18, v32

    move-object/from16 v8, v34

    move-object/from16 v12, v35

    move-object/from16 v23, v36

    move-object/from16 v25, v41

    move-object/from16 v27, v49

    move-object v2, v1

    move-object/from16 v30, v5

    move-object/from16 v5, v17

    move-object/from16 v17, v31

    move-object v1, v0

    .line 39
    filled-new-array/range {v1 .. v30}, [Lj$/time/temporal/a;

    move-result-object v0

    sput-object v0, Lj$/time/temporal/a;->c:[Lj$/time/temporal/a;

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;)V
    .locals 0

    .line 1
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    .line 2
    .line 3
    .line 4
    iput-object p3, p0, Lj$/time/temporal/a;->a:Ljava/lang/String;

    .line 5
    .line 6
    iput-object p6, p0, Lj$/time/temporal/a;->b:Lj$/time/temporal/s;

    .line 7
    .line 8
    return-void
.end method

.method public constructor <init>(Ljava/lang/String;ILjava/lang/String;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/ChronoUnit;Lj$/time/temporal/s;I)V
    .locals 0

    .line 9
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    .line 10
    iput-object p3, p0, Lj$/time/temporal/a;->a:Ljava/lang/String;

    .line 11
    iput-object p6, p0, Lj$/time/temporal/a;->b:Lj$/time/temporal/s;

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lj$/time/temporal/a;
    .locals 1

    .line 1
    const-class v0, Lj$/time/temporal/a;

    .line 2
    .line 3
    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    .line 4
    .line 5
    .line 6
    move-result-object p0

    .line 7
    check-cast p0, Lj$/time/temporal/a;

    .line 8
    .line 9
    return-object p0
.end method

.method public static values()[Lj$/time/temporal/a;
    .locals 1

    .line 1
    sget-object v0, Lj$/time/temporal/a;->c:[Lj$/time/temporal/a;

    .line 2
    .line 3
    invoke-virtual {v0}, [Lj$/time/temporal/a;->clone()Ljava/lang/Object;

    .line 4
    .line 5
    .line 6
    move-result-object v0

    .line 7
    check-cast v0, [Lj$/time/temporal/a;

    .line 8
    .line 9
    return-object v0
.end method


# virtual methods
.method public final D(Lj$/time/temporal/l;)Lj$/time/temporal/s;
    .locals 0

    .line 1
    invoke-interface {p1, p0}, Lj$/time/temporal/l;->l(Lj$/time/temporal/p;)Lj$/time/temporal/s;

    .line 2
    .line 3
    .line 4
    move-result-object p0

    .line 5
    return-object p0
.end method

.method public final K()Lj$/time/temporal/s;
    .locals 0

    .line 1
    iget-object p0, p0, Lj$/time/temporal/a;->b:Lj$/time/temporal/s;

    .line 2
    .line 3
    return-object p0
.end method

.method public final S(Lj$/time/temporal/l;)J
    .locals 0

    .line 1
    invoke-interface {p1, p0}, Lj$/time/temporal/l;->k(Lj$/time/temporal/p;)J

    .line 2
    .line 3
    .line 4
    move-result-wide p0

    .line 5
    return-wide p0
.end method

.method public final Y(Lj$/time/temporal/Temporal;J)Lj$/time/temporal/Temporal;
    .locals 0

    .line 1
    invoke-interface {p1, p2, p3, p0}, Lj$/time/temporal/Temporal;->a(JLj$/time/temporal/p;)Lj$/time/temporal/Temporal;

    .line 2
    .line 3
    .line 4
    move-result-object p0

    .line 5
    return-object p0
.end method

.method public final a0(J)V
    .locals 1

    .line 1
    iget-object v0, p0, Lj$/time/temporal/a;->b:Lj$/time/temporal/s;

    .line 2
    .line 3
    invoke-virtual {v0, p1, p2, p0}, Lj$/time/temporal/s;->b(JLj$/time/temporal/p;)V

    .line 4
    .line 5
    .line 6
    return-void
.end method

.method public final b0()Z
    .locals 1

    .line 1
    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    .line 2
    .line 3
    .line 4
    move-result p0

    .line 5
    sget-object v0, Lj$/time/temporal/a;->DAY_OF_WEEK:Lj$/time/temporal/a;

    .line 6
    .line 7
    invoke-virtual {v0}, Ljava/lang/Enum;->ordinal()I

    .line 8
    .line 9
    .line 10
    move-result v0

    .line 11
    if-ge p0, v0, :cond_0

    .line 12
    .line 13
    const/4 p0, 0x1

    .line 14
    return p0

    .line 15
    :cond_0
    const/4 p0, 0x0

    .line 16
    return p0
.end method

.method public final isDateBased()Z
    .locals 2

    .line 1
    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    .line 2
    .line 3
    .line 4
    move-result v0

    .line 5
    sget-object v1, Lj$/time/temporal/a;->DAY_OF_WEEK:Lj$/time/temporal/a;

    .line 6
    .line 7
    invoke-virtual {v1}, Ljava/lang/Enum;->ordinal()I

    .line 8
    .line 9
    .line 10
    move-result v1

    .line 11
    if-lt v0, v1, :cond_0

    .line 12
    .line 13
    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    .line 14
    .line 15
    .line 16
    move-result p0

    .line 17
    sget-object v0, Lj$/time/temporal/a;->ERA:Lj$/time/temporal/a;

    .line 18
    .line 19
    invoke-virtual {v0}, Ljava/lang/Enum;->ordinal()I

    .line 20
    .line 21
    .line 22
    move-result v0

    .line 23
    if-gt p0, v0, :cond_0

    .line 24
    .line 25
    const/4 p0, 0x1

    .line 26
    return p0

    .line 27
    :cond_0
    const/4 p0, 0x0

    .line 28
    return p0
.end method

.method public final s(Lj$/time/temporal/l;)Z
    .locals 0

    .line 1
    invoke-interface {p1, p0}, Lj$/time/temporal/l;->i(Lj$/time/temporal/p;)Z

    .line 2
    .line 3
    .line 4
    move-result p0

    .line 5
    return p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 0

    .line 1
    iget-object p0, p0, Lj$/time/temporal/a;->a:Ljava/lang/String;

    .line 2
    .line 3
    return-object p0
.end method
