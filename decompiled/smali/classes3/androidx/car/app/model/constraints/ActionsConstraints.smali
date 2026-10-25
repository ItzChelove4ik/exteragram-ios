.class public final Landroidx/car/app/model/constraints/ActionsConstraints;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/car/app/model/constraints/ActionsConstraints$Builder;
    }
.end annotation


# static fields
.field public static final ACTIONS_CONSTRAINTS_BODY:Landroidx/car/app/model/constraints/ActionsConstraints;

.field public static final ACTIONS_CONSTRAINTS_BODY_WITH_PRIMARY_ACTION:Landroidx/car/app/model/constraints/ActionsConstraints;

.field private static final ACTIONS_CONSTRAINTS_CONSERVATIVE:Landroidx/car/app/model/constraints/ActionsConstraints;

.field public static final ACTIONS_CONSTRAINTS_CONVERSATION_ITEM:Landroidx/car/app/model/constraints/ActionsConstraints;

.field public static final ACTIONS_CONSTRAINTS_FAB:Landroidx/car/app/model/constraints/ActionsConstraints;
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "UnsafeOptInUsageError"
        }
    .end annotation
.end field

.field public static final ACTIONS_CONSTRAINTS_HEADER:Landroidx/car/app/model/constraints/ActionsConstraints;

.field public static final ACTIONS_CONSTRAINTS_MAP:Landroidx/car/app/model/constraints/ActionsConstraints;

.field public static final ACTIONS_CONSTRAINTS_MULTI_HEADER:Landroidx/car/app/model/constraints/ActionsConstraints;

.field public static final ACTIONS_CONSTRAINTS_NAVIGATION:Landroidx/car/app/model/constraints/ActionsConstraints;

.field public static final ACTIONS_CONSTRAINTS_ROW:Landroidx/car/app/model/constraints/ActionsConstraints;

.field public static final ACTIONS_CONSTRAINTS_SIMPLE:Landroidx/car/app/model/constraints/ActionsConstraints;

.field public static final ACTIONS_CONSTRAINTS_TABS:Landroidx/car/app/model/constraints/ActionsConstraints;


# instance fields
.field private final mAllowedActionTypes:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private final mDisallowedActionTypes:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private final mMaxActions:I

.field private final mMaxCustomTitles:I

.field private final mMaxPrimaryActions:I

.field private final mOnClickListenerAllowed:Z

.field private final mRequireActionBackgroundColor:Z

.field private final mRequireActionIcons:Z

.field private final mRequiredActionTypes:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private final mRestrictBackgroundColorToPrimaryAction:Z

.field private final mTitleTextConstraints:Landroidx/car/app/model/constraints/CarTextConstraints;


