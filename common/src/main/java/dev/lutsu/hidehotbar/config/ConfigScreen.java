package dev.lutsu.hidehotbar.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;

import static dev.lutsu.hidehotbar.HideHotbarMod.currentVersion;

public class ConfigScreen {

    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("hidehotbar.title.config"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory general = builder.getOrCreateCategory(
                Text.translatable("hidehotbar.category.general")
        );

        SubCategoryBuilder general_sub = entryBuilder.startSubCategory(
                Text.translatable("hidehotbar.category.general")
        );


        general_sub.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.enabled"),
                                ToolBarConfig.INSTANCE.enabled
                        ).setDefaultValue(true)
                        .setSaveConsumer(value -> ToolBarConfig.INSTANCE.enabled = value)
                        .build()
        );

        general_sub.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.is_hidden"),
                                ToolBarConfig.INSTANCE.hid
                        ).setDefaultValue(false)
                        .setSaveConsumer(value -> ToolBarConfig.INSTANCE.hid = value)
                        .build()
        );

        general_sub.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.debug"),
                                ToolBarConfig.INSTANCE.debug
                        ).setDefaultValue(false)
                        .setSaveConsumer(value -> ToolBarConfig.INSTANCE.debug = value)
                        .build()
        );

        general.addEntry(general_sub.build());

        SubCategoryBuilder unhide = entryBuilder.startSubCategory(
                Text.translatable("hidehotbar.category.unhide_if")
        );

        unhide.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.unhideon.restart"),
                                ToolBarConfig.INSTANCE.unhide_on_restart
                        ).setTooltip(Text.translatable("hidehotbar.option.unhideon.damage.tooltip"))
                        .setDefaultValue(false)
                        .setSaveConsumer(value -> ToolBarConfig.INSTANCE.unhide_on_restart = value)
                        .build()
        );

        unhide.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.unhideon.slot_change"),
                                ToolBarConfig.INSTANCE.unhide_on_slot_change
                        ).setTooltip(Text.translatable("hidehotbar.option.unhideon.slot_change.tooltip"))
                        .setDefaultValue(false)
                        .setSaveConsumer(value -> ToolBarConfig.INSTANCE.unhide_on_slot_change = value)
                        .build()
        );

        unhide.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.unhideon.damage"),
                                ToolBarConfig.INSTANCE.unhide_on_damage
                        ).setTooltip(Text.translatable("hidehotbar.option.unhideon.damage.tooltip"))
                        .setDefaultValue(false)
                        .setSaveConsumer(value -> ToolBarConfig.INSTANCE.unhide_on_damage = value)
                        .build()
        );
        unhide.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.unhideon.low_hp"),
                                ToolBarConfig.INSTANCE.unhide_on_low_hp
                        ).setTooltip(Text.translatable("hidehotbar.option.unhideon.low_hp.tooltip"))
                        .setDefaultValue(false)
                        .setSaveConsumer(value -> ToolBarConfig.INSTANCE.unhide_on_low_hp = value)
                        .build()
        );

        unhide.add(
                entryBuilder.startIntSlider(
                                Text.translatable("hidehotbar.option.unhideon.low_hp_level"),
                                ToolBarConfig.INSTANCE.low_hp_percentage, 1, 100
                        ).setTooltip(Text.translatable("hidehotbar.option.unhideon.low_hp_level.tooltip"))
                        .setDefaultValue(20)
                        .setSaveConsumer(value -> ToolBarConfig.INSTANCE.low_hp_percentage = value)
                        .build()
        );

        unhide.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.unhideon.low_food"),
                                ToolBarConfig.INSTANCE.unhide_on_low_food
                        ).setTooltip(Text.translatable("hidehotbar.option.unhideon.low_food.tooltip"))
                        .setDefaultValue(false)
                        .setSaveConsumer(value -> ToolBarConfig.INSTANCE.unhide_on_low_food = value)
                        .build()
        );

        unhide.add(
                entryBuilder.startIntSlider(
                                Text.translatable("hidehotbar.option.unhideon.low_food_level"),
                                ToolBarConfig.INSTANCE.low_food_percentage, 1, 100
                        ).setTooltip(Text.translatable("hidehotbar.option.unhideon.low_food_level.tooltip"))
                        .setDefaultValue(20)
                        .setSaveConsumer(value -> ToolBarConfig.INSTANCE.low_food_percentage = value)
                        .build()
        );

        general.addEntry(unhide.build());

        SubCategoryBuilder hidingCustomization = entryBuilder.startSubCategory(
                Text.translatable("hidehotbar.category.hide_customize")
        );
        hidingCustomization.setTooltip(Text.translatable("hidehotbar.category.hide_customize.tooltip"));

        hidingCustomization.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.hide_custom.hotbar"),
                                ToolBarConfig.INSTANCE.hide_hotbar
                        ).setDefaultValue(true)
                        .setSaveConsumer(value -> ToolBarConfig.INSTANCE.hide_hotbar = value)
                        .build()
        );

        hidingCustomization.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.hide_custom.armor"),
                                ToolBarConfig.INSTANCE.hide_armor
                        ).setDefaultValue(true)
                        .setSaveConsumer(value -> ToolBarConfig.INSTANCE.hide_armor = value)
                        .build()
        );

        hidingCustomization.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.hide_custom.healthbar"),
                                ToolBarConfig.INSTANCE.hide_healthbar
                        ).setDefaultValue(true)
                        .setSaveConsumer(value -> ToolBarConfig.INSTANCE.hide_healthbar = value)
                        .build()
        );

        hidingCustomization.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.hide_custom.food"),
                                ToolBarConfig.INSTANCE.hide_food
                        ).setDefaultValue(true)
                        .setSaveConsumer(value -> ToolBarConfig.INSTANCE.hide_food = value)
                        .build()
        );

        if (currentVersion.compareTo(MinecraftVersion.MC_1_21_4) < 0) {
            // in version bellow 1.21.2 (1.21.2 is in 1.21.4 group) bubbles is in `renderStatusBar`()
            hidingCustomization.add(
                    entryBuilder.startBooleanToggle(
                                    Text.translatable("hidehotbar.option.hide_custom.bubbles"),
                                    ToolBarConfig.INSTANCE.hide_bubbles
                            ).setDefaultValue(true)
                            .setSaveConsumer(value -> ToolBarConfig.INSTANCE.hide_bubbles = value)
                            .build()
            );
        }

        hidingCustomization.add(
                entryBuilder.startBooleanToggle(
                                Text.translatable("hidehotbar.option.hide_custom.expbar"),
                                ToolBarConfig.INSTANCE.hide_expbar
                        ).setDefaultValue(true)
                        .setSaveConsumer(value -> ToolBarConfig.INSTANCE.hide_expbar = value)
                        .build()
        );

        if (currentVersion.compareTo(MinecraftVersion.MC_1_21_7) < 0){
            hidingCustomization.add(
                    entryBuilder.startBooleanToggle(
                                    Text.translatable("hidehotbar.option.hide_custom.locator"),
                                    ToolBarConfig.INSTANCE.hide_locator
                            ).setDefaultValue(true)
                            .setSaveConsumer(value -> ToolBarConfig.INSTANCE.hide_locator = value)
                            .build()
            );
        }

        general.addEntry(hidingCustomization.build());

        if (ToolBarConfig.INSTANCE.debug) {
//            ConfigCategory devOptions =
            addDevCategory(builder);
        }

        builder.setSavingRunnable(ToolBarConfig::save);

        return builder.build();
    }

    private static void addDevBooleanToggle(
            ConfigCategory category,
            ConfigEntryBuilder entryBuilder,
            String title,
            boolean currentValue,
            java.util.function.Consumer<Boolean> saveConsumer
    ) {
        category.addEntry(
                entryBuilder.startBooleanToggle(Text.literal(title), currentValue)
                        .setDefaultValue(false)
                        .setSaveConsumer(saveConsumer)
                        .build()
        );
    }

    private static void addDevCategory(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory devOptions = builder.getOrCreateCategory(
                Text.literal("Dev options")
        );

        devOptions.setDescription(new StringVisitable[]{
                StringVisitable.plain("Use at your own risk!"),
                StringVisitable.plain("To disable them set debug to false"),
                StringVisitable.plain("No translations available")
        });

//        addDevBooleanToggle(devOptions, entryBuilder,
//                "Hide hotbar v1 mixin",
//                ToolBarConfig.INSTANCE.DEV_OPTIONS.hide_hotbar_v1,
//                val -> ToolBarConfig.INSTANCE.DEV_OPTIONS.hide_hotbar_v1 = val
//        );
//
//        addDevBooleanToggle(devOptions, entryBuilder,
//                "Hide armor v1 mixin",
//                ToolBarConfig.INSTANCE.DEV_OPTIONS.hide_armor_v1,
//                val -> ToolBarConfig.INSTANCE.DEV_OPTIONS.hide_armor_v1 = val
//        );
//
//        addDevBooleanToggle(devOptions, entryBuilder,
//                "Hide health bar v1 mixin",
//                ToolBarConfig.INSTANCE.DEV_OPTIONS.hide_healthbar_v1,
//                val -> ToolBarConfig.INSTANCE.DEV_OPTIONS.hide_healthbar_v1 = val
//        );
//
//        addDevBooleanToggle(devOptions, entryBuilder,
//                "Hide food bar v1 mixin",
//                ToolBarConfig.INSTANCE.DEV_OPTIONS.hide_food_v1,
//                val -> ToolBarConfig.INSTANCE.DEV_OPTIONS.hide_food_v1 = val
//        );
//
//        addDevBooleanToggle(devOptions, entryBuilder,
//                "Hide bubbles v1 mixin",
//                ToolBarConfig.INSTANCE.DEV_OPTIONS.hide_bubbles_v1,
//                val -> ToolBarConfig.INSTANCE.DEV_OPTIONS.hide_bubbles_v1 = val
//        );
//
//        addDevBooleanToggle(devOptions, entryBuilder,
//                "Hide experience bar v1 mixin",
//                ToolBarConfig.INSTANCE.DEV_OPTIONS.hide_expbar_v1,
//                val -> ToolBarConfig.INSTANCE.DEV_OPTIONS.hide_expbar_v1 = val
//        );
//
//        addDevBooleanToggle(devOptions, entryBuilder,
//                "Hide locator bar v1 mixin",
//                ToolBarConfig.INSTANCE.DEV_OPTIONS.hide_locator_v1,
//                val -> ToolBarConfig.INSTANCE.DEV_OPTIONS.hide_locator_v1 = val
//        );
    }
}
