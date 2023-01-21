package me.hotpocket.skriptadvancements.utils.advancement;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.visibilities.HiddenVisibility;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class HiddenAdvancement extends BaseAdvancement implements HiddenVisibility {
    public HiddenAdvancement(@NotNull String key, @NotNull AdvancementDisplay display, @NotNull Advancement parent) {
        super(key, display, parent);
    }

    public HiddenAdvancement(@NotNull String key, @NotNull AdvancementDisplay display, @NotNull Advancement parent, @Range(from = 1L, to = 2147483647L) int maxProgression) {
        super(key, display, parent, maxProgression);
    }
}
