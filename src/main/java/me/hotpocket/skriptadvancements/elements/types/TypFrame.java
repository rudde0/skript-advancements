package me.hotpocket.skriptadvancements.elements.types;

import ch.njol.skript.registrations.Classes;
import me.hotpocket.skriptadvancements.EnumClassInfo;
import me.hotpocket.skriptadvancements.advancementcreator.Advancement;

public class TypFrame {
    static{
        if(Classes.getExactClassInfo(Advancement.Frame.class) == null){
            Classes.registerClass(new EnumClassInfo<>(Advancement.Frame.class, "frame")
                    .user("frames")
                    .name("Advancement Frame")
                    .description("Represents an advancement frame. (Challenge, Goal, Task)")
                    .since("1.3")
            );
        }
    }
}
