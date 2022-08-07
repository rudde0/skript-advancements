package me.hotpocket.skriptadvancements.elements.types;

import ch.njol.skript.registrations.Classes;
import me.hotpocket.skriptadvancements.utils.AdvancementAPI;
import me.hotpocket.skriptadvancements.utils.EnumClassInfo;

public class TypFrame {
    static {
        if (Classes.getExactClassInfo(AdvancementAPI.Frame.class) == null) {
            Classes.registerClass(new EnumClassInfo<>(AdvancementAPI.Frame.class, "frame")
                    .user("frames")
                    .name("Advancement Frame")
                    .description("Represents an advancement frame. (Challenge, Goal, Task)")
                    .since("1.3")
            );
        }
    }
}