# direct methods
.method static constructor <clinit>()V
    .locals 7

    .line 52
    new-instance v0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    invoke-direct {v0}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;-><init>()V

    const/4 v1, 0x1

    .line 54
    invoke-virtual {v0, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setMaxActions(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v0

    .line 55
    invoke-virtual {v0, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setRequireActionIcons(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v0

    const/4 v2, 0x0

    .line 56
    invoke-virtual {v0, v2}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setOnClickListenerAllowed(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v0

    .line 57
    invoke-virtual {v0}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->build()Landroidx/car/app/model/constraints/ActionsConstraints;

    move-result-object v0

    sput-object v0, Landroidx/car/app/model/constraints/ActionsConstraints;->ACTIONS_CONSTRAINTS_HEADER:Landroidx/car/app/model/constraints/ActionsConstraints;

    .line 63
    new-instance v2, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    invoke-direct {v2}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;-><init>()V

    const/4 v3, 0x2

    .line 65
    invoke-virtual {v2, v3}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setMaxActions(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 66
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setRequireActionIcons(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 67
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setOnClickListenerAllowed(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 68
    invoke-virtual {v2}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->build()Landroidx/car/app/model/constraints/ActionsConstraints;

    move-result-object v2

    sput-object v2, Landroidx/car/app/model/constraints/ActionsConstraints;->ACTIONS_CONSTRAINTS_MULTI_HEADER:Landroidx/car/app/model/constraints/ActionsConstraints;

    .line 71
    new-instance v2, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    invoke-direct {v2}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;-><init>()V

    sget-object v4, Landroidx/car/app/model/constraints/CarTextConstraints;->CONSERVATIVE:Landroidx/car/app/model/constraints/CarTextConstraints;

    .line 73
    invoke-virtual {v2, v4}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setTitleTextConstraints(Landroidx/car/app/model/constraints/CarTextConstraints;)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 74
    invoke-virtual {v2, v3}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setMaxActions(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 75
    invoke-virtual {v2}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->build()Landroidx/car/app/model/constraints/ActionsConstraints;

    move-result-object v2

    sput-object v2, Landroidx/car/app/model/constraints/ActionsConstraints;->ACTIONS_CONSTRAINTS_CONSERVATIVE:Landroidx/car/app/model/constraints/ActionsConstraints;

    .line 80
    new-instance v4, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    invoke-direct {v4, v2}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;-><init>(Landroidx/car/app/model/constraints/ActionsConstraints;)V

    sget-object v5, Landroidx/car/app/model/constraints/CarTextConstraints;->COLOR_ONLY:Landroidx/car/app/model/constraints/CarTextConstraints;

    .line 82
    invoke-virtual {v4, v5}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setTitleTextConstraints(Landroidx/car/app/model/constraints/CarTextConstraints;)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v4

    .line 83
    invoke-virtual {v4, v3}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setMaxCustomTitles(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v4

    .line 84
    invoke-virtual {v4, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setOnClickListenerAllowed(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v4

    .line 85
    invoke-virtual {v4}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->build()Landroidx/car/app/model/constraints/ActionsConstraints;

    move-result-object v4

    sput-object v4, Landroidx/car/app/model/constraints/ActionsConstraints;->ACTIONS_CONSTRAINTS_BODY:Landroidx/car/app/model/constraints/ActionsConstraints;

    .line 91
    new-instance v4, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    invoke-direct {v4, v2}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;-><init>(Landroidx/car/app/model/constraints/ActionsConstraints;)V

    .line 93
    invoke-virtual {v4, v5}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setTitleTextConstraints(Landroidx/car/app/model/constraints/CarTextConstraints;)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v4

    .line 94
    invoke-virtual {v4, v3}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setMaxCustomTitles(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v4

    .line 95
    invoke-virtual {v4, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setMaxPrimaryActions(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v4

    .line 96
    invoke-virtual {v4, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setOnClickListenerAllowed(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v4

    .line 97
    invoke-virtual {v4}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->build()Landroidx/car/app/model/constraints/ActionsConstraints;

    move-result-object v4

    sput-object v4, Landroidx/car/app/model/constraints/ActionsConstraints;->ACTIONS_CONSTRAINTS_BODY_WITH_PRIMARY_ACTION:Landroidx/car/app/model/constraints/ActionsConstraints;

    .line 103
    new-instance v4, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    invoke-direct {v4, v2}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;-><init>(Landroidx/car/app/model/constraints/ActionsConstraints;)V

    .line 105
    invoke-virtual {v4, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setMaxCustomTitles(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v4

    sget-object v5, Landroidx/car/app/model/constraints/CarTextConstraints;->TEXT_ONLY:Landroidx/car/app/model/constraints/CarTextConstraints;

    .line 106
    invoke-virtual {v4, v5}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setTitleTextConstraints(Landroidx/car/app/model/constraints/CarTextConstraints;)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v4

    .line 107
    invoke-virtual {v4, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setOnClickListenerAllowed(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v4

    .line 108
    invoke-virtual {v4, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setRestrictBackgroundColorToPrimaryAction(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v4

    .line 109
    invoke-virtual {v4}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->build()Landroidx/car/app/model/constraints/ActionsConstraints;

    move-result-object v4

    sput-object v4, Landroidx/car/app/model/constraints/ActionsConstraints;->ACTIONS_CONSTRAINTS_SIMPLE:Landroidx/car/app/model/constraints/ActionsConstraints;

    .line 113
    new-instance v4, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    invoke-direct {v4, v2}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;-><init>(Landroidx/car/app/model/constraints/ActionsConstraints;)V

    const/4 v5, 0x4

    .line 115
    invoke-virtual {v4, v5}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setMaxActions(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v4

    .line 116
    invoke-virtual {v4, v5}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setMaxCustomTitles(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v4

    .line 117
    invoke-virtual {v4, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setMaxPrimaryActions(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v4

    sget-object v6, Landroidx/car/app/model/constraints/CarTextConstraints;->TEXT_AND_ICON:Landroidx/car/app/model/constraints/CarTextConstraints;

    .line 118
    invoke-virtual {v4, v6}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setTitleTextConstraints(Landroidx/car/app/model/constraints/CarTextConstraints;)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v4

    .line 119
    invoke-virtual {v4, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setOnClickListenerAllowed(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v4

    .line 120
    invoke-virtual {v4, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setRestrictBackgroundColorToPrimaryAction(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v4

    .line 121
    invoke-virtual {v4}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->build()Landroidx/car/app/model/constraints/ActionsConstraints;

    move-result-object v4

    sput-object v4, Landroidx/car/app/model/constraints/ActionsConstraints;->ACTIONS_CONSTRAINTS_NAVIGATION:Landroidx/car/app/model/constraints/ActionsConstraints;

    .line 129
    new-instance v4, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    invoke-direct {v4, v2}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;-><init>(Landroidx/car/app/model/constraints/ActionsConstraints;)V

    .line 131
    invoke-virtual {v4, v5}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setMaxActions(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 132
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setMaxPrimaryActions(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 133
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setOnClickListenerAllowed(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 134
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setRestrictBackgroundColorToPrimaryAction(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 135
    invoke-virtual {v2}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->build()Landroidx/car/app/model/constraints/ActionsConstraints;

    move-result-object v2

    sput-object v2, Landroidx/car/app/model/constraints/ActionsConstraints;->ACTIONS_CONSTRAINTS_MAP:Landroidx/car/app/model/constraints/ActionsConstraints;

    .line 142
    new-instance v2, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    invoke-direct {v2}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;-><init>()V

    .line 144
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setMaxActions(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 145
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setMaxCustomTitles(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 146
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->addAllowedActionType(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 147
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setRequireActionIcons(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 148
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setOnClickListenerAllowed(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 149
    invoke-virtual {v2}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->build()Landroidx/car/app/model/constraints/ActionsConstraints;

    move-result-object v2

    sput-object v2, Landroidx/car/app/model/constraints/ActionsConstraints;->ACTIONS_CONSTRAINTS_ROW:Landroidx/car/app/model/constraints/ActionsConstraints;

    .line 155
    new-instance v2, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    invoke-direct {v2}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;-><init>()V

    .line 157
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setMaxActions(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 158
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setMaxCustomTitles(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 159
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->addAllowedActionType(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 160
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setRequireActionIcons(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 161
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setOnClickListenerAllowed(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 162
    invoke-virtual {v2}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->build()Landroidx/car/app/model/constraints/ActionsConstraints;

    move-result-object v2

    sput-object v2, Landroidx/car/app/model/constraints/ActionsConstraints;->ACTIONS_CONSTRAINTS_CONVERSATION_ITEM:Landroidx/car/app/model/constraints/ActionsConstraints;

    .line 171
    new-instance v2, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    invoke-direct {v2}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;-><init>()V

    .line 173
    invoke-virtual {v2, v3}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setMaxActions(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 174
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->addAllowedActionType(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    const v3, 0x10005

    .line 175
    invoke-virtual {v2, v3}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->addAllowedActionType(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 176
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setRequireActionIcons(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 177
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setRequireActionBackgroundColor(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v2

    .line 178
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->setOnClickListenerAllowed(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v1

    .line 179
    invoke-virtual {v1}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->build()Landroidx/car/app/model/constraints/ActionsConstraints;

    move-result-object v1

    sput-object v1, Landroidx/car/app/model/constraints/ActionsConstraints;->ACTIONS_CONSTRAINTS_FAB:Landroidx/car/app/model/constraints/ActionsConstraints;

    .line 184
    new-instance v1, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    invoke-direct {v1, v0}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;-><init>(Landroidx/car/app/model/constraints/ActionsConstraints;)V

    const v0, 0x10002

    .line 186
    invoke-virtual {v1, v0}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->addRequiredActionType(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;

    move-result-object v0

    .line 187
    invoke-virtual {v0}, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->build()Landroidx/car/app/model/constraints/ActionsConstraints;

    move-result-object v0

    sput-object v0, Landroidx/car/app/model/constraints/ActionsConstraints;->ACTIONS_CONSTRAINTS_TABS:Landroidx/car/app/model/constraints/ActionsConstraints;

    return-void
.end method

.method public constructor <init>(Landroidx/car/app/model/constraints/ActionsConstraints$Builder;)V
    .locals 5

    .line 201
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 202
    iget v0, p1, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mMaxActions:I

    iput v0, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mMaxActions:I

    .line 203
    iget v1, p1, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mMaxPrimaryActions:I

    iput v1, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mMaxPrimaryActions:I

    .line 204
    iget v1, p1, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mMaxCustomTitles:I

    iput v1, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mMaxCustomTitles:I

    .line 205
    iget-object v1, p1, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mTitleTextConstraints:Landroidx/car/app/model/constraints/CarTextConstraints;

    iput-object v1, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mTitleTextConstraints:Landroidx/car/app/model/constraints/CarTextConstraints;

    .line 206
    iget-boolean v1, p1, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mRequireActionIcons:Z

    iput-boolean v1, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mRequireActionIcons:Z

    .line 207
    iget-boolean v1, p1, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mRequireActionBackgroundColor:Z

    iput-boolean v1, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mRequireActionBackgroundColor:Z

    .line 208
    iget-boolean v1, p1, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mOnClickListenerAllowed:Z

    iput-boolean v1, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mOnClickListenerAllowed:Z

    .line 209
    iget-boolean v1, p1, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mRestrictBackgroundColorToPrimaryAction:Z

    iput-boolean v1, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mRestrictBackgroundColorToPrimaryAction:Z

    .line 210
    new-instance v1, Ljava/util/HashSet;

    iget-object v2, p1, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mRequiredActionTypes:Ljava/util/Set;

    invoke-direct {v1, v2}, Ljava/util/HashSet;-><init>(Ljava/util/Collection;)V

    iput-object v1, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mRequiredActionTypes:Ljava/util/Set;

    .line 211
    new-instance v2, Ljava/util/HashSet;

    iget-object v3, p1, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mAllowedActionTypes:Ljava/util/Set;

    invoke-direct {v2, v3}, Ljava/util/HashSet;-><init>(Ljava/util/Collection;)V

    iput-object v2, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mAllowedActionTypes:Ljava/util/Set;

    .line 213
    new-instance v3, Ljava/util/HashSet;

    iget-object v4, p1, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mDisallowedActionTypes:Ljava/util/Set;

    invoke-direct {v3, v4}, Ljava/util/HashSet;-><init>(Ljava/util/Collection;)V

    .line 214
    invoke-interface {v3, v1}, Ljava/util/Set;->retainAll(Ljava/util/Collection;)Z

    .line 215
    invoke-interface {v3}, Ljava/util/Set;->isEmpty()Z

    move-result v3

    const/4 v4, 0x0

    if-eqz v3, :cond_3

    .line 220
    iget-object v3, p1, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mDisallowedActionTypes:Ljava/util/Set;

    invoke-interface {v3}, Ljava/util/Set;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_1

    invoke-interface {v2}, Ljava/util/Set;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_0

    goto :goto_0

    .line 221
    :cond_0
    const-string p0, "Both disallowed and allowed action type set cannot be defined."

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw v4

    .line 225
    :cond_1
    :goto_0
    new-instance v2, Ljava/util/HashSet;

    iget-object p1, p1, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mDisallowedActionTypes:Ljava/util/Set;

    invoke-direct {v2, p1}, Ljava/util/HashSet;-><init>(Ljava/util/Collection;)V

    iput-object v2, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mDisallowedActionTypes:Ljava/util/Set;

    .line 227
    invoke-interface {v1}, Ljava/util/Set;->size()I

    move-result p0

    if-gt p0, v0, :cond_2

    return-void

    .line 228
    :cond_2
    const-string p0, "Required action types exceeded max allowed actions"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw v4

    .line 216
    :cond_3
    const-string p0, "Disallowed action types cannot also be in the required set"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw v4
.end method


# virtual methods
.method public areActionIconsRequired()Z
    .locals 0

    .line 277
    iget-boolean p0, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mRequireActionIcons:Z

    return p0
.end method

.method public getAllowedActionTypes()Ljava/util/Set;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Set<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation

    .line 269
    iget-object p0, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mAllowedActionTypes:Ljava/util/Set;

    return-object p0
.end method

.method public getDisallowedActionTypes()Ljava/util/Set;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Set<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation

    .line 263
    iget-object p0, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mDisallowedActionTypes:Ljava/util/Set;

    return-object p0
.end method

.method public getMaxActions()I
    .locals 0

    .line 235
    iget p0, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mMaxActions:I

    return p0
.end method

.method public getMaxCustomTitles()I
    .locals 0

    .line 245
    iget p0, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mMaxCustomTitles:I

    return p0
.end method

.method public getMaxPrimaryActions()I
    .locals 0

    .line 240
    iget p0, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mMaxPrimaryActions:I

    return p0
.end method

.method public getRequiredActionTypes()Ljava/util/Set;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Set<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation

    .line 257
    iget-object p0, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mRequiredActionTypes:Ljava/util/Set;

    return-object p0
.end method

.method public getTitleTextConstraints()Landroidx/car/app/model/constraints/CarTextConstraints;
    .locals 0

    .line 251
    iget-object p0, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mTitleTextConstraints:Landroidx/car/app/model/constraints/CarTextConstraints;

    return-object p0
.end method

.method public isActionBackgroundColorRequired()Z
    .locals 0

    .line 285
    iget-boolean p0, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mRequireActionBackgroundColor:Z

    return p0
.end method

.method public isOnClickListenerAllowed()Z
    .locals 0

    .line 290
    iget-boolean p0, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mOnClickListenerAllowed:Z

    return p0
.end method

.method public restrictBackgroundColorToPrimaryAction()Z
    .locals 0

    .line 295
    iget-boolean p0, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mRestrictBackgroundColorToPrimaryAction:Z

    return p0
.end method

.method public validateOrThrow(Ljava/util/List;)V
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Landroidx/car/app/model/Action;",
            ">;)V"
        }
    .end annotation

    .line 308
    iget v0, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mMaxActions:I

    .line 309
    iget v1, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mMaxPrimaryActions:I

    .line 310
    iget v2, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mMaxCustomTitles:I

    .line 313
    iget-object v3, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mRequiredActionTypes:Ljava/util/Set;

    invoke-interface {v3}, Ljava/util/Set;->isEmpty()Z

    move-result v3

    if-eqz v3, :cond_0

    .line 314
    sget-object v3, Ljava/util/Collections;->EMPTY_SET:Ljava/util/Set;

    goto :goto_0

    .line 315
    :cond_0
    new-instance v3, Ljava/util/HashSet;

    iget-object v4, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mRequiredActionTypes:Ljava/util/Set;

    invoke-direct {v3, v4}, Ljava/util/HashSet;-><init>(Ljava/util/Collection;)V

    .line 317
    :goto_0
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_1
    :goto_1
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_13

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Landroidx/car/app/model/Action;

    .line 318
    iget-object v5, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mDisallowedActionTypes:Ljava/util/Set;

    invoke-interface {v5}, Ljava/util/Set;->isEmpty()Z

    move-result v5

    if-nez v5, :cond_3

    iget-object v5, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mDisallowedActionTypes:Ljava/util/Set;

    .line 319
    invoke-virtual {v4}, Landroidx/car/app/model/Action;->getType()I

    move-result v6

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    invoke-interface {v5, v6}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_2

    goto :goto_2

    .line 320
    :cond_2
    new-instance p0, Ljava/lang/IllegalArgumentException;

    .line 321
    invoke-virtual {v4}, Landroidx/car/app/model/Action;->getType()I

    move-result p1

    invoke-static {p1}, Landroidx/car/app/model/Action;->typeToString(I)Ljava/lang/String;

    move-result-object p1

    .line 320
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    .line 321
    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " is disallowed"

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 324
    :cond_3
    :goto_2
    iget-object v5, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mAllowedActionTypes:Ljava/util/Set;

    invoke-interface {v5}, Ljava/util/Set;->isEmpty()Z

    move-result v5

    if-nez v5, :cond_5

    iget-object v5, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mAllowedActionTypes:Ljava/util/Set;

    .line 325
    invoke-virtual {v4}, Landroidx/car/app/model/Action;->getType()I

    move-result v6

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    invoke-interface {v5, v6}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_4

    goto :goto_3

    .line 326
    :cond_4
    new-instance p0, Ljava/lang/IllegalArgumentException;

    .line 327
    invoke-virtual {v4}, Landroidx/car/app/model/Action;->getType()I

    move-result p1

    invoke-static {p1}, Landroidx/car/app/model/Action;->typeToString(I)Ljava/lang/String;

    move-result-object p1

    .line 326
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    .line 327
    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " is not allowed"

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 330
    :cond_5
    :goto_3
    invoke-virtual {v4}, Landroidx/car/app/model/Action;->getType()I

    move-result v5

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-interface {v3, v5}, Ljava/util/Set;->remove(Ljava/lang/Object;)Z

    .line 332
    invoke-virtual {v4}, Landroidx/car/app/model/Action;->getTitle()Landroidx/car/app/model/CarText;

    move-result-object v5

    .line 333
    const-string v6, "Action list exceeded max number of "

    if-eqz v5, :cond_7

    invoke-virtual {v5}, Landroidx/car/app/model/CarText;->isEmpty()Z

    move-result v7

    if-nez v7, :cond_7

    add-int/lit8 v2, v2, -0x1

    if-ltz v2, :cond_6

    .line 341
    iget-object v7, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mTitleTextConstraints:Landroidx/car/app/model/constraints/CarTextConstraints;

    invoke-virtual {v7, v5}, Landroidx/car/app/model/constraints/CarTextConstraints;->validateOrThrow(Landroidx/car/app/model/CarText;)V

    goto :goto_4

    .line 335
    :cond_6
    iget p0, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mMaxCustomTitles:I

    const-string p1, " actions with custom titles"

    invoke-static {v6, p0, p1}, Lcom/sun/jna/Structure$$ExternalSyntheticBUOutline2;->m(Ljava/lang/String;ILjava/lang/Object;)V

    return-void

    :cond_7
    :goto_4
    add-int/lit8 v0, v0, -0x1

    if-ltz v0, :cond_12

    .line 349
    invoke-virtual {v4}, Landroidx/car/app/model/Action;->getFlags()I

    move-result v5

    and-int/lit8 v5, v5, 0x1

    if-eqz v5, :cond_9

    add-int/lit8 v1, v1, -0x1

    if-ltz v1, :cond_8

    goto :goto_5

    .line 351
    :cond_8
    iget p0, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mMaxPrimaryActions:I

    const-string p1, " primary actions"

    invoke-static {v6, p0, p1}, Lcom/sun/jna/Structure$$ExternalSyntheticBUOutline2;->m(Ljava/lang/String;ILjava/lang/Object;)V

    return-void

    .line 358
    :cond_9
    :goto_5
    iget-boolean v5, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mRequireActionIcons:Z

    if-eqz v5, :cond_b

    invoke-virtual {v4}, Landroidx/car/app/model/Action;->getIcon()Landroidx/car/app/model/CarIcon;

    move-result-object v5

    if-nez v5, :cond_b

    invoke-virtual {v4}, Landroidx/car/app/model/Action;->isStandard()Z

    move-result v5

    if-eqz v5, :cond_a

    goto :goto_6

    .line 359
    :cond_a
    const-string p0, "Non-standard actions without an icon are disallowed"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 363
    :cond_b
    :goto_6
    iget-boolean v5, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mRequireActionBackgroundColor:Z

    if-eqz v5, :cond_e

    .line 364
    invoke-virtual {v4}, Landroidx/car/app/model/Action;->getBackgroundColor()Landroidx/car/app/model/CarColor;

    move-result-object v5

    if-eqz v5, :cond_c

    sget-object v5, Landroidx/car/app/model/CarColor;->DEFAULT:Landroidx/car/app/model/CarColor;

    .line 365
    invoke-virtual {v4}, Landroidx/car/app/model/Action;->getBackgroundColor()Landroidx/car/app/model/CarColor;

    move-result-object v6

    invoke-virtual {v5, v6}, Landroidx/car/app/model/CarColor;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_e

    .line 366
    :cond_c
    invoke-virtual {v4}, Landroidx/car/app/model/Action;->isStandard()Z

    move-result v5

    if-eqz v5, :cond_d

    goto :goto_7

    .line 367
    :cond_d
    const-string p0, "Non-standard actions without a background color are disallowed"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 373
    :cond_e
    :goto_7
    iget-boolean v5, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mRequireActionBackgroundColor:Z

    if-nez v5, :cond_10

    sget-object v5, Landroidx/car/app/model/CarColor;->DEFAULT:Landroidx/car/app/model/CarColor;

    .line 374
    invoke-virtual {v4}, Landroidx/car/app/model/Action;->getBackgroundColor()Landroidx/car/app/model/CarColor;

    move-result-object v6

    invoke-virtual {v5, v6}, Landroidx/car/app/model/CarColor;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_10

    iget-boolean v5, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mRestrictBackgroundColorToPrimaryAction:Z

    if-eqz v5, :cond_10

    .line 376
    invoke-virtual {v4}, Landroidx/car/app/model/Action;->getFlags()I

    move-result v5

    and-int/lit8 v5, v5, 0x1

    if-eqz v5, :cond_f

    goto :goto_8

    .line 377
    :cond_f
    const-string p0, "Background color can only be set for primary actions"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 381
    :cond_10
    :goto_8
    iget-boolean v5, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mOnClickListenerAllowed:Z

    if-nez v5, :cond_1

    .line 382
    invoke-virtual {v4}, Landroidx/car/app/model/Action;->getOnClickDelegate()Landroidx/car/app/model/OnClickDelegate;

    move-result-object v5

    if-eqz v5, :cond_1

    .line 383
    invoke-virtual {v4}, Landroidx/car/app/model/Action;->isStandard()Z

    move-result v4

    if-eqz v4, :cond_11

    goto/16 :goto_1

    .line 384
    :cond_11
    const-string p0, "Setting a click listener for a custom action is disallowed"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 345
    :cond_12
    iget p0, p0, Landroidx/car/app/model/constraints/ActionsConstraints;->mMaxActions:I

    const-string p1, " actions"

    invoke-static {v6, p0, p1}, Lcom/sun/jna/Structure$$ExternalSyntheticBUOutline2;->m(Ljava/lang/String;ILjava/lang/Object;)V

    return-void

    .line 389
    :cond_13
    invoke-interface {v3}, Ljava/util/Set;->isEmpty()Z

    move-result p0

    if-nez p0, :cond_15

    .line 390
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    .line 391
    invoke-interface {v3}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_9
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_14

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    .line 392
    invoke-static {v0}, Landroidx/car/app/model/Action;->typeToString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ","

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_9

    .line 394
    :cond_14
    const-string p1, "Missing required action types: "

    invoke-static {p1, p0}, Lcom/sun/jna/Native$$ExternalSyntheticBUOutline5;->m(Ljava/lang/String;Ljava/lang/Object;)V

    :cond_15
    return-void
.end method
