package util

import game.State
import listener.AdvancementListener
import listener.ItemInInventoryListener
import listener.NoOpListener
import listener.ObjectiveListener
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.advancement.Advancement
import org.bukkit.entity.Player

enum class Objective(val listenerFactory: (State, Objective, Player) -> ObjectiveListener) {
    ACompleteCatalogueAdvancement(AdvancementListener.factory("husbandry/complete_catalogue")),
    AFuriousCocktailAdvancement(AdvancementListener.factory("nether/all_potions")),
    ASeedyPlaceAdvancement(AdvancementListener.factory("husbandry/plant_seed")),
    ATerribleFortressAdvancement(AdvancementListener.factory("nether/find_fortress")),
    AcquireHardwareAdvancement(AdvancementListener.factory("story/smelt_iron")),
    ArbalisticAdvancement(AdvancementListener.factory("adventure/arbalistic")),
    BeeOurGuestAdvancement(AdvancementListener.factory("husbandry/safely_harvest_honey")),
    BestFriendsForeverAdvancement(AdvancementListener.factory("husbandry/tame_an_animal")),
    BullseyeAdvancement(AdvancementListener.factory("adventure/bullseye")),
    CountryLodeTakeMeHomeAdvancement(AdvancementListener.factory("nether/use_lodestone")),
    CoverMeWithDiamondsAdvancement(AdvancementListener.factory("story/shiny_gear")),
    DiamondsAdvancement(AdvancementListener.factory("story/mine_diamond")),
    EnchanterAdvancement(AdvancementListener.factory("story/enchant_item")),
    FishyBusinessAdvancement(AdvancementListener.factory("husbandry/fishy_business")),
    GettingAnUpgradeAdvancement(AdvancementListener.factory("story/upgrade_tools")),
    GlowAndBeholdAdvancement(AdvancementListener.factory("husbandry/make_a_sign_glow")),
    GreatViewFromUpHereAdvancement(AdvancementListener.factory("end/levitate")),
    HeroOfTheVillageAdvancement(AdvancementListener.factory("adventure/hero_of_the_village")),
    HiddenInTheDepthsAdvancement(AdvancementListener.factory("nether/obtain_ancient_debris")),
    HiredHelpAdvancement(AdvancementListener.factory("adventure/summon_iron_golem")),
    HotStuffAdvancement(AdvancementListener.factory("story/lava_bucket")),
    HotTouristDestinationsAdvancement(AdvancementListener.factory("nether/explore_nether")),
    IceBucketChallengeAdvancement(AdvancementListener.factory("story/form_obsidian")),
    IntoFireAdvancement(AdvancementListener.factory("nether/obtain_blaze_rod")),
    IsItABalloonAdvancement(AdvancementListener.factory("adventure/spyglass_at_ghast")),
    IsItAPlaneAdvancement(AdvancementListener.factory("adventure/spyglass_at_dragon")),
    IsntItIronPickAdvancement(AdvancementListener.factory("story/iron_tools")),
    IsItABirdAdvancement(AdvancementListener.factory("adventure/spyglass_at_parrot")),
    LocalBreweryAdvancement(AdvancementListener.factory("nether/brew_potion")),
    MonsterHunterAdvancement(AdvancementListener.factory("adventure/kill_a_mob")),
    NotQuiteNineLivesAdvancement(AdvancementListener.factory("nether/charge_respawn_anchor")),
    NotTodayThankYouAdvancement(AdvancementListener.factory("story/deflect_arrow")),
    OhShinyAdvancement(AdvancementListener.factory("nether/distract_piglin")),
    OlBetsyAdvancement(AdvancementListener.factory("adventure/ol_betsy")),
    PostmortalAdvancement(AdvancementListener.factory("adventure/totem_of_undying")),
    RemoteGetawayAdvancement(AdvancementListener.factory("end/enter_end_gateway")),
    ReturnToSenderAdvancement(AdvancementListener.factory("nether/return_to_sender")),
    SeriousDedicationAdvancement(AdvancementListener.factory("husbandry/obtain_netherite_hoe")),
    SkysTheLimitAdvancement(AdvancementListener.factory("end/elytra")),
    SniperDuelAdvancement(AdvancementListener.factory("adventure/sniper_duel")),
    SpookyScarySkeletonAdvancement(AdvancementListener.factory("nether/get_wither_skull")),
    StickySituationAdvancement(AdvancementListener.factory("adventure/honey_block_slide")),
    StoneAgeAdvancement(AdvancementListener.factory("story/mine_stone")),
    SubspaceBubbleAdvancement(AdvancementListener.factory("nether/fast_travel")),
    SuitUpAdvancement(AdvancementListener.factory("story/obtain_armor")),
    SweetDreamsAdvancement(AdvancementListener.factory("adventure/sleep_in_bed")),
    TacticalFishingAdvancement(AdvancementListener.factory("husbandry/tactical_fishing")),
    TakeAimAdvancement(AdvancementListener.factory("adventure/shoot_arrow")),
    TheCutestPredatorAdvancement(AdvancementListener.factory("husbandry/axolotl_in_a_bucket")),
    TheEndAdvancement(AdvancementListener.factory("end/root")),
    TheEndAgainAdvancement(AdvancementListener.factory("end/respawn_dragon")),
    TheHealingPowerOfFriendshipAdvancement(AdvancementListener.factory("husbandry/kill_axolotl_target")),
    TheNextGenerationAdvancement(AdvancementListener.factory("end/dragon_egg")),
    TheParrotsAndTheBatsAdvancement(AdvancementListener.factory("husbandry/breed_an_animal")),
    ThisBoatHasLegsAdvancement(AdvancementListener.factory("nether/ride_strider")),
    ThoseWereTheDaysAdvancement(AdvancementListener.factory("nether/find_bastion")),
    TotalBeelocationAdvancement(AdvancementListener.factory("husbandry/silk_touch_nest")),
    TwoBirdsOneArrowAdvancement(AdvancementListener.factory("adventure/two_birds_one_arrow")),
    UneasyAllianceAdvancement(AdvancementListener.factory("nether/uneasy_alliance")),
    VoluntaryExileAdvancement(AdvancementListener.factory("adventure/voluntary_exile")),
    WarPigsAdvancement(AdvancementListener.factory("nether/loot_bastion")),
    WaxOffAdvancement(AdvancementListener.factory("husbandry/wax_off")),
    WaxOnAdvancement(AdvancementListener.factory("husbandry/wax_on")),
    WeNeedToGoDeeperAdvancement(AdvancementListener.factory("story/enter_the_nether")),
    WhatADealAdvancement(AdvancementListener.factory("adventure/trade")),
    WhateverFloatsYourGoatAdvancement(AdvancementListener.factory("husbandry/ride_a_boat_with_a_goat")),
    WhoIsCuttingOnionsAdvancement(AdvancementListener.factory("nether/obtain_crying_obsidian")),
    WhosThePillagerNowAdvancement(AdvancementListener.factory("adventure/whos_the_pillager_now")),
    WitheringHeightsAdvancement(AdvancementListener.factory("nether/summon_wither")),
    YouNeedAMintAdvancement(AdvancementListener.factory("end/dragon_breath")),
    ZombieDoctorAdvancement(AdvancementListener.factory("story/cure_zombie_villager")),
    UniqueConcreteBlocks12(::NoOpListener),
    BlackStainedGlass16(::NoOpListener),
    BlackWool16(::NoOpListener),
    BlueIce16(::NoOpListener),
    BlueStainedGlass16(::NoOpListener),
    BlueWool16(::NoOpListener),
    BrownStainedGlass16(::NoOpListener),
    BrownWool16(::NoOpListener),
    CyanStainedGlass16(::NoOpListener),
    CyanWool16(::NoOpListener),
    Eggs16(::NoOpListener),
    EnderPearls16(::NoOpListener),
    GrassBlocks16(::NoOpListener),
    GrayStainedGlass16(::NoOpListener),
    GrayWool16(::NoOpListener),
    GreenStainedGlass16(::NoOpListener),
    GreenWool16(::NoOpListener),
    InkSacks16(::NoOpListener),
    LightBlueStainedGlass16(::NoOpListener),
    LightBlueWool16(::NoOpListener),
    LightGrayStainedGlass16(::NoOpListener),
    LightGrayWool16(::NoOpListener),
    LimeGreenStainedGlass16(::NoOpListener),
    LimeGreenWool16(::NoOpListener),
    MagentaStainedGlass16(::NoOpListener),
    MagentaWool16(::NoOpListener),
    OrangeStainedGlass16(::NoOpListener),
    OrangeWool16(::NoOpListener),
    PinkStainedGlass16(::NoOpListener),
    PinkWool16(::NoOpListener),
    Podzol16(::NoOpListener),
    Pumpkins16(::NoOpListener),
    PurpleStainedGlass16(::NoOpListener),
    PurpleWool16(::NoOpListener),
    RedStainedGlass16(::NoOpListener),
    RedWool16(::NoOpListener),
    TintedGlass16(::NoOpListener),
    WhiteStainedGlass16(::NoOpListener),
    WhiteWool16(::NoOpListener),
    YellowStainedGlass16(::NoOpListener),
    YellowWool16(::NoOpListener),
    UniqueConcreteBlocks3(::NoOpListener),
    BlazeRods32(::NoOpListener),
    Diamonds32(::NoOpListener),
    SpiderEyes32(::NoOpListener),
    AcaciaLogs64(::NoOpListener),
    AmethystShards64(::NoOpListener),
    Andesite64(::NoOpListener),
    Apples64(::NoOpListener),
    Arrows64(::NoOpListener),
    BirchLogs64(::NoOpListener),
    Bones64(::NoOpListener),
    Cacti64(::NoOpListener),
    Carrots64(::NoOpListener),
    Charcoal64(::NoOpListener),
    ClayBlocks64(::NoOpListener),
    Coal64(::NoOpListener),
    Cobblestone64(::NoOpListener),
    CopperIngots64(::NoOpListener),
    CraftingTable64(::NoOpListener),
    CrimsonStems64(::NoOpListener),
    DarkOakLogs64(::NoOpListener),
    Diorite64(::NoOpListener),
    Dirt64(::NoOpListener),
    Emeralds64(::NoOpListener),
    Endstone64(::NoOpListener),
    Feathers64(::NoOpListener),
    Flint64(::NoOpListener),
    Glass64(::NoOpListener),
    GlowstoneBlocks64(::NoOpListener),
    GoldIngots64(::NoOpListener),
    Granite64(::NoOpListener),
    Gravel64(::NoOpListener),
    Gunpowder64(::NoOpListener),
    IronIngots64(::NoOpListener),
    JungleLogs64(::NoOpListener),
    Ladders64(::NoOpListener),
    LapisLazuli64(::NoOpListener),
    Leather64(::NoOpListener),
    Leaves64(::NoOpListener),
    LilyPads64(::NoOpListener),
    NetherQuartz64(::NoOpListener),
    NetherWart64(::NoOpListener),
    Netherrack64(::NoOpListener),
    OakLogs64(::NoOpListener),
    Obsidian64(::NoOpListener),
    Potatoes64(::NoOpListener),
    Rails64(::NoOpListener),
    Redstone64(::NoOpListener),
    RottenFlesh64(::NoOpListener),
    Sand64(::NoOpListener),
    Sandstone64(::NoOpListener),
    Slimeballs64(::NoOpListener),
    SmoothStone64(::NoOpListener),
    SpruceLogs64(::NoOpListener),
    Stone64(::NoOpListener),
    String64(::NoOpListener),
    SugarCane64(::NoOpListener),
    SweetBerries64(::NoOpListener),
    WarpedStems64(::NoOpListener),
    Wheat64(::NoOpListener),
    UniqueConcreteBlocks7(::NoOpListener),
    Cobwebs8(::NoOpListener),
    UniqueGlazedTerracotta8(::NoOpListener),
    Activate3EndPortals(::NoOpListener),
    All16DyeColors(::NoOpListener),
    All26UniquePaintings(::NoOpListener),
    All3Stews(::NoOpListener),
    AllDiamondTools(::NoOpListener),
    AllIronTools(::NoOpListener),
    AllStoneTools(::NoOpListener),
    AllTypesOfDoorsIncludingTrapdoors(::NoOpListener),
    AllWoodTools(::NoOpListener),
    AnyChainArmor(::NoOpListener),
    AnyConcreteBlock(::NoOpListener),
    AnyGlazedTerracotta(::NoOpListener),
    AnyTippedArrow(::NoOpListener),
    BarterWithPiglin(::NoOpListener),
    BlowUpBedInNether(::NoOpListener),
    BounceOnSlimeBlock(::NoOpListener),
    BreakDiamondTool(::NoOpListener),
    BreakPieceOfDiamondArmor(::NoOpListener),
    BreakShield(::NoOpListener),
    BreakAnyArmor(::NoOpListener),
    BreakAnyTool(::NoOpListener),
    BreedAxolotl(::NoOpListener),
    BreedBee(::NoOpListener),
    BreedCat(::NoOpListener),
    BreedChicken(::NoOpListener),
    BreedCow(::NoOpListener),
    BreedDonkey(::NoOpListener),
    BreedFox(::NoOpListener),
    BreedGoat(::NoOpListener),
    BreedHoglin(::NoOpListener),
    BreedHorse(::NoOpListener),
    BreedLlama(::NoOpListener),
    BreedMule(::NoOpListener),
    BreedOcelot(::NoOpListener),
    BreedPanda(::NoOpListener),
    BreedPig(::NoOpListener),
    BreedRabbit(::NoOpListener),
    BreedSheep(::NoOpListener),
    BreedStrider(::NoOpListener),
    BreedTurtle(::NoOpListener),
    BreedWolf(::NoOpListener),
    BrewPotionOfFireResistance(::NoOpListener),
    BrewPotionOfHarming(::NoOpListener),
    BrewPotionOfHealing(::NoOpListener),
    BrewPotionOfInvisibility(::NoOpListener),
    BrewPotionOfLeaping(::NoOpListener),
    BrewPotionOfNightVision(::NoOpListener),
    BrewPotionOfRegeneration(::NoOpListener),
    BrewPotionOfSlowness(::NoOpListener),
    BrewPotionOfStrength(::NoOpListener),
    BrewPotionOfSwiftness(::NoOpListener),
    BrewPotionOfWaterBreathing(::NoOpListener),
    BrewPotionOfWeakness(::NoOpListener),
    BrewAnyLingeringPotion(::NoOpListener),
    CarvePumpkin(::NoOpListener),
    CleanBannerWithCauldron(::NoOpListener),
    CompleteMap(::NoOpListener),
    Cook4FoodOnCampfire(::NoOpListener),
    CraftActivatorRail(::NoOpListener),
    CraftAnvil(::NoOpListener),
    CraftArmorStand(::NoOpListener),
    CraftBeehive(::NoOpListener),
    CraftBlastFurnace(::NoOpListener),
    CraftBookshelf(::NoOpListener),
    CraftCake(::NoOpListener),
    CraftCampfire(::NoOpListener),
    CraftCandle(::NoOpListener),
    CraftCartographyTable(::NoOpListener),
    CraftClock(::NoOpListener),
    CraftCompass(::NoOpListener),
    CraftDaylightDetector(::NoOpListener),
    CraftDispenser(::NoOpListener),
    CraftDropper(::NoOpListener),
    CraftEndCrystal(::NoOpListener),
    CraftEnderChest(::NoOpListener),
    CraftFireCharge(::NoOpListener),
    CraftFletchingTable(::NoOpListener),
    CraftGrindstone(::NoOpListener),
    CraftHoneyBlock(::NoOpListener),
    CraftHopper(::NoOpListener),
    CraftJackOlantern(::NoOpListener),
    CraftJukebox(::NoOpListener),
    CraftLectern(::NoOpListener),
    CraftLoom(::NoOpListener),
    CraftMagmaCream(::NoOpListener),
    CraftNoteBlock(::NoOpListener),
    CraftObserver(::NoOpListener),
    CraftPainting(::NoOpListener),
    CraftPiston(::NoOpListener),
    CraftPoweredRail(::NoOpListener),
    CraftPrismarineBrick(::NoOpListener),
    CraftRabbitStew(::NoOpListener),
    CraftRedNetherBrick(::NoOpListener),
    CraftRedstoneComparator(::NoOpListener),
    CraftRedstoneLamp(::NoOpListener),
    CraftRedstoneRepeater(::NoOpListener),
    CraftRespawnAnchor(::NoOpListener),
    CraftScaffolding(::NoOpListener),
    CraftSmoker(::NoOpListener),
    CraftSoulCampfire(::NoOpListener),
    CraftSoulLantern(::NoOpListener),
    CraftSpyglass(::NoOpListener),
    CraftStickyPiston(::NoOpListener),
    CraftStonecutter(::NoOpListener),
    CraftTarget(::NoOpListener),
    CraftTrappedChest(::NoOpListener),
    CreateDirtPathBlock(::NoOpListener),
    DestroyMobSpawner(::NoOpListener),
    Die(::NoOpListener),
    DrinkHoneyBottle(::NoOpListener),
    DrinkMilk(::NoOpListener),
    DrySponge(::NoOpListener),
    DyeLeatherArmorPiece(::NoOpListener),
    DyeSheep(::NoOpListener),
    EatApple(::NoOpListener),
    EatBakedPotato(::NoOpListener),
    EatBeetroot(::NoOpListener),
    EatBeetrootSoup(::NoOpListener),
    EatBread(::NoOpListener),
    EatCarrot(::NoOpListener),
    EatChorusFruit(::NoOpListener),
    EatCookedBeef(::NoOpListener),
    EatCookedChicken(::NoOpListener),
    EatCookedCod(::NoOpListener),
    EatCookedMutton(::NoOpListener),
    EatCookedPorkchop(::NoOpListener),
    EatCookedRabbit(::NoOpListener),
    EatCookedSalmon(::NoOpListener),
    EatCookie(::NoOpListener),
    EatDriedKelp(::NoOpListener),
    EatEnchantedGoldenApple(::NoOpListener),
    EatGlowBerry(::NoOpListener),
    EatGoldenApple(::NoOpListener),
    EatGoldenCarrot(::NoOpListener),
    EatMelonSlice(::NoOpListener),
    EatMushroomStew(::NoOpListener),
    EatPoisonPotato(::NoOpListener),
    EatPotato(::NoOpListener),
    EatPufferfish(::NoOpListener),
    EatPumpkinPie(::NoOpListener),
    EatRabbitStew(::NoOpListener),
    EatRawBeef(::NoOpListener),
    EatRawChicken(::NoOpListener),
    EatRawCod(::NoOpListener),
    EatRawMutton(::NoOpListener),
    EatRawPorkchop(::NoOpListener),
    EatRawRabbit(::NoOpListener),
    EatRawSalmon(::NoOpListener),
    EatRottenFlesh(::NoOpListener),
    EatSpiderEye(::NoOpListener),
    EatSuspiciousStew(::NoOpListener),
    EatSweetBerry(::NoOpListener),
    EatTropicalFish(::NoOpListener),
    EnchantBook(::NoOpListener),
    EnchantFishingPole(::NoOpListener),
    EnchantWoodenTool(::NoOpListener),
    EnterBadlandsBiome(::NoOpListener),
    EnterBambooJungleBiome(::NoOpListener),
    EnterDesertTemple(::NoOpListener),
    EnterDesertVillage(::NoOpListener),
    EnterDungeon(::NoOpListener),
    EnterEndCity(::NoOpListener),
    EnterIceSpikesBiome(::NoOpListener),
    EnterIgloo(::NoOpListener),
    EnterJungleBiome(::NoOpListener),
    EnterJungleTemple(::NoOpListener),
    EnterLargeTreeTaigaBiome(::NoOpListener),
    EnterMushroomIslandBiome(::NoOpListener),
    EnterPlainsVillage(::NoOpListener),
    EnterRavine(::NoOpListener),
    EnterSavannaVillage(::NoOpListener),
    EnterShatteredSavannaBiome(::NoOpListener),
    EnterShipwreck(::NoOpListener),
    EnterTaigaVillage(::NoOpListener),
    EnterTundraBiome(::NoOpListener),
    EnterTundraVillage(::NoOpListener),
    EnterWarmOceanBiome(::NoOpListener),
    EnterWitchHut(::NoOpListener),
    EnterAmethystGeode(::NoOpListener),
    EquipCurseOfBindingItem(::NoOpListener),
    FeedWheatToHorse(::NoOpListener),
    FillComposter(::NoOpListener),
    FullDiamondArmor(::NoOpListener),
    FullGoldArmor(::NoOpListener),
    FullIronArmor(::NoOpListener),
    FullLeatherArmor(::NoOpListener),
    FullNetheriteArmor(::NoOpListener),
    GetSkeletonToShootSkeleton(::NoOpListener),
    GetAbsorptionEffect(::NoOpListener),
    GetBlindnessEffect(::NoOpListener),
    GetDolphinsGraceEffect(::NoOpListener),
    GetGlowingEffect(::NoOpListener),
    GetHungerEffect(::NoOpListener),
    GetLevitationEffect(::NoOpListener),
    GetMiningFatigueEffect(::NoOpListener),
    GetRegenerationEffect(::NoOpListener),
    GetResistanceEffect(::NoOpListener),
    GetWitherEffect(::NoOpListener),
    GrowDarkOakTree(::NoOpListener),
    GrowGiantMushroom(::NoOpListener),
    GrowLargeJungleTree(::NoOpListener),
    GrowLargeSpruceTree(::NoOpListener),
    HaveDolphinLeadYouToTreasure(::NoOpListener),
    HitYourselfWithArrow(::NoOpListener),
    KillAxolotl(::NoOpListener),
    KillBabyVillager(::NoOpListener),
    KillBabyZombieWithArrow(::NoOpListener),
    KillBat(::NoOpListener),
    KillBee(::NoOpListener),
    KillBlaze(::NoOpListener),
    KillBlazeWithSnowballs(::NoOpListener),
    KillCat(::NoOpListener),
    KillCaveSpider(::NoOpListener),
    KillChicken(::NoOpListener),
    KillCod(::NoOpListener),
    KillCow(::NoOpListener),
    KillCreeper(::NoOpListener),
    KillCreeperWithAnotherCreeper(::NoOpListener),
    KillCreeperWithTnt(::NoOpListener),
    KillDolphin(::NoOpListener),
    KillDonkey(::NoOpListener),
    KillDrowned(::NoOpListener),
    KillDrownedHoldingTrident(::NoOpListener),
    KillElderGuardian(::NoOpListener),
    KillEnderDragon(::NoOpListener),
    KillEnderDragonWithoutBeds(::NoOpListener),
    KillEnderman(::NoOpListener),
    KillEndermite(::NoOpListener),
    KillEvoker(::NoOpListener),
    KillFox(::NoOpListener),
    KillGhast(::NoOpListener),
    KillGlowSquid(::NoOpListener),
    KillGuardian(::NoOpListener),
    KillHoglin(::NoOpListener),
    KillHorse(::NoOpListener),
    KillHusk(::NoOpListener),
    KillIronGolem(::NoOpListener),
    KillLlama(::NoOpListener),
    KillMagmaCube(::NoOpListener),
    KillMobWhileItsOnFire(::NoOpListener),
    KillMobWithTamedWolf(::NoOpListener),
    KillMobWithDripstone(::NoOpListener),
    KillMobWithFallDamage(::NoOpListener),
    KillMooshroom(::NoOpListener),
    KillMule(::NoOpListener),
    KillOcelot(::NoOpListener),
    KillPanda(::NoOpListener),
    KillParrot(::NoOpListener),
    KillPhantom(::NoOpListener),
    KillPig(::NoOpListener),
    KillPiglin(::NoOpListener),
    KillPiglinBrute(::NoOpListener),
    KillPillager(::NoOpListener),
    KillPolarBear(::NoOpListener),
    KillPufferfish(::NoOpListener),
    KillRabbit(::NoOpListener),
    KillRavager(::NoOpListener),
    KillSalmon(::NoOpListener),
    KillSheep(::NoOpListener),
    KillShulker(::NoOpListener),
    KillSilverfish(::NoOpListener),
    KillSkeleton(::NoOpListener),
    KillSkeletonWithoutItDamagingYou(::NoOpListener),
    KillSlime(::NoOpListener),
    KillSnowGolem(::NoOpListener),
    KillSpider(::NoOpListener),
    KillSpiderJockey(::NoOpListener),
    KillSquid(::NoOpListener),
    KillStray(::NoOpListener),
    KillStrider(::NoOpListener),
    KillTropicalFish(::NoOpListener),
    KillTurtle(::NoOpListener),
    KillVex(::NoOpListener),
    KillVillager(::NoOpListener),
    KillVindicator(::NoOpListener),
    KillWitch(::NoOpListener),
    KillWither(::NoOpListener),
    KillWitherSkeleton(::NoOpListener),
    KillWolf(::NoOpListener),
    KillZoglin(::NoOpListener),
    KillZombie(::NoOpListener),
    KillZombiePiglin(::NoOpListener),
    KillZombieVillager(::NoOpListener),
    LightCreeperWithFlintSteel(::NoOpListener),
    LightTntWithFlintSteel(::NoOpListener),
    LightTntWithRedstone(::NoOpListener),
    LootBuriedTreasure(::NoOpListener),
    LootRuinedPortalChest(::NoOpListener),
    MakeEfficiency5Tool(::NoOpListener),
    MakePower5Bow(::NoOpListener),
    MakeSharpness5Sword(::NoOpListener),
    MakeWitchPoisonItself(::NoOpListener),
    MineAncientDebris(::NoOpListener),
    MineCoalOre(::NoOpListener),
    MineCopperOre(::NoOpListener),
    MineDiamondOre(::NoOpListener),
    MineEmeraldOre(::NoOpListener),
    MineGoldOre(::NoOpListener),
    MineIronOre(::NoOpListener),
    MineLapisLazuliOre(::NoOpListener),
    MineNetherGoldOre(::NoOpListener),
    MineNetherQuartzOre(::NoOpListener),
    MineRedstoneOre(::NoOpListener),
    Obtain12UniqueFlowers(::NoOpListener),
    ObtainAmethystShard(::NoOpListener),
    ObtainMendingBook(::NoOpListener),
    ObtainNameTag(::NoOpListener),
    ObtainNautilusShell(::NoOpListener),
    ObtainSaddle(::NoOpListener),
    ObtainTrident(::NoOpListener),
    ObtainAnyMobHead(::NoOpListener),
    ObtainDiamondHorseArmor(::NoOpListener),
    ObtainGoldHorseArmor(::NoOpListener),
    ObtainIronHorseArmor(::NoOpListener),
    ObtainLeatherHorseArmor(::NoOpListener),
    PlantBamboo(::NoOpListener),
    PlantBeetrootSeed(::NoOpListener),
    PlantChorusFruit(::NoOpListener),
    PlantCocoaBean(::NoOpListener),
    PlantNetherWart(::NoOpListener),
    PlantPumpkinSeed(::NoOpListener),
    PlantSugarCane(::NoOpListener),
    PlantWheatSeed(::NoOpListener),
    PlantAnySapling(::NoOpListener),
    PlantSomethingInFlowerPot(::NoOpListener),
    PlayMusicDisc(::NoOpListener),
    PushMobWithPiston(::NoOpListener),
    ReachLevel10(::NoOpListener),
    ReachLevel25(::NoOpListener),
    ReachBuildLimitY256(::NoOpListener),
    ReachY0InEnd(::NoOpListener),
    RepairToolInAnvil(::NoOpListener),
    RideBoatWithCreeper(::NoOpListener),
    RideMinecartOnRail(::NoOpListener),
    RidePig(::NoOpListener),
    RingBell(::NoOpListener),
    SetYourSpawnWithBed(::NoOpListener),
    ShearMooshroom(::NoOpListener),
    ShearSheep(::NoOpListener),
    ShootBatWithArrow(::NoOpListener),
    ShootBell(::NoOpListener),
    ShootFireworkFromCrossbow(::NoOpListener),
    StandOnBedrock(::NoOpListener),
    StareAtSunWithSpyglass(::NoOpListener),
    StripLog(::NoOpListener),
    SwimInDesertWell(::NoOpListener),
    SwimInLavaWithFireResistance(::NoOpListener),
    TameCat(::NoOpListener),
    TameDonkey(::NoOpListener),
    TameHorse(::NoOpListener),
    TameLlama(::NoOpListener),
    TameMule(::NoOpListener),
    TameParrot(::NoOpListener),
    TameWolf(::NoOpListener),
    ThrowDiamondInLava(::NoOpListener),
    ThrowEnderPearl(::NoOpListener),
    ThrowSnowball(::NoOpListener),
    ThrowEgg(::NoOpListener),
    TrapMobInBoat(::NoOpListener),
    UseAnyDyeOnSign(::NoOpListener),
    UseBonemealOnGrass(::NoOpListener),
    WearCarvedPumpkin(::NoOpListener),
    WriteBook(::NoOpListener),
    Unknown(::NoOpListener);

    fun addListener(state: State, player: Player): ObjectiveListener {
        val listener = listenerFactory(state, this, player)
        Bukkit.getServer().pluginManager.registerEvents(listener, state.plugin)
        return listener
    }
}
