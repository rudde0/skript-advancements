package me.hotpocket.skriptadvancements.utils.advancement;

import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.multiParents.MultiParentsAdvancement;
import com.fren_gor.ultimateAdvancementAPI.visibilities.HiddenVisibility;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Set;

public class HiddenMultiParentsAdvancement extends MultiParentsAdvancement implements HiddenVisibility {
    public HiddenMultiParentsAdvancement(@NotNull String key, @NotNull AdvancementDisplay display, @NotNull BaseAdvancement... parents) {
        super(key, display, parents);
    }

    public HiddenMultiParentsAdvancement(@NotNull String key, @NotNull AdvancementDisplay display, @Range(from = 1L, to = 2147483647L) int maxProgression, @NotNull BaseAdvancement... parents) {
        super(key, display, maxProgression, parents);
    }

    public HiddenMultiParentsAdvancement(@NotNull String key, @NotNull AdvancementDisplay display, @NotNull Set<BaseAdvancement> parents) {
        super(key, display, parents);
    }

    public HiddenMultiParentsAdvancement(@NotNull String key, @NotNull AdvancementDisplay display, @Range(from = 1L, to = 2147483647L) int maxProgression, @NotNull Set<BaseAdvancement> parents) {
        super(key, display, maxProgression, parents);
    }
}
